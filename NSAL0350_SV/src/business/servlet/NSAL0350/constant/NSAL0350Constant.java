/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0350.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/10   CUSA            Fujitsu         Create          N/A
 * 2016/04/25   Hitachi         T.Iwamoto       Update          QC#1759
 *</pre>
 */
public class NSAL0350Constant {

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
    public static final String[] BTN_CMN_BLANK6 = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_BLANK7 = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Screen ID */
    public static final String SCREEN_ID = "NSAL0350Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0350";

    /** READ */
    public static final String FUNC_ID_READ = BUSINESS_ID + "T010";

    /** UPDATE */
    public static final String FUNC_ID_UPDATE = BUSINESS_ID + "T020";

    /** BASE_TBL_NM */
    public static final String BASE_TBL_NM = "O";

    /** USAGE_TBL_NM_ARRAY */
    public static final String[] USAGE_TBL_NM_ARRAY = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

    // ADD start 2016/04/25 CSA Defect#1759
    /** HEADER_LIST */
    public static final String HEADER_LIST = "MtrList";

    /** STYLE_CLASS */
    public static final String STYLE_CLASS = "pEvenNumberBGcolor";
    // ADD end 2016/04/25 CSA Defect#1759

}
