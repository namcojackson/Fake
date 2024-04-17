/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB720001.constant;


/**
 *<pre>
 * CSMP Contract List sync process Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/23   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public final class NMAB720001Constant {
    /** DEFAULT_COMMIT_UNIT */
    public static final int DEFAULT_COMMIT_UNIT = 1000;

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** The value for [@] must be smaller than [@]. */
    public static final String NMAM0043E = "NMAM0043E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** @ is null. */
    public static final String NMAM8533E = "NMAM8533E";

    /** @ is invalid. */
    public static final String NMAM8534E = "NMAM8534E";

    /** @ is not number. */
    public static final String NMAM8535E = "NMAM8535E";

    /** Data not found for this @. */
    public static final String NMAM8536E = "NMAM8536E";

    /** @ is not 5 digits. */
    public static final String NMAM8537E = "NMAM8537E";

    /**
     * E-mail address <Type: [@], Group: [@], Key: [@]> cannot be
     * obtained.
     */
    public static final String NMAM8028E = "NMAM8028E";

    /** The e-mail template <template ID: [@]> cannot be obtained. */
    public static final String NMAM8031E = "NMAM8031E";

    /** @ doesn't exist in @. */
    public static final String NMAM8132E = "NMAM8132E";

    /** @  is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** Num Const CSMP_ERR_DTL_CNT */
    public static final String CSMP_ERR_DTL_CNT = "CSMP_ERR_DTL_CNT";

    /** Var Char Const CSMP_EML_SEND_CD */
    public static final String CSMP_EML_SEND_CD = "CSMP_EML_SEND_CD";

    /** Var Char Const CSMP_CONTR_TGT_PROC_STS_CD */
    public static final String CSMP_CONTR_TGT_PROC_STS_CD = "CSMP_CONTR_TGT_PROC_STS_CD";

    /** VAR_CHAR_CONST */
    public static final String VAR_CHAR_CONST = "VAR_CHAR_CONST";

    /** NUM_CONST */
    public static final String NUM_CONST = "NUM_CONST";

    /** ASTERISK */
    public static final String ASTERISK = "*";

    /** ZERO */
    public static final String ZERO = "0";

    /** STS_A */
    public static final String STS_A = "A";

    /** STS_N */
    public static final String STS_N = "N";

    /** STS_I */
    public static final String STS_I = "I";

    /** STS_U */
    public static final String STS_U = "U";

    // mail

    /** Mail Message Hyphen */
    public static final String HYPHEN = "-";

    /** Mail Type : From */
    public static final String MAIL_TYPE_FROM = "From";

    /** Mail Group Id Key: From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** Mail Key From : DS */
    public static final String MAIL_KEY_FROM = "NMA";

    /** Mail Type : To */
    public static final String MAIL_TYPE_TO = "To";

    /** Mail Group Id Key: To */
    public static final String MAIL_GROUP_ID_TO = "NMAB7200";

    /** Mail Group id for To. */
    public static final String MAIL_TEMPLATE_ID = "NMAB7200M001";

    /** Mail Batch Program ID */
    public static final String BATCH_PROGRAM_ID = "NMAB7200";

    /** Mail Batch Program ID */
    public static final String BATCH_PROGRAM_NAME = "CSMP Contract List sync process Batch";

    /** Mail Parameter : batchId */
    public static final String MAIL_FIELD_TOTAL = "totalNumOfContractsSync";

    /** Mail Parameter : batchName */
    public static final String MAIL_FIELD_SCUCECC_TOTAL = "processNumOfContractsSync";

    /** Mail Parameter : batchName */
    public static final String MAIL_FIELD_ERROR_TOTAL = "errorNumOfContractsSync";

    /** Mail Parameter : batchId */
    public static final String MAIL_FIELD_MAX_ERROR = "csmpErrorDetailCount";

    /** Mail Parameter : errMsg */
    public static final String MAIL_FIELD_ERR_MSG = "ErrorInfo";

    /** Mail Character Set */
    public static final String MAIL_CHARSET = "ISO-8859-1";

    /** Mail Message New Line */
    public static final String CRLF = "\r\n";

}
