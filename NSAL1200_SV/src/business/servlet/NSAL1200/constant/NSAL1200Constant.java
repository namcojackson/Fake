/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL1200.constant;

/**
 *<pre>
 * Counter Group Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Hitachi         Y.Takeno        Create          N/A
 * 2016/06/08   Hitachi         M.Gotou         Update          QC#8604
 * 2017/08/03   Hitachi         T.Kanasaka      Update          QC#18193
 *</pre>
 */
public class NSAL1200Constant {
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
    public static final String SCREEN_NM = "Counter Group Setup";

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL1200";

    /** Reference authority */
    public static final String AUTH_REFERENCE = "NSAL1200T010";

    /** Update authority */
    public static final String AUTH_UPDATE = "NSAL1200T020";

    /** SCRN_ID : NSBL0450Scrn00 */
    public static final String SCRN_ID = "NSAL1200Scrn00";

    /** Function code (Search) */
    public static final String FUNCTION_SEARCH = "20";

    /** Function code (Submit) */
    public static final String FUNCTION_SUBMIT = "40";

    /** Window title : NWAL1130*/
    public static final String WINDOW_TITLE_NWAL1130 = "Counter Group Search";

    /** Parameter length : 3 */
    public static final int PRM_LENGTH_NSAL0480 = 3;

    /** Parameter index : 0 */
    public static final int PRM_INDEX_NSAL0480_MDL_NM = 0;

    /** Parameter index : 1 */
    public static final int PRM_INDEX_NSAL0480_ITEM_CD = 1;

    /** Parameter index : 2 */
    public static final int PRM_INDEX_NSAL0480_MTR_GRP_PK = 2;

    /** Parameter length : 7 */
    public static final int PRM_LENGTH_NWAL1130 = 7;

    /** Display Name : MTR_GRP_NM */
    public static final String NWAL1130_DISP_NM_MTR_GRP_NM = "Counter Group Name";

    /** Display Name : MTR_GRP_DESC_TXT */
    public static final String NWAL1130_DISP_NM_MTR_GRP_DESC_TXT = "Description";

    /** SQL Name : MTR_GRP_PK */
    public static final String NWAL1130_SQL_NM_MTR_GRP_PK = "MTR_GRP_PK";

    /** SQL Name : MTR_GRP_NM */
    public static final String NWAL1130_SQL_NM_MTR_GRP_NM = "MTR_GRP_NM";

    /** SQL Name : MTR_GRP_DESC_TXT */
    public static final String NWAL1130_SQL_NM_MTR_GRP_DESC_TXT = "MTR_GRP_DESC_TXT";

    /** SQL Name : MTR_GRP_TP_CD */
    public static final String NWAL1130_SQL_NM_MTR_GRP_TP_CD = "MTR_GRP_TP_CD";

    /** Width : MTR_GRP_NM */
    public static final int NWAL1130_WIDTH_MTR_GRP_NM = 30;

    /** Width : MTR_GRP_DESC_TXT */
    public static final int NWAL1130_WIDTH_MTR_GRP_DESC_TXT = 50;

    /** Event Name : CMN_Close */
    public static final String NWAL1130_EVENT_NM_CLOSE = "CMN_Close";

    /** Table Name : BLLG_MTR_MAP */
    public static final String NSAL9900_TBL_NM = "BLLG_MTR_MAP";

    /** Tab Name : Mst Maint */
    public static final String NSAL9900_TAB_NM = "Mst Maint";

// add start 2016/06/08 CSA Defect#8604
    /** Tab Name : Bill Cnt Map */
    public static final String NSAL1290_TAB_NM = "Bill Cnt Map";
// add end 2016/06/08 CSA Defect#8604

    /** Button Name : OpenWin_NSAL0480 */
    public static final String BTN_NM_OPEN_WIN_NSAL0480 = "OpenWin_NSAL0480";

    /** Button Name : AddLine */
    public static final String BTN_NM_ADD_LINE = "AddLine";

    /** Button Name : DeleteLine */
    public static final String BTN_NM_DELETE_LINE = "DeleteLine";

    /** Button Name : AddCounterGroup */
    public static final String BTN_NM_ADD_COUNTER_GROUP = "AddCounterGroup";

    /** Link Name : OpenWin_NWAL1130 */
    public static final String LINK_NM_OPEN_WIN_NWAL1130 = "OpenWin_NWAL1130";

    /** Button Name : OpenWin_Counter */
    public static final String BTN_NM_OPEN_WIN_COUNTER = "OpenWin_Counter";

    // START 2017/08/03 T.Kanasaka [QC#18193,DEL]
//    /** Button Name : OpenWin_DefaultBilling */
//    public static final String BTN_NM_OPEN_WIN_DEFAULT_BILLING = "OpenWin_DefaultBilling";
//
//    /** Button Name : OpenWin_FLTCounter */
//    public static final String BTN_NM_OPEN_WIN_FLT_COUNTER = "OpenWin_FLTCounter";
//
//    /** Button Name : OpenWin_AGGCounter */
//    public static final String BTN_NM_OPEN_WIN_AGG_COUNTER = "OpenWin_AGGCounter";
    // END 2017/08/03 T.Kanasaka [QC#18193,DEL]

    // START 2017/08/03 T.Kanasaka [QC#18193,ADD]
    /** Button Name : OpenWin_LVL1Counter */
    public static final String BTN_NM_OPEN_WIN_LVL1_COUNTER = "OpenWin_LVL1Counter";

    /** Button Name : OpenWin_LVL2Counter */
    public static final String BTN_NM_OPEN_WIN_LVL2_COUNTER = "OpenWin_LVL2Counter";

    /** Button Name : OpenWin_LVL3Counter */
    public static final String BTN_NM_OPEN_WIN_LVL3_COUNTER = "OpenWin_LVL3Counter";
    // END 2017/08/03 T.Kanasaka [QC#18193,ADD]

    /** Length : MTR_LB_CD LENGTH */
    public static final int MTR_LB_CD_LENGTH = 10;

    /** Length : MTR_LB_NM LENGTH */
    public static final int MTR_LB_NM_LENGTH = 30;

    /** Length : MTR_LB_DESC_TXT LENGTH */
    public static final int MTR_LB_DESC_TXT_LENGTH = 42;

    /** Length : MTR_LB_TP_CD LENGTH */
    public static final int MTR_LB_TP_CD_LENGTH = 5;

    /** Length : BLLG_MTR_LVL_NUM LENGTH */
    public static final int BLLG_MTR_LVL_NUM_LENGTH = 5;
}
