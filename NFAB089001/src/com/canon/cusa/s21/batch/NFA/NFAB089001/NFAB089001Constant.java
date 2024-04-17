/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB089001;

/**
 * Class name: NFAB089001Constant
 * <dd>The class explanation: Constant variable for NFAB089001.
 * <dd>Remarks:
 * @version 1.0
 * @author N. Sasaki
 */
public interface NFAB089001Constant {

    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM = "Inventory Report: Ship In-Out";

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 10000;

    /** Fixed Value : Location Status Code: In transit */
    static final String LOC_STS_CD_IN_TRANSIT = "01";

    /** Fixed Value : Inventory Location Type Code: Warehouse */
    static final String INVTY_LOC_TP_CD_WAREHOUSE = "01";

    /** Fixed Value : AJE Interface Type Code: MDSE Inventory */
    static final String AJE_INTFC_TP_CD_MDSE_INVTY = "02";

    /**
     * Fixed Value : AJE Interface Type Code: MDSE Inventory
     * Revaluation
     */
    static final String AJE_INTFC_TP_CD_MDSE_INVTY_RVAL = "09";

    /** Fixed Value : AJE Interface Type Code: MDSE Inventory Re-class */
    static final String AJE_INTFC_TP_CD_MDSE_INVTY_RECLASS = "12";

    /** Fixed Value : Asset Code */
    static final String ASSET_CD_F = "F";

    /** Fixed Value : Ship In (Debit) */
    static final String SHIP_IN_CD = "1";

    /** Fixed Value : Ship Out (Credit) */
    static final String SHIP_OUT_CD = "2";

    /** Fixed Value : Unknown product code */
    static final String UNKNOWN = "UNKNOWN";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

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
    static final String JRNL_FUNC_CR_AMT = "JRNL_FUNC_CR_AMT";
    
    /** DB Item Column Name */
    static final String D_OR_C = "D_OR_C";
    
    /** Varchar Constant Value */
    static final String LOAN_INVTY_ACCT_CD = "AJE_LOAN_INVTY_ACCT_CD";
    
}
