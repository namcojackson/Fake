/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB067001.constant;

import java.math.BigDecimal;

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
public class NSAB067001Constant {

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

    /** message Item: Commit Transaction Count. */
    public static final String MSG_ITEM_COMMIT_TRANCNT = "Commit Transaction Count";

    /** message Item: Batch Process Date. */
    public static final String MSG_ITEM_BATCH_PROC_DATE = "Batch Process Date";

    /** REV_RECOG_PROC_STS_CD TERMINATED */
    public static final String TERMINATED = "9";

    /** CONDITION EST MODE 2 */
    public static final String MODE2 = "02";

    /** CONDITION EST MODE 3 */
    public static final String MODE3 = "03";

    // START 2018/04/12 M.Naito [QC#23378,ADD]
    /** ALLOC_PCT_SCL */
    public static final int ALLOC_PCT_SCL = 2;
    // END 2018/04/12 M.Naito [QC#23378,ADD]

    /** Average Date Of One Month */
    public static final BigDecimal AVG_DATE = new BigDecimal("30.41");

    /** Forecast Type */
    /** . */
    public static final String BASE = "Base";
    /** . */
    public static final String USG_ACT = "Usage Actual";
    /** . */
    public static final String USG_EST = "Usage Estimated";
    /** . */
    public static final String DEFFERAL = "Deferral Recapture";
    /** . */
    public static final String RECOGNIZE = "Recognize";
    /** . */
    public static final String NOT_RECOGNIZE = "Not Recognize";

    /** Account Type */
    /** . */
    public static final String EQUIP = "EQUIPMENT";
    /** . */
    public static final String SVC = "SERVICE";
    /** . */
    public static final String SUP = "SUPPLIES";
    /** . */
    public static final String MISC1 = "MISC1";
    /** . */
    public static final String MISC2 = "MISC2";

    /** CONDITION CONSTANT GROUP ID */
    /** . */
    public static final String COND_FLT_MDL_ID = "NSAB0670_FLT_MDL_ID";
    /** . */
    public static final String COND_EST_MODE = "NSAB0670_EST_MODE";
    /** . */
    public static final String COND_GRP_EQP_ALLOC = "NSAB0670_EQUIP_ALLOC";
    /** . */
    public static final String COND_GRP_SVC_ALLOC = "NSAB0670_SVC_ALLOC";
    /** . */
    public static final String COND_GRP_SUP_ALLOC = "NSAB0670_SPLY_ALLOC";
    /** . */
    public static final String COND_GRP_MI1_ALLOC = "NSAB0670_MISC1_ALLOC";
    /** . */
    public static final String COND_GRP_MI2_ALLOC = "NSAB0670_MISC2_ALLOC";
    /** . */
    public static final String COND_DFRD_ACCTG_RULE_DURN_AOT = "NSAB0670_DFRD_DURN";

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";
    /** . */
    public static final String PARAM_MODE = "estMode";
    /** . */
    public static final String PARAM_BASE = "base";
    /** . */
    public static final String PARAM_USG_ACT = "usgAct";
    /** . */
    public static final String PARAM_USG_EST = "usgEst";
    /** . */
    public static final String PARAM_DEFFERAL = "defferal";
    /** . */
    public static final String PARAM_DEFFERED = "deffered";
    /** . */
    public static final String PARAM_OPEN = "open";
    /** . */
    public static final String PARAM_BILLED = "billed";
    /** . */
    public static final String PARAM_CLOSE = "close";
    /** . */
    public static final String PARAM_FLT = "flt";
    /** . */
    public static final String PARAM_FLEET = "fleet";
    /** . */
    public static final String PARAM_AGG = "agg";
    /** . */
    public static final String PARAM_SVC_INV_CHRG_TP_CD = "svcInvChrgTpCd";
    /** . */
    public static final String PARAM_ARREARS = "arrears";
    /** . */
    public static final String PARAM_EQP_ALLOC = "equipAlloc";
    /** . */
    public static final String PARAM_SVC_ALLOC = "serviceAlloc";
    /** . */
    public static final String PARAM_SUP_ALLOC = "suplyAlloc";
    /** . */
    public static final String PARAM_MI1_ALLOC = "misc1";
    /** . */
    public static final String PARAM_MI2_ALLOC = "misc2";
    // START 2018/04/12 M.Naito [QC#23378,ADD]
    /** . */
    public static final String PARAM_DS_CONTR_DTL_PK = "dsContrDtlPk";
    // END 2018/04/12 M.Naito [QC#23378,ADD]
    /** . */
    public static final String PARAM_DS_CONTR_PK = "dsContrPk";
    /** . */
    public static final String PARAM_DS_CONTR_BLLG_SCHD_PK = "dsContrBllgSchdPk";
    /** . */
    public static final String PARAM_DS_CONTR_BLLG_MTR_PK = "dsContrBllgMtrPk";
    /** . */
    public static final String PARAM_DS_INV_SLS_CR_PK = "dsInvSlsCrPk";
    /** . */
    public static final String PARAM_BLLG_MTR_LB_CD = "bllgMtrLbCd";
    /** . */
    public static final String PARAM_SVC_ALLOC_TP_CD = "svcAllocTpCd";
    /** . */
    public static final String PARAM_TERMINATED = "terminated";
    /** . */
    public static final String PARAM_RECOGNIZE = "recognize";
    /** . */
    public static final String PARAM_NOT_RECOGNIZE = "notRecognize";
    /** . */
    public static final String PARAM_BLLG_CYCLE_MTH_AOT = "bcmAot";
    /** . */
    public static final String PARAM_AVG_DATE = "avgDt";

