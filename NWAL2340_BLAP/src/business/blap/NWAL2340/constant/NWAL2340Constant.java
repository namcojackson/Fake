/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2340.constant;

/**
 *<pre>
 * SOM Address Mass Apply
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/22   CITS            S.Tanikawa      Create          N/A
 * 2017/09/20   Fujitsu         R.Nakamura      Update          QC#21114
 *</pre>
 */
public class NWAL2340Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NWAL2340";

    /** Screen ID */
    public static final String SCRN_ID = "NWAL2340Scrn00";

    // =================================================
    // Event Name
    // =================================================
    /** Event Name : Init */
    public static final String EVENT_NM_NWAL2340_INIT = "NWAL2340_INIT";

    /** Event Name : CMN_Close */
    public static final String EVENT_NM_NWAL2340_CMN_CLOSE = "NWAL2340Scrn00_CMN_Close";

    /** Event Name : CMN_ColClear */
    public static final String EVENT_NM_NWAL2340_CMN_COL_CLEAR = "NWAL2340Scrn00_CMN_ColClear";

    // Add Start 2017/09/20 QC#21114
    /** Event Name : MassApply */
    public static final String EVENT_NWAL2340SCRN00_MASSAPPLY = "NWAL2340Scrn00_MassApply";
    // Add End 2017/09/20 QC#21114

    // =================================================
    // DB Param
    // =================================================
    /** DB Param Name: Global Company Code */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** DB Param Name: sell_to_cust_cd */
    public static final String DB_PARAM_SELL_TO_CUST_CD = "sellToCustCd";

    /** DB Param Name: ship_to_cust_cd */
    public static final String DB_PARAM_SHIP_TO_CUST_CD = "shipToCustCd";

    // Add Start 2017/09/20 QC#21114
    /** DB Param Name: ship_to_cust_cd */
    public static final String DB_PARAM_MAX_ROWS = "maxRows";

    /** DB Param Name: ship_to_cust_cd */
    public static final int PARAM_VAL_MAX_ROWS = 2;

    /** NWAM0168E: There are too many search results.  Please review search criteria. */
    public static final String NWAM0168E = "NWAM0168E";

    /** ZZZM9001E: No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";
    // Add End 2017/09/20 QC#21114

    /** NWAM0702E: Relation between "@" and "@" is not correct. */
    public static final String NWAM0702E = "NWAM0702E";
}
