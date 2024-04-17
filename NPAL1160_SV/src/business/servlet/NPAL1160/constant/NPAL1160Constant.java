/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1160.constant;

/**
 * <pre>
 * Business ID : NPAL1160 PO/Inventory Approval Maintenace
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CITS            R Shimamoto     Create          N/A
 * 03/23/2016   CITS            K.Ogino         Update          QC#5273
 * 11/14/2017   CITS            T.Tokutomi      Create          QC#18689-Sol#303
 * 05/17/2023   Hitachi         T.Kuroda        Update          QC#61211
 * 08/29/2023   Hitachi         M.Kikushima     Update          QC#61590
 *</pre>
 */
public class NPAL1160Constant {

    /**
     * DB Like search char
     */
    public static final String LIKE_SEARCH_CHAR = "%";

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

    // START 2023/08/29 M.Kikushima [QC#61590, ADD]
    /**
     * PARAM PRCH_REQ_TP_CD_1
     */
    public static final String[] PARAM_PRCH_REQ_TP_CD_1 = {"2001", "2002", "2011" };

    /**
     * PARAM PRCH_REQ_TP_CD_2
     */
    public static final String[] PARAM_PRCH_REQ_TP_CD_2 = {"3001", "3002", "3004", "3005", "3007" };

    /**
     * PARAM PRCH_REQ_TP_NM_1
     */
    public static final String[] PRCH_REQ_TP_NM_1 = {"Standard", "Emergency", "ITT Inbound" };

    /**
     * PARAM PRCH_REQ_TP_NM_2
     */
    public static final String[] PRCH_REQ_TP_NM_2 = {"WH Transfer", "Disposal", "Vendor Return", "Refurbishing", "Expense Order" };
    // END 2023/08/29 M.Kikushima [QC#61590, ADD]

    /**
     * Business button Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Business button PagePrev
     */
    public static final String BTN_PAGE_PREV = "PagePrev";

    /**
     * Business button PageNext
     */
    public static final String BTN_PAGE_NEXT = "PageNext";

    /**
     * Business button OPENWIN_MEMBER
     */
    public static final String BTN_OPENWIN_MEMBER = "OpenWin_Member";

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1160";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1160Scrn00";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : Team Tab
     */
    public static final String DISPLAY_TAB_NM_TEAM = "Team";

    /**
     * Display Name : Member Tab
     */
    public static final String DISPLAY_TAB_NM_MEMBER = "Member";

    /**
     * Display Name : Transaction Tab
     */
    public static final String DISPLAY_TAB_NM_TRANSACTION = "Transaction";

    /**
     * Display Name : Location Tab
     */
    public static final String DISPLAY_TAB_NM_LOCATION = "Location";

    /**
     * Display Name : ApvlLimit Tab
     */
    public static final String DISPLAY_TAB_NM_APVLLIMIT = "ApvlLimit";

    /**
     * Display Name : TechThrhd Tab
     */
    public static final String DISPLAY_TAB_NM_TECHTHRHD = "TechThrhd";

    // START 2023/05/17 T.Kuroda [QC#61211, ADD]
    /**
     * Display Name : TechMin Tab
     */
    public static final String DISPLAY_TAB_NM_TECHMIN = "TechMin";
    // END   2023/05/17 T.Kuroda [QC#61211, ADD]

    /**
     * Display Name : Team Table Name
     */
    public static final String DISPLAY_TEAM_TABLE_NAME = "A";

    /**
     * Display Name : Member Table Name
     */
    public static final String DISPLAY_MEMBER_TABLE_NAME = "B";

    /**
     * Display Name : Transaction Table Name
     */
    public static final String DISPLAY_TRANSACTION_TABLE_NAME = "C";

    /**
     * Display Name : Location Table Name
     */
    public static final String DISPLAY_LOCATION_TABLE_NAME = "D";

    /**
     * Display Name : ApvlLimit Table Name
     */
    public static final String DISPLAY_APVLLIMIT_TABLE_NAME = "E";

    /**
     * Add QC#18689-Sol#303
     * Display Name : ApvlLimit Table Name
     */
    public static final String DISPLAY_TECHTHRESHOLD_TABLE_NAME = "K";

