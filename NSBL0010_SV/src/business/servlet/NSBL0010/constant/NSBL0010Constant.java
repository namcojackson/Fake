/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010.constant;

/**
 * <pre>
 * Service Dispatch Schedule/Dispatch Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 * 2016/11/15   Hitach          N.Arai          Update          QC#15784
 * 2016/11/22   Hitachi         N.Arai          Update          QC#13901
 * 2017/01/05   Hitachi         N.Arai          Update          QC#13901-2
 * 2018/01/22   Hitachi         T.Iwamoto       Update          QC#23655
 * 2018/01/24   Hitachi         T.Tomita        Update          QC#23655
 *</pre>
 */
public interface NSBL0010Constant {

    /** Business ID */
    String BUSINESS_ID = "NSBL0010";

    /** Screen ID */
    String SCREEN_ID = "NSBL0010Scrn00";

// START 2018/01/22 [QC#23655, MOD]
    /** Function name for update */
    String FUNC_UPDATE = "NSBL0010T020";

    /** Function name for search */
    String FUNC_SEARCH = "NSBL0010T010";
// END 2018/01/22 [QC#23655, MOD]

    /** Function Code 20 */
    String FUNC_CD_20 = "20";

    /** Function Code 40 */
    String FUNC_CD_40 = "40";

// START 2016/11/22 N.Arai [QC#13901, MOD]
    /** PARAMS_SHIP_TO_ONLY 03 */
    String PARAMS_SHIP_TO_ONLY = "03";
    
    /** OpenWin_ShipTo  */
    public static final String OPENWIN_SHIPTO = "OpenWin_ShipTo";
// END 2016/11/22 N.Arai [QC#13901, MOD]

    /** color row */
    int ROW_NUM2 = 2;

    /** color row */
    int ROW_NUM3 = 3;

    /** time length */
    int TIME_LEN = 4;

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    /** Function Button 1 */
    String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Function Button 2 */
    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Function Button 4 */
    String[] BTN_CMN_BLANK4 = {"btn4", "CMN_Approve", "Approve" };

    /** Function Button 5 */
    String[] BTN_CMN_BLANK5 = {"btn5", "CMN_Reject", "Reject" };

    /** Function Button 6 */
    String[] BTN_CMN_BLANK6 = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    String[] BTN_CMN_BLANK7 = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Search Button */
    String SEARCH_BTN = "Search";

    /** Search ShipTo Button */
    String SEARCH_SHIPTO_BTN = "SearchShipToName";

    /** Search Schedule Button */
    String SCHEDULE_BTN = "Schedule";

    /** Dispatch Button */
    String DISPATCH_BTN = "Dispatch";

    /** Un Schedule/Dispatch Button */
    String UN_SCHD_DISPT_BTN = "Cancel";

    // Mod Start 2018/01/24 QC#23655
    /** Accept Button */
    String ACCEPT_BTN = "Accept";

    /** History Button */
    String HISTORY_BTN = "OpenWin_History";

    /** Update ETA Button */
    String UPDATE_ETA_BTN = "UpdateETA";
    // Mod End 2018/01/24 QC#23655

    /** Tech Button */
    String TECH_CD_BTN = "OpenWin_TechnicianDetail";

    /** table id */
    String TBL_ID = "TBL_D";

    /** table id */
    String TBL_ID_LEFT = "A_Left";

    /** table id */
    String TBL_ID_RIGHT = "A_Right";

    /** tag id */
    String SCHD_SORT_ID = "xxDtTm";

    /** tag id */
    String PRTY_SORT_ID = "svcCallPrtyCd";

    /** tag id */
    String EML_SORT_ID = "schdDisptEmlFlg";

    /** tag id */
    String CHK_FLG_ID = "xxChkBox_A#";

    /** tag id */
    String SCHD_TZ_ID = "techSchdTz_A#";

    /** tag id */
    String PRTY_CD_ID = "svcCallPrtyCd_A#";

    /** tag id */
    String EML_FLG_ID = "schdDisptEmlFlg_A#";

    /** tag id */
    String HOLD_FLG_ID = "svcCrHldFlg_AL#";

    /** tag id */
    String C_O_FLG_ID = "xxBtnFlg_AL#";

    /** tag id */
    String TR_LEFT01_ID = "trLeft01#";

    /** tag id */
    String TR_LEFT02_ID = "trLeft02#";

    /** tag id */
    String TR_RIGHT01_ID = "trRight01#";

    /** tag id */
    String TR_RIGHT02_ID = "trRight02#";

    /** Message Kind : "E" : Error */
    String MESSAGE_KIND_E = "E";

    /** Colon */
    String COLON = ":";

    /** Column Name : TECH_CD */
    String TECH_CD = "TECH_CD";

