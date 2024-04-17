/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB067001;

import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.AFT_DECL_PNT_DIGIT_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.ALLOC_PCT_SCL;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.AVG_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BASE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BASE_EQP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BASE_EQP_AMT_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BASE_PRC_DEAL_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BASE_SUP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BASE_SUP_AMT_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BASE_SVC_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BASE_SVC_AMT_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BLLG_COPY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BLLG_CYCLE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BLLG_CYCLE_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BLLG_CYCLE_MTH_AOT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BLLG_FREE_COPY_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BLLG_MIN_AMT_RATE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BLLG_MIN_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BLLG_MTR_LB_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BLLG_REV_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BLLG_ROLL_OVER_RATIO;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BLLG_SCHD_FROM_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BLLG_SCHD_THRU_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BLLG_TMG_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.BLLG_TMG_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.COND_DFRD_ACCTG_RULE_DURN_AOT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.COND_EST_MODE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.COND_FLT_MDL_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.COND_GRP_EQP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.COND_GRP_MI1_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.COND_GRP_MI2_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.COND_GRP_SUP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.COND_GRP_SVC_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.CONTR_CLO_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.CONTR_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.CONTR_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.CONTR_VRSN_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.CONTR_VRSN_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DEFFERAL;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DFRD_ACCTG_RULE_DURN_AOT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DFRD_REV_AMT_EQP;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DFRD_REV_AMT_MI1;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DFRD_REV_AMT_MI2;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DFRD_REV_AMT_SUP;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DFRD_REV_AMT_SVC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DFRD_REV_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_BLLG_SCHD_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_CONTR_BLLG_MTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_CONTR_BLLG_SCHD_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_CONTR_CATG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_CONTR_CRAT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_CONTR_CRAT_PSN_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_CONTR_CTRL_DTL_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_CONTR_CTRL_DTL_STS_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_CONTR_CTRL_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_CONTR_CTRL_STS_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_CONTR_DTL_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_CONTR_DTL_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_CONTR_LAST_UPD_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_CONTR_LAST_UPD_PSN_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_CONTR_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_CONTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DS_INV_SLS_CR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DTL_EQP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DTL_SUP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.DTL_SVC_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.EQP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.EQP_AMT_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.EQP_SUM;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.EQP_SUM_DF;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.EQP_SUM_N;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.EQUIP;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.FACT_CATG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.HEAD_EQP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.HEAD_SUP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.HEAD_SVC_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MDL_EQP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MDL_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MDL_SUP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MDL_SVC_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MI1_SUM;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MI1_SUM_DF;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MI2_SUM;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MI2_SUM_DF;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MISC1;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MISC2;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MODE2;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MODE3;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MTR_CHRG_DEAL_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MTR_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.MTR_LB_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.NON_MDL_EQP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.NON_MDL_SUP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.NON_MDL_SVC_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.NOT_RECOGNIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_AGG;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_ARREARS;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_AVG_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_BASE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_BILLED;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_BLLG_CYCLE_MTH_AOT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_BLLG_MTR_LB_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_CLOSE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_DEFFERAL;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_DEFFERED;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_DS_CONTR_BLLG_MTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_DS_CONTR_BLLG_SCHD_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_DS_CONTR_DTL_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_DS_CONTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_DS_INV_SLS_CR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_EQP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_FLEET;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_FLT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_MI1_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_MI2_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_MODE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_NOT_RECOGNIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_OPEN;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_RECOGNIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_SUP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_SVC_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_SVC_ALLOC_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_SVC_INV_CHRG_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_TERMINATED;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_USG_ACT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.PARAM_USG_EST;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.RECOGNIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.REV_RECOG_AMT_EQP;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.REV_RECOG_AMT_MI1;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.REV_RECOG_AMT_MI2;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.REV_RECOG_AMT_SUP;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.REV_RECOG_AMT_SVC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.ROLL_OVER_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SUP;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SUP_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SUP_AMT_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SUP_SUM;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SUP_SUM_DF;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SUP_SUM_N;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SVC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SVC_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SVC_ALLOC_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SVC_AMT_ALLOC;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SVC_CONTR_BR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SVC_INV_CHRG_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SVC_LINE_BIZ_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SVC_MACH_MSTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SVC_SUM;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SVC_SUM_DF;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.SVC_SUM_N;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.TERMINATED;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.T_MDL_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.USG_ACT;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.USG_EST;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.XS_CHRG_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.XS_MTR_AMT_RATE;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.XS_MTR_AMT_RATE_01;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.XS_MTR_AMT_RATE_02;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.XS_MTR_AMT_RATE_03;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.XS_MTR_AMT_RATE_04;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.XS_MTR_COPY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.XS_MTR_COPY_QTY_01;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.XS_MTR_COPY_QTY_02;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.XS_MTR_COPY_QTY_03;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.XS_MTR_COPY_QTY_04;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NSA.NSAB067001.constant.NSAB067001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.FCT_DLY_CONTR_REV_FCSTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_ACCTG_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_CHRG_TP;
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
 * Monthly Contract Revenue Forecast Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/23/2016   CITS            T.Kikuhara      Create          N/A
 * 04/12/2018   CITS            M.Naito         Update          QC#23378
 *</pre>
 */
