/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610.constant;

/**
 * <pre>
 * Resource Search  NMAL2610Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/04    Fujitsu         J.Sakamoto      Create          N/A
 * 2015/12/08    Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/05    Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/03/02    Fujitsu         M.suzuki        Update          CSA-QC#4553
 * 2015/03/11    Fujitsu         C.Yokoi         Update          CSA-QC#5346
 * 2016/07/27    Fujitsu         C.Yokoi         Update          CSA-QC#11453
 * 2016/10/06    Hitachi         Y.Takeno        Update          QC#13431
 * 2017/03/02    Fujitsu         R.Nakamura      Update          QC#15878
 * 2017/12/05    Fujitsu         N.Sugiura       Update          QC#21270
 * 2018/09/21    Fujitsu         S.Kosaka        Update          QC#27726
 * </pre>
 */
public class NMAL2610Constant {
    /** Business ID */
    public static final String BIZ_ID = "NMAL2610";

    /** Function Cd */
    public static final String FUNCTION_CD = "20";

    /** Function Cd */
    public static final String FUNCTION_CD_SUBMIT = "40";

    /** ScreenID */
    public static final String SCREEN_ID = "NMAL2610Scrn00";

    /** User Security Update */
    public static final String FUNCID_UPD = "NMAL2610T020";

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

    /** Business button Copy Territory */
    public static final String[] BTN_COPY_TERRITORY = {"Link_CopyTerritory", "Link_CopyTerritory" };

    /** Business button Copy Territory */
    public static final String[] BTN_OPEN_WIN_TERRITORY_DETAIL = {"OpenWin_Territory_Detail", "OpenWin_Territory_Detail" };

    /** Business button Copy Territory */
    public static final String[] BTN_FILTER = {"Filter", "Filter" };

    /** Button Add */
    public static final String BTN_INSERT_ROW_TERRITORY = "InsertRow_Territory";

    /** Button Delete */
    public static final String BTN_DELETE_ROW_TERRITORY = "DeleteRow_Territory";

    /** Button Add */
    public static final String BTN_INSERT_ROW_RULES = "InsertRow_Rules";

    /** Button Delete */
    public static final String BTN_DELETE_ROW_RULES = "DeleteRow_Rules";

    /** Button Add */
    public static final String BTN_INSERT_ROW_RESOURCE = "InsertRow_Resource";

    /** Button Delete */
    public static final String BTN_DELETE_ROW_RESOURCE = "DeleteRow_Resource";

    // QC#13431
    /** Button Attachments */
    public static final String BTN_ATTACHMENTS = "OpenWin_Attachments";

    /** Tab Territory */
    public static final String TAB_TERRITORY = "Relation";

    /** Tab Rules */
    public static final String TAB_TERRITORY_RULES = "Rules";

    /** Tab Resource */
    public static final String TAB_RESOURCE_ASSIGNE = "Resource";

    /** Copy Territory */
    public static final String EVENT_NAME_LINK_COPY_TERRITORY = "Link_CopyTerritory";

    /** Button Open Window Target From */
    public static final String BTN_OPENWIN_TARGET_FROM = "OpenWin_TargetFrom";

    /** Button Open Window Target To */
    public static final String BTN_OPENWIN_TARGET_TO = "OpenWin_TargetTo";

    /** Pop-up Type Common NWAL1130 */
    public static final String POPUP_TYPE_GEN = "01";

    /** Pop-up Type Account Search NMAL7660 */
    public static final String POPUP_TYPE_ACCT = "02";

    /** Pop-up Type Generic NMAL6050 */
    public static final String POPUP_TYPE_CMN = "03";

    /** Pop-up Flag From */
    public static final String POPUP_BTN_FLG_FROM = "0";

    /** Pop-up Flag To */
    public static final String POPUP_BTN_FLG_TO = "1";

    /** Pop-up Type None */
    public static final String POPUP_TYPE_NONE = "99";

    /** Conect Value Search */
    public static final String CON_VAL_SEARCH = " Search";

