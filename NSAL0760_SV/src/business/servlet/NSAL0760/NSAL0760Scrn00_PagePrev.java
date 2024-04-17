/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0760;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0760.NSAL0760CMsg;
import business.servlet.NSAL0760.common.NSAL0760CommonLogic;
//import business.servlet.NSAL0760.constant.NSAL0760Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Contract Status History
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class NSAL0760Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NSAL0760BMsg scrnMsg = (NSAL0760BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0760BMsg scrnMsg = (NSAL0760BMsg) bMsg;

        NSAL0760CMsg bizMsg = new NSAL0760CMsg();
        bizMsg.setBusinessID("NSAL0760");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0760BMsg scrnMsg = (NSAL0760BMsg) bMsg;
        NSAL0760CMsg bizMsg  = (NSAL0760CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0760CommonLogic.itemProtect(scrnMsg);
        NSAL0760CommonLogic.setRowColors(scrnMsg);

    }
}
