/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB721001.constant;

/**
 * <pre>
 * CSMP Price List sync process.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/18   Fujitsu         W.Honda         Create          N/A
 * 2016/06/14   Fujitsu         W.Honda         Update          QC#9945
 *</pre>
 */

public class NMAB721001Constant {

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "Global Company Code";

    /** DEFAULT_COMMIT_UNIT */
    public static final int DEFAULT_COMMIT_UNIT = 1000;

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** VAR_CHAR_CONST Key : CSMP_EML_SEND_CD */
    public static final String CSMP_EML_SEND_CD = "CSMP_EML_SEND_CD";

    /** NUM_CONST Key : CSMP_ERR_DTL_CNT */
    public static final String CSMP_ERR_DTL_CNT = "CSMP_ERR_DTL_CNT";

    /** Default Error Message Limit */
    public static final int DEFAULT_ERR_MSG_LIMIT = 200;

    /** Merchandise Code Digit : 8 */
    public static final int DIGIT_FOR_MDSE_CD = 8;

    /** 0 : SubString From Position */
    public static final int SUBSTR_FROM_POS = 0;

    /** 1 : Minimum Record Count */
    public static final int MIN_REC_CNT = 1;

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
    public static final String MAIL_GROUP_ID_TO = "NMAB7210";

    /** Mail Group id for To. */
    public static final String MAIL_TEMPLATE_ID = "NMAB7210M001";

    /** Mail Batch Program ID */
    public static final String BATCH_PROGRAM_ID = "NMAB7210";

    /** Mail Batch Program ID */
    public static final String BATCH_PROGRAM_NAME = "CSMP Price List sync process";

    // QC#9945 2016/06/14 Mod start
//    /** Date Format : yyyyMMddHHmmss */
//    public static final String DATEFORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    /** Date Format : MM/DD/YYYY HH:mm:ss */
    public static final String SET_TIMESTAMP_FORMAT = "MM/dd/yyyy HH:mm:ss";

    /** Date length : 8 */
    public static final int YYYYMMDD_LENGTH = 8;
    // QC#9945 2016/06/14 Mod end

    /** Mail Parameter : batchId */
    public static final String MAIL_FIELD_BATCH_ID = "batchId";

    /** Mail Parameter : batchName */
    public static final String MAIL_FIELD_BATCH_NAME = "batchNm";

    /** Mail Parameter : Timestamp */
    public static final String MAIL_FIELD_TIMESTAMP = "Timestamp";

    /** Mail Parameter : total_records_cnt */
    public static final String MAIL_FIELD_TOTAL_CNT = "total_records_cnt";

    /** Mail Parameter : complete_cnt */
    public static final String MAIL_FIELD_COMPLETED_CNT = "complete_cnt";

    /** Mail Parameter : process_cnt */
    public static final String MAIL_FIELD_PROCESSED_CNT = "process_cnt";

    /** Mail Parameter : error_cnt */
    public static final String MAIL_FIELD_ERROR_CNT = "error_cnt";

    /** Mail Parameter : warning_cnt */
    public static final String MAIL_FIELD_WARNING_CNT = "warning_cnt";

    /** Mail Parameter : errMsg */
    public static final String MAIL_FIELD_ERR_MSG = "errorInfo";

    /** Mail Character Set */
    public static final String MAIL_CHARSET = "ISO-8859-1";

    /** Mail Field Digit For Credit List */
    public static final int MAIL_DIGIT_FOR_CREDIT_LIST = 21;

    /** Mail Field Digit For Item Code */
    public static final int MAIL_DIGIT_FOR_ITEM_CODE = 17;

    /** Mail Field Digit For CSA Item */
    public static final int MAIL_DIGIT_FOR_CSA_ITEM = 41;

    /** Mail Field Digit For IUD Flag */
    public static final int MAIL_DIGIT_FOR_IUD_FLAG = 11;

    /** Mail Field Digit For Update Date */
    public static final int MAIL_DIGIT_FOR_UPD_DATE = 12;

    /** Mail Field Digit For Error Message */
    public static final int MAIL_DIGIT_FOR_ERR_MSG = 61;

    /** Error Info Header : Exception Records in CANON_ERRORS */
    public static final String ERR_INFO_HEADER = "Exception Records in CANON_ERRORS";

