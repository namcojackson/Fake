/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.SCRN_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.TABLE_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.TAB_DETAIL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Button Action to Detail Tab
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/16/2016   CITS            Makoto Okigami  Create          N/A
 * 02/14/2016   CITS            K.Ogino         Update          QC#17483
 *</pre>
 */
public class NPBL0020Scrn00_TAB_Detail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        NPBL0020CMsg bizMsg = new NPBL0020CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;

        if (bizMsg != null) {

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(TABLE_ID, scrnMsg.A);

        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);

    }
}
