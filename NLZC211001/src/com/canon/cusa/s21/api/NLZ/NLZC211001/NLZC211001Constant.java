/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC211001;


/**
 * <pre>
 * Update SO Confirmation
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2011/08/17   CSAI            M.Talahashi     Create          N/A
 * 03/23/2016   CITS            T.Tokutomi      Update          QC#5905
 * 07/27/2016   CSAI            Y.Imazu         Update          QC#594
 * 12/19/2016   CITS            K.Ogino         Update          QC#6562
 * 02/16/2017   CITS            K.Ogino         UPDATE          QC#17393
 * 07/12/2017   CITS            K.Ogino         Update          QC#19831
 * 11/13/2017   CITS            M.Naito         Update          QC#22503
 * 09/03/2019   CITS            K.Ogino         Update          QC#53009
 */
public interface NLZC211001Constant {

    /** Program ID for Log */
    String PROGRAM_ID = " ## NLZC211001 ## ";

    /** Message Type: E */
    String MSG_TYPE_ERROR = "E";

    /** Message Type: W */
    String MSG_TYPE_WARNING = "W";

    /** Please fill out/select the required field. */
    String NLZM2001E = "NLZM2001E";

    /** The data specified does not exist. */
    String NLZM2004E = "NLZM2004E";

    /** The data specified does not exist. */
    String NLZM2005E = "NLZM2005E";

    /** The process abended. */
    String NLZM2006E = "NLZM2006E";

    /** The value you entered is incorrect. */
    String NLZM2007E = "NLZM2007E";

    /** The process abended. */
    String NLZM2008E = "NLZM2008E";

    /** The process abended. */
    String NLZM2009E = "NLZM2009E";

    /** The process abended. */
    String NLZM2010E = "NLZM2010E";

    /** The value you entered is incorrect. */
    String NLZM2011E = "NLZM2011E";

    /** The code you entered cannot be found in the master. */
    String NLZM2029E = "NLZM2029E";

    /** The WH-CD does not match. (Serial) */
    String NLZM2012W = "NLZM2012W";

    /** The MDSE_CD does not match (Serial). */
    String NLZM2013W = "NLZM2013W";

    /** This Serial# is not necessary to be acquired. */
    String NLZM2014W = "NLZM2014W";

    /** The process abended. */
    String NLZM2030E = "NLZM2030E";

    /** Shipping Qty and the Serial Qty does not match. */
    String NLZM2032W = "NLZM2032W";

    /** The data specified does not exist. */
    String NLZM2033W = "NLZM2033W";

    /** This Serial# is redundant. */
    String NLZM2041W = "NLZM2041W";

    /** This Serial# is out of range. */
    String NLZM2042W = "NLZM2042W";

    /**
     * The number of Serial#'s received exceeds the number that has
     * been shipped.
     */
    String NLZM2043W = "NLZM2043W";

    /** The (@) was (@) . ResultCount = (@) */
    String ZZBM0009I = "ZZBM0009I";

    /** Carrier does not exist. */
    String NLZM2044W = "NLZM2044W";

    /** This Serial# is out of length. */
    String NLZM2045W = "NLZM2045W";

    /** This Serial# is redundant.(Return) */
    String NLZM2046W = "NLZM2046W";

    /** An error has occurred in the called API. API ID: [@] */
    String NDMM0012E = "NDMM0012E";

    /** Error has occurred at [@]. */
    String NFCM0576E = "NFCM0576E";

    /** ShipQty and OrdQty does not match.[@] */
    String NLZM2048E = "NLZM2048E";

    /** DB error occurred. */
    String NLBM1064E = "NLBM1064E";

    /** SHPG_ORD_CUST_DTL does not exist. */
    String NLBM1134E = "NLBM1134E";

    /** The value you entered is incorrect. */
    String NLBM1129E = "NLBM1129E";

    /** FROM_STK_STS_CD is abnormal. */
    String NLZM2312E = "NLZM2312E";

    /** Maximum number of digits exceeded.[@] */
    String NPAM1320E = "NPAM1320E";

    /** This SO# does not exist. */
    String NLZM2300E = "NLZM2300E";

    /**
     * The serial number does not include in the specified
     * configuration.[@]
     */
    String NLZM2330W = "NLZM2330W";

    /**
     * All lines of shipping order are not shipped.[@]
     */
    String NLZM2325E = "NLZM2325E";

    /** All components of the configuration are not shipped.[@] */
    String NLZM2326E = "NLZM2326E";

