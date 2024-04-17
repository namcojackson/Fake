package business.servlet.ZZZL0061.common;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.ZZZL0061.ZZZL0061BMsg;
import business.servlet.ZZZL0061.constant.ZZZL0061Constant;

public class ZZZL0061CommonLogic implements ZZZL0061Constant {

    /**
     * Initial Common Button setting method.
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        // Common Buttons.
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }
    
    public static void ControlButtonsDisplay(S21CommonHandler objS21CommonHandler, ZZZL0061BMsg scrnMsg) {

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
