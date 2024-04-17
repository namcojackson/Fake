/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1160.constant;

/**
 * <pre>
 * Business ID : NPAL1160 PO/Inventory Approval Maintenace
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CITS            R Shimamoto     Create          N/A
 * 03/23/2016   CITS            K.Ogino         Update          QC#5278
 * 02/10/2017   CITS            M.Naito         Update          QC#12673
 * 11/14/2017   CITS            T.Tokutomi      Create          QC#18689-Sol#303
 * 05/17/2023   Hitachi         T.Kuroda        Update          QC#61211
 * 08/29/2023   Hitachi         M.Kikushima     Update          QC#61590
 *</pre>
 */
public class NPAL1160Constant {
    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1160";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1160Scrn00";

    // CSV D/L
    /** max download count */
    public static final int MAX_DOWNLOAD_CNT = 65000;

    /** fetch size */
    public static final int FETCH_SIZE = 3000;

    /**  */
    public static final String CSV_FILE_NAME_TEAM = "NPAL1160_POAPLVLTeam";

    /**  */
    public static final String CSV_FILE_NAME_MBR = "NPAL1160_POAPLVLMember";

    /**  */
    public static final String CSV_FILE_NAME_TRX = "NPAL1160_POAPLVLTransaction";

    /**  */
    public static final String CSV_FILE_NAME_LOC = "NPAL1160_POAPLVLLocation";

    /**  */
    public static final String CSV_FILE_NAME_LIMIT = "NPAL1160_POAPLVLApvlLimit";

    /** */
    public static final String EXTN_CSV = ".csv";

    /** CSV file header Information Team */
    public static final String[] CSV_HEADER_INFO_TEAM = new String[] {

    "Team Name" //
            , "Description" //
            , "Hierarchy Type" //
    };

    /** CSV file header Information Member */
    public static final String[] CSV_HEADER_INFO_MBR = new String[] {

    "Approval Team Member Primary Key" //
            , "Approval Team Primary Key" //
            , "Approval Team Position Type Code" //
            , "Person Code" //
            , "Full Person Name" //
    };

    /** CSV file header Information Transaction */
    public static final String[] CSV_HEADER_INFO_TRX = new String[] {

    "Team Name" //
            , "Planning Group" //
            , "Parts/MERCH" //
            , "Transactions" //
    };

    /** CSV file header Information Location */
    public static final String[] CSV_HEADER_INFO_LOC = new String[] {

    "Approval Team Location Primary Key" //
            , "Approval Team Primary Key" //
            , "Retail Warehouse Code" //
    };

    /** CSV file header Information ApvLimit */
    public static final String[] CSV_HEADER_INFO_LIMIT = new String[] {

    "Hierarchy Type" //
            , "Position" //
            , "Employee Id" //
            , "Employee Name" //
            , "Planning Group" //
            , "Transactions" //
            // START 2023/08/29 M.Kikushima [QC#61590, ADD]
            , "Request Type" //
            // END 2023/08/29 M.Kikushima [QC#61590, ADD]
            , "$ Limit" //
    };

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NPAL1160_INIT = "NPAL1160_INIT";

    /**
     * Event Name : CMN_Reset
     */
    public static final String EVENT_NM_NPAL1160_CMN_RESET = "NPAL1160Scrn00_CMN_Reset";

    /**
     * Event Name : CMN_SUBMIT
     */
    public static final String EVENT_NM_NPAL1160_CMN_SUBMIT = "NPAL1160Scrn00_CMN_Submit";

    /**
     * Event Name : SetItemDescription
     */
    public static final String EVENT_NM_NPAL1160_SET_ITEM_DESCRIPTION = "NPAL1160Scrn00_SetItemDescription";

    /**
     * Event Name : SetSupplierName
     */
    public static final String EVENT_NM_NPAL1160_SET_SUPPLIER_NAME = "NPAL1160Scrn00_SetSupplierName";

    /**
     * Event Name : Search_Team
     */
    public static final String EVENT_NM_NPAL1160_SEARCH_TEAM = "NPAL1160Scrn00_Search_Team";

    /**
     * Event Name : Search_Member
     */
    public static final String EVENT_NM_NPAL1160_SEARCH_MEMBER = "NPAL1160Scrn00_Search_Member";

    /**
     * Event Name : Search_TeamTransaction
     */
    public static final String EVENT_NM_NPAL1160_SEARCH_TRANSACTION = "NPAL1160Scrn00_Search_TeamTransaction";

