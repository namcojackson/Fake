/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0300.common;

import static business.servlet.NSBL0300.constant.NSBL0300Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;

import business.servlet.NSBL0300.NSBL0300BMsg;
import business.servlet.NSBL0300.NSBL0300_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         K.Kasai         Create          N/A
 * 2017/01/25   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSBL0300CommonLogic {

    /**
     * Activate buttons by Function List
     * @param handler S21CommonHandler
     * @param scrnMsg NSBL0300BMsg
     * @param functionList List<String>
     */
    public static void activateButtonsByFuncList(S21CommonHandler handler, NSBL0300BMsg scrnMsg, List<String> functionList) {

        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        handler.setButtonEnabled(BTN_SEARCH, false);
        handler.setButtonEnabled(BTN_INSERT_ROW, false);
        handler.setButtonEnabled(BTN_DELETE_ROW, false);
        if (isInqFunc(functionList)) {
            //Inquiry
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            handler.setButtonEnabled(BTN_SEARCH, true);
        }
        if (isUpdFunc(functionList)) {
            // Update
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            handler.setButtonEnabled(BTN_SEARCH, true);
            handler.setButtonEnabled(BTN_INSERT_ROW, true);
            handler.setButtonEnabled(BTN_DELETE_ROW, true);
            activateButtons(handler, scrnMsg);
        }
    }

    /**
     * Activate buttons
     * @param handler S21CommonHandler
     * @param scrnMsg NSBL0300BMsg
     */
    public static void activateButtons(S21CommonHandler handler, NSBL0300BMsg scrnMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.procFlg.getValue())) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            // START 2017/01/25 K.Ochiai [QC#16331,ADD]
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            // END 2017/01/25 K.Ochiai [QC#16331,ADD]
            handler.setButtonEnabled(BTN_INSERT_ROW, true);
            handler.setButtonEnabled(BTN_SEARCH, false);
        } else {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            // START 2017/01/25 K.Ochiai [QC#16331,ADD]
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            // END 2017/01/25 K.Ochiai [QC#16331,ADD]
            handler.setButtonEnabled(BTN_INSERT_ROW, false);
            handler.setButtonEnabled(BTN_SEARCH, true);
        }
        if (scrnMsg.A.getValidCount() == scrnMsg.A.length()) {
            handler.setButtonEnabled(BTN_INSERT_ROW, false);
        }
        boolean existDeleteTarget = false;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!hasValue(scrnMsg.A.no(i).svcSkillLvlPk_A)) {
                existDeleteTarget = true;
                break;
            }
        }
        if (scrnMsg.A.getValidCount() == 0 || !existDeleteTarget) {
            handler.setButtonEnabled(BTN_DELETE_ROW, false);
        } else {
            handler.setButtonEnabled(BTN_DELETE_ROW, true);
        }
    }

    /**
     * Activate screen items
     * @param handler S21CommonHandler
     * @param functionList List<String>
     * @param scrnMsg NSBL0300BMsg
     */
    public static void activateScreenItemsByFuncList(S21CommonHandler handler, List<String> functionList, NSBL0300BMsg scrnMsg) {

        // Header
        scrnMsg.svcSkillLvlGrpCd_H3.setInputProtected(true);
        scrnMsg.svcSkillLvlGrpDescTxt.setInputProtected(true);
        scrnMsg.effFromDt.setInputProtected(true);
        scrnMsg.effThruDt.setInputProtected(true);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
            scrnMsg.A.no(i).svcSkillLvlSortNum_A.setInputProtected(true);
            scrnMsg.A.no(i).svcSkillLvlNm_A.setInputProtected(true);
            scrnMsg.A.no(i).svcSkillLvlDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
            scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
        }

        if (isInqFunc(functionList)) {
            scrnMsg.svcSkillLvlGrpCd_H3.setInputProtected(false);
        }
        if (isUpdFunc(functionList)) {
            scrnMsg.svcSkillLvlGrpCd_H3.setInputProtected(false);
            scrnMsg.svcSkillLvlGrpDescTxt.setInputProtected(false);
            scrnMsg.effFromDt.setInputProtected(false);
            scrnMsg.effThruDt.setInputProtected(false);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                scrnMsg.A.no(i).svcSkillLvlSortNum_A.setInputProtected(false);
                scrnMsg.A.no(i).svcSkillLvlNm_A.setInputProtected(false);
                scrnMsg.A.no(i).svcSkillLvlDescTxt_A.setInputProtected(false);
                scrnMsg.A.no(i).effFromDt_A.setInputProtected(false);
                scrnMsg.A.no(i).effThruDt_A.setInputProtected(false);
            }
            activateScreenItems(handler, scrnMsg);
        }
    }

    /**
     * Activate screen items
     * @param handler S21CommonHandler
     * @param scrnMsg NSBL0300BMsg
     */
    public static void activateScreenItems(S21CommonHandler handler, NSBL0300BMsg scrnMsg) {

        // Header
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.procFlg.getValue())) {
            scrnMsg.svcSkillLvlGrpCd_H3.setInputProtected(true);
            scrnMsg.svcSkillLvlGrpDescTxt.setInputProtected(false);
            scrnMsg.effFromDt.setInputProtected(false);
            scrnMsg.effThruDt.setInputProtected(false);
        } else {
            scrnMsg.svcSkillLvlGrpCd_H3.setInputProtected(false);
            scrnMsg.svcSkillLvlGrpDescTxt.setInputProtected(true);
            scrnMsg.effFromDt.setInputProtected(true);
            scrnMsg.effThruDt.setInputProtected(true);
        }
        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (hasValue(scrnMsg.A.no(i).svcSkillLvlPk_A)) {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
            }
        }
    }

    /**
     * Alternate table row color
     * @param scrnMsg NSBL0300BMsg
     */
    public static void alternateTableRowColor(NSBL0300BMsg scrnMsg) {
        S21TableColorController control = new S21TableColorController(SCR_ID_00, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            control.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Focus item
     * @param scrnMsg NSBL0300BMsg
     */
    public static void focusItem(NSBL0300BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.setFocusItem(scrnMsg.svcSkillLvlGrpCd_H3);
        } else {
            scrnMsg.setFocusItem(scrnMsg.svcSkillLvlGrpDescTxt);
        }
    }

    /**
     * guiAttributeClear
     * @param scrnMsg NSBL0300BMsg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NSBL0300BMsg scrnMsg, String[][] baseContents) {
        S21SortColumnIMGController.clearIMG(SCR_ID_00, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(SCR_ID_00);
    }

    /**
     * Check input
     * @param scrnMsg NSBL0300BMsg
     */
    public static void addCheckItem(NSBL0300BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.svcSkillLvlGrpCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcSkillLvlGrpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillLvlSortNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillLvlNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSkillLvlDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);
        }
    }

    /**
     * format Flag
     * @param abMsg NSBL0300_ABMsg
     */
    public static void formatFlg(NSBL0300_ABMsg abMsg) {
        if (!hasValue(abMsg.xxChkBox_A)) {
            setValue(abMsg.xxChkBox_A, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * reflect to line
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0300BMsg
     */
    public static final void reflectToLine(EZDCommonHandler handler, NSBL0300BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!hasValue(scrnMsg.A.no(i).svcSkillLvlPk_A)) {
                setValue(scrnMsg.A.no(i).submtFlg_A, ZYPConstant.FLG_ON_Y);
            } else if (hasValue(scrnMsg.A.no(i).svcSkillLvlPk_A) && isChange(scrnMsg, i)) {
                setValue(scrnMsg.A.no(i).submtFlg_A, ZYPConstant.FLG_ON_Y);
            } else {
                setValue(scrnMsg.A.no(i).submtFlg_A, ZYPConstant.FLG_OFF_N);
            }
        }
    }

    private static boolean isInqFunc(List<String> functionList) {
        return functionList != null && !functionList.isEmpty() && functionList.contains(FUNC_ID_INQ);
    }

    private static boolean isUpdFunc(List<String> functionList) {
        if (functionList != null && !functionList.isEmpty()) {
            if (functionList.contains(FUNC_ID_UPD_1) || functionList.contains(FUNC_ID_UPD_2) || functionList.contains(FUNC_ID_UPD_3)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isChange(NSBL0300BMsg scrnMsg, int tragetNo) {

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            if (hasValue(scrnMsg.D.no(i).svcSkillLvlPk_D) && scrnMsg.A.no(tragetNo).svcSkillLvlPk_A.getValue().compareTo(scrnMsg.D.no(i).svcSkillLvlPk_D.getValue()) == 0) {
                if (isChangeBigDecimal(scrnMsg.A.no(tragetNo).svcSkillLvlSortNum_A, scrnMsg.D.no(i).svcSkillLvlSortNum_D)) {
                    return true;
                }
                if (isChangeString(scrnMsg.A.no(tragetNo).svcSkillLvlDescTxt_A, scrnMsg.D.no(i).svcSkillLvlDescTxt_D)) {
                    return true;
                }
                if (isChangeDate(scrnMsg.A.no(tragetNo).effFromDt_A, scrnMsg.D.no(i).effFromDt_D)) {
                    return true;
                }
                if (isChangeDate(scrnMsg.A.no(tragetNo).effThruDt_A, scrnMsg.D.no(i).effThruDt_D)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private static boolean isChangeString(EZDBStringItem befObj, EZDBStringItem changeObj) {
        if (!hasValue(befObj) && !hasValue(changeObj)) {
            return false;
        }
        if (!hasValue(befObj) && hasValue(changeObj)) {
            return true;
        }

        if (hasValue(befObj) && !hasValue(changeObj)) {
            return true;
        }

        if (!befObj.getValue().equals(changeObj.getValue())) {
            return true;
        }
        return false;
    }

    private static boolean isChangeBigDecimal(EZDBBigDecimalItem befObj, EZDBBigDecimalItem changeObj) {
        if (!hasValue(befObj) && !hasValue(changeObj)) {
            return false;
        }
        if (!hasValue(befObj) && hasValue(changeObj)) {
            return true;
        }

        if (hasValue(befObj) && !hasValue(changeObj)) {
            return true;
        }

        if (befObj.getValue().compareTo(changeObj.getValue()) != 0) {
            return true;
        }
        return false;
    }

    private static boolean isChangeDate(EZDBDateItem befObj, EZDBDateItem changeObj) {
        if (!hasValue(befObj) && !hasValue(changeObj)) {
            return false;
        }
        if (!hasValue(befObj) && hasValue(changeObj)) {
            return true;
        }

        if (hasValue(befObj) && !hasValue(changeObj)) {
            return true;
        }

        if (!befObj.getValue().equals(changeObj.getValue())) {
            return true;
        }
        return false;
    }
}
