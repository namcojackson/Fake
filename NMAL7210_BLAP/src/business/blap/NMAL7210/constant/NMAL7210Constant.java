/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7210.constant;

/**
 *<pre>
 * NMAL7210Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7210Constant {

    /** CSV file name : Transaction Conditions */
    public static final String CSV_FILE_NM = "NMAL7210PriceFormula";

    /** CSV file extension */
    public static final String CSV_FILE_EXTENSION = ".csv";

    /** CSV Header For DownLoad */
    public static final String[] CSV_DOWNLOAD_HEADER = new String[] {
          "Formula Name"
        , "Formula Description"
        , "Formula Type"
        , "Date From"
        , "Date To"
    };

    /** Search Max Count */
    public static final int SEARCH_MAX_CNT = 202;
    /** effThruMaxDt */
    public static final String EFF_THRU_MAX_DT = "99991231";
    //--------------------------------
    // Message ID
    //--------------------------------
    /** No search result were found.  */
    public static final String NMAM0007I = "NMAM0007I";

    /** Too many search results.  Please narrow your search criteria and retry. */
    public static final String NZZM0007E = "NZZM0007E";
}
