/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1230.constant;

/**
 * <pre>
 * Business ID : NPAL1230 ASL Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   CITS            T.Gotoda        Create          N/A
 * 2018/01/12   CITS            S.Katsuma       Update          QC#12226
 * 2023/01/26   Hitachi         S.Dong          Update          QC#60966
 *</pre>
 */
public class NPAL1230Constant {

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
     * Business button SetSupplierName
     */
    public static final String BTN_SET_SUPPLIER_NAME = "SetSupplierName";

    /**
     * Business button SetItemDescription
     */
    public static final String BTN_SET_ITEM_DESCRIPTION = "SetItemDescription";

    /**
     * Business button Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Business button CheckAll
     */
    public static final String BTN_CHECK_ALL = "OnClick_CheckAll";

    /**
     * Business button UnCheck
     */
    public static final String BTN_UNCHECK = "OnClick_UnCheck";

    /**
     * Business button InsertRow
     */
    public static final String BTN_INSERT_ROW = "OnClick_InsertRow";

    /**
     * Business button DeleteRow
     */
    public static final String BTN_DELETE_ROW = "OnClick_DeleteRow";

    /**
     * Business button PagePrev
     */
    public static final String BTN_PAGE_PREV = "PagePrev";

    /**
     * Business button PageNext
     */
    public static final String BTN_PAGE_NEXT = "PageNext";

    /**
     * Business button Item
     */
    public static final String BTN_ITEM = "OpenWin_Item";

    /**
     * Business button Item
     */
    public static final String BTN_SITE = "OpenWin_Supplier";

    /**
     * Business button reference file
     */
    public static final String BTN_REF_FILE = "xxFileData_UP";

    /**
     * Business button Upload
     */
    public static final String BTN_UPLOAD = "OnClick_Upload";

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1230";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1230Scrn00";

    /** Function Inquiry */
    public static final String FUNC_INQUIRY = "NPAL1230T010";

    /** Function Edit */
    public static final String FUNC_EDIT = "NPAL1230T020";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    /**
     * The mode. (Inquiry : 1)
     */
    public static final String MODE_INQUIRY = "1";

    /**
     * The mode. (Update : 2)
     */
    public static final String MODE_UPDATE = "2";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : ASL_NM
     */
    public static final String DISPLAY_NM_ASL_NM = "ASL Name";

    /**
     * Display Name : ASL_START_DT
     */
    public static final String DISPLAY_NM_ASL_START_DT = "ASL Start Date";

    /**
     * Display Name : ASL_END_DT
     */
    public static final String DISPLAY_NM_ASL_END_DT = "ASL End Date";

    /**
     * Display Name : PRNT_VND_CD
     */
    public static final String DISPLAY_NM_PRNT_VND_CD = "Supplier Number";

    /**
     * Display Name : SPLY_NM
     */
    public static final String DISPLAY_NM_PRNT_VND_NM = "Supplier Name";

    /**
     * Display Name : ASL_DESC_TXT
     */
    public static final String DISPLAY_NM_ASL_DESC_TXT = "ASL Description";

    /**
     * Display Name : XX_YES_NO_CD
     */
    public static final String DISPLAY_NM_XX_YES_NO_CD = "Qualifier";

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

    /**
     * Select Item
     */
    public static final String EVENT_NM_SELECT_ITEM = "SelectItem";

    // START 2018/01/12 S.Katsuma [QC#12226,ADD]
    /**
     * Select Item
     */
    public static final String EVENT_NM_OPEN_WIN_ASLNAME = "OpenWin_ASLName";
    // END 2018/01/12 S.Katsuma [QC#12226,ADD]

    // =================================================
    // CSV Upload Download
    // =================================================
    /**
     * CSV file extension
     */
    public static final String FILE_EXTENSION_CSV = ".csv";

    /**
     * CSV template file name
     */
    public static final String TEMPLATE_FILE_NAME = "UploadTemplate";

    /**
     * Max date
     */
    public static final String MAX_DATE = "99991231";

    /**
     * Min date
     */
    public static final String MIN_DATE = "19000101";

    // =================================================
    // Message Code
    // =================================================
    /** Please set at least one search criteria. */
    public static final String NMAM0288E = "NMAM0288E";

    /** Please set at least one search criteria. */
    public static final String NMAM0835E = "NMAM0835E";

    /** For "@", please enter a date earlier than "@" */
    public static final String NLAM0028E = "NLAM0028E";

    /** For Please enter today's or later date. */
    public static final String NPZM0041E = "NPZM0041E";

    /** @ is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** A value of "@" must be greater than 0. */
    public static final String NPAL1498E = "NPAL1498E";

    /** "@" must be integral multiplication of "@". */
    public static final String NPAL1499E = "NPAL1499E";

    /** Base PO Qty cannot be 0 or negative. */
    public static final String NPZM0240E = "NPZM0240E";

    /** Vendor UOM Qty cannot be 0 or negative. */
    public static final String NPZM0241E = "NPZM0241E";

    /** Vendor Incr Ord Qty cannot be 0 or negative. */
    public static final String NPZM0242E = "NPZM0242E";

    /** Vendor Min Ord Qty cannot be 0 or negative. */
    public static final String NPZM0243E = "NPZM0243E";

    /** The field of [@] is not input. */
    public static final String ZZZM9007E = "ZZZM9007E";

    /** The data that was already registered cannot be deleted. */
    public static final String NPAM1516E = "NPAM1516E";

    /** Vendor UOM Qty or Base Qty must be 1 qty. */
    public static final String NPAM1590E = "NPAM1590E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    // START 2023/01/26 S.Dong [QC#60966, ADD]
    /** Vendor Ship Lead Time should be less than Lead Time. */
    public static final String NPAM1656E = "NPAM1656E";
    // END 2023/01/26 S.Dong [QC#60966, ADD]

}
