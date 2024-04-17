/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0850.constant;

/** 
 *<pre>
 * Sales Credit for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Hitachi         Y.Takeno        Create          N/A
 * 2016/05/20   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/07/27   Hitachi         M.Gotou         Update          QC#11854
 *</pre>
 */
public final class NSAL0850Constant {

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
    public static final String BUSINESS_ID = "NSAL0850";

    /** Reference authority */
    public static final String AUTH_REFERENCE = "NSAL0850T010";

    /** Update authority */
    public static final String AUTH_UPDATE = "NSAL0850T020";

    /** SCRN_ID : NSBL0450Scrn00 */
    public static final String SCRN_ID = "NSAL0850Scrn00";

    /** Function code (Search) */
    public static final String FUNCTION_SEARCH = "20";

    /** Function code (Submit) */
    public static final String FUNCTION_SUBMIT = "40";

    /** mode (After Search) */
    public static final String MODE_AFTER_SEARCH = "01";

    /** mode (After Open For Correction) */
    public static final String MODE_AFTER_OPEN = "02";

    /** parameter length (NSAL1240) */
    public static final int PARAM_LENGTH_NSAL1240 = 31;

    /** parameter index : 30 */
    public static final int PARAM_INDEX_30 = 30;

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

    /** TOC_CD Length : 8 */
    public static final int TOC_CD_LENGTH = 8;

    /** TOC_NM Length : 50 */
    public static final int TOC_NM_LENGTH = 50;

    /** "@" is not entered. */
    public static final String NSAM0007E = "NSAM0007E";

    /** Please specify "@" for the file extension. */
    public static final String NSAM0404E = "NSAM0404E";

    // START 2016/07/27 [QC#11854, ADD]
    /** Got an error while validation data. Please correct it and try again. */
    public static final String NSAM0541E = "NSAM0541E";
    // END   2016/07/27 [QC#10854, ADD]

    // START 2016/05/20 [QC#4061, MOD]
    /** file extension */
    public static final String[] FILE_EXTENSION = {"csv", "txt", "xls", "xlsx"};
    // END   2016/05/20 [QC#4061, MOD]
}
