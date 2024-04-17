/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB100001;

import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ADMIN_PSN_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ASL_DTL_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ASL_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ASL_UNIT_PRC_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BILL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_ACK_EDI_PROC_STS_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_ASL_END_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_ASL_START_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_ITRL_INTFC_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_PENDING;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_PO_ACK_DTL_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_PO_ACK_HDR_LTST_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_PO_ACK_HDR_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_PO_ACK_HDR_WRK_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_PO_ACK_UPD_PROC_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_RGTN_STS_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_SLS_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_SPLY_ITEM_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_SUPD_FROM_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_SUPD_TO_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_TST_IMPRESO_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.BIND_VND_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.CARR_ACCT_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.CARR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.CCY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.CPO_DTL_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.CPO_ORD_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.CTAC_PSN_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.CUST_ISS_PO_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.CUST_ISS_PO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.CUST_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.DB_EDI_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.DB_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.DB_PO_ORD_DTL_SUB_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.DB_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.DEST_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.DEST_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.DISP_PO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.DPD_SHIP_FROM_SO_NUM_LIST_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.DP_SHIP_FROM_SO_NUM_LIST_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.DS_PO_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.DS_PO_TP_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.EDI_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.EDI_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.EFF_FROM_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.EFF_THRU_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.EIP_RPT_RQST_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ENT_DEAL_NET_UNIT_PRC_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ENT_FUNC_NET_UNIT_PRC_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ENT_PO_DTL_DEAL_NET_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ENT_PO_DTL_FUNC_NET_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ERROR_MDSE_MASTER;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ERROR_ORIG_VND_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ERROR_SUPPLY_ITEM_NUMBER;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ETA_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ETD_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.EXCH_RATE;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.FROM_STK_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.FRT_CHRG_METH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.FRT_CHRG_TO_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.FRT_COND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.HYPHEN;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.INDENT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.KEY_INTERVAL_DAYS;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.LINE_SPASE_12;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.LINE_SPASE_18;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.LINE_SPASE_80;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.LINE_SPASE_9;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_FIELD_EDI_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_FIELD_ERR_DETAIL;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_FIELD_ERR_MSG;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_FIELD_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_FIELD_SUBMIT_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_FIELD_USER_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_FIELD_USER_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_FIELD_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_FIELD_VND_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_KEY_TO;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_TEMPLATE_ID_01;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_TEMPLATE_ID_03;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_TEMPLATE_ID_04;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAIL_TEMPLATE_ID_06;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MANDATORY_DTL_ITEMS;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MANDATORY_HDR_ITEMS;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAP_KEY_ACK_EDI_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAP_KEY_PO_ACK_HDR_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAP_KEY_PO_ACK_UPD_PROC_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAP_KEY_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAP_KEY_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MAP_KEY_VND_ISS_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MDSE_DESC_SHORT_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.MDSE_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.*;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPAM1169E;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPAM1171E;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPAM1172E;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPAM1178E;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPAM1236E;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPAM1265E;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPAM1281W;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPAM1282W;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPAM1288E;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPAM1321E;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPAM1323E;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPAM1530E;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPAM1644W;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPZM0080E;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPZM0081E;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NPZM0147E;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.NSBM0141E;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ORD_DTL_LAST_UPD_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ORD_HDR_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ORD_LAST_UPD_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ORD_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ORIG_DISP_PO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ORIG_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ORIG_ORD_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ORIG_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ORIG_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ORIG_TRD_PTNR_SKU_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.ORIG_VND_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ACK_DTL_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ACK_DTL_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ACK_DTL_WRK;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ACK_DTL_WRK_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ACK_ERR_WRK_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ACK_HDR_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ACK_HDR_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ACK_HDR_WRK;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ACK_HDR_WRK_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ACK_LINE_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ACK_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_APVL_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_APVL_PSN_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_APVL_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_APVL_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_DISP_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_DISP_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_INV_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_LINE_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_MATCH_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_MDSE_CMPSN_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ORD_CMNT_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ORD_DTL_CMNT_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ORD_DTL_SUB_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_ORD_SRC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_PRINT_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_QLFY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_SEND_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_SEND_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_SUBMT_PSN_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PO_SUBMT_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PRCH_GRP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PRCH_REQ_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PRCH_REQ_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PRCH_REQ_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PRNT_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PRNT_VND_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.PRO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.RQST_RCV_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.RQST_RCV_TM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.RQST_TECH_TOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.RQST_TOT_STD_COST_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.RTRN_SHIP_TO_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SELL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SEND_PO_EML_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SEND_PO_FAX_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SET_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_ACCT_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_ADDL_LOC_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_CNTY_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_CTRY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_CTY_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_FIRST_REF_CMNT_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_FRTH_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_LOC_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_POST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_PROV_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_SCD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_SCD_REF_CMNT_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_ST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHIP_TO_THIRD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHPG_PLN_DPLY_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHPG_PLN_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHPG_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SHPG_SVC_LVL_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SPLY_ITEM_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SRC_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SRC_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SUPD_RELN_STAGE_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.SVC_CONFIG_MSTR_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.THIS_MTH_FOB_COST_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.TO_STK_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.TRD_PTNR_SKU_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.TRSMT_METH_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.TRX_REF_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.TRX_REF_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.TRX_REF_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.TS_FORMAT_EDI_RCV_DATE_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.TS_FORMAT_FOR_MAIL;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_CHILD_BOM_PRC_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_CPO_CRAT_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_CPO_DTL_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_CPO_LINE_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_DROP_SHIP_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_ISS_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_OTBD_CARR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_OTBD_CARR_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_PMT_TERM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_PMT_TERM_DESC_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_PO_ACK_LINE_STS_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_PO_ACK_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_SELL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_SELL_TO_CUST_LOC_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_SHIP_TO_CUST_LOC_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant.VND_SO_SLP_NUM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.POTMsg;
import business.db.PO_ACK_DTLTMsg;
import business.db.PO_ACK_DTL_WRKTMsg;
import business.db.PO_ACK_ERR_WRKTMsg;
import business.db.PO_ACK_HDRTMsg;
import business.db.PO_ACK_HDR_WRKTMsg;
import business.db.PO_DTLTMsg;
import business.db.RCV_ASN_VNDTMsg;
import business.db.S21_PSNTMsg;
import business.db.SUPD_RELN_STAGETMsg;
import business.parts.NPZC004001PMsg;
import business.parts.NPZC104001PMsg;
import business.parts.NPZC106001PMsg;
import business.parts.NPZC110001PMsg;
import business.parts.NPZC115001PMsg;

import com.canon.cusa.s21.api.NLG.NLGC001001.NLGC001001;
import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.api.NPZ.NPZC104001.NPZC104001;
import com.canon.cusa.s21.api.NPZ.NPZC104001.constant.NPZC104001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC106001.NPZC106001;
import com.canon.cusa.s21.api.NPZ.NPZC110001.NPZC110001;
import com.canon.cusa.s21.api.NPZ.NPZC115001.NPZC115001;
import com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant;
import com.canon.cusa.s21.batch.NPA.NPAB100001.constant.NPAB100001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACK_EDI_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACK_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_PO_ACK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * PO ACK<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------

 * 01/17/2016   CITS            T.Hakodate      Created         WDS NA V4.0
 * 08/12/2016   CITS            T.Wada          Update          QC#13055
 * 09/27/2016   CITS            K.Ogino         Update          QC#8195
 * 10/24/2016   CITS            T.Hakodate      Update          QC#15523
 * 10/24/2016   CITS            T.Hakodate      Update          QC#8424
 * 11/16/2016   CITS            K.Ogino         Update          QC#15974
 * 12/21/2016   CITS            R.Shimamoto     Update          QC#16100
 * 04/20/2017   CITS            R.Shimamoto     Update          QC#18283
 * 07/04/2017   CITS            S.Endo          Update          QC#19487
 * 07/19/2017   CITS            K.Ogino         Update          QC#19664
 * 09/19/2017   CITS            K.Ogino         Update          QC#21108
 * 10/31/2017   CITS            K.Ogino         Update          QC#20737
 * 07/20/2018   CITS            K.Ogino         Update          QC#26455
 * 10/08/2018   CITS            T.Hakodate      Update          QC#28655
 * 10/20/2018   CITS            T.Hakodate      Update          QC#28834
 * 11/14/2018   CITS            K.Ogino         Update          QC#29171
 * 11/14/2018   CITS            M.Naito         Update          QC#29047
 * 11/20/2018   CITS            T.hakodate      Update          QC#28679
 * 12/05/2018   Fujitsu         S.Takami        Update          QC#29494
 * 12/17/2018   CITS            K.Ogino         Update          QC#29663
 * 01/11/2019   CITS            M.Naito         Update          QC#29694
 * 02/15/2019   CITS            M.Naito         Update          QC#30277
 * 02/18/2019   CITS            M.Naito         Update          QC#30276
 * 02/21/2019   CITS            M.Naito         Update          QC#30277
 * 05/15/2019   CITS            K.Ogino         Update          QC#31207
 * 05/23/2019   CITS            M.Naito         Update          QC#50340
 * 08/09/2019   CITS            M.Naito         Update          QC#51601
 * 08/20/2019   CITS            R.Shimamoto     Update          QC#52184
 * 09/07/2019   CITS            K.Ogino         Update          QC#52579
 * 11/15/2019   CITS            M.Naito         Update          QC#53502
 * 12/25/2019   CITS            R.Shimamoto     Update          QC#55163
 * 10/06/2020   CITS            H.Dimay         Update          QC#57795
 * 03/13/2023   Hitachi         TZ.Win          Update          QC#60960
 * 03/27/2023   Hitachi         TZ.Win          Update          QC#61269
 * 07/20/2023   Hitachi         S.Dong          Update          QC#61638
 * 01/23/2024   CITS            S.Okamoto       Update          QC#62198
 * </pre>
 */

public class NPAB100001 extends S21BatchMain {

    /** Log Level. */
    protected static final int D_MES_CODE = 1;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** success count */
    private int successCount = 0;

    /** error count */
    private int errorCount = 0;

    /** UserProfile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glCmpCd;

    /** Sales Date */
    private String slsDt;

    /** Interface ID */
    private String interfaceId;

    /** ErrorList */

    private List<PO_ACK_ERR_WRKTMsg> errorList = null;

    /** WarningList */
    private List<PO_ACK_ERR_WRKTMsg> warningList = null;

    /** createPoAckDtlList */
    private List<PO_ACK_DTLTMsg> createPoAckDtlList = null;

    /** PO_ORD_NUM:Digit */
    private int digitPoOrdNum = 0;

    /** PO_ORD_NUM:Digit */
    private int digitEdiPoOrdNum = 0;

    /** PO_ORD_DTL_LINE_NUM:Digit */
    private int digitPoOrdDtlLineNum = 0;

    /** EDI_PO_ORD_DTL_LINE_NUM:Digit */
    private int digitEdiPoOrdDtlLineNum = 0;

    /** ORIG_VND_MDSE_CD:Digit */
    private int digitOrigVndMdseCd = 0;

    /** interval days for dummy ETA_DT/ETD_DT */
    private int intervalDays = 0;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SQL access parts */
    private BigDecimal newPrice = BigDecimal.ZERO;

    /** isError */
    boolean isError = false;

    /** Create Material Parts Codes */
    private String[] materialParts = null;

