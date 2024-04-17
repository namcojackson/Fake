/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0320.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            SRAA            Create          N/A
 * 2015/10/13   Hitachi         T.Tomita        Update          N/A
 * 2016/05/18   Hitachi         T.Tomita        Update          QC#4958
 * 2016/12/09   Hitachi         K.Kojima        Update          QC#16461
 * 2016/12/16   Hitachi         T.Mizuki        Update          QC#16399
 * 2017/02/10   Hitachi         Y.Takeno        Update          QC#17494
 * 2022/10/25   CITS            E.Sanchez       Update          QC#60661
 *</pre>
 */
public class NSAL0320Constant {

    /**
     * Failed to update "@".
     */
    public static final String NSAM0031E = "NSAM0031E";

    /**
     * Failed to insert "@".
     */
    public static final String NSAM0032E = "NSAM0032E";

    /**
     * Failed to delete "@".
     */
    public static final String NSAM0033E = "NSAM0033E";

    /**
     * NSAM0045E : @ does not exist.
     */
    public static final String NSAM0045E = "NSAM0045E";

    /**
     * System Error : @
     */
    public static final String NSAM0219E = "NSAM0219E";

    /**
     * Billing counters are not setup for the regular counter.
     */
    public static final String NSAM0313E = "NSAM0313E";

    /**
     * The counter : @ does not have the associate billing meter level @.
     */
    public static final String NSAM0314E = "NSAM0314E";

    /**
     * Please input @ field.
     */
    public static final String NSAM0315E = "NSAM0315E";

    /**
     * The multiplier must be between @ and @.
     */
    public static final String NSAM0316E = "NSAM0316E";

    /**
     * The multiplier must be incremented by @.
     */
    public static final String NSAM0317E = "NSAM0317E";

    /**
     * Multiple billing counter levels are not allowed. Please change
     * billing counters.
     */
    public static final String NSAM0318E = "NSAM0318E";

    /**
     * Multiplier must be unique within the same billing counters.
     */
// START 2017/02/10 [QC#17494, DEL]
//   public static final String NSAM0319E = "NSAM0319E";
// END   2017/02/10 [QC#17494, DEL]

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * No search results found.
     */
    public static final String ZZZM9005W = "ZZZM9005W";

    // START 2016/12/09 K.Kojima [QC#16461,ADD]
    /**
     * Service Model cannot be specified.
     */
    public static final String NSZM0569E = "NSZM0569E";

    // END 2016/12/09 K.Kojima [QC#16461,ADD]

    public static final String NSAL0320_MTR_MULT_RATE_FCT_NUM = "NSAL0320_MTR_MULT_RATE_FCT_NUM";

    public static final String NSAL0320_MTR_MULT_RATE_MIN_NUM = "NSAL0320_MTR_MULT_RATE_MIN_NUM";

    public static final String NSAL0320_MTR_MULT_RATE_MAX_NUM = "NSAL0320_MTR_MULT_RATE_MAX_NUM";

    public static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";

    public static final String SPCL_AGG_MDSE_CD = "SPCL_AGG_MDSE_CD";

    public static final String INVLD_BLLG_MTR_MAP_LVL_NUM = "0123456789";

    public static boolean INPUT_IS_VALID = true;

    public static boolean INPUT_IS_INVALID = false;

    // mod start 2016/12/16 CSA QC#16399
    public static final String MODE_22 = "22";

    // mod end 2016/12/16 CSA QC#16399

    // START 2022/10/25 E.Sanchez [QC#60661, ADD]
    public static final String MODE_10 = "10";
    // END 2022/10/25 E.Sanchez [QC#60661, ADD]

    // START 2015/10/13 T.Tomita [N/A, ADD]
    /**
     * Error Msg : An error occurred in API. <APIID: [@], Error Code:
     * [@], Data Key: [@]>
     */
    public static final String NSAM0003E = "NSAM0003E";

    /**
     * End Date : 99991231
     */
    public static final String END_DT = "99991231";
    // END 2015/10/13 T.Tomita [N/A, ADD]

}
