/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1150.constant;

/**
 *<pre>
 * Supply Watch Used / Allowed
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Hitachi         T.Kanasaka      Create          N/A
 * 2016/03/30   Hitachi         A.Kohinata      Update          QC#6066
 *</pre>
 */
public final class NSAL1150Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL1150";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * Please set at least one search criteria.
     */
    public static final String NSAM0017E = "NSAM0017E";

    /**
     * No search results found.
     */
    public static final String NSBM0123I = "NSBM0123I";

    /**
     * No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * Search results exceeded [@]. Only showing first @ records.
     */
    public static final String NSAM0024W = "NSAM0024W";

    /**
     * VarCharConst : TERM_TONER_ALLOWANCE_PCT_NM
     */
    public static final String TERM_TONER_ALLOWANCE_PCT_NM = "TERM_TONER_ALLOWANCE_PCT_NM";

    /**
     * VarCharConst : NSAL1150_TOTAL_LINE_TITLE
     */
    public static final String NSAL1150_TOTAL_LINE_TITLE = "NSAL1150_TOTAL_LINE_TITLE";

    /**
     * VarCharConst : NSAL1150_TOTAL_LINE_CONTR_NUM
     */
    public static final String NSAL1150_TOTAL_LINE_CONTR_NUM = "NSAL1150_TOTAL_LINE_CONTR_NUM";

    /**
     * CommonConst : LIMIT_THRU_DT
     */
    public static final String LIMIT_THRU_DT = "20991231";

    /** Fetch size for Download */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Download limit */
    public static final int DOWNLOAD_LIMIT_CNT = 65000;

    /** CSV file name */
    public static final String CSV_FILE_NAME = "_SUPPLY_WATCH_USED_ALLOWED";

    /** Max Percentage */
    public static final double MAX_PCT = 999.99;
}
