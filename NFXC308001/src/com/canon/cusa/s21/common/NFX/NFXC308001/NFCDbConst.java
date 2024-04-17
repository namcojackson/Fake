/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * <pre>
 *
 * Date         Company    Name         Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/04/2009   FSSL       M.Moriyama   Create          N/A
 * 11/19/2009   FSSL       K.Usui       Update          DefID-753
 * 11/23/2009   FSSL       Y.Kondo      Update          DefID-1130
 * 11/24/2009   FSSL       Y.Kondo      Update          Paymentech Upload Check Batch
 * 12/04/2009   FSSL       Y.Koiwai     Update          AR_TRX_BAL_BY_PROD Data Creation
 * 12/08/2009   FSSL       H.Tokunaga   Update          AR TRX BAL Summary By Monthly Data Creation
 * 12/09/2009   FSSL       K,Usui       Update          DefID-2397
 * 12/21/2009   FSSL       H.Tokunaga   Update          ReqId676 C.N.A AGING
 * 04/13/2010   FSSL       H.Tokunaga   Update          Defid 5617
 * 04/23/2010   FSSL       H.Tokunaga   Update          Defid 5949
 * 05/05/2010   JSU        K.Kimura     Update          Defid 6266
 * 05/25/2010   Fujitsu    S.Uehara     Update          Defid 6739  No.041
 * 05/28/2010   Fujitsu    K.Kimura     Update          Defid 6658  No.055
 * 06/15/2010   Fujitsu    I.Kondo      Update          Defid 7162  No.121
 * 07/12/2010   Fujitsu    I.Kondo      Update          Defid 7653  No.181
 * 08/02/2010   Canon      I.Kondo      Update          Merge.
 * 11/18/2010   Canon      K.Kimura     Update          Defid M115
 * 07/06/2011   Fujitsu    K.Kimura     Update          DefectID:2378
 * 07/10/2013   Fujitsu    K.Kimura     Update          WDS#1368 Hard-coding modification
 * 07/29/2013   Fujitsu    K.Kimura     Update          WDS#1458 Installment Invoice modification
 * 04/02/2015   Fujitsu    T.Yoshida    Update          for North America(CSA)
 * 03/25/2016   Hitachi    T.Tsuchida   Update          S21 NA Unit Test #164 Change Update Data
 * 06/26/2018   Fujitsu    Y.Matsui     Update          QC#26788
 *</pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC308001;

/**
 */
public class NFCDbConst {

    /**
     * --------
     */
    /** CUST_CD */
    public static final String CUST_CD = "CUST_CD";

    /** TOT_PG_CNT */
    public static final String TOT_PG_CNT = "TOT_PG_CNT";

    /** STMT_PRINT_DT */
    public static final String STMT_PRINT_DT = "STMT_PRINT_DT";

    /** CMPY_NM */
    public static final String CMPY_NM = "CMPY_NM";

    /** OFC_BR_NM */
    public static final String OFC_BR_NM = "OFC_BR_NM";

    /** IND_ML_STMT_CD */
    public static final String IND_ML_STMT_CD = "IND_ML_STMT_CD";

    /** ONE_LINE_CUST_NM */
    public static final String ONE_LINE_CUST_NM = "ONE_LINE_CUST_NM";

    /** CUST_STR_ADDR */
    public static final String CUST_STR_ADDR = "CUST_STR_ADDR";

    /** CUST_ST_ADDR */
    public static final String CUST_ST_ADDR = "CUST_ST_ADDR";

    /** PMT_DT */
    public static final String PMT_DT = "PMT_DT";

    /** LAST_PG_CNT */
    public static final String LAST_PG_CNT = "LAST_PG_CNT";

    /** BAL_TOT_AMT */
    public static final String BAL_TOT_AMT = "BAL_TOT_AMT";

    /** CASH_ON_ACCT_TOT_AMT */
    public static final String CASH_ON_ACCT_TOT_AMT = "CASH_ON_ACCT_TOT_AMT";

    /** ON_ACCT_CNT */
    public static final String ON_ACCT_CNT = "ON_ACCT_CNT";

    /** DISC_TOT_AMT */
    public static final String DISC_TOT_AMT = "DISC_TOT_AMT";

    /** INV_TOT_AMT */
    public static final String INV_TOT_AMT = "INV_TOT_AMT";

    /** AGE_CUR_AMT */
    public static final String AGE_CUR_AMT = "AGE_CUR_AMT";

    /** AGE_0130_AMT */
    public static final String AGE_0130_AMT = "AGE_0130_AMT";

    /** AGE_3160_AMT */
    public static final String AGE_3160_AMT = "AGE_3160_AMT";

    /** AGE_6190_AMT */
    public static final String AGE_6190_AMT = "AGE_6190_AMT";

    /** AGE_OVER_AMT */
    public static final String AGE_OVER_AMT = "AGE_OVER_AMT";

    /** MAKE_DT */
    public static final String MAKE_DT = "MAKE_DT";

    /**
     * --------
     */
    /** TOT_PER_CD */
    public static final String TOT_PER_CD = "TOT_PER_CD";

    /** INTVL_DAYS_AOT */
    public static final String INTVL_DAYS_AOT = "INTVL_DAYS_AOT";

    /** USE_SEG_CD */
    public static final String USE_SEG_CD = "USE_SEG_CD";

    /** TOT_PER_SEG_CD */
    public static final String TOT_PER_SEG_CD = "TOT_PER_SEG_CD";

    /** TOT_PER_DPLY_NM */
    public static final String TOT_PER_DPLY_NM = "TOT_PER_DPLY_NM";

    /** TOT_PER_FROM_DT */
    public static final String TOT_PER_FROM_DT = "TOT_PER_FROM_DT";

    /** TOT_PER_THRU_DT */
    public static final String TOT_PER_THRU_DT = "TOT_PER_THRU_DT";

    /**
     * --------
     */
    /** GLBL_CMPY_CD */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** GLBL_CMPY_NM */
    public static final String GLBL_CMPY_NM = "GLBL_CMPY_NM";

    /** APPLY_GRP_KEY */
    public static final String APPLY_GRP_KEY = "APPLY_GRP_KEY";

    /** APPLY_GRP_SUB_PK */
    public static final String APPLY_GRP_SUB_PK = "APPLY_GRP_SUB_PK";

    /** RCPT_IN_PROC_SQ_PK */
    public static final String RCPT_IN_PROC_SQ_PK = "RCPT_IN_PROC_SQ_PK";

    /** RCV_HDR_NUM */
    public static final String RCV_HDR_NUM = "RCV_HDR_NUM";

    /** DEAL_SQ_NUM */
    public static final String DEAL_SQ_NUM = "DEAL_SQ_NUM";

    /** RCPT_GL_DT */
    public static final String RCPT_GL_DT = "RCPT_GL_DT";

    /** RCV_DTL_NUM */
    public static final String RCV_DTL_NUM = "RCV_DTL_NUM";

    /** PROC_TP_CD */
    public static final String PROC_TP_CD = "PROC_TP_CD";

    /** BIZ_APP_ID */
    public static final String BIZ_APP_ID = "BIZ_APP_ID";

    /** INTFC_ID */
    public static final String INTFC_ID = "INTFC_ID";

    /** UPLD_CSV_ID */
    public static final String UPLD_CSV_ID = "UPLD_CSV_ID";

    /** DEAL_SQ_DTL_NUM */
    public static final String DEAL_SQ_DTL_NUM = "DEAL_SQ_DTL_NUM";

    /** PROC_STS_CD */
    public static final String PROC_STS_CD = "PROC_STS_CD";

    /** USR_ID */
    public static final String USR_ID = "USR_ID";

    /** RCPT_DTL_NUM */
    public static final String RCPT_DTL_NUM = "RCPT_DTL_NUM";

    /** RCV_FUNC_ID */
    public static final String RCV_FUNC_ID = "RCV_FUNC_ID";

    /** RCPT_TRX_BAL_PK */
    public static final String RCPT_TRX_BAL_PK = "RCPT_TRX_BAL_PK";

    /** RCPT_HDR_LAST_UPD_TS */
    public static final String RCPT_HDR_LAST_UPD_TS = "RCPT_HDR_LAST_UPD_TS";

    /** RCPT_HDR_TZ */
    public static final String RCPT_HDR_TZ = "RCPT_HDR_TZ";

    /** RCPT_TRX_BAL_LAST_UPD_TS */
    public static final String RCPT_TRX_BAL_LAST_UPD_TS = "RCPT_TRX_BAL_LAST_UPD_TS";

    /** RCPT_TRX_BAL_TZ */
    public static final String RCPT_TRX_BAL_TZ = "RCPT_TRX_BAL_TZ";

    /** GRP_INV_FLG */
    public static final String GRP_INV_FLG = "GRP_INV_FLG";

    /** INV_TRX_BAL_PK */
    public static final String INV_TRX_BAL_PK = "INV_TRX_BAL_PK";

    /** INV_TRX_BAL_LAST_UPD_TS */
    public static final String INV_TRX_BAL_LAST_UPD_TS = "INV_TRX_BAL_LAST_UPD_TS";

    /** INV_TRX_BAL_TZ */
    public static final String INV_TRX_BAL_TZ = "INV_TRX_BAL_TZ";

    /** CR_NUM */
    public static final String CR_NUM = "CR_NUM";

    /** CR_TRX_BAL_PK */
    public static final String CR_TRX_BAL_PK = "CR_TRX_BAL_PK";

    /** CR_TRX_BAL_LAST_UPD_TS */
    public static final String CR_TRX_BAL_LAST_UPD_TS = "CR_TRX_BAL_LAST_UPD_TS";

    /** CR_TRX_BAL_TZ */
    public static final String CR_TRX_BAL_TZ = "CR_TRX_BAL_TZ";

    /** CASH_APP_GL_DT */
    public static final String CASH_APP_GL_DT = "CASH_APP_GL_DT";

    /** DEAL_ON_ACCT_CASH_AMT */
    public static final String DEAL_ON_ACCT_CASH_AMT = "DEAL_ON_ACCT_CASH_AMT";

    /** APVL_PSN_CD */
    public static final String APVL_PSN_CD = "APVL_PSN_CD";

    /** AR_ADJ_LAST_UPD_TS */
    public static final String AR_ADJ_LAST_UPD_TS = "AR_ADJ_LAST_UPD_TS";

    /** AR_ADJ_TZ */
    public static final String AR_ADJ_TZ = "AR_ADJ_TZ";

    /**
     * --------
     */
    /** INV_SQ_PK */
    public static final String INV_SQ_PK = "INV_SQ_PK";

    /** INV_STS_CD */
    public static final String INV_STS_CD = "INV_STS_CD";

    /** ORIG_INV_NUM */
    public static final String ORIG_INV_NUM = "ORIG_INV_NUM";

    /** GRP_INV_NUM */
    public static final String GRP_INV_NUM = "GRP_INV_NUM";

    /** INV_DT */
    public static final String INV_DT = "INV_DT";

    /** ACCT_DT */
    public static final String ACCT_DT = "ACCT_DT";

    /** INV_TP_CD */
    public static final String INV_TP_CD = "INV_TP_CD";

    /** CR_INV_TP_CD */
    public static final String CR_INV_TP_CD = "CR_INV_TP_CD";

    /** NET_DUE_DT */
    public static final String NET_DUE_DT = "NET_DUE_DT";

    /** CPO_ORD_NUM */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** ORIG_CPO_ORD_NUM */
    public static final String ORIG_CPO_ORD_NUM = "ORIG_CPO_ORD_NUM";

    /** ORD_DT */
    public static final String ORD_DT = "ORD_DT";

    /** CPO_ORD_TP_CD */
    public static final String CPO_ORD_TP_CD = "CPO_ORD_TP_CD";

    /** CUST_ISS_PO_NUM */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** CUST_ISS_PO_DT */
    public static final String CUST_ISS_PO_DT = "CUST_ISS_PO_DT";

    /** OFC_NM */
    public static final String OFC_NM = "OFC_NM";

    /** OFC_BR_CD */
    public static final String OFC_BR_CD = "OFC_BR_CD";

    /** OFC_FIRST_LINE_ADDR */
    public static final String OFC_FIRST_LINE_ADDR = "OFC_FIRST_LINE_ADDR";

    /** OFC_SCD_LINE_ADDR */
    public static final String OFC_SCD_LINE_ADDR = "OFC_SCD_LINE_ADDR";

    /** OFC_THIRD_LINE_ADDR */
    public static final String OFC_THIRD_LINE_ADDR = "OFC_THIRD_LINE_ADDR";

    /** OFC_FRTH_LINE_ADDR */
    public static final String OFC_FRTH_LINE_ADDR = "OFC_FRTH_LINE_ADDR";

    /** OFC_TEL_NUM */
    public static final String OFC_TEL_NUM = "OFC_TEL_NUM";

    /** OFC_FAX_NUM */
    public static final String OFC_FAX_NUM = "OFC_FAX_NUM";

    /** OFC_LOC_NM */
    public static final String OFC_LOC_NM = "OFC_LOC_NM";

    /** OFC_ADDL_LOC_NM */
    public static final String OFC_ADDL_LOC_NM = "OFC_ADDL_LOC_NM";

    /** REMIT_TO_LOC_NM */
    public static final String REMIT_TO_LOC_NM = "REMIT_TO_LOC_NM";

    /** REMIT_TO_ADDL_LOC_NM */
    public static final String REMIT_TO_ADDL_LOC_NM = "REMIT_TO_ADDL_LOC_NM";

    /** REM_ID */
    public static final String REM_ID = "REM_ID";

    /** DUNS_NUM */
    public static final String DUNS_NUM = "DUNS_NUM";

    /** BILL_TO_CUST_CD */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** SELL_TO_CUST_CD */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** SELL_TO_LOC_NM */
    public static final String SELL_TO_LOC_NM = "SELL_TO_LOC_NM";

    /** SELL_TO_ADDL_LOC_NM */
    public static final String SELL_TO_ADDL_LOC_NM = "SELL_TO_ADDL_LOC_NM";

    /** SELL_TO_FIRST_LINE_ADDR */
    public static final String SELL_TO_FIRST_LINE_ADDR = "SELL_TO_FIRST_LINE_ADDR";

    /** SELL_TO_SCD_LINE_ADDR */
    public static final String SELL_TO_SCD_LINE_ADDR = "SELL_TO_SCD_LINE_ADDR";

    /** SELL_TO_THIRD_LINE_ADDR */
    public static final String SELL_TO_THIRD_LINE_ADDR = "SELL_TO_THIRD_LINE_ADDR";

    /** SELL_TO_FRTH_LINE_ADDR */
    public static final String SELL_TO_FRTH_LINE_ADDR = "SELL_TO_FRTH_LINE_ADDR";

    /** SELL_TO_CTY_ADDR */
    public static final String SELL_TO_CTY_ADDR = "SELL_TO_CTY_ADDR";

    /** SELL_TO_ST_CD */
    public static final String SELL_TO_ST_CD = "SELL_TO_ST_CD";

    /** SELL_TO_PROV_NM */
    public static final String SELL_TO_PROV_NM = "SELL_TO_PROV_NM";

    /** SELL_TO_CNTY_NM */
    public static final String SELL_TO_CNTY_NM = "SELL_TO_CNTY_NM";

    /** SELL_TO_POST_CD */
    public static final String SELL_TO_POST_CD = "SELL_TO_POST_CD";

    /** SELL_TO_CTRY_CD */
    public static final String SELL_TO_CTRY_CD = "SELL_TO_CTRY_CD";

    /** SELL_TO_FIRST_REF_CMNT_TXT */
    public static final String SELL_TO_FIRST_REF_CMNT_TXT = "SELL_TO_FIRST_REF_CMNT_TXT";

    /** SELL_TO_SCD_REF_CMNT_TXT */
    public static final String SELL_TO_SCD_REF_CMNT_TXT = "SELL_TO_SCD_REF_CMNT_TXT";

    /** ADV_RCPT_NUM */
    public static final String ADV_RCPT_NUM = "ADV_RCPT_NUM";

    /** RCPNT_FIRST_LINE_ADDR */
    public static final String RCPNT_FIRST_LINE_ADDR = "RCPNT_FIRST_LINE_ADDR";

    /** RCPNT_SCD_LINE_ADDR */
    public static final String RCPNT_SCD_LINE_ADDR = "RCPNT_SCD_LINE_ADDR";

    /** RCPNT_THIRD_LINE_ADDR */
    public static final String RCPNT_THIRD_LINE_ADDR = "RCPNT_THIRD_LINE_ADDR";

    /** RCPNT_FRTH_LINE_ADDR */
    public static final String RCPNT_FRTH_LINE_ADDR = "RCPNT_FRTH_LINE_ADDR";

    /** RCPNT_CTY_ADDR */
    public static final String RCPNT_CTY_ADDR = "RCPNT_CTY_ADDR";

    /** RCPNT_ST_CD */
    public static final String RCPNT_ST_CD = "RCPNT_ST_CD";

    /** RCPNT_PROV_NM */
    public static final String RCPNT_PROV_NM = "RCPNT_PROV_NM";

    /** RCPNT_CNTY_NM */
    public static final String RCPNT_CNTY_NM = "RCPNT_CNTY_NM";

    /** RCPNT_POST_CD */
    public static final String RCPNT_POST_CD = "RCPNT_POST_CD";

    /** RCPNT_LOC_NM */
    public static final String RCPNT_LOC_NM = "RCPNT_LOC_NM";

    /** RCPNT_ADDL_LOC_NM */
    public static final String RCPNT_ADDL_LOC_NM = "RCPNT_ADDL_LOC_NM";

    /** PMT_TERM_START_DT */
    public static final String PMT_TERM_START_DT = "PMT_TERM_START_DT";

    /** PMT_TERM_CD */
    public static final String PMT_TERM_CD = "PMT_TERM_CD";

    /** PMT_TERM_CASH_DISC_CD */
    public static final String PMT_TERM_CASH_DISC_CD = "PMT_TERM_CASH_DISC_CD";

    /** PMT_TERM_NM */
    public static final String PMT_TERM_NM = "PMT_TERM_NM";

    /** INV_PMT_METH_CD */
    public static final String INV_PMT_METH_CD = "INV_PMT_METH_CD";

    /** INV_PMT_COND_CD */
    public static final String INV_PMT_COND_CD = "INV_PMT_COND_CD";

    /** CASH_DISC_TERM_CD */
    public static final String CASH_DISC_TERM_CD = "CASH_DISC_TERM_CD";

    /** SLS_ADMIN_PSN_CD */
    public static final String SLS_ADMIN_PSN_CD = "SLS_ADMIN_PSN_CD";

    /** RCPNT_CTRY_CD */
    public static final String RCPNT_CTRY_CD = "RCPNT_CTRY_CD";

    /** FIRST_ORG_CD */
    public static final String FIRST_ORG_CD = "FIRST_ORG_CD";

    /** FIRST_ORG_NM */
    public static final String FIRST_ORG_NM = "FIRST_ORG_NM";

    /** SCD_ORG_CD */
    public static final String SCD_ORG_CD = "SCD_ORG_CD";

    /** SCD_ORG_NM */
    public static final String SCD_ORG_NM = "SCD_ORG_NM";

    /** THIRD_ORG_CD */
    public static final String THIRD_ORG_CD = "THIRD_ORG_CD";

    /** THIRD_ORG_NM */
    public static final String THIRD_ORG_NM = "THIRD_ORG_NM";

    /** FRTH_ORG_CD */
    public static final String FRTH_ORG_CD = "FRTH_ORG_CD";

    /** FRTH_ORG_NM */
    public static final String FRTH_ORG_NM = "FRTH_ORG_NM";

    /** FIFTH_ORG_CD */
    public static final String FIFTH_ORG_CD = "FIFTH_ORG_CD";

    /** FIFTH_ORG_NM */
    public static final String FIFTH_ORG_NM = "FIFTH_ORG_NM";

    /** SIXTH_ORG_CD */
    public static final String SIXTH_ORG_CD = "SIXTH_ORG_CD";

    /** SIXTH_ORG_NM */
    public static final String SIXTH_ORG_NM = "SIXTH_ORG_NM";

    /** SVNTH_ORG_CD */
    public static final String SVNTH_ORG_CD = "SVNTH_ORG_CD";

    /** SVNTH_ORG_NM */
    public static final String SVNTH_ORG_NM = "SVNTH_ORG_NM";

    /** EIGHTH_ORG_CD */
    public static final String EIGHTH_ORG_CD = "EIGHTH_ORG_CD";

    /** EIGHTH_ORG_NM */
    public static final String EIGHTH_ORG_NM = "EIGHTH_ORG_NM";

    /** CR_ANLST_PSN_NM */
    public static final String CR_ANLST_PSN_NM = "CR_ANLST_PSN_NM";

    /** INV_TOT_DEAL_NET_AMT */
    public static final String INV_TOT_DEAL_NET_AMT = "INV_TOT_DEAL_NET_AMT";

    /** INV_TOT_DEAL_SLS_AMT */
    public static final String INV_TOT_DEAL_SLS_AMT = "INV_TOT_DEAL_SLS_AMT";

    /** INV_TOT_DEAL_FRT_AMT */
    public static final String INV_TOT_DEAL_FRT_AMT = "INV_TOT_DEAL_FRT_AMT";

    /** INV_TOT_DEAL_TAX_AMT */
    public static final String INV_TOT_DEAL_TAX_AMT = "INV_TOT_DEAL_TAX_AMT";

    /** INV_TOT_DEAL_DISC_AMT */
    public static final String INV_TOT_DEAL_DISC_AMT = "INV_TOT_DEAL_DISC_AMT";

    /** INV_TOT_FUNC_NET_AMT */
    public static final String INV_TOT_FUNC_NET_AMT = "INV_TOT_FUNC_NET_AMT";

    /** INV_TOT_FUNC_SLS_AMT */
    public static final String INV_TOT_FUNC_SLS_AMT = "INV_TOT_FUNC_SLS_AMT";

    /** INV_TOT_FUNC_FRT_AMT */
    public static final String INV_TOT_FUNC_FRT_AMT = "INV_TOT_FUNC_FRT_AMT";

    /** INV_TOT_FUNC_TAX_AMT */
    public static final String INV_TOT_FUNC_TAX_AMT = "INV_TOT_FUNC_TAX_AMT";

    /** INV_TOT_FUNC_DISC_AMT */
    public static final String INV_TOT_FUNC_DISC_AMT = "INV_TOT_FUNC_DISC_AMT";

    /** INV_FIRST_CMNT_TXT */
    public static final String INV_FIRST_CMNT_TXT = "INV_FIRST_CMNT_TXT";

    /** INV_SCD_CMNT_TXT */
    public static final String INV_SCD_CMNT_TXT = "INV_SCD_CMNT_TXT";

    /** INV_THIRD_CMNT_TXT */
    public static final String INV_THIRD_CMNT_TXT = "INV_THIRD_CMNT_TXT";

    /** INV_FRTH_CMNT_TXT */
    public static final String INV_FRTH_CMNT_TXT = "INV_FRTH_CMNT_TXT";

    /** ACTL_APPLY_EXCH_RATE */
    public static final String ACTL_APPLY_EXCH_RATE = "ACTL_APPLY_EXCH_RATE";

    /** FL_PLN_FLG */
    public static final String FL_PLN_FLG = "FL_PLN_FLG";

    /** OTPT_CPLT_DT */
    public static final String OTPT_CPLT_DT = "OTPT_CPLT_DT";

    /** TRX_SRC_TP_CD */
    public static final String TRX_SRC_TP_CD = "TRX_SRC_TP_CD";

    /** AUTH_CD */
    public static final String AUTH_CD = "AUTH_CD";

    /** FNLZ_INV_FLG */
    public static final String FNLZ_INV_FLG = "FNLZ_INV_FLG";

    /** CR_DR_RSN_CD */
    public static final String CR_DR_RSN_CD = "CR_DR_RSN_CD";

    /** CR_DR_RSN_SUB_CD */
    public static final String CR_DR_RSN_SUB_CD = "CR_DR_RSN_SUB_CD";

    /** SYS_SRC_CD */
    public static final String SYS_SRC_CD = "SYS_SRC_CD";

    /** CR_CARD_ORD_NUM */
    public static final String CR_CARD_ORD_NUM = "CR_CARD_ORD_NUM";

    /**
     * Name --------
     */
    /** INV_BOL_LINE_NUM */
    public static final String INV_BOL_LINE_NUM = "INV_BOL_LINE_NUM";

    /** INV_LINE_NUM */
    public static final String INV_LINE_NUM = "INV_LINE_NUM";

