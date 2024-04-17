/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7010.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NMAL7010Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/02   SRAA            Y.Chen          Update          QC#2175
 * 2016/02/09   SRA             E.Inada         Update          QC#2174
 * 2016/05/23   SRA             E.Inada         Update          QC#6315 Download columns
 * 2016/08/26   Fujitsu         R.Nakamura      Update          QC#3139
 * 2016/09/29   Hitachi         T.Mizuki        Update          QC#13270
 * 2017/02/13   Fujitsu         R.Nakamura      Update          QC#17503
 * 2017/11/13   Fujitsu         A.Kosai         Update          QC#20203(Sol#257)
 * 2018/05/08   Fujitsu         T.Noguchi       Update          QC#20269
 * 2018/07/17   Fujitsu         H.Kumagai       Update          QC#20267
 * 2018/08/22   Fujitsu         T.Noguchi       Update          QC#26664
 *</pre>
 */
public class NMAL7010Constant {

    /** Business Application ID */
    public static final String BIZ_ID = "NMAL7010";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NMAL7010Scrn00";

    /** String COMMA */
    public static final String COMMA = ",";

    // Add Start 2016/08/26 QC#3934
    /** String COMMA */
    public static final int MAX_ROW_EQUIPMENT = 2;
    // Add End 2016/08/26 QC#3934

    /** String Date High Value */
    public static final String HIGH_VAL_DT = "99991231";

    /** String Date High Value */
    public static final String HIGH_VAL_TM = "999999999";

    /** Status */
    public static final String STS_INACTIVE = "Inactive";

    /** Status */
    public static final String STS_ACTIVE = "Active";

    /** Status */
    public static final String STS_DELETED = "Deleted";

    /** BigDecimal MAX_SORT_NUM:999 */
    public static final BigDecimal MAX_SORT_NUM = new BigDecimal("999");

    /** Num Const Key : NMAL7010_MAX_FETCH_SIZE */
    public static final String NMAL7010_MAX_FETCH_SIZE = "NMAL7010_MAX_FETCH_SIZE";

    /** Num Const Key : NMAL7010_PER_PAGE_SIZE */
    public static final String NMAL7010_PER_PAGE_SIZE = "NMAL7010_PER_PAGE_SIZE";

    /** Var Char Const Key : NMAL7010_PAGE_AVAL_FLG */
    public static final String NMAL7010_PAGE_AVAL_FLG = "NMAL7010_PAGE_AVAL_FLG";

    /** Var Char Const Key : NMAL7010_DEF_TERM_UOM */
    public static final String NMAL7010_DEF_TERM_UOM = "NMAL7010_DEF_TERM_UOM";

    /** ITEM_TP_CTX_TP_CD for Service Item */
    public static final String ITEM_TP_CTX_TP_CD = "PRC_BOOK_MDSE_QLFY_ITEM_TP";

    /** Cache key */
    public static final String KEY_SVC_ITEM_TP_CACHE = "KEY_SVC_ITEM_TP_CACHE";

    /** Check Box PA */
    public static final String CHK_PA = "xxChkBox_PA";

    /** Check Box PB */
    public static final String CHK_PB = "xxChkBox_PB";

    /** Check Box PC */
    public static final String CHK_PC = "xxChkBox_PC";

    /** Check Box PD */
    public static final String CHK_PD = "xxChkBox_PD";

    /** Check Box PE */
    public static final String CHK_PE = "xxChkBox_PE";

    /** Check Box PF */
    public static final String CHK_PF = "xxChkBox_PF";

    /** Check Box PG */
    public static final String CHK_PG = "xxChkBox_PG";

    /** Check Box PH */
    public static final String CHK_PH = "xxChkBox_PH";

    /** Check Box PI */
    public static final String CHK_PI = "xxChkBox_PI";

    /** Check Box PJ */
    public static final String CHK_PJ = "xxChkBox_PJ";

    // QC#2175
    /** Check Box SW */
    public static final String CHK_SW = "xxChkBox_SW";
    
