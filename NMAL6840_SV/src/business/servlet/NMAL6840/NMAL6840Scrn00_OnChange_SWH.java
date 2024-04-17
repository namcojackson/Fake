/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6840;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6840.common.NMAL6840CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/27/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class NMAL6840Scrn00_OnChange_SWH extends S21CommonHandler {

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

        NMAL6840BMsg scrnMsg = (NMAL6840BMsg) bMsg;

        int eventRowIndex = getButtonSelectNumber();

        // set SWH information.
        NMAL6840CommonLogic.setSWHInfo(scrnMsg, eventRowIndex);

        scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).rtlSwhCd_A1);
    }
}
