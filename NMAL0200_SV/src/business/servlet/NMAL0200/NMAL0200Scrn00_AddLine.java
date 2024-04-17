/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0200;

import static business.servlet.NMAL0200.constant.NMAL0200Constant.BIZ_APP_ID;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL0200.constant.NMAL0200Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0200.NMAL0200CMsg;
import business.servlet.NMAL0200.common.NMAL0200CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NMAL0200 Product Categorization Maintenance
 * Function Name : AddLine
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NMAL0200Scrn00_AddLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL0200BMsg scrnMsg = (NMAL0200BMsg) bMsg;
        NMAL0200CMsg bizMsg = new NMAL0200CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL0200BMsg scrnMsg = (NMAL0200BMsg) bMsg;
        NMAL0200CMsg bizMsg = (NMAL0200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL0200CommonLogic.setTableColumnAttr4AddLine(scrnMsg, scrnMsg.A.getValidCount() - 1);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, bMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).mdseStruElmntTpCd_A1);
    }
}
