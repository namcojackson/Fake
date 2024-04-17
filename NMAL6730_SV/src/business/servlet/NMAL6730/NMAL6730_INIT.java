/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import static business.servlet.NMAL6730.constant.NMAL6730Constant.TAB_FINANCIAL;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6730.NMAL6730CMsg;
import business.servlet.NMAL6730.constant.NMAL6730Constant;
import business.servlet.NMAL6730.common.NMAL6730CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            Fujitsu         Create          N/A
 * 2015/11/05   Fujitsu         N.Sugiura       Update          N/A
 * 2016/02/15   Fujitsu         C.Tanaka        Update          QC#1668
 * 2016/05/02   SRAA            Y.Chen          Update          QC#4324
 * 2016/06/08   SRAA            Y.Chen          Update          QC#7781
 * 2016/08/04   Fujitsu         R.Nakamura      Update          QC#9078
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 * 2018/01/16   Hitachi         Y.Takeno        Update          QC#21734
 * 2018/01/25   Fujitsu         H.Ikeda         Update          QC#22095
 * 2018/08/01   Fujitsu         S.Ohki          Update          QC#27222
 * 2018/08/21   Fujitsu         S.Ohki          Update          QC#27222-2
 * 2023/02/02   Hitachi         S.Nakatani      Update          QC#60811
 *</pre>
 */
