/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1510.common.NPAL1510CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1510 PO Search
 * Function Name : OpenWin_ShipToCust
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   CUSA            K.Ogino         Create          N/A
 *</pre>
 */
public class NPAL1510Scrn00_OpenWin_ShipToCust extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;
        Object[] params = NPAL1510CommonLogic.setParamForShipToCustPopup(scrnMsg);
        setArgForSubScreen(params);

    }
}
