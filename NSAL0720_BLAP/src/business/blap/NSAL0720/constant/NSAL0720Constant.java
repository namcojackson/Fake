/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0720.constant;

/**
 *<pre>
 * Update Bill To
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Hitachi         K.Kasai         Create          N/A
 * 2015/12/08   Hitachi         T.Tsuchida      Update          QC#1606
 * 2016/03/25   Hitachi         M.Gotou         Update          QC#5070
 *</pre>
 */
public final class NSAL0720Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0720";

    /**
     * DATE_FORMAT
     */
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /** No search results found. */
    public static final String NSAM0013E = "NSAM0013E";

    /** Please select at least 1 checkbox. */
    public static final String NSAM0015E = "NSAM0015E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** "@" is not entered. */
    public static final String NSAM0007E = "NSAM0007E";

    /** [@] is earlier than [@]. */
    public static final String NSAM0284E = "NSAM0284E";

    /** [@] does not exist in Master. */
    public static final String NSAM0011E = "NSAM0011E";

    /** The entered '@' does not exist. */
    public static final String NSAM0072E = "NSAM0072E";

    /** The process has been error in some data. Please check the data. */
    public static final String NSAM0394W = "NSAM0394W";

    /** The process has been successfully completed.  */
    public static final String NSAM0200I = "NSAM0200I";

    /** There are lines you have not entered the "New Bill To". */
    public static final String NSAM0558E = "NSAM0558E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** MTR_LB_NM : mtrLbNm */
    public static final String MTR_LB_NM = "Base";

    // START 2016/03/25 M.Gotou [QC#5070, MOD]
    /** Return Message : Success */
    public static final String RTRN_MSG_SUCCESS = "Success";
    // END 2016/03/25 M.Gotou [QC#5070, MOD]

    /** Return Message : Failed to update bill to */
    public static final String RTRN_MSG_FAILED = "Failed to update bill to";
}
