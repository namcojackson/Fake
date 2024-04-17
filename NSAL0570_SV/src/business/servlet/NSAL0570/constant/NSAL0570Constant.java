/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0570.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Overage Pricing Effectivity
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Hitachi         K.Kasai         Create          N/A
 * 2016/11/22   Hitachi         T.Mizuki        Update          QC#16116
 *</pre>
 */
public final class NSAL0570Constant {

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
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

    /** Button InsertRow */
    public static final String[] BTN_INSERT_ROW = {"InsertRow", "InsertRow" };

    /** Button DeleteRow */
    public static final String[] BTN_DELETE_ROW = {"DeleteRow", "DeleteRow" };

    /** Button InsertPrcAllowRow */
    public static final String[] BTN_INSERT_PRC_ALLOW_ROW = {"InsertPrcAllowRow", "InsertPrcAllowRow" };

    /** Button DeletePrcAllowRow */
    public static final String[] BTN_DELETE_PRC_ALLOW_ROW = {"DeletePrcAllowRow", "DeletePrcAllowRow" };

    /** Button Schedules */
    public static final String[] BTN_SCHEDULES = {"TopSchedules", "TopSchedules" };

    /** Button SkipMonth */
    public static final String[] BTN_SKIP_MONTH = {"MoveWin_SkipMonths", "MoveWin_SkipMonths" };

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0570";

    /** Screen ID */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /** READ */
    public static final String FUNC_ID_READ = BUSINESS_ID + "T010";

    /** UPDATE */
    public static final String FUNC_ID_UPDATE = BUSINESS_ID + "T020";

    /** XX_MODE_CD: reference mode */
    public static final String REF_MODE = "0";

    /** XX_MODE_CD: edit mode */
    public static final String EDIT_MODE = "1";

    /** INVLD_DS_CONTR_PRC_EFF_SQ_NUM: -1 */
    public static final BigDecimal INVLD_DS_CONTR_PRC_EFF_SQ_NUM = BigDecimal.ONE.negate();

    /** Error Message */
    public static final String NSAM0338W = "NSAM0338W";

    // mod start 2016/11/22 CSA QC#16116
    /** Contract Manager */
    public static final String AUTH_CONTR_MGR = "NSAL0570T020";
    // mod end 2016/11/22 CSA QC#16116
}
