/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2340;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2340.common.NWAL2340CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * SOM Address Mass Apply Open Win ShipTo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/22   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NWAL2340Scrn00_OpenWin_ShipTo_H extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2340BMsg scrnMsg = (NWAL2340BMsg) bMsg;
        scrnMsg.xxScrEventNm_H.setValue(ctx.getEventName());

        Object[] params = NWAL2340CommonLogic.getParamNMAL6760ForShipTo(scrnMsg);
        setArgForSubScreen(params);
    }
}
