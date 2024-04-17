/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0060.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/17   Fujitsu         Kamide          Create          N/A
 * 2016/07/08   Hitachi         K.Kojima        Update          QC#11417
 *</pre>
 */
public interface NFDL0060Constant {

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    String NZZM0001W = "NZZM0001W";

    /**
     * No search results found.
     */
    String ZZZM9005W = "ZZZM9005W";

    // START 2016/07/08 K.Kojima [QC#11417,ADD]
    /** Message ID : NFCM0850E */
    public static final String NFCM0850E = "NFCM0850E";

    // END 2016/07/08 K.Kojima [QC#11417,ADD]

    /**
     * Maximum number of rows in a CSV file.
     */
    int MAX_CSV_ROWS = 65000;

    /**
     * Header
     */
    String[] HEADER = {"Item Type", "Work Item#", "Date", "Strategy Name", "Item Name", "Owner", "Type", "Status", "Priority", "Customer Name", "Created By", "$Amount Overdue" };

    /**
     * Fetch Size
     */
    int FETCH_SIZE = 1000;

}