    /**
     * Initialize method.
     */
    @Override
    protected void initRoutine() {

        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glCmpCd = this.profile.getGlobalCompanyCode();
        this.slsDt = ZYPDateUtil.getSalesDate(this.glCmpCd);
        this.interfaceId = getInterfaceID();
        this.errorList = new ArrayList<PO_ACK_ERR_WRKTMsg>();
        this.warningList = new ArrayList<PO_ACK_ERR_WRKTMsg>();
        this.createPoAckDtlList = new ArrayList<PO_ACK_DTLTMsg>();

        EZDItemAttribute getDigitPoOrdNum = new PO_ACK_DTL_WRKTMsg().getAttr(DB_PO_ORD_NUM);
        EZDItemAttribute getDigitEdiPoOrdNum = new PO_ACK_HDR_WRKTMsg().getAttr(DB_EDI_PO_ORD_NUM);
        EZDItemAttribute getDigitPoOrdDtlLineNum = new PO_ACK_DTL_WRKTMsg().getAttr(DB_PO_ORD_DTL_LINE_NUM);
        EZDItemAttribute getDigitPoOrdDtlSubLineNum = new PO_ACK_DTL_WRKTMsg().getAttr(DB_PO_ORD_DTL_SUB_LINE_NUM);
        this.digitPoOrdNum = getDigitPoOrdNum.getDigit();
        this.digitEdiPoOrdNum = getDigitEdiPoOrdNum.getDigit();
        this.digitPoOrdDtlLineNum = getDigitPoOrdDtlLineNum.getDigit();
        this.digitEdiPoOrdDtlLineNum = getDigitPoOrdDtlSubLineNum.getDigit();
        this.digitOrigVndMdseCd = getDigitPoOrdDtlSubLineNum.getDigit();
        BigDecimal intervalDaysBigDecimal = ZYPCodeDataUtil.getNumConstValue(KEY_INTERVAL_DAYS, this.glCmpCd);
        if (ZYPCommonFunc.hasValue(intervalDaysBigDecimal)) {
            this.intervalDays = ZYPCodeDataUtil.getNumConstValue(KEY_INTERVAL_DAYS, this.glCmpCd).intValue();
        }

        String constValue = ZYPCodeDataUtil.getVarCharConstValue(NPAB100001Constant.VAR_CONST_CREATE_MATERIAL_PARTS, getGlobalCompanyCode());
        if (constValue != null) {
            materialParts = constValue.split(",");
        }

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Main method.
     */
    @Override
    protected void mainRoutine() {

        // search PO_ACK_HDR_WRK
        List<Map<String, Object>> poAckHdrWrkList = searchPoAckHdrWrk();

        if (poAckHdrWrkList == null || poAckHdrWrkList.isEmpty()) {

            debug("No target PO Record In PO_ACK_HDR_WRK.");

            return;

        } else {

            POTMsg inPoTMsg = new POTMsg();
            POTMsg outPoTMsg = new POTMsg();

            // start Loop poAckHdrWrk by EDI_PO_ORD_NUM
            for (int i = 0; i < poAckHdrWrkList.size(); i++) {

                boolean ackHdrCreateError = false;

                Map<String, Object> poAckHdrWrk = poAckHdrWrkList.get(i);

                // Call Api [Get PO Line From EDI API(NPZC110001)]
                NPZC110001PMsg apiReturn = convertPoDataFromPoAckWrk((String) poAckHdrWrk.get(EDI_PO_ORD_NUM), (String) poAckHdrWrk.get(PO_ORD_NUM));

                String poOrdNum = apiReturn.poOrdNum_O.getValue();

                // get PO
                outPoTMsg = getPoData(inPoTMsg, poOrdNum);
                // QC#26455
                if (ZYPCommonFunc.hasValue(poOrdNum)) {
                    poAckHdrWrk.put("XREF_PO_ORD_NUM", poOrdNum);
                }

                // START 2023/03/13 TZ.Win [QC#60960, ADD]
                if (outPoTMsg == null) {
                    
                    updatePoAckHdrWrkWithProcessedWarning(poAckHdrWrk, poOrdNum, ackHdrCreateError, outPoTMsg, null);
                    commit();
                    this.successCount++;
                    continue;
                }
                // END 2023/03/13 TZ.Win [QC#60960, ADD]

                // PO Result Check
                if (isErrorPo(poAckHdrWrk, null, outPoTMsg)) {

                    // update PO_ACK_HDR_WRK
                    updatePoAckHdrWrk(poAckHdrWrk, poOrdNum, ackHdrCreateError, outPoTMsg, null);
                    this.errorCount++;
                    isError = true;
                }

                if (!isError) {

                    List<Map<String, Object>> poAckDtlWrkList = searchPoAckDtlWrk(poAckHdrWrk);

                    if (poAckDtlWrkList == null || poAckDtlWrkList.isEmpty()) {

                        sendErrorMail(poAckHdrWrk, outPoTMsg, null);

                        // update PO_ACK_HDR_WRK
                        updatePoAckHdrWrk(poAckHdrWrk, poOrdNum, ackHdrCreateError, outPoTMsg, null);
                        this.errorCount++;
                        isError = true;

                    } else {

                    	// QC#52184 Add Start
                    	String checkPoSts = null;
                    	String poApvlDt = null;
                        if (outPoTMsg != null && ZYPCommonFunc.hasValue(outPoTMsg.poStsCd)) {
                        	checkPoSts = outPoTMsg.poStsCd.getValue();
                        }
                        if (outPoTMsg != null && ZYPCommonFunc.hasValue(outPoTMsg.poApvlDt)) {
                        	poApvlDt = outPoTMsg.poApvlDt.getValue();
                        }
                        if (PO_STS.SAVED.equals(checkPoSts)) {
                        	sendErrorMail(poAckHdrWrk, outPoTMsg, NPZM0080E);
                        	updatePoAckHdrWrk(poAckHdrWrk, poOrdNum, ackHdrCreateError, outPoTMsg, null);
                        	this.errorCount++;
                            isError = true;
                        } else if (PO_STS.WAITING_FOR_APPROVAL.equals(checkPoSts)) {
                        	sendErrorMail(poAckHdrWrk, outPoTMsg, NPZM0081E);
                        	updatePoAckHdrWrk(poAckHdrWrk, poOrdNum, ackHdrCreateError, outPoTMsg, null);
                        	this.errorCount++;
                            isError = true;
                        } else if (poApvlDt == null) {
                        	sendErrorMail(poAckHdrWrk, outPoTMsg, NSBM0027E);
                        	updatePoAckHdrWrk(poAckHdrWrk, poOrdNum, ackHdrCreateError, outPoTMsg, null);
                        	this.errorCount++;
                            isError = true;
                        }
                        // QC#52184 Add End


                        if (!isError) {

                            boolean isErrorLine = false;
                            // START 2019/11/15 M.Naito [QC#53502,ADD]
                            boolean isSkipInvoicedAck = false;
                            // END 2019/11/15 M.Naito [QC#53502,ADD]

                            List<poAckBean> poAckBeanList = new ArrayList<poAckBean>();

                            // START LOOP poAckDtlWrkList
                            for (int j = 0; j < poAckDtlWrkList.size(); j++) {

                                poAckBean poAckBean = new poAckBean();

                                Map<String, Object> poAckDtlWrk = poAckDtlWrkList.get(j);

                                // MDSE_CD EXCHANGE
                                // *********************

                                // Get Mdse code from ASL
                                // START 2019/01/11 M.Naito [QC#29694,MOD]
//                                Map<String, Object> mdseCdFromAslMap = getMdseCdFromAsl(poAckHdrWrk, poAckDtlWrk, outPoTMsg.vndCd.getValue(), (String) poAckDtlWrk.get(MDSE_CD));
                                // QC#52579 Mod
                                List<Map<String, Object>> mdseCdFromAslList = getMdseCdFromAsl(poAckHdrWrk, poAckDtlWrk, outPoTMsg.vndCd.getValue(), (String) poAckDtlWrk.get(MDSE_CD), this.slsDt);

                                // Get Mdse name from MDSE
//                                if (mdseCdFromAslMap != null && !mdseCdFromAslMap.isEmpty()) {
                                if (mdseCdFromAslList != null && !mdseCdFromAslList.isEmpty()) {

                                    Map<String, Object> mdseCdFromAslMap = mdseCdFromAslList.get(0);
                                    String mdseNm = getMdseNm(poAckHdrWrk, poAckDtlWrk, (String) mdseCdFromAslMap.get(MDSE_CD));
                                    poAckBean.setChangMdseCd((String) mdseCdFromAslMap.get(MDSE_CD));
                                    poAckBean.setPrntVndCd((String) mdseCdFromAslMap.get(PRNT_VND_CD));
                                    poAckBean.setSplyItemNum((String) mdseCdFromAslMap.get(SPLY_ITEM_NUM));
                                    poAckBean.setVndCd((String) mdseCdFromAslMap.get(VND_CD));
                                    poAckBean.setOrigVndMdseCd((String) poAckDtlWrk.get(MDSE_CD));

                                    if (mdseNm != null) {
                                        poAckBean.setChangMdseNm(mdseNm);
                                        poAckBean.setPoAckDtlWrkPk((BigDecimal) poAckDtlWrk.get(PO_ACK_DTL_WRK_PK));
                                    }

                                    // PO_ACK_DTL_WRK mandatory check
                                    if (!isErrorLine && checkMandatoryDtlItem(poAckHdrWrk, poAckDtlWrk)) {
                                        isErrorLine = true;
                                    }

                                    if (VND_PO_ACK_STS.ITEM_CHANGE.equals(poAckDtlWrk.get(VND_PO_ACK_STS_CD))) {

                                        if (!isErrorLine && checkMandatoryCusaMdse(poAckHdrWrk, poAckDtlWrk)) {
                                            isErrorLine = true;
                                        }

                                        // mdse code FROM ASL
                                        if (!isErrorLine) {

                                            poAckBean.setOrigVndMdseCd((String) poAckDtlWrk.get(ORIG_VND_MDSE_CD));
                                            // QC#21108 Mod
//                                            Map<String, Object> csaMdseCdMap = getMdseCdFromAsl(poAckHdrWrk, poAckDtlWrk, outPoTMsg.vndCd.getValue(), (String) poAckDtlWrk.get(ORIG_VND_MDSE_CD));
//
//                                            if (csaMdseCdMap != null) {
//                                                checkMandatoryCsaMdse(poAckHdrWrk, poAckDtlWrk, outPoTMsg.vndCd.getValue());
//                                                checkOldMdseCd(poAckHdrWrk, poAckDtlWrk, (String) csaMdseCdMap.get(MDSE_CD));
//                                                poAckBean.setOrigVndMdseCd((String) csaMdseCdMap.get(SPLY_ITEM_NUM));
//                                            }
                                        }
                                    }

                                    if (!isErrorLine) {

                                        // Call Api [Get PO Line From
                                        // EDI
                                        // API(NPZC110001)]
                                        NPZC110001PMsg apiGetPoLineFromEdiParam = new NPZC110001PMsg();
                                        List<String> mdseCdList = new ArrayList<String>();
                                        for (Map<String, Object> mdseCdMap : mdseCdFromAslList) {
                                            mdseCdList.add((String) mdseCdMap.get(MDSE_CD));
                                        }
                                        if (mdseCdList != null && !mdseCdList.isEmpty()) {
                                            apiGetPoLineFromEdiParam = callApiGetPoLineFromEdi(poAckHdrWrk, poAckDtlWrk, apiGetPoLineFromEdiParam, mdseCdList);
//                                        // QC#26455
//                                        if (ZYPCommonFunc.hasValue((String) poAckDtlWrk.get(ORIG_VND_MDSE_CD))) {
//                                            apiGetPoLineFromEdiParam = callApiGetPoLineFromEdi(poAckHdrWrk, poAckDtlWrk, apiGetPoLineFromEdiParam, (String) poAckDtlWrk.get(ORIG_VND_MDSE_CD));
//                                        } else {
//                                            apiGetPoLineFromEdiParam = callApiGetPoLineFromEdi(poAckHdrWrk, poAckDtlWrk, apiGetPoLineFromEdiParam, poAckBean.getOrigVndMdseCd());
//                                        }

                                            if (ZYPCommonFunc.hasValue(apiGetPoLineFromEdiParam.poDetailOutList.no(0).mdseCd)) {
                                                poAckBean.setApiReturnedMdseCd(apiGetPoLineFromEdiParam.poDetailOutList.no(0).mdseCd.getValue());
                                            }

                                            if (S21ApiUtil.isXxMsgId(apiGetPoLineFromEdiParam)) {

                                                isErrorLine = true;

                                            }
                                        }

                                        if (!isErrorLine) {
                                            if (ZYPCommonFunc.hasValue(apiGetPoLineFromEdiParam.poDetailInList.no(0).mdseCd)) {
                                                mdseNm = getMdseNm(poAckHdrWrk, poAckDtlWrk, apiGetPoLineFromEdiParam.poDetailInList.no(0).mdseCd.getValue());
                                                poAckBean.setChangMdseCd(apiGetPoLineFromEdiParam.poDetailInList.no(0).mdseCd.getValue());
                                                if (mdseNm != null) {
                                                    poAckBean.setChangMdseNm(mdseNm);
                                                }
                                            }

                                            // search PO_DTL
                                            List<Map<String, Object>> poDtlList = searchPoDtl(poAckHdrWrk, poAckDtlWrk, apiGetPoLineFromEdiParam);

                                            if (!isErrorLine) {

                                                Map<String, Object> poDtlMap = poDtlList.get(0);
                                                poAckBean.setApiReturnedPoOrdNum((String) poDtlMap.get(PO_ORD_NUM));
                                                poAckBean.setApiReturnedPoOrdDtlLineNum((String) poDtlMap.get(PO_ORD_DTL_LINE_NUM));
                                            }

                                            // ord Qty check
                                            checkOrdQty(poAckHdrWrk, poAckDtlWrk, poDtlList, poAckDtlWrkList, apiGetPoLineFromEdiParam);

                                            if (!isErrorLine) {

                                                // search PoMdseCmpsnTpCd
                                                poAckBean.setPoMdseCmpsnTpCd(getPoMdseCmpsnTpCd(poAckHdrWrk, poAckDtlWrk, apiGetPoLineFromEdiParam));
                                            }
                                        }
                                    }

                                    poAckBeanList.add(poAckBean);

                                    isErrorLine = false;

                                }
                                // END 2019/01/11 M.Naito [QC#29694,MOD]
                            }

                            // END LOOP poAckDtlWrkList
                            if (!isError) {

                                // START 2019/11/15 M.Naito [QC#53502,MOD]
                                PO_ACK_HDRTMsg insertPoAckHdr = new PO_ACK_HDRTMsg();
                                if (chkInvoicedAckList(poAckDtlWrkList, poAckBeanList, poOrdNum)) {
                                    isSkipInvoicedAck = true;
                                } else {
                                    // Insert or Update PO_ACK_HDR
                                    insertPoAckHdr = insertAndUpdatePoAckHdr(poAckHdrWrk, outPoTMsg.vndCd.getValue(), outPoTMsg);

                                    if (insertPoAckHdr == null) {
                                        ackHdrCreateError = true;
                                        isErrorLine = true;
                                    }

                                    if (PO_STS.VALIDATED.equals(outPoTMsg.poStsCd.getValue()) || PO_STS.PO_CONFIRMED.equals(outPoTMsg.poStsCd.getValue()) || PO_STS.PO_ERROR.equals(outPoTMsg.poStsCd.getValue())) {

                                        // Call Api PO VND_ISS_ORD_NUM
                                        if (!updatePo(poAckHdrWrk, outPoTMsg)) {
                                            isError = true;
                                        }

                                    }
                                }
                                // END 2019/11/15 M.Naito [QC#53502,MOD]

                                if (!isError) {

                                    for (int k = 0; k < poAckDtlWrkList.size(); k++) {

                                        poAckBean poAckBeanWk = poAckBeanList.get(k);

                                        PO_DTLTMsg outPoDtlTMsg = new PO_DTLTMsg();

                                        Map<String, Object> poAckDtlWrk = poAckDtlWrkList.get(k);

                                        // get PO_DTL
                                        outPoDtlTMsg = getPoDtlData(poOrdNum, poAckBeanWk.getApiReturnedPoOrdDtlLineNum());
                                        // QC#26455
                                        if (outPoDtlTMsg == null) {
                                            isError = true;
                                            PO_ACK_ERR_WRKTMsg poAckError = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1236E, new String[] {poOrdNum + "," + poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM)});

                                            errorList.add(poAckError);

                                            S21InfoLogOutput.println(NPAM1236E, new String[] {poOrdNum + "," + poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM)});

                                            continue;
                                        }
                                        String poStsCd = outPoDtlTMsg.poStsCd.getValue();

                                        // START 2019/11/15 M.Naito [QC#53502,MOD]
//                                        if (!PO_STS.VALIDATED.equals(poStsCd) && !PO_STS.PO_CONFIRMED.equals(poStsCd) && !PO_STS.PO_ERROR.equals(poStsCd)) {
                                        if (!PO_STS.VALIDATED.equals(poStsCd) && !PO_STS.PO_CONFIRMED.equals(poStsCd) && !PO_STS.PO_ERROR.equals(poStsCd) && !PO_STS.CLOSED.equals(poStsCd)) {
                                            // QC#29663
//                                            if (insertAndUpdatePoAckDtl(insertPoAckHdr, poAckHdrWrk, poAckDtlWrk, poAckBeanWk, outPoTMsg)) {
                                            if ( insertPoAckHdr != null && insertAndUpdatePoAckDtl(insertPoAckHdr, poAckHdrWrk, poAckDtlWrk, poAckBeanWk, outPoTMsg)) {
                                                isError = true;
                                                continue;
                                            }

                                            continue;
                                        // END 2019/11/15 M.Naito [QC#53502,MOD]

                                        } else {

                                            String mdseCd = null;

                                            if (ZYPCommonFunc.hasValue(poAckBeanWk.getApiReturnedMdseCd())) {

                                                mdseCd = poAckBeanWk.getApiReturnedMdseCd();

                                            } else {

                                                mdseCd = (String) poAckDtlWrk.get(MDSE_CD);
                                            }

                                            // NPZC004001
                                            // ********************************************************************
                                            // START 2019/11/15 M.Naito [QC#53502,MOD]
//                                            if (!isError && callPoUpdateApi(poAckHdrWrk, poAckDtlWrk, mdseCd, outPoDtlTMsg)) {
                                            if (!isError && (!PO_STS.CLOSED.equals(poStsCd) && callPoUpdateApi(poAckHdrWrk, poAckDtlWrk, mdseCd, outPoDtlTMsg))) {
                                            // END 2019/11/15 M.Naito [QC#53502,MOD]
                                                isError = true;

                                            }

                                            // QC#8424
                                            // NPZC104001
                                            // ********************************************************************
                                            // if (!isError &&
                                            // callPoCreateApi(poAckHdrWrk,
                                            // poAckDtlWrk, mdseCd,
                                            // outPoDtlTMsg)) {
                                            // isError = true;
                                            // }

                                            // Promotion Item Check.
                                            BigDecimal unitPriceAmtProm = checkPrmoItem(poAckHdrWrk, poAckDtlWrk);

                                            BigDecimal poDtlUnitPrcAmt = BigDecimal.ZERO;
                                            poDtlUnitPrcAmt = outPoDtlTMsg.entFuncNetUnitPrcAmt.getValue();

                                            newPrice = poDtlUnitPrcAmt;

                                            if (unitPriceAmtProm != null) {

                                                if (poDtlUnitPrcAmt.compareTo(unitPriceAmtProm) != 0) {
                                                    newPrice = unitPriceAmtProm;
                                                    // START 2019/05/23 M.Naito [QC#50340,ADD]
                                                    PO_ACK_ERR_WRKTMsg poAckError = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1644W);
                                                    warningList.add(poAckError);
                                                    // END 2019/05/23 M.Naito [QC#50340,ADD]
                                                }

                                            } else {

                                                BigDecimal poAckDtlWrkUnitPrice = BigDecimal.ZERO;
                                                poAckDtlWrkUnitPrice = (BigDecimal) poAckDtlWrk.get(THIS_MTH_FOB_COST_AMT);

                                                // QC#13055
                                                if (ZYPCommonFunc.hasValue(poAckDtlWrkUnitPrice)) {
                                                    // QC#55163 2019/12/25 MOD START
                                                    //if (poDtlUnitPrcAmt.compareTo(poAckDtlWrkUnitPrice) != 0) {
                                                    if (poDtlUnitPrcAmt.compareTo(poAckDtlWrkUnitPrice) != 0 && !PO_ACK_LINE_STS.CANCEL.equals((String) poAckDtlWrk.get(PO_ACK_LINE_STS_CD))) {
                                                    // QC#55163 2019/12/25 MOD END
                                                        newPrice = poAckDtlWrkUnitPrice;
                                                        // START 2019/05/23 M.Naito [QC#50340,ADD]
                                                        PO_ACK_ERR_WRKTMsg poAckError = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1644W);
                                                        warningList.add(poAckError);
                                                        // END 2019/05/23 M.Naito [QC#50340,ADD]
                                                    }
                                                }
                                            }

                                            // QC#8424
                                            // NPZC104001
                                            // ********************************************************************
                                            // if
                                            // (poDtlUnitPrcAmt.compareTo(newPrice)
                                            // != 0) {
                                            // QC#52579 Start
                                            boolean isAslMulti = false;
                                            List<Map<String, Object>> mdseCdFromAslList = getMdseCdFromAsl(poAckHdrWrk, poAckDtlWrk, outPoTMsg.vndCd.getValue(), (String) poAckDtlWrk.get(MDSE_CD), null);

                                            if (mdseCdFromAslList != null && !mdseCdFromAslList.isEmpty() && mdseCdFromAslList.size() > 1) {
                                                isAslMulti = true;
                                            }

                                            if (!isError && callPoCreateApi(poAckHdrWrk, poAckDtlWrk, mdseCd, outPoDtlTMsg, isAslMulti)) {
                                                isError = true;
                                            }
                                            // QC#52579 End
                                            // }

                                            // QC#18283 Add.
                                            String supyItemNum = getNewAslMdseCd(poAckDtlWrk);

                                            if (supyItemNum == null && (poAckDtlWrk.get(ORIG_VND_MDSE_CD) != null || !"".equals(poAckDtlWrk.get(ORIG_VND_MDSE_CD)))) {
                                                // Check mdse Type
                                                // QC#8195
                                                supyItemNum = getSplyItemNumFromAsl((String) poAckDtlWrk.get(ORIG_VND_MDSE_CD), outPoTMsg.vndCd.getValue());

                                            } else if (supyItemNum == null && (poAckDtlWrk.get(ORIG_VND_MDSE_CD) == null || "".equals(poAckDtlWrk.get(ORIG_VND_MDSE_CD)))) {

                                                isError = true;
                                                PO_ACK_ERR_WRKTMsg poAckError = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NSBM0141E, new String[] {PO_ACK_DTL_WRK, ORIG_VND_MDSE_CD });

                                                errorList.add(poAckError);

                                                S21InfoLogOutput.println(NSBM0141E, new String[] {PO_ACK_DTL_WRK, ORIG_VND_MDSE_CD });

                                                continue;

                                            }


                                            if (materialParts == null || !Arrays.asList(materialParts).contains(supyItemNum)) {
                                                // QC#16100 Mod.
//                                                if (!isError && !callAslUpdateApi(poAckHdrWrk, poAckDtlWrk, null, outPoDtlTMsg, outPoTMsg, poAckBeanWk)) {
                                                if (!isError && !callAslUpdateApi(poAckHdrWrk, poAckDtlWrk, mdseCd, outPoDtlTMsg, outPoTMsg, poAckBeanWk)) {
                                                    isError = true;
                                                }
                                                if (!isError) {

                                                    // Case Item
                                                    // Change
                                                    if (VND_PO_ACK_STS.ITEM_CHANGE.equals((String) poAckDtlWrk.get(VND_PO_ACK_STS_CD))) {

                                                        // Supercede
                                                        // Get
                                                        // OrigMdseCd
                                                        String origMdseCd = poAckBeanWk.getOrigVndMdseCd();

                                                        // Get
                                                        // newMdseCd
                                                        String newMdseCd = poAckBeanWk.getChangMdseCd();

                                                        if (!origMdseCd.equals(newMdseCd)) {

                                                            if (!checkAndUpdateSupercede(poAckHdrWrk, poAckDtlWrk, origMdseCd, newMdseCd, outPoTMsg)) {
                                                                isError = true;
                                                            }

                                                        }

                                                    }
                                                }
                                            }

                                        }

                                        // START 2019/11/15 M.Naito [QC#53502,ADD]
                                        // If ACK Status is Invoiced, it is not creating PO_ACK_DTL
                                        if (isSkipInvoicedAck || (VND_PO_ACK_STS.INVOICED.equals((String) poAckDtlWrk.get(VND_PO_ACK_STS_CD)) && chkInvoicedAck(poOrdNum, poAckBeanWk.getApiReturnedPoOrdDtlLineNum()))) {
                                            continue;
                                        }
                                        // END 2019/11/15 M.Naito [QC#53502,ADD]
                                        // QC#20735
                                        if (!isError) {
                                            initializeItfcErrMsgTxt(poOrdNum, poAckBeanList.get(k).getApiReturnedPoOrdDtlLineNum());
                                            // QC#29171
                                            if (insertAndUpdatePoAckDtl(insertPoAckHdr, poAckHdrWrk, poAckDtlWrk, poAckBeanWk, outPoTMsg)) {
                                                isError = true;
                                                continue;
                                            }
                                        }

                                        // QC#20737 Add
                                        // Update Parent Set Item VND_PO_ACK_STS_CD=AR
                                        if (ZYPCommonFunc.hasValue(outPoDtlTMsg.setPoOrdDtlLineNum)) {
                                            List<Map<String, Object>> compList = getSetCompAckStsCd(poOrdNum, outPoDtlTMsg.setPoOrdDtlLineNum.getValue());
                                            if (compList != null) {
                                                Map<String, Object> compMap = compList.get(0);

                                                PO_ACK_DTLTMsg poAckDtlTMsg = new PO_ACK_DTLTMsg();
                                                PO_ACK_DTLTMsg parentPoAckDtlTMsg = new PO_ACK_DTLTMsg();

                                                ZYPEZDItemValueSetter.setValue(poAckDtlTMsg.glblCmpyCd, this.glCmpCd);
                                                ZYPEZDItemValueSetter.setValue(poAckDtlTMsg.poAckDtlPk, (BigDecimal) compMap.get(PO_ACK_DTL_PK));

                                                poAckDtlTMsg = (PO_ACK_DTLTMsg) EZDTBLAccessor.findByKey(poAckDtlTMsg);
                                                if (poAckDtlTMsg != null) {
                                                    EZDMsg.copy(poAckDtlTMsg, null, parentPoAckDtlTMsg, null);
                                                    ZYPEZDItemValueSetter.setValue(parentPoAckDtlTMsg.poAckDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_DTL_SQ));
                                                    ZYPEZDItemValueSetter.setValue(parentPoAckDtlTMsg.poAckHdrPk, insertPoAckHdr.poAckHdrPk);
                                                    ZYPEZDItemValueSetter.setValue(parentPoAckDtlTMsg.vndPoAckStsCd, (String) compMap.get(VND_PO_ACK_STS_CD));
                                                    if (VND_PO_ACK_STS.RELEASED_FOR_SHIPMENT.equals(parentPoAckDtlTMsg.vndPoAckStsCd.getValue())) {
                                                        ZYPEZDItemValueSetter.setValue(parentPoAckDtlTMsg.poAckLineStsCd, PO_ACK_LINE_STS.SO_PRINTED);
                                                    } else if (VND_PO_ACK_STS.SHIPPED.equals(parentPoAckDtlTMsg.vndPoAckStsCd.getValue())) {
                                                        ZYPEZDItemValueSetter.setValue(parentPoAckDtlTMsg.poAckLineStsCd, PO_ACK_LINE_STS.SHIPPED);
                                                    } else {
                                                        continue;
                                                    }
                                                    ZYPEZDItemValueSetter.setValue(parentPoAckDtlTMsg.vndPoAckLineStsTxt, ZYPCodeDataUtil.getName(PO_ACK_LINE_STS.class, this.glCmpCd, parentPoAckDtlTMsg.poAckLineStsCd.getValue()));

                                                    // Parent Set Item AR Carete
                                                    EZDTBLAccessor.insert(parentPoAckDtlTMsg);
                                                    // Old Parent Set Item Latest flag Update
                                                    poAckDtlTMsg.poAckDtlLtstFlg.setValue(ZYPConstant.FLG_OFF_N);
                                                    EZDTBLAccessor.update(poAckDtlTMsg);
                                                }
                                            }
                                        }
                                        // QC#20737 End
                                    }
                                }

                            }

                            // logical delete PO_ACK_ERR_WRK
                            logicalDeletePoAckErrWrk(poAckHdrWrk);

                            // QC#28679 mod start
                            // create PO_ACK_ERR_WRK
                            // errorProcess(outPoTMsg, poAckHdrWrk);
                            // warningProcess(outPoTMsg, poAckHdrWrk);
                            // QC#28679 mod end

                            // update PO_ACK_HDR_WRK
                            if (!updatePoAckHdrWrk(poAckHdrWrk, poOrdNum, ackHdrCreateError, outPoTMsg, poAckBeanList)) {
                                continue;
                            }

                            // update PO_ACK_DTL_WRK
                            if (!updatePoAckDtlWrk(poAckHdrWrk, outPoTMsg, poAckDtlWrkList, poAckBeanList)) {
                                continue;
                            }

                            // QC#28679 mod start
                            // create PO_ACK_ERR_WRK
                            errorProcess(outPoTMsg, poAckHdrWrk);
                            warningProcess(outPoTMsg, poAckHdrWrk);
                            // QC#28679 mod end