    /** Column Name : TECH_SCHD_FROM_DT */
    String TECH_SCHD_FROM_DT = "TECH_SCHD_FROM_DT";

    /** Column Name : TECH_SCHD_FROM_TM */
    String TECH_SCHD_FROM_TM = "TECH_SCHD_FROM_TM";

    /** Column Name : TECH_SCHD_TO_DT */
    String TECH_SCHD_TO_DT = "TECH_SCHD_TO_DT";

    /** Column Name : TECH_SCHD_TO_TM */
    String TECH_SCHD_TO_TM = "TECH_SCHD_TO_TM";

    /** Column Name : TECH_SCHD_TZ */
    String TECH_SCHD_TZ = "TECH_SCHD_TZ";

    /** Column Name : SVC_CALL_PRTY_CD */
    String SVC_CALL_PRTY_CD = "SVC_CALL_PRTY_CD";

    /** format */
    String FORMAT_TM_FROM = "^([0-1][0-9]|2[0-3]):?([0-5][0-9])$";

    /** format */
    String FORMAT_TM_TO_SCR = "$1:$2";

    /** format */
    String FORMAT_TM_TO_DB = "$1$200";

    /** format */
    String FORMAT_TM_FROM2 = "^([0-9]):?([0-5][0-9])$";

    /** format */
    String FORMAT_TM_TO_SCR2 = "0$1:$2";

    /** format */
    String FORMAT_TM_TO_DB2 = "0$1$200";

    /** format */
    String FORMAT_TM_FROM3 = "^([0-9]|[0-1][0-9]|2[0-3])$";

    /** format */
    String FORMAT_TM_TO_SCR3 = "%02d:00";

    /** format */
    String FORMAT_TM_TO_DB3 = "%02d0000";

    /** format */
    String FORMAT_SCHD_TM_FROM = "^([0-1][0-9]|2[0-3])([0-5][0-9]).*$";

    /** format */
    String FORMAT_SCHD_TM_TO = "$1:$2";

    /** format */
    String FORMAT_RCV_TM_FROM = "^([0-1][0-9]|2[0-3])([0-5][0-9])([0-5][0-9]).*$";

    /** format */
    String FORMAT_RCV_TM_TO = "$1:$2";

    // ----------- Pop up setting -----------
    /** MEMO_POP_LABEL_01 */
    String MEMO_POP_LABEL_01 = "Service Task Num";

    /** MEMO_POP_LABEL_02 */
    String MEMO_POP_LABEL_02 = "FSR Num";

    /** MEMO_POP_LABEL_03 */
    String MEMO_POP_LABEL_03 = "Serial Num";

    /** MEMO_POP_LABEL_04 */
    String MEMO_POP_LABEL_04 = "Model Name";

    /** MEMO_POP_COND_01 */
    String MEMO_POP_COND_01 = "SVC_TASK_NUM";

    /** MEMO_POP_COND_01 */
    String MEMO_POP_COND_02 = "FSR_NUM";

    /** CLOSE */
    String CLOSE = "CMN_Close";

    /** parameter number */
    int PARAM_0 = 0;

    /** parameter number */
    int PARAM_1 = 1;

    /** parameter number */
    int PARAM_2 = 2;

    /** parameter number */
    int PARAM_3 = 3;

    /** parameter number */
    int PARAM_4 = 4;

    /** parameter number */
    int PARAM_5 = 5;

    /** parameter number */
    int PARAM_6 = 6;

    /** parameter number */
    int PARAM_7 = 7;

    /** parameter number */
    int PARAM_8 = 8;

    /** parameter number */
    int PARAM_9 = 9;

    /** parameter number */
    int PARAM_10 = 10;

    /** parameter number */
    int PARAM_11 = 11;

    /** parameter number */
    int PARAM_12 = 12;

    /** parameter number */
    int PARAM_13 = 13;

    /** parameter number */
    int PARAM_14 = 14;

    /** parameter number */
    int PARAM_15 = 15;

    /** parameter number */
    int PARAM_16 = 16;

    /** parameter number */
    int PARAM_17 = 17;

    /** parameter number */
    int PARAM_18 = 18;

    /** parameter number */
    int PARAM_19 = 19;

    /** parameter number */
    int PARAM_20 = 20;

    /** parameter number */
    int PARAM_21 = 21;

// START 2016/11/15 N.Arai [QC#15784, MOD]
    /** parameter number */
    int PARAM_22 = 22;
// END 2016/11/15 N.Arai [QC#15784, MOD]

    // ----------- API Mode Code ------------
    /** Mode: 01 : Schedule */
    String MODE_SCHEDULE = "01";

