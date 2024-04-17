/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.servlet.ZZIL0100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZIL0100.common.ZZIL0100CommonLogic;
import business.servlet.ZZIL0100.constant.ZZIL0100Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZIL0100Scrn00_OpenWin_ItrlIntfcId extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0100BMsg scrnMsg = (ZZIL0100BMsg) bMsg;
        // ZZIL0100CMsg bizMsg = (ZZIL0100CMsg) cMsg;

        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

        Object[] params = ZZIL0100CommonLogic.createZZIL0120Parameters(scrnMsg);
        setArgForSubScreen(params);

    }

}
