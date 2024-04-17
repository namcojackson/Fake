/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1860;

import static business.servlet.NWAL1860.constant.NWAL1860Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1860.NWAL1860CMsg;
import business.servlet.NWAL1860.common.NWAL1860CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1860Scrn00_CMN_Close
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1860Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1860BMsg scrnMsg = (NWAL1860BMsg) bMsg;
        NWAL1860CommonLogic.checkClose(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1860BMsg scrnMsg = (NWAL1860BMsg) bMsg;
        NWAL1860CMsg bizMsg = new NWAL1860CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1860BMsg scrnMsg = (NWAL1860BMsg) bMsg;
        NWAL1860CMsg bizMsg = (NWAL1860CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL1860CommonLogic.setReturnValues(scrnMsg, (Object[]) getArgForSubScreen());
    }
}
