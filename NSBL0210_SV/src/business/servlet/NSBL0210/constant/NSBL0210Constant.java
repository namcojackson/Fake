/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0210.constant;

/**
 * <pre>
 * Labor Charge Table Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSBL0210Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NSBL0210";

    /** Screen ID */
    public static final String SCREEN_ID = "NSBL0210Scrn00";

    /** Function code (Search) */
    public static final String FUNCTION_SEARCH = "20";

    /** Function code (Submit) */
    public static final String FUNCTION_SUBMIT = "40";

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

    /** Button Search */
    public static final String[] BTN_SEARCH = {"Search", "Search" };

    /** Button SelectAll */
    public static final String[] BTN_SELECT_ALL = {"SelectAll", "Select All" };

    /** Button UnselectAll */
    public static final String[] BTN_UNSELECT_ALL = {"UnselectAll", "Unselect All" };

    /** Button Upload */
    public static final String[] BTN_UPLOAD = {"Upload", "Upload" };

    /** Button InsertRow */
    public static final String[] BTN_INSERT_ROW = {"InsertRow", "Insert Row" };

    /** Button DeleteRow */
    public static final String[] BTN_DELETE_ROW = {"DeleteRow", "Delete Row" };

    /** Button OpenWin_ModelGroupDetail */
    public static final String[] BTN_OPEN_WIN_MODEL_GROUP_DETAIL = {"OpenWin_ModelGroupDetail", "M" };

    /** Button ApplyModelGroup */
    public static final String[] BTN_APPLY_MODEL_GROUP = {"ApplyModelGroup", ">>" };

    /** Button OpenWin_ShiftPopup */
    public static final String[] BTN_OPEN_WIN_SHIFT_POPUP = {"OpenWin_ShiftDetail", "M" };

    /** Button ApplyShiftItem */
    public static final String[] BTN_APPLY_SHIFT = {"ApplyShift", ">>" };

    /** Button OpenWin_IntgItemPopup */
    public static final String[] BTN_OPEN_WIN_INTG_ITEM_POPUP = {"OpenWin_IntgItemPopup", "M" };

    /** Button ApplyIntgItem */
    public static final String[] BTN_APPLY_INTG_ITEM = {"ApplyIntgItem", ">>" };

    /** READ */
    public static final String FUNC_ID_READ = BUSINESS_ID + "T010";

    /** UPDATE */
    public static final String FUNC_ID_UPDATE = BUSINESS_ID + "T020";

    /** "@" is not entered. */
    public static final String NSBM0166E = "NSBM0166E";

    /** file extension */
    public static final String[] FILE_EXTENSIONS = {"txt", "csv", "xlsx", "xls" };

    /** Parameter length : NMAL6050 */
    public static final int PRM_LENGTH_NMAL6050 = 11;

    /** Parameter value : PRM_VALUE_NMAL6050_TBL_NM */
    public static final String PRM_VALUE_NMAL6050_TBL_NM = "DS_MDL_GRP";

    /** Parameter value : PRM_VALUE_NMAL6050_CD_COL_NM */
    public static final String PRM_VALUE_NMAL6050_CD_COL_NM = "MDL_GRP_NM";

    /** Parameter value : PRM_VALUE_NMAL6050_NM_COL_NM */
    public static final String PRM_VALUE_NMAL6050_NM_COL_NM = "MDL_GRP_DESC_TXT";

    /** Parameter value : PRM_VALUE_NMAL6050_SCR_NM */
    public static final String PRM_VALUE_NMAL6050_SCR_NM = "Model Group PopUp";

    /** Parameter value : PRM_VALUE_NMAL6050_CD_LBL_NM */
    public static final String PRM_VALUE_NMAL6050_CD_LBL_NM = "Model Group Name";

    /** Parameter value : PRM_VALUE_NMAL6050_NM_LBL_NM */
    public static final String PRM_VALUE_NMAL6050_NM_LBL_NM = "Model Group Description";

    /** Parameter value : Event value : NMAL6050 */
    public static final String NMAL6050_EVENT_NM_CLOSE = "CMN_Close";

    /** Event value : LINK_NM_OPEN_WIN_MDL_GRP_HDR */
    public static final String LINK_NM_OPEN_WIN_MDL_GRP_HDR = "OpenWin_ModelGroupHeader";

    /** Event value : BTN_NM_OPEN_WIN_MDL_GRP_DTL */
    public static final String BTN_NM_OPEN_WIN_MDL_GRP_DTL = "OpenWin_ModelGroupDetail";

    /** Event value : LINK_NM_OPEN_WIN_SHIFT_HDR */
    public static final String LINK_NM_OPEN_WIN_SHIFT_HDR = "OpenWin_ShiftHeader";

    /** Event value : BTN_NM_OPEN_WIN_SHIFT_DTL */
    public static final String BTN_NM_OPEN_WIN_SHIFT_DTL = "OpenWin_ShiftDetail";

    /** Event value : BTN_NM_OPEN_WIN_INTG_ITEM */
    public static final String BTN_NM_OPEN_WIN_INTG_ITEM = "OpenWin_IntgItemPopup";

    /** Parameter value : MDSE_DESC_SHORT_TXT */
    public static final String PRM_VALUE_MDSE_CD = "MDSE_CD";

    /** Parameter value : MDSE_DESC_SHORT_TXT */
    public static final String PRM_VALUE_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** Parameter value : SVC_PRC_SHIFT_NUM */
    public static final String PRM_VALUE_SVC_PRC_SHIFT_NUM = "SVC_PRC_SHIFT_NUM";

    /** Parameter value : SVC_PRC_SHIFT_DESC_TXT */
    public static final String PRM_VALUE_SVC_PRC_SHIFT_DESC_TXT = "SVC_PRC_SHIFT_DESC_TXT";

    /** Parameter length : NWAL1130 */
    public static final int PRM_LENGTH_NWAL1130 = 7;

    /** MDSE_CD_LENGTH */
    public static final int MDSE_CD_LENGTH = 16;

    /** MDSE_DESC_SHORT_TXT_LENGTH */
    public static final int MDSE_DESC_SHORT_TXT_LENGTH = 50;

    /** Label name : Model Group */
    public static final String LBL_NM_MDL_GRP = "Model Group";

    /** Label name : Description */
    public static final String LBL_NM_MDL_GRP_DESC_TXT = "Model Group Description";

    /** Label name : Line of Business */
    public static final String LBL_NM_LOB = "Line of Business";

    /** Label name : Charge Travel */
    public static final String LBL_NM_TRVL_CHRG_FLG = "Charge Travel";

    /** Label name : Shift# */
    public static final String LBL_NM_SHIFT_NUM = "Shift#";

    /** Label name : Shift Description */
    public static final String LBL_NM_SHIFT_DESC_TXT = "Shift Description";

    /** Label name : Labor Per Hour */
    public static final String LBL_NM_LBOR_UNIT_AMT = "Labor Per Hour";

    /** Label name : Minimum Hours */
    public static final String LBL_NM_MIN_CHRG_HRS_AOT = "Minimum Hours";

    /** Label name : Default Intangible Item# */
    public static final String LBL_NM_DEF_INTG_ITEM = "Default Intangible Item#";

    /** Label name : Default Intangible Item Description */
    public static final String LBL_NM_DEF_INTG_ITEM_DESC = "Default Intangible Item Description";

    /** LIMIT_NUM_SEARCH */
    public static final int LIMIT_NUM_SEARCH = 1000;

    /** SVC_PRC_SHIFT_NUM_LENGTH */
    public static final int SVC_PRC_SHIFT_NUM_LENGTH = 25;

    /** SVC_PRC_SHIFT_DESC_TXT_LENGTH */
    public static final int SVC_PRC_SHIFT_DESC_TXT_LENGTH = 50;

    /** LINE_BIZ_CD_LENGTH */
    public static final int LINE_BIZ_CD_LENGTH = 16;

    /** Event Name : CMN_Close */
    public static final String NWAL1130_EVENT_NM_CLOSE = "CMN_Close";
}
