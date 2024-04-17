/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1500.constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/25   Fujitsu         M.Suzuki        Update          S21_NA#966
 * 2016/03/15   Fujitsu         S.Takami        Update          S21_NA#2096 Reopen
 * 2016/04/19   Fujitsu         S.Takami        Update          S21_NA#5394
 * 2016/06/09   Fujitsu         S.Yamamoto      Update          S21_NA#9277
 * 2016/06/27   Fujitsu         S.Takami        Update          S21_NA#10841
 * 2016/07/04   Fujitsu         S.Takami        Update          S21_NA#4691
 * 2016/08/23   Fujitsu         Y.Taoka         Update          S21_NA#8388
 * 2016/09/20   Fujitsu         S.Takami        Update          S21_NA#8279
 * 2016/10/05   Fujitsu         S.Takami        Update          S21_NA#7645-3
 * 2017/01/27   Fujitsu         R.Nakamura      Update          S21_NA#17257
 * 2017/03/01   Fujitsu         Y.Kanefusa      Update          S21_NA#17637
 * 2017/08/07   Fujitsu         T.Ogura         Update          Sol#249
 * 2017/08/09   Fujitsu         W.Honda         Update          S21_NA#20093
 * 2017/10/04   Fujitsu         S.Takami        Update          S21_NA#21300
 * 2017/10/31   Fujitsu         Y.Kanefusa      Update          S21_NA#22031
 * 2017/11/02   Fujitsu         T.Aoi           Update          S21_NA#20146(Sol#92)
 * 2017/11/24   Fujitsu         K.Ishizuka      Update          S21_NA#20019
 * 2017/12/05   Fujitsu         A.Kosai         Update          S21_NA#21702
 * 2017/12/08   Fujitsu         A.Kosai         Update          S21_NA#21621
 * 2018/03/05   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/12   Fujitsu         A.Kosai         Update          S21_NA#20297(Sol#379)
 * 2018/04/11   Fujitsu         Y.Kanefusa      Update          S21_NA#22965
 * 2018/05/11   Fujitsu         T.Aoi           Update          S21_NA#22139
 * 2018/05/17   Fujitsu         S.Takami        Update          S21_NA#22988
 * 2018/08/01   Fujitsu         M.Ohno          Upadte          S21_NA#26414
 * 2018/08/03   Fujitsu         K.Ishizuka      Update          S21_NA#27040
 * 2018/08/21   Fujitsu         S.Takami        Update          S21_NA#26767
 * 2018/08/24   Fujitsu         T.Noguchi       Update          S21_NA#27202
 * 2018/08/28   Fujitsu         H.Kumagai       Update          QC#26329
 * 2018/10/25   Fujitsu         T.Aoi           Update          S21_NA#28897
 * 2019/05/07   Fujitsu         R.Nakamura      Update          S21_NA#50015
 * 2019/05/24   Fujitsu         Mz.takahashi    Update          QC#50043
 * 2019/07/11   Fujitsu         T.Noguchi       Update          S21_NA#51287
 * 2019/07/23   Fujitsu         Mz.takahash     Update          QC#51252
 * 2019/11/01   Fujitsu         W.Honda         Update          S21_NA#54509
 * 2019/12/20   Fujitsu         A.Kazuki        Update          QC#53055
 * 2020/01/17   Fujitsu         A.Kazuki        Update          QC#55202
 * 2020/03/16   Fujitsu         C.Hara          Update          QC#56132
 * 2024/03/08   CITS            T.Miki          Update          QC#63654
 *</pre>
 */
