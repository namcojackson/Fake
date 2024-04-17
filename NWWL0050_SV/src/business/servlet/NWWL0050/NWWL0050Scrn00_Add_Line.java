/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0050;

import static business.servlet.NWWL0050.constant.NWWL0050Constant.BIZ_ID;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.NWWM0029E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0050.NWWL0050CMsg;
import business.servlet.NWWL0050.common.NWWL0050CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0050Scrn00_Add_Line
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/09   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0050Scrn00_Add_Line extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWWL0050BMsg scrnMsg = (NWWL0050BMsg) bMsg;
        if (scrnMsg.A.getValidCount() >= scrnMsg.A.length()) {
            scrnMsg.setMessageInfo(NWWM0029E, new String[] {String.valueOf(scrnMsg.A.length()) });
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWWL0050BMsg scrnMsg = (NWWL0050BMsg) bMsg;

        NWWL0050CMsg bizMsg = new NWWL0050CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0050BMsg scrnMsg = (NWWL0050BMsg) bMsg;
        NWWL0050CMsg bizMsg = (NWWL0050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        NWWL0050CommonLogic.controlScreenFields(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).ntfyDistQlfyCd_A);

    }
}
