/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPBL0010.constant;

/**
 * <pre>
 * Business ID : NPBL0010 Inventory Request List
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS         Makoto Okigami     Create          N/A
 * 09/05/2016   CITS         K.Ogino            Update          QC#13923
 * 11/15/2017   CITS         K.Ogino            Update          QC#22345
 * 11/26/2018   CITS         K.Ogino            Update          QC#28409
 * 06/22/2023   Hitachi      S.Fujita           Update          QC#61637
 *</pre>
 */
public class NPBL0010Constant {
    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPBL0010";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPBL0010Scrn00";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NPBL0010_INIT = "NPBL0010_INIT";

    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NPBL0010_SEARCH = "NPBL0010Scrn00_Search";

    /**
     * Event Name : CMN_Rest
     */
    public static final String EVENT_NM_NPBL0010_CMN_RESET = "NPBL0010Scrn00_CMN_Reset";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NPBL0010_PAGE_NEXT = "NPBL0010Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NPBL0010_PAGE_PREV = "NPBL0010Scrn00_PagePrev";

    // START 2023/06/22 S.Fujita [QC#61637, ADD]
    /**
     * Event Name : TBLColumnSort
     */
    public static final String EVENT_NM_NPBL0010_TBL_COLUMN_SORT = "NPBL0010Scrn00_TBLColumnSort";
    // END 2023/06/22 S.Fujita [QC#61637, ADD]

    /**
     * Event Name : SaveSearchOption
     */
    public static final String EVENT_NM_NPBL0010_SAVE_SEARCH_OPTION = "NPBL0010Scrn00_SaveSearchOption";

    /**
     * Event Name : DeleteSearchOption
     */
    public static final String EVENT_NM_NPBL0010_DELETE_SEARCH_OPTION = "NPBL0010Scrn00_DeleteSearchOption";

    /**
     * Event Name : OnChange_SearchOption
     */
    public static final String EVENT_NM_NPBL0010_ON_CHANGE_SEARCH_OPTION = "NPBL0010Scrn00_OnChangeSearchOption";

    /**
     * Event Name : CMN_ColClear
     */
    public static final String EVENT_NM_NPBL0010_CMN_COL_CLEAR = "NPBL0010Scrn00_CMN_ColClear";

    /**
     * Event Name : CMN_ColSave
     */
    public static final String EVENT_NM_NPBL0010_CMN_COL_SAVE = "NPBL0010Scrn00_CMN_ColSave";

    /** Event Name : Download */
    public static final String EVENT_NM_NPBL0010_CMN_DOWNLOAD = "NPBL0010Scrn00_CMN_Download";

    // =================================================
    // Screen Control Value
    // =================================================
    /**
     * Control Value : Search Option Header
     */
    public static final int SCREEN_CONTROL_VALUE_SEARCH_OP_HEADER = 0;

    /**
     * Control Value : Search Option Detail
     */
    public static final int SCREEN_CONTROL_VALUE_SEARCH_OP_DETAIL = 1;

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name: Search Option Application Id
     */
    public static final String DB_PARAM_SRCH_OPT_APL_ID = "srchOptAplId";

    /**
     * DB Param Name: Search Option User Id
     */
    public static final String DB_PARAM_SRCH_OPT_USR_ID = "srchOptUsrId";

    /**
     * DB Param Name: PR Record Type
     */
    public static final String DB_PARAM_PRCH_REQ_REC_TP_CD = "prchReqRecTpCd";

    /**
     * DB Param Name:cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Param Name:Serial Query Parts
     */
    public static final String DB_PARAM_SERIAL_QUERY_PARTS = "serialQueryParts";

    /**
     * DB Param Name:ROWNUM
     */
    public static final String DB_PARAM_ROW_NUM = "rowNum";

    /**
     * DB Param VND_RTRN_FLG
     */
    public static final String DB_PARAM_VND_RTRN_FLG = "vndRtrnFlg";

