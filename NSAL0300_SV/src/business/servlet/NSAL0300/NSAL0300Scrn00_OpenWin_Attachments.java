/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.servlet.NSAL0300.constant.NSAL0300Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;


import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_Attachments extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        Object[] params = new Object[9];

        String sqNum = scrnMsg.dsContrPk.getValue().toString();

        params[0] = PARAMS_DISPLAY_MODE;
        params[1] = BIZ_ID;
        params[2] = getGlobalCompanyCode() + sqNum;
        params[3] = "Contract Attachments";
        params[4] = "Contract #";
        params[5] = null;
        params[6] = PARAMS_UPPER_KEY;
        params[7] = PARAMS_EXTENSION_KEY;
        params[8] = PARAMS_SIZE_KEY;

        setArgForSubScreen(params);
    }
}
