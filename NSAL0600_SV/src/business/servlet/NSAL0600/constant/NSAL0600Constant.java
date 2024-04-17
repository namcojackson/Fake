/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0600.constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *<pre>
 * Cascade Date
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/01/16   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public final class NSAL0600Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0600";

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
     * APPLY TO ALL
     */
    public static final List<String> APPLY_TO_ALL = createImmutableList("ApplyToAll", "ApplyToAll", "APPLY TO ALL");

    /**
     * Select All button
     */
    public static final List<String> SELECTALL = createImmutableList("SelectAll", "SelectAll", "Select All");

    /**
     * Unselect All button
     */
    public static final List<String> UNSELECTALL = createImmutableList("UnselectAll", "UnselectAll", "Unselect All");

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
     * Edit Mode
     */
    public static final String EDIT_MODE = "1";

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    private static List<String> createImmutableList(String... elements) {
        List<String> tmp = new ArrayList<String>();
        for (String element : elements) {
            tmp.add(element);
        }
        return Collections.unmodifiableList(tmp);
    }
}
