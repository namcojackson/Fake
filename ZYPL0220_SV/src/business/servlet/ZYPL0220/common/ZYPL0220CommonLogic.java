package business.servlet.ZYPL0220.common;

import business.servlet.ZYPL0220.ZYPL0220BMsg;
import business.servlet.ZYPL0220.constant.ZYPL0220Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZYPL0220CommonLogic implements ZYPL0220Constant {

    /**
     * 画面の表示状態を初期表示モードに変更する。
     * @param handler
     * @param scrnMsg
     */
    public static void setInitMode(S21CommonHandler handler, ZYPL0220BMsg scrnMsg) {

        // button properties
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_TOP, false);
        handler.setButtonEnabled(BTN_RESTRICTION, true);
        handler.setButtonEnabled(BTN_LOAD, true);
        handler.setButtonEnabled(BTN_ADD, false);
        handler.setButtonEnabled(BTN_UPDATE, false);

        // Common button
        handler.setButtonProperties(BTN_CMN_BTN1[0], BTN_CMN_BTN1[1], BTN_CMN_BTN1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN2[0], BTN_CMN_BTN2[1], BTN_CMN_BTN2[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN3[0], BTN_CMN_BTN3[1], BTN_CMN_BTN3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN4[0], BTN_CMN_BTN4[1], BTN_CMN_BTN4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN5[0], BTN_CMN_BTN5[1], BTN_CMN_BTN5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN6[0], BTN_CMN_BTN6[1], BTN_CMN_BTN6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN7[0], BTN_CMN_BTN7[1], BTN_CMN_BTN7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN8[0], BTN_CMN_BTN8[1], BTN_CMN_BTN8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN9[0], BTN_CMN_BTN9[1], BTN_CMN_BTN9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN10[0], BTN_CMN_BTN10[1], BTN_CMN_BTN10[2], 1, null);

        handler.setButtonConfirmMsg(BTN_CMN_BTN10[1], MSG_CD_CONFIRM_RETURN_EVENT, new String[] {BTN_CMN_BTN10[2] }, 0);
        handler.setButtonConfirmMsg(BTN_LOAD, MSG_CD_CONFIRM_LOAD_NAMES, null, 0);

        // save init uploadCsvID
        String upldCsvId = scrnMsg.upldCsvId_DF.getValue();

        scrnMsg.clear();
        scrnMsg.A.setValidCount(0);
        if (upldCsvId != null && !"".equals(upldCsvId)) {
            scrnMsg.upldCsvId.setValue(upldCsvId);
        }
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).upldCsvHdrNum_1B.setValue(i + 1);
        }
        scrnMsg.B.setValidCount(scrnMsg.B.length());
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).upldCsvHdrNum_1C.setValue(i + 1);
        }
        scrnMsg.C.setValidCount(scrnMsg.C.length());
        scrnMsg.upldCsvId_DF.setValue(upldCsvId);
        scrnMsg.upldCsvId.setValue(upldCsvId);

        // input properties On Screen00
        scrnMsg.upldCsvId.setInputProtected(false);
        scrnMsg.upldCsvNm.setInputProtected(false);
        scrnMsg.upldCsvFileId.setInputProtected(false);
        scrnMsg.upldCsvTempTblId.setInputProtected(false);
        scrnMsg.ezReqBusinessID.setInputProtected(false);

        // input properties On Screen01
        scrnMsg.upldCsvId_1H.setInputProtected(true);
        scrnMsg.upldCsvNm_1H.setInputProtected(true);
        scrnMsg.upldCsvFileId_1H.setInputProtected(true);
        scrnMsg.upldCsvTempTblId_1H.setInputProtected(true);
        scrnMsg.ezReqBusinessID_1H.setInputProtected(true);
    }

    /**
     * 画面の表示状態を検索モードに変更する。
     * @param handler
     * @param scrnMsg
     */
    public static void setSearchMode(S21CommonHandler handler, ZYPL0220BMsg scrnMsg) {

        // Input Properties
        scrnMsg.upldCsvNm.setInputProtected(false);
        scrnMsg.upldCsvFileId.setInputProtected(false);
        scrnMsg.upldCsvTempTblId.setInputProtected(false);
        scrnMsg.ezReqBusinessID.setInputProtected(false);

        // Button Properties
        handler.setButtonEnabled(BTN_TOP, false);
        handler.setButtonEnabled(BTN_RESTRICTION, true);
        handler.setButtonEnabled(BTN_LOAD, true);
        handler.setButtonEnabled(BTN_ADD, true);
        handler.setButtonEnabled(BTN_UPDATE, true);

    }

    /**
     * 画面の表示状態をRestriction表示モードに変更する。
     * @param handler
     * @param scrnMsg
     */
    public static void setRestrictionMode(S21CommonHandler handler, ZYPL0220BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_SEARCH, false);
        handler.setButtonEnabled(BTN_TOP, true);
        handler.setButtonEnabled(BTN_RESTRICTION, false);

        handler.setButtonProperties(BTN_CMN_BTN8[0], BTN_CMN_BTN8[1], BTN_CMN_BTN8[2], 0, null);

        scrnMsg.upldCsvId.setInputProtected(true);
    }

    /**
     * 画面の表示状態をTop表示モードに変更する。
     * @param handler
     * @param scrnMsg
     */
    public static void setTopMode(S21CommonHandler handler, ZYPL0220BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_TOP, false);
        handler.setButtonEnabled(BTN_RESTRICTION, true);

        handler.setButtonProperties(BTN_CMN_BTN8[0], BTN_CMN_BTN8[1], BTN_CMN_BTN8[2], 1, null);

        scrnMsg.upldCsvId.setInputProtected(false);
    }

    /**
     * 画面の表示状態をLoad Namesモードに変更する。
     * @param handler
     * @param scrnMsg
     */
    public static void setLoadMode(S21CommonHandler handler, ZYPL0220BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_ADD, true);
        if (scrnMsg.upldCsvId_SC.isClear()) {
            handler.setButtonEnabled(BTN_UPDATE, false);
        } else {
            handler.setButtonEnabled(BTN_UPDATE, true);
        }

    }

}
