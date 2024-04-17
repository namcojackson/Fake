/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3050;

import static business.servlet.NFCL3050.constant.NFCL3050Constant.BIZ_ID;
import static business.servlet.NFCL3050.constant.NFCL3050Constant.FUNC_CD_SRCH;
import static business.servlet.NFCL3050.constant.NFCL3050Constant.COMP_TP_COMP;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3050.NFCL3050CMsg;
import business.servlet.NFCL3050.common.NFCL3050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Fujitsu         S.Fujita        Create          N/A
 * 2016/03/09   Hitachi         T.Tsuchida      Update          QC#5103
 * 2016/03/14   Hitachi         T.Tsuchida      Update          QC#5423
 * 2016/07/12   Hitachi         K.Kojima        Update          QC#11049
 * 2016/07/14   Hitachi         K.Kojima        Update          QC#11507
 * 2016/08/23   Fujitsu         S.Fujita        Update          QC#13478
 * 2016/12/01   Fujitsu         T.Murai         Update          QC#16158
 * 2018/05/28   Fujitsu         Y.Matsui        Update          QC#26342
 * 2022/11/29   Hitachi         M.Nakajima      Update          QC#60742
 * 2024/03/05   Hitachi         TZ.Win          Update          QC#63665
 *</pre>
 */
public class NFCL3050_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

       checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3050BMsg scrnMsg = (NFCL3050BMsg) bMsg;
        NFCL3050CMsg bizMsg = new NFCL3050CMsg();

        // START 2016/07/12 K.Kojima [QC#11049,ADD]
        NFCL3050CommonLogic.setDisplayPattern(scrnMsg);
        // END 2016/07/12 K.Kojima [QC#11049,ADD]

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3050BMsg scrnMsg = (NFCL3050BMsg) bMsg;
        NFCL3050CMsg bizMsg  = (NFCL3050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL3050CommonLogic.initCmnBtnProp(this);
        NFCL3050CommonLogic.controlScreen(this, getUserProfileService(), scrnMsg);
        // START 2016/07/12 K.Kojima [QC#11049,ADD]
        NFCL3050CommonLogic.initDisplayFunction(scrnMsg);
        // END 2016/07/12 K.Kojima [QC#11049,ADD]

        // START 2016/08/23 S.Fujita [QC#13478,MOD]
        // set initial value to field
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_H, COMP_TP_COMP);
        // END   2016/08/23 S.Fujita [QC#13478,MOD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NFCL3050BMsg scrnMsg = (NFCL3050BMsg) bMsg;
        // START 2016/03/14 T.Tsuchida [QC#5423,MOD]
