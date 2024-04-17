/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB110001.constant;

import java.lang.reflect.Field;
import java.util.Comparator;

import parts.common.EZDTStringItem;

/**
 * <pre>
 * NPAB110001:Receiving ASN
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/07   CITS         T.Hakodate         ALL UPDATE      CSA NA
 * 2017/02/24   CITS         R.Shimamoto        Update          QC#17049
 * 2017/03/08   CITS         S.Endo             Update          QC#17627
 * 2017/08/15   CITS         K.Ogino            Update          QC#20500
 * 2018/03/23   CITS         S.Katsuma          Update          QC#24353
 * 04/02/2018   CITS         K.Ogino            Update          QC#25146
 * 09/13/2018   CITS         K.Ogino            Update          QC#26964/QC#26965(TST Impreso / Dietzgen PO EDI)
 * 02/19/2019   Fujitsu      T.Ogura            Update          QC#30312
 * 03/13/2023   Hitachi      TZ.Win             Update          QC#60960
 * 03/27/2023   Hitachi      TZ.Win             Update          QC#61269
 * 07/18/2023   Hitachi      TZ.Win             Update          QC#61614
 * 07/20/2023   Hitachi      S.Dong             Update          QC#61638
 *</pre>
 */
public class NPAB110001Constant {

    /** XR_MDSE_CD */
    public static final String XR_MDSE_CD = "XR_MDSE_CD";

    /** PO_RCV_LINE_NUM. */
    public static final String PO_RCV_LINE_NUM = "PO_RCV_LINE_NUM";

    /** CARR_CD. */
    public static final String CARR_CD = "CARR_CD";

    /** RTL_WH_CD. */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** PRCH_REQ_NUM. */
    public static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** PRCH_REQ_LINE_NUM. */
    public static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** PRCH_REQ_LINE_SUB_NUM. */
    public static final String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /** VAR_CUSA_GLBL_CMPY_CD. */
    public static final String VAR_CUSA_GLBL_CMPY_CD = "CUSA_GLBL_CMPY_CD";

    /** VAR_DROP_SHIP_WH_CD. */
    public static final String VAR_DROP_SHIP_WH_CD = "DROP_SHIP_WH_CD";

    /** CAL_SPLIT_FLAG. */
    public static final String CAL_SPLIT_FLAG = "CAL_SPLIT_FLAG";

    /** CAL_SPLIT_LINE_COUNT. */
    public static final String CAL_SPLIT_LINE_COUNT = "CAL_SPLIT_LINE_COUNT";

    /** CAL_ORD_PLUS_LINE_NUM. */
    public static final String CAL_ORD_PLUS_LINE_NUM = "CAL_ORD_PLUS_LINE_NUM";

    /** CAL_TOT_ASN_QTY. */
    public static final String CAL_TOT_ASN_QTY = "CAL_TOT_ASN_QTY";

    /** PD_MDSE_CD */
    public static final String PD_MDSE_CD = "PD_MDSE_CD";

    /** PD_PO_BAL_QTY */
    public static final String PD_PO_BAL_QTY = "PD_PO_BAL_QTY";

    /** PD_PO_ORD_DTL_LINE_NUM */
    public static final String PD_PO_ORD_DTL_LINE_NUM = "PD_PO_ORD_DTL_LINE_NUM";

    /** PO_STS_CD */
    public static final String PD_PO_STS_CD = "PD_PO_STS_CD";
    
    // START 2023/07/18 TZ.Win [QC#61614,ADD]
    /** PD_PO_LINE_STS_CD */
    public static final String PD_PO_LINE_STS_CD = "PD_PO_LINE_STS_CD";
    // END 2023/07/18 TZ.Win [QC#61614,ADD]

    /** PD_PO_ORD_NUM */
    public static final String PD_PO_ORD_NUM = "PD_PO_ORD_NUM";

    /** DP_DS_PO_TP_CD */
    public static final String DP_DS_PO_TP_CD = "DP_DS_PO_TP_CD";

    /** DP_DS_PO_TP_NM */
    public static final String DP_DS_PO_TP_NM = "DP_DS_PO_TP_NM";

    /** DP_PO_QLFY_CD */
    public static final String DP_PO_QLFY_CD = "DP_PO_QLFY_CD";

    /** PO_PO_SUBMT_PSN_CD */
    public static final String PO_PO_SUBMT_PSN_CD = "PO_PO_SUBMT_PSN_CD";

    /** PO_PO_SUBMT_PSN_CD */
    public static final String DP_VND_PMT_TERM_CD = "DP_VND_PMT_TERM_CD";

    /** PO_PO_SUBMT_PSN_CD */
    public static final String DP_VND_PMT_TERM_DESC_TXT = "DP_VND_PMT_TERM_DESC_TXT";

    /** PO_PO_SUBMT_PSN_CD */
    public static final String DP_RQST_TECH_TOC_CD = "DP_RQST_TECH_TOC_CD";

    /** PO_PO_SUBMT_PSN_CD */
    public static final String DP_RQST_RCV_TM = "DP_RQST_RCV_TM";
    // START 2023/07/20 S.Dong [QC#61638, ADD]
    public static final String DP_RQST_SHIP_DT = "DP_RQST_SHIP_DT";
    // END 2023/07/20 S.Dong [QC#61638, ADD]
    /** PD_SHIP_TO_CUST_CD */
    public static final String PD_SHIP_TO_CUST_CD = "PD_SHIP_TO_CUST_CD";

    /** PD_SHIP_TO_LOC_NM */
    public static final String PD_SHIP_TO_LOC_NM = "PD_SHIP_TO_LOC_NM";

