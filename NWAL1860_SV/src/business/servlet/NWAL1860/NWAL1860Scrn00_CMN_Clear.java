/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1860;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1860.common.NWAL1860CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1860Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1860Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1860BMsg scrnMsg = (NWAL1860BMsg) bMsg;

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length == 8) {
            NWAL1860CommonLogic.setInputParam(scrnMsg, params);
        } else {
            scrnMsg.setMessageInfo("NWAM0270E");
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1860BMsg scrnMsg = (NWAL1860BMsg) bMsg;

        NWAL1860CommonLogic.initCmnBtnProp(this);
        NWAL1860CommonLogic.controlScreenFields(scrnMsg);
        NWAL1860CommonLogic.clear(scrnMsg, (Object[]) getArgForSubScreen());
        scrnMsg.setFocusItem(scrnMsg.ordQty);
    }
}
