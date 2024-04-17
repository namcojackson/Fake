/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1230.constant;

/**
 * <pre>
 * Business ID : NPAL1230 ASL Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   CITS            T.Gotoda        Create          N/A
 * 10/07/2016   CSAI            Y.Imazu         Update          QC#14881
 * 2018/01/12   CITS            S.Katsuma       Update          QC#12226
 * 2018/04/11   CITS            K.Fukumura      Update          QC#21170
 * 2018/06/15   CITS            Y.Iwasaki       Update          QC#18390
 * 2021/01/15   CITS            J.Evangelista   Update          QC#58165
 * 2023/01/26   Hitachi         S.Dong          Update          QC#60966
 * 2023/02/14   CITS            F.Fadriquela    Update          QC#61095
 *</pre>
 */
public class NPAL1230Constant {
    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1230";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1230Scrn00";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NPAL1230_INIT = "NPAL1230_INIT";

    /**
     * Event Name : GetItemInfo
     */
    public static final String EVENT_NM_NPAL1230_GET_ITEM_INFO = "NPAL1230Scrn00_GetItemInfo";

    /**
     * Event Name : CMN_Reset
     */
    public static final String EVENT_NM_NPAL1230_CMN_RESET = "NPAL1230Scrn00_CMN_Reset";

    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NPAL1230_CMN_SUBMIT = "NPAL1230Scrn00_CMN_Submit";

    /**
     * Event Name : CMN_Clear
     */
    public static final String EVENT_NM_NPAL1230_CMN_CLEAR = "NPAL1230Scrn00_CMN_Clear";

    /**
     * Event Name : CMN_Download
     */
    public static final String EVENT_NM_NPAL1230_CMN_DOWNLOAD = "NPAL1230Scrn00_CMN_Download";

    /**
     * Event Name : SetItemDescription
     */
    public static final String EVENT_NM_NPAL1230_SET_ITEM_DESCRIPTION = "NPAL1230Scrn00_SetItemDescription";

    /**
     * Event Name : SetSupplierName
     */
    public static final String EVENT_NM_NPAL1230_SET_SUPPLIER_NAME = "NPAL1230Scrn00_SetSupplierName";

    /**
     * Event Name : OnClick_InsertRow
     */
    public static final String EVENT_NM_NPAL1230_INSERT_ROW = "NPAL1230Scrn00_OnClick_InsertRow";

    /**
     * Event Name : OnClick_DeleteRow
     */
    public static final String EVENT_NM_NPAL1230_DELETE_ROW = "NPAL1230Scrn00_OnClick_DeleteRow";

    /**
     * Event Name : OnClick_TemplateDownload
     */
    public static final String EVENT_NM_NPAL1230_TEMPLATE_DOWNLOAD = "NPAL1230Scrn00_OnClick_TemplateDownload";

    /**
     * Event Name : OnClick_OnClick_Upload
     */
    public static final String EVENT_NM_NPAL1230_UPLOAD = "NPAL1230Scrn00_OnClick_Upload";

    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NPAL1230_SEARCH = "NPAL1230Scrn00_Search";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NPAL1230_PAGE_NEXT = "NPAL1230Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NPAL1230_PAGE_PREV = "NPAL1230Scrn00_PagePrev";

    // START 2018/01/12 S.Katsuma [QC#12226,ADD]
    /**
     * Event Name : SetSupplierSiteName
     */
    public static final String EVENT_NM_NPAL1230_GET_SUPPLIER_SITE_NAME = "NPAL1230Scrn00_GetSupplierSiteName";
    // END 2018/01/12 S.Katsuma [QC#12226,ADD]

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name: Sales Date
     */
    public static final String DB_PARAM_SLS_DT = "slsDt";

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
     * DB Param Name: COA MDSE Type code
     */
    public static final String DB_PARAM_COA_MDSE_TP_CD = "coaMdseTpCd";

    /**
     * DB Param Name:Supplier Number
     */
    public static final String DB_PARAM_PRNT_VND_CD = "prntVndCd";

    /**
     * DB Param Name:ASL Name
     */
    public static final String DB_PARAM_ASL_NM = "aslNm";

    /**
     * DB Param Name:Supplier Site
     */
    public static final String DB_PARAM_VND_CD = "vndCd";

    /**
     * DB Param Name:effFromDt
     */
    public static final String DB_PARAM_EFF_FROM_DT = "effFromDt";

    /**
     * DB Param Name:effThruDt
     */
    public static final String DB_PARAM_EFF_THRU_DT = "effThruDt";

    /**
     * DB Param Name:primSplyFlg
     */
    public static final String DB_PARAM_PRIM_SPLY_FLG = "primSplyFlg";

    /**
     * DB Param Name:aslQlfyTpCd
     */
    public static final String DB_PARAM_ASL_QLFY_TP_CD = "aslQlfyTpCd";

    /**
     * DB Param Name:aslDtlPk
     */
    public static final String DB_PARAM_ASL_DTL_PK = "aslDtlPk";

    /**
     * DB Param Name:aslQlfyRefCdList
     */
    public static final String DB_PARAM_ASL_QLFY_REF_CD_LIST = "aslQlfyRefCdList";

    /**
     * DB Param Name:isAslQlfyRefFlg
     */
    public static final String DB_PARAM_IS_ASL_QLFY_REF_FLG = "isAslQlfyRefFlg";

    /**
     * DB Param Name:cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Param Name:Sales Date
     */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /**
     * DB Param Name:Row Number
     */
    public static final String DB_PARAM_ROW_NUM = "rowNum";

