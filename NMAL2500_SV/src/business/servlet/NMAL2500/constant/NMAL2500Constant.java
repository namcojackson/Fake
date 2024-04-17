/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2500.constant;

/**
 * <pre>
 * Org Resource Search  NMAL2500Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/4    Fujitsu         J.Sakamoto      Create          N/A
 * 2015/12/1    Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/02   Fujitsu         C.Yokoi         Update          S21_NA#2249
 * 2016/03/01   Fujitsu         H.Ikeda         Update          QC#4532
 * 2016/09/21   Fujitsu         Mz.Takahashi    Update          QC#11068
 * 2017/02/23   Fujitsu         K.Ishizuka      Update          QC#16481
 * 2018/11/01   Fujitsu         Hd.Sugawara     Update          QC#29014
 * </pre>
 */
public class NMAL2500Constant {
    /** Business ID */
    public static final String BIZ_ID = "NMAL2500";

    /** Function Code */
    public static final String FUNCTION_CD = "20";

    /** Function Code */
    public static final String FUNCTION_CD_SUBMIT = "40";

    /** ScreenID */
    public static final String SCREEN_ID = "NMAL2500Scrn00";

    /** ReadOnly Function Code **/
    public static final String AUTH_READONLY = "NMAL2500T010";

    /** Edit Function Code **/
    public static final String AUTH_EDIT = "NMAL2500T020";

    /** Common button 1 */
    public static final String[] BTN_CMN_BTN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Common button 2 */
    public static final String[] BTN_CMN_BTN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Common button 3 */
    public static final String[] BTN_CMN_BTN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Common button 4 */
    public static final String[] BTN_CMN_BTN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Common button 5 */
    public static final String[] BTN_CMN_BTN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Common button 6 */
    public static final String[] BTN_CMN_BTN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Common button 7 */
    public static final String[] BTN_CMN_BTN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /** Common button 8 */
    public static final String[] BTN_CMN_BTN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Common button 9 */
    public static final String[] BTN_CMN_BTN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Common button 10 */
    public static final String[] BTN_CMN_BTN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Business button Search */
    public static final String[] BTN_SEARCH = {"Search", "Search" };
    // 2016/02/26 QC#4532 ADD Start
    /** Business button Delete */
    public static final String[] BTN_DELETE = {"Delete", "Delete" };
    // 2016/02/26 QC#4532 ADD End
    /** Business button Search */
    public static final String[] BTN_EDIT_ORGANIZATION = {"Link_Organization", "Link_Organization" };

    /** Business button Search */
    public static final String[] BTN_ADD_CHILD_ORGANIZATION = {"Add_Parent", "Add_Parent" };

    /** Business button Search */
    public static final String[] BTN_EDIT_RESOURCE = {"Link_Resource", "Link_Resource" };

    /** Warning Kind */
    public static final String MESSAGE_KIND_WARN = "W";

    /** Error Kind */
    public static final String MESSAGE_KIND_ERR = "E";

    /** SEARCH MODE SHOW HIERARCHY **/
    public static final String SEARCH_MODE_SHOW_HIERARCHY = "10";

    /** SEARCH MODE QUICK LOOK UP **/
    public static final String SEARCH_MODE_QUICK_RESOURCE_LOOK_UP = "20";

    /** SHOW HIERARCHY **/
    public static final String SHOW_HIERARCHY = "Hierarchy";

    /** QUICK LOOK UP **/
    public static final String QUICK_RESOURCE_LOOK_UP = "QuickLookup";

    /** SHOW HIERARCHY - Display Top Tiers Only **/
    public static final int DISPLAY_TOP_TIERS_ONLY = 1;

    /** SHOW HIERARCHY - Display Org Level & Children Only **/
    public static final int DISPLAY_ORG_LEVEL_CHILDREN_ONLY = 2;

    /** SHOW HIERARCHY - Display Org Level, Children & Resource **/
    public static final int DISPLAY_ORG_LEVEL_CHILDREN_RESOURCE = 3;

    /**
     * SHOW HIERARCHY - Display All Levels Expanded w/Resource
     * Assignment
     **/
    public static final int DISPLAY_ALL_LEVELS_EXPANDED_WITH_RESOURCE_ASSIGNMENT = 4;

    /** BACKGROUND COLOR YELLOW **/
    public static final String BACKGROUND_COLOR_YELLOW = "FFFF00";

    /** Index Radio Button **/
    public static final int COLUMN_INDEX_RADIO_BUTTON = 4;

    // Add Start 2018/11/01 QC#29014
    /** Index Resource#(PSN_NUM) **/
    public static final int COLUMN_INDEX_PSN_NUM = 5;
    // Add End 2018/11/01 QC#29014

    /** Organization Name */
    public static final String NAME_FOR_MESSAGE_ORG_NM = "Organization Name";

    /** Employee ID */
    public static final String NAME_FOR_MESSAGE_PSN_CD = "Employee ID";

    /** Job Code */
    public static final String NAME_FOR_MESSAGE_JOB_TTL_CD = "Job Code";

    /** Job Name */
    public static final String NAME_FOR_MESSAGE_JOB_TTL_NM = "Job Name";

    /** Search Mode */
    public static final String NAME_FOR_MESSAGE_SEARCH_MODE = "Search Mode";

    /** End Date */
    public static final String NAME_FOR_MESSAGE_EFF_THRU_DT = "End Date";

    /** Current Date */
    public static final String NAME_FOR_MESSAGE_CURRENT_DT = "Current Date";

    /** Start Date */
    public static final String NAME_FOR_MESSAGE_EFF_FROM_DT = "Start Date";

    /** Resource Name */
    public static final String NAME_FOR_MESSAGE_RESRC_NM = "Resource Name";

    /** Resource# */
    public static final String NAME_FOR_MESSAGE_RESRC_NUM = "Resource#";

    /** Territory Name */
    public static final String NAME_FOR_MESSAGE_NTRTY_NM = "Territory Name";

    /** The field of [@] is not input. */
    public static final String ZZZM9007E = "ZZZM9007E";

    /** Enter a future date in [@]. */
    public static final String NMAM8157W = "NMAM8157W";

    /** The effective date of the entered data is incorrect. */
    public static final String NMAM0803E = "NMAM0803E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** The value for [@] must be bigger than [@]. */
    public static final String NMAM0044E = "NMAM0044E";

    /** [@] is not selected. */
    public static final String NMAM0014E = "NMAM0014E";

    /** No search result is found. */
    public static final String NMAM0038I = "NMAM0038I";

    // Del Start 2018/11/01 QC#29014
    ///** You are selecting to Resource .  Please select Organization structure . */
    //public static final String NMAM8339E = "NMAM8339E";
    // Del End 2018/11/01 QC#29014

    /** You can not select Root Node. */
    public static final String NMAM8386E = "NMAM8386E";

    // Add Start 2018/11/01 QC#29014
    /** You are selecting to Organization structure .  Please select Resource . */
    public static final String NMAM8695E = "NMAM8695E";
    // Add End 2018/11/01 QC#29014

    // QC#11068
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    
    // QC#16481
    /** A past date cannot be entered into the "Date Effective From". */
    public static final String NMAM0185E = "NMAM0185E";

    /**
     * TREEVIEW
     */
    public static final String TREEVIEWMOVEFTO = "treeView";
}
