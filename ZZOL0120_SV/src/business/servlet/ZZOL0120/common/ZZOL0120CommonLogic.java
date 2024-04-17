/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/08/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.ZZOL0120.common;

import business.blap.ZZOL0120.ZZOL0120CMsg;
import business.servlet.ZZOL0120.ZZOL0120BMsg;
import business.servlet.ZZOL0120.constant.ZZOL0120Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * Method name: Screen processing Common Logic
 */
public class ZZOL0120CommonLogic implements ZZOL0120Constant {
    /**
     * Method name: doClear
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param sGlblCmpyCd String
     * @param cMsg ZZOL0100CMsg
     */
    public static void doClear(String sGlblCmpyCd, ZZOL0120CMsg cMsg) {

        cMsg.clear();
        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        cMsg.glblCmpyCd.setValue(sGlblCmpyCd);
        cMsg.glblCmpyCd_BK.setValue(sGlblCmpyCd);

    }

    /**
     * Method name: dspScrn00
     * <dd>The method explanation: button Screen Control.
     * <dd>Remarks:
     * @param scrnMsg ZZOL0120BMsg
     * @param handler S21CommonHandler
     */
    public static void dspScrn00(ZZOL0120BMsg scrnMsg, S21CommonHandler handler) {

        // Common Button Control
        handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
        handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null);
        handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
        handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
        handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
        handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 1, null);
        handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
        handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 1, null);
        handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);

        // Common Button Confirm Message Settings       
        handler.setButtonConfirmMsg(CMN_BTN7[1], "ZZM8102I" , new String[] {"Delete"}, 0);
        handler.setButtonConfirmMsg(CMN_BTN10[1], "ZZM8101I", new String[] {"Return"}, 0);

        // Color Settings
        S21TableColorController tblColor = new S21TableColorController(SCREEN_NAME_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        // button Settings
        handler.setButtonEnabled("Upd", false);
        handler.setButtonEnabled("Add", true);
        scrnMsg.menuProcGrpCd.setInputProtected(false);
        if ((scrnMsg.menuProcGrpCd.getValue() != null) && (scrnMsg.menuProcGrpCd.getValue() != "")) {
            handler.setButtonEnabled("Upd", true);
            handler.setButtonEnabled("Add", false);
            scrnMsg.menuProcGrpCd.setInputProtected(true);
        }
        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 1, null);
        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
        }
    }

    /**
     * Method name: dspScrn01
     * <dd>The method explanation: button Screen Control.
     * <dd>Remarks:
     * @param scrnMsg ZZOL0120BMsg
     * @param handler S21CommonHandler
     */
    public static void dspScrn01(ZZOL0120BMsg scrnMsg, S21CommonHandler handler) {

        // Common Button Control
        handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
        handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null);
        handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
        handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
        handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
        handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 1, null);
        handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
        handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 0, null);
        handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);

        // Common Button Confirm Message Settings       
        handler.setButtonConfirmMsg(CMN_BTN7[1], "ZZM8102I" , new String[] {"Delete"}, 0);
        handler.setButtonConfirmMsg(CMN_BTN10[1], "ZZM8101I", new String[] {"Return"}, 0);

        // Color Settings
        S21TableColorController tblColor = new S21TableColorController(SCREEN_NAME_01, scrnMsg);
        tblColor.setAlternateRowsBG("B", scrnMsg.B);

        // button Settings
        handler.setButtonEnabled("Upd", false);
        handler.setButtonEnabled("Add", true);
        if ((scrnMsg.menuProcId_B1.getValue() != null) && (scrnMsg.menuProcId_B1.getValue() != "")) {
            handler.setButtonEnabled("Upd", true);
        }
        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 1, null);
        if (scrnMsg.B.getValidCount() == 0) {
            handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
        }
    }

    /**
     * Method name: dspScrn02
     * <dd>The method explanation: button Screen Control.
     * <dd>Remarks:
     * @param scrnMsg ZZOL0120BMsg
     * @param handler S21CommonHandler
     */
    public static void dspScrn02(ZZOL0120BMsg scrnMsg, S21CommonHandler handler) {

        // Common Button Control
        handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
        handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null);
        handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
        handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
        handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
        handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 1, null);
        handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
        handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 0, null);
        handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);

        // Common Button Confirm Message Settings       
        handler.setButtonConfirmMsg(CMN_BTN7[1], "ZZM8102I" , new String[] {"Delete"}, 0);
        handler.setButtonConfirmMsg(CMN_BTN10[1], "ZZM8101I", new String[] {"Return"}, 0);

        // Color Settings
        S21TableColorController tblColor = new S21TableColorController(SCREEN_NAME_02, scrnMsg);
        tblColor.setAlternateRowsBG("C", scrnMsg.C);

        // button Settings
        handler.setButtonEnabled("Upd", false);
        handler.setButtonEnabled("Add", true);
        if ((scrnMsg.upTabCd_C1.getValue() != null) && (scrnMsg.upTabCd_C1.getValue() != "")) {
            handler.setButtonEnabled("Upd", true);
        }
        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 1, null);
        if (scrnMsg.C.getValidCount() == 0) {
            handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
        }
    }

}
