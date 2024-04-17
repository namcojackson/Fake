/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL0060.constant;

/**
 * <pre>
 * This interface defines the constant used in the bussiness application
 * program of BusinessID NLBL0060.
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/28   Fujitsu         D.Fukaya         Create          N/A
 * 2013/05/21   CUSA            Mizutani         Update          R-OM025 Inventory Transaction
 * 02/02/2017   CITS            M.Naito          Update          QC#12673
 *</pre>
 */
public interface NLBL0060Constant {
    // 2013/05/21 R-OM025 Inventory Transaction Add Start

    /**
     * BLANK
     */
    String BLANK = "";

    /**
     * Business ID
     */
    String BUSINESS_ID = "NLBL0060";
    // 2013/05/21 R-OM025 Inventory Transaction Add End
	/**
     * Event Name(NLBL0060_INIT)
     */
	String EVENT_NM_NLBL0060_INIT = "NLBL0060_INIT";
    
    /**
     * Event Name(NLBL0060Scrn00_OnClick_Search)
     */
    String EVENT_NM_NLBL0060SCRN00_ONCLICK_SEARCH = "NLBL0060Scrn00_OnClick_Search";

    /**
     * Event Name(NLBL0060Scrn00_PagePrev)
     */
    String EVENT_NM_NLBL0060SCRN00_PAGEPREV = "NLBL0060Scrn00_PagePrev";
    
    /**
     * Event Name(NLBL0060Scrn00_PageNext)
     */
    String EVENT_NM_NLBL0060SCRN00_PAGENEXT = "NLBL0060Scrn00_PageNext";
        
    /**
     * Event Name(NLBL0060Scrn00_OnClick_DeleteRow)
     */
    String EVENT_NM_NLBL0060SCRN00_ONCLICK_DELETEROW = "NLBL0060Scrn00_OnClick_DeleteRow";
    
    /**
     * Event Name(NLBL0060Scrn00_OnClick_InsertRow)
     */
    String EVENT_NM_NLBL0060SCRN00_ONCLICK_INSERTROW = "NLBL0060Scrn00_OnClick_InsertRow";
    
    /**
     * Event Name(NLBL0060Scrn00_CMN_Submit)
     */
    String EVENT_NM_NLBL0060SCRN00_CMN_SUBMIT = "NLBL0060Scrn00_CMN_Submit";
    
    /**
     * Event Name(NLBL0060Scrn00_CMN_Download)
     */
    String EVENT_NM_NLBL0060SCRN00_CMN_DOWNLOAD = "NLBL0060Scrn00_CMN_Download";
    
    /**
     * Event Name(NLBL0060Scrn00_CMN_Reset)
     */
    String EVENT_NM_NLBL0060SCRN00_CMN_RESET = "NLBL0060Scrn00_CMN_Reset";
        
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
    String SSM_ID_GET_WH_LEAD_TM_LIST = "getWHLeadTmList";
    String SSM_ID_GET_EFF_FROM_TO_LIST_FOR_SEARCH_SNAPSHOT = "getEffFromToListForSearchSnapshot";
    String SSM_ID_GET_WH_LEAD_TM_LIST_FOR_SEARCH_SNAPSHOT = "getWHLeadTmListForSearchSnapshot";
    String SSM_ID_GET_WH_LEAD_TM_LIST_FOR_SUBMIT_SNAPSHOT = "getWHLeadTmListForSubmitSnapshot";
    String SSM_ID_GET_WH_COUNT = "getWHCnt";
    String SSM_ID_GET_WH_LEAD_TM_LIST_FOR_UPDATE_INSERT = "getWHLeadTmListForUpdateInsert";
    String SSM_ID_GET_WH_LEAD_TM_LIST_FOR_UPDATE = "getWHLeadTmListForUpdate";
    String SSM_ID_GET_WH_LEAD_TM_LIST_FOR_DELETE_INSERT = "getWHLeadTmListForDeleteInsert";
    String SSM_ID_GET_WH_LEAD_TM_LIST_FOR_DELETE = "getWHLeadTmListForDelete";
    String SSM_ID_GET_CSV_DOWNLOAD_DATA = "getCSVDownloadData";
    
    // =================================================
    // key value to get value from DB search result
    // =================================================
    String WH_CD = "WH_CD";
    String LOC_NM = "LOC_NM";

    // 2013/05/21 R-OM025 Inventory Transaction Delete Start
    /**
     * code value of wh all in pulldown[WH]
     */
    //String ALL_CD_VALUE = "00";
    // 2013/05/21 R-OM025 Inventory Transaction Delete End

