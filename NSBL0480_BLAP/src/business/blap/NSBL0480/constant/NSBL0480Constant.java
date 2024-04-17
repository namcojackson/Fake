/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0480.constant;

/**
 *<pre>
 * Access Permits Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/30   Hitachi         J.Kim           Create          N/A
 * 2017/02/01   Hitachi         K.Kitachi       Update          QC#16629
 *</pre>
 */
public final class NSBL0480Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSBL0480";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * There are too many search results, there is data that cannot be displayed.
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

    /**
     * Another user has already updated target record. Please search again. 
     */
    public static final String NFAM0004E = "NFAM0004E";

    /** Switch View Mode */
    public static final String RESOURCE_MODE = "Resource";

    /** Switch View Mode */
    public static final String ACCESSPERMITS_MODE = "AccessPermits";

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

    /**
     * [@] already exists in [@].
     */
    public static final String NSBM0082E = "NSBM0082E";

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

    // START 2017/02/01 K.Kitachi [QC#16629, DEL]
//    /**
//     * Entered "Access Permits Number[@]" does not exist in Access Permit. Please select correct Access Permit from the "Access Permits Search Popup".
//     */
//    public static final String NSBM0087E = "NSBM0087E";
//
//    /**
//     * Unable to check Access Permit. Please select correct Access Permit from the "Access Permits Search Popup".
//     */
//    public static final String NSBM0089E = "NSBM0089E";
    // END 2017/02/01 K.Kitachi [QC#16629, DEL]

    // START 2017/02/01 K.Kitachi [QC#16629, ADD]
    /**
     * @ does not exist in @.
     */
    public static final String NSBM0025E = "NSBM0025E";

    /**
     * @ is overlap with @.
     */
    public static final String NSBM0185E = "NSBM0185E";
    // END 2017/02/01 K.Kitachi [QC#16629, ADD]
}
