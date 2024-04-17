/**
 *  <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC101001.constant;

/** 
 *<pre>
 * NPZC1010:MRP Calculation API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS            T.Kikuhara      Create          N/A
 * 07/27/2017   CITS            S.Katsuma       Update          QC#19656
 * 08/09/2017   CITS            S.Katsuma       Update          QC#20543
 * 2017/10/24   CITS            K.Ogino         Update          QC#21987
 * 2017/11/06   CITS            T.Tokutomi      Update          QC#18401-Sol#014
 * 2017/12/27   CITS            T.Tokutomi      Update          QC#18401-Sol#014
 * 2018/01/17   CITS            T.Tokutomi      Update          QC#20832
 * 2018/03/07   CITS            T.Tokutomi      Update          QC#24580
 * 2018/06/21   CITS            T.Tokutomi      Update          QC#26532
 * 2018/06/21   CITS            T.Tokutomi      Update          QC#26554
 * 07/09/2018   CITS            Y.Iwasaki       Update          QC#27013
 * 08/02/2018   CITS            T.Tokutomi      Update          QC#27019
 * 08/02/2018   CITS            T.Tokutomi      Update          QC#27020
 * 10/25/2018   CITS            T.Tokutomi      Update          QC#28729
 * 02/18/2019   CITS            K.Ogino         Update          QC#30326
 * 02/19/2019   CITS            T.Tokutomi      Update          QC#30334
 * 04/17/2023   CSA             G.Quan          Insert          QC#61206
 *</pre>
 */
public class NPZC101001Constant {

    // MODE_CD
    /** culc Supersede contain. */
    public static final String MODE_ZERO = "0";
    /** culc Supersede Not contain. */
    public static final String MODE_ONE = "1";
    
    // QC#26532
    /** MODE IND : Gross */
    public static final String MODE_IND_GROSS = "0";
    /** MODE IND : Detail */
    public static final String MODE_IND_DETAIL = "1";
    /** MODE IND : On hand detail */
    public static final String MODE_IND_ON_HAND_DTL = "2";

    // FIX VALUE
    /** . */
    public static final String MIN_YMD = "10000101";
    /** . */
    public static final String MAX_YMD = "99991231";

    // ERROR MSG ID
    /** . */
    public static final String NPZM0001E = "NPZM0001E";
    /** . */
    public static final String NPZM0020E = "NPZM0020E";
    /** . */
    public static final String NPZM0053E = "NPZM0053E";
    /** . */
    public static final String NPZM0054E = "NPZM0054E";
    /** . */
    public static final String NPZM0055E = "NPZM0055E";
    /** . */
    public static final String NPZM0133E = "NPZM0133E";
    /** . */
    public static final String NPZC1010 = "NPZC1010";
    /** . */
    public static final String NPZM0093E = "NPZM0093E";
    /** . */
    public static final String NPZM0180E = "NPZM0180E";
    /** . */
    public static final String NPAM1310E = "NPAM1310E";
    /** . */
    public static final String NPZM0214E = "NPZM0214E";
    /**
     * QC#20832
     * Too many search results.  Please narrow your search criteria and retry.
     */
    public static final String NPAM0001W = "NPAM0001W";
    // VARCHAR CONST NAME
    /** . */
    public static final String NPZC1010_INVTY_IN = "NPZC1010_INVTY_IN";
    /** . */
    public static final String NPZC1010_INVTY_OUT = "NPZC1010_INVTY_OUT";
    /** . */
    public static final String NPZC1010_INVTY_STK = "NPZC1010_INVTY_STK";
    /** . */
    public static final String NPZC1010_ORDER_PO = "NPZC1010_ORDER_PO";
    /** . */
    public static final String NPZC1010_ORDER_POREQ = "NPZC1010_ORDER_POREQ";
    /** . */
    public static final String NPZC1010_ORDER_INVREQ = "NPZC1010_ORDER_INVREQ";
    /** . */
    public static final String NPZC1010_ORDER_WO = "NPZC1010_ORDER_WO";
    /** . */
    public static final String NPZC1010_ORDER_CPO = "NPZC1010_ORDER_CPO";
    /** . */
    public static final String NPZC1010_ORDER_TECHREQ = "NPZC1010_ORDER_TECHREQ";
    /** . */
    public static final String NPZC1010_ORDER_REMAN = "NPZC1010_ORDER_REMAN";

