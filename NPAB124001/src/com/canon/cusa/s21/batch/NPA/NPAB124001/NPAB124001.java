/*
* <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NPA.NPAB124001;

import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.ACTL_SHIP_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.ASL_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.AUTO_LIC_ALLOC_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_CPO_DTL_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_CPO_ORD_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_CPO_ORD_TP_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_CPO_SRC_TP_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_D_EDI_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_D_EDI_PROC_STS_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_EDI_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_EDI_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_EDI_PROC_STS_CD_C;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_EDI_PROC_STS_CD_E;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_EDI_TRD_PTNR_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_EDI_TRX_PRPS;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_EDI_TRX_PRPS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_EZUPTIME;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_H_EDI_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_H_EDI_PROC_STS_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_LAK_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_ORD_TAKE_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_POYO_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_PO_ACK_LINE_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_PROC_PGM_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_P_CODE_CUST_SOLD_TO_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_P_CODE_IND_HOLD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_P_CODE_ORDR_TYPE_PART_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_P_CODE_STAT_OE_1;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_P_CODE_STAT_OE_4;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_P_CODE_STAT_OE_8;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_P_CODE_STAT_OE_9;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_P_CODE_STAT_OE_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_P_FILE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_P_FLAG_CANCEL_BO;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_P_QTY_ORDR_ACPT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_P_QTY_SHIP_SCHED;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_SET_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_SHPG_STS_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_TRX_SRC_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BIND_VND_SHPG_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CARR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CARR_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CCY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CCY_CD_USD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CPO_DTL_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CPO_DTL_LINE_SUB_NUM_000;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CPO_ORD_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CP_EZUPTIME;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CR_HLD_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CST_DEBUG_MSG_LVL;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CS_LOC_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CUST_ISS_PO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CUST_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.CUST_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.DS_BIZ_LAST_UPD_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.DS_BIZ_PROC_LOG_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.DS_BIZ_PROC_LOG_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_CCY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_CPO_DTL_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_CUSA_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_CUST_EDI_PO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_CUST_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_EDI_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_EDI_PO_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_EDI_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_ENT_DEAL_NET_UNIT_PRC_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_EZUPTIME;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_ORD_LINE_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_ORD_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_ORIG_CUSA_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_ORIG_ORD_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_ORIG_TRD_PTNR_SKU_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_SHIP_TO_LOC_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_TRD_PTNR_SKU_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_UNIT_PRC_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.D_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.EDI_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.ENT_DEAL_NET_UNIT_PRC_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.ETA_DRV_PTRN_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.ETA_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.ETD_DRV_PTRN_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.EZUPTIME;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.HC_EZUPTIME;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.H_CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.H_CPO_ORD_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.H_CUST_EDI_PO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.H_CUST_ISS_PO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.H_EDI_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.H_EZUPTIME;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.H_ORD_HDR_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.H_SELL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.IF_ID_NPAA0031;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.IF_ID_NPAA0032;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.IF_ID_NPAA0041;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.INTERNAL_VAR_CHAR_CONST;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.INVTY_CTRL_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.LOC_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.MDSE_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.MDSE_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.M_MDSE_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.NMAM0039E;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.NPAM1003E;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.NPAM1496E;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.NPAM1497E;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.NPAM1498E;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.ONE;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.ORD_HDR_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.ORD_LINE_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.ORD_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.ORIG_ORD_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PDD_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PE_NAME_CUST_01;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PH_NAME_CUST_01;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.POYO_EZUPTIME;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PO_ACK_CREATE_PTN_DIRECT_CPO;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PO_ACK_CREATE_PTN_EDI_CANCEL;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PO_ACK_CREATE_PTN_EDI_CPO;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PO_ACK_CREATE_PTN_EDI_ERROR;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PO_ACK_CREATE_PTN_PARTS;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PO_ACK_DTL_WRK_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PO_ACK_HDR_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PO_ACK_HDR_WRK_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PO_ACK_LINE_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PO_ORD_DTL_SUB_LINE_NUM_001;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PO_REQ_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PRO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.PSD_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_CUST_SHIP_TO;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_CUST_SOLD_TO;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_SHIP_VIA;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_STAT_OE;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_STAT_OE_1;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_STAT_OE_2;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_STAT_OE_3;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_STAT_OE_4;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_STAT_OE_8;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_STAT_OE_9;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_STAT_SO;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_STAT_SO_1;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_STAT_SO_2;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_STAT_SO_3;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_TABLE_DESCRIPTION;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CODE_WH;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_CUST_PART_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_DATE_ORDR_RCV;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_DATE_SHIP_PRSS;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_DATE_SO_ISSUE;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_DATE_STOCK_IN_EST;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_DESC_PART;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_FLAG_CANCEL_BO;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_NUM_CUST_PO;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_NUM_LINE;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_NUM_ORDR;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_NUM_SO;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_NUM_TRUCKER;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_ORIG_TRD_PTNR_SKU_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_PARTS_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_PRC_UNIT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_QTY_ACPT_ORDR;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_QTY_ORDR_ACPT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.P_QTY_PCSE_ORDR;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SELL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SET_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SHIP_TO_LOC_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SHPG_PLN_DPLY_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SHPG_PLN_DPLY_LINE_NUM_001;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SHPG_PLN_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SHPG_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SLS_HLD_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SO_SLP_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_ACTL_SHIP_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_CARR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_CARR_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_CR_HLD_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_CUST_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_EZUPTIME;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_ORD_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_PDD_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_PRO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_PSD_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_SET_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_SHPG_PLN_DPLY_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_SHPG_PLN_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_SHPG_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_SLS_HLD_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.SP_SO_SLP_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.S_EZUPTIME;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.TIME_FORMAT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.TIME_STAMP;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.TWO;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.UOM_CD_EA;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_CPO_HDLG_API_INTFC_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_CPO_SRC_TP_EDI;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_CPO_SRC_TP_GRP_CMPY;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_EDI_PROC_STS_CANC;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_EDI_PROC_STS_ERR;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_EDI_PROC_STS_ITRL_ERR;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_EDI_PROC_STS_PROC;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_EDI_TRX_PRPS_ORIG;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_PRT_ACK_CANC_WRK_FILE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_PRT_OE_STS_AUTH_HOLD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_PRT_OE_STS_BACK_ORD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_PRT_OE_STS_CR_HOLD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_PRT_OE_STS_NORM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_PRT_OE_STS_ODC_HOLD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_PRT_OE_STS_RUSH_ORD_HOLD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_PRT_OE_STS_VOID;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_PRT_ORD_TP_PRT_RTRN;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_PRT_ORD_TP_RPR_PRT_RTRN;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_PRT_ORD_TP_RR_FROM_DLR;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_PRT_SO_STS_INV;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_PRT_SO_STS_PRNT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_CUSA_PRT_SO_STS_SHIP;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAL_DROP_SHIP_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_CPO_HDLG_API_INTFC_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_CPO_SRC_TP_EDI;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_CPO_SRC_TP_GRP_CMPY;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_EDI_PROC_STS_CANC;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_EDI_PROC_STS_ERR;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_EDI_PROC_STS_ITRL_ERR;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_EDI_PROC_STS_PROC;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_EDI_TRD_PTNR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_EDI_TRX_PRPS_ORIG;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PARTS_PO_CRAT_APL_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PRT_ACK_CANC_WRK_FILE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PRT_EDI_TRD_PTNR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PRT_OE_STS_AUTH_HOLD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PRT_OE_STS_BACK_ORD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PRT_OE_STS_CR_HOLD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PRT_OE_STS_NORM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PRT_OE_STS_ODC_HOLD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PRT_OE_STS_RUSH_ORD_HOLD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PRT_OE_STS_VOID;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PRT_ORD_TP_PRT_RTRN;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PRT_ORD_TP_RPR_PRT_RTRN;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PRT_ORD_TP_RR_FROM_DLR;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PRT_SO_STS_INV;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PRT_SO_STS_PRNT;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_CUSA_PRT_SO_STS_SHIP;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VAR_DROP_SHIP_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VND_CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VND_CPO_DTL_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VND_CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.VND_PO_ACK_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB124001constant.NPAB124001constant.ZERO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.PO_ACK_DTL_WRKTMsg;
import business.db.PO_ACK_HDR_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACK_EDI_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LAK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACK_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_PO_ACK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NPAB124001
 * Function Name : Receiving PO ACK for CUSA Domestic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/15/2016   CITS            T.Hakodate      Create          WDS NA
 * 05/01/2016   CITS            T.Hakodate      Update          QC#7667
 * 05/04/2016   CSAI            K.Lee           Update          QC#7895 
 * 05/04/2016   CSAI            T.Hakodate      Update          QC#7901
 * 12/08/2016   CITS            K.Ogino         Update          QC#15770
 * 2017/01/12   CITS            K.Kameoka       Update          QC#17077
 * 04/20/2017   CITS            R.Shimamoto     Update          QC#18283
 * 06/13/2017   CITS            K.Fukumura      Update          QC#18611
 * 08/01/2017   CITS            Y.Iwasaki       Update          QC#19201
 * 08/18/2017   CITS            K.Ogino         Update          QC#20681
 * 09/01/2017   CITS            R.Shimamoto     Update          QC#20737
 * 11/27/2017   CITS            T.Hakodate      Update          QC#22425
 * 03/12/2018   CITS            T.Wada          Update          QC#24404
 * 2018/07/05   CITS            T.Hakodate      Update          QC#26090
 * 2018/07/06   CITS            T.Hakodate      Update          QC#26089
 * 2018/10/19   CITS            T.Hakodate      Update          QC#28834
 * 2018/10/19   CITS            M.Naito         Update          QC#28830
 * 2018/11/20   CITS            T.Hakodate      Update          QC#29245
 * 2019/02/25   CITS            M.Naito         Update          QC#30452
 * 2019/04/16   CITS            A.Kobayashi     Update          QC#31144
 * 2019/05/13   CITS            K.Ogino         Update          QC#31207
 * 2021/01/27   CITS            K.Ogino         Update          QC#56768
 * 2021/05/12   CITS            J.Evangelista   Update          QC#58718
 * 2022/11/08   CITS            A.Cullano       Update          QC#60776
 */

public class NPAB124001 extends S21BatchMain {

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** total normal count. */
    private int normalRecCnt;

    /** total error count. */
    private int errRecCnt;

    /** Grobal Company Code. */
    private String glblCmpyCd;

    /** Cusa Grobal Company Code. */
    private String cusaGlblCmpyCd;

    /** SSM Client. */
    private S21SsmBatchClient ssmBatchClient = null;

    /** UserProfile */
    private S21UserProfileService profile;

    /** Interface ID */
    private String interfaceId;

    /** Config ID. */
    private String processMode;

    /** trdPtnrCdList. */
    private ArrayList<String> trdPtnrCdList = new ArrayList<String>();

    /** cusaPartsPoCratAplId. */
    private String cusaPartsPoCratAplId;

    /** bizProcLogPk. */
    private BigDecimal bizProcLogPk = null;

    /** bizLastUpdTs. */
    private String bizLastUpdTs = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** poAckHdrWrkList */
    private List<PO_ACK_HDR_WRKTMsg> poAckHdrWrkList = null;

    /** poAckDtlWrkList */
    private List<PO_ACK_DTL_WRKTMsg> poAckDtlWrkList = null;

    /** dsBizLastUpdTs. */
    private String dsBizLastUpdTs = null;

    // QC#20737 Add.
    /** cpoOrdTpCd */
    private String cpoOrdTpCd = null;
    // QC#31207
    private BigDecimal leadTimeDay = null;
    // QC#56768
    private BigDecimal minAgo = BigDecimal.ZERO;

    /**
     * Initialize method.
     */
    @Override
    protected void initRoutine() {

        // SQL access parts
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Initialize UserProfile
        this.profile = S21UserProfileServiceFactory.getInstance().getService();

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        // Get interface ID
        this.interfaceId = getInterfaceID();

        this.poAckHdrWrkList = new ArrayList<PO_ACK_HDR_WRKTMsg>();

        this.poAckDtlWrkList = new ArrayList<PO_ACK_DTL_WRKTMsg>();

        // QC#20737 Add.
        this.cpoOrdTpCd = ZYPCodeDataUtil.getVarCharConstValue("NPAB1250_CPO_ORD_TP_CD", this.glblCmpyCd);
        // QC#31207
        this.leadTimeDay = ZYPCodeDataUtil.getNumConstValue("PO_ACK_LT_DAY", this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.leadTimeDay)) {
            this.leadTimeDay = BigDecimal.ZERO;
        }

        // User Variable1
        processMode = getInterfaceID();

