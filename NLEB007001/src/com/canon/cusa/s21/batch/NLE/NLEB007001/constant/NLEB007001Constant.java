/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLE.NLEB007001.constant;

/**
 * <pre>
 * NLEB007001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Hitachi         J.Kim           Create          N/A
 * 2016/06/06   Hitachi         T.Tsuchida      Update          QC#9451
 * 2017/03/13   Hitachi         E.Kameishi      Update          QC#17980
 * </pre>
 */
public class NLEB007001Constant {

    /*****************************************************************
     * Process Mode
     ****************************************************************/

    /** Mode: Revenue Recognition 1 */
    public static final String REVENUE_RECOGNITION = "1";

    /** Mode: Return 2 */
    public static final String MODE_RETURN = "2";

    /** Mode: Disposal 3 */
    public static final String DISPOASAL = "3";

    /** Mode: Update 4 */
    public static final String UPDATE = "4";

    /** Mode: Ship from Asset WH 6 */
    public static final String SHIP_FROM_ASSET_WH = "6";

    // START 2017/12/04 J.Kim [QC#18127,ADD]
    /** Mode: Update by Service Exchange 7 */
    public static final String UPDATE_SERVICE_EXCHANGE = "7";
    // END 2017/12/04 J.Kim [QC#18127,ADD]

    /** Mode: Pending Asset Entry 10 */
    public static final String PENDING_ASSET_ENTRY = "A";

    /** Mode: Asset Activate 11 */
    public static final String ASSET_ACTIVATE = "B";

    /** Mode: Update Before Activate */
    public static final String MODE_UPDATE_BEFORE_ACTIVATE = "C";

    /** Mode: Asset Entry from AP 13 */
    public static final String ASSET_ENTRY_FROM_AP = "D";

    /** Mode: Initial Cost Calculation */
    public static final String INIT_COST_CALUC = "H";

    /** Process Mode : 01 */
    public static final String PROC_MODE_01 = "01";

    /** Process Mode : 02 */
    public static final String PROC_MODE_02 = "02";

    /** Process Mode : 11 */
    public static final String PROC_MODE_11 = "11";

    /** Process Mode : 21 */
    public static final String PROC_MODE_21 = "21";

    /** Process Mode : 31 */
    public static final String PROC_MODE_31 = "31";

    /** Process Mode : 41 */
    public static final String PROC_MODE_41 = "41";

    /** Process Mode : 51 */
    public static final String PROC_MODE_51 = "51";

    /** Process Mode : 61 */
    public static final String PROC_MODE_61 = "61";

    /** Message : It failed to get @. @ */
    public static final String NLEM0001E = "NLEM0001E";

    /** Message : [@] No target records exists for this process. TABLE : @ */
    public static final String NLEM0022E = "NLEM0022E";

    /** Message : [@] Error occurred in Asset Update API. <Mode @> */
    public static final String NLEM0024E = "NLEM0024E";

    /** Message : [@] SHPG_STS_CD is incorrect. <SHPG_STS_CD : @> */
    public static final String NLEM0026E = "NLEM0026E";

    /** Message : [@] ASSET_STS_CD is incorrect. <ASSET_STS_CD : @> */
    public static final String NLEM0027E = "NLEM0027E";

    /** Message : [@] You need to post the data to Sales Assets. */
    public static final String NLEM0028E = "NLEM0028E";

    /** Message : Before Import asset can not be return process. Please confirm asset status. */
    public static final String NLEM0029E = "NLEM0029E";

    /** Message : [@] There are no Disposal Asset Data. */
    public static final String NLEM0030E = "NLEM0030E";

    // START 2017/03/13 E.Kameishi [QC#17980,ADD]
    /** Message : Process ended. "Account Date Year Month" does not match with Batch Operation Date. */
    public static final String NLEM0048I = "NLEM0048I";
    // END 2017/03/13 E.Kameishi [QC#17980,ADD]

    /** Const : NLEB0070_ASSET_STGNG_STS */
    public static final String ASSET_STGNG_STS = "NLEB0070_ASSET_STGNG_STS";

    /** Const : NLEB0070_COA_MDSE_TP_CD */
    public static final String COA_MDSE_TP_CD = "NLEB0070_COA_MDSE_TP_CD";

    /** BATCH_PROGRAM_ID(NLEB007001) */
    public static final String PROGRAM_ID = "NLEB007001";

    /** BATCH_BUSINESS_ID(NLEB0070) */
    public static final String BUSINESS_ID = "NLEB0070";

    /** Fetch size for SSM */
    public static final int SIZE_MAX = 1000;

    /** Mode 01:Rental Shipment */
    public static final String RENTAL_SHIPMENT = "Rental Shipment";

    /** Mode 02:Rental Shipment */
    public static final String EMSD_SHIPMENT = "EMSD Shipment";

    /** Mode 11:Rental to Sales */
    public static final String RENTAL_TO_SALES = "Rental to Sales";

    /** Mode 21:Return / Retire */
    public static final String RETURN = "Return / Retire";

