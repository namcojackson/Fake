/*
 * <Pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL1410.constant;

/**
 *<pre>
 * Contract Branch Rep Update
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/06/07   Hitachi         A.Kohinata      Create          QC#60080
 *</pre>
 */
public final class NSAL1410Constant {

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
    public static final String BUSINESS_ID = "NSAL1410";

    /** Reference authority */
    public static final String AUTH_REFERENCE = "NSAL1410T010";

    /** Update authority */
    public static final String AUTH_UPDATE = "NSAL1410T020";

    /** SCRN_ID */
    public static final String SCREEN_ID = "NSAL1410Scrn00";

    /** Function code (Search) */
    public static final String FUNCTION_SEARCH = "20";

    /** Function code (Submit) */
    public static final String FUNCTION_SUBMIT = "40";

    /** mode (After Submit) */
    public static final String MODE_AFTER_SUBMIT = "01";

    /** NMAL6760_PRM_LENGTH */
    public static final int NMAL6760_PRM_LENGTH = 24;

    /** NSAL0420_PRM_LENGTH */
    public static final int NSAL0420_PRM_LENGTH = 9;

    /** NWAL1130_PRM_LENGTH */
    public static final int NWAL1130_PRM_LENGTH = 7;

    /** CONTR_BR_FIRST_ORG_CD */
    public static final String CONTR_BR_FIRST_ORG_CD = "CONTR_BR_FIRST_ORG_CD";

    /** file extension */
    public static final String[] FILE_EXTENSION = {"csv", "txt", "xls", "xlsx" };

    /** "@" is not entered. */
    public static final String NSAM0007E = "NSAM0007E";

    /** Please specify "@" for the file extension. */
    public static final String NSAM0404E = "NSAM0404E";

    /** Please set at least one search criteria. */
    public static final String NSAM0017E = "NSAM0017E";

    /** There is no data to be processed. */
    public static final String NSAM0456E = "NSAM0456E";
}
