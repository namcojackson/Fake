/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB124001;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Invoice Import For Compensation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/30/2016   CUSA            Y.Aikawa        Create          N/A
 * 07/14/2016   Hitachi         T.Tsuchida      Update          QC#11621
 * 12/19/2017   Hitachi         Y.Takeno        Update          QC#23022
 * 03/14/2018   CITS            K.Kameoka       Update          QC#20316(Sol#202)
 * </pre>
 */
public interface NFBB124001Constant {

    // ** Fixed Value ** //
    // ** Common ** //
    /** Empty String */
    static final String EMPTY_STRING = "";
    /** yyyyMMdd */
    static final String YYYYMMDD = "yyyyMMdd";
    /** Bulk Commit Limit */
    static final int INT_BULK_COM_LIM = 10000;
    /** AP_VND_INV_SQ_NUM 00 */
    static final String AP_VND_INV_SQ_NUM_00 = "00";
    /** AP_INV_TP_CD 00 */
    static final String AP_INV_TP_CD_00 = "00";
    /** FLG_OFF_N */
    static final String FLG_OFF_N = ZYPConstant.FLG_OFF_N;

    /** VAR_CHAR_CONST Key : Canon USA Vendor Code */
    static final String CONST_KEY_CUSA_AP_VND_CD = "CUSA_AP_VND_CD";
    /** VAR_CHAR_CONST Key : NFBB124001_ACCT_INV_STS_CD */
    static final String CONST_KEY_NFBB124001_ACCT_INV_STS_CD = "NFBB124001_ACCT_INV_STS_CD";
    /** VAR_CHAR_CONST Key : AP_LINE_TP_ITEM */
    static final String CONST_KEY_AP_LINE_TP_ITEM = "AP_LINE_TP_ITEM";

    // ** AP_INV_CATG_CD ** //
    /** AP_INV_CATG_CD ROSS */
    static final String AP_INV_CATG_CD_03 = "03";

    // ** AP_INV_ROSS_STS_CD ** //
    /** AP_INV_ROSS_STS_CD 00 */
    static final String AP_INV_ROSS_STS_CD_00 = "00";
    /** AP_INV_ROSS_STS_CD 99 */
    static final String AP_INV_ROSS_STS_CD_99 = "99";

    // ** DS_ORD_CATG_CD ** //
    /** DS_ORD_CATG_CD EMSD, CSA */
    static final String DS_ORD_CATG_CD_0005 = "0005";
    
    // ** COA_BR_CD ** //
    /** COA_BR_CD 201 */
    static final String COA_BR_CD_201 = "201";

    // ** COA_CC_CD ** //
    /** COA_CC_CD 894103 */
    static final String COA_CC_CD_894103 = "894103";

    // ** COA_PROJ_CD ** //
    /** COA_PROJ_CD 10 */
    static final String COA_PROJ_CD_10 = "10";
    /** COA_PROJ_CD 73 */
    static final String COA_PROJ_CD_73 = "73";

    // ** SLS_REP_ROLE_TP_CD ** //
    /** SLS_REP_ROLE_TP_CD ESSI */
    static final String SLS_REP_ROLE_TP_CD_ESSI = "ESSI";

