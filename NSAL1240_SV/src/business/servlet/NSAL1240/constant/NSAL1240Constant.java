/* <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSAL1240.constant;

/**
 *<pre>
 * Config# Search Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Hitachi         A.Kohinata      Create          N/A
 * 2016/03/29   Hitachi         M.Gotou         Update          QC#4910
 * 2016/05/11   Hitachi         T.Tomita        Update          QC#7832
 *</pre>
 */
public class NSAL1240Constant {

    // [0]:Button Name [1]:Event Name [2]:Button Label
    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /** SCRN_ID : NSAL1240Scrn00 */
    public static final String SCREEN_ID = "NSAL1240Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL1240";

    /** Function code : Search */
    public static final String FUNC_SEARCH = "20";

    /** Function code : Update */
    public static final String FUNC_UPDATE = "40";

    /** NMAL6760_PRM_LENGTH : 15 */
    public static final int NMAL6760_PRM_LENGTH = 15;

    /** NMAL6800_PRM_LENGTH : 7 */
    public static final int NMAL6800_PRM_LENGTH = 7;

    // START 2016/05/11 T.Tomita [QC#7832, DEL]
//    /** NSAL1240_PRM_LENGTH : 31 */
//    public static final int NSAL1240_PRM_LENGTH = 31;
    // END 2016/05/11 T.Tomita [QC#7832, DEL]

    /** MODE_CD : 01 */
    public static final String MODE_01 = "01";

    /** MODE_CD : 02 */
    public static final String MODE_02 = "02";

    /** MODE_CD : 03 */
    public static final String MODE_03 = "03";

    // START 2016/03/29 M.Gotou [QC#4910, ADD]
    /** Parameter Bill To's Only */
    public static final String PARAMS_BILL_TO_ONLY = "02";

    /** Parameter Ship To's Only */
    public static final String PARAMS_SHIP_TO_ONLY = "03";
    // END 2016/03/29 M.Gotou [QC#4910, ADD]
}
