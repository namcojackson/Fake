/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1190.constant;

/**
 *<pre>
 * Counters Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Hitachi         O.Okuma         Create          N/A
 * 2016/06/27   Hitachi         M.Gotou         Update          QC#7886
 *</pre>
 */
public final class NSAL1190Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL1190";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * Process ended normally.
     */
    public static final String ZZM8100I = "ZZM8100I";

    /**
     * This data has been updated by another user.
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * Please select at least 1 checkbox.
     */
    public static final String NSAM0015E = "NSAM0015E";

    /**
     * [@] is duplicated.
     */
    public static final String NSAM0035E = "NSAM0035E";

    /**
     * The end date must be later than [@].
     */
    public static final String NSAM0374E = "NSAM0374E";

    /**
     * [@] is mandatory when [@] is "@".
     */
    public static final String NSAM0442E = "NSAM0442E";

    /**
     * "@" is invalid.
     */
    public static final String NSAM0040E = "NSAM0040E";

    /**
     * Failed to update "@".
     */
    public static final String NSAM0031E = "NSAM0031E";

    /**
     * Failed to insert "@".
     */
    public static final String NSAM0032E = "NSAM0032E";

    /**
     * Cannot add anymore details.
     */
    public static final String NSAM0112E = "NSAM0112E";

    /**
     * VAR_CHAR_CONST:NSAL1190_INTG_MDSE_ITEM_TP_CD
     */
    public static final String NSAL1190_INTG_MDSE_ITEM_TP_CD = "NSAL1190_INTG_MDSE_ITEM_TP_CD";

    /**
     * NUM_CONST:NSAL1190_MTR_LVL_MAX_NUM
     */
    public static final String NSAL1190_MTR_LVL_MAX_NUM = "NSAL1190_MTR_LVL_MAX_NUM";

    /**
     * NUM_CONST:NSAL1190_MTR_LB_CD_LEN
     */
    public static final String NSAL1190_MTR_LB_CD_LEN = "NSAL1190_MTR_LB_CD_LEN";

    /**
     * AUTO_SEQ:MTR_LB_CD
     */
    public static final String AUTO_SEQ_MTR_LB_CD = "MTR_LB_CD";

    /**
     * BLLG_MTR_LVL_NUM:0
     */
    public static final String BLLG_MTR_LVL_NUM_0 = "0";

    /**
     * Field Name:Counter
     */
    public static final String FIELD_NM_COUNTER = "Counter";

    /**
     * Field Name:Start Date
     */
    public static final String FIELD_NM_START_DATE = "Start Date";

    /**
     * Field Name:End Date
     */
    public static final String FIELD_NM_END_DATE = "End Date";

    /**
     * Field Name:FLT Counter
     */
    public static final String FIELD_NM_FLT_COUNTER = "FLT Counter";

    /**
     * Field Name:AGG Counter
     */
    public static final String FIELD_NM_AGG_COUNTER = "AGG Counter";

    /**
     * Field Name:Ind
     */
    public static final String FIELD_NM_IND = "IND";

    /**
     * Field Name:Level
     */
    public static final String FIELD_NM_LEVEL = "Level";

    /**
     * Field Name:Type
     */
    public static final String FIELD_NM_TYPE = "Type";

    /**
     * Field Name:Counter Item
     */
    public static final String FIELD_NM_COUNTER_ITEM = "Counter Item";

    /**
     * Field Name:Description
     */
    public static final String FIELD_NM_DESCRIPTION = "Description";

    /**
     * String:SlsDate
     */
    public static final String STR_SLS_DATE = "SlsDate";

    /**
     * Table name:MTR_LB
     */
    public static final String TBL_NM_MTR_LB = "MTR_LB";

    // add start 2016/06/27 CSA Defect#7886
    /** Referenced Entity Item */
    public static final String REF_ENTITY_ITEM = "MODEL";

    /** Key Name : MDL_ID*/
    public static final String KEY_NAME_MTR_LB_CD = "MTR_LB_CD";

    /** Key Name : T_MDL_NM*/
    public static final String KEY_NAME_MTR_LB_NM = "MTR_LB_NM";
    // add end 2016/06/27 CSA Defect#7886
}
