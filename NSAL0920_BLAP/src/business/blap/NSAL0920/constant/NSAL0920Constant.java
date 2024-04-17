/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0920.constant;

/**
 *<pre>
 * Contract Billing Search
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         K.Kasai         Create          N/A
 * 2016/08/09   Hitachi         T.Tomita        Update          QC#13149
 * 2016/09/01   Hitachi         T.Tomita        Update          QC#13976
 * 2018/05/24   Hitachi         U.Kim           Update          QC#26213
 *</pre>
 */
public class NSAL0920Constant {

    /** NZZM0000E: No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** NZZM0001W: There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** BUSINESS ID */
    public static final String BUSINESS_ID = "NSAL0920";

    /** SEARCH_LIMIT_CNT */
    // START 2018/05/24 U.Kim [QC#26213, MOD]
    public static final int SEARCH_LIMIT_CNT = 1001;
    // END 2018/05/24 U.Kim [QC#26213, MOD]

    /** Fetch size for Download */
    public static final int FETCH_SIZE_MAX = 1000;

    /** DOWNLOAD_LIMIT_CNT */
    public static final int DOWNLOAD_LIMIT_CNT = 65000;

    /** VAR_CHAR_CONST:SVC_ORG_FUNC_ROLE_TP_SUPERVR  */
    public static final String SVC_ORG_FUNC_ROLE_TP_SUPERVR = "SVC_ORG_FUNC_ROLE_TP_SUPERVR";

    /** EVENT:Search  */
    public static final String SEARCH = "SEARCH";

    /** EVENT:Download  */
    public static final String DOWNLOAD = "DOWNLOAD";

    // add start 2016/08/01 CSA Defect#12504
    public static final String BASE = "Base";
    // add end 2016/08/01 CSA Defect#12504

    // add start 2016/08/09 CSA Defect#13149
    /** String : hyphen */
    public static final String HYPHEN = "-";
    // add end 2016/08/09 CSA Defect#13149

    // START 2016/09/01 T.Tomita [QC#13976, ADD]
    /** String : end date */
    public static final String END_DT = "99991231";
    // END 2016/09/01 T.Tomita [QC#13976, ADD]
}