    /** INV_LINE_SUB_NUM */
    public static final String INV_LINE_SUB_NUM = "INV_LINE_SUB_NUM";

    /** INV_LINE_SUB_TRX_NUM */
    public static final String INV_LINE_SUB_TRX_NUM = "INV_LINE_SUB_TRX_NUM";

    /** CPO_DTL_LINE_NUM */
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** CPO_DTL_LINE_SUB_NUM */
    public static final String CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** SHPG_PLN_NUM */
    public static final String SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** STK_STS_CD */
    public static final String STK_STS_CD = "STK_STS_CD";

    /** SLS_REP_TOC_CD */
    public static final String SLS_REP_TOC_CD = "SLS_REP_TOC_CD";

    /** SLS_REP_TOC_NM */
    public static final String SLS_REP_TOC_NM = "SLS_REP_TOC_NM";

    /** ZEROTH_PROD_CTRL_CD */
    public static final String ZEROTH_PROD_CTRL_CD = "ZEROTH_PROD_CTRL_CD";

    /** ZEROTH_PROD_CTRL_NM */
    public static final String ZEROTH_PROD_CTRL_NM = "ZEROTH_PROD_CTRL_NM";

    /** FIRST_PROD_CTRL_CD */
    public static final String FIRST_PROD_CTRL_CD = "FIRST_PROD_CTRL_CD";

    /** FIRST_PROD_CTRL_NM */
    public static final String FIRST_PROD_CTRL_NM = "FIRST_PROD_CTRL_NM";

    /** SCD_PROD_CTRL_CD */
    public static final String SCD_PROD_CTRL_CD = "SCD_PROD_CTRL_CD";

    /** SCD_PROD_CTRL_NM */
    public static final String SCD_PROD_CTRL_NM = "SCD_PROD_CTRL_NM";

    /** THIRD_PROD_CTRL_CD */
    public static final String THIRD_PROD_CTRL_CD = "THIRD_PROD_CTRL_CD";

    /** THIRD_PROD_CTRL_NM */
    public static final String THIRD_PROD_CTRL_NM = "THIRD_PROD_CTRL_NM";

    /** FRTH_PROD_CTRL_CD */
    public static final String FRTH_PROD_CTRL_CD = "FRTH_PROD_CTRL_CD";

    /** FRTH_PROD_CTRL_NM */
    public static final String FRTH_PROD_CTRL_NM = "FRTH_PROD_CTRL_NM";

    /** FIFTH_PROD_CTRL_CD */
    public static final String FIFTH_PROD_CTRL_CD = "FIFTH_PROD_CTRL_CD";

    /** FIFTH_PROD_CTRL_NM */
    public static final String FIFTH_PROD_CTRL_NM = "FIFTH_PROD_CTRL_NM";

    /** SIXTH_PROD_CTRL_CD */
    public static final String SIXTH_PROD_CTRL_CD = "SIXTH_PROD_CTRL_CD";

    /** SIXTH_PROD_CTRL_NM */
    public static final String SIXTH_PROD_CTRL_NM = "SIXTH_PROD_CTRL_NM";

    /** SVNTH_PROD_CTRL_CD */
    public static final String SVNTH_PROD_CTRL_CD = "SVNTH_PROD_CTRL_CD";

    /** SVNTH_PROD_CTRL_NM */
    public static final String SVNTH_PROD_CTRL_NM = "SVNTH_PROD_CTRL_NM";

    /** MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** MDSE_NM */
    public static final String MDSE_NM = "MDSE_NM";

    /** COA_PROD_CD */
    public static final String COA_PROD_CD = "COA_PROD_CD";

    /** COA_PROD_NM */
    public static final String COA_PROD_NM = "COA_PROD_NM";

    /** TRX_CD */
    public static final String TRX_CD = "TRX_CD";

    /** TRX_RSN_CD */
    public static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** ORD_QTY */
    public static final String ORD_QTY = "ORD_QTY";

    /** SHIP_QTY */
    public static final String SHIP_QTY = "SHIP_QTY";

    /** BO_QTY */
    public static final String BO_QTY = "BO_QTY";

    /** MAN_PRC_FLG */
    public static final String MAN_PRC_FLG = "MAN_PRC_FLG";

    /** DEAL_NET_UNIT_PRC_AMT */
    public static final String DEAL_NET_UNIT_PRC_AMT = "DEAL_NET_UNIT_PRC_AMT";

    /** INV_LINE_DEAL_TAX_AMT */
    public static final String INV_LINE_DEAL_TAX_AMT = "INV_LINE_DEAL_TAX_AMT";

    /** INV_LINE_DEAL_NET_AMT */
    public static final String INV_LINE_DEAL_NET_AMT = "INV_LINE_DEAL_NET_AMT";

    /** DEAL_DISC_UNIT_PRC_AMT */
    public static final String DEAL_DISC_UNIT_PRC_AMT = "DEAL_DISC_UNIT_PRC_AMT";

    /** FUNC_NET_UNIT_PRC_AMT */
    public static final String FUNC_NET_UNIT_PRC_AMT = "FUNC_NET_UNIT_PRC_AMT";

    /** INV_LINE_FUNC_TAX_AMT */
    public static final String INV_LINE_FUNC_TAX_AMT = "INV_LINE_FUNC_TAX_AMT";

    /** INV_LINE_FUNC_NET_AMT */
    public static final String INV_LINE_FUNC_NET_AMT = "INV_LINE_FUNC_NET_AMT";

    /** FUNC_DISC_UNIT_PRC_AMT */
    public static final String FUNC_DISC_UNIT_PRC_AMT = "FUNC_DISC_UNIT_PRC_AMT";

    /** TAX_FLG */
    public static final String TAX_FLG = "TAX_FLG";

    /** TAX_PCT */
    public static final String TAX_PCT = "TAX_PCT";

    /** U_CFC_MAT_CD */
    public static final String U_CFC_MAT_CD = "U_CFC_MAT_CD";

    /** U_CFC_MAT_NM */
    public static final String U_CFC_MAT_NM = "U_CFC_MAT_NM";

    /** U_CFC_TXT */
    public static final String U_CFC_TXT = "U_CFC_TXT";

    /** MERC_CNTN_FLG */
    public static final String MERC_CNTN_FLG = "MERC_CNTN_FLG";

    /** MERC_STMT_ON_INV_TXT */
    public static final String MERC_STMT_ON_INV_TXT = "MERC_STMT_ON_INV_TXT";

    /** COA_ACCT_CD */
    public static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** COA_PROJ_CD */
    public static final String COA_PROJ_CD = "COA_PROJ_CD";

    /** SET_MDSE_CD */
    public static final String SET_MDSE_CD = "SET_MDSE_CD";

    /** UNIT_COST_AMT */
    public static final String UNIT_COST_AMT = "UNIT_COST_AMT";

    /** SHIP_CMPL_COST_AMT */
    public static final String SHIP_CMPL_COST_AMT = "SHIP_CMPL_COST_AMT";

    /** BR_CD */
    public static final String BR_CD = "BR_CD";

    /** BR_NM */
    public static final String BR_NM = "BR_NM";

    /** DEAL_GRS_UNIT_PRC_AMT */
    public static final String DEAL_GRS_UNIT_PRC_AMT = "DEAL_GRS_UNIT_PRC_AMT";

    /** DEAL_GRS_TOT_PRC_AMT */
    public static final String DEAL_GRS_TOT_PRC_AMT = "DEAL_GRS_TOT_PRC_AMT";

    /** FUNC_GRS_UNIT_PRC_AMT */
    public static final String FUNC_GRS_UNIT_PRC_AMT = "FUNC_GRS_UNIT_PRC_AMT";

    /** FUNC_GRS_TOT_PRC_AMT */
    public static final String FUNC_GRS_TOT_PRC_AMT = "FUNC_GRS_TOT_PRC_AMT";

    /** SET_RATIO_KEEP_FLG */
    public static final String SET_RATIO_KEEP_FLG = "SET_RATIO_KEEP_FLG";

    /**
     * Name --------
     */
    /** SO_NUM */
    public static final String SO_NUM = "SO_NUM";

    /** BOL_NUM */
    public static final String BOL_NUM = "BOL_NUM";

    /** CARR_VND_CD */
    public static final String CARR_VND_CD = "CARR_VND_CD";

    /** CARR_VND_LOC_NM */
    public static final String CARR_VND_LOC_NM = "CARR_VND_LOC_NM";

    /** PRO_NUM */
    public static final String PRO_NUM = "PRO_NUM";

    /** SHIP_FROM_INVTY_LOC_CD */
    public static final String SHIP_FROM_INVTY_LOC_CD = "SHIP_FROM_INVTY_LOC_CD";

    /** SHIP_TO_CUST_CD */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** DROP_SHIP_FLG */
    public static final String DROP_SHIP_FLG = "DROP_SHIP_FLG";

    /** SHIP_TO_LOC_NM */
    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /** SHIP_TO_ADDL_LOC_NM */
    public static final String SHIP_TO_ADDL_LOC_NM = "SHIP_TO_ADDL_LOC_NM";

    /** SHIP_TO_FIRST_LINE_ADDR */
    public static final String SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";

    /** SHIP_TO_SCD_LINE_ADDR */
    public static final String SHIP_TO_SCD_LINE_ADDR = "SHIP_TO_SCD_LINE_ADDR";

    /** SHIP_TO_THIRD_LINE_ADDR */
    public static final String SHIP_TO_THIRD_LINE_ADDR = "SHIP_TO_THIRD_LINE_ADDR";

    /** SHIP_TO_FRTH_LINE_ADDR */
    public static final String SHIP_TO_FRTH_LINE_ADDR = "SHIP_TO_FRTH_LINE_ADDR";

    /** SHIP_TO_ST_CD */
    public static final String SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /** SHIP_TO_PROV_NM */
    public static final String SHIP_TO_PROV_NM = "SHIP_TO_PROV_NM";

    /** SHIP_TO_CNTY_NM */
    public static final String SHIP_TO_CNTY_NM = "SHIP_TO_CNTY_NM";

    /** SHIP_TO_FIRST_REF_CMNT_TXT */
    public static final String SHIP_TO_FIRST_REF_CMNT_TXT = "SHIP_TO_FIRST_REF_CMNT_TXT";

    /** SHIP_TO_SCD_REF_CMNT_TXT */
    public static final String SHIP_TO_SCD_REF_CMNT_TXT = "SHIP_TO_SCD_REF_CMNT_TXT";

    /** SHIP_TO_POST_CD */
    public static final String SHIP_TO_POST_CD = "SHIP_TO_POST_CD";

    /** SHIP_TO_CTY_ADDR */
    public static final String SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";

    /** SHIP_TO_CTRY_CD */
    public static final String SHIP_TO_CTRY_CD = "SHIP_TO_CTRY_CD";

    /** EXPT_FLG */
    public static final String EXPT_FLG = "EXPT_FLG";

    /** EXPT_FLG_J */
    public static final String EXPT_FLG_J = "exptFlg";

    /** SHIP_DEAL_SLS_AMT */
    public static final String SHIP_DEAL_SLS_AMT = "SHIP_DEAL_SLS_AMT";

    /** SHIP_DEAL_NET_AMT */
    public static final String SHIP_DEAL_NET_AMT = "SHIP_DEAL_NET_AMT";

    /** SHIP_DEAL_FRT_AMT */
    public static final String SHIP_DEAL_FRT_AMT = "SHIP_DEAL_FRT_AMT";

    /** SHIP_DEAL_DISC_AMT */
    public static final String SHIP_DEAL_DISC_AMT = "SHIP_DEAL_DISC_AMT";

    /** SHIP_DEAL_HDLG_CHRG_AMT */
    public static final String SHIP_DEAL_HDLG_CHRG_AMT = "SHIP_DEAL_HDLG_CHRG_AMT";

    /** SHIP_DT */
    public static final String SHIP_DT = "SHIP_DT";

    /** ARV_DT */
    public static final String ARV_DT = "ARV_DT";

    /** TOT_BOL_DEAL_TAX_AMT */
    public static final String TOT_BOL_DEAL_TAX_AMT = "TOT_BOL_DEAL_TAX_AMT";

    /** TOT_BOL_FUNC_TAX_AMT */
    public static final String TOT_BOL_FUNC_TAX_AMT = "TOT_BOL_FUNC_TAX_AMT";

    /** SHPG_SVC_LVL_CD */
    public static final String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** SHPG_SVC_LVL_NM */
    public static final String SHPG_SVC_LVL_NM = "SHPG_SVC_LVL_NM";

    /** FRT_CHRG_TO_CD */
    public static final String FRT_CHRG_TO_CD = "FRT_CHRG_TO_CD";

    /** FRT_CHRG_TO_NM */
    public static final String FRT_CHRG_TO_NM = "FRT_CHRG_TO_NM";

    /** FRT_CHRG_METH_CD */
    public static final String FRT_CHRG_METH_CD = "FRT_CHRG_METH_CD";

    /** FRT_CHRG_METH_NM */
    public static final String FRT_CHRG_METH_NM = "FRT_CHRG_METH_NM";

    /** FRT_TAX_PCT */
    public static final String FRT_TAX_PCT = "FRT_TAX_PCT";

    /** FRT_DEAL_TAX_AMT */
    public static final String FRT_DEAL_TAX_AMT = "FRT_DEAL_TAX_AMT";

    /** FRT_FUNC_TAX_AMT */
    public static final String FRT_FUNC_TAX_AMT = "FRT_FUNC_TAX_AMT";

    /** APVL_NUM */
    public static final String APVL_NUM = "APVL_NUM";

    /**
     * Name --------
     */
    /** INV_PRMO_INFO_PK */
    public static final String INV_PRMO_INFO_PK = "INV_PRMO_INFO_PK";

    /** PRMO_PK */
    public static final String PRMO_PK = "PRMO_PK";

    /** PRMO_CATG_PK */
    public static final String PRMO_CATG_PK = "PRMO_CATG_PK";

    /** PRMO_CATG_NM */
    public static final String PRMO_CATG_NM = "PRMO_CATG_NM";

    /** PRMO_SHORT_NM */
    public static final String PRMO_SHORT_NM = "PRMO_SHORT_NM";

    /** PRC_DT */
    public static final String PRC_DT = "PRC_DT";

    /** PRMO_QTY */
    public static final String PRMO_QTY = "PRMO_QTY";

    /** DEAL_UNIT_PRC_AMT */
    public static final String DEAL_UNIT_PRC_AMT = "DEAL_UNIT_PRC_AMT";

    /** DEAL_LAST_NET_UNIT_PRC_AMT */
    public static final String DEAL_LAST_NET_UNIT_PRC_AMT = "DEAL_LAST_NET_UNIT_PRC_AMT";

    /** DEAL_NET_AMT */
    public static final String DEAL_NET_AMT = "DEAL_NET_AMT";

    /** DEAL_DISC_AMT */
    public static final String DEAL_DISC_AMT = "DEAL_DISC_AMT";

    /** DEAL_PRMO_NET_UNIT_PRC_AMT */
    public static final String DEAL_PRMO_NET_UNIT_PRC_AMT = "DEAL_PRMO_NET_UNIT_PRC_AMT";

    /** DEAL_PK */
    public static final String DEAL_PK = "DEAL_PK";

    /** DEAL_PER_UNIT_FIX_AMT */
    public static final String DEAL_PER_UNIT_FIX_AMT = "DEAL_PER_UNIT_FIX_AMT";

    /** DEAL_SLS_PCT_NUM */
    public static final String DEAL_SLS_PCT_NUM = "DEAL_SLS_PCT_NUM";

    /** FUNC_PER_UNIT_FIX_AMT */
    public static final String FUNC_PER_UNIT_FIX_AMT = "FUNC_PER_UNIT_FIX_AMT";

    /** FUNC_UNIT_PRC_AMT */
    public static final String FUNC_UNIT_PRC_AMT = "FUNC_UNIT_PRC_AMT";

    /** FUNC_LAST_NET_UNIT_PRC_AMT */
    public static final String FUNC_LAST_NET_UNIT_PRC_AMT = "FUNC_LAST_NET_UNIT_PRC_AMT";

    /** FUNC_NET_AMT */
    public static final String FUNC_NET_AMT = "FUNC_NET_AMT";

    /** FUNC_DISC_AMT */
    public static final String FUNC_DISC_AMT = "FUNC_DISC_AMT";

    /** FUNC_PRMO_NET_UNIT_PRC_AMT */
    public static final String FUNC_PRMO_NET_UNIT_PRC_AMT = "FUNC_PRMO_NET_UNIT_PRC_AMT";

    /** DEAL_NM */
    public static final String DEAL_NM = "DEAL_NM";

    /** PRMO_DESC_TXT */
    public static final String PRMO_DESC_TXT = "PRMO_DESC_TXT";

    /** INV_PRMO_INFO_SQ_NUM */
    public static final String INV_PRMO_INFO_SQ_NUM = "INV_PRMO_INFO_SQ_NUM";

    /**
     * --------
     */
    /** CASH_DISC_TERM_EFF_FROM_DT */
    public static final String CASH_DISC_TERM_EFF_FROM_DT = "CASH_DISC_TERM_EFF_FROM_DT";

    /** CASH_DISC_TERM_EFF_THRU_DT */
    public static final String CASH_DISC_TERM_EFF_THRU_DT = "CASH_DISC_TERM_EFF_THRU_DT";

    /** CASH_DISC_TERM_DTL_NUM */
    public static final String CASH_DISC_TERM_DTL_NUM = "CASH_DISC_TERM_DTL_NUM";

    /** CASH_DISC_FROM_AOT */
    public static final String CASH_DISC_FROM_AOT = "CASH_DISC_FROM_AOT";

    /** CASH_DISC_THRU_AOT */
    public static final String CASH_DISC_THRU_AOT = "CASH_DISC_THRU_AOT";

    /** CASH_DISC_PCT */
    public static final String CASH_DISC_PCT = "CASH_DISC_PCT";

    /**
     * --------
     */
    /** RCPT_BAT_NUM */
    public static final String RCPT_BAT_NUM = "RCPT_BAT_NUM";

    /** RCPT_BAT_SQ_NUM */
    public static final String RCPT_BAT_SQ_NUM = "RCPT_BAT_SQ_NUM";

    /** AR_RCPT_TRX_TP_CD */
    public static final String AR_RCPT_TRX_TP_CD = "AR_RCPT_TRX_TP_CD";

    /** AR_RCPT_TP_CD */
    public static final String AR_RCPT_TP_CD = "AR_RCPT_TP_CD";

    /** DEAL_CCY_CD */
    public static final String DEAL_CCY_CD = "DEAL_CCY_CD";

    /** DEAL_RCPT_AMT */
    public static final String DEAL_RCPT_AMT = "DEAL_RCPT_AMT";

    /** DEAL_APPLY_AMT */
    public static final String DEAL_APPLY_AMT = "DEAL_APPLY_AMT";

    /** DEAL_RF_AMT */
    public static final String DEAL_RF_AMT = "DEAL_RF_AMT";

    /** DEAL_VOID_AMT */
    public static final String DEAL_VOID_AMT = "DEAL_VOID_AMT";

    /** DEAL_RCPT_RMNG_BAL_AMT */
    public static final String DEAL_RCPT_RMNG_BAL_AMT = "DEAL_RCPT_RMNG_BAL_AMT";

    /** EXCH_RATE */
    public static final String EXCH_RATE = "EXCH_RATE";

    /** FUNC_CCY_CD */
    public static final String FUNC_CCY_CD = "FUNC_CCY_CD";

    /** FUNC_RCPT_AMT */
    public static final String FUNC_RCPT_AMT = "FUNC_RCPT_AMT";

    /** FUNC_APPLY_AMT */
    public static final String FUNC_APPLY_AMT = "FUNC_APPLY_AMT";

    /** FUNC_RF_AMT */
    public static final String FUNC_RF_AMT = "FUNC_RF_AMT";

    /** FUNC_VOID_AMT */
    public static final String FUNC_VOID_AMT = "FUNC_VOID_AMT";

    /** FUNC_RCPT_RMNG_BAL_AMT */
    public static final String FUNC_RCPT_RMNG_BAL_AMT = "FUNC_RCPT_RMNG_BAL_AMT";

    /** RCPT_DT */
    public static final String RCPT_DT = "RCPT_DT";

    /** GL_DT */
    public static final String GL_DT = "GL_DT";

    /** RCPT_CHK_NUM */
    public static final String RCPT_CHK_NUM = "RCPT_CHK_NUM";

    /** RCPT_CHK_DT */
    public static final String RCPT_CHK_DT = "RCPT_CHK_DT";

    /** PAYER_CUST_CD */
    public static final String PAYER_CUST_CD = "PAYER_CUST_CD";

    /** TOC_CD */
    public static final String TOC_CD = "TOC_CD";

    /** TOC_NM */
    public static final String TOC_NM = "TOC_NM";

    /** CR_ANLST_PSN_CD */
    public static final String CR_ANLST_PSN_CD = "CR_ANLST_PSN_CD";

    /** AR_BANK_ACCT_CD */
    public static final String AR_BANK_ACCT_CD = "AR_BANK_ACCT_CD";

    /** RCPT_FIRST_CMNT_TXT */
    public static final String RCPT_FIRST_CMNT_TXT = "RCPT_FIRST_CMNT_TXT";

    /** RCPT_SCD_CMNT_TXT */
    public static final String RCPT_SCD_CMNT_TXT = "RCPT_SCD_CMNT_TXT";

    /** AR_CASH_APPLY_STS_CD */
    public static final String AR_CASH_APPLY_STS_CD = "AR_CASH_APPLY_STS_CD";

    /** AR_CASH_APPLY_STS_CD_J */
    public static final String AR_CASH_APPLY_STS_CD_J = "arCashApplyStsCd";

    /** VOID_FLG */
    public static final String VOID_FLG = "VOID_FLG";

    /** AR_RCPT_VOID_RSN_CD */
    public static final String AR_RCPT_VOID_RSN_CD = "AR_RCPT_VOID_RSN_CD";

    /** VOID_DT */
    public static final String VOID_DT = "VOID_DT";

    /** VOID_FIRST_CMNT_TXT */
    public static final String VOID_FIRST_CMNT_TXT = "VOID_FIRST_CMNT_TXT";

    /** VOID_SCD_CMNT_TXT */
    public static final String VOID_SCD_CMNT_TXT = "VOID_SCD_CMNT_TXT";

    /** VOID_GL_DT */
    public static final String VOID_GL_DT = "VOID_GL_DT";

    /** AR_RCPT_RF_RSN_CD */
    public static final String AR_RCPT_RF_RSN_CD = "AR_RCPT_RF_RSN_CD";

    /** RF_DT */
    public static final String RF_DT = "RF_DT";

    /** FIRST_RF_CMNT_TXT */
    public static final String FIRST_RF_CMNT_TXT = "FIRST_RF_CMNT_TXT";

    /** SCD_RF_CMNT_TXT */
    public static final String SCD_RF_CMNT_TXT = "SCD_RF_CMNT_TXT";

    /** RF_GL_DT */
    public static final String RF_GL_DT = "RF_GL_DT";

    /** RF_EXCH_RATE */
    public static final String RF_EXCH_RATE = "RF_EXCH_RATE";

    /** RCV_TS */
    public static final String RCV_TS = "RCV_TS";

    /** RCV_SQ_PK */
    public static final String RCV_SQ_PK = "RCV_SQ_PK";

    /** RCV_SQ_PK_J */
    public static final String RCV_SQ_PK_J = "rcvSqPk";

    /** RCV_TRX_TP_CD */
    public static final String RCV_TRX_TP_CD = "RCV_TRX_TP_CD";

    /** RCV_TRX_NUM */
    public static final String RCV_TRX_NUM = "RCV_TRX_NUM";

    /** AR_EDI_SEND_BANK_CD */
    public static final String AR_EDI_SEND_BANK_CD = "AR_EDI_SEND_BANK_CD";

    /** EDI_RCV_CUST_NM */
    public static final String EDI_RCV_CUST_NM = "EDI_RCV_CUST_NM";

    /** CRAT_METH_TP_CD */
    public static final String CRAT_METH_TP_CD = "CRAT_METH_TP_CD";

    /** INV_NUM */
    public static final String INV_NUM = "INV_NUM";

    /** AR_CUST_REF_NUM */
    public static final String AR_CUST_REF_NUM = "AR_CUST_REF_NUM";

    /** AR_CUST_REF_TP_CD */
    public static final String AR_CUST_REF_TP_CD = "AR_CUST_REF_TP_CD";

