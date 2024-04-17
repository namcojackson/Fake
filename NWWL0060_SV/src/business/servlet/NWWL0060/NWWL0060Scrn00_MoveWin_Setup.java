/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0060Scrn00_MoveWin_Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/05   Fujitsu         Q10253          Create          N/A
 *</pre>
 */
public class NWWL0060Scrn00_MoveWin_Setup extends S21CommonHandler {

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

        NWWL0060BMsg scrnMsg = (NWWL0060BMsg) bMsg;

        Object[] params = new Object[1];
        params[0] = scrnMsg.A.no(getButtonSelectNumber()).ntfyHdrId_A.getValue();
        setArgForSubScreen(params);

    }
}
