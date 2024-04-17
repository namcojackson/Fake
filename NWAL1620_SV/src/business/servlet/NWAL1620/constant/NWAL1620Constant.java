/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1620.constant;

/**
 *<pre>
 * NWAL1620Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/08   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/20   Fujitsu         S.Yoshida       Update          QC#2177
 * 2017/09/20   Fujitsu         T.Murai         Update          S21_NA#18859(Sol#154)
 *</pre>
 */
public class NWAL1620Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1620";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1620Scrn00";

    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    /** Button : CMN_OK */
    public static final String BTN_CMN_OK = "CMN_OK";

    /** Button : CMN_Cancel */
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

    /** CPO Order Number for the parameter is not set. */
    public static final String NWZM1205E = "NWZM1205E";

    /** "Configuration Category Code" is required. */
    public static final String NWZM1409E = "NWZM1409E";

    /** DS CPO Position Number is required. */
    public static final String NWZM1374E = "NWZM1374E";

    /** "DS CPO Line Number" is required. */
    public static final String NWZM1411E = "NWZM1411E";

    /** The number of copy must be entered. */
    public static final String NWAM0693E = "NWAM0693E";

    /** The specified format is incorrect. It must be @ */
    public static final String NWAM0664E = "NWAM0664E";

    // Add Start 2017/09/20 S21_NA#18859
    /** You cannot check the both "@" and "@". */
    public static final String NWAM0943E = "NWAM0943E";
    // Add End 2017/09/20 S21_NA#18859

    /** [@] is not matched with [@] of order. */
    public static final String NLBM1269E = "NLBM1269E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // MODE
    // --------------------------------
    /** COPY MODE: Copy(01) */
    public static final String COPY_MODE = "01";

    /** COPY MODE: Copy From(02) */
    public static final String COPY_FROM_MODE = "02";

    /** MODE: Header Level(01) */
    public static final String HEADER_MODE = "01";

    /** MODE: Config Level(02) */
    public static final String CONFIG_MODE = "02";

    /** MODE: Line Level(03) */
    public static final String LINE_MODE = "03";

}
