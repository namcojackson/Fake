package com.canon.cusa.s21.batch.NFA.NFAB008001;

import java.math.BigDecimal;

/**
 * Class name: NFAB008001Constant
 * <dd>The class explanation: Constant variable for NFAB008001.
 * <dd>Remarks:
 * @version 1.0
 * @author H.Naoi
 */
public interface NFAB008001Constant {

    // ** Fixed Value ** //    
    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM = "Rental Depreciation";

    /** Fixed Value : AJE Interface Type Code for Rental Depreciation. */
    static final String AJE_INTFC_TP_CD_VAL = "07";

    /** Fixed Value : CoA Segment Lookup Type Value for TOC */
    static final String COA_SEG_LKUP_TP_TOC_VAL = "@TOC";

    /** Fixed Value : CoA Segment Lookup Type Value for ITEM */
    static final String COA_SEG_LKUP_TP_ITEM_VAL = "@ITEM";

    /** Fixed Value : CoA Segment Lookup Type Value for CUST */
    static final String COA_SEG_LKUP_TP_CUST_VAL = "@CUST";

    /** Fixed Value : Initial Exchange Rate Value. Loan Depreciation Amount must be in dollars */
    static final BigDecimal INITIAL_EXCH_RATE_VAL = new BigDecimal("1.00");

    /** Fixed Value : Debit Type Code */
    static final String DEBIT_TP_CD_VAL = "D";

    /** Fixed Value : Credit Type Code */
    static final String CREDIT_TP_CD_VAL = "C";

    /** Fixed Value : Referrence Text To Oracle */
    static final String ORCL_REF_10_TXT_PREFIX = "AJE ID : ";

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 10000;
    
    /** Fixed Value : Line Indicator Type Cd value '08' (**WRITE OFF**) */
    static final String LIN_IND_TP_VAL_WRITE_OFF = "08";
    
    /** Fixed Value : Line Indicator Type Cd value '09' (**ACCUM DEPR**) */
    static final String LIN_IND_TP_VAL_ACCUM_DEPR = "09";
    
    /** Fixed Value : Line Indicator Type Cd value '12' (**COST OF SALES**) */
    static final String LIN_IND_TP_VAL_COS = "12";
    
    /** Fixed Value : Line Indicator Type Cd value '16' (**CUR VAL**) */
    static final String LIN_IND_TP_VAL_CUR_VAL = "16";
    
    /** Fixed Value : Invoice Inventory Indicator Type value 'C' (**COST OF SALES**) */
    static final String INV_INVTY_IND_TP_VAL_C = "C";
        
    /** Fixed Value : COA Prod Cd val Z */
    static final String COA_PROD_CD_VAL_Z = "Z";
    
    /** Fixed Value : Blank for null value */
    static final String BLANK = "";
    
    // ** DB Item Column Name's Fixed Value ** //       
    /** DB Item Column Name */
    static final String  TRIAL_MACH_IND_TP_CD = "TRIAL_MACH_IND_TP_CD";

    /** DB Item Column Name */
    static final String JRNL_ENTRY_SQ = "JRNL_ENTRY_SQ";

    /** DB Item Column Name */
    static final String  AJE_ASSET_TRX_INTFC_PK = "AJE_ASSET_TRX_INTFC_PK";

    /** DB Item Column Name */
    static final String  GL_DT = "GL_DT";

    /** DB Item Column Name */
    static final String  MDSE_CD = "MDSE_CD";

    /** DB Item Column Name */
    static final String  MDSE_NM = "MDSE_NM";

    /** DB Item Column Name */
    static final String COA_BR_CD = "COA_BR_CD";

    /** DB Item Column Name */
    static final String COA_CC_CD = "COA_CC_CD";

    /** DB Item Column Name */
    static final String COA_PROD_CD_MDSE = "COA_PROD_CD_MDSE";

    /** DB Item Column Name */
    static final String COA_PROD_CD_ORG = "COA_PROD_CD_ORG";

    /** DB Item Column Name */
    static final String COA_CH_CD = "COA_CH_CD";
    
    /** DB Item Column Name */
    static final String COA_CH_CD_CUST = "COA_CH_CD_CUST";

    /** DB Item Column Name */
    static final String COA_AFFL_CD = "COA_AFFL_CD";

    /** DB Item Column Name */
    static final String  INV_QTY = "INV_QTY";

    /** DB Item Column Name */
    static final String  CUR_DEPC_AMT = "CUR_DEPC_AMT";
    
    /** DB Item Column Name */
    static final String  CUR_VAL_AMT = "CUR_VAL_AMT";
    
    /** DB Item Column Name */
    static final String  ORIG_VAL_AMT = "ORIG_VAL_AMT";

    /** DB Item Column Name */
    static final String  AJE_INTFC_CMNT_TXT = "AJE_INTFC_CMNT_TXT";

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
    
    /** DB Item Column Name */
    static final String COA_BR_CD_DPC = "COA_BR_CD_DPC";
    
    /** DB Item Column Name */
    static final String COA_CC_CD_DPC = "COA_CC_CD_DPC";
    
    /** DB Item Column Name */
    static final String COA_CH_CD_DPC = "COA_CH_CD_DPC";
    
    /** DB Item Column Name */
    static final String CH_PTRN_CUST = "CH_PTRN_CUST";
    
    /** DB Item Column Name */
    static final String CH_ITEM_CUST = "CH_ITEM_CUST";
    
    /** DB Item Column Name */
    static final String CH_ITEM_PTRN = "CH_ITEM_PTRN";
    
    /** DB Item Column Name */
    static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB Item Column Name */
    static final String TOC_CD = "TOC_CD";

    /** DB Item Column Name */
    static final String INV_NUM = "INV_NUM";
    
    /** DB Item Column Name */
    static final String COA_BR_CD_LINK = "COA_BR_CD_LINK";
    
    /** DB Item Column Name */
    static final String COA_CC_CD_LINK = "COA_CC_CD_LINK";
    
    /** DB Item Column Name */
    static final String COA_PROD_CD_LINK = "COA_PROD_CD_LINK";
    
    /** DB Item Column Name */
    static final String COA_CH_CD_LINK = "COA_CH_CD_LINK";
    
    /** DB Item Column Name */
    static final String COA_ACCT_CD = "COA_ACCT_CD";
    
    /** DB Item Column Name */
    static final String COA_PROJ_CD_LINK = "COA_PROJ_CD_LINK";
    
    /** Fixed Value : MDSE or PARTS Code M */
    static final String MDSE_OR_PRT_CD_M_VAL = "M";
    
    /** Fixed Value : Any Product Code * */
    static final String ANY_PROD_CD = "*";

    /** DB Item Column Name */
    static final String COA_CC_CD_PR = "COA_CC_CD_PR";

    /** DB Item Column Name */
    static final String COA_CH_CD_PR = "COA_CH_CD_PR";
    
    /** DB Item Column Name */
    static final String ASSET_ADJ_AMT = "ASSET_ADJ_AMT";

    // START 2019/08/10 S.Takami [QC#51897,ADD]
    /** DB Item Column Name */
    static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";
    // END 2019/08/10 S.Takami [QC#51897,ADD]
}