    // START 2023/05/17 T.Kuroda [QC#61211, ADD]
    /**
     * Display Name : TechMin Table Name
     */
    public static final String DISPLAY_TECHMIN_TABLE_NAME = "L";
    // END   2023/05/17 T.Kuroda [QC#61211, ADD]

    /**
     * Display Name : OpenWin_Team Btn
     */
    public static final String DISPLAY_BTN_OPENWIN_TEAM = "OpenWin_Team";

    /**
     * Display Name : Team Name
     */
    public static final String DISPLAY_NM_TEAM_NAME = "Team Name";

    /**
     * Display Name : Member Id
     */
    public static final String DISPLAY_NM_MEMBER_ID = "Member Id";

    /**
     * Display Name : Member Name
     */
    public static final String DISPLAY_NM_MEMBER_NAME = "Member Name";

    /**
     * Display Name : Description
     */
    public static final String DISPLAY_NM_TEAM_DESCRIPTION = "Description";

    /**
     * Display Name : Warehouse Code
     */
    public static final String DISPLAY_NM_WAREHOUSE_CODE = "Warehouse Code";

    /**
     * Display Name : Warehouse Name
     */
    public static final String DISPLAY_NM_WAREHOUSE_NAME = "Warehouse Name";

    /**
     * Display Name : Limit Amount
     */
    public static final String DISPLAY_NM_LIMIT_AMOUNT = "Limit Amount";

    /**
     * Display Name : Current Page Number
     */
    public static final String DISPLAY_NM_CURRENT_PAGE_NUMBER = "Current Page Number";
    
    // =================================================
    // Popup Param
    // =================================================
    /**
     * PopUp Param Name: Team Description Name
     */
    public static final String PARAM_NM_TEAM_DESCRIPTION_NAME = "Team Desc Name";

    // =================================================
    // Member Popup Param
    // =================================================
    /**
     * 
     */
    public static final int IDX_0 = 0;

    /**
     * 
     */
    public static final int IDX_1 = 1;

    /**
     * 
     */
    public static final int IDX_2 = 2;

    /**
     * 
     */
    public static final int IDX_3 = 3;

    /**
     * 
     */
    public static final int IDX_4 = 4;

    /**
     * 
     */
    public static final int IDX_5 = 5;

    /**
     * 
     */
    public static final int IDX_6 = 6;

    /**
     * 
     */
    public static final int IDX_7 = 7;

    /**
     * 
     */
    public static final int IDX_10 = 10;

    /**
     * 
     */
    public static final int IDX_11 = 11;

    /**
     * 
     */
    public static final int IDX_20 = 20;

    /**
     * 
     */
    public static final int IDX_30 = 30;

    /**
     * 
     */
    public static final int IDX_60 = 60;

    // =================================================
    // Event Name
    // =================================================
    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NMAM0288E : Please set at least one search criteria.
     */
    public static final String NMAM0288E = "NMAM0288E";

    /**
     * ZZZM9007E : The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

    /**
     * NPAM0009E : Please fill out/select the required field.
     */
    public static final String NPAM0009E = "NPAM0009E";

    /**
     * NAMM0027E : Please Enter @.
     */
    public static final String NAMM0027E = "NAMM0027E";

    /**
     * NPAM1216E : The details to the process are not selected.
     */
    public static final String NPAM1216E = "NPAM1216E";

    // =================================================
    // Function Code
    // =================================================
    /**
     * Reffrence function
     */
    public static final String FUNCTION_UPDATE = "NPAL1160T020";

    /**
     * InsertRow button
     */
    public static final String BTN_INS_ROW = "InsertRow";

    /**
     * DeleteRow button
     */
    public static final String BTN_DEL_ROW = "DeleteRow";

    /**
     * DeleteRow button
     */
    public static final String BTN_COPY = "CopyRow";

    /**
     * file data up button
     */
    public static final String BTN_XX_FILE_DATA_UP = "xxFileData_UP";

    /**
     * Member tab upload button
     */
    public static final String BTN_MEM_UPLOAD = "Upload_Member";

    /**
     * Location tab upload button
     */
    public static final String BTN_LOC_UPLOAD = "Upload_WH";

}
