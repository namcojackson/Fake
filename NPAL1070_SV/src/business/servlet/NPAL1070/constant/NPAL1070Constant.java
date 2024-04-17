/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1070.constant;

/**
 *<pre>
 * Business ID : NPAL1070 Min-Max Planning Entry
 * Function Name : Constant (SV)
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/24/2016   CITS            Keiichi Masaki  Create          N/A
 * 01/09/2018   CITS            T.Tokutomi      Update          QC#21885
 * 2022/10/05   Hitachi         M.Kikushima     Update          QC#60560
 * 2023/04/17   Hitachi         S.Dong          Update          QC#61348
 *</pre>
 */

public class NPAL1070Constant {

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
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1070";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1070Scrn00";

    /** 
     * Function Inquiry
     */
    public static final String FUNC_INQUIRY = "NPAL1070T010";

    /**
     * Function Edit
     */
    public static final String FUNC_EDIT = "NPAL1070T020";

    // START 2018/12/03 J.Kim [QC#18224,ADD] 
    /**
     * Function Edit
     */
    public static final String FUNC_EDIT_FSM = "NPAL1070T030";
    // END 2018/12/03 J.Kim [QC#18224,ADD] 

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    /**
     * Function Code : Update
     */
    public static final String ITEM_MODE_ALL = "AL";

    /**
     * Business button SetItemDescription
     */
    public static final String BTN_SET_ITEM_DESCRIPTION = "SetItemDescription";

    /**
     * Business button SetWarehouseName
     */
    public static final String BTN_SET_WAREHOUSE_NAME = "SetWarehouseName";

    /**
     * Business button SetSubWarehouseName
     */
    public static final String BTN_SET_SUB_WAREHOUSE_NAME = "SetSubWarehouseName";

    /**
     * Business button SetManagerName
     */
    public static final String BTN_SET_MANAGER_NAME = "SetManagerName";

    /**
     * Business button SetSourceWarehouseName
     */
    public static final String BTN_SET_SOURCE_WAREHOUSE_NAME = "SetSourceWarehouseName";

    /**
     * Business button SetSourceSubWarehouseName
     */
    public static final String BTN_SET_SOURCE_SUB_WAREHOUSE_NAME = "SetSourceSubWarehouseName";

    /**
     * Business button Search
     */
    public static final String BTN_SEARCH = "Search";

    // START 2022/10/05 M.Kikushima [QC#60560,ADD]
    /**
     * Business button ApplyToAll
     */
    public static final String BTN_APPLY_TO_ALL = "ApplyToAll";
    // END 2022/10/05 M.Kikushima [QC#60560,ADD]

    /**
     * Business button Add
     */
    public static final String BTN_ADD = "Add";

    /**
     * Business button CheckAll
     */
    public static final String BTN_CHECK_ALL = "CheckAll";

    /**
     * Business button UncheckAll
     */
    public static final String BTN_UNCHECK_ALL = "UncheckAll";

    /**
     * Business button PagePrev
     */
    public static final String BTN_PAGE_PREV = "PagePrev";

    /**
     * Business button PageNext
     */
    public static final String BTN_PAGE_NEXT = "PageNext";

    /**
     * Business button Detail WH
     */
    public static final String BTN_DETAIL_WH = "OpenWin_SourceWarehouseDetail";

    /**
     * Business button Detail WH Name
     */
    public static final String BTN_DETAIL_WH_NM = "SetSourceWarehouseNameDetail";

    /**
     * Business button DeleteRow
     */
    public static final String BTN_DELETE_ROW = "DeleteRow";

    /**
     * Business button Disable
     */
    public static final String BTN_DISABLE = "Disable";

    /**
     * Business button Import
     */
    public static final String BTN_IMPORT = "Import";

    /**
     * Business button Copy
     */
    public static final String BTN_COPY = "OpneWin_Min_Max_Plan_Copy";

    /**
     * Business Attachments
     */
    public static final String BTN_ATTACHMENTS = "Attachments";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : Min-Max Planning Entry
     */
    public static final String DISPLAY_NM_MIN_MAX_PLN_ENT_NM = "Min-Max Planning Entry";

    /**
    * Display Name : MRP_PLN_NM
    */
    public static final String DISPLAY_NM_MRP_PLN_NM = "Plan Name";

    /**
     * Display Name : MDSE_CD
     */
     public static final String DISPLAY_NM_MDSE_CD = "Item Number";

     /**
      * Display Name : RTL_WH_CATG_CD
      */
      public static final String DISPLAY_NM_RTL_WH_CATG_CD = "WH Category";

    /**
     * Display Name : RTL_WH_CD
     */
    public static final String DISPLAY_NM_RTL_WH_CD = "Warehouse Code";

