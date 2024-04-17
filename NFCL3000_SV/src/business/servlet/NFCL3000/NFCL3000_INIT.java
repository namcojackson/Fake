/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3000.NFCL3000CMsg;
import business.servlet.NFCL3000.common.NFCL3000CommonLogic;
import business.servlet.NFCL3000.constant.NFCL3000Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Fujitsu         T.Tanaka        Create          N/A
 * 2016/06/03   Fujitsu         S.Fujita        Update          QC#9157
 * 2016/07/14   Fujitsu         S.Fujita        Update          QC#11157
 * 2016/08/01   Fujitsu         S.Fujita        Update          QC#10148
 * 2016/09/05   Fujitsu         S.Fujita        Update          QC#13648
 * 2016/10/03   Fujitsu         S.Fujita        Update          QC#14120
 * 2016/10/14   Fujitsu         S.Fujita        Update          QC#10281
 * 2016/10/26   Fujitsu         T.Murai         Update          QC#13639
 * 2017/03/13   Fujitsu         T.Murai         Update          QC#17933
 * 2017/12/25   Hitachi         E.Kameishi      Update          QC#20312
 * 2018/05/22   Fujitsu         Y.Matsui        Update          QC#21841
 * 2018/09/28   Fujitsu         T.Ogura         Update          QC#28526
 * 2019/04/25   Fujitsu         S.Takami        Update          QC#50078
 * 2019/05/10   Fujitsu         S.Takami        Update          QC#50148
 *</pre>
 */
