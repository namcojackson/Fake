/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLEL0080.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   Hitachi         J.Kim           Create          N/A
 * 05/09/2016   Fujitsu         C.Tanaka        Update          QC#7065
 * 2016/05/17   Hitachi         K.Kojima        Update          QC#8101
 * 2016/05/20   Hitachi         T.Tsuchida      Update          QC#8096
 * 2016/07/20   Hitachi         T.Tsuchida      Update          QC#8092
 * 2016/09/15   Hitachi         J.Kim           Update          QC#10360
 *</pre>
 */
public class NLEL0080Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NLEL0080";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = "NLEL0080Scrn00";

    /**
     * Common button 1
     */
    public static final String[] SAVE = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    public static final String[] SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] APPLY = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] REJECT = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] DELETE = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * "Add" button
     */
    public static final String[] ADD = {"Add", "Add", "Add" };

    /**
     * "Delete" button
     */
    public static final String[] SCRN_DELETE = {"Delete", "Delete", "Delete" };

    /**
     * "GL" button [OpenWin_Customer]
     */
    public static final String[] SEARCH_GL = {"GL", "OpenWin_GL", "GL" };

    /**
     * "SR" button [OpenWin_Customer]
     */
    public static final String[] SEARCH_SR = {"SR", "OpenWin_SR", "SR" };

    /**
     * "C" button [OpenWin_Customer]
     */
    public static final String[] SEARCH_CUSTOMER = {"C", "OpenWin_Customer", "C" };

    /**
     * "V" button [OpenWin_V]
     */
    public static final String[] SEARCH_VENDER = {"V", "OpenWin_V", "V" };

    /**
     * Button is active
     */
    public static final int BTN_ACTIVE = 1;

    /**
     * Button is inacitve
     */
    public static final int BTN_INACTIVE = 0;

    /**
     * FUNC_ID_T010
     */
    public static final String FUNC_ID_T020 = "NLEL0080T020";

    /**
     * TABLE_A
     */
    public static final String TABLE_A = "A";

    /** Parameter Account Search: 24 */
    public static final int PRM_NMAL6760 = 24;

    /** Parameter Account Search: 8 */
    public static final int PRM_NMAL6800 = 8;

    /** The number of popup parameter:10 */
    public static final int PRM_NMAL2550 = 10;

    // START 2016/05/17 K.Kojima [QC#8101,ADD]
    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";
    // END 2016/05/17 K.Kojima [QC#8101,ADD]

    /**
     * The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

    /**
     * [@] field has too many decimal digits entered.
     */
    public static final String ZZM9015E = "ZZM9015E";

    /**
     * Display Hirarchey Account
     */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";

    /** ADD_LIMIT_COUNT */
    public static final int ADD_LIMIT_COUNT = 200;

    /** COMMA */
    public static final String STR_CNM = ".";

    /** Index Number 0 */
    public static final int IDX_0 = 0;

    /** Index Number 1 */
    public static final int IDX_1 = 1;

    /** Index Number 2 */
    public static final int IDX_2 = 2;

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Index Number 4 */
    public static final int IDX_4 = 4;

    /** Index Number 5 */
    public static final int IDX_5 = 5;

    /** Index Number 6 */
    public static final int IDX_6 = 6;

    /** Index Number 7 */
    public static final int IDX_7 = 7;

    /** Index Number 10 */
    public static final int IDX_10 = 10;

    // START 2016/07/20 T.Tsuchida [QC#8092,ADD]
    /** Index Number 12 */
    public static final int IDX_12 = 12;
    // END 2016/07/20 T.Tsuchida [QC#8092,ADD]

    /** Index Number 20 */
    public static final int IDX_20 = 20;

    /** Index Number 30 */
    public static final int IDX_30 = 30;

    // START 2016/05/09 C.Tanaka [QC#7065, ADD]
    /** [@] field requires numeric input only. */
    public static final String ZZM9004E = "ZZM9004E";

    /** Life In Month */
    public static final String LIFE_IN_MTH = "Life In Month";
    // END 2016/05/09 C.Tanaka [QC#7065, ADD]

    // START 2016/07/20 T.Tsuchida [QC#8092,ADD]
    /** Percent */
    public static final String PERCENT = "%";
    // END 2016/07/20 T.Tsuchida [QC#8092,ADD]

    // START 2016/09/13 J.Kim [QC#10360,ADD]
    /** Event Name : OpenWin_V */
    public static final String OPENWIN_VENDOR = "OpenWin_V";
    // END 2016/09/13 J.Kim [QC#10360,ADD]

    /**
     * REGEX:NUMBER
     */
    public static final String REGEX_NUMBER = "^[0-9]{1,3}$";
}
