/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB008501;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Daily Process for WDS NA
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2016   CUSA            Y.Aikawa        Create          N/A
 * 10/25/2016   Hitachi         Y.Tsuchimoto    Update          QC#14501
 * 03/14/2018   CITS            K.Kameoka       Update          QC#20316(Sol#202)
 * 05/31/2018   Hitachi         Y.Takeno        Update          QC#26158
 * 09/05/2018   Fujitsu         S.Ohki          Update          QC#28040
 * 04/08/2024   Hitachi         Y.Ogura         Update          QC#63871
 * </pre>
 */
public interface NFBB008501Constant {
    // ** Fixed Value ** //
    // ** Common ** //
    /** Bulk Commit Limit */
    static final int INT_BULK_COM_LIM = 10000;
    /** Int 5 */
    static final int INT_5 = 5;
    /** Strings 0 */
    static final String STR_0 = "0";
    /** Empty String */
    static final String EMPTY_STRING = "";

    // ** Message ID ** //
    /** Error Message */
    static final String NFBM0028E = "NFBM0028E";
    /** Error Message (Unique Constraint Violation) */
    static final String ZZBM0074E = "ZZBM0074E";

    // ** ACCT_INV_STS_CD ** //
    /** ACCT_INV_STS_CD 20 (AUTHORIZED)  */
    static final String ACCT_INV_STS_CD_20 = "20";
    /** ACCT_INV_STS_CD 21 (AUTO RELEASED)  */
    static final String ACCT_INV_STS_CD_21 = "21";
    /** ACCT_INV_STS_CD 30 (COST LINK)  */
    static final String ACCT_INV_STS_CD_30 = "30";

    // ** REM_SQ_NUM ** //
    /** REM_SQ_NUM 001 */
    static final String REM_SQ_NUM_001 = "001";

    // ** VND_INV_SQ_NUM ** //
    /** VND_INV_SQ_NUM 00 */
    static final String VND_INV_SQ_NUM_00 = "00";

    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_RECORD = "SELECT_RECORD";
    /** SSM Statement ID */
    static final String SELECT_OPEN_AP_HDR_RECORD = "SELECT_OPEN_AP_HDR_RECORD";
    /** SSM Statement ID */
    static final String SELECT_OPEN_AP_DTL_RECORD = "SELECT_OPEN_AP_DTL_RECORD";
// QC#20316(Sol#202) START
    /** SSM Statement ID */
    static final String SELECT_CM_AP_INV_HDR_RECORD_FOR_UPDATE = "SELECT_CM_AP_INV_HDR_RECORD_FOR_UPDATE";
// QC#20316(Sol#202) END

    // ** CM_DEF_ACCT_CD ** //
    /** CM_DEF_ACCT_CD FREIGHT */
    static final String CM_DEF_ACCT_CD_FREIGHT = "FREIGHT";
    /** CM_DEF_ACCT_CD TAX */
    static final String CM_DEF_ACCT_CD_TAX = "TAX";
    /** CM_DEF_ACCT_CD VAR */
    static final String CM_DEF_ACCT_CD_VAR = "VAR";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String AP_INV_ID = "AP_INV_ID";
    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";
    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";
    /** DB Item Column Name */
    static final String AP_VND_INV_SQ_NUM = "AP_VND_INV_SQ_NUM";
 // QC#20316(Sol#202) START
    /** DB Item Column Name */
    static final String AP_VND_INV_LINE_NUM = "AP_VND_INV_LINE_NUM";
    /** DB Item Column Name */
    static final String CM_INV_ACCT_DIST_LINE_NUM = "CM_INV_ACCT_DIST_LINE_NUM";
 // QC#20316(Sol#202) END
    /** DB Item Column Name */
    static final String INV_DT = "INV_DT";
    /** DB Item Column Name */
    static final String AC_INV_TOT_COST_AMT = "AC_INV_TOT_COST_AMT";
    /** DB Item Column Name */
    static final String AP_INV_SRC_NM = "AP_INV_SRC_NM";
    /** DB Item Column Name */
    static final String AP_INV_DESC_TXT = "AP_INV_DESC_TXT";
    /** DB Item Column Name */
    static final String COA_CMPY_CD = "COA_CMPY_CD";
    /** DB Item Column Name */
    static final String COA_BR_CD = "COA_BR_CD";
    /** DB Item Column Name */
    static final String COA_CC_CD = "COA_CC_CD";
    /** DB Item Column Name */
    static final String COA_ACCT_CD = "COA_ACCT_CD";
    /** DB Item Column Name */
    static final String COA_PROD_CD = "COA_PROD_CD";
    /** DB Item Column Name */
    static final String COA_CH_CD = "COA_CH_CD";
    /** DB Item Column Name */
    static final String COA_AFFL_CD = "COA_AFFL_CD";
    /** DB Item Column Name */
    static final String COA_PROJ_CD = "COA_PROJ_CD";
    /** DB Item Column Name */
    static final String COA_EXTN_CD = "COA_EXTN_CD";
 // QC#20316(Sol#202) START
    /** DB Item Column Name */
    static final String CR_COA_CMPY_CD = "CR_COA_CMPY_CD";
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
// QC#20316(Sol#202) START 
    /** DB Item Column Name */
    static final String VND_PMT_TERM_CD = "VND_PMT_TERM_CD";
    /** DB Item Column Name */
    static final String ACCT_BANK_CD = "ACCT_BANK_CD";
    /** DB Item Column Name */
    static final String ACCT_BANK_ACCT_PAY_TP_CD = "ACCT_BANK_ACCT_PAY_TP_CD";
    /** DB Item Column Name */
    static final String PMT_DUE_DT = "PMT_DUE_DT";
    /** DB Item Column Name */
    static final String ACTL_EXCH_RATE = "ACTL_EXCH_RATE";
    /** DB Item Column Name */
    static final String CCY_CD = "CCY_CD";
    /** DB Item Column Name */
    static final String AP_INV_LINE_NUM = "AP_INV_LINE_NUM";
    /** DB Item Column Name */
    static final String AC_INV_JRNL_COST_AMT = "AC_INV_JRNL_COST_AMT";
    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";
    /** DB Item Column Name */
    static final String AP_INV_TP_CD = "AP_INV_TP_CD";
    /** DB Item Column Name */
    static final String PO_NUM = "PO_NUM";
    /** DB Item Column Name */
    static final String DELY_ORD_NUM = "DELY_ORD_NUM";
    // START 2016/10/25 Y.Tsuchimoto [QC#14501,ADD]
    /** DB Item Column Name */
    public static final String INV_AUTH_DT = "INV_AUTH_DT";
    // END   2016/10/25 Y.Tsuchimoto [QC#14501,ADD]
    // START 2018/05/31 [QC#26158 ,ADD]
    public static final String PAY_ALONE_FLG = "PAY_ALONE_FLG";
    // END   2018/05/31 [QC#26158 ,ADD]
    // START 2018/09/05 [QC#28040, ADD]
    public static final String INV_HDR_DESC_TXT = "INV_HDR_DESC_TXT";
    // END 2018/09/05 [QC#28040, ADD]
    // START 2024/4/8 Y.Ogura [QC#63871, ADD]
    /** DS_COND_CONST_GRP_ID */
    public static final String NFBB0085_FILTER_TXT = "NFBB0085_FILTER_TXT";
    /** SPACE */
    public static final String SPACE = " ";
    // END 2024/4/8 Y.Ogura [QC#63871, ADD]
}
