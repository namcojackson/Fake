/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1540;

import static business.servlet.NPAL1540.constant.NPAL1540Constant.BIZ_APP_ID;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.FUNCTION_CD_UPDATE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1540.NPAL1540CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NPAL1540 Manual ASN Entry
 * Function Name : ColSave
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/25   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NPAL1540Scrn00_CMN_ColSave extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1540BMsg scrnMsg = (NPAL1540BMsg) bMsg;

        NPAL1540CMsg bizMsg = new NPAL1540CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1540BMsg scrnMsg = (NPAL1540BMsg) bMsg;
        NPAL1540CMsg bizMsg  = (NPAL1540CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}