    /** Error Info Header Mark : ============================ */
    public static final String ERR_INFO_HEADER_MARK = "=============================";

    /** Error Info Header Rule : ----------- */
    public static final String ERR_MSG_HEADER_RULE_FOR_CREDIT_LIST = "-----------";

    /** Error Info Header Rule : --------- */
    public static final String ERR_MSG_HEADER_RULE_FOR_ITEM_CODE = "---------";

    /** Error Info Header Rule : ------------------ */
    public static final String ERR_MSG_HEADER_RULE_FOR_CSA_ITEM = "------------------";

    /** Error Info Header Rule : --- */
    public static final String ERR_MSG_HEADER_RULE_FOR_IUD_FLAG = "---";

    /** Error Info Header Rule : ------------ */
    public static final String ERR_MSG_HEADER_RULE_FOR_UPD_DATE = "------------";

    /** Error Info Header Rule : ----------------------------- */
    public static final String ERR_MSG_HEADER_RULE_FOR_ERR_MSG = "-----------------------------";

    /** Error Info Header : Credit List */
    public static final String ERR_INFO_HEADER_FOR_CREDIT_LIST = "Credit List";

    /** Error Info Header : Item Code */
    public static final String ERR_INFO_HEADER_FOR_ITEM_CODE = "Item Code";

    /** Error Info Header : CSA Item */
    public static final String ERR_INFO_HEADER_FOR_CSA_ITEM = "CSA Item";

    /** Error Info Header : IUD Flag */
    public static final String ERR_INFO_HEADER_FOR_IUD_FLAG = "IUD Flag";

    /** Error Info Header : Update Date */
    public static final String ERR_INFO_HEADER_FOR_UPD_DATE = "Update Date";

    /** Error Info Header : Error Message */
    public static final String ERR_INFO_HEADER_FOR_ERR_MSG = "Error Message";

    /** Message space */
    public static final String MSG_SPACE = " ";

    /** Mail Message New Line */
    public static final String CRLF = "\r\n";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** E-mail address <Type: [@], Group: [@], Key: [@]>  cannot be obtained. */
    public static final String NMAM8028E = "NMAM8028E";

    /** The e-mail template <template ID: [@]> cannot be obtained. */
    public static final String NMAM8031E = "NMAM8031E";

    /** @ doesn't exist in @. */
    public static final String NMAM8132E = "NMAM8132E";

    /** Invalid CSMP Transaction Status - [@] */
    public static final String NMAM8519E = "NMAM8519E";

    /** Invalid update date */
    public static final String NMAM8520E = "NMAM8520E";

    /** Item does not exist in Item Master - [@] */
    public static final String NMAM8521E = "NMAM8521E";

    /** Item is inactive in Item Master - [@] */
    public static final String NMAM8522E = "NMAM8522E";

    /** Pricelist Mapping does not exist - [@] */
    public static final String NMAM8523E = "NMAM8523E";

    /** Pricelist Mapping is disabled - [@] */
    public static final String NMAM8524E = "NMAM8524E";

    /** CLEANUP - Duplicate [@] records with same item,pricelist,category and amount */
    public static final String NMAM8525E = "NMAM8525E";

    /** Pricelist does not exist - [@] */
    public static final String NMAM8526E = "NMAM8526E";

    /** Pricelist is inactive -[@] */
    public static final String NMAM8527E = "NMAM8527E";

    /** Item exists with same price on pricelist - [@] - [@] - [@] */
    public static final String NMAM8528E = "NMAM8528E";

    /** Item cannot be end-dated - price mismatch - [@] - [@] -[@] */
    public static final String NMAM8529E = "NMAM8529E";

    /** Item exists with a more recent price on pricelist - [@] - [@] - [@] */
    public static final String NMAM8530E = "NMAM8530E";

    /** Duplicate Item in Target Data - [@] */
    public static final String NMAM8531E = "NMAM8531E";

    /** Duplicate record - not being processed */
    public static final String NMAM8540E = "NMAM8540E";

    /** Item being loaded with more than one price - [@] - [@] - [@] */
    public static final String NMAM8541E = "NMAM8541E";

    /** I and U */
    public static final String I_AND_U = "I and U";

