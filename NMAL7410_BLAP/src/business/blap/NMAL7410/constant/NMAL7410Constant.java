/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7410.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/29   Fujitsu         Y.Murai         Create          N/A
 * </pre>
 */
public class NMAL7410Constant {
    /** Business ID */
    public static final String BIZ_ID = "NMAL7410";

    /** Period */
    public static final String PERIOD = ".";

    /** Comma */
    public static final String COMMA = ",";

    /** Blank */
    public static final String BLANK = "";

    /** Space */
    public static final String SPACE = " ";

    /** Index Number 3 */
    public static final int IDX_1 = 1;

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Index Number 8 */
    public static final int IDX_8 = 8;

    /** Index Number 100 */
    public static final int IDX_100 = 100;

    /** Download File Name */
    public static final String CSV_FILE_NAME_DOWNLOAD = "NMAL7410F00";

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

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** It failed to delete [@]. */
    public static final String NMAM0208E = "NMAM0208E";

    /** This data has been updated by another user. [ TableName = @ , key = @, value = @ ] */
    public static final String NMAM8175E = "NMAM8175E";

    /** Line is duplicated. Please confirm it. [CSMP Price List :@], [CSMP Generation# :@], [CSA Price List :@], [Effective Date From :@] */
    public static final String NMAM8620E = "NMAM8620E";

    /** Effective Date is duplicated. Please confirm it. [CSMP Price List :@], [CSMP Generation# :@], [CSA Price List :@] */
    public static final String NMAM8621E = "NMAM8621E";

    /** The number of Detail rows reached to the maximum.  No more Details can be registered. */
    public static final String NZZM0013E = "NZZM0013E";

    // ----------- Message Parameter -----------
    /** Check Box */
    public static final String MSG_PARAM_CHECK_BOX = "Line Selection Check";

    /** doProcess_Submit */
    public static final String DO_PROCESS_SUBMIT = "Submit";
}