    /** PO_PO_SUBMT_PSN_CD */
    public static final String DP_RTRN_SHIP_TO_RTL_WH_CD = "DP_RTRN_SHIP_TO_RTL_WH_CD";

    /** PO_PO_SUBMT_PSN_CD */
    public static final String DP_SHIP_FROM_SO_NUM_LIST_TXT = "DP_SHIP_FROM_SO_NUM_LIST_TXT";

    /** PO_PO_SUBMT_PSN_CD */
    public static final String DP_CARR_ACCT_NUM = "DP_CARR_ACCT_NUM";

    /** PO_PO_SUBMT_PSN_CD */
    public static final String DP_LINE_BIZ_TP_CD = "DP_LINE_BIZ_TP_CD";

    /** PO_PO_SUBMT_PSN_CD */
    public static final String DP_TRSMT_METH_TP_CD = "DP_TRSMT_METH_TP_CD";

    /** PO_PO_SUBMT_PSN_CD */
    public static final String DP_SEND_PO_FAX_NUM = "DP_SEND_PO_FAX_NUM";

    /** PO_PO_SUBMT_PSN_CD */
    public static final String DP_SEND_PO_EML_ADDR = "DP_SEND_PO_EML_ADDR";

    /** PO_PO_SUBMT_PSN_CD */
    public static final String DP_PO_SEND_TS = "DP_PO_SEND_TS";

    /** PO_PO_SUBMT_PSN_CD */
    public static final String DP_EIP_RPT_RQST_PK = "DP_EIP_RPT_RQST_PK";

    /** PO_PO_SUBMT_TS */
    public static final String PO_PO_SUBMT_TS = "PO_PO_SUBMT_TS";

    /** PO_PO_APVL_PSN_CD */
    public static final String PO_PO_APVL_PSN_CD = "PO_PO_APVL_PSN_CD";

    /** PD_PO_APVL_STS_CD */
    public static final String DP_PO_APVL_STS_CD = "DP_PO_APVL_STS_CD";

    /** PO_PO_APVL_DT */
    public static final String PO_PO_APVL_DT = "PO_PO_APVL_DT";

    /** PO_PO_APVL_TS */
    public static final String PO_PO_APVL_TS = "PO_PO_APVL_TS";

    /** DP_DEST_RTL_WH_CD */
    public static final String DP_DEST_RTL_WH_CD = "DP_DEST_RTL_WH_CD";

    /** PO_PO_APVL_TS */
    public static final String DP_SRC_RTL_WH_CD = "DP_SRC_RTL_WH_CD";

    /** DP_PRNT_VND_CD */
    public static final String DP_PRNT_VND_CD = "DP_PRNT_VND_CD";

    /** DP_PRNT_VND_NM */
    public static final String DP_PRNT_VND_NM = "DP_PRNT_VND_NM";

    /** PO_VND_CD */
    public static final String PO_VND_CD = "PO_VND_CD";

    /** PO_VND_NM */
    public static final String PO_VND_NM = "PO_VND_NM";

    /** PO_VND_DROP_SHIP_FLG */
    public static final String PO_VND_DROP_SHIP_FLG = "PO_VND_DROP_SHIP_FLG";

    /** PD_CCY_CD */
    public static final String PD_CCY_CD = "PD_CCY_CD";

    public static final String D_ASN_QTY = "D_ASN_QTY";

    public static final String D_MDSE_CD = "D_MDSE_CD";

    public static final String PD_PO_QTY = "PD_PO_QTY";

    public static final String D_MDSE_CD_UPD_FLG = "D_MDSE_CD_UPD_FLG";

    public static final String DPD_ASL_DTL_PK = "DPD_ASL_DTL_PK";

    public static final String DPD_ASL_MDSE_CD = "DPD_ASL_MDSE_CD";

    public static final String DPD_ASL_UNIT_PRC_AMT = "DPD_ASL_UNIT_PRC_AMT";

    public static final String DPD_CTAC_PSN_NM = "DPD_CTAC_PSN_NM";

    public static final String DPD_DEST_RTL_SWH_CD = "DPD_DEST_RTL_SWH_CD";

    public static final String DPD_DISP_PO_DTL_LINE_NUM = "DPD_DISP_PO_DTL_LINE_NUM";

    public static final String DPD_ENT_DEAL_NET_UNIT_PRC_AMT = "DPD_ENT_DEAL_NET_UNIT_PRC_AMT";

    public static final String DPD_ENT_FUNC_NET_UNIT_PRC_AMT = "DPD_ENT_FUNC_NET_UNIT_PRC_AMT";

    public static final String DPD_ENT_PO_DTL_DEAL_NET_AMT = "DPD_ENT_PO_DTL_DEAL_NET_AMT";

    public static final String DPD_ENT_PO_DTL_FUNC_NET_AMT = "DPD_ENT_PO_DTL_FUNC_NET_AMT";

    public static final String DPD_EXCH_RATE = "DPD_EXCH_RATE";

    public static final String DPD_FROM_STK_STS_CD = "DPD_FROM_STK_STS_CD";

    public static final String DPD_FRT_COND_CD = "DPD_FRT_COND_CD";

    public static final String DPD_MDSE_DESC_SHORT_TXT = "DPD_MDSE_DESC_SHORT_TXT";

    public static final String DPD_ORIG_DISP_PO_DTL_LINE_NUM = "DPD_ORIG_DISP_PO_DTL_LINE_NUM";

    public static final String DPD_ORIG_MDSE_CD = "DPD_ORIG_MDSE_CD";

