/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB135001.constant;

/**
 * <pre>
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/04/05   Hitachi         R.Takau         Create          QC#61036
 * </pre>
 */
public class NFBB135001Constant {

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /**
     * Error Msg : Global Company Code is required.
     */
    public static final String NFBM0199E = "NFBM0199E";

    /** Error Msg : There is no parameter in [@] */
    public static final String NFBM0207E = "NFBM0207E";

    /** An error occurred in NFCA0010_01  registration. [@] */
    public static final String NFBM0297E = "NFBM0297E";

    /** Failed to update the @.[@] */
    public static final String NFBM0298E = "NFBM0298E";

    /** @ has been updated by another user. */
    public static final String NFBM0299E = "NFBM0299E";

    /** An input parameter, [@],  has not been set. */
    public static final String NZZM0012E = "NZZM0012E";

    /** VAR_CHAR_CONST : VAR_CHAR_CONST_NM */
    public static final String CUSA_PRNT_VND_CD = "CUSA_PRNT_VND_CD";

    /** venderCode */
    public static final String DEFAULT_CUSA_PRNT_VND_CD = "614271";

    /** DB field */
    public static final String AP_VND_CD = "AP_VND_CD";

    /** DB field */
    public static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";

    /** DB field */
    public static final String AP_VND_INV_SQ_NUM = "AP_VND_INV_SQ_NUM";

    /** DB field */
    public static final String AC_INV_TOT_COST_AMT = "AC_INV_TOT_COST_AMT";

    /** DB field */
    public static final String AP_PMT_CHK_NUM = "AP_PMT_CHK_NUM";

    /** DB field */
    public static final String ARCS_PMT_AMT = "ARCS_PMT_AMT";

    /** DB field */
    public static final String ARCS_PMT_DT = "ARCS_PMT_DT";

    /** VAR_CHAR_CONST : ITRL_ID_CSA_CUSA_WS. */
    public static final String ITRL_ID_CSA_CUSA_WS = "ITRL_ID_CSA_CUSA_WS";

    /**VAR_CHAR_CONST : NFCA0010_01_CUST_CD. */
    public static final String NFCA0010_01_CUST_CD = "NFCA0010_01_CUST_CD";

    /** Interface ID */
    public static final String NFCA0010 = "NFCA0010";
}