    // ** Message ID ** //
    /** Error Message (Unexpected Error Occurred) */
    static final String NFBM0028E = "NFBM0028E";
    /** Error Message (unique constraint violated.) **/
    static final String ZZBM0074E = "ZZBM0074E";

    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_AP_INV_ROSS = "SELECT_AP_INV_ROSS";
    /** SSM Statement ID */
    static final String SELECT_CUSA_VND = "SELECT_CUSA_VND";
    /** SSM Statement ID */
    static final String GET_SVC_MACH_MSTR_BY_SER_NUM = "GET_SVC_MACH_MSTR_BY_SER_NUM";
    /** SSM Statement ID */
    static final String GET_DS_CPO_SLS_CR = "GET_DS_CPO_SLS_CR";
    /** SSM Statement ID */
    static final String SELECT_AP_INV_ROSS_BY_PARTIAL_VALUE = "SELECT_AP_INV_ROSS_BY_PARTIAL_VALUE";
    /** SSM Statement ID */
    static final String SELECT_PRNT_VND_CD = "SELECT_PRNT_VND_CD";
    /** SSM Statement ID */
    static final String SELECT_AC_INV_JRNL_COST_AMT = "SELECT_AC_INV_JRNL_COST_AMT";
    /** SSM Statement ID */
    static final String SELECT_VND_DEAL_CCY_CD = "SELECT_VND_DEAL_CCY_CD";
    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String RTL_INV_NUM = "RTL_INV_NUM";
    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";
    /** DB Item Column Name */
    static final String SHIP_QTY = "SHIP_QTY";
    /** DB Item Column Name */
    static final String AC_INV_JRNL_COST_AMT = "AC_INV_JRNL_COST_AMT";
    /** DB Item Column Name */
    static final String SER_NUM = "SER_NUM";
    /** DB Item Column Name */
    static final String INV_DT = "INV_DT";
    /** DB Item Column Name */
    static final String SPLY_COA_CMPY_CD = "SPLY_COA_CMPY_CD";
    /** DB Item Column Name */
    static final String SPLY_COA_BR_CD = "SPLY_COA_BR_CD";
    /** DB Item Column Name */
    static final String SPLY_COA_CC_CD = "SPLY_COA_CC_CD";
    /** DB Item Column Name */
    static final String SPLY_COA_ACCT_CD = "SPLY_COA_ACCT_CD";
    /** DB Item Column Name */
    static final String SPLY_COA_PROD_CD = "SPLY_COA_PROD_CD";
    /** DB Item Column Name */
    static final String SPLY_COA_CH_CD = "SPLY_COA_CH_CD";
    /** DB Item Column Name */
    static final String SPLY_COA_AFFL_CD = "SPLY_COA_AFFL_CD";
    /** DB Item Column Name */
    static final String SPLY_COA_PROJ_CD = "SPLY_COA_PROJ_CD";
    /** DB Item Column Name */
    static final String SPLY_COA_EXTN_CD = "SPLY_COA_EXTN_CD";
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
    /** DB Item Column Name */
    static final String DS_ORD_CATG_CD = "DS_ORD_CATG_CD";
    /** DB Item Column Name */
    static final String CPO_ORD_NUM = "CPO_ORD_NUM";
    /** DB Item Column Name */
    static final String FLD_SVC_BR_CD = "FLD_SVC_BR_CD";
    /** DB Item Column Name */
    static final String SLS_REP_ROLE_TP_CD = "SLS_REP_ROLE_TP_CD";
    /** DB Item Column Name */
    static final String SLS_REP_TOC_CD = "SLS_REP_TOC_CD";
    /** DB Item Column Name */
    static final String RDSTC_SELL_TO_CUST_CD = "RDSTC_SELL_TO_CUST_CD";
    /** DB Item Column Name */
    static final String RTL_INV_PK = "RTL_INV_PK";
    /** DB Item Column Name */
    static final String RTL_INV_LINE_PK = "RTL_INV_LINE_PK";
    /** DB Item Column Name */
    static final String AP_INV_ROSS_STS_CD = "AP_INV_ROSS_STS_CD";
    /** DB Item Column Name */
    static final String PRNT_VND_CD = "PRNT_VND_CD";
    // START 2016/09/29 [QC#14798, ADD]
    /** DB Item Column Name */
    static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
    /** DB Item Column Name */
    static final String CM_INV_ACCT_DIST_LINE_NUM = "CM_INV_ACCT_DIST_LINE_NUM";
    // END 2016/09/29 [QC#14798, ADD]
    // QC#20316(Sol#202) START
    /** DB Item Column Name */
    static final String RTL_INV_LINE_NUM = "RTL_INV_LINE_NUM";
    /** DB Item Column Name */
    static final String AP_VND_INV_LINE_NUM = "AP_VND_INV_LINE_NUM";
    // QC#20316(Sol#202) END

    // START 2017/12/19 [QC#23022, ADD]
    /** DB Item Column Name : RTL_DIV_CD */
    static final String RTL_DIV_CD = "RTL_DIV_CD";
    /** DB Item Column Name : RTL_DIV_CD */
    static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";
    /** DB Item Column Name : RTL_DIV_CD */
    static final String ITRL_RTL_SMRY_INV_NUM = "ITRL_RTL_SMRY_INV_NUM";
    /** SPACE */
    static final String SPACE = " ";
    // END   2017/12/19 [QC#23022, ADD]
}
