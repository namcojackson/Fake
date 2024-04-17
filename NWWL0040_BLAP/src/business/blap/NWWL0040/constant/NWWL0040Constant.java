/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0040.constant;

/**
 *<pre>
 * NWWL0040Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/27   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0040Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWWL0040";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    // --------------------------------
    // Common
    // --------------------------------

    /** BIZ AREA TP NAME */
    public static final String BIZ_AREA_TP_TBL_NM = "NTFY_BIZ_AREA_TP";

    /** BIZ AREA TP NAME */
    public static final String SUB_AREA_TP_TBL_NM = "NTFY_SUB_AREA_TP";

    /** MAX_DT */
    public static final String MAX_DT = "99991231";

    // --------------------------------
    // CSV Common
    // --------------------------------
    /** CSV_HDR_SCHD */
    public static final String[] CSV_HDR = new String[] {"Dist ID", //
            "Name", //
            "Description", //
            "Business Area", //
            "Sub Area", //
            "Start Date", //
            "End Date" };

    /** Date Format (yyyyMMdd) */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /** Date Format (MM/dd/yyyy) */
    public static final String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";

    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;

    /** CSV Download Count */
    public static final int CSV_LIMIT_COUNT = 1000;

    /** CSV Extension */
    public static final String CSV_EXT = ".csv";

}
