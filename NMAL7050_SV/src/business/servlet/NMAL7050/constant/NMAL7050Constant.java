/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7050.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7050Constant {

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    /** Function Button 1 */
    public static final String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Function Button 2 */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    public static final String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Function Button 4 */
    public static final String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Function Button 5 */
    public static final String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Function Button 6 */
    public static final String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_DETELE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Button Search */
    public static final String[] BTN_SEARCH = {"Search", "Search", "Search" };

    /** Button Create new */
    public static final String[] BTN_CREATE_NEW = {"Create_new", "Create_new", "Create New" };

    /** Screen ID */
    public static final String SCREEN_ID = "NMAL7050Scrn00";

    /** CLOSE */
    public static final String CLOSE = "CLOSE";

    // =================================================
    // Event Name
    // =================================================
    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** Event Name: OpenWin_LocInfoRtlWh */
    public static final String EVENT_NM_LINK_MDL = "LINK_MDL";

    /** Popup Name: Service Model Search */
    public static final String POPUP_NM_SERVICE_MODEL_SEARCH = "Service Model Search";

    /** Popup Display Column Name: Model Name */
    public static final String POPUP_NM_MODEL_NAME_DISPLAY = "Model Name";

    /** Popup Column Name: Model Name */
    public static final String POPUP_NM_MODEL_NAME = "T_MDL_NM";

    /** Popup Sort Name: ASC */
    public static final String POPUP_SORT_KEY_ASC = "ASC";

    /**  A past date cannot be entered into the "Date Effective From */
    public static final String NMAM0185E = "NMAM0185E";

    /**  Please set at least one search criteria */
    public static final String NMAM0192E = "NMAM0192E";

    /**  [@] field has too many digits entered.  */
    public static final String NMAM8090W = "NMAM8090W";

    /**  Common Message */
    public static final String ZZM9037E = "ZZM9037E";
}
