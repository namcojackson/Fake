/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1180;

import static business.servlet.NSAL1180.constant.NSAL1180Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1180.NSAL1180CMsg;
import business.servlet.NSAL1180.common.NSAL1180CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Hitachi         K.Yamada        Create          N/A
 *</pre>
 */
public class NSAL1180Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1180BMsg scrnMsg = (NSAL1180BMsg) bMsg;

        NSAL1180CMsg bizMsg = new NSAL1180CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1180BMsg scrnMsg = (NSAL1180BMsg) bMsg;
        NSAL1180CMsg bizMsg = (NSAL1180CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL1180CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);

    }
}