                            if (!isError) {
                                this.successCount++;
                            }

                        }
                    }
                }

                // end roop poAckHdrWrk
                commit();
                clearList();
                isError = false;

            }
        }
    }

    /**
     * updatePoAckDtlWrk
     * @param poAckDtlWrkList
     * @return
     */
    private boolean updatePoAckDtlWrk(Map<String, Object> poAckHdrWrk, POTMsg outPoTMsg, List<Map<String, Object>> poAckDtlWrkList, List<poAckBean> poAckBeanList) {

        PO_ACK_DTL_WRKTMsg outMsg = new PO_ACK_DTL_WRKTMsg();

        // QC#15974
        boolean lineErr = false;
        if (poAckDtlWrkList.size() != poAckBeanList.size()) {
            lineErr = true;
        }

        for (int i = 0; i < poAckDtlWrkList.size(); i++) {

            Map<String, Object> poAckDtlWrk = poAckDtlWrkList.get(i);

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glCmpCd);
            ZYPEZDItemValueSetter.setValue(outMsg.poAckDtlWrkPk, (BigDecimal) poAckDtlWrk.get(PO_ACK_DTL_WRK_PK));
            ZYPEZDItemValueSetter.setValue(outMsg.poOrdDtlLineNum, (String) poAckDtlWrk.get(PO_ORD_DTL_LINE_NUM));

            outMsg = (PO_ACK_DTL_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(outMsg);

            if (!lineErr && !poAckBeanList.isEmpty()) {

                poAckBean poAckBean = poAckBeanList.get(i);
                ZYPEZDItemValueSetter.setValue(outMsg.poOrdNum, poAckBean.getApiReturnedPoOrdNum());
                ZYPEZDItemValueSetter.setValue(outMsg.poOrdDtlLineNum, poAckBean.getApiReturnedPoOrdDtlLineNum());
            }

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {

                return false;

            }

            EZDTBLAccessor.update(outMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {

                StringBuilder sb = new StringBuilder();
                sb.append("TABLE:PO_ACK_DTL_WRK_TBL EDI_PO_ORD_NUM:").append(outMsg.ediPoOrdNum.getValue());
                sb.append(" EDI_PO_ORD_DTL_LINE_NUM:").append(outMsg.ediPoOrdDtlLineNum.getValue());
                sb.append(" PO_ACK_DTL_WRK_PK:").append(outMsg.poAckDtlWrkPk.getValue());

                S21InfoLogOutput.println(NPAM1171E, new String[] {sb.toString() });

                String msg = S21MessageFunc.clspGetMessage(NPAM1171E, new String[] {sb.toString() });

                rollback();

                sendMail(MAIL_TEMPLATE_ID_04, poAckHdrWrk, msg, outPoTMsg);

                return false;
            }
        }

        return true;
    }

    /**
     * setAckEdiProcStsCd
     * @return String
     */
    private String setAckEdiProcStsCd() {

        if (!this.errorList.isEmpty()) {

            return ACK_EDI_PROC_STS.ERROR;

        } else if (!this.warningList.isEmpty()) {

            return ACK_EDI_PROC_STS.PROCESSED_WITH_WARNING;
        }

        return ACK_EDI_PROC_STS.PROCESSED;
    }

    /**
     * updatePoAckHdrWrk
     * @param poAckHdrWrk
     * @param poOrdNum
     * @param flg
     * @return
     */
    private boolean updatePoAckHdrWrk(Map<String, Object> poAckHdrWrk, String poOrdNum, boolean flg, POTMsg outPoTMsg, List<poAckBean> poAckBeanList) {

        PO_ACK_HDR_WRKTMsg outMsg = new PO_ACK_HDR_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(outMsg.poAckHdrWrkPk, (BigDecimal) poAckHdrWrk.get(PO_ACK_HDR_WRK_PK));

        outMsg = (PO_ACK_HDR_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(outMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {

            return false;

        }

        ZYPEZDItemValueSetter.setValue(outMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(outMsg.poAckUpdProcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(outMsg.ackEdiProcStsCd, setAckEdiProcStsCd());
        ZYPEZDItemValueSetter.setValue(outMsg.poAckHdrPk, (BigDecimal) poAckHdrWrk.get(PO_ACK_HDR_PK));

        if (outPoTMsg != null) {
            ZYPEZDItemValueSetter.setValue(outMsg.vndCd, outPoTMsg.vndCd);
        }

        poAckBean poAckBean = new poAckBean();

        if (poAckBeanList != null && !poAckBeanList.isEmpty()) {

            poAckBean = poAckBeanList.get(0);
            ZYPEZDItemValueSetter.setValue(outMsg.poOrdNum, poAckBean.getApiReturnedPoOrdNum());
        }

        EZDTBLAccessor.updateSelectionField(outMsg, new String[] {MAP_KEY_PO_ORD_NUM, MAP_KEY_PO_ACK_UPD_PROC_FLG, MAP_KEY_ACK_EDI_PROC_STS_CD, MAP_KEY_PO_ACK_HDR_PK, MAP_KEY_VND_CD });

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();

            sb.append("TABLE:PO_ACK_HDR_WRK_TBL EDI_PO_ORD_NUM:").append(outMsg.ediPoOrdNum.getValue());
            sb.append(" PO_ACK_HDR_WRK_PK:").append(outMsg.poAckHdrWrkPk.getValue());

            S21InfoLogOutput.println(NPAM1171E, new String[] {sb.toString() });

            String msg = S21MessageFunc.clspGetMessage(NPAM1171E, new String[] {sb.toString() });

            rollback();

            sendMail(MAIL_TEMPLATE_ID_04, poAckHdrWrk, msg, outPoTMsg);

            return false;

        }

        return true;
    }

    // START 2023/03/13 TZ.Win [QC#60960, ADD]
    /**
     * updatePoAckHdrWrkWithProcessedWarning
     * @param poAckHdrWrk
     * @param poOrdNum
     * @param flg
     * @return
     */
    private boolean updatePoAckHdrWrkWithProcessedWarning(Map<String, Object> poAckHdrWrk, String poOrdNum, boolean flg, POTMsg outPoTMsg, List<poAckBean> poAckBeanList) {

        PO_ACK_HDR_WRKTMsg outMsg = new PO_ACK_HDR_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(outMsg.poAckHdrWrkPk, (BigDecimal) poAckHdrWrk.get(PO_ACK_HDR_WRK_PK));

        outMsg = (PO_ACK_HDR_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(outMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {

            return false;

        }

        ZYPEZDItemValueSetter.setValue(outMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(outMsg.poAckUpdProcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(outMsg.ackEdiProcStsCd, ACK_EDI_PROC_STS.PROCESSED_WITH_WARNING);
        ZYPEZDItemValueSetter.setValue(outMsg.poAckHdrPk, (BigDecimal) poAckHdrWrk.get(PO_ACK_HDR_PK));

        if (outPoTMsg != null) {
            ZYPEZDItemValueSetter.setValue(outMsg.vndCd, outPoTMsg.vndCd);
        }

        poAckBean poAckBean = new poAckBean();

        if (poAckBeanList != null && !poAckBeanList.isEmpty()) {

            poAckBean = poAckBeanList.get(0);
            ZYPEZDItemValueSetter.setValue(outMsg.poOrdNum, poAckBean.getApiReturnedPoOrdNum());
        }

        EZDTBLAccessor.updateSelectionField(outMsg, new String[] {MAP_KEY_PO_ORD_NUM, MAP_KEY_PO_ACK_UPD_PROC_FLG, MAP_KEY_ACK_EDI_PROC_STS_CD, MAP_KEY_PO_ACK_HDR_PK, MAP_KEY_VND_CD });

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();

            sb.append("TABLE:PO_ACK_HDR_WRK_TBL EDI_PO_ORD_NUM:").append(outMsg.ediPoOrdNum.getValue());
            sb.append(" PO_ACK_HDR_WRK_PK:").append(outMsg.poAckHdrWrkPk.getValue());

            S21InfoLogOutput.println(NPAM1171E, new String[] {sb.toString() });

            String msg = S21MessageFunc.clspGetMessage(NPAM1171E, new String[] {sb.toString() });

            rollback();

            sendMail(MAIL_TEMPLATE_ID_04, poAckHdrWrk, msg, outPoTMsg);

            return false;

        }

        return true;
    }
    // END 2023/03/13 TZ.Win [QC#60960, ADD]

    // 2018/12/05 QC#29494 Del Start
//    /**
//     * @param errList
//     * @return
//     */
//    private String getErrMsg(List<PO_ACK_ERR_WRKTMsg> errList) {
//
//        StringBuilder buf = new StringBuilder();
//        String msg = null;
//
//        for (PO_ACK_ERR_WRKTMsg tMsg : errList) {
//
//            msg = tMsg.batErrMsgTxt.getValue();
//
//            if (buf.indexOf(msg) > -1) {
//                continue;
//            }
//
//            if (buf.length() > 0) {
//                buf.append(S21MailTemplate.ML_LINE_SEPARATOR);
//                buf.append(INDENT);
//            }
//
//            buf.append(tMsg.batErrMsgTxt.getValue());
//        }
//
//        return buf.toString();
//    }
    // 2018/12/05 QC#29494 Del End

    /**
     * @param errList
     */
    private void createPoAckErrWrk(List<PO_ACK_ERR_WRKTMsg> errList) {

        for (PO_ACK_ERR_WRKTMsg inTMsg : errList) {

            ZYPEZDItemValueSetter.setValue(inTMsg.poAckErrWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PO_ACK_ERR_WRK_SQ));

            EZDTBLAccessor.insert(inTMsg);

        }

    }

    /**
     * errorProcess
     * @param outPoTMsg
     * @param poAckHdrWrkTMsg
     */
    private void errorProcess(POTMsg outPoTMsg, Map<String, Object> poAckHdrWrk) {

        if (!this.errorList.isEmpty()) {

            // 2018/12/05 QC#29494 Mod Start
//            sendMail(MAIL_TEMPLATE_ID_03, poAckHdrWrk, getErrMsg(this.errorList), outPoTMsg);
            sendMail(MAIL_TEMPLATE_ID_03, poAckHdrWrk, "", outPoTMsg);
            // 2018/12/05 QC#29494 Mod End

            createPoAckErrWrk(this.errorList);

            this.errorCount++;
        }
    }

    /**
     * warningProcess
     * @param outPoTMsg
     * @param poAckHdrWrkTMsg
     */
    private void warningProcess(POTMsg outPoTMsg, Map<String, Object> poAckHdrWrk) {

        if (!this.warningList.isEmpty()) {

            // QC#28679 mod start
            sendWarningMail(MAIL_TEMPLATE_ID_06);
            // QC#28679 mod end

            createPoAckErrWrk(this.warningList);
        }
    }

    /**
     * @param poAckHdrWrk
     * @return
     */
    private boolean logicalDeletePoAckErrWrk(Map<String, Object> poAckHdrWrk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_PO_ACK_HDR_WRK_PK, poAckHdrWrk.get(PO_ACK_HDR_WRK_PK));

        List<Map<String, Object>> poAckErrWrkList = ssmBatchClient.queryObjectList("getPoAckErrWrkList", ssmParam);

        for (int i = 0; i < poAckErrWrkList.size(); i++) {

            Map<String, Object> poAckErrWrkMap = poAckErrWrkList.get(i);

            PO_ACK_ERR_WRKTMsg updateMsg = new PO_ACK_ERR_WRKTMsg();

            ZYPEZDItemValueSetter.setValue(updateMsg.glblCmpyCd, this.glCmpCd);
            ZYPEZDItemValueSetter.setValue(updateMsg.poAckErrWrkPk, (BigDecimal) poAckErrWrkMap.get(PO_ACK_ERR_WRK_PK));

            updateMsg = (PO_ACK_ERR_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(updateMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateMsg.getReturnCode())) {

                return false;

            }

            EZDTBLAccessor.logicalRemove(updateMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateMsg.getReturnCode())) {

                return false;
            }

        }

        return true;

    }

    /**
     * checkAndUpdateSupercede
     * @param poAckHdrWrk
     * @param poAckDtlWrk
     * @param origMdseCd
     * @param newMdseCd
     */
    private boolean checkAndUpdateSupercede(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, String origMdseCd, String newMdseCd, POTMsg outPoTMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_SUPD_FROM_MDSE_CD, origMdseCd);
        ssmParam.put(BIND_SUPD_TO_MDSE_CD, newMdseCd);

        String checkResultStage = null;
        String checkResultReln = null;

        checkResultStage = (String) ssmBatchClient.queryObject("checkSupercedeStage", ssmParam);

        if (!ZYPCommonFunc.hasValue(checkResultStage)) {
            checkResultReln = (String) ssmBatchClient.queryObject("checkSupercedeReln", ssmParam);
        }

        if (!ZYPCommonFunc.hasValue(checkResultStage) && !ZYPCommonFunc.hasValue(checkResultReln)) {

            SUPD_RELN_STAGETMsg inMsg = new SUPD_RELN_STAGETMsg();

            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glCmpCd);
            ZYPEZDItemValueSetter.setValue(inMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
            ZYPEZDItemValueSetter.setValue(inMsg.supdFromMdseCd, origMdseCd);
            ZYPEZDItemValueSetter.setValue(inMsg.supdToMdseCd, newMdseCd);
            ZYPEZDItemValueSetter.setValue(inMsg.supdRelnStagePk, ZYPOracleSeqAccessor.getNumberBigDecimal(SUPD_RELN_STAGE_SQ));
            ZYPEZDItemValueSetter.setValue(inMsg.submtFlg, ZYPConstant.FLG_OFF_N);

            EZDTBLAccessor.create(inMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {

                StringBuilder sb = new StringBuilder();

                sb.append("TABLE:SUPD_RELN_STAGE: ").append((String) poAckHdrWrk.get(PO_ORD_NUM));
                sb.append("CUST_ISS_PO_NUM = ").append((String) poAckDtlWrk.get(EDI_PO_ORD_NUM));
                sb.append("PO_ORD_DTL_LINE_NUM = ");
                String msg = null;
                S21InfoLogOutput.println(NPAM1172E, new String[] {sb.toString() });
                msg = S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {sb.toString() });

                rollback();

                sendMail(MAIL_TEMPLATE_ID_04, poAckHdrWrk, msg, outPoTMsg);

                return false;

            }
        }
        return true;
    }

    /**
     * callAslUpdateApi
     * @param poAckHdrWrk
     * @param poAckDtlWrk
     * @param mdseCd
     * @param outPoDtlTMsg
     * @return
     */
    private boolean callAslUpdateApi(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, String mdseCd, PO_DTLTMsg outPoDtlTMsg, POTMsg outPoTMsg, poAckBean poAckBeanWk) {

        //QC#62198 2024/1/23 Add Start
        if (!ZYPCommonFunc.hasValue((BigDecimal) poAckDtlWrk.get(THIS_MTH_FOB_COST_AMT))) {
            return true;
        }
        //QC#62198 2024/1/23 Add End

        // QC#55163 2019/12/25 ADD START
        if (PO_ACK_LINE_STS.CANCEL.equals((String) poAckDtlWrk.get(PO_ACK_LINE_STS_CD))) {
            return true;
        }
        // QC#55163 2019/12/25 ADD END

        // START QC#57795 2020/10/06 H.Dimay [ADD]
        if (isVendorExcludeAslUpdate(outPoTMsg)) {
            return true;
        }
        // END QC#57795 2020/10/06 H.Dimay [ADD]

        NPZC115001PMsg pMsg = new NPZC115001PMsg();

        pMsg.xxAslDtlList.setValidCount(1);

        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC115001Constant.COST_UPDATE_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, poAckBeanWk.getPrntVndCd());
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).splyItemNum, poAckBeanWk.getSplyItemNum());
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).vndCd, poAckBeanWk.getVndCd());
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).unitPrcAmt, this.newPrice);
        // ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).effFromDt,
        // this.slsDt);

        if (PO_MDSE_CMPSN_TP.REGULAR.equals(poAckBeanWk.getPoMdseCmpsnTpCd())) {
            // QC#16100 Mod.
//            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).xxValUpdFlg, ZYPConstant.FLG_ON_Y);
            if (updateCheckMdseCost(mdseCd, (BigDecimal) poAckDtlWrk.get(THIS_MTH_FOB_COST_AMT))) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).xxValUpdFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).xxValUpdFlg, ZYPConstant.FLG_OFF_N);
            }

        } else if (PO_MDSE_CMPSN_TP.PARENT.equals(poAckBeanWk.getPoMdseCmpsnTpCd())) {

            ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).xxValUpdFlg, ZYPConstant.FLG_OFF_N);

        } else {

            // QC#16009
            return true;
        }

        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).mdseCstUpdGrpTxt, " PO COST UPDATE " + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).mdseCstUpdDescTxt, " PO COST UPDATE " + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).mdseCstUpdRefTxt, "Purchase Order");
        ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(0).mdseCstUpdRefId, outPoTMsg.poOrdNum.getValue());

        // Call PO Update API
        new NPZC115001().execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);

            for (String xxMsgId : xxMsgIdList) {

                S21InfoLogOutput.println(xxMsgId);

                if (xxMsgId.endsWith("E")) {
                    StringBuilder subMsg = new StringBuilder("API:ASL Update: ");
                    subMsg.append("EDI_PO_ORD_NUM=").append((String) poAckDtlWrk.get(EDI_PO_ORD_NUM));
                    subMsg.append("MDSE_CD = ").append(poAckBeanWk.getSplyItemNum());
                    subMsg.append("ERROR = ").append(xxMsgId);
                    PO_ACK_ERR_WRKTMsg poAckError = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1178E, new String[] {subMsg.toString() });
                    errorList.add(poAckError);
                    isError = true;
                    S21InfoLogOutput.println(NPAM1178E, new String[] {subMsg.toString() });
                } else {
                    StringBuilder subMsg = new StringBuilder("API:ASL Update: ");
                    subMsg.append("EDI_PO_ORD_NUM=").append((String) poAckDtlWrk.get(EDI_PO_ORD_NUM));
                    subMsg.append("MDSE_CD = ").append(poAckBeanWk.getSplyItemNum());
                    subMsg.append("WARNING = ").append(xxMsgId);
                    PO_ACK_ERR_WRKTMsg poAckError = setPoAckErr(poAckHdrWrk, poAckDtlWrk, xxMsgId, new String[] {subMsg.toString() });
                    warningList.add(poAckError);
                }
            }

            // QC#28834 mod start
            // return false;
            // QC#28834 mod end
        }

        // there no err return true
        return true;

    }

    /**
     * isErrorPo
     * @param poAckHdrWrk
     * @param poAckdtlWrk
     * @param outPoTMsg
     * @return
     */
    private boolean isErrorPo(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckdtlWrk, POTMsg outPoTMsg) {

// START 2023/03/13 TZ.Win [QC#60960, DEL]
//        if (outPoTMsg == null) {
//
//            StringBuilder subMsg = new StringBuilder("EDI_PO_ORD_NUM=").append((String) poAckHdrWrk.get(EDI_PO_ORD_NUM));
//            PO_ACK_ERR_WRKTMsg poAckErrWrkTMsg = setPoAckErr(poAckHdrWrk, null, NPAM1169E, new String[] {subMsg.toString() });
//            errorList.add(poAckErrWrkTMsg);
//            S21InfoLogOutput.println(NPAM1169E, new String[] {getSubMsgItemValue((String) poAckHdrWrk.get(EDI_PO_ORD_NUM), this.digitEdiPoOrdNum) });
//            return true;
//
//        }
// END 2023/03/13 TZ.Win [QC#60960, DEL]

        // check Mandatory Item
        if (checkMandatoryHdrItem(poAckHdrWrk, poAckdtlWrk)) {

            return true;

        }

        return false;
    }

    /**
     * checkPrmoItem
     * @param poAckHdrWrk
     * @param poAckDtlWrk
     * @param mdseCd
     * @return
     */
    private BigDecimal checkPrmoItem(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        String slsDate = ZYPDateUtil.getSalesDate();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_VND_MDSE_CD, (String) poAckDtlWrk.get(MDSE_CD));
        ssmParam.put(BIND_EFF_THRU_DT, EFF_THRU_DATE);
        ssmParam.put(BIND_EFF_FROM_DT, EFF_FROM_DATE);
        ssmParam.put(BIND_SLS_DATE, slsDate);

        return (BigDecimal) ssmBatchClient.queryObject("checkPrmoItem", ssmParam);

    }

    /**
     * callPoCreateApi. QC#52579 Mod
     * @param poAckHdrWrk
     * @param poAckDtlWrk
     * @param mdseCd
     * @param outPoDtlTMsg
     * @param isAslMulti
     * @return
     */
    private boolean callPoCreateApi(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, String mdseCd, PO_DTLTMsg outPoDtlTMsg, boolean isAslMulti) {

        NPZC104001PMsg pMsg = new NPZC104001PMsg();

        // common info
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.procDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, (String) outPoDtlTMsg.poOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poOrdDtlLineNum, (String) outPoDtlTMsg.poOrdDtlLineNum.getValue());

        // QC#8424
        // if (ZYPCommonFunc.hasValue(mdseCd)) {
        // // mode 4 po send
        // ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd,
        // NPZC104001Constant.MODE_SEND_PO);
        // ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).vndPoAckStsCd,
        // (String) poAckDtlWrk.get(VND_PO_ACK_STS_CD));
        // ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).vndIssPoOrdNum,
        // (String) poAckDtlWrk.get(VND_ISS_PO_ORD_NUM));
        // ZYPEZDItemValueSetter.setValue(pMsg.vndIssOrdNum, (String)
        // poAckHdrWrk.get(VND_CPO_ORD_NUM));
        //
        // } else {

        Map<String, Object> costUpdateTargetInfoMap = costUpdateTargetInfo((String) outPoDtlTMsg.poOrdNum.getValue(), (String) outPoDtlTMsg.poOrdDtlLineNum.getValue());

        // mode 2 po update
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC104001Constant.MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC104001Constant.EVENT_SUBMIT);
        ZYPEZDItemValueSetter.setValue(pMsg.dsPoTpCd, (String) costUpdateTargetInfoMap.get(DS_PO_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.dsPoTpNm, (String) costUpdateTargetInfoMap.get(DS_PO_TP_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.poQlfyCd, (String) costUpdateTargetInfoMap.get(PO_QLFY_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poSubmtPsnCd, (String) costUpdateTargetInfoMap.get(PO_SUBMT_PSN_CD));
        // QC#52579
        if (isAslMulti) {
            ZYPEZDItemValueSetter.setValue(pMsg.poSubmtTs, (String) costUpdateTargetInfoMap.get(PO_SUBMT_TS));
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.poSubmtTs, this.slsDt);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.poApvlStsCd, (String) costUpdateTargetInfoMap.get(PO_APVL_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poApvlPsnCd, (String) costUpdateTargetInfoMap.get(PO_APVL_PSN_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poApvlDt, (String) costUpdateTargetInfoMap.get(PO_APVL_DT));
        ZYPEZDItemValueSetter.setValue(pMsg.poApvlTs, (String) costUpdateTargetInfoMap.get(PO_APVL_TS));
        ZYPEZDItemValueSetter.setValue(pMsg.destRtlWhCd, (String) costUpdateTargetInfoMap.get(DEST_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.srcRtlWhCd, (String) costUpdateTargetInfoMap.get(SRC_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdSrcCd, (String) costUpdateTargetInfoMap.get(PO_ORD_SRC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, (String) costUpdateTargetInfoMap.get(PRNT_VND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.prntVndNm, (String) costUpdateTargetInfoMap.get(PRNT_VND_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.vndCd, (String) costUpdateTargetInfoMap.get(VND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.vndNm, (String) costUpdateTargetInfoMap.get(VND_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.dealCcyCd, (String) costUpdateTargetInfoMap.get(CCY_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.vndDropShipFlg, (String) costUpdateTargetInfoMap.get(VND_DROP_SHIP_FLG));
        ZYPEZDItemValueSetter.setValue(pMsg.prchGrpCd, (String) costUpdateTargetInfoMap.get(PRCH_GRP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.vndPmtTermCd, (String) costUpdateTargetInfoMap.get(VND_PMT_TERM_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.vndPmtTermDescTxt, (String) costUpdateTargetInfoMap.get(VND_PMT_TERM_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.rqstTechTocCd, (String) costUpdateTargetInfoMap.get(RQST_TECH_TOC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.rqstRcvDt, (String) costUpdateTargetInfoMap.get(RQST_RCV_DT));
        ZYPEZDItemValueSetter.setValue(pMsg.rqstRcvTm, (String) costUpdateTargetInfoMap.get(RQST_RCV_TM));
        // START 2023/07/20 S.Dong [QC#61638, ADD]
        if (IF_ID_NPAA0031.equals(this.interfaceId) || IF_ID_NPAA0032.equals(this.interfaceId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.rqstShipDt, (String) costUpdateTargetInfoMap.get(RQST_SHIP_DT));
        }
        // END 2023/07/20 S.Dong [QC#61638, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, (String) costUpdateTargetInfoMap.get(SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, (String) costUpdateTargetInfoMap.get(SHIP_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToAcctNm, (String) costUpdateTargetInfoMap.get(SHIP_TO_ACCT_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToAddlLocNm, (String) costUpdateTargetInfoMap.get(SHIP_TO_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, (String) costUpdateTargetInfoMap.get(SHIP_TO_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, (String) costUpdateTargetInfoMap.get(SHIP_TO_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, (String) costUpdateTargetInfoMap.get(SHIP_TO_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, (String) costUpdateTargetInfoMap.get(SHIP_TO_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstRefCmntTxt, (String) costUpdateTargetInfoMap.get(SHIP_TO_FIRST_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToScdRefCmntTxt, (String) costUpdateTargetInfoMap.get(SHIP_TO_SCD_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, (String) costUpdateTargetInfoMap.get(SHIP_TO_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, (String) costUpdateTargetInfoMap.get(SHIP_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, (String) costUpdateTargetInfoMap.get(SHIP_TO_PROV_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, (String) costUpdateTargetInfoMap.get(SHIP_TO_CNTY_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, (String) costUpdateTargetInfoMap.get(SHIP_TO_POST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, (String) costUpdateTargetInfoMap.get(SHIP_TO_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, (String) costUpdateTargetInfoMap.get(CTAC_PSN_NM));
        ZYPEZDItemValueSetter.setValue(pMsg.rtrnShipToRtlWhCd, (String) costUpdateTargetInfoMap.get(RTRN_SHIP_TO_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.shipFromSoNumListTxt, (String) costUpdateTargetInfoMap.get(DP_SHIP_FROM_SO_NUM_LIST_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.carrCd, (String) costUpdateTargetInfoMap.get(CARR_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, (String) costUpdateTargetInfoMap.get(CARR_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd, (String) costUpdateTargetInfoMap.get(SHPG_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.frtChrgToCd, (String) costUpdateTargetInfoMap.get(FRT_CHRG_TO_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.frtChrgMethCd, (String) costUpdateTargetInfoMap.get(FRT_CHRG_METH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, (String) costUpdateTargetInfoMap.get(LINE_BIZ_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdCmntTxt, (String) costUpdateTargetInfoMap.get(PO_ORD_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.trsmtMethTpCd, (String) costUpdateTargetInfoMap.get(TRSMT_METH_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.sendPoFaxNum, (String) costUpdateTargetInfoMap.get(SEND_PO_FAX_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.sendPoEmlAddr, (String) costUpdateTargetInfoMap.get(SEND_PO_EML_ADDR));
        ZYPEZDItemValueSetter.setValue(pMsg.poSendFlg, (String) costUpdateTargetInfoMap.get(PO_SEND_FLG));
        ZYPEZDItemValueSetter.setValue(pMsg.poSendTs, (String) costUpdateTargetInfoMap.get(PO_SEND_TS));
        ZYPEZDItemValueSetter.setValue(pMsg.poPrintFlg, (String) costUpdateTargetInfoMap.get(PO_PRINT_FLG));
        ZYPEZDItemValueSetter.setValue(pMsg.dsctnInd, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.wfFlg, ZYPConstant.FLG_OFF_N);
        // ZYPEZDItemValueSetter.setValue(pMsg.vndIssOrdNum, (String)
        // costUpdateTargetInfoMap.get(VND_ISS_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.vndIssOrdNum, (String) poAckHdrWrk.get(VND_CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.eipRptRqstPk, (BigDecimal) costUpdateTargetInfoMap.get(EIP_RPT_RQST_PK));

        // PO Header Message Information
        // null

        // PO Line Information
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).dispPoDtlLineNum, (String) costUpdateTargetInfoMap.get(DISP_PO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poLineTpCd, (String) costUpdateTargetInfoMap.get(PO_LINE_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poMdseCmpsnTpCd, (String) costUpdateTargetInfoMap.get(PO_MDSE_CMPSN_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).setPoOrdDtlLineNum, (String) costUpdateTargetInfoMap.get(SET_PO_ORD_DTL_LINE_NUM));
        // ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).mdseCd,
        // (String) costUpdateTargetInfoMap.get(MDSE_CD));
        // QC#19664 Start
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).mdseCd, outPoDtlTMsg.mdseCd.getValue());

//        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).mdseNm, (String) costUpdateTargetInfoMap.get(MDSE_NM));
//        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).mdseDescShortTxt, (String) costUpdateTargetInfoMap.get(MDSE_DESC_SHORT_TXT));
        // QC#19664 End
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poQty, (BigDecimal) costUpdateTargetInfoMap.get(PO_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poDispQty, (BigDecimal) costUpdateTargetInfoMap.get(PO_DISP_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poInvQty, (BigDecimal) costUpdateTargetInfoMap.get(PO_INV_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poDispUomCd, (String) costUpdateTargetInfoMap.get(PO_DISP_UOM_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).thisMthFobCostAmt, (BigDecimal) costUpdateTargetInfoMap.get(THIS_MTH_FOB_COST_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).entDealNetUnitPrcAmt, newPrice);

        //
        // BigDecimal entPoDtlDealNetAmt =
        // newPrice.multiply((BigDecimal) poAckDtlWrk.get(ORD_QTY));
        // ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).entPoDtlDealNetAmt,
        // entPoDtlDealNetAmt);
        // ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).entFuncNetUnitPrcAmt,
        // newPrice);
        // ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).entPoDtlFuncNetAmt,
        // entPoDtlDealNetAmt);

        pMsg.poLineInfo.no(0).entPoDtlDealNetAmt.clear();
        pMsg.poLineInfo.no(0).entFuncNetUnitPrcAmt.clear();
        pMsg.poLineInfo.no(0).entPoDtlFuncNetAmt.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).exchRate, (BigDecimal) costUpdateTargetInfoMap.get(EXCH_RATE));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).custUomCd, (String) costUpdateTargetInfoMap.get(CUST_UOM_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).destRtlSwhCd, (String) costUpdateTargetInfoMap.get(DEST_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).srcRtlSwhCd, (String) costUpdateTargetInfoMap.get(SRC_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).invtyLocCd, (String) costUpdateTargetInfoMap.get(INVTY_LOC_CD));
        // QC#31207
        // START 2023/07/20 S.Dong [QC#61638, MOD]
        //if (ZYPCommonFunc.hasValue((String) poAckDtlWrk.get(ETA_DT))) {
        if (ZYPCommonFunc.hasValue((String) poAckDtlWrk.get(ETA_DT)) && !(IF_ID_NPAA0031.equals(this.interfaceId) || IF_ID_NPAA0032.equals(this.interfaceId))) {
        // END 2023/07/20 S.Dong [QC#61638, MOD]
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).rqstRcvDt, (String) poAckDtlWrk.get(ETA_DT));
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).rqstRcvDt, (String) costUpdateTargetInfoMap.get(RQST_RCV_DT));
        }
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).rqstRcvTm, (String) costUpdateTargetInfoMap.get(RQST_RCV_TM));
        // START 2023/07/20 S.Dong [QC#61638, ADD]
        if (IF_ID_NPAA0031.equals(this.interfaceId) || IF_ID_NPAA0032.equals(this.interfaceId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).rqstShipDt, (String) costUpdateTargetInfoMap.get(DTL_RQST_SHIP_DT));
        }
        // END 2023/07/20 S.Dong [QC#61638, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).frtCondCd, (String) costUpdateTargetInfoMap.get(FRT_COND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).origMdseCd, (String) costUpdateTargetInfoMap.get(ORIG_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).fromStkStsCd, (String) costUpdateTargetInfoMap.get(FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).toStkStsCd, (String) costUpdateTargetInfoMap.get(TO_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).adminPsnCd, (String) costUpdateTargetInfoMap.get(ADMIN_PSN_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poMatchTpCd, (String) costUpdateTargetInfoMap.get(PO_MATCH_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).ordQty, (BigDecimal) costUpdateTargetInfoMap.get(ORD_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).cpoOrdNum, (String) costUpdateTargetInfoMap.get(CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).cpoDtlLineNum, (String) costUpdateTargetInfoMap.get(CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).cpoDtlLineSubNum, (String) costUpdateTargetInfoMap.get(CPO_DTL_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).custIssPoNum, (String) costUpdateTargetInfoMap.get(CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).custIssPoDt, (String) costUpdateTargetInfoMap.get(CUST_ISS_PO_DT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).cpoOrdTpCd, (String) costUpdateTargetInfoMap.get(CPO_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).billToCustCd, (String) costUpdateTargetInfoMap.get(BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).sellToCustCd, (String) costUpdateTargetInfoMap.get(SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).shpgPlnNum, (String) costUpdateTargetInfoMap.get(SHPG_PLN_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).prchReqNum, (String) costUpdateTargetInfoMap.get(PRCH_REQ_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).prchReqLineNum, (String) costUpdateTargetInfoMap.get(PRCH_REQ_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).prchReqLineSubNum, (BigDecimal) costUpdateTargetInfoMap.get(PRCH_REQ_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).trxRefNum, (String) costUpdateTargetInfoMap.get(TRX_REF_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).trxRefLineNum, (String) costUpdateTargetInfoMap.get(TRX_REF_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).trxRefLineSubNum, (String) costUpdateTargetInfoMap.get(TRX_REF_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).aslDtlPk, (BigDecimal) costUpdateTargetInfoMap.get(ASL_DTL_PK));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).aslMdseCd, (String) costUpdateTargetInfoMap.get(ASL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).aslUnitPrcAmt, (BigDecimal) costUpdateTargetInfoMap.get(ASL_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).shipFromSoNumListTxt, (String) costUpdateTargetInfoMap.get(DPD_SHIP_FROM_SO_NUM_LIST_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).vndInvtyLocCd, (String) costUpdateTargetInfoMap.get(VND_INVTY_LOC_CD));
        // ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).vndIssPoOrdNum,
        // (String) costUpdateTargetInfoMap.get(VND_ISS_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).vndIssPoOrdNum, (String) poAckDtlWrk.get(VND_ISS_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).proNum, (String) costUpdateTargetInfoMap.get(PRO_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).origPoOrdNum, (String) costUpdateTargetInfoMap.get(ORIG_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).origPoOrdDtlLineNum, (String) costUpdateTargetInfoMap.get(ORIG_PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).origDispPoDtlLineNum, (String) costUpdateTargetInfoMap.get(ORIG_DISP_PO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).svcConfigMstrPk, (BigDecimal) costUpdateTargetInfoMap.get(SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poSendTs, (String) costUpdateTargetInfoMap.get(PO_SEND_TS));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).poOrdDtlCmntTxt, (String) costUpdateTargetInfoMap.get(PO_ORD_DTL_CMNT_TXT));
        // ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).vndPoAckStsCd,
        // (String) costUpdateTargetInfoMap.get(VND_PO_ACK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).vndPoAckStsCd, (String) poAckDtlWrk.get(VND_PO_ACK_STS_CD));
        // }

        pMsg.poLineInfo.setValidCount(1);

        String poMdseCmpsnTpCd = pMsg.poLineInfo.no(0).poMdseCmpsnTpCd.getValue();
        if (poMdseCmpsnTpCd != null && PO_MDSE_CMPSN_TP.PARENT.equals(poMdseCmpsnTpCd)) {
            //get set children info. Mod QC#31207
            setChildrenInfo(outPoDtlTMsg, pMsg, poAckDtlWrk);
        }
        
        // Call PO Update API
        NPZC104001 api = new NPZC104001();
        api.execute(pMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);

            for (String xxMsgId : xxMsgIdList) {

                S21InfoLogOutput.println(xxMsgId);

            }

            StringBuilder subMsg = new StringBuilder("API:PO Create - Vendor Ack Status: ");
            subMsg.append("EDI_PO_ORD_NUM=").append((String) poAckDtlWrk.get(EDI_PO_ORD_NUM));

            if (mdseCd != null) {
                subMsg.append("MDSE_CD = ").append(mdseCd);
            } else {
                subMsg.append("PO_ORD_DTL_LINE_NUM = ").append((String) outPoDtlTMsg.poOrdDtlLineNum.getValue());
            }

            PO_ACK_ERR_WRKTMsg poAckError = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1178E, new String[] {subMsg.toString() });

            errorList.add(poAckError);

            S21InfoLogOutput.println(NPAM1178E, new String[] {subMsg.toString() });

            return true;
        }

        return false;
    }

    private void setChildrenInfo(PO_DTLTMsg outPoDtlTMsg, NPZC104001PMsg pMsg, Map<String, Object> poAckDtlWrk) {
        List<Map<String, Object>> searchSetChildrenTargetList = searchSetChildrenTarget((String) outPoDtlTMsg.poOrdNum.getValue(), (String) outPoDtlTMsg.poOrdDtlLineNum.getValue());
        int i = 1;
        for(Map<String,Object> setChild : searchSetChildrenTargetList) {
            // PO Line Information
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).dispPoDtlLineNum, (String) setChild.get(DISP_PO_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poLineTpCd, (String) setChild.get(PO_LINE_TP_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poOrdDtlLineNum, (String) setChild.get(PO_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poMdseCmpsnTpCd, (String) setChild.get(PO_MDSE_CMPSN_TP_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).setPoOrdDtlLineNum, (String) setChild.get(SET_PO_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).mdseCd, (String) setChild.get(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).mdseNm, (String) setChild.get(MDSE_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).mdseDescShortTxt, (String) setChild.get(MDSE_DESC_SHORT_TXT));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poQty, (BigDecimal) setChild.get(PO_QTY));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poDispQty, (BigDecimal) setChild.get(PO_DISP_QTY));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poInvQty, (BigDecimal) setChild.get(PO_INV_QTY));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poDispUomCd, (String) setChild.get(PO_DISP_UOM_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).thisMthFobCostAmt, (BigDecimal) setChild.get(THIS_MTH_FOB_COST_AMT));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).entDealNetUnitPrcAmt, (BigDecimal) setChild.get(ENT_DEAL_NET_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).entFuncNetUnitPrcAmt, (BigDecimal) setChild.get(ENT_FUNC_NET_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).entPoDtlDealNetAmt, (BigDecimal) setChild.get(ENT_PO_DTL_DEAL_NET_AMT));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).entPoDtlFuncNetAmt, (BigDecimal) setChild.get(ENT_PO_DTL_FUNC_NET_AMT));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).exchRate, (BigDecimal) setChild.get(EXCH_RATE));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).custUomCd, (String) setChild.get(CUST_UOM_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).destRtlSwhCd, (String) setChild.get(DEST_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).srcRtlSwhCd, (String) setChild.get(SRC_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).invtyLocCd, (String) setChild.get(INVTY_LOC_CD));
            // QC#31207
            // START 2023/07/20 S.Dong [QC#61638, MOD]
            //if (ZYPCommonFunc.hasValue((String) poAckDtlWrk.get(ETA_DT))) {
            if (ZYPCommonFunc.hasValue((String) poAckDtlWrk.get(ETA_DT)) && !(IF_ID_NPAA0031.equals(this.interfaceId) || IF_ID_NPAA0032.equals(this.interfaceId))) {
            // END 2023/07/20 S.Dong [QC#61638, MOD]
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).rqstRcvDt, (String) poAckDtlWrk.get(ETA_DT));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(0).rqstRcvDt, (String) setChild.get(RQST_RCV_DT));
            }
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).rqstRcvDt, (String) setChild.get(RQST_RCV_DT));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).rqstRcvTm, (String) setChild.get(RQST_RCV_TM));
            // START 2023/07/20 S.Dong [QC#61638, ADD]
            if (IF_ID_NPAA0031.equals(this.interfaceId) || IF_ID_NPAA0032.equals(this.interfaceId)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).rqstShipDt, (String) setChild.get(DTL_RQST_SHIP_DT));
            }
            // END 2023/07/20 S.Dong [QC#61638, ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).frtCondCd, (String) setChild.get(FRT_COND_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).origMdseCd, (String) setChild.get(ORIG_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).fromStkStsCd, (String) setChild.get(FROM_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).toStkStsCd, (String) setChild.get(TO_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).adminPsnCd, (String) setChild.get(ADMIN_PSN_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poMatchTpCd, (String) setChild.get(PO_MATCH_TP_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).ordQty, (BigDecimal) setChild.get(ORD_QTY));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).cpoOrdNum, (String) setChild.get(CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).cpoDtlLineNum, (String) setChild.get(CPO_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).cpoDtlLineSubNum, (String) setChild.get(CPO_DTL_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).custIssPoNum, (String) setChild.get(CUST_ISS_PO_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).custIssPoDt, (String) setChild.get(CUST_ISS_PO_DT));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).cpoOrdTpCd, (String) setChild.get(CPO_ORD_TP_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).billToCustCd, (String) setChild.get(BILL_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).sellToCustCd, (String) setChild.get(SELL_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).shpgPlnNum, (String) setChild.get(SHPG_PLN_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).prchReqNum, (String) setChild.get(PRCH_REQ_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).prchReqLineNum, (String) setChild.get(PRCH_REQ_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).prchReqLineSubNum, (BigDecimal) setChild.get(PRCH_REQ_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).trxRefNum, (String) setChild.get(TRX_REF_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).trxRefLineNum, (String) setChild.get(TRX_REF_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).trxRefLineSubNum, (String) setChild.get(TRX_REF_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).aslDtlPk, (BigDecimal) setChild.get(ASL_DTL_PK));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).aslMdseCd, (String) setChild.get(ASL_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).aslUnitPrcAmt, (BigDecimal) setChild.get(ASL_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).shipFromSoNumListTxt, (String) setChild.get(DPD_SHIP_FROM_SO_NUM_LIST_TXT));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).vndInvtyLocCd, (String) setChild.get(VND_INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).vndIssPoOrdNum, (String) setChild.get(VND_ISS_PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).proNum, (String) setChild.get(PRO_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).origPoOrdNum, (String) setChild.get(ORIG_PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).origPoOrdDtlLineNum, (String) setChild.get(ORIG_PO_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).origDispPoDtlLineNum, (String) setChild.get(ORIG_DISP_PO_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).svcConfigMstrPk, (BigDecimal) setChild.get(SVC_CONFIG_MSTR_PK));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poSendTs, (String) setChild.get(PO_SEND_TS));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poOrdDtlCmntTxt, (String) setChild.get(PO_ORD_DTL_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).vndPoAckStsCd, (String) setChild.get(VND_PO_ACK_STS_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).rqstRcvDt, (String) setChild.get(ETA_DT));

            i++;
        }
        if (searchSetChildrenTargetList.size() > 0){
            pMsg.poLineInfo.setValidCount(searchSetChildrenTargetList.size() + 1);
        }
    }

    /**
     * callPoUpdateApi
     * @param poAckHdrWrk
     * @param poAckDtlWrk
     * @param mdseCd
     * @param outPoDtlTMsg
     * @return
     */
    private boolean callPoUpdateApi(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, String mdseCd, PO_DTLTMsg outPoDtlTMsg) {

        NPZC004001PMsg pMsg = new NPZC004001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, outPoDtlTMsg.poOrdNum);

        if (PO_STS.PO_ERROR.equals((String) poAckDtlWrk.get(PO_ACK_LINE_STS_CD))) {

            ZYPEZDItemValueSetter.setValue(pMsg.poStsCd, PO_STS.PO_ERROR);

        } else {

            ZYPEZDItemValueSetter.setValue(pMsg.poStsCd, PO_STS.PO_CONFIRMED);

        }

        ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, outPoDtlTMsg.poOrdDtlLineNum);

        // // Call PO Update API
        NPZC004001 api = new NPZC004001();
        api.execute(pMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);

            for (String xxMsgId : xxMsgIdList) {

                S21InfoLogOutput.println(xxMsgId);

            }

            StringBuilder subMsg = new StringBuilder("API:PO Create - Vendor Ack Status Update: PO#: ");
            subMsg.append("PO_ORD_NUM=").append((String) outPoDtlTMsg.poOrdNum.getValue());
            subMsg.append("PO_ORD_DTL_LINE_NUM=").append((String) outPoDtlTMsg.poOrdDtlLineNum.getValue());

            PO_ACK_ERR_WRKTMsg poAckError = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1178E, new String[] {subMsg.toString() });

            errorList.add(poAckError);

            S21InfoLogOutput.println(NPAM1178E, new String[] {subMsg.toString() });

            return true;
        }

        return false;
    }

    /**
     * setPoAckErr
     * @param poAckHdrWrk
     * @param poAckDtlWrk
     * @param msgId
     * @param msgArgs
     * @return
     */
    private PO_ACK_ERR_WRKTMsg setPoAckErr(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, String msgId, String[] msgArgs) {

        String msg = null;

        if (msgArgs != null) {

            msg = S21MessageFunc.clspGetMessage(msgId, msgArgs);

        } else {

            msg = S21MessageFunc.clspGetMessage(msgId);

        }

        return setPoAckErrWrk(poAckHdrWrk, poAckDtlWrk, msg);
    }

    /**
     * setPoAckDtlWrk
     * @param poAckDtlWrkPk
     * @return
     */
    private BigDecimal setPoAckDtlWrk(BigDecimal poAckDtlWrkPk) {

        if (ZYPCommonFunc.hasValue(poAckDtlWrkPk)) {

            return poAckDtlWrkPk;
        }

        return BigDecimal.ZERO;
    }

    /**
     * insertAndUpdatePoAckDtl
     * @param poAckHdrWrk
     * @param vndCd
     * @return
     */
    private boolean insertAndUpdatePoAckDtl(PO_ACK_HDRTMsg insertPoAckHdr, Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, poAckBean poAckBean, POTMsg outPoTMsg) {

        PO_ACK_DTLTMsg outPoAckDtl = new PO_ACK_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(outPoAckDtl.glblCmpyCd, this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.poAckDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.poOrdDtlLineNum, poAckBean.getApiReturnedPoOrdDtlLineNum());
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.poOrdDtlSubLineNum, (String) poAckDtlWrk.get(PO_ORD_DTL_SUB_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.shpgPlnDplyLineNum, (String) poAckDtlWrk.get(SHPG_PLN_DPLY_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.poOrdNum, poAckBean.getApiReturnedPoOrdNum());
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.poAckLineStsCd, (String) poAckDtlWrk.get(PO_ACK_LINE_STS_CD));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndPoAckLineStsTxt, (String) poAckDtlWrk.get(VND_PO_ACK_LINE_STS_TXT));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndMdseCd, poAckBean.getOrigVndMdseCd());
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.mdseCd, poAckBean.getChangMdseCd());

        if (!ZYPCommonFunc.hasValue((String) poAckDtlWrk.get(MDSE_NM))) {
            ZYPEZDItemValueSetter.setValue(outPoAckDtl.mdseNm, poAckBean.getChangMdseNm());
        } else {
            ZYPEZDItemValueSetter.setValue(outPoAckDtl.mdseNm, (String) poAckDtlWrk.get(MDSE_NM));
        }

        ZYPEZDItemValueSetter.setValue(outPoAckDtl.trdPtnrSkuCd, (String) poAckDtlWrk.get(TRD_PTNR_SKU_CD));
        if (!ZYPCommonFunc.hasValue(outPoAckDtl.trdPtnrSkuCd)) {
            ZYPEZDItemValueSetter.setValue(outPoAckDtl.trdPtnrSkuCd, poAckBean.getChangMdseCd());
        }

        ZYPEZDItemValueSetter.setValue(outPoAckDtl.ordQty, (BigDecimal) poAckDtlWrk.get(ORD_QTY));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.thisMthFobCostAmt, (BigDecimal) poAckDtlWrk.get(THIS_MTH_FOB_COST_AMT));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.ccyCd, (String) poAckDtlWrk.get(CCY_CD));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.uomCd, (String) poAckDtlWrk.get(UOM_CD));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.etdDt, editDate((String) poAckDtlWrk.get(ETD_DT), outPoTMsg));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.etaDt, editDate((String) poAckDtlWrk.get(ETA_DT), outPoTMsg));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndCpoOrdNum, (String) poAckDtlWrk.get(VND_CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndCpoDtlLineNum, (String) poAckDtlWrk.get(VND_CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndCpoDtlLineSubNum, (String) poAckDtlWrk.get(VND_CPO_DTL_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndCpoLineStsCd, (String) poAckDtlWrk.get(VND_CPO_LINE_STS_CD));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.shpgStsCd, (String) poAckDtlWrk.get(SHPG_STS_CD));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.origTrdPtnrSkuCd, (String) poAckDtlWrk.get(ORIG_TRD_PTNR_SKU_CD));
        if (!ZYPCommonFunc.hasValue(outPoAckDtl.origTrdPtnrSkuCd)) {
            ZYPEZDItemValueSetter.setValue(outPoAckDtl.origTrdPtnrSkuCd, poAckBean.getChangMdseCd());
        }
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.origOrdQty, (BigDecimal) poAckDtlWrk.get(ORIG_ORD_QTY));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.origVndMdseCd, (String) poAckDtlWrk.get(ORIG_VND_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.shpgPlnNum, (String) poAckDtlWrk.get(SHPG_PLN_NUM));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndCpoCratTs, (String) poAckDtlWrk.get(VND_CPO_CRAT_TS));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.ordDtlLastUpdTs, (String) poAckDtlWrk.get(ORD_DTL_LAST_UPD_TS));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndInvtyLocCd, (String) poAckDtlWrk.get(VND_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndIssPoOrdNum, (String) poAckDtlWrk.get(VND_ISS_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndOtbdCarrCd, (String) poAckDtlWrk.get(VND_OTBD_CARR_CD));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndOtbdCarrNm, (String) poAckDtlWrk.get(VND_OTBD_CARR_NM));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.proNum, (String) poAckDtlWrk.get(PRO_NUM));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndSoNum, (String) poAckDtlWrk.get(VND_SO_NUM));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndSoSlpNum, (String) poAckDtlWrk.get(VND_SO_SLP_NUM));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndShipToCustCd, (String) poAckDtlWrk.get(VND_SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndShipToCustLocNm, (String) poAckDtlWrk.get(VND_SHIP_TO_CUST_LOC_NM));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndSellToCustCd, (String) poAckDtlWrk.get(VND_SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndSellToCustLocNm, (String) poAckDtlWrk.get(VND_SELL_TO_CUST_LOC_NM));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndChildBomPrcAmt, (BigDecimal) poAckDtlWrk.get(VND_CHILD_BOM_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.poAckHdrPk, insertPoAckHdr.poAckHdrPk.getValue());
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.vndPoAckStsCd, (String) poAckDtlWrk.get(VND_PO_ACK_STS_CD));
        ZYPEZDItemValueSetter.setValue(outPoAckDtl.poAckDtlLtstFlg, ZYPConstant.FLG_ON_Y);

        EZDTBLAccessor.create(outPoAckDtl);

        createPoAckDtlList.add(outPoAckDtl);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outPoAckDtl.getReturnCode())) {

            StringBuilder sb = new StringBuilder();

            sb.append("TABLE:PO_ACK_DTL PO_ORD_NUM:").append((String) poAckDtlWrk.get(PO_ORD_NUM)).append(" PO_ORD_DTL_LINE_NUM:").append((String) poAckDtlWrk.get(PO_ORD_DTL_LINE_NUM)).append(" PO_ORD_DTL_SUB_LINE_NUM:").append(
                    (String) poAckDtlWrk.get(PO_ORD_DTL_SUB_LINE_NUM)).append(" SHPG_PLN_DPLY_LINE_NUM:").append((String) poAckDtlWrk.get(SHPG_PLN_DPLY_LINE_NUM));

            String msg = null;

            if (outPoAckDtl == null) {

                S21InfoLogOutput.println(NPAM1172E, new String[] {sb.toString() });
                msg = S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {sb.toString() });

            } else {

                S21InfoLogOutput.println(NPAM1171E, new String[] {sb.toString() });
                msg = S21MessageFunc.clspGetMessage(NPAM1171E, new String[] {sb.toString() });
            }

            sendMail(MAIL_TEMPLATE_ID_04, poAckHdrWrk, msg, outPoTMsg);

            isError = true;

        }

        // START 2019/02/21 M.Naito [QC#30277,MOD]
