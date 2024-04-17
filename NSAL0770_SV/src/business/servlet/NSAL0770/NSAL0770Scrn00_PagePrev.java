/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0770;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0770.NSAL0770CMsg;
import business.servlet.NSAL0770.common.NSAL0770CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Change Status Audit
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class NSAL0770Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0770BMsg scrnMsg = (NSAL0770BMsg) bMsg;

        NSAL0770CMsg bizMsg = new NSAL0770CMsg();
        bizMsg.setBusinessID("NSAL0770");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0770BMsg scrnMsg = (NSAL0770BMsg) bMsg;
        NSAL0770CMsg bizMsg  = (NSAL0770CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0770CommonLogic.itemProtect(scrnMsg);
        NSAL0770CommonLogic.setRowColors(scrnMsg);

    }
}
