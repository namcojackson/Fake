/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2550.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/06/20   Hitachi         K.Kojima        Update          QC#10316
 * 2016/11/25   Fujitsu         R.Nakamura      Update          QC#15438
 * 2017/02/06   Hitachi         E.Kameishi      Update          QC#16904
 * 2017/02/06   Fujitsu         H.Ikeda         Update          QC#23696
 *</pre>
 */
public class NMAL2550Constant {

    /**
     * Business application id.
     */
    public static final String BIZ_AP_ID = "NMAL2550";

    /** COMPANY CODE */
    public static final String COMPANY_CODE = "Company Code";

    // Mod Start 2016/11/25 QC#15438
    /** AFFILIATE CODE */
//    public static final String AFFILIATE_CODE = "Affiliate Code";
    public static final String AFFILIATE_CODE = "Intercompany Code";
    // Mod End 2016/11/25 QC#15438

    /** BRANCH CODE */
    public static final String BRANCH_CODE = "Branch Code";

    /** COST CENTER CODE */
    public static final String COST_CENTER_CODE = "Cost Center Code";

    /** ACCOUNT CODE */
    public static final String ACCOUNT_CODE = "Account Code";

    /** PRODUCT CODE */
    public static final String PRODUCT_CODE = "Product Code";

    /** CHANNEL CODE */
    public static final String CHANNEL_CODE = "Channel Code";

    // Mod Start 2016/11/25 QC#15438
    /** PROJECT CODE */
//    public static final String PROJECT_CODE = "Project Code";
    public static final String PROJECT_CODE = "Merchandise Code";

    /** EXTENSION CODE */
//    public static final String EXTENSION_CODE = "Extension Code";
    public static final String EXTENSION_CODE = "Business Unit Code";
    // Mod End 2016/11/25 QC#15438

    /** COMPANY CODE */
    public static final int COMPANY = 0;

    /** AFFILIATE CODE */
    public static final int AFFILIATE = 1;

    /** BRANCH CODE */
    public static final int BRANCH = 2;

    /** COST CENTER CODE */
    public static final int COST_CENTER = 3;

    /** ACCOUNT CODE */
    public static final int ACCOUNT = 4;

    /** PRODUCT CODE */
    public static final int PRODUCT = 5;

    /** CHANNEL CODE */
    public static final int CHANNEL = 6;

    /** PROJECT CODE */
    public static final int PROJECT = 7;

    /** EXTENSION CODE */
    public static final int EXTENSION = 8;

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data */
    public static final String NZZM0001W = "NZZM0001W";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    // START 2017/02/06 E.Kameishi [QC#16904,MOD]
    // START 2016/06/20 K.Kojima [QC#10316,ADD]
    /** COA_GL_SEG_NOT_ALLW_SEC.COA_SEG_NM : Company */
    public static final String COA_SEG_NM_CMPY = "COA_CMPY";

    /** COA_GL_SEG_NOT_ALLW_SEC.COA_SEG_NM : Branch */
    public static final String COA_SEG_NM_BR = "COA_BR";

    /** COA_GL_SEG_NOT_ALLW_SEC.COA_SEG_NM : Cost center */
    public static final String COA_SEG_NM_CC = "COA_CC";

    /** COA_GL_SEG_NOT_ALLW_SEC.COA_SEG_NM : Account */
    public static final String COA_SEG_NM_ACCT = "COA_ACCT";

    /** COA_GL_SEG_NOT_ALLW_SEC.COA_SEG_NM : Product */
    public static final String COA_SEG_NM_PROD = "COA_PROD";

    /** COA_GL_SEG_NOT_ALLW_SEC.COA_SEG_NM : Channel */
    public static final String COA_SEG_NM_CH = "COA_CH";

    /** COA_GL_SEG_NOT_ALLW_SEC.COA_SEG_NM : Affiliate */
    public static final String COA_SEG_NM_AFFL = "COA_AFFL";

    /** COA_GL_SEG_NOT_ALLW_SEC.COA_SEG_NM : Project */
    public static final String COA_SEG_NM_PROJ = "COA_PROJ";

    /** COA_GL_SEG_NOT_ALLW_SEC.COA_SEG_NM : Business unit */
    public static final String COA_SEG_NM_EXTN = "COA_EXTN";
    // END 2016/06/20 K.Kojima [QC#10316,ADD]
    // END 2017/02/06 E.Kameishi [QC#16904,MOD]

    // START 2019/03/20 H.Ikeda [QC#23696, ADD]
    /** COAID_COA_CMPY */
    public static final String COAID_COA_CMPY = "1";
    /** COAID_COA_BR */
    public static final String COAID_COA_BR = "2";
    /** COAID_COA_CC */
    public static final String COAID_COA_CC = "3";
    /** COAID_COA_ACCT */
    public static final String COAID_COA_ACCT = "4";
    /** COAID_COA_PROD */
    public static final String COAID_COA_PROD = "5";
    /** COAID_COA_CH */
    public static final String COAID_COA_CH = "6";
    /** COAID_COA_AFFL */
    public static final String COAID_COA_AFFL = "7";
    /** COAID_COA_PROJ */
    public static final String COAID_COA_PROJ = "8";
    /** COAID_COA_EXTN */
    public static final String COAID_COA_EXTN = "9";
    /** COA_CMPY_CD */
    public static final String COA_CMPY_CD = "COA_CMPY_CD";
    /** COA_AFFL_CD */
    public static final String COA_AFFL_CD = "COA_AFFL_CD";
    /** COA_BR_CD */
    public static final String COA_BR_CD = "COA_BR_CD";
    /** COA_CC_CD */
    public static final String COA_CC_CD = "COA_CC_CD";
    /** COA_ACCT_CD */
    public static final String COA_ACCT_CD = "COA_ACCT_CD";
    /** COA_PROD_CD */
    public static final String COA_PROD_CD = "COA_PROD_CD";
    /** COA_CH_CD */
    public static final String COA_CH_CD = "COA_CH_CD";
    /** COA_PROJ_CD */
    public static final String COA_PROJ_CD = "COA_PROJ_CD";
    /** COA_EXTN_CD */
    public static final String COA_EXTN_CD = "COA_EXTN_CD";
    // END  2019/03/20 H.Ikeda [QC#23696, ADD]

}
