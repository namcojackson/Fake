/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1400.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID : NPAL1400 Reman Inquiry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/08/2015   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */

public class NPAL1400Constant {

    /**
     * USD Decimal Point (e.g. 50.25)
     */
    public static final int DECIMAL_POINT_USD = 2;

    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    public static final String[] BTN_CMN_BTN_2 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_BTN_9 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Return", "Return" };

    /**
     * Business button SaveSearchOption
     */
    public static final String BTN_SAVE_SEARCH = "SaveSearchOption";

    /**
     * Business button DeleteSearchOption
     */
    public static final String BTN_DELETE_SEARCH = "DeleteSearchOption";

    /**
     * Business button Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Business button Add
     */
    public static final String BTN_NEW = "OpenWin_NewRemanWorkbench";

    /**
     * Business button PagePrev
     */
    public static final String BTN_PAGE_PREV = "PagePrev";

    /**
     * Business button PageNext
     */
    public static final String BTN_PAGE_NEXT = "PageNext";

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1400";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1400Scrn00";

    /** Function Inquiry */
    public static final String FUNC_INQUIRY = "NPAL1400T010";

    /** Function Edit */
    public static final String FUNC_EDIT = "NPAL1400T020";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : SRCH_OPT_NM
     */
    public static final String DISPLAY_NM_SRCH_OPT_NM = "Saved Option Name";

    /**
     * Display Name : DISPLAY_NM_RTL_WH_CD
     */
    public static final String DISPLAY_NM_RTL_WH_CD = "Reman Warehouse Code";

    /**
     * Display Name : DISPLAY_NM_RTL_WH_NM
     */
    public static final String DISPLAY_NM_RTL_WH_NM = "Reman Warehouse Name";

    /**
     * Display Name : DISPLAY_NM_RTL_SWH_CD
     */
    public static final String DISPLAY_NM_RTL_SWH_CD = "Reman Sub Warehouse Code";

    /**
     * Display Name : DISPLAY_NM_RTL_SWH_NM
     */
    public static final String DISPLAY_NM_RTL_SWH_NM = "Reman Sub Warehouse Name";

    /**
     * Display Name : DISPLAY_NM_RMNF_ORD_NUM
     */
    public static final String DISPLAY_NM_RMNF_ORD_NUM = "Reman Order #";

    /**
     * Display Name : DISPLAY_NM_RMNF_ORD_STS_CD_SL
     */
    public static final String DISPLAY_NM_RMNF_ORD_STS_CD_SL = "Reman Order Status";

    /**
     * Display Name : DISPLAY_NM_RMNF_START_DT_FR
     */
    public static final String DISPLAY_NM_RMNF_START_DT_FR = "Reman Start Date";

    /**
     * Display Name : DISPLAY_NM_RMNF_START_DT_TO
     */
    public static final String DISPLAY_NM_RMNF_START_DT_TO = "Reman Start Date";

    /**
     * Display Name : DISPLAY_NM_RMNF_MAIN_UNIT_SER_NUM
     */
    public static final String DISPLAY_NM_RMNF_MAIN_UNIT_SER_NUM = "Main Unit Serial";

    /**
     * Display Name : DISPLAY_NM_MKT_MDL_CD
     */
    public static final String DISPLAY_NM_MKT_MDL_CD = "Model";

    /**
     * Display Name : DISPLAY_NM_RMNF_MAIN_UNIT_MDSE_CD
     */
    public static final String DISPLAY_NM_RMNF_MAIN_UNIT_MDSE_CD = "Main Unit Item Code";

    /**
     * Display Name : DISPLAY_NM_MDSE_DESC_SHORT_TXT
     */
    public static final String DISPLAY_NM_MDSE_DESC_SHORT_TXT = "Main Unit Item Name";

    /**
     * Display Name : DISPLAY_NM_TECH_TOC_CD
     */
    public static final String DISPLAY_NM_TECH_TOC_CD = "Reman Tec Owner Code";

