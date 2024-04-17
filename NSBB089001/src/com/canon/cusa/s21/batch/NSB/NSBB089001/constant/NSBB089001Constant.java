/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB089001.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Monthly FSR Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/01/2016   CITS            T.Kikuhara      Create          N/A
 * 09/26/2018   Fujitsu         W.Honda         Update          QC#28381
 *</pre>
 */
public class NSBB089001Constant {

    /** fetchSize */
    public static final int FETCH_SIZE = 1000;

    /** commitCount */
    public static final int DEFAULT_COMMIT_COUNT = 1000;

    /** [@] has no value. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";

    /** message Item: GlobalCompanyCode. */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Batch Process Date. */
    public static final String MSG_ITEM_BATCH_PROC_DATE = "Batch Process Date";

    /** message Item: DWH Target Year Month. */
    public static final String MSG_ITEM_DWH_TRGT_YR_MTH = "DWH Target Year Month";

    /** FIRST VISIT NUM */
    public static final String FIRST_VISIT_NUM = "01";

    /** ONE VISIT COUNT */
    public static final int COUNT_ONE = 1;

    /** FROM DATE TIME FLG */
    public static final int FROM_DTM_FLG = 1;

    /** THRU DATE TIME FLG */
    public static final int THRU_DTM_FLG = 2;

    /** Miri Second To Second */
    public static final int MIRI_SEC_TO_SEC = 1000;

    /** Time Adjust Number */
    public static final BigDecimal TIME_ADJ_VAL = new BigDecimal("60");

    /** YYYYMMDDHHMISS LENGTH */
    public static final int YMDTS_LEN = 14;

    /** YYYYMMDD LENGTH */
    public static final int YMD_LEN = 8;

    /** YYYYMM LENGTH */
    public static final int YM_LEN = 6;

    /** Second decimal place */
    public static final int INT_SECONDS = 2;

    /** PICK UP SUPPL TASK CD */
    public static final String PICK_SUPPL_TASK = "5I%";

    /** After Hours Working Program */
    public static final String AHS_ATTR = "18";

    /** Response Time Measurement Period */
    public static final String RSP_TIME = "32";

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";
    /** . */
    public static final String PARAM_TRGT_YR_MTH = "trgtYrMth";
    /** . */
    public static final String PARAM_SAVED = "saved";
    /** . */
    public static final String PARAM_COMPLETED = "comleted";
    /** . */
    public static final String PARAM_PENDING_CHARGE = "pendingCharge";
    /** . */
    public static final String PARAM_CLOSED = "closed";
    /** . */
    public static final String PARAM_CANCELLED = "cancelled";
    /** . */
    public static final String PARAM_DEBRIEF_ERROR = "debriefError";
    /** . */
    public static final String PARAM_FIRST_NUM = "firstNum";
    // Mod Start 2018/09/26 QC#28381
//    /** . */
//    public static final String PARAM_PHONE = "phone";
//    /** . */
//    public static final String PARAM_MDS_PHONE = "mdsPhone";
//    /** . */
//    public static final String PARAM_PHONE_2 = "mdsPhone2";
    /** . */
    public static final String PARAM_PHONE_FIX_DISPATCHER = "phoneFixDispatch";
    /** . */
    public static final String PARAM_PHONE_FIX_TECHNICIAN = "phoneFixTech";
    /** . */
    public static final String PARAM_AHS_PHONE_FIX_DISPATCHER = "ahsPhoneFixDispatch";
    /** . */
    public static final String PARAM_AHS_PHONE_FIX_TECHNICIAN = "ahsPhoneFixTech";
    // Mod End 2018/09/26 QC#28381
    /** . */
    public static final String PARAM_PART_CALL = "partCall";
    /** . */
    public static final String PARAM_HIGH_PART = "highPart";
    /** . */
    public static final String PARAM_COUNT_ONE = "cntOne";
    /** . */
    public static final String PARAM_LABOR = "labor";
    /** . */
    public static final String PARAM_TRAVEL = "travel";
    /** . */
    public static final String PARAM_PICK_SUPPL_TASK = "pickSupplTask";
    /** . */
    public static final String PARAM_MEAL_2 = "meal2";
    /** . */
    public static final String PARAM_LABOR_RATE = "laborRate";
    /** . */
    public static final String PARAM_ESS = "ess";
    /** . */
    public static final String PARAM_AHS_ATTR = "ahsAttr";
    /** . */
    public static final String PARAM_DEFAULT_SVC_TIME = "defaultSvcTime";
    /** . */
    public static final String PARAM_SVC_TIME = "svcTime";
    /** . */
    public static final String PARAM_RSP_TIME = "rspTime";
    /** . */
    public static final String PARAM_YMDTS_LEN = "ymdtsLen";
    /** . */
    public static final String PARAM_YMD_LEN = "ymdLen";
    /** . */
    public static final String PARAM_YM_LEN = "ymLen";
    /** . */
    public static final String PARAM_TIME_ADJ = "timeAdj";

