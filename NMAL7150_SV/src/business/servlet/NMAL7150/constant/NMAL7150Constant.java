/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7150.constant;

/**
 *<pre>
 * CSMP Contract Synchronization  Errors Correction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7150Constant {
    /** Business ID */
    public static final String BIZ_ID = "NMAL7150";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7150Scrn00";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";

    /** The value for [@] must be smaller than [@]. */
    public static final String NMAM0043E = "NMAM0043E";

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

    /** Search Button */
    public static final String[] BTN_SEARCH = {"Search", "Search", "Search" };

    /** UnSelect All Button */
    public static final String[] BTN_UN_SELECT_ALL = {"UnSelectAll", "UnSelectAll", "UnSelectAll" };

    /** Select All Button */
    public static final String[] BTN_SELECT_ALL = {"SelectAll", "SelectAll", "SelectAll" };

    /** Delete Button */
    public static final String[] BTN_DELETE = {"Delete", "Delete", "Delete" };

    /** Reprocess Button */
    public static final String[] BTN_REPROCESS = {"Reprocess", "Reprocess", "Reprocess" };

    // --------------------------------
    // Common
    // --------------------------------
    /** FUNC_ID_FULL */
    public static final String FUNC_ID_FULL = "NMAL7150T030";

    /** FUNC_ID_DELETE */
    public static final String FUNC_ID_DELETE = "NMAL7150T020";

    /** FUNC_ID_READ */
    public static final String FUNC_ID_READ = "NMAL7150T010";

    /** Error Kind */
    public static final String MESSAGE_KIND_ERR = "E";
}
