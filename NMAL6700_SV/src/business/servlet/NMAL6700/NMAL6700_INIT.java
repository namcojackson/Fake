/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6700.NMAL6700CMsg;
import business.servlet.NMAL6700.common.NMAL6700CommonLogic;
import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         N.Sugiura       Update          N/A
 * 2017/12/06   Fujitsu         Hd.Sugawara     Update          QC#21897
 * 2018/01/16   Hitachi         Y.Takeno        Update          QC#21734
 * 2018/01/25   Fujitsu         H.Ikeda         Update          QC#22095
 * 2018/07/30   Fujitsu         S.Ohki          Update          QC#27222
 * 2018/08/21   Fujitsu         S.Ohki          Update          QC#27222-2
 * 2018/12/12   Fujitsu         M.Ishii          Update         QC#29315
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NMAL6700_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NMAL6700Constant.BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            if (null != params[0]) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H1, param01);
            }
            if (null != params[1]) {
                EZDBStringItem param02 = (EZDBStringItem) params[1];
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNum_H1, param02);
            }
        }

        NMAL6700CMsg bizMsg = new NMAL6700CMsg();
        bizMsg.setBusinessID(NMAL6700Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        NMAL6700CMsg bizMsg = (NMAL6700CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6700CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
        NMAL6700CommonLogic.changeFormatTime(scrnMsg);
        NMAL6700CommonLogic.setAppFracDigit(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.dsAcctNm_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        // Header
        scrnMsg.dsAcctNm_H1.setNameForMessage("Account Name");
        scrnMsg.dsAcctInacRsnCd_H3.setNameForMessage("Reason");
        scrnMsg.dsAcctTpCd_H3.setNameForMessage("Type");
        scrnMsg.dsAcctItrlFlg_H3.setNameForMessage("Internal/External");
        scrnMsg.dsAcctClsCd_H3.setNameForMessage("Classification");
        scrnMsg.coaChCd_H1.setNameForMessage("GL Sales Channel");
        scrnMsg.coaAfflCd_H1.setNameForMessage("GL Intercompany Code");
        scrnMsg.dsAcctDlrCd_H3.setNameForMessage("Dealer Code");

        scrnMsg.xxChkBox_H1.setNameForMessage("Active");
        scrnMsg.xxChkBox_H2.setNameForMessage("Active Customer Activity");
        // 2023/11/06 QC#61924 Add Start
        scrnMsg.xxChkBox_H3.setNameForMessage("Deactivate for new tx");
        // 2023/11/06 QC#61924 Add End

        scrnMsg.dsAcctLegalNm_H1.setNameForMessage("Account Legal Name");
        scrnMsg.dbaNm_H1.setNameForMessage("Does Business As(DBA)");
        scrnMsg.dsAcctDunsNm_H1.setNameForMessage("DNB Name");
        scrnMsg.dsAcctAltNm_H1.setNameForMessage("Altername Acct Name");
        scrnMsg.dsXtrnlRefTxt_H1.setNameForMessage("External Reference");
        scrnMsg.dsDataSrcTxt_H1.setNameForMessage("Source");

        // Address Tab
        scrnMsg.xxChkBox_AX.setNameForMessage("Show Inactive");
        scrnMsg.xxPageShowCurNum_A1.setNameForMessage("Current Page Number");
        scrnMsg.xxPageShowTotNum_A1.setNameForMessage("Total Page Number");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_AB.setNameForMessage("Bill To");
            scrnMsg.A.no(i).xxChkBox_AS.setNameForMessage("Ship To");
            scrnMsg.A.no(i).xxChkBox_AP.setNameForMessage("Primary");
        }

        // Relationships Tab
        scrnMsg.xxChkBox_CX.setNameForMessage("Show Inactive");
        scrnMsg.xxPageShowCurNum_C1.setNameForMessage("Current Page Number");
        scrnMsg.xxPageShowTotNum_C1.setNameForMessage("Total Page Number");

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).dsAcctRelnTpCd_C3.setNameForMessage("Relationship Type");
            scrnMsg.C.no(i).dsAcctNum_C1.setNameForMessage("Account#");
            scrnMsg.C.no(i).dsAcctNm_C1.setNameForMessage("Account Name");
            scrnMsg.C.no(i).xxChkBox_CB.setNameForMessage("Bill To");
            scrnMsg.C.no(i).xxChkBox_CS.setNameForMessage("Ship To");
            scrnMsg.C.no(i).xxChkBox_CR.setNameForMessage("Reciprocal");
            scrnMsg.C.no(i).effFromDt_C1.setNameForMessage("Start Date");
            scrnMsg.C.no(i).effThruDt_C1.setNameForMessage("End Date");
        }

        // Contacts Tab
        scrnMsg.xxChkBox_DX.setNameForMessage("Show Inactive");
        scrnMsg.xxPageShowCurNum_D1.setNameForMessage("Current Page Number");
        scrnMsg.xxPageShowTotNum_D1.setNameForMessage("Total Page Number");

        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).ctacPsnPk_D1.setNameForMessage("Contact ID");
            // Mod Start 2017/12/06 QC#21897
            //scrnMsg.D.no(i).ctacTpNm_D1.setNameForMessage("Type");
            scrnMsg.D.no(i).ctacTpDescTxt_D1.setNameForMessage("Type");
            // Mod End 2017/12/06 QC#21897
            scrnMsg.D.no(i).ctacPsnFirstNm_D1.setNameForMessage("First Name");
            scrnMsg.D.no(i).ctacPsnLastNm_D1.setNameForMessage("Last Name");
            scrnMsg.D.no(i).dsCtacPsnDeptNm_D1.setNameForMessage("Department");
            scrnMsg.D.no(i).dsCtacPntValTxt_D1.setNameForMessage("Email-Work");
            scrnMsg.D.no(i).dsCtacPntValTxt_D2.setNameForMessage("Phone-Work");
            scrnMsg.D.no(i).ctacPsnExtnNum_D1.setNameForMessage("Ext");
            scrnMsg.D.no(i).effFromDt_D1.setNameForMessage("Start Date");
            scrnMsg.D.no(i).effThruDt_D1.setNameForMessage("End Date");
            scrnMsg.D.no(i).dsPrimLocFlg_D1.setNameForMessage("Primary");
            scrnMsg.D.no(i).dplStsNm_D1.setNameForMessage("Status");
        }

        // Marketing Tab
        scrnMsg.xxPageShowCurNum_M1.setNameForMessage("Current Page Number");
        scrnMsg.xxPageShowTotNum_M1.setNameForMessage("Total Page Number");
        scrnMsg.dsAcctDunsNum_M1.setNameForMessage("DUNS#");
        scrnMsg.dsUltDunsNum_M1.setNameForMessage("Ultimate DUNS#");
        scrnMsg.dsLocEmpNum_M1.setNameForMessage("# of Employees");
        scrnMsg.dsLocRevAmt_M1.setNameForMessage("Annual Revenue");
        scrnMsg.dsCustSicCd_M1.setNameForMessage("SIC Code");
        scrnMsg.dsCustSicDescTxt_M1.setNameForMessage("Industry");
        scrnMsg.dsLastUpdDunsDt_M1.setNameForMessage("Last DNB Update Date");
        scrnMsg.dsAcctUrl_M1.setNameForMessage("Website");
        scrnMsg.xxScrItem7Txt_M1.setNameForMessage("Time From");
        scrnMsg.xxScrItem7Txt_M2.setNameForMessage("Time To");
        scrnMsg.xxScrItem7Txt_M3.setNameForMessage("Time From");
        scrnMsg.xxScrItem7Txt_M4.setNameForMessage("Time To");
        scrnMsg.xxScrItem7Txt_M5.setNameForMessage("Time From");
        scrnMsg.xxScrItem7Txt_M6.setNameForMessage("Time To");

        for (int i = 0; i < scrnMsg.E.length(); i++) {
            scrnMsg.E.no(i).dsBizAreaCd_E3.setNameForMessage("Business Area");
            scrnMsg.E.no(i).dsAcctGrpCd_E3.setNameForMessage("Group Code");
            scrnMsg.E.no(i).dsAcctGrpDescTxt_E3.setNameForMessage("Group Name");
            scrnMsg.E.no(i).effFromDt_E1.setNameForMessage("Start Date");
            scrnMsg.E.no(i).effThruDt_E1.setNameForMessage("End Date");
        }

        for (int i = 0; i < scrnMsg.N.length(); i++) {
            scrnMsg.N.no(i).svcAccsPmitNum_N1.setNameForMessage("No");
            scrnMsg.N.no(i).svcAccsPmitDescTxt_N1.setNameForMessage("Access Permit Description");
            scrnMsg.N.no(i).effFromDt_N1.setNameForMessage("Start Date");
            scrnMsg.N.no(i).effToDt_N1.setNameForMessage("End Date");
        }

        // Transactions Tab
        for (int i = 0; i < scrnMsg.F.length(); i++) {
            scrnMsg.xxPageShowCurNum_F1.setNameForMessage("Current Page Number");
            scrnMsg.xxPageShowTotNum_F1.setNameForMessage("Total Page Number");
            scrnMsg.F.no(i).dsTrxRuleTpCd_F3.setNameForMessage("Transaction Type");
            scrnMsg.F.no(i).dsPoReqFlg_F1.setNameForMessage("PO Required");
            scrnMsg.F.no(i).dsBlktPoNum_F1.setNameForMessage("Blanket PO#");
            scrnMsg.F.no(i).dsPoExprDt_F1.setNameForMessage("PO Expire Date");
            scrnMsg.F.no(i).dsDefBillToCd_F1.setNameForMessage("Default Bill To");
            scrnMsg.F.no(i).dsDefShipToCd_F1.setNameForMessage("Default Ship To");
            scrnMsg.F.no(i).custEffLvlCd_F3.setNameForMessage("Effective Level");
            scrnMsg.F.no(i).dsCrCardReqFlg_F1.setNameForMessage("Credit Card Reqd");
            scrnMsg.F.no(i).dsOvngtAllwFlg_F1.setNameForMessage("Overnight Allowed");
        }
        for (int i = 0; i < scrnMsg.S.length(); i++) {
            scrnMsg.S.no(i).dsSpclHdlgTpCd_S3.setNameForMessage("Type");
            scrnMsg.S.no(i).dsSpclHdlgTpValCd_S3.setNameForMessage("Value");
            scrnMsg.S.no(i).effFromDt_S1.setNameForMessage("Start Date");
            scrnMsg.S.no(i).effThruDt_S1.setNameForMessage("End Date");
            scrnMsg.S.no(i).custEffLvlCd_S3.setNameForMessage("Effective Level");
        }
        scrnMsg.coaCcCd_F1.setNameForMessage("DEPT#");

        // Cr/Clt Tab
        scrnMsg.dsCustArTmplNm_U1.setNameForMessage("Apply Template");
        scrnMsg.ccyCd_U3.setNameForMessage("Currency");
        scrnMsg.custCrRtgCd_U3.setNameForMessage("Credit Rating");
        scrnMsg.crLimitAmt_U1.setNameForMessage("Credit Limit");
        scrnMsg.crChkReqTpCd_U3.setNameForMessage("Credit Hold");
        scrnMsg.crRiskClsCd_U3.setNameForMessage("Grace Period (Days)");
        // START 2018/01/25 [QC#22095, ADD]
        scrnMsg.contrCrRiskClsCd_U3.setNameForMessage("Contract Grace Period");
        // END   2018/01/25 [QC#22095, ADD]     
        scrnMsg.pmtTermCashDiscCd_U3.setNameForMessage("Payment Term");
        scrnMsg.ovrdPmtTermFlg_U1.setNameForMessage("Override Payment Term");
        scrnMsg.cashOrCcReqFlg_U1.setNameForMessage("CWO or CC Required");
        scrnMsg.custHardHldFlg_U1.setNameForMessage("Hard Hold");

        scrnMsg.arStmtFlg_U1.setNameForMessage("Send Statements");
        // START 2018/01/25 [QC#22095, ADD]
        //scrnMsg.sendCrBalStmtFlg_U1.setNameForMessage("Send Credit Balance");
        // END   2018/01/25 [QC#22095, ADD]     
        scrnMsg.arStmtIssCycleCd_U3.setNameForMessage("Statements Issue Day");
        // START 2018/01/16 [QC#21734, DEL]
        // scrnMsg.dunFlg_U1.setNameForMessage("Send Dunning Letters");
        // END   2018/01/16 [QC#21734, DEL]
        scrnMsg.cltCustTpCd_U1.setNameForMessage("Collection Customer Type");
        scrnMsg.cltPtfoCd_U1.setNameForMessage("Default Collector");
        scrnMsg.dsCltAcctStsCd_U3.setNameForMessage("Account Status");
        scrnMsg.lateFeeFlg_U1.setNameForMessage("Calculate Late Fee");
        scrnMsg.lateFeeAmt_U1.setNameForMessage("Minimum Balance to Calculate Late Fee");

        // Del Start 2018/07/30 QC#27222
