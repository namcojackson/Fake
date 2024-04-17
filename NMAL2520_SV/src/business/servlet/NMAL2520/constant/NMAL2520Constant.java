/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520.constant;

/**
 * <pre>
 * Resource Search  NMAL2400Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/4    Fujitsu         J.Sakamoto      Create          N/A
 * 2015/11/10   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/02/05   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/07/27   Fujitsu         C.Yokoi         Update          CSA-QC#11453
 * 2016/10/06   Hitachi         Y.Takeno        Update          CSA-QC#13431
 * 2017/12/05   Fujitsu         N.Sugiura       Update          QC#21270
 * 2018/09/20   Fujitsu         S.Kosaka        Update          QC#27724
 * </pre>
 */
public class NMAL2520Constant {
    /** Business ID */
    public static final String BIZ_ID = "NMAL2520";

    /** Business ID */
    public static final String FUNCTION_CD = "20";

    /** Business ID */
    public static final String FUNCTION_CD_SUBMIT = "40";

    /** ScreenID */
    public static final String SCREEN_ID = "NMAL2520Scrn00";

    /** Tab Name */
    public static final String TAB_NAME = "OrgStruMaint";

    /** ReadOnly Function Code **/
    public static final String AUTH_READONLY = "NMAL2520T010";

    /** Edit Function Code **/
    public static final String AUTH_EDIT = "NMAL2520T020";

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

    /** Common Button : Close */
    public static final String BTN_CMN_CLOSE = "CMN_Close";

    /** Business button Search */
    public static final String[] BTN_SEARCH = {"Search", "Search" };

    /** NMAL2500_EVENT_NAME_ADD_PARENT */
    public static final String NMAL2500_EVENT_NAME_ADD_PARENT = "Add_Parent";

    /** Button Add */
    public static final String BTN_INSERT_HIERARCHY = "Insert_Row_Hierarchy";

    /** Button Delete */
    public static final String BTN_DELETE_HIERARCHY = "Delete_Row_Hierarchy";

    /** Button Insert */
    public static final String BTN_INSERT_RESRC = "Insert_Row_Resrc";

    /** Button Delete */
    public static final String BTN_DELETE_RESRC = "Delete_Row_Resrc";

    /** Button ORG Header */
    public static final String OPEN_WIN_ORGANIZATION_LOOKUP = "0";

    /** Button ORG Detail */
    public static final String OPEN_WIN_ORGANIZATION_LOOKUP_DETAIL = "1";

    /** Button Add Child in NMAL2500 */
    public static final String ADD_CHILD_FROM_NMAL2500 = "2";

    // QC#13431
    /** Button Attachments */
    public static final String BTN_ATTACHMENTS = "OpenWin_Attachments";

    /** xxChkBox_A1 **/
    public static final String CHKBOX_A = "xxChkBox_A1";

    /** xxChkBox_A1 **/
    public static final String CHKBOX_C = "xxChkBox_C1";

    /**
     * Define Tab Name
     */
    /** BUILD HIERARCHY TAB **/
    public static final String TAB_BUILD_HIERARCHY = "Build";

    /** VIEW HIERARCHY TAB **/
    public static final String TAB_VIEW_HIERARCHY = "View";

    /** RESRC ASIGN TAB **/
    public static final String TAB_RESRC_ASIGN = "Asign";

    /**
     * TREEVIEW
     */
    public static final String TREEVIEWMOVEFTO = "treeView";

    /** BACKGROUND COLOR YELLOW **/
    public static final String BACKGROUND_COLOR_YELLOW = "FFFF00";

    /** Radio button index **/
    public static final int COLUMN_INDEX_RADIO_BUTTON = 1;

    // =================================================
    // Display character string for message
    // =================================================
    /** Business Area **/
    public static final String NAME_FOR_MESSAGE_BUSINESS_AREA = "Business Area";

    /** Organization ID **/
    public static final String NAME_FOR_MESSAGE_ORGANIZATION_ID = "Organization ID";

    /** Line of Business **/
    public static final String NAME_FOR_MESSAGE_LINE_OF_BUSINESS = "Line of Business";

    /** Organization Short Name **/
    public static final String NAME_FOR_MESSAGE_ORGANIZATION_SHORT_NAME = "Organization Short Name";

    /** Description **/
    public static final String NAME_FOR_MESSAGE_DESCRIPTION = "Description";

