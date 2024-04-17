/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL9900.constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *<pre>
 * Master Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/11   Hitachi         T.Aoyagi        Create          N/A
 * 2017/07/11   Hitachi         M.Kidokoro      Update          18659,19534
 * 2018/06/22   Hitachi         K.Kojima        Update          QC#24320
 *</pre>
 */
public final class NSAL9900Constant {

    /** Business Id */
    public static final String BUSINESS_ID = "NSAL9900";

    /** Screen Id */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /** Common button 1 */
    public static final List<String> SAVE = createImmutableList("btn1", "CMN_Save", "Save");

    /** Common button 2 */
    public static final List<String> SUBMIT = createImmutableList("btn2", "CMN_Submit", "Submit");

    /** Common button 3 */
    public static final List<String> APPLY = createImmutableList("btn3", "CMN_Apply", "Apply");

    /** Common button 4 */
    public static final List<String> APPROVE = createImmutableList("btn4", "CMN_Approve", "Approve");

    /** Common button 5 */
    public static final List<String> REJECT = createImmutableList("btn5", "CMN_Reject", "Reject");

    /** Common button 6 */
    public static final List<String> DOWNLOAD = createImmutableList("btn6", "CMN_Download", "Download");

    /** Common button 7 */
    public static final List<String> DELETE = createImmutableList("btn7", "CMN_Delete", "Delete");

    /** Common button 8 */
    public static final List<String> CLEAR = createImmutableList("btn8", "CMN_Clear", "Clear");

    /** Common button 9 */
    public static final List<String> RESET = createImmutableList("btn9", "CMN_Reset", "Reset");

    /** Common button 10 */
    public static final List<String> RETURN = createImmutableList("btn10", "CMN_Return", "Return");

    /** Search button */
    public static final List<String> SEARCH = createImmutableList("Search", "Search", "Search");

    /** Select All button */
    public static final List<String> SELECTALL = createImmutableList("Select_All", "Select_All", "Select All");

    /** Select All button */
    public static final List<String> UNSELECTALL = createImmutableList("Un_Select_All", "Un_Select_All", "Unselect All");

    /** Upload button */
    // START 2018/06/21 K.Kojima [QC#24320,MOD]
    // public static final List<String> UPLOAD = createImmutableList("Upload", "Upload", "Upload");
    public static final List<String> UPLOAD = createImmutableList("UploadCsv", "UploadCsv", "Upload");
    // END 2018/06/21 K.Kojima [QC#24320,MOD]

    /** Insert Row button */
    public static final List<String> INSERTROW = createImmutableList("InsertRow", "InsertRow", "Insert Row");

    /** Copy Row button */
    public static final List<String> COPYROW = createImmutableList("CopyRow", "CopyRow", "Copy Row");

    /** Delete Row button */
    public static final List<String> DELETEROW = createImmutableList("DeleteRow", "DeleteRow", "Delete Row");

    /** Button is active */
    public static final int BTN_ACTIVE = 1;

    /** FUNC_ID_T010 */
    public static final String FUNC_ID_T010 = "T010";

    /** FUNC_ID_T020 */
    public static final String FUNC_ID_T020 = "T020";

    /** COL_TP_STRING */
    public static final String COL_TP_STRING = "String";

    /** COL_TP_NUMBER */
    public static final String COL_TP_NUMBER = "Number";

    /** COL_TP_DATE */
    public static final String COL_TP_DATE = "Date";

    /** COL_TP_YEARMONTH */
    public static final String COL_TP_YEARMONTH = "YearMonth";

    /** COL_TP_YEAR */
    public static final String COL_TP_YEAR = "Year";

    /** COL_TP_TIME */
    public static final String COL_TP_TIME = "Time";

    /** COL_TP_TS */
    public static final String COL_TP_TS = "Ts";

    /** Index 3 */
    public static final int IDX_3 = 3;

    /** Index 4 */
    public static final int IDX_4 = 4;

    /** Index 5 */
    public static final int IDX_5 = 5;

    /** Index 6 */
    public static final int IDX_6 = 6;

    /** Index 7 */
    public static final int IDX_7 = 7;

    /** Index 8 */
    public static final int IDX_8 = 8;

    /** Index 9 */
    public static final int IDX_9 = 9;

    /** Index 10 */
    public static final int IDX_10 = 10;

    /** Index 11 */
    public static final int IDX_11 = 11;

    /** There are error data in this file. Please download a CSV file and correct an error. */
    public static final String MSG_ID_NSAM0205E = "NSAM0205E";

    /** System Error : @ */
    public static final String MSG_ID_NSAM0219E = "NSAM0219E";

    /** The file with other than the file extension "csv" "txt" can't be uploaded. */
    public static final String ZYEM0096E = "ZYEM0096E";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    // START 2017/07/11 M.Kidokoro [QC#18659,19534, ADD]
    /** String CMN_Close */
    public static final String EVENT_CMN_CLOSE = "CMN_Close";

    // END 2017/07/11 M.Kidokoro [QC#18659,19534, ADD]

    private static List<String> createImmutableList(String... elements) {
        List<String> tmp = new ArrayList<String>();
        for (String element : elements) {
            tmp.add(element);
        }
        return Collections.unmodifiableList(tmp);
    }
}