    /**
     * code value of wh all in pulldown[WH]
     */

    // 2013/05/21 R-OM025 Inventory Transaction Delete Start
    //String WH_ALL_CD_VALUE = "00";
    // 2013/05/21 R-OM025 Inventory Transaction Delete End
    // 2013/05/21 R-OM025 Inventory Transaction Add Start
    //String WH_ALL_CD_VALUE = "";
    // 2013/05/21 R-OM025 Inventory Transaction Add Start
    
    /**
     * display value of wh all in pulldown[WH]
     */
    // 2013/05/21 R-OM025 Inventory Transaction Delete Start
    //String WH_ALL_DISPLAY_VALUE = "00:ALL";
    // 2013/05/21 R-OM025 Inventory Transaction Delete End
    
    /**
     * list box delimiter
     */
    String LIST_BOX_DELIMITER = ":";
    
    /**
     * date format for item[Shipping Closing Time (24H)]
     */
    String DATE_FORMAT_SHPG_CLO_TM_TS = "HHmm";
    
    /**
     * The maximum display number of table
     */
    int MAX_NUM_OF_TABLE = 200;
    
    /**
     * csv file name
     */
    String CSV_FILE_NM = "NLBL0060_WHLeadTime";
    
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
    
    /**
     * default timezone
     */
    String DEFAULT_TM_ZONE = "EST";
    
    /**
     * date separator
     */
    Character DATE_SEPARATOR = '/';
    
    // =================================================
    // csv header name
    // =================================================
    String CSV_HEADER_WH = "WH";
    String CSV_HEADER_EFFECTIVE_PERIOD_FROM = "Effective Period From";
    String CSV_HEADER_EFFECTIVE_PERIOD_TO = "Effective Period To";
    String CSV_HEADER_SHPG_MODE = "Shipping Mode";
    String CSV_HEADER_SHPG_SVC_LVL = "Service Level";
    String CSV_HEADER_SHPG_CLO_TM_TS = "Shipping Closing Time(24H)";
    String CSV_HEADER_TM_ZONE = "Standard Time";
    String CSV_HEADER_PICK_PACK_AOT = "Lead Time(day(s))";
    
    // =================================================
    // Definition of Message ID
    // =================================================
    String NZZM0000E = "NZZM0000E";
    String NZZM0002I = "NZZM0002I";
    String NLBM0007E = "NLBM0007E";
    String NLBM0008E = "NLBM0008E";
    String NLBM0001E = "NLBM0001E";
    String NLBM0009E = "NLBM0009E";
    String NLBM0024E = "NLBM0024E";
    String NLBM0028E = "NLBM0028E";
    String NLBM0029E = "NLBM0029E";
    String NLBM0030E = "NLBM0030E";
    String NLBM0031E = "NLBM0031E";
    String NLBM0032E = "NLBM0032E";
    String NLBM0080E = "NLBM0080E";
    String NLBM0081E = "NLBM0081E";
    String NLBM0082E = "NLBM0082E";
    String NLBM0083E = "NLBM0083E";
    String NLBM0084E = "NLBM0084E";
    String NLBM0085E = "NLBM0085E";
    String NZZM0001W = "NZZM0001W";
    String ZZM8002I = "ZZM8002I";
    String ZZM8100I = "ZZM8100I";
    String ZZM9000E = "ZZM9000E";
    String NLBM0010I = "NLBM0010I";
    String NLBM0013I = "NLBM0013I";
    String NLBM0006I = "NLBM0006I";
    String NLBM0005I = "NLBM0005I";
    String NLBM1097E = "NLBM1097E";
    String NZZM0007E = "NZZM0007E";

    // =================================================
    // DB access parts return code
    // =================================================
    String DB_ACCESS_PARTS_RETURN_CODE_NORMAL = "0000";
    String DB_ACCESS_PARTS_RETURN_CODE_ABNORMAL = "2300";
    

    // =================================================
    // Screen item name when error message is displayed
    // =================================================
    String NAME_FOR_MESSAGE_WH = "WH";
    String NAME_FOR_MESSAGE_EFF_FROM_DT = "Effective Period Start";
    String NAME_FOR_MESSAGE_EFF_THRU_DT = "Effective Period End";
    String NAME_FOR_MESSAGE_SHPG_CLO_TM_TS = "Shipping Closing Time";
    String NAME_FOR_MESSAGE_PICK_PACK_AOT = "Lead Time";
    
    /**
     * html table name
     */
    String HTML_TABLE_NAME = "A";
    
    /**
     * max value of effective period
     */
    String MAX_VALUE_OF_EFFECTIVE_PERIOD = "99991231";
}
