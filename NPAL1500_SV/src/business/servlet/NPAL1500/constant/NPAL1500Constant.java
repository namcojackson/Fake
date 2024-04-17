package business.servlet.NPAL1500.constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;

/**
 * <pre>
 * Business ID : NPAL1500 PO Entry
 * Function Name : Constant Definition
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/15/2015   CITS            N Akaishi       Create          N/A
 * 09/23/2016   CITS            S.Endo          Update          QC#11985
 * 11/13/2017   Hitachi         Y.Takeno        Update          QC#21849(Sol#218)
 * 01/09/2018   CITS            K.Kameoka       Update          QC#18602(Sol#102)
 * 2018/02/08   CITS            K.Ogino         Update          QC#21169
 * 09/25/2018   CITS            K.Kameoka       Update          QC#28226
 * 03/20/2019   Fujitsu         T.Ogura         Update          QC#30769
 * 05/21/2019   Fujitsu         T.Ogura         Update          QC#50138
 * 11/11/2019   Fujitsu         T.Ogura         Update          QC#54588
 * 04/21/2021   CITS            J.Evangelista   Update          QC#57102
 * 2022/05/16   Hitachi         A.Kohinata      Update          QC#57934
 * 2023/02/15   Hitachi         S.Dong          Update          QC#60966
 * 2023/04/19   Hitachi         TZ.Win          Update          QC#61299
 *</pre>
 */
public class NPAL1500Constant {

    /** Business ID */
    public static final String BIZ_ID = "NPAL1500";

    /** ScreenID */
    public static final String SCREEN_ID = "NPAL1500Scrn00";

    /** Function Code : Search */
    public static final String FUNCTION_CD_SEARCH = "20";

    /** Function Code : Update */
    public static final String FUNCTION_CD_UPDATE = "40";

    /** Function Code : Print */
    public static final String FUNCTION_CD_PRINT = "70";

    /** Button Close Button Event Name */
    public static final String BTN_CMN_CLOSE_EVENT_NM = "CMN_Close";

    /** Button Close Button Event Name */
    public static final String BTN_CMN_CLOSE_LABEL = "Close";

    // ----------- TAB -----------
    /** Tab anchor Header */
    public static final String TAB_HEADER = "Header";

    /** Tab anchor Additional Header */
    public static final String TAB_ADDL_HEADER = "TAB_AddlHeader";

    /** Tab anchor Line Detail */
    public static final String TAB_DETAIL = "TAB_Detail";

    // QC#18602 ADD Start
    /** Tab anchor AP Invoice */
    public static final String TAB_AP_INVOICE = "TAB_APInvoice";
    // QC#18602 ADD End
    
    // ----------- Event Button -----------
    /** Button OrderCancel Button Event Name */
    public static final String BTN_CANCEL_EVENT_NM = "Cancel";

    /** OnChange OnChange_PoType Event Name */
    public static final String ONCHANGE_PO_TYPE_EVENT_NM = "OnChange_PoType";

    /** Button OpenWin_Supplier Button Event Name */
    public static final String BTN_OPEN_SUPPLIER_EVENT_NM = "OpenWin_Supplier";

    /** Button OpenWin_WH(Warehouse) Button Event Name */
    public static final String BTN_OPENWIN_WH_EVENT_NM = "OpenWin_WH";

    /** Button ShipToCust Button Event Name */
    public static final String BTN_SHIP_TO_CUST_EVENT_NM = "OpenWin_ShipToCust";

    /** Button Set_ShipToAddress Event Name */
    public static final String SET_SHIP_TO_ADDRESS_EVENT_NM = "Set_ShipToAddress";

    /** Button Set_CSA_Return_Address Event Name */
    public static final String SET_CSA_RETURN_ADDRESS_EVENT_NM = "Set_CSA_Return_Address";

    /** Button OpenWin_Carrier Button Event Name */
    public static final String BTN_OPENWIN_CARRIER_EVENT_NM = "OpenWin_Carrier";

    /** Button OpenWin_AppvlHistory Button Event Name */
    public static final String BTN_OPENWIN_APPVL_HISTORY_EVENT_NM = "OpenWin_AppvlHistory";

    /** Button DeriveItemInfo Button Event Name */
    public static final String BTN_DERIVE_ITEM_INFO_EVENT_NM = "DeriveItemInfo";

