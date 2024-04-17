/**
 * <pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0010.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * SO Mainenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/20/2013   CSAI            Y.Miyauchi      Create          MW Replace Initial
 * 05/31/2017   CITS            S.Endo          Update          RS#3168
 * 06/21/2017   CITS            S.Endo          Update          QC#19042
 *</pre>
 */
public interface NLGL0010Constant {

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Init
     */
    String EVENT_NM_NLGL0010_INIT = "NLGL0010_INIT";

    /**
     * Event
     */
    String EVENT_NM_PAGE_NEXT = "NLGL0010Scrn00_PageNext";

    /**
     * Event
     */
    String EVENT_NM_PAGE_PRE = "NLGL0010Scrn00_PagePrev";

    /**
     * Event
     */
    String EVENT_NM_CMN_RESET = "NLGL0010Scrn00_CMN_Reset";

    /**
     * Event
     */
    String EVENT_NM_CMN_RETURN = "NLGL0010Scrn00_CMN_Return";

    /**
     * Event
     */
    String EVENT_NM_CMN_SUBMIT = "NLGL0010Scrn00_CMN_Submit";

    /**
     * Event
     */
    String EVENT_NM_CMN_DELETE = "NLGL0010Scrn00_CMN_Delete";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_CHECKALL = "NLGL0010Scrn00_OnClick_CheckAll";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_DNLD_COPY_ROW = "NLGL0010Scrn00_OnClick_DNLD_CopyRow";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_DNLD_DELETE_ROW = "NLGL0010Scrn00_OnClick_DNLD_DeleteRow";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_DNLD_INSERT_ROW = "NLGL0010Scrn00_OnClick_DNLD_InsertRow";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_DOWNLOAD_EDIT_TAB = "NLGL0010Scrn00_OnClick_DownloadEditTab";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_SEARCH = "NLGL0010Scrn00_OnClick_Search";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_SOLIST_TAB = "NLGL0010Scrn00_OnClick_SOListTab";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_SOSTATUS_TAB = "NLGL0010Scrn00_OnClick_SOStatusTab";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_UNCHECK_ALL = "NLGL0010Scrn00_OnClick_UncheckAll";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_UPD_COPY_ROW = "NLGL0010Scrn00_OnClick_UPD_CopyRow";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_UPD_DELETE_ROW = "NLGL0010Scrn00_OnClick_UPD_DeleteRow";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_UPD_INSERT_ROW = "NLGL0010Scrn00_OnClick_UPD_InsertRow";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_UPLOAD_EDIT_TAB = "NLGL0010Scrn00_OnClick_UploadEditTab";

    /**
     * Event
     */
    String EVENT_NM_ONCHANGE_SELECTSENDTYPE = "NLGL0010Scrn00_OnChange_DNLD_SelectSendType";

    /**
     * Event
     */
    String EVENT_NM_ONCHANGE_UPD_TASK_LIST = "NLGL0010Scrn00_OnChange_UPD_TaskList";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_UPD_CHECKALL = "NLGL0010Scrn00_OnClick_UPD_CheckAll";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_UPD_UNCHECK_ALL = "NLGL0010Scrn00_OnClick_UPD_UncheckAll";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_UPD_SEARCH = "NLGL0010Scrn00_OnClick_UPD_Search";

    /**
     * Event
     */
    String EVENT_NM_ONCLICK_GET_RTL_WH_NM = "NLGL0010Scrn00_OnClick_GetRtlWhNm";

    /**
     * Event
     */
    String EVENT_NM_OPEN_WIN_WH = "NLGL0010_NWAL1130";

    /**
     * Event
     */
    String EVENT_NM_SELECT_WH = "NLGL0010Scrn00_SelectWh";

    /**
     * Event
     */
    String EVENT_NM_SELECT_SHIP_VIA = "NLGL0010Scrn00_SelectShipVia";
    // =================================================
    // key value of ssm parameter
    // =================================================

    /**
     * global company code
     */
    String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * wh code
     */
    String DB_PARAM_WH_CD = "whCd";

    /**
     * wms so id
     */
    String DB_PARAM_WMS_SO_ID = "wmsSoId";

    /**
     * wms sq num
     */
    String DB_PARAM_WMS_SQ_NUM = "wmsSqNum";

    /**
     * wms order type code
     */
    String DB_PARAM_SCE_ORD_TP_CD = "sceOrdTpCd";

    /**
     * wms console flag
     */
    String DB_PARAM_WMS_CONSL_FLG = "wmsConslFlg";

    /**
     * custom order number
     */
    String DB_PARAM_CUST_ORD_NM = "custOrdNm";

    /**
     * date type
     */
    String DB_PARAM_DT_TP = "dateEntryL14If";

    /**
     * so num
     */
    String DB_PARAM_SO_NUM = "soNum";

    /**
     * mdseCd
     */
    String DB_PARAM_MDSE_CD = "mdseCd";

    /**
     * soSlpNum
     */
    String DB_PARAM_SO_SLP_NUM = "soSlpNum";

    /**
     * SSM set Key Name: KEY_WMS_CANC_DT_TM_TS
     */
    public static final String KEY_WMS_CANC_DT_TM_TS = "wmsCancDtTmTs";

    /**
     * SSM set Key Name: KEY_SHIP_DT_TM_TS
     */
    public static final String KEY_SHIP_DT_TM_TS = "shipDtTmTs";

    /**
     * SSM set Key Name: KEY_EZCANCELFLAG
     */
    public static final String KEY_EZCANCELFLAG = "ezCancelFlag";

