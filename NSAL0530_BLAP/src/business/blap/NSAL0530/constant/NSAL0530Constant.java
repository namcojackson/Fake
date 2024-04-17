/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0530.constant;

/**
 *<pre>
 * Solution Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tomita        Create          N/A
 *</pre>
 */
public final class NSAL0530Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0530";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * FETCH_SIZE_MAX = 1000;
     */
    public static final int FETCH_SIZE_MAX = 1000;

    /**
     * DOWNLOAD_LIMIT_COUNT
     */
    public static final int DOWNLOAD_LIMIT_COUNT = 65000;

    /**
     * CSV file name
     */
    public static final String CSV_FILE_NAME = "_SOLUTION_INFO";

    /**
     * Please input at least one search condition.
     */
    public static final String NSAM0005E = "NSAM0005E";

    /**
     * More than @ records have been retrieved. Please change search
     * conditions and try again.
     */
    public static final String NSAM0006I = "NSAM0006I";

    /**
     * No search results found.
     */
    public static final String NSAM0194I = "NSAM0194I";

    /**
     * The process has been successfully completed.
     */
    public static final String NSAM0200I = "NSAM0200I";
}