    /** DEAL_RCPT_DTL_AMT */
    public static final String DEAL_RCPT_DTL_AMT = "DEAL_RCPT_DTL_AMT";

    /** FUNC_RCPT_DTL_AMT */
    public static final String FUNC_RCPT_DTL_AMT = "FUNC_RCPT_DTL_AMT";

    /** AUTO_CRAT_FLG */
    public static final String AUTO_CRAT_FLG = "AUTO_CRAT_FLG";

    /** DUP_ERR_CD */
    public static final String DUP_ERR_CD = "DUP_ERR_CD";

    /* */
    /** RCPT_NUM */
    public static final String RCPT_NUM = "RCPT_NUM";

    /* */
    /** AR_TRX_BAL_PK */
    public static final String AR_TRX_BAL_PK = "AR_TRX_BAL_PK";

    /** CASH_APP_DT */
    public static final String CASH_APP_DT = "CASH_APP_DT";

    /** INV_DUE_DT */
    public static final String INV_DUE_DT = "INV_DUE_DT";

    /** AR_TRX_DT */
    public static final String AR_TRX_DT = "AR_TRX_DT";

    /** arTrxDt */
    public static final String AR_TRX_DT_J = "arTrxDt";

    /** ACCT_YR_MTH */
    public static final String ACCT_YR_MTH = "ACCT_YR_MTH";

    /**
     * --------
     */
    /** UNIT_ID */
    public static final String UNIT_ID = "UNIT_ID";

    /** SEQ_NUMBER */
    public static final String SEQ_NUMBER = "SEQ_NUMBER";

    /**
     * --------
     */
    /** CODE_CUST_CHRG_TO_IF */
    public static final String CODE_CUST_CHRG_TO_IF = "CODE_CUST_CHRG_TO_IF";

    /** NUM_ORDR_ID_IF */
    public static final String NUM_ORDR_ID_IF = "NUM_ORDR_ID_IF";

    /** NUM_SO_IF */
    public static final String NUM_SO_IF = "NUM_SO_IF";

    /** NUM_ORDR_ORIG_IF */
    public static final String NUM_ORDR_ORIG_IF = "NUM_ORDR_ORIG_IF";

    /** NAME_CNTRY_IF */
    public static final String NAME_CNTRY_IF = "NAME_CNTRY_IF";

    /** CODE_VCHR_TYPE_IF */
    public static final String CODE_VCHR_TYPE_IF = "CODE_VCHR_TYPE_IF";

    /** CODE_DEPT_IF */
    public static final String CODE_DEPT_IF = "CODE_DEPT_IF";

    /** DATE_INVO_IF */
    public static final String DATE_INVO_IF = "DATE_INVO_IF";

    /** DATE_DUE_IF */
    public static final String DATE_DUE_IF = "DATE_DUE_IF";

    /** CODE_TERM_IF */
    public static final String CODE_TERM_IF = "CODE_TERM_IF";

    /** CODE_CUST_SOLD_TO_IF */
    public static final String CODE_CUST_SOLD_TO_IF = "CODE_CUST_SOLD_TO_IF";

    /** NUM_CUST_PO_IF */
    public static final String NUM_CUST_PO_IF = "NUM_CUST_PO_IF";

    /** CODE_BRCH_IF */
    public static final String CODE_BRCH_IF = "CODE_BRCH_IF";

    /** CODE_CUST_SHIP_TO_IF */
    public static final String CODE_CUST_SHIP_TO_IF = "CODE_CUST_SHIP_TO_IF";

    /** AMT_SALE_TAX_TOT_IF */
    public static final String AMT_SALE_TAX_TOT_IF = "AMT_SALE_TAX_TOT_IF";

    /** AMT_SALE_FRT_CHRG_TOT_IF */
    public static final String AMT_SALE_FRT_CHRG_TOT_IF = "AMT_SALE_FRT_CHRG_TOT_IF";

    /** AMT_SALE_DISC_TOT_IF */
    public static final String AMT_SALE_DISC_TOT_IF = "AMT_SALE_DISC_TOT_IF";

    /** AMT_SALE_SHIP_TOT_IF */
    public static final String AMT_SALE_SHIP_TOT_IF = "AMT_SALE_SHIP_TOT_IF";

    /** AMT_SALE_NET_TOT_IF */
    public static final String AMT_SALE_NET_TOT_IF = "AMT_SALE_NET_TOT_IF";

    /** AMT_SPECIAL_DISC_TOT_IF */
    public static final String AMT_SPECIAL_DISC_TOT_IF = "AMT_SPECIAL_DISC_TOT_IF";

    /** AMT_SPECIAL_DISC_IF */
    public static final String AMT_SPECIAL_DISC_IF = "AMT_SPECIAL_DISC_IF";

    /** DATE_ISSUED_IF */
    public static final String DATE_ISSUED_IF = "DATE_ISSUED_IF";

    /** CODE_AUTHRZ_IF */
    public static final String CODE_AUTHRZ_IF = "CODE_AUTHRZ_IF";

    /** CODE_ID_RECORD_IF */
    public static final String CODE_ID_RECORD_IF = "CODE_ID_RECORD_IF";

    /** CODE_CARRIER_IF */
    public static final String CODE_CARRIER_IF = "CODE_CARRIER_IF";

    /** NAME_CARRIER_IF */
    public static final String NAME_CARRIER_IF = "NAME_CARRIER_IF";

    /** CODE_FRT_CHRG_IF */
    public static final String CODE_FRT_CHRG_IF = "CODE_FRT_CHRG_IF";

    /** AMT_COST_IF */
    public static final String AMT_COST_IF = "AMT_COST_IF";

    /** CODE_ITEM_SET_IF */
    public static final String CODE_ITEM_SET_IF = "CODE_ITEM_SET_IF";

    /** PRC_UNIT_IF */
    public static final String PRC_UNIT_IF = "PRC_UNIT_IF";

    /** AMT_SHIP_ITEM_IF */
    public static final String AMT_SHIP_ITEM_IF = "AMT_SHIP_ITEM_IF";

    /** QTY_ORDR_ITEM_IF */
    public static final String QTY_ORDR_ITEM_IF = "QTY_ORDR_ITEM_IF";

    /** QTY_SHIP_ITEM_IF */
    public static final String QTY_SHIP_ITEM_IF = "QTY_SHIP_ITEM_IF";

    /** QTY_BO_ITEM_IF */
    public static final String QTY_BO_ITEM_IF = "QTY_BO_ITEM_IF";

    /** CODE_STAT_STOCK_IF */
    public static final String CODE_STAT_STOCK_IF = "CODE_STAT_STOCK_IF";

    /** CODE_TRANSACT_IF */
    public static final String CODE_TRANSACT_IF = "CODE_TRANSACT_IF";

    /** CODE_ITEM_IF */
    public static final String CODE_ITEM_IF = "CODE_ITEM_IF";

    /** DATE_SHIP_IF */
    public static final String DATE_SHIP_IF = "DATE_SHIP_IF";

    /** CODE_PROD_LINE_MDS_IF */
    public static final String CODE_PROD_LINE_MDS_IF = "CODE_PROD_LINE_MDS_IF";

    /** CODE_PROD_LINE_IF */
    public static final String CODE_PROD_LINE_IF = "CODE_PROD_LINE_IF";

    /** CODE_IND_CORR_IF */
    public static final String CODE_IND_CORR_IF = "CODE_IND_CORR_IF";

    /** DATE_CASH_DISC_LMT_1_IF */
    public static final String DATE_CASH_DISC_LMT_1_IF = "DATE_CASH_DISC_LMT_1_IF";

    /** PCT_CASH_DISC_EST_1_IF */
    public static final String PCT_CASH_DISC_EST_1_IF = "PCT_CASH_DISC_EST_1_IF";

    /** AMT_CASH_DISC_EST_1_IF */
    public static final String AMT_CASH_DISC_EST_1_IF = "AMT_CASH_DISC_EST_1_IF";

    /** DATE_CASH_DISC_LMT_2_IF */
    public static final String DATE_CASH_DISC_LMT_2_IF = "DATE_CASH_DISC_LMT_2_IF";

    /** PCT_CASH_DISC_EST_2_IF */
    public static final String PCT_CASH_DISC_EST_2_IF = "PCT_CASH_DISC_EST_2_IF";

    /** AMT_CASH_DISC_EST_2_IF */
    public static final String AMT_CASH_DISC_EST_2_IF = "AMT_CASH_DISC_EST_2_IF";

    /** DATE_CASH_DISC_LMT_3_IF */
    public static final String DATE_CASH_DISC_LMT_3_IF = "DATE_CASH_DISC_LMT_3_IF";

    /** PCT_CASH_DISC_EST_3_IF */
    public static final String PCT_CASH_DISC_EST_3_IF = "PCT_CASH_DISC_EST_3_IF";

    /** AMT_CASH_DISC_EST_3_IF */
    public static final String AMT_CASH_DISC_EST_3_IF = "AMT_CASH_DISC_EST_3_IF";

    /** DATE_CASH_DISC_LMT_4_IF */
    public static final String DATE_CASH_DISC_LMT_4_IF = "DATE_CASH_DISC_LMT_4_IF";

    /** PCT_CASH_DISC_EST_4_IF */
    public static final String PCT_CASH_DISC_EST_4_IF = "PCT_CASH_DISC_EST_4_IF";

    /** AMT_CASH_DISC_EST_4_IF */
    public static final String AMT_CASH_DISC_EST_4_IF = "AMT_CASH_DISC_EST_4_IF";

    /** DATE_CASH_DISC_LMT_5_IF */
    public static final String DATE_CASH_DISC_LMT_5_IF = "DATE_CASH_DISC_LMT_5_IF";

    /** PCT_CASH_DISC_EST_5_IF */
    public static final String PCT_CASH_DISC_EST_5_IF = "PCT_CASH_DISC_EST_5_IF";

    /** AMT_CASH_DISC_EST_5_IF */
    public static final String AMT_CASH_DISC_EST_5_IF = "AMT_CASH_DISC_EST_5_IF";

    /** NUM_ORDR_SUB_IF */
    public static final String NUM_ORDR_SUB_IF = "NUM_ORDR_SUB_IF";

    /** CODE_EXPNS_ACC_IF */
    public static final String CODE_EXPNS_ACC_IF = "CODE_EXPNS_ACC_IF";

    /** CODE_CHANEL_IF */
    public static final String CODE_CHANEL_IF = "CODE_CHANEL_IF";

    /** DATE_ISSUED_CNTRY_IF */
    public static final String DATE_ISSUED_CNTRY_IF = "DATE_ISSUED_CNTRY_IF";

    /** CODE_SUMMARY_SFX_IF */
    public static final String CODE_SUMMARY_SFX_IF = "CODE_SUMMARY_SFX_IF";

    /** CODE_WH_FR_IF */
    public static final String CODE_WH_FR_IF = "CODE_WH_FR_IF";

    /** NUM_RCPTN_IF */
    public static final String NUM_RCPTN_IF = "NUM_RCPTN_IF";

    /** NUM_BL_IF */
    public static final String NUM_BL_IF = "NUM_BL_IF";

    /** ADDR_CUST_MAIL_IF */
    public static final String ADDR_CUST_MAIL_IF = "ADDR_CUST_MAIL_IF";

    /**
     * --------
     */
    /** BAL_INSTN_SQ_PK */
    public static final String BAL_INSTN_SQ_PK = "BAL_INSTN_SQ_PK";

    /** AGING_PER_TP_CD */
    public static final String AGING_PER_TP_CD = "AGING_PER_TP_CD";

    /** AR_TRX_TP_CD */
    public static final String AR_TRX_TP_CD = "AR_TRX_TP_CD";

    /** DEAL_ORIG_GRS_AMT */
    public static final String DEAL_ORIG_GRS_AMT = "DEAL_ORIG_GRS_AMT";

    /** DEAL_APPLY_GRS_AMT */
    public static final String DEAL_APPLY_GRS_AMT = "DEAL_APPLY_GRS_AMT";

    /** DEAL_APPLY_CASH_DISC_AMT */
    public static final String DEAL_APPLY_CASH_DISC_AMT = "DEAL_APPLY_CASH_DISC_AMT";

    /** DEAL_APPLY_CR_AMT */
    public static final String DEAL_APPLY_CR_AMT = "DEAL_APPLY_CR_AMT";

    /** DEAL_APPLY_ADJ_AMT */
    public static final String DEAL_APPLY_ADJ_AMT = "DEAL_APPLY_ADJ_AMT";

    /** DEAL_APPLY_ADJ_RSVD_AMT */
    public static final String DEAL_APPLY_ADJ_RSVD_AMT = "DEAL_APPLY_ADJ_RSVD_AMT";

    /** DEAL_RMNG_BAL_GRS_AMT */
    public static final String DEAL_RMNG_BAL_GRS_AMT = "DEAL_RMNG_BAL_GRS_AMT";

    /** DEAL_NET_SLS_AMT */
    public static final String DEAL_NET_SLS_AMT = "DEAL_NET_SLS_AMT";

    /** DEAL_FRT_AMT */
    public static final String DEAL_FRT_AMT = "DEAL_FRT_AMT";

    /** DEAL_TAX_AMT */
    public static final String DEAL_TAX_AMT = "DEAL_TAX_AMT";

    /** DEAL_INV_DISC_AMT */
    public static final String DEAL_INV_DISC_AMT = "DEAL_INV_DISC_AMT";

    /** DEAL_PRMO_DISC_AMT */
    public static final String DEAL_PRMO_DISC_AMT = "DEAL_PRMO_DISC_AMT";

    /** DEAL_RCPT_VOID_AMT */
    public static final String DEAL_RCPT_VOID_AMT = "DEAL_RCPT_VOID_AMT";

    /** FUNC_ORIG_GRS_AMT */
    public static final String FUNC_ORIG_GRS_AMT = "FUNC_ORIG_GRS_AMT";

    /** FUNC_APPLY_GRS_AMT */
    public static final String FUNC_APPLY_GRS_AMT = "FUNC_APPLY_GRS_AMT";

    /** FUNC_APPLY_CASH_DISC_AMT */
    public static final String FUNC_APPLY_CASH_DISC_AMT = "FUNC_APPLY_CASH_DISC_AMT";

    /** FUNC_APPLY_CR_AMT */
    public static final String FUNC_APPLY_CR_AMT = "FUNC_APPLY_CR_AMT";

    /** FUNC_APPLY_ADJ_AMT */
    public static final String FUNC_APPLY_ADJ_AMT = "FUNC_APPLY_ADJ_AMT";

    /** FUNC_APPLY_ADJ_RSVD_AMT */
    public static final String FUNC_APPLY_ADJ_RSVD_AMT = "FUNC_APPLY_ADJ_RSVD_AMT";

    /** FUNC_RVAL_VAR_AMT */
    public static final String FUNC_RVAL_VAR_AMT = "FUNC_RVAL_VAR_AMT";

    /** FUNC_RMNG_BAL_GRS_AMT */
    public static final String FUNC_RMNG_BAL_GRS_AMT = "FUNC_RMNG_BAL_GRS_AMT";

    /** FUNC_NET_SLS_AMT */
    public static final String FUNC_NET_SLS_AMT = "FUNC_NET_SLS_AMT";

    /** FUNC_FRT_AMT */
    public static final String FUNC_FRT_AMT = "FUNC_FRT_AMT";

    /** FUNC_TAX_AMT */
    public static final String FUNC_TAX_AMT = "FUNC_TAX_AMT";

    /** FUNC_INV_DISC_AMT */
    public static final String FUNC_INV_DISC_AMT = "FUNC_INV_DISC_AMT";

    /** FUNC_PRMO_DISC_AMT */
    public static final String FUNC_PRMO_DISC_AMT = "FUNC_PRMO_DISC_AMT";

    /** FUNC_RCPT_VOID_AMT */
    public static final String FUNC_RCPT_VOID_AMT = "FUNC_RCPT_VOID_AMT";

    /**
     * --------
     */
    /** AR_CASH_APP_PK */
    public static final String AR_CASH_APP_PK = "AR_CASH_APP_PK";

    /** AR_CASH_APP_SQ_NUM */
    public static final String AR_CASH_APP_SQ_NUM = "AR_CASH_APP_SQ_NUM";

    /** AR_ADJ_NUM */
    public static final String AR_ADJ_NUM = "AR_ADJ_NUM";

    /** AR_TRX_NUM */
    public static final String AR_TRX_NUM = "AR_TRX_NUM";

    /** AR_CR_TRX_NUM */
    public static final String AR_CR_TRX_NUM = "AR_CR_TRX_NUM";

    /** CR_AR_TRX_BAL_PK */
    public static final String CR_AR_TRX_BAL_PK = "CR_AR_TRX_BAL_PK";

    /** AR_APPLY_TP_CD */
    public static final String AR_APPLY_TP_CD = "AR_APPLY_TP_CD";

    /** EZINUSERID */
    public static final String EZINUSERID = "EZINUSERID";

    /** EZINTIME */
    public static final String EZINTIME = "EZINTIME";

    /**
     * --------
     */
    /** BILL_SELL_TP_CD */
    public static final String BILL_SELL_TP_CD = "BILL_SELL_TP_CD";

    /** FIRST_REM_TO_LOC_NM */
    public static final String FIRST_REM_TO_LOC_NM = "FIRST_REM_TO_LOC_NM";

    /** SCD_REM_TO_LOC_NM */
    public static final String SCD_REM_TO_LOC_NM = "SCD_REM_TO_LOC_NM";

    /** FIRST_CUST_NM */
    public static final String FIRST_CUST_NM = "FIRST_CUST_NM";

    /** SCD_CUST_NM */
    public static final String SCD_CUST_NM = "SCD_CUST_NM";

    /** FIRST_CTAC_TXT */
    public static final String FIRST_CTAC_TXT = "FIRST_CTAC_TXT";

    /** SCD_CTAC_TXT */
    public static final String SCD_CTAC_TXT = "SCD_CTAC_TXT";

    /** CUST_CTY_ADDR */
    public static final String CUST_CTY_ADDR = "CUST_CTY_ADDR";

    /** CUST_ST_ADDR_CD */
    public static final String CUST_ST_ADDR_CD = "CUST_ST_ADDR_CD";

    /** CUST_ZIP_ADDR */
    public static final String CUST_ZIP_ADDR = "CUST_ZIP_ADDR";

    /** FIRST_MSG_TXT */
    public static final String FIRST_MSG_TXT = "FIRST_MSG_TXT";

    /** SCD_MSG_TXT */
    public static final String SCD_MSG_TXT = "SCD_MSG_TXT";

    /** THIRD_MSG_TXT */
    public static final String THIRD_MSG_TXT = "THIRD_MSG_TXT";

    /** HDR_DUNS_NUM */
    public static final String HDR_DUNS_NUM = "HDR_DUNS_NUM";

    /**
     * --------
     */
    /** STMT_TRX_TP_CD */
    public static final String STMT_TRX_TP_CD = "STMT_TRX_TP_CD";

    /** INV_AMT */
    public static final String INV_AMT = "INV_AMT";

    /** DUE_DT */
    public static final String DUE_DT = "DUE_DT";

    /** CASH_DISC_DUE_DT */
    public static final String CASH_DISC_DUE_DT = "CASH_DISC_DUE_DT";

    /** CASH_DISC_AMT */
    public static final String CASH_DISC_AMT = "CASH_DISC_AMT";

    /** CR_RSN_TXT */
    public static final String CR_RSN_TXT = "CR_RSN_TXT";

    /**
     * --------
     */
    /** STMT_SQ_PK */
    public static final String STMT_SQ_PK = "STMT_SQ_PK";

    /** PAGE_NUM */
    public static final String PAGE_NUM = "PAGE_NUM";

    /** AR_STMT_LINE_NUM */
    public static final String AR_STMT_LINE_NUM = "AR_STMT_LINE_NUM";

    /** HDR_CMPY_NM */
    public static final String HDR_CMPY_NM = "HDR_CMPY_NM";

    /** HDR_OFC_NM */
    public static final String HDR_OFC_NM = "HDR_OFC_NM";

    /** HDR_OFC_FIRST_LINE_ADDR */
    public static final String HDR_OFC_FIRST_LINE_ADDR = "HDR_OFC_FIRST_LINE_ADDR";

    /** HDR_OFC_SCD_LINE_ADDR */
    public static final String HDR_OFC_SCD_LINE_ADDR = "HDR_OFC_SCD_LINE_ADDR";

    /** HDR_STMT_PRINT_DT_TXT */
    public static final String HDR_STMT_PRINT_DT_TXT = "HDR_STMT_PRINT_DT_TXT";

    /** HDR_CUST_CD_LB_TXT */
    public static final String HDR_CUST_CD_LB_TXT = "HDR_CUST_CD_LB_TXT";

    /** HDR_CUST_CD_TXT */
    public static final String HDR_CUST_CD_TXT = "HDR_CUST_CD_TXT";

    /** HDR_FIRST_REM_TO_LOC_NM */
    public static final String HDR_FIRST_REM_TO_LOC_NM = "HDR_FIRST_REM_TO_LOC_NM";

    /** HDR_SCD_REM_TO_LOC_NM */
    public static final String HDR_SCD_REM_TO_LOC_NM = "HDR_SCD_REM_TO_LOC_NM";

    /** HDR_FIRST_CUST_NM */
    public static final String HDR_FIRST_CUST_NM = "HDR_FIRST_CUST_NM";

    /** HDR_SCD_CUST_NM */
    public static final String HDR_SCD_CUST_NM = "HDR_SCD_CUST_NM";

    /** HDR_CUST_STR_ADDR */
    public static final String HDR_CUST_STR_ADDR = "HDR_CUST_STR_ADDR";

    /** HDR_CUST_CTY_ADDR */
    public static final String HDR_CUST_CTY_ADDR = "HDR_CUST_CTY_ADDR";

    /** HDR_CUST_ST_ADDR */
    public static final String HDR_CUST_ST_ADDR = "HDR_CUST_ST_ADDR";

    /** HDR_CUST_ST_ADDR_CD */
    public static final String HDR_CUST_ST_ADDR_CD = "HDR_CUST_ST_ADDR_CD";

    /** HDR_CUST_ZIP_ADDR */
    public static final String HDR_CUST_ZIP_ADDR = "HDR_CUST_ZIP_ADDR";

    /** HDR_PMT_DT_LB_TXT */
    public static final String HDR_PMT_DT_LB_TXT = "HDR_PMT_DT_LB_TXT";

    /** HDR_PMT_DT_TXT */
    public static final String HDR_PMT_DT_TXT = "HDR_PMT_DT_TXT";

    /** HDR_FIRST_CTAC_TXT */
    public static final String HDR_FIRST_CTAC_TXT = "HDR_FIRST_CTAC_TXT";

    /** HDR_SCD_CTAC_TXT */
    public static final String HDR_SCD_CTAC_TXT = "HDR_SCD_CTAC_TXT";

    /** HDR_FIRST_MSG_TXT */
    public static final String HDR_FIRST_MSG_TXT = "HDR_FIRST_MSG_TXT";

    /** HDR_SCD_MSG_TXT */
    public static final String HDR_SCD_MSG_TXT = "HDR_SCD_MSG_TXT";

    /** HDR_THIRD_MSG_TXT */
    public static final String HDR_THIRD_MSG_TXT = "HDR_THIRD_MSG_TXT";

    /** HDR_RT_STMT_PRINT_DT_TXT */
    public static final String HDR_RT_STMT_PRINT_DT_TXT = "HDR_RT_STMT_PRINT_DT_TXT";

    /** HDR_IND_ML_STMT_CD */
    public static final String HDR_IND_ML_STMT_CD = "HDR_IND_ML_STMT_CD";

    /** HDR_ONE_LINE_CUST_NM */
    public static final String HDR_ONE_LINE_CUST_NM = "HDR_ONE_LINE_CUST_NM";

    /** HDR_RT_CUST_CD_LB_TXT */
    public static final String HDR_RT_CUST_CD_LB_TXT = "HDR_RT_CUST_CD_LB_TXT";

    /** HDR_RT_CUST_CD_TXT */
    public static final String HDR_RT_CUST_CD_TXT = "HDR_RT_CUST_CD_TXT";

    /** HDR_RT_FIRST_REM_TO_LOC_NM */
    public static final String HDR_RT_FIRST_REM_TO_LOC_NM = "HDR_RT_FIRST_REM_TO_NM";

    /** HDR_RT_SCD_REM_TO_LOC_NM */
    public static final String HDR_RT_SCD_REM_TO_LOC_NM = "HDR_RT_SCD_REM_TO_NM";

