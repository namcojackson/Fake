/**
 * <pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0020.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * PO Maintenance
 * 
 * Date           Company         Name              Create/Update      Defect No
 * ------------------------------------------------------------------------------------
 * 08/30/2013     CSAI            N.Sekine          Create             MW Replace Initial
 *</pre>
 */
public interface NLGL0020Constant {

    /**
     * value for all wh in data security attribute of wh
     */
    String WH_ALL_VALUE = "*";

    /**
     * delimiter for pulldown list
     */
    String DELIMITER_COLON = ":";

    /**
     * delimiter for SQL
     */
    String SQL_DELIMITER = ",";

    /**
     * Status value(Open)
     */
    String STATUS_VAL_OPEN = "Open";

    /**
     * Status value(Partial)
     */
    String STATUS_VAL_PARTIAL = "Partial";

    /**
     * Status value(Closed)
     */
    String STATUS_VAL_CLOSED = "Closed";

    /**
     * delimiter for SQL
     */
    String BLANK = "";

    /**
     * Task Code:RCVD
     */
    String TASK_CD_RCVD = "RCVD";

    /**
     * code value of "00:All" in pulldown list of wh
     */
    String WH_ALL_SELECTION_VALUE = "00";

    /**
     * yyyyMMddHHmmSSSSS for time before
     */
    String YYYYMMDDHHMMSS_BEFORE = "yyyyMMddHHmmss";

    /**
     * yyyyMMddHHmmSSSSS for EZINTIME before
     */
    String YYYYMMDDHHMMSSSSS_BEFORE = "yyyyMMddHHmmssSSS";

    /**
     * yyyyMMddHHmmSSSSS for EZUptime after
     */
    String YYYYMMDDHHMMSS_AFTER = "MM/dd/yyyy HH:mm:ss";

    /**
     * yyyyMMdd
     */
    String YYYYMMDD_BEFORE = "yyyyMMdd";

    /**
     * yyyy/MM/dd
     */
    String YYYYMMDD_AFTER = "yyyy/MM/dd";

    /**
     * Date Type yyyyMMddHHmmSS
     */
    String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event [INIT] Name
     */
    String EVENT_NM_NLGL0040SCRN00_INIT = "NLGL0020_INIT";

    /**
     * Event [Next] Name
     */
    String EVENT_NM_NLGL0020SCRN00_PAGENEXT = "NLGL0020Scrn00_PageNext";

    /**
     * Event [Prev] Name
     */
    String EVENT_NM_NLGL0020SCRN00_PAGEPREV = "NLGL0020Scrn00_PagePrev";

    /**
     * Event [Reset] Name
     */
    String EVENT_NM_NLGL0020SCRN00_CMN_RESET = "NLGL0020Scrn00_CMN_Reset";

    /**
     * Event [Return] Name
     */
    String EVENT_NM_NLGL0020SCRN00_CMN_RETURN = "NLGL0020Scrn00_CMN_Return";

    /**
     * Event [Submit] Name
     */
    String EVENT_NM_NLGL0020SCRN00_CMN_SUBMIT = "NLGL0020Scrn00_CMN_Submit";

