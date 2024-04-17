/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1900.constant;

/**
 *<pre>
 * NWAL1900Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/13   Fujitsu         H.Kumagai        Create          N/A
 * 2018/11/27   Fujitsu         M.Ishii          Update          QC#29361
 *</pre>
 */
public class NWAL1900Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1900";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1900Scrn00";

    /** Td ID Description */
    public static final String TD_ID_DESCRIPTION = "Description";

    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    /** SEARCH Function Code 20 */
    public static final String FUNCTION_CODE_SEARCH = "20";

    /** Parameter Index 0 */
    public static final int PARAM_INDEX_0 = 0;

    /** Parameter Index 1 */
    public static final int PARAM_INDEX_1 = 1;

    /** Parameter Index 2 */
    public static final int PARAM_INDEX_2 = 2;

    /** Parameter Index 3 */
    public static final int PARAM_INDEX_3 = 3;

    /** Parameter Index 4 */
    public static final int PARAM_INDEX_4 = 4;

    // 2018/11/27 QC#29361 Add Start
    /** Parameter Index 5 */
    public static final int PARAM_INDEX_5 = 5;
    // 2018/11/27 QC#29361 Add End

    /** Applied */
    public static final String APPLIED = "Applied";

    /** Qualified */
    public static final String QUALIFIED = "Qualified";

    /** Html Table Id : A_Left */
    public static final String HTML_TBL_ID_A_LEFT = "A_Left";

    /** Html Table Id : A_Right */
    public static final String HTML_TBL_ID_A_RIGHT = "A_Right";

    /** Default Row Background Color **/
    public static final String DEF_ROW_BG_COLOR = "pHightlightBGcolor";

    /**
     * Html color yellow
     */
    public static final String HTML_COLOR_YELLOW = "#ffff00";

    /** Next Screen To Rule */
    public static final String NEXT_SCREEN_TO_RULE = "Rule";

    /** Next Screen To Adjustment */
    public static final String NEXT_SCREEN_TO_ADJUSTMENT = "Adjustment";

    // 2018/11/27 QC#29361 Add Start
    /** Manual Adjustment */
    public static final String MANUAL_ADJUSTMENT = "MANUAL ADJUSTMENT";
    // 2018/11/27 QC#29361 Add Start

}