//        synchronizePoAckDtl(outPoAckDtl.poAckDtlPk.getValue(), poAckDtlWrk, poAckBean.getApiReturnedPoOrdDtlLineNum());
        synchronizePoAckDtl(outPoAckDtl, poAckDtlWrk, poAckBean.getApiReturnedPoOrdDtlLineNum());
        // END 2019/02/21 M.Naito [QC#30277,MOD]

        return isError;

    }

    /**
     * 
     */
    private void clearList() {

        this.errorList.clear();
        this.warningList.clear();
    }

    /**
     * synchronizePoAckDtl
     * @param pk
     * @param poAckDtlWrk
     * @return
     */
    //QC# 28665 mod start
    // START 2019/02/21 M.Naito [QC#30277,MOD]
//    private boolean synchronizePoAckDtl(BigDecimal pk, Map<String, Object> poAckDtlWrk, String ediLineNum) {
    private boolean synchronizePoAckDtl(PO_ACK_DTLTMsg outPoAckDtl, Map<String, Object> poAckDtlWrk, String ediLineNum) {
    // END 2019/02/21 M.Naito [QC#30277,MOD]
        // QC# 28665 mod end

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        String ediNum = (String) poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM);
        if (!ZYPCommonFunc.hasValue(ediNum)) {
            ediNum = ediLineNum;
        }

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        // START 2019/02/21 M.Naito [QC#30277,MOD]
//        ssmParam.put(BIND_PO_ORD_NUM, (String) poAckDtlWrk.get(EDI_PO_ORD_NUM));
        ssmParam.put(BIND_PO_ORD_NUM, outPoAckDtl.poOrdNum.getValue());
        // END 2019/02/21 M.Naito [QC#30277,MOD]

        if (ZYPCommonFunc.hasValue(ediNum)) {

            if (ediNum.length() > 3) {

                ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum.substring(ediNum.length() - 3));

            } else {

                ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum);

            }

        } else {

            ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum);

        }

        // START 2019/02/21 M.Naito [QC#30277,MOD]
