package business.blap.NFDL0010.constant;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/07/08   Hitachi         K.Kojima        Update          QC#11417
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2018/05/16   CITS            S.Katsuma       Update          QC#24793
 * 2018/07/11   Hitachi         Y.Takeno        Update          QC#26679
 * 2018/07/30   Fujitsu         Y.Matsui        Update          QC#27081
 * 2019/10/23   Fujitsu         S.Takami        Update          QC#53536
 *</pre>
 */
public interface NFDL0010Constant {

    // START 2016/07/08 K.Kojima [QC#11417,ADD]
    /** Message ID : NFCM0850E */
    public static final String NFCM0850E = "NFCM0850E";
    // END 2016/07/08 K.Kojima [QC#11417,ADD]

    // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
    /** */
    public static final String MAX_DT = "99991231"; // 2019/10/23 S.Takami [QC#53536,MOD, without any comments]
    // END 2017/08/07 T.Tsuchida [QC#19576,ADD]

    // START 2018/05/16 S.Katsuma [QC#24793,ADD]
    /** CSV Header */
    public static final String[] CSV_HEADER = {
        "Account#",
        "Account Name",
        "Payment Terms",
        "DSO",
        "Collector Name",
        "Portfolio Name",
        "Collector DSO",
        "Over Due Amt",
        "Net Amt",
        "Disputes Amt",
        "Promise Amt",
        "Promise Date",
        "Promise Due Date",
        "Broken Promise",
        "Contact Name",
        "Contact Number",
        "Email Address",
        // START 2018/07/30 Y.Matsui [QC#27081,ADD]
        "Last Contact Date",
        // END   2018/07/30 Y.Matsui [QC#27081,ADD]
        "Notes",
        "Status",
        "Strategy Name",
        "Current Work Item Name",
        "Last Payment Date",
        "Last Payment Amt"
    };

    /** CSV file name */
    public static final String CSV_NAME = "Collection Summary";

    /** CSV extension */
    public static final String CSV = ".csv";

    /** Download size */
    public static final int CSV_LIMIT_COUNT = 1000;
    // END 2018/05/16 S.Katsuma [QC#24793,ADD]

    // START 2018/07/11 [QC#26679, ADD]
    /** Search Mode Code : Customer Lookup */
    public static final String SEARCH_MODE_CD_LOOKUP  = "01";

    /** Search Mode Code : Customer View Summary */
    public static final String SEARCH_MODE_CD_SUMMARY = "02";

    /** Search Mode Name : Customer Lookup */
    public static final String SEARCH_MODE_NM_LOOKUP  = "Customer Lookup";

    /** Search Mode Name : Customer View Summary */
    public static final String SEARCH_MODE_NM_SUMMARY = "View Summary";
    // END   2018/07/11 [QC#26679, ADD]
    // START 2018/10/30 J.Kim [QC#28937,MOD]
    /** Fetch size */
    public static final int FETCH_SIZE = 1000;
    // END 2018/10/30 J.Kim [QC#28937,MOD]
}
