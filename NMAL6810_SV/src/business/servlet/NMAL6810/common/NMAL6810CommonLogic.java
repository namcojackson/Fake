/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6810.common;

import static business.servlet.NMAL6810.constant.NMAL6810Constant.BTN_CMN_CLEAR;
import static business.servlet.NMAL6810.constant.NMAL6810Constant.BTN_CMN_CLOSE;
import static business.servlet.NMAL6810.constant.NMAL6810Constant.SCREEN_ID;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6810.NMAL6810BMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Item Master Template Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/17   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class NMAL6810CommonLogic {

    /**
     * initCommonButton
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * initScrn
     * @param scrnMsg NMAL6810BMsg
     * @param baseContents String[][]
     */
    public static void initScrn(NMAL6810BMsg scrnMsg, String[][] baseContents) {

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, baseContents);

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        scrnMsg.setFocusItem(scrnMsg.mdseItemTpCd_HS);
    }

    /**
     * pageNationScrn
     * @param scrnMsg NMAL6810BMsg
     */
    public static void pageNationScrn(NMAL6810BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        scrnMsg.setFocusItem(scrnMsg.mdseItemTpCd_HS);
    }
}
