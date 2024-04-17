/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */

package com.canon.cusa.s21.batch.NMA.NMAB724001.constant;

/**
 *<pre>
 * I105 CSMP Inbound Contract I/F (WMB)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public final class NMAB724001Constant {

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "Global Company Code";

    /** Interface Id */
    public static final String INTFC_ID = "Interface Id";

    /** Transaction ID */
    public static final String TRX_ID = "Transaction ID";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** TRANSACTION_ID */
    public static final String TRANSACTION_ID = "TRANSACTION_ID";

    /** Error Message */
    public static final String ERR_MSG = "Error Message";

    /** Mail Message New Line */
    public static final String CRLF = "\r\n";

    /** CSMP_CSA_NUM */
    public static final String CSMP_CSA_NUM = "CSMP_CSA_NUM";

    /** CSMP_CSA_USR_NM */
    public static final String CSMP_CSA_USR_NM = "CSMP_CSA_USR_NM";

    /** CSMP_CUSA_USR_NUM */
    public static final String CSMP_CUSA_USR_NUM = "CSMP_CUSA_USR_NUM";

    /** CSMP_RCV_CONTR_NUM */
    public static final String CSMP_RCV_CONTR_NUM = "CSMP_RCV_CONTR_NUM";

    /** CSMP_CONTR_STS_TXT */
    public static final String CSMP_CONTR_STS_TXT = "CSMP_CONTR_STS_TXT";

    /** CSMP_RG_CD */
    public static final String CSMP_RG_CD = "CSMP_RG_CD";

    /** CSMP_DLR_TXT */
    public static final String CSMP_DLR_TXT = "CSMP_DLR_TXT";

    /** CSMP_EFF_FROM_DT */
    public static final String CSMP_EFF_FROM_DT = "CSMP_EFF_FROM_DT";

    /** CSMP_EFF_THRU_DT */
    public static final String CSMP_EFF_THRU_DT = "CSMP_EFF_THRU_DT";

    /** CSMP_CATG_CD */
    public static final String CSMP_CATG_CD = "CSMP_CATG_CD";

    /** CSMP_CR_LIST_TXT */
    public static final String CSMP_CR_LIST_TXT = "CSMP_CR_LIST_TXT";

    /** CSMP_CR_LIST_TXT */
    public static final String CSMP_CR_LIST_GEN_NUM = "CSMP_CR_LIST_GEN_NUM";

    /** CSMP_VLD_FROM_DT */
    public static final String CSMP_VLD_FROM_DT = "CSMP_VLD_FROM_DT";

    /** CSMP_PREV_RCV_CONTR_NUM */
    public static final String CSMP_PREV_RCV_CONTR_NUM = "CSMP_PREV_RCV_CONTR_NUM";

    /** CSMP_PREV_CUSA_USR_NUM */
    public static final String CSMP_PREV_CUSA_USR_NUM = "CSMP_PREV_CUSA_USR_NUM";

    /** CSMP_TRX_TXT */
    public static final String CSMP_TRX_TXT = "CSMP_TRX_TXT";

    /** CSMP_LAST_UPD_TS */
    public static final String CSMP_LAST_UPD_TS = "CSMP_LAST_UPD_TS";

    /** csmpErrMsgTxt */
    public static final String CSMP_ERR_MSG_TXT = "csmpErrMsgTxt";

    /** dlrRefNum */
    public static final String DLR_REF_NUM = "dlrRefNum";

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

    /** [@] for [＠] is changed from [@] to blank. */
    public static final String NMAM8547E = "NMAM8547E";

    /** 20 : DLR_REF_NUM Max Length */
    public static final int DLR_REF_NUM_MAX_LENGTH = 20;

    /** 15 : Transaction Id Length For Error Mail*/
    public static final int TRX_ID_LENGTH_FOR_ERR = 15;

    /** 0 : SubString From Position */
    public static final int SUBSTR_FROM_POS = 0;

    /** Blank */
    public static final String BLANK = "";

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
    public static final String MAIL_GROUP_ID_TO = "NMAB7240";

    /** Mail Group id for To. */
    public static final String MAIL_TEMPLATE_ID = "NMAB7240M001";

    /** Mail Batch Program ID */
    public static final String BATCH_PROGRAM_ID = "NMAB7240";

    /** Mail Batch Program ID */
    public static final String BATCH_PROGRAM_NAME = "I105 CSMP Inbound Contract I/F";

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

}
