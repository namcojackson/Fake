/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0420.common;

import static business.servlet.NSAL0420.constant.NSAL0420Constant.SCREEN_ID;
import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0420.NSAL0420BMsg;
import business.servlet.NSAL0420.NSAL0420_ABMsg;
import business.servlet.NSAL0420.constant.NSAL0420Constant.BTN_LBL;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Representative Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0420CommonLogic {

    /**
     * setRowColors
     * @param scrnMsg NSAL0420BMsg
     */
    public static void setRowColors(NSAL0420BMsg scrnMsg) {

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

        scrnAppli.setButtonEnabled("btn8", true);
        scrnAppli.setButtonEnabled("btn10", true);
    }

    /**
     * control Inactive Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0420BMsg
     */
    public static void protectFields(//
            EZDCommonHandler scrnAppli //
            , NSAL0420BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0420_ABMsg abMsg = scrnMsg.A.no(i);

            abMsg.svcRgNm_A.setInputProtected(true);
            abMsg.lineBizTpDescTxt_A.setInputProtected(true);
            abMsg.svcContrBrCd_A.setInputProtected(true);
            abMsg.svcContrBrDescTxt_A.setInputProtected(true);
            abMsg.xxGenlFldAreaTxt_A.setInputProtected(true);
            abMsg.orgFuncRoleTpNm_A.setInputProtected(true);
        }
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSAL0420BMsg
     */
    public static void commonAddCheckItem(NSAL0420BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.svcRgNm_H);
        scrnMsg.addCheckItem(scrnMsg.lineBizTpDescTxt_H);
        scrnMsg.addCheckItem(scrnMsg.svcContrBrCd_H);
        scrnMsg.addCheckItem(scrnMsg.svcContrBrDescTxt_H);
        scrnMsg.addCheckItem(scrnMsg.xxGenlFldAreaTxt_H);
    }

    // ************* For Popup *************
    /**
     * Check lastGuard is 'CLOSE' event.
     * @param lastGuard String
     * @return If lastGuard is 'CLOSE' event.
     */
    public static boolean isClosedEvent(String lastGuard) {
        return BTN_LBL.CLOSE.getBtnLbl().toLowerCase().equals(lastGuard.toLowerCase());
    }
}
