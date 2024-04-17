package business.servlet.ZZZL0032.common;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.ZZZL0030.ZZZL0030_ABMsgArray;
import business.servlet.ZZZL0032.ZZZL0032BMsg;
import business.servlet.ZZZL0032.constant.ZZZL0032Constant;

public class ZZZL0032CommonLogic implements ZZZL0032Constant {

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
        handler.setButtonProperties(BTN_CMN_ADD[0], BTN_CMN_ADD[1], BTN_CMN_ADD[2], 1, null);
    }
    
    public static void removeUnCheckRows (ZZZL0032BMsg scrnMsg) {
        int cnt = scrnMsg.A.getValidCount();
        for (int i=0 ; i < cnt ; i++) {
            if (scrnMsg.A.no(i).xxChkBox_02.isClear()) {
                scrnMsg.A.no(i).clear();
                scrnMsg.A.setValidCount(scrnMsg.A.getValidCount() - 1);
            }
        }
    }
    
    public static boolean duplicateCheck (String event, ZZZL0030_ABMsgArray eventList) {
        int cnt = eventList.getValidCount();
        for (int i=0 ; i < cnt ; i++) {
            if (eventList.no(i).scrAppId_A.getValue().equals(event)) {
                return false;
            }
        }
        return true;
    }
}
