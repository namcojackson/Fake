/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020.constant;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 05/03/2016   CSAI            Y.Imazu         Update          QC#5771
 * 02/16/2018   CITS            T.Tokutomi      Update          QC#18367
 *</pre>
 */
public class NLBL2020Constant {

    /** BUSINESS_ID */
    public static final String BUSINESS_ID = "NLBL2020";

    /** Screen ID */
    public static final String SCRN_ID = "NLBL2020Scrn00";

    /** FUNC_SRCH */
    public static final String FUNC_SRCH = "20";

    /** FUNC_UPD */
    public static final String FUNC_UPD = "40";

    /** FUNC_PRNT */
    public static final String FUNC_PRNT = "70";

    /** TAB_SO_LIST */
    public static final String TAB_SO_LIST = "SoList";

    /** TAB_PICK_LIST */
    public static final String TAB_PICK_LIST = "PickList";

    /** TXT_SERIAL_NUM */
    public static final String TXT_SERIAL_NUM = "serNum_A1";

    /** ROLE_INQUIRY */
    public static final String ROLE_INQUIRY = "NLBL2020T010";

    /** ROLE_UPDATE */
    public static final String ROLE_UPDATE = "NLBL2020T020";

    /** ROLE_ALL_WH_PERMISSION */
    public static final String ROLE_ALL_WH_PERMISSION = "NLBL2020T030";

    // =================================================
    // Button
    // [0]:Button Name [1]:Event Name [2]:Button Level
    // =================================================

    /** Function Button 1 */
    public static final String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Function Button 2 */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    public static final String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Function Button 4 */
    public static final String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Function Button 5 */
    public static final String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Function Button 6 */
    public static final String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_DETELE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** BTN_SAVE_SEARCH */
    public static final String BTN_SAVE_SEARCH = "Save_Search";

    /** BTN_DELETE_SEARCH */
    public static final String BTN_DELETE_SEARCH = "Delete_Search";

    /** BTN_SEARCH_RTL_WH */
    public static final String BTN_SEARCH_RTL_WH = "Search_RtlWHInfo";

    /** BTN_SEARCH_RTL_SWH */
    public static final String BTN_SEARCH_RTL_SWH = "Search_RtlSWHInfo";

    /** BTN_SEARCH_SHIP_TO_CUST */
    public static final String BTN_SEARCH_SHIP_TO_CUST = "Search_ShipToCustInfo";

    /** BTN_SEARCH_CARRIER */
    public static final String BTN_SEARCH_CARRIER = "Search_CarrInfo";

    /** BTN_SEARCH_MDSE_INFO */
    public static final String BTN_SEARCH_MDSE_INFO = "Search_MdseInfo";

    /** BTN_SEARCH_MDSE_INFO */
    public static final String BTN_SEARCH = "Search";

    /** BTN_SELECT_ALL */
    public static final String BTN_SELECT_ALL = "Select_All";

    /** BTN_UN_SELECT_ALL */
    public static final String BTN_UN_SELECT_ALL = "UnSelect_All";

    /** BTN_PRINT */
    public static final String BTN_PRINT = "Print";

    /** BTN_APPLY */
    public static final String BTN_APPLY = "OnClick_Apply";

    /** BTN_CANCLE */
    public static final String BTN_CANCLE = "Cancel";

    /** BTN_SHIP */
    public static final String[] BTN_SHIP = {"Ship", "Ship", "Ship Confirm" };

    /** BTN_COMPLETE */
    public static final String BTN_SO_CLOSE = "SO_Close";

    /** BTN_SERIAL */
    public static final String BTN_SERIAL = "OpenWin_SerEntry";

    /** BTN_OpenWin_CarrInfo */
    public static final String BTN_CARRIER = "OpenWin_CarrInfo";

    // QC#18367 Add
    /** BTN_OpenWin_CarrInfo */
    public static final String BTN_CUSTOM_DOC_PRINT = "CustomDocPrint";

