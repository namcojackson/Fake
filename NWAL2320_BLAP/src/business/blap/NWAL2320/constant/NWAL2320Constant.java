package business.blap.NWAL2320.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 * 01/05/2017   Fujitsu         S.Ohki          Update          S21_NA#16757
 * 2016/12/22   Fujitsu         W.Honda         Update          S21_NA#16750
 * 2018/06/01   Fujitsu         M.Ohno          Update          S21_NA#26273
 *</pre>
 */
public class NWAL2320Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL2320";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL2320Scrn00";

    /** Row Of page Count VAR_CHAR_CONST */
    public static final String NWAL2320_ROW_PER_PAGE = "NWAL2320_ROW_PER_PAGE";

    /** Max Row Count VAR_CHAR_CONST */
    public static final String NWAL2320_MAX_ROW_CNT = "NWAL2320_MAX_ROW_CNT";

    // Add Start 2016/12/22 W.Honda S21_NA#16750
    /** Max Config Count */
    public static final int NWAL2320_MAX_CONFIG_CNT = 300;

    /** Max Line Count */
    public static final int NWAL2320_MAX_LINE_CNT = 3000;

    /** Max Return Config Count */
    public static final int NWAL2320_MAX_RTRN_CONFIG_CNT = 200;

    /** Max Return Line Count */
    public static final int NWAL2320_MAX_RTRN_LINE_CNT = 200;
    // Add End 2016/12/22 W.Honda S21_NA#16750

    /** ORD_SRC_REF_PREFIX */
    public static final String ORD_SRC_REF_PREFIX = "UPLOAD";

    /** Line Status Success */
    public static final String LINE_STS_SUCCESS = "SUCCESS";

    /** Line Status Error */
    public static final String LINE_STS_ERROR = "ERROR";

    // --------------------------------
    // File Extension
    // --------------------------------
    /** CSV Extension */
    public static final String EXT_CSV = ".csv";

    // --------------------------------
    // Message
    // --------------------------------

    /** The entered @ does not exist in Master  or "Inactive" item. */
    public static final String NWAM0181E = "NWAM0181E";

    /** Data insert failure. (table name is [@]) */
    public static final String NWAM0728E = "NWAM0728E";

    /** There is no data to be processed. */
    public static final String NWAM0794E = "NWAM0794E";

    /** CPO Order Number does not exist. */
    public static final String NWAM0799E = "NWAM0799E";

    /** @ item of the same order must be the same */
    public static final String NWAM0856E = "NWAM0856E";

    /** Error has occurred while uploading. Please verify the upload file format.  */
    public static final String NWAM0857E = "NWAM0857E";

    /** Upload file must contain at least 1 row */
    public static final String NWAM0858E = "NWAM0858E";

    /** Upload file has incorrect data. Please verify it. ( [@] row, [@]) */
    public static final String NWAM0859E = "NWAM0859E";

    /** The order was already cancelled or closed. */
    public static final String NWAM0862E = "NWAM0862E";

    // Add Start 2016/12/22 W.Honda S21_NA#16750
    /** Upload failed because the number of configurations of this file exceeded the upper limit [@]. */
    public static final String NWAM0917E = "NWAM0917E";

    /** Upload failed because the number of lines in Order exceeded the upper limit [@]. */
    public static final String NWAM0918E = "NWAM0918E";
    // Add End 2016/12/22 W.Honda S21_NA#16750

    /** Order qty should be equal to minimum qty or more. */
    public static final String NWZM1488E = "NWZM1488E";

    /** The return qty must be a negative number. */
    public static final String NWZM1596E = "NWZM1596E";

    /** This record exceeded the upper limit [@].This and bellow records were not able to be uploaded. */
    public static final String ZYEM0013E = "ZYEM0013E";

    // 2018/06/01 S21_NA#26273 add start
    /** Order qty should be equal to minimum qty or more.(Mimimum Qty=[@]) */
    public static final String NWZM2023E = "NWZM2023E";

    /** Order qty should be equal to maximum qty or less.(Maximum Qty=[@]) */
    public static final String NWZM2024E = "NWZM2024E";

    /** Order qty should be multiple of increment qty.(Increment Qty=[@]) */
    public static final String NWZM2025E = "NWZM2025E";
    // 2018/06/01 S21_NA#26273 add end
    // --------------------------------
    // Download Template File Header
    // --------------------------------

    /** New Order File Header */
    public static final String[] TMPL_DL_HEADER_FOR_NEW_ORDER = new String[] {
                                                "Group Number",         // 0
                                                "Customer PO",
                                                "PO Agreement No",
                                                "Negotiated Deal",
                                                "Order Category",
                                                "Reason",               // 5
                                                "Item",
                                                "Qty",
                                                "Selling Price",
                                                "Line Configuration#",
                                                "Line Category",        // 10
                                                "Bill To Location",
                                                "Ship To Location",
                                                "Sold To Location", // 2017/01/05 S21_NA#16757 Add
                                                "Sales Rep",
                                                "Warehouse",            // 15
                                                "Sub Warehouse",
                                                "Shipping Instructions"};

    /** Existing Order File Header */
    public static final String[] TMPL_DL_HEADER_FOR_EXS_ORDER = new String[] {
                                                "Order Number",         // 0
                                                "Item",
                                                "Qty",
                                                "Selling Price",
                                                "Line Configuration#",
                                                "Line Category",        // 5
                                                "Bill To Location",
                                                "Ship To Location",
                                                "Sales Rep",
                                                "Warehouse",
                                                "Sub Warehouse",        // 10
                                                "Shipping Instructions"};

    /** New RMA File Header */
    public static final String[] TMPL_DL_HEADER_FOR_NEW_RMA = new String[] {
                                                "Group Number",         // 0
                                                "Customer PO",
                                                "PO Agreement No",
                                                "Negotiated Deal",
                                                "Order Category",
                                                "Reason",               // 5
                                                "Item",
                                                "Qty",
                                                "Selling Price",
                                                "Line Configuration#",
                                                "Line Category",        // 10
                                                "Bill To Location",
                                                "Ship To Location",
                                                "Sold To Location", // 2017/01/05 S21_NA#16757 Add
                                                "Serial Number",
                                                "Sales Rep",            // 15
                                                "Warehouse",
                                                "Return Reason",
                                                "Shipping Instructions"};

    /** Existing RMA File Header */
    public static final String[] TMPL_DL_HEADER_FOR_EXS_RMA = new String[] {
                                                "Order Number",         // 0
                                                "Item",
                                                "Qty",
                                                "Selling Price",
                                                "Line Configuration#",
                                                "Line Category",        // 5
                                                "Bill To Location",
                                                "Ship To Location",
                                                "Serial Number",
                                                "Sales Rep",
                                                "Warehouse",            // 10
                                                "Return Reason",
                                                "Shipping Instructions"};

    /** Download Add Header */
    public static final String[] DL_ADD_HEADER = new String[] {
                                                "Status",
                                                "Validation Message"};

    /**
     * TMPL_TP
     */
        public static enum TMPL_TP {
        /** NEW_SALES */
        NEW_SALES,
        /** EXIST_SALES */
        EXIST_SALES,
        /** NEW_RMA */
        NEW_RMA,
        /** EXIST_RMA */
        EXIST_RMA,
    }


}
