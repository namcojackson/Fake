/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2760;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;


import business.blap.NFCL2760.NFCL2760CMsg;
import business.servlet.NFCL2760.NFCL2760BMsg;
import business.servlet.NFCL2760.common.NFCL2760CommonLogic;
import business.servlet.NFCL2760.constant.NFCL2760Constant;

import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/07/27   Hitachi         K.Kojima        Update          QC#6461
 * 2016/11/21   Fujitsu         H.Ikeda         Update          QC#12865
 * 2018/08/24   CITS            K.Kameoka       Update          QC#25955
 * 2020/01/20   Fujitsu         H.Ikeda         Update          QC#54902
 *</pre>
 */
public class NFCL2760_INIT extends S21INITCommonHandler implements NFCL2760Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Start 2016/11/21 H.Ikeda [QC#12865,MOD]
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFCL2760");
        // End   2016/11/21 H.Ikeda [QC#12865,MOD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760CMsg bizMsg = null;

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();

        if (null == params || 2 != params.length) {
            scrnMsg.setMessageInfo("NFCM0031E");
            return null;
        } else {
            // do nothing
        }

        NFCL2760CommonLogic.otherBusConnectFrom_NFCL2760_INIT(params, scrnMsg);

        NFCL2760CommonLogic.controlTableBegin_NFCL2760_A(this, scrnMsg);

        bizMsg = NFCL2760CommonLogic.setRequestData_NFCL2760_INIT(scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        if (null != bizMsg) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NFCL2760CommonLogic.initialize(this, scrnMsg);

        // Details position Initialize
        scrnMsg.xxListNum_LX.setValue(0);
        scrnMsg.xxListNum_LY.setValue(0);

        NFCL2760CommonLogic.commonBtnControl_NFCL2760_INIT(this, scrnMsg);

        NFCL2760CommonLogic.busBtnControl_NFCL2760_INIT(this, scrnMsg);

        if (null != bizMsg) {
            if ("E".equals(bizMsg.getMessageKind()) && SCRN_STATUS_N.equals(scrnMsg.xxRsltStsCd_H1.getValue())) {
                NFCL2760CommonLogic.setErrorScreen_NFCL2760(this, scrnMsg);

            } else {

                if (SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
                    // get GLdate
                    String cashAppGlDtYm = scrnMsg.cashAppDt_BK.getValue().substring(DATE_INFO.YEAR_START_POS.getValue(), DATE_INFO.MONTH_END_POS.getValue());
                    String acctYmOrg = scrnMsg.acctYrMth.getValue();

                    BigDecimal limitMonthsBigDecimal = ZYPCodeDataUtil.getNumConstValue(AR_CANCEL_LIMIT_MONTH, bizMsg.glblCmpyCd_H1.getValue());

                    int limitMonths = limitMonthsBigDecimal.intValue();

                    String acctYm = NFCL2760CommonLogic.getLimitGLDate(acctYmOrg, limitMonths);
                    if (acctYm != null && acctYm.length() > DATE_INFO.MONTH_END_POS.getValue()) {
                        acctYm = acctYm.substring(DATE_INFO.YEAR_START_POS.getValue(), DATE_INFO.MONTH_END_POS.getValue());
                    } else {
                        // do nothing
                    }

                    if (null != acctYm && acctYm.compareTo(cashAppGlDtYm) > 0) {
                        NFCL2760CommonLogic.setCancelScreen_NFCL2760Error(this, scrnMsg);
                    } else {
                        NFCL2760CommonLogic.setCancelScreen_NFCL2760(this, scrnMsg);
                    }
                    scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox);

                } else {
                    NFCL2760CommonLogic.setEntryScreen_NFCL2760(this, scrnMsg);
                }

                setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 1);
            }

            NFCL2760CommonLogic.setRowBg(scrnMsg);

        } else {
            NFCL2760CommonLogic.setErrorScreen_NFCL2760(this, scrnMsg);
        }
        NFCL2760CommonLogic.setCheckAllBtn(this, scrnMsg);

        if (!SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
            NFCL2760CommonLogic.protectAddDetailLine(scrnMsg, this);
        } else {
            if ("E".equals(bizMsg.getMessageKind())) {
                this.setButtonEnabled("Check_All", false);
                this.setButtonEnabled("Un_Check_All", false);
            }
        }
        if (SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
            NFCL2760CommonLogic.protectCancelSubmit(scrnMsg, this);
        }

        scrnMsg.putErrorScreen();
        NFCL2760CommonLogic.protectModeOne(scrnMsg, this);
        NFCL2760CommonLogic.setAppFracDigit(scrnMsg);
        
        //QC#25955 Add Start
        scrnMsg.setFocusItem(scrnMsg.A.no(0).arCustRefNum);
        //QC#25955 Add End
        // Start 2020/01/20 H.Ikeda [QC#54902, ADD]
        scrnMsg.xxCellIdx_H1.setValue(0);
        // End   2020/01/20 H.Ikeda [QC#54902, ADD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());

        scrnMsg.xxModeNm23Txt.setNameForMessage("Mode");
        scrnMsg.rcptNum.setNameForMessage("Receipt Number");
        scrnMsg.rcptDt.setNameForMessage("Receipt Date");
        scrnMsg.glDt_H1.setNameForMessage("GL Date");
        scrnMsg.arRcptTrxTpCd.setNameForMessage("Receipt Transaction Type");
        scrnMsg.arRcptTrxTpNm.setNameForMessage("Receipt Transaction Type");
        scrnMsg.arRcptTpCd.setNameForMessage("Receipt Type");
        scrnMsg.arRcptTpNm.setNameForMessage("Receipt Type");
        scrnMsg.rcptBatNum.setNameForMessage("Receipt Batch");
        scrnMsg.payerCustCd.setNameForMessage("Bill to/Alt Payer");
        scrnMsg.dsAcctNm.setNameForMessage("Payer");
        scrnMsg.ccyCd.setNameForMessage("Currency");

        scrnMsg.dealRcptAmt.setNameForMessage("Receipt");
        scrnMsg.dealApplyAmt_H1.setNameForMessage("Applied Amt");
        scrnMsg.dealApplyAdjAmt_H1.setNameForMessage("Adjusted");
        scrnMsg.dealRcptRmngBalAmt.setNameForMessage("Rcpt Bal");
        scrnMsg.dealRfAmt.setNameForMessage("Refund");
        scrnMsg.applyAmt_3.setNameForMessage("Debit");
        scrnMsg.applyAmt_2.setNameForMessage("Credit");
        scrnMsg.dealCashDiscAmt.setNameForMessage("Cash Disc");
        scrnMsg.trxApplyAdjAmt.setNameForMessage("Trx Adj");
        scrnMsg.trxApplyGrsAmt.setNameForMessage("Trx Apply Gross");
        scrnMsg.applyAmt_1.setNameForMessage("Rcpt Adj");
        scrnMsg.xxOnAcctAmt.setNameForMessage("On Acc");
        scrnMsg.xxDedctAmt.setNameForMessage("Deduction");
        scrnMsg.xxApplyGrsAmt.setNameForMessage("Apply Total");
        scrnMsg.xxBalApplyGrsAmt.setNameForMessage("Rcpt Bal-Apply Gross");

        for (int i = 0; i < scrnMsg.A.length(); i++) {

            scrnMsg.A.no(i).xxChkBox.setNameForMessage("Delete Check Box");
            scrnMsg.A.no(i).arTrxNum.setNameForMessage("TRX Num");
            scrnMsg.A.no(i).grpInvNum.setNameForMessage("Group Inv Num");
            scrnMsg.A.no(i).arTrxTpCd.setNameForMessage("TRX Type");
            scrnMsg.A.no(i).cpoOrdNum.setNameForMessage("CPO Num");
            scrnMsg.A.no(i).invDueDt.setNameForMessage("Due Date");
            scrnMsg.A.no(i).glDt_A1.setNameForMessage("GL Date");
            scrnMsg.A.no(i).custIssPoNum.setNameForMessage("Cust PO Num");
            scrnMsg.A.no(i).arCustRefNum.setNameForMessage("Customer REF Number");
            scrnMsg.A.no(i).billToCustCd.setNameForMessage("Bill to");
            scrnMsg.A.no(i).dealOrigGrsAmt.setNameForMessage("Gross");
            scrnMsg.A.no(i).dealApplyGrsAmt.setNameForMessage("Applied");
            scrnMsg.A.no(i).dealApplyCrAmt.setNameForMessage("Others");
            scrnMsg.A.no(i).dealNetSlsAmt.setNameForMessage("Sales");
            scrnMsg.A.no(i).dealFrtAmt.setNameForMessage("Freight");
            scrnMsg.A.no(i).dealTaxAmt.setNameForMessage("Tax");
            // START 2016/07/27 K.Kojima [QC#6461,MOD]
            // scrnMsg.A.no(i).dealRmngBalGrsAmt.setNameForMessage("Balance");
            // scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setNameForMessage("Apply");
            // scrnMsg.A.no(i).xxDtlDiffAmt_A1.setNameForMessage("Diff.");
            scrnMsg.A.no(i).dealRmngBalGrsAmt.setNameForMessage("Invoice Amount");
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setNameForMessage("Applied Amount");
            scrnMsg.A.no(i).xxDtlDiffAmt_A1.setNameForMessage("Balance Remaining");
            // END 2016/07/27 K.Kojima [QC#6461,MOD]
            scrnMsg.A.no(i).cashAppGlDt_A1.setNameForMessage("GL Date");
            scrnMsg.A.no(i).dealCashDiscAmt_A1.setNameForMessage("Cash Discount");
            scrnMsg.A.no(i).cashDiscPct_A1.setNameForMessage("Percent");
            scrnMsg.A.no(i).arAdjTpCd_A1.setNameForMessage("ADJ Type");
            scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setNameForMessage("ADJ AMT");

        }

    }
}