public class NWAL1500Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1500";

    /** Tab anchor Header */
    public static final String TAB_HEADER = "Header";

    /** Tab anchor Additional Header */
    public static final String TAB_ADDL_HEADER = "Addl_Header";

    /** Tab anchor Line Config */
    public static final String TAB_LINE_CONFIG = "Line_Config";

    /** Tab anchor RMA */
    public static final String TAB_RMA = "RMA";

    /** No Selected Detail Line */
    public static final int NO_SLCT_DTL = -1;

    /** Period */
    public static final String PERIOD = ".";

    /** Comma */
    public static final String COMMA = ",";

    /** Blank */
    public static final String BLANK = "";

    /** Space */
    public static final String SPACE = " ";

    // 2018/05/11 S21_NA#22139 Add Start
    /** Under Line */
    public static final String UNDER_LINE = "_";
    // 2018/05/11 S21_NA#22139 Add End

    /** Enter */
    public static final String ENTER = "\r\n";

    /** 100 Percentage */
    public static final BigDecimal PCT_100 = new BigDecimal(100);

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** ORD_CATG_BIZ_CTX : EQUIPMENT_ORDER */
    public static final String EQUIPMENT_ORDER = "EQUIPMENT_ORDER";

    /** ORD_CATG_BIZ_CTX : SUPPLIES_ORDER */
    public static final String SUPPLIES_ORDER = "SUPPLIES_ORDER";

    /** Pricing Package UOM Varchar Const Name */
    public static final String PKG_UOM_FOR_PRC = "PKG_UOM_FOR_PRC";

    /** Salesrep Credit Percent Num Const Name */
    public static final String DEF_SLS_CR_PCT_WRITER = "DEF_SLS_CR_PCT_WRITER";     // QC#17637 2017/03/01 Add

    /** Request Type : New. */
    public static final String REQ_NEW = "1";

    /** Request Type : Modify. */
    public static final String REQ_MOD = "2";

    /** Request Type : Delete. */
    public static final String REQ_DEL = "3";

    /** Sub System ID */
    public static final String SUB_SYS_ID = "NWA";

    /** Process ID */
    public static final String PROCESS_ID = "OM";

    /** Document Type */
    public static final String DOCUMENT_TYPE = "OM";

    /** Default Sub WH Code (NEW) */
    public static final String SUB_WH_CD_NEW = "NEW";

    /** Event Id : Order Cancel */
    public static final String EVENT_ID_ORDER_CANCEL = "Order Cancel";

    // 2019/05/24 QC#50043 Add Start
    /** Event Id : Order Entry Action */
    public static final String EVENT_ID_ENTRY_ACTION = "OrderEntryAction";
    // 2019/05/24 QC#50043 Add End

    // 2017/05/08 S21_NA#Review structure Lv.2 Del Start
//    /** Event Id : Change Order Modification */
//    public static final String EVENT_ID_CHNG_ORD_MODIFY = "Change Order Modification";
    // 2017/05/08 S21_NA#Review structure Lv.2 Del End

    /** Order Category Context Type Code : CHANGE_ORDER_EXCLUSIONS */
    public static final String ORD_CATG_BIZ_CTX_CHANGE_ORDER_EXCLUSIONS = "CHANGE_ORDER_EXCLUSIONS";

    /** VARCHAR_CONST define: ORD_LINE_STS_TO_BE_CANCELLED */
    public static final String ORD_LINE_STS_TO_BE_CANCELLED = "ORD_LINE_STS_TO_BE_CANCELLED";

    /** VARCHAR_CONST define: NWAL1500_ORD_LINE_CANCELLED */
    public static final String NWAL1500_ORD_LINE_CANCELLED = "NWAL1500_ORD_LINE_CANCELLED";

    /** VARCHAR_CONST define: NOT_HARD_ALLOC_WH_CD */
    public static final String NOT_ALLOC_WH_CD = "NOT_HARD_ALLOC_WH_CD";

    /** VARCHAR_CONST define: NWAL1500_ALLOW_RDD_CHANGE_WH S21_NA#2096 add */
    public static final String NWAL1500_ALLOW_RDD_CHANGE_WH = "NWAL1500_ALLOW_RDD_CHANGE_WH";

    /** VARCHAR_CONST define: DROP_SHIP_RTL_WH_CD */
    public static final String DROP_SHIP_RTL_WH_CD = "DROP_SHIP_RTL_WH_CD";

    /** VARCHAR_CONST define: LEASE_BYOT_MDSE_CD */
    public static final String LEASE_BYOT_MDSE_CD = "LEASE_BYOT_MDSE_CD";

    // 2019/11/01 S21_NA#54509 Mod Start
    /** VARCHAR_CONST define: LEASE_BYOT_MDSE_CD */
    public static final String LOAN_DUMMY_WH_CD = "LOAN_DUMMY_WH_CD";
    // 2019/11/01 S21_NA#54509 Mod End

    // 2016/06/09 S21_NA#9277 Add Start
    /** VARCHAR_CONST define: NWAL1500_RENTAL_CONV_DEFWH */
    public static final String NWAL1500_RENTAL_CONV_DEFWH = "NWAL1500_RENTAL_CONV_DEFWH";

    // 2016/06/27 S21_NA#10841 Add 
    /** VARCHAR_CONST define: NWAL1500_RENTAL_CONV_DEFLINESRC */
    public static final String NWAL1500_RENTAL_CONV_DEFLNSRC = "NWAL1500_RENTAL_CONV_DEFLNSRC";

    // 2018/05/17 S21_NA#22988 Add Start
    /** VARCHAR_CONST define: NWAL1500_RENTAL_CONV_DEFLNCATG */
    public static final String NWAL1500_RENTAL_CONV_DEFLNCATG = "NWAL1500_RENTAL_CONV_DEFLNCATG";

    /** Dummy Warehouse Value set Context Value */
    public static final String FORCE_DUMMY_WH = "FORCE_DUMMY_WH";
    // 2018/05/17 S21_NA#22988 Add End

    // 2016/08/09 S21_NA#20093 Add Start
    /** VARCHAR_CONST define: DEF_LSE_BO_LOC_NM */
    public static final String DEF_LSE_BO_LOC_NM = "DEF_LSE_BO_LOC_NM";

    /** VARCHAR_CONST define: DEF_LSE_BO_LOC_ADDR */
    public static final String DEF_LSE_BO_LOC_ADDR = "DEF_LSE_BO_LOC_ADDR";

    /** VARCHAR_CONST define: DEF_LSE_BO_LOC_CITY_ADDR */
    public static final String DEF_LSE_BO_LOC_CITY_ADDR = "DEF_LSE_BO_LOC_CITY_ADDR";

    /** VARCHAR_CONST define: DEF_LSE_BO_LOC_ST_CD */
    public static final String DEF_LSE_BO_LOC_ST_CD = "DEF_LSE_BO_LOC_ST_CD";

    /** VARCHAR_CONST define: DEF_LSE_BO_LOC_POST_CD */
    public static final String DEF_LSE_BO_LOC_POST_CD = "DEF_LSE_BO_LOC_POST_CD";
    // 2016/08/09 S21_NA#20093 Add End

    // 2018/08/01 S21_NA#26414 add start
    /** VARCHAR_CONST define: SERVICE_EXCHANGE_RTRN_RSN_CD */
    public static final String SERVICE_EXCHANGE_RTRN_RSN_CD = "SERVICE_EXCHANGE_RTRN_RSN_CD";
    // 2018/08/01 S21_NA#26414 add end

    // 2018/08/21 S21_NA#26767 Add Start
    /** VARCHAR_CONST define: NWA_LEASE_AVAL_CSA_RTRN_RSN */
    public static final String NWA_LEASE_AVAL_CSA_RTRN_RSN = "NWA_LEASE_AVAL_CSA_RTRN_RSN";

    /** VARCHAR_CONST define: NWA_CONV_CONFIG_TP */
    public static final String NWA_CONV_CONFIG_TP = "NWA_CONV_CONFIG_TP";
    // 2018/08/21 S21_NA#26767 Add End

    /** TO_BE_CANCELLED */
    public static final String TO_BE_CANCELLED = "To Be Cancelled";

    // 2017/05/08 S21_NA#Review structure Lv.2 Del Start
