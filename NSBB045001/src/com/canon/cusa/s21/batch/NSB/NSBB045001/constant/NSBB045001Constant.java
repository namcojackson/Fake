/**
 * <pre>
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB045001.constant;

/**
 *<pre>
 * Model Duration Update Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/04   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSBB045001Constant {

    /*** Error Msg : Global Company Code is mandatory. */
    public static final String NASM0010E = "NASM0010E";

    /** Error Msg : There is no parameter in [@]. */
    public static final String NSBM0032E = "NSBM0032E";

    /** Failed to update @ table.[@] */
    public static final String NSBM0120E = "NSBM0120E";

    /** BATCH_PROGRAM_ID(NSBB045001) */
    public static final String PROGRAM_ID = "NSBB045001";

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSBB0450";

    /** Colon */
    public static final String STR_COLON = ":";

    /** Comma */
    public static final String STR_COMMA = ",";

    /** Slash */
    public static final String STR_SLASH = "/";

    /** ZERO */
    public static final String ZERO = "0";

    /** FSR_VISIT_NUM */
    public static final String FSR_VISIT_NUM = "01";

    /** SVC_CALL_ONLY */
    public static final String SVC_CALL_ONLY = "SVC_CALL_ONLY";

    /** FOR_EACH */
    public static final String FOR_EACH = "FOR_EACH";

    /** Error Key Name: MDL_ID */
    public static final String KEY_MDL_ID = "MDL_ID";

    /** Max FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** 0 */
    public static final int NUM_0 = 0;

    /** 2 */
    public static final int NUM_2 = 2;

    /** 4 */
    public static final int NUM_4 = 4;

    /** 6 */
    public static final int NUM_6 = 6;

    /** 8 */
    public static final int NUM_8 = 8;

    /** 12 */
    public static final int NUM_12 = 12;

    /** 60 */
    public static final int NUM_60 = 60;

    /** DAY_TIME */
    public static final int NUM_DAY = 86400;

    /** ONE_DAY_TIME */
    public static final long ONE_DAY_TIME = 86400000;

    /** SVC_TASK_CALC_DURN_MTH_AOT */
    public static final String SVC_TASK_CALC_DURN_MTH_AOT = "SVC_TASK_CALC_DURN_MTH_AOT";

    /** SVC_TASK_CACL_DURN_TP */
    public static final String SVC_TASK_CACL_DURN_TP = "SVC_TASK_CACL_DURN_TP";

    /** MULTI_MDL_DURN_CNT */
    public static final String MULTI_MDL_DURN_CNT = "MULTI_MDL_DURN_CNT";

    /** SVC_TM_EVENT_CD */
    public static final String SVC_TM_EVENT_CD = "SVC_TM_EVENT_CD";
}