    /**
     * so search from date
     */
    String DB_PARAM_XX_SO_SEARCH_FROM_DT = "xxSoSrchFromDt";

    /**
     * so search thru date
     */
    String DB_PARAM_XX_SO_SEARCH_THRU_DT = "xxSoSrchThruDt";

    /**
     * wms frt out cd
     */
    String DB_PARAM_WMS_FRT_OUT_CD = "wmsFrtOutCd";

    /**
     * ship to cust cd
     */
    String DB_PARAM_SHIP_TO_CUST_CD = "shipToCustCd";

    /**
     * bill to cust code
     */
    String DB_PARAM_BILL_TO_CUST_CD = "billToCustCd";

    /**
     * wms trx cd
     */
    String DB_PARAM_WMS_TRX_CD = "wmsTrxCd";

    /**
     * wms mdse code
     */
    String DB_PARAM_WMS_MDSE_CD = "wmsMdseCd";

    /**
     * wms uom code
     */
    String DB_PARAM_WMS_UOM_CD = "wmsUomCd";

    /**
     * 
     */
    String DB_PARAM_SO_SHIP_VIA_CD = "wmsMdseCd";

    /**
     * ship via cd
     */
    String DB_PARAM_SHIP_VIA_CD = "shipViaCd";

    /**
     * wms ship bia tp id
     */
    String DB_PARAM_WMS_SHIP_VIA_TP_ID = "wmsShipViaTpCd";

    /**
     * shpg svc level cd
     */
    String DB_PARAM_SHPG_SVC_LVL_CD = "shpgSvsLvlCd";

    /**
     * carr cd
     */
    String DB_PARAM_CARR_CD = "carrCd";

    /**
     * wms ship id
     */
    String DB_PARAM_WMS_SHIP_ID = "wmsShipId";

    /**
     * bol num
     */
    String DB_PARAM_BOL_NUM = "bolNum";

    /**
     * wms wave id
     */
    String DB_PARAM_WMS_WAVE_ID = "wmsWaveId";

    /**
     * wms wave id
     */
    String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /**
     * wms wave id
     */
    String DB_PARAM_WMS_PACK_CD_SET_OWNER_CD_FLG = "wmsPackCdSetOwnerCdFlg";

    /**
     * wms wave id
     */
    String DB_PARAM_INVTY_OWNR_CD = "invtyOwnrCd";

    /**
     * Conditions of Inboud Transaction
     */
    String DB_PARAM_COND_WMS_INBD_TRX = "condWmsInbdTrx";

    /**
     * check box
     */
    String DB_PARAM_CHECK_BOX = "xxChkBox";

    /**
     * wms mdse code
     */
    String DB_PARAM_ERR_MSG_CD = "errMsgCd";

    /**
     * setting *
     */
    String DB_WH_ALL_VAL = "whAllVal";

    /**
     * whAllSelval means ALL(00) on WMS_WH PullDown.
     */
    String DB_WH_ALL_SEL_VAL = "whAllSelval";

    /**
     * Limit Row Number
     */
    String DB_PARAM_ROWNUM = "rownum";

    /**
     * WMS_UOM_CD EA
     */
    String DB_PARAM_WMS_UOM_CD_EA = "wmsUomCdEa";

    /**
     * WMS_TASK_CD
     */
    String DB_PARAM_WMS_TASK_CD = "wmsTaskCd";

    /**
     * WMS_TASK_CD SHIP
     */
    String DB_PARAM_WMS_TASK_CD_SHIP = "wmsTaskCdShip";

    /**
     * WMS_TASK_CD OSC
     */
    String DB_PARAM_WMS_TASK_CD_OSC = "wmsTaskCdOsc";

    /**
     * WMS_TASK_CD ASN
     */
    String DB_PARAM_WMS_TASK_CD_ASN = "wmsTaskCdAsn";

    /**
     * PROC_STS_CD ERROR
     */
    String DB_PARAM_PROC_STS_CD_ERROR = "procStsCdError";

    /**
     * PROC_STS_CD COMPLEATED
     */
    String DB_PARAM_PROC_STS_CD_COMPLEATED = "procStsCdCompleated";

    /**
     * PROC_STS_CD IN_COMPLEATED
     */
    String DB_PARAM_PROC_STS_CD_IN_COMPLEATED = "procStsCdInCompleated";

    /**
     * Process Processed
     */
    String DB_PARAM_PROCESSED = "processed";

    /**
     * Process UnProcessed
     */
    String DB_PARAM_UN_PROCESSED = "unProcessed";

    /**
     * Process Error
     */
    String DB_PARAM_ERROR = "error";

    /**
     * Process Processing
     */
    String DB_PARAM_PROCESSING = "processing";

    /**
     * Cancel
     */
    String DB_PARAM_CANCEL = "CAN";

    /**
     * WMS_ORD_STS
     */
    String DB_PARAM_WMS_ORD_STS = "wmsOrdSts";

    /**
     * WMS_TXT_CD
     */
    String DB_PARAM_WMS_TXT_CD = "wmsTxtCd";

    /**
     * INBD_OTBD_CD
     */
    String DB_PARAM_INBD_OTBD_CD = "inbdOtbdCd";

    /**
     * Condition of Task Code
     */
    String DB_PARAM_IS_SPECIFIC_TASK_COND = "isSpecificTaskCond";

    /**
     * Condition of search for asn
     */
    String DB_PARAM_IS_SEARCH_TASK_ASN = "isSearchTaskAsn";

    /**
     * EZCancelFlag
     */
    String DB_PARAM_EZCANCELFLAG = "wmsEzCancelFlag";

