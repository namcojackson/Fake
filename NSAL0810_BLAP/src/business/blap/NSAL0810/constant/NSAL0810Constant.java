/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0810.constant;

/**
 *<pre>
 * Ds Contract Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/02/16   Hitachi         Y.Takeno        Update          QC#4006
 * 2016/04/01   Hitachi         T.Iwamoto       Update          QC#6335
 *</pre>
 */
public class NSAL0810Constant {

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** The process completed successfully . */
    public static final String NZZM0002I = "NZZM0002I";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

 // START 2016/02/16 [QC#4006, ADD]
    /** Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";
 // END  2016/02/16 [QC#4006, ADD]

    /** It failed to insert the [@]. PK [@] */
    public static final String NACM0746E = "NACM0746E";

    /** It failed to update the [@]. PK [@] */
    public static final String NACM0747E = "NACM0747E";

    /** It failed to delete the [@]. PK [@] */
    public static final String NACM0748E = "NACM0748E";

    /** [@] does not exist in [@]. */
    public static final String NFAM0066E = "NFAM0066E";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please select at least 1 checkbox. */
    public static final String NSAM0015E = "NSAM0015E";

    /** Select one Interface Bat#. */
    public static final String NSAM0408E = "NSAM0408E";

    /** You cannot move [@]. Please click the [Save] or [Resubmit Bat Interface] or [Resubmit Interface] button. */
    public static final String NSAM0409E = "NSAM0409E";

    /** All detail lines will be validated. Would you like to continue? If OK, push button again. */
    public static final String NSAM0405W = "NSAM0405W";

    /** All detail lines will be registered. Would you like to continue? If OK, push button again. */
    public static final String NSAM0406W = "NSAM0406W";

    /** DS_CONTR_INTFC */
    public static final String DS_CONTR_INTFC = "DS Contract Interface";

// START 2016/02/16 [QC#4006, ADD]
    /** DS_ACTL_CNT_INTFC */
    public static final String DS_ACTL_CNT_INTFC = "DS Actual Count Interface";

    /** DS_XS_COPY_INTFC */
    public static final String DS_XS_COPY_INTFC = "DS Excess Copy Interface";

    /** DS_ADDL_CHRG_INTFC */
    public static final String DS_ADDL_CHRG_INTFC = "DS Additional Charge Interface";

    /** PRC_ALLOC_INTFC */
    public static final String PRC_ALLOC_INTFC = "Price Allocation Interface";
// END   2016/02/16 [QC#4006, ADD]

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0810";

    /** SCRN_ID : NSAL0810Scrn00 */
    public static final String SCRN_ID = "NSAL0810Scrn00";

    /** LIMIT_DOWNLOAD:65000 */
    public static final int LIMIT_DOWNLOAD = 65000;

    /** LENGTH:30 */
    public static final int LENGTH_30 = 30;

    /** MAX_THRU_DATE:29991231 */
    public static final String MAX_THRU_DATE = "29991231";

    /** RTNCD_NORMAL */
    public static final String RTNCD_NORMAL = "0000";

    /** The Upload File is empty or only has a header line, therefore it could not be processed. Please confirm the content of the  Upload file. */
    public static final String ZYEM0004E = "ZYEM0004E";

    /** The number of items Unjust error. */
    public static final String NSAM0208E = "NSAM0208E";

    /** Number of  digits over error (item name [@]). */
    public static final String NSAM0209E = "NSAM0209E";

    /** Item classification error (item name [@]). */
    public static final String NSAM0210E = "NSAM0210E";

    /** The number for this process exceeds the maximum numbers for display and can not process. */
    public static final String NSAM0214E = "NSAM0214E";

    /** Screen name NSAL0820 */
    public static final String SCREEN_NAME_NSAL0820 = "Actual Counters";

    /** Screen name NSAL0830 */
    public static final String SCREEN_NAME_NSAL0830 = "Tiers";

    /** Screen name NSAL0840 */
    public static final String SCREEN_NAME_NSAL0840 = "Additional Charges";

    /** Screen name NSAL0850 */
    public static final String SCREEN_NAME_NSAL0850 = "Sales Credits";

    /** mode (After Open For Correction) */
    public static final String MODE_AFTER_OPEN = "02";

    /** BLLG_MTR_LB_NM MAX LENGTH */
    public static final int BLLG_MTR_LB_NM_MAX_LENGTH = 30;

 // ADD start 2016/04/01 CSA Defect#6335
    /** leftpadding length 6 */
    public static final int LPAD_LEN = 6;

    /** leftpadding by 0 */
    public static final String PAD_STR = "0";
 // ADD end 2016/04/01 CSA Defect#6335
}
