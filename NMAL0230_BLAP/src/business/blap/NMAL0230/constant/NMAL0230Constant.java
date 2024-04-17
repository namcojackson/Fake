/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0230.constant;

/**
 *<pre>
 * NMAL0230Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         C.Tanaka        Create          CSA
 *</pre>
 */
public class NMAL0230Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL0230";

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

    /**
     * The download information could not be found. Go back to Search
     * and try again.
     */
    public static final String NMAM8298E = "NMAM8298E";

    // --------------------------------
    // SQL
    // --------------------------------
    /** CUSA CMPSN Const Name */
    public static final String CUSA_MDSE = "CUSA_MDSE";

    /** UPDATE */
    public static final String UPDATE = "UPDATE";

    /** Active */
    public static final String ACTIVE = "Active";

    /** Inactive */
    public static final String INACTIVE = "Inactive";

    // --------------------------------
    // CSV
    // --------------------------------
    /** CSV File Name Download */
    public static final String CSV_FILE_NAME_DOWNLOAD = "NMAL0230_BOMSearch";

    /** CSV File Extension */
    public static final String CSV_FILE_EXT = ".csv";

    /** CSV Fetch size */
    public static final int FETCH_SIZE_MAX = 1000;

    /** BOM */
    public static final String BOM = "BOM";

    /** Component */
    public static final String COMPONENT = "Component";

    /** CSV: BOM Item# */
    public static final String CSV_BOM_ITEM = "BOM Item#";

    /** CSV: Component */
    public static final String CSV_CMP = COMPONENT;

    /** CSV: Description */
    public static final String CSV_DESC = "Description";

    /** CSV: BOM Type */
    public static final String CSV_BOM_TP = "BOM Type";

    /** CSV: Merch Type */
    public static final String CSV_MERCH_TP = "Merch Type";

    /** CSV: Prod Code */
    public static final String CSV_PROD_CD = "Prod Code";

    /** CSV Header: Cusa Set */
    public static final String CSV_CUSA_SET = "CUSA Set";

    /** CSV: Active */
    public static final String CSV_ACTIVE = ACTIVE;

    /** CSV: Revision */
    public static final String CSV_REV = "Revision";

    /** CSV: Revision Comment */
    public static final String CSV_REV_CMNT = "Revision Comment";

    /** CSV: Effective Start */
    public static final String CSV_EFF_FROM = "Effective Start";

    /** CSV: Effective End */
    public static final String CSV_EFF_THRU = "Effective End";

    /** CSV: Sequence1 */
    public static final String CSV_SEQ1 = "Sequence1";

    /** CSV: Sequence2 */
    public static final String CSV_SEQ2 = "Sequence2";

    /** CSV: Quantity */
    public static final String CSV_QTY = "Quantity";

    /** CSV: Max row */
    public static final int CSV_MAX_ROW = 65000;

    /** CUSA Global Company Code */
    public static final String CUSA_GLBL_CMPY_CD = "ABR";
}
