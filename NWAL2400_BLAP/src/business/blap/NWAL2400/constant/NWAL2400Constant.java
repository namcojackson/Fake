/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2400.constant;

/**
 * <pre>
 * Business ID : NWAL2400 CUSA Retail Dealer Maintenance
 * </pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/12   CITS            T.Gotoda        Create          N/A
 * 2016/08/02   CITS            S.Tanikawa      Update          QC#10537
 *</pre>
 */
public class NWAL2400Constant {
    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NWAL2400";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NWAL2400Scrn00";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NWAL2400_INIT = "NWAL2400_INIT";

    /**
     * Event Name : CMN_Clear
     */
    public static final String EVENT_NM_NWAL2400_CMN_CLEAR = "NWAL2400Scrn00_CMN_Clear";

    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NWAL2400_CMN_SUBMIT = "NWAL2400Scrn00_CMN_Submit";

    /**
     * Event Name : CMN_Download
     */
    public static final String EVENT_NM_NWAL2400_CMN_DOWNLOAD = "NWAL2400Scrn00_CMN_Download";

    /**
     * Event Name : OnClick_AddNewLine
     */
    public static final String EVENT_NM_NWAL2400_ADD_NEW_LINE = "NWAL2400Scrn00_OnClick_AddNewLine";

    /**
     * Event Name : OnClick_DeleteLine
     */
    public static final String EVENT_NM_NWAL2400_DELETE_LINE = "NWAL2400Scrn00_OnClick_DeleteLine";

    /**
     * Event Name : OnClick_Upload
     */
    public static final String EVENT_NM_NWAL2400_UPLOAD = "NWAL2400Scrn00_OnClick_Upload";

    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NWAL2400_SEARCH = "NWAL2400Scrn00_Search";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NWAL2400_PAGE_NEXT = "NWAL2400Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NWAL2400_PAGE_PREV = "NWAL2400Scrn00_PagePrev";

    /**
     * Event Name : OpenWin_OrderReason
     */
    public static final String EVENT_NM_NWAL2400_ORDER_REASON_POPUP = "NWAL2400Scrn00_OpenWin_OrderReason";

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
     * DB Param Name: Business Application ID
     */
    public static final String DB_PARAM_BIZ_APP_ID = "bizAppId";

    /**
     * DB Param Name: Search Option Application Id
     */
    public static final String DB_PARAM_DS_ORD_CATG_DESC_TXT = "dsOrdCatgDescTxt";

    /**
     * DB Param Name: Search Option User Id
     */
    public static final String DB_PARAM_SRCH_OPT_USR_ID = "srchOptUsrId";

    /**
     * DB Param Name: COA Branch code
     */
    public static final String DB_PARAM_COA_BR_CD = "coaBrCd";

    /**
     * DB Param Name: Retail Warehouse Name
     */
    public static final String DB_PARAM_RTL_WH_NM = "rtlWhNm";

    /**
     * DB Param Name:DS Order Category Code
     */
    public static final String DB_PARAM_DS_ORD_CATG_CD = "dsOrdCatgCd";

    /**
     * DB Param Name:DS Order Type Desc Text
     */
    public static final String DB_PARAM_DS_ORD_TP_DESC_TXT = "dsOrdTpDescTxt";

    /**
     * DB Param Name:DS Order Type Desc Text
     */
    public static final String DB_PARAM_RTL_DLR_CD = "rtlDlrCd";

    /**
     * DB Param Name:DS Order Type Desc Text
     */
    public static final String DB_PARAM_RTL_DIV_CD = "rtlDivCd";

    /**
     * DB Param Name:DS Order Type Desc Text
     */
    public static final String DB_PARAM_DS_RTL_DLR_INFO_PK = "dsRtlDlrInfoPk";

    /**
     * DB Param Name:effFromDt
     */
    public static final String DB_PARAM_EFF_FROM_DT = "effFromDt";

    /**
     * DB Param Name:effThruDt
     */
    public static final String DB_PARAM_EFF_THRU_DT = "effThruDt";

