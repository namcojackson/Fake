/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0050.constant;

/**
 *<pre>
 * NWWL0050Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/05   Fujitsu         M.Ohno          Create          N/A
 * 2016/10/06   Fujitsu         Mz.Takahashi    Update          QC#14431
 *</pre>
 */
public class NWWL0050Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWWL0050";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWWL0050Scrn00";

    /** Business ID */
    public static final String UPD_FUNC_ID = "NWWL0050T020";

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

    /** Add Line */
    public static final String BTN_ADD_LINE_NM = "Add_Line";

    /** Del Line */
    public static final String BTN_DELETE_LINE_NM = "Del_Line";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";

    /** For [@], please enter [@] or a later date. */
    public static final String NWWM0005E = "NWWM0005E";

    /** Email address is not valid. */
    public static final String NWWM0011E = "NWWM0011E";

    /** Set number exceeds the maximum number. Maximum number: [@] */
    public static final String NWWM0029E = "NWWM0029E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Message Kind warning */
    public static final String MESSAGE_KIND_WARNING = "W";

    /** Message Kind info */
    public static final String MESSAGE_KIND_INFO = "I";

    // --------------------------------
    // Common
    // --------------------------------

    /** EMail check Pattern */
    // 2016/10/06 QC#1443 Del
    //public static final String CHK_EMAIL_PATTERN = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

}