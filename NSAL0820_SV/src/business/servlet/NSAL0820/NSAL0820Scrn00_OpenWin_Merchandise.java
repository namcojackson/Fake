/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0820;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NSAL0820.constant.NSAL0820Constant.*;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Actual Counters for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         T.Iwamoto         Create          N/A
 *</pre>
 */
public class NSAL0820Scrn00_OpenWin_Merchandise extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0820BMsg scrnMsg = (NSAL0820BMsg) bMsg;

        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();

        Object[] params = new Object[LENGTH_7];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_00;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        setArgForSubScreen(params);

    }
}
