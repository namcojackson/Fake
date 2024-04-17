/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0470.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         K.Yamada        Create          N/A
 * 2016/02/19   Hitachi         K.Kasai         Update          QC#3689
 * 2016/04/21   Hitachi         A.Kohinata      Update          QC1088
 * 2017/07/24   Hitachi         K.Kasai         Update          QC#18882
 * 2018/05/10   Hitachi         U.Kim           Update          QC#25482
 * 2019/01/15   Hitachi         K.Kitachi       Update          QC#30080
 *</pre>
 */
public class NSAL0470Constant {

    /** Download Limit */
    public static final int DOWNLOAD_LIMIT_CNT = 65000;

    /** Fetch size for Download */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Max memo size */
    public static final int SVC_MEMO_SIZE = 4000;

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0470";

    /**No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /**The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /**This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /**Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /**Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    // START 2018/05/10 U.Kim [QC#25482, ADD]
    /**Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";
    // END 2018/05/10 U.Kim [QC#25482, ADD]

    /**This validation does not allow you to override the outcome as success.[@] */
    public static final String NSAM0393E = "NSAM0393E";

    //START 2017/07/24 K.Kasai [QC#18882,ADD]
    /**To ignore the warning, please click "Complete Contract" button again. */
    public static final String NSAM0689W = "NSAM0689W";
    //END 2017/07/24 K.Kasai [QC#18882,ADD]

    // add start 2016/02/22 CSA Defect#3689
    /**
     * VAR_CHAR_CONST:SVC_MEMO_RSN_FOR_CPLT_CONTR
     */
    public static final String SVC_MEMO_RSN_FOR_CPLT_CONTR = "SVC_MEMO_RSN_FOR_CPLT_CONTR";
    // add end 2016/02/22 CSA Defect#3689

    // START 2016/04/21 [QC1088, ADD]
    /** @ does not exist in @. */
    public static final String NSAM0063E = "NSAM0063E";

    /** An error occurred in Workflow Process. */
    public static final String NSAM0459E = "NSAM0459E";

    /** VAR_CHAR_CONST:SVC_ORG_FUNC_ROLE_TP_SUPERVR  */
    public static final String SVC_ORG_FUNC_ROLE_TP_SUPERVR = "SVC_ORG_FUNC_ROLE_TP_SUPERVR";

    /** VAR_CHAR_CONST:SVC_ORG_FUNC_ROLE_TP_REP  */
    public static final String SVC_ORG_FUNC_ROLE_TP_REP = "SVC_ORG_FUNC_ROLE_TP_REP";

    /** Workflow process name */
    public static final String WF_PROCESS_NM = "NSWP0001";

    /** Max Date */
    public static final String MAX_DATE = "29991231";
    // END 2016/04/21 [QC1088, ADD]

    // START 2018/05/10 U.Kim [QC#25482, ADD]
    /** Workflow process name : Preview Billng */
    public static final String WF_PROCESS_NM_PREV_BLLG = "NSWP0002";

    /** Workflow process name : Negative Billing */
    public static final String WF_PROCESS_NM_NEGA_BLLG = "NSWP0005";

    /** Prefix Document Id : Regular */
    public static final String PREFIX_DOC_ID_REG = "M";

    /** Prefix Document Id : Fleet */
    public static final String PREFIX_DOC_ID_FLT = "C";

    /** Prefix Document Id : Aggregate */
    public static final String PREFIX_DOC_ID_AGG = "A";

    /** It failed to cancel workflow. */
    public static final String NSZM0866E = "NSZM0866E";
    // END 2018/05/10 U.Kim [QC#25482, ADD]

    // START 2019/01/25 K.Kitachi [QC#30080, ADD]
    /** Level Number : 1 */
    public static final String LVL_NUM_1 = "1";

    /** Number of Months : 12 */
    public static final int NUM_OF_MTH_12 = 12;

    /** Number of 6 */
    public static final int NUM_OF_6 = 6;
    // END 2019/01/25 K.Kitachi [QC#30080, ADD]
}
