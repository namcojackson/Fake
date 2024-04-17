/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.OPEN_WIN_SHIP_TO_CUST_D;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : OpenWin_ShipToCustD
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/05/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NPBL0020Scrn00_OpenWin_ShipToCustD extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(OPEN_WIN_SHIP_TO_CUST_D);
        Object[] params = NPBL0020CommonLogic.setParamForShipToCustPopup(scrnMsg, getButtonSelectNumber());

        // Subscreen transition
        setArgForSubScreen(params);

    }
}
