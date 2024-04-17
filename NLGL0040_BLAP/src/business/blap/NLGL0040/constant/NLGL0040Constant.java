/**
 * <pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0040.constant;

/**
 * <pre>
 * Ship Via Mapping from HOST to WMS
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/12/2013     CSAI            N.Sekine          Create             MW Replace Initial
 *</pre>
 */
public interface NLGL0040Constant {

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event [INIT] Name
     */
    String EVENT_NM_NLGL0040SCRN00_INIT = "NLGL0040_INIT";
    /**
     * Event [SEARCH] Name
     */
    String EVENT_NM_NLGL0040SCRN00_ONCLICK_SEARCH = "NLGL0040Scrn00_OnClick_Search";
    /**
     * Event [SHIP VIA LIST TAB CLICK] Name
     */
    String EVENT_NM_NLGL0040SCRN00_ONCLICK_TAB_LIST = "NLGL0040Scrn00_OnClick_Ship_Via_List_Tab";
    /**
     * Event [SHIP VIA EDIT TAB CLICK] Name
     */
    String EVENT_NM_NLGL0040SCRN00_ONCLICK_TAB_EDIT = "NLGL0040Scrn00_OnClick_Ship_Via_Edit_Tab";
    /**
     * Event [NEW CLICK] Name
     */
    String EVENT_NM_NLGL0040SCRN00_ONCLICK_NEW = "NLGL0040Scrn00_OnClick_New";
    /**
     * Event [PREV CLICK] Name
     */
    String EVENT_NM_NLGL0040SCRN00_PAGEPREV = "NLGL0040Scrn00_PagePrev";
    /**
     * Event [NEXT CLICK] Name
     */
    String EVENT_NM_NLGL0040SCRN00_PAGENEXT = "NLGL0040Scrn00_PageNext";
    /**
     * Event [SUBMIT CLICK] Name
     */
    String EVENT_NM_NLGL0040SCRN00_CMN_SUBMIT = "NLGL0040Scrn00_CMN_Submit";
    /**
     * Event [DELETE CLICK] Name
     */
    String EVENT_NM_NLGL0040SCRN00_CMN_DELETE = "NLGL0040Scrn00_CMN_Delete";
    /**
     * Event [CLEAR CLICK] Name
     */
    String EVENT_NM_NLGL0040SCRN00_CMN_CLEAR = "NLGL0040Scrn00_CMN_Clear";
    /**
     * Event [RESET CLICK] Name
     */
    String EVENT_NM_NLGL0040SCRN00_CMN_RESET = "NLGL0040Scrn00_CMN_Reset";
    /**
     * value for all wh in data security attribute of wh
     */
    String WH_ALL_VALUE = "*";
    /**
     * delimiter for pulldown list
     */
    String LIST_BOX_DELIMITER = ":";
    /**
     * delimiter for message
     */
    String MSG_DELIMITER = ",";
    /**
     * count for message
     */
    String MSG_SUCCESS_COUNT = "1";
    /**
     * yyyyMMddHHmmSSSSS for EZUptime before
     */
    String YYYYMMDDHHMMSSSSS_BEFORE = "yyyyMMddHHmmSSSSS";
    /**
     * yyyyMMddHHmmSSSSS for EZUptime after
     */
    String YYYYMMDDHHMMSSSSS_AFTER = "MM/dd/yyyy HH:mm:ss";
    /**
     * DELETE for message
     */
    String DELETE = "DELETE";
    /**
     * bussiness error code
     */
    int BIZ_ERR_CD = 1;
    // =================================================
    // Message ID
    // =================================================
    /**
     * The record cannot be registered. Table Name: [@], Field Name:
     * [@], Key Value: [@]
     */
    String NLGM0007E = "NLGM0007E";
    /**
     * The record cannot be updated. Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    String NLGM0008E = "NLGM0008E";
    /**
     * No search results found.
     */
    String NLGM0027W = "NLGM0027W";
    /**
     * Multiple Details cannot be processed at the same time.
     */
    String NLGM0035E = "NLGM0035E";
    /**
     * It has not been selected. Please select.
     */
    String NLGM0036E = "NLGM0036E";
    /**
     * Exclusive control error of @ table. @ is @.
     */
    String NLGM0048E = "NLGM0048E";
    /**
     * Key Duplication Error. Table Name:  [@], Key Field Name:  [@], Key Value:  [@]
     */
    String NLGM0050E = "NLGM0050E";
    /**
     * The process terminated. Table Name: [@], Created Records: [@]
     */
    String NLGM0001I = "NLGM0001I";
    /**
     * The process terminated. Table Name: [@], Updated Records: [@]
     */
    String NLGM0002I = "NLGM0002I";
    /**
     * The process [@] has been successfully completed.
     */
    String NLGM0025I = "NLGM0025I";
    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    String NZZM0001W = "NZZM0001W";
    // =================================================
    // key value of ssm parameter
    // =================================================
    /**
     * global company code
     */
    String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /**
     * wh code
     */
    String DB_PARAM_WH_CD = "whCd";
    /**
     * Limit Row Number
     */
    String DB_PARAM_ROWNUM = "rownum";
    /**
     * ship via code
     */
    String DB_PARAM_WMS_SHIP_VIA_TP_CD = "wmsShipViaTpCd";
    /**
     * wh code code
     */
    String DB_WH_CD = "WH_CD";
    /**
     * global company code
     */
    String DB_GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /**
     * ship via code
     */
    String DB_WMS_SHIP_VIA_CD = "WMS_SHIP_VIA_CD";
    /**
     * description about ship via code
     */
    String DB_WMS_DESC_NM = "WMS_DESC_NM";
    // =================================================
    // name of Table
    // =================================================
    /**
     * table name WMS_SHIP_VIA_RTE_MAP
     */
    String TBL_WMS_SHIP_VIA_RTE_MAP = "WMS_SHIP_VIA_RTE_MAP";
    // =================================================
    // name of field
    // =================================================
    /**
     * field name WMS_SHIP_VIA_RTE_MAP
     */
    String FIELD_NAME_XXCHKBOX_D1 = "xxChkBox_D1";
}
