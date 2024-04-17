/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7150.constant;

/**
 *<pre>
 * CSMP Contract Synchronization  Errors Correction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   fujitsu         M.Ohno          Create          N/A
 * 2016/06/14   Fujitsu         W.Honda         Update          QC#9943
 * 2018/06/04   Fujitsu         M.Ishii         Update          QC#18256
 * 2018/06/08   Fujitsu         M.Ishii         Update          QC#18256
 *</pre>
 */
public class NMAL7150Constant {
    /** Business ID */
    public static final String BIZ_ID = "NMAL7150";

    /** No search result were found. */
    public static final String NMAM0007I = "NMAM0007I";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /**
     * Too many search results. Please narrow your search criteria and retry.
     */
    public static final String NMAM8518I = "NMAM8518I";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /** Check Box : xxChkBox_A */
    public static final String CHK_BOX_A = "xxChkBox_A";

    /** SEARCH_ROW_NUM */
    public static final int SEARCH_ROW_NUM = 100001;

    /** Supply Agreement Plan */
    public static final String CSMP_CONTR_INTF_TABLE_NAME = "CSMP Cntract Interface";

    /** Warning Kind */
    public static final String MESSAGE_KIND_WARN = "W";

    /** Error Kind */
    public static final String MESSAGE_KIND_ERR = "E";

    /** CSV_EXTENSION */
    public static final String CSV_EXTENSION = ".csv";

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NMAL7150 CSMP Contract Synchronization  Errors Correction";

    /** CSV_HDR_SCHD */
    public static final String[] CSV_HDR = new String[] {//
            "Created Date" //
            , "Process Flag" //
            , "Error Message" //
            // QC#18256 2018/06/04 Mod start
//            , "Dealer Ref Num" //
            , "CSA Number" //
            // QC#18256 2018/06/04 Mod end
            // QC#18256 2018/06/08 Mod start
//            , "Transaction Flag" //
            , "Transaction Status" //
            // QC#18256 2018/06/08 Mod end
            // QC#18256 2018/06/04 Mod start
//            , "Dealer" //
            , "Origin Dealer Code" //
            // QC#18256 2018/06/04 Mod end
            // QC#18256 2018/06/04 Mod start
//            , "End User Name" //
            , "Account Name" //
            // QC#18256 2018/06/04 Mod end
            , "CSMP#" //
            // QC#18256 2018/06/08 Mod start
//            , "Start Date" //
            , "Effective Date" //
            // QC#18256 2018/06/08 Mod end
            // QC#18256 2018/06/08 Mod start
//            , "End Date" //
            , "Expiration Date" //
            // QC#18256 2018/06/08 Mod end
            , "Credit List" //
            // QC#18256 2018/06/08 Mod start
//            , "Gen" //
            , "Generation#" //
            // QC#18256 2018/06/08 Mod end
            // QC#9943 2016/06/14 Mod start
//            , "Contracct Status" //
            , "Contract Status" //
            // QC#9943 2016/06/14 Mod end
            , "Region" //
            // QC#18256 2018/06/04 Mod start
//            , "Category" //
            , "CSMP Level" //
            // QC#18256 2018/06/04 Mod end
            , "Valid From Date" //
            , "Prev Contract" //
            , "Prev End User" };

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Dt Length */
    public static final int YYYYMMDD_LENGTH = 8;
}
