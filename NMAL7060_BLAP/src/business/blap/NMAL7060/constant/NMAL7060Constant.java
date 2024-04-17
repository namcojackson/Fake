/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7060.constant;

/**
 *<pre>
 * NMAL7060Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         q09947          Create          N/A
 * 2017/02/16   Fujitsu         R.Nakamura      Update          QC#17529
 * 2018/08/20   Fujitsu         W.Honda         Update          QC#24307
 *</pre>
 */
public class NMAL7060Constant {


    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7060Scrn00";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_INFOMATION = "I";

    /** Message Kind error */
    public static final String MESSAGE_KIND_WARNING = "W";

    /** Delimiter */
    public static final String DELIMITER_STRING = "/";

    /** Month End Position Index */
    public static final int MONTH_POSITION_END_INDEX = 6;

    /** Day End Position Index */
    public static final int DAY_POSITION_END_INDEX = 8;

    /** Year Start Position Index */
    public static final int YEAR_POSITION_START_INDEX = 0;

    /** Year End Position Index */
    public static final int YEAR_POSITION_END_INDEX = 4;

    /** High Value Date : 99991231 */
    public static final String HIGH_VAL_DT = "99991231";

    // Add Start 2018/08/20 QC#24307
    /** Event Name: OpenWin_ModelName */
    public static final String EVENT_NM_OPENWIN_PRCMTRPKGBLLGMTR = "OpenWin_PrcMtrPkgBllgMtr";

    /** Event Name: OpenWin_ModelName */
    public static final String EVENT_NM_OPENWIN_MODEL = "OpenWin_Model";
    // Add End 2018/08/20 QC#24307

    //--------------------------------
    // Message ID
    //--------------------------------

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Common Message */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /**  It is being updated by another user.  Please start again from a search.  */
    public static final String NLBM0009E = "NLBM0009E";

    /** No search result were found.   */
    public static final String NMAM0007I = "NMAM0007I";

    /** @  is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** [@] cannot be updated because of [@]. */
    public static final String NMAM0073E = "NMAM0073E";

    /**  It failed to register [@].  */
    public static final String NMAM0176E = "NMAM0176E";

    /**  It failed to update [@].  */
    public static final String NMAM0177E = "NMAM0177E";

    /**  The entered [@] does not exist in [@].  */
    public static final String NMAM0163E = "NMAM0163E";

    /**  Please set at least one search criteria */
    public static final String NMAM0192E = "NMAM0192E";

    /** If [@] value is [@], then [@] must be [@].  */
    public static final String NMAM8299E = "NMAM8211E";

    /**  [@] should be unique.  */
    public static final String NMAM8296E = "NMAM8296E";

    /**  All billing counters must have the same level number.  */
    public static final String NMAM8341E = "NMAM8341E";

    /**  The same level of Meter need to be all registered. */
    public static final String NMAM8483E = "NMAM8483E";

    /**  Billing Counter there must be common to all models registered. */
    public static final String NMAM8484E = "NMAM8484E";

    /**  Billing Counter must have been registered that are common to all models registered */
    public static final String NMAM8485E = "NMAM8485E";

    // Add Start 2017/02/16 QC#17529
    /**  The data you are editing will be discarded.  OK? */
    public static final String NMAM0096I = "NMAM0096I";
    // Add End 2017/02/16 QC#17529

    //--------------------------------
    // String For Message
    //--------------------------------

    /**  When Billable is Y, Active */
    public static final String BILLABLE_ACTIVE_Y = "When Billable is Y, Active";

    /**  Active */
    public static final String ACTIVE = "Active";

    /**  Model */
    public static final String MODEL = "Model";

    /**  Combination of start date and End date */
    public static final String COMBI_START_END = "Combination of start date and End date";

    /**  Billing Meter */
    public static final String BILLING_METER = "Billing Meter";

    /**  outside the scope of effective date */
    public static final String OUTSIDE_EFF_DT = "outside the scope of effective date";
}