    /**
     * Display Name : DISPLAY_NM_TECH_NM
     */
    public static final String DISPLAY_NM_TECH_NM = "Reman Tec Owner Name";

    /**
     * Display Name : DISPLAY_NM_RMNF_END_DT_FR
     */
    public static final String DISPLAY_NM_RMNF_END_DT_FR = "Reman End Date";

    /**
     * Display Name : DISPLAY_NM_RMNF_END_DT_TO
     */
    public static final String DISPLAY_NM_RMNF_END_DT_TO = "Reman End Date";

    // =================================================
    // Popup Param
    // =================================================
    /**
     * Code Column for NPAL1010
     */
    public static final String BLANK_FOR_WH_CD = "";

    /**
     * Item Master Search Popup : Mode Code = 10
     */
    public static final String MODE_CODE_10 = "10";

    /**
     * Common Popup : SCRN_NM_TEC = Technician Search Popup
     */
    public static final String SCRN_NM_TEC = "Technician Search Popup";

    /**
     * Common Popup : WHERE_DISP_NM_CD_FOR_TECH_CD
     */
    public static final String WHERE_DISP_NM_CD_FOR_TECH_CD = "Technician Code";

    /**
     * Common Popup : WHERE_SQL_NM_CD_FOR_TECH_CD
     */
    public static final String WHERE_SQL_NM_CD_FOR_TECH_CD = "TECH_TOC_CD";

    /**
     * Common Popup : COLUMN_DISP_NM_CD_FOR_TECH_CD
     */
    public static final String COLUMN_DISP_NM_CD_FOR_TECH_CD = "Technician Code";

    /**
     * Common Popup : COLUMN_SQL_NM_CD_FOR_TECH_CD
     */
    public static final String COLUMN_SQL_NM_CD_FOR_TECH_CD = "TECH_TOC_CD";

    /**
     * Common Popup : COLUMN_WIDTH_CD_FOR_TECH_CD
     */
    public static final BigDecimal COLUMN_WIDTH_CD_FOR_TECH_CD = new BigDecimal(20);

    /**
     * Common Popup : WHERE_DISP_NM_CD_FOR_TECH_CD
     */
    public static final String WHERE_DISP_NM_CD_FOR_TECH_NM = "Technician Name";

    /**
     * Common Popup : WHERE_SQL_NM_CD_FOR_TECH_NM
     */
    public static final String WHERE_SQL_NM_CD_FOR_TECH_NM = "TECH_NM";

    /**
     * Common Popup : COLUMN_DISP_NM_CD_FOR_TECH_NM
     */
    public static final String COLUMN_DISP_NM_CD_FOR_TECH_NM = "Technician Name";

    /**
     * Common Popup : COLUMN_SQL_NM_CD_FOR_TECH_NM
     */
    public static final String COLUMN_SQL_NM_CD_FOR_TECH_NM = "TECH_NM";

    /**
     * Common Popup : COLUMN_WIDTH_CD_FOR_TECH_NM
     */
    public static final BigDecimal COLUMN_WIDTH_CD_FOR_TECH_NM = new BigDecimal(62);

    /**
     * Common Popup : SORT_NAME_FOR_TECH_CD
     */
    public static final String SORT_NAME_FOR_TECH_CD = "TECH_TOC_CD";

    /**
     * Common Popup : SORT_VAL_FOR_TECH_CD
     */
    public static final String SORT_VAL_FOR_TECH_CD = "ASC";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /**
     * Event Name : OpenWin_Warehouse
     */
    public static final String EVENT_OPEN_WIN_WAREHOUSE = "OpenWin_Warehouse";

    /**
     * Event Name : OpenWin_SubWarehouse
     */
    public static final String EVENT_OPEN_WIN_SUB_WAREHOUSE = "OpenWin_Sub_Warehouse";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NMAM0288E:Please set at least one search criteria.
     */
    public static final String NMAM0288E = "NMAM0288E";

    /** For "@", please enter a date earlier than "@" */
    public static final String NMAM8644E = "NMAM8644E";
}
