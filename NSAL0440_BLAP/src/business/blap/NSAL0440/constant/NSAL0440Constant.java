/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0440.constant;

/**
 *<pre>
 * Terms & Conditions
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Hitachi         T.Iwamoto       Create          N/A
 * 2016/06/03   Hitachi         T.Tomita        Update          QC#5489
 * 2016/09/29   Hitachi         A.Kohinata      Update          QC#12898
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 * 2022/08/03   Hitachi         N.Takatsu       Update          QC#60077
 *</pre>
 */
public class NSAL0440Constant {
    /** MAX_DATE */
    public static final String MAX_DATE = "29991231";

    /** Error Message: @ is found. */
    public static final String NSAM0353E = "NSAM0353E";

    /**
     * Warnig Message: Search results exceeded [@]. Only showing first @
     * records.
     */
    public static final String NSAM0024W = "NSAM0024W";

    /** Error Parameter */
    public static final String ERR_PRAM_NO_DATA = "No Data";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** Pulldown FirstData. */
    public static final String ALL = "All";

    /** Pulldown FirstDataValue. */
    public static final String ALL_VALUE = "--";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** SVC_TERM_COND. */
    public static final String SVC_TERM_COND = "SVC_TERM_COND";

    /** The process has been successfully completed. */
    public static final String NSAM0200I = "NSAM0200I";

    // START 2016/06/03 T.Tomita [QC#5489, ADD]
    // mod start 2018/06/25 QC#17369
    /** Pulldown Key Length : 50 */
    public static final int PULLDOWN_KEY_LENGTH = 50;
    // mod end 2018/06/25 QC#17369

    /** Pulldown Value Length : 50 */
    public static final int PULLDOWN_VAL_LENGTH = 50;

    /** Pulldown Row Number : 100 */
    public static final int PULLDOWN_ROW_NUM = 100;
    // END 2016/06/03 T.Tomita [QC#5489, ADD]

    // START 2022/08/03 N.Takatsu [QC#60077, ADD]
    /** List Icon Open */
    public static final String IMG_OPEN_ALL = "./img/wfcomp/S21WfPlus.gif";

    /** List Icon Close */
    public static final String IMG_CLOSE_ALL = "./img/wfcomp/S21WfMinus.gif";
    // END 2022/08/03 N.Takatsu [QC#60077, ADD]

    // START 2016/09/29 A.Kohinata [QC#12898, ADD]
    /** Terms&Conditions Fleet Capacity Inactive Flag */
    public static final String TERM_COND_FLT_CAP_INACT_FLG = "TERM_COND_FLT_CAP_INACT_FLG";

    /** Black/White cap original */
    public static final String TERM_COND_CAP_BW_ORG_ATTRB_NM = "TERM_COND_CAP_BW_ORG_ATTRB_NM";

    /** Color cap original */
    public static final String TERM_COND_CAP_CLR_ORG_ATTRB_NM = "TERM_COND_CAP_CLR_ORG_ATTRB_NM";

    /** Total cap original */
    public static final String TERM_COND_CAP_TOT_ORG_ATTRB_NM = "TERM_COND_CAP_TOT_ORG_ATTRB_NM";

    /** Black/White cap running */
    public static final String TERM_COND_CAP_BW_RUN_ATTRB_NM = "TERM_COND_CAP_BW_RUN_ATTRB_NM";

    /** Color cap running */
    public static final String TERM_COND_CAP_CLR_RUN_ATTRB_NM = "TERM_COND_CAP_CLR_RUN_ATTRB_NM";

    /** Total cap running */
    public static final String TERM_COND_CAP_TOT_RUN_ATTRB_NM = "TERM_COND_CAP_TOT_RUN_ATTRB_NM";
    // END 2016/09/29 A.Kohinata [QC#12898, ADD]
}
