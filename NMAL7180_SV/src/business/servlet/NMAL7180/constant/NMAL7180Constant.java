/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7180.constant;

/**
 *<pre>
 * NMAL7180Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7180Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7180";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7180Scrn00";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    // --------------------------------
    // Business button
    // --------------------------------
    /** Button Search */
    public static final String[] BTN_SEARCH = {"Search", "Search", "Search" };

    /** Button View Group */
    public static final String[] BTN_VIEW_GROUP = {"MoveWin_PrcGrpSetUp", "MoveWin_PrcGrpSetUp", "View Group" };

    /** Button New Group */
    public static final String[] BTN_NEW_GROUP = {"MoveWin_PrcGrpSetUpNew", "MoveWin_PrcGrpSetUpNew", "New Group" };

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /**  Please set at least one search criteria */
    public static final String NMAM0192E = "NMAM0192E";

    /**  A past date cannot be entered into the "Date Effective From */
    public static final String NMAM0185E = "NMAM0185E";

    /**  Common Message */
    public static final String ZZM9037E = "ZZM9037E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";
}
