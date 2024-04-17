/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.blap.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL2200.constant.NWAL2200Constant.TAB_RMA;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_ERRORS;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWAL2200Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, true, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, //
                    scrnMsg.B, //
                    scrnMsg.xxPageShowFromNum_LC, //
                    scrnMsg.xxPageShowToNum_LC, //
                    scrnMsg.xxPageShowOfNum_LC, //
                    scrnMsg.xxPageShowCurNum_LC, //
                    scrnMsg.xxPageShowTotNum_LC);
        } else if (TAB_RMA.equals(dplyTab)) {
            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, //
                    scrnMsg.D, //
                    scrnMsg.xxPageShowFromNum_RC, //
                    scrnMsg.xxPageShowToNum_RC, //
                    scrnMsg.xxPageShowOfNum_RC, //
                    scrnMsg.xxPageShowCurNum_RC, //
                    scrnMsg.xxPageShowTotNum_RC);
        } else if (TAB_ERRORS.equals(dplyTab)) {
            S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, //
                    scrnMsg.E, //
                    scrnMsg.xxPageShowFromNum_EL, //
                    scrnMsg.xxPageShowToNum_EL, //
                    scrnMsg.xxPageShowOfNum_EL, //
                    scrnMsg.xxPageShowCurNum_EL, //
                    scrnMsg.xxPageShowTotNum_EL);
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID("NWAL2200");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg  = (NWAL2200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