    /** Tier (LEVEL) **/
    public static final String NAME_FOR_MESSAGE_TIER_LEVEL = "Tier (LEVEL)";

    /** Tier Description **/
    public static final String NAME_FOR_MESSAGE_TIER_DESCRIPTION = "Tier Description";

    /** End Date **/
    public static final String NAME_FOR_MESSAGE_EFF_THRU_DT = "End Date";

    /** CSR Region **/
    public static final String NAME_FOR_MESSAGE_CSR_REGION = "CSR Region";

    /** Auto Estimate **/
    public static final String NAME_FOR_MESSAGE_AUTO_ESTIMATE = "Auto Estimate";

    /** Organization Name **/
    public static final String NAME_FOR_MESSAGE_ORG_NM = "Organization Name";

    /** Start Date **/
    public static final String NAME_FOR_MESSAGE_EFF_FROM_DT = "Start Date";

    /** Tier **/
    public static final String NAME_FOR_MESSAGE_ORG_TIER_CD = "Tier";

    /** Active Assignments Only **/
    public static final String NAME_FOR_MESSAGE_ACTIVE_ASSIGNMENTS_ONLY = "Active Assignments Only";

    // 2016/07/27 CSA-QC#11453 Add Start
    // 2017/12/05 QC#21270 Del Start
    //  /** Organization */
    //  public static final String NAME_FOR_MESSAGE_ORGANIZATION = "Organization";
    // 2017/12/05 QC#21270 Del End

    /** Search */
    public static final String NAME_FOR_MESSAGE_SEARCH = "Search";
    // 2016/07/27 CSA-QC#11453 Add End

    /** Warning Kind */
    public static final String MESSAGE_KIND_WARN = "W";

    /** Error Kind */
    public static final String MESSAGE_KIND_ERR = "E";

    /** Info Kind */
    public static final String MESSAGE_KIND_INF = "I";

    /** MSG */
    public static final String ZZZM9007E = "ZZZM9007E";

    /** MSG */
    public static final String ZZZM9010E = "ZZZM9010E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Please check at least 1 checkbox. */
    public static final String NZZM0011E = "NZZM0011E";

    /** Registered data cannot be deleted. */
    public static final String NMAM8230E = "NMAM8230E";

    /**
     * Press Submit button to confirm deletion of the record. Data
     * will be permanently deleted.
     */
    public static final String NMAM8291W = "NMAM8291W";

    // 2016/07/27 CSA-QC#11453 Add Start
    /** [@] process ended normally. */
    public static final String NMAM8182I = "NMAM8182I";
// 2017/12/05 QC#21270 Del Start
//    /** @ name already exists, to register a new @, please 'Clear' and input a unique @ name. */
//    public static final String NMAM8635I = "NMAM8635I";
// 2017/12/05 QC#21270 Del End
    // 2016/07/27 CSA-QC#11453 Add End

 // QC#13431 add start
    /** Attachment : attachment func name */
    public static final String ATT_FUNC_NAME = "Organization Master Attachments";

    /** Attachment : primary key */
    public static final String ATT_KEY_NAME = "Organization ID";

    /** Attachment : document type list (table) */
    public static final String TABLE_NAME_ATT_DOC_TP = "ORG_ATT_DOC_TP";

    /** Attachment : attachments limit */
    public static final String NUM_CONST_KEY_ATT_LIMIT_NUM = "NMAL2520_PARAM_ATT_LIMIT_NUM";

    /** Attachment : authorize file volume */
    public static final String NUM_CONST_KEY_ATT_DATA_VOL = "NMAL2520_PARAM_ATT_DATA_VOL";
// QC#13431 add end

    // START 2017/06/14 J.Kim [QC#18924,ADD]
    /** Insert limit Number */
    public static final int INSERT_LIMIT_NUM = 2000;
    // END 2017/06/14 J.Kim [QC#18924,ADD]

    // 2018/09/20 QC#27724,ADD Add Start
    /** Screen table name : Build Hierarchy upper table */
    public static final String SCREEN_TABLE_NAME_HIERARCHY_UP = "A";

    /** Screen table name : Build Hierarchy lower table */
    public static final String SCREEN_TABLE_NAME_HIERARCHY_UN = "B";

    /** Screen table name : Resrc Assign */
    public static final String SCREEN_TABLE_NAME_RESRC = "C";
    // 2018/09/20 QC#27724,ADD Add End
}
