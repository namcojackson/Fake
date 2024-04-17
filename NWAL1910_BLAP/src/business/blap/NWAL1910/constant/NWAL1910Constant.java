/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1910.constant;

/**
 *<pre>
 * NWAL1910Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/13   Fujitsu         M.Ishii          Create          N/A
 **/
public class NWAL1910Constant {

    /** PROCESS_LVL_HEADER */
    public static final String PROCESS_LVL_HEADER = "01";

    /** Mode Order Entry */
    public static final String MODE_ORDER_ENTRY = "1";

    /** Mode Supply Quote Entry */
    public static final String MODE_SUPPLY_QUOTE = "2";

    /** Mode Scheduling Agreement */
    public static final String MODE_SCHEDULE_AGREEMENT = "3";

    /** Default Line Number = 1 */
    public static final String DEFAULT_LINE_NUM = "1";

    /** YYYYMMDDHHMISS */
    public static final String YYYYMMDDHHMISS = "YYYYMMDDHHMISS";

    /** "Round Factor" for fuzzy search */
    public static final String ROUND_FACTOR = "RND%";

    /** Tax */
    public static final String TAX = "TAX";
    // --------------------------- Message ID ---------------------------
    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search result was found. */
    public static final String NWAM0006I = "NWAM0006I";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";
}
