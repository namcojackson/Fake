/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3070;

import static business.servlet.NFCL3070.constant.NFCL3070Constant.BIZ_ID;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.FUNC_CD_SRCH;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.RQST_TP_BOTH;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.RQST_TP_CREDIT_ONLY;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3070.NFCL3070CMsg;
import business.servlet.NFCL3070.common.NFCL3070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NFCL3070_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/19   Fujitsu         S.Fujita        Update          N/A
 * 2018/05/30   Hitachi         E.Kameishi      Update          QC#26229
 * 2018/08/29   Fujitsu         S.Ohki          Update          QC#27887
 *</pre>
 */
public class NFCL3070_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL3070BMsg scrnMsg = (NFCL3070BMsg) bMsg;
        NFCL3070CMsg bizMsg = new NFCL3070CMsg();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            scrnMsg.origInvNum.setValue(param0.getValue());

        } else {
            // To process the menu transition
            scrnMsg.origInvNum.clear();
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFCL3070BMsg scrnMsg = (NFCL3070BMsg) bMsg;
        NFCL3070CMsg bizMsg = (NFCL3070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // set initial value to field
        // START 2018/05/30 E.Kameishi [QC#26299,MOD]
        //ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn, RQST_TP_BOTH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn, RQST_TP_CREDIT_ONLY);
        // END 2018/05/30 E.Kameishi [QC#26299,MOD]
        NFCL3070CommonLogic.setInitialValue(scrnMsg);

        // protect field
        NFCL3070CommonLogic.initCmnBtnField(this, scrnMsg);
        setAppFracDigit(scrnMsg);

        // START 2018/05/30 E.Kameishi [QC#26299,ADD]
        boolean protectKey = false;
        NFCL3070CommonLogic.protectCreditOnlySection(scrnMsg, protectKey);
        // END 2018/05/30 E.Kameishi [QC#26299,ADD]

        scrnMsg.setFocusItem(scrnMsg.xxRadioBtn);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NFCL3070BMsg scrnMsg = (NFCL3070BMsg) bMsg;

        // ### Request Type Section ###
        scrnMsg.xxRadioBtn.setNameForMessage("Request Type");

        // ### Parameters Section ###
        scrnMsg.custIncdtId.setNameForMessage("CI Number");
        scrnMsg.origInvNum.setNameForMessage("Customer Invoice");
        scrnMsg.arCrRebilRsnCd.setNameForMessage("Reason Code");
        scrnMsg.arCrRebilCmntTxt.setNameForMessage("Comments");
        scrnMsg.invNum_C.setNameForMessage("Credit Memo Number");
        scrnMsg.invNum_R.setNameForMessage("Rebill Invoice Number");
        scrnMsg.arCrRebilPk_C.setNameForMessage("Request ID");
        scrnMsg.arCrRebilAddlCmntTxt.setNameForMessage("Comments");

        // ### Credit Only Section ###
        scrnMsg.invLineNum.setNameForMessage("Line Number");
        scrnMsg.arCrTpCd.setNameForMessage("Type Of Credit");
        scrnMsg.arCrPct.setNameForMessage("Percentage Of Credit");
        scrnMsg.crRebilAmt_CO.setNameForMessage("Credit Amount");
        scrnMsg.arAutoCashAppFlg.setNameForMessage("Auto Apply");
        // START 2018/08/29 S.Ohki [QC#27887, MOD]
//        scrnMsg.invBolLineNum.setNameForMessage("BOL Line Number");
//        scrnMsg.invLineSubNum.setNameForMessage("Line Sub Number");
//        scrnMsg.invLineSubTrxNum.setNameForMessage("Line Sub Transaction Number");
        scrnMsg.invBolLineNum.setNameForMessage("Ship Line Number");
        // END 2018/08/29 S.Ohki [QC#27887, MOD]

    }

    private void setAppFracDigit(NFCL3070BMsg scrnMsg) {
        scrnMsg.arCrPct.setAppFracDigit(2); // Percentage Of Credit
        scrnMsg.crRebilAmt_CO.setAppFracDigit(2); // Credit Amount
    }

}
