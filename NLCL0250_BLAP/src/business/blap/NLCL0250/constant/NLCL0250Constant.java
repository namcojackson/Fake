/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL0250.constant;

/**
 *<pre>
 * Onhand Inventory Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 * 02/09/2017   CITS            M.Naito         Update          QC#12673
 * 12/18/2017   CITS            S.Katsuma       Update          QC#22469
 *</pre>
 */
public class NLCL0250Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NLCL0250";

    /** Screen ID */
    public static final String SCREEN_ID = "NLCL0250Scrn00";

    /***************************************************
     * Message
     ***************************************************/

    /** Data does not exist in Stock Status Code Master. */
    public static final String NLZM0024E = "NLZM0024E";

    /** Data does not exist in @ */
    public static final String NLCM0170E = "NLCM0170E";

    /** [@] is not selected. */
    public static final String NLZM2274E = "NLZM2274E";

    /** The entered [@] does not exist in master. */
    public static final String NLZM2278E = "NLZM2278E";

    /** The combination of [@] and [@] does not exist in master. */
    public static final String NLZM2279E = "NLZM2279E";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NMAM0182E = "NMAM0182E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] field requires numeric input only. */
    public static final String ZZM9004E = "ZZM9004E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /***************************************************
     * Constant
     ***************************************************/

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Code Search Size */
    public static final int CODE_SEARCH_SIZE = 1001;

    /** Comma */
    public static final String COMMA = ",";

    /** Percent */
    public static final String PERCENT = "%";

    /** Blank */
    public static final String BLANK = "";

    /** Summary Search */
    public static final String SUMMARY_SEARCH = "1";

    /** Detail Search */
    public static final String DETAIL_SEARCH = "2";

    /** DS Condition Constant Group ID */
    public static final String LOC_STS_COND_CONST_GRP_ID = "NLCL0250_LOC_STS";

    /** Table Name : A */
    public static final String TABLE_A = "A";

    /** Table Header : A */
    public static final String TABLE_A_HEAD = "AHEAD";

    /***************************************************
     * CSV Download
     ***************************************************/

    /** CSV file Extension */
    public static final String CSV_FILE_EXTENSION  = ".csv";

    /** CSV file name (Summary) */
    public static final String CSV_FILE_SMRY_NAME = "NLCL0250_OnhandInventoryInquirySummary";

    /** CSV file name (Detail) */
    public static final String CSV_FILE_DTL_NAME = "NLCL0250_OnhandInventoryInquiryDetail";

    /** Limit Down load RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65001;

    /** CSV Header */
    public static final String[] CSV_HDR = new String[] {
          "Item Number"
        , "Item Description"
        , "Serial Number"
        , "Warehouse Type"
        , "Warehouse"
        , "Sub Warehouse"
        // START 2017/12/18 S.Katsuma [QC#22469,ADD]
        , "Min Qty"
        , "Max Qty"
        // END 2017/12/18 S.Katsuma [QC#22469,ADD]
        , "On Hand Qty"
        , "Available Qty"
        , "Config ID"
        , "Stock Status"
        , "Location Status"
        , "Inventory Age"
        , "From Location"
        , "To Location"
        , "Souce Document Type"
        , "Souce Document Number"
        , "Souce Document Line Number"
        , "Warehouse Code"
        , "Merchandise Type"
        , "Item Type"
        , "Business Unit"
        , "Product Group"
        , "Product Family"
        , "Product Line"
        , "Product Series"
        , "Install Base"
        , "Serial Transaction"};
}
