/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB126001.constant;

/**
 * <pre>
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/15   Hitachi         K.Kojima        Create          N/A
 * 2016/03/15   Hitachi         K.Kojima        Update          CSA QC#5115
 * 2016/03/16   Hitachi         K.Kojima        Update          CSA QC#5146
 * 2016/09/16   Hitachi         K.Kojima        Update          QC#14412
 * 2016/10/12   Hitachi         K.Kojima        Update          QC#13088
 * 2016/10/25   Hitachi         K.Kojima        Update          QC#13088
 * 2016/12/01   Hitachi         Y.Tsuchimoto    Update          QC#13088
 * 2016/12/12   Fujitsu         H.Ikeda         Update          QC#15823
 * 2017/06/23   CITS            T.Kikuhara      Update          QC#19412
 * </pre>
 */
public class NFBB126001Constant {

    /** BUSINESS_ID (NFBB1260) */
    public static final String BUSINESS_ID = "NFBB1260";

    /** PROGRAM_ID(NFBB1260) */
    public static final String PROGRAM_ID = BUSINESS_ID + "01";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Max Commit Number */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Error Msg : Global Company Code is required. */
    public static final String NFBM0199E = "NFBM0199E";

    /** Message Id : MFBM0207E */
    public static final String NFBM0207E = "NFBM0207E";

    /** Message Id : NFBM0208E */
    public static final String NFBM0208E = "NFBM0208E";

    /** Message Id : NFBM0209W */
    public static final String NFBM0209W = "NFBM0209W";

    /** Message Id : NFBM0028E */
    public static final String NFBM0028E = "NFBM0028E";

    // START 2016/12/13 H.Ikeda [QC#15823,MOD]
    /** Message Id : NFZM0028E */
    public static final String NFZM0028E = "NFZM0028E";
    // END   2016/12/13 H.Ikeda [QC#15823,MOD]

    /** limitThruDt */
    public static final String LIMIT_THRU_DT = "99991231";

    /** APVL_TP_TXT : Accounting(FIN) */
    public static final String APVL_TP_TXT_ACCOUNTING = "FIN";

    /** max size : LOC_NM */
    public static final int MAX_SIZE_LOC_NM = 15;

    /** Default LOC_NM */
    public static final String DEFAULT_LOC_NM = "ACMEC-000000001";

    /** LOC_NM exist check patern */
    public static final String REG_PTN_LOC_NM = "ACMEC-\\d{9}";

    /** LOC_NM first name */
    public static final String LOC_NM_FIRST_NM = "ACMEC-";

    // START 2016/09/16 K.Kojima [QC#14412,DEL]
    // /** SPLY_COA_VALUE */
    // // START 2016/03/15 K.Kojima [QC#5115,MOD]
    // // public static final String SPLY_COA_VALUE =
    // // "800,000,000,0000,31200,00,00,000,000";
    // public static final String[] SPLY_COA_VALUE = new String[]
    // {"800", "000", "000", "0000", "31200", "00", "00", "000", "000"
    // };
    //
    // // END 2016/03/15 K.Kojima [QC#5115,MOD]
    //
    // /** PRE_PMT_COA_VALUE */
    // // START 2016/03/15 K.Kojima [QC#5115,MOD]
    // // public static final String PRE_PMT_COA_VALUE =
    // // "800,000,000,0000,16130,00,00,000,000";
    // public static final String[] PRE_PMT_COA_VALUE = new String[]
    // {"800", "000", "000", "0000", "16130", "00", "00", "000", "000"
    // };
    //
    // // END 2016/03/15 K.Kojima [QC#5115,MOD]
    // END 2016/09/16 K.Kojima [QC#14412,DEL]

    /** String for binding AP_INV_NUM */
    public static final String STR_FOR_BINDING_AP_INV_NUM = "-";

    /** SPLY_COA_VALUE or PRE_PMT_COA_VALUE split number */
    public static final int SPLIT_CMPY_CD_NUMBER = 0;