    /** All components of the set item are not shipped.[@] */
    String NLZM2327E = "NLZM2327E";

    /**
     * Shipped quantity does not match each quantity of UOM specified
     * by order.[@]
     */
    String NLZM2328E = "NLZM2328E";

    /** All lines of shipping order are not shipped.[@] */
    String NLZM2329E = "NLZM2329E";

    /** Could not get the RTL_WH_CD from Inventory Location Code */
    String NLZM2503E = "NLZM2503E";

    /** DB item: GLBL_CMPY_CD */
    String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB item: SO_NUM */
    String SO_NUM = "SO_NUM";

    /** DB item: TRANSACTION_ID */
    String WRK_TRX_ID = "WRK_TRX_ID";

    /** DB item: SQ_ID */
    String SQ_ID = "SQ_ID";

    /** DB item: PROC_STS_CD */
    String PROC_STS_CD = "PROC_STS_CD";

    /** DB item: ERR_MSG_CD */
    String ERR_MSG_CD = "ERR_MSG_CD";

    /** DB item: WH_CD */
    String WH_CD = "WH_CD";

    /** DB item: SCE_ORD_TP_CD */
    String SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /** DB item: S80_ORD_TP_CD */
    String S80_ORD_TP_CD = "S80_ORD_TP_CD";

    /** DB item: SO_PROC_STS_CD */
    String SO_PROC_STS_CD = "SO_PROC_STS_CD";

    /** DB item: SO_DATA_ACT_TP_CD */
    String SO_DATA_ACT_TP_CD = "SO_DATA_ACT_TP_CD";

    /** DB item: SHIP_DT_TM_TS */
    String SHIP_DT_TM_TS = "SHIP_DT_TM_TS";

    /** DB item: TOT_SHIP_WT */
    String TOT_SHIP_WT = "TOT_SHIP_WT";

    /** DB item: TOT_FRT_AMT */
    String TOT_FRT_AMT = "TOT_FRT_AMT";

    /** DB item: SRC_TP_CD */
    String SRC_TP_CD = "SRC_TP_CD";

    /** DB item: SO_SLP_NUM */
    String SO_SLP_NUM = "SO_SLP_NUM";

    /** DB item: PRO_NUM */
    String PRO_NUM = "PRO_NUM";

    /** DB item: VND_CD */
    String VND_CD = "VND_CD";

    /** DB item: MDSE_CD */
    String MDSE_CD = "MDSE_CD";

    /** DB item: FROM_STK_STS_CD */
    String FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /** DB item: S80_STK_STS_CD */
    String S80_STK_STS_CD = "S80_STK_STS_CD";

    /** DB item: BOL_NUM */
    String BOL_NUM = "BOL_NUM";

    /** DB item: SHIP_QTY */
    String SHIP_QTY = "SHIP_QTY";

    /** DB item: UCC128_CD */
    String UCC128_CD = "UCC128_CD";

    /** DB item: SO_SER_ID */
    String SO_SER_ID = "SO_SER_ID";

    /** DB item: SER_NUM */
    String SER_NUM = "SER_NUM";

    /** DB item: SER_TAKE_DT_TM_TS */
    String SER_TAKE_DT_TM_TS = "SER_TAKE_DT_TM_TS";

    /** DB item: SER_INTFC_PROC_STS_CD */
    String SER_INTFC_PROC_STS_CD = "SER_INTFC_PROC_STS_CD";

    /** DB item: SER_INTFC_ERR_CD */
    String SER_INTFC_ERR_CD = "SER_INTFC_ERR_CD";

    /** DB item: COUNT */
    String CNT = "CNT";

    /** DB item: BOL_SQ_NUM */
    String BOL_SQ_NUM = "BOL_SQ_NUM";

    /** DB item: SHPG_STS_CD */
    String SHPG_STS_CD = "SHPG_STS_CD";

    /** DB item: RWS_NUM */
    String RWS_NUM = "RWS_NUM";

    /** DB item: TRX_HDR_NUM */
    String TRX_HDR_NUM = "TRX_HDR_NUM";

    /** DB item: TRX_LINE_NUM */
    String TRX_LINE_NUM = "TRX_LINE_NUM";

    /** DB item: SYS_SRC_CD */
    String SYS_SRC_CD = "SYS_SRC_CD";

    /** SHPG_ORD_CONF_WRK */
    String SHPG_ORD_CONF_WRK = "SHPG_ORD_CONF_WRK";

    /** SHPG_ORD_CONF_DTL_WRK */
    String SHPG_ORD_CONF_DTL_WRK = "SHPG_ORD_CONF_DTL_WRK";