public class NMAL6730_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        // checkBusinessAppGranted(getContextUserInfo().getUserId(),
        // NMAL6720Constant.BUSINESS_ID);

        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null) {
            if (params.length == 1) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd_P1, param01);
            }
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_FINANCIAL);

        scrnMsg.addCheckItem(scrnMsg.billToCustCd_P1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd_H1, scrnMsg.billToCustCd_P1);

        NMAL6730CMsg bizMsg = new NMAL6730CMsg();
        bizMsg.setBusinessID(NMAL6730Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
        NMAL6730CMsg bizMsg = (NMAL6730CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6730CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());

        setAppFracDigit(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.ccyCd_P1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        // Header
        scrnMsg.dsAcctNm_H1.setNameForMessage("Account Name");
        scrnMsg.xxAllLineAddr_H1.setNameForMessage("Address");
        scrnMsg.ctyAddr_H1.setNameForMessage("City");
        scrnMsg.stCd_H1.setNameForMessage("State");
        scrnMsg.postCd_H1.setNameForMessage("Postal Code");
        scrnMsg.locNum_H1.setNameForMessage("Location Number");
        scrnMsg.billToCustCd_H1.setNameForMessage("Bill To Code");
        scrnMsg.coaChCd_H1.setNameForMessage("Sales Channel");
        // QC#9448
        // scrnMsg.coaAfflCd_H1.setNameForMessage("Intercompany");

        // Financials Tab
        // CREDIT
        scrnMsg.ccyCd_P1.setNameForMessage("Currency");
        scrnMsg.custCrRtgCd_P1.setNameForMessage("Credit Rating");
        scrnMsg.crLimitAmt_F1.setNameForMessage("Credit Limit");
        scrnMsg.crChkReqTpCd_P1.setNameForMessage("Credit Hold");
        scrnMsg.crRiskClsCd_P1.setNameForMessage("Grace Period (Days)");
        // START 2018/01/25 [QC#22095, ADD]
        scrnMsg.contrCrRiskClsCd_P1.setNameForMessage("Contract Grace Period");
        // END   2018/01/25 [QC#22095, ADD] 
        scrnMsg.pmtTermCashDiscCd_P1.setNameForMessage("Payment Term");
        scrnMsg.ovrdPmtTermFlg_F1.setNameForMessage("Override Payment Term");
        scrnMsg.cashOrCcReqFlg_F1.setNameForMessage("CWO or CC Required");
        // QC#4324
        scrnMsg.custHardHldFlg_F1.setNameForMessage("Hard Hold");
        scrnMsg.remId_F1.setNameForMessage("Rem ID");

        // COLLECTIONS
        // scrnMsg.ar.setNameForMessage("Send Statements");
        scrnMsg.arStmtFlg_F1.setNameForMessage("Send Statements");
        scrnMsg.arStmtIssCycleCd_P1.setNameForMessage("Statements Issue Day");
        // START 2018/01/16 [QC#21734, DEL]
        // scrnMsg.dunFlg_F1.setNameForMessage("Send Dunning Letters");
        // END   2018/01/16 [QC#21734, DEL]
        scrnMsg.cltCustTpCd_F1.setNameForMessage("Collection Customer Type");
        scrnMsg.cltPtfoCd_F1.setNameForMessage("Default Collector");
        scrnMsg.dsCltAcctStsCd_P1.setNameForMessage("Account Status");
        scrnMsg.lateFeeFlg_F1.setNameForMessage("Calculate Late Fee");
        scrnMsg.lateFeeAmt_F1.setNameForMessage("Minimum Balance to Calculate Late Fee");
        // Start 2023/2/02 S.Nakatani [QC#60811, ADD]
        scrnMsg.mlyLateFeeRate_F1.setNameForMessage("Monthly Late Fee Rate");
        // End 2023/2/02 S.Nakatani [QC#60811, ADD]

        // TAXING
        // Del Start 2018/08/01 QC#27222
//        scrnMsg.dsCustTaxCd_F1.setNameForMessage("Tax Code");
//        scrnMsg.dsCustTaxCalcCd_P1.setNameForMessage("Tax Calculation");
        // Mod Start 2018/08/21 QC#27222-2 Uncomment
        scrnMsg.dsTaxExemFlg_F1.setNameForMessage("Tax Exempt");
        scrnMsg.dsExemExprDt_F1.setNameForMessage("Exempt Exp Date");
        // Mod End 2018/08/21 QC#27222-2
        // Del End 2018/08/01 QC#27222
        scrnMsg.dsTaxGrpExemCd_P1.setNameForMessage("Vertex Group Exemption");
        scrnMsg.dsTaxPrntTpCd_P1.setNameForMessage("Tax Printing");

        // DEFAULT CONTRACT BILLING CYCLE
        scrnMsg.defBaseTpCd_P1.setNameForMessage("Base");
        scrnMsg.defBaseCycleCd_P1.setNameForMessage("Base");
        scrnMsg.defUsgTpCd_P1.setNameForMessage("Usage");
        scrnMsg.defUsgCycleCd_P1.setNameForMessage("Usage");
        scrnMsg.dsBillTgtrFlg_I1.setNameForMessage("Bill Base & Usage Together");

        // INVOICE GROUPING
        NMAL6730_ABMsg aBMsg;
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            aBMsg = scrnMsg.A.no(i);
            aBMsg.custInvSrcCd_P1.setNameForMessage("Invoice Source");
            aBMsg.custBllgTpCd_P1.setNameForMessage("Bill Type");
            aBMsg.custConslTermCd_P1.setNameForMessage("Consolidated Term");
            aBMsg.custBllgVcleCd_P1.setNameForMessage("Bill Vehicle");
            // QC#7781
            aBMsg.xxGenlFldAreaTxt_A1.setNameForMessage("Internal Email Review");
            aBMsg.xxCustInvRuleRcpntTxt_A1.setNameForMessage("Internal Email Review Name");
            aBMsg.xxGenlFldAreaTxt_A2.setNameForMessage("External Email Review");
            aBMsg.xxCustInvRuleRcpntTxt_A2.setNameForMessage("External Email Review Name");
            aBMsg.custEmlMsgTxt_A1.setNameForMessage("Custom Email Subject");
            aBMsg.defInvGrpCd_P1.setNameForMessage("Default Grouping");
            aBMsg.invGrpNum_A1.setNameForMessage("Invoice Group#");
        }

        for (int i = 0; i < scrnMsg.K.length(); i++) {
            NMAL6730_KBMsg kbMsg = scrnMsg.K.no(i);
            kbMsg.xxCtlNm_K1.setNameForMessage("Control");
            kbMsg.bllgAttrbNm_K1.setNameForMessage("Billing Attribute Name");
            kbMsg.bllgAttrbValTxt_K1.setNameForMessage("Default Value");
            kbMsg.bllgAttrbEnblFlg_K1.setNameForMessage("Enabled");
            kbMsg.bllgAttrbReqFlg_K1.setNameForMessage("Required");
            kbMsg.custEffLvlCd_K3.setNameForMessage("Effective");
        }
    }

    private void setAppFracDigit(NMAL6730BMsg scrnMsg) {
        scrnMsg.crLimitAmt_F1.setAppFracDigit(2);
        scrnMsg.lateFeeAmt_F1.setAppFracDigit(2);
        // Start 2023/2/02 S.Nakatani [QC#60811, ADD]
        scrnMsg.mlyLateFeeRate_F1.setAppFracDigit(1);
        // End 2023/2/02 S.Nakatani [QC#60811, ADD]
    }
}
