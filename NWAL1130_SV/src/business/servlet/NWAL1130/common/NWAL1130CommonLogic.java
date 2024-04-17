/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NWAL1130.common;

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
import business.blap.NWAL1130.NWAL1130CMsg;
import business.servlet.NWAL1130.NWAL1130BMsg;
import business.servlet.NWAL1130.NWAL1130Bean;
import business.servlet.NWAL1130.NWAL1130_ABMsg;
import business.servlet.NWAL1130.NWAL1130_CBMsg;
import business.servlet.NWAL1130.NWAL1130_CBMsgArray;
import business.servlet.NWAL1130.NWAL1130_HBMsg;
import business.servlet.NWAL1130.NWAL1130_HBMsgArray;
import business.servlet.NWAL1130.NWAL1130_RBMsgArray;
import business.servlet.NWAL1130.constant.NWAL1130Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 *  Common PopUp Common Logic (SV)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/22/2012   Fujitsu         T.Ishii         Create          N/A
 * 12/18/2012   Fujitsu         F.Saito         Update          WDS Defect#9
 * 09/20/2013   Hitachi         T.Arakawa       Update          WDS Defect#2453
 * 10/09/2015   Fujitsu         M.Nakamura      Update          S21 CSA
 * 02/10/2016   Fujitsu         S.Ohki          Update          S21 CSA[Defect#1622]
 * 02/19/2016   Fujitsu         W.Honda         Update          S21 CSA QC#1130
 * 02/19/2016   Fujitsu         M.Ohno          Update          S21 CSA QC#3243
 * 2016/07/25   Hitachi         T.Tomita        Update          QC#11471
 * 2018/10/23   Fujitsu         M.Ohno          Update          QC#28425
 *</pre>
 */
public class NWAL1130CommonLogic implements NWAL1130Constant {

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

        NWAL1130BMsg scrnMsg = (NWAL1130BMsg) bMsg;

        // START 2016/02/10 S.Ohki [Defect#1622 Add]
        setScrnItemSearchCriteriaOff(handler, scrnMsg);
        // END 2016/02/10 S.Ohki [Defect#1622 Add]

        // set screen item condition
        setScreenItemCondition(scrnMsg);
    }

    /**
     * @return bizMsg NWAL1130CMsg
     */
    public static NWAL1130CMsg setRequestData_NWAL1130Scrn00_Search() {

        NWAL1130CMsg bizMsg = new NWAL1130CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);

        return bizMsg;
    }

    /**
     * Execute EZ Default Input Check
     * @param bMsg EZDBMsg
     */
    public static void ezCheck(EZDBMsg bMsg) {

        NWAL1130BMsg scrnMsg = (NWAL1130BMsg) bMsg;

        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
//        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H0);
//        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H1);
//        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H2);
//        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H3);
//        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H4);
//        // S21 CSA Add Start
//        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H5);
//        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H6);
//        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H7);
//        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H8);
//        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_H9);
//        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_HA);
//        scrnMsg.addCheckItem(scrnMsg.xxComnScrCondValTxt_HB);
//        // S21 CSA Add End
        scrnMsg.addCheckItem(scrnMsg.xxScrItem500Txt_H0);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem500Txt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem500Txt_H2);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem500Txt_H3);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem500Txt_H4);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem500Txt_H5);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem500Txt_H6);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem500Txt_H7);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem500Txt_H8);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem500Txt_H9);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem500Txt_HA);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem500Txt_HB);
        // END 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
        scrnMsg.putErrorScreen();
    }

    /**
     * Set table BackGround Color(White/Gray)
     * @param bMsg EZDBMsg
     */
    public static void setTableBGColor(EZDBMsg bMsg) {

        NWAL1130BMsg scrnMsg = (NWAL1130BMsg) bMsg;

        S21TableColorController lineTblColor = new S21TableColorController(SCRN_ID, scrnMsg);

        lineTblColor.setAlternateRowsBG(TABLE_ID, scrnMsg.A);
    }

    /**
     * Set result
     * @param scrnMsg NWAL1130BMsg
     * @param resultArray NWAL1130_RBMsgArray
     * @param selectedItem NWAL1130_ABMsg
     * @param selectedColumnNumber int
     */
    public static void setResult(NWAL1130BMsg scrnMsg, NWAL1130_RBMsgArray resultArray, NWAL1130_ABMsg selectedItem, int selectedColumnNumber) {

        for (int i = 0; i < resultArray.length(); i++) {
            ZYPEZDItemValueSetter.setValue(resultArray.no(i).xxComnScrColValTxt, getEZDStringItemValue(selectedItem, "xxComnScrColValTxt_A", i));
            ZYPEZDItemValueSetter.setValue(resultArray.no(i).xxComnScrQueryColNm, scrnMsg.C.no(i).xxComnScrQueryColNm_C);
            if (i == selectedColumnNumber) {
                resultArray.no(i).xxSelFlg.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                resultArray.no(i).xxSelFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
    }

    /**
     * set output parameter
     * @param arg Object[]
     * @param resultArray NWAL1130_RBMsgArray
     */
    public static void setOutputParam(Object[] arg, NWAL1130_RBMsgArray resultArray) {

        if (!(arg instanceof Object[])) {
            return;
        }

        if (arg.length < MAX_INPUT_PARAM_NUM) {
            return;
        }

        if (arg[INPUT_PARAM_RESULT] instanceof EZDMsgArray) {
            // EDIT START WDS Defect#9
            EZDMsg.copy(resultArray, "", (EZDMsgArray) arg[INPUT_PARAM_RESULT], (String) arg[INPUT_PARAM_SUFFIX]);
            EZDDebugOutput.println(1, "NWAL1130 Output Parameters:" + ((EZDMsgArray) arg[INPUT_PARAM_RESULT]).toString(), ((EZDMsgArray) arg[INPUT_PARAM_RESULT]).toString());
            // EDIT END WDS Defect#9
        }
    }

    /**
     * Get EZDStringItem value
     * @param msg EZDMsg
     * @param itemName String
     * @param suffixNumber int
     * @return String value
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
            // S21 CSA Mod Start
//            msg.setValueString(String.format(itemName + "%d", suffixNumber), -1, value);
            if (suffixNumber > 9) {
                msg.setValueString(String.format(itemName + getSuffixStr(suffixNumber)), -1, value);
            } else {
                msg.setValueString(String.format(itemName + "%d", suffixNumber), -1, value);
            }
            // S21 CSA Mod End
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
     * @param scrnMsg NWAL1130BMsg
     * @param arg Object[]
     * @param glblCmpyCd Global Company Code
     */
    public static void setInputParam(NWAL1130BMsg scrnMsg, Object[] arg, String glblCmpyCd) {

        if (!(arg instanceof Object[])) {
            return;
        }

        if (arg.length < MAX_INPUT_PARAM_NUM) {
            return;
        }

        // 0.Global Company Code
        // DELETE START WDS Defect#9
//        if (arg[INPUT_PARAM_GLBL_CMPY_CD] instanceof EZDBStringItem) {
//
//            ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, (EZDBStringItem) arg[INPUT_PARAM_GLBL_CMPY_CD]);
//        } else {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, (String) arg[INPUT_PARAM_GLBL_CMPY_CD]);
//        }
        // DELETE END WDS Defect#9
        // ADD START WDS Defect#9
        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, glblCmpyCd);
        // ADD END WDS Defect#9

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
            // START 2016/02/19 W.Honda [S21 CSA QC#1130 ADD]
            for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(i).xxScrItem500Txt_H, scrnMsg.H.no(i).xxComnScrCondValTxt_H);
            }
            // END 2016/02/19 W.Honda [S21 CSA QC#1130 ADD]
        } else {
            List< ? > paramList = (List< ? >) arg[INPUT_PARAM_CRITERIA];
            int index = 0;
            for (Object param : paramList) {
                if (param instanceof Object[]) {
                    int column = 0;
                    ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(index).xxComnScrCondLbTxt_H, (String) ((Object[]) param)[column++]);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(index).xxComnScrQueryFltrTxt_H, (String) ((Object[]) param)[column++]);
                    // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
                    //ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(index).xxComnScrCondValTxt_H, (String) ((Object[]) param)[column++]);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(index).xxScrItem500Txt_H, (String) ((Object[]) param)[column++]);
                    // End 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
                    // 2018/10/19 QC#28425 Mod Start
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(index++).xxComnScrQueryLikeFlg_H, (String) ((Object[]) param)[column++]);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(index).xxComnScrQueryLikeFlg_H, (String) ((Object[]) param)[column++]);
                    if (((Object[]) param).length > 4) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(index++).xxValUpdFlg_H, (String) ((Object[]) param)[column++]);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(index++).xxValUpdFlg_H, ZYPConstant.FLG_ON_Y);
                    }
                    // 2018/10/19 QC#28425 Mod End
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
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(index++).xxSelFlg_C, (String) ((Object[]) param)[column++]);
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
                    // S21 CSA Mod Start
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(index).xxSortOrdByTxt_S, (String) ((Object[]) param)[column++]);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(index++).xxSortOrdByTxt_S, (String) ((Object[]) param)[column++]);
                    // S21 CSA Mod End
                }
            }
            scrnMsg.S.setValidCount(index);
        }

        // 6.Output(R)
        if (arg[INPUT_PARAM_RESULT] instanceof EZDMsgArray) {
            // EDIT START WDS Defect#9
            if (arg[INPUT_PARAM_SUFFIX] == null) {
                arg[INPUT_PARAM_SUFFIX] = "";
            }
            EZDMsg.copy((EZDMsgArray) arg[INPUT_PARAM_RESULT], (String) arg[INPUT_PARAM_SUFFIX], scrnMsg.R, "");
            // EDIT END WDS Defect#9
        }
    }

    /**
     * prepare screen item
     * @param scrnMsg NWAL1130BMsg
     */
    public static void prepareScreenItem(NWAL1130BMsg scrnMsg) {

        // search criteria
        prepareSearchCriteria(scrnMsg, scrnMsg.H);

        // column
        prepareColumn(scrnMsg, scrnMsg.C);
    }

    /**
     * needs initial search
     * @param scrnMsg NWAL1130BMsg
     * @return true : necessary , false : unnecessary
     */
    public static boolean needsInitialSearch(NWAL1130BMsg scrnMsg) {
        if (!existsSearchCriteria(scrnMsg) && existsSelectColumn(scrnMsg)) {
            return true;
        }
        return existsSearchCriteriaValue(scrnMsg);
    }

    /**
     * exists select column
     * @param scrnMsg NWAL1130BMsg
     * @return true : exist, false : not exist
     */
    private static boolean existsSelectColumn(NWAL1130BMsg scrnMsg) {

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
     * @param scrnMsg NWAL1130BMsg
     * @return true : exist, false : not exist
     */
    private static boolean existsSearchCriteria(NWAL1130BMsg scrnMsg) {

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
        // S21 CSA Add Start
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
        // S21 CSA Add End
        return false;
    }

    /**
     * exists search criteria value
     * @param scrnMsg NWAL1130BMsg
     * @return true : exist, false : not exist
     */
    private static boolean existsSearchCriteriaValue(NWAL1130BMsg scrnMsg) {

        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
        //if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrCondValTxt_H0)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem500Txt_H0)) {
        // END 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
            return true;
        }

        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
        //if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrCondValTxt_H1)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem500Txt_H1)) {
        // END 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
            return true;
        }

        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
        //if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrCondValTxt_H2)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem500Txt_H2)) {
        // END 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
            return true;
        }

        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
        //if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrCondValTxt_H3)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem500Txt_H3)) {
        // END 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
            return true;
        }

        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
        //if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrCondValTxt_H4)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem500Txt_H4)) {
        // END 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
            return true;
        }

        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
        // S21 CSA Add Start
        //if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrCondValTxt_H5)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem500Txt_H5)) {
        // END 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
            return true;
        }

        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
        //if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrCondValTxt_H6)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem500Txt_H6)) {
        // END 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
            return true;
        }

        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
        //if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrCondValTxt_H7)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem500Txt_H7)) {
        // END 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
            return true;
        }

        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
        //if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrCondValTxt_H8)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem500Txt_H8)) {
        // END 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
            return true;
        }

        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
        //if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrCondValTxt_H9)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem500Txt_H9)) {
        // END 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
            return true;
        }

        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
        //if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrCondValTxt_HA)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem500Txt_HA)) {
        // END 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
            return true;
        }

        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
        //if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrCondValTxt_HB)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem500Txt_HB)) {
        // END 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
            return true;
        }
        // S21 CSA Add End
        return false;
    }

    /**
     * prepare search criteria
     * @param scrnMsg NWAL1130BMsg
     * @param searchCriteriaArray NWAL1130_HBMsgArray
     */
    private static void prepareSearchCriteria(NWAL1130BMsg scrnMsg, NWAL1130_HBMsgArray searchCriteriaArray) {

        for (int i = 0; i < searchCriteriaArray.length(); i++) {
            setSearchCriteria(scrnMsg, searchCriteriaArray.no(i), i);
        }
    }

    /**
     * prepare column
     * @param scrnMsg NWAL1130BMsg
     * @param columnArray NWAL1130_CBMsgArray
     */
    private static void prepareColumn(NWAL1130BMsg scrnMsg, NWAL1130_CBMsgArray columnArray) {
        for (int i = 0; i < columnArray.length(); i++) {
            setColumn(scrnMsg, columnArray.no(i), i);
        }
    }

    /**
     * set search criteria
     * @param scrnMsg NWAL1130BMsg
     * @param searchCriteria NWAL1130_HBMsg
     * @param itemSuffixNumber int
     */
    private static void setSearchCriteria(NWAL1130BMsg scrnMsg, NWAL1130_HBMsg searchCriteria, int itemSuffixNumber) {

        if (ZYPCommonFunc.hasValue(searchCriteria.xxComnScrCondLbTxt_H)) {
            setEZDStringItemValue(scrnMsg, NWAL1130Bean.xxComnScrCondLbTxt_H, itemSuffixNumber, searchCriteria.xxComnScrCondLbTxt_H.getValue());
        }
        if (ZYPCommonFunc.hasValue(searchCriteria.xxScrItem500Txt_H)) {
            setEZDStringItemValue(scrnMsg, NWAL1130Bean.xxScrItem500Txt_H, itemSuffixNumber, searchCriteria.xxScrItem500Txt_H.getValue());
        }
        if (ZYPCommonFunc.hasValue(searchCriteria.xxComnScrQueryFltrTxt_H)) {
            setEZDStringItemValue(scrnMsg, NWAL1130Bean.xxComnScrQueryFltrTxt_H, itemSuffixNumber, searchCriteria.xxComnScrQueryFltrTxt_H.getValue());
        }
        if (ZYPConstant.FLG_ON_Y.equals(searchCriteria.xxComnScrQueryLikeFlg_H.getValue())) {
            setEZDStringItemValue(scrnMsg, NWAL1130Bean.xxComnScrQueryLikeFlg_H, itemSuffixNumber, ZYPConstant.FLG_ON_Y);
            setEZDStringItemValue(scrnMsg, "xxScrItem7Txt_H", itemSuffixNumber, "(*)");
        }
        // 2018/10/19 QC#28425 Add Start
        if (ZYPCommonFunc.hasValue(searchCriteria.xxValUpdFlg_H)) {
            setEZDStringItemValue(scrnMsg, NWAL1130Bean.xxValUpdFlg_H, itemSuffixNumber, searchCriteria.xxValUpdFlg_H.getValue());
        }
        // 2018/10/19 QC#28425 Add End
    }

    /**
     * set column
     * @param scrnMsg NWAL1130BMsg
     * @param column NWAL1130_CBMsg
     * @param itemSuffixNumber int
     */
    private static void setColumn(NWAL1130BMsg scrnMsg, NWAL1130_CBMsg column, int itemSuffixNumber) {

        if (ZYPCommonFunc.hasValue(column.xxComnScrQueryColNm_C)) {
            setEZDStringItemValue(scrnMsg, NWAL1130Bean.xxComnScrQueryColNm_C, itemSuffixNumber, column.xxComnScrQueryColNm_C.getValue());
            if (ZYPCommonFunc.hasValue(column.xxComnScrColLbTxt_C)) {
                setEZDStringItemValue(scrnMsg, NWAL1130Bean.xxComnScrColLbTxt_C, itemSuffixNumber, column.xxComnScrColLbTxt_C.getValue());
            }
            if (ZYPCommonFunc.hasValue(column.xxSelFlg_C) && ZYPConstant.FLG_ON_Y.equals(column.xxSelFlg_C.getValue())) {
                setEZDStringItemValue(scrnMsg, NWAL1130Bean.xxSelFlg_C, itemSuffixNumber, column.xxSelFlg_C.getValue());
            }
            if (ZYPCommonFunc.hasValue(column.xxComnScrColLg_C)) {
                setEZDBigDecimalItemValue(scrnMsg, NWAL1130Bean.xxComnScrColLg_C, itemSuffixNumber, column.xxComnScrColLg_C.getValue());
            } else {
                setEZDBigDecimalItemValue(scrnMsg, NWAL1130Bean.xxComnScrColLg_C, itemSuffixNumber, BigDecimal.ZERO);
            }
        } else {
            setEZDBigDecimalItemValue(scrnMsg, NWAL1130Bean.xxComnScrColLg_C, itemSuffixNumber, BigDecimal.ZERO);
        }
    }

    /**
     * set screen item condition
     * @param scrnMsg NWAL1130BMsg
     */
    private static void setScreenItemCondition(NWAL1130BMsg scrnMsg) {

        // search criteria table row height
        int hiddenSearchCriteriaTableRowNum = 0;
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H0) && !ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H1)) {
            hiddenSearchCriteriaTableRowNum++;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H2) && !ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H3)) {
            hiddenSearchCriteriaTableRowNum++;
        }
        // S21 CSA Mod Start