    public static final String DPD_ORIG_PO_ORD_DTL_LINE_NUM = "DPD_ORIG_PO_ORD_DTL_LINE_NUM";

    public static final String DPD_ORIG_PO_ORD_NUM = "DPD_ORIG_PO_ORD_NUM";

    public static final String DPD_PO_DISP_QTY = "DPD_PO_DISP_QTY";

    public static final String DPD_PO_DISP_UOM_CD = "DPD_PO_DISP_UOM_CD";

    public static final String DPD_PO_INV_QTY = "DPD_PO_INV_QTY";

    public static final String DPD_PO_LINE_TP_CD = "DPD_PO_LINE_TP_CD";

    public static final String DPD_PO_MATCH_TP_CD = "DPD_PO_MATCH_TP_CD";

    public static final String DPD_PO_MDSE_CMPSN_TP_CD = "DPD_PO_MDSE_CMPSN_TP_CD";

    public static final String DPD_PO_SEND_TS = "DPD_PO_SEND_TS";

    public static final String DPD_RQST_RCV_DT = "DPD_RQST_RCV_DT";

    public static final String DPD_RQST_RCV_TM = "DPD_RQST_RCV_TM";
    // START 2023/07/20 S.Dong [QC#61638, ADD]
    public static final String DPD_RQST_SHIP_DT = "DPD_RQST_SHIP_DT";
    // END 2023/07/20 S.Dong [QC#61638, ADD]
    public static final String DPD_PRCH_GRP_CD = "DPD_PRCH_GRP_CD";

    public static final String DPD_PRCH_REQ_LINE_NUM = "DPD_PRCH_REQ_LINE_NUM";

    public static final String DPD_PRCH_REQ_LINE_SUB_NUM = "DPD_PRCH_REQ_LINE_SUB_NUM";

    public static final String DPD_PRCH_REQ_NUM = "DPD_PRCH_REQ_NUM";

    public static final String DPD_PRO_NUM = "DPD_PRO_NUM";

    public static final String PA_PO_ACCT_TP_CD = "PA_PO_ACCT_TP_CD";

    public static final String PA_COA_CMPY_CD = "PA_COA_CMPY_CD";

    public static final String PA_COA_AFFL_CD = "PA_COA_AFFL_CD";

    public static final String PA_COA_BR_CD = "PA_COA_BR_CD";

    public static final String PA_COA_CH_CD = "PA_COA_CH_CD";

    public static final String PA_COA_CC_CD = "PA_COA_CC_CD";

    public static final String PA_COA_ACCT_CD = "PA_COA_ACCT_CD";

    public static final String PA_COA_PROD_CD = "PA_COA_PROD_CD";

    public static final String PA_COA_PROJ_CD = "PA_COA_PROJ_CD";

    public static final String PA_COA_EXTN_CD = "PA_COA_EXTN_CD";

    public static final String DPD_PO_ORD_DTL_LINE_NUM = "DPD_PO_ORD_DTL_LINE_NUM";

    public static final String DPD_SET_PO_ORD_DTL_LINE_NUM = "DPD_SET_PO_ORD_DTL_LINE_NUM";

    public static final String DPD_SHIP_FROM_SO_NUM_LIST_TXT = "DPD_SHIP_FROM_SO_NUM_LIST_TXT";

    public static final String DPD_SHIP_TO_ACCT_NM = "DPD_SHIP_TO_ACCT_NM";

    public static final String DPD_INVTY_LOC_CD = "DPD_INVTY_LOC_CD";

    public static final String DPD_SRC_RTL_SWH_CD = "DPD_SRC_RTL_SWH_CD";

    public static final String DPD_SVC_CONFIG_MSTR_PK = "DPD_SVC_CONFIG_MSTR_PK";

    public static final String DPD_TO_STK_STS_CD = "DPD_TO_STK_STS_CD";

    public static final String DPD_TRX_REF_LINE_NUM = "DPD_TRX_REF_LINE_NUM";

    public static final String DPD_TRX_REF_LINE_SUB_NUM = "DPD_TRX_REF_LINE_SUB_NUM";

    public static final String DPD_TRX_REF_NUM = "DPD_TRX_REF_NUM";

    public static final String DPD_VND_INVTY_LOC_CD = "DPD_VND_INVTY_LOC_CD";

    public static final String DPD_VND_ISS_PO_ORD_NUM = "DPD_VND_ISS_PO_ORD_NUM";

    public static final String DPD_VND_PMT_TERM_DESC_TXT = "DPD_VND_PMT_TERM_DESC_TXT";

    public static final String DPD_VND_PO_ACK_STS_CD = "DPD_VND_PO_ACK_STS_CD";

    public static final String ED_ASN_BOL_NUM = "ED_ASN_BOL_NUM";

    public static final String ED_ASN_CARR_CD = "ED_ASN_CARR_CD";

    public static final String ED_ASN_LINE_NUM = "ED_ASN_LINE_NUM";

    public static final String ED_ASN_PRO_NUM = "ED_ASN_PRO_NUM";

    public static final String ED_ASN_SO_NUM = "ED_ASN_SO_NUM";

    public static final String ED_ASN_SO_SLP_NUM = "ED_ASN_SO_SLP_NUM";

    public static final String ED_GLBL_CMPY_CD = "ED_GLBL_CMPY_CD";

    public static final String ED_PO_ORD_DTL_LINE_NUM = "ED_PO_ORD_DTL_LINE_NUM";

    public static final String ED_PO_ORD_NUM = "ED_PO_ORD_NUM";

