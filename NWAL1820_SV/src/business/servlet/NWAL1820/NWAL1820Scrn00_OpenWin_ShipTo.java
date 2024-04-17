/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1820;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL1820.common.NWAL1820CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1820Scrn00_OpenWin_ShipTo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1820Scrn00_OpenWin_ShipTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1820BMsg scrnMsg = (NWAL1820BMsg) bMsg;

        Object[] params = NWAL1820CommonLogic.getParamNMAL6760ForShipTo(scrnMsg);
        setArgForSubScreen(params);
    }
}