    /**
     * Event Name : Search_TeamLocation
     */
    public static final String EVENT_NM_NPAL1160_SEARCH_TEAMLOCATION = "NPAL1160Scrn00_Search_TeamLocation";

    /**
     * Event Name : TAB_Member
     */
    public static final String EVENT_NM_NPAL1160_TAB_MEMBER = "NPAL1160Scrn00_TAB_Member";

    /**
     * Event Name : TAB_Team
     */
    public static final String EVENT_NM_NPAL1160_TAB_TEAM = "NPAL1160Scrn00_TAB_Team";

    /**
     * Event Name : TAB_Transaction
     */
    public static final String EVENT_NM_NPAL1160_TAB_TRANSACTION = "NPAL1160Scrn00_TAB_Transaction";

    /**
     * Event Name : TAB_Location
     */
    public static final String EVENT_NM_NPAL1160_TAB_LOCATION = "NPAL1160Scrn00_TAB_Location";

    /**
     * Event Name : TAB_ApvlLimit
     */
    public static final String EVENT_NM_NPAL1160_TAB_APVLLIMIT = "NPAL1160Scrn00_TAB_ApvlLimit";

    /**
     * QC#18689 Add Constant
     * Event Name : TAB_TechThrhd
     */
    public static final String EVENT_NM_NPAL1160_TAB_TECHTHRHD = "NPAL1160Scrn00_TAB_TechThrhd";

    // START 2023/05/17 T.Kuroda [QC#61211, ADD]
    /**
     * Event Name : TAB_TechMin
     */
    public static final String EVENT_NM_NPAL1160_TAB_TECHMIN = "NPAL1160Scrn00_TAB_TechMin";
    // END   2023/05/17 T.Kuroda [QC#61211, ADD]

    /**
     * Event Name : Search_ApprovalLimit
     */
    public static final String EVENT_NM_NPAL1160_SEARCH_APPROVALLIMIT = "NPAL1160Scrn00_Search_ApprovalLimit";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NPAL1160_PAGE_NEXT = "NPAL1160Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NPAL1160_PAGE_PREV = "NPAL1160Scrn00_PagePrev";

    /**
     * Event Name : PageJump
     */
    public static final String EVENT_NM_NPAL1160_PAGE_JUMP = "NPAL1160Scrn00_PageJump";

    /**
     * Event Name : InsertRow
     */
    public static final String EVENT_NM_NPAL1160_INSERT_ROW = "NPAL1160Scrn00_InsertRow";

    /**
     * Event Name : DeleteRow
     */
    public static final String EVENT_NM_NPAL1160_DELETE_ROW = "NPAL1160Scrn00_DeleteRow";

    /**
     * Event Name : CopyRow
     */
    public static final String EVENT_NM_NPAL1160_COPY_ROW = "NPAL1160Scrn00_CopyRow";

    /**
     * Event Name : CMN_ColClear
     */
    public static final String EVENT_NM_NPAL1160_CMN_COL_CLEAR = "NPAL1160Scrn00_CMN_ColClear";

    /**
     * Event Name : CMN_ColSave
     */
    public static final String EVENT_NM_NPAL1160_CMN_COL_SAVE = "NPAL1160Scrn00_CMN_ColSave";

    /**
     * Event Name : CMN_ColSave
     */
    public static final String EVENT_NM_NPAL1160_CMN_DOWNLOAD = "NPAL1160Scrn00_CMN_Download";

    /**
     * Event Name : NWAL1130_AfterEvent
     */
    public static final String EVENT_NM_NPAL1160_NWAL1130_AFTER = "NPAL1160_NWAL1130";

    /**
     * Event Name : Upload_Member
     */
    public static final String EVENT_NM_NPAL1160_UPLOAD_MEMBER = "NPAL1160Scrn00_Upload_Member";

    /**
     * Event Name : Upload_WH
     */
    public static final String EVENT_NM_NPAL1160_UPLOAD_WH = "NPAL1160Scrn00_Upload_WH";

    /**
     * Event Name : TempleteFileForUpload
     */
    public static final String EVENT_NM_NPAL1160_TEMPFILE = "NPAL1160Scrn00_TempleteFileForUpload";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name:cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Param Name: APVL_TEAM_PK
     */
    public static final String DB_PARAM_APVL_TEAM_PK = "apvlTeamPk";

    /**
     * DB Param Name: APVL_TEAM_NM
     */
    public static final String DB_PARAM_APVL_TEAM_NM = "apvlTeamNm";

