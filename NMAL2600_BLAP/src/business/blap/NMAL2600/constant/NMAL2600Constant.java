/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2600.constant;

/**
 *<pre>
 * PR Release  NPAL1030Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/5   Fujitsu          J.Sakamoto      Create          N/A
 * 2015/12/2   Fujitsu          K.Koitabashi    Create          N/A
 * 2016/04/25  Fujitsu          C.Yokoi         Update          CSA-QC#7547
 * 2016/09/21  Fujitsu          Mz.Takahashi    Update          QC#11068
 *</pre>
 */
public class NMAL2600Constant {
    /**
     * BUSINESS_ID
     */
    public static final String BIZ_ID = "NMAL2600";

    /**
     * SCREEN_ID
     */
    public static final String SCRN_ID_00 = BIZ_ID + "Scrn00";

    /** Date Format */
    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmssSSS";

    /** Warning Kind */
    public static final String MESSAGE_KIND_WARN = "W";

    /** First Tier Code */
    public static final String FIRST_TIER_CD = "1";

    /** Max Effective Thru Date */
    public static final String MAX_EFF_THRU_DT = "99991231";

    /** DB Param */
    public static final String DB_PARAM_CMSG = "cMsg";

    /** Dot */
    public static final String DOT = ".";

    /** Default Length for Advanced Search */
    public static final Integer ADV_DEF_CNT = 3;

    /** Max Length for Advanced Search */
    public static final Integer ADV_MAX_CNT = 10;

    /** BIZ_AREA_ORG_CD */
    public static final String BIZ_AREA_ORG_CD_DBCOLUMN = "BIZ_AREA_ORG_CD";

    /** BIZ_AREA_ORG_NM */
    public static final String BIZ_AREA_ORG_NM_DBCOLUMN = "BIZ_AREA_ORG_NM";

    /** TRTY_TP_CD */
    public static final String TRTY_TP_CD_DBCOLUMN = "TRTY_TP_CD";

    /** TRTY_TP_NM */
    public static final String TRTY_TP_NM_DBCOLUMN = "TRTY_TP_NM";

    /** ORG_TIER_CD */
    public static final String ORG_TIER_CD_DBCOLUMN = "ORG_TIER_CD";

    /** ORG_TIER_NM */
    public static final String ORG_TIER_NM_DBCOLUMN = "ORG_TIER_NM";

    /** TRTY_GRP_TP_CD */
    public static final String TRTY_GRP_TP_CD_DBCOLUMN = "TRTY_GRP_TP_CD";

    /** TRTY_GRP_TP_NM */
    public static final String TRTY_GRP_TP_NM_DBCOLUMN = "TRTY_GRP_TP_NM";

    /** TRTY_RULE_TP_CD */
    public static final String TRTY_RULE_TP_CD_DBCOLUMN = "TRTY_RULE_TP_CD";

    /** TRTY_RULE_TP_NM */
    public static final String TRTY_RULE_TP_NM_DBCOLUMN = "TRTY_RULE_TP_NM";

    /** TRTY_RULE_OPRD_TP_CD */
    public static final String TRTY_RULE_OPRD_TP_CD_DBCOLUMN = "TRTY_RULE_OPRD_TP_CD";

    /** TRTY_RULE_OPRD_TP_NM */
    public static final String TRTY_RULE_OPRD_TP_NM_DBCOLUMN = "TRTY_RULE_OPRD_TP_NM";

    /** ORG_CD */
    public static final String ORG_CD_DBCOLUMN = "ORG_CD";

    /** Top Territories Only */
    public static final int RADIO_BUTTON_TOP_TERRITORY_ONLY = 1;

    /** All Territories Expanded */
    public static final int RADIO_BUTTON_ALL_TERRITORY_EXPANDED = 2;

    /** Territory & Children Only */
    public static final int RADIO_BUTTON_TERRITORY_AND_CHILDREN_ONLY = 3;

    /** Territory, Children & Territory Rules */
    public static final int RADIO_BUTTON_TERRITORY_CHILDREN_RULES = 4;

    /** [@] is not found. */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search result is found. */
    public static final String NMAM0038I = "NMAM0038I";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** A past date cannot be entered into the "[@]". */
    public static final String NMAM0900E = "NMAM0900E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** You can not [@] this record Because of [@] already exists.*/
    public static final String NMAM0182E = "NMAM0182E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    // QC#7547
    /** Territory need to be select to show @. Please select active territory. */
    public static final String NMAM8476E = "NMAM8476E";
}
