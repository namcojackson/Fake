/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/06   Fujitsu         T.Aoi           Create          N/A
 *</pre>
 */
public class NWAL2300Scrn00_MoveWin_OrderEntry_InvoiceCredit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2300BMsg scrnMsg = (NWAL2300BMsg) bMsg;
        int idx = this.getButtonSelectNumber();

        Object[] params = new Object[1];
        params[0] = scrnMsg.B.no(idx).cpoOrdNum_B1;

        setArgForSubScreen(params);

        openMultiModeScreen();
    }
}