public class NFCL3000_INIT extends S21INITCommonHandler implements NFCL3000Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFCL3000");
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        NFCL3000CMsg bizMsg = new NFCL3000CMsg();

        // START 2016/08/01 S.Fujita [QC#10148,ADD]
        NFCL3000CommonLogic.setDisplayPattern(scrnMsg);
        // END   2016/08/01 S.Fujita [QC#10148,ADD]

        bizMsg.setBusinessID("NFCL3000");
        bizMsg.setFunctionCode("20");

        scrnMsg.appFuncId.setValue(BIZ_ID);

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            if (params.length == 1) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.invNum_H1, param01);
                scrnMsg.appFuncId.setValue("NFCLXXXX");
            }
        }
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        NFCL3000CMsg bizMsg  = (NFCL3000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL3000CommonLogic.initialize(this, scrnMsg, false);

        // Details position Initialize
        scrnMsg.xxListNum_LX.setValue(0);
        scrnMsg.xxListNum_LY.setValue(0);
        scrnMsg.xxListNum_LX.setInputProtected(false);
        scrnMsg.xxListNum_LY.setInputProtected(false);

        // START 2017/12/25 E.Kameishi [QC#20312,ADD]
        NFCL3000CommonLogic.taxAdjustmentProtect(scrnMsg);
        // END   2017/12/25 E.Kameishi [QC#20312,ADD]

        if(scrnMsg.xxRadioBtn_C.getValue().equals(ACCT_DIST_EDIT)) {
            scrnMsg.xxDplyTab.setValue(TAB_Accounting);
            NFCL3000CommonLogic.setProtectAcctInput(this, scrnMsg);
            NFCL3000CommonLogic.addCheckItem_AcctDist(scrnMsg, false);
            scrnMsg.setMessageInfo("NFCM0576E", new String[]{"Accounting Information"});
        } else {
            // Display TAB = Line
            scrnMsg.xxDplyTab.setValue(TAB_Line);
        }
        // START 2019/04/25 S.Takami [QC#50078,ADD]
        NFCL3000CommonLogic.setQtyIndispensable(scrnMsg);
        // END 2019/04/25 S.Takami [QC#50078,ADD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        scrnMsg.invNum_H1.setNameForMessage("Invoice Numer");
        scrnMsg.invTpCd_H1.setNameForMessage("Class");
        scrnMsg.dsInvTpCd_H1.setNameForMessage("Invoice Type");
        scrnMsg.invDt_H1.setNameForMessage("Invoice Date");
        scrnMsg.pmtTermCashDiscCd_H1.setNameForMessage("Payment Term");
        scrnMsg.netDueDt_H1.setNameForMessage("Due Date");
        // START 2016/10/03 S.Fujita [QC#14120,DEL]
//        scrnMsg.dfrdInvRuleCd_H1.setNameForMessage("Invoice Rule");
        // END   2016/10/03 S.Fujita [QC#14120,DEL]
        scrnMsg.srcSysDocNum_H1.setNameForMessage("Source Number");
        scrnMsg.custIssPoNum_H1.setNameForMessage("PO Number");
        // START 2016/07/14 S.Fujita [QC#11157,MOD]
//        scrnMsg.slsRepTocCd_H1.setNameForMessage("Sales Rep");
        scrnMsg.psnNum_H1.setNameForMessage("Sales Rep");
        // END   2016/07/14 S.Fujita [QC#11157,MOD]
        scrnMsg.custCareTktNum_H1.setNameForMessage("CI NUmber");
        scrnMsg.shipToCustAcctCd_H2.setNameForMessage("Ship To Cust Account Number");
        scrnMsg.shipToCustAcctNm_H2.setNameForMessage("Ship To Cust Account Name");
        scrnMsg.locNum_H2.setNameForMessage("Ship To Location");
        scrnMsg.xxPsnNm_H2.setNameForMessage("Contact Person");
        scrnMsg.billToCustAcctCd_H3.setNameForMessage("Bill To Cust Account Number");
        scrnMsg.billToCustAcctNm_H3.setNameForMessage("Bill To Cust Account Name");
        // START 2016/07/28 S.Fujita [QC#10148,DEL]
//        scrnMsg.shipToCustCd_H2.setNameForMessage("Ship To Cust Code");
//        scrnMsg.billToCustCd_H3.setNameForMessage("Bill To Cust Code");
        // END   2016/07/28 S.Fujita [QC#10148,DEL]
        scrnMsg.locNum_H3.setNameForMessage("Bill To Location");
        scrnMsg.xxPsnNm_H3.setNameForMessage("Contact Person");
        scrnMsg.shipFromInvtyLocCd_H4.setNameForMessage("Warehouse");
        scrnMsg.shipDt_H4.setNameForMessage("Ship Date");
        scrnMsg.xxTotAmt_T1.setNameForMessage("Sales Amount");
        scrnMsg.xxTotAmt_T2.setNameForMessage("Freight Amount");
        scrnMsg.xxTotAmt_T3.setNameForMessage("TAX Amount");
        scrnMsg.xxTotAmt_T4.setNameForMessage("Invoice Total Amount");
        scrnMsg.xxTotAmt_T5.setNameForMessage("Balance");
        scrnMsg.xxChkBox_H3.setNameForMessage("Complete");
        scrnMsg.xxChkBox_H4.setNameForMessage("Credit Applied");
        scrnMsg.xxChkBox_H5.setNameForMessage("Cash Applied");
        scrnMsg.xxChkBox_H6.setNameForMessage("Print Eligible");
        // START 2016/08/01 S.Fujita [QC#10148,ADD]
        scrnMsg.acctDt_H1.setNameForMessage("GL Date");
        // END   2016/08/01 S.Fujita [QC#10148,ADD]
        // START 2019/05/10 S.Takami [QC#50148,MOD]
//        // START 2017/03/13 T.Murai  [QC#17933,ADD]
//        scrnMsg.invFirstCmntTxt_E1.setNameForMessage("Comments");
//        // END   2017/03/13 T.Murai  [QC#17933,ADD]
        scrnMsg.xxInvMemoTxt_E1.setNameForMessage("Comments");
        // START 2019/05/10 S.Takami [QC#50148,MOD]

        for(int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).invBolLineNum_A1.setNameForMessage("BOL Line Number");
            scrnMsg.A.no(i).invLineNum_A1.setNameForMessage("Invoice Line Number");
            scrnMsg.A.no(i).mdseCd_A1.setNameForMessage("Item Code");
            scrnMsg.A.no(i).pkgUomCd_A1.setNameForMessage("UOM");
            // START 2018/09/28 T.Ogura [QC#28526,MOD]
//            scrnMsg.A.no(i).shipQty_A1.setNameForMessage("Ship Quantity");
            scrnMsg.A.no(i).ordCustUomQty_A1.setNameForMessage("UOM Quantity");
            // END   2018/09/28 T.Ogura [QC#28526,MOD]
            // START 2019/04/25 S.Takami [QC#50078,ADD]
            scrnMsg.A.no(i).shipQty_A1.setNameForMessage("Ship Quantity");
            scrnMsg.A.no(i).invDplyQty_A1.setNameForMessage("Hours");
            scrnMsg.A.no(i).adjQtyDplyTxt_A1.setNameForMessage("Qty");
            // END 2019/04/25 S.Takami [QC#50078,ADD]
            scrnMsg.A.no(i).dealNetUnitPrcAmt_A1.setNameForMessage("Unit Price Amount");
            scrnMsg.A.no(i).invLineDealTaxAmt_A1.setNameForMessage("Tax Amount");
            scrnMsg.A.no(i).taxPct_A1.setNameForMessage("Tax Percent");
            scrnMsg.A.no(i).cpoOrdNum_A1.setNameForMessage("Order#");
            scrnMsg.A.no(i).ordDt_A1.setNameForMessage("Order Date");
            scrnMsg.A.no(i).unitCostAmt_A1.setNameForMessage("Standard Cost Amount");
            // START 2016/08/01 S.Fujita [QC#10148,ADD]
            // scrnMsg.A.no(i).cpoDtlLineNum_A1.setNameForMessage("Order Line#"); // 2016/11/17 T.Murai [QC#14096, DEL]
            scrnMsg.A.no(i).xxDplyOrdLineNum_A1.setNameForMessage("Order Line#"); // 2016/10/26 T.Murai [QC#13639, ADD]
            scrnMsg.A.no(i).dsContrNum_A1.setNameForMessage("Contract#");
            scrnMsg.A.no(i).dsContrSqNum_A1.setNameForMessage("Contract Line Revision");
            scrnMsg.A.no(i).svcConfigMstrPk_A1.setNameForMessage("Serial#");
            scrnMsg.A.no(i).serNum_A1.setNameForMessage("Serial#"); // 2016/10/26 T.Murai [QC#13639, ADD]
            scrnMsg.A.no(i).mdlNm_A1.setNameForMessage("Model#");
            scrnMsg.A.no(i).svcInvChrgTpCd_A1.setNameForMessage("Billing Type");
            scrnMsg.A.no(i).bllgPerFromDt_A1.setNameForMessage("Bill From Date");
            scrnMsg.A.no(i).bllgPerThruDt_A1.setNameForMessage("Bill To Date");
            scrnMsg.A.no(i).bllgCopyQty_A1.setNameForMessage("No of Copies");
            scrnMsg.A.no(i).ordQty_A1.setNameForMessage("Quantity Ordered");
            scrnMsg.A.no(i).crDrRsnCd_A1.setNameForMessage("Reason Code");
            scrnMsg.A.no(i).dsContrDtlPk_A1.setNameForMessage("Bill Instance#");
            scrnMsg.A.no(i).manInvCratCmntTxt_A1.setNameForMessage("Comments");
            // END   2016/08/01 S.Fujita [QC#10148,ADD]

            // START 2016/10/14 S.Fujita [QC#10281,ADD]
            scrnMsg.A.no(i).firstBllgAttrbValTxt_A1.setNameForMessage("Control1");
            scrnMsg.A.no(i).scdBllgAttrbValTxt_A1.setNameForMessage("Control2");
            scrnMsg.A.no(i).thirdBllgAttrbValTxt_A1.setNameForMessage("Control3");
            scrnMsg.A.no(i).frthBllgAttrbValTxt_A1.setNameForMessage("Control4");
            scrnMsg.A.no(i).fifthBllgAttrbValTxt_A1.setNameForMessage("Control5");
            scrnMsg.A.no(i).sixthBllgAttrbValTxt_A1.setNameForMessage("Control6");
            // END   2016/10/14 S.Fujita [QC#10281,ADD]

            // START 2018/05/22 Y.Matsui [QC#21841,ADD]
            scrnMsg.A.no(i).invLineCatgCd_A1.setNameForMessage("Line Category");
            // END   2018/05/22 Y.Matsui [QC#21841,ADD]
        }

        for(int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).xxChkBox_B1.setNameForMessage("Check Box");
            scrnMsg.B.no(i).invBolLineNum_B1.setNameForMessage("BOL Line Number");
            scrnMsg.B.no(i).invLineNum_B1.setNameForMessage("Invoice Line Number");
            // START 2016/09/05 S.Fujita [QC#13648,DEL]
