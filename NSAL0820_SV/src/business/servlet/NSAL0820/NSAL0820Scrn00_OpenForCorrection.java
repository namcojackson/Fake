/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0820;

import static business.servlet.NSAL0820.common.NSAL0820CommonLogic.initialControlScreen;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NSAL0820.constant.NSAL0820Constant.MODE_AFTER_OPEN;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
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
public class NSAL0820Scrn00_OpenForCorrection extends S21CommonHandler {

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

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, MODE_AFTER_OPEN);
        initialControlScreen(this, scrnMsg);

    }
}
