package business.servlet.ZZZL0071.common;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.ZZZL0071.ZZZL0071BMsg;
import business.servlet.ZZZL0071.constant.ZZZL0071Constant;

public class ZZZL0071CommonLogic implements ZZZL0071Constant {

    /**
     * Initial Common Button setting method.
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        // Common Buttons.
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_ADD[0], BTN_CMN_ADD[1], BTN_CMN_ADD[2], 1, null);
    }
    
    public static void ControlButtonsDisplay(S21CommonHandler objS21CommonHandler, ZZZL0071BMsg scrnMsg) {

        if (scrnMsg.xxPageShowFromNum.getValueInt() == 1) {
            objS21CommonHandler.setButtonEnabled(BTN_PREV[1], false);
        } else {
            objS21CommonHandler.setButtonEnabled(BTN_PREV[1], true);
        }
        // to disable next button
        if (scrnMsg.A.getValidCount() < 40) {
            objS21CommonHandler.setButtonEnabled(BTN_NEXT[1], false);
        } else {
            objS21CommonHandler.setButtonEnabled(BTN_NEXT[1], true);
        }
    }
}
