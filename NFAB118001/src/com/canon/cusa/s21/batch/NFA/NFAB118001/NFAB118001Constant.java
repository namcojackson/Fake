package com.canon.cusa.s21.batch.NFA.NFAB118001;

/**
 * Credit Memo Journalize
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 02/19/2018   Hitachi         E.Kameishi      Create          N/A
 * 04/04/2018   Hitachi         E.Kameishi      Update          QC#24870
 * 04/13/2018   Hitachi         E.Kameishi      Update          QC#25489
 *</pre>
 */
public interface NFAB118001Constant {

    // ** Fixed Value ** //
    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM = "Credit Memo Journalize";

    /** business_id : NFAB1180 */
    String BUSINESS_ID = "NFAB118001";

    /** Message */
    String NFAM0035E = "NFAM0035E";

    /** Message */
    String ZZZM9025E = "ZZZM9025E";

    /** Message */
    String UNIQUE_ERROR = "ZZBM0074E";

    /** Fixed Value : TRX_RSN_CD SA,SB,SC */
    String TRX_RSN_LIST = "SA,SB,SC";

    /** Fixed Value : D */
    static final String DR_CD = "D";

    /** Fixed Value : C */
    static final String CR_CD = "C";

    //START 2018/04/04 E.Kameishi [QC#24870,ADD]
    /** Fixed Value : 5 */
    static final int STRING_LENGTH_5 = 5;

    /** Fixed Value : 001 */
    static final String STR_001 = "001";
    //END 2018/04/04 E.Kameishi [QC#24870,ADD]

    /** Insert Count*/
    int BULK_INSERT_CNT =  1000;

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB Item Column Name */
    static final String INV_NUM = "INV_NUM";

    /** DB Item Column Name */
    static final String DS_INV_SLS_CR_PK = "DS_INV_SLS_CR_PK";

    /** DB Item Column Name */
    static final String SLS_REP_TOC_CD = "SLS_REP_TOC_CD";

    /** DB Item Column Name */
    static final String SLS_REP_CR_PCT = "SLS_REP_CR_PCT";

    /** DB Item Column Name */
    static final String INV_LINE_SPL_TP_CD = "INV_LINE_SPL_TP_CD";

    /** DB Item Column Name */
    static final String INV_LINE_SPL_PCT = "INV_LINE_SPL_PCT";

    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";

    /** DB Item Column Name */
    static final String COA_BR_CD = "COA_BR_CD";

    /** DB Item Column Name */
    static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** DB Item Column Name */
    static final String ORIG_INV_NUM = "ORIG_INV_NUM";

    /** DB Item Column Name */
    static final String CR_REBIL_RSN_CATG_CD = "CR_REBIL_RSN_CATG_CD";

    /** DB Item Column Name */
    static final String SVC_INV_CHRG_TP_CD = "SVC_INV_CHRG_TP_CD";

    /** DB Item Column Name */
    static final String INV_BOL_LINE_NUM = "INV_BOL_LINE_NUM";

    /** DB Item Column Name */
    static final String INV_LINE_NUM = "INV_LINE_NUM";

    /** DB Item Column Name */
    static final String INV_LINE_SUB_NUM = "INV_LINE_SUB_NUM";

    /** DB Item Column Name */
    static final String INV_LINE_SUB_TRX_NUM = "INV_LINE_SUB_TRX_NUM";

    /** DB Item Column Name */
    static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** DB Item Column Name */
    static final String INTG_MDSE_CD = "INTG_MDSE_CD";

    /** DB Item Column Name */
    static final String DFRD_ACCTG_RULE_CD = "DFRD_ACCTG_RULE_CD";

    /** DB Item Column Name */
    static final String DFRD_REV_FLG = "DFRD_REV_FLG";

    /** DB Item Column Name */
    static final String AJE_INV_ACCT_DIST_SQ = "AJE_INV_ACCT_DIST_SQ";

    /** DB Item Column Name */
    static final String PROC_DT = "PROC_DT";

    /** DB Item Column Name */
    static final String GL_DT = "GL_DT";

    //START 2018/04/04 E.Kameishi [QC#24870,ADD]
    /** DB Item Column Name */
    static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";
    //END 2018/04/04 E.Kameishi [QC#24870,ADD]

    //START 2018/04/13 E.Kameishi [QC#25489,ADD]
    /** DB Item Column Name */
    static final String CPO_ORD_NUM = "CPO_ORD_NUM";
    //END 2018/04/13 E.Kameishi [QC#25489,ADD]

}
