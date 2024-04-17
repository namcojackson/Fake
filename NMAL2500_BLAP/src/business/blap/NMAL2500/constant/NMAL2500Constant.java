/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2500.constant;

/**
 *<pre>
 * Org Resource Search  NMAL2500Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/5   Fujitsu          J.Sakamoto      Create          N/A
 * 2015/10/19  Fujitsu          K.Koitabashi    Update          N/A
 * 2016/09/21  Fujitsu          Mz.Takahashi    Update          QC#11068
 *</pre>
 */
public class NMAL2500Constant {
    /**
     * BUSINESS_ID
     */
    public static final String BIZ_ID = "NMAL2500";

    /**
     * SCREEN_ID
     */
    public static final String SCRN_ID_00 = BIZ_ID + "Scrn00";

    /** You can not [@] this record Because of [@] already exists.*/
    public static final String NMAM0182E = "NMAM0182E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** [@] process ended normally. */
    public static final String NMAM8182I = "NMAM8182I";

    /** Active children Organization assignments still exist. */
    public static final String NMAM8245E = "NMAM8245E";

    /** Active resource assignments still exist. */
    public static final String NMAM8246E = "NMAM8246E";

    /** The value for [@] must be bigger than [@]. */
    public static final String NMAM0044E = "NMAM0044E";

    /** NZZM0000E=0,No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** No search result is found. */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** Update */
    public static final String UPDATE = "UPDATE";

    /** Date Format */
    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmssSSS";

    /** Max Effective Thru Date */
    public static final String MAX_EFF_THRU_DT = "99991231";

    /** Warning Kind */
    public static final String MESSAGE_KIND_WARN = "W";

    /** COMMMA */
    public static final String DOT = ".";

    /** cMsg */
    public static final String DB_PARAM_CMSG = "cMsg";

    /** SEARCH MODE SHOW HIERARCHY **/
    public static final String SEARCH_MODE_SHOW_HIERARCHY = "10";

    /** SEARCH MODE QUICK LOOK UP **/
    public static final String SEARCH_MODE_QUICK_RESOURCE_LOOK_UP = "20";

    /** SHOW HIERARCHY **/
    public static final String SHOW_HIERARCHY = "Hierarchy";

    /** QUICK LOOK UP **/
    public static final String QUICK_RESOURCE_LOOK_UP = "QuickLookup";

    /** first Tier **/
    public static final String FIRST_TIER_CD = "1";

    /** SHOW HIERARCHY - Display Top Tiers Only **/
    public static final int DISPLAY_TOP_TIERS_ONLY = 1;

    /** SHOW HIERARCHY - Display Org Level & Children Only **/
    public static final int DISPLAY_ORG_LEVEL_CHILDREN_ONLY = 2;

    /** SHOW HIERARCHY - Display Org Level, Children & Resource **/
    public static final int DISPLAY_ORG_LEVEL_CHILDREN_RESOURCE = 3;

    /**
     * SHOW HIERARCHY - Display All Levels Expanded w/Resource
     * Assignment
     **/
    public static final int DISPLAY_ALL_LEVELS_EXPANDED_WITH_RESOURCE_ASSIGNMENT = 4;

    /** Event Name : Submit */
    public static final String EVENT_NM_NMAL2500SCRN00_CMN_SUBMIT = "NMAL2500Scrn00_CMN_Submit";

    /**
     * Define Table Column to create Pulldown
     */
    /** BIZ_AREA_ORG_CD */
    public static final String BIZ_AREA_ORG_CD_DBCOLUMN = "BIZ_AREA_ORG_CD";

    /** BIZ_AREA_ORG_NM */
    public static final String BIZ_AREA_ORG_NM_DBCOLUMN = "BIZ_AREA_ORG_NM";

    /** VAR_CHAR_CONST_NM */
    public static final String VAR_CHAR_CONST_NM_DBCOLUMN = "NMAL2500_MODE_CD%";

    /** VAR_CHAR_CONST_VAL */
    public static final String VAR_CHAR_CONST_VAL_DBCOLUMN = "VAR_CHAR_CONST_VAL";

    /** VAR_CHAR_CONST_DESC_TXT */
    public static final String VAR_CHAR_CONST_DESC_TXT_DBCOLUMN = "VAR_CHAR_CONST_DESC_TXT";

    /** ORG_TIER_CD */
    public static final String ORG_TIER_CD_DBCOLUMN = "ORG_TIER_CD";

    /** ORG_TIER_NM */
    public static final String ORG_TIER_NM_DBCOLUMN = "ORG_TIER_NM";

    /** CSR_RG_TP_CD */
    public static final String CSR_RG_TP_CD_DBCOLUMN = "CSR_RG_TP_CD";

    /** CSR_RG_TP_NM */
    public static final String CSR_RG_TP_NM_DBCOLUMN = "CSR_RG_TP_NM";

    /** ORG_FUNC_ROLE_TP_CD */
    public static final String ORG_FUNC_ROLE_TP_CD_DBCOLUMN = "ORG_FUNC_ROLE_TP_CD";

    /** ORG_FUNC_ROLE_TP_NM */
    public static final String ORG_FUNC_ROLE_TP_NM_DBCOLUMN = "ORG_FUNC_ROLE_TP_NM";

    /** End Date */
    public static final String NAME_FOR_MESSAGE_EFF_THRU_DT = "End Date";

    /** Current Date */
    public static final String NAME_FOR_MESSAGE_CURRENT_DT = "Current Date";

    /** PSN_NUM */
    public static final String DB_COLUMN_PSN_NUM = "PSN_NUM";

    /** ORG_CD */
    public static final String DB_COLUMN_ORG_CD = "ORG_CD";

    /** ORG_CD */
    public static final String DB_COLUMN_ORG_NM = "ORG_NM";

    /** ORG_STRU_TP_CD */
    public static final String DB_COLUMN_ORG_STRU_TP_CD = "ORG_STRU_TP_CD";

    /** EFF_FROM_DT */
    public static final String DB_COLUMN_EFF_FROM_DT = "EFF_FROM_DT";

    /** EZUPTIME */
    public static final String DB_COLUMN_EZUPTIME = "EZUPTIME";

    /** EZUPTIMEZONE */
    public static final String DB_COLUMN_EZUPTIMEZONE = "EZUPTIMEZONE";

    /** PRNT_ORG_CD */
    public static final String DB_COLUMN_PRNT_ORG_CD = "PRNT_ORG_CD";

    // 2017/03/06 S21_NA#17760 Add Start
    /** DS_ORG_RELN_PK */
    public static final String DB_COLUMN_DS_ORG_RELN_PK = "DS_ORG_RELN_PK";
    // 2017/03/06 S21_NA#17760 Add End

}
