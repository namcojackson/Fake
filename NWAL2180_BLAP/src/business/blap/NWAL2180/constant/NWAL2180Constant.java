/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2180.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Fujitsu         T.Murai         Create          N/A
 * 2016/07/22   Fujitsu         M.Yamada        Update          QC#11339
 * 2016/09/07   Fujitsu         M.Yamada        Update          QC#13256
 * 2016/12/20   Fujitsu         S.Iidaka        Update          QC#16141
 *</pre>
 */
public class NWAL2180Constant {

    // --------------------------------
    // Message ID
    // --------------------------------

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** [@] could not be acquired. */
    public static final String NWAM0325E = "NWAM0325E";

    /** Could not get the initial parameter. */
    public static final String NWAM0270E = "NWAM0270E";

    /** Entered @ can not in be performed. */
    public static final String NWAM0705E = "NWAM0705E";

    /** Cannot add anymore details. */
    public static final String NWAM0100E = "NWAM0100E";

    /** It doesn't exist [@] master. [@]. */
    public static final String NWAM0403E = "NWAM0403E";

    /** Please Set of PRC_MTR_PKG_MDL @ */
    public static final String NWAM0716E = "NWAM0716E";

    /** Number of Tier must be greater than 1 per billing counter. */
    public static final String NWAM0724E = "NWAM0724E";

    /** <pre>Rental Order requires all accessory lines to be added on Accessory Base.</pre> */
    public static final String NWAM0725E = "NWAM0725E";

    /** Accessory line item can't be duplicated. */
    public static final String NWAM0726E = "NWAM0726E";

    /** @ is invalid. */
    public static final String NWAM0789E = "NWAM0789E";

    /** Data is duplicated. [@]. */
    public static final String NWAM0551W = "NWAM0551W";

    /** <pre>Are you sure to delete the pricing information on the current shell?</pre> */
    public static final String NWAM0778W = "NWAM0778W";

    /** <pre>There are error in the @.</pre> */
    public static final String NWAM0782E = "NWAM0782E";

    /** <pre>There are no active billing counters associated with the Model (@).</pre> */
    public static final String NWAM0784W = "NWAM0784W";

    /** <pre>It does not exist in {@} table.</pre> */
    public static final String NWAM0809E = "NWAM0809E";

    /** <pre>It does not exist in {@} table.</pre> */
    public static final String NWBM0148E = "NWBM0148E";

    /** <pre>[@] has duplicate records. [@ : @].</pre> */
    public static final String NWAM2641E = "NWAM2641E";

    /** <pre>Invalid @ entered.</pre> */
    public static final String NWAM0900E = "NWAM0900E";

    /** <pre>Supply Program  (@) associated with the Service Shell is inactive. Can't proceed.</pre> */
    public static final String NWZM1661E = "NWZM1661E";

    /** NWAM0181E : The entered @ does not exist in Master. */
    public static final String NWAM0181E = "NWAM0181E";

    /** <pre>NWAM0916E : You cannot remove the all billing counters in config#.</pre> */
    public static final String NWAM0916E = "NWAM0916E";

    /** <pre>The term from/to is overlapping for other shell.</pre> */
    public static final String NWAM0930E = "NWAM0930E";

    /** <pre>One of the configuration of the Model has different pricing. Press "Save" again to proceed, or please check config level pricing.</pre> */
    public static final String NWAM0932W = "NWAM0932W";

    /** MAX_LINE = 200 */
    public static final int MAX_LINE = 200;

    /** DB Item CD */
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** DB Item CD */
    public static final String CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** DB Item CD */
    public static final String DPLY_LINE_NUM = "DPLY_LINE_NUM";

    /** DB Item CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** DB Item CD */
    public static final String PRC_CATG_CD = "PRC_CATG_CD";

    /** DB Item CD */
    public static final String PRC_CATG_NM = "PRC_CATG_NM";

    /** DB Item CD */
    public static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** DB Item CD */
    public static final String PRC_LIST_DPLY_NM = "PRC_LIST_DPLY_NM";

    /** DB Item CD */
    public static final String ADDL_BASE_PRC_DEAL_AMT = "ADDL_BASE_PRC_DEAL_AMT";

    /** DB Item CD */
    public static final String ADDL_BASE_PRC_CATG_CD = "ADDL_BASE_PRC_CATG_CD";

    /** DB Item CD */
    public static final String UNIT_MDSE = "UNIT_MDSE";

    /** DB Item CD */
    public static final String CHRG_MDSE = "CHRG_MDSE";

    /** DB Item CD */
    public static final String ADDL_CHRG_MDSE_CD = "ADDL_CHRG_MDSE_CD";

    /** DB Item CD */
    public static final String ADDL_CHRG_PRC_DEAL_AMT = "ADDL_CHRG_PRC_DEAL_AMT";

    /** DB Item CD */
    public static final String ADDL_CHRG_PRC_CATG_CD = "ADDL_CHRG_PRC_CATG_CD";

    /** DB Item CD */
    public static final String PRC_MTR_PKG_PK = "PRC_MTR_PKG_PK";

    /** DB Item CD */
    public static final String PRC_MTR_PKG_NM = "PRC_MTR_PKG_NM";

    /** DB Item CD */
    public static final String PRC_TIER_SVC_OFFER_NM = "PRC_TIER_SVC_OFFER_NM";

