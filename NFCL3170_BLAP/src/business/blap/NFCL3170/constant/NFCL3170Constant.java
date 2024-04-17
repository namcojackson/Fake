package business.blap.NFCL3170.constant;

/**
 *<pre>
 * bank Account Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/08/2015   Fujitsu         T.Tanaka        Create          Initial
 * 05/27/2016   Fujitsu         S.Fujita        Update          QC#8534
 * 01/12/2018   Fujitsu         T.Murai         Update          QC#21290
 *</pre>
 */

public interface NFCL3170Constant {

    /**
     * BusinessID:"NFCL3170"
     */
    String BUSINESS_ID = "NFCL3170";

    /**
     * CancelBusinessID:"NFCL3120"
     */
    String SEARCH_BUSINESS_ID = "NFCL3120";

    /**
     * XX_MODE_CD
     */
    public static final String NEW_BUTTON_ON = "1";

    /**
     * XX_MODE_CD
     */
    public static final String NEW_BUTTON_OFF = "0";

    /** */
    public static final String ADDR_NO_ERROR = "0";

    /** */
    public static final String ADDR_SUGGESTED = "1";

    /** */
    public static final String ADDR_ERROR = "9";

    public static final String DS_BANK_COMMON = "99";

    // START 2016/05/27 S.Fujita [QC#8534,ADD]
    /** You have entered an invalid address. Please enter a valid address.  */
    public static final String NMAM8359E = "NMAM8359E";

    /** We found a different address from the one you entered your address has been modified. Please confirm and click submit. */
    public static final String NMAM8360W = "NMAM8360W";
    // END   2016/05/27 S.Fujita [QC#8534,ADD]

    // START 2018/01/12 [QC#21290,ADD]
    /** A bank account can't be related to multiple customer number. */
    public static final String NFCM0879E = "NFCM0879E";
    // END   2018/01/12 [QC#21290,ADD]
}

