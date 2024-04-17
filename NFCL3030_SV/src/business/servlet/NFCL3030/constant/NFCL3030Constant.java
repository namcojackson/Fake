package business.servlet.NFCL3030.constant;

/**
 *<pre>
 * Receipt Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/05/12   Hitachi         K.Kojima        Update          QC#4492
 * 2016/05/16   Fujitsu         S.Fujita        Update          QC#6457
 * 2016/05/23   Hitachi         T.Tsuchida      Update          QC#4492
 * 2016/07/07   Hitachi         K.Kojima        Update          QC#10995
 * 2016/09/02   Hitachi         T.Tsuchida      Update          QC#14090
 * 2018/06/06   Hitachi         E.Kameishi      Update          QC#22758
 * 2018/08/22   CITS            K.Kameoka       Update          QC#24746
 * 2024/02/23   Hitachi         S.Ikariya       Update          QC#63452
 *</pre>
 */
public interface NFCL3030Constant {

    /**
     * Cash Application Mode : Cash Application
     */
    public static final String MODE_CASH_APPLICATION = "1";

    /**
     * Cash Application Mode : Cash Application Cancel
     */
    public static final String MODE_CASH_APPLICATION_CANCEL = "2";
    
    /**
     * Cash Application Mode : All
     */
    public static final String MODE_CASH_APPLICATION_ALL = "3";

    // START 2016/05/16 S.Fujita [QC#6457,ADD]
    /**
     * Journal Inquiry Mode : Journal Entry Inquiry
     */
    public static final String MODE_JNL_ENT_INQ = "4";
    // END 2016/05/16 S.Fujita [QC#6457,ADD]

    /**
     * Function ID
     */
    public static final String FUNC_ID = "NFCL3030";

    /**
     * Function Update
     */
    public static final String FUNCTION_UPDATE = "NFCL3030T020";
    
    /**
     * Function Reference
     */
    public static final String FUNCTION_REFERENCE = "NFCL3030T010";

    String TAB_AddInfo = "TAB_AddInfo";
    String TAB_Customer = "TAB_Customer";
    String TAB_Reversal = "TAB_Reversal";

    // START 2018/06/06 E.Kameishi [QC#22758,ADD]
    /**
     * Function Reverse
     */
    public static final String FUNCTION_REVERSE = "NFCL3030T030";
    // END 2018/06/06 E.Kameishi [QC#22758,ADD]
    // START 2016/05/12 K.Kojima [QC#4492,ADD]
    /**
     * Attachment button
     */
    public static final String[] ATTACHMENT = {"OpenWin_AttachFile", "OpenWin_AttachFile", "Attachment" };

    // START 2016/05/23 T.Tsuchida [QC#4492,MOD]
//    /**
//     * PARAMS_Upload_Only(A screen display mode)
//     */
//    public static final String PARAMS_MODE = "Upload-Only";
//
//    /**
//     * BUSINESS_APP_ID_NFCL3030(Duties ID)
//     */
//    public static final String PARAMS_DUTIES = "NFCL3030";
//
//    /**
//     * The number of the attached file upper limits
//     */
//    public static final String PARAMS_UPPER_KEY = "NFCL3030_ATT_LIMIT";
//
//    /**
//     * Attached file extension
//     */
//    public static final String PARAMS_EXTENSION_KEY = "NFCL3030_AUTHORIZE_FILE_EXT";
//
//    /**
//     * The attached file size upper limit
//     */
//    public static final String PARAMS_SIZE_KEY = "NFCL3030_AUTHORIZE_FILE_VOL";
    // END 2016/05/12 K.Kojima [QC#4492,ADD]
    // START 2024/02/23 S.Ikariya [QC#63452, ADD]
    /**
     * Select Account Event
     */
    public static final String SELECT_ACCT_EVENT = "SelectAcct";

    // END 2024/02/23 S.Ikariya [QC#63452, ADD]
    /**
     * parameter index : 9
     */
    public static final int PARAM_INDEX_9 = 9;

     /**
     * Attachments Screen Display Mode : Read-Only
     */
    public static final String PARAMS_DISPLAY_MODE_READ_ONLY = "Read-Only";

    // START 2016/07/07 K.Kojima [QC#10995,ADD]
    /**
     * Attachments Screen Display Mode : Modification
     */
    public static final String DISPLAY_MODE_MODIFICATION = "Modification";

    // END 2016/07/07 K.Kojima [QC#10995,ADD]

    // START 2016/07/07 K.Kojima [QC#10995,DEL]
    // /**
    // * Attachments Screen Display Mode : Upload-Only
    // */
    // public static final String PARAMS_DISPLAY_MODE_UPLOAD_ONLY =
    // "Upload-Only";
    // END 2016/07/07 K.Kojima [QC#10995,DEL]

    /**
     * Attachments Screen Function Name
     */
    public static final String PARAMS_FUNCTION_NAME = "Receipt Entry/Serach Cash Application Attachments";

    /**
     * Attachments Screen Primary Key
     */
    public static final String PARAMS_PRIMARY_KEY = "Receipt Number";

    /**
     * Attachments Screen Attachments Limit
     */
    public static final String PARAMS_UPPER_KEY = "NFCL2610_ATT_LIMIT";

    /**
     * Attachments Screen Authorize File Extensions
     */
    public static final String PARAMS_EXTENSION_KEY = "NFCL2610_AUTHORIZE_FILE_EXT";

    /**
     * Attachments Screen Authorize File Volume
     */
    public static final String PARAMS_SIZE_KEY = "NFCL2610_AUTHORIZE_FILE_VOL";
    // END 2016/05/23 T.Tsuchida [QC#4492,MOD]

    // START 2016/09/02 T.Tsuchida [QC#14090,ADD]
    /**
     * Display Hierarchy Accounts Code: 02
     */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";
    // END 2016/09/02 T.Tsuchida [QC#14090,ADD]

    // START 2016/11/11 J.Kim [QC#15756,ADD]
    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";
    // END 2016/11/11 J.Kim [QC#15756,ADD]
}
