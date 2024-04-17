/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1910.constant;
/**
 *<pre>
 * NWAL1910Constant
 *
 * Date         Company         Name          Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/12   Fujitsu         M.Ishii           Create          N/A
 *</pre>
 */
public class NWAL1910Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1910";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1910Scrn00";

    /** Number of parameters. */
    public static final int PRM_NUM = 6;

    /** Default Line Number = 1 */
    public static final String DEFAULT_LINE_NUM = "1";

    /** PROCESS_LVL_HEADER */
    public static final String PROCESS_LVL_HEADER = "01";

    //---------------------------------------- button setting ----------------------------------------

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };
}
