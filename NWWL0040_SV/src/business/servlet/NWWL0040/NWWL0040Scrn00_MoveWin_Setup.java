/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0040Scrn00_MoveWin_Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/27   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0040Scrn00_MoveWin_Setup extends S21CommonHandler {

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
        NWWL0040BMsg scrnMsg = (NWWL0040BMsg) bMsg;
        int selectNum = getButtonSelectNumber();
        String distId = null;
        getArgForSubScreen();
        if (selectNum >= 0) {
            NWWL0040_ABMsg abMsg = scrnMsg.A.no(selectNum);
            distId = abMsg.ntfyDistListId_A.getValue();
        }

        Object[] params = new Object[1];
        params[0] = distId;
        setArgForSubScreen(params);
    }
}
