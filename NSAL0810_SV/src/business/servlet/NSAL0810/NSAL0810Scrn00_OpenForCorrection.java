/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0810;

import static business.servlet.NSAL0810.common.NSAL0810CommonLogic.initialControlScreen;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NSAL0810.constant.NSAL0810Constant.MODE_AFTER_OPEN;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Interface Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL0810Scrn00_OpenForCorrection extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, MODE_AFTER_OPEN);
        initialControlScreen(this, scrnMsg);

    }
}
