/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2530.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/20   Fujitsu         N.Sugiura       Create          N/A
 * 2017/02/23   Fujitsu         K.Ishizuka      Update          QC#16481
 *</pre>
 */
public class NMAL2530Constant {

    // [0]:Button Name [1]:Event Name [2]:Button Lavel

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /** Button Search */
    public static final String[] BTN_SEARCH = {"Search", "Search" };

    /** Screen ID */
    public static final String SCREEN_ID = "NMAL2530Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NMAL2530";

    /** Function Code */
    public static final String FUNCTION_CD = "20";

    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    
    // QC#16481
    /** A past date cannot be entered into the "Date Effective From". */
    public static final String NMAM0185E = "NMAM0185E";
}
