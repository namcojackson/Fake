/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7020.constant;

/**
 *<pre>
 * NMAL7020Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7020Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7020";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7020Scrn00";

    /** Update User */
    public static final String FUNC_ID_UPDATE = BIZ_ID + "T020";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit"};

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

    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** If [@] is entered, please enter [@], too. */
    public static final String NMAM0049E = "NMAM0049E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** The value for [@] must be smaller than [@]. */
    public static final String NMAM0043E = "NMAM0043E";

    /** Please enter both [@] and [@]. */
    public static final String NMAM8289E = "NMAM8289E";

}
