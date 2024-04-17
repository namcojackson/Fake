/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB494001.constant;

/**
 *<pre>
 * Load DUNS by batch (Outbound)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         T.Murai         Create          N/A
 * 2016/06/20   Fujitsu         H.Ikeda         Update          QC#10493
 * 2018/03/30   Fujitsu         Hd.Sugawara     Update          QC#24462
 * 2018/07/03   Fujitsu         Mz.Takahashi    Update          QC#25339
 *</pre>
 */

public class NMAB494001Constant {

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "Global Company Code";

    /** Interface Id */
    public static final String INTFC_ID = "Interface Id";

    /** Classification Code */
    public static final String CLS_CD = "Classification Code";

    /** GL Classification Code */
    public static final String GL_CLS_CD = "GL Classification Code";

    /** GL Intercompany Code */
    public static final String GL_IN_CMPY_CD = "GL Intercompany Code";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** DEFAULT_COMMIT_SIZE */
    public static final int DEFAULT_COMMIT_SIZE = 1000;

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    // Del Start 2018/03/30 QC#24462
    ///** It failed to register [@]. */
    //public static final String NMAM0176E = "NMAM0176E";
    // Del End 2018/03/30 QC#24462

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** E-mail address <Type: [@], Group: [@], Key: [@]> cannot be obtained. */
    public static final String NMAM8028E = "NMAM8028E";

    /** The e-mail template <template ID: [@]> cannot be obtained. */
    public static final String NMAM8031E = "NMAM8031E";

    /** The data @ does not exist in the master. */
    public static final String NMAM8186E = "NMAM8186E";

    /** [@] is an invalid date. */
    public static final String NMAM8459E = "NMAM8459E";

    /** There is no parameter in [@]. */
    public static final String NMAM8504E = "NMAM8504E";

    /** Group ID @ for Duns criteria is set @ items. */
    public static final String NMAM8596E = "NMAM8596E";

    // Add Start 2018/03/30 QC#24462
    /** It failed to register [@]. (DS_ACCT_NUM: [@], LOC_NUM: [@]) */
    public static final String NMAM8678E = "NMAM8678E";

    /** It failed to register [DUNS_TRX_FILE]. (DUNS_FILE_TP_CD: [@]) */
    public static final String NMAM8679E = "NMAM8679E";
    // Add End 2018/03/30 QC#24462

    /** KEY VAR_CHAR_CONST : Classification Code */
    public static final String KEY_ACCT_CLS_CD = "NMAL2800_DEF_DS_ACCT_CLS_CD";

    /** KEY DEF_DPLY_COA_INFO : GL Classification Code */
    public static final String KEY_COA_INFO = "NMAL6700001";

    /** DUNS_CRIT_CD */
    public static final String DUNS_CRIT_CD = "DUNS_CRIT_CD";

    /** DUNS_CRIT_VAL_TXT */
    public static final String DUNS_CRIT_VAL_TXT = "DUNS_CRIT_VAL_TXT";

    /** DUNS_TRX_XTRCT */
    public static final String DUNS_TRX_XTRCT = "DUNS_TRX_XTRCT";

    /** DUNS_TRX_HDR */
    public static final String DUNS_TRX_HDR = "DUNS_TRX_HDR";

    // Del Start 2018/03/30 QC#24462
    ///** DUNS_TRX_FILE */
    //public static final String DUNS_TRX_FILE = "DUNS_TRX_FILE";
    // Del End 2018/03/30 QC#24462

    /** NMAI4900_01 */
    public static final String NMAI4900_01 = "NMAI4900_01";

    /** NMAI4900_02 */
    public static final String NMAI4900_02 = "NMAI4900_02";

    /** NMAI4900_03 */
    public static final String NMAI4900_03 = "NMAI4900_03";

    /** NMAI4900_04 */
    public static final String NMAI4900_04 = "NMAI4900_04";

    // Add Start 2018/07/03 QC#25339
    /** PTY_LOC_WRK */
    public static final String PTY_LOC_WRK = "PTY_LOC_WRK";

    /** PROS_PTY_LOC_WRK */
    public static final String PROS_PTY_LOC_WRK = "PROS_PTY_LOC_WRK";
    // Add End 2018/07/03 QC#25339

    /** Mail Type : From */
    public static final String MAIL_TYPE_FROM = "From";

    /** Mail Group Id Key: From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** Mail Key From : DS */
    public static final String MAIL_KEY_FROM = "NMA";

    /** Mail Type : To */
    public static final String MAIL_TYPE_TO = "To";

    /** Mail Group Id Key: To */
    public static final String MAIL_GROUP_ID_TO = "NMAB4940";

    /** Mail template id for ERROR. */
    public static final String MAIL_TEMPLATE_ERROR = "NMAB4940M001";

    /** Mail template id for NORMAL. */
    public static final String MAIL_TEMPLATE_NORMAL = "NMAB4940M002";

    /** Mail Key To : NORMAL */
    public static final String MAIL_KEY_TO_NORMAL = "NORMAL";

    /** Mail Key To : ERROR */
    public static final String MAIL_KEY_TO_ERROR = "ERROR";

    /** Mail Batch Program ID */
    public static final String BATCH_PROGRAM_ID = "NMAB4940";

    /** Mail Batch Program ID */
    public static final String BATCH_PROGRAM_NAME = "Load DUNS by batch(Outbound)";

    /** Mail Parameter : batchId */
    public static final String MAIL_FIELD_BATCH_ID = "batchId";

    /** Mail Parameter : batchName */
    public static final String MAIL_FIELD_BATCH_NAME = "batchNm";

    /** Mail Parameter : batchName */
    public static final String MAIL_FIELD_BATCH_PROC_LOG_ID = "batchProcLogId";

    /** Mail Parameter : errMsg */
    public static final String MAIL_FIELD_ERR_MSG = "ErrorInfo";

    /** Mail Parameter : sucMsg */
    public static final String MAIL_FIELD_SUC_MSG = "MailInfo";

    /** Mail Parameter FileName Max Length */
    public static final int MAX_FILE_NAME_LENGTH = 40;

    /** Mail Character Set */
    public static final String MAIL_CHARSET = "ISO-8859-1";

    /** Duns Transaction Header */
    public static final String MAIL_DUNS_TRX_HDR = "DunsTrxHdr";

    // Add Start 2018/03/30 QC#24462
    /** Direct Sales Account Number */
    public static final String MAIL_DS_ACCT_NUM = "DsAcctNum";

    /** Location Number */
    public static final String MAIL_LOC_NUM = "LocNum";
    // Add End 2018/03/30 QC#24462

    /** Date Time Pattern */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    /** End Date */
    public static final String END_DATE = "99991231";

    /** Mail Message Space */
    public static final String SPACE = " ";

    /** Mail Message Comma */
    public static final String COMMA = ",";

    /** Mail Message Equal */
    public static final String EQUAL = "=";

    /** Mail Message New Line */
    public static final String CRLF = "\r\n";

    /** GROUP_ID_ZERO */
    public static final String GROUP_ID_ZERO = "0";

    /** GROUP_ID_ONE */
    public static final String GROUP_ID_ONE = "1";

}
