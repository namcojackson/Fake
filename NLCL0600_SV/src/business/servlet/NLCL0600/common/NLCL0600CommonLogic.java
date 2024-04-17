/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0600.common;

import static business.servlet.NLCL0600.constant.NLCL0600Constant.BTN_ADD_ALL_SUB_WH;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.BTN_ADD_SPECIFIC_SUB_WH;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.BTN_CMN_BTN_1;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.BTN_CMN_BTN_10;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.BTN_CMN_BTN_2;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.BTN_CMN_BTN_3;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.BTN_CMN_BTN_4;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.BTN_CMN_BTN_5;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.BTN_CMN_BTN_6;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.BTN_CMN_BTN_7;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.BTN_CMN_BTN_8;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.BTN_CMN_BTN_9;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.BTN_DELETE_SUB_WH;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.BTN_SEARCH_RETAIL_SUB_WH_INFO;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.BTN_SEARCH_WH_INFO;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.COLUMN_DISP_NM_FOR_INVTY_LOC_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.COLUMN_DISP_NM_FOR_SUB_WH_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.COLUMN_DISP_NM_FOR_SUB_WH_NAME;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.COLUMN_DISP_NM_FOR_WH_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.COLUMN_DISP_NM_FOR_WH_NAME;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.COLUMN_SQL_NM_FOR_INVTY_LOC_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.COLUMN_SQL_NM_FOR_SUB_WH_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.COLUMN_SQL_NM_FOR_SUB_WH_NAME;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.COLUMN_SQL_NM_FOR_WH_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.COLUMN_SQL_NM_FOR_WH_NAME;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.COLUMN_WIDTH_FOR_INVTY_LOC_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.COLUMN_WIDTH_FOR_SUB_WH_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.COLUMN_WIDTH_FOR_SUB_WH_NAME;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.COLUMN_WIDTH_FOR_WH_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.COLUMN_WIDTH_FOR_WH_NAME;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.DISPLAY_NM_PHYS_INVTY_CTRL_DESC_NM;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.DISPLAY_NM_PHYS_INVTY_DT;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.DISPLAY_NM_PHYS_INVTY_CTRL_NM;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.DISPLAY_NM_RTL_WH_CD;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.DISPLAY_NM_RTL_WH_NM;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.FUNC_EDIT;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.SCRN_ID;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.SORT_COLUMN_NAME_FOR_SUB_WH_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.SORT_COLUMN_NAME_FOR_WH_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.SORT_CONDITION_FOR_SUB_WH_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.SORT_CONDITION_FOR_WH_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.WHERE_DISP_NM_FOR_INVTY_LOC_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.WHERE_DISP_NM_FOR_SUB_WH_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.WHERE_DISP_NM_FOR_SUB_WH_NAME;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.WHERE_DISP_NM_FOR_WH_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.WHERE_DISP_NM_FOR_WH_NAME;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.WHERE_SQL_NM_FOR_INVTY_LOC_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.WHERE_SQL_NM_FOR_SUB_WH_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.WHERE_SQL_NM_FOR_SUB_WH_NAME;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.WHERE_SQL_NM_FOR_WH_CODE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.WHERE_SQL_NM_FOR_WH_NAME;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.WINDOW_TITLE_SUB_WH_SEARCH;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.WINDOW_TITLE_WH_SEARCH;

import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL0600.NLCL0600BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID :  NLCL0600 NLCL0600 PI Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2016   CITS         Makoto Okigami     Create          N/A
 *</pre>
 */