    // COLUMN NAME
    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** . */
    public static final String MDSE_CD = "MDSE_CD";
    /** . */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";
    /** . */
    public static final String LOC_STS_CD = "LOC_STS_CD";
    /** . */
    public static final String STK_STS_CD = "STK_STS_CD";
    /** . */
    public static final String INVTY_AVAL_QTY = "INVTY_AVAL_QTY";
    /** . */
    public static final String SCHD_QTY = "SCHD_QTY";
    /** . */
    public static final String SCHD_INBD_QTY = "SCHD_INBD_QTY";
    /** . */
    public static final String SCHD_OTBD_QTY = "SCHD_OTBD_QTY";
    /** . */
    public static final String PO_BAL_QTY = "PO_BAL_QTY";
    /** . */
    public static final String OPEN = "OPEN";
    /** . */
    public static final String APPROVED = "APPROVED";
    /** . */
    public static final String PRE_APPROVED = "PRE_APPROVED";
    /** . */
    public static final String BIZ_APP_ID = "BIZ_APP_ID";
    /** . */
    public static final String TO_STK_STS_CD = "TO_STK_STS_CD";
    /** . */
    public static final String FROM_STK_STS_CD = "FROM_STK_STS_CD";
    /** . */
    public static final String PO_STS_CD = "PO_STS_CD";
    /** . */
    public static final String FLG_ON_Y = "FLG_ON_Y";
    /** . */
    public static final String POYO_RECEIVE = "POYO_RECEIVE";
    /** . */
    public static final String FROM_DT = "FROM_DT";
    /** . */
    public static final String THRU_DT = "THRU_DT";
    /** . */
    public static final String DOMESTIC_PO_RECEIVE = "DOMESTIC_PO_RECEIVE";
    /** . */
    public static final String STOCK_IN_DC = "STOCK_IN_DC";
    /** . */
    public static final String VIS_QTY = "VIS_QTY";
    /** . */
    public static final String VIS_LOC_CD = "VIS_LOC_CD";
    /** . */
    public static final String ETA_ETD_DT = "ETA_ETD_DT";
    /** . */
    public static final String RSD_DT = "RSD_DT";
    /** . */
    public static final String ORD_DT = "ORD_DT";
    /** . */
    public static final String ORD_QTY = "ORD_QTY";
    /** . */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";
    /** . */
    public static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";
    /** . */
    public static final String DS_PO_TP_DESC_TXT = "DS_PO_TP_DESC_TXT";
    /** . */
    public static final String PRCH_REQ_REC_TP_CD = "PRCH_REQ_REC_TP_CD";
    /** . */
    public static final String PRCH_REQ_SRC_TP_CD = "PRCH_REQ_SRC_TP_CD";
    /** . */
    public static final String PRCH_REQ_STS_CD = "PRCH_REQ_STS_CD";
    /** . */
    public static final String PRCH_REQ_APVL_STS_CD = "PRCH_REQ_APVL_STS_CD";
    /** . */
    public static final String PRCH_REQ_QTY = "PRCH_REQ_QTY";
    /** . */
    public static final String PRCH_REQ_BAL_QTY = "PRCH_REQ_BAL_QTY";
    /** . */
    public static final String RQST_RCV_DT = "RQST_RCV_DT";
    /** . */
    public static final String PRCH_REQ_CRAT_DT = "PRCH_REQ_CRAT_DT";
    /** . */
    public static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";
    /** . */
    public static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";
    /** . */
    public static final String PRCH_REQ_TP_DESC_TXT = "PRCH_REQ_TP_DESC_TXT";
    /** . */
    public static final String DEST_INVTY_LOC_CD = "DEST_INVTY_LOC_CD";
    /** . */
    public static final String INBD_VIS_EVENT_CD = "INBD_VIS_EVENT_CD";
    /** . */
    public static final String INBD_VIS_DATA_TP_CD = "INBD_VIS_DATA_TP_CD";
    /** . */
    public static final String WRK_ORD_STS_CD = "WRK_ORD_STS_CD";
    /** . */
    public static final String DS_WRK_ORD_TP_CD = "DS_WRK_ORD_TP_CD";
    /** . */
    public static final String FNSH_GOODS_BAL_QTY = "FNSH_GOODS_BAL_QTY";
    /** . */
    public static final String FNSH_GOODS_MDSE_CD = "FNSH_GOODS_MDSE_CD";
    /** . */
    public static final String WRK_ORD_DT = "WRK_ORD_DT";
    /** . */
    public static final String WRK_ORD_NUM = "WRK_ORD_NUM";
    /** . */
    public static final String DS_WRK_ORD_TP_DESC_TXT = "DS_WRK_ORD_TP_DESC_TXT";
    /** . */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";
    /** . */
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";
    /** . */
    public static final String CPO_ORD_TP_CD = "CPO_ORD_TP_CD";
    /** . */
    public static final String ORD_LINE_STS_CD = "ORD_LINE_STS_CD";
    // QC#18401 Add ORD_HDR_DPLY_STS_BOOK.
    /** . */
    public static final String ORD_HDR_DPLY_STS_BOOK = "ORD_HDR_DPLY_STS_BOOK";
    /** . */
    public static final String CPO_ORD_TP_DESC_TXT = "CPO_ORD_TP_DESC_TXT";
    /** . */
    public static final String SHPG_STS_CD = "SHPG_STS_CD";
    /** . */
    public static final String TRX_HDR_NUM = "TRX_HDR_NUM";
    /** . */
    public static final String TRX_LINE_NUM = "TRX_LINE_NUM";
    /** . */
    public static final String SRC_INVTY_LOC_CD = "SRC_INVTY_LOC_CD";
    /** . */
    public static final String WH_IN_ETA_DT = "WH_IN_ETA_DT";
    /** . */
    public static final String RMNF_ORD_NUM = "RMNF_ORD_NUM";
    /** . */
    public static final String RMNF_ORD_DTL_LINE_NUM = "RMNF_ORD_DTL_LINE_NUM";
    /** . */
    public static final String PRCH_REQ_REL_STS_CD = "PRCH_REQ_REL_STS_CD";
    /** . */
    public static final String DS_SO_LINE_STS_CD = "DS_SO_LINE_STS_CD";
    /** . */
    public static final String XX_INBD_OTBD_NM = "XX_INBD_OTBD_NM";
    /** . */
    public static final String XX_ORD_TRX_TP_NM = "XX_ORD_TRX_TP_NM";
    /** . */
    public static final String RMNF_PRT_RQST_PROC_CD = "RMNF_PRT_RQST_PROC_CD";
    /** . */
    public static final String RMNF_PRT_REQ_LINE_NUM = "RMNF_PRT_REQ_LINE_NUM";
    /** . */
    public static final String RMNF_ORD_TP_DESC_TXT = "RMNF_ORD_TP_DESC_TXT";
    // START 2017/07/26 S.Katsuma [QC#19656,ADD]
    /** . */
    public static final String RMNF_TO_CMPT_MDSE_CD = "RMNF_TO_CMPT_MDSE_CD";
    /** . */
    public static final String RMNF_START_DT = "RMNF_START_DT";
    // END 2017/07/26 S.Katsuma [QC#19656,ADD]
    // START 2017/08/09 S.Katsuma [QC#20543,ADD]
    /** . */
    public static final String ORD_TAKE_MDSE_FLG = "ORD_TAKE_MDSE_FLG";
    // END 2017/08/09 S.Katsuma [QC#20543,ADD]

