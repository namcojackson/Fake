/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.servlet.ZZIL0110;

import parts.common.*;
import parts.servletcommon.*;

import business.servlet.ZZIL0110.constant.ZZIL0110Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZIL0110Scrn00_CMN_Return extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZIL0110BMsg scrnMsg = (ZZIL0110BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZIL0110BMsg scrnMsg = (ZZIL0110BMsg) bMsg;

        // ZZIL0110CMsg bizMsg = new ZZIL0110CMsg();
        // bizMsg.setBusinessID("ZZIL0110");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // ZZIL0110BMsg scrnMsg = (ZZIL0110BMsg) bMsg;
        // ZZIL0110CMsg bizMsg = (ZZIL0110CMsg) cMsg;

        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }

}
