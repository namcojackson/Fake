/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0260.constant;

/**
 *<pre>
 * EOL Exception Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public final class NSBL0260Constant {

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
    public static final String BUSINESS_ID = "NSBL0260";

    /** Reference authority */
    public static final String AUTH_REFERENCE = "NSBL0260T010";

    /** Update authority */
    public static final String AUTH_UPDATE = "NSBL0260T020";

    /** Screen ID */
    public static final String SCREEN_ID = "NSBL0260Scrn00";

    /** Function code (Search) */
    public static final String FUNCTION_SEARCH = "20";

    /** Function code (Submit) */
    public static final String FUNCTION_SUBMIT = "40";

    /** Invalid Search Multiple Criteria. */
    public static final String NSBM0153E = "NSBM0153E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** No New Contracts After Date must be less than Stop Service Date. */
    public static final String NSBM0155E = "NSBM0155E";

    /** PRMS_00 */
    public static final int PRMS_00 = 0;

    /** PRMS_01 */
    public static final int PRMS_01 = 1;

    /** PRMS_02 */
    public static final int PRMS_02 = 2;

    /** PRMS_03 */
    public static final int PRMS_03 = 3;

    /** PRMS_04 */
    public static final int PRMS_04 = 4;

    /** PRMS_05 */
    public static final int PRMS_05 = 5;

    /** PRMS_06 */
    public static final int PRMS_06 = 6;

    /** PRMS_07 */
    public static final int PRMS_07 = 7;

    /** PRMS_08 */
    public static final int PRMS_08 = 8;

    /** PRMS_09 */
    public static final int PRMS_09 = 9;

    /** PRMS_10 */
    public static final int PRMS_10 = 10;

    /** PRMS_11 */
    public static final int PRMS_11 = 11;

    /** PRMS_12 */
    public static final int PRMS_12 = 12;

    /** PRMS_26 */
    public static final int PRMS_26 = 26;

    /** PRMS_29 */
    public static final int PRMS_29 = 29;

    /** PRMS_30 */
    public static final int PRMS_30 = 30;

    /** parameter length (NSAL1240) */
    public static final int PARAM_LENGTH_NSAL1240 = 31;

    /** parameter length (NMAL6760) */
    public static final int PARAM_LENGTH_NMAL6760 = 24;

    /** NMAL6760 Bill To Only Code */
    public static final String BILL_TO_ONLY = "02";

    /** select popup : Model Name Combination */
    public static final String SELECT_POPUP_MDL_HEADER_COMB = "MHC";

    /** select popup : Serial Number Combination */
    public static final String SELECT_POPUP_SER_HEADER_COMB = "SHC";

    /** select popup : Model Name */
    public static final String SELECT_POPUP_MDL_HEADER = "MH";

    /** select popup : Customer# */
    public static final String SELECT_POPUP_CUST_HEADER = "CH";

    /** select popup : Model Name(Line) */
    public static final String SELECT_POPUP_MDL_LINE = "ML";

    /** select popup : Serial Number(Line)  */
    public static final String SELECT_POPUP_SER_LINE = "SL";

}
