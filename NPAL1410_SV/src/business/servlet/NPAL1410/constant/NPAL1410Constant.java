/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1410.constant;

/**
 * <pre>
 * Business ID : NPAL1410 Reman Workbench
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 4/05/2016   CITS       Yasushi Nomura       Create          N/A
 * 09/20/2017   CITS            T.Kikuhara      Update          QC#18675(SOL#219)
 *</pre>
 */
public class NPAL1410Constant {
    /**
     * Business Application ID
     */
    public static final String BUSINESS_APPLICATION_ID = "NPAL1410";

    /**
     * Screen ID
     */
    public static final String SCREEN_ID = "NPAL1410Scrn00";

    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

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
    
    /** Function Button 11(Configuration Tab) */
    public static final String[] BTN_ADD_CONFIG = {"btn11", "AddComponent", "Add" };

    /** Function Button 11(Parts Tab) */
    public static final String[] BTN_ADD_PARTS = {"btn11", "AddParts", "Add" };

    /** Function Button 11(Task Tab) */
    public static final String[] BTN_ADD_TASK = {"btn11", "OpenWin_TaskEntryNew", "Add" };

    /** Button Name for F11(Configuration Tab/AddComponent) */
    public static final String BTN_ADD_CONFIG_NAME = "AddComponent";

    /** Button Name for F11(Parts Tab/AddParts) */
    public static final String BTN_ADD_PARTS_NAME = "AddParts";

    /** Button Name for F11(Task Tab/OpenWin_TaskEntryNew) */
    public static final String BTN_ADD_TASK_NAME = "OpenWin_TaskEntryNew";

    /** Disable Button Status */
    public static final String BUTTON_DISABLED_STR="disabled";

    /** Protected Control Mode New */
    public static final String MODE_INIT = "0";

    /** Protected Control Mode Saved edit */
    public static final String MODE_EDIT = "1";

    /** Protected Control Mode */
    public static final String MODE_IN_PROCESS = "2";

    /** Protected Control Mode */
    public static final String MODE_READ_ONLY = "3";

    /** Protected Control Mode */
    public static final String MODE_COMP_VIEW = "9";

    /** NPAL1420 Task Entry Mode */
    public static final String TASK_MODE_NEW = "1";

    /** NPAL1420 Task Entry Mode */
    public static final String TASK_MODE_EDIT = "2";

    /**
     * FUNC_ID_SEARCH
     */
    public static final String FUNC_ID_SEARCH = "NPAL1410T010";

    /**
     * FUNC_ID_SUBMIT
     */
    public static final String FUNC_ID_SUBMIT = "NPAL1410T020";

    /**
     * Tab Configration
     */
    public static final String TAB_CONF = "Config";

    /**
     * Tab Parts
     */
    public static final String TAB_PARTS = "Parts";

    /**
     * Tab Task
     */
    public static final String TAB_TASK = "Task";

    //QC#18675 ADD START
    /**
     * Tab SO/RWS
     */
    public static final String TAB_SO_RWS = "SoRws";
    //QC#18675 ADD END

    // Pop param
    /** . */
    public static final String INVENTORY_ACCOUNT_CODE_INVENTORY = "01";
    /** . */
    public static final String CONFIG_EXST_MODE_CD = "01";
    /** . */
    public static final String MACH_ALLOC_MODE_CODE = "01";

    /** . */
    public static final String OWNER_CODE_CSA = "CSA";

    // Event Name
    /** . */
    public static final String EVENT_NAME_WH_POPUP = "WH_POPUP";

    /** . */
    public static final String EVENT_NAME_SWH_POPUP = "SWH_POPUP";

    /** . */
    public static final String EVENT_NAME_ITEM_CO_POPUP = "ITEM_CO_POPUP";

    /** . */
    public static final String EVENT_NAME_ITEM_CC_POPUP = "ITEM_CC_POPUP";

    /** . */
    public static final String EVENT_NAME_ITEM_H_POPUP = "ITEM_H_POPUP";

    /** . */
    public static final String EVENT_NAME_ITEM_P_POPUP = "ITEM_P_POPUP";

    /** . */
    public static final String EVENT_NAME_TECH_POPUP = "TECH_POPUP";

    /** . */
    public static final String EVENT_NAME_LOC_POPUP = "LOC_POPUP";

    /** . */
    public static final String EVENT_NAME_CONF_POPUP_H = "CONF_POPUP_H";
    /** . */
    public static final String EVENT_NAME_CONF_POPUP_CO = "CONF_POPUP_CO";

    // Message
    /** . */
    public static final String NAMM0027E = "NAMM0027E";

    /** . */
    public static final String NPAM1216E = "NPAM1216E";

    /** . */
    public static final String NPAM0049E = "NPAM0049E";

    /** . */
    public static final String NPAM1536E = "NPAM1536E";

    /** . */
    public static final String NPAM1540I = "NPAM1540I";
}
