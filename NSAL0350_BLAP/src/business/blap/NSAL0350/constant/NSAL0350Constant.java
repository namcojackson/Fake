/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0350.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/10   CUSA            Fujitsu         Create          N/A
 * 2017/08/07   Hitachi         K.Kitachi       Update          QC#20048
 * 2017/09/08   Hitachi         U.Kim           Update          QC#20071
 * 2018/06/29   Hitachi         U.KIm           Update          QC#26933
 *</pre>
 */
public class NSAL0350Constant {

    /** Failed to update @ [@]. */
    public static final String NSAM0001E = "NSAM0001E";

    /** "@" does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** Search results exceeded [@]. Only showing first @ records. */
    public static final String NSAM0024W = "NSAM0024W";

    /** @ is found. */
    public static final String NSAM0353E = "NSAM0353E";

    /** @ is not valid. */
    public static final String NSAM0354E = "NSAM0354E";

    /** Recover type cannot be selected at the final billing schedule. */
    public static final String NSAM0355E = "NSAM0355E";

    /** Search @ results exceeded [@]. Only showing first @ records. */
    public static final String NSAM0360W = "NSAM0360W";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** BASE_TBL_NM */
    public static final String BASE_TBL_NM = "O";

    /** USAGE_TBL_NM_ARRAY */
    public static final String[] USAGE_TBL_NM_ARRAY = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

    // START 2017/08/07 K.Kitachi [QC#20048, ADD]
    /** No search results found. */
    public static final String NSAM0194I = "NSAM0194I";

    /** The process has been successfully completed. */
    public static final String NSAM0200I = "NSAM0200I";
    // END 2017/08/07 K.Kitachi [QC#20048, ADD]

    // START 2017/09/08 U.Kim [QC#20071, ADD]
    /** Bill with Equipment*/
    public static final String BILL_WITH_EQUIP_INV_STS_NM = "BILL_WITH_EQUIP_INV_STS_NM";
    // END 2017/09/08 U.Kim [QC#20071, ADD]

    // START 2018/06/29 U.Kim [QC#26933, ADD]
    /** Number : 200*/
    public static final int INT_200 = 200;
    // END 2018/06/29 U.Kim [QC#26933, ADD]
}
