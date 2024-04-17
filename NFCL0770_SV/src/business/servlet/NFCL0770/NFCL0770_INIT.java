/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0770;

import static business.servlet.NFCL0770.constant.NFCL0770Constant.AR_CANCEL_LIMIT_MONTH;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.SCRN_MODE_CANCEL;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.SCRN_STATUS_N;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0770.NFCL0770CMsg;
import business.servlet.NFCL0770.common.NFCL0770CommonLogic;
import business.servlet.NFCL0770.constant.NFCL0770Constant.DATE_INFO;

import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Fujitsu         S.Takami        Create          QC#28289
 * 2022/09/02   Hitachi         A.Kohinata      Update          QC#60403
 *</pre>
 */
public class NFCL0770_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFCL0770");
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0770CMsg bizMsg = null;

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        NFCL0770CommonLogic.controlTableBegin_NFCL0770_A(this, scrnMsg);

        bizMsg = NFCL0770CommonLogic.setRequestData_NFCL0770_INIT(scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        if (null != bizMsg) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NFCL0770CommonLogic.initialize(this, scrnMsg);

        // Details position Initialize
        scrnMsg.xxListNum_LX.setValue(0);
        scrnMsg.xxListNum_LY.setValue(0);

        NFCL0770CommonLogic.commonBtnControl_NFCL0770_INIT(this, scrnMsg);

        NFCL0770CommonLogic.busBtnControl_NFCL0770_INIT(this, scrnMsg);

        if (null != bizMsg) {
            if ("E".equals(bizMsg.getMessageKind()) && SCRN_STATUS_N.equals(scrnMsg.xxRsltStsCd_H1.getValue())) {
                NFCL0770CommonLogic.setErrorScreen_NFCL0770(this, scrnMsg);

            } else {

                if (SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
                    // get GLdate
                    String cashAppGlDtYm = scrnMsg.cashAppDt_BK.getValue().substring(DATE_INFO.YEAR_START_POS.getValue(), DATE_INFO.MONTH_END_POS.getValue());
                    String acctYmOrg = scrnMsg.acctYrMth.getValue();

                    BigDecimal limitMonthsBigDecimal = ZYPCodeDataUtil.getNumConstValue(AR_CANCEL_LIMIT_MONTH, bizMsg.glblCmpyCd_H1.getValue());

                    int limitMonths = limitMonthsBigDecimal.intValue();

                    String acctYm = NFCL0770CommonLogic.getLimitGLDate(acctYmOrg, limitMonths);
                    if (acctYm != null && acctYm.length() > DATE_INFO.MONTH_END_POS.getValue()) {
                        acctYm = acctYm.substring(DATE_INFO.YEAR_START_POS.getValue(), DATE_INFO.MONTH_END_POS.getValue());
                    }

                    if (null != acctYm && acctYm.compareTo(cashAppGlDtYm) > 0) {
                        NFCL0770CommonLogic.setCancelScreen_NFCL0770Error(this, scrnMsg);
                    } else {
                        NFCL0770CommonLogic.setCancelScreen_NFCL0770(this, scrnMsg);
                    }
                    scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox);

                } else {
                    NFCL0770CommonLogic.setEntryScreen_NFCL0770(this, scrnMsg);
                }

                setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 1);
            }

            NFCL0770CommonLogic.setRowBg(scrnMsg);

        } else {
            NFCL0770CommonLogic.setErrorScreen_NFCL0770(this, scrnMsg);
        }
        NFCL0770CommonLogic.setCheckAllBtn(this, scrnMsg);

        if (!SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
            NFCL0770CommonLogic.protectAddDetailLine(scrnMsg, this);
        } else {
            if ("E".equals(bizMsg.getMessageKind())) {
                this.setButtonEnabled("Check_All", false);
                this.setButtonEnabled("Un_Check_All", false);
            }
        }
        if (SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
            NFCL0770CommonLogic.protectCancelSubmit(scrnMsg, this);
        }

        scrnMsg.putErrorScreen();
        NFCL0770CommonLogic.protectModeOne(scrnMsg, this);
        NFCL0770CommonLogic.setAppFracDigit(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.payerCustCd);
        // add start 2022/09/02 QC#60403
        scrnMsg.xxCellIdx_H1.setValue(0);
        // add end 2022/09/02 QC#60403
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;
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
        // add start 2022/09/02 QC#60403
        scrnMsg.arCustRefNum_H1.setNameForMessage("Customer REF Number");
        // add end 2022/09/02 QC#60403

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
            scrnMsg.A.no(i).dealRmngBalGrsAmt.setNameForMessage("Invoice Amount");
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setNameForMessage("Applied Amount");
            scrnMsg.A.no(i).xxDtlDiffAmt_A1.setNameForMessage("Balance Remaining");
            scrnMsg.A.no(i).cashAppGlDt_A1.setNameForMessage("GL Date");
            scrnMsg.A.no(i).dealCashDiscAmt_A1.setNameForMessage("Cash Discount");
            scrnMsg.A.no(i).cashDiscPct_A1.setNameForMessage("Percent");
            scrnMsg.A.no(i).arAdjTpCd_A1.setNameForMessage("ADJ Type");
            scrnMsg.A.no(i).xxDealApplyAdjAmtNum_A1.setNameForMessage("ADJ AMT");
        }
    }
}
