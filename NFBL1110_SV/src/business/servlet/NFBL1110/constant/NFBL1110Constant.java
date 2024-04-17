/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFBL1110.constant;

/**
 * <pre>
 * AP Invoice Maintenance Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   CUSA            Y.Aikawa        Create          N/A
 * 2016/08/05   Hitachi         K.Kojima        Update          QC#12690
 * 2016/08/23   Fujitsu         T.Murai         Update          QC#12830
 * </pre>
 */
public interface NFBL1110Constant {

    /** Empty Value */
    static final String EMPTY_STRING = "";
    /** Business ID */
    static final String BIZ_ID = "NFBL1110";

    // Del 2016/08/23 QC#12830
    // /** Record Count for one serial number */
    // static final int INT_6 = 6;
    /** yyyyMMdd */
    static final String YYYYMMDD = "yyyyMMdd";

    // Function Code
    /** Fuction Code 20 (Search) */
    static final String FUNC_CD_20 = "20";
    /** Fuction Code 40 (Update) */
    static final String FUNC_CD_40 = "40";

    /** Screen Name */
    static final String SCRN_NM_00 = "NFBL1110Scrn00";

    /** Message Kind */
    static final String MESSAGE_KIND_E = "E";

    // Tab
    /** Tab anchor Header */
    public static final String TAB_HEADER = "Header";
    /** Tab anchor Holds */
    public static final String TAB_HOLDS = "Holds";
    /** Tab anchor Lines */
    public static final String TAB_LINES = "Lines";
    /** Tab anchor Distributions */
    public static final String TAB_DISTRIBUTIONS = "Distributions";
    
    // Table
    /** Table : A */
    public static final String TABLE_A = "A";

    /** Index Number */
    /** Index Number 0 */
    public static final int IDX_0 = 0;
    /** Index Number 1 */
    public static final int IDX_1 = 1;
    /** Index Number 2 */
    public static final int IDX_2 = 2;
    /** Index Number 3 */
    public static final int IDX_3 = 3;
    /** Index Number 4 */
    public static final int IDX_4 = 4;
    /** Index Number 5 */
    public static final int IDX_5 = 5;
    /** Index Number 6 */
    public static final int IDX_6 = 6;
    /** Index Number 7 */
    public static final int IDX_7 = 7;
    /** Index Number 8 */
    public static final int IDX_8 = 8;
    /** Index Number 9 */
    public static final int IDX_9 = 9;
    /** Index Number 10 */
    public static final int IDX_10 = 10;
    /** Index Number 13 */
    public static final int IDX_13 = 13;
    /** Index Number 16 */
    public static final int IDX_16 = 16;
    /** Index Number 20 */
    public static final int IDX_20 = 20;
    /** Index Number 30 */
    public static final int IDX_30 = 30;
    /** Index Number 50 */
    public static final int IDX_50 = 50;
    /** Index Number 60 */
    public static final int IDX_60 = 60;
    /** Index Number 100 */
    public static final int IDX_100 = 100;

    // AP_MAINT_INV_STS_CD
    /** AP_MAINT_INV_STS_CD 00 (Invoice Entered) */
    static final String AP_MAINT_INV_STS_CD_00 = "00";
    /** AP_MAINT_INV_STS_CD 10 (Invoice Batch Entry Completed) */
    static final String AP_MAINT_INV_STS_CD_10 = "10";
    /** AP_MAINT_INV_STS_CD 11 (Pending Contract Admin Approval) */
    static final String AP_MAINT_INV_STS_CD_11 = "11";
    /** AP_MAINT_INV_STS_CD 12 (Pending Workflow Approval) */
    static final String AP_MAINT_INV_STS_CD_12 = "12";
    /** AP_MAINT_INV_STS_CD 20 (Approved) */
    static final String AP_MAINT_INV_STS_CD_20 = "20";
    /** AP_MAINT_INV_STS_CD 30 (ARCS AP Invoice Created) */
    static final String AP_MAINT_INV_STS_CD_30 = "30";
    /** AP_MAINT_INV_STS_CD 50 (Paid) */
    static final String AP_MAINT_INV_STS_CD_50 = "50";
    /** AP_MAINT_INV_STS_CD 90 (Cancelled) */
    static final String AP_MAINT_INV_STS_CD_90 = "90";
    /** AP_MAINT_INV_STS_CD 95 (Voided) */
    static final String AP_MAINT_INV_STS_CD_95 = "95";
    

    // Screen ID
    /** Screen ID NFBL1110Scrn00 */
    static final String SCRN_ID_00 = "NFBL1110Scrn00";

    // Function ID
    /** Function ID NFBL1110T010 (Reference) */
    static final String FUNC_ID_NFBL1110T010 = "NFBL1110T010";
    /** Function ID NFBL1110T020 (Registration/Update without Approve/Reject) */
    static final String FUNC_ID_NFBL1110T020 = "NFBL1110T020";
    /** Function ID NFBL1110T030 (Registration/Update with Approve/Reject) */
    static final String FUNC_ID_NFBL1110T030 = "NFBL1110T030";

