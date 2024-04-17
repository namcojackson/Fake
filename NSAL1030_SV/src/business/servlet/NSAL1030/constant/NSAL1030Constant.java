/* <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSAL1030.constant;

/**
 *<pre>
 * Contract Invoice Detail Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/29   Hitachi         A.Kohinata      Create          N/A
 * 05/11/2016   Hitachi         T.Aoyagi        Update          QC#7734
 *</pre>
 */
public class NSAL1030Constant {

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
    public static final String[] BTN_CMN_BLANK7 = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL1030";

    /** Screen ID */
    public static final String SCREEN_ID = "NSAL1030Scrn00";

    /** Function code : Search */
    public static final String FUNC_SEARCH = "20";

    /** Function code : Update */
    public static final String FUNC_UPDATE = "40";

    /** Reference authority */
    public static final String AUTH_REFERENCE = "NSAL1030T010";

    /** Update authority */
    public static final String AUTH_UPDATE = "NSAL1030T020";

    /** Your request cannot be processed under this status. */
    public static final String NSAM0065E = "NSAM0065E";

    /** It is necessary to search this invoice from Detail Screen for linking it to CI. */
    public static final String NSAM0422E = "NSAM0422E";

    // START 05/11/2016 T.Aoyagi [QC#7734, ADD]
    /** Selected invoice cannot be processed because it has been rebilled. */
    public static final String NSAM0471E = "NSAM0471E";
    // END 05/11/2016 T.Aoyagi [QC#7734, ADD]

    /** NSAL1030_PRM_LENGTH : 7 */
    public static final int NSAL1030_PRM_LENGTH = 7;

    /** NSAL1120_PRM_LENGTH : 5 */
    public static final int NSAL1120_PRM_LENGTH = 5;

    /** NSAL1120_MODE : BASE_CHARGE */
    public static final String NSAL1120_MODE_BASE_CHARGE = "1";

    /** NSAL1120_MODE : METER_CHARGE */
    public static final String NSAL1120_MODE_METER_CHARGE = "2";
}
