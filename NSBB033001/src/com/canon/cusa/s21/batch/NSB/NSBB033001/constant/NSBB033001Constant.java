/**
 * <pre>
 * Copyright (c) 2015 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB033001.constant;

/**
 * <pre>
 * Create Modification Call
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/03   Hitachi         A.Kohinata      Create          N/A
 * 2016/07/06   Hitachi         A.Kohinata      Update          CSA QC#11352
 * </pre>
 */
public class NSBB033001Constant {

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** "@" doesn't exist in the VAR_CHAR_CONST */
    public static final String NSBM0069E = "NSBM0069E";

    /** Failed to update @ table.[@] */
    public static final String NSBM0120E = "NSBM0120E";

    /** Failed to insert @ table. [@] */
    public static final String NSBM0121E = "NSBM0121E";

    /** [@] is not registered.(@) */
    public static final String NSBM0135E = "NSBM0135E";

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** Format Date */
    public static final String DATE_FORMAT = "yyyyMMdd";

    /** Substring Position 8 */
    public static final int SUB_STR_POS_8 = 8;

    /** Substring Position 14 */
    public static final int SUB_STR_POS_14 = 14;

    /** Colon */
    public static final String STR_COLON = ":";

    /** Percent */
    public static final String STR_PERCENT = "%";

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSBB0330";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** Max FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Max TM */
    public static final String MAX_TM = "235959000";

    /** VAR_CHAR_CONST_KEY: SVC_MOD_CALL_TP */
    public static final String VAR_CHAR_CONST_KEY_SVC_MOD_CALL_TP = "SVC_MOD_CALL_TP";

    /** VAR_CHAR_CONST_KEY: SVC_MOD_PBLM_TP */
    public static final String VAR_CHAR_CONST_KEY_SVC_MOD_PBLM_TP = "SVC_MOD_PBLM_TP";

    // 2016/07/06 QC#11352 Add Start
    /** VAR_CHAR_CONST_KEY: SVC_MOD_NOT_EXIST_EML_ADDR */
    public static final String VAR_CHAR_CONST_KEY_SVC_MOD_NOT_EXIST_EML_ADDR = "SVC_MOD_NOT_EXIST_EML_ADDR";
    // 2016/07/06 QC#11352 Add End

    /** Error Key Name: DS Contract PK */
    public static final String KEY_SVC_MOD_SER_RNG_PK = "SVC_MOD_SER_RNG_PK";

    /** Error Key Name: DS Contract Detail PK */
    public static final String KEY_SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = "NSBB0330";

    /** mail key for To */
    public static final String MAIL_KEY_TO = "To";

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = BIZ_APP_ID + "M001";

    /** template parameter key : batch id */
    public static final String MAIL_TEMPLATE_KEY_ID = "batchId";

    /** template parameter key : err date */
    public static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** template parameter key : message */
    public static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Date Time Pattern For Mail */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
}
