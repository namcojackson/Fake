/**
 * Copyright(c)2012 Canon USA Inc. All rights reserved.
 */
package business.servlet.NMAL3300.common;

import static business.servlet.NMAL3300.constant.NMAL3300Constant.BTN_CMN_CLEAR;
import static business.servlet.NMAL3300.constant.NMAL3300Constant.BTN_CMN_CLOSE;
import static business.servlet.NMAL3300.constant.NMAL3300Constant.BTN_NM_OPEN_WIN_ATT;
import static business.servlet.NMAL3300.constant.NMAL3300Constant.DEF_ROW_BG_COLOR;
import static business.servlet.NMAL3300.constant.NMAL3300Constant.HTML_TBL_ID_D_LEFT;
import static business.servlet.NMAL3300.constant.NMAL3300Constant.HTML_TBL_ID_D_RIGHT;
import static business.servlet.NMAL3300.constant.NMAL3300Constant.SCREEN_ID_00;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL3300.NMAL3300BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
/**
 *<pre>
 * Resource Search NMAL24000CommonLogic
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         N.Sugiura       Create          N/A
 * 2016/02/24   SRAA            Y.Chen          Update          QC#4482
 * 2016/04/26   SRAA            Y.Chen          Update          QC#6186
 * 2018/11/12   Fujitsu         Hd.Sugawara     Update          QC#28683
 *</pre>
 */

public class NMAL3300CommonLogic {

    /**
     * <pre>
     * Details are initialized (sorting sign deletion and BG color control).
     * </pre>
     * @param scrnMsg NMAL3300BMsg
     */
    public static void setDefaultRowBgColor(NMAL3300BMsg scrnMsg) {
        // Del Start 2018/11/12 QC#28683
        // Set Default Row
        //if (DPLY_TAB_EXPAND.equals(scrnMsg.xxDplyTab_A.getValue())) {
        //    for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
        //        if (scrnMsg.A.no(i).xxYesNoCd_A1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
        //            S21TableColorController tblColor = new S21TableColorController(SCREEN_ID_00, scrnMsg);
        //            tblColor.setRowStyle("A_Left", i, DEF_ROW_BG_COLOR);
        //            tblColor.setRowStyle("A_Right", i, DEF_ROW_BG_COLOR);
        //        }
        //    }
        //}
        // Del End 2018/11/12 QC#28683
        
        // Set Default Row
        // Del Start 2018/11/12 QC#28683
        //if (DPLY_TAB_EXPAND.equals(scrnMsg.xxDplyTab_D.getValue())) {
            // Del End 2018/11/12 QC#28683
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                if (scrnMsg.D.no(i).xxYesNoCd_D1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                    S21TableColorController tblColor = new S21TableColorController(SCREEN_ID_00, scrnMsg);
                    tblColor.setRowStyle("D_Left", i, DEF_ROW_BG_COLOR);
                    tblColor.setRowStyle("D_Right", i, DEF_ROW_BG_COLOR);
                }
            }
            // Del Start 2018/11/12 QC#28683
        //}
        // Del End 2018/11/12 QC#28683
        
    }
    /**
     * Set bottom button as Initial state
     * @param handler Handler of View
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * Set search button as Initial state
     * @param handler Handler of View
     */
    public static void initButton(EZDCommonHandler handler) {
//        handler.setButtonEnabled(NMAL3300Constant.BTN_SEARCH[0], true);
    }
    /**
     * Set description of detail matrix
     * @param scrnMsg Screen Message of NMAL3300
     * @param baseContents Color of Raw
     */
    public static void setBgColor(NMAL3300BMsg scrnMsg) {

        // Set HTML Table background color.
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID_00, scrnMsg);
        
        // Del Start 2018/11/12 QC#28683
        //if (DPLY_TAB_EXPAND.equals(scrnMsg.xxDplyTab_A.getValue())) {
        //    tblColor.clearRowsBG(HTML_TBL_ID_A_LEFT, scrnMsg.A);
        //    tblColor.clearRowsBG(HTML_TBL_ID_A_RIGHT, scrnMsg.A);
        //    tblColor.setAlternateRowsBG(HTML_TBL_ID_A_LEFT, scrnMsg.A);
        //    tblColor.setAlternateRowsBG(HTML_TBL_ID_A_RIGHT, scrnMsg.A);
        //}
        //if (DPLY_TAB_EXPAND.equals(scrnMsg.xxDplyTab_B.getValue())) {
        //    tblColor.clearRowsBG(HTML_TBL_ID_B, scrnMsg.B);
        //    tblColor.setAlternateRowsBG(HTML_TBL_ID_B, scrnMsg.B);
        //}
        //if (DPLY_TAB_EXPAND.equals(scrnMsg.xxDplyTab_C.getValue())) {
        //    tblColor.clearRowsBG(HTML_TBL_ID_C_LEFT, scrnMsg.C);
        //    tblColor.clearRowsBG(HTML_TBL_ID_C_RIGHT, scrnMsg.C);
        //    tblColor.setAlternateRowsBG(HTML_TBL_ID_C_LEFT, scrnMsg.C);
        //    tblColor.setAlternateRowsBG(HTML_TBL_ID_C_RIGHT, scrnMsg.C);
        //}
        //if (DPLY_TAB_EXPAND.equals(scrnMsg.xxDplyTab_D.getValue())) {
            // Del End 2018/11/12 QC#28683
            tblColor.clearRowsBG(HTML_TBL_ID_D_LEFT, scrnMsg.D);
            tblColor.clearRowsBG(HTML_TBL_ID_D_RIGHT, scrnMsg.D);
            tblColor.setAlternateRowsBG(HTML_TBL_ID_D_LEFT, scrnMsg.D);
            tblColor.setAlternateRowsBG(HTML_TBL_ID_D_RIGHT, scrnMsg.D);
            // Del Start 2018/11/12 QC#28683
        //}
        // Del End 2018/11/12 QC#28683

        NMAL3300CommonLogic.setDefaultRowBgColor(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.locNum_D);
    }

    // Del Start 2018/11/12 QC#28683
