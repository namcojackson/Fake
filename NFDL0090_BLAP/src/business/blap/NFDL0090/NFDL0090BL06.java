/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0090;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0090.common.NFDL0090CommonLogic;
import business.blap.NFDL0090.constant.NFDL0090Constant;
import business.db.AR_TRX_BALTMsg;
import static business.blap.NFDL0090.common.NFDL0090CommonLogic.*;
import static business.blap.NFDL0090.constant.NFDL0090Constant.*;
import business.db.AR_WRT_OFF_RQSTTMsg;
import business.db.AR_WRT_OFF_RQST_DTLTMsg;

import com.canon.cusa.s21.api.NFD.NFZC500001.NFZC500001TokenObject;
import com.canon.cusa.s21.api.NFD.NFZC500001.NFZC500001TokenObjectLine;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFCCurrencyConversion;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_WRT_OFF_RQST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Fujitsu         M.Nakamura      Create          N/A
 * 2016/05/11   Hitachi         K.Kojima        Update          QC#7638
 * 2016/11/07   Hitachi         J.Kim           Update          QC#15712
 * 2016/12/02   Fujitsu         H.Ikeda         Update          QC#15823
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2018/02/28   Hitachi         J.Kim           Update          QC#20944
 * 2018/03/01   Hitachi         J.Kim           Update          QC#21141
 * 2018/05/31   Fujitsu         S.Ohki          Update          QC#24747
 * 2018/08/23   Hitachi         E.Kameishi      Update          QC#25188
 * 2018/09/11   Hitachi         Y.Takeno        Create          QC#24884
 * 2018/09/14   Hitachi         Y.Takeno        Update          QC#24884
 * 2018/11/19   Fujitsu         T.Ogura         Update          QC#29332
 * 2020/02/20   Fujitsu         H.Mizukami      Update          QC#55773
 * 2021/02/15   CITS            A.Raguero       Update          QC#56199
 * 2021/02/18   CITS            A.Raguero       Update          QC#56199
 * 2022/11/24   Hitachi         S.Naya          Update          QC#57252
 *</pre>
 */
