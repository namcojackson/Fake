/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0040.constant;

/**
 * Credit Approval
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/01   SRA             Otsuji          Create          N/A
 * 2013/09/10   SRA             Yanada          Update          QC2131
 * 2014/01/17   FUJITSU         T.Tanaka        update          QC3336 set fetch size
 * 2015/12/14   Hitachi         K.Yamada        Update          CSA QC#895
 * 2018/06/25   CITS            M.Naito         Update          QC#26634
 *</pre>
 */
public interface NSBL0040Constant {

    /**
     * No search results found.
     */
    String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    String NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    String NZZM0002I = "NZZM0002I";

    /**
     * This data has been updated by another user.
     */
    String NZZM0003E = "NZZM0003E";

    /**
     * Too many search results. Please narrow your search criteria and
     * retry.
     */
    String NZZM0007E = "NZZM0007E";

    /**
     * Please select item(s) to approve.
     */
    String NSBM0015E = "NSBM0015E";

    /**
     * No search results found.
     */
    String ZZZM9005W = "ZZZM9005W";

    // START 2018/06/25 M.Naito [QC#26634, ADD]

    /**
     * Failed to approve the following claim(s). Process Definition
     * Name:@ Document Id:@
     */
    public static final String NSAM0436E = "NSAM0436E";

    /**
     * You do not have the required authorization to perform this
     * action.
     */
    public static final String NSAM0437E = "NSAM0437E";
    // END 2018/06/25 M.Naito [QC#26634, ADD]

    /**
     * Maximum number of rows in a CSV file.
     */
    int MAX_CSV_ROWS = 65000;

    /**
     * Header
     */
    String[] HEADER = {"Bill To Code", "Bill To Name", "FSR Number", "Term Code", "Term Name", "Tech", "CCY", "Total Charge", "Task Created By Code", "Task Created By Name" };
    
    /**
     * Fetch Size
     */
    int FETCH_SIZE = 1000;

    /**
     * Service Credit Review API:Approve Mode
     */
    String MODE_APPROVE = "01";

    /**
     * Service Credit Review Reject Mode
     */
    String MODE_REJECT = "02";

    // START 2018/06/25 M.Naito [QC#26634, ADD]
    /**
     * Process def name : NSWP0004
     */
    String PROCESS_DEF_NSWP0004 = "NSWP0004";
    // END 2018/06/25 M.Naito [QC#26634, ADD]
}