    /** CSMP_TRX_STS_CD : "I" */
    public static final String I_CSMP_TRX_STS_CD = "I";

    /** CSMP_TRX_STS_CD : "U" */
    public static final String U_CSMP_TRX_STS_CD = "U";

    /** CSMP_TRX_STS_CD : "D" */
    public static final String D_CSMP_TRX_STS_CD = "D";

    /** CSMP_TRX_STS_CD List */
    public static final String[] CSMP_TRX_STS_CD_LIST = new String[] {
        I_CSMP_TRX_STS_CD
        , U_CSMP_TRX_STS_CD
        , D_CSMP_TRX_STS_CD
    };

    /** NUM_CONST */
    public static final String NUM_CONST = "NUM_CONST";

    /** BATCH_PROC_DT */
    public static final String BATCH_PROC_DT = "Batch Process Date";

    /** Table Name : CSMP_PRC_INTFC */
    public static final String CSMP_PRC_INTFC = "CSMP_PRC_INTFC";

    /** Table Name : PRC_LIST_EQUIP */
    public static final String PRC_LIST_EQUIP = "PRC_LIST_EQUIP";

    /** CSMP_PRC_INTFC_PK */
    public static final String CSMP_PRC_INTFC_PK = "CSMP_PRC_INTFC_PK";

    /** CUSA_MDSE_CD */
    public static final String CUSA_MDSE_CD = "CUSA_MDSE_CD";

    /** CSA_MDSE_CD */
    public static final String CSA_MDSE_CD = "CSA_MDSE_CD";

    /** CSMP_PROC_STS_CD */
    public static final String CSMP_PROC_STS_CD = "CSMP_PROC_STS_CD";

    /** CSMP_TRX_STS_CD */
    public static final String CSMP_TRX_STS_CD = "CSMP_TRX_STS_CD";

    /** LAST_UPD_DT */
    public static final String LAST_UPD_DT = "LAST_UPD_DT";

    /** CR_LIST_GNRN_NUM */
    public static final String CR_LIST_GNRN_NUM = "CR_LIST_GNRN_NUM";

    /** CR_LIST_TXT */
    public static final String CR_LIST_TXT = "CR_LIST_TXT";

    /** CSA_PRC_CATG_CD */
    public static final String CSA_PRC_CATG_CD = "CSA_PRC_CATG_CD";

    /** CSMP_CR_AMT */
    public static final String CSMP_CR_AMT = "CSMP_CR_AMT";

    /** PRC_LIST_EQUIP_PRC_AMT */
    public static final String PRC_LIST_EQUIP_PRC_AMT = "PRC_LIST_EQUIP_PRC_AMT";

    /** PRC_LIST_EQUIP_PK */
    public static final String PRC_LIST_EQUIP_PK = "PRC_LIST_EQUIP_PK";

    /** EFF_FROM_DT */
    public static final String EFF_FROM_DT = "EFF_FROM_DT";

    // QC#9945 2016/06/14 Del start
//    /** CREDIT_LIST */
//    public static final String CREDIT_LIST = "CREDIT_LIST";
    // QC#9945 2016/06/14 Del end

    /** CSMP_ERR_MSG_TXT */
    public static final String CSMP_ERR_MSG_TXT = "CSMP_ERR_MSG_TXT";

    /** BEFORE_COUNT : -1 */
    public static final int BEFORE_COUNT = -1;

    /** Error Message Update Blank */
    public static final String ERR_MSG_UPDATE_BLANK = "";

    /** Delimiter : ,(Comma) */
    public static final String COMMA = ",";

    /** CSMP_TRX_STS_CD List */
    public static final String[] KEY_CREATE_LIST = new String[] {
        CSA_MDSE_CD
        , CSA_PRC_CATG_CD
    };

    /** CSMP_TRX_STS_CD List */
    public static final String[] KEY_CREATE_LIST_AND_AMT = new String[] {
        CSA_MDSE_CD
        , CSA_PRC_CATG_CD
        , CSMP_CR_AMT
    };

    /** CSMP_TRX_STS_CD List */
    public static final String[] KEY_CREATE_LIST_BY_STS = new String[] {
        CSA_MDSE_CD
        , CSA_PRC_CATG_CD
        , CSMP_CR_AMT
        , CR_LIST_TXT
    };
}
