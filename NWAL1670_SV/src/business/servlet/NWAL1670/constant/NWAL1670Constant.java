/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1670.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   Hitachi         O.Okuma          Create          N/A
 *</pre>
 */
public class NWAL1670Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1670";

    /** ScreenID */
    public static final String SCREEN_ID = "NWAL1670Scrn00";

    /** Function Id: READ */
    public static final String FUNC_ID_INQ = BIZ_ID + "T010";

    /** Function Id: EDIT */
    public static final String FUNC_ID_EDT = BIZ_ID + "T020";

    /** Function Code: Inquiry */
    public static final String EZD_FUNC_CD_INQ = "20";

    /** Function Code: Update */
    public static final String EZD_FUNC_CD_UPD = "40";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    // --------------------------------
    // button Name
    // --------------------------------
    /** Button name attribute: Search */
    public static final String BTN_SEARCH = "Search";

    /** Button name attribute: Add_Team */
    public static final String BTN_ADD_TEAM = "Add_Team";

    /** Button name attribute: Del_Team */
    public static final String BTN_DEL_TEAM = "Del_Team";

    /** Button name attribute: Copy_Team */
    public static final String BTN_CPY_TEAM = "Copy_Team";

    /** Button name attribute: Add_Member */
    public static final String BTN_ADD_MMBR = "Add_Member";

    /** Button name attribute: Del_Member */
    public static final String BTN_DEL_MMBR = "Del_Member";

    /** Button name attribute: OpenWin_ResourceLookup */
    public static final String BTN_OPEN_WIN_NMAL2570 = "OpenWin_ResourceLookup";

    /** Button name attribute: Add_Attribute */
    public static final String BTN_ADD_ATTR = "Add_Attribute";

    /** Button name attribute: Del_Attribute */
    public static final String BTN_DEL_ATTR = "Del_Attribute";

    /** Button name attribute: OpenWin_Attribute */
    public static final String BTN_OPEN_WIN_ATTR = "OpenWin_Attribute";

    // --------------------------------
    // Other
    // --------------------------------
    /** Parameters length Resource Look up Pop up: 3 */
    public static final int PRM_LENGTH_NMAL2570 = 3;

    /** Parameters length Account Search(Popup): 24 */
    public static final int PRM_LENGTH_NMAL6760 = 24;

    /** Parameters length Common Code Pop Up: 11 */
    public static final int PRM_LENGTH_NMAL6050 = 11;

    /** Parameter Display Hirarchey Account All: 01 */
    public static final String PRM_DISP_HIRARCHEY_ACCT = "01";

    /** screen transition condition to Common Code Pop Up : 0 */
    public static final String SCRRN_TRANS_COND_NMAL6050 = "0";

    /** screen transition condition to Account Search(Popup) */
    public static final String SCRRN_TRANS_COND_NMAL6760 = "1";
}
