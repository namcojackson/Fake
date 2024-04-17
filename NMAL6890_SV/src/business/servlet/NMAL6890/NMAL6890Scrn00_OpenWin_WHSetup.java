/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6890;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NMAL6890 Warehouse Search
 * Function Name : Open Window for Warehouse Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/11/2016   CITS            Y.Kuroda        Create          N/A
 *</pre>
 */
public class NMAL6890Scrn00_OpenWin_WHSetup extends S21CommonHandler {

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

        NMAL6890BMsg scrnMsg = (NMAL6890BMsg) bMsg;

        // parameter for sub screen.
        Object[] params = new Object[1];
        params[0] = scrnMsg.A.no(getButtonSelectNumber()).rtlWhCd_A;

        super.setArgForSubScreen(params);
    }

}
