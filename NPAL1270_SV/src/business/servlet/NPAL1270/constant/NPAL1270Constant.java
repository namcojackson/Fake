/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1270.constant;

/**
 * <pre>
 * Business ID : NPAL1270 PO Requisition List
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/08/2016   CITS            R Shimamoto     Create          N/A
 * 01/24/2017   CITS            T.Kikuhara      Update          QC#10621
 * 08/10/2017   CITS            H.Naoi          Update          Sol#097(QC#18398)
 * 01/29/2018   CITS            T.Gotoda        Update          QC#22521
 * 02/01/2023   Hitachi         S.Dong          Update          QC#60966
 *</pre>
 */
public class NPAL1270Constant {

    /**
     * DB Like search char
     */
    public static final String LIKE_SEARCH_CHAR = "%";

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

    /**
     * Business button SetItemDescription
     */
    public static final String BTN_SET_ITEM_DESCRIPTION = "SetItemDescription";

    /**
     * Business button Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Business button New
     */
    public static final String BTN_NEW = "New";

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
    public static final String BIZ_APP_ID = "NPAL1270";

    /**
     * PO Requisition Entry Business Application ID
     */
    public static final String PO_R_ENTRY_BIZ_APP_ID = "NPAL1280";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1270Scrn00";

    /** Function Inquiry */
    public static final String FUNC_INQUIRY = "NPAL1270T010";

    /** Function Edit */
    public static final String FUNC_EDIT = "NPAL1270T020";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    /**
     * All Item
     */
    public static final String ALL_ITEM = "AL";

    /**
     * Ship To's Only.
     */
    public static final String SHIP_TO_ONLY_CD = "03";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : SRCH_OPT_NM
     */
    public static final String DISPLAY_NM_SRCH_OPT_NM = "Search Option Name";

    /**
     * Display Name : PRCH_REQ_NUM
     */
    public static final String DISPLAY_NM_REQ_NUM = "Requisition Number";

    /**
     * Display Name : PRCH_REQ_TP_CD
     */
    public static final String DISPLAY_NM_REQ_TP_CD = "Requisition Type";

    /**
     * Display Name : PRCH_REQ_TP_DESC_TXT
     */
    public static final String DISPLAY_NM_REQ_TP_DESC_TXT = "Requisition Type Txt";

    /**
     * Display Name : PRCH_REQ_STS_CD
     */
    public static final String DISPLAY_NM_REQ_STS_CD = "Header Status";

    /**
     * Display Name : PRCH_REQ_STS_DESC_TXT
     */
    public static final String DISPLAY_NM_REQ_STS_DESC_TXT = "Header Status Txt";

    /**
     * Display Name : PRCH_REQ_APVL_STS_CD
     */
    public static final String DISPLAY_NM_REQ_APVL_STS_CD = "Approved Status Code";

    /**
     * Display Name : PRCH_REQ_APVL_STS_DESC_TXT
     */
    public static final String DISPLAY_NM_REQ_APVL_STS_DESC_TXT = "Approved Status Txt";

    /**
     * Display Name : PRCH_REQ_CRAT_DT_FR
     */
    public static final String DISPLAY_NM_REQ_CRAT_DT_FR = "Date Created From";

    /**
     * Display Name : PRCH_REQ_CRAT_DT_TO
     */
    public static final String DISPLAY_NM_REQ_CRAT_DT_TO = "Date Created To";

    /**
     * Display Name : RQST_RCV_DT_FR
     */
    public static final String DISPLAY_NM_RQST_RCV_DT_FR = "Date Needed From";

    /**
     * Display Name : RQST_RCV_DT_TO
     */
    public static final String DISPLAY_NM_RQST_RCV_DT_TO = "Date Needed To";
    // START 2023/02/01 S.Dong [QC#60966, ADD]
    /**
     * Display Name : RQST_SHIP_DT_FR
     */
    public static final String DISPLAY_NM_RQST_SHIP_DT_FR = "Date Vendor Ship From";

    /**
     * Display Name : RQST_SHIP_DT_TO
     */
    public static final String DISPLAY_NM_RQST_SHIP_DT_TO = "Date Vendor Ship To";
    // END 2023/02/01 S.Dong [QC#60966, ADD]
    /**
     * Display Name : PRCH_REQ_LINE_STS_CD
     */
    public static final String DISPLAY_NM_REQ_LINE_STS_CD = "Line Status";

