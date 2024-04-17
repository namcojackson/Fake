/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0810;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0810.NSAL0810CMsg;
import static business.servlet.NSAL0810.constant.NSAL0810Constant.*;
import static business.servlet.NSAL0810.common.NSAL0810CommonLogic.*;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Interface Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/05/20   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/05/25   Hitachi         T.Tomita        Update          QC#8898
 * 2016/06/30   Hitachi         T.Iwamoto       Update          QC#10661
 * 2016/09/01   Hitachi         Y.Zhang         Update          QC#11846
 * 2016/09/23   Hitachi         Y.Zhang         Update          QC#12582
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 *</pre>
 */
public class NSAL0810_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;

        NSAL0810CMsg bizMsg = new NSAL0810CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;
        NSAL0810CMsg bizMsg = (NSAL0810CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/09/01 Y.Zhang [QC#11846, MOD]
        // because focus set add,process execute sort change
        scrnMsg.putErrorScreen();
        initialControlScreen(this, scrnMsg);
        // START 2016/09/01 Y.Zhang [QC#11846, MOD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;

        scrnMsg.contrIntfcSrcTpCd_SS.setNameForMessage("Source Type");
        scrnMsg.dsContrIntfcBatNum_S.setNameForMessage("Interface Bat#");
        scrnMsg.dsContrSrcRefNum_S.setNameForMessage("Source Ref#");
        scrnMsg.dsContrIntfcDt_SF.setNameForMessage("Interface Date(From)");
        scrnMsg.dsContrIntfcDt_ST.setNameForMessage("Interface Date(Thru)");
        scrnMsg.xxErrFlg_S.setNameForMessage("Errors Only");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dsContrIntfcBatNum_A.setNameForMessage("Interface Bat#");
            scrnMsg.A.no(i).dsContrSrcRefNum_A.setNameForMessage("Source Ref#");
            scrnMsg.A.no(i).contrIntfcSrcTpCd_AS.setNameForMessage("Source Type");
            scrnMsg.A.no(i).dsContrNum_A.setNameForMessage("Contract#");
            scrnMsg.A.no(i).dsContrIntfcActCd_AS.setNameForMessage("Action");
            scrnMsg.A.no(i).serNum_A.setNameForMessage("Serial#");
            scrnMsg.A.no(i).svcMachMstrPk_A.setNameForMessage("IB ID");
            scrnMsg.A.no(i).contrIntfcDtlTpCd_AS.setNameForMessage("Line Type");
            scrnMsg.A.no(i).dsContrIntfcStsCd_A.setNameForMessage("Proc Flag");
            scrnMsg.A.no(i).intfcErrMsgTxt_A.setNameForMessage("Proc Message");
            scrnMsg.A.no(i).dsContrProcStsCd_AS.setNameForMessage("Proc Status");
            scrnMsg.A.no(i).dsAcctNum_A.setNameForMessage("Cust Acct Num");
            scrnMsg.A.no(i).dsAcctNm_AC.setNameForMessage("Cust Acct Name");
            scrnMsg.A.no(i).billToCustCd_A.setNameForMessage("Bill To Customer Number");
            scrnMsg.A.no(i).leaseCmpyCd_A.setNameForMessage("Lease Company Num");
            scrnMsg.A.no(i).svcContrBrCd_A.setNameForMessage("Contract Branch");
            // mod start 2016/06/30 CSA Defect#10661
            scrnMsg.A.no(i).contrAdminPsnCd_A.setNameForMessage("Contract Rep");
            scrnMsg.A.no(i).tocCd_A.setNameForMessage("Sales Rep");
            // mod start 2016/06/30 CSA Defect#10661
            scrnMsg.A.no(i).custPoNum_A.setNameForMessage("PO#");
            scrnMsg.A.no(i).poDt_A.setNameForMessage("PO Exp Date");
            scrnMsg.A.no(i).crCardCustRefNum_A.setNameForMessage("CC Reference#");
            scrnMsg.A.no(i).crCardExprYrMth_A.setNameForMessage("CC Exp Date");
            scrnMsg.A.no(i).mtrEstTpCd_AS.setNameForMessage("Auto Estimation Code");
            scrnMsg.A.no(i).svcPgmMdseCd_A.setNameForMessage("Service Program");
            // START 2016/09/23 Y.Zhang [QC#12582, MOD]
            scrnMsg.A.no(i).mdseCd_A.setNameForMessage("Item Code");
            // END 2016/09/23 Y.Zhang [QC#12582, MOD]
            scrnMsg.A.no(i).mdlNm_A.setNameForMessage("Model Name");
            scrnMsg.A.no(i).contrFromDt_A.setNameForMessage("Start Date");
            scrnMsg.A.no(i).contrThruDt_A.setNameForMessage("End Date");
            scrnMsg.A.no(i).bllgCycleCd_AS.setNameForMessage("Frequency");
            scrnMsg.A.no(i).prcAllocByMachQtyFlg_A.setNameForMessage("Alloc Across Machines");
            scrnMsg.A.no(i).contrAutoRnwTpCd_AS.setNameForMessage("Renewal Type");
            scrnMsg.A.no(i).rnwPrcMethCd_AS.setNameForMessage("Renewal Method");
            scrnMsg.A.no(i).befEndRnwDaysAot_A.setNameForMessage("# Days Before");
            scrnMsg.A.no(i).rnwPrcUpRatio_A.setNameForMessage("Renewal %");
            scrnMsg.A.no(i).contrUplftTpCd_AS.setNameForMessage("Annual Escalation Type");
            scrnMsg.A.no(i).uplftPrcMethCd_AS.setNameForMessage("Escalation Method");
            scrnMsg.A.no(i).uplftPrcUpRatio_A.setNameForMessage("Escalation Percent");
            scrnMsg.A.no(i).mtrReadMethCd_AS.setNameForMessage("Meter Read Method");
            scrnMsg.A.no(i).basePrcDealAmt_A.setNameForMessage("Price Per Period");
            scrnMsg.A.no(i).contrCloDay_A.setNameForMessage("Period End Date Ctrl");
            scrnMsg.A.no(i).contrBllgDay_A.setNameForMessage("Invoice Date Ctrl");
            scrnMsg.A.no(i).bllgThruDt_A.setNameForMessage("Bill Through Date");
            scrnMsg.A.no(i).bllgMtrLbCd_A.setNameForMessage("Overage Counter Code");
            // START 2016/05/25 T.Tomita [QC#8898, MOD]
//            scrnMsg.A.no(i).bllgMtrLbCd_AS.setNameForMessage("Overage Counter Name");
            scrnMsg.A.no(i).mtrLbDescTxt_A.setNameForMessage("Overage Counter Name");
            // END 2016/05/25 T.Tomita [QC#8898, MOD]
            scrnMsg.A.no(i).startMtrCnt_A.setNameForMessage("Start Reading");
            scrnMsg.A.no(i).bllgRollOverRatio_A.setNameForMessage("Counter Rollover %");
            scrnMsg.A.no(i).dsContrCatgCd_AS.setNameForMessage("Contract Category");
            scrnMsg.A.no(i).dsContrStsCd_AS.setNameForMessage("Contract Status");
            scrnMsg.A.no(i).xsChrgTpCd_AS.setNameForMessage("Excess Charge Type");
            scrnMsg.A.no(i).xsMtrCopyQty_A.setNameForMessage("Excess Meter Copy Qty");
            scrnMsg.A.no(i).xsMtrAmtRate_A.setNameForMessage("Excess Meter Amount Rate");
            scrnMsg.A.no(i).addlChrgTpCd_AS.setNameForMessage("Additional Charge Type");
            scrnMsg.A.no(i).addlChrgFlatDealPrcAmt_A.setNameForMessage("Additional Charge Amount");
            scrnMsg.A.no(i).addlChrgAplcPct_A.setNameForMessage("Additional Charge Applicable");
            scrnMsg.A.no(i).chrgLvlTpCd_AS.setNameForMessage("Charge Level");
            scrnMsg.A.no(i).addlChrgInvTpCd_AS.setNameForMessage("Additional Charge Invoice Type");
            scrnMsg.A.no(i).printDtlFlg_A.setNameForMessage("Print on Invoice");
            scrnMsg.A.no(i).basePrcTermDealAmtRate_A.setNameForMessage("Term Amount Rate");
            scrnMsg.A.no(i).dsContrClsCd_AS.setNameForMessage("Contract Class");
            scrnMsg.A.no(i).ctacPsnPk_A.setNameForMessage("Contact Person ID");
            scrnMsg.A.no(i).ctacPsnNm_A.setNameForMessage("Contact Person Name");
            // START 2022/03/22 [QC#59683, MOD]
//            scrnMsg.A.no(i).invSeptBaseUsgFlg_A.setNameForMessage("Invoice Separate");
            scrnMsg.A.no(i).dsInvTgtrTpCd_AS.setNameForMessage("Invoicing Option");
            // END   2022/03/22 [QC#59683, MOD]
            scrnMsg.A.no(i).contrCloDt_A.setNameForMessage("Contract Close Date");
            scrnMsg.A.no(i).contrDurnAot_A.setNameForMessage("Duration");
            scrnMsg.A.no(i).pmtTermCashDiscCd_A.setNameForMessage("Payment Term Discount");
            scrnMsg.A.no(i).svcLineBizCd_AS.setNameForMessage("Line of Business");
            scrnMsg.A.no(i).bllgTmgTpCd_AS.setNameForMessage("Billing Timing");
            scrnMsg.A.no(i).dsContrEdiCd_A.setNameForMessage("Contract EDI");
            scrnMsg.A.no(i).dsContrDescTxt_A.setNameForMessage("Contract Description");
            scrnMsg.A.no(i).baseChrgToLeaseCmpyFlg_A.setNameForMessage("Base Charge To Lease Company");
            scrnMsg.A.no(i).usgChrgToLeaseCmpyFlg_A.setNameForMessage("Usage Charge To Lease Company");
            // START 2016/09/23 Y.Zhang [QC#12582, MOD]
            scrnMsg.A.no(i).intgMdseCd_A.setNameForMessage("Intangible Item Code");
            // END 2016/09/23 Y.Zhang [QC#12582, MOD]
            scrnMsg.A.no(i).capBwOrigQty_A.setNameForMessage("Cap BW Original Qty");
            scrnMsg.A.no(i).capColorOrigQty_A.setNameForMessage("Cap Color Original Qty");
            scrnMsg.A.no(i).capTotOrigQty_A.setNameForMessage("Cap Total Original Qty");
            scrnMsg.A.no(i).capBwRunQty_A.setNameForMessage("Cap BW Running Qty");
            scrnMsg.A.no(i).capColorRunQty_A.setNameForMessage("Cap Color Running Qty");
            scrnMsg.A.no(i).capTotRunQty_A.setNameForMessage("Cap Total Running Qty");
            // START 2016/05/20 [QC#4061, ADD]
            scrnMsg.A.no(i).manContrOvrdFlg_A.setNameForMessage("Manual Contract Override");
            // END   2016/05/20 [QC#4061, ADD]
        }
    }
}
