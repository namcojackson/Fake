/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB248001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/01/2022   Hitachi         D.Yoshida       Create          QC#59973
 * </pre>
 */
public class NWAB248001Constant {

    /** Program Name */
    public static final String PROGRAM_NM = "Schedule Agreement Adjustment To Contract Batch";

    /** Business Id */
    public static final String BIZ_ID = "NWAB2480";

    /** Batch Id */
    public static final String BATCH_ID = BIZ_ID + "01";

    /** Schedule Agreement Sub Line Number(001) */
    public static final String SCHD_SUB_LINE_NUM_001 = "001";

    /** Error Message Header Level */
    public static final BigDecimal HDR_LINE = BigDecimal.ZERO;
    
    /** Order Category Context Type Code (EQUIPMENT_ORDER) */
    public static final String EQUIPMENT_ORDER_VALUE_SET = "EQUIPMENT_ORDER";

    /** Order Category Context Type Code (SUPPLIES_ORDER) */
    public static final String SUPPLIES_ORDER_VALUE_SET = "SUPPLIES_ORDER";

    /** NWZM0011E : "Data Global Company Code" is required. */
    public static final String NWZM0011E = "NWZM0011E";

    /** NWAM0713E : ,Error occurred due to a process unable to complete. Please contact IT Dept. **/
    public static final String NWAM0713E = "NWAM0713E";

    /** NWAM0728E : Data insert failure. (table name is [@]) **/
    public static final String NWAM0729E = "NWAM0729E";

    /** NWAM0722E : [@] Search results for does not exist. **/
    public static final String NWAM0722E = "NWAM0722E";

    /** NWZM0482E : "Sales Date" is not set. **/
    public static final String NWZM0482E = "NWZM0482E";

    /** NWAM8461E : Frequency or periodic qty was not provided. **/
    public static final String NWAM8461E = "NWAM8461E";

    /** NWAM8462E : Schedule agreement #:[@] was not adjustment to contract. Because schedule agreement status was not active. **/
    public static final String NWAM8462E = "NWAM8462E";

    /** NWAM8463E : Ordered or Scheduled plan is not found. **/
    public static final String NWAM8463E = "NWAM8463E";

    /** NWAM8464E : Schedule has a schedule of future dates from Contract's valid thru date. **/
    public static final String NWAM8464E = "NWAM8464E";

    /** ZZM9000E : [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    // ----------- Message Parameter -----------
    /** Category */
    public static final String MSG_PARAM_CATG = "Category";

    /** Reason Code */
    public static final String MSG_PARAM_RSN_CD = "Reason Code";

    /** Source Name */
    public static final String MSG_PARAM_SRC_NM = "Source Name";

    /** Configuration ID */
    public static final String MSG_PARAM_CONF = "Configuration ID";

    /** Configuration ID */
    public static final String MSG_PARAM_CONTR_NUM = "Contract #";

    /** Configuration ID */
    public static final String MSG_PARAM_SER_NUM = "Serial #";

    /** Customer PO Number */
    public static final String MSG_PARAM_CUST_PO_NUM = "Customer PO Number";

    /** Price List */
    public static final String MSG_PARAM_PRC_LIST = "Price List";

    /** Bill To Name */
    public static final String MSG_PARAM_BILL_TO_NM = "Bill To Name";

    /** Bill To Number */
    public static final String MSG_PARAM_BILL_TO_NUM = "Bill To Number";

    /** Bill To Location */
    public static final String MSG_PARAM_BILL_TO_LOC = "Bill To Location";

    /** Ship To Name */
    public static final String MSG_PARAM_SHIP_TO_NM = "Ship To Name";

    /** Ship To Number */
    public static final String MSG_PARAM_SHIP_TO_NUM = "Ship To Number";

    /** Ship To Location */
    public static final String MSG_PARAM_SHIP_TO_LOC = "Ship To Location";

    /** Sold To Name */
    public static final String MSG_PARAM_SOLD_TO_NM = "Sold To Name";

    /** Sold To Number */
    public static final String MSG_PARAM_SOLD_TO_NUM = "Sold To Number";

    /** Sold To Location */
    public static final String MSG_PARAM_SOLD_TO_LOC = "Sold To Location";

    /** Freight Terms */
    public static final String MSG_PARAM_FRT_TERMS = "Freight Terms";

    /** Carrier Service Level */
    public static final String MSG_PARAM_CARR_SVC_LVL = "Carrier Service Level";

    /** Payment Terms */
    public static final String MSG_PARAM_PMT_TERMS = "Payment Terms";

    /** Item # */
    public static final String MSG_PARAM_ITEM = "Item#";

    /** Warehouse */
    public static final String MSG_PARAM_WH = "Warehouse";

    /** Sub Warehouse */
    public static final String MSG_PARAM_SUB_WH = "Sub Warehouse";

    /** Qty Allowed */
    public static final String MSG_PARAM_QTY_ALLW = "Qty Allowed";

    /** UOM */
    public static final String MSG_PARAM_UOM = "UOM";

    /** Unit Sell Price */
    public static final String MSG_PARAM_UNIT_SELL_PRC = "Unit Sell";

    /** Unit Sell Price */
    public static final String MSG_PARAM_UNIT_LIST_PRC = "Unit List";

    /** Order Qty */
    public static final String MSG_PARAM_ORD_QTY = "Ord Qty";

    /** Button Name (Price $) */
    public static final String MSG_PARAM_BTN_PRC = "Price $";

    /** Line */
    public static final String MSG_PARAM_LINE = "Line";

    /** Schedule Line */
    public static final String MSG_PARAM_SCHD_LINE = "Schd Line";

    /** Price Change */
    public static final String MSG_PARAM_PRC_CHG = "Price Change";

    /** API Name (NWZC2060 Supersede API) */
    public static final String MSG_PARAM_SUPER_SEDE_API = "NWZC2060 Supersede API";

    /** Sales Rep Code */
    public static final String MSG_PARAM_SLS_REP_CD = "Sales Rep Code";

    /** Sales Rep Name */
    public static final String MSG_PARAM_SLS_REP_NM = "Sales Rep Name";

    /** Shipping Service Level */
    public static final String MSG_PARAM_SHPG_SVC_LVL = "Service Level";

    /** Valid From Name */
    public static final String MSG_PARAM_VALID_FROM = "Valid From";

    /** Valid To Name */
    public static final String MSG_PARAM_VALID_TO = "Valid To";

    /** Contract From Name */
    public static final String MSG_PARAM_CONTR_FROM = "Contract Start Date";

    /** Contract To Name */
    public static final String MSG_PARAM_CONTR_TO = "Contract End Date";

    /** Min Price */
    public static final String MSG_PARAM_MIN_PRC = "Min Price";

    /** Max Price */
    public static final String MSG_PARAM_MAX_PRC = "Max Price";

    /** Model Name */
    public static final String MSG_PARAM_MDL_NM = "Model Nm";

    /** Max Price */
    public static final String MSG_PARAM_RDD_DT = "Requested Delivery Date";

}