    /**
     * Display Name : PRCH_REQ_LINE_STS_DESC_TXT
     */
    public static final String DISPLAY_NM_REQ_LINE_STS_DESC_TXT = "";

    /**
     * Display Name : PRCH_REQ_SRC_TP_CD
     */
    public static final String DISPLAY_NM_REQ_SRC_TP_CD = "Document Source Type";

    /**
     * Display Name : PRCH_REQ_SRC_TP_DESC_TXT
     */
    public static final String DISPLAY_NM_REQ_SRC_TP_DESC_TXT = "";

    /**
     * Display Name : TRX_REF_NUM
     */
    public static final String DISPLAY_NM_TRX_REF_NUM = "Source Doc#";

    //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
    /**
     * Display Name : MRP_PLN_NM
     */
    public static final String DISPLAY_NM_MRP_PLN_NM = "Plan Name";
    //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END

    /**
     * Display Name : PRCH_REQ_CRAT_BY_PSN_CD
     */
    public static final String DISPLAY_NM_REQ_CRAT_BY_PSN_CD = "Buyer";

    /**
     * Display Name : FULL_PSN_NM
     */
    public static final String DISPLAY_NM_FULL_PSN_NM = "Buyer Name";

    /**
     * Display Name : PRCH_GRP_CD
     */
    public static final String DISPLAY_NM_GRP_CD = "Business Unit";

    /**
     * Display Name : PRCH_GRP_DESC_TXT
     */
    public static final String DISPLAY_NM_GRP_DESC_TXT = "";

    /**
     * Display Name : SHPG_SVC_LVL_CD
     */
    public static final String DISPLAY_NM_SHPG_SVC_LVL_CD = "shpgSvcLvlCd_SL";

    /**
     * Display Name : SHPG_SVC_LVL_DESC_TXT
     */
    public static final String DISPLAY_NM_SHPG_SVC_LVL_DESC_TXT = "";

    /**
     * Display Name : CARR_CD
     */
    public static final String DISPLAY_NM_CARR_CD = "Requested Carrier";

    /**
     * Display Name : CARR_NM
     */
    public static final String DISPLAY_NM_CARR_NM = "Requested Carrier Name";

    /**
     * Display Name : Item Number
     */
    public static final String DISPLAY_NM_ITEM_NUMBER = "Item Number";

    /**
     * Display Name : PRNT_VND_CD
     */
    public static final String DISPLAY_NM_PRNT_VND_CD = "Supplier Code";

    /**
     * Display Name : PRNT_VND_NM
     */
    public static final String DISPLAY_NM_PRNT_VND_NM = "Supplier Name";

    /**
     * Display Name : VND_CD
     */
    public static final String DISPLAY_NM_VND_CD = "Supplier Site Code";

    /**
     * Display Name : VND_NM
     */
    public static final String DISPLAY_NM_VND_NM = "Supplier Site Name";

    /**
     * Display Name : DEST_RTL_WH_CD
     */
    public static final String DISPLAY_NM_DEST_RTL_WH_CD = "Destination WH Code";

    /**
     * Display Name : DEST_RTL_WH_NM
     */
    public static final String DISPLAY_NM_DEST_RTL_WH_NM = "Destination WH Name";

    /**
     * Display Name : DEST_RTL_SWH_CD
     */
    public static final String DISPLAY_NM_DEST_RTL_SWH_CD = "Destination SWH Code";

    /**
     * Display Name : DEST_RTL_SWH_NM
     */
    public static final String DISPLAY_NM_DEST_RTL_SWH_NM = "Destination SWH Name";

    /**
     * Display Name : SHIP_TO_CUST_CD
     */
    public static final String DISPLAY_NM_SHIP_TO_CUST_CD = "Ship To Customer Code";

    /**
     * Display Name : SHIP_TO_CUST_NM
     */
    public static final String DISPLAY_NM_SHIP_TO_CUST_NM = "Ship To Customer Name";

    /**
     * Display Name : PO_ORD_NUM
     */
    public static final String DISPLAY_NM_PO_ORD_NUM = "PO Number";

