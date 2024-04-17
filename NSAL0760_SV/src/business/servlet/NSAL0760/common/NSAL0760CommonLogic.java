/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0760.common;

import static business.servlet.NSAL0760.constant.NSAL0760Constant.*;
import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;

import business.servlet.NSAL0760.NSAL0760BMsg;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Contract Status History
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Hitachi         K.Kishimoto     Create          N/A
 * 2023/05/15   CSA             t.aizawa        Update          QC#59398
 *</pre>
 */
public class NSAL0760CommonLogic {

    /**
     * setRowColors
     * @param scrnMsg NSAL0760BMsg
     */
    public static void setRowColors(NSAL0760BMsg scrnMsg) {

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
     * itemProtect
     * @param scrnMsg NSAL0760BMsg
     */
    public static void itemProtect(NSAL0760BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).dsContrNum.setInputProtected(true);
            scrnMsg.A.no(i).serNum.setInputProtected(true);
            // START 2023/05/15 t.aizawa [QC#59398,ADD]
            scrnMsg.A.no(i).mdseDescShortTxt_AS.setInputProtected(true);
            // END 2023/05/15 t.aizawa [QC#59398,ADD]
            scrnMsg.A.no(i).xxDplyByCdNmCnctTxt.setInputProtected(true);
            scrnMsg.A.no(i).dsContrPrcEffSqNum.setInputProtected(true);
            scrnMsg.A.no(i).dsContrStsDescTxt.setInputProtected(true);
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

        scrnAppli.setButtonEnabled("btn8", true);
        scrnAppli.setButtonEnabled("btn10", true);
    }

}
