/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1850.constant;

/**
 *<pre>
 * NWAL1850Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         Y.Taoka         Create          N/A
 * 2017/03/08   Fujitsu         K.Ishizuka      Update          QC#13856
 * 2022/06/02   Hitachi         K.Kitachi       Update          QC#60037
 *</pre>
 */
public class NWAL1850Constant {

    /** Business Application ID */
    public static final String BIZ_ID = "NWAL1850";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NWAL1850Scrn00";

    /** Business Application ID (Scheduling Agreement Entry) */
    public static final String BIZ_ID_FOR_ENTRY = "NWAL1840";

    //--------------------------------
    // Message ID
    //--------------------------------
    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The entered @ does not exist in Master. */
    public static final String NWAM0181E = "NWAM0181E";
    
    /** CSV Fetch size */
    public static final int FETCH_SIZE_MAX = 1000;

    /** CSV: Max row */
    public static final int CSV_MAX_ROW = 65000;
    
    // ADD START S21_NA QC#13856
    /** Download File Name */
    public static final String CSV_FILE_NAME_DOWNLOAD = "NMAL1850F00";

    /** CSV File Extension */
    public static final String CSV_FILE_EXT = ".csv";
    
    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** Too many search results. Please narrow your search criteria and retry */
    public static final String ZZZM9002W = "ZZZM9002W";
    // ADD END S21_NA QC#13856

    // START 2022/06/02 K.Kitachi [QC#60037, ADD]
    /** Delivery Hold Pulldown: ALL (Code) */
    public static final String DELY_HLD_CD_ALL = "ALL";

    /** Delivery Hold Pulldown: ALL (Text) */
    public static final String DELY_HLD_TXT_ALL = "ALL";

    /** Delivery Hold Pulldown: No Delivery Hold (Code) */
    public static final String DELY_HLD_CD_NULL = "NULL";

    /** Delivery Hold Pulldown: No Delivery Hold (Text) */
    public static final String DELY_HLD_TXT_NULL = "No Delivery Hold";
    // END 2022/06/02 K.Kitachi [QC#60037, ADD]
}
