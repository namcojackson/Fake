/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7260.constant;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *<pre>
 * NMAL7260Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2016/04/06   Fujitsu         Y.Kanefusa      Update          QC#6397
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2016/09/12   Fujitsu         R.Nakamura      Update          QC#11615
 * 2016/09/28   Fujitsu         R.Nakamura      Update          QC#6924
 * 2016/09/30   Fujitsu         R.Nakamura      Update          QC#6931
 * 2016/10/17   Fujitsu         R.Nakamura      Update          QC#14936
 * 2017/08/15   Fujitsu         K.Ishizuka      Update          QC#18237(L3#161)
 * 2017/11/10   Fujitsu         Y.Kanefusa      Update          S21_NA#20249
 * 2018/04/09   Fujitsu         H.Nagashima     Update          QC#22593
 * 2018/04/19   Fujitsu         M.Ohno          Update          QC#22569 
 * 2018/06/07   Fujitsu         T.Noguchi       Update          QC#26099
 * 2018/06/18   Fujitsu         M.Ishii         Update          QC#22594
 * 2018/07/18   Fujitsu         W.Honda         Update          QC#20267
 * 2018/12/04   Fujitsu         Hd.Sugawara     Update          QC#29321
 * 2019/12/18   Fujitsu         C.Hara          Update          QC#55108
 *</pre>
 */
public class NMAL7260Constant {

    /** Check Box PA */
    public static final String CHK_A = "xxChkBox_A1";

    /** Check Box PB */
    public static final String CHK_B = "xxChkBox_B1";

    /** Check Box PC */
    public static final String CHK_C = "xxChkBox_C1";

    /** Row ID PB */
    public static final String ROW_ID_B = "xxRowId_B1";

    /** COMMA */
    public static final String COMMA = ",";

    /** ASTERISK */
    public static final String ASTERISK = "*";

    /** Table Data */
    public static final String TABLEDATA = "Table Data";

    /** HIGH_VAL_DT : 99991231 */
    public static final String HIGH_VAL_DT = "99991231";

    /** Date check Pattern */
    public static final String CHK_DATE_PATTERN = "\\d{1,2}/\\d{1,2}/\\d{4}";

    /** Slash */
    public static final String SLASH = "/";

    /** Slash */
    public static final String DATE_FORMAT_PADDING_ZERO = "0";
    
    // Add Start 2018/06/18 QC#22594
    /** GlblMsg Max Length */
    public static final int GLBLMSG_MAX_LENGTH = 2000;
    // Add End 2018/06/18 QC#22594
    

    //--------------------------------
    // Message ID
    //--------------------------------

    /** Details cannot be added because the number will exceed [@]. */
    public static final String NMAM0050E = "NMAM0050E";

    /** [@] is already registered. */
    public static final String NMAM0010E = "NMAM0010E";

    /** @ is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** The entered [@] does not exist in [@]. */
    public static final String NMAM0163E = "NMAM0163E";

    /** [@] field is mandatory. */
    public static final String NMAM0836E = "NMAM0836E";

    /** The process ended abnormally. */
    public static final String NMAM8020E = "NMAM8020E";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /** The chronological sequence is wrong. */
    public static final String NMAM8115E = "NMAM8115E";

    /** The data @ does not exist in the master. */
    public static final String NMAM8186E = "NMAM8186E";

    /** @ must have at least one row. Please enter data. */
    public static final String NMAM8214E = "NMAM8214E";

    /** Please entry 8 digits merchandise code. */
    public static final String NMAM8216E = "NMAM8216E";

    /** Please select the Modify corresponding to @. */
    public static final String NMAM8259E = "NMAM8259E";

    /**
     * Delete target, it has been used in the @. Please try again to
     * delete the @.
     */
    public static final String NMAM8260E = "NMAM8260E";

    /** Target number is of 0. */
    public static final String NMAM8320E = "NMAM8320E";

    /** It is different from the number of items of Table Data. */
    public static final String NMAM8321E = "NMAM8321E";

    /**
     * The format or number of digits of the specified value in CSV
     * file is invalid. Item Name: @ Value: @
     */
    public static final String NMAM8328E = "NMAM8328E";

    /** It failed to read the file. File name: @ */
    public static final String NMAM8329E = "NMAM8329E";

    /** You want to delete the [@]? If 'yes', click again. */
    public static final String NMAM8330I = "NMAM8330I";

    /**
     * Given price rule combination is not accepted. Please review
     * validation table (PRC_RULE_CMBN_EXCL).
     */
    public static final String NMAM8369E = "NMAM8369E";

    // 2018/07/18 QC#20267 Add Start
    /** Merchandise Code cannot be uniquely identified. */
    public static final String NMAM8685E ="NMAM8685E";
    // 2018/07/18 QC#20267 Add End

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** No more than [@] records can not be registered. */
    public static final String ZZZM9011E = "ZZZM9011E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** After [@] is registered, you cannot delete it. */
    public static final String NMAM8538E = "NMAM8538E";

