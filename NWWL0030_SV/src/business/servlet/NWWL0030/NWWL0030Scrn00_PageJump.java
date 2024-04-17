/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0030;

import static business.servlet.NWWL0030.constant.NWWL0030Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0030.NWWL0030CMsg;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0030Scrn00_PageJump
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0030Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0030BMsg scrnMsg = (NWWL0030BMsg) bMsg;
        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(//
                scrnMsg //
                , scrnMsg.A //
                , scrnMsg.xxPageShowFromNum //
                , scrnMsg.xxPageShowToNum //
                , scrnMsg.xxPageShowOfNum //
                , scrnMsg.xxPageShowCurNum //
                , scrnMsg.xxPageShowTotNum);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0030BMsg scrnMsg = (NWWL0030BMsg) bMsg;

        NWWL0030CMsg bizMsg = new NWWL0030CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWWL0030BMsg scrnMsg = (NWWL0030BMsg) bMsg;
        NWWL0030CMsg bizMsg = (NWWL0030CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.setFocusItem(scrnMsg.A.no(0).ntfyHdrId_A0);
    }
}
