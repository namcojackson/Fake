/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB117001.constant;

/**
 *<pre>
 * Customer Dimension Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/28/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NMAB117001Constant {

    /** fetchSize */
    public static final int FETCH_SIZE = 1000;

    /** commitCount */
    public static final int DEFAULT_COMMIT_COUNT = 1000;

    /** [@] has no value. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Failed to insert "@". */
    public static final String ZZZM9012E = "ZZZM9012E";

    /** Failed to delete "@". */
    public static final String ZZZM9014E = "ZZZM9014E";

    /** message Item: GlobalCompanyCode. */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Batch Process Date. */
    public static final String MSG_ITEM_BATCH_PROC_DATE = "Batch Process Date";

    /** SSM set Key Name: RGTN_STS_CD */
    public static final String KEY_RGTN_STS_CD = "rgtnStsCd";

    /** SSM set Key Name: SUBSTR_DT_LNGTH */
    public static final String KEY_SUBSTR_DT_LNGTH = "substrDtLngth";

    /** SSM set Key Name: DAC_RGTN_STS_CD_1 */
    public static final String KEY_DAC_RGTN_STS_CD_1 = "dacRgtnStsCd1";

    /** SSM set Key Name: DAC_RGTN_STS_CD_2 */
    public static final String KEY_DAC_RGTN_STS_CD_2 = "dacRgtnStsCd2";

    /** SSM set Key Name: DAP_RGTN_STS_CD_1 */
    public static final String KEY_DAP_RGTN_STS_CD_1 = "dapRgtnStsCd1";

    /** SSM set Key Name: DAP_RGTN_STS_CD_2 */
    public static final String KEY_DAP_RGTN_STS_CD_2 = "dapRgtnStsCd2";

    /**  Value : 8 */
    public static final String VAL_8 = "8";

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";

    /** DB COLUMN NAME */
    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** . */
    public static final String DWH_TRGT_DT = "DWH_TRGT_DT";
    /** . */
    public static final String LOC_NUM = "LOC_NUM";
    /** . */
    public static final String ACCT_CRAT_DT = "ACCT_CRAT_DT";
    /** . */
    public static final String ACCT_CRAT_USR_ID = "ACCT_CRAT_USR_ID";
    /** . */
    public static final String ACCT_CRAT_USR_NM = "ACCT_CRAT_USR_NM";
    /** . */
    public static final String ACCT_CRAT_LINE_BIZ_TP_CD = "ACCT_CRAT_LINE_BIZ_TP_CD";
    /** . */
    public static final String DS_ACCT_NUM = "DS_ACCT_NUM";
    /** . */
    public static final String DS_ACCT_NM = "DS_ACCT_NM";
    /** . */
    public static final String DS_ACCT_TP_CD = "DS_ACCT_TP_CD";
    /** . */
    public static final String DS_ACCT_TP_DESC_TXT = "DS_ACCT_TP_DESC_TXT";
    /** . */
    public static final String DS_ACCT_ITRL_FLG = "DS_ACCT_ITRL_FLG";
    /** . */
    public static final String DS_ACCT_CLS_CD = "DS_ACCT_CLS_CD";
    /** . */
    public static final String DS_ACCT_CLS_DESC_TXT = "DS_ACCT_CLS_DESC_TXT";
    /** . */
    public static final String ACCT_COA_CH_CD = "ACCT_COA_CH_CD";
    /** . */
    public static final String ACCT_COA_CH_DESC_TXT = "ACCT_COA_CH_DESC_TXT";
    /** . */
    public static final String ACCT_COA_AFFL_CD = "ACCT_COA_AFFL_CD";
    /** . */
    public static final String ACCT_COA_AFFL_DESC_TXT = "ACCT_COA_AFFL_DESC_TXT";
    /** . */
    public static final String DS_ACCT_DLR_CD = "DS_ACCT_DLR_CD";
    /** . */
    public static final String DS_ACCT_DLR_DESC_TXT = "DS_ACCT_DLR_DESC_TXT";
    /** . */
    public static final String DS_ACCT_LEGAL_NM = "DS_ACCT_LEGAL_NM";
    /** . */
    public static final String DBA_NM = "DBA_NM";
    /** . */
    public static final String DS_ACCT_ALT_NM = "DS_ACCT_ALT_NM";
    /** . */
    public static final String RGTN_STS_CD = "RGTN_STS_CD";
    /** . */
    public static final String RGTN_STS_DESC_TXT = "RGTN_STS_DESC_TXT";
    /** . */
    public static final String DS_ACCT_URL = "DS_ACCT_URL";
    /** . */
    public static final String DS_ACCT_DUNS_NUM = "DS_ACCT_DUNS_NUM";
    /** . */
    public static final String DS_ULT_DUNS_NUM = "DS_ULT_DUNS_NUM";
    /** . */
    public static final String DS_ACCT_DUNS_NM = "DS_ACCT_DUNS_NM";
    /** . */
    public static final String DS_LAST_UPD_DUNS_DT = "DS_LAST_UPD_DUNS_DT";
    /** . */
    public static final String DS_LOC_EMP_NUM = "DS_LOC_EMP_NUM";
    /** . */
    public static final String DS_LOC_REV_AMT = "DS_LOC_REV_AMT";
    /** . */
    public static final String DS_CUST_SIC_CD = "DS_CUST_SIC_CD";
    /** . */
    public static final String DS_XTRNL_REF_TXT = "DS_XTRNL_REF_TXT";
    /** . */
    public static final String DS_DATA_SRC_TXT = "DS_DATA_SRC_TXT";
    /** . */
    public static final String DS_CUST_SIC_DESC_TXT = "DS_CUST_SIC_DESC_TXT";
    /** . */
    public static final String ACCT_CCY_CD = "ACCT_CCY_CD";
    /** . */
    public static final String ACCT_CUST_CR_RTG_CD = "ACCT_CUST_CR_RTG_CD";
    /** . */
    public static final String ACCT_CUST_CR_RTG_DESC_TXT = "ACCT_CUST_CR_RTG_DESC_TXT";
    /** . */
    public static final String ACCT_CR_LIMIT_AMT = "ACCT_CR_LIMIT_AMT";
    /** . */
    public static final String ACCT_CR_CHK_REQ_TP_CD = "ACCT_CR_CHK_REQ_TP_CD";
    /** . */
    public static final String ACCT_CR_CHK_REQ_DESC_TXT = "ACCT_CR_CHK_REQ_DESC_TXT";
    /** . */
    public static final String ACCT_CR_RISK_CLS_CD = "ACCT_CR_RISK_CLS_CD";
    /** . */
    public static final String ACCT_CR_RISK_CLS_DESC_TXT = "ACCT_CR_RISK_CLS_DESC_TXT";
    /** . */
    public static final String ACCT_GRACE_PER_DAYS_AOT = "ACCT_GRACE_PER_DAYS_AOT";
    /** . */
    public static final String ACCT_PMT_TERM_CASH_DISC_CD = "ACCT_PMT_TERM_CASH_DISC_CD";
    /** . */
    public static final String ACCT_OVRD_PMT_TERM_FLG = "ACCT_OVRD_PMT_TERM_FLG";
    /** . */
    public static final String ACCT_CASH_OR_CC_REQ_FLG = "ACCT_CASH_OR_CC_REQ_FLG";
    /** . */
    public static final String ACCT_CUST_HARD_HLD_FLG = "ACCT_CUST_HARD_HLD_FLG";
    /** . */
    public static final String ACCT_STMT_FLG = "ACCT_STMT_FLG";
    /** . */
    public static final String AR_STMT_ISS_DAY = "AR_STMT_ISS_DAY";
    /** . */
    public static final String CLT_CUST_TP_CD = "CLT_CUST_TP_CD";
    /** . */
    public static final String CLT_PTFO_PK = "CLT_PTFO_PK";
    /** . */
    public static final String CLT_PTFO_CD = "CLT_PTFO_CD";
    /** . */
    public static final String CLT_PTFO_NM = "CLT_PTFO_NM";
    /** . */
    public static final String CLT_PTFO_DESC_TXT = "CLT_PTFO_DESC_TXT";
    /** . */
    public static final String CLT_PTFO_COR_NM = "CLT_PTFO_COR_NM";
    /** . */
    public static final String CLT_PSN_CD = "CLT_PSN_CD";
    /** . */
    public static final String CLT_PSN_NM = "CLT_PSN_NM";
    /** . */
    public static final String CLT_STMT_PHO_NUM = "CLT_STMT_PHO_NUM";
    /** . */
    public static final String CLT_STMT_FAX_NUM = "CLT_STMT_FAX_NUM";
    /** . */
    public static final String AR_ADJ_TP_CD = "AR_ADJ_TP_CD";
    /** . */
    public static final String AR_ADJ_TP_DESC_TXT = "AR_ADJ_TP_DESC_TXT";
    /** . */
    public static final String WRITE_OFF_APVL_LIMIT_AMT = "WRITE_OFF_APVL_LIMIT_AMT";
    /** . */
    public static final String CLT_CR_ANLST_EQUIP_PSN_CD = "CLT_CR_ANLST_EQUIP_PSN_CD";
    /** . */
    public static final String CLT_CR_ANLST_EQUIP_PSN_NM = "CLT_CR_ANLST_EQUIP_PSN_NM";
    /** . */
    public static final String CLT_CR_ANLST_SVC_PSN_CD = "CLT_CR_ANLST_SVC_PSN_CD";
    /** . */
    public static final String CLT_CR_ANLST_SVC_PSN_NM = "CLT_CR_ANLST_SVC_PSN_NM";
    /** . */
    public static final String CLT_CR_ANLST_SPLY_PSN_CD = "CLT_CR_ANLST_SPLY_PSN_CD";
    /** . */
    public static final String CLT_CR_ANLST_SPLY_PSN_NM = "CLT_CR_ANLST_SPLY_PSN_NM";
    /** . */
    public static final String REL_CLT_PTFO_PK = "REL_CLT_PTFO_PK";
    /** . */
    public static final String CLT_PTFO_STS_FLG = "CLT_PTFO_STS_FLG";
    /** . */
    public static final String DS_CLT_ACCT_STS_CD = "DS_CLT_ACCT_STS_CD";
    /** . */
    public static final String DS_CLT_ACCT_STS_DESC_TXT = "DS_CLT_ACCT_STS_DESC_TXT";
    /** . */
    public static final String LATE_FEE_FLG = "LATE_FEE_FLG";
    /** . */
    public static final String LATE_FEE_AMT = "LATE_FEE_AMT";
    /** . */
    public static final String ACCT_DS_CUST_TAX_CD = "ACCT_DS_CUST_TAX_CD";
    /** . */
    public static final String ACCT_DS_CUST_TAX_CALC_CD = "ACCT_DS_CUST_TAX_CALC_CD";
    /** . */
    public static final String ACCT_DS_TAX_CALC_DESC_TXT = "ACCT_DS_TAX_CALC_DESC_TXT";
    /** . */
    public static final String ACCT_DS_TAX_EXEM_FLG = "ACCT_DS_TAX_EXEM_FLG";
    /** . */
    public static final String ACCT_DS_TAX_GRP_EXEM_CD = "ACCT_DS_TAX_GRP_EXEM_CD";
    /** . */
    public static final String ACCT_TAX_GRP_EXEM_DESC_TXT = "ACCT_TAX_GRP_EXEM_DESC_TXT";
    /** . */
    public static final String ACCT_DS_EXEM_EXPR_DT = "ACCT_DS_EXEM_EXPR_DT";
    /** . */
    public static final String ACCT_DS_TAX_PRNT_TP_CD = "ACCT_DS_TAX_PRNT_TP_CD";
    /** . */
    public static final String PRIN_CUST_FLG = "PRIN_CUST_FLG";
    /** . */
    public static final String LOC_CRAT_DT = "LOC_CRAT_DT";
    /** . */
    public static final String LOC_CRAT_USR_ID = "LOC_CRAT_USR_ID";
    /** . */
    public static final String LOC_CRAT_LINE_BIZ_TP_CD = "LOC_CRAT_LINE_BIZ_TP_CD";
    /** . */
    public static final String LOC_LAST_UPD_DT = "LOC_LAST_UPD_DT";
    /** . */
    public static final String LOC_LAST_UPD_USR_ID = "LOC_LAST_UPD_USR_ID";
    /** . */
    public static final String LOC_EFF_FROM_DT = "LOC_EFF_FROM_DT";
    /** . */
    public static final String LOC_EFF_THRU_DT = "LOC_EFF_THRU_DT";
    /** . */
    public static final String LOC_ACTV_FLG = "LOC_ACTV_FLG";
    /** . */
    public static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";
    /** . */
    public static final String SCD_LINE_ADDR = "SCD_LINE_ADDR";
    /** . */
    public static final String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";
    /** . */
    public static final String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";
    /** . */
    public static final String CTY_ADDR = "CTY_ADDR";
    /** . */
    public static final String CNTY_NM = "CNTY_NM";
    /** . */
    public static final String CNTY_PK = "CNTY_PK";
    /** . */
    public static final String PROV_NM = "PROV_NM";
    /** . */
    public static final String ST_CD = "ST_CD";
    /** . */
    public static final String POST_CD = "POST_CD";
    /** . */
    public static final String CTRY_CD = "CTRY_CD";
    /** . */
    public static final String DS_LOC_NM = "DS_LOC_NM";
    /** . */
    public static final String ADDL_LOC_NM = "ADDL_LOC_NM";
    /** . */
    public static final String GLN_NUM = "GLN_NUM";
    /** . */
    public static final String TEL_NUM = "TEL_NUM";
    /** . */
    public static final String FAX_NUM = "FAX_NUM";
    /** . */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";
    /** . */
    public static final String LOC_GEO_CD = "LOC_GEO_CD";
    /** . */
    public static final String LOC_DS_INSD_CTY_LIMIT_FLG = "LOC_DS_INSD_CTY_LIMIT_FLG";
    /** . */
    public static final String LOC_DUNS_NUM = "LOC_DUNS_NUM";
    /** . */
    public static final String LOC_HQ_DUNS_NUM = "LOC_HQ_DUNS_NUM";
    /** . */
    public static final String LOC_DS_ULT_DUNS_NUM = "LOC_DS_ULT_DUNS_NUM";
    /** . */
    public static final String LOC_DS_PRNT_DUNS_NUM = "LOC_DS_PRNT_DUNS_NUM";
    /** . */
    public static final String LOC_DS_LOC_EMP_NUM = "LOC_DS_LOC_EMP_NUM";
    /** . */
    public static final String LOC_DS_LOC_REV_AMT = "LOC_DS_LOC_REV_AMT";
    /** . */
    public static final String LOC_DS_LAST_UPD_DUNS_DT = "LOC_DS_LAST_UPD_DUNS_DT";
    /** . */
    public static final String LOC_DS_CUST_SIC_CD = "LOC_DS_CUST_SIC_CD";
    /** . */
    public static final String LOC_DS_CUST_SIC_DESC_TXT = "LOC_DS_CUST_SIC_DESC_TXT";
    /** . */
    public static final String LOC_DUNS_TRADE_STYLE_NM = "LOC_DUNS_TRADE_STYLE_NM";
    /** . */
    public static final String LOC_DUNS_ACTV_CD = "LOC_DUNS_ACTV_CD";
    /** . */
    public static final String LOC_DUNS_LINE_ADDR = "LOC_DUNS_LINE_ADDR";
    /** . */
    public static final String LOC_DUNS_BIZ_NM = "LOC_DUNS_BIZ_NM";
    /** . */
    public static final String LOC_DS_LAST_RCV_DUNS_TXT = "LOC_DS_LAST_RCV_DUNS_TXT";
    /** . */
    public static final String LOC_DS_LAST_RCV_DUNS_DT = "LOC_DS_LAST_RCV_DUNS_DT";
    /** . */
    public static final String LOC_DUNS_SEND_CNT = "LOC_DUNS_SEND_CNT";
    /** . */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";
    /** . */
    public static final String BILL_EFF_FROM_DT = "BILL_EFF_FROM_DT";
    /** . */
    public static final String BILL_EFF_THRU_DT = "BILL_EFF_THRU_DT";
    /** . */
    public static final String BILL_PRIM_USG_FLG = "BILL_PRIM_USG_FLG";
    /** . */
    public static final String BILL_COA_CH_CD = "BILL_COA_CH_CD";
    /** . */
    public static final String BILL_COA_CH_DESC_TXT = "BILL_COA_CH_DESC_TXT";
    /** . */
    public static final String BILL_COA_AFFL_CD = "BILL_COA_AFFL_CD";
    /** . */
    public static final String BILL_COA_AFFL_DESC_TXT = "BILL_COA_AFFL_DESC_TXT";
    /** . */
    public static final String BILL_REM_ID = "BILL_REM_ID";
    /** . */
    public static final String BILL_CCY_CD = "BILL_CCY_CD";
    /** . */
    public static final String BILL_CUST_CR_RTG_CD = "BILL_CUST_CR_RTG_CD";
    /** . */
    public static final String BILL_CUST_CR_RTG_DESC_TXT = "BILL_CUST_CR_RTG_DESC_TXT";
    /** . */
    public static final String BILL_CR_LIMIT_AMT = "BILL_CR_LIMIT_AMT";
    /** . */
    public static final String BILL_CR_CHK_REQ_TP_CD = "BILL_CR_CHK_REQ_TP_CD";
    /** . */
    public static final String BILL_CR_CHK_REQ_DESC_TXT = "BILL_CR_CHK_REQ_DESC_TXT";
    /** . */
    public static final String BILL_CR_RISK_CLS_CD = "BILL_CR_RISK_CLS_CD";
    /** . */
    public static final String BILL_CR_RISK_CLS_DESC_TXT = "BILL_CR_RISK_CLS_DESC_TXT";
    /** . */
    public static final String BILL_GRACE_PER_DAYS_AOT = "BILL_GRACE_PER_DAYS_AOT";
    /** . */
    public static final String BILL_PMT_TERM_CASH_DISC_CD = "BILL_PMT_TERM_CASH_DISC_CD";
    /** . */
    public static final String BILL_CASH_DISC_DESC_TXT = "BILL_CASH_DISC_DESC_TXT";
    /** . */
    public static final String BILL_OVRD_PMT_TERM_FLG = "BILL_OVRD_PMT_TERM_FLG";
    /** . */
    public static final String BILL_CASH_OR_CC_REQ_FLG = "BILL_CASH_OR_CC_REQ_FLG";
    /** . */
    public static final String BILL_CUST_HARD_HLD_FLG = "BILL_CUST_HARD_HLD_FLG";
    /** . */
    public static final String BILL_AR_STMT_FLG = "BILL_AR_STMT_FLG";
    /** . */
    public static final String BILL_AR_STMT_ISS_DAY = "BILL_AR_STMT_ISS_DAY";
    /** . */
    public static final String BILL_CLT_CUST_TP_CD = "BILL_CLT_CUST_TP_CD";
    /** . */
    public static final String BILL_CLT_CUST_TP_DESC_TXT = "BILL_CLT_CUST_TP_DESC_TXT";
    /** . */
    public static final String BILL_CLT_PTFO_PK = "BILL_CLT_PTFO_PK";
    /** . */
    public static final String BILL_DS_CLT_ACCT_STS_CD = "BILL_DS_CLT_ACCT_STS_CD";
    /** . */
    public static final String BILL_LATE_FEE_FLG = "BILL_LATE_FEE_FLG";
    /** . */
    public static final String BILL_LATE_FEE_AMT = "BILL_LATE_FEE_AMT";
    /** . */
    public static final String BILL_DS_CUST_TAX_CD = "BILL_DS_CUST_TAX_CD";
    /** . */
    public static final String BILL_DS_CUST_TAX_CALC_CD = "BILL_DS_CUST_TAX_CALC_CD";
    /** . */
    public static final String BILL_TO_TAX_CALC_DESC_TXT = "BILL_TO_TAX_CALC_DESC_TXT";
    /** . */
    public static final String BILL_DS_TAX_EXEM_FLG = "BILL_DS_TAX_EXEM_FLG";
    /** . */
    public static final String BILL_DS_EXEM_EXPR_DT = "BILL_DS_EXEM_EXPR_DT";
    /** . */
    public static final String BILL_DS_TAX_GRP_EXEM_CD = "BILL_DS_TAX_GRP_EXEM_CD";
    /** . */
    public static final String BILL_TAX_GRP_EXEM_DESC_TXT = "BILL_TAX_GRP_EXEM_DESC_TXT";
    /** . */
    public static final String BILL_DS_TAX_PRNT_TP_CD = "BILL_DS_TAX_PRNT_TP_CD";
    /** . */
    public static final String BILL_TAX_PRINT_TP_DESC_TXT = "BILL_TAX_PRINT_TP_DESC_TXT";
    /** . */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";
    /** . */
    public static final String SHIP_EFF_FROM_DT = "SHIP_EFF_FROM_DT";
    /** . */
    public static final String SHIP_EFF_THRU_DT = "SHIP_EFF_THRU_DT";
    /** . */
    public static final String SHIP_RELN_BILL_TO_CUST_CD = "SHIP_RELN_BILL_TO_CUST_CD";
    /** . */
    public static final String SHIP_PRIM_USG_FLG = "SHIP_PRIM_USG_FLG";
    /** . */
    public static final String BIG_DEAL_NUM = "BIG_DEAL_NUM";
    /** . */
    public static final String SHIP_COA_CH_CD = "SHIP_COA_CH_CD";
    /** . */
    public static final String SHIP_COA_CH_DESC_TXT = "SHIP_COA_CH_DESC_TXT";
    /** . */
    public static final String SHIP_COA_AFFL_CD = "SHIP_COA_AFFL_CD";
    /** . */
    public static final String SHIP_COA_AFFL_DESC_TXT = "SHIP_COA_AFFL_DESC_TXT";
    /** . */
    public static final String SHIP_DS_CUST_TAX_CD = "SHIP_DS_CUST_TAX_CD";
    /** . */
    public static final String SHIP_DS_CUST_TAX_CALC_CD = "SHIP_DS_CUST_TAX_CALC_CD";
    /** . */
    public static final String SHIP_TAX_CALC_DESC_TXT = "SHIP_TAX_CALC_DESC_TXT";
    /** . */
    public static final String SHIP_DS_TAX_EXEM_FLG = "SHIP_DS_TAX_EXEM_FLG";
    /** . */
    public static final String SHIP_DS_EXEM_EXPR_DT = "SHIP_DS_EXEM_EXPR_DT";
    /** . */
    public static final String SHIP_DS_TAX_GRP_EXEM_CD = "SHIP_DS_TAX_GRP_EXEM_CD";
    /** . */
    public static final String SHIP_TAX_GRP_EXEM_DESC_TXT = "SHIP_TAX_GRP_EXEM_DESC_TXT";
    /** . */
    public static final String SHIP_DS_TAX_PRNT_TP_CD = "SHIP_DS_TAX_PRNT_TP_CD";
    /** . */
    public static final String SHIP_TAX_PRINT_TP_DESC_TXT = "SHIP_TAX_PRINT_TP_DESC_TXT";
}