    /** Button DeriveItemInfoBB Button Event Name */
    public static final String BTN_DERIVE_ITEM_INFO_BB_EVENT_NM = "DeriveItemInfoBB";

    /** Button OpenWin_TextEntry Button Event Name */
    public static final String BTN_OPENWIN_PO_TEXT_ENTRY_EVENT_NM = "OpenWin_TextEntry";

    /** Button GetAccountInfo Button Event Name */
    public static final String BTN_GET_ACCOUNT_INFO_EVENT_NM = "GetAccountInfo";

    /** Button OpenWin_SWH(Sub Warehouse) Button Event Name */
    public static final String BTN_OPENWIN_SWH_EVENT_NM = "OpenWin_SWH";

    /** Button OpenWin_ConfigID Button Event Name */
    public static final String BTN_OPENWIN_CONFIG_ID_EVENT_NM = "OpenWin_ConfigID";

    /** Button ApplyConfig Button Event Name */
    public static final String APPLY_CONFIG_EVENT_NM = "ApplyConfig";

    /** Button OpenWin_Account Button Event Name */
    public static final String BTN_OPENWIN_ACCOUNT_EVENT_NM = "OpenWin_Account";

    /** Button OpenWin_SerEnt Button Event Name */
    public static final String BTN_OPENWIN_SERIAL_EVENT_NM = "OpenWin_SerEnt";

    /** Button OpenWin_Component Button Event Name */
    public static final String BTN_OPENWIN_COMPONENT_EVENT_NM = "OpenWin_Component";

    /** Button OpenWin_History(PO History) Button Event Name */
    public static final String BTN_PO_HISTORY_EVENT_NM = "OpenWin_History";

    /** Button OnBlur_TrsmtMethod Button Event Name */
    public static final String ONBLUR_TRANSMIT_METHOD_EVENT_NM = "OnBlur_TrsmtMethod";

    /** Button Print Button Event Name */
    public static final String BTN_PRINT_EVENT_NM = "Print";

    /** Button OpenWin_Receive Button Event Name */
    public static final String BTN_OPENWIN_RECEIVE_EVENT_NM = "OpenWin_Receive";

    /** Button OpenWin_LocationFromWH Button Event Name */
    public static final String BTN_OPENWIN_LOCATION_FROM_WH_EVENT_NM = "OpenWin_LocationFromWH";

    /** Button OpenWin_LocationToWH Button Event Name */
    public static final String BTN_OPENWIN_LOCATION_TO_WH_EVENT_NM = "OpenWin_LocationToWH";

    /** Button OpenWin_LocationFromSWH Button Event Name */
    public static final String BTN_OPENWIN_LOCATION_FROM_SWH_EVENT_NM = "OpenWin_LocationFromSWH";

    /** Button OpenWin_LocationToSWH Button Event Name */
    public static final String BTN_OPENWIN_LOCATION_TO_SWH_EVENT_NM = "OpenWin_LocationToSWH";

    /** Button OpenWin_LocationFromDetailSWH Button Event Name */
    public static final String BTN_OPENWIN_LOCATION_FROM_DTL_SWH_EVENT_NM = "OpenWin_LocationFromDetailSWH";

    /** Button OpenWin_LocationToDetailSWH Button Event Name */
    public static final String BTN_OPENWIN_LOCATION_TO_DTL_SWH_EVENT_NM = "OpenWin_LocationToDetailSWH";
    /** RETAIL_WH_CD */
    public static final String RETAIL_WH_CD = "Retail WH Code";
    /** SOURCE_WH_CD */
    public static final String SOURCE_WH_CD = "Source WH Code";
    /** DEST_WH_CD */
    public static final String DEST_WH_CD = "Dest WH Code";

    /** Link OpenWin_ST Button Event Name */
    public static final String LINK_OPENWIN_ST_EVENT_NM = "OpenWin_ST";

    /** Button OpenWin_CTRY Button Event Name */
    public static final String BTN_OPENWIN_CTRY_EVENT_NM = "OpenWin_CTRY";

    /** Link OpenWin_PostCd Link Event Name */
    public static final String LINK_OPENWIN_POSTCD_EVENT_NM = "OpenWin_PostCd";

    /** Link OpenWin_City Link Event Name */
    public static final String LINK_OPENWIN_CTY_EVENT_NM = "OpenWin_City";

    /** Link OpenWin_Cnty Link Event Name */
    public static final String LINK_OPENWIN_CNTY_EVENT_NM = "OpenWin_Cnty";
    
