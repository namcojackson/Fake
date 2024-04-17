/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0240;

import static business.servlet.NSBL0240.constant.NSBL0240Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0240.NSBL0240CMsg;
import business.servlet.NSBL0240.common.NSBL0240CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/15   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSBL0240Scrn00_DeleteRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSBL0240BMsg scrnMsg = (NSBL0240BMsg) bMsg;

        NSBL0240CMsg bizMsg = new NSBL0240CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSBL0240BMsg scrnMsg = (NSBL0240BMsg) bMsg;
        NSBL0240CMsg bizMsg = (NSBL0240CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NSBL0240CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
        scrnMsg.putErrorScreen();
    }
}