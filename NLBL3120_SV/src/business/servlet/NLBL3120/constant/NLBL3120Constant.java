/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3120.constant;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         S.Yoshida       Create          N/A
 *</pre>
 */

public class NLBL3120Constant {

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    /** Function Button 1 */
    public static final String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Function Button 2 */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    public static final String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Function Button 4 */
    public static final String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Function Button 5 */
    public static final String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Function Button 6 */
    public static final String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_DETELE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Button Select All */
    public static final String BTN_SELECT_ALL = "Select_All";

    /** Button Un-select All */
    public static final String BTN_UNSELECT_ALL = "UnSelect_All";

    /** Button All Expand */
    public static final String BTN_ALL_EXPAND = "All_Expand";

    /** Button All Collapse */
    public static final String BTN_ALL_COLLAPSE = "All_Collapse";

    /** Button Coord Work Bench */
    public static final String BTN_COORD_WRK_BENCH = "MoveWin_CoordWrkBench";

    /** Button Manage BO */
    public static final String BTN_MNG_BO = "MoveWin_MngBO";

    /** Button CoordSearch Info */
    public static final String BTN_COORD_INFO = "OpenWin_CoordSearchInfo";

    /** Button CarrInfo */
    public static final String BTN_CARR_INFO = "OpenWin_CarrInfo";

    /** Button Apply */
    public static final String BTN_APPLY = "Apply";

    /** Button SO Release */
    public static final String BTN_REL_SO = "Release_SO";

    /** Screen ID */
    public static final String SCREEN_ID = "NLBL3120Scrn00";

    /** Business ID */
    public static final String BIZ_ID = "NLBL3120";

    /** Read User*/
    public static final String FUNC_ID_READ = BIZ_ID + "T010";

    /** Update User */
    public static final String FUNC_ID_UPDATE = BIZ_ID + "T020";

    /** CLOSE */
    public static final String CLOSE = "CLOSE";

    // =================================================
    // Event Name
    // =================================================
    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** Event Name: OpenWin_LocInfoRtlWh */
    public static final String EVENT_NM_OPENWIN_LOCINFO_RTLWH = "OpenWin_LocInfoRtlWh";

    /** Event Name: OpenWin_ModelName */
    public static final String EVENT_NM_OPENWIN_MODEL = "OpenWin_ModelName";

    /** Event Name: OpenWin_SlsRepCode */
    public static final String EVENT_NM_OPENWIN_SLS_REP = "OpenWin_SlsRepCode";

    /** Event Name: OpenWin_CarrInfo */
    public static final String EVENT_NM_OPENWIN_CARR_INFO = "OpenWin_CarrInfo";

    /** Event Name: OpenWin_AplyCarrCode */
    public static final String EVENT_NM_OPENWIN_CARR_APLY = "OpenWin_AplyCarrCode";

    /** Event Name: OpenWin_CoordSearchCode */
    public static final String EVENT_NM_OPENWIN_COORD_SEARCH_CD = "OpenWin_CoordSearchCode";

    /** Event Name: OpenWin_CoordSearchInfo */
    public static final String EVENT_NM_OPENWIN_COORD_SEARCH_INFO = "OpenWin_CoordSearchInfo";

    /** Event Name: OpenWin_AplyCoordSearchCode */
    public static final String EVENT_NM_OPENWIN_APLY_COORD_SEARCH = "OpenWin_AplyCoordSearchCode";

    /** Please enter at least one of '@'. */
    public static final String NLBM1329E = "NLBM1329E";

    /** Please set at least one search criteria. */
    public static final String NLZM2276E = "NLZM2276E";

    /** The value for [@] must be bigger than [@]. */
    public static final String NLZM2277E = "NLZM2277E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** To ignore the warning hit the [@] button again. To correct the data hit the Apply button. */
    public static final String NLZM2282W = "NLZM2282W";

    /** Please select only one Check Box. */
    public static final String NZZM0009E = "NZZM0009E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
}
