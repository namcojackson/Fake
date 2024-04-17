/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1520.constant;

/**
 *<pre>
 * Business ID : NPAL1520 Min-Max Planning Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/11/2016   CITS            Keiichi Masaki  Create          N/A
 * 02/16/2017   CITS            M.Naito         Update          QC#12673
 * 2022/10/05   Hitachi         M.Kikushima     Update          QC#60560
 *</pre>
 */

public class NPAL1520Constant {
    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1520";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1520Scrn00";
    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NPAL1520_INIT = "NPAL1520_INIT";
    /**
     * Event Name : CMN_Reset
     */
    public static final String EVENT_NM_NPAL1520_CMN_RESET = "NPAL1520Scrn00_CMN_Reset";
    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NPAL1520_SEARCH = "NPAL1520Scrn00_Search";
    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NPAL1520_PAGE_NEXT = "NPAL1520Scrn00_PageNext";
    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NPAL1520_PAGE_PREV = "NPAL1520Scrn00_PagePrev";
    /**
     * Event Name : SaveSearchOption
     */
    public static final String EVENT_NM_NPAL1520_SAVE_SEARCH_OPTION = "NPAL1520Scrn00_SaveSearchOption";
    /**
     * Event Name : DeleteSearchOption
     */
    public static final String EVENT_NM_NPAL1520_DELETE_SEARCH_OPTION = "NPAL1520Scrn00_DeleteSearchOption";
    /**
     * Event Name : OnChange_SearchOption
     */
    public static final String EVENT_NM_NPAL1520_ON_CHANGE_SEARCH_OPTION = "NPAL1520Scrn00_OnChange_SearchOption";
    /**
     * Event Name : SetItemDescription
     */
    public static final String EVENT_NM_NPAL1520_SET_ITEM_DESCRIPTION = "NPAL1520Scrn00_SetItemDescription";
    /**
     * Event Name : SetWarehouseName
     */
    public static final String EVENT_NM_NPAL1520_SET_WAREHOUSENAME = "NPAL1520Scrn00_SetWarehouseName";
    /**
     * Event Name : SetSubWarehouseName
     */
    public static final String EVENT_NM_NPAL1520_SET_SUB_WAREHOUSENAME = "NPAL1520Scrn00_SetSubWarehouseName";
    /**
     * Event Name : SetManagerName
     */
    public static final String EVENT_NM_NPAL1520_SET_MANAGERNAME = "NPAL1520Scrn00_SetManagerName";
    /**
     * Event Name : SetSourceWarehouseName
     */
    public static final String EVENT_NM_NPAL1520_SET_SOURCE_WAREHOUSENAME = "NPAL1520Scrn00_SetSourceWarehouseName";
    /**
     * Event Name : SetSourceSubWarehouseName
     */
    public static final String EVENT_NM_NPAL1520_SET_SOURCE_SUB_WAREHOUSENAME = "NPAL1520Scrn00_SetSourceSubWarehouseName";
    /**
     * Event Name : CMN_ColClear
     */
    public static final String EVENT_NM_NPAL1520_CMN_COL_CLEAR = "NPAL1520Scrn00_CMN_ColClear";
    /**
     * Event Name : CMN_ColSave
     */
    public static final String EVENT_NM_NPAL1520_CMN_COL_SAVE = "NPAL1520Scrn00_CMN_ColSave";
    /**
     * Event Name : CMN_Download
     */
    public static final String EVENT_NM_NPAL1520SCRN00_CMN_DOWNLOAD = "NPAL1520Scrn00_CMN_Download";
    /**
     * Event Name : Open Window Warehouse
     */
    public static final String EVENT_NM_NPAL1520SCRN00_OPEN_WH = "NPAL1520Scrn00_OpenWin_Warehouse";
    /**
     * Event Name : Open Window SubWarehouse
     */
    public static final String EVENT_NM_NPAL1520SCRN00_OPEN_SWH = "NPAL1520Scrn00_OpenWin_SubWarehouse";


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
     * DB Param Name: MDSE code
     */
    public static final String DB_PARAM_MDSE_CD = "mdseCd";

    /**
     * DB Param Name: RTL_WH code
     */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /**
     * DB Param Name: RTL_SWH code
     */
    public static final String DB_PARAM_RTL_SWH_CD = "rtlSwhCd";

    /**
     * DB Param Name: SRC_RTL_WH code
     */
    public static final String DB_PARAM_WH_MGR_PSN_CD = "whMgrPsnCd";

    /**
     * DB Param Name: SRC_RTL_WH code
     */
    public static final String DB_PARAM_SRC_RTL_WH_CD = "srcRtlWhCd";

    /**
     * DB Param Name: SRC_RTL_SWH code
     */
    public static final String DB_PARAM_SRC_RTL_SWH_CD = "srcRtlSwhCd";

    /**
     * DB Param Name:cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Param Name:Registration Status Code
     */
    public static final String DB_PARAM_RGTN_STS_CD = "rgtnStsCd";

    /**
     * DB Param Name:rownum
     */
    public static final String DB_PARAM_ROWNUM = "rowNum";

    /**
     * DB Param Value:rownum search
     */
    public static final int ROWNUM_SEARCH = 1000;

    /**
     * DB Param Value:rownum download
     */
    public static final int ROWNUM_DOWNLOAD = 65001;

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

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : SRCH_OPT_PK
     */
    public static final String DISPLAY_NM_SRCH_OPT_PK = "Saved Search Options";

    /**
     * Display Name : SRCH_OPT_NM
     */
    public static final String DISPLAY_NM_SRCH_OPT_NM = "Saved Option Name";

    /**
     * Display Name : MDSE_CD
     */
    public static final String DISPLAY_NM_MDSE_CD = "Item Number";

    /**
     * Display Name : RTL_WH_CD
     */
    public static final String DISPLAY_NM_RTL_WH_CD = "Warehouse Code";

    /**
     * Display Name : RTL_SWH_CD
     */
    public static final String DISPLAY_NM_RTL_SWH_CD = "Sub Warehouse Code";

     /**
     * Display Name : WH_MGR_PSN_CD
     */
    public static final String DISPLAY_NM_WH_MGR_PSN_CD = "Manager Code";

    /**
     * Display Name : SRC_RTL_WH_CD
     */
    public static final String DISPLAY_NM_SRC_RTL_WH_CD = "Source Warehouse Code";

    /**
     * Display Name : SRC_RTL_SWH_CD
     */
    public static final String DISPLAY_NM_SRC_RTL_SWH_CD = "Source Sub Warehouse";

    // =================================================
    // CSV Download
    // =================================================
    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NPAL1520_Min-MaxPlanningInquiry";

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** CSV_HDR */
    public static final String[] CSV_HDR = new String[] {
          "Plan Name"
        , "Item Number"
        , "Item Description"
        , "WH Category"
        , "WH Code"
        , "WH Name"
        , "Sub WH Code"
        , "Sub WH Name"
        , "Manager Code"
        , "Manager Name"
        , "Min Qty"
        , "Max Qty"
        , "Enabled"
        , "Source Type"
        , "Source WH Code"
        , "Source WH Name"
        , "Source Sub WH Code"
        , "Source Sub WH Name"
        // START 2022/10/05 M.Kikushima [QC#60560,ADD]
        , "Include Entered Sales Order"
        // END 2022/10/05 M.Kikushima [QC#60560,ADD]
        };

}
