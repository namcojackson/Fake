/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0010;

import static business.servlet.NPBL0010.constant.NPBL0010Constant.DESTINATION_SUB_WAREHOUSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPBL0010.NPBL0010CMsg;
//import business.servlet.NPBL0010.constant.NPBL0010Constant;

import business.servlet.NPBL0010.common.NPBL0010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NPBL0010 Inventory Request List
 * Function Name : Open Return to Location Search Popup(NPAL1010)
 * </pre>
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/19/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NPBL0010Scrn00_OpenWin_DestSWH extends S21CommonHandler {

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

        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;

        // Initialization of subscreen delivery information
        NPBL0010CommonLogic.setInitParamForDestWarehousePopup(scrnMsg);

        // Making of subscreen delivery information
        Object[] params = NPBL0010CommonLogic.setParamForDestWarehousePopup(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(12).xxPopPrm, DESTINATION_SUB_WAREHOUSE);

        // Subscreen transition
        setArgForSubScreen(params);

    }
}