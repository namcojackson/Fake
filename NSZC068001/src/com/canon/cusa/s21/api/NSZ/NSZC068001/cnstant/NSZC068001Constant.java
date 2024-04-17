/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC068001.cnstant;

/**
 *<pre>
 * NSZC068001:Receive Task Info from Click Software API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/27   Fujitsu         N.Sugiura       Create          Create
 * 2015/11/18   Hitachi         T.Iwamoto       Update          N/A
 * 2015/12/24   Hitachi         T.Iwamoto       Update          QC#1404
 * 2016/02/22   Hitachi         T.Iwamoto       Update          QC#4108
 * 2016/04/18   Hitachi         T.Iwamoto       Update          QC#4467,4469
 * 2017/01/30   Hitachi         Y.Takeno        Update          QC#17291
 * 2017/02/23   Hitachi         K.Kojima        Update          QC#16301
 * 2017/09/06   Hitachi         A.Kohinata      Update          QC#15134
 * 2018/01/29   Hitachi         U.Kim           Update          QC#19702
 * 2018/03/22   CITS            M.Naito         Update          CSA QC#18713
 * 2018/07/05   Hitachi         T.Tomita        Update          CSA QC#27015
 * 2018/08/03   Hitachi         T.Tomita        Update          CSA QC#27605
 * 2018/08/27   CITS            T.Wada          Update          CSA QC#23385
 * 2019/05/31   Hitachi         K.Kim           Update          CSA QC#50574
 * 2019/11/14   Hitachi         K.Fujimoto      Update          CSA QC#52262
 * 2019/11/29   Hitachi         A.Kohinata      Update          CSA QC#54113
 * 2023/09/25   Hitachi         N.Takatsu       Update          CSA QC#61780
 * 2023/11/07   Hitachi         K.Kitachi       Update          CSA QC#61648
 *</pre>
 */
public class NSZC068001Constant {

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "ADB";

    /** Date Start Position */
    public static final int DATE_START_POS = 0;

    /** Date End Position */
    public static final int DATE_END_POS = 8;

    /** Time Start Position */
    public static final int TIME_START_POS = 8;

    /** Time End Position */
    public static final int TIME_END_POS = 6;

    /** TimeStamp Length */
    public static final int TS_LEN = 14;

    /** TimeStamp format */
    public static final String FORMAT_SYS_TS = "yyyyMMddHHmmssSSS";

    /**
     * Cannot get mandatory item to Receive Task Info.[@]
     */
    public static final String NSZM0586E = "NSZM0586E";

    /**
     * Date and Time Format should be [@].[@]
     */
    public static final String NSZM0587E = "NSZM0587E";

    /**
     * The corresponding data does not exist in "VAR_CHAR_CONST".
     */
    public static final String NSZM0102E = "NSZM0102E";

    // START 2018/01/29 U.Kim [QC#19702, ADD]
    /**
     * The corresponding data does not exist in "NUM_CONST".
     */
    public static final String NSZM1015E = "NSZM1015E";
    // END 2018/01/29 U.Kim [QC#19702, ADD]
    /**
     * The [@] does not exist in S21. Please check it.
     */
    public static final String NSZM0588E = "NSZM0588E";

    // START 2019/05/31 [QC#50574,ADD]
    /**
     * Failed to update the @.
     */
    public static final String NSZM0625E = "NSZM0625E";
    // END 2019/05/31 [QC#50574,ADD]

    // START 2023/09/25 N.Takatsu [QC#61780, ADD]
    /**
     * The task was completed but the error occurred in Parts Order Creation. Please review the transaction.
     */
    public static final String NSZM1407W = "NSZM1407W";
    // END   2023/09/25 N.Takatsu [QC#61780, ADD]

    /** varchar const : CLICK_DATE_TIME_FORMAT */
    public static final String CLICK_DATE_TIME_FORMAT = "CLICK_DATE_TIME_FORMAT";

    // START 2019/11/14 K.Fujimoto [QC#52262, ADD]
    /** varchar const : NSZC0680_EXCLD_THROW_ABEND */
    public static final String  NSZC0680_EXCLD_THROW_ABEND = "NSZC0680_EXCLD_THROW_ABEND";
    // END   2019/11/14 K.Fujimoto [QC#52262, ADD]

    // add start 2019/11/29 QC#54113
    /** varchar const : PHONE_FIX_SVC_PBLM_TP_CD */
    public static final String PHONE_FIX_SVC_PBLM_TP_CD = "PHONE_FIX_SVC_PBLM_TP_CD";

    /** varchar const : PHONE_FIX_SVC_PBLM_LOC_TP_CD */
    public static final String PHONE_FIX_SVC_PBLM_LOC_TP_CD = "PHONE_FIX_SVC_PBLM_LOC_TP_CD";

