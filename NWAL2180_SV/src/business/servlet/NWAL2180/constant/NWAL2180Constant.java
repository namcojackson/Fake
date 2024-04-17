/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2180.constant;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Fujitsu         T.Murai         Create          N/A
 * 2016/03/08   Fujitsu         M.Yamada        Update          QC#5038, 5091
 * 2016/03/29   Fujitsu         M.Yamada        Update          QC#5038-1
 * 2016/05/17   Fujitsu         M.Yamada        Update          QC#4628
 * 2016/12/20   Fujitsu         S.Iidaka        Create          QC#16141
 * 2018/05/08   Fujitsu         W.Honda         Update          QC#25030
 *</pre>
 */
public class NWAL2180Constant {

    /** Screen ID */
    public static final String SCREEN_ID = "NWAL2180Scrn00";

    /** Process ended normally. */
    public static final String BIZ_ID = "NWAL2180";

    /** Function ID(update) */
    public static final String FUNC_ID_UPD = "NWAL2180T010";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** @ has not been entered. */
    public static final String NWAM0671E = "NWAM0671E";

    /** Could not get the initial parameter. */
    public static final String NWAM0270E = "NWAM0270E";

    /** Could not get the initial parameter. */
    public static final String NWAM0298E = "NWAM0298E";

    /** Please specify a @ correctly. */
    public static final String NWAM0706E = "NWAM0706E";

    /** It has exceeded the maximum value (@). */
    public static final String NWAM0707E = "NWAM0707E";

    /** @ Is not unique. */
    public static final String NWAM0708E = "NWAM0708E";

    /** In the case of Manual Override is 'Yes', @ is required. */
    public static final String NWAM0709E = "NWAM0709E";

    /** @ link cannot be clicked when @ textbox is disabled. */
    public static final String NWAM0710E = "NWAM0710E";

    /** Value is missing in the parameter's required field.[@] */
    public static final String NWZM1107E = "NWZM1107E";

    /** Please select only one Check Box. */
    public static final String NZZM0009E = "NZZM0009E";

    /** It doesn't exist [@] master. [@]. */
    public static final String NWAM0403E = "NWAM0403E";

    /** <pre>NWAM0599E : The number of detail lines has reached the maximum.  Cannot add anymore.</pre> */
    public static final String NWAM0599E = "NWAM0599E";

    /** Select at least one of [@]. */
    public static final String NWAM0634E = "NWAM0634E";

    /** <pre>If the Contract Indicator is Aggregate, please set the same Service Package to all Model.</pre> */
    public static final String NWAM0714E = "NWAM0714E";

    /** <pre>Excess thing Charge Rate is the same for each Billing Counter in all Model under.</pre> */
    public static final String NWAM0717E = "NWAM0717E";

    /** <pre>Tiered Pricing can not be blank while tiered price lines exist. Please remove tiered lines first.</pre> */
    public static final String NWAM0719E = "NWAM0719E";

    /** <pre>Tier lines can be maintained only when the Tiered Pricing field has a value.</pre> */
    public static final String NWAM0720E = "NWAM0720E";

    /** Multiplier must be a multiple of @ */
    public static final String NWAM0723E = "NWAM0723E";

    /** Number of Tier must be greater than 1 per billing counter. */
    public static final String NWAM0724E = "NWAM0724E";

    /** You cannot delete this line. */
    public static final String NWAM0745E = "NWAM0745E";

    /** Copy Volume must be 0 or a positive number. */
    public static final String NWAM0751E = "NWAM0751E";

    /** <pre>Tier and Non-Tier are mixed.</pre> */
    public static final String NWAM0777E = "NWAM0777E";

    /** <pre>@ should be 0 or more.</pre> */
    public static final String NWAM0903E = "NWAM0903E";

    /** <pre>If the contract indicator is "Fleet", config pricing does not support.</pre> */
    public static final String NWAM0919E = "NWAM0919E";

    // -------------------- //

    /** NUM_CONST KEY NAME */
    public static final String NSAL0320_MTR_MULT_RATE_FCT_NUM = "NSAL0320_MTR_MULT_RATE_FCT_NUM";

    /** POPUP NAME */
    public static final String POP_UP_OVERRIDE = "Override Reason Search Popup";

    /** POPUP NAME */
    public static final String POP_UP_BOOK_ITEM = "Price Book Item Search Popup";

    /** POPUP NAME */
    public static final String POP_UP_BOOK_ITEM_CONFIG = "Price Book Item Search Popup Config";

    /** POPUP NAME */
    public static final String POP_UP_COVERED_ITEM = "Covered Item Search Popup";

    /** POPUP NAME */
    public static final String POP_UP_RENTAL_EQUIP_COVERED_ITEM = "Rental Equipment Covered Item Search Popup";