    /**
     * QC#21885 Add.
     * Display Name : RTL_WH
     */
    public static final String DISPLAY_NM_RTL_WH = "Warehouse";

    /**
     * Display Name : RTL_SWH_CD
     */
    public static final String DISPLAY_NM_RTL_SWH_CD = "Sub Warehouse Code";

    /**
     * Display Name : WH_MGR_PSN_CD
     */
    public static final String DISPLAY_NM_WH_MGR_PSN_CD = "Manager Code";

    /**
     * Display Name : PROCR_TP_CD
     */
    public static final String DISPLAY_NM_PROCR_TP_CD = "Source Type";

    /**
     * Display Name : SRC_RTL_WH_CD
     */
    public static final String DISPLAY_NM_SRC_RTL_WH_CD = "Source Warehouse Code";

    /**
     * QC#21885 Add.
     * Display Name : SRC_RTL_WH
     */
    public static final String DISPLAY_NM_SRC_RTL_WH = "Source Warehouse";

    /**
     * Display Name : SRC_RTL_SWH_CD
     */
    public static final String DISPLAY_NM_SRC_RTL_SWH_CD = "Source Sub Warehouse Code";

    /**
     * Display Name : Source_WH_SWH
     */
    public static final String DISPLAY_SOURCE_WH_SWH = "Source WH / SWH Code";

    /**
     * Display Name : Min Qty
     */
    public static final String DISPLAY_MIN_QTY = "Min Qty";

    /**
     * Display Name : Max Qty
     */
    public static final String DISPLAY_MAX_QTY = "Max Qty";

    /**
     * Display Name : Reorder Qty
     */
    public static final String DISPLAY_REORDER_QTY = "Reorder Qty";

    /**
     * Display Name : Comment
     */
    public static final String DISPLAY_COMMENT = "Comment";

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

    /**
     * Event Name : OpenWin_SourceWarehouse
     */
    public static final String EVENT_OPEN_WIN_SOURCE_WAREHOUSE = "OpenWin_Source_Warehouse";

    /**
     * Event Name : OpenWin_SourceSubWarehouse
     */
    public static final String EVENT_OPEN_WIN_SOURCE_SUB_WAREHOUSE = "OpenWin_Source_Sub_Warehouse";

    /**
     * Event Name : OpenWin_SourceSubWarehouse Detail
     */
    public static final String EVENT_OPEN_WIN_SOURCE_SUB_WAREHOUSE_DETAIL = "OpenWin_SourceWarehouseDetail";

    /**
     * Event Name : ADD
     */
    public static final String EVENT_ADDING_PLAN = "Adding Plan";
    // =================================================
    // Popup Parameter
    // =================================================
    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_FOR_PSN = "S21_PSN_V";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_CD_COL_NM_FOR_PSN = "PSN_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_COL_NM_FOR_PSN = "FULL_PSN_NM";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_SORT_COL_NM_FOR_PSN = "PSN_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String SCR_NM_FOR_PSN = "Manager Popup";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_CD_LB_NM_FOR_PSN = "Person Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_NM_LB_NM_FOR_PSN = "Person Name";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_CD_LB_NM_FOR_PSN = "Person Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_NM_LB_NM_FOR_PSN = "Person Name";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NMAM0288E:Please set at least one search criteria.
     */
    public static final String NMAM0288E = "NMAM0288E";

    /**
     * NPAM0073E:[@] is duplicated.
     */
    public static final String NPAM0073E = "NPAM0073E";

    /**
     * NPAM0016E [@] is "[@]", you don't enter the field.[@]
     */
    public static final String NPAM0016E = "NPAM0016E";

    /**
     * NMAM0835E select at least 1 check box.
     */
    public static final String NMAM0835E = "NMAM0835E";

    /**
     * ZZM9000E:[@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * ZZZM9007E:The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

    /**
     * NMAM0011E:[@] is not registered.
     */
    public static final String NMAM0011E = "NMAM0011E";

    /**
     * QC#21885
     * NPAM1195E:The same value cannot be entered in [@]and [@].
     */
    public static final String NPAM1195E = "NPAM1195E";

    // START 2022/10/05 M.Kikushima [QC#60560,ADD]
    /**
     * NSAM0015E:Please select at least 1 checkbox.
     */
    public static final String NSAM0015E = "NSAM0015E";
    // END 2022/10/05 M.Kikushima [QC#60560,ADD]

    /**
     * NMAM0011E:[@] is not registered.
     */
    public static final String MINMAXPLANNING = "Min-Max Planning";

    /**
     * NPAM1656W:Planning day is blank.
     */
    public static final String NPAM1656W = "NPAM1656W";

    /**
     * NPAM1657W:Planning day is blank. If ok, please click same button again.
     */
    public static final String NPAM1657W = "NPAM1657W";
}
