package business.blap.ZZIL0040.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/15   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0040Constant {

    /*****************************************************************
     * Request Process Status
     ****************************************************************/

    /** ALL */
    public static final String ALL = "ALL";

    /** WAITING */
    public static final String WAITING = "WAITING";

    /** COMPLETED */
    public static final String COMPLETED = "COMPLETED";

    /** FAILED1 */
    public static final String FAILED1 = "FAILED1";

    /** FAILED2 */
    public static final String FAILED2 = "FAILED2";

    /** Search Process Status List */
    public static final String[] PROC_LIST = {ALL, WAITING, COMPLETED, FAILED1, FAILED2 };

    /** Changeable Status List(From) */
    public static final String[] RQST_STS_CHANGEABLE = {COMPLETED, FAILED1, FAILED2 };

    /** Change Status List(To) */
    public static final String[] RQST_STS_CHANGE_TO = {WAITING };

}
