/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0700.constant;

/**
 *<pre>
 * Contract On Billing Hold
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Hitachi         K.Kasai         Create          N/A
 * 2017/09/04   Hitachi         K.Kojima        Update          QC#20816
 *</pre>
 */
public final class NSAL0700Constant {

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
    public static final String BUSINESS_ID = "NSAL0700";

    /** Screen ID */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /** Error Message */
    public static final String NSAM0338W = "NSAM0338W";

    /** Your request cannot be processed under this status. */
    public static final String NSAM0065E = "NSAM0065E";

    /** Level Number : 10 */
    public static final int LVL_NUM_10 = 10;

    /** Level Number : 20 */
    public static final int LVL_NUM_20 = 20;

    /** Level Number : 30 */
    public static final int LVL_NUM_30 = 30;

    // START 2017/09/04 K.Kojima [QC#20816,ADD]
    /** Billing Hold Message */
    public static final String BLLG_HOLD_MSG = "Billing Hold";
    // END 2017/09/04 K.Kojima [QC#20816,ADD]
}
