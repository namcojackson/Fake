package business.servlet.ZZIL0040.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/15   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0040Constant {

    /** Business ID */
    public static final String BIZ_ID = "ZZIL0040";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "ZZIL0040Scrn00";

    /** Screen ID 01 */
    public static final String SCRN_ID_01 = "ZZIL0040Scrn01";

    /** Time List */
    public static final int HH24 = 24;

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

    /*****************************************************************
     * Common button
     ****************************************************************/

    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

}
