/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1010.constant;

/**
 * <pre>
 * NPAL1010 Location Info Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/06/25   Fujitsu         D.Noguchi       Create          N/A
 * 2013/05/14   Fujitsu         H.Mizutani      Update          N/A
 * 2015/04/28   CITS            H.Sugawara      Update          N/A
 * 02/23/2016   CSAI            D.Fukaya        Update          QC#2378
 * 04/14/2016   CSAI            D.Fukaya        Update          QC#6610
 * 04/21/2016   CSAI            D.Fukaya        Update          QC#2378
 *</pre>
 */
public class NPAL1010Constant {

    /**
     * Function Search ID
     */
    public static final String FUNC_SRCH_ID = "20";

    /**
     * blank
     */
    public static final String BLANK = "";

    /**
     * Function ID(Reference)
     */
    public static final String FUNC_ID_SELECT_USER = "NPAL1010T010";

    /**
     * Business ID
     */
    public static final String BUSINESS_ID = "NPAL1010";

    /**
     * Screen ID
     */
    public static final String SCREEN_ID = "NPAL1010Scrn00";

    /**
     * Error Message: There is no search result. Please change search
     * conditions and try again.
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * Error Message: More than 200 results found.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * Error Message: [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * TBL WH
     */
    public static final String TBL_WH = "WH";

    /**
     * TBL ST
     */
    public static final String TBL_ST = "ST";

    /**
     * SQL Column
     */
    public static final String COL_ADDL_LOC_NM = "ADDL_LOC_NM";

    /**
     * SQL Column
     */
    public static final String COL_CTY_ADDR = "CTY_ADDR";

    /**
     * SQL Column
     */
    public static final String COL_EZCANCELFLAG = "EZCANCELFLAG";

    /**
     * SQL Column
     */
    public static final String COL_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /**
     * SQL Column
     */
    public static final String COL_LOC_NM = "LOC_NM";

    /**
     * SQL Column
     */
    public static final String COL_LOC_ROLE_TP_CD = "LOC_ROLE_TP_CD";

    /**
     * SQL Column
     */
    public static final String COL_POST_CD = "POST_CD";

    /**
     * SQL Column
     */
    public static final String COL_RGTN_STS_CD = "RGTN_STS_CD";

    /**
     * SQL Column
     */
    public static final String COL_ST_CD = "ST_CD";

    /**
     * SQL Column
     */
    public static final String COL_ST_NM = "ST_NM";

    /**
     * SQL Column
     */
    public static final String COL_WH_CD = "WH_CD";

    /**
     * TBL LABEL
     */
    public static final String LABEL_RTL_WH_CD = "WH Code"; // 10/12/2015

    // add

    /**
     * TBL LABEL
     */
    public static final String LABEL_RTL_WH_NM = "WH Name"; // 10/12/2015

    // add

    /**
     * TBL LABEL
     */
    public static final String LABEL_RTL_SWH_CD = "SWH Code"; // 10/12/2015

    // add

    /**
     * TBL LABEL
     */
    public static final String LABEL_RTL_SWH_NM = "SWH Name"; // 10/12/2015

    // add

    /**
     * TBL LABEL
     */
    public static final String LABEL_INVTY_ACCT_CD = "Inv Accnt"; // 10/28/2015

    // add

    /**
     * TBL LABEL
     */
    public static final String LABEL_WH_CD = "Location Code";

    /**
     * TBL LABEL
     */
    public static final String LABEL_LOC_NM = "Location Name";

    /**
     * TBL LABEL
     */
    public static final String LABEL_LOC_ROLE_TP_CD = "Location Usage";

    // START 2015/04/28 H.Sugawara E850 Warehouse Setup
    /**
     * TBL LABEL
     */
    public static final String LABEL_RTL_WH_CATG_CD = "WH Category";

    // END 2015/04/28 H.Sugawara E850 Warehouse Setup

    /**
     * TBL LABEL
     */
    public static final String LABEL_OWNR_CD = "Owner Code";

    /**
     * TBL LABEL
     */
    public static final String LABEL_OWNR_NM = "Owner Name";

    /**
     * Left Table Name
     */
    public static final String LEFT_TABLE_NAME = "A_Left";

    /**
     * Right Table Name
     */
    public static final String RIGHT_TABLE_NAME = "A_Right";

    /**
     * TBL LABEL
     */
    public static final String LABEL_ADDL_LOC_NM = "Additional Name";

    /**
     * TBL LABEL
     */
    public static final String LABEL_FIRST_LINE_ADDR = "Address Line1";

    /**
     * TBL LABEL
     */
    public static final String LABEL_POST_CD = "Postal Code";

    /**
     * TBL LABEL
     */
    public static final String LABEL_CTY_ADDR = "City";

    /**
     * TBL LABEL
     */
    public static final String LABEL_ST_CD = "State";

    /**
     * TBL LABEL
     */
    public static final String LABEL_WH = "WH";

    /**
     * TBL LABEL
     */
    public static final String LABEL_TECH = "Tech";

    /**
     * TBL LABEL
     */
    public static final String LABEL_ALL = "ALL";

    /**
     * TBL LABEL
     */
    public static final String LABEL_CONDITION_LIKE = "(*)";

    /**
     * TBL LABEL
     */
    public static final String LABEL_ADDR_CITY_ST = "Address, City, State";

    /**
     * Screen ID TBLColumnSort
     */
    public static final String SCREEN_STATE_CD_SEARCH = "State Code Search";

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Close", "Close" };

    /**
     * Btn Search_InstallLocation
     */
    public static final String[] BTN_SEARCH_LOCATION = {"Search_Location", "Search_Location", "Search" };

