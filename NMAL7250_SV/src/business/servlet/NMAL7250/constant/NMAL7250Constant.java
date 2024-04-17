/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7250.constant;

/**
 *<pre>
 * NMAL7250Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/11   Fujitsu         W.Honda         Create          N/A
 * 2016/04/28   Fujitsu         W.Honda         Create          QC#5932
 *</pre>
 */
public class NMAL7250Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7250";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7250Scrn00";

    // QC#6738 2016/04/28 Add start
    /** Next Screen To Rule */
    public static final String NEXT_SCREEN_TO_RULE = "Rule";

    /** Next Screen To Adjustment */
    public static final String NEXT_SCREEN_TO_ADJUSTMENT = "Adjustment";
    // QC#6738 2016/04/28 Add end

    // --------------------------------
    // Common button
    // --------------------------------
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

    /** Header : Search */
    public static final String[] BTN_SEARCH = {"Search", "Search", "Search" };

    /** Header : Maintain Price Adjustment Precedence */
    public static final String[] BTN_MOVEWIN_PRICE_RULE_PRECEDENCE = {"MoveWin_PriceRulePrecedence", "MoveWin_PriceRulePrecedence", "Maintain Price Adjustment Precedence" };

    /** Header : Maintain Price Adjustment Precedence */
    public static final String[] BTN_MOVEWIN_PRICING_RULE = {"MoveWin_PricingRules", "MoveWin_PricingRules", "Create Rule" };

    /** Header : Maintain Price Adjustment Precedence */
    public static final String[] BTN_MOVEWIN_PRICE_ADJUSTMENT = {"MoveWin_PriceAdjustment", "MoveWin_PriceAdjustment", "Create Adjustment Table" };

    // --------------------------------
    // Message ID
    // --------------------------------

    /** [@] field is mandatory. */
    public static final String NMAM0288E = "NMAM0288E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Cannot select multiple. */
    public static final String NMAM8098E = "NMAM8098E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Item Name CheckBox */
    public static final String CHECK_BOX_NAME = "xxChkBox_A1";

}
