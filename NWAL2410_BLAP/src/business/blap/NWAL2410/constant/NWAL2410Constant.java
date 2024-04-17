/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2410.constant;

/**
 *<pre>
 * NWAL2410Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/25   Fujitsu         N.Aoyama        Create          QC#16740
 *</pre>
 */
public class NWAL2410Constant {

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** [@] is already registered. */
    public static final String NWAM0923E = "NWAM0923E";

    /** Details cannot be added because the number will exceed [@]. */
    public static final String NWAM0924E = "NWAM0924E";

    /** @ is duplicated. */
    public static final String NWAM0925E = "NWAM0925E";

    /** It failed to update [@]. */
    public static final String NWAM0926E = "NWAM0926E";

    /** Please select item(s). */
    public static final String NWAM0927E = "NWAM0927E";

    /** It failed to transmission into Therefore. */
    public static final String NWAM0928W = "NWAM0928W";

    /** The entered @ does not exist in Master or "Inactive" item. */
    public static final String NWAM0181E = "NWAM0181E";

    // --------------------------------
    // PROC_MODE_CD
    // --------------------------------

    /** PROC_MODE_CD Create */
    public static final String PROC_MODE_CD_CRT = "C";

    /** PROC_MODE_CD Update */
    public static final String PROC_MODE_CD_UPD = "U";

    /** PROC_MODE_CD Delete */
    public static final String PROC_MODE_CD_DEL = "D";

    // --------------------------------
    // OTHER
    // --------------------------------
    /** Date Pattan */
    public static final int DATE_TIME_LENGTH = 17;

    /** TS format yyyyMMddHHmmssSSS */
    public static final String FORMAT_TIMESTAMP = "yyyyMMddHHmmssSSS";

    /** "KeyCode(LOB, Branch Code)" */
    public static final String KEY_CODE_DISP_PK = "KeyCode(LOB, Branch Code)";

    /** "Code " */
    public static final String DISP_CODE = "Code";

    /** "Psn Code  " */
    public static final String PSN_CODE = "Psn Code";

    /** DOC_MGT_CATG table search key (DOC_MGT_CATG_SRCH_KEY_NM) */
    public static final String MAINTENANCE_BRANCH = "MAINTENANCE_BRANCH";

}
