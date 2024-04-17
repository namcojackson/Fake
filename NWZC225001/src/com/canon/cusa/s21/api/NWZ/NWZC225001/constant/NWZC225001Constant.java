/**
 * <Pre>Copyright (c) 2016-2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC225001.constant;

/**
 * <pre>
 * NWZC2250 : Deal Configuration Order Creation API Constant.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/21   Fujitsu         T.Ishii         Create          N/A
 * 2017/03/02   Fujitsu         S.Ohki          Update          QC#17867
 * 2017/07/13   Fujitsu         K.Ishizuka      Update          QC#18806(L3#372)
 * 2017/08/25   Fujitsu         S.Iidaka        Update          QC#20627-1
 * 2017/12/05   Fujitsu         M.Ohno          Update          QC#22527
 * 2018/01/10   Fujitsu         N.Sugiura       Update          QC#18460
 * 2019/01/25   Fujitsu         M.Ishii         Update          QC#29980
 * 2019/01/29   Fujitsu         K.Ishizuka      Update          QC#30022
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 *</pre>
 */
public class NWZC225001Constant {

    // ************************
    // interface result code
    // ************************

    /** Interface Result : Normal */
    public static final String INTFC_RSLT_NORMAL = "00";

    /** Interface Result : Warning */
    public static final String INTFC_RSLT_WARNING = "10";

    /** Interface Result : Error */
    public static final String INTFC_RSLT_ERROR = "99";

    // ************************
    // mode
    // ************************

    /** mode : validate */
    public static final String MODE_VALIDATE = "10";

    /** mode : create order */
    public static final String MODE_CREATE_ORDER = "20";

    // ************************
    // message id
    // ************************

    /** "Global Company Code" is required. */
    public static final String NWZM0188E = "NWZM0188E";

    /** "Sales Date" for the entered parameter is not set. */
    public static final String NWZM0978E = "NWZM0978E";

    /** Mode is required. */
    public static final String NWZM0012E = "NWZM0012E";

    /** Specified value for Mode is invalid. */
    public static final String NWZM0013E = "NWZM0013E";

    /** "Order Source Reference Line Number" is required. */
    public static final String NWZM1905E = "NWZM1905E";

    /**
     * Quota Revenue Sales Rep @ was not imported due to 0% credit
     * percentage.
     */
    public static final String NWAM2640W = "NWAM2640W";

    /** Data insert failure. (table name is [@]) */
    public static final String NWAM0728E = "NWAM0728E";

    // START 2023/09/05 K.Watanabe [QC#53408, ADD]
    /** Data update failure. (table name is [@]) */
    public static final String NWAM0729E = "NWAM0729E";
    // END 2023/09/05 K.Watanabe [QC#53408, ADD]

    /** Relation between "@" and "@" is not correct. */
    public static final String NWAM0702E = "NWAM0702E";

    /** Freight Condition Code is required. */
    public static final String NWZM1266E = "NWZM1266E";

    /** Data delete failure. (table name is [@]) */
    public static final String NWAM0730E = "NWAM0730E";

    /** Failed to register advanced CPO. */
    public static final String NWZM1565E = "NWZM1565E";

    /** Some problem occurred, it failed to register the order. */
    public static final String NWZM2200E = "NWZM2200E";

    /** Some problem occurred, it failed to validate the order. */
    public static final String NWZM2201E = "NWZM2201E";

    /** Validation has been successfully. The order has no problem. */
    public static final String NWZM2202E = "NWZM2202E";

    /** Only one order allow to register. */
    public static final String NWZM2203E = "NWZM2203E";

    /** The Serial# is mandatory for a serialized item. */
    public static final String NWZM2223E = "NWZM2223E";

    // 2019/01/25 QC#29980 Add Start
    /** Quota Revenue Sales Rep [@] was not imported because writer has 0% credit percentage. */
    public static final String NWAM0969E = "NWAM0969E";
    // 2019/01/25 QC#29980 Add End
    
    /** You cannot import this order because this order does not include import line. */
    public static final String NWZM2305E = "NWZM2305E"; // 2019/01/29 S21_NA#30022 Add

    // ************************
    // message parameter
    // ************************
    /** order header. */
    public static final String ORDER_HEADER = "Order Header";

    /** order configuration. */
    public static final String ORDER_CONFIG = "Order Config";

    /** sales credit. */
    public static final String ORDER_SALES_CREDIT = "Sales Credit";

    /** install information. */
    public static final String ORDER_INSTALL_INFORMATION = "Install Information";