    /** SHIP_SER_NUM_WRK */
    String SHIP_SER_NUM_WRK = "SHIP_SER_NUM_WRK";

    /** SHPG_SVC_LVL_CD */
    String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** SO_PROC_STS */
    String TBL_SO_PROC_STS = "SO_PROC_STS";

    /** Data Created */
    String DATA_PROCESSED = "processed";

    /** Vendor Type Code: SCAC */
    String VND_TP_CD_SCAC = "04";

    /** Shipping Plan Update API Mode */
    String SHPG_MODE_HARDALLOCATED_SOCANCELLED = "03";

    /** Shipping Plan Update API Mode */
    String SHPG_MODE_INSHED = "11";

    /** Shipping Plan Update API Mode */
    String SHPG_MODE_SHIPPED = "12";

    /** Shipping Plan Update API Mode */
    String SHPG_MODE_SHIPPED_SOCLOSE = "14";

    /** Shipping Plan Update API Mode : Partial Ship */
    String SHPG_MODE_PARTIAL_SHIP = "25";

    /** Vendor Return Update API Mode(Closed) */
    String VND_RTRN_MODE_CLOSED = "15";

    /** Vendor Return Update API Mode(Cancelled) */
    String VND_RTRN_MODE_CANCELLED = "09";

    /** DB Access Component Return CD: Normal End */
    String RETURN_CD_NORMAL_END = "0000";

    /** DB Access Component Return CD: Duplicate Key Error */
    String RETURN_CD_PRIMARY_KEY_DUPLICATE = "2300";

    /** DB Access Component Return CD: No Data */
    String RETURN_CD_NO_DATA = "2000";

    /** Max Value: TOT_SHIP_WT */
    String MAX_VALUE_TOT_SHIP_WT = "9999999.99";

    /** Detail Shipping Status */
    public enum DetailShippingStatus {

        /** All details are Shipped */
        ALL_SHIPPED,

        /** All details are Inshed */
        ALL_INSHED,

        /** All details are S/O Cancelled */
        ALL_CANCELLED,

        /** All details are Shipped or S/O Cancelled */
        SHIPPED_CANCELLED_MIX,

        /** All details are Inshed or S/O Cancelled */
        INSHED_CANCELLED_MIX,

        /** Other (Not all details are closed) */
        NOT_ALL_CLOSE

    }

    /** ANSI Transaction#(EDI856) */
    String ANSI_TRX_NUM_EDI_856 = "856";

    /** TRD_PTNR_ASN_STRU_LVL_NUM(3Level) */
    String TRD_PTNR_ASN_STRU_LVL_NUM_3 = "3";

    /** EDI customer type code(SHIP TO) */
    String EDI_CUST_TP_SHIP_TO = "2";

    /** EDI customer type code(SELL TO) */
    String EDI_CUST_TP_SELL_TO = "1";

    /** EDI_TRX_ID Max Length */
    int EDI_TRX_ID_MAX_LG = 30;

    /** EDI_SQ_ID Max Length */
    int EDI_SQ_ID_MAX_LG = 10;

    /** DB Return CD: Normal End */
    String DB_RETURN_CD_NORMAL_END = "0000";

    /** */
    String EDI_INTFC_ID_ASN = "ASN";

    /** DB item: BOL_NUM */
    String SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** Transport Type Code for level3 */
    String ASN_TRNSP_TP_LEVEL3 = "ZZZ";

    /** Parent UCC code for lovel3 */
    String PARENT_UCC_CD_LEVEL3 = "-";

    /** xxMsgIdListSize */
    int MSG_ID_LIST_SIZE = 1000;

    /** Inventory Order API Process Mode CLOSED */
    String IOA_CLOSED = "3";

    /** Inventory Order API Process Mode CANCEL */
    String IOA_CANCEL = "4";

    /** Inventory Order API Process Mode HEADER CLOSE */
    String IOA_HEADER_CLOSED = "5";

    /** Inventory Order API DATA TYPE DETAIL */
    String IOA_DATA_TYPE_DETAIL = "D";

    /** Inventory Order API DATA TYPE DETAIL */
    String IOA_DATA_TYPE_HEADER = "H";

    /** PR Update API Process Mode UPDATE */
    String PRUA_UPDATE = "2";

    /** PR Update API Process Mode CANCEL */
    String PRUA_CANCEL = "3";

    /** PR Update API Event ID Shipped */
    String PRUA_EVENT_SHIPPED = "6";

