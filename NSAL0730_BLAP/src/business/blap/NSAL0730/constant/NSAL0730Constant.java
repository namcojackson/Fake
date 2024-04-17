/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0730.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2015/12/07   Hitachi         T.Tsuchida      Update          QC#1542
 * 2015/12/07   Hitachi         T.Tsuchida      Update          QC#1577
 * 2017/02/27   Hitachi         K.Ochiai        Update          QC#4210
 * 2019/01/10   Hitachi         K.Kitachi       Update          QC#26928
 *</pre>
 */
public class NSAL0730Constant {

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /** No search results found. */
    public static final String NSAM0013E = "NSAM0013E";

    /** Please select at least 1 check box. */
    public static final String NSAM0015E = "NSAM0015E";

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** Return Message : Note Added */
    public static final String RTRN_MSG_SUCCESS = "Success.";

    /** Return Message : Failed to register Note */
    public static final String RTRN_MSG_FAILED = "Status Not Eligible.";

    /** The process has been successfully completed. */
    public static final String NSAM0200I = "NSAM0200I";

    /** The process has been error in some data. Please check the data. */
    public static final String NSAM0394W = "NSAM0394W";

    /** Level Number : 1 */
    public static final String LVL_NUM_1 = "1";

    /** Level Number : 1 */
    public static final String LVL_NUM_2 = "2";

    /** Level Number : 1 */
    public static final String LVL_NUM_3 = "3";

    /** BASE */
    public static final String MTR_LB = "BASE";

    /**
     *This data has been updated by another user.
     */
    public static final String NZZM0003E = "NZZM0003E";

    // START 2017/02/27 K.Ochiai [QC#4210, ADD]
    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";
    // END 2017/02/27 K.Ochiai [QC#4210, ADD]

    // START 2019/01/10 K.Kitachi [QC#26928, ADD]
    /**
     * [@] is duplicated.
     */
    public static final String NSAM0035E = "NSAM0035E";

    /**
     * If the "@" field is entered, please enter the "@" field as well.
     */
    public static final String NSAM0066E = "NSAM0066E";

    /**
     * @ cannot be added because it is exceeding the maximum number of row [@].
     */
    public static final String NSAM0320E = "NSAM0320E";

    /**
     * @ is mandatory value.
     */
    public static final String NSAM0645E = "NSAM0645E";

    /**
     * [@] must be greater than or equal to [@].
     */
    public static final String NSAM0743E = "NSAM0743E";

    /**
     * Business Id
     */
    public static final String BIZ_ID = "NSAL0730";
    // END 2019/01/10 K.Kitachi [QC#26928, ADD]
}
