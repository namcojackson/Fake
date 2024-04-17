/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0420.constant;

/**
 *<pre>
 * Mods Parts Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Tsuchida      Create          N/A
 * 2016/07/13   Hitachi         O.Okuma         Update          QC#11672
 *</pre>
 */
public final class NSBL0420Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSBL0420";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /** Service Mods Detail PK */
    public static final String SVC_MOD_DTL_PK = "Service Mods Detail Primary Key";

    /** Update Mode */
    public static final String MODE_CODE = "Mode Code";

    /** Mdse Code */
    public static final String MDSE_CODE = "Mdse Code";

    /** Merchandise */
    public static final String MDSE = "Merchandise";

    // del start 2016/07/13 CSA Defect#11672
//    /** Service Call */
//    public static final String SVC_CALL = "Service Call";
    // del end 2016/07/13 CSA Defect#11672

    /** SVC_MOD_DTL_ITEM */
    public static final String SVC_MOD_DTL_ITEM = "SVC_MOD_DTL_ITEM";

    /** SVC_MOD_DTL_PK */
    public static final String SVC_MOD_DTL_PK_COL = "SVC_MOD_DTL_PK";

    /**
     * An input parameter, [@],  has not been set.
     */
    public static final String NZZM0012E = "NZZM0012E";

    /**
     * No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * Failed to insert @ table. [@]
     */
    public static final String NSBM0121E = "NSBM0121E";

    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * Cannot add anymore details.
     */
    public static final String NSBM0057E = "NSBM0057E";

    /**
     * The record cannot be delete. Table Name: [@], Key Field Name: [@], Key Value: [@]
     */
    public static final String NSBM0079E = "NSBM0079E";

    /**
     * "@" does not exist.
     */
    public static final String NSBM0122E = "NSBM0122E";

    /**
     * User @ has no permissions to operate this application.
     */
    public static final String ZZSM4300E = "ZZSM4300E";

    /**
     * The process completed successfully .
     */
    public static final String NSBM0005I = "NSBM0005I";

    /**
     * [@] already exists.
     */
    public static final String NSBM0016E = "NSBM0016E";

    /**
     * The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

    /**
     * Qty must be entered.
     */
    public static final String NSBM0041E = "NSBM0041E";

    /**
     * The parameter in "@ Quantity" is invalid. Enter 1 or greater.
     */
    public static final String NSBM0063E = "NSBM0063E";

    /** Please select item(s). */
    public static final String NSBM0042E = "NSBM0042E";

    // add start 2016/07/13 CSA Defect#11672
    /**
     * Service call has been created against this mods plan. If you
     * would like to continue, please click 'Submit' again.
     */
    public static final String NSBM0169W = "NSBM0169W";
    // add end 2016/07/13 CSA Defect#11672
}
