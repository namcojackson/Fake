/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0630;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0630.common.NLCL0630CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0630 Tech PI Inquiry
 * Function Name : Open Return to Location Search Popup(NWAL1130)
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/11/2018   Fujitsu         T.Ogura         Create          QC#28755
 *</pre>
 */
public class NLCL0630Scrn00_OpenWin_Location extends S21CommonHandler {

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

        NLCL0630BMsg scrnMsg = (NLCL0630BMsg) bMsg;

        // set popup parameter
        Object[] params = NLCL0630CommonLogic.setLocationSearchParam(scrnMsg, getGlobalCompanyCode());

        // send popup parameter
        setArgForSubScreen(params);

    }
}
