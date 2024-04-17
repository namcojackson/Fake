/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0030.constant;

/**
 * <pre>
 * ForcedItem Master download
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/16/2013     CSAI            M.Shimamura       Create            MW Replace Initial
 *</pre>
 */
public interface NLGL0030Constant {

    /**
     * value for all wh in data security attribute of wh
     */
    String WH_ALL_VALUE = "*";

    /**
     * delimiter for pulldown list
     */
    String PULL_DOWN_DELIMITER = ":";

    /**
     * code value of "00:All" in pulldown list of wh
     */
    String WH_ALL_SELECTION_VALUE = "00";

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
     * UOM Code CA
     */
    String UOM_CA = "CA";

    /**
     * UOM Code PL
     */
    String UOM_PL = "PL";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event [INIT] Name
     */
    String EVENT_NM_NLGL0030_INIT = "NLGL0030_INIT";

    /**
     * Event [History TAB CLICK] Name
     */
    String EVENT_NM_NLGL0030_ONCLICK_TAB_HIST = "NLGL0030Scrn00_TAB_History";

    /**
     * Event [History SEARCH] Name
     */
    String EVENT_NM_NLGL0030_ONCLICK_HISTSEARCH = "NLGL0030Scrn00_OnClick_HistSrch";

    /**
     * Event [PREV CLICK] Name
     */
    String EVENT_NM_NLGL0030_PAGEPREV = "NLGL0030Scrn00_PagePrev";

    /**
     * Event [NEXT CLICK] Name
     */
    String EVENT_NM_NLGL0030_PAGENEXT = "NLGL0030Scrn00_PageNext";

    /**
     * Event [Download TAB CLICK] Name
     */
    String EVENT_NM_NLGL0030_ONCLICK_TAB_DNLD = "NLGL0030Scrn00_TAB_Download";

    /**
     * Event [Download Search] Name
     */
    String EVENT_NM_NLGL0030_ONCLICK_DNLDSRCH = "NLGL0030Scrn00_OnClick_DnldSrch";

    /**
     * Event [SUBMIT CLICK] Name
     */
    String EVENT_NM_NLGL0030_CMN_SUBMIT = "NLGL0030Scrn00_CMN_Submit";

    /**
     * Event [RESET CLICK] Name
     */
    String EVENT_NM_NLGL0030_CMN_RESET = "NLGL0030Scrn00_CMN_Reset";

    // =================================================
    // name of Table
    // =================================================
    /**
     * table name WMS_MDSE_LIST
     */
    String TBL_WMS_MDSE_LIST = "WMS_MDSE_LIST";

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
     * This merchandise has already been downloaded.
     */
    String NLGM0030W = "NLGM0030W";

    /**
     * This merchandise has not been downloaded yet.
     */
    String NLGM0031W = "NLGM0031W";

    /**
     * You can't download until the error is resolved.
     */
    String NLGM0032W = "NLGM0032W";

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
     * [@] is not found.
     */
    String ZZZM9006E = "ZZZM9006E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    String NZZM0001W = "NZZM0001W";

    /** Item Number */
    String MSG_ITEM_NUMBER = "Item Number";

    /**
     * wh code
     */
    String DB_WH_CD = "WH_CD";

    /**
     * wms_mdse_code code
     */
    String DB_WMS_MDSE_CD = "WMS_MDSE_CD";

    /**
     * description about wh code
     */
    String DB_WMS_DESC_NM = "WMS_DESC_NM";

    // =================================================
    // key value of ssm parameter
    // =================================================
    /**
     * global company code
     */
    String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * gwh code
     */
    String DB_PARAM_WH_CD = "whCd";

    /**
     * wms mdse code
     */
    String DB_PARAM_WMS_MDSE_CD = "wmsMdseCd";

    /**
     * mdse code
     */
    String DB_PARAM_MDSE_CD = "mdseCd";

    /**
     * Limit Row Number
     */
    String DB_PARAM_ROWNUM = "rownum";

    /**
     * global company code
     */
    String DB_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /**
     * whAllVal means ALL(*) on WMS_WH table.
     */
    String DB_WH_ALL_VAL = "whAllVal";

    /**
     * whAllSelval means ALL(00) on WMS_WH PullDown.
     */
    String DB_WH_ALL_SEL_VAL = "whAllSelval";

    /**
     * UOM Code CA
     */
    String DB_PARAM_UOM_CA = "uomCA";

    /**
     * UOM Code PL
     */
    String DB_PARAM_UOM_PL = "uomPL";
}
