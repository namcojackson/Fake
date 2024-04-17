/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0780.common;

import static business.servlet.NSAL0780.constant.NSAL0780Constant.*;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0780.NSAL0780BMsg;

/**
 *<pre>
 * Fleet Rollup Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Hitachi         A.Kohinata      Create          N/A
 * 2016/01/21   Hitachi         T.Kanasaka      Update          QC3457
 *</pre>
 */
public class NSAL0780CommonLogic {

    /**
     * The initial state of the screen item is set.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0780BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0780BMsg scrnMsg) {

        initControlCommonButton(handler);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * init Control Common Button
     * @param handler EZDCommonHandler
     */
    public static void initControlCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0780BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL0780BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setInputProtected(false);
    }

    /**
     * control Inactive Field
     * @param scrnMsg NSAL0780BMsg
     */
    public static final void protectFields(NSAL0780BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).dsContrNum_A.setInputProtected(true);
            scrnMsg.A.no(i).mtrLbDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).fleetCalcProcDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).vldMsgTxt_A.setInputProtected(true);
        }
    }

    /**
     * set Row Colors
     * @param scrnMsg NSAL0780BMsg
     */
    public static void setRowColors(NSAL0780BMsg scrnMsg) {

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
     * commonAddCheckItem
     * @param scrnMsg NSAL0780BMsg
     */
    public static void commonAddCheckItem(NSAL0780BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H);
        scrnMsg.addCheckItem(scrnMsg.serNum_H);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum_H);
        scrnMsg.addCheckItem(scrnMsg.bllgFromDt_H);
        scrnMsg.addCheckItem(scrnMsg.bllgThruDt_H);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H1);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H2);
    }

    /**
     * clear Popup Parameter
     * @param scrnMsg NSAL0780BMsg
     */
    public static void clearPopupParameter(NSAL0780BMsg scrnMsg) {

        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.dsContrPk_P.clear();
        scrnMsg.dsContrDtlPk_P.clear();
        ZYPTableUtil.clear(scrnMsg.X);
        ZYPTableUtil.clear(scrnMsg.O);
    }
}