    /**
     * rowId
     */
    String DB_PARAM_ROWID = "rowId";


    // =================================================
    // DB Select Column Name
    // =================================================
    /**
     * global company code
     */
    String DB_SEL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /**
     * wh code
     */
    String DB_WH_CD = "WH_CD";

    /**
     * value for all wh in data security attribute of wh
     */
    String WH_ALL_VALUE = "*";

    /**
     * blank code
     */
    String BLANK = "";

    /**
     * wms so id
     */
    String WMS_SO_ID = "WMS_SO_ID";

    /**
     * description about wh code
     */
    String DB_WMS_DESC_NM = "WMS_DESC_NM";

    /**
     * wms base uom qty
     */
    String DB_WMS_BASE_UOM_QTY = "WMS_BASE_UOM_QTY";

    /**
     * wms mdse wt
     */
    String DB_WMS_MDSE_WT = "WMS_MDSE_WT";

    /**
     * wms mdse vol
     */
    String DB_WMS_MDSE_VOL = "WMS_MDSE_VOL";

    /**
     * wms otbd so wrk pk
     */
    String DB_WMS_OTBD_SO_WRK_PK = "WMS_OTBD_SO_WRK_PK";

    /**
     * wms otbd so wrk pk
     */
    String DB_WMS_INBD_TRX_PK = "WMS_INBD_TRX_PK";

    /**
     * wms otbd so Record ID
     */
    String DB_WMS_REC_ID = "WMS_REC_ID";

    /**
     * WMS_ORD_TP_CD
     */
    String DB_WMS_ORD_TP_CD = "WMS_ORD_TP_CD";

    /**
     * WMS_ORD_TP_NM
     */
    String DB_WMS_ORD_TP_NM = "WMS_ORD_TP_NM";

    /**
     * WMS_SQ_NUM
     */
    String DB_WMS_SQ_NUM = "WMS_SQ_NUM";

    /**
     * SHPG_SVC_LVL_CD
     */
    String DB_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /**
     * CARR_CD
     */
    String DB_CARR_CD = "CARR_CD";

    /**
     * SHIP_VIA_CD
     */
    String DB_SHIP_VIA_CD = "SHIP_VIA_CD";
    /**
     * SO_NUM
     */
    String DB_SO_NUM = "SO_NUM";

    /**
     * SO_MSG_TP_CD
     */
    String DB_SO_MSG_TP_CD = "SO_MSG_TP_CD";

    /**
     * TXT_SQ_NUM
     */
    String DB_TXT_SQ_NUM = "TXT_SQ_NUM";

    /**
     * WMS_MAINT_ORD_SQ
     */
    String WMS_MAINT_ORD_SQ = "WMS_MAINT_ORD_SQ";

    /**
     * FRT_CHRG_TO_CD
     */
    String FRT_CHRG_TO_CD = "FRT_CHRG_TO_CD";

    /**
     * FRT_CHRG_METH_CD
     */
    String FRT_CHRG_METH_CD = "FRT_CHRG_METH_CD";

    /**
     * SO_CUST_DATA_TP_CD
     */
    String SO_CUST_DATA_TP_CD = "SO_CUST_DATA_TP_CD";

    /**
     * SO_SLP_NUM
     */
    String SO_SLP_NUM = "SO_SLP_NUM";

    /**
     * SO_SER_PK
     */
    String SO_SER_PK = "SO_SER_PK";
    // =================================================
    // name of Table
    // =================================================
    /**
     * TABLE NAME WMS_OTBD_SO_WRK
     */
    String WMS_OTBD_SO_WRK = "WMS_OTBD_SO_WRK";

    /**
     * TABLE NAME WMS_INBD_TRX
     */
    String WMS_INBD_TRX = "WMS_INBD_TRX";

    /**
     * TABLE NAME WMS_INBD_SO_DTLT
     */
    String WMS_INBD_SO_DTL = "WMS_INBD_SO_DTL";

    /**
     * TABLE NAME WMS_INBD_SO_SHIP_TO
     */
    String WMS_INBD_SO_SHIP_TO = "WMS_INBD_SO_SHIP_TO";

    /**
     * TABLE NAME WMS_INBD_SO_TEXT
     */
    String WMS_INBD_SO_TEXT = "WMS_INBD_SO_TEXT";

    /**
     * TABLE NAME WMS_INBD_SO_CHRG_TO
     */
    String WMS_INBD_SO_CHRG_TO = "WMS_INBD_SO_CHRG_TO";

    /**
     * TABLE NAME WMS_INBD_SO_BILL_TO
     */
    String WMS_INBD_SO_BILL_TO = "WMS_INBD_SO_BILL_TO";

    /**
     * TABLE NAME WMS_INBD_SO_RTRN_TO
     */
    String WMS_INBD_SO_RTRN_TO = "WMS_INBD_SO_RTRN_TO";

    /**
     * TABLE NAME SHPG_ORD
     */
    String SHPG_ORD = "SHPG_ORD";

    /**
     * TABLE NAME TPL_CARR_SVC_LVL
     */
    String TPL_CARR_SVC_LVL = "TPL_CARR_SVC_LVL";

    /**
     * TABLE NAME SHPG_ORD_CUST_DTL
     */
    String SHPG_ORD_CUST_DTL = "SHPG_ORD_CUST_DTL";

    /**
     * TABLE NAME SHPG_ORD_DTL
     */
    String SHPG_ORD_DTL = "SHPG_ORD_DTL";

