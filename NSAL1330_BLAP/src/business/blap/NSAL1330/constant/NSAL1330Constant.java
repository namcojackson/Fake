/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1330.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 * 2017/07/24   Hitachi         Y.Takeno        Update          QC#20055
 * 2017/07/28   Hitachi         Y.Takeno        Update          QC#20088
 * 2017/08/04   Hitachi         Y.Takeno        Update          QC#20443
 * 2018/07/20   Fujitsu         R.Nakamura      Update          QC#27268
 * 2019/07/18   Fujitsu         K.Kato          Update          S21_NA#51327
 * 2023/07/05   CITS            T.Kojima        Update          QC#61472
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 * 2023/11/08   CITS            R.Jin           Update          QC#62108
 *</pre>
 */
public class NSAL1330Constant {

    // --------------------------------
    // Message ID
    // --------------------------------

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** [@] could not be acquired. */
    public static final String NSAM0646E = "NSAM0646E";

    /** Could not get the initial parameter. */
    public static final String NSAM0644E = "NSAM0644E";

    /** Entered @ can not in be performed. */
    public static final String NSAM0650E = "NSAM0650E";

    /** Cannot add anymore details. */
    public static final String NSAM0112E = "NSAM0112E";

    /** It doesn't exist [@] master. [@]. */
    public static final String NSAM0647E = "NSAM0647E";

    /** Please Set of PRC_MTR_PKG_MDL @ */
    public static final String NSAM0657E = "NSAM0657E";

    /** Number of Tier must be greater than 1 per billing counter. */
    public static final String NSAM0662E = "NSAM0662E";

    /** <pre>Rental Order requires all accessory lines to be added on Accessory Base.</pre> */
    public static final String NSAM0663E = "NSAM0663E";

    /** Accessory line item can't be duplicated. */
    public static final String NSAM0664E = "NSAM0664E";

    /** @ is invalid. */
    public static final String NSAM0671E = "NSAM0671E";

    /** Data is duplicated. [@]. */
    public static final String NSAM0648W = "NSAM0648W";

    /** <pre>Are you sure to delete the pricing information on the current shell?</pre> */
    public static final String NSAM0668W = "NSAM0668W";

    /** <pre>There are error in the @.</pre> */
    public static final String NSAM0669E = "NSAM0669E";

    /** <pre>There are no active billing counters associated with the Model (@).</pre> */
    public static final String NSAM0670W = "NSAM0670W";

    /** <pre>It does not exist in {@} table.</pre> */
    public static final String NSAM0672E = "NSAM0672E";

    /** <pre>It does not exist in {@} table.</pre> */
    public static final String NSAM0035E = "NSAM0035E";

    /** <pre>[@] has duplicate records. [@ : @].</pre> */
    public static final String NSAM0680E = "NSAM0680E";

    /** <pre>Invalid @ entered.</pre> */
    public static final String NSAM0674E = "NSAM0674E";

    /** <pre>Supply Program  (@) associated with the Service Shell is inactive. Can't proceed.</pre> */
    public static final String NSAM0682E = "NSAM0682E";

    /** NSAM0627E : The entered @ does not exist in Master. */
    public static final String NSAM0627E = "NSAM0627E";

    /** <pre>NSAM0676E : You cannot remove the all billing counters in config#.</pre> */
    public static final String NSAM0676E = "NSAM0676E";

    /** <pre>The term from/to is overlapping for other shell.</pre> */
    public static final String NSAM0678E = "NSAM0678E";

    /** <pre>One of the configuration of the Model has different pricing. Press "Save" again to proceed, or please check config level pricing.</pre> */
    public static final String NSAM0679W = "NSAM0679W";

    // START 2017/07/21 [QC#20055, ADD]
    /** The combination of [@] and [@] is incorrect. */
    public static final String NSZM0698E = "NSZM0698E";
    // END   2017/07/21 [QC#20055, ADD]

    // START 2023/11/08 R.Jin [QC#62108, DEL]
    // START 2023/07/05 T.Kojima [QC#61472, ADD]
//    /** Contract detail status associated with entered contract# and Config ID is inactive. Please reject this order and correct/resend it from Deal Config. */
//    public static final String NSZM1406E = "NSZM1406E";
//
//    /** Contract detail status associated with entered contract# and Config ID is inactive. Please change the contract# on Service Pricing Entry. */
//    public static final String NSAM0779E = "NSAM0779E";
    // END 2023/07/05 T.Kojima [QC#61472, ADD]
    // END 2023/11/08 R.Jin [QC#62108, DEL]

    // 2019/07/18 S21_NA#51327 Add Start
//    /** Contract has been created. It cannot be added to shell. */
//    public static final String NSAM0750E = "NSAM0750E";
    // 2019/07/18 S21_NA#51327 Add End

    // 2023/11/06 QC#61924 Add Start
    /** Bill To# {@} is deactivated for new transactions. */
    public static final String NSAM0781E = "NSAM0781E";

    /** Account# {@} is deactivated for new transactions. */
    public static final String NSAM0782E = "NSAM0782E";
    // 2023/11/06 QC#61924 Add End

    //    /** MDSE_TP_CTX_TP NAME - ACCESSORY */
    //    public static final String MDSE_TP_CTX_TP_ACSRY = "CPO_SVC_ADDL_BASE_ITEMS";
    //
    //    /** MDSE_TP_CTX_TP NAME - Covered Unit */
    //    public static final String MDSE_TP_CTX_TP_COVERED_UNIT = "CPO_SVC_CONFIG_ITEMS";
    //
    //    /** MDSE_TP_CTX_TP NAME - Additional Charge */
    //    public static final String MDSE_TP_CTX_TP_ADDL_CHRG = "CPO_SVC_ADDL_CHRG_ITEMS";

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

    // START 2017/08/04 [QC#20443, ADD]
    /** POPUP NAME : OpenWin_Service_Price_List_Accessory_Charge */
    public static final String POP_UP_SVC_PRC_AC = "ServicePriceList_AC";
    // END   2017/08/04 [QC#20443, ADD]

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

    // START 2017/07/28 [QC#20088, ADD]
    public static final String SVC_MEMO_TRX_NM_CPO_ORD_NUM = "CPO_ORD_NUM";
    // END   2017/07/28 [QC#20088, ADD]
    
    // Add Start 2018/07/20 QC#27268
    public static final String TARGET_ADDL_SVC_CHRG = "addlSvcChrg";
    // Add End 2018/07/20 QC#27268
}
