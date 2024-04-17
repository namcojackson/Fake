/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1540;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1540.common.NPAL1540CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NPAL1540 Manual ASN Entry
 * Function Name : Open Carrier Search
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/25   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NPAL1540Scrn00_OpenWin_Carrier extends S21CommonHandler {

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

        NPAL1540BMsg scrnMsg = (NPAL1540BMsg) bMsg;

        // set popup parameter
        Object[] params = NPAL1540CommonLogic.setCarrierSearchParam(scrnMsg, getGlobalCompanyCode());

        // send popup parameter
        setArgForSubScreen(params);

    }
}
