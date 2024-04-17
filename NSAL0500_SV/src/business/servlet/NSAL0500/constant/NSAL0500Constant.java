/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0500.constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *<pre>
 * Sub Contract Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/07   Hitachi         T.Aoyagi        Create          N/A
 * 2016/01/06   Hitachi         T.Tsuchida      Update          N/A
 * 2016/01/12   Hitachi         T.Tsuchida      Update          QC#2889
 * 2016/02/10   Hitachi         K.Kasai         Update          QC#3704
 * 2016/12/14   Hitachi         K.Kojima        Update          QC#15653
 * 2017/02/17   Hitachi         K.Kitachi       Update          QC#17564
 *</pre>
 */
public final class NSAL0500Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0500";

    /**
     * BUSINESS_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * Common button 1
     */
    public static final List<String> SAVE = createImmutableList("btn1", "CMN_Save", "Save");

    /**
     * Common button 2
     */
    public static final List<String> SUBMIT = createImmutableList("btn2", "CMN_Submit", "Submit");

    /**
     * Common button 3
     */
    public static final List<String> APPLY = createImmutableList("btn3", "CMN_Apply", "Apply");

    /**
     * Common button 4
     */
    public static final List<String> APPROVE = createImmutableList("btn4", "CMN_Approve", "Approve");

    /**
     * Common button 5
     */
    public static final List<String> REJECT = createImmutableList("btn5", "CMN_Reject", "Reject");

    /**
     * Common button 6
     */
    public static final List<String> DOWNLOAD = createImmutableList("btn6", "CMN_Download", "Download");

    /**
     * Common button 7
     */
    public static final List<String> DELETE = createImmutableList("btn7", "CMN_Delete", "Delete");

    /**
     * Common button 8
     */
    public static final List<String> CLEAR = createImmutableList("btn8", "CMN_Clear", "Clear");

    /**
     * Common button 9
     */
    public static final List<String> RESET = createImmutableList("btn9", "CMN_Reset", "Reset");

    /**
     * Common button 10
     */
    public static final List<String> RETURN = createImmutableList("btn10", "CMN_Return", "Return");

    /**
     * Search Vendor Name button
     */
    public static final List<String> SEARCH_VNDNM = createImmutableList("Search_VndNm", "Search_VndNm", ">>");

    /**
     * Attachment button
     */
    public static final List<String> FILEATTACH = createImmutableList("FileAttach", "FileAttach", "Attachment");

    /**
     * Create New button
     */
    public static final List<String> CREATENEW = createImmutableList("CreateNew", "CreateNew", "Create New");

    /**
     * Button is in-active
     */
    public static final int BTN_INACTIVE = 0;

    /**
     * Button is active
     */
    public static final int BTN_ACTIVE = 1;

    /**
     * FUNC_ID_T010
     */
    public static final String FUNC_ID_T010 = BUSINESS_ID + "T010";

    /**
     * FUNC_ID_T020
     */
    public static final String FUNC_ID_T020 = BUSINESS_ID + "T020";

    /**
     * Attach Mode : Modification
     */
    public static final String ATTACH_MODE_MODIFICATION = "Modification";

    /**
     * CMN_Close
     */
    public static final String CMN_CLOSE = "CMN_Close";

    /**
     * Timestamp store pattern
     */
    public static final String TS_STORE_PATTERN = "yyyyMMddHHmmssSSS";

    /**
     * UNDERSCORE
     */
    public static final String UNDERSCORE = "_";

    /**
     * Index3
     */
    public static final int IDX_3 = 3;

    /**
     * Index4
     */
    public static final int IDX_4 = 4;

    /**
     * Index5
     */
    public static final int IDX_5 = 5;

    /**
     * Index6
     */
    public static final int IDX_6 = 6;

    /**
     * Index7
     */
    public static final int IDX_7 = 7;

    /**
     * Index8
     */
    public static final int IDX_8 = 8;

    /**
     * Index9
     */
    public static final int IDX_9 = 9;

    /**
     * Index10
     */
    public static final int IDX_10 = 10;

    /**
     * Index11
     */
    public static final int IDX_11 = 11;

    private static List<String> createImmutableList(String... elements) {
        List<String> tmp = new ArrayList<String>();
        for (String element : elements) {
            tmp.add(element);
        }
        return Collections.unmodifiableList(tmp);
    }

    /**
     * TABLE_A
     */
    public static final String TABLE_A = "A";

    /**
     * TABLE_B
     */
    public static final String TABLE_B = "B";

    /**
     * TABLE_C
     */
    public static final String TABLE_C = "C";

    /**
     * NWAL1130 LENGTH
     */
    public static final int NWAL1130_PRM_LENGTH = 7;

    /**
     * The number of the attribute of WhereList
     */
    public static final int ATTR_NUM_WHERE_LIST = 4;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_DSP_OBJ_NM = 0;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_OBJ_ID = 1;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_OBJ_VALUE = 2;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_WHERE_FLG = 3;

    /**
     * The number of the attribute of ColumnList
     */
    public static final int ATTR_NUM_CLMN_LIST = 4;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_DSP_OBJ_NM = 0;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_OBJ_ID = 1;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_OBJ_LENGTH = 2;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_LINK_FLG = 3;

    /**
     * VND_CD LENGTH 
     */
    public static final int VND_CD_LENGTH = 15;

    /**
     * LOC_NM LENGTH 
     */
    public static final int LOC_NM_LENGTH = 30;

    /**
     * PRNT_VND_CD LENGTH 
     */
    public static final int PRNT_VND_CD_LENGTH = 15;

    /**
     * PRNT_VND_NM LENGTH 
     */
    public static final int PRNT_VND_NM_LENGTH = 30;

    /**
     * INDEX_THREE 
     */
    public static final int INDEX_THREE = 3;

    /** Display Mode : Freeze */
    public static final String DISPLAY_MODE_FREEZE = "0";

    // START 2016/12/14 K.Kojima [QC#15653,ADD]
    /** OpenWin_Vendor */
    public static final String OPENWIN_VENDOR = "OpenWin_Vendor";

    /** OpenWin_Tech */
    public static final String OPENWIN_TECH = "OpenWin_Tech";

    /** MAX_DT_VAL */
    public static final String MAX_DT_VAL = "29991231";

    /** TECH_CD LENGTH */
    public static final int TECH_CD_LENGTH = 22;

    /** TECH_NM LENGTH */
    public static final int TECH_NM_LENGTH = 50;

    // END 2016/12/14 K.Kojima [QC#15653,ADD]

    // START 2017/02/17 K.Kitachi [QC#17564, ADD]
    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";
    // END 2017/02/17 K.Kitachi [QC#17564, ADD]
}