    public static final String ED_VND_INVTY_LOC_CD = "ED_VND_INVTY_LOC_CD";

    public static final String M_MDSE_NM = "M_MDSE_NM";

    public static final String PD_ADMIN_PSN_CD = "PD_ADMIN_PSN_CD";

    public static final String PD_BILL_TO_CUST_CD = "PD_BILL_TO_CUST_CD";

    public static final String PD_CARR_CD = "PD_CARR_CD";

    public static final String PD_CPO_DTL_LINE_NUM = "PD_CPO_DTL_LINE_NUM";

    public static final String PD_CPO_DTL_LINE_SUB_NUM = "PD_CPO_DTL_LINE_SUB_NUM";

    public static final String PD_CPO_ORD_NUM = "PD_CPO_ORD_NUM";

    public static final String PD_CPO_ORD_TP_CD = "PD_CPO_ORD_TP_CD";

    public static final String PD_CUST_ISS_PO_DT = "PD_CUST_ISS_PO_DT";

    public static final String PD_CUST_ISS_PO_NUM = "PD_CUST_ISS_PO_NUM";

    public static final String PD_CUST_UOM_CD = "PD_CUST_UOM_CD";

    public static final String PD_FRT_CHRG_METH_CD = "PD_FRT_CHRG_METH_CD";

    public static final String PD_FRT_CHRG_TO_CD = "PD_FRT_CHRG_TO_CD";

    public static final String PD_MDSE_NM = "PD_MDSE_NM";

    public static final String PD_ORD_QTY = "PD_ORD_QTY";

    public static final String PD_PO_ORD_DTL_CMNT_TXT = "PD_PO_ORD_DTL_CMNT_TXT";

    public static final String PD_PO_RCV_QTY = "PD_PO_RCV_QTY";

    public static final String PD_SELL_TO_CUST_CD = "PD_SELL_TO_CUST_CD";

    public static final String PD_SHIP_TO_ADDL_LOC_NM = "PD_SHIP_TO_ADDL_LOC_NM";

    public static final String PD_SHIP_TO_CNTY_NM = "PD_SHIP_TO_CNTY_NM";

    public static final String PD_SHIP_TO_CTRY_CD = "PD_SHIP_TO_CTRY_CD";

    public static final String PD_SHIP_TO_CTY_ADDR = "PD_SHIP_TO_CTY_ADDR";

    public static final String PD_SHIP_TO_FIRST_LINE_ADDR = "PD_SHIP_TO_FIRST_LINE_ADDR";

    public static final String PD_SHIP_TO_FIRST_REF_CMNT_TXT = "PD_SHIP_TO_FIRST_REF_CMNT_TXT";

    public static final String PD_SHIP_TO_FRTH_LINE_ADDR = "PD_SHIP_TO_FRTH_LINE_ADDR";

    public static final String PD_SHIP_TO_POST_CD = "PD_SHIP_TO_POST_CD";

    public static final String PD_SHIP_TO_PROV_NM = "PD_SHIP_TO_PROV_NM";

    public static final String PD_SHIP_TO_SCD_LINE_ADDR = "PD_SHIP_TO_SCD_LINE_ADDR";

    public static final String PD_SHIP_TO_SCD_REF_CMNT_TXT = "PD_SHIP_TO_SCD_REF_CMNT_TXT";

    public static final String PD_SHIP_TO_ST_CD = "PD_SHIP_TO_ST_CD";

    public static final String PD_SHIP_TO_THIRD_LINE_ADDR = "PD_SHIP_TO_THIRD_LINE_ADDR";

    public static final String PD_SHPG_PLN_NUM = "PD_SHPG_PLN_NUM";

    public static final String PD_SHPG_SVC_LVL_CD = "PD_SHPG_SVC_LVL_CD";

    public static final String PD_THIS_MTH_FOB_COST_AMT = "PD_THIS_MTH_FOB_COST_AMT";

    public static final String PM_PO_MSG_PK = "PM_PO_MSG_PK";

    public static final String PM_PO_MSG_SUBMT_PSN_CD = "PM_PO_MSG_SUBMT_PSN_CD";

    public static final String PM_PO_MSG_TP_CD = "PM_PO_MSG_TP_CD";

    public static final String PM_PRCH_REQ_LINE_NUM = "PM_PRCH_REQ_LINE_NUM";

    public static final String PM_PRCH_REQ_LINE_SUB_NUM = "PM_PRCH_REQ_LINE_SUB_NUM";

    public static final String PM_PO_MSG_TXT = "PM_PO_MSG_TXT";

    public static final String PM_PRCH_REQ_NUM = "PM_PRCH_REQ_NUM";

    public static final String PO_ORD_SRC_CD = "PO_ORD_SRC_CD";

    public static final String PO_PO_ORD_CMNT_TXT = "PO_PO_ORD_CMNT_TXT";

    public static final String PO_PO_PRINT_FLG = "PO_PO_PRINT_FLG";

    public static final String PO_PO_SEND_FLG = "PO_PO_SEND_FLG";

    public static final String PO_VND_ISS_ORD_NUM = "PO_VND_ISS_ORD_NUM";

    /** PO_PO_ORD_SRC_CD */
    public static final String PO_PO_ORD_SRC_CD = "PO_PO_ORD_SRC_CD";

    /** GLBL_CMPY_CD */
    public static final String EH_GLBL_CMPY_CD = "EH_GLBL_CMPY_CD";

    /** ED_ASN_SO_NUM */
    public static final String EH_ASN_SO_NUM = "EH_ASN_SO_NUM";

