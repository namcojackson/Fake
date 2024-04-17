/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0270;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0270.common.NLCL0270CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/20/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class NLCL0270Scrn00_OpenWin_ItemMaster extends S21CommonHandler {

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

        NLCL0270BMsg scrnMsg = (NLCL0270BMsg) bMsg;

        Object[] params = NLCL0270CommonLogic.getParamNMAL6800(scrnMsg);
        setArgForSubScreen(params);
    }
}
