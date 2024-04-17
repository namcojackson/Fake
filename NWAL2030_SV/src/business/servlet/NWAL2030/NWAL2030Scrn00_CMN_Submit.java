/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2030;

import static business.servlet.NWAL2030.constant.NWAL2030Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2030.NWAL2030CMsg;
import business.servlet.NWAL2030.common.NWAL2030CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Hold Set Up Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NWAL2030Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2030BMsg scrnMsg = (NWAL2030BMsg) bMsg;
        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.setMessageInfo(NWAM0794E);
            throw new EZDFieldErrorException();
        }
        NWAL2030CommonLogic.addCheckItemSubmit(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2030BMsg scrnMsg = (NWAL2030BMsg) bMsg;

        NWAL2030CMsg bizMsg = new NWAL2030CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2030BMsg scrnMsg = (NWAL2030BMsg) bMsg;
        NWAL2030CMsg bizMsg = (NWAL2030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2030CommonLogic.setupScreenItems(this, scrnMsg);

        NWAL2030CommonLogic.addCheckItemSubmit(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() != EZDMessageInfo.MSGTYPE_ERROR) {
            scrnMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
            this.setButtonEnabled(BTN_DELETE_LINE, false);
            scrnMsg.setFocusItem(scrnMsg.hldRsnCd);
        }

    }
}
