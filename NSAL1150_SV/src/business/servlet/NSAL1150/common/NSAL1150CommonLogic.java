/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1150.common;

import static business.servlet.NSAL1150.constant.NSAL1150Constant.*;
import business.servlet.NSAL1150.NSAL1150BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Hitachi         T.Kanasaka      Create          N/A
 * 2016/03/30   Hitachi         A.Kohinata      Update          QC#6066
 *</pre>
 */
public class NSAL1150CommonLogic {

    /**
     * addCheckItem
     * @param scrnMsg NSAL1150BMsg
     */
    public static void addCheckItem(NSAL1150BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.condSqlTxt_CU);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm);
        scrnMsg.addCheckItem(scrnMsg.condSqlTxt_CO);
    }

    /**
     * setupScreenItems
     * @param handler S21CommonHandler
     * @param scrnMsg NSAL1150BMsg
     */
    public static void setupScreenItems(S21CommonHandler handler, NSAL1150BMsg scrnMsg) {
        // Initialize Common Button
        scrnMsg.setInputProtected(false);
        handler.setButtonEnabledAll();
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        // Initialize digit items
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).bwAvgMlyCopyCnt_A.setAppFracDigit(DEF_FRAC_DIGIT_NUM_INT);
            scrnMsg.A.no(i).colorAvgMlyCopyCnt_A.setAppFracDigit(DEF_FRAC_DIGIT_NUM_INT);
            scrnMsg.A.no(i).bwPrrtQty_A.setAppFracDigit(DEF_FRAC_DIGIT_NUM_INT);
            scrnMsg.A.no(i).colorPrrtQty_A.setAppFracDigit(DEF_FRAC_DIGIT_NUM_INT);
            scrnMsg.A.no(i).bwUsedQty_A.setAppFracDigit(DEF_FRAC_DIGIT_NUM_INT);
            scrnMsg.A.no(i).colorUsedQty_A.setAppFracDigit(DEF_FRAC_DIGIT_NUM_INT);
            scrnMsg.A.no(i).splyAllocPct_AB.setAppFracDigit(DEF_FRAC_DIGIT_NUM_PCT);
            scrnMsg.A.no(i).splyAllocPct_AC.setAppFracDigit(DEF_FRAC_DIGIT_NUM_PCT);
        }
    }

    /**
     * clearPopupParam
     * @param scrnMsg NSAL1150BMsg
     */
    public static void clearPopupParam(NSAL1150BMsg scrnMsg) {
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
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
        scrnMsg.xxPopPrm_17.clear();
        scrnMsg.xxPopPrm_18.clear();
        scrnMsg.xxPopPrm_19.clear();
        scrnMsg.xxPopPrm_20.clear();
        scrnMsg.xxPopPrm_21.clear();
        scrnMsg.xxPopPrm_22.clear();
        scrnMsg.xxPopPrm_23.clear();
        scrnMsg.xxScrEventNm.clear();
    }

    /**
     * setupListTable
     * @param scrnMsg NSAL1150BMsg
     */
    public static void setupListTable(NSAL1150BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        }
        for (int num = 0; num < scrnMsg.A.getValidCount(); num++) {
            String lineType = scrnMsg.A.no(num).xxTpCd_A.getValue();
            if (LINE_TYPE_TOTAL.equals(lineType)) {
                tblColor.setRowStyle("A", num, "totalLineBGcolor");
            }
            scrnMsg.A.no(num).dsAcctNm_A.setInputProtected(true);
            scrnMsg.A.no(num).mdseDescShortTxt_A.setInputProtected(true);
            scrnMsg.A.no(num).t_MdlNm_A.setInputProtected(true);
            scrnMsg.A.no(num).serNum_A.setInputProtected(true);
        }
    }
}
