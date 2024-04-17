/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0540;

import static business.servlet.NSAL0540.constant.NSAL0540Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Solution Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 * 2016/04/21   Hitachi         T.Tomita        Update          QC#6399
 * 2016/05/11   Hitachi         T.Tomita        Update          QC#7832
 *</pre>
 */
public class NSAL0540Scrn00_LinkConfig extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0540BMsg scrnMsg = (NSAL0540BMsg) bMsg;

        // START 2016/05/11 T.Tomita [QC#7832, MOD]
        // Set Parameters
        scrnMsg.svcConfigMstrPk_O.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_O, NSAL1240_MODE_02);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_VD, NSAL1240_MODE_02);

        Object[] params = new Object[NSAL1240_PARAM_CNT];
        params[PARAM_NUM_0] = scrnMsg.xxModeCd_O;
        params[PARAM_NUM_29] = scrnMsg.svcConfigMstrPk_O;
        params[PARAM_NUM_31] = scrnMsg.xxModeCd_VD;
        params[PARAM_NUM_32] = scrnMsg.P;
        setArgForSubScreen(params);
        // END 2016/05/11 T.Tomita [QC#7832, MOD]
    }
}