//        scrnMsg.billToCustAcctNm_H.setNameForMessage("Bill to customer name");
//        scrnMsg.billToCustAcctCd_H.setNameForMessage("Bill to customer number");
//        scrnMsg.shipToLocNm_H.setNameForMessage("Ship to customer name");
//        scrnMsg.shipToCustCd_H.setNameForMessage("Ship to customer number");
//        scrnMsg.invNum_FR.setNameForMessage("Invoice number from");
//        scrnMsg.invNum_TO.setNameForMessage("Invoice number to");
//        scrnMsg.dueDt_FR.setNameForMessage("Due date from");
//        scrnMsg.dueDt_TO.setNameForMessage("Due date to");
//        scrnMsg.dealRmngBalGrsAmt_LO.setNameForMessage("Invoice balance low");
//        scrnMsg.dealRmngBalGrsAmt_HI.setNameForMessage("Invoice balance high");
//        scrnMsg.invTotDealNetAmt_LO.setNameForMessage("Invoice amount low");
//        scrnMsg.invTotDealNetAmt_HI.setNameForMessage("Invoice amount high");
//        scrnMsg.dsInvTpCd_L.setNameForMessage("Invoice type");
//        scrnMsg.invTpCd_L.setNameForMessage("Invoice class");
//        scrnMsg.mdlNm_H.setNameForMessage("Model number");
//        scrnMsg.serNum_H.setNameForMessage("Serial number");
//        scrnMsg.xxChkBox_CL.setNameForMessage("Include closed invoices");
//        scrnMsg.xxChkBox_CN.setNameForMessage("Include consolidated invoices");
//        scrnMsg.xxChkBox_ER.setNameForMessage("Invoice Error Only");
//
//        for (int i = 0; i < scrnMsg.A.length(); i++) {
//            NFCL3050_ABMsg aBMsg = scrnMsg.A.no(i);
//            aBMsg.billToCustAcctNm_A.setNameForMessage("Customer Name");
//            aBMsg.billToCustAcctCd_A.setNameForMessage("Cust Num");
//            aBMsg.invNum_A.setNameForMessage("Invoice Num");
//            aBMsg.invDt_A.setNameForMessage("Invoice Dt");
//            aBMsg.invDueDt_A.setNameForMessage("Due Date");
//            aBMsg.invTotDealNetAmt_A.setNameForMessage("Invoice Amount");
//            aBMsg.dealRmngBalGrsAmt_A.setNameForMessage("Balance");
//            aBMsg.dsContrNum_A.setNameForMessage("Sales order /Contract#");
//            // START 2016/03/09 T.Tsuchida [QC#5103,MOD]
//            //aBMsg.slsRepTocNm_B.setNameForMessage("Sales Rep");
//            aBMsg.xxScrItem61Txt_B.setNameForMessage("Sales Rep");
//            // END 2016/03/09 T.Tsuchida [QC#5103,MOD]
//            aBMsg.dsInvTpDescTxt_A.setNameForMessage("Invoice Type");
//            aBMsg.invTpDescTxt_A.setNameForMessage("Invoice Class");
//            aBMsg.invSrcTxt_A.setNameForMessage("Batch Source");
//            aBMsg.arCashApplyStsDescTxt_A.setNameForMessage("Status");
//            aBMsg.dealCltDsptAmt_A.setNameForMessage("Dispute Amt");
//            aBMsg.cltDsptDt_A.setNameForMessage("Dispute Date");
//            aBMsg.invErrMsgTxt_A.setNameForMessage("Invoice Error Message Text");
//            aBMsg.invldValTxt_A.setNameForMessage("Invalid Value Text");
//        }
        scrnMsg.billToCustAcctNm_H.setNameForMessage("Bill To Customer Name");
        scrnMsg.billToCustAcctCd_H.setNameForMessage("Bill To Customer Number");
        scrnMsg.shipToLocNm_H.setNameForMessage("Ship To Customer Name");
        scrnMsg.shipToCustCd_H.setNameForMessage("Ship To Customer Number");
        scrnMsg.invNum_FR.setNameForMessage("Invoice Number From");
        scrnMsg.invNum_TO.setNameForMessage("Invoice Number To");
        scrnMsg.xxFromDt_FR.setNameForMessage("Invoice Date From");
        scrnMsg.xxToDt_TO.setNameForMessage("Invoice Date To");
        scrnMsg.dueDt_FR.setNameForMessage("Due Date From");
        scrnMsg.dueDt_TO.setNameForMessage("Due Date To");
        scrnMsg.dealRmngBalGrsAmt_LO.setNameForMessage("Invoice Balance Low");
        scrnMsg.dealRmngBalGrsAmt_HI.setNameForMessage("Invoice Balance High");
        scrnMsg.invTotDealNetAmt_LO.setNameForMessage("Invoice Amount Low");
        scrnMsg.invTotDealNetAmt_HI.setNameForMessage("Invoice Amount High");
        scrnMsg.dsInvTpCd_L.setNameForMessage("Invoice Type");
        scrnMsg.invTpCd_L.setNameForMessage("Invoice Class");
        scrnMsg.mdlNm_H.setNameForMessage("Model Number");
        scrnMsg.serNum_H.setNameForMessage("Serial Number");
        scrnMsg.xxChkBox_CL.setNameForMessage("Include Closed Invoices");
        scrnMsg.xxChkBox_CN.setNameForMessage("Include Consolidated Invoices");
        scrnMsg.xxChkBox_ER.setNameForMessage("Invoice Error Only");
        // START 2016/07/12 K.Kojima [QC#11049,ADD]
        scrnMsg.glDt_FR.setNameForMessage("GL Date From");
        scrnMsg.glDt_TO.setNameForMessage("GL Date To");
        scrnMsg.srcSysDocNum.setNameForMessage("Source Number");
        // START 2016/08/23 S.Fujita [QC#13478,MOD]
