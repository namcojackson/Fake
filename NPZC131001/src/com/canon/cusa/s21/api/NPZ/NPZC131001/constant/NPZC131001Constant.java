/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC131001.constant;

/**
 * <pre>
 * NPZC1310:Drop Ship Create API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   CITS            T.Kikuhara      Create          N/A
 * 2016/03/30   CSAI            K.Lee           Update          QC#6175
 * 2019/02/19   Fujitsu         T.Ogura         Update          QC#30312
 * 2022/10/21   CITS            R.Azucena       Update          QC#60682
 *</pre>
 */
public class NPZC131001Constant {

    // MODE
    /**  */
    public static final String MODE_CREATE = "1";
    /**  */
    public static final String MODE_CANCEL = "2";

    // FIX VALUE
    /**  */
    public static final String MIN_SO_SLP_NUM = "001";
    /**  */
    public static final String INBOUND = "2";
    /**  */
    public static final int LENGTH_8 = 8;
    /**  */
    public static final int LENGTH_13 = 13;
    /**  */
    public static final int LENGTH_15 = 15;
    /**  */
    public static final String DUMMY_SO_NUM_FOR_INTG = "DUMMY_SO_NUM_FOR_INTG";
    /**  */
    public static final String CUST_DROP_SHIP_PO_QULF = "CUST_DROP_SHIP_PO_QULF";

    // ERROR ID
    /** . */
    public static final String NPZM0001E = "NPZM0001E";
    /** . */
    public static final String NPZM0018E = "NPZM0018E";
    /** . */
    public static final String NPZM0093E = "NPZM0093E";
    /** . */
    public static final String NPZM0180E = "NPZM0180E";
    /** . */
    public static final String NSZM0463E = "NSZM0463E";
    /** . */
    public static final String NPAM1250E = "NPAM1250E";
    /** . */
    public static final String NPZM0020E = "NPZM0020E";
    /** . */
    public static final String NWZM0998E = "NWZM0998E";
    /** . */
    public static final String NPZM0195E = "NPZM0195E";
    /** . */
    public static final String NLZM2314E = "NLZM2314E";
    /** . */
    public static final String NPZM0069E = "NPZM0069E";
    /** . */
    public static final String NPAM1169E = "NPAM1169E";
    /** . */
    public static final String NPAM1318E = "NPAM1318E";
    /** . */
    public static final String NPAM1320E = "NPAM1320E";
    /** . */
    public static final String NPAM1214E = "NPAM1214E";
    /** . */
    public static final String NPAM1351E = "NPAM1351E";
    /** . */
    // START 2019/02/19 T.Ogura [QC#30312,MOD]
//    public static final String NPAM1178E = "NPAM1178E";
    public static final String NPZM0310E = "NPZM0310E";
    // END   2019/02/19 T.Ogura [QC#30312,MOD]
    /** . */
    public static final String NPZM0230E = "NPZM0230E";

