/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0830.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Hitachi         Y.Takeno        Create          N/A
 * 2016/05/20   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/07/22   Hitachi         M.Gotou         Update          QC#11854
 *</pre>
 */
public class NSAL0830Constant {

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
    public static final String BUSINESS_ID = "NSAL0830";

    /** Reference authority */
    public static final String AUTH_REFERENCE = "NSAL0830T010";

    /** Update authority */
    public static final String AUTH_UPDATE = "NSAL0830T020";

    /** SCRN_ID : NSBL0450Scrn00 */
    public static final String SCRN_ID = "NSAL0830Scrn00";

    /** Function code (Search) */
    public static final String FUNCTION_SEARCH = "20";

    /** Function code (Submit) */
    public static final String FUNCTION_SUBMIT = "40";

    /** mode (After Search) */
    public static final String MODE_AFTER_SEARCH = "01";

    /** mode (After Open For Correction) */
    public static final String MODE_AFTER_OPEN = "02";

    /** parameter length (NMAL6800) */
    public static final int PARAM_LENGTH_NMAL6800 = 7;

    /** parameter length (NSAL1240) */
    public static final int PARAM_LENGTH_NSAL1240 = 31;

    /** parameter index : 30 */
    public static final int PARAM_INDEX_30 = 30;

    /** SER_NUM Length : 30 */
    public static final int SER_NUM_LENGTH = 30;

    /** MDSE_CD Length : 16 */
    public static final int MDSE_CD_LENGTH = 16;

    /** MDSE_NM Length : 30 */
    public static final int MDSE_NM_LENGTH = 30;

    /** SVC_MACH_MSTR_STS_CD Length : 5 */
    public static final int SVC_MACH_MSTR_STS_CD_LENGTH = 5;

    /** SVC_CONFIG_MSTR_PK Length : 28 */
    public static final int SVC_CONFIG_MSTR_PK_LENGTH = 28;

    /** SVC_MACH_MSTR_PK Length : 28 */
    public static final int SVC_MACH_MSTR_PK_LENGTH = 28;

    /** T_MDL_NM Length : 50 */
    public static final int T_MDL_NM_LENGTH = 50;

    /** CUR_LOC_NUM Length : 30 */
    public static final int CUR_LOC_NUM_LENGTH = 30;

    /** "@" is not entered. */
    public static final String NSAM0007E = "NSAM0007E";

    /** Please specify "@" for the file extension. */
    public static final String NSAM0404E = "NSAM0404E";

    // START 2016/07/22 [QC#11854, ADD]
    /** Got an error while validation data. Please correct it and try again. */
    public static final String NSAM0541E = "NSAM0541E";
    // END   2016/07/22 [QC#10854, ADD]

    // START 2016/05/20 [QC#4061, MOD]
    /** file extension */
    public static final String[] FILE_EXTENSION = {"csv", "txt", "xls", "xlsx"};
    // END   2016/05/20 [QC#4061, MOD]

}
