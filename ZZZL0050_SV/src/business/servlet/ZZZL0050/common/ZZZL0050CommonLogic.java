package business.servlet.ZZZL0050.common;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.ZZZL0050.ZZZL0050BMsg;
import business.servlet.ZZZL0050.constant.ZZZL0050Constant;

public class ZZZL0050CommonLogic implements ZZZL0050Constant {

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
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }
    
    public static void screenControlBySearchResult(EZDCommonHandler handler, boolean searchResultFlag) {

        if (searchResultFlag) {
            handler.setButtonEnabled("View", true);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        } else {
            handler.setButtonEnabled("View", true);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        }
        
    }
    
    /**
     * Setting data to all pulldowns for initializing.
     * @param handler EZDCommonHandler
     */
    public static void initPullDowns (ZZZL0050BMsg scrnMsg) {
//        // JVM list
//        for (int i =0; i < JVM.length; i++) {
//            scrnMsg.jvmNm_C.no(i).setValue(JVM[i]);
//            scrnMsg.jvmNm_D.no(i).setValue(JVM[i]);
//        }
        // Active flag
        scrnMsg.onlProcActvFlg_C.no(0).setValue(OFF);
        scrnMsg.onlProcActvFlg_D.no(0).setValue(OFF);
        scrnMsg.onlProcActvFlg_C.no(1).setValue(ON);
        scrnMsg.onlProcActvFlg_D.no(1).setValue(ON);
    }
    
    /**
     * Method name: checkItem
     * <dd>The method explanation: Add check item.
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public static void checkItem(ZZZL0050BMsg scrnMsg) {

        // add check items.
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.jvmNm_S);
        scrnMsg.addCheckItem(scrnMsg.curPldQueueNum);
        scrnMsg.addCheckItem(scrnMsg.plngIntvlScd);
        scrnMsg.addCheckItem(scrnMsg.startThrdNum);
        scrnMsg.addCheckItem(scrnMsg.maxQueueNum);
        scrnMsg.addCheckItem(scrnMsg.onlProcActvFlg_S);
    }
}
