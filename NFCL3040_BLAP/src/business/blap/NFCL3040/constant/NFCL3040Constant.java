package business.blap.NFCL3040.constant;

/**
 *<pre>
 * Batch Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/13   Hitachi         J.Kim           Create          Initial
 *</pre>
 */
public interface NFCL3040Constant {

    public static final String TABLE_A = "A";

    /** percent. */
    public static final String PERCENT = "%";

    /** Fetch size */
    public static final int FETCH_SIZE = 1000;

    /** Download size */
    public static final int CSV_LIMIT_COUNT = 65536;

    /** CSV extension */
    public static final String CSV = ".csv";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    // --------------------------------
    // CSV File Download
    // --------------------------------
    /** CSV File Name Download */
    public static final String CSV_FILE_NAME_DOWNLOAD = "NFCL3040_Batch_Inquiry";

    /** CSV Header For Download */
    public static final String[] CSV_DOWNLOAD_HEADER = new String[] { 
                                                                         "Batch Name"
                                                                        ,"Receipt Source"
                                                                        ,"Batch Date"
                                                                        ,"Batch Status"
                                                                        ,"Ctrl Count"
                                                                        ,"Control Amount"
                                                                        ,"Lockbox File Nm"
                                                                        ,"Lockbox Name"
                                                                        ,"Lockbox Batch"
                                                                        };
}
