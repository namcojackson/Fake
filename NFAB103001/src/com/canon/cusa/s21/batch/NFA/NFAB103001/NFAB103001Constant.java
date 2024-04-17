package com.canon.cusa.s21.batch.NFA.NFAB103001;

/**
 * Deferred Revenue Recognition
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 2016/09/29   Hitachi         K.Kojima        Update          QC#14609
 * 2016/02/09   Fujitsu         S.Fujita        Update          QC#14655
 * 2022/03/17   CITS            D.Mamaril       Update          QC#59657
 *</pre>
 */
public interface NFAB103001Constant {

    // START 2016/09/29 K.Kojima [QC#14609,ADD]
    /** Business Application ID */
    String BUZ_APP_ID = "NFAB1030";
    // END 2016/09/29 K.Kojima [QC#14609,ADD]

    /** business_id : NFAB1030 */
    String BUSINESS_ID = "NFAB103001";
    
    /** Message */
    String ZZZM9025E = "ZZZM9025E";
    
    /** Message */
    String UNIQUE_ERROR = "ZZBM0074E";
    
    /** Message */
    String NFAM0035E = "NFAM0035E";
    
    /** */
    String REV_RECOG_STS_NOT_PROCESSED = "0";
    
    /** */
    String REV_RECOG_STS_IN_PROCESS = "2";
    
    /** */
    int ERR_MSG_TXT_LEN = 1000;
    
    /** Fixed Value : COA */
    String COA_ERROR_MSG_ID = "NFAM0063E";
    
    /** Sequence */
    String AJE_INV_ACCT_DIST_SQ = "AJE_INV_ACCT_DIST_SQ";
    
    /** Sequence */
    String AJE_INV_ACCT_DIST_ERR_SQ = "AJE_INV_ACCT_DIST_ERR_SQ";
    
    /** Insert Count*/
    int BULK_INSERT_CNT =  1000;

    // START 2016/09/12 [QC#14349, ADD]
    /** Fixed Value:*/
    String FIXED_VALUE_UR =  "UnearnedRevenue";

    /** Fixed Value:*/
    String FIXED_VALUE_SRT =  "ServiceRevenueTransfer";
    // END 2016/09/12 [QC#14349, ADD]

    // START 2017/02/09 S.Fujita [QC#14655,MOD]
    /** VAR_CHAR_CONST : AJE_CONTR_ACTV_STS */
    String AJE_CONTR_ACTV_STS = "AJE_CONTR_ACTV_STS";

    /** VAR_CHAR_CONST : AJE_CONTR_INACTV_STS */
    String AJE_CONTR_INACTV_STS = "AJE_CONTR_INACTV_STS";

    /** Comma */
    String STR_COMMA = ",";

    /** MAX_END_DATE */
    String MAX_END_DATE = "99991231";
    // END   2017/02/09 S.Fujita [QC#14655,MOD]

    // START 2022/03/17 D.Mamaril [QC#59657, ADD]
    /** VAR_CHAR_CONST : NFAB1030_DEFAULT_TERM_MTH_NUM */
    String NFAB1030_DEFAULT_TERM_MTH_NUM = "NFAB1030_DEFAULT_TERM_MTH_NUM";
    // END 2022/03/17 D.Mamaril [QC#59657, ADD]
}
