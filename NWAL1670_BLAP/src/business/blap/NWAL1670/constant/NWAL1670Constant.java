/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1670.constant;

/**
 *<pre>
 * Order Team Set up
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public final class NWAL1670Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NWAL1670";

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
     * Process ended normally.
     */
    public static final String ZZM8100I = "ZZM8100I";

    /**
     * No search result was found.
     */
    public static final String NWAM0006I = "NWAM0006I";

    /**
     * This data has been updated by another user.
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * Failed to update the @.
     */
    public static final String NWAM0791E = "NWAM0791E";

    /**
     * Please select for Radio button.
     */
    public static final String NWAM0827E = "NWAM0827E";

    /**
     * The selected team doesn't save yet. 
     * If you want to copy, please click the submit button.
     */
    public static final String NWAM0828E = "NWAM0828E";

    /**
     * You can not delete record.
     * Order team must have  at least one record of "Member" or "Attribute" .
     */
    public static final String NWAM0829E = "NWAM0829E";

    /**
     * The selected team can not be deleted.please enter End Date.
     */
    public static final String NWAM0830E = "NWAM0830E";

    /**
     * Some results can not be displayed as there are too many results. 
     */
    public static final String NWAM0496W = "NWAM0496W";

    /**
     * The number of lines exceeds the maximum. It must be [@] lines or less.
     */
    public static final String NWAM0495E = "NWAM0495E";

    /**
     * Please check at least 1 checkbox.
     */
    public static final String NWAM0186E = "NWAM0186E";

    /**
     * Required Fields have not been entered.
     */
    public static final String NWAM0093E = "NWAM0093E";

    /**
     * There is no data to be processed.
     */
    public static final String NWAM0794E = "NWAM0794E";

    /**
     * For [@], please enter  [@] or a later date.
     */
    public static final String NWAM0223E = "NWAM0223E";

    /**
     * [@] already exists. Cannot add.
     */
    public static final String NWAM0559E = "NWAM0559E";

    /**
     * The data @ does not exist in the master.
     */
    public static final String NZZM0010E = "NZZM0010E";

    /**
     * Start Date
     */
    public static final String MDG_PARAM_START_DATE = "Start Date";

    /**
     * Sales Date
     */
    public static final String MDG_PARAM_SLS_DT = "Sales Date";

    /**
     * End Date
     */
    public static final String MDG_PARAM_END_DATE = "End Date";

    /**
     * Team Name
     */
    public static final String MDG_PARAM_TEAM_NAME = "Team Name";

    /**
     * Attribute Value
     */
    public static final String MDG_PARAM_ATTR_VAL = "Attribute Value";

    /**
     * User ID
     */
    public static final String MDG_PARAM_USER_ID = "User ID";

    /**
     * Table name: ORD_TEAM_MSTR_HDR
     */
    public static final String TBL_NM_ORD_TEAM_MSTR_HDR = "ORD_TEAM_MSTR_HDR";

    /**
     * Table name: ORD_TEAM_MSTR_DTL
     */
    public static final String TBL_NM_ORD_TEAM_MSTR_DTL = "ORD_TEAM_MSTR_DTL";
}
