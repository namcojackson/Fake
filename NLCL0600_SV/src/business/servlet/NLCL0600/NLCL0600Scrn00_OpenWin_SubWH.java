/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0600;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0600.common.NLCL0600CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0600 PI Entry
 * Function Name : Open Return to Sub WH Search Popup(NWAL1130)
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/04/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NLCL0600Scrn00_OpenWin_SubWH extends S21CommonHandler {

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

        NLCL0600BMsg scrnMsg = (NLCL0600BMsg) bMsg;

        // set popup parameter
        Object[] params = NLCL0600CommonLogic.setSubWHSearchParam(scrnMsg, getGlobalCompanyCode());

        // send popup parameter
        setArgForSubScreen(params);

    }
}
