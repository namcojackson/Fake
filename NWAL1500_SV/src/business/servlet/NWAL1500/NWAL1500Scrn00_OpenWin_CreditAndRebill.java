/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_0;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_1;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_CreditAndRebill extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing

        // TODO to remove later
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        scrnMsg.setMessageInfo("ZZXM0001E", new String[] {"This event is not available." });
        throw new EZDFieldErrorException();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        // set param
        Object[] params = new Object[IDX_1];
        params[IDX_0] = scrnMsg.cpoOrdNum;
        setArgForSubScreen(params);
        openMultiModeScreen();
    }
}
