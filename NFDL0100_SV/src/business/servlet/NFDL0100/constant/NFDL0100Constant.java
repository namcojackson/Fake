package business.servlet.NFDL0100.constant;

/** 
 *<pre>
 * Print Invoice
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   CSAI            K.Lee           Create          N/A
 * 2016/05/20   Hitachi         T.Tsuchida      Create          QC#7639
 *</pre>
 */
public interface NFDL0100Constant {

    /**
     * Business Application ID
     */
    public static final String BUSINESS_APPLICATION_ID = "NFDL0100";

    /**
     * Table : A
     */
    public static final String TABLE_A = "A";

    // START 2016/05/20 T.Tsuchida [QC#7639,MOD]
    /**
     * parameter index : 9
     */
    public static final int PARAM_INDEX_9 = 9;

    /**
     * Attachments Screen Display Mode : Upload-Only
     */
    public static final String PARAMS_DISPLAY_MODE = "Upload-Only";

    /**
     * Attachments Screen Grouping Text : Bill To Customer Account Code
     */
    public static final String PARAMS_BILL_TO_CUST_ACCT_CD_KEY = "BILL_TO_CUST_ACCT_CD";

    /**
     * Attachments Screen Grouping Text : Bill To Customer Code
     */
    public static final String PARAMS_BILL_TO_CUST_CD_KEY = "BILL_TO_CUST_CD";

    /**
     * Attachments Screen Function Name
     */
    public static final String PARAMS_FUNCTION_NAME = "Print Invoice Attachments";

    /**
     * Attachments Screen Primary Key
     */
    public static final String PARAMS_PRIMARY_KEY = "Customer Account# And Bill To";

    /**
     * Attachments Screen Attachments Limit
     */
    public static final String PARAMS_UPPER_KEY = "NFDL0100_ATT_LIMIT";

    /**
     * Attachments Screen Authorize File Extensions
     */
    public static final String PARAMS_EXTENSION_KEY = "NFDL0100_AUTHORIZE_FILE_EXT";

    /**
     * Attachments Screen Authorize File Volume
     */
    public static final String PARAMS_SIZE_KEY = "NFDL0100_AUTHORIZE_FILE_VOL";
    // END 2016/05/20 T.Tsuchida [QC#7639,MOD]
}
