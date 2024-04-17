/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL1020.constant;

/**
 *<pre>
 * Supply Order Serial# Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         T.Tsuchida      Create          N/A
 * 2018/07/12   Hitachi         K.Kim           Update          QC#27009
 *</pre>
 */
public class NSAL1020Constant {

    // [0]:Button Name [1]:Event Name [2]:Button Label
    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /** Search Button */
    public static final String BTN_SEARCH = "Search";

    /** Business ID : NSAL1020 */
    public static final String BUSINESS_ID = "NSAL1020";

    /** Screen ID : NSAL1020Scrn00 */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /** Function code : Search */
    public static final String FUNC_SEARCH = "20";

    // START 2018/07/12 K.Kim [QC#27009, MOD]
    /** PARAM_LENGTH_NSAL1020 : 7 */
    public static final int PARAM_LENGTH_NSAL1020 = 7;
    // END 2018/07/12 K.Kim [QC#27009, MOD]

    /** PARAM_LENGTH_NSAL1240 : 31 */
    public static final int PARAM_LENGTH_NSAL1240 = 31;

    /** PARAM_INDEX_2 : 2 */
    public static final int PARAM_INDEX_2 = 2;

    /** PARAM_INDEX_30 : 30 */
    public static final int PARAM_INDEX_30 = 30;

    // START 2018/07/12 K.Kim [QC#27009, MOD]
    /** OUT_PARAM_START_INDEX : 5 */
    public static final int OUT_PARAM_START_INDEX = 5;
    // END 2018/07/12 K.Kim [QC#27009, MOD]
}
