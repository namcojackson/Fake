package business.servlet.NLCL0290.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   CSAI            K.Lee           Create          N/A
 * 2018/04/06   CITS            K.Masaki        Update          QC#18472/18490
 *</pre>
 */
public interface NLCL0290Constant {
    /** Function Button */
    String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    // [0]:Button Name [1]:Event Name
    /** Another Button */
    String[] BTN_SEARCH = {"Search", "Search" };

    String[] BTN_NEW = {"New", "New" };

    String[] BTN_DELETE_LINE = {"Delete_Line", "Delete_Line" };

    String[] BTN_OPEN_WIN_ACCOUNT = {"OpenWin_Account", "OpenWin_Account" };

    String[] BTN_OPEN_WIN_SERIAL = {"OpenWin_Serial", "OpenWin_Serial" };

    String[] BTN_IMPORT = {"Import", "Import" };

    String[] BTN_ATTACHMENTS = {"Attachments", "Attachments" };

    // QC:18472 Start
    String SEARCH_RTL_WH_NM = "Search_RtlWHInfo";

    String BTN_ADD_CONFIG = "Add_Config";

    String BTN_SEARCH_ITEM_CD = "OpenWin_Item";

    String BTN_SEARCH_ITEM_NM = "Search_Item";

    String BTN_SEARCH_ACCT_CD = "OpenWin_Account_Detail";
    // QC:18472 End

    /** Screen ID */
    String SCREEN_ID = "NLCL0290Scrn00";

    String APPLICATION_ID = "NLCL0290";

    String TABLE_ID_A_LEFT = "A_Left";

    String TABLE_ID_A_RIGHT = "A_Right";

    // Function ID
    String FUNC_REFER = "NLCL0290T010";

    String FUNC_UPDATE = "NLCL0290T020";

    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    public static final String BUSINESS_APPL_ID = "NLCL0290";

    /** Function Button 11 */
    public static final String[] BTN_ADD_LINE = {"btn11", "Add_Line", "Add Line" };

}
