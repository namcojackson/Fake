package business.servlet.ZZOL0600.constant;

public interface ZZOL0600Constant {

    String SH_DATE_FMT = "yyyyMMdd";

    String ONLINE_FLG = "Online";

    String BATCH_FLG = "Batch";

    String ALL_FLG = "All";

    String ALL_CHAR = "A";

    String ONLINE_CHAR = "N";

    String BATCH_CHAR = "Y";

    String glbl_cmpy_cd = "GLBL_CMPY_CD";

    String bat_proc_log = "BAT_PROC_LOG_PK";

    String bat_proc_joblog_path = "BAT_PROC_JOB_LOG_PATH_NM";

    String BIZAPL_CHKBOX_OFF_N = "N";

    String BIZAPL_CHKBOX_ON_Y = "Y";

    /**
     * Common Button Name.
     */
    String[] CMN_BTN1 = {"btn1", "CMN_Save", "Save" };

    String[] CMN_BTN2 = {"btn2", "CMN_Submit", "Submit" };

    String[] CMN_BTN3 = {"btn3", "CMN_Apply", "Apply" };

    String[] CMN_BTN4 = {"btn4", "CMN_Approve", "Approve" };

    String[] CMN_BTN5 = {"btn5", "CMN_Reject", "Reject" };

    String[] CMN_BTN6 = {"btn6", "CMN_Download", "Download" };

    String[] CMN_BTN7 = {"btn7", "CMN_Delete", "Delete" };

    String[] CMN_BTN8 = {"btn8", "CMN_Clear", "Clear" };

    String[] CMN_BTN9 = {"btn9", "CMN_Reset", "Reset" };

    String[] CMN_BTN10 = {"btn10", "CMN_Return", "Return" };

    String[] BTN11 = {"btn11", "DownloadJobLog", "Download JobLog" };

    String pageID = "ZZOL0600Scrn00";
    String BUSINESS_ID = "ZZOL0600";

    /**
     * Pull down value
     */
    String TRA_TYPE_CREATE = "CREATE";

    String TRA_TYPE_UPDATE = "UPDATE";

    String TRA_TYPE_L_DELETE = "L_DELETE";

    String TRA_TYPE_DELETE = "DELETE";

    /**
     * default time value
     */
    String DEF_HRS_FROM = "00";

    String DEF_MIN_FROM = "00";

    String DEF_SEC_MILLI_FROM = "00000";

    String DEF_HRS_TO = "23";

    String DEF_MIN_TO = "59";

    String DEF_SEC_MILLI_TO = "59999";

    /**
     * Hour
     */
    final String[] Hour = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" };

    /**
     * Minutes
     */
    final String[] Min = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34",
            "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" };
}
