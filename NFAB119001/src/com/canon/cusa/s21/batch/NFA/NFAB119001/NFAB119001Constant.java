package com.canon.cusa.s21.batch.NFA.NFAB119001;

/**
 * Credit Memo Journalize For Inventory
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 02/19/2018   Hitachi         E.Kameishi      Create          N/A
 * 05/25/2018   Hitachi         E.Kameishi      Update          QC#26098
 * 05/13/2019   Hitachi         E.Kameishi      Update          QC#50173
 *</pre>
 */
public interface NFAB119001Constant {

    // ** Fixed Value ** //
    /** Fixed Value : Error Message Parameter. */
    static final String MSG_PARAM = "Credit Memo Journalize For Inventory";

    /** business_id : NFAB1190 */
    String BUSINESS_ID = "NFAB119001";

    /** Message */
    String NFAM0035E = "NFAM0035E";

    /** Message */
    String ZZZM9025E = "ZZZM9025E";

    /** Message */
    String UNIQUE_ERROR = "ZZBM0074E";

    /** Insert Count*/
    int BULK_INSERT_CNT =  1000;

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB Item Column Name */
    static final String INVTY_TRX_PK = "INVTY_TRX_PK";

    /** DB Item Column Name */
    static final String DR_COA_CMPY_CD = "DR_COA_CMPY_CD";

    /** DB Item Column Name */
    static final String DR_COA_BR_CD = "DR_COA_BR_CD";

    /** DB Item Column Name */
    static final String DR_COA_CC_CD = "DR_COA_CC_CD";

    /** DB Item Column Name */
    static final String DR_COA_ACCT_CD = "DR_COA_ACCT_CD";

    /** DB Item Column Name */
    static final String DR_COA_PROD_CD = "DR_COA_PROD_CD";

    /** DB Item Column Name */
    static final String DR_COA_CH_CD = "DR_COA_CH_CD";

    /** DB Item Column Name */
    static final String DR_COA_AFFL_CD = "DR_COA_AFFL_CD";

    /** DB Item Column Name */
    static final String DR_COA_PROJ_CD = "DR_COA_PROJ_CD";

    /** DB Item Column Name */
    static final String DR_COA_EXTN_CD = "DR_COA_EXTN_CD";

    // START 2019/05/13 E.Kameishi [QC#50173,ADD]
    /** DB Item Column Name */
    static final String CR_COA_BR_CD = "CR_COA_BR_CD";

    /** DB Item Column Name */
    static final String CR_COA_CC_CD = "CR_COA_CC_CD";

    /** DB Item Column Name */
    static final String CR_COA_ACCT_CD = "CR_COA_ACCT_CD";

    /** DB Item Column Name */
    static final String CR_COA_PROD_CD = "CR_COA_PROD_CD";

    /** DB Item Column Name */
    static final String CR_COA_CH_CD = "CR_COA_CH_CD";

    /** DB Item Column Name */
    static final String CR_COA_AFFL_CD = "CR_COA_AFFL_CD";

    /** DB Item Column Name */
    static final String CR_COA_PROJ_CD = "CR_COA_PROJ_CD";

    /** DB Item Column Name */
    static final String CR_COA_EXTN_CD = "CR_COA_EXTN_CD";

    /** DB Item Column Name */
    static final String AJE_INVTY_INTFC_SQ = "AJE_INVTY_INTFC_SQ";
    // END 2019/05/13 E.Kameishi [QC#50173,ADD]
    // START 2018/05/25 E.Kameishi [QC#26098,ADD]
    /** DB Item Column Name */
    static final String TOC_CD = "TOC_CD";
    // END 2018/05/25 E.Kameishi [QC#26098,ADD]
}
