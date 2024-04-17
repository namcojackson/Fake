/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB098001;

/**
 *<pre>
 *  Create Task From Walmart Request
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/03/15   Hitachi         M.Nakajima        Create        QC#60012
 * 2023/07/10   Hitachi         N.Takatsu         Update        QC#60012
 * 2023/07/28   Hitachi         N.Takatsu         Update        QC#60012
 * 2023/08/03   Hitachi         K.Kitachi         Update        QC#60012
 * 2023/08/08   Hitachi         N.Takatsu         Update        QC#61771
 *</pre>
 */
public class NSBB098001Constant {

    /**
     * BATCH_PROGRAM_ID : NSBB0980
     */
    public static final String BATCH_PROGRAM_ID = "NSBB0980";

    /**
     * BUSINESS_ID : NSBB0980
     */
    public static final String BUSINESS_ID = "NSBB0980";

    /**
     * Date Format : yyyyMMddHHmmssSSS
     */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /**
     * Date Format : yyyyMMdd
     */
    public static final String DT_FORMAT_DT = "yyyyMMdd";

    /**
     * Date Format : HHmmss
     */
    public static final String DT_FORMAT_TM = "HHmmss";

    /**
     * filler string millisecond part
     */
    public static final String FILLER_MSEC_STRING = "000";

    /**
     * time-stamp length
     */
    public static final int LENGTH_TS = 14;

    /**
     * date length
     */
    public static final int LENGTH_DT = 8;

    /**
     * Message : There is no parameter in [@].
     */
    public static final String NSBM0032E = "NSBM0032E";

    /**
     * @ doesn't exist in the VAR_CHAR_CONST.
     */
    public static final String NSBM0069E = "NSBM0069E";

    /**
     * Message : The e-mail template <template ID: [@]> cannot be
     * obtained.
     */
    public static final String NSBM0077E = "NSBM0077E";

    /**
     * Message : Failed to update @ table.[@]
     */
    public static final String NSBM0120E = "NSBM0120E";

    /**
     * Message : [@] is not registered.(@)
     */
    public static final String NSBM0135E = "NSBM0135E";

    /**
     * Message : @'s @ is incorrect.
     */
    public static final String NSBM0140E = "NSBM0140E";

    /**
     * Message : @'s @ doesn't exist.
     */
    public static final String NSBM0141E = "NSBM0141E";

    /**
     * Message : @'s @ doesn't exist in a master.
     */
    public static final String NSBM0142E = "NSBM0142E";

    /**
     * Message : @'s status cannot @.
     */
    public static final String NSBM0144E = "NSBM0144E";

    /**
     * Message : @ is already received.
     */
    public static final String NSBM0145E = "NSBM0145E";

    /**
     * Message : @ is not received.
     */
    public static final String NSBM0146E = "NSBM0146E";

    /**
     * Message : correlation_id @ type @ @ was created for incident @.
     */
    public static final String NSBM0208I = "NSBM0208I";

    /**
     * Message : Task note could not be added to incident @ in Canon
     * system.
     */
    public static final String NSBM0209E = "NSBM0209E";

    /**
     * Message : Incident @ could not be canceled/resolved in Canon
     * system.
     */
    public static final String NSBM0210E = "NSBM0210E";

    /**
     * Message : The parameter @[@] is not exist in S21 system.[number
     * : @]
     */
    public static final String NSBM0211E = "NSBM0211E";

    /**
     * Message : There is no parameter in @.[number : @]
     */
    public static final String NSBM0212E = "NSBM0212E";

    /**
     * Message : The parameter @[@] is invalid.[number : @]
     */
    public static final String NSBM0213E = "NSBM0213E";

    /**
     * Message : The request,@[@],has already processed in S21 system.
     */
    public static final String NSBM0214E = "NSBM0214E";

    /**
     * Message : The request,@[@],can not be canceled.[@ : @]
     */
    public static final String NSBM0215E = "NSBM0215E";

    /**
     * Message : [@] field is mandatory to process your request in S21
     * system.
     */
    public static final String NSBM0216E = "NSBM0216E";

    /**
     * Message : There are no @[@] can apply updating note in S21
     * system.[@:@]
     */
    public static final String NSBM0217E = "NSBM0217E";

    // START 2023/08/08 N.Takatsu [QC#60012, ADD]
    /**
     * Message : incident information is invalid or missing.
     */
    public static final String NSBM0223E = "NSBM0223E";
    // END   2023/08/08 N.Takatsu [QC#60012, ADD]

    /**
     * VAR_CHAR_CONST : WALMART_STATE_REGISTER
     */
    public static final String WALMART_STATE_REGISTER = "WALMART_STATE_REGISTER";

    /**
     * VAR_CHAR_CONST : WALMART_STATE_CANCEL
     */
    public static final String WALMART_STATE_CANCEL = "WALMART_STATE_CANCEL";

