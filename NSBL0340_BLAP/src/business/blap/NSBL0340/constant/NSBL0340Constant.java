/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0340.constant;

/**
 *<pre>
 * Service Task Summary
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSBL0340Constant {

    /** SCRN_ID : NSBL0340Scrn00 */
    public static final String SCRN_ID = "NSBL0340Scrn00";

    /** BUSINESS_ID : NSBL0340 */
    public static final String BUSINESS_ID = "NSBL0340";

    /** DOWNLOAD_LIMIT_COUNT */
    public static final int DOWNLOAD_LIMIT_COUNT = 65535;

    /** HH24MISS */
    public static final String TIME_FORMAT_PRE = "HH24MISS";

    /** HH24:MI */
    public static final String TIME_FORMAT = "HH24:MI:SS";

    /** YYYYMMDD */
    public static final String DATE_FORMAT_PRE = "YYYYMMDD";

    /** "MM/DD/YYYY */
    public static final String DATE_FORMAT = "MM/DD/YYYY";

    /** padding 00 */
    public static final String PAD00 = "FM00";

    /** ASTERISK */
    public static final String ASTERISK = "*";

    /** FETCH_SIZE_MAX = 1000     */
    public static final int FETCH_SIZE_MAX = 1000;

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** The process completed successfully . */
    public static final String NZZM0002I = "NZZM0002I";

    /** DB error occurred. */
    public static final String NWDM0007E = "NWDM0007E";
}
