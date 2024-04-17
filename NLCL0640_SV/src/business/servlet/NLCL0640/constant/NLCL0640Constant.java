/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0640.constant;

/** 
 * <pre>
 * Business ID : NLCL0640 Tech PI Count
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/09/2016   CITS            Makoto Okigami  Create          N/A
 * 05/09/2018   CITS            Y.Iwasaki       Update          QC#10572
 * 12/17/2019   Fujitsu         T.Ogura         Update          QC#54986
 *</pre>
 */
public class NLCL0640Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NLCL0640";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NLCL0640Scrn00";

    /** Function Inquiry */
    public static final String FUNC_INQUIRY = "NLCL0640T010";

    /** Function Edit */
    public static final String FUNC_EDIT = "NLCL0640T020";

    // =================================================
    // Message Code
    // =================================================
    /**
     * ZYEM0004E: The Upload File is empty or only has a header line, therefore it could not be processed. Please confirm the content of the  Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    /**
     * ZYPM0188E: Only the files with the following extensions can be attached.
     */
    public static final String ZYPM0188E = "ZYPM0188E";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : TECH_LOC_CD
     */
    public static final String DISPLAY_NM_TECH_LOC_CD = "Tech Location";

    /**
     * Display Name : MDSE_CD
     */
    public static final String DISPLAY_NM_MDSE_CD = "Item Number";

    /**
     * Display Name : MDSE_DESC_SHORT_TXT
     */
    public static final String DISPLAY_NM_MDSE_DESC_SHORT_TXT = "Item Description";

    /**
     * Display Name : CNT_INP_QTY
     */
    public static final String DISPLAY_NM_CNT_INP_QTY = "Quantity";

    /**
     * Display Name : SER_NUM
     */
    public static final String DISPLAY_NM_SER_NUM = "Serial";

    // =================================================
    // Business Button Name
    // =================================================
    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    public static final String[] BTN_CMN_BTN_2 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_BTN_9 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Return", "Return" };

    /**
     * Business button Search Mdse Name
     */
    public static final String BTN_SEARCH_MDSE_NAME = "Search_MdseName";

    /**
     * Add Count Item button
     */
    public static final String BTN_ADD_COUNT_ITEM = "Add_CountItem";

    /**
     * Import Count Items button
     */
    public static final String BTN_IMPORT_COUNT_ITEMS = "Import_CountItems";

    // =================================================
    // Popup Param
    // =================================================
    // START 2019/12/17 T.Ogura [QC#54986,DEL]
//    /**
//     * Popup Param: NLCL0650
//     */
//    public static final String POPUP_PARAM_FINISH_DLG_MSG = "Your Physical Inventory counting process has been completed and the results have been submitted to your manager.  Please contact your manager to approve.  Your inventory will not be available until the approval is complete.";
    // END   2019/12/17 T.Ogura [QC#54986,DEL]

    /**
     * Popup Param: NLCL0650
     */
    public static final String POPUP_PARAM_FINISH_DLG_BTN_LABEL = "Close";

    // START 2019/12/17 T.Ogura [QC#54986,DEL]
//    /**
//     * Popup Param: NLCL0650
//     */
//    public static final String POPUP_PARAM_RECOUNT_DLG_MSG = "Variances exists in your submitted counts.  Please recount Items";
    // END   2019/12/17 T.Ogura [QC#54986,DEL]

    /**
     * Popup Param: NLCL0650
     */
    public static final String POPUP_PARAM_RECOUNT_DLG_BTN_LABEL = "ReCount";

    // --------------------------------
    // INV_TP_NM
    // --------------------------------
    /**
     * Show Dialog
     */
    public static final String SHOW_DIALOG = "ShowDialog";

    /**
     * Not Show Dialog
     */
    public static final String NOT_SHOW_DIALOG = "NotShowDialog";

    /**
     * 
     */
    public static final String TEMPLATE_FILE_NAME = "TechPITemplate.csv";

    /**
     * 
     */
    public static final String FILE_EXT_CSV = "csv";

    /**
     * 
     */
    public static final String FILE_EXT_TXT = "txt";

    /**
     * 
     */
    public static final String FILE_EXT_XLS = "xls";

    /**
     * 
     */
    public static final String FILE_EXT_XLSX = "xlsx";

    /**
     * 
     */
    public static final String FILE_EXT_SUPPORTED = "csv,txt,xls,xlsx";
}
