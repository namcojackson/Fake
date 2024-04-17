/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7050.NMAL7050CMsg;
import business.servlet.NMAL7050.common.NMAL7050CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/27   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7050Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7050BMsg scrnMsg = (NMAL7050BMsg) bMsg;

        // page jump common setting
        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg
                                                                  , scrnMsg.A
                                                                  , scrnMsg.xxPageShowFromNum
                                                                  , scrnMsg.xxPageShowToNum
                                                                  , scrnMsg.xxPageShowOfNum
                                                                  , scrnMsg.xxPageShowCurNum
                                                                  , scrnMsg.xxPageShowTotNum);

        NMAL7050CMsg bizMsg = new NMAL7050CMsg();
        bizMsg.setBusinessID("NMAL7050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7050BMsg scrnMsg = (NMAL7050BMsg) bMsg;
        NMAL7050CMsg bizMsg = (NMAL7050CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7050CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.setFocusItem(scrnMsg.prcMtrPkgNm);
    }
}
