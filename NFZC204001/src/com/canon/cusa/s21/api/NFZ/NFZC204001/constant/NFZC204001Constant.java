/**<pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NFZ.NFZC204001.constant;

/**
 * <pre>
 * NFZC204001Constant.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/31   Fujitsu         S.Yoshida       Create          N/A.
 * 2017/10/26   Fujitsu         K.Ishizuka      Update          QC#21977
 * 2019/05/10   Fujitsu         H.Ikeda         Update          QC#50140
 * 2023/02/02   Hitachi         S.Nakatani      Update          QC#60811
 * </pre>
 */
public class NFZC204001Constant {

    /** Mode : Unapply */
    public static final String MODE_UNAPPLY = "01";

    /** Mode : Apply */
    public static final String MODE_APPLY = "02";

    /** Mode : Flag Off Init */
    public static final String MODE_FLG_OFF_INIT = "03";

    /** Late Fee Title String */
    public static final String AR_CUST_REF_STR = "LF";
    
    /** Max Late Days*/
    public static final String MAX_LATE_DAYS = "999"; //S21_NA ADD QC#21977

    // Start 2023/2/02 S.Nakatani [QC#60811, ADD]
    /** Late Fee Prorated Days */
    public static final String LATE_FEE_PRORATED_DAYS = "LATE_FEE_PRORATED_DAYS";
    /** Round Down Decimal Point */
    public static final String ROUND_DOWN_DECIMAL_POINT = "ROUND_DOWN_DECIMAL_POINT";
    // End 2023/2/02 S.Nakatani [QC#60811, ADD]

    //--------------
    // Message ID
    //--------------
    /** Global Company Code is a mandatory field. */
    public static final String NFZM0001E = "NFZM0001E";
    /** Sales Date is a mandatory field. */
    public static final String NFZM0002E = "NFZM0002E";
    /** AR Statement Date is a mandatory field. */
    public static final String NFZM0003E = "NFZM0003E";
    /** Sales Date is an invalid date. */
    public static final String NFZM0005E = "NFZM0005E";
    /** AR Statement Date is an invalid date. */
    public static final String NFZM0006E = "NFZM0006E";
    /** It does not exist in Payment Term Cash Discount. */
    public static final String NFZM0008E = "NFZM0008E";
    /** It does not exist in Payment Term. */
    public static final String NFZM0009E = "NFZM0009E";
    /** It does not exist in AR Statement Control Issued Cycle. */
    public static final String NFZM0010E = "NFZM0010E";
    /** It does not exist in AR Grace Period. */
    public static final String NFZM0012E = "NFZM0012E";
    /** It failed to register. */
    public static final String NFZM0013E = "NFZM0013E";
    /** It does not exist in Currency. */
    public static final String NFZM0015E = "NFZM0015E";
    /** It does not exist in AR Statement Control. */
    public static final String NFZM0016E = "NFZM0016E";
    /** Could not get the "From Address". */
    public static final String NFZM0018E = "NFZM0018E";
    /** Could not get the "To Address". */
    public static final String NFZM0019E = "NFZM0019E";
    /** Could not get the "Mail Template". */
    public static final String NFZM0020E = "NFZM0020E";

    //--------------
    // Report
    //--------------
    /** Late fee Report ID */
    public static final String LF_RPT_ID = "NFCF0020";

    // START 2019/05/10 H.Ikeda [QC#50140,ADD]
    /** Commit Count */
    public static final int COMMIT_CNT = 1000;
    // EMD   2019/05/10 H.Ikeda [QC#50140,ADD]

}
