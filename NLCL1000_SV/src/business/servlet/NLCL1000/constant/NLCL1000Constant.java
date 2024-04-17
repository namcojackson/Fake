/**
 *<pre>
 * Copyright (c) 2012 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.servlet.NLCL1000.constant;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;

/**
 * <pre>
 * Stock Over View Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/07/17   Fujitsu         Y.Mori          Create          N/A
 * 2017/01/17   CITS            T.Kikuhara      Update          QC#16256
 *</pre>
 */
public interface NLCL1000Constant {

    /** Business ID */
    String MY_BUSINESS_ID = "NLCL1000";

    /** Function Update */
    public static final String FUNCTION_UPDATE = "NLCL1000T020";

    /** Screen ID */
    String SCREEN_ID = "NLCL1000Scrn00";

    /** Function ID:Search */
    String FUNCTION_ID_SEARCH = "20";

    /** Constant String Value:Blank */
    String STR_BLANK = "";

    /** Search Condition (%)*/
    String STR_SEARCH_PARAM_LIKE = "%";

    /** Common PopUp Parameter(for MDSE):TBL_NM */
    String TBL_NM_MDSE = "MDSE";

    /** Common PopUp Parameter(for MDSE):TBL_CD_COLUMN_CD */
    String TBL_CD_COLUMN_CD_MDSE = "MDSE_CD";

    /** Common PopUp Parameter(for MDSE):TBL_CD_COLUMN_NM */
    String TBL_CD_COLUMN_NM_MDSE = "MDSE_NM";

    /** Common PopUp Parameter(for MDSE):TBL_SORT_NUM_COLUMN_NM */
    String TBL_SORT_NUM_COLUMN_NM_MDSE = "MDSE_NM";

    /** Common PopUp Parameter(for MDSE):SCR_NM */
    String SCR_NM_MDSE = "Merchandise Inquiry";

    /** Common PopUp Parameter(for MDSE):HDR_CD_LABLE */
    String HDR_CD_LABLE_MDSE = "Merchandise Code";

    /** Common PopUp Parameter(for MDSE):HDR_NM_LABEL */
    String HDR_NM_LABEL_MDSE = "Merchandise Name";

    /** Common PopUp Parameter(for MDSE):DTL_CD_LABEL */
    String DTL_CD_LABEL_MDSE = "Merchandise Code";

    /** Common PopUp Parameter(for MDSE):DTL_NM_LABEL */
    String DTL_NM_LABEL_MDSE = "Merchandise Name";

    /** Common PopUp Parameter(for DS_WH_V):*/
    String TBL_NM_LOC = "DS_WH_V";

    /** Common PopUp Parameter(for DS_WH_V):TBL_CD_COLUMN_CD */
    String TBL_CD_COLUMN_CD_LOC = "INVTY_LOC_CD";

    /** Common PopUp Parameter(for DS_WH_V):TBL_CD_COLUMN_NM */
    String TBL_CD_COLUMN_NM_LOC = "INVTY_LOC_NM";

    /** Common PopUp Parameter(for DS_WH_V):TBL_SORT_NUM_COLUMN_NM */
    String TBL_SORT_NUM_COLUMN_NM_LOC = "INVTY_LOC_NM";

    /** Common PopUp Parameter(for DS_WH_V):SCR_NM */
    String SCR_NM_LOC = "Location Inquiry";

    /** Common PopUp Parameter(for DS_WH_V):HDR_CD_LABLE */
    String HDR_CD_LABLE_LOC = "Location Code";

    /** Common PopUp Parameter(for DS_WH_V):HDR_NM_LABEL */
    String HDR_NM_LABEL_LOC = "Location Name";

    /** Common PopUp Parameter(for DS_WH_V):DTL_CD_LABEL */
    String DTL_CD_LABEL_LOC = "Location Code";

    /** Common PopUp Parameter(for DS_WH_V):DTL_NM_LABEL */
    String DTL_NM_LABEL_LOC = "Location Name";

    /** Common PopUp Parameter:RGTN_STS_CD */
    String RGTN_STS_CD = RGTN_STS.READY_FOR_ORDER_TAKING;

    /** Common PopUp Parameter:SRCH_FUNC_CD */
    String SRCH_FUNC_CD = "Equal";

    /** Input check error code */
    int ERROR_CD_ERR = 1;

    /** Message Kind E */
    String MESSAGE_KIND_E = "E";

    /**
     * Html color yellow
     */
    String HTML_COLOR_YELLOW= "#ffff00";

    // ---------------------------------------
    // Screen Item Initial Value
    // ---------------------------------------
    /** Initial Value:Internal External radio button */
    BigDecimal XX_RADIO_BTN_IX_BOTH = new BigDecimal("3");

    /** Date format */
    String DATE_FMT_PROC_DT = "yyyyMMdd";

    // ---------------------------------------
    // Messages
    // ---------------------------------------
    /** Enter MDSE CD to search. */
    String NLCM0104E = "NLCM0104E";

    /** Select either WH or Tech. */
    String NLCM0105E = "NLCM0105E";

    /** Enter a parameter in "Need to Quantity" to search. */
    String NLCM0106E = "NLCM0106E";

    /** The parameter in "Need to Quantity" is invalid. Enter 1 or greater. */
    String NLCM0107E = "NLCM0107E";

    /** Please check at least 1 checkbox. */
    String NWAM0186E = "NWAM0186E";

    // ---------------------------------------
    // Common button
    // ---------------------------------------
    /**
     * Common button 1
     */
    String[] BTN_CMN_BTN_1 = {"btn1", "", "" };

    /**
     * Common button 2
     */
    String[] BTN_CMN_BTN_2 = {"btn2", "", "" };

    /**
     * Common button 3
     */
    String[] BTN_CMN_BTN_3 = {"btn3", "", "" };

    /**
     * Common button 4
     */
    String[] BTN_CMN_BTN_4 = {"btn4", "", "" };

    /**
     * Common button 5
     */
    String[] BTN_CMN_BTN_5 = {"btn5", "", "" };

    /**
     * Common button 6
     */
    String[] BTN_CMN_BTN_6 = {"btn6", "", "" };

    /**
     * Common button 7
     */
    String[] BTN_CMN_BTN_7 = {"btn7", "", "" };

    /**
     * Common button 8
     */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    String[] BTN_CMN_BTN_9 = {"btn9", "", "" };

    /**
     * Common button 10
     */
    String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /**
     * Business button Search
     */
    String[] BTN_SEARCH = {"Search", "Search" };
}
