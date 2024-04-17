package com.canon.cusa.s21.batch.NFA.NFAB090001;

/**
 * Class name: NFAB090001Constant
 * <dd>The class explanation: Constant variable for NFAB090001.
 * <dd>Remarks:
 * @version 1.0
 * @author N. Sasaki
 */
public interface NFAB090001Constant {

    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM = "Inventory Report: Daily Work Table";

    /** Fixed Value : Valid date format */
    static final String YYYYMMDD = "yyyyMMdd";

    /** Fixed Value : Ship In (Debit) */
    static final String INVTY_IN_OUT_STS_CD_SHIP_IN = "1";

    /** Fixed Value : Ship Out (Credit) */
    static final String INVTY_IN_OUT_STS_CD_SHIP_OUT = "2";

    /** Fixed Value : Open Amount */
    static final int OPEN = 1;

    /** Fixed Value : End Amount */
    static final int END = 2;

    /** Fixed Value : Error */
    static final int ERROR = -1;

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 10000;
    
    /** DB Item Column Name */
    static final String COA_PROD_CD = "COA_PROD_CD";
    
    /** DB Item Column Name */
    static final String COA_ACCT_CD = "COA_ACCT_CD";
    
    

    /** DB Item Column Name */
    static final String DLY_AJE_RPT_INVTY_SQ = "DLY_AJE_RPT_INVTY_SQ";

    /** DB Item Column Name */
    static final String DLY_MDSE_SMRY_AMT_SUM = "DLY_MDSE_SMRY_AMT_SUM";

    /** DB Item Column Name */
    static final String RVAL_AMT_SUM = "RVAL_AMT_SUM";

    /** DB Item Column Name */
    static final String INVTY_SHIP_AMT = "INVTY_SHIP_AMT";
    
    /** Report ID */
    static final String reportIdAll = "NFAF0900";

    /** Report ID */
    static final String reportIdByProd = "NFAF0901";

    /** Report Branch No. */
    static final String reportBranchNum = "01";

    /** DB Item Column Name */
    static final String DT_PREV = "DT_PREV";

    // START 2018/02/22 J.Kim [QC#20564,ADD]
    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;
    // END 2018/02/22 J.Kim [QC#20564,ADD]
}