    // =================================================
    // HTML ID
    // =================================================

    /** HTML_ID_HEAD_CHK_BOX */
    public static final String HTML_ID_HEAD_CHK_BOX = "headChkBox";

    /** HTML_ID_SRC_ORDER_LINK */
    public static final String HTML_ID_SRC_ORDER_LINK = "openSrcOrdEntry";

    /** HTML_ID_SUB_CHK_BOX */
    public static final String HTML_ID_SUB_CHK_BOX = "subChkBox";

    /** HTML_ID_SRC_ORD_NUM */
    public static final String HTML_ID_SRC_ORD_NUM = "sourceOrderNum";

    /** HTML_ID_SRC_ORD_LINE_NUM */
    public static final String HTML_ID_SRC_ORD_LINE_NUM = "srcOrdLineNum";

    /** HTML_ID_SO_NUM */
    public static final String HTML_ID_SO_NUM = "soNum";

    /** HTML_ID_SO_LINE_NUM */
    public static final String HTML_ID_SO_LINE_NUM = "soLineNum";

    /** HTML_ID_SHPG_ORD_TP */
    public static final String HTML_ID_SHPG_ORD_TP = "shpgOrdTp";

    /** HTML_ID_RTL_WH_NM */
    public static final String HTML_ID_RTL_WH_NM = "rtlWhNm";

    /** HTML_ID_RTL_SWH_NM */
    public static final String HTML_ID_RTL_SWH_NM = "rtlSWhNm";

    /** HTML_ID_SHPG_ORD_STS */
    public static final String HTML_ID_SHPG_ORD_STS = "shpgOrdSts";

    /** HTML_ID_SHIP_CONF */
    public static final String HTML_ID_SHIP_CONF = "shipConf";

    /** HTML_ID_PICK_CONF */
    public static final String HTML_ID_PICK_CONF = "pickConf";

    /** HTML_ID_MDSE_CD */
    public static final String HTML_ID_MDSE_CD = "mdseCd";

    /** HTML_ID_MDSE_NM */
    public static final String HTML_ID_MDSE_NM = "mdseNm";

    /** HTML_ID_BACK_ORD_IMPCT */
    public static final String HTML_ID_BACK_ORD_IMPCT = "backOrdImpct";

    /** HTML_ID_STK_STS_CD */
    public static final String HTML_ID_STK_STS_CD = "stkStsCd";

    /** HTML_ID_SHPG_QTY */
    public static final String HTML_ID_SHPG_QTY = "shpgQty";

    /** HTML_ID_UOM */
    public static final String HTML_ID_UOM = "uom";

    /** HTML_ID_IN_EACH_QTY */
    public static final String HTML_ID_IN_EACH_QTY = "inEachQty";

    /** HTML_ID_PICK_CONF_QTY */
    public static final String HTML_ID_SHIP_QTY = "shipQty";

    /** HTML_ID_PICK_CONF_QTY */
    public static final String HTML_ID_PICK_CONF_QTY = "pickConfQty";

    /** HTML_ID_TRX_HDR_NUM_B1 */
    public static final String HTML_ID_TRX_HDR_NUM_B1 = "trxHdrNum_B1";

    /** HTML_ID_DPLY_LINE_NUM_B1 */
    public static final String HTML_ID_DPLY_LINE_NUM_B1 = "dplyLineNum_B1";

    /** HTML_ID_SO_NUM_B1 */
    public static final String HTML_ID_SO_NUM_B1 = "soNum_B1";

    /** HTML_ID_SO_SLP_NUM_B1 */
    public static final String HTML_ID_SO_SLP_NUM_B1 = "soSlpNum_B1";

    /** HTML_ID_RTL_WH_NM_B1 */
    public static final String HTML_ID_RTL_WH_NM_B1 = "rtlWhNm_B1";

    /** HTML_ID_RTL_SWH_CD_B1 */
    public static final String HTML_ID_RTL_SWH_CD_B1 = "shipFromRtlSwhCd_B1";