    /** varchar const : PHONE_FIX_SVC_PBLM_RSN_TP_CD */
    public static final String PHONE_FIX_SVC_PBLM_RSN_TP_CD = "PHONE_FIX_SVC_PBLM_RSN_TP_CD";

    /** varchar const : PHONE_FIX_SVC_PBLM_CRCT_TP_CD */
    public static final String PHONE_FIX_SVC_PBLM_CRCT_TP_CD = "PHONE_FIX_SVC_PBLM_CRCT_TP_CD";
    // add end 2019/11/29 QC#54113

    // START 2018/01/29 U.Kim [QC#19702, ADD]
    /** num const : SLEEP_TIMES_FIND_SVC_TASK */
    public static final String SLEEP_TIMES_FIND_SVC_TASK = "SLEEP_TIMES_FIND_SVC_TASK";

    /** num const : SLEEP_MILL_SEC_FIND_SVC_TASK */
    public static final String SLEEP_MILL_SEC_FIND_SVC_TASK = "SLEEP_MILL_SEC_FIND_SVC_TASK";
    // END 2018/01/29 U.Kim [QC#19702, ADD]

    // Add Start 2018/07/05 QC#27015
    /** num const : RETRY_COUNT_FOR_LOCK */
    public static final String RETRY_COUNT_FOR_LOCK = "RETRY_COUNT_FOR_LOCK";

    /** num const : WAIT_TIME_FOR_LOCK */
    public static final String NSZC1070_WAIT_MSEC = "NSZC1070_WAIT_MSEC";

    /** Default Value : RETRY_COUNT */
    public static final int DEF_VAL_RETRY_COUNT = 400;

    /** Default Value : WAIT_MSEC */
    public static final int DEF_VAL_WAIT_MSEC = 5000;
    // Add End 2018/07/05 QC#27015

    /** TEC_LOC SWH = G */
    public static final String TEC_LOC_SWH = "G";

    /** minute */
    public static final int MINUTES = 60000;

    /** MDSE_CD length */
    public static final int MDSE_LEN = 10;

    /** branch length */
    public static final int BR_LEN = 3;

    /** Error Variable : CSA_Zone */
    public static final String ERR_VAR_CALL_ID = "CallID";

    /** Error Variable : CSA_Zone */
    public static final String ERR_VAR_CALL_INFO = "Task Info";

    /** Error Variable : CSA_BranchManager */
    public static final String ERR_VAR_BR_MGR = "CSA_BranchManager";

    /** Error Variable : CSA_TerritoryManager */
    public static final String ERR_VAR_TRTY_MGR = "CSA_TerritoryManager";

    /** Error Variable : CSA_TeamManager */
    public static final String ERR_VAR_TEAM_MGR = "CSA_TeamManager";

    /** Error Variable : Priority */
    public static final String ERR_VAR_PRTY = "Priority";

    /** Error Variable : Stamp */
    public static final String ERR_VAR_STAMP = "Stamp";

    /** Error Variable : Finish */
    public static final String ERR_VAR_FINISH = "Finish";

    /** Error Variable : Start */
    public static final String ERR_VAR_START = "Start";

    /** Error Variable : ID */
    public static final String ERR_VAR_TECH = "ID";

    /** Error Variable : Status */
    public static final String ERR_VAR_STATUS = "Status";

    /** Error Variable : CSA_InRoute_ClientTimeStamp */
    public static final String ERR_VAR_INROUTE_TS = "CSA_InRoute_ClientTimeStamp";

    /** Error Variable : CSA_Arrived_ClientTimeStamp */
    public static final String ERR_VAR_ARRIVED_TS = "CSA_Arrived_ClientTimeStamp";

    /** Error Variable : CAS_ActualStart */
    public static final String ERR_VAR_COMP_TS = "CSA_Completed_ClientTimeStamp";

    /** Error Variable : CAS_ActualStart */
    public static final String ERR_VAR_CLIENT_TS = "ClientTimeStamp";

    /** Error Variable : CancellationDate */
    public static final String ERR_VAR_CANC_DT = "CancellationDate";

    /** Error Variable : CSA_ResourceManager */
    public static final String ERR_VAR_RESR_MGR = "CSA_ResourceManager";

    /** Error Variable : CSA_LineOfBusiness */
    public static final String ERR_VAR_LINE = "CSA_LineOfBusiness";

    /** Error Variable : CSA_LocationCode */
    public static final String ERR_VAR_LOC = "CSA_LocationCode";

    /** Error Variable : CSA_ReasonCode */
    public static final String ERR_VAR_RSN = "CSA_ReasonCode";