    /**
     * If Default Precedence# is duplicated, Please register the Price
     * Precedence.
     */
    public static final String NMAM8627W = "NMAM8627W"; // QC#9694 2016/07/08 Add

    // Add Start 2016/09/12 QC#11615
    /** [@] cannot be set because of [@]. */
    public static final String NMAM0179E = "NMAM0179E";

    // Add End 2016/09/12 QC#11615

    // Add Start 2016/10/25 QC#14936
    /** The magnitude correlation of [@] is wrong. */
    public static final String NMAM8332E = "NMAM8332E";

    /** The contents of Table Definition and Table Data is different. */
    public static final String NMAM8324E = "NMAM8324E";

    // Add End 2016/10/25 QC#14936

    /** Quantity Discount is required. */
    public static final String NMAM8203E = "NMAM8203E";

    // 2018/06/07 QC#26099 Add Start
    /** [@] field has too many decimal digits entered. */
    public static final String ZZM9015E = "ZZM9015E";
    // 2018/06/07 QC#26099 Add End

    // 2019/12/18 QC#55108 Add Start
    /** [@] field requires numeric input only. */
    public static final String ZZM9004E = "ZZM9004E";
    // 2019/12/18 QC#55108 Add End

    /** CSV file name : Transaction Conditions */
    public static final String CSV_FILE_NM = "NMAL7260PriceAdjustment";

    /** CSV file extension */
    public static final String CSV_FILE_EXTENSION = ".csv";

    /**
     * Common button縲�/ public static final String[] BTN_CMN_SAVE =
     * {"btn1", "CMN_Save", "Save" }; /** Common button縲�/ public
     * static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit",
     * "Submit" }; /** Common button縲�/ public static final String[]
     * BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" }; // table data
     * /** check box
     */
    public static final String TABLE_ITEM_CHECK_BOX = "BH0";

    /** CSMP# */
    public static final String TABLE_ITEM_CSMP = "BH4";

    /** Material Price */
    public static final String TABLE_ITEM_MATERIAL_PRICE = "BH5";

    /** Prod Ctrl -1(BU) */
    public static final String TABLE_ITEM_PROD_CTRL_1 = "BH6";

    /** Prod Ctrl -2(Model Series)CSMP# */
    public static final String TABLE_ITEM_PROD_CTRL_2 = "BH7";

    /** Mdse Type */
    public static final String TABLE_ITEM_MDSE_TYPE = "BH8";

    /** Product Code */
    public static final String TABLE_ITEM_PRODUCT_CD = "BH9";

    /** Item Code */
    public static final String TABLE_ITEM_ITEM_CD = "BH10";

    /** Order Category */
    public static final String TABLE_ITEM_ORDER_CATEGORY = "BH11";

    /** Order Type */
    public static final String TABLE_ITEM_ORDER_REASON = "BH12";

    /** Order Line of Business */
    public static final String TABLE_ITEM_ORDER_LINE_OF_BUSINESS = "BH13";

    /** Transaction Group */
    public static final String TABLE_ITEM_TRANSACTION_GROUP = "BH14";

    /** Total Transaction Weight */
    public static final String TABLE_ITEM_TRANSACTION_WEIGHT = "BH15";

    /** Bill To (Acct#) */
    public static final String TABLE_ITEM_BILL_TO = "BH16";

    /** Bill To Acct (Channel) */
    public static final String TABLE_ITEM_BILL_TO_ACCT_CHANNEL = "BH17";

    /** Bill To Acct (Classification) */
    public static final String TABLE_ITEM_BILL_TO_ACCT_CLASSIFICATION = "BH18";

    /** Branch */
    public static final String TABLE_ITEM_BRANCH = "BH19";

    /** Call Type */
    public static final String TABLE_ITEM_CALL_TYPE = "BH20";

    /** Call Date */
    public static final String TABLE_ITEM_CALL_DATE = "BH21";

    /** Customer PO */
    public static final String TABLE_ITEM_CUSTOMER_PO = "BH22";

    /** Line Amount */
    public static final String TABLE_ITEM_LINE_AMOUNT = "BH24";

    /** Line Category (Line Type) */
    public static final String TABLE_ITEM_LINE_CATEGORY_LINE_TYPE = "BH25";

    /** Line Qty */
    public static final String TABLE_ITEM_LINE_QTY = "BH26";

    /** Marketing Model Name */
    public static final String TABLE_ITEM_MARKETING_MODEL_NAME = "BH27";

    /** Model Segment */
    public static final String TABLE_ITEM_MODEL_SEGMENT = "BH28";

    /** Order Source */
    public static final String TABLE_ITEM_ORDER_SOURCE = "BH29";

