/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0610.constant;

/**
 * <pre>
 * PI Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CITS            T.Gotoda        Create          N/A
 * 2016/11/08   CITS            R.Shimamoto     Update          V0.2
 *</pre>
 */
public class NLCL0610Constant {

    /**
     * The business Id.
     */
    public static final String BIZ_ID = "NLCL0610";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NLCL0610_INIT = "NLCL0610_INIT";

    /**
     * Event Name : OnClick_SetWhNm
     */
    public static final String EVENT_NM_NLCL0610_ONCLICK_SET_WH_NM = "NLCL0610Scrn00_OnClick_SetWhNm";

    /**
     * Event Name : OnClick_Search
     */
    public static final String EVENT_NM_NLCL0610_ONCLICK_SEARCH = "NLCL0610Scrn00_OnClick_Search";

    /**
     * Event Name : Page Prev
     */
    public static final String EVENT_NM_NLCL0610_PAGE_PREV = "NLCL0610Scrn00_PagePrev";

    /**
     * Event Name : Page Next
     */
    public static final String EVENT_NM_NLCL0610_PAGE_NEXT = "NLCL0610Scrn00_PageNext";

    /**
     * Event Name : OnClick_Cancel
     */
    public static final String EVENT_NM_NLCL0610_ONCLICK_CANCEL = "NLCL0610Scrn00_OnClick_Cancel";

    /**
     * Event Name : OnClick_Edit
     */
    public static final String EVENT_NM_NLCL0610_ONCLICK_EDIT = "NLCL0610Scrn00_OnClick_Edit";

    /**
     * Event Name : CMN_Rest
     */
    public static final String EVENT_NM_NLCL0610_CMN_RESET = "NLCL0610Scrn00_CMN_Reset";

    // ------------------- The message code -------------------
    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";
    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";
    /**The data [@] does not exist in the master. */
    public static final String NZZM0010E = "NZZM0010E";
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";
    /** [@] does not exist in Master. */
    public static final String  NPAM0076E = "NPAM0076E";
    /** [@] is not ready for execution. Please check the status. */
    public static final String  NLCM0179E = "NLCM0179E";
    /** message id : NLAM1118E */
    public static final String MSG_ID_NLAM1118E = "NLAM1118E";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name: RTL_WH_CD
     */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /**
     * DB Param Name: PHYS_INVTY_DT From
     */
    public static final String DB_PARAM_PHYS_INVTY_DT_FROM = "physInvtyDtFrom";

    /**
     * DB Param Name: PHYS_INVTY_DT Thru
     */
    public static final String DB_PARAM_PHYS_INVTY_DT_THRU = "physInvtyDtThru";

    /**
     * DB Param Name: PHYS_INVTY_CTRL_NM
     */
    public static final String DB_PARAM_PHYS_INVTY_CTRL_NM = "physInvtyCtrlNm";

    /**
     * DB Param Name: LOC_TP_CD List
     */
    public static final String DB_PARAM_LOC_TP_CD_LIST = "whPilocTpCdList";

    /**
     * DB Param Name: WH_OWNR_CD List
     */
    public static final String DB_PARAM_WH_OWNR_CD_LIST = "whPiWhOwnrCdList";

    /**
     * DB Param Name: Max ROWNUM
     */
    public static final String DB_PARAM_MAX_ROWNUM = "rowNum";

    /**
     * DB Param Name: PHYS_INVTY_NUM
     */
    public static final String DB_PARAM_PHYS_INVTY_NUM = "physInvtyNum";

    /**
     * DB Param Name: PHYS_INVTY_STS_CD(03:Scheduled)
     */
    public static final String DB_PARAM_PHYS_INVTY_STS_CD_SCHEDULED = "physInvtyStsCdScheduled";

    /**
     * DB Param Name: PHYS_INVTY_STS_CD(01:Open)
     */
    public static final String DB_PARAM_PHYS_INVTY_STS_CD_OPEN = "physInvtyStsCdOpen";

    /**
     * DB Param Name: TECH_PI_CANC_CNT_STS_CD
     */
    public static final String DB_PARAM_TECH_PI_CANC_CNT_STS_CD = "techPiCancCntStsCd";

    // =================================================
    // DB Columns
    // =================================================
    /**
     * DB Column Name: RTL_WH_NM
     */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";

    // =================================================
    // Cost value key
    // =================================================
    /**
     * Key Name : WH_PI_LOC_TP_CD
     */
    public static final String CONST_VALUE_KEY_WH_PI_LOC_TP_CD = "WH_PI_LOC_TP_CD";

    /**
     * Key Name : WH_PI_WH_OWNR_CD
     */
    public static final String CONST_VALUE_KEY_WH_PI_WH_OWNR_CD = "WH_PI_WH_OWNR_CD";

    /**
     * Key Name : TECH_PI_CANC_CNT_STS_CD
     */
    public static final String CONST_VALUE_KEY_TECH_PI_CANC_CNT_STS_CD = "TECH_PI_CANC_CNT_STS_CD";

    // =================================================
    // API Param Value
    // =================================================
    /**
     * API Param Value: Timestamp display length
     */
    public static final int TIMESTAMP_LENGTH = 19;

    // =================================================
    // Other
    // =================================================
    /** Blank. */
    public static final String BLANK = "";

    /** Error Message Text: Status codes can be canceled by Tech or MGR */
    public static final String MSG_TXT_TECH_PI_CANC_CNT_STS_CD = "Status codes can be canceled by Tech or MGR";

}
