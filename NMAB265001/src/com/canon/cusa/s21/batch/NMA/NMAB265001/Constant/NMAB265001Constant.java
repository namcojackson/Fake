/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB265001.Constant;

/**
 * <pre>
 * NMAB2650 Sales Territory Visibility Defaulting Processor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         K.Minamide      Create          N/A
 * 2016/07/05   Fujitsu         R.Nakamura      Update          QC#5909
 * 2017/08/24   Fujitsu         W.Honda         Update          QC#20535,20538
 * 2018/03/20   Fujitsu         Mz.Takahashi    Update          QC#23671(Second Fix)
 * 2018/06/01   Fujitsu         Hd.Sugawara     Update          QC#24293
 * 2018/08/22   Fujitsu         M.Ohno          Update          QC#26772
 *</pre>
 */
public class NMAB265001Constant {

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** Mode :1 Territory to Location */
    public static final String MODE_TERRITORY_TO_LOCATION = "1";

    /** Data insert failure. (table name is [@]) */
    public static final String MMAM0003E = "MMAM0003E";

    // Add Start 2016/07/05 QC#5909
    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;

    /** Table Name : TRTY_RULE_LOC_RELN_LOG */
    public static final String TABLE_TRTY_RULE_LOC_RELN_LOG = "TRTY_RULE_LOC_RELN_LOG";

    /** Table Name : TRTY_RULE_LOC_RELN */
    public static final String TABLE_TRTY_RULE_LOC_RELN = "TRTY_RULE_LOC_RELN";

    /** Table Name : TRTY_RULE_PROS_RELN_LOG */
    public static final String TABLE_TRTY_RULE_PROS_RELN_LOG = "TRTY_RULE_LOC_RELN_LOG";

    /** Table Name : TRTY_RULE_PROS_RELN */
    public static final String TABLE_TRTY_RULE_PROS_RELN = "TRTY_RULE_PROS_RELN";
    // Add End 2016/07/05 QC#5909

    // Add Start 2017/08/24 QC#20535,20538
    // NMAB265002 Start
//    /** [@] is invalid value */
//    public static final String ZZZM9026E = "ZZZM9026E";

    // 2016/03/16 QC#4443 Del Start
    ///** Mode :1 Territory to Location */
    // public static final String MODE_TERRITORY_TO_LOCATION = "1";
    // 2016/End/16 QC#4443 Del Start

//    /** Data insert failure. (table name is [@]) */
//    public static final String MMAM0003E = "MMAM0003E";

    // 2016/03/16 QC#4443 Add Start
    /** Territory Rule Type Code :State=1, Poatal Code=2, Location Number=4
     * , Account Classification=5, Customer Group=6, SIC Code=7, Account Number=10 */
    public static final String CONST_TRTY_RULE_TP = "NMAB2650_TRTY_RULE_TP";
    // 2016/03/16 QC#4443 Add End

    // Add Start 2018/06/01 QC#24293
    /** Territory Rule Operand Type Code :Equal=2, Between=4, In=5 */
    public static final String CONST_TRTY_RULE_OPRD_TP = "NMAB2650_TRTY_RULE_OPRD_TP";
    // Add End 2018/06/01 QC#24293

    // 2018/03/20 QC#23671(Second Fix) Add Start
    /** DB Owner */
    public static final String CONST_DB_SCHEMA = "NMAB2650_DB_SCHEMA";

    /** Tables subject to "DBMS_STATS.GATHER_TABLE_STATS" */
    public static final String CONST_CUST_GATHER_TABLE_INIT = "NMAB2650_CUST_GATHER_INIT";

    /** Tables subject to "DBMS_STATS.GATHER_TABLE_STATS" */
    public static final String CONST_PROS_GATHER_TABLE_INIT = "NMAB2650_PROS_GATHER_INIT";

    /** Tables subject to "DBMS_STATS.GATHER_TABLE_STATS" */
    public static final String CONST_CUST_GATHER_TABLE_BEFORE_INS_LOC = "NMAB2650_CUST_GATHER_INS_LOC";

    /** Tables subject to "DBMS_STATS.GATHER_TABLE_STATS" */
    public static final String CONST_PROS_GATHER_TABLE_BEFORE_INS_LOC = "NMAB2650_PROS_GATHER_INS_LOC";
    // 2018/03/20 QC#23671(Second Fix) Add End

