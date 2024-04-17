/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0950.constant;

/**
 *<pre>
 * Contract Validation Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/14   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public final class NSAL0950Constant {

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
    public static final String[] BTN_CMN_DOWNROAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0950";

    /** Reference authority */
    public static final String AUTH_REFERENCE = "NSAL0950T010";

    /** Update authority */
    public static final String AUTH_UPDATE = "NSAL0950T020";

    /** SCRN_ID */
    public static final String SCRN_ID = "NSAL0950Scrn00";

    /** Function code (Search) */
    public static final String FUNCTION_SEARCH = "20";

    /** Function code (Submit) */
    public static final String FUNCTION_SUBMIT = "40";

    /** For [@], please enter  [@] or a later date. */
    public static final String NSAM0457E = "NSAM0457E";

    /** length 7 */
    public static final int LENGTH_7 = 7;

    /** The number of the attribute of WhereList */
    public static final int ATTR_NUM_WHERE_LIST = 4;

    /** The index number of the attribute of WhereList */
    public static final int WLIST_DSP_OBJ_NM = 0;

    /** The index number of the attribute of WhereList */
    public static final int WLIST_OBJ_ID = 1;

    /** The index number of the attribute of WhereList */
    public static final int WLIST_OBJ_VALUE = 2;

    /** The index number of the attribute of WhereList */
    public static final int WLIST_WHERE_FLG = 3;

    /** The number of the attribute of ColumnList */
    public static final int ATTR_NUM_CLMN_LIST = 4;

    /** The index number of the attribute of ColumnList */
    public static final int CLIST_DSP_OBJ_NM = 0;

    /** The index number of the attribute of ColumnList */
    public static final int CLIST_OBJ_ID = 1;

    /** Tc12he index number of the attribute of ColumnList */
    public static final int CLIST_OBJ_LENGTH = 2;

    /** The index number of the attribute of ColumnList */
    public static final int CLIST_LINK_FLG = 3;

    /** VLD_CMPT_TXT Length */
    public static final int VLD_CMPT_TXT_LENGTH = 40;

    /** VLD_CMPT_DESC_TXT Length */
    public static final int VLD_CMPT_DESC_TXT_LENGTH = 40;

    /** select popup : Search */
    public static final String SELECT_POPUP_SEARCH = "S";

    /** select popup : Detail */
    public static final String SELECT_POPUP_DETAIL = "D";

    /** Screen ID */
    public static final String SCREEN_ID = "NSAL0950Scrn00";
}
