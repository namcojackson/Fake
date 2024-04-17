/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL2610.constant;

/**
 * <pre>
 * NFCL2610Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 * 2016/04/12   Hitachi         K.Kojima        Update          CSA QC#6871
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#4758
 * 2016/05/13   Hitachi         K.Kojima        Update          QC#7796
 * 2016/11/07   Hitachi         J.Kim           Update          QC#15758
 * 2017/01/13   Fujitsu         T.Murai         Update          QC#16941
 * 2020/05/08   Fujitsu         H.Mizukami      Update          QC#56436
 * 2020/06/29   CITS            R.Kurahashi     Update          QC#57119
 * 2022/04/21   CITS            K.Suzuki        Update          QC#59333
 * 2022/07/25   Hitachi         A.Kohinata      Update          QC#57417
 * </pre>
 */
public class NFCL2610Constant {

    /** SEPARATOR */
    public static final String SEPARATOR = ",";

    /** [@] field is mandatory. */
    public static final String NFBM0214E = "NFBM0214E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** Please check at least 1 checkbox. */
    public static final String NZZM0011E = "NZZM0011E";

    /** Please select only one Check Box. */
    public static final String NZZM0009E = "NZZM0009E";

    /** Substring Length : 4 */
    public static final int SUBSTRING_LEN4 = 4;

    /** Substring Length : 6 */
    public static final int SUBSTRING_LEN6 = 6;

    /** Bill to Customer Account */
    public static final String BILL_TO_ACCOUNT = "BILL_TO_ACCOUNT";

    /** Bill to Customer */
    public static final String BILL_TO_CUSTOMER = "BILL_TO_CUSTOMER";

    /** Date Format(yyyyMMdd) */
    public static final String DT_FORMAT_TS = "yyyyMMdd";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * The @ has been updated by another user. Please re-open the screen and try again.
     */
    public static final String NFCM0794E = "NFCM0794E";

    /**
     * The data @ does not exist in the master.
     */
    public static final String NFCM0502E = "NFCM0502E";

    /**
     * Data insert failure. (table name is [@])
     */
    public static final String NFCM0782E = "NFCM0782E";

    // START 2016/05/13 K.Kojima [QC#7796,ADD]
    /**
     * Please select a Supplier radio button.
     */
    public static final String NFCM0843E = "NFCM0843E";

    // END 2016/05/13 K.Kojima [QC#7796,ADD]

    // START 2017/01/13  [QC#16941,ADD]
    /** Target data is already requested to refund. */
    public static final String NFCM0875E = "NFCM0875E";
    // END   2017/01/13 [QC#16941,ADD]

    /**
     * AR_RF_RQST
     */
    public static final String AR_RF_RQST = "AR_RF_RQST";

    /**
     * AR_RF_RQST
     */
    public static final String AR_RF_RQST_DTL = "AR_RF_RQST_DTL";

    /**
     * Default Bill/Ship to
     */
    public static final String PROCESS_MODE = "04";

    // START 2016/04/12 K.Kojima [QC#6871,ADD]
    /** Workflod Id */
    public static final String AR_REFUND_WF_ID = "AR_REFUND_WF_ID";

    /** Unexpected error has occurred. */
    public static final String NFCM0624E = "NFCM0624E";
    // END 2016/04/12 K.Kojima [QC#6871,ADD]

    // START 2016/11/07 J.Kim [QC#15758,ADD]
    /** CFS_COA_AFFL_CD */
    public static final String CFS_COA_AFFL_CD = "CFS_COA_AFFL_CD";
    // END 2016/11/07 J.Kim [QC#15758,ADD]

    // START 2020/05/12 [QC#56436,ADD]
    /** DEF_COA_AFFL_CD */
    public static final String DEF_COA_AFFL_CD = "DEF_COA_AFFL_CD";

    /** Blank */
    public static final String BLANK = "";

    /** [Chart of Account Affiliate Code] does not exist in [COA_AFFL_CD]. */
    public static final String NFCM0914E = "NFCM0914E";
    // END  2020/05/12 [QC#56436,ADD]
    
    // START 2020/06/29 R.Kurahashi [QC#57119,ADD]
    /** [Vendor Site Code] does not exist in [VND_CD_TXT]. */
    public static final String NFCM0915E = "NFCM0915E";
    // END 2020/06/29 R.Kurahashi [QC#57119,ADD]

    // START 2022/04/21 K.Suzuki [QC#59333,ADD]
    /** This On Account is partially applied. Please unapply all first.. */
    public static final String NFCM0919E = "NFCM0919E";
    // END   2022/04/21 K.Suzuki [QC#59333,ADD]

    // add start 2022/07/25 QC#57417
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    // add end 2022/07/25 QC#57417
    
    // START 2023/06/13 S.Fujita [QC#61486,ADD]
    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NFCL2610";

    /** Function ID NFCL2610T030 (Update for Collection Reps) */
    public static final String FUNC_ID_CLT_REPS = "NFCL2610T030";
    // END 2023/06/13 S.Fujita [QC#61486,ADD]
}
