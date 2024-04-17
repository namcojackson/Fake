/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLBL3170.constant;

/**
 *<pre>
 * Tracking Number Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/28   CITS            T.Hakodate      Create          QC#21913
 *</pre>
 */
public class NLBL3170Constant {

    /** Business ID */
    public static final String BIZ_ID = "NLBL3170";

    /** Screen ID */
    public static final String SCRN_ID = BIZ_ID + "Scrn00";

    /** Table ID for Row BG Color (A) */
    public static final String TABLE_ID_A = "A";

    /**
     * PARAM_INDEX_0
     */
    public static final int PARAM_INDEX_0 = 0;

    /**
     * PARAM_INDEX_1
     */
    public static final int PARAM_INDEX_1 = 1;

    /**
     * PARAM_INDEX_2
     */
    public static final int PARAM_INDEX_2 = 2;

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /** Button Assign */
    public static final String BTN_ASSIGN = "AddLine";

    public static final int MAX_INPUT_PARAM_NUM = 3;

    /** No more than [@] records can not be registered. */
    public static final String ZZZM9011E = "ZZZM9011E";

}
