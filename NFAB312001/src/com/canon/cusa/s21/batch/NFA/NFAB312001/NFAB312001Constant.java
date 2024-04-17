package com.canon.cusa.s21.batch.NFA.NFAB312001;

/**
 *<pre>
 * Accrual Journal Creation Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/06/14   Hitachi         S.Nakatani      Create          QC#61088
 */
public interface NFAB312001Constant {

    // ** Fixed Value ** //
    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM = "Accrual Jounal Creation";

    /** Fixed Value : AJE Interface Type Code for AR. */
    static final String AJE_INTFC_TP_CD_VAL = "14";

    /** Fixed Value : GL_DT error message */
    static final String NFAM0076E_MSG = "GL_DT is not in open journalizable period.";

    /** Fixed Value : Referrence Text To Oracle */
    static final String ORCL_REF_10_TXT_PREFIX = "AJE ID : ";

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 1000;

    /** DB Item Column Name */
    static final String JRNL_ENTRY_SQ = "JRNL_ENTRY_SQ";

    /** DB Item Column Name */
    static final String GL_DT = "GL_DT";

    /** DB Item Column Name */
    static final String RCPT_NUM = "RCPT_NUM";

    /** DB Item Column Name */
    static final String JRNL_DEAL_AMT = "JRNL_DEAL_AMT";

    /** DB Item Column Name */
    static final String JRNL_FUNC_AMT = "JRNL_FUNC_AMT";

    /** DB Item Column Name */
    static final String DEAL_CCY_CD = "DEAL_CCY_CD";

    /** DB Item Column Name */
    static final String FUNC_CCY_CD = "FUNC_CCY_CD";

    /** DB Item Column Name */
    static final String ORCL_CCY_CD = "ORCL_CCY_CD";

    /** DB Item Column Name */
    static final String EXCH_RATE = "EXCH_RATE";

    /** DB Item Column Name */
    static final String ACCT_ARTH_TP_CD = "ACCT_ARTH_TP_CD";

    /** DB Item Column Name */
    static final String TOC_CD = "TOC_CD";

    /** DB Item Column Name */
    static final String DS_ACCT_NUM = "DS_ACCT_NUM";

    /** DB Item Column Name */
    static final String SER_NUM = "SER_NUM";

    /** DB Item Column Name */
    static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** DB Item Column Name */
    static final String DS_CONTR_PK = "DS_CONTR_PK";

    /** DB Item Column Name */
    static final String DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";

    /** DB Item Column Name */
    static final String DS_CONTR_NUM = "DS_CONTR_NUM";

    /** DB Item Column Name */
    static final String SLS_REP_CR_PCT = "SLS_REP_CR_PCT";

    /** DB Item Column Name */
    static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** DB Item Column Name */
    static final String AJE_ACRL_INTFC_PK = "AJE_ACRL_INTFC_PK";

    /** DB Item Column Name */
    static final String AJE_ITEM_CD = "AJE_ITEM_CD";

    /** DB Item Column Name */
    static final String AJE_ITEM_DESC_TXT = "AJE_ITEM_DESC_TXT";

    /** DB Item Column Name */
    static final String CR = "cr";

}
