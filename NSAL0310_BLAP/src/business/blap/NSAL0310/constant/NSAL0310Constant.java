/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0310.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/26   CUSA            SRAA            Create          N/A
 * 2016/03/03   Hitachi         T.Tomita        Update          CSA Defect#1720
 * 2016/05/24   Hitachi         T.Mizuki        Update          QC#447
 * 2017/03/01   Hitachi         N.Arai          Update          QC#17620
 * 2017/09/04   Hitachi         U.Kim           Update          QC#20856
 * 2018/10/30   Hitachi         K.Kim           Update          QC#28890
 * 2023/04/21   Hitachi         K.Watanabe      Update          QC#61146
 *</pre>
 */
public class NSAL0310Constant {
    /**
     * Business Id
     */
    public static final String BIZ_ID = "NSAL0310";

    /**
     * Detail Status: Collapsed
     */
    public static final String DTL_STS_CLPS = "C";

    /**
     * Expand button name
     */
    public static final String DTL_STS_XPND = "E";

    /**
     * @ does not exist.
     */
    public static final String NSAM0045E = "NSAM0045E";

    // START 2018/10/30 [QC#28890, ADD]
    /** The entered '@' does not exist. */
    public static final String NSAM0072E = "NSAM0072E";
    // END 2018/10/30 [QC#28890, ADD]

    /**
     * Parameter "@" is not set.
     */
    public static final String NSAM0131E = "NSAM0131E";

    /**
     * Please select Machines.
     */
    public static final String NSAM0310E = "NSAM0310E";

    // del start 2016/03/03 CSA Defect#1720
//    /**
//     * Please correct the Effective Date.
//     */
//    public static final String NSAM0311E = "NSAM0311E";
    // del end 2016/03/03 CSA Defect#1720

    /**
     * Please select up to @ items.
     */
    public static final String NSAM0312E = "NSAM0312E";

    /**
     * Ship To is not entered.
     */
    public static final String NWZM0201E = "NWZM0201E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * No search results found.
     */
    public static final String ZZZM9005W = "ZZZM9005W";

    // add start 2016/03/03 CSA Defect#1720
    /**
     * If the "@" field is entered, please enter the "@" field as
     * well.
     */
    public static final String NSAM0066E = "NSAM0066E";

    /**
     * You can not enter [@] earlier than [@].
     */
    public static final String NSAM0346E = "NSAM0346E";

    /**
     * You can not enter [@] later than [@].
     */
    public static final String NSAM0347E = "NSAM0347E";
    // add end 2016/03/03 CSA Defect#1720
    // Start 2016/05/24 T.Mizuki
    /**
     * The EOL has passed. Can not add the Serial# @}.
     */
    public static final String NSAM0478E = "NSAM0478E";
    // End 2016/05/24 T.Mizuki

    // START 2017/03/01 N.Arai [QC#17620, MOD]
    /** field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    // END 2017/03/01 N.Arai [QC#17620, MOD]

    // START 2017/09/04 U.Kim [QC#20856, ADD]
    /**
     * There is no Machine that makes a connection to Accessory. If
     * you want to make a connection to any Machine, please check the
     * appropriate Machine.
     */
    public static final String NSAM0697E = "NSAM0697E";
    // END 2017/09/04 U.Kim [QC#20856, ADD]

    // START 2023/04/21 K.Watanabe [QC#61146, ADD]
    /** SMSG_MAX_CNT */
    public static final int SMSG_MAX_CNT = 10000;

    /** LIMIT_SEARCH */
    public static final int LIMIT_SEARCH = 2000;

    /** LIMIT_DOWNLOAD */
    public static final int LIMIT_DOWNLOAD = -1;
    // END 2023/04/21 K.Watanabe [QC#61146, ADD]
}
