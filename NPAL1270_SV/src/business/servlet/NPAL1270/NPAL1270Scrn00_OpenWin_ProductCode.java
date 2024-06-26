/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1270;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1270.common.NPAL1270CommonLogic;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * PO Requisition List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/29/2018   CITS            T.Gotoda        Create          QC#22521
 *</pre>
 */
public class NPAL1270Scrn00_OpenWin_ProductCode extends S21CommonHandler {

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

        NPAL1270BMsg scrnMsg = (NPAL1270BMsg) bMsg;
        Object[] params = NPAL1270CommonLogic.setParamForProdCdPopup(scrnMsg);

        // Subscreen transition
        setArgForSubScreen(params);

    }
}
