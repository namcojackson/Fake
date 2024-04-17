package business.servlet.NLBL0040.constant;

/**
 * <pre>
 * This class defines the constant used in the screen application
 * program of BusinessID NLBL0040. 
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/28   Fujitsu         D.Fukaya         Create          N/A
 * 2013/05/13   Fujitsu         H.Mizutani       Update          R-OM025 Inventory Transaction
 * </pre>
 */
public interface NLBL0040Constant {
	
    /**
     * Function:Reference
     */
    String FUNCTION_REFERENCE = "NLBL0040T010";

    /**
     * Function:Update
     */
    String FUNCTION_UPDATE = "NLBL0040T020";
    
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
	 * html name value for button[Detail]
	 */
	String HTML_NAME_VALUE_BTN_DETAIL = "OnClick_Detail";

	/**
	 * html name value for [checkbox]
	 */
	String HTML_NAME_VALUE_CHKBOX = "xxChkBox_B1";
	
	/**
	 * html name value for button[Delete Row]
	 */
	String HTML_NAME_VALUE_BTN_DELETE_ROW = "OnClick_DeleteRow";
	
	/**
	 * html name value for button[Insert Row]
	 */
	String HTML_NAME_VALUE_BTN_INSERT_ROW = "OnClick_InsertRow";
	
	/**
	 * html name value for button[Upload]
	 */
	String HTML_NAME_VALUE_BTN_UPLOAD = "OnClick_Upload";
    
	/**
     * html id value for [left table]
     */
    String HTML_ID_VALUE_LEFT_TABLE = "A";

    /**
     * html id value for [right table]
     */
    String HTML_ID_VALUE_RIGHT_TABLE = "B";
    
	/**
	 * html id value for [checkbox]
	 */
	String HTML_ID_VALUE_CHKBOX = "chkBox#";
	
	/**
	 * html id value for button[Delete Row]
	 */
	String HTML_ID_VALUE_BTN_DELETE_ROW = "btn_deleteRow";
	
	/**
	 * html id value for button[Insert Row]
	 */
	String HTML_ID_VALUE_BTN_INSERT_ROW = "btn_InsertRow";
	
	/**
	 * html id value for button[Upload]
	 */
	String HTML_ID_VALUE_BTN_UPLOAD = "btn_Upload";
	
    /**
     * Business Application ID
     */
    String BIZ_APP_ID = "NLBL0040";
    
    /**
     * Screen ID
     */
    String SCRN_ID = "NLBL0040Scrn00";
    
    /**
     * Function Code : Search
     */
    String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    String FUNCTION_CD_UPDATE = "40";
    
    /**
     * pulldown[ST] selection value of "All"
     */
    String ST_LIST_ALL_VALUE = "00";
    
    /**
     * The Maximum date value
     */
    String DATE_MAX_VALUE = "99991231";
    
    /**
     * The maximum display number of lists
     */
    int LIST_MAX_NUM = 1000;
    
    /**
     * date separator
     */
    Character DATE_SEPARATOR = '/';
    
    // =================================================
    // Definition of Message ID
    // =================================================
    String NZZM0004W = "NZZM0004W";
    String NLBM0017E = "NLBM0017E";
    String NLBM0020E = "NLBM0020E";
    String NLBM0029E = "NLBM0029E";
    String NLBM0068E = "NLBM0068E";
    String NLBM0069E = "NLBM0069E";
    String NLBM0070W = "NLBM0070W";
    String NLBM0071W = "NLBM0071W";
    String NLBM0082E = "NLBM0082E";
    String NLBM0083E = "NLBM0083E";
    String NLBM0086E = "NLBM0086E";
    String NLBM0087E = "NLBM0087E";
    String NLBM0088E = "NLBM0088E";
    String ZZM9004E = "ZZM9004E";
    

    // =================================================
    // Screen item name when error message is displayed
    // =================================================
    // 2013/05/13 R-OM025 Inventory Transaction Mod Start
    String NAME_FOR_MESSAGE_LOC_CD = "Loc Cd";
//    String NAME_FOR_MESSAGE_WH = "WH";
    // 2013/05/13 R-OM025 Inventory Transaction Mod End
    String NAME_FOR_MESSAGE_EFF_PER = "Effective Period";
    String NAME_FOR_MESSAGE_EFF_FROM_DT = "Start Date";
    String NAME_FOR_MESSAGE_EFF_THRU_DT = "End Date";
    String NAME_FOR_MESSAGE_ST = "State";
    String NAME_FOR_MESSAGE_LEAD_TM = "Lead Time";
    String NAME_FOR_MESSAGE_FROM_POST_CD = "Post Code From";
    String NAME_FOR_MESSAGE_TO_POST_CD = "Post Code To";
    
    /**
     *  The last character of messageID When the error occurs.
     */
    String MESSAGE_KIND_ERROR = "E";
    
    /**
     * Upload CSV ID
     */
    String Upload_CSV_ID = "NLB0010001";
    
    /**
     * key value to get pagenation table name from session area.
     */
    String PAGING_TABLE_NAME = "xxPagenationTableNm";
    
    /**
     * left table name
     */
    String LEFT_TABLE_NAME = "A";

    /**
     * right table name
     */
    String RIGHT_TABLE_NAME = "B";
}