    /**
     * DB Param STD_COST_RELN_FLG
     */
    public static final String DB_PARAM_STD_COST_RELN_FLG = "stdCostRelnFlg";

    /**
     * DB Param MDSE_CST_UPD_STS_CD
     */
    public static final String DB_PARAM_MDSE_CST_UPD_STS_CD = "mdseCstUpdStsCd";

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
     * DB Column Name: Requisition Type CD
     */
    public static final String DB_COLUMN_PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    /**
     * DB Column Name: Requisition Type Name
     */
    public static final String DB_COLUMN_PRCH_REQ_TP_DESC_TXT = "PRCH_REQ_TP_DESC_TXT";

    /**
     * DB Column Name: Document Source Type CD
     */
    public static final String DB_COLUMN_PRCH_REQ_SRC_TP_CD = "PRCH_REQ_SRC_TP_CD";

    /**
     * DB Column Name: Document Source Type Name
     */
    public static final String DB_COLUMN_PRCH_REQ_SRC_TP_DESC_TXT = "PRCH_REQ_SRC_TP_DESC_TXT";

    /**
     * DB Column Name: Requisition Number
     */
    public static final String DB_COLUMN_PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /**
     * DB Column Name: Requisition Line Number
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /**
     * DB Column Name: Requisition Line Sub Number
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /**
     * DB Column Name: Serial#
     */
    public static final String DB_COLUMN_SER_NUM = "SER_NUM";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_PRCH_REQ_NUM_FOR_VIEW = "PRCH_REQ_NUM_FOR_VIEW";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_PRCH_REQ_STS_DESC_TXT = "PRCH_REQ_STS_DESC_TXT";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_PRCH_REQ_APVL_STS_DESC_TXT = "PRCH_REQ_APVL_STS_DESC_TXT";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_PRCH_REQ_CRAT_DT = "PRCH_REQ_CRAT_DT";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_RQST_RCV_DT = "RQST_RCV_DT";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_TRX_REF_NUM = "TRX_REF_NUM";

    //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_MRP_PLN_NM = "MRP_PLN_NM";
    //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
    
    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_PRCH_REQ_RQST_BY_PSN_CD = "PRCH_REQ_RQST_BY_PSN_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_FULL_PSN_NM = "FULL_PSN_NM";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_PRCH_GRP_DESC_TXT = "PRCH_GRP_DESC_TXT";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_SHPG_SVC_LVL_DESC_TXT = "SHPG_SVC_LVL_DESC_TXT";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_CARR_CD = "CARR_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_CARR_NM = "CARR_NM";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_VND_RTRN_RSN_DESC_TXT = "VND_RTRN_RSN_DESC_TXT";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_SRC_RTL_WH_CD = "SRC_RTL_WH_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_SRC_RTL_SWH_CD = "SRC_RTL_SWH_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_RTL_SWH_NM = "RTL_SWH_NM";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_RTL_WH_NM2 = "RTL_WH_NM2";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_DEST_RTL_SWH_CD = "DEST_RTL_SWH_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_RTL_SWH_NM2 = "RTL_SWH_NM2";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_PRNT_VND_CD = "PRNT_VND_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_PRNT_VND_NM = "PRNT_VND_NM";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_VND_CD = "VND_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_DPLY_VND_NM = "DPLY_VND_NM";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_LINE_NUMBER = "LINE_NUMBER";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_TP_DESC_TXT = "PRCH_REQ_LINE_TP_DESC_TXT";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_MDSE_CD = "MDSE_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_MDSE_NM = "MDSE_NM";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_PRCH_REQ_DISP_QTY = "PRCH_REQ_DISP_QTY";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_STS_DESC_TXT = "PRCH_REQ_LINE_STS_DESC_TXT";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_ENT_PO_DTL_DEAL_NET_AMT = "ENT_PO_DTL_DEAL_NET_AMT";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_CHARGE_ACCOUNT = "CHARGE_ACCOUNT";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_COA_MDSE_TP_CD = "COA_MDSE_TP_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_COA_PROD_CD = "COA_PROD_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_PRCH_REQ_LINE_CMNT_TXT = "PRCH_REQ_LINE_CMNT_TXT";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_COA_CMPY_CD = "COA_CMPY_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_COA_AFFL_CD = "COA_AFFL_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_COA_BR_CD = "COA_BR_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_COA_CC_CD = "COA_CC_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_COA_ACCT_CD = "COA_ACCT_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_COA_PROD_CD2 = "COA_PROD_CD2";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_COA_CH_CD = "COA_CH_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_COA_PROJ_CD = "COA_PROJ_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_COA_EXTN_CD = "COA_EXTN_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_SERIAL_NUMBER = "SERIAL_NUMBER";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /**
     * DB Column Name:
     */
    public static final String DB_COLUMN_DS_ACCT_NM = "DS_ACCT_NM";

