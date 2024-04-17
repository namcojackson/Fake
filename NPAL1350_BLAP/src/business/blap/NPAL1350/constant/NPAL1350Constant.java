/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1350.constant;

/**
 * <pre>
 * Business ID : NPAL1350 Kitting WO Search
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/20/2016   CITS         Makoto Okigami     Create          N/A
 * 02/15/2017   CITS            M.Naito         Update          QC#12673
 *</pre>
 */
public class NPAL1350Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1350";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1350Scrn00";

    // 2018/03/14 Start
    /**
     * Work Order Report ID
     */
    public static final String WO_RPT_ID = "WO_RPT_ID";

    /**
     * Report Name
     */
    public static final String REPORT_NAME = "WO Download(PDF) : ";

    /**
     * Report Abend Exception
     */
    public static final String REPORT_EXCEPTION = "get report bytes failure";

    /**
     * Reprint
     */
    public static final String REPRINT = "REPRINT";

    /**
     * Work Order Status : Print
     */
    public static final String WRK_ORD_STS_PRINT = "99";
    // 2018/03/14 End
    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NPAL1350_INIT = "NPAL1350_INIT";

    /**
     * Event Name : CMN_Rest
     */
    public static final String EVENT_NM_NPAL1350_CMN_RESET = "NPAL1350Scrn00_CMN_Reset";

    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NPAL1350_SEARCH = "NPAL1350Scrn00_Search";

    /**
     * Event Name : SaveSearchOption
     */
    public static final String EVENT_NM_NPAL1350_SAVE_SEARCH_OPTION = "NPAL1350Scrn00_SaveSearchOption";

    /**
     * Event Name : DeleteSearchOption
     */
    public static final String EVENT_NM_NPAL1350_DELETE_SEARCH_OPTION = "NPAL1350Scrn00_DeleteSearchOption";

    /**
     * Event Name : OnChange_SearchOption
     */
    public static final String EVENT_NM_NPAL1350_ON_CHANGE_SEARCH_OPTION = "NPAL1350Scrn00_OnChangeSearchOption";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NPAL1350_PAGE_NEXT = "NPAL1350Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NPAL1350_PAGE_PREV = "NPAL1350Scrn00_PagePrev";

    /**
     * Event Name : CMN_ColClear
     */
    public static final String EVENT_NM_NPAL1350_CMN_COL_CLEAR = "NPAL1350Scrn00_CMN_ColClear";

    /**
     * Event Name : CMN_ColSave
     */
    public static final String EVENT_NM_NPAL1350_CMN_COL_SAVE = "NPAL1350Scrn00_CMN_ColSave";


    /** Event Name : Download */
    public static final String EVENT_NM_NPAL1350_CMN_DOWNLOAD = "NPAL1350Scrn00_CMN_Download";

    // 2018/03/14 Start
    /**
     * Event Name : Print
     */
    public static final String EVENT_NM_NPAL1350_PRINT = "NPAL1350Scrn00_Print";
    
    /**
     * Button Event Name : Print
     */
    public static final String BTN_PRINT = "Print";
    // 2018/03/14 End
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
     * DB Param Name:WRK_ORD_TP_CD
     */
    public static final String DB_PARAM_WRK_ORD_TP_CD = "wrkOrdTpCd";

    /**
     * DB Param Name:cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Param Name: Max ROWNUM
     */
    public static final String DB_PARAM_MAX_ROWNUM = "maxRownum";

    // 2018/03/14 Start

    /**
     * DB Parameter Name : ROWNUM
     */
    public static final String DB_PARAM_ROWNUM = "rowNum";

    /**
     * DB Parameter Name : WRK_ORD_NUM
     */
    public static final String DB_PARAM_WRK_ORD_NUM = "wrkOrdNum";
    
    /**
     * DB Parameter Name : Inventory Location Code
     */
    public static final String DB_PARAM_INVTY_LOC_CD = "invtyLocCd";

    /**
     * DB Parameter Name : Retail Warehouse Code
     */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /**
     * Message Parameter Name : Insert Work Order BOM
     */
    public static final String INSERT_WRK_ORD_BOM = "Work Order BOM Insert";

    /**
     * Message Parameter Name : Update Work Order
     */
    public static final String UPDATE_WRK_ORD = "Work Order update";
    // 2018/03/14 End

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
     * DB Column Name: Work Order Status Name
     */
    public static final String DB_COLUMN_DS_WRK_ORD_STS_NM = "DS_WRK_ORD_STS_NM";

    /**
     * DB Column Name: WRK_ORD_NUM
     */
    public static final String DB_COLUMN_WRK_ORD_NUM = "WRK_ORD_NUM";

    /**
     * DB Column Name: DS_WRK_ORD_TP_DESC_TXT
     */
    public static final String DB_COLUMN_DS_WRK_ORD_TP_DESC_TXT = "DS_WRK_ORD_TP_DESC_TXT";

    /**
     * DB Column Name: WRK_ORD_DT
     */
    public static final String DB_COLUMN_WRK_ORD_DT = "WRK_ORD_DT";

    /**
     * DB Column Name: RQST_RCV_DT
     */
    public static final String DB_COLUMN_RQST_RCV_DT = "RQST_RCV_DT";

    /**
     * DB Column Name: FNSH_GOODS_MDSE_CD
     */
    public static final String DB_COLUMN_FNSH_GOODS_MDSE_CD = "FNSH_GOODS_MDSE_CD";

    /**
     * DB Column Name: FNSH_MDSE_DESC_SHORT_TXT
     */
    public static final String DB_COLUMN_FNSH_MDSE_DESC_SHORT_TXT = "FNSH_MDSE_DESC_SHORT_TXT";

    /**
     * DB Column Name: RTL_WH_CD
     */
    public static final String DB_COLUMN_RTL_WH_CD = "RTL_WH_CD";

    /**
     * DB Column Name: RTL_WH_NM
     */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";

    /**
     * DB Column Name: CPLT_RTL_SWH_CD
     */
    public static final String DB_COLUMN_CPLT_RTL_SWH_CD = "CPLT_RTL_SWH_CD";

    /**
     * DB Column Name: FNSH_GOODS_ORD_QTY
     */
    public static final String DB_COLUMN_FNSH_GOODS_ORD_QTY = "FNSH_GOODS_ORD_QTY";

    /**
     * DB Column Name: FNSH_GOODS_RCV_QTY
     */
    public static final String DB_COLUMN_FNSH_GOODS_RCV_QTY = "FNSH_GOODS_RCV_QTY";

    /**
     * DB Column Name: FNSH_GOODS_BAL_QTY
     */
    public static final String DB_COLUMN_FNSH_GOODS_BAL_QTY = "FNSH_GOODS_BAL_QTY";

    /**
     * DB Column Name: FNSH_GOODS_CANC_QTY
     */
    public static final String DB_COLUMN_FNSH_GOODS_CANC_QTY = "FNSH_GOODS_CANC_QTY";

    /**
     * DB Column Name: OLD_WRK_ORD_NUM
     */
    public static final String DB_COLUMN_OLD_WRK_ORD_NUM = "OLD_WRK_ORD_NUM";

    /**
     * DB Column Name: RTL_SWH_NM
     */
    public static final String DB_COLUMN_RTL_SWH_NM = "RTL_SWH_NM";

    // QC#22324 2018/03/14 Start
    /**
     * DB Column Name : GLBL_CMPY_CD
     */
    public static final String DB_COLUMN_GLBL_CMPY_CD = "GLBL_CMPY_CD";
    
    /**
     * DB Column Name : CHILD_MDSE_QTY
     */
    public static final String DB_COLUMN_CHILD_MDSE_QTY = "CHILD_MDSE_QTY";

    /**
     * DB Column Name : WRK_ORD_DTL_LINE_NUM
     */
    public static final String DB_COLUMN_WRK_ORD_DTL_LINE_NUM = "WRK_ORD_DTL_LINE_NUM";

    /**
     * DB Column Name : KIT_BOM_PRINT_FLG
     */
    public static final String DB_COLUMN_KIT_BOM_PRINT_FLG = "KIT_BOM_PRINT_FLG";

    /**
     * DB Column Name : ORIG_GOODS_MDSE_CD
     */
    public static final String DB_COLUMN_ORIG_GOODS_MDSE_CD = "ORIG_GOODS_MDSE_CD";

    /**
     * DB Column Name : ORIG_GOODS_ORD_QTY
     */
    public static final String DB_COLUMN_ORIG_GOODS_ORD_QTY = "ORIG_GOODS_ORD_QTY";

    /**
     * DB Column Name : ORIG_MDSE_DESC_SHORT_TXT
     */
    public static final String DB_COLUMN_ORIG_MDSE_DESC_SHORT_TXT = "ORIG_MDSE_DESC_SHORT_TXT";

    /**
     * DB Column Name : WRK_ORD_MSG_TXT
     */
    public static final String DB_COLUMN_WRK_ORD_MSG_TXT = "WRK_ORD_MSG_TXT";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_01
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_01 = "BOM_KIT_CMNT_TXT_01";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_02
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_02 = "BOM_KIT_CMNT_TXT_02";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_03
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_03 = "BOM_KIT_CMNT_TXT_03";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_04
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_04 = "BOM_KIT_CMNT_TXT_04";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_05
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_05 = "BOM_KIT_CMNT_TXT_05";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_06
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_06 = "BOM_KIT_CMNT_TXT_06";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_07
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_07 = "BOM_KIT_CMNT_TXT_07";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_08
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_08 = "BOM_KIT_CMNT_TXT_08";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_09
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_09 = "BOM_KIT_CMNT_TXT_09";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_10
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_10 = "BOM_KIT_CMNT_TXT_10";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_11
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_11 = "BOM_KIT_CMNT_TXT_11";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_12
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_12 = "BOM_KIT_CMNT_TXT_12";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_13
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_13 = "BOM_KIT_CMNT_TXT_13";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_14
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_14 = "BOM_KIT_CMNT_TXT_14";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_15
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_15 = "BOM_KIT_CMNT_TXT_15";

    /**
     * DB Column Name : SO_NUM
     */
    public static final String DB_COLUMN_SO_NUM = "SO_NUM";

    /**
     * DB Column Name : WH_OWNR_CD
     */
    public static final String DB_COLUMN_WH_OWNR_CD = "WH_OWNR_CD";

    /**
     * DB Column Name : SPLY_RTL_SWH_CD
     */
    public static final String DB_COLUMN_SPLY_RTL_SWH_CD = "SPLY_RTL_SWH_CD";
    
    /**
     * DB Column Name : ATTN_NM
     */
    public static final String DB_COLUMN_ATTN_NM = "ATTN_NM";

    /**
     * DB Column Name : FIRST_LINE_ADDR
     */
    public static final String DB_COLUMN_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /**
     * DB Column Name : SCD_LINE_ADDR
     */
    public static final String DB_COLUMN_SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /**
     * DB Column Name : THIRD_LINE_ADDR
     */
    public static final String DB_COLUMN_THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /**
     * DB Column Name : FRTH_LINE_ADDR
     */
    public static final String DB_COLUMN_FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /**
     * DB Column Name : FIFTH_LINE_ADDR
     */
    public static final String DB_COLUMN_FIFTH_LINE_ADDR = "FIFTH_LINE_ADDR";

    /**
     * DB Column Name : SIXTH_LINE_ADDR
     */
    public static final String DB_COLUMN_SIXTH_LINE_ADDR = "SIXTH_LINE_ADDR";

    /**
     * DB Column Name : USR_ID
     */
    public static final String DB_COLUMN_USR_ID = "USR_ID";

    /**
     * DB Column Name : RCV_RPT_TS
     */
    public static final String DB_COLUMN_BOM_RPT_TS = "BOM_RPT_TS";

    /**
     * DB Column Name : INTL_LANG_VAL_COL_NM
     */
    public static final String DB_COLUMN_INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    /**
     * DB Column Name : COUNT
     */
    public static final String DB_COLUMN_COUNT = "COUNT";

    // QC#22324 2018/03/14 End

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
    // CSV Download
    // =================================================
    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NPAL1350_KittingWOSearch";

    /**
     * CSV file extesion
     */
    public static final String CSV_FILE_EXTENSION = ".csv";

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** CSV_HDR */
    public static final String[] CSV_HDR = new String[] {
        "WO Number",
        "WO Type",
        "WO Status",
        "WO Date",
        "Req Comp Date",
        "Kit Item",
        "Item Description",
        "WH",
        "WH Name",
        "Completion Sub WH",
        "Order Qty",
        "Receive Qty",
        "Balance Qty",
        "Cancelled Qty",
        "Old WO Number",
        "Completion Sub WH Name"};

    // =================================================
    // Message Code
    // =================================================
    /**
     * NMAM0038I: No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * NMAM8181W: Search results exceeded [@]. Only showing first @ records.
     */
    public static final String NMAM8181W = "NMAM8181W";

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

    // 2018/03/14 Start
    /**
     * NPAM0006E: This data has been updated by another user.
     */
    public static final String NPAM0006E = "NPAM0006E";

    /**
     * NLBM0024E: @ ended abnormally.
     */
    public static final String NLBM0024E = "NLBM0024E";

    /**
     * NPAM1527E: Entered Work Order# has been changed. Please search again.
     */
    public static final String NPAM1527E = "NPAM1527E";

    /**
     * ZZXM0001E: [@]
     */
    public static final String ZZXM0001E = "ZZXM0001E";

    /**
     * DB access parts return code : Normal
     */
    public static final String DB_ACCESS_PARTS_RETURN_CODE_NORMAL = "0000";

    /**
     * DB access parts return code : No Data Found
     */
    public static final String DB_ACCESS_PARTS_NO_DATA_FOUND = "2000";
    
    // 2018/03/14 End
}
