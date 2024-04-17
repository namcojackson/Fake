/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         T.Murai         Create          N/A
 * 2016/11/15   Fujitsu         H.Ikeda         Update          S21_NA#15875
 *</pre>
 */
public class NWAL1840Scrn00_OpenWin_Contract extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do Nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        // S21_NA#15875 Mod Start
        //setArgForSubScreen(NWAL1840CommonLogic.getParamNSAL1240(scrnMsg));
        setArgForSubScreen(NWAL1840CommonLogic.setParamNSAL0110(scrnMsg));
        // S21_NA#15875 Mod End

    }
}
