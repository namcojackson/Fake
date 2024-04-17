/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */

package business.blap.NPAL1010.constant;

/**
 * <pre>
 * NPAL1010 Location Info Pop Up
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/06/26   Fujitsu         S.Noguchi       Create          N/A
 * 2012/09/13   Fujitsu         Y.Kamide        Update          N/A
 * 2013/05/14   Fujitsu         H.Mizutani      Update          N/A
 * 2015/04/28   CITS            H.Sugawara      Update          N/A
 * 2016/02/23   CSAI            D.Fukaya        Update          QC# 2378
 *</pre>
 */
public class NPAL1010Constant {

    // WDS R-WH025 Inventory Transaction Mizutani Start
    /**
     * Business ID
     */
    public static final String BLANK = "";

    // WDS R-WH025 Inventory Transaction Mizutani Emd
    /**
     * Business ID
     */
    public static final String BUSINESS_ID = "NPAL1010";

    /**
     * Data Security Attribute
     */
    public static final String DATA_SECUR_ATTR = "WH";

    /**
     * Event ID INIT
     */
    public static final String EVENT_INIT = "NPAL1010_INIT";

    /**
     * Event ID Search_StateCode
     */
    public static final String EVENT_SEARCH_STATE_CODE = "NPAL1010Scrn00_Search_StateCode";

    /**
     * Event ID Search_Location
     */
    public static final String EVENT_SEARCH_LOCATION = "NPAL1010Scrn00_Search_Location";

    /**
     * Event ID Select_Location
     */
    public static final String EVENT_SELECT_LOCATION = "NPAL1010Scrn00_Select_Location";

    /**
     * Event ID OpenWin_Mgr
     */
    public static final String EVENT_OPENWIN_MGR = "NPAL1010Scrn00_OpenWin_Mgr";

    /**
     * Event ID OnClick_MgrNm
     */
    public static final String EVENT_ONCLICK_MGRNM = "NPAL1010Scrn00_OnClick_MgrNm";

    /**
     * Event ID CMN_Clear
     */
    public static final String EVENT_CMN_CLEAR = "CMN_Clear";

    /**
     * Event ID CMN_Close
     */
    public static final String EVENT_CMN_CLOSE = "CMN_Close";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_GLBL_CMPY_CD = "glblCmpyCd";

    // WDS R-WH025 Inventory Transaction Mizutani Start
    /**
     * SQL set Key
     */
    //public static final String SQL_KEY_CUSA_GLBL_CMPY_CD = "cusaGlblCmpyCd";
    // WDS R-WH025 Inventory Transaction Mizutani End

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_RGTN_STS_CD = "rgtnStsCd";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_INVTY_LOC_CD_LIST = "invtyLocCdList";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_LOC_ROLE_TP = "locRoleTpCd";

    // START 2015/04/28 H.Sugawara E850 Warehouse Setup
    /**
     * SQL set Key
     */
    public static final String SQL_KEY_RTL_WH_CATG = "rtlWhCatgCd";
    // END 2015/04/28 H.Sugawara E850 Warehouse Setup
    /**
     * SQL set Key
     */
    public static final String SQL_KEY_INVTY_LOC_NM_LIST = "invtyLocNmList";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_ADDL_LOC_NM = "addlLocNm";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_FIRST_LINE_ADDR = "firstLineAddr";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_POST_CD = "postCd";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_CTY_ADDR = "ctyAddr";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_ST_CD = "stCd";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_ST_NM = "stNm";

