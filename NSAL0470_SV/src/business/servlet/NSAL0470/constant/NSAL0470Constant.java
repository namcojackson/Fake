/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0470.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Hitachi         K.Yamada        Create          N/A
 * 2016/02/15   Hitachi         T.Aoyagi        Update          QC3050
 * 2017/01/25   Hitachi         K.Kitachi       Update          QC#17277
 *</pre>
 */
public class NSAL0470Constant {

    /** Application ID */
    public static final String BUSINESS_APPLICATION_ID = "NSAL0470";

    /** Screen ID */
    public static final String SCREEN_ID = BUSINESS_APPLICATION_ID + "Scrn00";

    /** Update Function */
    public static final String UPD_FUNC = BUSINESS_APPLICATION_ID + "T020";

    /** Supervisor Function */
    public static final String SUPERVISOR_FUNC = BUSINESS_APPLICATION_ID + "T030";

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
    public static final String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Function Button Send to supervisor */
    public static final String[] BTN_SEND_SUPERVISOR = {"SendTo_SuperVisor", "SendTo_SuperVisor", "Send to Supervisor" };

    /** Function Button Override outcome */
    public static final String[] BTN_OVERRIDE_OUTCOME = {"Override_Outcome", "Override_Outcome", "Override Outcome" };

    // START 2016/02/15 T.Aoyagi [QC3050, ADD]
    /** Function Button Fix on Contract */
    public static final String[] BTN_FIX_CONTRACT = {"FixOnContract", "FixOnContract", "Fix on Contract" };

    /** Function Button Activate Contract */
    public static final String[] BTN_ACTIVATE_CONTRACT = {"ActivateContract", "ActivateContract", "Activate Contract" };
    // END 2016/02/15 T.Aoyagi [QC3050, ADD]

    // START 2017/01/25 K.Kitachi [QC#17277, ADD]
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";
    // END 2017/01/25 K.Kitachi [QC#17277, ADD]
}
