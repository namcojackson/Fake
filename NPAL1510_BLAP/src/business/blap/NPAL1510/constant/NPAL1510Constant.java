/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.blap.NPAL1510.constant;

/**
 *<pre>
 * Business ID : NPAL1510 PO Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   CUSA            K.Ogino         Create          N/A
 * 2017/02/09   CITS            M.Naito         Update          QC#12673
 * 2017/12/11   CITS            S.Katsuma       Update          SOL#060(QC#14858)
 * 2018/01/31   CITS            K.Mishiro       Update          QC#22521
 * 2018/02/27   CITS            T.Gotoda        Update          QC#21944
 * 2018/02/27   CITS            T.Gotoda        Update          QC#22521-2
 * 2022/05/19   Hitachi         A.Kohinata      Update          QC#57934
 * 2023/02/09   Hitachi         TZ.Win          Update          QC#60966
 *</pre>
 */
public class NPAL1510Constant {

    /** max download count */
    public static final int MAX_DOWNLOAD_CNT = 65000;

    /** fetch size */
    public static final int FETCH_SIZE = 3000;

    /**  */
    public static final String CSV_FILE_NAME = "NPAL1510_POSearch";

    /** */
    public static final String EXTN_CSV = ".csv";

    /** */
    public static final String SEARCH_HEDER_DTL_NUM = "001";

    /** */
    public static final String BUSINESS_APPL_ID = "NPAL1510";

    /**
     * 
     */
    public static final String BUSINESS_SCREEN_ID = "NPAL1510Scrn00";

    /**
     * 
     */
    public static final String NPAL1510_INIT = "NPAL1510_INIT";

    /**
     * 
     */
    public static final String NPAL1510_CMN_RESET = "NPAL1510Scrn00_CMN_Reset";

    /**
     * 
     */
    public static final String NPAL1510_SEARCH = "NPAL1510Scrn00_Search";

    /**
     * 
     */
    public static final String NPAL1510_SAVE_SEARCH_OPTION = "NPAL1510Scrn00_SaveSearchOption";

    /**
     * 
     */
    public static final String NPAL1510_DELETE_SEARCH_OPTION = "NPAL1510Scrn00_DeleteSearchOption";

    /**
     * 
     */
    public static final String NPAL1510_CMN_COL_CLEAR = "NPAL1510Scrn00_CMN_ColClear";

    /**
     * 
     */
    public static final String NPAL1510_CMN_COL_SAVE = "NPAL1510Scrn00_CMN_ColSave";

    /**
     * 
     */
    public static final String NPAL1510_ON_CHANGE_SEARCH_OPTION = "NPAL1510Scrn00_OnChange_SearchOption";

    /**
     * 
     */
    public static final String NPAL1510_NEXT = "NPAL1510Scrn00_PageNext";

    /**
     * 
     */
    public static final String NPAL1510_PREV = "NPAL1510Scrn00_PagePrev";

    /**
     * 
     */
    public static final String NPAL1510_CMN_DOWNLOAD = "NPAL1510Scrn00_CMN_Download";

    /**
     * 
     */
    public static final String NPAM0089E = "NPAM0089E";

    /**
     * 
     */
    public static final String NPAM0001W = "NPAM0001W";

    /**
     * 
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * 
     */
    public static final String NMAM0182E = "NMAM0182E";

    /**
     * 
     */
    public static final String NMAM0014E = "NMAM0014E";

    /**
     * 
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * 
     */
    public static final String NONE = "";

