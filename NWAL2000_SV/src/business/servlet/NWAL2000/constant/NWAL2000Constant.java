/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2000.constant;

/**
 *<pre>
 * NWAL2000Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/08   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL2000Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL2000";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL2000Scrn00";

    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** Common button 8 */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** Common button 10 */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    /** CMN_OK Button */
    public static final String BTN_CMN_OK = "CMN_OK";

    /** CMN_Cancel Button */
    public static final String BTN_CMN_CANCEL = "CMN_Cancel";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Could not get the initial parameter. */
    public static final String NWAM0270E = "NWAM0270E";

    /** Mode is required. */
    public static final String NWZM0012E = "NWZM0012E";

    /** Input Parameter Global Company Code is mandatory field. */
    public static final String NMZM0009E = "NMZM0009E";

    /** DS Order Line Category Code is required. */
    public static final String NWZM1518E = "NWZM1518E";

    /** "Direct Sales Order Position Number" is required. */
    public static final String NWZM1408E = "NWZM1408E";

    /** 'CPO Detail Line Number' has not been entered. */
    public static final String NWAM0115E = "NWAM0115E";

    /** 'CPO Detail Line Sub Number' has not been entered. */
    public static final String NWAM0116E = "NWAM0116E";

    /** "Order Quantity" is required. */
    public static final String NWZM0046E = "NWZM0046E";

    /** The quantity must be less than or equal to Order Quantity. */
    public static final String NWAM0698E = "NWAM0698E";

    /** Numbers smaller than 0 cannot be selected for Quantity. */
    public static final String NWAM0042E = "NWAM0042E";

    /** The error fields then re-execute. */
    public static final String NWAM0134E = "NWAM0134E";

    /** Specified value for Mode is invalid. */
    public static final String NWZM0013E = "NWZM0013E";

    /** Error Message */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Variable
    // --------------------------------
    /** MODE : Config Level(01). */
    public static final String CONFIG_MODE = "01";

    /** MODE : Line Level(02). */
    public static final String LINE_MODE = "02";

    /** MODE : Header Level(03). */
    public static final String HEADER_MODE = "03";

    /** comma. */
    public static final String STR_COMMA = ",";

    /** not applicable. */
    public static final String STR_N_A = "N/A";

    /** number of parameters. */
    public static final int CNT_PARAM = 6;
}
