/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1030.constant;

/**
 * <pre>
 * Business ID : NLCL1030 Inventory ABC Analysis Search
 * Function Name : Constant
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/21/2016   CITS            T.Hakodate      Create          N/A
 * 01/17/2017   CITS            T.Kikuhara      Update          QC#16256
 *</pre>
 */
public class NLCL1030Constant {

    // *************************************************************************//
    // Display Name
    // *************************************************************************//
    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NLCL1030";

    /** Function Update */
    public static final String FUNCTION_UPDATE = "NLCL1030T020";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NLCL1030Scrn00";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

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

    // *************************************************************************//
    // Display Name
    // *************************************************************************//

    /**
     * Display Name : DISPLAY_SRCH_OPT_PK
     */
    public static final String DISPLAY_SRCH_OPT_PK = "Saved Search Options";

    /**
     * Display Name : SRCH_OPT_NM
     */
    public static final String DISPLAY_NM_SRCH_OPT_NM = "Saved Option Name";

    /**
     * Display Name : DISPLAY_ABC_NM
     */
    public static final String DISPLAY_ABC_NM = "ABC Name";

    /**
     * Display Name : DISPLAY_WH_TP_CD
     */
    public static final String DISPLAY_WH_TP_CD = "Warehouse Type";

    /**
     * Display Name : DISPLAY_WH_CD
     */
    public static final String DISPLAY_WH_CD = "Warehouse Code";

    /**
     * Display Name : DISPLAY_WH_NM
     */
    public static final String DISPLAY_WH_NM = "Warehouse Name";

    /**
     * Display Name : DISPLAY_SWH_CD
     */
    public static final String DISPLAY_SWH_CD = "Sub Warehouse";

    /**
     * Display Name : DISPLAY_CURRENT_PAGE_NUMBER
     */
    public static final String DISPLAY_CURRENT_PAGE_NUMBER = "Current Page Number";

    // *************************************************************************//
    // Event Name
    // *************************************************************************//
    /**
     * Event name : OpenWin_Warehouse.
     */
    public static final String EVENT_NM_NLCL1030SCRN00_OPEN_WIN_WH = "NLCL10300Scrn00_OpenWin_Warehouse";

    /**
     * Event name : OpenWin_SubWarehouse.
     */
    public static final String EVENT_NM_NLCL1030SCRN00_OPEN_WIN_SWH = "NLCL1030Scrn00_OpenWin_SubWarehouse";

    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    // *************************************************************************//
    // Search Key
    // *************************************************************************//

    /**
     * Search Key : Inventory Account Code 01(Inventory)
     */
    public static final String INVTY_ACCT_CD_01 = "01";

    // =================================================
    // value for message kind error
    // =================================================
    /** error */
    public static final String MESSAGE_KIND_ERROR = "E";
}
