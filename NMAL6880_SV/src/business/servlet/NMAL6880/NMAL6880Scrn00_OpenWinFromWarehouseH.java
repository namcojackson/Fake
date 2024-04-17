/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6880;

import static business.servlet.NMAL6880.constant.NMAL6880Constant.OPEN_WIN_FROM_WH_H;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6880.common.NMAL6880CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NMAL6880 TPC09 WH Mapping Maintenance
 * Function Name : OpenWinFromWarehouseH
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/09/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NMAL6880Scrn00_OpenWinFromWarehouseH extends S21CommonHandler {

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
        NMAL6880BMsg scrnMsg = (NMAL6880BMsg) bMsg;
        Object[] params = NMAL6880CommonLogic.setParamForWarehousePopup(scrnMsg, OPEN_WIN_FROM_WH_H, getButtonSelectNumber());
        if (params != null) {
            setArgForSubScreen(params);
        }
    }
}