    // DB COLUMN
    /** . */
    public static final String OPEN_PO_FLG = "OPEN_PO_FLG";
    /** . */
    public static final String VAR_CHAR_CONST_NM = "VAR_CHAR_CONST_NM";
    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** . */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";
    /** . */
    public static final String ORIG_PO_ORD_NUM = "ORIG_PO_ORD_NUM";
    /** . */
    public static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";
    /** . */
    public static final String ORIG_PO_ORD_DTL_LINE_NUM = "ORIG_PO_ORD_DTL_LINE_NUM";
    /** . */
    public static final String WH_CD = "WH_CD";
    /** . */
    public static final String MDSE_CD = "MDSE_CD";
    /** . */
    public static final String PO_QTY = "PO_QTY";
    /** . */
    public static final String PO_CANC_QTY = "PO_CANC_QTY";
    /** . */
    public static final String SHIP_QTY = "SHIP_QTY";
    /** . */
    public static final String PO_RCV_NUM = "PO_RCV_NUM";
    /** . */
    public static final String RWS_REF_NUM = "RWS_REF_NUM";
    /** . */
    public static final String ASN_LINE_NUM = "ASN_LINE_NUM";
    /** . */
    public static final String CARR_CD = "CARR_CD";
    /** . */
    public static final String BOL_NUM = "BOL_NUM";
    /** . */
    public static final String PRO_NUM = "PRO_NUM";
    /** . */
    public static final String SLS_DT = "SLS_DT";
    /** . */
    public static final String ETA_DT = "ETA_DT";
    /** . */
    public static final String MDSE_CHECK = "MDSE_CHECK";
    /** . */
    public static final String VND_CD = "VND_CD";
    /** . */
    public static final String PO_STS_CD = "PO_STS_CD";
    /** . */
    public static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";
    /** . */
    public static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";
    /** . */
    public static final String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";
    /**  */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";
    /** . */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";
    /** . */
    public static final String PO_BAL_QTY = "PO_BAL_QTY";
    /** . */
    public static final String SHPG_PLN_NUM = "SHPG_PLN_NUM";
    /** . */
    public static final String RTL_WH_CD = "RTL_WH_CD";
    /** . */
    public static final String DIVIDE_FLG = "DIVIDE_FLG";
    /** . */
    public static final String MDSE_UPD_FLG = "MDSE_UPD_FLG";
    /** . */
    public static final String PO_RCV_QTY = "PO_RCV_QTY";
    /** . */
    public static final String DOM_INV_NUM = "DOM_INV_NUM";
    /** . */
    public static final String CARR_NM = "CARR_NM";
    /** . */
    public static final String VND_NM = "VND_NM";
    /** . */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";
    /** . */
    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";
    /** . */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";
    /** . */
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";
    /** . */
    public static final String CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";
    /** . */
    public static final String PO_RCV_LINE_NUM = "PO_RCV_LINE_NUM";
    /** . */
    public static final String SET_MDSE_CD = "SET_MDSE_CD";
    /** . */
    public static final String INVTY_LOC_NM = "INVTY_LOC_NM";
    /** . */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";
    /** . */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";
    /** . */
    public static final String SLS_REP_TOC_CD = "SLS_REP_TOC_CD";
    /** . */
    public static final String STK_STS_CD = "STK_STS_CD";
    /** . */
    public static final String TRX_HDR_NUM = "TRX_HDR_NUM";
    /** . */
    public static final String TRX_LINE_NUM = "TRX_LINE_NUM";
    /** . */
    public static final String TRX_LINE_SUB_NUM = "TRX_LINE_SUB_NUM";
    /** . */
    public static final String PO_SUBMT_PSN_CD = "PO_SUBMT_PSN_CD";
    /** . */
    public static final String PO_SUBMT_TS = "PO_SUBMT_TS";
    /** . */
    public static final String PO_APVL_PSN_CD = "PO_APVL_PSN_CD";
    /** . */
    public static final String PO_APVL_DT = "PO_APVL_DT";
    /** . */
    public static final String PO_APVL_TS = "PO_APVL_TS";
    /** . */
    public static final String PO_ORD_SRC_CD = "PO_ORD_SRC_CD";
    /** . */
    public static final String VND_DROP_SHIP_FLG = "VND_DROP_SHIP_FLG";
    /** . */
    public static final String PO_ORD_CMNT_TXT = "PO_ORD_CMNT_TXT";
    /** . */
    public static final String PO_SEND_FLG = "PO_SEND_FLG";
    /** . */
    public static final String PO_PRINT_FLG = "PO_PRINT_FLG";
    /** . */
    public static final String DS_PO_TP_CD = "DS_PO_TP_CD";
    /** . */
    public static final String DS_PO_TP_NM = "DS_PO_TP_NM";
    /** . */
    public static final String PO_QLFY_CD = "PO_QLFY_CD";
    /** . */
    public static final String PO_APVL_STS_CD = "PO_APVL_STS_CD";
    /** . */
    public static final String DEST_RTL_WH_CD = "DEST_RTL_WH_CD";
    /** . */
    public static final String SRC_RTL_WH_CD = "SRC_RTL_WH_CD";
    /** . */
    public static final String PRNT_VND_CD = "PRNT_VND_CD";
    /** . */
    public static final String PRNT_VND_NM = "PRNT_VND_NM";
    /** . */
    public static final String PRCH_GRP_CD = "PRCH_GRP_CD";
    /** . */
    public static final String VND_PMT_TERM_CD = "VND_PMT_TERM_CD";
    /** . */
    public static final String VND_PMT_TERM_DESC_TXT = "VND_PMT_TERM_DESC_TXT";
    /** . */
    public static final String RQST_TECH_TOC_CD = "RQST_TECH_TOC_CD";
    /** . */
    public static final String RQST_RCV_DT = "RQST_RCV_DT";
    /** . */
    public static final String RQST_RCV_TM = "RQST_RCV_TM";
    /** . */
    public static final String RTRN_SHIP_TO_RTL_WH_CD = "RTRN_SHIP_TO_RTL_WH_CD";
    /** . */
    public static final String SHIP_FROM_SO_NUM_LIST_TXT = "SHIP_FROM_SO_NUM_LIST_TXT";
    /** . */
    public static final String CARR_ACCT_NUM = "CARR_ACCT_NUM";
    /** . */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";
    /** . */
    public static final String TRSMT_METH_TP_CD = "TRSMT_METH_TP_CD";
    /** . */
    public static final String SEND_PO_FAX_NUM = "SEND_PO_FAX_NUM";
    /** . */
    public static final String SEND_PO_EML_ADDR = "SEND_PO_EML_ADDR";
    /** . */
    public static final String PO_SEND_TS = "PO_SEND_TS";
    /** . */
    public static final String SHIP_TO_ACCT_NM = "SHIP_TO_ACCT_NM";
    /** . */
    public static final String SHIP_TO_ADDL_LOC_NM = "SHIP_TO_ADDL_LOC_NM";
    /** . */
    public static final String SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";
    /** . */
    public static final String SHIP_TO_SCD_LINE_ADDR = "SHIP_TO_SCD_LINE_ADDR";
    /** . */
    public static final String SHIP_TO_THIRD_LINE_ADDR = "SHIP_TO_THIRD_LINE_ADDR";
    /** . */
    public static final String SHIP_TO_FRTH_LINE_ADDR = "SHIP_TO_FRTH_LINE_ADDR";
    /** . */
    public static final String SHIP_TO_FIRST_REF_CMNT_TXT = "SHIP_TO_FIRST_REF_CMNT_TXT";
    /** . */
    public static final String SHIP_TO_SCD_REF_CMNT_TXT = "SHIP_TO_SCD_REF_CMNT_TXT";
    /** . */
    public static final String SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";
    /** . */
    public static final String SHIP_TO_ST_CD = "SHIP_TO_ST_CD";
    /** . */
    public static final String SHIP_TO_PROV_NM = "SHIP_TO_PROV_NM";
    /** . */
    public static final String SHIP_TO_CNTY_NM = "SHIP_TO_CNTY_NM";
    /** . */
    public static final String SHIP_TO_POST_CD = "SHIP_TO_POST_CD";
    /** . */
    public static final String SHIP_TO_CTRY_CD = "SHIP_TO_CTRY_CD";
    /** . */
    public static final String CTAC_PSN_NM = "CTAC_PSN_NM";
    /** . */
    public static final String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";
    /** . */
    public static final String FRT_CHRG_TO_CD = "FRT_CHRG_TO_CD";
    /** . */
    public static final String FRT_CHRG_METH_CD = "FRT_CHRG_METH_CD";
    /** . */
    public static final String VND_ISS_ORD_NUM = "VND_ISS_ORD_NUM";
    /** . */
    public static final String DISP_PO_DTL_LINE_NUM = "DISP_PO_DTL_LINE_NUM";
    /** . */
    public static final String PO_LINE_TP_CD = "PO_LINE_TP_CD";
    /** . */
    public static final String PO_MDSE_CMPSN_TP_CD = "PO_MDSE_CMPSN_TP_CD";
    /** . */
    public static final String SET_PO_ORD_DTL_LINE_NUM = "SET_PO_ORD_DTL_LINE_NUM";
    /** . */
    public static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
    /** . */
    public static final String PO_DISP_QTY = "PO_DISP_QTY";
    /** . */
    public static final String PO_INV_QTY = "PO_INV_QTY";
    /** . */
    public static final String PO_DISP_UOM_CD = "PO_DISP_UOM_CD";
    /** . */
    public static final String THIS_MTH_FOB_COST_AMT = "THIS_MTH_FOB_COST_AMT";
    /** . */
    public static final String ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";
    /** . */
    public static final String ENT_PO_DTL_DEAL_NET_AMT = "ENT_PO_DTL_DEAL_NET_AMT";
    /** . */
    public static final String ENT_FUNC_NET_UNIT_PRC_AMT = "ENT_FUNC_NET_UNIT_PRC_AMT";
    /** . */
    public static final String ENT_PO_DTL_FUNC_NET_AMT = "ENT_PO_DTL_FUNC_NET_AMT";
    /** . */
    public static final String EXCH_RATE = "EXCH_RATE";
    /** . */
    public static final String CUST_UOM_CD = "CUST_UOM_CD";
    /** . */
    public static final String DEST_RTL_SWH_CD = "DEST_RTL_SWH_CD";
    /** . */
    public static final String SRC_RTL_SWH_CD = "SRC_RTL_SWH_CD";
    /** . */
    public static final String FRT_COND_CD = "FRT_COND_CD";
    /** . */
    public static final String ORIG_MDSE_CD = "ORIG_MDSE_CD";
    /** . */
    public static final String FROM_STK_STS_CD = "FROM_STK_STS_CD";
    /** . */
    public static final String TO_STK_STS_CD = "TO_STK_STS_CD";
    /** . */
    public static final String ADMIN_PSN_CD = "ADMIN_PSN_CD";
    /** . */
    public static final String PO_MATCH_TP_CD = "PO_MATCH_TP_CD";
    /** . */
    public static final String ORD_QTY = "ORD_QTY";
    /** . */
    public static final String CUST_ISS_PO_DT = "CUST_ISS_PO_DT";
    /** . */
    public static final String CPO_ORD_TP_CD = "CPO_ORD_TP_CD";
    /** . */
    public static final String TRX_REF_NUM = "TRX_REF_NUM";
    /** . */
    public static final String TRX_REF_LINE_NUM = "TRX_REF_LINE_NUM";
    /** . */
    public static final String TRX_REF_LINE_SUB_NUM = "TRX_REF_LINE_SUB_NUM";
    /** . */
    public static final String ASL_DTL_PK = "ASL_DTL_PK";
    /** . */
    public static final String ASL_MDSE_CD = "ASL_MDSE_CD";
    /** . */
    public static final String ASL_UNIT_PRC_AMT = "ASL_UNIT_PRC_AMT";
    /** . */
    public static final String VND_INVTY_LOC_CD = "VND_INVTY_LOC_CD";
    /** . */
    public static final String VND_ISS_PO_ORD_NUM = "VND_ISS_PO_ORD_NUM";
    /** . */
    public static final String VND_PO_ACK_STS_CD = "VND_PO_ACK_STS_CD";
    /** . */
    public static final String ORIG_DISP_PO_DTL_LINE_NUM = "ORIG_DISP_PO_DTL_LINE_NUM";
    /** . */
    public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";
    /** . */
    public static final String PO_ORD_DTL_CMNT_TXT = "PO_ORD_DTL_CMNT_TXT";
    /** . */
    public static final String RTE_STS_CD = "RTE_STS_CD";
    /** . */
    public static final String SO_CLOSE_FLG = "SO_CLOSE_FLG";
    /** . */
    public static final String SHPG_STS_CD = "SHPG_STS_CD";
    /** . */
    public static final String CCY_CD = "CCY_CD";

    // QC#27503 Start
    /** . */
    public static final String NPAM1287E = "NPAM1287E";
    /** . */
    public static final String NPZM0306E = "NPZM0306E";
    /** . */
    public static final String NPZM0156E = "NPZM0156E";
    // QC#27503 End

    // START 2022/10/21 R.Azucena [QC#60682, MOD]
    /** BASE_NUM_MAX_LIMIT -- 10 */
    public static final int BASE_NUM_MAX_LIMIT = 10;
    // END 2022/10/21 R.Azucena [QC#60682, MOD]
}
