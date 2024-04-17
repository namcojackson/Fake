/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1350.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID :  NPAL1350 Kitting WO Search
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/20/2016   CITS         Makoto Okigami     Create          N/A
 * 03/14/2018   CITS         K.Fukumura         Update          QC#22324
 *</pre>
 */
public class NPAL1350Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1350";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    // 2018/03/14 Start
    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_PRINT = "70";
    // 2018/03/14 End

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1350Scrn00";

    /** Function Inquiry */
    public static final String FUNC_INQUIRY = "NPAL1350T010";

    /** Function Edit */
    public static final String FUNC_EDIT = "NPAL1350T020";

    // =================================================
    // Business Button Name
    // =================================================
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
     * Business button Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Business button New
     */
    public static final String BTN_NEW = "New_WorkOrderEntry";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : WRK_ORD_NUM
     */
    public static final String DISPLAY_NM_WRK_ORD_NUM = "Work Order Number";

    /**
     * Display Name : DS_WRK_ORD_TP
     */
    public static final String DISPLAY_NM_DS_WRK_ORD_TP = "Work Order Type";

    /**
     * Display Name : DS_WRK_ORD_STS
     */
    public static final String DISPLAY_NM_DS_WRK_ORD_STS = "Work Order Status";

    /**
     * Display Name : WRK_ORD_DT(From)
     */
    public static final String DISPLAY_NM_DS_WRK_ORD_DT_FROM = "Work Order Date (From)";

    /**
     * Display Name : WRK_ORD_DT(To)
     */
    public static final String DISPLAY_NM_DS_WRK_ORD_DT_TO = "Work Order Date (To)";

    /**
     * Display Name : PRNT_MDSE_CD
     */
    public static final String DISPLAY_NM_PRNT_MDSE_CD = "Kit Item Code";

    /**
     * Display Name : MDSE_DESC_SHORT_TXT
     */
    public static final String DISPLAY_NM_MDSE_DESC_SHORT_TXT = "Kit Item Name";

    /**
     * Display Name : RTL_WH_CD
     */
    public static final String DISPLAY_NM_RTL_WH_CD = "Warehouse Code";

    /**
     * Display Name : RTL_WH_NM
     */
    public static final String DISPLAY_NM_RTL_WH_NM = "Warehouse Name";

    /**
     * Display Name : RTL_SWH_CD
     */
    public static final String DISPLAY_NM_RTL_SWH_CD = "Completion Sub WH Code";

    /**
     * Display Name : RTL_SWH_NM
     */
    public static final String DISPLAY_NM_RTL_SWH_NM = "Completion Sub WH Name";

    /**
     * Display Name : RQST_RCV_DT(From)
     */
    public static final String DISPLAY_NM_RQST_RCV_DT_FROM = "Request Comp Date (From)";

    /**
     * Display Name : RQST_RCV_DT(To)
     */
    public static final String DISPLAY_NM_RQST_RCV_DT_TO = "Request Comp Date (To)";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    // =================================================
    // Popup Param
    // =================================================
    /**
     * Param Value for NWAL1130(Kit Item Search)
     */
    public static final String WINDOW_TITLE_KIT_ITEM_SEARCH = "Kit Item Search Popup";

    /**
     * Param Value for NWAL1130(Kit Item Search)
     */
    public static final String WHERE_DISP_NM_FOR_KIT_ITEM_CODE = "Kit Item";

    /**
     * Param Value for NWAL1130(Kit Item Search)
     */
    public static final String WHERE_SQL_NM_FOR_KIT_ITEM_CODE = "FNSH_GOODS_MDSE_CD";

    /**
     * Param Value for NWAL1130(Kit Item Search)
     */
    public static final String WHERE_DISP_NM_FOR_KIT_ITEM_NAME = "Kit Item Description";

    /**
     * Param Value for NWAL1130(Kit Item Search)
     */
    public static final String WHERE_SQL_NM_FOR_KIT_ITEM_NAME = "FNSH_GOODS_MDSE_NM";

    /**
     * Param Value for NWAL1130(Kit Item Search)
     */
    public static final String COLUMN_DISP_NM_FOR_KIT_ITEM_CODE = "Kit Item";

    /**
     * Param Value for NWAL1130(Kit Item Search)
     */
    public static final String COLUMN_SQL_NM_FOR_KIT_ITEM_CODE = "FNSH_GOODS_MDSE_CD";

    /**
     * Param Value for NWAL1130(Kit Item Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_KIT_ITEM_CODE = new BigDecimal(20);

    /**
     * Param Value for NWAL1130(Kit Item Search)
     */
    public static final String COLUMN_DISP_NM_FOR_KIT_ITEM_NAME = "Kit Item Description";

    /**
     * Param Value for NWAL1130(Kit Item Search)
     */
    public static final String COLUMN_SQL_NM_FOR_KIT_ITEM_NAME = "FNSH_GOODS_MDSE_NM";

    /**
     * Param Value for NWAL1130(Kit Item Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_KIT_ITEM_NAME = new BigDecimal(60);

    /**
     * Param Value for NWAL1130(Kit Item Search)
     */
    public static final String SORT_COLUMN_NAME_FOR_KIT_ITEM_CODE = "FNSH_GOODS_MDSE_CD";

    /**
     * Param Value for NWAL1130(Kit Item Search)
     */
    public static final String SORT_CONDITION_FOR_KIT_ITEM_CODE = "ASC";

    /**
     * Param Value for NPAL1010(Kitting Warehouse Search)
     */
    public static final String POPUP_PARAM_LOC_ROLE_TP_CD_LIST = "CSA_WH";

}
