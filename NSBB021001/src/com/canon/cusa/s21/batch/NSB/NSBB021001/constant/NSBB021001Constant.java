/**
 * <pre>
 * Copyright (c) 2015 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB021001.constant;

/**
 * <pre>
 * Alert Cross Service Request
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/21   Hitachi         O.Okuma         Create          N/A
 * 2016/08/05   Hitachi         T.Tomita        Update          QC#12436
 * </pre>
 */
public class NSBB021001Constant {

    /** "@" doesn't exist in the VAR_CHAR_CONST */
    public static final String NSBM0069E = "NSBM0069E";

    /** [@] is not registered.(@) */
    public static final String NSBM0135E = "NSBM0135E";

    /** Failed to insert @ table. [@] */
    public static final String NSBM0121E = "NSBM0121E";

    /** @ cannot be obtained. */
    public static final String NSZM0392E = "NSZM0392E";

    /** Time Stamp Format(yyyyMMddHHmmss) */
    public static final String TIME_STAMP_FORMAT_14 = "yyyyMMddHHmmss";

    /** Time Stamp Format(yyyyMMddHHmmssSSS) */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** Substring Position 14 */
    public static final int SUB_STR_POS_14 = 14;

    /** Colon */
    public static final String STR_COLON = ":";

    /** COMMA */
    public static final String STR_CNM = ",";

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSBB0210";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** Max FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** DS_COND_CONST_GRP_ID:ML_CTRL */
    public static final String DS_COND_CONST_GRP_ID_ML_CTRL = "NSBB0210_ML_CTRL";

    /** DS_COND_CONST_GRP_ID:SEND_TO */
    public static final String DS_COND_CONST_GRP_ID_SEND_TO = "NSBB0210_SEND_TO";

    /** DS_COND_CONST_CD:INIT_INTVL_MIN */
    public static final String DS_COND_CONST_CD_INIT_INTVL_MIN = "INIT_INTVL_MIN";

    /** DS_COND_CONST_CD:RE-NTFY_FLG */
    public static final String DS_COND_CONST_CD_RE_NTFY_FLG = "RE-NTFY_FLG";

    /** DS_COND_CONST_CD:RE-NTFY_INTVL_MIN */
    public static final String DS_COND_CONST_CD_RE_NTFY_INTVL_MIN = "RE-NTFY_INTVL_MIN";

    /** Table Name:DS_COND_CONST */
    public static final String TBL_NM_DS_COND_CONST = "DS_COND_CONST";

    /** Table Name:FSR_EVENT */
    public static final String TBL_NM_FSR_EVENT = "FSR_EVENT";

    /** VAR_CHAR_CONST_KEY: CRS_SVC_PSN_TP */
    public static final String VAR_CHAR_CONST_KEY_CRS_SVC_PSN_TP = "CRS_SVC_PSN_TP";

    // mod start 2016/08/05 CSA Defect#12436
//    /** Error Key Name: FSR_EVENT_PK */
//    public static final String KEY_FSR_EVENT_PK = "FSR_EVENT_PK";
    /** Error Key Name: FSR_NUM */
    public static final String KEY_FSR_NUM = "FSR#";

    /** Error Key Name: FSR_NUM */
    public static final String KEY_SVC_TASK_NUM = "Task#";
    // mod end 2016/08/05 CSA Defect#12436

    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** mail group key for From  */
    public static final String MAIL_GROUP_KEY_FROM  = "NS";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = BIZ_APP_ID;

    /** mail group id for To Group 1 */
    public static final String MAIL_GROUP_ID_GRP1 = BIZ_APP_ID + "_GRP1";

    /** mail group id for To Group 2 */
    public static final String MAIL_GROUP_ID_GRP2 = BIZ_APP_ID + "_GRP2";

    /** mail group id for CREATOR */
    public static final String MAIL_GROUP_ID_CREATOR = "CREATOR";

    /** template ID 1 */
    public static final String MAIL_TEMPLATE_ID_1 = BIZ_APP_ID + "M001";

    /** template ID 2*/
    public static final String MAIL_TEMPLATE_ID_2 = BIZ_APP_ID + "M002";

    /** template parameter key : batch id */
    public static final String MAIL_PARAM_BATCH_ID = "batchId";

    /** template parameter key : err date */
    public static final String MAIL_PARAM_ERR_DATE = "errDate";

    /** template parameter key : message */
    public static final String MAIL_PARAM_MESSAGE = "message";

    /** template parameter key : date */
    public static final String MAIL_PARAM_DATE = "date";

    /** template parameter key : fsrNum */
    public static final String MAIL_PARAM_FSR_NUM = "fsrNum";

    /** template parameter key : svcTaskNum */
    public static final String MAIL_PARAM_SVC_TASK_NUM = "svcTaskNum";

    /** template parameter key : serNum */
    public static final String MAIL_PARAM_SER_NUM = "serNum";

    /** template parameter key : mdlNm */
    public static final String MAIL_PARAM_MDL_NM = "mdlNm";

    /** template parameter key : dsAcctNm */
    public static final String MAIL_PARAM_DS_ACCT_NM = "dsAcctNm";

    /** template parameter key : svcTaskCratDate */
    public static final String MAIL_PARAM_SVC_TASK_CRAT_DATE = "svcTaskCratDate";

    /** template parameter key : noProcTime */
    public static final String MAIL_PARAM_NO_PROC_TIME = "noProcTime";

    /** Date Time Pattern For Mail */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";

    /** ZYPCommonFunc.isDigits Non numeric: 0 */
    public static final int CHECK_DIGITS_TP_NON_NUMERIC = 0;
}
