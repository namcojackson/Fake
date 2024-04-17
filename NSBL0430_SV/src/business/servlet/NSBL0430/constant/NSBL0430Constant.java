/* <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSBL0430.constant;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/28    Hitachi         O.Okuma         Create          N/A
 * 2016/04/18    Hitachi         M.Gotou         Update          QC#3425
 * 2017/02/15    Hitachi         N.Arai          Update          QC#17562
 *</pre>
 */
public class NSBL0430Constant {

    /** ScreenID : NSBL0430Scrn00 */
    public static final String SCREEN_ID = "NSBL0430Scrn00";

    /** BizID : NSBL0430 */
    public static final String BIZ_ID = "NSBL0430";

    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * ｓ Common button 2
     */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * Button name attribute [Mod Plan Search]
     */
    public static final String BTN_MDF_SRCH = "MoveWin_Search";

    /**
     * Button name attribute [Plan Detail]
     */
    public static final String BTN_MDF_DTL = "MoveWin_Detail";

    /**
     * Button name attribute [Machine Status]
     */
    public static final String BTN_MCN_STTS = "MoveWin_MachineStatus";

    // add start 2016/04/18 CSA Defect#3425
    /**
     * Button name attribute [Filter]
     */
    public static final String BTN_FILTER = "Filter";
    // add end 2016/04/18 CSA Defect#3425

    /**
     * Button name attribute [Add]
     */
    public static final String BTN_ADD = "Add";

    /**
     * Button name attribute [Delete]
     */
    public static final String BTN_DELETE = "Delete";

    /**
     * Button name attribute [Upload]
     */
    public static final String BTN_UPLOAD = "Upload";

    /** Function Id: READ */
    public static final String FUNC_ID_INQ = BIZ_ID + "T010";

    /** Function Id: EDIT */
    public static final String FUNC_ID_EDT = BIZ_ID + "T020";

    /**
     * An input parameter, [@], has not been set.
     */
    public static final String NZZM0012E = "NZZM0012E";

    /** Please specify "@" for the file extension. */
    public static final String NSAM0404E = "NSAM0404E";

    /** "@" is not entered. */
    public static final String NSAM0007E = "NSAM0007E";

    /** FUNCTION */
    /**
     * Function Code: Inquiry
     */
    public static final String EZD_FUNC_CD_INQ = "20";

    /**
     * Function Code: Update
     */
    public static final String EZD_FUNC_CD_UPD = "40";

    /** length 7 */
    public static final int LENGTH_7 = 7;

    /** file extension */
    public static final String FILE_EXTENSION = "csv";

 // START 2017/02/15 N.Arai [QC#17562, MOD]
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
 // END 2017/02/15 N.Arai [QC#17562, MOD]
}