    /** SPLY_COA_VALUE or PRE_PMT_COA_VALUE split number */
    public static final int SPLIT_BR_CD_NUMBER = 1;

    /** SPLY_COA_VALUE or PRE_PMT_COA_VALUE split number */
    public static final int SPLIT_CC_CD_NUMBER = 2;

    /** SPLY_COA_VALUE or PRE_PMT_COA_VALUE split number */
    public static final int SPLIT_ACCT_CD_NUMBER = 3;

    /** SPLY_COA_VALUE or PRE_PMT_COA_VALUE split number */
    public static final int SPLIT_PROD_CD_NUMBER = 4;

    /** SPLY_COA_VALUE or PRE_PMT_COA_VALUE split number */
    public static final int SPLIT_CH_CD_NUMBER = 5;

    /** SPLY_COA_VALUE or PRE_PMT_COA_VALUE split number */
    public static final int SPLIT_AFFL_CD_NUMBER = 6;

    /** SPLY_COA_VALUE or PRE_PMT_COA_VALUE split number */
    public static final int SPLIT_PROJ_CD_NUMBER = 7;

    /** SPLY_COA_VALUE or PRE_PMT_COA_VALUE split number */
    public static final int SPLIT_EXTN_CD_NUMBER = 8;

    /** VAR CHAR CONST : LSE_BO_DS_ORD_LINE_CATG_CD */
    public static final String LSE_BO_DS_ORD_LINE_CATG_CD = "LSE_BO_DS_ORD_LINE_CATG_CD";

    /** VAR CHAR CONST : CFS_COA_AFFL_CD */
    public static final String CFS_COA_AFFL_CD = "CFS_COA_AFFL_CD";

    /** VAR CHAR CONST : LEASE_BYOT_APVL_WF_ID */
    public static final String LEASE_BYOT_APVL_WF_ID = "LEASE_BYOT_APVL_WF_ID";

    // START 2016/03/16 K.Kojima [QC#5146,ADD]
    /** VAR CHAR CONST : LSE_BO_DEFAULT_SPLY_NM */
    public static final String LSE_BO_DEFAULT_SPLY_NM = "LSE_BO_DEFAULT_SPLY_NM";

    // END 2016/03/16 K.Kojima [QC#5146,ADD]

    // START 2016/09/16 K.Kojima [QC#14412,ADD]
    /** VAR CHAR CONST : SPLY_COA_VALUE */
    public static final String SPLY_COA_VALUE = "SPLY_COA_VALUE";

    /** VAR CHAR CONST : PRE_PMT_COA_VALUE */
    public static final String PRE_PMT_COA_VALUE = "PRE_PMT_COA_VALUE";

    // END 2016/09/16 K.Kojima [QC#14412,ADD]

    /** Oracle Sequence Number */
    public static final String LSE_BO_WF_PK = "LSE_BO_WF_PK";

    /** Column name : CPO_ORD_NUM */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** Column name : CPO_ORD_TP_CD */
    public static final String CPO_ORD_TP_CD = "CPO_ORD_TP_CD";

    /** Column name : CPO_ORD_TS */
    public static final String CPO_ORD_TS = "CPO_ORD_TS";

    /** Column name : ADMIN_PSN_CD */
    public static final String ADMIN_PSN_CD = "ADMIN_PSN_CD";

    /** Column name : CPO_DTL_LINE_NUM */
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** Column name : CPO_DTL_DEAL_NET_AMT */
    public static final String CPO_DTL_DEAL_NET_AMT = "CPO_DTL_DEAL_NET_AMT";

    /** Column name : SHIP_TO_CUST_CD */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** Column name : SHIP_TO_LOC_NM */
    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /** Column name : COA_BR_CD */
    public static final String COA_BR_CD = "COA_BR_CD";

    /** Column name : MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** Column name : MDSE_NM */
    public static final String MDSE_NM = "MDSE_NM";