    //QC#28226 Add Start
    /** Link OpenWin_PoTerm Link Event Name */
    public static final String LINK_OPENWIN_PAYMENT_TERM = "OpenWin_PoTerm";
    //QC#28226 Add End

    /** for popup  */
    /** ST_CD */
    public static final String ST_CD = "ST_CD";
    /** CTRY_CD */
    public static final String CTRY_CD = "CTRY_CD";
    /** CTRY_DESC_TXT */
    public static final String CTRY_DESC_TXT = "CTRY_DESC_TXT";
    /** Code Column for NPAL1010 */
    public static final String LOC_ROLE_TP_CDLIST = "RGRL_STK_WH,RTRN_ASSET_WH,TECHNICIAN";    // 2019/05/21 T.Ogura [QC#50138,ADD]

    // ----------- Common Constant(for Logic) -----------

    /** No Selected Detail Line */
    public static final int NO_SLCT_DTL = -1;

    /** Index Number 0 */
    public static final int IDX_0 = 0;

    /** Index Number 1 */
    public static final int IDX_1 = 1;

    /** Index Number 2 */
    public static final int IDX_2 = 2;

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Index Number 4 */
    public static final int IDX_4 = 4;

    /** Index Number 5 */
    public static final int IDX_5 = 5;

    /** Index Number 6 */
    public static final int IDX_6 = 6;

    /** Index Number 7 */
    public static final int IDX_7 = 7;

    /** Index Number 8 */
    public static final int IDX_8 = 8;

    /** Index Number 9 */
    public static final int IDX_9 = 9;

    /** Index Number 10 */
    public static final int IDX_10 = 10;

    /** Index Number 13 */
    public static final int IDX_13 = 13;

    /** Index Number 17 */
    public static final int IDX_17 = 17;

    /** Index Number 18 */
    public static final int IDX_18 = 18;

    /** Index Number 20 */
    public static final int IDX_20 = 20;

    /** Index Number 30 */
    public static final int IDX_30 = 30;

    /** Index Number 50 */
    public static final int IDX_50 = 50;

    /** Index Number 100 */
    public static final int IDX_100 = 100;

    /** Comma */
    public static final String COMMA = ",";

    /** Blank */
    public static final String BLANK = "";

    /** Space */
    public static final String SPACE = " ";

    /** Relation Type (Bill To) */
    public static final String RELN_TP_BILL_TO = "52";

    /** Relation Type (Ship To) */
    public static final String RELN_TP_SHIP_TO = "53";

    /** Price Context Type : Sales Price */
    public static final String PRC_CTX_TP_SALES_PRICE = "10";

    /** Price Context Type : Floor Price */
    public static final String PRC_CTX_TP_FLOOR_PRICE = "50";

    /** 8 Digit Mode */
    public static final String EIGHT_DIGIT_MODE = "08";

    /** 10 Digit Mode */
    public static final String TEN_DIGIT_MODE = "10";

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
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

    /** Function Button 11 */
    public static final String[] BTN_ADD_LINE = {"btn11", "Add_NewLine", "Add New Row" };

    // [0]:Button Name [1]:Event Name
    /** BTN_SEARCH */
    public static final String[] BTN_SEARCH = {"Search", "Search" };
    /** BTN_CANCEL */
    public static final String[] BTN_CANCEL = {"Cancel", "Cancel" };
    /** BTN_PRINT */
    public static final String[] BTN_PRINT = {"Print", "Print" };

