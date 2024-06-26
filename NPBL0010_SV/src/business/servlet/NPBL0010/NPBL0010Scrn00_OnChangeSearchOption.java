/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0010;

import static business.servlet.NPBL0010.constant.NPBL0010Constant.BIZ_APP_ID;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0010.NPBL0010CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0010 Inventory Request List
 * Function Name : Pulldown on change action to search
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/18/2016   CITS            Makoto Okigami  Create          N/A
 */
public class NPBL0010Scrn00_OnChangeSearchOption extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;
        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        NPBL0010CMsg bizMsg = new NPBL0010CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;
        NPBL0010CMsg bizMsg = (NPBL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.srchOptPk_SL.isError()) {
            scrnMsg.addCheckItem(scrnMsg.srchOptPk_SL);
            scrnMsg.putErrorScreen();
        } else {
            // Set Focus
            scrnMsg.setFocusItem(scrnMsg.srchOptPk_SL);
        }

    }
}
