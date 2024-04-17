/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFBL2100.constant;

/**
 *<pre>
 * Lease Buyout Approve List
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/25   Hitachi         K.Kojima        Create          N/A
 * 2016/03/10   Hitachi         K.Kojima        Update          CSA QC#4998
 * 2016/03/11   Hitachi         K.Kojima        Update          CSA QC#4998
 *</pre>
 */
public class NFBL2100Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NFBL2100";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

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
     * Please set at least one search criteria.
     */
    public static final String NFBM0212E = "NFBM0212E";

    /**
     * @ doesn't exist in Vendor Master.
     */
    public static final String NFBM0133E = "NFBM0133E";

    // START 2016/03/11 K.Kojima [QC#4998,ADD]
    /**
     * The value in the 'To' field has to be equal to or greater than the 'From' field.
     */
    public static final String NFBM0215E = "NFBM0215E";

    // END 2016/03/11 K.Kojima [QC#4998,ADD]

    // START 2016/03/10 K.Kojima [QC#4998,ADD]
    /**
     * Search Condition From Time
     */
    public static final String SRCH_COND_FROM_TIME = "000000000";

    /**
     * Search Condition Thru Time
     */
    public static final String SRCH_COND_THRU_TIME = "235959999";
    // END 2016/03/10 K.Kojima [QC#4998,ADD]

}
