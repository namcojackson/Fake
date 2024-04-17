/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1620.constant;

/**
 *<pre>
 * NWAL1620Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/08   Fujitsu         C.Yokoi         Create          N/A
 * 2016/07/29   Fujitsu         T.Mizuki        Update          S21_NA#12607
 *</pre>
 */
public class NWAL1620Constant {

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** Order detail does not exists. */
    public static final String NWAM0694E = "NWAM0694E";

    /** Please enter "1" or a larger value. */
    public static final String NWAM0370E = "NWAM0370E";

    /** The value for @ must be less than or equal to @. */
    public static final String NWAM0695E = "NWAM0695E";

    /** Order does not exists. */
    public static final String NWAM0696E = "NWAM0696E";
    // mod start 2016/07/29 CSA S21_NA#12607
    /** Cannot Copy. This order is Credit Rebill Order. */
    public static final String NWAM0871E = "NWAM0871E";
    // mod end 2016/07/29 CSA S21_NA#12607

    // --------------------------------
    // MODE
    // --------------------------------
    /** COPY MODE: Copy(01) */
    public static final String COPY_MODE = "01";

    /** COPY MODE: Copy(02) */
    public static final String COPY_FROM_MODE = "02";

    /** MODE: Header Level(01) */
    public static final String HEADER_MODE = "01";

    /** MODE: Config Level(02) */
    public static final String CONFIG_MODE = "02";

    /** MODE: Line Level(03) */
    public static final String LINE_MODE = "03";

    // --------------------------------
    // Valiable
    // --------------------------------
    /** NumConst Table Name */
    public static final String NUMCONST_NUM_OF_COPY = "NWAL1620_NUM_OF_COPIES";

    /** Row Number */
    public static final String ROW_NUM_ONE = "1";

}
