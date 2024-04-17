/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

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
public class NWAL1500Scrn00_OnChange_RetrunReason extends S21CommonHandler {

    // TODO
    // UnUsed T.Yoshida

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        // NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        // bizMsg.setBusinessID("NWAL1500");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        // NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;

        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
