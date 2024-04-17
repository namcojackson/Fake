/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLGL0050.constant;

/**
 * <pre>
 * Ship Via Mapping from WMS to HOST
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/16   CSAI            Y.Miyauchi      Create          MW Replace Initial
 *</pre>
 */
public interface NLGL0050Constant {

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Init
     */
    String EVENT_NM_NLGL0050_INIT = "NLGL0050_INIT";

    /**
     * Event Click List Tab
     */
    String EVENT_NM_ONCLICK_LIST_TAB = "NLGL0050Scrn00_OnClick_CodeListTab";

    /**
     * Event Click Edit Tab
     */
    String EVENT_NM_ONCLICK_EDIT_TAB = "NLGL0050Scrn00_OnClick_CodeEditTab";

    /**
     * Event Click Search
     */
    String EVENT_NM_ONCLICK_SEARCH = "NLGL0050Scrn00_OnClick_Search";

    /**
     * Event Click Page Pre
     */
    String EVENT_NM_PAGE_PRE = "NLGL0050Scrn00_PagePrev";

    /**
     * Event Click Page Next
     */
    String EVENT_NM_PAGE_NEXT = "NLGL0050Scrn00_PageNext";

    /**
     * Event Click Insert Row
     */
    String EVENT_NM_ONCLICK_NEW = "NLGL0050Scrn00_OnClick_New";

    /**
     * Event Click Reset
     */
    String EVENT_NM_CMN_RESET = "NLGL0050Scrn00_CMN_Reset";

    /**
     * Event Click Submit
     */
    String EVENT_NM_CMN_SUBMIT = "NLGL0050Scrn00_CMN_Submit";

    /**
     * Event Click Clear
     */
    String EVENT_NM_CMN_CLEAR = "NLGL0050Scrn00_CMN_Clear";

    /**
     * Event Click Delete
     */
    String EVENT_NM_CMN_DELTE = "NLGL0050Scrn00_CMN_Delete";

    /**
     * DELETE for message
     */
    String DELETE = "DELETE";

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
     * Multiple Details cannot be processed at the same time.
     */
    String NLGM0035E = "NLGM0035E";

    /**
     * It has not been selected. Please select.
     */
    String NLGM0036E = "NLGM0036E";

    /**
     * Error Message NLGM0044E
     */
    String NLGM0044E = "NLGM0044E";

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

    /**
     * No search results found.
     */
    String NLGM0027W = "NLGM0027W";

    // =================================================
    // value of ssm parameter
    // =================================================

    /**
     * DB PARAM glblCmpyCd
     */
    String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB PARAM wmsCarrCd
     */
    String DB_PARAM_WMS_CARR_CD = "wmsCarrCd";

    /**
     * DB PARAM wmsOrgHostId
     */
    String DB_PARAM_WMS_ORG_HOST_ID = "wmsOrgHostId";

    /**
     * DB PARAM wmsTrnspTpCd
     */
    String DB_PARAM_WMS_TRNSP_TP_CD = "wmsTrnspTpCd";

    /**
     * DB PARAM carrScacCd
     */
    String DB_PARAM_CARR_SCAC_CD = "carrScacCd";

    /**
     * DB PARAM Limit Carr Cd List Row Number
     */
    String DB_PARAM_CARR_CD_ROW_NUM = "rownum";

    //  =================================================
    // blank value of db
    // =================================================
    /**
     * DB SETTING BLANK
     */
    String DB_FIELD_BLANK = "";

    // =================================================
    // name of Table
    // =================================================
    /**
     * TABLE NAME WMS_SHIP_VIA
     */
    String TBL_WMS_SHIP_VIA = "WMS_SHIP_VIA";

    // =================================================
    // name of field
    // =================================================
    /**
     * Check box name
     */
    String FIELD_NAME_CHKBOX_A1 = "xxChkBox_A1";

    // =================================================
    // name of column
    // =================================================
    /**
     * Column Name GLBL_CMPY_CD
     */
    String CN_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /**
     * Column Name WMS_ORG_HOST_ID
     */
    String CN_WMS_ORG_HOST_ID = "WMS_ORG_HOST_ID";

    /**
     * Column Name WMS_TRNSP_TP_CD
     */
    String CN_WMS_TRNSP_TP_CD = "WMS_TRNSP_TP_CD";

    /**
     * Column Name WMS_TRNSP_TP_NM
     */
    String CN_WMS_TRNSP_TP_NM = "WMS_TRNSP_TP_NM";

    /**
     * Column Name WMS_CARR_CD
     */
    String CN_WMS_CARR_CD = "WMS_CARR_CD";

    /**
     * Column Name WMS_DESC_NM
     */
    String CN_WMS_DESC_NM = "WMS_DESC_NM";

    //  =================================================
    // other values
    // =================================================
    /**
     * all for pulldown list
     */
    String PULL_DOWN_ALL = "ALL";

    /**
     * delimiter for pulldown list
     */
    String PULL_DOWN_DELIMITER = ":";

    /**
     * delimiter for message
     */
    String MSG_DELIMITER = ",";

    /**
     * count for message
     */
    String MSG_SUCCESS_COUNT = "1";

    /** TAB ID List */
    String TAB_ID_LIST = "xxTabCarrierCode_List";

    /** TAB ID Edit */
    String TAB_ID_EDIT = "xxTabCarrierCode_Edit";

    /**
     * yyyyMMddHHmmSSSSS for EZUptime before
     */
    String YYYYMMDDHHMMSSSSS_BEFORE = "yyyyMMddHHmmSSSSS";

    /**
     * yyyyMMddHHmmSSSSS for EZUptime after
     */
    String YYYYMMDDHHMMSSSSS_AFTER = "MM/dd/yyyy HH:mm:ss";

    /**
     * bussiness error code
     */
    int BIZ_ERR_CD = 1;
}
