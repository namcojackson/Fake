package business.blap.NFCL0510.constant;

/**
 *<pre>
 * Lock box Error Correction Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 06/11/2018   Hitachi         Y.Takeno        Create          QC#19723
 *</pre>
 */
public interface NFCL0510Constant {

    /**
     * Table : A
     */
    public static final String TABLE_A = "A";

    /**
     * Table : B
     */
    public static final String TABLE_B = "B";

    // START 2018/06/11 [QC#19723, ADD]
    /** CSV file name */
    public static final String CSV_NAME = "Lockbox Correction Detail";

    /** CSV extension */
    public static final String CSV = ".csv";

    /** CSV Header */
    public static final String[] CSV_HEADER = {
         "Bat#"
        ,"Line#"
        ,"Status Msg Detail"
        ,"Receipt#"
        ,"Amount"
        ,"Transit Routing#"
        ,"Cust Bank Account#"
        ,"Customer"
        ,"Invoice#"
        ,"Bill Type"
        ,"Applied Amount"
    };

    /** AR_LOCK_BOX_FILE_NM */
    public static final String AR_LOCK_BOX_FILE_NM = "AR_LOCK_BOX_FILE_NM";

    /** AR_LOCK_BOX_BAT_NUM */
    public static final String AR_LOCK_BOX_BAT_NUM = "AR_LOCK_BOX_BAT_NUM";

    /** AR_LOCK_BOX_STS_CD */
    public static final String AR_LOCK_BOX_STS_CD = "AR_LOCK_BOX_STS_CD";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search results found. */
    public static final String ZZZM9005W = "ZZZM9005W";
    // END   2018/06/11 [QC#19723, ADD]
}