    /** Check Box CX */
    public static final String CHK_CX = "xxChkBox_CX";

    /** Check Box TY */
    public static final String CHK_TY = "xxChkBox_TY";

    /** Price List ID Extension Key */
    public static final String EXTN_KEY = "PRC_CTG_CD";

    /** Default Gross Price Percent */
    public static final BigDecimal DEF_GRS_PRC_PCT = new BigDecimal(15.0);

    /** TAB : CustAudc */
    public static final String TAB_PRC_LIST_CUST_AUDC = "CustAudc";

    /** TAB : TrxAudc */
    public static final String TAB_PRC_LIST_TRX_AUDC = "TrxAudc";

    /** TAB : PrcListValEquip (A) */
    public static final String TAB_PRC_LIST_VAL_EQUIP = "PrcListValEquip";

    /** TAB : PrcListValService (B) */
    public static final String TAB_PRC_LIST_VAL_SVC = "PrcListValService";

    /** TAB : PrcListValServiceTiers (C) */
    public static final String TAB_PRC_LIST_VAL_SVC_TIER = "PrcListValServiceTiers";

    /** TAB : PrcListValServiceSpecialCharges (D) */
    public static final String TAB_PRC_LIST_VAL_SVC_SPEC_CHRG = "PrcListValServiceSpecialCharges";

    /** TAB : PrcListValSupplyProgram (E) */
    public static final String TAB_PRC_LIST_VAL_SPLY_PGM = "PrcListValSupplyProgram";

    /** TAB : PrcListValLeaseRate (F) */
    public static final String TAB_PRC_LIST_VAL_LEASE_RATE = "PrcListValLeaseRate";

    /** TAB : PrcListValLeaseReturnFees (G) */
    public static final String TAB_PRC_LIST_VAL_LEASE_RTRN_FEE = "PrcListValLeaseReturnFees";

    /** TAB : PrcListValTradeIn (H) */
    public static final String TAB_PRC_LIST_VAL_TI = "PrcListValTradeIn";

    /** TAB : PrcListValQtyDiscount (I) */
    public static final String TAB_PRC_LIST_VAL_QTY_DISC = "PrcListValQtyDiscount";

    /** TAB : TrxDrvEquipment (A-2) */
    public static final String TAB_TRX_DRV_EQUIP = "TrxDrvEquipment";

    /** TAB : TrxDrvService (B-2) */
    public static final String TAB_TRX_DRV_SVC = "TrxDrvService";

    /** TAB : TrxDrvServiceTiers (C-2) */
    public static final String TAB_TRX_DRV_SVC_TIER = "TrxDrvServiceTiers";

    /** Table A. */
    public static final String TBL_A = "A";

    /** Table B. */
    public static final String TBL_B = "B";

    /** Table C. */
    public static final String TBL_C = "C";

    /** Table D. */
    public static final String TBL_D = "D";

    /** Table E. */
    public static final String TBL_E = "E";

    /** Table F. */
    public static final String TBL_F = "F";

    /** Table G. */
    public static final String TBL_G = "G";

    /** Table H. */
    public static final String TBL_H = "H";

    /** Table I. */
    public static final String TBL_I = "I";

    // Oracle SQ
    public static final String SQ_PRC_LIST_EQUIP_DTL_SQ = "PRC_LIST_EQUIP_DTL_SQ";

    /** CSV File Name */
    public static final String CSV_FILE_NAME = "NMAL7010_PriceListSetup";

    // 2018/05/08 QC#20269 Add Start
    /** AsTemplate CSV File Name */
    public static final String ASTEMPLATE_CSV_FILE_NAME = "NMAL7010_PriceListSetup_asTemplate";

    /** Upload CSV Id. Price List Equipment Work */
    public static final String UPLD_CSV_ID_EQUIP_WRK = "NMA7010001";

    /** Upload CSV Id. Price List Service Work */
    public static final String UPLD_CSV_ID_SVC_WRK = "NMA7010002";

    /** Upload CSV Id. Price List Service Tier Work */
    public static final String UPLD_CSV_ID_SVC_TIER_WRK = "NMA7010003";

