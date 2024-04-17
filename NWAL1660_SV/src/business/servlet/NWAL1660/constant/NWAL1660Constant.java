/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1660.constant;

/**
 *<pre>
 * NWAL1660Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         M.Yamada        Create          N/A
 * 2018/04/11   Fujitsu         Y.Kanefusa      Update          S21_NA#22965
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 *</pre>
 */
public class NWAL1660Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1660";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1660Scrn00";

    // --------------------------------
    /** MODE_REFERENCE = "10" */
    public static final String MODE_REFERENCE = "10";

    /** MODE_REGIST = "11" */
    public static final String MODE_REGIST = "11";

    // QC#22965 2018/04/11 Add Start
    /** PROCESS_LVL_HEADER = "01" */
    public static final String PROCESS_LVL_HEADER = "01";

    /** PROCESS_LVL_LINE = "02" */
    public static final String PROCESS_LVL_LINE = "02";
    // QC#22965 2018/04/11 Add End

    // --------------------------------
    // Common button for Popup
    // --------------------------------
    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F10 : Return */
    public static final String[] BTN_CMN_CLS = {"btn10", "CMN_Close", "Close" };

    // --------------------------------
    /** AddSellPrice */
    public static final String[] BTN_ADD_SELL_PRICE = {"AddSellPrice", "AddSellPrice", "Add" };

    /** AddCharges */
    public static final String[] BTN_ADD_CHARGES = {"AddCharges", "AddCharges", "Add" };

    /** DeleteSellPrice */
    public static final String[] BTN_DEL_SELL_PRICE = {"DeleteSellPrice", "DeleteSellPrice", "Delete" };

    /** DeleteCharges */
    public static final String[] BTN_DEL_CHARGES = {"DeleteCharges", "DeleteCharges", "Delete" };

    // QC#9700  2018/09/03 Add Start
    /** Price Analysis For Sell */
    public static final String[] BTN_ANALYSIS_SELL_PRICE = {"OpenWin_PriceAnalysisForSellPrice", "OpenWin_PriceAnalysisForSellPrice", "Price Analysis" };

    /** Price Analysis For Charges */
    public static final String[] BTN_ANALYSIS_CHARGES = {"OpenWin_PriceAnalysisForCharges", "OpenWin_PriceAnalysisForCharges", "Price Analysis" };
    // QC#9700  2018/09/03 Add End

    /** Calculate */
    public static final String[] BTN_CALCULATE = {"Calculate", "Calculate", "Calculate" };

    /** Reset */
    public static final String[] BTN_RESET = {"Reset", "Reset", "Reset" };

    // QC#9700  2018/09/03 Add Start
    /** Price History */
    public static final String[] BTN_HISTORY = {"OpenWin_PriceHistory", "OpenWin_PriceHistory", "History" };
    // QC#9700  2018/09/03 Add End

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** NWAM0553E : Only one Base Price can be valid. */
    public static final String NWAM0553E = "NWAM0553E";

    /** NWAM0186E : Please check at least 1 checkbox. */
    public static final String NWAM0186E = "NWAM0186E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------
    /** MAX_INPUT_PARAM_NUM = 78 */
    public static final int MAX_INPUT_PARAM_NUM = 80;

}
