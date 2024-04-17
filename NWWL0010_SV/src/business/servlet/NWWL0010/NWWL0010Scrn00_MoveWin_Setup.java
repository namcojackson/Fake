/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0010Scrn00_MoveWin_Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/19   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0010Scrn00_MoveWin_Setup extends S21CommonHandler {

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

        NWWL0010BMsg scrnMsg = (NWWL0010BMsg) bMsg;

        String notifId = null;

        if (getButtonSelectNumber() >= 0) {
            NWWL0010_ABMsg abMsg = scrnMsg.A.no(getButtonSelectNumber());
            notifId = abMsg.ntfyHdrId_A0.getValue();
        }

        Object[] params = new Object[1];
        params[0] = notifId;
        setArgForSubScreen(params);
    }
}
