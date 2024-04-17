/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1360.constant;

/**
 *<pre>
 * Business ID : NPAL1360 Kitting Work Order Entry
 * Function Name : Constant Value Definition
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2016   CITS            Keiichi Masaki  Create          N/A
 * 03/14/2018   CITS            K.Fukumura      Update          QC#22324
 *</pre>
 */

public class NPAL1360Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1360";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1360Scrn00";
    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NPAL1360_INIT = "NPAL1360_INIT";

    /**
     * Event Name : Cancel
     */
    public static final String EVENT_NM_NPAL1360_CANCEL = "NPAL1360Scrn00_Cancel";

    /**
     * Event Name : CMN_Clear
     */
    public static final String EVENT_NM_NPAL1360_CMN_CLEAR = "NPAL1360Scrn00_CMN_Clear";

    /**
     * Event Name : CMN_Reset
     */
    public static final String EVENT_NM_NPAL1360_CMN_RESET = "NPAL1360Scrn00_CMN_Reset";

    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NPAL1360_CMN_SUBMIT = "NPAL1360Scrn00_CMN_Submit";

    /**
     * Event Name : Component
     */
    public static final String EVENT_NM_NPAL1360_COMPONENT = "NPAL1360Scrn00_Component";

    /**
     * Event Name : OpenWin_CompletionSwh
     */
    public static final String EVENT_NM_NPAL1360_OPENWIN_COMPLETION_SWH = "NPAL1360Scrn00_OpenWin_CompletionSwh";

    /**
     * Event Name : OpenWin_KitItem
     */
    public static final String EVENT_NM_NPAL1360_OPEN_WIN_KIT_ITEM = "NPAL1360Scrn00_OpenWin_KitItem";

    /**
     * Event Name : OpenWin_KitSerial
     */
    public static final String EVENT_NM_NPAL1360_OPEN_WIN_KIT_SERIAL = "NPAL1360Scrn00_OpenWin_KitSerial";

    /**
     * Event Name : OpenWin_SupplySerial
     */
    public static final String EVENT_NM_NPAL1360_OPEN_WIN_SUPPLY_SERIAL = "NPAL1360Scrn00_OpenWin_SupplySerial";

    /**
     * Event Name : OpenWin_SupplySwh
     */
    public static final String EVENT_NM_NPAL1360_OPEN_WIN_SUPPLY_SWH = "NPAL1360Scrn00_OpenWin_SupplySwh";

    /**
     * Event Name : OpenWin_Wh
     */
    public static final String EVENT_NM_NPAL1360_OPEN_WIN_WH = "NPAL1360Scrn00_OpenWin_Wh";

    /**
     * Event Name : Print
     */
    public static final String EVENT_NM_NPAL1360_PRINT = "NPAL1360Scrn00_Print";

    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NPAL1360_SEARCH = "NPAL1360Scrn00_Search";

    /**
     * Event Name : SetKitItemName
     */
    public static final String EVENT_NM_NPAL1360_SET_KIT_ITEM_NAME = "NPAL1360Scrn00_SetKitItemName";

    /**
     * Event Name : SetWhName
     */
    public static final String EVENT_NM_NPAL1360_SET_WH_NAME = "NPAL1360Scrn00_SetWhName";

    /**
     * Event Name : SetSwhName
     */
    public static final String EVENT_NM_NPAL1360_SET_SWH_NAME = "NPAL1360Scrn00_SetSwhName";

    /**
     * Button Event Name : Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Button Event Name : Submit
     */
    public static final String BTN_SUBMIT = "Submit";

    /**
     * Button Event Name : Component
     */
    public static final String BTN_COMPONENT = "Component";

    /**
     * Button Event Name : Cancel
     */
    public static final String BTN_CANCEL = "Cancel";

    /**
     * Button Event Name : Print
     */
    public static final String BTN_PRINT = "Print";

    /**
     * Work Order Report ID
     */
    public static final String WO_RPT_ID = "WO_RPT_ID";

    // =================================================
    // DB Parameter
    // =================================================
    /**
     * DB Parameter Name : cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Parameter Name : sMsg
     */
    public static final String DB_PARAM_SMSG = "sMsg";

    /**
     * DB Parameter Name : Work Order Line Number
     */
    public static final String DB_PARAM_WRK_ORD_DTL_LINE_NUM = "wrkOrdDtlLineNum";

    /**
     * DB Parameter Name : Work Order Line Sub Number
     */
    public static final String DB_PARAM_WRK_ORD_DTL_LINE_SUB_NUM = "wrkOrdDtlLineSubNum";

    /**
     * DB Parameter Name : Location Status Code
     */
    public static final String DB_PARAM_LOC_STS_CD = "locStsCd";

    /**
     * DB Parameter Name : Stock Status Code
     */
    public static final String DB_PARAM_STK_STS_CD = "stkStsCd";

    /**
     * DB Parameter Name : Item Type Kit
     */
    public static final String DB_PARAM_KIT_ITEM = "kitItem";

    /**
     * DB Parameter Name : Merchandise Code
     */
    public static final String DB_PARAM_MDSE_CD = "mdseCd";

    /**
     * DB Parameter Name : Order Quantity
     */
    public static final String DB_PARAM_ORD_QTY = "ordQty";

    /**
     * DB Parameter Name : Inventory Location Code
     */
    public static final String DB_PARAM_INVTY_LOC_CD = "invtyLocCd";

    /**
     * DB Parameter Name : Retail Warehouse Code
     */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /**
     * DB Parameter Name : Serial#
     */
    public static final String DB_PARAM_SER_NUM = "serNum";

    /**
     * DB Parameter Name : Shipping Status
     */
    public static final String DB_PARAM_SHPG_STS_CD = "shpgStsCd";

    /**
     * DB Parameter Name : Service Machine Master Status Code
     */
    public static final String DB_PARAM_SVC_MACH_MSTR_STS_CD = "svcMachMstrStsCd";

    /**
     * DB Parameter Name : HIST_ACT_NM
     */
    public static final String DB_PARAM_HIST_ACT_NM = "histActNm";

    /**
     * DB Parameter Name : DB_PARAM_LIMIT_EFF_THRU_DT
     */
    public static final String DB_PARAM_LIMIT_EFF_THRU_DT = "limitEffThruDt";

    /**
     * DB Parameter Name : HIST_ACT_NM VALUE DELETE
     */
    public static final String HIST_ACT_NM_DELETE = "DELETE";

    /**
     * DB Parameter Name : ROWNUM
     */
    public static final String DB_PARAM_ROWNUM = "rowNum";

    /**
     * DB Parameter Name : ROWNUM VALUE 1
     */
    public static final int ROWNUM_1 = 1;

    /**
     * DB Parameter Name : Limit Date
     */
    public static final String LIMIT_DT = "99991231";

    /**
     * Index 0
     */
    public static final int INDEX_0 = 0;

    /**
     * Index 1
     */
    public static final int INDEX_1 = 1;

    /**
     * Work Order Number
     */
    public static final String WRK_ORD_NUM = "WO#_ONL";

    /**
     * Kit Item Line# 000
     */
    public static final String KIT_ITEM_LINE_NUM = "000";

    /**
     * Work Order Sub Line# 001
     */
    public static final String WRK_ORD_SUB_LINE_NUM = "001";

    /**
     * Merchandise Type Set Child
     */
    public static final String SET_CHILD = "Set Child";

    /**
     * Reprint
     */
    public static final String REPRINT = "REPRINT";

    /**
     * Return Code : Validated
     */
    public static final String VALIDATED = "0";

    /**
     * Return Code : No Need to enter Serial#
     */
    public static final String NO_NEED_TO_ENTER_SERIAL = "1";

    /**
     * Return Code Error
     */
    public static final String ERROR = "9";

    /**
     * XX_PROC_TP_CD OUTBOUND
     */
    public static final String OUTBOUND = "01";

    /**
     * XX_PROC_TP_CD INBOUND
     */
    public static final String INBOUND = "02";

    /**
     * Process Mode ALLOC-ON
     */
    public static final String ALLOC_ON = "18";

    // =================================================
    // DB Column Name
    // =================================================
    /**
     * DB Column Name : GLBL_CMPY_CD
     */
    public static final String DB_COLUMN_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /**
     * DB Column Name : WRK_ORD_NUM
     */
    public static final String DB_COLUMN_WRK_ORD_NUM = "WRK_ORD_NUM";

    /**
     * DB Column Name : WRK_ORD_DTL_LINE_NUM
     */
    public static final String DB_COLUMN_WRK_ORD_DTL_LINE_NUM = "WRK_ORD_DTL_LINE_NUM";

    /**
     * DB Column Name : MDSE_CD
     */
    public static final String DB_COLUMN_MDSE_CD = "MDSE_CD";

    /**
     * DB Column Name : MDSE_NM
     */
    public static final String DB_COLUMN_MDSE_NM = "MDSE_NM";

    /**
     * DB Column Name : MDSE_DESC_SHORT_TXT
     */
    public static final String DB_COLUMN_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /**
     * DB Column Name : FIRST_PROD_CTRL_CD
     */
    public static final String DB_COLUMN_FIRST_PROD_CTRL_CD = "FIRST_PROD_CTRL_CD";

    /**
     * DB Column Name : RCV_SER_TAKE_FLG
     */
    public static final String DB_COLUMN_RCV_SER_TAKE_FLG = "RCV_SER_TAKE_FLG";

    /**
     * DB Column Name : EFF_FROM_DT
     */
    public static final String DB_COLUMN_EFF_FROM_DT = "EFF_FROM_DT";

    /**
     * DB Column Name : EFF_THRU_DT
     */
    public static final String DB_COLUMN_EFF_THRU_DT = "EFF_THRU_DT";

    /**
     * DB Column Name : CMPSN_REVN_NUM
     */
    public static final String DB_COLUMN_CMPSN_REVN_NUM = "CMPSN_REVN_NUM";

    /**
     * DB Column Name : INVTY_CTRL_FLG
     */
    public static final String DB_COLUMN_INVTY_CTRL_FLG = "INVTY_CTRL_FLG";

    /**
     * DB Column Name : INSTL_BASE_CTRL_FLG
     */
    public static final String DB_COLUMN_INSTL_BASE_CTRL_FLG = "INSTL_BASE_CTRL_FLG";

    /**
     * DB Column Name : EFF_THRU_DT
     */
    public static final String DB_COLUMN_BASE_PKG_UOM_CD = "BASE_PKG_UOM_CD";

    /**
     * DB Column Name : MDSE_TP_CD
     */
    public static final String DB_COLUMN_MDSE_TP_CD = "MDSE_TP_CD";

    /**
     * DB Column Name : MDSE_TP_DESC_TXT
     */
    public static final String DB_COLUMN_MDSE_TP_DESC_TXT = "MDSE_TP_DESC_TXT";

    /**
     * DB Column Name : ORD_QTY
     */
    public static final String DB_COLUMN_ORD_QTY = "ORD_QTY";

    /**
     * DB Column Name : CHILD_MDSE_QTY
     */
    public static final String DB_COLUMN_CHILD_MDSE_QTY = "CHILD_MDSE_QTY";

    /**
     * DB Column Name : SER_NUM
     */
    public static final String DB_COLUMN_SER_NUM = "SER_NUM";

    /**
     * DB Column Name : INVTY_AVAL_QTY
     */
    public static final String DB_COLUMN_INVTY_AVAL_QTY = "INVTY_AVAL_QTY";

    /**
     * DB Column Name : SHPG_PLN_NUM
     */
    public static final String DB_COLUMN_SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /**
     * DB Column Name : SVC_MACH_MSTR_PK
     */
    public static final String DB_COLUMN_SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /**
     * DB Column Name : RWS_NUM
     */
    public static final String DB_COLUMN_RWS_NUM = "RWS_NUM";

    /**
     * DB Column Name : TRX_ORD_NUM
     */
    public static final String DB_COLUMN_TRX_ORD_NUM = "TRX_ORD_NUM";

    /**
     * DB Column Name : RWS_REF_NUM
     */
    public static final String DB_COLUMN_RWS_REF_NUM = "RWS_REF_NUM";

    /**
     * DB Column Name : WH_CD
     */
    public static final String DB_COLUMN_WH_CD = "WH_CD";

    /**
     * DB Column Name : WMS_DROP_STS_CD
     */
    public static final String DB_COLUMN_WMS_DROP_STS_CD = "WMS_DROP_STS_CD";

    /**
     * DB Column Name : FNSH_GOODS_MDSE_CD
     */
    public static final String DB_COLUMN_FNSH_GOODS_MDSE_CD = "FNSH_GOODS_MDSE_CD";

    /**
     * DB Column Name : FNSH_GOODS_ORD_QTY
     */
    public static final String DB_COLUMN_FNSH_GOODS_ORD_QTY = "FNSH_GOODS_ORD_QTY";

    /**
     * DB Column Name : RQST_RCV_DT
     */
    public static final String DB_COLUMN_RQST_RCV_DT = "RQST_RCV_DT";

    /**
     * DB Column Name : KIT_BOM_PRINT_FLG
     */
    public static final String DB_COLUMN_KIT_BOM_PRINT_FLG = "KIT_BOM_PRINT_FLG";

    /**
     * DB Column Name : ORIG_GOODS_MDSE_CD
     */
    public static final String DB_COLUMN_ORIG_GOODS_MDSE_CD = "ORIG_GOODS_MDSE_CD";

    /**
     * DB Column Name : ORIG_GOODS_ORD_QTY
     */
    public static final String DB_COLUMN_ORIG_GOODS_ORD_QTY = "ORIG_GOODS_ORD_QTY";

    /**
     * DB Column Name : FNSH_MDSE_DESC_SHORT_TXT
     */
    public static final String DB_COLUMN_FNSH_MDSE_DESC_SHORT_TXT = "FNSH_MDSE_DESC_SHORT_TXT";

    /**
     * DB Column Name : ORIG_MDSE_DESC_SHORT_TXT
     */
    public static final String DB_COLUMN_ORIG_MDSE_DESC_SHORT_TXT = "ORIG_MDSE_DESC_SHORT_TXT";

    /**
     * DB Column Name : DS_WRK_ORD_TP_DESC_TXT
     */
    public static final String DB_COLUMN_DS_WRK_ORD_TP_DESC_TXT = "DS_WRK_ORD_TP_DESC_TXT";

    // QC#22324 2018/03/14 Start
    /**
     * DB Column Name : WRK_ORD_MSG_TXT
     */
    public static final String DB_COLUMN_WRK_ORD_MSG_TXT = "WRK_ORD_MSG_TXT";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_01
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_01 = "BOM_KIT_CMNT_TXT_01";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_02
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_02 = "BOM_KIT_CMNT_TXT_02";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_03
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_03 = "BOM_KIT_CMNT_TXT_03";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_04
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_04 = "BOM_KIT_CMNT_TXT_04";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_05
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_05 = "BOM_KIT_CMNT_TXT_05";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_06
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_06 = "BOM_KIT_CMNT_TXT_06";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_07
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_07 = "BOM_KIT_CMNT_TXT_07";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_08
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_08 = "BOM_KIT_CMNT_TXT_08";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_09
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_09 = "BOM_KIT_CMNT_TXT_09";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_10
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_10 = "BOM_KIT_CMNT_TXT_10";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_11
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_11 = "BOM_KIT_CMNT_TXT_11";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_12
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_12 = "BOM_KIT_CMNT_TXT_12";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_13
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_13 = "BOM_KIT_CMNT_TXT_13";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_14
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_14 = "BOM_KIT_CMNT_TXT_14";

    /**
     * DB Column Name : BOM_KIT_CMNT_TXT_15
     */
    public static final String DB_COLUMN_BOM_KIT_CMNT_TXT_15 = "BOM_KIT_CMNT_TXT_15";

    /**
     * DB Column Name : SO_NUM
     */
    public static final String DB_COLUMN_SO_NUM = "SO_NUM";

    /**
     * DB Column Name : RTL_WH_CD
     */
    public static final String DB_COLUMN_RTL_WH_CD = "RTL_WH_CD";

    /**
     * DB Column Name : RTL_WH_NM
     */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";

    /**
     * DB Column Name : WH_OWNR_CD
     */
    public static final String DB_COLUMN_WH_OWNR_CD = "WH_OWNR_CD";

    /**
     * DB Column Name : SPLY_RTL_SWH_CD
     */
    public static final String DB_COLUMN_SPLY_RTL_SWH_CD = "SPLY_RTL_SWH_CD";

    // QC#22324 2018/03/14 End
    /**
     * DB Column Name : ATTN_NM
     */
    public static final String DB_COLUMN_ATTN_NM = "ATTN_NM";

    /**
     * DB Column Name : FIRST_LINE_ADDR
     */
    public static final String DB_COLUMN_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /**
     * DB Column Name : SCD_LINE_ADDR
     */
    public static final String DB_COLUMN_SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /**
     * DB Column Name : THIRD_LINE_ADDR
     */
    public static final String DB_COLUMN_THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /**
     * DB Column Name : FRTH_LINE_ADDR
     */
    public static final String DB_COLUMN_FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /**
     * DB Column Name : FIFTH_LINE_ADDR
     */
    public static final String DB_COLUMN_FIFTH_LINE_ADDR = "FIFTH_LINE_ADDR";

    /**
     * DB Column Name : SIXTH_LINE_ADDR
     */
    public static final String DB_COLUMN_SIXTH_LINE_ADDR = "SIXTH_LINE_ADDR";

    /**
     * DB Column Name : USR_ID
     */
    public static final String DB_COLUMN_USR_ID = "USR_ID";

    /**
     * DB Column Name : RCV_RPT_TS
     */
    public static final String DB_COLUMN_BOM_RPT_TS = "BOM_RPT_TS";

    /**
     * DB Column Name : INTL_LANG_VAL_COL_NM
     */
    public static final String DB_COLUMN_INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    /**
     * DB Column Name : COUNT
     */
    public static final String DB_COLUMN_COUNT = "COUNT";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NZZM0010E: The data [@] does not exist in the master.
     */
    public static final String NZZM0010E = "NZZM0010E";

    /**
     * NMAM8181W: Search results exceeded [@]. Only showing first @ records.
     */
    public static final String NMAM8181W = "NMAM8181W";

    /**
     * NMAM0038I: No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * NPAM1519E: Item code [@] is not serialized item.
     */
    public static final String NPAM1519E = "NPAM1519E";

    /**
     * NPAM1520E: When "Order Qty" is 1, please enter the serial# text field.
     */
    public static final String NPAM1520E = "NPAM1520E";

    /**
     * NPAM1361E: The entered @ does not exist in Master.
     */
    public static final String NPAM1361E = "NPAM1361E";

    /**
     * NPAM0224E: Valid [@] does not exist in master.
     */
    public static final String NPAM0224E = "NPAM0224E";

    /**
     * NAMM0027E: Please Enter @.
     */
    public static final String NAMM0027E = "NAMM0027E";

    /**
     * NPAM0006E: This data has been updated by another user.
     */
    public static final String NPAM0006E = "NPAM0006E";

    /**
     * NPAM1523E: Composition master of the kit item changed. Please be "Component" again.
     */
    public static final String NPAM1523E = "NPAM1523E";

    /**
     * NPAM1524W: Inventory of the [@] could not allocation. They are not in stock.
     */
    public static final String NPAM1524W = "NPAM1524W";

    /**
     * NLZM2324E: The specified serial number is the component of the other configuration.
     */
    public static final String NLZM2324E = "NLZM2324E";

    /**
     * NLBM0024E: @ ended abnormally.
     */
    public static final String NLBM0024E = "NLBM0024E";

    /**
     * NWZM0452E: Hard Allocation could not be executed.
     */
    public static final String NWZM0452E = "NWZM0452E";

    /**
     * NPAM1525W: Hard Allocation could not be executed.
     */
    public static final String NPAM1525W = "NPAM1525W";

    /**
     * NPAM1526I: A new work order of the unallocated amount was created by "Saved" status. WO#:[@]
     */
    public static final String NPAM1526I = "NPAM1526I";

    /**
     * ZZZM9003I: The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * NPAM1527E: Entered Work Order# has been changed. Please search again.
     */
    public static final String NPAM1527E = "NPAM1527E";

    /**
     * NLAM1278W: The selected RWS has already been sent to WMS. Manual cancel is needed from within WMS. Press button again to continue.
     */
    public static final String NLAM1278W = "NLAM1278W";

    /**
     * NPAM1529E: Entered [@] has been changed. Please "Component" again.
     */
    public static final String NPAM1529E = "NPAM1529E";

    /**
     * ZZXM0001E: [@]
     */
    public static final String ZZXM0001E = "ZZXM0001E";

    /**
     * Message Parameter Name : MAX_ROW 200
     */
    public static final String MAX_ROW = "200";

    /**
     * Message Parameter Name : Serial#
     */
    public static final String SERIAL_NUM = "Serial#";

    /**
     * Message Parameter Name : Combination WH/SWH
     */
    public static final String COMBINATION_WH_SWH = "Combination for Warehouse and Completion SWH";

    /**
     * Message Parameter Name : Kit Item
     */
    public static final String KIT_ITEM = "Kit Item";

    /**
     * Message Parameter Name : Component Item
     */
    public static final String COMPONENT_ITEM = "Component Item";

    /**
     * Message Parameter Name : Kit Item Serial#
     */
    public static final String KIT_ITEM_SERIAL = "Kit Item Serial#";

    /**
     * Message Parameter Name : Component Item Serial#
     */
    public static final String COMPONENT_ITEM_SERIAL = "Component Item Serial#";

    /**
     * Message Parameter Name : Delete Work Order
     */
    public static final String DELETE_WRK_ORD = "Work Order delete";

    /**
     * Message Parameter Name : Insert Work Order
     */
    public static final String INSERT_WRK_ORD = "Work Order Insert";

    /**
     * Message Parameter Name : Insert Work Order BOM
     */
    public static final String INSERT_WRK_ORD_BOM = "Work Order BOM Insert";

    /**
     * Message Parameter Name : Update Work Order
     */
    public static final String UPDATE_WRK_ORD = "Work Order update";

    /**
     * Message Parameter Name : Work Order Type
     */
    public static final String WORK_ORDER_TYPE = "Work Order Type";

    /**
     * Message Parameter Name : Warehouse
     */
    public static final String WAREHOUSE = "Warehouse";

    /**
     * Message Parameter Name : Completion SWH
     */
    public static final String COMPLETION_SWH = "Completion SWH";

    /**
     * Message Parameter Name : Order Quantity
     */
    public static final String ORDER_QTY = "Order Qty";

    /**
     * DB access parts return code : Normal
     */
    public static final String DB_ACCESS_PARTS_RETURN_CODE_NORMAL = "0000";

    /**
     * DB access parts return code : No Data Found
     */
    public static final String DB_ACCESS_PARTS_NO_DATA_FOUND = "2000";

    // =================================================
    // API Parameter
    // =================================================
    /**
     * API Parameter Name : Mode Update
     */
    public static final String MODE_UPDATE = "2";

    /**
     * API Parameter Name : Event ID Order Release
     */
    public static final String EVENT_ID_ORDER_RELEASE = "5";

    // =================================================
    // Report Constant Value
    // =================================================
    /**
     * Work Order Status : Print
     */
    public static final String WRK_ORD_STS_PRINT = "99";

    /**
     * Report Name
     */
    public static final String REPORT_NAME = "WO Download(PDF) : ";

    /**
     * Report Abend Exception
     */
    public static final String REPORT_EXCEPTION = "get report bytes failure";

}
