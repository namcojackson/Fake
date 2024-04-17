/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0010.constant;

/**
 *<pre>
 * NWWL0010Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/19   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0010Constant {

    /** Business Application ID */
    public static final String BIZ_ID = "NWWL0010";

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

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** CSV_HDR_SCHD */
    public static final String[] CSV_HDR = new String[] {"Notif ID", "Name", "Description", "Business Area", "Sub Area", "Start Date", "End Date" };

    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;

    /** CSV Download Count */
    public static final int CSV_LIMIT_COUNT = 1000;

    /** CSV Extension */
    public static final String CSV_EXT = ".csv";

    /** Table Name NTFY_BIZ_AREA_TP */
    public static final String TABLE_NM_NTFY_BIZ_AREA_TP = "NTFY_BIZ_AREA_TP";

    /** Table Name NTFY_SUB_AREA_TP */
    public static final String TABLE_NM_NTFY_SUB_AREA_TP = "NTFY_SUB_AREA_TP";

    /** Date Format (yyyyMMdd) */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /** Date Format (MM/dd/yyyy) */
    public static final String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";

}
