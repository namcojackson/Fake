/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0860.constant;

/**
 *<pre>
 * Register & Deregister Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/06   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public class NSAL0860Constant {

    /** Business ID */
    public static final String BIZ_ID = "NSAL0860";

    /** SCREEN_ID */
    public static final String SCREEN_ID = "NSAL0860Scrn00";

    /** Function Code 20 */
    public static final String FUNC_CD_SEARCH = "20";

    /** Function Code 40 */
    public static final String FUNC_CD_UPDATE = "40";

    /**
     * Function Id: Inquiry
     */
    public static final String FUNC_ID_INQ = "NSAL0860T010";

    /**
     * Function Id: Update/Data Management Super User
     */
    public static final String FUNC_ID_UPD = "NSAL0860T020";

    /** The number of popup parameter */
    public static final int POP_PARAM_NUM = 7;

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
    public static final String[] BTN_CMN_DOWNROAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_BLANK7 = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * Button name attribute [Select All]
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Button name attribute [Select All]
     */
    public static final String BTN_SELECT_ALL = "SelectAll";

    /**
     * Button name attribute [UnSelect All]
     */
    public static final String BTN_UNSELECT_ALL = "UnselectAll";

    /**
     * Button name attribute [Register]
     */
    public static final String BTN_REGISTER = "Register";

    /**
     * Button name attribute [De-Register]
     */
    public static final String BTN_DEREGISTER = "Deregister";

    /**
     * Button name attribute [Upload]
     */
    public static final String BTN_UPLOAD = "Upload";

    /**
     * Button name attribute [xxFileData_U]
     */
    public static final String BTN_FILEDATE = "xxFileData_U";

    /** XX_CHK_BOX */
    public static final String XXCHKBOX = "xxChkBox";

    /** file extension */
    public static final String[] FILE_EXTENSION = {"txt", "csv", "xlsx", "xls" };

    /** Please specify "@" for the file extension. */
    public static final String NSAM0404E = "NSAM0404E";

    /** "@" is not entered. */
    public static final String NSAM0007E = "NSAM0007E";
}
