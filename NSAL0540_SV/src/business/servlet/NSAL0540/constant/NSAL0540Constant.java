/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0540.constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *<pre>
 * Solution Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 * 2016/04/21   Hitachi         T.Tomita        Update          QC#6399
 * 2016/05/11   Hitachi         T.Tomita        Update          QC#7832
 *</pre>
 */
public final class NSAL0540Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0540";

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
     * Select All button
     */
    public static final List<String> SELECTALL = createImmutableList("SelectAll", "SelectAll", "Select All");

    /**
     * Select All button
     */
    public static final List<String> UNSELECTALL = createImmutableList("UnselectAll", "UnselectAll", "Unselect All");

    /**
     * Link Configuration(s) button
     */
    public static final List<String> LINKCONFIG = createImmutableList("LinkConfig", "LinkConfig", "Link Configuration(s)");

    /**
     * Delete Configuration(s) button
     */
    public static final List<String> DELETECONFIG = createImmutableList("DeleteConfig", "DeleteConfig", "Delete Configuration(s)");

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
     * DETAIL_MAX_SIZE
     */
    public static final int DETAIL_MAX_SIZE = 1000;

    /**
     * NSAL1240_MODE : 02
     */
    public static final String NSAL1240_MODE_02 = "02";

    // START 2016/05/11 T.Tomita [QC#7832, MOD]
    /**
     * NSAL1240_PARM_CNT : 33
     */
    public static final int NSAL1240_PARAM_CNT = 33;
    // END 2016/05/11 T.Tomita [QC#7832, MOD]

    /**
     * PARAM_NUM : 0
     */
    public static final int PARAM_NUM_0 = 0;

    /**
     * PARAM_NUM : 29
     */
    public static final int PARAM_NUM_29 = 29;

    // START 2016/05/11 T.Tomita [QC#7832, ADD]
    /**
     * PARAM_NUM : 31
     */
    public static final int PARAM_NUM_31 = 31;

    /**
     * PARAM_NUM : 32
     */
    public static final int PARAM_NUM_32 = 32;
    // END 2016/05/11 T.Tomita [QC#7832, ADD]

    private static List<String> createImmutableList(String... elements) {
        List<String> tmp = new ArrayList<String>();
        for (String element : elements) {
            tmp.add(element);
        }
        return Collections.unmodifiableList(tmp);
    }
}
