/*
 * <pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6040.common;

import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6040.NMAL6040BMsg;
import business.servlet.NMAL6040.constant.NMAL6040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * NMAL6040 P&L Product Structure Pop Up
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ----------------------------------------------------------------------
 * 09/05/2012   Fujitsu         H.Mizutani      Update          N/A 
 * 13/09/2013   Fujitsu         A.Shinohara     Update          R-MS001
 *</pre>
 */
public class NMAL6040CommonLogic implements NMAL6040Constant {

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

        handler.setButtonEnabled(BTN_SEARCH[0], true);
    }

    /**
     * Set button as Initial state
     * @param scrnMsg Screen Message of NMAL6040
     */
    public static void clearMerchandise(NMAL6040BMsg scrnMsg) {

        String strModeCd = null;
        if (ZYPCommonFunc.hasValue(scrnMsg.xxModeCd)) {
            strModeCd = scrnMsg.xxModeCd.getValue();
        }

        // 20120905 Deviation of All Merchandise View -Mizutani
        scrnMsg.zerothProdCtrlCd.clear();
        scrnMsg.zerothProdCtrlNm.clear();
        scrnMsg.firstProdCtrlCd.clear();
        scrnMsg.firstProdCtrlNm.clear();
        scrnMsg.scdProdCtrlCd.clear();
        scrnMsg.scdProdCtrlNm.clear();
        scrnMsg.thirdProdCtrlCd.clear();
        scrnMsg.thirdProdCtrlNm.clear();
        scrnMsg.frthProdCtrlCd.clear();
        scrnMsg.frthProdCtrlNm.clear();
        scrnMsg.fifthProdCtrlCd.clear();
        scrnMsg.fifthProdCtrlNm.clear();
        scrnMsg.mdseCd.clear();
        scrnMsg.mdseNm.clear();
        scrnMsg.upcCd.clear();
        // 2013/05/13 Mod START R-MS001
        scrnMsg.mdseCatgCd.clear();
//        scrnMsg.oldMatNum.clear();
//        scrnMsg.lgcyOldMatCd.clear();
//        scrnMsg.dsMatGrpCd.clear();
//        scrnMsg.xtrnlMatGrpCd.clear();
//        scrnMsg.mdseRgtnTpCd.clear();
        // 2013/05/13 Mod E N D R-MS001
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        // 20120905 Deviation of All Merchandise View -Mizutani

        if (ZYPCommonFunc.hasValue(strModeCd)) {
            scrnMsg.xxModeCd.setValue(strModeCd);
        }

    }

    /**
     * Set description of detail matrix
     * @param scrnMsg Screen Message of NMAL6040
     * @param BaseContents Color of Raw
     */
    public static void initScrn(NMAL6040BMsg scrnMsg, String[][] BaseContents) {

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, BaseContents);

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A, 2);

        scrnMsg.setFocusItem(scrnMsg.zerothProdCtrlCd);

    }

    /**
     * Set search output to detail matrix
     * @param scrnMsg Screen Message of NMAL6040
     * @param index Number of detail matrix
     * @param pattern Number which differentiate how this pop up
     * return variable to View
     * @param arg Object to return View which called this pop up
     */
    public static void returnSelectData(NMAL6040BMsg scrnMsg, int index, int pattern, Object[] arg) {

        String zerothProdCtrlCdA1 = scrnMsg.A.no(index).zerothProdCtrlCd_A1.getValue();
        String zerothProdCtrlNmA1 = scrnMsg.A.no(index).zerothProdCtrlNm_A1.getValue();
        String firstProdCtrlCdA1 = scrnMsg.A.no(index).firstProdCtrlCd_A1.getValue();
        String firstProdCtrlNmA1 = scrnMsg.A.no(index).firstProdCtrlNm_A1.getValue();
        String scdProdCtrlCdA1 = scrnMsg.A.no(index).scdProdCtrlCd_A1.getValue();
        String scdProdCtrlNmA1 = scrnMsg.A.no(index).scdProdCtrlNm_A1.getValue();
        String thirdProdCtrlCdA1 = scrnMsg.A.no(index).thirdProdCtrlCd_A1.getValue();
        String thirdProdCtrlNmA1 = scrnMsg.A.no(index).thirdProdCtrlNm_A1.getValue();
        String frthProdCtrlCdA1 = scrnMsg.A.no(index).frthProdCtrlCd_A1.getValue();
        String frthProdCtrlNmA1 = scrnMsg.A.no(index).frthProdCtrlNm_A1.getValue();
        String fifthProdCtrlCdA1 = scrnMsg.A.no(index).fifthProdCtrlCd_A1.getValue();
        String fifthProdCtrlNmA1 = scrnMsg.A.no(index).fifthProdCtrlNm_A1.getValue();
        String mdseCdA1 = scrnMsg.A.no(index).mdseCd_A1.getValue();
        String mdseNmA1 = scrnMsg.A.no(index).mdseNm_A1.getValue();

        if (null != arg) {

            Object[] params = arg;
            EZDBStringItem param14 = (EZDBStringItem) params[14];
            EZDBStringItem param15 = (EZDBStringItem) params[15];
            EZDBStringItem param16 = (EZDBStringItem) params[16];
            EZDBStringItem param17 = (EZDBStringItem) params[17];
            EZDBStringItem param18 = (EZDBStringItem) params[18];
            EZDBStringItem param19 = (EZDBStringItem) params[19];
            EZDBStringItem param20 = (EZDBStringItem) params[20];
            EZDBStringItem param21 = (EZDBStringItem) params[21];
            EZDBStringItem param22 = (EZDBStringItem) params[22];
            EZDBStringItem param23 = (EZDBStringItem) params[23];
            EZDBStringItem param24 = (EZDBStringItem) params[24];
            EZDBStringItem param25 = (EZDBStringItem) params[25];
            EZDBStringItem param26 = (EZDBStringItem) params[26];
            EZDBStringItem param27 = (EZDBStringItem) params[27];

            param14.setValue(zerothProdCtrlCdA1);
            param15.setValue(zerothProdCtrlNmA1);
            param16.clear();
            param17.clear();
            param18.clear();
            param19.clear();
            param20.clear();
            param21.clear();
            param22.clear();
            param23.clear();
            param24.clear();
            param25.clear();
            param26.clear();
            param27.clear();
            // 20120905 Deviation of All Merchandise View - Mizutani
            if (pattern == SELECT_PROD_LINE_GRP) {
                return;
            }
            param16.setValue(firstProdCtrlCdA1);
            param17.setValue(firstProdCtrlNmA1);
            if (pattern == SELECT_PROD_LINE) {
                return;
            }
            param18.setValue(scdProdCtrlCdA1);
            param19.setValue(scdProdCtrlNmA1);
            if (pattern == SELECT_PROD_LVL_2_CD) {
                return;
            }
            param20.setValue(thirdProdCtrlCdA1);
            param21.setValue(thirdProdCtrlNmA1);
            if (pattern == SELECT_PROD_LVL_3_CD) {
                return;
            }
            param22.setValue(frthProdCtrlCdA1);
            param23.setValue(frthProdCtrlNmA1);
            if (pattern == SELECT_PROD_LVL_4_CD) {
                return;
            }
            param24.setValue(fifthProdCtrlCdA1);
            param25.setValue(fifthProdCtrlNmA1);
            if (pattern == SELECT_MDSE_GRP_CD) {
                return;
            }
            param26.setValue(mdseCdA1);
            param27.setValue(mdseNmA1);
            // 20120905 Deviation of All Merchandise View - Mizutani
        }

    }

}
