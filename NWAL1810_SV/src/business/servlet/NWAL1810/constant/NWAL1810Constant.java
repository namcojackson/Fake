/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1810.constant;

/**
 *<pre>
 * NWAL1810Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL1810Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1810";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1810Scrn00";

    /** Number of parameters. */
    public static final int PRM_NUM = 5;

    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** An input parameter, [@], has not been set. */
    public static final String NZZM0012E = "NZZM0012E";

    /** If [@], please set [@]. */
    public static final String NWAM0624E = "NWAM0624E";

    /** Please select either Config or Line. */
    public static final String NWAM0688E = "NWAM0688E";

    /** Could not get the initial parameter. */
    public static final String NWAM0270E = "NWAM0270E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Tab Summary. */
    public static final String TAB_SUMMARY = "TAB_Summary";

    /** Tab Detail. */
    public static final String TAB_DETAIL = "TAB_Detail";

    /** Level Display All. */
    public static final String LVL_DISP_ALL = "All";

    /** Level Display Configuration. */
    public static final String LVL_DISP_CONF = "Config";

    /** Level Display Line. */
    public static final String LVL_DISP_LINE = "Line";

    /** Level Code All. */
    public static final String LVL_CD_ALL = "1";

    /** Level Code Configuration. */
    public static final String LVL_CD_CONF = "2";

    /** Level Code Line. */
    public static final String LVL_CD_LINE = "3";

    /** Source Name Order. */
    public static final String SOURCE_NM_ORDER = "ORDER";

    /** Source Name Quote. */
    public static final String SOURCE_NM_QUOTE = "QUOTE";

    /** Source Name Schedule. */
    public static final String SOURCE_NM_SCHEDULE = "SCHEDULE";

    /** Source Id Order. */
    public static final String SOURCE_ID_ORDER = "1";

    /** Source ID Quote. */
    public static final String SOURCE_ID_QUOTE = "2";

    /** Source Id Schedule. */
    public static final String SOURCE_ID_SCHEDULE = "3";

    /** Default Code Summary. */
    public static final String DEF_CD_SUMMARY = "S";

    /** Default Code Detail. */
    public static final String DEF_CD_DETAIL = "D";
}
