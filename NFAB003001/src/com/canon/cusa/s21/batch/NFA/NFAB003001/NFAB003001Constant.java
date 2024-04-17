package com.canon.cusa.s21.batch.NFA.NFAB003001;

import java.math.BigDecimal;

/**
 * Class name: NFAB003001Constant
 * <dd>The class explanation: Constant variable for NFAB003001.
 * <dd>Remarks:
 * @version 1.0
 * @author H.Naoi
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/13   Hitachi         K.Kojima        Update          QC#14586
 * 2016/10/14   Hitachi         K.Kojima        Update          QC#14823
 * </pre>
 */
public interface NFAB003001Constant {

    // ** Fixed Value ** //    
    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM = "Merchandise Revaluation";

    /** Fixed Value : AJE Interface Type Code for Merchandise Revaluation. */
    static final String AJE_INTFC_TP_CD_VAL = "09";

    /** Fixed Value : Journal Completion Flag Value for Y */
    //static final String JRNL_CPLT_FLG_Y_VAL = "Y";

    /** Fixed Value : Journal Completion Flag Value for N */
    //static final String JRNL_CPLT_FLG_N_VAL = "N";

    /** Fixed Value : GL Send Completion Flag Value for N */
    //static final String GL_SEND_CPLT_FLG_N_VAL = "N";

    /** Fixed Value : CoA Segment Lookup Type Value for ITEM */
    static final String COA_SEG_LKUP_TP_ITEM_VAL = "@ITEM";

    /** Fixed Value : CoA Segment Lookup Type Value for COA_BR_CC_CH_PTRN */
    //static final String COA_SEG_LKUP_TP_PR_VAL = "#PR";   deleted

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
    
    /** Fixed Value : MDSE or PARTS Code M */
    static final String MDSE_OR_PRT_CD_M_VAL = "M";

    // ** DB Item Column Name's Fixed Value ** //   
    
    /** DB Item Column Name */
    static final String JRNL_ENTRY_SQ = "JRNL_ENTRY_SQ";

    /** DB Item Column Name */
    static final String  AJE_MDSE_RVAL_INTFC_PK = "AJE_MDSE_RVAL_INTFC_PK";

    /** DB Item Column Name */
    static final String  GL_DT = "GL_DT";

    /** DB Item Column Name */
    static final String  MDSE_CD = "MDSE_CD";

    /** DB Item Column Name */
    static final String  MDSE_NM = "MDSE_NM";

    /** DB Item Column Name */
    static final String COA_PROD_CD = "COA_PROD_CD";

    /** DB Item Column Name */
    static final String COA_BR_CD = "COA_BR_CD";

    /** DB Item Column Name */
    static final String COA_CC_CD = "COA_CC_CD";

    /** DB Item Column Name */
    static final String COA_CH_CD = "COA_CH_CD";

    /** DB Item Column Name */
    static final String COST_CCY_CD = "COST_CCY_CD";

    /** DB Item Column Name */
    static final String ORCL_CCY_CD = "ORCL_CCY_CD";

    /** DB Item Column Name */
    static final String ACCT_ARTH_TP_CD = "ACCT_ARTH_TP_CD";

    /** DB Item Column Name */
    static final String INVTY_QTY = "INVTY_QTY";

    /** DB Item Column Name */
    static final String RVAL_AMT = "RVAL_AMT";

    /** DB Item Column Name */
    static final String TOT_RVAL_AMT = "TOT_RVAL_AMT";

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
    static final String COA_ACCT_CD_MC = "COA_ACCT_CD_MC";

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
    
    /** Varchar Constant Value */
    static final String LOAN_INVTY_ACCT_CD = "AJE_LOAN_INVTY_ACCT_CD";

    // START 2016/10/13 K.Kojima [QC#14586,ADD]
    /** SQL Item Column Name */
    static final String COA_ACCT_CD_WIP = "COA_ACCT_CD_WIP";

    /** SQL Item Column Name */
    static final String COA_ACCT_CD_CI = "COA_ACCT_CD_CI";
    // END 2016/10/13 K.Kojima [QC#14586,ADD]

}
