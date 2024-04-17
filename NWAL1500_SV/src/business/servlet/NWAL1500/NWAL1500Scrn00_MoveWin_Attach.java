/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

// import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1500Scrn00_MoveWin_Attach
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/01   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NWAL1500Scrn00_MoveWin_Attach extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // TODO to remove later
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        scrnMsg.setMessageInfo("ZZXM0001E", new String[] {"This event is not available." });
        throw new EZDFieldErrorException();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        //
        // NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        // bizMsg.setBusinessID(BIZ_ID);
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        //
        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        // NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        //
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