    /**
     * DB Param Name:rgtnStsCdList
     */
    public static final String DB_PARAM_RGTN_STS_CD_LIST = "rgtnStsCdList";

    /**
     * DB Param Name:aslDtlPkList
     */
    public static final String DB_PARAM_ASL_DTL_PK_LIST = "aslDtlPkList";

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
     * DB Column Name: ASL Qualifier Type Code
     */
    public static final String DB_COLUMN_ASL_QLFY_TP_CD = "ASL_QLFY_TP_CD";

    /**
     * DB Column Name: ASL Qualifier Type Description Text
     */
    public static final String DB_COLUMN_ASL_QLFY_TP_DESC_TXT = "ASL_QLFY_TP_DESC_TXT";

    /**
     * DB Column Name: MDSE_DESC_SHORT_TXT
     */
    public static final String DB_COLUMN_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /**
     * DB Column Name: XX_COMN_SCR_FIRST_VAL_TXT
     */
    public static final String DB_COLUMN_XX_COMN_SCR_FIRST_VAL_TXT = "XX_COMN_SCR_FIRST_VAL_TXT";

    // =================================================
    // Common
    // =================================================
    /**
     * Max date
     */
    public static final String MAX_DATE = "99991231";

    /**
     * Min date
     */
    public static final String MIN_DATE = "19000101";

    // =================================================
    // CSV Upload Download
    // =================================================
    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV file name */
    public static final String CSV_FILE_NAME = "ASLItemList";

    /** CSV Template file name */
    public static final String CSV_TEMP_FILE_NAME = "UploadTemplate";

    /**
     * CSV file extension
     */
    public static final String CSV_FILE_EXTENSION = ".csv";

    /** CSV format error */
    public static final int CSV_FORMAT_ERR = 1000;

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** CSV HEADER DOWNLOAD */
    public static final String[] CSV_HDR_DOWNLOAD = new String[] {"Item Number", "Item Description", "Primary Supplier Flag", "Merchandise Type", "UOM", "Supplier Item Number", "Primary Supplier Site",
            // START 2018/01/12 S.Katsuma [QC#12226,ADD]
            "Supplier Site Name",
            // END 2018/01/12 S.Katsuma [QC#12226,ADD]
            "PO Price", "Effective From Date",
            "Effective Thru Date", "Comment", "Vnd UOM Qty", "Base PO Qty", "Incr Ord Qty", "Min Ord Qty", "Lead Time"
            // QC#21170 2018/04/11 [Lead Time] Add
            // START 2023/01/26 S.Dong [QC#60966, ADD]
            ,"Vendor Ship Lead Time"};
            // END 2023/01/26 S.Dong [QC#60966, ADD]

    // =================================================
    // Message Code
    // =================================================
    /**
     * NZZM0000E: No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

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

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * ZZZM9013E:Data update failure.(ReturnCode = [@])
     */
    public static final String ZZZM9013E = "ZZZM9013E";

    /**
     * NMAM0182E: You can not [@] this record Because of [@] already
     * exists.
     */
    public static final String NMAM0182E = "NMAM0182E";

    /**
     * The number for this process exceeds the maximum numbers for
     * display and cannot proceed.
     */
    public static final String NMAM8114E = "NMAM8114E";

    /**
     * ZZM9003I: The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * NMAM0014E: [@] is not selected.
     */
    public static final String NMAM0014E = "NMAM0014E";

    /**
     * NMAM0072E: @ is duplicated.
     */
    public static final String NMAM0072E = "NMAM0072E";

    /**
     * The Upload File is empty or only has a header line, therefore
     * it could not be processed. Please confirm the content of the
     * Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    /** The format of [@] is incorrect. */
    public static final String NMAM0052E = "NMAM0052E";

    /** This data has been updated by another user. */
    public static final String NPAM1297E = "NPAM1297E";

    /** Error Message: [@] does not exist in Master. */
    public static final String NPAM0076E = "NPAM0076E";

    /** The primary supplier of this item has already been set in other ASL. */
    public static final String NPAM1565E = "NPAM1565E";

    /* 10/07/2016 CSAI Y.Imazu Mod QC#14881 START */
    /** There is no primary supplier of this item. So please set the primary supplier flag. */
    public static final String NPAM1580E = "NPAM1580E";

    /** Details cannot be added because the number will exceed max line count. */
    public static final String NPAM1440E = "NPAM1440E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";
    /* 10/07/2016 CSAI Y.Imazu Mod QC#14881 END */

    // START 2021/01/15 J.Evangelista [QC#58165,ADD]
    /** The length of @ is over than definition. Please enter within @ characters. */
    public static final String NPAM1652E = "NPAM1652E";
    // END 2021/01/15 J.Evangelista [QC#58165,ADD]

    // START 2023/02/14 F.Fadriquela [QC#61095,ADD]
    /** After Decimal Point Digit Number */
    public static final int DECIMAL_POINT_USD = 2;
    // END 2023/02/14 F.Fadriquela [QC#61095,ADD]

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

    // START 2021/01/15 J.Evangelista [QC#58165,ADD]
    /**
     * Display Name : SPLY_ITEM_NUM
     */
    public static final String DISPLAY_NM_SPLY_ITEM_NUM = "Supplier Item Code";
    // END 2021/01/15 J.Evangelista [QC#58165,ADD]

    /** Display Name : Upload Data Format */
    public static final String UPLOAD_DATA_FORMAT = "CSV";
}
