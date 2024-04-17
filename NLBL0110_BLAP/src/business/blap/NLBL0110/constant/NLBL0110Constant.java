package business.blap.NLBL0110.constant;

/**
 * <pre>
 * This interface defines the constant used in the bussiness application
 * program of BusinessID NLBL0110.
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/02/04   Fujitsu         S.Uehara        Create          N/A
 * 2013/05/24   Fujitsu         T.Tozuka        Create          R-OM025 Inventory Transaction
 *</pre>
 */
public interface NLBL0110Constant {

    /**
     * Event Name(NLBL0110_INIT)
     */
    String EVENT_NM_NLBL0110_INIT = "NLBL0110_INIT";

    /**
     * Event Name(NLBL0110Scrn00_OnClick_Search)
     */
    String EVENT_NM_NLBL0110SCRN00_SEARCH = "NLBL0110Scrn00_Search";

    /**
     * Event Name(NLBL0110Scrn00_OnClick_DeleteRow)
     */
    String EVENT_NM_NLBL0110SCRN00_VIEW = "NLBL0110Scrn00_View";

    /**
     * Event Name(NLBL0110Scrn00_CMN_Submit)
     */
    String EVENT_NM_NLBL0110SCRN00_CMN_SUBMIT = "NLBL0110Scrn00_CMN_Submit";

    /**
     * Event Name(NLBL0110Scrn00_CMN_Reset)
     */
    String EVENT_NM_NLBL0110SCRN00_CMN_RESET = "NLBL0110Scrn00_CMN_Reset";

    /**
     * data security value of wh all
     */
    String WH_ALL_VALUE = "*";

    /**
     * key value of ssm parameter
     */
    String SSM_PARAM_SMSG = "sMsg";

    /**
     * key value of ssm parameter
     */
    String SSM_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * key value of ssm parameter
     */
    String SSM_PARAM_WH_CD = "whCd";

    /**
     * key value of ssm parameter
     */
    String SSM_PARAM_DATA_SECULITY_ATTR_LIST = "dataSecurityAttrList";

    /**
     * ssm statement id
     */
    String SSM_ID_GET_WHLIST = "getWHList";

    /**
     * ssm statement id
     */
    String SSM_ID_GET_WH_BIZ_DAYS_END_OF_MONTH = "getWHBizDaysOfEndOfMonth";

    /**
     * ssm statement id
     */
    String SSM_ID_GET_CAL_TP_CD = "getCalTpCd";

    /**
     * ssm statement id
     */
    String SSM_ID_GET_WH_END_MTH_FOR_UPDATE = "getWHEndMthForUpdate";

    /**
     * Message Code
     */
    String NLBM0010I  = "NLBM0010I";

    /**
     * Message Code
     */
    String ZZM8002I = "ZZM8002I";

    /**
     * Message Code
     */
    String NLBM0009E  = "NLBM0009E";

    /**
     * Message Code
     */
    String NLBM0006I  = "NLBM0006I";

    /**
     * Message Code
     */
    String NLBM0024E = "NLBM0024E";

    /**
     * Warehouse is not available
     */
    String NMXM0004 = "NMXM0004";

    /**
     * key value to get value from DB search result
     */
    String WH_CD = "WH_CD";

    /**
     * key value to get value from DB search result
     */
    String LOC_NM = "LOC_NM";

    /**
     * code value of wh all in pulldown[WH]
     */
    String WH_ALL_CD_VALUE = "00";

    /**
     * display value of wh all in pulldown[WH]
     */
    String WH_ALL_DISPLAY_VALUE = "00:ALL";

    /**
     * list box delimiter
     */
    String LIST_BOX_DELIMITER = ":";

    /**
     * The maximum display number of table
     */
    int MAX_NUM_OF_TABLE = 40;

    /**
     * DB access parts return code
     */
    String DB_ACCESS_PARTS_RETURN_CODE_NORMAL = "0000";

    /**
     * DB access parts return code
     */
    String DB_ACCESS_PARTS_RETURN_CODE_NODATE = "2000";

    /**
     * DB access parts return code
     */
    String DB_ACCESS_PARTS_RETURN_CODE_DUPLICATE = "2300";

    /**
     * Zero for padding
     */
    String PADDING_ZERO = "0";

    /**
     * First day of Month
     */
    String FIRST_DAY = "01";

    /**
     * Display month For View
     */
    int DISP_MONTH = 12;

    /**
     * Business ID
     */
    String BUSINESS_ID = "NLBL0110";
}
