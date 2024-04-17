/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1400.common;

import static business.servlet.NPAL1400.constant.NPAL1400Constant.BTN_CMN_BTN_1;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.BTN_CMN_BTN_2;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.BTN_CMN_BTN_3;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.BTN_CMN_BTN_4;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.BTN_CMN_BTN_5;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.BTN_CMN_BTN_6;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.BTN_CMN_BTN_7;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.BTN_CMN_BTN_9;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.BTN_DELETE_SEARCH;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.BTN_NEW;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.BTN_SAVE_SEARCH;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.BTN_SEARCH;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.COLUMN_DISP_NM_CD_FOR_TECH_CD;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.COLUMN_DISP_NM_CD_FOR_TECH_NM;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.COLUMN_SQL_NM_CD_FOR_TECH_CD;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.COLUMN_SQL_NM_CD_FOR_TECH_NM;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.COLUMN_WIDTH_CD_FOR_TECH_CD;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.COLUMN_WIDTH_CD_FOR_TECH_NM;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_MDSE_DESC_SHORT_TXT;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_MKT_MDL_CD;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_RMNF_END_DT_FR;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_RMNF_END_DT_TO;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_RMNF_MAIN_UNIT_MDSE_CD;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_RMNF_MAIN_UNIT_SER_NUM;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_RMNF_ORD_NUM;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_RMNF_ORD_STS_CD_SL;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_RMNF_START_DT_FR;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_RMNF_START_DT_TO;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_RTL_SWH_CD;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_RTL_SWH_NM;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_RTL_WH_CD;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_RTL_WH_NM;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_SRCH_OPT_NM;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_TECH_NM;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.DISPLAY_NM_TECH_TOC_CD;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.FUNC_EDIT;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.MODE_CODE_10;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.NMAM0288E;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.NMAM8644E;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.SCRN_ID;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.SORT_NAME_FOR_TECH_CD;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.SORT_VAL_FOR_TECH_CD;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.WHERE_DISP_NM_CD_FOR_TECH_CD;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.WHERE_DISP_NM_CD_FOR_TECH_NM;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.WHERE_SQL_NM_CD_FOR_TECH_CD;
import static business.servlet.NPAL1400.constant.NPAL1400Constant.WHERE_SQL_NM_CD_FOR_TECH_NM;

import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1400.NPAL1400BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NPAL1400 Reman Inquiry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/07/2016   CITS            S.Tanikawa      Create          N/A
 * 12/19/2016   CITS            Y.Fujii         Update          QC#16370
 *</pre>
 */
