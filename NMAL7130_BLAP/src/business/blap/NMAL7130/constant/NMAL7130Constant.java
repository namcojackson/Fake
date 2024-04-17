/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7130.constant;

/**
 *<pre>
 * NMAL7130Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7130Constant {

    //--------------------------------
    // Value
    //--------------------------------
    /** String Date High Value */
    public static final String HIGH_VAL_DT = "99991231";

    /** Status: Active */
    public static final String STS_ACTV = "ACTIVE";

    /** Status: Inactive */
    public static final String STS_INACTV = "INACTIVE";

    /** Check Box D */
    public static final String CHK_D = "xxChkBox_D1";

    /** String COMMA */
    public static final String COMMA = ",";

    /** NMAL7130_SEPT_CHAR */
    public static final String NMAL7130_SEPT_CHAR = "NMAL7130_SEPT_CHAR";

    /** NMAL7130_MDSE_TP_CTX_TP */
    public static final String NMAL7130_MDSE_TP_CTX_TP = "NMAL7130_MDSE_TP_CTX_TP";

    //--------------------------------
    // TAB
    //--------------------------------
    /** TAB:Registered Accounts */
    public static final String TAB_REGD_ACCT = "RegdAcct";

    /** TAB:Related Price Lists */
    public static final String TAB_RELN_PRC_LIST = "RelnPrcList";

    /** TAB:Init Price Point & Summary */
    public static final String TAB_INIT_PRC_PNT_SUM = "InitPrcPntSum";

    /** TAB:Transaction Charges */
    public static final String TAB_TRX_CHRG = "TrxChrg";

    /** TAB:Term & Conditions */
    public static final String TAB_TERM_COND = "TermCond";

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

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** [@] is already registered. */
    public static final String NMAM0010E = "NMAM0010E";

    /** The value for [@] must be smaller than [@]. */
    public static final String NMAM0043E = "NMAM0043E";

    /** Details cannot be added because the number will exceed [@]. */
    public static final String NMAM0050E = "NMAM0050E";

    /** @  is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** The process ended abnormally. */
    public static final String NMAM8020E = "NMAM8020E";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /** Is this OK to delete? */
    public static final String NMAM8103E = "NMAM8103W";
    
    /** The entered [@] does not exist in master. */
    public static final String NMAM8121E = "NMAM8121E"; // QC#11221 2016/07/01 Add

    //--------------------------------
    // Download CSV
    //--------------------------------
    /** csv file name : Adjustment Details */
    public static final String CSV_FILE_NM_REGD_ACCT = "NMAL7130PrcContrRegdAcct";

    /** csv file name : Adjustment Details */
    public static final String CSV_FILE_NM_RELN_PRC_LIST = "NMAL7130PrcContrRelnPrcList";

    /** csv file name : Transaction Charges */
    public static final String CSV_FILE_NM_TRX_CHRG = "NMAL7130PriceContrTrxChrg";

    /** csv file extension */
    public static final String CSV_FILE_EXTENSION = ".csv";

    /** CSV Download Header : Registered Accounts. */
    public static final String[] CSV_DOWNLOAD_HEADER_REGD_ACCT = new String[] {
        "Contract ID"
        , "Contract Name"
        , "Contract#"
        , "Bid#"
        , "CSAP Contract"
        , "Brief Description"
        , "Version#"
        , "Renewed Date"
        , "Term"
        , "Line of Business"
        , "Start Date"
        , "End Date"
        , "Active"
        , "Account#"
        , "Account Name"
        , "C/P"
        , "CSMP#"
        , "Price List"
        , "Price List Type"
        , "Line Start Date"
        , "Line End Date"
    };

    /** CSV Download Header : Related Price List. */
    public static final String[] CSV_DOWNLOAD_HEADER_RELN_PRC_LIST = new String[] {
        "Contract ID"
        , "Contract Name"
        , "Contract#"
        , "Bid#"
        , "CSAP Contract"
        , "Brief Description"
        , "Version#"
        , "Renewed Date"
        , "Term"
        , "Line of Business"
        , "Start Date"
        , "End Date"
        , "Active"
        , "Price List Name"
        , "Price List Type"
        , "Price List Status"
        , "Price List Start Date"
        , "Price List End Date"
    };

    /** CSV Download Header : Transaction Charges. */
    public static final String[] CSV_DOWNLOAD_HEADER_TRX_CHRG = new String[] {
        "Contract ID"
        , "Contract Name"
        , "Contract#"
        , "Bid#"
        , "CSAP Contract"
        , "Brief Description"
        , "Version#"
        , "Renewed Date"
        , "Term"
        , "Line of Business"
        , "Start Date"
        , "End Date"
        , "Active"
        , "Charge Type"
        , "Item Code"
        , "Rebate Percent"
        , "Admin Fee"
        , "Charge Name"
        , "Transaction Type"
        , "Special Qualifying Account Number"
        , "Account Name"
        , "Start Date"
        , "End Date"
        , "Rebate Type"
        , "Required"
    };
}