    /**
     * Display Name : COA_MDSE_TP_CD
     */
    public static final String DISPLAY_NM_COA_MDSE_TP_CD = "Merchandise Type";

    /**
     * Display Name : COA_PROD_CD
     */
    public static final String DISPLAY_NM_COA_PROD_CD = "Product Code";

    /**
     * Display Name : Default Display Level
     */
    public static final String DEFAULT_DISPLAY_LEVEL = "Header";

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
    // CARR Popup Param
    // =================================================
    /**
     * Code Column for NPAL1010
     */
    public static final String LOC_ROLE_TP_CDLIST = "RGRL_STK_WH,RTRN_ASSET_WH,TECHNICIAN";

    // for Carrier
    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_FOR_CARR = "OTBD_CARR_V";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_CD_COL_NM_FOR_CARR = "CARR_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_COL_NM_FOR_CARR = "CARR_NM";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_SORT_COL_NM_FOR_CARR = "CARR_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String SCR_NM_FOR_CARR = "Carrier Search Popup";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_CD_LB_NM_FOR_CARR = "Carrier Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_NM_LB_NM_FOR_CARR = "Carrier Name";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_CD_LB_NM_FOR_CARR = "Carrier Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_NM_LB_NM_FOR_CARR = "Carrier Name";

    // =================================================
    // Buyer Popup Param
    // =================================================
    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_FOR_BUYER = "AUTH_PSN";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_CD_COL_NM_FOR_BUYER = "USR_NM";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_COL_NM_FOR_BUYER = "FIRST_NM || ' ' || LAST_NM";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_SORT_COL_NM_FOR_BUYER = "USR_NM";

    /**
     * Code Column for NMAL6050
     */
    public static final String SCR_NM_FOR_BUYER = "Buyer Search Popup";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_CD_LB_NM_FOR_BUYER = "Person Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_NM_LB_NM_FOR_BUYER = "Person Name";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_CD_LB_NM_FOR_BUYER = "Person Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_NM_LB_NM_FOR_BUYER = "Person Name";
    
    /**
     * 
     */
    public static final int IDX_0 = 0;

    /**
     * 
     */
    public static final int IDX_1 = 1;

    /**
     * 
     */
    public static final int IDX_2 = 2;

    /**
     * 
     */
    public static final int IDX_3 = 3;

    /**
     * 
     */
    public static final int IDX_4 = 4;

    /**
     * 
     */
    public static final int IDX_5 = 5;

    /**
     * 
     */
    public static final int IDX_6 = 6;

    /**
     * 
     */
    public static final int IDX_7 = 7;

    /**
     * 
     */
    public static final int IDX_20 = 20;

    /**
     * 
     */
    public static final int IDX_62 = 62;

    // =================================================
    // Supplier Popup Param
    // =================================================
    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_FOR_SUPPLIER = "PO_VND_V";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_CD_COL1_NM_FOR_SUPPLIER = "PRNT_VND_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_COL1_NM_FOR_SUPPLIER = "PRNT_VND_NM";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_CD_COL2_NM_FOR_SUPPLIER = "VND_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_NM_COL2_NM_FOR_SUPPLIER = "VND_NM";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_SORT_COL1_NM_FOR_SUPPLIER = "PRNT_VND_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String TBL_SORT_COL2_NM_FOR_SUPPLIER = "VND_CD";

    /**
     * Code Column for NMAL6050
     */
    public static final String SCR_NM_FOR_SUPPLIER = "Supplier Search Popup";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_CD1_LB_NM_FOR_SUPPLIER = "Supplier Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_NM1_LB_NM_FOR_SUPPLIER = "Supplier Name";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_CD2_LB_NM_FOR_SUPPLIER = "Supplier Site Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String HDR_NM2_LB_NM_FOR_SUPPLIER = "Supplier Site Name";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_CD1_LB_NM_FOR_SUPPLIER = "Supplier Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_NM1_LB_NM_FOR_SUPPLIER = "Supplier Name";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_CD2_LB_NM_FOR_SUPPLIER = "Supplier Site Code";

    /**
     * Code Column for NMAL6050
     */
    public static final String DTL_NM2_LB_NM_FOR_SUPPLIER = "Supplier Site Name";

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

    /**
     * NPAM0009E:Please fill out/select the required field.
     */
    public static final String NPAM0009E = "NPAM0009E";

}
