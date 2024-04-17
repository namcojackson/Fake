/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7200.constant;

/**
 *<pre>
 * NMAL7200Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7200Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7200";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7200Scrn00";

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
    // Message ID
    // --------------------------------
    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Please set at least one search criteria */
    public static final String NMAM0192E = "NMAM0192E";

    // --------------------------------
    // Usage Type
    // --------------------------------

    /** Usage Type List */
    public static final String USAGE_TYPE_RULE = "Price Rule";

    /** Usage Type List */
    public static final String USAGE_TYPE_LIST = "Price List";

    // --------------------------------
    // Next Screen Param
    // --------------------------------

    /** Next Screen To List */
    public static final String NEXT_SCREEN_TO_LIST = "ToList";

    /** Next Screen To Rule */
    public static final String NEXT_SCREEN_TO_RULE = "ToRule";

    /** Next Screen To Adjustment */
    public static final String NEXT_SCREEN_TO_ADJUSTMENT = "ToAdjustment";

    /** Param Size */
    public static final int PARAM_SIZE = 1;

}