    // 2017/04/19 RS#8275 Mod Start
    // 2016/03/16 QC#4443 Del Start
    ///** Max Rules to send API  */
    // public static final Integer MAX_RULE_CNT = 10000;
    // 2016/03/16 QC#4443 Del End
    // 2016/03/29 QC#5924 Add Start
    /** Conditional Expression :AND */
    public static final String COND_EXP_AND = " AND ";
    /** Conditional Expression :ST_CD */
    public static final String COND_EXP_TRTY_RULE_TP_STATE = "PLW.ST_CD ";
    /** Conditional Expression :POST_CD */
    public static final String COND_EXP_TRTY_RULE_TP_POSTAL_CODE = "PLW.POST_CD  ";
    // Mod Start 2018/06/01 QC#24293
    ///** Conditional Expression :DS_ACCT_NM */
    //public static final String COND_EXP_TRTY_RULE_TP_ACCOUNT_NAME = "ACCT.DS_ACCT_NM ";
    /** Conditional Expression :DS_ACCT_NUM */
    public static final String COND_EXP_TRTY_RULE_TP_ACCOUNT_NUMBER_CUSTOMER = "AL.DS_ACCT_NUM ";
    /** Conditional Expression :DS_ACCT_NUM */
    public static final String COND_EXP_TRTY_RULE_TP_ACCOUNT_NUMBER_PROSPECT = "ACCT.DS_ACCT_NUM ";
    // Mod End 2018/06/01 QC#24293
    /** Conditional Expression :LOC_NUM */
    public static final String COND_EXP_TRTY_RULE_TP_ACCOUNT_OR_SITE_NUMBER = "ACCT.LOC_NUM ";
    // 2018/08/22 QC#26772 add start
    /** Conditional Expression :LOC_NUM */
    public static final String COND_EXP_TRTY_RULE_TP_ACCOUNT_OR_SITE_NUMBER_CUSTOMER = "AL.LOC_NUM ";
    // 2018/08/22 QC#26772 add end
    /** Conditional Expression :DS_ACCT_CLS_CD */
    public static final String COND_EXP_TRTY_RULE_TP_ACCOUNT_CLASSIFICATION = "ACCT.DS_ACCT_CLS_CD ";
    /** Conditional Expression :DS_ACCT_GRP_CD */
    public static final String COND_EXP_TRTY_RULE_TP_CUSTOMERGROUP = "DAGA.DS_ACCT_GRP_CD ";
    /** Conditional Expression :DS_CUST_SIC_CD */
    public static final String COND_EXP_TRTY_RULE_TP_SIC_CODE = "PLW.DS_CUST_SIC_CD ";
//    /** Conditional Expression :ST_CD */
//    public static final String COND_EXP_TRTY_RULE_TP_STATE = "ST_CD ";
//    /** Conditional Expression :POST_CD */
//    public static final String COND_EXP_TRTY_RULE_TP_POSTAL_CODE = "POST_CD  ";
//    /** Conditional Expression :DS_ACCT_NM */
//    public static final String COND_EXP_TRTY_RULE_TP_ACCOUNT_NAME = "DS_ACCT_NM ";
//    /** Conditional Expression :LOC_NUM */
//    public static final String COND_EXP_TRTY_RULE_TP_ACCOUNT_OR_SITE_NUMBER = "LOC_NUM ";
//    /** Conditional Expression :DS_ACCT_CLS_CD */
//    public static final String COND_EXP_TRTY_RULE_TP_ACCOUNT_CLASSIFICATION = "DS_ACCT_CLS_CD ";
//    /** Conditional Expression :DS_ACCT_GRP_CD */
//    public static final String COND_EXP_TRTY_RULE_TP_CUSTOMERGROUP = "DS_ACCT_GRP_CD ";
//    /** Conditional Expression :DS_CUST_SIC_CD */
//    public static final String COND_EXP_TRTY_RULE_TP_SIC_CODE = "DS_CUST_SIC_CD ";
    // 2017/04/19 RS#8275 Mod End
    /** Conditional Expression :EQUAL */
    public static final String COND_EXP_TRTY_RULE_OPRD_TP_EQUAL = "= '@' ";
    /** Conditional Expression :BETWEEN */
    public static final String COND_EXP_TRTY_RULE_OPRD_TP_BETWEEN = "BETWEEN '@' AND '@' ";
    // Del Start 2018/06/01 QC#24293
    ///** Conditional Expression :LIKE */
    //public static final String COND_EXP_TRTY_RULE_OPRD_TP_LIKE = "LIKE '@' ";
    // Del End 2018/06/01 QC#24293
    /** Conditional Expression :Replace String */
    public static final String COND_EXP_REPLACE_STR = "@";
    // 2016/03/29 QC#5924 Add End