    /** DTL_INV_DT */
    public static final String DTL_INV_DT = "DTL_INV_DT";

    /** DTL_INV_NUM */
    public static final String DTL_INV_NUM = "DTL_INV_NUM";

    /** DTL_AR_CUST_REF_NUM */
    public static final String DTL_AR_CUST_REF_NUM = "DTL_AR_CUST_REF_NUM";

    /** DTL_STMT_TRX_TP_CD */
    public static final String DTL_STMT_TRX_TP_CD = "DTL_STMT_TRX_TP_CD";

    /** DTL_INV_AMT */
    public static final String DTL_INV_AMT = "DTL_INV_AMT";

    /** DTL_DUE_DT */
    public static final String DTL_DUE_DT = "DTL_DUE_DT";

    /** DTL_CUST_ISS_PO_NUM_TXT */
    public static final String DTL_CUST_ISS_PO_NUM_TXT = "DTL_CUST_ISS_PO_NUM_TXT";

    /** DTL_RT_INV_NUM */
    public static final String DTL_RT_INV_NUM = "DTL_RT_INV_NUM";

    /** DTL_CASH_DISC_DUE_DT */
    public static final String DTL_CASH_DISC_DUE_DT = "DTL_CASH_DISC_DUE_DT";

    /** DTL_CASH_DISC_AMT */
    public static final String DTL_CASH_DISC_AMT = "DTL_CASH_DISC_AMT";

    /** DTL_INV_NET_AMT */
    public static final String DTL_INV_NET_AMT = "DTL_INV_NET_AMT";

    /** DTL_ORIG_INV_NUM_LB_TXT */
    public static final String DTL_ORIG_INV_NUM_LB_TXT = "DTL_ORIG_INV_NUM_LB_TXT";

    /** DTL_ORIG_INV_NUM */
    public static final String DTL_ORIG_INV_NUM = "DTL_ORIG_INV_NUM";

    /** DTL_CR_RSN_LB_TXT */
    public static final String DTL_CR_RSN_LB_TXT = "DTL_CR_RSN_LB_TXT";

    /** DTL_CR_RSN_TXT */
    public static final String DTL_CR_RSN_TXT = "DTL_CR_RSN_TXT";

    /** FTR_BAL_TOT_AMT */
    public static final String FTR_BAL_TOT_AMT = "FTR_BAL_TOT_AMT";

    /** FTR_ACCT_TOT_AMT_LB_TXT */
    public static final String FTR_ACCT_TOT_AMT_LB_TXT = "FTR_ACCT_TOT_AMT_LB_TXT";

    /** FTR_ACCT_TOT_AMT */
    public static final String FTR_ACCT_TOT_AMT = "FTR_ACCT_TOT_AMT";

    /** FTR_DISC_TOT_AMT */
    public static final String FTR_DISC_TOT_AMT = "FTR_DISC_TOT_AMT";

    /** FTR_INV_TOT_AMT */
    public static final String FTR_INV_TOT_AMT = "FTR_INV_TOT_AMT";

    /** FTR_SCD_ACCT_TOT_AMT_LB_TXT */
    public static final String FTR_SCD_ACCT_TOT_AMT_LB_TXT = "FTR_SCD_ACCT_TOT_AMT_LB_TXT";

    /** FTR_AGE_CUR_AMT */
    public static final String FTR_AGE_CUR_AMT = "FTR_AGE_CUR_AMT";

    /** FTR_AGE_0130_AMT */
    public static final String FTR_AGE_0130_AMT = "FTR_AGE_0130_AMT";

    /** FTR_AGE_3160_AMT */
    public static final String FTR_AGE_3160_AMT = "FTR_AGE_3160_AMT";

    /** FTR_AGE_6190_AMT */
    public static final String FTR_AGE_6190_AMT = "FTR_AGE_6190_AMT";

    /** FTR_AGE_OVER_AMT */
    public static final String FTR_AGE_OVER_AMT = "FTR_AGE_OVER_AMT";

    /** HDR_HDR_DUNS_NUM */
    public static final String HDR_HDR_DUNS_NUM = "HDR_DUNS_NUM";

    /**
     * --------
     */
    /** AGING_TRX_TP_CD */
    public static final String AGING_TRX_TP_CD = "AGING_TRX_TP_CD";

    /** AGING_TRX_TP_CD(INV) */
    public static final String AGING_TRX_TP_CD_INV_J = "agingTrxTpCdInv";

    /** AGING_TRX_TP_CD(CRM) */
    public static final String AGING_TRX_TP_CD_CRM_J = "agingTrxTpCdCrm";

    /** AGING_TRX_TP_CD(DEM) */
    public static final String AGING_TRX_TP_CD_DEM_J = "agingTrxTpCdDem";

    /** AGING_TRX_TP_CD(DED) */
    public static final String AGING_TRX_TP_CD_DED_J = "agingTrxTpCdDed";

    /** AGING_TRX_TP_CD(ACC) */
    public static final String AGING_TRX_TP_CD_ACC_J = "agingTrxTpCdAcc";

    /** AGING_TRX_TP_CD(RCP) */
    public static final String AGING_TRX_TP_CD_RCP_J = "agingTrxTpCdRcp";

    /** DEAL_CARRY_RMNG_BAL_AMT */
    public static final String DEAL_CARRY_RMNG_BAL_AMT = "DEAL_CARRY_RMNG_BAL_AMT";

    /** DEAL_CASH_ON_ACCT_AMT */
    public static final String DEAL_CASH_ON_ACCT_AMT = "DEAL_CASH_ON_ACCT_AMT";

    /** DEAL_CR_BAL_AMT */
    public static final String DEAL_CR_BAL_AMT = "DEAL_CR_BAL_AMT";

    /** FUNC_CARRY_RMNG_BAL_AMT */
    public static final String FUNC_CARRY_RMNG_BAL_AMT = "FUNC_CARRY_RMNG_BAL_AMT";

    /** FUNC_CASH_ON_ACCT_AMT */
    public static final String FUNC_CASH_ON_ACCT_AMT = "FUNC_CASH_ON_ACCT_AMT";

    /** FUNC_CR_BAL_AMT */
    public static final String FUNC_CR_BAL_AMT = "FUNC_CR_BAL_AMT";

    /** DEAL_01_AGING_AMT */
    public static final String DEAL_01_AGING_AMT = "DEAL_01_AGING_AMT";

    /** DEAL_01_DD_AGING_AMT */
    public static final String DEAL_01_DD_AGING_AMT = "DEAL_01_DD_AGING_AMT";

    /** DEAL_01_OI_AGING_AMT */
    public static final String DEAL_01_OI_AGING_AMT = "DEAL_01_OI_AGING_AMT";

    /** DEAL_01_CM_AGING_AMT */
    public static final String DEAL_01_CM_AGING_AMT = "DEAL_01_CM_AGING_AMT";

    /** DEAL_02_AGING_AMT */
    public static final String DEAL_02_AGING_AMT = "DEAL_02_AGING_AMT";

    /** DEAL_02_DD_AGING_AMT */
    public static final String DEAL_02_DD_AGING_AMT = "DEAL_02_DD_AGING_AMT";

    /** DEAL_02_OI_AGING_AMT */
    public static final String DEAL_02_OI_AGING_AMT = "DEAL_02_OI_AGING_AMT";

    /** DEAL_02_CM_AGING_AMT */
    public static final String DEAL_02_CM_AGING_AMT = "DEAL_02_CM_AGING_AMT";

    /** DEAL_03_AGING_AMT */
    public static final String DEAL_03_AGING_AMT = "DEAL_03_AGING_AMT";

    /** DEAL_03_DD_AGING_AMT */
    public static final String DEAL_03_DD_AGING_AMT = "DEAL_03_DD_AGING_AMT";

    /** DEAL_03_OI_AGING_AMT */
    public static final String DEAL_03_OI_AGING_AMT = "DEAL_03_OI_AGING_AMT";

    /** DEAL_03_CM_AGING_AMT */
    public static final String DEAL_03_CM_AGING_AMT = "DEAL_03_CM_AGING_AMT";

    /** DEAL_04_AGING_AMT */
    public static final String DEAL_04_AGING_AMT = "DEAL_04_AGING_AMT";

    /** DEAL_04_DD_AGING_AMT */
    public static final String DEAL_04_DD_AGING_AMT = "DEAL_04_DD_AGING_AMT";

    /** DEAL_04_OI_AGING_AMT */
    public static final String DEAL_04_OI_AGING_AMT = "DEAL_04_OI_AGING_AMT";

    /** DEAL_04_CM_AGING_AMT */
    public static final String DEAL_04_CM_AGING_AMT = "DEAL_04_CM_AGING_AMT";

    /** DEAL_05_AGING_AMT */
    public static final String DEAL_05_AGING_AMT = "DEAL_05_AGING_AMT";

    /** DEAL_05_DD_AGING_AMT */
    public static final String DEAL_05_DD_AGING_AMT = "DEAL_05_DD_AGING_AMT";

    /** DEAL_05_OI_AGING_AMT */
    public static final String DEAL_05_OI_AGING_AMT = "DEAL_05_OI_AGING_AMT";

    /** DEAL_05_CM_AGING_AMT */
    public static final String DEAL_05_CM_AGING_AMT = "DEAL_05_CM_AGING_AMT";

    /** DEAL_06_AGING_AMT */
    public static final String DEAL_06_AGING_AMT = "DEAL_06_AGING_AMT";

    /** DEAL_06_DD_AGING_AMT */
    public static final String DEAL_06_DD_AGING_AMT = "DEAL_06_DD_AGING_AMT";

    /** DEAL_06_OI_AGING_AMT */
    public static final String DEAL_06_OI_AGING_AMT = "DEAL_06_OI_AGING_AMT";

    /** DEAL_06_CM_AGING_AMT */
    public static final String DEAL_06_CM_AGING_AMT = "DEAL_06_CM_AGING_AMT";

    /** DEAL_07_AGING_AMT */
    public static final String DEAL_07_AGING_AMT = "DEAL_07_AGING_AMT";

    /** DEAL_07_DD_AGING_AMT */
    public static final String DEAL_07_DD_AGING_AMT = "DEAL_07_DD_AGING_AMT";

    /** DEAL_07_OI_AGING_AMT */
    public static final String DEAL_07_OI_AGING_AMT = "DEAL_07_OI_AGING_AMT";

    /** DEAL_07_CM_AGING_AMT */
    public static final String DEAL_07_CM_AGING_AMT = "DEAL_07_CM_AGING_AMT";

    /** DEAL_08_AGING_AMT */
    public static final String DEAL_08_AGING_AMT = "DEAL_08_AGING_AMT";

    /** DEAL_08_DD_AGING_AMT */
    public static final String DEAL_08_DD_AGING_AMT = "DEAL_08_DD_AGING_AMT";

    /** DEAL_08_OI_AGING_AMT */
    public static final String DEAL_08_OI_AGING_AMT = "DEAL_08_OI_AGING_AMT";

    /** DEAL_08_CM_AGING_AMT */
    public static final String DEAL_08_CM_AGING_AMT = "DEAL_08_CM_AGING_AMT";

    /** DEAL_09_AGING_AMT */
    public static final String DEAL_09_AGING_AMT = "DEAL_09_AGING_AMT";

    /** DEAL_09_DD_AGING_AMT */
    public static final String DEAL_09_DD_AGING_AMT = "DEAL_09_DD_AGING_AMT";

    /** DEAL_09_OI_AGING_AMT */
    public static final String DEAL_09_OI_AGING_AMT = "DEAL_09_OI_AGING_AMT";

    /** DEAL_09_CM_AGING_AMT */
    public static final String DEAL_09_CM_AGING_AMT = "DEAL_09_CM_AGING_AMT";

    /** DEAL_10_AGING_AMT */
    public static final String DEAL_10_AGING_AMT = "DEAL_10_AGING_AMT";

    /** DEAL_10_DD_AGING_AMT */
    public static final String DEAL_10_DD_AGING_AMT = "DEAL_10_DD_AGING_AMT";

    /** DEAL_10_OI_AGING_AMT */
    public static final String DEAL_10_OI_AGING_AMT = "DEAL_10_OI_AGING_AMT";

    /** DEAL_10_CM_AGING_AMT */
    public static final String DEAL_10_CM_AGING_AMT = "DEAL_10_CM_AGING_AMT";

    /** FUNC_01_AGING_AMT */
    public static final String FUNC_01_AGING_AMT = "FUNC_01_AGING_AMT";

    /** FUNC_01_DD_AGING_AMT */
    public static final String FUNC_01_DD_AGING_AMT = "FUNC_01_DD_AGING_AMT";

    /** FUNC_01_OI_AGING_AMT */
    public static final String FUNC_01_OI_AGING_AMT = "FUNC_01_OI_AGING_AMT";

    /** FUNC_01_CM_AGING_AMT */
    public static final String FUNC_01_CM_AGING_AMT = "FUNC_01_CM_AGING_AMT";

    /** FUNC_02_AGING_AMT */
    public static final String FUNC_02_AGING_AMT = "FUNC_02_AGING_AMT";

    /** FUNC_02_DD_AGING_AMT */
    public static final String FUNC_02_DD_AGING_AMT = "FUNC_02_DD_AGING_AMT";

    /** FUNC_02_OI_AGING_AMT */
    public static final String FUNC_02_OI_AGING_AMT = "FUNC_02_OI_AGING_AMT";

    /** FUNC_02_CM_AGING_AMT */
    public static final String FUNC_02_CM_AGING_AMT = "FUNC_02_CM_AGING_AMT";

    /** FUNC_03_AGING_AMT */
    public static final String FUNC_03_AGING_AMT = "FUNC_03_AGING_AMT";

    /** FUNC_03_DD_AGING_AMT */
    public static final String FUNC_03_DD_AGING_AMT = "FUNC_03_DD_AGING_AMT";

    /** FUNC_03_OI_AGING_AMT */
    public static final String FUNC_03_OI_AGING_AMT = "FUNC_03_OI_AGING_AMT";

    /** FUNC_03_CM_AGING_AMT */
    public static final String FUNC_03_CM_AGING_AMT = "FUNC_03_CM_AGING_AMT";

    /** FUNC_04_AGING_AMT */
    public static final String FUNC_04_AGING_AMT = "FUNC_04_AGING_AMT";

    /** FUNC_04_DD_AGING_AMT */
    public static final String FUNC_04_DD_AGING_AMT = "FUNC_04_DD_AGING_AMT";

    /** FUNC_04_OI_AGING_AMT */
    public static final String FUNC_04_OI_AGING_AMT = "FUNC_04_OI_AGING_AMT";

    /** FUNC_04_CM_AGING_AMT */
    public static final String FUNC_04_CM_AGING_AMT = "FUNC_04_CM_AGING_AMT";

    /** FUNC_05_AGING_AMT */
    public static final String FUNC_05_AGING_AMT = "FUNC_05_AGING_AMT";

    /** FUNC_05_DD_AGING_AMT */
    public static final String FUNC_05_DD_AGING_AMT = "FUNC_05_DD_AGING_AMT";

    /** FUNC_05_OI_AGING_AMT */
    public static final String FUNC_05_OI_AGING_AMT = "FUNC_05_OI_AGING_AMT";

    /** FUNC_05_CM_AGING_AMT */
    public static final String FUNC_05_CM_AGING_AMT = "FUNC_05_CM_AGING_AMT";

    /** FUNC_06_AGING_AMT */
    public static final String FUNC_06_AGING_AMT = "FUNC_06_AGING_AMT";

    /** FUNC_06_DD_AGING_AMT */
    public static final String FUNC_06_DD_AGING_AMT = "FUNC_06_DD_AGING_AMT";

    /** FUNC_06_OI_AGING_AMT */
    public static final String FUNC_06_OI_AGING_AMT = "FUNC_06_OI_AGING_AMT";

    /** FUNC_06_CM_AGING_AMT */
    public static final String FUNC_06_CM_AGING_AMT = "FUNC_06_CM_AGING_AMT";

    /** FUNC_07_AGING_AMT */
    public static final String FUNC_07_AGING_AMT = "FUNC_07_AGING_AMT";

    /** FUNC_07_DD_AGING_AMT */
    public static final String FUNC_07_DD_AGING_AMT = "FUNC_07_DD_AGING_AMT";

    /** FUNC_07_OI_AGING_AMT */
    public static final String FUNC_07_OI_AGING_AMT = "FUNC_07_OI_AGING_AMT";

    /** FUNC_07_CM_AGING_AMT */
    public static final String FUNC_07_CM_AGING_AMT = "FUNC_07_CM_AGING_AMT";

    /** FUNC_08_AGING_AMT */
    public static final String FUNC_08_AGING_AMT = "FUNC_08_AGING_AMT";

    /** FUNC_08_DD_AGING_AMT */
    public static final String FUNC_08_DD_AGING_AMT = "FUNC_08_DD_AGING_AMT";

    /** FUNC_08_OI_AGING_AMT */
    public static final String FUNC_08_OI_AGING_AMT = "FUNC_08_OI_AGING_AMT";

    /** FUNC_08_CM_AGING_AMT */
    public static final String FUNC_08_CM_AGING_AMT = "FUNC_08_CM_AGING_AMT";

    /** FUNC_09_AGING_AMT */
    public static final String FUNC_09_AGING_AMT = "FUNC_09_AGING_AMT";

    /** FUNC_09_DD_AGING_AMT */
    public static final String FUNC_09_DD_AGING_AMT = "FUNC_09_DD_AGING_AMT";

    /** FUNC_09_OI_AGING_AMT */
    public static final String FUNC_09_OI_AGING_AMT = "FUNC_09_OI_AGING_AMT";

    /** FUNC_09_CM_AGING_AMT */
    public static final String FUNC_09_CM_AGING_AMT = "FUNC_09_CM_AGING_AMT";

    /** FUNC_10_AGING_AMT */
    public static final String FUNC_10_AGING_AMT = "FUNC_10_AGING_AMT";

    /** FUNC_10_DD_AGING_AMT */
    public static final String FUNC_10_DD_AGING_AMT = "FUNC_10_DD_AGING_AMT";

    /** FUNC_10_OI_AGING_AMT */
    public static final String FUNC_10_OI_AGING_AMT = "FUNC_10_OI_AGING_AMT";

    /** FUNC_10_CM_AGING_AMT */
    public static final String FUNC_10_CM_AGING_AMT = "FUNC_10_CM_AGING_AMT";

    /** AGING_01_CNT */
    public static final String AGING_01_CNT = "AGING_01_CNT";

    /** DD_AGING_01_CNT */
    public static final String DD_AGING_01_CNT = "DD_AGING_01_CNT";

    /** OI_AGING_01_CNT */
    public static final String OI_AGING_01_CNT = "OI_AGING_01_CNT";

    /** CM_AGING_01_CNT */
    public static final String CM_AGING_01_CNT = "CM_AGING_01_CNT";

    /** AGING_02_CNT */
    public static final String AGING_02_CNT = "AGING_02_CNT";

    /** DD_AGING_02_CNT */
    public static final String DD_AGING_02_CNT = "DD_AGING_02_CNT";

    /** OI_AGING_02_CNT */
    public static final String OI_AGING_02_CNT = "OI_AGING_02_CNT";

    /** CM_AGING_02_CNT */
    public static final String CM_AGING_02_CNT = "CM_AGING_02_CNT";

    /** AGING_03_CNT */
    public static final String AGING_03_CNT = "AGING_03_CNT";

    /** DD_AGING_03_CNT */
    public static final String DD_AGING_03_CNT = "DD_AGING_03_CNT";

    /** OI_AGING_03_CNT */
    public static final String OI_AGING_03_CNT = "OI_AGING_03_CNT";

    /** CM_AGING_03_CNT */
    public static final String CM_AGING_03_CNT = "CM_AGING_03_CNT";

    /** AGING_04_CNT */
    public static final String AGING_04_CNT = "AGING_04_CNT";

    /** DD_AGING_04_CNT */
    public static final String DD_AGING_04_CNT = "DD_AGING_04_CNT";

    /** OI_AGING_04_CNT */
    public static final String OI_AGING_04_CNT = "OI_AGING_04_CNT";

    /** CM_AGING_04_CNT */
    public static final String CM_AGING_04_CNT = "CM_AGING_04_CNT";

    /** AGING_05_CNT */
    public static final String AGING_05_CNT = "AGING_05_CNT";

    /** DD_AGING_05_CNT */
    public static final String DD_AGING_05_CNT = "DD_AGING_05_CNT";

    /** OI_AGING_05_CNT */
    public static final String OI_AGING_05_CNT = "OI_AGING_05_CNT";

    /** CM_AGING_05_CNT */
    public static final String CM_AGING_05_CNT = "CM_AGING_05_CNT";

    /** AGING_06_CNT */
    public static final String AGING_06_CNT = "AGING_06_CNT";

    /** DD_AGING_06_CNT */
    public static final String DD_AGING_06_CNT = "DD_AGING_06_CNT";

    /** OI_AGING_06_CNT */
    public static final String OI_AGING_06_CNT = "OI_AGING_06_CNT";

    /** CM_AGING_06_CNT */
    public static final String CM_AGING_06_CNT = "CM_AGING_06_CNT";

    /** AGING_07_CNT */
    public static final String AGING_07_CNT = "AGING_07_CNT";

    /** DD_AGING_07_CNT */
    public static final String DD_AGING_07_CNT = "DD_AGING_07_CNT";

    /** OI_AGING_07_CNT */
    public static final String OI_AGING_07_CNT = "OI_AGING_07_CNT";

    /** CM_AGING_07_CNT */
    public static final String CM_AGING_07_CNT = "CM_AGING_07_CNT";

    /** AGING_08_CNT */
    public static final String AGING_08_CNT = "AGING_08_CNT";

    /** DD_AGING_08_CNT */
    public static final String DD_AGING_08_CNT = "DD_AGING_08_CNT";

    /** OI_AGING_08_CNT */
    public static final String OI_AGING_08_CNT = "OI_AGING_08_CNT";

    /** CM_AGING_08_CNT */
    public static final String CM_AGING_08_CNT = "CM_AGING_08_CNT";

    /** AGING_09_CNT */
    public static final String AGING_09_CNT = "AGING_09_CNT";

    /** DD_AGING_09_CNT */
    public static final String DD_AGING_09_CNT = "DD_AGING_09_CNT";

    /** OI_AGING_09_CNT */
    public static final String OI_AGING_09_CNT = "OI_AGING_09_CNT";

    /** CM_AGING_09_CNT */
    public static final String CM_AGING_09_CNT = "CM_AGING_09_CNT";

    /** AGING_10_CNT */
    public static final String AGING_10_CNT = "AGING_10_CNT";

    /** DD_AGING_10_CNT */
    public static final String DD_AGING_10_CNT = "DD_AGING_10_CNT";

    /** OI_AGING_10_CNT */
    public static final String OI_AGING_10_CNT = "OI_AGING_10_CNT";

    /** CM_AGING_10_CNT */
    public static final String CM_AGING_10_CNT = "CM_AGING_10_CNT";

    /**
     * Name --------
     */
    /** UPLD_CSV_RQST_ROW_NUM */
    public static final String UPLD_CSV_RQST_ROW_NUM = "UPLD_CSV_RQST_ROW_NUM";

    /** TRX_TP_CD_AND_NUM */
    public static final String TRX_TP_CD_AND_NUM = "TRX_TP_CD_AND_NUM";

    /** AR_CLT_CATG_CD */
    public static final String AR_CLT_CATG_CD = "AR_CLT_CATG_CD";

    /** AR_OPEN_TRX_RSN_CD */
    public static final String AR_OPEN_TRX_RSN_CD = "AR_OPEN_TRX_RSN_CD";

    /** AR_CLT_RVW_GRP_CD */
    public static final String AR_CLT_RVW_GRP_CD = "AR_CLT_RVW_GRP_CD";

    /** AR_CLT_RVW_GRP_SUB_CD */
    public static final String AR_CLT_RVW_GRP_SUB_CD = "AR_CLT_RVW_GRP_SUB_CD";

    /** LGSC_CARR_VND_CD */
    public static final String LGSC_CARR_VND_CD = "LGSC_CARR_VND_CD";

    /** WH_CD */
    public static final String WH_CD = "WH_CD";

    /** AR_RVW_CD */
    public static final String AR_RVW_CD = "AR_RVW_CD";

    /** AR_CRAT_PSN_CD */
    public static final String AR_CRAT_PSN_CD = "AR_CRAT_PSN_CD";

    /** AR_LGSC_STS_CD */
    public static final String AR_LGSC_STS_CD = "AR_LGSC_STS_CD";