//        ssmParam.put(BIND_PO_ACK_DTL_PK, pk);
        ssmParam.put(BIND_PO_ACK_DTL_PK, outPoAckDtl.poAckDtlPk.getValue());
        // END 2019/02/21 M.Naito [QC#30277,MOD]

        // *******************AND EZCANCELFLAG = '0' or '1'
        // *******************************************************
        List<Map<String, Object>> updateTargetPoAckDtlPkList = ssmBatchClient.queryObjectList("getUpdateTargetPoAckDtlPk", ssmParam);

        if (updateTargetPoAckDtlPkList != null) {

            for (int i = 0; i < updateTargetPoAckDtlPkList.size(); i++) {

                Map<String, Object> updateTargetPoAckDtlPk = updateTargetPoAckDtlPkList.get(i);

                PO_ACK_DTLTMsg updateMsg = new PO_ACK_DTLTMsg();

                ZYPEZDItemValueSetter.setValue(updateMsg.glblCmpyCd, this.glCmpCd);
                ZYPEZDItemValueSetter.setValue(updateMsg.poAckDtlPk, (BigDecimal) updateTargetPoAckDtlPk.get(PO_ACK_DTL_PK));

                updateMsg = (PO_ACK_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(updateMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateMsg.getReturnCode())) {

                    return isError;
                }

                ZYPEZDItemValueSetter.setValue(updateMsg.poAckDtlLtstFlg, ZYPConstant.FLG_OFF_N);

                EZDTBLAccessor.update(updateMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateMsg.getReturnCode())) {

                    return isError;
                }

            }

        }

        return isError;
    }

    /**
     * Get PO record.
     * @param inPoTMsg POTMsg
     * @param poOrdNum String
     * @return POTMsg
     */
    private POTMsg getPoData(POTMsg inPoTMsg, String poOrdNum) {

        if (!ZYPCommonFunc.hasValue(poOrdNum)) {

            return null;
        }

        ZYPEZDItemValueSetter.setValue(inPoTMsg.glblCmpyCd, this.glCmpCd);

        if (poOrdNum.length() > digitPoOrdNum) {

            return null;

        }

        ZYPEZDItemValueSetter.setValue(inPoTMsg.poOrdNum, poOrdNum);

        return (POTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inPoTMsg);

    }

    /**
     * checkOldMdseCd
     * @param poAckHdrWrk
     * @param poAckDtlWrk
     * @param mdseCd
     * @return
     */
    private boolean checkOldMdseCd(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, String mdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_MDSE_CD, mdseCd);

        String oldMdseCd = (String) ssmBatchClient.queryObject("getOldMdseCd", ssmParam);

        if (oldMdseCd == null) {

            StringBuilder sbKey = new StringBuilder();
            sbKey.append("EDI_PO_ORD_NUM=");
            sbKey.append(poAckHdrWrk.get(EDI_PO_ORD_NUM));
            sbKey.append(", EDI_PO_ORD_DTL_LINE_NUM=");
            sbKey.append(poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM));
            PO_ACK_ERR_WRKTMsg poAckErrWrkTMsg = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1288E, new String[] {ERROR_MDSE_MASTER, ERROR_SUPPLY_ITEM_NUMBER, sbKey.toString() });

            errorList.add(poAckErrWrkTMsg);

            S21InfoLogOutput.println(NPAM1288E, new String[] {ERROR_MDSE_MASTER, ERROR_SUPPLY_ITEM_NUMBER, sbKey.toString() });

            isError = true;

        }

        return isError;
    }

    /**
     * insertAndUpdatePoAckHdr
     * @param poAckHdrTMsg
     * @param poAckHdrWrkTMsg
     * @return
     */
    private PO_ACK_HDRTMsg insertAndUpdatePoAckHdr(Map<String, Object> poAckHdrWrk, String vndCd, POTMsg outPoTMsg) {

        if (!ZYPCommonFunc.hasValue((BigDecimal) poAckHdrWrk.get(PO_ACK_HDR_PK))) {
            poAckHdrWrk.put(PO_ACK_HDR_PK, ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_HDR_SQ));
        }

        // ACK Reprocess Check Step Header
        // *********************************************
        PO_ACK_HDRTMsg reprocessPoAckHdr = new PO_ACK_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(reprocessPoAckHdr.glblCmpyCd, this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(reprocessPoAckHdr.poAckHdrPk, (BigDecimal) poAckHdrWrk.get(PO_ACK_HDR_PK));
        reprocessPoAckHdr = (PO_ACK_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(reprocessPoAckHdr);

        if (reprocessPoAckHdr != null) {
            EZDTBLAccessor.logicalRemove(reprocessPoAckHdr);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(reprocessPoAckHdr.getReturnCode())) {
                isError = true;
            }
        }

        // ACK Reprocess Check Step Detail EZCANCELFLAG '0' OR '1'
        // ***********************************************************
        Map<String, Object> ssmParamDtl = new HashMap<String, Object>();

        ssmParamDtl.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParamDtl.put(BIND_PO_ACK_HDR_PK, (BigDecimal) poAckHdrWrk.get(PO_ACK_HDR_PK));

        List<Map<String, Object>> logicalDeleteTargetPoAckDtlPkList = ssmBatchClient.queryObjectList("logicalDeleteTargetPoAckDtlPkList", ssmParamDtl);

        for (int i = 0; i < logicalDeleteTargetPoAckDtlPkList.size(); i++) {

            Map<String, Object> logicalDeleteTargetPoAckDtlPkMap = logicalDeleteTargetPoAckDtlPkList.get(i);
            PO_ACK_DTLTMsg updateMsg = new PO_ACK_DTLTMsg();

            ZYPEZDItemValueSetter.setValue(updateMsg.poAckDtlPk, (BigDecimal) logicalDeleteTargetPoAckDtlPkMap.get(PO_ACK_DTL_PK));
            ZYPEZDItemValueSetter.setValue(updateMsg.glblCmpyCd, this.glCmpCd);

            updateMsg = (PO_ACK_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(updateMsg);

            if (updateMsg != null) {
                ZYPEZDItemValueSetter.setValue(updateMsg.poAckDtlLtstFlg, ZYPConstant.FLG_OFF_N);
                EZDTBLAccessor.update(updateMsg);
                EZDTBLAccessor.logicalRemove(updateMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateMsg.getReturnCode())) {
                    isError = true;
                }
            }

        }

        // ACK Create Step
        // *********************************************
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_PO_ORD_NUM, poAckHdrWrk.get(EDI_PO_ORD_NUM));
        ssmParam.put(BIND_PO_ACK_HDR_LTST_FLG, ZYPConstant.FLG_ON_Y);

        Map<String, Object> poAckHdrMap = (Map<String, Object>) ssmBatchClient.queryObject("getPoAckHdr", ssmParam);

        PO_ACK_HDRTMsg outPoAckHdr = new PO_ACK_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(outPoAckHdr.glblCmpyCd, this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(outPoAckHdr.poAckHdrPk, (BigDecimal) poAckHdrWrk.get(PO_ACK_HDR_PK));
        // QC#26455
        if (ZYPCommonFunc.hasValue((String) poAckHdrWrk.get("XREF_PO_ORD_NUM"))) {
            ZYPEZDItemValueSetter.setValue(outPoAckHdr.poOrdNum, (String) poAckHdrWrk.get("XREF_PO_ORD_NUM"));
        } else {
            ZYPEZDItemValueSetter.setValue(outPoAckHdr.poOrdNum, (String) poAckHdrWrk.get(EDI_PO_ORD_NUM));
        }
        ZYPEZDItemValueSetter.setValue(outPoAckHdr.itrlIntfcId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(outPoAckHdr.vndCpoOrdNum, (String) poAckHdrWrk.get(VND_CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(outPoAckHdr.ordHdrStsCd, (String) poAckHdrWrk.get(ORD_HDR_STS_CD));
        ZYPEZDItemValueSetter.setValue(outPoAckHdr.ordLastUpdTs, (String) poAckHdrWrk.get(ORD_LAST_UPD_TS));
        ZYPEZDItemValueSetter.setValue(outPoAckHdr.poAckUpdProcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(outPoAckHdr.vndCd, vndCd);
        ZYPEZDItemValueSetter.setValue(outPoAckHdr.poAckNum, (String) poAckHdrWrk.get(PO_ACK_NUM));
        ZYPEZDItemValueSetter.setValue(outPoAckHdr.poAckHdrLtstFlg, ZYPConstant.FLG_ON_Y);

        if (poAckHdrMap != null) {
            ZYPEZDItemValueSetter.setValue(outPoAckHdr.poSendTs, (String) poAckHdrMap.get(PO_SEND_TS));
        }

        EZDTBLAccessor.create(outPoAckHdr);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outPoAckHdr.getReturnCode())) {

            StringBuilder sb = new StringBuilder();

            sb.append("TABLE:PO_ACK_HDR PO_ORD_NUM:").append((String) poAckHdrWrk.get(EDI_PO_ORD_NUM));

            String msg = null;

            if (outPoAckHdr == null) {
                S21InfoLogOutput.println(NPAM1172E, new String[] {sb.toString() });
                msg = S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {sb.toString() });

            } else {
                S21InfoLogOutput.println(NPAM1171E, new String[] {sb.toString() });
                msg = S21MessageFunc.clspGetMessage(NPAM1171E, new String[] {sb.toString() });

            }

            sendMail(MAIL_TEMPLATE_ID_04, poAckHdrWrk, msg, outPoTMsg);

            isError = true;

        }

        // START 2019/02/21 M.Naito [QC#30277,MOD]
//        synchronizePoAckHdr(outPoAckHdr.poAckHdrPk.getValue(), poAckHdrWrk);
        synchronizePoAckHdr(outPoAckHdr.poAckHdrPk.getValue(), outPoAckHdr.poOrdNum.getValue());
        // END 2019/02/21 M.Naito [QC#30277,MOD]

        return outPoAckHdr;
    }

    /**
     * checkMandatoryCusaMdse
     * @param poAckHdrWrk
     * @param poAckDtlWrk
     * @return
     */
    private boolean checkMandatoryCusaMdse(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk) {

        if (!ZYPCommonFunc.hasValue((String) poAckDtlWrk.get(ORIG_VND_MDSE_CD))) {

            StringBuilder sbKey = new StringBuilder();
            sbKey.append("EDI_PO_ORD_NUM=");
            sbKey.append(poAckDtlWrk.get(EDI_PO_ORD_NUM));
            sbKey.append(", EDI_PO_ORD_DTL_LINE_NUM=");
            sbKey.append(poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM));
            PO_ACK_ERR_WRKTMsg poAckErrWrkTMsg = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1321E, new String[] {ORIG_VND_MDSE_CD, PO_ACK_DTL_WRK, sbKey.toString() });

            errorList.add(poAckErrWrkTMsg);

            S21InfoLogOutput.println(NPAM1321E, new String[] {ERROR_ORIG_VND_MDSE_CD, PO_ACK_DTL_WRK, sbKey.toString() });

            isError = true;
        }

        return isError;
    }

    /**
     * synchronizePoAckHdr
     * @param pk
     * @param sendPoMap
     * @param msgMap
     * @return
     */
    // START 2019/02/21 M.Naito [QC#30277,MOD]
