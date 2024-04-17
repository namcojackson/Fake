/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB091001.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Monthly FSR By Machine Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/25/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NSBB091001Constant {

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
    public static final String MSG_ITEM_BATCH_DATE = "Batch Process Date";

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

    /** Three Month */
    public static final int THREE_MTH = 3;

    /** Six Month */
    public static final int SIX_MTH = 6;

    /** Twelve Month */
    public static final int TWELVE_MTH = 12;

    /** COPIER */
    public static final String COPIER = "%COPIER%";

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
    public static final String PARAM_COPIER = "copier";
    /** . */
    public static final String PARAM_EFFECTIVE = "effective";
    /** . */
    public static final String PARAM_APPROVED = "approved";
    /** . */
    public static final String PARAM_SERVICE_CALL = "serviceCall";
    /** . */
    public static final String PARAM_INSTALL_CALL = "installCall";
    /** . */
    public static final String PARAM_PICK_SUPPL_TASK = "pickSupplTask";
    /** . */
    public static final String PARAM_MEAL_2 = "meal2";
    /** . */
    public static final String PARAM_LABOR_RATE = "laborRate";
    /** . */
    public static final String PARAM_YM_LEN = "ymLen";
    /** . */
    public static final String PARAM_SEC_PERIOD = "secPeriod";
    /** . */
    public static final String PARAM_TIME_ADJ = "timeAdj";
    /** . */
    public static final String PARAM_SVC_MACH_MSTR_PK = "svcMachMstrPk";
    /** . */
    public static final String PARAM_BEFORE_MTH = "beforeMth";
    /** . */
    public static final String PARAM_THREE_MTH = "threeMth";
    /** . */
    public static final String PARAM_SIX_MTH = "sixMth";
    /** . */
    public static final String PARAM_TWELVE_MTH = "twelveMth";

    /** DB COLUMN NAME */
    /** . */
    public static final String DWH_TRGT_YR_MTH = "DWH_TRGT_YR_MTH";
    /** . */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
    /** . */
    public static final String TECH_CD = "TECH_CD";
    /** . */
    public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";
    /** . */
    public static final String SVC_BY_LINE_BIZ_TP_CD = "SVC_BY_LINE_BIZ_TP_CD";
    /** . */
    public static final String MDL_ID = "MDL_ID";
    /** . */
    public static final String SER_NUM = "SER_NUM";
    /** . */
    public static final String COPR_MACH_FLG = "COPR_MACH_FLG";
    /** . */
    public static final String TM_AND_MAT_FLG = "TM_AND_MAT_FLG";
    /** . */
    public static final String READ_MTR_CNT = "READ_MTR_CNT";
    /** . */
    public static final String RGTN_MTR_DT = "RGTN_MTR_DT";
    /** . */
    public static final String PREV_READ_MTR_CNT = "PREV_READ_MTR_CNT";
    /** . */
    public static final String LTST_READ_MTR_CNT = "LTST_READ_MTR_CNT";
    /** . */
    public static final String MLY_COPY_VOL = "MLY_COPY_VOL";
    /** . */
    public static final String SVC_INV_NUM = "SVC_INV_NUM";
    /** . */
    public static final String INV_DT = "INV_DT";
    /** . */
    public static final String CHRG_COPY_VOL = "CHRG_COPY_VOL";
    /** . */
    public static final String MLY_TOT_FSR_CNT = "MLY_TOT_FSR_CNT";
    /** . */
    public static final String MLY_TOT_CM_CNT = "MLY_TOT_CM_CNT";
    /** . */
    public static final String VISIT_CNT = "VISIT_CNT";
    /** . */
    public static final String CM_CNT = "CM_CNT";
    /** . */
    public static final String MLY_TOT_VISIT_CNT = "MLY_TOT_VISIT_CNT";
    /** . */
    public static final String MLY_TOT_RTRN_VISIT_CNT = "MLY_TOT_RTRN_VISIT_CNT";
    /** . */
    public static final String SCD_VISIT_RATIO = "SCD_VISIT_RATIO";
    /** . */
    public static final String SVC_AVG_TRVL_AOT = "SVC_AVG_TRVL_AOT";
    /** . */
    public static final String SVC_LBOR_COST_UNIT_AMT = "SVC_LBOR_COST_UNIT_AMT";
    /** . */
    public static final String MLY_TOT_TRVL_AOT = "MLY_TOT_TRVL_AOT";
    /** . */
    public static final String MLY_TOT_LBOR_AOT = "MLY_TOT_LBOR_AOT";
    /** . */
    public static final String MLY_TOT_ITRPT_AOT = "MLY_TOT_ITRPT_AOT";
    /** . */
    public static final String MLY_TOT_TRVL_COST_AMT = "MLY_TOT_TRVL_COST_AMT";
    /** . */
    public static final String MLY_TOT_LBOR_COST_AMT = "MLY_TOT_LBOR_COST_AMT";
    /** . */
    public static final String MLY_TOT_ITRPT_COST_AMT = "MLY_TOT_ITRPT_COST_AMT";
    /** . */
    public static final String MLY_TOT_PRT_COST_AMT = "MLY_TOT_PRT_COST_AMT";
    /** . */
    public static final String MLY_TOT_COST_AMT = "MLY_TOT_COST_AMT";
    /** . */
    public static final String MLY_NI_VISIT_CNT = "MLY_NI_VISIT_CNT";
    /** . */
    public static final String MLY_NI_TRVL_AOT = "MLY_NI_TRVL_AOT";
    /** . */
    public static final String MLY_NI_LBOR_AOT = "MLY_NI_LBOR_AOT";
    /** . */
    public static final String MLY_NI_ITRPT_AOT = "MLY_NI_ITRPT_AOT";
    /** . */
    public static final String MLY_NI_SCD_VISIT_CNT = "MLY_NI_SCD_VISIT_CNT";
    /** . */
    public static final String MLY_NI_SCD_VISIT_TRVL_AOT = "MLY_NI_SCD_VISIT_TRVL_AOT";
    /** . */
    public static final String MLY_NI_SCD_VISIT_LBOR_AOT = "MLY_NI_SCD_VISIT_LBOR_AOT";
    /** . */
    public static final String MLY_NI_SCD_VISIT_ITRPT_AOT = "MLY_NI_SCD_VISIT_ITRPT_AOT";
    /** . */
    public static final String MLY_NI_SCD_VISIT_PRT_CNT = "MLY_NI_SCD_VISIT_PRT_CNT";
    /** . */
    public static final String MLY_ISTL_VISIT_CNT = "MLY_ISTL_VISIT_CNT";
    /** . */
    public static final String MLY_ISTL_SCD_VISIT_CNT = "MLY_ISTL_SCD_VISIT_CNT";
    /** . */
    public static final String CNF_YR_PRT_COST_AMT = "CNF_YR_PRT_COST_AMT";
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
    public static final String K_CLICK_PRT_COST_AMT = "K_CLICK_PRT_COST_AMT";
    /** . */
    public static final String TOT_VISIT_CNT_01 = "TOT_VISIT_CNT_01";
    /** . */
    public static final String TOT_CM_CNT_01 = "TOT_CM_CNT_01";
    /** . */
    public static final String TOT_MTR_VOL_01 = "TOT_MTR_VOL_01";
    /** . */
    public static final String TOT_VISIT_CNT_03 = "TOT_VISIT_CNT_03";
    /** . */
    public static final String TOT_CM_CNT_03 = "TOT_CM_CNT_03";
    /** . */
    public static final String TOT_MTR_VOL_03 = "TOT_MTR_VOL_03";
    /** . */
    public static final String TOT_VISIT_CNT_06 = "TOT_VISIT_CNT_06";
    /** . */
    public static final String TOT_CM_CNT_06 = "TOT_CM_CNT_06";
    /** . */
    public static final String TOT_MTR_VOL_06 = "TOT_MTR_VOL_06";
    /** . */
    public static final String TOT_VISIT_CNT_12 = "TOT_VISIT_CNT_12";
    /** . */
    public static final String TOT_CM_CNT_12 = "TOT_CM_CNT_12";
    /** . */
    public static final String TOT_MTR_VOL_12 = "TOT_MTR_VOL_12";
    /** . */
    public static final String FSR_TP_CD = "FSR_TP_CD";
    /** . */
    public static final String LABOR_RATE = "LABOR_RATE";
}
