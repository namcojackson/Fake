/* <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSBL0410.constant;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Hitachi         M.Gotou         Create          N/A
 * 2016/04/15   Hitachi         M.Gotou         Update          QC#3425
 * 2018/05/31   Hitachi         U.kim           Update          QC#22393
 *</pre>
 */
public class NSBL0410Constant {

    /** ScreenID : NSBL0410Scrn00 */
    public static final String SCREEN_ID = "NSBL0410Scrn00";

    /** BizID : NSBL0410 */
    public static final String BIZ_ID = "NSBL0410";

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
     * Button name attribute [Serial# Assign]
     */
    public static final String BTN_SRL_ASSN = "MoveWin_SerialAssinment";

    /**
     * Button name attribute [Machine Status]
     */
    public static final String BTN_MCN_STS = "MoveWin_MachineStatus";

    /**
     * Button name attribute [Edit Description]
     */
    public static final String BTN_EDT_DEP = "EditDescription";

    // add start 2016/04/15 CSA Defect#3425
    /**
     * Button name attribute [Filter]
     */
    public static final String BTN_FILTER = "Filter";
    // add end 2016/04/15 CSA Defect#3425

    /**
     * Button name attribute [Apply]
     */
    public static final String BTN_APPLY = "Apply";

    /**
     * Button name attribute [Add]
     */
    public static final String BTN_ADD = "Add";

    /**
     * Button name attribute [SelectAll]
     */
    public static final String BTN_SLCT_ALL = "SelectAll";

    /**
     * Button name attribute [UnselectAll]
     */
    public static final String BTN_UNSLCT_ALL = "UnselectAll";

    /**
     * Button name attribute [Delete]
     */
    public static final String BTN_DEL = "Delete";

    /**
     * Button name attribute [Detail P]
     */
    public static final String BTN_P = "MoveWin_PartsEntry";

    /** FUNCTION */

    /** Function Id: READ */
    public static final String FUNC_ID_INQ = BIZ_ID + "T010";

    /** Function Id: EDIT */
    public static final String FUNC_ID_EDT = BIZ_ID + "T020";

    /** Function Id: Description EDIT */
    public static final String FUNC_ID_DSPEDT = BIZ_ID + "T030";

    /**
     * Function Code: Inquiry
     */
    public static final String EZD_FUNC_CD_INQ = "20";

    /**
     * Function Code: Update
     */
    public static final String EZD_FUNC_CD_UPD = "40";

    /** XX_MODE_CD: reference mode */
    public static final String REF_MODE = "02";

    /** XX_MODE_CD: edit mode */
    public static final String EDIT_MODE = "01";

    /**
     * "@" is invalid.
     */
    public static final String NSAM0040E = "NSAM0040E";

    /** length 7 */
    public static final int LENGTH_7 = 7;

    // START 2018/05/31 U.Kim [QC#22393, ADD]
    /**
     * Please fill out the search criteria for 1 field or more.
     */
    public static final String NSBM0001E = "NSBM0001E";
    // END 2018/05/31 U.Kim [QC#22393, ADD]

}
