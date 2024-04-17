/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/22   Fujitsu         T.Murai       Create          N/A
 * 2016/09/08   Fujitsu         T.Murai       Update          S21_NA#13918,13919
 * 2016/09/20   Fujitsu         T.Murai       Update          S21_NA#14578
 * 2016/10/07   Fujitsu         T.Murai       Update          S21_NA#11883
 * 2017/05/21   Hitachi         T.Tomita      Update          S21_NA:RS#8385
 * 2018/01/12   Fujitsu         K.Ishizuka    Update          S21_NA#20164
 * 2018/02/26   Fujitsu         Hd.Sugawara   Update          QC#22967
 * 2018/06/01   Fujitsu         M.Ohno        Update          S21_NA#26273
 * 2018/06/26   Fujitsu         H.Nagashima   Update          S21_NA#23726
 * 2018/07/27   Fujitsu         H.Kumagai     Update          QC#14307
 * 2019/12/07   Fujitsu         M.Ohno        Update          QC#54670
 * 2020/04/24   CITS            K.Ogino       Update          QC#56638
 * 2022/08/18   Hitachi         H.Watanabe    Update          QC#60255
 * 2023/04/28   CITS            R.Kurahashi   Update          QC#61281
 * 2023/10/10   Hitachi         T.Fukuta      Update          CSA-QC#61730
 * </pre>
 */
public class NWAL1840MsgConstant {

    // ----------- Message ID -----------
    /** The merchandise you specified cannot be sold. */
    public static final String NWAM0037E = "NWAM0037E";

    /** The entered "Sales Rep Code" does not exist in the Master. */
    public static final String NWAM0054E = "NWAM0054E";

    /** Cannot add anymore details. */
    public static final String NWAM0100E = "NWAM0100E";

    /** The entered @ does not exist in Master. */
    public static final String NWAM0181E = "NWAM0181E";

    /** An error has occurred in the called API [@]. */
    public static final String NWAM0189E = "NWAM0189E";

    /** This data has been updated by another user. */
    public static final String NWAM0429E = "NWAM0429E";

    /** Combination of [@] is incorrect. */
    public static final String NWAM0545E = "NWAM0545E";

    /** Please execute the @ button. */
    public static final String NWAM0673E = "NWAM0673E";

    /** Multiple Details cannot be processed at the same time. */
    public static final String NWAM0683E = "NWAM0683E";

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** Detail rows exceeds the maximum lines. No more rows can be added. */
    public static final String NWAM0659E = "NWAM0659E";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /**  */
    public static final String NWAM0667E = "NWAM0667E";

    /** [@] should be greater than [@]. */
    public static final String NWAM0712E = "NWAM0712E";

    /** Sales Rep is not assigned to the specified Ship-To customer. */
    public static final String NWAM0757W = "NWAM0757W";

    /** For "@", please enter a date earlier than "@". */
    public static final String NWAM0765E = "NWAM0765E";

    /** Net Price is out of range.[@ = @] */
    public static final String NWAM0775E = "NWAM0775E";

    /** @ is duplicated. If ok, please click @ button again. */
    public static final String NWAM0793W = "NWAM0793W";

    /** This quantity can not be entered, When less than the total quantity cooperated with Order. */
    public static final String NWAM0797E = "NWAM0797E";

    /** This [@] has already been sent to Order. You cannot be canceled. */
    public static final String NWAM0801E = "NWAM0801E";

    /** Entered Merchandise is not in the components of the input Model. Please enter the Model of components. */
    public static final String NWAM0813E = "NWAM0813E";

    /** The entered "Order Qty" is less than the "Minimum Order Qty". */
    public static final String NWAM0814E = "NWAM0814E";

    /** The entered "Order Qty" exceeds the "Maximum Order Qty". */
    public static final String NWAM0815E = "NWAM0815E";

    /** The entered "Order Qty" is different from the "Unit of Order". */
    public static final String NWAM0816E = "NWAM0816E";

    /** The relationship between 'Merchandise Code' and 'Substitute Item Code' is incorrect. */
    public static final String NWAM0817E = "NWAM0817E";

    /** The relationship between 'Bill To Customer Location Code' and 'Bill to customer Account Code' is incorrect. */
    public static final String NWAM0818E = "NWAM0818E";

    /** The relationship between 'Ship To Customer Code' and 'Ship to Customer Account Code' is incorrect. */
    public static final String NWAM0819E = "NWAM0819E";

    /** The relationship between 'Sold to customer Location Code' and 'Sell To Customer Account Code' is incorrect. */
    public static final String NWAM0820E = "NWAM0820E";

    /** The relationship between 'Bill to', 'Ship to' and 'Sold to' is incorrect. */
    public static final String NWAM0821E = "NWAM0821E";

    /** If Freight Condition Code is 'Collect', Carrier Account Number must be entered. */
    public static final String NWAM0822E = "NWAM0822E";

    /** The relationship between 'Freight Condition Code', 'LOB', 'Carrier Service Level Code' and 'Add Shipping Service Level Code' is incorrect. */
    public static final String NWAM0823E = "NWAM0823E";

    /** The sum of the ord Qty exceeds the Qty Allowed in line. Please cancel Schedule line or reduce the ord Qty. */
    public static final String NWAM0824E = "NWAM0824E";

    /** Line having Requested Delivery Date of the future than "Valid To" will be canceled. If ok, please click @ button again. */
    public static final String NWAM0825W = "NWAM0825W";

