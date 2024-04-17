/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWW.NWWB001001.constant;

/**
 *<pre>
 * Notification Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/20   Fujitsu         M.Ohno          Create
 * 2016/10/06   Fujitsu         Mz.Takahashi    Update          QC#14431
 * 2019/11/29   Fujitsu         S.Iidaka        Update          QC#54226
 * 2022/09/19   CITS            R.Azucena       Update          QC#60563
 *</pre>
 */
public class NWWB001001Constant {
    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** Email address is not valid. */
    public static final String NWWM0011E = "NWWM0011E";

    /** [@] doesn't exist in [@]. */
    public static final String NWWM0015E = "NWWM0015E";

    /** Failed to update the record.[@] */
    public static final String NWWM0020E = "NWWM0020E";

    /** Failed to create the record.[@] */
    public static final String NWWM0021E = "NWWM0021E";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** MAX_DT */
    public static final String MAX_DT = "99991231";

    /** New Line */
    public static final String CRLF = "\r\n";

    /** Equal */
    public static final String EQUAL = "=";

    /** Quote */
    public static final char QUOTE = '\'';

    /** line separator */
    public static final String NEW_LINE = String.format("%n");

    /** Param Pattern */
    public static final String PARAM_PATTERN = "\\$\\{(\\s*([\\w|\\.|\\_]+)\\s*|(\\s*([\\w|\\.|\\_]+)\\s*,\\s*\"(.+)\"\\s*))\\}";

    /** PARAM_KEY_GROUP */
    public static final int PARAM_KEY_GROUP = 2;

    /** EMail check Pattern */
    // 2016/10/06 QC#1443 Del
    //public static final String CHK_EMAIL_PATTERN = "^[a-zA-Z0-9]+([\\w\\.\\-]*[\\w\\-])*@([\\w\\-]+\\.)+[\\w\\-]+$";

    /** EMail check Pattern */
    public static final String DATE_PATTERN = "yyyyMMddHHmmssSSS";

    /** HMTL Template Name */
    public static final String NTYF_HTML_TMPL_NM = "NTFY_CTR_HTML_TMPL";

    /** MailBody Replace Name */
    public static final String ML_BODY_REPL_NM = "${mailBody}";

    /** Html Table Replace Name */
    public static final String HTML_TBL_REPL_NM = "${htmlTable}";

    /** Html Table Replace Name */
    public static final String CONT_TP_HTML = "text/html";

    /** VAR_CHAR_CONST */
    public static final String VAR_CHAR_CONST = "VAR_CHAR_CONST";

    /** VAR_CHAR_CONST */
    public static final String JOB_COUNT_NM = "NTFY_CTR_RUN_JOB_COUNT";

    /** mail group id(from) : FROM0005 */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id(to) for Error : NWWB0010 */
    public static final String MAIL_GROUP_ID_TO = "NWWB0010";

    /** mail template id : NWWB0010M001 */
    public static final String MAIL_TEMPLATE_ID_FOR_BIZ_ERROR = "NWWB0010M001";

    /** <pre>batchId : NWWB001001</pre> */
    public static final String BATCH_ID = "NWWB001001";

    /** <pre>batchNm : Notification Batch</pre> */
    public static final String BATCH_NM = "Notification Batch";

    /** mail template key(batchId) : batchId */
    public static final String MAIL_TEMPLATE_KEY_BATCH_ID = "batchId";

    /** mail template key(batchNm) : batchNm */
    public static final String MAIL_TEMPLATE_KEY_BATCH_NM = "batchNm";

    /** mail template key(batchProcLogId) : batchProcLogId */
    public static final String MAIL_TEMPLATE_KEY_BATCH_PROC_LOG_ID = "batchProcLogId";

    /** MAIL_TEMPLATE_KEY_MSG_INFO */
    public static final String MAIL_TEMPLATE_KEY_MSG_INFO = "ErrorInfo";

    // START 2022/09/19 R.Azucena [QC#60563 ADD]
    /** VAR_CHAR_CONST_NM:  NTFY_CTR_HTML_ERR_MSG*/
    public static final String NTFY_CTR_HTML_ERR_MSG = "NTFY_CTR_HTML_ERR_MSG";

    /** Error Mail Message Text */
    public static final String DEFAULT_NTFY_CTR_HTML_ERR_MSG = "Error Message: The system cannot send notification data, because there are too many data.";

    /** Max Length of Mail Body */
    public static final int MAX_ML_BODY_LEN = 1000000;
    // END 2022/09/19 R.Azucena [QC#60563 ADD]
}