    /**
     * TABLE NAME SO_SER
     */
    String SO_SER = "SO_SER";

    /**
     * TABLE SHPG_ORD_MSG
     */
    String SHPG_ORD_MSG = "SHPG_ORD_MSG";

    // =================================================
    // Message ID
    // =================================================
    /**
     * There are too many search results, there is data that cannot be
     * displayed. ※Warning
     */
    String NZZM0001W = "NZZM0001W";

    /**
     * No search results found. ※Warning
     */
    String NZZM0000E = "NZZM0000E";

    /**
     * Multiple Details cannot be processed at the same time.
     */
    String NLGM0035E = "NLGM0035E";

    /**
     * It has not been selected. Please select.
     */
    String NLGM0036E = "NLGM0036E";

    /**
     * Tab Error
     */
    String NLGM0051E = "NLGM0051E";

    /**
     * [@] cannot be added because the maximum number of line breaks
     * for has been exceeded.
     */
    String NLGM0054E = "NLGM0054E";

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
     * The record cannot be updated. Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    String NLGM0008E = "NLGM0008E";

    /**
     * The process [@] has been successfully completed.
     */
    String NLGM0025I = "NLGM0025I";

    /**
     * The process [@] has been successfully completed.[@]
     */
    String NLGM0073I = "NLGM0073I";

    /**
     * There is a line that not entered both of Inbound Order Number
     * and Outbound Order Number.
     */
    String NLGM0076W = "NLGM0076W";

    /**
     * The entered [@] does not exist in master.
     */
    String NLZM2278E = "NLZM2278E";

    /**
     * Please execute again after correcting the error field.
     */
    String ZZM9037E = "ZZM9037E";

    // =================================================
    // Box Type
    // =================================================
    /**
     * box type (sml)
     */
    String BOX_TYPE_SML = "(SML);";

    /**
     * box type (med)
     */
    String BOX_TYPE_MED = "(MED);";

    /**
     * box type (lrg)
     */
    String BOX_TYPE_LRG = "(LRG);";

    /**
     * box type (lrg)
     */
    String BOX_TYPE_OVR = "(OVERSIZE)";

    /**
     * box type (sml) lg
     */
    BigDecimal BOX_TYPE_SML_LG = new BigDecimal(9);

    /**
     * box type (sml) wdt
     */
    BigDecimal BOX_TYPE_SML_WDT = new BigDecimal(9);

    /**
     * box type (sml) hgt
     */
    BigDecimal BOX_TYPE_SML_HGT = new BigDecimal(9);

    /**
     * box type (sml) vol
     */
    BigDecimal BOX_TYPE_SML_VOL = new BigDecimal(0.42);

    /**
     * box type (med) lg
     */
    BigDecimal BOX_TYPE_MED_LG = new BigDecimal(15);

    /**
     * box type (med) wdt
     */
    BigDecimal BOX_TYPE_MED_WDT = new BigDecimal(15);

    /**
     * box type (med) hgt
     */
    BigDecimal BOX_TYPE_MED_HGT = new BigDecimal(15);

    /**
     * box type (med) vol
     */
    BigDecimal BOX_TYPE_MED_VOL = new BigDecimal(1.95);

    /**
     * box type (lrg) lg
     */
    BigDecimal BOX_TYPE_LRG_LG = new BigDecimal(21);

    /**
     * box type (lrg) wdt
     */
    BigDecimal BOX_TYPE_LRG_WDT = new BigDecimal(20);

    /**
     * box type (lrg) hgt
     */
    BigDecimal BOX_TYPE_LRG_HGT = new BigDecimal(11);

    /**
     * box type (lrg) vol
     */
    BigDecimal BOX_TYPE_LRG_VOL = new BigDecimal(2.67);

    /**
     * box type pct full
     */
    BigDecimal BOX_TYPE_PCT_FILL = new BigDecimal(0.85);

    // =================================================
    // The other
    // =================================================
    /**
     * asterisk
     */
    String ASTERRISK = "*";

    /**
     * colon for delimiter
     */
    String DELIMITER_COLON = ":";

    /**
     * space
     */
    String SPACE = " ";

    /**
     * UnProcessed
     */
    String UN_PROCESSED = "unProcessed";

    /**
     * Processed
     */
    String PROCESSED = "processed";

    /**
     * Error
     */
    String ERROR = "error";

    /**
     * Processing
     */
    String PROCESSING = "processing";

    /**
     * code value of "00:All" in pulldown list of wh"
     */
    String WH_ALL_SELECTION_VALUE = "00";

    /**
     * code value of Record Type
     */
    String RECORD_TYPE_VALUE = "02";

    /**
     * Data value of Record ID
     */
    String DATA_VALUE_0 = "0";

    /**
     * SO Copy Sequence Key
     */
    String SEQ_WMS_ORD_ID = "WMS_ORD_ID";

    /**
     * Primary Key of AUTO_SEQ(REC ID)
     */
    String REC_ID_ONLINE_KEY = "WMS_REC_ID";

    /**
     * base uom qty of wms mdse code "EA"
     */
    BigDecimal MDSE_EA_BASE_UOM_QTY = BigDecimal.ONE;

    /**
     * base weight of wms mdse code "EA"
     */
    BigDecimal MDSE_EA_BASE_WEIGHT = new BigDecimal(0.5);

    /**
     * base volume of wms mdse code "EA"
     */
    BigDecimal MDSE_EA_BASE_VOLUME = new BigDecimal(0.5);

    /**
     * SO Resend Sequence Key
     */
    String SEQ_TO_WMS_DATA_IF_SQ = "TO_WMS_DATA_IF_SQ";

