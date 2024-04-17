package com.canon.cusa.s21.batch.NFA.NFAB006001;

import java.math.BigDecimal;

/**
 * Class name: NFAB006001Constant
 * <dd>The class explanation: Constant variable for NFAB006001.
 * <dd>Remarks:
 * @version 1.0
 * @author N. Sasaki
 */
public interface NFAB006001Constant {

    // ** Fixed Value ** //
    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM = "AR Jounal Creation";

    /** Fixed Value : AJE Interface Type Code for AR. */
    static final String AJE_INTFC_TP_CD_VAL = "04";

    /** Fixed Value : CoA Segment Lookup Type Value for TOC */
    static final String COA_SEG_LKUP_TP_TOC_VAL = "@TOC";

    /** Fixed Value : CoA Segment Lookup Type Value for ITEM */
    static final String COA_SEG_LKUP_TP_ITEM_VAL = "@ITEM";

    /** Fixed Value : CoA Segment Lookup Type Value for CUST */
    static final String COA_SEG_LKUP_TP_CUST_VAL = "@CUST";

    /** Fixed Value : Blank for null value */
    static final String BLANK = "";

    /** Fixed Value : GL_DT error message */
    static final String NFAM0076E_MSG = "GL_DT is not in open journalizable period.";

    /**
     * Fixed Value : Initial Exchange Rate Value. Loan Depreciation
     * Amount must be in dollars
     */
    static final BigDecimal INITIAL_EXCH_RATE_VAL = new BigDecimal("1.00");

    /**
     * Fixed Value : Quantity for AR (There is no qty)
     */
    static final BigDecimal AR_QTY = new BigDecimal("0");

    /** Fixed Value : Transaction Reason Code value '02' */
    static final String TRX_RSN_CD_VAL_02 = "02";

    /** Transaction Code for A/R reclass */
    static final String TRX_CD_AR_RECLASS = "170";

    /** Transaction Reason Code for A/R reclass */
    static final String TRX_RSN_CD_AR_RECLASS = "01";

    /** Fixed Value : Debit Type Code */
    static final String DEBIT_TP_CD_VAL = "D";

    /** Fixed Value : Credit Type Code */
    static final String CREDIT_TP_CD_VAL = "C";

    /** Fixed Value : Referrence Text To Oracle */
    static final String ORCL_REF_10_TXT_PREFIX = "AJE ID : ";

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 10000;

    /** Fixed Value : COA Prod Cd val Z */
    static final String COA_PROD_CD_VAL_Z = "Z";
    
    /** Fixed Value : COA Affiliate Cd val *** */
    static final String COA_AFFL_CD_VAL_OTH = "***";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String JRNL_ENTRY_SQ = "JRNL_ENTRY_SQ";

    /** DB Item Column Name */
    static final String GL_DT = "GL_DT";

    /** DB Item Column Name */
    static final String RCPT_NUM = "RCPT_NUM";

    /** DB Item Column Name */
    static final String AR_ADJ_NUM = "AR_ADJ_NUM";

    /** DB Item Column Name */
    static final String AR_TRX_NUM = "AR_TRX_NUM";

    /** DB Item Column Name */
    static final String COA_BR_CD = "COA_BR_CD";

    /** DB Item Column Name */
    static final String COA_CC_CD = "COA_CC_CD";

    /** DB Item Column Name */
    static final String COA_PROD_CD = "COA_PROD_CD";

    /** DB Item Column Name */
    static final String COA_CH_CD = "COA_CH_CD";

    /** DB Item Column Name */
    static final String COA_AFFL_CD = "COA_AFFL_CD";

    /** DB Item Column Name */
    static final String COA_AFFL_CD_LINK = "COA_AFFL_CD_LINK";
    
    /** DB Item Column Name */
    static final String EXCH_RATE = "EXCH_RATE";

    /** DB Item Column Name */
    static final String FUNC_CCY_CD = "FUNC_CCY_CD";

    /** DB Item Column Name */
    static final String DEAL_CCY_CD = "DEAL_CCY_CD";

    /** DB Item Column Name */
    static final String ACCT_ARTH_TP_CD = "ACCT_ARTH_TP_CD";

    /** DB Item Column Name */
    static final String ORCL_CCY_CD = "ORCL_CCY_CD";

    /** DB Item Column Name */
    static final String DEAL_ACCT_AMT = "DEAL_ACCT_AMT";

