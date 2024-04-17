/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1120;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.servlet.NSAL1120.constant.NSAL1120Constant.PARAM_LENGTH_NSAL1130;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NSAL1120Scrn00_OpenWin_SerNumSrch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1120BMsg scrnMsg = (NSAL1120BMsg) bMsg;
        int eventLine = getButtonSelectNumber();

        setValue(scrnMsg.svcMachMstrPk_P, scrnMsg.B.no(eventLine).svcMachMstrPk_B);
        scrnMsg.mtrLbCd_P.clear();

        Object[] params = new Object[PARAM_LENGTH_NSAL1130];

        int i = 0;
        params[i++] = scrnMsg.svcMachMstrPk_P;
        params[i++] = scrnMsg.mtrLbCd_P;
        setArgForSubScreen(params);
    }
}
