/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0080.common;

import parts.common.EZDBMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFAL0080.NFAL0080BMsg;
import business.servlet.NFAL0080.constant.NFAL0080Constant;

/**
 * Class name: NFAL0080CommonLogic
 * <dd>The class explanation: Common Logic for screen component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0080CommonLogic implements NFAL0080Constant {

    /**
     * Method name: initButton
     * <dd>The method explanation: Init Button Control.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     * @param handler EZ Common Handler
     */
    public static void initButton(EZDBMsg bMsg, EZDCommonHandler handler) {

        NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;

        handler.setButtonProperties(BTN_CMN_BLANK1[0], BTN_CMN_BLANK1[1], BTN_CMN_BLANK1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK4[0], BTN_CMN_BLANK4[1], BTN_CMN_BLANK4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK5[0], BTN_CMN_BLANK5[1], BTN_CMN_BLANK5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK8[0], BTN_CMN_BLANK8[1], BTN_CMN_BLANK8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        if (scrnMsg.A.getValidCount() == scrnMsg.A.length()) {
            handler.setButtonEnabled(BTN_ADD_ROW, false);
        } else {
            handler.setButtonEnabled(BTN_ADD_ROW, true);
        }

        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonEnabled(BTN_DELETE_ROWS, false);
        } else {
            handler.setButtonEnabled(BTN_DELETE_ROWS, true);
        }

    }

    /**
     * Method name: initButtonConfirmMsg
     * <dd>The method explanation: Init button confirm message.
     * <dd>Remarks:
     * @param handler EZDCommonHandler
     */
    public static void initButtonConfirmMsg(EZDCommonHandler handler) {

        // Common Button Confirm Message Settings
        handler.setButtonConfirmMsg(BTN_CMN_SUBMIT[1], "ZZM8102I", new String[] {BTN_CMN_SUBMIT[2] }, 0);
        handler.setButtonConfirmMsg(BTN_CMN_RESET[1], "ZZM8101I", new String[] {BTN_CMN_RESET[2] }, 0);
        handler.setButtonConfirmMsg(BTN_CMN_RETURN[1], "ZZM8101I", new String[] {BTN_CMN_RETURN[2] }, 0);
    }

    /**
     * Method name: initFocusItem
     * <dd>The method explanation: Init button confirm message.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void initFocusItem(EZDBMsg bMsg) {

        NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;

        scrnMsg.setFocusItem(scrnMsg.eligCoaSegPtrnCd);
    }

    /**
     * Method name: setNameForMessageCommon
     * <dd>The method explanation: Set name for message.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setNameForMessageCommon(EZDBMsg bMsg) {

        NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).eligCoaSegPtrnCd_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Canon Inc Product Code"));
            scrnMsg.A.no(i).coaSegLkupTpCd_A3.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "First Product Control Code"));
        }
    }

    /**
     * Method name: setProtectEligCoaSetPtrnCdList
     * <dd>The method explanation: Set name for message.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setProtectEligCoaSetPtrnCdList(EZDBMsg bMsg) {

        NFAL0080BMsg scrnMsg = (NFAL0080BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).eligCoaSegPtrnCd_A.setInputProtected(true);
        }
    }

    /**
     * Method name: setEditableMode
     * <dd>The method explanation: set check boxes
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     * @param handler EZDCommonHandler
     * @param disable boolean
     */
    public void setInputProtectedEditableFields(NFAL0080BMsg scrnMsg, EZDCommonHandler handler, boolean disable) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.clear();
            scrnMsg.A.no(i).xxChkBox_A.setInputProtected(disable);
            // if( i != index ) {
            // scrnMsg.A.no(i).coaSegLkupTpCd_A3.setInputProtected(disable);
            // }
        }
        scrnMsg.coaSegLkupTpCd_3.setInputProtected(disable);

        boolean buttonEnabled = true;
        if (disable) {
            buttonEnabled = false;
        }
        handler.setButtonEnabled(BTN_CMN_SUBMIT[0], buttonEnabled);
        handler.setButtonEnabled(BTN_ADD_ROW, buttonEnabled);
        handler.setButtonEnabled(BTN_DELETE_ROWS, buttonEnabled);
    }

}
