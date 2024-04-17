/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Fujitsu         T.Yoshida       Create          N/A
 * 2016/09/06   Fujitsu         H.Ikeda         Update          N/A
 * 2016/09/21   Fujitsu         H.Ikeda         Update          S21_NA#14578
 * 2017/08/07   Fujitsu         Y.Kanefusa      Update          S21_NA#10347
 * 2017/10/26   Fujitsu         R.Nakamura      Update          S21_NA#20304
 * 2018/02/08   Fujitsu         S.Ohki          Update          S21_NA#20173
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 2019/10/03   Fujitsu         A.Kazuki        Update          QC#53595
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 * 2022/08/18   Hitachi         H.Watanabe      Update          QC#60255
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 * </pre>
 */
public class NWAL1770MsgConstant {

    // ----------- Message ID -----------
    /** The merchandise you specified cannot be sold. */
    public static final String NWAM0037E = "NWAM0037E";

    /** Cannot add anymore details. */
    public static final String NWAM0100E = "NWAM0100E";

    /** The entered @ does not exist in Master. */
    public static final String NWAM0181E = "NWAM0181E";

    /** An error has occurred in the called API [@]. */
    public static final String NWAM0189E = "NWAM0189E";

    /** It failed to register Mail. */
    public static final String NWAM0268E = "NWAM0268E";

    /** In the table [@], there is no data corresponding to [@]. */
    public static final String NWAM0311E = "NWAM0311E";

    /** This data has been updated by another user. */
    public static final String NWAM0429E = "NWAM0429E";

    /** Please execute the @ button. */
    public static final String NWAM0673E = "NWAM0673E";

    /** Sales order will be created. If ok, please click submit button again. */
    public static final String NWAM0753W = "NWAM0753W";

    /** Sales Rep is not assigned to the specified Ship-To customer. */
    public static final String NWAM0757W = "NWAM0757W";

    /** Net Price is out of range.[@ = @] */
    public static final String NWAM0775E = "NWAM0775E";

    /** It failed to get Report File. */
    public static final String NWAM0788E = "NWAM0788E";

    /** @ is duplicated. If ok, please click @ button again. */
    public static final String NWAM0793W = "NWAM0793W";

    /** Payment Terms can not be derived. Bill To Number [@], Bill To Location [@]. */
    public static final String NWAM0796E = "NWAM0796E";

    // Add Start 2018/02/26 QC#22967
    /** NWZM2254E : The relationship between 'Ship To' and 'Sold To' is incorrect. */
    public static final String NWZM2254E = "NWZM2254E";

    /** NWZM2255E : The relationship between 'Sold To' and 'Bill To' is incorrect. */
    public static final String NWZM2255E = "NWZM2255E";
    // Add End 2018/02/26 QC#22967

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    // ADD START 2022/08/19 H.Watanabe [QC#60255]
    /** The format of [@] is incorrect. */
    public static final String NWAM8465E = "NWAM8465E";
    // ADD END   2022/08/19 H.Watanabe [QC#60255]

    // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    /** SN @ HAS TONER CONTRACT @, Are you sure you want to proceed with a standard order ? */
    public static final String NWAM8471W = "NWAM8471W";
    /** The items included in the supply contract are specified. Are you sure you want to proceed with a standard order ? */
    public static final String NWAM8472W = "NWAM8472W";
    /** This is HazMat item. */
    public static final String NWAM8473W = "NWAM8473W";
    // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

    // ----------- Message Parameter -----------
    /** Category */
    public static final String MSG_PARAM_CATG = "Category";

    /** Reason Code */
    public static final String MSG_PARAM_RSN_CD = "Reason Code";

    /** Source Name */
    public static final String MSG_PARAM_SRC_NM = "Source Name";

    /** Quote Date */
    public static final String MSG_PARAM_QUOTE_DT = "Quote Date";

    /** Days Valid */
    public static final String MSG_PARAM_DAYS_VLD = "Days Valid";

    /** Customer PO Number */
    public static final String MSG_PARAM_CUST_PO_NUM = "Customer PO Number";

    // S21_NA#7861 Mod Start
    // /** Sales Rep Code */
    // public static final String MSG_PARAM_SLS_REP_CD = "Sales Rep Code";
    /** Sales Rep Number */
    public static final String MSG_PARAM_SLS_REP_NUM = "Sales Rep Number";
    // S21_NA#7861 Mod End

    /** Sales Rep Name */
    public static final String MSG_PARAM_SLS_REP_NM = "Sales Rep Name";

    /** Price List */
    public static final String MSG_PARAM_PRC_LIST = "Price List";