//    private boolean synchronizePoAckHdr(BigDecimal pk, Map<String, Object> poAckHdrWrk) {
    private boolean synchronizePoAckHdr(BigDecimal pk, String poOrdNum) {
    // END 2019/02/21 M.Naito [QC#30277,MOD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        // START 2019/02/21 M.Naito [QC#30277,MOD]
//        ssmParam.put(BIND_PO_ORD_NUM, poAckHdrWrk.get(EDI_PO_ORD_NUM));
        ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);
        // END 2019/02/21 M.Naito [QC#30277,MOD]
        ssmParam.put(BIND_PO_ACK_HDR_PK, pk);

        List<Map<String, Object>> updatePoAckHdrList = ssmBatchClient.queryObjectList("getPoAckHdrPkList", ssmParam);

        for (int i = 0; i < updatePoAckHdrList.size(); i++) {

            Map<String, Object> updateAckHdrPkMap = updatePoAckHdrList.get(i);

            BigDecimal updateAckHdrPk = (BigDecimal) updateAckHdrPkMap.get(PO_ACK_HDR_PK);

            if (updateAckHdrPk != pk) {

                PO_ACK_HDRTMsg inMsg = new PO_ACK_HDRTMsg();

                ZYPEZDItemValueSetter.setValue(inMsg.poAckHdrPk, updateAckHdrPk);
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glCmpCd);

                inMsg = (PO_ACK_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {

                    isError = true;
                }

                ZYPEZDItemValueSetter.setValue(inMsg.poAckHdrLtstFlg, ZYPConstant.FLG_OFF_N);

                EZDTBLAccessor.update(inMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {

                    isError = true;

                }

            }

        }

        return isError;
    }

    /**
     * checkMandatoryCsaMdse
     * @param poAckHdrWrk
     * @param poAckDtlWrk
     * @return
     */
    private boolean checkMandatoryCsaMdse(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, String vndCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_VND_CD, vndCd);
        ssmParam.put(BIND_SPLY_ITEM_NUM, poAckDtlWrk.get(ORIG_VND_MDSE_CD));
        ssmParam.put(BIND_EFF_THRU_DT, EFF_THRU_DATE);
        ssmParam.put(BIND_EFF_FROM_DT, EFF_FROM_DATE);
        ssmParam.put(BIND_ASL_END_DT, EFF_THRU_DATE);
        ssmParam.put(BIND_ASL_START_DT, EFF_FROM_DATE);

        ssmParam.put(BIND_SLS_DATE, this.slsDt);

        if (!ZYPCommonFunc.hasValue(vndCd)) {

            StringBuilder sbKey = new StringBuilder();

            sbKey.append("EDI_PO_ORD_NUM=");
            sbKey.append(poAckDtlWrk.get(EDI_PO_ORD_NUM));
            sbKey.append(", EDI_PO_ORD_DTL_LINE_NUM=");
            sbKey.append(poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM));

            PO_ACK_ERR_WRKTMsg poAckErrWrkTMsg = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1288E, new String[] {ORIG_VND_MDSE_CD, PO_ACK_DTL_WRK, sbKey.toString() });

            errorList.add(poAckErrWrkTMsg);

            S21InfoLogOutput.println(NPAM1288E, new String[] {"ASL Master", "Supply Item Number", sbKey.toString() });

            isError = true;
        }

        return isError;
    }

    /**
     * searchPoAckHdrWrk
     * @return
     */
    private List<Map<String, Object>> searchPoAckHdrWrk() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_PO_ACK_UPD_PROC_FLG, ZYPConstant.FLG_OFF_N);
        ssmParam.put(BIND_ITRL_INTFC_ID, this.interfaceId);

        List<String> ackEdiProcStsCdList = new ArrayList<String>();

        ackEdiProcStsCdList.add(ACK_EDI_PROC_STS.SAVED);
        ackEdiProcStsCdList.add(ACK_EDI_PROC_STS.REPROCESS);
        ssmParam.put(BIND_ACK_EDI_PROC_STS_CD_LIST, ackEdiProcStsCdList);

        return ssmBatchClient.queryObjectList("searchPoAckHdrWrkList", ssmParam);

    }

    /**
     * searchPoAckDtlWrk
     * @return
     */
    private List<Map<String, Object>> searchPoAckDtlWrk(Map<String, Object> poAckHdrWrk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_PO_ACK_HDR_WRK_PK, poAckHdrWrk.get(PO_ACK_HDR_WRK_PK));

        return ssmBatchClient.queryObjectList("searchPoAckDtlWrkList", ssmParam);

    }

    /**
     * @param poAckHdrWrkTMsg
     * @param poAckDtlWrkMsg
     * @param str
     * @return
     */
    private String getPoMdseCmpsnTpCd(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, NPZC110001PMsg pmsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_PO_ORD_NUM, pmsg.poOrdNum_O.getValue());
        ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, pmsg.poDetailOutList.no(0).poOrdDtlLineNum.getValue());

        String poMdseCmpsnTpCd = (String) ssmBatchClient.queryObject("searchPoDtl", ssmParam);

        if (poMdseCmpsnTpCd == null) {

            StringBuilder sbKey = new StringBuilder();
            sbKey.append("EDI_PO_ORD_NUM=");
            sbKey.append(poAckDtlWrk.get(EDI_PO_ORD_NUM));
            sbKey.append(", EDI_PO_ORD_DTL_LINE_NUM=");
            sbKey.append(poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM));
            PO_ACK_ERR_WRKTMsg poAckErrWrkTMsg = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1236E, new String[] {sbKey.toString() });
            errorList.add(poAckErrWrkTMsg);
            isError = true;
            S21InfoLogOutput.println(NPAM1236E, new String[] {sbKey.toString() });

        }

        return poMdseCmpsnTpCd;
    }

    /**
     * searchPoDtl
     * @param pmsg
     * @return
     */
    private List<Map<String, Object>> searchPoDtl(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, NPZC110001PMsg pmsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(NPAB100001Constant.BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(NPAB100001Constant.BIND_PO_ORD_NUM, pmsg.poOrdNum_O.getValue());

        if (ZYPCommonFunc.hasValue(pmsg.poDetailOutList.no(0).poOrdDtlLineNum)) {

            ssmParam.put(NPAB100001Constant.BIND_PO_ORD_DTL_LINE_NUM, pmsg.poDetailOutList.no(0).poOrdDtlLineNum.getValue());

        } else {

            ssmParam.put(NPAB100001Constant.BIND_MDSE_CD, pmsg.poDetailOutList.no(0).mdseCd.getValue());
        }

        List<Map<String, Object>> poDtlList = ssmBatchClient.queryObjectList("searchPoDtlList", ssmParam);

        if (poDtlList == null) {

            StringBuilder subMsg = new StringBuilder("EDI PO#=").append((String) poAckHdrWrk.get(EDI_PO_ORD_NUM));
            subMsg.append(", EDI_Line#=").append(getSubMsgItemValue((String) poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM), digitEdiPoOrdDtlLineNum));
            subMsg.append(", PO#=").append(getSubMsgItemValue((String) pmsg.poOrdNum_O.getValue(), digitPoOrdNum));
            subMsg.append(", PO Line#=").append(getSubMsgItemValue((String) pmsg.poDetailOutList.no(0).poOrdDtlLineNum.getValue(), digitPoOrdDtlLineNum));
            subMsg.append(", Mdse Cd=").append(getSubMsgItemValue((String) poAckDtlWrk.get(ORIG_VND_MDSE_CD), digitOrigVndMdseCd));

            PO_ACK_ERR_WRKTMsg poAckErrWrkTMsg = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1236E, null);

            errorList.add(poAckErrWrkTMsg);
            isError = true;

            S21InfoLogOutput.println(NPAM1236E, new String[] {getSubMsgItemValue((String) poAckDtlWrk.get(EDI_PO_ORD_NUM), this.digitEdiPoOrdNum) });
        }

        return poDtlList;

    }

    /**
     * getSubMsgItemValue
     * @param str
     * @param digit
     * @return
     */
    private String getSubMsgItemValue(String str, int digit) {

        if (!ZYPCommonFunc.hasValue(str)) {

            return ZYPCommonFunc.leftPad("", digit, HYPHEN);

        }

        return str;
    }

    /**
     * checkMandatoryHdrItem
     * @param poAckHdrWrk
     * @return
     */
    private boolean checkMandatoryHdrItem(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckdtlWrk) {

        StringBuilder sbKey = new StringBuilder();

        sbKey.append("EDI_PO_ORD_NUM=");

        sbKey.append((String) poAckHdrWrk.get(EDI_PO_ORD_NUM));

        for (String itemName : MANDATORY_HDR_ITEMS) {

            if (poAckHdrWrk.get(itemName) == null) {

                PO_ACK_ERR_WRKTMsg poAckErrWrkTMsg = setPoAckErr(poAckHdrWrk, poAckdtlWrk, NPAM1321E, new String[] {itemName, PO_ACK_HDR_WRK, sbKey.toString() });

                errorList.add(poAckErrWrkTMsg);

                S21InfoLogOutput.println(NPAM1321E, new String[] {itemName, PO_ACK_HDR_WRK, sbKey.toString() });

                isError = true;

            }

        }

        return isError;
    }

    /**
     * getMdseCdFromAsl. QC#52579 Mod
     * @param vndCd
     * @param vndMdseCd
     * @return List<Map<String, Object>>
     */
    // START 2019/01/11 M.Naito [QC#29694,MOD]
//    private Map<String, Object> getMdseCdFromAsl(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, String vndCd, String vndMdseCd) {
    private List<Map<String, Object>> getMdseCdFromAsl(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, String vndCd, String vndMdseCd, String date) {

//        Map<String, Object> mdseCdMap = new HashMap<String, Object>();
        List<Map<String, Object>> mdseCdList = null;

        if (ZYPCommonFunc.hasValue(vndMdseCd)) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
            ssmParam.put(BIND_VND_CD, vndCd);
            ssmParam.put(BIND_SPLY_ITEM_NUM, vndMdseCd);
            ssmParam.put(BIND_EFF_THRU_DT, EFF_THRU_DATE);
            ssmParam.put(BIND_EFF_FROM_DT, EFF_FROM_DATE);
            ssmParam.put(BIND_ASL_END_DT, EFF_THRU_DATE);
            ssmParam.put(BIND_ASL_START_DT, EFF_FROM_DATE);
            // QC#52579
            if (ZYPCommonFunc.hasValue(date)) {
                ssmParam.put(BIND_SLS_DATE, date);
            }

//            mdseCdMap = (Map<String, Object>) ssmBatchClient.queryObject("getMdseCdFromAsl", ssmParam);
            mdseCdList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMdseCdFromAsl", ssmParam);

            // START 2018/11/14 M.Naito [QC#29047,ADD]
            // check TST / Impreso Inc
//            if (mdseCdMap == null) {
            if (mdseCdList == null || mdseCdList.isEmpty()) {
                String vndSysTpCd = getVndSysTpCd(vndCd);
                if (ZYPCommonFunc.hasValue(vndSysTpCd) && VND_SYS_TP.TST_IMPRESO.equals(vndSysTpCd)) {
                    ssmParam.put(BIND_TST_IMPRESO_FLG, ZYPConstant.FLG_ON_Y);
                }
//                mdseCdMap = (Map<String, Object>) ssmBatchClient.queryObject("getMdseCdFromAsl", ssmParam);
                mdseCdList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMdseCdFromAsl", ssmParam);
            }
            // END 2018/11/14 M.Naito [QC#29047,ADD]
        } else {
            StringBuilder subMsg = new StringBuilder("EDI PO#=").append((String) poAckHdrWrk.get(EDI_PO_ORD_NUM));
            subMsg.append(", EDI_Line#=").append(getSubMsgItemValue((String) poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM), digitEdiPoOrdDtlLineNum));
            PO_ACK_ERR_WRKTMsg poAckErrWrkTMsg = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1288E, new String[] {"PO_ACK_DTL_WRK", getSubMsgItemValue((String) poAckDtlWrk.get(ORIG_VND_MDSE_CD), digitOrigVndMdseCd), subMsg.toString() });
            errorList.add(poAckErrWrkTMsg);
            isError = true;
            S21InfoLogOutput.println(NPAM1288E, new String[] {"PO_ACK_DTL_WRK", getSubMsgItemValue((String) poAckDtlWrk.get(ORIG_VND_MDSE_CD), digitOrigVndMdseCd), subMsg.toString() });
        }

        if (mdseCdList == null || mdseCdList.isEmpty()) {
            StringBuilder subMsg = new StringBuilder("EDI PO#=").append((String) poAckHdrWrk.get(EDI_PO_ORD_NUM));
            subMsg.append(", EDI_Line#=").append(getSubMsgItemValue((String) poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM), digitEdiPoOrdDtlLineNum));
            PO_ACK_ERR_WRKTMsg poAckErrWrkTMsg = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1288E, new String[] {"ASL_DTL", getSubMsgItemValue(vndMdseCd, digitOrigVndMdseCd), subMsg.toString() });
            errorList.add(poAckErrWrkTMsg);
            isError = true;
            S21InfoLogOutput.println(NPAM1288E, new String[] {"ASL_DTL", getSubMsgItemValue(vndMdseCd, digitOrigVndMdseCd), subMsg.toString() });
        }

        return mdseCdList;