    /** BTN_SEARCH_VND */
    public static final String[] BTN_SEARCH_VND = {"SearchVendorName", "SearchVendorName" };
    /** BTN_SEARCH_TOC */
    public static final String[] BTN_SEARCH_TOC = {"SearchTocName", "SearchTocName" };
    /** BTN_SEARCH_MDSE */
    public static final String[] BTN_SEARCH_MDSE = {"SearchMdseName", "SearchMdseName" };
    /** BTN_CLOSE */
    public static final String[] BTN_CLOSE = {"PoClose", "Close" };
    /** BTN_COPY */
    public static final String[] BTN_COPY = {"Copy", "Copy" };
    /** BTN_DEST_WH */
    public static final String BTN_DEST_WH = "Get_ShipToInfo";
    // START 2017/11/13 [QC#21849, ADD]
    /** BTN_SHIP_TO_CUST */
    public static final String BTN_SHIP_TO_CUST = "Get_ShipToInfoCustomer";
    // END   2017/11/13 [QC#21849, ADD]
    /** BTN_APPRV_HIST */
    public static final String BTN_APPRV_HIST = "OpenWin_AppvlHistory";
    /** BTN_ATTACHMENTS */
    public static final String BTN_ATTACHMENTS = "Attachments";
    /** BTN_HISTORY */
    public static final String BTN_HISTORY = "OpenWin_History";
    /** BTN_SELECT_ALL */
    public static final String BTN_SELECT_ALL = "SelectAll";
    /** BTN_UN_SELECT_ALL */
    public static final String BTN_UN_SELECT_ALL = "UnSelectAll";
    /** BTN_ADD_NEW_LINE */
    public static final String BTN_ADD_NEW_LINE = "Add_NewLine";
    /** BTN_ADD_CONFIG */
    public static final String BTN_ADD_CONFIG = "AddConfig";
    /** BTN_COMMENT */
    public static final String BTN_COMMENT = "OpenWin_TextEntry";
    /** BTN_GET_ADDRESS */
    public static final String BTN_GET_ADDRESS = "GetAddress";
    /** BTN_DETAIL_MDSE_INFO */
    public static final String BTN_DETAIL_MDSE_INFO = "Get_MdseInfo";
    /** BTN_DETAIL_MDSE_POPUP */
    public static final String BTN_DETAIL_MDSE_POPUP = "OpenWin_Mdse";
    /** BTN_DETAIL_DEST_RSWH */
    public static final String BTN_DETAIL_DEST_RSWH = "OpenWin_LocationToDetailSWH";
    /** BTN_DETAIL_SRC_RSWH */
    public static final String BTN_DETAIL_SRC_RSWH = "OpenWin_LocationFromDetailSWH";
    /** BTN_DETAIL_SERIAL_POPUP */
    public static final String BTN_DETAIL_SERIAL_POPUP = "OpenWin_SerEnt";
    /** BTN_DETAIL_ACCT_CHRG */
    public static final String BTN_DETAIL_ACCT_CHRG = "OpenWin_AccountChrg";
    /** BTN_DETAIL_ACCT_ACRL */
    public static final String BTN_DETAIL_ACCT_ACRL = "OpenWin_AccountAcrl";
    /** BTN_DETAIL_ACCT_VAR */
    public static final String BTN_DETAIL_ACCT_VAR = "OpenWin_AccountVar";
    /** BTN_MOVE_TO_COMPONENT */
    public static final String BTN_MOVE_TO_COMPONENT = "MoveTo_Componet";
    /** BTN_GET_SUPPLIER_NAME */
    public static final String BTN_GET_SUPPLIER_NAME = "Get_SupplierName";
    /** BTN_GET_SUPPLIER_SITE_NAME */
    public static final String BTN_GET_SUPPLIER_SITE_NAME = "Get_SupplierSiteName";
    //QC#26893 Add Start
    /** BTN_APPLY_DISC */
    public static final String BTN_APPLY_DISC = "Apply_Disc";
    /** BTN_DETAIL_LINE_PRICE_INFO */
    public static final String BTN_DETAIL_LINE_PRICE_INFO = "Get_LinePriceInfo";
    //QC#26893 Add End
    // del start 2022/05/16 QC#57934
    ///** TBL_ID_LEFT_A */
    //public static final String TBL_ID_LEFT_A = "A_Left";
    ///** TBL_ID_RIGHT_A */
    //public static final String TBL_ID_RIGHT_A = "A_Right";
    // del end 2022/05/16 QC#57934
    // add start 2022/05/16 QC#57934
    /** TBL_ID_RIGHT_A */
    public static final String TBL_ID_A = "A";
    // add end 2022/05/16 QC#57934
    /** FUNC_ID_SEARCH */
    public static final String FUNC_ID_SEARCH = "NPAL1500T010";
    /** FUNC_ID_SUBMIT */
    public static final String FUNC_ID_SUBMIT = "NPAL1500T020";

    /** NPAL1500T030: Approve Function */
    public static final String FUNC_APPROVE = "NPAL1500T030";

    /** PO Cancel Qty Protect List */
    public static final String[] PO_CANC_QTY_PROTECTS = {PO_STS.WAITING_FOR_APPROVAL, PO_STS.PO_ERROR, PO_STS.CLOSED, PO_STS.CANCELLED };