    /**
     * Event [Submit List Change] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCHANGE_SUBMITLIST = "NLGL0020Scrn00_OnChange_SubmitList";

    /**
     * Event [Submit List Change] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCHANGE_UPL_TASKLIST = "NLGL0020Scrn00_OnChange_UPL_TaskList";

    /**
     * Event [CheckAll] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_CHECKALL = "NLGL0020Scrn00_OnClick_CheckAll";

    /**
     * Event [DNLD_DeleteRow] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_DNLD_DELETEROW = "NLGL0020Scrn00_OnClick_DNLD_DeleteRow";

    /**
     * Event [DNLD_InsertRow] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_DNLD_INSERTROW = "NLGL0020Scrn00_OnClick_DNLD_InsertRow";

    /**
     * Event [DownloadEditTab] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_DOWNLOADEDITTAB = "NLGL0020Scrn00_OnClick_DownloadEditTab";

    /**
     * Event [POListTab] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_POLISTTAB = "NLGL0020Scrn00_OnClick_POListTab";

    /**
     * Event [POStatusTab] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_POSTATUSTAB = "NLGL0020Scrn00_OnClick_POStatusTab";

    /**
     * Event [Radio Bottun Copy] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_RADIO_COPY = "NLGL0020Scrn00_OnClick_Radio_Copy";

    /**
     * Event [Radio Bottun Resend] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_RADIO_RESEND = "NLGL0020Scrn00_OnClick_Radio_Resend";

    /**
     * Event [Search] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_SEARCH = "NLGL0020Scrn00_OnClick_Search";

    /**
     * Event [UncheckAll] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_UNCHECKALL = "NLGL0020Scrn00_OnClick_UncheckAll";

    /**
     * Event [UPL_CopyRow] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_UPL_COPYROW = "NLGL0020Scrn00_OnClick_UPL_CopyRow";

    /**
     * Event [UPL_DeleteRow] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_UPL_DELETEROW = "NLGL0020Scrn00_OnClick_UPL_DeleteRow";

    /**
     * Event [UPL_InsertRow] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_UPL_INSERTROW = "NLGL0020Scrn00_OnClick_UPL_InsertRow";

    /**
     * Event [UploadEditTab] Name
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_UPLOADEDITTAB = "NLGL0020Scrn00_OnClick_UploadEditTab";

    /**
     * Event
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_UPD_CHECKALL = "NLGL0020Scrn00_OnClick_UPD_CheckAll";

    /**
     * Event
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_UPD_UNCHECK_ALL = "NLGL0020Scrn00_OnClick_UPD_UncheckAll";

    /**
     * Event
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_UPD_SEARCH = "NLGL0020Scrn00_OnClick_UPD_Search";

    /**
     * Event
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_DELETE = "NLGL0020Scrn00_CMN_Delete";

    /**
     * Event
     */
    String EVENT_NM_NLGL0020SCRN00_SELECT_RETAIL_WH = "NLGL0020Scrn00_SelectRetailWh";
    
    /**
     * Event
     */
    String EVENT_NM_NLGL0020SCRN00_OPENWIN_RTLWH = "NLGL0020Scrn00_OpenWin_RtlWh";

    /**
     * Event
     */
    String EVENT_NM_NLGL0020SCRN00_ONCLICK_GET_RTLWHNM = "NLGL0020Scrn00_OnClick_GetRtlWhNm";

    /**
     * Event
     */
    String EVENT_NM_NLGL0020_NWAL1130 = "NLGL0020_NWAL1130";

    /**
     * Event
     */
    String EVENT_NM_NLGL0020Scrn00_SelectWh = "NLGL0020Scrn00_SelectWh";
    // =================================================
    // TAB Name
    // =================================================
    /**
     * Field Value : "POList"
     */
    String TAB_ID_LIST = "POList";

    /**
     * Field Value : "POStatus"
     */
    String TAB_ID_STATUS = "POStatus";

    /**
     * Field Value : "DnldEdt"
     */
    String TAB_ID_DNLD = "DnldEdt";

    /**
     * Field Value : "UpldEdt"
     */
    String TAB_ID_UPD = "UpldEdt";

    // =================================================
    // name of Table
    // =================================================
    /**
     * table name WMS_MDSE_LIST
     */
    String TBL_WMS_MDSE_LIST = "WMS_MDSE_LIST";
    
    /**
     * table name RWS_HDR
     */
    String TBL_RWS_HDR = "RWS_HDR";

    /**
     * table name RWS_DTL
     */
    String TBL_RWS_DTL = "RWS_DTL";

    /**
     * table name RWS_SER
     */
    String TBL_RWS_SER = "RWS_SER";
    
    // =================================================
    // Message ID
    // =================================================
    /**
     * No search results found.
     */
    String NZZM0000E = "NZZM0000E";

    /**
     * The record cannot be registered. Table Name: [@], Field Name:
     * [@], Key Value: [@]
     */
    String NLGM0007E = "NLGM0007E";