    /** DB Item CD */
    public static final String PRC_TIER_SVC_OFFER_CD = "PRC_TIER_SVC_OFFER_CD";

    /** DB Item CD */
    public static final String PRC_LIST_STRU_TP_CD = "PRC_LIST_STRU_TP_CD";

    /** DB Item CD */
    public static final String MAN_CONTR_OVRD_RSN_NM = "MAN_CONTR_OVRD_RSN_NM";

    /** DB Item CD */
    public static final String PRC_LIST_TP_CD = "PRC_LIST_TP_CD";

    /** DB Item CD */
    public static final String PRC_LIST_BAND_DESC_TXT = "PRC_LIST_BAND_DESC_TXT";

    /** DB Item CD */
    public static final String PRC_BOOK_MDSE_CD = "PRC_BOOK_MDSE_CD";

    /** DB Item CD */
    public static final String BASE_PRC_DEAL_AMT = "BASE_PRC_DEAL_AMT";

    /** DB Item CD */
    public static final String MTR_LB_NM_BLLG = "MTR_LB_NM_BLLG";

    /** DB Item CD */
    public static final String USG_MDSE_CD = "USG_MDSE_CD";

    /** DB Item CD */
    public static final String MTR_LB_NM_REG = "MTR_LB_NM_REG";

    /** DB Item CD */
    public static final String PRC_SVC_TIER_TP_CD = "PRC_SVC_TIER_TP_CD";

    /** DB Item CD */
    public static final String RANGE_FROM = "RANGE_FROM";

    /** DB Item CD */
    public static final String CONTR_MTR_MULT_RATE = "CONTR_MTR_MULT_RATE";

    /** DB Item CD */
    public static final String RANGE_TO = "RANGE_TO";

    /** DB Item CD */
    public static final String XS_MTR_AMT_RATE = "XS_MTR_AMT_RATE";

    /** DB Item CD */
    public static final String DS_CONTR_NUM = "DS_CONTR_NUM";

    /** DB Item CD */
    public static final String T_MDL_NM = "T_MDL_NM";

    /** DB Item CD */
    public static final String PRC_LIST_EQUIP_CONFIG_NUM = "PRC_LIST_EQUIP_CONFIG_NUM";

    /** DB Item CD */
    public static final String PRC_LIST_EQUIP_CONFIG_NM = "PRC_LIST_EQUIP_CONFIG_NM";

    /** POPUP NAME */
    public static final String POP_UP_COVERED_ITEM = "Covered Item Search Popup";

    /** POPUP NAME */
    public static final String POP_UP_RENTAL_EQUIP_COVERED_ITEM = "Rental Equipment Covered Item Search Popup";

    /** POPUP NAME */
    public static final String POP_UP_ADD_CHARGE_ITEM = "Additional Charge Item Search Popup";

    /** POPUP NAME : OpenWin_Service_Price_List_Service_Pricing */
    public static final String POP_UP_SVC_PRC_SP = "ServicePriceList_SP";

    /** <pre> POPUP NAME : OpenWin_Service_Price_List_Service_Pricing_forConfigPricing</pre> */
    public static final String POP_UP_SVC_PRC_SPC = "ServicePriceList_SPC";

    /** POPUP NAME : OpenWin_Service_Price_List_Accessory_Charge_P */
    public static final String POP_UP_SVC_PRC_AC_P = "ServicePriceList_AC_P";

    /** <pre>POPUP NAME : OpenWin_Service_Price_List_Accessory_Charge_RentalEquip_P</pre> */
    public static final String POP_UP_SVC_PRC_RE_P = "ServicePriceList_RE_P";

    /** <pre>POPUP NAME : OpenWin_Service_Price_List_Accessory_Charge_RentalEquip</pre> */
    public static final String POP_UP_SVC_PRC_RE = "ServicePriceList_RE";

    /** <pre>POPUP NAME : OpenWin_TierPricing</pre> */
    public static final String POP_UP_TIER_PRICING = "TierPricing";

    /** <pre>POPUP NAME : OpenWin_TierPricing_forConfigPricing</pre> */
    public static final String POP_UP_TIER_PRICING_CONFIG = "TierPricingConfig";

    /** <pre>POPUP NAME : OpenWin_Band_Search</pre> */
    public static final String POP_UP_BAND_LIST = "BandSearch";

    /** <pre>POPUP NAME : OpenWin_Band_SearchConfig</pre> */
    public static final String POP_UP_BAND_LIST_CONFIG = "BandSearchConfig";

    /** POPUP NAME : OpenWin_CmnBigActiveMeter */
    public static final String POP_UP_ADD_ACTIVE_METER = "AddActiveMeter";

    /** FLEET Dummy MODEL NAME */
    public static final String FLEET = "Fleet";

    /** MDSE_TP_CTX_TP NAME */
    public static final String SVC_PRC_ITEMS = "SVC_PRC_ITEMS";

    // ---------
    /** Usage Line Type : Parent */
    public static final String XX_FLG_PARENT = "P";

    /** Usage Line Type : Tier */
    public static final String XX_FLG_TIER = "T";

    /** Usage Line Type : Hard Counter */
    public static final String XX_FLG_HARD = "H";

    // ---------
    /** LINE_COLLAPS : - */
    public static final String LINE_COLLAPS = "-";

    /** LINE_EXPAND : + */
    public static final String LINE_EXPAND = "+";

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
