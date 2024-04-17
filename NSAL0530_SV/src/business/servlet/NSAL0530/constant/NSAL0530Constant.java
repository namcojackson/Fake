/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0530.constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *<pre>
 * Solution Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tomita        Create          N/A
 * 2016/03/01   Hitachi         K.Kasai         Update          QC#3586
 *</pre>
 */
public final class NSAL0530Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0530";

    /**
     * SCREEN_ID
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
     * Search button
     */
    public static final List<String> SEARCH = createImmutableList("Search", "Search", "Search");

    /**
     * Solution Create New button
     */
    public static final List<String> SOLUTIONCREATENEW = createImmutableList("SolutionCreateNew", "SolutionCreateNew", "New");

    /**
     * Button is active
     */
    public static final int BTN_ACTIVE = 1;

    /**
     * Attach Mode : Modification
     */
    public static final String ATTACH_MODE_MODIFICATION = "Modification";

    /**
     * CMN_Close
     */
    public static final String CMN_CLOSE = "CMN_Close";

    /**
     * OpenWin_SvcMachMstr
     */
    public static final String OPENWIN_SVCMACHMSTR = "OpenWin_SvcMachMstr";

    /**
     * OpenWin_Mdl
     */
    public static final String OPENWIN_MDL = "OpenWin_Mdl";

    /**
     * Timestamp store pattern
     */
    public static final String TS_STORE_PATTERN = "yyyyMMddHHmmssSSS";

    /**
     * UNDERSCORE
     */
    public static final String UNDERSCORE = "_";

    /**
     * NSAL0540_PRM_LENGTH : 1
     */
    public static final int NSAL0540_PRM_LENGTH = 1;

    /**
     * NSAL0030_PRM_LENGTH : 17
     */
    public static final int NSAL0030_PRM_LENGTH = 17;

    /**
     * NWAL1130_PRM_LENGTH : 7
     */
    public static final int NWAL1130_PRM_LENGTH = 7;

    // mod start 2016/03/01 CSA Defect#3586
    /**
     * NMAL6800_PRM_LENGTH : 7
     */
    public static final int NMAL6800_PRM_LENGTH = 7;
    // mod end 2016/03/01 CSA Defect#3586

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
     * T_MDL_ID Length : 22
     */
    public static final int T_MDL_ID_LENGTH = 22;

    /**
     * T_MDL_NM Length : 50
     */
    public static final int T_MDL_NM_LENGTH = 50;

    /**
     * SVC_MACH_MSTR_PK Length : 28
     */
    public static final int SVC_MACH_MSTR_PK_LENGTH = 28;

    /**
     * SER_NUM Length : 30
     */
    public static final int SER_NUM_LENGTH = 30;

    /**
     * MDSE_CD Length : 16
     */
    public static final int MDSE_CD_LENGTH = 16;

    /**
     * NWAL1130 Window Title : Install Base ID  Search Popup
     */
    public static final String NWAL1130_WIN_TITLE_MACH = "Install Base ID  Search Popup";

    /**
     * NWAL1130 Window Title : Svc Model Name Search Popup
     */
    public static final String NWAL1130_WIN_TITLE_MDL = "Svc Model Name Search Popup";

    /**
     * SVC_MACH_MSTR Table Name
     */
    public static final String SVC_MACH_MSTR_TBL_NM = "SVC_MACH_MSTR";

    /**
     * No search results found.
     */
    public static final String NSAM0194I = "NSAM0194I";

    private static List<String> createImmutableList(String... elements) {
        List<String> tmp = new ArrayList<String>();
        for (String element : elements) {
            tmp.add(element);
        }
        return Collections.unmodifiableList(tmp);
    }
}