    /** AR_SLS_STS_CD */
    public static final String AR_SLS_STS_CD = "AR_SLS_STS_CD";

    /** AR_CLT_NOTE_TP_CD */
    public static final String AR_CLT_NOTE_TP_CD = "AR_CLT_NOTE_TP_CD";

    /** AR_CLT_GRP_TP_CD */
    public static final String AR_CLT_GRP_TP_CD = "AR_CLT_GRP_TP_CD";

    /** AR_CLT_CMNT_TXT */
    public static final String AR_CLT_CMNT_TXT = "AR_CLT_CMNT_TXT";

    /**
     * --------
     */
    /** CLT_NOTE_SQ_NUM */
    public static final String CLT_NOTE_SQ_NUM = "CLT_NOTE_SQ_NUM";

    /**
     * --------
     */
    /** AJE_INTFC_PK */
    public static final String AJE_INTFC_PK = "AJE_INTFC_PK";

    /** AR_ADJ_TRX_TP_CD */
    public static final String AR_ADJ_TRX_TP_CD = "AR_ADJ_TRX_TP_CD";

    /** AR_ADJ_TRX_TP_CD_J */
    public static final String AR_ADJ_TRX_TP_CD_J = "arAdjTrxTpCd";

    /** AR_ADJ_TP_CD */
    public static final String AR_ADJ_TP_CD = "AR_ADJ_TP_CD";

    /** CR_AR_TRX_NUM */
    public static final String CR_AR_TRX_NUM = "CR_AR_TRX_NUM";

    /** CR_AR_TRX_TP_CD */
    public static final String CR_AR_TRX_TP_CD = "CR_AR_TRX_TP_CD";

    /** DEAL_ACCT_AMT */
    public static final String DEAL_ACCT_AMT = "DEAL_ACCT_AMT";

    /** DEAL_ATTRB_ITEM_NM */
    public static final String DEAL_ATTRB_ITEM_NM = "DEAL_ATTRB_ITEM_NM";

    /** FUNC_ACCT_AMT */
    public static final String FUNC_ACCT_AMT = "FUNC_ACCT_AMT";

    /** FUNC_ATTRB_ITEM_NM */
    public static final String FUNC_ATTRB_ITEM_NM = "FUNC_ATTRB_ITEM_NM";

    /** AMT_INFO_ENTY_NM */
    public static final String AMT_INFO_ENTY_NM = "AMT_INFO_ENTY_NM";

    /** AJE_INTFC_CMNT_TXT */
    public static final String AJE_INTFC_CMNT_TXT = "AJE_INTFC_CMNT_TXT";

    /**
     * Name --------
     */
    /** UPLD_HDR_NUM */
    public static final String UPLD_HDR_NUM = "UPLD_HDR_NUM";

    /** AR_RCPT_UPLD_CMNT_TXT */
    public static final String AR_RCPT_UPLD_CMNT_TXT = "AR_RCPT_UPLD_CMNT_TXT";

    /** RCPT_DT_TXT */
    public static final String RCPT_DT_TXT = "RCPT_DT_TXT";

    /** RCPT_CHK_DT_TXT */
    public static final String RCPT_CHK_DT_TXT = "RCPT_CHK_DT_TXT";

    /* */
    /** AR_ADJ_TP_NM */
    public static final String AR_ADJ_TP_NM = "AR_ADJ_TP_NM";

    /** AR_ADJ_TP_SORT_NUM */
    public static final String AR_ADJ_TP_SORT_NUM = "AR_ADJ_TP_SORT_NUM";

    /** AR_ADJ_TP_DESC_TXT */
    public static final String AR_ADJ_TP_DESC_TXT = "AR_ADJ_TP_DESC_TXT";

    /** AR_ADJ_MAN_ENTRY_FLG */
    public static final String AR_ADJ_MAN_ENTRY_FLG = "AR_ADJ_MAN_ENTRY_FLG";

    /** AR_CASH_APP_MAN_ENTRY_TP_CD */
    public static final String AR_CASH_APP_MAN_ENTRY_TP_CD = "AR_CASH_APP_MAN_ENTRY_TP_CD";

    /** AR_ADJ_WF_USE_FLG */
    public static final String AR_ADJ_WF_USE_FLG = "AR_ADJ_WF_USE_FLG";

    /** AR_ADJ_TOC_ENTRY_FLG */
    public static final String AR_ADJ_TOC_ENTRY_FLG = "AR_ADJ_TOC_ENTRY_FLG";

    /** AR_ADJ_TRX_CD */
    public static final String AR_ADJ_TRX_CD = "AR_ADJ_TRX_CD";

    /** AR_ADJ_TRX_RSN_CD */
    public static final String AR_ADJ_TRX_RSN_CD = "AR_ADJ_TRX_RSN_CD";

    /* */
    /** DEAL_AR_ADJ_AMT */
    public static final String DEAL_AR_ADJ_AMT = "DEAL_AR_ADJ_AMT";

    /** FUNC_AR_ADJ_AMT */
    public static final String FUNC_AR_ADJ_AMT = "FUNC_AR_ADJ_AMT";

    /** ORIG_AR_TRX_BAL_PK */
    public static final String ORIG_AR_TRX_BAL_PK = "ORIG_AR_TRX_BAL_PK";

    /** ADJ_DT */
    public static final String ADJ_DT = "ADJ_DT";

    /** ADJ_CMNT_TXT */
    public static final String ADJ_CMNT_TXT = "ADJ_CMNT_TXT";

    /** ADJ_APVL_CMNT_TXT */
    public static final String ADJ_APVL_CMNT_TXT = "ADJ_APVL_CMNT_TXT";

    /** APVL_BY_PSN_CD */
    public static final String APVL_BY_PSN_CD = "APVL_BY_PSN_CD";

    /** AR_ADJ_STS_CD */
    public static final String AR_ADJ_STS_CD = "AR_ADJ_STS_CD";

    /**
     * --------
     */
    /** AR_UN_APPLY_TP_CD */
    public static final String AR_UN_APPLY_TP_CD = "AR_UN_APPLY_TP_CD";

    /** AR_UN_APPLY_STS_CD */
    public static final String AR_UN_APPLY_STS_CD = "AR_UN_APPLY_STS_CD";

    /** DEAL_UN_APPLY_AMT */
    public static final String DEAL_UN_APPLY_AMT = "DEAL_UN_APPLY_AMT";

    /** FUNC_UN_APPLY_AMT */
    public static final String FUNC_UN_APPLY_AMT = "FUNC_UN_APPLY_AMT";

    /* */
    /** SUB_SYS_CD */
    public static final String SUB_SYS_CD = "SUB_SYS_CD";

    /** ONL_BAT_TP_CD */
    public static final String ONL_BAT_TP_CD = "ONL_BAT_TP_CD";

    /**
     * ========
     */
    /** ATT_ADJ_NUM */
    public static final String ATT_ADJ_NUM = "ATT_ADJ_NUM";

    /** ATT_ADJ_NUM */
    public static final String ATT_ADJ_NUM_J = "attAdjNum";

    /** APPLY_CPLT_DT */
    public static final String APPLY_CPLT_DT = "APPLY_CPLT_DT";

    /** TRX_YR_MTH */
    public static final String TRX_YR_MTH = "TRX_YR_MTH";

    /**
     * Name --------
     */
    /** UPLD_CSV_RQST_PK */
    public static final String UPLD_CSV_RQST_PK = "UPLD_CSV_RQST_PK";

    /** DEAL_CASH_DISC_AMT */
    public static final String DEAL_CASH_DISC_AMT = "DEAL_CASH_DISC_AMT";

    /** UPLD_CSV_RQST_CMNT_TXT */
    public static final String UPLD_CSV_RQST_CMNT_TXT = "UPLD_CSV_RQST_CMNT_TXT";

    /**
     * Name --------
     */
    /** GL_DT_TXT */
    public static final String GL_DT_TXT = "GL_DT_TXT";

    /** APP_AR_CUST_REF_NUM */
    public static final String APP_AR_CUST_REF_NUM = "APP_AR_CUST_REF_NUM";

    /**
     * --------
     */
    /** AGING_01_TOT_AMT */
    public static final String AGING_01_TOT_AMT = "AGING_01_TOT_AMT";

    /** AGING_02_TOT_AMT */
    public static final String AGING_02_TOT_AMT = "AGING_02_TOT_AMT";

    /** AGING_03_TOT_AMT */
    public static final String AGING_03_TOT_AMT = "AGING_03_TOT_AMT";

    /** AGING_04_TOT_AMT */
    public static final String AGING_04_TOT_AMT = "AGING_04_TOT_AMT";

    /** AGING_05_TOT_AMT */
    public static final String AGING_05_TOT_AMT = "AGING_05_TOT_AMT";

    /** AGING_06_TOT_AMT */
    public static final String AGING_06_TOT_AMT = "AGING_06_TOT_AMT";

    /** AGING_07_TOT_AMT */
    public static final String AGING_07_TOT_AMT = "AGING_07_TOT_AMT";

    /** AGING_01_TOT_QTY */
    public static final String AGING_01_TOT_QTY = "AGING_01_TOT_QTY";

    /** AGING_02_TOT_QTY */
    public static final String AGING_02_TOT_QTY = "AGING_02_TOT_QTY";

    /** AGING_03_TOT_QTY */
    public static final String AGING_03_TOT_QTY = "AGING_03_TOT_QTY";

    /** AGING_04_TOT_QTY */
    public static final String AGING_04_TOT_QTY = "AGING_04_TOT_QTY";

    /** AGING_05_TOT_QTY */
    public static final String AGING_05_TOT_QTY = "AGING_05_TOT_QTY";

    /** AGING_06_TOT_QTY */
    public static final String AGING_06_TOT_QTY = "AGING_06_TOT_QTY";

    /** AGING_07_TOT_QTY */
    public static final String AGING_07_TOT_QTY = "AGING_07_TOT_QTY";

    /** OVD_STS_CD */
    public static final String OVD_STS_CD = "OVD_STS_CD";

    /** AR_BAL_SET_DT */
    public static final String AR_BAL_SET_DT = "AR_BAL_SET_DT";

    /** AR_BAL_01_QTY */
    public static final String AR_BAL_01_QTY = "AR_BAL_01_QTY";

    /** AR_BAL_01_AMT */
    public static final String AR_BAL_01_AMT = "AR_BAL_01_AMT";

    /** AR_BAL_02_QTY */
    public static final String AR_BAL_02_QTY = "AR_BAL_02_QTY";

    /** AR_BAL_02_AMT */
    public static final String AR_BAL_02_AMT = "AR_BAL_02_AMT";

    /** AR_BAL_03_QTY */
    public static final String AR_BAL_03_QTY = "AR_BAL_03_QTY";

    /** AR_BAL_03_AMT */
    public static final String AR_BAL_03_AMT = "AR_BAL_03_AMT";

    /** PAY_LAST_DT */
    public static final String PAY_LAST_DT = "PAY_LAST_DT";

    /** PAY_LAST_AMT */
    public static final String PAY_LAST_AMT = "PAY_LAST_AMT";

    /** STMT_ISS_DT */
    public static final String STMT_ISS_DT = "STMT_ISS_DT";

    /** STMT_BAL_01_QTY */
    public static final String STMT_BAL_01_QTY = "STMT_BAL_01_QTY";

    /** STMT_BAL_01_AMT */
    public static final String STMT_BAL_01_AMT = "STMT_BAL_01_AMT";

    /** STMT_BAL_02_QTY */
    public static final String STMT_BAL_02_QTY = "STMT_BAL_02_QTY";

    /** STMT_BAL_02_AMT */
    public static final String STMT_BAL_02_AMT = "STMT_BAL_02_AMT";

    /** STMT_BAL_03_QTY */
    public static final String STMT_BAL_03_QTY = "STMT_BAL_03_QTY";

    /** STMT_BAL_03_AMT */
    public static final String STMT_BAL_03_AMT = "STMT_BAL_03_AMT";

    /**
     * --------
     */
    /** EXTN_CASH_DISC_PCT */
    public static final String EXTN_CASH_DISC_PCT = "EXTN_CASH_DISC_PCT";

    /** DEAL_EXTN_CASH_DISC_AMT */
    public static final String DEAL_EXTN_CASH_DISC_AMT = "DEAL_EXTN_CASH_DISC_AMT";

    /**
     * --------
     */
    /** AR_STMT_FLG */
    public static final String AR_STMT_FLG = "AR_STMT_FLG";

    /**
     * --------
     */
    /** CASH_DISC_RNG_THRU_DT */
    public static final String CASH_DISC_RNG_THRU_DT = "CASH_DISC_RNG_THRU_DT";

    /**
     * --------
     */
    /** CHK_RCPT_TOT_AMT */
    public static final String CHK_RCPT_TOT_AMT = "CHK_RCPT_TOT_AMT";

    /** ADD_CHAR_TXT */
    public static final String ADD_CHAR_TXT = "ADD_CHAR_TXT";

    /** DEL_CHAR_CNT */
    public static final String DEL_CHAR_CNT = "DEL_CHAR_CNT";

    /** INV_NUM_EDIT_PROC_TP_CD */
    public static final String INV_NUM_EDIT_PROC_TP_CD = "INV_NUM_EDIT_PROC_TP_CD";

    /** EZUPTIME */
    public static final String EZUPTIME = "EZUPTIME";

    /** EZUPTIMEZONE */
    public static final String EZUPTIMEZONE = "EZUPTIMEZONE";

    /** CR_ANLST_GRP_TOC_CD */
    public static final String CR_ANLST_GRP_TOC_CD = "CR_ANLST_GRP_TOC_CD";

    /** CCY_CD */
    public static final String CCY_CD = "CCY_CD";

    /** AFT_DECL_PNT_DIGIT_NUM */
    public static final String AFT_DECL_PNT_DIGIT_NUM = "AFT_DECL_PNT_DIGIT_NUM";

    /** RCV_DT */
    public static final String RCV_DT = "RCV_DT";

    /** ANSI_INCG_SEND_ID */
    public static final String ANSI_INCG_SEND_ID = "ANSI_INCG_SEND_ID";

    /** ANSI_INCG_CTRL_NUM */
    public static final String ANSI_INCG_CTRL_NUM = "ANSI_INCG_CTRL_NUM";

    /** ANSI_TRX_CD */
    public static final String ANSI_TRX_CD = "ANSI_TRX_CD";

    /** ANSI_INCG_RCV_ID */
    public static final String ANSI_INCG_RCV_ID = "ANSI_INCG_RCV_ID";

    /** ANSI_FUNC_ID */
    public static final String ANSI_FUNC_ID = "ANSI_FUNC_ID";

    /** ENTRY_DT_01 */
    public static final String ENTRY_DT_01 = "ENTRY_DT_01";

    /** ENTRY_TM_01 */
    public static final String ENTRY_TM_01 = "ENTRY_TM_01";

    /** FIRST_BILL_TO_CUST_CD */
    public static final String FIRST_BILL_TO_CUST_CD = "FIRST_BILL_TO_CUST_CD";

    /** CHK_30_NUM */
    public static final String CHK_30_NUM = "CHK_30_NUM";

    /** BANK_ID_NUM */
    public static final String BANK_ID_NUM = "BANK_ID_NUM";

    /** BAT_NUM */
    public static final String BAT_NUM = "BAT_NUM";

    /** RCV_CHK_NUM */
    public static final String RCV_CHK_NUM = "RCV_CHK_NUM";

    /** DEP_DT_01 */
    public static final String DEP_DT_01 = "DEP_DT_01";

    /** ANSI_TRX_HDLG_CD */
    public static final String ANSI_TRX_HDLG_CD = "ANSI_TRX_HDLG_CD";

    /** CHK_AMT */
    public static final String CHK_AMT = "CHK_AMT";

    /** ANSI_CR_DR_FLG */
    public static final String ANSI_CR_DR_FLG = "ANSI_CR_DR_FLG";

    /** ANSI_PMT_FMT_CD */
    public static final String ANSI_PMT_FMT_CD = "ANSI_PMT_FMT_CD";

    /** CUST_BANK_ID_NUM */
    public static final String CUST_BANK_ID_NUM = "CUST_BANK_ID_NUM";

    /** CUST_BANK_ACCT_NUM */
    public static final String CUST_BANK_ACCT_NUM = "CUST_BANK_ACCT_NUM";

    /** CANON_BANK_ID_NUM */
    public static final String CANON_BANK_ID_NUM = "CANON_BANK_ID_NUM";

    /** ANSI_CD_ORIG_COMP_ID */
    public static final String ANSI_CD_ORIG_COMP_ID = "ANSI_CD_ORIG_COMP_ID";

    /** SELL_TP_CD */
    public static final String SELL_TP_CD = "SELL_TP_CD";

    /** AR_RCPT_RCV_TRX_TP_CD */
    public static final String AR_RCPT_RCV_TRX_TP_CD = "AR_RCPT_RCV_TRX_TP_CD";

    /** TRX_TP_NUM */
    public static final String TRX_TP_NUM = "TRX_TP_NUM";

    /** CHK_30_NUM_CD */
    public static final String CHK_30_NUM_CD = "CHK_30_NUM_CD";

    /** CHK_DT_CD */
    public static final String CHK_DT_CD = "CHK_DT_CD";

    /** CHK_APPLY_DT */
    public static final String CHK_APPLY_DT = "CHK_APPLY_DT";

    /** EFF_DT_CD */
    public static final String EFF_DT_CD = "EFF_DT_CD";

    /** CHK_EFF_DT */
    public static final String CHK_EFF_DT = "CHK_EFF_DT";

    /** APVL_FLG_01 */
    public static final String APVL_FLG_01 = "APVL_FLG_01";

    /** BR_BANK_CD */
    public static final String BR_BANK_CD = "BR_BANK_CD";

    /** ACCT_MA_CD */
    public static final String ACCT_MA_CD = "ACCT_MA_CD";

    /** ENTRY_DT_02 */
    public static final String ENTRY_DT_02 = "ENTRY_DT_02";

    /** ENTRY_TM_02 */
    public static final String ENTRY_TM_02 = "ENTRY_TM_02";

    /** ENTRY_TERM_CD_01 */
    public static final String ENTRY_TERM_CD_01 = "ENTRY_TERM_CD_01";

    /** UPD_DT_01 */
    public static final String UPD_DT_01 = "UPD_DT_01";

    /** UPD_TM_01 */
    public static final String UPD_TM_01 = "UPD_TM_01";

    /** UPD_OP_CD_01 */
    public static final String UPD_OP_CD_01 = "UPD_OP_CD_01";

    /** UPD_TERM_CD_01 */
    public static final String UPD_TERM_CD_01 = "UPD_TERM_CD_01";

    /** DEP_DT_02 */
    public static final String DEP_DT_02 = "DEP_DT_02";

    /** APVL_FLG_02 */
    public static final String APVL_FLG_02 = "APVL_FLG_02";

    /** SCD_BILL_TO_CUST_CD */
    public static final String SCD_BILL_TO_CUST_CD = "SCD_BILL_TO_CUST_CD";

    /** INV_30_NUM_CD */
    public static final String INV_30_NUM_CD = "INV_30_NUM_CD";

    /** INV_30_ORIG_NUM */
    public static final String INV_30_ORIG_NUM = "INV_30_ORIG_NUM";

    /** INV_ORIG_TOT_AMT */
    public static final String INV_ORIG_TOT_AMT = "INV_ORIG_TOT_AMT";

    /** INV_DISC_TOT_AMT */
    public static final String INV_DISC_TOT_AMT = "INV_DISC_TOT_AMT";

    /** ANSI_PMT_ACT_CD */
    public static final String ANSI_PMT_ACT_CD = "ANSI_PMT_ACT_CD";

    /** ANSI_DT */
    public static final String ANSI_DT = "ANSI_DT";

    /** ENTRY_DT_03 */
    public static final String ENTRY_DT_03 = "ENTRY_DT_03";

    /** ENTRY_TM_03 */
    public static final String ENTRY_TM_03 = "ENTRY_TM_03";

    /** ENTRY_OP_CD_02 */
    public static final String ENTRY_OP_CD_02 = "ENTRY_OP_CD_02";

    /** ENTRY_TERM_CD_02 */
    public static final String ENTRY_TERM_CD_02 = "ENTRY_TERM_CD_02";

    /** UPD_DT_02 */
    public static final String UPD_DT_02 = "UPD_DT_02";

    /** UPD_TM_02 */
    public static final String UPD_TM_02 = "UPD_TM_02";

    /** UPD_OP_CD_02 */
    public static final String UPD_OP_CD_02 = "UPD_OP_CD_02";

    /** UPD_TERM_CD_02 */
    public static final String UPD_TERM_CD_02 = "UPD_TERM_CD_02";

    /** CASH_APPLY_AMT */
    public static final String CASH_APPLY_AMT = "CASH_APPLY_AMT";

    /** ANSI_RSN_CD */
    public static final String ANSI_RSN_CD = "ANSI_RSN_CD";

    /** REF_CD */
    public static final String REF_CD = "REF_CD";

    /** REF_NUM */
    public static final String REF_NUM = "REF_NUM";

    /** RCV_STS_CD */
    public static final String RCV_STS_CD = "RCV_STS_CD";

    /** ANSI_NM */
    public static final String ANSI_NM = "ANSI_NM";

    /** UPLD_CRAT_METH_TP_CD */
    public static final String UPLD_CRAT_METH_TP_CD = "UPLD_CRAT_METH_TP_CD";

    /** UPLD_AR_RCPT_TP_CD */
    public static final String UPLD_AR_RCPT_TP_CD = "UPLD_AR_RCPT_TP_CD";

    /** UPLD_GL_DT */
    public static final String UPLD_GL_DT = "UPLD_GL_DT";

    /** UPLD_AR_RCPT_TRX_TP_CD */
    public static final String UPLD_AR_RCPT_TRX_TP_CD = "UPLD_AR_RCPT_TRX_TP_CD";

    /** UPLD_RCPT_BAT_NUM */
    public static final String UPLD_RCPT_BAT_NUM = "UPLD_RCPT_BAT_NUM";

    /** UPLD_AR_BANK_ACCT_CD */
    public static final String UPLD_AR_BANK_ACCT_CD = "UPLD_AR_BANK_ACCT_CD";

    /** UPLD_DEAL_CCY_CD */
    public static final String UPLD_DEAL_CCY_CD = "UPLD_DEAL_CCY_CD";

    /** UPLD_AR_CUST_REF_NUM */
    public static final String UPLD_AR_CUST_REF_NUM = "UPLD_AR_CUST_REF_NUM";

    /** UPLD_AR_CUST_REF_TP_CD */
    public static final String UPLD_AR_CUST_REF_TP_CD = "UPLD_AR_CUST_REF_TP_CD";

    /** UPLD_RCPT_FIRST_CMNT_TXT */
    public static final String UPLD_RCPT_FIRST_CMNT_TXT = "UPLD_RCPT_FIRST_CMNT_TXT";

    /** UPLD_RCPT_SCD_CMNT_TXT */
    public static final String UPLD_RCPT_SCD_CMNT_TXT = "UPLD_RCPT_SCD_CMNT_TXT";

    /** INV_30_NUM */
    public static final String INV_30_NUM = "INV_30_NUM";

    /** ANSI_PMT_METH_CD */
    public static final String ANSI_PMT_METH_CD = "ANSI_PMT_METH_CD";

    /** UPLD_TOC_CD */
    public static final String UPLD_TOC_CD = "UPLD_TOC_CD";

    /** UPLD_COA_PROD_CD */
    public static final String UPLD_COA_PROD_CD = "UPLD_COA_PROD_CD";

    /**
     * ======== 'Interface Table' Java Attribune Name ========
     */
    /** CHK_NUM_TXT */
    public static final String CHK_NUM_TXT = "CHK_NUM_TXT";

    /** CUST_CD_TXT */
    public static final String CUST_CD_TXT = "CUST_CD_TXT";

    /** DPST_DT_TXT */
    public static final String DEP_DT_TXT = "DEP_DT_TXT";

    /** INV_NUM_TXT */
    public static final String INV_NUM_TXT = "INV_NUM_TXT";

    /** NET_AMT_TXT */
    public static final String NET_AMT_TXT = "NET_AMT_TXT";

    /**
     * ======== 'AJE_AR_INTFC' Java Attribune Name ========
     */
    /** AJE_CRAT_VRSN_NUM */
    public static final String AJE_CRAT_VRSN_NUM = "AJE_CRAT_VRSN_NUM";

