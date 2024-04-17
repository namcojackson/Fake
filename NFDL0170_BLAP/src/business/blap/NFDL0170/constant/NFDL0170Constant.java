/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0170.constant;

/**
 *<pre>
 * Customer Account Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Hitachi         T.Tsuchida      Create          QC#19574
 * 2017/09/27   Hitachi         T.Tsuchida      Update          QC#21373
 * 2018/06/05   Fujitsu         Y.Matsui        Update          QC#26456
 *</pre>
 */
public class NFDL0170Constant {

    /** Business ID */
    public static final String BIZ_ID = "NFDL0170";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NFDL0170Scrn00";

    /** Table ID */
    public static final String TBL_ID = "A";

    // --------------------------------
    // Message ID
    // --------------------------------
    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** No record found. */
    public static final String NFCM0048I = "NFCM0048I";

    // --------------------------------
    // Fuzzy Search Value
    // --------------------------------
    /** percent. */
    public static final String PERCENT = "%";

    // START 2018/06/05 Y.Matsui [QC#26456,MOD]
    // START 2017/09/27 T.Tsuchida [QC#21373,ADD]
    /** Include Closed Invoices : Include Closed Invoices */
    public static final String INCLUDE_CLOSED_INVOICES = "NFDL0170_INCL_CLOSED_INVOICES";
    // END 2017/09/27 T.Tsuchida [QC#21373,ADD]
    // END 2018/06/05 Y.Matsui [QC#26456,MOD]
}
