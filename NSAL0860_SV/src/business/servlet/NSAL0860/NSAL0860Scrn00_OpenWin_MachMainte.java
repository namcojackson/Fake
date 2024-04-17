/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0860;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/06/2016   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public class NSAL0860Scrn00_OpenWin_MachMainte extends S21CommonHandler {

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

        NSAL0860BMsg scrnMsg = (NSAL0860BMsg) bMsg;
        int idex = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcMachMstrPk_P, scrnMsg.A.no(idex).svcMachMstrPk_A);

        Object[] params = new Object[1];
        params[0] = scrnMsg.svcMachMstrPk_P;

        setArgForSubScreen(params);

        openMultiModeScreen();
    }
}
