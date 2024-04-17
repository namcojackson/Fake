package business.servlet.ZZZL0060.common;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.ZZZL0060.constant.ZZZL0060Constant;

public class ZZZL0060CommonLogic implements ZZZL0060Constant {

    /**
     * Initial Common Button setting method.
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        // Common Buttons.
        handler.setButtonProperties(BTN_CMN_SAVE[0],     BTN_CMN_SAVE[1],     BTN_CMN_SAVE[2],   0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0],   BTN_CMN_SUBMIT[1],   BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0],    BTN_CMN_APPLY[1],    BTN_CMN_APPLY[2],  0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0],  BTN_CMN_APPROVE[1],  BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0],   BTN_CMN_REJECT[1],   BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0],   BTN_CMN_DELETE[1],   BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0],    BTN_CMN_CLEAR[1],    BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0],    BTN_CMN_RESET[1],    BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0],   BTN_CMN_RETURN[1],   BTN_CMN_RETURN[2], 1, null);
    }
    
    public static void searchCommonButton(EZDCommonHandler handler) {
        initCommonButton(handler);
        handler.setButtonProperties(BTN_CMN_DELETE[0],   BTN_CMN_DELETE[1],   BTN_CMN_DELETE[2], 1, null);
    }

    public static void addCommonButton(EZDCommonHandler handler) {
        initCommonButton(handler);

        // Common Buttons.
        handler.setButtonProperties(BTN_CMN_SUBMIT[0],   BTN_CMN_SUBMIT[1],   BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0],   BTN_CMN_DELETE[1],   BTN_CMN_DELETE[2], 1, null);

    }
    
    /**
     * Method name: isEmpty
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param str String
     * @return String
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }
}