    /**
     * The record cannot be updated. Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    String NLGM0008E = "NLGM0008E";

    /**
     * Relating records have errors.You can't download until the error
     * is resolved.
     */
    String NLGM0032W = "NLGM0032W";

    /**
     * Multiple Details cannot be processed at the same time.
     */
    String NLGM0035E = "NLGM0035E";

    /**
     * It has not been selected. Please select.
     */
    String NLGM0036E = "NLGM0036E";

    /**
     * Exclusive control error of @ table. @ is @.
     */
    String NLGM0048E = "NLGM0048E";

    /**
     * [@] cannot be added because the maximum number of line breaks
     * for [@] has been exceeded.
     */
    String NLGM0054E = "NLGM0054E";

    /**
     * MDSE does not exist.
     */
    String NLGM0057E = "NLGM0057E";

    /**
     * [@] is an invalid parameter. Parameter Name: [@]
     */
    String NLGM0060E = "NLGM0060E";

    /**
     * MDSE does not exist.
     */
    String NLGM0062E = "NLGM0062E";

    /**
     * [@]is invalid to process.
     */
    String NLGM0069E = "NLGM0069E";

    /**
     * Please select [@].
     */
    String NLGM0070E = "NLGM0070E";

    /**
     * Please input MDSE that has been downloaded.
     */
    String NLGM0077E = "NLGM0077E";

    /**
     * The process terminated. Table Name: [@], Created Records: [@]
     */
    String NLGM0001I = "NLGM0001I";

    /**
     * The process terminated. Table Name: [@], Updated Records: [@]
     */
    String NLGM0002I = "NLGM0002I";

    /**
     * The process [@] has been successfully completed.
     */
    String NLGM0025I = "NLGM0025I";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    String NZZM0001W = "NZZM0001W";

    /**
     * The process [@] has been successfully completed.[@]
     */
    String NLGM0073I = "NLGM0073I";

    /**
     * The entered [@] does not exist in master.
     */
    String NLZM2278E = "NLZM2278E";


    // =================================================
    // name of field
    // =================================================
    /**
     * LIST Table Name name Item Input
     */
    String FIELD_NAME_ITEM_INPUT = "Item Input";

    /**
     * Upload List
     */
    String NAME_FOR_MESSAGE_LIST = "Upload List";

    /**
     * Retail Warehouse code
     */
    String DB_RTL_WH_CD = "RTL_WH_CD";

    /**
     * Retail Warehouse code
     */
    String DB_PACK_CD_TXT = "PACK_CD_TXT";

    /**
     * Retail Warehouse Name
     */
    String DB_RTL_WH_NM = "RTL_WH_NM";

    /**
     * Retail Sub Warehouse code
     */
    String DB_RTL_SWH_CD = "RTL_SWH_CD";

    /**
     * wh code
     */
    String DB_WH_CD = "WH_CD";

    /**
     * wh owner code
     */
    String DB_WH_OWNR_CD = "WH_OWNR_CD";
    
    /**
     * wh owner desc text
     */
    String DB_WH_OWNR_DESC_TXT = "WH_OWNR_DESC_TXT";
    
    /**
     * sce order type code
     */
    String DB_SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /**
     * sce order type name
     */
    String DB_SCE_ORD_TP_NM = "SCE_ORD_TP_NM";

    /**
     * RWS number
     */
    String DB_RWS_NUM = "RWS_NUM";

    /**
     * RWS Detail Line number
     */
    String DB_RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";

    /**
     * RWS Line number
     */
    String DB_RWS_LINE_NUM = "RWS_LINE_NUM";

    /**
     * SER_NUM
     */
    String DB_SER_NUM = "SER_NUM";

    /**
     * MDSE_CD
     */
    String DB_MDSE_CD = "MDSE_CD";

    /**
     * wms_mdse_code code
     */
    String DB_WMS_MDSE_CD = "WMS_MDSE_CD";

    /**
     * description about wh code
     */
    String DB_WMS_DESC_NM = "WMS_DESC_NM";

    /**
     * UOM code
     */
    String DB_WMS_UOM_CD = "WMS_UOM_CD";

    /**
     * UOM name
     */
    String DB_WMS_UOM_NM = "WMS_UOM_NM";

