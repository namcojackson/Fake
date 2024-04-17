/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0600;

import static business.servlet.NSAL0600.common.NSAL0600CommonLogic.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0600.NSAL0600CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Cascade Date
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         T.Tomita        Create          N/A
 *</pre>
 */
public class NSAL0600Scrn00_SelectAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0600BMsg scrnMsg = (NSAL0600BMsg) bMsg;
        NSAL0600CMsg bizMsg = createCMsgForSearch();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0600BMsg scrnMsg = (NSAL0600BMsg) bMsg;
        NSAL0600CMsg bizMsg = (NSAL0600CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        screenControlProcess(this, scrnMsg);
        addCheckAllItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A);
    }
}
