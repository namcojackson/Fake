/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0510.common;

import static business.servlet.NSAL0510.constant.NSAL0510Constant.SCREEN_ID;
import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0510.NSAL0510BMsg;
import business.servlet.NSAL0510.NSAL0510_ABMsg;
import business.servlet.NSAL0510.constant.NSAL0510Constant.BTN_LBL;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Sub Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/01/08   Hitachi         T.Tsuchida      Update          QC#2844
 * 2016/01/12   Hitachi         T.Tsuchida      Update          QC#2889
 * 2016/02/12   Hitachi         K.Kasai         Update          QC#3710
 *</pre>
 */
public class NSAL0510CommonLogic {

    /**
     * setRowColors
     * @param scrnMsg NSAL0510BMsg
     */
    public static void setRowColors(NSAL0510BMsg scrnMsg) {

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
        scrnAppli.setButtonProperties("btn10", "CMN_Return", BTN_LBL.RETURN.getBtnLbl(), 0, null);

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
     * @param scrnMsg NSAL0510BMsg
     */
    public static void protectFields(//
            EZDCommonHandler scrnAppli //
            , NSAL0510BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0510_ABMsg abMsg = scrnMsg.A.no(i);

            abMsg.vndCd_A.setInputProtected(true);
            abMsg.prntVndNm_A.setInputProtected(true);
            abMsg.dsAcctNum_A.setInputProtected(true);
            abMsg.dsAcctNm_A.setInputProtected(true);
            abMsg.dsContrNum_A.setInputProtected(true);
            // mod start 2016/02/12 CSA Defect#3710
            abMsg.xxScrItem10Txt_A.setInputProtected(true);
            // mod end 2016/02/12 CSA Defect#3710
            abMsg.dlrFleetNum_A.setInputProtected(true);
            abMsg.serNum_A.setInputProtected(true);
            abMsg.t_MdlNm_A.setInputProtected(true);
            abMsg.effFromDt_A.setInputProtected(true);
            abMsg.effThruDt_A.setInputProtected(true);
        }
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSAL0510BMsg
     */
    public static void commonAddCheckItem(NSAL0510BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.vndCd_H);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum_H);
        scrnMsg.addCheckItem(scrnMsg.dsContrCtrlStsNm_H);
        scrnMsg.addCheckItem(scrnMsg.dlrFleetNum_H);
        scrnMsg.addCheckItem(scrnMsg.serNum_H);
        scrnMsg.addCheckItem(scrnMsg.mdlActvFlg_H);
        scrnMsg.addCheckItem(scrnMsg.t_MdlNm_H);
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
