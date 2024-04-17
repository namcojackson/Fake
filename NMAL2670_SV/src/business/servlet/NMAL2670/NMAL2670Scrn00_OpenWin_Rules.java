/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2670;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   CUSA            Fujitsu         Create          N/A
 * 2016/10/04   Fujitsu         C.Yokoi         Update          CSA-QC#14961
 *</pre>
 */
public class NMAL2670Scrn00_OpenWin_Rules extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2670BMsg scrnMsg = (NMAL2670BMsg) bMsg;
        int index = getButtonSelectNumber();

        // 2016/10/04 CSA-QC#14961 Mod Start
        Object[] params = new Object[2];
        params[0] = scrnMsg.A.no(index).trtyRulePk_A1;
        params[1] = null;
        // Object[] params = new Object[1];
        // params[0] = scrnMsg.A.no(index).trtyRulePk_A1;
        // 2016/10/04 CSA-QC#14961 Mod End

        setArgForSubScreen(params);
    }
}
