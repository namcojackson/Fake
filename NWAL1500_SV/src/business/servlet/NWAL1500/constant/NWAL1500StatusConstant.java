/*
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 */
package business.servlet.NWAL1500.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         A.Suda          Create          N/A
 * 2016/05/11   Fujitsu         S.Iidaka        Update          S21_NA#7262
 * 2016/05/30   Fujitsu         K.Sato          Update          S21_NA#8509
 * 2016/07/04   Fujitsu         K.Sato          Update          S21_NA#7504/10723
 * 2017/10/03   Fujitsu         S.Takami        Update          S21_NA#21167
 * 2018/05/10   Fujitsu         S.Takami        Update          S21_NA#25251
 * 2019/05/07   Fujitsu         R.Nakamura      Update          S21_NA#50015
 * </pre>
 */
public class NWAL1500StatusConstant {

    // ----------- Authority -----------
    /** Reference Authority */
    public static final String REF_AUTH = "NWAL1500T010";

    /** Registration Authority */
    public static final String RGTN_AUTH = "NWAL1500T020";

    /** Modify Authority (Workflow Items) */
    public static final String MOD_WF_ITEMS_AUTH = "NWAL1500T030";

    /** Modify Authority (Price Items after Book) */
    public static final String MOD_PRICE_AUTH = "NWAL1500T040";

    /** Modify Authority (Qty Items after Book) */
    public static final String MOD_QTY_AUTH = "NWAL1500T050";

    // 2018/05/10 S21_NA#25251 Add Start
    /** Modify Authority (Outbound Price Manual Update) */ 
    public static final String MOD_PRICE_OUTBOUND = "NWAL1500T060";

    /** Modify Authority (Inbound Price Manual Update) */ 
    public static final String MOD_PRICE_INBOUND = "NWAL1500T070";

    // Add Start 2019/05/07 QC#50015
    /** Modify Authority (Logistics) */ 
    public static final String MOD_LOGISTICS = "NWAL1500T080";
    // Add End 2019/05/07 QC#50015

    // 2018/05/10 S21_NA#25251 Add End
    // ----------- Header Status -----------
    /** ENTERED */
    public static final String HEADER_STS_NM_ENTERED = "ENTERED";

    /** DI Check Hold */
    public static final String HEADER_STS_NM_DI_CHECK_HOLD = "DI CHECK HOLD";

    /** Validation Hold */
    public static final String HEADER_STS_NM_VALIDATION_HOLD = "VALIDATION HOLD";

    /** Supply Abuse Hold */
    public static final String HEADER_STS_NM_SUPPLY_ABUSE_HOLD = "SUPPLY ABUSE HOLD";

    /** Profitability Hold */
    public static final String HEADER_STS_NM_PROFIITABILITY_HOLD = "PROFITABILITY HOLD";

    // 2017/12/13 QC#22691 mod start
//    /** Credit Hold */
//    public static final String HEADER_STS_NM_CREDIT_HOLD = "CREDIT HOLD";
    /** Credit Hold */
    public static final String HEADER_STS_NM_CREDIT_HOLD = "CREDIT REVIEW";
    // 2017/12/13 QC#22691 mod end

    /** Pending Re-Submission */
    public static final String HEADER_STS_NM_PENDING = "PENDING RE-SUBMISSION";

    /** Booked */
    public static final String HEADER_STS_NM_BOOKED = "BOOKED";

    /** Closed */
    public static final String HEADER_STS_NM_CLOSED = "CLOSED";

    /** Cancelled */
    public static final String HEADER_STS_NM_CANCELLED = "CANCELLED";

    // ----------- Line Status -----------
    /** Unregistered */
    public static final String LINE_STS_NM_UNREGISTERED = "UNREGISTERED";

    /** ENTERED */
    public static final String LINE_STS_NM_ENTERED = "ENTERED";

    /** SO Cancelled */
    public static final String LINE_STS_NM_SO_CANCEL = "SO CANCELLED";

    /** PO Cancelled */
    public static final String LINE_STS_NM_PO_CANCEL = "PO CANCELLED";

    /** Booked */
    public static final String LINE_STS_NM_BOOKED = "BOOKED";

    /** Pending Fulfillment */
    public static final String LINE_STS_NM_PENDING_FULFILLMENT = "PENDING FULFILLMENT";

    /** Pending Allocation */
    public static final String LINE_STS_NM_PENDING_ALLOCATION = "PENDING ALLOCATION";

    /** Back Order */
    public static final String LINE_STS_NM_BACK_ORDER = "BACK ORDER";

    /** Waiting Pick */
    public static final String LINE_STS_NM_WAITING_PICK = "WAITING PICK";

    /** Delivered to Shop */
    public static final String LINE_STS_NM_DELIVERED_TO_SHOP = "DELIVERED TO SHOP";

    /** In Shop/Config */
    public static final String LINE_STS_NM_IN_SHOP_OR_CONFIG = "IN SHOP/CONFIG";

    /** Pending Ship */
    public static final String LINE_STS_NM_PENDING_SHIP = "PENDING SHIP";

    /** Shipped */
    public static final String LINE_STS_NM_SHIPPED = "SHIPPED";

    /** Pending Delivery Confirmation */
    public static final String LINE_STS_NM_PENDING_DELIVERY_CONF = "PENDING DELIVERY CONFIRMATION";

    /** Pending Installation */
    public static final String LINE_STS_NM_PENDING_INSTALL = "PENDING INSTALLATION";

    /** On Loan */
    public static final String LINE_STS_NM_ON_LOAN = "ON LOAN";

    /** Waiting Receipt */
    public static final String LINE_STS_NM_WAITING_RECEIPT = "WAITING RECEIPT";

    /** Pending Invoice */
    public static final String LINE_STS_NM_PENDING_INVOICE = "PENDING INVOICE";

    /** Billing Hold */
    public static final String LINE_STS_NM_BILLING_HOLD = "BILLING HOLD";

    // 2017/10/03 S21_NA#21167 Add Start
    /** Pending Dealer Install */
    public static final String LINE_STS_NM_PENDING_DEALER_INSTALL = "PENDING DEALER INSTALL";
    // 2017/10/03 S21_NA#21167 Add End

    /** Shipped Closed */
    public static final String LINE_STS_NM_SHIPPED_CLOSED = "CLOSED";

    /** Closed Loan Return */
    public static final String LINE_STS_NM_CLOSED_LOAN_RETURN = "CLOSED LOAN RETURN";

    /** Closed Loan Sold */
    public static final String LINE_STS_NM_CLOSED_LOAN_SOLD = "CLOSED LOAN SOLD";

    /** Invoiced */
    public static final String LINE_STS_NM_INVOICED = "INVOICED";

    /** Cancelled */
    public static final String LINE_STS_NM_CANCELLED = "CANCELLED";

    /** Pending Return */
    public static final String LINE_STS_NM_PENDING_RETURN = "PENDING RETURN";

    /** RWS Cancelled */
    public static final String LINE_STS_NM_RWS_CANCELLED = "RWS CANCELLED";

    /** Partial Received */
    public static final String LINE_STS_NM_PARTIAL_RECEIVED = "PARTIAL RECEIVED";

    /** Pending Inspection */
    public static final String LINE_STS_NM_PENDING_INSPECTION = "PENDING INSPECTION";

    /** Closed */
    public static final String LINE_STS_NM_CLOSED = "CLOSED";
}
