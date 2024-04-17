/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510.constant;

/**
 * <pre>
 * Resource Maintenance  NMAL2510Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/4    Fujitsu         J.Sakamoto      Create          N/A
 * 2015/10/19   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/09/19/  SRAA            Y.Chen          Update          QC#14595
 * 2016/10/06   Hitachi         Y.Takeno        Update          QC#13431
 * 2016/10/12   Fujitsu         C.Yokoi         Update          QC#4096
 * 2018/09/14   Fujitsu         S.Kosaka        Update          QC#27723
 * </pre>
 */
public class NMAL2510Constant {
    /** Business ID */
    public static final String BIZ_ID = "NMAL2510";

    /** Business ID */
    public static final String FUNCTION_CD = "20";

    /** Business ID */
    public static final String FUNCTION_CD_SUBMIT = "40";

    /** ScreenID */
    public static final String SCREEN_ID = "NMAL2510Scrn00";

    /** ReadOnly Function Code **/
    public static final String AUTH_READONLY = "NMAL2510T010";

    /** Edit Function Code **/
    public static final String AUTH_EDIT = "NMAL2510T020";

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

    /** Button Insert */
    public static final String BTN_INSERT_HIERARCHY = "Insert_Row_Hierarchy";

    /** Button Delete */
    public static final String BTN_DELETE_HIERARCHY = "Delete_Row_Hierarchy";

    /** Button Insert */
    public static final String BTN_INSERT_TERRITORY = "Insert_Row_Territory";

    /** Button Delete */
    public static final String BTN_DELETE_TERRITORY = "Delete_Row_Territory";

    /** Button Insert */
    public static final String BTN_INSERT_REVENUE = "Insert_Row_Revenue";

    /** Button Delete */
    public static final String BTN_DELETE_REVENUE = "Delete_Row_Revenue";

    // QC#13431
    /** Button Attachments */
    public static final String BTN_ATTACHMENTS = "OpenWin_Attachments";
    
    /** xxChkBox_A1 **/
    public static final String CHKBOX_B = "xxChkBox_B2";

    /** xxChkBox_A1 **/
    public static final String CHKBOX_C = "xxChkBox_C2";

    /** Link Resource# */
    public static final String OPEN_WIN_RESOURCE_LOOKUP = "0";

    /** Link Supervisor ID */
    public static final String OPEN_WIN_RESOURCE_LOOKUP_BY_SUPERVISOR = "1";

    /** TRUE */
    public static final boolean TRUE = true;

    /** FALSE */
    public static final boolean FALSE = false;

    /** COMMMA */
    public static final String COMMMA = ",";

    /** SPACE */
    public static final String SPACE = " ";

    /** COMMMA_SPACE */
    public static final String COMMMA_SPACE = ", ";

    /** Resource # */
    public static final String NAME_FOR_MESSAGE_PSN_NUM = "Resource #";

    /** Employee ID */
    public static final String NAME_FOR_MESSAGE_PSN_CD = "Employee ID";

    /** First Name */
    public static final String NAME_FOR_MESSAGE_PSN_FIRST_NM = "First Name";

    /** Last Name */
    public static final String NAME_FOR_MESSAGE_PSN_LAST_NM = "Last Name";

    /** Type */
    public static final String NAME_FOR_MESSAGE_PSN_TP_CD = "Type";

    /** Employment Date From */
    public static final String NAME_FOR_MESSAGE_EFF_FROM_DT = "Employment Date From";

    /** MSG_COA_CMPY_CD */
    public static final String MSG_COA_CMPY_CD = "COA_CMPY_CD";

    /** MSG_COA_BR_CD */
    public static final String MSG_COA_BR_CD = "COA_BR_CD";

    /** MSG_COA_CC_CD */
    public static final String MSG_COA_CC_CD = "COA_CC_CD";

    /** MSG_COA_EXTN_CD */
    public static final String MSG_COA_EXTN_CD = "COA_EXTN_CD";

    /** [@] is not found. */
    public static final String ZZZM9007E = "ZZZM9007E";

    /** HIERARCHY TAB **/
    public static final String TAB_HIERARCHY = "Hierarchy";

    /** HIERARCHY TAB **/
    public static final String TAB_TERRITORY = "Territory";

    /** HIERARCHY TAB **/
    public static final String TAB_REVENUE = "Revenue";

    /** HIERARCHY TAB **/
    public static final String TAB_MISC = "Misc";

    /** HR supervisor ID */
    public static final int HR_SUPV_ID_LENGTH = 11;

    /** HR supervisor Name */
    public static final int HR_SUPV_NM_LENGTH = 50;

    /** Warning Kind */
    public static final String MESSAGE_KIND_WARN = "W";

    /** Error Kind */
    public static final String MESSAGE_KIND_ERR = "E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Please check at least 1 check box. */
    public static final String NZZM0011E = "NZZM0011E";

    /** Registered data cannot be deleted. */
    public static final String NMAM8230E = "NMAM8230E";

    // 2016/10/12 CSA-QC#4096 Add Start
    /**  @ is required when @ are selected. */
    public static final String NMAM8385E = "NMAM8385E";
    // 2016/10/12 CSA-QC#4096 Add End

    /** Registered data cannot be deleted. */
    public static final String ZZM8100I = "ZZM8100I";

    /**
     * Press Submit button to confirm deletion of the record. Data
     * will be permanently deleted.
     */
    public static final String NMAM8291W = "NMAM8291W";

    // 2016/10/12 CSA-QC#4096 Add Start
    /** Name For Message : Physical Location  */
    public static final String NAME_FOR_MESSAGE_PHYSICAL_LOC = "Physical Location";

    /** Name For Message : GeoCode search popup  */
    public static final String NAME_FOR_MESSAGE_GEOCODE_SRCH = "GeoCode search";
    // 2016/10/12 CSA-QC#4096 Add End

    // QC#14595
    /** Popup close event name */
    public static final String POPUP_CLOSE = "CMN_Close";

    // QC#13431 add start
    /** Attachment : attachment func name */
    public static final String ATT_FUNC_NAME = "Resource Master Attachments";

    /** Attachment : primary key */
    public static final String ATT_KEY_NAME = "Employee ID";

    /** Attachment : document type list (table) */
    public static final String TABLE_NAME_ATT_DOC_TP = "ORG_ATT_DOC_TP";

    /** Attachment : attachments limit */
    public static final String NUM_CONST_KEY_ATT_LIMIT_NUM = "NMAL2510_PARAM_ATT_LIMIT_NUM";

    /** Attachment : authorize file volume */
    public static final String NUM_CONST_KEY_ATT_DATA_VOL = "NMAL2510_PARAM_ATT_DATA_VOL";
    // QC#13431 add end

    // START 2018/09/14 S.Kosaka [QC#27723,ADD]
    /** Screen table name : Hierarchy upper table */
    public static final String SCREEN_TABLE_NAME_HIERARCHY_UP = "A";

    /** Screen table name : Hierarchy under table */
    public static final String SCREEN_TABLE_NAME_HIERARCHY_UN = "A_Right";

    /** Screen table name : Territory */
    public static final String SCREEN_TABLE_NAME_TERRITORY = "B";

    /** Screen table name : Revenue */
    public static final String SCREEN_TABLE_NAME_REVENUE = "C";
    // END 2018/09/14 S.Kosaka [QC#27723,ADD]
}
