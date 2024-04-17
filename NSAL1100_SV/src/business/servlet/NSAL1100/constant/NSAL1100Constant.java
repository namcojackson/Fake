/* <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSAL1100.constant;

/**
 *<pre>
 * Approval List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL1100Constant {

    // [0]:Button Name [1]:Event Name [2]:Button Label
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
    public static final String[] BTN_CMN_DOWNROAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_BLANK7 = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Function Button : Continue */
    public static final String BTN_CONTINUE = "Continue";

    /** Function Button : OverrideApprove */
    public static final String BTN_OVERRIDE_APPROVE = "OverrideApprove";

    /** Function Button : CancelCI */
    public static final String BTN_CANCEL_CI = "CancelCI";

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL1100";

    /** Screen ID */
    public static final String SCREEN_ID = "NSAL1100Scrn00";

    /** Function code : Search */
    public static final String FUNC_SEARCH = "20";

    /** Function code : Update */
    public static final String FUNC_UPDATE = "40";

    /** Reference authority */
    public static final String AUTH_REFERENCE = "NSAL1100T010";

    /** Update authority */
    public static final String AUTH_UPDATE = "NSAL1100T020";

    /** NSAL1090_PRM_LENGTH : 1 */
    public static final int NSAL1090_PRM_LENGTH = 1;

    /** NSAL1100_PRM_LENGTH : 4 */
    public static final int NSAL1100_PRM_LENGTH = 4;

    /** MODE_CD : Submit for Approval */
    public static final String MODE_SUBMIT_FOR_APPROVAL = "1";

    /** MODE_CD : Cancel CI */
    public static final String MODE_CANCEL_CI = "2";

    /** MODE_CD : View Approvers */
    public static final String MODE_VIEW_APPROVERS = "3";
}
