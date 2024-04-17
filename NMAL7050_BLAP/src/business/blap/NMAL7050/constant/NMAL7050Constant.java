/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7050.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Fujitsu         W.Honda         Create          N/A
 * 2017/02/15   Fujitsu         R.Nakamuara     Update          QC#17529
 *</pre>
 */

public class NMAL7050Constant {

    /** Screen ID */
    public static final String SCREEN_ID = "NMAL7050Scrn00";

    /** Function ID Assign */
    public static final String FUNC_ID_ASSIGN = "NMAL7050T10";

    /**  There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** Common Message */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** No search result were found.   */
    public static final String NMAM0007I = "NMAM0007I";

    // Add Start 2017/02/15 QC#17529
    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** Csv File Name */
    public static final String CSV_FILE_NAME = "NMAL7050 Service Meter Package Search";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Max Download Size */
    public static final int LIMIT_DL_ROWNUM = 65000;

    /** Date Length */
    public static final int YYYYMMDD_LENGTH = 8;
    // Add End 2017/02/15 QC#17529
}