    /** Error Variable : CSA_CorrectionCode */
    public static final String ERR_VAR_CRCT = "CSA_CorrectionCode";

    /** Error Variable : FinishTime(SupplementalTime) */
    public static final String ERR_VAR_FINISH_SPPL = "FinishTime(SupplementalTime)";

    /** Error Variable : StartTime(SupplementalTime) */
    public static final String ERR_VAR_START_SPPL = "StartTime(SupplementalTime)";

    /** Error Variable : Finish(Assignment) */
    public static final String ERR_VAR_FINISH_ASS = "Finish(Assignment)";

    /** Error Variable : Start(Assignment) */
    public static final String ERR_VAR_START_ASS = "Start(Assignment)";

    /** Error Variable : Type(SupplementalTime) */
    public static final String ERR_VAR_TYPE_SPPL = "Type(SupplementalTime)";

    /** Error Variable : Status(SupplementalTime) */
    public static final String ERR_VAR_STS_SPPL = "Status(SupplementalTime)";

    /** Error Variable : ID(Assignment) */
    public static final String ERR_VAR_TECH_ASS = "ID(Assignment)";

    /** Error Variable : Serial(FieldRequest) */
    public static final String ERR_VAR_SER_FRQ = "Serial(FieldRequest)";

    /** Error Variable : Type(FieldRequest) */
    public static final String ERR_VAR_TYPE_FRQ = "Type(FieldRequest)";

    /** Error Variable : ID(FieldRequest) */
    public static final String ERR_VAR_TECH_FRQ = "ID(FieldRequest)";

    /** SVC_TASK_STS_CD */
    public static final String SVC_TASK_STS_CD = "SVC_TASK_STS_CD";

    /** XTRNL_STS_REF_TXT */
    public static final String XTRNL_STS_REF_TXT = "XTRNL_STS_REF_TXT";

    /** SVC_CALL_PRTY_CD */
    public static final String SVC_CALL_PRTY_CD = "SVC_CALL_PRTY_CD";

    /** SVC_CALL_PRTY_CD */
    public static final String FSR_VISIT_STS_CD = "FSR_VISIT_STS_CD";

    /** FSR_NUM */
    public static final String FSR_NUM = "FSR_NUM";

    /** TECH_CD */
    public static final String TECH_CD = "TECH_CD";

    /** FSR_VISIT_NUM */
    public static final String FSR_VISIT_NUM = "FSR_VISIT_NUM";

    /** FSR_VISIT_DISPT_DT */
    public static final String FSR_VISIT_DISPT_DT = "FSR_VISIT_DISPT_DT";

    /** FSR_VISIT_DISPT_TM */
    public static final String FSR_VISIT_DISPT_TM = "FSR_VISIT_DISPT_TM";

    /** FSR_VISIT_ARV_DT */
    public static final String FSR_VISIT_ARV_DT = "FSR_VISIT_ARV_DT";

    /** FSR_VISIT_ARV_TM */
    public static final String FSR_VISIT_ARV_TM = "FSR_VISIT_ARV_TM";

    /** INV_CCY_CD */
    public static final String INV_CCY_CD = "INV_CCY_CD";

    /*** CCY_EXCH_RATE */
    public static final String CCY_EXCH_RATE = "CCY_EXCH_RATE";

    /** SVC_MACH_MSTR_PK */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** SVC_BILL_TP_CD */
    public static final String SVC_BILL_TP_CD = "SVC_BILL_TP_CD";

    /** PMT_TERM_CASH_DISC_CD */
    public static final String PMT_TERM_CASH_DISC_CD = "PMT_TERM_CASH_DISC_CD";

    /** SVC_PBLM_TP_CD */
    public static final String SVC_PBLM_TP_CD = "SVC_PBLM_TP_CD";

    /** DS_SVC_CALL_TP_CD */
    public static final String DS_SVC_CALL_TP_CD = "DS_SVC_CALL_TP_CD";

    /** MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** DS_ACCT_NM */
    public static final String DS_ACCT_NM = "DS_ACCT_NM";

    /** FIRST_LINE_ADDR */
    public static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /** MDL_ID */
    public static final String MDL_ID = "MDL_ID";

    /** MDL_NM */
    public static final String MDL_NM = "MDL_NM";

    /** MDSE_CD */
    public static final String SER_NUM = "SER_NUM";

    /** CUST_MACH_CTRL_NUM */
    public static final String CUST_MACH_CTRL_NUM = "CUST_MACH_CTRL_NUM";

    /** SVC_EXP_MDSE_CD */
    public static final String SVC_EXP_MDSE_CD = "SVC_EXP_MDSE_CD";

    /** SVC_SUPPL_TASK_TP_CD */
    public static final String SVC_SUPPL_TASK_TP_CD = "SVC_SUPPL_TASK_TP_CD";