    /** DB COLUMN NAME */
    /** . */
    public static final String FACT_CATG_CD = "FACT_CATG_CD";
    /** . */
    public static final String FACT_CRAT_DT = "FACT_CRAT_DT";
    /** . */
    public static final String SVC_ALLOC_TP_CD = "SVC_ALLOC_TP_CD";
    /** . */
    public static final String REV_ACCT_CD = "REV_ACCT_CD";
    /** . */
    public static final String DS_CONTR_PK = "DS_CONTR_PK";
    /** . */
    public static final String DS_CONTR_NUM = "DS_CONTR_NUM";
    /** . */
    public static final String DS_CONTR_DTL_TP_CD = "DS_CONTR_DTL_TP_CD";
    /** . */
    public static final String SVC_LINE_BIZ_CD = "SVC_LINE_BIZ_CD";
    /** . */
    public static final String SVC_CONTR_BR_CD = "SVC_CONTR_BR_CD";
    /** . */
    public static final String DS_CONTR_CATG_CD = "DS_CONTR_CATG_CD";
    /** . */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
    /** . */
    public static final String SER_NUM = "SER_NUM";
    /** . */
    public static final String MDL_ID = "MDL_ID";
    /** . */
    public static final String T_MDL_NM = "T_MDL_NM";
    /** . */
    public static final String BLLG_SCHD_FROM_DT = "BLLG_SCHD_FROM_DT";
    /** . */
    public static final String BLLG_SCHD_THRU_DT = "BLLG_SCHD_THRU_DT";
    /** . */
    public static final String BLLG_CYCLE_CD = "BLLG_CYCLE_CD";
    /** . */
    public static final String BLLG_CYCLE_DESC_TXT = "BLLG_CYCLE_DESC_TXT";
    /** . */
    public static final String BLLG_CYCLE_MTH_AOT = "BLLG_CYCLE_MTH_AOT";
    /** . */
    public static final String BLLG_TMG_TP_CD = "BLLG_TMG_TP_CD";
    /** . */
    public static final String BLLG_TMG_TP_DESC_TXT = "BLLG_TMG_TP_DESC_TXT";
    /** . */
    public static final String BLLG_MTR_LB_CD = "BLLG_MTR_LB_CD";
    /** . */
    public static final String MTR_LB_DESC_TXT = "MTR_LB_DESC_TXT";
    /** . */
    public static final String BLLG_COPY_QTY = "BLLG_COPY_QTY";
    /** . */
    public static final String XS_MTR_COPY_QTY = "XS_MTR_COPY_QTY";
    /** . */
    public static final String XS_MTR_AMT_RATE = "XS_MTR_AMT_RATE";
    /** . */
    public static final String XS_MTR_COPY_QTY_01 = "XS_MTR_COPY_QTY_01";
    /** . */
    public static final String XS_MTR_AMT_RATE_01 = "XS_MTR_AMT_RATE_01";
    /** . */
    public static final String XS_MTR_COPY_QTY_02 = "XS_MTR_COPY_QTY_02";
    /** . */
    public static final String XS_MTR_AMT_RATE_02 = "XS_MTR_AMT_RATE_02";
    /** . */
    public static final String XS_MTR_COPY_QTY_03 = "XS_MTR_COPY_QTY_03";
    /** . */
    public static final String XS_MTR_AMT_RATE_03 = "XS_MTR_AMT_RATE_03";
    /** . */
    public static final String XS_MTR_COPY_QTY_04 = "XS_MTR_COPY_QTY_04";
    /** . */
    public static final String XS_MTR_AMT_RATE_04 = "XS_MTR_AMT_RATE_04";
    /** . */
    public static final String DS_CONTR_CTRL_STS_CD = "DS_CONTR_CTRL_STS_CD";
    /** . */
    public static final String DS_CONTR_CTRL_STS_NM = "DS_CONTR_CTRL_STS_NM";
    /** . */
    public static final String CONTR_VRSN_EFF_FROM_DT = "CONTR_VRSN_EFF_FROM_DT";
    /** . */
    public static final String CONTR_VRSN_EFF_THRU_DT = "CONTR_VRSN_EFF_THRU_DT";
    /** . */
    public static final String DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";
    /** . */
    public static final String DS_CONTR_CTRL_DTL_STS_CD = "DS_CONTR_CTRL_DTL_STS_CD";
    /** . */
    public static final String DS_CONTR_CTRL_DTL_STS_NM = "DS_CONTR_CTRL_DTL_STS_NM";
    /** . */
    public static final String CONTR_EFF_FROM_DT = "CONTR_EFF_FROM_DT";
    /** . */
    public static final String CONTR_EFF_THRU_DT = "CONTR_EFF_THRU_DT";
    /** . */
    public static final String CONTR_CLO_DT = "CONTR_CLO_DT";
    /** . */
    public static final String DS_CONTR_CRAT_DT = "DS_CONTR_CRAT_DT";
    /** . */
    public static final String DS_CONTR_CRAT_PSN_CD = "DS_CONTR_CRAT_PSN_CD";
    /** . */
    public static final String DS_CONTR_LAST_UPD_DT = "DS_CONTR_LAST_UPD_DT";
    /** . */
    public static final String DS_CONTR_LAST_UPD_PSN_CD = "DS_CONTR_LAST_UPD_PSN_CD";
    /** . */
    public static final String REV_RECOG_AMT = "REV_RECOG_AMT";
    /** . */
    public static final String DFRD_REV_AMT = "DFRD_REV_AMT";
    /** . */
    public static final String BLLG_REV_DT = "BLLG_REV_DT";
    /** . */
    public static final String EQP_ALLOC = "EQP_ALLOC";
    /** . */
    public static final String SVC_ALLOC = "SVC_ALLOC";
    /** . */
    public static final String SUP_ALLOC = "SUP_ALLOC";
    /** . */
    // START 2018/04/12 M.Naito [QC#23378,ADD]
    public static final String EQP_AMT_ALLOC = "EQP_AMT_ALLOC";
    /** . */
    public static final String SVC_AMT_ALLOC = "SVC_AMT_ALLOC";
    /** . */
    public static final String SUP_AMT_ALLOC = "SUP_AMT_ALLOC";
    // END 2018/04/12 M.Naito [QC#23378,ADD]
    /** . */
    public static final String BASE_CONTR_PK = "BASE_CONTR_PK";
    /** . */
    public static final String BASE_DS_CONTR_DTL_PK = "BASE_DS_CONTR_DTL_PK";
    /** . */
    public static final String SVC_INV_CHRG_TP_CD = "SVC_INV_CHRG_TP_CD";
    /** . */
    public static final String BASE_EQP_ALLOC = "BASE_EQP_ALLOC";
    /** . */
    public static final String BASE_SVC_ALLOC = "BASE_SVC_ALLOC";
    /** . */
    public static final String BASE_SUP_ALLOC = "BASE_SUP_ALLOC";
 // START 2018/04/12 M.Naito [QC#23378,ADD]
    /** . */
    public static final String BASE_EQP_AMT_ALLOC = "BASE_EQP_AMT_ALLOC";
    /** . */
    public static final String BASE_SVC_AMT_ALLOC = "BASE_SVC_AMT_ALLOC";
    /** . */
    public static final String BASE_SUP_AMT_ALLOC = "BASE_SUP_AMT_ALLOC";
 // END 2018/04/12 M.Naito [QC#23378,ADD]
    /** . */
    public static final String DTL_EQP_ALLOC = "DTL_EQP_ALLOC";
    /** . */
    public static final String DTL_SVC_ALLOC = "DTL_SVC_ALLOC";
    /** . */
    public static final String DTL_SUP_ALLOC = "DTL_SUP_ALLOC";
    /** . */
    public static final String HEAD_EQP_ALLOC = "HEAD_EQP_ALLOC";
    /** . */
    public static final String HEAD_SVC_ALLOC = "HEAD_SVC_ALLOC";
    /** . */
    public static final String HEAD_SUP_ALLOC = "HEAD_SUP_ALLOC";
    /** . */
    public static final String AJE_MDL_ID = "AJE_MDL_ID";
    /** . */
    public static final String MDL_EQP_ALLOC = "MDL_EQP_ALLOC";
    /** . */
    public static final String MDL_SVC_ALLOC = "MDL_SVC_ALLOC";
    /** . */
    public static final String MDL_SUP_ALLOC = "MDL_SUP_ALLOC";
    /** . */
    public static final String NON_MDL_ALLOC = "NON_MDL_ALLOC";
    /** . */
    public static final String NON_MDL_EQP_ALLOC = "NON_MDL_EQP_ALLOC";
    /** . */
    public static final String NON_MDL_SVC_ALLOC = "NON_MDL_SVC_ALLOC";
    /** . */
    public static final String NON_MDL_SUP_ALLOC = "NON_MDL_SUP_ALLOC";
    /** . */
    public static final String DFRD_REV_FLG = "DFRD_REV_FLG";
    /** . */
    public static final String DFRD_ACCTG_RULE_DURN_AOT = "DFRD_ACCTG_RULE_DURN_AOT";
    /** . */
    public static final String DS_CONTR_BLLG_SCHD_PK = "DS_CONTR_BLLG_SCHD_PK";
    /** . */
    public static final String DS_INV_SLS_CR_PK = "DS_INV_SLS_CR_PK";
    /** . */
    public static final String DS_BLLG_SCHD_STS_CD = "DS_BLLG_SCHD_STS_CD";
    /** . */
    public static final String BASE_PRC_DEAL_AMT = "BASE_PRC_DEAL_AMT";
    /** . */
    public static final String EQP_SUM = "EQP_SUM";
    /** . */
    public static final String SVC_SUM = "SVC_SUM";
    /** . */
    public static final String SUP_SUM = "SUP_SUM";
    /** . */
    public static final String MI1_SUM = "MI1_SUM";
    /** . */
    public static final String MI2_SUM = "MI2_SUM";
    /** . */
    public static final String EQP_SUM_N = "EQP_SUM_N";
    /** . */
    public static final String SVC_SUM_N = "SVC_SUM_N";
    /** . */
    public static final String SUP_SUM_N = "SUP_SUM_N";
    /** . */
    public static final String EQP_SUM_DF = "EQP_SUM_DF";
    /** . */
    public static final String SVC_SUM_DF = "SVC_SUM_DF";
    /** . */
    public static final String SUP_SUM_DF = "SUP_SUM_DF";
    /** . */
    public static final String MI1_SUM_DF = "MI1_SUM_DF";
    /** . */
    public static final String MI2_SUM_DF = "MI2_SUM_DF";
    /** . */
    public static final String REV_RECOG_AMT_EQP = "REV_RECOG_AMT_EQP";
    /** . */
    public static final String REV_RECOG_AMT_SVC = "REV_RECOG_AMT_SVC";
    /** . */
    public static final String REV_RECOG_AMT_SUP = "REV_RECOG_AMT_SUP";
    /** . */
    public static final String REV_RECOG_AMT_MI1 = "REV_RECOG_AMT_MI1";
    /** . */
    public static final String REV_RECOG_AMT_MI2 = "REV_RECOG_AMT_MI2";
    /** . */
    public static final String DFRD_REV_AMT_EQP = "DFRD_REV_AMT_EQP";
    /** . */
    public static final String DFRD_REV_AMT_SVC = "DFRD_REV_AMT_SVC";
    /** . */
    public static final String DFRD_REV_AMT_SUP = "DFRD_REV_AMT_SUP";
    /** . */
    public static final String DFRD_REV_AMT_MI1 = "DFRD_REV_AMT_MI1";
    /** . */
    public static final String DFRD_REV_AMT_MI2 = "DFRD_REV_AMT_MI2";
    /** . */
    public static final String MTR_CHRG_DEAL_AMT = "MTR_CHRG_DEAL_AMT";
    /** . */
    public static final String DS_CONTR_BLLG_MTR_PK = "DS_CONTR_BLLG_MTR_PK";
    /** . */
    public static final String MTR_CNT = "MTR_CNT";
    /** . */
    public static final String BLLG_MIN_CNT = "BLLG_MIN_CNT";
    /** . */
    public static final String BLLG_ROLL_OVER_RATIO = "BLLG_ROLL_OVER_RATIO";
    /** . */
    public static final String ROLL_OVER_CNT = "ROLL_OVER_CNT";
    /** . */
    public static final String BLLG_FREE_COPY_CNT = "BLLG_FREE_COPY_CNT";
    /** . */
    public static final String BLLG_MIN_AMT_RATE = "BLLG_MIN_AMT_RATE";
    /** . */
    public static final String AFT_DECL_PNT_DIGIT_NUM = "AFT_DECL_PNT_DIGIT_NUM";
    /** . */
    public static final String XS_CHRG_TP_CD = "XS_CHRG_TP_CD";

}
