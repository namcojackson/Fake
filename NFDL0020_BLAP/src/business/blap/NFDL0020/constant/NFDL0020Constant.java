package business.blap.NFDL0020.constant;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/06/22   Hitachi         K.Kojima        Update          QC#10529
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13004
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13907
 * 2017/07/04   Hitachi         E.Kameishi      Update          QC#18754
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2018/03/15   Hitachi         J.Kim           Update          QC#20945
 * 2018/05/16   Fujitsu         Y.Matsui        Update          QC#24329
 * 2018/06/21   Hitachi         Y.Takeno        Update          QC#25080
 * 2018/07/30   Fujitsu         Y.Matsui        Update          QC#27081
 * 2018/08/30   Hitachi         Y.Takeno        Update          QC#27603
 * 2020/01/28   CITS            M.Furugoori     Update          QC#55017
 * 2021/05/25   CITS            G.Delgado       Update          QC#58704
 * 2022/11/01   Hitachi         T.Doi           Update          QC#60415
 * 2022/11/01   Hitachi         T.Doi           Update          QC#57088
 *</pre>
 */
public interface NFDL0020Constant {

    // START 2016/06/22 K.Kojima [QC#10529,ADD]
    /** Message ID : ZZZM9006E */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Message ID : ZZZM9006E */
    public static final String ZZZM9025E = "ZZZM9025E";

    // END 2016/06/22 K.Kojima [QC#10529,ADD]

    // START 2016/09/26 K.Kojima [QC#13907,ADD]
    /** Message ID : NFDM0039E */
    public static final String NFDM0039E = "NFDM0039E";

    // END 2016/09/26 K.Kojima [QC#13907,ADD]

    // START 2016/09/26 K.Kojima [QC#13004,ADD]
    /** Message ID : NFDM0040E */
    public static final String NFDM0040E = "NFDM0040E";

    // END 2016/09/26 K.Kojima [QC#13004,ADD]

    // START 2017/07/04 E.Kameishi [QC#18754,ADD]
    /** Message ID : NFDM0046E */
    public static final String NFDM0046E = "NFDM0046E";
    // END 2017/07/04 E.Kameishi [QC#18754,ADD]

    // START 2016/07/28 K.Kojima [QC#12096,ADD]
    /** Var Char Const : NFDL0020_STS_MEMO_RSN_CD */
    public static final String NFDL0020_STS_MEMO_RSN_CD = "NFDL0020_STS_MEMO_RSN_CD";
    // END 2016/07/28 K.Kojima [QC#12096,ADD]

    // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
    /** */
    public static final String INIT_INV_BOL_LINE_NUM = "001";

    /** */
    public static final String MAX_DT = "29991231";
    // END 2017/08/07 T.Tsuchida [QC#19576,ADD]

    // START 2018/03/15 J.Kim [QC#20945,ADD]
    /** CSV file name */
    public static final String CSV_NAME = "Adjustment History";

    /** CSV extension */
    public static final String CSV = ".csv";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** Download size */
    public static final int CSV_LIMIT_COUNT = 1000;
    // END 2018/03/15 J.Kim [QC#20945,ADD]

    // START 2018/05/11 J.Kim [QC#21720,ADD]
    /**
     * CLT_STRGY_WRK_ITEM_TRX_SQ
     */
    static final String CLT_STRGY_WRK_ITEM_TRX_SQ = "CLT_STRGY_WRK_ITEM_TRX_SQ";
    // END 2018/05/11 J.Kim [QC#21720,ADD]

    // START 2018/05/16 [QC#24329,ADD]
    /** Statement CSV file name */
    public static final String STMT_CSV_NAME = "Statement";

    /** Statement CSV file name */
    public static final String STMT_BAT_ID = "NFCB057001";
    // END 2018/05/16 [QC#24329,ADD]

    // START 2018/05/21 S.Katsuma [QC#24793,ADD]
    /** Transaction CSV file name */
    public static final String TRAN_CSV_NAME = "Transaction";

