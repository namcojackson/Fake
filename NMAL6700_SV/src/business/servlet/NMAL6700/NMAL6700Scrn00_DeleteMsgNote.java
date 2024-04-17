/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6700.NMAL6700CMsg;
import business.servlet.NMAL6700.common.NMAL6700CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/24   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NMAL6700Scrn00_DeleteMsgNote extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        NMAL6700CMsg bizMsg = new NMAL6700CMsg();
        bizMsg.setBusinessID("NMAL6700");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        NMAL6700CMsg bizMsg = (NMAL6700CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6700CommonLogic.checkAuthForMsgNote(scrnMsg, this);
        NMAL6700CommonLogic.setBgColor(scrnMsg);
        NMAL6700CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());

        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (scrnMsg.J.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.J.no(0).xxChkBox_J1);
        }
    }
}
