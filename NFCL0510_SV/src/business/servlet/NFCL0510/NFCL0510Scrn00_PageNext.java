/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0510.NFCL0510CMsg;
import business.servlet.NFCL0510.common.NFCL0510CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.http.HttpDispatchContext;

/**
 *<pre>
 * Lock box Error Correction Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NFCL0510Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0510BMsg scrnMsg = (NFCL0510BMsg) bMsg;
        NFCL0510CMsg bizMsg = new NFCL0510CMsg();
        bizMsg.setBusinessID("NFCL0510");
        bizMsg.setFunctionCode("20");
        String tblName = (String) (((HttpDispatchContext) ctx.getDispatchContext()).getParameters().get("xxPagenationTableNm"))[0];
        scrnMsg.xxPageTblNm.setValue(tblName);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0510BMsg scrnMsg = (NFCL0510BMsg) bMsg;
        NFCL0510CMsg bizMsg = (NFCL0510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFCL0510CommonLogic.setProtected(this, scrnMsg, getUserProfileService());
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).arLockBoxStsCd_A);
        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).custRcptAmt_B);
        }
        scrnMsg.putErrorScreen();
    }
}
