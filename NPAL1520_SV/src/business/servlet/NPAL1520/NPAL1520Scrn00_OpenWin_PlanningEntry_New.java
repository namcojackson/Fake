/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1520;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1520 Min-Max Planning Inquiry
 * Function Name : Open Window MRP Planning Entry (New Button)
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/11/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1520Scrn00_OpenWin_PlanningEntry_New extends S21CommonHandler {

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

        NPAL1520BMsg scrnMsg = (NPAL1520BMsg) bMsg;

        scrnMsg.xxPopPrm_E0.clear();
        scrnMsg.xxPopPrm_E1.clear();
        scrnMsg.xxPopPrm_E2.clear();
        scrnMsg.xxPopPrm_E3.clear();

        Object[] params = new Object[4];
        params[0] = scrnMsg.xxPopPrm_E0;
        params[1] = scrnMsg.xxPopPrm_E1;
        params[2] = scrnMsg.xxPopPrm_E2;
        params[3] = scrnMsg.xxPopPrm_E3;

        setArgForSubScreen(params);

    }
}
