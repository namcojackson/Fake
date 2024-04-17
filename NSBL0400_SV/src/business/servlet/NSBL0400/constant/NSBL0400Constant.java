/* <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSBL0400.constant;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         M.Gotou         Create          N/A
 * 2018/05/24   Hitachi         U.Kim           Update          QC#22393
 *</pre>
 */
public class NSBL0400Constant {

    /** ScreenID : NSBL0400Scrn00 */
    public static final String SCREEN_ID = "NSBL0400Scrn00";

    /** BizID : NSBL0400 */
    public static final String BIZ_ID = "NSBL0400";

    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * ｓ Common button 2
     */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * Button name attribute [Search]
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Button name attribute [New]
     */
    public static final String BTN_NEW = "OpenWin_MdsEty";

    /** Function Id: READ */
    public static final String FUNC_ID_INQ = BIZ_ID + "T010";

    /** length 7 */
    public static final int LENGTH_7 = 7;

    /**
     * Please fill out the search criteria for 1 field or more.
     */
    public static final String NSBM0001E = "NSBM0001E";

    /** FUNCTION */
    public static final String EZD_FUNC_CD_INQ = "20";

    // START 2018/05/24 U.Kim [QC#22393, ADD]
    /** parameter length (NSAL1240) */
    public static final int PARAM_LENGTH_NSAL1240 = 33;

    /** parameter index : 2 */
    public static final int PARAM_INDEX_02 = 2;

    /** parameter index : 4 */
    public static final int PARAM_INDEX_04 = 4;

    /** parameter index : 30 */
    public static final int PARAM_INDEX_30 = 30;

    // END 2018/05/24 U.Kim [QC#22393, ADD]
}
