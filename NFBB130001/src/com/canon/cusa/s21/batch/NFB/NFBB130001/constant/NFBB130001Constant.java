/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB130001.constant;

/**
 * <pre>
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/04/2016   Hitachi         J.Kim           Create          N/A
 * 2022/08/10   Hitachi         M.Kikushima     Update          QC#57083
 * </pre>
 */
public class NFBB130001Constant {

    /**
     * BATCH_PROGRAM_ID(NFBB1300)
     */
    public static final String BUSINESS_ID = "NFBB1300";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Max Commit Number */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Error Msg : Global Company Code is required. */
    public static final String NFBM0199E = "NFBM0199E";

    /** Column name : GLBL_CMPY_CD */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name : AR_RF_TRX_PK */
    public static final String AR_RF_TRX_PK = "AR_RF_TRX_PK";

    /** Column name : AP_PMT_CHK_NUM */
    public static final String AP_PMT_CHK_NUM = "AP_PMT_CHK_NUM";

    /** Column name : ACCT_INV_STS_CD */
    public static final String ACCT_INV_STS_CD = "ACCT_INV_STS_CD";

    // START 2022/08/10 M.Kikushima [QC#57083,ADD]
    /** Column name : AR_RF_CRAT_DT */
    public static final String AR_RF_CRAT_DT = "AR_RF_CRAT_DT";

    /** Column name : AP_VND_INV_NUM */
    public static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";

    /** Column name : RCPT_NUM */
    public static final String RCPT_NUM = "RCPT_NUM";

    /** Column name : AR_ADJ_NUM */
    public static final String AR_ADJ_NUM = "AR_ADJ_NUM";
    // END 2022/08/10 M.Kikushima [QC#57083,ADD]
}
