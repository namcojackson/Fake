/*
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NMAL6870.constant;

/**
 *<pre>
 * Multi Selection Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/27   Fujitsu         S.Yoshida         Create          N/A
 *</pre>
 */
public class NMAL6870Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL6870";

    /** Screen ID */
    public static final String SCRN_ID = BIZ_ID + "Scrn00";

    /** Table ID for Row BG Color */
    public static final String TABLE_ID = "A";

    /**
     * Table ID (body)
     */
    public static final String TABLE_ID_BODY = "bottomTBL";

    /** Function Code (Search) */
    public static final String FUNC_CD_SRCH = "20";

    /** Function Code (Update) */
    public static final String FUNC_CD_UPD = "40";

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
    public static final String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Close", "Close" };

    /** Event name for CMN_Close */
    public static final String EVENT_CMN_CLOSE = "CMN_Close";

    // Indexes of Popup parameter
    /**
     * Popup parameter 0
     */
    public static final int POP_PAR_0 = 0;

    /**
     * Popup parameter 1
     */
    public static final int POP_PAR_1 = 1;

    /**
     * Popup Close Button Name
     */
    public static final String POPUP_CLOSE = "CMN_Close";

    /**
     * The maximum number of data has been exceeded.
     */
    public static final String NMAM0289E = "NMAM0289E";

    /**
     * Please enter an public static final integer value between 1 and 9999999999.
     */
    public static final String NMAM0290E = "NMAM0290E";

    /**
     * Please select item(s).
     */
    public static final String NMAM8054E = "NMAM8054E";

    /** Character means Error Message */
    public static final String MESSAGE_KIND_ERROR = "E";

    /**
     * character width
     */
    public static final int CHAR_WIDTH = 12;

    /**
     * style attribute width
     */
    public static final String STYLE_ATTR_WIDTH = "width";

    /**
     * style attribute height
     */
    public static final String STYLE_ATTR_HEIGHT = "height";

    /**
     * style attribute visibility
     */
    public static final String STYLE_ATTR_VISIBILITY = "visibility";

    /**
     * style value visibility hidden
     */
    public static final String STYLE_VALUE_VISIBILITY_HIDDEN = "hidden";

    /**
     * unit name px
     */
    public static final String UNIT_NAME_PX = "px";

    /**
     * unit name em
     */
    public static final String UNIT_NAME_EM = "em";

    /**
     * px value zero
     */
    public static final String PX_VALUE_ZERO = "0px";

    /**
     * Table Body Height
     */
    public static final int TABLE_BODY_HEIGHT = 342;

    /**
     * Search Criteria Table Row Height
     */
    public static final int SEARCH_CRITERIA_ROW_HEIGHT = 18;

    /**
     * MAX_INPUT_PARAM_NUM = 7
     */
    public static final int MAX_INPUT_PARAM_NUM = 7;

    /** INPUT_PARAM_SUFFIX = 0 */
    public static final int INPUT_PARAM_SUFFIX = 0;

    /**
     * INPUT_PARAM_SCR_NM = 1
     */
    public static final int INPUT_PARAM_SCR_NM = 1;

    /**
     * INPUT_PARAM_TBL_NM = 2
     */
    public static final int INPUT_PARAM_TBL_NM = 2;

    /**
     * INPUT_PARAM_CRITERIA = 3
     */
    public static final int INPUT_PARAM_CRITERIA = 3;

    /**
     * INPUT_PARAM_COLUMN = 4
     */
    public static final int INPUT_PARAM_COLUMN = 4;

    /**
     * INPUT_PARAM_SORT = 5
     */
    public static final int INPUT_PARAM_SORT = 5;

    /**
     * INPUT_PARAM_RESULT = 6
     */
    public static final int INPUT_PARAM_RESULT = 6;

    /**
     * COLUMN_0 = 0
     */
    public static final int COLUMN_0 = 0;

    /**
     * COLUMN_1 = 1
     */
    public static final int COLUMN_1 = 1;

    /**
     * COLUMN_2 = 2
     */
    public static final int COLUMN_2 = 2;

    /**
     * COLUMN_3 = 3
     */
    public static final int COLUMN_3 = 3;

    /**
     * COLUMN_4 = 4
     */
    public static final int COLUMN_4 = 4;

    /**
     * COLUMN_5 = 5
     */
    public static final int COLUMN_5 = 5;

    /**
     * COLUMN_6 = 6
     */
    public static final int COLUMN_6 = 6;

    /**
     * COLUMN_7 = 7
     */
    public static final int COLUMN_7 = 7;

    /**
     * COLUMN_8 = 8
     */
    public static final int COLUMN_8 = 8;

    /**
     * COLUMN_9 = 9
     */
    public static final int COLUMN_9 = 9;
}