    /** Order Subtotal */
    public static final String TABLE_ITEM_ORDER_SUBTOTAL = "BH30";

    /** Payment Type */
    public static final String TABLE_ITEM_PAYMENT_TYPE = "BH31";

    /** Price List */
    public static final String TABLE_ITEM_PRICE_LIST = "BH32";

    /** Pricing Date */
    public static final String TABLE_ITEM_PRICING_DATE = "BH33";

    /** Prod Ctrl -3(Product) */
    public static final String TABLE_ITEM_PROD_CTRL_3 = "BH34";

    /** Prod Ctrl -4(Product-Group) */
    public static final String TABLE_ITEM_PROD_CTRL_4 = "BH35";

    /** Prod Ctrl -5(Product-Type) */
    public static final String TABLE_ITEM_PROD_CTRL_5 = "BH36";

    /** Request Date */
    public static final String TABLE_ITEM_REQUEST_DATE = "BH37";

    /** Return Reason Code */
    public static final String TABLE_ITEM_RETURN_REASON_CODE = "BH38";

    /** Service Level */
    public static final String TABLE_ITEM_SERVICE_LEVEL = "BH39";

    /** Service Model */
    public static final String TABLE_ITEM_SERVICE_MODEL = "BH40";

    /** Service Zone */
    public static final String TABLE_ITEM_SERVICE_ZONE = "BH41";

    /** Ship To Acct (Classification) */
    public static final String TABLE_ITEM_SHIP_TO_ACCT_CLASSIFICATION = "BH42";

    /** Special Handling Type */
    public static final String TABLE_ITEM_SPECIAL_HANDLING_TYPE = "BH44";

    /** Total Qry */
    public static final String TABLE_ITEM_TOTAL_QTY = "BH45";

    /** Business Unit */
    public static final String TABLE_ITEM_BUSINESS_UNIT = "BH46";

    /** Freight Term */
    public static final String TABLE_ITEM_FREIGHT_TERM = "BH48";

    /** Freight Zone */
    public static final String TABLE_ITEM_FREIGHT_ZONE = "BH49";

    /** Customer Price Group(Sold To) */
    public static final String TABLE_ITEM_CUSTOMER_PRICE_GROUP_SOLD_TO = "BH53";

    /** Sold To (Acct#) */
    public static final String TABLE_ITEM_ACCNT_SOLD_TO = "BH54";

    /** Sold To Acct (Channel) */
    public static final String TABLE_ITEM_CUSTOMER_CHANNEL_SOLD_TO = "BH55";

    /** Sold To Acct (Classification) */
    public static final String TABLE_ITEM_CUSTOMER_CLS_SOLD_TO = "BH56";

    /** Material Price Group(QtyBrk) */
    public static final String TABLE_ITEM_MATERIAL_PRICE_QTYBRK = "BH57";

    /** LineQty (QtyBrk) */
    public static final String TABLE_ITEM_LINE_QTY_QTYBRK = "BH58";

    // 2018/04/19 QC#22569 add start
    /** Material Group 1 */
    public static final String TABLE_ITEM_MATERIAL_GRP_1 = "BH59";

    /** Material Group 2 */
    public static final String TABLE_ITEM_MATERIAL_GRP_2 = "BH60";

    /** Material Group 3 */
    public static final String TABLE_ITEM_MATERIAL_GRP_3 = "BH61";
    // 2018/04/19 QC#22569 add end

    // 2018/04/19 QC#22569 add start

    /** Manufacture# */
    public static final String TABLE_ITEM_MNF_ITEM_CD = "BH62";
    // 2018/04/19 QC#22569 add end

    /** Formula */
    public static final String TABLE_ITEM_FORMULA = "BH50";

    /** Percent */
    public static final String TABLE_ITEM_PERCENT = "BH51";

    /** Value */
    public static final String TABLE_ITEM_VALUE = "BH52";

    /** Value */
    public static final String TABLE_ITEM_QTYDISC = "BH90";

    /** Effective Date From */
    public static final String TABLE_ITEM_EFFECTIVE_DATE_FROM = "BH97";

    /** Effective Date To */
    public static final String TABLE_ITEM_EFFECTIVE_DATE_TO = "BH98";

    /** pAuditInfo */
    public static final String TABLE_ITEM_P_AUDIT_INFO = "BH99";

    /** table def */
    public static final String TABLE_ITEM_DEF = "BH0:BH97:BH98";

    /** delimiter */
    public static final String CSV_DELIMITER = ":";

    /** table Max cnt */
    public static final int TABLE_ITEM_CSV_MAX_CNT = 50;

    /** table def max */
    public static final int TABLE_DEF_MAX_CNT = 13;

    /** 100 */
    public static final BigDecimal NUM_100 = new BigDecimal(100);