    /** Cancel Button Enabled List */
    public static final String[] CANCEL_BUTTON_ENABLED = {PO_STS.SAVED, PO_STS.VALIDATED, PO_STS.WAITING_FOR_APPROVAL, PO_STS.PO_CONFIRMED, PO_STS.PO_ERROR, PO_STS.RECEIVING, PO_STS.RECEIVING_COMPLETION };

    /** Close Button Enabled List */

    public static final String[] CLOSE_BUTTON_ENABLED = {PO_STS.VALIDATED, PO_STS.PO_CONFIRMED, PO_STS.PO_ERROR, PO_STS.RECEIVING, PO_STS.RECEIVING_COMPLETION };

    /** Save & Submit Button Enabled List */
    // String[] SAVE_SUBMIT_BUTTON_ENABLED = {PO_STS.SAVED};
    /** Approve & Reject Button Enabled List */
    public static final String[] APPROVE_REJECT_BUTTON_ENABLED = {PO_STS.WAITING_FOR_APPROVAL };

    /** Amount Digit */
    public static final int FRAC_DIGIT = 2;
    /** SHARP */
    public static final String SHARP = "#";
    /** MAX_DETAIL */
    public static final int MAX_DETAIL = 50;

    // ----------- Error Message --------
    /**
     * Error Message: The process cannot be executed because the
     * "PO Type" is not "Int'l Vendor PO"
     */
    public static final String NPAM0111E = "NPAM0111E";

    /**
     * Error Message: The process cannot be executed because the
     * "PO Status" is not "@"
     */
    public static final String NPAM0113E = "NPAM0113E";

    /**
     * Error Message: The details of the process target have not been
     * selected.
     */
    public static final String NPAM0049E = "NPAM0049E";

    /** The value for @ must be greater than or equal to @. */
    public static final String NPAM1196E = "NPAM1196E";

    /** Multiple details to process are selected. */
    public static final String NPAM1215E = "NPAM1215E";

    /** The details to the process are not selected. */
    public static final String NPAM1216E = "NPAM1216E";

    /** Enter Service Call # where PO Type is "Emergency PO". */
    public static final String NPAM1224E = "NPAM1224E";

    /** For [@], '0' or less than '0' cannot be specified. */
    public static final String NPAM0046E = "NPAM0046E";

    /** For [@], less than '0' cannot be specified. */
    public static final String NPAM0051E = "NPAM0051E";

    /** Please enter today's or later date. */
    public static final String NPAM0079E = "NPAM0079E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is [@]. */
    public static final String NPAM0108E = "NPAM0108E";

    /** Please fill out/select the required field. */
    public static final String NPAM0009E = "NPAM0009E";

    /** Please set up the business days. */
//    public static final String NPAM0094E = "NPAM0094E";    // 2019/03/20 T.Ogura [QC#30769,DEL]

    /** You can add Details up to [@]. */
    public static final String NPAM0077E = "NPAM0077E";

    /** [@] is [@]. */
    public static final String NPAM0106I = "NPAM0106I";

    /** Cannot select the PO Type entered. */
    public static final String NPAM1217E = "NPAM1217E";

    /** The process has been successfully completed. */
    public static final String NPAM0005I = "NPAM0005I";

    /**
     * Valid CONSTANT data <Type: [@], Table [VAR_CHAR_CONST] Key:
     * [@]> is not registered.
     */
    public static final String NMAM8027E = "NMAM8027E";

    /** The total amount exceeds the maximum limit. */
    public static final String NPAM0057E = "NPAM0057E";
    /** NPAM1255E */
    public static final String NPAM1255E = "NPAM1255E";

    /** The value for [@] must be over [@]. */
    public static final String NPAM1183E = "NPAM1183E";
    /** NPAM1284E */
    public static final String NPAM1284E = "NPAM1284E";

    /** Before enter @, please click "Save" button. */
    public static final String NPAM1263E = "NPAM1263E";

    /**
     * Please submit additional rows at first in order to cancel
     * selected rows.
     */
    public static final String NPAM1326E = "NPAM1326E";

    /** [@] is mandatory value.@ */
    public static final String NPAM1329E = "NPAM1329E";

    /** Please specify [@]. */
    public static final String NPAM1182E = "NPAM1182E";

