/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0800;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Hitachi         Y.Tsuchimoto    Update          N/A
 *</pre>
 */
public class NSAL0800Scrn00_OpenWin_Account extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0800BMsg scrnMsg = (NSAL0800BMsg) bMsg;

        Object[] params = new Object[2];

        params[0] = scrnMsg.leaseCmpyCd_A1;
        params[1] = scrnMsg.dsAcctNm_A1;

        setArgForSubScreen(params);

    }
}
