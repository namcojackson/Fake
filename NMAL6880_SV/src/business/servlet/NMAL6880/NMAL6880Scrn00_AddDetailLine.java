/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6880;

import static business.servlet.NMAL6880.constant.NMAL6880Constant.BIZ_APP_ID;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6880.NMAL6880CMsg;
import business.servlet.NMAL6880.common.NMAL6880CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NMAL6880 TPC09 WH Mapping Maintenance
 * Function Name : AddDetailLine
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/09/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NMAL6880Scrn00_AddDetailLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6880BMsg scrnMsg = (NMAL6880BMsg) bMsg;
        NMAL6880CMsg bizMsg = new NMAL6880CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6880BMsg scrnMsg = (NMAL6880BMsg) bMsg;
        NMAL6880CMsg bizMsg = (NMAL6880CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6880CommonLogic.setTableColumnAttr(scrnMsg);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, bMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).vndShipToCustCd_A1);
    }
}
