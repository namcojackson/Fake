package business.servlet.NMAL7300.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/10   Fujitsu         Y.Kanefusa      Create          N/A
 *</pre>
 */
public class NMAL7300Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7300";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7300Scrn00";

    /** Update Authority Screen ID T020 */
    public static final String UPDATE_AUTHORITY = "NMAL7300T020";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /* ------ Parameter Number ------ */
    public static final int GLBL_CMPY_CD = 1;

    public static final int PRC_ADJ_LINE = 2;

    public static final int CONDITION_LIST = 3;

    public static final int INPUT_LIST = 4;

    public static final int MAX_INPUT_PARAM_NUM = INPUT_LIST;

    /* ------ Error Message ------ */
    /** Details cannot be added because the number will exceed [@]. */
    public static final String NMAM0050E = "NMAM0050E";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /** @ is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** The value for [@] must be bigger than [@]. */
    public static final String NMAM0044E = "NMAM0044E";

    /* ------ Other ------- */
    public static final String ATTB_CHECK_BOX = "xxChkBox_B";
}
