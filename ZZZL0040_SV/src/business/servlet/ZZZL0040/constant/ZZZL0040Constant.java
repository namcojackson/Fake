package business.servlet.ZZZL0040.constant;

import parts.common.EZDApplicationContextConst;

public interface ZZZL0040Constant extends EZDApplicationContextConst {
    /**
     * Common button
     */
    static final String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };
    static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };
    static final String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };
    static final String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };
    static final String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };
    static final String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };
    static final String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };
    static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };
    static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };
    static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };
    
    /**
     * Screen ID
     */
    static final String SCREEN_ID = "ZZZL0040Scrn00";
    String BUSINESS_ID = "ZZZL0040";
    
    /**
     * Default time value
     */
    String DEF_HRS_FROM = "00";
    String DEF_MIN_FROM = "00";
    String DEF_SEC_MILLI_FROM = "00000";
    String DEF_HRS_TO = "23";
    String DEF_MIN_TO = "59";
    String DEF_SEC_MILLI_TO  = "00000";
    
    /**
     * Hour contents
     */
    static final String[] Hour = {"00", "01", "02", "03", "04", 
                                  "05", "06", "07", "08", "09",
                                  "10", "11", "12", "13", "14",
                                  "15", "16", "17", "18", "19",
                                  "20", "21", "22", "23"};

    /**
     * Minutes contents
     */
    static final String[] Min = {"00", "01", "02", "03", "04",
                                 "05", "06", "07", "08", "09",
                                 "10", "11", "12", "13", "14",
                                 "15", "16", "17", "18", "19",
                                 "20", "21", "22", "23", "24",
                                 "25", "26", "27", "28", "29",
                                 "30", "31", "32", "33", "34",
                                 "35", "36", "37", "38", "39",
                                 "40", "41", "42", "43", "44",
                                 "45", "46", "47", "48", "49",
                                 "50", "51", "52", "53", "54",
                                 "55", "56", "57", "58", "59" };
    
    /**
     * JVM contents
     */
    static final String[] JVM = {"server1", "server2", "server3", "server4",
                                 "server5", "server6", "server7", "server8"};
    
    /**
     * Radio button contents
     */
    static final int BIZ_PROC_TIME_RANK = 001;
    static final int BIZ_THROUGHPUT_RANK = 002;
    static final int GLBL_AREA_SIZE_RANK = 003;
    
}
