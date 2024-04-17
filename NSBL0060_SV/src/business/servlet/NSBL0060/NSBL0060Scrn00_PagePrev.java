/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0060;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0060.NSBL0060CMsg;
import business.servlet.NSBL0060.common.NSBL0060CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/20/2013   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public class NSBL0060Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0060BMsg scrnMsg = (NSBL0060BMsg) bMsg;
        NSBL0060CMsg bizMsg = NSBL0060CommonLogic.createCMsgForSearch();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0060BMsg scrnMsg = (NSBL0060BMsg) bMsg;
        NSBL0060CMsg bizMsg = (NSBL0060CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSBL0060CommonLogic.screenControlProcess(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.xxFromDt);
    }
}
