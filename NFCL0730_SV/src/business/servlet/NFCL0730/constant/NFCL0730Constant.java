package business.servlet.NFCL0730.constant;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/17/2015   Fujitsu         T.Tanaka         Create          Initial
 * 01/17/2018   Fujitsu         T.Murai          Update          QC#20563
 * 2022/11/10   Hitachi         S.Naya           Update          QC#57252
 * 2023/01/27   Hitachi         T.Kuroda         Update          QC#61089
 *</pre>
 */
public interface NFCL0730Constant {
    
    /**
     * Function ID
     */
    public static final String FUNC_ID = "NFCL0730";

    /**
     * Table : B
     */
    public static final String TABLE_A = "A";

    // START 2023/01/27 T.Kuroda [QC#61089, ADD]
    /**
     * Function Reference
     */
    public static final String FUNCTION_REFERENCE = "NFCL0730T010";
    // END   2023/01/27 T.Kuroda [QC#61089, ADD]

    /**
     * Function Update
     */
    public static final String FUNCTION_UPDATE = "NFCL0730T020";

    // START 2018/01/17 T.Murai [QC#20563,ADD]
    /**
     * This invoice has pending cash application and this amount will make balance minus.
     */
    public static final String NFCM0881E = "NFCM0881E";
    // END 2018/01/17 T.Murai [QC#20563,ADD]
    // START 2022/11/10 S.Naya [QC#57252,ADD]
    /**
     */
    static final String CMN_CLOSE = "CMN_Close";

    /** Empty Value */
    static final String EMPTY_STRING = "";

    /**
     * Comma
     */
    public static final String STR_COMMA = ".";

    /**
     * parameter index : 10
     */
    public static final int PARAM_INDEX_10 = 10;

    /**
     * parameter index : 9
     */
    public static final int PARAM_INDEX_9 = 9;

    /** @ is invalid. */
    static final String NFBM0041E = "NFBM0041E";

    /** [@] field is mandatory. */
    static final String ZZM9000E = "ZZM9000E";

    /** A Button **/
    public static final String BTN_A = "OpenWin_ChargeAccount";

    /** Other Code */
    public static final String OTHER_CODE = "999";
    // END   2022/11/10 S.Naya [QC#57252,ADD]
}
