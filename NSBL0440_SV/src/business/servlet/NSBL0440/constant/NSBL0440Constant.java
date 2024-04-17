/* <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSBL0440.constant;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22    Hitachi         O.Okuma         Create          N/A
 * 2016/04/18    Hitachi         M.Gotou         Update          QC#3425
 *</pre>
 */
public class NSBL0440Constant {

    /** ScreenID : NSBL0440Scrn00 */
    public static final String SCREEN_ID = "NSBL0440Scrn00";

    /** BizID : NSBL0440 */
    public static final String BIZ_ID = "NSBL0440";

    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
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
    public static final String BTN_MDF_SRCH = "MoveWin_ModPlanSearch";

    /**
     * Button name attribute [Plan Detail]
     */
    public static final String BTN_MDF_DTL = "MoveWin_PlanDetail";

    /**
     * Button name attribute [Serial# Assign]
     */
    public static final String BTN_SRL_ASSN = "MoveWin_SerialAssinment";

    // add start 2016/04/18 CSA Defect#3425
    /**
     * Button name attribute [Filter]
     */
    public static final String BTN_FILTER = "Filter";
    // add end 2016/04/18 CSA Defect#3425

    /** Function Id: READ */
    public static final String FUNC_ID_INQ = BIZ_ID + "T010";

    /** Function Id: EDIT */
    public static final String FUNC_ID_EDT = BIZ_ID + "T020";

    /**
     * An input parameter, [@], has not been set.
     */
    public static final String NZZM0012E = "NZZM0012E";

    /** FUNCTION */
    /**
     * Function Code: Inquiry
     */
    public static final String EZD_FUNC_CD_INQ = "20";

    /**
     * Function Code: Update
     */
    public static final String EZD_FUNC_CD_UPD = "40";
}
