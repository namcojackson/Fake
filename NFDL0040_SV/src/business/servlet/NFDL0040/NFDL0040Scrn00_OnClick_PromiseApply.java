/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0040.NFDL0040CMsg;
import business.servlet.NFDL0040.common.NFDL0040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Promise/Dispute Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NFDL0040Scrn00_OnClick_PromiseApply extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0040BMsg scrnMsg = (NFDL0040BMsg) bMsg;

        NFDL0040CMsg bizMsg = new NFDL0040CMsg();
        bizMsg.setBusinessID("NFDL0040");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0040BMsg scrnMsg = (NFDL0040BMsg) bMsg;
        NFDL0040CMsg bizMsg  = (NFDL0040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFDL0040CommonLogic.initialize(this, scrnMsg);
    }
}
