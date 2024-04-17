/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0550.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/22   Hitachi         T.Kanasaka      Update          QC#5562
 * 2016/11/21   Hitachi         T.Mizuki        Update          QC#16116
 * 2018/06/11   Fujitsu         M.Ohno          Update          QC#22381
 *</pre>
 */
public class NSAL0550Constant {

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
    public static final String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0550";

    /** Screen ID */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /** Function Code : Search */
    public static final String FUCNTION_CD_SEARCH = "20";

    /** Function Code : Update */
    public static final String FUCNTION_CD_UPDATE = "40";

    /** Function Code : Print */
    public static final String FUCNTION_CD_PRINT = "90";

    /** message id : ZZXM0001E */
    public static final String ZZXM0001E = "ZZXM0001E";

    /** abnormal end message : failed get report. */
    public static final String ABEND_MSG_FAILED_GET_REPORT = "get report bytes failure";

    /** parameter size NFCL5050 */
    public static final int PARAM_SIZE_NFCL5050 = 13;

    // START 2016/03/22 T.Kanasaka [QC#5562, ADD]
    /** Please select item(s). */
    public static final String NSAM0034E = "NSAM0034E";

    /** The corresponding [@] does not exist. */
    public static final String NSAM0207E = "NSAM0207E";
    // END 2016/03/22 T.Kanasaka [QC#5562, ADD]

    // START 2018/06/11 M.Ohno [QC#22381, ADD]
    /** Please set at least one search criteria. */
    public static final String NSAM0017E = "NSAM0017E";

    /** [@] is mandatory because [@] is checked. */
    public static final String NSAM0736E = "NSAM0736E";
    // END 2018/06/11 M.Ohno [QC#22381, ADD]

    // mod start 2016/11/21 CSA QC#16116
    /** Contract Manager */
    public static final String AUTH_CONTR_MGR = "NSAL0550T020";
    // mod end 2016/11/21 CSA QC#16116
}