//    // 2016/01/20 S21_NA#966 Add Start
//    /** Attach Business Name */
//    public static final String ATTACH_BUSINESS_NM = "Order Entry";
//
//    /** Attach Data Nm */
//    public static final String ATTACH_DATA_ORD_NUM = "Order Number";
//
//    /** Attach Doc Tp Cd Order */
//    public static final String ATTACH_DOC_TP_ORDER = "10";
//    // 2016/01/20 S21_NA#966 Add End
    // 2017/05/08 S21_NA#Review structure Lv.2 Del End

    /** Save/Submit Counter : Header */
    public static final int CNT_HEADER = 0;

    /** Save/Submit Counter : Config */
    public static final int CNT_CONFIG = 1;

    /** Save/Submit Counter : Line */
    public static final int CNT_LINE = 2;

    /** Save/Submit Counter : RMA Config */
    public static final int CNT_RMA_CONFIG = 3;

    /** Save/Submit Counter : RMA Line */
    public static final int CNT_RMA_LINE = 4;

    // 2016/03/04 S21_NA#2173 Add Start
    /** Cancelled */
    public static final String LINE_STS_NM_CANCELLED = "CANCELLED";
    // 2016/03/04 S21_NA#2173 Add Start

    /** Total Line Count Limitation */
    public static final String NWAL1500_LINE_LIMIT_CNT = "NWAL1500_LINE_LIMIT_CNT";
    /** Config Line Count Limitation */
    public static final String NWAL1500_CONFIG_LINE_LIMIT_CNT = "NWAL1500_CONFIG_LINE_LIMIT_CNT";

    /** Delete Action Step 1 2016/09/20 S21_NA#8279 Add */
    public static final String DELETE_LINE_STEP_1 = "1";

    /** Delete Action Step 2 2016/09/20 S21_NA#8279 Add */
    public static final String DELETE_LINE_STEP_2 = "2";

    /** Order Line Filter Action Step 2 2017/08/07 Sol#249 Add */
    public static final String ORDER_LINE_FILTER_STEP_2 = "2";

    /** Bill Event List */
    public static final List<String> BILL_EVENT_LIST;
    static {
        List<String> billEventList = new ArrayList<String>();
        billEventList.add("OpenWin_BillTo");
        billEventList.add("OnBlur_DeriveFromBillToName");
        billEventList.add("OnBlur_DeriveFromBillToAccount");
        billEventList.add("OnBlur_DeriveFromBillToLocation");
        BILL_EVENT_LIST = Collections.unmodifiableList(billEventList);
    }

    /** Ship Event List */
    public static final List<String> SHIP_EVENT_LIST;
    static {
        List<String> shipEventList = new ArrayList<String>();
        shipEventList.add("OpenWin_ShipTo");
        shipEventList.add("OnBlur_DeriveFromShipToName");
        shipEventList.add("OnBlur_DeriveFromShipToAccount");
        shipEventList.add("OnBlur_DeriveFromShipToLocation");
        SHIP_EVENT_LIST = Collections.unmodifiableList(shipEventList);
    }

    /** Sold Event List */
    public static final List<String> SOLD_EVENT_LIST;
    static {
        List<String> soldEventList = new ArrayList<String>();
        soldEventList.add("OpenWin_SoldTo");
        soldEventList.add("OnBlur_DeriveFromSoldToName");
        soldEventList.add("OnBlur_DeriveFromSoldToAccount");
        soldEventList.add("OnBlur_DeriveFromSoldToLocation");
        SOLD_EVENT_LIST = Collections.unmodifiableList(soldEventList);
    }

    /**
     * CSV DOWNLOAD
     */

    /** extension (CSV) */
    public static final String EXTENSION_CSV = ".csv";

    /** DOWNLOAD CSV file name (OUTBOUND) */
    public static final String DOWNLOAD_CSV_FILE_NAME_FOR_OUTBOUND = "NWAL1500F00";

    /** DOWNLOAD CSV file name (INBOUND) */
    public static final String DOWNLOAD_CSV_FILE_NAME_FOR_INBOUND = "NWAL1500F01";
    
    // 2024/03/08 CITS QC#63654 Add Start
    /** DOWNLOAD CSV file name (ORDER NUMBER) */
    public static final String[] DOWNLOAD_CSV_HEADER_FOR_ORDER = {"Order Number"};

    /** DOWNLOAD CSV header (HEADER) */
    public static final String[] DOWNLOAD_CSV_HEADER_FOR_OUTBOUND_HEADER ={"Order Status", "Category", "Reason", "Sub Reason", "Order Date", "Subtotal", "Charges", "Tax", "Total", "Lines", "Maintenance", "RMA", "Bill To Name",
            "Bill To Number", "Bill To Location", "Bill To 1st Line Address", "Bill To 2nd Line Address", "Bill To 3rd Line Address", "Bill To 4th Line Address", "Bill To City", "Bill To State", "Bill To Postal Code", "Ship To Name",
            "Ship To Number", "Ship To Location", "DS", "DS Name", "Ship To 1st Line Address", "Ship To 2nd Line Address", "Ship To 3rd Line Address", "Ship To 4th Line Address", "Ship To City", "Ship To State", "Ship To Postal Code", "Sold To Name",
            "Sold To Number", "Sold To Location", "Sold To 1st Line Address", "Sold To 2nd Line Address", "Sold To 3rd Line Address", "Sold To 4th Line Address", "Sold To City", "Sold To State", "Sold To Postal Code", "Negotiated Deal",
            "Invoiced", "Invoiced(%)", "Salesrep", "Resource Number", "Salesrep Branch", "Salesrep Bus Unit", "Price List", "Customer PO", "Lease Company PO", "Customer PO Date", "Acquisition Number", "Log Type", "Invoice Comments",
            "Source Name", "Import Date", "Source Reference", "Freight Terms", "Service Level", "Carrier Service Level", "Carrier Acct Num", "Special Handling", "Payment Terms", "Payment Method", "Prepayment Check Number",
            "Prepayment Type", "Prepayment Amount", "Creation Date", "Created By", "Last Update Date", "Last Update By", "Booked Date", "Requested Delivery Date", "Association Program Name", "Association Program Number", "Floor Price List",
            "Loan Period (Days)", "CSMP Number", "CSA Number(Dealer Ref#)", "Customer Signed Date", "Customer Declines Equipment Maintenance", "End of Term Purchase Option", "Lease Term", "Payment Frequency", "Lease Total Payment Amount","Config#","Config Action", "Model", "Config ID", "Ship To 1st Line Address", "Ship To 2nd Line Address", "Ship To 3rd Line Address", "Ship To 4th Line Address",
            // 2017/12/08 S21_NA#21621 Mod Start
            //"Ship To City", "Ship To State", "Ship To Postal Code", "Ship To Acct#", "Ship To Loc#", "Ship To Name", "Bill To 1st Line Address", "Bill To 2nd Line Address", "Bill To 3rd Line Address", "Bill To 4th Line Address",
            "Ship To City", "Ship To State", "Ship To Postal Code", "Ship To Acct#", "Ship To Loc#", "Ship To Name", "DS Name", "Bill To 1st Line Address", "Bill To 2nd Line Address", "Bill To 3rd Line Address", "Bill To 4th Line Address",
            // 2017/12/08 S21_NA#21621 Mod End
            "Bill To City", "Bill To State", "Bill To Postal Code", "Bill To Acct#", "Bill To Loc#", "Bill To Name", "Sold To 1st Line Address", "Sold To 2nd Line Address", "Sold To 3rd Line Address", "Sold To 4th Line Address",
            "Sold To City", "Sold To State", "Sold To Postal Code", "Sold to Address", "Sold To#", "Sold To Loc#", "Sold To Name", "Customer Declines Equipment Maintenance", "Model Group", "Model Description", "Segment", "Install Req.", "Site Sur.","Sls CR Splits", "On Hold",
            "Created By", "Creation Date", "Last Update By", "Last Update Date", "Subtotal", "Charges", "Tax", "Config Total", "DS",};

    /** DOWNLOAD CSV header (HEADER) */
    public static final String[] DOWNLOAD_CSV_HEADER_FOR_INBOUND_HEADER ={"Order Status", "Category", "Reason", "Sub Reason", "Order Date", "Subtotal", "Charges", "Tax", "Total", "Lines", "Maintenance", "RMA", "Bill To Name",
            "Bill To Number", "Bill To Location", "Bill To 1st Line Address", "Bill To 2nd Line Address", "Bill To 3rd Line Address", "Bill To 4th Line Address", "Bill To City", "Bill To State", "Bill To Postal Code", "Ship To Name",
            "Ship To Number", "Ship To Location", "DS", "DS Name", "Ship To 1st Line Address", "Ship To 2nd Line Address", "Ship To 3rd Line Address", "Ship To 4th Line Address", "Ship To City", "Ship To State", "Ship To Postal Code", "Sold To Name",
            "Sold To Number", "Sold To Location", "Sold To 1st Line Address", "Sold To 2nd Line Address", "Sold To 3rd Line Address", "Sold To 4th Line Address", "Sold To City", "Sold To State", "Sold To Postal Code", "Negotiated Deal",
            "Invoiced", "Invoiced(%)", "Salesrep", "Resource Number", "Salesrep Branch", "Salesrep Bus Unit", "Price List", "Customer PO", "Lease Company PO", "Customer PO Date", "Acquisition Number", "Log Type", "Invoice Comments",
            "Source Name", "Import Date", "Source Reference", "Freight Terms", "Service Level", "Carrier Service Level", "Carrier Acct Num", "Special Handling", "Payment Terms", "Payment Method", "Prepayment Check Number",
            "Prepayment Type", "Prepayment Amount", "Creation Date", "Created By", "Last Update Date", "Last Update By", "Booked Date", "Requested Delivery Date", "Association Program Name", "Association Program Number", "Floor Price List",
            "Loan Period (Days)", "CSMP Number", "CSA Number(Dealer Ref#)", "Customer Signed Date", "Customer Declines Equipment Maintenance", "End of Term Purchase Option", "Lease Term", "Payment Frequency", "Lease Total Payment Amount","Config#","Config Action", "Model", "Config ID", "Ship To 1st Line Address", "Ship To 2nd Line Address", "Ship To 3rd Line Address", "Ship To 4th Line Address",
            // 2017/12/08 S21_NA#21621 Mod Start
            //"Ship To City", "Ship To State", "Ship To Postal Code", "Ship To Acct#", "Ship To Loc#", "Ship To Name", "Bill To 1st Line Address", "Bill To 2nd Line Address", "Bill To 3rd Line Address", "Bill To 4th Line Address",
            "Ship To City", "Ship To State", "Ship To Postal Code", "Ship To Acct#", "Ship To Loc#", "Ship To Name", "DS Name", "Bill To 1st Line Address", "Bill To 2nd Line Address", "Bill To 3rd Line Address", "Bill To 4th Line Address",
            // 2017/12/08 S21_NA#21621 Mod End
            "Bill To City", "Bill To State", "Bill To Postal Code", "Bill To Acct#", "Bill To Loc#", "Bill To Name", "Sold To 1st Line Address", "Sold To 2nd Line Address", "Sold To 3rd Line Address", "Sold To 4th Line Address",
            "Sold To City", "Sold To State", "Sold To Postal Code", "Sold to Address", "Sold To#", "Sold To Loc#", "Sold To Name", "Model Group", "Model Description", "Segment", "Install Req.", "Site Sur.", "Sls CR Splits", "On Hold",
            "Created By", "Creation Date", "Last Update By", "Last Update Date", "Subtotal", "Charges", "Tax", "Config Total", "DS"};
    
    /** DOWNLOAD CSV header (OUTBOUND) */
    // 2016/07/04 S21_NA#4691-2 Add "QTY B/O before QTY Allocated"
    public static final String[] DOWNLOAD_CSV_HEADER_FOR_OUTBOUND = {"Line#", "Item#", "Item Description", "Qty Order", "Sell Price", "Net Price", "Status", "Line Category",
            "C/R", "Warehouse", "Sub Warehouse", "Line Source", "UOM", "Price List", "Floor Price List", "Unit List Price", "Serial#", "Machine Master ID", "Substitute Item", "Customer Item", "Req. Delivery Date", "Pricing Date", "Creation Date",
            "Booked Date", "Shipped Date", "Delivery Scheduled Date", "QTY B/O", "QTY Allocated", "QTY Shipped", "QTY Invoiced", "QTY Cancelled", "Cancel Reason", "Supd. Lock", "Price Config#", "Picing Config Description", "Merchandise Type",
            "Product Code", "Item Classification", "Manufacturer#", "Line Ref#", "Customer PO#", "Customer PO Date", "Order Source Name", "Order Source Reference", "Line Source Reference", "BOM Parent Line#", "BOM Parent Version#",
            "BOM Parent Item Code", "Created By", "Creation Date", "Last Update By", "Last Update Date", "Subtotal ", "Charges", "Tax", "Line Total" };

    /** DOWNLOAD CSV header (INBOUND) */
    public static final String[] DOWNLOAD_CSV_HEADER_FOR_INBOUND = {"Line#", "Item#", "Item Description", "Qty Order", "Return Reason", "Sell Price", "Net Price", "Status",
            "Line Category", "Warehouse", "Sub Warehouse", "UOM", "Price List", "Floor Price List", "Unit List Price", "Serial#", "Machine Master ID", "Customer Item", "Req. Pick Up Date", "Pricing Date", "Creation Date",
            "Booked Date", "Schd. Pickup Date", "Received Date", "Qty Returned", "QTY Cancelled", "Cancel Reason", "Merchandise Type", "Product Code", "Item Classification", "Manufacturer#", "Line Ref#", "Customer PO#", "Customer PO Date",
            // 2017/12/05 S21_NA#21702 Mod Start
//            "Order Source Name", "Order Source Reference", "Line Source Reference", "BOM Parent Line#", "BOM Parent Version#", "BOM Parent Item Code", "Created By", "Creation Date", "Last Update By", "Last Update Date", "Subtotal ",
            "Order Source Name", "Order Source Reference", "Line Source Reference", "Original Invoice Number", "Original Invoice Line Number", "BOM Parent Line#", "BOM Parent Version#", "BOM Parent Item Code", "Created By", "Creation Date", "Last Update By", "Last Update Date", "Subtotal ",
            // 2017/12/05 S21_NA#21702 Mod End
            "Charges", "Tax", "Line Total", "HDD Removal" };
    // 2024/03/08 CITS QC#63654 Add End
    
