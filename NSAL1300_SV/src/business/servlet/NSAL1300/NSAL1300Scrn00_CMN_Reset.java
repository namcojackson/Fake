/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1300;

import static business.servlet.NSAL1300.constant.NSAL1300Constant.BIZ_ID;
import static business.servlet.NSAL1300.constant.NSAL1300Constant.FUNCTION_INQUIRY;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1300.NSAL1300CMsg;
import business.servlet.NSAL1300.common.NSAL1300CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/01   Hitachi         N.Arai          Create          N/A
 *</pre>
 */
public class NSAL1300Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1300BMsg scrnMsg = (NSAL1300BMsg) bMsg;

        NSAL1300CMsg bizMsg = new NSAL1300CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_INQUIRY);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1300BMsg scrnMsg = (NSAL1300BMsg) bMsg;
        NSAL1300CMsg bizMsg  = (NSAL1300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL1300CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();

    }
}