public class NFDL0090BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFDL0090CMsg bizMsg = (NFDL0090CMsg) cMsg;
            NFDL0090SMsg glblMsg = (NFDL0090SMsg) sMsg;

            if ("NFDL0090Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0090Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * CMN_Submit Event
     * </pre>
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NFDL0090Scrn00_CMN_Submit(NFDL0090CMsg cMsg, NFDL0090SMsg sMsg) {
        // START 2022/11/24 S.Naya [QC#57252,ADD]
        // check 9segment
        if (OTHER_CODE.equals(cMsg.arAdjTpCd_FS.getValue())) {
            String[] coa = cMsg.xxCmntTxt_FS.getValue().split("\\.");
            if(!checkGlCodeCombination(cMsg, coa, getGlobalCompanyCode())){
                return;
            }
        }
        // END   2022/11/24 S.Naya [QC#57252,ADD]
        boolean isNotInsert = true;
        // START 2016/05/11 K.Kojima [QC#7638,MOD]
        // int paginationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int paginationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        // END 2016/05/11 K.Kojima [QC#7638,MOD]
        NFDL0090CommonLogic.setCurrentPageIn(cMsg, sMsg, paginationFrom);

        String userId = getContextUserInfo().getUserId();
        String aGrKey = userId.concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));

        List<BigDecimal> pks = new ArrayList<BigDecimal>();
        // START 2018/08/23 E.Kameishi [QC#25188,MOD]
        BigDecimal pk = insertRqst(cMsg, sMsg, aGrKey, userId);
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            }
            //BigDecimal pk = insertRqst(cMsg, sMsg.A.no(i), aGrKey, userId);
            insertRqstDtl(cMsg, sMsg.A.no(i), aGrKey, userId, pk);
            if (pk == null) {
                NFDL0090CommonLogic.setCurrentPageOut(cMsg, sMsg, paginationFrom);
                return;
            }
            pks.add(pk);
            isNotInsert = false;
        }
        // END 2018/08/23 E.Kameishi [QC#25188,MOD]

        if (isNotInsert) {
            cMsg.setMessageInfo(NFDL0090Constant.NFDM0002E, new String[] {"line(s)"});
            NFDL0090CommonLogic.setCurrentPageOut(cMsg, sMsg, paginationFrom);
            return;
        }

        startWorkflow(cMsg, sMsg, pks, aGrKey, userId);

        // START 2018/09/11 [QC#24884, ADD]
        // START 2018/09/14 [QC#24884, MOD]
        if (!NFDL0090Constant.MESSAGE_KIND_ERROR.equals(cMsg.getMessageKind())) {
            ZYPEZDItemValueSetter.setValue(cMsg.arWrtOffRqstPk_H1, pk);
        }
        // END   2018/09/14 [QC#24884, MOD]
        // END   2018/09/11 [QC#24884, ADD]

        cMsg.setMessageInfo(NFDL0090Constant.NFDM0014I, new String[] {aGrKey});
    }

    private BigDecimal nullZero(BigDecimal val) {
        if (val == null) {
            return BigDecimal.ZERO;
        }
        return val;
    }

    private BigDecimal insertRqst(NFDL0090CMsg cMsg, NFDL0090SMsg sMsg, String aGrKey, String userId) {
        // START 2018/08/23 E.Kameishi [QC#25188,MOD]
        //AR_TRX_BALTMsg condTMsg = new AR_TRX_BALTMsg();
        //ZYPEZDItemValueSetter.setValue(condTMsg.glblCmpyCd, getGlobalCompanyCode());
        //ZYPEZDItemValueSetter.setValue(condTMsg.arTrxBalPk, aSMsg.arTrxBalPk_A1);
        //AR_TRX_BALTMsg arTrxBalTMsg = (AR_TRX_BALTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(condTMsg);
        //if (arTrxBalTMsg == null) {
        //    cMsg.setMessageInfo(NFDL0090Constant.NFCM0041E);
        //    return;
        //}

        //NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        
        //---- start 2016/02/18 pending amount = current pending amount + this request's amount
        //BigDecimal dealAmt = aSMsg.xxDealApplyAmtNum_A1.getValue().add(nullZero(arTrxBalTMsg.dealApplyAdjRsvdAmt.getValue()));
        //BigDecimal funcAmt = afxc3070.getFuncAmtByRate(getGlobalCompanyCode(), arTrxBalTMsg.dealCcyCd.getValue(), dealAmt, arTrxBalTMsg.exchRate.getValue());

        //ZYPEZDItemValueSetter.setValue(arTrxBalTMsg.dealApplyAdjRsvdAmt, dealAmt);
        //ZYPEZDItemValueSetter.setValue(arTrxBalTMsg.funcApplyAdjRsvdAmt, funcAmt);
        //---- end 2016/02/18

        //S21FastTBLAccessor.update(arTrxBalTMsg);
        AR_WRT_OFF_RQSTTMsg inMsg = new AR_WRT_OFF_RQSTTMsg();

        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.AR_WRT_OFF_RQST_SQ);
        ZYPEZDItemValueSetter.setValue(inMsg.arWrtOffRqstPk, pk);
        ZYPEZDItemValueSetter.setValue(inMsg.wrtOffRqstGrpCd, aGrKey);
        ZYPEZDItemValueSetter.setValue(inMsg.wrtOffRqstUsrId, userId);
        inMsg.arAdjRsnCd.clear();
        ZYPEZDItemValueSetter.setValue(inMsg.arAdjTpCd, cMsg.arAdjTpCd_FS);
        inMsg.lowRmngBalAmt.clear();
        inMsg.highRmngBalAmt.clear();
        //ZYPEZDItemValueSetter.setValue(inMsg.lowInvNum, aSMsg.arTrxNum_A1);
        //ZYPEZDItemValueSetter.setValue(inMsg.highInvNum, aSMsg.arTrxNum_A1);
        inMsg.lowInvDueDt.clear();
        inMsg.highInvDueDt.clear();
        // START 2018/02/28 J.Kim [QC#21141,MOD]
        // inMsg.lowDsAcctNum.clear();
        // inMsg.highDsAcctNum.clear();
        ZYPEZDItemValueSetter.setValue(inMsg.lowDsAcctNum, cMsg.dsAcctNum_H1);
        ZYPEZDItemValueSetter.setValue(inMsg.highDsAcctNum, cMsg.dsAcctNum_H1);
        // END 2018/02/28 J.Kim [QC#21141,MOD]
        inMsg.lowBillToCustCd.clear();
        inMsg.highBillToCustCd.clear();
        inMsg.invTpCd.clear();
        ZYPEZDItemValueSetter.setValue(inMsg.inclConslInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inMsg.wrtOffOptTpCd, NFDL0090Constant.CRAT_ADJ);
        //ZYPEZDItemValueSetter.setValue(inMsg.wrtOffApplyAmt, aSMsg.xxDealApplyAmtNum_A1);
        ZYPEZDItemValueSetter.setValue(inMsg.arWrtOffNoteCd, cMsg.arWrtOffNoteCd_FS);
        ZYPEZDItemValueSetter.setValue(inMsg.arWrtOffNoteTxt, cMsg.arWrtOffNoteTxt_FS);
        ZYPEZDItemValueSetter.setValue(inMsg.arWrtOffRqstTpCd, AR_WRT_OFF_RQST_TP.WRITE_OFF_SCREEN);
        ZYPEZDItemValueSetter.setValue(inMsg.arDsWfStsCd, AR_DS_WF_STS.PENDING);
        ZYPEZDItemValueSetter.setValue(inMsg.procStsCd, PROC_STS.IN_COMPLETED);
        inMsg.wrtOffProcDt.clear();
        inMsg.wrtOffErrMsgTxt.clear();
        // START 2018/11/19 T.Ogura [QC#29332,MOD]