    // Normal Button
    /** >>(Supplier) Button */
    static final String BTN_NORMAL_SEARCH_SUPPLIER_NAME = "SearchSupplierName";
    /** Add Serial Button */
    static final String BTN_NORMAL_ADD_SERIAL = "AddSerial";
    /** Select All Button */
    static final String BTN_NORMAL_SELECT_ALL = "SelectAll";
    /** Unselect All Button */
    static final String BTN_NORMAL_UNSELECT_ALL = "UnselectAll";
    /** Delete Serial Button */
    static final String BTN_NORMAL_DELETE_SERIAL = "DeleteSerial";
    // Add Start 2016/08/22 QC#12830
    /** Add Counter Line Button */
    static final String BTN_NORMAL_ADD_COUNTER = "AddCounterTp";
    /** Delete Counter Line Button */
    static final String BTN_NORMAL_DELETE_COUNTER = "DeleteCounterTp";
    // Add Start 2016/08/22 QC#12830
    /** Prev Invoice Button */
    static final String BTN_NORMAL_PREV_INVOICE = "PrevInvoice";
    /** Next Invoice Button */
    static final String BTN_NORMAL_NEXT_INVOICE = "NextInvoice";
    /** Delete Invoice Button */
    static final String BTN_NORMAL_DELETE_INVOICE = "DeleteInvoice";
    /** Summary Button */
    static final String BTN_NORMAL_SUMMARY = "Summary";

    // Common Button
    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    // Scrn00
    /** Function Button 1 */
    String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };
    /** Function Button 2 */
    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };
    /** Function Button 3 */
    // START 2016/08/05 K.Kojima [QC#12690,MOD]
    // String[] BTN_CMN_BLANK3 = {"btn3", EMPTY_STRING, EMPTY_STRING };
    String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };
    // END 2016/08/05 K.Kojima [QC#12690,MOD]
    /** Function Button 4 */
    String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };
    /** Function Button 5 */
    String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };
    /** Function Button 6 */
    // START 2016/08/05 K.Kojima [QC#12690,MOD]
    // String[] BTN_CMN_BLANK6 = {"btn6", EMPTY_STRING, EMPTY_STRING };
    String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };
    // END 2016/08/05 K.Kojima [QC#12690,MOD]
    /** Function Button 7 */
    // START 2016/08/05 K.Kojima [QC#12690,MOD]
    // String[] BTN_CMN_BLANK7 = {"btn7", EMPTY_STRING, EMPTY_STRING };
    String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };
    // END 2016/08/05 K.Kojima [QC#12690,MOD]
    /** Function Button 8 */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };
    /** Function Button 9 */
    // START 2016/08/05 K.Kojima [QC#12690,MOD]
    // String[] BTN_CMN_BLANK9 = {"btn9", EMPTY_STRING, EMPTY_STRING };
    String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };
    // END 2016/08/05 K.Kojima [QC#12690,MOD]
    /** Function Button 10 */
    String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    // Message ID
    /** At least one @ is needed */
    static final String NAAM0041E = "NAAM0041E";
    /** At least one @ is needed */
    static final String NFAM0033E = "NFAM0033E";
    /** @ already exists in @ */
    static final String NFAM0070E = "NFAM0070E";
    /** @ cannot be added because it is exceeding the maximum number of row [@] */
    static final String NFAM0072E = "NFAM0072E";
    /** Please check at least 1 @ checkbox. */
    static final String NFBM0017E = "NFBM0017E";
    /** @ is invalid. */
    static final String NFBM0041E = "NFBM0041E";
    /** You cannot change @ before submit. */
    static final String NFBM0052E = "NFBM0052E";
    /** Please check only 1 checkbox. */
    static final String NFBM0064E = "NFBM0064E";
    /** @ of @ is different from that of @. */
    static final String NFBM0134E = "NFBM0134E";
    /** @ must be before @ */
    static final String NFBM0141E = "NFBM0141E";
    /** You can not @ the selected invoice. */
    static final String NFBM0192E = "NFBM0192E";
    /**
     * Showing only first @ of total @ records. Please review your
     * search criteria.
     */
    static final String NFBM0001W = "NFBM0001W";
    /** The value for [@] must be equal to or later  than [@] */
    static final String NMAM8061E = "NMAM8061E";
    /** Multiple @ exist in @. */
    static final String NLBM1004E = "NLBM1004E";
    /** For [@], enter a value larger than [@]. */
    static final String NTAM0128E = "NTAM0128E";
    /** Please check at least 1 checkbox. */
    static final String NZZM0011E = "NZZM0011E";
    /** The search ended normally. */
    static final String ZZM8002I = "ZZM8002I";
    /** Do you want to execute [@]? Please respond to message box. */
    static final String ZZM8101I = "ZZM8101I";
    /** [@] field is mandatory. */
    static final String ZZM9000E = "ZZM9000E";
    static final String ZZM9010E = "ZZM9010E";
    /** Invalid parameters found. */
    static final String ZZSM2014E = "ZZSM2014E";
    /** 'please check at least 1 checkbox from [@]. */
    static final String NFBM0236E = "NFBM0236E";

    /** Error sign for String */
    static final String ERROR_STR = "E";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String SER_NUM = "SER_NUM";
    /** DB Item Column Name */
    static final String CNT_TP_CD = "CNT_TP_CD";

    // Frac Digit
    /** Frac Digit 2 */
    static final int FRAC_DIGIT_2 = 2;

    // START 2016/09/21 W.Honda [Unit Test#201,ADD]
    // VAR_CHAR_CONST_NM
    /** VAR_CHAR_CONST_NM :  */
    static final String CONST_NM_NFBL1110_THEREFORE_CATG_LIST = "NFBL1110_THEREFORE_CATG_LIST";
    // END 2016/09/21 W.Honda [Unit Test#201,ADD]
}