    /** POPUP NAME */
    public static final String POP_UP_COVERED_UNIT = "Covered Unit Search Popup";

    /** POPUP NAME */
    public static final String POP_UP_ADD_TO_CONTR = "Contract Number Search Popup";

    /** POPUP NAME */
    public static final String POP_UP_ADD_CHARGE_ITEM = "Additional Charge Item Search Popup";

    /** POPUP NAME : OpenWin_Service_Price_List_Service_Pricing */
    public static final String POP_UP_SVC_PRC_SP = "ServicePriceList_SP";

    /**
     * POPUP NAME :
     * OpenWin_Service_Price_List_Service_Pricing_forConfigPricing
     */
    public static final String POP_UP_SVC_PRC_SPC = "ServicePriceList_SPC";

    /** POPUP NAME : OpenWin_Service_Price_List_Accessory_Charge_P */
    public static final String POP_UP_SVC_PRC_AC_P = "ServicePriceList_AC_P";

    /** POPUP NAME : OpenWin_Service_Price_List_Accessory_Charge */
    public static final String POP_UP_SVC_PRC_AC = "ServicePriceList_AC";

    /** <pre>POPUP NAME : OpenWin_Service_Price_List_Additional_Service_Charge</pre> */
    public static final String POP_UP_SVC_PRC_AD = "ServicePriceList_AD";

    /** POPUP NAME : OpenWin_Service_Price_List_RentalEquip_P */
    public static final String POP_UP_SVC_PRC_RE_P = "ServicePriceList_RE_P";

    /** POPUP NAME : OpenWin_Service_Price_List_RentalEquip */
    public static final String POP_UP_SVC_PRC_RE = "ServicePriceList_RE";

    /** POPUP NAME : OpenWin_CmnBigActiveMeter */
    public static final String POP_UP_ADD_ACTIVE_METER = "AddActiveMeter";

    /** POPUP NAME */
    public static final String POP_UP_BAND_LIST = "BandSearch";

    /** POPUP NAME */
    public static final String POP_UP_BAND_LIST_CONFIG = "BandSearchConfig";

    /** POPUP NAME */
    public static final String POP_UP_TIER_PRICING = "TierPricing";

    /** POPUP NAME */
    public static final String POP_UP_TIER_PRICING_CONFIG = "TierPricingConfig";

    /** LINK NAME */
    public static final String LINK_MANUAL_OVERRIDE = "Manual Override Reason";

    /** LINK NAME */
    public static final String LINK_SVC_PRC_LIST = "Service Price List";

    /** LINK NAME */
    public static final String LINK_RENTAL_EQ_PRC_LIST = "Rental Eq Price List";

    /** LINK NAME */
    public static final String LINK_ADD_TO_CONTRACT = "Add To Contract#";

    /** TABLE NAME */
    public static final String MAN_CONTR_OVRD_RSN = "MAN_CONTR_OVRD_RSN";

    /** BUTTON NAME */
    public static final String LINK_OVERRIDE_RSN = "OpenWin_Override_Reasn";

    /** BUTTON NAME */
    public static final String BUTTON_REFRESH = "Refresh";

    /** BUTTON NAME */
    public static final String LINK_SERVICE_PRICE_LIST_SP = "OpenWin_Service_Price_List_Sp";

    /** BUTTON NAME */
    public static final String BUTTON_SUPPLY_AGREEMENT = "OpenWin_Supply_Agreement";

    /** BUTTON NAME */
    public static final String LINK_ADD_TO_CONT = "OpenWin_Add_To_Contract";

    /** LINK NAME */
    public static final String LINK_CUSTOMER = "Customer";

    /** LINK NAME */
    public static final String LINK_LOCATION = "Location";

    /** BUTTON NAME */
    public static final String BUTTON_CALC = "Calc";


    /** BUTTON NAME : Tier */
    public static final String BUTTON_TIER = "OpenWin_TierPricing";

    /** BUTTON NAME */
    public static final String BUTTON_BAND_SEARCH = "OpenWin_Band_Search";

    /** BUTTON NAME */
    public static final String BUTTON_BAND_SEARCH_CONFIG = "OpenWin_Band_SearchConfig";

    /** BUTTON NAME */
    public static final String BUTTON_ITEM_SEARCH_PRICING_BOOK_ITEM = "OpenWin_Item_Search_Pricing_Book_Item";

    /** BUTTON NAME */
    public static final String BUTTON_ITEM_SEARCH_PRICING_BOOK_ITEM_CONFIG = "OpenWin_Item_Search_PriceBookItemConfig";

    /** BUTTON NAME */
    public static final String BUTTON_ADD_ACCESSORY = "Add_Accessory";

