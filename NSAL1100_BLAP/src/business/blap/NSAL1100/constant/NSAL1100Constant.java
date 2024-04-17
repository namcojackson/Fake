/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1100.constant;

/**
 *<pre>
 * Approval List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Hitachi         A.Kohinata      Create          N/A
 * 2016/09/21   Hitachi         U.Kim           Update          QC#18526
 *</pre>
 */
public final class NSAL1100Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL1100";

    /** Screen ID */
    public static final String SCREEN_ID = "NSAL1100Scrn00";

    /** VAR_CAR_CONST Key : SVC_CR_REBIL_APVL_WF_ID */
    public static final String CONST_KEY_SVC_CR_REBIL_APVL_WF_ID = "SVC_CR_REBIL_APVL_WF_ID";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * Your request cannot be processed under this status.
     */
    public static final String NSAM0065E = "NSAM0065E";

    /**
     * [@] doesn't exist in the table [@].
     */
    public static final String NSAM0416E = "NSAM0416E";

    /** DATE_LENGTH */
    public static final int DATE_LENGTH = 8;
    
    // START 2017/09/21 U.Kim [QC#18526, ADD]
    /**
     * [@] is mandatory.
     */
    public static final String ZZZM9025E = "ZZZM9025E";
    
    // END 2017/09/21 U.Kim [QC#18526, ADD]

}
