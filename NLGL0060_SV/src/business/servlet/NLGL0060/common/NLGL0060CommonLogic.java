/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0060.common;

import static business.servlet.NLGL0060.constant.NLGL0060Constant.BTN_SEARCH;
import static business.servlet.NLGL0060.constant.NLGL0060Constant.BTN_CMN_SAVE;
import static business.servlet.NLGL0060.constant.NLGL0060Constant.BTN_CMN_SUBMIT;
import static business.servlet.NLGL0060.constant.NLGL0060Constant.BTN_CMN_APPLY;
import static business.servlet.NLGL0060.constant.NLGL0060Constant.BTN_CMN_APPROVE;
import static business.servlet.NLGL0060.constant.NLGL0060Constant.BTN_CMN_REJECT;
import static business.servlet.NLGL0060.constant.NLGL0060Constant.BTN_CMN_DOWNLOAD;
import static business.servlet.NLGL0060.constant.NLGL0060Constant.BTN_CMN_DETELE;
import static business.servlet.NLGL0060.constant.NLGL0060Constant.BTN_CMN_CLEAR;
import static business.servlet.NLGL0060.constant.NLGL0060Constant.BTN_CMN_RESET;
import static business.servlet.NLGL0060.constant.NLGL0060Constant.BTN_CMN_RETURN;

import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLGL0060.NLGL0060BMsg;
import business.servlet.NLGL0060.constant.NLGL0060Constant;

/**
 *<pre>
 * Business ID : NLGL0060
 * Function Name : Common Logic (SV)
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/01/09   CITS            M.Furugoori         Create          N/A
 *</pre>
 */

public class NLGL0060CommonLogic {
    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NLGL0060BMsg
     * @param functionList List<String>S
     */
    public static void ctrlScrnItem(EZDCommonHandler handler, NLGL0060BMsg scrnMsg, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible
        // Header Control
        scrnMsg.whCd_H.setInputProtected(false);
        scrnMsg.wmsTaskCd_H.setInputProtected(false);
        scrnMsg.wmsTaskCd_P.setInputProtected(false);
        scrnMsg.wmsTaskNm_P.setInputProtected(false);
        scrnMsg.procStsCd_H.setInputProtected(false);
        scrnMsg.procStsCd_P.setInputProtected(false);
        scrnMsg.procStsDescTxt_P.setInputProtected(false);
        scrnMsg.wmsInbdTrxPk_H.setInputProtected(false);
        scrnMsg.wmsMdseCd_H.setInputProtected(false);

        // Detail Header Control
        scrnMsg.xxPageShowFromNum.setInputProtected(false);
        scrnMsg.xxPageShowToNum.setInputProtected(false);
        scrnMsg.xxPageShowOfNum.setInputProtected(false);

        // Button activation
        handler.setButtonEnabled(BTN_SEARCH, true);

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

    }

    /**
     * The method explanation: The display control of the screen item
     * for Search screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NLGL0060BMsg
     * @param functionList List<String>S
     */
    public static void ctrlScrnItemSearch(EZDCommonHandler handler, NLGL0060BMsg scrnMsg, List<String> functionList) {
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
    }

    /**
     * searchItemCheck
     * @param scrnMsg NLGL0060BMsg
     */
    public static void searchItemCheck(NLGL0060BMsg scrnMsg) {

        // mandatory check
        if (!ZYPCommonFunc.hasValue(scrnMsg.whCd_H) && !ZYPCommonFunc.hasValue(scrnMsg.wmsTaskCd_H) && !ZYPCommonFunc.hasValue(scrnMsg.procStsCd_H) && !ZYPCommonFunc.hasValue(scrnMsg.wmsInbdTrxPk_H)
                && !ZYPCommonFunc.hasValue(scrnMsg.wmsMdseCd_H)) {

            scrnMsg.whCd_H.setErrorInfo(1, NLGL0060Constant.NLZM2276E);
            scrnMsg.wmsTaskCd_H.setErrorInfo(1, NLGL0060Constant.NLZM2276E);
            scrnMsg.procStsCd_H.setErrorInfo(1, NLGL0060Constant.NLZM2276E);
            scrnMsg.wmsInbdTrxPk_H.setErrorInfo(1, NLGL0060Constant.NLZM2276E);
            scrnMsg.wmsMdseCd_H.setErrorInfo(1, NLGL0060Constant.NLZM2276E);
            scrnMsg.setMessageInfo(NLGL0060Constant.ZZM9037E);
        }

        // check Item
        scrnMsg.addCheckItem(scrnMsg.whCd_H);
        scrnMsg.addCheckItem(scrnMsg.wmsTaskCd_H);
        scrnMsg.addCheckItem(scrnMsg.procStsCd_H);
        scrnMsg.addCheckItem(scrnMsg.wmsInbdTrxPk_H);
        scrnMsg.addCheckItem(scrnMsg.wmsMdseCd_H);
    }

    /**
     * setTableScreen
     * @param scrnMsg NLGL0060BMsg
     */
    public static void setTableScreen(NLGL0060BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(NLGL0060Constant.SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        
        for (int rowIndex = 0; rowIndex < scrnMsg.A.getValidCount(); rowIndex++) {
            scrnMsg.A.no(rowIndex).serNum_A1.setInputProtected(true);
        }
    }

}
