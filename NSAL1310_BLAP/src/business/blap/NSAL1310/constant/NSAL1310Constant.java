/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1310.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/31   Hitachi         K.Kojima        Create          QC#15136
 * 2016/12/08   Hitachi         K.Kojima        Update          QC#14204
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 *</pre>
 */
public class NSAL1310Constant {

    // START 2016/12/08 K.Kojima [QC#14204,ADD]
    /** Message ID : NSAM0031E */
    public static final String NSAM0031E = "NSAM0031E";

    /** Message ID : NSAM0032E */
    public static final String NSAM0032E = "NSAM0032E";

    // END 2016/12/08 K.Kojima [QC#14204,ADD]

    /** Message ID : NSAM0035E */
    public static final String NSAM0035E = "NSAM0035E";

    /** Message ID : NSAM0081E */
    public static final String NSAM0081E = "NSAM0081E";

    /** Message ID : NSAM0456E */
    public static final String NSAM0456E = "NSAM0456E";

    /** Message ID : NSAM0457E */
    public static final String NSAM0457E = "NSAM0457E";

    /** Message ID : NZZM0003E */
    public static final String NZZM0003E = "NZZM0003E";

    /** Message ID : ZZZM9001E */
    public static final String ZZZM9001E = "ZZZM9001E";

    public static final String NZZM0001W = "NZZM0001W";

    /** Max Date String */
    public static final String MAX_DATE = "99991231";

    /** SQL Column Name : SVC_TERM_COND_DATA_VAL_CD */
    public static final String SVC_TERM_COND_DATA_VAL_CD = "SVC_TERM_COND_DATA_VAL_CD";

    /** SQL Column Name : SVC_TERM_COND_DATA_DISP_TXT */
    public static final String SVC_TERM_COND_DATA_DISP_TXT = "SVC_TERM_COND_DATA_DISP_TXT";

    /** SQL Column Name : TARGET_CD */
    public static final String TARGET_CD = "TARGET_CD";

    /** SQL Column Name : TARGET_NAME */
    public static final String TARGET_NAME = "TARGET_NAME";

    // mod start 2018/06/25 QC#17369
    /** Pull Down Key Length : 50 */
    public static final int PULLDOWN_KEY_LENGTH = 50;
    // mod end 2018/06/25 QC#17369

    /** Pull Down Value Length : 50 */
    public static final int PULLDOWN_VAL_LENGTH = 50;

    /** Pull Down Row Number : 99 */
    public static final int PULLDOWN_ROW_NUM = 99;
}