    /** Upload CSV Id. Price List Additional Charge Work */
    public static final String UPLD_CSV_ID_ADDL_CHRG_WRK = "NMA7010004";

    /** Upload CSV Id. Price List Supply Program Work */
    public static final String UPLD_CSV_ID_SPLY_PGM_WRK = "NMA7010005";

    /** Upload CSV Id. Price List Lease Rate Work */
    public static final String UPLD_CSV_ID_LEASE_RATE_WRK = "NMA7010006";

    /** Upload CSV Id. Price List Lease Return Work */
    public static final String UPLD_CSV_ID_LEASE_RTRN_WRK = "NMA7010007";

    /** Upload CSV Id. Price List Trade In Work */
    public static final String UPLD_CSV_ID_TI_VAL_WRK = "NMA7010008";
    // 2018/05/08 QC#20269 Add End

    /** Download Fetch Size */
    public static final int DOWNLOAD_FETCH_SIZE = 1000;

    /** convert format : 0.regex, 1.replacement */
    public static final String[] DT_CONV_FORMAT = {"^(....)(..)(..)$", "$2/$3/$1" };

    //--------------------------------
    // Qty Discount Update Mode
    //--------------------------------
    public static final String MODE_MODIFY = "M";

    public static final String MODE_NEW = "N";

    //--------------------------------
    // Message ID
    //--------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** The value for [@] must be smaller than [@]. */
    public static final String NMAM0043E = "NMAM0043E";

    /** Details cannot be added because the number will exceed [@]. */
    public static final String NMAM0050E = "NMAM0050E";

    /** @  is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    // Del Start 2017/02/13 QCE17503
//    /** [@] cannot be deleted because of [@]. */
//    public static final String NMAM0074E = "NMAM0074E";
    // Del End 2017/02/13 QCE17503

    /** The entered [@] does not exist in [@].*/
    public static final String NMAM0163E = "NMAM0163E";

    /** [@] already exists in [@] */
    public static final String NMAM0834E = "NMAM0834E";

    /** The process ended abnormally. */
    public static final String NMAM8020E = "NMAM8020E";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /** Error has occurred while uploading. Please verify the upload file format. (@) */
    public static final String NMAM8191E = "NMAM8191E";

    /** The Upload File is empty. Please confirm the content of the Upload file. */
    public static final String NMAM8193E = "NMAM8193E";

    /** There are too many search results, there is data that cannot be uploaded. */
    public static final String NMAM8197E = "NMAM8197E";

    /** Since the circular reference occurs, it can not be registered. */
    public static final String NMAM8212E = "NMAM8212E";

    /** Effective date is out of range. Please check the Effective date. */
    public static final String NMAM8213E = "NMAM8213E";

    /** @ must have at least one row.  Please enter data. */
    public static final String NMAM8214E = "NMAM8214E";

    /** Cannot enter the other audience in case of Public. */
    public static final String NMAM8215E = "NMAM8215E";

    /** Please entry 8 digits merchandise code. */
    public static final String NMAM8216E = "NMAM8216E";

    /** Please enter Price or Formula. */
    public static final String NMAM8217E = "NMAM8217E";

    /** In case of Qualify Type = Item Code, Formula Type should be 01 : Price List or 03 : Standard Cost. */
    public static final String NMAM8218E = "NMAM8218E";

    /** In case of Qualify Type = Merchandise Type or Product Hierarchy 1-5, Formula Type should be 01 : Price List. */
    public static final String NMAM8219E = "NMAM8219E";

    /** In case of Qualify Type = Item Code, Linked equipment price list has price amount on same Item Code. */
    public static final String NMAM8220E = "NMAM8220E";

    /** Cannot calculate. Please confirm formula setup. */
    public static final String NMAM8224E = "NMAM8224E";

    /** This Price List Line is used as Source Price List on [@]. */
    public static final String NMAM8226E = "NMAM8226E";

    /** The each price amount has decimal fraction. */
    public static final String NMAM8232E = "NMAM8232E";

