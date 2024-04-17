package business.blap.NFCL0730;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL0730.common.NFCL0730CommonLogic;
import business.blap.NFCL0730.constant.NFCL0730Constant;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;
import business.db.HR_TTL_APVL_LIMITTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/17/2015   Fujitsu         T.Tanaka        Create          Initial
 * 03/09/2016   CSAI            K.Uramori       Update          QC#5251 Cash Application is not processed as designed in specification
 * 2016/06/21   Hitachi         K.Kojima        Update          QC#10214
 * 2016/08/04   Hitachi         K.Kojima        Update          QC#12654
 * 2022/11/10   Hitachi         S.Naya          Update          QC#57252
 * 2023/06/30   Hitachi         S.Fujita        Update          QC#60397
 * 2023/08/02   Hitachi         S.Fujita        Update          QC#60397
 *</pre>
 */
public class NFCL0730BL06 extends S21BusinessHandler implements NFCL0730Constant {
    
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL0730Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL0730Scrn00_CMN_Submit(cMsg, sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL0730Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL0730CMsg bizMsg = (NFCL0730CMsg) cMsg;
        NFCL0730SMsg globalMsg = (NFCL0730SMsg) sMsg;
        
        // Addjust Amount
        BigDecimal adjAmount = new BigDecimal(0);
        // START 2016/08/04 K.Kojima [QC#12654,MOD]
        // for( int i = 0; i < bizMsg.A.getValidCount(); i++) {
        // adjAmount =
        // adjAmount.add(bizMsg.A.no(i).dealApplyAmt_A1.getValue());
        // }
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).ezInUserID_A1)) {
                // New record
                adjAmount = adjAmount.add(bizMsg.A.no(i).dealApplyAmt_A1.getValue());
            } else if (bizMsg.A.no(i).ezInUserID_A1.getValue().equals(getContextUserInfo().getUserId())) {
                // Existing record
                adjAmount = adjAmount.add(bizMsg.A.no(i).dealApplyAmt_A1.getValue());
            }
        }
        // END 2016/08/04 K.Kojima [QC#12654,MOD]

        //---- start 2016/03/09 mod
        adjAmount = adjAmount.negate();
        //---- end 2016/03/09
        
        // Check Approval Limit
        // START 2023/06/30 S.Fujita [QC#60397,MOD]
        // AR_ADJ_APVL_LIMITTMsg arAdjApvlLimitTMsg = new AR_ADJ_APVL_LIMITTMsg();
        HR_TTL_APVL_LIMITTMsg hrTtlApvlLimitTMsg = new HR_TTL_APVL_LIMITTMsg();
        String psnCd   = getContextUserInfo().getUserId();
        String hrTtlNm = NFCL0730CommonLogic.getHrTtlNm(bizMsg.glblCmpyCd.getValue(), psnCd);
        // START 2023/08/02 S.Fuijta [QC#60397,ADD]
        if (!ZYPCommonFunc.hasValue(hrTtlNm)) {
            bizMsg.setMessageInfo("NFCM0815E");
            return;
        }
        // END 2023/08/02 S.Fuijta [QC#60397,ADD]
        BigDecimal hrTtlApvlLimitPk = NFCL0730CommonLogic.getHrTtlApvlLimitPk(bizMsg.glblCmpyCd.getValue(), hrTtlNm);
        // START 2023/08/02 S.Fuijta [QC#60397,MOD]
        BigDecimal adjAmountAbs =  adjAmount.abs();
        // arAdjApvlLimitTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        // arAdjApvlLimitTMsg.arAdjApvlUsrId.setValue(getContextUserInfo().getUserId());
        // arAdjApvlLimitTMsg.arAdjCatgCd.setValue("ADJ");
        // arAdjApvlLimitTMsg.apvlLimitRfFlg.setValue(ZYPConstant.FLG_OFF_N);
        // arAdjApvlLimitTMsg.apvlLimitActvFlg.setValue(ZYPConstant.FLG_ON_Y);
        hrTtlApvlLimitTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        // hrTtlApvlLimitTMsg.hrTtlNm.setValue(hrTtlNm);
        hrTtlApvlLimitTMsg.hrTtlApvlLimitPk.setValue(hrTtlApvlLimitPk);
        // hrTtlApvlLimitTMsg.arAdjCatgCd.setValue("ADJ");
        // hrTtlApvlLimitTMsg.apvlLimitActvFlg.setValue(ZYPConstant.FLG_ON_Y);
        // END 2023/08/02 S.Fuijta [QC#60397,MOD]
        // arAdjApvlLimitTMsg = (AR_ADJ_APVL_LIMITTMsg) EZDTBLAccessor.findByKey(arAdjApvlLimitTMsg);
        hrTtlApvlLimitTMsg = (HR_TTL_APVL_LIMITTMsg) EZDTBLAccessor.findByKey(hrTtlApvlLimitTMsg);
        // if(arAdjApvlLimitTMsg == null) {
        if(hrTtlApvlLimitTMsg == null) {
            // START 2016/06/21 K.Kojima [QC#10214,MOD]
            // bizMsg.setMessageInfo("NFCM0041E");
           //  bizMsg.setMessageInfo(NFCM0845E, new String[] {"AR_ADJ_APVL_LIMIT", getContextUserInfo().getUserId() });
            bizMsg.setMessageInfo(NFCM0845E, new String[] {"HR_TTL_APVL_LIMIT", getContextUserInfo().getUserId() });
            // END 2023/06/30 S.Fujita [QC#60397,MOD]
            // END 2016/06/21 K.Kojima [QC#10214,MOD]
            return;
        }
        
        // START 2023/06/30 S.Fujita [QC#60397,MOD]
        // if(adjAmount.compareTo(arAdjApvlLimitTMsg.apvlLimitFromAmt.getValue()) < 0 || adjAmount.compareTo(arAdjApvlLimitTMsg.apvlLimitToAmt.getValue()) > 0) {
        if(adjAmountAbs.compareTo(hrTtlApvlLimitTMsg.apvlLimitAmt.getValue()) > 0) {
        // END 2023/06/30 S.Fujita [QC#60397,MOD]
            bizMsg.setMessageInfo("NFCM0084E");
            return;
        }

        // Check Exclusion
        AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("arTrxNum01", bizMsg.arTrxNum_H1.getValue());
        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");
        
        AR_TRX_BALTMsgArray arTrxBalTMsg = (AR_TRX_BALTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if(arTrxBalTMsg == null) {
            bizMsg.setMessageInfo("NFCM0041E");
            return;
        }
        
        if (!ZYPDateUtil.isSameTimeStamp(arTrxBalTMsg.no(0).ezUpTime.getValue(), arTrxBalTMsg.no(0).ezUpTimeZone.getValue(), bizMsg.ezUpTime_H2.getValue(), bizMsg.ezUpTimeZone_H2.getValue())) {
            bizMsg.setMessageInfo("NFCM0079E");
            return;
        }
        
        String arRcptBatNum = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_CONST_KEY_RCPT_BAT_NUM, bizMsg.glblCmpyCd.getValue());
        String arRcptBatSqNum = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_CONST_KEY_RCPT_BAT_SQ_NUM, bizMsg.glblCmpyCd.getValue());
        String tocCd = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_CONST_KEY_AR_RCPT_TOC_CD, bizMsg.glblCmpyCd.getValue());
        String coaProdCd = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_CONST_KEY_AR_RCPT_PROD_CD, bizMsg.glblCmpyCd.getValue());
        String crMgrPsnCd = NFCL0730CommonLogic.getCrMsgPsnCd(bizMsg);
        

        //--- start mod 2016/03/09 process under this line to be executed per line
        for (int i = 0; i < bizMsg.A.getValidCount() ; i++) {
            
            // skip processed data
            if (hasValue(bizMsg.A.no(i).arAdjNum_A1)) {
                continue;
            }
            
            ////////////////  HEADER ////////////////////////////////////////////////////////////
        
            String rcptNum = NFCL0730CommonLogic.getRcptNum();
            bizMsg.rcptNum_H2.setValue(rcptNum);
    
            // AR_RCPT
            if (!NFCL0730CommonLogic.createArRcptForNewReceipt(bizMsg, rcptNum, arRcptBatNum, arRcptBatSqNum, tocCd, coaProdCd, crMgrPsnCd, i)) {
                return;
            }
            
            // AR_RCPT_DTL
            if (!NFCL0730CommonLogic.createArRcptDtlForNewReceipt(bizMsg, rcptNum)) {
                return;
            }
            
            BigDecimal arTrxBalPk = NFCL0730CommonLogic.getArTrxBalPk();
            if (arTrxBalPk == null) {
                bizMsg.setMessageInfo("NFCM0002E", new String[] {"Numbering" });
                return;
            }
            
            // AR_TRX_BAL(RCPT)
            if (!NFCL0730CommonLogic.createArTrxBalNewReceipt(bizMsg, rcptNum, arTrxBalPk, tocCd, coaProdCd, i)) {
                return;
            }
            
            // AR_APPLY_INTFC_WRK -create(Header)
            String userId = getContextUserInfo().getUserId();
            String aGrKey = userId.concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
            
            if (!NFCL0730CommonLogic.createArApplyIntfcWrkForNewlyHeader(bizMsg, rcptNum, arTrxBalPk, userId, aGrKey, i)) {
                return;
            }
            
            // AR Cash Apply API
            if (!NFCL0730CommonLogic.doApplyBatchAPI(bizMsg, aGrKey)) {
                return;
            }
            /////////////////////////////////////////////////////////////////////////////////////////////
            
            /////////////////////// DETAIL ////////////////////////////////////////
            // AR_APPLY_INTFC_WRK -create(Detail)
            String aGrKeyDtl = userId.concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
            if (!NFCL0730CommonLogic.createArApplyIntfcWrkForNewlyDetail(bizMsg, globalMsg, rcptNum, arTrxBalPk, userId, aGrKeyDtl, i)) {
                return;
            }
    
            // AR Cash Apply API
            if (!NFCL0730CommonLogic.doApplyBatchAPI(bizMsg, aGrKeyDtl)) {
                return;
            }
            // START 2022/11/10 S.Naya [QC#57252,ADD]
            //  AR_ADJ_COA_INFO -Create
            if (OTHER_CODE.equals(bizMsg.A.no(i).arAdjTpCd_A1.getValue())) {
                if (!NFCL0730CommonLogic.createArAdjCoaInfo(bizMsg, aGrKeyDtl, i)) {
                    return;
                }
            }
            // END   2022/11/10 S.Naya [QC#57252,ADD]
            //////////////////////////////////////////////////////////////////////////
        
            // get updated EZUPTIME and EZUPTIMEZONE for the header
            if (!NFCL0730CommonLogic.searchHeader(bizMsg)) {
                return;
            }
            
        }
    
        // AR Credit Profile Update(Balance) API
        if (!NFCL0730CommonLogic.doCreditProfileUpdateAPI(bizMsg)) {
            return;
        }
        
        //---- start 2016/03/10 add
        // refresh data
        if (!NFCL0730CommonLogic.searchHeader(bizMsg)) {
            return;
        }
        NFCL0730CommonLogic.searchDetail(bizMsg);
        //---- end 2016/03/10
        
    }
}