//        scrnMsg.dsCustTaxCd_U1.setNameForMessage("Tax Code");
//        scrnMsg.dsCustTaxCalcCd_U3.setNameForMessage("Tax Calculation");
        // Mod Start 2018/08/21 QC#27222-2 Uncomment
        scrnMsg.dsTaxExemFlg_U1.setNameForMessage("Tax Exempt");
        scrnMsg.dsExemExprDt_U1.setNameForMessage("Exempt Exp Date");
        // Mod End 2018/08/21 QC#27222-2
        // Del End 2018/07/30 QC#27222
        scrnMsg.dsTaxGrpExemCd_U3.setNameForMessage("Vertex Group Exemption");
        scrnMsg.dsTaxPrntTpCd_U3.setNameForMessage("Tax Printing");

        // Invoice Billing Tab
        scrnMsg.custEffLvlCd_V3.setNameForMessage("Effective Level");
        scrnMsg.defBaseTpCd_V3.setNameForMessage("BASE Adv/Arrears");
        scrnMsg.defBaseCycleCd_V3.setNameForMessage("BASE Cycle");
        scrnMsg.defUsgTpCd_V3.setNameForMessage("USAGE Adv/Arrears");
        scrnMsg.defUsgCycleCd_V3.setNameForMessage("USAGE Cycle");
        // START 2022/03/22 [QC#59683, MOD]