//        scrnMsg.xxChkBox_IC.setNameForMessage("Include Incomplete");
        scrnMsg.xxRadioBtn_H.setNameForMessage("Completed /Incompleted Invoices");
        // END   2016/08/23 S.Fujita [QC#13478,MOD]
        // END 2016/07/12 K.Kojima [QC#11049,ADD]
        // START 2024/03/05 TZ.Win [QC#63665, ADD]
        scrnMsg.custIncdtId.setNameForMessage("CI Number");
        scrnMsg.xxCratDt_FR.setNameForMessage("Creation Date From");
        scrnMsg.xxCratDt_TO.setNameForMessage("Creation Date To");
        scrnMsg.ezInUserID.setNameForMessage("Created by");
        // END 2024/03/05 TZ.Win [QC#63665, ADD]

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NFCL3050_ABMsg aBMsg = scrnMsg.A.no(i);
            aBMsg.billToCustAcctNm_A.setNameForMessage("Customer Name");
            aBMsg.billToCustAcctCd_A.setNameForMessage("Cust Num");
            aBMsg.invNum_A.setNameForMessage("Invoice Num");
            aBMsg.invDt_A.setNameForMessage("Invoice Dt");
            aBMsg.invDueDt_A.setNameForMessage("Due Date");
            aBMsg.invTotDealNetAmt_A.setNameForMessage("Invoice Amount");
            aBMsg.dealRmngBalGrsAmt_A.setNameForMessage("Balance");
            aBMsg.dsContrNum_A.setNameForMessage("Sales Order /Contract#");
            aBMsg.xxScrItem61Txt_B.setNameForMessage("Sales Rep");
            // START 2016/07/14 K.Kojima [QC#11507,MOD]
            // aBMsg.dsInvTpDescTxt_A.setNameForMessage("Invoice Type");
            aBMsg.dsInvTpNm_A.setNameForMessage("Invoice Type");
            // END 2016/07/14 K.Kojima [QC#11507,MOD]
            // START 2016/07/14 K.Kojima [QC#11507,MOD]
            // aBMsg.invTpDescTxt_A.setNameForMessage("Invoice Class");
            aBMsg.invTpNm_A.setNameForMessage("Invoice Class");
            // END 2016/07/14 K.Kojima [QC#11507,MOD]
            aBMsg.invSrcTxt_A.setNameForMessage("Batch Source");
            // START 2016/07/14 K.Kojima [QC#11507,MOD]
            // aBMsg.arCashApplyStsDescTxt_A.setNameForMessage("Status");
            aBMsg.arCashApplyStsNm_A.setNameForMessage("Status");
            // END 2016/07/14 K.Kojima [QC#11507,MOD]
            aBMsg.dealCltDsptAmt_A.setNameForMessage("Dispute Amt");
            aBMsg.cltDsptDt_A.setNameForMessage("Dispute Date");
            aBMsg.invErrMsgTxt_A.setNameForMessage("Invoice Error Message Text");
            aBMsg.invldValTxt_A.setNameForMessage("Invalid Value Text");
            // START 2018/05/28 Y.Matsui [QC#26342,ADD]
            aBMsg.custIssPoNum_A.setNameForMessage("Customer PO#");
            // END   2018/05/28 Y.Matsui [QC#26342,ADD]
            // START 2022/11/28 M.Nakajima [QC#60742,ADD]
            aBMsg.cltPsnNm_A.setNameForMessage("Collector Name");
            // END 2022/11/28 M.Nakajima [QC#60742,ADD]
        }
        // END 2016/03/14 T.Tsuchida [QC#5423,MOD]

        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName()); //ADD 2016/12/01 [QC#16158]

        scrnMsg.dealRmngBalGrsAmt_LO.setAppFracDigit(2);
        scrnMsg.dealRmngBalGrsAmt_HI.setAppFracDigit(2);
        scrnMsg.invTotDealNetAmt_LO.setAppFracDigit(2);
        scrnMsg.invTotDealNetAmt_HI.setAppFracDigit(2);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).invTotDealNetAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).dealRmngBalGrsAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).dealCltDsptAmt_A.setAppFracDigit(2);
        }
    }
}