    /** machine master shipping check */
    String MM_NO_SHIP_TARGET = "noTarget";

    /** Serial Trx API Error Status : 600 : Duplication Error */
    String SERIAL_TRX_ERROR_DUPLICATE = "600";

    /** Serial Trx API Error Status : 900 : Not Scheduled */
    String SERIAL_TRX_ERROR_NOT_SCHEDULE = "900";

    /** Serial Trx API Error Status : 550 : Stock Status Error */
    String SERIAL_TRX_ERROR_STK_STS_CD = "550";

    /** Machine Master Status : check column */
    int MACH_MSTR_STATUS_CHECK_COLUMN = 10;

    /** Machine Master Status : check column */
    int MM_CHECK_ALLOCATION = 0;

    /** Machine Master Status : check column */
    int MM_CHECK_EXIST_SERIAL = 1;

    /** Machine Master Status : check column */
    int MM_CHECK_SHIP_SERIAL = 2;

    /** Machine Master Status : check column */
    int MM_CHECK_MM_STATUS_TRMN = 3;

    /** Machine Master Status : check column */
    int MM_CHECK_MM_STATUS_CRAT = 4;

    /** Machine Master Status : check column */
    int MM_CHECK_OTHER_ALLOCATION = 5;

    /** Machine Master Status : check column */
    int MM_CHECK_LOCATION = 6;

    /** Machine Master Status : check column */
    int MM_CHECK_CONFIG = 7;

    /** Machine Master Status : check column */
    int MM_CHECK_ORDER_CONFIG = 8;

    /** Machine Master Status : check column */
    int MM_CHECK_STK_STS = 9;

    /** Machine Master Check Pattern : on schedule */
    String MM_CHK_PTR_ON_SCHD = "Y";

    /** Machine Master Check Pattern : other order */
    String MM_CHK_PTR_OTHR_ORD = "NYYYYY";

    /** Machine Master Check Pattern : wh */
    String MM_CHK_PTR_WH = "NYYYYNY";

    /** Machine Master Check Pattern : config item & other wh */
    String MM_CHK_PTR_CONFIG_ITEM_OTHER_WH = "NYYYYNNYY";

    /** Machine Master Check Pattern : other config item & other wh */
    String MM_CHK_PTR_OTHR_ITEM_OTHR_WH = "NYYYYNNYN";

    /** Machine Master Check Pattern : other wh */
    String MM_CHK_PTR_OTHER_WH = "NYYYYNNN";

    /** Machine Master Check Pattern : in customer */
    String MM_CHK_PTR_IN_CUST = "NYYYN";

    /** Machine Master Check Pattern : no IB */
    String MM_CHK_PTR_NO_IB = "NYYN";

    /** Machine Master Check Pattern : other order no ship serial */
    String MM_CHK_PTR_OTHR_ORD_NSS = "NYNYYY";

    /** Machine Master Check Pattern : wh */
    String MM_CHK_PTR_WH_NSS = "NYNYYNY";

    /** Machine Master Check Pattern : config item & other wh */
    String MM_CHK_PTR_CONFIG_ITEM_OTHER_WH_NSS = "NYNYYNNYY";

    /** Machine Master Check Pattern : other config item & other wh */
    String MM_CHK_PTR_OTHR_ITEM_OTHR_WH_NSS = "NYNYYNNYN";

    /** Machine Master Check Pattern : other wh */
    String MM_CHK_PTR_OTHER_WH_NSS = "NYNYYNNN";

    /** Machine Master Check Pattern : in customer */
    String MM_CHK_PTR_IN_CUST_NSS = "NYNYN";

    /** Machine Master Check Pattern : no IB */
    String MM_CHK_PTR_NO_IB_NSS = "NYNN";

    /** Machine Master Check Pattern : other order no serial */
    String MM_CHK_PTR_OTHR_ORD_NS = "NNNYYY";

    /** Machine Master Check Pattern : wh */
    String MM_CHK_PTR_WH_NS = "NNNYYNY";

    /** Machine Master Check Pattern : config item & other wh */
    String MM_CHK_PTR_CONFIG_ITEM_OTHER_WH_NS = "NNNYYNNYY";

    /** Machine Master Check Pattern : other config item & other wh */
    String MM_CHK_PTR_OTHR_ITEM_OTHR_WH_NS = "NNNYYNNYN";

    /** Machine Master Check Pattern : other wh */
    String MM_CHK_PTR_OTHER_WH_NS = "NNNYYNNN";

    /** Machine Master Check Pattern : in customer */
    String MM_CHK_PTR_IN_CUST_NS = "NNNYN";

