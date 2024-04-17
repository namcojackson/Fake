/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB099001.constant;

/**
 * <pre>
 * Service Call Update Coordination Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/02/27   Hitachi         T.Usui          Create          CSA-QC#60012
 * 2023/09/08   Hitachi         K.Kitachi       Update          CSA-QC#61771
 * 2023/09/25   Hitachi         N.Takatsu       Update          CSA-QC#61771
 * 2023/11/20   Hitachi         N.Takatsu       Update          CSA-QC#61771
 *</pre>
 */
public final class NSBB099001Constant {

    /** Batch Id */
    public static final String BATCH_ID = "NSBB099001";

    /** FETCH_SIZE_MAX */
    public static final int FETCH_SIZE_MAX = 1000;

    /** MAX_COMMIT_LIMIT */
    public static final int MAX_COMMIT_LIMIT = 1000;

    /** UPD_SYSTEM_NAME */
    public static final String UPD_SYSTEM_NAME = "SYSTEM";

    /** Mail Template Id */
    public static final String MAIL_TEMPLATE_ID_01 = "NSBB0990M001";

    /** DB Item Column Name */
    public static final String CUST_SYS_ID = "CUST_SYS_ID";

    /** DB Item Column Name */
    public static final String FSR_NUM = "FSR_NUM";

    /** DB Item Column Name */
    public static final String SVC_TASK_NUM = "SVC_TASK_NUM";

    /** DB Item Column Name */
    public static final String SVC_CMNT_TXT = "SVC_CMNT_TXT";

    /** DB Item Column Name */
    public static final String INTFC_ID = "INTFC_ID";

    /** DB Item Column Name */
    public static final String SVC_TASK_RQST_DUP_PK = "SVC_TASK_RQST_DUP_PK";

    /** DB Item Column Name */
    public static final String SVC_MEMO_PK = "SVC_MEMO_PK";

    /** DB Item Column Name */
    public static final String SVC_WRK_TXT = "SVC_WRK_TXT";

    /** DB Item Column Name */
    public static final String CUST_RSN_CD = "CUST_RSN_CD";

    /** DB Item Column Name */
    public static final String SVC_STS_TXT = "SVC_STS_TXT";

    /** DB Item Column Name */
    public static final String CUST_WBHK_ID = "CUST_WBHK_ID";

    /** DB Item Column Name */
    public static final String CUST_TBL_NM = "CUST_TBL_NM";

    /** DB Item Column Name */
    public static final String SVC_TASK_WATCH_FLD_TXT = "SVC_TASK_WATCH_FLD_TXT";

    /** DB Item Column Name */
    public static final String SVC_TASK_RTRN_FLD_TXT = "SVC_TASK_RTRN_FLD_TXT";

    /** DB Item Column Name */
    public static final String CRAT_TS = "CRAT_TS";

    /** DB Item Column Name */
    public static final String OTBD_INTFC_TP_CD = "OTBD_INTFC_TP_CD";

    /** DB Item Column Name */
    public static final String SVC_RQST_PROC_STS_CD = "SVC_RQST_PROC_STS_CD";

    /** DB Item Column Name */
    public static final String ERR_INFO_TXT = "ERR_INFO_TXT";

    /** DB Item Column Name */
    public static final String CUST_CALL_ID = "CUST_CALL_ID";

    /** DB Item Column Name */
    public static final String ITT_NUM = "ITT_NUM";

    /** DB Item Column Name */
    public static final String LAST_UPD_TS = "LAST_UPD_TS";

    /** DB Item Column Name */
    public static final String SVC_MEMO_KEY_INFO_PK = "SVC_MEMO_KEY_INFO_PK";

    /** DB Item Column Name */
    public static final String SVC_TASK_OTBD_MSG_PK = "SVC_TASK_OTBD_MSG_PK";

    // START 2023/11/20 N.Takatsu [QC#61771, ADD]
    /** DB Item Column Name */
    public static final String DS_SVC_CALL_TP_CD = "DS_SVC_CALL_TP_CD";

    /** DB Item Column Name */
    public static final String DS_SVC_CALL_TP_DESC_TXT = "DS_SVC_CALL_TP_DESC_TXT";

