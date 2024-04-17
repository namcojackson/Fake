/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1200.constant;

/**
 * <pre>
 * Business ID : NPAL1200 Insourcing Planning Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS       Yasushi Nomura        Create          N/A
 * 01/17/2017   CITS            T.Kikuhara      Update          QC#16256
 *</pre>
 */
public class NPAL1200Constant {
    /**
     * Business Application ID
     */
    public static final String BUSINESS_APPLICATION_ID = "NPAL1200";

    /** Function Update */
    public static final String FUNCTION_UPDATE = "NPAL1200T020";

    /**
     * Screen ID
     */
    public static final String SCREEN_ID = "NPAL1200Scrn00";

    /** . */
    public static final String POP_PARAM_01 = "01";

    /** . */
    public static final String POP_PARAM_02 = "02";

    /** . */
    public static final int MODE_ERROR = -1;

    /** . */
    public static final int MODE_INIT = 0;

    /** . */
    public static final int MODE_NORMAL = 1;

    /** . */
    public static final int MODE_EDIT = 2;

    /** . */
    public static final int MODE_DELETE = 3;

    /** . */
    public static final int MODE_ADD = 4;

    /** . */
    public static final String LIKE_CHAR = "%";

    /** . */
    public static final int FRAC_DEGIT = 2;

    // =================================================
    // Message
    // =================================================
    /** . */
    public static final String ZZM9000E = "ZZM9000E";

    /** . */
    public static final String NPAM1348E = "NPAM1348E";

    /** . */
    public static final String NPAM1216E = "NPAM1216E";

    /** . */
    public static final String NPAM1237W = "NPAM1237W";

    /** . */
    public static final String NPAM0049E = "NPAM0049E";

    /** . */
    public static final String NPAM0005I = "NPAM0005I";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * ｓ Common button 2
     */
    public static final String[] BTN_CMN_BTN_2 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_BTN_9 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Return", "Return" };
}
