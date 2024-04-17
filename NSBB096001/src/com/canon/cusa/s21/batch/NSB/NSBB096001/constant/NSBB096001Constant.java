/**
 * <pre>
 * Copyright (c) 2018 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB096001.constant;

/**
 * <pre>
 * Send notification to Duty Manager
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/20   CITS            T.Wada          Create          N/A
 * 2018/07/31   CITS            T.Wada          Update          QC#26035
 * 2023/05/11   Hitachi         T.Usui          Update          QC#61069
 * </pre>
 */
public class NSBB096001Constant {

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Data update failure.. */
    public static final String ZZZM9013E = "ZZZM9013E";

    /** [@] is not registered.(@) */
    public static final String NSBM0135E = "NSBM0135E";

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSBB0960";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmss";

    /** MAX_FETCH_SIZE */
    public static final int MAX_FETCH_SIZE = 1000;

    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = "NSBB0960";

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = BIZ_APP_ID + "M001";

    /** Date Time Pattern For Mail */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";

    /** DEFAULT INTERVAL MINUTES */
    public static final String CONST_INTERVAL_NTFY_DUTY_MGR = "INTERVAL_NTFY_DUTY_MGR";

    /** Mail Date Format */
    public static final String CONST_MAIL_DATE_TIME_FORMAT = "NSBB0960_MAIL_DATE_TIME_FORMAT";

    /** VALCHAR CONST NAME  (For Get INTERVAL MINUTES) */
    public static final long DEF_INTERVAL_MINUTES = 30;

    /** MAIL DATE TIME FORMAT IN */
    public static final String MAIL_DATE_TIME_FORMAT_IN = "yyyyMMddHHmm";

    /** MAIL DATE TIME FORMAT OUT */
    public static final String NOTICE_MAIL_DATE_TIME_FORMAT = "MMM dd yyyy hh:mm a";

    /** Date Length */
    public static final int DT_LEN = 8;

    /** Time Length */
    public static final int TM_LEN = 12;

    // QC#26035 Add Start
    /** MAX NOTIFY COUNT */
    public static final int MAX_NTFY_CNT = 999;
    // QC#26035 Add End

    // QC#61069 2023/05/11 Add Start
    /** SVC_BY_LINE_BIZ_TP_CD_PPS */
    public static final String SVC_BY_LINE_BIZ_TP_CD_PPS = "PPS";

    /** SVC_BY_LINE_BIZ_TP_CD_LFS */
    public static final String SVC_BY_LINE_BIZ_TP_CD_LFS = "LFS";
    // QC#61069 2023/05/11 Add End

}
