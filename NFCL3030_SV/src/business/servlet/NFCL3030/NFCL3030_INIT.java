/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3030;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3030.NFCL3030CMsg;
import business.servlet.NFCL3030.common.NFCL3030CommonLogic;
import business.servlet.NFCL3030.constant.NFCL3030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Receipt Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 01/18/2018   Hitachi         Y.Takeno        Update          QC#21406
 * 02/02/2018   Fujitsu         T.Murai         Update          QC#21372
 * 2020/01/31   Fujitsu         Y.Matsui        Update          QC#54826
 *</pre>
 */
public class NFCL3030_INIT extends S21INITCommonHandler implements NFCL3030Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFCL3030");
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;
        NFCL3030CMsg bizMsg = new NFCL3030CMsg();
        bizMsg.setBusinessID("NFCL3030");
        bizMsg.setFunctionCode("20");

        Object[] params = (Object[]) getArgForSubScreen();

        scrnMsg.appFuncId.setValue("NFCL3030");
        if (params != null && params.length > 0) {
            if (params.length == 1) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.rcptNum_H, param01);
                scrnMsg.appFuncId.setValue("NFCL30XX");
            }
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;
        NFCL3030CMsg bizMsg  = (NFCL3030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL3030CommonLogic.initialize(this, scrnMsg, getUserProfileService());

        scrnMsg.xxDplyTab.setValue(TAB_Customer);
        
        scrnMsg.setFocusItem(scrnMsg.rcptChkNum_H);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;
        // START 2018/01/18 [QC#21406, MOD]
        scrnMsg.rcptChkNum_H.setNameForMessage("Receipt Number");
        // scrnMsg.rcptNum_H.setNameForMessage("Receipt Number");
        scrnMsg.rcptNum_H.setNameForMessage("Receipt Doc Number");
        // END   2018/01/18 [QC#21406, MOD]
        scrnMsg.arBatRcptNm_H.setNameForMessage("Batch Name");
        scrnMsg.rcptDt_H.setNameForMessage("Receipt Date");
        scrnMsg.arRcptSrcCd_H.setNameForMessage("Receipt Source");
        scrnMsg.arRcptTrxTpCd_H.setNameForMessage("Receipt Type");
        scrnMsg.arRcptStsCd_H.setNameForMessage("Receipt Status");
        scrnMsg.arRcptSrcCd_H.setNameForMessage("Receipt Source");
        scrnMsg.glDt_H.setNameForMessage("Account Date");
        scrnMsg.arRcptNoteTxt_H.setNameForMessage("Application Note");
        scrnMsg.xxTotAmt_H1.setNameForMessage("Receipt Amount");
        scrnMsg.arRcptRemDt_H.setNameForMessage("Remittance Date");
        scrnMsg.dsBankCd_H1.setNameForMessage("Bank");
        // scrnMsg.bankRteNum_H1.setNameForMessage("Bank Branch"); // DEL 2018/02/02 QC#21372
        scrnMsg.remDsBankAcctPk_H1.setNameForMessage("Account#");
        scrnMsg.crCardApvlCd_H.setNameForMessage("Approval Code");
        scrnMsg.pmtSvcOrdNum_H.setNameForMessage("Payment Service Order Number");
        scrnMsg.payerCustCd_H.setNameForMessage("Customer");
        scrnMsg.arRcptRefTxt_H.setNameForMessage("Reference");
        scrnMsg.arRcptCmntTxt_H.setNameForMessage("Comment");
        // START 2020/01/31 [QC#54826, ADD]
        scrnMsg.arRcptCmntTxt_H.setNameForMessage("Note");
        // END 2020/01/31 [QC#54826, ADD]
        scrnMsg.dsBankCd_H2.setNameForMessage("Bank");
        // scrnMsg.bankRteNum_H2.setNameForMessage("Branch"); // DEL 2018/02/02 QC#21372
        scrnMsg.custDsBankAcctPk_H2.setNameForMessage("Account");
        scrnMsg.voidDt_H.setNameForMessage("Date");
        scrnMsg.arRcptVoidRsnCd_H.setNameForMessage("Category");
        scrnMsg.arRcptRvrsRsnCd_H.setNameForMessage("Reason");
        scrnMsg.voidGlDt_H.setNameForMessage("GL Date");
        scrnMsg.arRcptRvrsCmntTxt_H.setNameForMessage("Comment");
        // START 2018/01/18 [QC#21372, ADD]
        scrnMsg.dsBankBrNm_H1.setNameForMessage("Remittance Branch Name");
        scrnMsg.dsBankBrNm_H2.setNameForMessage("Branch Name");
        scrnMsg.bankRteNum_H2.setNameForMessage("Routing Number");
        // END   2018/01/18 [QC#21372, ADD]
        scrnMsg.xxTotAmt_H1.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H2.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H3.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H4.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H5.setAppFracDigit(2);
    }
}
