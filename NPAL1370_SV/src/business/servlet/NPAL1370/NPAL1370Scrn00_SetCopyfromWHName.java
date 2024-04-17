/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1370;

import static business.servlet.NPAL1370.constant.NPAL1370Constant.BIZ_APP_ID;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1370.NPAL1370CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1370 Min Max Planning Copy Popup
 * Function Name :  Set Copy from WH Name
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2015   CITS       Takeshi Tokutomi     Create          N/A
 *</pre>
 */
public class NPAL1370Scrn00_SetCopyfromWHName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1370BMsg scrnMsg = (NPAL1370BMsg) bMsg;

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_FR);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1370BMsg scrnMsg = (NPAL1370BMsg) bMsg;

        NPAL1370CMsg bizMsg = new NPAL1370CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1370BMsg scrnMsg = (NPAL1370BMsg) bMsg;
        NPAL1370CMsg bizMsg = (NPAL1370CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        if (scrnMsg.rtlWhCd_FR.isError()) {
            scrnMsg.addCheckItem(scrnMsg.rtlWhCd_FR);
            scrnMsg.putErrorScreen();
        } else {
            scrnMsg.setFocusItem(scrnMsg.rtlWhCd_FR);
        }
    }
}
