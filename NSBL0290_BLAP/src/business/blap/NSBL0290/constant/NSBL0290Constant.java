/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0290.constant;

/**
 *<pre>
 * Skill Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/09   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public final class NSBL0290Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSBL0290";

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
    public static final String ZZZM9005W = "ZZZM9005W";

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * Please check at least 1 checkbox.
     */
    public static final String NZZM0011E = "NZZM0011E";

    /**
     * Please select only one Check Box.
     */
    public static final String NZZM0009E = "NZZM0009E";

    /**
     * Another user has already updated target record. Please search again. 
     */
    public static final String NFAM0004E = "NFAM0004E";

    /**
     * The record cannot be inserted. Table Name: [@], Key Field Name: [@], Key Value: [@].
     */
    public static final String NSBM0081E = "NSBM0081E";

    /**
     * The record cannot be updated. Table Name: [@], Key Field Name: [@], Key Value: [@].
     */
    public static final String NSBM0073E = "NSBM0073E";

    /**
     * This data has been updated by another user.
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * [@] already exists in [@].
     */
    public static final String NSBM0082E = "NSBM0082E";

    /**
     * An earlier date should be entered in 'Effective From Date' than for 'Effective Thru Date'.
     */
    public static final String NSBM0083E = "NSBM0083E";
}