    /** [@] is not different price list structure. */
    public static final String NMAM8248E = "NMAM8248E";

    /** Price List that you enter, you have circulation. */
    public static final String NMAM8249E = "NMAM8249E";

    /** The selected UOM is not allowed for the item. */
    public static final String NMAM8510E = "NMAM8510E";

    /**
     * Term, Term UOM and Monthly Payment Amount must be entered
     * together, or all of them must be blank.
     */
    public static final String NMAM8364E = "NMAM8364E";

    /** [@] cannot be set because of [@]. */
    public static final String NMAM0179E = "NMAM0179E";

    // mod start 2016/09/29 CSA QC#13270
    /** Please enter a number equal to or greater than 0 in [@]. */
    public static final String NMAM0847E = "NMAM0847E";
    // mod end 2016/09/29 CSA QC#13270

    // 2017/02/24 QC#17501 add Start
    /** It failed to register @ Table,[@] */
    public static final String NMAM8571E = "NMAM8571E";

    /** It failed to update @ Table,[@] */
    public static final String NMAM8664E = "NMAM8664E";

    /** It failed to delete @ Table,[@] */
    public static final String NMAM8665E = "NMAM8665E";
    // 2017/02/24 QC#17501 add End

    // 2017/11/13 QC#20203(Sol#257) Add Start
    /** Detail requires at least one line.  Please enter. */
    public static final String NMAM8190E = "NMAM8190E";
    // 2017/11/13 QC#20203(Sol#257) Add End

    /** CSV Upload Column : Equipment */
    public static final String[] CSV_UPLOAD_COLUMN_EQUIP = new String[] {
            "Config#"
            , "Config Name"
            , "Qualifier Type Name"
            , "Value"
            , "UOM"
            , "Price"
            , "Min Price"
            , "Max Price"
            , "Term"
            , "Term UOM"
            , "Bid Qty"
            , "Montyly Payment Amount"
            , "Lease Payment Min"
            , "Lease Payment Max"
            , "Price Formula"
            , "Open Mark(Not on Context)"
            , "Quota Rev"
            , "Comp Code"
            , "Date From"
            , "Date To"
    };

    /** CSV Upload Column : Service */
    public static final String[] CSV_UPLOAD_COLUMN_SVC = new String[] {
            "Rate Type"
            , "Service Model Name"
            , "Meter Package Name"
            , "Plan Type"
            , "Contract Type"
            , "Meter Type"
            , "Min Vol"
            , "Max Vol"
            , "Band"
            , "Base Amount"
            , "Min Base"
            , "Max Base"
            , "Cost Per Copy(OVERAGE)"
            , "Min CPC"
            , "Max CPC"
            , "Term From(MTH)"
            , "Term Thru(MTH)"
            , "Service Zone(s) Included"
            , "Multiplier"
            , "Service Item"
            , "Date From"
            , "Date To"
    };

    /** CSV Upload Column : Service Tier */
    public static final String[] CSV_UPLOAD_COLUMN_SVC_TIER = new String[] {
            "Model Name"
            , "Tier Type"
            , "Service Package Name"
            , "Plan Type"
            , "Service Program"
            , "Meter Type Name"
            , "Avg Copy Volume"
            , "Min Vol"
            , "Max Vol"
            , "Band"
            , "Base Amount"
            , "Min Base"
            , "Max Base"
            , "Cost Per Copy(OVERAGE)"
            , "Min CPC"
            , "Max CPC"
            , "Term From(MTH)"
            , "Term Thru(MTH)"
            , "Service Item"
            , "Date From"
            , "Date To"
            , "Multiplier"
    };

    /** CSV Upload Column : Service Special Charge */
    public static final String[] CSV_UPLOAD_COLUMN_SVC_SPEC_CHRG = new String[] {
            "Item Code"
            , "Charge Type"
            , "Item Segment"
            , "Service Model Name"
            , "Price"
            , "Date From"
            , "Date To"
    };