    // Add Start 2016/07/06 QC#5909
    /** Fetch Size */
//    public static final int FETCH_SIZE = 1000;
    // Add End 2016/07/06 QC#5909
    
    // QC#5786
    /** Character : Comma */
    public static final String CHAR_COMMA = ",";
    // 2017/04/19 RS#8275 Add Start
    // Del Start 2018/06/01 QC#24293
    ///** trty Rule Oprd Tp Cd  */
    //public static final int TRTY_RULE_LOGIC_TP_PTN = 5;
    // Del End 2018/06/01 QC#24293
    // 2017/04/19 RS#8275 Add End
    // NMAB265002 End

    // NMAB265003 Start

//    /** [@] is invalid value */
//    public static final String ZZZM9026E = "ZZZM9026E";
//
//    /** Mode :1 Territory to Location */
//    public static final String MODE_TERRITORY_TO_LOCATION = "1";
//
//    /** Data insert failure. (table name is [@]) */
//    public static final String MMAM0003E = "MMAM0003E";

//    // Add Start 2016/07/06 QC#5909paramsATRAL
//    /** Fetch Size */
//    public static final int FETCH_SIZE = 1000;

    /** Table Name : ACCT_TRTY_RESRC_LOG */
    public static final String TABLE_ACCT_TRTY_RESRC_LOG = "ACCT_TRTY_RESRC_LOG";
    // Add Start 2017/05/09 RS#8275
    /** Table Name : PROS_TRTY_RESRC_LOG */
    public static final String TABLE_PROS_TRTY_RESRC_LOG = "PROS_TRTY_RESRC_LOG";
    // Add End 2017/05/09 RS#8275
    /** Table Name : ACCT_TRTY_ROLE_ASG_LOG */
    public static final String TABLE_ACCT_TRTY_ROLE_ASG_LOG = "ACCT_TRTY_ROLE_ASG_LOG";

    /** Table Name : ACCT_TRTY_ROLE_ASG */
    public static final String TABLE_ACCT_TRTY_ROLE_ASG = "ACCT_TRTY_ROLE_ASG";

    /** Table Name : PROS_TRTY_ROLE_ASG_LOG */
    public static final String TABLE_PROS_TRTY_ROLE_ASG_LOG = "PROS_TRTY_ROLE_ASG_LOG";

    /** Table Name : PROS_TRTY_ROLE_ASG */
    public static final String TABLE_PROS_TRTY_ROLE_ASG = "PROS_TRTY_ROLE_ASG";
    // Add End 2016/07/06 QC#5909paramsATRAL

    // Add Start 2017/05/09 RS#8275
    /** Table Name : ACCT_TRTY_RESRC_ASG */
    public static final String TABLE_ACCT_TRTY_RESRC_ASG = "ACCT_TRTY_RESRC_ASG";

    /** Table Name : PROS_TRTY_RESRC_ASG */
    public static final String TABLE_PROS_TRTY_RESRC_ASG = "PROS_TRTY_RESRC_ASG";
    // Add End 2017/05/09 RS#8275
    // NMAB265003 End

    // NMAB265004 Start

    /** [@] is invalid value */
//    public static final String ZZZM9026E = "ZZZM9026E";
//
//    /** Mode :1 Territory to Location */
//    public static final String MODE_TERRITORY_TO_LOCATION = "1";
//
//    /** Data insert failure. (table name is [@]) */
//    public static final String MMAM0003E = "MMAM0003E";
//
//    // Add Start 2016/07/06 QC#5909paramsATRAL
//    /** Fetch Size */
//    public static final int FETCH_SIZE = 1000;
    // Add End 2016/07/06 QC#5909paramsATRAL

    /** Org Rank Number Max */
    public static final String ORG_RANK_NUM_MAX = "999999999999";
    // NMAB265004 End

    // Add End 2017/08/24 QC#20535,20538
}