    /** Mode 31:Configuration Change */
    public static final String CONFIGURATION_CHANGE = "Configuration Change";

    /** Mode 41:Service Exchange Shipment */
    public static final String SERVICE_EXCHANGE_SHIPMENT = "Service Exchange Shipment";

    /** Mode 51:AP Invoice */
    public static final String AP_INVOICE = "AP Invoice";

    /** Mode 61:Asset Adjustoment or Disposal */
    public static final String ASSET_ADJUSTOMENT_OR_DISPOSAL = "Asset Adjustment Or Disposal";

    /** Table Name:DS_RTL_WH_V */
    public static final String DS_RTL_WH_V = "DS_RTL_WH_V";

    /** Table Name:DS_ASSET_MSTR */
    public static final String DS_ASSET_MSTR = "DS_ASSET_MSTR";

    /** Table Name:SHPG_PLN */
    public static final String SHPG_PLN = "SHPG_PLN";

    /** Table Name:SVC_MACH_MSTR */
    public static final String SVC_MACH_MSTR = "SVC_MACH_MSTR";

    /** Table Name:DS_MDSE_INFO */
    public static final String MDSE = "MDSE";

    /** Table Name:DS_ASSET_STGNG */
    public static final String DS_ASSET_STGNG = "DS_ASSET_STGNG";

    /** Table Name:INV */
    public static final String INV = "INV";

    /**
     * Mail
     */

     /** Mail Template ID(NLEB0070M001) */
     public static final String MAIL_TEMPLATE_ID = BUSINESS_ID + "M001";

     /** Mail Character Set */
     public static final String MAIL_CHARSET = "ISO-8859-1";

     /** Date Format(yyyyMMddHHmmssSSS) */
     public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

     /** PROC_PGM_ID : NLEB007001 */
     public static final String PROC_PGM_ID = "NLEB007001";

     /** Mail Group From(FROM0003) */
     public static final String MAIL_GROUP_ID_FROM = "FROM0003";

     /** Mail Group From(DS) */
     public static final String MAIL_GROUP_KEY_FROM = "NLE";

     /** Mail Group From(DS) */
     public static final String MAIL_TITLE = "Asset Creation Batch";

     /** Mail Group To(NSBB0480) */
     public static final String MAIL_GROUP_ID_TO = "NLEB0070";

     /** Mail Item (Asset Creation Batch [${batchId}]-[${batchNm}]) */
     public static final String MAIL_ITEM_BATCH_ID = "batchId";

     /** Mail Item (Asset Creation Batch [${batchId}]-[${batchNm}]) */
     public static final String MAIL_ITEM_BATCH_NM = "batchNm";

     /** Mail Item (${sysdate}) */
     public static final String MAIL_ITEM_DATE = "sysdate";

     /** Mail Item (${CSV_Value}) */
     public static final String MAIL_ITEM_CSV_FILE_NAME = "CSV_Value";

     /** Mail Item (${message}) */
     public static final String MAIL_ITEM_ERR_MSG = "message";

     /** CSV FILE NAME */
     public static final String FILE = "-";

     /** CSV FILE NAME EXT */
     public static final String CSV_EXT = ".csv";

     /** Date Length */
     public static final int DT_LEN_14 = 14;

     /** Date Length */
     public static final int DT_LEN_8 = 8;

     /** Empty Value */
     public static final String EMPTY_STRING = "";

     /** COMMA */
     public static final String STR_CNM = ",";

     /** BREAK */
     public static final String STR_CRLF = "\r\n";

     /** SQLID(DS_BIZ_PROC_LOGT) */
     public static final String SQLID = "001";

     /** CSV Header (Mode 01,02,11,21(ASSET_TP_CD:R), 31) */
     public static final String[] CSV_HEADER_1 = {
         "Asset Number",
         "Merchandise Code",
         "Description",
         "Serial Number",
         "Merchandise Type",
         "Configuration Number",
         "Shipment TO",
         "Quantity",
         "Order Number",
         "CPO Line Number",
         "CPO Line Sub Number",
         "CPO Position Number"
     };

     /** CSV Header (Mode 21(ASSET_TP_CD:F), 41) */
     public static final String[] CSV_HEADER_2 = {
         "Asset Number",
         "Merchandise Code",
         "Description",
         "Serial Number",
         "Merchandise Type",
         "Configuration Number",
         "Shipment TO",
         "Quantity",
         "Order Number",
         "CPO Line Number",
         "CPO Line Sub Number",
         "CPO Position Number",
         "Return WH"
     };

     /** CSV Header (Mode 51)*/
     public static final String[] CSV_HEADER_3 = {
         "Asset Number",
         "Merchandise Code",
         "Description",
         "Serial Number",
         "Merchandise Type",
         "Configuration Number",
         "Vender Name",
         "Quantity",
         "Purchase Order Number"
     };

     /** CSV Header (Mode 61)*/
     public static final String[] CSV_HEADER_4 = {
         "Asset Number",
         "Merchandise Code",
         "Description",
         "Serial Number",
         "Merchandise Type",
         "Configuration Number",
         "Shipment TO",
         "Quantity"
     };
}
