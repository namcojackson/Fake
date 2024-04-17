/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0150;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/29   Hitachi         M.Gotou         Create          N/A
 *</pre>
 */
public class NSAL0150Scrn00_OpenWin_ScheduleAgreement extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.schdAgmtNum_P, scrnMsg.schdAgmtNum);

        Object[] prm = new Object[1];
        prm[0] = scrnMsg.schdAgmtNum_P;

        setArgForSubScreen(prm);

    }
}
