/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1010.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/19   Fujitsu         Tozuka          Create          R-WH002
 *</pre>
 */
public class NLCL1010Constant {

    /**
     * xxOpsTp: Edit (NLBL3030 Parameter)
     */
    public static final String XX_OPS_TP_EDIT = "E";

    /**
     * xxOpsTp: Inquiry (NLBL3030 Parameter)
     */
    public static final String XX_OPS_TP_INQUIRY = "I";

    /**
     * Business Id
     */
    public static final String BUSINESS_ID = "NLCL1010";

    /**
     * Screen Id
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * A
     */
    public static final String TABLE_A = "A";

    /**
     * A1
     */
    public static final String TABLE_A_LEFT = "A1";

    /**
     * A2
     */
    public static final String TABLE_A_RIGHT = "A2";

    /**
     * Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Add
     */
    public static final String BTN_ADD = "Add";

    /**
     * Popup close button
     */
    public static final String POPUP_CLOSE = "CMN_Close";

    /**
     * NLCL1010T010
     */
    public static final String FUNCTION_INQUIRY = BUSINESS_ID + "T010";

    /**
     * NLCL1010T020
     */
    public static final String FUNCTION_UPDATE = BUSINESS_ID + "T020";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    /**
     * The last character of messageID When the error occurs.
     */
    public static final String MESSAGE_KIND_ERROR = "E";

    /**
     * The last character of messageID When the warning occurs.
     */
    public static final String MESSAGE_KIND_WARNING = "W";

    /**
     * The same Code cannot be selected for @ and @.
     */
    public static final String NLCM0035E = "NLCM0035E";

    /**
     * Please enter at least one search criteria
     */
    public static final String NLCM0114E = "NLCM0114E";

    /**
     * The chronological sequence between the dates in the "@" field is wrong
     */
    public static final String NLCM0115E = "NLCM0115E";

    /**
     * No data found with this search criteria
     */
    public static final String NLCM0002I = "NLCM0002I";

    /**
     * Too many search results. Please narrow your search criteria and retry.
     */
    public static final String ZZZM9002W = "ZZZM9002W";

    /**
     * Old Merchandise Code required when Item Change Stock In selected
     */
    public static final String NLCM0116E = "NLCM0116E";

    /**
     * Serial Transaction Source type required when Serial Transaction Source Header# inputed
     */
    public static final String NLCM0117E = "NLCM0117E";

    /**
     * Merchandise is not Serialized Tangible
     */
    public static final String NLCM0118E = "NLCM0118E";

    /**
     * Merchandise is not Refurbish
     */
    public static final String NLCM0119E = "NLCM0119E";

    /**
     * Merchandise Code is not registered as "Original Merchandise Code" On Merchandise Master
     */
    public static final String NLCM0120E = "NLCM0120E";

    /**
     * The entered Merchandise Code does not exist in Master
     */
    public static final String NLCM0108E = "NLCM0108E";

    /**
     * Entered "Location Code" does not exist
     */
    public static final String NLCM0110E = "NLCM0110E";

    /**
     * The number of Detail rows reached to the maximum. No more Details can be registered
     */
    public static final String NLCM0025E = "NLCM0025E";

    /**
     * The Length of Serial # is not different from master's value
     */
    public static final String NLCM0121W = "NLCM0121W";

    /**
     * The Range of Serial # is not different from master's value
     */
    public static final String NLCM0122W = "NLCM0122W";

    /**
     * The target data for the update does not exist
     */
    public static final String NLCM0123E = "NLCM0123E";

    /**
     * [@] field is mandatory.
     */
    public static final String ZZZM9000E = "ZZZM9000E";

    /**
     * The process [@] has been successfully completed
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * This data has been updated by another user
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * [@] does not exist
     */
    public static final String NLCM0124W = "NLCM0124W";

    /** 
     * Time is invalid. Please use a valid format, [hh:mm] 
     */
    public static final String  NSZM0304E = "NSZM0304E"; // TODO pending
}
