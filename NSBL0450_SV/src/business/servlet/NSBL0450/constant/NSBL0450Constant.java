/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0450.constant;

/**
 *<pre>
 * Supplemental Task
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/05   Hitachi         T.Iwamoto         Create          N/A
 *</pre>
 */
public final class NSBL0450Constant {

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
    public static final String[] BTN_CMN_BLANK7 = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Business ID */
    public static final String BUSINESS_ID = "NSBL0450";

    /** Reference authority */
    public static final String AUTH_REFERENCE = "NSBL0450T010";

    /** Update authority */
    public static final String AUTH_UPDATE = "NSBL0450T020";

    /** SCRN_ID : NSBL0450Scrn00 */
    public static final String SCRN_ID = "NSBL0450Scrn00";

    /** Function code (Search) */
    public static final String FUNCTION_SEARCH = "20";

    /** Function code (Submit) */
    public static final String FUNCTION_SUBMIT = "40";

    /** Supplemental Task# */
    public static final String NAME_SUPP_NUM = "Supplemental Task#";

    /** Technician */
    public static final String NAME_TECH = "Technician";

    /** Task Name */
    public static final String NAME_TASK_NM = "Task Name";

    /** Resource Manager */
    public static final String NAME_MANAGER = "Resource Manager";

    /** Creation Date */
    public static final String NAME_CREATE = "Creation Date";

    /** Start Time */
    public static final String NAME_START = "Start Time";

    /** End Time */
    public static final String NAME_END = "End Time";

    /** Travel Time */
    public static final String NAME_TRAVEL = "Travel Time";

    /** Duration */
    public static final String NAME_DURATION = "Duration";

    /** Please set at least one search criteria. */
    public static final String NSBM0012E = "NSBM0012E";

    /** The chronological sequence between the dates is wrong. */
    public static final String NSBM0024E = "NSBM0024E";

    /** @ is a mandatory field. */
    public static final String NSBM0036E = "NSBM0036E";

    /** Time is invalid. Please use a valid format, [@] */
    public static final String NSBM0004E = "NSBM0004E";

    /** Time format */
    public static final String TIME_FORMAT = "hh:mm";

    /** Time format */
    public static final String DAY_TIME_FORMAT = "hh:mm(Less than 24 hours)";

    /** @ is a mandatory field if staus is @. */
    public static final String NSBM0130E = "NSBM0130E";

    /** Supplemental Time cannot exceed 99:59. */
    public static final String NSBM0131E = "NSBM0131E";

    /** status :closed */
    public static final String CLOSED = "Closed";

    /** If [@] is entered, please enter [@], too. */
    public static final String NSBM0134E = "NSBM0134E";

    /** If [@] is entered, please enter [@], too. */
    public static final String END_DT = "End Time(date)";

    /** If [@] is entered, please enter [@], too. */
    public static final String END_TIME = "End Time(time)";

    /** Time format */
    public static final String CHECK_DAY_TIME_FORMAT = "^([0-1][0-9]|2[0-3]):([0-5][0-9])$";

    /** Time format */
    public static final String CHECK_TIME_FORMAT = "^([0-9][0-9]):([0-5][0-9])$";

    /** hour. */
    public static final int HOUR = 3600000;

    /** minute. */
    public static final int MINUTES = 60000;

    /** limit 100hour */
    public static final int LIMIT_100 = 100;

    /** minute. */
    public static final String TS_FORMAT = "yyyyMMddHHmmss";

    /** substring 0 */
    public static final int SUB_0 = 0;

    /** substring 2 */
    public static final int SUB_2 = 2;

    /** substring 3 */
    public static final int SUB_3 = 3;

    /** substring 5 */
    public static final int SUB_5 = 5;

    /** length 5 */
    public static final int LENGTH_5 = 5;

    /** length 7 */
    public static final int LENGTH_7 = 7;

    /** ChangeTime */
    public static final String CHANGE_TIME = "ChangeTime";

    /** OpenWin_Technician */
    public static final String OPENWIN_TECH = "OpenWin_Technician";

    /** OpenWin_Manager */
    public static final String OPENWIN_MANAGER = "OpenWin_Manager";

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
     * PSN_CD Length : 20
     */
    public static final int PSN_CD_LENGTH = 20;

    /**
     * PSN_NM Length : 50
     */
    public static final int PSN_NM_LENGTH = 50;

}
