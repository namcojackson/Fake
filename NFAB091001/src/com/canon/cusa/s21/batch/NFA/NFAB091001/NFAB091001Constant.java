/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB091001;

/**
 * Class name: NFAB091001Constant
 * <dd>The class explanation: Constant variable for NFAB091001.
 * <dd>Remarks:
 * @version 1.0
 * @author N. Sasaki
 */
public interface NFAB091001Constant {

    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM = "Inventory Report: Revaluation";

    /** Fixed Value : Valid date format */
    static final String YYYYMMDD = "yyyyMMdd";

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 10000;

    /** Fixed Value : Transaction Reason Code: Revaluation */
    static final String TRX_RSN_CD_REVALUATION = "06";

    /** Fixed Value : Transaction Reason Code: Write Down */
    static final String TRX_RSN_CD_WRITE_DOWN = "07";

    /** Fixed Value : Unknown product code */
    static final String UNKNOWN = "UNKNOWN";
    
    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String JRNL_ENTRY_PK = "JRNL_ENTRY_PK";

    /** DB Item Column Name */
    static final String GL_DT = "GL_DT";

    /** DB Item Column Name */
    static final String AJE_ITEM_CD = "AJE_ITEM_CD";

    /** DB Item Column Name */
    static final String DR_COA_ACCT_CD = "DR_COA_ACCT_CD";

    /** DB Item Column Name */
    static final String CR_COA_ACCT_CD = "CR_COA_ACCT_CD";

    /** DB Item Column Name */
    static final String COA_PROD_CD = "COA_PROD_CD";

    /** DB Item Column Name */
    static final String JRNL_QTY = "JRNL_QTY";

    /** DB Item Column Name */
    static final String JRNL_FUNC_DR_AMT = "JRNL_FUNC_DR_AMT";
    
    /** DB Item Column Name */
    static final String DT_PREV = "DT_PREV";
    
    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";
    
    /** DB Item Column Name */
    static final String COA_ACCT_CD = "COA_ACCT_CD";
    
    /** Varchar Constant Value */
    static final String LOAN_INVTY_ACCT_CD = "AJE_LOAN_INVTY_ACCT_CD";
}
