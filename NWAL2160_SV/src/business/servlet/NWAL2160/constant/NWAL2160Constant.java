/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2160.constant;

/**
 *<pre>
 * NWAL2160Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL2160Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL2160";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL2160Scrn00";

    // --------------------------------
    /** Number of parameters */
    public static final int PRM_CNT = 5;

    //--------------------------------
    /** PROC_MD_UPD */
    public static final String PROC_MD_UPD = "1";

    /** PROC_MD_REF */
    public static final String PROC_MD_REF = "2";

    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    /** + : Add Tier */
    public static final String BTN_ADD_TIER = "Add_Tier";

    /** - : Delete Tier */
    public static final String BTN_DEL_TIER = "Delete_Tier";

    //--------------------------------
    /** BTN_CTRL_INACTIVE */
    public static final int BTN_CTRL_INACTIVE = 0;

    /** BTN_CTRL_ACTIVE */
    public static final int BTN_CTRL_ACTIVE = 1;

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Input Parameter @ is invalid. Process will be terminated. */
    public static final String NWAM0199E = "NWAM0199E";

    /** @ can't be determined. Please contact system administrator. */
    public static final String NWAM0759E = "NWAM0759E";

    /** [@] should be greater than [@]. */
    public static final String NWAM0712E = "NWAM0712E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

}
