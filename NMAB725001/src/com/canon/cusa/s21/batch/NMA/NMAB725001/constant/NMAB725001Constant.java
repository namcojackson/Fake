/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB725001.constant;

/**
 *<pre>
 * I105 CSMP Inbound Contract I/F (WMB)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */


public class NMAB725001Constant {

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "Global Company Code";

    /** Interface Id */
    public static final String INTFC_ID = "Interface Id";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** 15 : Transaction Id Length For Error Mail*/
    public static final int TRX_ID_LENGTH_FOR_ERR = 15;

    /** 20 : DLR_REF_NUM Max Length */
    public static final int CUSA_MDSE_CD_MAX_LENGTH = 16;

    /** 0 : DLR_REF_NUM SubString From Position */
    public static final int CUSA_MDSE_CD_SUBSTR_FROM_POS = 0;

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** E-mail address <Type: [@], Group: [@], Key: [@]>  cannot be obtained. */
    public static final String NMAM8028E = "NMAM8028E";

    /** The e-mail template <template ID: [@]> cannot be obtained. */
    public static final String NMAM8031E = "NMAM8031E";

    /** There is no parameter in [@]. */
    public static final String NMAM8504E = "NMAM8504E";

    /** CSA# Overflow: [@]. */
    public static final String NMAM8505E = "NMAM8505E";

    /** [@] is cut off. Please confirm this. */
    public static final String NMAM8512E = "NMAM8512E";

    /** CUSA Merchandise Code */
    public static final String CUSA_MDSE_CD = "CUSA Merchandise Code";

    /** CSMP_RTL_DLR_CD */
    public static final String CSMP_RTL_DLR_CD = "CSMP_RTL_DLR_CD";

    /** CR_LIST_GNRN_NUM */
    public static final String CR_LIST_GNRN_NUM = "CR_LIST_GNRN_NUM";

    /** CR_LIST_TXT */
    public static final String CR_LIST_TXT = "CR_LIST_TXT";

    /** CSMP_CUSA_MDSE_TXT */
    public static final String CSMP_CUSA_MDSE_TXT = "CSMP_CUSA_MDSE_TXT";

    /** CSMP_CR_AMT */
    public static final String CSMP_CR_AMT = "CSMP_CR_AMT";

    /** CSMP_TRX_TP_CD */
    public static final String CSMP_TRX_TP_CD = "CSMP_TRX_TP_CD";

    /** LAST_UPD_DT */
    public static final String LAST_UPD_DT = "LAST_UPD_DT";

    /** csmpErrMsgTxt */
    public static final String CSMP_ERR_MSG_TXT = "csmpErrMsgTxt";

    /** Auto Sequence Key : CSMP_PRC */
    public static final String AUTO_SEQ_KEY = "CSMP_PRC";

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
    public static final String MAIL_GROUP_ID_TO = "NMAB7250";

    /** Mail Group id for To. */
    public static final String MAIL_TEMPLATE_ID = "NMAB7250M001";

    /** Mail Batch Program ID */
    public static final String BATCH_PROGRAM_ID = "NMAB7250";

    /** Mail Batch Program ID */
    public static final String BATCH_PROGRAM_NAME = "I95 CSMP Inbound Credit Price List IF (WMB)";

    /** Mail Parameter : batchId */
    public static final String MAIL_FIELD_BATCH_ID = "batchId";

    /** Mail Parameter : batchName */
    public static final String MAIL_FIELD_BATCH_NAME = "batchNm";

    /** Mail Parameter : batchName */
    public static final String MAIL_FIELD_BATCH_PROC_LOG_ID = "batchProcLogId";

    /** Mail Parameter : errMsg */
    public static final String MAIL_FIELD_ERR_MSG = "ErrorInfo";

    /** Mail Character Set */
    public static final String MAIL_CHARSET = "ISO-8859-1";

    /** TRANSACTION_ID */
    public static final String TRANSACTION_ID = "TRANSACTION_ID";

    /** Transaction ID */
    public static final String TRX_ID = "Transaction ID";

    /** Error Message */
    public static final String ERR_MSG = "Error Message";

    /** Mail Message New Line */
    public static final String CRLF = "\r\n";

    /** 0 : SubString From Position */
    public static final int SUBSTR_FROM_POS = 0;

}
