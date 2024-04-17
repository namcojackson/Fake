/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0880.common;

import static business.servlet.NSAL0880.constant.NSAL0880Constant.SCREEN_ID;
import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0880.NSAL0880BMsg;
import business.servlet.NSAL0880.NSAL0880_ABMsg;
import business.servlet.NSAL0880.constant.NSAL0880Constant.BTN_LBL;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0880CommonLogic {

    /**
     * setRowColors
     * @param scrnMsg NSAL0880BMsg
     */
    public static void setRowColors(NSAL0880BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * initControlCommonButton
     * @param scrnAppli EZDCommonHandler
     */
    public static void initControlCommonButton(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", BTN_LBL.SAVE.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn2", "", BTN_LBL.SUBMIT.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn3", "", BTN_LBL.APPLY.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn4", "", BTN_LBL.APPROVE.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn5", "", BTN_LBL.REJECT.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn6", "", BTN_LBL.DOWNLOAD.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn7", "", BTN_LBL.DELETE.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", BTN_LBL.CLEAR.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn9", "", BTN_LBL.RESET.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Close", BTN_LBL.CLOSE.getBtnLbl(), 0, null);

    }

    /**
     * initCommonButton
     * @param scrnAppli EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonEnabled("btn8", false);
        scrnAppli.setButtonEnabled("btn10", true);
    }

    /**
     * control Inactive Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0880BMsg
     */
    public static void protectFields(EZDCommonHandler scrnAppli, NSAL0880BMsg scrnMsg) {

        scrnMsg.serNum.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0880_ABMsg abMsg = scrnMsg.A.no(i);

            abMsg.mtrLbDescTxt_A.setInputProtected(true);
            abMsg.readMtrCnt_A.setInputProtected(true);
            abMsg.dsMsgTxt_A.setInputProtected(true);
            abMsg.dsMtrProcStsDescTxt_A.setInputProtected(true);
        }
    }

    /**
     * set Background Color
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0880BMsg
     */
    public static void setBackgroundColor(EZDCommonHandler scrnAppli, NSAL0880BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController("NSAL0880Scrn00", scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);

        String preMtrLbDescTxt = null;
        boolean changeColor = false;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (i > 0 && !preMtrLbDescTxt.equals(scrnMsg.A.no(i).mtrLbDescTxt_A.getValue())) {
                changeColor = !changeColor;
            }
            if (changeColor) {
                tblColor.setRowStyle("A", i, "pEvenNumberBGcolor");
            }
            preMtrLbDescTxt = scrnMsg.A.no(i).mtrLbDescTxt_A.getValue();
        }
    }
}
