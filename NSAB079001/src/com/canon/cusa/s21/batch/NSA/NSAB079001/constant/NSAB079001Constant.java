package com.canon.cusa.s21.batch.NSA.NSAB079001.constant;

/**
 * <pre>
 * Auto Ship Toner from IWR
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/14   Hitachi         U.Kim           Create          QC#18616
 * 2018/02/19   Hitachi         U.Kim           Update          QC#20297(Sol#379)
 * 2018/02/28   Hitachi         U.Kim           Update          QC#23296
 * 2018/05/25   Hitachi         K.Kim           Update          QC#15410(Sol#246)
 * 2018/09/10   Hitachi         K.Kitachi       Update          QC#26260
 *</pre>
 */
public class NSAB079001Constant {

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Not found record or mgtDt in DT_MGT. [dtProcCd : S, glblCmpyCd : @].*/
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** An error occurred in API. @ @ [@] */
    public static final String NSZM0407E = "NSZM0407E";

    /** [@] is not registered.(@) */
    public static final String NSAM0069E = "NSAM0069E";

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSAB0790";

    /** Batch Id **/
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = "NSAB0790";

    /** Length : Date Time */
    public static final int LEN_DT_TM = 14;

    /** number : 1 */
    public static final int NUM_ONE = 1;

    /** number : 3 */
    public static final int NUM_THREE = 3;

    /** number : 8 */
    public static final int NUM_EIGHT = 8;

    /** template ID : Error Email */
    public static final String MAIL_TEMPLATE_ID_ERR = BIZ_APP_ID + "M001";

    /** template ID : Customer Name Mismatch or Duplicate Supply Order */
    public static final String MAIL_TEMPLATE_ID_MIS_DUP = BIZ_APP_ID + "M002";

    /** template ID : Special Handing */
    public static final String MAIL_TEMPLATE_ID_SPEC_HAND = BIZ_APP_ID + "M003";

    /** template parameter key : batch id */
    public static final String MAIL_TEMPLATE_KEY_ID = "batchId";

    /** template parameter key : message */
    public static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** template parameter key : error date */
    public static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** template parameter key : error type */
    public static final String MAIL_TEMPLATE_KEY_ERR_TP = "errType";

    /** template parameter key : main error tpye */
    public static final String MAIL_TEMPLATE_KEY_MAIN_ERR_TP = "mainErrType";

    /** template parameter key : customer name */
    public static final String MAIL_TEMPLATE_KEY_CUST_NM = "custNm";

    /** template parameter key : customer id */
    public static final String MAIL_TEMPLATE_KEY_CUST_ID = "custId";

    /** template parameter key : address */
    public static final String MAIL_TEMPLATE_KEY_ADDR = "address";

    /** template parameter key : order number */
    public static final String MAIL_TEMPLATE_KEY_ORD_NUM = "ordNum";

    /** template parameter key : model/product name */
    public static final String MAIL_TEMPLATE_KEY_MDL_NM = "mdlNm";

    /** template parameter key : serial number */
    public static final String MAIL_TEMPLATE_KEY_SER_NUM = "serNum";

    /** template parameter key : consumable name */
    public static final String MAIL_TEMPLATE_KEY_CON_NM = "conNm";

    /** template parameter key : part number */
    public static final String MAIL_TEMPLATE_KEY_PART_NUM = "partNum";

    /** template parameter key : quote number */
    public static final String MAIL_TEMPLATE_KEY_Q_NUM = "quoNum";

    /** Date Time Pattern For Mail */
    public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";

    /** UGW_SPLY_ORDR_LIMIT_DAY */
    public static final String UGW_SPLY_ORDR_LIMIT_DAY = "UGW_SPLY_ORDR_LIMIT_DAY";

    /** Max Commit Number */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Mail Mode for Error mail */
    public static final int SEND_MAIL_MODE_ERROR = 1;

    /** Mail Mode for Customer Name Mismatch mail */
    public static final int SEND_MAIL_MODE_CUST_MIS = 2;

    /** Mail Mode for Duplicate Supply Order mail */
    public static final int SEND_MAIL_MODE_DUPL_ORD = 3;

    /** Mail Mode for Special Handing mail */
    public static final int SEND_MAIL_MODE_SPC_HAND = 4;

    // START 2018/09/10 K.Kitachi [QC#26260, DEL]
//    /** DEF_LINE_BIZ_CD */
//    public static final String DEF_LINE_BIZ_CD = "ALL";
    // END 2018/09/10 K.Kitachi [QC#26260, DEL]

    // START 2018/05/25 K.Kim [QC#15410(Sol#246),ADD]
    /** default EFF_THRU_DT*/
    public static final String DEF_EFF_THRU_DT = "99991231";
    // END 2018/05/25 K.Kim [QC#15410(Sol#246),ADD]

    /** Space */
    public static final String SPACE = " ";

    /** SER_NUM_TXT */
    public static final String SER_NUM_TXT = "Serial#";

    // START 2018/03/01 U.Kim [QC#23296, ADD]
    /** DTL_TXT */
    public static final String DTL_TXT = "Details";

    /** Colon */
    public static final String COLON = ":";

    /** Time */
    public static final String TM_TXT = "Time";

    /** Serial Number */
    public static final String SER_NUM_TXT_LOG = "Serial Number";

    // END 2018/03/01 U.Kim [QC#23296, ADD]

    /** DEF_LINE_SUB_NUM */
    public static final String DEF_LINE_SUB_NUM = "001";

    /** Delivery comment Start index */
    public static final int DELY_CMNT_START_IDX = 0;

    /** Delivery comment End index */
    public static final int DELY_CMNT_END_IDX = 240;
    // START 2018/02/19 U.Kim [QC#QC#20297(Sol#379), ADD]
    /**
     * BLANK
     */
    public static final String BLANK = "";

    /**
     * EQUIPMENT_ORDER_VALUE_SET
     */
    public static final String EQUIPMENT_ORDER_VALUE_SET = "EQUIPMENT_ORDER";

    /**
     * EQUIPMENT_ORDER_VALUE_SET
     */
    public static final String SUPPLIES_ORDER_VALUE_SET = "SUPPLIES_ORDER";

    /** New Line */
    public static final String NEW_LINE = "\r\n";

    /** Shipping Comment Limit Size*/
    public static final int SHPG_CMT_TXT_LIMIT_SIZE = 260;
    // END 2018/02/19 U.Kim [QC#QC#20297(Sol#379), ADD]

    /** PKG_UOM_FOR_PRC */
    public static final String PKG_UOM_FOR_PRC = "PKG_UOM_FOR_PRC";

    /** Error mail for Search Toner */
    public static final String MSG_FAIL_SRCH_TNR = "Search for supply is failed";

    /** Mail title for Customer Name Mismatch */
    public static final String MAIL_MAIN_TITL_CUST_MIS = "Customer Name Mismatch";

    /** Main mail title for Customer Name Mismatch */
    public static final String MSG_MAIL_TITL_CUST_MIS = "UGW Customer Name Mismatch";

    /** Main mail title for Duplicate Supply Order */
    public static final String MSG_MAIL_TITL_DUPL_ORD = "Duplicate Supply Order";

    /** API Error for Default Carrier API */
    public static final String MSG_ERR_DFT_API = "Default Carrier API Error";

    /** API Error for Pricing API */
    public static final String MSG_ERR_PRC_API = "Pricing API Error";

    /** API Error for Order Update API */
    public static final String MSG_ERR_CPO_UPDT_API = "DS CPO Update API Error";
}