        this.cusaGlblCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_GLBL_CMPY_CD, this.glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(this.cusaGlblCmpyCd)) {

            throw new S21AbendException(NPAM1496E);

        }

        if (ZYPCommonFunc.hasValue(processMode)) {

            if (IF_ID_NPAA0032.equals(processMode)) {

                String str = ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_EDI_TRD_PTNR_CD, this.glblCmpyCd);
                // QC#56768
                BigDecimal numConstVal = ZYPCodeDataUtil.getNumConstValue("NPAA0032_PO_ACK_MINUTES_AGO", this.glblCmpyCd);
                if (ZYPCommonFunc.hasValue(numConstVal)) {
                    this.minAgo = numConstVal;
                }

                if (!ZYPCommonFunc.hasValue(str)) {

                    throw new S21AbendException(NPAM1497E);

                } else {

                    String[] trdPtnr = str.split(",");

                    for (int i = 0; i < trdPtnr.length; i++) {

                        trdPtnrCdList.add(trdPtnr[i]);

                    }

                }

            } else if (IF_ID_NPAA0041.equals(processMode)) {

                cusaPartsPoCratAplId = ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PARTS_PO_CRAT_APL_ID, this.glblCmpyCd);

                if (!ZYPCommonFunc.hasValue(cusaPartsPoCratAplId)) {

                    throw new S21AbendException(NPAM1498E);

                }
             // QC#26089 ADD START
                String str = ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_EDI_TRD_PTNR_CD, this.glblCmpyCd);
                // QC#56768
                BigDecimal numConstVal = ZYPCodeDataUtil.getNumConstValue("NPAA0041_PO_ACK_MINUTES_AGO", this.glblCmpyCd);
                if (ZYPCommonFunc.hasValue(numConstVal)) {
                    this.minAgo = numConstVal;
                }

                if (!ZYPCommonFunc.hasValue(str)) {
                    throw new S21AbendException(NPAM1497E);
                } else {
                    String[] trdPtnr = str.split(",");
                    for (int i = 0; i < trdPtnr.length; i++) {
                        this.trdPtnrCdList.add(trdPtnr[i]);
                    }
                }
                // QC#26089 ADD END

                // QC#26090 ADD START
            } else if (IF_ID_NPAA0031.equals(processMode)) {

                String str = ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_EDI_TRD_PTNR_CD, this.glblCmpyCd);
                // QC#56768
                BigDecimal numConstVal = ZYPCodeDataUtil.getNumConstValue("NPAA0031_PO_ACK_MINUTES_AGO", this.glblCmpyCd);
                if (ZYPCommonFunc.hasValue(numConstVal)) {
                    this.minAgo = numConstVal;
                }

                if (!ZYPCommonFunc.hasValue(str)) {

                    throw new S21AbendException(NPAM1497E);

                } else {

                    String[] trdPtnr = str.split(",");

                    for (int i = 0; i < trdPtnr.length; i++) {
                        trdPtnrCdList.add(trdPtnr[i]);
                    }

                }
                // QC#26090 ADD END

            } else if (!IF_ID_NPAA0031.equals(processMode)) {

                return;

            }

        } else {

            return;

        }

    }

    /**
     * getLastUpdateTs
     */
    private void getLastUpdateTs() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);

        if (IF_ID_NPAA0031.equals(processMode)) {
            ssmParam.put(BIND_PROC_PGM_ID, BUSINESS_ID + "1");
        } else if (IF_ID_NPAA0032.equals(processMode)) {
            ssmParam.put(BIND_PROC_PGM_ID, BUSINESS_ID + "2");
        } else if (IF_ID_NPAA0041.equals(processMode)) {
            ssmParam.put(BIND_PROC_PGM_ID, BUSINESS_ID + "3");
        }

        List<Map<String, Object>> lastUpdateTsList = ssmBatchClient.queryObjectList("getLastUpdateTs", ssmParam);

        if (lastUpdateTsList == null || lastUpdateTsList.isEmpty()) {

            bizProcLogPk = null;
            bizLastUpdTs = "00000000";

        } else {

            Map<String, Object> lastUpdateTsMap = lastUpdateTsList.get(0);
            bizProcLogPk = (BigDecimal) lastUpdateTsMap.get(DS_BIZ_PROC_LOG_PK);
            bizLastUpdTs = (String) lastUpdateTsMap.get(DS_BIZ_LAST_UPD_TS);

        }

    }

    /**
     * Main method.
     */
    protected void mainRoutine() {

        // get Latest Refresh time
        getLastUpdateTs();

        // QC#31144 inform the time for processing data from last task (this message can be changed)
        S21InfoLogOutput.println("TARGET DATA : " + bizLastUpdTs);
        // QC#56768
        S21InfoLogOutput.println("MINUTES AGO : " + this.minAgo);
        this.bizLastUpdTs = EZDCommonFunc.toyyyyMMddHHmmssSSS(EZDCommonFunc.toTimeMinute(this.bizLastUpdTs) - (this.minAgo.longValue() * 60000L));
        S21InfoLogOutput.println("TARGET DATA : " + bizLastUpdTs);

        // Set the fetch size.
        S21SsmExecutionParameter execParam = null;
        execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);

        if (IF_ID_NPAA0031.equals(processMode)) {

            createPoAckWs(execParam);

        } else if (IF_ID_NPAA0032.equals(processMode)) {

            createPoAckWsEdi(execParam);

        } else if (IF_ID_NPAA0041.equals(processMode)) {

            createAckParts(execParam);

        }

        if (dsBizLastUpdTs == null) {
            // QC#31144 inform the time to finish and next time processing (this message can be changed)
            S21InfoLogOutput.println("NEXT TIME TARGET DATA : " + bizLastUpdTs);
        } else {
            // QC#31144 inform the time to finish and next time processing (this message can be changed)
            S21InfoLogOutput.println("NEXT TIME TARGET DATA : " + dsBizLastUpdTs);
        }

    }

    /**
     * createAckParts
     * @param execParam
     */
    private void createAckParts(S21SsmExecutionParameter execParam) {

        PreparedStatement prdStmtParts = null;
        ResultSet rsParts = null;

        HashMap<String, String> internalVarConstMap = new HashMap<String, String>();

        for (String itemName : INTERNAL_VAR_CHAR_CONST) {
            internalVarConstMap.put(itemName, null);
        }

        try {

            getVarConstCd(internalVarConstMap);
            
            //QC#26089 ADD START
            List<String> pCodeCustSoldToList = getSellToCustCd(); 
            //QC#26089 ADD END

            Map<String, Object> ssmParam = new HashMap<String, Object>();

            //ssmParam.put(BIND_EZINAPLID, this.cusaPartsPoCratAplId);

            List<String> pCodeOrdrTypePartList = new ArrayList<String>();
            pCodeOrdrTypePartList.add(internalVarConstMap.get(VAL_CUSA_PRT_ORD_TP_PRT_RTRN));
            pCodeOrdrTypePartList.add(internalVarConstMap.get(VAL_CUSA_PRT_ORD_TP_RPR_PRT_RTRN));
            pCodeOrdrTypePartList.add(internalVarConstMap.get(VAL_CUSA_PRT_ORD_TP_RR_FROM_DLR));

            ssmParam.put(BIND_P_CODE_ORDR_TYPE_PART_LIST, pCodeOrdrTypePartList);

            ssmParam.put(BIND_EZUPTIME, this.bizLastUpdTs);

            ssmParam.put(BIND_P_CODE_STAT_OE_1, internalVarConstMap.get(VAL_CUSA_PRT_OE_STS_NORM));
            ssmParam.put(BIND_P_CODE_STAT_OE_4, internalVarConstMap.get(VAL_CUSA_PRT_OE_STS_AUTH_HOLD));
            ssmParam.put(BIND_P_CODE_STAT_OE_8, internalVarConstMap.get(VAL_CUSA_PRT_OE_STS_ODC_HOLD));
            ssmParam.put(BIND_P_QTY_SHIP_SCHED, ZERO);

            ssmParam.put(BIND_P_QTY_ORDR_ACPT, ZERO);

            ssmParam.put(BIND_P_CODE_IND_HOLD, ZYPConstant.FLG_ON_Y);
            ssmParam.put(BIND_P_FLAG_CANCEL_BO, ZYPConstant.FLG_ON_Y);

            List<String> pCodeStatOeList = new ArrayList<String>();
            pCodeStatOeList.add(internalVarConstMap.get(VAL_CUSA_PRT_OE_STS_BACK_ORD));
            pCodeStatOeList.add(internalVarConstMap.get(VAL_CUSA_PRT_OE_STS_RUSH_ORD_HOLD));
            pCodeStatOeList.add(internalVarConstMap.get(VAL_CUSA_PRT_OE_STS_VOID));

            ssmParam.put(BIND_P_CODE_STAT_OE_LIST, pCodeStatOeList);
            ssmParam.put(BIND_P_CODE_STAT_OE_9, internalVarConstMap.get(VAL_CUSA_PRT_OE_STS_VOID));
            ssmParam.put(BIND_P_FILE_ID, internalVarConstMap.get(VAL_CUSA_PRT_ACK_CANC_WRK_FILE_ID));

            // QC#26089 ADD START
            ssmParam.put(BIND_P_CODE_CUST_SOLD_TO_LIST, pCodeCustSoldToList);
            // QC#26089 ADD END
            // QC#31207
            ssmParam.put("days", this.leadTimeDay);

            prdStmtParts = ssmLLClient.createPreparedStatement("getPartsInfo", ssmParam, execParam);
            rsParts = prdStmtParts.executeQuery();

            String ediNum = "";
            String latestEdiNum = "";
            // START 2019/02/25 M.Naito [QC#30452, ADD]
            String ediLineNum = "";
            // END 2019/02/25 M.Naito [QC#30452, ADD]
            int lineSubNum = 1;

            poAckBean poAckBean = new poAckBean();

            // QC#29245 add start
            boolean updateBizProc = false;
            // QC#29245 add end
            boolean isForceInsert = false;

            while (rsParts.next()) {

                int currentRowNum = rsParts.getRow();

                ediNum = rsParts.getString(P_NUM_CUST_PO);
                // START 2019/02/25 M.Naito [QC#30452, ADD]
                ediNum = getPoOrdNumFromXref(ediNum);
                ediLineNum = searchPoOrdDtlLineNum(ediNum, rsParts.getString(P_NUM_LINE));
                poAckBean.setEdiLineNum(ediLineNum);
                // END 2019/02/25 M.Naito [QC#30452, ADD]

                if (!latestEdiNum.equals(ediNum)) {
                    lineSubNum = 1;
                } else {
                    lineSubNum++;
                }
                poAckBean.setEdiNum(ediNum);

                poAckBean.setAckHdrPtn("5");
                poAckBean.setAckDtlPtn("5");
                poAckBean.setVndCpoDtlLineSubNum(String.format("%03d", lineSubNum));

                // ACK Stratus
                if (ZYPConstant.FLG_ON_Y.equals(rsParts.getString(P_FLAG_CANCEL_BO))) {

                    poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.CANCEL);
                    poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.CANCELLED);

                } else if (ZERO.equals(rsParts.getString(P_QTY_ORDR_ACPT))) {

                    poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.CANCEL);
                    poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.CANCELLED);

                } else if (P_CODE_STAT_OE_1.equals(rsParts.getString(P_CODE_STAT_OE))) {

                    // 2017/11/27 QC#22425 T.Hakodate UPDATE START
                    // if (!((rsParts.getString(P_PARTS_NUM)).equals(rsParts.getString(P_CUST_PART_NUM)))) {

                    // START 2019/02/25 M.Naito [QC#30452, ADD]
//                    if (checkPartsItemFlip(ediNum, rsParts.getString(P_PARTS_NUM), rsParts.getString(P_NUM_LINE))) {
                    if (checkPartsItemFlip(ediNum, rsParts.getString(P_PARTS_NUM), ediLineNum)) {
                    // END 2019/02/25 M.Naito [QC#30452, ADD]

                        poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.ITEM_CHANGE);

                        // 2017/11/27 QC#22425 T.Hakodate UPDATE END

                        if (P_CODE_STAT_SO_1.equals(rsParts.getString(P_CODE_STAT_SO))) {

                            poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.SO_PRINTED);

                        } else if (P_CODE_STAT_SO_2.equals(rsParts.getString(P_CODE_STAT_SO))) {

                            poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.SHIPPED);

                        } else if (P_CODE_STAT_SO_3.equals(rsParts.getString(P_CODE_STAT_SO))) {

                            poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.SHIPPED);

                        } else {

                            poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.ALLOCATION);

                        }

                    } else {

                        if (P_CODE_STAT_SO_1.equals(rsParts.getString(P_CODE_STAT_SO))) {

                            poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.SO_PRINTED);
                            poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.RELEASED_FOR_SHIPMENT);

                        } else if (P_CODE_STAT_SO_2.equals(rsParts.getString(P_CODE_STAT_SO))) {

                            poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.SHIPPED);
                            poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.SHIPPED);

                        } else if (P_CODE_STAT_SO_3.equals(rsParts.getString(P_CODE_STAT_SO))) {

                            poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.SHIPPED);
                            poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.SHIPPED);

                        } else {

                            poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.ALLOCATION);
                            poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.RELEASED_FOR_SHIPMENT);

                        }

                    }

                } else if (P_CODE_STAT_OE_9.equals(rsParts.getString(P_CODE_STAT_OE))) {

                    poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.CANCEL);
                    poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.CANCELLED);

                } else if (P_CODE_STAT_OE_3.equals(rsParts.getString(P_CODE_STAT_OE))) {

                    poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.BACK_ORDER);
                    poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.ITEM_BACKORDERED);

                } else if (P_CODE_STAT_OE_2.equals(rsParts.getString(P_CODE_STAT_OE))) {

                    poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.HOLD);
                    poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.ITEM_ON_HOLD);

                } else if (P_CODE_STAT_OE_4.equals(rsParts.getString(P_CODE_STAT_OE))) {

                    poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.HOLD);
                    poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.ITEM_ON_HOLD);

                } else if (P_CODE_STAT_OE_8.equals(rsParts.getString(P_CODE_STAT_OE))) {

                    poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.HOLD);
                    poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.ITEM_ON_HOLD);

                } else {

                    poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.BACK_ORDER);
                    poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.ITEM_BACKORDERED);

                }

                if (P_CODE_STAT_SO_1.equals(rsParts.getString(P_CODE_STAT_SO))) {

                    poAckBean.setETD(rsParts.getString(P_DATE_SO_ISSUE));
                    poAckBean.setETA(rsParts.getString(P_DATE_SO_ISSUE));

                } else if (P_CODE_STAT_SO_2.equals(rsParts.getString(P_CODE_STAT_SO))) {

                    poAckBean.setETD(rsParts.getString(P_DATE_SHIP_PRSS));
                    poAckBean.setETA(rsParts.getString(P_DATE_SHIP_PRSS));

                } else if (P_CODE_STAT_SO_3.equals(rsParts.getString(P_CODE_STAT_SO))) {

                    poAckBean.setETD(rsParts.getString(P_DATE_SHIP_PRSS));
                    poAckBean.setETA(rsParts.getString(P_DATE_SHIP_PRSS));

                } else if (P_CODE_STAT_OE_1.equals(rsParts.getString(P_CODE_STAT_OE))) {

                    poAckBean.setETD(ZYPDateUtil.getCurrentSystemTime(TIME_STAMP));
                    poAckBean.setETA(ZYPDateUtil.getCurrentSystemTime(TIME_STAMP));
                // QC#31207
                } else if (P_CODE_STAT_OE_3.equals(rsParts.getString(P_CODE_STAT_OE)) && ZYPCommonFunc.hasValue(rsParts.getString(P_DATE_STOCK_IN_EST))) {

                    poAckBean.setETD(rsParts.getString(P_DATE_STOCK_IN_EST));
                    poAckBean.setETA(rsParts.getString(P_DATE_STOCK_IN_EST));
                    isForceInsert = true;

                } else if (ZYPCommonFunc.hasValue(rsParts.getString(P_DATE_STOCK_IN_EST))) {

                    poAckBean.setETD(rsParts.getString(P_DATE_STOCK_IN_EST));
                    poAckBean.setETA(rsParts.getString(P_DATE_STOCK_IN_EST));
                    isForceInsert = true;

                }

                // get Ack Status Name
                String poAckLineStsTxt = getAckStsNm(poAckBean.getPoAckLineStsCd());
                poAckBean.setPoAckLineStsTxt(poAckLineStsTxt);

                // create Ack Detail
                boolean hdrCreateFlg = false;

                if (currentRowNum == 1 || !latestEdiNum.equals(ediNum)) {

                    hdrCreateFlg = true;

                    if (this.poAckDtlWrkList.size() > 0) {
                        insertPoAck();
                        normalRecCnt++;
                        updateBizProc = true;
                        commit();
                    }

                    this.poAckHdrWrkList.clear();
                    this.poAckDtlWrkList.clear();
                }

                poAckBean = createAckData(rsParts, poAckBean, hdrCreateFlg, isForceInsert);

                latestEdiNum = ediNum;

            }

            if (this.poAckDtlWrkList.size() > 0) {
                insertPoAck();
                // QC#29245 mod start
                normalRecCnt++;
                updateBizProc = true;
                // updateDsBizProcLog();
                // commit();
                // QC#29245 mod end
               
            }

            // QC#29245 mod start
            if (updateBizProc) {
                updateDsBizProcLog();
                commit();
            }
            // QC#29245 mod end

        } catch (SQLException e) {

            termCd = TERM_CD.ABNORMAL_END;
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
            throw new S21AbendException(e);

        } catch (Exception e) {

            termCd = TERM_CD.ABNORMAL_END;
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            S21InfoLogOutput.println("Error Message: " + e);
            throw new S21AbendException(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(prdStmtParts, rsParts);

        }

    }

    /**
     * createPoAckWsEdi
     * @param execParam
     */
    private void createPoAckWsEdi(S21SsmExecutionParameter execParam) {

        PreparedStatement prdStmtCpo = null;
        ResultSet rsCpo = null;
        PreparedStatement prdStmtEdi = null;
        ResultSet rsEdi = null;
        // QC#29245 add start
        boolean updateBizProc = false;
        // QC#29245 add end

        HashMap<String, String> internalVarConstMap = new HashMap<String, String>();

        for (String itemName : INTERNAL_VAR_CHAR_CONST) {
            internalVarConstMap.put(itemName, null);
        }

        try {

            getVarConstCd(internalVarConstMap);

            // *******************getWsEdiOrderNomalInfo**************************************//

            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put(BIND_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
            ssmParam.put(BIND_EDI_TRD_PTNR_CD_LIST, trdPtnrCdList);
            ssmParam.put(BIND_EDI_TRX_PRPS_CD, internalVarConstMap.get(VAL_CUSA_EDI_TRX_PRPS_ORIG));

            // QC#24404
            //ssmParam.put(BIND_CPO_SRC_TP_CD, internalVarConstMap.get(VAL_CUSA_CPO_SRC_TP_GRP_CMPY));
            List<String> cpoSrcTpCdList = new ArrayList<String>();
            cpoSrcTpCdList.add(internalVarConstMap.get(VAL_CUSA_CPO_SRC_TP_GRP_CMPY));
            if (ZYPCommonFunc.hasValue(internalVarConstMap.get(VAL_CUSA_CPO_SRC_TP_EDI))) {
                cpoSrcTpCdList.add(internalVarConstMap.get(VAL_CUSA_CPO_SRC_TP_EDI));
            }
            ssmParam.put(BIND_CPO_SRC_TP_CD_LIST, cpoSrcTpCdList);

            ssmParam.put(BIND_CPO_ORD_TP_CD, CPO_ORD_TP.SALES);
            ssmParam.put(BIND_EDI_TRX_PRPS, ZYPConstant.FLG_ON_Y);
            ssmParam.put(BIND_H_EDI_PROC_STS_CD, internalVarConstMap.get(VAL_CUSA_EDI_PROC_STS_PROC));

            List<String> dEdiProcStsCdList = new ArrayList<String>();

            dEdiProcStsCdList.add(internalVarConstMap.get(VAL_CUSA_EDI_PROC_STS_CANC));
            dEdiProcStsCdList.add(internalVarConstMap.get(VAL_CUSA_EDI_PROC_STS_ERR));

            ssmParam.put(BIND_D_EDI_PROC_STS_CD_LIST, dEdiProcStsCdList);

            ssmParam.put(BIND_TRX_SRC_TP_CD, TRX_SRC_TP.WHOLE_SALES);

            List<String> shpgStsCdList = new ArrayList<String>();

            // QC#15770
            shpgStsCdList.add(SHPG_STS.SAVED);
            shpgStsCdList.add(SHPG_STS.VALIDATED);
            shpgStsCdList.add(SHPG_STS.HARD_ALLOCATED);
            shpgStsCdList.add(SHPG_STS.S_OR_O_PRINTED);
            shpgStsCdList.add(SHPG_STS.PICKED);
            shpgStsCdList.add(SHPG_STS.PACKED);
            shpgStsCdList.add(SHPG_STS.STAGED);
            shpgStsCdList.add(SHPG_STS.INSHED);
            // QC#20681 Add Start
            shpgStsCdList.add(SHPG_STS.SHIPPED);
            shpgStsCdList.add(SHPG_STS.CANCELLED);
            shpgStsCdList.add(SHPG_STS.INVOICED);
            // QC#20681 Add End
            shpgStsCdList.add(SHPG_STS.P_OR_O_PRINTED);
            ssmParam.put(BIND_SHPG_STS_CD_LIST, shpgStsCdList);
            ssmParam.put(BIND_EZUPTIME, bizLastUpdTs);
            ssmParam.put(BIND_D_EDI_PROC_STS_CD, internalVarConstMap.get(VAL_CUSA_EDI_PROC_STS_CANC));
            ssmParam.put(BIND_EDI_PROC_STS_CD_C, ACK_EDI_PROC_STS.CANCELLED);
            // QC#20737 Add Start
            ssmParam.put(BIND_LAK_STS_CD, LAK_STS.USED);
            List<String> cpoOrdTpCdList = new ArrayList<String>();
            if (ZYPCommonFunc.hasValue(this.cpoOrdTpCd)) {
                String[] cpoOrdTpCdTmp = this.cpoOrdTpCd.split(",");
                for (int j = 0; j < cpoOrdTpCdTmp.length; j++) {
                    cpoOrdTpCdList.add(cpoOrdTpCdTmp[j]);
                }
            }
            ssmParam.put(BIND_CPO_ORD_TP_CD_LIST, cpoOrdTpCdList);
            // QC#20737 Add End

            prdStmtCpo = ssmLLClient.createPreparedStatement("getWsEdiOrderNomalInfo", ssmParam, execParam);
            rsCpo = prdStmtCpo.executeQuery();

            String ediNum = null;
            String latestEdiNum = null;

            poAckBean poAckBean = new poAckBean();

            while (rsCpo.next()) {

                int currentRowNumEdi = rsCpo.getRow();
                ediNum = rsCpo.getString(H_CUST_EDI_PO_NUM);
                // START 2019/02/25 M.Naito [QC#30452, ADD]
                ediNum = getPoOrdNumFromXref(ediNum);
                // END 2019/02/25 M.Naito [QC#30452, ADD]
                poAckBean.setEdiLineNum(rsCpo.getString(D_EDI_PO_LINE_NUM));
                poAckBean.setEdiNum(ediNum);

                // Ack Status
                if (ACK_EDI_PROC_STS.CANCELLED.equals(rsCpo.getString(D_EDI_PROC_STS_CD))) {

                    poAckBean.setOrdDtlLastUpdTs(rsCpo.getString(D_EZUPTIME));
                    poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.CANCELLED);
                    poAckBean.setPoAckLineStsCd((PO_ACK_LINE_STS.CANCEL));
                    poAckBean.setAckDtlPtn("4");

                } else {

                    poAckBean.setOrdDtlLastUpdTs(rsCpo.getString(S_EZUPTIME));
                    poAckBean.setAckDtlPtn("2");

                    String cusaMdseCd = (String) rsCpo.getString(SP_MDSE_CD);
                    String custIssPoNum = (String) rsCpo.getString(H_CUST_ISS_PO_NUM);
                    // START 2019/02/25 M.Naito [QC#30452, ADD]
                    custIssPoNum = getPoOrdNumFromXref(custIssPoNum);
                    // END 2019/02/25 M.Naito [QC#30452, ADD]
                    // QC#18283 START
                    String ediLineNum = null;
                    // QC#18283 END
                    String poOrdDtlLineNum = "";

                    // Set Mdse mapping
                    // *********************************
                    // step 1
                    if (!ZYPCommonFunc.hasValue(rsCpo.getString(SP_SET_MDSE_CD))) {

                        ediLineNum = rsCpo.getString(D_EDI_NUM);
                        // START 2019/02/25 M.Naito [QC#30452, ADD]
                        ediLineNum = searchPoOrdDtlLineNum(custIssPoNum, ediLineNum);
                        // END 2019/02/25 M.Naito [QC#30452, ADD]

                    } else {

                        // step 2
                        // search csa cpo dtl
                        String cusaEdiNum = searchCsaCpoDtl(rsCpo.getString(H_CPO_ORD_NUM), rsCpo.getString(D_CPO_DTL_LINE_NUM));

                        if (ZYPCommonFunc.hasValue(cusaEdiNum)) {

                            // step 3
                            // search csa set parent
                            poOrdDtlLineNum = searchPoOrdDtlLineNum(custIssPoNum, cusaEdiNum);

                        }

                        List<Map<String, Object>> aslMdseList = null;

                        if (ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {

                            // step 4
                            // search csa set comp list
                            aslMdseList = searchAslMdseCd(custIssPoNum, poOrdDtlLineNum);

                        }

                        if (aslMdseList != null) {

                            boolean mdseMatched = false;

                            for (int j = 0; j < aslMdseList.size(); j++) {

                                Map<String, Object> aslMdseMap = aslMdseList.get(j);

                                // step 5 mdse code mapping
                                if (rsCpo.getString(SP_MDSE_CD).equals((String) aslMdseMap.get(ASL_MDSE_CD))) {

                                    ediLineNum = (String) aslMdseMap.get(PO_ORD_DTL_LINE_NUM);

                                    mdseMatched = true;

                                    break;

                                }

                            }

                            if (!mdseMatched) {

                                for (int i = 0; i < aslMdseList.size(); i++) {

                                    Map<String, Object> aslMdseMap = aslMdseList.get(i);

                                    // step 5 8 digit mdse code
                                    // mapping

                                    if (mappingWithOrdTakeMdse(rsCpo.getString(SP_MDSE_CD), (String) aslMdseMap.get(ASL_MDSE_CD))) {

                                        ediLineNum = (String) aslMdseMap.get(PO_ORD_DTL_LINE_NUM);

                                        break;

                                    }

                                }
                            }

                        }

                    }

                    if (!ZYPCommonFunc.hasValue(ediLineNum)) {

                        ediLineNum = rsCpo.getString(D_CPO_DTL_LINE_NUM).concat(rsCpo.getString(D_CPO_DTL_LINE_SUB_NUM));

                        poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.ITEM_CHANGE);

                    }

                    poAckBean.setEdiLineNum(ediLineNum);

                    // mapping Ack Status
                    // *****************************************
                    String shpgSts = rsCpo.getString(SP_SHPG_STS_CD);

                    if (ZYPCommonFunc.hasValue(shpgSts)) {

                        Map<String, Object> mappedAckStsMap = mappingAckStatus(shpgSts);

                        if (SHPG_STS.HARD_ALLOCATED.equals(shpgSts) || SHPG_STS.S_OR_O_CANCELLED.equals(shpgSts) || SHPG_STS.P_OR_O_CANCELLED.equals(shpgSts)) {

                            if ((BigDecimal.ZERO.compareTo(rsCpo.getBigDecimal(SP_SLS_HLD_QTY)) == 0) && (BigDecimal.ZERO.compareTo(rsCpo.getBigDecimal(SP_CR_HLD_QTY)) == 0)) {

                                poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.ALLOCATION);

                                // Item Change [IC]
                                if (isItemChanged(cusaMdseCd, custIssPoNum, ediLineNum)) {
                                    poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.ITEM_CHANGE);
                                } else {
                                    poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.RELEASED_FOR_SHIPMENT);
                                }

                            } else {

                                poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.HOLD);
                                poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.ITEM_ON_HOLD);

                            }

                        } else {

                        	// START QC#20737 Update of intangible item ACK status (for AR)
                            if (VND_PO_ACK_STS.SHIPPED.equals((String) mappedAckStsMap.get(VND_PO_ACK_STS_CD))) {

                                // ACK Status for AC
                                poAckBean.setPoAckLineStsCd((String) mappedAckStsMap.get(PO_ACK_LINE_STS_CD));
                                poAckBean.setVndPoAckStsCd((String) mappedAckStsMap.get(VND_PO_ACK_STS_CD));

                            } else if (ZYPConstant.FLG_OFF_N.equals(rsCpo.getString(INVTY_CTRL_FLG)) && !MDSE_TP.SET.equals(rsCpo.getString(MDSE_TP_CD))) {

                                if (ZYPConstant.FLG_OFF_N.equals(rsCpo.getString(AUTO_LIC_ALLOC_FLG))) {

                                    // Not check license
                                    poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.SO_PRINTED);
                                    poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.RELEASED_FOR_SHIPMENT);

                                } else {
                                    // Check license
                                    if (checkLakAssingn(rsCpo.getString(H_CPO_ORD_NUM), rsCpo.getString(D_CPO_DTL_LINE_NUM), rsCpo.getString(D_CPO_DTL_LINE_SUB_NUM), rsCpo.getString(SP_MDSE_CD))) {
                                        // License registered
                                        poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.SO_PRINTED);
                                        poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.RELEASED_FOR_SHIPMENT);

                                    } else {
                                        // No license registered
                                        poAckBean.setPoAckLineStsCd((String) mappedAckStsMap.get(PO_ACK_LINE_STS_CD));
                                        poAckBean.setVndPoAckStsCd((String) mappedAckStsMap.get(VND_PO_ACK_STS_CD));
                                    }
                                }

                            } else {

                                poAckBean.setPoAckLineStsCd((String) mappedAckStsMap.get(PO_ACK_LINE_STS_CD));
                                poAckBean.setVndPoAckStsCd((String) mappedAckStsMap.get(VND_PO_ACK_STS_CD));
                            }
                            // END QC#20737

                        }

                        // get ETD/ETA
                        // *****************************************
                        if (ONE.equals((String) mappedAckStsMap.get(ETD_DRV_PTRN_CD))) {
                            poAckBean.setETD(rsCpo.getString(SP_PSD_DT));

                        } else if (TWO.equals((String) mappedAckStsMap.get(ETD_DRV_PTRN_CD))) {

                            poAckBean.setETD(rsCpo.getString(SP_ACTL_SHIP_DT));

                        }

                        if (ONE.equals((String) mappedAckStsMap.get(ETA_DRV_PTRN_CD))) {

                            poAckBean.setETA(rsCpo.getString(SP_PDD_DT));

                        } else if (TWO.equals((String) mappedAckStsMap.get(ETA_DRV_PTRN_CD))) {

                            poAckBean.setETA(rsCpo.getString(ETA_DT));

                        }

                        // get Ack Status Name
                        String poAckLineStsTxt = getAckStsNm(poAckBean.getPoAckLineStsCd());
                        poAckBean.setPoAckLineStsTxt(poAckLineStsTxt);

                    }

                }
                // Ack Status Ptn
                poAckBean.setAckHdrPtn("2");

                // create Ack Detail
                boolean hdrCreateFlg = false;

                if (currentRowNumEdi == 1 || !latestEdiNum.equals(ediNum)) {

                    hdrCreateFlg = true;

                    if (this.poAckDtlWrkList.size() > 0) {
                        insertPoAck();
                        normalRecCnt++;
                        // QC#29245 mod start
                        // commit();
                        updateBizProc = true;
                        // QC#29245 mod end

                    }
                    this.poAckHdrWrkList.clear();
                    this.poAckDtlWrkList.clear();
                }

                // QC#25435
                if (VND_PO_ACK_STS.RELEASED_FOR_SHIPMENT.equals((String) poAckBean.getVndPoAckStsCd()) //
                        || VND_PO_ACK_STS.SHIPPED.equals((String) poAckBean.getVndPoAckStsCd()) //
                        || ZYPConstant.FLG_OFF_N.equals(rsCpo.getString(INVTY_CTRL_FLG)) && !MDSE_TP.SET.equals(rsCpo.getString(MDSE_TP_CD))) {

                    String cusaMdseCd = (String) rsCpo.getString(SP_MDSE_CD);
                    // START 2019/02/25 M.Naito [QC#30452, ADD]
                    String custIssPoNum = (String) rsCpo.getString(H_CUST_ISS_PO_NUM);
                    custIssPoNum = getPoOrdNumFromXref(custIssPoNum);
                    // END 2019/02/25 M.Naito [QC#30452, ADD]
                    String poAckLineStsCd = poAckBean.getPoAckLineStsCd();
                    String vndPoAckStsCd = poAckBean.getVndPoAckStsCd();

                    // Search Item Change Record
                    if (isItemChanged(cusaMdseCd, custIssPoNum, poAckBean.getEdiLineNum()) && checkItemChangeData(custIssPoNum, poAckBean.getEdiLineNum())) {
                        poAckBean icAckBean = poAckBean;
                        icAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.ITEM_CHANGE);
                        createAckData(rsCpo, icAckBean, hdrCreateFlg, false);

                        poAckBean.setPoAckLineStsCd(poAckLineStsCd);
                        poAckBean.setVndPoAckStsCd(vndPoAckStsCd);
                        hdrCreateFlg = false;
                    }
                }

                poAckBean = createAckData(rsCpo, poAckBean, hdrCreateFlg, false);

                latestEdiNum = ediNum;

            }

            if (this.poAckDtlWrkList.size() > 0) {
                insertPoAck();
                normalRecCnt++;
                // QC#29245 mod start
                // updateDsBizProcLog();
                // commit();
                updateBizProc = true;
                // QC#29245 mod start
                this.poAckHdrWrkList.clear();
                this.poAckDtlWrkList.clear();
            }

            // QC#29245 mod start
            if (updateBizProc) {
                updateDsBizProcLog();
                commit();
            }
            // QC#29245 mod end

            updateBizProc = false;
            
            // *******************getWsEdiOrderCancelInfo**************************************//
            Map<String, Object> ssmParamCancel = new HashMap<String, Object>();

            ssmParamCancel.put(BIND_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
            ssmParamCancel.put(BIND_EDI_TRD_PTNR_CD_LIST, trdPtnrCdList);
            ssmParamCancel.put(BIND_EDI_TRX_PRPS_CD, internalVarConstMap.get(VAL_CUSA_EDI_TRX_PRPS_ORIG));

            // QC#24404
            //ssmParamCancel.put(BIND_CPO_SRC_TP_CD, internalVarConstMap.get(VAL_CUSA_CPO_SRC_TP_GRP_CMPY));
            List<String> cpoSrcTpCdList4Cancel = new ArrayList<String>();
            cpoSrcTpCdList4Cancel.add(internalVarConstMap.get(VAL_CUSA_CPO_SRC_TP_GRP_CMPY));
            if (ZYPCommonFunc.hasValue(internalVarConstMap.get(VAL_CUSA_CPO_SRC_TP_EDI))) {
            	cpoSrcTpCdList4Cancel.add(internalVarConstMap.get(VAL_CUSA_CPO_SRC_TP_EDI));
            }
            ssmParamCancel.put(BIND_CPO_SRC_TP_CD_LIST, cpoSrcTpCdList4Cancel);

            ssmParamCancel.put(BIND_CPO_ORD_TP_CD, CPO_ORD_TP.SALES);
            ssmParamCancel.put(BIND_EDI_TRX_PRPS, ZYPConstant.FLG_ON_Y);
            ssmParamCancel.put(BIND_D_EDI_PROC_STS_CD, internalVarConstMap.get(VAL_CUSA_EDI_PROC_STS_ITRL_ERR));

            List<String> hEediProcStsCdList = new ArrayList<String>();

            hEediProcStsCdList.add(internalVarConstMap.get(VAL_CUSA_EDI_PROC_STS_CANC));
            hEediProcStsCdList.add(internalVarConstMap.get(VAL_CUSA_EDI_PROC_STS_ERR));
            ssmParamCancel.put(BIND_H_EDI_PROC_STS_CD_LIST, hEediProcStsCdList);
            ssmParamCancel.put(BIND_EDI_PROC_STS_CD_C, ACK_EDI_PROC_STS.CANCELLED);
            ssmParamCancel.put(BIND_EDI_PROC_STS_CD_E, ACK_EDI_PROC_STS.ERROR);
            ssmParamCancel.put(BIND_EZUPTIME, bizLastUpdTs);

            prdStmtEdi = ssmLLClient.createPreparedStatement("getWsEdiOrderCancelInfo", ssmParamCancel, execParam);
            rsEdi = prdStmtEdi.executeQuery();

            String ediNumEdi = null;
            String latestEdiNumEdi = null;

            poAckBean poAckBeanEdi = new poAckBean();

            while (rsEdi.next()) {

                int currentRowNumEdi = rsEdi.getRow();
                ediNumEdi = rsEdi.getString(H_CUST_EDI_PO_NUM);
                // START 2019/02/25 M.Naito [QC#30452, ADD]
                ediNumEdi = getPoOrdNumFromXref(ediNumEdi);
                // END 2019/02/25 M.Naito [QC#30452, ADD]
                poAckBeanEdi.setEdiNum(ediNumEdi);
                poAckBeanEdi.setEdiLineNum(rsEdi.getString(D_EDI_PO_LINE_NUM));

                // Detail Last Update Time
                // Ack Status
                if (ACK_EDI_PROC_STS.CANCELLED.equals(rsEdi.getString(D_EDI_PROC_STS_CD))) {

                    poAckBeanEdi.setOrdDtlLastUpdTs(rsEdi.getString(D_EZUPTIME));
                    poAckBeanEdi.setVndPoAckStsCd(VND_PO_ACK_STS.CANCELLED);
                    poAckBeanEdi.setPoAckLineStsCd(PO_ACK_LINE_STS.CANCEL);
                    poAckBeanEdi.setAckDtlPtn("3");

                } else {

                    poAckBeanEdi.setOrdDtlLastUpdTs(rsEdi.getString(S_EZUPTIME));
                    poAckBeanEdi.setVndPoAckStsCd(VND_PO_ACK_STS.ERROR);
                    poAckBeanEdi.setPoAckLineStsCd(PO_ACK_LINE_STS.ERROR);
                    poAckBeanEdi.setAckDtlPtn("4");

                }

                // get Ack Status Name
                String poAckLineStsTxt = getAckStsNm(poAckBeanEdi.getPoAckLineStsCd());
                poAckBeanEdi.setPoAckLineStsTxt(poAckLineStsTxt);

                // Ack Status Ptn
                if (ACK_EDI_PROC_STS.CANCELLED.equals(rsEdi.getString(H_EDI_PROC_STS_CD))) {
                    poAckBeanEdi.setAckHdrPtn("3");

                } else {
                    poAckBeanEdi.setAckHdrPtn("4");

                }

                // create Ack Detail
                boolean hdrCreateFlg = false;

                if (currentRowNumEdi == 1 || !latestEdiNumEdi.equals(ediNumEdi)) {

                    hdrCreateFlg = true;

                    if (this.poAckDtlWrkList.size() > 0) {
                        insertPoAck();
                        normalRecCnt++;
                        // QC#29245 mod start
                        // commit();
                        updateBizProc = true;
                        // QC#29245 mod end
                    }

                    this.poAckHdrWrkList.clear();
                    this.poAckDtlWrkList.clear();
                }

                poAckBeanEdi = createAckData(rsEdi, poAckBeanEdi, hdrCreateFlg, false);

                latestEdiNumEdi = ediNumEdi;

            }

            // QC#29245 mod start
            if (this.poAckDtlWrkList.size() > 0) {
                insertPoAck();
                normalRecCnt++;
                updateBizProc = true;
                // updateDsBizProcLog();
                // commit();
            }

            // QC#29245 mod start
            if (updateBizProc) {
                updateDsBizProcLog();
                commit();
            }
            // QC#29245 mod end

        } catch (SQLException e) {

            termCd = TERM_CD.ABNORMAL_END;
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
            throw new S21AbendException(e);

        } catch (Exception e) {

            termCd = TERM_CD.ABNORMAL_END;
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            S21InfoLogOutput.println("Error Message: " + e);
            throw new S21AbendException(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(prdStmtCpo, rsCpo);
            S21SsmLowLevelCodingClient.closeResource(prdStmtEdi, rsEdi);
            this.poAckHdrWrkList.clear();
            this.poAckDtlWrkList.clear();

        }

    }

    /**
     * getWsOrderInfo
     * @return queryObjectList
     */
    private void createPoAckWs(S21SsmExecutionParameter execParam) {

        PreparedStatement prdStmtSo = null;
        ResultSet rsSo = null;
        String vndInvtyLocCd = null;
        String custIssPoNum = null;
        String latestCustIssPoNum = null;
        String cusaMdseCd = null;
        String ediLineNum = null;
        
        // QC#29245 add start
        boolean updateBizProc = false;
        // QC#29245 add end

        HashMap<String, String> internalVarConstMap = new HashMap<String, String>();

        for (String itemName : INTERNAL_VAR_CHAR_CONST) {
            internalVarConstMap.put(itemName, null);
        }

        try {

            getVarConstCd(internalVarConstMap);
            
         // get Bill To/Sell To
            // ********************************************************************
            Map<String, List> groupCompanyList = getGroupCompany(trdPtnrCdList);

            Map<String, Object> ssmParam = new HashMap<String, Object>();

            List<String> shpgStsCdList = new ArrayList<String>();

            shpgStsCdList.add(SHPG_STS.SAVED);
            shpgStsCdList.add(SHPG_STS.INSTALLED);

            ssmParam.put(BIND_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
            // QC#26090 ADD START
            // ssmParam.put(BIND_EZINUSERID,
            // internalVarConstMap.get("cusaCpoHdlgApiIntfcId"));
            // QC#26090 ADD START
            ssmParam.put(BIND_CPO_ORD_TP_CD, CPO_ORD_TP.SALES);
            ssmParam.put(BIND_TRX_SRC_TP_CD, TRX_SRC_TP.WHOLE_SALES);
            ssmParam.put(BIND_EZUPTIME, bizLastUpdTs);
            ssmParam.put(BIND_SHPG_STS_CD_LIST, shpgStsCdList);
            // QC#20737 Add Start
            ssmParam.put(BIND_LAK_STS_CD, LAK_STS.USED);
            List<String> cpoOrdTpCdList = new ArrayList<String>();
            if (ZYPCommonFunc.hasValue(this.cpoOrdTpCd)) {
                String[] cpoOrdTpCdTmp = this.cpoOrdTpCd.split(",");
                for (int j = 0; j < cpoOrdTpCdTmp.length; j++) {
                    cpoOrdTpCdList.add(cpoOrdTpCdTmp[j]);
                }
            }
            ssmParam.put(BIND_CPO_ORD_TP_CD_LIST, cpoOrdTpCdList);
            // QC#20737 Add End

            // QC#26090 ADD START
            ssmParam.put("billToCustCdList", groupCompanyList.get("BILL_TO_CUST_CD"));
            ssmParam.put("sellToCustCdList", groupCompanyList.get("SELL_TO_CUST_CD"));
            // QC#26090 ADD END

            prdStmtSo = ssmLLClient.createPreparedStatement("getWsOrderInfo", ssmParam, execParam);
            rsSo = prdStmtSo.executeQuery();

            poAckBean poAckBean = new poAckBean();

            while (rsSo.next()) {

                cusaMdseCd = (String) rsSo.getString(MDSE_CD);
                custIssPoNum = rsSo.getString(CUST_ISS_PO_NUM);
                // QC#18283 START
                ediLineNum = null;
                // QC#18283 END

                // START 2019/02/25 M.Naito [QC#30452, ADD]
                custIssPoNum = getPoOrdNumFromXref(custIssPoNum);
                // END 2019/02/25 M.Naito [QC#30452, ADD]

                if (ZYPConstant.FLG_ON_Y.equals(rsSo.getString(PO_REQ_FLG))) {

                    vndInvtyLocCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_DROP_SHIP_WH_CD, this.glblCmpyCd);

                } else {

                    vndInvtyLocCd = rsSo.getString(INVTY_LOC_CD);

                }

                int currentRowNum = rsSo.getRow();

                String poOrdDtlLineNum = null;
                String shpgSts = rsSo.getString(SHPG_STS_CD);

                // Set Mdse mapping
                // *********************************
                // step 1
                if (!ZYPCommonFunc.hasValue(rsSo.getString(SET_MDSE_CD))) {

                    ediLineNum = rsSo.getString(EDI_NUM);
                    // START 2019/02/25 M.Naito [QC#30452, ADD]
                    ediLineNum = searchPoOrdDtlLineNum(custIssPoNum, ediLineNum);
                    // END 2019/02/25 M.Naito [QC#30452, ADD]

                } else {

                    // step 2
                    // search csa cpo dtl
                    String cusaEdiNum = searchCsaCpoDtl(rsSo.getString(CPO_ORD_NUM), rsSo.getString(CPO_DTL_LINE_NUM));

                    if (ZYPCommonFunc.hasValue(cusaEdiNum)) {

                        // step 3
                        // search csa set parent
                        poOrdDtlLineNum = searchPoOrdDtlLineNum(custIssPoNum, cusaEdiNum);

                    }

                    List<Map<String, Object>> aslMdseList = null;

                    if (ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {

                        // step 4
                        // search csa set comp list
                        aslMdseList = searchAslMdseCd(custIssPoNum, poOrdDtlLineNum);

                    }

                    if (aslMdseList != null) {

                        boolean mdseMatched = false;

                        for (int j = 0; j < aslMdseList.size(); j++) {

                            Map<String, Object> aslMdseMap = aslMdseList.get(j);

                            // step 5 mdse code mapping
                            if (rsSo.getString(MDSE_CD).equals((String) aslMdseMap.get(ASL_MDSE_CD))) {

                                ediLineNum = (String) aslMdseMap.get(PO_ORD_DTL_LINE_NUM);

                                mdseMatched = true;

                                break;

                            }

                        }

                        if (!mdseMatched) {

                            for (int i = 0; i < aslMdseList.size(); i++) {

                                Map<String, Object> aslMdseMap = aslMdseList.get(i);

                                // step 5 8 digit mdse code
                                // mapping

                                if (mappingWithOrdTakeMdse(rsSo.getString(MDSE_CD), (String) aslMdseMap.get(ASL_MDSE_CD))) {

                                    ediLineNum = (String) aslMdseMap.get(PO_ORD_DTL_LINE_NUM);

                                    break;

                                }

                            }
                        }

                    }

                }

                if (!ZYPCommonFunc.hasValue(ediLineNum)) {

                    ediLineNum = rsSo.getString(CPO_DTL_LINE_NUM).concat(rsSo.getString(CPO_DTL_LINE_SUB_NUM));

                    poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.ITEM_CHANGE);

                }

                poAckBean.setEdiLineNum(ediLineNum);

                // mapping Ack Status
                // *****************************************

                Map<String, Object> mappedAckStsMap = mappingAckStatus(shpgSts);

                if (SHPG_STS.HARD_ALLOCATED.equals(shpgSts) || SHPG_STS.S_OR_O_CANCELLED.equals(shpgSts) || SHPG_STS.P_OR_O_CANCELLED.equals(shpgSts)) {

                    if ((BigDecimal.ZERO.compareTo(rsSo.getBigDecimal(SLS_HLD_QTY)) == 0) && (BigDecimal.ZERO.compareTo(rsSo.getBigDecimal(CR_HLD_QTY)) == 0)) {

                        poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.ALLOCATION);
                        // QC#18471 Mod
                        if (isItemChangedWS(cusaMdseCd, custIssPoNum, ediLineNum)) {

                            poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.ITEM_CHANGE);

                        } else {

                            poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.RELEASED_FOR_SHIPMENT);

                        }

                    } else {

                        poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.HOLD);
                        poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.ITEM_ON_HOLD);

                    }

                } else {

                	// START QC#20737 Update of intangible item ACK status (for AR)
                	if (VND_PO_ACK_STS.SHIPPED.equals((String) mappedAckStsMap.get(VND_PO_ACK_STS_CD))) {

                		// ACK Status for AC
                		poAckBean.setPoAckLineStsCd((String) mappedAckStsMap.get(PO_ACK_LINE_STS_CD));
                        poAckBean.setVndPoAckStsCd((String) mappedAckStsMap.get(VND_PO_ACK_STS_CD));

                	} else if (ZYPConstant.FLG_OFF_N.equals(rsSo.getString(INVTY_CTRL_FLG))
                			&& !MDSE_TP.SET.equals(rsSo.getString(MDSE_TP_CD))) {

                		if (ZYPConstant.FLG_OFF_N.equals(rsSo.getString(AUTO_LIC_ALLOC_FLG))) {

                			// Not check license
                			poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.SO_PRINTED);
                            poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.RELEASED_FOR_SHIPMENT);

                		} else {
                			// Check license
                    		if (checkLakAssingn(rsSo.getString(CPO_ORD_NUM),
                    				rsSo.getString(CPO_DTL_LINE_NUM),
                    				rsSo.getString(CPO_DTL_LINE_SUB_NUM),
                    				rsSo.getString(MDSE_CD))) {
                    			// License registered
                    			poAckBean.setPoAckLineStsCd(PO_ACK_LINE_STS.SO_PRINTED);
                                poAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.RELEASED_FOR_SHIPMENT);

                    		} else {
                    			// No license registered
                    			poAckBean.setPoAckLineStsCd((String) mappedAckStsMap.get(PO_ACK_LINE_STS_CD));
                                poAckBean.setVndPoAckStsCd((String) mappedAckStsMap.get(VND_PO_ACK_STS_CD));
                    		}
                		}

                    } else {

                    	poAckBean.setPoAckLineStsCd((String) mappedAckStsMap.get(PO_ACK_LINE_STS_CD));
                        poAckBean.setVndPoAckStsCd((String) mappedAckStsMap.get(VND_PO_ACK_STS_CD));
                	}
                	// END QC#20737

                }

                // get ETD/ETA
                // *****************************************
                if (ONE.equals((String) mappedAckStsMap.get(ETD_DRV_PTRN_CD))) {

                    poAckBean.setETD(rsSo.getString(PSD_DT));

                } else if (TWO.equals((String) mappedAckStsMap.get(ETD_DRV_PTRN_CD))) {

                    poAckBean.setETD(rsSo.getString(ACTL_SHIP_DT));

                }

                if (ONE.equals((String) mappedAckStsMap.get(ETA_DRV_PTRN_CD))) {

                    poAckBean.setETA(rsSo.getString(PDD_DT));

                } else if (TWO.equals((String) mappedAckStsMap.get(ETA_DRV_PTRN_CD))) {

                    poAckBean.setETA(rsSo.getString(ETA_DT));

                }

                // get Ack Status Name
                String poAckLineStsTxt = getAckStsNm(poAckBean.getPoAckLineStsCd());

                poAckBean.setPoAckLineStsTxt(poAckLineStsTxt);
                poAckBean.setEdiNum(custIssPoNum);
                poAckBean.setAckHdrPtn(PO_ACK_CREATE_PTN_DIRECT_CPO);
                poAckBean.setAckDtlPtn(PO_ACK_CREATE_PTN_DIRECT_CPO);
                poAckBean.setVndInvtyLocCd(vndInvtyLocCd);

                // create Ack Detail
                boolean hdrCreateFlg = false;

                if (currentRowNum == 1 || !latestCustIssPoNum.equals(custIssPoNum)) {

                    hdrCreateFlg = true;

                    if (this.poAckDtlWrkList.size() > 0) {
                        insertPoAck();
                        normalRecCnt++;
                        // QC#29245 mod start
                        updateBizProc = true;
                        // commit();
                        // QC#29245 mod end
                    }

                    this.poAckHdrWrkList.clear();
                    this.poAckDtlWrkList.clear();
                }

                // QC#25435
                if (VND_PO_ACK_STS.RELEASED_FOR_SHIPMENT.equals((String) poAckBean.getVndPoAckStsCd()) //
                        || VND_PO_ACK_STS.SHIPPED.equals((String) poAckBean.getVndPoAckStsCd()) //
                        || ZYPConstant.FLG_OFF_N.equals(rsSo.getString(INVTY_CTRL_FLG)) && !MDSE_TP.SET.equals(rsSo.getString(MDSE_TP_CD))) {

                    String poAckLineStsCd = poAckBean.getPoAckLineStsCd();
                    String vndPoAckStsCd = poAckBean.getVndPoAckStsCd();

                    // Search Item Change RecordisItemChangedWS
                    // START 2019/02/25 M.Naito [QC#30452, MOD]
//                    if (isItemChanged(cusaMdseCd, custIssPoNum, ediLineNum) && checkItemChangeData(custIssPoNum, ediLineNum)) {
                        if (isItemChangedWS(cusaMdseCd, custIssPoNum, ediLineNum) && checkItemChangeData(custIssPoNum, ediLineNum)) {
                    // END 2019/02/25 M.Naito [QC#30452, MOD]
                        poAckBean icAckBean = poAckBean;
                        icAckBean.setVndPoAckStsCd(VND_PO_ACK_STS.ITEM_CHANGE);
                        createAckData(rsSo, icAckBean, hdrCreateFlg, false);

                        poAckBean.setPoAckLineStsCd(poAckLineStsCd);
                        poAckBean.setVndPoAckStsCd(vndPoAckStsCd);
                        hdrCreateFlg = false;
                    }
                }

                poAckBean = createAckData(rsSo, poAckBean, hdrCreateFlg, false);

                latestCustIssPoNum = custIssPoNum;

            }

            if (this.poAckDtlWrkList.size() > 0) {
                insertPoAck();
                normalRecCnt++;
                // QC#29245 mod start
                // updateDsBizProcLog();
                updateBizProc = true;
                // QC#29245 mod end
            }

            // QC#29245 mod start
            if (updateBizProc) {
                updateDsBizProcLog();
                commit();
            }
            // QC#29245 mod end

        } catch (SQLException e) {

            termCd = TERM_CD.ABNORMAL_END;
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
            throw new S21AbendException(e);

        } catch (Exception e) {

            termCd = TERM_CD.ABNORMAL_END;
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            S21InfoLogOutput.println("Error Message: " + e);
            throw new S21AbendException(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(prdStmtSo, rsSo);
            this.poAckHdrWrkList.clear();
            this.poAckDtlWrkList.clear();

        }

    }

    /**
     * @param rs
     * @return
     * @throws SQLException
     */
    private boolean isItemChanged(String cusaMdseCd, String poOrdNum, String poOrdDtlLineNum) throws SQLException {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);
        ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);

        String aslMdseCd = (String) ssmBatchClient.queryObject("itemChangeCheck", ssmParam);

        if (ZYPCommonFunc.hasValue(aslMdseCd)) {
            // START 2021/05/12 J.Evangelista [QC#58718,MOD]
//            if (!cusaMdseCd.equals(aslMdseCd)) {
            if (!aslMdseCd.equals(cusaMdseCd)) {
            //   END 2021/05/12 J.Evangelista [QC#58718,MOD]
                return true;
            }
        } else {
            return true;
        }
        return false;
    }
    
    /**
     * Add QC#18471
     * @param rs
     * @return
     * @throws SQLException
     */
    private boolean isItemChangedWS(String cusaMdseCd, String poOrdNum, String poOrdDtlLineNum) throws SQLException {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);
        ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);

        String aslMdseCd = (String) ssmBatchClient.queryObject("itemChangeCheck", ssmParam);

        if (ZYPCommonFunc.hasValue(aslMdseCd)) {
            if (cusaMdseCd.equals(aslMdseCd)) {
                return false;
            }

            if (aslMdseCd.length() > 8) {
                aslMdseCd = aslMdseCd.substring(0, 8);
            }
            if (cusaMdseCd.length() > 8) {
                cusaMdseCd = cusaMdseCd.substring(0, 8);
            }

            ssmParam.put(BIND_ORD_TAKE_MDSE_CD, cusaMdseCd);
            String ordTakeCusaMdseCd = (String) ssmBatchClient.queryObject("itemChangeCheckOrdTakeMdse", ssmParam);
            ssmParam.put(BIND_ORD_TAKE_MDSE_CD, aslMdseCd);
            String ordTakeCsaMdseCd = (String) ssmBatchClient.queryObject("itemChangeCheckOrdTakeMdse", ssmParam);

            if (ZYPCommonFunc.hasValue(ordTakeCusaMdseCd) && ZYPCommonFunc.hasValue(ordTakeCsaMdseCd) && !ordTakeCusaMdseCd.equals(ordTakeCsaMdseCd)) {
                return true;
            }

        } else {
            return true;
        }
        return false;
    }

    /**
     * mappingAckStatus
     * @param shpgSts
     * @return
     */
    private Map<String, Object> mappingAckStatus(String shpgSts) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(BIND_VND_SHPG_STS_CD, shpgSts);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("mappingShpgStatus", ssmParam);

    }

    /**
     * @throws S21AbendException
     */
    private void insertPoAck() {

        PO_ACK_HDR_WRKTMsg[] insertHdrTMsg = new PO_ACK_HDR_WRKTMsg[poAckHdrWrkList.size()];

        for (int i = 0; i < poAckHdrWrkList.size(); i++) {
            insertHdrTMsg[i] = poAckHdrWrkList.get(i);
        }

        int hdrCnt = S21FastTBLAccessor.insert(insertHdrTMsg);

        if (hdrCnt != poAckHdrWrkList.size()) {

            rollback();

            termCd = TERM_CD.ABNORMAL_END;

            throw new S21AbendException(S21MessageFunc.clspGetMessage(NPAM1003E, new String[] {"PO_ACK_HDR_WRK" }));
        }

        PO_ACK_DTL_WRKTMsg[] insertDtlTMsg = new PO_ACK_DTL_WRKTMsg[poAckDtlWrkList.size()];

        for (int j = 0; j < poAckDtlWrkList.size(); j++) {

            insertDtlTMsg[j] = poAckDtlWrkList.get(j);
        }

        int dtlCnt = S21FastTBLAccessor.insert(insertDtlTMsg);

        if (dtlCnt != poAckDtlWrkList.size()) {

            rollback();

            termCd = TERM_CD.ABNORMAL_END;

            throw new S21AbendException(S21MessageFunc.clspGetMessage(NPAM1003E, new String[] {"PO_ACK_DTL_WRK" }));

        }

    }

    /**
     * updateDsBizProcLog
     * @throws SQLException
     */
    private void updateDsBizProcLog() {

        DS_BIZ_PROC_LOGTMsg outMsg = new DS_BIZ_PROC_LOGTMsg();

        if (!ZYPCommonFunc.hasValue(this.bizProcLogPk)) {

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);

            if (IF_ID_NPAA0031.equals(processMode)) {
                ZYPEZDItemValueSetter.setValue(outMsg.procPgmId, BUSINESS_ID.concat("1"));
            } else if (IF_ID_NPAA0032.equals(processMode)) {
                ZYPEZDItemValueSetter.setValue(outMsg.procPgmId, BUSINESS_ID.concat("2"));
            } else if (IF_ID_NPAA0041.equals(processMode)) {
                ZYPEZDItemValueSetter.setValue(outMsg.procPgmId, BUSINESS_ID.concat("3"));
            }

            outMsg.dsBizProcTrxNum.clear();
            outMsg.dsBizProcTrxDtlNum.clear();
            outMsg.dsBizProcTrxSubDtlNum.clear();
            outMsg.dsBizProcRqstSlsDt.clear();
            outMsg.dsBizProcDt.clear();
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastProcTs, ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT));
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastUpdTs, dsBizLastUpdTs);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(DS_BIZ_PROC_LOG_SQ));

            EZDTBLAccessor.insert(outMsg);

        } else {

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcLogPk, this.bizProcLogPk);
            outMsg = (DS_BIZ_PROC_LOGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(outMsg);

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);

            if (IF_ID_NPAA0031.equals(processMode)) {
                ZYPEZDItemValueSetter.setValue(outMsg.procPgmId, BUSINESS_ID.concat("1"));
            } else if (IF_ID_NPAA0032.equals(processMode)) {
                ZYPEZDItemValueSetter.setValue(outMsg.procPgmId, BUSINESS_ID.concat("2"));
            } else if (IF_ID_NPAA0041.equals(processMode)) {
                ZYPEZDItemValueSetter.setValue(outMsg.procPgmId, BUSINESS_ID.concat("3"));
            }

            outMsg.dsBizProcTrxNum.clear();
            outMsg.dsBizProcTrxDtlNum.clear();
            outMsg.dsBizProcTrxSubDtlNum.clear();
            outMsg.dsBizProcRqstSlsDt.clear();
            outMsg.dsBizProcDt.clear();
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastProcTs, ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT));
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastUpdTs, dsBizLastUpdTs);

            EZDTBLAccessor.update(outMsg);
        }

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();

            sb.append(outMsg.getTableName());

            rollback();

            termCd = TERM_CD.ABNORMAL_END;

            throw new S21AbendException(S21MessageFunc.clspGetMessage(NPAM1003E, new String[] {sb.toString() }));

        }

    }

    /**
     * insertOrUpdateAck
     * @param rs ResultSet
     * @param internalVariableMap HashMap<String, String>
     * @throws SQLException
     */
    private poAckBean createAckData(ResultSet rs, poAckBean poAckBean, boolean hdrCreateFlg, boolean isForceInsert) throws SQLException {

        if (hdrCreateFlg) {

            // Ack Number
            // ******************************************************************************
            Map<String, Object> ssmAckNumParam = new HashMap<String, Object>();
            ssmAckNumParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
            ssmAckNumParam.put(BIND_EDI_PO_ORD_NUM, poAckBean.getEdiNum());

            String nextPoAckNum = (String) ssmBatchClient.queryObject("getNextPoAckNum", ssmAckNumParam);

            // insert PO ACk HDR
            // ******************************************************************************
            PO_ACK_HDR_WRKTMsg inHdrMsg = new PO_ACK_HDR_WRKTMsg();

            ZYPEZDItemValueSetter.setValue(inHdrMsg.glblCmpyCd, this.glblCmpyCd);

            ZYPEZDItemValueSetter.setValue(inHdrMsg.poAckHdrWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_HDR_WRK_SQ));

            // if (poAckBean.getPoAckHdrWrkPk() == null) {
            poAckBean.setPoAckHdrWrkPk(inHdrMsg.poAckHdrWrkPk.getValue());
            // }

            inHdrMsg.poOrdNum.clear();
            ZYPEZDItemValueSetter.setValue(inHdrMsg.ackEdiProcStsCd, ACK_EDI_PROC_STS.SAVED);
            ZYPEZDItemValueSetter.setValue(inHdrMsg.ediRcvDateTs, ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT));
            ZYPEZDItemValueSetter.setValue(inHdrMsg.itrlIntfcId, interfaceId);

            if ((IF_ID_NPAA0031.equals(processMode))) {
                ZYPEZDItemValueSetter.setValue(inHdrMsg.vndCpoOrdNum, rs.getString(CPO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(inHdrMsg.ordHdrStsCd, rs.getString(ORD_HDR_STS_CD));
                ZYPEZDItemValueSetter.setValue(inHdrMsg.ordLastUpdTs, rs.getString(EZUPTIME));
                // START 2019/02/25 M.Naito [QC#30452, MOD]
//                ZYPEZDItemValueSetter.setValue(inHdrMsg.ediPoOrdNum, rs.getString(CUST_ISS_PO_NUM));
                ZYPEZDItemValueSetter.setValue(inHdrMsg.ediPoOrdNum, poAckBean.getEdiNum());
                // END 2019/02/25 M.Naito [QC#30452, MOD]

            } else if (PO_ACK_CREATE_PTN_DIRECT_CPO.equals(poAckBean.getAckHdrPtn()) || (IF_ID_NPAA0032.equals(processMode) && PO_ACK_CREATE_PTN_EDI_CPO.equals(poAckBean.getAckHdrPtn()))) {

                ZYPEZDItemValueSetter.setValue(inHdrMsg.vndCpoOrdNum, rs.getString(H_CPO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(inHdrMsg.ordHdrStsCd, rs.getString(H_ORD_HDR_STS_CD));
                ZYPEZDItemValueSetter.setValue(inHdrMsg.ordLastUpdTs, rs.getString(HC_EZUPTIME));
                // START 2019/02/25 M.Naito [QC#30452, MOD]
//                ZYPEZDItemValueSetter.setValue(inHdrMsg.ediPoOrdNum, rs.getString(H_CUST_ISS_PO_NUM));
                ZYPEZDItemValueSetter.setValue(inHdrMsg.ediPoOrdNum, getPoOrdNumFromXref(rs.getString(H_CUST_ISS_PO_NUM)));
                // END 2019/02/25 M.Naito [QC#30452, MOD]

            } else if (IF_ID_NPAA0032.equals(processMode) && (PO_ACK_CREATE_PTN_EDI_CANCEL.equals(poAckBean.getAckHdrPtn()) || PO_ACK_CREATE_PTN_EDI_ERROR.equals(poAckBean.getAckHdrPtn()))) {

                inHdrMsg.vndCpoOrdNum.clear();
                inHdrMsg.ordHdrStsCd.clear();
                ZYPEZDItemValueSetter.setValue(inHdrMsg.ordLastUpdTs, rs.getString(H_EZUPTIME));
                // START 2019/02/25 M.Naito [QC#30452, MOD]
//                ZYPEZDItemValueSetter.setValue(inHdrMsg.ediPoOrdNum, rs.getString(H_CUST_EDI_PO_NUM));
                ZYPEZDItemValueSetter.setValue(inHdrMsg.ediPoOrdNum, poAckBean.getEdiNum());
                // END 2019/02/25 M.Naito [QC#30452, MOD]


            } else if ((IF_ID_NPAA0041.equals(processMode) && PO_ACK_CREATE_PTN_PARTS.equals(poAckBean.getAckHdrPtn()))) {

                inHdrMsg.vndCpoOrdNum.clear();
                inHdrMsg.ordHdrStsCd.clear();
                ZYPEZDItemValueSetter.setValue(inHdrMsg.ordLastUpdTs, rs.getString(CP_EZUPTIME));
                // START 2019/02/25 M.Naito [QC#30452, MOD]
//                ZYPEZDItemValueSetter.setValue(inHdrMsg.ediPoOrdNum, rs.getString(P_NUM_CUST_PO));
                ZYPEZDItemValueSetter.setValue(inHdrMsg.ediPoOrdNum, poAckBean.getEdiNum());
                // END 2019/02/25 M.Naito [QC#30452, MOD]

            }

            ZYPEZDItemValueSetter.setValue(inHdrMsg.poAckUpdProcFlg, ZYPConstant.FLG_OFF_N);
            inHdrMsg.vndCd.clear();
            ZYPEZDItemValueSetter.setValue(inHdrMsg.poAckHdrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_HDR_SQ));
            ZYPEZDItemValueSetter.setValue(inHdrMsg.poAckNum, nextPoAckNum);

            poAckHdrWrkList.add(inHdrMsg);

        }

        // insert PO ACk DTL
        // ******************************************************************************
        PO_ACK_DTL_WRKTMsg inDtlMsg = new PO_ACK_DTL_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(inDtlMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inDtlMsg.poAckDtlWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_DTL_WRK_SQ));
        inDtlMsg.poOrdDtlLineNum.clear();
        ZYPEZDItemValueSetter.setValue(inDtlMsg.poOrdDtlSubLineNum, PO_ORD_DTL_SUB_LINE_NUM_001);

        if ((IF_ID_NPAA0031.equals(processMode))) {

            ZYPEZDItemValueSetter.setValue(inDtlMsg.shpgPlnDplyLineNum, rs.getString(SHPG_PLN_DPLY_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.mdseCd, rs.getString(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.mdseNm, rs.getString(MDSE_NM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.trdPtnrSkuCd, rs.getString(CUST_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ordQty, rs.getBigDecimal(ORD_QTY));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.thisMthFobCostAmt, rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ccyCd, rs.getString(CCY_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.uomCd, rs.getString(CUST_UOM_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.etdDt, poAckBean.getETD());
            ZYPEZDItemValueSetter.setValue(inDtlMsg.etaDt, poAckBean.getETA());
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndCpoOrdNum, rs.getString(CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndCpoDtlLineNum, rs.getString(CPO_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndCpoDtlLineSubNum, rs.getString(CPO_DTL_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndCpoLineStsCd, rs.getString(ORD_LINE_STS_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.shpgStsCd, rs.getString(SHPG_STS_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.origTrdPtnrSkuCd, rs.getString(CUST_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.origOrdQty, rs.getBigDecimal(ORIG_ORD_QTY));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.origVndMdseCd, searchOrigAslMdseCd(poAckBean));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.shpgPlnNum, rs.getString(SHPG_PLN_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndCpoCratTs, rs.getString(CPO_ORD_TS));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ordDtlLastUpdTs, rs.getString(EZUPTIME));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndInvtyLocCd, poAckBean.getVndInvtyLocCd());
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndIssPoOrdNum, rs.getString(PO_ORD_NUM));
            // START 2019/02/25 M.Naito [QC#30452, MOD]
//            ZYPEZDItemValueSetter.setValue(inDtlMsg.ediPoOrdNum, rs.getString(CUST_ISS_PO_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ediPoOrdNum, poAckBean.getEdiNum());
            // END 2019/02/25 M.Naito [QC#30452, MOD]
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ediPoOrdDtlLineNum, poAckBean.getEdiLineNum());
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndOtbdCarrCd, rs.getString(CARR_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndOtbdCarrNm, rs.getString(CARR_NM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.proNum, rs.getString(PRO_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndSoNum, rs.getString(SO_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndSoSlpNum, rs.getString(SO_SLP_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndShipToCustCd, rs.getString(SHIP_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndShipToCustLocNm, rs.getString(SHIP_TO_LOC_NM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndSellToCustCd, rs.getString(SELL_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndSellToCustLocNm, rs.getString(LOC_NM));

            if (PO_MDSE_CMPSN_TP.CHILD.equals(searchMdseCmpsnTpCd(poAckBean))) {
                ZYPEZDItemValueSetter.setValue(inDtlMsg.vndChildBomPrcAmt, rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT));
            } else {
                inDtlMsg.vndChildBomPrcAmt.clear();
            }

        } else if (PO_ACK_CREATE_PTN_DIRECT_CPO.equals(poAckBean.getAckDtlPtn()) || (IF_ID_NPAA0032.equals(processMode) && PO_ACK_CREATE_PTN_EDI_CPO.equals(poAckBean.getAckDtlPtn()))) {

            ZYPEZDItemValueSetter.setValue(inDtlMsg.shpgPlnDplyLineNum, rs.getString(SP_SHPG_PLN_DPLY_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.mdseCd, rs.getString(SP_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.mdseNm, rs.getString(M_MDSE_NM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.trdPtnrSkuCd, rs.getString(D_CUST_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ordQty, rs.getBigDecimal(SP_ORD_QTY));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.thisMthFobCostAmt, rs.getBigDecimal(D_ENT_DEAL_NET_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ccyCd, rs.getString(D_CCY_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.uomCd, rs.getString(SP_CUST_UOM_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.etdDt, poAckBean.getETD());
            ZYPEZDItemValueSetter.setValue(inDtlMsg.etaDt, poAckBean.getETA());
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndCpoOrdNum, rs.getString(H_CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndCpoDtlLineNum, rs.getString(D_CPO_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndCpoDtlLineSubNum, rs.getString(D_CPO_DTL_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndCpoLineStsCd, rs.getString(D_ORD_LINE_STS_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.shpgStsCd, rs.getString(SP_SHPG_STS_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.origTrdPtnrSkuCd, rs.getString(D_CUST_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.origOrdQty, rs.getBigDecimal(D_ORD_QTY));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.origVndMdseCd, searchOrigAslMdseCd(poAckBean));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.shpgPlnNum, rs.getString(SP_SHPG_PLN_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndCpoCratTs, rs.getString(H_CPO_ORD_TS));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ordDtlLastUpdTs, rs.getString(SP_EZUPTIME));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndInvtyLocCd, poAckBean.getVndInvtyLocCd());
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndIssPoOrdNum, rs.getString(SP_PO_ORD_NUM));
            // START 2019/02/25 M.Naito [QC#30452, MOD]
//            ZYPEZDItemValueSetter.setValue(inDtlMsg.ediPoOrdNum, rs.getString(H_CUST_ISS_PO_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ediPoOrdNum, getPoOrdNumFromXref(rs.getString(H_CUST_ISS_PO_NUM)));
            // END 2019/02/25 M.Naito [QC#30452, MOD]
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ediPoOrdDtlLineNum, poAckBean.getEdiLineNum());
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndOtbdCarrCd, rs.getString(SP_CARR_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndOtbdCarrNm, rs.getString(SP_CARR_NM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.proNum, rs.getString(SP_PRO_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndSoNum, rs.getString(SP_SO_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndSoSlpNum, rs.getString(SP_SO_SLP_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndShipToCustCd, rs.getString(D_SHIP_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndShipToCustLocNm, rs.getString(D_SHIP_TO_LOC_NM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndSellToCustCd, rs.getString(H_SELL_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndSellToCustLocNm, rs.getString(CS_LOC_NUM));

            if (PO_MDSE_CMPSN_TP.CHILD.equals(searchMdseCmpsnTpCd(poAckBean))) {

                ZYPEZDItemValueSetter.setValue(inDtlMsg.vndChildBomPrcAmt, rs.getBigDecimal(D_ENT_DEAL_NET_UNIT_PRC_AMT));

            } else {

                inDtlMsg.vndChildBomPrcAmt.clear();
            }

        } else if (IF_ID_NPAA0032.equals(processMode) && (PO_ACK_CREATE_PTN_EDI_CANCEL.equals(poAckBean.getAckDtlPtn()) || PO_ACK_CREATE_PTN_EDI_ERROR.equals(poAckBean.getAckDtlPtn()))) {

            ZYPEZDItemValueSetter.setValue(inDtlMsg.shpgPlnDplyLineNum, SHPG_PLN_DPLY_LINE_NUM_001);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.mdseCd, rs.getString(D_CUSA_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.mdseNm, rs.getString(M_MDSE_NM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.trdPtnrSkuCd, rs.getString(D_TRD_PTNR_SKU_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ordQty, rs.getBigDecimal(D_ORD_QTY));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.thisMthFobCostAmt, rs.getBigDecimal(D_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ccyCd, CCY_CD_USD);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.uomCd, rs.getString(D_UOM_CD));
            inDtlMsg.etdDt.clear();
            inDtlMsg.etaDt.clear();
            inDtlMsg.vndCpoOrdNum.clear();
            inDtlMsg.vndCpoDtlLineNum.clear();
            inDtlMsg.vndCpoDtlLineSubNum.clear();
            inDtlMsg.vndCpoLineStsCd.clear();
            inDtlMsg.shpgStsCd.clear();
            ZYPEZDItemValueSetter.setValue(inDtlMsg.origTrdPtnrSkuCd, rs.getString(D_ORIG_TRD_PTNR_SKU_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.origOrdQty, rs.getBigDecimal(D_ORIG_ORD_QTY));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.origVndMdseCd, rs.getString(D_ORIG_CUSA_MDSE_CD));
            inDtlMsg.shpgPlnNum.clear();
            inDtlMsg.vndCpoCratTs.clear();
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ordDtlLastUpdTs, poAckBean.getOrdDtlLastUpdTs());
            inDtlMsg.vndInvtyLocCd.clear();
            inDtlMsg.vndIssPoOrdNum.clear();
            // START 2019/02/25 M.Naito [QC#30452, MOD]
//            ZYPEZDItemValueSetter.setValue(inDtlMsg.ediPoOrdNum, rs.getString(D_CUST_EDI_PO_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ediPoOrdNum, getPoOrdNumFromXref(rs.getString(D_CUST_EDI_PO_NUM)));
            // END 2019/02/25 M.Naito [QC#30452, MOD]
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ediPoOrdDtlLineNum, poAckBean.getEdiLineNum());
            inDtlMsg.vndOtbdCarrCd.clear();
            inDtlMsg.vndOtbdCarrNm.clear();
            inDtlMsg.proNum.clear();
            inDtlMsg.vndSoNum.clear();
            inDtlMsg.vndSoSlpNum.clear();
            inDtlMsg.vndShipToCustCd.clear();
            inDtlMsg.vndShipToCustLocNm.clear();
            inDtlMsg.vndSellToCustCd.clear();
            inDtlMsg.vndSellToCustLocNm.clear();
            inDtlMsg.vndChildBomPrcAmt.clear();

        } else if ((IF_ID_NPAA0041.equals(processMode) && PO_ACK_CREATE_PTN_PARTS.equals(poAckBean.getAckDtlPtn()))) {

            ZYPEZDItemValueSetter.setValue(inDtlMsg.shpgPlnDplyLineNum, SHPG_PLN_DPLY_LINE_NUM_001);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.mdseCd, rs.getString(P_PARTS_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.mdseNm, rs.getString(P_DESC_PART));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.trdPtnrSkuCd, rs.getString(P_CUST_PART_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ordQty, rs.getBigDecimal(P_QTY_ACPT_ORDR));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.thisMthFobCostAmt, rs.getBigDecimal(P_PRC_UNIT));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ccyCd, CCY_CD_USD);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.uomCd, UOM_CD_EA);

            if (ZYPCommonFunc.hasValue(poAckBean.getETD())) {
                ZYPEZDItemValueSetter.setValue(inDtlMsg.etdDt, poAckBean.getETD());
            }

            if (ZYPCommonFunc.hasValue(poAckBean.getETA())) {
                ZYPEZDItemValueSetter.setValue(inDtlMsg.etaDt, poAckBean.getETA());
            }

            // START 2022/11/08 A.Cullano [QC#60776, ADD]
            ZYPEZDItemValueSetter.setValue(inDtlMsg.inbdVisUpdTs, rs.getString(POYO_EZUPTIME));
            // START 2022/11/08 A.Cullano [QC#60776, ADD]

            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndCpoOrdNum, rs.getString(P_NUM_ORDR));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndCpoDtlLineNum, poAckBean.getVndCpoDtlLineSubNum());
            inDtlMsg.vndCpoDtlLineSubNum.clear();
            inDtlMsg.vndCpoLineStsCd.clear();
            inDtlMsg.shpgStsCd.clear();
            ZYPEZDItemValueSetter.setValue(inDtlMsg.origTrdPtnrSkuCd, rs.getString(P_CUST_PART_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.origOrdQty, rs.getBigDecimal(P_QTY_PCSE_ORDR));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.origVndMdseCd, rs.getString(P_ORIG_TRD_PTNR_SKU_CD));
            inDtlMsg.shpgPlnNum.clear();
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndCpoCratTs, rs.getString(P_DATE_ORDR_RCV));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ordDtlLastUpdTs, rs.getString(CP_EZUPTIME));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndInvtyLocCd, rs.getString(P_CODE_WH));
            inDtlMsg.vndIssPoOrdNum.clear();
            // START 2019/02/25 M.Naito [QC#30452, MOD]
//            ZYPEZDItemValueSetter.setValue(inDtlMsg.ediPoOrdNum, rs.getString(P_NUM_CUST_PO));
//            ZYPEZDItemValueSetter.setValue(inDtlMsg.ediPoOrdDtlLineNum, rs.getString(P_NUM_LINE));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ediPoOrdNum, poAckBean.getEdiNum());
            ZYPEZDItemValueSetter.setValue(inDtlMsg.ediPoOrdDtlLineNum, poAckBean.getEdiLineNum());
            // END 2019/02/25 M.Naito [QC#30452, MOD]
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndOtbdCarrCd, rs.getString(P_CODE_SHIP_VIA));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndOtbdCarrNm, rs.getString(P_CODE_TABLE_DESCRIPTION));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.proNum, rs.getString(P_NUM_TRUCKER));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndSoNum, rs.getString(P_NUM_SO));
            inDtlMsg.vndSoSlpNum.clear();
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndShipToCustCd, rs.getString(P_CODE_CUST_SHIP_TO));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndShipToCustLocNm, rs.getString(PH_NAME_CUST_01));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndSellToCustCd, rs.getString(P_CODE_CUST_SOLD_TO));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.vndSellToCustLocNm, rs.getString(PE_NAME_CUST_01));
            // ZYPEZDItemValueSetter.setValue(inDtlMsg.vndChildBomPrcAmt,
            // rs.getBigDecimal(P_PRC_UNIT));

        }

        inDtlMsg.poOrdNum.clear();
        ZYPEZDItemValueSetter.setValue(inDtlMsg.poAckLineStsCd, poAckBean.getPoAckLineStsCd());
        ZYPEZDItemValueSetter.setValue(inDtlMsg.vndPoAckLineStsTxt, poAckBean.getPoAckLineStsTxt());
        inDtlMsg.poAckCmntTxt.clear();
        ZYPEZDItemValueSetter.setValue(inDtlMsg.poAckHdrWrkPk, poAckBean.getPoAckHdrWrkPk());
        inDtlMsg.shipToCustCd.clear();
        inDtlMsg.ediCnsgnCd.clear();
        inDtlMsg.vndRtrnStsCd.clear();
        inDtlMsg.vndShpgStsCd.clear();
        inDtlMsg.vndDoStsTpCd.clear();
        inDtlMsg.vndFirstProdCtrlCd.clear();
        inDtlMsg.vndGoodsTpCd.clear();
        inDtlMsg.vndGoodsTpNm.clear();
        inDtlMsg.vndShpgMethCd.clear();
        inDtlMsg.vndShpgMethNm.clear();
        inDtlMsg.asnSoNum.clear();
        inDtlMsg.asnSoIssDt.clear();
        inDtlMsg.vndShipToEtaDt.clear();
        inDtlMsg.poAckDtlPk.clear();
        ZYPEZDItemValueSetter.setValue(inDtlMsg.vndPoAckStsCd, poAckBean.getVndPoAckStsCd());

        if (insertCheck(rs, poAckBean, inDtlMsg, isForceInsert)) {
            poAckDtlWrkList.add(inDtlMsg);

            // QC#29245 mod start init
            String dsBizLastUpdTimeStamp = inDtlMsg.ordDtlLastUpdTs.getValue();

            if (!ZYPCommonFunc.hasValue(dsBizLastUpdTs)) {
                dsBizLastUpdTs = dsBizLastUpdTimeStamp;
            }

            // if
            // (dsBizLastUpdTs.compareTo(inDtlMsg.ordDtlLastUpdTs.getValue())
            // > 0) {

            if (dsBizLastUpdTimeStamp.compareTo(dsBizLastUpdTs) > 0) {

                // dsBizLastUpdTs =
                // inDtlMsg.ordDtlLastUpdTs.getValue();
                dsBizLastUpdTs = dsBizLastUpdTimeStamp;

                // QC#29245 mod end

            }
        }
        return poAckBean;
    }

    /**
     * @param rs
     * @param inMap
     * @return
     * @throws SQLException
     */
    private boolean insertCheck(ResultSet rs, poAckBean poAckBean, PO_ACK_DTL_WRKTMsg inDtlMsg, boolean isForceInsert) throws SQLException {

        boolean insertFlg = false;

        // START 2022/11/08 A.Cullano [QC#60776, ADD]
        // Skip insert if POYO date is available in PO_ACK_DTL_WRK for CUSA Parts processing
        if (PO_ACK_CREATE_PTN_PARTS.equals(poAckBean.getAckDtlPtn())) {
            if (isAvailPoAckDtlWrk(rs)) {
                return insertFlg;
            }
        }
        // END 2022/11/08 A.Cullano [QC#60776, ADD]

        // get latest ack hdr info
        // ******************************************************************************
        Map<String, Object> ssmPoAckParam = new HashMap<String, Object>();

        ssmPoAckParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmPoAckParam.put(BIND_EDI_PO_ORD_NUM, poAckBean.getEdiNum());
        ssmPoAckParam.put(BIND_EDI_PO_ORD_DTL_LINE_NUM, inDtlMsg.ediPoOrdDtlLineNum.getValue());
        ssmPoAckParam.put("shpgPlnDplyLineNum", inDtlMsg.shpgPlnDplyLineNum.getValue());
        List<Map<String, Object>> latestPoAckList = ssmBatchClient.queryObjectList("getLatestPoAckList", ssmPoAckParam);

        if (latestPoAckList == null || latestPoAckList.isEmpty()) {

            return true;

        }

        for (int i = 0; i < latestPoAckList.size(); i++) {

            Map<String, Object> latestPoAckDtl = latestPoAckList.get(i);

            if (!equalsNullSafe(inDtlMsg.poAckLineStsCd.getValue(), (String) latestPoAckDtl.get(PO_ACK_LINE_STS_CD))) {

                insertFlg = true;

            } else if (!equalsNullSafe(inDtlMsg.vndPoAckStsCd.getValue(), (String) latestPoAckDtl.get(VND_PO_ACK_STS_CD))) {

                insertFlg = true;

            } else if (!equalsNullSafe(inDtlMsg.vndCpoOrdNum.getValue(), (String) latestPoAckDtl.get(VND_CPO_ORD_NUM))) {

                insertFlg = true;

            } else if (!equalsNullSafe(inDtlMsg.vndCpoDtlLineNum.getValue(), (String) latestPoAckDtl.get(VND_CPO_DTL_LINE_NUM))) {
                insertFlg = true;

            } else if (!equalsNullSafe(inDtlMsg.vndCpoDtlLineSubNum.getValue(), (String) latestPoAckDtl.get(VND_CPO_DTL_LINE_SUB_NUM))) {

                insertFlg = true;

            } else if (!equalsNullSafe(inDtlMsg.shpgPlnNum.getValue(), (String) latestPoAckDtl.get(SHPG_PLN_NUM))) {

                insertFlg = true;
            } else if (isForceInsert && !equalsNullSafe(inDtlMsg.etaDt.getValue(), (String) latestPoAckDtl.get(ETA_DT))) {

                insertFlg = true;
            }
        }

        return insertFlg;

    }

    /**
     * equalsNullSafe
     * Treat empty string is equal to null.
     * @param val1
     * @param val2
     * @return
     */
    private boolean equalsNullSafe(String val1, String val2) {
        if (!ZYPCommonFunc.hasValue(val1)) {
            if (!ZYPCommonFunc.hasValue(val2)) {
                return true;
            }
            return false;
        }
        return val1.equals(val2);
    }

    /**
     * mappingWithOrdTakeMdse
     * @param cusaMdseCd
     * @param csaMdseCd
     * @return boolean
     */
    private boolean mappingWithOrdTakeMdse(String cusaMdseCd, String csaMdseCd) {

        boolean mapFlg = false;

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        // QC#17077 Start
        if (csaMdseCd.length() > 8) {
            csaMdseCd = csaMdseCd.substring(0, 8);
        }
        if (cusaMdseCd.length() > 8) {
            cusaMdseCd = cusaMdseCd.substring(0, 8);
        }
        // QC#17077 End

        ssmParam.put(BIND_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        // QC#17077 Start
        ssmParam.put(BIND_ORD_TAKE_MDSE_CD, csaMdseCd);
        //ssmParam.put(BIND_ORD_TAKE_MDSE_CD, csaMdseCd.substring(0, 8));
        // QC#17077 End

        String ordTakeMdseCd = (String) ssmBatchClient.queryObject("searcOrdTakeMdse", ssmParam);

        // QC#17077 Start
        if (ZYPCommonFunc.hasValue(ordTakeMdseCd) && cusaMdseCd.equals(csaMdseCd)) {
        //if (ZYPCommonFunc.hasValue(ordTakeMdseCd) && cusaMdseCd.substring(0, 8).equals(csaMdseCd.substring(0, 8))) {
        // QC#17077 End

            mapFlg = true;

        }

        return mapFlg;

    }

    /**
     * searchAslMdseCd
     * @param poOrdNum
     * @param ediNum
     * @return aslMdseCdList
     */
    private String searchOrigAslMdseCd(poAckBean poAckBean) throws SQLException {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        String ediNum = poAckBean.getEdiLineNum();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(BIND_PO_ORD_NUM, poAckBean.getEdiNum());

        if (ZYPCommonFunc.hasValue(ediNum)) {

            if (ediNum.length() > 3) {

                ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum.substring(ediNum.length() - 3));

            } else {

                ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum);

            }

        } else {

            ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum);

        }

        String origMdseCD = (String) ssmBatchClient.queryObject("searchOrigAslMdseCd", ssmParam);

        // START 2018/10/19 M.Naito [QC#28830, ADD]
        if (!ZYPCommonFunc.hasValue(origMdseCD)) {
            origMdseCD = (String) ssmBatchClient.queryObject("searchOrigAslMdseCdFromXref", ssmParam);
        }
        // END 2018/10/19 M.Naito [QC#28830, ADD]

        return origMdseCD;
    }

    /**
     * @param rs
     * @return
     * @throws SQLException
     */
    private String searchMdseCmpsnTpCd(poAckBean poAckBean) throws SQLException {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        String ediNum = poAckBean.getEdiLineNum();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(BIND_PO_ORD_NUM, poAckBean.getEdiNum());

        if (ZYPCommonFunc.hasValue(ediNum)) {

            if (ediNum.length() > 3) {

                ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum.substring(ediNum.length() - 3));

            } else {

                ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum);

            }

        } else {

            ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum);

        }

        String mdseCmpsnTpCd = (String) ssmBatchClient.queryObject("searchMdseCmpsnTpCd", ssmParam);

        return mdseCmpsnTpCd;
    }

    /**
     * searchAslMdseCd
     * @param poOrdNum
     * @param ediNum
     * @return aslMdseCdList
     */
    private List<Map<String, Object>> searchAslMdseCd(String poOrdNum, String poOrdDtlLineNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);
        ssmParam.put(BIND_SET_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);

        List<Map<String, Object>> aslMdseCdList = ssmBatchClient.queryObjectList("searchAslMdseCd", ssmParam);

        return aslMdseCdList;
    }

    /**
     * searchDsPoDtl
     * @param poOrdNum
     * @param ediNum
     * @return poOrdDtlLineNum
     */
    private String searchPoOrdDtlLineNum(String poOrdNum, String ediNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);

        if (ZYPCommonFunc.hasValue(ediNum)) {

            if (ediNum.length() > 3) {

                ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum.substring(ediNum.length() - 3));

            } else {

                ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum);

            }

        // START 2019/02/25 M.Naito [QC#30452, MOD]