    /**
     * Transaction Sequence digits
     */
    int SEQ_TRX_DIGITS = 7;

    /**
     * Date Type List(Code)
     */
    String[] DATE_TYPE_CODE_LIST = {"3", "1", "2", "4" };

    /**
     * Date Type List(Display)
     */
    String[] DATE_TYPE_NAME_LIST = {"SO Create date", "RSD", "RDD", "Print date" };

    /**
     * Send Type List(Code)
     */
    String[] SEND_TYPE_CODE_LIST = {"1", "2" };

    /**
     * Send Type List(Display)
     */
    String[] SEND_TYPE_NAME_LIST = {"Copy", "Resend" };

    // =================================================
    // Data Value
    // =================================================
    /**
     * Data Value("DELETE")
     */
    String DATA_VALUE_DELETE = "Delete";

    /**
     * Data Value("DELETE")
     */
    String DATA_VALUE_INSERT = "Insert";

    // =================================================
    // name of field
    // =================================================
    /**
     * Check box name
     */
    String FIELD_NAME_CHKBOX_A1 = "xxChkBox_A1";

    /**
     * Check box name
     */
    String FIELD_NAME_CHKBOX_K1 = "xxChkBox_K1";

    /**
     * Check box name
     */
    String FIELD_NAME_CHKBOX_O1 = "xxChkBox_O1";

    // =================================================
    // TAB setting values
    // =================================================
    /** Tab SO List */
    String TAB_SO_LIST = "SOList";

    /** Tab SO Status */
    String TAB_SO_STS = "SOStatus";

    /** Tab SO Download Edit */
    String TAB_SO_DNLD_EDT = "DnldEdt";

    /** Tab SO Upload Edit */
    String TAB_SO_UPLD_EDT = "UpldEdt";

    // =================================================
    // SO_TO_WMS_DATA_TXT
    // =================================================

    /** Detail Type Header */
    String DTL_TP_HDR = "1";

    /** Detail Type Detail */
    String DTL_TP_DTL = "2";

    /** Detail Type Ship */
    String DTL_TP_SHIP = "3";

    /** Detail Type Text */
    String DTL_TP_TXT = "4";

    /** Detail Type Charge */
    String DTL_TP_CHRG = "5";

    /** Detail Type Bill */
    String DTL_TP_BILL = "6";

    /* Header */

    /** Length of WH_CD */
    int LEN_WH_CD = 4;

    /** Length of WMS_CMPY_CD */
    int LEN_WMS_CMPY_CD = 2;

    /** Length of WMS_SO_NUM */
    int LEN_WMS_SO_NUM = 15;

    /** Length of WMS_ORD_NUM */
    int LEN_WMS_ORD_NUM = 15;

    /** Length of ALT_DOC_NUM */
    int LEN_ALT_DOC_NUM = 15;

    /** Length of CUST_ORD_NUM */
    int LEN_CUST_ORD_NUM = 30;

    /** Length of CHRG_TO_CUST_CD */
    int LEN_CHRG_TO_CUST_CD = 10;

    /** Length of BILL_TO_CUST_CD */
    int LEN_BILL_TO_CUST_CD = 10;

    /** Length of SHIP_TO_CUST_CD */
    int LEN_SHIP_TO_CUST_CD = 10;

    /** Length of WMS_PRTY_CD */
    int LEN_WMS_PRTY_CD = 1;

    /** Length of WMS_ORD_TP_CD */
    int LEN_WMS_ORD_TP_CD = 1;

    /** Length of WMS_TRX_CD */
    int LEN_WMS_TRX_CD = 2;

    /** Length of WMS_ORD_SRC_CD */
    int LEN_WMS_ORD_SRC_CD = 2;

    /** Length of WMS_SO_STS_CD */
    int LEN_WMS_SO_STS_CD = 1;

    /** Length of WMS_SHIP_VIA_TP_CD */
    int LEN_WMS_SHIP_VIA_TP_CD = 2;

    /** Length of WMS_DESC_TXT */
    int LEN_WMS_DESC_TXT = 35;

    /** Length of CRAT_DT_TM_TS */
    int LEN_CRAT_DT_TM_TS = 10;

    /** Length of EST_SHIP_DT_TM_TS */
    int LEN_EST_SHIP_DT_TM_TS = 10;

    /** Length of WMS_RQST_DT_TM_TS */
    int LEN_WMS_RQST_DT_TM_TS = 10;

    /** Format */
    String FMT_YYYYMMDD = "yyyyMMdd";

    /** Format */
    String FMT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /** Format */
    String FMT_YYYY_MM_DD = "yyyy/MM/dd";

    /** Format */
    String FMT_HH_MM_SS = "hh:mm:ss";

    /** Format */
    String FMT_HH_MM_SS_DB = "hhmmss";

    /** Length of Date */
    int LEN_YMD_17 = 17;

    /** Length of WMS_FRT_OUT_CD */
    int LEN_WMS_FRT_OUT_CD = 1;

    /** Length of WMS_FRT_OUT_DESC_TXT */
    int LEN_WMS_FRT_OUT_DESC_TXT = 35;

    /** Length of WMS_DEPT_CD */
    int LEN_WMS_DEPT_CD = 5;

    /** Length of PAY_TERM_CD */
    int LEN_PAY_TERM_CD = 2;

    /** Length of WMS_SO_CARR_CD */
    int LEN_WMS_SO_CARR_CD = 4;

