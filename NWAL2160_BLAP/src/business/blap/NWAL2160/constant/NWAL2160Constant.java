/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2160.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NWAL2160Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL2160Constant {

    //--------------------------------
    // Message ID
    //--------------------------------
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

    /** Cannot add anymore details. */
    public static final String NWAM0100E = "NWAM0100E";

    /** @ should be start from @ . */
    public static final String NWAM0760E = "NWAM0760E";

    /** [@] should be greater than [@]. */
    public static final String NWAM0712E = "NWAM0712E";

    /** Number of Tier must be greater than 1 per billing counter. */
    public static final String NWAM0724E = "NWAM0724E";

    /** It has exceeded the maximum value (@). */
    public static final String NWAM0707E = "NWAM0707E";

    //--------------------------------
    /** PROC_MD_UPD */
    public static final String PROC_MD_UPD = "1";

    /** PROC_MD_REF */
    public static final String PROC_MD_REF = "2";

    //--------------------------------
    /** DUMMY_TIER_TP_CD for get the first tier */
    public static final String DUMMY_TIER_TP_CD = "00";

    //--------------------------------
    /** Maximum value of Range */
    public static final BigDecimal MAX_RANGE = new BigDecimal("999999999");

    /** Maximum value of Excess Per Image Charge */
    public static final BigDecimal MAX_XS_RATE = new BigDecimal("9.99999");

    //--------------------------------
    /** Fleet Model Name */
    public static final String MDL_NM_FLEET = "Fleet";
}