    /** DB COLUMN NAME */
    /** . */
    public static final String DWH_TRGT_YR_MTH = "DWH_TRGT_YR_MTH";
    /** . */
    public static final String FSR_NUM = "FSR_NUM";
    /** . */
    public static final String FSR_OPEN_FLG = "FSR_OPEN_FLG";
    /** . */
    public static final String FSR_TP_CD = "FSR_TP_CD";
    /** . */
    public static final String DS_SVC_CALL_TP_CD = "DS_SVC_CALL_TP_CD";
    /** . */
    public static final String SVC_CALL_SRC_TP_CD = "SVC_CALL_SRC_TP_CD";
    /** . */
    public static final String SVC_CALL_AVOID_CD = "SVC_CALL_AVOID_CD";
    /** . */
    public static final String SVC_CALL_RQST_OWNR_TOC_CD = "SVC_CALL_RQST_OWNR_TOC_CD";
    /** . */
    public static final String SVC_CALL_INCDT_DT = "SVC_CALL_INCDT_DT";
    /** . */
    public static final String SVC_CALL_INCDT_TM = "SVC_CALL_INCDT_TM";
    /** . */
    public static final String FSR_CRAT_DT = "FSR_CRAT_DT";
    /** . */
    public static final String FSR_CRAT_TM = "FSR_CRAT_TM";
    /** . */
    public static final String FSR_CPLT_DT = "FSR_CPLT_DT";
    /** . */
    public static final String FSR_CPLT_TM = "FSR_CPLT_TM";
    /** . */
    public static final String FSR_CPLT_DTM = "FSR_CPLT_DTM";
    /** . */
    public static final String FSR_CLO_DT = "FSR_CLO_DT";
    /** . */
    public static final String FSR_CLO_TM = "FSR_CLO_TM";
    /** . */
    public static final String SVC_INV_NUM = "SVC_INV_NUM";
    /** . */
    public static final String INV_DT = "INV_DT";
    /** . */
    public static final String TRGT_SVC_RSP_AOT = "TRGT_SVC_RSP_AOT";
    /** . */
    public static final String SVC_RSP_AOT = "SVC_RSP_AOT";
    /** . */
    public static final String SVC_RSP_DIFF_AOT = "SVC_RSP_DIFF_AOT";
    /** . */
    public static final String TRGT_SVC_RSTR_AOT = "TRGT_SVC_RSTR_AOT";
    /** . */
    public static final String SVC_RSTR_AOT = "SVC_RSTR_AOT";
    /** . */
    public static final String SVC_RSTR_DIFF_AOT = "SVC_RSTR_DIFF_AOT";
    /** . */
    public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";
    /** . */
    public static final String MDL_ID = "MDL_ID";
    /** . */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
    /** . */
    public static final String SER_NUM = "SER_NUM";
    /** . */
    public static final String MDSE_CD = "MDSE_CD";
    /** . */
    public static final String MDL_GRP_ID = "MDL_GRP_ID";
    /** . */
    public static final String TM_AND_MAT_FLG = "TM_AND_MAT_FLG";
    /** . */
    public static final String CUR_LOC_ACCT_NUM = "CUR_LOC_ACCT_NUM";
    /** . */
    public static final String CUR_LOC_ACCT_NM = "CUR_LOC_ACCT_NM";
    /** . */
    public static final String DS_ACCT_GRP_CD = "DS_ACCT_GRP_CD";
    /** . */
    public static final String DS_ACCT_GRP_NM = "DS_ACCT_GRP_NM";
    /** . */
    public static final String CUR_LOC_NUM = "CUR_LOC_NUM";
    /** . */
    public static final String IND_CUR_LOC_NUM = "IND_CUR_LOC_NUM";
    /** . */
    public static final String DS_KEY_ACCT_FLG = "DS_KEY_ACCT_FLG";
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
    public static final String ISTL_DT = "ISTL_DT";
    /** . */
    public static final String MACH_DOWN_AOT = "MACH_DOWN_AOT";
    /** . */
    public static final String ERL_START_TS = "ERL_START_TS";
    /** . */
    public static final String PBLM_SMRY_TXT = "PBLM_SMRY_TXT";
    /** . */
    public static final String SVC_PBLM_TP_CD = "SVC_PBLM_TP_CD";
    /** . */
    public static final String SVC_PBLM_LOC_TP_CD = "SVC_PBLM_LOC_TP_CD";
    /** . */
    public static final String SVC_PBLM_RSN_TP_CD = "SVC_PBLM_RSN_TP_CD";
    /** . */
    public static final String SVC_PBLM_CRCT_TP_CD = "SVC_PBLM_CRCT_TP_CD";
    /** . */
    public static final String TECH_CD = "TECH_CD";
    /** . */
    public static final String FSR_VISIT_ARV_DT = "FSR_VISIT_ARV_DT";
    /** . */
    public static final String FSR_VISIT_ARV_TM = "FSR_VISIT_ARV_TM";
    /** . */
    public static final String FSR_TOT_VISIT_CNT = "FSR_TOT_VISIT_CNT";
    /** . */
    public static final String FIRST_VISIT_CPLT_FLG = "FIRST_VISIT_CPLT_FLG";
    /** . */
    public static final String PRT_RSCHD_FLG = "PRT_RSCHD_FLG";
    /** . */
    public static final String RCLL_FSR_FLG = "RCLL_FSR_FLG";
    /** . */
    public static final String FSR_TOT_TRVL_AOT = "FSR_TOT_TRVL_AOT";
    /** . */
    public static final String FSR_TOT_LBOR_AOT = "FSR_TOT_LBOR_AOT";
    /** . */
    public static final String FSR_TOT_ITRPT_AOT = "FSR_TOT_ITRPT_AOT";
    /** . */
    public static final String SVC_AVG_TRVL_AOT = "SVC_AVG_TRVL_AOT";
    /** . */
    public static final String SVC_LBOR_COST_UNIT_AMT = "SVC_LBOR_COST_UNIT_AMT";
    /** . */
    public static final String FSR_TOT_TRVL_COST_AMT = "FSR_TOT_TRVL_COST_AMT";
    /** . */
    public static final String FSR_TOT_LBOR_COST_AMT = "FSR_TOT_LBOR_COST_AMT";
    /** . */
    public static final String FSR_TOT_ITRPT_COST_AMT = "FSR_TOT_ITRPT_COST_AMT";
    /** . */
    public static final String FSR_TOT_PRT_COST_AMT = "FSR_TOT_PRT_COST_AMT";
    /** . */
    public static final String FSR_TOT_COST_AMT = "FSR_TOT_COST_AMT";
    /** . */
    public static final String SVC_LBOR_DEAL_AMT = "SVC_LBOR_DEAL_AMT";
    /** . */
    public static final String SVC_LBOR_FUNC_AMT = "SVC_LBOR_FUNC_AMT";
    /** . */
    public static final String SVC_LBOR_DEAL_DISC_AMT = "SVC_LBOR_DEAL_DISC_AMT";
    /** . */
    public static final String SVC_LBOR_FUNC_DISC_AMT = "SVC_LBOR_FUNC_DISC_AMT";
    /** . */
    public static final String SVC_TRVL_DEAL_AMT = "SVC_TRVL_DEAL_AMT";
    /** . */
    public static final String SVC_TRVL_FUNC_AMT = "SVC_TRVL_FUNC_AMT";
    /** . */
    public static final String SVC_TRVL_DEAL_DISC_AMT = "SVC_TRVL_DEAL_DISC_AMT";
    /** . */
    public static final String SVC_TRVL_FUNC_DISC_AMT = "SVC_TRVL_FUNC_DISC_AMT";
    /** . */
    public static final String SVC_PRT_DEAL_AMT = "SVC_PRT_DEAL_AMT";
    /** . */
    public static final String SVC_PRT_FUNC_AMT = "SVC_PRT_FUNC_AMT";
    /** . */
    public static final String SVC_PRT_DEAL_DISC_AMT = "SVC_PRT_DEAL_DISC_AMT";
    /** . */
    public static final String SVC_PRT_FUNC_DISC_AMT = "SVC_PRT_FUNC_DISC_AMT";
    /** . */
    public static final String ORIG_INV_CCY_CD = "ORIG_INV_CCY_CD";
    /** . */
    public static final String INV_CCY_CD = "INV_CCY_CD";
    /** . */
    public static final String CPLT_BY_PHO_FIX_FLG = "CPLT_BY_PHO_FIX_FLG";
    /** . */
    public static final String TOT_READ_MTR_CNT = "TOT_READ_MTR_CNT";
    /** . */
    public static final String BW_READ_MTR_CNT = "BW_READ_MTR_CNT";
    /** . */
    public static final String COLOR_READ_MTR_CNT = "COLOR_READ_MTR_CNT";
    /** . */
    public static final String MTR_READ_DT = "MTR_READ_DT";
    /** . */
    public static final String MACH_DOWN_FLG = "MACH_DOWN_FLG";
    /** . */
    public static final String LABOR_RATE = "LABOR_RATE";
    /** . */
    public static final String RPT_COND_CONST_VAL_NUM_01 = "RPT_COND_CONST_VAL_NUM_01";
    /** . */
    public static final String RPT_COND_CONST_VAL_NUM_02 = "RPT_COND_CONST_VAL_NUM_02";
    /** . */
    public static final String AHS_CD = "AHS_CD";
    /** . */
    public static final String SVC_LINE_BIZ_CD = "SVC_LINE_BIZ_CD";
    /** . */
    public static final String SVC_BY_LINE_BIZ_TP_CD = "SVC_BY_LINE_BIZ_TP_CD";
    /** . */
    public static final String SUN_FROM = "SUN_FROM";
    /** . */
    public static final String SUN_THRU = "SUN_THRU";
    /** . */
    public static final String MON_FROM = "MON_FROM";
    /** . */
    public static final String MON_THRU = "MON_THRU";
    /** . */
    public static final String TUE_FROM = "TUE_FROM";
    /** . */
    public static final String TUE_THRU = "TUE_THRU";
    /** . */
    public static final String WED_FROM = "WED_FROM";
    /** . */
    public static final String WED_THRU = "WED_THRU";
    /** . */
    public static final String THU_FROM = "THU_FROM";
    /** . */
    public static final String THU_THRU = "THU_THRU";
    /** . */
    public static final String FRI_FROM = "FRI_FROM";
    /** . */
    public static final String FRI_THRU = "FRI_THRU";
    /** . */
    public static final String SAT_FROM = "SAT_FROM";
    /** . */
    public static final String SAT_THRU = "SAT_THRU";
    /** . */
    public static final String DEFAULT_SERVICE_TIME = "DEFAULT_SERVICE_TIME";
    /** . */
    public static final String SERVICE_TIME = "SERVICE_TIME";
}