    /** Length of IND_OTM_ADD_SWTH_FLG */
    int LEN_IND_OTM_ADD_SWTH_FLG = 1;

    /** Length of IND_ASN_FLG */
    int LEN_IND_ASN_FLG = 1;

    /** Length of IND_SCC_14_FLG */
    int LEN_IND_SCC_14_FLG = 1;

    /** Length of IND_UCC_FLG */
    int LEN_IND_UCC_FLG = 1;

    /** Length of IND_MIXED_PLT_FLG */
    int LEN_IND_MIXED_PLT_FLG = 1;

    /** Length of IND_PLT_LB_FLG */
    int LEN_IND_PLT_LB_FLG = 1;

    /** Length of IND_UCC_NUM_FLG */
    int LEN_IND_UCC_NUM_FLG = 1;

    /** Length of IND_NON_ASN_FLG */
    int LEN_IND_NON_ASN_FLG = 1;

    /** Length of WMS_LB_NUM */
    int LEN_WMS_LB_NUM = 10;

    /** Length of CUST_STORE_NUM */
    int LEN_CUST_STORE_NUM = 8;

    /** Length of CUST_DC_NUM */
    int LEN_CUST_DC_NUM = 8;

    /** Length of WMS_CUST_DEPT_NUM */
    int LEN_WMS_CUST_DEPT_NUM = 5;

    /** Length of TOT_SHIP_PRC_AMT_NUM */
    int LEN_TOT_SHIP_PRC_AMT_NUM = 14;

    /** Length of TOT_SHIP_PRC_AMT_NUM */
    int LEN_DEC_TOT_SHIP_PRC_AMT_NUM = 2;

    /** Length of TP_VND_CD */
    int LEN_TP_VND_CD = 12;

    /** Length of EDI_TRNSP_TP_CD */
    int LEN_EDI_TRNSP_TP_CD = 2;

    /** Length of WMS_PMT_TERM_CD */
    int LEN_WMS_PMT_TERM_CD = 2;

    /** Length of CANC_BY_DT_TM_TS */
    int LEN_CANC_BY_DT_TM_TS = 10;

    /** Length of IND_CONFIG_FLG */
    int LEN_IND_CONFIG_FLG = 1;

    /** Length of HOST_ORD_DT_TM_TS */
    int LEN_HOST_ORD_DT_TM_TS = 10;

    /** Length of WMS_NET_AMT_NUM */
    int LEN_WMS_NET_AMT_NUM = 14;

    /** Length of WMS_NET_AMT_NUM */
    int LEN_DEC_WMS_NET_AMT_NUM = 2;

    /** Length of WMS_NET_DISC_AMT_NUM */
    int LEN_WMS_NET_DISC_AMT_NUM = 14;

    /** Length of WMS_NET_DISC_AMT_NUM */
    int LEN_DEC_WMS_NET_DISC_AMT_NUM = 2;

    /** Length of SHPG_HDLG_AMT_NUM */
    int LEN_SHPG_HDLG_AMT_NUM = 14;

    /** Length of SHPG_HDLG_AMT_NUM */
    int LEN_DEC_SHPG_HDLG_AMT_NUM = 2;

    /** Length of TOT_DISC_AMT_NUM */
    int LEN_TOT_DISC_AMT_NUM = 14;

    /** Length of TOT_DISC_AMT_NUM */
    int LEN_DEC_TOT_DISC_AMT_NUM = 2;

    /** Length of SHPG_HDLG_DISC_AMT_NUM */
    int LEN_SHPG_HDLG_DISC_AMT_NUM = 14;

    /** Length of SHPG_HDLG_DISC_AMT_NUM */
    int LEN_DEC_SHPG_HDLG_DISC_AMT_NUM = 2;

    /** Length of NET_TAX_AMT_NUM */
    int LEN_NET_TAX_AMT_NUM = 14;

    /** Length of NET_TAX_AMT_NUM */
    int LEN_DEC_NET_TAX_AMT_NUM = 2;

    /** Length of SHPG_HDLG_TAX_AMT_NUM */
    int LEN_SHPG_HDLG_TAX_AMT_NUM = 14;

    /** Length of SHPG_HDLG_TAX_AMT_NUM */
    int LEN_DEC_SHPG_HDLG_TAX_AMT_NUM = 2;

    /** Length of TOT_TAX_AMT_NUM */
    int LEN_TOT_TAX_AMT_NUM = 14;

    /** Length of TOT_TAX_AMT_NUM */
    int LEN_DEC_TOT_TAX_AMT_NUM = 2;

    /** Length of TOT_ORD_AMT_NUM */
    int LEN_TOT_ORD_AMT_NUM = 14;

    /** Length of TOT_ORD_AMT_NUM */
    int LEN_DEC_TOT_ORD_AMT_NUM = 2;

    /** Length of TOT_ORD_QTY */
    int LEN_TOT_ORD_QTY = 9;

    /** Length of TOT_ORD_QTY */
    int LEN_DEC_TOT_ORD_QTY = 0;

    /** Length of IND_SGN_REQ_FLG */
    int LEN_IND_SGN_REQ_FLG = 1;

    /** Length of BILL_ACCT_NUM */
    int LEN_BILL_ACCT_NUM = 20;

    /** Length of END_CUST_ORD_NUM */
    int LEN_END_CUST_ORD_NUM = 35;

    /** Length of ALT_CUST_ORD_NUM */
    int LEN_ALT_CUST_ORD_NUM = 35;

    /** Length of Filler */
    int LEN_HDR_FILLER = 117;

    /* Ship, Charge, Bill */
    /** Company Code */
    String CMPY_CD_01 = "01";

