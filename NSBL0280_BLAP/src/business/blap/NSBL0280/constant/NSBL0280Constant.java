/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0280.constant;

/**
 *<pre>
 * Resource Skill Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/17   Hitachi         J.Kim           Create          N/A
 * 2016/11/15   Hitachi         K.Ochiai        Update          QC#15700
 * 2016/12/15   Hitachi         K.Kojima        Update          QC#15653
 *</pre>
 */
public final class NSBL0280Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSBL0280";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * Please check at least 1 checkbox.
     */
    public static final String NZZM0011E = "NZZM0011E";

    /**
     * No search results found.
     */
    public static final String ZZZM9005W = "ZZZM9005W";

    /**
     * Please select only one Check Box.
     */
    public static final String NZZM0009E = "NZZM0009E";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /** Switch View Mode */
    public static final String RESOURCE_TYPE = "ResourceType";

    /** Switch View Mode */
    public static final String SKILL_TYPE = "skillType";

    /**
     * The record cannot be inserted. Table Name: [@], Key Field Name:
     * [@], Key Value: [@].
     */
    public static final String NSBM0081E = "NSBM0081E";

    /**
     * The record cannot be updated. Table Name: [@], Key Field Name:
     * [@], Key Value: [@].
     */
    public static final String NSBM0073E = "NSBM0073E";

    /**
     * This data has been updated by another user.
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    // START 2016/11/15 K.Ochiai [QC#15700, MOD]
    /**
     * Multiple same skills cannot be assigned to the same resource.
     */
    public static final String NSBM0184E = "NSBM0184E";

    // END 2016/11/15 K.Ochiai [QC#15700, MOD]

    /**
     * An earlier date should be entered in 'Effective From Date' than
     * for 'Effective Thru Date'.
     */
    public static final String NSBM0083E = "NSBM0083E";

    /**
     * [@] doesn't exists in [@].
     */
    public static final String NSBM0084E = "NSBM0084E";

    /**
     * The data cannot be deleted if already registered.
     */
    public static final String NSBM0085E = "NSBM0085E";

    /**
     * This Resource[@] is different from the one previously set. [@]
     */
    public static final String NSBM0086E = "NSBM0086E";

    /**
     * Skill Type "Group Code[@]" and Skill Level "Group Code[@]" do not match.
     */
    public static final String NSBM0088E = "NSBM0088E";

    // START 2016/12/15 K.Kojima [QC#15653,ADD]
    /** MAX_DT_VAL */
    public static final String MAX_DT_VAL = "29991231";
    // END 2016/12/15 K.Kojima [QC#15653,ADD]
}
