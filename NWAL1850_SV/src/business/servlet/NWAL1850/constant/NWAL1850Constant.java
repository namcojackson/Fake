/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1850.constant;

/**
 *<pre>
 * NWAL1850Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         Y.Taoka         Create          N/A
 * 2016/05/30   Fujitsu         T.Murai         Update          S21_NA#8393
 * 2022/06/02   Hitachi         K.Kitachi       Update          QC#60037
 *</pre>
 */
public class NWAL1850Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1850";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1850Scrn00";

    /** Business Application ID (Scheduling Agreement Entry) */
    public static final String BIZ_ID_FOR_ENTRY = "NWAL1840";
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
    /** Please enter at least one search criteria. */
    public static final String NWAM0754E = "NWAM0754E";

    /** [@]  should be greater than [@]. */
    public static final String NWAM0712E = "NWAM0712E";


    // --------------------------------
    // Other
    // --------------------------------

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** 8 Digit Mode */
    public static final String EIGHT_DIGIT_MODE = "08";

    /** Percent */
    public static final String PERCENT = "%"; // S21_NA#8393 Add

    // START 2022/06/02 K.Kitachi [QC#60037, ADD]
    /** Delivery Hold Pulldown: ALL (Code) */
    public static final String DELY_HLD_CD_ALL = "ALL";
    // END 2022/06/02 K.Kitachi [QC#60037, ADD]
}
