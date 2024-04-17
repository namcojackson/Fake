/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL0860;

import static business.blap.NFCL0860.constant.NFCL0860Constant.APPLY_RTNCD_CASHAPP_ERROR;
import static business.blap.NFCL0860.constant.NFCL0860Constant.APPLY_RTNCD_EXCLUSION_ERROR;
import static business.blap.NFCL0860.constant.NFCL0860Constant.APPLY_RTNCD_NORMAL;
import static business.blap.NFCL0860.constant.NFCL0860Constant.APPLY_RTNCD_OTHERS_ERROR;
import static business.blap.NFCL0860.constant.NFCL0860Constant.APPLY_RTNCD_UN_PROCCES;
import static business.blap.NFCL0860.constant.NFCL0860Constant.BIZ_ID;
import static business.blap.NFCL0860.constant.NFCL0860Constant.DETAILS;
import static business.blap.NFCL0860.constant.NFCL0860Constant.ERRMSG_DETAIL;
import static business.blap.NFCL0860.constant.NFCL0860Constant.HEADER;
import static business.blap.NFCL0860.constant.NFCL0860Constant.VIEW_SCALE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;

import business.blap.NFCL0860.common.NFCL0860CommonLogic;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_CASH_APPTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_TRX_BALTMsg;
import business.parts.NFZC301001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC301001.NFZC301001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NFCL0860BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Fujitsu         S.Fujita        Create          N/A
 * 2016/11/30   Fujitsu         T.Murai         Update          QC#15823
 * 2019/09/03   Fujitsu         H.Ikeda         Update          QC#53152
 * 2020/01/28   Fujitsu         H.Ikeda         Update          QC#55633
 * 2022/02/07   CITS            D.Mamaril       Update          QC#56575
 * 2022/04/22   CITS            K.Suzuki        Update          QC#59333
 *</pre>
 */