    /** SVC_ZN_CD */
    public static final String SVC_ZN_CD = "SVC_ZN_CD";

    /** CTRY_CD */
    public static final String CTRY_CD = "CTRY_CD";

    /** POST_CD */
    public static final String POST_CD = "POST_CD";

    // Add Start 2018/08/03 QC#27605
    /** ERL_START_TS */
    public static final String ERL_START_TS = "ERL_START_TS";

    /** LATE_START_TS */
    public static final String LATE_START_TS = "LATE_START_TS";
    // Add End 2018/08/03 QC#27605

    /** service region length */
    public static final int RG_LEN = 6;
    
    // START 2017/01/30 [QC#17291, ADD]
    /** max value of getTimeDiff(String, String) */
    public static final int TIME_DIFF_MAX_VALUE = 9999;
    // END   2017/01/30 [QC#17291, ADD]

    // START 2017/02/23 K.Kojima [QC#16301,ADD]
    /** CLICK_TECH_ORD_PRT_SQ */
    public static final String CLICK_TECH_ORD_PRT_SQ = "CLICK_TECH_ORD_PRT_SQ";

    /** TECH_ORD_PRT_SQ */
    public static final String TECH_ORD_PRT_SQ = "TECH_ORD_PRT_SQ";

    /** DS_COND_CONST GroupId : NPZC1170 */
    public static final String GRP_ID_NPZC1170 = "NPZC1170";

    /** DS_COND_CONST KEY : REQ_STATUS */
    public static final String REQ_STATUS = "REQ_STATUS";
    // END 2017/02/23 K.Kojima [QC#16301,ADD]

    // add start 2017/09/06 QC#15134
    /** CLICK_MTR_READ_TP : REPLACE */
    public static final String CLICK_MTR_READ_TP_REPLACE = "REPLACE";

    /** CLICK_MTR_READ_TP : ROLLOVER */
    public static final String CLICK_MTR_READ_TP_ROLLOVER = "ROLLOVER";
    // add end 2017/09/06 QC#15134

    // START 2018/03/22 M.Naito [QC#18713, ADD]
    /** INTFC_ID : NSBI1030 */
    public static final String INTFC_ID = "NSBI1030";

    /** SIGNA_FILE_TYPE : SIGNA_FILE_TYPE */
    public static final String SIGNA_FILE_TYPE = "jpeg";
    // END 2018/03/22 M.Naito [QC#18713, ADD]

    // START 2019/11/14 K.Fujimoto [QC#52262, ADD]
    /** comma */
    public static final String COMMA = ",";
    // END   2019/11/14 K.Fujimoto [QC#52262, ADD]
    
    // START 2018/08/29 T.Wada [QC#23385, ADD]
    /** EXEC_MODE_BATCH : EXEC_MODE_BATCH */
    public static final String EXEC_MODE_BATCH = "BATCH";
    /** EXEC_MODE_ONLINE : EXEC_MODE_ONLINE */
    public static final String EXEC_MODE_ONLINE = "ONLINE";
    // END 2018/08/29 T.Wada [QC#23385, ADD]

    // START 2023/11/07 K.Kitachi [QC#61648, ADD]
    /** Ordering Parts doesn't exist in inventory. Please select the other Delivery Option if Premium Rush Order needs. */
    public static final String NPZM0318E = "NPZM0318E";

    /** Mail Group ID(From) */
    public static final String MAIL_FROM_GROUP_ID = "FROM0002";

    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NPZC1170M001";

    /** Mail Language */
    public static final String ML_LANG = "en";

    /** Mail Language Code */
    public static final String ML_LANG_CD = "ISO-8859-1";

    /** Mail Param Today */
    public static final String MAIL_PARAM_TODAY = "today";

    /** Mail Param Date */
    public static final String MAIL_PARAM_TIME = "time";

    /** Mail Param Details Info */
    public static final String MAIL_PARAM_MESSAGE = "message";

    /** Mail Date Format */
    public static final String MAIL_DATE_FORMAT = "MM/dd/yyyy";

    /** Mail Time Format */
    public static final String MAIL_TIME_FORMAT = "HH:mm";

    /** Mail item code Format */
    public static final String MAIL_ITEM_CODE_FORMAT = "%-24s";

    /** Mail item description Format */
    public static final String MAIL_ITEM_DESCRIPTIONE_FORMAT = "%-29s";

    /** 1 space. */
    public static final String MAIL_SPACE = " ";

    /** Mail Line separator */
    public static final String MAIL_LINE_SEPARATOR = System.getProperty("line.separator");
    // END 2023/11/07 K.Kitachi [QC#61648, ADD]
}
