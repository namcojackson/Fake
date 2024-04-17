/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0800.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2017/08/15   CITS            T.Kikuhara      Update          QC#18799(L3)
 *</pre>
 */
public class NSAL0800Constant {

    /** It failed to insert the [@]. PK [@] */
    public static final String NACM0746E = "NACM0746E";

    /** It failed to update the [@]. PK [@] */
    public static final String NACM0747E = "NACM0747E";

    /** [@] does not exist in [@]. */
    public static final String NFAM0066E = "NFAM0066E";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    // QC#18799 ADD START

    /** No search results found. */
    public static final String NSAM0013E = "NSAM0013E";

    /** The process has been successfully completed. */
    public static final String NSAM0200I = "NSAM0200I";

    public static final String LAST_DAY_OF_A_MONTH = "99";

    public static final String LAST_DAY = "Last Day";

    /** LIMIT_DOWNLOAD */
    public static final int LIMIT_DOWNLOAD = 65000;

    /** DOWNLOAD_FILE_NAME */
    public static final String DOWNLOAD_FILE_NAME = "ContractDefaultRuleList";

    // QC#18799 ADD END

}
