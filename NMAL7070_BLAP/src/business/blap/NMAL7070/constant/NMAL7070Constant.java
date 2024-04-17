/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7070.constant;

/**
 *<pre>
 * Supply Agreement Plan Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7070Constant {
    /** Business ID */
    public static final String BIZ_ID = "NMAL7070";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7070Scrn00";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** ZZZM9003I The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** [@] is not selected. */
    public static final String NMAM0014E = "NMAM0014E";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NMAM0182E = "NMAM0182E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** Active */
    public static final String STS_ACTIVE = "Active";

    /** Inactive */
    public static final String STS_INACTIVE = "Inactive";

    /** Deleted */
    public static final String STS_DELETED = "Deleted";

    /** CSV file name */
    public static final String CSV_FILE_NM = "NMAL7070AgreementPlanList";

    /** CSV file extension */
    public static final String CSV_FILE_EXTENSION = ".csv";

    /** CSV header upload */
    public static final String[] CSV_HEADER = new String[] {"RadioButton",
            "Plan ID", //
            "Plan Name", //
            "Plan Short Name", //
            "Plan Description", //
            "Plan Type", //
            "Status", //
            "Document Type", //
            "Term Cap", //
            "Eff From", //
            "Eff To", //
            "Creation Date", //
            "Created By", //
            "Last Update Date",//
            "Last Update By" };

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Limit Download RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** Search */
    public static final String EVENT_SEARCH = "Search";

    /** Search */
    public static final String EVENT_SAVE_SEARCH = "Save Search";

    /** Date format length */
    public static final int YYYYMMDD_LENGTH = 8;
}
