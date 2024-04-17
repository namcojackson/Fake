/* <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSAL1120.constant;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03    Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NSAL1120Constant {

    /** ScreenID : NSAL1120Scrn00 */
    public static final String SCREEN_ID = "NSAL1120Scrn00";

    /** BizID : NSAL1120 */
    public static final String BIZ_ID = "NSAL1120";

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

    /** Function Id: READ */
    public static final String FUNC_ID_INQ = BIZ_ID + "T010";

    /** Function Id: EDIT */
    public static final String FUNC_ID_EDT = BIZ_ID + "T020";

    /** FUNCTION */
    /**
     * Function Code: Inquiry
     */
    public static final String EZD_FUNC_CD_INQ = "20";

    /**
     * Function Code: Update
     */
    public static final String EZD_FUNC_CD_UPD = "40";

    /**
     * Mode Code: Base Charge
     */
    public static final String MODE_CODE_BASE = "1";

    /**
     * Mode Code: Usage Charge
     */
    public static final String MODE_CODE_USAGE = "2";

    /**
     * TABLE_A (Base Price Details)
     */
    public static final String TABLE_A = "A";

    /**
     * TABLE_B (Meter Details)
     */
    public static final String TABLE_B = "B";

    /**
     * TABLE_C (Pricing Details)
     */
    public static final String TABLE_C = "C";

    /** parameter length (NSAL1130) */
    public static final int PARAM_LENGTH_NSAL1130 = 2;

    /** FracDigit 2 */
    public static final int FRAC_DIGIT_TWO = 2;

    /** FracDigit 6 */
    public static final int FRAC_DIGIT_SIX = 6;

    /** Review Button */
    public static final String BTN_REVIEW = "Review";
}
