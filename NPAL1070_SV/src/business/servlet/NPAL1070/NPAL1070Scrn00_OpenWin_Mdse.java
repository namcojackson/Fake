/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NPAL1070.common.NPAL1070CommonLogic;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1070 Min-Max Planning Entry
 * Function Name : Open Window Merchandise
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/24/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1070Scrn00_OpenWin_Mdse extends S21CommonHandler {

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

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;

        // Initialization of sub screen delivery information
        NPAL1070CommonLogic.setInitParamForItemMasterPopup(scrnMsg);

        // Making of sub screen delivery information
        Object[] params = NPAL1070CommonLogic.setParamForItemMasterPopup(scrnMsg);

        // Sub screen transition
        setArgForSubScreen(params);

    }
}
