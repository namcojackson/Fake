/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.DESTINATION_WAREHOUSE_FOR_LINE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_12;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Open Return to Location Search Popup(NPAL1010)
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/16/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NPBL0020Scrn00_OpenWin_DestWHForLine extends S21CommonHandler {

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

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        int eventRowIndex = getButtonSelectNumber();

        // Initialization of subscreen delivery information
        NPBL0020CommonLogic.setInitParamForDestWarehouseForLinePopup(scrnMsg, eventRowIndex);

        // Making of subscreen delivery information
        Object[] params = NPBL0020CommonLogic.setParamForDestWarehouseForLinePopup(scrnMsg, eventRowIndex);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_12).xxPopPrm, DESTINATION_WAREHOUSE_FOR_LINE);

        // Subscreen transition
        setArgForSubScreen(params);

    }
}
