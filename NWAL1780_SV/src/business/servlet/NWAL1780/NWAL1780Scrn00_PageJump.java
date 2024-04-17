/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1780;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1780.NWAL1780CMsg;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Supply Quote Search Jump Page
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NWAL1780Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1780BMsg scrnMsg = (NWAL1780BMsg) bMsg;
        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, //
                scrnMsg.A, //
                scrnMsg.xxPageShowFromNum, //
                scrnMsg.xxPageShowToNum, //
                scrnMsg.xxPageShowOfNum, //
                scrnMsg.xxPageShowCurNum, //
                scrnMsg.xxPageShowTotNum);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1780BMsg scrnMsg = (NWAL1780BMsg) bMsg;

        S21BatchSearchPagenationScrnSupport.setRequestDataForPageJump(scrnMsg, //
                scrnMsg.A, //
                scrnMsg.xxPageShowFromNum, //
                scrnMsg.xxPageShowToNum, //
                scrnMsg.xxPageShowOfNum, //
                scrnMsg.xxPageShowCurNum, //
                scrnMsg.xxPageShowTotNum);

        NWAL1780CMsg bizMsg = new NWAL1780CMsg();
        bizMsg.setBusinessID("NWAL1780");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1780BMsg scrnMsg = (NWAL1780BMsg) bMsg;
        NWAL1780CMsg bizMsg = (NWAL1780CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