    /** Machine Master Check Pattern : no IB */
    String MM_CHK_PTR_NO_IB_NS = "NNNN";

    /** Serial Trx comment : NLBM1302W */
    String SER_TRX_CMNT_NLBM1302W = "NLBM1302W Serial Number which is different from scheduled serial is shipped.";

    /** Serial Trx comment : NLZM2317E */
    String SER_TRX_CMNT_NLZM2317E = "NLZM2317E The entered Serial Number is already allocated by other order.";

    /** Serial Trx comment : NLZM2318E */
    String SER_TRX_CMNT_NLZM2318E = "NLZM2318E The entered Serial Number is located at customer site.";

    /** Serial Trx comment : NLZM2324E */
    String SER_TRX_CMNT_NLZM2324E = "NLZM2324E The specified serial number is the component of the other configuration.";

    /** Serial Trx comment : Stock Status Error */
    String SER_TRX_CMNT_NLZM2414E = "NLZM2414E Stock status of the specified Serial number is different from IB.";

    /** DS_CONST_GRP_ID : NLZC2110_SHIP_CTRL */
    String DS_CONST_GRP_ID = "NLZC2110_SHIP_CTRL";

    /** RWS Reference Number Length */
    int RWS_REF_NUM_LEN = 15;

    /** LF_PAD_CHAR */
    String LF_PAD_CHAR = "0";

    /** Loan Judge : ORD_TP_RS */
    String ORD_TP_RS = "RS";

    /** Loan Judge : ORD_TP_RS_LOAN */
    String ORD_TP_RS_LOAN = "LOAN";

    /** Loan Judge : ORD_TP_RS_LOAN_EXP */
    String ORD_TP_RS_LOAN_EXP = "LOAN_EXP";

    /** Format : yyyyMMdd */
    String FORMAT_YYYYMMDD = "yyyyMMdd";

    //2017/11/13 M.Naito Add QC#22503 START
    /** COMMA */
    String COMMA = ",";

    /** PRO_NUM_LENGTH */
    int PRO_NUM_LENGTH = 30;
    //2017/11/13 M.Naito Add QC#22503 END

    /** PRCH_REQ_NUM */
    String PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** PRCH_REQ_LINE_NUM */
    String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** PRCH_REQ_LINE_SUB_NUM */
    String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /** Machine Master Check Pattern : wh */
    String MM_CHK_PTR_WH_ITEM_NSS = "NYNYYNYNY";

    /** Machine Master Check Pattern : In WH other config  */
    String MM_CHK_PTR_WH_OTHER_CONFIG = "NYYYYNYYN";

    /** Machine Master Check Pattern : In WH other config item */
    String MM_CHK_PTR_WH_OTHER_CONFIG_ITEM = "NYYYYNYNN";

    /** Machine Master Check Pattern : Other WH config item */
    String MM_CHK_PTR_OTHER_WH_CONFIG_ITEM = "NYYYYNNNN";

    /** Machine Master Check Pattern : In WH other config */
    String MM_CHK_PTR_WH_OTHER_CONFIG_NSS = "NYNYYNYYN";

    /** Machine Master Check Pattern : In WH other config item */
    String MM_CHK_PTR_WH_OTHER_CONFIG_ITEM_NSS = "NYNYYNYNN";

    /** Machine Master Check Pattern :  Other WH config item */
    String MM_CHK_PTR_OTHER_WH_CONFIG_ITEM_NSS = "NYNYYNNNN";

    /** Machine Master Check Pattern : In WH other config */
    String MM_CHK_PTR_WH_OTHER_CONFIG_NS = "NNNYYNYYN";

    /** Machine Master Check Pattern : In WH other config item */
    String MM_CHK_PTR_WH_OTHER_CONFIG_ITEM_NS = "NNNYYNYNN";

    /** Machine Master Check Pattern :  Other WH config item */
    String MM_CHK_PTR_OTHER_WH_CONFIG_ITEM_NS = "NNNYYNNNN";

    /** DB item: PO_RCV_NUM */
    String PO_RCV_NUM = "PO_RCV_NUM";

    /** DB item: PO_RCV_LINE_NUM */
    String PO_RCV_LINE_NUM = "PO_RCV_LINE_NUM";

    /** QC#53009. NLZM2521E : The Location Qty is a negative number. */
    String NLZM2521E = "NLZM2521E";

    /** QC#53009. NLZM0040W : The Location Qty is a negative number. (Warning) */
    String NLZM0040W = "NLZM0040W";
}
