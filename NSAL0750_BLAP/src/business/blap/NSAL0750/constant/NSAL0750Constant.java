/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0750.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2016/11/16   Hitachi         T.Kanasaka      Update          QC#15942
 *</pre>
 */
public class NSAL0750Constant {

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /** No search results found. */
    public static final String NSAM0013E = "NSAM0013E";

    /** Please select at least 1 check box. */
    public static final String NSAM0015E = "NSAM0015E";

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** Return Message : Note Added */
    public static final String RTRN_MSG_SUCCESS = "Success.";

    /** Return Message : Failed to register Note */
    public static final String RTRN_MSG_FAILED = "Status Not Eligible.";

    /** The process has been successfully completed. */
    public static final String NSAM0200I = "NSAM0200I";

    /** The process has been error in some data. Please check the data. */
    public static final String NSAM0394W = "NSAM0394W";

    /**
     *This data has been updated by another user.
     */
    public static final String NZZM0003E = "NZZM0003E";

    /**
     * SEARCH_LIMIT_CNT
     */
    public static final int SEARCH_LIMIT_CNT = 200;

    // START 2016/11/16 T.Kanasaka [QC#15942, ADD]
    /**
     * Business Id
     */
    public static final String BIZ_ID = "NSAL0750";
    // END 2016/11/16 T.Kanasaka [QC#15942, ADD]
}
