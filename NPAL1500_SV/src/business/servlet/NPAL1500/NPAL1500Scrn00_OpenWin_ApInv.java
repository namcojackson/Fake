/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/25   CITS            K.Ogino        Create          N/A
 *</pre>
 */
public class NPAL1500Scrn00_OpenWin_ApInv extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        Object[] params = new Object[3];
        params[0] = scrnMsg.B.no(getButtonSelectNumber()).apVndCd_B1;
        params[1] = scrnMsg.B.no(getButtonSelectNumber()).apVndInvNum_B1;
        params[2] = scrnMsg.B.no(getButtonSelectNumber()).apVndInvSqNum_B1;
        setArgForSubScreen(params);

    }
}
