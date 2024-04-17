/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0360.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/03   CUSA            Fujitsu         Create          N/A
 * 2015/10/23   Hitach          T.Tomita        Update          N/A
 *</pre>
 */
public class NSAL0360Constant {

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

    /** Button SelectAll */
    public static final String[] BTN_SELECT_ALL = {"SelectAll", "SelectAll" };

    /** Button UnSelectAll */
    public static final String[] BTN_UN_SELECT_ALL = {"UnSelectAll", "UnSelectAll" };

    /** Button InsertRow */
    public static final String[] BTN_INSERT_ROW = {"InsertRow", "InsertRow" };

    /** Button DeleteRow */
    public static final String[] BTN_DELETE_ROW = {"DeleteRow", "DeleteRow" };

    /** Button Schedules */
    public static final String[] BTN_SCHEDULES = {"Schedules", "Schedules" };

    /** Button SkipMonth */
    public static final String[] BTN_SKIP_MONTH = {"SkipMonth", "SkipMonth" };

    /** Screen ID */
    public static final String SCREEN_ID = "NSAL0360Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0360";

    /** READ */
    public static final String FUNC_ID_READ = BUSINESS_ID + "T010";

    /** UPDATE */
    public static final String FUNC_ID_UPDATE = BUSINESS_ID + "T020";

    /** Please enter a number between [@] and [@]. */
    public static final String NSAM0194E = "NSAM0194E";

    /** [@] does not exit. */
    public static final String NSAM0339E = "NSAM0339E";

    /** RADIO_VALUE_CLOSING_DAY */
    public static final BigDecimal RADIO_VALUE_CLOSING_DAY = new BigDecimal("1");

    /** TBL_NM_ARRAY */
    public static final String[] TBL_NM_ARRAY = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

    // START 2015/10/23 T.Tomita [N/A, ADD]
    /** XX_MODE_CD: reference mode */
    public static final String REF_MODE = "0";

    /** XX_MODE_CD: edit mode */
    public static final String EDIT_MODE = "1";
    // END 2015/10/23 T.Tomita [N/A, ADD]
}
