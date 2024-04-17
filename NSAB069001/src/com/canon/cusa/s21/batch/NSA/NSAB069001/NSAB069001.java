package com.canon.cusa.s21.batch.NSA.NSAB069001;

import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.AUTO_CRAT_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.BILL_TO_ACCT_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.BILL_TO_ACCT_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.BILL_TO_LOC_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.BIZ_HRS_MON_FRI_FROM_TM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.BIZ_HRS_MON_FRI_TO_TM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.BIZ_HRS_SAT_FROM_TM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.BIZ_HRS_SAT_TO_TM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.BIZ_HRS_SUN_FROM_TM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.BIZ_HRS_SUN_TO_TM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.BR_ORG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CELL_DS_CTAC_PNT_VAL_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CNTY_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.COA_PROD_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.COA_PROD_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CONTR_VRSN_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CONTR_VRSN_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CORP_ADVTG_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CPO_DTL_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CTAC_PSN_FIRST_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CTAC_PSN_LAST_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CTAC_PSN_MID_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CTAC_PSN_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CTRL_FLD_TXT_01;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CTRL_FLD_TXT_02;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CTRL_FLD_TXT_03;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CTRL_FLD_TXT_04;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CTRL_FLD_TXT_05;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CTRL_FLD_TXT_06;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CTRY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CTY_ADDR;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CUR_LOC_ACCT_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CUR_LOC_ACCT_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CUR_LOC_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CUST_ISS_PO_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CUST_ISS_PO_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.CUST_MACH_CTRL_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.DRUM_EXCH_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.DS_CONTR_CATG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.DS_CONTR_CATG_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.DS_CONTR_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.DS_KEY_ACCT_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.DS_PO_EXPR_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.EML_DS_CTAC_PNT_VAL_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.ENET_CMNT_TXT_01;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.ENET_CMNT_TXT_02;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.ENET_PLOT_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.EXCH_DRUM_MTR_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.EXTN_DS_CTAC_PNT_VAL_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.FAX_DS_CTAC_PNT_VAL_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.FIN_BR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.FLD_SVC_BR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.FRTH_LINE_ADDR;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.HARD_DRIVE_ERASE_INCL_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.HARD_DRIVE_RMV_INCL_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.IND_BILL_TO_LOC_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.IND_CUR_LOC_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.INTFC_TO_FA_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.ISTL_AUTH_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.ISTL_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.ISTL_STS_UPD_CPLT_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.IWR_RGTN_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.KEY_DS_CONTR_DTL_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.KEY_EMAIL_WORK;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.KEY_FAX_WORK;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.KEY_FST_ORG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.KEY_ORG_STRU_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.KEY_PHONE_MOBILE;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.KEY_PHONE_WORK;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.KEY_SVC_KEY_OPERATOR;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.KEY_SVC_MACH_MSTR_STS_1;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.KEY_SVC_MACH_MSTR_STS_2;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.KEY_SVC_MACH_MSTR_STS_3;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.KEY_SVC_MACH_MSTR_STS_4;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.KEY_SVC_MACH_MSTR_STS_TRMN;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.KEY_SVC_MACH_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.KEY_SVC_TERM_COND_ATTRB;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.LAST_PRVNT_MAINT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.LAST_PRVNT_MAINT_MTR_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.LAST_SVC_CALL_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.LAST_SVC_CALL_MTR_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.LAST_SVC_CALL_VISIT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.LAST_TECH_VISIT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.LAST_TECH_VISIT_MTR_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.LEASE_END_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.LEASE_RTRN_FEE_INCL_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.LTST_FSR_CRAT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.LTST_FSR_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.LTST_FSR_VISIT_ARV_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.LTST_VISIT_TECH_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.MDL_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.MDSE_DESC_SHORT_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.MSG_ITEM_DWH_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.MTR_GRP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.MTR_GRP_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.OLD_SER_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.OWNR_ACCT_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.OWNR_ACCT_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.OWNR_LOC_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.PARAM_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.POST_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.PRC_CONTR_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.PRF_TECH_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.PRNT_SER_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.PROV_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.REQ_TECH_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.RMA_LINE_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.RMA_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.RMA_LINE_SUB_TRX_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.RMA_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.RTRN_TO_WH_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SCD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SHIP_FROM_WH_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SHPG_PLN_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SHR_DLR_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SLD_BY_LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SO_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SO_SLP_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.STK_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.ST_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_BY_LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_CMNT_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_CONFIG_MSTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_CORP_ADVTG_STS_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_LIC_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_MACH_MAINT_AVAL_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_MACH_MSTR_LOC_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_MACH_MSTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_MACH_MSTR_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_MACH_MSTR_STS_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_MACH_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_MACH_RMV_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_MACH_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_MACH_TRX_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_MACH_TRX_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_MACH_USG_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_MACH_USG_STS_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_NTWK_CONN_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_NTWK_CONN_STS_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_TRTY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.SVC_TRTY_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.TEL_DS_CTAC_PNT_VAL_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.THIRD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.TOT_SVC_VISIT_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.TRGT_SVC_RSP_VAL_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.TRGT_SVC_RSTR_VAL_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.TRX_HDR_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.TRX_LINE_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.TRX_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.T_MDL_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.VAL_2;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.VAL_7;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.ZZZM9006E;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NSA.NSAB069001.constant.NSAB069001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.FCT_MLY_MACH_MSTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Machine Master Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/16/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NSAB069001 extends S21BatchMain {


    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Target Date */
    private String trgtDt = null;

    /** Target Year Month */
    private String trgtYrMth = null;

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
        new NSAB069001().executeBatch(NSAB069001.class.getSimpleName());
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
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
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

        // Get Target Year Month
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(PARAM_TRGT_DT, this.trgtDt);
        this.trgtYrMth = (String) ssmBatchClient.queryObject("getTrgtYrMth", queryParam);
        if (!ZYPCommonFunc.hasValue(this.trgtYrMth)) {
            throw new S21AbendException(ZZZM9006E, new String[] {MSG_ITEM_DWH_TRGT_YR_MTH });
        }

    }

    @Override
    protected void mainRoutine() {

        // Check Target Year Month Recored
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_YR_MTH, this.trgtYrMth);
        BigDecimal recCnt = (BigDecimal) ssmBatchClient.queryObject("getTrgtYrMthRec", queryParam);
        if (ZYPCommonFunc.hasValue(recCnt) && (BigDecimal.ZERO.compareTo(recCnt) < 0)) {
            // Delete Target Year Month Recored
            FCT_MLY_MACH_MSTRTMsg tMsg = new FCT_MLY_MACH_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg});
            }
        }

        // Get Main Data For FCT_MLY_MACH_MSTR Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(PARAM_TRGT_YR_MTH, this.trgtYrMth);
        param.put(PARAM_TRGT_DT, this.trgtDt);
        param.put(KEY_SVC_MACH_TP_CD, SVC_MACH_TP.ACCESSORY);
        param.put(KEY_SVC_MACH_MSTR_STS_1, SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        param.put(KEY_SVC_MACH_MSTR_STS_2, SVC_MACH_MSTR_STS.INSTALLED);
        param.put(KEY_SVC_MACH_MSTR_STS_3, SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        param.put(KEY_SVC_MACH_MSTR_STS_4, SVC_MACH_MSTR_STS.DEALER_SERVICE);
        param.put(KEY_SVC_MACH_MSTR_STS_TRMN, SVC_MACH_MSTR_STS.TERMINATED);
        param.put(KEY_DS_CONTR_DTL_STS_CD, DS_CONTR_DTL_STS.ACTIVE);
        param.put(KEY_SVC_TERM_COND_ATTRB, VAL_7);
        param.put(KEY_ORG_STRU_CD, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        param.put(KEY_FST_ORG_CD, VAL_2);
        param.put(KEY_PHONE_WORK, DS_CTAC_PNT_TP.PHONE_WORK);
        param.put(KEY_EMAIL_WORK, DS_CTAC_PNT_TP.EMAIL_WORK);
        param.put(KEY_PHONE_MOBILE, DS_CTAC_PNT_TP.PHONE_MOBILE);
        param.put(KEY_FAX_WORK, DS_CTAC_PNT_TP.FAX_WORK);
        param.put(KEY_SVC_KEY_OPERATOR, SVC_CTAC_TP.SVC_KEY_OPERATOR);

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

                // Map Result Set Data To FCT_MLY_MACH_MSTR
                FCT_MLY_MACH_MSTRTMsg tMsg = mapData(rs);

                // Insert FCT_MLY_MACH_MSTR
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", SVC_MACH_MSTR_PK, "=", rs.getString(SVC_MACH_MSTR_PK), ", ",
                            SVC_CONFIG_MSTR_PK, "=", rs.getString(SVC_CONFIG_MSTR_PK));
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
     * Map Result Set To FCT_MLY_MACH_MSTRTMsg
     */
    private FCT_MLY_MACH_MSTRTMsg mapData(ResultSet rs) throws SQLException {
        FCT_MLY_MACH_MSTRTMsg tMsg = new FCT_MLY_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
        BigDecimal fctMlyMachMstrPkSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FCT_MLY_MACH_MSTR_SQ);
        ZYPEZDItemValueSetter.setValue(tMsg.fctMlyMachMstrPk, fctMlyMachMstrPkSq);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, rs.getBigDecimal(SVC_MACH_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, rs.getBigDecimal(SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, rs.getBigDecimal(MDL_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.t_MdlNm, rs.getString(T_MDL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, rs.getString(SER_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseDescShortTxt, rs.getString(MDSE_DESC_SHORT_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mtrGrpPk, rs.getBigDecimal(MTR_GRP_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.mtrGrpDescTxt, rs.getString(MTR_GRP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.coaProdCd, rs.getString(COA_PROD_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaProdDescTxt, rs.getString(COA_PROD_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, rs.getString(EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, rs.getString(EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrStsCd, rs.getString(SVC_MACH_MSTR_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrStsDescTxt, rs.getString(SVC_MACH_MSTR_STS_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcCmntTxt, rs.getString(SVC_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTrtyCd, rs.getString(SVC_TRTY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTrtyDescTxt, rs.getString(SVC_TRTY_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.ownrAcctNum, rs.getString(OWNR_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.ownrAcctNm, rs.getString(OWNR_ACCT_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.ownrLocNum, rs.getString(OWNR_LOC_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.billToAcctNum, rs.getString(BILL_TO_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.billToAcctNm, rs.getString(BILL_TO_ACCT_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.billToLocNum, rs.getString(BILL_TO_LOC_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.indBillToLocNum, rs.getString(IND_BILL_TO_LOC_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.curLocAcctNum, rs.getString(CUR_LOC_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.curLocAcctNm, rs.getString(CUR_LOC_ACCT_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.curLocNum, rs.getString(CUR_LOC_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.indCurLocNum, rs.getString(IND_CUR_LOC_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsKeyAcctFlg, rs.getString(DS_KEY_ACCT_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, rs.getString(FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, rs.getString(SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, rs.getString(THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, rs.getString(FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, rs.getString(CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.cntyPk, rs.getBigDecimal(CNTY_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.provNm, rs.getString(PROV_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString(ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, rs.getString(POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, rs.getString(CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.istlDt, rs.getString(ISTL_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.istlAuthNum, rs.getString(ISTL_AUTH_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.fldSvcBrCd, rs.getString(FLD_SVC_BR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.finBrCd, rs.getString(FIN_BR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.custIssPoNum, rs.getString(CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.custIssPoDt, rs.getString(CUST_ISS_PO_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsPoExprDt, rs.getString(DS_PO_EXPR_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrNum, rs.getString(DS_CONTR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrCatgCd, rs.getString(DS_CONTR_CATG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrCatgDescTxt, rs.getString(DS_CONTR_CATG_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.contrVrsnEffFromDt, rs.getString(CONTR_VRSN_EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.contrVrsnEffThruDt, rs.getString(CONTR_VRSN_EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.trgtSvcRspValCd, rs.getString(TRGT_SVC_RSP_VAL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.trgtSvcRstrValCd, rs.getString(TRGT_SVC_RSTR_VAL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.bizHrsSunFromTm, rs.getString(BIZ_HRS_SUN_FROM_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.bizHrsSunToTm, rs.getString(BIZ_HRS_SUN_TO_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.bizHrsMonFriFromTm, rs.getString(BIZ_HRS_MON_FRI_FROM_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.bizHrsMonFriToTm, rs.getString(BIZ_HRS_MON_FRI_TO_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.bizHrsSatFromTm, rs.getString(BIZ_HRS_SAT_FROM_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.bizHrsSatToTm, rs.getString(BIZ_HRS_SAT_TO_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.leaseEndDt, rs.getString(LEASE_END_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.intfcToFaFlg, rs.getString(INTFC_TO_FA_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.ltstFsrNum, rs.getString(LTST_FSR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.ltstFsrCratDt, rs.getString(LTST_FSR_CRAT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.ltstFsrVisitArvDt, rs.getString(LTST_FSR_VISIT_ARV_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.ltstVisitTechCd, rs.getString(LTST_VISIT_TECH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.custMachCtrlNum, rs.getString(CUST_MACH_CTRL_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachTpCd, rs.getString(SVC_MACH_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipFromWhCd, rs.getString(SHIP_FROM_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachRmvDt, rs.getString(SVC_MACH_RMV_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToWhCd, rs.getString(RTRN_TO_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.drumExchDt, rs.getString(DRUM_EXCH_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.exchDrumMtrCnt, rs.getBigDecimal(EXCH_DRUM_MTR_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.lastSvcCallDt, rs.getString(LAST_SVC_CALL_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.totSvcVisitCnt, rs.getBigDecimal(TOT_SVC_VISIT_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.lastTechVisitDt, rs.getString(LAST_TECH_VISIT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.lastTechVisitMtrCnt, rs.getBigDecimal(LAST_TECH_VISIT_MTR_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.lastPrvntMaintDt, rs.getString(LAST_PRVNT_MAINT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.lastPrvntMaintMtrCnt, rs.getBigDecimal(LAST_PRVNT_MAINT_MTR_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.lastSvcCallVisitDt, rs.getString(LAST_SVC_CALL_VISIT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.lastSvcCallMtrCnt, rs.getBigDecimal(LAST_SVC_CALL_MTR_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.prfTechCd, rs.getString(PRF_TECH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, rs.getString(CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlLineNum, rs.getString(CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlLineSubNum, rs.getString(CPO_DTL_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.shpgPlnNum, rs.getString(SHPG_PLN_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.soNum, rs.getString(SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.soSlpNum, rs.getString(SO_SLP_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.rmaNum, rs.getString(RMA_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.rmaLineNum, rs.getString(RMA_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.rmaLineSubNum, rs.getString(RMA_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.rmaLineSubTrxNum, rs.getString(RMA_LINE_SUB_TRX_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.autoCratFlg, rs.getString(AUTO_CRAT_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.oldSerNum, rs.getString(OLD_SER_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.istlStsUpdCpltFlg, rs.getString(ISTL_STS_UPD_CPLT_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachUsgStsCd, rs.getString(SVC_MACH_USG_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachUsgStsDescTxt, rs.getString(SVC_MACH_USG_STS_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcByLineBizTpCd, rs.getString(SVC_BY_LINE_BIZ_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.reqTechCd, rs.getString(REQ_TECH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.stkStsCd, rs.getString(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.prntSerNum, rs.getString(PRNT_SER_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachTrxTpCd, rs.getString(SVC_MACH_TRX_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachTrxTpDescTxt, rs.getString(SVC_MACH_TRX_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachQty, rs.getBigDecimal(SVC_MACH_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.ctrlFldTxt_01, rs.getString(CTRL_FLD_TXT_01));
        ZYPEZDItemValueSetter.setValue(tMsg.ctrlFldTxt_02, rs.getString(CTRL_FLD_TXT_02));
        ZYPEZDItemValueSetter.setValue(tMsg.ctrlFldTxt_03, rs.getString(CTRL_FLD_TXT_03));
        ZYPEZDItemValueSetter.setValue(tMsg.ctrlFldTxt_04, rs.getString(CTRL_FLD_TXT_04));
        ZYPEZDItemValueSetter.setValue(tMsg.ctrlFldTxt_05, rs.getString(CTRL_FLD_TXT_05));
        ZYPEZDItemValueSetter.setValue(tMsg.ctrlFldTxt_06, rs.getString(CTRL_FLD_TXT_06));
        ZYPEZDItemValueSetter.setValue(tMsg.prcContrNum, rs.getString(PRC_CONTR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.corpAdvtgStsCd, rs.getString(CORP_ADVTG_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcCorpAdvtgStsDescTxt, rs.getString(SVC_CORP_ADVTG_STS_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.hardDriveRmvInclFlg, rs.getString(HARD_DRIVE_RMV_INCL_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.hardDriveEraseInclFlg, rs.getString(HARD_DRIVE_ERASE_INCL_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.hardDriveEraseInclFlg, rs.getString(HARD_DRIVE_ERASE_INCL_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.leaseRtrnFeeInclFlg, rs.getString(LEASE_RTRN_FEE_INCL_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.svcNtwkConnStsCd, rs.getString(SVC_NTWK_CONN_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcNtwkConnStsDescTxt, rs.getString(SVC_NTWK_CONN_STS_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.sldByLineBizTpCd, rs.getString(SLD_BY_LINE_BIZ_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLicCnt, rs.getBigDecimal(SVC_LIC_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMaintAvalFlg, rs.getString(SVC_MACH_MAINT_AVAL_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.trxHdrNum, rs.getString(TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.trxLineNum, rs.getString(TRX_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.trxLineSubNum, rs.getString(TRX_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrLocStsCd, rs.getString(SVC_MACH_MSTR_LOC_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shrDlrFlg, rs.getString(SHR_DLR_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.iwrRgtnStsCd, rs.getString(IWR_RGTN_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.enetPlotFlg, rs.getString(ENET_PLOT_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.enetCmntTxt_01, rs.getString(ENET_CMNT_TXT_01));
        ZYPEZDItemValueSetter.setValue(tMsg.enetCmntTxt_02, rs.getString(ENET_CMNT_TXT_02));
        ZYPEZDItemValueSetter.setValue(tMsg.orgCd, rs.getString(BR_ORG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnPk, rs.getBigDecimal(CTAC_PSN_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnFirstNm, rs.getString(CTAC_PSN_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnMidNm, rs.getString(CTAC_PSN_MID_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnLastNm, rs.getString(CTAC_PSN_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.telDsCtacPntValTxt, rs.getString(TEL_DS_CTAC_PNT_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.extnDsCtacPntValTxt, rs.getString(EXTN_DS_CTAC_PNT_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.emlDsCtacPntValTxt, rs.getString(EML_DS_CTAC_PNT_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.cellDsCtacPntValTxt, rs.getString(CELL_DS_CTAC_PNT_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.faxDsCtacPntValTxt, rs.getString(FAX_DS_CTAC_PNT_VAL_TXT));

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
