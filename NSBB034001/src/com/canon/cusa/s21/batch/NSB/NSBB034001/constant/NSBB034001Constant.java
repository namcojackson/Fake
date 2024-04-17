/**
 * <pre>
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB034001.constant;

/**
 * <pre>
 * Create Modification Call
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         G.Gotou         Create          N/A
 * 2018/08/08   Hitachi         K.Kitachi       Update          QC#27460
 * </pre>
 */
public class NSBB034001Constant {

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

    // START 2018/08/08 K.Kitachi [QC#27460, DEL]
//    /** Percent */
//    public static final String STR_PERCENT = "%";
    // END 2018/08/08 K.Kitachi [QC#27460, DEL]

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSBB0340";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** Max FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** VAR_CHAR_CONST_KEY: SVC_MOD_CANC_TERM_AOT */
    public static final String VAR_CHAR_CONST_KEY_SVC_MOD_CANC_TERM_AOT = "SVC_MOD_CANC_TERM_AOT";

    /** Error Key Name: Mods Status PK */
    public static final String KEY_SVC_MOD_STS_PK = "SVC_MOD_STS_PK";

    /** Error Key Name: Task Number */
    public static final String KEY_SVC_TASK_NUM = "SVC_TASK_NUM";

    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = "NSBB0340";

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