public class NLCL0600CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL0600BMsg
     * @param functionList List<String>
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NLCL0600BMsg scrnMsg, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible

        // Header
        if (ZYPCommonFunc.hasValue(scrnMsg.physInvtyNum)) {
            scrnMsg.rtlWhCd.setInputProtected(true);
            scrnMsg.xxLinkAncr_01.setInputProtected(true);
        } else {
            scrnMsg.rtlWhCd.setInputProtected(false);
            scrnMsg.xxLinkAncr_01.setInputProtected(false);
        }
        scrnMsg.rtlWhNm.setInputProtected(true);
        scrnMsg.physInvtyDt.setInputProtected(false);
        scrnMsg.physInvtyCtrlNm.setInputProtected(false);
        scrnMsg.physInvtyCtrlDescNm.setInputProtected(false);

        // Detail Header Control
        scrnMsg.rtlSwhCd.setInputProtected(false);
        scrnMsg.rtlSwhNm.setInputProtected(true);

        // button activation
        if (ZYPCommonFunc.hasValue(scrnMsg.physInvtyNum)) {
            handler.setButtonEnabled(BTN_SEARCH_WH_INFO, false);
        } else {
            handler.setButtonEnabled(BTN_SEARCH_WH_INFO, true);
        }
        handler.setButtonEnabled(BTN_SEARCH_RETAIL_SUB_WH_INFO, true);
        handler.setButtonEnabled(BTN_ADD_SPECIFIC_SUB_WH, false);
        handler.setButtonEnabled(BTN_ADD_ALL_SUB_WH, false);
        handler.setButtonEnabled(BTN_DELETE_SUB_WH, false);

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
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        setAuthorityProtect(handler, scrnMsg, functionList);
        setTableScreen(scrnMsg);
    }

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL0600BMsg
     * @param functionList List<String>
     */
    private static void setAuthorityProtect(EZDCommonHandler handler, NLCL0600BMsg scrnMsg, List<String> functionList) {

        if (hasRegisterAuthority(functionList)) {
            handler.setButtonEnabled(BTN_ADD_SPECIFIC_SUB_WH, true);
            handler.setButtonEnabled(BTN_ADD_ALL_SUB_WH, true);
            handler.setButtonEnabled(BTN_DELETE_SUB_WH, true);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
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
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg NLCL0600BMsg
     */
    public static void setNameForMessage(NLCL0600BMsg scrnMsg) {

        // Header
        scrnMsg.rtlWhCd.setNameForMessage(DISPLAY_NM_RTL_WH_CD);
        scrnMsg.rtlWhNm.setNameForMessage(DISPLAY_NM_RTL_WH_NM);
        scrnMsg.physInvtyDt.setNameForMessage(DISPLAY_NM_PHYS_INVTY_DT);
        scrnMsg.physInvtyCtrlNm.setNameForMessage(DISPLAY_NM_PHYS_INVTY_CTRL_NM);
        scrnMsg.physInvtyCtrlDescNm.setNameForMessage(DISPLAY_NM_PHYS_INVTY_CTRL_DESC_NM);

    }

    /**
     * Table Column Protect
     * @param scrnMsg NLCL0600BMsg
     */
    public static void setTableScreen(NLCL0600BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).rtlSwhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A1.setInputProtected(true);
        }
    }

    /**
     * Set WH Search Param
     * @param scrnMsg NLCL0600BMsg
     * @param glblCmpyCd String
     * @return Technician Search Parameter
     */
    public static Object[] setWHSearchParam(NLCL0600BMsg scrnMsg, String glblCmpyCd) {

        // Parameter Clear
        ZYPTableUtil.clear(scrnMsg.R);

        // Paramter Set
        Object[] param = new Object[7];
        param[0] = "";
        param[1] = WINDOW_TITLE_WH_SEARCH;
        param[2] = getSelectSQLForWH(scrnMsg, glblCmpyCd);
        param[3] = getSearchConditionForWH(scrnMsg);
        param[4] = getDisplayColumnForWH(scrnMsg);
        param[5] = getSortForWH(scrnMsg);
        param[6] = scrnMsg.R;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm, WINDOW_TITLE_WH_SEARCH);

        return param;

    }

    /**
     * getSelectSQLForWH
     * @param scrnMsg NLCL0600BMsg
     * @param glblCmpyCd String
     * @return Select SQL
     */
    private static String getSelectSQLForWH(NLCL0600BMsg scrnMsg, String glblCmpyCd) {
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT                                                           ").append("\n");
        sb.append("    RW.RTL_WH_CD,                                                ").append("\n");
        sb.append("    RW.RTL_WH_NM,                                                ").append("\n");
        sb.append("    RW.LOC_TP_CD,                                                ").append("\n");
        sb.append("    RW.GLBL_CMPY_CD,                                             ").append("\n");
        sb.append("    RW.EZCANCELFLAG                                              ").append("\n");
        sb.append("FROM                                                             ").append("\n");
        sb.append("    RTL_WH RW                                                    ").append("\n");
        sb.append("WHERE                                                            ").append("\n");
        sb.append("        RW.GLBL_CMPY_CD                     =  '#glblCmpyCd#'    ").append("\n");
        sb.append("    AND RW.EZCANCELFLAG                     =  '0'               ").append("\n");
        sb.append("    AND RW.LOC_TP_CD IN (#whPilocTpCd#)                          ").append("\n");
        sb.append("    AND RW.WH_OWNR_CD IN (#whPiWhOwnrCd#)                        ").append("\n");
        sb.append("    AND RW.EFF_FROM_DT                     <=  '#salesDate#'     ").append("\n");
        sb.append("    AND NVL(RW.EFF_THRU_DT, '99991231')    >=  '#salesDate#'     ").append("\n");

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", glblCmpyCd);
        sql = sql.replaceAll("#salesDate#", ZYPDateUtil.getSalesDate(glblCmpyCd));

        String whPilocTpCd = ZYPCommonFunc.concatString(ZYPCommonFunc.concatString(//
                "'" //
                , "" //
                , scrnMsg.varCharConstVal_01.getValue().replaceAll(",", "','")) //
                , "" //
                , "'");
        sql = sql.replaceAll("#whPilocTpCd#", whPilocTpCd);

        String whPiWhOwnrCd = ZYPCommonFunc.concatString(ZYPCommonFunc.concatString(//
                "'" //
                , "" //
                , scrnMsg.varCharConstVal_02.getValue().replaceAll(",", "','")) //
                , "" //
                , "'");
        sql = sql.replaceAll("#whPiWhOwnrCd#", whPiWhOwnrCd);

        return sql;
    }

    /**
     * getSearchConditionForWH
     * @param scrnMsg NLCL0600BMsg
     * @return Condition SQL
     */
    private static List<Object> getSearchConditionForWH(NLCL0600BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {WHERE_DISP_NM_FOR_WH_CODE, WHERE_SQL_NM_FOR_WH_CODE, scrnMsg.rtlWhCd.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {WHERE_DISP_NM_FOR_WH_NAME, WHERE_SQL_NM_FOR_WH_NAME, scrnMsg.rtlWhNm.getValue(), ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    /**
     * getDisplayColumnForWH
     * @param scrnMsg NLCL0600BMsg
     * @return Column SQL
     */
    private static List<Object> getDisplayColumnForWH(NLCL0600BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {COLUMN_DISP_NM_FOR_WH_CODE, COLUMN_SQL_NM_FOR_WH_CODE, COLUMN_WIDTH_FOR_WH_CODE, ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {COLUMN_DISP_NM_FOR_WH_NAME, COLUMN_SQL_NM_FOR_WH_NAME, COLUMN_WIDTH_FOR_WH_NAME, ZYPConstant.FLG_ON_Y };

        colList.add(colObj1);
        colList.add(colObj2);

        return colList;
    }

    /**
     * getSortForWH
     * @param scrnMsg NLCL0600BMsg
     * @return Sort SQL
     */
    private static List<Object> getSortForWH(NLCL0600BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {SORT_COLUMN_NAME_FOR_WH_CODE, SORT_CONDITION_FOR_WH_CODE };

        sortList.add(sortObj1);

        return sortList;
    }

    /**
     * Set Sub WH Search Param
     * @param scrnMsg NLCL0600BMsg
     * @param glblCmpyCd String
     * @return Technician Search Parameter
     */
    public static Object[] setSubWHSearchParam(NLCL0600BMsg scrnMsg, String glblCmpyCd) {

        // Parameter Clear
        ZYPTableUtil.clear(scrnMsg.R);

        // Paramter Set
        Object[] param = new Object[7];
        param[0] = "";
        param[1] = WINDOW_TITLE_SUB_WH_SEARCH;
        param[2] = getSelectSQLForSubWH(scrnMsg, glblCmpyCd);
        param[3] = getSearchConditionForSubWH(scrnMsg);
        param[4] = getDisplayColumnForSubWH(scrnMsg);
        param[5] = getSortForSubWH(scrnMsg);
        param[6] = scrnMsg.R;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm, WINDOW_TITLE_SUB_WH_SEARCH);

        return param;

    }

    /**
     * getSelectSQLForSubWH
     * @param scrnMsg NLCL0600BMsg
     * @param glblCmpyCd String
     * @return Select SQL
     */
    private static String getSelectSQLForSubWH(NLCL0600BMsg scrnMsg, String glblCmpyCd) {
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT                                                           ").append("\n");
        sb.append("    RS.RTL_SWH_CD,                                               ").append("\n");
        sb.append("    RS.RTL_SWH_NM,                                               ").append("\n");
        sb.append("    RS.INVTY_LOC_CD,                                             ").append("\n");
        sb.append("    RS.GLBL_CMPY_CD,                                             ").append("\n");
        sb.append("    RS.EZCANCELFLAG                                              ").append("\n");
        sb.append("FROM                                                             ").append("\n");
        sb.append("    RTL_SWH RS                                                   ").append("\n");
        sb.append("WHERE                                                            ").append("\n");
        sb.append("        RS.GLBL_CMPY_CD                     =  '#glblCmpyCd#'    ").append("\n");
        sb.append("    AND RS.EZCANCELFLAG                     =  '0'               ").append("\n");
        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {
            sb.append("    AND RS.RTL_WH_CD                        =  '#rtlWhCd#'       ").append("\n");
        }
        sb.append("    AND RS.EFF_FROM_DT                     <=  '#salesDate#'     ").append("\n");
        sb.append("    AND NVL(RS.EFF_THRU_DT, '99991231')    >=  '#salesDate#'     ").append("\n");

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", glblCmpyCd);
        sql = sql.replaceAll("#salesDate#", ZYPDateUtil.getSalesDate(glblCmpyCd));
        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {
            sql = sql.replaceAll("#rtlWhCd#", scrnMsg.rtlWhCd.getValue());
        }

        return sql;
    }

    /**
     * getSearchConditionForSubWH
     * @param scrnMsg NLCL0600BMsg
     * @return Condition SQL
     */
    private static List<Object> getSearchConditionForSubWH(NLCL0600BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {WHERE_DISP_NM_FOR_SUB_WH_CODE, WHERE_SQL_NM_FOR_SUB_WH_CODE, scrnMsg.rtlSwhCd.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {WHERE_DISP_NM_FOR_SUB_WH_NAME, WHERE_SQL_NM_FOR_SUB_WH_NAME, scrnMsg.rtlSwhNm.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj3 = {WHERE_DISP_NM_FOR_INVTY_LOC_CODE, WHERE_SQL_NM_FOR_INVTY_LOC_CODE, "", ZYPConstant.FLG_OFF_N };

        whereList.add(whereObj1);
        whereList.add(whereObj2);
        whereList.add(whereObj3);

        return whereList;
    }

    /**
     * getDisplayColumnForSubWH
     * @param scrnMsg NLCL0600BMsg
     * @return Column SQL
     */
    private static List<Object> getDisplayColumnForSubWH(NLCL0600BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {COLUMN_DISP_NM_FOR_SUB_WH_CODE, COLUMN_SQL_NM_FOR_SUB_WH_CODE, COLUMN_WIDTH_FOR_SUB_WH_CODE, ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {COLUMN_DISP_NM_FOR_SUB_WH_NAME, COLUMN_SQL_NM_FOR_SUB_WH_NAME, COLUMN_WIDTH_FOR_SUB_WH_NAME, ZYPConstant.FLG_OFF_N };
        Object[] colObj3 = {COLUMN_DISP_NM_FOR_INVTY_LOC_CODE, COLUMN_SQL_NM_FOR_INVTY_LOC_CODE, COLUMN_WIDTH_FOR_INVTY_LOC_CODE, ZYPConstant.FLG_OFF_N };

        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);

        return colList;
    }

    /**
     * getSortForSubWH
     * @param scrnMsg NLCL0600BMsg
     * @return Sort SQL
     */
    private static List<Object> getSortForSubWH(NLCL0600BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {SORT_COLUMN_NAME_FOR_SUB_WH_CODE, SORT_CONDITION_FOR_SUB_WH_CODE };

        sortList.add(sortObj1);

        return sortList;
    }

}
