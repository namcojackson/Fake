/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1070.constant;

/**
 *<pre>
 * Business ID : NPAL1070 Min-Max Planning Entry
 * Function Name : Constant (BLAP)
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/24/2016   CITS            Keiichi Masaki  Create          N/A
 * 02/16/2017   CITS            M.Naito         Update          QC#12673
 * 02/17/2017   CITS     Takeshi Tokutomi       Update          QC#17572
 * 11/07/2017   CITS            S.Katsuma       Update          Sol#014(QC#18401)
 * 01/09/2018   CITS            T.Tokutomi      Update          QC#17571
 * 2018/04/09   CITS            K.Ogino         Update          QC#21229
 * 2018/06/19   CITS            K.Ogino         Update          QC#25857
 * 2022/10/05   Hitachi         M.Kikushima     Update          QC#60560
 *</pre>
 */

public class NPAL1070Constant {
    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1070";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1070Scrn00";
    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NPAL1070_INIT = "NPAL1070_INIT";
    /**
     * Event Name : CMN_Clear
     */
    public static final String EVENT_NM_NPAL1070_CMN_CLEAR = "NPAL1070Scrn00_CMN_Clear";
    /**
     * Event Name : CMN_Download
     */
    public static final String EVENT_NM_NPAL1070_CMN_DOWNLOAD = "NPAL1070Scrn00_CMN_Download";
    /**
     * Event Name : CMN_Reset
     */
    public static final String EVENT_NM_NPAL1070_CMN_RESET = "NPAL1070Scrn00_CMN_Reset";
    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NPAL1070_CMN_SUBMIT = "NPAL1070Scrn00_CMN_Submit";
    /**
     * Event Name : Copy
     */
    public static final String EVENT_NM_NPAL1070_CMN_COPY = "NPAL1070Scrn00_Copy";
    /**
     * Event Name : DeleteRow
     */
    public static final String EVENT_NM_NPAL1070_DELETEROW = "NPAL1070Scrn00_DeleteRow";
    /**
     * Event Name : Disable
     */
    public static final String EVENT_NM_NPAL1070_DISABLE = "NPAL1070Scrn00_Disable";
    /**
     * Event Name : Import
     */
    public static final String EVENT_NM_NPAL1070_IMPORT = "NPAL1070Scrn00_Import";
    /**
     * Event Name : Import
     */
    public static final String EVENT_NM_NPAL1070_TEMPLETE_FILE_FOR_UPLOAD = "NPAL1070Scrn00_TempleteFileForUpload";
    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NPAL1070_SEARCH = "NPAL1070Scrn00_Search";
    /**
     * Event Name : Add
     */
    public static final String EVENT_NM_NPAL1070_ADD = "NPAL1070Scrn00_Add";
    // START 2022/10/05 M.Kikushima [QC#60560,ADD]
    /**
     * Event Name : ApplyToAll
     */
    public static final String EVENT_NM_NPAL1070_APPLY_TO_ALL = "NPAL1070Scrn00_ApplyToAll";
    // END 2022/10/05 M.Kikushima [QC#60560,ADD]
    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NPAL1070_PAGE_NEXT = "NPAL1070Scrn00_PageNext";
    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NPAL1070_PAGE_PREV = "NPAL1070Scrn00_PagePrev";
    /**
     * Event Name : SetItemDescription
     */
    public static final String EVENT_NM_NPAL1070_SET_ITEM_DESCRIPTION = "NPAL1070Scrn00_SetItemDescription";
    /**
     * Event Name : SetWarehouseName
     */
    public static final String EVENT_NM_NPAL1070_SET_WAREHOUSENAME = "NPAL1070Scrn00_SetWarehouseName";
    /**
    /**
     * Event Name : SetSubWarehouseName
     */
    public static final String EVENT_NM_NPAL1070_SET_SUB_WAREHOUSENAME = "NPAL1070Scrn00_SetSubWarehouseName";
    /**
     * Event Name : SetManagerName
     */
    public static final String EVENT_NM_NPAL1070_SET_MANAGERNAME = "NPAL1070Scrn00_SetManagerName";
    /**
     * Event Name : SetSourceWarehouseName
     */
    public static final String EVENT_NM_NPAL1070_SET_SOURCE_WAREHOUSENAME = "NPAL1070Scrn00_SetSourceWarehouseName";
    /**
    * Event Name : SetSourceWarehouseNameDetail
    */
    public static final String EVENT_NM_NPAL1070_SET_SOURCE_WAREHOUSENAME_DETAIL = "NPAL1070Scrn00_SetSourceWarehouseNameDetail";
    /**
     * Event Name : SetSourceSubWarehouseName
     */
    public static final String EVENT_NM_NPAL1070_SET_SOURCE_SUB_WAREHOUSENAME = "NPAL1070Scrn00_SetSourceSubWarehouseName";
    /**
     * Event Name : SetSourceSubWarehouseName
     */
    public static final String EVENT_NM_NPAL1070_COPY = "NPAL1070_NPAL1370";
    /**
     * Event Name : SetSourceSubWarehouseName
     */
    public static final String EVENT_NM_NPAL1070_OPEN_WH = "NPAL1070Scrn00_OpenWin_Warehouse";
    /**
     * Event Name : SetSourceSubWarehouseName
     */
    public static final String EVENT_NM_NPAL1070_OPEN_SWH = "NPAL1070Scrn00_OpenWin_SubWarehouse";
    /**
     * Event Name : CMN_ColClear
     */
    public static final String EVENT_NM_NPAL1070_CMN_COL_CLEAR = "NPAL1070Scrn00_CMN_ColClear";
    /**
     * Event Name : CMN_ColSave
     */
    public static final String EVENT_NM_NPAL1070_CMN_COL_SAVE = "NPAL1070Scrn00_CMN_ColSave";
    /**
     * QC#21229
     * Event Name : ItemMasterAttachment
     */
    public static final String EVENT_NM_NPAL1070_ITEM_MASTER_ATTACHMENT = "NPAL1070Scrn00_ItemMasterAttachment";
    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

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
     * DB Param Name: PRCH_PLN_AVAL_FLG code
     */
    public static final String DB_PARAM_PRCH_PLN_AVAL_FLG = "prchPlnAvalFlg";

