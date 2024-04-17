/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0570.constant;

/**
 *<pre>
 * Overage Pricing Effectivity
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2015/02/16   Hitachi         K.Kishimoto     Update          QC#2063
 * 2016/04/11   Hitachi         K.Kishimoto     Update          QC#6728
 * 2016/05/20   Hitachi         T.Tomita        Update          QC#4923
 * 2017/02/21   Hitachi         K.Kishimoto     Update          QC#17646
 * 2019/11/25   Hitachi         K.Kitachi       Update          QC#54703
 * 2020/03/12   Hitachi         K.Kitachi       Update          QC#55662
 *</pre>
 */
public final class NSAL0570Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0570";

    /** Err Message*/
    public static final String NSAM0013E = "NSAM0013E";

    public static final String NZZM0001W = "NZZM0001W";

    public static final String NSAM0034E = "NSAM0034E";

    public static final String NSAM0112E = "NSAM0112E";

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

    public static final String NSAM0376E = "NSAM0376E";

    public static final String NSAM0388E = "NSAM0388E";

    /** Please don't delete all [@] . */
    public static final String NSAM0198E = "NSAM0198E";

    // Add Start 02/16/2016  <QC#2063>
    public static final String NSAM0045E = "NSAM0045E";
    // Add End   02/16/2016  <QC#2063>

    // START 2020/03/12 K.Kitachi [QC#55662, ADD]
    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";
    // END 2020/03/12 K.Kitachi [QC#55662, ADD]

    public static final String NSAM0032E = "NSAM0032E";

    // START 2016/05/20 T.Tomita [QC#4923, ADD]
    /** The process has been successfully completed. */
    public static final String NSAM0200I = "NSAM0200I";
    // END 2016/05/20 T.Tomita [QC#4923, ADD]
    // START 2017/02/21 K.Kishimoto [QC#17646, ADD]
    /**
     * allowance must be greater than above
     */
    public static final String NSAM0623E = "NSAM0623E";
    // END   2017/02/21 K.Kishimoto [QC#17646, ADD]

    // START 2019/11/25 K.Kitachi [QC#54703, ADD]
    /**
     * You cannot change this pricing effectivity because the customer incident of this contract is in progress.
     */
    public static final String NSAM0754E = "NSAM0754E";
    // END 2019/11/25 K.Kitachi [QC#54703, ADD]

    /**
     * SEARCH_LIMIT_CNT
     */

    public static final int SEARCH_LIMIT_CNT = 200;

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

}
