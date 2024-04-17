/*
 * \ <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0510;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0510.NFCL0510CMsg;
import business.servlet.NFCL0510.common.NFCL0510CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Lock box Error Correction Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NFCL0510_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFCL0510");
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0510BMsg scrnMsg = (NFCL0510BMsg) bMsg;

        NFCL0510CMsg bizMsg = new NFCL0510CMsg();
        bizMsg.setBusinessID("NFCL0510");
        bizMsg.setFunctionCode("20");

        // START 2018/01/23 [QC#19728, ADD]
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            if (params.length == 1) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.arBatRcptNm_P, param01);
           }
        }
        // END   2018/01/23 [QC#19728, ADD]

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0510BMsg scrnMsg = (NFCL0510BMsg) bMsg;
        NFCL0510CMsg bizMsg = (NFCL0510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFCL0510CommonLogic.initialize(this, scrnMsg, getUserProfileService());
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NFCL0510BMsg scrnMsg = (NFCL0510BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).lockBoxTotAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).batRcptTotAmt_A.setAppFracDigit(2);
        }
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).custInvAmt_B.setAppFracDigit(2);
            scrnMsg.B.no(i).custRcptAmt_B.setAppFracDigit(2);
        }
    }
}
