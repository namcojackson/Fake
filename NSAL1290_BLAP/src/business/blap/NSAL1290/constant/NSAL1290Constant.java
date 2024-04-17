/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1290.constant;

/**
 *<pre>
 * Billing Counter Mapping Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/01   Hitachi         M.Gotou         Create          N/A
 * 2016/07/14   Hitachi         M.Gotou         Update          QC#9603
 * 2016/08/22   Hitachi         A.Kohinata      Update          QC#12910
 *</pre>
 */
public final class NSAL1290Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL1290";

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
     * No search results found.
     */
    public static final String ZZZM9005W = "ZZZM9005W";

    /**
     * Cannot add anymore details.
     */
    public static final String NSAM0112E = "NSAM0112E";

    /**
     * Please select at least 1 checkbox.
     */
    public static final String NSAM0015E = "NSAM0015E";

    /**
     * This data has been updated by another user.
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * Failed to update "@".
     */
    public static final String NSAM0031E = "NSAM0031E";

    /**
     * Failed to insert "@".
     */
    public static final String NSAM0032E = "NSAM0032E";

    /**
     * The chronological sequence between the dates is wrong.
     */
    public static final String NSAM0062E = "NSAM0062E";

    /**
     * The end date must be later than [@].
     */
    public static final String NSAM0374E = "NSAM0374E";

    /**
     * [@] is duplicated.
     */
    public static final String NSAM0035E = "NSAM0035E";

    /**
     * @ does not exist.
     */
    public static final String NSAM0045E = "NSAM0045E";

    /**
     * The type of billing counter does not match with that of regular counter.
     */
    public static final String NSAM0520E = "NSAM0520E";

    /**
     * NUM_CONST:NSAL1290_MTR_LVL_MAX_NUM
     */
    public static final String NSAL1290_MTR_LVL_MAX_NUM = "NSAL1290_MTR_LVL_MAX_NUM";

    /** table name : Billing Meter Map (BLLG_MTR_MAP) */
    public static final String TBL_NM_BLLG_MTR_MAP = "Billing Meter Map(BLLG_MTR_MAP)";

    /**
     * String:SlsDate
     */
    // START 2016/07/14 M.Gotou [QC#9603, MOD]
    public static final String STR_SLS_DATE = "Sales Date";
    // END 2016/07/14 M.Gotou [QC#9603, MOD]

    /**
     * String:Period
     */
    public static final String STR_PERIOD = "The entered periods";

    // add start 2016/08/22 CSA Defect#12910
    /** String: Level */
    public static final String STR_LEVEL = "Level";
    // add end 2016/08/22 CSA Defect#12910

    /** MIN_DT_VAL */
    public static final String MIN_DT_VAL = "19000101";

    /** MAX_DT_VAL */
    public static final String MAX_DT_VAL = "29991231";

    /** Referenced Entity Item */
    public static final String REF_ENTITY_ITEM = "METERCLASSES";

    /** Key Name : BLLG_MTR_LB_CD */
    public static final String KEY_NAME_BLLG_MTR_LB_CD = "BLLG_MTR_LB_CD";

    /** Key Name : MTR_LB_NM */
    public static final String KEY_NAME_MTR_LB_NM = "MTR_LB_NM";
}
