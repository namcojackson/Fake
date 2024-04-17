/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1220.constant;

/**
 * <pre>
 * Business ID : NPAL1220 ASL Search
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/17/2015   CITS       Takeshi Tokutomi     Create          N/A
 * 01/11/2018   CITS            S.Katsuma       Update          QC#12226
 *</pre>
 */
public class NPAL1220Constant {
    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1220";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1220Scrn00";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NPAL1220_INIT = "NPAL1220_INIT";

    /**
     * Event Name : CMN_Clear
     */
    public static final String EVENT_NM_NPAL1220_CMN_CLEAR = "NPAL1220Scrn00_CMN_Clear";

    /**
     * Event Name : SetItemDescription
     */
    public static final String EVENT_NM_NPAL1220_SET_ITEM_DESCRIPTION = "NPAL1220Scrn00_SetItemDescription";

    /**
     * Event Name : SetSupplierName
     */
    public static final String EVENT_NM_NPAL1220_SET_SUPPLIER_NAME = "NPAL1220Scrn00_SetSupplierName";

    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NPAL1220_SEARCH = "NPAL1220Scrn00_Search";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NPAL1220_PAGE_NEXT = "NPAL1220Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NPAL1220_PAGE_PREV = "NPAL1220Scrn00_PagePrev";

    /**
     * Event Name : SaveSearchOption
     */
    public static final String EVENT_NM_NPAL1220_SAVE_SEARCH_OPTION = "NPAL1220Scrn00_SaveSearchOption";

    /**
     * Event Name : DeleteSearchOption
     */
    public static final String EVENT_NM_NPAL1220_DELETE_SEARCH_OPTION = "NPAL1220Scrn00_DeleteSearchOption";

    /**
     * Event Name : OnChange_SearchOption
     */
    public static final String EVENT_NM_NPAL1220_ON_CHANGE_SEARCH_OPTION = "NPAL1220Scrn00_OnChange_SearchOption";

    /**
     * Event Name : CMN_ColClear
     */
    public static final String EVENT_NM_NPAL1220_CMN_COL_CLEAR = "NPAL1220Scrn00_CMN_ColClear";

    /**
     * Event Name : CMN_ColSave
     */
    public static final String EVENT_NM_NPAL1220_CMN_COL_SAVE = "NPAL1220Scrn00_CMN_ColSave";

    // START 2018/01/11 S.Katsuma [QC#12226,ADD]
    /**
     * Event Name : SetSupplierSiteName
     */
    public static final String EVENT_NM_NPAL1220_SET_SUPPLIER_SITE_NAME = "NPAL1220Scrn00_SetSupplierSiteName";
    // END 2018/01/11 S.Katsuma [QC#12226,ADD]

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
     * DB Param Name:Supplier Number
     */
    public static final String DB_PARAM_PRNT_VND_CD = "prntVndCd";

    /**
     * DB Param Name:cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Param Name:Sales Date
     */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    // START 2018/01/11 S.Katsuma [QC#12226,ADD]
    /**
     * DB Param Name:Supplier Site Code
     */
    public static final String DB_PARAM_VND_CD = "vndCd";
    // END 2018/01/11 S.Katsuma [QC#12226,ADD]

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

}
