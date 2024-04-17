/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.servlet.ZZIL0120;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZIL0120.ZZIL0120CMsg;
import business.servlet.ZZIL0120.constant.ZZIL0120Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZIL0120Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZIL0120BMsg scrnMsg = (ZZIL0120BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0120BMsg scrnMsg = (ZZIL0120BMsg) bMsg;

        ZZIL0120CMsg bizMsg = new ZZIL0120CMsg();
        bizMsg.setBusinessID("ZZIL0120");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0120BMsg scrnMsg = (ZZIL0120BMsg) bMsg;
        ZZIL0120CMsg bizMsg = (ZZIL0120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.setFocusItem(scrnMsg.itrlIntfcId);
    }
}
