/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   CSAI            K.Lee           Create          N/A
 *</pre>
 */
package business.blap.NLCL0290.constant;


/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   CSAI            K.Lee           Create          N/A
 * 2017/01/10   CITS            Y.Fujii         Update          QC#16674-2
 * 2017/02/09   CITS            M.Naito         Update          QC#12673
 * 2017/02/10   CITS            Y.Fujii         Update          QC#16674-3
 * 2018/04/06   CITS            K.Masaki        Update          QC#18472/18490
 * 2018/05/08   CITS            T.Tokutomi      Update          QC#25925
 * 2018/12/03   CITS            T.hakodate      Update          QC#29172
 * 2019/05/10   CITS            T.Tokutomi      Update          QC#50008
 * 2022/10/21   Hitachi         N.Takatsu       Update          QC#60603
 *</pre>
 */
public interface NLCL0290Constant {

    String BUSINESS_ID = "NLCL0290";

    enum MESSAGE_ID {
        NZZM0002I, NZZM0003E, NMAM0007I, NMAM0098I, NMAM0009E, NMAM0010E, NMAM0050E, NMAM0072E, NMAM0177E, 
        NMAM8054E, NLAM8140E, NWDM0099E, NWDM0134E, NWDM0223E, NLBM1295E, NFCM0063E, NFCM0504E, NFCM0764E, 
        NFAM0075E;
    }

    String LAST_DATE = "99991231";

    /**
     * CSV Header Name
     */
    public static final String[] HEADER_NAME = {
          "Adjustment Transaction Type"
        , "Warehouse Code"
        , "Warehouse Name"
        , "Item Number"
        , "Item Description"
        , "Source Sub WH"
        , "Destination Sub WH"
        , "Item Quantity"
        , "Stock Status"
        //QC#29172 add start
        , "To Stock Status"
        //QC#29172 add end
        // QC#25925 Delete Location Status.
//        , "Location Status"
        , "Reason Code"
        , "Account"
        , "Comments"
        , "Serial"
        , "Config ID"
        , "Machine Type"
        , "Line Status"
        , "Transaction Cost"
        , "Document Number"
        , "Date & Time"
        , "Available Inventory Qty"
    };

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** delimiter string */
    public static final String VAR_CHAR_KEY_DELIMITER = "CONT_DELIMITER";

    /** segment token list size. */
    public static final int SEGMENT_TOKEN_LIST_SIZE = 9;

    /** The format of [@] is incorrect. */
    public static final String NLCM0210E = "NLCM0210E";

    /** Number of digits over error (item name [@]). */
    public static final String NLCM0211E = "NLCM0211E";

    /** Entered @ is invalid. */
    public static final String NLCM0065E = "NLCM0065E";

    /** message parameter : Segment */
    public static final String MSG_PARAM_SEGMENT = "Segment";

    /** message parameter : Company */
    public static final String MSG_PARAM_CMPY = "Company";

    /** message parameter : Extension */
    public static final String MSG_PARAM_EXTN = "Extension";

    /** message parameter : Cost Center */
    public static final String MSG_PARAM_CC = "Cost Center";

    /** message parameter : Account */
    public static final String MSG_PARAM_ACCT = "Account";

    /** message parameter : Company */
    public static final String MSG_PARAM_PROJ = "Project";

    /** message parameter : Product */
    public static final String MSG_PARAM_PROD = "Product";

    /** message parameter : Affiliate */
    public static final String MSG_PARAM_AFFL = "Affiliate";

    /** message parameter : Channel */
    public static final String MSG_PARAM_CH = "Channel";

    /** message parameter : Branch */
    public static final String MSG_PARAM_BR = "Branch";

    /** segment token list index : COA_CMPY_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD = 0;

    /** segment token list index : COA_BR_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD = 1;

    /** segment token list index : COA_CC_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD = 2;

    /** segment token list index : COA_ACCT_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD = 3;

    /** segment token list index : COA_PROD_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD = 4;

    /** segment token list index : COA_CH_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD = 5;

    /** segment token list index : COA_AFFL_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD = 6;

    /** segment token list index : COA_PROJ_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD = 7;

    /** segment token list index : COA_EXTN_CD */
    public static final int SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD = 8;

    /** segment element length : COA_CMPY_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CMPY = 3;

    /** segment element length : COA_EXTN_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_EXTN = 3;

    /** segment element length : COA_CC_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CC = 6;

    /** segment element length : COA_ACCT_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_ACCT = 8;

    /** segment element length : COA_PROJ_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_PROJ = 4;

    /** segment element length : COA_PROD_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_PROD = 8;

    /** segment element length : COA_AFFL_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_AFFL = 3;

    /** segment element length : COA_CH_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_CH = 3;

    /** segment element length : COA_BR_CD */
    public static final int SEGMENT_ELEMENT_LENGTH_BR = 3;

    /** DB Column Name  : COA_CMPY_CD */
    public static final String DB_COLUMN_COA_CMPY_CD = "COA_CMPY_CD";

    /** DB Column Name  : COA_BR_CD */
    public static final String DB_COLUMN_COA_BR_CD = "COA_BR_CD";

    /** DB Column Name  : COA_CC_CD */
    public static final String DB_COLUMN_COA_CC_CD = "COA_CC_CD";

    /** DB Column Name  : COA_ACCT_CD */
    public static final String DB_COLUMN_COA_ACCT_CD = "COA_ACCT_CD";

    /** DB Column Name  : COA_PROD_CD */
    public static final String DB_COLUMN_COA_PROD_CD = "COA_PROD_CD";

    /** DB Column Name  : COA_CH_CD */
    public static final String DB_COLUMN_COA_CH_CD = "COA_CH_CD";

    /** DB Column Name  : COA_AFFL_CD */
    public static final String DB_COLUMN_COA_AFFL_CD = "COA_AFFL_CD";

    /** DB Column Name  : COA_PROJ_CD */
    public static final String DB_COLUMN_COA_PROJ_CD = "COA_PROJ_CD";

    /** DB Column Name  : COA_EXTN_CD */
    public static final String DB_COLUMN_COA_EXTN_CD = "COA_EXTN_CD";

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NLCL0290_InventoryAdjustmentForm";

    // QC#29172 add start
    /**
     * The specified Serial Number is already allocated by other
     * order.
     */
    public static final String NLZM2409E = "NLZM2409E";

    /**
     * The Serial # specified exists in other location.
     */
    public static final String NLBM1337E = "NLBM1337E";

    /**
     * Stock status of the specified Serial number is different from
     * IB.
     */
    public static final String NLZM2414E = "NLZM2414E";
    
    /**
     * The specified Serial Number is located at customer site.
     */
    public static final String NLZM2410E = "NLZM2410E";

    // QC#29172 add end

    // QC#50008 add
    /**
     * mail template line Separator
     */
    public static final String mlTmpLineSeparator = "--------------------------------------------------------------------------------";

    // QC#50008 add
    /**
     * mail template Line Breka
     */
    public static final String mlTmpLineBreak = String.format("%n");

    // QC#50008 add
    /**
     * Mail send error. Please contact system administrator. Failed:[@]
     */
    public static final String NLCM0234E = "NLCM0234E";

    // START 2022/10/21 N.Takatsu[QC#60603, ADD]
    /**
     * The warehouse to be adjusted uses WMS. Please hit submit again if force to submit only on S21 side.
     */
    public static final String NLCM0244W = "NLCM0244W";

    /** ERROR */
    public static final String ERROR = "E";

    /** WARNING */
    public static final String WARNING = "W";

    // END 2022/10/21 N.Takatsu[QC#60603, ADD]
}
