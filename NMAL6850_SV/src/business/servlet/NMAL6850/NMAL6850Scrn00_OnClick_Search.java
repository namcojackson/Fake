/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6850;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6850.NMAL6850CMsg;
import business.servlet.NMAL6850.common.NMAL6850CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID   : NMAL6850 Supplier Search
 * Function Name : The business process for Search.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/23   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NMAL6850Scrn00_OnClick_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do Nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6850BMsg scrnMsg = (NMAL6850BMsg) bMsg;

        // reset paging.
        NMAL6850CommonLogic.resetPegingView(scrnMsg);

        return NMAL6850CommonLogic.copyScrnMsgToBizMsgForSearch(scrnMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6850BMsg scrnMsg = (NMAL6850BMsg) bMsg;
        EZDMsg.copy((NMAL6850CMsg) cMsg, null, scrnMsg, null);

        // set the display name.
        NMAL6850CommonLogic.setNameForMessage(scrnMsg);

        NMAL6850CommonLogic.setTableScreen(scrnMsg);

        // focus on Supplier Number.
        scrnMsg.setFocusItem(scrnMsg.prntVndCd_H);
    }
}
