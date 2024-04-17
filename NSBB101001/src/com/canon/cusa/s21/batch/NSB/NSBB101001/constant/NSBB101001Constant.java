/**
 * <pre>
 * Copyright (c) 2015 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB101001.constant;

/**
 * <pre>
 * Meter Read Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/03/02   Hitachi         S.Naya          Create          QC#60927
 * </pre>
 */
public class NSBB101001Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSBB1010";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** MSG_GLOBAL_COMPANY_CODE */
    public static final String MSG_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** MSG_UPLD_CSV_RQST_PK */
    public static final String MSG_UPLD_CSV_RQST_PK = "Upload CSV Request PK";

    /** MSG_SVC_MOD_PLN_ID */
    public static final String MSG_SVC_MOD_PLN_ID = "Plan ID";

    /** MSG_SERIAL_NUM */
    public static final String MSG_SERIAL_NUM = "Serial#";

    /** MSG_SVC_MOD_OPT_DESC_TXT */
    public static final String MSG_SVC_MOD_OPT_DESC_TXT = "Option";

    /** MSG_SVC_MOD_OPT_DT */
    public static final String MSG_SVC_MOD_OPT_DT = "Date";


    /** "@" is invalid. */
    public static final String NSAM0040E = "NSAM0040E";

    /** Plan ID does not exist in Service Mods Master. */
    public static final String NSBM0191E = "NSBM0191E";

    /** Serial# does not exist in Service Machine Master. */
    public static final String NSBM0192E = "NSBM0192E";

    /** Option does not exist in Service Mods Option Master. */
    public static final String NSBM0193E = "NSBM0193E";

    /** [@] is not entered. Option and Date must be entered as a set. */
    public static final String NSBM0194E = "NSBM0194E";
    
    /** Service Mods Status does not exist. */
    public static final String NSBM0195E = "NSBM0195E";

    /** Failed Update Service Mods Status. */
    public static final String NSBM0196E = "NSBM0196E";

    /** None of the target Service Mods Status is closed. */
    public static final String NSBM0218E = "NSBM0218E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is invalid value. */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** MessageID : NYXM0001I */
    public static final String NYXM0001I = "NYXM0001I";

    /** MessageID : NYXM0002E */
    public static final String NYXM0002E = "NYXM0002E";

    /** Message : RESULT_MSG_INS */
    public static final String RESULT_MSG_INS = "%d record(s) successfully created.";

    /** Message : RESULT_MSG_ERR */
    public static final String RESULT_MSG_ERR = "%d record(s) skipped/errored.";
}