//            scrnMsg.B.no(i).invLineSplPct_B2.setNameForMessage("Line Split Percent");
            // END   2016/09/05 S.Fujita [QC#13648,DEL]
            // START 2016/07/14 S.Fujita [QC#11157,MOD]
//            scrnMsg.B.no(i).slsRepTocCd_B1.setNameForMessage("Sales Rep");
            scrnMsg.B.no(i).psnNum_B1.setNameForMessage("Sales Rep");
            // END   2016/07/14 S.Fujita [QC#11157,MOD] 
            scrnMsg.B.no(i).slsRepCrPct_B2.setNameForMessage("Percentage Split");
            scrnMsg.B.no(i).slsRepBrCd_B1.setNameForMessage("Sales Rep Branch");
            scrnMsg.B.no(i).dfrdAcctgRuleCd_B1.setNameForMessage("Acounting Rule");
            // START 2017/12/25 E.Kameishi [QC#20312,ADD]
            scrnMsg.B.no(i).dealDfrdBalAmt_B1.setNameForMessage("Deferred Revenue Balance Amount");
            scrnMsg.B.no(i).firstRevRecogDt_B1.setNameForMessage("First Revenue Recognition Date");
            scrnMsg.B.no(i).nextRevRecogDt_B1.setNameForMessage("Next Revenue Recognition Date");
            scrnMsg.B.no(i).dealSchdRevAmt_B1.setNameForMessage("Schedule Revenue Amount");
            scrnMsg.B.no(i).revRecogCnt_B1.setNameForMessage("Revenue Recognition Count");
            scrnMsg.B.no(i).trxCd_B1.setNameForMessage("Trx");
            scrnMsg.B.no(i).trxRsnCd_B1.setNameForMessage("Trx Reason");
            // END 2017/12/25 E.Kameishi [QC#20312,ADD]
            scrnMsg.B.no(i).dfrdAcctgRuleDurnAot_B1.setNameForMessage("Accounting Rule Duration");
            scrnMsg.B.no(i).durnStartDt_B1.setNameForMessage("Rule Start Date");
            scrnMsg.B.no(i).invLineSplTpCd_B1.setNameForMessage("Revenue Split Type");
            // START 2016/08/01 S.Fujita [QC#10148,ADD]
            scrnMsg.B.no(i).coaBrNm_B1.setNameForMessage("Salesrep Branch");
            // END   2016/08/01 S.Fujita [QC#10148,ADD]
        }

        for(int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).xxChkBox_C1.setNameForMessage("Check Box");
            scrnMsg.C.no(i).invBolLineNum_C1.setNameForMessage("BOL LIne Number");
            scrnMsg.C.no(i).invLineNum_C1.setNameForMessage("Invoice Line Number");
            scrnMsg.C.no(i).ajeInvAcctClsCd_C1.setNameForMessage("Accounting Class");
            scrnMsg.C.no(i).xxScrItem61Txt_C1.setNameForMessage("Account Information");
            scrnMsg.C.no(i).jrnlDealAmt_C1.setNameForMessage("Debit Amount");
            scrnMsg.C.no(i).jrnlDealAmt_C2.setNameForMessage("Credit Amount");
            scrnMsg.C.no(i).glDt_C1.setNameForMessage("GL date");
            // START 2016/06/03 S.Fujita [QC#9157,ADD]
            scrnMsg.C.no(i).xxLineNum_C1.setNameForMessage("Sales Credit Number");
            // END   2016/06/03 S.Fujita [QC#9157,ADD]
        }

        for(int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).xxChkBox_D1.setNameForMessage("Check Box");
            scrnMsg.D.no(i).invBolLineNum_D1.setNameForMessage("BOL Line Number");
            scrnMsg.D.no(i).invLineNum_D1.setNameForMessage("Invoice Line Number");
            scrnMsg.D.no(i).soNum_D1.setNameForMessage("SO Number");
            scrnMsg.D.no(i).bolNum_D1.setNameForMessage("BOL Number");
            scrnMsg.D.no(i).shipFromInvtyLocCd_D1.setNameForMessage("Warehouse");
            // START 2016/08/01 S.Fujita [QC#10148,MOD]
