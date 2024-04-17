/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB090001.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Monthly FSR Visit Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/22/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NSBB090001Constant {

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

    /** Time Adjust Number */
    public static final BigDecimal TIME_ADJ_VAL = new BigDecimal("60");

    /** YYYYMMDDHHMISS LENGTH */
    public static final int YMDTS_LEN = 14;

    /** YYYYMM LENGTH */
    public static final int YM_LEN = 6;

    /** Second decimal place */
    public static final int INT_SECONDS = 2;

    /** PICK UP SUPPL TASK CD */
    public static final String PICK_SUPPL_TASK = "5I%";

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";
    /** . */
    public static final String PARAM_TRGT_YR_MTH = "trgtYrMth";
    /** . */
    public static final String PARAM_LABOR = "labor";
    /** . */
    public static final String PARAM_TRAVEL = "travel";
    /** . */
    public static final String PARAM_MODIFICATION = "modification";
    /** . */
    public static final String PARAM_PICK_SUPPL_TASK = "pickSupplTask";
    /** . */
    public static final String PARAM_MEAL_2 = "meal2";
    /** . */
    public static final String PARAM_LABOR_RATE = "laborRate";
    /** . */
    public static final String PARAM_YMDTS_LEN = "ymdtsLen";
    /** . */
    public static final String PARAM_YM_LEN = "ymLen";
    /** . */
    public static final String PARAM_SEC_PERIOD = "secPeriod";
    /** . */
    public static final String PARAM_TIME_ADJ = "timeAdj";

    /** DB COLUMN NAME */
    /** . */
    public static final String DWH_TRGT_YR_MTH = "DWH_TRGT_YR_MTH";
    /** . */
    public static final String FSR_NUM = "FSR_NUM";
    /** . */
    public static final String FSR_TP_CD = "FSR_TP_CD";
    /** . */
    public static final String FSR_CRAT_DT = "FSR_CRAT_DT";
    /** . */
    public static final String FSR_CRAT_TM = "FSR_CRAT_TM";
    /** . */
    public static final String FSR_CPLT_DT = "FSR_CPLT_DT";
    /** . */
    public static final String FSR_CPLT_TM = "FSR_CPLT_TM";
    /** . */
    public static final String SVC_TASK_NUM = "SVC_TASK_NUM";
    /** . */
    public static final String SVC_TASK_STS_CD = "SVC_TASK_STS_CD";
    /** . */
    public static final String DS_SVC_CALL_TP_CD = "DS_SVC_CALL_TP_CD";
    /** . */
    public static final String PHO_FIX_FLG = "PHO_FIX_FLG";
    /** . */
    public static final String ERL_START_TS = "ERL_START_TS";
    /** . */
    public static final String FSR_VISIT_NUM = "FSR_VISIT_NUM";
    /** . */
    public static final String PREV_FSR_VISIT_NUM = "PREV_FSR_VISIT_NUM";
    /** . */
    public static final String NEXT_FSR_VISIT_NUM = "NEXT_FSR_VISIT_NUM";
    /** . */
    public static final String NEXT_VISIT_SVC_CALL_TP_CD = "NEXT_VISIT_SVC_CALL_TP_CD";
    /** . */
    public static final String SVC_SUPPL_TASK_NUM = "SVC_SUPPL_TASK_NUM";
    /** . */
    public static final String SVC_SUPPL_TASK_TP_CD = "SVC_SUPPL_TASK_TP_CD";
    /** . */
    public static final String TECH_CD = "TECH_CD";
    /** . */
    public static final String FLD_SVC_MGR_CD = "FLD_SVC_MGR_CD";
    /** . */
    public static final String FIRST_VISIT_FLG = "FIRST_VISIT_FLG";
    /** . */
    public static final String FIRST_VISIT_CPLT_FLG = "FIRST_VISIT_CPLT_FLG";
    /** . */
    public static final String TSS_FOLL_UP_FLG = "TSS_FOLL_UP_FLG";
    /** . */
    public static final String MOD_EVENT_CNT = "MOD_EVENT_CNT";
    /** . */
    public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";
    /** . */
    public static final String MDL_ID = "MDL_ID";
    /** . */
    public static final String RCLL_INTVL_DAYS_AOT = "RCLL_INTVL_DAYS_AOT";
    /** . */
    public static final String RCLL_COPY_VOL_CNT = "RCLL_COPY_VOL_CNT";
    /** . */
    public static final String RCLL_GLBL_INTVL_DAYS_AOT = "RCLL_GLBL_INTVL_DAYS_AOT";
    /** . */
    public static final String RCLL_GLBL_COPY_VOL_CNT = "RCLL_GLBL_COPY_VOL_CNT";
    /** . */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
    /** . */
    public static final String SER_NUM = "SER_NUM";
    /** . */
    public static final String MDSE_CD = "MDSE_CD";
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
    public static final String TECH_SCHD_FROM_DT = "TECH_SCHD_FROM_DT";
    /** . */
    public static final String TECH_SCHD_FROM_TM = "TECH_SCHD_FROM_TM";
    /** . */
    public static final String TECH_SCHD_TO_DT = "TECH_SCHD_TO_DT";
    /** . */
    public static final String TECH_SCHD_TO_TM = "TECH_SCHD_TO_TM";
    /** . */
    public static final String TECH_SCHD_TZ = "TECH_SCHD_TZ";
    /** . */
    public static final String FSR_VISIT_SCHD_DT = "FSR_VISIT_SCHD_DT";
    /** . */
    public static final String FSR_VISIT_SCHD_TM = "FSR_VISIT_SCHD_TM";
    /** . */
    public static final String FSR_VISIT_DISPT_DT = "FSR_VISIT_DISPT_DT";
    /** . */
    public static final String FSR_VISIT_DISPT_TM = "FSR_VISIT_DISPT_TM";
    /** . */
    public static final String SVC_ASG_TP_CD = "SVC_ASG_TP_CD";
    /** . */
    public static final String TECH_ACPT_FLG = "TECH_ACPT_FLG";
    /** . */
    public static final String TECH_ACPT_DT = "TECH_ACPT_DT";
    /** . */
    public static final String TECH_ACPT_TM = "TECH_ACPT_TM";
    /** . */
    public static final String FSR_VISIT_ARV_DT = "FSR_VISIT_ARV_DT";
    /** . */
    public static final String FSR_VISIT_ARV_TM = "FSR_VISIT_ARV_TM";
    /** . */
    public static final String FSR_VISIT_CPLT_DT = "FSR_VISIT_CPLT_DT";
    /** . */
    public static final String FSR_VISIT_CPLT_TM = "FSR_VISIT_CPLT_TM";
    /** . */
    public static final String FSR_VISIT_CLO_DT = "FSR_VISIT_CLO_DT";
    /** . */
    public static final String FSR_VISIT_CLO_TM = "FSR_VISIT_CLO_TM";
    /** . */
    public static final String SVC_SUPPL_FROM_DT = "SVC_SUPPL_FROM_DT";
    /** . */
    public static final String SVC_SUPPL_FROM_TM = "SVC_SUPPL_FROM_TM";
    /** . */
    public static final String SVC_SUPPL_TO_DT = "SVC_SUPPL_TO_DT";
    /** . */
    public static final String SVC_SUPPL_TO_TM = "SVC_SUPPL_TO_TM";
    /** . */
    public static final String RSN_NOT_CPLT_TXT = "RSN_NOT_CPLT_TXT";
    /** . */
    public static final String SVC_TRVL_AOT = "SVC_TRVL_AOT";
    /** . */
    public static final String SVC_LBOR_AOT = "SVC_LBOR_AOT";
    /** . */
    public static final String SVC_ITRPT_AOT = "SVC_ITRPT_AOT";
    /** . */
    public static final String SVC_ITRPT_TRVL_AOT = "SVC_ITRPT_TRVL_AOT";
    /** . */
    public static final String SVC_ITRPT_MEAL_AOT = "SVC_ITRPT_MEAL_AOT";
    /** . */
    public static final String SVC_TOT_AOT = "SVC_TOT_AOT";
    /** . */
    public static final String SVC_AVG_TRVL_AOT = "SVC_AVG_TRVL_AOT";
    /** . */
    public static final String SVC_LBOR_COST_UNIT_AMT = "SVC_LBOR_COST_UNIT_AMT";
    /** . */
    public static final String SVC_TRVL_COST_AMT = "SVC_TRVL_COST_AMT";
    /** . */
    public static final String SVC_LBOR_COST_AMT = "SVC_LBOR_COST_AMT";
    /** . */
    public static final String SVC_ITRPT_COST_AMT = "SVC_ITRPT_COST_AMT";
    /** . */
    public static final String SVC_PRT_COST_AMT = "SVC_PRT_COST_AMT";
    /** . */
    public static final String SVC_TOT_COST_AMT = "SVC_TOT_COST_AMT";
    /** . */
    public static final String FSR_VISIT_ETA_DT = "FSR_VISIT_ETA_DT";
    /** . */
    public static final String FSR_VISIT_ETA_TM = "FSR_VISIT_ETA_TM";
    /** . */
    public static final String MTR_GRP_PK = "MTR_GRP_PK";
    /** . */
    public static final String TOT_READ_MTR_CNT = "TOT_READ_MTR_CNT";
    /** . */
    public static final String BW_READ_MTR_CNT = "BW_READ_MTR_CNT";
    /** . */
    public static final String COLOR_READ_MTR_CNT = "COLOR_READ_MTR_CNT";
    /** . */
    public static final String MTR_READ_DT = "MTR_READ_DT";
    /** . */
    public static final String MTR_READ_EX_RSN_TXT = "MTR_READ_EX_RSN_TXT";
    /** . */
    public static final String LABOR_RATE = "LABOR_RATE";
}
