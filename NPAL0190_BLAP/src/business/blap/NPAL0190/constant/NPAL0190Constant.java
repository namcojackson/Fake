/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL0190.constant;

/**
 * <pre>
 * PO History Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/29/2012   SRA             N.Otsuji        Create          N/A
 * 03/14/2013   Hitachi         T.Arakawa       Create          K25(QC822)
 * 01/25/2016   CITS            K.Ogino         Update          CSA
 * 03/08/2016   CITS            K.Ogino         Update          QC#4976
 * 12/11/2017   CITS            S.Katsuma       Update          SOL#060(QC#14858)
 *</pre>
 */
public class NPAL0190Constant {

    /**
     * Search Max Rows
     */
    public static final int MAX_ROWS = 2000;

    /**
     * Error Message ZZZM9005W
     */
    public static final String ZZZM9005W = "ZZZM9005W";

    /**
     * Error Message NZZM0001W
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * SQL ID
     */
    public static final String SQL_GET_BIZ_PROC_LOG = "getBizProcLog";

    /**
     * NPAL0190_INIT
     */
    public static final String APL_ID_INIT = "NPAL0190_INIT";

    /**
     * NPAL0190Scrn00_PageNext
     */
    public static final String APL_ID_PAGE_NEXT = "NPAL0190Scrn00_PageNext";

    /**
     * NPAL0190Scrn00_PagePrev
     */
    public static final String APL_ID_PAGE_PREV = "NPAL0190Scrn00_PagePrev";

    /**
     * NPAL0190Scrn00_TBLColumnSort
     */
    public static final String APL_ID_TBL_COLUMN_SORT = "NPAL0190Scrn00_TBLColumnSort";

    /**
     * 
     */
    public static final String CMN_COL_CLEAR = "NPAL0190Scrn00_CMN_ColClear";

    /**
     * 
     */
    public static final String NMN_COL_SAVE = "NPAL0190Scrn00_CMN_ColSave";

    /** */
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** */
    public static final String BIND_PO_ORD_NUM = "poOrdNum";

    /** */
    public static final String BIND_PO_DISP_LINE_NUM = "poDispLineNum";

    /** */
    public static final String BIND_ROW_NUM = "rowNum";

    /** */
    public static final String RS_EVENT_ID = "EVENT_ID";

    /** */
    public static final String RS_PO_BIZ_PROC_LOG_UPD_TS = "PO_BIZ_PROC_LOG_UPD_TS";

    /** */
    public static final String RS_PO_SUBMT_PSN_CD = "PO_SUBMT_PSN_CD";

    /** */
    public static final String RS_PO_HDR_STS_DESC_TXT = "PO_HDR_STS_DESC_TXT";

    /** */
    public static final String RS_PO_APVL_STS_DESC_TXT = "PO_APVL_STS_DESC_TXT";

    /** */
    public static final String RS_DISP_PO_DTL_LINE_NUM = "DISP_PO_DTL_LINE_NUM";

    /** */
    public static final String RS_PO_LINE_STS_DESC_TXT = "PO_LINE_STS_DESC_TXT";

    /** */
    public static final String RS_MDSE_CD = "MDSE_CD";

    /** */
    public static final String RS_ASL_MDSE_CD = "ASL_MDSE_CD";

    /** */
    public static final String RS_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** */
    public static final String RS_ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";

    /** */
    public static final String RS_PO_DISP_QTY = "PO_DISP_QTY";

    /** */
    public static final String RS_PKG_UOM_CLS_DESC_TXT = "PKG_UOM_CLS_DESC_TXT";

    /** */
    public static final String RS_ENT_PO_DTL_DEAL_NET_AMT = "ENT_PO_DTL_DEAL_NET_AMT";

    /** */
    public static final String RS_RQST_RCV_DT = "RQST_RCV_DT";

    /** */
    public static final String RS_SRC_RTL_WH_CD = "SRC_RTL_WH_CD";

    /** */
    public static final String RS_SRC_RTL_SWH_CD = "SRC_RTL_SWH_CD";

    /** */
    public static final String RS_DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /** */
    public static final String RS_DEST_RTL_SWH_CD = "DEST_RTL_SWH_CD";

    /** */
    public static final String RS_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** */
    public static final String RS_SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /** */
    public static final String RS_PO_MATCH_TP_DESC_TXT = "PO_MATCH_TP_DESC_TXT";

    /** */
    public static final String RS_PO_RCV_QTY = "PO_RCV_QTY";

    /** */
    public static final String RS_PO_CANC_QTY = "PO_CANC_QTY";

    /** */
    public static final String RS_PO_INV_QTY = "PO_INV_QTY";

    /** */
    public static final String RS_FROM_STK_STS_DESC_TXT = "FROM_STK_STS_DESC_TXT";

    /** */
    public static final String RS_TO_STK_STS_DESC_TXT = "TO_STK_STS_DESC_TXT";

    /** */
    public static final String RS_SER_NUM_LIST_TXT = "SER_NUM_LIST_TXT";

    /** */
    public static final String RS_CHRG_COA_XXXX_CD = "CHRG_COA_XXXX_CD";

    /** */
    public static final String RS_ACRL_COA_XXXX_CD = "ACRL_COA_XXXX_CD";

    /** */
    public static final String RS_VAR_COA_XXXX_CD = "VAR_COA_XXXX_CD";

    /** */
    public static final String RS_PO_ORD_DTL_CMNT_TXT = "PO_ORD_DTL_CMNT_TXT";

    // START 2017/12/08 S.Katsuma [SOL#060(QC#14858),ADD]
    /** */
    public static final String BIND_PO_LINE_TP_CD_EXPENSE = "poLineTpCdExpense";

    /** */
    public static final String BIND_PO_LINE_TP_CD_EXPENSE_W_RECEIPT = "poLineTpCdExpenseWReceipt";

    /** */
    public static final String BIND_MDSE_ITEM_TP_CD_TEXT_ITEM = "mdseItemTpCdTextItem";
    // END 2017/12/08 S.Katsuma [SOL#060(QC#14858),ADD]
}