    /** Conect Value Code */
    public static final String CON_VAL_CODE = " Code";

    /** Conect Value Name */
    public static final String CON_VAL_NAME = " Name";

    /** NMAL6760 Param Nume */
    public static final int NAML6760_PRM_NUM = 35;
    // Add End 2017/03/02 QC#15878

    /** xxChkBox_A1 **/
    public static final String CHKBOX_A = "xxChkBox_A1";

    /** xxChkBox_C1 **/
    public static final String CHKBOX_C = "xxChkBox_C1";

    /** xxChkBox_D1 **/
    public static final String CHKBOX_D = "xxChkBox_D1";

    /** Button T Header */
    public static final String OPEN_WIN_TERRITORY_LOOKUP = "0";

    /** Button T Detail */
    public static final String OPEN_WIN_TERRITORY_LOOKUP_DETAIL = "1";

    /** Button T Detail */
    public static final String LINK_COPY_TERRITORY = "2";

    /** Popup parameter */
    public static final Integer POP_PAR_0 = 0;

    /** Popup parameter */
    public static final Integer POP_PAR_1 = 1;

    /** Popup parameter */
    public static final Integer POP_PAR_2 = 2;

    /** Popup parameter */
    public static final Integer POP_PAR_3 = 3;

    /** Popup parameter */
    public static final Integer POP_PAR_4 = 4;

    /** Popup parameter */
    public static final Integer POP_PAR_5 = 5;

    /** Popup parameter */
    public static final Integer POP_PAR_6 = 6;

    /** Popup parameter */
    public static final Integer POP_PAR_7 = 7;

    /** Popup parameter */
    public static final Integer POP_PAR_8 = 8;

    /** Popup parameter */
    public static final Integer POP_PAR_9 = 9;

    /** Popup parameter */
    public static final Integer POP_PAR_10 = 10;

    /** Screen Name for Upper Tab : "Trty Admin"  */
    public static final String UPPER_TAB_SCREEN_NAME = "Trty Admin";

    /** Warning Kind */
    public static final String MESSAGE_KIND_WARN = "W";

    /** Error Kind */
    public static final String MESSAGE_KIND_ERR = "E";

    /** Info Kind */
    public static final String MESSAGE_KIND_INF = "I";

    /** Copied From */
    public static final String MESSAGE_KIND_COPY = "Copied From ";

    /** MSG */
    public static final String ZZZM9007E = "ZZZM9007E";

    /** MSG */
    public static final String ZZZM9010E = "ZZZM9010E";

    /** Registered data cannot be deleted. */
    public static final String NMAM8230E = "NMAM8230E";

    /**
     * Press Submit button to confirm deletion of the record. Data
     * will be permanently deleted.
     */
    public static final String NMAM8291W = "NMAM8291W";

    /** Please check at least 1 checkbox. */
    public static final String NZZM0011E = "NZZM0011E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] is not selected. */
    public static final String NMAM0014E = "NMAM0014E";

    /** There is no territory to filter.Please filter after territory shows by pressing search button. */
    public static final String NMAM8395E = "NMAM8395E";

    // 2016/07/27 CSA-QC#11453 Add Start
    /** [@] process ended normally. */
    public static final String NMAM8182I = "NMAM8182I";

// 2017/12/05 QC#21270 Del Start
//    /** @ name already exists, to register a new @, please 'Clear' and input a unique @ name. */
//    public static final String NMAM8635I = "NMAM8635I";
// 2017/12/05 QC#21270 Del End

    /** Search */
    public static final String SEARCH = "Search";

    /** Territory */
    public static final String NAME_FOR_MESSAGE_TERRITORY = "Territory";
    // 2016/07/27 CSA-QC#11453 Add End

    /** Territory ID */
    public static final String NAME_FOR_MESSAGE_ORG_CD = "Territory ID";

    /** Business Area */
    public static final String NAME_FOR_MESSAGE_BIZ_AREA_ORG_CD = "Business Area";

