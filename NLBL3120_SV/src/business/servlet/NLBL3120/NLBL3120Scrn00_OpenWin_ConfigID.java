/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         S.Yoshida       Create          N/A
 *</pre>
 */
public class NLBL3120Scrn00_OpenWin_ConfigID extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;
        scrnMsg.svcConfigMstrPk_P.clear();

        Object[] params = new Object[31];
        params[1] = scrnMsg.svcConfigMstrPk;
        params[29] = scrnMsg.svcConfigMstrPk_P;
        setArgForSubScreen(params);
    }

}