    /** Length of WMS_CUST_CD */
    int LEN_WMS_CUST_CD = 10;

    /** Length of TO_NM_01 */
    int LEN_WMS_TO_NM_01 = 35;

    /** Length of TO_NM_02 */
    int LEN_WMS_TO_NM_02 = 35;

    /** Length of FIRST_LINE_ADDR */
    int LEN_FIRST_LINE_ADDR = 35;

    /** Length of SCD_LINE_ADDR */
    int LEN_SCD_LINE_ADDR = 35;

    /** Length of THIRD_LINE_ADDR */
    int LEN_THIRD_LINE_ADDR = 35;

    /** Length of FRTH_LINE_ADDR */
    int LEN_FRTH_LINE_ADDR = 35;

    /** Length of CTY_ADDR */
    int LEN_CTY_ADDR = 20;

    /** Length of ST_CD */
    int LEN_ST_CD = 2;

    /** Length of POST_CD */
    int LEN_POST_CD = 15;

    /** Length of CTRY_CD */
    int LEN_CTRY_CD = 2;

    /** Length of CTAC_NM */
    int LEN_WMS_CTAC_NM = 25;

    /** Length of CTAC_NUM */
    int LEN_CTAC_NUM = 15;

    /** Length of Filler */
    int LEN_SHIP_FILLER = 365;

    /** Length of Filler */
    int LEN_BILL_FILLER = 365;

    /** Length of Filler */
    int LEN_CHRG_FILLER = 365;

    /* DTL */
    /** Length of WMS_LINE_NUM */
    int LEN_WMS_LINE_NUM = 3;

    /** Length of WMS_LINE_NUM */
    int LEN_DEC_WMS_LINE_NUM = 0;

    /** Length of WMS_MDSE_CD */
    int LEN_WMS_MDSE_CD = 25;

    /** Length of S80_STK_STS_CD */
    int LEN_S80_STK_STS_CD = 2;

    /** Length of CUST_MDSE_CD */
    int LEN_CUST_MDSE_CD = 25;

    /** Length of WMS_ORD_QTY */
    int LEN_WMS_ORD_QTY = 9;

    /** Length of WMS_ORD_QTY */
    int LEN_DEC_WMS_ORD_QTY = 0;

    /** Length of BACK_ORD_QTY_NUM */
    int LEN_BACK_ORD_QTY_NUM = 9;

    /** Length of BACK_ORD_QTY_NUM */
    int LEN_DEC_BACK_ORD_QTY_NUM = 0;

    /** Length of WMS_SHIP_QTY */
    int LEN_WMS_SHIP_QTY = 9;

    /** Length of WMS_SHIP_QTY */
    int LEN_DEC_WMS_SHIP_QTY = 0;

    /** Length of UNIT_PRC_AMT_NUM */
    int LEN_UNIT_PRC_AMT_NUM = 14;

    /** Length of UNIT_PRC_AMT_NUM */
    int LEN_DEC_UNIT_PRC_AMT_NUM = 2;

    /** Length of UNIT_DISC_AMT_NUM */
    int LEN_UNIT_DISC_AMT_NUM = 14;

    /** Length of UNIT_DISC_AMT_NUM */
    int LEN_DEC_UNIT_DISC_AMT_NUM = 2;

    /** Length of UNIT_DISC_PRC_AMT_NUM */
    int LEN_UNIT_DISC_PRC_AMT_NUM = 14;

    /** Length of UNIT_DISC_PRC_AMT_NUM */
    int LEN_DEC_UNIT_DISC_PRC_AMT_NUM = 2;

    /** Length of WMS_TOT_AMT_NUM */
    int LEN_WMS_TOT_AMT_NUM = 14;

    /** Length of WMS_TOT_AMT_NUM */
    int LEN_DEC_WMS_TOT_AMT_NUM = 2;

    /** Length of IND_SER_ID */
    int LEN_IND_SER_ID = 1;

    /** Length of IND_VOID_ALLW_FLG */
    int LEN_IND_VOID_ALLW_FLG = 1;

    /** Length of S80_STK_STS_CD_TO_CD */
    int LEN_S80_STK_STS_CD_TO_CD = 2;

    /** Length of MDSE_CD_SET_CD */
    int LEN_MDSE_CD_SET_CD = 25;

    /** Length of MDSE_CD_SET_DSEC_TXT */
    int LEN_MDSE_CD_SET_DSEC_TXT = 35;

    /** Length of SHIP_SET_QTY */
    int LEN_SHIP_SET_QTY = 9;

    /** Length of SHIP_SET_QTY */
    int LEN_DEC_SHIP_SET_QTY = 0;

    /** Length of UNIT_INSD_QTY */
    int LEN_UNIT_INSD_QTY = 9;

    /** Length of UNIT_INSD_QTY */
    int LEN_DEC_UNIT_INSD_QTY = 0;

    /** Length of ESS_PO_SQ_NUM */
    int LEN_ESS_PO_SQ_NUM = 3;

    /** Length of ESS_PO_SQ_NUM */
    int LEN_DEC_ESS_PO_SQ_NUM = 0;

    /** Length of ESS_MDSE_LINE_NUM */
    int LEN_ESS_MDSE_LINE_NUM = 3;

    /** Length of ESS_MDSE_LINE_NUM */
    int LEN_DEC_ESS_MDSE_LINE_NUM = 0;

    /** Length of ESS_LINE_NUM */
    int LEN_ESS_LINE_NUM = 2;

