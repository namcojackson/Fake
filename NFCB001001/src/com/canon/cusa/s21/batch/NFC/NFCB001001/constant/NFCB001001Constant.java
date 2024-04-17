/**
 * <pre>
 * Copyright (c) 2017 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB001001.constant;

import java.math.BigDecimal;

import com.canon.cusa.s21.batch.NFC.NFCB001001.NFCB001001;

/**
 * <pre>
 * Create Transaction Balance From S21OM Invoice Data
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/30/2017   Hitachi         K.Kasai         Create          N/A
 * </pre>
 */
public class NFCB001001Constant {

    /** Program ID */
    public static final String PROGRAM_ID = NFCB001001.class.getSimpleName();

    /** BATCH ID */
    public static final String BATCH_ID = "NFCB0010";

    /** MAX_COMMIT_NUMBER */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** FETCH_SIZE */
    public static final int FETCH_SIZE = 1000;

    /** First day */
    public static final String CNT_STR_DAY_FIRST_01 = "01";

    /** int:0 */
    public static final int CST_INT_NUM_0 = 0;

    /** int:1 */
    public static final int CST_INT_NUM_1 = 1;

    /** int:4 */
    public static final int CST_INT_NUM_4 = 4;

    /** int:6 */
    public static final int CST_INT_NUM_6 = 6;

    /** int:12 */
    public static final int CST_INT_NUM_12 = 12;

    /** LENGTH:YYYYMM */
    public static final int LEN_YYYYMM = 6;

    /** Calc value */
    public static final BigDecimal CALC_VALUE = new BigDecimal(100);

    /** 00 */
    public static final String CST_MONTH_FORMAT_00 = "00";

    /** Return code */
    public static final int RTN_CD_NORMAL = 0;

    /** Return code */
    public static final int RTN_CD_SKIP = 1;

    /** Return code */
    public static final int RTN_CD_ERROR = 2;

    /** Return code */
    public static final int RTN_CD_INS_ERROR = 3;

    /** Return code */
    public static final int RTN_CD_PARTS_ERROR = 4;

    /** Return code */
    public static final int RTN_CD_AR_CCY_CNTL_ERROR = 5;

    /** Return code */
    public static final int RTN_CD_USER_ID_ERROR = 10;

    /** Return code */
    public static final int RTN_CD_INV_SEQ_PK_ERROR = 11;

    /** Return code */
    public static final int RTN_CD_TP_CD_ERROR = 12;

    /** Message ID:Entry Parameter Error. */
    public static final String NFCM0522E = "NFCM0522E";

    /** Message ID:@ cannot be obtained. */
    public static final String NFCM0531E = "NFCM0531E";

    /** Message ID:@ cannot be added. */
    public static final String NFCM0532E = "NFCM0532E";

    /** Message ID:Error has occurred at [@]. */
    public static final String NFCM0576E = "NFCM0576E";

    /** Message ID:Cash Application: Mandatory field [@] has an invalid value. */
    public static final String NFCM0602E = "NFCM0602E";

    /** Message String */
    public static final String[] MSG_STR_INV_SEQ_PK = {"INV_SEQ_PK" };

    /** Message String */
    public static final String[] MSG_STR_INV_TP_CD = {"INV_TP_CD" };

    /** Message String */
    public static final String[] MSG_STR_AR_TRX_BAL = {"AR_TRX_BAL" };

    /** Message String */
    public static final String[] MSG_STR_AR_CCY_CNTL = {"AR_CCY_CNTL" };

    /** Message String */
    public static final String[] MSG_STR_AR_CASH_DISC_SCHD = {"AR_CASH_DISC_SCHD" };

    /** Message String */
    public static final String[] MSG_STR_USER_ID = {"USER_ID" };

    /** Message String */
    public static final String[] MSG_STR_AR_APPLY_INTFC_WRK = {"AR_APPLY_INTFC_WRK" };

    /** Message String */
    public static final String[] MSG_STR_ISTL_PMT_TERM = {"ISTL_PMT_TERM" };

    /** Message String */
    public static final String[] MSG_STR_APPLY_GRP_KEY = {"APPLY_GRP_KEY" };

    /** Message String */
    public static final String[] MSG_STR_DEAL_SQ_NUM = {"DEAL_SQ_NUM" };

    /** Message String */
    public static final String[] MSG_STR_NFZC3010 = {"NFZC3010" };

    /** Cash discount Term Code ZYB */
    public static final String CASH_DISC_TERM_CD_ZYB = "ZYB";

    /** HYPHEN */
    public static final String HYPHEN = "-";

    /** ZERO */
    public static final String ZERO = "0";

    /** Round Method Code */
    public static final String ROUND_METH_CD_U = "U";

    /** Round Method Code */
    public static final String ROUND_METH_CD_O = "O";

    /** Round Method Code */
    public static final String ROUND_METH_CD_D = "D";

    /** PRCS_STS_CD */
    public static final String PRCS_STS_CD_VALUE = "0";

    /** length */
    public static final int TWO_LENGTH = 2;

}
