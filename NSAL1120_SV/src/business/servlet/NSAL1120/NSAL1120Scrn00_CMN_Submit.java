/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1120;


import static business.servlet.NSAL1120.common.NSAL1120CommonLogic.addCheckItem;
import static business.servlet.NSAL1120.common.NSAL1120CommonLogic.createCMsgForUpdate;
import static business.servlet.NSAL1120.common.NSAL1120CommonLogic.initialControlScreen;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1120.NSAL1120CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NSAL1120Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1120BMsg scrnMsg = (NSAL1120BMsg) bMsg;
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1120BMsg scrnMsg = (NSAL1120BMsg) bMsg;

        NSAL1120CMsg bizMsg = createCMsgForUpdate();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1120BMsg scrnMsg = (NSAL1120BMsg) bMsg;
        NSAL1120CMsg bizMsg  = (NSAL1120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        initialControlScreen(this, scrnMsg);
    }
}
