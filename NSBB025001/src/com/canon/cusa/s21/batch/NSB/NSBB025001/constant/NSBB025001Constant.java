/**
 * <pre>
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB025001.constant;

/**
 * <pre>
 * Update Cross Service Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   Hitachi         Y.Tsuchimoto    Created         N/A
 * </pre>
 */
public class NSBB025001Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSBB0250";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** [@] field is mandatory. */
    public static final String NSBM0032E = "NSBM0032E";

    /** Failed to register to the [@] table. */
    public static final String NSBM0068E = "NSBM0068E";

    /** [@] is not registered.(@) */
    public static final String NSBM0135E = "NSBM0135E";

    /** @'s @ is incorrect. */
    public static final String NSBM0140E = "NSBM0140E";

    /** @'s @ doesn't exist. */
    public static final String NSBM0141E = "NSBM0141E";

    /** @'s @ doesn't exist in a master. */
    public static final String NSBM0142E = "NSBM0142E";

    /** @'s @ is not found. */
    public static final String NSBM0143E = "NSBM0143E";

    /** @'s status cannot @. */
    public static final String NSBM0144E = "NSBM0144E";

    /** The data @ does not exist in the master. */
    public static final String NSBM0011E = "NSBM0011E";

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** User Id: NSBB0250 */
    public static final String USER_ID = "NSBB0250";

    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = "NSBB0250";

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

    /** Date Time Pattern For API */
    public static final String API_DATE_FORMAT = "yyyyMMdd";

    /** Date Time Pattern For API */
    public static final String API_TIME_FORMAT = "HHmmss";

    /** DS_COND_CONST GroupId : NSBB0250_SVC_MEMO_TP_CD */
    public static final String CONST_GRP_ID_SVC_MEMO_TP = "NSBB0250_SVC_MEMO_TP";

    /** DS_COND_CONST Code : FSR_UPDATE_API */
    public static final String CONST_CONST_CD_FSR_UPDATE_API = "FSR_UPDATE_API";

    /** DS_COND_CONST Code : ADD_TASK_API */
    public static final String CONST_CONST_CD_ADD_TASK_API = "ADD_TASK_API";

    /** NSZC0450_XX_MODE_CD : FOLLOW UP CALL */
    public static final String NSZC0450_XX_MODE_CD_FOLLOW_UP_CALL = "03";

    /** NSZC0450_XX_MODE_CD : CANCEL TASK */
    public static final String NSZC0450_XX_MODE_CD_CANCEL_TASK = "02";
}
