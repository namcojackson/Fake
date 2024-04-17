package business.servlet.NFCL3020.constant;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2015   CSAI            K.Lee           Create          Initial
 * 09/05/2016   Fujitsu         C.Tanaka        Update          QC#5521
 * 2016/10/07   Hitachi         K.Kojima        Update          QC#13432
 * 2024/02/23   Hitachi         S.Ikariya       Update          QC#63452
 *</pre>
 */
public interface NFCL3020Constant {

    /**
     * Function ID
     */
    public static final String FUNC_ID = "NFCL3020";

    /**
     * Table : B
     */
    public static final String TABLE_B = "B";

    /**
     * Function Update
     */
    public static final String FUNCTION_UPDATE = "NFCL3020T020";

    String TAB_AddInfo = "TAB_AddInfo";

    String TAB_Receipts = "TAB_Receipts";

    // QC#5521 ADD Start
    /** Screen ID */
    public static final String SCRN_ID = "NFCL3020Scrn00";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    static final String ZZM9000E = "ZZM9000E";

    // QC#5521 ADD End

    // START 2024/02/23 S.Ikariya [QC#63452, ADD]
    /**
     * Select Account Event
     */
    public static final String SELECT_ACCT_EVENT= "SelectAcct";

    // END 2024/02/23 S.Ikariya [QC#63452, ADD]
    // START 2016/10/07 K.Kojima [QC#13432,ADD]
    /** parameter index : 9 */
    public static final int PARAM_INDEX_9 = 9;

    /** Attachments Screen Display Mode : Read-Only */
    public static final String PARAMS_DISPLAY_MODE_READ_ONLY = "Read-Only";

    /** Attachments Screen Display Mode : Modification */
    public static final String DISPLAY_MODE_MODIFICATION = "Modification";

    /** Attachments Screen Function Name */
    public static final String PARAMS_FUNCTION_NAME = "Batch Entry Attachments";

    /** Attachments Screen Primary Key */
    public static final String PARAMS_PRIMARY_KEY = "Batch Name";

    /** Attachments Screen Attachments Limit */
    public static final String PARAMS_UPPER_KEY = "NFCL3020_ATT_LIMIT";

    /** Attachments Screen Authorize File Extensions */
    public static final String PARAMS_EXTENSION_KEY = "NFCL3020_AUTHORIZE_FILE_EXT";

    /** Attachments Screen Authorize File Volume */
    public static final String PARAMS_SIZE_KEY = "NFCL3020_AUTHORIZE_FILE_VOL";

    /** Attachment button */
    public static final String[] ATTACHMENT = {"OpenWin_AttachFile", "OpenWin_AttachFile", "Attachment" };

    // END 2016/10/07 K.Kojima [QC#13432,ADD]
}
