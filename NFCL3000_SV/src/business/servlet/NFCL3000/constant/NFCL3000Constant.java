package business.servlet.NFCL3000.constant;

import java.math.BigDecimal;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/12/21   Fujitsu         T.Tanaka        Create          N/A
 * 2016/05/12   Hitachi         K.Kojima        Update          QC#4492
 * 2016/05/23   Hitachi         T.Tsuchida      Update          QC#4492
 * 2016/07/11   Fujitsu         S.Fujita        Update          QC#10995
 * 2018/10/24   Fujitsu         S.Takami        Update          QC#27069
 * 2019/05/29   Fujitsu         S.Takami        Update          QC#50542
 *</pre>
 */

public interface NFCL3000Constant {
    
    /**
     * BusinessID:"NFCL3000"
     */
    public static final String BIZ_ID = "NFCL3000";

    String TAB_Line = "TAB_Line";
    String TAB_SalesCredit = "TAB_SalesCredit";
    String TAB_Accounting = "TAB_Accounting";
    String TAB_BOL = "TAB_BOL";
    String TAB_MoreInfo = "TAB_MoreInfo";

    public static final String CMN_CLOSE = "CMN_Close";

    /** */
    public static final String DR_CR_TP_DEBIT = "D";

    /** */
    public static final String DR_CR_TP_CREDIT = "C";

    /** */
    public static final String NMAL1130_WAREHOUSE = "Warehouse";

    /** */
    public static final BigDecimal ACCT_DIST_SMRY = BigDecimal.ZERO;

    /** */
    public static final BigDecimal ACCT_DIST_EDIT = BigDecimal.ONE;

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
//     * BUSINESS_APP_ID_NFCL3000(Duties ID)
//     */
//    public static final String PARAMS_DUTIES = "NFCL3000";
//
//    /**
//     * The number of the attached file upper limits
//     */
//    public static final String PARAMS_UPPER_KEY = "NFCL3000_ATT_FILE_LIMITS";
//
//    /**
//     * Attached file extension
//     */
//    public static final String PARAMS_EXTENSION_KEY = "NFCL3000_ATT_FILE_EXTENSION";
//
//    /**
//     * The attached file size upper limit
//     */
//    public static final String PARAMS_SIZE_KEY = "NFCL3000_ATT_FILE_SIZE";
    // END 2016/05/12 K.Kojima [QC#4492,ADD]

    // START 2016/08/01 S.Fujita [QC#10148,ADD]
    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NFCL3000Scrn00";

    /** Function ID : T020 */
    public static final String FUNC_T020 = "NFCL3000T020";

    /** Function ID : T030 */
    public static final String FUNC_T030 = "NFCL3000T030";

    /** DisplayPattern : Invisibility Error */
    public static final String DIS_PAT_INVISIBILITY_ERROR = "InvisibilityError";

    /** DisplayPattern : Visibility Error */
    public static final String DIS_PAT_VISIBILITY_ERROR = "VisibilityError";
    // END   2016/08/01 S.Fujita [QC#10148,ADD]

    /**
     * parameter index : 9
     */
    public static final int PARAM_INDEX_9 = 9;

    /**
     * Attachments Screen Display Mode : Read-Only
     */
    public static final String PARAMS_DISPLAY_MODE_READ_ONLY = "Read-Only";

    // START 2016/07/11 S.Fujita [QC#10995,ADD]
    /**
     * Attachments Screen Display Mode : Modification
     */
    public static final String DISPLAY_MODE_MODIFICATION = "Modification";
    // END   2016/07/11 S.Fujita [QC#10995,ADD]
    // START 2016/07/11 S.Fujita [QC#10995,DEL]
//    /**
//     * Attachments Screen Display Mode : Upload-Only
//     */
//    public static final String PARAMS_DISPLAY_MODE_UPLOAD_ONLY = "Upload-Only";
    // END   2016/07/11 S.Fujita [QC#10995,DEL]
    /**
     * Attachments Screen Function Name
     */
    public static final String PARAMS_FUNCTION_NAME = "Manual Invoice Entry Attachments";

    /**
     * Attachments Screen Primary Key
     */
    public static final String PARAMS_PRIMARY_KEY = "Invoice Number";

    /**
     * Attachments Screen Attachments Limit
     */
    public static final String PARAMS_UPPER_KEY = "NFCL3000_ATT_LIMIT";

    /**
     * Attachments Screen Authorize File Extensions
     */
    public static final String PARAMS_EXTENSION_KEY = "NFCL3000_AUTHORIZE_FILE_EXT";

    /**
     * Attachments Screen Authorize File Volume
     */
    public static final String PARAMS_SIZE_KEY = "NFCL3000_AUTHORIZE_FILE_VOL";

    /** Percent */
    public static final String PERCENT = "%";
    // END 2016/05/23 T.Tsuchida [QC#4492,MOD]

    // START 2018/10/24 S.Takami [QC#27069, Add]
    /** Index Number 30 */
    public static final int IDX_31 = 31;
    // END   2018/10/24 S.Takami [QC#27069, Add]

    // START 2019/05/29 S.Takami [QC#50542,ADD]
    /** NFZC102001 Resource Object Name: Debit */
    public static final String RESRC_OBJ_NM_DEBIT = "NFCL3000D";

    /** NFZC102001 Resource Object Name: Credit */
    public static final String RESRC_OBJ_NM_CREDIT = "NFCL3000C";
    // END 2019/05/29 S.Takami [QC#50542,ADD]
}