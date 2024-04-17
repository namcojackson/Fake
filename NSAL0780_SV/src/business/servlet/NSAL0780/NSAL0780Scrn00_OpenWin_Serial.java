/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0780;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0780.common.NSAL0780CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Fleet Rollup Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL0780Scrn00_OpenWin_Serial extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0780BMsg scrnMsg = (NSAL0780BMsg) bMsg;
        NSAL0780CommonLogic.clearPopupParameter(scrnMsg);
        Object[] params = new Object[31];
        params[2] = scrnMsg.serNum_H;
        params[30] = scrnMsg.O;
        setArgForSubScreen(params);
    }
}