    /** HTML_ID_PICK_CONF_MSTR_PK_B1 */
    public static final String HTML_ID_PICK_CONF_MSTR_PK_B1 = "pickSvcConfigMstrPk_B1";

    /** HTML_ID_MDSE_CD_B1 */
    public static final String HTML_ID_MDSE_CD_B1 = "mdseCd_B1";

    /** HTML_ID_MDSE_NM_B1 */
    public static final String HTML_ID_MDSE_NM_B1 = "mdseDescShortTxt_B1";

    /** HTML_ID_SHPG_QTY_B1 */
    public static final String HTML_ID_SHPG_QTY_B1 = "shpgQty_B1";

    /** HTML_ID_SHIP_QTY_B1 */
    public static final String HTML_ID_SHIP_QTY_B1 = "shipQty_B1";

    /** HTML_ID_PICK_CONF_QTY_B1 */
    public static final String HTML_ID_PICK_CONF_QTY_B1 = "pickConfQty_B1";

    // =================================================
    // Display Name
    // =================================================

    /** Source WH */
    public static final String DISPLAY_RTL_WH_CD = "Source WH";

    /** Source SWH */
    public static final String DISPLAY_SWH_CD = "Source SWH";

    /** Item Number */
    public static final String DISPLAY_MDSE_CD = "Item Number";

    /** Carrier */
    public static final String DISPLAY_CARR_CD = "Carrier";

    /** Ship to Customer */
    public static final String DISPLAY_SHIP_TO_CUST_CD = "Ship to Customer";

    /** Search Option Name */
    public static final String DISPLAY_SRCH_OPT_NM = "Search Option Name";

    /** Save Search Options */
    public static final String DISPLAY_SAVE_SRCH_OPT = "Save Search Options";

    /** Shipping Order Creation Date To */
    public static final String DISPLAY_SHIP_ORD_DT_TO = "Shipping Order Creation Date To";

    /** Shipping Order Creation Date From */
    public static final String DISPLAY_SHIP_ORD_DT_FROM = "Shipping Order Creation Date From";

    /** Request Delivery Date To */
    public static final String DISPLAY_RDD_DT_TO = "Request Delivery Date To";

    /** Request Delivery Date From */
    public static final String DISPLAY_RDD_DT_FROM = "Request Delivery Date From";

    /** Need By Date To */
    public static final String DISPLAY_NEED_BY_DT_TO = "Need By Date To";

    /** Need By Date From */
    public static final String DISPLAY_NEED_BY_DT_FROM = "Need By Date From";

    /** Schedule Delivery Date From */
    public static final String DISPLAY_DETAIL_CHKBOX = "Detail Line Check";

    // =================================================
    // Popup Parameter
    // =================================================

    /** Code Column for NMAL6050 */
    public static final String TBL_NM_FOR_OTBD_CARR_V = "OTBD_CARR_V";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_CARR_CD = "CARR_CD";

    /** Code Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_CARR_NM = "CARR_NM";

    /** Code Column for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_CARR_SORT_NUM = "CARR_SORT_NUM";

    /** Code Column for NMAL6050 */
    public static final String SCR_NM_FOR_PRNT_CARRIER = "Carrier Popup";

    /** Code Column for NMAL6050 */
    public static final String HDR_CD_LB_NM_FOR_CARRIER_CD = "Carrier Code";

    /** Code Column for NMAL6050 */
    public static final String HDR_NM_LB_NM_FOR_CARRIER_NM = "Carrier Name";

    /** Code Column for NMAL6050 */
    public static final String DTL_CD_LB_NM_FOR_CARRIER_CD = "Carrier Code";

    /** Code Column for NMAL6050 */
    public static final String DTL_NM_LB_NM_FOR_CARRIER_NM = "Carrier Name";

    /** Code Column for NMAL6800 */
    public static final String MDSE_CD_TARGET_ALL = "AL";