//        if (!ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H4)) {
//            hiddenSearchCriteriaTableRowNum++;
//        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H4) && !ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H5)) {
            hiddenSearchCriteriaTableRowNum++;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H6) && !ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H7)) {
            hiddenSearchCriteriaTableRowNum++;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H8) && !ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_H9)) {
            hiddenSearchCriteriaTableRowNum++;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_HA) && !ZYPCommonFunc.hasValue(scrnMsg.xxComnScrQueryFltrTxt_HB)) {
            hiddenSearchCriteriaTableRowNum++;
        }
        // S21 CSA Mod End

        // table body height
        int tableBodyHeight = TABLE_BODY_HEIGHT + SEARCH_CRITERIA_ROW_HEIGHT * hiddenSearchCriteriaTableRowNum;
        if (tableBodyHeight > 0) {
            EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCRN_ID, TABLE_ID_BODY);
            guiAttr.setStyleAttribute(STYLE_ATTR_HEIGHT, String.valueOf(tableBodyHeight) + UNIT_NAME_PX);
            scrnMsg.addGUIAttribute(guiAttr);
        }

        // header column width
        setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColLbTxt_C0, scrnMsg.xxComnScrColLg_C0);
        setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColLbTxt_C1, scrnMsg.xxComnScrColLg_C1);
        setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColLbTxt_C2, scrnMsg.xxComnScrColLg_C2);
        setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColLbTxt_C3, scrnMsg.xxComnScrColLg_C3);
        setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColLbTxt_C4, scrnMsg.xxComnScrColLg_C4);
        setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColLbTxt_C5, scrnMsg.xxComnScrColLg_C5);
        setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColLbTxt_C6, scrnMsg.xxComnScrColLg_C6);
        setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColLbTxt_C7, scrnMsg.xxComnScrColLg_C7);
        setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColLbTxt_C8, scrnMsg.xxComnScrColLg_C8);
        setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColLbTxt_C9, scrnMsg.xxComnScrColLg_C9);

        
        // START 2016/02/23 K.Aratani [S21 CSA QC#3243-1]
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.clearGUIAttribute(SCRN_ID, NWAL1130Bean.xxComnScrColValTxt_A0 + "#" + Integer.toString(i));
            scrnMsg.clearGUIAttribute(SCRN_ID, NWAL1130Bean.xxComnScrColValTxt_A1 + "#" + Integer.toString(i));
            scrnMsg.clearGUIAttribute(SCRN_ID, NWAL1130Bean.xxComnScrColValTxt_A2 + "#" + Integer.toString(i));
            scrnMsg.clearGUIAttribute(SCRN_ID, NWAL1130Bean.xxComnScrColValTxt_A3 + "#" + Integer.toString(i));
            scrnMsg.clearGUIAttribute(SCRN_ID, NWAL1130Bean.xxComnScrColValTxt_A4 + "#" + Integer.toString(i));
            scrnMsg.clearGUIAttribute(SCRN_ID, NWAL1130Bean.xxComnScrColValTxt_A5 + "#" + Integer.toString(i));
            scrnMsg.clearGUIAttribute(SCRN_ID, NWAL1130Bean.xxComnScrColValTxt_A6 + "#" + Integer.toString(i));
            scrnMsg.clearGUIAttribute(SCRN_ID, NWAL1130Bean.xxComnScrColValTxt_A7 + "#" + Integer.toString(i));
            scrnMsg.clearGUIAttribute(SCRN_ID, NWAL1130Bean.xxComnScrColValTxt_A8 + "#" + Integer.toString(i));
            scrnMsg.clearGUIAttribute(SCRN_ID, NWAL1130Bean.xxComnScrColValTxt_A9 + "#" + Integer.toString(i));
        }
        // END 2016/02/23 K.Aratani [S21 CSA QC#3243-1]
        
        if (scrnMsg.A.getValidCount() > 0) {
            // START 2016/02/23 M.Ohno [S21 CSA QC#3243]
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // list column width
                setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColValTxt_A0 + "#" + Integer.toString(i), scrnMsg.xxComnScrColLg_C0);
                setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColValTxt_A1 + "#" + Integer.toString(i), scrnMsg.xxComnScrColLg_C1);
                setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColValTxt_A2 + "#" + Integer.toString(i), scrnMsg.xxComnScrColLg_C2);
                setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColValTxt_A3 + "#" + Integer.toString(i), scrnMsg.xxComnScrColLg_C3);
                setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColValTxt_A4 + "#" + Integer.toString(i), scrnMsg.xxComnScrColLg_C4);
                setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColValTxt_A5 + "#" + Integer.toString(i), scrnMsg.xxComnScrColLg_C5);
                setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColValTxt_A6 + "#" + Integer.toString(i), scrnMsg.xxComnScrColLg_C6);
                setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColValTxt_A7 + "#" + Integer.toString(i), scrnMsg.xxComnScrColLg_C7);
                setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColValTxt_A8 + "#" + Integer.toString(i), scrnMsg.xxComnScrColLg_C8);
                setColumnWidth(scrnMsg, NWAL1130Bean.xxComnScrColValTxt_A9 + "#" + Integer.toString(i), scrnMsg.xxComnScrColLg_C9);
            }
            // END 2016/02/23 M.Ohno [S21 CSA QC#3243]
        }
    }

    /**
     * set column width
     * @param scrnMsg NWAL1130BMsg
     * @param id String
     * @param charLength EZDBBigDecimalItem
     */
    private static void setColumnWidth(NWAL1130BMsg scrnMsg, String id, EZDBBigDecimalItem charLength) {

        if (ZYPCommonFunc.hasValue(charLength)) {

            int clumnWidth = calculateColumnWidth(charLength.getValueInt());

            // START 2016/02/23 M.Ohno [S21 CSA QC#3243]
            EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCRN_ID, id);
            if (clumnWidth == 0) {
                guiAttr.setStyleAttribute(STYLE_ATTR_WHITE_SPACE, STYLE_VALUE_NOWRAP);
                guiAttr.setStyleAttribute(STYLE_ATTR_WIDTH, PX_VALUE_ZERO);
            } else {
                guiAttr.setStyleAttribute(STYLE_ATTR_WORD_BREAK, STYLE_VALUE_BREAK_ALL);
                guiAttr.setStyleAttribute(STYLE_ATTR_WIDTH, String.valueOf(clumnWidth) + UNIT_NAME_EM);
            }
            // END 2016/02/23 M.Ohno [S21 CSA QC#3243]
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

    // S-ADD-20130920 WDS Defect#2453
    /**
     * Convert to i18n label names
     * @param scrnMsg NWAL1130BMsg
     */
    public static void convLabelNames(NWAL1130BMsg scrnMsg) {
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
    // E-ADD-20130920 WDS Defect#2453

    // S21 CSA Add Start
    private static String getSuffixStr(int num) {
        return String.valueOf((char)('A' + num - 10));
    }
    // S21 CSA Add End

    // START 2016/02/10 S.Ohki [Defect#1622 Add]
    /**
     * Set Screen Item Search Criteria Off
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1130BMsg
     */
    private static void setScrnItemSearchCriteriaOff(EZDCommonHandler handler, NWAL1130BMsg scrnMsg) {

        if (scrnMsg.H.getValidCount() == 0) {
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);

            EZDGUIAttribute srchBtn = new EZDGUIAttribute(SCRN_ID,"Search");
            srchBtn.setVisibility(false);
            scrnMsg.addGUIAttribute(srchBtn);

            return;
        }
    }
    // END 2016/02/10 S.Ohki [Defect#1622 Add]
}
