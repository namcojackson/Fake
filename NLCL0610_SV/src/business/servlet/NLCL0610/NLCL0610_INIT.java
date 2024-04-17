/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0610.NLCL0610CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

import business.servlet.NLCL0610.common.NLCL0610CommonLogic;
import static business.servlet.NLCL0610.constant.NLCL0610Constant.BIZ_ID;

/**
 * <pre>
 * PI Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NLCL0610_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0610BMsg scrnMsg = (NLCL0610BMsg) bMsg;

        return NLCL0610CommonLogic.copyScrnMsgToBizMsgForSearch(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // copies bizMsg to scrnMsg.
        NLCL0610BMsg scrnMsg = (NLCL0610BMsg) bMsg;
        EZDMsg.copy((NLCL0610CMsg) cMsg, null, scrnMsg, null);

        // sets the button properties.
        NLCL0610CommonLogic.initializeButtonProperties(this);

        // Protect
        scrnMsg.rtlWhNm.setInputProtected(true);
        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLCL0610CommonLogic.setDisplayNameForMessage((NLCL0610BMsg) bMsg);
    }
}