    /**
     * Transaction code
     */
    String DB_WMS_TRX_CD = "WMS_TRX_CD";

    /**
     * Transaction Code Name
     */
    String DB_WMS_TRX_NM = "WMS_TRX_NM";

    /**
     * Purchase Type code
     */
    String DB_PRCH_TP_CD = "PRCH_TP_CD";

    /**
     * Purchase Type Code Name
     */
    String DB_PRCH_TP_NM = "PRCH_TP_NM";

    /**
     * TO_WMS_DATA_IF_SQ
     */
    String TO_WMS_DATA_IF_SQ = "TO_WMS_DATA_IF_SQ";

    /**
     * SQ number
     */
    String DB_WMS_SQ_NUM = "WMS_SQ_NUM";

    /**
     * Base Unit of Measure Quantity
     */
    String DB_WMS_BASE_UOM_QTY = "WMS_BASE_UOM_QTY";

    /**
     * Length of SQ
     */
    int LEN_TO_WMS_SEQ = 7;

    /**
     * Primary Key of AUTO_SEQ(ORDER ID)
     */
    String PO_ID_ONLINE_KEY = "WMS_ORD_ID";

    /**
     * Primary Key of AUTO_SEQ(REC ID)
     */
    String REC_ID_ONLINE_KEY = "WMS_REC_ID";

    /**
     * ROWID
     */
    String DB_ROWID = "MAX(ROWID)";

    // DB Name about WMS_INBD_PO_HDR-PO_TO_WMS_DATA_TXT
    /**
     * Purchase Order Number
     */
    String DB_WMS_PO_ID = "WMS_PO_ID";

    /**
     * Vendor Code
     */
    String DB_VND_CD = "VND_CD";

    /**
     * Company Code
     */
    String DB_WMS_CMPY_CD = "WMS_CMPY_CD";

    /**
     * Order Type
     */
    String DB_WMS_PRCH_TP_CD = "WMS_PRCH_TP_CD";

    /**
     * ETA Date
     */
    String DB_PO_FROM_DT_TM_TS = "PO_FROM_DT_TM_TS";

    /**
     * Vessel Name
     */
    String DB_WMS_VESL_NM = "WMS_VESL_NM";

    /**
     * B/L#
     */
    String DB_WMS_BOL_NUM = "WMS_BOL_NUM";

    /**
     * Message1
     */
    String DB_INBD_PO_MSG_TXT_01 = "INBD_PO_MSG_TXT_01";

    /**
     * Message2
     */
    String DB_INBD_PO_MSG_TXT_02 = "INBD_PO_MSG_TXT_02";

    /**
     * Message3
     */
    String DB_INBD_PO_MSG_TXT_03 = "INBD_PO_MSG_TXT_03";

    /**
     * Message4
     */
    String DB_INBD_PO_MSG_TXT_04 = "INBD_PO_MSG_TXT_04";

    /**
     * Seal Num
     */
    String DB_WMS_SER_NUM = "WMS_SER_NUM";

    /**
     * INTFC_PROC_STS_CD
     */
    String DB_INTFC_PROC_STS_CD = "INTFC_PROC_STS_CD";

    /**
     * PROC_STS_CD
     */
    String DB_PROC_STS_CD = "PROC_STS_CD";

    // DB Name about WMS_INBD_PO_DTL - RWS_TO_WMS_DATA_TXT
    /**
     * Line Number
     */
    String DB_WMS_LINE_NUM = "WMS_LINE_NUM";

    /**
     * Stock Status
     */
    String DB_S80_STK_STS_CD = "S80_STK_STS_CD";

    /**
     * Line Status
     */
    String DB_WMS_LINE_STS_CD = "WMS_LINE_STS_CD";

    /**
     * Inventory indicator
     */
    String DB_WMS_INV_IND = "WMS_INV_IND";

    /**
     * Purchase quantity
     */
    String DB_WMS_OPEN_QTY = "WMS_OPEN_QTY";

    /**
     * ETA date
     */
    String DB_WMS_EST_DT_TM_TS = "WMS_EST_DT_TM_TS";