    /** BUTTON NAME */
    public static final String BUTTON_DEL_ACCESSORY = "Del_Accessory";

    /** BUTTON NAME : Add_RentalEquip */
    public static final String BUTTON_ADD_ACCESSORY_RE = "Add_RentalEquip";

    /** BUTTON NAME : Del_RentalEquip */
    public static final String BUTTON_DEL_ACCESSORY_RE = "Del_RentalEquip";

    /** BUTTON NAME */
    public static final String LINK_SERVICE_PRICE_LIST_AC = "OpenWin_Service_Price_List_Accessory_Charge";

    /** BUTTON NAME */
    public static final String BUTTON_APPLY_BUNDLE_PRICE = "Apply_Bundle_Price";

    /** BUTTON NAME */
    public static final String BUTTON_ITEM_SEARCH_COVERED_ITEM = "OpenWin_Item_Search_Covered_Item";

    /** BUTTON NAME : OpenWin_Item_Search_Covered_Item_RentalEquip */
    public static final String BUTTON_ITEM_SEARCH_COVERED_ITEM_RE = "OpenWin_Item_Search_Covered_Item_RentalEquip";

    /** BUTTON NAME */
    public static final String BUTTON_SERVICE_PRICE_LIST_ACP = "OpenWin_Service_Price_List_Accessory_Charge_P";

    /** BUTTON NAME : OpenWin_Service_Price_List_RentalEquip_P */
    public static final String BUTTON_SERVICE_PRICE_LIST_REP = "OpenWin_Service_Price_List_RentalEquip_P";

    /** BUTTON NAME */
    public static final String BUTTON_ADD_SERVICE = "Add_Service";

    /** BUTTON NAME */
    public static final String BUTTON_DEL_SERVICE = "Del_Service";

    /** BUTTON NAME */
    public static final String LINK_SERVICE_PRICE_LIST_ASC = "OpenWin_Service_Price_List_Additional_Service_Charge";

    /** BUTTON NAME */
    public static final String BUTTON_ITEM_DESC_COVERED_ITEM = "Item_Desc_Covered_Item";

    /** BUTTON NAME : Item_Desc_Covered_Item_RentalEquip */
    public static final String BUTTON_ITEM_DESC_COVERED_ITEM_RE = "Item_Desc_Covered_Item_RentalEquip";

    /** BUTTON NAME */
    public static final String BUTTON_ITEM_SEARCH_COVERED_UNIT = "OpenWin_Item_Search_Covered_Unit";

    /** BUTTON NAME */
    public static final String BUTTON_ITEM_DESC_COVERED_UNIT = "Item_Desc_Covered_Unit";

    /** BUTTON NAME */
    public static final String BUTTON_ITEM_SEARCH_ADD = "OpenWin_Item_Search_Additional_Charge_Item";

    /** BUTTON NAME */
    public static final String BUTTON_ITEM_DESC_ADD = "Item_Desc_Additional_Charge_Item";

    /** BUTTON NAME */
    public static final String BUTTON_APPLY_ALL_HEADER = "ApplyAllBillToFromHeader";

    /** BUTTON NAME */
    public static final String BUTTON_APPLY_ALL_MODEL = "ApplyAllFromModel";

    /** BUTTON NAME */
    public static final String BUTTON_ADD_ACTIVE_METER = "OpenWin_CmnBigAddActiveMeter";

    /** BUTTON NAME */
    public static final String BUTTON_DEL_ACTIVE_METER = "Del_ActiveMeter";

    /** MDSE_TP_CTX_TP NAME */
    public static final String CPO_SVC_ADDL_BASE_ITEMS = "CPO_SVC_ADDL_BASE_ITEMS";

    /** MDSE_TP_CTX_TP NAME */
    public static final String CPO_SVC_ADDL_CHRG_ITEMS = "CPO_SVC_ADDL_CHRG_ITEMS";

    /** MDSE_TP_CTX_TP NAME */
    public static final String CPO_SVC_CONFIG_ITEMS = "CPO_SVC_CONFIG_ITEMS";

    /** MDSE_TP_CTX_TP NAME */
    public static final String SVC_PRC_ITEMS = "SVC_PRC_ITEMS";

    /** Dummy model name of Fleet case. */
    public static final String FLEET = "Fleet";

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

    /** F6 : DownLoad */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    //-------
    /** OnClick_LineCtrl Expanded(+) */
    public static final String[] BTN_BIZ_LINE_CTRL_EXPANDED = {"OnClick_LineCtrl", "OnClick_LineCtrl", "-" };

