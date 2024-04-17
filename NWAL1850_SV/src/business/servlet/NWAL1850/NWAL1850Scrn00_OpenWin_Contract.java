/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1850;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1850.common.NWAL1850CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1850Scrn00_OpenWin_Contract
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/06/02   Hitachi         K.Kitachi       Create          QC#60037
 *</pre>
 */
public class NWAL1850Scrn00_OpenWin_Contract extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1850BMsg scrnMsg = (NWAL1850BMsg) bMsg;
        Object[] params = NWAL1850CommonLogic.getParamNSAL0110(scrnMsg);
        setArgForSubScreen(params);
    }
}
