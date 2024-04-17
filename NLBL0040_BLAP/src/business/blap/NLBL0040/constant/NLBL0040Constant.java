package business.blap.NLBL0040.constant;

/**
 * <pre>
 * This interface defines the constant used in the bussiness application
 * program of BusinessID NLBL0040.
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/28   Fujitsu         D.Fukaya        Create          N/A
 * 09/09/2010   CSAI            D.Fukaya        Update          360
 * 2013/05/13   Fujitsu         H.Mizutani      Update          R-OM025 Inventory Transaction
 *</pre>
 */
public interface NLBL0040Constant {

    /**
     * BLANK
     */
    String BLANK = "";

    // 2013/05/21 R-OM025 Inventory Transaction Add Start
    /**
     * Business ID
     */
    String BUSINESS_ID = "NLBL0040";
    // 2013/05/21 R-OM025 Inventory Transaction Add End

    
    /**
     * Event Name(NLBL0040_INIT)
     */
	String EVENT_NM_NLBL0040_INIT = "NLBL0040_INIT";


    // 2013/05/21 R-OM025 Inventory Transaction Add Start
    /**
     * Event Name(NLBL0040Scrn00_OnChange_WH)
     */
    String EVENT_NM_NLBL0040SCRN00_ONCLICK_REFLESH = "NLBL0040Scrn00_OnClick_Reflesh_EffectivePeriod";
    // 2013/05/21 R-OM025 Inventory Transaction Add End
    

    // 2013/05/21 R-OM025 Inventory Transaction Delete Start
	/**
     * Event Name(NLBL0040Scrn00_OnChange_WH)
     */
    //String EVENT_NM_NLBL0040SCRN00_ONCHANGE_WH = "NLBL0040Scrn00_OnChange_WH";
    // 2013/05/21 R-OM025 Inventory Transaction Delete End
    
    /**
     * Event Name(NLBL0040Scrn00_OnClick_Search)
     */
    String EVENT_NM_NLBL0040SCRN00_ONCLICK_SEARCH = "NLBL0040Scrn00_OnClick_Search";
    
    /**
     * Event Name(NLBL0040Scrn00_OnClick_Detail)
     */
    String EVENT_NM_NLBL0040SCRN00_ONCLICK_DETAIL = "NLBL0040Scrn00_OnClick_Detail";

    /**
     * Event Name(NLBL0040Scrn00_PagePrev)
     */
    String EVENT_NM_NLBL0040SCRN00_PAGEPREV = "NLBL0040Scrn00_PagePrev";
    
    /**
     * Event Name(NLBL0040Scrn00_PageNext)
     */
    String EVENT_NM_NLBL0040SCRN00_PAGENEXT = "NLBL0040Scrn00_PageNext";
        
    /**
     * Event Name(NLBL0040Scrn00_OnClick_DeleteRow)
     */
    String EVENT_NM_NLBL0040SCRN00_ONCLICK_DELETEROW = "NLBL0040Scrn00_OnClick_DeleteRow";
    
    /**
     * Event Name(NLBL0040Scrn00_OnClick_InsertRow)
     */
    String EVENT_NM_NLBL0040SCRN00_ONCLICK_INSERTROW = "NLBL0040Scrn00_OnClick_InsertRow";
    
    /**
     * Event Name(NLBL0040Scrn00_ZYPL0210)
     */
    String EVENT_NM_NLBL0040SCRN00_ZYPL0210 = "NLBL0040_ZYPL0210";
    
    /**
     * Event Name(NLBL0040Scrn00_CMN_Submit)
     */
    String EVENT_NM_NLBL0040SCRN00_CMN_SUBMIT = "NLBL0040Scrn00_CMN_Submit";
    
    /**
     * Event Name(NLBL0040Scrn00_CMN_Download)
     */
    String EVENT_NM_NLBL0040SCRN00_CMN_DOWNLOAD = "NLBL0040Scrn00_CMN_Download";
    
    /**
     * Event Name(NLBL0040Scrn00_CMN_Reset)
     */
    String EVENT_NM_NLBL0040SCRN00_CMN_RESET = "NLBL0040Scrn00_CMN_Reset";
        
    /**
     * data security value of wh all
     */
    String WH_ALL_VALUE = "*";
    
    // =================================================
    // key value of ssm parameter
    // =================================================
    String SSM_PARAM_CMSG = "cMsg";
    String SSM_PARAM_SMSG = "sMsg";
    String SSM_PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    String SSM_PARAM_WH_CD = "whCd";
    String SSM_PARAM_ST_CD = "stCd";
    String SSM_PARAM_EFF_FROM_DT = "effFromDt";
    String SSM_PARAM_DATA_SECULITY_ATTR_LIST = "dataSecurityAttrList";
    
