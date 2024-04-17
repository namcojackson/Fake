package business.servlet.ZZVL0000.common;

import business.servlet.ZZVL0000.ZZVL0000BMsg;
import business.servlet.ZZVL0000.constant.ZZVL0000Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/26   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0000CommonLogic {

    /**
     * Method name: dspScrn00
     * <dd>The method explanation: button Screen Control(Scrn00).
     * <dd>Remarks:
     * @param scrnMsg ZZVL0000BMsg
     * @param handler S21CommonHandler
     */
    public static final void dspScrn00(ZZVL0000BMsg scrnMsg, S21CommonHandler handler) {

        // Button Control
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN1[0], ZZVL0000Constant.CMN_BTN1[1], ZZVL0000Constant.CMN_BTN1[2], 0, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN2[0], ZZVL0000Constant.CMN_BTN2[1], ZZVL0000Constant.CMN_BTN2[2], 0, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN3[0], ZZVL0000Constant.CMN_BTN3[1], ZZVL0000Constant.CMN_BTN3[2], 0, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN4[0], ZZVL0000Constant.CMN_BTN4[1], ZZVL0000Constant.CMN_BTN4[2], 0, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN5[0], ZZVL0000Constant.CMN_BTN5[1], ZZVL0000Constant.CMN_BTN5[2], 0, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN6[0], ZZVL0000Constant.CMN_BTN6[1], ZZVL0000Constant.CMN_BTN6[2], 0, null);
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonProperties(ZZVL0000Constant.CMN_BTN7[0], "Delete", ZZVL0000Constant.CMN_BTN7[2], 1, null);
        } else {
            handler.setButtonProperties(ZZVL0000Constant.CMN_BTN7[0], "Delete", ZZVL0000Constant.CMN_BTN7[2], 0, null);
        }
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN8[0], ZZVL0000Constant.CMN_BTN8[1], ZZVL0000Constant.CMN_BTN8[2], 0, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN9[0], ZZVL0000Constant.CMN_BTN9[1], ZZVL0000Constant.CMN_BTN9[2], 0, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN10[0], ZZVL0000Constant.CMN_BTN10[1], ZZVL0000Constant.CMN_BTN10[2], 1, null);

        S21TableColorController tblColor = new S21TableColorController("ZZVL0000Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        if (scrnMsg.A.getValidCount() > 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_3.setInputProtected(true);
            }
        }

    }

    /**
     * Method name: dspScrn01
     * <dd>The method explanation: button Screen Control(Scrn01).
     * <dd>Remarks:
     * @param scrnMsg ZZVL0000BMsg
     * @param handler S21CommonHandler
     */
    public static final void dspScrn01(ZZVL0000BMsg scrnMsg, S21CommonHandler handler) {

        // Button Control
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN1[0], ZZVL0000Constant.CMN_BTN1[1], ZZVL0000Constant.CMN_BTN1[2], 0, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN2[0], "CMN_Add", ZZVL0000Constant.CMN_BTN2[2], 1, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN3[0], ZZVL0000Constant.CMN_BTN3[1], ZZVL0000Constant.CMN_BTN3[2], 0, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN4[0], ZZVL0000Constant.CMN_BTN4[1], ZZVL0000Constant.CMN_BTN4[2], 0, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN5[0], ZZVL0000Constant.CMN_BTN5[1], ZZVL0000Constant.CMN_BTN5[2], 0, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN6[0], ZZVL0000Constant.CMN_BTN6[1], ZZVL0000Constant.CMN_BTN6[2], 0, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN7[0], ZZVL0000Constant.CMN_BTN7[1], ZZVL0000Constant.CMN_BTN7[2], 0, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN8[0], ZZVL0000Constant.CMN_BTN8[1], ZZVL0000Constant.CMN_BTN8[2], 0, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN9[0], ZZVL0000Constant.CMN_BTN9[1], ZZVL0000Constant.CMN_BTN9[2], 0, null);
        handler.setButtonProperties(ZZVL0000Constant.CMN_BTN10[0], ZZVL0000Constant.CMN_BTN10[1], ZZVL0000Constant.CMN_BTN10[2], 1, null);

        S21TableColorController tblColor = new S21TableColorController("ZZVL0000Scrn01", scrnMsg);
        tblColor.setAlternateRowsBG("B", scrnMsg.B);

    }

}
