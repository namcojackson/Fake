/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NMAL6870.common;

import static business.servlet.NMAL6870.constant.NMAL6870Constant.BIZ_ID;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.BTN_CMN_APPLY;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.BTN_CMN_APPROVE;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.BTN_CMN_CLEAR;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.BTN_CMN_DELETE;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.BTN_CMN_DOWNLOAD;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.BTN_CMN_REJECT;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.BTN_CMN_RESET;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.BTN_CMN_RETURN;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.BTN_CMN_SAVE;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.BTN_CMN_SUBMIT;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.INPUT_PARAM_COLUMN;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.INPUT_PARAM_CRITERIA;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.INPUT_PARAM_RESULT;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.INPUT_PARAM_SCR_NM;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.INPUT_PARAM_SORT;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.INPUT_PARAM_SUFFIX;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.INPUT_PARAM_TBL_NM;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.MAX_INPUT_PARAM_NUM;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.PX_VALUE_ZERO;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.SCRN_ID;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.SEARCH_CRITERIA_ROW_HEIGHT;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.STYLE_ATTR_HEIGHT;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.STYLE_ATTR_WIDTH;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.TABLE_BODY_HEIGHT;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.TABLE_ID;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.TABLE_ID_BODY;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.UNIT_NAME_EM;
import static business.servlet.NMAL6870.constant.NMAL6870Constant.UNIT_NAME_PX;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDDebugOutput;
import parts.common.EZDGUIAttribute;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDValidatorException;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NMAL6870.NMAL6870CMsg;
import business.servlet.NMAL6870.NMAL6870BMsg;
import business.servlet.NMAL6870.NMAL6870Bean;
import business.servlet.NMAL6870.NMAL6870_ABMsg;
import business.servlet.NMAL6870.NMAL6870_ABMsgArray;
import business.servlet.NMAL6870.NMAL6870_CBMsg;
import business.servlet.NMAL6870.NMAL6870_CBMsgArray;
import business.servlet.NMAL6870.NMAL6870_HBMsg;
import business.servlet.NMAL6870.NMAL6870_HBMsgArray;
import business.servlet.NMAL6870.NMAL6870_RBMsg;
import business.servlet.NMAL6870.NMAL6870_RBMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Multi Selection Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/27   Fujitsu         S.Yoshida         Create          N/A
 * 2016/05/17   CITS            S.Tanikawa        Update          QC#8182
 *</pre>
 */
public class NMAL6870CommonLogic {

    /**
     * Initialize Display Information
     * <p>
     * init display information
     * </p>
     * @param handler EZDCommonHandler
     * @param bMsg EZDBMsg
     */
    public static void initDisplayInfo(EZDCommonHandler handler, EZDBMsg bMsg) {

        /*
         * Initial Common Button
         */
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);

        NMAL6870BMsg scrnMsg = (NMAL6870BMsg) bMsg;

