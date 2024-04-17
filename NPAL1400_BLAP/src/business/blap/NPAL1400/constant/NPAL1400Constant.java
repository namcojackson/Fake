/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1400.constant;

/**
 * <pre>
 * Business ID : NPAL1400 Reman Inquiry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/07/2016   CITS            S.Tanikawa      Create          N/A
 * 08/24/2016   CITS            T.Gotoda        Update          QC#13290
 * 03/06/2017   CITS            K.Kameoka       Update          QC#17826
 *</pre>
 */
public class NPAL1400Constant {
    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1400";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1400Scrn00";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NPAL1400_INIT = "NPAL1400_INIT";
    /**
     * Event Name : CMN_Reset
     */
    public static final String EVENT_NM_NPAL1400_CMN_RESET = "NPAL1400Scrn00_CMN_Reset";

    /**
     * Event Name : OnChange_SearchOption
     */
    public static final String EVENT_NM_NPAL1400_ON_CHANGE_SEARCH_OPTION = "NPAL1400Scrn00_OnChange_SearchOption";

    /**
     * Event Name : SaveSearchOption
     */
    public static final String EVENT_NM_NPAL1400_SAVE_SEARCH_OPTION = "NPAL1400Scrn00_SaveSearchOption";

    /**
     * Event Name : DeleteSearchOption
     */
    public static final String EVENT_NM_NPAL1400_DELETE_SEARCH_OPTION = "NPAL1400Scrn00_DeleteSearchOption";

    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NPAL1400_SEARCH = "NPAL1400Scrn00_Search";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NPAL1400_PAGE_NEXT = "NPAL1400Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NPAL1400_PAGE_PREV = "NPAL1400Scrn00_PagePrev";

    /**
     * Event Name : CMN_ColClear
     */
    public static final String EVENT_NM_NPAL1400_CMN_COL_CLEAR = "NPAL1400Scrn00_CMN_ColClear";

    /**
     * Event Name : CMN_ColSave
     */
    public static final String EVENT_NM_NPAL1400_CMN_COL_SAVE = "NPAL1400Scrn00_CMN_ColSave";

    /**
     * Event Name : CMN_Download
     */
    public static final String EVENT_NM_NPAL1400_CMN_DOWNLOAD = "NPAL1400Scrn00_CMN_Download";

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
     * DB Param Name:cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Param Name:Row Number
     */
    public static final String DB_PARAM_ROW_NUM = "rowNum";

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
     * DB Column Name: RMNF_ORD_NUM
     */
    public static final String DB_COLUMN_RMNF_ORD_NUM = "RMNF_ORD_NUM";

    /**
     * DB Column Name: OWNR_TECH_TOC_CD
     */
    public static final String DB_COLUMN_OWNR_TECH_TOC_CD = "OWNR_TECH_TOC_CD";

    /**
     * DB Column Name: TECH_NM
     */
    public static final String DB_COLUMN_TECH_NM = "TECH_NM";

    /**
     * DB Column Name: RMNF_START_DT
     */
    public static final String DB_COLUMN_RMNF_START_DT = "RMNF_START_DT";

    /**
     * DB Column Name: RMNF_END_DT
     */
    public static final String DB_COLUMN_RMNF_END_DT = "RMNF_END_DT";

    /**
     * DB Column Name: RMNF_MAIN_UNIT_SER_NUM
     */
    public static final String DB_COLUMN_RMNF_MAIN_UNIT_SER_NUM = "RMNF_MAIN_UNIT_SER_NUM";

    //QC#17826 Start
    /**
     * DB Column Name: T_MDL_NM
     */
    // public static final String DB_COLUMN_MKT_MDL_CD = "MKT_MDL_CD";
    public static final String DB_COLUMN_T_MDL_NM = "T_MDL_NM";
    //QC#17826 End
    
    /**
     * DB Column Name: RMNF_MAIN_UNIT_MDSE_CD
     */
    public static final String DB_COLUMN_RMNF_MAIN_UNIT_MDSE_CD = "RMNF_MAIN_UNIT_MDSE_CD";

    /**
     * DB Column Name: MDSE_DESC_SHORT_TXT
     */
    public static final String DB_COLUMN_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /**
     * DB Column Name: RMNF_ORD_STS_NM
     */
    public static final String DB_COLUMN_RMNF_ORD_STS_NM = "RMNF_ORD_STS_NM";

    /**
     * DB Column Name: RMNF_PRT_USG_COST_AMT
     */
    public static final String DB_COLUMN_RMNF_PRT_USG_COST_AMT = "RMNF_PRT_USG_COST_AMT";

    /**
     * DB Column Name: RMNF_TOT_LBOR_COST_AMT
     */
    public static final String DB_COLUMN_RMNF_TOT_LBOR_COST_AMT = "RMNF_TOT_LBOR_COST_AMT";

    /**
     * DB Column Name: RMNF_OTH_COST_AMT
     */
    public static final String DB_COLUMN_RMNF_OTH_COST_AMT = "RMNF_OTH_COST_AMT";

    /**
     * DB Column Name: RMNF_TOT_COST_AMT
     */
    public static final String DB_COLUMN_RMNF_TOT_COST_AMT = "RMNF_TOT_COST_AMT";

    /**
     * DB Column Name: RMNF_ORD_STS_DESC_TXT
     */
    public static final String DB_COLUMN_RMNF_ORD_STS_DESC_TXT = "RMNF_ORD_STS_DESC_TXT";

    // =================================================
    // Message Code
    // =================================================
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
     * NMAM0038I: No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * NMAM8181W:  Search results exceeded [@]. Only showing first @ records.
     */
    public static final String NMAM8181W = "NMAM8181W";

    /**
     * NZZM0000E: No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * NZZM0001W: There are too many search results, there is data
     * that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

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
    public static final String CSV_FILE_NAME = "NPAL1400_RemanList";

    /**
     * CSV file extension
     */
    public static final String CSV_FILE_EXTENSION = ".csv";

    /** CSV format error */
    public static final int CSV_FORMAT_ERR = 1000;

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** CSV HEADER */
    public static final String[] CSV_HDR = new String[] {"Reman Order #", "Reman Tech Owner", "Reman Tech Owner Name", "Start Date", "End Date",
        "Main Unit Serial", "Model", "Item Number", "Item Name", "Status", "Part Cost", "Labor Cost", "Other Cost", "Total Cost"};
}
