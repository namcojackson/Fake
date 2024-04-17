/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1640.constant;

/**
 *<pre>
 * NWAL1640Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 * 2016/05/20   SRAA            Y.Chen          Update          QC#4505
 *</pre>
 */
public class NWAL1640Constant {

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The entered @ does not exist in Master. */
    public static final String NWAM0181E = "NWAM0181E";

    /** An error has occurred in the called API [@]. */
    public static final String NWAM0189E = "NWAM0189E";

    /** Country Code is required. */
    public static final String NWZM0369E = "NWZM0369E";

    /** Address has been modified to suggested address. */
    public static final String NWZM1734W = "NWZM1734W";

    /** The post code is invalid. */
    public static final String NWZM1735E = "NWZM1735E";

    /** The state is invalid. */
    public static final String NWZM1736E = "NWZM1736E";

    /** The adress 1 is invalid. */
    public static final String NWZM1737E = "NWZM1737E";

    /** The city is invalid. */
    public static final String NWZM1738E = "NWZM1738E";

    // --------------------------------
    // Valiable
    // --------------------------------
    /** Address Validation API. */
    public static final String ADDR_VALID_API = "Address Validation API";
    
    // QC#4505
    /** The corresponding [@] does not exist. */
    public static final String NMAM0039E = "NMAM0039E";
}