public class NFCL0860BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFCL0860CMsg bizMsg = (NFCL0860CMsg) cMsg;
            NFCL0860SMsg glblMsg = (NFCL0860SMsg) sMsg;
            cMsg.setCommitSMsg(true);

            if ("NFCL0860Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);

            } else if ("NFCL0860Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);

            } else if ("NFCL0860Scrn00_Unapply".equals(screenAplID)) {
                doProcess_NFCL0860Scrn00_Unapply(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void doProcess_NFCL0860Scrn00_Unapply(NFCL0860CMsg bizMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        String userId = getContextUserInfo().getUserId();
        String btPrDt = ZYPDateUtil.getBatProcDate(bizMsg.glblCmpyCd.getValue());

        if (checkSelectCheck(bizMsg)) {
            if (isArTrxBal(bizMsg)) {
                submitCancel(bizMsg, userId, btPrDt);
            } else {
                return;
            }
        } else {
            // Please select @.
            bizMsg.setMessageInfo("NFDM0002E", new String[] {"line(s)"});
        }
    }
    /**
     * The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860SMsg
     * @param bizMsgCount int
     * @return boolean
     */

    private boolean checkSelectCheck(NFCL0860CMsg bizMsg) {

        boolean result = false;
        boolean countPgflg2 = false;
        boolean checkbox = false;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            NFCL0860_ACMsg aCMsg = bizMsg.A.no(i);
            if (aCMsg.xxNum_A.getValue().compareTo(BigDecimal.valueOf(2)) == 0) {
                countPgflg2 = true;
            }
            // If the check box is selected
            if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A.getValue())) {
                checkbox = true;
            }
        }
        if (countPgflg2 != true) {
            result = true;
        } else {
            result = false;
        }
        if (checkbox == true) {
            result = true;
        }
        return result;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @param userId String
     * @param btPrDt String
     * @param unLockFlg boolean.
     */
    private void submitCancel(NFCL0860CMsg bizMsg, String userId, String btPrDt) {

        // START 2022/02/07 D.Mamaril [QC#56575, ADD]
        List<Integer> toCancel = new ArrayList<Integer>();
        // END 2022/02/07 D.Mamaril [QC#56575, ADD]

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (!bizMsg.A.no(i).xxChkBox_A.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                continue;
            }
            if (isCancelDetail(bizMsg, bizMsg.A.no(i))) {
                // START 2022/02/07 D.Mamaril [QC#56575, MOD]
                // START 2020/01/28 H.Ikeda [QC#55633, MOD]
                // callCanselApi(bizMsg, bizMsg.A.no(i), userId, btPrDt, i);
                // END   2020/01/28 H.Ikeda [QC#55633, MOD]
                //if ("E".equals(bizMsg.getMessageKind())) {
                //    return;
                //}
                toCancel.add(i);
                // END 2022/02/07 D.Mamaril [QC#56575, MOD]
            } else {
                return;
            }
        }

        // START 2022/02/07 D.Mamaril [QC#56575, ADD]
        for (int i : toCancel) {
            callCanselApi(bizMsg, bizMsg.A.no(i), userId, btPrDt, i);
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }
        }
        // END 2022/02/07 D.Mamaril [QC#56575, ADD]

        // START 2019/09/03 H.Ikeda [QC#53152, ADD]
        updateArTrxBalArAutoPurgeOfsFlg (bizMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
        // END   2019/09/03 H.Ikeda [QC#53152, ADD]

        // NFZC202001 -batch(Header)
        if (!NFCL0860CommonLogic.callCreditProfileUpdateApiHeader(bizMsg, btPrDt)) {
            return;
        }
        // NFZC202001 -batch(Detail)
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (!bizMsg.A.no(i).xxChkBox_A.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                continue;
            }

            String billToCustHeader = bizMsg.billToCustCd_AB.getValue();
            String billToCustDetail = bizMsg.A.no(i).billToCustCd_B1.getValue();
            if (!billToCustHeader.equals(billToCustDetail)) {
                if (!NFCL0860CommonLogic.callCreditProfileUpdateApiDetail(bizMsg, bizMsg.A.no(i), btPrDt)) {
                    return;
                }
            }
        }
        bizMsg.setMessageInfo("NZZM0002I", null);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @param userId String
     * @param btPrDt String
     * @param unLockFlg boolean.
     * @param cnt       int
     */
    // START 2020/01/28 H.Ikeda [QC#55633, MOD]
    private void callCanselApi(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA, String userId, String btPrDt ,int cnt) {
    // END   2020/01/28 H.Ikeda [QC#55633, MOD]
        // SET APPLY_GRP_KEY
        String aGrKeyDtl = userId.concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));

        // START 2022/04/22 K.Suzuki [QC#59333,ADD]
        if (ZYPCommonFunc.hasValue(bizMsgA.arAdjTpCd_A) && AR_ADJ_TP.OPEN_A_OR_R_REFUND.equals(bizMsgA.arAdjTpCd_A.getValue())) {
            // AR_APPLY_INTFC_WRK -create(Credit Memo Refund Reversal)
            createArApplyIntfcWrkForCreditMemoReversal(bizMsg, bizMsgA, userId, aGrKeyDtl);

        } else {
            // END   2022/04/22 K.Suzuki [QC#59333,ADD]
            // AR_APPLY_INTFC_WRK -create(Detail)
            createArApplyIntfcWrkForCancelDetail(bizMsg, bizMsgA, userId, aGrKeyDtl);
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }
            // AR_APPLY_INTFC_WRK -create(Header)
            // START 2020/01/28 H.Ikeda [QC#55633, MOD]
            createArApplyIntfcWrkForCancelHeader(bizMsg, bizMsgA, userId, aGrKeyDtl, cnt);
            // END   2020/01/28 H.Ikeda [QC#55633, MOD]
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }
            // START 2022/04/22 K.Suzuki [QC#59333,ADD]
        }
        // END   2022/04/22 K.Suzuki [QC#59333,ADD]
        // NFZC301001 -batch
        doApplyBatchAPI(bizMsg, aGrKeyDtl, btPrDt, DETAILS);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
        // AR_RCPT -update
        updateArRcpt(bizMsg, bizMsgA);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
        // AR_TRX_BAL -update
        updateArTrxBal(bizMsg, bizMsgA, btPrDt);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
        // AR_APPLY_INTFC_WRK -create(Dummy)
        String aGrKey = userId.concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        createArApplyIntfcWrkForCancelDummy(bizMsg, bizMsgA, userId, aGrKey);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
        // AR_CASH_APP -update(Header,Detail)
        updateArCashApp(bizMsg, bizMsgA);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
        // NFZC301001 -batch(Dummy)
        doApplyBatchAPI(bizMsg, aGrKey, btPrDt, HEADER);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
    }

    // START 2019/09/03 H.Ikeda [QC#53152, ADD]
    /**
     * update ArTrxBal.ArAutoPurgeOfsFlg
     * @param bizMsg
     */
    private void updateArTrxBalArAutoPurgeOfsFlg (NFCL0860CMsg bizMsg){
        AR_TRX_BALTMsg  outArTrxBalMsg = null;
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        // AR_TRX_BAL(ArAutoPurgeOfsFlg) -update
        outArTrxBalMsg = NFCL0860CommonLogic.updateArTrxBalArAutoPurgeOfsFlg(glblCmpyCd, bizMsg.arTrxBalPk_AB.getValue());
        if (outArTrxBalMsg == null || !RTNCD_NORMAL.equals(outArTrxBalMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0794E", new String[] {ERRMSG_DETAIL});
            return;
        }
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (!bizMsg.A.no(i).xxChkBox_A.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                continue;
            }
            // AR_TRX_BAL(ArAutoPurgeOfsFlg) -update
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).arTrxTpCd_B1) && AR_TRX_TP.CREDIT_MEMO.equals(bizMsg.A.no(i).arTrxTpCd_B1.getValue())) {
                outArTrxBalMsg = NFCL0860CommonLogic.updateArTrxBalArAutoPurgeOfsFlg(glblCmpyCd, bizMsg.A.no(i).arTrxBalPk_B1.getValue());
                if (outArTrxBalMsg == null || !RTNCD_NORMAL.equals(outArTrxBalMsg.getReturnCode())) {
                    bizMsg.setMessageInfo("NFCM0794E", new String[] {ERRMSG_DETAIL});
                    return;
                }
            }
        }
    }
    // END   2019/09/03 H.Ikeda [QC#53152, ADD]
 
    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @param bizMsgA NFCL0860_ACMsg
     * @param usrId String
     * @param aGrKey String
     * @return void
     */
    private void createArApplyIntfcWrkForCancelDetail(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA, String usrId, String aGrKey) {

        AR_APPLY_INTFC_WRKTMsg inArApplyIntfcWrkMsg = null;
        // AR_APPLY_INTFC_WRK -CreateDetail
        BigDecimal applyAmt = null;
        inArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();

        applyAmt = NFCL0860CommonLogic.convertZero(bizMsgA.dealApplyAmt_A.getValue(), VIEW_SCALE);

        // START 2022/02/07 D.Mamaril [QC#56575, ADD]
        AR_TRX_BALTMsg outArTrxBalMsg = NFCL0860CommonLogic.findArTrxBalInfo(bizMsg, bizMsgA.arTrxBalPk_B1.getValue());
        // END 2022/02/07 D.Mamaril [QC#56575, ADD]

        inArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArApplyIntfcWrkMsg.applyGrpKey.setValue(aGrKey);
        inArApplyIntfcWrkMsg.applyGrpSubPk.setValue(1);
        inArApplyIntfcWrkMsg.bizAppId.setValue(BIZ_ID);
        inArApplyIntfcWrkMsg.upldCsvRqstPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.procTpCd.setValue("3");
        inArApplyIntfcWrkMsg.dealSqNum.setValue("00000001");
        inArApplyIntfcWrkMsg.dealSqDtlNum.setValue("0001");
        inArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
        inArApplyIntfcWrkMsg.usrId.setValue(usrId);
        inArApplyIntfcWrkMsg.rcptNum.setValue(bizMsgA.rcptNum_R1.getValue());
        inArApplyIntfcWrkMsg.rcptInProcSqPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.rcptGlDt.setValue(bizMsgA.glDt_B2.getValue());
        inArApplyIntfcWrkMsg.payerCustCd.setValue(bizMsgA.billToCustAcctCd_B1.getValue());
        inArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(bizMsgA.arTrxBalPk_B2.getValue());
        inArApplyIntfcWrkMsg.rcptHdrLastUpdTs.setValue(bizMsgA.ezUpTime_R1.getValue());
        inArApplyIntfcWrkMsg.rcptHdrTz.setValue(bizMsgA.ezUpTimeZone_R1.getValue());
        inArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(bizMsgA.ezUpTime_B2.getValue());
        inArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(bizMsgA.ezUpTimeZone_B2.getValue());
        inArApplyIntfcWrkMsg.grpInvFlg.setValue(ZYPConstant.FLG_OFF_N);
        inArApplyIntfcWrkMsg.invNum.setValue(bizMsgA.arTrxNum_B1.getValue());
        inArApplyIntfcWrkMsg.arTrxTpCd.setValue(bizMsgA.arTrxTpCd_B1.getValue());
        inArApplyIntfcWrkMsg.invTrxBalPk.setValue(bizMsgA.arTrxBalPk_B1.getValue());
        // START 2022/02/07 D.Mamaril [QC#56575, MOD]
        //inArApplyIntfcWrkMsg.invTrxBalLastUpdTs.setValue(bizMsgA.ezUpTime_B1.getValue());
        //inArApplyIntfcWrkMsg.invTrxBalTz.setValue(bizMsgA.ezUpTimeZone_B1.getValue());
        inArApplyIntfcWrkMsg.invTrxBalLastUpdTs.setValue(outArTrxBalMsg.ezUpTime.getValue());
        inArApplyIntfcWrkMsg.invTrxBalTz.setValue(outArTrxBalMsg.ezUpTimeZone.getValue());
        // END 2022/02/07 D.Mamaril [QC#56575, MOD]
        inArApplyIntfcWrkMsg.crTrxBalPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealCcyCd.setValue(bizMsgA.dealCcyCd_B1.getValue());
        inArApplyIntfcWrkMsg.dealApplyAmt.setValue(applyAmt.multiply(new BigDecimal(-1)));
        inArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsgA.acctDt_AD.getValue());
        inArApplyIntfcWrkMsg.cashDiscPct.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealOnAcctCashAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);

        AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkMsg = NFCL0860CommonLogic.createArApplyIntfcWrk(inArApplyIntfcWrkMsg);

        if (!RTNCD_NORMAL.equals(outArApplyIntfcWrkMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E", null);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @param bizMsgA NFCL0860_ACMsg
     * @param usrId String
     * @param aGrKey String
     * @param cnt    int
     * @return void
     */
    // START 2020/01/28 H.Ikeda [QC#55633, MOD]
    private void createArApplyIntfcWrkForCancelHeader(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA, String usrId, String aGrKey, int cnt) {
    // END   2020/01/28 H.Ikeda [QC#55633, MOD]
        AR_APPLY_INTFC_WRKTMsg inArApplyIntfcWrkMsg = null;
        // AR_APPLY_INTFC_WRK -CreateHeader
        BigDecimal applyAmt = null;
        inArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();

        applyAmt = NFCL0860CommonLogic.convertZero(bizMsgA.dealApplyAmt_A.getValue(), VIEW_SCALE);

        inArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArApplyIntfcWrkMsg.applyGrpKey.setValue(aGrKey);
        inArApplyIntfcWrkMsg.applyGrpSubPk.setValue(2);
        inArApplyIntfcWrkMsg.bizAppId.setValue(BIZ_ID);
        inArApplyIntfcWrkMsg.upldCsvRqstPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.procTpCd.setValue("3");
        inArApplyIntfcWrkMsg.dealSqNum.setValue("00000001");
        inArApplyIntfcWrkMsg.dealSqDtlNum.setValue("0002");
        inArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
        inArApplyIntfcWrkMsg.usrId.setValue(usrId);
        inArApplyIntfcWrkMsg.rcptNum.setValue(bizMsgA.rcptNum_R1.getValue());
        inArApplyIntfcWrkMsg.rcptInProcSqPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.rcptGlDt.setValue(bizMsgA.glDt_B2.getValue());
        inArApplyIntfcWrkMsg.payerCustCd.setValue(bizMsg.billToCustAcctCd_AB.getValue());
        inArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(bizMsgA.arTrxBalPk_B2.getValue());
        inArApplyIntfcWrkMsg.rcptHdrLastUpdTs.setValue(bizMsgA.ezUpTime_R1.getValue());
        inArApplyIntfcWrkMsg.rcptHdrTz.setValue(bizMsgA.ezUpTimeZone_R1.getValue());
        inArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(bizMsgA.ezUpTime_B2.getValue());
        inArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(bizMsgA.ezUpTimeZone_B2.getValue());
        inArApplyIntfcWrkMsg.grpInvFlg.setValue(ZYPConstant.FLG_OFF_N);
        inArApplyIntfcWrkMsg.invNum.setValue(bizMsg.arTrxNum_AB.getValue());
        inArApplyIntfcWrkMsg.arTrxTpCd.setValue(bizMsg.arTrxTpCd_AB.getValue());
        inArApplyIntfcWrkMsg.invTrxBalPk.setValue(bizMsg.arTrxBalPk_AB.getValue());
        // START 2020/01/28 H.Ikeda [QC#55633, MOD]
        if (cnt > 0) {
            AR_TRX_BALTMsg tMsg = NFCL0860CommonLogic.getArTrxBalInfo(bizMsg.glblCmpyCd.getValue(), bizMsg.arTrxBalPk_AB.getValue());
            if (tMsg != null) {
                inArApplyIntfcWrkMsg.invTrxBalLastUpdTs.setValue(tMsg.ezUpTime.getValue());
                inArApplyIntfcWrkMsg.invTrxBalTz.setValue(tMsg.ezUpTimeZone.getValue());
            }
        } else {
            inArApplyIntfcWrkMsg.invTrxBalLastUpdTs.setValue(bizMsg.ezUpTime_AB.getValue());
            inArApplyIntfcWrkMsg.invTrxBalTz.setValue(bizMsg.ezUpTimeZone_AB.getValue());
        }
        // END   2020/01/28 H.Ikeda [QC#55633, MOD]
        inArApplyIntfcWrkMsg.crTrxBalPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealCcyCd.setValue(bizMsg.dealCcyCd_AB.getValue());
        inArApplyIntfcWrkMsg.dealApplyAmt.setValue(applyAmt);
        inArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsgA.acctDt_AD.getValue());
        inArApplyIntfcWrkMsg.cashDiscPct.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealOnAcctCashAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);

        AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkMsg = NFCL0860CommonLogic.createArApplyIntfcWrk(inArApplyIntfcWrkMsg);
        if (!RTNCD_NORMAL.equals(outArApplyIntfcWrkMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E", null);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @param bizMsgA NFCL0860_ACMsg
     * @param usrId String
     * @param aGrKey String
     * @return void
     */
    private void createArApplyIntfcWrkForCancelDummy(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA, String usrId, String aGrKey) {

        AR_APPLY_INTFC_WRKTMsg inArApplyIntfcWrkMsg = null;
        // AR_APPLY_INTFC_WRK -CreateDummy
        inArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();

        // AR_RCPT TimeStamp -Get
        
        AR_RCPTTMsg outArRcptMsg = NFCL0860CommonLogic.findArRcptInfo(bizMsg, bizMsgA.rcptNum_R1.getValue());
        bizMsgA.ezUpTime_R9.setValue(outArRcptMsg.ezUpTime.getValue());
        bizMsgA.ezUpTimeZone_R9.setValue(outArRcptMsg.ezUpTimeZone.getValue());

        // AR_TRX_BAL TimeStamp -Get
        AR_TRX_BALTMsg outArTrxBalMsg = NFCL0860CommonLogic.findArTrxBalInfo(bizMsg, bizMsgA.arTrxBalPk_B2.getValue());
        bizMsgA.ezUpTime_B9.setValue(outArTrxBalMsg.ezUpTime.getValue());
        bizMsgA.ezUpTimeZone_B9.setValue(outArTrxBalMsg.ezUpTimeZone.getValue());

        inArApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArApplyIntfcWrkMsg.applyGrpKey.setValue(aGrKey);
        inArApplyIntfcWrkMsg.applyGrpSubPk.setValue(1);
        inArApplyIntfcWrkMsg.bizAppId.setValue(BIZ_ID);
        inArApplyIntfcWrkMsg.upldCsvRqstPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.procTpCd.setValue("3");
        inArApplyIntfcWrkMsg.dealSqNum.setValue("00000001");
        inArApplyIntfcWrkMsg.dealSqDtlNum.setValue("0001");
        inArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
        inArApplyIntfcWrkMsg.usrId.setValue(usrId);
        inArApplyIntfcWrkMsg.rcptNum.setValue(bizMsgA.rcptNum_R1.getValue());
        inArApplyIntfcWrkMsg.rcptInProcSqPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.rcptTrxBalPk.setValue(bizMsgA.arTrxBalPk_B2.getValue());
        inArApplyIntfcWrkMsg.rcptHdrLastUpdTs.setValue(bizMsgA.ezUpTime_R9.getValue());
        inArApplyIntfcWrkMsg.rcptHdrTz.setValue(bizMsgA.ezUpTimeZone_R9.getValue());
        inArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(bizMsgA.ezUpTime_B9.getValue());
        inArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(bizMsgA.ezUpTimeZone_B9.getValue());
        inArApplyIntfcWrkMsg.grpInvFlg.setValue(ZYPConstant.FLG_OFF_N);
        inArApplyIntfcWrkMsg.invTrxBalPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.crTrxBalPk.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealCcyCd.setValue(bizMsgA.dealCcyCd_B2.getValue());
        inArApplyIntfcWrkMsg.dealApplyAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.cashAppGlDt.setValue(bizMsgA.acctDt_AD.getValue());
        inArApplyIntfcWrkMsg.cashDiscPct.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealCashDiscAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealOnAcctCashAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(BigDecimal.ZERO);
        inArApplyIntfcWrkMsg.dealApplyAdjRsvdAmt.setValue(BigDecimal.ZERO);

        AR_APPLY_INTFC_WRKTMsg outArApplyIntfcWrkMsg = NFCL0860CommonLogic.createArApplyIntfcWrk(inArApplyIntfcWrkMsg);
        if (!RTNCD_NORMAL.equals(outArApplyIntfcWrkMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0032E", null);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @param bizMsgA NFCL0860_ACMsg
     * @return boolean
     */
    private boolean isCancelDetail(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA) {

        if (!isArTrxBalDetail(bizMsg, bizMsgA)) {
            bizMsg.setMessageInfo("NFCM0794E", new String[] {ERRMSG_DETAIL});
            return false;
        }
        if (!isArRcptDummy(bizMsg, bizMsgA)) {
            bizMsg.setMessageInfo("NFCM0794E", new String[] {ERRMSG_DETAIL});
            return false;
        }
        if (!isArTrxBalLineDummy(bizMsg, bizMsgA)) {
            bizMsg.setMessageInfo("NFCM0794E", new String[] {ERRMSG_DETAIL});
            return false;
        }
        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @return boolean
     */
    private boolean isArTrxBal(NFCL0860CMsg bizMsg) {

        boolean retVal = true;
        AR_TRX_BALTMsg outArTrxBalMsg = NFCL0860CommonLogic.findArTrxBalInfo(bizMsg, bizMsg.arTrxBalPk_AB.getValue());

        String errMsg = "";
        errMsg = NFCL0860CommonLogic.getErrMsgName(bizMsg);

        if (outArTrxBalMsg == null) {
            bizMsg.setMessageInfo("NFCM0794E", new String[] {errMsg});
            retVal = false;
        } else {
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_AB.getValue(), bizMsg.ezUpTimeZone_AB.getValue(), outArTrxBalMsg.ezUpTime.getValue(), outArTrxBalMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo("NFCM0794E", new String[] {errMsg});
                retVal = false;
            }
        }
        return retVal;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @param bizMsgA NFCL0860_ACMsg
     * @return boolean
     */
    private boolean isArTrxBalDetail(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA) {

        boolean retVal = true;
        AR_TRX_BALTMsg outArTrxBalMsg = NFCL0860CommonLogic.findArTrxBalInfo(bizMsg, bizMsgA.arTrxBalPk_B1.getValue());

        if (outArTrxBalMsg == null) {
            bizMsg.setMessageInfo("NFCM0794E", new String[] {ERRMSG_DETAIL});
            retVal = false;
        } else {
            if (!ZYPDateUtil.isSameTimeStamp(bizMsgA.ezUpTime_B1.getValue(), bizMsgA.ezUpTimeZone_B1.getValue(), outArTrxBalMsg.ezUpTime.getValue(), outArTrxBalMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo("NFCM0794E", new String[] {ERRMSG_DETAIL});
                retVal = false;
            }
        }
        return retVal;

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @param bizMsgA NFCL0860_ACMsg
     * @return boolean
     */
    private boolean isArRcptDummy(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA) {

        boolean retVal = true;
        AR_RCPTTMsg outArRcptMsg = NFCL0860CommonLogic.findArRcptInfo(bizMsg, bizMsgA.rcptNum_R1.getValue());

        if (outArRcptMsg == null) {
            bizMsg.setMessageInfo("NFCM0794E", new String[] {ERRMSG_DETAIL});
            retVal = false;
        } else {
            if (!ZYPDateUtil.isSameTimeStamp(bizMsgA.ezUpTime_R1.getValue(), bizMsgA.ezUpTimeZone_R1.getValue(), outArRcptMsg.ezUpTime.getValue(), outArRcptMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo("NFCM0794E", new String[] {ERRMSG_DETAIL});
                retVal = false;
            }
        }
        return retVal;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @param bizMsgA NFCL0860_ACMsg
     * @return boolean
     */
    private boolean isArTrxBalLineDummy(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA) {

        boolean retVal = true;
        AR_TRX_BALTMsg outArTrxBalMsg = NFCL0860CommonLogic.findArTrxBalInfo(bizMsg, bizMsgA.arTrxBalPk_B2.getValue());

        if (outArTrxBalMsg == null) {
            bizMsg.setMessageInfo("NFCM0794E", new String[] {ERRMSG_DETAIL});
            retVal = false;
        } else {
            if (!ZYPDateUtil.isSameTimeStamp(bizMsgA.ezUpTime_B2.getValue(), bizMsgA.ezUpTimeZone_B2.getValue(), outArTrxBalMsg.ezUpTime.getValue(), outArTrxBalMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo("NFCM0794E", new String[] {ERRMSG_DETAIL});
                retVal = false;
            }
        }
        return retVal;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @param aGrKey String
     * @param btPrDt String
     * @return boolean
     */
    private boolean doApplyBatchAPI(NFCL0860CMsg bizMsg, String aGrKey, String btPrDt, String paramMsg) {

        boolean retVal = true;

        NFZC301001 api = new NFZC301001();
        NFZC301001PMsg apiMsg = new NFZC301001PMsg();
        apiMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        apiMsg.applyGrpKey.setValue(aGrKey);
        apiMsg.dealSqNum.setValue("00000001");
        apiMsg.batDt.setValue(btPrDt);
        api.execute(apiMsg, S21ApiInterface.ONBATCH_TYPE.ONLINE);
        String result = apiMsg.getReturnCode();
        // result == "1"
        if (APPLY_RTNCD_NORMAL.equals(result)) {
            retVal = true;
        } else {
            // result == "0"
            if (APPLY_RTNCD_UN_PROCCES.equals(result)) {
                bizMsg.setMessageInfo("NFCM0795E", new String[] {paramMsg, "Unprocessing" });
            // result == "2"
            } else if (APPLY_RTNCD_CASHAPP_ERROR.equals(result)) {
                bizMsg.setMessageInfo("NFCM0795E", new String[] {paramMsg, "Cash Application Error" });
            // result == "3"
            } else if (APPLY_RTNCD_EXCLUSION_ERROR.equals(result)) {
                bizMsg.setMessageInfo("NFCM0795E", new String[] {paramMsg, "Exclusion Error" });
            // result == "4"
            } else if (APPLY_RTNCD_OTHERS_ERROR.equals(result)) {
                bizMsg.setMessageInfo("NFCM0795E", new String[] {paramMsg, "Others Error" });
            } else {
                bizMsg.setMessageInfo("NFCM0795E", new String[] {paramMsg, "Others Error" });
            }
            retVal = false;
        }
        return retVal;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @param bizMsgA NFCL0860_ACMsg
     * @return void
     */
    private void updateArRcpt(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA) {

        AR_RCPTTMsg inArRcptMsg = new AR_RCPTTMsg();
        AR_RCPTTMsg outArRcptMsg = NFCL0860CommonLogic.updateArRcptInfo(bizMsg, bizMsgA, inArRcptMsg);

        if (outArRcptMsg == null || !RTNCD_NORMAL.equals(outArRcptMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0794E", new String[] {ERRMSG_DETAIL});
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @param bizMsgA NFCL0860_ACMsg
     * @param btPrDt String
     * @return void
     */
    private void updateArTrxBal(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA, String btPrDt) {

        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        inArTrxBalMsg.cashAppDt.setValue(btPrDt);

        AR_TRX_BALTMsg  outArTrxBalMsg = NFCL0860CommonLogic.updateArTrxBalLInfo(bizMsg, bizMsgA, inArTrxBalMsg);

        if (outArTrxBalMsg == null || !RTNCD_NORMAL.equals(outArTrxBalMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0794E", new String[] {ERRMSG_DETAIL});
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @param bizMsgA NFCL0860_ACMsg
     * @return void
     */
    private void updateArCashApp(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA) {

        AR_CASH_APPTMsg inArCashAppMsg = new AR_CASH_APPTMsg();

        inArCashAppMsg.clear();
        inArCashAppMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArCashAppMsg.arCashAppPk.setValue(bizMsgA.arCashAppPk_A1.getValue());
        inArCashAppMsg.arCashAppSqNum.setValue(bizMsgA.arCashAppSqNum_A1.getValue());
        inArCashAppMsg.ezUpTime.setValue(bizMsgA.ezUpTime_A1.getValue());
        inArCashAppMsg.ezUpTimeZone.setValue(bizMsgA.ezUpTimeZone_A1.getValue());
        inArCashAppMsg.arScrCancFlg.setValue(ZYPConstant.FLG_ON_Y);

        AR_CASH_APPTMsg outArCashAppMsg = NFCL0860CommonLogic.updateArCashAppInfoExclusive(inArCashAppMsg);

        String errMsg = "";
        errMsg = NFCL0860CommonLogic.getErrMsgName(bizMsg);

        if (outArCashAppMsg == null) {
            bizMsg.setMessageInfo("NFCM0794E", new String[] {errMsg});
            return;
        }
        // START 2016/11/30 [QC#15823, ADD]
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outArCashAppMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0615E", new String[] {"AR_CASH_APP"});
            return;
        }
        // END 2016/11/30 [QC#15823, ADD]

        AR_CASH_APPTMsg inTMsg = new AR_CASH_APPTMsg();
        inTMsg.clear();
        inTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inTMsg.arCashAppPk.setValue(bizMsgA.arCashAppPk_A2.getValue());
        inTMsg.arCashAppSqNum.setValue(bizMsgA.arCashAppSqNum_A2.getValue());
        inTMsg.ezUpTime.setValue(bizMsgA.ezUpTime_A2.getValue());
        inTMsg.ezUpTimeZone.setValue(bizMsgA.ezUpTimeZone_A2.getValue());
        inTMsg.arScrCancFlg.setValue(ZYPConstant.FLG_ON_Y);

        AR_CASH_APPTMsg outDetailArCashAppMsg = NFCL0860CommonLogic.updateArCashAppInfoExclusive(inTMsg);

        if (outDetailArCashAppMsg == null) {
            bizMsg.setMessageInfo("NFCM0794E", new String[] {ERRMSG_DETAIL});
            return;
        }
        // START 2016/11/30 [QC#15823, ADD]
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outDetailArCashAppMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0615E", new String[] {"AR_CASH_APP"});
            return;
        }
        // END 2016/11/30 [QC#15823, ADD]
        return;
    }

    // START 2022/04/22 K.Suzuki [QC#59333,ADD]
    /**
     * createArApplyIntfcWrkForCreditMemoReversal
     * 
     * @param bizMsg NFCL0860CMsg
     * @param bizMsgA NFCL0860_ACMsg
     * @param usrId String
     * @param aGrKey String
     * @return void
     */
    private void createArApplyIntfcWrkForCreditMemoReversal(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA, String usrId, String aGrKey) {

        // AR_APPLY_INTFC_WRK Create cancel data
        S21SsmEZDResult s21SsmEZDResult = NFCL0860Query.getInstance().findCancelArApplyIntfcWrk(bizMsg, bizMsgA);
        if (S21SsmEZDResult.RESULT_CODE_NORMAL.equals(s21SsmEZDResult.getResultCode())) {
            Map<String, Object> resMapRcpt = (Map<String, Object>) s21SsmEZDResult.getResultObject();
            if (resMapRcpt == null || resMapRcpt.size() == 0) {
                S21InfoLogOutput.println("createArApplyIntfcWrkForCreditMemoReversal() Err:createArApplyIntfcWrk :");
                bizMsg.setMessageInfo("NFDM0008E", null);
                return;
            }
            AR_APPLY_INTFC_WRKTMsg arApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();
            arApplyIntfcWrkMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            arApplyIntfcWrkMsg.applyGrpKey.setValue((String) resMapRcpt.get("APPLY_GRP_KEY"));
            arApplyIntfcWrkMsg.applyGrpSubPk.setValue((BigDecimal) resMapRcpt.get("APPLY_GRP_SUB_PK"));

            AR_APPLY_INTFC_WRKTMsg copyArApplyIntfcWrkMsg = (AR_APPLY_INTFC_WRKTMsg) S21FastTBLAccessor.findByKey(arApplyIntfcWrkMsg);
            AR_APPLY_INTFC_WRKTMsg insertArApplyIntfcWrkMsg = new AR_APPLY_INTFC_WRKTMsg();
            EZDMsg.copy(copyArApplyIntfcWrkMsg, null, insertArApplyIntfcWrkMsg, null);

            insertArApplyIntfcWrkMsg.applyGrpKey.setValue(aGrKey);
            insertArApplyIntfcWrkMsg.procTpCd.setValue("3");
            insertArApplyIntfcWrkMsg.procStsCd.setValue(PROC_STS.IN_COMPLETED);
            //insertArApplyIntfcWrkMsg.arTrxTpCd.setValue(AR_TRX_TP.ON_ACCOUNT);
            insertArApplyIntfcWrkMsg.rcptHdrLastUpdTs.setValue(bizMsgA.ezUpTime_R1.getValue());
            insertArApplyIntfcWrkMsg.rcptHdrTz.setValue(bizMsgA.ezUpTimeZone_R1.getValue());
            insertArApplyIntfcWrkMsg.rcptTrxBalLastUpdTs.setValue(bizMsgA.ezUpTime_B1.getValue());
            insertArApplyIntfcWrkMsg.rcptTrxBalTz.setValue(bizMsgA.ezUpTimeZone_B1.getValue());
            insertArApplyIntfcWrkMsg.invTrxBalLastUpdTs.setValue(bizMsgA.ezUpTime_B1.getValue());
            insertArApplyIntfcWrkMsg.invTrxBalTz.setValue(bizMsgA.ezUpTimeZone_B1.getValue());

            insertArApplyIntfcWrkMsg.dealApplyAmt.setValue(insertArApplyIntfcWrkMsg.dealApplyAmt.getValue().negate());
            insertArApplyIntfcWrkMsg.dealApplyAdjAmt.setValue(insertArApplyIntfcWrkMsg.dealApplyAdjAmt.getValue().negate());

            EZDTBLAccessor.create(insertArApplyIntfcWrkMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertArApplyIntfcWrkMsg.getReturnCode())) {
                S21InfoLogOutput.println("createArApplyIntfcWrkForCreditMemoReversal() Err:createArApplyIntfcWrk :" + insertArApplyIntfcWrkMsg.toString());
                bizMsg.setMessageInfo("NFDM0008E", null);
                return;
            }
        } else {
            bizMsg.setMessageInfo("ZZZM9001E");
            return;
        }
    }
    // END   2022/04/22 K.Suzuki [QC#59333,ADD]
}
