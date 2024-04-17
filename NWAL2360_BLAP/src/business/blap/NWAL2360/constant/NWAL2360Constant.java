/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2360.constant;

/**
 *<pre>
 * SOM Approval Detail
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NWAL2360Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NWAL2360";

    /** Screen ID */
    public static final String SCRN_ID = "NWAL2360Scrn00";

    // =================================================
    // Event Name
    // =================================================
    /** Event Name : Init */
    public static final String EVENT_NM_NWAL2360_INIT = "NWAL2360_INIT";

    /** Event Name : CMN_Close */
    public static final String EVENT_NM_NWAL2360_CMN_CLOSE = "NWAL2360Scrn00_CMN_Close";

    /** Event Name : CMN_ColClear */
    public static final String EVENT_NM_NWAL2360_CMN_COL_CLEAR = "NWAL2360Scrn00_CMN_ColClear";

    // =================================================
    // DB Param
    // =================================================
    /** DB Param Name: Global Company Code */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** DB Param Name: DS_ORD_IMPT_PK */
    public static final String DB_PARAM_DS_ORD_IMPT_PK = "dsImptOrdPk";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NMAM0038I: No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * NZZM0001W: There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

}