    /** DB Item Column Name */
    static final String FUNC_ACCT_AMT = "FUNC_ACCT_AMT";

    /** DB Item Column Name */
    static final String AJE_INTFC_CMNT_TXT = "AJE_INTFC_CMNT_TXT";

    /** DB Item Column Name */
    static final String AJE_ID = "AJE_ID";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_CD_01 = "AJE_PTRN_IND_TP_CD_01";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_NM_01 = "AJE_PTRN_IND_TP_NM_01";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_CD_01 = "AJE_PTRN_ACTL_CD_01";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_NM_01 = "AJE_PTRN_ACTL_NM_01";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_CD_02 = "AJE_PTRN_IND_TP_CD_02";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_NM_02 = "AJE_PTRN_IND_TP_NM_02";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_CD_02 = "AJE_PTRN_ACTL_CD_02";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_NM_02 = "AJE_PTRN_ACTL_NM_02";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_CD_03 = "AJE_PTRN_IND_TP_CD_03";

    /** DB Item Column Name */
    static final String AJE_PTRN_IND_TP_NM_03 = "AJE_PTRN_IND_TP_NM_03";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_CD_03 = "AJE_PTRN_ACTL_CD_03";

    /** DB Item Column Name */
    static final String AJE_PTRN_ACTL_NM_03 = "AJE_PTRN_ACTL_NM_03";

    /** DB Item Column Name */
    static final String JRNL_SRC_CD = "JRNL_SRC_CD";

    /** DB Item Column Name */
    static final String JRNL_SRC_NM = "JRNL_SRC_NM";

    /** DB Item Column Name */
    static final String JRNL_CATG_CD = "JRNL_CATG_CD";

    /** DB Item Column Name */
    static final String JRNL_CATG_NM = "JRNL_CATG_NM";

    /** DB Item Column Name */
    static final String DR_CR_TP_CD = "DR_CR_TP_CD";

    /** DB Item Column Name */
    static final String AJE_LINK_FLG = "AJE_LINK_FLG";

    /** DB Item Column Name */
    static final String AJE_LINE_IDX_NUM = "AJE_LINE_IDX_NUM";

    /** DB Item Column Name */
    static final String AJE_LINE_IDX_DESC_TXT = "AJE_LINE_IDX_DESC_TXT";

    /** DB Item Column Name */
    static final String AJE_COA_CMPY_CD = "AJE_COA_CMPY_CD";

    /** DB Item Column Name */
    static final String AJE_COA_BR_CD = "AJE_COA_BR_CD";

    /** DB Item Column Name */
    static final String AJE_COA_CC_CD = "AJE_COA_CC_CD";

    /** DB Item Column Name */
    static final String AJE_COA_ACCT_CD = "AJE_COA_ACCT_CD";

    /** DB Item Column Name */
    static final String AJE_COA_PROD_CD = "AJE_COA_PROD_CD";

    /** DB Item Column Name */
    static final String AJE_COA_CH_CD = "AJE_COA_CH_CD";

    /** DB Item Column Name */
    static final String AJE_COA_AFFL_CD = "AJE_COA_AFFL_CD";

    /** DB Item Column Name */
    static final String AJE_COA_PROJ_CD = "AJE_COA_PROJ_CD";

    /** DB Item Column Name */
    static final String AJE_COA_EXTN_CD = "AJE_COA_EXTN_CD";

    /** COA_BR_CD Name in map list */
    static final String ORG_BR = "ORG_BR";

    /** COA_CC_CD Name in map list */
    static final String ORG_CC = "ORG_CC";

    /** COA_PROD_CD Name in map list */
    static final String ORG_PROD = "ORG_PROD";

    /** COA_PROD_CD Name in map list */
    static final String AJE_ID_COUNT = "AJE_ID_COUNT";

    /** DB Item Column Name */
    static final String COA_BR_RCPT = "COA_BR_RCPT";

    /** DB Item Column Name */
    static final String COA_CC_RCPT = "COA_CC_RCPT";

    /** DB Item Column Name */
    static final String COA_PROD_RCPT = "COA_PROD_RCPT";

    /** DB Item Column Name */
    static final String COA_BR_ADJ = "COA_BR_ADJ";

    /** DB Item Column Name */
    static final String COA_CC_ADJ = "COA_CC_ADJ";

    /** DB Item Column Name */
    static final String COA_PROD_ADJ = "COA_PROD_ADJ";