    /**
     * DB Param Name:billToCustCd
     */
    public static final String DB_PARAM_BILL_TO_CUST_CD = "billToCustCd";

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
     * DB Param Name:rgtnStsCd
     */
    public static final String DB_PARAM_RGTN_STS_CD = "rgtnStsCd";

    // =================================================
    // DB Columns
    // =================================================
    /**
     * DB Column Name: RTL_DIV_CD
     */
    public static final String DB_COLUMN_RTL_DIV_CD = "RTL_DIV_CD";

    /**
     * DB Column Name: RTL_WH_CD
     */
    public static final String DB_COLUMN_RTL_WH_CD = "RTL_WH_CD";

    /**
     * DB Column Name: INVTY_LOC_CD
     */
    public static final String DB_COLUMN_INVTY_LOC_CD = "INVTY_LOC_CD";

    /**
     * DB Column Name: DS_RTL_DLR_INFO_PK
     */
    public static final String DB_COLUMN_DS_RTL_DLR_INFO_PK = "DS_RTL_DLR_INFO_PK";

    /**
     * DB Column Name: EZUPTIME
     */
    public static final String DB_COLUMN_EZUPTIME = "EZUPTIME";

    /**
     * DB Column Name: EZUPTIMEZONE
     */
    public static final String DB_COLUMN_EZUPTIMEZONE = "EZUPTIMEZONE";

    // =================================================
    // CSV Upload Download
    // =================================================
    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV file name */
    public static final String CSV_FILE_NAME = "CUSA_Retail_Dealer";

    /**
     * CSV file extension
     */
    public static final String CSV_FILE_EXTENSION = ".csv";

    /** CSV format error */
    public static final int CSV_FORMAT_ERR = 1000;

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** CSV HEADER DOWNLOAD*/
    public static final String[] CSV_HDR_DOWNLOAD = new String[] {
          "Branch Code"
        , "Dealer Code"
        , "Division"
        , "Order Category"
        , "Reason Code"
        , "Warehouse"
        , "Bill To Location"
        , "Contact Group"
        , "Start Date"
        , "End Date"
        };

    // =================================================
    // Common
    // =================================================
    /** Max Date*/
    public static final String MAX_DATE = "99991231";

    /** Check Box Append*/
    public static final String CHKBOX_APPEND = "Append";

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
     * NMAM0836E:[@] field is mandatory.
     */
    public static final String NMAM0836E = "NMAM0836E";

    /**
     * NMAM0803E:Please enter today or future date on [@].
     */
    public static final String NMAM0803E = "NMAM0803E";

    /** For [@], please enter  [@] or a later date. */
    public static final String NWAM0223E = "NWAM0223E";

    /**
     * NWAM0221E:Data failed @. [ Table Name = @, Return Code = @ ]
     */
    public static final String NWAM0221E = "NWAM0221E";

    /**
     * NWAM0429E:This data has been updated by another user.
     */
    public static final String NWAM0429E = "NWAM0429E";

    /**
     * NMAM0038I: No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * NMAM8509E:The data you entered has already been registered.
     */
    public static final String NMAM8509E = "NMAM8509E";

    /**
     * NWAM8457E:Please select the same value to Order Type , Reason Code,WH Code , Bill to Cust Code under same Dealer code and Division code.
     */
    public static final String NWAM8457E = "NWAM8457E";

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
     * NWAM0181E: The entered @ does not exist in Master  or "Inactive" item.
     */
    public static final String NWAM0181E = "NWAM0181E";

    /**
     * NMAM0182E: You can not [@] this record Because of [@] already
     * exists.
     */
    public static final String NMAM0182E = "NMAM0182E";

    /** The number for this process exceeds the maximum numbers for
     *  display and cannot proceed. */
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
     * NMAM0072E:  @ is duplicated.
     */
    public static final String NMAM0072E = "NMAM0072E";

    /**
     * The Upload File is empty or only has a header line, therefore it could not be processed.
     * Please confirm the content of the Upload file. */
    public static final String ZYEM0004E = "ZYEM0004E";

    /** The format of [@] is incorrect. */
    public static final String NMAM0052E = "NMAM0052E";

    /** The details of the process target have not been selected. */
    public static final String NWAM0504E = "NWAM0504E";
}