    /**
     * DB Param Name: MRP_ENBL_FLG code
     */
    public static final String DB_PARAM_MRP_ENBL_FLG = "mrpEnblFlg";

    /**
     * DB Param Name: RTL_SWH_DSBL_FLG code
     */
    public static final String DB_PARAM_RTL_SWH_DSBL_FLG = "rtlSwhDsblFlg";

    /**
     * DB Param Name:cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Param Name:Sales Date
     */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /**
     * DB Param Name:Registration Status Code
     */
    public static final String DB_PARAM_RGTN_STS_CD = "rgtnStsCd";

    /**
     * DB Param Name:MDSE_TP_CD
     */
    public static final String DB_PARAM_MDSE_TP_CD = "mdseTpCd";

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

    /**
     * DB Param Name:Application ID
     */
    public static final String DB_PARAM_APL_ID = "aplId";

    /**
     * DB Param Name:Procurement Type
     */
    public static final String DB_PARAM_PROCR_TP_CD = "procrTpCd";

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
     * NMAM0039E: The corresponding [@] does not exist.
     */
    public static final String NMAM0039E = "NMAM0039E";

    /**
     * NPAM1232E: Duplicated [@] data exist.
     */
    public static final String NPAM1232E = "NPAM1232E";

    /**
     * NPAM1512E: The item code is not kit. So "Source Type" can not select "Work Order".
     */
    public static final String NPAM1512E = "NPAM1512E";

    /**
     * NPAM1599E: This combination of @ is already registered. Plan Name:[@].
     */
    public static final String NPAM1599E = "NPAM1599E";

    /**
     * NMAM0039E: Message Pram
     */
    public static final String ITEM_NUMBER = "Item Number";

