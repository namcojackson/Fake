package business.blap.NFCL3120.constant;

/**
 *<pre>
 * write-Off Request Creaton
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/03/2015   Fujitsu         T.Tanaka        Create          Initial
 * 2016/07/27   Hitachi         K.Kojima        Update          QC#4870
 * 2016/08/02   Hitachi         K.Kojima        Update          QC#5521
 *</pre>
 */
public interface NFCL3120Constant {

    /** */
    String DL_FILE_NAME = "NFCL3120";

    /** */
    String DL_FILE_EXT = ".csv";

    // START 2016/07/27 K.Kojima [QC#4870,ADD]
    /** Fetch size */
    public static final int FETCH_SIZE = 1000;

    /** Download size */
    public static final int CSV_LIMIT_COUNT = 65536;

    // END 2016/07/27 K.Kojima [QC#4870,ADD]

    // START 2016/08/02 K.Kojima [QC#5521,ADD]
    /** Biz ID */
    public static final String BIZ_ID = "NFCL3120";

    /** Message ID : ZZM9000E */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message ID : ZZZM9003I */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** Message ID : NFCM0858E */
    public static final String NFCM0858E = "NFCM0858E";

    /** Message ID : NFCM0859E */
    public static final String NFCM0859E = "NFCM0859E";
    // END 2016/08/02 K.Kojima [QC#5521,ADD]

}