    /** Code Column for NLBL3000 */
    public static final String MODE_INQUIRY = "2";

    /** Code Column for NLBL3000 */
    public static final String MODE_UPDATE = "1";

    /** Code Column for NMAL6780 */
    public static final String MODE_SHIP_TO_ONLY = "53";

    /** RTRN_CD_RS */
    public static final String RTRN_CD_RS = "RS";

    /** RTRN_CD_KT */
    public static final String RTRN_CD_KT = "KT";

    /** RTRN_CD_IC */
    public static final String RTRN_CD_IC = "IC";

    /** RTRN_CD_SC */
    public static final String RTRN_CD_SC = "SC";

    /** RTRN_CD_SW */
    public static final String RTRN_CD_SW = "SW";

    /** RTRN_CD_CC */
    public static final String RTRN_CD_CC = "CC";

    /**  RTRN_CD_DT */
    public static final String RTRN_CD_DT = "DT";

    /** RTRN_CD_TR */
    public static final String RTRN_CD_TR = "TR";

    /** RTRN_CD_RM */
    public static final String RTRN_CD_RM = "RM";

    /** xxOpsTp: Inquiry (NLBL3030 Parameter) */
    public static final String XX_OPS_TP_INQUIRY = "I";

    /** Display Hierarchy Accounts Code: 03 */
    public static final String DISP_HRCH_ACCT_CD_SHIP = "03";

    // =================================================
    // Event Name
    // =================================================

    /** Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** OpenWin_CarrInfo */
    public static final String EVENT_NM_NLBL2020SCRN00_OPENWIN_CARRINFO = "OpenWin_CarrInfo";

    /** OpenWin_AplyCarrCode */
    public static final String EVENT_NM_NLBL2020SCRN00_OPENWIN_APLYCARRCODE = "OpenWin_AplyCarrCode";

    /** Open_Location_Info_Popup */
    public static final String EVENT_NM_OPENWIN_LOCINFO_RTLWH = "Open_Location_Info_Popup";

    /** Open_Location_Info_Popup_Swh */
    public static final String EVENT_NM_OPENWIN_LOCINFO_RTLSWH = "Open_Location_Info_Popup_Swh";

    // =================================================
    // Message Code
    // =================================================

    /** The field of [@] is not input. */
    public static final String ZZZM9007E = "ZZZM9007E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** The process [@] has been successfully completed. */
    public static final String NLZM2274E = "NLZM2274E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** Please set at least one search criteria. */
    public static final String NLZM2276E = "NLZM2276E";

    /** The value for [@] must be bigger than [@]. */
    public static final String NLZM2277E = "NLZM2277E";

    /** Details require more than 1 row. Please enter. */
    public static final String NLBM0002E = "NLBM0002E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** NLZM2284E : Select all details with the same Config Number. [@] */
    public static final String NLZM2284E = "NLZM2284E";

    /** Your request cannot be processed under this status. */
    public static final String NLZM2283E = "NLZM2283E";

    /** Entered time is incorrect. */
    public static final String NWAM0665E = "NWAM0665E";

    /** Multiple Shipping Order have been selected. Please select a single Shipping Order. */
    public static final String NLBM1357E = "NLBM1357E";

    /** When Source SWH is entered, Source WH also needs to be entered. */
    public static final String NLBM1361E = "NLBM1361E";
    // =================================================
    // Constant Value
    // =================================================

    /** Hour minute string length : HHMM */
    public static final int HOUR_MINUTE_STR_LENGTH = 4;

    /** half day hours */
    public static final int HALF_DAY_HOURS = 12;

    /** One day hours */
    public static final int ONE_DAY_HOURS = 24;

    /** One hour minutes */
    public static final int ONE_HOUR_MINUTES = 60;

    /** Time : AM */
    public static final String TIME_AM = "AM";

    /** Time : PM */
    public static final String TIME_PM = "PM";
}
