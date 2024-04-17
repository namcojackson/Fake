/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6740;

import static business.blap.NMAL6740.constant.NMAL6740Constant.*;
import static business.blap.NMAL6740.constant.NMAL6740Constant.COA_CH;
import static business.blap.NMAL6740.constant.NMAL6740Constant.NMAM8105E;
import static business.blap.NMAL6740.constant.NMAL6740Constant.NMAM8121E;
import static business.blap.NMAL6740.constant.NMAL6740Constant.NMAM8333I;
import static business.blap.NMAL6740.constant.NMAL6740Constant.ZZZM9004E;
import static business.blap.NMAL6740.constant.NMAL6740Constant.ZZZM9013E;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL6740.common.NMAL6740CommonLogic;
import business.db.SHIP_TO_CUSTTMsg;
import business.parts.NFZC102001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/20   CUSA            Fujitsu         Create          N/A
 * 2015/10/09   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/18   Fujitsu         C.Tanaka        Update          QC#2440
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 * 2018/04/16   Fujitsu         M.Ohno          Update          QC#24635
 * 2018/08/07   Fujitsu         S.Ohki          Update          QC#27222
 * 2019/04/17   Fujitsu         T.Noguchi       Update          QC#31192
 *</pre>
 */
public class NMAL6740BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NMAL6740CMsg bizMsg = (NMAL6740CMsg) cMsg;
        NMAL6740SMsg sharedMsg = (NMAL6740SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6740Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6740Scrn00_CMN_Submit(bizMsg, sharedMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NMAL6740Scrn00_Linkage_CMN_Submit <dd>
     * The method explanation: submit button event
     * @param bizMsg NMAL6740CMsg
     * @param sharedMsg NMAL6740SMsg
     */
    private void doProcess_NMAL6740Scrn00_CMN_Submit(NMAL6740CMsg bizMsg, NMAL6740SMsg sharedMsg) {
        
        // 2018/04/16 QC#24635 add start
        if (!NMAL6740CommonLogic.inputCheckForExpenseAccount(bizMsg)) {
            return;
        }
        // 2018/04/16 QC#24635 add end

        if (!updateCheck(bizMsg)) {
            bizMsg.setMessageInfo(NMAM8333I);
            return;
        }

        // 2018/04/16 QC#24635 del start
//        if (!inputCheckForCoa(bizMsg)) {
//            return;
//        }
        // 2018/04/16 QC#24635 del end

        if (!coaCmbnCheck(bizMsg)) {
            return;
        }

        if (!updateDsShipToCust(bizMsg)) {
            return;
        }
    }

    @SuppressWarnings("unchecked")
    private boolean updateCheck(NMAL6740CMsg cMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String shipToCustCd = cMsg.shipToCustCd.getValue();

        S21SsmEZDResult res = NMAL6740Query.getInstance().getShipToCustInfoByShipToCustCd(glblCmpyCd, shipToCustCd);
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            Map map = (Map) list.get(0);

            if (!cMsg.ezUpTime.getValue().equals(nvlItem((String) map.get("EZUPTIME")))) {
                return true;
            }
            if (!cMsg.ezUpTimeZone.getValue().equals(nvlItem((String) map.get("EZUPTIMEZONE")))) {
                return true;
            }
            if (cMsg.shipToCustPk.getValue().compareTo((BigDecimal) map.get("SHIP_TO_CUST_PK")) != 0) {
                return true;
            }
            if (!cMsg.shipToCustCd.getValue().equals(nvlItem((String) map.get("SHIP_TO_CUST_CD")))) {
                return true;
            }
            if (!cMsg.dsAcctNm.getValue().equals(nvlItem((String) map.get("DS_ACCT_NM")))) {
                return true;
            }
            if (!cMsg.xxAllLineAddr.getValue().equals(nvlItem((String) map.get("ALL_ADDR")))) {
                return true;
            }
            if (!cMsg.ctyAddr.getValue().equals(nvlItem((String) map.get("CTY_ADDR")))) {
                return true;
            }
            if (!cMsg.stCd.getValue().equals(nvlItem((String) map.get("ST_CD")))) {
                return true;
            }
            if (!cMsg.postCd.getValue().equals(nvlItem((String) map.get("POST_CD")))) {
                return true;
            }
            if (!cMsg.locNum.getValue().equals(nvlItem((String) map.get("LOC_NUM")))) {
                return true;
            }
            if (!cMsg.coaChCd.getValue().equals(nvlItem((String) map.get("COA_CH_CD")))) {
                return true;
            }
// QC#9448
            // 2019/04/17 QC#31192 Add Start
            if (!cMsg.coaAfflCd.getValue().equals(nvlItem((String) map.get("COA_AFFL_CD")))) {
                return true;
            }
            // 2019/04/17 QC#31192 Add End
            if (!cMsg.coaCmpyCd.getValue().equals(nvlItem((String) map.get("COA_CMPY_CD")))) {
                return true;
            }
            if (!cMsg.coaBrCd.getValue().equals(nvlItem((String) map.get("COA_BR_CD")))) {
                return true;
            }
            if (!cMsg.coaCcCd.getValue().equals(nvlItem((String) map.get("COA_CC_CD")))) {
                return true;
            }
            if (!cMsg.coaAcctCd.getValue().equals(nvlItem((String) map.get("COA_ACCT_CD")))) {
                return true;
            }
            if (!cMsg.coaProdCd.getValue().equals(nvlItem((String) map.get("COA_PROD_CD")))) {
                return true;
            }
            if (!cMsg.coaProjCd.getValue().equals(nvlItem((String) map.get("COA_PROJ_CD")))) {
                return true;
            }
            if (!cMsg.coaExtnCd.getValue().equals(nvlItem((String) map.get("COA_EXTN_CD")))) {
                return true;
            }
            // Del Start 2018/08/07 QC#27222
//            if (!cMsg.dsCustTaxCd.getValue().equals(nvlItem((String) map.get("TAX_CD")))) {
//                return true;
//            }
//            if (!cMsg.dsCustTaxCalcCd.getValue().equals(nvlItem((String) map.get("TAX_CALC_CD")))) {
//                return true;
//            }
//            if (!nvlFlgItem(cMsg.dsTaxExemFlg.getValue()).equals(nvlFlgItem((String) map.get("DS_TAX_EXEM_FLG")))) {
//                return true;
//            }
//            if (!cMsg.dsExemExprDt.getValue().equals(nvlItem((String) map.get("DS_EXEM_EXPR_DT")))) {
//                return true;
//            }
//            if (!cMsg.dsTaxPrntTpCd.getValue().equals(nvlItem((String) map.get("DS_TAX_PRNT_TP_CD")))) {
//                return true;
//            }
            // Del End 2018/08/07 QC#27222
            if (!cMsg.dsTaxGrpExemCd.getValue().equals(nvlItem((String) map.get("DS_TAX_GRP_EXEM_CD")))) {
                return true;
            }
            if (!cMsg.bigDealNum.getValue().equals(nvlItem((String) map.get("BIG_DEAL_NUM")))) {
                return true;
            }
            if (!cMsg.coaChCd_BK.getValue().equals(nvlItem((String) map.get("COA_CH_CD_ACCT")))) {
                return true;
            }
// QC#9448
//            if (!cMsg.coaAfflCd_BK.getValue().equals(nvlItem((String) map.get("COA_AFFL_CD_ACCT")))) {
//                return true;
//            }
        } else {
            return true;
        }
        return false;
    }

    private String nvlItem(String item) {
        if (ZYPCommonFunc.hasValue(item)) {
            return item;
        } else {
            return "";
        }
    }

    private String nvlFlgItem(String item) {
        if (ZYPCommonFunc.hasValue(item)) {
            return item;
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }

    private boolean inputCheckForCoa(NMAL6740CMsg bizMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        // QC#9448
        // String coaAfflCd = bizMsg.coaAfflCd.getValue();
        String coaChCd = bizMsg.coaChCd.getValue();

        if (ZYPCommonFunc.hasValue(coaChCd)) {
            if (NMAL6740CommonLogic.findCoaCh(glblCmpyCd, coaChCd) == null) {
                bizMsg.coaChCd.setErrorInfo(1, NMAM8121E, new String[] {COA_CH });
                return false;
            }
        }
// QC#9448
//        if (ZYPCommonFunc.hasValue(coaAfflCd)) {
//            if (NMAL6740CommonLogic.findCoaAffl(glblCmpyCd, coaAfflCd) == null) {
//                bizMsg.coaAfflCd.setErrorInfo(1, NMAM8121E, new String[] {COA_AFFL });
//                return false;
//            }
//        }

        return true;
    }

    @SuppressWarnings("unchecked")
    private boolean coaCmbnCheck(NMAL6740CMsg bizMsg) {

        // GL Code Combination Check API
        NFZC102001 afzc102001 = new NFZC102001();
        NFZC102001PMsg paramMsg = new NFZC102001PMsg();

        ZYPEZDItemValueSetter.setValue(paramMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(paramMsg.xxMstChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(paramMsg.xxGlCmbnChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(paramMsg.xxArcsApiChkFlg, "");
        ZYPEZDItemValueSetter.setValue(paramMsg.coaCmpyCd, bizMsg.coaCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(paramMsg.coaAfflCd, bizMsg.coaAfflCd.getValue());
        ZYPEZDItemValueSetter.setValue(paramMsg.coaBrCd, bizMsg.coaBrCd.getValue());
        ZYPEZDItemValueSetter.setValue(paramMsg.coaCcCd, bizMsg.coaCcCd.getValue());
        ZYPEZDItemValueSetter.setValue(paramMsg.coaAcctCd, bizMsg.coaAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(paramMsg.coaProdCd, bizMsg.coaProdCd.getValue());
        ZYPEZDItemValueSetter.setValue(paramMsg.coaChCd, bizMsg.coaChCd.getValue());
        ZYPEZDItemValueSetter.setValue(paramMsg.coaProjCd, bizMsg.coaProjCd.getValue());
        ZYPEZDItemValueSetter.setValue(paramMsg.coaExtnCd, bizMsg.coaExtnCd.getValue());
        ZYPEZDItemValueSetter.setValue(paramMsg.resrcObjNm, BIZ_ID);

        afzc102001.execute(paramMsg, ONBATCH_TYPE.ONLINE);

        // 2019/04/17 QC#31192 Mod Start
        //List msgIdList = S21ApiUtil.getXxMsgIdList(paramMsg);
        //if (msgIdList != null && msgIdList.size() > 0) {
        //    bizMsg.setMessageInfo((String) msgIdList.get(0));
        //    return false;
        //}
        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
        if (msgList != null && msgList.size() > 0) {
            S21ApiMessage msg = msgList.get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            if (msgPrms != null && msgPrms.length > 0) {
                bizMsg.setMessageInfo(msgId, msgPrms);
            } else {
                bizMsg.setMessageInfo(msgId);
            }
            return false;
        }
        // 2019/04/17 QC#31192 Mod End
        return true;
    }

    private boolean updateDsShipToCust(NMAL6740CMsg bizMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        BigDecimal shipToCustPk = bizMsg.shipToCustPk.getValue();

        SHIP_TO_CUSTTMsg updateShipToCustTMsg = NMAL6740CommonLogic.findDsShipToCustForUpdate(glblCmpyCd, shipToCustPk);
        if (updateShipToCustTMsg == null) {
            bizMsg.setMessageInfo(NMAM8105E);
            return false;
        }

        if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime.getValue(), bizMsg.ezUpTimeZone.getValue(), updateShipToCustTMsg.ezUpTime.getValue(), updateShipToCustTMsg.ezUpTimeZone.getValue())) {
            bizMsg.setMessageInfo(ZZZM9004E);
            return false;
        }

        setDsShipToCustData(updateShipToCustTMsg, bizMsg);

        S21FastTBLAccessor.update(updateShipToCustTMsg);
        if (!RTNCD_NORMAL.equals(updateShipToCustTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(ZZZM9013E, new String[] {updateShipToCustTMsg.getReturnCode() });
            return false;
        }
        return true;
    }

    private void setDsShipToCustData(SHIP_TO_CUSTTMsg targetTMsg, NMAL6740CMsg bizMsg) {

        ZYPEZDItemValueSetter.setValue(targetTMsg.coaAcctCd, bizMsg.coaAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(targetTMsg.coaAfflCd, bizMsg.coaAfflCd.getValue());
        ZYPEZDItemValueSetter.setValue(targetTMsg.coaBrCd, bizMsg.coaBrCd.getValue());
        ZYPEZDItemValueSetter.setValue(targetTMsg.coaCcCd, bizMsg.coaCcCd.getValue());
        ZYPEZDItemValueSetter.setValue(targetTMsg.coaChCd, bizMsg.coaChCd.getValue());
        ZYPEZDItemValueSetter.setValue(targetTMsg.coaCmpyCd, bizMsg.coaCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(targetTMsg.coaExtnCd, bizMsg.coaExtnCd.getValue());
        ZYPEZDItemValueSetter.setValue(targetTMsg.coaProdCd, bizMsg.coaProdCd.getValue());
        ZYPEZDItemValueSetter.setValue(targetTMsg.coaProjCd, bizMsg.coaProjCd.getValue());
        // Del Start 2018/08/07 QC#27222
//        ZYPEZDItemValueSetter.setValue(targetTMsg.dsCustTaxCd, bizMsg.dsCustTaxCd.getValue());
//        ZYPEZDItemValueSetter.setValue(targetTMsg.dsCustTaxCalcCd, bizMsg.dsCustTaxCalcCd.getValue());
//        ZYPEZDItemValueSetter.setValue(targetTMsg.dsExemExprDt, bizMsg.dsExemExprDt.getValue());
//        ZYPEZDItemValueSetter.setValue(targetTMsg.dsTaxPrntTpCd, bizMsg.dsTaxPrntTpCd.getValue());
        // Del Start 2018/08/07 QC#27222
        ZYPEZDItemValueSetter.setValue(targetTMsg.dsTaxGrpExemCd, bizMsg.dsTaxGrpExemCd.getValue());
        ZYPEZDItemValueSetter.setValue(targetTMsg.bigDealNum, bizMsg.bigDealNum.getValue());

        // Del Start 2018/08/07 QC#27222
//        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.dsTaxExemFlg.getValue())) {
//            ZYPEZDItemValueSetter.setValue(targetTMsg.dsTaxExemFlg, ZYPConstant.FLG_ON_Y);
//        } else {
//            ZYPEZDItemValueSetter.setValue(targetTMsg.dsTaxExemFlg, ZYPConstant.FLG_OFF_N);
//        }
        // Del End 2018/08/07 QC#27222
    }
}