    /** AJE_CRAT_CPLT_FLG */
    public static final String AJE_CRAT_CPLT_FLG = "AJE_CRAT_CPLT_FLG";

    /** AJE_CRAT_VRSN_NUM_J */
    public static final String AJE_CRAT_VRSN_NUM_J = "ajeCratVrsnNum";

    /** AJE_STATUS_FLG */
    public static final String AJE_STATUS_FLG_J = "ajeStatusFlg";

    /** AJE_CRAT_CPLT_FLG_J */
    public static final String AJE_CRAT_CPLT_FLG_J = "ajeCratCpltFlg";

    /**
     * ======== 'AR_CR_CARD_PMT_UPLD_WRK Table' Java Attribune Name ========
     */
    /** UPLD_CSV_DATA_TXT */
    public static final String UPLD_CSV_DATA_TXT = "UPLD_CSV_DATA_TXT";

    /** UPLD_CSV_PMT_TXT */
    public static final String UPLD_CSV_PMT_TXT = "UPLD_CSV_PMT_TXT";

    /**
     * ======== 'AR_DSO_MLY_HIST Table' Java Attribune Name ========
     */
    /** DSO_AVG_DAYS_AOT */
    public static final String DSO_AVG_DAYS_AOT = "DSO_AVG_DAYS_AOT";

    /** DSO_CNT */
    public static final String DSO_CNT = "DSO_CNT";

    /** DSO_AMT */
    public static final String DSO_AMT = "DSO_AMT";

    /** DSO_TRX_BAL_AMT */
    public static final String DSO_TRX_BAL_AMT = "DSO_TRX_BAL_AMT";

    /** DSO_AVG_DAYS_AOT_J */
    public static final String DSO_AVG_DAYS_AOT_J = "dsoAvgDaysAot";

    /** DSO_CNT_J */
    public static final String DSO_CNT_J = "dsoCnt";

    /** DSO_AMT_J */
    public static final String DSO_AMT_J = "dsoAmt";

    /** DSO_TRX_BAL_AMT_J */
    public static final String DSO_TRX_BAL_AMT_J = "dsoTrxBalAmt";

    /**
     * ======== 'BILL_TO_CUST Table' Java Attribune Name ========
     */
    /** LOC_NM */
    public static final String LOC_NM = "LOC_NM";

    /** LOC_NM_J */
    public static final String LOC_NM_J = "locNm";

    /**
     * Name ========
     */
    /** GLBL_CMPY_CD */
    public static final String GLBL_CMPY_CD_J = "glblCmpyCd";

    /** APPLY_GRP_KEY */
    public static final String APPLY_GRP_KEY_J = "applyGrpKey";

    /** RCPT_IN_PROC_SQ_PK */
    public static final String RCPT_IN_PROC_SQ_PK_J = "rcptInProcSqPk";

    /** RCV_HDR_NUM */
    public static final String RCV_HDR_NUM_J = "rcvHdrNum";

    /** RCPT_NUM */
    public static final String RCPT_NUM_J = "rcptNum";

    /** RCPT_HD_LAST_UPD_TS */
    public static final String RCPT_HDR_LAST_UPD_TS_J = "rcptHdrLastUpdTs";

    /** RCPT_HD_TZ */
    public static final String RCPT_HDR_TZ_J = "rcptHdrTz";

    /** RCPT_TRX_BAL_LAST_UPD_TS */
    public static final String RCPT_TRX_BAL_LAST_UPD_TS_J = "rcptTrxBalLastUpdTs";

    /** RCPT_TRX_BAL_TZ */
    public static final String RCPT_TRX_BAL_TZ_J = "rcptTrxBalTz";

    /** RCPT_DTL_NUM */
    public static final String RCPT_DTL_NUM_J = "rcptDtlNum";

    /** RCPT_TRX_BAL_PK */
    public static final String RCPT_TRX_BAL_PK_J = "rcptTrxBalPk";

    /**
     * Name ========
     */
    /** RCPT_STS_CD */
    public static final String RCPT_STS_CD_J = "rcptStsCd";

    /** RCPT_DT */
    public static final String RCPT_DT_J = "rcptDt";

    /**
     * Name ========
     */
    /** BAT_STS_CD */
    public static final String BAT_STS_CD_J = "batStsCd";

    /**
     * ========
     */
    /** DEAL_CARRY_RMNG_BAL_AMT */
    public static final String DEAL_CARRY_RMNG_BAL_AMT_J = "dealCarryRmngBalAmt";

    /** DEAL_ORIG_GRS_AMT */
    public static final String DEAL_ORIG_GRS_AMT_J = "dealOrigGrsAmt";

    /** DEAL_APPLY_GRS_AMT */
    public static final String DEAL_APPLY_GRS_AMT_J = "dealApplyGrsAmt";

    /** DEAL_APPLY_CASH_DISC_AMT */
    public static final String DEAL_APPLY_CASH_DISC_AMT_J = "dealApplyCashDiscAmt";

    /** DEAL_APPLY_CR_AMT */
    public static final String DEAL_APPLY_CR_AMT_J = "dealApplyCrAmt";

    /** DEAL_APPLY_ADJ_AMT */
    public static final String DEAL_APPLY_ADJ_AMT_J = "dealApplyAdjAmt";

    /** DEAL_APPLY_ADJ_RSVD_AMT */
    public static final String DEAL_APPLY_ADJ_RSVD_AMT_J = "dealApplyAdjRsvdAmt";

    /** DEAL_NET_SLS_AMT */
    public static final String DEAL_NET_SLS_AMT_J = "dealNetSlsAmt";

    /** DEAL_FRT_AMT */
    public static final String DEAL_FRT_AMT_J = "dealFrtAmt";

    /** DEAL_TAX_AMT */
    public static final String DEAL_TAX_AMT_J = "dealTaxAmt";

    /** DEAL_INV_DISC_AMT */
    public static final String DEAL_INV_DISC_AMT_J = "dealInvDiscAmt";

    /** DEAL_PRMO_DISC_AMT */
    public static final String DEAL_PRMO_DISC_AMT_J = "dealPrmoDiscAmt";

    /** DEAL_CR_BAL_AMT */
    public static final String DEAL_CR_BAL_AMT_J = "dealCrBalAmt";

    /** DEAL_CASH_ON_ACCT_AMT */
    public static final String DEAL_CASH_ON_ACCT_AMT_J = "dealCashOnAcctAmt";

    /** FUNC_CARRY_RMNG_BAL_AMT */
    public static final String FUNC_CARRY_RMNG_BAL_AMT_J = "funcCarryRmngBalAmt";

    /** FUNC_ORIG_GRS_AMT */
    public static final String FUNC_ORIG_GRS_AMT_J = "funcOrigGrsAmt";

    /** FUNC_APPLY_GRS_AMT */
    public static final String FUNC_APPLY_GRS_AMT_J = "funcApplyGrsAmt";

    /** FUNC_APPLY_CASH_DISC_AMT */
    public static final String FUNC_APPLY_CASH_DISC_AMT_J = "funcApplyCashDiscAmt";

    /** FUNC_APPLY_CR_AMT */
    public static final String FUNC_APPLY_CR_AMT_J = "funcApplyCrAmt";

    /** FUNC_APPLY_ADJ_AMT */
    public static final String FUNC_APPLY_ADJ_AMT_J = "funcApplyAdjAmt";

    /** FUNC_APPLY_ADJ_RSVD_AMT */
    public static final String FUNC_APPLY_ADJ_RSVD_AMT_J = "funcApplyAdjRsvdAmt";

    /** FUNC_RVAL_VAR_AMT */
    public static final String FUNC_RVAL_VAR_AMT_J = "funcRvalVarAmt";

    /** FUNC_NET_SLS_AMT */
    public static final String FUNC_NET_SLS_AMT_J = "funcNetSlsAmt";

    /** FUNC_FRT_AMT */
    public static final String FUNC_FRT_AMT_J = "funcFrtAmt";

    /** FUNC_TAX_AMT */
    public static final String FUNC_TAX_AMT_J = "funcTaxAmt";

    /** FUNC_INV_DISC_AMT */
    public static final String FUNC_INV_DISC_AMT_J = "funcInvDiscAmt";

    /** FUNC_PRMO_DISC_AMT */
    public static final String FUNC_PRMO_DISC_AMT_J = "funcPrmoDiscAmt";

    /** FUNC_CR_BAL_AMT */
    public static final String FUNC_CR_BAL_AMT_J = "funcCrBalAmt";

    /** FUNC_CASH_ON_ACCT_AMT */
    public static final String FUNC_CASH_ON_ACCT_AMT_J = "funcCashOnAcctAmt";

    /** DEAL_01_AGING_AMT */
    public static final String DEAL_01_AGING_AMT_J = "deal01AgingAmt";

    /** DEAL_01_DD_AGING_AMT */
    public static final String DEAL_01_DD_AGING_AMT_J = "deal01DdAgingAmt";

    /** DEAL_01_OI_AGING_AMT */
    public static final String DEAL_01_OI_AGING_AMT_J = "deal01OiAgingAmt";

    /** DEAL_01_CM_AGING_AMT */
    public static final String DEAL_01_CM_AGING_AMT_J = "deal01CmAgingAmt";

    /** FUNC_01_AGING_AMT */
    public static final String FUNC_01_AGING_AMT_J = "func01AgingAmt";

    /** FUNC_01_DD_AGING_AMT */
    public static final String FUNC_01_DD_AGING_AMT_J = "func01DdAgingAmt";

    /** FUNC_01_OI_AGING_AMT */
    public static final String FUNC_01_OI_AGING_AMT_J = "func01OiAgingAmt";

    /** FUNC_01_CM_AGING_AMT */
    public static final String FUNC_01_CM_AGING_AMT_J = "func01CmAgingAmt";

    /** AGING_01_CNT */
    public static final String AGING_01_CNT_J = "aging01Cnt";

    /** DD_AGING_01_CNT */
    public static final String DD_AGING_01_CNT_J = "ddAging01Cnt";

    /** OI_AGING_01_CNT */
    public static final String OI_AGING_01_CNT_J = "oiAging01Cnt";

    /** CM_AGING_01_CNT */
    public static final String CM_AGING_01_CNT_J = "cmAging01Cnt";

    /** DEAL_02_AGING_AMT */
    public static final String DEAL_02_AGING_AMT_J = "deal02AgingAmt";

    /** DEAL_02_DD_AGING_AMT */
    public static final String DEAL_02_DD_AGING_AMT_J = "deal02DdAgingAmt";

    /** DEAL_02_OI_AGING_AMT */
    public static final String DEAL_02_OI_AGING_AMT_J = "deal02OiAgingAmt";

    /** DEAL_02_CM_AGING_AMT */
    public static final String DEAL_02_CM_AGING_AMT_J = "deal02CmAgingAmt";

    /** FUNC_02_AGING_AMT */
    public static final String FUNC_02_AGING_AMT_J = "func02AgingAmt";

    /** FUNC_02_DD_AGING_AMT */
    public static final String FUNC_02_DD_AGING_AMT_J = "func02DdAgingAmt";

    /** FUNC_02_OI_AGING_AMT */
    public static final String FUNC_02_OI_AGING_AMT_J = "func02OiAgingAmt";

    /** FUNC_02_CM_AGING_AMT */
    public static final String FUNC_02_CM_AGING_AMT_J = "func02CmAgingAmt";

    /** AGING_02_CNT */
    public static final String AGING_02_CNT_J = "aging02Cnt";

    /** DD_AGING_02_CNT */
    public static final String DD_AGING_02_CNT_J = "ddAging02Cnt";

    /** OI_AGING_02_CNT */
    public static final String OI_AGING_02_CNT_J = "oiAging02Cnt";

    /** CM_AGING_02_CNT */
    public static final String CM_AGING_02_CNT_J = "cmAging02Cnt";

    /** DEAL_03_AGING_AMT */
    public static final String DEAL_03_AGING_AMT_J = "deal03AgingAmt";

    /** DEAL_03_DD_AGING_AMT */
    public static final String DEAL_03_DD_AGING_AMT_J = "deal03DdAgingAmt";

    /** DEAL_03_OI_AGING_AMT */
    public static final String DEAL_03_OI_AGING_AMT_J = "deal03OiAgingAmt";

    /** DEAL_03_CM_AGING_AMT */
    public static final String DEAL_03_CM_AGING_AMT_J = "deal03CmAgingAmt";

    /** FUNC_03_AGING_AMT */
    public static final String FUNC_03_AGING_AMT_J = "func03AgingAmt";

    /** FUNC_03_DD_AGING_AMT */
    public static final String FUNC_03_DD_AGING_AMT_J = "func03DdAgingAmt";

    /** FUNC_03_OI_AGING_AMT */
    public static final String FUNC_03_OI_AGING_AMT_J = "func03OiAgingAmt";

    /** FUNC_03_CM_AGING_AMT */
    public static final String FUNC_03_CM_AGING_AMT_J = "func03CmAgingAmt";

    /** AGING_03_CNT */
    public static final String AGING_03_CNT_J = "aging03Cnt";

    /** DD_AGING_03_CNT */
    public static final String DD_AGING_03_CNT_J = "ddAging03Cnt";

    /** OI_AGING_03_CNT */
    public static final String OI_AGING_03_CNT_J = "oiAging03Cnt";

    /** CM_AGING_03_CNT */
    public static final String CM_AGING_03_CNT_J = "cmAging03Cnt";

    /** DEAL_04_AGING_AMT */
    public static final String DEAL_04_AGING_AMT_J = "deal04AgingAmt";

    /** DEAL_04_DD_AGING_AMT */
    public static final String DEAL_04_DD_AGING_AMT_J = "deal04DdAgingAmt";

    /** DEAL_04_OI_AGING_AMT */
    public static final String DEAL_04_OI_AGING_AMT_J = "deal04OiAgingAmt";

    /** DEAL_04_CM_AGING_AMT */
    public static final String DEAL_04_CM_AGING_AMT_J = "deal04CmAgingAmt";

    /** FUNC_04_AGING_AMT */
    public static final String FUNC_04_AGING_AMT_J = "func04AgingAmt";

    /** FUNC_04_DD_AGING_AMT */
    public static final String FUNC_04_DD_AGING_AMT_J = "func04DdAgingAmt";

    /** FUNC_04_OI_AGING_AMT */
    public static final String FUNC_04_OI_AGING_AMT_J = "func04OiAgingAmt";

    /** FUNC_04_CM_AGING_AMT */
    public static final String FUNC_04_CM_AGING_AMT_J = "func04CmAgingAmt";

    /** AGING_04_CNT */
    public static final String AGING_04_CNT_J = "aging04Cnt";

    /** DD_AGING_04_CNT */
    public static final String DD_AGING_04_CNT_J = "ddAging04Cnt";

    /** OI_AGING_04_CNT */
    public static final String OI_AGING_04_CNT_J = "oiAging04Cnt";

    /** CM_AGING_04_CNT */
    public static final String CM_AGING_04_CNT_J = "cmAging04Cnt";

    /** DEAL_05_AGING_AMT */
    public static final String DEAL_05_AGING_AMT_J = "deal05AgingAmt";

    /** DEAL_05_DD_AGING_AMT */
    public static final String DEAL_05_DD_AGING_AMT_J = "deal05DdAgingAmt";

    /** DEAL_05_OI_AGING_AMT */
    public static final String DEAL_05_OI_AGING_AMT_J = "deal05OiAgingAmt";

    /** DEAL_05_CM_AGING_AMT */
    public static final String DEAL_05_CM_AGING_AMT_J = "deal05CmAgingAmt";

    /** FUNC_05_AGING_AMT */
    public static final String FUNC_05_AGING_AMT_J = "func05AgingAmt";

    /** FUNC_05_DD_AGING_AMT */
    public static final String FUNC_05_DD_AGING_AMT_J = "func05DdAgingAmt";

    /** FUNC_05_OI_AGING_AMT */
    public static final String FUNC_05_OI_AGING_AMT_J = "func05OiAgingAmt";

    /** FUNC_05_CM_AGING_AMT */
    public static final String FUNC_05_CM_AGING_AMT_J = "func05CmAgingAmt";

    /** AGING_05_CNT */
    public static final String AGING_05_CNT_J = "aging05Cnt";

    /** DD_AGING_05_CNT */
    public static final String DD_AGING_05_CNT_J = "ddAging05Cnt";

    /** OI_AGING_05_CNT */
    public static final String OI_AGING_05_CNT_J = "oiAging05Cnt";

    /** CM_AGING_05_CNT */
    public static final String CM_AGING_05_CNT_J = "cmAging05Cnt";

    /** DEAL_06_AGING_AMT */
    public static final String DEAL_06_AGING_AMT_J = "deal06AgingAmt";

    /** DEAL_06_DD_AGING_AMT */
    public static final String DEAL_06_DD_AGING_AMT_J = "deal06DdAgingAmt";

    /** DEAL_06_OI_AGING_AMT */
    public static final String DEAL_06_OI_AGING_AMT_J = "deal06OiAgingAmt";

    /** DEAL_06_CM_AGING_AMT */
    public static final String DEAL_06_CM_AGING_AMT_J = "deal06CmAgingAmt";

    /** FUNC_06_AGING_AMT */
    public static final String FUNC_06_AGING_AMT_J = "func06AgingAmt";

    /** FUNC_06_DD_AGING_AMT */
    public static final String FUNC_06_DD_AGING_AMT_J = "func06DdAgingAmt";

    /** FUNC_06_OI_AGING_AMT */
    public static final String FUNC_06_OI_AGING_AMT_J = "func06OiAgingAmt";

    /** FUNC_06_CM_AGING_AMT */
    public static final String FUNC_06_CM_AGING_AMT_J = "func06CmAgingAmt";

    /** AGING_06_CNT */
    public static final String AGING_06_CNT_J = "aging06Cnt";

    /** DD_AGING_06_CNT */
    public static final String DD_AGING_06_CNT_J = "ddAging06Cnt";

    /** OI_AGING_06_CNT */
    public static final String OI_AGING_06_CNT_J = "oiAging06Cnt";

    /** CM_AGING_06_CNT */
    public static final String CM_AGING_06_CNT_J = "cmAging06Cnt";

    /** DEAL_07_AGING_AMT */
    public static final String DEAL_07_AGING_AMT_J = "deal07AgingAmt";

    /** DEAL_07_DD_AGING_AMT */
    public static final String DEAL_07_DD_AGING_AMT_J = "deal07DdAgingAmt";

    /** DEAL_07_OI_AGING_AMT */
    public static final String DEAL_07_OI_AGING_AMT_J = "deal07OiAgingAmt";

    /** DEAL_07_CM_AGING_AMT */
    public static final String DEAL_07_CM_AGING_AMT_J = "deal07CmAgingAmt";

    /** FUNC_07_AGING_AMT */
    public static final String FUNC_07_AGING_AMT_J = "func07AgingAmt";

    /** FUNC_07_DD_AGING_AMT */
    public static final String FUNC_07_DD_AGING_AMT_J = "func07DdAgingAmt";

    /** FUNC_07_OI_AGING_AMT */
    public static final String FUNC_07_OI_AGING_AMT_J = "func07OiAgingAmt";

    /** FUNC_07_CM_AGING_AMT */
    public static final String FUNC_07_CM_AGING_AMT_J = "func07CmAgingAmt";

    /** AGING_07_CNT */
    public static final String AGING_07_CNT_J = "aging07Cnt";

    /** DD_AGING_07_CNT */
    public static final String DD_AGING_07_CNT_J = "ddAging07Cnt";

    /** OI_AGING_07_CNT */
    public static final String OI_AGING_07_CNT_J = "oiAging07Cnt";

    /** CM_AGING_07_CNT */
    public static final String CM_AGING_07_CNT_J = "cmAging07Cnt";

    /** DEAL_08_AGING_AMT */
    public static final String DEAL_08_AGING_AMT_J = "deal08AgingAmt";

    /** DEAL_08_DD_AGING_AMT */
    public static final String DEAL_08_DD_AGING_AMT_J = "deal08DdAgingAmt";

    /** DEAL_08_OI_AGING_AMT */
    public static final String DEAL_08_OI_AGING_AMT_J = "deal08OiAgingAmt";

    /** DEAL_08_CM_AGING_AMT */
    public static final String DEAL_08_CM_AGING_AMT_J = "deal08CmAgingAmt";

    /** FUNC_08_AGING_AMT */
    public static final String FUNC_08_AGING_AMT_J = "func08AgingAmt";

    /** FUNC_08_DD_AGING_AMT */
    public static final String FUNC_08_DD_AGING_AMT_J = "func08DdAgingAmt";

    /** FUNC_08_OI_AGING_AMT */
    public static final String FUNC_08_OI_AGING_AMT_J = "func08OiAgingAmt";

    /** FUNC_08_CM_AGING_AMT */
    public static final String FUNC_08_CM_AGING_AMT_J = "func08CmAgingAmt";

    /** AGING_08_CNT */
    public static final String AGING_08_CNT_J = "aging08Cnt";

    /** DD_AGING_08_CNT */
    public static final String DD_AGING_08_CNT_J = "ddAging08Cnt";

    /** OI_AGING_08_CNT */
    public static final String OI_AGING_08_CNT_J = "oiAging08Cnt";

    /** CM_AGING_08_CNT */
    public static final String CM_AGING_08_CNT_J = "cmAging08Cnt";

    /** DEAL_09_AGING_AMT */
    public static final String DEAL_09_AGING_AMT_J = "deal09AgingAmt";

    /** DEAL_09_DD_AGING_AMT */
    public static final String DEAL_09_DD_AGING_AMT_J = "deal09DdAgingAmt";

    /** DEAL_09_OI_AGING_AMT */
    public static final String DEAL_09_OI_AGING_AMT_J = "deal09OiAgingAmt";

    /** DEAL_09_CM_AGING_AMT */
    public static final String DEAL_09_CM_AGING_AMT_J = "deal09CmAgingAmt";

    /** FUNC_09_AGING_AMT */
    public static final String FUNC_09_AGING_AMT_J = "func09AgingAmt";

    /** FUNC_09_DD_AGING_AMT */
    public static final String FUNC_09_DD_AGING_AMT_J = "func09DdAgingAmt";

    /** FUNC_09_OI_AGING_AMT */
    public static final String FUNC_09_OI_AGING_AMT_J = "func09OiAgingAmt";

    /** FUNC_09_CM_AGING_AMT */
    public static final String FUNC_09_CM_AGING_AMT_J = "func09CmAgingAmt";

    /** AGING_09_CNT */
    public static final String AGING_09_CNT_J = "aging09Cnt";

    /** DD_AGING_09_CNT */
    public static final String DD_AGING_09_CNT_J = "ddAging09Cnt";

    /** OI_AGING_09_CNT */
    public static final String OI_AGING_09_CNT_J = "oiAging09Cnt";

    /** CM_AGING_09_CNT */
    public static final String CM_AGING_09_CNT_J = "cmAging09Cnt";

    /** DEAL_10_AGING_AMT */
    public static final String DEAL_10_AGING_AMT_J = "deal10AgingAmt";

    /** DEAL_10_DD_AGING_AMT */
    public static final String DEAL_10_DD_AGING_AMT_J = "deal10DdAgingAmt";

    /** DEAL_10_OI_AGING_AMT */
    public static final String DEAL_10_OI_AGING_AMT_J = "deal10OiAgingAmt";

    /** DEAL_10_CM_AGING_AMT */
    public static final String DEAL_10_CM_AGING_AMT_J = "deal10CmAgingAmt";

    /** FUNC_10_AGING_AMT */
    public static final String FUNC_10_AGING_AMT_J = "func10AgingAmt";

    /** FUNC_10_DD_AGING_AMT */
    public static final String FUNC_10_DD_AGING_AMT_J = "func10DdAgingAmt";

    /** FUNC_10_OI_AGING_AMT */
    public static final String FUNC_10_OI_AGING_AMT_J = "func10OiAgingAmt";

    /** FUNC_10_CM_AGING_AMT */
    public static final String FUNC_10_CM_AGING_AMT_J = "func10CmAgingAmt";

    /** AGING_10_CNT */
    public static final String AGING_10_CNT_J = "aging10Cnt";

    /** DD_AGING_10_CNT */
    public static final String DD_AGING_10_CNT_J = "ddAging10Cnt";

    /** OI_AGING_10_CNT */
    public static final String OI_AGING_10_CNT_J = "oiAging10Cnt";

    /** CM_AGING_10_CNT */
    public static final String CM_AGING_10_CNT_J = "cmAging10Cnt";

    /**
     * Attribune Name ========
     */
    /** UPLD_CSV_RQST_PK */
    public static final String UPLD_CSV_RQST_PK_J = "upldCsvRqstPk";