    // START 2019/11/11 T.Ogura [QC#54588,ADD]
    /** Email format is incorrect. (@) */
    public static final String NPAM1646E = "NPAM1646E";
    /** Email Address is not entered. */
    public static final String NPAM1253E = "NPAM1253E";
    // END   2019/11/11 T.Ogura [QC#54588,ADD]

    /** LOCATION_TYPE_ALL */
    public static final String LOCATION_TYPE_ALL = "3";
    /** LOCATION_TYPE_VENDOR_WH */
    public static final String LOCATION_TYPE_VENDOR_WH = "4";
    /** SYSTEM_COMMON */
    public static final String SYSTEM_COMMON = "System Common";
    /** PO_FROM_PRICE */
    public static final String PO_FROM_PRICE = "PO From Price";
    /** SEND_PO */
    public static final String SEND_PO = "Send PO";
    /** UNIT_PRICE */
    public static final String UNIT_PRICE = "Unit Price";
    /** UNIT_PRICE */
    public static final String PO_QTY = "PO Qty";
    /** PO_RCV_QTY */
    public static final String PO_RCV_QTY = "Received Qty";
    /** SHIP_TO */
    public static final String SHIP_TO = "Ship To";
    /** WAITINGAPPROVAL */
    public static final String WAITINGAPPROVAL = "Waiting Approval";
    /** MAX_DETAIL_AMOUNT */
    public static final String MAX_DETAIL_AMOUNT = "999999999999999.99";
    /** VND_SYS_TP_PARTS */
    public static final String VND_SYS_TP_PARTS = "P";

    /** Buyer: SYSTEM */
    public static final String BUYER_SYSTEM = "SYSTEM";

    // ----------- Header ** Event Button -----------
    /** Button OpenWin_PoCopy Button Event Name */
    public static final String BTN_OPENWIN_PO_COPY_EVENT_NM = "OpenWin_PoCopy";

    /** Button OrderCancel Button Event Name */
    public static final String BTN_ORDER_CANCEL_EVENT_NM = "Order_Cancel";

    // ----------- Line Config Tab ** Event Button -----------
    /** Button Add NewConfig Button Event Name */
    public static final String BTN_ADD_NEWCONFIG_EVENT_NM = "Line_Config_Add";

    /** Button Add NewLine Button Event Name */
    public static final String BTN_ADD_NEWLINE_NM = "Line_Add";

    /** Button LineCancel Button Event Name */
    public static final String BTN_LINE_CANCEL_EVENT_NM = "OpenWin_LineCancel";

    /** Index Number 11 */
    public static final int IDX_11 = 11;

    /** Index Number 12 */
    public static final int IDX_12 = 12;

    /** ZERO */
    public static final int ZERO = 0;

    // ----------- Message ID -----------
    /** Order Number has not been entered. */
    public static final String NWAM0014E = "NWAM0014E";

    /** The details of the process target have not been selected */
    public static final String NWAM0504E = "NWAM0504E";

    /** Cannot select more than one @. */
    public static final String NWAM0666E = "NWAM0666E";

    /** @ must be selected. */
    public static final String NWAM0667E = "NWAM0667E";

    /** The order will be cancelled. OK? */
    public static final String NWAM0668I = "NWAM0668I";

    /** @ has not been entered. */
    public static final String NWAM0671E = "NWAM0671E";

    /** Selected line cannot process. */
    public static final String NWAM0682E = "NWAM0682E";

    /** Multiple Details cannot be processed at the same time. */
    public static final String NWAM0683E = "NWAM0683E";

    /** Please select either Config or Line. */
    public static final String NWAM0688E = "NWAM0688E";

    /** It is not possible to simultaneously process the [@] and [@]. */
    public static final String NWAM0690E = "NWAM0690E";

    /** [@] is not selected. */
    public static final String NWAM0697E = "NWAM0697E";

    // ----------- Message Parameter -----------
    /** Order Number */
    public static final String MSG_PARAM_ORD_NUM = "Order Number";

    /** Ship To Location */
    public static final String MSG_PARAM_SHIP_TO_LOC = "Ship To Location";

    /** Config Line */
    public static final String MSG_PARAM_CONF_LINE = "Config Line";

    /** Component Line */
    public static final String MSG_PARAM_CMPT_LINE = "Component Line";

    /** Model */
    public static final String MSG_PARAM_MDL = "Model";

    /** Config ID */
    public static final String MSG_PARAM_CONFIG_ID = "Config ID";

    /** Serial# */
    public static final String MSG_PARAM_SERIAL_NUM = "Serial#";