    /** site survey. */
    public static final String ORDER_SITE_SURVEY = "Site Survey";

    /** delivery information. */
    public static final String ORDER_DELIVERY_INFORMATION = "Delivery Information";

    /** contact person. */
    public static final String ORDER_CONTACT_PERSON = "Contact";

    /** order line. */
    public static final String ORDER_LINE = "Order Line";

    /** order RMA line. */
    public static final String ORDER_RMA_LINE = "Order RMA Line";

    /** service line. */
    public static final String SERVICE_LINE = "Service Line";

    /** service configuration reference. */
    public static final String SERVICE_CONFIG_REFERENCE = "Service Config Reference";

    /** service price. */
    public static final String SERVICE_PRICE = "Service Price";

    /** service usage price. */
    public static final String SERVICE_USAGE_PROCE = "Service Usage Price";

    /** service additional charge. */
    public static final String SERVICE_ADDITIONAL_BASE_CHARGE = "Service Additional Base";

    /** service additional charge. */
    public static final String SERVICE_ADDITIONAL_CHARGE = "Service Additional Charge";

    /** supply program. */
    public static final String SUPPLY_PROGRAM = "Supply Program";

    /** supply program line. */
    public static final String SUPPLY_PROGRAM_LINE = "Supply Program Line";

    // ************************
    // other
    // ************************

    /** yyyyMMddHHmmssSSS */
    public static final String YYYYMMDDHHMNSSFFF = "yyyyMMddHHmmssSSS";

    /**
     * service list type
     */
    public enum SVC_LIST {

        CONFIG_REF, PRC, USG_PRC,
    }

    /**
     * schedule list type
     */
    public enum SCHED_LIST {

        TMPL, TMPL_LINE
    }

    /** DS_IMPT_SVC_TP : Service */
    public static final String DS_IMPT_SVC_TP_SERVICE = "S";

    /** DS_IMPT_SVC_TP : Product */
    public static final String DS_IMPT_SVC_TP_PRODUCT = "P";

    /** BDL_PMT : Bundled */
    public static final String BDL_PMT_BUNDLED = "Y";

    /** BDL_PMT : UnBundled */
    public static final String BDL_PMT_UNBUNDLED = "N";

    /** NUM_CONST Tier Copy Vol Count Max Value */
    public static final String NUM_CONST_TIER_CNT_MAX_VAL = "SVC_TIER_PRC_CNT_MAX_VAL"; // 2017/03/02 S21_NA#17867

    /** Percent */
    public static final String PERCENT = "%";

    /** Value Set Code : Order Log Available */
    public static final String VAL_SET_CODE_ORDER_LOG_AVAILABLE ="ORDER_LOG_AVAILABLE"; // 2017/07/13 S21_NA#18806(L3#372)

    /** Service Install Rule Number(No Install:03) */
    public static final String SVC_ISTL_RULE_NUM_NO_INSTALL = "03";

    // 2017/12/05 QC#22527 add start
    /** VARCHAR_CONST define: LEASE_BYOT_MDSE_CD */
    public static final String LEASE_BYOT_MDSE_CD = "LEASE_BYOT_MDSE_CD";

    /** Merchandise Code Short Length (8) */
    public static final int MDSE_CD_SHORT_LENGTH = 8;

    /** Order Line Status Name (On Loan)  */
    public static final String ORD_LINE_STS_NM_ON_LOAN = "ON LOAN";

    /** Order Line Status Name (CLOSED)  */
    public static final String ORD_LINE_STS_NM_CLOSED = "CLOSED";

    /** Order Line Status Name (PENDING FULFILLMENT)  */
    public static final String ORD_LINE_STS_NM_PENDING_FULFILLMENT = "PENDING FULFILLMENT";

    /** LOAN_CONV_LINE_CRAT(OrdLineValSetKey) */
    public static final String LOAN_CONV_LINE_CRAT = "LOAN_CONV_LINE_CRAT";

    /** Loan Order Action (Loan Sell) */
    public static final String LOAN_ORD_ACTION_LOAN_SELL = "LOAN SELL";

    /** Loan Order Action (Loan Return) */
    public static final String LOAN_ORD_ACTION_LOAN_RETURN = "LOAN RETURN";
    // 2017/12/05 QC#22527 add end
    // 2018/01/10 QC#18460 Add Start
    /** Time Start Position */
    public static final int TIME_START_POS = 8;

    /** Time End Position */
    public static final int TIME_END_POS = 4;
    // 2018/01/10 QC#18460 Add End
}
