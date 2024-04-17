/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1760.constant;

/**
 *<pre>
 * NWAL1760Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/25   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public class NWAL1760Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1760";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1760Scrn00";

    /** index of 9th parameter : 9 */
    public static final int PRM_OUT_IX09 = 9;   // 20160305 add

    /** Number of parameters : 14 */
    public static final int PRM_CNT = 14;   // 20160305 add

    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

}
