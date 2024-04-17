/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0020.common;

import static business.servlet.NWWL0020.constant.NWWL0020Constant.BIZ_ID;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.BTN_CMN_APL;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.BTN_CMN_APR;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.BTN_CMN_CLR;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.BTN_CMN_DEL;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.BTN_CMN_DWL;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.BTN_CMN_RJT;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.BTN_CMN_RST;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.BTN_CMN_RTN;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.BTN_CMN_SAV;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.BTN_CMN_SUB;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.CHK_TIME_PATTERN;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.COLON;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.NWWM0026E;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.NWWM0027E;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.SCRN_ID_00;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.SEL_INTVL_RADIO_INTVL;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.SEL_INTVL_RADIO_MIN_AFTR_HOUR;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.TAB_NAME_HEADER;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.TIME_FORMAT;

import java.util.List;

import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWWL0020.NWWL0020BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.NTFY_FREQ_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWWL0020CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0020CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWWL0020BMsg
     * @param scrnAMsgAry NWWL0020_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWWL0020BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWWL0020BMsg
     * @param scrnAMsgAry NWWL0020_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWWL0020BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWWL0020BMsg
     * @param scrnAMsgAry NWWL0020_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWWL0020BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * Checking Time Item
     * @param scrnMsg NWWL0020BMsg
     */
    public static void checkTimeItem(NWWL0020BMsg scrnMsg) {
        // Delivery &Install Tab
        isTime(scrnMsg.xxStartDplyTmTxt, scrnMsg.xxEndDplyTmTxt);
    }

    /**
     * Is Time
     * @param timeItems EZDBStringItem[]
     */
    private static void isTime(EZDBStringItem... timeItems) {
        for (EZDBStringItem timeItem : timeItems) {
            if (ZYPCommonFunc.hasValue(timeItem)) {
                if (timeItem.getValue().length() < 5) {
                    timeItem.setErrorInfo(1, NWWM0026E, new String[] {TIME_FORMAT });
                }

                if (!timeItem.getValue().matches(CHK_TIME_PATTERN)) {
                    timeItem.setErrorInfo(1, NWWM0027E);
                }
            }
        }
    }

    /**
     * Set Screen Time
     * @param scrnMsg NWWL0020BMsg
     */
    public static void setScrnTm(NWWL0020BMsg scrnMsg) {

        scrnMsg.xxStartDplyTmTxt.setValue(setTm(scrnMsg.ntfyStartHourMn_PD.getValue()));
        scrnMsg.xxEndDplyTmTxt.setValue(setTm(scrnMsg.ntfyEndHourMn_PD.getValue()));
    }

    /**
     * Get Screen Time
     * @param scrnMsg NWWL0020BMsg
     */
    public static void getScrnTm(NWWL0020BMsg scrnMsg) {

        scrnMsg.ntfyStartHourMn_PD.setValue(getTm(scrnMsg.xxStartDplyTmTxt.getValue()));
        scrnMsg.ntfyEndHourMn_PD.setValue(getTm(scrnMsg.xxEndDplyTmTxt.getValue()));
    }

    /**
     * Set Time
     * @param tm time
     * @return time
     */
    public static String setTm(String tm) {
        if (ZYPCommonFunc.hasValue(tm)) {
            StringBuffer strBuf = new StringBuffer();
            strBuf.append(tm);
            if (strBuf.length() > 2) {
                strBuf.insert(2, COLON);
            }
            return strBuf.toString();
        }
        return tm;
    }

    /**
     * Get Time
     * @param tm time
     * @return time
     */
    public static String getTm(String tm) {
        if (ZYPCommonFunc.hasValue(tm)) {
            return tm.replace(COLON, "");
        }
        return tm;
    }

    /**
     * Set Control Fields
     * @param scrnMsg NWWL0020BMsg
     * @param handler EZDCommonHandler
     */
    public static void setControlFields(NWWL0020BMsg scrnMsg, EZDCommonHandler handler) {

        scrnMsg.ntfyHdrId_H0.setInputProtected(true);
        setControlFieldsHdrNm(scrnMsg);
        setControlFieldsActDtlBtn(scrnMsg, handler);

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg.getValue())) {

            // Header TAB
            scrnMsg.ntfyBizAreaTpCd_SL.setInputProtected(true);
            scrnMsg.ntfySubAreaTpCd_SL.setInputProtected(true);
            scrnMsg.effFromDt_H0.setInputProtected(true);
            scrnMsg.effThruDt_H0.setInputProtected(true);
            scrnMsg.ntfyHdrActvFlg_H0.setInputProtected(true);

            scrnMsg.ntfyFreqTpCd_SL.setInputProtected(true);
            scrnMsg.ntfyRunDayListTxt_PD.setInputProtected(true);
            scrnMsg.ntfyEomFlg_PD.setInputProtected(true);
            scrnMsg.ntfyRunSunFlg_PD.setInputProtected(true);
            scrnMsg.ntfyRunMonFlg_PD.setInputProtected(true);
            scrnMsg.ntfyRunTueFlg_PD.setInputProtected(true);
            scrnMsg.ntfyRunWedFlg_PD.setInputProtected(true);
            scrnMsg.ntfyRunThuFlg_PD.setInputProtected(true);
            scrnMsg.ntfyRunFriFlg_PD.setInputProtected(true);
            scrnMsg.ntfyRunSatFlg_PD.setInputProtected(true);
            scrnMsg.xxStartDplyTmTxt.setInputProtected(true);
            scrnMsg.xxRptChkFlg_PD.setInputProtected(true);
            scrnMsg.histDaysAot_PD.setInputProtected(true);
            scrnMsg.xxRadioBtn_PD.setInputProtected(true);

            setControlFieldsPrdcDtl(scrnMsg);

            // SQL TAB
            scrnMsg.xxNtfySqlTxt.setInputProtected(true);

            // Action Detail TAB
            scrnMsg.xxRadioBtn_A0.setInputProtected(true);

            scrnMsg.ntfyActNm.setInputProtected(true);
            scrnMsg.ntfyActDescTxt.setInputProtected(true);
            scrnMsg.ntfyActTpCd_SL.setInputProtected(true);
            scrnMsg.ntfyEmlToAddr.setInputProtected(true);
            scrnMsg.ntfyOtptTpCd_SL.setInputProtected(true);
            scrnMsg.ntfyEmlCcAddr.setInputProtected(true);
            scrnMsg.ntfyEmlRpyToAddr.setInputProtected(true);
            scrnMsg.ntfyEmlBccAddr.setInputProtected(true);
            scrnMsg.ntfyDistListNmListTxt_LK.setInputProtected(true);
            scrnMsg.ntfyDistListNmListTxt.setInputProtected(true);
            scrnMsg.rtrvToAddrFromSqlFlg.setInputProtected(true);
            scrnMsg.ntfyAttTpCd_SL.setInputProtected(true);
            scrnMsg.ntfyEmlSubjTxt.setInputProtected(true);
            scrnMsg.xxNtfyEmlBodyTxt.setInputProtected(true);

            scrnMsg.xxNum.setInputProtected(true);
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.B.no(i).ntfyActDtlColSortNum_B0.setInputProtected(true);
                scrnMsg.B.no(i).hdrLbNm_B0.setInputProtected(true);
                scrnMsg.B.no(i).placeHldNm_B0.setInputProtected(true);
            }

            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);

        } else {

            // Header TAB
            scrnMsg.ntfyBizAreaTpCd_SL.setInputProtected(false);
            scrnMsg.ntfySubAreaTpCd_SL.setInputProtected(false);
            scrnMsg.effThruDt_H0.setInputProtected(false);
            scrnMsg.ntfyHdrActvFlg_H0.setInputProtected(false);

            scrnMsg.ntfyFreqTpCd_SL.setInputProtected(false);
            scrnMsg.ntfyRunDayListTxt_PD.setInputProtected(false);
            scrnMsg.ntfyEomFlg_PD.setInputProtected(false);
            scrnMsg.ntfyRunSunFlg_PD.setInputProtected(false);
            scrnMsg.ntfyRunMonFlg_PD.setInputProtected(false);
            scrnMsg.ntfyRunTueFlg_PD.setInputProtected(false);
            scrnMsg.ntfyRunWedFlg_PD.setInputProtected(false);
            scrnMsg.ntfyRunThuFlg_PD.setInputProtected(false);
            scrnMsg.ntfyRunFriFlg_PD.setInputProtected(false);
            scrnMsg.ntfyRunSatFlg_PD.setInputProtected(false);
            scrnMsg.xxStartDplyTmTxt.setInputProtected(false);
            scrnMsg.xxRptChkFlg_PD.setInputProtected(false);
            scrnMsg.histDaysAot_PD.setInputProtected(false);

            if (ZYPCommonFunc.hasValue(scrnMsg.ntfyHdrPk_H0)) {
                scrnMsg.effFromDt_H0.setInputProtected(true);
            } else {
                scrnMsg.effFromDt_H0.setInputProtected(false);
            }

            setControlFieldsPrdcDtl(scrnMsg);

            // SQL TAB
            scrnMsg.xxNtfySqlTxt.setInputProtected(false);

            // Action Detail TAB
            scrnMsg.xxRadioBtn_A0.setInputProtected(false);

            setControlFieldsActDtl(scrnMsg, handler);
        }
    }

    /**
     * Set Control Fields Header Name and Header Description
     * @param scrnMsg NWWL0020BMsg
     * @param handler EZDCommonHandler
     */
    public static void setControlFieldsActDtlBtn(NWWL0020BMsg scrnMsg, EZDCommonHandler handler) {

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg.getValue())) {
            handler.setButtonEnabled("Add_Line", false);
            handler.setButtonEnabled("Del_Line", false);
            handler.setButtonEnabled("ViewActionDetail", false);
            handler.setButtonEnabled("CreateColumn", false);
        } else {

            handler.setButtonEnabled("Add_Line", true);

            if (scrnMsg.A.getValidCount() == 0) {
                handler.setButtonEnabled("ViewActionDetail", false);
                handler.setButtonEnabled("Del_Line", false);
            } else {
                handler.setButtonEnabled("ViewActionDetail", true);
                handler.setButtonEnabled("Del_Line", true);
            }
        }
    }

    /**
     * Set Control Fields Header Name and Header Description
     * @param scrnMsg NWWL0020BMsg
     */
    public static void setControlFieldsHdrNm(NWWL0020BMsg scrnMsg) {

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg.getValue())) {
            scrnMsg.ntfyHdrNm_H0.setInputProtected(true);
            scrnMsg.ntfyHdrDescTxt_H0.setInputProtected(true);
        } else {

            if (TAB_NAME_HEADER.equals(scrnMsg.xxDplyTab.getValue())) {
                scrnMsg.ntfyHdrNm_H0.setInputProtected(false);
                scrnMsg.ntfyHdrDescTxt_H0.setInputProtected(false);
            } else {
                scrnMsg.ntfyHdrNm_H0.setInputProtected(true);
                scrnMsg.ntfyHdrDescTxt_H0.setInputProtected(true);
            }
        }
    }

    /**
     * Set Control Fields Action List Protect
     * @param scrnMsg NWWL0020BMsg
     */
    public static void setControlFieldsActListProt(NWWL0020BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).ntfyActNm_A0.setInputProtected(true);
            scrnMsg.A.no(i).ntfyActDescTxt_A0.setInputProtected(true);
            scrnMsg.A.no(i).ntfyActTpDescTxt_A0.setInputProtected(true);
        }
    }

    /**
     * Set Control Fields Action Detail
     * @param scrnMsg NWWL0020BMsg
     * @param handler EZDCommonHandler
     */
    public static void setControlFieldsActDtl(NWWL0020BMsg scrnMsg, EZDCommonHandler handler) {

        boolean actDtlProtFlg = false;
        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDtlProtFlg.getValue())) {
            actDtlProtFlg = true;
        }

        scrnMsg.ntfyActNm.setInputProtected(actDtlProtFlg);
        scrnMsg.ntfyActDescTxt.setInputProtected(actDtlProtFlg);
        scrnMsg.ntfyActTpCd_SL.setInputProtected(actDtlProtFlg);
        scrnMsg.ntfyEmlToAddr.setInputProtected(actDtlProtFlg);
        scrnMsg.ntfyOtptTpCd_SL.setInputProtected(actDtlProtFlg);
        scrnMsg.ntfyEmlCcAddr.setInputProtected(actDtlProtFlg);
        scrnMsg.ntfyEmlRpyToAddr.setInputProtected(actDtlProtFlg);
        scrnMsg.ntfyEmlBccAddr.setInputProtected(actDtlProtFlg);
        scrnMsg.ntfyDistListNmListTxt_LK.setInputProtected(actDtlProtFlg);
        scrnMsg.ntfyDistListNmListTxt.setInputProtected(actDtlProtFlg);
        scrnMsg.rtrvToAddrFromSqlFlg.setInputProtected(actDtlProtFlg);
        scrnMsg.ntfyAttTpCd_SL.setInputProtected(actDtlProtFlg);
        scrnMsg.ntfyEmlSubjTxt.setInputProtected(actDtlProtFlg);
        scrnMsg.xxNtfyEmlBodyTxt.setInputProtected(actDtlProtFlg);

        scrnMsg.xxNum.setInputProtected(actDtlProtFlg);
        handler.setButtonEnabled("CreateColumn", !actDtlProtFlg);
        setControlFieldsColumns(scrnMsg, actDtlProtFlg);
    }

    /**
     * Set Control Fields Columns
     * @param scrnMsg NWWL0020BMsg
     * @param actDtlProtFlg boolean
     */
    public static void setControlFieldsColumns(NWWL0020BMsg scrnMsg, boolean actDtlProtFlg) {

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).ntfyActDtlColSortNum_B0.setInputProtected(actDtlProtFlg);
            scrnMsg.B.no(i).hdrLbNm_B0.setInputProtected(actDtlProtFlg);
            scrnMsg.B.no(i).placeHldNm_B0.setInputProtected(actDtlProtFlg);
        }
    }

    /**
     * Set Control Fields Action Detail TAB Submit Button
     * @param scrnMsg NWWL0020BMsg
     * @param handler EZDCommonHandler
     */
    public static void setControlFieldsActDtlTabSubmitBtn(NWWL0020BMsg scrnMsg, EZDCommonHandler handler) {

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg.getValue())) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        }
    }

    /**
     * Set Focus Item Frequency
     * @param scrnMsg NWWL0020BMsg
     */
    public static void setFocusItemFrequency(NWWL0020BMsg scrnMsg) {

        if (NTFY_FREQ_TP.DAILY.equals(scrnMsg.ntfyFreqTpCd_SL.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.xxStartDplyTmTxt);
        } else if (NTFY_FREQ_TP.WEEKLY.equals(scrnMsg.ntfyFreqTpCd_SL.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.ntfyRunSunFlg_PD);
        } else {
            scrnMsg.setFocusItem(scrnMsg.ntfyRunDayListTxt_PD);
        }
    }

    /**
     * Set Control Fields Repeat Check Box
     * @param scrnMsg NWWL0020BMsg
     */
    public static void setControlFieldsRepeatChkBox(NWWL0020BMsg scrnMsg) {

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg.getValue())) {
            scrnMsg.xxRptChkFlg_PD.setInputProtected(true);
        } else {
            if (NTFY_FREQ_TP.DAILY.equals(scrnMsg.ntfyFreqTpCd_SL.getValue())) {
                scrnMsg.xxRptChkFlg_PD.setInputProtected(false);
            } else {
                scrnMsg.xxRptChkFlg_PD.clear();
                scrnMsg.xxRptChkFlg_PD.setInputProtected(true);
            }
        }
    }

    /**
     * Set Control Fields Periodic Detail
     * @param scrnMsg NWWL0020BMsg
     */
    public static void setControlFieldsPrdcDtl(NWWL0020BMsg scrnMsg) {

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg.getValue())) {
            scrnMsg.xxEndDplyTmTxt.setInputProtected(true);
            scrnMsg.ntfyIntvlAot_PD.setInputProtected(true);
            scrnMsg.ntfyIntvlUomTpCd_SL.setInputProtected(true);
            scrnMsg.ntfyRunMnListTxt_PD.setInputProtected(true);
            scrnMsg.xxRadioBtn_PD.setInputProtected(true);
        } else {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.xxRptChkFlg_PD.getValue())) {
                scrnMsg.xxEndDplyTmTxt.setInputProtected(false);
                scrnMsg.xxRadioBtn_PD.setInputProtected(false);
                setControlFieldsSelIntvl(scrnMsg);
                scrnMsg.setFocusItem(scrnMsg.xxEndDplyTmTxt);
            } else {
                scrnMsg.xxEndDplyTmTxt.clear();
                scrnMsg.ntfyIntvlAot_PD.clear();
                scrnMsg.ntfyIntvlUomTpCd_SL.clear();
                scrnMsg.ntfyRunMnListTxt_PD.clear();
                scrnMsg.xxEndDplyTmTxt.setInputProtected(true);
                scrnMsg.ntfyIntvlAot_PD.setInputProtected(true);
                scrnMsg.ntfyIntvlUomTpCd_SL.setInputProtected(true);
                scrnMsg.ntfyRunMnListTxt_PD.setInputProtected(true);
                scrnMsg.xxRadioBtn_PD.setInputProtected(true);
            }
        }
    }

    /**
     * Set Backup Information Repeat Item
     * @param scrnMsg NWWL0020BMsg
     */
    public static void setBackupInfoRepeatItem(NWWL0020BMsg scrnMsg) {

        if (NTFY_FREQ_TP.DAILY.equals(scrnMsg.ntfyFreqTpCd_PB.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRptChkFlg_PB, scrnMsg.xxRptChkFlg_PD);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxEndDplyTmTxt_PB, scrnMsg.xxEndDplyTmTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ntfyRunMnListTxt_PB, scrnMsg.ntfyRunMnListTxt_PD);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ntfyIntvlAot_PB, scrnMsg.ntfyIntvlAot_PD);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ntfyIntvlUomTpCd_PB, scrnMsg.ntfyIntvlUomTpCd_SL);
        } else if (NTFY_FREQ_TP.DAILY.equals(scrnMsg.ntfyFreqTpCd_SL.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRptChkFlg_PD, scrnMsg.xxRptChkFlg_PB);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxEndDplyTmTxt, scrnMsg.xxEndDplyTmTxt_PB);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ntfyRunMnListTxt_PD, scrnMsg.ntfyRunMnListTxt_PB);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ntfyIntvlAot_PD, scrnMsg.ntfyIntvlAot_PB);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ntfyIntvlUomTpCd_SL, scrnMsg.ntfyIntvlUomTpCd_PB);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.ntfyFreqTpCd_PB, scrnMsg.ntfyFreqTpCd_SL);
    }

    /**
     * Set Control Fields Select Interval
     * @param scrnMsg NWWL0020BMsg
     */
    public static void setControlFieldsSelIntvl(NWWL0020BMsg scrnMsg) {

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg.getValue())) {
            scrnMsg.ntfyIntvlAot_PD.setInputProtected(true);
            scrnMsg.ntfyIntvlUomTpCd_SL.setInputProtected(true);
            scrnMsg.ntfyRunMnListTxt_PD.setInputProtected(true);
        } else {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.xxRptChkFlg_PD.getValue())) {
                if (SEL_INTVL_RADIO_INTVL.compareTo(scrnMsg.xxRadioBtn_PD.getValue()) == 0) {
                    scrnMsg.ntfyRunMnListTxt_PD.clear();
                    scrnMsg.ntfyIntvlAot_PD.setInputProtected(false);
                    scrnMsg.ntfyIntvlUomTpCd_SL.setInputProtected(false);
                    scrnMsg.ntfyRunMnListTxt_PD.setInputProtected(true);
                    scrnMsg.setFocusItem(scrnMsg.ntfyIntvlAot_PD);
                } else {
                    scrnMsg.ntfyIntvlAot_PD.clear();
                    scrnMsg.ntfyIntvlUomTpCd_SL.clear();
                    scrnMsg.ntfyIntvlAot_PD.setInputProtected(true);
                    scrnMsg.ntfyIntvlUomTpCd_SL.setInputProtected(true);
                    scrnMsg.ntfyRunMnListTxt_PD.setInputProtected(false);
                    scrnMsg.setFocusItem(scrnMsg.ntfyRunMnListTxt_PD);
                }
            }
        }
    }

    /**
     * Set Control Fields Retrieve To Address
     * @param scrnMsg NWWL0020BMsg
     */
    public static void setControlFieldsRtrvToAddr(NWWL0020BMsg scrnMsg) {

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDtlProtFlg.getValue())) {
            scrnMsg.ntfyEmlToAddr.setInputProtected(true);
            scrnMsg.ntfyDistListNmListTxt.setInputProtected(true);
            scrnMsg.ntfyDistListNmListTxt_LK.setInputProtected(true);
        } else {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.rtrvToAddrFromSqlFlg.getValue())) {
                scrnMsg.ntfyEmlToAddr.setInputProtected(true);
                scrnMsg.ntfyDistListNmListTxt.setInputProtected(true);
                scrnMsg.ntfyDistListNmListTxt_LK.setInputProtected(true);
            } else {
                scrnMsg.ntfyEmlToAddr.setInputProtected(false);
                scrnMsg.ntfyDistListNmListTxt.setInputProtected(false);
                scrnMsg.ntfyDistListNmListTxt_LK.setInputProtected(false);
            }
        }
    }

    /**
     * Set Interval Radio Value
     * @param scrnMsg NWWL0020BMsg
     */
    public static void setIntvalRadioValue(NWWL0020BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.ntfyRunMnListTxt_PD)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_PD, SEL_INTVL_RADIO_MIN_AFTR_HOUR);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_PD, SEL_INTVL_RADIO_INTVL);
        }
    }

    /**
     * Set Control Fields Retrieve To Address
     * @param scrnMsg NWWL0020BMsg
     */
    public static void setControlFieldsByAuth(NWWL0020BMsg scrnMsg) {

        if (hasUpdateFuncId()) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            return false;
        }

        if (funcList.contains("NWWL0020T020")) {
            return true;
        }

        return false;
    }
}