//    /**
//     * clearFilter
//     * @param scrnMsg Screen Message of NMAL3300
//     * @param baseContents Color of Raw
//     */
//    public static void clearFilter(NMAL3300BMsg scrnMsg) {
//
//        scrnMsg.hrchEffLvlTpCd_D.clear();
//        scrnMsg.hrchEffLvlTpCd_D2.clear();
//        scrnMsg.hrchEffLvlTpCd_D3.clear();
//        scrnMsg.dsSpclHdlgTpCd_D.clear();
//        scrnMsg.custEffLvlCd_D.clear();
//        scrnMsg.effFromDt_C.clear();
//        scrnMsg.effThruDt_C.clear();
//        scrnMsg.hrchEffLvlTpCd_D4.clear();
//        scrnMsg.lineBizTpCd_D.clear();
//        scrnMsg.dsBizAreaCd_D.clear();
//        scrnMsg.dsCustMsgTpCd_D.clear();
//        scrnMsg.custEffLvlCd_D2.clear();
//        scrnMsg.effFromDt_D.clear();
//        scrnMsg.effThruDt_D.clear();
//    }
    // Del End 2018/11/12 QC#28683

    /**
     * Protect Screen Items
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL3300BMsg
     */
    public static void protectScrn(EZDCommonHandler handler, NMAL3300BMsg scrnMsg) {

        // Del Start 2018/11/12 QC#28683
        //if (DPLY_TAB_EXPAND.equals(scrnMsg.xxDplyTab_A.getValue())) {
        //    for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
        //        scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
        //        scrnMsg.A.no(i).xxChkBox_A2.setInputProtected(true);
        //        scrnMsg.A.no(i).xxChkBox_A3.setInputProtected(true);
        //    }
        //}
        //if (DPLY_TAB_EXPAND.equals(scrnMsg.xxDplyTab_D.getValue())) {
            // Del End 2018/11/12 QC#28683
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                scrnMsg.D.no(i).xxChkBox_D1.setInputProtected(true);
                // QC#4482
                handler.setButtonEnabled(BTN_NM_OPEN_WIN_ATT, i, ZYPCommonFunc.hasValue(scrnMsg.D.no(i).ezBusinessID_D1.getValue()));
                // QC#6186
                scrnMsg.D.no(i).dsCustMsgTxt_D1.setInputProtected(true);
            }
            // Del Start 2018/11/12 QC#28683
        //}
        // Del End 2018/11/12 QC#28683
        scrnMsg.dsAcctNm_D.setInputProtected(true);
    }


}
