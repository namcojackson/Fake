/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB495001.constant;

/**
 *<pre>
 * I105 CSMP Inbound Contract I/F (WMB)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/06   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAB495001Constant {

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "Global Company Code";

    /** Interface Id */
    public static final String INTFC_ID = "Interface Id";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** DEFAULT_COMMIT_SIZE */
    public static final int DEFAULT_COMMIT_SIZE = 1000;

    /** JOB ID */
    public static final String JOB_ID = "NMAB4950";

    /** REMOTE_FILE_NAME */
    public static final String REMOTE_FILE_NAME = "REMOTE_FILE_NAME";

    /** Mail Character Set */
    public static final String MAIL_CHARSET = "ISO-8859-1";

    /** Mail Type : From */
    public static final String MAIL_TYPE_FROM = "From";

    /** Mail Group Id Key: From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** CRLF */
    public static final String CRLF = "\r\n";

    /** Mail Key From : NMA */
    public static final String MAIL_KEY_FROM = "NMA";

    /** Mail Key To : ERR */
    public static final String MAIL_KEY_TO_ERR = "A";

    /** Mail Key To : Normal */
    public static final String MAIL_KEY_TO_NORMAL = "B";

    /** Mail Type : To */
    public static final String MAIL_TYPE_TO = "To";

    /** Mail Message Hyphen */
    public static final String HYPHEN = "-";

    /** 15 : Transaction Id Length For Error Mail*/
    public static final int TRX_ID_LENGTH_FOR_ERR = 15;

    /** Transaction ID */
    public static final String MAIL_FIELD_MSG_TRX_ID = "Transaction ID";

    /** Error Message */
    public static final String MAIL_FIELD_MSG_ERR_MSG = "Error Message";

    /** DUNS_RCV_INFO(table name) */
    public static final String MAIL_FIELD_BATCH_TBL_NAME = "DUNS_RCV_INFO";

    /** Mail Group Id Key: To */
    public static final String MAIL_GROUP_ID_TO = "NMAB4950";

    /** Mail Template ID(ERR) */
    public static final String MAIL_TEMPLATE_ID_ERR = "NMAB4950M001";

    /** Mail Template ID(Normal) */
    public static final String MAIL_TEMPLATE_ID_NORMAL = "NMAB4950M002";

    /** Mail Parameter : batchId */
    public static final String MAIL_FIELD_BATCH_ID = "batchId";

    /** Mail Parameter : batchName */
    public static final String MAIL_FIELD_BATCH_NAME = "batchNm";

    /** Mail Parameter : batchProcLogId */
    public static final String MAIL_FIELD_BATCH_PROC_LOG_ID = "batchProcLogId";

    /** Mail Parameter : ErrorInfo */
    public static final String MAIL_FIELD_ERR_INFO = "ErrorInfo";

    /** Mail Parameter : MailInfo */
    public static final String MAIL_FIELD_INFO = "MailInfo";

    /** Mail Parameter : fileName */
    public static final String MAIL_FIELD_BATCH_FILE_NAME = "fileName";

    /** Mail Parameter : fileDate */
    public static final String MAIL_FIELD_BATCH_FILE_DATE = "fileDate";

    /** Mail Parameter : fileRecordCount */
    public static final String MAIL_FIELD_BATCH_FILE_RECORD_CNT = "fileRecordCount";

    /** Batch Program ID */
    public static final String BATCH_PROGRAM_ID = "NMAB4950";

    /** Batch Program Name */
    public static final String BATCH_PROGRAM_NAME = "Load DUNS by batch(Inbound)";

    /** Date Time Pattern */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** There is no parameter in [@]. */
    public static final String NMAM8504E = "NMAM8504E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** E-mail address <Type: [@], Group: [@], Key: [@]>  cannot be obtained. */
    public static final String NMAM8028E = "NMAM8028E";

    /** The e-mail template <template ID: [@]> cannot be obtained. */
    public static final String NMAM8031E = "NMAM8031E";

    /** [@] It failed to register @ Table,[@] */
    public static final String NMAM8571E = "NMAM8571E";
}
