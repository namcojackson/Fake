/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2120.constant;

/**
 *<pre>
 * NFBL2120Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/14   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NFBL2120Constant {

    /** Business ID */
    public static final String BIZ_ID = "NFBL2120";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NFBL2120Scrn00";

    /** Event Name : Init */
    public static final String EVENT_NM_INIT = "Init";

    /** Event Name : CMN_Clear */
    public static final String EVENT_NM_CMN_CLEAR = "CMN_Clear";

    /** Event Name : OpenWin_ViewTherefore */
    public static final String EVENT_NM_VIEW_THEREFORE = "OpenWin_ViewTherefore";

    /** Event Name : PageJum */
    public static final String EVENT_NM_PAGE_JUMP = "PageJum";

    /** Event Name : PagePrev */
    public static final String EVENT_NM_PAGE_PREV = "PagePrev";

    /** Event Name : PageNext */
    public static final String EVENT_NM_PAGE_NEXT = "PageNext";

    /** Event Name : TBLColumnSort */
    public static final String EVENT_NM_TBL_SORT = "TBLColumnSort";

    /** Event Name : Search */
    public static final String EVENT_NM_SEARCH = "Search";

    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    /** Button Name : search */
    public static final String[] BTN_SEARCH = {"Search", "Search", "Search" };

    /** Button Name : show Document */
    public static final String[] BTN_SHOW_DOC = {"OpenWin_ViewTherefore", "OpenWin_ViewTherefore", "Show Document" };

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Please select item(s). */
    public static final String NFBM0261E = "NFBM0261E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

}