//            scrnMsg.D.no(i).shipToCustCd_D1.setNameForMessage("Ship To Location Code");
//            scrnMsg.D.no(i).shipToLocNm_D1.setNameForMessage("Shipt To Location Name");
            scrnMsg.D.no(i).shipToCustAcctCd_D1.setNameForMessage("Ship To Cust Account Number");
            scrnMsg.D.no(i).shipToCustAcctNm_D1.setNameForMessage("Ship To Cust Account Name");
            scrnMsg.D.no(i).locNum_D1.setNameForMessage("Ship To Location");
            scrnMsg.D.no(i).xxPsnNm_D1.setNameForMessage("Ship To Contact");
            // END   2016/08/01 S.Fujita [QC#10148,MOD]
            scrnMsg.D.no(i).shipToFirstLineAddr_D1.setNameForMessage("1st Line Address");
            scrnMsg.D.no(i).shipToScdLineAddr_D1.setNameForMessage("2nd Line Address");
            scrnMsg.D.no(i).shipToStCd_D1.setNameForMessage("State Code");
            scrnMsg.D.no(i).shipToPostCd_D1.setNameForMessage("Postal Code");
            scrnMsg.D.no(i).shipToCtyAddr_D1.setNameForMessage("City Address");
            scrnMsg.D.no(i).shipDt_D1.setNameForMessage("Ship Date");
            scrnMsg.D.no(i).shipDealNetAmt_D1.setNameForMessage("Net Sales Amount");
            scrnMsg.D.no(i).shipDealSlsAmt_D1.setNameForMessage("Sales Amount");
            scrnMsg.D.no(i).shipDealFrtAmt_D1.setNameForMessage("Freight Amount");
            scrnMsg.D.no(i).frtDealTaxAmt_D1.setNameForMessage("Freight Tax Amount");
            scrnMsg.D.no(i).shipDealDiscAmt_D1.setNameForMessage("Discount Amount");
            scrnMsg.D.no(i).shipDealHdlgChrgAmt_D1.setNameForMessage("Shipping & Handling Charge Amount");
            scrnMsg.D.no(i).totBolDealTaxAmt_D1.setNameForMessage("Total TAX Amount");
            scrnMsg.D.no(i).frtTaxPct_D1.setNameForMessage("Freight Tax Percent");
        }
    }
}
