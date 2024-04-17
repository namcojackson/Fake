package business.servlet.ZZVL0020.common;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import business.servlet.ZZVL0020.ZZVL0020BMsg;
import business.servlet.ZZVL0020.constant.ZZVL0020Constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/26   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public final class ZZVL0020CommonLogic {


    /**
     * Method name: dspScrn00
     * <dd>The method explanation: button Screen Control(Scrn00).
     * <dd>Remarks:
     * @param scrnMsg ZZVL0020BMsg
     * @param handler S21CommonHandler
     */
    public static final void dspScrn00(ZZVL0020BMsg scrnMsg, S21CommonHandler handler) {

        // Button Control
        handler.setButtonProperties(ZZVL0020Constant.BTN_CMN_SAVE[0], ZZVL0020Constant.BTN_CMN_SAVE[1], ZZVL0020Constant.BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(ZZVL0020Constant.BTN_CMN_SUBMIT[0], ZZVL0020Constant.BTN_CMN_SUBMIT[1], ZZVL0020Constant.BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(ZZVL0020Constant.BTN_CMN_APPLY[0], ZZVL0020Constant.BTN_CMN_APPLY[1], ZZVL0020Constant.BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(ZZVL0020Constant.BTN_CMN_APPROVE[0], ZZVL0020Constant.BTN_CMN_APPROVE[1], ZZVL0020Constant.BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(ZZVL0020Constant.BTN_CMN_REJECT[0], ZZVL0020Constant.BTN_CMN_REJECT[1], ZZVL0020Constant.BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(ZZVL0020Constant.BTN_CMN_BLANK6[0], ZZVL0020Constant.BTN_CMN_BLANK6[1], ZZVL0020Constant.BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(ZZVL0020Constant.BTN_CMN_BLANK7[0], ZZVL0020Constant.BTN_CMN_BLANK7[1], ZZVL0020Constant.BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(ZZVL0020Constant.BTN_CMN_CLEAR[0], ZZVL0020Constant.BTN_CMN_CLEAR[1], ZZVL0020Constant.BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(ZZVL0020Constant.BTN_CMN_RESET[0], ZZVL0020Constant.BTN_CMN_RESET[1], ZZVL0020Constant.BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(ZZVL0020Constant.BTN_CMN_RETURN[0], ZZVL0020Constant.BTN_CMN_RETURN[1], ZZVL0020Constant.BTN_CMN_RETURN[2], 1, null);

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            if (scrnMsg.C.no(i).xxSetFlg_C.getValue().equals(ZZVL0020Constant.SET_Y)) {
                handler.setButtonEnabled(ZZVL0020Constant.BTN_DELETE, i, true);
            } else {
                handler.setButtonEnabled(ZZVL0020Constant.BTN_DELETE, i, false);
            }
        }

        S21TableColorController tblColor = new S21TableColorController(ZZVL0020Constant.SCREEN_NAME0, scrnMsg);
        tblColor.setAlternateRowsBG("C", scrnMsg.C);

    }

    /**
     * Method name: dspScrn01
     * <dd>The method explanation: button Screen Control(Scrn01).
     * <dd>Remarks:
     * @param scrnMsg ZZVL0020BMsg
     * @param handler S21CommonHandler
     */
    public static void dspScrn01(ZZVL0020BMsg scrnMsg, S21CommonHandler handler) {

        // Button Control
        handler.setButtonGuardLabel(ZZVL0020Constant.BTN_RIGHT, null, ">");
        handler.setButtonGuardLabel(ZZVL0020Constant.BTN_LEFT, null, "<");

        handler.setButtonEnabled(ZZVL0020Constant.BTN_RIGHT, false);
        handler.setButtonEnabled(ZZVL0020Constant.BTN_LEFT, false);

        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled(ZZVL0020Constant.BTN_RIGHT, true);
        }
        if (scrnMsg.B.getValidCount() > 0) {
            handler.setButtonEnabled(ZZVL0020Constant.BTN_LEFT, true);
        }

        // Common Button Control
        handler.setButtonProperties(ZZVL0020Constant.CMN_BTN1[0], ZZVL0020Constant.CMN_BTN1[1], ZZVL0020Constant.CMN_BTN1[2], 0, null);
        handler.setButtonProperties(ZZVL0020Constant.CMN_BTN2[0], ZZVL0020Constant.CMN_BTN2[1], ZZVL0020Constant.CMN_BTN2[2], 1, null);
        handler.setButtonProperties(ZZVL0020Constant.CMN_BTN3[0], ZZVL0020Constant.CMN_BTN3[1], ZZVL0020Constant.CMN_BTN3[2], 0, null);
        handler.setButtonProperties(ZZVL0020Constant.CMN_BTN4[0], ZZVL0020Constant.CMN_BTN4[1], ZZVL0020Constant.CMN_BTN4[2], 0, null);
        handler.setButtonProperties(ZZVL0020Constant.CMN_BTN5[0], ZZVL0020Constant.CMN_BTN5[1], ZZVL0020Constant.CMN_BTN5[2], 0, null);
        handler.setButtonProperties(ZZVL0020Constant.CMN_BTN6[0], ZZVL0020Constant.CMN_BTN6[1], ZZVL0020Constant.CMN_BTN6[2], 0, null);
        handler.setButtonProperties(ZZVL0020Constant.CMN_BTN7[0], ZZVL0020Constant.CMN_BTN7[1], ZZVL0020Constant.CMN_BTN7[2], 0, null);
        handler.setButtonProperties(ZZVL0020Constant.CMN_BTN8[0], ZZVL0020Constant.CMN_BTN8[1], ZZVL0020Constant.CMN_BTN8[2], 1, null);
        handler.setButtonProperties(ZZVL0020Constant.CMN_BTN9[0], ZZVL0020Constant.CMN_BTN9[1], ZZVL0020Constant.CMN_BTN9[2], 1, null);
        handler.setButtonProperties(ZZVL0020Constant.CMN_BTN10[0], ZZVL0020Constant.CMN_BTN10[1], ZZVL0020Constant.CMN_BTN10[2], 1, null);

        // Common Button Confirm Message Settings       
        handler.setButtonConfirmMsg(ZZVL0020Constant.CMN_BTN10[1], "ZZZM9009W", null, 0);

        scrnMsg.roleNm_2.setInputProtected(true);

        // Color Settings
        S21TableColorController tblColor = new S21TableColorController(ZZVL0020Constant.SCREEN_NAME1, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        tblColor.setAlternateRowsBG("B", scrnMsg.B);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            String xxErrFlg = scrnMsg.B.no(i).xxErrFlg_B1.getValue();
            if (xxErrFlg.equals("1")) {
                tblColor.setRowStyle("B", i, "pErr");
            }
        }

    }

}