//        } else {
//
//            ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, ediNum);

            String poOrdDtlLineNum = (String) ssmBatchClient.queryObject("searchPoOrdDtlLineNum", ssmParam);
            if (ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {
                ediNum = poOrdDtlLineNum;
            }
        }

//        String poOrdDtlLineNum = (String) ssmBatchClient.queryObject("searchPoOrdDtlLineNum", ssmParam);
//
//        return poOrdDtlLineNum;
        return ediNum;
        // END 2019/02/25 M.Naito [QC#30452, MOD]
    }

    /**
     * @param ordNum
     * @param lineNum
     * @return
     */
    private String searchCsaCpoDtl(String ordNum, String lineNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        ssmParam.put(BIND_CPO_ORD_NUM, ordNum);
        ssmParam.put(BIND_CPO_DTL_LINE_NUM, lineNum);
        ssmParam.put(BIND_CPO_DTL_LINE_SUB_NUM, CPO_DTL_LINE_SUB_NUM_000);

        String ediNum = (String) ssmBatchClient.queryObject("searchCsaCpoDtl", ssmParam);

        return ediNum;
    }

    /**
     * getAckStsNm
     * @param str
     * @return getAckStsNm
     */
    private String getAckStsNm(String str) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(BIND_PO_ACK_LINE_STS_CD, str);

        String getAckStsNm = (String) ssmBatchClient.queryObject("getAckStsNm", ssmParam);

        return getAckStsNm;
    }

    // START 2019/02/25 M.Naito [QC#30452, ADD]
    private String getPoOrdNumFromXref(String poOrdNum) {

        if (!ZYPCommonFunc.hasValue(poOrdNum)) {
            return poOrdNum;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);
        String poOrdNumFromXref = (String) ssmBatchClient.queryObject("getPoOrdNumFromXref", ssmParam);

        if (!ZYPCommonFunc.hasValue(poOrdNumFromXref)) {
            if (poOrdNum.length() > 8) {
                poOrdNumFromXref = poOrdNum.substring(poOrdNum.length() - 8);
            }
        }
        if (ZYPCommonFunc.hasValue(poOrdNumFromXref)) {
            poOrdNum = poOrdNumFromXref;
        }

        return poOrdNum;
    }
    // END 2019/02/25 M.Naito [QC#30452, ADD]

    /**
     * @param internalVarConstMap
     * @return
     */
    private HashMap<String, String> getVarConstCd(HashMap<String, String> internalVarConstMap) {

        String errorItem = null;

        internalVarConstMap.put(VAL_CUSA_CPO_HDLG_API_INTFC_ID, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_CPO_HDLG_API_INTFC_ID, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_EDI_TRX_PRPS_ORIG, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_EDI_TRX_PRPS_ORIG, this.glblCmpyCd));

        internalVarConstMap.put(VAL_CUSA_CPO_SRC_TP_GRP_CMPY, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_CPO_SRC_TP_GRP_CMPY, this.glblCmpyCd));

        /* QC#24404 */
        internalVarConstMap.put(VAL_CUSA_CPO_SRC_TP_EDI, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_CPO_SRC_TP_EDI, this.glblCmpyCd));

        internalVarConstMap.put(VAL_CUSA_EDI_PROC_STS_CANC, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_EDI_PROC_STS_CANC, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_EDI_PROC_STS_ERR, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_EDI_PROC_STS_ERR, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_EDI_PROC_STS_ITRL_ERR, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_EDI_PROC_STS_ITRL_ERR, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_EDI_PROC_STS_PROC, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_EDI_PROC_STS_PROC, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_PRT_ORD_TP_PRT_RTRN, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_ORD_TP_PRT_RTRN, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_PRT_ORD_TP_RPR_PRT_RTRN, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_ORD_TP_RPR_PRT_RTRN, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_PRT_ORD_TP_RR_FROM_DLR, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_ORD_TP_RR_FROM_DLR, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_PRT_OE_STS_NORM, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_OE_STS_NORM, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_PRT_OE_STS_AUTH_HOLD, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_OE_STS_AUTH_HOLD, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_PRT_OE_STS_RUSH_ORD_HOLD, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_OE_STS_RUSH_ORD_HOLD, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_PRT_OE_STS_ODC_HOLD, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_OE_STS_ODC_HOLD, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_PRT_OE_STS_BACK_ORD, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_OE_STS_BACK_ORD, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_PRT_OE_STS_CR_HOLD, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_OE_STS_CR_HOLD, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_PRT_OE_STS_VOID, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_OE_STS_VOID, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_PRT_SO_STS_PRNT, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_SO_STS_PRNT, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_PRT_SO_STS_SHIP, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_SO_STS_SHIP, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_PRT_SO_STS_INV, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_SO_STS_INV, this.glblCmpyCd));
        internalVarConstMap.put(VAL_CUSA_PRT_ACK_CANC_WRK_FILE_ID, ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_PRT_ACK_CANC_WRK_FILE_ID, this.glblCmpyCd));
        internalVarConstMap.put(VAL_DROP_SHIP_WH_CD, ZYPCodeDataUtil.getVarCharConstValue(VAR_DROP_SHIP_WH_CD, this.glblCmpyCd));

        for (String itemName : INTERNAL_VAR_CHAR_CONST) {

            if (!ZYPCommonFunc.hasValue((internalVarConstMap.get(itemName)))) {
                errorItem = itemName;
                throw new S21AbendException(S21MessageFunc.clspGetMessage(NMAM0039E, new String[] {errorItem }));
            }

        }

        return internalVarConstMap;

    }

    /** QC#20737 Add.
     * checkLakAssingn
     * @param cpoOrdNum
     * @param cpoDtlLineNum
     * @param cpoDtlLineSubNum
     * @param mdseCd
     * @return
     */
    private boolean checkLakAssingn(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum, String mdseCd) {

    	boolean lakAssigned = false;

    	Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cusaGlblCmpyCd", this.cusaGlblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("cpoDtlLineNum", cpoDtlLineNum);
        ssmParam.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("lakStsCd", LAK_STS.USED);

        List<String> cpoOrdTpCdList = new ArrayList<String>();

        if (ZYPCommonFunc.hasValue(this.cpoOrdTpCd)) {

            String[] cpoOrdTpCdTmp = this.cpoOrdTpCd.split(",");

            for (int j = 0; j < cpoOrdTpCdTmp.length; j++) {

                cpoOrdTpCdList.add(cpoOrdTpCdTmp[j]);

            }

        }

        ssmParam.put("cpoOrdTpCdList", cpoOrdTpCdList);

        String lakAssigne = (String) ssmBatchClient.queryObject("checkLakAssingn", ssmParam);

        if (ZYPCommonFunc.hasValue(lakAssigne) && Integer.parseInt(lakAssigne) > 0) {

            lakAssigned = true;
        }

        return lakAssigned;
    }

    // 2017/11/27 QC#22425 T.Hakodate UPDATE START
    /**
     * checkPartsItemFlip
     * @param poNum
     * @param itemCd
     * @param lineNum
     */
    private boolean checkPartsItemFlip(String poNum, String itemCd, String lineNum) {

        boolean itemFlipped = false;

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("poOrdNum", poNum);
        ssmParam.put("poOrdDtlLineNum", lineNum);

        String aslMdseCd = (String) ssmBatchClient.queryObject("checkPartsItemFlip", ssmParam);

        if (aslMdseCd != null && !aslMdseCd.equals(itemCd)) {

            itemFlipped = true;
        }

        return itemFlipped;

    }

    // 2017/11/27 QC#22425 T.Hakodate UPDATE END
    /**
     * Finalize method.
     */
    @Override
    protected void termRoutine() {

        setTermState(termCd, normalRecCnt, errRecCnt);

    }

    /**
     * Called for batch shell.
     * @param args execution parameter
     */
    public static void main(String[] args) {

        new NPAB124001().executeBatch(NPAB124001.class.getSimpleName());

    }

    /**
     * @param rs
     * @return
     * @throws SQLException
     */
    private boolean checkItemChangeData(String ediPoOrdNum, String ediPoOrdDtlLineNum) throws SQLException {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(BIND_EDI_PO_ORD_NUM, ediPoOrdNum);
        ssmParam.put(BIND_EDI_PO_ORD_DTL_LINE_NUM, ediPoOrdDtlLineNum);
        ssmParam.put("ItemChange", VND_PO_ACK_STS.ITEM_CHANGE);

        BigDecimal cnt = (BigDecimal) ssmBatchClient.queryObject("checkItemChangeData", ssmParam);

        if (ZYPCommonFunc.hasValue(cnt) && BigDecimal.ZERO.compareTo(cnt) == 0) {
            return true;
        } else {
            return false;
        }
    }

    
    /**
     * getGroupCompany
     * @param trdPtnrCdList
     * @return
     */
    private Map<String, List> getGroupCompany(ArrayList<String> trdPtnrCdList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("cusaGlblCmpyCd", this.cusaGlblCmpyCd);
        ssmParam.put("ediTrdPtnrCdList", trdPtnrCdList);
        ssmParam.put("ediCustTpCd", "1");

        S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<String> billToCustCdList = new ArrayList<String>();
        List<String> sellToCustCdList = new ArrayList<String>();
        Map<String, List> groupCompanyList = new HashMap<String, List>();
        groupCompanyList.put("BILL_TO_CUST_CD", billToCustCdList);
        groupCompanyList.put("SELL_TO_CUST_CD", sellToCustCdList);

        try {
            preparedStatement = ssmLlcClient.createPreparedStatement("getBillToSellTo", ssmParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String billToCustomer = resultSet.getString("BILL_TO_CUST_CD");
                String sellToCustomer = resultSet.getString("SELL_TO_CUST_CD");

                if (ZYPCommonFunc.hasValue(billToCustomer) && !billToCustCdList.contains(billToCustomer)) {
                    billToCustCdList.add(billToCustomer);
                }

                if (ZYPCommonFunc.hasValue(sellToCustomer) && !sellToCustCdList.contains(sellToCustomer)) {
                    sellToCustCdList.add(sellToCustomer);
                }
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);

        }

        return groupCompanyList;
    }
    
    
    // QC#26089 ADD START
    /**
     * getSellToCustCd
     */
    private List<String> getSellToCustCd() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("trdPtnrCdList", this.trdPtnrCdList);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSellToCustCd", ssmParam);

        ArrayList<String> soldToList = new ArrayList<String>();

        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> aslMdseMap = resultList.get(i);
            soldToList.add((String) aslMdseMap.get(P_CODE_CUST_SOLD_TO));
        }
        return soldToList;
    }
    // QC#26089 ADD END

    // START 2022/11/08 A.Cullano [QC#60776, ADD]
    /**
     * isAvailPoAckDtlWrk
     * @param rs
     * @return Boolean
     * @throws SQLException
     */
    private boolean isAvailPoAckDtlWrk(ResultSet rs) throws SQLException {
        boolean isAvailable = false;
        String poyoDt = rs.getString(POYO_EZUPTIME);

        if (!ZYPCommonFunc.hasValue(poyoDt)) {
            return false;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(BIND_PO_ORD_NUM, rs.getString(P_NUM_CUST_PO));
        ssmParam.put(BIND_MDSE_CD, rs.getString(P_CUST_PART_NUM));
        ssmParam.put(BIND_POYO_DT, poyoDt);

        BigDecimal instance = (BigDecimal) ssmBatchClient.queryObject("isAvailPoAckDtlWrk", ssmParam);

        if ((BigDecimal.ZERO.compareTo(instance) != 0)) {
            isAvailable = true;
        }

        return isAvailable;
    }
    // END 2022/11/08 A.Cullano [QC#60776, ADD]
    
    
    
}
