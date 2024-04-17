/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1250;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1250.NPAL1250CMsg;
import business.servlet.NPAL1250.common.NPAL1250CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Big Deal Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NPAL1250Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1250BMsg scrnMsg = (NPAL1250BMsg) bMsg;

        return NPAL1250CommonLogic.copyScrnMsgToBizMsgForUpdate(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1250BMsg scrnMsg = (NPAL1250BMsg) bMsg;
        NPAL1250CMsg bizMsg = (NPAL1250CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            // Add Check Item for Address Validation
            for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(index).bigDealNum_A);
            }
            scrnMsg.putErrorScreen();
            return;
        }
    }
}
