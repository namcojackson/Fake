/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0820.constant;

/**
 *<pre>
 * Actual Counters for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         T.Iwamoto         Create          N/A
 * 2016/02/03   Hitachi         T.Tomita        Update          QC#3312
 * 2016/05/20   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/05/25   Hitachi         T.Tomita        Update          QC#8898
 * 2016/07/19   Hitachi         M.Gotou         Update          QC#11854
 *</pre>
 */
public final class NSAL0820Constant {

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
    public static final String BUSINESS_ID = "NSAL0820";

    /** Reference authority */
    public static final String AUTH_REFERENCE = "NSAL0820T010";

    /** Update authority */
    public static final String AUTH_UPDATE = "NSAL0820T020";

    /** SCRN_ID : NSBL0450Scrn00 */
    public static final String SCRN_ID = "NSAL0820Scrn00";

    /** Function code (Search) */
    public static final String FUNCTION_SEARCH = "20";

    /** Function code (Submit) */
    public static final String FUNCTION_SUBMIT = "40";

    /** mode (After Search) */
    public static final String MODE_AFTER_SEARCH = "01";

    /** mode (After Open For Correction) */
    public static final String MODE_AFTER_OPEN = "02";

    /** length 7 */
    public static final int LENGTH_7 = 7;

    // START 2016/02/03 T.Tomita [QC#3312, DEL]
//    /** The number of the attribute of WhereList */
//    public static final int ATTR_NUM_WHERE_LIST = 4;
//
//    /** The index number of the attribute of WhereList */
//    public static final int WLIST_DSP_OBJ_NM = 0;
//
//    /** The index number of the attribute of WhereList */
//    public static final int WLIST_OBJ_ID = 1;
//
//    /** The index number of the attribute of WhereList */
//    public static final int WLIST_OBJ_VALUE = 2;
//
//    /** The index number of the attribute of WhereList */
//    public static final int WLIST_WHERE_FLG = 3;
//
//    /** The number of the attribute of ColumnList */
//    public static final int ATTR_NUM_CLMN_LIST = 4;
//
//    /** The index number of the attribute of ColumnList */
//    public static final int CLIST_DSP_OBJ_NM = 0;
//
//    /** The index number of the attribute of ColumnList */
//    public static final int CLIST_OBJ_ID = 1;
//
//    /** Tc12he index number of the attribute of ColumnList */
//    public static final int CLIST_OBJ_LENGTH = 2;
//
//    /** The index number of the attribute of ColumnList */
//    public static final int CLIST_LINK_FLG = 3;
//
//    /** SER_NUM Length : 30 */
//    public static final int SER_NUM_LENGTH = 30;
//
//    /** MDSE_CD Length : 16 */
//    public static final int MDSE_CD_LENGTH = 16;
//
//    /** MDSE_NM Length : 30 */
//    public static final int MDSE_NM_LENGTH = 30;
//
//    /** SVC_MACH_MSTR_STS_CD Length : 5 */
//    public static final int SVC_MACH_MSTR_STS_CD_LENGTH = 5;
//
//    /** SVC_CONFIG_MSTR_PK Length : 28 */
//    public static final int SVC_CONFIG_MSTR_PK_LENGTH = 28;
//
//    /** SVC_MACH_MSTR_PK Length : 28 */
//    public static final int SVC_MACH_MSTR_PK_LENGTH = 28;
//
//    /** T_MDL_NM Length : 50 */
//    public static final int T_MDL_NM_LENGTH = 50;
//
//    /** CUR_LOC_NUM Length : 30 */
//    public static final int CUR_LOC_NUM_LENGTH = 30;
    // END 2016/02/03 T.Tomita [QC#3312, DEL]

    /** "@" is not entered. */
    public static final String NSAM0007E = "NSAM0007E";

    /** Please specify "@" for the file extension. */
    public static final String NSAM0404E = "NSAM0404E";

    // START 2016/07/19 [QC#11854, ADD]
    /** Got an error while validation data. Please correct it and try again. */
    public static final String NSAM0541E = "NSAM0541E";
    // END   2016/07/19 [QC#10854, ADD]

    // START 2016/05/20 [QC#4061, MOD]
    /** file extension */
    public static final String[] FILE_EXTENSION = {"csv", "txt", "xls", "xlsx"};
    // END   2016/05/20 [QC#4061, MOD]

    // START 2016/02/03 T.Tomita [QC#3312, ADD]
    /** parameter length (NSAL1240) */
    public static final int PARAM_LENGTH_NSAL1240 = 31;

    /** parameter index : 30 */
    public static final int PARAM_INDEX_30 = 30;
    // END 2016/02/03 T.Tomita [QC#3312, ADD]

    // START 2016/05/25 T.Tomita [QC#8898, ADD]
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

    /** The number of the attribute of SortList */
    public static final int ATTR_NUM_SORT_LIST = 2;

    /** The index number of the attribute of SortList */
    public static final int SLIST_CLMN_NM = 0;

    /** The index number of the attribute of SortList */
    public static final int SLIST_ORD_COND = 1;

    /** MTR_LB_CD Length : 2 */
    public static final int MTR_LB_CD_LENGTH = 7;

    /** MTR_LB_DESC_TXT Length : 50 */
    public static final int MTR_LB_DESC_TXT_LENGTH = 50;

    /** OPENWIN_PHYSMTR : OpenWin_PhysMtr */
    public static final String OPENWIN_PHYSMTR = "OpenWin_PhysMtr";

    /** OPENWIN_BLLGMTR : OpenWin_BllgMtr */
    public static final String OPENWIN_BLLGMTR = "OpenWin_BllgMtr";
    // END 2016/05/25 T.Tomita [QC#8898, ADD]
}
