/*
 * <Pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB134001;

import java.math.BigDecimal;

/**
 * <pre>
 * AP Accrual Write-off PO Close Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/04/28   Hitachi         A.Kohinata      Create          QC#57934
 *</pre>
 */
public class NFBB134001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NFBB134001";

    /** VAR_CHAR_CONST Name NFBL1130_COA_ACCT_CD */
    public static final String NFBL1130_COA_ACCT_CD = "NFBL1130_COA_ACCT_CD";

    /** NUM_CONST Name NFBB1340_WRT_OFF_PAST_DAYS */
    public static final String NFBB1340_WRT_OFF_PAST_DAYS = "NFBB1340_WRT_OFF_PAST_DAYS";

    /** Default COA_ACCT_CD value */
    public static final String DEFAULT_COA_ACCT_CD = "21431001";

    /** Default PAST_DAYS value */
    public static final BigDecimal DEFAULT_PAST_DAYS = new BigDecimal(365);

    /** Array Delimiter */
    public static final String ARRAY_DELIMITER = ",";

    /** An error occurred in API.  <APIID: [@], Error Code: [@], Data Key: [@]> */
    public static final String NFBM0208E = "NFBM0208E";
}