    /** PO_ORD_NUM */
    public static final String EH_EDI_PO_ORD_NUM = "EH_EDI_PO_ORD_NUM";

    /** SHIP_FROM_SO_NUM */
    public static final String EH_SHIP_FROM_SO_NUM = "EH_SHIP_FROM_SO_NUM";

    /** ES_ASN_MDSE_CD */
    public static final String ES_ASN_MDSE_CD = "ES_ASN_MDSE_CD";

    /** ES_SO_SER_ID */
    public static final String ES_SO_SER_ID = "ES_SO_SER_ID";

    /** ED_EDI_PO_ORD_DTL_LINE_NUM */
    public static final String ED_EDI_PO_ORD_DTL_LINE_NUM = "ED_EDI_PO_ORD_DTL_LINE_NUM";

    /** ED_ORD_PLUS_LINE_NUM */
    public static final String ED_ORD_PLUS_LINE_NUM = "ED_ORD_PLUS_LINE_NUM";

    /** EDI_SUB_NUM */
    public static final String ED_EDI_SUB_NUM = "ED_EDI_SUB_NUM";

    /** ASN_TTL_WT */
    public static final String ED_ASN_TTL_WT = "ED_ASN_TTL_WT";

    /** ASN_TOT_FRT_AMT */
    public static final String ED_ASN_TOT_FRT_AMT = "ED_ASN_TOT_FRT_AMT";

    /** ASN_PLN_DELY_DT */
    public static final String ED_ASN_PLN_DELY_DT = "ED_ASN_PLN_DELY_DT";

    /** MDSE_CD */
    public static final String ED_MDSE_CD = "ED_MDSE_CD";

    /** ASN_MDSE_CD */
    public static final String ED_ASN_MDSE_CD = "ED_ASN_MDSE_CD";

    /** ASN_QTY */
    public static final String ED_ASN_QTY = "ED_ASN_QTY";

    /** ASN_STK_STS_CD */
    public static final String ED_ASN_STK_STS_CD = "ED_ASN_STK_STS_CD";

    /** MDSE_CD_UPD_FLG */
    public static final String ED_MDSE_CD_UPD_FLG = "ED_MDSE_CD_UPD_FLG";

    /** CUST_ISS_PO_NUM */
    public static final String ED_CUST_ISS_PO_NUM = "ED_CUST_ISS_PO_NUM";

    /** PO_ORD_NUM */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";

    /** PO_ORD_CMNT_TXT */
    public static final String PO_ORD_CMNT_TXT = "PO_ORD_CMNT_TXT";

    /** PO_ORD_NUM */
    public static final String XR_PO_ORD_NUM = "XR_PO_ORD_NUM";

    /** PO_STS_CD */
    public static final String PO_STS_CD = "PO_STS_CD";

    /** VND_DROP_SHIP_FLG */
    public static final String VND_DROP_SHIP_FLG = "VND_DROP_SHIP_FLG";

    /** VND_CD */
    public static final String VND_CD = "VND_CD";

    /** RD_RWS_DTL_LINE_NUM */
    public static final String RD_RWS_DTL_LINE_NUM = "RD_RWS_DTL_LINE_NUM";

    /** ES_SER_NUM */
    public static final String ES_SER_NUM = "ES_SER_NUM";

    /** ES_MDSE_CD */
    public static final String ES_MDSE_CD = "ES_MDSE_CD";

    /** ASN_SO_NUM */
    public static final String ASN_SO_NUM = "ASN_SO_NUM";

    /** RWS_NUM */
    public static final String RWS_NUM = "RWS_NUM";

    /** RWS_DTL_LINE_NUM */
    public static final String RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";

    /** TRX_ORD_NUM */
    public static final String TRX_ORD_NUM = "TRX_ORD_NUM";

    /** MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** RWS_STK_STS_CD */
    public static final String RWS_STK_STS_CD = "RWS_STK_STS_CD";

    /** RWS_BAL_QTY */
    public static final String RWS_BAL_QTY = "RWS_BAL_QTY";

    /** TRX_LINE_NUM */
    public static final String TRX_LINE_NUM = "TRX_LINE_NUM";

    /** RWS_REF_NUM */
    public static final String RWS_REF_NUM = "RWS_REF_NUM";

    /** PO_ORD_DTL_LINE_NUM */
    public static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** SHIP_FROM_LOC_CD */
    public static final String SHIP_FROM_LOC_CD = "SHIP_FROM_LOC_CD";

    /** SCE_ORD_TP_CD */
    public static final String SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /** The field of [@] is not input. */
    public static final String MSG_ID_NPAM1173E = "NPAM1173E";

    /** Could not get Mail Template. MAIL_TEMPLATE_ID : [@] */
    public static final String MSG_ID_NPAM0064E = "NPAM0064E";

    /** The Mdse Code does not match.[@] */
    public static final String MSG_ID_NPAM1290E = "NPAM1290E";

    /** Asn Qty is invalid. [@] */
    public static final String MSG_ID_NPAM1291E = "NPAM1291E";

    /**
     * Could not get MailGroupAddress. MAIL_GROUP_ID_TO : [@]
     * MAIL_KEY1 : [@]
     */
    public static final String MSG_ID_NPAM0063E = "NPAM0063E";

    /**
     * Target data does not exist. Table Name: [@], Key Item: [@], Key
     * Value: [@].
     */
    public static final String MSG_ID_NPAM1288E = "NPAM1288E";

    /** "CUSA Global Company Code" does not exist in "VAR_CHAR_CONST". */
    public static final String MSG_ID_NPAM1496E = "NPAM1496E";