    /**
     * VAR_CHAR_CONST : WALMART_STATE_UPDATE
     */
    public static final String WALMART_STATE_UPDATE = "WALMART_STATE_UPDATE";

    /**
     * VAR_CHAR_CONST : NSBB0980_DEF_SVC_PBLM_TP
     */
    public static final String NSBB0980_DEF_SVC_PBLM_TP = "NSBB0980_DEF_SVC_PBLM_TP";

    /**
     * VAR_CHAR_CONST : WALMART_ISSUE_DTL_PTRN
     */
    public static final String WALMART_ISSUE_DTL_PTRN = "WALMART_ISSUE_DTL_PTRN";

    /**
     * VAR_CHAR_CONST : WALMART_SVC_PBLM_TP_PTRN
     */
    public static final String WALMART_SVC_PBLM_TP_PTRN = "WALMART_SVC_PBLM_TP_PTRN";

    /**
     * VAR_CHAR_CONST : WALMART_REGISTER_CMNT
     */
    public static final String WALMART_REGISTER_CMNT = "WALMART_REGISTER_CMNT";

    /**
     * VAR_CHAR_CONST : WALMART_INC_TBL_NM
     */
    public static final String WALMART_INC_TBL_NM = "WALMART_INC_TBL_NM";

    /**
     * VAR_CHAR_CONST : WALMART_CUST_RSN_CD
     */
    public static final String WALMART_CUST_RSN_CD = "WALMART_CUST_RSN_CD";

    /**
     * VAR_CHAR_CONST : WALMART_SVC_STS_TXT
     */
    public static final String WALMART_SVC_STS_TXT = "WALMART_SVC_STS_TXT";

    // START 2023/08/08 N.Takatsu [QC#60012, DEL]
//    /**
//     * VAR_CHAR_CONST : WALMART_SVC_CMNT_BGN_TXT
//     */
//    public static final String WALMART_SVC_CMNT_BGN_TXT = "WALMART_SVC_CMNT_BGN_TXT";
    // END   2023/08/08 N.Takatsu [QC#60012, DEL]

    /**
     * VAR_CHAR_CONST : WALMART_CUST_WBHK_ID
     */
    public static final String WALMART_CUST_WBHK_ID = "WALMART_CUST_WBHK_ID";

    /**
     * Record Type : Register FSR
     */
    public static final int RECORD_TYPE_REGISTER = 1;

    /**
     * Record Type : Update FSR
     */
    public static final int RECORD_TYPE_UPDATE = 2;

    /**
     * Record Type : Cancel FSR
     */
    public static final int RECORD_TYPE_CANCEL = 3;

    /**
     * Interface ID : NSBI1320
     */
    public static final String NSBI1320 = "NSBI1320";

    /**
     * Interface ID : NSBI1330
     */
    public static final String NSBI1330 = "NSBI1330";

    /**
     * Interface ID : NSBI1340
     */
    public static final String NSBI1340 = "NSBI1340";

    /**
     * Interface ID : NSBI1350
     */
    public static final String NSBI1350 = "NSBI1350";

    /**
     * Mail Group From
     */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /**
     * Mail Group From
     */
    public static final String MAIL_GROUP_KEY_FROM = "NS";

    /**
     * Mail Group To
     */
    public static final String MAIL_GROUP_ID_TO = "NSBB0980";

    /**
     * Mail Template ID
     */
    public static final String MAIL_TEMPLATE_ID_01 = "NSBB0980M001";

    /**
     * Mail item : ${errDate}
     */
    public static final String MAIL_ITEM_ERR_DATE = "errDate";

    /**
     * Mail item : ${message}
     */
    public static final String MAIL_ITEM_MESSAGE = "message";

    /**
     * Mail item : ${number}
     */
    public static final String MAIL_ITEM_NUM = "number";

    /**
     * Mail item : ${cratts}
     */
    public static final String MAIL_ITEM_CRAT_TS = "cratts";

    /**
     * Mail item : ${sernum}
     */
    public static final String MAIL_ITEM_SER_NUM = "sernum";

    /**
     * Mail item : ${pblmcmnt}
     */
    public static final String MAIL_ITEM_PBLM_CMNT = "pblmcmnt";

    /**
     * Mail item : ${tasknum}
     */
    public static final String MAIL_ITEM_TASK_NUM = "tasknum";

    /**
     * Mail item : ${tasksts}
     */
    public static final String MAIL_ITEM_TASK_STS = "tasksts";

    /**
     * Mail item : ${tasktp}
     */
    public static final String MAIL_ITEM_TASK_TP = "tasktp";

    /**
     * Mail item : ${fsrnum}
     */
    public static final String MAIL_ITEM_FSR_NUM = "fsrnum";

