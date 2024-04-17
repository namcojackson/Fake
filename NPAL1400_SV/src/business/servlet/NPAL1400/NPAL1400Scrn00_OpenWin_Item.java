/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1400;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1400.common.NPAL1400CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NPAL1400 Reman Inquiry
 * Function Name : Open Return to Item Search Popup(NMAL6800)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/08/2016   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NPAL1400Scrn00_OpenWin_Item extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1400BMsg scrnMsg = (NPAL1400BMsg) bMsg;

        // set popup parameter
        Object[] params = NPAL1400CommonLogic.setMdseInfoParam(scrnMsg);

        // Subscreen transition
        setArgForSubScreen(params);

    }
}