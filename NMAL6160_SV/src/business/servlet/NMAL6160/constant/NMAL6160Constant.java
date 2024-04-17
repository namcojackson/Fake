/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6160.constant;

/**
 *<pre>
 * Multi Candinate Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/15   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL6160Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL6160";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL6160Scrn00";

    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    /** Filter Button Event Name */
    public static final String BTN_FILTER_EVENT_NM = "Filter";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** param Length */
    public static final int PARAM_LG = 5;

    /** NMAL6760 param length */
    public static final int PRM_NMAL6760 = 24;

    /** NMAL2630 param length */
    public static final int PRM_NMAL2630 = 9;

    /** BIZ_ID_NMAL6760 */
    public static final String BIZ_ID_NMAL6760 = "NMAL6760";

    /** BIZ_ID_NMAL2630 */
    public static final String BIZ_ID_NMAL2630 = "NMAL2630";

}
