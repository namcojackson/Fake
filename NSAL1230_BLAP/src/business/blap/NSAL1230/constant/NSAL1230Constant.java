/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1230.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/10   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/23   Hitachi         Y.Takeno        Update          QC#2728
 * 2016/08/04   Hitachi         T.Mizuki        Update          QC#12767
 * 2018/04/10   CITS            T.Wada          Update          QC#23378(Sol#320)
 * 2018/05/07   Hitachi         U.Kim           Update          QC#25895
 *</pre>
 */
public class NSAL1230Constant {

    /** business ID. */
    public static final String BUSINESS_ID = "NSAL1230";

    /** delimiter string */
    public static final String VAR_CHAR_KEY_DELIMITER = "CONT_DELIMITER";

    /** fixed value COA_BR_CD */
    public static final String COA_BR_CD = "XXX";

    /** default percentage */
    public static final BigDecimal DEFAULT_PERCENTAGE = new BigDecimal("100.00");

    /** check box */
    public static final String XX_CHK_BOX_A1 = "xxChkBox_A1";

    /** [@] does not exist in Master. */
    public static final String NSAM0011E = "NSAM0011E";

    /** Please select at least 1 checkbox. */
    public static final String NSAM0015E = "NSAM0015E";

    /** No target data exists. */
    public static final String NSAM0020E = "NSAM0020E";

    /** [@] is duplicated. */
    public static final String NSAM0035E = "NSAM0035E";

    /** The format of [@] is incorrect. */
    public static final String NSAM0206E = "NSAM0206E";

    /** Number of digits over error (item name [@]). */
    public static final String NSAM0209E = "NSAM0209E";

    /**
     * @ cannot be added because it is exceeding the maximum number of
     * row [@].
     */
    public static final String NSAM0320E = "NSAM0320E";

    /** Total percent must be 100%. */
    public static final String NSAM0402E = "NSAM0402E";

// START 2016/03/23 Y.Takeno [QC#2728, ADD]
    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";
// END  2016/03/23 Y.Takeno [QC#2728, ADD]

    /** The process has been successfully completed. */
    public static final String NSAM0200I = "NSAM0200I";

    /** message parameter : Segment */
    public static final String MSG_PARAM_SEGMENT = "Segment";

    /** message parameter : Company */
    public static final String MSG_PARAM_CMPY = "Company";

    /** message parameter : Extension */
    public static final String MSG_PARAM_EXTN = "Extension";

    /** message parameter : Cost Center */
    public static final String MSG_PARAM_CC = "Cost Center";

    /** message parameter : Account */
    public static final String MSG_PARAM_ACCT = "Account";

    /** message parameter : Company */
    public static final String MSG_PARAM_PROJ = "Project";

    /** message parameter : Product */
    public static final String MSG_PARAM_PROD = "Product";

    /** message parameter : Affiliate */
    public static final String MSG_PARAM_AFFL = "Affiliate";

    /** message parameter : Channel */
    public static final String MSG_PARAM_CH = "Channel";

    /** segment element length : COA_CMPY_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CMPY = 3;

    /** segment element length : COA_EXTN_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_EXTN = 3;

    /** segment element length : COA_CC_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CC = 6;

    /** segment element length : COA_ACCT_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_ACCT = 8;

    /** segment element length : COA_PROJ_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_PROJ = 2;

    /** segment element length : COA_PROD_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_PROD = 2;

    /** segment element length : COA_AFFL_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_AFFL = 3;

    /** segment element length : COA_CH_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CH = 3;

    /** segment token list size. */
    public static final int SEGMENT_TOKEN_LIST_SIZE = 9;
    // mod start 2016/08/04 CSA QC#12767
    /** segment token list index : COA_CMPY_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD = 0;

    /** segment token list index : COA_BR_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD = 1;

    /** segment token list index : COA_CC_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD = 2;

    /** segment token list index : COA_ACCT_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD = 3;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD = 4;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD = 5;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD = 6;

    /** segment token list index : COA_PROJ_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD = 7;

    /** segment token list index : COA_EXTN_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD = 8;
    // mod end 2016/08/04 CSA QC#12767
    /** segment string minimum length */
    public static final int SEGMENT_STRING_MIN_LENGTH = 11;

    // QC#23378(Sol#320) Add Start
    /** Total price must be [@].. */
    public static final String NSAM0715E = "NSAM0715E";
    /** SVC_ACCT_DIST_CATG_NM : 'Other' */
    public static final String SVC_ACCT_DIST_CATG_NM_OTHER = "Other";
    // QC#23378(Sol#320) Add End

    // START 2018/05/07 U.Kim [QC#, ADD]
    /** [@] cannot be entered when [@] is "@". */
    public static final String NSAM0448E = "NSAM0448E";

    /** Price */
    public static final String PRICE = "Price";

    /** Total Price */
    public static final String TOTALPRICE = "Total Price";

    // END 2018/05/07 U.Kim [QC#, ADD]

}
