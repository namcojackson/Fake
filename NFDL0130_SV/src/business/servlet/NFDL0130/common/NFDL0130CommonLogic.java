/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFDL0130.common;

import static business.servlet.NFDL0130.constant.NFDL0130Constant.*;

import java.util.List;

import business.servlet.NFDL0130.NFDL0130BMsg;
import business.servlet.NFDL0130.NFDL0130Bean;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         K.Kojima        Create          N/A
 * 2016/03/11   Hitachi         K.Kojima        Update          CSA QC#5358
 * 2016/09/12   Hitachi         K.Kojima        Update          QC#12997
 *</pre>
 */
public class NFDL0130CommonLogic {

    /**
     * setupScreenItems
     * @param handler S21CommonHandler
     * @param scrnMsg NFDL0130BMsg
     */
    public static void setupScreenItems(S21CommonHandler handler, NFDL0130BMsg scrnMsg) {
        S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcIdList = profile.getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Complete Contract. UserID is -> " + profile.getContextUserInfo().getUserId());
        }

        scrnMsg.setInputProtected(false);
        handler.setButtonEnabledAll();
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        if (!funcIdList.contains(UPD_FUNC)) {
            handler.setButtonEnabled(BTN_CMN_BTN_2[0], false);
            handler.setButtonEnabled(BTN_INSERT_ROW, false);
            handler.setButtonEnabled(BTN_DELETE_ROW, false);
            for (int num = 0; num < scrnMsg.A.getValidCount(); num++) {
                scrnMsg.A.no(num).cltWrkItemCd_A.setInputProtected(true);
                scrnMsg.A.no(num).cltWrkItemNm_A.setInputProtected(true);
                scrnMsg.A.no(num).cltWrkTpCd_SV.setInputProtected(true);
                scrnMsg.A.no(num).cltWrkCatgCd_A.setInputProtected(true);
                scrnMsg.A.no(num).cltWrkCatgNm_A.setInputProtected(true);
                scrnMsg.A.no(num).cltWrkWaitDaysAot_A.setInputProtected(true);
                // START 2016/09/12 K.Kojima [QC#12997,DEL]
                // scrnMsg.A.no(num).cltWrkPrtyCd_A.setInputProtected(true);
                // scrnMsg.A.no(num).cltWrkPrtyNm_A.setInputProtected(true);
                // END 2016/09/12 K.Kojima [QC#12997,DEL]
                scrnMsg.A.no(num).cltWrkDescTxt_A.setInputProtected(true);
                scrnMsg.A.no(num).cltWrkOptItemFlg_A.setInputProtected(true);
                scrnMsg.A.no(num).cltWrkEsclFlg_A.setInputProtected(true);
                scrnMsg.A.no(num).cltWrkNtfyFlg_A.setInputProtected(true);
                scrnMsg.A.no(num).cltWrkEsclWaitDaysAot_A.setInputProtected(true);
            }
        } else {
            for (int num = 0; num < scrnMsg.A.getValidCount(); num++) {
                if (XX_TP_CD_UPD.equals(scrnMsg.A.no(num).xxTpCd_A.getValue())) {
                    scrnMsg.A.no(num).cltWrkItemCd_A.setInputProtected(true);
                } else {
                    scrnMsg.A.no(num).cltWrkItemCd_A.setInputProtected(false);
                }
            }
        }

        // START 2016/03/11 K.Kojima [QC#5358,ADD]
        if (scrnMsg.A.getValidCount() == 0 && scrnMsg.D.getValidCount() == 0) {
            handler.setButtonEnabled(BTN_CMN_BTN_2[0], false);
        }
        // END 2016/03/11 K.Kojima [QC#5358,ADD]

        if (scrnMsg.A.getValidCount() == 0) {
            // START 2016/03/11 K.Kojima [QC#5358,DEL]
            // handler.setButtonEnabled(BTN_CMN_BTN_2[0], false);
            // END 2016/03/11 K.Kojima [QC#5358,DEL]
            handler.setButtonEnabled(BTN_DELETE_ROW, false);
        }

