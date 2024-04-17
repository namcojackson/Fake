/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0560.constant;

/**
 *<pre>
 * Base Pricing Effectivity
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2015/12/03   Hitachi         A.Kohinata      Update          QC1427
 * 2015/12/11   Hitachi         T.Kanasaka      Update          QC#1439
 * 2016/02/01   Hitachi         T.Tomita        Update          CSA QC#2063
 * 2016/05/17   Hitachi         T.Tomita        Update          QC#3891
 * 2019/11/25   Hitachi         K.Kitachi       Update          QC#54703
 *</pre>
 */
public final class NSAL0560Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0560";

    /** Err Message*/
    public static final String NSAM0013E = "NSAM0013E";

    public static final String NZZM0001W = "NZZM0001W";

    public static final String NSAM0034E = "NSAM0034E";

    public static final String NSAM0112E = "NSAM0112E";

    public static final String NSAM0135E = "NSAM0135E";

    public static final String NSAM0368E = "NSAM0368E";

    public static final String NSAM0369E = "NSAM0369E";

    public static final String ZZZM9025E = "ZZZM9025E";

    public static final String NSAM0370E = "NSAM0370E";

    public static final String NSAM0062E = "NSAM0062E";

    public static final String NSAM0336E = "NSAM0336E";

    public static final String NSAM0371E = "NSAM0371E";

    public static final String NSAM0372E = "NSAM0372E";

    public static final String NSAM0373E = "NSAM0373E";

    public static final String NSAM0374E = "NSAM0374E";

    public static final String NSAM0375E = "NSAM0375E";

    public static final String NSAM0388E = "NSAM0388E";
    // START 2016/02/01 T.Tomita [QC#2063, ADD]
    public static final String NSAM0045E = "NSAM0045E";

    public static final String NZZM0003E = "NZZM0003E";

    public static final String NSAM0001E = "NSAM0001E";
    // END 2016/02/01 T.Tomita [QC#2063, ADD]

    // START 2019/11/25 K.Kitachi [QC#54703, ADD]
    /**
     * You cannot change this pricing effectivity because the customer incident of this contract is in progress.
     */
    public static final String NSAM0754E = "NSAM0754E";
    // END 2019/11/25 K.Kitachi [QC#54703, ADD]

    public static final String NSAM0200I = "NSAM0200I";

    /**
     * SEARCH_LIMIT_CNT
     */

    public static final int SEARCH_LIMIT_CNT = 200;

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

}
