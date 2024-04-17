/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL1030.constant;

/**
 * <pre>
 * Business ID : NLCL1030 Inventory ABC Analysis Search
 * Function Name : Constant
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/21/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public class NLCL1030Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NLCL1030";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NLCL1030Scrn00";

    /** Table Header : A */
    public static final String TABLE_A_HEAD = "AHEAD";

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NLCL1030_InventoryABCAnalysisSearch";

    // =================================================
    // Common
    // =================================================
    /** Comma */
    public static final String COMMA = ",";

    /** Percent */
    public static final String PERCENT = "%";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Limit Download RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65000;

    // =================================================
    // Event Name
    // =================================================
    /** Event Name : Init */
    public static final String EVENT_NM_NLCL1030_INIT = "NLCL1030_INIT";

    /** Event Name : Search */
    public static final String EVENT_NM_NLCL1030SCRN00_SEARCH = "NLCL1030Scrn00_Search";

    /** Event Name : SaveSearhOption */
    public static final String EVENT_NM_NLCL1030SCRN00_SAVESEARCH = "NLCL1030Scrn00_SaveSearch";

    /** Event Name : DeleteSearhOption */
    public static final String EVENT_NM_NLCL1030SCRN00_DELETESEARCH = "NLCL1030Scrn00_DeleteSearch";

    /** Event Name : OnChange_SearchOption */
    public static final String EVENT_NM_NLCL1030SCRN00_ONCHANGE_SAVEDSEARCHOPTION = "NLCL1030Scrn00_OnChangeSavedSearchOption";

    /** Event Name : CMN_Clear */
    public static final String EVENT_NM_NLCL1030SCRN00_CMN_CLEAR = "NLCL1030Scrn00_CMN_Clear";

    /** Event Name : CMN_ColClear */
    public static final String EVENT_NM_NLCL1030SCRN00_CMN_COL_CLEAR = "NLCL1030Scrn00_CMN_ColClear";

    /** Event Name : CMN_ColSave */
    public static final String EVENT_NM_NLCL1030SCRN00_CMN_COL_SAVE = "NLCL1030Scrn00_CMN_ColSave";

    /** Event Name : CMN_Download */
    public static final String EVENT_NM_NLCL1030SCRN00_CMN_DOWNLOAD = "NLCL1030Scrn00_CMN_Download";

    /** Event Name : PageNext */
    public static final String EVENT_NM_NLCL1030SCRN00_PAGE_NEXT = "NLCL1030Scrn00_PageNext";

    /** Event Name : PagePrev */
    public static final String EVENT_NM_NLCL1030SCRN00_PAGE_PREV = "NLCL1030Scrn00_PagePrev";

    /** Event Name : PageJump */
    public static final String EVENT_NM_NLCL1030SCRN00_PAGE_JUMP = "NLCL1030Scrn00_PageJump";

    /** Event Name : TBLColumnSort */
    public static final String EVENT_NM_NLCL1030SCRN00_TBLCOLUMNSORT = "NLCL1030Scrn00_TBLColumnSort";

    // =================================================
    // DB Param
    // =================================================
    /** DB Param: glblCmpyCd */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** DB Param: limitRownum */
    public static final String DB_PARAM_LIMIT_ROWNUM = "limitRownum";

    /** DB Param: srchOptAplId */
    public static final String DB_PARAM_SRCH_OPT_APL_ID = "srchOptAplId";

    /** DB Param: srchOptUsrId */
    public static final String DB_PARAM_SRCH_OPT_USR_ID = "srchOptUsrId";

    /** DB Param: abcAsgNm */
    public static final String DB_PARAM_ABC_ASG_NM = "abcAsgNm";

    /** DB Param: abcAsgPk */
    public static final String DB_PARAM_ABC_ASG_PK = "abcAsgPk";

    /** DB Param: rtlWhCatgCd */
    public static final String DB_PARAM_RTL_WH_CATG_CD = "rtlWhCatgCd";

    /** DB Param: rtlWhCd */
    public static final String DB_PARAM_INVTY_LOC_SRCH_TXT_RW = "rtlWhCd";

    /** DB Param: rtlWhNm */
    public static final String DB_PARAM_LOC_NM_SRCH_TXT_RW = "rtlWhNm";

    /** DB Param: rtlSwhCd */
    public static final String DB_PARAM_INVTY_LOC_SRCH_TXT_SW = "rtlSwhCd";

    // =================================================
    // Define Table Column Name
    // =================================================
    /** SRCH_OPT_PK */
    public static final String SRCH_OPT_PK_DBCOLUMN = "SRCH_OPT_PK";

    /** SRCH_OPT_NM */
    public static final String SRCH_OPT_NM_DBCOLUMN = "SRCH_OPT_NM";

    /** ABC_ASG_NM */
    public static final String ABC_ASG_NM_DBCOLUMN = "ABC_ASG_NM";

    /** ABC_ASG_PK */
    public static final String ABC_ASG_PK_DBCOLUMN = "ABC_ASG_PK";

    /** ABC_ASG_DESC_TXT_DBCOLUMN */
    public static final String ABC_ASG_DESC_TXT_DBCOLUMN = "ABC_ASG_DESC_TXT";

    /** RTL_WH_CATG_DESC_TXT */
    public static final String RTL_WH_CATG_DESC_TXT_DBCOLUMN = "RTL_WH_CATG_DESC_TXT";

    /** RTL_WH_CD */
    public static final String RTL_WH_CD_DBCOLUMN = "RTL_WH_CD";

    /** RTL_WH_NM */
    public static final String RTL_WH_NM_DBCOLUMN = "RTL_WH_NM";

    /** RTL_SWH_CD */
    public static final String RTL_SWH_CD_DBCOLUMN = "RTL_SWH_CD";

    /** RTL_SWH_CD_LIST_TXT */
    public static final String RTL_SWH_CD_LIST_TXT_DBCOLUMN = "RTL_SWH_CD_LIST_TXT";

    /** ABC_ANLS_CRIT_NM */
    public static final String ABC_ANLS_CRIT_NM_DBCOLUMN = "ABC_ANLS_CRIT_NM";

    /** EFF_FROM_DT */
    public static final String EFF_FROM_DT_DBCOLUMN = "EFF_FROM_DT";

    /** EFF_THRU_DT */
    public static final String EFF_THRU_DT_DBCOLUMN = "EFF_THRU_DT";

    /** ABC_ANLS_CLS_NM */
    public static final String ABC_ANLS_CLS_NM_DBCOLUMN = "ABC_ANLS_CLS_NM";

    // =================================================
    // CSV Download Header Column Name
    // =================================================
    /** CSV HEADER DOWNLOAD */
    public static final String[] CSV_HDR_DOWNLOAD = new String[] {
          "ABC Name"
        , "ABC Description"
        , "Warehouse Type"
        , "Warehouse"
        , "Warehouse Name"
        , "Sub Warehouse"
        , "Analysis Criteria"
        , "From Date"
        , "To Date"
        , "ABC Class Name"
        };

    // =================================================
    // The Message Code
    // =================================================
    /** [@] is not selected. */
    public static final String MSG_CD_NPAM1549E = "NPAM1549E";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String MSG_CD_NPAM1550E = "NPAM1550E";

    /** No search results found. */
    public static final String MSG_CD_NPAM1552I = "NPAM1552I";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String MSG_CD_NZZM0001W = "NZZM0001W";

    /** Search results exceeded [@]. Only showing first @ records. */
    public static final String MSG_CD_NPAM1551W = "NPAM1551W";

    /** The process has been successfully completed. */
    public static final String MSG_CD_NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String MSG_CD_ZZM9000E = "ZZM9000E";

    /** The process [@] has been successfully completed. */
    public static final String MSG_CD_ZZZM9003I = "ZZZM9003I";

    // *************************************************************************//
    // Message Parameter Name
    // *************************************************************************//

    /**
     * Display Name : MSG_PRM_SAVE
     */
    public static final String MSG_PRM_SAVE = "Save";

    /**
     * Display Name : MSG_PRM_SRCH_OPT_NM
     */
    public static final String MSG_PRM_SRCH_OPT_NM = "Saved Option Name";

    /**
     * Display Name : MSG_PRM_SRCH_OPT
     */
    public static final String MSG_PRM_SRCH_OPT = "Saved Search Options";

    /**
     * Display Name : MSG_PRM_DEL_SRCH
     */
    public static final String MSG_PRM_DEL_SRCH = "Delete Search";

    /**
     * Display Name : MSG_PRM_SAVE_SRCH
     */
    public static final String MSG_PRM_SAVE_SRCH = "Save Search";

}
