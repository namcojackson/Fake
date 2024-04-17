/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0270.common;

import static business.servlet.NSBL0270.constant.NSBL0270Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;

import business.servlet.NSBL0270.NSBL0270BMsg;
import business.servlet.NSBL0270.NSBL0270Bean;
import business.servlet.NSBL0270.NSBL0270_ABMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/18   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBL0270CommonLogic {

    /**
     * Activate buttons
     * @param handler S21CommonHandler
     * @param scrnMsg NSBL0270BMsg
     * @param functionList List<String>
     */
    public static void activateButtons(S21CommonHandler handler, NSBL0270BMsg scrnMsg, List<String> functionList) {
        if (functionList == null || functionList.isEmpty() || functionList.contains(FUNC_ID_INQ)) {
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
            handler.setButtonEnabled(BTN_INSERT_ROW, false);
            handler.setButtonEnabled(BTN_DELETE_ROW, false);
            handler.setButtonEnabled(BTN_UPLOAD, false);
        }
        if (functionList != null && !functionList.isEmpty() && functionList.contains(FUNC_ID_UPD)) {
            // Update
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            handler.setButtonEnabled(BTN_INSERT_ROW, true);
            handler.setButtonEnabled(BTN_DELETE_ROW, true);
            handler.setButtonEnabled(BTN_UPLOAD, true);
            boolean existDeleteTarget = false;
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (!hasValue(scrnMsg.A.no(i).svcPrcShiftPk_A)) {
                    existDeleteTarget = true;
                    break;
                }
            }
            if (scrnMsg.A.getValidCount() == 0 || !existDeleteTarget) {
                handler.setButtonEnabled(BTN_DELETE_ROW, false);
            }
            if (scrnMsg.A.getValidCount() == scrnMsg.A.length()) {
                handler.setButtonEnabled(BTN_INSERT_ROW, false);
            }
        } else if (functionList != null && !functionList.isEmpty() && functionList.contains(FUNC_ID_INQ)) {
            // reference
            handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
        }
    }

    /**
     * Activate screen items
     * @param handler S21CommonHandler
     * @param functionList List<String>
     * @param scrnMsg NSBL0270BMsg
     */
    public static void activateScreenItems(S21CommonHandler handler, List<String> functionList, NSBL0270BMsg scrnMsg) {

        // Header
        if (!functionList.contains(FUNC_ID_UPD)) {
            scrnMsg.xxFileData.setInputProtected(true);
            scrnMsg.xxLinkAncr.setInputProtected(true);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_AD.setInputProtected(true);
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxChkBox_A2.setInputProtected(true);
                scrnMsg.A.no(i).svcLineBizCd_A.setInputProtected(true);
                scrnMsg.A.no(i).svcPrcShiftDescTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).xxStartDplyTmTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxEndDplyTmTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxStartDplyTmTxt_A2.setInputProtected(true);
                scrnMsg.A.no(i).xxEndDplyTmTxt_A2.setInputProtected(true);
                scrnMsg.A.no(i).xxStartDplyTmTxt_A3.setInputProtected(true);
                scrnMsg.A.no(i).xxEndDplyTmTxt_A3.setInputProtected(true);
                scrnMsg.A.no(i).xxStartDplyTmTxt_A4.setInputProtected(true);
                scrnMsg.A.no(i).xxEndDplyTmTxt_A4.setInputProtected(true);
                scrnMsg.A.no(i).xxStartDplyTmTxt_A5.setInputProtected(true);
                scrnMsg.A.no(i).xxEndDplyTmTxt_A5.setInputProtected(true);
                scrnMsg.A.no(i).xxStartDplyTmTxt_A6.setInputProtected(true);
                scrnMsg.A.no(i).xxEndDplyTmTxt_A6.setInputProtected(true);
                scrnMsg.A.no(i).xxStartDplyTmTxt_A7.setInputProtected(true);
                scrnMsg.A.no(i).xxEndDplyTmTxt_A7.setInputProtected(true);
                scrnMsg.A.no(i).xxStartDplyTmTxt_A8.setInputProtected(true);
                scrnMsg.A.no(i).xxEndDplyTmTxt_A8.setInputProtected(true);
            }
        } else {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (hasValue(scrnMsg.A.no(i).svcPrcShiftPk_A)) {
                    scrnMsg.A.no(i).xxChkBox_AD.setInputProtected(true);
                } else {
                    scrnMsg.A.no(i).xxChkBox_AD.setInputProtected(false);
                }
            }
        }
    }

    /**
     * Alternate table row color
     * @param scrnMsg NSBL0270BMsg
     */
    public static void alternateTableRowColor(NSBL0270BMsg scrnMsg) {
        S21TableColorController control = new S21TableColorController(SCR_ID_00, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            control.setAlternateRowsBG("A_Left", table);
            control.setAlternateRowsBG("A_Right", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Focus item
     * @param scrnMsg NSBL0270BMsg
     */
    public static void focusItem(NSBL0270BMsg scrnMsg) {
        scrnMsg.setFocusItem(scrnMsg.xxFileData);
    }

    /**
     * guiAttributeClear
     * @param scrnMsg NSBL0270BMsg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NSBL0270BMsg scrnMsg, String[][] baseContents) {
        S21SortColumnIMGController.clearIMG(SCR_ID_00, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(SCR_ID_00);
    }

    /**
     * Check input
     * @param scrnMsg NSBL0270BMsg
     */
    public static void addCheckItem(NSBL0270BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.xxFileData);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_AD);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcLineBizCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcPrcShiftDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A3);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A3);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A4);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A4);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A5);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A5);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A6);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A6);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A7);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A7);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxStartDplyTmTxt_A8);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxEndDplyTmTxt_A8);
        }
    }

    /**
     * @param a BigDecimal
     * @param b BigDecimal
     * @return result boolean
     */
    public static boolean isEqualNum(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null && a.compareTo(b) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Format all item
     * @param scrnMsg NSBL0270BMsg
     */
    public static void formatItem(NSBL0270BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            setValue(scrnMsg.A.no(i).xxStartDplyTmTxt_A1, formatHHmmss(scrnMsg.A.no(i).xxStartDplyTmTxt_A1));
            setValue(scrnMsg.A.no(i).xxEndDplyTmTxt_A1, formatHHmmss(scrnMsg.A.no(i).xxEndDplyTmTxt_A1));
            setValue(scrnMsg.A.no(i).xxStartDplyTmTxt_A2, formatHHmmss(scrnMsg.A.no(i).xxStartDplyTmTxt_A2));
            setValue(scrnMsg.A.no(i).xxEndDplyTmTxt_A2, formatHHmmss(scrnMsg.A.no(i).xxEndDplyTmTxt_A2));
            setValue(scrnMsg.A.no(i).xxStartDplyTmTxt_A3, formatHHmmss(scrnMsg.A.no(i).xxStartDplyTmTxt_A3));
            setValue(scrnMsg.A.no(i).xxEndDplyTmTxt_A3, formatHHmmss(scrnMsg.A.no(i).xxEndDplyTmTxt_A3));
            setValue(scrnMsg.A.no(i).xxStartDplyTmTxt_A4, formatHHmmss(scrnMsg.A.no(i).xxStartDplyTmTxt_A4));
            setValue(scrnMsg.A.no(i).xxEndDplyTmTxt_A4, formatHHmmss(scrnMsg.A.no(i).xxEndDplyTmTxt_A4));
            setValue(scrnMsg.A.no(i).xxStartDplyTmTxt_A5, formatHHmmss(scrnMsg.A.no(i).xxStartDplyTmTxt_A5));
            setValue(scrnMsg.A.no(i).xxEndDplyTmTxt_A5, formatHHmmss(scrnMsg.A.no(i).xxEndDplyTmTxt_A5));
            setValue(scrnMsg.A.no(i).xxStartDplyTmTxt_A6, formatHHmmss(scrnMsg.A.no(i).xxStartDplyTmTxt_A6));
            setValue(scrnMsg.A.no(i).xxEndDplyTmTxt_A6, formatHHmmss(scrnMsg.A.no(i).xxEndDplyTmTxt_A6));
            setValue(scrnMsg.A.no(i).xxStartDplyTmTxt_A7, formatHHmmss(scrnMsg.A.no(i).xxStartDplyTmTxt_A7));
            setValue(scrnMsg.A.no(i).xxEndDplyTmTxt_A7, formatHHmmss(scrnMsg.A.no(i).xxEndDplyTmTxt_A7));
            setValue(scrnMsg.A.no(i).xxStartDplyTmTxt_A8, formatHHmmss(scrnMsg.A.no(i).xxStartDplyTmTxt_A8));
            setValue(scrnMsg.A.no(i).xxEndDplyTmTxt_A8, formatHHmmss(scrnMsg.A.no(i).xxEndDplyTmTxt_A8));
        }
    }

    /**
     * Format all item
     * @param scrnMsg NSBL0270BMsg
     */
    public static void setSplitTblFocus(NSBL0270BMsg scrnMsg) {
     // focus control for Split table
        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(SCR_ID_00, NSBL0270Bean.A);
        scrnMsg.addGUIAttribute(tblFocusRule);

        // focus rule
        ZYPGUIFocusRule fRule = new ZYPGUIFocusRule("focus rule");
        fRule.setNextId(NSBL0270Bean.xxChkBox_AD + "#0");
        tblFocusRule.addRule(fRule);

        scrnMsg.clearGUIAttribute(SCR_ID_00, NSBL0270Bean.A);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // xxChkBox_AD
            fRule = new ZYPGUIFocusRule(NSBL0270Bean.xxChkBox_AD + "#" + i);
            if (i > 0) {
                fRule.setPrevId(NSBL0270Bean.xxEndDplyTmTxt_A8 + "#" + (i - 1));
            }
            tblFocusRule.addRule(fRule);

            // svcPrcShiftDescTxt_A
            fRule = new ZYPGUIFocusRule(NSBL0270Bean.svcPrcShiftDescTxt_A + "#" + i);
            fRule.setNextId(NSBL0270Bean.xxStartDplyTmTxt_A1 + "#" + i);
            tblFocusRule.addRule(fRule);

            // xxStartDplyTmTxt_A1
            fRule = new ZYPGUIFocusRule(NSBL0270Bean.xxStartDplyTmTxt_A1 + "#" + i);
            fRule.setPrevId(NSBL0270Bean.svcPrcShiftDescTxt_A + "#" + i);
            tblFocusRule.addRule(fRule);

            // xxEndDplyTmTxt_A8
            fRule = new ZYPGUIFocusRule(NSBL0270Bean.xxEndDplyTmTxt_A8 + "#" + i);
            if ((i + 1) != scrnMsg.A.length()) {
                fRule.setNextId(NSBL0270Bean.xxChkBox_AD + "#" + (i + 1));
            }
            tblFocusRule.addRule(fRule);
        }
    }

    /**
     * Format HHmmss
     * @param item EZDBStringItem
     * @return String
     */
    public static String formatHHmmss(EZDBStringItem item) {
        if (!hasValue(item)) {
            return null;
        }
        String itemStr = item.getValue();
        if (itemStr.indexOf(":") != -1 || itemStr.length() < LENGTH_HHMM) {
            return itemStr;
        }
        if (itemStr.length() == LENGTH_HHMMSS) {
            itemStr = ZYPDateUtil.formatDispHHmmss(itemStr.substring(0, 2), itemStr.substring(2, POINT_HHMM_S), itemStr.substring(POINT_HHMM_S, POINT_HHMMSS_S), true);
            if (itemStr.substring(POINT_HHMMSS_S, POINT_HHMMSS_E).equals(VALUE_SS)) {
                return itemStr.substring(0, POINT_HH_MM_E);
            }
            return itemStr;
        }
        itemStr = ZYPDateUtil.formatDispHHmmss(itemStr.substring(0, 2), itemStr.substring(2, POINT_HHMM_S), null, true);
        return itemStr.substring(0, POINT_HH_MM_E);
    }

    /**
     * format Flag
     * @param abMsg NSBL0270_ABMsg
     */
    public static void formatFlg(NSBL0270_ABMsg abMsg) {
        if (!hasValue(abMsg.xxChkBox_A1)) {
            setValue(abMsg.xxChkBox_A1, ZYPConstant.FLG_OFF_N);
        }
        if (!hasValue(abMsg.xxChkBox_A2)) {
            setValue(abMsg.xxChkBox_A2, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * checkDate
     * @param abMsg NSBL0270_ABMsg
     */
    public static void checkFormatTm(NSBL0270_ABMsg abMsg) {
        checkAndFormatTm(abMsg.xxStartDplyTmTxt_A1);
        checkAndFormatTm(abMsg.xxEndDplyTmTxt_A1);
        checkAndFormatTm(abMsg.xxStartDplyTmTxt_A2);
        checkAndFormatTm(abMsg.xxEndDplyTmTxt_A2);
        checkAndFormatTm(abMsg.xxStartDplyTmTxt_A3);
        checkAndFormatTm(abMsg.xxEndDplyTmTxt_A3);
        checkAndFormatTm(abMsg.xxStartDplyTmTxt_A4);
        checkAndFormatTm(abMsg.xxEndDplyTmTxt_A4);
        checkAndFormatTm(abMsg.xxStartDplyTmTxt_A5);
        checkAndFormatTm(abMsg.xxEndDplyTmTxt_A5);
        checkAndFormatTm(abMsg.xxStartDplyTmTxt_A6);
        checkAndFormatTm(abMsg.xxEndDplyTmTxt_A6);
        checkAndFormatTm(abMsg.xxStartDplyTmTxt_A7);
        checkAndFormatTm(abMsg.xxEndDplyTmTxt_A7);
        checkAndFormatTm(abMsg.xxStartDplyTmTxt_A8);
        checkAndFormatTm(abMsg.xxEndDplyTmTxt_A8);
    }

    /**
     * @param scrnMsg
     * @param isError
     * @param p
     * @return
     */
    private static boolean checkAndFormatTm(EZDBStringItem item) {
        String regex = "([0-1][0-9]|[2][0-3])([0-5][0-9])([0-5][0-9])";
        Pattern p = Pattern.compile(regex);
        if (hasValue(item)) {
            String formatTm = getTm(item.getValue());
            Matcher m = p.matcher(formatTm);
            if (m.find()) {
                item.setValue(formatTm);
            } else {
                item.setErrorInfo(1, NSAM0466E, new String[] {});
                return true;
            }
        }
        return false;
    }

    /**
     * get Time
     * @param tm time
     * @return time
     */
    public static String getTm(String tm) {
        if (hasValue(tm)) {
            tm = tm.replace(COLON, "");
            if (tm.length() == LENGTH_HHMM) {
                tm = tm.concat(VALUE_SS);
            }
        }
        return tm;
    }

    /**
     * set Time
     * @param tm time
     * @return time
     */
    public static String setTm(String tm) {
        if (hasValue(tm)) {
            StringBuffer strBuf = new StringBuffer();
            strBuf.append(tm);
            if (strBuf.length() > 2) {
                strBuf.insert(2, COLON);
            }
            return strBuf.toString();
        }
        return tm;
    }
}