    /**
     * Mail item : ${rqstmode}
     */
    public static final String MAIL_ITEM_RQST_MODE = "rqstmode";

    /**
     * Mail Item (${batchId})
     */
    public static final String MAIL_ITEM_BATCH_ID = "batchId";

    /**
     * Date Format : MM/dd/yyyy HH:mm:ss.SSS
     */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    /**
     * SER_NUM length
     */
    public static final int SER_NUM_LENGTH = 30;

    /**
     * FSR_NUM length
     */
    public static final int FSR_NUM_LENGTH = 10;

    /**
     * CTAC_PSN_CELL_PHO_NUM length
     */
    public static final int CTAC_PSN_CELL_PHO_NUM_LENGTH = 20;

    /**
     * SVC_PBLM_TP_CD length
     */
    public static final int SVC_PBLM_TP_CD_LENGTH = 4;

    /** DB Item Column Name */
    public static final String CUST_WBHK_ID = "CUST_WBHK_ID";

    /** DB Item Column Name */
    public static final String CUST_TBL_NM = "CUST_TBL_NM";

    /** DB Item Column Name */
    public static final String CUST_SYS_ID = "CUST_SYS_ID";

    // START 2023/07/10 N.Takatsu [QC#60012, ADD]
    /**
     * WALMART_NOT_EXIST_EML
     */
    public static final String WALMART_NOT_EXIST_EML = "WALMART_NOT_EXIST_EML";
    // END   2023/07/10 N.Takatsu [QC#60012, ADD]

    // START 2023/07/28 N.Takatsu [QC#60012, ADD]
    /**
     * CREATE
     */
    public static final String CREATE = "Create";

    /**
     * UPDATE
     */
    public static final String UPDATE = "Update";

    /**
     * CANCEL
     */
    public static final String CANCEL = "Cancel";

    /**
     * UNKNOWN
     */
    public static final String UNKNOWN = "Unknown";
    // END   2023/07/28 N.Takatsu [QC#60012, ADD]

    // START 2023/08/03 K.Kitachi [QC#60012, ADD]
    /**
     * Mail Template ID 02
     */
    public static final String MAIL_TEMPLATE_ID_02 = "NSBB0980M002";

    /**
     * Mail Template ID 03
     */
    public static final String MAIL_TEMPLATE_ID_03 = "NSBB0980M003";

    /**
     * Mail Template ID 04
     */
    public static final String MAIL_TEMPLATE_ID_04 = "NSBB0980M004";

    /**
     * Mail Template ID 05
     */
    public static final String MAIL_TEMPLATE_ID_05 = "NSBB0980M005";

    /**
     * VAR_CHAR_CONST : OUT_TRTY_SVC_BR_CD
     */
    public static final String OUT_TRTY_SVC_BR_CD = "OUT_TRTY_SVC_BR_CD";

    /**
     * VAR_CHAR_CONST : CLICK_SEND_EXCL_CALL_TP
     */
    public static final String CLICK_SEND_EXCL_CALL_TP = "CLICK_SEND_EXCL_CALL_TP";

    /**
     * VAR_CHAR_CONST : ESS_DUMMY_TECH
     */
    public static final String ESS_DUMMY_TECH = "ESS_DUMMY_TECH";

    /**
     * DS_COND_CONST_GRP_ID : NSBB0980_NTFY_ADDR
     */
    public static final String DS_COND_CONST_GRP_ID_NSBB0980_NTFY_ADDR = "NSBB0980_NTFY_ADDR";

    /**
     * DS_COND_CONST_NM : DEFAULT ZONE
     */
    public static final String DS_COND_CONST_NM_DEFAULT_ZONE = "DEFAULT ZONE";

    /**
     * MAX_DATE : 29991231
     */
    public static final String MAX_DATE = "29991231";

    /**
     * Date Attribute Value Code
     */
    public static final String DT_ATTRB_VAL_CD = "1";

    /**
     * Day of the week is Holiday
     */
    public static final int DAY_OF_THE_WEEK_IS_HOLIDAY = 0;

    /**
     * All Business Line Type Code
     */
    public static final String ALL_BIZ_LINE_TP_CD = "*";

    /**
     * Substring Position 4
     */
    public static final int SUB_STR_POS_4 = 4;

    /**
     * Substring Position 6
     */
    public static final int SUB_STR_POS_6 = 6;

    /**
     * Substring Position 8
     */
    public static final int SUB_STR_POS_8 = 8;

    /**
     * Substring Position 14
     */
    public static final int SUB_STR_POS_14 = 14;
    // END   2023/08/03 K.Kitachi [QC#60012, ADD]

    // START 2023/08/08 N.Takatsu [QC#61771, ADD]
    /**
     * SPACE
     */
    public static final String SPACE = " ";
    // START 2023/08/08 N.Takatsu [QC#61771, ADD]
}