    // QC#10347 2017/07/24 Add Start
    /** Pricing Date */
    public static final String MSG_PARAM_PRC_BASE_DT = "Pricing Date";
    // QC#10347 2017/07/24 Add End

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

    /** Shipping Service Level */
    public static final String MSG_PARAM_SHPG_SVC_LVL = "Service Level";

    /** Carrier Service Level */
    public static final String MSG_PARAM_CARR_SVC_LVL = "Carrier Service Level";

    /** Payment Terms */
    public static final String MSG_PARAM_PMT_TERMS = "Payment Terms";

    /** Item # */
    public static final String MSG_PARAM_ITEM = "Item#";

    /** Qty */
    public static final String MSG_PARAM_QTY = "Qty";

    /** UOM */
    public static final String MSG_PARAM_UOM = "UOM";

    /** Unit Sell Price */
    public static final String MSG_PARAM_SELL_PRC = "Sell Price";

    /** Line Category */
    public static final String MSG_PARAM_LINE_CATG = "Line Category";

    /** Line Source */
    public static final String MSG_PARAM_LINE_SRC = "Line Source";

    /** Warehouse */
    public static final String MSG_PARAM_WH = "Warehouse";

    /** Sub Warehouse */
    public static final String MSG_PARAM_SUB_WH = "Sub Warehouse";

    /** Button Name (Price $) */
    public static final String MSG_PARAM_BTN_PRC = "Price $";

    /** API Name (NWZC1920 Supersede API) */
    public static final String MSG_PARAM_SUPER_SEDE_API = "NWZC1920 Supersede API"; // 2018/02/06 S21_NA#20173 Mod

    /** Min Price */
    public static final String MSG_PARAM_MIN_PRC = "Min Price";

    /** Max Price */
    public static final String MSG_PARAM_MAX_PRC = "Max Price";

    /** Save */
    public static final String MSG_PARAM_SAVE = "Save";

    /** Submit */
    public static final String MSG_PARAM_SUBMIT = "Submit";

    // 2016/09/06 S21_NA#13917 Add Start
    /** Reference 1 */
    public static final String REFERENCE_1 = "Reference 1 Value";

    /** Reference 2 */
    public static final String REFERENCE_2 = "Reference 2 Value";

    /** Reference 3 */
    public static final String REFERENCE_3 = "Reference 3 Value";

    /** Reference 4 */
    public static final String REFERENCE_4 = "Reference 4 Value";

    /** Reference 5 */
    public static final String REFERENCE_5 = "Reference 5 Value";

    /** Reference 6 */
    public static final String REFERENCE_6 = "Reference 6 Value";
    // 2016/08/03 S21_NA#13917 Add End

    // QC#14578 2016/09/21 Add Start 
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

    /** "Quota Revenue Sales Rep" is required */
    public static final String NWZM2014E = "NWZM2014E";
    // QC#14578 2016/09/21 Add End 
    
    // Add Start 2016/09/29 S21_NA#13921
    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** Order qty should be equal to minimum qty or more.(Mimimum Qty=[@]) */
    public static final String NWZM2023E = "NWZM2023E";

    /** Order qty should be equal to maximum qty or less.(Maximum Qty=[@]) */
    public static final String NWZM2024E = "NWZM2024E";

    /** Order qty should be multiple of increment qty.(Increment Qty=[@]) */
    public static final String NWZM2025E = "NWZM2025E";
    // Add End 2016/09/29 S21_NA#13921

    // Add Start 2017/10/26 QC#20304
    /** It failed to get the EASY_PACK type code. */
    public static final String NWZM1530E = "NWZM1530E";

    /** The customer doesn't have an active Easy Pac I contract. */
    public static final String NWZM2008E = "NWZM2008E";
    // Add End 2017/10/26 QC#20304

    /** Default warehouse is not found by customer and product */
    public static final String NWZM0900W = "NWZM0900W";
    // Add Start 2019/10/03 QC#53595
    /** The SalesRep you selected does not belong to IS Territory Group. */
    public static final String NWAM0973E = "NWAM0973E";
    // Add End 2019/10/03 QC#53595

    // 2020/04/27 QC#56638 Add Start
    /** Sales Rep is not assigned to the specified Sold-To customer. */
    public static final String NWAM0981W = "NWAM0981W";
    // 2020/04/27 QC#56638 Add End

    // 2023/11/06 QC#61924 Add Start
    /** Bill To# {@} is deactivated for new transactions. */
    public static final String NWAM0987E = "NWAM0987E";

    /** Ship To# {@} is deactivated for new transactions. */
    public static final String NWAM0988E = "NWAM0988E";

    /** Sold To# {@} is deactivated for new transactions. */
    public static final String NWAM0989E = "NWAM0989E";
    // 2023/11/06 QC#61924 Add End
}
