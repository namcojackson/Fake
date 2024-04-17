/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0310.constant;

/**
 * <pre>
 * Service Request By Manager
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Harada        Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          QC#5735
 * 2019/03/28   Hitachi         K.Kitamura      Update          CSA QC#30906
 *</pre>
 */
public class NSBL0310Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NSBL0310";

    /** SCREEN_ID */
    public static final String SCREEN_ID = "NSBL0310Scrn00";

    /** Function Code 20 */
    public static final String FUNC_CD_20 = "20";

    /** The number of popup parameter */
    public static final int POP_PARAM_NUM = 7;

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    /** Function Button 1 */
    public static final String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Function Button 2 */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    public static final String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Function Button 4 */
    public static final String[] BTN_CMN_BLANK4 = {"btn4", "CMN_Approve", "Approve" };

    /** Function Button 5 */
    public static final String[] BTN_CMN_BLANK5 = {"btn5", "CMN_Reject", "Reject" };

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

    /** Search Button */
    public static final String SEARCH_BTN = "Search";

    /** Search ShipTo Button */
    public static final String SHOW_BRANCH_BTN = "Show_Branch";

    /** NSBL0320_PRM_LENGTH */
    public static final int NSBL0320_PRM_LENGTH = 5;

    /** The process has been successfully completed. */
    public static final String NSBM0005I = "NSBM0005I";

    /** column name (In) */
    public static final String IN_PROC_COL_NM = "inprocTaskCnt";

    /** column name (Close) */
    public static final String CLOSE_COL_NM = "cloTaskCnt";

    /** column name (Cancel) */
    public static final String CANCEL_COL_NM = "cancTaskCnt";

    /** column name (Auto Reject) */
    public static final String REJECT_COL_NM = "rejTaskCnt";

    /** column name (Avr Resp Time) */
    public static final String RESP_COL_NM = "xxRcvTm";

    // START 2019/03/28 K.Kitamura [QC#30233, ADD]
    /** column name (TBO) */
    public static final String TBO_COL_NM = "toBeOptmTaskCnt";

    /** column name (Scheduled) */
    public static final String SCHD_COL_NM = "schdTaskCnt";
    // END 2019/03/28 K.Kitamura [QC#30233, ADD]

    /** column name (Assign) */
    public static final String ASSIGN_COL_NM = "asgTaskCnt";

    /** column name (Open) */
    public static final String OPEN_COL_NM = "openTaskCnt";

    /** column name (On Hold) */
    public static final String HOLD_COL_NM = "hldTaskCnt";

    /** column name (Escalation) */
    public static final String ESCL_COL_NM = "esclTaskCnt";

    /** column name (VIP) */
    public static final String VIP_COL_NM = "vipTaskCnt";

    /** column name (Error) */
    public static final String ERR_COL_NM = "attnTaskCnt";

    // START 2016/03/22 K.Yamada [QC#5735, ADD]
    /** Tab Name : Slct Smry */
    public static final String TAB_NAME_NSBL0320 = "Slct Smry";
    // END 2016/03/22 K.Yamada [QC#5735, ADD]
}
