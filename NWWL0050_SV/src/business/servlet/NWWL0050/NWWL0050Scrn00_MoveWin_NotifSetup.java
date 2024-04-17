/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0050Scrn00_MoveWin_NotifSetup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/05   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0050Scrn00_MoveWin_NotifSetup extends S21CommonHandler {

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

        NWWL0050BMsg scrnMsg = (NWWL0050BMsg) bMsg;

        Object[] params = new Object[1];
        params[0] = scrnMsg.B.no(getButtonSelectNumber()).ntfyHdrId_B.getValue();
        setArgForSubScreen(params);

    }
}
