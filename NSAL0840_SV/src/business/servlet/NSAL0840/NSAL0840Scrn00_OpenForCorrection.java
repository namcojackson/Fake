/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0840;

import static business.servlet.NSAL0840.common.NSAL0840CommonLogic.initialControlScreen;
import static business.servlet.NSAL0840.constant.NSAL0840Constant.MODE_AFTER_OPEN;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL0840Scrn00_OpenForCorrection extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0840BMsg scrnMsg = (NSAL0840BMsg) bMsg;

        setValue(scrnMsg.xxModeCd, MODE_AFTER_OPEN);
        initialControlScreen(this, scrnMsg);
    }
}
