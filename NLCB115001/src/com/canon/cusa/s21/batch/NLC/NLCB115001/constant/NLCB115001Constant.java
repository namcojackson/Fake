/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB115001.constant;

/**
 *<pre>
 * Monthly Parts Usage Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/04/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NLCB115001Constant {

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

    /** message Item: DWH Target Year Month. */
    public static final String MSG_ITEM_DWH_TRGT_YR_MTH = "DWH Target Year Month";

    /** SSM set Key Name: KEY_TASK_STS_CD_1 */
    public static final String KEY_TASK_STS_CD_1 = "taskStsCd1";

    /** SSM set Key Name: KEY_TASK_STS_CD_2 */
    public static final String KEY_TASK_STS_CD_2 = "taskStsCd2";

    /** SSM set Key Name: KEY_TASK_STS_CD_3 */
    public static final String KEY_TASK_STS_CD_3 = "taskStsCd3";

    /** SSM set Key Name: KEY_TASK_CPLT_DT_1 */
    public static final String KEY_TASK_CPLT_DT_1 = "taskCpltDt1";

    /** SSM set Key Name: KEY_TASK_CPLT_DT_2 */
    public static final String KEY_TASK_CPLT_DT_2 = "taskCpltDt2";

    /**  Value : 1 */
    public static final String VAL_1 = "1";

    /**  Value : 6 */
    public static final String VAL_6 = "6";

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";
    /** . */
    public static final String PARAM_TRGT_YR_MTH = "trgtYrMth";

    /** DB COLUMN NAME */
    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** . */
    public static final String DWH_TRGT_YR_MTH = "DWH_TRGT_YR_MTH";
    /** . */
    public static final String FCT_MLY_FSR_PRT_USG_PK = "FCT_MLY_FSR_PRT_USG_PK";
    /** . */
    public static final String FSR_NUM = "FSR_NUM";
    /** . */
    public static final String DS_SVC_CALL_TP_CD = "DS_SVC_CALL_TP_CD";
    /** . */
    public static final String FSR_CRAT_DT = "FSR_CRAT_DT";
    /** . */
    public static final String FSR_CRAT_TM = "FSR_CRAT_TM";
    /** . */
    public static final String FSR_CPLT_DT = "FSR_CPLT_DT";
    /** . */
    public static final String FSR_CPLT_TM = "FSR_CPLT_TM";
    /** . */
    public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";
    /** . */
    public static final String MDL_ID = "MDL_ID";
    /** . */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
    /** . */
    public static final String SER_NUM = "SER_NUM";
    /** . */
    public static final String SVC_TASK_NUM = "SVC_TASK_NUM";
    /** . */
    public static final String FSR_VISIT_NUM = "FSR_VISIT_NUM";
    /** . */
    public static final String TECH_CD = "TECH_CD";
    /** . */
    public static final String PRT_USED_BY_TECH_LOC_CD = "PRT_USED_BY_TECH_LOC_CD";
    /** . */
    public static final String MDSE_CD = "MDSE_CD";
    /** . */
    public static final String FSR_VISIT_USED_PRT_QTY = "FSR_VISIT_USED_PRT_QTY";
    /** . */
    public static final String FSR_VISIT_USED_PRT_COST_AMT = "FSR_VISIT_USED_PRT_COST_AMT";
    /** . */
    public static final String BASE_PRC_AMT = "BASE_PRC_AMT";
}
