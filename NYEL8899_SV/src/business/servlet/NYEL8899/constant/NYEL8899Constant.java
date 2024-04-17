/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8899.constant;

/**
 *<pre>
 * NYEL8899Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8899Constant {

    /** Business ID */
    public static final String BIZ_ID = "NYEL8899";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NYEL8899Scrn00";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = { "btn2", "CMN_Submit", "Submit" };

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

    //TODO [Template] if popup screen then use below.
//    // --------------------------------
//    // Common button for Popup
//    // --------------------------------
//    /** F8 : Clear */
//    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };
//
//    /** F10 : Return */
//    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";
}