    /** CSV Upload Column : Supply Program */
    public static final String[] CSV_UPLOAD_COLUMN_SPLY_PGM = new String[] {
            "Service Model Name"
            , "Meter Package Name"
            , "Plan Type"
            , "Contract Type"
            , "Meter Type"
            , "Min Vol"
            , "Max Vol"
            , "Band"
            , "Total Base Amount"
            , "Supply Base Amount"
            , "Cost Per Copy(OVERAGE)"
            , "Min CPC"
            , "Max CPC"
            , "Term From(MTH)"
            , "Term Thru(MTH)"
            , "Multiplier"
            , "Service Item"
            , "Service Zone(s) Included"
            , "Supply Plan"
            , "Date From"
            , "Date To"
    };

    /** CSV Upload Column : Lease Rate */
    public static final String[] CSV_UPLOAD_COLUMN_LEASE_RATE = new String[] {
            "Lease Company Abbreviation"
            , "Lease Company"
            , "Program Name"
            , "Equipment Type"
            , "Service Model Name"
            , "Total Financed Min"
            , "Total Financed Max"
            , "Qualifiying Term From"
            , "Qualifiying Term To"
            , "Lease Rate"
            , "Date From"
            , "Date To"
    };

    /** CSV Upload Column : Lease Rate */
    public static final String[] CSV_UPLOAD_COLUMN_LEASE_RTRN_FEES = new String[] {
            "Return to Lease Company Abbreviation"
            , "Machine Segment"
            , "In or Out of Region"
            , "Distance(Miles)"
            , "Return Fee"
            , "Date From"
            , "Date To"
    };

    /** CSV Upload Column : Trade In */
    public static final String[] CSV_UPLOAD_COLUMN_TRADE_IN = new String[] {
              "Service Model Name"
            , "Trade In Value"
            , "Meter Range Required)"
            , "Meter From"
            , "Meter To"
            , "Date From"
            , "Date To"
    };

    /** CSV Upload Column : Qty Discount */
    public static final String[] CSV_UPLOAD_COLUMN_QTY_DISC = new String[] {
              "Service Model Name"
            , "Trade In Value"
            , "Meter Range Required)"
            , "Meter From"
            , "Meter To"
            , "Date From"
            , "Date To"
    };

    // --------- Download -----------
    /** CSV Download Column : Equipment */
    public static final String[] CSV_DOWNLOAD_COLUMN_EQUIP = new String[] { //
    "Price List ID" //
            , "Price List Name" //
            , "Price List Display Name" //
            , "Price List Type " //
            , "Active " //
            , "Header Date From" //
            , "Header Date To" //
            , "Customer Regist Req" //
            , "Sales Visibility " //
            , "Price List Group " //
            , "Related Contract#" //
            , "Related Contract Name" //
            , "Deleted " //
            , "Config#" //
            , "Config Name" //
            , "Qualifier Type Name" //
            , "Value" //
            // 2018/07/17 QC#20267 Add Start
            , "Manufacture#" //
            // 2018/07/17 QC#20267 Add End
            , "Description" //
            , "Mdse Type" //
            , "Item Type" //
            , "Prod Code" //
            , "Model" //
    };

    /** CSV Download Column : Equipment2 */
    public static final String[] CSV_DOWNLOAD_COLUMN_EQUIP_2 = new String[] { //
    "UOM" //
            , "Price" //
            , "Min Price" //
            , "Max Price" //
            , "Term" //
            , "Term UOM" //
            , "Bid Qty" //
            , "Monthly Payment Amount " //
            , "Lease Payment Min" //
            , "Lease Payment Max" //
            , "Price Formula" //
            , "Price Formula Description" //
            , "Calc Price" //
            , "Open Mark(Not on Context)" //
            , "Quota Rev" //
            , "Comp Code" //
            , "Date From" //
            , "Date To" //
            , "Status" //
            , "Created By" //
            , "Created Date" //
            , "Updated By" //
            , "Updated Date" //
            , "Qty Break " //
    };

