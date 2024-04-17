/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0740.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Hitachi         Y.Takeno        Create          N/A
 * 2016/04/27   Hitachi         M.Gotou         Update          QC#6799
 * 2019/11/19   Hitachi         Y.Takeno        Update          QC#52179
 *</pre>
 */
public class NSAL0740Constant {

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

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0740";

    /** Screen ID */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /** Function Code : Search */
    public static final String FUCNTION_CD_SEARCH = "20";

    /** Function Code : Update */
    public static final String FUCNTION_CD_UPDATE = "40";

    /** Line Level Number : 1(contract) */
    public static final String LINE_LEVEL_CONTRACT = "1";

    /** Line Level Number : 2(contract detail) */
    public static final String LINE_LEVEL_CONTRACT_DETAIL = "2";

    /** Line Level Number : 3(base / overage) */
    public static final String LINE_LEVEL_BASE_OVERAGE = "3";

    /** Base/Overage : BASE */
    public static final String BASE = "BASE";

    /** Base/Overage : OVERAGE */
    public static final String OVERAGE = "OVERAGE";

    // add start 2016/04/27 CSA Defect#6799
    /**
     * [@] is mandatory.
     */
    public static final String ZZZM9025E = "ZZZM9025E";
    // add end 2016/04/27 CSA Defect#6799

    // START 2019/11/19 [QC#52179, ADD]
    /** Function ID : NSAL0740T010 */
    public static final String FUNCTION_ID_UPDATE = "NSAL0740T010";

    /** Button : APPLY TO ALL */
    public static final String BTN_APPLY_TO_ALL = "ApplyToAll";
    
    // END   2019/11/19 [QC#52179, ADD]
}