    /**
     * ========
     */
    /** AR_TRX_TP_CD */
    public static final String AR_TRX_TP_CD_J = "arTrxTpCd";

    /** AR_TRX_NUM */
    public static final String AR_TRX_NUM_J = "arTrxNum";

    /** INV_30_NUM */
    public static final String INV_30_NUM_J = "inv30Num";

    /** COA_PROD_CD */
    public static final String COA_PROD_CD_J = "coaProdCd";

    /** CPO_ORD_NUM */
    public static final String CPO_ORD_NUM_J = "cpoOrdNum";

    /**
     * ========
     */
    /** CARR_CD */
    public static final String CARR_CD_J = "carrCd";

    /* */
    /** WH_CD */
    public static final String WH_CD_J = "whCd";

    /**
     * ========
     */
    /** PSN_CD */
    public static final String PSN_CD = "PSN_CD";

    /** PSN_CD */
    public static final String PSN_CD_J = "psnCd";

    /**
     * ========
     */
    /** PAYER_CUST_CD */
    public static final String PAYER_CUST_CD_J = "payerCustCd";

    /**
     * ========
     */
    /** INV_NUM */
    public static final String INV_NUM_J = "invNum";

    /**
     * Name ========
     */
    /** AR_TRX_BAL_PK */
    public static final String AR_TRX_BAL_PK_J = "arTrxBalPk";

    /** CASH_DISC_RNG_THRU_DT */
    public static final String CASH_DISC_RNG_THRU_DT_J = "cashDiscRngThruDt";

    /** DEAL_CCY_CD */
    public static final String DEAL_CCY_CD_J = "dealCcyCd";

    /** EXTN_CASH_DISC_PCT */
    public static final String EXTN_CASH_DISC_PCT_J = "extnCashDiscPct";

    /** DEAL_EXTN_CASH_DISC_AMT */
    public static final String DEAL_EXTN_CASH_DISC_AMT_J = "dealExtnCashDiscAmt";

    /**
     * ========
     */
    /** PMT_TERM_CD */
    public static final String PMT_TERM_CD_J = "pmtTermCd";

    /**
     * ========
     */
    /** CUST_CD */
    public static final String CUST_CD_J = "custCd";

    /** BILL_SELL_TP_CD */
    public static final String BILL_SELL_TP_CD_J = "billSellTpCd";

    /** MAKE_DT */
    public static final String MAKE_DT_J = "makeDt";

    /**
     * Name ========
     */
    /** STMT_SQ_PK */
    public static final String STMT_SQ_PK_J = "stmtSqPk";

    /** STMT_TRX_TP_CD */
    public static final String STMT_TRX_TP_CD_J = "stmtTrxTpCd";

    /**
     * ========
     */
    /** BILL_TO_CUST_CD */
    public static final String BILL_TO_CUST_CD_J = "billToCustCd";

    /** SELL_TO_CUST_CD */
    public static final String SELL_TO_CUST_CD_J = "sellToCustCd";

    /** AGING_PER_TP_CD */
    public static final String AGING_PER_TP_CD_J = "agingPerTpCd";

    /**
     * ========
     */
    /** CR_AR_TRX_BAL_PK */
    public static final String CR_AR_TRX_BAL_PK_J = "crArTrxBalPk";

    /** CASH_APP_DT */
    public static final String CASH_APP_DT_J = "cashAppDt";

    /** AR_CASH_APP_PK */
    public static final String AR_CASH_APP_PK_J = "arCashAppPk";

    /** AR_CASH_APP_SQ_NUM */
    public static final String AR_CASH_APP_SQ_NUM_J = "arCashAppSqNum";

    /** CASH_APP_DT */
    public static final String TRX_CD_J = "trxCd";

    /** APPLY_TP_CD */
    public static final String APPLY_TP_CD_J = "applyTpCd";

    /** AR_ADJ_TP_CD_J */
    public static final String AR_ADJ_TP_CD_J = "arAdjTpCd";

    /**
     * ========
     */
    /** SUB_SYS_CD */
    public static final String SUB_SYS_CD_J = "subSysCd";

    /** ONL_BAT_TP_CD */
    public static final String ONL_BAT_TP_CD_J = "onlBatTpCd";

    /**
     * Name ========
     */
    /** RCV_STS_CD */
    public static final String RCV_STS_CD_J = "rcvStsCd";

    /** RCV_FUNC_ID */
    public static final String RCV_FUNC_ID_J = "rcvFuncId";

    /**
     * Attribune Name ========
     */
    /** RCPT_CHK_NUM */
    public static final String RCPT_CHK_NUM_J = "rcptChkNum";

    /**
     * ========
     */
    /** RGTN_STS_CD */
    public static final String RGTN_STS_CD = "RGTN_STS_CD";

    /** RGSTN_STS_CD */
    public static final String RGSTN_STS_CD_J = "rgtnStsCd";

    /**
     * Attribune Name ========
     */
    /** EDI_RCV_CUST_NM */
    public static final String EDI_RCV_CUST_NM_J = "ediRcvCustNm";

    /** LAST_USE_DT */
    public static final String LAST_USE_DT_J = "lastUseDt";

    /**
     * Attribune Name ========
     */
    /** CCY_CD */
    public static final String CCY_CD_J = "ccyCd";

    /**
     * ========
     */
    /** AR_ADJ_TRX_CD */
    public static final String AR_ADJ_TRX_CD_J = "arAdjTrxCd";

    /** AR_ADJ_TRX_RSN_CD */
    public static final String AR_ADJ_TRX_RSN_CD_J = "arAdjTrxRsnCd";

    /**
     * ======== 'CUST_CR_PRFL' or 'Other Entity' Java Attribune Name ========
     */
    /** CR_MGR_PSN_CD */
    public static final String CR_MGR_PSN_CD = "CR_MGR_PSN_CD";

    /** CR_LIMIT_AMT */
    public static final String CR_LIMIT_AMT = "CR_LIMIT_AMT";

    /** CR_BAL_AMT */
    public static final String CR_BAL_AMT = "CR_BAL_AMT";

    /** IN_PROC_AMT */
    public static final String IN_PROC_AMT = "IN_PROC_AMT";

    /** lastPmtRcptDt */
    public static final String LAST_PMT_RCPT_DT_J = "lastPmtRcptDt";

    /** LAST_PMT_RCPT_DT */
    public static final String LAST_PMT_RCPT_DT = "LAST_PMT_RCPT_DT";

    /**
     * ========
     */
    /** TRX_RSN_CD */
    public static final String TRX_RSN_CD_J = "trxRsnCd";

    /**
     * ======== 'AR_CR_BAL_S80' or 'Other Entity' Java Attribune Name ========
     */
    /** SEND_CPLT_FLG */
    public static final String SEND_CPLT_FLG_J = "sendCpltFlg";

    /**
     * ======== 'AR_APPLY_INTFC_WRK' or 'Other Entity' Java Attribune Name ========
     */
    /** INTFC_ID */
    public static final String INTFC_ID_J = "intfcId";

    /** UPLD_CSV_ID */
    public static final String UPLD_CSV_ID_J = "upldCsvId";

    /** PROC_STS_CD_J */
    public static final String PROC_STS_CD_J = "procStsCd";

    /** AR_ADJ_NUM */
    public static final String AR_ADJ_NUM_J = "arAdjNum";

    /**
     * ======== 'S21_ORG' or 'Other Entity' Java Attribune Name ========
     */

    /** TOC_CD */
    public static final String TOC_CD_J = "tocCd";

    /** RGTN_STS_CD */
    public static final String RGTN_STS_CD_J = "rgtnStsCd";

    /**
     * ======== 'Interface Table' Java Attribune Name ========
     */

    /** INTERFACE_ID */
    public static final String INTERFACE_ID_J = "interfaceId";

    /** TRANSACTION_ID */
    public static final String TRANSACTION_ID_J = "transactionId";

    /** INTERFACE_ID */
    public static final String INTERFACE_ID = "INTERFACE_ID";

    /** TRANSACTION_ID */
    public static final String TRANSACTION_ID = "TRANSACTION_ID";

    /**
     * ======== 'AR_INV_INFO Table' Java Attribune Name ========
     */

    /** GRP_INV_NUM */
    public static final String GRP_INV_NUM_J = "grpInvNum";

    /** CR_CARD_ORD_NUM */
    public static final String CR_CARD_ORD_NUM_J = "crCardOrdNum";

    /**
     * ======== 'AR_EDI_SEND_BANK Table' Java Attribune Name ========
     */

    /** AR_EDI_SEND_BANK_CD */
    public static final String AR_EDI_SEND_BANK_CD_J = "arEdiSendBankCd";

    /** SO_NUM */
    public static final String SO_NUM_J = "soNum";

    /** SET_AR_ADJ_NUM */
    public static final String SET_AR_ADJ_NUM_J = "setArAdjNum";

    /** SET_AR_ADJ_NUM */
    public static final String SET_AR_ADJ_NUM = "SET_AR_ADJ_NUM";

    /** AR_CUST_REF_NUM */
    public static final String AR_CUST_REF_NUM_J = "arCustRefNum";

    /**
     * ======== 'AR_TOL_LVL Table' Java Attribune Name ========
     */

    /** PER_FROM_DT */
    public static final String PER_FROM_DT_J = "perFromDt";

    /** PER_THRU_DT */
    public static final String PER_THRU_DT_J = "perThruDt";

    /**
     * ======== 'AR_TRX_BAL_BY_PROD Table' Java Attribune Name ========
     */

    /** AR_TRX_SUB_NUM */
    public static final String AR_TRX_SUB_NUM = "AR_TRX_SUB_NUM";

    /** DEAL_NET_SLS_SMRY_AMT */
    public static final String DEAL_NET_SLS_SMRY_AMT = "DEAL_NET_SLS_SMRY_AMT";

    /** FUNC_NET_SLS_SMRY_AMT */
    public static final String FUNC_NET_SLS_SMRY_AMT = "FUNC_NET_SLS_SMRY_AMT";

    /** DEAL_PROD_PCT */
    public static final String DEAL_PROD_PCT = "DEAL_PROD_PCT";

    /** FUNC_PROD_PCT */
    public static final String FUNC_PROD_PCT = "FUNC_PROD_PCT";

    /** DEAL_DIFF_PCT */
    public static final String DEAL_DIFF_PCT = "DEAL_DIFF_PCT";

    /** FUNC_DIFF_PCT */
    public static final String FUNC_DIFF_PCT = "FUNC_DIFF_PCT";

    /**
     * ======== 'MDSE Table' Java Attribune Name ========
     */

    /** MDSE */
    public static final String MDSE_J = "mdse";

    /** MDSE_CD */
    public static final String MDSE_CD_J = "mdseCd";

    /**
     * ======== 'AR_TRX_BAL_BY_PROD Table' Java Attribune Name ========
     */

    /** ACCT_YR_MTH */
    public static final String ACCT_YR_MTH_J = "acctYrMth";

    /**
     * 'AR_AGING_CALC_PER' or 'Other Entity' Java Attribune Name
     */
    /** USE_SEG_CD */
    public static final String USE_SEG_CD_J = "useSegCd";

    /**
     * 'AR_CUST_AGING' or 'Other Entity' Java Attribune Name
     */
    /** DEAL_RMNG_BAL_GRS_AMT */
    public static final String DEAL_RMNG_BAL_GRS_AMT_J = "dealRmngBalGrsAmt";

    /** FUNC_RMNG_BAL_GRS_AMT */
    public static final String FUNC_RMNG_BAL_GRS_AMT_J = "funcRmngBalGrsAmt";

    /**
     * 'AR_TRX_BAL_BY_PROD' or 'Other Entity' Java Attribune Name
     */
    /** AGING_TRX_TP_CD */
    public static final String AGING_TRX_TP_CD_J = "agingTrxTpCd";

    /** EZCANCELFLAG */
    public static final String EZCANCELFLAG_J = "ezCancelFlag";

    /**
     * ======== 'AR_AGING_LINK' or 'Other Entity' Java Attribune Name ========
     */
    /** BILL_TO_LOC_NM */
    public static final String BILL_TO_LOC_NM = "BILL_TO_LOC_NM";

    /** billToLocNm */
    public static final String BILL_TO_LOC_NM_J = "billToLocNm";

    /** ADD_LOC_NM */
    public static final String ADD_LOC_NM = "ADD_LOC_NM";

    /** addLocNm */
    public static final String ADD_LOC_NM_J = "addLocNm";

    /** CR_MGR_PSN_NM */
    public static final String CR_MGR_PSN_NM = "CR_MGR_PSN_NM";

    /** crMgrFullPsnNm */
    public static final String CR_MGR_PSN_NM_J = "crMgrFullPsnNm";

    /** CUST_CMNT_TXT */
    public static final String CUST_CMNT_TXT = "CUST_CMNT_TXT";

    /** custCmntTxt */
    public static final String CUST_CMNT_TXT_J = "custCmntTxt";

    /**
     * ======== 'AR_AGING_MLY_HIST' or 'Other Entity' Java Attribune Name ========
     */
    /** arBalMlyHistYrMth */
    public static final String AR_BAL_MLY_HIST_YR_MTH_J = "arBalMlyHistYrMth";

    /** AR_BAL_MLY_HIST_YR_MTH */
    public static final String AR_BAL_MLY_HIST_YR_MTH = "AR_BAL_MLY_HIST_YR_MTH";

    /** BILL_TO_ADDL_LOC_NM */
    public static final String BILL_TO_ADDL_LOC_NM = "BILL_TO_ADDL_LOC_NM";

    /** billToaddLocNm */
    public static final String BILL_TO_ADDL_LOC_NM_J = "billToaddLocNm";

    /** CR_MGR_FULL_PSN_NM */
    public static final String CR_MGR_FULL_PSN_NM = "CR_MGR_FULL_PSN_NM";

    /** crmgrfullpsnnm */
    public static final String CR_MGR_FULL_PSN_NM_J = "crMgrFullPsnNm";

    /** TRX_AGING_AMT */
    public static final String TRX_AGING_AMT = "TRX_AGING_AMT";

    /** DEAL_TRX_AGING_AMT_01 */
    public static final String DEAL_TRX_AGING_AMT_01 = "DEAL_TRX_AGING_AMT_01";

    /** DEAL_TRX_AGING_AMT_02 */
    public static final String DEAL_TRX_AGING_AMT_02 = "DEAL_TRX_AGING_AMT_02";

    /** DEAL_TRX_AGING_AMT_03 */
    public static final String DEAL_TRX_AGING_AMT_03 = "DEAL_TRX_AGING_AMT_03";

    /** DEAL_TRX_AGING_AMT_04 */
    public static final String DEAL_TRX_AGING_AMT_04 = "DEAL_TRX_AGING_AMT_04";

    /** DEAL_TRX_AGING_AMT_05 */
    public static final String DEAL_TRX_AGING_AMT_05 = "DEAL_TRX_AGING_AMT_05";

    /** DEAL_TRX_AGING_AMT_06 */
    public static final String DEAL_TRX_AGING_AMT_06 = "DEAL_TRX_AGING_AMT_06";

    /** DEAL_TRX_AGING_AMT_07 */
    public static final String DEAL_TRX_AGING_AMT_07 = "DEAL_TRX_AGING_AMT_07";

    /** CM_AGING_AMT */
    public static final String CM_AGING_AMT = "CM_AGING_AMT";

    /** DEAL_CM_AGING_AMT_01 */
    public static final String DEAL_CM_AGING_AMT_01 = "DEAL_CM_AGING_AMT_01";

    /** DEAL_CM_AGING_AMT_02 */
    public static final String DEAL_CM_AGING_AMT_02 = "DEAL_CM_AGING_AMT_02";

    /** DEAL_CM_AGING_AMT_03 */
    public static final String DEAL_CM_AGING_AMT_03 = "DEAL_CM_AGING_AMT_03";

    /** DEAL_CM_AGING_AMT_04 */
    public static final String DEAL_CM_AGING_AMT_04 = "DEAL_CM_AGING_AMT_04";

    /** DEAL_CM_AGING_AMT_05 */
    public static final String DEAL_CM_AGING_AMT_05 = "DEAL_CM_AGING_AMT_05";

    /** DEAL_CM_AGING_AMT_06 */
    public static final String DEAL_CM_AGING_AMT_06 = "DEAL_CM_AGING_AMT_06";

    /** DEAL_CM_AGING_AMT_07 */
    public static final String DEAL_CM_AGING_AMT_07 = "DEAL_CM_AGING_AMT_07";

    /** ACCT_AGING_AMT */
    public static final String ACCT_AGING_AMT = "ACCT_AGING_AMT";

    /** DEAL_ACCT_AGING_AMT_01 */
    public static final String DEAL_ACCT_AGING_AMT_01 = "DEAL_ACCT_AGING_AMT_01";

    /** DEAL_ACCT_AGING_AMT_02 */
    public static final String DEAL_ACCT_AGING_AMT_02 = "DEAL_ACCT_AGING_AMT_02";

    /** DEAL_ACCT_AGING_AMT_03 */
    public static final String DEAL_ACCT_AGING_AMT_03 = "DEAL_ACCT_AGING_AMT_03";

    /** DEAL_ACCT_AGING_AMT_04 */
    public static final String DEAL_ACCT_AGING_AMT_04 = "DEAL_ACCT_AGING_AMT_04";

    /** DEAL_ACCT_AGING_AMT_05 */
    public static final String DEAL_ACCT_AGING_AMT_05 = "DEAL_ACCT_AGING_AMT_05";

    /** DEAL_ACCT_AGING_AMT_06 */
    public static final String DEAL_ACCT_AGING_AMT_06 = "DEAL_ACCT_AGING_AMT_06";

    /** DEAL_ACCT_AGING_AMT_07 */
    public static final String DEAL_ACCT_AGING_AMT_07 = "DEAL_ACCT_AGING_AMT_07";

    /** DEDCT_AGING_AMT */
    public static final String DEDCT_AGING_AMT = "DEDCT_AGING_AMT";

    /** DEAL_DEDCT_AGING_AMT_01 */
    public static final String DEAL_DEDCT_AGING_AMT_01 = "DEAL_DEDCT_AGING_AMT_01";

    /** DEAL_DEDCT_AGING_AMT_02 */
    public static final String DEAL_DEDCT_AGING_AMT_02 = "DEAL_DEDCT_AGING_AMT_02";

    /** DEAL_DEDCT_AGING_AMT_03 */
    public static final String DEAL_DEDCT_AGING_AMT_03 = "DEAL_DEDCT_AGING_AMT_03";

    /** DEAL_DEDCT_AGING_AMT_04 */
    public static final String DEAL_DEDCT_AGING_AMT_04 = "DEAL_DEDCT_AGING_AMT_04";

    /** DEAL_DEDCT_AGING_AMT_05 */
    public static final String DEAL_DEDCT_AGING_AMT_05 = "DEAL_DEDCT_AGING_AMT_05";

    /** DEAL_DEDCT_AGING_AMT_06 */
    public static final String DEAL_DEDCT_AGING_AMT_06 = "DEAL_DEDCT_AGING_AMT_06";

    /** DEAL_DEDCT_AGING_AMT_07 */
    public static final String DEAL_DEDCT_AGING_AMT_07 = "DEAL_DEDCT_AGING_AMT_07";

    /** RCPT_AGING_AMT */
    public static final String RCPT_AGING_AMT = "RCPT_AGING_AMT";

    /** DEAL_RCPT_AGING_AMT_01 */
    public static final String DEAL_RCPT_AGING_AMT_01 = "DEAL_RCPT_AGING_AMT_01";

    /** DEAL_RCPT_AGING_AMT_02 */
    public static final String DEAL_RCPT_AGING_AMT_02 = "DEAL_RCPT_AGING_AMT_02";

    /** DEAL_RCPT_AGING_AMT_03 */
    public static final String DEAL_RCPT_AGING_AMT_03 = "DEAL_RCPT_AGING_AMT_03";

    /** DEAL_RCPT_AGING_AMT_04 */
    public static final String DEAL_RCPT_AGING_AMT_04 = "DEAL_RCPT_AGING_AMT_04";

    /** DEAL_RCPT_AGING_AMT_05 */
    public static final String DEAL_RCPT_AGING_AMT_05 = "DEAL_RCPT_AGING_AMT_05";

    /** DEAL_RCPT_AGING_AMT_06 */
    public static final String DEAL_RCPT_AGING_AMT_06 = "DEAL_RCPT_AGING_AMT_06";

    /** DEAL_RCPT_AGING_AMT_07 */
    public static final String DEAL_RCPT_AGING_AMT_07 = "DEAL_RCPT_AGING_AMT_07";

    /** ==='AR_PROD_CD_CONV' or 'Other Entity' Attribune Name=== */
    /** AR_PROD_CD_CONV_CD */
    public static final String AR_PROD_CD_CONV_CD = "AR_PROD_CD_CONV_CD";

    /** AR_PROD_CD_CONV_NM */
    public static final String AR_PROD_CD_CONV_NM = "AR_PROD_CD_CONV_NM";

    /** SEGMENT_ID */
    public static final String SEGMENT_ID = "SEGMENT_ID";

    /** GL_DT */
    public static final String GL_DT_J = "glDt";

    /** ar_sls_eom_amt */
    public static final String AR_BAL_EOM_AMT = "AR_BAL_EOM_AMT";

    /** ar_sls_eom_amt */
    public static final String AR_BAL_EOM_AMT_J = "arBalEomAmt";

    /** ar_sls_eom_amt */
    public static final String AR_SLS_EOM_AMT = "AR_SLS_EOM_AMT";

    /** ar_sls_eom_amt */
    public static final String AR_SLS_EOM_AMT_J = "arSlsEomAmt";

    /** ==='AR_INV_RCV_S80_SAVE_WRK'=== */
    /** AR_INV_RCV_S80_SAVE_WRK_PK */
    public static final String AR_INV_RCV_S80_SAVE_WRK_PK = "AR_INV_RCV_S80_SAVE_WRK_PK";

    /** LGCY_REC_ID */
    public static final String LGCY_REC_ID = "LGCY_REC_ID";

    /** LGCY_TRX_TP_CD */
    public static final String LGCY_TRX_TP_CD = "LGCY_TRX_TP_CD";

    /** LGCY_BR_CD */
    public static final String LGCY_BR_CD = "LGCY_BR_CD";

    /** LGCY_DEPT_CD */
    public static final String LGCY_DEPT_CD = "LGCY_DEPT_CD";

    /** INV_LGCY_DT_TXT */
    public static final String INV_LGCY_DT_TXT = "INV_LGCY_DT_TXT";

    /** INV_DUE_LGCY_DT_TXT */
    public static final String INV_DUE_LGCY_DT_TXT = "INV_DUE_LGCY_DT_TXT";

    /** LGCY_TRX_CD */
    public static final String LGCY_TRX_CD = "LGCY_TRX_CD";

    /** LGCY_IND_CD */
    public static final String LGCY_IND_CD = "LGCY_IND_CD";

    /** DEAL_TOT_INV_TAX_AMT */
    public static final String DEAL_TOT_INV_TAX_AMT = "DEAL_TOT_INV_TAX_AMT";

    /** DEAL_TOT_INV_FRT_AMT */
    public static final String DEAL_TOT_INV_FRT_AMT = "DEAL_TOT_INV_FRT_AMT";

    /** DEAL_TOT_INV_DISC_AMT */
    public static final String DEAL_TOT_INV_DISC_AMT = "DEAL_TOT_INV_DISC_AMT";

    /** DEAL_TOT_INV_SHIP_AMT */
    public static final String DEAL_TOT_INV_SHIP_AMT = "DEAL_TOT_INV_SHIP_AMT";

    /** DEAL_TOT_INV_NET_AMT */
    public static final String DEAL_TOT_INV_NET_AMT = "DEAL_TOT_INV_NET_AMT";

    /** CASH_DISC_LIMIT_LGCY_DT_TXT_01 */
    public static final String CASH_DISC_LIMIT_LGCY_DT_TXT_01 = "CASH_DISC_LIMIT_LGCY_DT_TXT_01";

    /** CASH_DISC_PCT_01 */
    public static final String CASH_DISC_PCT_01 = "CASH_DISC_PCT_01";

    /** DEAL_CASH_DISC_AMT_01 */
    public static final String DEAL_CASH_DISC_AMT_01 = "DEAL_CASH_DISC_AMT_01";

    /** CASH_DISC_LIMIT_LGCY_DT_TXT_02 */
    public static final String CASH_DISC_LIMIT_LGCY_DT_TXT_02 = "CASH_DISC_LIMIT_LGCY_DT_TXT_02";