    /** The Mdse Cd does not exist.[@] */
    public static final String MSG_ID_NPAM1176E = "NPAM1176E";

    /**
     * Mandatory parameter is not set. Item Name: [@], Table Name:
     * [@], Key Item: [@].
     */
    public static final String MSG_ID_NPAM1321E = "NPAM1321E";

    /** Cannot retrieve mail address. */
    public static final String MSG_ID_NPAM1265E = "NPAM1265E";

    /** No PO data found.[@] */
    public static final String MSG_ID_NPAM1169E = "NPAM1169E";

    /** Mail Template ID Send To Ordered User */
    public static final String MAIL_TMPL_ID_TO_ORDER = "NPAB1100M002";

    /** It exceeds the maximum number of digits.[@] */
    public static final String MSG_ID_NPAM1320E = "NPAM1320E";

    /** The PO has been closed. */
    public static final String MSG_ID_NPAM1201E = "NPAM1201E";

    /** The PO has been canceled. */
    public static final String MSG_ID_NPAM1285E = "NPAM1285E";

    /** Cannot process since the PO status is Saved. */
    public static final String MSG_ID_NPZM0080E = "NPZM0080E";

    /** Cannot process since the PO status is Waiting Approval. */
    public static final String MSG_ID_NPZM0081E = "NPZM0081E";

    /** The PO Status has no value. */
    public static final String MSG_ID_NPAM1286E = "NPAM1286E";

    /** Failed to update [@] table. [@] is [@]. */
    public static final String MSG_ID_NPAM1003E = "NPAM1003E";

    /** Error of API [@] */
    public static final String MSG_ID_NPAM1178E = "NPAM1178E";

    // QC#28968 Update
    /** No PO Ord Dtl data found.[@] */
    public static final String MSG_ID_NPAM1637E = "NPAM1637E";

    /**
     * There is no data corresponding to the RWS Header.
     */
    public static final String MSG_ID_NLZM1011E = "NLZM1011E";

    /**
     * There is multiple data corresponding to the RWS Header.
     */
    public static final String MSG_ID_NLZM2180E = "NLZM2180E";

    /**
     * There is multiple data corresponding to the RWS details.
     */
    public static final String MSG_ID_NLZM2181E = "NLZM2181E";

    /**
     * Failed to update RWS_HDR
     */
    public static final String MSG_ID_NLZM2182E = "NLZM2182E";

    /**
     * Failed to update RWS_DTL
     */
    public static final String MSG_ID_NLZM2183E = "NLZM2183E";

    /**
     * There is no data for RWS details.
     */
    public static final String MSG_ID_NLZM1006E = "NLZM1006E";

    /**
     * The Length of Serial # is different from master's value.
     */
    public static final String MSG_ID_NPAM1507W = "NPAM1507W";

    /**
     * The Range of Serial # is different from master's value.
     */
    public static final String MSG_ID_NPAM1508W = "NPAM1508W";

    /** It failed to register an email. */
    public static final String MSG_ID_NPAM1200E = "NPAM1200E";

    /** Failed to update. [@] */
    public static final String MSG_ID_NPAM1171E = "NPAM1171E";

    /** Failed to delete the data. */
    public static final String MSG_ID_NPAM1298E = "NPAM1298E";

    /**
     * The process will be skipped since PO has been received. [SO#: @,
     * PO#: @]
     */
    public static final String MSG_ID_NPAM1575I = "NPAM1575I";

    // START 2019/02/19 T.Ogura [QC#30312,ADD]
    /** Error of API [@ PO#:[@]  ASN_SO#:[@]] */
    public static final String MSG_ID_NPAM1641E = "NPAM1641E";
    // END   2019/02/19 T.Ogura [QC#30312,ADD]

    /** Search Condition '*' */
    public static final String SEARCH_ALL = "*";

    /** Prefix P1 */
    public static final String P1_PREFIX = "P1_";

    /** Prefix P2 */
    public static final String P2_PREFIX = "P2_";

    /** Brunch Flag:WAREHOUSE */
    public static final String BR_FLG_WAREHOUSE = "1";

    /** Brunch Flag:DIRECT */
    public static final String BR_FLG_DIRECT = "2";

    /** map key : glblCmpyCd. */
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** map key : poRcvNum. */
    public static final String BIND_PO_RCV_NUM = "poRcvNum";

    /** map key : rwsNum. */
    public static final String BIND_RWS_NUM = "rwsNum";

    /** map key : prntCmpyVnd. */
    public static final String BIND_PRNT_CMPY_VND = "prntCmpyVnd";

    /** map key : prntCmpyVndCd. */
    public static final String BIND_PRNT_CMPY_VND_CD = "prntCmpyVndCd";

    /** map key : whCd. */
    public static final String BIND_WH_CD = "whCd";

    /** map key : rwsRefNum. */
    public static final String BIND_RWS_REF_NUM = "rwsRefNum";

    /** map key : mdseCd. */
    public static final String BIND_MDSE_CD = "mdseCd";

    /** map key : ediPoOrdDtlLineNum. */
    public static final String BIND_EDI_PO_ORD_DTL_LINE_NUM = "ediPoOrdDtlLineNum";

    /** map key : procFlg. */
    public static final String BIND_PROC_FLG = "procFlg";

    /** map key : asnSoNum. */
    public static final String BIND_ASN_SO_NUM = "asnSoNum";

    /** map key : poAcctTpCd. */
    public static final String BIND_PO_ACCT_TP_CD = "poAcctTpCd";