//    /** DOWNLOAD CSV header (HEADER) */ //2017/11/24 S21_NA#20019 MOD rename 'Customer Request Date' to 'Requested Delivery Date'
//    public static final String[] DOWNLOAD_CSV_HEADER_FOR_HEADER = {"Order Number", "Order Status", "Category", "Reason", "Sub Reason", "Order Date", "Subtotal", "Charges", "Tax", "Total", "Lines", "Maintenance", "RMA", "Bill To Name",
//            "Bill To Number", "Bill To Location", "Bill To 1st Line Address", "Bill To 2nd Line Address", "Bill To 3rd Line Address", "Bill To 4th Line Address", "Bill To City", "Bill To State", "Bill To Postal Code", "Ship To Name",
//            // 2017/12/08 S21_NA#21621 Mod Start
//            //"Ship To Number", "Ship To Location", "DS", "Ship To 1st Line Address", "Ship To 2nd Line Address", "Ship To 3rd Line Address", "Ship To 4th Line Address", "Ship To City", "Ship To State", "Ship To Postal Code", "Sold To Name",
//            "Ship To Number", "Ship To Location", "DS", "DS Name", "Ship To 1st Line Address", "Ship To 2nd Line Address", "Ship To 3rd Line Address", "Ship To 4th Line Address", "Ship To City", "Ship To State", "Ship To Postal Code", "Sold To Name",
//            // 2017/12/08 S21_NA#21621 Mod End
//            "Sold To Number", "Sold To Location", "Sold To 1st Line Address", "Sold To 2nd Line Address", "Sold To 3rd Line Address", "Sold To 4th Line Address", "Sold To City", "Sold To State", "Sold To Postal Code", "Negotiated Deal",
//            "Invoiced", "Invoiced(%)", "Salesrep", "Resource Number", "Salesrep Branch", "Salesrep Bus Unit", "Price List", "Customer PO", "Lease Company PO", "Customer PO Date", "Acquisition Number", "Log Type", "Invoice Comments",
//            "Source Name", "Import Date", "Source Reference", "Freight Terms", "Service Level", "Carrier Service Level", "Carrier Acct Num", "Special Handling", "Payment Terms", "Payment Method", "Prepayment Check Number",
//            "Prepayment Type", "Prepayment Amount", "Creation Date", "Created By", "Last Update Date", "Last Update By", "Booked Date", "Requested Delivery Date", "Association Program Name", "Association Program Number", "Floor Price List",
//            "Loan Period (Days)", "CSMP Number", "CSA Number(Dealer Ref#)", "Customer Signed Date", "Customer Declines Equipment Maintenance", "End of Term Purchase Option", "Lease Term", "Payment Frequency", "Lease Total Payment Amount" };

    /** NWAL1500_Cancel_Percial_Cancel (2016/04/19 S21_NA#5394 Add Start) */
    public static final String NWAL1500_CANC_PRTL_CANC = "NWAL1500_Cancel_Parcial_Cancel"; // 2016/04/19 S21_NA#5394 Add

    /** Status Name: Back Order */
    public static final String STS_NM_BACK_ORDER = "BACK ORDER"; // 2016/10/05 S21_NA#7645-3 Add

    /** Closed */
    public static final String HEADER_STS_NM_CLOSED = "CLOSED";

    /** Cancelled */
    public static final String HEADER_STS_NM_CANCELLED = "CANCELLED";

    // 2016/12/07 S21_NA#15934 Add Start
    /** CPO Detail Line Sub Number Parent */
    public static final String CPO_DTL_LINE_SUB_NUM_SET_PRNT = "000";
    // 2016/12/07 S21_NA#15934 Add End

    // Add Start 2017/01/27 QC#17257
    /** Sub Warehouse Code(Supply) */
    public static final String SUPPLY_SUB_WH_CD = "SUP";
    // Add Start 2017/01/27 QC#17257

    // 2017/10/04 S21_NA#21300 Add Start
    /** ITT Available Cancel Status */
    public static final String NWAL1500_ITT_AVAL_CANC_PO_STS = "NWAL1500_ITT_AVAL_CANC_PO_STS";

    /** ITT Available Cancel Order Line Source Codes */
    public static final String NWAL1500_ITT_AVAL_CANC_LN_SRC = "NWAL1500_ITT_AVAL_CANC_LN_SRC";
    // 2017/10/04 S21_NA#21300 Add End

    // QC#22031 2017/10/31 Add Start
    public static final String DROP_SHIP_WH = "DS";
    // QC#22031 2017/10/31 Add End

    // 2017/11/02 S21_NA#20146 Add Start
    public static final String XX_MODE_CD_EMSD = "01";
    // 2017/11/02 S21_NA#20146 Add End

    // 2016/01/20 S21_NA#12024 Add Start => 2018/03/05 S21_NA#19808
    public static final String NLCL1000_DROP_SHIP_RTL_WH_CD = "NLCL1000_DROP_SHIP_RTL_WH_CD";
    // 2016/01/20 S21_NA#12024 Add End  => 2018/03/05 S21_NA#19808

    // 2018/03/12 S21_NA#20297(Sol#379) Add Start
    public static final int SHPG_CMT_TXT_LIMIT_SIZE = 240;
    // 2018/03/12 S21_NA#20297(Sol#379) Add End

    // QC#22965 2018/04/11 Add Start
    public static final String NWAL1660_MODE_REFERENCE = "10";

    public static final String NWAL1660_MODE_REGIST = "11";

    public static final String NWAL1660_PROCESS_LVL_HEADER = "01";

    public static final String NWAL1660_PROCESS_LVL_LINE = "02";

    public static final String NWAL1660_VIEW_SRC_CD = "1"; // QC#9700  2018/09/03 Add 

    public static final int TOT_WT_SCALE = 6;
    // QC#22965 2018/04/11 Add End

    // 2018/05/11 S21_NA#22139 Add Start
    /** Brunch Number 00 */
    public static final String BRUNCH_00 = "00";

    /** Brunch Number 03 */
    public static final String BRUNCH_03 = "03";

    /** Date Format (yyyyMMddHHmmssSSS) */
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";

    //2019/07/23  QC#51252 Add Start
    /** Mail Group ID Default */
    public static final String ML_GRP_ID_DEF = "ORD_CONF";

    /** Mail Group ID LFS */
    public static final String ML_GRP_ID_LFS = "ORD_CONF_LFS_SPLY";

    /** Mail Group ID PPS */
    public static final String ML_GRP_ID_PPS = "ORD_CONF_PPS_PARTS";
    //2019/07/23  QC#51252 Add End

    //2019/07/23  QC#51252 Del Start
    // /** Mail Group ID (Order Confirmation) */
    // public static final String MAIL_GROUP_ID_ORD_CONF = "NWAL1770";

    // /** Mail Group ID (Return Authorization) */
    // public static final String MAIL_GROUP_ID_RTRN_AUTH = "NWAL1500";
    //2019/07/23  QC#51252 Del End

    /** Mail Key From */
    public static final String MAIL_KEY_FROM = "From";

    /** Report ID (Order Conf LFS) */
    public static final String NWAF0060 = "NWAF0060";

    /** Report ID (Order Conf Parts & PPS) */
    public static final String NWAF0070 = "NWAF0070";

    /** Report ID (Return Authorization) */
    public static final String NWAF0095 = "NWAF0095";

    /** Mail Template ID (Order Confirmation) */
    public static final String NWAL1770M002 = "NWAL1770M002";

    /** Mail Template ID (Return Authorization) */
    public static final String NWAL1500M001 = "NWAL1500M001";

    /** Report Name (Order Confirmation) */
    public static final String REPORT_NM_ORD_CONF = "Order Confirmation(";

    /** Report Name (Return Authorization) */
    public static final String REPORT_NM_RTRN_AUTH = "Return Authorization(";

    /** Parentheses (Close) */
    public static final String PARENTHESES_CLOSE = ")";

    /** Extension (.pdf) */
    public static final String PDF = ".pdf";

    /** Returned by Customer */
    public static final String RTRN_BY_CUST_CD = "1";
    // 2018/05/11 S21_NA#22139 Add End
    
    // 2018/08/03 S21_NA#27040 Add Start
    // ----------- Line Status -----------
    /** Pending Fulfillment */
    public static final String LINE_STS_NM_PENDING_FULFILLMENT = "PENDING FULFILLMENT";

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

    /** Waiting Receipt */
    public static final String LINE_STS_NM_WAITING_RECEIPT = "WAITING RECEIPT";

    /** Pending Invoice */
    public static final String LINE_STS_NM_PENDING_INVOICE = "PENDING INVOICE";

    /** Billing Hold */
    public static final String LINE_STS_NM_BILLING_HOLD = "BILLING HOLD";

    /** Pending Dealer Install */
    public static final String LINE_STS_NM_PENDING_DEALER_INSTALL = "PENDING DEALER INSTALL";

    /** Invoiced */
    public static final String LINE_STS_NM_INVOICED = "INVOICED";

    /** Pending Return */
    public static final String LINE_STS_NM_PENDING_RETURN = "PENDING RETURN";

    /** Partial Received */
    public static final String LINE_STS_NM_PARTIAL_RECEIVED = "PARTIAL RECEIVED";

    /** Pending Inspection */
    public static final String LINE_STS_NM_PENDING_INSPECTION = "PENDING INSPECTION";
    // 2018/08/03 S21_NA#27040 Add End

    // 2018/08/28 QC#26329 Add Start
    /** Closed */
    public static final String LINE_STS_NM_CLOSED = "CLOSED";
    // 2018/08/28 QC#26329 Add End

    // 2019/07/11 S21_NA#51287 Add Start
    /** On Loan */
    public static final String LINE_STS_NM_ON_LOAN = "ON LOAN";

    /** Shipped Closed */
    public static final String LINE_STS_NM_SHIPPED_CLOSED = "CLOSED";

    /** Closed Loan Return */
    public static final String LINE_STS_NM_CLOSED_LOAN_RETURN = "CLOSED LOAN RETURN";

    /** Closed Loan Sold */
    public static final String LINE_STS_NM_CLOSED_LOAN_SOLD = "CLOSED LOAN SOLD";
    // 2019/07/11 S21_NA#51287 Add End

    // 2018/08/24 S21_NA#27202 Add Start
    /** DS_DROP_SHIP_WH_CD */
    public static final String DS_DROP_SHIP_WH_CD = "DS_DROP_SHIP_WH_CD";
    // 2018/08/24 S21_NA#27202 Add End

    // 2023/02/07 QC#61010 Add Start
    /** RCV_SER_TAKE_FLG */
    public static final String RCV_SER_TAKE_FLG = "RCV_SER_TAKE_FLG";

    /** SPHG_SER_TAKE_FLG */
    public static final String SHPG_SER_TAKE_FLG = "SHPG_SER_TAKE_FLG";
    // 2023/02/07 QC#61010 Add End
    // 2018/10/25 S21_NA#28897 Add Start
    /** Booked */
    public static final String LINE_STS_NM_BOOKED = "BOOKED";
    // 2018/10/25 S21_NA#28897 Add End

    // Add Start 2019/05/07 QC#50015
    /** Modify Authority (Logistics) */ 
    public static final String MOD_LOGISTICS = "NWAL1500T080";
    // Add End 2019/05/07 QC#50015
    // Add Start 2019/12/20 QC#53055
    public static final String TRTY_GRP_TP_IS = "IS";
    // Add End   2019/12/20 QC#53055
    // Add Start 2020/01/17 QC#55202
    public static final String RTL_WH_CATG_DROP_RMA_CD = "90";
    // Add End   2020/01/17 QC#55202
    // Add Start 2020/03/16 QC#56132
    public static final String RTL_WH_CATG_VIRTUAL_CD = "99";
    // Add End   2020/03/16 QC#56132
}
