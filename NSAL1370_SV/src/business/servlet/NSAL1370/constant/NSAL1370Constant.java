/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1370.constant;

/**
 *<pre>
 * NSAL1370Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Osawa         Create          N/A
 * 2017/10/26   Hitachi         K.Kojima        Update          QC#21556
 *</pre>
 */
public class NSAL1370Constant {

    /** Business ID */
    public static final String BIZ_ID = "NSAL1370";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NSAL1370Scrn00";

    // --------------------------------
    /** Number of parameters */
    // START 2017/10/26 K.Kojima [QC#21556,MOD]
    // public static final int PRM_CNT = 6;
    public static final int PRM_CNT = 7;
    // END 2017/10/26 K.Kojima [QC#21556,MOD]

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
    public static final String NSAM0626E = "NSAM0626E";

    /** @ can't be determined. Please contact system administrator. */
    public static final String NSAM0636E = "NSAM0636E";

    /** [@] should be greater than [@]. */
    public static final String NSAM0640E = "NSAM0640E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

}