    /**
     * DB Param Name: APVL_HRCH_TP_CD
     */
    public static final String DB_PARAM_APVL_HRCH_TP_CD = "apvlHrchTpCd";

    /**
     * DB Param Name: APVL_TEAM_DESC_TXT
     */
    public static final String DB_PARAM_APVL_TEAM_DESC_TXT = "apvlTeamDescTxt";

    /**
     * DB Param Name: RTL_SWH_PRTY_LOC_FLG
     */
    public static final String DB_PARAM_RTL_SWH_PRTY_LOC_FLG = "rtlSwhPrtyLocFlg";

    /**
     * DB Param Name: GTN_STS_CD
     */
    public static final String DB_PARAM_RGTN_STS_CD = "rgtnStsCd";

    /**
     * DB Param Name: APVL_TEAM_POSN_TP
     */
    public static final String DB_PARAM_APVL_TEAM_POSN_TP = "apvlTeamPosnTp";

    /**
     * DB Param Name: PSN_CD
     */
    public static final String DB_PARAM_PSN_CD = "psnCd";

    /**
     * DB Param Name: PRCH_GRP_CD
     */
    public static final String DB_PARAM_PRCH_GRP_CD = "prchGrpCd";

    /**
     * DB Param Name: MDSE_ITEM_TP_CD
     */
    public static final String DB_PARAM_MDSE_ITEM_TP_CD = "mdseItemTpCd";

    /**
     * DB Param Name: APVL_HIST_SRC_TP_CD
     */
    public static final String DB_PARAM_APVL_HIST_SRC_TP_CD = "apvlHistSrcTpCd";

    // START 2023/08/29 M.Kikushima [QC#61590, ADD]
    /**
     * DB Param Name: PRCH_REQ_REC_TP_CD
     */
    public static final String DB_PARAM_PRCH_REQ_REC_TP_CD = "prchReqRecTpCd";

    /**
     * DB Param Name: PO_REQUISITION
     */
    public static final String DB_PARAM_PO_REQUISITION = "poRequisition";

    /**
     * DB Param Name: INVENTORY_REQUEST
     */
    public static final String DB_PARAM_INVENTORY_REQUEST = "inventoryRequest";

    /**
     * DB Param Name: SCR_ENT_AVAL_FLG
     */
    public static final String DB_PARAM_SCR_ENT_AVAL_FLG = "scrEntAvalFlg";

    /**
     * DB Param Name: PRCH_REQ_TP_CD
     */
    public static final String DB_PARAM_PRCH_REQ_TP_CD = "prchReqTpCd";
    // END 2023/08/29 M.Kikushima [QC#61590, ADD]

    /**
     * DB Param Name: RTL_WH_CD
     */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /**
     * DB Param Name: APVL_LIMIT_AMT
     */
    public static final String DB_PARAM_APVL_LIMIT_AMT = "apvlLimitAmt";

    /**
     * DB Param Name: APVL_TEAM_MBR_PK
     */
    public static final String DB_PARAM_APVL_TEAM_MBR_PK = "apvlTeamMbrPk";

    // =================================================
    // DB Columns
    // =================================================
    /**
     * DB Column Name: Search Option PK
     */
    public static final String DB_COLUMN_SRCH_OPT_PK = "SRCH_OPT_PK";

    /**
     * DB Column Name: Search Option Name
     */
    public static final String DB_COLUMN_SRCH_OPT_NM = "SRCH_OPT_NM";

    /**
     * DB Column Name: PO Requisition Type Code
     */
    public static final String DB_COLUMN_PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    /**
     * DB Column Name: PO Requisition Type Code
     */
    public static final String DB_COLUMN_PRCH_REQ_TP_DESC_TXT = "PRCH_REQ_TP_DESC_TXT";

    /**
     * DB Column Name: PRCH_REQ_STS_CD
     */
    public static final String DB_COLUMN_PRCH_REQ_STS_CD = "PRCH_REQ_STS_CD";

    /**
     * DB Column Name: PRCH_REQ_STS_DESC_TXT
     */
    public static final String DB_COLUMN_PRCH_REQ_STS_DESC_TXT = "PRCH_REQ_STS_DESC_TXT";

    /**
     * DB Column Name: PRCH_REQ_APVL_STS_CD
     */
    public static final String DB_COLUMN_PRCH_REQ_APVL_STS_CD = "PRCH_REQ_APVL_STS_CD";

