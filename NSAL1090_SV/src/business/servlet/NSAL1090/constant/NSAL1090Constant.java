/* <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSAL1090.constant;

/**
 *<pre>
 * Credit Rebill Detail Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Hitachi         A.Kohinata      Create          N/A
 * 2018/05/31   CITS            M.Naito         Update          QC#22908
 * 2019/02/13   Hitachi         K.Kim           Update          QC#30309
 *</pre>
 */
public class NSAL1090Constant {

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

    /** Function Button : SubmitForApproval */
    public static final String BTN_SUBMIT_FOR_APPROVAL = "SubmitForApproval";

    /** Function Button : ViewApprovers */
    public static final String BTN_VIEW_APPROVERS = "ViewApprovers";

    /** Function Button : Research */
    public static final String BTN_RESEARCH = "Research";

    /** Function Button : CancelCI */
    public static final String BTN_CANCEL_CI = "CancelCI";

    /** Screen ID */
    public static final String SCREEN_ID = "NSAL1090Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL1090";

    /** Function code : Search */
    public static final String FUNC_SEARCH = "20";

    /** Function code : Update */
    public static final String FUNC_UPDATE = "40";

    /** Your request cannot be processed under this status. */
    public static final String NSAM0065E = "NSAM0065E";

    /** NSAL1030_PRM_LENGTH : 7 */
    public static final int NSAL1030_PRM_LENGTH = 7;

    /** NSAL1100_PRM_LENGTH : 4 */
    public static final int NSAL1100_PRM_LENGTH = 4;

    /** NSAL1120_PRM_LENGTH : 5 */
    public static final int NSAL1120_PRM_LENGTH = 5;

    /** NSAL1100_MODE : Submit for Approval */
    public static final String NSAL1100_MODE_SUBMIT_FOR_APPROVAL = "1";

    /** NSAL1100_MODE : Cancel CI */
    public static final String NSAL1100_MODE_CANCEL_CI = "2";

    /** NSAL1100_MODE : View Approvers */
    public static final String NSAL1100_MODE_VIEW_APPROVERS = "3";

    /** NSAL1120_MODE : BASE_CHARGE */
    public static final String NSAL1120_MODE_BASE_CHARGE = "1";

    /** NSAL1120_MODE : METER_CHARGE */
    public static final String NSAL1120_MODE_METER_CHARGE = "2";

    /** Invoice Type : BASE */
    public static final String INV_TP_BASE = "BASE";

    /** Invoice Type : USAGE */
    public static final String INV_TP_USAGE = "USAGE";

    // START 2018/05/31 M.Naito [QC#22908, ADD]
    // START 2019/02/13 [QC#30309, DEL]
    // /** READ */
    // public static final String FUNC_ID_READ = BUSINESS_ID + "T010";

    // /** UPDATE */
    // public static final String FUNC_ID_UPDATE = BUSINESS_ID + "T020";

    // /** SERIAL_CHANGE */
    // public static final String FUNC_ID_SER_CHANGE = BUSINESS_ID + "T030";

    // /** LGSC_USER */
    // public static final String FUNC_ID_LGSC = BUSINESS_ID + "T040";

    // /** MDSE_EDIT_USER */
    // public static final String FUNC_ID_MDSE_EDIT = BUSINESS_ID + "T050";

    // /**
    //  * Parameter Display Mode
    //  */
    // public static final String PARAMS_DISPLAY_MODE = "Upload-Only";
    // END 2019/02/13 [QC#30309, DEL]

    // START 2019/02/13 [QC#30309, ADD]
    /**
     * Parameter Display Mode : Modification
     */
    public static final String PARAMS_DISPLAY_MODE_MODIFICATION = "Modification";
    // END 2019/02/13 [QC#30309, ADD]

    /**
     * Parameter Display Mode : Read-Only
     */
    public static final String PARAMS_DISPLAY_MODE_READ_ONLY = "Read-Only";

    /**
     * Parameter Upper Key
     */
    public static final String PARAMS_UPPER_KEY = "AR_NSAL0010_ATT_LMT";

    /**
     * Parameter Extension Key
     */
    public static final String PARAMS_EXTENSION_KEY = "AR_NSAL0010_AUTH_FILE_EXT";

    /**
     * Parameter Size Key
     */
    public static final String PARAMS_SIZE_KEY = "AR_NSAL0010_AUTH_FILE_VOL";
    // END 2018/05/31 M.Naito [QC#22908, ADD]
}