    /** DB Item Column Name */
    static final String COA_BR_TRX = "COA_BR_TRX";

    /** DB Item Column Name */
    static final String COA_CC_TRX = "COA_CC_TRX";

    /** DB Item Column Name */
    static final String COA_PROD_TRX = "COA_PROD_TRX";

    /** DB Item Column Name */
    static final String BR_DPC_PTRN = "BR_DPC_PTRN";

    /** DB Item Column Name */
    static final String CC_DPC_PTRN = "CC_DPC_PTRN";

    /** DB Item Column Name */
    static final String CH_DPC_PTRN = "CH_DPC_PTRN";

    /** DB Item Column Name */
    static final String BR_DPC_PTRN_RCPT = "BR_DPC_PTRN_RCPT";

    /** DB Item Column Name */
    static final String CC_DPC_PTRN_RCPT = "CC_DPC_PTRN_RCPT";

    /** DB Item Column Name */
    static final String CH_DPC_PTRN_RCPT = "CH_DPC_PTRN_RCPT";

    /** DB Item Column Name */
    static final String BR_DPC_PTRN_TRX = "BR_DPC_PTRN_TRX";

    /** DB Item Column Name */
    static final String CC_DPC_PTRN_TRX = "CC_DPC_PTRN_TRX";

    /** DB Item Column Name */
    static final String CH_DPC_PTRN_TRX = "CH_DPC_PTRN_TRX";

    /** DB Item Column Name */
    static final String BR_DPC_LINK = "BR_DPC_LINK";

    /** DB Item Column Name */
    static final String CC_DPC_LINK = "CC_DPC_LINK";

    /** DB Item Column Name */
    static final String CH_DPC_LINK = "CH_DPC_LINK";

    /** DB Item Column Name */
    static final String CH_LINK_CUST = "CH_LINK_CUST";

    /** DB Item Column Name */
    static final String CH_LINK_PTRN = "CH_LINK_PTRN";

    /** DB Item Column Name */
    static final String COA_CH_RCPT = "COA_CH_RCPT";

    /** DB Item Column Name */
    static final String COA_CH_ADJ = "COA_CH_ADJ";

    /** DB Item Column Name */
    static final String COA_CH_TRX = "COA_CH_TRX";

    /** DB Item Column Name */
    static final String RCPT_CHK_NUM = "RCPT_CHK_NUM";

    /** DB Item Column Name */
    static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB Item Column Name */
    static final String TOC_CD = "TOC_CD";

    /** DB Item Column Name */
    static final String COA_ACCT_CD_LINK = "COA_ACCT_CD_LINK";
    
    /** DB Item Column Name */
    static final String EXPT_AR_ACCT_CD = "EXPT_AR_ACCT_CD";
    
    /** DB Item Column Name */
    static final String RCPT_AFFL_ACCT_CD = "RCPT_AFFL_ACCT_CD";
    
    /** DB Item Column Name */
    static final String RCPT_COA_AFFL_CD = "RCPT_COA_AFFL_CD";
    
    /** DB Item Column Name */
    static final String COA_CH_CD_PR = "COA_CH_CD_PR";
    
    /** DB Item Column Name */
    static final String COA_CC_CD_PR = "COA_CC_CD_PR";
    
    /** DB Item Column Name */
    static final String DS_ACCT_NUM = "DS_ACCT_NUM";
    
    /** DB Item Column Name */
    static final String DS_INV_TP_CD = "DS_INV_TP_CD";
  
    /** DB Item Column Name */
    static final String DR_AR_RCPT_SRC_CD = "DR_AR_RCPT_SRC_CD";
    
    /** DB Item Column Name */
    static final String CR_AR_RCPT_SRC_CD = "CR_AR_RCPT_SRC_CD";
    
    /** Disp Item Column Name */
    static final String DISP_DS_INV_TP_CD = "Invoice Type";
  
    /** Disp Item Column Name */
    static final String DISP_DR_AR_RCPT_SRC_CD = "Dr Receipt Source";
    
    /** Disp Item Column Name */
    static final String DISP_CR_AR_RCPT_SRC_CD = "Cr Receipt Source";

    /** Disp Item Column Name */
    static final String DISP_AR_ADJ_NUM = "Transaction#";

    /** Disp Item Column Name */
    static final String DISP_AR_TRX_NUM = "Adjustment#";

}