    /**
     * Invoice Number
     */
    String DB_WMS_INV_ID = "WMS_INV_ID";

    /**
     * Delivery Order Number
     */
    String DB_WMS_DO_ID = "WMS_DO_ID";

    /**
     * Case Number From
     */
    String DB_CSE_TO_NUM = "CSE_TO_NUM";

    /**
     * Case Number To
     */
    String DB_CSE_FROM_NUM = "CSE_FROM_NUM";

    /**
     * Case mark text collo Number
     */
    String DB_WMS_COLLO_NUM = "WMS_COLLO_NUM";

    // DB Name about WMS_INBD_RWS_WRK - RWS_TO_WMS_DATA_TXT

    /**
     * Vendor Name1
     */
    String DB_WMS_VND_NM_01 = "WMS_VND_NM_01";

    /**
     * Vendor Name2
     */
    String DB_WMS_VND_NM_02 = "WMS_VND_NM_02";

    /**
     * Address1
     */
    String DB_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /**
     * Address2
     */
    String DB_SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /**
     * Address3
     */
    String DB_THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /**
     * Address4
     */
    String DB_FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /**
     * City
     */
    String DB_CTY_ADDR = "CTY_ADDR";

    /**
     * State Code
     */
    String DB_ST_CD = "ST_CD";

    /**
     * Zip Code
     */
    String DB_POST_CD = "POST_CD";

    /**
     * Country Code
     */
    String DB_CTRY_CD = "CTRY_CD";

    /**
     * Contact Person Name
     */
    String DB_VND_TO_CTAC_NM = "VND_TO_CTAC_NM";

    /**
     * Contact Tel Number
     */
    String DB_VND_TO_CTAC_NUM = "VND_TO_CTAC_NUM";

    /**
     * WMS_INBD_TRX_PK
     */
    String DB_WMS_INBD_TRX_PK = "WMS_INBD_TRX_PK";

    /**
     * WMS_INBD_RWS_WRK_PK
     */
    String DB_WMS_INBD_RWS_WRK_PK = "WMS_INBD_RWS_WRK_PK";

    /**
     * MDSE Volume
     */
    String DB_XX_MDSE_VOLUME = "XX_MDSE_VOLUME";

    /**
     * field name check box(PO List)
     */
    String FIELD_NAME_XXCHKBOX_A1 = "xxChkBox_A1";

    /**
     * field name check box(Download)
     */
    String FIELD_NAME_XXCHKBOX_F1 = "xxChkBox_F1";

    /**
     * field name check box(Upload)
     */
    String FIELD_NAME_XXCHKBOX_I1 = "xxChkBox_I1";

    /**
     * Length Set of Table Layout Columns: HAEDER
     */
    int[] LENG_SET_HEADER = new int[] {4, 7, 2, 1, 1, 2, 4, 15, 10, 1, 2, 1, 10, 8, 1, 30, 20, 65, 65, 65, 65, 15, 306 };

    /**
     * Length Set of Table Layout Columns: Detail
     */
    int[] LENG_SET_DETAIL = new int[] {4, 7, 2, 1, 1, 2, 4, 15, 5, 25, 2, 1, 1, 9, 10, 15, 15, 5, 5, 10, 25, 536 };

    /**
     * Length Set of Table Layout Columns: VENDER
     */
    int[] LENG_SET_VND = new int[] {4, 7, 2, 1, 1, 2, 4, 15, 10, 35, 35, 35, 35, 35, 35, 20, 2, 15, 2, 25, 15, 365 };

    // =================================================
    // name of Table
    // =================================================
    /**
     * TABLE NAME WMS_INBD_TRX
     */
    String WMS_INBD_TRX = "WMS_INBD_TRX";

    /**
     * TABLE NAME WMS_INBD_RWS_WRK
     */
    String WMS_INBD_RWS_WRK = "WMS_INBD_RWS_WRK";

    /**
     * TABLE NAME WMS_INBD_PO_HDR
     */
    String WMS_INBD_PO_HDR = "WMS_INBD_PO_HDR";

    // =================================================
    // Data value
    // =================================================

