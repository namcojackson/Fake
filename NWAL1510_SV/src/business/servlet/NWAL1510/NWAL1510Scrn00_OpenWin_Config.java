/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1510;

import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.getParamNWAL1130ForOrdConfig;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1510Scrn00_OpenWin_Config
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL1510Scrn00_OpenWin_Config extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;

        Object[] params = getParamNWAL1130ForOrdConfig(scrnMsg, getGlobalCompanyCode());
        setArgForSubScreen(params);
    }
}
