/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFCL2610.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 * 2016/05/23   Hitachi         T.Tsuchida      Update          QC#4492
 * 2016/07/07   Hitachi         K.Kojima        Update          QC#10995
 * 2016/10/20   Hitachi         K.Kojima        Update          QC#13191
 * 2020/10/28   CITS            R.Kurahashi     Update          QC#57732
 * 2022/02/02   Hitachi         A.Kohinata      Update          QC#59612
 * 2023/06/20   Hitachi         S.Fujita        Update          QC#61486
 *</pre>
 */
public class NFCL2610Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NFCL2610";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = "NFCL2610Scrn00";

    /**
     * Common button 1
     */
    public static final String[] SAVE = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    public static final String[] SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] APPLY = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] REJECT = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] DELETE = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * Search button
     */
    public static final String[] SEARCH = {"Search", "Search", "Search" };

    /**
     * ">>" button [Bill to Customer Account Name]
     */
    public static final String[] SEARCH_BILL = {"GetCustomerNm", "GetCustomerNm", ">>" };

    /**
     * ">>" button [Bill to Customer Name]
     */
    public static final String[] SEARCH_BILL_LOC = {"GetBillToLoc", "GetBillToLoc", ">>" };

    /**
     * Attachment button
     */
    public static final String[] ATTACHMENT = {"OpenWin_AttachFile", "OpenWin_AttachFile", "Attachment" };

    /**
     * CheckAll button Check
     */
    public static final String[] CHECKALL = {"CheckAll", "CheckAll", "CheckAll" };

    /**
     * UncheckAll button
     */
    public static final String[] UNCHECKALL = {"UncheckAll", "UncheckAll", "UncheckAll" };

    /**
     * Entry Supplier button
     */
    // START 2020/10/28 R.Kurahashi [QC#57732,MOD]
    // START 2016/10/20 K.Kojima [QC#13191,MOD]
    // public static final String[] ENTRY_SUPPLIER = {"SupplierEntry",
    // "OpenWin_SupplierEntry", "Entry Supplier" };
    //public static final String[] ENTRY_SUPPLIER = {"SupplierEntry", "OpenWin_SupplierEntry", "Supplier Entry" };
    public static final String[] ENTRY_SUPPLIER = {"SupplierEntry", "OpenWin_SupplierEntry", "Site Entry" };
    // END 2016/10/20 K.Kojima [QC#13191,MOD]
    // END 2020/10/28 R.Kurahashi [QC#57732,MOD]
    
    // START 2020/10/28 R.Kurahashi [QC#57732,ADD]
    /**
     * New Supplier button
     */
    public static final String[] NEW_SUPPLIER = {"NewSupplier", "OpenWin_NewSupplier", "New Supplier" };
    // END 2020/10/28 R.Kurahashi [QC#57732,ADD]

    // add start 2022/02/02 QC#59612
    /**
     * Re-Entry button
     */
    public static final String[] RE_ENTRY = {"ReEntry", "ReEntry", "Re-Entry" };
    // add end 2022/02/02 QC#59612

    /**
     * Button is active
     */
    public static final int BTN_ACTIVE = 1;

    /**
     * Button is inacitve
     */
    public static final int BTN_INACTIVE = 0;

    /**
     * FUNC_ID_T010
     */
    public static final String FUNC_ID_T020 = "NFCL2610T020";

    // START 2023/06/20 S.Fujita [QC#61486,ADD]
    /**
     * FUNC_ID_T030
     */
    public static final String FUNC_ID_T030 = "NFCL2610T030";
    // END 2023/06/20 S.Fujita [QC#61486,ADD]

    /**
     * TABLE_A
     */
    public static final String TABLE_A = "A";

    /**
     * TABLE_B
     */
    public static final String TABLE_B = "B";

    /** Parameter Account Search: 24 */
    public static final int PRM_NMAL6760 = 24;

    /**
     * The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

    // START 2016/05/23 T.Tsuchida [QC#4492,MOD]
//    /**
//     * PARAMS_Upload_Only(A screen display mode)
//     */
//    public static final String PARAMS_MODE = "Upload-Only";
//
//    /**
//     * BUSINESS_APP_ID_NFCL2610(Duties ID)
//     */
//    public static final String PARAMS_DUTIES = "NFCL2610";
//
//    /**
//     * The number of the attached file upper limits
//     */
//    public static final String PARAMS_UPPER_KEY = "NFCL2610_ATT_FILE_LIMITS";
//
//    /**
//     * Attached file extension
//     */
//    public static final String PARAMS_EXTENSION_KEY = "NFCL2610_ATT_FILE_EXTENSION";
//
//    /**
//     * The attached file size upper limit
//     */
//    public static final String PARAMS_SIZE_KEY = "NFCL2610_ATT_FILE_SIZE";

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
    public static final String PARAMS_FUNCTION_NAME = "AR Refund By Check Entry Attachments";

    /**
     * Attachments Screen Primary Key
     */
    public static final String PARAMS_PRIMARY_KEY = "Refund Number";

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

    /**
     * Display Hirarchey Account
     */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";
}