    // WDS R-WH025 Inventory Transaction Mizutani Start
    /**
     * It is a computational method that shows multiplication.
     */
    public static final String MULTIPLICATION = "*";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_WH_SYS_TP_CD_NA = "na";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_DATA_SEC_FLG = "dataSecFlg";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_DATA_SEC_LIST = "dataSecList";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_LOC_ROLE_TP_LIST = "locRoleTpList";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_VIRTUAL_WH_FLG = "virtualWHFlg";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_RTL_WH_CD_LIST = "rtlWhCdList"; // 10/12/2015 add

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_RTL_WH_NM_LIST = "rtlWhNmList"; // 10/12/2015 add

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_RTL_SWH_CD_LIST = "rtlSwhCdList"; // 10/12/2015 add

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_RTL_SWH_NM_LIST = "rtlSwhNmList"; // 10/12/2015 add

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_ONLY_RTL_WH_FLG = "onlyRtlWhFlg"; // 10/12/2015 add

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_INVTY_ACCT_CD = "invtyAcctCd"; // 10/28/2015 add

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_INVTY_OWNR_CD = "invtyOwnrCd";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_WH_OWNR_CD = "whOwnrCd";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_WH_MGR_PSN_CD = "whMgrPsnCd";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_FULL_PSN_NM = "fullPsnNm";
    /**
     * SQL set Key
     */
    public static final String SQL_KEY_LOC_TP_TECHNICIAN = "locTpCd_Technician";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_ACTIVE_FLG = "activeFlg"; // 08/03/2016 add

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_SALES_DATE = "salesDate"; // 08/03/2016 add
 
    /**
     * SQL set Key
     */
    public static final String SQL_KEY_EFF_THRU_DT_DEFALUT = "effThruDtDefalut"; // 08/03/2016 add

    // WDS R-WH025 Inventory Transaction Mizutani End
    /**
     * MSG Key
     */
    public static final String MSG_KEY_DS_WH_SRC_TP_CD_WAREHOUSE = "warehouse";

    /**
     * SQL set Value
     */
    public static final String SQL_VALUE_DS_WH_SRC_TP_CD_WAREHOUSE = "1";

    /**
     * MSG Key
     */
    public static final String MSG_KEY_DS_WH_SRC_TP_CD_TECHNICIAN = "technician";

    /**
     * SQL set Value
     */
    public static final String SQL_VALUE_DS_WH_SRC_TP_CD_TECHNICIAN = "2";

    /**
     * MSG Key
     */
    public static final String MSG_KEY_DS_WH_SRC_TP_CD_VENDOR_WAREHOUSE = "vendorWarehouse";

    /**
     * SQL set Value
     */
    public static final String SQL_VALUE_DS_WH_SRC_TP_CD_VENDOR_WAREHOUSE = "3";

    /**
     * MSG Key
     */
    public static final String MSG_KEY_DS_WH_SRC_TP_CD_VENDOR_PARTS = "vendorParts";

    /**
     * SQL set Value
     */
    public static final String SQL_VALUE_DS_WH_SRC_TP_CD_VENDOR_PARTS = "4";

    /**
     * SQL set Value
     */
    public static final String RGTN_STS_CD_P20 = "P20";

    /**
     * SQL set Value
     */
    public static final String MAX_DATE = "99991231"; // 08/03/2016 add

    /**
     * Radio Button Value
     */
    public static final int RADIO_WH = 1;

    /**
     * Radio Button Value
     */
    public static final int RADIO_TECH = 2;

    /**
     * Radio Button Value
     */
    public static final int RADIO_ALL = 3;

    /**
     * Error Message: No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

    // WDS R-WH025 Inventory Transaction Mizutani Start

    /**
     * Error Message: Fatal Internal Error found.
     */
    public static final String ZZM9501E = "ZZM9501E";
    /** 
     * No search results found of [@]. 
     */
    public static final String NPAM0020E = "NPAM0020E";
    // WDS R-WH025 Inventory Transaction Mizutani End

    /** 
     * The entered @ does not exist in Master.
     */
    public static final String NPAM1361E = "NPAM1361E";

    /**
     * Error Message: There are too many search results, there is data
     * that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * Set Message Name: Parent Sell To
     */
    public static final String MES_PARENT_SELL_TO = "Sell To";

    /**
     * SQL set Key
     */
    public static final String SQL_KEY_DS_LOC_NM = "dsLocNm";
}