public class NPAL1400CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1400BMsg
     * @param functionList List<String>S
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NPAL1400BMsg scrnMsg, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible
        // Search Option
        scrnMsg.srchOptPk_PD.setInputProtected(false);
        scrnMsg.srchOptNm_PD.setInputProtected(false);
        scrnMsg.srchOptNm_TX.setInputProtected(false);

        // Search Condition
        scrnMsg.rtlWhCd.setInputProtected(false);
        scrnMsg.rtlSwhCd.setInputProtected(false);
        scrnMsg.rtlWhNm.setInputProtected(false);
        scrnMsg.rtlSwhNm.setInputProtected(false);
        scrnMsg.rmnfOrdNum.setInputProtected(false);
        scrnMsg.rmnfOrdStsCd_SL.setInputProtected(false);
        scrnMsg.rmnfStartDt_FR.setInputProtected(false);
        scrnMsg.rmnfStartDt_TO.setInputProtected(false);
        scrnMsg.rmnfMainUnitSerNum.setInputProtected(false);
        scrnMsg.t_MdlNm.setInputProtected(false);
        scrnMsg.rmnfMainUnitMdseCd.setInputProtected(false);
        scrnMsg.mdseDescShortTxt.setInputProtected(false);
        scrnMsg.techTocCd.setInputProtected(false);
        scrnMsg.techNm.setInputProtected(false);
        scrnMsg.rmnfEndDt_FR.setInputProtected(false);
        scrnMsg.rmnfEndDt_TO.setInputProtected(false);

        // Detail Header Control
        scrnMsg.xxPageShowFromNum.setInputProtected(true);
        scrnMsg.xxPageShowToNum.setInputProtected(true);
        scrnMsg.xxPageShowOfNum.setInputProtected(true);

        // button activation
        handler.setButtonEnabled(BTN_SAVE_SEARCH, true);
        handler.setButtonEnabled(BTN_DELETE_SEARCH, true);
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_NEW, false);

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        // set protect based on authority
        setAuthorityProtect(handler, scrnMsg, functionList);
    }

    /**
     * Table Column Protect
     * @param scrnMsg NPAL1400BMsg
     */
    public static void setTableScreen(NPAL1400BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).ownrTechTocCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).techNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).t_MdlNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).rmnfMainUnitMdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rmnfOrdStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rmnfMainUnitSerNum_A1.setInputProtected(true);

            scrnMsg.A.no(i).rmnfPrtUsgCostAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).rmnfTotLborCostAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).rmnfOthCostAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).rmnfTotCostAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        }
    }

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1400BMsg
     * @param functionList List<String>
     */
    private static void setAuthorityProtect(EZDCommonHandler handler, NPAL1400BMsg scrnMsg, List<String> functionList) {

        if (hasRegisterAuthority(functionList)) {
            handler.setButtonEnabled(BTN_NEW, true);
        } else {
            handler.setButtonEnabled(BTN_NEW, false);
        }
    }

    /**
     * Checks for register authority.
     * @param functionList List<String>
     * @return true, if successful
     */
    private static boolean hasRegisterAuthority(List<String> functionList) {
        for (String function : functionList) {
            if (FUNC_EDIT.equals(function)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the name for the error message.
     * @param scrnMsg NPAL1400BMsg
     */
    public static void setNameForMessage(NPAL1400BMsg scrnMsg) {
        // Search Options
        scrnMsg.srchOptNm_TX.setNameForMessage(DISPLAY_NM_SRCH_OPT_NM);

        // Search Condition
        scrnMsg.rtlWhCd.setNameForMessage(DISPLAY_NM_RTL_WH_CD);
        scrnMsg.rtlWhNm.setNameForMessage(DISPLAY_NM_RTL_WH_NM);
        scrnMsg.rtlSwhCd.setNameForMessage(DISPLAY_NM_RTL_SWH_CD);
        scrnMsg.rtlSwhNm.setNameForMessage(DISPLAY_NM_RTL_SWH_NM);
        scrnMsg.rmnfOrdNum.setNameForMessage(DISPLAY_NM_RMNF_ORD_NUM);
        scrnMsg.rmnfOrdStsCd_SL.setNameForMessage(DISPLAY_NM_RMNF_ORD_STS_CD_SL);
        scrnMsg.rmnfStartDt_FR.setNameForMessage(DISPLAY_NM_RMNF_START_DT_FR);
        scrnMsg.rmnfStartDt_TO.setNameForMessage(DISPLAY_NM_RMNF_START_DT_TO);
        scrnMsg.rmnfMainUnitSerNum.setNameForMessage(DISPLAY_NM_RMNF_MAIN_UNIT_SER_NUM);
        scrnMsg.t_MdlNm.setNameForMessage(DISPLAY_NM_MKT_MDL_CD);
        scrnMsg.rmnfMainUnitMdseCd.setNameForMessage(DISPLAY_NM_RMNF_MAIN_UNIT_MDSE_CD);
        scrnMsg.mdseDescShortTxt.setNameForMessage(DISPLAY_NM_MDSE_DESC_SHORT_TXT);
        scrnMsg.techTocCd.setNameForMessage(DISPLAY_NM_TECH_TOC_CD);
        scrnMsg.techNm.setNameForMessage(DISPLAY_NM_TECH_NM);
        scrnMsg.rmnfEndDt_FR.setNameForMessage(DISPLAY_NM_RMNF_END_DT_FR);
        scrnMsg.rmnfEndDt_TO.setNameForMessage(DISPLAY_NM_RMNF_END_DT_TO);

    }

    /**
     * Checks for Search Items
     * @param scrnMsg NPAL1400BMsg
     */
    public static void checkSearchItem(NPAL1400BMsg scrnMsg) {

        // addCheck
        addCheckAllHeaderItem(scrnMsg);

        // Mandatory Check
        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhNm) && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd) && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhNm) && !ZYPCommonFunc.hasValue(scrnMsg.rmnfOrdNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.rmnfOrdStsCd_SL) && !ZYPCommonFunc.hasValue(scrnMsg.rmnfMainUnitSerNum) && !ZYPCommonFunc.hasValue(scrnMsg.t_MdlNm) && !ZYPCommonFunc.hasValue(scrnMsg.rmnfMainUnitMdseCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.mdseDescShortTxt) && !ZYPCommonFunc.hasValue(scrnMsg.techTocCd) && !ZYPCommonFunc.hasValue(scrnMsg.techNm) && !ZYPCommonFunc.hasValue(scrnMsg.rmnfStartDt_FR)
                && !ZYPCommonFunc.hasValue(scrnMsg.rmnfStartDt_TO) && !ZYPCommonFunc.hasValue(scrnMsg.rmnfEndDt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.rmnfEndDt_TO)) {
            scrnMsg.rtlWhCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rtlWhNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rtlSwhCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rtlSwhNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rmnfOrdNum.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rmnfOrdStsCd_SL.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rmnfMainUnitSerNum.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.t_MdlNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rmnfMainUnitMdseCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.mdseDescShortTxt.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.techTocCd.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.techNm.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rmnfStartDt_FR.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rmnfStartDt_TO.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rmnfEndDt_FR.setErrorInfo(1, NMAM0288E, new String[] {});
            scrnMsg.rmnfEndDt_TO.setErrorInfo(1, NMAM0288E, new String[] {});
        }

        // date check
        if (ZYPCommonFunc.hasValue(scrnMsg.rmnfStartDt_FR) || ZYPCommonFunc.hasValue(scrnMsg.rmnfStartDt_TO)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.rmnfStartDt_FR)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rmnfStartDt_FR, scrnMsg.rmnfStartDt_TO);
            } else if (!ZYPCommonFunc.hasValue(scrnMsg.rmnfStartDt_TO)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rmnfStartDt_TO, scrnMsg.rmnfStartDt_FR);
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.rmnfEndDt_FR) || ZYPCommonFunc.hasValue(scrnMsg.rmnfEndDt_TO)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.rmnfEndDt_FR)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rmnfEndDt_FR, scrnMsg.rmnfEndDt_TO);
            } else if (!ZYPCommonFunc.hasValue(scrnMsg.rmnfEndDt_TO)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rmnfEndDt_TO, scrnMsg.rmnfEndDt_FR);
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.rmnfStartDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.rmnfStartDt_TO)) {
            if (ZYPDateUtil.compare(scrnMsg.rmnfStartDt_FR.getValue(), scrnMsg.rmnfStartDt_TO.getValue()) > 0) {
                scrnMsg.rmnfStartDt_FR.setErrorInfo(1, NMAM8644E, new String[] {"Start Date From", "Start Date Thru" });
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.rmnfEndDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.rmnfEndDt_TO)) {
            if (ZYPDateUtil.compare(scrnMsg.rmnfEndDt_FR.getValue(), scrnMsg.rmnfEndDt_TO.getValue()) > 0) {
                scrnMsg.rmnfEndDt_FR.setErrorInfo(1, NMAM8644E, new String[] {"End Date From", "End Date Thru" });
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.rmnfStartDt_TO) && ZYPCommonFunc.hasValue(scrnMsg.rmnfEndDt_FR)) {
            if (ZYPDateUtil.compare(scrnMsg.rmnfStartDt_TO.getValue(), scrnMsg.rmnfEndDt_FR.getValue()) > 0) {
                scrnMsg.rmnfStartDt_FR.setErrorInfo(1, NMAM8644E, new String[] {"Start Date", "End Date" });
                scrnMsg.rmnfStartDt_TO.setErrorInfo(1, NMAM8644E, new String[] {"Start Date", "End Date" });
            }
        }
    }

    /**
     * addCheckAllHeaderItem
     * @param scrnMsg NPAL1400BMsg
     */
    public static void addCheckAllHeaderItem(NPAL1400BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhNm);
        scrnMsg.addCheckItem(scrnMsg.rmnfOrdNum);
        scrnMsg.addCheckItem(scrnMsg.rmnfOrdStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.rmnfStartDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rmnfStartDt_TO);
        scrnMsg.addCheckItem(scrnMsg.rmnfMainUnitSerNum);
        scrnMsg.addCheckItem(scrnMsg.t_MdlNm);
        scrnMsg.addCheckItem(scrnMsg.rmnfEndDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rmnfEndDt_TO);
        scrnMsg.addCheckItem(scrnMsg.rmnfMainUnitMdseCd);
        scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt);
        scrnMsg.addCheckItem(scrnMsg.techTocCd);
        scrnMsg.addCheckItem(scrnMsg.techNm);
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NPAL1400BMsg
     * @return LocationPopup Param (NPAL1010) Object[]
     */
    public static Object[] setLocationPopupParam(NPAL1400BMsg scrnMsg) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.rtlWhNm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, scrnMsg.rtlSwhNm);

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.xxPopPrm_P4;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.xxPopPrm_P6;
        params[7] = scrnMsg.xxPopPrm_P7;
        params[8] = scrnMsg.xxPopPrm_P8;
        params[9] = scrnMsg.xxPopPrm_P9;
        params[10] = scrnMsg.xxPopPrm_PA;

        return params;
    }

    /**
     * setMdseInfoParam
     * @param scrnMsg NPAL1400BMsg
     * @return Item Master Search Popup Parameter
     */
    public static Object[] setMdseInfoParam(NPAL1400BMsg scrnMsg) {

        // Parameter Clear
        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();

        // Parameter Copy
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.rmnfMainUnitMdseCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.mdseDescShortTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, MODE_CODE_10);

        // Paramter Set
        Object[] param = new Object[10];
        param[0] = scrnMsg.xxPopPrm_P0;
        param[1] = scrnMsg.xxPopPrm_P1;
        param[2] = scrnMsg.xxPopPrm_P2;
        param[3] = scrnMsg.xxPopPrm_P3;
        param[4] = scrnMsg.xxPopPrm_P4;
        param[5] = scrnMsg.xxPopPrm_P5;
        param[6] = scrnMsg.xxPopPrm_P6;
        param[7] = scrnMsg.xxPopPrm_P7;
        param[8] = scrnMsg.xxPopPrm_P8;
        param[9] = scrnMsg.xxPopPrm_P9;

        return param;
    }

    /**
     * check Item
     * @param scrnMsg NPAL1400BMsg
     */
    public static void commonAddCheckItem(NPAL1400BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_SL);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_TX);

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhNm);
        scrnMsg.addCheckItem(scrnMsg.rmnfOrdNum);
        scrnMsg.addCheckItem(scrnMsg.rmnfOrdStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.rmnfStartDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rmnfStartDt_TO);
        scrnMsg.addCheckItem(scrnMsg.rmnfMainUnitSerNum);
        scrnMsg.addCheckItem(scrnMsg.t_MdlNm);
        scrnMsg.addCheckItem(scrnMsg.rmnfEndDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rmnfEndDt_TO);
        scrnMsg.addCheckItem(scrnMsg.rmnfMainUnitMdseCd);
        scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt);
        scrnMsg.addCheckItem(scrnMsg.techTocCd);
        scrnMsg.addCheckItem(scrnMsg.techNm);
    }

    /**
     * clear table
     * @param scrnMsg NPAL1400BMsg
     */
    public static void clearTable(NPAL1400BMsg scrnMsg) {
        scrnMsg.A.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }

    /**
     * setTecInfoeParam
     * @param scrnMsg NPAL1400BMsg
     * @param glblCmpyCd String
     * @param popupScrnNm String
     * @return TecCode / TecName Popup Parameter
     */
    public static Object[] setTecCodeParam(NPAL1400BMsg scrnMsg, String glblCmpyCd, String popupScrnNm) {

        // Parameter Clear
        scrnMsg.xxPopPrm_P0.clear();

        // Ctrl Data
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, popupScrnNm);

        // Paramter Set
        Object[] param = new Object[7];
        param[0] = "";
        param[1] = popupScrnNm;
        param[2] = getSelectSQL(scrnMsg, glblCmpyCd);
        param[3] = getSearchConditionSetting(scrnMsg);
        param[4] = getColumnSetting(scrnMsg);
        param[5] = getSortSetting(scrnMsg);
        param[6] = scrnMsg.R;

        return param;
    }

    private static String getSelectSQL(NPAL1400BMsg scrnMsg, String glblCmpyCd) {
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT TM.TECH_TOC_CD").append("\n");
        sb.append("      ,TM.TECH_NM").append("\n");
        sb.append("      ,TM.GLBL_CMPY_CD").append("\n");
        sb.append("      ,TM.EZCANCELFLAG").append("\n");
        sb.append("FROM  TECH_MSTR  TM").append("\n");
        sb.append("     ,S21_PSN    PSN").append("\n");
        sb.append("WHERE TM.GLBL_CMPY_CD  = '#GLBL_CMPY_CD#'").append("\n");
        sb.append("  AND TM.EZCANCELFLAG  = '0'").append("\n");
        sb.append("  AND TM.GLBL_CMPY_CD  = PSN.GLBL_CMPY_CD").append("\n");
        sb.append("  AND TM.TECH_TOC_CD   = PSN.PSN_CD").append("\n");
        sb.append("  AND PSN.EFF_FROM_DT <= '#SALES_DT#'").append("\n");
        sb.append("  AND NVL(PSN.EFF_THRU_DT, '99991231') >= '#SALES_DT#'").append("\n");
        sb.append("  AND PSN.DEL_FLG      = 'N'").append("\n");
        sb.append("  AND PSN.EZCANCELFLAG = '0'").append("\n");

        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", glblCmpyCd);
        sql = sql.replaceAll("#SALES_DT#", ZYPDateUtil.getSalesDate());

        return sql;
    }

    private static List<Object> getSearchConditionSetting(NPAL1400BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {WHERE_DISP_NM_CD_FOR_TECH_CD, WHERE_SQL_NM_CD_FOR_TECH_CD, scrnMsg.techTocCd.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {WHERE_DISP_NM_CD_FOR_TECH_NM, WHERE_SQL_NM_CD_FOR_TECH_NM, scrnMsg.techNm.getValue(), ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    private static List<Object> getColumnSetting(NPAL1400BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {COLUMN_DISP_NM_CD_FOR_TECH_CD, COLUMN_SQL_NM_CD_FOR_TECH_CD, COLUMN_WIDTH_CD_FOR_TECH_CD, ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {COLUMN_DISP_NM_CD_FOR_TECH_NM, COLUMN_SQL_NM_CD_FOR_TECH_NM, COLUMN_WIDTH_CD_FOR_TECH_NM, ZYPConstant.FLG_OFF_N };

        colList.add(colObj1);
        colList.add(colObj2);

        return colList;
    }

    private static List<Object> getSortSetting(NPAL1400BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {SORT_NAME_FOR_TECH_CD, SORT_VAL_FOR_TECH_CD };

        sortList.add(sortObj1);

        return sortList;
    }

}
