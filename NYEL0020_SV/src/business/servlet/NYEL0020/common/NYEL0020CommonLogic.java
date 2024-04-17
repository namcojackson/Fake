/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/05/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.NYEL0020.common;

import business.servlet.NYEL0020.NYEL0020BMsg;
import business.servlet.NYEL0020.constant.NYEL0020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * Method name: Screen processing Common Logic
 */
public class NYEL0020CommonLogic implements NYEL0020Constant {

    /**
     * Method name: dspScrn
     * <dd>The method explanation: button Screen Control.
     * <dd>Remarks:
     * @param scrnMsg NYEL0020BMsg
     * @param handler S21CommonHandler
     */
    public static void dspScrn(NYEL0020BMsg scrnMsg, S21CommonHandler handler) {

        // Button Control
        handler.setButtonGuardLabel(BTN_RIGHT, null, ">");
        handler.setButtonGuardLabel(BTN_LEFT, null, "<");
        handler.setButtonGuardLabel(BTN_UP, null, "Up");
        handler.setButtonGuardLabel(BTN_DOWN, null, "Down");

        handler.setButtonEnabled(BTN_RIGHT, false);
        handler.setButtonEnabled(BTN_LEFT, false);
        handler.setButtonEnabled(BTN_UP, false);
        handler.setButtonEnabled(BTN_DOWN, false);

        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled(BTN_RIGHT, true);
        }
        if (scrnMsg.B.getValidCount() > 0) {
            handler.setButtonEnabled(BTN_LEFT, true);
            handler.setButtonEnabled(BTN_UP, true);
            handler.setButtonEnabled(BTN_DOWN, true);
        }

        // Common Button Control
        handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
        handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 1, null);
        handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
        handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
        handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
        handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
        handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
        handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 1, null);
        handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);

        // Common Button Confirm Message Settings       
        handler.setButtonConfirmMsg(CMN_BTN10[1], "ZZZM9009W", null, 0);

        // Color Settings
        S21TableColorController tblColor = new S21TableColorController(SCREEN_NAME, scrnMsg);
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