    /** Mode: 02 : Dispatch */
    String MODE_DISPATCH = "02";

    /** Mode: 03 : Cancel */
    String MODE_CANCEL = "03";

    // -------------- Error Message --------------
    /** Please fill out the search criteria at 1 field or more. */
    String NSBM0001E = "NSBM0001E";

    /** No search results found. */
    String NSBM0002E = "NSBM0002E";

    /** Search results exceeded [@]. Please modify search criteria. */
    String NSBM0003E = "NSBM0003E";

    /** Time is invalid. Please use a valid format, [@] */
    String NSBM0004E = "NSBM0004E";

    /** The process has been successfully completed. */
    String NSBM0005I = "NSBM0005I";

    /**
     * Multiple check boxes cannot be selected. Check either one of
     * the boxes only.
     */
    String NTAM0005E = "NTAM0005E";

    /** Please check at least 1 checkbox. */
    String NSBM0007E = "NSBM0007E";

    /** Please select only one checkbox. */
    String NSBM0008E = "NSBM0008E";

    /** "@" process is only available in [@] status. */
    String NSBM0010E = "NSBM0010E";

    /** Please enter the [to date] greater than [from date]. */
    String NSBM0014E = "NSBM0014E";

    /** [@] field is mandatory. */
    String ZZM9000E = "ZZM9000E";

// START 2016/11/22 N.Arai [QC#13901, MOD]
    /** Item Name (Service Branch) */
    public static final String ITEM_NM_BRANCH = "Service Branch";

    /** Parameter 0 */
    public static final int PRMS_00 = 0;

    /** Parameter 1 */
    public static final int PRMS_01 = 1;

    /** Parameter 2 */
    public static final int PRMS_02 = 2;

    /** Parameter 3 */
    public static final int PRMS_03 = 3;

    /** Parameter 4 */
    public static final int PRMS_04 = 4;

    /** Parameter 5 */
    public static final int PRMS_05 = 5;

    /** Parameter 6 */
    public static final int PRMS_06 = 6;

    /** Parameter 7 */
    public static final int PRMS_07 = 7;

    /** Parameter 8 */
    public static final int PRMS_08 = 8;

    /** Parameter 9 */
    public static final int PRMS_09 = 9;

    /** Parameter 10 */
    public static final int PRMS_10 = 10;

    /** Parameter 11 */
    public static final int PRMS_11 = 11;
// END 2016/11/22 N.Arai [QC#13901, MOD]

// START 2017/01/05 N.Arai [QC#13901-2, MOD]
    /** OpenWin_Branch  */
    public static final String OPENWIN_BRANCH = "OpenWin_Branch";

    /** OpenWin_Technician  */
    public static final String OPENWIN_TECHNICIAN = "OpenWin_Technician";

    /** String Item : percent */
    public static final String PERCENT = "%";

    /** The number of the attribute of WhereList */
    public static final int ATTR_NUM_WHERE_LIST = 4;

    /** The index number of the attribute of WhereList */
    public static final int WLIST_DSP_OBJ_NM = 0;

    /** The index number of the attribute of WhereList */
    public static final int WLIST_OBJ_ID = 1;

    /** The index number of the attribute of WhereList */
    public static final int WLIST_OBJ_VALUE = 2;

    /** The index number of the attribute of WhereList */
    public static final int WLIST_WHERE_FLG = 3;

    /** The number of the attribute of ColumnList */
    public static final int ATTR_NUM_CLMN_LIST = 4;

    /** The index number of the attribute of ColumnList */
    public static final int CLIST_DSP_OBJ_NM = 0;

    /** The index number of the attribute of ColumnList */
    public static final int CLIST_OBJ_ID = 1;

    /** The index number of the attribute of ColumnList */
    public static final int CLIST_OBJ_LENGTH = 2;

    /** The index number of the attribute of ColumnList */
    public static final int CLIST_LINK_FLG = 3;

    /** TECH_CD LENGTH */
    public static final int TECH_CD_LENGTH = 22;

    /** TECH_NM LENGTH */
    public static final int TECH_NM_LENGTH = 50;

    /** SVC_BR_CD LENGTH */
    public static final int SVC_BR_CD_LENGTH = 12;

    /** SVC_BR_CD_DESC_TXT LENGTH */
    public static final int SVC_BR_CD_DESC_TXT_LENGTH = 50;

    /** The number of the attribute of SortList */
    public static final int ATTR_NUM_SORT_LIST = 2;

    /** The index number of the attribute of SortList */
    public static final int SLIST_OBJ_ID = 0;

    /** The index number of the attribute of SortList */
    public static final int SLIST_ORD_BY = 1;
// END 2017/01/05 N.Arai [QC#13901-2, MOD]
}