    /**
     * Date Type List(Code)
     */
    String[] DATE_TYPE_CODE_LIST = {"1", "2" };

    /**
     * Date Type List(Display)
     */
    String[] DATE_TYPE_NAME_LIST = {"Download Date", "From Date" };

    /**
     * Submit Type List(Code)
     */
    String[] SUBMIT_TYPE_CODE_LIST = {"1", "2" };

    /**
     * Submit Type List(Display)
     */
    String[] SUBMIT_TYPE_NAME_LIST = {"Copy", "Resend" };

    /**
     * Data Value("01")
     */
    String DATA_VALUE_01 = "01";

    /**
     * Data Value("0")
     */
    String DATA_VALUE_0 = "0";

    /**
     * Data Value("1")
     */
    String DATA_VALUE_1 = "1";
    
    /**
     * Data Value("2")
     */
    String DATA_VALUE_2 = "2";

    /**
     * Data Value("3")
     */
    String DATA_VALUE_3 = "3";

    /**
     * Data Value("10")
     */
    String DATA_VALUE_10 = "10";
    
    /**
     * Data Value("P")
     */
    String DATA_VALUE_P = "P";

    /**
     * Data Value("Y")
     */
    String DATA_VALUE_Y = "Y";

    /**
     * Data Value("N")
     */
    String DATA_VALUE_N = "N";

    /**
     * Data Value("S")
     */
    String DATA_VALUE_S = "S";

    /**
     * Data Value("R")
     */
    String DATA_VALUE_R = "R";

    /**
     * Data Value(" ")
     */
    String DATA_VALUE_SPACE = " ";

    /**
     * Data Value(-1)
     */
    int DATA_VALUE_INT_MIN_1 = -1;

    /**
     * Data Value(0)
     */
    int DATA_VALUE_INT_0 = 0;

    /**
     * Data Value(1)
     */
    int DATA_VALUE_INT_1 = 1;

    /**
     * Data Value(6)
     */
    int DATA_VALUE_INT_6 = 6;

    /**
     * Data Value(8)
     */
    int DATA_VALUE_INT_8 = 8;

    /**
     * Data Value(999999999)
     */
    BigDecimal DATA_VALUE_DEC_999999999 = BigDecimal.valueOf(999999999);

    /**
     * Data Value("DELETE")
     */
    String DATA_VALUE_DELETE = "Delete";

    /**
     * Data Value("DELETE")
     */
    String DATA_VALUE_INSERT = "Insert";

    /**
     * Data Value("QTY/UNIT")
     */
    String DATA_VALUE_QTY_UNIT = "QTY/UNIT";

    /**
     * Data Value("S1"-stock status)
     */
    String DATA_VALUE_STKSTS = "S1";

    /**
     * Data Value(TASK CD : PDLT)
     */
    String DATA_VALUE_TASK_PDLT = "PDLT";

    /**
     * Data Value(TASK CD : PCLS)
     */
    String DATA_VALUE_TASK_PCLS = "PCLS";

    /**
     * Data Value(TASK CD : PCFM)
     */
    String DATA_VALUE_TASK_PCFM = "PCFM";

    /**
     * Data Value(Submit Type)
     */
    String DATA_VALUE_SUBMIT_TYPE = "Submit Type";

    /**
     * 0:UNPROCEESSED
     */
    String UNPROCESSED = "UnProcessed";

    /**
     * 1:PROCEESSED
     */
    String PROCEESSED = "Proceessed";

    /**
     * 9:Error
     */
    String ERROR = "Error";

    /**
     * Processing
     */
    String PROCESSING = "Processing";

    /**
     * GLBL_CMPY_CD
     */
    String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /**
     * WH_CD
     */
    String WH_CD = "WH_CD";

    /**
     * WMS_PO_ID
     */
    String WMS_PO_ID = "WMS_PO_ID";

    /**
     * WMS_SQ_NUM
     */
    String WMS_SQ_NUM = "WMS_SQ_NUM";

    /** Value of WH_CD All */
    String VAL_WH_CD_NM_ALL = "ALL";
    
    
    /** Value of WMS_TASK_CD All */
    String VAL_WMS_TASK_CD_ALL = "-";