    // QC#20833
    /** DB_PARAM */
    public static final String PRCH_REQ_STS_CD_LIST = "prchReqStsCdList";

    /** DB_PARAM */
    public static final String BATCH_MODE = "batchMode";

    /** DB_PARAM */
    public static final String PRCH_REQ_SRC_TP_CD_LIST = "prchReqSrcTpCdList";

    // QC#21987
    /** DB_PARAM */
    public static final String PRCH_REQ_TP_CD_KT = "prchReqTpCdKt";

    // QC#18401 SOL#014 Add.
    /** . */
    public static final String RTL_WH_CD = "RTL_WH_CD";
    /** . */
    public static final String SHPG_STS_CD_VALIDATED = "VALIDATED";
    /** . */
    public static final String SHPG_STS_CD_INSHED = "INSHED";
    /** . */
    public static final String SHPG_STS_CD_SHIPPED = "SHIPPED";
    /** . */
    public static final String NOT_PROCESS = "NOT_PROCESS";
    /** . */
    public static final String INVTY_QTY = "INVTY_QTY";
    /** . */
    public static final String TECH_REQ = "TECH_REQ";
    // QC#26554 Add.
    /** . */
    public static final String DS_ORD_TP_DESC_TXT = "DS_ORD_TP_DESC_TXT";
    // QC#24580 Add
    /** . */
    public static final String RWS_STS_CANCEL = "rwsStsCd_Cancel";
    // QC#28729 Add
    /** . */
    public static final String RWS_STS_CLOSE = "rwsStsCd_Close";
    // QC#26534 Add.
    /** . */
    public static final String MDSE_ITEM_RELN_TP_CD = "MDSE_ITEM_RELN_TP_CD";

    // QC#27019 Add.
    /** . */
    public static final String STK_STS_GOOD = "STK_STS_GOOD";

    // QC#27020 Add.
    /** . */
    public static final String SCE_ORD_TP_TR = "SCE_ORD_TP_TR";

    // QC#30326 Add.
    public static final String PRCH_REQ_TP_WH_TRANSFER = "whTransfer";
    public static final String PRCH_REQ_TP_EXPENCE_ORDER = "expenceOrder";

    // QC#30334 Add
    /** . */
    public static final String EXCLUSION_PO_LINE_TYPE = "EXCLUSION_PO_LINE_TYPE";

    // START 2023/04/17 G.Quan [QC#61206, ADD]
    /** . */
    public static final String XX_REC_HIST_CRAT_TS = "XX_REC_HIST_CRAT_TS";
    /** . */
    public static final String XX_REC_HIST_CRAT_BY_NM = "XX_REC_HIST_CRAT_BY_NM";
    /** . */
    public static final String XX_REC_HIST_UPD_TS = "XX_REC_HIST_UPD_TS";
    /** . */
    public static final String XX_REC_HIST_UPD_BY_NM = "XX_REC_HIST_UPD_BY_NM";
    /** . */
    public static final String XX_REC_HIST_TBL_NM = "XX_REC_HIST_TBL_NM";
    // END 2023/04/17 G.Quan [QC#61206, ADD]
}
