/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0440.constant;

/**
 *<pre>
 * Terms & Conditions
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Hitachi         T.Iwamoto         Create          N/A
 * 2016/02/17   Hitachi         K.Kasai           Update          QC#2815
 * 2018/07/31   Hitachi         A.Kohinata        Update          QC#26659
 * 2022/08/03   Hitachi         N.Takatsu         Update          QC#60077
 *</pre>
 */
public final class NSAL0440Constant {

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
    public static final String[] BTN_CMN_BLANK6 = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_BLANK7 = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0440";

    /** Reference authority */
    public static final String AUTH_REFERENCE = "NSAL0440T010";

    /** Update authority */
    public static final String AUTH_UPDATE = "NSAL0440T020";

    /** Function code (Search) */
    public static final String FUNCTION_SEARCH = "20";

    /** Function code (Submit) */
    public static final String FUNCTION_SUBMIT = "40";

    /** Error Message: @ is found. */
    public static final String NSAM0353E = "NSAM0353E";

    /** Error Parameter */
    public static final String ERR_PRAM_NO_INPUT = "No input parameter";

    /** MODE UPDATE */
    public static final String MODE_UPDATE = "1";

    // add start 2016/02/17 CSA Defect#2815
    /** parameter length (NSAL1240) */
    public static final int PARAM_LENGTH_NSAL1240 = 31;

    /** parameter index : 30 */
    public static final int PARAM_INDEX_30 = 30;
    // add start 2016/02/17 CSA Defect#2815

    // START 2022/08/03 N.Takatsu [QC#60077, ADD]
    /** List Icon Open */
    public static final String IMG_OPEN_ALL = "./img/wfcomp/S21WfPlus.gif";

    /** List Icon Close */
    public static final String IMG_CLOSE_ALL = "./img/wfcomp/S21WfMinus.gif";
    // END 2022/08/03 N.Takatsu [QC#60077, ADD]

    /**
     * Button name attribute [Search]
     */
    public static final String BTN_SEARCH = "Search";
    // add end 2016/02/17 CSA Defect#2815

    // add start 2018/07/31 QC#26659
    /** Message ID : ZZM9004E */
    public static final String ZZM9004E = "ZZM9004E";
    // add end 2018/07/31 QC#26659

}
