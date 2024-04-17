/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1430;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPAL1430.NPAL1430CMsg;
//import business.servlet.NPAL1430.constant.NPAL1430Constant;

import business.servlet.NPAL1430.common.NPAL1430CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NPAL1430 Reman Standard Parts Setup
 * Function Name : Open Return to Item Search Popup(NMAL6800)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/18/2016   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NPAL1430Scrn00_OpenWin_Item extends S21CommonHandler {

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

        NPAL1430BMsg scrnMsg = (NPAL1430BMsg) bMsg;

        // set popup parameter
        int selectedNum = getButtonSelectNumber();
        Object[] params = NPAL1430CommonLogic.setMdseInfoParam(scrnMsg, selectedNum);

        // Subscreen transition
        setArgForSubScreen(params);
    }
}