    /** DB Item Column Name */
    public static final String TECH_CD = "TECH_CD";
    // END   2023/11/20 N.Takatsu [QC#61771, ADD]

    // START 2023/11/20 N.Takatsu [QC#61771, MOD]
    public static final String TASK_MESSAGE = "Task %s type %s %s was added.";
//    public static final String TASK_MESSAGE = "Task %s was added.";
    // END   2023/11/20 N.Takatsu [QC#61771, MOD]

    public static final String VENDOR_MESSAGE = "Vendor Call# %s was registered.";

    // START 2023/11/20 N.Takatsu [QC#61771, ADD]
    public static final String VENDOR_MESSAGE_EXSITS = "Vendor Call# %s opened with %s.";
    // END   2023/11/20 N.Takatsu [QC#61771, ADD]
    
    public static final String ADD_TASK = "Add Task/Follow up call";

    public static final String UPDATE_VENDOR = "Update Vendor#";

    public static final String UPDATE_NOTE = "Update Note";

    public static final String CALL_CREATION = "Call Creation";

    public static final String CALL_CANCEL = "Call Cancel";

    public static final String WALMART_STATE_REGISTER = "WALMART_STATE_REGISTER";

    public static final String WALMART_STATE_CANCEL = "WALMART_STATE_CANCEL";

    public static final String WALMART_REPLACE_LINEFEED = "WALMART_REPLACE_LINEFEED";

    public static final String WALMART_ERR_MAIL_FORMAT = "WALMART_ERR_MAIL_FORMAT";

    public static final String SVC_TASK_KEY_INFO = "SVC_TASK_KEY_INFO";

    public static final String SVC_TASK_RQST_DUP = "SVC_TASK_RQST_DUP";

    public static final String SVC_MEMO_KEY_INFO = "SVC_MEMO_KEY_INFO";

    public static final String SVC_TASK_OTBD_MSG = "SVC_TASK_OTBD_MSG";

    // ---------------------------------------
    // Interface
    // ---------------------------------------
    public static final String UNSUBSCRIBE = "NSBI1320";

    public static final String INCIDENT_UPDATE = "NSBI1330";

    public static final String WEBHOOK = "NSBI1340";

    public static final String INCIDENT_HOLD = "NSBI1350";

    // ---------------------------------------
    // Messages
    // ---------------------------------------

    /** [@] field is mandatory. */
    public static final String NSBM0069E = "NSBM0069E";

    public static final String NSBM0077E = "NSBM0077E";

    public static final String NSBM0120E = "NSBM0120E";

    public static final String NSBM0121E = "NSBM0121E";

    public static final String NSBM0032E = "NSBM0032E";

    public static final String NSBM0135E = "NSBM0135E";

    // ---------------------------------------
    // Mail
    // ---------------------------------------
    /** Mail Group Id Key: From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** Mail Group Id Key: From */
    public static final String MAIL_GROUP_KEY_FROM = "NS";

    /** Business Error Mail group id for To. */
    public static final String MAIL_GROUP_ID_TO = "NSBB0990";

    /** Error Mail Headline */
    public static final String HEADER_ACTION = "Action";

    /** Error Mail Headline */
    public static final String HEADER_INTFC_ID = "I/F ID";

    /** Error Mail Headline */
    public static final String HEADER_INCIDENT_NO = "Incident#";

    /** Error Mail Headline */
    public static final String HEADER_FSR_NO = "FSR#";

    /** Error Mail Headline */
    public static final String HEADER_TASK_NO = "Task#";

    /** Error Mail Headline */
    public static final String HEADER_ERROR = "Error";

    /** Carriage Return Line Feed */
    public static final String CRLF = "\r\n";

    // START 2023/09/08 K.Kitachi [QC#61771, ADD]
    /** Substring Position 25 */
    public static final int SUB_STR_POS_25 = 25;
    // END   2023/09/08 K.Kitachi [QC#61771, ADD]

    // START 2023/09/25 N.Takatsu [QC#61771, ADD]
    /** ROW_NUM 1 */
    public static final int ROW_NUM = 1;
    // END   2023/09/25 N.Takatsu [QC#61771, ADD]
}
