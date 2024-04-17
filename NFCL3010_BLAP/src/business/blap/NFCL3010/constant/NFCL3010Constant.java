package business.blap.NFCL3010.constant;

/**
 *<pre>
 * Batch Receipt Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 04/26/2016   Fujitsu         S.Fujita        Update          QC#3293
 * 2018/03/16   Fujitsu         H.Ikeda         Update          QC#21737
 * 2018/04/05   Fujitsu         H.Ikeda         Update          QC#21737-1
 * 
 *</pre>
 */
public interface NFCL3010Constant {

    public static final String ASTERISK = "*";

    public static final String TABLE_A = "A";

    public static final String TABLE_B = "B";

    // START 2016/04/26 S.Fujita [QC#3293,MOD]
    // --------------------------------
    // Fuzzy Search Value
    // --------------------------------
    /** percent. */
    public static final String PERCENT = "%";
    // END 2016/04/26 S.Fujita [QC#3293,MOD]

    // START 2018/04/05 H.Ikeda [QC#21737-1,MOD]
    // START 2018/03/16 H.Ikeda [QC#21737,ADD]
    // --------------------------------
    // CSV File Download
    // --------------------------------
    /** CSV File Name Download */
    public static final String CSV_FILE_NAME_DOWNLOAD = "NFCL3010_Batch_Receipt_Inquiry";

    /** CSV Header For Download */
    public static final String[] CSV_DOWNLOAD_HEADER = new String[] { "Receipt Doc#"
                                                                        ,"Receipt#"
                                                                        ,"Receipt Amount"
                                                                        ,"Applied Amount"
                                                                        ,"On Account"
                                                                        ,"Unapplied"
                                                                        ,"Receipt Type"
                                                                        ,"Receipt Date"
                                                                        ,"Customer#"
                                                                        ,"Customer Name"
                                                                        ,"Batch Name"
                                                                        ,"Receipt Source"
                                                                        ,"Batch Date"
                                                                        ,"Batch Status"
                                                                        ,"Ctrl Count"
                                                                        ,"Control Amount"
                                                                        ,"Lockbox File Nm"
                                                                        ,"Lockbox Name"
                                                                        ,"Lockbox Batch"
                                                                        };
    // END   2018/03/16 H.Ikeda [QC#21737,ADD]
    // END   2018/04/04 H.Ikeda [QC#21737-1,MOD]
}
