/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0790;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Fleet Rollup Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL0790Scrn00_Entry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0790BMsg scrnMsg = (NSAL0790BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcMachMstrPk_P, scrnMsg.A.no(getButtonSelectNumber()).svcMachMstrPk_A);

        Object[] params = new Object[1];
        int i = 0;
        params[i++] = scrnMsg.svcMachMstrPk_P;

        setArgForSubScreen(params);
    }
}
