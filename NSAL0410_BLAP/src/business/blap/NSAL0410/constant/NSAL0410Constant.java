/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0410.constant;

/**
 *<pre>
 * Additional Charge
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/24   Fujitsu         C.Naito         Create          N/A
 * 2015/11/26   Hitachi         T.Tomita        Update          N/A
 *</pre>
 */
public class NSAL0410Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_ID = "NSAL0410";

    /**
     * Function ID UPDATE
     */
    public static final String FUNC_ID_UPDATE = "NSAL0410T020";

    /**
     * Function ID REFERENCE
     */
    public static final String FUNC_ID_REFER = "NSAL0410T010";

    /**
     * Max Date Value
     */
    public static final String MAX_DT_VAL = "99991231";

    /* Message */
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] field exceeded maximum value. */
    public static final String ZZM9002E = "ZZM9002E";

    /** [@] field is less than minimum value. */
    public static final String ZZM9003E = "ZZM9003E";

    /** [@] field has too many decimal digits entered. */
    public static final String ZZM9015E = "ZZM9015E";

    /** 
     * You can not select Frequency lower than Contract's Frequency. 
     */
    public static final String NSAM0344E = "NSAM0344E";

    /** 
     * Combination of Serial# and ChargeType is duplicated.
     */
    public static final String NSAM0345E = "NSAM0345E";

    /**
     * This data has been updated by another user.
     */
    public static final String NZZM0003E = "NZZM0003E";

    /**
     * For already full line, cannot be registered.
     */
    public static final String NSAM0342E = "NSAM0342E";

    /**
     * Failed to update "@"
     */
    public static final String NSAM0032E = "NSAM0032E";

    /**
     * Failed to insert "@"
     */
    public static final String NSAM0031E = "NSAM0031E";

    /**
     * You can not enter [@] earlier than than [@].
     */
    public static final String NSAM0346E = "NSAM0346E";

    /**
     * You can not enter [@] later than than [@].
     */
    public static final String NSAM0347E = "NSAM0347E";

    /**
     * Failed to delete "@"
     */
    public static final String NSAM0033E = "NSAM0033E";

    /**
     * The corresponding data does not exist. Table Name : [@]
     */
    public static final String NSAM0348E = "NSAM0348E";

    /**
     * [@] is not found.
     */
    public static final String ZZZM9006E = "ZZZM9006E";

    /**
     * If you specify @ as bill by type, you cannot specify @.
     */
    public static final String NSAM0395E = "NSAM0395E";
}
