/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1360.constant;

/**
 *<pre>
 * NSAL1360Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/06   Hitachi         Y.Osawa         Create          N/A
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#20162
 *</pre>
 */
public class NSAL1360Constant {

    /** Business ID */
    public static final String BIZ_ID = "NSAL1360";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NSAL1360Scrn00";

    /** Number of parameters */
    // 2018/04/16 QC#20162 Mod Start
//    public static final int PRM_CNT = 3;
    public static final int PRM_CNT = 4;
    // 2018/04/16 QC#20162 Mod End
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

    /** Input Parameter @ is invalid. Process will be terminated. */
    public static final String NSAM0626E = "NSAM0626E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

}
