/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0600.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID : NLCL0600 PI Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2016   CITS         Makoto Okigami     Create          N/A
 * 10/05/2016   CITS         Y.Fujii            Update          QC#14417
 *</pre>
 */
public class NLCL0600Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NLCL0600";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NLCL0600Scrn00";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NLCL0600_INIT = "NLCL0600_INIT";

    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NLCL0600_CMN_SUBMIT = "NLCL0600Scrn00_CMN_Submit";

    /**
     * Event Name : CMN_Rest
     */
    public static final String EVENT_NM_NLCL0600_CMN_RESET = "NLCL0600Scrn00_CMN_Reset";

    /**
     * Event Name : CMN_Clear
     */
    public static final String EVENT_NM_NLCL0600_CMN_CLEAR = "NLCL0600Scrn00_CMN_Clear";

    /**
     * Event Name : Add_SpecificSubWH
     */
    public static final String EVENT_NM_NLCL0600_ADD_SPECIFIC_SUBWH = "NLCL0600Scrn00_Add_SpecificSubWH";

    /**
     * Event Name : Add_AllSubWH
     */
    public static final String EVENT_NM_NLCL0600_ADD_ALL_SUBWH = "NLCL0600Scrn00_Add_AllSubWH";

    /**
     * Event Name : Add_AllSubWH
     */
    public static final String EVENT_NM_NLCL0600_SEARCH_WH_INFO = "NLCL0600Scrn00_SearchWHInfo";

    /**
     * Event Name : Add_AllSubWH
     */
    public static final String EVENT_NM_NLCL0600_SEARCH_RETAIL_SUB_WH_INFO = "NLCL0600Scrn00_SearchRetailSubWHInfo";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name:cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Param Name: LOC_TP_CD
     */
    public static final String DB_PARAM_LOC_TP_CD = "locTpCd";

    /**
     * DB Param Name: Max ROWNUM
     */
    public static final String DB_PARAM_MAX_ROWNUM = "maxRownum";

    /**
     * DB Param Name: RTL_WH_CD
     */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /**
     * DB Param Name: WH_OWNR_CD
     */
    public static final String DB_PARAM_WH_OWNR_CD = "whOwnrCd";

    /**
     * DB Param Name: RTL_SWH_CD
     */
    public static final String DB_PARAM_RTL_SWH_CD = "rtlSwhCd";

    /**
     * DB Param Name: INVTY_LOC_CD
     */
    public static final String DB_PARAM_INVTY_LOC_CD = "invtyLocCd";

    /**
     * DB Param Name: Sales Date
     */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /**
     * DB Param Name: PHYS_INVTY_CTRL_NM
     */
    public static final String DB_PARAM_PHYS_INVTY_CTRL_NM = "physInvtyCtrlNm";

    /**
     * DB Param Name: PHYS_INVTY_DT
     */
    public static final String DB_PARAM_PHYS_INVTY_DT = "physInvtyDt";

    /**
     * DB Param Name: PHYS_INVTY_NUM
     */
    public static final String DB_PARAM_PHYS_INVTY_NUM = "physInvtyNum";

    /**
     * DB Param Name: ROWNUM
     */
    public static final String DB_PARAM_ROWNUM = "rownum";

    // =================================================
    // DB Param Value
    // =================================================
    /**
     * DB Param Value: ROWNUM 1
     */
    public static final BigDecimal DB_PARAM_VALUE_ROWNUM_1 = BigDecimal.ONE;

    // =================================================
    // DB Columns
    // =================================================
    /**
     * DB Column Name: RTL_SWH_NM
     */
    public static final String DB_COLUMN_RTL_SWH_NM = "RTL_SWH_NM";

    /**
     * DB Column Name: RTL_WH_NM
     */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";

    /**
     * DB Column Name: LOC_TP_CD
     */
    public static final String DB_COLUMN_LOC_TP_CD = "LOC_TP_CD";

    /**
     * DB Column Name: RTL_SWH_CD
     */
    public static final String DB_COLUMN_RTL_SWH_CD = "RTL_SWH_CD";

    /**
     * DB Column Name: INVTY_LOC_CD
     */
    public static final String DB_COLUMN_INVTY_LOC_CD = "INVTY_LOC_CD";

    /**
     * DB Column Name: EZUPTIME
     */
    public static final String DB_COLUMN_EZUPTIME = "EZUPTIME";

    /**
     * DB Column Name: EZUPTIMEZONE
     */
    public static final String DB_COLUMN_EZUPTIMEZONE = "EZUPTIMEZONE";

    /**
     * DB Column Name: PHYS_INVTY_CTRL_PK
     */
    public static final String DB_COLUMN_PHYS_INVTY_CTRL_PK = "PHYS_INVTY_CTRL_PK";

    /**
     * DB Column Name: EFF_THRU_DT
     */
    public static final String DB_COLUMN_EFF_THRU_DT = "EFF_THRU_DT";

    /**
     * DB Column Name: PHYS_INVTY_NUM
     */
    public static final String DB_COLUMN_PHYS_INVTY_NUM = "PHYS_INVTY_NUM";

    // =================================================
    // Cost value key
    // =================================================
    /**
     * Key Name : WH_PI_LOC_TP_CD
     */
    public static final String CONST_VALUE_KEY_WH_PI_LOC_TP_CD = "WH_PI_LOC_TP_CD";

    /**
     * Key Name : WH_PI_WH_OWNR_CD
     */
    public static final String CONST_VALUE_KEY_WH_PI_WH_OWNR_CD = "WH_PI_WH_OWNR_CD";

    // =================================================
    // Cost value
    // =================================================
    /**
     * Const Value Delim
     */
    public static final String VAR_CHAR_CONST_VAL_DELIM = ",";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NLCM0001E: Due to an error, process cannot be completed.  Please contact IT Department.
     */
    public static final String NLCM0001E = "NLCM0001E";

    /**
     * NMAM0038I: No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * NMAM8181W: Search results exceeded [@]. Only showing first @ records.
     */
    public static final String NMAM8181W = "NMAM8181W";

    /**
     * NLZM2278E: The entered [@] does not exist in master.
     */
    public static final String NLZM2278E = "NLZM2278E";

    /**
     * NLZM2279E: The combination of [@] and [@] does not exist in master.
     */
    public static final String NLZM2279E = "NLZM2279E";

    /**
     * NPAM0002E: No search results found.
     */
    public static final String NPAM0002E = "NPAM0002E";

    /**
     * NLCM0176E: The  [@] is already registered in Physical Inventory.
     */
    public static final String NLCM0176E = "NLCM0176E";

    /**
     * NMAM0176E: It failed to register [@].
     */
    public static final String NMAM0176E = "NMAM0176E";

    /**
     * NMAM0177E: It failed to update [@].
     */
    public static final String NMAM0177E = "NMAM0177E";

    /**
     * ZZM8100I: Process ended normally.
     */
    public static final String ZZM8100I = "ZZM8100I";

    /**
     * NPAM0006E: This data has been updated by another user.
     */
    public static final String NPAM0006E = "NPAM0006E";

    /**
     * NLCM0177E: The value for the PI scheduled date must be in the expiration date[@] of Retail WH[@].
     */
    public static final String NLCM0177E = "NLCM0177E";

    /**
     * NLCM0019E: Entered data is already registered.
     */
    public static final String NLCM0019E = "NLCM0019E";

    // =================================================
    // Message Value
    // =================================================
    /**
     * Message Value :Physical Inventory Control
     */
    public static final String MSG_VALUE_PHYS_INVTY_CTRL = "Physical Inventory Control";

}
