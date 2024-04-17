/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0630.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID : NLCL0630 Tech PI Inquiry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/26/2016   CITS         Makoto Okigami     Create          N/A
 * 08/07/2018   CITS            Y.Iwasaki       Update          QC#27010
 * 09/12/2018   CITS            M.Naito         Update          QC#28190
 * 12/03/2018   Fujitsu         T.Ogura         Update          QC#27978
 * 12/11/2018   Fujitsu         T.Ogura         Update          QC#28755
 *</pre>
 */
public class NLCL0630Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NLCL0630";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NLCL0630Scrn00";

    /**
     * Function ID for all reference
     */
    public static final String FUNC_ID_ALL_USERS = "NLCL0630T020";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NLCL0630_INIT = "NLCL0630_INIT";

    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NLCL0630_SEARCH = "NLCL0630Scrn00_Search";

    /**
     * Event Name : CMN_Rest
     */
    public static final String EVENT_NM_NLCL0630_CMN_RESET = "NLCL0630Scrn00_CMN_Reset";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NLCL0630_PAGE_NEXT = "NLCL0630Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NLCL0630_PAGE_PREV = "NLCL0630Scrn00_PagePrev";

    /**
     * Event Name : SearchTechnician
     */
    public static final String EVENT_NM_NLCL0630_SEARCH_TECHNICIAN = "NLCL0630Scrn00_SearchTechnician";

    /**
     * Event Name : Search Branch
     */
    public static final String EVENT_NM_NLCL0630_SEARCH_BRANCH = "NLCL0630Scrn00_SearchBranch";

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * Event Name : SearchLocation
     */
    public static final String EVENT_NM_NLCL0630_SEARCH_LOCATION = "NLCL0630Scrn00_SearchLocation";
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

    /**
     * Event Name : Cancel
     */
    public static final String EVENT_NM_NLCL0630_CANCEL = "NLCL0630Scrn00_Cancel";

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * Event Name : Begin
     */
    public static final String EVENT_NM_NLCL0630_BEGIN = "NLCL0630Scrn00_Begin";

    /**
     * Event Name : Upload
     */
    public static final String EVENT_NM_NLCL0630_UPLOAD = "NLCL0630Scrn00_Upload";

    /**
     * Event Name : NLCL0640
     */
    public static final String EVENT_NM_NLCL0630_NLCL0640 = "NLCL0630_NLCL0640";
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : TECH_TOC_CD
     */
    public static final String DISPLAY_NM_TECH_TOC_CD = "Technician";

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
     * DB Param Name: Sales Date
     */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /**
     * DB Param Name: User Id
     */
    public static final String DB_PARAM_USER_ID = "userId";

    /**
     * DB Param Name: TECH_TOC_CD
     */
    public static final String DB_PARAM_TECH_TOC_CD = "techTocCd";

    /**
     * DB Param Name: COA_BR_CD
     */
    public static final String DB_PARAM_COA_BR_CD = "coaBrCd";

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * DB Param Name: RTL_WH_CD
     */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

    /**
     * DB Param Name: Max ROWNUM
     */
    public static final String DB_PARAM_MAX_ROWNUM = "maxRownum";

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * DB Param Name: PHYS_INVTY_STS_CD
     */
    public static final String DB_PARAM_PHYS_INVTY_STS_CD = "physInvtyStsCd";

    /**
     * DB Param Name: PHYS_INVTY_NUM
     */
    public static final String DB_PARAM_PHYS_INVTY_NUM = "physInvtyNum";

    /**
     * DB Param Name: ROWNUM
     */
    public static final String DB_PARAM_ROWNUM = "rownum";
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

    // =================================================
    // DB Param Value
    // =================================================
    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * DB Param Value: ROWNUM 1
     */
    public static final BigDecimal DB_PARAM_VALUE_ROWNUM_1 = BigDecimal.ONE;
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

    // =================================================
    // DB Columns
    // =================================================
    /**
     * DB Column Name: TECH_TOC_CD
     */
    public static final String DB_COLUMN_TECH_TOC_CD = "TECH_TOC_CD";

    /**
     * DB Column Name: TECH_NM
     */
    public static final String DB_COLUMN_TECH_NM = "TECH_NM";

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * DB Column Name: TECH_NM
     */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * DB Column Name: PHYS_INVTY_STS_DESC_TXT
     */
    public static final String DB_COLUMN_PHYS_INVTY_STS_DESC_TXT = "PHYS_INVTY_STS_DESC_TXT";

    /**
     * DB Column Name: PHYS_INVTY_CNT_STS_CD
     */
    public static final String DB_COLUMN_PHYS_INVTY_CNT_STS_CD = "PHYS_INVTY_CNT_STS_CD";

    /**
     * DB Column Name: PHYS_INVTY_CTRL_PK
     */
    public static final String DB_COLUMN_PHYS_INVTY_CTRL_PK = "PHYS_INVTY_CTRL_PK";
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

    // =================================================
    // API Param
    // =================================================
    /**
     * API Param: NLZC061001
     */
    public static final String CANCEL_MODE = "09";

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * API Param: Timestamp Format
     */
    public static final String API_PARAM_TIMESTAMP_FORMAT = "yyyyMMddHHmmssSSS";
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

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
     * NLZM2278E: The entered [@] does not exist in master.
     */
    public static final String NLZM2278E = "NLZM2278E";

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * NMAM0177E: It failed to update [@].
     */
    public static final String NMAM0177E = "NMAM0177E";
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * NLCM0231E: There is already an [OPEN] state record of Location [@].
     */
    public static final String NLCM0231E = "NLCM0231E";
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

    // =================================================
    // Message Value
    // =================================================
    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * PHYS_INVTY_CTRL
     */
    public static final String MSG_VALUE_PHYS_INVTY_CTRL = "Physical Inventory Control";
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

    // =================================================
    // Other Value
    // =================================================
    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /** */
    public static final String TIMESTAMP_PATTERN = "yyyyMMddHHmmssSSS";
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

}
