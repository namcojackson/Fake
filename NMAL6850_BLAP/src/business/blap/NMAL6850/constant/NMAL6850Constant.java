/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6850.constant;

/**
 * <pre>
 * NMAL6850 Supplier Search
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/24   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NMAL6850Constant {

    // ------------------- Event Name -------------------

    /**
     * The event name of "INIT".
     */
    public static final String EVENT_NM_INIT = "NMAL6850_INIT";

    /**
     * The event name of "OnClick_Search".
     */
    public static final String EVENT_NM_ON_CLICK_SEARCH = "NMAL6850Scrn00_OnClick_Search";

    /**
     * The event name of "OnClick_Add".
     */
    public static final String EVENT_NM_ON_CLICK_NEW = "NMAL6850Scrn00_OnClick_New";

    /**
     * The event name of "PageNext".
     */
    public static final String EVENT_NM_PAGE_NEXT = "NMAL6850Scrn00_PageNext";

    /**
     * The event name of "PagePrev".
     */
    public static final String EVENT_NM_PAGE_PREV = "NMAL6850Scrn00_PagePrev";

    /**
     * The event name of "OpenWin_Supplier".
     */
    public static final String EVENT_NM_OPEN_WIN_SUPPLIER = "NMAL6850Scrn00_OpenWin_Supplier";

    /**
     * The event name of "CMN_Reset".
     */
    public static final String EVENT_NM_CMN_RESET = "NMAL6850Scrn00_CMN_Reset";

    /**
     * The event name of "CMN_Return".
     */
    public static final String EVENT_NM_CMN_RETURN = "NMAL6850Scrn00_CMN_Return";

    /**
     * The event name of "NMAL6850_NMAL6860".
     */
    public static final String EVENT_NMAL6850_NMAL6860 = "NMAL6850_NMAL6860";

    // ------------------- The message code -------------------
    /**
     * No search results found.
     */
    public static final String MSG_CD_NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String MSG_CD_NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    public static final String MSG_CD_NZZM0002I = "NZZM0002I";

}
