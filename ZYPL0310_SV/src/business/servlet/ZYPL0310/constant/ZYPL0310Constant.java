package business.servlet.ZYPL0310.constant;

public class ZYPL0310Constant {
    /**
     * My Screen ID
     */
    public static String MY_SCRN_ID = "ZYPL0310Scrn00";

    /**
     * Screen Display Mode : Read-Only
     */
    public static String DISPLAY_MODE_READ_ONLY = "Read-Only";

    /**
     * Screen Display Mode : Upload-Only
     */
    public static String DISPLAY_MODE_UPLOAD_ONLY = "Upload-Only";

    /**
     * Screen Display Mode : Modification
     */
    public static String DISPLAY_MODE_MODIFICATION = "Modification";

    /**
     * Button - Attributes : Upload<br> { Name, new Name, Value,
     * Confirm Message ID }
     */
    public static String[] BTN_UPLOAD = {"Upload", "Upload", "Upload", "" };

    /**
     * Button - Attributes : Select All<br> { Name, new Name, Value,
     * Confirm Message ID }
     */
    public static String[] BTN_SELECT_ALL = {"SelectAll", "SelectAll", "SelectAll", "" };

    /**
     * Button - Attributes : Un Select All<br> { Name, new Name,
     * Value, Confirm Message ID }
     */
    public static String[] BTN_UN_SELECT_ALL = {"UnSelectAll", "UnSelectAll", "Un Select All", "" };

    /**
     * Button - Attributes : Delete<br> { Name, new Name, Value,
     * Confirm Message ID }
     */
    public static String[] BTN_DELETE = {"Delete", "Delete", "Delete", "ZZM8102I" };

    /**
     * Common Button - Attributes : Delete<br> { Name, new Name,
     * Value, Confirm Message ID }
     */
    public static String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear", "" };

    /**
     * Common Button - Attributes : Close<br> { Name, new Name,
     * Value, Confirm Message ID }
     */
    public static String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close", "" };

    /**
     * key to get "display mode" from ezd parameters.
     */
    public static int PARAM_DISPLAY_MODE = 0;

    /**
     * key to get "business application id" from ezd parameters.
     */
    public static int PARAM_EZBUSINESS_ID = 1;

    /**
     * key to get "attachments grouping text" from ezd parameters.
     */
    public static int PARAM_ATT_DATA_GRP_TXT = 2;

    /**
     * key to get "attachments limit (This is key of [NUM_CONST]
     * table.)" from ezd parameters.
     */
    public static int PARAM_ATT_LIMIT_NUM = 6;

    /**
     * key to get "authorize file extentions (This is key of
     * [VAR_CHAR_CONST ] table.)" from ezd parameters.
     */
    public static int PARAM_AUTHORIZE_FILE_EXT = 7;

    /**
     * key to get "authorize file volume(size) (This is key of
     * [NUM_CONST] table.)" from ezd parameters.
     */
    public static int PARAM_ATT_DATA_VOL = 8;

    /**
     * key to get "function name" from ezd parameters.
     */
    public static int PARAM_FUNCTION_NAME = 3;

    /**
     * key to get "primary key" from ezd parameters.
     */
    public static int PARAM_PRIMARY_KEY = 4;

    /**
     * key to get "business api for therefore" from ezd parameters.
     */
    public static int PARAM_BIZ_API_ID = 9;

    /**
     * key to get "document type list" from ezd parameters.
     */
    public static int PARAM_DOC_TYPE_CD_TBL_NM = 5;

    // Add Start 2019/05/09 QC#50015
    /**
     * key to get "document type constant value" from ezd parameters.
     */
    public static int PARAM_DOC_TYPE_CONST_VAL = 10;
    // Add End 2019/05/09 QC#50015

    /**
     * attachment data type : File
     */
    public static String ATT_DATA_TYPE_FILE = "FILE";

    /**
     * attachment data type : NOTE
     */
    public static String ATT_DATA_TYPE_NOTE = "NOTE";

    /**
     * attachment data type : Webpage(URL)
     */
    public static String ATT_DATA_TYPE_URL = "WEBPAGE(URL)";

    /**
     * attachment data type : Therefore
     */
    public static String ATT_DATA_TYPE_THEREFORE = "Therefore";

    /**
     * attachment data type code : File
     */
    public static String ATT_DATA_TYPE_CODE_FILE = "FL";

    /**
     * attachment data type code : NOTE
     */
    public static String ATT_DATA_TYPE_CODE_NOTE = "NT";

    /**
     * attachment data type code : Webpage(URL)
     */
    public static String ATT_DATA_TYPE_CODE_URL = "WP";

    /**
     * attachment data type code : Therefore
     */
    public static String ATT_DATA_TYPE_CODE_THEREFORE = "TF";

    /**
     * attachment data document type code table name
     */
    public static String ATT_DATA_DOC_TYPE_CD_TBL_NM = "ATT_DATA_DOC_TP";
}