    /**
     * DB Column Name:CPO Add QC#22345
     */
    public static final String DB_COLUMN_CPO_ORD_NUM = "CPO_ORD_NUM";
    // =================================================
    // DB Value Limit
    // =================================================
    /**
     * DB Value Limit: Serial# and charge account
     */
    public static final int DB_VALUE_MAX_BYTE_SIZE = 4000;

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : SRCH_OPT_NM
     */
    public static final String DISPLAY_NM_SRCH_OPT_NM = "Saved Option Name";

    /**
     * Display Name : SRCH_OPT_PK
     */
    public static final String DISPLAY_NM_SRCH_OPT_PK = "Saved Search Options";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NMAM8181W: Search results exceeded [@]. Only showing first @
     * records.
     */
    public static final String NMAM8181W = "NMAM8181W";

    /**
     * NMAM0038I: No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * ZZM9000E:
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * NMAM0182E: You can not [@] this record Because of [@] already
     * exists.
     */
    public static final String NMAM0182E = "NMAM0182E";

    /**
     * ZZM9003I: The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * NMAM0014E: [@] is not selected.
     */
    public static final String NMAM0014E = "NMAM0014E";

    /**
     * NZZM0000E:
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * NZZM0001W:
     */
    public static final String NZZM0001W = "NZZM0001W";

    // =================================================
    // CSV Download
    // =================================================
    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NPBL0010_InventoryRequestList";

    /**
     * CSV file extesion
     */
    public static final String CSV_FILE_EXTENSION = ".csv";

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /**
     * DB Param Name:whTransferTp
     */
    public static final String DB_PARAM_WH_TRANSFER_TP = "whTransferTp";

    /**
     * DB Param Name:expenseOrderTp
     */
    public static final String DB_PARAM_EXPENSE_ORDER_TP = "expenseOrderTp";

    /** CSV_HDR */
    //08/22/2017 CITS H.Naoi Add Sol#097(QC#18398) START
    public static final String[] CSV_HDR = new String[] {"Request#", "Request Type", "Header Status", "Approval Status", "Date Created", "Need By Date", "Document Source Type", "Source Doc#", "Plan Name", "Requester", "Requester Name", "Business Unit",
            "Req Service Level", "Carrier", "Carrier Name", "Vendor Return Reason", "Source WH", "Source WH Name", "Source SWH", "Source SWH Name", "Destination WH", "Destination WH Name", "Destination SWH", "Destination SWH Name",
            "Ship To Supplier Name", "Ship To Customer", "Ship To Customer Name", "Line#", "Line Type", "Item#", "Item Description", "Config#", "Transfer Qty", "Status", "Unit Price", "Total Cost", "Charge Account",
            "MT", "PC", "Serial#", "CPO#", "Text"};
    //08/22/2017 CITS H.Naoi Add Sol#097(QC#18398) END

    /**
     * Add QC#28409.DB Param Name:Vendor Type Code
     */
    public static final String DB_PARAM_VND_TP_CD = "vndTpCd";
}
