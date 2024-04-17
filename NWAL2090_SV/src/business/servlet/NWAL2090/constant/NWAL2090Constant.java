/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2090.constant;

/**
 *<pre>
 * NWAL2090Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/09   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL2090Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL2090";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL2090Scrn00";

    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** Common button 8 */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** Common button 10 */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    /** CMN_OK Button */
    public static final String BTN_CMN_OK = "CMN_Clear";

    /** CMN_Cancel Button */
    public static final String BTN_CMN_CANCEL = "CMN_Close";
    // --------------------------------
    // Message ID
    // --------------------------------
    /** Could not get the initial parameter. */
    public static final String NWAM0270E = "NWAM0270E";

    /** Input Parameter Global Company Code is mandatory field. */
    public static final String NMZM0009E = "NMZM0009E";

    /** CPO Order Number for the parameter is not set. */
    public static final String NWZM1205E = "NWZM1205E";

    /** Comment is mandantory. */
    public static final String NWAM0700E = "NWAM0700E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

}
