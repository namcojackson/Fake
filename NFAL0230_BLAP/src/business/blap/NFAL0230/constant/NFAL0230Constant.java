/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFAL0230.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/23   Fujitsu         Y.Murai         Create          N/A
 * 2017/02/06   Hitachi         E.Kameishi      Update          QC#16904
 * </pre>
 */
public class NFAL0230Constant {
    /** Business ID */
    public static final String BIZ_ID = "NFAL0230";

    /** Period */
    public static final String PERIOD = ".";

    /** Comma */
    public static final String COMMA = ",";

    /** Blank */
    public static final String BLANK = "";

    /** Space */
    public static final String SPACE = " ";

    /** Download File Name */
    public static final String CSV_FILE_NAME_DOWNLOAD = "COA_HIERARCHY";

    /** CSV File Extension */
    public static final String CSV_FILE_EXT = ".csv";

    /** CSV Fetch size */
    public static final int FETCH_SIZE_MAX = 1000;

    /** CSV: Max row */
    public static final int CSV_MAX_ROW = 65000;

    /** Tree View Max Level */
    public static final int DEFALT_MAX_LEVEL = 10;

    // START 2017/02/06 E.Kameishi [QC#16904,MOD]
    /** Segment Value Code : COA_BR */
    public static final String SEGMENT2 = "COA_BR";

    /** Segment Value Name : BRANCHES */
    public static final String BRANCHES = "BRANCHES";

    /** Segment Value Code : COA_CC */
    public static final String SEGMENT3 = "COA_CC";

    /** Segment Value Name : COST CENTERS */
    public static final String COST_CENTERS = "COST CENTERS";

    /** Segment Value Code : COA_ACCT */
    public static final String SEGMENT4 = "COA_ACCT";

    /** Segment Value Name : ACCOUNTS */
    public static final String ACCOUNTS = "ACCOUNTS";

    /** Segment Value Code : COA_PROD */
    public static final String SEGMENT5 = "COA_PROD";

    /** Segment Value Name : PRODUCTS */
    public static final String PRODUCTS = "PRODUCTS";

    /** Segment Value Code : COA_CH */
    public static final String SEGMENT6 = "COA_CH";

    /** Segment Value Name : CHANNELLS */
    public static final String CHANNELLS = "CHANNELLS";

    /** Segment Value Code : COA_AFFL */
    public static final String SEGMENT7 = "COA_AFFL";

    /** Segment Value Name : AFFILIATES */
    public static final String AFFILIATES = "AFFILIATES";

    /** Segment Value Code : COA_PROJ */
    public static final String SEGMENT8 = "COA_PROJ";

    /** Segment Value Name : MERCHANDISE TYPES */
    public static final String MERCHANDISE_TYPES = "MERCHANDISE TYPES";

    /** Segment Value Code : COA_EXTN */
    public static final String SEGMENT9 = "COA_EXTN";
    // END 2017/02/06 E.Kameishi [QC#16904,MOD]

    /** Segment Value Name : BUSINESS UNITS */
    public static final String BUSINESS_UNITS = "BUSINESS UNITS";

    /** csv Header Column */
    public static final String[] CSV_HEADER = {"Coa Value Text1", "Coa Value Text2", "Coa Value Text3", "Coa Value Text4" //
            , "Coa Value Text5", "Coa Value Text6", "Coa Value Text7", "Coa Value Text8", "Coa Value Text9", "Coa Value Text10", "Coa Value Text11", "Coa Value Text12" };

    // ----------- Message ID -----------
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** There is no hierarchy information is defined. */
    public static final String NFAM0174I = "NFAM0174I";

    /** Too many search results. Please narrow your search criteria and retry */
    public static final String ZZZM9002W = "ZZZM9002W";

    /** Search result exceeds @ records. Showing only first @ records.  */
    public static final String NFAM0157I = "NFAM0157I";

    /** There is hierarchy information with its depth is more than 12 and not being displayed. */
    public static final String NFAM0175W = "NFAM0175W";

}
