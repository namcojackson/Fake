/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0460.constant;

/**
 *<pre>
 * Start Read Capture
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Hitachi         T.Iwamoto       Create          N/A
 * 2015/12/07   Hitachi         T.Tsuchida      Update          QC#1219
 * 2015/12/10   Hitachi         T.Tsuchida      Update          QC#1611
 * 2019/11/05   Hitachi         K.Kitachi       Update          QC#54164
 *</pre>
 */
public final class NSAL0460Constant {

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

    /** Button Read */
    public static final String[] BTN_CMN_READ = {"OpenMeterRead", "Read", "Read" };

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0460";

    /** ScreenID : NSAL0460Scrn00 */
    public static final String SCREEN_ID = "NSAL0460Scrn00";

    /** Reference authority */
    public static final String AUTH_REFERENCE = "NSAL0460T010";

    /** Update authority */
    public static final String AUTH_UPDATE = "NSAL0460T020";

    /** Function code (Search) */
    public static final String FUNCTION_SEARCH = "20";

    /** Function code (Submit) */
    public static final String FUNCTION_SUBMIT = "40";

    /** Your request cannot be processed under this status. */
    public static final String NSAM0065E = "NSAM0065E";

    /** Error Message */
    public static final String NSAM0219E = "NSAM0219E";

    /** Error Parameter */
    public static final String ERR_PRAM_NO_INPUT = "No input parameter found";

    /** Error Parameter */
    public static final String ERR_PRAM_LIMIT = "Number of Parameters exceed the limit.";

    // START 2019/11/05 K.Kitachi [QC#54164, MOD]
    /** NSAL1060_PRM_LENGTH : 4 */
//    public static final int NSAL1060_PRM_LENGTH = 3;
    public static final int NSAL1060_PRM_LENGTH = 4;
    // END 2019/11/05 K.Kitachi [QC#54164, MOD]

    /** PARAM_LENGTH : 200 */
    public static final int PARAM_LENGTH = 200;

}
