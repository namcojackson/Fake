/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB117001;

import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_CASH_OR_CC_REQ_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_CCY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_COA_AFFL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_COA_AFFL_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_COA_CH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_COA_CH_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_CRAT_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_CRAT_LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_CRAT_USR_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_CRAT_USR_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_CR_CHK_REQ_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_CR_CHK_REQ_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_CR_LIMIT_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_CR_RISK_CLS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_CR_RISK_CLS_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_CUST_CR_RTG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_CUST_CR_RTG_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_CUST_HARD_HLD_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_DS_CUST_TAX_CALC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_DS_CUST_TAX_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_DS_EXEM_EXPR_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_DS_TAX_CALC_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_DS_TAX_EXEM_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_DS_TAX_GRP_EXEM_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_DS_TAX_PRNT_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_GRACE_PER_DAYS_AOT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_OVRD_PMT_TERM_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_PMT_TERM_CASH_DISC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_STMT_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ACCT_TAX_GRP_EXEM_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ADDL_LOC_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.AR_ADJ_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.AR_ADJ_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.AR_STMT_ISS_DAY;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BIG_DEAL_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_AR_STMT_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_AR_STMT_ISS_DAY;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_CASH_DISC_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_CASH_OR_CC_REQ_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_CCY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_CLT_CUST_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_CLT_CUST_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_CLT_PTFO_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_COA_AFFL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_COA_AFFL_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_COA_CH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_COA_CH_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_CR_CHK_REQ_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_CR_CHK_REQ_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_CR_LIMIT_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_CR_RISK_CLS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_CR_RISK_CLS_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_CUST_CR_RTG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_CUST_CR_RTG_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_CUST_HARD_HLD_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_DS_CLT_ACCT_STS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_DS_CUST_TAX_CALC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_DS_CUST_TAX_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_DS_EXEM_EXPR_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_DS_TAX_EXEM_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_DS_TAX_GRP_EXEM_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_DS_TAX_PRNT_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_GRACE_PER_DAYS_AOT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_LATE_FEE_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_LATE_FEE_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_OVRD_PMT_TERM_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_PMT_TERM_CASH_DISC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_PRIM_USG_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_REM_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_TAX_GRP_EXEM_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_TAX_PRINT_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.BILL_TO_TAX_CALC_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_CR_ANLST_EQUIP_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_CR_ANLST_EQUIP_PSN_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_CR_ANLST_SPLY_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_CR_ANLST_SPLY_PSN_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_CR_ANLST_SVC_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_CR_ANLST_SVC_PSN_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_CUST_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_PSN_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_PTFO_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_PTFO_COR_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_PTFO_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_PTFO_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_PTFO_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_PTFO_STS_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_STMT_FAX_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CLT_STMT_PHO_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CNTY_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CNTY_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CTRY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.CTY_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DBA_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_ACCT_ALT_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_ACCT_CLS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_ACCT_CLS_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_ACCT_DLR_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_ACCT_DLR_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_ACCT_DUNS_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_ACCT_DUNS_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_ACCT_ITRL_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_ACCT_LEGAL_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_ACCT_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_ACCT_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_ACCT_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_ACCT_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_ACCT_URL;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_CLT_ACCT_STS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_CLT_ACCT_STS_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_CUST_SIC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_CUST_SIC_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_DATA_SRC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_LAST_UPD_DUNS_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_LOC_EMP_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_LOC_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_LOC_REV_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_ULT_DUNS_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.DS_XTRNL_REF_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.FAX_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.FRTH_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.GLN_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.KEY_DAC_RGTN_STS_CD_1;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.KEY_DAC_RGTN_STS_CD_2;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.KEY_DAP_RGTN_STS_CD_1;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.KEY_DAP_RGTN_STS_CD_2;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.KEY_RGTN_STS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.KEY_SUBSTR_DT_LNGTH;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LATE_FEE_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LATE_FEE_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_ACTV_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_CRAT_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_CRAT_LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_CRAT_USR_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DS_CUST_SIC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DS_CUST_SIC_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DS_INSD_CTY_LIMIT_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DS_LAST_RCV_DUNS_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DS_LAST_RCV_DUNS_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DS_LAST_UPD_DUNS_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DS_LOC_EMP_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DS_LOC_REV_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DS_PRNT_DUNS_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DS_ULT_DUNS_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DUNS_ACTV_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DUNS_BIZ_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DUNS_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DUNS_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DUNS_SEND_CNT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_DUNS_TRADE_STYLE_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_GEO_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_HQ_DUNS_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_LAST_UPD_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_LAST_UPD_USR_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.LOC_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.POST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.PRIN_CUST_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.PROV_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.REL_CLT_PTFO_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.RGTN_STS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.RGTN_STS_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SCD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_COA_AFFL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_COA_AFFL_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_COA_CH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_COA_CH_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_DS_CUST_TAX_CALC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_DS_CUST_TAX_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_DS_EXEM_EXPR_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_DS_TAX_EXEM_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_DS_TAX_GRP_EXEM_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_DS_TAX_PRNT_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_PRIM_USG_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_RELN_BILL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_TAX_CALC_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_TAX_GRP_EXEM_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_TAX_PRINT_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.TEL_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.THIRD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.VAL_8;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.WRITE_OFF_APVL_LIMIT_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NMA.NMAB117001.constant.NMAB117001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DMN_ACCT_LOCTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Customer Dimension Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/29/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NMAB117001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Target Date */
    private String trgtDt = null;

    /** Commit Count */
    private int commitCount;

    /** Insert Count */
    private int normalRecCnt;

    /** Fetch Count */
    private int totalRecCnt;

    /** Error Count */
    private int errRecCnt;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * @param args parameter
     */
    public static void main(String[] args) {
        new NMAB117001().executeBatch(NMAB117001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Initialize
        this.normalRecCnt = 0;
        this.errRecCnt = 0;
        this.totalRecCnt = 0;
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE});
        }

        // Get Commit Count
        this.commitCount = getCommitCount();
        if (this.commitCount <= 0 || this.commitCount >= DEFAULT_COMMIT_COUNT) {
            this.commitCount = DEFAULT_COMMIT_COUNT;
        }

        // Get Target date
        this.trgtDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.trgtDt)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_BATCH_PROC_DATE });
        }

    }

    @Override
    protected void mainRoutine() {

        // Check Target Date Recored
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_DT, this.trgtDt);
        BigDecimal recCnt = (BigDecimal) ssmBatchClient.queryObject("getTrgtDtRec", queryParam);
        if (ZYPCommonFunc.hasValue(recCnt) && (BigDecimal.ZERO.compareTo(recCnt) < 0)) {
            // Delete Target Date Recored
            DMN_ACCT_LOCTMsg tMsg = new DMN_ACCT_LOCTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg});
            }
        }

        // Get Main Data For DMN_ACCT_LOC Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(PARAM_TRGT_DT, this.trgtDt);
        param.put(KEY_SUBSTR_DT_LNGTH, VAL_8);
        param.put(KEY_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put(KEY_DAC_RGTN_STS_CD_1, RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put(KEY_DAC_RGTN_STS_CD_2, RGTN_STS.TERMINATED);
        param.put(KEY_DAP_RGTN_STS_CD_1, RGTN_STS.PENDING_COMPLETION);
        param.put(KEY_DAP_RGTN_STS_CD_2, RGTN_STS.TERMINATED);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getMainData", param, execParam);
            rs = stmt.executeQuery();

            // Loop for Main data
            int wkInsertCount = 0;
            while (rs.next()) {
                this.totalRecCnt++;

                // Map Result Set Data To DMN_ACCT_LOC
                DMN_ACCT_LOCTMsg tMsg = mapData(rs);

                // Insert DMN_ACCT_LOC
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", LOC_NUM, "=", rs.getString(LOC_NUM));
                    S21InfoLogOutput.println(ZZZM9012E, new String[] {errMsg});
                    this.errRecCnt++;
                }

                // Commit By Commit Point
                wkInsertCount++;
                if (this.errRecCnt == 0 && wkInsertCount == this.commitCount) {
                    commit();
                    this.normalRecCnt = this.normalRecCnt + wkInsertCount;
                    wkInsertCount = 0;
                }

            } // End Loop

            // Commit By Last Data
            if (this.errRecCnt == 0 && wkInsertCount > 0) {
                this.normalRecCnt = this.normalRecCnt + wkInsertCount;
                commit();
            } else {
                rollback();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }
    /**
     * Map Result Set To DMN_ACCT_LOCTMsg
     */
    private DMN_ACCT_LOCTMsg mapData(ResultSet rs) throws SQLException {
        DMN_ACCT_LOCTMsg tMsg = new DMN_ACCT_LOCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt,  this.trgtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.locNum, rs.getString(LOC_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCratDt, rs.getString(ACCT_CRAT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCratUsrId, rs.getString(ACCT_CRAT_USR_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCratUsrNm, rs.getString(ACCT_CRAT_USR_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCratLineBizTpCd, rs.getString(ACCT_CRAT_LINE_BIZ_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rs.getString(DS_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, rs.getString(DS_ACCT_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctTpCd, rs.getString(DS_ACCT_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctTpDescTxt, rs.getString(DS_ACCT_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctItrlFlg, rs.getString(DS_ACCT_ITRL_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctClsCd, rs.getString(DS_ACCT_CLS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctClsDescTxt, rs.getString(DS_ACCT_CLS_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCoaChCd, rs.getString(ACCT_COA_CH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCoaChDescTxt, rs.getString(ACCT_COA_CH_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCoaAfflCd, rs.getString(ACCT_COA_AFFL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCoaAfflDescTxt, rs.getString(ACCT_COA_AFFL_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctDlrCd, rs.getString(DS_ACCT_DLR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctDlrDescTxt, rs.getString(DS_ACCT_DLR_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctLegalNm, rs.getString(DS_ACCT_LEGAL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.dbaNm, rs.getString(DBA_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctAltNm, rs.getString(DS_ACCT_ALT_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.rgtnStsCd, rs.getString(RGTN_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rgtnStsDescTxt, rs.getString(RGTN_STS_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctUrl, rs.getString(DS_ACCT_URL));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctDunsNum, rs.getString(DS_ACCT_DUNS_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsUltDunsNum, rs.getString(DS_ULT_DUNS_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctDunsNm, rs.getString(DS_ACCT_DUNS_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsLastUpdDunsDt, rs.getString(DS_LAST_UPD_DUNS_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsLocEmpNum, rs.getBigDecimal(DS_LOC_EMP_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsLocRevAmt, rs.getBigDecimal(DS_LOC_REV_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicCd, rs.getString(DS_CUST_SIC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsXtrnlRefTxt, rs.getString(DS_XTRNL_REF_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsDataSrcTxt, rs.getString(DS_DATA_SRC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsCustSicDescTxt, rs.getString(DS_CUST_SIC_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCcyCd, rs.getString(ACCT_CCY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCustCrRtgCd, rs.getString(ACCT_CUST_CR_RTG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCustCrRtgDescTxt, rs.getString(ACCT_CUST_CR_RTG_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCrLimitAmt, rs.getBigDecimal(ACCT_CR_LIMIT_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCrChkReqTpCd, rs.getString(ACCT_CR_CHK_REQ_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCrChkReqDescTxt, rs.getString(ACCT_CR_CHK_REQ_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCrRiskClsCd, rs.getString(ACCT_CR_RISK_CLS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCrRiskClsDescTxt, rs.getString(ACCT_CR_RISK_CLS_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.acctGracePerDaysAot, rs.getBigDecimal(ACCT_GRACE_PER_DAYS_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.acctPmtTermCashDiscCd, rs.getString(ACCT_PMT_TERM_CASH_DISC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.acctOvrdPmtTermFlg, rs.getString(ACCT_OVRD_PMT_TERM_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCashOrCcReqFlg, rs.getString(ACCT_CASH_OR_CC_REQ_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.acctCustHardHldFlg, rs.getString(ACCT_CUST_HARD_HLD_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.acctStmtFlg, rs.getString(ACCT_STMT_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.arStmtIssDay, rs.getString(AR_STMT_ISS_DAY));
        ZYPEZDItemValueSetter.setValue(tMsg.cltCustTpCd, rs.getString(CLT_CUST_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoPk, rs.getBigDecimal(CLT_PTFO_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoCd, rs.getString(CLT_PTFO_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoNm, rs.getString(CLT_PTFO_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoDescTxt, rs.getString(CLT_PTFO_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoCorNm, rs.getString(CLT_PTFO_COR_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.cltPsnCd, rs.getString(CLT_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.cltPsnNm, rs.getString(CLT_PSN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.cltStmtPhoNum, rs.getString(CLT_STMT_PHO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.cltStmtFaxNum, rs.getString(CLT_STMT_FAX_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.arAdjTpCd, rs.getString(AR_ADJ_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.arAdjTpDescTxt, rs.getString(AR_ADJ_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.writeOffApvlLimitAmt, rs.getBigDecimal(WRITE_OFF_APVL_LIMIT_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstEquipPsnCd, rs.getString(CLT_CR_ANLST_EQUIP_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstEquipPsnNm, rs.getString(CLT_CR_ANLST_EQUIP_PSN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstSvcPsnCd, rs.getString(CLT_CR_ANLST_SVC_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstSvcPsnNm, rs.getString(CLT_CR_ANLST_SVC_PSN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstSplyPsnCd, rs.getString(CLT_CR_ANLST_SPLY_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.cltCrAnlstSplyPsnNm, rs.getString(CLT_CR_ANLST_SPLY_PSN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.relCltPtfoPk, rs.getBigDecimal(REL_CLT_PTFO_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.cltPtfoStsFlg, rs.getString(CLT_PTFO_STS_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.dsCltAcctStsCd, rs.getString(DS_CLT_ACCT_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsCltAcctStsDescTxt, rs.getString(DS_CLT_ACCT_STS_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.lateFeeFlg, rs.getString(LATE_FEE_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.lateFeeAmt, rs.getBigDecimal(LATE_FEE_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.acctDsCustTaxCd, rs.getString(ACCT_DS_CUST_TAX_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.acctDsCustTaxCalcCd, rs.getString(ACCT_DS_CUST_TAX_CALC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.acctDsTaxCalcDescTxt, rs.getString(ACCT_DS_TAX_CALC_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.acctDsTaxExemFlg, rs.getString(ACCT_DS_TAX_EXEM_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.acctDsTaxGrpExemCd, rs.getString(ACCT_DS_TAX_GRP_EXEM_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.acctTaxGrpExemDescTxt, rs.getString(ACCT_TAX_GRP_EXEM_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.acctDsExemExprDt, rs.getString(ACCT_DS_EXEM_EXPR_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.acctDsTaxPrntTpCd, rs.getString(ACCT_DS_TAX_PRNT_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.prinCustFlg, rs.getString(PRIN_CUST_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.locCratDt, rs.getString(LOC_CRAT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.locCratUsrId, rs.getString(LOC_CRAT_USR_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.locCratLineBizTpCd, rs.getString(LOC_CRAT_LINE_BIZ_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.locLastUpdDt, rs.getString(LOC_LAST_UPD_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.locLastUpdUsrId, rs.getString(LOC_LAST_UPD_USR_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.locEffFromDt, rs.getString(LOC_EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.locEffThruDt, rs.getString(LOC_EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.locActvFlg, rs.getString(LOC_ACTV_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, rs.getString(FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, rs.getString(SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, rs.getString(THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, rs.getString(FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, rs.getString(CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.cntyNm, rs.getString(CNTY_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.cntyPk, rs.getBigDecimal(CNTY_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.provNm, rs.getString(PROV_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString(ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, rs.getString(POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, rs.getString(CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsLocNm, rs.getString(DS_LOC_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.addlLocNm, rs.getString(ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.glnNum, rs.getBigDecimal(GLN_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.telNum, rs.getString(TEL_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.faxNum, rs.getString(FAX_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd, rs.getString(LINE_BIZ_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.locGeoCd, rs.getString(LOC_GEO_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.locDsInsdCtyLimitFlg, rs.getString(LOC_DS_INSD_CTY_LIMIT_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.locDunsNum, rs.getString(LOC_DUNS_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.locHqDunsNum, rs.getString(LOC_HQ_DUNS_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.locDsUltDunsNum, rs.getString(LOC_DS_ULT_DUNS_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.locDsPrntDunsNum, rs.getString(LOC_DS_PRNT_DUNS_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.locDsLocEmpNum, rs.getBigDecimal(LOC_DS_LOC_EMP_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.locDsLocRevAmt, rs.getBigDecimal(LOC_DS_LOC_REV_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.locDsLastUpdDunsDt, rs.getString(LOC_DS_LAST_UPD_DUNS_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.locDsCustSicCd, rs.getString(LOC_DS_CUST_SIC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.locDsCustSicDescTxt, rs.getString(LOC_DS_CUST_SIC_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.locDunsTradeStyleNm, rs.getString(LOC_DUNS_TRADE_STYLE_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.locDunsActvCd, rs.getString(LOC_DUNS_ACTV_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.locDunsLineAddr, rs.getString(LOC_DUNS_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.locDunsBizNm, rs.getString(LOC_DUNS_BIZ_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.locDsLastRcvDunsTxt, rs.getString(LOC_DS_LAST_RCV_DUNS_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.locDsLastRcvDunsDt, rs.getString(LOC_DS_LAST_RCV_DUNS_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.locDunsSendCnt, rs.getBigDecimal(LOC_DUNS_SEND_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, rs.getString(BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billEffFromDt, rs.getString(BILL_EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.billEffThruDt, rs.getString(BILL_EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.billPrimUsgFlg, rs.getString(BILL_PRIM_USG_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.billCoaChCd, rs.getString(BILL_COA_CH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billCoaChDescTxt, rs.getString(BILL_COA_CH_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.billCoaAfflCd, rs.getString(BILL_COA_AFFL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billCoaAfflDescTxt, rs.getString(BILL_COA_AFFL_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.billRemId, rs.getString(BILL_REM_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.billCcyCd, rs.getString(BILL_CCY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billCustCrRtgCd, rs.getString(BILL_CUST_CR_RTG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billCustCrRtgDescTxt, rs.getString(BILL_CUST_CR_RTG_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.billCrLimitAmt, rs.getBigDecimal(BILL_CR_LIMIT_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.billCrChkReqTpCd, rs.getString(BILL_CR_CHK_REQ_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billCrChkReqDescTxt, rs.getString(BILL_CR_CHK_REQ_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.billCrRiskClsCd, rs.getString(BILL_CR_RISK_CLS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billCrRiskClsDescTxt, rs.getString(BILL_CR_RISK_CLS_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.billGracePerDaysAot, rs.getBigDecimal(BILL_GRACE_PER_DAYS_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.billPmtTermCashDiscCd, rs.getString(BILL_PMT_TERM_CASH_DISC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billCashDiscDescTxt, rs.getString(BILL_CASH_DISC_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.billOvrdPmtTermFlg, rs.getString(BILL_OVRD_PMT_TERM_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.billCashOrCcReqFlg, rs.getString(BILL_CASH_OR_CC_REQ_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.billCustHardHldFlg, rs.getString(BILL_CUST_HARD_HLD_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.billArStmtFlg, rs.getString(BILL_AR_STMT_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.billArStmtIssDay, rs.getString(BILL_AR_STMT_ISS_DAY));
        ZYPEZDItemValueSetter.setValue(tMsg.billCltCustTpCd, rs.getString(BILL_CLT_CUST_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billCltCustTpDescTxt, rs.getString(BILL_CLT_CUST_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.billCltPtfoPk, rs.getBigDecimal(BILL_CLT_PTFO_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.billDsCltAcctStsCd, rs.getString(BILL_DS_CLT_ACCT_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billLateFeeFlg, rs.getString(BILL_LATE_FEE_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.billLateFeeAmt, rs.getBigDecimal(BILL_LATE_FEE_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.billDsCustTaxCd, rs.getString(BILL_DS_CUST_TAX_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billDsCustTaxCalcCd, rs.getString(BILL_DS_CUST_TAX_CALC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billToTaxCalcDescTxt, rs.getString(BILL_TO_TAX_CALC_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.billDsTaxExemFlg, rs.getString(BILL_DS_TAX_EXEM_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.billDsExemExprDt, rs.getString(BILL_DS_EXEM_EXPR_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.billDsTaxGrpExemCd, rs.getString(BILL_DS_TAX_GRP_EXEM_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billTaxGrpExemDescTxt, rs.getString(BILL_TAX_GRP_EXEM_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.billDsTaxPrntTpCd, rs.getString(BILL_DS_TAX_PRNT_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.billTaxPrintTpDescTxt, rs.getString(BILL_TAX_PRINT_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, rs.getString(SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipEffFromDt, rs.getString(SHIP_EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.shipEffThruDt, rs.getString(SHIP_EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.shipRelnBillToCustCd, rs.getString(SHIP_RELN_BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipPrimUsgFlg, rs.getString(SHIP_PRIM_USG_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.bigDealNum, rs.getString(BIG_DEAL_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.shipCoaChCd, rs.getString(SHIP_COA_CH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipCoaChDescTxt, rs.getString(SHIP_COA_CH_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.shipCoaAfflCd, rs.getString(SHIP_COA_AFFL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipCoaAfflDescTxt, rs.getString(SHIP_COA_AFFL_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.shipDsCustTaxCd, rs.getString(SHIP_DS_CUST_TAX_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipDsCustTaxCalcCd, rs.getString(SHIP_DS_CUST_TAX_CALC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipTaxCalcDescTxt, rs.getString(SHIP_TAX_CALC_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.shipDsTaxExemFlg, rs.getString(SHIP_DS_TAX_EXEM_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.shipDsExemExprDt, rs.getString(SHIP_DS_EXEM_EXPR_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.shipDsTaxGrpExemCd, rs.getString(SHIP_DS_TAX_GRP_EXEM_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipTaxGrpExemDescTxt, rs.getString(SHIP_TAX_GRP_EXEM_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.shipDsTaxPrntTpCd, rs.getString(SHIP_DS_TAX_PRNT_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipTaxPrintTpDescTxt, rs.getString(SHIP_TAX_PRINT_TP_DESC_TXT));

        return tMsg;
    }

    @Override
    protected void termRoutine() {
        if (this.errRecCnt > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);
    }
}