public class NSAB067001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Target Date */
    private String trgtDt = null;

    /** FLEET MDL_ID By RPT_COND_CONST */
    private String fltMdlId = null;

    /** FLEET MDL_NM By RPT_COND_CONST */
    private String fltMdlNm = null;

    /** EST MODE By RPT_COND_CONST */
    private String estMode = null;

    /** DFRD_ACCTG_RULE_DURN_AOT By RPT_COND_CONST */
    private BigDecimal dfrdAcctgRuleDurnAotConst = null;

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
        new NSAB067001().executeBatch(NSAB067001.class.getSimpleName());
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

        // Get FLEET Model By RPT_COND_CONST
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_FLT, COND_FLT_MDL_ID);
        Map<String, Object> mdlMap = (Map<String, Object>) ssmBatchClient.queryObject("getModelByCond", queryParam);
        if (mdlMap == null) {
            throw new S21AbendException(ZZM9000E, new String[] {COND_FLT_MDL_ID });
        }
        this.fltMdlId = (String) mdlMap.get(MDL_ID);
        this.fltMdlNm = (String) mdlMap.get(T_MDL_NM);

        // Get EST MODE By RPT_COND_CONST
        queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_MODE, COND_EST_MODE);
        this.estMode = (String) ssmBatchClient.queryObject("getEstModeByCond", queryParam);
        if (!ZYPCommonFunc.hasValue(this.estMode)) {
            throw new S21AbendException(ZZM9000E, new String[] {COND_EST_MODE });
        }

        // Get DFRD_ACCTG_RULE_DURN_AOT By RPT_COND_CONST
        queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_MODE, COND_DFRD_ACCTG_RULE_DURN_AOT);
        this.dfrdAcctgRuleDurnAotConst = (BigDecimal) ssmBatchClient.queryObject("getDfrdAcctgRuleDurnAotByCond", queryParam);
        if (!ZYPCommonFunc.hasValue(this.dfrdAcctgRuleDurnAotConst)) {
            throw new S21AbendException(ZZM9000E, new String[] {COND_DFRD_ACCTG_RULE_DURN_AOT });
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
            FCT_DLY_CONTR_REV_FCSTTMsg tMsg = new FCT_DLY_CONTR_REV_FCSTTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg });
            }
        }

        // Get Main Data For FCT_DLY_CONTR_REV_FCST Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(PARAM_TRGT_DT, this.trgtDt);
        param.put(PARAM_BASE, BASE);
        param.put(PARAM_USG_ACT, USG_ACT);
        param.put(PARAM_USG_EST, USG_EST);
        param.put(PARAM_DEFFERAL, DEFFERAL);
        param.put(PARAM_OPEN, DS_BLLG_SCHD_STS.OPEN);
        param.put(PARAM_BILLED, DS_BLLG_SCHD_STS.BILLED);
        param.put(PARAM_CLOSE, DS_BLLG_SCHD_STS.CLOSE);
        param.put(PARAM_FLT, DS_CONTR_CATG.FLEET);
        param.put(PARAM_FLEET, DS_CONTR_DTL_TP.FLEET);
        param.put(PARAM_AGG, DS_CONTR_DTL_TP.AGGREGATE);
        param.put(PARAM_ARREARS, BLLG_TMG_TP.ARREARS);
        param.put(PARAM_DEFFERED, DFRD_ACCTG_RULE.DEFERRED);
        param.put(PARAM_TERMINATED, TERMINATED);
        param.put(PARAM_RECOGNIZE, RECOGNIZE);
        param.put(PARAM_NOT_RECOGNIZE, NOT_RECOGNIZE);

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

                // Mapping Result Set Data To FCT_DLY_CONTR_REV_FCST
                FCT_DLY_CONTR_REV_FCSTTMsg tMsg = mapData(rs);

                // Calculate Amount By Not Use Allocation Percent
                Map<String, Object> amountMap = null;
                amountMap = calcAmtNoAlc(rs);

                // Get Each Allocation Percent (EQUIPMENT/SERVICE/SUPPLIES)
                Map<String, Object> allocPctMap = null;
                if (amountMap == null && !DEFFERAL.equals(rs.getString(FACT_CATG_CD))) {
                    allocPctMap = getAllocPct(rs);
                }

                // Calculate EQUIPMENT Amount
                calcAmt(tMsg, rs, amountMap, allocPctMap, EQUIP);
                // Insert EQUIPMENT Amount into FCT_DLY_CONTR_REV_FCST
                if (!DEFFERAL.equals(rs.getString(FACT_CATG_CD)) || ZYPCommonFunc.hasValue(tMsg.revRecogAmt)) {
                    EZDTBLAccessor.insert(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", FACT_CATG_CD, "=", rs.getString(FACT_CATG_CD));
                        S21InfoLogOutput.println(ZZZM9012E, new String[] {errMsg });
                        this.errRecCnt++;
                    }
                    this.totalRecCnt++;
                    wkInsertCount++;
                }

                // Calculate SERVICE Amount
                calcAmt(tMsg, rs, amountMap, allocPctMap, SVC);
                // Insert SERVICE Amount into FCT_DLY_CONTR_REV_FCST
                if (!DEFFERAL.equals(rs.getString(FACT_CATG_CD)) || ZYPCommonFunc.hasValue(tMsg.revRecogAmt)) {
                    EZDTBLAccessor.insert(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", FACT_CATG_CD, "=", rs.getString(FACT_CATG_CD));
                        S21InfoLogOutput.println(ZZZM9012E, new String[] {errMsg });
                        this.errRecCnt++;
                    }
                    this.totalRecCnt++;
                    wkInsertCount++;
                }

                // Calculate SUPPLIES Amount
                calcAmt(tMsg, rs, amountMap, allocPctMap, SUP);
                // Insert SUPPLIES Amount into FCT_DLY_CONTR_REV_FCST
                if (!DEFFERAL.equals(rs.getString(FACT_CATG_CD)) || ZYPCommonFunc.hasValue(tMsg.revRecogAmt)) {
                    EZDTBLAccessor.insert(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", FACT_CATG_CD, "=", rs.getString(FACT_CATG_CD));
                        S21InfoLogOutput.println(ZZZM9012E, new String[] {errMsg });
                        this.errRecCnt++;
                    }
                    this.totalRecCnt++;
                    wkInsertCount++;
                }

                // when Defer Recapture case expands more two record (MISC1/MISC2)
                // Calculate MISC1 Amount
                calcAmt(tMsg, rs, amountMap, allocPctMap, MISC1);
                if (DEFFERAL.equals(rs.getString(FACT_CATG_CD)) && ZYPCommonFunc.hasValue(tMsg.revRecogAmt)) {
                   // Insert MISC1 Amount into FCT_DLY_CONTR_REV_FCST
                    EZDTBLAccessor.insert(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", FACT_CATG_CD, "=", rs.getString(FACT_CATG_CD));
                        S21InfoLogOutput.println(ZZZM9012E, new String[] {errMsg });
                        this.errRecCnt++;
                    }
                    this.totalRecCnt++;
                    wkInsertCount++;
                }
                // Calculate MISC2 Amount
                calcAmt(tMsg, rs, amountMap, allocPctMap, MISC2);
                if (DEFFERAL.equals(rs.getString(FACT_CATG_CD)) && ZYPCommonFunc.hasValue(tMsg.revRecogAmt)) {
                   // Insert MISC2 Amount into FCT_DLY_CONTR_REV_FCST
                    EZDTBLAccessor.insert(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", FACT_CATG_CD, "=", rs.getString(FACT_CATG_CD));
                        S21InfoLogOutput.println(ZZZM9012E, new String[] {errMsg });
                        this.errRecCnt++;
                    }
                    this.totalRecCnt++;
                    wkInsertCount++;
                }

                // Commit By Commit Point
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
     * Map Result Set Data To FCT_DLY_CONTR_REV_FCST
     */
    private FCT_DLY_CONTR_REV_FCSTTMsg mapData(ResultSet rs) throws SQLException {
        FCT_DLY_CONTR_REV_FCSTTMsg tMsg = new FCT_DLY_CONTR_REV_FCSTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.fctCratDt, this.trgtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.fctCatgCd, rs.getString(FACT_CATG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcAllocTpCd, rs.getString(SVC_ALLOC_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, rs.getBigDecimal(DS_CONTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrNum, rs.getString(DS_CONTR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlTpCd, rs.getString(DS_CONTR_DTL_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizCd, rs.getString(SVC_LINE_BIZ_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrCd, rs.getString(SVC_CONTR_BR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrCatgCd, rs.getString(DS_CONTR_CATG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, rs.getBigDecimal(SVC_MACH_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, rs.getString(SER_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, rs.getBigDecimal(MDL_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.t_MdlNm, rs.getString(T_MDL_NM));
        if (!ZYPCommonFunc.hasValue(rs.getBigDecimal(MDL_ID)) && DS_CONTR_CATG.FLEET.equals(rs.getString(DS_CONTR_CATG_CD))) {
            ZYPEZDItemValueSetter.setValue(tMsg.mdlId, new BigDecimal(this.fltMdlId));
            ZYPEZDItemValueSetter.setValue(tMsg.t_MdlNm, this.fltMdlNm);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.bllgSchdFromDt, rs.getString(BLLG_SCHD_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.bllgSchdThruDt, rs.getString(BLLG_SCHD_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.bllgCycleCd, rs.getString(BLLG_CYCLE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.bllgCycleDescTxt, rs.getString(BLLG_CYCLE_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.bllgCycleMthAot, rs.getBigDecimal(BLLG_CYCLE_MTH_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.bllgTmgTpCd, rs.getString(BLLG_TMG_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.bllgTmgTpDescTxt, rs.getString(BLLG_TMG_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrLbCd, rs.getString(BLLG_MTR_LB_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mtrLbDescTxt, rs.getString(MTR_LB_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.bllgCopyQty, rs.getBigDecimal(BLLG_COPY_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.xsMtrCopyQty_01, rs.getBigDecimal(XS_MTR_COPY_QTY_01));
        ZYPEZDItemValueSetter.setValue(tMsg.xsMtrAmtRate_01, rs.getBigDecimal(XS_MTR_AMT_RATE_01));
        ZYPEZDItemValueSetter.setValue(tMsg.xsMtrCopyQty_02, rs.getBigDecimal(XS_MTR_COPY_QTY_02));
        ZYPEZDItemValueSetter.setValue(tMsg.xsMtrAmtRate_02, rs.getBigDecimal(XS_MTR_AMT_RATE_02));
        ZYPEZDItemValueSetter.setValue(tMsg.xsMtrCopyQty_03, rs.getBigDecimal(XS_MTR_COPY_QTY_03));
        ZYPEZDItemValueSetter.setValue(tMsg.xsMtrAmtRate_03, rs.getBigDecimal(XS_MTR_AMT_RATE_03));
        ZYPEZDItemValueSetter.setValue(tMsg.xsMtrCopyQty_04, rs.getBigDecimal(XS_MTR_COPY_QTY_04));
        ZYPEZDItemValueSetter.setValue(tMsg.xsMtrAmtRate_04, rs.getBigDecimal(XS_MTR_AMT_RATE_04));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrCtrlStsCd, rs.getString(DS_CONTR_CTRL_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrCtrlStsNm, rs.getString(DS_CONTR_CTRL_STS_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.contrVrsnEffFromDt, rs.getString(CONTR_VRSN_EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.contrVrsnEffThruDt, rs.getString(CONTR_VRSN_EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, rs.getBigDecimal(DS_CONTR_DTL_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrCtrlDtlStsCd, rs.getString(DS_CONTR_CTRL_DTL_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrCtrlDtlStsNm, rs.getString(DS_CONTR_CTRL_DTL_STS_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.contrEffFromDt, rs.getString(CONTR_EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.contrEffThruDt, rs.getString(CONTR_EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.contrCloDt, rs.getString(CONTR_CLO_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrCratDt, rs.getString(DS_CONTR_CRAT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrCratPsnCd, rs.getString(DS_CONTR_CRAT_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrLastUpdDt, rs.getString(DS_CONTR_LAST_UPD_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrLastUpdPsnCd, rs.getString(DS_CONTR_LAST_UPD_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.bllgRevDt, rs.getString(BLLG_REV_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.revAdcvAmt, BigDecimal.ZERO);
        return tMsg;
    }

    /**
     * Calculate Amount By Not Use Allocation Percent
     */
    private Map<String, Object> calcAmtNoAlc(ResultSet rs) throws SQLException {

        if (BASE.equals(rs.getString(FACT_CATG_CD))
         && DS_BLLG_SCHD_STS.OPEN.equals(rs.getString(DS_BLLG_SCHD_STS_CD))) {
            return null;
        }

        // when Usage Estimate and Mode3. not execute pattern one Calculation
        if (USG_EST.equals(rs.getString(FACT_CATG_CD)) && MODE3.equals(this.estMode)) {
            return null;
        }

        BigDecimal revRecogAmtEqp = BigDecimal.ZERO;
        BigDecimal revRecogAmtSvc = BigDecimal.ZERO;
        BigDecimal revRecogAmtSup = BigDecimal.ZERO;
        BigDecimal revRecogAmtMi1 = BigDecimal.ZERO;
        BigDecimal revRecogAmtMi2 = BigDecimal.ZERO;
        BigDecimal dfrdRevAmtEqp = BigDecimal.ZERO;
        BigDecimal dfrdRevAmtSvc = BigDecimal.ZERO;
        BigDecimal dfrdRevAmtSup = BigDecimal.ZERO;
        BigDecimal dfrdRevAmtMi1 = BigDecimal.ZERO;
        BigDecimal dfrdRevAmtMi2 = BigDecimal.ZERO;
        BigDecimal sumAmt = BigDecimal.ZERO;

        // change query name by each case
        String queryName = null;
        if (BASE.equals(rs.getString(FACT_CATG_CD))) {
            queryName = "getAmtByContrBase";
            if (DS_BLLG_SCHD_STS.CLOSE.equals(rs.getString(DS_BLLG_SCHD_STS_CD))) {
                queryName = "getAmtByInvBase";
            }
        } else if (USG_ACT.equals(rs.getString(FACT_CATG_CD))) {
            queryName = "getAmtByContrUsgAct";
            if (DS_BLLG_SCHD_STS.CLOSE.equals(rs.getString(DS_BLLG_SCHD_STS_CD))) {
                queryName = "getAmtByInvUsgAct";
            }
        } else if (USG_EST.equals(rs.getString(FACT_CATG_CD))) {
            queryName = "getAmtByInvUsgEst";
        } else if (DEFFERAL.equals(rs.getString(FACT_CATG_CD))) {
            queryName = "getAmtByDefNotRec";
            if (RECOGNIZE.equals(rs.getString(DS_BLLG_SCHD_STS_CD))) {
                queryName = "getAmtByDefRec";
            }
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_DT, this.trgtDt);
        queryParam.put(PARAM_EQP_ALLOC, COND_GRP_EQP_ALLOC);
        queryParam.put(PARAM_SVC_ALLOC, COND_GRP_SVC_ALLOC);
        queryParam.put(PARAM_SUP_ALLOC, COND_GRP_SUP_ALLOC);
        queryParam.put(PARAM_MI1_ALLOC, COND_GRP_MI1_ALLOC);
        queryParam.put(PARAM_MI2_ALLOC, COND_GRP_MI2_ALLOC);
        queryParam.put(PARAM_DEFFERED, DFRD_ACCTG_RULE.DEFERRED);
        queryParam.put(PARAM_DS_CONTR_BLLG_SCHD_PK, rs.getBigDecimal(DS_CONTR_BLLG_SCHD_PK));
        queryParam.put(PARAM_DS_INV_SLS_CR_PK, rs.getBigDecimal(DS_INV_SLS_CR_PK));
        Map<String, Object> amountMap = (Map<String, Object>) ssmBatchClient.queryObject(queryName, queryParam);

        if (amountMap == null) {
            return null;
        }

        // calculate each Billing Amount By BASE case
        if (BASE.equals(rs.getString(FACT_CATG_CD))) {
            if (ZYPConstant.FLG_ON_Y.equals((String) amountMap.get(DFRD_REV_FLG))) {
                BigDecimal dfrdAcctgRuleDurnAot = (BigDecimal) amountMap.get(DFRD_ACCTG_RULE_DURN_AOT);
                if (BigDecimal.ZERO.compareTo(dfrdAcctgRuleDurnAot) == 0) {
                    dfrdAcctgRuleDurnAot = this.dfrdAcctgRuleDurnAotConst;
                }
                // calculate Equipment Amount
                sumAmt = (BigDecimal) amountMap.get(EQP_SUM);
                revRecogAmtEqp = sumAmt.divide(dfrdAcctgRuleDurnAot, 2, BigDecimal.ROUND_HALF_UP);
                dfrdRevAmtEqp = (sumAmt.multiply(dfrdAcctgRuleDurnAot.subtract(BigDecimal.ONE))).divide(dfrdAcctgRuleDurnAot, 2, BigDecimal.ROUND_HALF_UP);
                // calculate Service Amount
                sumAmt = (BigDecimal) amountMap.get(SVC_SUM);
                revRecogAmtSvc = sumAmt.divide(dfrdAcctgRuleDurnAot, 2, BigDecimal.ROUND_HALF_UP);
                dfrdRevAmtSvc = (sumAmt.multiply(dfrdAcctgRuleDurnAot.subtract(BigDecimal.ONE))).divide(dfrdAcctgRuleDurnAot, 2, BigDecimal.ROUND_HALF_UP);
                // calculate Supplies Amount
                sumAmt = (BigDecimal) amountMap.get(SUP_SUM);
                revRecogAmtSup = sumAmt.divide(dfrdAcctgRuleDurnAot, 2, BigDecimal.ROUND_HALF_UP);
                dfrdRevAmtSup = (sumAmt.multiply(dfrdAcctgRuleDurnAot.subtract(BigDecimal.ONE))).divide(dfrdAcctgRuleDurnAot, 2, BigDecimal.ROUND_HALF_UP);
            } else if (BASE.equals(rs.getString(FACT_CATG_CD))) {
                revRecogAmtEqp = (BigDecimal) amountMap.get(EQP_SUM_N);
                revRecogAmtSvc = (BigDecimal) amountMap.get(SVC_SUM_N);
                revRecogAmtSup = (BigDecimal) amountMap.get(SUP_SUM_N);
            }
        } else if (USG_ACT.equals(rs.getString(FACT_CATG_CD))) {
            revRecogAmtEqp = (BigDecimal) amountMap.get(EQP_SUM);
            revRecogAmtSvc = (BigDecimal) amountMap.get(SVC_SUM);
            revRecogAmtSup = (BigDecimal) amountMap.get(SUP_SUM);
        } else if (USG_EST.equals(rs.getString(FACT_CATG_CD))) {
            revRecogAmtEqp = (BigDecimal) amountMap.get(REV_RECOG_AMT_EQP);
            revRecogAmtSvc = (BigDecimal) amountMap.get(REV_RECOG_AMT_SVC);
            revRecogAmtSup = (BigDecimal) amountMap.get(REV_RECOG_AMT_SUP);
        } else if (DEFFERAL.equals(rs.getString(FACT_CATG_CD))) {
            revRecogAmtEqp = (BigDecimal) amountMap.get(EQP_SUM);
            revRecogAmtSvc = (BigDecimal) amountMap.get(SVC_SUM);
            revRecogAmtSup = (BigDecimal) amountMap.get(SUP_SUM);
            revRecogAmtMi1 = (BigDecimal) amountMap.get(MI1_SUM);
            revRecogAmtMi2 = (BigDecimal) amountMap.get(MI2_SUM);
            dfrdRevAmtEqp = (BigDecimal) amountMap.get(EQP_SUM_DF);
            dfrdRevAmtSvc = (BigDecimal) amountMap.get(SVC_SUM_DF);
            dfrdRevAmtSup = (BigDecimal) amountMap.get(SUP_SUM_DF);
            dfrdRevAmtMi1 = (BigDecimal) amountMap.get(MI1_SUM_DF);
            dfrdRevAmtMi2 = (BigDecimal) amountMap.get(MI2_SUM_DF);
        }

        Map<String, Object> returnAmtMap = new HashMap<String, Object>();
        returnAmtMap.put(REV_RECOG_AMT_EQP, revRecogAmtEqp);
        returnAmtMap.put(REV_RECOG_AMT_SVC, revRecogAmtSvc);
        returnAmtMap.put(REV_RECOG_AMT_SUP, revRecogAmtSup);
        returnAmtMap.put(REV_RECOG_AMT_MI1, revRecogAmtMi1);
        returnAmtMap.put(REV_RECOG_AMT_MI2, revRecogAmtMi2);
        returnAmtMap.put(DFRD_REV_AMT_EQP, dfrdRevAmtEqp);
        returnAmtMap.put(DFRD_REV_AMT_SVC, dfrdRevAmtSvc);
        returnAmtMap.put(DFRD_REV_AMT_SUP, dfrdRevAmtSup);
        returnAmtMap.put(DFRD_REV_AMT_MI1, dfrdRevAmtMi1);
        returnAmtMap.put(DFRD_REV_AMT_MI2, dfrdRevAmtMi2);

        return returnAmtMap;

    }

    /**
     * get Each Allocation Percent
     */
    private Map<String, Object> getAllocPct(ResultSet rs) throws SQLException {

        // each allocation percent (EQUIPMENT/SERVICE/SUPPLIES)
        BigDecimal eqpAllocPct = BigDecimal.ZERO;
        BigDecimal svcAllocPct = BigDecimal.ZERO;
        BigDecimal supAllocPct = BigDecimal.ZERO;
        // START 2018/04/12 M.Naito [QC#23378,ADD]
        // each allocation amount (EQUIPMENT/SERVICE/SUPPLIES)
        BigDecimal eqpAllocAmt = null;
        BigDecimal svcAllocAmt = null;
        BigDecimal supAllocAmt = null;
        // END 2018/04/12 M.Naito [QC#23378,ADD]

        // get allocation percent by DS_CONTR_PRC_ALLOC
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_EQP_ALLOC, COND_GRP_EQP_ALLOC);
        queryParam.put(PARAM_SVC_ALLOC, COND_GRP_SVC_ALLOC);
        queryParam.put(PARAM_SUP_ALLOC, COND_GRP_SUP_ALLOC);
        queryParam.put(PARAM_SVC_INV_CHRG_TP_CD, SVC_INV_CHRG_TP.METER_CHARGE);
        if (BASE.equals(rs.getString(FACT_CATG_CD))) {
            queryParam.put(PARAM_SVC_INV_CHRG_TP_CD, SVC_INV_CHRG_TP.BASE_CHARGE);
        }
        queryParam.put(PARAM_DS_CONTR_PK, rs.getBigDecimal(DS_CONTR_PK));
        // START 2018/04/12 M.Naito [QC#23378,ADD]
        queryParam.put(PARAM_DS_CONTR_DTL_PK, rs.getBigDecimal(DS_CONTR_DTL_PK));
        // END 2018/04/12 M.Naito [QC#23378,ADD]
        List<Map<String, Object>> allocListByContr = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getAllocDataByContr", queryParam);

        // get allocation percent by CONTRACT
        if (allocListByContr.size() > 0) {
            for (Map<String, Object> allocDataByContr : allocListByContr) {
                BigDecimal dsContrDtlPk = rs.getBigDecimal(DS_CONTR_DTL_PK);
                BigDecimal dsContrDtlPkWk = (BigDecimal) allocDataByContr.get(DS_CONTR_DTL_PK);
                String svcInvChrgTpCd = (String) allocDataByContr.get(SVC_INV_CHRG_TP_CD);
                // Base Usage level
                if (ZYPCommonFunc.hasValue(svcInvChrgTpCd)
                 && ZYPCommonFunc.hasValue(dsContrDtlPk)
                 && ZYPCommonFunc.hasValue(dsContrDtlPkWk)
                 && dsContrDtlPk.equals(dsContrDtlPkWk)) {
                    eqpAllocPct = (BigDecimal) allocDataByContr.get(BASE_EQP_ALLOC);
                    svcAllocPct = (BigDecimal) allocDataByContr.get(BASE_SVC_ALLOC);
                    supAllocPct = (BigDecimal) allocDataByContr.get(BASE_SUP_ALLOC);
                    // START 2018/04/12 M.Naito [QC#23378,ADD]
                    eqpAllocAmt = (BigDecimal) allocDataByContr.get(BASE_EQP_AMT_ALLOC);
                    svcAllocAmt = (BigDecimal) allocDataByContr.get(BASE_SVC_AMT_ALLOC);
                    supAllocAmt = (BigDecimal) allocDataByContr.get(BASE_SUP_AMT_ALLOC);
                    // END 2018/04/12 M.Naito [QC#23378,ADD]
                // Contract Detail level
                } else if (ZYPCommonFunc.hasValue(dsContrDtlPk)
                        && ZYPCommonFunc.hasValue(dsContrDtlPkWk)
                        && dsContrDtlPk.equals(dsContrDtlPkWk)) {
                    eqpAllocPct = (BigDecimal) allocDataByContr.get(DTL_EQP_ALLOC);
                    svcAllocPct = (BigDecimal) allocDataByContr.get(DTL_SVC_ALLOC);
                    supAllocPct = (BigDecimal) allocDataByContr.get(DTL_SUP_ALLOC);
                // Contract Header level
                } else {
                    eqpAllocPct = (BigDecimal) allocDataByContr.get(HEAD_EQP_ALLOC);
                    svcAllocPct = (BigDecimal) allocDataByContr.get(HEAD_SVC_ALLOC);
                    supAllocPct = (BigDecimal) allocDataByContr.get(HEAD_SUP_ALLOC);
                }
            }
        // get allocation percent by AJE_INV_LINE_ALLOC
        } else {
            queryParam = new HashMap<String, Object>();
            queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
            queryParam.put(PARAM_SVC_INV_CHRG_TP_CD, SVC_INV_CHRG_TP.METER_CHARGE);
            if (BASE.equals(rs.getString(FACT_CATG_CD))) {
                queryParam.put(PARAM_SVC_INV_CHRG_TP_CD, SVC_INV_CHRG_TP.BASE_CHARGE);
            }
            if (!ZYPCommonFunc.hasValue(rs.getString(SVC_ALLOC_TP_CD))) {
                return null;
            }
            queryParam.put(PARAM_SVC_ALLOC_TP_CD, rs.getString(SVC_ALLOC_TP_CD));
            List<Map<String, Object>> allocListByAjeInv = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getAllocDataByAjeInv", queryParam);
            for (Map<String, Object> allocDataByAjeInv : allocListByAjeInv) {
                BigDecimal mdlId = rs.getBigDecimal(MDL_ID);
                if (!ZYPCommonFunc.hasValue(rs.getBigDecimal(MDL_ID)) && DS_CONTR_CATG.FLEET.equals(rs.getString(DS_CONTR_CATG_CD))) {
                    mdlId = new BigDecimal(this.fltMdlId);
                }
                BigDecimal mdlIdWk = (BigDecimal) allocDataByAjeInv.get(MDL_ID);
                // Model level
                if (ZYPCommonFunc.hasValue(mdlId)
                 && ZYPCommonFunc.hasValue(mdlIdWk)
                 && mdlId.equals(mdlIdWk)) {
                    eqpAllocPct = (BigDecimal) allocDataByAjeInv.get(MDL_EQP_ALLOC);
                    svcAllocPct = (BigDecimal) allocDataByAjeInv.get(MDL_SVC_ALLOC);
                    supAllocPct = (BigDecimal) allocDataByAjeInv.get(MDL_SUP_ALLOC);
                } else {
                    eqpAllocPct = (BigDecimal) allocDataByAjeInv.get(NON_MDL_EQP_ALLOC);
                    svcAllocPct = (BigDecimal) allocDataByAjeInv.get(NON_MDL_SVC_ALLOC);
                    supAllocPct = (BigDecimal) allocDataByAjeInv.get(NON_MDL_SUP_ALLOC);
                }
            }
        }

        Map<String, Object> allocMap = new HashMap<String, Object>();
        allocMap.put(EQP_ALLOC, eqpAllocPct);
        allocMap.put(SVC_ALLOC, svcAllocPct);
        allocMap.put(SUP_ALLOC, supAllocPct);
        // START 2018/04/12 M.Naito [QC#23378,ADD]
        allocMap.put(EQP_AMT_ALLOC, eqpAllocAmt);
        allocMap.put(SVC_AMT_ALLOC, svcAllocAmt);
        allocMap.put(SUP_AMT_ALLOC, supAllocAmt);
        // END 2018/04/12 M.Naito [QC#23378,ADD]

        return allocMap;

    }

    /**
     * Calculate Each Type Amount
     */
    private void calcAmt(FCT_DLY_CONTR_REV_FCSTTMsg tMsg, ResultSet rs, Map<String, Object> amountMap, Map<String, Object> allocMap, String revAcctCd) throws SQLException {

        BigDecimal revRecogAmt = BigDecimal.ZERO;
        BigDecimal revAdcvAmt = BigDecimal.ZERO;
        BigDecimal dfrdRevAmt = BigDecimal.ZERO;

        // this case is Defer Recapture and Amount Data is not exist
        if (DEFFERAL.equals(rs.getString(FACT_CATG_CD)) && amountMap == null) {
            return;
        }

        // when amountMap exist Amount is already calculated By Billing or Invoice
        if (amountMap != null) {
            if (EQUIP.equals(revAcctCd)) {
                revRecogAmt = (BigDecimal) amountMap.get(REV_RECOG_AMT_EQP);
                dfrdRevAmt = (BigDecimal) amountMap.get(DFRD_REV_AMT_EQP);
            } else if (SVC.equals(revAcctCd)) {
                revRecogAmt = (BigDecimal) amountMap.get(REV_RECOG_AMT_SVC);
                dfrdRevAmt = (BigDecimal) amountMap.get(DFRD_REV_AMT_SVC);
            } else if (SUP.equals(revAcctCd)) {
                revRecogAmt = (BigDecimal) amountMap.get(REV_RECOG_AMT_SUP);
                dfrdRevAmt = (BigDecimal) amountMap.get(DFRD_REV_AMT_SUP);
            } else if (MISC1.equals(revAcctCd)) {
                revRecogAmt = (BigDecimal) amountMap.get(REV_RECOG_AMT_MI1);
                dfrdRevAmt = (BigDecimal) amountMap.get(DFRD_REV_AMT_MI1);
            } else if (MISC2.equals(revAcctCd)) {
                revRecogAmt = (BigDecimal) amountMap.get(REV_RECOG_AMT_MI2);
                dfrdRevAmt = (BigDecimal) amountMap.get(DFRD_REV_AMT_MI2);
            }
        // Calculate By each allocation percent
        } else if (allocMap != null) {
            // Decide Allocation Percent
            BigDecimal allocPct = BigDecimal.ZERO;
            // START 2018/04/12 M.Naito [QC#23378,MOD]
            BigDecimal allocAmt = null;
            BigDecimal totAllocAmt = null;
            if (EQUIP.equals(revAcctCd)) {
                allocPct = (BigDecimal) allocMap.get(EQP_ALLOC);
                allocAmt = (BigDecimal) allocMap.get(EQP_AMT_ALLOC);
            } else if (SVC.equals(revAcctCd)) {
                allocPct = (BigDecimal) allocMap.get(SVC_ALLOC);
                allocAmt = (BigDecimal) allocMap.get(SVC_AMT_ALLOC);
            } else {
                allocPct = (BigDecimal) allocMap.get(SUP_ALLOC);
                allocAmt = (BigDecimal) allocMap.get(SUP_AMT_ALLOC);
            }
            if (ZYPCommonFunc.hasValue((BigDecimal) allocMap.get(EQP_AMT_ALLOC)) && ZYPCommonFunc.hasValue((BigDecimal) allocMap.get(SVC_AMT_ALLOC)) && ZYPCommonFunc.hasValue((BigDecimal) allocMap.get(SUP_AMT_ALLOC))) {
                totAllocAmt = ((BigDecimal) allocMap.get(EQP_AMT_ALLOC)).add((BigDecimal) allocMap.get(SVC_AMT_ALLOC)).add((BigDecimal) allocMap.get(SUP_AMT_ALLOC)).setScale(ALLOC_PCT_SCL, BigDecimal.ROUND_HALF_UP);
            }
            // END 2018/04/12 M.Naito [QC#23378,MOD]

            // Calculate each pattern (Billing/Invoice)
            if (BASE.equals(rs.getString(FACT_CATG_CD))
                && (DS_BLLG_SCHD_STS.OPEN.equals(rs.getString(DS_BLLG_SCHD_STS_CD))
                || DS_BLLG_SCHD_STS.BILLED.equals(rs.getString(DS_BLLG_SCHD_STS_CD)))) {

                BigDecimal basePrcDealAmt = rs.getBigDecimal(BASE_PRC_DEAL_AMT);
                BigDecimal dfrdAcctgRuleDurnAot = rs.getBigDecimal(DFRD_ACCTG_RULE_DURN_AOT);
                if (BigDecimal.ZERO.compareTo(dfrdAcctgRuleDurnAot) == 0) {
                    dfrdAcctgRuleDurnAot = this.dfrdAcctgRuleDurnAotConst;
                }

                // START 2018/04/12 M.Naito [QC#23378,MOD]
                if (ZYPConstant.FLG_ON_Y.equals(rs.getString(DFRD_REV_FLG))) {
                    if (ZYPCommonFunc.hasValue(allocAmt) && BigDecimal.ZERO.compareTo(totAllocAmt) != 0) {
                        allocPct = allocAmt.divide(totAllocAmt, ALLOC_PCT_SCL, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.TEN.multiply(BigDecimal.TEN));
                    }
                    revRecogAmt = (basePrcDealAmt.divide(dfrdAcctgRuleDurnAot, 2, BigDecimal.ROUND_HALF_UP)).multiply(allocPct.divide(BigDecimal.TEN.multiply(BigDecimal.TEN)));
                    dfrdRevAmt = (basePrcDealAmt.multiply((dfrdAcctgRuleDurnAot.subtract(BigDecimal.ONE)).divide(dfrdAcctgRuleDurnAot, 2, BigDecimal.ROUND_HALF_UP))).multiply(allocPct.divide(BigDecimal.TEN.multiply(BigDecimal.TEN)));
                } else {
                    if (ZYPCommonFunc.hasValue(allocAmt)) {
                        if (!basePrcDealAmt.setScale(ALLOC_PCT_SCL, BigDecimal.ROUND_HALF_UP).equals(totAllocAmt)) {
                            revRecogAmt = basePrcDealAmt.multiply(allocAmt).divide(totAllocAmt, ALLOC_PCT_SCL, BigDecimal.ROUND_HALF_UP);
                        } else {
                            revRecogAmt = allocAmt;
                        }
                    } else {
                        revRecogAmt = (basePrcDealAmt.multiply(allocPct)).divide(BigDecimal.TEN.multiply(BigDecimal.TEN));
                    }
                }
                // END 2018/04/12 M.Naito [QC#23378,MOD]
            } else if (USG_ACT.equals(rs.getString(FACT_CATG_CD)) && DS_BLLG_SCHD_STS.BILLED.equals(rs.getString(DS_BLLG_SCHD_STS_CD))) {
                BigDecimal mtrChrgDealAmt = rs.getBigDecimal(MTR_CHRG_DEAL_AMT);
                revRecogAmt = (mtrChrgDealAmt.multiply(allocPct)).divide(BigDecimal.TEN.multiply(BigDecimal.TEN));
            } else if (USG_EST.equals(rs.getString(FACT_CATG_CD)) && !MODE2.equals(this.estMode)) {
                BigDecimal bllgAmt = calcUsgEstAmt(rs);
                revAdcvAmt = (bllgAmt.multiply(allocPct)).divide(BigDecimal.TEN.multiply(BigDecimal.TEN));
            }
        }

        // set table message
        BigDecimal fctDlyContrRevFcstPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FCT_DLY_CONTR_REV_FCST_SQ);
        ZYPEZDItemValueSetter.setValue(tMsg.fctDlyContrRevFcstPk, fctDlyContrRevFcstPk);
        ZYPEZDItemValueSetter.setValue(tMsg.revAcctCd, revAcctCd);
        ZYPEZDItemValueSetter.setValue(tMsg.revRecogAmt, revRecogAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.revAdcvAmt, revAdcvAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.dfrdRevAmt, dfrdRevAmt);

    }

    /**
     * Calculate Usage Estimate Amount
     */
    private BigDecimal calcUsgEstAmt(ResultSet rs) throws SQLException {

        if (!ZYPCommonFunc.hasValue(rs.getBigDecimal(DS_CONTR_BLLG_MTR_PK))) {
            return BigDecimal.ZERO;
        }

        if (!ZYPCommonFunc.hasValue(rs.getString(BLLG_MTR_LB_CD))) {
            return BigDecimal.ZERO;
        }

        if (!ZYPCommonFunc.hasValue(rs.getBigDecimal(AFT_DECL_PNT_DIGIT_NUM))) {
            return BigDecimal.ZERO;
        }

        // get Billing Meter Info By DS_CONTR_BLLG_MTR
        String queryName = "getBillingMeterInfo";
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_DS_CONTR_BLLG_MTR_PK, rs.getBigDecimal(DS_CONTR_BLLG_MTR_PK));
        Map<String, Object> billMtrMap = (Map<String, Object>) ssmBatchClient.queryObject(queryName, queryParam);
        if (billMtrMap == null) {
            return BigDecimal.ZERO;
        }

        // get Summary Meter Read Count by SVC_ADCV_BY_SER
        if (ZYPCommonFunc.hasValue(rs.getBigDecimal(BLLG_CYCLE_MTH_AOT))) {
            queryParam.put(PARAM_BLLG_CYCLE_MTH_AOT, rs.getBigDecimal(BLLG_CYCLE_MTH_AOT));
        } else {
            queryParam.put(PARAM_BLLG_CYCLE_MTH_AOT, BigDecimal.ONE);
        }
        queryParam.put(PARAM_AVG_DATE, AVG_DATE);
        if (DS_CONTR_CATG.FLEET.equals(rs.getString(DS_CONTR_CATG_CD))) {
            queryName = "getAmtByFltUsgEst";
            queryParam.put(PARAM_DS_CONTR_PK, rs.getBigDecimal(DS_CONTR_PK));
            queryParam.put(PARAM_FLEET, DS_CONTR_DTL_TP.FLEET);
            queryParam.put(PARAM_BLLG_MTR_LB_CD, rs.getString(BLLG_MTR_LB_CD));
        } else {
            queryName = "getAmtByNoFltUsgEst";
        }

        Map<String, Object> sumMtrMap = (Map<String, Object>) ssmBatchClient.queryObject(queryName, queryParam);
        if (sumMtrMap == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal bllgAmt = BigDecimal.ZERO;
        BigDecimal mtrCnt = (BigDecimal) sumMtrMap.get(MTR_CNT);
        BigDecimal bllgMinCnt = (BigDecimal) billMtrMap.get(BLLG_MIN_CNT);
        BigDecimal bllgRollOverRatio = (BigDecimal) billMtrMap.get(BLLG_ROLL_OVER_RATIO);
        BigDecimal rollOverCnt = (BigDecimal) billMtrMap.get(ROLL_OVER_CNT);
        BigDecimal bllgFreeCopyCnt = (BigDecimal) billMtrMap.get(BLLG_FREE_COPY_CNT);
        BigDecimal bllgMinAmtRate = (BigDecimal) billMtrMap.get(BLLG_MIN_AMT_RATE);

        BigDecimal xsMtrCopyQty = rs.getBigDecimal(XS_MTR_COPY_QTY_01);
        BigDecimal xsMtrAmtRate = rs.getBigDecimal(XS_MTR_AMT_RATE_01);
        int digit = rs.getInt(AFT_DECL_PNT_DIGIT_NUM);
        boolean tierFlg = true;

        // Billing minimum count
        if (BigDecimal.ZERO.compareTo(bllgMinCnt) < 0) {
            if (mtrCnt.compareTo(bllgMinCnt) < 0) {
                mtrCnt = bllgMinCnt;
            }
            if (xsMtrCopyQty.compareTo(mtrCnt) < 0) {
                bllgAmt = ((mtrCnt.subtract(xsMtrCopyQty)).multiply(xsMtrAmtRate)).setScale(digit, BigDecimal.ROUND_HALF_UP);
            }
            tierFlg = false;

        // Roll Over
        } else if (BigDecimal.ZERO.compareTo(bllgRollOverRatio) < 0) {
            if (BigDecimal.ZERO.compareTo(mtrCnt) > 0) {
                mtrCnt = BigDecimal.ZERO;
            } else {
                mtrCnt = mtrCnt.subtract(rollOverCnt);
            }
            if (rollOverCnt.compareTo(mtrCnt) < 0) {
                bllgAmt = ((mtrCnt.subtract(xsMtrCopyQty)).multiply(xsMtrAmtRate)).setScale(digit, BigDecimal.ROUND_HALF_UP);
            }
            tierFlg = false;

        // Free Copy
        } else if (BigDecimal.ZERO.compareTo(bllgFreeCopyCnt) < 0) {
            if (xsMtrCopyQty.compareTo(mtrCnt) < 0 && bllgFreeCopyCnt.add(xsMtrCopyQty).compareTo(mtrCnt) < 0) {
                bllgAmt = ((mtrCnt.subtract(bllgFreeCopyCnt.add(xsMtrCopyQty))).multiply(xsMtrAmtRate)).setScale(digit, BigDecimal.ROUND_HALF_UP);
            }
            tierFlg = false;

        // Billing Minimum Amount
        } else if (BigDecimal.ZERO.compareTo(bllgMinAmtRate) < 0) {
            bllgAmt = ((mtrCnt.subtract(xsMtrCopyQty)).multiply(xsMtrAmtRate)).setScale(digit, BigDecimal.ROUND_HALF_UP);
            if (bllgMinAmtRate.compareTo(bllgAmt) > 0) {
                bllgAmt = bllgMinAmtRate;
            }
            tierFlg = false;
        }

        // Tier
        if (tierFlg) {
            queryParam = new HashMap<String, Object>();
            queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
            queryParam.put(PARAM_DS_CONTR_BLLG_SCHD_PK, rs.getBigDecimal(DS_CONTR_BLLG_SCHD_PK));
            List<Map<String, Object>> mtrCopyQtyList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMtrCopyQtyList", queryParam);

            if (mtrCopyQtyList.size() == 0) {
                return BigDecimal.ZERO;
            }

            // Point
            if (XS_CHRG_TP.POINT.equals((String) billMtrMap.get(XS_CHRG_TP_CD))) {
                for (Map<String, Object> mtrCopyQtyMap : mtrCopyQtyList) {
                    xsMtrAmtRate = (BigDecimal) mtrCopyQtyMap.get(XS_MTR_AMT_RATE);
                    xsMtrCopyQty = (BigDecimal) mtrCopyQtyMap.get(XS_MTR_COPY_QTY);
                    if (mtrCnt.compareTo(xsMtrCopyQty) > 0) {
                        break;
                    }
                }
                bllgAmt = ((mtrCnt.subtract(xsMtrCopyQty)).multiply(xsMtrAmtRate)).setScale(digit, BigDecimal.ROUND_HALF_UP);
            // Range
            } else {
                for (Map<String, Object> mtrCopyQtyMap : mtrCopyQtyList) {
                    xsMtrAmtRate = (BigDecimal) mtrCopyQtyMap.get(XS_MTR_AMT_RATE);
                    xsMtrCopyQty = (BigDecimal) mtrCopyQtyMap.get(XS_MTR_COPY_QTY);
                    if (mtrCnt.compareTo(xsMtrCopyQty) > 0) {
                        BigDecimal chrgCopyCnt = mtrCnt.subtract(xsMtrCopyQty);
                        BigDecimal chrgXsAmt = (xsMtrAmtRate.multiply(chrgCopyCnt)).setScale(digit, BigDecimal.ROUND_HALF_UP);
                        mtrCnt = mtrCnt.subtract(chrgCopyCnt);
                        bllgAmt = bllgAmt.add(chrgXsAmt);
                    }
                }
            }
        }

        if (BigDecimal.ZERO.compareTo(bllgAmt) > 0) {
            bllgAmt = BigDecimal.ZERO;
        }

        return bllgAmt;

    }

    @Override
    protected void termRoutine() {
        if (this.errRecCnt > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);
    }

}