    /** CSV Download Column : Service */
    public static final String[] CSV_DOWNLOAD_COLUMN_SVC = new String[] { //
    "Price List ID" //
            , "Price List Name" //
            , "Price List Display Name" //
            , "Price List Type " //
            , "Active " //
            , "Header Date From" //
            , "Header Date To" //
            , "Customer Regist Req" //
            , "Sales Visibility " //
            , "Price List Group " //
            , "Related Contract#" //
            , "Related Contract Name" //
            , "Deleted " //
            , "Rate Type" //
            , "Service Model Name" //
            , "Meter Package Name" //
            , "Item#" //
            , "Item Description" //
            , "Plan Type" //
            , "Contract Type" //
            , "Meter Type" //
            , "Min Vol" //
            , "Max Vol" //
            , "Band" //
            , "Base Amount" //
            , "Min Base" //
            , "Max Base" //
            , "Cost Per Copy(OVERAGE)" //
            , "Min CPC" //
            , "Max CPC" //
            , "Term From(MTH)" //
            , "Term Thru(MTH)" //
            , "Service Zone(s) Included" //
            , "Service Item" //
            , "Service Item Description " //
            , "Billing Counter Name " //
            , "Quota Rev" //
            , "Comp Code" //
            , "Date From" //
            , "Date To" //
            , "Status" //
            , "Created By" //
            , "Created Date" //
            , "Updated By" //
            , "Updated Date" //
    };

    /** CSV Download Column : Service Tier */
    public static final String[] CSV_DOWNLOAD_COLUMN_SVC_TIER = new String[] { //
    "Price List ID" //
            , "Price List Name" //
            , "Price List Display Name" //
            , "Price List Type " //
            , "Active " //
            , "Header Date From" //
            , "Header Date To" //
            , "Customer Regist Req" //
            , "Sales Visibility " //
            , "Price List Group " //
            , "Related Contract#" //
            , "Related Contract Name" //
            , "Deleted " //
            , "Model Name" //
            , "Tier Type" //
            , "Meter Package Name " //
            , "Plan Type" //
            , "Service Program" //
            , "Meter Type Name" //
            , "Avg Copy Volume" //
            , "Min Vol" //
            , "Max Vol" //
            , "Band" //
            , "Base Amount" //
            , "Min Base" //
            , "Max Base" //
            , "Cost Per Copy(OVERAGE)" //
            , "Min CPC" //
            , "Max CPC" //
            , "Term From(MTH)" //
            , "Term Thru(MTH)" //
            , "Service Item" //
            , "Service Item Description " //
            , "Billing Counter Name " //
            , "Date From" //
            , "Date To" //
            , "Status" //
            , "Created By" //
            , "Created Date" //
            , "Updated By" //
            , "Updated Date" //
    };

    /** CSV Download Column : Service Special Charge */
    public static final String[] CSV_DOWNLOAD_COLUMN_SVC_SPEC_CHRG = new String[] { //
    "Price List ID" //
            , "Price List Name" //
            , "Price List Display Name" //
            , "Price List Type " //
            , "Active " //
            , "Header Date From" //
            , "Header Date To" //
            , "Customer Regist Req" //
            , "Sales Visibility " //
            , "Price List Group " //
            , "Related Contract#" //
            , "Related Contract Name" //
            , "Deleted " //
            , "Item Code" //
            , "Service Item Description " //
            , "Charge Type" //
            , "Item Segment" //
            , "Service Model Name" //
            , "Price" //
            , "Date From" //
            , "Date To" //
            , "Status" //
            , "Created By" //
            , "Created Date" //
            , "Updated By" //
            , "Updated Date" //
    };

