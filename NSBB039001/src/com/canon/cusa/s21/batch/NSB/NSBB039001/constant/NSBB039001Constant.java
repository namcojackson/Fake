package com.canon.cusa.s21.batch.NSB.NSBB039001.constant;

/**
 * <pre>
 * NSBB039001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/04/2016   Hitachi         J.Kim           Create          N/A
 * 07/12/2016   Hitachi         Y.Takeno        Update          QC#8423
 * 08/24/2016   Hitachi         K.Kasai         Update          QC#13662,13678
 * 10/04/2016   Hitachi         T.Tomita        Update          QC#11896
 * 11/15/2016   Hitachi         T.Tomita        Update          QC#15957
 * 12/15/2016   Hitachi         N.Arai          Update          QC#16570
 * 01/10/2018   CITS            M.Naito         Update          QC#18889
 * 02/19/2018   CITS            M.Naito         Update          QC#24203
 * 2019/02/15   Hitachi         K.Kitachi       Update          QC#30165
 * 2019/08/23   Hitachi         K.Kitachi       Update          QC#52626
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 * </pre>
 */
public class NSBB039001Constant {

    /** Mode: 01 : Create FSR */
    public static final String MODE_CREATE_FSR = "01";

    /** Process Mode: 01 Add Task */
    public static final String MODE_ADD_TASK = "01";

    /**
     * BATCH_PROGRAM_ID(NSBB039001)
     */
    public static final String PROGRAM_ID = "NSBB039001";

    /**
     * BATCH_BUSINESS_ID(NSBB0390)
     */
    public static final String BUSINESS_ID = "NSBB0390";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /**
     * Error Msg : Global Company Code is mandatory.
     */
    public static final String NASM0010E = "NASM0010E";

    /**
     * Error Msg : There is no parameter in [@]. 
     */
    public static final String NSBM0032E = "NSBM0032E";

// START 2016/07/12 [QC#8423, ADD]
    /**
     * Error Msg : "@" doesn't exist in the VAR_CHAR_CONST
     **/
    public static final String NSBM0069E = "NSBM0069E";
// END   2016/07/12 [QC#8423, ADD]

    /**
     * Error Msg : Sales date cannot be obtained.
     */
    public static final String NASM0011E = "NASM0011E";

    /**
     * Error Msg : An error occurred in API. <APIID: [@], Error Code:
     * [@], Data Key: [@]>
     */
    public static final String NSAM0003E = "NSAM0003E";

    // add start 2016/10/04 CSA Defect#11896
    /**
     * @ does not exist in @.
     */
    public static final String NSBM0025E = "NSBM0025E";
    // add end 2016/10/04 CSA Defect#11896

    /** Mail Group From(FROM0005) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail Group To(NSBB0390) */
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

    // START 2019/08/23 K.Kitachi [QC#52626, ADD]
    /** Substring Length : 60 */
    public static final int SUBSTRING_LEN60 = 60;
    // END 2019/08/23 K.Kitachi [QC#52626, ADD]

    /** ISTL_CALL_EXCL_ORD_TP */
    public static final String ISTL_CALL_EXCL_ORD_TP = "ISTL_CALL_EXCL_ORD_TP";

    /** SERVICE_EXCHANGE */
    public static final String SERVICE_EXCHANGE = "SERVICE_EXCHANGE";

    /** Date Format(yyyyMMddHHmmss) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmss";

    // START 2016/08/24 [QC#13662,13678, ADD]
    /** Default HHmmss Value */
    public static final String DEF_HHMMSS = "000000";
    // END 2016/08/24 [QC#13662,13678, ADD]

// START 2016/07/12 [QC#8423, ADD]
    /** VAR_CHAR_CONST : SVC_ISTL_NOT_EXIST_EML_ADDR */
    public static final String VAR_CHAR_CONST_KEY_ISTL_NOT_EXIST_EML_ADDR = "SVC_ISTL_NOT_EXIST_EML_ADDR";
// END   2016/07/12 [QC#8423, ADD]