    /**
     * NMAM0039E: Message Pram
     */
    public static final String KIT_ITEM_NUMBER = "Kit Item Number";

    /**
     * NMAM0039E: Message Pram
     */
    public static final String SOURCE_RTL_WH_SWH = "Source WH / SWH Code";

    /**
     * NMAM0039E: Message Pram
     */
    public static final String RTL_WH_SWH = "WH / SWH Code";

    /**
     * NPAM1232E: Message Pram
     */
    public static final String ITEM_WH_SWH = "Item Number, WH and SWH";

    /**
     * Message Pram
     */
    public static final String MAX_ROW_999 = "999";

    /**
     * Message Pram
     */
    public static final String MAX_ROW_1000 = "999";

    // =================================================
    // CSV Upload Download
    // =================================================
    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NPAL1070_Min-MaxPlanningEntry";

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    // QC#17571 Update. delete Reorder Qty.
    /** CSV HEADER UPLOAD */
    public static final String[] CSV_HDR_UPLOAD = new String[] {
        "Plan Name"
        , "WH Category Name"
        , "WH Code"
        , "WH Name"
        , "SWH Code"
        , "SWH Name"
        , "Item Number"
        , "Item Description"
        , "Note"
        , "Merchandise Type Name"
        , "Product Control Code"
        , "Minimum Quantity"
        , "Maximum Quantity"
        , "Enabled Check box"
        , "Source Type"
        , "Source Type Name"
        , "Source WH Code"
        , "Source WH Name"
        , "Source SWH Code"
        , "Source SWH Name"
        , "Daily"
        , "Monday"
        , "Tuesday"
        , "Wednesday"
        , "Thursday"
        , "Friday"
        , "Supersession Flag"
        , "Comment"
        // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        , "Include Entered Sales Order"
        // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        };

    // QC#17571 Update. delete Reorder Qty.
    /** CSV HEADER DOWNLOAD*/
    public static final String[] CSV_HDR_DOWNLOAD = new String[] {
          "Plan Name"
        , "WH Category Name"
        , "WH Code"
        , "WH Name"
        , "SWH Code"
        , "SWH Name"
        , "Item Number"
        , "Item Description"
        , "Note"
        , "Merchandise Type Name"
        , "Product Control Code"
        , "Minimum Quantity"
        , "Maximum Quantity"
        , "Enabled Check box"
        , "Source Type"
        , "Source Type Name"
        , "Source WH Code"
        , "Source WH Name"
        , "Source SWH Code"
        , "Source SWH Name"
        , "Daily"
        , "Monday"
        , "Tuesday"
        , "Wednesday"
        , "Thursday"
        , "Friday"
        , "Supersession Flag"
        , "Comment"
        // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        , "Include Entered Sales Order"
        // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        };

    /**
     * DB Param Name: DB_PARAM_EZBUSINESSID
     */
    public static final String DB_PARAM_EZBUSINESSID = "ezBusinessId";

    /**
     * DB Param Name: BIZ_APP_NM
     */
    public static final String DB_PARAM_BIZ_APP_NM = "bizAppNm";

    /**
     * NMAL0110
     */
    public static final String EZBUSINESSID = "NMAL0110";

    /**
     * Item Master Attachments
     */
    public static final String BIZ_APP_NM = "Item Master Attachments";

    /**
     * DB Param Name: RTL_WH_CATG_CD
     */
    public static final String DB_PARAM_RTL_WH_CATG_CD = "rtlWhCatgCd";

    /**
     * DB Param Name: BIZ_APP_NM
     */
    public static final String DB_PARAM_ATT_DATA_KEY_TXT = "attDataKeyTxt";

    /**
     * NPZM0272E:There is no ASL setup for the item number.
     */
    public static final String NPZM0272E = "NPZM0272E";

    // START 2018/12/03 J.Kim [QC#18224,ADD]
    /**
     * Function Edit
     */
    public static final String FUNC_EDIT_FSM = "NPAL1070T030";
    // END 2018/12/03 J.Kim [QC#18224,ADD]
}
