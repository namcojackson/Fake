/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1400.constant;

/** 
 * NSAL1400 Named Customer Resource setup
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/01   Hitachi         T.Tomita        Create          QC#18362
 *</pre>
 */
public class NSAL1400Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL1400";

    /** Screen ID */
    public static final String SCREEN_ID_00 = BUSINESS_ID + "Scrn00";

    /** Function Edit */
    public static final String FUNC_ID_T010 = BUSINESS_ID + "T010";

    /** Function Inquiry */
    public static final String FUNC_ID_T020 = BUSINESS_ID + "T020";

    /** Function code (Search) */
    public static final String FUNCTION_SEARCH = "20";

    /** Function code (Submit) */
    public static final String FUNCTION_SUBMIT = "40";

    /** Button (OpenWin_Customer) */
    public static final String BTN_OPEN_CUST = "OpenWin_Customer";

    /** Button (OpenWin_CustomerLine) */
    public static final String BTN_OPEN_CUST_LINE = "OpenWin_CustomerLine";

    /** Button (OpenWin_Resource) */
    public static final String BTN_OPEN_RESRC = "OpenWin_Resource";

    /** Button (Add) */
    public static final String BTN_ADD = "Add";

    /** Button (Delete) */
    public static final String BTN_DELETE = "Delete";

    /** Function Button 1 Button Name */
    public static final String BTN_CMN_SAVE_BTN_NM = "btn1";

    /** Function Button 1 Label */
    public static final String BTN_CMN_SAVE_LABEL = "Save";

    /** Function Button 2 Button Name */
    public static final String BTN_CMN_SUBMIT_BTN_NM = "btn2";

    /** Function Button 2 Event Name */
    public static final String BTN_CMN_SUBMIT_EVENT_NM = "CMN_Submit";

    /** Function Button 2 Label */
    public static final String BTN_CMN_SUBMIT_LABEL = "Submit";

    /** Function Button 3 Button Name */
    public static final String BTN_CMN_APPLY_BTN_NM = "btn3";

    /** Function Button 3 Label */
    public static final String BTN_CMN_APPLY_LABEL = "Apply";

    /** Function Button 4 Button Name */
    public static final String BTN_CMN_APPROVE_BTN_NM = "btn4";

    /** Function Button 4 Label */
    public static final String BTN_CMN_APPROVE_LABEL = "Approve";

    /** Function Button 5 Button Name */
    public static final String BTN_CMN_REJECT_BTN_NM = "btn5";

    /** Function Button 5 Label */
    public static final String BTN_CMN_REJECT_LABEL = "Reject";

    /** Function Button 6 Button Name */
    public static final String BTN_CMN_DOWNLOAD_BTN_NM = "btn6";

    /** Function Button 6 Label */
    public static final String BTN_CMN_DOWNLOAD_LABEL = "Download";

    /** Function Button 7 Button Name */
    public static final String BTN_CMN_DELETE_BTN_NM = "btn7";

    /** Function Button 7 Label */
    public static final String BTN_CMN_DELETE_LABEL = "Delete";

    /** Function Button 8 Button Name */
    public static final String BTN_CMN_CLEAR_BTN_NM = "btn8";

    /** Function Button 8 Event Name */
    public static final String BTN_CMN_CLEAR_EVENT_NM = "CMN_Clear";

    /** Function Button 8 Label */
    public static final String BTN_CMN_CLEAR_LABEL = "Clear";

    /** Function Button 9 Button Name */
    public static final String BTN_CMN_RESET_BTN_NM = "btn9";

    /** Function Button 9 Button Name */
    public static final String BTN_CMN_RESET_EVENT_NM = "CMN_Reset";

    /** Function Button 9 Label */
    public static final String BTN_CMN_RESET_LABEL = "Reset";

    /** Function Button 10 Button Name */
    public static final String BTN_CMN_RETURN_BTN_NM = "btn10";

    /** Function Button 10 Event Name */
    public static final String BTN_CMN_RETURN_EVENT_NM = "CMN_Return";

    /** Function Button 10 Label */
    public static final String BTN_CMN_RETURN_LABEL = "Return";

    /** Search result Max Row */
    public static final int MAX_ROW = 2000;
}