    /**
     * DB Column Name: PRCH_REQ_APVL_STS_DESC_TXT
     */
    public static final String DB_COLUMN_PRCH_REQ_APVL_STS_DESC_TXT = "PRCH_REQ_APVL_STS_DESC_TXT";

    /**
     * DB Column Name: PRCH_REQ_LINE_STS_CD
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_STS_CD = "PRCH_REQ_LINE_STS_CD";

    /**
     * DB Column Name: PRCH_REQ_LINE_STS_DESC_TXT
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_STS_DESC_TXT = "PRCH_REQ_LINE_STS_DESC_TXT";

    /**
     * DB Column Name: PRCH_REQ_SRC_TP_CD
     */
    public static final String DB_COLUMN_PRCH_REQ_SRC_TP_CD = "PRCH_REQ_SRC_TP_CD";

    /**
     * DB Column Name: PRCH_REQ_SRC_TP_DESC_TXT
     */
    public static final String DB_COLUMN_PRCH_REQ_SRC_TP_DESC_TXT = "PRCH_REQ_SRC_TP_DESC_TXT";

    /**
     * DB Column Name: PRCH_GRP_CD
     */
    public static final String DB_COLUMN_PRCH_GRP_CD = "PRCH_GRP_CD";

    /**
     * DB Column Name: PRCH_GRP_DESC_TXT
     */
    public static final String DB_COLUMN_PRCH_GRP_DESC_TXT = "PRCH_GRP_DESC_TXT";

    /**
     * DB Column Name: SHPG_SVC_LVL_CD
     */
    public static final String DB_COLUMN_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /**
     * DB Column Name: SHPG_SVC_LVL_DESC_TXT
     */
    public static final String DB_COLUMN_SHPG_SVC_LVL_DESC_TXT = "SHPG_SVC_LVL_DESC_TXT";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NZZM0010E: The data [@] does not exist in the master.
     */
    public static final String NZZM0010E = "NZZM0010E";

    /**
     * NMAM0038I: No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * NZZM0001W: There are too many search results, there is data
     * that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * NMAM8181W:
     */
    public static final String NMAM8181W = "NMAM8181W";

    /**
     * ZZM9000E : [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * NMAM0182E: You can not [@] this record Because of [@] already
     * exists.
     */
    public static final String NMAM0182E = "NMAM0182E";

    /**
     * ZZM9003I : The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * NMAM0014E : [@] is not selected.
     */
    public static final String NMAM0014E = "NMAM0014E";

    /**
     * NZZM0007E : Too many search results.  Please narrow your search criteria and retry.
     */
    public static final String NZZM0007E = "NZZM0007E";

    /**
     * NPAM0049E : The details of the process target have not been selected.
     */
    public static final String NPAM0049E = "NPAM0049E";

    /**
     * NPAM1199E : The number for this process exceeds the maximum numbers for display and cannot proceed.
     */
    public static final String NPAM1199E = "NPAM1199E";

    /**
     * NPAM0077E : You can add Details up to [@].
     */
    public static final String NPAM0077E = "NPAM0077E";

    /**
     * NMAM0265E : There are duplicate of matched transactions.
     */
    public static final String NMAM0265E = "NMAM0265E";

    /**
     * NZZM0003E : This data has been updated by another user.
     */
    public static final String NZZM0003E = "NZZM0003E";

    /**
     * NPAM0007E : The process abended.
     */
    public static final String NPAM0007E = "NPAM0007E";

    /**
     * NMAM0074E : [@] cannot be deleted because of [@].
     */
    public static final String NMAM0074E = "NMAM0074E";

    /**
     * NPAM1237W : By clicking the [Submit] button, the data will be deleted permanently.
     */
    public static final String NPAM1237W = "NPAM1237W";

    /**
     * NPAM0005I : The process has been successfully completed.
     */
    public static final String NPAM0005I = "NPAM0005I";

    /**
     * NPAM1361E: The entered @ does not exist in Master.
     */
    public static final String NPAM1361E = "NPAM1361E";

    /**
     * NLBM1344E : Duplicated @ can not be registered.
     */
    public static final String NLBM1344E = "NLBM1344E";

    /**
     * NPAM1215E : Multiple details to process are selected.
     */
    public static final String NPAM1215E = "NPAM1215E";

    /**
     * Add QC#18689-Sol#303
     * NPAM0108E : [@] is [@].
     */
    public static final String NPAM0108E = "NPAM0108E";

