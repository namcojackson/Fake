/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0720.constant;

/**
 *<pre>
 * Update Bill To
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Hitachi         K.Kasai         Create          N/A
 * 2015/12/10   Hitachi         T.Tsuchida      Update          QC#1611
 * 2016/01/05   Hitachi         T.Tomita        Update          QC#1029
 *</pre>
 */
public final class NSAL0720Constant {

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

    /** Button Bill To */
    public static final String BTN_OPENWIN_BILLTO = "OpenWin_BillTo";

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0720";

    /** ScreenID : NSAL0720Scrn00 */
    public static final String SCREEN_ID = "NSAL0720Scrn00";

    /** Error Message */
    public static final String NSAM0338W = "NSAM0338W";

    /** Your request cannot be processed under this status. */
    public static final String NSAM0065E = "NSAM0065E";

    /** "@" is not entered. */
    public static final String NSAM0007E = "NSAM0007E";

    // START 2016/01/05 T.Tomita [QC#1029, ADD]
    /**
     * Function Code: Inquiry
     */
    public static final String EZD_FUNC_CD_INQ = "20";

    /**
     * Space: " "
     */
    public static final String SPACE = " ";

    /** Display Hierarchy Accounts Code: 02 */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";
    // END 2016/01/05 T.Tomita [QC#1029, ADD]
}