        // set screen item condition
        setScreenItemCondition(scrnMsg);
    }

    /**
     * @return bizMsg NMAL6870CMsg
     */
    public static NMAL6870CMsg setRequestData_NMAL6870Scrn00_Search() {

        NMAL6870CMsg bizMsg = new NMAL6870CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);

        return bizMsg;
    }

    /**
     * Execute EZ Default Input Check
     * @param bMsg EZDBMsg
     */
    public static void ezCheck(EZDBMsg bMsg) {

        NMAL6870BMsg scrnMsg = (NMAL6870BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H0);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H2);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H3);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H4);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H5);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H6);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H7);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H8);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H9);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_HA);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_HB);
        scrnMsg.putErrorScreen();
    }

    /**
     * Set table BackGround Color(White/Gray)
     * @param bMsg EZDBMsg
     */
    public static void setTableBGColor(EZDBMsg bMsg) {

        NMAL6870BMsg scrnMsg = (NMAL6870BMsg) bMsg;

        S21TableColorController lineTblColor = new S21TableColorController(SCRN_ID, scrnMsg);

        lineTblColor.setAlternateRowsBG(TABLE_ID, scrnMsg.A);
    }

    /**
     * Set result
     * @param scrnMsg NMAL6870BMsg
     * @param resMsgArray NMAL6870_RBMsgArray
     * @param viewMsgArray NMAL6870_ABMsg
     */
    public static void setResult(NMAL6870BMsg scrnMsg, NMAL6870_RBMsgArray resMsgArray, NMAL6870_ABMsgArray viewMsgArray) {

        List<Integer> sel = ZYPTableUtil.getSelectedRows(scrnMsg.A, NMAL6870Bean.xxChkBox_A0, ZYPConstant.FLG_ON_Y);

        if (sel.isEmpty()) {
            ZYPTableUtil.clear(resMsgArray);
        }

        int resIdx = 0;
        for (int viewIdx : sel) {
            NMAL6870_RBMsg resMsg = resMsgArray.no(resIdx++);
            NMAL6870_ABMsg viewMsg = viewMsgArray.no(viewIdx);
            for (int j = 0; j < scrnMsg.C.getValidCount(); j++) {
                setEZDStringItemValue(resMsg, "xxComnScrColValTxt_", j, getEZDStringItemValue(viewMsg, "xxComnScrColValTxt_A", j));
                setEZDStringItemValue(resMsg, "xxComnScrQueryColNm_", j, scrnMsg.C.no(j).xxComnScrQueryColNm_C.getValue());
            }
        }
        resMsgArray.setValidCount(sel.size());
    }

    /**
     * set output parameter
     * @param arg Object[]
     * @param resultArray NMAL6870_RBMsgArray
     */
    public static void setOutputParam(Object[] arg, NMAL6870_RBMsgArray resultArray) {

        if (!(arg instanceof Object[])) {
            return;
        }

        if (arg.length < MAX_INPUT_PARAM_NUM) {
            return;
        }

        if (arg[INPUT_PARAM_RESULT] instanceof EZDMsgArray) {
            EZDMsg.copy(resultArray, null, (EZDMsgArray) arg[INPUT_PARAM_RESULT], null);
            EZDDebugOutput.println(1, "NMAL6870 Output Parameters:" + ((EZDMsgArray) arg[INPUT_PARAM_RESULT]).toString(), ((EZDMsgArray) arg[INPUT_PARAM_RESULT]).toString());
        }
    }

    /**
     * Get EZDStringItem value
     * @param msg EZDMsg
     * @param itemName String
     * @param suffixNumber int
     * @return value
     */
    public static String getEZDStringItemValue(EZDMsg msg, String itemName, int suffixNumber) {
        return msg.getValueString(String.format(itemName + "%d", suffixNumber), -1);
    }

    /**
     * Set EZDStringItem value
     * @param msg EZDMsg
     * @param itemName String
     * @param suffixNumber int
     * @param value String
     */
    public static void setEZDStringItemValue(EZDMsg msg, String itemName, int suffixNumber, String value) {
        try {
            if (suffixNumber > 9) {
                msg.setValueString(String.format(itemName + getSuffixStr(suffixNumber)), -1, value);
            } else {
                msg.setValueString(String.format(itemName + "%d", suffixNumber), -1, value);
            }

        } catch (EZDValidatorException e) {
            // not set
            e.printStackTrace();
        }
    }

    /**
     * Set EZDBigDecimalItem value
     * @param msg EZDMsg
     * @param itemName String
     * @param suffixNumber int
     * @param value BigDecimal
     */
    public static void setEZDBigDecimalItemValue(EZDMsg msg, String itemName, int suffixNumber, BigDecimal value) {
        try {
            msg.setValueBigDecimal(String.format(itemName + "%d", suffixNumber), -1, value);
        } catch (EZDValidatorException e) {
            // not set
            e.printStackTrace();
        }
    }

    /**
     * set input parameter
     * @param scrnMsg NMAL6870BMsg
     * @param arg Object[]
     * @param glblCmpyCd Global Company Code
     */
    public static void setInputParam(NMAL6870BMsg scrnMsg, Object[] arg, String glblCmpyCd) {

        if (!(arg instanceof Object[])) {
            return;
        }

        if (arg.length < MAX_INPUT_PARAM_NUM) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, glblCmpyCd);

        // 1.Screen Title
        if (arg[INPUT_PARAM_SCR_NM] instanceof EZDBStringItem) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm, (EZDBStringItem) arg[INPUT_PARAM_SCR_NM]);

        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm, (String) arg[INPUT_PARAM_SCR_NM]);
        }

        // 2.Table Name
        if (arg[INPUT_PARAM_TBL_NM] instanceof EZDBStringItem) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrTblTxt, (EZDBStringItem) arg[INPUT_PARAM_TBL_NM]);

        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrTblTxt, (String) arg[INPUT_PARAM_TBL_NM]);
        }

        // 3.Search Criteria(H)
        if (arg[INPUT_PARAM_CRITERIA] instanceof EZDMsgArray) {
            EZDMsg.copy((EZDMsgArray) arg[INPUT_PARAM_CRITERIA], null, scrnMsg.H, null);

        } else {
            List< ? > paramList = (List< ? >) arg[INPUT_PARAM_CRITERIA];
            int index = 0;
            for (Object param : paramList) {
                if (param instanceof Object[]) {
                    int column = 0;
                    ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(index).xxComnScrCondLbTxt_H, (String) ((Object[]) param)[column++]);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(index).xxComnScrQueryFltrTxt_H, (String) ((Object[]) param)[column++]);
                    column++;  // xxComnScrCondValTxt_H is skip
                    ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(index).xxComnScrQueryLikeFlg_H, (String) ((Object[]) param)[column++]);
                    index++;
                }
            }
            scrnMsg.H.setValidCount(index);
        }

        // 4.Column (C)
        if (arg[INPUT_PARAM_COLUMN] instanceof EZDMsgArray) {
            EZDMsg.copy((EZDMsgArray) arg[INPUT_PARAM_COLUMN], null, scrnMsg.C, null);

        } else {
            List< ? > paramList = (List< ? >) arg[INPUT_PARAM_COLUMN];
            int index = 0;
            for (Object param : paramList) {
                if (param instanceof Object[]) {
                    int column = 0;
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index).xxComnScrColLbTxt_C, (String) ((Object[]) param)[column++]);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index).xxComnScrQueryColNm_C, (String) ((Object[]) param)[column++]);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index).xxComnScrColLg_C, (BigDecimal) ((Object[]) param)[column++]);
                    index++;
                }
            }
            scrnMsg.C.setValidCount(index);
        }

        // 5.Sort Order(S)
        if (arg[INPUT_PARAM_SORT] instanceof EZDMsgArray) {

            EZDMsg.copy((EZDMsgArray) arg[INPUT_PARAM_SORT], null, scrnMsg.S, null);
        } else {
            List< ? > paramList = (List< ? >) arg[INPUT_PARAM_SORT];
            int index = 0;
            for (Object param : paramList) {
                if (param instanceof Object[]) {
                    int column = 0;
                    ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(index).xxTblSortColNm_S, (String) ((Object[]) param)[column++]);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(index).xxSortOrdByTxt_S, (String) ((Object[]) param)[column++]);
                    index++;
                }
            }
            scrnMsg.S.setValidCount(index);
        }

        // 6.Output(R)
        if (arg[INPUT_PARAM_RESULT] instanceof EZDMsgArray) {
            if (arg[INPUT_PARAM_SUFFIX] == null) {
                arg[INPUT_PARAM_SUFFIX] = "";
            }
            EZDMsg.copy((EZDMsgArray) arg[INPUT_PARAM_RESULT], null, scrnMsg.R, null);
        }
    }

    /**
     * prepare screen item
     * @param scrnMsg NMAL6870BMsg
     */
    public static void prepareScreenItem(NMAL6870BMsg scrnMsg) {

        // search criteria
        prepareSearchCriteria(scrnMsg, scrnMsg.H);

        // column
        prepareColumn(scrnMsg, scrnMsg.C);
    }

    /**
     * needs initial search
     * @param scrnMsg NMAL6870BMsg
     * @return true : necessary , false : unnecessary
     */
    public static boolean needsInitialSearch(NMAL6870BMsg scrnMsg) {
        if (!existsSearchCriteria(scrnMsg) && existsSelectColumn(scrnMsg)) {
            return true;
        }
        return true;
    }

    /**
     * exists select column
     * @param scrnMsg NMAL6870BMsg
     * @return true : exist, false : not exist
     */
    private static boolean existsSelectColumn(NMAL6870BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryColNm_C0)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryColNm_C1)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryColNm_C2)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryColNm_C3)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryColNm_C4)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryColNm_C5)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryColNm_C6)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryColNm_C7)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryColNm_C8)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryColNm_C9)) {
            return true;
        }
        return false;
    }

    /**
     * exists search criteria
     * @param scrnMsg NMAL6870BMsg
     * @return true : exist, false : not exist
     */
    private static boolean existsSearchCriteria(NMAL6870BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H0)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H1)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H2)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H3)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H4)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H5)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H6)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H7)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H8)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H9)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_HA)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_HB)) {
            return true;
        }

        return false;
    }

    /**
     * prepare search criteria
     * @param scrnMsg NMAL6870BMsg
     * @param searchCriteriaArray NMAL6870_HBMsgArray
     */
    private static void prepareSearchCriteria(NMAL6870BMsg scrnMsg, NMAL6870_HBMsgArray searchCriteriaArray) {
        for (int i = 0; i < searchCriteriaArray.length(); i++) {
            setSearchCriteria(scrnMsg, searchCriteriaArray.no(i), i);
        }
    }

    /**
     * prepare column
     * @param scrnMsg NMAL6870BMsg
     * @param columnArray NMAL6870_CBMsgArray
     */
    private static void prepareColumn(NMAL6870BMsg scrnMsg, NMAL6870_CBMsgArray columnArray) {
        for (int i = 0; i < columnArray.length(); i++) {
            setColumn(scrnMsg, columnArray.no(i), i);
        }
    }

    /**
     * set search criteria
     * @param scrnMsg NMAL6870BMsg
     * @param searchCriteria NMAL6870_HBMsg
     * @param itemSuffixNumber int
     */
    private static void setSearchCriteria(NMAL6870BMsg scrnMsg, NMAL6870_HBMsg searchCriteria, int itemSuffixNumber) {

        if (ZYPCommonFunc.hasValue(searchCriteria.xxComnScrCondLbTxt_H)) {
            setEZDStringItemValue(scrnMsg, NMAL6870Bean.xxComnScrCondLbTxt_H, itemSuffixNumber, searchCriteria.xxComnScrCondLbTxt_H.getValue());
        }
        if (ZYPCommonFunc.hasValue(searchCriteria.xxComnScrCondValTxt_H)) {
            setEZDStringItemValue(scrnMsg, NMAL6870Bean.xxComnScrCondValTxt_H, itemSuffixNumber, searchCriteria.xxComnScrCondValTxt_H.getValue());
        }
        if (ZYPCommonFunc.hasValue(searchCriteria.xxComnScrQueryFltrTxt_H)) {
            setEZDStringItemValue(scrnMsg, NMAL6870Bean.xxComnScrQueryFltrTxt_H, itemSuffixNumber, searchCriteria.xxComnScrQueryFltrTxt_H.getValue());
        }
        if (ZYPConstant.FLG_ON_Y.equals(searchCriteria.xxComnScrQueryLikeFlg_H.getValue())) {
            setEZDStringItemValue(scrnMsg, NMAL6870Bean.xxComnScrQueryLikeFlg_H, itemSuffixNumber, ZYPConstant.FLG_ON_Y);
            setEZDStringItemValue(scrnMsg, "xxScrItem7Txt_H", itemSuffixNumber, "(*)");
        }
    }

    /**
     * set column
     * @param scrnMsg NMAL6870BMsg
     * @param column NMAL6870_CBMsg
     * @param itemSuffixNumber int
     */
    private static void setColumn(NMAL6870BMsg scrnMsg, NMAL6870_CBMsg column, int itemSuffixNumber) {

        if (ZYPCommonFunc.hasValue(column.xxComnScrQueryColNm_C)) {
            setEZDStringItemValue(scrnMsg, NMAL6870Bean.xxComnScrQueryColNm_C, itemSuffixNumber, column.xxComnScrQueryColNm_C.getValue());
            if (ZYPCommonFunc.hasValue(column.xxComnScrColLbTxt_C)) {
                setEZDStringItemValue(scrnMsg, NMAL6870Bean.xxComnScrColLbTxt_C, itemSuffixNumber, column.xxComnScrColLbTxt_C.getValue());
            }

            if (ZYPCommonFunc.hasValue(column.xxComnScrColLg_C)) {
                setEZDBigDecimalItemValue(scrnMsg, NMAL6870Bean.xxComnScrColLg_C, itemSuffixNumber, column.xxComnScrColLg_C.getValue());
            } else {
                setEZDBigDecimalItemValue(scrnMsg, NMAL6870Bean.xxComnScrColLg_C, itemSuffixNumber, BigDecimal.ZERO);
            }
        } else {
            setEZDBigDecimalItemValue(scrnMsg, NMAL6870Bean.xxComnScrColLg_C, itemSuffixNumber, BigDecimal.ZERO);
        }
    }

    /**
     * set screen item condition
     * @param scrnMsg NMAL6870BMsg
     */
    private static void setScreenItemCondition(NMAL6870BMsg scrnMsg) {

        // search criteria table row height
        int hiddenSearchCriteriaTableRowNum = 0;
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H0)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H1)) {
            hiddenSearchCriteriaTableRowNum++;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H2)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H3)) {
            hiddenSearchCriteriaTableRowNum++;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H4)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H5)) {
            hiddenSearchCriteriaTableRowNum++;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H6)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H7)) {
            hiddenSearchCriteriaTableRowNum++;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H8)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H9)) {
            hiddenSearchCriteriaTableRowNum++;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_HA)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_HB)) {
            hiddenSearchCriteriaTableRowNum++;
        }

        // table body height
        int tableBodyHeight = TABLE_BODY_HEIGHT + SEARCH_CRITERIA_ROW_HEIGHT * hiddenSearchCriteriaTableRowNum;
        if (tableBodyHeight > 0) {
            EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCRN_ID, TABLE_ID_BODY);
            guiAttr.setStyleAttribute(STYLE_ATTR_HEIGHT, String.valueOf(tableBodyHeight) + UNIT_NAME_PX);
            scrnMsg.addGUIAttribute(guiAttr);
        }

        // header column width
        setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColLbTxt_C0, scrnMsg.xxComnScrColLg_C0);
        setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColLbTxt_C1, scrnMsg.xxComnScrColLg_C1);
        setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColLbTxt_C2, scrnMsg.xxComnScrColLg_C2);
        setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColLbTxt_C3, scrnMsg.xxComnScrColLg_C3);
        setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColLbTxt_C4, scrnMsg.xxComnScrColLg_C4);
        setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColLbTxt_C5, scrnMsg.xxComnScrColLg_C5);
        setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColLbTxt_C6, scrnMsg.xxComnScrColLg_C6);
        setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColLbTxt_C7, scrnMsg.xxComnScrColLg_C7);
        setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColLbTxt_C8, scrnMsg.xxComnScrColLg_C8);
        setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColLbTxt_C9, scrnMsg.xxComnScrColLg_C9);

        if (scrnMsg.A.getValidCount() > 0) {
            // list column width
            setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColValTxt_A0 + "#0", scrnMsg.xxComnScrColLg_C0);
            setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColValTxt_A1 + "#0", scrnMsg.xxComnScrColLg_C1);
            setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColValTxt_A2 + "#0", scrnMsg.xxComnScrColLg_C2);
            setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColValTxt_A3 + "#0", scrnMsg.xxComnScrColLg_C3);
            setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColValTxt_A4 + "#0", scrnMsg.xxComnScrColLg_C4);
            setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColValTxt_A5 + "#0", scrnMsg.xxComnScrColLg_C5);
            setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColValTxt_A6 + "#0", scrnMsg.xxComnScrColLg_C6);
            setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColValTxt_A7 + "#0", scrnMsg.xxComnScrColLg_C7);
            setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColValTxt_A8 + "#0", scrnMsg.xxComnScrColLg_C8);
            setColumnWidth(scrnMsg, NMAL6870Bean.xxComnScrColValTxt_A9 + "#0", scrnMsg.xxComnScrColLg_C9);
        }
    }

    /**
     * set column width
     * @param scrnMsg NMAL6870BMsg
     * @param id String
     * @param charLength EZDBBigDecimalItem
     */
    private static void setColumnWidth(NMAL6870BMsg scrnMsg, String id, EZDBBigDecimalItem charLength) {

        if (ZYPCommonFunc.hasValue(charLength)) {

            int clumnWidth = calculateColumnWidth(charLength.getValueInt());

            EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCRN_ID, id);
            guiAttr.setStyleAttribute(STYLE_ATTR_WIDTH, String.valueOf(clumnWidth) + UNIT_NAME_EM);
            scrnMsg.addGUIAttribute(guiAttr);
        } else {

            EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCRN_ID, id);
            guiAttr.setStyleAttribute(STYLE_ATTR_WIDTH, PX_VALUE_ZERO);
            scrnMsg.addGUIAttribute(guiAttr);
        }
    }

    /**
     * calculate column width
     * @param charLength int
     * @return column width
     */
    private static int calculateColumnWidth(int charLength) {
        if (charLength < 0) {
            return 0;
        } else {
            return charLength;
        }
    }

    /**
     * Convert to i18n label names
     * @param scrnMsg NMAL6870BMsg
     */
    public static void convLabelNames(NMAL6870BMsg scrnMsg) {
        List<EZDBStringItem> items = new ArrayList<EZDBStringItem>();

        // 1.Screen Title
        items.add(scrnMsg.xxScrNm);
        // 3.Search Criteria(H)
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.H.no(i).xxComnScrCondLbTxt_H)) {
                items.add(scrnMsg.H.no(i).xxComnScrCondLbTxt_H);
            }
        }
        // 4.Column (C)
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).xxComnScrColLbTxt_C)) {
                items.add(scrnMsg.C.no(i).xxComnScrColLbTxt_C);
            }
        }

        // Convert
        EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        for (EZDBStringItem item : items) {
            ZYPEZDItemValueSetter.setValue(item, converter.convLabel2i18nLabel("", item.getValue()));
        }
    }

    private static String getSuffixStr(int num) {
        return String.valueOf((char) ('A' + num - 10));
    }

    /**
     * Selection
     * @param scrnMsg NMAL6870BMsg
     */
    public static void selection(NMAL6870BMsg scrnMsg) {

        for (int a = 0; a < scrnMsg.A.getValidCount(); a++) {
            NMAL6870_ABMsg aBMsg = scrnMsg.A.no(a);

            for (int r = 0; r < scrnMsg.R.getValidCount(); r++) {
                NMAL6870_RBMsg rBMsg = scrnMsg.R.no(r);
                //UPDATE START 2016/05/17 QC#8182
                boolean flg = false;

                for (int c = 0; c < scrnMsg.C.getValidCount(); c++) {
                    String valA = getEZDStringItemValue(aBMsg, "xxComnScrColValTxt_A", c);
                    String valR = getEZDStringItemValue(rBMsg, "xxComnScrColValTxt_", c);

                    if (!ZYPCommonFunc.hasValue(valR)) {
                        continue;
                    }

                    if (valR.equals(valA)) {
                        flg = true;
                    }
                    //UPDATE END 2016/05/17 QC#8182
                }

                if (flg) {
                    aBMsg.xxChkBox_A0.setValue(ZYPConstant.FLG_ON_Y);
                    break;
                }
            }
        }
    }
}
