/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID :  NPBL0010 Inventory Request List
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2016   CITS            Makoto Okigami  Create          N/A
 * 06/13/2016   CSAI            D.Fukaya        Update          QC#9046
 * 06/15/2016   CSAI            D.Fukaya        Update          QC#9297
 * 08/03/2016   CITS            K.Ogino         Update          QC#12517
 + 08/24/2016   CITS            K.Ogino         Update          QC#9058
 * 12/20/2016   CITS            K.Ogino         Update          QC#15815
 * 01/10/2017   CITS            K.Ogino         Update          QC#16296
 * 08/08/2017   CITS            Y.Iwasaki       Update          QC#20118
 * 08/23/2017   CITS            H.Naoi          Update          Sol#097(QC#18398)
 * 11/07/2017   CITS            S.Katsuma       Update          SOL#014(QC#18401)
 * 05/18/2020   CITS            K.Ogino         Update          QC#56867
 *</pre>
 */
public class NPBL0020Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPBL0020";

    /**
     * Reference function code
     */
    public static final String FUNCTION_UPDATE = "NPBL0020T020";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPBL0020Scrn00";

    /**
     * Table Name
     */
    public static final String TABLE_ID = "A";

    /** Function Inquiry */
    public static final String FUNC_INQUIRY = "NPBL0020T010";

    /** Function Edit */
    public static final String FUNC_EDIT = "NPBL0020T020";

    /**
     * Tab Header
     */
    public static final String TAB_HEADER = "Header";

    /**
     * Tab Detail
     */
    public static final String TAB_DETAIL = "Detail";

    /**
     * Config Line Sub Number
     */
    public static final int CONFIG_LINE_SUB_NUMBER = 0;

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
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "CMN_APPLY", "Apply" };

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

    /** Function Button 11 */
    public static final String[] BTN_ADD_LINE = {"btn11", "Add_NewLine", "Add Line" };

    /**
     * Business button Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Business button Copy
     */
    public static final String BTN_COPY = "Copy";

    /**
     * Business button Cancel
     */
    public static final String BTN_HEADER_CANCEL = "HeaderCancel";

    /**
     * Business button Apprvl Hist
     */
    public static final String BTN_APPRVL_HIST = "MoveTo_ApprovalHist";

    /**
     * Business button Add New Line
     */
    public static final String BTN_ADD_NEW_LINE = "Add_NewLine";

    /**
     * Business button Add New Config
     */
    public static final String BTN_ADD_NEW_CONFIG = "Add_NewConfig";

    /**
     * Business button Add
     */
    public static final String BTN_ADD_EXISTING_CONFIG = "Add_ExistingConfig";

    /**
     * Business button Select All
     */
    public static final String BTN_SELECT_ALL = "SelectAll";

    /**
     * Business button Un Select All
     */
    public static final String BTN_UN_SELECT_ALL = "UnSelectAll";

    /**
     * Business button Kitting
     */
    public static final String BTN_KITTING = "MoveTo_KittingWOEntry";

    /**
     * Business button Line Cancel
     */
    public static final String BTN_LINE_CANCEL = "LineCancel";

    /**
     * Business button On Hand Inv
     */
    public static final String BTN_ON_HAND_INV = "MoveTo_OnHandInv";

    /**
     * Business button Import
     */
    public static final String BTN_IMPORT = "Upload";

    /**
     * Business button Item# Search
     */
    public static final String BTN_ITEM = "OpenWin_Item";

    /**
     * Business button GetItemInfo
     */
    public static final String BTN_ITEM_INFO = "GetItemInfo";

    /**
     * Business button Source WH Search
     */
    public static final String BTN_SRC_WH = "OpenWin_SrcWHForLine";

    /**
     * Business button Dest WH Search
     */
    public static final String BTN_DEST_WH = "OpenWin_DestWHForLine";

    /**
     * Event Name OpenWin_ShipToCustH
     */
    public static final String OPEN_WIN_SHIP_TO_CUST_H = "OpenWin_ShipToCustH";

    /**
     * Event Name OpenWin_ShipToCustD
     */
    public static final String OPEN_WIN_SHIP_TO_CUST_D = "OpenWin_ShipToCustD";

    /**
     * Business button Ship to Customer Name
     */
    public static final String BTN_SHIP_TO_CUST = "OpenWin_ShipToCustD";

    /**
     * Business button Chage Account
     */
    public static final String BTN_ACCOUNT = "OpenWin_Account";

    /**
     * 
     */
    public static final String BTN_SERIAL = "OpenWin_SerEnt";

    /**
     * Business Attachments
     */
    public static final String BTN_ATTACHMENTS = "Attachments";

    /**
     * Business Attachments
     */
    public static final String BTN_HEADER_CLOSE = "HeaderClose";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : PRCH_REQ_NUM
     */
    public static final String DISPLAY_NM_PRCH_REQ_NUM = "Request Number";

    /**
     * Display Name : PRCH_REQ_TP_DESC_TXT
     */
    public static final String DISPLAY_NM_PRCH_REQ_TP_DESC_TXT = "Reqeust Type";

    /**
     * Display Name : PRCH_REQ_STS_DESC_TXT
     */
    public static final String DISPLAY_NM_PRCH_REQ_STS_DESC_TXT = "Header Status";

    /**
     * Display Name : PRCH_REQ_APVL_STS_DESC_TXT
     */
    public static final String DISPLAY_NM_PRCH_REQ_APVL_STS_DESC_TXT = "Approval Status";

    /**
     * Display Name : PRCH_REQ_CRAT_DT
     */
    public static final String DISPLAY_NM_PRCH_REQ_CRAT_DT = "Date Created";

    /**
     * Display Name : RQST_RCV_DT
     */
    public static final String DISPLAY_NM_RQST_RCV_DT = "Date Needed";

    /**
     * Display Name : PRCH_REQ_SRC_TP_DESC_TXT
     */
    public static final String DISPLAY_NM_PRCH_REQ_SRC_TP_DESC_TXT = "Document Source Type";

    /**
     * Display Name : TRX_REF_NUM
     */
    public static final String DISPLAY_NM_TRX_REF_NUM = "Source Doc#";

    //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) START
    /**
     * Display Name : MRP_PLN_NM
     */
    public static final String DISPLAY_NM_MRP_PLN_NM = "Plan Name";
    //08/09/2017 CITS H.Naoi Add Sol#097(QC#18398) END

    /**
     * Display Name : FULL_PSN_NM
     */
    public static final String DISPLAY_NM_FULL_PSN_NM = "Requester";

    /**
     * Display Name : SHPG_SVC_LVL_DESC_TXT
     */
    public static final String DISPLAY_NM_SHPG_SVC_LVL_DESC_TXT = "Service Level";

    /**
     * Display Name : CARR_NM
     */
    public static final String DISPLAY_NM_CARR_NM = "Carrier";

    /**
     * Display Name : CTAC_PSN_NM
     */
    public static final String DISPLAY_NM_CTAC_PSN_NM = "Attention";

    /**
     * Display Name : PRCH_REQ_CMNT_TXT
     */
    public static final String DISPLAY_NM_PRCH_REQ_CMNT_TXT = "Description";

    /**
     * Display Name : SRC_RTL_WH_CD
     */
    public static final String DISPLAY_NM_SRC_RTL_WH_CD = "Source WH";

    /**
     * Display Name : RTL_WH_NM_SW
     */
    public static final String DISPLAY_NM_RTL_WH_NM_SW = "Source WH Name";

    /**
     * Display Name : SRC_RTL_SWH_CD
     */
    public static final String DISPLAY_NM_SRC_RTL_SWH_CD = "Source SWH";

    /**
     * Display Name : RTL_SWH_NM_SS
     */
    public static final String DISPLAY_NM_RTL_SWH_NM_SS = "Source SWH Name";

    /**
     * Display Name : DEST_RTL_WH_CD
     */
    public static final String DISPLAY_NM_DEST_RTL_WH_CD = "Destination WH";

    /**
     * Display Name : RTL_WH_NM_DW
     */
    public static final String DISPLAY_NM_RTL_WH_NM_DW = "Destination WH Name";

    /**
     * Display Name : DEST_RTL_SWH_CD
     */
    public static final String DISPLAY_NM_DEST_RTL_SWH_CD = "Destination SWH";

    /**
     * Display Name : RTL_SWH_NM_DS
     */
    public static final String DISPLAY_NM_RTL_SWH_NM_DS = "Destination SWH Name";

    /**
     * Display Name : VND_CD
     */
    public static final String DISPLAY_NM_VND_CD = "Ship To Supplier Code";

    /**
     * Display Name : DPLY_VND_NM
     */
    public static final String DISPLAY_NM_DPLY_VND_NM = "Ship To Supplier Name";

    /**
     * Display Name : SHIP_TO_CUST_CD
     */
    public static final String DISPLAY_NM_SHIP_TO_CUST_CD = "Ship To Customer Code";

    /**
     * Display Name : SHIP_TO_LOC_NM
     */
    public static final String DISPLAY_NM_SHIP_TO_LOC_NM = "Ship To Customer Name";

    /**
     * Display Name : SVC_CONFIG_MSTR_PK
     */
    public static final String DISPLAY_NM_SVC_CONFIG_MSTR_PK = "Config#";

    /**
     * Display Name : PRCH_REQ_LINE_TP_DESC_TXT
     */
    public static final String DISPLAY_NM_PRCH_REQ_LINE_TP_DESC_TXT = "Line Type";

    /**
     * Display Name : MDSE_CD
     */
    public static final String DISPLAY_NM_MDSE_CD = "Item#";

    /**
     * Display Name : PRCH_REQ_DISP_QTY
     */
    public static final String DISPLAY_NM_PRCH_REQ_DISP_QTY = "Transfer Qty";

    /**
     * Display Name : SRC_RTL_WH_CD_LINE
     */
    public static final String DISPLAY_NM_SRC_RTL_WH_CD_LINE = "Source WH Code";

    /**
     * Display Name : RTL_WH_NM_LINE_SW
     */
    public static final String DISPLAY_NM_RTL_WH_NM_LINE_SW = "Source WH";

    /**
     * Display Name : SRC_RTL_SWH_CD
     */
    public static final String DISPLAY_NM_SRC_RTL_SWH_CD_LINE = "Source SWH";

    /**
     * Display Name : DEST_RTL_WH_CD_LINE
     */
    public static final String DISPLAY_NM_DEST_RTL_WH_CD_LINE = "Dest WH Code";

    /**
     * Display Name : RTL_WH_NM_LINE_DW
     */
    public static final String DISPLAY_NM_RTL_WH_NM_LINE_DW = "Dest WH";

    /**
     * Display Name : DEST_RTL_SWH_CD
     */
    public static final String DISPLAY_NM_DEST_RTL_SWH_CD_LINE = "Dest SWH";

    /**
     * Display Name : VND_RTRN_RSN_DESC_TXT
     */
    public static final String DISPLAY_NM_VND_RTRN_RSN_DESC_TXT = "Return Reason";

    /**
     * Display Name : Upload File
     */
    public static final String DISPLAY_NM_UPLOAD_FILE = "Upload File";

    // =================================================
    // Screen Control Value
    // =================================================
    /**
     * Screen Control Value: Multiple
     */
    public static final String SCREEN_CTRL_VALUE_MULTIPLE = "MULTIPLE";

    /**
     * Screen Control Value: Serial# Delimiter
     */
    public static final String SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM = ",";

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
     * Param Value for NMAL6050
     */
    public static final String TBL_NM_FOR_S21_PSN_V = "S21_PSN_V";

    /**
     * Param Value for NMAL6050
     */
    public static final String TBL_CD_COL_NM_FOR_S21_PSN_V = "PSN_CD";

    /**
     * Param Value for NMAL6050
     */
    public static final String TBL_NM_COL_NM_FOR_S21_PSN_V = "FULL_PSN_NM";

    /**
     * Param Value for NMAL6050
     */
    public static final String TBL_SORT_COL_NM_FOR_S21_PSN_V = "PSN_CD";

    /**
     * Param Value for NMAL6050
     */
    public static final String SCR_NM_FOR_S21_PSN_V = "Requester Search Popup";

    /**
     * Param Value for NMAL6050
     */
    public static final String HDR_CD_LB_NM_FOR_S21_PSN_V = "Person Code";

    /**
     * Param Value for NMAL6050
     */
    public static final String HDR_NM_LB_NM_FOR_S21_PSN_V = "Person Name";

    /**
     * Param Value for NMAL6050
     */
    public static final String DTL_CD_LB_NM_FOR_S21_PSN_V = "Person Code";

    /**
     * Param Value for NMAL6050
     */
    public static final String DTL_NM_LB_NM_FOR_S21_PSN_V = "Person Name";

    /**
     * Param Value for NMAL6050
     */
    public static final String TBL_NM_FOR_OTBD_CARR_V = "OTBD_CARR_V";

    /**
     * Param Value for NMAL6050
     */
    public static final String TBL_CD_COL_NM_FOR_OTBD_CARR_V = "CARR_CD";

    /**
     * Param Value for NMAL6050
     */
    public static final String TBL_NM_COL_NM_FOR_OTBD_CARR_V = "CARR_NM";

    /**
     * Param Value for NMAL6050
     */
    public static final String TBL_SORT_COL_NM_FOR_OTBD_CARR_V = "CARR_CD";

    /**
     * Param Value for NMAL6050
     */
    public static final String SCR_NM_FOR_OTBD_CARR_V = "Carrier Search Popup";

    /**
     * Param Value for NMAL6050
     */
    public static final String HDR_CD_LB_NM_FOR_OTBD_CARR_V = "Carrier Code";

    /**
     * Param Value for NMAL6050
     */
    public static final String HDR_NM_LB_NM_FOR_OTBD_CARR_V = "Carrier Name";

    /**
     * Param Value for NMAL6050
     */
    public static final String DTL_CD_LB_NM_FOR_OTBD_CARR_V = "Carrier Code";

    /**
     * Param Value for NMAL6050
     */
    public static final String DTL_NM_LB_NM_FOR_OTBD_CARR_V = "Carrier Name";

    /**
     * Param Value for NPAL1010
     */
    public static final String SOURCE_WAREHOUSE = "0";

    /**
     * Param Value for NPAL1010
     */
    public static final String SOURCE_SUB_WAREHOUSE = "1";

    /**
     * Param Value for NPAL1010
     */
    public static final String DESTINATION_WAREHOUSE = "2";

    /**
     * Param Value for NPAL1010
     */
    public static final String DESTINATION_SUB_WAREHOUSE = "3";

    /**
     * Param Value for NPAL1010
     */
    public static final String SOURCE_WAREHOUSE_FOR_LINE = "4";

    /**
     * Param Value for NPAL1010
     */
    public static final String DESTINATION_WAREHOUSE_FOR_LINE = "5";

    /**
     * Param Value for NPAL1010
     */
    public static final String SOURCE_SUB_WAREHOUSE_FOR_LINE = "6";

    /**
     * Param Value for NPAL1010
     */
    public static final String DESTINATION_SUB_WAREHOUSE_FOR_LINE = "7";

    /**
     * Param Value for NWAL1130
     */
    public static final String WINDOW_TITLE_SUPPLIER_SEARCH = "Ship To Supplier Search";

    /**
     * Param Value for NWAL1130
     */
    public static final String SELECT_TABLE_NAME_FOR_SUPPLIER_SEARCH = "PO_VND_V";

    /**
     * Param Value for NWAL1130
     */
    public static final String WHERE_DISP_NM_FOR_SUPPLIER_SITE_CODE = "Supplier Site Code";

    /**
     * Param Value for NWAL1130
     */
    public static final String WHERE_SQL_NM_FOR_SUPPLIER_SITE_CODE = "VND_CD";

    /**
     * Param Value for NWAL1130
     */
    public static final String WHERE_DISP_NM_FOR_SUPPLIER_SITE_NAME = "Supplier Site Name";

    /**
     * Param Value for NWAL1130
     */
    public static final String WHERE_SQL_NM_FOR_SUPPLIER_SITE_NAME = "DPLY_VND_NM";

    /**
     * Param Value for NWAL1130
     */
    public static final String COLUMN_DISP_NM_FOR_SUPPLIER_CODE = "Supplier Code";

    /**
     * Param Value for NWAL1130
     */
    public static final String COLUMN_SQL_NM_FOR_SUPPLIER_CODE = "PRNT_VND_CD";

    /**
     * Param Value for NWAL1130
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_SUPPLIER_CODE = new BigDecimal(20);

    /**
     * Param Value for NWAL1130
     */
    public static final String COLUMN_DISP_NM_FOR_SUPPLIER_SITE_CODE = "Supplier Site Code";

    /**
     * Param Value for NWAL1130
     */
    public static final String COLUMN_SQL_NM_FOR_SUPPLIER_SITE_CODE = "VND_CD";

    /**
     * Param Value for NWAL1130
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_SUPPLIER_SITE_CODE = new BigDecimal(20);

    /**
     * Param Value for NWAL1130
     */
    public static final String COLUMN_DISP_NM_FOR_SUPPLIER_SITE_NAME = "Supplier Site Name";

    /**
     * Param Value for NWAL1130
     */
    public static final String COLUMN_SQL_NM_FOR_SUPPLIER_SITE_NAME = "DPLY_VND_NM";

    /**
     * Param Value for NWAL1130
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_SUPPLIER_SITE_NAME = new BigDecimal(52);

    /**
     * Param Value for NWAL1130
     */
    public static final String SORT_COLUMN_NAME_FOR_SUPPLIER_CODE = "PRNT_VND_CD";

    /**
     * Param Value for NWAL1130
     */
    public static final String SORT_CONDITION_FOR_SUPPLIER_CODE = "ASC";

    /**
     * Param Value for NWAL1130
     */
    public static final String SORT_COLUMN_NAME_FOR_SUPPLIER_SITE_CODE = "VND_CD";

    /**
     * Param Value for NWAL1130
     */
    public static final String SORT_CONDITION_FOR_SUPPLIER_SITE_CODE = "ASC";

    /**
     * Param Value for NSAL1240
     */
    public static final String PARM_CONFIG_EXST_MODE_CD_02 = "02";

    /**
     * Param Value for NSAL1240
     */
    public static final String PARM_MACH_ALLOC_MODE_CODE_03 = "03";

    /**
     * Param Value for NMAL6800
     */
    public static final String PARM_NMAL6800_MODE_CODE_10 = "10";

    /**
     * Param Value for NPAL1360
     */
    public static final String PARM_DS_WO_TYPE_1 = "1";

    /**
     * Param Value for NLBL3000
     */
    public static final String SCR_EDT_TP_CD_ENTRY = "1";

    /**
     * Param Value for NLBL3000
     */
    public static final String SCR_EDT_TP_CD_READ = "2";

    /**
     * Param Value for NLBL3000
     */
    public static final String PRAM_NLBL3000_SUFFIX = "T1";

    /**
     * Param Value for Upload
     */
    public static final String PRAM_UPLOAD_FILE_EXTENSION_CSV = ".csv";

    /**
     * Param Value for Upload
     */
    public static final String PRAM_UPLOAD_FILE_EXTENSION_TXT = ".txt";

    /**
     * Upload Template File Name
     */
    public static final String CSV_FILE_NAME = "InventoryRequestEntry_UploadTemplate";

    /**
     * Upload Template File Extesion
     */
    public static final String TMPL_FILE_EXTENSION = ".csv";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NAMM0027E:Please Enter @.
     */
    public static final String NAMM0027E = "NAMM0027E";

    /**
     * NPAM0009E:Please fill out/select the required field.
     */
    public static final String NPAM0009E = "NPAM0009E";

    /**
     * ZZZM9025E: [@] is mandatory.
     */
    public static final String ZZZM9025E = "ZZZM9025E";

    /**
     * NPAM1360E:Details must have at least one row. Please enter
     * data.
     */
    public static final String NPAM1360E = "NPAM1360E";

    /**
     * NPAM0079E:Please enter today's or later date.
     */
    public static final String NPAM0079E = "NPAM0079E";

    /**
     * NPAM1239E: Select only one detail to process.
     */
    public static final String NPAM1239E = "NPAM1239E";

    /**
     * NPAM0049E: The details of the process target have not been
     * selected.
     */
    public static final String NPAM0049E = "NPAM0049E";

    /**
     * NPAM0038I: To enter more than 200 + Serial Numbers, contact the
     * system administrator.
     */
    public static final String NPAM0038I = "NPAM0038I";

    /**
     * ZZM9000E:
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * NWAM0682E: Selected line cannot process.
     */
    public static final String NWAM0682E = "NWAM0682E";

    /**
     * Multiple details to process are selected.
     */
    public static final String NPAM1215E = "NPAM1215E";

    /**
     * No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * Can not delete only the configuration item line. Please include
     * the Config line.
     */
    public static final String NPBM0009E = "NPBM0009E";

    /**
     * "Line Cancel" the process can not be. It is possible only when
     * the pre-approval request.
     */
    public static final String NPBM0011E = "NPBM0011E";

    /**
     * Selected Line has been canceled or closed.
     */
    public static final String NPBM0013I = "NPBM0013I";

    // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
    /**
     * NPAM1239E: [@] is mandatory value.@
     */
    public static final String NPAM1329E = "NPAM1329E";
    // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]

    // =================================================
    // index
    // =================================================
    /**
     * IDX_0
     */
    public static final int IDX_0 = 0;

    /**
     * IDX_1
     */
    public static final int IDX_1 = 1;

    /**
     * IDX_2
     */
    public static final int IDX_2 = 2;

    /**
     * IDX_3
     */
    public static final int IDX_3 = 3;

    /**
     * IDX_4
     */
    public static final int IDX_4 = 4;

    /**
     * IDX_5
     */
    public static final int IDX_5 = 5;

    /**
     * IDX_6
     */
    public static final int IDX_6 = 6;

    /**
     * IDX_7
     */
    public static final int IDX_7 = 7;

    /**
     * IDX_8
     */
    public static final int IDX_8 = 8;

    /**
     * IDX_9
     */
    public static final int IDX_9 = 9;

    /**
     * IDX_10
     */
    public static final int IDX_10 = 10;

    /**
     * IDX_11
     */
    public static final int IDX_11 = 11;

    /**
     * IDX_12
     */
    public static final int IDX_12 = 12;

    /**
     * IDX_13
     */
    public static final int IDX_13 = 13;

    /**
     * IDX_14
     */
    public static final int IDX_14 = 14;

    /**
     * IDX_15
     */
    public static final int IDX_15 = 15;

    /**
     * IDX_16
     */
    public static final int IDX_16 = 16;

    /**
     * IDX_17
     */
    public static final int IDX_17 = 17;

    /**
     * IDX_18
     */
    public static final int IDX_18 = 18;

    /**
     * IDX_19
     */
    public static final int IDX_19 = 19;

    /**
     * IDX_20
     */
    public static final int IDX_20 = 20;

    /**
     * IDX_21
     */
    public static final int IDX_21 = 21;

    /**
     * IDX_22
     */
    public static final int IDX_22 = 22;

    /**
     * IDX_23
     */
    public static final int IDX_23 = 23;

    /**
     * IDX_24
     */
    public static final int IDX_24 = 24;

    /**
     * IDX_25
     */
    public static final int IDX_25 = 25;

    /**
     * IDX_26
     */
    public static final int IDX_26 = 26;

    /**
     * IDX_27
     */
    public static final int IDX_27 = 17;

    /**
     * IDX_28
     */
    public static final int IDX_28 = 28;

    /**
     * IDX_29
     */
    public static final int IDX_29 = 29;

    /**
     * IDX_30
     */
    public static final int IDX_30 = 30;

    /**
     * IDX_31
     */
    public static final int IDX_31 = 31;

    /**
     * IDX_32
     */
    public static final int IDX_32 = 32;

    /**
     * IDX_60
     */
    public static final int IDX_60 = 60;

    /**
     * IDX_200
     */
    public static final int IDX_200 = 200;

    /**
     * Ship To's Only.
     */
    public static final String SHIP_TO_ONLY_CD = "03";

    /**
     * The request will be closed. OK?
     */
    public static final String NPBM0012I = "NPBM0012I";

    /**
     * Business button >> Get_SrcWhH
     */
    public static final String BTN_GET_SRC_WH_H = "Get_SrcWhH";

    /**
     * Business button >> Get_SrcSwhH
     */
    public static final String BTN_GET_SRC_SWH_H = "Get_SrcSwhH";

    /**
     * Business button >> Get_DestWhH
     */
    public static final String BTN_GET_DEST_WH_H = "Get_DestWhH";

    /**
     * Business button >> Get_DestSwhH
     */
    public static final String BTN_GET_DEST_SWH_H = "Get_DestSwhH";

    /**
     * Business button >> Get_ShipToSplyH
     */
    public static final String BTN_GET_SHIP_TO_SPLY_H = "Get_ShipToSplyH";

    /**
     * Business button >> Get_ShipToCusthH
     */
    public static final String BTN_GET_SHIP_TO_CUST_H = "Get_ShipToCustH";

    /**
     * Business button >> Get_ShipToCusthH
     */
    public static final String BTN_OPEN_WIN_SRC_SWH = "OpenWin_SrcSWH";

    /**
     * Business button >> Get_ShipToCusthH
     */
    public static final String BTN_OPEN_WIN_DEST_SWH = "OpenWin_DestSWH";

    // QC#56867 Add
    /**
     * Business button Line Close
     */
    public static final String BTN_LINE_CLOSE = "LineClose";
}