    /** Line Config Tab */
    public static final String MSG_PARAM_LINE_CONFIG_TAB = "Line Config Tab";

    /** Rma Tab */
    public static final String MSG_PARAM_RMA_TAB = "RMA Tab";

    /**
     * The combination of specified input parameters [@] and [@] is
     * incorrect.
     */
    public static final String NPAM1363E = "NPAM1363E";

    /**
     * The process cannot be executed because the PO is not submitted.
     */
    public static final String NPAM1268E = "NPAM1268E";

    // ----------- Popup Parameter Message -----------
    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_SHIP_TO = "PO_SHIP_TO_CUST_V";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_SHIP_TO = "SHIP_TO_CUST_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_SHIP_TO = "SHIP_TO_LOC_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_SHIP_TO = "SHIP_TO_CUST_CD";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_SHIP_TO = "Ship To Search";

    /** Header Code of Label Name for NMAL6050 : Ship To */
    public static final String HDR_CD_LB_NM_FOR_SHIP_TO = "Ship To";

    /** Header Name of Label Name for NMAL6050 : Location Name */
    public static final String HDR_NM_LB_NM_FOR_SHIP_TO = "Location Name";

    /** Detail Code of Label Name for NMAL6050 : Ship To */
    public static final String DTL_CD_LB_NM_FOR_SHIP_TO = "Ship To";

    /** Detail Name of Label Name for NMAL6050 : Location Name */
    public static final String DTL_NM_LB_NM_FOR_SHIP_TO = "Location Name";

    /** ERROR_CODE : WARNING */
    public static final int ERROR_CODE_WARNING =  2;

    /** MSG_KIND : WARNING */
    public static final String MSG_KIND_WARNING =  "W";

    /** MSG_KIND : ERROR */
    public static final String MSG_KIND_ERROR =  "E";

    /** abnormal end message : failed get report. */
    public static final String ABEND_MSG_FAILED_GET_REPORT = "get report bytes failure";

    /** Message Id: [@] */
    public static final String ZZXM0001E = "ZZXM0001E";
    /** NFBM0064E */
    public static final String NFBM0064E = "NFBM0064E";
    /** NLAM1294E */
    public static final String NLAM1294E = "NLAM1294E";

    // QC#13985 2016/09/09 Add Start
    /** Screen Name for Account Type Charge */
    public static final String SCR_NM_ACCOUNT_TYPE_CH = "CH";

    /** Screen Name for Account Type Accrual */
    public static final String SCR_NM_ACCOUNT_TYPE_AC = "AC";

    /** Screen Name for Account Type Variance */
    public static final String SCR_NM_ACCOUNT_TYPE_VA = "VA";
    // QC#13985 2016/09/09 Add End

    // QC#21169
    /** TBL_ID_LEFT_B */
    public static final String TBL_ID_LEFT_B = "B_Left";

    /** Please enter today's or later date. **/
    public static final String NPZM0041E = "NPZM0041E";

    /** BTN_APPLY */
    public static final String BTN_APPLY = "Apply";

    // START 2023/02/15 S.Dong [QC#60966, ADD]
    /** Vendor Ship Date Apply button name */
    public static final String BTN_RQSTSHIPDT_APPLY = "RqstShipDt_Apply";
    // END 2023/02/15 S.Dong [QC#60966, ADD]
    // START 2023/04/19 TZ.Win [QC#61299, ADD]
    /** ASL Apply button name */
    public static final String BTN_APPLY_ASL = "Apply_ASL";
    // END 2023/04/19 TZ.Win [QC#61299, ADD]
    /**
     * Error Message: It cannot be processed since there is no detail.
     */
    public static final String NPAM1351E = "NPAM1351E";
    // QC#21169

    // START 2021/04/21 J.Evangelista [QC#57102,ADD]
    // ----------- Sort IMG Constants (from S21SortColumnIMGController) -----------
    /** Sort IMG attribute prefix */
    public static final String SORT_IMG_ATTR_PREFIX = "sortIMG.";

    /** Sort IMG path (ascending) */
    public static final String SORT_IMG_ASC_PATH = "./img/tableSort/asc.gif";

    /** Sort IMG path (descending) */
    public static final String SORT_IMG_DESC_PATH = "./img/tableSort/desc.gif";
    // END 2021/04/21 J.Evangelista [QC#57102,ADD]

}
