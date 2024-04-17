/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0310.common;

import static business.servlet.NSBL0310.constant.NSBL0310Constant.BTN_CMN_APPLY;
import static business.servlet.NSBL0310.constant.NSBL0310Constant.BTN_CMN_BLANK4;
import static business.servlet.NSBL0310.constant.NSBL0310Constant.BTN_CMN_BLANK5;
import static business.servlet.NSBL0310.constant.NSBL0310Constant.BTN_CMN_BLANK6;
import static business.servlet.NSBL0310.constant.NSBL0310Constant.BTN_CMN_BLANK7;
import static business.servlet.NSBL0310.constant.NSBL0310Constant.BTN_CMN_CLEAR;
import static business.servlet.NSBL0310.constant.NSBL0310Constant.BTN_CMN_RESET;
import static business.servlet.NSBL0310.constant.NSBL0310Constant.BTN_CMN_RETURN;
import static business.servlet.NSBL0310.constant.NSBL0310Constant.BTN_CMN_SAVE;
import static business.servlet.NSBL0310.constant.NSBL0310Constant.BTN_CMN_SUBMIT;
import static business.servlet.NSBL0310.constant.NSBL0310Constant.SEARCH_BTN;
import static business.servlet.NSBL0310.constant.NSBL0310Constant.SHOW_BRANCH_BTN;
import static business.servlet.NSBL0310.constant.NSBL0310Constant.SCREEN_ID;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSBL0310.NSBL0310BMsg;


/**
 * <pre>
 * Service Request By Manager
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Harada        Create          N/A
 *</pre>
 */
public class NSBL0310CommonLogic {

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     */
    public static final void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK4[0], BTN_CMN_BLANK4[1], BTN_CMN_BLANK4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK5[0], BTN_CMN_BLANK5[1], BTN_CMN_BLANK5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        handler.setButtonEnabled(BTN_CMN_SAVE[0], false);
        handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
        handler.setButtonEnabled(BTN_CMN_APPLY[0], false);
        handler.setButtonEnabled(BTN_CMN_BLANK4[0], false);
        handler.setButtonEnabled(BTN_CMN_BLANK5[0], false);
        handler.setButtonEnabled(BTN_CMN_BLANK6[0], false);
        handler.setButtonEnabled(BTN_CMN_BLANK7[0], false);
        handler.setButtonEnabled(BTN_CMN_CLEAR[0], true);
        handler.setButtonEnabled(BTN_CMN_RESET[0], false);
        handler.setButtonEnabled(BTN_CMN_RETURN[0], true);

        handler.setButtonEnabled(SEARCH_BTN, true);
        handler.setButtonEnabled(SHOW_BRANCH_BTN, true);

    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NSBL0310BMsg
     */
    public static void initParam(NSBL0310BMsg scrnMsg) {
        scrnMsg.xxPopPrm_0.clear();
        scrnMsg.xxPopPrm_1.clear();
        scrnMsg.xxPopPrm_2.clear();
        scrnMsg.xxPopPrm_3.clear();
        scrnMsg.xxPopPrm_4.clear();
        scrnMsg.xxPopPrm_5.clear();
        scrnMsg.xxPopPrm_6.clear();
    }

    /**
     * setProtected
     * @param scrnMsg NSBL0310BMsg
     */
    public static void setProtected(NSBL0310BMsg scrnMsg) {
        scrnMsg.orgDescTxt_H.setInputProtected(true);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mgrNm_A.setInputProtected(true);
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg NSBL0310BMsg
     */
    public static void addCheckItem(NSBL0310BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.orgCd_HT);
    }

    /**
     * setTableBGColor
     * @param scrnMsg NSBL0310BMsg
     */
    public static void setTableBGColor(NSBL0310BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.clearRowsBG("A", scrnMsg.A);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        } else {
            tblColor.clearRowsBG("A", scrnMsg.A);
        }
    }
}
