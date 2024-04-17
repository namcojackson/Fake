/**
 *<pre>
 * Copyright (c) 2012 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.servlet.NLCL1060.constant;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;

/**
 * <pre>
 * 3rd Party Onhand Inventory Inquiry Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/15   Hitachi         S.Dong          Create          QC#61398
 *</pre>
 */
public class NLCL1060Constant {

    /** Business ID */
    public static final String MY_BUSINESS_ID = "NLCL1060";

    /** Screen ID */
    public static final String SCREEN_ID = "NLCL1060Scrn00";

    /** Function ID:Search */
    public static final String FUNCTION_ID_SEARCH = "20";

    /** Constant public static final String Value:Blank */
    public static final String STR_BLANK = "";

    /** Search Condition (%)*/
    public static final String STR_SEARCH_PARAM_LIKE = "%";

    /** CMN_CLOSE*/
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /**
     * Common Popup : SCR_NM = Supplier Popup
     */
    public static final String SCR_NM_VND = "Supplier Popup";

    /**
     * Common Popup : TBL_NM = VND
     */
    public static final String TBL_NM_VND = "PO_VND_V";

    /**
     * Common Popup : HDR_CD_LABEL = Supplier Code
     */
    public static final String WHERE_DISP_NM_FOR_SUPPLIER_SITE_CODE = "Supplier Site Code";

    /**
     * Common Popup : SCR_NM = Product Name
     */
    public static final String WHERE_DISP_NM_FOR_SUPPLIER_SITE_NAME = "Supplier Site Name";

    /**
     * Common Popup : DTL_CD_LABEL = Supplier Code
     */
    public static final String COLUMN_DISP_NM_FOR_SUPPLIER_CODE = "Supplier Code";

    /**
     * Common Popup : DTL_CD_LABEL = Supplier Site Code
     */
    public static final String COLUMN_DISP_NM_FOR_SUPPLIER_SITE_CODE = "Supplier Site Code";

    /**
     * Common Popup : DTL_NM_LABEL = Supplier Site Name
     */
    public static final String COLUMN_DISP_NM_FOR_SUPPLIER_SITE_NAME = "Supplier Site Name";

    /**
     * Common Popup : COLUMN_SQL_NM_FOR_SUPPLIER_CODE
     */
    public static final String COLUMN_SQL_NM_FOR_SUPPLIER_CODE = "PRNT_VND_CD";

    /**
     * Common Popup : COLUMN_SQL_NM_FOR_SUPPLIER_SITE_CODE
     */
    public static final String COLUMN_SQL_NM_FOR_SUPPLIER_SITE_CODE = "VND_CD";

    /**
     * Common Popup : COLUMN_SQL_NM_FOR_SUPPLIER_SITE_NAME
     */
    public static final String COLUMN_SQL_NM_FOR_SUPPLIER_SITE_NAME = "DPLY_VND_NM";

    /**
     * Common Popup : SORT_COLUMN_NAME_FOR_SUPPLIER_SITE_CODE = VND_CD
     */
    public static final String SORT_COLUMN_NAME_FOR_SUPPLIER_SITE_CODE = "VND_CD";

    /**
     * Common Popup : SORT_COLUMN_NAME_FOR_SUPPLIER_CODE = PRNT_VND_CD
     */
    public static final String SORT_COLUMN_NAME_FOR_SUPPLIER_CODE = "PRNT_VND_CD";

    /**
     * Common Popup : SORT_CONDITION_FOR_SUPPLIER_CODE = ASC
     */
    public static final String SORT_CONDITION_FOR_SUPPLIER_CODE = "ASC";

    /**
     * Common Popup : SORT_CONDITION_FOR_SUPPLIER_SITE_CODE = ASC
     */
    public static final String SORT_CONDITION_FOR_SUPPLIER_SITE_CODE = "ASC";

    /**
     * Common Popup : COLUMN_WIDTH_FOR_SUPPLIER_SITE_CODE
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_SUPPLIER_SITE_CODE = new BigDecimal(20);

    /**
     * Common Popup : COLUMN_WIDTH_FOR_SUPPLIER_CODE
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_SUPPLIER_CODE = new BigDecimal(20);

    /**
     * Common Popup : COLUMN_WIDTH_FOR_SUPPLIER_SITE_NAME
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_SUPPLIER_SITE_NAME = new BigDecimal(52);

    /** Common PopUp Parameter(for MDSE):TBL_NM */
    public static final String TBL_NM_MDSE = "MDSE";

    /** Common PopUp Parameter(for MDSE):TBL_CD_COLUMN_CD */
    public static final String TBL_CD_COLUMN_CD_MDSE = "MDSE_CD";

    /** Common PopUp Parameter(for MDSE):TBL_CD_COLUMN_NM */
    public static final String TBL_CD_COLUMN_NM_MDSE = "MDSE_NM";

    /** Common PopUp Parameter(for MDSE):TBL_SORT_NUM_COLUMN_NM */
    public static final String TBL_SORT_NUM_COLUMN_NM_MDSE = "MDSE_NM";


    /** Common PopUp Parameter:RGTN_STS_CD */
    public static final String RGTN_STS_CD = RGTN_STS.READY_FOR_ORDER_TAKING;

    /** Common PopUp Parameter:SRCH_FUNC_CD */
    public static final String SRCH_FUNC_CD = "Equal";

    /** Message Kind E */
    public static final String MESSAGE_KIND_E = "E";

    /** NMAL6800 Parameter : Mode Code (All) */
    public static final String ITEM_SEARCH_AL = "AL";

    /**
     * Html color yellow
     */
    public static final String HTML_COLOR_YELLOW = "#ffff00";

    /** Date format */
    public static final String DATE_FMT_PROC_DT = "yyyyMMdd";

    // ---------------------------------------
    // Messages
    // ---------------------------------------
    /** [@] or [@] must be entered. */
    public static final String NLCM0135E = "NLCM0135E";

    /** [@] or [@] must be entered. */
    public static final String ZZM9037E = "ZZM9037E";
    // ---------------------------------------
    // Common button
    // ---------------------------------------
    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "", "" };

    /**
     * Common button 2
     */
    public static final String[] BTN_CMN_BTN_2 = {"btn2", "", "" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "", "" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "", "" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "", "" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "", "" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "", "" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_BTN_9 = {"btn9", "", "" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /**
     * Business button Search
     */
    public static final String[] BTN_SEARCH = {"Search", "Search" };
}