    /** CSV Download Column : Supply Program */
    public static final String[] CSV_DOWNLOAD_COLUMN_SPLY_PGM = new String[] { //
    "Price List ID" //
            , "Price List Name" //
            , "Price List Display Name" //
            , "Price List Type " //
            , "Active " //
            , "Header Date From" //
            , "Header Date To" //
            , "Customer Regist Req" //
            , "Sales Visibility " //
            , "Price List Group " //
            , "Related Contract#" //
            , "Related Contract Name" //
            , "Deleted " //
            , "Service Model Name" //
            , "Meter Package Name" //
            , "Plan Type" //
            , "Contract Type" //
            , "Meter Type" //
            , "Min Vol" //
            , "Max Vol" //
            , "Band" //
            , "Total Base Amount" //
            , "Supply Base Amount" //
            , "Cost Per Copy(OVERAGE)" //
            , "Min CPC" //
            , "Max CPC" //
            , "Term From(MTH)" //
            , "Term Thru(MTH)" //
            , "Service Item" //
            , "Service Item Description " //
            , "Billing Counter Name " //
            , "Service Zone(s) Included" //
            , "Supply Plan" //
            , "Supply Plan Description" //
            , "Date From" //
            , "Date To" //
            , "Status" //
            , "Created By" //
            , "Created Date" //
            , "Updated By" //
            , "Updated Date" //
    };

    /** CSV Download Column : Lease Rate */
    public static final String[] CSV_DOWNLOAD_COLUMN_LEASE_RATE = new String[] { //
    "Price List ID" //
            , "Price List Name" //
            , "Price List Display Name" //
            , "Price List Type " //
            , "Active " //
            , "Header Date From" //
            , "Header Date To" //
            , "Customer Regist Req" //
            , "Sales Visibility " //
            , "Price List Group " //
            , "Related Contract#" //
            , "Related Contract Name" //
            , "Deleted " //
            , "Lease Company Abbreviation" //
            , "Lease Company" //
            , "Program Name" //
            , "Equipment Type" //
            , "Service Model Name" //
            , "Total Financed Min" //
            , "Total Financed Max" //
            , "Qualifiying Term From" //
            , "Qualifiying Term To" //
            , "Lease Rate" //
            , "Date From" //
            , "Date To" //
            , "Status" //
            , "Created By" //
            , "Created Date" //
            , "Updated By" //
            , "Updated Date" //
    };

    /** CSV Download Column : Lease Rate */
    public static final String[] CSV_DOWNLOAD_COLUMN_LEASE_RTRN_FEES = new String[] { //
    "Price List ID" //
            , "Price List Name" //
            , "Price List Display Name" //
            , "Price List Type " //
            , "Active " //
            , "Header Date From" //
            , "Header Date To" //
            , "Customer Regist Req" //
            , "Sales Visibility " //
            , "Price List Group " //
            , "Related Contract#" //
            , "Related Contract Name" //
            , "Deleted " //
            , "Return to Lease Company Abbreviation" //
            , "Machine Segment" //
            , "In or Out of Region" //
            , "Distance(Miles)" //
            , "Return Fee" //
            , "Date From" //
            , "Date To" //
            , "Status" //
            , "Created By" //
            , "Created Date" //
            , "Updated By" //
            , "Updated Date" //
    };

    /** CSV Download Column : Trade In */
    public static final String[] CSV_DOWNLOAD_COLUMN_TRADE_IN = new String[] { //
    "Price List ID" //
            , "Price List Name" //
            , "Price List Display Name" //
            , "Price List Type " //
            , "Active " //
            , "Header Date From" //
            , "Header Date To" //
            , "Customer Regist Req" //
            , "Sales Visibility " //
            , "Price List Group " //
            , "Related Contract#" //
            , "Related Contract Name" //
            , "Deleted " //
            , "Service Model Name" //
            , "Trade In Value" //
            , "Meter Range Required)" //
            , "Meter From" //
            , "Meter To" //
            , "Date From" //
            , "Date To" //
            , "Status" //
            , "Created By" //
            , "Created Date" //
            , "Updated By" //
            , "Updated Date" //
    };
    /** Referenced Entity Item */
    public static String REF_ENTITY_ITEM = "ITEM";

    /** MDSE Code key Name */
    public static String MODE_CD_KEY_NAME = "MDSE_CD";

    // 2018/08/22 QC#26664 Add Start
    /** Rate Scale */
    public static int RATE_SCALE = 5;
    // 2018/08/22 QC#26664 Add End
}
