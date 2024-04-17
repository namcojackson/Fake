/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1250;

import static business.servlet.NPAL1250.constant.NPAL1250Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1250.NPAL1250CMsg;
import business.servlet.NPAL1250.common.NPAL1250CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Big Deal Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NPAL1250_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1250BMsg scrnMsg = (NPAL1250BMsg) bMsg;

        return NPAL1250CommonLogic.copyScrnMsgToBizMsgForSearch(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // copies bizMsg to scrnMsg.
        NPAL1250BMsg scrnMsg = (NPAL1250BMsg) bMsg;
        EZDMsg.copy((NPAL1250CMsg) cMsg, null, scrnMsg, null);

        // sets the button properties.
        NPAL1250CommonLogic.initializeButtonProperties(this);

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.sellToCustCd);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NPAL1250CommonLogic.setDisplayNameForMessage((NPAL1250BMsg) bMsg);
    }

}