    // START 2018/01/31 K.Mishiro [QC#22521),DEL]
    /**
     * 
     */
//    public static final String CSV_HDR_1 = "PO#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_2 = "PO type";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_3 = "Header Status";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_4 = "Approval Status";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_5 = "Line#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_6 = "Line Type";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_7 = "Item#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_8 = "Sub Item#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_9 = "Supplier Item#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_10 = "Item Description";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_11 = "Line Price";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_12 = "Ordered Qty";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_13 = "Date Needed";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_14 = "Ship To Customer";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_15 = "Ship To Customer Name";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_16 = "Dest WH";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_17 = "Dest SWH";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_18 = "Match Opt";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_19 = "Line Status";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_20 = "Ext. Total";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_21 = "Received Qty";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_22 = "Invoiced Qty";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_23 = "Cancelled Qty";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_24 = "Req Ship Metchod";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_25 = "Req Doc#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_26 = "Req Doc Line#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_27 = "Src Ord Doc#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_28 = "Src Ord Doc Line#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_29 = "Original PO#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_30 = "Original PO Line#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_31 = "ACK#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_32 = "Date Trans.";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_33 = "ACK Cd";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_34 = "ACK Description";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_35 = "ACK Date";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_36 = "ACK Qty";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_37 = "FOB";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_38 = "ETD";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_39 = "ETA";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_40 = "Shipped by";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_41 = "Tracking#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_42 = "Ship#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_43 = "Ship Line#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_44 = "Ship From WH";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_45 = "Ship To";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_46 = "Sold To";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_47 = "Unit Price";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_48 = "COMP Price";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_49 = "Vnd CPO#";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_50 = "CUSA Hist";
//
//    /**
//     * 
//     */
//    public static final String CSV_HDR_51 = "Vnd PO#";
    // END 2018/01/31 K.Mishiro [QC#22521),DEL]

    // START 2018/01/31 K.Mishiro [QC#22521),ADD]
    /** */
    public static final String CSV_HDR_1 = "PO#";
    /** */
    public static final String CSV_HDR_2 = "PO type";
    /** */
    public static final String CSV_HDR_3 = "Header Status";
    /** */
    public static final String CSV_HDR_4 = "Approval Status";
    /** */
    public static final String CSV_HDR_5 = "Supplier Code";
    /** */
    public static final String CSV_HDR_6 = "Supplier Name";
    /** */
    public static final String CSV_HDR_7 = "Supplier Site Code";
    /** */
    public static final String CSV_HDR_8 = "Supplier Site Name";
    /** */
    public static final String CSV_HDR_9 = "Line#";
    /** */
    public static final String CSV_HDR_10 = "Line Type";
    /** */
    public static final String CSV_HDR_11 = "Item#";
    /** */
    public static final String CSV_HDR_12 = "Sub Item#";
    /** */
    public static final String CSV_HDR_13 = "Supplier Item#";
    /** */
    public static final String CSV_HDR_14 = "Item Description";
    /** */
    public static final String CSV_HDR_15 = "MT";
    /** */
    public static final String CSV_HDR_16 = "PC";
    /** */
    public static final String CSV_HDR_17 = "Line Price";
    /** */
    public static final String CSV_HDR_18 = "Ordered Qty";
    /** */
    public static final String CSV_HDR_19 = "Date Created";
    /** */
    public static final String CSV_HDR_20 = "Ship To Customer";
    /** */
    public static final String CSV_HDR_21 = "Ship To Customer Name";
    /** */
    public static final String CSV_HDR_22 = "Dest WH";
    /** */
    public static final String CSV_HDR_23 = "Dest SWH";
    /** */
    public static final String CSV_HDR_24 = "Match Opt";
    /** */
    public static final String CSV_HDR_25 = "Line Status";
    /** */
    public static final String CSV_HDR_26 = "Ext. Total";
    /** */
    public static final String CSV_HDR_27 = "Received Qty";
    /** */
    public static final String CSV_HDR_28 = "Invoiced Qty";
    /** */
    public static final String CSV_HDR_29 = "Cancelled Qty";
    /** */
    public static final String CSV_HDR_30 = "Req Ship Metchod";
    /** */
    public static final String CSV_HDR_31 = "Req Doc#";
    /** */
    public static final String CSV_HDR_32 = "Req Doc Line#";
    /** */
    public static final String CSV_HDR_33 = "Src Ord Doc#";
    /** */
    public static final String CSV_HDR_34 = "Src Ord Doc Line#";
    /** */
    public static final String CSV_HDR_35 = "Original PO#";
    /** */
    public static final String CSV_HDR_36 = "Original PO Line#";
    /** */
    public static final String CSV_HDR_37 = "ACK#";
    /** */
    public static final String CSV_HDR_38 = "Date Trans.";
    /** */
    public static final String CSV_HDR_39 = "ACK Cd";
    /** */
    public static final String CSV_HDR_40 = "ACK Description";
    /** */
    public static final String CSV_HDR_41 = "ACK Date";
    /** */
    public static final String CSV_HDR_42 = "ACK Qty";
    /** */
    public static final String CSV_HDR_43 = "FOB";
    /** */
    public static final String CSV_HDR_44 = "ETD";
    /** */
    public static final String CSV_HDR_45 = "ETA";
    /** */
    public static final String CSV_HDR_46 = "Shipped by";
    /** */
    public static final String CSV_HDR_47 = "Tracking#";
    /** */
    public static final String CSV_HDR_48 = "Ship#";
    /** */
    public static final String CSV_HDR_49 = "Ship Line#";
    /** */
    public static final String CSV_HDR_50 = "Ship From WH";
    /** */
    public static final String CSV_HDR_51 = "Ship To";
    /** */
    public static final String CSV_HDR_52 = "Sold To";
    /** */
    public static final String CSV_HDR_53 = "Unit Price";
    /** */
    public static final String CSV_HDR_54 = "COMP Price";
    /** */
    public static final String CSV_HDR_55 = "Vnd CPO#";
    /** */
    public static final String CSV_HDR_56 = "CUSA Hist";
    /** */
    public static final String CSV_HDR_57 = "Vnd PO#";
    /** */
    public static final String CSV_HDR_58 = "Vnd Line Detail Status";
    // END 2018/01/31 K.Mishiro [QC#22521),ADD]
    // START 2023/02/09 TZ.Win [QC#60966, ADD]
    /** */
    public static final String CSV_HDR_59 = "Vendor Ship Date";
    // END 2023/02/09 TZ.Win [QC#60966, ADD]
    // add start 2022/05/19 QC#57934
    // START 2023/02/09 TZ.Win [QC#60966, MOD]
    /** */
    public static final String CSV_HDR_60 = "Received WO Qty";
    /** */
    public static final String CSV_HDR_61 = "Invoiced WO Qty";
    // END 2023/02/09 TZ.Win [QC#60966, MOD]
    // add end 2022/05/19 QC#57934