    /** The number of Schedule Line rows reached to the maximum.  No more Details can be registered. */
    public static final String NWAM0831E = "NWAM0831E";

    /** Set Item Merchandise Code cannot be entered. */
    public static final String NWAM0839E = "NWAM0839E";

    /** If Freight Condition Code is 'Collect', Carrier Service Level must be entered. */
    public static final String NWAM0882E = "NWAM0882E";

    /** Contract # is required. */
    public static final String NWAM0901E = "NWAM0901E"; // Add 2016/10/06 S21_NA#11883

    // QC#13918,13919 2016/09/07 Add Start
    /** NWZM1530E : It failed to get the EASY_PACK type code. */
    public static final String NWZM1530E = "NWZM1530E";

    /** NWZM2008E :The customer doesn't have an active Easy Pac I contract. */
    public static final String NWZM2008E = "NWZM2008E";
    // QC#13918,13919 2016/09/07 Add End

    // QC#14578 2016/09/20 Add Start 
    /** "Sales Rep Code" has not been entered. */
    public static final String NWAM0053E = "NWAM0053E";

    /** "Sales Rep Role Type Code" is required. */
    public static final String NWZM1338E = "NWZM1338E";

    /** Sales Representative Credit Percentage is required. */
    public static final String NWZM1344E = "NWZM1344E";

    /** Sales Credit Quota Flag is required. */
    public static final String NWZM1345E = "NWZM1345E";

    /** Credit Percentage is invalid. */
    public static final String NWZM1388E = "NWZM1388E";

    /** Total of credit percentage must be 100%. */
    public static final String NWZM1387E = "NWZM1387E";

    /** Sales rep does not belong to valid Sales Organization Hierarchy.  */
    public static final String NWZM1463E = "NWZM1463E";

    /** "Quota Revenue Sales Rep" is required */
    public static final String NWZM2014E = "NWZM2014E";
    // QC#14578 2016/09/20 Add End 

    /** The status of [@] is invalid. */
    public static final String NWAM0936E = "NWAM0936E";

    // Del Start 2018/02/26 QC#22967
    ///** The relationship between 'Location' and 'Sold to' is incorrect. */
    //public static final String NWZM1455E = "NWZM1455E";
    // Del End 2018/02/26 QC#22967
    
    /** Attention is Required. */ // 2018/01/12 S21_NA#20164 ADD
    public static final String NWZM2251E = "NWZM2251E";

    // Add Start 2018/02/26 QC#22967
    /** NWZM2254E : The relationship between 'Ship To' and 'Sold To' is incorrect. */
    public static final String NWZM2254E = "NWZM2254E";

    /** NWZM2255E : The relationship between 'Sold To' and 'Bill To' is incorrect. */
    public static final String NWZM2255E = "NWZM2255E";
    // Add End 2018/02/26 QC#22967

    // 2018/06/01 S21_NA#26273 add start
    /** Order qty should be equal to minimum qty or more.(Mimimum Qty=[@]) */
    public static final String NWZM2023E = "NWZM2023E";

    /** Order qty should be equal to maximum qty or less.(Maximum Qty=[@]) */
    public static final String NWZM2024E = "NWZM2024E";

    /** Order qty should be multiple of increment qty.(Increment Qty=[@]) */
    public static final String NWZM2025E = "NWZM2025E";
    // 2018/06/01 S21_NA#26273 add end
    // QC#23726 2018/06/26 add Start
    /** The relationship between 'Ship To Account' and 'Carrier Service Level' is incorrect. */
    public static final String NWZM2267E = "NWZM2267E";
    // QC#23726 2018/06/26 add End

    // QC#14307 2018/07/26 add Start
    /** Customer special instruction is not registered */
    public static final String NWZM2274W = "NWZM2274W";
    // QC#14307 2018/07/26 add End

    // 2019/12/07 QC#54670 Add Start
    /** The SalesRep you selected does not belong to IS Territory Group. */
    public static final String NWAM0973E = "NWAM0973E";
    // 2019/12/07 QC#54670 Add End

    // ADD START 2022/08/19 H.Watanabe [QC#60255]
    /** The format of [@] is incorrect. */
    public static final String NWAM8465E = "NWAM8465E";
    // ADD END   2022/08/19 H.Watanabe [QC#60255]

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

    /** Save */
    public static final String MSG_PARAM_SAVE = "Save";

    /** Submit */
    public static final String MSG_PARAM_SUBMIT = "Submit";

    // 2020/04/27 QC#56638 Add Start
    /** Sales Rep is not assigned to the specified Sold-To customer. */
    public static final String NWAM0981W = "NWAM0981W";
    // 2020/04/27 QC#56638 Add End
    
    // QC#61281 Add Start
    /** Credit Card can not be selected. Please select another 'Payment Term' or review account settings. */
    public static final String NWZM2321E = "NWZM2321E";
    // QC#61281 Add End

    // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
    /** Please enter a note to update scheduling agreement. */
    public static final String NWAM8475E = "NWAM8475E";

    /** (Warning) Another scheduling agreement @ exists for s/n @, are you sure to activate a new scheduling agreement ? */
    public static final String NWAM8476W = "NWAM8476W";

    /** (Error) Another scheduling agreement @ exists for s/n @. */
    public static final String NWAM8477E = "NWAM8477E";
    // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
}
