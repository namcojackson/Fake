/**
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 */
package business.servlet.NMAL2630.constant;

/**
 * <pre>
 * Resource Search  NMAL2630Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/20   Fujitsu         N.Sugiura       Create          N/A
 * 2017/02/23   Fujitsu         K.Ishizuka      Update          QC#16481
 * </pre>
 */
public class NMAL2630Constant {

    // [0]:Button Name [1]:Event Name [2]:Button Lavel

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /** Button Search */
    public static final String[] BTN_SEARCH = {"Search", "Search" };

    /** Button Insert */
    public static final String BTN_INSERT = "InsertRow";

    /** Button Delete */
    public static final String BTN_DELETE = "DeleteRow";

    /** Screen ID */
    public static final String SCREEN_ID = "NMAL2630Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NMAL2630";

    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    
    // QC#16481
    /** A past date cannot be entered into the "Date Effective From". */
    public static final String NMAM0185E = "NMAM0185E";
}
