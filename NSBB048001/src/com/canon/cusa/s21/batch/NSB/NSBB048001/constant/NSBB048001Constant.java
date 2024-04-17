package com.canon.cusa.s21.batch.NSB.NSBB048001.constant;

/**
 * <pre>
 * NSBB048001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2015   Hitachi         J.Kim           Create          N/A
 * </pre>
 */
public class NSBB048001Constant {

   /**
    * BATCH_PROGRAM_ID(NSBB0480)
    */
   public static final String BATCH_PROGRAM_ID = "NSBB0480";

    /** Error Msg : There is no parameter in [@]. */
   public static final String NSBM0032E = "NSBM0032E";

    /** Message ID : NSAM0069E */
    public static final String NSAM0069E = "NSAM0069E";

    /** Mail Template ID(NSBB0480M001) */
    public static final String MAIL_TEMPLATE_ID_01 = "NSBB0480M001";

    /** Mail Template ID(NSBB0480M002) */
    public static final String MAIL_TEMPLATE_ID_02 = "NSBB0480M002";

    /** Mail Character Set */
    public static final String MAIL_CHARSET = "ISO-8859-1";

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** Mail Date Format : MM/dd/yyyy HH:mm:ss.SSS */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** SVC_BAT_ERR_LOG : Service Batch Error Key Name 01 */
    public static final String SVC_TSS_ESCL_PK = "SVC_TSS_ESCL_PK";

    /** SVC_BAT_ERR_LOG : Service Batch Error Key Name 02 */
    public static final String SVC_TSS_ESCL_DT = "SVC_TSS_ESCL_DT";

    /** SVC_BAT_ERR_LOG : Service Batch Error Key Name 03 */
    public static final String SVC_TASK_NUM = "SVC_TASK_NUM";

    /** CSV FILE NAME : TT&S Submissions-yyyyMMddHHmmss */
    public static final String TTS_FILE_NAME = "TT&S Submissions-";

    /** CSV FILE NAME EXT */
    public static final String CSV_EXT = ".csv";

    /** Date Length */
    public static final int DT_LEN = 14;

    /**
     * PROC_PGM_ID : NSBB048001
     */
    public static final String PROC_PGM_ID = "NSBB048001";

    /** Mail Group From(FROM0003) */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** Mail Group From(DS) */
    public static final String MAIL_GROUP_KEY_FROM = "NS";

    /** Mail Group To(NSBB0480) */
    public static final String MAIL_GROUP_ID_TO = "NSBB0480";

    /** Mail Item (TT&S Submission Notification -- ${detectionTime}) */
    public static final String MAIL_ITEM_DT = "detectionTime";

    /** Mail Item (Escalation Date : ${SVC_TSS_ESCL.SVC_TSS_ESCL_DT}) */
    public static final String MAIL_ITEM_SVC_TSS_ESCL_DT = "SVC_TSS_ESCL.SVC_TSS_ESCL_DT";

    /** Mail Item (Attachment name : ${CSV_Value}) */
    public static final String MAIL_ITEM_CSV_FILE_NAME = "CSV_Value";

    /** Mail Item (${batchId}) */
    public static final String MAIL_ITEM_BATCH_ID = "batchId";

    /** Mail Item (${errDate}) */
    public static final String MAIL_ITEM_ERR_DATE = "errDate";

    /** Mail Item (${message}) */
    public static final String MAIL_ITEM_ERR_MSG = "message";

    /** Column name : ESCL_TP_TXT */
    public static final String ESCL_TP_TXT = "ESCL_TP_TXT";

    /** Column name : TECH_SPRT_GRP_EML_ADDR */
    public static final String TECH_SPRT_GRP_EML_ADDR = "TECH_SPRT_GRP_EML_ADDR";

    /** Column name : SER_NUM */
    public static final String SER_NUM = "SER_NUM";

    /** Column name : MDL_NM */
    public static final String MDL_NM = "MDL_NM";

    /** Column name : TECH_NM */
    public static final String TECH_NM = "TECH_NM";

    /** Column name : CELL_PHO_NUM */
    public static final String CELL_PHO_NUM = "CELL_PHO_NUM";

    /** Column name : SVC_PBLM_TP_DESC_TXT */
    public static final String SVC_PBLM_TP_CD = "SVC_PBLM_TP_CD";

    /** Column name : SVC_PBLM_LOC_TP_DESC_TXT */
    public static final String SVC_PBLM_LOC_TP_CD = "SVC_PBLM_LOC_TP_CD";

    /** Column name : SVC_PBLM_RSN_TP_DESC_TXT */
    public static final String SVC_PBLM_RSN_TP_CD = "SVC_PBLM_RSN_TP_CD";

    /** Column name : SVC_PBLM_LOC_TP_DESC_TXT */
    public static final String HELP_DESK_TKT_NUM = "HELP_DESK_TKT_NUM";

    /** Column name : MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** Column name : SVC_CMNT_TXT */
    public static final String SVC_CMNT_TXT = "SVC_CMNT_TXT";

    /** Column name : SVC_CMNT_TXT */
    public static final String ATT_DATA_PK = "ATT_DATA_PK";

    /** Empty Value */
    public static final String EMPTY_STRING = "";

    /** CSV EXTENTION */
    public static final String EXT_CSV = ".csv";

    /** COMMA */
    public static final String STR_CNM = ",";

    /** BREAK */
    public static final String STR_CRLF = "\r\n";

    /** CSV Header */
    public static final String[] CSV_HEADER = {
            "Date (of Escalation)",      // SVC_TSS_ESCL_DT
            "Escalation Type",           // ESCL_TP_TXT
            "Serial Number",             // SER_NUM
            "Model",                     // MDL_NM
            "Tech Name",                 // TECH_NM
            "Cell #",                    // CELL_PHO_NUM
            "Problem Description ",      // SVC_PBLM_TP_DESC_TXT
            "Location Description",      // SVC_PBLM_LOC_TP_DESC_TXT
            "Reason Description",        // SVC_PBLM_RSN_TP_DESC_TXT
            "Help Desk Ticket Number",   // HELP_DESK_TKT_NUM
            "Parts Used",                // MDSE_NM
            "Comments ",                 // SVC_CMNT_TXT
            "Attachments",               // ATT_DATA_DESC_TXT
    };

    /** SQLID(DS_BIZ_PROC_LOGT) */
    public static final String SQLID = "001";
}