    /** CASH_DISC_PCT_02 */
    public static final String CASH_DISC_PCT_02 = "CASH_DISC_PCT_02";

    /** DEAL_CASH_DISC_AMT_02 */
    public static final String DEAL_CASH_DISC_AMT_02 = "DEAL_CASH_DISC_AMT_02";

    /** CASH_DISC_LIMIT_LGCY_DT_TXT_03 */
    public static final String CASH_DISC_LIMIT_LGCY_DT_TXT_03 = "CASH_DISC_LIMIT_LGCY_DT_TXT_03";

    /** CASH_DISC_PCT_03 */
    public static final String CASH_DISC_PCT_03 = "CASH_DISC_PCT_03";

    /** DEAL_CASH_DISC_AMT_03 */
    public static final String DEAL_CASH_DISC_AMT_03 = "DEAL_CASH_DISC_AMT_03";

    /** CASH_DISC_LIMIT_LGCY_DT_TXT_04 */
    public static final String CASH_DISC_LIMIT_LGCY_DT_TXT_04 = "CASH_DISC_LIMIT_LGCY_DT_TXT_04";

    /** CASH_DISC_PCT_04 */
    public static final String CASH_DISC_PCT_04 = "CASH_DISC_PCT_04";

    /** DEAL_CASH_DISC_AMT_04 */
    public static final String DEAL_CASH_DISC_AMT_04 = "DEAL_CASH_DISC_AMT_04";

    /** CASH_DISC_LIMIT_LGCY_DT_TXT_05 */
    public static final String CASH_DISC_LIMIT_LGCY_DT_TXT_05 = "CASH_DISC_LIMIT_LGCY_DT_TXT_05";

    /** CASH_DISC_PCT_05 */
    public static final String CASH_DISC_PCT_05 = "CASH_DISC_PCT_05";

    /** DEAL_CASH_DISC_AMT_05 */
    public static final String DEAL_CASH_DISC_AMT_05 = "DEAL_CASH_DISC_AMT_05";

    /** ORD_SUB_NUM */
    public static final String ORD_SUB_NUM = "ORD_SUB_NUM";

    /** EXP_ACCT_CD */
    public static final String EXP_ACCT_CD = "EXP_ACCT_CD";

    /** COA_CH_CD */
    public static final String COA_CH_CD = "COA_CH_CD";

    /** ISS_FIRST_2_DIGIT_YR */
    public static final String ISS_FIRST_2_DIGIT_YR = "ISS_FIRST_2_DIGIT_YR";

    /** CUST_ISS_PO_LGCY_DT_TXT */
    public static final String CUST_ISS_PO_LGCY_DT_TXT = "CUST_ISS_PO_LGCY_DT_TXT";

    /** SMRY_SFX_CD */
    public static final String SMRY_SFX_CD = "SMRY_SFX_CD";

    /** SPCL_DISC_AMT */
    public static final String SPCL_DISC_AMT = "SPCL_DISC_AMT";

    /** ORIG_ORD_NUM */
    public static final String ORIG_ORD_NUM = "ORIG_ORD_NUM";

    /** ISS_FIRST_2_DIGIT_YR_TXT */
    public static final String ISS_FIRST_2_DIGIT_YR_TXT = "ISS_FIRST_2_DIGIT_YR_TXT";

    /* NFCI0460 */
    /** FLD_MSG_ORDR_01_IF */
    public static final String FLD_MSG_ORDR_01_IF = "FLD_MSG_ORDR_01_IF";

    /** FLD_MSG_ORDR_02_IF */
    public static final String FLD_MSG_ORDR_02_IF = "FLD_MSG_ORDR_02_IF";

    /** FLD_MSG_ORDR_03_IF */
    public static final String FLD_MSG_ORDR_03_IF = "FLD_MSG_ORDR_03_IF";

    /** FLD_MSG_ORDR_04_IF */
    public static final String FLD_MSG_ORDR_04_IF = "FLD_MSG_ORDR_04_IF";

    /* loc_usg */
    /** LOC_ROLE_TP_CD */
    public static final String LOC_ROLE_TP_CD = "LOC_ROLE_TP_CD";

    /** locRoleTpCd */
    public static final String LOC_ROLE_TP_CD_J = "locRoleTpCd";

    /* NFCI0700_01 */
    /** LGCY_CUST_REF_12_DIGIT_NUM */
    public static final String LGCY_CUST_REF_12_DIGIT_NUM = "LGCY_CUST_REF_12_DIGIT_NUM";

    /** lgcyCustRef12DigitNum */
    public static final String LGCY_CUST_REF_12_DIGIT_NUM_J = "lgcyCustRef12DigitNum";

    /* CTAC_PSN */
    /** CTAC_PSN_PK */
    public static final String CTAC_PSN_PK = "CTAC_PSN_PK";

    /** ctacPsnPk */
    public static final String CTAC_PSN_PK_J = "ctacPsnPk";

    /** PTY_LOC_PK */
    public static final String PTY_LOC_PK = "PTY_LOC_PK";

    /** ptyLocPk */
    public static final String PTY_LOC_PK_J = "ptyLocPk";

    /** CTAC_PSN_FIRST_NM */
    public static final String CTAC_PSN_FIRST_NM = "CTAC_PSN_FIRST_NM";

    /** ctacPsnFirstNm */
    public static final String CTAC_PSN_FIRST_NM_J = "ctacPsnFirstNm";

    /** CTAC_PSN_MID_NM */
    public static final String CTAC_PSN_MID_NM = "CTAC_PSN_MID_NM";

    /** ctacPsnMidNm */
    public static final String CTAC_PSN_MID_NM_J = "ctacPsnMidNm";

    /** CTAC_PSN_LAST_NM */
    public static final String CTAC_PSN_LAST_NM = "CTAC_PSN_LAST_NM";

    /** ctacPsnLastNm */
    public static final String CTAC_PSN_LAST_NM_J = "ctacPsnLastNm";

    /** CTAC_PSN_EML_ADDR */
    public static final String CTAC_PSN_EML_ADDR = "CTAC_PSN_EML_ADDR";

    /** ctacPsnEmlAddr */
    public static final String CTAC_PSN_EML_ADDR_J = "ctacPsnEmlAddr";

    /** CTAC_PSN_EXTN_NUM */
    public static final String CTAC_PSN_EXTN_NUM = "CTAC_PSN_EXTN_NUM";

    /** ctacPsnExtnNum */
    public static final String CTAC_PSN_EXTN_NUM_J = "ctacPsnExtnNum";

    /** CTAC_PSN_PVT_FLG */
    public static final String CTAC_PSN_PVT_FLG = "CTAC_PSN_PVT_FLG";

    /** ctacPsnPvtFlg */
    public static final String CTAC_PSN_PVT_FLG_J = "ctacPsnPvtFlg";

    /** CTAC_PSN_PVT_LOGIN_USR_NM */
    public static final String CTAC_PSN_PVT_LOGIN_USR_NM = "CTAC_PSN_PVT_LOGIN_USR_NM";

    /** ctacPsnPvtLoginUsrNm */
    public static final String CTAC_PSN_PVT_LOGIN_USR_NM_J = "ctacPsnPvtLoginUsrNm";

    /** CTAC_PSN_SCR_TRGT_FLG */
    public static final String CTAC_PSN_SCR_TRGT_FLG = "CTAC_PSN_SCR_TRGT_FLG";

    /** ctacPsnScrTrgtFlg */
    public static final String CTAC_PSN_SCR_TRGT_FLG_J = "ctacPsnScrTrgtFlg";

    /** CTAC_PSN_RCV_ML_FLG */
    public static final String CTAC_PSN_RCV_ML_FLG = "CTAC_PSN_RCV_ML_FLG";

    /** ctacPsnRcvMlFlg */
    public static final String CTAC_PSN_RCV_ML_FLG_J = "ctacPsnRcvMlFlg";

    /** CTAC_TP_CD */
    public static final String CTAC_TP_CD = "CTAC_TP_CD";

    /** ctacTpCd */
    public static final String CTAC_TP_CD_J = "ctacTpCd";

    /** CTAC_PSN_TEL_NUM */
    public static final String CTAC_PSN_TEL_NUM = "CTAC_PSN_TEL_NUM";

    /** ctacPsnTelNum */
    public static final String CTAC_PSN_TEL_NUM_J = "ctacPsnTelNum";

    /** CTAC_PSN_CELL_PHO_NUM */
    public static final String CTAC_PSN_CELL_PHO_NUM = "CTAC_PSN_CELL_PHO_NUM";

    /** ctacPsnCellPhoNum */
    public static final String CTAC_PSN_CELL_PHO_NUM_J = "ctacPsnCellPhoNum";

    /** CTAC_PSN_FAX_NUM */
    public static final String CTAC_PSN_FAX_NUM = "CTAC_PSN_FAX_NUM";

    /** ctacPsnFaxNum */
    public static final String CTAC_PSN_FAX_NUM_J = "ctacPsnFaxNum";

    /** CTAC_PSN_CMNT_TXT */
    public static final String CTAC_PSN_CMNT_TXT = "CTAC_PSN_CMNT_TXT";

    /** ctacPsnCmntTxt */
    public static final String CTAC_PSN_CMNT_TXT_J = "ctacPsnCmntTxt";

    /** MSTR_CHNG_DT */
    public static final String MSTR_CHNG_DT = "MSTR_CHNG_DT";

    /** mstrChngDt */
    public static final String MSTR_CHNG_DT_J = "mstrChngDt";

    /** ORIG_GRS_CNT */
    public static final String ORIG_GRS_CNT = "ORIG_GRS_CNT";

    /** RCV_HIST_SQ_PK */
    public static final String RCV_HIST_SQ_PK = "RCV_HIST_SQ_PK";

    /** rcvHistSqPk */
    public static final String RCV_HIST_SQ_PK_J = "rcvHistSqPk";

    /** RCPT_DEP_CHK_FLG */
    public static final String RCPT_DEP_CHK_FLG = "RCPT_DEP_CHK_FLG";

    /** rcptDepChkFlg */
    public static final String RCPT_DEP_CHK_FLG_J = "rcptDepChkFlg";

    // ///////////////////////////////////////////////////////////////////////
    // ADDITIONAL FIELD FOR R3
    // ///////////////////////////////////////////////////////////////////////
    /**
     * additional NFCB2720
     */
    /** DEF_TRX_RSN_CD_J * */
    public static final String DEF_TRX_RSN_CD_J = "defRsnCd";

    /** AR_RCPT_TRX_TP_00_J * */
    public static final String AR_RCPT_TRX_TP_00_J = "arRcptTrxTp00";

    /** AR_RCPT_TRX_TP_01_J * */
    public static final String AR_RCPT_TRX_TP_01_J = "arRcptTrxTp01";

    /** ISTL_PMT_TERM_CD_J */
    public static final String ISTL_PMT_TERM_CD_J = "istlPmtTermCd";

    /** FUNC_FIRST_EXPT_CHRG_AMT * */
    public static final String FUNC_FIRST_EXPT_CHRG_AMT = "FUNC_FIRST_EXPT_CHRG_AMT";

    /** DEAL_FIRST_EXPT_CHRG_AMT * */
    public static final String DEAL_FIRST_EXPT_CHRG_AMT = "DEAL_FIRST_EXPT_CHRG_AMT";

    /** DEAL_CCY_CD_RCPT * */
    public static final String DEAL_CCY_CD_RCPT = "DEAL_CCY_CD_RCPT";

    /** FUNC_CCY_CD_RCPT * */
    public static final String FUNC_CCY_CD_RCPT = "FUNC_CCY_CD_RCPT";

    /** COA_PROD_CD_RCPT * */
    public static final String COA_PROD_CD_RCPT = "COA_PROD_CD_RCPT";

    /** TOC_CD_RCPT * */
    public static final String TOC_CD_RCPT = "TOC_CD_RCPT";

    /** DEAL_CCY_CD_TRX * */
    public static final String DEAL_CCY_CD_TRX = "DEAL_CCY_CD_TRX";

    /** FUNC_CCY_CD_TRX * */
    public static final String FUNC_CCY_CD_TRX = "FUNC_CCY_CD_TRX";

    /** COA_PROD_CD_TRX * */
    public static final String COA_PROD_CD_TRX = "COA_PROD_CD_TRX";

    /** TOC_CD_TRX * */
    public static final String TOC_CD_TRX = "TOC_CD_TRX";

    /** FGN_EXCH_LOSS_GAIN_AMT * */
    public static final String FGN_EXCH_LOSS_GAIN_AMT = "FGN_EXCH_LOSS_GAIN_AMT";

    /** AR_TRX_TP_CD_TRX2 * */
    public static final String AR_TRX_TP_CD_TRX2 = "AR_TRX_TP_CD_TRX2";

    /** AR_TRX_TP_CD_TRX * */
    public static final String AR_TRX_TP_CD_TRX = "AR_TRX_TP_CD_TRX";

    /** DEAL_CCY_CD_AR * */
    public static final String DEAL_CCY_CD_AR = "DEAL_CCY_CD_AR";

    /** FUNC_CCY_CD_AR * */
    public static final String FUNC_CCY_CD_AR = "FUNC_CCY_CD_AR";

    /** TOC_CD_ADJ * */
    public static final String TOC_CD_ADJ = "TOC_CD_ADJ";

    /** TOC_CD_TRX2 * */
    public static final String TOC_CD_TRX2 = "TOC_CD_TRX2";

    /** COA_PROD_CD_ADJ * */
    public static final String COA_PROD_CD_ADJ = "COA_PROD_CD_ADJ";

    /** COA_PROD_CD_TRX2 * */
    public static final String COA_PROD_CD_TRX2 = "COA_PROD_CD_TRX2";

    /** AR_TRX_TP_CD_TRX_CR * */
    public static final String AR_TRX_TP_CD_TRX_CR = "AR_TRX_TP_CD_TRX_CR";

    /** AR_CASH_APP_TRX_RSN_CD * */
    public static final String AR_CASH_APP_TRX_RSN_CD = "AR_CASH_APP_TRX_RSN_CD";

    /** AR_APPLY_TP_CD_RVL_J * */
    public static final String AR_APPLY_TP_CD_RVL_J = "arApplyTpCdRvl";

    /** AR_APPLY_TP_CD_CRM_J * */
    public static final String AR_APPLY_TP_CD_CRM_J = "arApplyTpCdCrm";

    /** CARR_VND_CD */
    public static final String CARR_CD = "CARR_CD";

    /** CARR_VND_LOC_NM */
    public static final String CARR_NM = "CARR_NM";

    /** COA_CMPY_CD */
    public static final String COA_CMPY_CD = "COA_CMPY_CD";

    /** COA_AFFL_CD */
    public static final String COA_AFFL_CD = "COA_AFFL_CD";

    /** COA_AFFL_CD_J */
    public static final String COA_AFFL_CD_J = "coaAfflCd";

    /** COA_BR_CD */
    public static final String COA_BR_CD = "COA_BR_CD";

    /** COA_CC_CD */
    public static final String COA_CC_CD = "COA_CC_CD";

    /** COA_EXTN_CD */
    public static final String COA_EXTN_CD = "COA_EXTN_CD";

    /** UNIVS_PRMO_ID */
    public static final String UNIVS_PRMO_ID = "UNIVS_PRMO_ID";

    /** PRC_CATG_CD */
    public static final String PRC_CATG_CD = "PRC_CATG_CD";

    /** INV_PRINT_STS_CD */
    public static final String INV_PRINT_STS_CD = "INV_PRINT_STS_CD";

    /** INV_ML_SEND_STS_CD */
    public static final String INV_ML_SEND_STS_CD = "INV_ML_SEND_STS_CD";

    /** INV_EDI_SEND_STS_CD */
    public static final String INV_EDI_SEND_STS_CD = "INV_EDI_SEND_STS_CD";

    /** INV_FAX_SEND_STS_CD */
    public static final String INV_FAX_SEND_STS_CD = "INV_FAX_SEND_STS_CD";

    /** INV_EML_SEND_STS_CD */
    public static final String INV_EML_SEND_STS_CD = "INV_EML_SEND_STS_CD";

    /** INV_RCPNT_FAX_NUM */
    public static final String INV_RCPNT_FAX_NUM = "INV_RCPNT_FAX_NUM";

    /** AR_EXP_PMT_METH_CD */
    public static final String AR_EXP_PMT_METH_CD = "AR_EXP_PMT_METH_CD";

    /** AR_EXP_PMT_METH_NM */
    public static final String AR_EXP_PMT_METH_NM = "AR_EXP_PMT_METH_NM";

    /** AR_EXP_PMT_COND_CD */
    public static final String AR_EXP_PMT_COND_CD = "AR_EXP_PMT_COND_CD";

    /** AR_EXP_PMT_COND_NM */
    public static final String AR_EXP_PMT_COND_NM = "AR_EXP_PMT_COND_NM";

    /** INV_TOT_DEAL_INS_AMT */
    public static final String INV_TOT_DEAL_INS_AMT = "INV_TOT_DEAL_INS_AMT";

    /** INV_TOT_FUNC_INS_AMT */
    public static final String INV_TOT_FUNC_INS_AMT = "INV_TOT_FUNC_INS_AMT";

    /** INV_PMT_METH_NM */
    public static final String INV_PMT_METH_NM = "INV_PMT_METH_NM";

    /** INV_PMT_COND_NM */
    public static final String INV_PMT_COND_NM = "INV_PMT_COND_NM";

    /** ROSS_ORD_TP_CD */
    public static final String ROSS_ORD_TP_CD = "ROSS_ORD_TP_CD";

    /** HIST_CRAT_CPLT_FLG */
    public static final String HIST_CRAT_CPLT_FLG = "HIST_CRAT_CPLT_FLG";

    /** AR_AFT_RCPT_NUM */
    public static final String AR_AFT_RCPT_NUM = "AR_AFT_RCPT_NUM";

    /** AR_RELN_RCPT_NUM */
    public static final String AR_RELN_RCPT_NUM = "AR_RELN_RCPT_NUM";

    /** DEAL_NET_RCPT_AMT */
    public static final String DEAL_NET_RCPT_AMT = "DEAL_NET_RCPT_AMT";

    /** FUNC_NET_RCPT_AMT */
    public static final String FUNC_NET_RCPT_AMT = "FUNC_NET_RCPT_AMT";

    /** DEAL_SCD_EXPT_CHRG_AMT */
    public static final String DEAL_SCD_EXPT_CHRG_AMT = "DEAL_SCD_EXPT_CHRG_AMT";

    /** FUNC_SCD_EXPT_CHRG_AMT */
    public static final String FUNC_SCD_EXPT_CHRG_AMT = "FUNC_SCD_EXPT_CHRG_AMT";

    /** CPO_ORD_NUM_DTL */
    public static final String CPO_ORD_NUM_DTL = "CPO_ORD_NUM_DTL";

    /** ORIG_CPO_ORD_NUM_DTL */
    public static final String ORIG_CPO_ORD_NUM_DTL = "ORIG_CPO_ORD_NUM_DTL";

    /** HIST_CRAT_CPLT_FLG_DTL */
    public static final String HIST_CRAT_CPLT_FLG_DTL = "HIST_CRAT_CPLT_FLG_DTL";

    /** PMT_TERM_CASH_DISC_DESC_TXT */
    public static final String PMT_TERM_CASH_DISC_DESC_TXT = "PMT_TERM_CASH_DISC_DESC_TXT";

    /** PMTC_SUBMT_DT_TXT */
    public static final String PMTC_SUBMT_DT_TXT = "PMTC_SUBMT_DT_TXT";

    /** PMTC_REC_NUM */
    public static final String PMTC_REC_NUM = "PMTC_REC_NUM";

    /** PMTC_ENTY_NUM */
    public static final String PMTC_ENTY_NUM = "PMTC_ENTY_NUM";

    /** PMTC_MDSE_ORD_NUM */
    public static final String PMTC_MDSE_ORD_NUM = "PMTC_MDSE_ORD_NUM";

    /** PMTC_RCPT_AMT_TXT */
    public static final String PMTC_RCPT_AMT_TXT = "PMTC_RCPT_AMT_TXT";

    /** ISTL_PMT_TERM_FLG */
    public static final String ISTL_PMT_TERM_FLG = "ISTL_PMT_TERM_FLG";

    // for North America(CSA) 2015/04/02 Add Start
    /** AR_BAT_RCPT_NM */
    public static final String AR_BAT_RCPT_NM = "AR_BAT_RCPT_NM";

    /** CUST_RCPT_NUM */
    public static final String CUST_RCPT_NUM = "CUST_RCPT_NUM";

    /** AR_RCPT_SRC_CD */
    public static final String AR_RCPT_SRC_CD = "AR_RCPT_SRC_CD";

    /** REM_DS_BANK_ACCT_PK */
    public static final String REM_DS_BANK_ACCT_PK = "REM_DS_BANK_ACCT_PK";

    /** CUST_DS_BANK_ACCT_PK */
    public static final String CUST_DS_BANK_ACCT_PK = "CUST_DS_BANK_ACCT_PK";

    /** CUST_ACCT_REF_NUM */
    public static final String CUST_ACCT_REF_NUM = "CUST_ACCT_REF_NUM";

    /** CUST_INV_NUM */
    public static final String CUST_INV_NUM = "CUST_INV_NUM";

    /** INV_CONSL_TP_CD */
    public static final String INV_CONSL_TP_CD = "INV_CONSL_TP_CD";

    /** AR_CUST_ID_STS_CD */
    public static final String AR_CUST_ID_STS_CD = "AR_CUST_ID_STS_CD";

    /** AR_LOCK_BOX_FILE_NM */
    public static final String AR_LOCK_BOX_FILE_NM = "AR_LOCK_BOX_FILE_NM";

    /** AR_LOCK_BOX_CD */
    public static final String AR_LOCK_BOX_CD = "AR_LOCK_BOX_CD";

    /** BAT_RCPT_REC_CNT */
    public static final String BAT_RCPT_REC_CNT = "BAT_RCPT_REC_CNT";

    /** BAT_RCPT_TOT_AMT */
    public static final String BAT_RCPT_TOT_AMT = "BAT_RCPT_TOT_AMT";
    // for North America(CSA) 2015/04/02 Add END

    // START 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]
    /** UPPER_CUST_ISS_PO_NUM */
    public static final String UPPER_CUST_ISS_PO_NUM = "UPPER_CUST_ISS_PO_NUM";

    /** BILL_TO_CUST_ACCT_CD */
    public static final String BILL_TO_CUST_ACCT_CD = "BILL_TO_CUST_ACCT_CD";

    /** SRC_SYS_DOC_NUM */
    public static final String SRC_SYS_DOC_NUM = "SRC_SYS_DOC_NUM";

    /** SVC_INV_PK */
    public static final String SVC_INV_PK = "SVC_INV_PK";

    /** DS_INV_TP_CD */
    public static final String DS_INV_TP_CD = "DS_INV_TP_CD";

    /** CUST_CARE_TKT_NUM */
    public static final String CUST_CARE_TKT_NUM = "CUST_CARE_TKT_NUM";

    /** AR_AUTO_PURGE_OFS_FLG */
    public static final String AR_AUTO_PURGE_OFS_FLG = "AR_AUTO_PURGE_OFS_FLG";

    /** CR_REBIL_RSN_CATG_CD */
    public static final String CR_REBIL_RSN_CATG_CD = "CR_REBIL_RSN_CATG_CD";

    /** AR_TRX_BILL_FROM_DT */
    public static final String AR_TRX_BILL_FROM_DT = "AR_TRX_BILL_FROM_DT";

    /** AR_TRX_BILL_THRU_DT */
    public static final String AR_TRX_BILL_THRU_DT = "AR_TRX_BILL_THRU_DT";
    // END 2016/03/25 T.Tsuchida [S21 NA Unit Test #164,MOD]

    /** LOC_NUM */
    public static final String LOC_NUM = "LOC_NUM";

    // START 2018/06/26 Y.Matsui [QC#26788,MOD]
    /** LOC_NUM */
    public static final String CR_CARD_APVL_CD = "CR_CARD_APVL_CD";
    // END   2018/06/26 Y.Matsui [QC#26788,MOD]
    // START 2018/07/27 E.Kameishi [QC#27419,ADD]
    /** CR_CARD_LAST_DIGIT_NUM */
    public static final String CR_CARD_LAST_DIGIT_NUM = "CR_CARD_LAST_DIGIT_NUM";
    // END 2018/07/27 E.Kameishi [QC#27419,ADD]
}
