/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLEL0080.common.NLEL0080CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/04   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NLEL0080Scrn00_OpenWin_SR extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0080BMsg scrnMsg = (NLEL0080BMsg) bMsg;

        int index = getButtonSelectNumber();
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        Object[] params = NLEL0080CommonLogic.getParamNWAL1130ForSlsrep(scrnMsg, index);
        scrnMsg.P.setValidCount(1);
        setArgForSubScreen(params);
    }
}
