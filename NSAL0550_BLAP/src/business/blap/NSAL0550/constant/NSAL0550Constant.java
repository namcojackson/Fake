/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0550.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Takeno        Create          N/A
 * 2017/01/04   Hitachi         A.Kohinata      Update          CSA QC#12497
 * 2017/09/21   CITS            M.Naito         Update          CSA QC#18758
 * 2017/10/12   CITS            M.Naito         Update          CSA QC#21762
 * 2018/03/05   CITS            M.Naito         Update          CSA QC#22243
 * 2019/06/12   Hitachi         K.Kim           Update          CSA QC#50525
 * 2019/12/06   Hitachi         E.Kameishi      Update          CSA QC#54749
 *</pre>
 */
public class NSAL0550Constant {

    /** Business ID. */
    public static final String BUSINESS_ID = "NSAL0550";

    /** Page Size. */
    public static final int PAGE_SIZE = 50;

    /** message id : NZZM0000E */
    public static final String NZZM0000E = "NZZM0000E";

    /** message id : NZZM0001W */
    public static final String NZZM0001W = "NZZM0001W";

    /** Please select item(s). */
    public static final String NSAM0034E = "NSAM0034E";
    
    /** Failed to create ticket for the following reason : [@] */
    public static final String NSAM0603E = "NSAM0603E";
    
    /** Exception occurred while creating customer care ticket. */
    public static final String NSAM0604E = "NSAM0604E";
    
    /** Ticket#: @  successfully created for invoice#: @. */
    public static final String NSAM0605I = "NSAM0605I";

    /** code table name : INV_TP */
    public static final String DELIMITER_CHRG_TYPE = "/";

    /** code table name : INV_TP */
    public static final String TABLE_NAME_INV_TP = "INV_TP";

    /** csv file name. */
    public static final String CSV_FILE_NAME = "ContractInvoiceSearch";

    /** csv file extension. */
    public static final String CSV_FILE_EXT = ".csv";

    /** csv file header columns. */
    public static final String[] CSV_HEADER_COLUMNS = new String[] {"Contract#", "Invoice#", "Invoice Date", "Invoice Charge Type", "Invoice Type", "Invoice Source Type", "Invoice Amount", "Tax Amount", "Total Amount", "Open Amount",
            "Date Billed From", "Date Billed To", "Invoice Due Date", "CI Number", "Rebill Invoice#", "Credit Memo Invoice#", "Original Invoice#" };

    /**  CSV exclusion items. */
    public static final String[] CSV_EXCLUSION_ITEMS = new String[] {
        "invPrintRqstNum_A1"
    };

    /** customer care calling statement */
    public final static String CALL_PRC_CANON_E193_CREAT_BILL_TICKET = "{call CANON_E193_CS_EVOLUTION_PKG.CREATE_BILL_TICKET(?,?,?,?,?,?,?,?,?)}";

    // mod start 2018/03/05 CSA Defect#22243
    /** customer care parameter:CANON_E193_HDR_ORIG_TYPE */
    public final static String CANON_E193_HDR_ORIG_TYPE = "customer";
    // mod end 2018/03/05 CSA Defect#22243

    /** customer care parameter:CANON_E193_HDR_SOURCE */
    public final static String CANON_E193_HDR_SOURCE = "Contract Invoice Search";

    // mod start 2017/09/21 CSA Defect#18758
    /** customer care parameter:CANON_E193_HDR_CAT_DESC */
    public final static String CANON_E193_HDR_CAT_DESC = "CANON_E193_HDR_CAT_DESC";

    /** customer care parameter:CANON_E193_LINE_REASON_CODE */
    public final static String CANON_E193_LINE_REASON_CODE = "CANON_E193_LINE_REASON_CODE";

    /** customer care parameter:CANON_E193_LINE_REASON */
    public final static String CANON_E193_LINE_REASON = "CANON_E193_LINE_REASON";

    /** customer care parameter:CANON_E193_LINE_CAT_DESC */
    public final static String CANON_E193_LINE_CAT_DESC = "CANON_E193_LINE_CAT_DESC";
    // mod start 2017/09/21 CSA Defect#18758
    
    public final static String NO_PHONE = "No Phone";
    public final static String NO_EMAIL = "No E-mail";
    
    public final static String CANON_E193_NOTES_DETAIL = "Customer Incident created from Contract Invoice Search";
    
    public final static String ORACLE_STRUCT_CANON_E193_S21_HDR = "CANON_E193_S21_HDR_REC";
    public final static String ORACLE_STRUCT_CANON_E193_S21_LINE = "CANON_E193_S21_LINE_REC";
    public final static String ORACLE_STRUCT_CANON_E193_S21_SUBLINE = "CANON_E193_S21_SUBLINE_REC";
    
    public final static String ORACLE_ARRAY_CANON_E193_S21_HDR = "CANON_E193_S21_HDR_TBL";
    public final static String ORACLE_ARRAY_CANON_E193_S21_LINE = "CANON_E193_S21_LINE_TBL";
    public final static String ORACLE_ARRAY_CANON_E193_S21_SUBLINE = "CANON_E193_S21_SUBLINE_TBL";

    // START 2019/06/12 [QC#50525,MOD]
    // // add start 2017/09/21 CSA Defect#18758
    // /** VarCharConst : Customer Care Ticket History URL */
    // public final static String CUST_CARE_TKT_HIST_URL = "CUST_CARE_TKT_HIST_URL";
    // // add end 2017/09/21 CSA Defect#18758
    // END 2019/06/12 [QC#50525,MOD]

    // add start 2017/10/12 CSA Defect#21762
    /** The selected invoice cannot be created ticket. Because the invoice has already been created ticket or the Invoice Type is "Credit Memo". */
    public static final String NSAM0708E = "NSAM0708E";
    // add end 2017/10/12 CSA Defect#21762
    // START 2019/12/06 E.Kameishi [QC#54749, ADD]
    public final static String CONTRACT = "Contract";
    
    /** This Invoice cannot be executed "Credit And Rebill" in this screen. Please execute in AR Credit And Rebill screen. */
    public static final String NSAM0756E = "NSAM0756E";

    // END 2019/12/06 E.Kameishi [QC#54749, ADD]
}
