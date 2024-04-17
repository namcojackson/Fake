/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1820.constant;

/**
 *<pre>
 * NWAL1820Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         Y.Taoka         Create          N/A
 * 2017/03/08   Fujitsu         K.Ishizuka      Update          QC#13856
 *</pre>
 */
public class NWAL1820Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1820";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1820Scrn00";
    //--------------------------------
    // Message ID
    //--------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";
    
    // ADD START S21_NA QC#13856
    /** CSV Fetch size */
    public static final int FETCH_SIZE_MAX = 1000;

    /** CSV: Max row */
    public static final int CSV_MAX_ROW = 65000;
    
    /** DownLoad File Name */
    public static final String CSV_FILE_NAME_DOWNLOAD = "NMAL1852F00";

    /** CSV File Extension */
    public static final String CSV_FILE_EXT = ".csv";
    
    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** Too many search results. Please narrow your search criteria and retry */
    public static final String ZZZM9002W = "ZZZM9002W";
    // ADD END S21_NA QC#13856

}