    /** map key : shpgPlnNum. */
    public static final String BIND_SHPG_PLN_NUM = "shpgPlnNum";

    /** map key : vndCd. */
    public static final String BIND_VND_CD = "vndCd";

    /** map key : soNum. */
    public static final String BIND_SO_NUM = "soNum";

    /** map key : trdPtnrCarrCd. */
    public static final String BIND_TRD_PTNR_CARR_CD = "trdPtnrCarrCd";

    /** map key : shpgSvcLclCd. */
    public static final String BIND_SHPG_SVC_LCL_CD = "shpgSvcLclCd";

    /** map key : origPoOrdNum. */
    public static final String BIND_ORIG_PO_ORD_NUM = "origPoOrdNum";

    /** map key : origPoOrdDtlLineNum. */
    public static final String BIND_ORIG_PO_ORD_DTL_LINE_NUM = "origPoOrdDtlLineNum";

    /** map key : openPoFlag. */
    public static final String BIND_OPEN_PO_FLG = "openPoFlag";

    /** map key : aslStartDt. */
    public static final String BIND_ASL_START_DT = "aslStartDt";

    /** map key : aslEndDt. */
    public static final String BIND_ASL_END_DT = "aslEndDt";

    /** map key : slsDate. */
    public static final String BIND_SLS_DT = "slsDate";

    /** map key : poRcvNum. */
    public static final String PO_RCV_NUM = "poRcvNum";

    /** map key : splyItemNum. */
    public static final String BIND_SPLY_ITEM_NUM = "splyItemNum";

    /** map key : poOrdNum. */
    public static final String BIND_PO_ORD_NUM = "poOrdNum";

    /** map key : poOrdDtlLineNum. */
    public static final String BIND_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /** map key : aslMdseCd. */
    public static final String BIND_ASL_MDSE_CD = "aslMdseCd";

    /** map key : PO_QTY. */
    public static final String BIND_PO_QTY = "poQty";

    /** map key : rwsStsCd. */
    public static final String BIND_RWS_STS_CD = "rwsStsCd";

    /** map key : poRcvQty. */
    public static final String BIND_PO_RCV_QTY = "poRcvQty";

    /** map key : prchReqLineTpCdInsourcedPo. */
    public static final String BIND_PRCH_REQ_LINE_TP_CD_INSOURCED_PO = "prchReqLineTpCdInsourcedPo";

    /** map key : prchReqRecTpCdTech. */
    public static final String BIND_PRCH_REQ_REC_TP_CD_TECH = "prchReqRecTpCdTech";

    /** map key : sceOrdTpCd. */
    public static final String BIND_SCE_ORD_TP_CD = "sceOrdTpCd";

    // START 2018/03/23 S.Katsuma [QC#24353,ADD]
    /** map key : carrCd. */
    public static final String BIND_CARR_CD = "carrCd";
    // END 2018/03/23 S.Katsuma [QC#24353,ADD]
    
    // START 2023/07/18 TZ.Win [QC#61614, DEL]
    // START 2023/03/13 TZ.Win [QC#60960, ADD]
    /** map key : poLineStsCd. */
//    public static final String BIND_PO_LINE_STS_CD_LIST = "poLineStsCdList";
    // END 2023/03/13 TZ.Win [QC#60960, ADD]
    // END 2023/07/18 TZ.Win [QC#61614, DEL]

    /** Field Name : rwsStsCd. */
    public static final String FIELD_RWS_STS_CD = "rwsStsCd";

    /** SQL ID */
    public static final String SQL_ID_011 = "011";

    /** SQL Condition Value */
    public static final String SQL_COND_KEY_GLBL_CMPY_CD_01 = "glblCmpyCd01";

    /** SQL Condition Value */
    public static final String SQL_COND_KEY_PO_RCV_NUM_01 = "poRcvNum01";

    /** SQL Condition Value */
    public static final String SQL_COND_KEY_INBD_LTST_REC_FLG_01 = "inbdLtstRecFlg01";

    /** Mandatory ASN Header Items */
    // T.Wada Mod QC12630
    // public static final String[] MANDATORY_HDR_ITEMS =
    // {"asnEdiProcStsCd", "asnShipDtTmTs", "asnSoNum", "cmProcStsCd",
    // "ediPoOrdNum", "ediRcvTs", "glblCmpyCd", "itrlIntfcId",
    // "shipFromSoNum", "shipToCustCd" };
    public static final String[] MANDATORY_HDR_ITEMS = {"asnEdiProcStsCd", "asnShipDtTmTs", "asnSoNum", "cmProcStsCd", "ediPoOrdNum", "ediRcvTs", "glblCmpyCd", "itrlIntfcId", "shipFromSoNum" };

    /** Mandatory ASN Detail Items */
    public static final String[] MANDATORY_DTL_ITEMS = {"glblCmpyCd", "asnSoNum", "asnLineNum", "asnPlnDelyDt", "asnMdseCd", "asnQty", "mdseCdUpdFlg" };

    /** Mandatory ASN Serial Items */
    public static final String[] MANDATORY_SER_ITEMS = {"soSerId", "glblCmpyCd", "asnMdseCd", "asnSoNum", "asnSoSlpNum", "serNum" };

    /** MailMessage Map Key:poOrdNum */
    public static final String MAIL_MSG_MAP_KEY_PO = "poOrdNum";

    /** MailMessage Map Key:soOrdNum */
    public static final String MAIL_MSG_MAP_KEY_SO = "asnSoNum";

    /** MailMessage Map Key:rcvDate */
    public static final String MAIL_MSG_MAP_KEY_STS = "ediSts";

