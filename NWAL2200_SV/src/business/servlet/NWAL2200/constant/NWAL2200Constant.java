/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200.constant;

/**
 *<pre>
 * NWAL2200Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2016/08/29   CITS            S.Tanikawa      Update          Unit Test#202
 * 2018/01/23   Fujitsu         T.Aoi           Update          QC#18798(Sol#173)
 * 2018/07/27   Fujitsu         Mz.Takahashi    Update          S21_NA#14307
 *</pre>
 */
public class NWAL2200Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL2200";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL2200Scrn00";

    /** Function Id update */
    public static final String FUNC_ID_UPDATE = "NWAL2200T010";

    /** Function Id read only */
    public static final String FUNC_ID_READ = "NWAL2200T020";

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

    /** Search */
    public static final String BTN_SEARCH = "Order_Search";

    /** Delivery Info */
    public static final String BTN_DELIVERY_INFO = "OpenWin_DeliveryInfo";

    /** Service pricing */
    public static final String BTN_SERVICE_PRICING = "OpenWin_ServicePricing";

    /** Profitability */
    public static final String BTN_PROFITABILITY = "OpenWin_Profitability";

    // UPDATE START 2016/08/31 Unit Test#202
    /** Approval */
    public static final String BTN_APPROVAL = "OpenWin_Approval";

    /** Address Mass Apply */
    public static final String BTN_ADDR_MASS_APLY_DTL = "OpenWin_AddressMassApplyDtl";

    /** Address Mass Apply */
    public static final String BTN_ADDR_MASS_APLY_RMA = "OpenWin_AddressMassApplyRma";

    // UPDATE END 2016/08/31 Unit Test#202
    /** SOM */
    public static final String BTN_SOM = "Call_SOM";

    /** Validate */
    public static final String BTN_VALIDATE = "Validate";

    /** Import attribute */
    public static final String BTN_IMPORT_ATTRIBUTE = "OpenWin_ImportAttribute";

    /** Special instruction */
    public static final String BTN_SPECIAL_INSTRUCTION = "OpenWin_SpecialInstruction";

    /** Sales credit */
    public static final String BTN_SALES_CREDIT = "OpenWin_SalesCredit";

    /** Line all expand */
    public static final String BTN_LINE_ALL_EXPAND = "Line_All_Expand";

    /** Line all collapsed */
    public static final String BTN_LINE_ALL_COLLAPSED = "Line_All_Collapsed";

    /** Buy out */
    public static final String BTN_BUYOUT = "OpenWin_Buyout";

    /** Price change */
    public static final String BTN_PRICE_CHANGE = "OpenWin_PriceChanges";

    /** Add line detail */
    public static final String BTN_ADD_LINE_DETAIL = "OpenWin_AddlLineDetails";

    // 2018/01/23 QC#18798 Add Start
    /** Config Check All */
    public static final String BTN_CONFIG_CHECK_ALL = "Config_Check_All";

    /** Config UnCheck All */
    public static final String BTN_CONFIG_UNCHECK_ALL = "Config_UnCheck_All";
    // 2018/01/23 QC#18798 Add End

    public static final String BTN_CONFIG_JUMP = "ConfigJump";

    // --------------------------------
    // Message ID
    // --------------------------------

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /**
     * The import order will be rejected. Is this OK? If this is OK, click Reject again.
     */
    public static final String NWAM0750W = "NWAM0750W";

    /** Only 1 @ can be selected. */
    public static final String NWAM0681E = "NWAM0681E";

    /** Selected line cannot process. */
    public static final String NWAM0682E = "NWAM0682E";

    /** Cannot select more than one @. */
    public static final String NWAM0666E = "NWAM0666E";

    /** @ must be selected. */
    public static final String NWAM0667E = "NWAM0667E";

    /** @ has not been entered. */
    public static final String NWAM0671E = "NWAM0671E";

    // UPDATE START 2016/08/31 Unit Test#202
    /** Please enter at least one Address Group. */
    public static final String NWAM0884E = "NWAM0884E";

    // UPDATE END 2016/08/31 Unit Test#202

    /** It has exceeded the maximum value (@). */
    public static final String NWAM0707E = "NWAM0707E";

    /** Only numeric values can be entered in [@]. */
    public static final String NWAM0915E = "NWAM0915E";

    /** Please enter "1" or a larger value. */
    public static final String NWAM0370E = "NWAM0370E";

    // Add Start 2018/07/25 S21_NA#14307
    /** Customer special instruction is not registered. */
    public static final String NWZM2274W = "NWZM2274W";
    // Add End 2018/07/25 S21_NA#14307

    // --------------------------------
    // TAB name
    // --------------------------------

    /**
     * TAB name additional header
     */
    public static final String TAB_HEADER = "Header";

    /**
     * TAB name additional header
     */
    public static final String TAB_ADDL_HEADER = "Addl_Header";

    /**
     * TAB name line configuration
     */
    public static final String TAB_LINE_CONFIG = "Line_Config";

    /**
     * TAB name RMA
     */
    public static final String TAB_RMA = "RMA";

    /**
     * TAB name Errors
     */
    public static final String TAB_ERRORS = "Errors";

    // --------------------------------
    // type code
    // --------------------------------

    /** Relation Type (Bill To) */
    public static final String RELN_TP_BILL_TO = "52";

    /** Relation Type (Ship To) */
    public static final String RELN_TP_SHIP_TO = "53";

    /** Price Context Type : Sales Price */
    public static final String PRC_CTX_TP_SALES_PRICE = "10";
    
    /** MODE_REFERENCE = "10" */
    public static final String MODE_REFERENCE = "10";

    // --------------------------------
    // type code
    // --------------------------------

    /** Comma */
    public static final String COMMA = ",";

    /** Blank */
    public static final String BLANK = "";

    /** Space */
    public static final String SPACE = " ";

    // UPDATE START 2016/08/31 Unit Test#202
    /** Address Mass Apply */
    public static final String EVENT_NM_NMAL2340_LINE = "OpenWin_AddressMassApplyDtl";

    /** Address Mass Apply */
    public static final String EVENT_NM_NMAL2340_RMA = "OpenWin_AddressMassApplyRma";
    // UPDATE END 2016/08/31 Unit Test#202
}