        if (scrnMsg.A.getValidCount() == scrnMsg.A.length()) {
            handler.setButtonEnabled(BTN_INSERT_ROW, false);
        }

        S21TableColorController lineTblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        lineTblColor.clearRowsBG("A1", scrnMsg.A);
        lineTblColor.clearRowsBG("A2", scrnMsg.A);
        lineTblColor.setAlternateRowsBG("A1", scrnMsg.A);
        lineTblColor.setAlternateRowsBG("A2", scrnMsg.A);
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSAL1170BMsg
     */
    public static void commonAddCheckItem(NFDL0130BMsg scrnMsg) {
        // START 2016/09/12 K.Kojima [QC#12997,MOD]
        // scrnMsg.A.setCheckParam(new String[]
        // {NFDL0130Bean.cltWrkItemCd_A, NFDL0130Bean.cltWrkItemNm_A,
        // NFDL0130Bean.cltWrkTpCd_SV, NFDL0130Bean.cltWrkCatgCd_A,
        // NFDL0130Bean.cltWrkCatgNm_A,
        // NFDL0130Bean.cltWrkWaitDaysAot_A,
        // NFDL0130Bean.cltWrkPrtyCd_A, NFDL0130Bean.cltWrkPrtyNm_A,
        // NFDL0130Bean.cltWrkDescTxt_A,
        // NFDL0130Bean.cltWrkOptItemFlg_A,
        // NFDL0130Bean.cltWrkEsclFlg_A, NFDL0130Bean.cltWrkNtfyFlg_A,
        // NFDL0130Bean.cltWrkEsclWaitDaysAot_A }, 1);
        scrnMsg.A.setCheckParam(new String[] {NFDL0130Bean.cltWrkItemCd_A, NFDL0130Bean.cltWrkItemNm_A, NFDL0130Bean.cltWrkTpCd_SV, NFDL0130Bean.cltWrkCatgCd_A, NFDL0130Bean.cltWrkCatgNm_A, NFDL0130Bean.cltWrkWaitDaysAot_A,
                NFDL0130Bean.cltWrkDescTxt_A, NFDL0130Bean.cltWrkOptItemFlg_A, NFDL0130Bean.cltWrkEsclFlg_A, NFDL0130Bean.cltWrkNtfyFlg_A, NFDL0130Bean.cltWrkEsclWaitDaysAot_A }, 1);
        // END 2016/09/12 K.Kojima [QC#12997,MOD]

        scrnMsg.addCheckItem(scrnMsg.A);
    }

    /**
     * setFocusRule
     * @param scrnMsg NFDL0130BMsg
     */
    public static void setFocusRule(NFDL0130BMsg scrnMsg) {

        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(SCREEN_ID, "");
        ZYPGUIFocusRule focusRule = null;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            focusRule = new ZYPGUIFocusRule(NFDL0130Bean.cltWrkItemNm_A + "#" + i);
            focusRule.setNextId(NFDL0130Bean.cltWrkTpCd_SV + "#" + i);
            tblFocusRule.addRule(focusRule);

            focusRule = new ZYPGUIFocusRule(NFDL0130Bean.cltWrkTpCd_SV + "#" + i);
            focusRule.setPrevId(NFDL0130Bean.cltWrkItemNm_A + "#" + i);
            tblFocusRule.addRule(focusRule);

            if ((i + 1) != scrnMsg.A.getValidCount()) {
                focusRule = new ZYPGUIFocusRule(NFDL0130Bean.cltWrkDescTxt_A + "#" + i);
                focusRule.setNextId(NFDL0130Bean.cltWrkItemCd_A + "#" + (i + 1));
                tblFocusRule.addRule(focusRule);
            }

            if (i != 0) {
                focusRule = new ZYPGUIFocusRule(NFDL0130Bean.cltWrkItemCd_A + "#" + i);
                focusRule.setPrevId(NFDL0130Bean.cltWrkDescTxt_A + "#" + (i - 1));
                tblFocusRule.addRule(focusRule);
            }

        }

        scrnMsg.addGUIAttribute(tblFocusRule);
    }

}
