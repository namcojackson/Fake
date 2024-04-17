/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1650.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Fujitsu         M.Yamada        Create          N/A
 * 2016/02/22   Fujitsu         M.suzuki        Update          S21_NA#2140
 *</pre>
 */
public class NWAL1650Constant {

    /** Screen ID */
    public static final String SCRN_ID = "NWAL1650Scrn00";

    /** Common button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Common button 10 */
    public static final String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };
    
    // 2016/02/22 S21_NA#2140 Mod Start ----------
    /** NWAL1650_PRM_CNT = 19 */
    public static final int NWAL1650_PRM_CNT = 19;
    // 2016/02/22 S21_NA#2140 Mod End ------------

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    
    // 2016/02/22 S21_NA#2140 Add Start ----------
    /** MODE_REFERENCE = "10" */
    public static final String MODE_REFERENCE = "10";
    // 2016/02/22 S21_NA#2140 Add End ------------

}