//        scrnMsg.dsBillTgtrFlg_V1.setNameForMessage("Bill Base & Usage Together");
        scrnMsg.dsInvTgtrTpCd_V1.setNameForMessage("Invoicing Option");
        // END   2022/03/22 [QC#59683, MOD]

        for (int i = 0; i < scrnMsg.G.length(); i++) {
            scrnMsg.G.no(i).custInvSrcCd_G3.setNameForMessage("Invoice Source");
            scrnMsg.G.no(i).custBllgTpCd_G3.setNameForMessage("Bill Type");
            scrnMsg.G.no(i).custConslTermCd_G3.setNameForMessage("Consolidated Term");
            scrnMsg.G.no(i).custBllgVcleCd_G3.setNameForMessage("Bill Vehicle");
            scrnMsg.G.no(i).xxGenlFldAreaTxt_G1.setNameForMessage("Internal Email Review");
            scrnMsg.G.no(i).xxCustInvRuleRcpntTxt_G1.setNameForMessage("Internal Email Review Name");
            scrnMsg.G.no(i).xxGenlFldAreaTxt_G2.setNameForMessage("External Email Contact");
            scrnMsg.G.no(i).xxCustInvRuleRcpntTxt_G2.setNameForMessage("External Email Review Name");
            scrnMsg.G.no(i).custEmlMsgTxt_G1.setNameForMessage("Custom Email Subject");
            scrnMsg.G.no(i).defInvGrpCd_G3.setNameForMessage("Default Grouping");
            scrnMsg.G.no(i).invGrpNum_G1.setNameForMessage("Invoice Group#");
            scrnMsg.G.no(i).custEffLvlCd_G3.setNameForMessage("Effective Level");
        }

        for (int i = 0; i < scrnMsg.K.length(); i++) {
            scrnMsg.K.no(i).xxCtlNm_K1.setNameForMessage("Control");
            scrnMsg.K.no(i).bllgAttrbNm_K1.setNameForMessage("Billing Attribute Name");
            scrnMsg.K.no(i).bllgAttrbValTxt_K1.setNameForMessage("Defult Value");
            scrnMsg.K.no(i).bllgAttrbEnblFlg_K1.setNameForMessage("Enabled");
            scrnMsg.K.no(i).bllgAttrbReqFlg_K1.setNameForMessage("Required");
            scrnMsg.K.no(i).custEffLvlCd_K3.setNameForMessage("Effective Level");
        }

        // BASE/USAGE
        scrnMsg.defBaseTpCd_V3.setNameForMessage("Adv/Arrears");
        scrnMsg.defBaseCycleCd_V3.setNameForMessage("Cycle");

        // 2018/12/10 QC#29315 Del Start