    /** */
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** */
    public static final String BIND_STRCH_OPT_USR_ID = "srchOptUsrId";

    /** */
    public static final String BIND_BUSINESS_APPL_ID = "businessApplId";

    /** */
    public static final String BIND_MSG = "msg";

    /** */
    public static final String BIND_SEARCH_COND_ONE = "searchCond1";

    /** */
    public static final String BIND_SEARCH_COND_TWO = "searchCond2";

    /** */
    public static final String BIND_ROWNUM = "rowNum";

    /** */
    public static final String BIND_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /** */
    public static final String RS_SRCH_OPT_PK = "SRCH_OPT_PK";

    /** */
    public static final String RS_SRCH_OPT_NM = "SRCH_OPT_NM";

    /** */
    public static final String RS_PO_ORD_SRC_CD = "PO_ORD_SRC_CD";

    /** */
    public static final String RS_PO_ORD_SRC_DESC_TXT = "PO_ORD_SRC_DESC_TXT";

    /** */
    public static final String RS_DS_PO_TP_CD = "DS_PO_TP_CD";

    /** */
    public static final String RS_DS_PO_TP_DESC_TXT = "DS_PO_TP_DESC_TXT";

    /** */
    public static final String RS_PO_APVL_STS_CD = "PO_APVL_STS_CD";

    /** */
    public static final String RS_PO_APVL_STS_DESC_TXT = "PO_APVL_STS_DESC_TXT";

    /** */
    public static final String RS_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** */
    public static final String RS_SHPG_SVC_LVL_DESC_TXT = "SHPG_SVC_LVL_DESC_TXT";

