/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0150;

import static business.servlet.NSAL0150.constant.NSAL0150Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Meter Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Hitachi         M.Gotou         Create          QC#4398
 *</pre>
 */
public class NSAL0150Scrn00_MoveWin_MachMaint extends S21CommonHandler {

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
        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
        setValue(scrnMsg.svcMachMstrPk_P, scrnMsg.svcMachMstrPk);

        Object[] params = new Object[NSAL0010_PRM_LENGTH];
        params[0] = scrnMsg.svcMachMstrPk_P;

        setArgForSubScreen(params);
    }
}