    /** percent digit */
    public static final int PERCENT_DIGIT = 2;

    /** db digit */
    public static final int DB_DIGIT = 5;

    // 2018/07/18 QC#20267 Add Start
    /** Column Header Name */
    public static final String HDR_NM_MNF_NUM ="Manufacture#";
    // 2018/07/18 QC#20267 Add End

    // 2018/06/07 QC#26099 Add Start
    /** decimal format for amount */
    public static final DecimalFormat DF_AMT = new DecimalFormat("#,##0.00");
    // 2018/06/07 QC#26099 Add End

    // --------------------------------
    // Index
    // --------------------------------
    public static final int IDX_0 = 0;

    public static final int IDX_1 = 1;

    public static final int IDX_2 = 2;

    public static final int IDX_3 = 3;

    public static final int IDX_4 = 4;

    public static final int IDX_5 = 5;

    public static final int IDX_6 = 6;

    public static final int IDX_7 = 7;

    public static final int IDX_8 = 8;

    public static final int IDX_9 = 9;

    public static final int IDX_10 = 10;

    public static final int IDX_11 = 11;

    public static final int IDX_12 = 12;

    public static final int IDX_13 = 13;

    public static final int IDX_20 = 20;

    public static final int IDX_22 = 22;

    public static final int IDX_26 = 26;

    public static final int IDX_27 = 27;

    public static final int IDX_30 = 30;

    public static final int IDX_31 = 31;

    public static final int IDX_33 = 33;

    public static final int IDX_50 = 50;

    public static final int IDX_80 = 80;

    public static final int IDX_100 = 100;

    // Mod Start 2018/12/04 QC#29321
    ///** CSV Commmon Header For Download */
    //public static final String[] CSV_DOWNLOAD_COMMON_HEADER = new String[] {"Adjustment Table ID", "Adjustment Table Name", "Adjustment Table Description", "Comments", "Default Rule Precedence#", "Precedence Group", "Line of Business",
    //        "Category", "Price Adjustment Level", "Modify", "Adjustment ", "Transaction Type", "Apply Automatically", "Override", "Active ", "Table Effective Date From", "Table Effective Date To", "Table Created By", "Table Created Date", "Table Updated By",
    //        "Table Updated Date" };
    /** CSV Commmon Header For Download */
    public static final String[] CSV_DOWNLOAD_COMMON_HEADER = new String[] {"Adjustment Table ID" };
    // Mod End 2018/12/04 QC#29321

    // Mod Start 2018/12/04 QC#29321
    ///** CSV Commmon Header For Download */
    //public static final int[] CSV_DOWNLOAD_COMMON_HEADER_NUM = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
    /** CSV Commmon Header For Download */
    public static final int[] CSV_DOWNLOAD_COMMON_HEADER_NUM = new int[] {0 };
    // Mod End 2018/12/04 QC#29321

    // Del Start 2018/12/04 QC#29321
    ///** CSV Commmon Data For Download */
    //public static final String[] CSV_DOWNLOAD_COMMON_DATA = new String[] {"Created By", "Created Date", "Updated By", "Updated Date" };
    // Del End 2018/12/04 QC#29321

    // Del Start 2018/12/04 QC#29321
    ///** CSV Commmon Header For Download */
    //public static final int[] CSV_DOWNLOAD_COMMON_DATA_NUM = new int[] {21, 22, 23, 24 };
    // Del End 2018/12/04 QC#29321

    /** convert format : 0.regex, 1.replacement */
    public static final String[] DT_CONV_FORMAT = {"^(....)(..)(..)$", "$2/$3/$1" };

    // Add End 2016/10/03 QC#6931

    // Add Start 2016/10/17 QC#14936
    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** PRC_RULE_COND_GRP_CD Max Length */
    // Mod Start 2016/10/25 QC#14936
    //    public static final int COND_GRP_MAX_LENGTH = 2;
    public static final int COND_GRP_MAX_LENGTH = 4;

    // Mod End 2016/10/25 QC#14936

    /** date max */
    public static final String TO_DATE_MAX = "99991231";

    // Add End 2016/10/17 QC#14936
    /** String Date High Value */
    public static final String HIGH_VAL_TM = "999999999"; // Add S21_NA QC#18237(Sol#161)


    public static final BigDecimal LOW_VAL_QTY  = BigDecimal.ONE;

    public static final BigDecimal HIGH_VAL_QTY = new BigDecimal("9999999999");

    public static final String MODE_MODIFY = "M";

    public static final String MODE_NEW = "N";

    public static final String MODE_DELETE = "D";

    // QC#22593 add Start
    /** TAB : Table Definition */
    public static final String TAB_ADJ_TBL_DFN = "AdjTblDfn";

    /** TAB : Table Data */
    public static final String TAB_ADJ_TBL_DATA = "AdjTblData";
    // QC#22593 add End
}