    /** OnClick_LineCtrl Collapsed(-) */
    public static final String[] BTN_BIZ_LINE_CTRL_COLLAPSED = {"OnClick_LineCtrl", "OnClick_LineCtrl", "+" };

    //-------
    /** number of NWAL1130' parameters : 7 */
    public static final int NWAL1130_PRM_NUM = 7;

    /** number of NWAL2190' parameters : 14 */
    public static final int NWAL2190_PRM_NUM = 14;

    /** number of NWAL2130' parameters : 18 */
    public static final int NWAL2130_PRM_NUM = 18;

    /** number of NWAL2160' parameters : 8 */
    public static final int NWAL2160_PRM_NUM = 8;

    /** number of NWAL1760' parameters : 14 */
    public static final int NWAL1760_PRM_NUM = 14;

    /** NWAL2160 PROC_MD_UPD */
    public static final String PROC_MD_UPD = "1";

    /** NWAL2160 PROC_MD_REF */
    public static final String PROC_MD_REF = "2";

    // ---------
    /** Usage Line Type : Parent */
    public static final String XX_FLG_PARENT = "P";

    /** Usage Line Type : Tier */
    public static final String XX_FLG_TIER = "T";

    /** Usage Line Type : Hard Counter */
    public static final String XX_FLG_HARD = "H";

    /** MAX_VOL_CNT : 999,999,999 */
    public static final BigDecimal MAX_VOL_CNT = new BigDecimal(999999999);

    /** MAX_BASE_AMT : 10,000,000 */
    public static final BigDecimal MAX_BASE_AMT = new BigDecimal(10000000);

    /** MAX_BASE_PRC : 999,999,999,999,999.99 */
    public static final BigDecimal MAX_BASE_PRC = new BigDecimal("999999999999999.99");

    /** MAX_BASE_PRC_STR : 999,999,999,999,999.99 */
    public static final String MAX_BASE_PRC_STR = "999,999,999,999,999.99";

    /** decimal format for amount */
    public static final DecimalFormat DF_AMT = new DecimalFormat("#,###.##");

    /** decimal format for count */
    public static final DecimalFormat DF_CNT = new DecimalFormat("#,###");

    /** number of after decimal digits of amount : 2 */
    public static final int AFTER_DECIMAL_DIGITS_AMT = 2;

    /** number of after decimal digits of multiplier rate : 2 */
    public static final int AFTER_DECIMAL_DIGITS_MULTI_RATE = 2;

    /** number of after decimal digits of amount rate : 5 */
    public static final int AFTER_DECIMAL_DIGITS_AMT_RATE = 5;

    // Add Start 2018/05/08 QC#25030
    /** number of after decimal digits of Integer : 0 */
    public static final int AFTER_DECIMAL_DIGITS_INT = 0;
    // Add End 2018/05/08 QC#25030

    /** line separator for SQL */
    public static final String NEWLINE = System.getProperty("line.separator");

    /** Comma */
    public static final String COMMA = ",";

    /** Single Quotation */
    public static final String SINGLE_QUOTE = "'";

    /** IX_POP_PRM_FIRST_LINE_ADDR = 7 */
    public static final int IX_POP_PRM_FIRST_LINE_ADDR = 7;

    /** IX_POP_PRM_SCD_LINE_ADDR = 8 */
    public static final int IX_POP_PRM_SCD_LINE_ADDR = 8;

    /** IX_POP_PRM_THIRD_LINE_ADDR = 9 */
    public static final int IX_POP_PRM_THIRD_LINE_ADDR = 9;

    /** IX_POP_PRM_FRTH_LINE_ADDR = 10 */
    public static final int IX_POP_PRM_FRTH_LINE_ADDR = 10;

    /** IX_POP_PRM_CTY_ADDR = 11 */
    public static final int IX_POP_PRM_CTY_ADDR = 11;

    /** IX_POP_PRM_POST_CD = 15 */
    public static final int IX_POP_PRM_POST_CD = 15;

    /** IX_POP_PRM_ST_CD = 14 */
    public static final int IX_POP_PRM_ST_CD = 14;

    /** USAGE_CD_BILL_TO_ONLY = "52" */
    public static final String USAGE_CD_BILL_TO_ONLY = "52";

    /** Status Code for Active in Account Search Popup */
    public static final String STATUS_CD_ACTIVE = "01";

    /** Display for Active in Account Search Popup */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";

    /** */
    public static enum XX_PAGE {
        PAGE_SHELL("S", "Order Number") //
        , PAGE_IMPT("I", "Source Reference#");

        private final String code;

        private final String name;

        private XX_PAGE(final String code, final String name) {
            this.code = code;
            this.name = name;
        }

        /** */
        public String getCode() {
            return code;
        }

        /** */
        public String getName() {
            return name;
        }
    }
}
