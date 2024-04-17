package com.canon.cusa.s21.batch.NSB.NSBB103001.constant;

/**
 * <pre>
 * NSBB103001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/12/13   Hitachi         S.Naya          Create          QC#61300
 * </pre>
 */
public class NSBB103001Constant {

    /** Mode: 01 : Create FSR */
    public static final String MODE_CREATE_FSR = "01";

    /** Process Mode: 01 Add Task */
    public static final String MODE_ADD_TASK = "01";

    /**
     * BATCH_PROGRAM_ID(NSBB103001)
     */
    public static final String PROGRAM_ID = "NSBB103001";

    /**
     * BATCH_BUSINESS_ID(NSBB1030)
     */
    public static final String BUSINESS_ID = "NSBB1030";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * Error Msg : Failed to update @ table. @ is @.
     */
    public static final String NPAM1003E = "NPAM1003E";

    /**
     * Error Msg : Global Company Code is mandatory.
     */
    public static final String NASM0010E = "NASM0010E";

    /**
     * Error Msg : There is no parameter in [@]. 
     */
    public static final String NSBM0032E = "NSBM0032E";

    /**
     * Error Msg : "@" doesn't exist in the VAR_CHAR_CONST
     **/
    public static final String NSBM0069E = "NSBM0069E";

    /**
     * Error Msg : Sales date cannot be obtained.
     */
    public static final String NASM0011E = "NASM0011E";

    /**
     * Error Msg : An error occurred in API. <APIID: [@], Error Code:
     * [@], Data Key: [@]>
     */
    public static final String NSAM0003E = "NSAM0003E";

    /**
     * @ does not exist in @.
     */
    public static final String NSBM0025E = "NSBM0025E";

    /** Error Msg : Failed to update @ table.[@] */
    public static final String NSBM0120E = "NSBM0120E";

    /** Mail Group From(FROM0005) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail Group To(NSBB1030) */
    public static final String MAIL_GROUP_ID_TO = BUSINESS_ID;

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = BUSINESS_ID + "M001";

    /** mail message header */
    public static final String MAIL_MSG_HEADER = "ErrorMessage";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** Mail Item (${batchId}) */
    public static final String MAIL_ITEM_BATCH_ID = "batchId";

    /** Mail Item (${errDate}) */
    public static final String MAIL_ITEM_ERR_DATE = "errDate";

    /** Mail Item (${message}) */
    public static final String MAIL_ITEM_ERR_MSG = "message";

    /** Service Machine Transaction Type Code Length */
    public static final int SVC_MACH_TRX_TP_CD_LEN = 20;

    /** Date Time */
    public static final String FORMAT_HHMMSS = "HHmmss";

    /** Install Additional Comment Text Length */
    public static final int ISTL_ADDL_CMNT_TXT_LEN = 60;

    /** Substring Length : 8 */
    public static final int SUBSTRING_LEN8 = 8;

    /** Substring Length : 14 */
    public static final int SUBSTRING_LEN14 = 14;

    /** Substring Length : 60 */
    public static final int SUBSTRING_LEN60 = 60;

    /** Date Format(yyyyMMddHHmmss) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmss";

    /** Default HHmmss Value */
    public static final String DEF_HHMMSS = "000000";

    /** VAR_CHAR_CONST : SVC_ISTL_NOT_EXIST_EML_ADDR */
    public static final String VAR_CHAR_CONST_KEY_ISTL_NOT_EXIST_EML_ADDR = "SVC_ISTL_NOT_EXIST_EML_ADDR";

    /** VAR_CHAR_CONST : NSBB1030_CTAC_PSN_TP_CD */
    public static final String VAR_CHAR_CONST_NSBB1030_CTAC_PSN_TP_CD = "NSBB0390_CTAC_PSN_TP_CD";

    /** String Length : 6 */
    public static final int STRING_LEN6 = 6;

    /** Hour minute string length : HHMMDD */
    public static final int HOUR_MINUTE_LEN = 4;

    /** SVC_TASK */
    public static final String SVC_TASK = "SVC_TASK";

    /** OUT_TRTY_SVC_BR_CD */
    public static final String OUT_TRTY_SVC_BR_CD = "OUT_TRTY_SVC_BR_CD";

    /** String : "," */
    public static final String STR_COMMA = ",";

    /** VAR_CHAR_CONST_NM: CLICK_SEND_EXCL_CALL_TP */
    public static final String CLICK_SEND_EXCL_CALL_TP = "CLICK_SEND_EXCL_CALL_TP";

    /** VAR_CHAR_CONST_NM: ACC_CALL_OVRD_CALL_TP */
    public static final String ACC_CALL_OVRD_CALL_TP = "ACC_CALL_OVRD_CALL_TP";

    /** Direct Sales Service Call Type : Accessory Install */
    public static final String DS_SVC_CALL_TP_CD_ACC_INSTALL = "B";
}
