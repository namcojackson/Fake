/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NWAL1130.constant;

/**
 *<pre>
 *  Common PopUp Constant Values
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/22/2012   Fujitsu         T.Ishii         Create          N/A
 * 12/18/2012   Fujitsu         F.Saito         Update          WDS Defect#9
 * 10/09/2015   Fujitsu         M.Nakamura      Update          S21 CSA
 * 02/23/2016   Fujitsu         M.Ohno          Update          S21 CSA_QC#3243
 *</pre>
 */
public interface NWAL1130Constant {

    /** Business ID */
    String BIZ_ID = "NWAL1130";

    /** Screen ID */
    String SCRN_ID = BIZ_ID + "Scrn00";

    /** Table ID for Row BG Color */
    String TABLE_ID = "A";

    /**
     * Table ID (body)
     */
    String TABLE_ID_BODY = "bottomTBL";

    /** Function Code (Search) */
    String FUNC_CD_SRCH = "20";

    /** Function Code (Update) */
    String FUNC_CD_UPD = "40";

    // [0]:Button Name [1]:Event Name [2]:Button Label
    /** Function Button 1 */
    String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Function Button 2 */
    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Function Button 4 */
    String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Function Button 5 */
    String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Function Button 6 */
    String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    String[] BTN_CMN_RETURN = {"btn10", "CMN_Close", "Close" };

    /** Event name for CMN_Close */
    String EVENT_CMN_CLOSE = "CMN_Close";

    // Indexes of Popup parameter
    /**
     * Popup parameter 0
     */
    Integer POP_PAR_0 = 0;

    /**
     * Popup parameter 1
     */
    Integer POP_PAR_1 = 1;

    /**
     * Popup Close Button Name
     */
    String POPUP_CLOSE = "CMN_Close";

    /**
     * The maximum number of data has been exceeded.
     */
    String NLAM0156E = "NLAM0156E";

    /**
     * Please enter an integer value between 1 and 9999999999.
     */
    String NLAM0005E = "NLAM0005E";

    /** Character means Error Message */
    String MESSAGE_KIND_ERROR = "E";

    /**
     * character width
     */
    int CHAR_WIDTH = 12;

    /**
     * style attribute width
     */
    String STYLE_ATTR_WIDTH = "width";

    /**
     * style attribute height
     */
    String STYLE_ATTR_HEIGHT = "height";

    /**
     * style attribute visibility
     */
    String STYLE_ATTR_VISIBILITY = "visibility";

    /**
     * style value visibility hidden
     */
    String STYLE_VALUE_VISIBILITY_HIDDEN = "hidden";

    /**
     * style attribute word-break
     */
    String STYLE_ATTR_WORD_BREAK = "word-break";

    /**
     * style value break-all
     */
    String STYLE_VALUE_BREAK_ALL = "break-all";

    /**
     * style attribute white-space
     */
    String STYLE_ATTR_WHITE_SPACE = "white-space";

    /**
     * style value nowrap
     */
    String STYLE_VALUE_NOWRAP = "nowrap";

    /**
     * unit name px
     */
    String UNIT_NAME_PX = "px";

    /**
     * unit name em
     */
    String UNIT_NAME_EM = "em";

    /**
     * px value zero
     */
    String PX_VALUE_ZERO = "0px";

    // S21 CSA Mod Start
    /**
     * Table Body Height
     */
//    int TABLE_BODY_HEIGHT = 402;
    int TABLE_BODY_HEIGHT = 342;
    // S21 CSA Mod End

    /**
     * Search Criteria Table Row Height
     */
    int SEARCH_CRITERIA_ROW_HEIGHT = 18;

    /**
     * MAX_INPUT_PARAM_NUM = 7
     */
    int MAX_INPUT_PARAM_NUM = 7;

    // DELETE START WDS Defect#9
//    /**
//     * INPUT_PARAM_GLBL_CMPY_CD = 0
//     */
//    int INPUT_PARAM_GLBL_CMPY_CD = 0;
    // DELETE END WDS Defect#9
    // ADD START WDS Defect#9
    /** INPUT_PARAM_SUFFIX = 0 */
    int INPUT_PARAM_SUFFIX = 0;
    // ADD END WDS Defect#9

    /**
     * INPUT_PARAM_SCR_NM = 1
     */
    int INPUT_PARAM_SCR_NM = 1;

    /**
     * INPUT_PARAM_TBL_NM = 2
     */
    int INPUT_PARAM_TBL_NM = 2;

    /**
     * INPUT_PARAM_CRITERIA = 3
     */
    int INPUT_PARAM_CRITERIA = 3;

    /**
     * INPUT_PARAM_COLUMN = 4
     */
    int INPUT_PARAM_COLUMN = 4;

    /**
     * INPUT_PARAM_SORT = 5
     */
    int INPUT_PARAM_SORT = 5;

    /**
     * INPUT_PARAM_RESULT = 6
     */
    int INPUT_PARAM_RESULT = 6;

    /**
     * COLUMN_0 = 0
     */
    int COLUMN_0 = 0;

    /**
     * COLUMN_1 = 1
     */
    int COLUMN_1 = 1;

    /**
     * COLUMN_2 = 2
     */
    int COLUMN_2 = 2;

    /**
     * COLUMN_3 = 3
     */
    int COLUMN_3 = 3;

    /**
     * COLUMN_4 = 4
     */
    int COLUMN_4 = 4;

    /**
     * COLUMN_5 = 5
     */
    int COLUMN_5 = 5;

    /**
     * COLUMN_6 = 6
     */
    int COLUMN_6 = 6;

    /**
     * COLUMN_7 = 7
     */
    int COLUMN_7 = 7;

    /**
     * COLUMN_8 = 8
     */
    int COLUMN_8 = 8;

    /**
     * COLUMN_9 = 9
     */
    int COLUMN_9 = 9;
}