    /** Column name : CPO_DTL_FUNC_NET_AMT */
    public static final String CPO_DTL_FUNC_NET_AMT = "CPO_DTL_FUNC_NET_AMT";

    /** Column name : DS_ORD_LINE_CATG_CD */
    public static final String DS_ORD_LINE_CATG_CD = "DS_ORD_LINE_CATG_CD";

    /** Column name : SPLY_TP_CD */
    public static final String SPLY_TP_CD = "SPLY_TP_CD";

    /** Column name : SPLY_NM */
    public static final String SPLY_NM = "SPLY_NM";

    /** Column name : SPLY_FIRST_ADDR */
    public static final String SPLY_FIRST_ADDR = "SPLY_FIRST_ADDR";

    /** Column name : SPLY_CTY_ADDR */
    public static final String SPLY_CTY_ADDR = "SPLY_CTY_ADDR";

    /** Column name : SPLY_ST_CD */
    public static final String SPLY_ST_CD = "SPLY_ST_CD";

    /** Column name : SPLY_POST_CD */
    public static final String SPLY_POST_CD = "SPLY_POST_CD";

    /** Column name : INV_NUM */
    public static final String INV_NUM = "INV_NUM";

    /** Column name : ORIG_INV_NUM */
    public static final String ORIG_INV_NUM = "ORIG_INV_NUM";

    /** Column name : BILL_TO_CUST_ACCT_CD */
    public static final String BILL_TO_CUST_ACCT_CD = "BILL_TO_CUST_ACCT_CD";

    /** Column name : INV_BOL_LINE_NUM */
    public static final String INV_BOL_LINE_NUM = "INV_BOL_LINE_NUM";

    /** Column name : INV_LINE_NUM */
    public static final String INV_LINE_NUM = "INV_LINE_NUM";

    /** Column name : INV_LINE_SUB_NUM */
    public static final String INV_LINE_SUB_NUM = "INV_LINE_SUB_NUM";

    /** Column name : INV_LINE_SUB_TRX_NUM */
    public static final String INV_LINE_SUB_TRX_NUM = "INV_LINE_SUB_TRX_NUM";

    /** Column name : COA_BR_NM */
    public static final String COA_BR_NM = "COA_BR_NM";

    /** Column name : PRNT_VND_PK */
    public static final String PRNT_VND_PK = "PRNT_VND_PK";

    /** Column name : PRNT_VND_CD */
    public static final String PRNT_VND_CD = "PRNT_VND_CD";

    /** Column name : VND_PK */
    public static final String VND_PK = "VND_PK";

    /** Column name : VND_CD */
    public static final String VND_CD = "VND_CD";

    /** Column name : COND_VAL_NUM_01 */
    public static final String COND_VAL_NUM_01 = "COND_VAL_NUM_01";

    /** Column name : ATTRB_COND_VAL_TXT_01 */
    public static final String ATTRB_COND_VAL_TXT_01 = "ATTRB_COND_VAL_TXT_01";

    // START 2016/10/12 K.Kojima [QC#13088,ADD]
    /** Column name : ORIG_CPO_ORD_NUM */
    public static final String ORIG_CPO_ORD_NUM = "ORIG_CPO_ORD_NUM";
    // END 2016/10/12 K.Kojima [QC#13088,ADD]

    // START 2016/10/25 K.Kojima [QC#13088,ADD]
    /** Column name : DS_ORD_CATG_CD */
    public static final String DS_ORD_CATG_CD = "DS_ORD_CATG_CD";
    // END 2016/10/25 K.Kojima [QC#13088,ADD]

    // START 2016/12/01 Y.Tsuchimoto [QC#13088,ADD]
    /** Column name : DPLY_LINE_NUM */
    public static final String DPLY_LINE_NUM = "DPLY_LINE_NUM";
    // END   2016/12/01 Y.Tsuchimoto [QC#13088,ADD]
}