//        BigDecimal lowInvNum = BigDecimal.ZERO;
//        BigDecimal highInvNum = BigDecimal.ZERO;
        String lowInvNum = "";
        String highInvNum = "";
        // END   2018/11/19 T.Ogura [QC#29332,MOD]
        boolean firstFlg = true;
        BigDecimal applyAmt = BigDecimal.ZERO;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            }
            applyAmt = applyAmt.add(sMsg.A.no(i).xxDealApplyAmtNum_A1.getValue());
            
            if (firstFlg) {
                // START 2018/11/19 T.Ogura [QC#29332,MOD]
//                lowInvNum = new BigDecimal(sMsg.A.no(i).arTrxNum_A1.getValue());
//                highInvNum = new BigDecimal(sMsg.A.no(i).arTrxNum_A1.getValue());
                lowInvNum = sMsg.A.no(i).arTrxNum_A1.getValue();
                highInvNum = sMsg.A.no(i).arTrxNum_A1.getValue();
                // END   2018/11/19 T.Ogura [QC#29332,MOD]
                firstFlg = false;
                continue;
            }
            // START 2018/11/19 T.Ogura [QC#29332,MOD]
//            BigDecimal tmpInvNum = new BigDecimal(sMsg.A.no(i).arTrxNum_A1.getValue());
            String tmpInvNum = sMsg.A.no(i).arTrxNum_A1.getValue();
            // END   2018/11/19 T.Ogura [QC#29332,MOD]
            if (lowInvNum.compareTo(tmpInvNum) > 0) {
                lowInvNum = tmpInvNum;
            }
            if (highInvNum.compareTo(tmpInvNum) < 0) {
                highInvNum = tmpInvNum;
            }
        }
        // START 2018/11/19 T.Ogura [QC#29332,MOD]
