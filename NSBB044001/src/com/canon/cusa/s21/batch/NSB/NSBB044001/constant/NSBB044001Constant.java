/**
 * <pre>
 * Copyright (c) 2015 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB044001.constant;

/**
 * <pre>
 * Create Follow Up Call
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/06   Hitachi         A.Kohinata      Create          N/A
 * </pre>
 */
public class NSBB044001Constant {

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** "@" doesn't exist in the VAR_CHAR_CONST */
    public static final String NSBM0069E = "NSBM0069E";

    /** [@] is not registered.(@) */
    public static final String NSBM0135E = "NSBM0135E";

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSBB0440";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** Substring Position 8 */
    public static final int SUB_STR_POS_8 = 8;

    /** Substring Position 14 */
    public static final int SUB_STR_POS_14 = 14;

    /** API PMsg SVC_CUST_CLLR_TXT */
    public static final String API_SVC_CUST_CLLR_TXT = "Not Communication for IWR";

    /** DEF_TEL_NUM */
    public static final String DEF_TEL_NUM = "0";

    /** DEF_EML_ADDR */
    public static final String DEF_EML_ADDR = "No Email Address";

    /** Length : 60 */
    public static final int LENGTH_60 = 60;

    /** MAX_EFF_THRU_DT */
    public static final String MAX_EFF_THRU_DT = "99991231";

    /** MAX_FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** VAR_CHAR_CONST_KEY: IWR_SVC_PBLM_TP */
    public static final String VAR_CHAR_CONST_KEY_IWR_SVC_PBLM_TP = "IWR_SVC_PBLM_TP";

    /** Error Key Name: DS Contract Detail PK */
    public static final String KEY_SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = "NSBB0440";

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
