/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0590;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSAL0590Scrn00_CMN_Return extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NSAL0590BMsg scrnMsg = (NSAL0590BMsg) bMsg;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NSAL0590BMsg scrnMsg = (NSAL0590BMsg) bMsg;

        // NSAL0590CMsg bizMsg = new NSAL0590CMsg();
        // bizMsg.setBusinessID("NSAL0590");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // NSAL0590BMsg scrnMsg = (NSAL0590BMsg) bMsg;
        // NSAL0590CMsg bizMsg = (NSAL0590CMsg) cMsg;

        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