    /** Length of ESS_LINE_NUM */
    int LEN_DEC_ESS_LINE_NUM = 0;

    /** Length of ESS_MSG_LINE_NUM */
    int LEN_ESS_MSG_LINE_NUM = 2;

    /** Length of ESS_MSG_LINE_NUM */
    int LEN_DEC_ESS_MSG_LINE_NUM = 0;

    /** Length of TOT_WT_AMT_NUM */
    int LEN_TOT_WT_AMT_NUM = 8;

    /** Length of TOT_WT_AMT_NUM */
    int LEN_DEC_TOT_WT_AMT_NUM = 2;

    /** Length of TOT_VOL_AMT_NUM */
    int LEN_TOT_VOL_AMT_NUM = 12;

    /** Length of TOT_VOL_AMT_NUM */
    int LEN_DEC_TOT_VOL_AMT_NUM = 4;

    /** Length of SO_MDSE_TP_CD */
    int LEN_SO_MDSE_TP_CD = 20;

    /** Length of WMS_PACK_TP_CD */
    int LEN_WMS_PACK_TP_CD = 30;

    /** Length of BAT_CPTR_REQ_FLG */
    int LEN_BAT_CPTR_REQ_FLG = 1;

    /** Length of WMS_DISC_AMT_NUM */
    int LEN_WMS_DISC_AMT_NUM = 14;

    /** Length of WMS_DISC_AMT_NUM */
    int LEN_DEC_WMS_DISC_AMT_NUM = 2;

    /** Length of WMS_TAX_AMT_NUM */
    int LEN_WMS_TAX_AMT_NUM = 14;

    /** Length of WMS_TAX_AMT_NUM */
    int LEN_DEC_WMS_TAX_AMT_NUM = 2;

    /** Length of Filler */
    int LEN_DTL_FILLER = 320;

    /* Text */
    /** Length of WMS_TXT_CD */
    int LEN_WMS_TXT_CD = 1;

    /** Length of WMS_PRINT_TP_CD */
    int LEN_WMS_PRINT_TP_CD = 1;

    /** Length of INBD_SO_MSG_TXT */
    int LEN_INBD_SO_MSG_TXT = 65;

    /** Length of Filler */
    int LEN_TXT_FILLER = 402;

    // =================================================
    // Others
    // =================================================

    /** int 0 */
    int DATA_VALUE_INT_0 = 0;

    /** Type copy */
    String TP_CD_COPY = "1";

    /** Type Resend */
    String TP_CD_RESEND = "2";

    /**
     * yyyyMMddHHmmss for EZUptime before
     */
    String YYYYMMDDHHMMSS_BEFORE = "yyyyMMddHHmmss";

    /**
     * yyyyMMddHHmmssSSS for EZUptime before
     */
    String YYYYMMDDHHMMSSSSS_BEFORE = "yyyyMMddHHmmssSSS";

    /**
     * yyyyMMddHHmmss for EZUptime after
     */
    String YYYYMMDDHHMMSS_AFTER = "MM/dd/yyyy HH:mm:ss";

    /**
     * yyyyMMddHH for EZUptime after
     */
    String YYYYMMDD_AFTER = "yyyyMMdd";

    /** To judge about set mm:ss */
    boolean SET_MMSS = true;

    /** To judge about set mm:ss */
    boolean UNSET_MMSS = false;

    /** INBD_OTBD_CD OTBD */
    String INBD_OTBD_CD_INBD = "1";

    /** INBD_OTBD_CD OTBD */
    String INBD_OTBD_CD_OTBD = "2";

    /** WMS_SO_ID Prefix */
    String WMS_SO_ID_PFX = "Z";

    /** Length of Unique So Id */
    int LEN_UNIQUE_SO_ID_START = 1;

    /** Length of Unique So Id */
    int LEN_UNIQUE_SO_ID_END = 6;

    /** custSKU */
    String CUST_SKU = "custSKU";

    /** Data Row */
    String DATA_ROW = "Data Row";

    /** Value of WH_CD All */
    String VAL_WH_CD_NM_ALL = "ALL";

    /** Value of WMS_TASK_CD All */
    String VAL_WMS_TASK_CD_ALL = "-";

    /** Value of WMS_TASK_CD All */
    String VAL_WMS_TASK_NM_ALL = "ALL";

    /** Prefix of Stock Status */
    String VAL_STK_STS_CD_PFX = "S";

    /** Value of Active Status */
    String VAL_Actv_ = "Y";

    /** Value of InActive Status */
    String VAL_InActv_ = "N";

    /**
     * VAR_CHAR_CONST Key Name: Valid copy flag
     */
    String NLGL_VALID_COPY_FLG = "NLGL_VALID_COPY_FLG";

    /**
     * VAR_CHAR_CONST Key Name: WMS Package Code Set Owner Code Flag
     */
    String WMS_PACK_CD_SET_OWNER_CD_FLG = "WMS_PACK_CD_SET_OWNER_CD_FLG";

    /**
     * Retail Warehouse code
     */
    String DB_RTL_WH_CD = "RTL_WH_CD";

    /**
     * Retail Warehouse Name
     */
    String DB_RTL_WH_NM = "RTL_WH_NM";

    /**
     * Package Code Text
     */
    String DB_PACK_CD_TXT = "PACK_CD_TXT";

    /**
     *3PL ShipVia Code
     */
    String DB_TPL_SVC_LVL_CD = "TPL_SVC_LVL_CD";

    /**
     *3PL ShipVia Name
     */
    String DB_TPL_SVC_LVL_NM = "TPL_SVC_LVL_NM";
}
