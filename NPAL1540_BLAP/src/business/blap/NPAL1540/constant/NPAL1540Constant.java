/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1540.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID : NPAL1540 Manual ASN Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/19/2016   CITS         Makoto Okigami     Create          N/A
 * 03/19/2018   CITS         K.Ogino            Update          QC#24905
 * 09/13/2018   CITS         K.Ogino            Update          QC#26964/QC#26965(TST Impreso / Dietzgen PO EDI)
 *</pre>
 */
public class NPAL1540Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1540";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1540Scrn00";

    // =================================================
    // Window Item Name
    // =================================================
    /**
     * XX_CHK_BOX_A1
     */
    public static final String XX_CHK_BOX_A1 = "xxChkBox_A1";

    /**
     * ASN_SO_SLP_NUM_S1
     */
    public static final String ASN_SO_SLP_NUM_S1 = "asnSoSlpNum_S1";

    /**
     * PO_ORD_DTL_LINE_NUM_A1
     */
    public static final String PO_ORD_DTL_LINE_NUM_A1 = "poOrdDtlLineNum_A1";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NPAL1540_INIT = "NPAL1540_INIT";

    /**
     * Event Name : CMN_Submit
     */
    public static final String EVENT_NM_NPAL1540_CMN_SUBMIT = "NPAL1540Scrn00_CMN_Submit";

    /**
     * Event Name : CMN_Clear
     */
    public static final String EVENT_NM_NPAL1540_CMN_CLEAR = "NPAL1540Scrn00_CMN_Clear";

    /**
     * Event Name : Search
     */
    public static final String EVENT_NM_NPAL1540_SEARCH = "NPAL1540Scrn00_Search";

    /**
     * Event Name : ApplyPO
     */
    public static final String EVENT_NM_NPAL1540_APPLY_PO = "NPAL1540Scrn00_ApplyPO";

    /**
     * Event Name : AddAllLine
     */
    public static final String EVENT_NM_NPAL1540_ADD_ALL_LINE = "NPAL1540Scrn00_AddAllLine";

    /**
     * Event Name : AddLine
     */
    public static final String EVENT_NM_NPAL1540_ADD_LINE = "NPAL1540Scrn00_AddLine";

    /**
     * Event Name : OpenWin_SerEntry
     */
    public static final String EVENT_NM_NPAL1540_OPEN_WIN_SER_ENTRY = "NPAL1540Scrn00_OpenWin_SerEntry";

    /**
     * Event Name : Return NLBL3000
     */
    public static final String EVENT_NM_NPAL1540_NLBL3000 = "NPAL1540_NLBL3000";

    /**
     * Event Name : Delete Row
     */
    public static final String EVENT_NM_NPAL1540_DELETE_ROW = "NPAL1540Scrn00_DeleteRow";

    /**
     * Event Name : PageNext
     */
    public static final String EVENT_NM_NPAL1540_PAGE_NEXT = "NPAL1540Scrn00_PageNext";

    /**
     * Event Name : PagePrev
     */
    public static final String EVENT_NM_NPAL1540_PAGE_PREV = "NPAL1540Scrn00_PagePrev";

    /**
     * Event Name : CMN_ColClear
     */
    public static final String EVENT_NM_NPAL1540_CMN_COL_CLEAR = "NPAL1540Scrn00_CMN_ColClear";

    /**
     * Event Name : CMN_ColSave
     */
    public static final String EVENT_NM_NPAL1540_CMN_COL_SAVE = "NPAL1540Scrn00_CMN_ColSave";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name: Sales Date
     */
    public static final String DB_PARAM_SALES_DATE = "slsDt";

    /**
     * DB Param Name: ITRL_INTFC_ID
     */
    public static final String DB_PARAM_ITRL_INTFC_ID = "itrlIntfcId";

    /**
     * DB Param Name: ASN_EDI_PROC_STS_CD
     */
    public static final String DB_PARAM_ASN_EDI_PROC_STS_CD = "asnEdiProcStsCd";

    /**
     * DB Param Name: PO_ORD_NUM
     */
    public static final String DB_PARAM_PO_ORD_NUM = "poOrdNum";

    /**
     * DB Param Name: PO_ORD_DTL_LINE_NUM
     */
    public static final String DB_PARAM_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /**
     * DB Param Name: VND_CD
     */
    public static final String DB_PARAM_VND_CD = "vndCd";

    /**
     * DB Param Name: CARR_CD
     */
    public static final String DB_PARAM_CARR_CD = "carrCd";

    /**
     * DB Param Name: SHPG_SVC_LCL_CD
     */
    public static final String DB_PARAM_SHPG_SVC_LCL_CD = "shpgSvcLclCd";

    /**
     * DB Param Name: ASL_MDSE_CD
     */
    public static final String DB_PARAM_ASL_MDSE_CD = "aslMdseCd";

    /**
     * DB Param Name: ASN_SO_NUM
     */
    public static final String DB_PARAM_ASN_SO_NUM = "asnSoNum";

    /**
     * DB Param Name: PO_STS_CD
     */
    public static final String DB_PARAM_PO_STS_CD = "poStsCd";

    /**
     * DB Param Name: DISP_PO_DTL_LINE_NUM
     */
    public static final String DB_PARAM_DISP_PO_DTL_LINE_NUM = "dispPoDtlLineNum";

    /**
     * DB Param Name: No Entry
     */
    public static final String DB_PARAM_NO_ENTRY = "noEntry";

    /**
     * DB Param Name: Entered
     */
    public static final String DB_PARAM_ENTERED = "entered";

    /**
     * DB Param Name:cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    /**
     * DB Param Name: Max ROWNUM
     */
    public static final String DB_PARAM_MAX_ROWNUM = "maxRownum";

    /**
     * DB Param Name: ROWNUM
     */
    public static final String DB_PARAM_ROWNUM = "rownum";

    // =================================================
    // DB Value
    // =================================================
    /**
     * DB Value: ITRL_INTFC_ID
     */
    public static final String ITRL_INTFC_ID_MANUAL = "MANUAL";

    /**
     * DB Value: EDI_SUB_NUM
     */
    public static final String EDI_SUB_NUM_001 = "001";

    // =================================================
    // DB Columns
    // =================================================
    /**
     * DB Column Name: PO_QTY
     */
    public static final String DB_COLUMN_PO_QTY = "PO_QTY";

    /**
     * DB Column Name: PO_RCV_QTY
     */
    public static final String DB_COLUMN_PO_RCV_QTY = "PO_RCV_QTY";

    /**
     * DB Column Name: IN_TRANSIT_QTY
     */
    public static final String DB_COLUMN_IN_TRANSIT_QTY = "IN_TRANSIT_QTY";

    /**
     * DB Column Name: TRD_PTNR_CARR_CD
     */
    public static final String DB_COLUMN_TRD_PTNR_CARR_CD = "TRD_PTNR_CARR_CD";

    /**
     * DB Column Name: PO_STS_CD
     */
    public static final String DB_COLUMN_PO_STS_CD = "PO_STS_CD";

    /**
     * DB Column Name: RCV_ASN_VND_CD
     */
    public static final String DB_COLUMN_RCV_ASN_VND = "RCV_ASN_VND_CD";

    /**
     * DB Column Name: VND_CD
     */
    public static final String DB_COLUMN_VND_CD = "VND_CD";

    /**
     * DB Column Name: DPLY_VND_NM
     */
    public static final String DB_COLUMN_DPLY_VND_NM = "DPLY_VND_NM";

    /**
     * DB Column Name: DISP_PO_DTL_LINE_NUM
     */
    public static final String DB_COLUMN_DISP_PO_DTL_LINE_NUM = "DISP_PO_DTL_LINE_NUM";

    /**
     * DB Column Name: PO_ORD_DTL_LINE_NUM
     */
    public static final String DB_COLUMN_PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /**
     * DB Column Name: ASL_MDSE_CD
     */
    public static final String DB_COLUMN_ASL_MDSE_CD = "ASL_MDSE_CD";

    /**
     * DB Column Name: MDSE_CD
     */
    public static final String DB_COLUMN_MDSE_CD = "MDSE_CD";

    /**
     * DB Column Name: MDSE_DESC_SHORT_TXT
     */
    public static final String DB_COLUMN_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /**
     * DB Column Name: ASN_QTY
     */
    public static final String DB_COLUMN_ASN_QTY = "ASN_QTY";

    /**
     * DB Column Name: SHPG_SVC_LVL_CD
     */
    public static final String DB_COLUMN_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /**
     * DB Column Name: DEST_RTL_WH_CD
     */
    public static final String DB_COLUMN_DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /**
     * DB Column Name: RTL_WH_NM
     */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";

    /**
     * DB Column Name: DEST_RTL_SWH_CD
     */
    public static final String DB_COLUMN_DEST_RTL_SWH_CD = "DEST_RTL_SWH_CD";

    /**
     * DB Column Name: INVTY_LOC_CD
     */
    public static final String DB_COLUMN_INVTY_LOC_CD = "INVTY_LOC_CD";

    /**
     * DB Column Name: AFT_DECL_PNT_DIGIT_NUM
     */
    public static final String DB_COLUMN_AFT_DECL_PNT_DIGIT_NUM = "AFT_DECL_PNT_DIGIT_NUM";

    /**
     * DB Column Name: SHIP_TO_CUST_CD
     */
    public static final String DB_COLUMN_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /**
     * DB Column Name: RCV_SER_TAKE_FLG
     */
    public static final String DB_COLUMN_RCV_SER_TAKE_FLG = "RCV_SER_TAKE_FLG";

    // =================================================
    // API Param
    // =================================================
    /**
     * API Param: Timestamp Format
     */
    public static final String API_PARAM_TIMESTAMP_FORMAT_YMD_HMS = "yyyyMMddHHmmssSSS";

    /**
     * API Param: Timestamp Format
     */
    public static final String API_PARAM_TIMESTAMP_FORMAT_HMS = "HHmmss";

    // =================================================
    // Popup Param
    // =================================================
    /**
     * Poupu Param: NLBL3000
     */
    public static final String SERIAL_POPUP_PARAM_SUFFIX = "L1";

    /**
     * Poupu Param: NLBL3000
     */
    public static final String SERIAL_POPUP_PARAM_INBOUND = "1";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : Supplier Item Code
     */
    public static final String DISPLAY_NM_ASN_MDSE_CD = "Supplier Item Code";

    /**
     * Display Name : Ship Qty
     */
    public static final String DISPLAY_NM_ASN_QTY = "Ship Qty";

    // =================================================
    // Display Value
    // =================================================
    /**
     * Display Value : Serial Input
     */
    public static final String DISPLAY_VAL_SER_INPUT_NO_ENTRY = "No Entry";

    /**
     * Display Value : Serial Input
     */
    public static final String DISPLAY_VAL_SER_INPUT_ENTERED = "Entered";

    // =================================================
    // After Decimal Point Digit Number
    // =================================================
    /**
     * After Decimal Point Digit Number(Default)
     */
    public static final BigDecimal AFT_DECL_PNT_DIGIT_NUM_DEF = BigDecimal.valueOf(2);

    // =================================================
    // Message Code
    // =================================================
    /**
     * NZZM0000E: No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * NZZM0011E: Please check at least 1 checkbox.
     */
    public static final String NZZM0011E = "NZZM0011E";

    /**
     * ZZM8100I: Process ended normally.
     */
    public static final String ZZM8100I = "ZZM8100I";

    /**
     * ZZM9000E: [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * NPAM1551W: Search results exceeded [@]. Only showing first @ records.
     */
    public static final String NPAM1551W = "NPAM1551W";

    /**
     * NPAM0077E: You can add Details up to [@].
     */
    public static final String NPAM0077E = "NPAM0077E";

    /**
     * NPAM1556E: Entered Carrier cannot set with specified supplier.
     */
    public static final String NPAM1556E = "NPAM1556E";

    /**
     * NPAM1557E: It failed to register [@].
     */
    public static final String NPAM1557E = "NPAM1557E";

    /**
     * NPZM0268E: Supplier Item Number cannot be obtained from ASL Table.
     */
    public static final String NPZM0268E = "NPZM0268E";

    /**
     * NPAM1558E: Total Weight is smaller than summary of detail's weight.
     */
    public static final String NPAM1558E = "NPAM1558E";

    /**
     * NPAM1559E: Entered ASN SO# is dupulicated. Please input different number.
     */
    public static final String NPAM1559E = "NPAM1559E";

    /**
     * NPAM1560E: Entered specified PO# is not an open order. Please check the PO status.
     */
    public static final String NPAM1560E = "NPAM1560E";

    /**
     * NPAM1561E: Supplier of the specified PO# is not an Receiving ASN Supplier.
     */
    public static final String NPAM1561E = "NPAM1561E";

    /**
     * NPAM1562E: Entered Serial Quantity exceeds Ship Quantity.
     */
    public static final String NPAM1562E = "NPAM1562E";

    /**
     * NPAM1567E: Cannot find validated PO Line of entered Line#.
     */
    public static final String NPAM1567E = "NPAM1567E";

    /**
     * NPAM1568E: Entered date is later (future date) than today.
     */
    public static final String NPAM1568E = "NPAM1568E";

    /**
     * NPAM1569E: PDD Date is earlier than Ship Date.
     */
    public static final String NPAM1569E = "NPAM1569E";

    /**
     * NPAM1572E: "Ship Qty+Intransit Qty+PO Rcv Qty" exceeded "PO Qty".
     */
    public static final String NPAM1572E = "NPAM1572E";

    /**
     * Add QC#24905. NPAM1618E:The value for [@] must be bigger than [@].
     */
    public static final String NPAM1618E = "NPAM1618E";

    // QC#26964/QC#26965
    /**
     * Entered specified SO# is not an open order. Please check the SO status
     */
    public static final String NPAM1629E = "NPAM1629E";

    /**
     * Supplier of the specified SO# is not an Receiving ASN Supplier.
     */
    public static final String NPAM1630E = "NPAM1630E";

    /**
     * If Combine SO Line is included, Required field(s) - BOL# or Carrier + PRO#.
     */
    public static final String NPAM1631E = "NPAM1631E";

    /**
     * "Ship Qty+Intransit Qty" exceeded "Request Qty".
     */
    public static final String NPAM1632E = "NPAM1632E";

}