    /**
     * Add QC#18689-Sol#303
     * NPAM1607E : Please enter the amount between [@] and [@].
     */
    public static final String NPAM1607E = "NPAM1607E";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : MDSE_CD
     */
    public static final String DISPLAY_NM_MDSE_CD = "Item Number";

    /**
     * Display Name : PRNT_VND_CD
     */
    public static final String DISPLAY_NM_PRNT_VND_CD = "Supplier Number";

    /**
     * Display Name : SRCH_OPT_NM
     */
    public static final String DISPLAY_NM_SRCH_OPT_NM = "Saved Option Name";

    /**
     * Display Name : SRCH_OPT_PK
     */
    public static final String DISPLAY_NM_SRCH_OPT_PK = "Saved Search Options";

    /**
     * Display Name : DISPLAY_LEVEL_HEADER
     */
    public static final String DISPLAY_LEVEL_HEADER = "Header";

    /**
     * Display Name : DISPLAY_LEVEL_DETAIL
     */
    public static final String DISPLAY_LEVEL_DETAIL = "Detail";

    /**
     * Display Name : Team Tab
     */
    public static final String DISPLAY_TAB_NM_TEAM = "Team";

    /**
     * Display Name : Team Tab
     */
    public static final String DISPLAY_TAB_NM_MEMBER = "Member";

    /**
     * Display Name : Team Tab
     */
    public static final String DISPLAY_TAB_NM_TRANSACTION = "Transaction";

    /**
     * Display Name : Team Tab
     */
    public static final String DISPLAY_TAB_NM_LOCATION = "Location";

    /**
     * Display Name : Team Tab
     */
    public static final String DISPLAY_TAB_NM_APVLLIMIT = "ApvlLimit";

    /**
     * QC#18689 Add constant.
     * Display Name : Team Tab
     */
    public static final String DISPLAY_TAB_NM_TECHTHRHD = "TechThrhd";

    // START 2023/05/17 T.Kuroda [QC#61211, ADD]
    /**
     * Display Name : Team Tab
     */
    public static final String DISPLAY_TAB_NM_TECHMIN = "TechMin";
    // END   2023/05/17 T.Kuroda [QC#61211, ADD]

    // =================================================
    // Seq Name
    // =================================================
    /**
     * Sequence Name : APVL_TEAM Sequence
     */
    public static final String SEQUENCE_NM_APVL_TEAM_SQ = "APVL_TEAM_SQ";

    /**
     * Sequence Name : APVL_TEAM_MBR Sequence
     */
    public static final String SEQUENCE_NM_APVL_TEAM_MBR_SQ = "APVL_TEAM_MBR_SQ";

    /**
     * Sequence Name : APVL_TEAM_TRX Sequence
     */
    public static final String SEQUENCE_NM_APVL_TEAM_TRX_SQ = "APVL_TEAM_TRX_SQ";

    /**
     * Sequence Name : APVL_TEAM_LOC Sequence
     */
    public static final String SEQUENCE_NM_APVL_TEAM_LOC_SQ = "APVL_TEAM_LOC_SQ";

    /**
     * Sequence Name : APVL_LIMIT Sequence
     */
    public static final String SEQUENCE_NM_APVL_LIMIT_SQ = "APVL_LIMIT_SQ";

    // =================================================
    // Error message parameters
    // =================================================
    /**
     * NMAM0074E parameter1
     */
    public static final String NMAM0074E_PARAM1 = "Target Team";

    /**
     * NMAM0074E parameter2
     */
    public static final String NMAM0074E_PARAM2 = "being used other data";

    /**
     * AHEAD
     */
    public static final String AHEAD = "AHEAD";

    /**
     * BHEAD
     */
    public static final String BHEAD = "BHEAD";

    /**
     * CHEAD
     */
    public static final String CHEAD = "CHEAD";

    /**
     * DHEAD
     */
    public static final String DHEAD = "DHEAD";

    /**
     * EHEAD
     */
    public static final String EHEAD = "EHEAD";

    /**
     * KHEAD
     */
    public static final String KHEAD = "KHEAD";

    // START 2023/05/17 T.Kuroda [QC#61211, ADD]
    /**
     * LHEAD
     */
    public static final String LHEAD = "LHEAD";
    // END   2023/05/17 T.Kuroda [QC#61211, ADD]

    // =================================================
    // Other
    // =================================================
    /**
     * ASTERISK
     */
    public static final String ASTERISK = "*";

}
