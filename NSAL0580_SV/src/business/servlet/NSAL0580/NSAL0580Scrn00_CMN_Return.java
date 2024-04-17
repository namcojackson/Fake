/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0580;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSAL0580Scrn00_CMN_Return extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NSAL0580BMsg scrnMsg = (NSAL0580BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NSAL0580BMsg scrnMsg = (NSAL0580BMsg) bMsg;

        // NSAL0580CMsg bizMsg = new NSAL0580CMsg();
        // bizMsg.setBusinessID(BUSINESS_ID);
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // NSAL0580BMsg scrnMsg = (NSAL0580BMsg) bMsg;
        // NSAL0580CMsg bizMsg = (NSAL0580CMsg) cMsg;

        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
