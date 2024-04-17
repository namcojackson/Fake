/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0050;

import static business.servlet.NWCL0050.constant.NWCL0050Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0050.NWCL0050CMsg;
import business.servlet.NWCL0050.common.NWCL0050CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/26   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NWCL0050Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;
        scrnMsg.xxDplyCtrlFlg_EM.clear();

        NWCL0050CMsg bizMsg = new NWCL0050CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;
        NWCL0050CMsg bizMsg = (NWCL0050CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWCL0050CommonLogic.controlFileLink(scrnMsg);
        NWCL0050CommonLogic.setTblBackColor(scrnMsg);
        NWCL0050CommonLogic.setProtected(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.xxPageShowCurNum);
    }
}