    /** Territory Type */
    public static final String NAME_FOR_MESSAGE_TRTY_TP_CD = "Territory Type";

    /** Territory Name */
    public static final String NAME_FOR_MESSAGE_ORG_NM = "Territory Name";

    /** Rank */
    public static final String NAME_FOR_MESSAGE_ORG_TIER_CD = "Rank";

    /** Territory Description */
    public static final String NAME_FOR_MESSAGE_ORG_DESC_TXT = "Territory Description";

    /** Territory Group */
    public static final String NAME_FOR_MESSAGE_TRTY_GRP_TP_CD = "Territory Group";

    /** Start Date */
    public static final String NAME_FOR_MESSAGE_EFF_FROM_DT = "Start Date";

    /** End Date */
    public static final String NAME_FOR_MESSAGE_EFF_THRU_DT = "End Date";

    /** Filter */
    public static final String NAME_FOR_MESSAGE_ORG_NM_FILTER = "Filter";

    /** Tab Name */
    public static final String TAB_NAME = "Trty Admin";

    // 2016/03/02 S21_NA#4553 Add Start --------------
    /** Parent Territory Name */
    public static final String NAME_FOR_MESSAGE_ORG_NM_A1 = "Parent Territory Name";

    /** Child Territory Name */
    public static final String NAME_FOR_MESSAGE_ORG_NM_B1 = "Child Territory Name";

    /** Rule Type */
    public static final String NAME_FOR_MESSAGE_TRTY_RULE_TP_CD = "Rule Type";

    /** Operator */
    public static final String NAME_FOR_MESSAGE_TRTY_RULE_OPRD_TP_CD = "Operator";

    /** Value From */
    public static final String NAME_FOR_MESSAGE_TRTY_RULE_FROM_VAL_TXT = "Value From";

    /** Value To */
    public static final String NAME_FOR_MESSAGE_TRTY_RULE_THRU_VAL_TXT = "Value To";

    /** Logic */
    public static final String NAME_FOR_MESSAGE_TRTY_RULE_LOGIC_TP_CD = "Logic";

    /** Resource Name */
    public static final String NAME_FOR_MESSAGE_XX_PSN_NM = "Resource Name";

    /** Employee ID */
    public static final String NAME_FOR_MESSAGE_PSN_CD = "Employee ID";

    /** Resource Role in Territory */
    public static final String NAME_FOR_MESSAGE_ACCT_TEAM_ROLE_TP_CD = "Resource Role in Territory";
    // 2016/03/02 S21_NA#4553 Add End --------------

    // QC#13431 add start
    /** Attachment : attachment func name */
    public static final String ATT_FUNC_NAME = "Territory Master Attachments";

    /** Attachment : primary key */
    public static final String ATT_KEY_NAME = "Territory ID";

    /** Attachment : document type list (table) */
    public static final String TABLE_NAME_ATT_DOC_TP = "ORG_ATT_DOC_TP";

    /** Attachment : attachments limit */
    public static final String NUM_CONST_KEY_ATT_LIMIT_NUM = "NMAL2610_PARAM_ATT_LIMIT_NUM";

    /** Attachment : authorize file volume */
    public static final String NUM_CONST_KEY_ATT_DATA_VOL = "NMAL2610_PARAM_ATT_DATA_VOL";
    // QC#13431 add end

    // 2018/09/21 QC#27726,ADD Add Start
    /** Screen table name : Build Territory upper table */
    public static final String SCREEN_TABLE_NAME_TERRITORY_UP = "A";

    /** Screen table name : Build HieTerritoryrarchy lower table */
    public static final String SCREEN_TABLE_NAME_TERRITORY_UN = "B";

    /** Screen table name : Territory Rule */
    public static final String SCREEN_TABLE_NAME_TERRITORY_RULE = "C";

    /** Screen table name : Resrc Assign */
    public static final String SCREEN_TABLE_NAME_RESRC = "D";
    // 2018/09/21 QC#27726,ADD Add End
}