    // =================================================
    // ssm statement id
    // =================================================
    String SSM_ID_GET_WHLIST = "getWHList";
    String SSM_ID_GET_EFF_FROM_TO_LIST = "getEffFromToList";
    String SSM_ID_GET_EFF_FROM_TO_LIST_FOR_SEARCH_SNAPSHOT = "getEffFromToListForSearchSnapshot";
    String SSM_ID_GET_EFF_FROM_TO_LIST_FOR_SUBMIT_SNAPSHOT = "getEffFromToListForSubmitSnapshot";
    String SSM_ID_GET_ST_LIST = "getStList";
    String SSM_ID_GET_AREA_LEAD_TIME_LIST = "getAreaLeadTmList";
    String SSM_ID_GET_AREA_LEAD_TIME_LIST_NO_VALUE = "getAreaLeadTmListNoValue";
    String SSM_ID_GET_AREA_LEAD_TIME_LIST_FOR_SEARCH_SNAPSHOT = "getAreaLeadTmListForSearchSnapshot";
    String SSM_ID_GET_AREA_LEAD_TIME_LIST_FOR_SUBMIT_SNAPSHOT = "getAreaLeadTmListForSubmitSnapshot";
    String SSM_ID_GET_TRNSP_LT_LIST = "getTrnspLtList";
    String SSM_ID_GET_TRNSP_LT_LIST_FOR_DETAIL_SNAPSHOT = "getTrnspLtListForDetailSnapshot";
    String SSM_ID_GET_TRNSP_LT_LIST_FOR_SUBMIT_SNAPSHOT = "getTrnspLtListForSubmitSnapshot";
    String SSM_ID_GET_AREA_LEAD_TM_LIST_BY_WH_AND_EFF_FROM = "getAreaLeadTmListByWHAndEffFrom";
    String SSM_ID_GET_TRNSP_LT_LIST_BY_EFF_FROM = "getTrnspLtListByEffFrom";
    // 09/09/2010 D.Fukaya append start
    String SSM_ID_GET_TRNSP_LT_LIST_BY_PARTIAL_KEY_FOR_INSERT = "getTrnspLtListByPartialKeyForInsert";
    // 09/09/2010 D.Fukaya append end
    String SSM_ID_GET_CSV_DOWNLOAD_DATA = "getCSVDownloadData";
    String SSM_ID_GET_CSV_DOWNLOAD_DATA_NO_VALUE = "getCSVDownloadDataNoValue";

    
    /**
     * list box delimiter
     */
    String LIST_BOX_DELIMITER = ":";
    
    /**
     * list box delimiter of effective period
     */
    String EFF_FROM_TO_LIST_BOX_DELIMITER = " - ";
   
    // =================================================
    // key value to get value from DB search result
    // =================================================
    String WH_CD = "WH_CD";
    String LOC_NM = "LOC_NM";
    String EFF_FROM_DT = "EFF_FROM_DT";
    String EFF_THRU_DT = "EFF_THRU_DT";
    String ST_CD = "ST_CD";
    String ST_NM = "ST_NM";
    
    /**
     * selection value of "All" in pulldown list[ST]
     */
    String ST_LIST_ALL_VALUE = "00";

    /**
     *  display value of "All" in pulldown list[ST]
     */
    String ST_LIST_ALL_DISPLAY_VALUE = "All";
    
	/**
	 * html name value for [checkbox]
	 */
	String HTML_NAME_VALUE_CHKBOX = "xxChkBox_B1";
	
    /**
     * csv file name
     */
    String CSV_FILE_NM = "NLBL0040TransLT";
    
    /**
     * csv file extention
     */
    String CSV_FILE_EXTENTION = ".csv";
    
    /**
     * csv max count
     */
    int MAX_COUNT_OF_CSV_DOWNLOAD = 65000;
    
    /**
     * date formatt for db
     */
    String DATE_FORMATT_FOR_DB = "yyyyMMdd";
    
    /**
     * date formatt for csv file
     */
    String DATE_FORMATT_FOR_CSV = "MM/dd/yyyy";
    
    // =================================================
    // csv header name
    // =================================================
    String CSV_HEADER_WH = "WH";
    String CSV_HEADER_DESTINATION_STATE_CODE = "Destination State Code";
    String CSV_HEADER_SHIPPING_MODE_CODE = "Shipping Mode Code";
    String CSV_HEADER_EFFECTIVE_PERIOD_FROM = "Effective Period From";
    String CSV_HEADER_EFFECTIVE_PERIOD_TO = "Effective Period To";
    String CSV_HEADER_STATE_LEAD_TIME = "State Lead Time(day(s))";
    String CSV_HEADER_POST_CODE_FROM = "Post Code From";
    String CSV_HEADER_POST_CODE_TO = "Post Code To";
    String CSV_HEADER_ZIP_LEAD_TIME = "Zip Lead Time(day(s))";

    // =================================================
    // Definition of Message ID
    // =================================================
    String NZZM0000E = "NZZM0000E";
    String NZZM0001W = "NZZM0001W";
    String NZZM0002I = "NZZM0002I";
    String NZZM0007E = "NZZM0007E";
    String NLBM0001E = "NLBM0001E";
    String NLBM0009E = "NLBM0009E";
    String NLBM0024E = "NLBM0024E";
    String NLBM0066W = "NLBM0066W";
    String NLBM0067E = "NLBM0067E";
    String NLBM0068E = "NLBM0068E";
    String NLBM0069E = "NLBM0069E";
    String NLBM1097E = "NLBM1097E";
    String ZZM8100I = "ZZM8100I";
    String ZZM9000E = "ZZM9000E";

    // =================================================
    // DB access parts return code
    // =================================================
    String DB_ACCESS_PARTS_RETURN_CODE_NORMAL = "0000";
    String DB_ACCESS_PARTS_RETURN_CODE_ABNORMAL = "2300";
    
    // =================================================
    // Screen item name when error message is displayed
    // =================================================
    String NAME_FOR_MESSAGE_LEAD_TM = "Lead Time";
    String NAME_FOR_MESSAGE_FROM_POST_CD = "Post Code From";
    String NAME_FOR_MESSAGE_TO_POST_CD = "Post Code To";
   

    /**
     * left table name
     */
    String LEFT_TABLE_NAME = "A";

    /**
     * right table name
     */
    String RIGHT_TABLE_NAME = "B";
    
    /**
     * zip sequence number maxlength
     */
    int ZIP_SQ_NUM_MAX_LENGTH = 3;
    
    /**
     * zip sequence number left padding string
     */
    String ZIP_SQ_NUM_LEFT_PADDING_STRING = "0";
}
