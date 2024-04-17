/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2580.constant;

/**
 *<pre>
 * NMAL2580Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   Fujitsu         R.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL2580Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL2580";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL2580Scrn00";

    // TODO [Template] if popup screen then use below.
    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    /** Business button Search */
    public static final String BTN_SEARCH = "Search";

    /** Business button Page Prev */
    public static final String BTN_PAGE_PREV = "PagePrev";

    /** Business button Page Next */
    public static final String BTN_PAGE_NEXT = "PageNext";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

}
