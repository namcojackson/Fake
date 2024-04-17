/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3030.NFCL3030CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/07   Fujitsu         T.Murai         Update          S21_NA#21372
 *</pre>
 */
public class NFCL3030Scrn00_CustomerName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.payerCustCd_H);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

        NFCL3030CMsg bizMsg = new NFCL3030CMsg();
        bizMsg.setBusinessID("NFCL3030");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;
        NFCL3030CMsg bizMsg = (NFCL3030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.payerCustCd_H);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.payerCustCd_H);
    }
}
