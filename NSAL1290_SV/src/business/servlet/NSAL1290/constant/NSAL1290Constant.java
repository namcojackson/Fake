/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL1290.constant;

/**
 *<pre>
 * Billing Counter Mapping Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/01   Hitachi         M.Gotou         Create          N/A
 *</pre>
 */
public class NSAL1290Constant {
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

    /** Screen Name */
    public static final String SCREEN_NM = "Billing Counter Mapping Setup";

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL1290";

    /** Reference authority */
    public static final String AUTH_REFERENCE = "NSAL1290T010";

    /** Update authority */
    public static final String AUTH_UPDATE = "NSAL1290T020";

    /** SCRN_ID : NSAL1290Scrn00 */
    public static final String SCRN_ID = "NSAL1290Scrn00";

    /** Function code (Search) */
    public static final String FUNCTION_SEARCH = "20";

    /** Function code (Submit) */
    public static final String FUNCTION_SUBMIT = "40";

    /** Window title : NWAL1130(Regular) */
    public static final String WINDOW_TITLE_NWAL1130_REG = "Regular Counter Search";

    /** Window title : NWAL1130(Billing) */
    public static final String WINDOW_TITLE_NWAL1130_BLLG = "Billing Counter Search";

    /** Parameter length : 7 */
    public static final int PRM_LENGTH_NWAL1130 = 7;

    /** Display Name : MTR_LB_NM(Regular) */
    public static final String NWAL1130_DISP_NM_REG_CNT = "Regular Counter";

    /** Display Name : MTR_LB_NM(Billing) */
    public static final String NWAL1130_DISP_NM_BLLG_CNT = "Billing Counter";

    /** Display Name : MTR_LB_DESC_TXT */
    public static final String NWAL1130_DISP_NM_MTR_LB_DESC_TXT = "Description";

    /** SQL Name : MTR_LB_CD */
    public static final String NWAL1130_SQL_NM_MTR_LB_CD = "MTR_LB_CD";

    /** SQL Name : MTR_LB_NM */
    public static final String NWAL1130_SQL_NM_MTR_LB_NM = "MTR_LB_NM";

    /** SQL Name : MTR_LB_DESC_TXT */
    public static final String NWAL1130_SQL_NM_MTR_LB_DESC_TXT = "MTR_LB_DESC_TXT";

    /** SQL Name : MTR_LB_TP_CD */
    public static final String NWAL1130_SQL_NM_MTR_LB_TP_CD = "MTR_LB_TP_CD";

    /** SQL Name : MTR_IDX_CD */
    public static final String NWAL1130_SQL_NM_MTR_IDX_CD = "MTR_IDX_CD";

    /** Width : MTR_LB_NM */
    public static final int NWAL1130_WIDTH_MTR_LB_NM = 32;

    /** Width : MTR_LB_DESC_TXT */
    public static final int NWAL1130_WIDTH_MTR_LB_DESC_TXT = 52;

    /** Event Name : CMN_Close */
    public static final String NWAL1130_EVENT_NM_CLOSE = "CMN_Close";

    /** Button Name : Search */
    public static final String BTN_NM_SEARCH = "Search";

    /** Button Name : AddLine */
    public static final String BTN_NM_ADD_LINE = "AddLine";

    /** Button Name : DeleteLine */
    public static final String BTN_NM_DELETE_LINE = "DeleteLine";

    /** Button Name : OpenWin_RegularCounter */
    public static final String BTN_NM_OPEN_WIN_REG_COUNTER = "OpenWin_RegularCounter";

    /** Button Name : OpenWin_BillingCounter */
    public static final String BTN_NM_OPEN_WIN_BLLG_COUNTER = "OpenWin_BillingCounter";

    /** Length : MTR_LB_CD LENGTH */
    public static final int MTR_LB_CD_LENGTH = 2;

    /** Length : MTR_LB_TP_CD LENGTH */
    public static final int MTR_LB_TP_CD_LENGTH = 2;

    /** Length : MTR_LDX_CD LENGTH */
    public static final int MTR_IDX_CD_LENGTH = 2;
}
