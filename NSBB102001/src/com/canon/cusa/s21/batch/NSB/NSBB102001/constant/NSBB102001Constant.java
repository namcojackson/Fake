/**
 * <pre>
 * Copyright (c) 2015 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB102001.constant;

/**
 * <pre>
 * Create FSR Audit Log Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/07/04   Hitachi         S.Naya          Create          QC#61374
 * </pre>
 */
public class NSBB102001Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSBB1020";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** MSG_GLOBAL_COMPANY_CODE */
    public static final String MSG_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** BATCH_DEFAULT_EXEC_TIME */
    public static final String BATCH_DEFAULT_EXEC_TIME = "10000101000000000";

    /** HIST_ACT_INS */
    public static final String HIST_ACT_INS = "INSERT";

    /** HIST_ACT_UPD */
    public static final String HIST_ACT_UPD = "UPDATE";

    /** SVC_BY_LINE_BIZ_TP_CD_PPS */
    public static final String SVC_BY_LINE_BIZ_TP_CD_PPS = "PPS";

    /** SVC_BY_LINE_BIZ_TP_CD_LFS */
    public static final String SVC_BY_LINE_BIZ_TP_CD_LFS = "LFS";

    /** USER_ID_SYSTEM */
    public static final String USER_ID_SYSTEM = "SYSTEM";

    /** EXCLUDE FLD ON */
    public static final String EXCLUDE_FLD_ON = "Y";

    /** EXCLUDE FLD OFF */
    public static final String EXCLUDE_FLD_OFF = "N";

    /** Fetch Size Max */
    public static final int FETCH_SIZE_MAX = 10000;

    /** Add Minutes */
    public static final int ADD_MINUTES = 60;

    /** USER_ID_LENGTH */
    public static final int USER_ID_LENGTH = 6;


    /** UPD_FLD_TXT_CD_FSR */
    public static final String UPD_FLD_TXT_CD_FSR = "01";

    /** UPD_FLD_TXT_CD_TASK */
    public static final String UPD_FLD_TXT_CD_TASK = "02";

    /** UPD_FLD_TXT_CD_FSR_STATUS */
    public static final String UPD_FLD_TXT_CD_FSR_STATUS = "03";

    /** UPD_FLD_TXT_CD_TASK_STATUS */
    public static final String UPD_FLD_TXT_CD_TASK_STATUS = "04";

    /** UPD_FLD_TXT_CD_ESS_TASK_STATUS */
    public static final String UPD_FLD_TXT_CD_ESS_TASK_STATUS = "05";

    /** UPD_FLD_TXT_CD_TASK_TYPE */
    public static final String UPD_FLD_TXT_CD_TASK_TYPE = "06";

    /** UPD_FLD_TXT_CD_DUTY_MGR */
    public static final String UPD_FLD_TXT_CD_DUTY_MGR = "07";

    /** UPD_FLD_TXT_CD_TECHNICIAN */
    public static final String UPD_FLD_TXT_CD_TECHNICIAN = "08";

    /** UPD_FLD_TXT_CD_VENDOR_CALL */
    public static final String UPD_FLD_TXT_CD_VENDOR_CALL = "09";


    /** COL_NM_FSR_NUM */
    public static final String COL_NM_FSR_NUM = "FSR_NUM";

    /** COL_NM_SVC_TASK_NUM */
    public static final String COL_NM_SVC_TASK_NUM = "SVC_TASK_NUM";

    /** COL_NM_FSR_VISIT_NUM */
    public static final String COL_NM_FSR_VISIT_NUM = "FSR_VISIT_NUM";

    /** COL_NM_HIST_ACT_NM */
    public static final String COL_NM_HIST_ACT_NM = "HIST_ACT_NM";

    /** COL_NM_HIST_CRAT_TS */
    public static final String COL_NM_HIST_CRAT_TS = "HIST_CRAT_TS";

    /** COL_NM_FSR_STATUS */
    public static final String COL_NM_FSR_STATUS = "FSR_STS_CD";

    /** COL_NM_TASK_STATUS */
    public static final String COL_NM_TASK_STATUS = "FSR_VISIT_STS_CD";

    /** COL_NM_ESS_TASK_STATUS */
    public static final String COL_NM_ESS_TASK_STATUS = "CRS_SVC_TASK_STS_CD";

    /** COL_NM_TASK_TYPE */
    public static final String COL_NM_TASK_TYPE = "DS_SVC_CALL_TP_CD";

    /** COL_NM_DUTY_MGR */
    public static final String COL_NM_DUTY_MGR = "PSN_CD";

    /** COL_NM_TECHNICIAN */
    public static final String COL_NM_TECHNICIAN = "TECH_CD";

    /** COL_NM_FSR_VENDOR_CALL */
    public static final String COL_NM_FSR_VENDOR_CALL = "ITT_NUM";

    /** COL_NM_TASK_VENDOR_CALL */
    public static final String COL_NM_TASK_VENDOR_CALL = "CRS_SVC_SR_NUM";


    /** Failed to register to FSR_AUD_LOG. */
    public static final String NSBM0218E = "NSBM0218E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is invalid value. */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** Message : RESULT_MSG_INS */
    public static final String RESULT_MSG_INS = "%d record(s) successfully created.";

    /** Message : RESULT_MSG_ERR */
    public static final String RESULT_MSG_ERR = "%d record(s) skipped/errored.";
}