    /**
     * Event name : OpenWin_Mgr.
     */
    public static final String EVENT_NM_OPENWIN_MGR = "NPAL1010Scrn00_OpenWin_Mgr";

    /**
     * Event name : Search_StateCode.
     */
    public static final String EVENT_NM_SEARCH_STATECODE = "NPAL1010Scrn00_Search_StateCode";

    /**
     * Event name : Search_StateCode.
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** Table name for NMAL6050 : WH Manager */
    public static final String TBL_NM_FOR_OWNER = "S21_PSN_V";

    /** Code Column for NMAL6050 : WH Manager */
    public static final String TBL_CD_COL_NM_FOR_OWNER = "PSN_CD";

    /** Name Column for NMAL6050 : WH Manager */
    public static final String TBL_NM_COL_NM_FOR_OWNER = "FULL_PSN_NM";

    /** Sort Column name for NMAL6050 : WH Manager */
    public static final String TBL_SORT_COL_NM_FOR_OWNER = "PSN_CD";

    /** Screen Name for NMAL6050 : WH Manager */
    public static final String SCR_NM_FOR_OWNER = "Owner Search Popup";

    /** Header Code of Label Name for NMAL6050 : Manager Code */
    public static final String HDR_CD_LB_NM_FOR_OWNER = "Owner Code";

    /** Header Name of Label Name for NMAL6050 : Manager Name */
    public static final String HDR_NM_LB_NM_FOR_OWNER = "Owner Name";

    /** Detail Code of Label Name for NMAL6050 : Manager Code */
    public static final String DTL_CD_LB_NM_FOR_OWNER = "Owner Code";

    /** Detail Name of Label Name for NMAL6050 : Manager Name */
    public static final String DTL_NM_LB_NM_FOR_OWNER = "Owner Name";

    /** Table name for NMAL6050 : Tech*/
    public static final String TBL_NM_FOR_TECH = "TECH_MSTR";

    /** Code Column for NMAL6050 : Tech */
    public static final String TBL_CD_COL_NM_FOR_TECH = "TECH_TOC_CD";

    /** Name Column for NMAL6050 : Tech */
    public static final String TBL_NM_COL_NM_FOR_TECH = "TECH_NM";

    /** Sort Column name for NMAL6050 : Tech */
    public static final String TBL_SORT_COL_NM_FOR_TECH = "TECH_TOC_CD";

    /** Screen Name for NMAL6050 : Tech */
    public static final String SCR_NM_FOR_TECH = "Technician Search Popup";

    /** Header Code of Label Name for NMAL6050 : Technician Code */
    public static final String HDR_CD_LB_NM_FOR_TECH = "Technician Code";

    /** Header Name of Label Name for NMAL6050 : Technician Name */
    public static final String HDR_NM_LB_NM_FOR_TECH = "Technician Name";

    /** Detail Code of Label Name for NMAL6050 : Technician Code */
    public static final String DTL_CD_LB_NM_FOR_TECH = "Technician Code";

    /** Detail Name of Label Name for NMAL6050 : Technician Name */
    public static final String DTL_NM_LB_NM_FOR_TECH = "Technician Name";

    /**
     * Index of Parameter
     */
    public static enum PARAMS_INDEX {
        /**
         * Location Code.
         */
        LOCATION_CODE(0),

        /**
         * Location Name.
         */
        LOCATION_NAME(1),

        /**
         * Location Role Type Code List.
         */
        LOC_ROLE_TP_CD_LIST(2),

        /**
         * Data Security Flag.
         */
        DATA_SECURITY_FLAG(3),

        /**
         * Virtual Warehouse Flag.
         */
        VIRTUAL_WH_FLAG(4),

        /**
         * Location Role Type Code (Return Only).
         */
        LOC_ROLE_TP_CD(5),

        /**
         * Retail Warehouse Code.
         */
        RETAIL_WH_CODE(6),

        /**
         * Retail Warehouse Name.
         */
        RETAIL_WH_NAME(7),

        /**
         * Retail Sub Warehouse Code.
         */
        RETAIL_SWH_CODE(8),

        /**
         * Retail Sub Warehouse Name.
         */
        RETAIL_SWH_NAME(9),

        /**
         * Only RTL_WH Flag (Input Only).
         */
        ONLY_RETAIL_WH_FLG(10), // 10/09/2015 add

        /**
         * Inventory Account.
         */
        INVTY_ACCT_CD(11), // 10/28/2015 add

        /**
         * Inventory Ownership.
         */
        INVTY_OWNR_CD(12),

        /**
         * WH Operation.
         */
        WH_OWNR_CD(13),

        /**
         * WH Category.
         */
        RTL_WH_CATG_CD(14),

        /**
         * Multiple Select Flag.
         */
        MULT_SEL_FLG(15),

        /**
         * WH Type Lock Flag.
         */
        RETAIL_WH_CATG_LOCK_FLG(16),

        /**
         * Inventory Owner Lock Flag.
         */
        INVENTORY_OWNER_LOCK_FLG(17),

        /**
         * WH Operation Lock Flag.
         */
        WH_OWNER_LOCK_FLG(18),

        /**
         * Inventory Account Lock Flag.
         */
        INVENTORY_ACCOUNT_LOCK_FLG(19),

        /**
         * WH Manager Code.
         */
        WH_MGR_PSN_CD(20),

        /**
         * WH Manager Name
         */
        FULL_PSN_NM(21),

        /**
         * Active Flag.
         */
        ACTIVE_FLG(22);

        /**
         * Constructor.
         * @param index
         */
        private PARAMS_INDEX(int index) {
            this.index = index;
        }

        /**
         * The Index.
         */
        private int index;

        /**
         * Get the Index.
         * @return the index
         */
        public int getIndex() {
            return index;
        }
    }

}
