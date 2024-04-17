/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NLCL0280.common.NLCL0280CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * OpenWin_LocInfo Inventory Transaction Inqiury
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   CITS            T.Tokutomi      Create          N/A
 *</pre>
 */
public class NLCL0280Scrn00_OpenWin_LocInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // /No Process.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;

        // set popup parameter
        Object[] params = NLCL0280CommonLogic.setLocationInfoParam(scrnMsg);

        // send popup parameter
        setArgForSubScreen(params);
    }
}