    /** Transaction CSV Header */
    public static final String[] TRAN_CSV_HEADER = {
        "",
        "Trxaction#",
        "Trx Type",
        "ConBill#",
        "Trx/Bill Date",
        "Account#",
        "Bill To",
        // START 2018/07/30 Y.Matsui [QC#27081,ADD]
        "Contact Name",
        "Contact Number",
        "Email Address",
        // END   2018/07/30 Y.Matsui [QC#27081,ADD]
        "Ship To",
        "Ship Name",
        "Line Amt",
        "Freight Amt",
        "Tax Amt",
         // START 2022/11/01 T.Doi [QC#60415, ADD]
        "Additional Amt",
        // END 2022/11/01 T.Doi [QC#60415, ADD]
        "Inv Amt",
        "Bal Amt",
        "Due Date",
        "Days Past Due",
        "CCY",
        "CI Number",
        "CI Status",
        "Dispute Date",
        "Dispute Amt",
        "Promise Amt",
        "Broken Promise Amt",
        "Contract Number",
        "Late Fees",
        "Bill Period From",
        "Bill Period To",
        "PO Number",
        "Lease PO Number",
        "Comments",
        "Installment",
        "Class",
        "Type",
         // START 2022/11/01 T.Doi [QC#57088, ADD]
        "Line Type",
         // END 2022/11/01 T.Doi [QC#57088, ADD]
        "Order#",
        "GL Date",
        "Payment Term",
        "Salesrep",
        "Branch",
        "Original Invoice",
        "Reference#"
    };

    /** Note CSV file name */
    public static final String NOTE_CSV_NAME = "Note";

    /** Note CSV Header */
    public static final String[] NOTE_CSV_HEADER = {
        "#",
        "Date",
// START 2018/06/21 [QC#25080, MOD]
//        "Name",
// END   2018/06/21 [QC#25080, MOD]
        "Note Type",
        "Note",
        "Created By"
    };

    /** Task CSV file name */
    public static final String TASK_CSV_NAME = "Task";

    /** Task CSV Header */
    public static final String[] TASK_CSV_HEADER = {
        "Task#",
        "Date",
        "Name",
        "Type",
        "Status",
        "Priority",
        "Owned By"
    };

    /** Contract CSV file name */
    public static final String CONTR_CSV_NAME = "Contract";

    /** Contract CSV Header */
    public static final String[] CONTR_CSV_HEADER = {
        "Contract Number",
        "Modifier",
        "Description",
        "CCY",
        "Start Date",
        "End Date",
        "Terminated",
        "Renewed",
        "Cancelled",
        "Hold Creation Date",
        "Hold Reason",
        "Hold Flag",
        "Hold Release Date",
        "Release By",
        "Hold Release"
    };
    // END 2018/05/21 S.Katsuma [QC#24793,ADD]

    // START 2018/07/09 J.Kim [QC#26801, ADD]
    /** Business ID */
    public static final String BIZ_ID = "NFDL0020";
    // END 2018/07/09 J.Kim [QC#26801, ADD]

    // START 2018/08/30 [QC#27603, ADD]
    /** Fetch size */
    public static final int FETCH_SIZE = 1000;
    // END   2018/08/30 [QC#27603, ADD]

    // START 2020/01/28 [QC#55017, ADD]
    /** Payment size */
    public static final int INV_LIMIT_COUNT = 99;
    // END   2020/01/28 [QC#55017, ADD]

    // START 2021/05/25 G.Delgado [QC#58704,ADD]
    /** You cannot enter a date older than Actual Comp Date which already has a status of completed. */
    public static final String NFDM0054E = "NFDM0054E";

    /** You cannot change to a status other than Skip. */
    public static final String NFDM0055E = "NFDM0055E";

    /** You cannot enter blank at @. */
    public static final String NFDM0056E = "NFDM0056E";

    /** Request Start Date */
    public static final String CLT_WRK_ITEM_RWSD_DT_NAME = "Request Start Date";

    /** Var Char Const : NFDL0020_ASSIGNED_TO_BATCH */
    public static final String NFDL0020_ASSIGNED_TO_BATCH = "NFDL0020_ASSIGNED_TO_BATCH";
    // END 2021/05/25 G.Delgado [QC#58704,ADD]

    // START 2022/11/01 T.Doi [QC#57088, ADD]
    /** Var Char Const : RPT_COND_CONST_GRP_ID */
    public static final String NFC004_PRIM_SVC_CHRG = "NFC004_PRIM_SVC_CHRG";
    // END 2022/11/01 T.Doi [QC#57088, ADD]
}
