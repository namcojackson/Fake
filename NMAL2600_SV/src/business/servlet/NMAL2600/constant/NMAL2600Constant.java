/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2600.constant;

/**
 * <pre>
 * Resource Search  NMAL2400Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/4    Fujitsu         J.Sakamoto      Create          N/A
 * 2015/12/1    Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/11   SRAA            Y.Chen          Update          QC#5311
 * 2016/04/25   Fujitsu         C.Yokoi         Update          CSA-QC#7547
 * 2016/09/21   Fujitsu         Mz.Takahashi    Update          QC#11068
 * </pre>
 */
public class NMAL2600Constant {
    /** Business ID */
    public static final String BIZ_ID = "NMAL2600";

    /** Function Cd */
    public static final String FUNCTION_CD = "20";

    /** ScreenID */
    public static final String SCREEN_ID = "NMAL2600Scrn00";

    /** User Security Update */
    public static final String FUNCID_UPD = "NMAL2600T020";

    /** ReadOnly Function Code **/
    public static final String AUTH_READONLY = "NMAL2600T010";

    /** Edit Function Code **/
    public static final String AUTH_EDIT = "NMAL2600T020";

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

    /** Business button Resource Assignment */
    public static final String[] BTN_RESOURCE_ASSIGNMENT = {"OpenWin_ResourceAssign", "OpenWin_ResourceAssign" };

    /** Business button Show Account */
    public static final String[] BTN_SHOW_ACCOUNT = {"OpenWin_AccountRules", "OpenWin_AccountRules" };

    /** Business button Show Rules */
    public static final String[] BTN_SHOW_RULES = {"OpenWin_TerritoryRules", "OpenWin_TerritoryRules" };

    /** Business button Show Territory */
    public static final String[] BTN_SHOW_TERRITORY = {"OpenWin_Territory", "OpenWin_Territory" };

    /** Button Insert */
    public static final String BTN_INSERT = "InsertRow";

    /** Button Delete */
    public static final String BTN_DELETE = "DeleteRow";

    /** Tab Advanced Search */
    public static final String TAB_ADVANCED_SEARCH = "AdvancedSearch";

    /** MAX TERRITROY HIERARCHY */
    public static final int MAX_TERRITORY_HIERARCHY = 10;

    /** Organization Name */
    public static final String NAME_FOR_MESSAGE_BIZ_AREA_ORG_CD = "Business Area";

    /** Organization Name */
    public static final String NAME_FOR_MESSAGE_TRTY_TP_CD = "Territory Type";

    /** Organization Name */
    public static final String NAME_FOR_MESSAGE_ORG_NM = "Territory Name";

    /** Organization Name */
    public static final String NAME_FOR_MESSAGE_ORG_TIER_CD = "Rank";

    /** Organization Name */
    public static final String NAME_FOR_MESSAGE_PSN_NM = "Resource Name";

    /** Organization Name */
    public static final String NAME_FOR_MESSAGE_TRTY_GRP_CD = "Territory Group";

    /** Organization Name */
    public static final String NAME_FOR_MESSAGE_PSN_CD = "Employee ID";

    /** Organization Name */
    public static final String NAME_FOR_MESSAGE_PSN_NUM = "Resource #";

    /** Organization Name */
    public static final String NAME_FOR_MESSAGE_EFF_FROM_DT = "Start Date";

    /** Organization Name */
    public static final String NAME_FOR_MESSAGE_TRTY_RULE_TP_CD = "Territory Rule Type";

    /** Organization Name */
    public static final String NAME_FOR_MESSAGE_TRTY_RULE_OPRD_CD = "Operand";

    /** Organization Name */
    public static final String NAME_FOR_MESSAGE_TRTY_RULE_FROM_VAL_TXT = "Value From";

    /** Organization Name */
    public static final String NAME_FOR_MESSAGE_TRTY_RULE_THRU_VAL_TXT = "Value To";

    /** Top Territories Only */
    public static final int RADIO_BUTTON_TOP_TERRITORY_ONLY = 1;

    /** All Territories Expanded */
    public static final int RADIO_BUTTON_ALL_TERRITORY_EXPANDED = 2;

    /** Territory & Children Only */
    public static final int RADIO_BUTTON_TERRITORY_AND_CHILDREN_ONLY = 3;

    /** Territory, Children & Territory Rules */
    public static final int RADIO_BUTTON_TERRITORY_CHILDREN_RULES = 4;

    /**
     * TREEVIEW
     */
    public static final String TREEVIEWMOVEFTO = "treeView";

    /** Radio button in Tree view **/
    public static final int COLUMN_INDEX_RADIO_BUTTON = 3;

    /** TREE VIEW BACKGROUND COLOR YELLOW **/
    public static final String BACKGROUND_COLOR_YELLOW = "FFFF00";

    /** The field of [@] is not input. */
    public static final String ZZZM9007E = "ZZZM9007E";

    /** [@] should be smaller than [@]. */
    public static final String ZZZM9010E = "ZZZM9010E";

    /** [@] is not selected. */
    public static final String NMAM0014E = "NMAM0014E";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    // QC#5311
    /** You can not select Root Node */
    public static final String NMAM8386E = "NMAM8386E";

    // QC#11068
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    // QC#7547 Add
    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";
}
