/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7160.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/20   Fujitsu         Y.Murai         Create          N/A
 * </pre>
 */
public class NMAL7160Constant {

    /** Download File Name */
    public static final String CSV_FILE_NAME_DOWNLOAD = "NMAL7160F00";

    /** CSV File Extension */
    public static final String CSV_FILE_EXT = ".csv";

    /** CSV Fetch size */
    public static final int FETCH_SIZE_MAX = 1000;

    /** CSV: Max row */
    public static final int CSV_MAX_ROW = 65000;

    // ----------- Message ID -----------
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** Too many search results. Please narrow your search criteria and retry */
    public static final String ZZZM9002W = "ZZZM9002W";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** No search result were found. */
    public static final String NMAM0007I = "NMAM0007I";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** This data has been updated by another user. [ TableName = @ , key = @, value = @ ] */
    public static final String NMAM8175E = "NMAM8175E";

    // ----------- Message Parameter -----------
    /** Check Box */
    public static final String MSG_PARAM_CHECK_BOX = "Line Selection Check";

    /** doProcess_Submit */
    public static final String DO_PROCESS_SUBMIT = "Submit";
}
