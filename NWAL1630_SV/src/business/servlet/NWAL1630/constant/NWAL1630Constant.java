/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1630.constant;

/**
 *<pre>
 * NWAL1630Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/22   Fujitsu         M.suzuki        Update          S21_NA#2140
 * 2017/09/22   Fujitsu         T.Ogura         Update          S21_NA#18859(Sol#154)
 *</pre>
 */
public class NWAL1630Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1630";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1630Scrn00";

    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    /** Price Button. */
    public static final String BTN_PRICE = "search_PriceList";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Could not get the initial parameter. */
    public static final String NWAM0270E = "NWAM0270E";

    /** [@] or [@] must be entered. */
    public static final String NMAM0207E = "NMAM0207E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";
    // 2016/02/22 S21_NA#2140 Add Start ------------
    /** MODE_REFERENCE = "10" */
    public static final String MODE_REFERENCE = "10";
    // 2016/02/22 S21_NA#2140 Add End ------------

    // 2017/09/22 QC#18859 Add Start
    // --------------------------------
    // Other
    // -------------------------------
    /** NSAL1240 MODE_CD : 02 */
    public static final String NSAL1240_MODE_02 = "02";

    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";
    // 2017/09/22 QC#18859 Add End
}