//        ZYPEZDItemValueSetter.setValue(inMsg.lowInvNum, lowInvNum.toString());
//        ZYPEZDItemValueSetter.setValue(inMsg.highInvNum, highInvNum.toString());
        ZYPEZDItemValueSetter.setValue(inMsg.lowInvNum, lowInvNum);
        ZYPEZDItemValueSetter.setValue(inMsg.highInvNum, highInvNum);
        // END   2018/11/19 T.Ogura [QC#29332,MOD]
        ZYPEZDItemValueSetter.setValue(inMsg.wrtOffApplyAmt, applyAmt);
        // START 2022/11/24 S.Naya [QC#57252,ADD]
        if (OTHER_CODE.equals(cMsg.arAdjTpCd_FS.getValue())) {
            // Separate 9 segment
            int coaIdx = 0;
            String[] coa = cMsg.xxCmntTxt_FS.getValue().split("\\.");

            // Set 9 segment
            inMsg.adjCoaCmpyCd.setValue(coa[coaIdx++]);
            inMsg.adjCoaBrCd.setValue(coa[coaIdx++]);
            inMsg.adjCoaCcCd.setValue(coa[coaIdx++]);
            inMsg.adjCoaAcctCd.setValue(coa[coaIdx++]);
            inMsg.adjCoaProdCd.setValue(coa[coaIdx++]);
            inMsg.adjCoaChCd.setValue(coa[coaIdx++]);
            inMsg.adjCoaAfflCd.setValue(coa[coaIdx++]);
            inMsg.adjCoaProjCd.setValue(coa[coaIdx++]);
            inMsg.adjCoaExtnCd.setValue(coa[coaIdx++]);
        }
        // END   2022/11/24 S.Naya [QC#57252,ADD]

        EZDTBLAccessor.insert(inMsg);
        // START 2016/12/05 H.Ikeda [QC#15823,ADD]
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            cMsg.setMessageInfo(NFDL0090Constant.NFDM0013E, new String[] {"AR_WRT_OFF_RQST"});
            return null;
        }
        // END   2016/12/05 H.Ikeda [QC#15823,ADD]
        //AR_WRT_OFF_RQST_DTLTMsg inDtlMsg = new AR_WRT_OFF_RQST_DTLTMsg();
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.glblCmpyCd, inMsg.glblCmpyCd);
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.arWrtOffRqstPk, inMsg.arWrtOffRqstPk);
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.arTrxBalPk, aSMsg.arTrxBalPk_A1);
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.invTrxBalLastUpdTs, arTrxBalTMsg.ezUpTime);
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.invTrxBalTz, arTrxBalTMsg.ezUpTimeZone);
        //---- start add 2016/03/01 new fields
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.arTrxNum, arTrxBalTMsg.arTrxNum.getValue());
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.billToCustCd, arTrxBalTMsg.billToCustCd.getValue());
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.billToCustAcctCd, arTrxBalTMsg.billToCustAcctCd.getValue());
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.dealCcyCd, arTrxBalTMsg.dealCcyCd.getValue());
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.dealOrigGrsAmt, arTrxBalTMsg.dealOrigGrsAmt.getValue());
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.funcOrigGrsAmt, arTrxBalTMsg.funcOrigGrsAmt.getValue());
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.dealRmngBalGrsAmt, arTrxBalTMsg.dealRmngBalGrsAmt.getValue());
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.funcRmngBalGrsAmt, arTrxBalTMsg.funcRmngBalGrsAmt.getValue());
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.arTrxDt, arTrxBalTMsg.arTrxDt.getValue());
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.invDueDt, arTrxBalTMsg.invDueDt.getValue());
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.daysPastDueAot, new BigDecimal(ZYPDateUtil.getDiffDays(ZYPDateUtil.getSalesDate(), arTrxBalTMsg.invDueDt.getValue())));
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.dsContrNum, aSMsg.dsContrNum_A1.getValue());
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.bllgPerFromDt, aSMsg.bllgPerFromDt_A1.getValue());
        //ZYPEZDItemValueSetter.setValue(inDtlMsg.bllgPerToDt, aSMsg.bllgPerToDt_A1.getValue());
        //---- end 2016/03/01

        //EZDTBLAccessor.insert(inDtlMsg);
        // START 2016/12/05 H.Ikeda [QC#15823,ADD]
        //if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inDtlMsg.getReturnCode())) {
        //    cMsg.setMessageInfo(NFDL0090Constant.NFDM0013E, new String[] {"AR_WRT_OFF_RQST_DTL"});
        //    return null;
        //}
        // END   2016/12/05 H.Ikeda [QC#15823,ADD]
        // END 2018/08/23 E.Kameishi [QC#25188,MOD]
        return pk;
    }
    private void insertRqstDtl(NFDL0090CMsg cMsg, NFDL0090_ASMsg aSMsg, String aGrKey, String userId, BigDecimal arWrtOffRqstPk) {

        AR_TRX_BALTMsg condTMsg = new AR_TRX_BALTMsg();
        ZYPEZDItemValueSetter.setValue(condTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(condTMsg.arTrxBalPk, aSMsg.arTrxBalPk_A1);
        AR_TRX_BALTMsg arTrxBalTMsg = (AR_TRX_BALTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(condTMsg);
        if (arTrxBalTMsg == null) {
            cMsg.setMessageInfo(NFDL0090Constant.NFCM0041E);
            return;
        }

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        
      //---- start 2016/02/18 pending amount = current pending amount + this request's amount
        BigDecimal dealAmt = aSMsg.xxDealApplyAmtNum_A1.getValue().add(nullZero(arTrxBalTMsg.dealApplyAdjRsvdAmt.getValue()));
        BigDecimal funcAmt = afxc3070.getFuncAmtByRate(getGlobalCompanyCode(), arTrxBalTMsg.dealCcyCd.getValue(), dealAmt, arTrxBalTMsg.exchRate.getValue());

        ZYPEZDItemValueSetter.setValue(arTrxBalTMsg.dealApplyAdjRsvdAmt, dealAmt);
        ZYPEZDItemValueSetter.setValue(arTrxBalTMsg.funcApplyAdjRsvdAmt, funcAmt);
        //---- end 2016/02/18

        S21FastTBLAccessor.update(arTrxBalTMsg);

        AR_WRT_OFF_RQST_DTLTMsg inDtlMsg = new AR_WRT_OFF_RQST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inDtlMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inDtlMsg.arWrtOffRqstPk, arWrtOffRqstPk);
        ZYPEZDItemValueSetter.setValue(inDtlMsg.arTrxBalPk, aSMsg.arTrxBalPk_A1);
        ZYPEZDItemValueSetter.setValue(inDtlMsg.invTrxBalLastUpdTs, arTrxBalTMsg.ezUpTime);
        ZYPEZDItemValueSetter.setValue(inDtlMsg.invTrxBalTz, arTrxBalTMsg.ezUpTimeZone);
        //---- start add 2016/03/01 new fields
        ZYPEZDItemValueSetter.setValue(inDtlMsg.arTrxNum, arTrxBalTMsg.arTrxNum.getValue());
        ZYPEZDItemValueSetter.setValue(inDtlMsg.billToCustCd, arTrxBalTMsg.billToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(inDtlMsg.billToCustAcctCd, arTrxBalTMsg.billToCustAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(inDtlMsg.dealCcyCd, arTrxBalTMsg.dealCcyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inDtlMsg.dealOrigGrsAmt, arTrxBalTMsg.dealOrigGrsAmt.getValue());
        ZYPEZDItemValueSetter.setValue(inDtlMsg.funcOrigGrsAmt, arTrxBalTMsg.funcOrigGrsAmt.getValue());
        ZYPEZDItemValueSetter.setValue(inDtlMsg.dealRmngBalGrsAmt, arTrxBalTMsg.dealRmngBalGrsAmt.getValue());
        ZYPEZDItemValueSetter.setValue(inDtlMsg.funcRmngBalGrsAmt, arTrxBalTMsg.funcRmngBalGrsAmt.getValue());
        ZYPEZDItemValueSetter.setValue(inDtlMsg.arTrxDt, arTrxBalTMsg.arTrxDt.getValue());
        ZYPEZDItemValueSetter.setValue(inDtlMsg.invDueDt, arTrxBalTMsg.invDueDt.getValue());
        ZYPEZDItemValueSetter.setValue(inDtlMsg.daysPastDueAot, new BigDecimal(ZYPDateUtil.getDiffDays(ZYPDateUtil.getSalesDate(), arTrxBalTMsg.invDueDt.getValue())));
        ZYPEZDItemValueSetter.setValue(inDtlMsg.dsContrNum, aSMsg.dsContrNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(inDtlMsg.bllgPerFromDt, aSMsg.bllgPerFromDt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(inDtlMsg.bllgPerToDt, aSMsg.bllgPerToDt_A1.getValue());
        //---- end 2016/03/01

        EZDTBLAccessor.insert(inDtlMsg);
        // START 2016/12/05 H.Ikeda [QC#15823,ADD]
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inDtlMsg.getReturnCode())) {
            cMsg.setMessageInfo(NFDL0090Constant.NFDM0013E, new String[] {"AR_WRT_OFF_RQST_DTL"});
        }
        // END   2016/12/05 H.Ikeda [QC#15823,ADD]
    }
    // END 2018/08/23 E.Kameishi [QC#25188,ADD] 

    private void startWorkflow(NFDL0090CMsg cMsg, NFDL0090SMsg sMsg, List<BigDecimal> pks, String aGrKey, String userId) {

        String wfId  = getWorkflowId(getGlobalCompanyCode(), NFDL0090Constant.CONST_KEY_VLD_APVL_WF_ID);
        //---- start 2016/02/18 change key to request group code  
        //String wfKey  = cMsg.dsAcctNum_H1.getValue();
        String wfKey  = aGrKey;
        //---- end 2016/02/18
        if (isWorkflowStarted(wfId, wfKey)) {
            return;
        }

        // Business Token Object
        NFZC500001TokenObject tokenBiz = new NFZC500001TokenObject();

        // Calc Summary
        BigDecimal summaryWriteOffAmt = new BigDecimal(0);

        for (int i = 0, idx = 0; i < sMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            }

            // Summary
            summaryWriteOffAmt = summaryWriteOffAmt.add(sMsg.A.no(i).xxDealApplyAmtNum_A1.getValue());

            // Line Data
            NFZC500001TokenObjectLine line = new NFZC500001TokenObjectLine();
            line.setBillToCustCd(sMsg.A.no(i).billToCustCd_A1.getValue());
            line.setCpoOrdNum(sMsg.A.no(i).cpoOrdNum_A1.getValue());
            line.setArTrxNum(sMsg.A.no(i).arTrxNum_A1.getValue());
            line.setArTrxTpCd(sMsg.A.no(i).arTrxTpCd_A1.getValue());
            //---- added 2016/02/17
            line.setArTrxBalPk(sMsg.A.no(i).arTrxBalPk_A1.getValue());
            //---- end 2016/02/17
            line.setPk(pks.get(idx++));
            tokenBiz.addLine(line);
        }

        // START 2018/03/28 J.Kim [QC#20944,DEL]
        //// Get CLT_PTFO PK
        //S21SsmEZDResult ssmResult = NFDL0090Query.getInstance().getCltPtfoPk(cMsg);
        //if (S21SsmEZDResult.RESULT_CODE_NOT_FOUND.equals(ssmResult.getResultCode())) {
        //    cMsg.setMessageInfo(NFDL0090Constant.NZZM0000E);
        //    return;
        //}
        // Long pk = (Long) ssmResult.getResultObject();
        // END 2018/03/28 J.Kim [QC#20944,DEL]

        // Set Condition Data
        tokenBiz.setBizId(wfId);
        tokenBiz.setCondNum1(summaryWriteOffAmt);
        // START 2018/03/28 J.Kim [QC#20944,MOD]
        // tokenBiz.setCondNum2(new BigDecimal(pk));
        tokenBiz.setCondStr1(userId);
        tokenBiz.setPsnNum(userId);
        // END 2018/03/28 J.Kim [QC#20944,MOD]

        // Set Attribute
        tokenBiz.setAttribute2Lbl("Account No");
        tokenBiz.setAttribute2(cMsg.dsAcctNum_H1.getValue());
        tokenBiz.setAttribute3Lbl("Request Amt");
        // START 2016/11/07 J.Kim [QC#15712,MOD]
        // tokenBiz.setAttribute3(summaryWriteOffAmt.toPlainString());
        tokenBiz.setAttribute3(formatAmount(summaryWriteOffAmt));
        // END 2016/11/07 J.Kim [QC#15712,MOD]
        //---- start add 2016/02/18
        tokenBiz.setAttribute1Lbl("Request No");
        tokenBiz.setAttribute1(aGrKey);
        tokenBiz.setAttribute4Lbl("Request Type");
        tokenBiz.setAttribute4("Write-Off Screen");
        //---- end 2016/02/18
        // START 2020/02/20 [QC#55773,MOD]
        // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
        tokenBiz.setAttribute5Lbl("Note");
        // START 2021/02/15 A.Raguero [QC#56199, MOD]
        // String noteStr = cMsg.arWrtOffNoteDescTxt_FD.no(Integer.parseInt(cMsg.arWrtOffNoteCd_FS.getValue())).getValue() + "  " + cMsg.arWrtOffNoteTxt_FS.getValue();
        // START 2021/02/18 A.Raguero [QC#56199, MOD]
        //String noteStr = getWriteOffDescription(cMsg) + ". " + cMsg.arWrtOffNoteTxt_FS.getValue();
        String noteStr = getActivityDescription(cMsg) + ". " + getWriteOffDescription(cMsg) + ". " + cMsg.arWrtOffNoteTxt_FS.getValue();
        // END 2021/02/18 A.Raguero [QC#56199, MOD]
        // END 2021/02/15 A.Raguero [QC#56199, MOD]
        tokenBiz.setAttribute5(noteStr);
        // END 2017/08/21 T.Tsuchida [QC#19573,ADD]
        // END   2020/02/20 [QC#55773,MOD]
        // START 2020/02/20 [QC#55773,ADD]
        tokenBiz.setAttribute6Lbl("Account Name");
        tokenBiz.setAttribute6(cMsg.dsAcctNm_H1.getValue());
        // END   2020/02/20 [QC#55773,ADD]

        // START 2018/05/30 S.Ohki [QC#24747,MOD]
//        // Set Biz Screen ID
//        tokenBiz.setBizScreenId("NFDL0090");
//        // Set Biz Screen Parameters
        tokenBiz.setBizScreenId("NFCL0760");

//        //---- start 2016/06/07 add condition
//        if (hasValue(cMsg.billToCustCd_H1)) {
//            tokenBiz.setBizScreenParams(cMsg.dsAcctNum_H1, cMsg.billToCustCd_H1);
//        } else {
//            tokenBiz.setBizScreenParams(cMsg.dsAcctNum_H1);
//        }
//        //---- end 2016/06/07
        ZYPEZDItemValueSetter.setValue(cMsg.wrtOffRqstGrpCd_P1, aGrKey);
        ZYPEZDItemValueSetter.setValue(cMsg.wrtOffRqstUsrId_P1, userId);
        ZYPEZDItemValueSetter.setValue(cMsg.arWrtOffRqstTpCd_P1, AR_WRT_OFF_RQST_TP.WRITE_OFF_SCREEN);

        tokenBiz.setBizScreenParams(null, cMsg.wrtOffRqstGrpCd_P1, cMsg.wrtOffRqstUsrId_P1, cMsg.arWrtOffRqstTpCd_P1);
        // END 2018/05/30 S.Ohki [QC#24747,MOD]

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        S21NwfProcess process = null;

        try {
            context = factory.getContex();
            process = context.newProcess(wfId);
            process.setDocumentId(wfKey);
            process.setGroup(wfKey);
        } catch (S21NwfSystemException e1) {
            e1.printStackTrace();
            cMsg.setMessageInfo(NFDL0090Constant.NFDM0008E);
            return;
        }

        S21NwfToken token = process.getToken();
        token.setTokenObj(tokenBiz);

        try {
            token.start();
        //---- start 2016/11/14 QC#15747 No approver found error
        } catch (S21NwfBizException e) {
            e.printStackTrace();
            cMsg.setMessageInfo("NFDM0042E");
        //---- end 2016/11/14 
        } catch (S21NwfException e) {
            e.printStackTrace();
            cMsg.setMessageInfo(NFDL0090Constant.NFDM0008E);
            return;
        }
    }

    // START 2016/11/07 J.Kim [QC#15712,ADD]
    private String formatAmount(BigDecimal amt) {
        if (!ZYPCommonFunc.hasValue(amt)) {
            return null;
        }
        NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
        return df.format(amt);
    }
    // END 2016/11/07 J.Kim [QC#15712,ADD]

    private String getWorkflowId(String glblCmpyCd, String constKey) {

        String wfId = ZYPCodeDataUtil.getVarCharConstValue(constKey, glblCmpyCd);

        if (wfId == null) {
            throw new S21AbendException("[Error]Not Found 'VAR_CHAR_CONST' : varCharConstNm=" + constKey);
        }

        return wfId;
    }

    private boolean isWorkflowStarted(String wfId, String wfKey) {

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;

        try {
            context = factory.getContex();

            List<S21NwfProcess>  processes = context.getProcess(wfId, wfKey);
            for (S21NwfProcess p : processes) {
                if (p.isActive()) {
                    return true;
                }
            }
        } catch (S21NwfSystemException e) {
            // System Error
            throw new S21AbendException(NFDL0090Constant.NFDM0008E);
        }

        return false;
    }

    // START 2021/02/15 A.Raguero [QC#56199, ADD]
    private String getWriteOffDescription(NFDL0090CMsg cMsg) {
        S21SsmEZDResult ssmResult = NFDL0090Query.getInstance().getArWrtOffNoteDesc(cMsg);
        String arWrtOffNoteNm = "";

        if (ssmResult.isCodeNormal()) {
            arWrtOffNoteNm = (String) ssmResult.getResultObject();
        }

        return arWrtOffNoteNm;
    }
    // END 2021/02/15 A.Raguero [QC#56199, ADD]

    // START 2021/02/18 A.Raguero [QC#56199, ADD]
    private String getActivityDescription(NFDL0090CMsg cMsg) {
        S21SsmEZDResult ssmResult = NFDL0090Query.getInstance().getArAdjTpDesc(cMsg);
        String arAdjTpDesc = "";

        if (ssmResult.isCodeNormal()) {
            arAdjTpDesc = (String) ssmResult.getResultObject();
        }

        return arAdjTpDesc;
    }
    // END 2021/02/18 A.Raguero [QC#56199, ADD]
}
