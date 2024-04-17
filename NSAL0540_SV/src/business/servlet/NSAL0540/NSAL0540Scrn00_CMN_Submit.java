/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0540;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0540.NSAL0540CMsg;
import business.servlet.NSAL0540.common.NSAL0540CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Solution Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public class NSAL0540Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0540BMsg scrnMsg = (NSAL0540BMsg) bMsg;
        NSAL0540CommonLogic.addCheckAllItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0540BMsg scrnMsg = (NSAL0540BMsg) bMsg;
        NSAL0540CMsg bizMsg = NSAL0540CommonLogic.createCMsgForUpdate();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0540BMsg scrnMsg = (NSAL0540BMsg) bMsg;
        NSAL0540CMsg bizMsg = (NSAL0540CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0540CommonLogic.screenControlProcess(this, scrnMsg);
        NSAL0540CommonLogic.addCheckAllItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.setFocusItem(scrnMsg.svcSlnNm);
    }
}