    // add start 2016/11/15 CSA Defect#15957
    /** VAR_CHAR_CONST : NSBB0390_CTAC_PSN_TP_CD */
    public static final String VAR_CHAR_CONST_NSBB0390_CTAC_PSN_TP_CD = "NSBB0390_CTAC_PSN_TP_CD";
    // add end 2016/11/15 CSA Defect#15957

 // START 2016/12/15 N.Arai [QC#16570, MOD]
    /** String Length : 6 */
    public static final int STRING_LEN6 = 6;
 // END 2016/12/15 N.Arai [QC#16570, MOD]

    // START 2018/01/10 M.Naito [QC#18889, ADD]
    /** NUM_CONST : MEET_TRUCK_ERL_START_DAYS_ESS */
    public static final String NUM_CONST_MEET_TRUCK_ERL_START_DAYS_ESS = "MEET_TRUCK_ERL_START_DAYS_ESS";

    /** NUM_CONST : MEET_TRUCK_ERL_START_DAYS_LFS */
    public static final String NUM_CONST_MEET_TRUCK_ERL_START_DAYS_LFS = "MEET_TRUCK_ERL_START_DAYS_LFS";

    /** NUM_CONST : MEET_TRUCK_ERL_START_DAYS_PPS */
    public static final String NUM_CONST_MEET_TRUCK_ERL_START_DAYS_PPS = "MEET_TRUCK_ERL_START_DAYS_PPS";

    /** VAR_CHAR_CONST : MEET_TRUCK_ERL_START_TM_ESS */
    public static final String VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_ESS = "MEET_TRUCK_ERL_START_TM_ESS";

    /** VAR_CHAR_CONST : MEET_TRUCK_ERL_START_TM_LFS */
    public static final String VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_LFS = "MEET_TRUCK_ERL_START_TM_LFS";

    /** VAR_CHAR_CONST : MEET_TRUCK_ERL_START_TM_PPS */
    public static final String VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_PPS = "MEET_TRUCK_ERL_START_TM_PPS";

    /** NUM_CONST : MEET_TRUCK_RSP_TM_MN_AOT */
    public static final String NUM_CONST_MEET_TRUCK_RSP_TM_MN_AOT = "MEET_TRUCK_RSP_TM_MN_AOT";

    /** Hour minute string length : HHMMDD */
    public static final int HOUR_MINUTE_LEN = 4;

    /** Error Msg : Failed to update @ table.[@] */
    public static final String NSBM0120E = "NSBM0120E";

    /** SVC_TASK */
    public static final String SVC_TASK = "SVC_TASK";

    // END 2018/01/10 M.Naito [QC#18889, ADD]
    // START 2018/02/19 M.Naito [QC#24203, ADD]
    /** OUT_TRTY_SVC_BR_CD */
    public static final String OUT_TRTY_SVC_BR_CD = "OUT_TRTY_SVC_BR_CD";
    // END 2018/02/19 M.Naito [QC#24203, ADD]

    // START 2019/02/15 K.Kitachi [QC#30165, ADD]
    /** String : "," */
    public static final String STR_COMMA = ",";

    /** VAR_CHAR_CONST_NM: CLICK_SEND_EXCL_CALL_TP */
    public static final String CLICK_SEND_EXCL_CALL_TP = "CLICK_SEND_EXCL_CALL_TP";
    // END 2019/02/15 K.Kitachi [QC#30165, ADD]

    // START 2023/09/05 K.Watanabe [QC#53408, ADD]
    /** VAR_CHAR_CONST_NM: ACC_CALL_OVRD_CALL_TP */
    public static final String ACC_CALL_OVRD_CALL_TP = "ACC_CALL_OVRD_CALL_TP";

    /** Direct Sales Service Call Type : Accessory Install */
    public static final String DS_SVC_CALL_TP_CD_ACC_INSTALL = "B";
    // END 2023/09/05 K.Watanabe [QC#53408, ADD]
}
