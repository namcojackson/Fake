/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1230.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/10   Hitachi         Y.Takeno        Create          N/A
 * 2018/04/10   CITS            T.Wada          Update          QC#23378(Sol#320)
 *</pre>
 */
public class NSAL1230Constant {

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
    public static final String[] BTN_CMN_BLANK6 = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_BLANK7 = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Button : */
    public static final String BTN_NM_SELECT_ALL = "SelectAll";

    /** Button : */
    public static final String BTN_NM_UNSELECT_ALL = "UnselectAll";

    /** Button : */
    public static final String BTN_NM_SELECT_SEGMENT = "Open_Win_GlComPop";

    /** Button : */
    public static final String BTN_NM_ADD = "Add";

    /** Button : */
    public static final String BTN_NM_DELETE = "Delete";

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL1230";

    /** Screen ID */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /** Mode : Reference */
    public static final String MODE_REFERENCE = "0";

    /** Mode : Update */
    public static final String MODE_UPDATE = "1";

    /** Function Code : Search */
    public static final String FUCNTION_CD_SEARCH = "20";

    /** Function Code : Update */
    public static final String FUCNTION_CD_UPDATE = "40";

    /** Function Id : NSAL1230T010 */
    public static final String FUNCTION_ID_NSAL1230T010 = "NSAL1230T010";

    /** Function Id : NSAL1230T020 */
    public static final String FUNCTION_ID_NSAL1230T020 = "NSAL1230T020";

    /** Message ID : NSAM0015E */
    public static final String NSAM0015E = "NSAM0015E";

    /** Message ID : NSAM0353E */
    public static final String NSAM0353E = "NSAM0353E";

    /** message parameter : No input parameter */
    public static final String MSG_PARAM_NO_INPUT_PARAM = "No input parameter";

    /** parameter array length(NMAL2550). */
    public static final int PARAM_ARRAY_LENGTH_NMAL2550 = 10;

    /** parameter array length(NSAL1230). */
    public static final int PARAM_ARRAY_LENGTH_NSAL1230 = 13;

    // QC#23378(Sol#320) Add Start
    /** LABEL [TotalPrice]. */
    public static final String LBL_BASE_CHARGE = "TotalPrice : "; 

    /** Message ID : NMAM0207E */
    public static final String NMAM0207E = "NMAM0207E";

    /** Message ID : ZZM9000E */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message ID : NSAM0714E */
    public static final String NSAM0714E = "NSAM0714E";

    /** Entry Mode Percent */
    public static final int MODE_PERCENT = 0;

    /** Entry Mode Price */
    public static final int MODE_PRICE = 1;
    // QC#23378(Sol#320) Add End
}
