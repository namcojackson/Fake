/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0060.constant;

/**
 * <pre>
 * This interface defines the constant used in the screen application
 *  program of BusinessID NLBL0020. 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/20   Fujitsu         D.Fukaya         Create          N/A
 * 2013/05/21   CUSA            Mizutani         Update          R-OM025 Inventory Transaction
 *</pre>
 */
public interface NLBL0060Constant {

    /**
     * Function:Reference
     */
    String FUNCTION_REFERENCE = "NLBL0060T010";

    /**
     * Function:Update
     */
    String FUNCTION_UPDATE = "NLBL0060T020";

    /**
     * Common button 1
     */
    String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    String[] BTN_CMN_BTN_2 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    String[] BTN_CMN_BTN_9 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Return", "Return" };

    /**
     * html name value for button[Delete Row]
     */
    String HTML_NAME_VALUE_BTN_DELETE_ROW = "OnClick_DeleteRow";

    /**
     * html name value for button[Insert Row]
     */
    String HTML_NAME_VALUE_BTN_INSERT_ROW = "OnClick_InsertRow";

    /**
     * html id value for [table]
     */
    String HTML_ID_VALUE_TABLE = "A";

    /**
     * html id value for [radio]
     */
    String HTML_ID_VALUE_RADIO = "radio#";

    // 2013/05/21 R-OM025 Inventory Transaction Add Start
    /**
     * html id value for [wh]
     */
    String HTML_ID_VALUE_WH_CD = "whCd#";

    /**
     * html id value for [wh]
     */
    String HTML_ID_VALUE_WH_NM = "whNm#";

    /**
     * html id value for [wh]
     */
    String HTML_ID_VALUE_WH_LINK = "whLink#";
    // 2013/05/21 R-OM025 Inventory Transaction Add End

    /**
     * html id value for [effective period]
     */
    String HTML_ID_VALUE_EFF_PERIOD = "effPer#";

    /**
     * html id value for [shipping mode name]
     */
    String HTML_ID_VALUE_SHPG_MODE_NM = "shpgModeNm#";

    /**
     * html id value for button[Delete Row]
     */
    String HTML_ID_VALUE_BTN_DELETE_ROW = "btn_deleteRow";

    /**
     * html id value for button[Insert Row]
     */
    String HTML_ID_VALUE_BTN_INSERT_ROW = "btn_InsertRow";

    /**
     * html style name for even number record's background color
     */
    String HTML_STYLE_NAME_FOR_EVEN_NUM_BG = "pEvenNumberBGcolor";

    /**
     * Business Application ID
     */
    String BIZ_APP_ID = "NLBL0060";

    /**
     * Screen ID
     */
    String SCRN_ID = "NLBL0060Scrn00";

    /**
     * Function Code : Search
     */
    String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    String FUNCTION_CD_UPDATE = "40";

    /**
     * pulldown[WH] selection value of "All"
     */
    //String WH_LIST_ALL_VALUE = "00";

    /**
     * The maximum display number of table
     */
    int MAX_NUM_OF_TABLE = 200;

    /**
     * The last character of messageID When the error occurs.
     */
    String MESSAGE_KIND_ERROR = "E";

    /**
     * date format for item[Shipping Closing Time (24H)]
     */
    String DATE_FORMAT_SHPG_CLO_TM_TS = "HHmm";

    // =================================================
    // Definition of Message ID
    // =================================================
    String NLBM0001E = "NLBM0001E";

    String NZZM0004W = "NZZM0004W";

    String NLBM0008E = "NLBM0008E";

    String NLBM0070W = "NLBM0070W";

    String NLBM0080E = "NLBM0080E";

    String NLBM0082E = "NLBM0082E";

    String NLBM0083E = "NLBM0083E";

    String NLBM0028E = "NLBM0028E";

    String NLBM1097E = "NLBM1097E";

    // =================================================
    // Screen item name when error message is displayed
    // =================================================
    String NAME_FOR_MESSAGE_WH = "WH";

    String NAME_FOR_MESSAGE_EFF_FROM_DT = "Start Date";

    String NAME_FOR_MESSAGE_EFF_THRU_DT = "End Date";

    String NAME_FOR_MESSAGE_SHPG_CLO_TM_TS = "Shipping Closing Time";

    String NAME_FOR_MESSAGE_PICK_PACK_AOT = "Lead Time";
}