    /** Value of WMS_TASK_CD All */
    String VAL_WMS_TASK_NM_ALL = "ALL";

    // =================================================
    // key value of ssm parameter
    // =================================================
    /**
     * global company code
     */
    String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * setting *
     */
    String DB_WH_ALL_VAL = "whAllVal";

    /**
     * whAllSelval means ALL(00) on WMS_WH PullDown.
     */
    String DB_WH_ALL_SEL_VAL = "whAllSelval";

    /**
     * wh code
     */
    String DB_PARAM_WH_CD = "whCd";

    /**
     * PO ID
     */
    String DB_PARAM_PO_ID = "poId";

    /**
     * From Date
     */
    String DB_PARAM_START_DT = "dateStart";

    /**
     * TO Date
     */
    String DB_PARAM_END_DT = "dateEnd";

    /**
     * Purchase code
     */
    String DB_PARAM_PRCH_CD = "prchTpCd";

    /**
     * Item code
     */
    String DB_PARAM_MDSE_CD = "wmsMDSECd";

    /**
     * Unit of Measure Quantity
     */
    String DB_PARAM_UOM_CD = "UOM";

    /**
     * Transaction code
     */
    String DB_PARAM_TRX_CD = "wmsTrxCd";

    /**
     * Check Box Value
     */
    String DB_PARAM_CHECK = "chk";

    /**
     * Check Box Value
     */
    String DB_PARAM_DATE_LIST = "dateList";

    /**
     * Status (Open)
     */
    String DB_PARAM_OPEN = "open";

    /**
     * Status (Partial)
     */
    String DB_PARAM_PARTIAL = "partial";

    /**
     * Status (Closed)
     */
    String DB_PARAM_CLOSED = "closed";

    /**
     * Demilitter (,)
     */
    String DB_PARAM_SEPARATE = "sept";

    /**
     * Limit Row Number
     */
    String DB_PARAM_ROWNUM = "rownum";

    /**
     * SQ #
     */
    String DB_PARAM_SQ_NUM = "sqNum";

    /**
     * Purchase Type
     */
    String DB_PARAM_PRCH_TP = "prchtp";

    /**
     * Task Code
     */
    String DB_PARAM_TASK_CD = "taskCd";

    /**
     * EZIntime
     */
    String DB_PARAM_EZINTIME = "ezIntime";

    /**
     * global company code
     */
    String DB_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /**
     * SSM set Key Name: KEY_WMS_CLO_DT_TM_TS
     */
    String KEY_WMS_CLO_DT_TM_TS = "wmsCloDtTmTs";

    /**
     * wms task code
     */
    String DB_PARAM_WMS_TASK_CD = "wmsTaskCd";

    /**
     * EZCancelFlag
     */
    String DB_PARAM_EZCANCELFLAG = "wmsEzCancelFlag";

    /**
     * rtlWhCd
     */
    String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /**
     * invtyOwnrCd
     */
    String DB_PARAM_INVTY_OWNR_CD = "invtyOwnrCd";

    /**
     * svcConfigMstrPk
     */
    String DB_PARAM_SVC_CONFIG_MSTR_PK = "svcConfigMstrPk";

    /** Value of Active Status */
    String VAL_Actv_ = "Y";

    /** Value of InActive Status */
    String VAL_InActv_ = "N";

    /** TpCd */
    String TP_CD_COPY = "1";

    /** Type Resend */
    String TP_CD_RESEND = "2";

    /**
     * VAR_CHAR_CONST Key Name: Valid copy flag 
     */
    String NLGL_VALID_COPY_FLG = "NLGL_VALID_COPY_FLG";

    /**
     * VAR_CHAR_CONST Key Name: WMS Package Code Set Owner Code Flag
     */
    String WMS_PACK_CD_SET_OWNER_CD_FLG = "WMS_PACK_CD_SET_OWNER_CD_FLG";

    /**
     * wms wave id
     */
    String DB_PARAM_WMS_PACK_CD_SET_OWNER_CD_FLG = "wmsPackCdSetOwnerCdFlg";

}