//        // Bank Account Tab
//        for (int i = 0; i < scrnMsg.W.length(); i++) {
//            scrnMsg.W.no(i).vndCd_W3.setNameForMessage("Carrier");
//            scrnMsg.W.no(i).dsCarrAcctNum_W1.setNameForMessage("Account Number");
//            scrnMsg.W.no(i).effFromDt_W1.setNameForMessage("Start Date");
//            scrnMsg.W.no(i).effThruDt_W1.setNameForMessage("End Date");
//        }
        // 2018/12/10 QC#29315 Del End
        scrnMsg.xxPageShowCurNum_I1.setNameForMessage("Current Page Number");
        scrnMsg.xxPageShowTotNum_I1.setNameForMessage("Total Page Number");
        for (int i = 0; i < scrnMsg.I.length(); i++) {
            scrnMsg.I.no(i).effThruDt_I1.setNameForMessage("End Date");
        }

        // Msg Note Tab
        scrnMsg.xxPageShowCurNum_J1.setNameForMessage("Current Page Number");
        scrnMsg.xxPageShowTotNum_J1.setNameForMessage("Total Page Number");
        for (int i = 0; i < scrnMsg.J.length(); i++) {
            scrnMsg.J.no(i).dsAcctNum_J1.setNameForMessage("Account#");
            scrnMsg.J.no(i).dsAcctNm_J1.setNameForMessage("Account Name");
            scrnMsg.J.no(i).lineBizTpCd_J3.setNameForMessage("Line Of Business");
            scrnMsg.J.no(i).dsBizAreaCd_J3.setNameForMessage("Business Area");
            scrnMsg.J.no(i).dsCustMsgTpCd_J3.setNameForMessage("Type");
            scrnMsg.J.no(i).dsCustMsgTxt_J1.setNameForMessage("Message Body");
            scrnMsg.J.no(i).dsPrintOnInvFlg_J1.setNameForMessage("Print On Invoice");
            scrnMsg.J.no(i).custEffLvlCd_J3.setNameForMessage("Effective Level");
            scrnMsg.J.no(i).effThruDt_J1.setNameForMessage("End Date");
        }

        // 2018/02/14 QC#20297(Sol#379) add start
        // Shipping Tab
        scrnMsg.xxPageShowCurNum_M1.setNameForMessage("Current Page Number");
        scrnMsg.xxPageShowTotNum_M1.setNameForMessage("Total Page Number");
        for (int i = 0; i < scrnMsg.M.length(); i++) {
            scrnMsg.M.no(i).lineBizTpCd_M3.setNameForMessage("Line Of Business");
            scrnMsg.M.no(i).dsBizAreaCd_M3.setNameForMessage("Business Area");
            scrnMsg.M.no(i).frtCondCd_M3.setNameForMessage("Freight Term");
            scrnMsg.M.no(i).shpgSvcLvlCd_M3.setNameForMessage("Service Levely");
            scrnMsg.M.no(i).custEffLvlCd_M3.setNameForMessage("Effective Level");
            scrnMsg.M.no(i).effThruDt_M1.setNameForMessage("End Date");
            // 2018/12/10 QC#29315 Add Start
            scrnMsg.M.no(i).xxChkBox_MD.setNameForMessage("Default Flag");
            scrnMsg.M.no(i).dsCarrAcctNum_M1.setNameForMessage("Account Number");
            scrnMsg.M.no(i).vndCd_M3.setNameForMessage("Carrier");
            scrnMsg.M.no(i).effFromDt_M1.setNameForMessage("Start Date");
            // 2018/12/10 QC#29315 Add End
        }
        // 2018/02/14 QC#20297(Sol#379) add end
    }
}