    /** */
    public static final String RS_VND_PO_ACK_STS_CD = "VND_PO_ACK_STS_CD";

    /** */
    public static final String RS_VND_PO_ACK_STS_DESC_TXT = "VND_PO_ACK_STS_DESC_TXT";

    /** */
    public static final String RS_PO_ORD_NUM = "PO_ORD_NUM";

    /** */
    public static final String RS_PO_HDR_STS_DESC_TXT = "PO_HDR_STS_DESC_TXT";

    /** */
    public static final String RS_SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /** */
    public static final String RS_DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /** */
    public static final String RS_PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** */
    public static final String RS_TRX_REF_NUM = "TRX_REF_NUM";

    /** */
    public static final String RS_ORIG_PO_ORD_NUM = "ORIG_PO_ORD_NUM";

    /** */
    public static final String RS_PO_SEND_TS_A0 = "PO_SEND_TS_A0";

    /** */
    public static final String RS_PO_DISP_LINE_NUM = "PO_DISP_LINE_NUM";

    /** */
    public static final String RS_PO_LINE_TP_DESC_TXT = "PO_LINE_TP_DESC_TXT";

    /** */
    public static final String RS_SET_PO_ORD_DTL_LINE_NUM = "SET_PO_ORD_DTL_LINE_NUM";

    /** */
    public static final String RS_MDSE_CD_A0 = "MDSE_CD_A0";

    /** */
    public static final String RS_MDSE_CD_A1 = "MDSE_CD_A1";

    /** */
    public static final String RS_XX_MDSE_CD_A1 = "XX_MDSE_CD_A1";

    /** */
    public static final String RS_XX_MDSE_CD_A0 = "XX_MDSE_CD_A0";

    /** */
    public static final String RS_ASL_MDSE_CD = "ASL_MDSE_CD";

    /** */
    public static final String RS_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** */
    public static final String RS_ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";

    /** */
    public static final String RS_PO_DISP_QTY = "PO_DISP_QTY";

    /** */
    public static final String RS_PO_SUBMT_DT = "PO_SUBMT_DT";

    /** */
    public static final String RS_RQST_RTL_SWH_CD = "RQST_RTL_SWH_CD";

    /** */
    public static final String RS_PO_MATCH_TP_DESC_TXT = "PO_MATCH_TP_DESC_TXT";

    /** */
    public static final String RS_PO_LINE_STS_DESC_TXT = "PO_LINE_STS_DESC_TXT";

    /** */
    public static final String RS_ENT_PO_DTL_DEAL_NET_AMT = "ENT_PO_DTL_DEAL_NET_AMT";

    /** */
    public static final String RS_PO_MDSE_CMPSN_TP_CD = "PO_MDSE_CMPSN_TP_CD";

    /** */
    public static final String RS_RCV_PO_QTY = "RCV_PO_QTY";

    /** */
    public static final String RS_PO_INV_QTY = "PO_INV_QTY";

    /** */
    public static final String RS_PO_CANC_QTY = "PO_CANC_QTY";

    /** */
    public static final String RS_PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** */
    public static final String RS_TRX_REF_LINE_NUM = "TRX_REF_LINE_NUM";

    /** */
    public static final String RS_ORIG_DISP_PO_DTL_LINE_NUM = "ORIG_DISP_PO_DTL_LINE_NUM";

    /** */
    public static final String RS_PO_ACK_NUM = "PO_ACK_NUM";

    /** */
    public static final String RS_PO_SEND_DT_A1 = "PO_SEND_DT_A1";

    /** */
    public static final String RS_ORD_LAST_UPD_TS = "ORD_LAST_UPD_TS";

    /** */
    public static final String RS_ORD_QTY = "ORD_QTY";

    /** */
    public static final String RS_XX_TOT_AMT = "XX_TOT_AMT";

    /** */
    public static final String RS_ETD_DT = "ETD_DT";

    /** */
    public static final String RS_ETA_DT = "ETA_DT";

