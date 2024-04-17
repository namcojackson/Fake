/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7060;

import static business.servlet.NMAL7060.constant.NMAL7060Constant.BIZ_ID;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_DELETE_ROW;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_DELETE_ROW_BLLG;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.NMAM8234I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7060.NMAL7060CMsg;
import business.servlet.NMAL7060.common.NMAL7060CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7060Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7060Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;
        NMAL7060CMsg bizMsg = new NMAL7060CMsg();

        scrnMsg.clear();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;
        NMAL7060CMsg bizMsg = (NMAL7060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7060CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);

        setButtonConfirmMsgEx(BTN_DELETE_ROW[1], NMAM8234I, new String[] {}, 1);
        setButtonConfirmMsgEx(BTN_DELETE_ROW_BLLG[1], NMAM8234I, new String[] {}, 1);

        scrnMsg.setFocusItem(scrnMsg.prcMtrPkgNm);
    }
}
