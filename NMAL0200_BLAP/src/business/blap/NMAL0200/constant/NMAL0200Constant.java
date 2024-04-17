/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0200.constant;

/**
 * <pre>
 * Business ID : NMAL0200 Product Categorization Maintenance
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2016   CITS            K.Ogino         Create          N/A
 * 12/27/2018   Fujitsu         C.Hara          Update          QC#29695
 *</pre>
 */
public class NMAL0200Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NMAL0200";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : INIT
     */
    public static final String EVENT_NM_NMAL0200_INIT = "NMAL0200_INIT";

    /**
     * Event Name : DeleteLine
     */
    public static final String EVENT_NM_NMAL0200_DELETE_LINE = "NMAL0200Scrn00_DeleteLine";

    /**
     * Event Name : CMN_Reset
     */
    public static final String EVENT_NM_NMAL0200_CMN_RESET = "NMAL0200Scrn00_CMN_Reset";

    /**
     * Event Name : CMN_Return
     */
    public static final String EVENT_NM_NMAL0200_CMN_RETURN = "NMAL0200Scrn00_CMN_Return";

    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NMAL0200_CMN_SUBMIT = "NMAL0200Scrn00_CMN_Submit";

    /**
     * Event Name : AddlLine
     */
    public static final String EVENT_NM_NMAL0200_ADD_LINE = "NMAL0200Scrn00_AddLine";

    /**
     * Event Name : PageJump
     */
    public static final String EVENT_NM_NMAL0200_PAGE_JUMP = "NMAL0200Scrn00_PageJump";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NMAL0200_PAGE_NEXT = "NMAL0200Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NMAL0200_PAGE_PREV = "NMAL0200Scrn00_PagePrev";

    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NMAL0200_SEARCH = "NMAL0200Scrn00_Search";

    /**
     * Event Name : EVENT_NM_NMAL0200_TBL_COL_SORT
     */
    public static final String EVENT_NM_NMAL0200_TBL_COL_SORT = "NMAL0200Scrn00_TBLColumnSort";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NMAM0038I: No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * NMAM8181W: Search results exceeded [@]. Only showing first @
     * records.
     */
    public static final String NMAM8181W = "NMAM8181W";

    /**
     * NMAM0050E:Details cannot be added because the number will
     * exceed [@].
     */
    public static final String NMAM0050E = "NMAM0050E";

    /**
     * NMAM8175E:This data has been updated by another user. [
     * TableName = @ , key = @, value = @ ]
     */
    public static final String NMAM8175E = "NMAM8175E";

    /**
     * NMAM8000E:Data to update by Pkey not found.
     * < Table [@] Pkey [@] >
     */
    public static final String NMAM8000E = "NMAM8000E";

    /**
     * NMAM0282E:Data insert failure.@
     */
    public static final String NMAM0282E = "NMAM0282E";

    /**
     * NMAM0836E:[@] field is mandatory.
     */
    public static final String NMAM0836E = "NMAM0836E";

    /**
     * NMAM8509E:The data you entered has already been registered.
     */
    public static final String NMAM8509E = "NMAM8509E";

    /**
     * NMAM8121E:[@] is not exists in master.
     */
    public static final String NMAM8121E = "NMAM8121E";

    /**
     * NMAM8622E:This record can not be deleted because it is being
     * used in the [@].
     */
    public static final String NMAM8622E = "NMAM8622E";

    /**
     * NMAM0011E:[@] is not registered.
     */
    public static final String NMAM0011E = "NMAM0011E";

    /**
     * NMAM0835E:Please select at least 1 check box.
     */
    public static final String NMAM0835E = "NMAM0835E";

    /**
     * The Relationship Code in this row is not registered. Please
     * check the Product Family.
     */
    public static final String NMAM8633E = "NMAM8633E";

    /**
     * ZZZM9003I:The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    // 2018/12/27 QC#29695 Add Start
    /**
     * NMAM8105E:The target data for the update does not exist.
     */
    public static final String NMAM8105E = "NMAM8105E";
    // 2018/12/27 QC#29695 Add End
    
    // =================================================
    // DB Query Parameters
    // =================================================
    /**
     * DB_PARAM_GLBL_CMPY_CD
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB_PARAM_PROD_CTRL_CD
     */
    public static final String DB_PARAM_PROD_CTRL_CD = "prodCtrlCd";

    /**
     * DB_PARAM_PROD_CTRL_CD_LIST
     */
    public static final String DB_PARAM_PROD_CTRL_CD_LIST = "prodCtrlCdList";

    /**
     * DB_PARAM_PROD_CTRL_NM
     */
    public static final String DB_PARAM_PROD_CTRL_NM = "prodCtrlNm";

    /**
     * DB_PARAM_ZEROTH_PROD_CTRL_CD
     */
    public static final String DB_PARAM_ZEROTH_PROD_CTRL_CD = "zerothProdCtrlCd";

    /**
     * DB_PARAM_FIRST_PROD_CTRL_CD
     */
    public static final String DB_PARAM_FIRST_PROD_CTRL_CD = "firstProdCtrlCd";

    /**
     * DB_PARAM_SCD_PROD_CTRL_CD
     */
    public static final String DB_PARAM_SCD_PROD_CTRL_CD = "scdProdCtrlCd";

    /**
     * DB_PARAM_THIRD_PROD_CTRL_CD
     */
    public static final String DB_PARAM_THIRD_PROD_CTRL_CD = "thirdProdCtrlCd";

    /**
     * DB_PARAM_FRTH_PROD_CTRL_CD
     */
    public static final String DB_PARAM_FRTH_PROD_CTRL_CD = "frthProdCtrlCd";

    /**
     * DB_PARAM_SCD_PROD_HRCH_CD
     */
    public static final String DB_PARAM_SCD_PROD_HRCH_CD = "scdProdHrchCd";

    /**
     * DB_PARAM_MDSE_STRU_ELMNT_TP_CD
     */
    public static final String DB_PARAM_MDSE_STRU_ELMNT_TP_CD = "mdseStruElmntTpCd";

    /**
     * DB_PARAM_MDSE_STRU_ELMNT_TP_CD_LIST
     */
    public static final String DB_PARAM_MDSE_STRU_ELMNT_TP_CD_LIST = "mdseStruElmntTpCdList";

    /**
     * DB_PARAM_PL3
     */
    public static final String DB_PARAM_PL3 = "prodLine";

    /**
     * DB_PARAM_PL2
     */
    public static final String DB_PARAM_PL2 = "prodLine2";

    /**
     * DB_PARAM_RGTN_STS_CD
     */
    public static final String DB_PARAM_RGTN_STS_CD = "rgtnStsCd";

    /**
     * DB_PARAM_MDSE_ROWNUM
     */
    public static final String DB_PARAM_MDSE_ROWNUM = "mdseRowNum";

    /**
     * DB_PRAM_ROWNUM
     */
    public static final String DB_PARAM_ROWNUM = "rowNum";

    // =================================================
    // DB Result Columns
    // =================================================
    /**
     * MDSE_STRU_ELMNT_TP_CD
     */
    public static final String MDSE_STRU_ELMNT_TP_CD = "MDSE_STRU_ELMNT_TP_CD";

    /**
     * MDSE_STRU_ELMNT_TP_DESC_TXT
     */
    public static final String MDSE_STRU_ELMNT_TP_DESC_TXT = "MDSE_STRU_ELMNT_TP_DESC_TXT";

    /**
     * PROD_CTRL_CD
     */
    public static final String PROD_CTRL_CD = "PROD_CTRL_CD";

    /**
     * PROD_CTRL_NM
     */
    public static final String PROD_CTRL_NM = "PROD_CTRL_NM";

    /**
     * SCD_PROD_HRCH_CD
     */
    public static final String SCD_PROD_HRCH_CD = "SCD_PROD_HRCH_CD";

    /**
     * SCD_PROD_HRCH_NM
     */
    public static final String SCD_PROD_HRCH_NM = "SCD_PROD_HRCH_NM";

    /**
     * MDSE_CD
     */
    public static final String MDSE_CD = "MDSE_CD";

    /**
     * CNT
     */
    public static final String CNT = "CNT";

    /**
     * EZUPTIME
     */
    public static final String EZUPTIME = "EZUPTIME";

    /**
     * EZUPTIMEZONE
     */
    public static final String EZUPTIMEZONE = "EZUPTIMEZONE";

    // =================================================
    // Other
    // =================================================

    /**
     * SUBMIT
     */
    public static final String SUBMIT = "Submit";

    /**
     * Delete Line
     */
    public static final String DELETE = "DeleteLine";

    /**
     * XX_CHK_BOX_A1
     */
    public static final String XX_CHK_BOX_A1 = "xxChkBox_A1";

    /**
     * MDSE
     */
    public static final String MSG_MDSE = "Merchandise";

    /**
     * MSG_PRODUCT_FAMILY
     */
    public static final String MSG_PRODUCT_FAMILY = "Product Family";

    /**
     * LEVEL_TXT
     */
    public static final String LEVEL_TXT = "Level%s(%s)";

    /**
     * PROD_CTRL
     */
    public static final String PROD_CTRL = "PROD_CTRL";

    /**
     * MDSE
     */
    public static final String MDSE = "MDSE";

    /**
     * MSG_LEVEL
     */
    public static final String MSG_LEVEL = "Level";

    /**
     * MSG_HC
     */
    public static final String MSG_HC = "Hierarchy Code";

    /**
     * MSG_DESC
     */
    public static final String MSG_DESC = "Description";

    /**
     * FETCH_SIZE
     */
    public static final int FETCH_SIZE = 1000;

    /**
     * NUM_PRODUCT_LINE_GROUP
     */
    public static final int NUM_PRODUCT_LINE_GROUP = 0;

    /**
     * NUM_PRODUCT_LINE
     */
    public static final int NUM_PRODUCT_LINE = 1;

    /**
     * NUM_PRODUCT_LEVEL2
     */
    public static final int NUM_PRODUCT_LEVEL2 = 2;

    /**
     * NUM_PRODUCT_LEVEL3
     */
    public static final int NUM_PRODUCT_LEVEL3 = 3;

    /**
     * NUM_PRODUCT_LEVEL4
     */
    public static final int NUM_PRODUCT_LEVEL4 = 4;
}