    /** MailMessage Map Key:rcvDate */
    public static final String MAIL_MSG_MAP_KEY_DATE = "rcvDate";

    // START 2023/03/27 TZ.Win [QC#61269,ADD]
    /** MailMessage Map Key:vndCD */
    public static final String MAIL_MSG_MAP_KEY_VND = "vndCD";
    // END 2023/03/27 TZ.Win [QC#61269,ADD]

    /** MailMessage Map Key:msgList */
    public static final String MAIL_MSG_MAP_KEY_MSG_LIST = "msgList";

    /** Mail Template ID Send To Admin User */
    public static final String MAIL_TMPL_ID_TO_ADMIN = "NPAB1100M001";

    /** Mail Group ID From : FROM0002 */
    public static final String MAIL_GROUP_ID_FROM = "FROM0002";

    /** Mail Key From : DP */
    public static final String MAIL_KEY_FROM = "NP";

    /** Length of INV_NUM */
    public static final int INV_NUM_LEN = 13;

    /** Length of PO_RCV_MSG_TXT */
    public static final int PO_RCV_MSG_TXT_LEN = 65;

    /** Length of BAT_ERR_MSG_TXT */
    public static final int BAT_ERR_MSG_TXT_LEN = 400;

    /** Length of MDSE_CD */
    public static final int MDSE_CD_LEN = 16;

    /** Length of EDI_PO_ORD_DTL_LINE_NUM */
    public static final int EDI_PO_ORD_DTL_LINE_NUM_LEN = 6;

    /** Length of BOL_NUM */
    public static final int BOL_NUM_LEN = 30;

    /** Length of PO_ORD_DTL_LINE_NUM */
    public static final int PO_ORD_DTL_LINE_NUM_LEN = 3;

    /** Length of EDI_PO_ORD_NUM */
    public static final int EDI_PO_ORD_NUM_LEN = 35;

    /** Length of PO_ORD_NUM */
    public static final int PO_ORD_NUM_LEN = 8;

    /** Length of EDI_RCV_TS */
    public static final int EDI_RCV_TS_LEN = 17;

    /** Length of RWS_REF_NUM */
    public static final int RWS_REF_NUM_LEN = 15;

    /** MAP_KEY_STRING_FLAG */
    public static final String SPLIT_FLAG = "SPLIT_FLAG";

    /** MAP_KEY_ITEM_CHANGE_FLAG */
    public static final String ITEM_HANGE_FLAG = "ITEM_HANGE_FLAG";

    /** INVTY_LOC_CD */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** CRLF */
    public static final String CRLF = "\r\n";

    /** HYPHEN */
    public static final String HYPHEN = "-";

    /** CARET */
    public static final String CARET = "^";

    /** DOLLAR */
    public static final String DOLLAR = "$";

    /** */
    public static final String LF_PAD_CHAR = "0";

    /** Length of CSA's EDI_NUM */
    public static final int CSA_EDI_NUM_LEN = 3;

    /** space * 4 */
    public static final String INDENT = "    ";

    /** Mail Character Set */
    public static final String MAIL_CHARSET = "ISO-8859-1";

    /** debug message code */
    public static final int DEBUG_MESSAGE_CODE = 600;

    /** TimeStamp digit : 8 */
    public static final int TS_LENGTH_YYYYMMDD = 8;

    /** TimeStamp digit : 14 */
    public static final int TS_LENGTH_YYYYMMDDHHMMSS = 14;

    /** TimeStamp digit : 17 */
    public static final int TS_LENGTH_YYYYMMDDHHMMSS_SSS = 17;

    /** TimeStamp format : 8 byte */
    public static final String TS_FORMAT_YYYYMMDD = "yyyyMMdd";

    /** TimeStamp format : 14 byte */
    public static final String TS_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String TS_FORMAT_YYYYMMDDHHMMSS_SSS = "yyyyMMddHHmmssSSS";

    /** TimeStamp format : For Error Mail */
    public static final String TS_FORMAT_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    // QC#17049 Add.
    /**
     * NPZC1030 Event ASN
     */
    public static final String NPZC1030_EVENT_ASN = "9";

    /**
     * NPZC1030 Mode Update
     */
    public static final String NPZC1030_MODE_UPDATE = "2";

    /** map key : flgY. */
    public static final String FLG_Y = "flgY";

    // QC#25146
    /** NPAM1364E: The combination of specified input parameters Supplier, Site and Item does not exist in ASL. */
    public static final String NPAM1364E = "NPAM1364E";

    /** NPZM0156E: No PO Detail data found.*/
    public static final String NPZM0156E = "NPZM0156E";
    // QC#25146

    /**
     * Override Comparator Class QC#26965 Add class.
     */
    public static class Comp implements Comparator<Object> {

        /** sortKey */
        private String sortKey;

        /**
         * Constructor
         * @param sortKey String
         */
        public Comp(String sortKey) {
            this.sortKey = sortKey;
        }

        /**
         * comparator
         * @param oPMsg1 Object
         * @param oPMsg2 Object
         * @return result int
         */
        @Override
        public int compare(Object oPMsg1, Object oPMsg2) {
            String ret1 = getValue(sortKey, oPMsg1);
            String ret2 = getValue(sortKey, oPMsg2);
            return ret1.compareTo(ret2);
        }

        private String getValue(String key, Object oPMsg) {
            try {
                Field field = oPMsg.getClass().getField(key);
                EZDTStringItem item = (EZDTStringItem) field.get(oPMsg);
                return item.getValue();
            } catch (Exception e) {
                return "";
            }
        }
    }
}
