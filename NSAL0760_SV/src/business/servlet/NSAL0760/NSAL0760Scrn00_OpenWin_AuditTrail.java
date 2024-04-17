/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0760;

import static business.servlet.NSAL0760.constant.NSAL0760Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Contract Status History
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class NSAL0760Scrn00_OpenWin_AuditTrail extends S21CommonHandler {

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

        NSAL0760BMsg scrnMsg = (NSAL0760BMsg) bMsg;

        int index = getButtonSelectNumber();

        Object[] params = new Object[AUDIT_TRAIL_PRM];
        int i = 0;
        params[i++] = scrnMsg.A.no(index).dsContrTrkTpCd;
        params[i++] = scrnMsg.dsContrPk;
        params[i++] = scrnMsg.A.no(index).dsContrDtlPk;
        params[i++] = scrnMsg.A.no(index).dsContrBllgMtrPk;
        params[i++] = scrnMsg.A.no(index).xxFromDt;

        setArgForSubScreen(params);
    }
}