    /** */
    public static final String RS_VND_OTBD_CARR_NM = "VND_OTBD_CARR_NM";

    /** */
    public static final String RS_PRO_NUM = "PRO_NUM";

    /** */
    public static final String RS_VND_SO_NUM = "VND_SO_NUM";

    /** */
    public static final String RS_VND_SO_SLP_NUM = "VND_SO_SLP_NUM";

    /** */
    public static final String RS_VND_INVTY_LOC_CD = "VND_INVTY_LOC_CD";

    /** */
    public static final String RS_VND_SHIP_TO_CUST_LOC_NM = "VND_SHIP_TO_CUST_LOC_NM";

    /** */
    public static final String RS_VND_SELL_TO_CUST_LOC_NM = "VND_SELL_TO_CUST_LOC_NM";

    /** */
    public static final String RS_THIS_MTH_FOB_COST_AMT = "THIS_MTH_FOB_COST_AMT";

    /** */
    public static final String RS_VND_CHILD_BOM_PRC_AMT = "VND_CHILD_BOM_PRC_AMT";

    /** */
    public static final String RS_VND_CPO_ORD_NUM = "VND_CPO_ORD_NUM";

    /** */
    public static final String RS_VND_ISS_PO_ORD_NUM = "VND_ISS_PO_ORD_NUM";

    /** */
    public static final String RS_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** */
    public static final String RS_VND_PO_ACK_LINE_STS_TXT = "VND_PO_ACK_LINE_STS_TXT";

    /**
     * Replace Character at CARR_URL.CARR_TRK_URL.
     */
    public static final String REPLACE_CHAR = "\\$\\$";

    // START 2017/12/08 S.Katsuma [SOL#060(QC#14858),ADD]
    /** */
    public static final String BIND_PO_LINE_TP_CD_EXPENSE = "poLineTpCdExpense";

    /** */
    public static final String BIND_PO_LINE_TP_CD_EXPENSE_W_RECEIPT = "poLineTpCdExpenseWReceipt";

    /** */
    public static final String BIND_MDSE_ITEM_TP_CD_TEXT_ITEM = "mdseItemTpCdTextItem";
    // END 2017/12/08 S.Katsuma [SOL#060(QC#14858),ADD]

    // START 2018/01/31 K.Mishiro [QC#22521),ADD]
    /** */
    public static final String RS_COA_MDSE_TP_CD = "COA_MDSE_TP_CD";

    /** */
    public static final String RS_COA_PROD_CD = "COA_PROD_CD";

    /** */
    public static final String RS_PRNT_VND_CD = "PRNT_VND_CD";

    /** */
    public static final String RS_PRNT_VND_NM = "PRNT_VND_NM";

    /** */
    public static final String RS_VND_CD = "VND_CD";

    /** */
    public static final String RS_VND_NM = "VND_NM";

    // END 2018/01/31 K.Mishiro [QC#22521),ADD]
    
    // START 2023/02/09 TZ.Win [QC#60966, ADD]
    /** */
    public static final String RS_RQST_SHIP_DT = "RQST_SHIP_DT";
    // END 2023/02/09 TZ.Win [QC#60966, ADD]

    // add start 2022/05/19 QC#57934
    /** */
    public static final String RS_RCV_WO_QTY = "RCV_WO_QTY";

    /** */
    public static final String RS_INV_WO_QTY = "INV_WO_QTY";

    /** DR_CR_TP_CREDIT */
    public static final String DR_CR_TP_CREDIT = "C";

    /** DR_CR_TP_DEBIT */
    public static final String DR_CR_TP_DEBIT = "D";

    /** VAR_CHAR_CONST: NFBL1130_COA_ACCT_CD */
    public static final String VAR_CHAR_NFBL1130_COA_ACCT_CD = "NFBL1130_COA_ACCT_CD";

    /** Default COA_ACCT_CD value */
    public static final String DEFAULT_COA_ACCT_CD = "21431001";

    /** String: COMMA "," */
    public static final String COMMA = ",";
    // add end 2022/05/19 QC#57934
}
