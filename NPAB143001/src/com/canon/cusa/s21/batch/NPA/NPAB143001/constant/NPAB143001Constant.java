/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB143001.constant;

/**
 * <pre>
 * NPAB143001 : CVI On-Hand Update
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/26/2015   CITS            Y.Kuroda        Create          N/A
 *</pre>
 */
public class NPAB143001Constant {

    /** program id */
    public static final String PROGRAM_ID = "NPAB143001";

    /** message id : The parameter is null. [@] */
    public static final String ZZXM0020E = "ZZXM0020E";

    /** message id : Cannot retrieve mail address. */
    public static final String NPAM1265E = "NPAM1265E";

    /** message id : Cannot retrieve mail template. */
    public static final String NPAM1266E = "NPAM1266E";

    /** message id : Failed to delete the data. */
    public static final String NPAM1298E = "NPAM1298E";

    /** message id : Failed to register the data. */
    public static final String NPAM1300E = "NPAM1300E";

    /** Oracle Sequence Name */
    public static final String DB_SEQ_CVI_INVTY_SQ = "CVI_INVTY_SQ";

    /** Error Message Text: Global Company Code */
    public static final String MSG_TXT_GLBL_CMPY_CD = "Global Company Code";

    /** Error Message Text: Interface Id */
    public static final String MSG_TXT_INTERFACE_ID = "Interface Id";

    /** System Error Mail group id for To. */
    public static final String MAIL_GROUP_ID = "NPAB1430";

    /** Mail Group Id Key: From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_NPAB1430M001 = "NPAB1430M001";

    /** Mail String Field : batchId */
    public static final String MAIL_FIELD_BATCH_ID = "batchId";

    /** Mail String Field : errDate */
    public static final String MAIL_FIELD_ERR_DATE = "errDate";

    /** Mail Date Format : MM/dd/yyyy HH:mm:ss.SSS */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** Mail String Field : message */
    public static final String MAIL_FIELD_MESSAGE = "message";

    /** Search Key : CVI_INVTY_TP_DESC_TXT */
    public static final String CVI_INVTY_TP_GOOD = "GOOD";

    /* Table column INTERFACE_ID NPAI2210_01 */
    /** NPAI222001_INTERFACE_ID */
    public static final String NPAI221001_INTERFACE_ID = "INTERFACE_ID";

    /** NPAI222001_TRANSACTION_ID */
    public static final String NPAI221001_TRANSACTION_ID = "TRANSACTION_ID";

    /** NPAI222001_SEGMENT_ID */
    public static final String NPAI221001_SEGMENT_ID = "SEGMENT_ID";

    /** NPAI222001_SEGMENT_ID */
    public static final String NPAI221001_UNIT_ID = "UNIT_ID";

    /** NPAI222001_SEQ_NUMBER */
    public static final String NPAI221001_SEQ_NUMBER = "SEQ_NUMBER";

    /** NPAI221001_MDSE_CD */
    public static final String NPAI221001_MDSE_CD = "MDSE_CD";

    /** NPAI221001_CVI_INVTY_TP_DESC_TXT */
    public static final String NPAI221001_CVI_INVTY_TP_DESC_TXT = "CVI_INVTY_TP_DESC_TXT";

    /** NPAI221001_CVI_INVTY_QTY */
    public static final String NPAI221001_CVI_INVTY_QTY = "CVI_INVTY_QTY";
}
