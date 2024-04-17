/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0600.constant;

/**
 *<pre>
 * Cascade Date
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         T.Tomita        Create          N/A
 * 2023/08/28   Hitachi         S.Moriai        Update          QC#59846
 *</pre>
 */
public final class NSAL0600Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0600";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * Failed to update "@".
     */
    public static final String NSAM0031E = "NSAM0031E";

    /**
     * Failed to insert "@".
     */
    public static final String NSAM0032E = "NSAM0032E";

    // START 2023/08/28 S.Moriai [QC#59846, ADD]
    /**
     * "@" does not exist.
     */
    public static final String NSAM0045E = "NSAM0045E";
    // END 2023/08/28 S.Moriai [QC#59846, ADD]

    /**
     * Please enter [@].
     */
    public static final String NSAM0212E = "NSAM0212E";

    /**
     * You can not enter [@] earlier than [@].
     */
    public static final String NSAM0346E = "NSAM0346E";

    /**
     * @ is found.
     */
    public static final String NSAM0353E = "NSAM0353E";

    /**
     * Please click [@] and then click [@].
     */
    public static final String NSAM0403E = "NSAM0403E";

    /**
     * This data has been updated by another user.
     */
    public static final String NZZM0003E = "NZZM0003E";

    /**
     * Level 4
     */
    public static final String LVL_4 = "4";
}
