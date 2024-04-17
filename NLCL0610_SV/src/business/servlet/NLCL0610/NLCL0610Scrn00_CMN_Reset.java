/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0610;

import static business.servlet.NLCL0610.constant.NLCL0610Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NLCL0610.NLCL0610CMsg;
import business.servlet.NLCL0610.common.NLCL0610CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * PI Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NLCL0610Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0610BMsg scrnMsg = (NLCL0610BMsg) bMsg;
        scrnMsg.clear();

        return NLCL0610CommonLogic.copyScrnMsgToBizMsgForSearch(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // copy bizMsg to scrnMsg.
        NLCL0610BMsg scrnMsg = (NLCL0610BMsg) bMsg;
        EZDMsg.copy((NLCL0610CMsg) cMsg, null, scrnMsg, null);

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        // set the button properties.
        NLCL0610CommonLogic.initializeButtonProperties(this);

        // Protect
        scrnMsg.rtlWhNm.setInputProtected(true);
        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
    }
}
