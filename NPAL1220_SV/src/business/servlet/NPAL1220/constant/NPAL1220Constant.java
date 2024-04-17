/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1220.constant;

/**
 * <pre>
 * Business ID : NPAL1220 ASL Search
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/17/2015   CITS       Takeshi Tokutomi     Create          N/A
 * 01/11/2018   CITS            S.Katsuma       Update          QC#12226
 *</pre>
 */
public class NPAL1220Constant {

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
     * Business button SetSupplierName
     */
    public static final String BTN_SET_SUPPLIER_NAME = "SetSupplierName";

    // START 2018/01/11 S.Katsuma [QC#12226,ADD]
    /**
     * Business button SetSupplierName
     */
    public static final String BTN_SET_SUPPLIER_SITE_NAME = "SetSupplierSiteName";
    // END 2018/01/11 S.Katsuma [QC#12226,ADD]

    /**
     * Business button SetItemDescription
     */
    public static final String BTN_SET_ITEM_DESCRIPTION = "SetItemDescription";

    /**
     * Business button Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Business button Add
     */
    public static final String BTN_ADD = "Add";

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
    public static final String BIZ_APP_ID = "NPAL1220";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1220Scrn00";

    /** Function Inquiry */
    public static final String FUNC_INQUIRY = "NPAL1220T010";

    /** Function Edit */
    public static final String FUNC_EDIT = "NPAL1220T020";

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
     * Display Name : ASL_NM
     */
    public static final String DISPLAY_NM_ASL_NM = "ASL Name";

    /**
     * Display Name : PRNT_VND_CD
     */
    public static final String DISPLAY_NM_PRNT_VND_CD = "Supplier Number";

    /**
     * Display Name : SPLY_NM
     */
    public static final String DISPLAY_NM_PRNT_VND_NM = "Supplier Name";

    /**
     * Display Name : SPLY_ITEM_NUM
     */
    public static final String DISPLAY_NM_SPLY_ITEM_NUM = "Supplier Item Number";

    /**
     * Display Name : MDSE_CD
     */
    public static final String DISPLAY_NM_MDSE_CD = "Item Number";

    /**
     * Display Name : MDSE_NM
     */
    public static final String DISPLAY_NM_MDSE_NM = "Item Description";

    /**
     * Display Name : ASL_QLFY_REF_CD
     */
    public static final String DISPLAY_NM_ASL_QLFY_REF_CD = "Qualifier Reference#";

    // START 2018/01/11 S.Katsuma [QC#12226,ADD]
    /**
     * Display Name : VND_CD
     */
    public static final String DISPLAY_NM_VND_CD = "Supplier Site Number";

    /**
     * Display Name : LOC_NM
     */
    public static final String DISPLAY_NM_LOC_NM = "Supplier Site Name";
    // END 2018/01/11 S.Katsuma [QC#12226,ADD]

    // =================================================
    // Popup Param
    // =================================================
    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_FOR_MDSE = "MDSE";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_CD_COL_NM_FOR_MDSE = "MDSE_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_COL_NM_FOR_MDSE = "MDSE_NM";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_SORT_COL_NM_FOR_MDSE = "MDSE_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String SCR_NM_FOR_MDSE = "Item Search Popup";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_CD_LB_NM_FOR_MDSE = "Item Number";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_NM_LB_NM_FOR_MDSE = "Item Description";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_CD_LB_NM_FOR_MDSE = "Item Number";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_NM_LB_NM_FOR_MDSE = "Item Description";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_FOR_PRNT_VND = "PRNT_VND";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_CD_COL_NM_FOR_PRNT_VND = "PRNT_VND_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_COL_NM_FOR_PRNT_VND = "SPLY_NM";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_SORT_COL_NM_FOR_PRNT_VND = "PRNT_VND_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String SCR_NM_FOR_PRNT_VND = "Supplier Search Popup";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_CD_LB_NM_FOR_PRNT_VND = "Supplier Number";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_NM_LB_NM_FOR_PRNT_VND = "Supplier Name";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_CD_LB_NM_FOR_PRNT_VND = "Supplier Number";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_NM_LB_NM_FOR_PRNT_VND = "Supplier Name";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NMAM0288E:Please set at least one search criteria.
     */
    public static final String NMAM0288E = "NMAM0288E";

    /**
     * ZZZM9007E:The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

}
