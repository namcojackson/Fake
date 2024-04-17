/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0210.common;

import static business.servlet.NFAL0210.constant.NFAL0210Constant.*;

import java.math.BigDecimal;

import business.servlet.NFAL0210.NFAL0210BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Manual Journal Entry Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/13   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFAL0210CommonLogic {

    /**
     * setupScreenItems
     * @param handler S21CommonHandler
     * @param scrnMsg NFAL0210BMsg
     */
    public static void setupScreenItems(S21CommonHandler handler, NFAL0210BMsg scrnMsg) {
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

        scrnMsg.jrnlCatgDescTxt.setInputProtected(true);

        handler.setButtonEnabled(BTN_DETAIL, false);
    }

    /**
     * addCheckItem
     * @param scrnMsg NFAL0210BMsg
     */
    public static void addCheckItem(NFAL0210BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.manJrnlNm);
        scrnMsg.addCheckItem(scrnMsg.glPerNm);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt);
        scrnMsg.addCheckItem(scrnMsg.xxToDt);
        scrnMsg.addCheckItem(scrnMsg.jrnlCatgCd);
        scrnMsg.addCheckItem(scrnMsg.glSendCpltFlg_SV);
        scrnMsg.addCheckItem(scrnMsg.rvslCpltFlg_SV);
        scrnMsg.addCheckItem(scrnMsg.manJrnlCpltFlg_SV);
        scrnMsg.addCheckItem(scrnMsg.autoRvslFlg_SV);
    }

    /**
     * setupListTable
     * @param handler S21CommonHandler
     * @param scrnMsg NFAL0210BMsg
     */
    public static void setupListTable(S21CommonHandler handler, NFAL0210BMsg scrnMsg) {
        S21TableColorController lineTblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        lineTblColor.clearRowsBG("A", scrnMsg.A);
        lineTblColor.setAlternateRowsBG("A", scrnMsg.A);
        for (int num = 0; num < scrnMsg.A.getValidCount(); num++) {
            scrnMsg.A.no(num).manJrnlNm_A.setInputProtected(true);
            scrnMsg.A.no(num).jrnlCatgDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(num).glPerNm_A.setInputProtected(true);
            scrnMsg.A.no(num).glDt_A.setInputProtected(true);
            scrnMsg.A.no(num).glSendCpltFlg_A.setInputProtected(true);
            scrnMsg.A.no(num).rvslCpltFlg_A.setInputProtected(true);
            scrnMsg.A.no(num).actlRvslDt_A.setInputProtected(true);
            scrnMsg.A.no(num).manJrnlCpltFlg_A.setInputProtected(true);
            scrnMsg.A.no(num).autoRvslFlg_A.setInputProtected(true);
        }
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled(BTN_DETAIL, true);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_A, BigDecimal.ZERO);
        } else {
            handler.setButtonEnabled(BTN_DETAIL, false);
        }
    }
}
