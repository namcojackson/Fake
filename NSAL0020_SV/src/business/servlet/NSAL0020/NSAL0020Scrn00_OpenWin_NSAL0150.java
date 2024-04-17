/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0020.NSAL0020CMsg;
//import business.servlet.NSAL0020.constant.NSAL0020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Hitachi         Y.Tsuchimoto    Update          N/A
 *</pre>
 */
public class NSAL0020Scrn00_OpenWin_NSAL0150 extends S21CommonHandler {

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
        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;

        Object[] params = new Object[1];

        int index = getButtonSelectNumber();

        params[0] = scrnMsg.A.no(index).svcMachMstrPk_A0;

        setArgForSubScreen(params);

    }
}
