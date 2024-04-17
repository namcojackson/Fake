/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7110.constant;

/**
 *<pre>
 * NMAL7110Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NMAL7110Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7110";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NMAL7110Scrn00";

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

    /** ZZZM9003I The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** [@] is not selected. */
    public static final String NMAM0014E = "NMAM0014E";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NMAM0182E = "NMAM0182E";

    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;

    /** CSV File Name Download */
    public static final String CSV_DOWNLOAD_FILE_NAME = "NMAL7110";

    /** CSV Header For Download */
    public static final String[] CSV_DOWNLOAD_HEADER = new String[] {"Contract ID", "Price Contract Name", "Price Contract#", "CSAP Contract", "Status", "Effective Start", "Effective Date To" };

}