//        if (mdseCdMap == null) {
//
//            StringBuilder subMsg = new StringBuilder("EDI PO#=").append((String) poAckHdrWrk.get(EDI_PO_ORD_NUM));
//            subMsg.append(", EDI_Line#=").append(getSubMsgItemValue((String) poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM), digitEdiPoOrdDtlLineNum));
//            // QC#29494
//            PO_ACK_ERR_WRKTMsg poAckErrWrkTMsg = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1288E, new String[] {"ASL_DTL", getSubMsgItemValue(vndMdseCd, digitOrigVndMdseCd), subMsg.toString() });
//            errorList.add(poAckErrWrkTMsg);
//            isError = true;
//            S21InfoLogOutput.println(NPAM1288E, new String[] {"ASL_DTL", getSubMsgItemValue(vndMdseCd, digitOrigVndMdseCd), subMsg.toString() });
//
//        } else if (mdseCdMap.isEmpty()) {
//
//            StringBuilder subMsg = new StringBuilder("EDI PO#=").append((String) poAckHdrWrk.get(EDI_PO_ORD_NUM));
//            subMsg.append(", EDI_Line#=").append(getSubMsgItemValue((String) poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM), digitEdiPoOrdDtlLineNum));
//            // QC#29494
//            PO_ACK_ERR_WRKTMsg poAckErrWrkTMsg = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1288E, new String[] {"PO_ACK_DTL_WRK", getSubMsgItemValue((String) poAckDtlWrk.get(ORIG_VND_MDSE_CD), digitOrigVndMdseCd), subMsg.toString() });
//            errorList.add(poAckErrWrkTMsg);
//            isError = true;
//            S21InfoLogOutput.println(NPAM1288E, new String[] {"PO_ACK_DTL_WRK", getSubMsgItemValue((String) poAckDtlWrk.get(ORIG_VND_MDSE_CD), digitOrigVndMdseCd), subMsg.toString() });
//        }
//
//        return mdseCdMap;
//
    }
    // END 2019/01/11 M.Naito [QC#29694,MOD]

    /**
     * getMdseNm
     * @param mdseCd
     * @return mdseCd
     */
    private String getMdseNm(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, String mdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_MDSE_CD, mdseCd);

        List<String> rgtnStsList = new ArrayList<String>();

        rgtnStsList.add(RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING);
        rgtnStsList.add(RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put(BIND_RGTN_STS_LIST, rgtnStsList);

        String mdseNm = (String) ssmBatchClient.queryObject("getMdseNm", ssmParam);

        if (mdseNm == null) {

            // START 2019/08/09 M.Naito [QC#51601,MOD]
//            PO_ACK_ERR_WRKTMsg poAckErrWrkTMsg = setPoAckErr(poAckHdrWrk, poAckHdrWrk, NPZM0147E, null);
            PO_ACK_ERR_WRKTMsg poAckErrWrkTMsg = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPZM0147E, null);
            // END 2019/08/09 M.Naito [QC#51601,MOD]

            errorList.add(poAckErrWrkTMsg);

            isError = true;

            S21InfoLogOutput.println(NPZM0147E, new String[] {getSubMsgItemValue((String) poAckDtlWrk.get(EDI_PO_ORD_NUM), this.digitEdiPoOrdNum) });

        }

        return mdseNm;
    }

    /**
     * convert PoDtlData From EdiPoData
     * @param ediPoOrdNum String
     * @param poOrdNum String
     * @return PO_ACK_HDR_WRKTMsg
     */
    private NPZC110001PMsg convertPoDataFromPoAckWrk(String ediPoOrdNum, String poOrdNum) {

        NPZC110001 apiGetPoLineFromEdi = new NPZC110001();

        NPZC110001PMsg apiGetPoLineFromEdiParam = new NPZC110001PMsg();

        ZYPEZDItemValueSetter.setValue(apiGetPoLineFromEdiParam.glblCmpyCd, this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(apiGetPoLineFromEdiParam.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(apiGetPoLineFromEdiParam.ediPoOrdNum, ediPoOrdNum);
        ZYPEZDItemValueSetter.setValue(apiGetPoLineFromEdiParam.poOrdNum_I, poOrdNum);

        apiGetPoLineFromEdi.execute(apiGetPoLineFromEdiParam, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(apiGetPoLineFromEdiParam)) {

            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiGetPoLineFromEdiParam);

            for (String xxMsgId : xxMsgIdList) {

                S21InfoLogOutput.println(xxMsgId);

            }

            StringBuilder subMsg = new StringBuilder("EDI_PO_ORD_NUM=").append(ediPoOrdNum);

            subMsg.append("PO_ORD_NUM=").append(poOrdNum);

            S21InfoLogOutput.println(NPAM1323E, new String[] {"Get Po Line From EDI", subMsg.toString() });

        }

        return apiGetPoLineFromEdiParam;
    }

    /**
     * @param poAckHdrWrk
     * @param poAckDtlWrk
     * @param pmsg
     * @param List<String>
     * @return
     */
    // START 2019/01/11 M.Naito [QC#29694,MOD]
//    private NPZC110001PMsg callApiGetPoLineFromEdi(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, NPZC110001PMsg pmsg, String mdseCd) {
    private NPZC110001PMsg callApiGetPoLineFromEdi(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, NPZC110001PMsg pmsg, List<String> mdseCdList) {

        boolean getPoLineErrorFlg = true;
        boolean poAckErrorFlg = true;
        boolean poAckWarningFlg = true;
        String trgtMdseCd  = null;

        NPZC110001 apiGetPoLineFromEdi = new NPZC110001();

        for (String mdseCd : mdseCdList) {
            pmsg.poDetailOutList.no(0).clear();
            ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, this.glCmpCd);
            ZYPEZDItemValueSetter.setValue(pmsg.slsDt, this.slsDt);
            ZYPEZDItemValueSetter.setValue(pmsg.ediPoOrdNum, (String) poAckHdrWrk.get(EDI_PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.poOrdNum_I, (String) poAckHdrWrk.get(PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.poDetailInList.no(0).ediPoOrdDtlLineNum, (String) poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.poDetailInList.no(0).poOrdDtlLineNum, (String) poAckDtlWrk.get(PO_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.poDetailInList.no(0).mdseCd, mdseCd);

            pmsg.poDetailInList.setValidCount(1);

            apiGetPoLineFromEdi.execute(pmsg, ONBATCH_TYPE.BATCH);

            if (!S21ApiUtil.isXxMsgId(pmsg)) {
                getPoLineErrorFlg = false;
            }

            if (ZYPCommonFunc.hasValue(pmsg.poDetailOutList.no(0).poOrdDtlLineNum)) {
                poAckErrorFlg = false;
                if (!ZYPCommonFunc.hasValue(pmsg.poDetailOutList.no(0).xxMsgId)) {
                    poAckWarningFlg = false;
                    trgtMdseCd = mdseCd;
                    break;
                }
            }
        }

        if (getPoLineErrorFlg) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pmsg);

            for (String xxMsgId : xxMsgIdList) {

                S21InfoLogOutput.println(xxMsgId);
            }

            StringBuilder subMsg = new StringBuilder("EDI_PO_ORD_NUM=").append((String) poAckHdrWrk.get(EDI_PO_ORD_NUM));

            subMsg.append("PO_ORD_NUM=").append((String) poAckHdrWrk.get(PO_ORD_NUM));

            S21InfoLogOutput.println(NPAM1323E, new String[] {"Get Po Line From EDI", subMsg.toString() });

        }

//        for (int i = 0; i < pmsg.poDetailOutList.getValidCount(); i++) {
//
//            if (!ZYPCommonFunc.hasValue(pmsg.poDetailOutList.no(i).poOrdDtlLineNum)) {
        if (poAckErrorFlg) {

            StringBuilder subMsg = new StringBuilder("EDI PO#=").append((String) poAckHdrWrk.get(EDI_PO_ORD_NUM));
            subMsg.append(", EDI_Line#=").append(getSubMsgItemValue((String) poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM), digitEdiPoOrdDtlLineNum));
            subMsg.append(", PO#=").append(getSubMsgItemValue((String) pmsg.poOrdNum_O.getValue(), digitPoOrdNum));
//                subMsg.append(", PO Line#=").append(getSubMsgItemValue((String) pmsg.poDetailOutList.no(i).poOrdDtlLineNum.getValue(), digitPoOrdDtlLineNum));
            subMsg.append(", PO Line#=").append(getSubMsgItemValue(null, digitPoOrdDtlLineNum));
            subMsg.append(", Mdse Cd=").append(getSubMsgItemValue((String) poAckDtlWrk.get(ORIG_VND_MDSE_CD), digitOrigVndMdseCd));

            PO_ACK_ERR_WRKTMsg poAckError = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1236E, new String[] {subMsg.toString() });

            errorList.add(poAckError);

            S21InfoLogOutput.println(NPAM1236E, new String[] {subMsg.toString() });

        } else {

//                if (ZYPCommonFunc.hasValue(pmsg.poDetailOutList.no(i).xxMsgId)) {
            if (poAckWarningFlg) {

                PO_ACK_ERR_WRKTMsg poAckWarning = null;

                StringBuilder subMsg = new StringBuilder("EDI PO#=").append((String) poAckHdrWrk.get(EDI_PO_ORD_NUM));
                subMsg.append(", EDI_Line#=").append(getSubMsgItemValue((String) poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM), digitEdiPoOrdDtlLineNum));
                subMsg.append(", PO#=").append(getSubMsgItemValue((String) pmsg.poOrdNum_O.getValue(), digitPoOrdNum));
//                    subMsg.append(", PO Line#=").append(getSubMsgItemValue((String) pmsg.poDetailOutList.no(i).poOrdDtlLineNum.getValue(), digitPoOrdDtlLineNum));
                subMsg.append(", PO Line#=").append(getSubMsgItemValue(pmsg.poDetailInList.no(0).poOrdDtlLineNum.getValue(), digitPoOrdDtlLineNum));
                subMsg.append(", Mdse Cd=").append(getSubMsgItemValue((String) poAckDtlWrk.get(ORIG_VND_MDSE_CD), digitOrigVndMdseCd));

                poAckWarning = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1281W, new String[] {subMsg.toString() });

                warningList.add(poAckWarning);
            }
        }

        if (ZYPCommonFunc.hasValue(trgtMdseCd)) {
            ZYPEZDItemValueSetter.setValue(pmsg.poDetailInList.no(0).mdseCd, trgtMdseCd);
        }
        return pmsg;
    }
    // END 2019/01/11 M.Naito [QC#29694,MOD]

    /**
     * sendErrorMail
     * @return
     */
    private void sendErrorMail(Map<String, Object> poAckHdrWrk, POTMsg outPoTMsg, String errId) {

    	// QC#52184 Mod Start
    	String msg = null;
        StringBuilder subMsg = new StringBuilder("EDI_PO_ORD_NUM=").append((String) poAckHdrWrk.get(EDI_PO_ORD_NUM));
        if (errId == null) {
        	msg = subMsg.toString();
        	S21InfoLogOutput.println(NPAM1530E, new String[] {PO_ACK_DTL_WRK, PO_ACK_HDR_WRK, subMsg.toString() });
        } else {
        	if (errId.equals(NSBM0027E)) {
        		msg = S21MessageFunc.clspGetMessage(errId, new String[] {"PO" });
        		S21InfoLogOutput.println(errId, new String[] {"PO" });
        	} else {
        		msg = S21MessageFunc.clspGetMessage(errId, new String[] { });
            	S21InfoLogOutput.println(errId, new String[] { });
        	}
        }

        rollback();

        PO_ACK_ERR_WRKTMsg poAckError = null;
        if (errId == null) {
        	poAckError = setPoAckErr(poAckHdrWrk, null, NPAM1530E, new String[] {msg });
        } else {
        	poAckError = setPoAckErr(poAckHdrWrk, null, errId, new String[] {msg });
        }
        // QC#52184 Mod End
        errorList.add(poAckError);
        sendMail(MAIL_TEMPLATE_ID_04, poAckHdrWrk, msg, outPoTMsg);

    }

    /**
     * sendMail
     * @param mailTemp
     * @param poAckHdrWrk
     * @param msg
     */
    private void sendMail(String mailTemp, Map<String, Object> poAckHdrWrk, String msg, POTMsg outPoTMsg) {

        // mail Init
        S21Mail mail = new S21Mail(this.glCmpCd);
        // START 2023/04/13 TZ.Win [QC#61269,ADD]
        poAckHdrWrk.put(VND_CD, outPoTMsg.vndCd.getValue());
        poAckHdrWrk.put(PO_ORD_NUM, outPoTMsg.poOrdNum.getValue());
        // END 2023/04/13 TZ.Win [QC#61269,ADD]

        // Get From address
        S21MailGroup groupFrom = new S21MailGroup(this.glCmpCd, MAIL_GROUP_ID_FROM);

        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        S21MailAddress addrFrom = addrFromList.get(0);

        if (!addrFromList.isEmpty()) {

            mail.setFromAddress(addrFromList.get(0));

        }

        // Get to address
        S21MailGroup groupTo = new S21MailGroup(this.glCmpCd, MAIL_GROUP_ID_TO);

        List<S21MailAddress> toAddrList = null;

        if (MAIL_TEMPLATE_ID_04.equals(mailTemp)) {

            groupTo.setMailKey1(MAIL_KEY_TO);
            toAddrList = groupTo.getMailAddress();

        } else {

            // START 2023/03/27 TZ.Win [QC#61269,ADD]
            RCV_ASN_VNDTMsg rcvAsnVnd = new RCV_ASN_VNDTMsg();
            
            ZYPEZDItemValueSetter.setValue(rcvAsnVnd.glblCmpyCd, this.glCmpCd);
            ZYPEZDItemValueSetter.setValue(rcvAsnVnd.rcvAsnVndCd, (String) poAckHdrWrk.get(VND_CD));
            rcvAsnVnd = (RCV_ASN_VNDTMsg) EZDTBLAccessor.findByKey(rcvAsnVnd);

            if (rcvAsnVnd == null || rcvAsnVnd.rcvErrEmlAddr.getValue() == null || rcvAsnVnd.rcvErrEmlAddr.getValue().equals("")) {
            // END 2023/03/27 TZ.Win [QC#61269,ADD]

                toAddrList = getAddressList((String) poAckHdrWrk.get(PO_ORD_NUM));

            // START 2023/03/27 TZ.Win [QC#61269,ADD]
            } else {

                List<String> rcvErrEmlAddrList = Arrays.asList(rcvAsnVnd.rcvErrEmlAddr.getValue().split(","));
                toAddrList = new ArrayList<S21MailAddress>(rcvErrEmlAddrList.size());
                for (int i = 0; i < rcvErrEmlAddrList.size(); i++) {
                    S21MailAddress address = new S21MailAddress(this.glCmpCd, rcvErrEmlAddrList.get(i));
                    toAddrList.add(address);
                }
            }
            // END 2023/03/27 TZ.Win [QC#61269,ADD]
        }

        if (toAddrList == null || toAddrList.isEmpty()) {

            throw new S21AbendException(NPAM1265E);

        }

        if (MAIL_TEMPLATE_ID_01.equals(mailTemp)) {

            // Get mail template
            S21MailTemplate template = new S21MailTemplate(this.glCmpCd, MAIL_TEMPLATE_ID_01);

            template.setTemplateParameter(MAIL_FIELD_PO_ORD_NUM, getSubMsgItemValue((String) outPoTMsg.poOrdNum.getValue(), digitPoOrdNum));
            template.setTemplateParameter(MAIL_FIELD_EDI_PO_ORD_NUM, getSubMsgItemValue((String) poAckHdrWrk.get(EDI_PO_ORD_NUM), digitEdiPoOrdNum));
            template.setTemplateParameter(MAIL_FIELD_SUBMIT_DATE, ZYPDateUtil.DateFormatter(outPoTMsg.poSubmtTs.getValue(), TS_FORMAT_EDI_RCV_DATE_TS, TS_FORMAT_FOR_MAIL));
            template.setTemplateParameter(MAIL_FIELD_USER_ID, outPoTMsg.poSubmtPsnCd.getValue());
            S21_PSNTMsg s21PsnOut = getS21PsnNm(outPoTMsg.poSubmtPsnCd.getValue());
            template.setTemplateParameter(MAIL_FIELD_USER_NM, s21PsnOut.psnFirstNm.getValue() + " " + s21PsnOut.psnLastNm.getValue());
            template.setTemplateParameter(MAIL_FIELD_VND_NM, outPoTMsg.vndNm.getValue());
            mail.setMailTemplate(template);

        } else if (MAIL_TEMPLATE_ID_03.equals(mailTemp)) {

            // Get mail template
            S21MailTemplate template = new S21MailTemplate(this.glCmpCd, MAIL_TEMPLATE_ID_03);

            template.setTemplateParameter(MAIL_FIELD_EDI_PO_ORD_NUM, getSubMsgItemValue((String) poAckHdrWrk.get(EDI_PO_ORD_NUM), digitEdiPoOrdNum));

            if (outPoTMsg != null) {
                template.setTemplateParameter(MAIL_FIELD_PO_ORD_NUM, getSubMsgItemValue((String) outPoTMsg.poOrdNum.getValue(), digitPoOrdNum));
            }

            // 2018/12/05 QC#29494 Del Start
//            template.setTemplateParameter(MAIL_FIELD_EDI_STS, (String) poAckHdrWrk.get(ACK_EDI_PROC_STS_CD));
//
//            if (ZYPCommonFunc.hasValue((String) poAckHdrWrk.get(EDI_RCV_DATE_TS))) {
//                template.setTemplateParameter(MAIL_FIELD_RCV_DATE, ZYPDateUtil.DateFormatter((String) poAckHdrWrk.get(EDI_RCV_DATE_TS), TS_FORMAT_EDI_RCV_DATE_TS, TS_FORMAT_FOR_MAIL));
//            }
//
//            template.setTemplateParameter(MAIL_FIELD_INTERFACE_ID, interfaceId);
//            template.setTemplateParameter(MAIL_FIELD_ERR_MSG, msg);
            // 2018/12/05 QC#29494 Del End
            // 2018/12/05 QC#29494 Add Start
            if (ZYPCommonFunc.hasValue((String) poAckHdrWrk.get(VND_CD))) {
                template.setTemplateParameter(MAIL_FIELD_VND_CD, (String) poAckHdrWrk.get(VND_CD));
            }
            Map<String, Object> errordataCash = new HashMap<String, Object>();
            String crlf = System.getProperty("line.separator");
            StringBuffer detailMsg = new StringBuffer();
            StringBuffer errMsg = new StringBuffer();
            for (PO_ACK_ERR_WRKTMsg error : errorList) {

                BigDecimal poAckHdrWrkPk = error.poAckHdrWrkPk.getValue();

                BigDecimal poAckDtlWrkPk = error.poAckDtlWrkPk.getValue();
                String batErrMsgTxt = error.batErrMsgTxt.getValue();

                String cacheKey = poAckHdrWrkPk.toString() + poAckDtlWrkPk.toString() + batErrMsgTxt;

                if (errordataCash.containsKey(cacheKey)) {
                    continue;
                }
                errordataCash.put(cacheKey, cacheKey);

                StringBuffer line = new StringBuffer();

                PO_ACK_DTL_WRKTMsg dtlMsg = new PO_ACK_DTL_WRKTMsg();

                ZYPEZDItemValueSetter.setValue(dtlMsg.glblCmpyCd, this.glCmpCd);
                ZYPEZDItemValueSetter.setValue(dtlMsg.poAckDtlWrkPk, poAckDtlWrkPk);

                dtlMsg = (PO_ACK_DTL_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(dtlMsg);

                line.append(NLGC001001.convFixedLgLeftAlign(dtlMsg.ediPoOrdDtlLineNum.getValue(), LINE_SPASE_12));
                line.append(NLGC001001.convFixedLgLeftAlign(dtlMsg.mdseCd.getValue(), LINE_SPASE_18));

                // po qty
                String ordQty = " ";
                BigDecimal poQty = getPoQty(dtlMsg);

                if (ZYPCommonFunc.hasValue(poQty)) {
                    ordQty = poQty.toString();
                }
                line.append(NLGC001001.convFixedLgLeftAlign(ordQty, LINE_SPASE_9));

                // po ack Qty
                String origOrdQty = " ";
                if (ZYPCommonFunc.hasValue(dtlMsg.origOrdQty.getValue())) {
                    origOrdQty = dtlMsg.origOrdQty.getValue().toString();
                }
                line.append(NLGC001001.convFixedLgLeftAlign(origOrdQty, LINE_SPASE_9));

                if (errMsg.length() > 0) {
                    errMsg.append(S21MailTemplate.ML_LINE_SEPARATOR);
                    errMsg.append(INDENT);
                }

                errMsg.append(batErrMsgTxt);

                // carriage return & line feed
                line.append(crlf);

                // line end.
                detailMsg.append(line);

            }
            template.setTemplateParameter(MAIL_FIELD_ERR_MSG, errMsg.toString());
            template.setTemplateParameter(MAIL_FIELD_ERR_DETAIL, detailMsg.toString());
            // 2018/12/05 QC#29494 Add End
            mail.setMailTemplate(template);

        } else if (MAIL_TEMPLATE_ID_04.equals(mailTemp)) {

            // Get mail template
            S21MailTemplate template = new S21MailTemplate(this.glCmpCd, MAIL_TEMPLATE_ID_04);

            template.setTemplateParameter(MAIL_FIELD_EDI_PO_ORD_NUM, (String) poAckHdrWrk.get(EDI_PO_ORD_NUM));

            if (outPoTMsg != null) {
                template.setTemplateParameter(MAIL_FIELD_PO_ORD_NUM, getSubMsgItemValue((String) outPoTMsg.poOrdNum.getValue(), digitPoOrdNum));
            }

            template.setTemplateParameter(MAIL_FIELD_ERR_MSG, msg);
            mail.setMailTemplate(template);
        }

        mail.setToAddressList(toAddrList);
        mail.setFromAddress(addrFrom);
        mail.postMail();

    }

    /**
     * getS21PsnNm
     * @param poSubmitPsnCd
     * @return
     */
    private S21_PSNTMsg getS21PsnNm(String poSubmitPsnCd) {

        S21_PSNTMsg s21PsnMsgOut = new S21_PSNTMsg();

        s21PsnMsgOut.glblCmpyCd.setValue(this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(s21PsnMsgOut.psnCd, poSubmitPsnCd);

        s21PsnMsgOut = (S21_PSNTMsg) EZDTBLAccessor.findByKey(s21PsnMsgOut);

        s21PsnMsgOut = new S21_PSNTMsg();

        return s21PsnMsgOut;
    }

    /**
     * getAddressList
     * @param poOrdNum
     * @return
     */
    private List<S21MailAddress> getAddressList(String poOrdNum) {
        NPZC106001 getMailAddrForPo = new NPZC106001();
        NPZC106001PMsg apiParam = new NPZC106001PMsg();
        ZYPEZDItemValueSetter.setValue(apiParam.glblCmpyCd, this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(apiParam.poOrdNum, poOrdNum);
        getMailAddrForPo.execute(apiParam, S21ApiInterface.ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiParam)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiParam);
            for (String xxMsgId : xxMsgIdList) {
                S21InfoLogOutput.println(xxMsgId);
            }
        }
        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>(apiParam.EmlAddrList.getValidCount());
        for (int i = 0; i < apiParam.EmlAddrList.getValidCount(); i++) {
            S21MailAddress address = new S21MailAddress(this.glCmpCd, apiParam.EmlAddrList.no(i).emlAddr.getValue());
            addrToList.add(address);
        }
        return addrToList;
    }

    /**
     * @param poAckHdrWrk
     * @param poAckDtlWrk
     * @param msgId
     * @return
     */
    private PO_ACK_ERR_WRKTMsg setPoAckErr(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, String msgId) {

        return setPoAckErrWrk(poAckHdrWrk, poAckDtlWrk, S21MessageFunc.clspGetMessage(msgId));

    }

    /**
     * @param poAckHdrWrk
     * @param poAckDtlWrk
     * @param msg
     * @return
     */
    private PO_ACK_ERR_WRKTMsg setPoAckErrWrk(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, String msg) {

        PO_ACK_ERR_WRKTMsg poAckError = new PO_ACK_ERR_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(poAckError.glblCmpyCd, this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(poAckError.poAckHdrWrkPk, (BigDecimal) poAckHdrWrk.get(PO_ACK_HDR_WRK_PK));
        if (poAckDtlWrk != null) {

            ZYPEZDItemValueSetter.setValue(poAckError.poAckDtlWrkPk, setPoAckDtlWrk((BigDecimal) poAckDtlWrk.get(PO_ACK_DTL_WRK_PK)));

        }
        ZYPEZDItemValueSetter.setValue(poAckError.ediPoOrdNum, (String) poAckHdrWrk.get(EDI_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(poAckError.batErrMsgTxt, msg);

        return poAckError;
    }

    /**
     * checkOrdQty
     * @param poAckHdrWrk
     * @param poAckDtlWrk
     * @param poDtlList
     * @param poAckDtlWrkList
     */
    private void checkOrdQty(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk, List<Map<String, Object>> poDtlList, List<Map<String, Object>> poAckDtlWrkList, NPZC110001PMsg apiGetPoLineFromEdiParam) {

        PO_ACK_ERR_WRKTMsg poAckWarning = null;

        Map<String, Object> poDtl = poDtlList.get(0);

        // QC#18283 Mod.
        // START 2019/02/18 M.Naito [QC#30276,DEL]
//        BigDecimal sumOrdQty = BigDecimal.ZERO;
        // END 2019/02/18 M.Naito [QC#30276,DEL]
//        String poOrdDtlLineNum = (String) poDtl.get(PO_ORD_DTL_LINE_NUM);
        BigDecimal poOrdQty = (BigDecimal) poDtl.get(PO_QTY);
//        String refEdiPoOrdDtlLineNum = apiGetPoLineFromEdiParam.poDetailInList.no(0).ediPoOrdDtlLineNum.getValue();
//        String refPoOrdDtlLineNum = apiGetPoLineFromEdiParam.poDetailOutList.no(0).poOrdDtlLineNum.getValue();

        // START 2019/02/18 M.Naito [QC#30276,MOD]
//        for (int k = 0; k < poAckDtlWrkList.size(); k++) {
//
//            Map<String, Object> poAckDtlWrkTemp = poAckDtlWrkList.get(k);
//            String ediPoOrdDtlLineNum = (String) poAckDtlWrkTemp.get(EDI_PO_ORD_DTL_LINE_NUM);

            // if (((String)
            // poDtl.get(PO_ORD_DTL_LINE_NUM)).equals(poAckDtlWrkTemp.get(EDI_PO_ORD_DTL_LINE_NUM)))
            // {
            //
            // sumOrdQty = sumOrdQty.add((BigDecimal)
            // poAckDtlWrkTemp.get(ORD_QTY));
            //
            // }
            // QC#28655 mod start

            // QC#28679 mod start
            // QC#28834 mod start
//            if (IF_ID_NPAA0031.equals(this.interfaceId)) {
//                if ((ZYPCommonFunc.hasValue(ediPoOrdDtlLineNum) && ediPoOrdDtlLineNum.equals(refEdiPoOrdDtlLineNum)) && poOrdDtlLineNum.equals(refPoOrdDtlLineNum)) {
//                    sumOrdQty = ((BigDecimal) poAckDtlWrkTemp.get(ORIG_ORD_QTY));
//                    if (poOrdQty.compareTo(sumOrdQty) != 0) {
                    BigDecimal origOrdQty = ((BigDecimal) poAckDtlWrk.get(ORIG_ORD_QTY));
                    if (ZYPCommonFunc.hasValue(origOrdQty) && poOrdQty.compareTo(origOrdQty) != 0) {
                        poAckWarning = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1282W);
                        warningList.add(poAckWarning);
                    }
//                }
//                // QC#28679 mod end
//            } else {
//
//                if ((ZYPCommonFunc.hasValue(ediPoOrdDtlLineNum) && ediPoOrdDtlLineNum.equals(refEdiPoOrdDtlLineNum)) && poOrdDtlLineNum.equals(refPoOrdDtlLineNum)) {
//                    // QC#28655 mod end
//
//                    sumOrdQty = sumOrdQty.add((BigDecimal) poAckDtlWrkTemp.get(ORD_QTY));
//
//                } else if (((String) poDtl.get(PO_ORD_DTL_LINE_NUM)).equals(poAckDtlWrkTemp.get(EDI_PO_ORD_DTL_LINE_NUM))) {
//
//                    sumOrdQty = sumOrdQty.add((BigDecimal) poAckDtlWrkTemp.get(ORD_QTY));
//
//                } else {
//                    continue;
//                }
//
//                // if (!poDtl.get(PO_QTY).equals(sumOrdQty)) {
//                if (poOrdQty.compareTo(sumOrdQty) != 0) {
//
//                    poAckWarning = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1282W);
//
//                    warningList.add(poAckWarning);
//
//                }
//            }
//            // QC#28834 mod end
//        }
        // END 2019/02/18 M.Naito [QC#30276,MOD]

    }

    /**
     * editDate
     * @param targetDate
     * @param poTMsg
     * @return targetDate
     */
    private String editDate(String targetDate, POTMsg poTMsg) {

        if (ZYPCommonFunc.hasValue(targetDate) && ZYPDateUtil.compare(targetDate, ZYPDateUtil.addDays(poTMsg.poApvlDt.getValue(), this.intervalDays)) >= 0) {

            return null;
        }

        return targetDate;
    }

    /**
     * @param poOrdNum
     * @param poOrdDtlLineNum
     * @return
     */
    private PO_DTLTMsg getPoDtlData(String poOrdNum, String poOrdDtlLineNum) {

        PO_DTLTMsg inPoDtlTMsg = new PO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inPoDtlTMsg.glblCmpyCd, this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(inPoDtlTMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(inPoDtlTMsg.poOrdDtlLineNum, poOrdDtlLineNum);

        return (PO_DTLTMsg) EZDTBLAccessor.findByKey(inPoDtlTMsg);
    }

    /**
     * costUpdateTargetInfo
     * @param poOrdNum
     * @param poOrdDtlLineNum
     * @return
     */
    private Map<String, Object> costUpdateTargetInfo(String poOrdNum, String poOrdDtlLineNum) {

        HashMap<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);
        ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);

        return (Map<String, Object>) ssmBatchClient.queryObject("searchCostUpdateTarget", ssmParam);

    }

    /**
     * costUpdateTargetInfo
     * @param poOrdNum
     * @param poOrdDtlLineNum
     * @return
     */
    private List<Map<String, Object>> searchSetChildrenTarget(String poOrdNum, String poOrdDtlLineNum) {

        HashMap<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);
        ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("searchSetChildrenTarget", ssmParam);

    }

    /**
     * checkMandatoryDtlItem
     * @param poAckHdrWrk
     * @param poAckDtlWrk
     * @return
     */
    private boolean checkMandatoryDtlItem(Map<String, Object> poAckHdrWrk, Map<String, Object> poAckDtlWrk) {

        StringBuilder sbKey = new StringBuilder();

        sbKey.append("EDI_PO_ORD_NUM=");
        sbKey.append(poAckDtlWrk.get(EDI_PO_ORD_NUM));
        sbKey.append(", EDI_PO_ORD_DTL_LINE_NUM=");
        sbKey.append(poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM));

        for (String itemName : MANDATORY_DTL_ITEMS) {
            if (poAckDtlWrk.get(itemName) == null) {
                PO_ACK_ERR_WRKTMsg poAckErrWrkTMsg = setPoAckErr(poAckHdrWrk, poAckDtlWrk, NPAM1321E, new String[] {itemName, PO_ACK_DTL_WRK, sbKey.toString() });
                errorList.add(poAckErrWrkTMsg);
                S21InfoLogOutput.println(NPAM1321E, new String[] {itemName, PO_ACK_DTL_WRK, sbKey.toString() });
                isError = true;
            }

        }
        return isError;
    }

    /**
     * @param poAckHdrWrk
     * @param poTmsg
     * @param poAckHdrWrkTMsg
     * @return
     */
    private boolean updatePo(Map<String, Object> poAckHdrWrk, POTMsg poTmsg) {

        ZYPEZDItemValueSetter.setValue(poTmsg.vndIssOrdNum, (String) poAckHdrWrk.get(VND_CPO_ORD_NUM));

        EZDTBLAccessor.updateSelectionField(poTmsg, new String[] {MAP_KEY_VND_ISS_ORD_NUM });

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poTmsg.getReturnCode())) {
            StringBuilder sb = new StringBuilder();
            sb.append("TABLE:PO PO_ORD_NUM:").append(poTmsg.poOrdNum.getValue());
            S21InfoLogOutput.println(NPAM1171E, new String[] {sb.toString() });
            String msg = S21MessageFunc.clspGetMessage(NPAM1171E, new String[] {sb.toString() });
            sendMail(MAIL_TEMPLATE_ID_04, poAckHdrWrk, msg, poTmsg);
            return false;
        }
        return true;

    }

    /**
     * debug
     * @param message
     */
    protected void debug(String message) {

        EZDDebugOutput.println(D_MES_CODE, message, this);

    }

    /**
     * Finalize method.
     */
    @Override
    protected void termRoutine() {

        setTermState(this.termCd, this.successCount, this.errorCount, this.successCount + this.errorCount);
    }

    /**
     * Called for batch shell.
     * @param args execution parameter
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NPAB100001().executeBatch(NPAB100001.class.getSimpleName());
    }

    /**
     * updateCheckMdseCost
     * @param mdse_cd String
     * @param updateCost updateCost
     * @return boolean
     */
    private boolean updateCheckMdseCost(String mdse_cd, BigDecimal updateCost) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_MDSE_CD, mdse_cd);
        ssmParam.put(BIND_PENDING, MDSE_CST_UPD_STS.PENDING);

        List<Map<String, Object>> checkMdseCostList = ssmBatchClient.queryObjectList("updateCheckMdseCost", ssmParam);

        for (Map<String, Object> checkMdseCost : checkMdseCostList) {
            if (checkMdseCost.get(RQST_TOT_STD_COST_AMT) != null && updateCost.compareTo((BigDecimal) checkMdseCost.get(RQST_TOT_STD_COST_AMT)) == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * getSplyItemNumFromAsl
     * @param mdseCd String
     * @param vndCd String
     * @return String
     */
    private String getSplyItemNumFromAsl(String mdseCd, String vndCd) {


        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_VND_CD, vndCd);
        ssmParam.put(BIND_MDSE_CD, mdseCd);
        ssmParam.put(BIND_EFF_THRU_DT, EFF_THRU_DATE);
        ssmParam.put(BIND_EFF_FROM_DT, EFF_FROM_DATE);
        ssmParam.put(BIND_ASL_END_DT, EFF_THRU_DATE);
        ssmParam.put(BIND_ASL_START_DT, EFF_FROM_DATE);

        ssmParam.put(BIND_SLS_DATE, this.slsDt);

        return (String) ssmBatchClient.queryObject("getSplyItemNumFromAsl", ssmParam);
    }
    
    // QC#18283 Add.
    /**
     * getNewAslMdseCd
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return aslMdseCdList List<Map<String, Object>>
     */
    private String getNewAslMdseCd(Map<String, Object> poAckDtlWrk) {

        NPZC110001 apiGetPoLineFromEdi = new NPZC110001();

        NPZC110001PMsg apiGetPoLineFromEdiParam = new NPZC110001PMsg();

        ZYPEZDItemValueSetter.setValue(apiGetPoLineFromEdiParam.glblCmpyCd, this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(apiGetPoLineFromEdiParam.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(apiGetPoLineFromEdiParam.ediPoOrdNum, (String) poAckDtlWrk.get(EDI_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(apiGetPoLineFromEdiParam.poOrdNum_I, (String) poAckDtlWrk.get(PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(apiGetPoLineFromEdiParam.poDetailInList.no(0).ediPoOrdDtlLineNum, (String) poAckDtlWrk.get(EDI_PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(apiGetPoLineFromEdiParam.poDetailInList.no(0).poOrdDtlLineNum, (String) poAckDtlWrk.get(PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(apiGetPoLineFromEdiParam.poDetailInList.no(0).mdseCd, (String) poAckDtlWrk.get(MDSE_CD));
        apiGetPoLineFromEdiParam.poDetailInList.setValidCount(1);

        apiGetPoLineFromEdi.execute(apiGetPoLineFromEdiParam, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(apiGetPoLineFromEdiParam)) {

            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiGetPoLineFromEdiParam);

            for (String xxMsgId : xxMsgIdList) {

                S21InfoLogOutput.println(xxMsgId);

            }

            StringBuilder subMsg = new StringBuilder("EDI_PO_ORD_NUM=").append((String) poAckDtlWrk.get(EDI_PO_ORD_NUM));

            subMsg.append("PO_ORD_NUM=").append((String) poAckDtlWrk.get(PO_ORD_NUM));

            S21InfoLogOutput.println(NPAM1323E, new String[] {"Get Po Line From EDI", subMsg.toString() });
            return null;
        }

        String poOrdNum = apiGetPoLineFromEdiParam.poOrdNum_O.getValue();
        String poOrdDtlLineNum = apiGetPoLineFromEdiParam.poDetailOutList.no(0).poOrdDtlLineNum.getValue();

        String aslMdseCd = null;
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);
        ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);

        List<Map<String, Object>> aslMdseCdList = ssmBatchClient.queryObjectList("getNewAslMdseCd", ssmParam);

        if (aslMdseCdList.size() == 0) {
            return null;
        }

        aslMdseCd = (String) aslMdseCdList.get(0).get(ASL_MDSE_CD);

        // Update  PO_ACK_DTL_WRK to ORIG_VND_MDSE_CD
        PO_ACK_DTL_WRKTMsg inPoAckDtlWrkTMsg = new PO_ACK_DTL_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(inPoAckDtlWrkTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inPoAckDtlWrkTMsg.poOrdNum, (String) poAckDtlWrk.get(PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(inPoAckDtlWrkTMsg.poAckDtlWrkPk, (BigDecimal) poAckDtlWrk.get(PO_ACK_DTL_WRK_PK));

        PO_ACK_DTL_WRKTMsg outPoAckDtlWrkTMsg = (PO_ACK_DTL_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inPoAckDtlWrkTMsg);

        if (outPoAckDtlWrkTMsg == null) {

            return null;

        } else {

            ZYPEZDItemValueSetter.setValue(outPoAckDtlWrkTMsg.poOrdDtlLineNum, poOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(outPoAckDtlWrkTMsg.ediPoOrdDtlLineNum, poOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(outPoAckDtlWrkTMsg.origVndMdseCd, aslMdseCd);

            EZDTBLAccessor.update(outPoAckDtlWrkTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outPoAckDtlWrkTMsg.getReturnCode())) {

                return null;
            }
        }

        return aslMdseCd;
    }

    /**
     * initializeItfcErrMsgTxt
     * @param poOrdNum
     * @param poOrdDtlLineNum
     */
    private void initializeItfcErrMsgTxt(String poOrdNum, String poOrdDtlLineNum) {

        PO_DTLTMsg poDtlTMsg = new PO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(poDtlTMsg.glblCmpyCd, this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(poDtlTMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(poDtlTMsg.poOrdDtlLineNum, poOrdDtlLineNum);

        poDtlTMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(poDtlTMsg);

        if (poDtlTMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(poDtlTMsg.getReturnCode()) && ZYPCommonFunc.hasValue(poDtlTMsg.poDtlIntfcErrMsgTxt)) {

            // Initialized PO Detail Interface Error Message Text.
            poDtlTMsg.poDtlIntfcErrMsgTxt.clear();
            EZDTBLAccessor.update(poDtlTMsg);
        }

    }

    /**
    * getSetCompAckStsCd
    * @param poOrdNum String
    * @param poOrdDtlLineNum String
    * @return boolean
    */
   private List<Map<String, Object>>getSetCompAckStsCd(String poOrdNum, String poOrdDtlLineNum) {

       Map<String, Object> ssmParam = new HashMap<String, Object>();
       String slsDate = ZYPDateUtil.getSalesDate();

       ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
       ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);
       ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);
       ssmParam.put("poAckDtlLtstFlgY", ZYPConstant.FLG_ON_Y);
       ssmParam.put("vndPoAckStsCdIb", VND_PO_ACK_STS.ITEM_BACKORDERED);
       ssmParam.put("poMdseCmpsnTpCdParent", PO_MDSE_CMPSN_TP.PARENT);

       List<Map<String, Object>> compList = ssmBatchClient.queryObjectList("getSetCompAckStsCd", ssmParam);

       if (compList == null || compList.size() != 1) {
           return null;
       }
       
       return compList;

   }

   // START 2018/11/14 M.Naito [QC#29047,ADD]
   /**
    * getVndSysTpCd
    * @param vndCd
    * @return
    */
   private String getVndSysTpCd(String vndCd) {

       HashMap<String, String> paramMap = new HashMap<String, String>();
       paramMap.put("glblCmpyCd", this.glCmpCd);
       paramMap.put("rcvAsnVndCd", vndCd);

       String vndSysTpCd = (String) ssmBatchClient.queryObject("getVndSysTpCd", paramMap);

       return vndSysTpCd;
   }
   // END 2018/11/14 M.Naito [QC#29047,ADD]
   
   // QC#28679 add start

   /**
    * sendWarningMail
    */
   private void sendWarningMail(String mailTemp) {

       // *********************************************************
       // Mail Address
       // *********************************************************
       // mail Init
       S21Mail mail = new S21Mail(this.glCmpCd);

       // Get From Address
       S21MailGroup groupFrom = new S21MailGroup(this.glCmpCd, MAIL_GROUP_ID_FROM);

       // Get To Address
       S21MailGroup groupTo = new S21MailGroup(this.glCmpCd, MAIL_GROUP_ID_TO);

       // Set address FROM
       List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
       mail.setFromAddress(fromAddrList.get(0));

       // Set address TO
       List<S21MailAddress> toAddrList = groupTo.getMailAddress();
       mail.setToAddressList(toAddrList);

       // S21MailTemplate
       S21MailTemplate template = new S21MailTemplate(this.glCmpCd, mailTemp);

       // *********************************************************
       // mail body
       // *********************************************************
       Map<String, Object> warningdataCash = new HashMap<String, Object>();

       // Set template parameter
       String crlf = System.getProperty("line.separator");

       boolean createdHeader = false;
       StringBuffer msg = new StringBuffer();

       // START 2019/02/18 M.Naito [QC#30276,ADD]
       String poOrdNum = null;
       // END 2019/02/18 M.Naito [QC#30276,ADD]

       for (PO_ACK_ERR_WRKTMsg warning : warningList) {

            BigDecimal poAckHdrWrkPk = warning.poAckHdrWrkPk.getValue();

            if (!createdHeader) {

                PO_ACK_HDR_WRKTMsg outMsg = new PO_ACK_HDR_WRKTMsg();

                ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glCmpCd);
                ZYPEZDItemValueSetter.setValue(outMsg.poAckHdrWrkPk, poAckHdrWrkPk);

                outMsg = (PO_ACK_HDR_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(outMsg);

                template.setTemplateParameter("poOrdNum", outMsg.poOrdNum.getValue());
                template.setTemplateParameter("ediPoOrdNum", outMsg.ediPoOrdNum.getValue());
                template.setTemplateParameter("vndCd", outMsg.vndCd.getValue());
                template.setTemplateParameter("batchId", BATCH_ID);

                createdHeader = true;
                // START 2019/02/18 M.Naito [QC#30276,ADD]
                poOrdNum = outMsg.poOrdNum.getValue();
                // END 2019/02/18 M.Naito [QC#30276,ADD]

            }

            BigDecimal poAckDtlWrkPk = warning.poAckDtlWrkPk.getValue();
            String batErrMsgTxt = warning.batErrMsgTxt.getValue();

            String cacheKey = poAckHdrWrkPk.toString() + poAckDtlWrkPk.toString() + batErrMsgTxt;

            if (warningdataCash.containsKey(cacheKey)) {
                continue;
            }
            warningdataCash.put(cacheKey, cacheKey);

            StringBuffer line = new StringBuffer();

            PO_ACK_DTL_WRKTMsg dtlMsg = new PO_ACK_DTL_WRKTMsg();

            ZYPEZDItemValueSetter.setValue(dtlMsg.glblCmpyCd, this.glCmpCd);
            ZYPEZDItemValueSetter.setValue(dtlMsg.poAckDtlWrkPk, poAckDtlWrkPk);

            dtlMsg = (PO_ACK_DTL_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(dtlMsg);

            line.append(NLGC001001.convFixedLgLeftAlign(dtlMsg.ediPoOrdDtlLineNum.getValue(), LINE_SPASE_12));
            line.append(NLGC001001.convFixedLgLeftAlign(dtlMsg.mdseCd.getValue(), LINE_SPASE_18));

            // START 2019/02/18 M.Naito [QC#30276,ADD]
            String cacheKeyPoLine = poOrdNum + dtlMsg.ediPoOrdDtlLineNum.getValue() + batErrMsgTxt;
            if (warningdataCash.containsKey(cacheKeyPoLine)) {
                continue;
            }
            warningdataCash.put(cacheKeyPoLine, cacheKeyPoLine);
            // END 2019/02/18 M.Naito [QC#30276,ADD]

            // po qty
            String ordQty = " ";
            BigDecimal poQty = getPoQty(dtlMsg);

            if (ZYPCommonFunc.hasValue(poQty)) {
                ordQty = poQty.toString();
            }
            line.append(NLGC001001.convFixedLgLeftAlign(ordQty, LINE_SPASE_9));

            // po ack Qty
            String origOrdQty = " ";
            if (ZYPCommonFunc.hasValue(dtlMsg.origOrdQty.getValue())) {
                origOrdQty = dtlMsg.origOrdQty.getValue().toString();
            }
            line.append(NLGC001001.convFixedLgLeftAlign(origOrdQty, LINE_SPASE_9));

            line.append(NLGC001001.convFixedLgLeftAlign(batErrMsgTxt, LINE_SPASE_80));

            // carriage return & line feed
            line.append(crlf);

            // line end.
            msg.append(line);

        }
        template.setTemplateParameter("errorDetail", msg.toString());
        mail.setMailTemplate(template);

        // Post
        mail.postMail();

    }

    /**
     * BigDecimal
     * @param tmsg
     * @return
     */
    private BigDecimal getPoQty(PO_ACK_DTL_WRKTMsg tmsg) {

        PO_DTLTMsg dtlMsg = new PO_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(dtlMsg.glblCmpyCd, this.glCmpCd);
        ZYPEZDItemValueSetter.setValue(dtlMsg.poOrdNum, tmsg.poOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(dtlMsg.poOrdDtlLineNum, tmsg.poOrdDtlLineNum.getValue());

        dtlMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(dtlMsg);

        if (dtlMsg != null) {
            return dtlMsg.poQty.getValue();
        } else {
            return null;
        }
    }
    // QC#28679 add start
    

    // START 2019/11/15 M.Naito [QC#53502,ADD]
    /**
     * chkInvoicedAckList
     * @param poAckDtlWrkList List<Map<String, Object>>
     * @param poAckBeanList List<poAckBean>
     * @param poOrdNum String
     * @return boolean
     */
    private boolean chkInvoicedAckList(List<Map<String, Object>> poAckDtlWrkList, List<poAckBean> poAckBeanList, String poOrdNum) {

        boolean chkInvoicedAckFlg = false;
        for (int i = 0; i < poAckDtlWrkList.size(); i++) {
            Map<String, Object> poAckDtlWrk = poAckDtlWrkList.get(i);
            poAckBean poAckBeanWk = poAckBeanList.get(i);
            if (!VND_PO_ACK_STS.INVOICED.equals((String) poAckDtlWrk.get(VND_PO_ACK_STS_CD))) {
                chkInvoicedAckFlg = false;
                break;
            }
            if (chkInvoicedAck(poOrdNum, poAckBeanWk.getApiReturnedPoOrdDtlLineNum())) {
                chkInvoicedAckFlg = true;
            }
        }
        return chkInvoicedAckFlg;
    }

    /**
     * chkInvoicedAck
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return boolean
     */
    private boolean chkInvoicedAck(String poOrdNum, String poOrdDtlLineNum) {

        boolean chkInvoicedAckFlg = false;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, this.glCmpCd);
        ssmParam.put(BIND_PO_ORD_NUM, (String) poOrdNum);
        ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);
        ssmParam.put(BIND_VND_PO_ACK_STS_CD, VND_PO_ACK_STS.SHIPPED);

        List<Map<String, Object>> latestShippedAckList = ssmBatchClient.queryObjectList("getLatestShippedAck", ssmParam);
        if (latestShippedAckList != null && latestShippedAckList.size() > 0) {
            chkInvoicedAckFlg = true;
        }
        return chkInvoicedAckFlg;
    }
    // END 2019/11/15 M.Naito [QC#53502,ADD]

    // START 2020/10/06 H.Dimay [QC#57795, ADD]
    private boolean isVendorExcludeAslUpdate(POTMsg poTMsg) {

        boolean isExcludedVndCdFlg = false;
        String vendorCode = poTMsg.vndCd.getValue();
        String excludeVendorCode = ZYPCodeDataUtil.getVarCharConstValue(NPAB100001Constant.VAR_CONST_UPD_ASL_EXCL_VND, this.glCmpCd);

        if (ZYPCommonFunc.hasValue(excludeVendorCode) && ZYPCommonFunc.hasValue(vendorCode)) {
            String[] arrayExcludeVendorCode = excludeVendorCode.split(",", 0);
            for (int i = 0; i < arrayExcludeVendorCode.length; i++) {

                if (vendorCode.equals(arrayExcludeVendorCode[i])) {
                    isExcludedVndCdFlg = true;
                    break;
                }
            }
        }

        return isExcludedVndCdFlg;
    }
    // END Q2020/10/06 H.Dimay [C#57795, ADD]
}
