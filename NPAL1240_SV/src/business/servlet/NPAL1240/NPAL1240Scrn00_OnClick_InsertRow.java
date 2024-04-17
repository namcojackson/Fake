/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1240;

import static business.servlet.NPAL1240.common.NPAL1240CommonLogic.activateButton;
import static business.servlet.NPAL1240.common.NPAL1240CommonLogic.copyScrnMsgToBizMsgForSearch;
import static business.servlet.NPAL1240.common.NPAL1240CommonLogic.setDisplayName;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1240.NPAL1240CMsg;
import business.servlet.NPAL1240.constant.NPAL1240Constant.BTN_APP;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID   : NPAL1240 Qualifier Setup
 * Function Name : The business process for OnClick_InsertRow.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/25   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NPAL1240Scrn00_OnClick_InsertRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return copyScrnMsgToBizMsgForSearch((NPAL1240BMsg) bMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1240BMsg scrnMsg = (NPAL1240BMsg) bMsg;
        EZDMsg.copy((NPAL1240CMsg) cMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            return;
        }

        // sets the display name.
        setDisplayName(scrnMsg);

        // activates the button of "Delete Row".
        activateButton(BTN_APP.DELETE_ROW, this);

        // focuses on Qualifier Type.
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).aslQlfyTpCd_A);
    }
}
