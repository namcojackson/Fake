/*
 * <Pre> Copyright (c) 2013 Canon USA Inc. All rights reserved. </Pre>
 */
package business.servlet.NLBL3030.common;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL3030.NLBL3030BMsg;
import business.servlet.NLBL3030.constant.NLBL3030Constant;

/**
 * <pre>
 * Message Entry PopUo
 * 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/16   Fujitsu         C.Naito         Create          (From NATL6050)
 * </pre>
 */
public class NLBL3030CommonLogic implements NLBL3030Constant {

    /**
     * Common Buttom Initial Settings
     * @param handler EZDCommonHandler
     * @param mode String
     */
    public static void initCommonButton(EZDCommonHandler handler, String mode) {
        if (EDIT_MODE.equals(mode)) {
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * Bussiness Buttom Initial Settings
     * @param handler EZDCommonHandler
     * @param mode String
     */
    public static void initButton(EZDCommonHandler handler, String mode) {
        if (EDIT_MODE.equals(mode)) {
            handler.setButtonEnabled(BTN_CHK[0], true);
            handler.setButtonEnabled(BTN_OK[0], true);
        } else {
            handler.setButtonEnabled(BTN_CHK[0], false);
            handler.setButtonEnabled(BTN_OK[0], false);
        }
    }

    /**
     * Clear Item
     * @param scrnMsg NLBL3030BMsg
     */
    public static void clear(NLBL3030BMsg scrnMsg) {
        scrnMsg.xxDsMultMsgDplyTxt.clear();
        scrnMsg.xxDsInputTxtNum.clear();
    }

    /**
     * Init Process Protect and Forcus
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3030BMsg
     * @param mode String
     */
    public static void initScrn(EZDCommonHandler handler, NLBL3030BMsg scrnMsg, String mode) {

        if (EDIT_MODE.equals(mode)) {
            scrnMsg.xxDsMultMsgDplyTxt.setInputProtected(false);
            scrnMsg.setFocusItem(scrnMsg.xxDsMultMsgDplyTxt);
        } else {
            scrnMsg.xxDsMultMsgDplyTxt.setInputProtected(true);
        }
    }

    /**
     * Add Check Item and Throw Error
     * @param scrnMsg NLBL3030BMsg
     */
    public static void checkOK(NLBL3030BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.xxDsMultMsgDplyTxt);
        scrnMsg.putErrorScreen();
    }


}
