/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC134001;

import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.ASL_MDSE_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.AWZC901001;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_CARR_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_CARR_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_CPO_ORD_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_CRS_REF_ACTV_FLG;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_CUST_ISS_PO_DT;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_CUST_ISS_PO_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_DS_ORD_CATG_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_DS_ORD_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_DS_PO_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_EDI_CUST_TP_CD_1;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_EDI_CUST_TP_CD_2;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_EDI_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_EDI_TRD_PTNR_LIST;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_EFF_FROM_DT;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_EFF_THRU_DT;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_FRT_COND_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_INVTY_LOC_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_MDSE_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_PO_ACK_DTL_PK_LIST;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_PO_ACK_HDR_PK;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_PO_MDSE_CMPSN_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_PO_MSG_SEG_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_PO_MSG_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_PO_MSG_TP_CD_SI;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_PO_MSG_TP_CD_SN;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_PO_ORD_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_PO_ORD_SRC_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_PO_STS_CD_EX;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_PO_STS_CD_LIST;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_PRCH_GRP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_PRNT_MDSE_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_RTL_WH_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_SHPG_SVC_LCL_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_SHPG_SVC_LVL_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_SLS_DT;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_TRD_PTNR_LOC_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_VND_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_VND_PO_ACK_STS_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BIND_VND_XREF_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.BLANK;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.CARR_ACCT_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.CARR_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.CARR_SVC_LVL_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.CARR_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.CHILD_MDSE_QTY;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.CPO_ORD_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.CPO_ORD_TP_CD_10;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.CPO_SRC_TP_CD_95;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.CTAC_PSN_NM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.CUST_ISS_PO_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.CUST_MDSE_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.CUST_UOM_CD_EA;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.DB_EXECUTE_SUCCESS;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.DEST_RTL_WH_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.DROP_SHIP_WH_CD_DS;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.DS_ORD_CATG_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.DS_ORD_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.DS_PO_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.EDI_CUST_TP_CD_B;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.EDI_CUST_TP_CD_S;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.EDI_RCV_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.EDI_SEND_FLG;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.EDI_SEND_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.EDI_SUB_NUM_001;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.EFF_FROM_DATE;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.EFF_THRU_DATE;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.ETA_DT;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.EVENT_ID_CUSA_CPO_CREATE_ERR;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.EVENT_ID_SEND_PO;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.FRT_COND_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.IF_MODE_CD_1;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.IF_MODE_CD_2;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.IF_MODE_CD_3;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.INVTY_LOC_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.IS_ORD_TAKE;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.ITRL_ID_CSA_CUSA_PRT;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.ITRL_ID_CSA_CUSA_WS;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.ITRL_INTFC_ID_NPAA0030;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.ITRL_INTFC_ID_NPAA0040;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.ITRL_INTFC_ID_NPAI2100;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.MDSE_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.MDSE_NM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.MD_VND_LOC_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.MODE_ADD_LINE;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.MODE_CANCEL_ADD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.MODE_CANCEL_LINE;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.MODE_CUSA_AZ;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.MODE_CUSA_PA;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.MODE_CUSA_WS;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.MSG_TYPE_ERROR;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NEAM0121E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NLBM0001E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NLBM1115E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NMZM0082E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAA0010;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAA0020;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAI2090;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM0007E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1384E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1397E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1398E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1399E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1400E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1401E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1402E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1403I;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1404E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1405E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1406E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1407E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1408E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1409E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1410E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1411E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1412W;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1413E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1414E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1417E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1418E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1419E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1420E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1421E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1442E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPAM1482E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPZC1330_SEGMENT_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPZC1330_SEQ_NUMBER;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPZC1330_UNIT_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPZM0179E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPZM0180E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPZM0275E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPZM0279E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPZM0280E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPZM0287E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPZM0296E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NPZM0312E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NSZM0175E;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.NWZA0010;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_ACK_DTL_PK;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_ACK_DTL_SQ;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_ACK_HDR_PK;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_ACK_HDR_SQ;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_ACK_LINE_STS_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_APVL_DT;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_MDSE_CMPSN_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_MDSE_CMPSN_TP_CHILD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_MDSE_CMPSN_TP_PARENT;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_MSG_SEG_ID_1;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_MSG_TP_CD_03;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_ORD_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_ORD_SRC_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_QTY;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_SEND_FLG;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_STS_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PO_STS_CD_CONFIRMED;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PRCH_GRP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SET_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SHIP_TO_ADDL_LOC_NM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SHIP_TO_CNTY_NM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SHIP_TO_CTRY_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SHIP_TO_CTY_ADDR;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SHIP_TO_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SHIP_TO_FRTH_LINE_ADDR;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SHIP_TO_LOC_NM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SHIP_TO_POST_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SHIP_TO_SCD_LINE_ADDR;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SHIP_TO_ST_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SHIP_TO_THIRD_LINE_ADDR;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SHPG_SVC_LVL_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.STAR;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SYSTEM_ERROR;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.SYS_SRC_CD_AW;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.TIME_FORMAT;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.TRD_PTNR_CARR_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.TRD_PTNR_FRT_CHRG_METH_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.TRD_PTNR_SHIP_VIA_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.TRD_PTNR_SHPG_COND_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.TRD_PTNR_SHPG_SVC_LVL_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.TXT_LENGTH_20;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.TXT_LENGTH_35;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.TXT_LENGTH_400;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.TXT_LENGTH_60;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.TXT_LENGTH_8;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.TXT_TP_CD_20;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.VAR_CUSA_EDI_TRD_PTNR_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.VAR_CUSA_GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.VAR_DROP_SHIP_WH_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.VND_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.VND_DROP_SHIP_FLG;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.VND_INVTY_LOC_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.VND_ISS_ORD_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.VND_PO_ACK_STS_CD_CW;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.VND_PO_ACK_STS_CD_IW;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.VND_PO_ACK_STS_DESC_TXT;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.VND_SYS_TP_A;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.VND_SYS_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.VND_SYS_TP_P;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.VND_SYS_TP_W;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.VND_XREF_TP_CD_1;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.XX_PROC_TP_CD_1;
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.XX_PROC_TP_CD_2;
// START 2023/02/17 TZ.Win [QC#60966, ADD]
import static com.canon.cusa.s21.api.NPZ.NPZC134001Constant.NPZC134001Constant.RQST_SHIP_DT;
// END 2023/02/17 TZ.Win [QC#60966, ADD]

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.POTMsg;
import business.db.PO_ACK_DTLTMsg;
import business.db.PO_ACK_HDRTMsg;
import business.db.PO_DTLTMsg;
import business.db.PO_MSGTMsg;
import business.parts.AWZC901001PMsg;
import business.parts.AWZC901001_xxOrdMsgListPMsgArray;
import business.parts.NPZC004001PMsg;
import business.parts.NPZC132001PMsg;
import business.parts.NPZC133001PMsg;
import business.parts.NPZC134001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.api.NPZ.NPZC132001.NPZC132001;
import com.canon.cusa.s21.api.NPZ.NPZC133001.NPZC133001;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreatePOHistory;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001PoMsg;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCharacterConversionUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ORD_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21IntBizApiProxy;
import com.canon.cusa.s21.framework.integration.S21IntTransactionTableAccessor;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.internalbizapiweb.constant.S21BizApiHttpClientConst;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Handling Direct CPO Creation API.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/15/2015   CITS            T.Hakodate      Create          N/A
 * 03/24/2016   CSAI            K.Lee           Update          QC#5956
 * 10/06/2016   CITS            K.Ogino         Update          QC#14996
 * 11/01/2016   CITS            T.Hakodate      Update          QC#15713
 * 12/22/2016   CITS            K.Ogino         UPDATE          QC#14749
 * 01/12/2017   CITS            K.Kameoka       Update          QC#17077
 * 01/19/2017   CITS            R.Shimamoto     Update          QC#16994
 * 06/13/2017   CITS            S.Endo          Update          QC#19069
 * 08/07/2017   CITS            T.Tokutomi      Update          QC#20346
 * 08/09/2017   CITS            S.Endo          Update          Sol#035(QC#18108)
 * 08/10/2017   CITS            T.Wada          Update          QC#20442
 * 09/15/2017   CITS            R.Shimamoto     Update          QC#20735
 * 10/16/2017   CITS            T.Kikuhara      Update          QC#20246(Sol454#)
 * 10/23/2017   CITS            T.Hakodate      Update          QC#20246(Sol454#)
 * 10/24/2017   CITS            T.Hakodate      Update          QC#20246(Sol454#)
 * 10/27/2017   CITS            K.Ogino         UPDATE          QC#22113
 * 12/11/2017   CITS            T.Hakodate      UPDATE          QC#21025
 * 12/18/2017   CITS            T.Hakodate      UPDATE          QC#23087
 * 02/20/2018   CITS            K.Ogino         UPDATE          QC#20572
 * 03/16/2018   CITS            K.Ogino         UPDATE          QC#24788
 * 05/22/2018   CITS            T.Hakodate      UPDATE          QC#26006
 * 05/28/2018   CITS            K.Kameoka       UPDATE          QC#26074
 * 06/06/2018   CITS            K.Kameoka       UPDATE          QC#23356
 * 08/03/2018   CITS            K.Ogino         UPDATE          QC#27606
 * 08/22/2018   CITS            T.hakodate      Update          QC#27628
 * 09/26/2018   CITS            T.hakodate      Update          QC#27628
 * 10/18/2018   CITS            T.Hakodate      Update          QC#28731
 * 02/13/2019   Fujitsu         S.Takami        Update          QC#30232
 * 07/17/2019   CITS            R.Shimamoto     Update          QC#51557
 * 10/17/2019   Fujitsu         T.Ogura         Update          QC#53813
 * 12/03/2019   CITS            M.Naito         Update          QC#54563-1
 * 2021/04/30   CITS            K.Ogino         Update          QC#58769
 * 2023/02/17   Hitachi         TZ.Win          Update          QC#60966
 *</pre>
 */

public class NPZC134001 extends S21ApiCommonBase {

    /** SQL Access */
    private S21SsmBatchClient ssmBatchClient = null;

    /** processMode. */
    private String processMode = null;

    /** cusaGlblCmpyCd. */
    private String cusaGlblCmpyCd = null;

    /** Transaction Accesor */
    private S21IntTransactionTableAccessor intTrxaccessor = null;

    /** trxAccessor. */
    private S21TransactionTableAccessor trxAccessor = null;

    /** cusaCpoCreated. */
    private boolean cusaCpoCreated = false;

    /** cusaValidFlg. */
    private boolean cusaValidFlg = true;

    /** csaValidFlg. */
    private boolean csaValidFlg = true;

    /** Transaction ID */
    private BigDecimal transactionId = null;

    /** cusaDuplicateFlg. */
    private boolean cusaDuplicateFlg = false;
    
    //QC#27628 add start
    /** carrCdfromXref. */
    private static String carrCdfromXref = "";
    private static boolean carrAcctNumIsNull = false;
    private static boolean carrAcctNumFromXref = false;
    //QC#27628 add end

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = new ArrayList<Map<String, String>>();

    /**
     * Initialize
     */
    public NPZC134001() {

        super();

        // SSM Init ******************************************
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    }

    /**
     * execute
     * @param param NPZC134001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC134001PMsg param, final ONBATCH_TYPE onBatchType) {

        EZDDebugOutput.println(1, PROGRAM_ID + "[ execute ] start", this);

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        NPZC134001PMsg inputParam = (NPZC134001PMsg) msgMap.getPmsg();

        // Default flag set
        // *****************************************
        if (BLANK.equals(inputParam.xxdrctCpoCratFlg)) {
            inputParam.xxdrctCpoCratFlg.setValue(ZYPConstant.FLG_ON_Y);
        }

        if (BLANK.equals(inputParam.xxsendPoIfCratFlg)) {
            inputParam.xxsendPoIfCratFlg.setValue(ZYPConstant.FLG_ON_Y);
        }

        // I/F input CHECK
        // ******************************************
        if (!checkParam(msgMap, onBatchType)) {
            msgMap.flush();
            return;
        }

        // getVarCharConstValue cusaGlblCmpyCd
        // ******************************************
        this.cusaGlblCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_GLBL_CMPY_CD, inputParam.glblCmpyCd.getValue());

        if (!ZYPCommonFunc.hasValue(this.cusaGlblCmpyCd)) {
            msgMap.addXxMsgId(NPAM1482E);
            msgMap.flush();
            return;
        }

        /***************************************
         * PO Info
         ***************************************/
        List<Map<String, Object>> sendPoInfoList = sendPoInfo(msgMap, onBatchType);

        // No send po Info
        if (sendPoInfoList == null || sendPoInfoList.isEmpty()) {

            S21InfoLogOutput.printlnv(NPAM1403I);

            msgMap.addXxMsgId(NPAM1403I);

            msgMap.flush();

            return;
        }

        Map<String, Object> poInfoMap = sendPoInfoList.get(0);
        
        // QC#27628 add start
        carrAcctNumFromXref = false;
        // QC#27628 add end

        // Mode Check
        // ******************************************
        // QC#14996
        if (XX_PROC_TP_CD_1.equals(param.xxProcTpCd.getValue())) {

            if (VND_SYS_TP_W.equals(poInfoMap.get(VND_SYS_TP_CD)) && ZYPConstant.FLG_ON_Y.equals(poInfoMap.get(PO_SEND_FLG)) && ZYPConstant.FLG_ON_Y.equals(poInfoMap.get(EDI_SEND_FLG))) {

                processMode = MODE_CUSA_WS;

            } else if (VND_SYS_TP_P.equals(poInfoMap.get(VND_SYS_TP_CD)) && ZYPConstant.FLG_ON_Y.equals(poInfoMap.get(PO_SEND_FLG)) && ZYPConstant.FLG_ON_Y.equals(poInfoMap.get(EDI_SEND_FLG))) {

                processMode = MODE_CUSA_PA;

            } else if (VND_SYS_TP_A.equals(poInfoMap.get(VND_SYS_TP_CD)) && ZYPConstant.FLG_ON_Y.equals(poInfoMap.get(PO_SEND_FLG)) && ZYPConstant.FLG_ON_Y.equals(poInfoMap.get(EDI_SEND_FLG))) {

                processMode = MODE_CUSA_AZ;

            } else {

                msgMap.flush();
                return;

            }

        } else if (XX_PROC_TP_CD_2.equals(param.xxProcTpCd.getValue()) && VND_SYS_TP_W.equals(poInfoMap.get(VND_SYS_TP_CD)) && ZYPConstant.FLG_ON_Y.equals(poInfoMap.get(PO_SEND_FLG))
                && ZYPConstant.FLG_ON_Y.equals(poInfoMap.get(EDI_SEND_FLG))) {

            if (ZYPConstant.FLG_ON_Y.equals(param.xxdrctCpoCratFlg.getValue())) {

                List<Map<String, Object>> poStsCdList = getPoStsCd(msgMap, onBatchType);

                String[] poStsCdTemp = new String[poStsCdList.size()];

                for (int i = 0; i < poStsCdList.size(); i++) {

                    poStsCdTemp[i] = (String) poStsCdList.get(i).get(PO_STS_CD);

                }

                if (Arrays.asList(poStsCdTemp).contains(PO_STS.VALIDATED) && Arrays.asList(poStsCdTemp).contains(PO_STS.CANCELLED)) {

                    processMode = MODE_CANCEL_ADD;

                } else if (Arrays.asList(poStsCdTemp).contains(PO_STS.VALIDATED)) {

                    processMode = MODE_ADD_LINE;

                } else if (Arrays.asList(poStsCdTemp).contains(PO_STS.CANCELLED)) {

                    processMode = MODE_CANCEL_LINE;

                } else {

                    msgMap.flush();

                    return;
                }

            } else {

                msgMap.flush();

                return;

            }

        } else {

            msgMap.flush();

            return;
        }

        // xx_DRCT_CPO_CRAT_FLG = Y
        // ******************************************

        if (MODE_CUSA_WS.equals(processMode)) {

            List<Map<String, Object>> tmpSendPoInfoList = new ArrayList<Map<String, Object>>();
            // QC#22113 Add
            for (int i = 0; i < sendPoInfoList.size(); i++) {
                if (PO_STS.CANCELLED.equals((String) sendPoInfoList.get(i).get("PO_STS_CD"))) {
                    continue;
                }
                tmpSendPoInfoList.add(sendPoInfoList.get(i));

            }
            // QC#22113 End

            if (ZYPConstant.FLG_ON_Y.equals(param.xxdrctCpoCratFlg.getValue())) {

                // Set API Parameter
                AWZC901001PMsg apiParam = new AWZC901001PMsg();

                // ******************************************
                // Call AWZC9010 CPO Creation for NA API
                // ******************************************
                apiParam = callCpoCreationApi(msgMap, tmpSendPoInfoList, apiParam);

                // cpo create error.
                // csaValidation NG.
                // cusaValidation NG.
                // cusa cpo does not created.
                if (apiParam.xxMsgIdList.getValidCount() > 0 || !csaValidFlg || (!cusaValidFlg && !cusaCpoCreated)) {

                    if (!csaValidFlg) {
                        msgMap.flush();
                        return;
                    }

                    // CPO Creation API Error
                    if (S21ApiUtil.isXxMsgId(apiParam)) {
                        List<String> msgIds = S21ApiUtil.getXxMsgIdList(apiParam);
                        for (String msgId : msgIds) {
                            S21InfoLogOutput.println("CUSA CPO Creation API Error:" + S21MessageFunc.clspGetMessage(msgId));

                        }
                    }

                    if (!cusaValidFlg) {

                        // An error occurred in creating CUSA order
                        // directly, but order has been sent to
                        // interface
                        // for batch
                        if (!cusaDuplicateFlg) {
                            msgMap = new S21ApiMessageMap(param);
                            msgMap.addXxMsgId(NPAM1412W);
                        }

                        // update po_dtl
                        updatePoDtlWithError(msgMap, tmpSendPoInfoList, apiParam);

                        // Business Error occurred
                        // NPXC001001 CreatePOHistory
                        // ******************************************
                        if (!callCreatePoHistoryApi(msgMap, tmpSendPoInfoList, EVENT_ID_CUSA_CPO_CREATE_ERR)) {
                            msgMap.flush();
                            return;
                        }

                        // QC#24788
                        if (!cusaDuplicateFlg) {
                            // Call Send PO API for Interface NPZC1320
                            this.intTrxaccessor = new S21IntTransactionTableAccessor(ITRL_ID_CSA_CUSA_WS);

                            transactionId = intTrxaccessor.getNextTransactionId();

                            if (!callSendPoIfApIf(msgMap, NPAA0010, tmpSendPoInfoList)) {
                                msgMap.flush();
                                return;
                            }

                            // QC#16994 DEL.
//                          S21TransactionTableAccessor s21TrxTblAccessor = new S21TransactionTableAccessor();
//                          s21TrxTblAccessor.createIntegrationRecordForBatch(NPAA0010, transactionId);

                            intTrxaccessor.createIntegrationRecord(NPAA0010, transactionId);

                            // Create PO ACK
                            // ******************************************
                            if (!createCusaPoAck(msgMap, tmpSendPoInfoList, apiParam)) {
                                msgMap.flush();
                                return;
                            }

                            // poStatusUpdate
                            // ******************************************
                            if (!poStatusUpdate(msgMap, tmpSendPoInfoList, apiParam)) {
                                msgMap.flush();
                                return;
                            }
                        } else {
                            msgMap.flush();
                            return;
                        }

                    }

                } else {

                    // Create PO ACK
                    // ******************************************
                    if (!createCusaPoAck(msgMap, tmpSendPoInfoList, apiParam)) {
                        msgMap.flush();
                        return;
                    }

                    // poStatusUpdate
                    // ******************************************
                    if (!poStatusUpdate(msgMap, tmpSendPoInfoList, apiParam)) {
                        msgMap.flush();
                        return;
                    }

                    // NPXC001001 CreatePOHistory
                    // ******************************************
                    if (!callCreatePoHistoryApi(msgMap, tmpSendPoInfoList, EVENT_ID_SEND_PO)) {
                        msgMap.flush();
                        return;
                    }

                    // NPZC0040 PO Status Update
                    // ******************************************
                    if (!callPoStatusUpdateApi(msgMap, tmpSendPoInfoList, apiParam)) {
                        msgMap.flush();
                        return;
                    }

                }

                // IF Create Flag
            } else if (ZYPConstant.FLG_ON_Y.equals(param.xxsendPoIfCratFlg.getValue())) {

                this.intTrxaccessor = new S21IntTransactionTableAccessor(ITRL_ID_CSA_CUSA_WS);

                transactionId = intTrxaccessor.getNextTransactionId();

                // Call Send PO API for Interface NPZC1320
                if (!callSendPoIfApIf(msgMap, NPAA0010, tmpSendPoInfoList)) {
                    msgMap.flush();
                    return;
                }

                // QC#16994 DEL.
//                S21TransactionTableAccessor s21TrxTblAccessor = new S21TransactionTableAccessor();
//                s21TrxTblAccessor.createIntegrationRecordForBatch(NPAA0010, transactionId);

                intTrxaccessor.createIntegrationRecord(NPAA0010, transactionId);

                // Create PO ACK
                // ******************************************
                AWZC901001PMsg apiParam = new AWZC901001PMsg();
                if (!createCusaPoAck(msgMap, tmpSendPoInfoList, apiParam)) {
                    msgMap.flush();
                    return;
                }

                // poStatusUpdate
                // ******************************************
                if (!poStatusUpdate(msgMap, tmpSendPoInfoList, apiParam)) {
                    msgMap.flush();
                    return;
                }

                // NPXC001001 CreatePOHistory
                // ******************************************
                if (!callCreatePoHistoryApi(msgMap, tmpSendPoInfoList, EVENT_ID_SEND_PO)) {
                    msgMap.flush();
                    return;
                }

                msgMap.flush();

                return;

            } else {

                msgMap.flush();

                return;
            }

        } else if (MODE_CUSA_PA.equals(processMode)) {

            if (ZYPConstant.FLG_ON_Y.equals(param.xxsendPoIfCratFlg.getValue())) {

                this.intTrxaccessor = new S21IntTransactionTableAccessor(ITRL_ID_CSA_CUSA_PRT);

                transactionId = intTrxaccessor.getNextTransactionId();

                // Call Send PO API for Interface NPZC1320
                if (!callSendPoIfApIf(msgMap, NPAA0020, sendPoInfoList)) {
                    msgMap.flush();
                    return;
                }

                // QC#16994 DEL.
//                S21TransactionTableAccessor s21TrxTblAccessor = new S21TransactionTableAccessor();
//                s21TrxTblAccessor.createIntegrationRecordForBatch(NPAA0020, transactionId);

                intTrxaccessor.createIntegrationRecord(NPAA0020, transactionId);

                // Create PO ACK
                // ******************************************
                AWZC901001PMsg apiParam = new AWZC901001PMsg();
                if (!createCusaPoAck(msgMap, sendPoInfoList, apiParam)) {
                    msgMap.flush();
                    return;
                }

                // poStatusUpdate
                // ******************************************
                if (!poStatusUpdate(msgMap, sendPoInfoList, apiParam)) {
                    msgMap.flush();
                    return;
                }

                // NPXC001001 CreatePOHistory
                // ******************************************
                if (!callCreatePoHistoryApi(msgMap, sendPoInfoList, EVENT_ID_SEND_PO)) {
                    msgMap.flush();
                    return;
                }

            }

        } else if (MODE_CUSA_AZ.equals(processMode)) {

            if (ZYPConstant.FLG_ON_Y.equals(param.xxsendPoIfCratFlg.getValue())) {

                this.trxAccessor = new S21TransactionTableAccessor();

                transactionId = trxAccessor.getNextTransactionId();

                // Call Send PO API for Interface for AZERTY NPZC1330
                if (!callSendPoIfApIfAzerty(msgMap, NPAI2090)) {
                    msgMap.flush();
                    return;
                }

                S21TransactionTableAccessor s21TrxTblAccessor = new S21TransactionTableAccessor();
                s21TrxTblAccessor.createIntegrationRecordForBatch(NPAI2090, transactionId);

                // Create PO ACK
                // ******************************************
                AWZC901001PMsg apiParam = new AWZC901001PMsg();
                if (!createCusaPoAck(msgMap, sendPoInfoList, apiParam)) {
                    msgMap.flush();
                    return;
                }

                // poStatusUpdate
                // ******************************************
                if (!poStatusUpdate(msgMap, sendPoInfoList, apiParam)) {
                    msgMap.flush();
                    return;
                }

                // NPXC001001 CreatePOHistory
                // ******************************************
                if (!callCreatePoHistoryApi(msgMap, sendPoInfoList, EVENT_ID_SEND_PO)) {
                    msgMap.flush();
                    return;
                }

            }

        } else if (MODE_ADD_LINE.equals(processMode)) {

            // Set API Parameter
            AWZC901001PMsg apiParam = new AWZC901001PMsg();

            // CPO Number Check (UPDATE)
            if (!ZYPCommonFunc.hasValue((String) poInfoMap.get(VND_ISS_ORD_NUM))) {
                S21InfoLogOutput.println(NPAM1417E);
                msgMap.addXxMsgId(NPAM1417E);
                return;
            }

            ZYPEZDItemValueSetter.setValue(apiParam.cpoOrdNum, (String) poInfoMap.get(VND_ISS_ORD_NUM));

            // CPO Line Number
            Map<String, Object> getCusaCpoDtlNumMap = getCusaCpoDtlNumInfo(poInfoMap, apiParam);

            if (getCusaCpoDtlNumMap == null) {

                S21InfoLogOutput.println(NPAM1418E);
                msgMap.addXxMsgId(NPAM1418E);
                return;

            } else {

                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(0).cpoDtlLineNum, (String) getCusaCpoDtlNumMap.get(CPO_DTL_LINE_NUM));

            }

            // ******************************************
            // Call AWZC9010 CPO Creation for NA API
            // ******************************************

            apiParam = callCpoCreationApi(msgMap, sendPoInfoList, apiParam);

            if (apiParam.xxMsgIdList.getValidCount() > 0) {

                for (int idx = 0; idx < apiParam.xxMsgIdList.getValidCount(); idx++) {
                    msgMap.addXxMsgId(apiParam.xxMsgIdList.no(idx).xxMsgId.getValue());
                    S21InfoLogOutput.println(apiParam.xxMsgIdList.no(idx).xxMsgId.getValue());
                }

                if (!csaValidFlg) {
                    msgMap.flush();
                    return;

                }

                // Business Error occurred
                // NPXC001001 CreatePOHistory
                // ******************************************
                if (!callCreatePoHistoryApi(msgMap, sendPoInfoList, EVENT_ID_CUSA_CPO_CREATE_ERR)) {
                    msgMap.flush();
                    return;
                }

                // An error occurred in adding lines to CUSA order.
                // Please process it manually.
                S21InfoLogOutput.println(NPAM1419E);
                msgMap.addXxMsgId(NPAM1419E);

                msgMap.flush();
                return;

            } else {

                // Create PO ACK
                // ******************************************
                if (!createCusaPoAck(msgMap, sendPoInfoList, apiParam)) {
                    msgMap.flush();
                    return;
                }

                // poStatusUpdate
                // ******************************************
                if (!poStatusUpdate(msgMap, sendPoInfoList, apiParam)) {
                    msgMap.flush();
                    return;
                }

                // NPXC001001 CreatePOHistory
                // ******************************************
                if (!callCreatePoHistoryApi(msgMap, sendPoInfoList, EVENT_ID_SEND_PO)) {
                    msgMap.flush();
                    return;
                }

                // NPZC0040 PO Status Update
                // ******************************************
                if (!callPoStatusUpdateApi(msgMap, sendPoInfoList, apiParam)) {
                    msgMap.flush();
                    return;
                }

            }

        } else if (MODE_CANCEL_LINE.equals(processMode)) {

            // Set API Parameter
            AWZC901001PMsg apiParam = new AWZC901001PMsg();

            // CPO Order Number
            if (!ZYPCommonFunc.hasValue((String) poInfoMap.get(VND_ISS_ORD_NUM))) {
                S21InfoLogOutput.println(NPAM1417E);
                msgMap.addXxMsgId(NPAM1417E);
                return;
            }

            ZYPEZDItemValueSetter.setValue(apiParam.cpoOrdNum, (String) poInfoMap.get(VND_ISS_ORD_NUM));

            // ******************************************
            // Call AWZC9010 CPO Creation for NA API
            // ******************************************

            apiParam = callCpoCreationApi(msgMap, sendPoInfoList, apiParam);

            if (apiParam.xxMsgIdList.getValidCount() > 0 || !csaValidFlg || !cusaValidFlg) {

                for (int idx = 0; idx < apiParam.xxMsgIdList.getValidCount(); idx++) {
                    msgMap.addXxMsgId(apiParam.xxMsgIdList.no(idx).xxMsgId.getValue());
                    S21InfoLogOutput.println(apiParam.xxMsgIdList.no(idx).xxMsgId.getValue());
                }

                if (!csaValidFlg) {
                    msgMap.flush();
                    return;
                }

                if (!cusaValidFlg) {

                    // Business Error occurred
                    // NPXC001001 CreatePOHistory
                    // ******************************************
                    if (!callCreatePoHistoryApi(msgMap, sendPoInfoList, EVENT_ID_CUSA_CPO_CREATE_ERR)) {
                        msgMap.flush();
                        return;
                    }

                    // An error occurred in canceling lines to CUSA
                    // order. Please process it manually.
                    S21InfoLogOutput.println(NPAM1420E);
                    msgMap.addXxMsgId(NPAM1420E);

                    msgMap.flush();
                    return;
                }

            } else {

                // Create PO ACK
                // ******************************************
                if (!createCusaPoAck(msgMap, sendPoInfoList, apiParam)) {
                    msgMap.flush();
                    return;
                }

                // poStatusUpdate
                // ******************************************
                if (!poStatusUpdate(msgMap, sendPoInfoList, apiParam)) {
                    msgMap.flush();
                    return;
                }

                // NPXC001001 CreatePOHistory
                // ******************************************
                if (!callCreatePoHistoryApi(msgMap, sendPoInfoList, EVENT_ID_SEND_PO)) {
                    msgMap.flush();
                    return;
                }

            }

        } else if (MODE_CANCEL_ADD.equals(processMode)) {

            // MODE CHANGE TO ADD
            // ******************************************
            processMode = MODE_ADD_LINE;

            // Set API Parameter
            AWZC901001PMsg apiParam = new AWZC901001PMsg();

            // CPO Number Check (UPDATE)
            if (!ZYPCommonFunc.hasValue((String) poInfoMap.get(VND_ISS_ORD_NUM))) {

                S21InfoLogOutput.println(NPAM1417E);
                msgMap.addXxMsgId(NPAM1417E);
                return;
            }

            ZYPEZDItemValueSetter.setValue(apiParam.cpoOrdNum, (String) poInfoMap.get(VND_ISS_ORD_NUM));

            // CPO Line Number
            Map<String, Object> getCusaCpoDtlNumMap = getCusaCpoDtlNumInfo(poInfoMap, apiParam);

            if (getCusaCpoDtlNumMap == null) {

                S21InfoLogOutput.println(NPAM1418E);
                msgMap.addXxMsgId(NPAM1418E);
                return;

            } else {

                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(0).cpoDtlLineNum, (String) getCusaCpoDtlNumMap.get(CPO_DTL_LINE_NUM));

            }

            // ******************************************
            // Call AWZC9010 CPO Creation for NA API
            // ******************************************

            apiParam = callCpoCreationApi(msgMap, sendPoInfoList, apiParam);

            if (apiParam.xxMsgIdList.getValidCount() > 0 || !csaValidFlg || (!cusaValidFlg && !cusaCpoCreated)) {

                for (int idx = 0; idx < apiParam.xxMsgIdList.getValidCount(); idx++) {

                    msgMap.addXxMsgId(apiParam.xxMsgIdList.no(idx).xxMsgId.getValue());
                    S21InfoLogOutput.println(apiParam.xxMsgIdList.no(idx).xxMsgId.getValue());

                }

                if (!csaValidFlg) {
                    msgMap.flush();
                    return;

                }

                if (!cusaValidFlg) {

                    // Business Error occurred
                    // NPXC001001 CreatePOHistory
                    // ******************************************
                    if (!callCreatePoHistoryApi(msgMap, sendPoInfoList, EVENT_ID_CUSA_CPO_CREATE_ERR)) {
                        msgMap.flush();
                        return;
                    }

                    // An error occurred in adding lines to CUSA
                    // order.
                    // Please process it manually.
                    S21InfoLogOutput.println(NPAM1419E);
                    msgMap.addXxMsgId(NPAM1419E);

                    msgMap.flush();
                    return;
                }

            }

            // Create PO ACK
            // ******************************************
            if (!createCusaPoAck(msgMap, sendPoInfoList, apiParam)) {
                msgMap.flush();
                return;
            }

            // poStatusUpdate
            // ******************************************
            if (!poStatusUpdate(msgMap, sendPoInfoList, apiParam)) {
                msgMap.flush();
                return;
            }

            // NPXC001001 CreatePOHistory
            // ******************************************
            if (!callCreatePoHistoryApi(msgMap, sendPoInfoList, EVENT_ID_SEND_PO)) {
                msgMap.flush();
                return;
            }

            // NPZC0040 PO Status Update
            // ******************************************
            if (!callPoStatusUpdateApi(msgMap, sendPoInfoList, apiParam)) {
                msgMap.flush();
                return;
            }

            // MODE CHANGE TO CANCEL
            // ******************************************
            processMode = MODE_CANCEL_LINE;

            // Set API Parameter Clear for Cancel
            apiParam.clear();

            // CPO Order Number
            if (!ZYPCommonFunc.hasValue((String) poInfoMap.get(VND_ISS_ORD_NUM))) {
                S21InfoLogOutput.println(NPAM1417E);
                msgMap.addXxMsgId(NPAM1417E);
                return;
            }

            ZYPEZDItemValueSetter.setValue(apiParam.cpoOrdNum, (String) poInfoMap.get(VND_ISS_ORD_NUM));

            // ******************************************
            // Call AWZC9010 CPO Creation for NA API
            // ******************************************

            apiParam = callCpoCreationApi(msgMap, sendPoInfoList, apiParam);

            if (apiParam.xxMsgIdList.getValidCount() > 0 || !csaValidFlg || (!cusaValidFlg && !cusaCpoCreated)) {

                for (int idx = 0; idx < apiParam.xxMsgIdList.getValidCount(); idx++) {

                    msgMap.addXxMsgId(apiParam.xxMsgIdList.no(idx).xxMsgId.getValue());
                    S21InfoLogOutput.println(apiParam.xxMsgIdList.no(idx).xxMsgId.getValue());

                }

                if (!csaValidFlg) {
                    msgMap.flush();
                    return;

                }

                if (!csaValidFlg) {
                    // change to IF
                    if (ZYPConstant.FLG_ON_Y.equals(param.xxsendPoIfCratFlg.getValue())) {

                        // Business Error occurred
                        // NPXC001001 CreatePOHistory
                        // ******************************************
                        if (!callCreatePoHistoryApi(msgMap, sendPoInfoList, EVENT_ID_CUSA_CPO_CREATE_ERR)) {
                            msgMap.flush();
                            return;
                        }

                        // An error occurred in canceling lines to
                        // CUSA
                        // order. Please process it manually.
                        S21InfoLogOutput.println(NPAM1420E);
                        msgMap.addXxMsgId(NPAM1420E);

                    }

                    msgMap.flush();
                    return;

                }

            }

            // Create PO ACK
            // ******************************************
            if (!createCusaPoAck(msgMap, sendPoInfoList, apiParam)) {
                msgMap.flush();
                return;
            }

            // poStatusUpdate
            // ******************************************
            if (!poStatusUpdate(msgMap, sendPoInfoList, apiParam)) {
                msgMap.flush();
                return;
            }

            // NPXC001001 CreatePOHistory
            // ******************************************
            if (!callCreatePoHistoryApi(msgMap, sendPoInfoList, EVENT_ID_SEND_PO)) {
                msgMap.flush();
                return;
            }

            // NPZC0040 PO Create API for NA
            // ******************************************
            if (!callPoStatusUpdateApi(msgMap, sendPoInfoList, apiParam)) {
                msgMap.flush();
                return;
            }

        } else {

            msgMap.flush();
            return;

        }

        msgMap.flush();

        return;
    }

    /**
     * checkParam
     * @param msgMap
     * @param onBatchType
     * @return
     */
    private boolean checkParam(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        EZDDebugOutput.println(1, PROGRAM_ID + "[ checkParam ] start", this);

        NPZC134001PMsg checkParam = (NPZC134001PMsg) msgMap.getPmsg();

        boolean returnValue = true;

        // Mandatory ******************************************
        // GLBL_CMPY_CD
        if (!ZYPCommonFunc.hasValue(checkParam.glblCmpyCd)) {
            S21InfoLogOutput.println(NPZM0179E);
            msgMap.addXxMsgId(NPZM0179E);
            returnValue = false;
        }

        // SLS_DT
        if (!ZYPCommonFunc.hasValue(checkParam.slsDt)) {
            S21InfoLogOutput.println(NPZM0180E);
            msgMap.addXxMsgId(NPZM0180E);
            returnValue = false;
        }

        // XX_PROC_TP_CD
        if (!ZYPCommonFunc.hasValue(checkParam.xxProcTpCd)) {
            S21InfoLogOutput.println(NPAM1397E);
            msgMap.addXxMsgId(NPAM1397E);
            returnValue = false;
        }

        // PO_ORD_NUM
        if (!ZYPCommonFunc.hasValue(checkParam.poOrdNum)) {
            S21InfoLogOutput.println(NPAM1384E);
            msgMap.addXxMsgId(NPAM1384E);
            returnValue = false;
        }

        // DRCT_CPO_CRAT_FLG
        if (!ZYPCommonFunc.hasValue(checkParam.xxdrctCpoCratFlg)) {
            S21InfoLogOutput.println(NPAM1398E);
            msgMap.addXxMsgId(NPAM1398E);
            returnValue = false;
        }

        // SEND_PO_IF_CRAT_FLG
        if (!ZYPCommonFunc.hasValue(checkParam.xxsendPoIfCratFlg)) {
            S21InfoLogOutput.println(NPAM1399E);
            msgMap.addXxMsgId(NPAM1399E);
            returnValue = false;
        }

        // consistency check
        // XX_PROC_TP_CD
        if (!XX_PROC_TP_CD_1.equals(checkParam.xxProcTpCd.getValue()) && !XX_PROC_TP_CD_2.equals(checkParam.xxProcTpCd.getValue())) {
            S21InfoLogOutput.println(NPAM1400E);
            msgMap.addXxMsgId(NPAM1400E);
            returnValue = false;
        }

        // DRCT_CPO_CRAT_FLG
        if (!ZYPConstant.FLG_ON_Y.equals(checkParam.xxdrctCpoCratFlg.getValue()) && !ZYPConstant.FLG_OFF_N.equals(checkParam.xxdrctCpoCratFlg.getValue())) {
            S21InfoLogOutput.println(NPAM1401E);
            msgMap.addXxMsgId(NPAM1401E);
            returnValue = false;
        }

        // SEND_PO_IF_CRAT_FLG
        if (!ZYPConstant.FLG_ON_Y.equals(checkParam.xxsendPoIfCratFlg.getValue()) && !ZYPConstant.FLG_OFF_N.equals(checkParam.xxsendPoIfCratFlg.getValue())) {
            S21InfoLogOutput.println(NPAM1402E);
            msgMap.addXxMsgId(NPAM1402E);
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * @param msgMap
     * @param sendPoInfoList
     * @return
     */
    private List<Map<String, Object>> getCusaCpoList(S21ApiMessageMap msgMap, List<Map<String, Object>> sendPoInfoList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        ssmParam.put(BIND_PO_ORD_NUM, sendPoInfoList.get(0).get("PO_ORD_NUM"));

        return ssmBatchClient.queryObjectList("getCusaCpoList", ssmParam);

    }

    /**
     * sendPoInfo
     * @param msgMap
     * @param onBatchType
     * @return sendPoInfo
     */
    private List<Map<String, Object>> sendPoInfo(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NPZC134001PMsg sendParam = (NPZC134001PMsg) msgMap.getPmsg();

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        List<String> poStsCdList = new ArrayList<String>();

        poStsCdList.add(PO_STS.VALIDATED);
        poStsCdList.add(PO_STS.CANCELLED);
        ssmParam.put(BIND_GLBL_CMPY_CD, sendParam.glblCmpyCd.getValue());
        ssmParam.put(BIND_PO_ORD_NUM, sendParam.poOrdNum.getValue());
        ssmParam.put(BIND_PO_MSG_TP_CD, PO_MSG_TP_CD_03);
        ssmParam.put(BIND_PO_STS_CD_LIST, poStsCdList);
        ssmParam.put(BIND_PO_MDSE_CMPSN_TP_CD, MDSE_CMPSN_TP.SET_ORDERTAKE_MDSE);
        ssmParam.put(BIND_PO_STS_CD_EX, PO_STS.CANCELLED);
        ssmParam.put(BIND_PO_MSG_SEG_ID, PO_MSG_SEG_ID_1);

        return ssmBatchClient.queryObjectList("sendPoInfo", ssmParam);
    }

    /**
     * getPoStsCd
     * @param msgMap
     * @param onBatchType
     * @return getPoStsCd
     */
    private List<Map<String, Object>> getPoStsCd(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NPZC134001PMsg stsParam = (NPZC134001PMsg) msgMap.getPmsg();

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        List<String> poStsCdList = new ArrayList<String>();

        poStsCdList.add(PO_STS.VALIDATED);
        poStsCdList.add(PO_STS.CANCELLED);
        ssmParam.put(BIND_GLBL_CMPY_CD, stsParam.glblCmpyCd.getValue());
        ssmParam.put(BIND_PO_ORD_NUM, stsParam.poOrdNum.getValue());
        ssmParam.put(BIND_PO_MSG_TP_CD, PO_MSG_TP_CD_03);
        ssmParam.put(BIND_PO_STS_CD_LIST, poStsCdList);
        ssmParam.put(BIND_PO_MDSE_CMPSN_TP_CD, MDSE_CMPSN_TP.SET_ORDERTAKE_MDSE);
        ssmParam.put(BIND_PO_STS_CD_EX, PO_STS.CANCELLED);
        ssmParam.put(BIND_PO_MSG_SEG_ID, PO_MSG_SEG_ID_1);

        return ssmBatchClient.queryObjectList("getPoStsCd", ssmParam);
    }

    /**
     * getTrdPtnrShpgXRefInfo
     * @param cusaCpoApiMap Map<String, Object>
     * @param msgMap S21ApiMessageMap
     * @return
     */
    private Map<String, Object> getTrdPtnrShpgXRefInfo(Map<String, Object> cusaCpoApiMap, S21ApiMessageMap msgMap) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        NPZC134001PMsg shpgParam = (NPZC134001PMsg) msgMap.getPmsg();

        Map<String, Object> trdPtnrShpgXRefMap = new HashMap<String, Object>();

        String glblCmpyCd = shpgParam.glblCmpyCd.getValue();
        String vndCd = (String) cusaCpoApiMap.get(VND_CD);
        String carrCd = (String) cusaCpoApiMap.get(CARR_CD);
        String shpgSvcLvlCd = (String) cusaCpoApiMap.get(SHPG_SVC_LVL_CD);
        String frtCondCd = (String) cusaCpoApiMap.get(FRT_COND_CD);


        if (vndCd == null) {

            // no search
            ssmParam.clear();

            return trdPtnrShpgXRefMap;

        }
        // QC#26006 MOD START
        if (ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(shpgSvcLvlCd) && ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 1
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, shpgSvcLvlCd, frtCondCd);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 2
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, frtCondCd);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 3
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 4
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 5
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (!ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(shpgSvcLvlCd) && ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 2
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, frtCondCd);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 4
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (ZYPCommonFunc.hasValue(carrCd) && !ZYPCommonFunc.hasValue(shpgSvcLvlCd) && ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 5
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(shpgSvcLvlCd) && !ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 3
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 4
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 5
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (ZYPCommonFunc.hasValue(carrCd) && !ZYPCommonFunc.hasValue(shpgSvcLvlCd) && !ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 5
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (!ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(shpgSvcLvlCd) && !ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 4
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (!ZYPCommonFunc.hasValue(carrCd) && !ZYPCommonFunc.hasValue(shpgSvcLvlCd) && ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (!ZYPCommonFunc.hasValue(carrCd) && !ZYPCommonFunc.hasValue(shpgSvcLvlCd) && !ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }
        }

        return trdPtnrShpgXRefMap;
     // START QC#23087 T.Hakodate MOD

////        if (cusaCpoApiMap.get(CARR_CD) == null && cusaCpoApiMap.get(SHPG_SVC_LVL_CD) == null && cusaCpoApiMap.get(FRT_COND_CD) == null) {
//
//        if (cusaCpoApiMap.get(VND_CD) == null) {
//
//            // no search
//            ssmParam.clear();
//
//            return trdPtnrShpgXRefMap;
//
//        } else {
//
//            String carrCd = null;
//            String shpgSvcLvlCd = null;
//            String frtCondCd = null;
//
//            if (cusaCpoApiMap.get(CARR_CD) == null) {
//                carrCd = STAR;
//            } else {
//                carrCd = (String) cusaCpoApiMap.get(CARR_CD);
//            }
//
//            if (cusaCpoApiMap.get(SHPG_SVC_LVL_CD) == null) {
//                shpgSvcLvlCd = STAR;
//            } else {
//                shpgSvcLvlCd = (String) cusaCpoApiMap.get(SHPG_SVC_LVL_CD);
//            }
//
//            if (cusaCpoApiMap.get(FRT_COND_CD) == null) {
//                frtCondCd = STAR;
//            } else {
//                frtCondCd = (String) cusaCpoApiMap.get(FRT_COND_CD);
//            }
//
//            ssmParam.clear();
//            ssmParam.put(BIND_GLBL_CMPY_CD, shpgParam.glblCmpyCd.getValue());
//            ssmParam.put(BIND_VND_CD, cusaCpoApiMap.get(VND_CD));
//            ssmParam.put(BIND_CARR_CD, carrCd);
//            ssmParam.put(BIND_SHPG_SVC_LCL_CD, shpgSvcLvlCd);
//            ssmParam.put(BIND_FRT_COND_CD, frtCondCd);
//
//            trdPtnrShpgXRefMap = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
//
//        }
//
//        return trdPtnrShpgXRefMap;
        // QC#26006 MOD END

    }

    /**
     * searchTrdPtnrShpgXRefInfo
     * @param glblCmpyCd
     * @param vndCd
     * @param carrCd
     * @param shpgSvcLvlCd
     * @param frtCondCd
     * @return
     */
    private Map<String, Object> searchTrdPtnrShpgXRefInfo(String glblCmpyCd, String vndCd, String carrCd, String shpgSvcLvlCd, String frtCondCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        Map<String, Object> trdPtnrShpgXRefMap = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(BIND_VND_CD, vndCd);
        ssmParam.put(BIND_CARR_CD, carrCd);
        ssmParam.put(BIND_SHPG_SVC_LCL_CD, shpgSvcLvlCd);
        ssmParam.put(BIND_FRT_COND_CD, frtCondCd);

        trdPtnrShpgXRefMap = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);

        return trdPtnrShpgXRefMap;

    }

//        } else if (cusaCpoApiMap.get(CARR_CD) != null && cusaCpoApiMap.get(SHPG_SVC_LVL_CD) != null && cusaCpoApiMap.get(FRT_COND_CD) != null) {
//
//            // search1
//            ssmParam.clear();
//            ssmParam.put(BIND_GLBL_CMPY_CD, shpgParam.glblCmpyCd.getValue());
//            ssmParam.put(BIND_VND_CD, cusaCpoApiMap.get(VND_CD));
//            ssmParam.put(BIND_CARR_CD, cusaCpoApiMap.get(CARR_CD));
//            ssmParam.put(BIND_SHPG_SVC_LCL_CD, cusaCpoApiMap.get(SHPG_SVC_LVL_CD));
//            ssmParam.put(BIND_FRT_COND_CD, cusaCpoApiMap.get(FRT_COND_CD));
//
//            trdPtnrShpgXRefMap = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
//
//            if (trdPtnrShpgXRefMap != null) {
//
//                return trdPtnrShpgXRefMap;
//
//            } else {
//
//                // search2 ******************************************
//                ssmParam.clear();
//                ssmParam.put(BIND_GLBL_CMPY_CD, shpgParam.glblCmpyCd.getValue());
//                ssmParam.put(BIND_VND_CD, cusaCpoApiMap.get(VND_CD));
//                ssmParam.put(BIND_CARR_CD, STAR);
//                ssmParam.put(BIND_SHPG_SVC_LCL_CD, cusaCpoApiMap.get(SHPG_SVC_LVL_CD));
//                ssmParam.put(BIND_FRT_COND_CD, cusaCpoApiMap.get(FRT_COND_CD));
//
//                trdPtnrShpgXRefMap = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
//
//                if (trdPtnrShpgXRefMap != null) {
//
//                    return trdPtnrShpgXRefMap;
//
//                } else {
//                    // search3
//                    // ******************************************
//                    ssmParam.clear();
//                    ssmParam.put(BIND_GLBL_CMPY_CD, shpgParam.glblCmpyCd.getValue());
//                    ssmParam.put(BIND_VND_CD, cusaCpoApiMap.get(VND_CD));
//                    ssmParam.put(BIND_CARR_CD, cusaCpoApiMap.get(CARR_CD));
//                    ssmParam.put(BIND_SHPG_SVC_LCL_CD, STAR);
//                    ssmParam.put(BIND_FRT_COND_CD, STAR);
//                    trdPtnrShpgXRefMap = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
//                    if (trdPtnrShpgXRefMap != null) {
//
//                    } else {
//                        // search4
//                        // ******************************************
//                        ssmParam.clear();
//                        ssmParam.put(BIND_GLBL_CMPY_CD, shpgParam.glblCmpyCd.getValue());
//                        ssmParam.put(BIND_VND_CD, cusaCpoApiMap.get(VND_CD));
//                        ssmParam.put(BIND_CARR_CD, STAR);
//                        ssmParam.put(BIND_SHPG_SVC_LCL_CD, STAR);
//                        ssmParam.put(BIND_FRT_COND_CD, STAR);
//
//                        trdPtnrShpgXRefMap = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
//                    }
//                }
//            }
//            return trdPtnrShpgXRefMap;
//
//        } else if (cusaCpoApiMap.get(CARR_CD) == null) {
//
//            // search2 ******************************************
//            ssmParam.clear();
//            ssmParam.put(BIND_GLBL_CMPY_CD, shpgParam.glblCmpyCd.getValue());
//            ssmParam.put(BIND_VND_CD, cusaCpoApiMap.get(VND_CD));
//            ssmParam.put(BIND_CARR_CD, STAR);
//            ssmParam.put(BIND_SHPG_SVC_LCL_CD, cusaCpoApiMap.get(SHPG_SVC_LVL_CD));
//            ssmParam.put(BIND_FRT_COND_CD, cusaCpoApiMap.get(FRT_COND_CD));
//
//            trdPtnrShpgXRefMap = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
//
//            if (trdPtnrShpgXRefMap != null) {
//                return trdPtnrShpgXRefMap;
//
//            } else {
//
//                // search4 ******************************************
//                ssmParam.clear();
//                ssmParam.put(BIND_GLBL_CMPY_CD, shpgParam.glblCmpyCd.getValue());
//                ssmParam.put(BIND_VND_CD, cusaCpoApiMap.get(VND_CD));
//                ssmParam.put(BIND_CARR_CD, STAR);
//                ssmParam.put(BIND_SHPG_SVC_LCL_CD, STAR);
//                ssmParam.put(BIND_FRT_COND_CD, STAR);
//
//                trdPtnrShpgXRefMap = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
//
//            }
//
//        } else {
//
//            // search3 ******************************************
//            ssmParam.clear();
//            ssmParam.put(BIND_GLBL_CMPY_CD, shpgParam.glblCmpyCd.getValue());
//            ssmParam.put(BIND_VND_CD, cusaCpoApiMap.get(VND_CD));
//            ssmParam.put(BIND_CARR_CD, cusaCpoApiMap.get(CARR_CD));
//            ssmParam.put(BIND_SHPG_SVC_LCL_CD, STAR);
//            ssmParam.put(BIND_FRT_COND_CD, STAR);
//
//            trdPtnrShpgXRefMap = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
//
//            if (trdPtnrShpgXRefMap != null) {
//
//                return trdPtnrShpgXRefMap;
//
//            } else {
//
//                // search4 ******************************************
//                ssmParam.clear();
//                ssmParam.put(BIND_GLBL_CMPY_CD, shpgParam.glblCmpyCd.getValue());
//                ssmParam.put(BIND_VND_CD, cusaCpoApiMap.get(VND_CD));
//                ssmParam.put(BIND_CARR_CD, STAR);
//                ssmParam.put(BIND_SHPG_SVC_LCL_CD, STAR);
//                ssmParam.put(BIND_FRT_COND_CD, STAR);
//
//                trdPtnrShpgXRefMap = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
//            }
//        }
//        return trdPtnrShpgXRefMap;
//    }

    // END QC#23087 T.Hakodate MOD
    
    /**
     * @param poInfoMap
     * @param apiParam
     * @return
     */
    private Map<String, Object> getCusaCpoDtlNumInfo(Map<String, Object> poInfoMap, AWZC901001PMsg apiParam) {

        Map<String, Object> ssmParamCpoDtl = new HashMap<String, Object>();

        ssmParamCpoDtl.put(BIND_GLBL_CMPY_CD, cusaGlblCmpyCd);
        ssmParamCpoDtl.put(BIND_CPO_ORD_NUM, poInfoMap.get(VND_ISS_ORD_NUM));

        return (Map<String, Object>) ssmBatchClient.queryObject("getCusaCpoDtlNumInfo", ssmParamCpoDtl);
    }

    /**
     * @param cusaCpoApiMap
     * @param apiParam
     * @return
     */
    private Map<String, Object> getCusaCpoDtlNumInfo2(Map<String, Object> cusaCpoApiMap, AWZC901001PMsg apiParam) {

        Map<String, Object> ssmParamCpoDtl2 = new HashMap<String, Object>();

        ssmParamCpoDtl2.put(BIND_GLBL_CMPY_CD, cusaGlblCmpyCd);
        ssmParamCpoDtl2.put(BIND_CPO_ORD_NUM, cusaCpoApiMap.get(CPO_ORD_NUM));
        ssmParamCpoDtl2.put(BIND_EDI_NUM, cusaCpoApiMap.get(PO_ORD_DTL_LINE_NUM));
        ssmParamCpoDtl2.put(BIND_MDSE_CD, cusaCpoApiMap.get(ASL_MDSE_CD));

        return (Map<String, Object>) ssmBatchClient.queryObject("getCusaCpoDtlNumInfo2", ssmParamCpoDtl2);
    }

    /****************************************************************
     * CUSA WS checkCreateApiParam
     ****************************************************************/
    private boolean checkCreateApiParam(S21ApiMessageMap msgMap, AWZC901001PMsg apiParam) {

        EZDDebugOutput.println(1, PROGRAM_ID + "[ checkCreateApiParam ] start", this);

        boolean returnValue = true;

        String mode = apiParam.xxModeCd.getValue();

        // Mandatory ******************************************
        // Header ******************************************

        // glblCmpyCd
        if (!ZYPCommonFunc.hasValue(apiParam.glblCmpyCd)) {
            S21InfoLogOutput.println(NPZM0179E);
            msgMap.addXxMsgId(NPZM0179E);
            returnValue = false;
        }
        // xxModeCd
        if (!ZYPCommonFunc.hasValue(apiParam.xxModeCd)) {
            S21InfoLogOutput.println(NSZM0175E);
            msgMap.addXxMsgId(NSZM0175E);
            returnValue = false;
        }
        // slsDt
        if (!ZYPCommonFunc.hasValue(apiParam.slsDt)) {
            S21InfoLogOutput.println(NMZM0082E);
            msgMap.addXxMsgId(NMZM0082E);
            returnValue = false;
        }

        if (IF_MODE_CD_1.equals(mode)) {

            // cpoOrdTpCd
            if (!ZYPCommonFunc.hasValue(apiParam.cpoOrdTpCd)) {
                S21InfoLogOutput.println(NLBM1115E);
                msgMap.addXxMsgId(NLBM1115E);
                returnValue = false;
            }
            // cpoSrcTpCd
            if (!ZYPCommonFunc.hasValue(apiParam.cpoSrcTpCd)) {
                S21InfoLogOutput.println(NLBM0001E);
                msgMap.addXxMsgId(NLBM0001E);
                returnValue = false;
            }
            // sysSrcCd
            if (!ZYPCommonFunc.hasValue(apiParam.sysSrcCd)) {
                S21InfoLogOutput.println(NLBM0001E);
                msgMap.addXxMsgId(NLBM0001E);
                returnValue = false;
            }
            // custIssPoNum
            if (!ZYPCommonFunc.hasValue(apiParam.custIssPoNum)) {
                S21InfoLogOutput.println(NLBM0001E);
                msgMap.addXxMsgId(NLBM0001E);
                returnValue = false;
            }

        }

        return returnValue;

    }

    /****************************************************************
     * Create Po ACK
     * @param processMode
     ****************************************************************/
    /**
     * createCusaPoAck
     * @param msgMap
     * @param sendPoInfoList
     * @param apiParam
     * @return
     */
    private boolean createCusaPoAck(S21ApiMessageMap msgMap, List<Map<String, Object>> sendPoInfoList, AWZC901001PMsg apiParam) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ createCusaPoAck ] start", this);

        NPZC134001PMsg ackParam = (NPZC134001PMsg) msgMap.getPmsg();
        Map<String, Object> sendPoMap = sendPoInfoList.get(0);

        boolean returnValue = true;

        // PO ACK Line Status
        Map<String, Object> ssmParamAckSts = new HashMap<String, Object>();

        ssmParamAckSts.put(BIND_GLBL_CMPY_CD, ackParam.glblCmpyCd.getValue());

        if (ZYPConstant.FLG_OFF_N.equals(ackParam.xxdrctCpoCratFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(ackParam.xxsendPoIfCratFlg.getValue())) {

            ssmParamAckSts.put(BIND_VND_PO_ACK_STS_CD, VND_PO_ACK_STS_CD_IW);

        } else if (MODE_CUSA_PA.equals(processMode) || MODE_CUSA_AZ.equals(processMode)) {

            ssmParamAckSts.put(BIND_VND_PO_ACK_STS_CD, VND_PO_ACK_STS_CD_IW);

        } else {

            ssmParamAckSts.put(BIND_VND_PO_ACK_STS_CD, VND_PO_ACK_STS_CD_CW);

        }

        Map<String, Object> cusaVndPoAck = (Map<String, Object>) ssmBatchClient.queryObject("getPoAckStatusInfo", ssmParamAckSts);

        if (cusaVndPoAck == null || cusaVndPoAck.get(PO_ACK_LINE_STS_CD) == null) {

            S21InfoLogOutput.println(NPAM1413E);
            msgMap.addXxMsgId(NPAM1413E);
            returnValue = false;

        }

        // PO ACK Number getPoAckNumberInfo
        Map<String, Object> ssmParampoAckNum = new HashMap<String, Object>();

        ssmParampoAckNum.put(BIND_GLBL_CMPY_CD, ackParam.glblCmpyCd.getValue());
        ssmParampoAckNum.put(BIND_PO_ORD_NUM, sendPoMap.get(PO_ORD_NUM));

        String poAckNum = (String) ssmBatchClient.queryObject("getPoAckNumberInfo", ssmParampoAckNum);

        // Create or update ack
        // Header

        PO_ACK_HDRTMsg poAckHdrTmsg = new PO_ACK_HDRTMsg();

        BigDecimal poAckHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_HDR_SQ);

        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.glblCmpyCd, ackParam.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.poAckHdrPk, poAckHdrPk);
        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.poSendTs, ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT));
        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.poOrdNum, (String) sendPoMap.get(PO_ORD_NUM));

        if (MODE_CUSA_WS.equals(processMode)) {
            ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.itrlIntfcId, ITRL_INTFC_ID_NPAA0030);
        } else if (MODE_CUSA_PA.equals(processMode)) {
            ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.itrlIntfcId, ITRL_INTFC_ID_NPAA0040);
        } else if (MODE_CUSA_AZ.equals(processMode)) {
            ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.itrlIntfcId, ITRL_INTFC_ID_NPAI2100);
        }

        if (ZYPConstant.FLG_ON_Y.equals(ackParam.xxdrctCpoCratFlg.getValue())) {
            if (XX_PROC_TP_CD_1.equals(ackParam.xxProcTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.vndCpoOrdNum, apiParam.cpoOrdNum_OP.getValue());
            } else if (XX_PROC_TP_CD_2.equals(ackParam.xxProcTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.vndCpoOrdNum, apiParam.cpoOrdNum.getValue());
            } else {
                poAckHdrTmsg.vndCpoOrdNum.clear();
            }
        } else {
            poAckHdrTmsg.vndCpoOrdNum.clear();
        }

        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.vndCd, (String) sendPoMap.get(VND_CD));
        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.poAckNum, poAckNum);
        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.poAckUpdProcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.poAckHdrLtstFlg, ZYPConstant.FLG_ON_Y);

        // Line

        List<PO_ACK_DTLTMsg> poAckDtlTmsgList = new ArrayList<PO_ACK_DTLTMsg>();
        List<BigDecimal> poAckDtlPkList = new ArrayList<BigDecimal>();

        for (int i = 0; i < sendPoInfoList.size(); i++) {

            PO_ACK_DTLTMsg poAckDtlTmsg = new PO_ACK_DTLTMsg();

            BigDecimal poAckDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_DTL_SQ);

            Map<String, Object> sendPoInfoMap = sendPoInfoList.get(i);

            ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.glblCmpyCd, ackParam.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.poAckDtlPk, poAckDtlPk);
            ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.poOrdDtlLineNum, (String) sendPoInfoMap.get(PO_ORD_DTL_LINE_NUM));
            poAckDtlTmsg.poOrdDtlSubLineNum.clear();
            poAckDtlTmsg.shpgPlnDplyLineNum.clear();
            ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.poOrdNum, (String) sendPoInfoMap.get(PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.poAckLineStsCd, (String) cusaVndPoAck.get(PO_ACK_LINE_STS_CD));
            ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.vndPoAckLineStsTxt, (String) cusaVndPoAck.get(VND_PO_ACK_STS_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.vndMdseCd, (String) sendPoInfoMap.get(ASL_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.mdseCd, (String) sendPoInfoMap.get(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.mdseNm, (String) sendPoInfoMap.get(MDSE_NM));

            if (sendPoInfoMap.get(CUST_MDSE_CD) != null) {

                ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.trdPtnrSkuCd, (String) sendPoInfoMap.get(CUST_MDSE_CD));

            } else {

                ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.trdPtnrSkuCd, (String) sendPoInfoMap.get(MDSE_CD));
            }

            ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.ordQty, (BigDecimal) sendPoInfoMap.get(PO_QTY));

            poAckDtlTmsg.thisMthFobCostAmt.clear();
            poAckDtlTmsg.ccyCd.clear();
            poAckDtlTmsg.uomCd.clear();
            poAckDtlTmsg.etdDt.clear();
            poAckDtlTmsg.etaDt.clear();

            if (ZYPConstant.FLG_ON_Y.equals(ackParam.xxdrctCpoCratFlg.getValue())) {

                if (XX_PROC_TP_CD_1.equals(ackParam.xxProcTpCd.getValue())) {

                    ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.vndCpoOrdNum, apiParam.cpoOrdNum_OP.getValue());

                } else if (XX_PROC_TP_CD_2.equals(ackParam.xxProcTpCd.getValue())) {

                    ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.vndCpoOrdNum, apiParam.xxLineList.no(i).cpoDtlLineNum.getValue());

                } else {

                    poAckDtlTmsg.vndCpoOrdNum.clear();

                }

                ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.vndCpoDtlLineNum, apiParam.xxLineList.no(i).cpoDtlLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.vndCpoDtlLineSubNum, apiParam.xxLineList.no(i).cpoDtlLineSubNum.getValue());
                ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.vndPoAckStsCd, VND_PO_ACK_STS_CD_CW);

            } else if (MODE_CUSA_PA.equals(processMode) || MODE_CUSA_AZ.equals(processMode)) {

                poAckDtlTmsg.vndCpoOrdNum.clear();
                poAckDtlTmsg.vndCpoDtlLineNum.clear();
                poAckDtlTmsg.vndCpoDtlLineSubNum.clear();
                ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.vndPoAckStsCd, VND_PO_ACK_STS_CD_IW);

            }

            poAckDtlTmsg.vndCpoLineStsCd.clear();
            poAckDtlTmsg.shpgStsCd.clear();
            poAckDtlTmsg.origTrdPtnrSkuCd.clear();
            poAckDtlTmsg.origOrdQty.clear();
            poAckDtlTmsg.origVndMdseCd.clear();
            poAckDtlTmsg.shpgPlnNum.clear();
            poAckDtlTmsg.vndCpoCratTs.clear();
            poAckDtlTmsg.ordDtlLastUpdTs.clear();
            poAckDtlTmsg.vndInvtyLocCd.clear();
            poAckDtlTmsg.vndIssPoOrdNum.clear();
            poAckDtlTmsg.poAckCmntTxt.clear();
            poAckDtlTmsg.vndOtbdCarrCd.clear();
            poAckDtlTmsg.vndOtbdCarrNm.clear();
            poAckDtlTmsg.proNum.clear();
            poAckDtlTmsg.vndSoNum.clear();
            poAckDtlTmsg.vndSoSlpNum.clear();
            poAckDtlTmsg.vndShipToCustCd.clear();
            poAckDtlTmsg.vndShipToCustLocNm.clear();
            poAckDtlTmsg.vndSellToCustCd.clear();
            poAckDtlTmsg.vndSellToCustLocNm.clear();
            poAckDtlTmsg.vndChildBomPrcAmt.clear();
            ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.poAckHdrPk, poAckHdrTmsg.poAckHdrPk);
            ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.poAckDtlLtstFlg, ZYPConstant.FLG_ON_Y);

            poAckDtlPkList.add(poAckDtlPk);
            poAckDtlTmsgList.add(poAckDtlTmsg);

        }

        S21ApiTBLAccessor.insert(poAckHdrTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poAckHdrTmsg.getReturnCode())) {
            S21InfoLogOutput.println(NPAM1414E);
            msgMap.addXxMsgId(NPAM1414E);
            returnValue = false;
        }

        // synchronize AP ACK HDR
        // ***********************************
        if (XX_PROC_TP_CD_2.equals(ackParam.xxProcTpCd.getValue())) {

            if (!synchronizePoAckHdr(poAckHdrPk, sendPoMap, msgMap)) {
                S21InfoLogOutput.println(NPAM1414E);
                msgMap.addXxMsgId(NPAM1414E);
                returnValue = false;
            }
        }

        PO_ACK_DTLTMsg[] insertTMsg = new PO_ACK_DTLTMsg[poAckDtlTmsgList.size()];

        for (int i = 0; i < poAckDtlTmsgList.size(); i++) {

            insertTMsg[i] = poAckDtlTmsgList.get(i);
        }

        int retCnt = 0;

        for (int i = 0; i < insertTMsg.length; i++) {
            PO_ACK_DTLTMsg insertData = insertTMsg[i];
            S21ApiTBLAccessor.insert(insertData);

            if (DB_EXECUTE_SUCCESS.equals(insertData.getReturnCode())) {
                retCnt++;
            }
        }

        if (retCnt != poAckDtlTmsgList.size()) {
            msgMap.addXxMsgId(NPAM1414E);
            returnValue = false;
        }

        // synchronize AP DTL
        // ***********************************
        if (XX_PROC_TP_CD_2.equals(ackParam.xxProcTpCd.getValue())) {

            if (!synchronizePoAckDtl(poAckDtlTmsgList, poAckDtlPkList, sendPoMap, msgMap)) {
                S21InfoLogOutput.println(NPAM1414E);
                msgMap.addXxMsgId(NPAM1414E);
                returnValue = false;
            }
        }

        return returnValue;

    }

    /****************************************************************
     * PO Create AWZC9010
     * @param msgMap
     ****************************************************************/
    private AWZC901001PMsg callCpoCreationApi(S21ApiMessageMap msgMap, List<Map<String, Object>> sendPoInfoList, AWZC901001PMsg apiParam) {

        EZDDebugOutput.println(1, PROGRAM_ID + "[ callCpoCreationApi ] start", this);

        Map<String, String> errorMap = new HashMap<String, String>();

        // Flg Init
        csaValidFlg = true;
        cusaValidFlg = true;
        // QC#24788
        cusaDuplicateFlg = false;

        NPZC134001PMsg param = (NPZC134001PMsg) msgMap.getPmsg();

        Map<String, Object> poInfoMap = sendPoInfoList.get(0);

        // ******************************************
        // HEADER
        // *****************************************

        // glblCmpyCd
        ZYPEZDItemValueSetter.setValue(apiParam.glblCmpyCd, cusaGlblCmpyCd);

        if (MODE_CUSA_WS.equals(processMode)) {
            // xxModeCd
            ZYPEZDItemValueSetter.setValue(apiParam.xxModeCd, IF_MODE_CD_1);

        } else if (MODE_ADD_LINE.equals(processMode)) {
            // xxModeCd
            ZYPEZDItemValueSetter.setValue(apiParam.xxModeCd, IF_MODE_CD_2);

        } else if (MODE_CANCEL_LINE.equals(processMode)) {
            // xxModeCd
            ZYPEZDItemValueSetter.setValue(apiParam.xxModeCd, IF_MODE_CD_3);

        }

        // slsDt
        ZYPEZDItemValueSetter.setValue(apiParam.slsDt, param.slsDt.getValue());

        if (MODE_CUSA_WS.equals(processMode)) {
            // cpoOrdTpCd
            ZYPEZDItemValueSetter.setValue(apiParam.cpoOrdTpCd, CPO_ORD_TP_CD_10);
            // cpoSrcTpCd
            ZYPEZDItemValueSetter.setValue(apiParam.cpoSrcTpCd, CPO_SRC_TP_CD_95);
            // sysSrcCd
            ZYPEZDItemValueSetter.setValue(apiParam.sysSrcCd, SYS_SRC_CD_AW);
            // custIssPoNum
            ZYPEZDItemValueSetter.setValue(apiParam.custIssPoNum, (String) poInfoMap.get(PO_ORD_NUM));
            // custIssPoDt
            ZYPEZDItemValueSetter.setValue(apiParam.custIssPoDt, (String) poInfoMap.get(PO_APVL_DT));
            // cpoOrdNum null
            apiParam.cpoOrdNum.clear();
            // sellToFirstRefCmntTxt
            ZYPEZDItemValueSetter.setValue(apiParam.sellToFirstRefCmntTxt, (String) poInfoMap.get(SHIP_TO_CUST_CD));

        } else {
            // cpoOrdTpCd
            apiParam.cpoOrdTpCd.clear();
            // cpoSrcTpCd
            apiParam.cpoSrcTpCd.clear();
            // sysSrcCd
            apiParam.sysSrcCd.clear();
            // custIssPoNum
            apiParam.custIssPoNum.clear();
            // custIssPoDt
            apiParam.custIssPoDt.clear();
            // sellToFirstRefCmntTxt
            apiParam.sellToFirstRefCmntTxt.clear();
        }

        // sellToScdRefCmntTxt null
        apiParam.sellToScdRefCmntTxt.clear();

        // CSA Validation Check Header
        if (MODE_CUSA_WS.equals(processMode)) {
            if (!ZYPCommonFunc.hasValue(apiParam.custIssPoNum)) {
                S21InfoLogOutput.println(NPAM1384E);
                msgMap.addXxMsgId(NPAM1384E);
                csaValidFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(apiParam.custIssPoDt)) {
                S21InfoLogOutput.println(NPAM1404E);
                msgMap.addXxMsgId(NPAM1404E);
                csaValidFlg = false;
            }

        }

        // ******************************************
        // LINE
        // ******************************************

        int cpoLineNum = 0;
        int cpoLineSubNum = 0;

        String cpoDtlLineNum = null;
        String cpoDtlLineSubNum = null;

        if (MODE_ADD_LINE.equals(processMode)) {

            cpoLineNum = Integer.parseInt((apiParam.xxLineList.no(0).cpoDtlLineNum.getValue()));

        }

        int rowCnt = 0;
        for (int i = 0; i < sendPoInfoList.size(); i++) {

            String ediLineNum = null;

            Map<String, Object> cusaCpoApiMap = sendPoInfoList.get(i);
            String lineNumber = (String) cusaCpoApiMap.get("PO_ORD_DTL_LINE_NUM");

            if (MODE_CANCEL_LINE.equals(processMode)) {

                // CPO Line Number
                Map<String, Object> getCusaCpoDtlNumMap2 = getCusaCpoDtlNumInfo2(cusaCpoApiMap, apiParam);

                if (getCusaCpoDtlNumMap2 == null) {
                    S21InfoLogOutput.println(NPAM1418E);
                    msgMap.addXxMsgId(NPAM1418E);
                    errorMap.put("msgId", NPAM1418E);
                    errorMap.put("lineNumber", lineNumber);
                    errMsgList.add(errorMap);
                    return apiParam;
                }

            } else {

                if (PO_MDSE_CMPSN_TP.PARENT.equals(cusaCpoApiMap.get(PO_MDSE_CMPSN_TP_CD))) {

                    cpoLineNum = cpoLineNum + 1;
                    cpoLineSubNum = 0;
                    ediLineNum = (String) cusaCpoApiMap.get(PO_ORD_DTL_LINE_NUM);

                } else if (PO_MDSE_CMPSN_TP.CHILD.equals(cusaCpoApiMap.get(PO_MDSE_CMPSN_TP_CD))) {

                    cpoLineSubNum = cpoLineSubNum + 1;
                    ediLineNum = (String) cusaCpoApiMap.get(SET_PO_ORD_DTL_LINE_NUM);

                } else if (PO_MDSE_CMPSN_TP.REGULAR.equals(cusaCpoApiMap.get(PO_MDSE_CMPSN_TP_CD))) {

                    cpoLineNum = cpoLineNum + 1;
                    cpoLineSubNum = 1;
                    ediLineNum = (String) cusaCpoApiMap.get(PO_ORD_DTL_LINE_NUM);

                } else {

                    EZDDebugOutput.println(1, PROGRAM_ID + "PO_MDSE_CMPSN_TP_CD", this);
                    msgMap.addXxMsgId(NPAM0007E);
                    errorMap.put("msgId", NPAM0007E);
                    errorMap.put("lineNumber", lineNumber);
                    errMsgList.add(errorMap);
                }
            }

            cpoDtlLineNum = String.format("%03d", cpoLineNum);
            cpoDtlLineSubNum = String.format("%03d", cpoLineSubNum);

            // Freight
            // ******************************************
            String shpgSvcLvlCd = null;
            String frtChrgMethCd = null;
            String carrCd = null;
            String shipViaCdString = null;

            Map<String, Object> trdPtnrMap = getTrdPtnrShpgXRefInfo(cusaCpoApiMap, msgMap);

            if (trdPtnrMap != null) {
                shpgSvcLvlCd = (String) trdPtnrMap.get(TRD_PTNR_SHPG_SVC_LVL_CD);
                frtChrgMethCd = (String) trdPtnrMap.get(TRD_PTNR_FRT_CHRG_METH_CD);
                carrCd = (String) trdPtnrMap.get(TRD_PTNR_CARR_CD);
                shipViaCdString = (String) trdPtnrMap.get(TRD_PTNR_SHIP_VIA_CD);
            }

            // Ship To
            // ******************************************
            // Drop Ship Flag, Ship To Cust Code
            // ******************************************
            String dropShipFlg = (String) cusaCpoApiMap.get(VND_DROP_SHIP_FLG);
            String cusaShipToCustCd = searchCsaLocationCd(dropShipFlg, cusaCpoApiMap, apiParam, msgMap, (String) poInfoMap.get(INVTY_LOC_CD));

            //QC#26074 Start
            String vndLocCd = searchVndLocationCd(dropShipFlg, cusaCpoApiMap, apiParam, msgMap, (String) poInfoMap.get(INVTY_LOC_CD));
            // dropShipFlag
            if(MD_VND_LOC_CD.equals(vndLocCd)){
                dropShipFlg = ZYPConstant.FLG_ON_Y;
            }
            //QC#26074 End
            
            // Ship To Cust Code does not exist.
            if (!ZYPCommonFunc.hasValue(cusaShipToCustCd)) {
                S21InfoLogOutput.println(NPZM0287E);
                msgMap.addXxMsgId(NPZM0287E);
                errorMap.put("msgId", NPZM0287E);
                errorMap.put("lineNumber", lineNumber);
                errMsgList.add(errorMap);
                csaValidFlg = false;
            }

            // QC#20346
            String shipToLocNm = (String) cusaCpoApiMap.get(SHIP_TO_LOC_NM);
            String shipToAddlLocNm = (String) cusaCpoApiMap.get(SHIP_TO_ADDL_LOC_NM);

            if (shipToLocNm != null) {
                if (shipToLocNm.length() > TXT_LENGTH_35) {
                    shipToLocNm = shipToLocNm.substring(0, TXT_LENGTH_35);
                }
            }

            if (shipToAddlLocNm != null) {

                if (shipToAddlLocNm.length() > TXT_LENGTH_35) {
                    shipToAddlLocNm = shipToAddlLocNm.substring(0, TXT_LENGTH_35);
                }
            }

            // Ship To Address
            // ******************************************
            String shipToFirstLineAddr = null;
            String shipToScdLineAddr = null;
            String shipToCtyAddr = null;

            // shipToFirstLineAddr
            StringBuffer shipToFirstLineAddrBuffer = new StringBuffer();

            if (cusaCpoApiMap.get(SHIP_TO_FIRST_LINE_ADDR) != null) {

                shipToFirstLineAddrBuffer.append((String) cusaCpoApiMap.get(SHIP_TO_FIRST_LINE_ADDR));
                shipToFirstLineAddrBuffer.append(" ");

            }

            if (cusaCpoApiMap.get(SHIP_TO_SCD_LINE_ADDR) != null) {

                shipToFirstLineAddrBuffer.append((String) cusaCpoApiMap.get(SHIP_TO_SCD_LINE_ADDR));
                shipToFirstLineAddrBuffer.append(" ");

            }

            if (cusaCpoApiMap.get(SHIP_TO_THIRD_LINE_ADDR) != null) {

                shipToFirstLineAddrBuffer.append((String) cusaCpoApiMap.get(SHIP_TO_THIRD_LINE_ADDR));
                shipToFirstLineAddrBuffer.append(" ");

            }

            if (cusaCpoApiMap.get(SHIP_TO_FRTH_LINE_ADDR) != null) {

                shipToFirstLineAddrBuffer.append((String) cusaCpoApiMap.get(SHIP_TO_FRTH_LINE_ADDR));
                shipToFirstLineAddrBuffer.append(" ");

            }

            shipToFirstLineAddr = new String(shipToFirstLineAddrBuffer);

            // QC:51557 Start
            shipToFirstLineAddr = shipToFirstLineAddr.replaceAll("\\r\\n|\\r|\\n|\\t", " ");
            // QC:51557 End

            if (shipToFirstLineAddr != null && shipToFirstLineAddr.length() > TXT_LENGTH_60) {

                shipToFirstLineAddr = shipToFirstLineAddr.substring(0, TXT_LENGTH_60);

            }

            // shipToScdLineAddr
            if (cusaCpoApiMap.get(CTAC_PSN_NM) != null) {

                // START 2019/12/04 M.Naito [QC#54563-1,ADD]
                convertCharacterForPoInfo(cusaCpoApiMap);
                // END 2019/12/04 M.Naito [QC#54563-1,ADD]
                if (((String) cusaCpoApiMap.get(CTAC_PSN_NM)).length() > TXT_LENGTH_60) {

                    shipToScdLineAddr = ((String) cusaCpoApiMap.get(CTAC_PSN_NM)).substring(0, TXT_LENGTH_60);

                } else {

                    shipToScdLineAddr = (String) cusaCpoApiMap.get(CTAC_PSN_NM);

                }
            }

            // shipToCtyAddr
            if (cusaCpoApiMap.get(SHIP_TO_CTY_ADDR) != null) {

                if (((String) cusaCpoApiMap.get(SHIP_TO_CTY_ADDR)).length() > TXT_LENGTH_20) {

                    shipToCtyAddr = ((String) cusaCpoApiMap.get(SHIP_TO_CTY_ADDR)).substring(0, TXT_LENGTH_20);

                } else {

                    shipToCtyAddr = (String) cusaCpoApiMap.get(SHIP_TO_CTY_ADDR);

                }

            }

            //QC#23356 Start
            //Carrier Account Num
            String carrAcctNum = null;
            
            if(cusaCpoApiMap.get(CARR_ACCT_NUM) == null){
                carrAcctNum = getCarrAcctNum(cusaCpoApiMap, apiParam, msgMap, poInfoMap);

                // QC#27628 mod start
                if (ZYPCommonFunc.hasValue(carrAcctNum)) {
                    carrAcctNumFromXref = true;
                }
                // QC#27628 mod end
                
            }else{
                carrAcctNum = (String) cusaCpoApiMap.get(CARR_ACCT_NUM);
            }
            //QC#23356 End
            // 
            // LINE : xxLineList
            // ******************************************
            if (MODE_CUSA_WS.equals(processMode)) {
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).ediNum, ediLineNum);
                // ediSubNum
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).ediSubNum, EDI_SUB_NUM_001);
                // mdseCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).mdseCd, (String) cusaCpoApiMap.get(ASL_MDSE_CD));
                // custMdseCd
                if (cusaCpoApiMap.get(CUST_MDSE_CD) != null) {
                    ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).custMdseCd, (String) cusaCpoApiMap.get(CUST_MDSE_CD));
                } else {
                    ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).custMdseCd, (String) cusaCpoApiMap.get(MDSE_CD));
                }
                // ordQty
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).ordQty, (BigDecimal) cusaCpoApiMap.get(PO_QTY));
                // invtyLocCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).invtyLocCd, (String) cusaCpoApiMap.get(VND_INVTY_LOC_CD));
                // custUomCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).custUomCd, CUST_UOM_CD_EA);
                // rddDt
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).rddDt, (String) cusaCpoApiMap.get(ETA_DT));
                // START 2023/02/17 TZ.Win [QC#60966, ADD]
                // rqstShipDt
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).rsdDt, (String) cusaCpoApiMap.get(RQST_SHIP_DT));
                // END 2023/02/17 TZ.Win [QC#60966, ADD]
                // shpgSvcLvlCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shpgSvcLvlCd, shpgSvcLvlCd);
                // frtChrgMethCd
                // START 2019/10/17 T.Ogura [QC#53813,MOD]
//                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).frtChrgMethCd, frtChrgMethCd);
                if (ZYPCommonFunc.hasValue(frtChrgMethCd) && apiParam.xxLineList.no(rowCnt).getAttr("frtChrgMethCd").getDigit() < frtChrgMethCd.length()) {
                    S21InfoLogOutput.println(NPZM0312E);
                    msgMap.addXxMsgId(NPZM0312E);
                    errorMap.put("msgId", NPZM0312E);
                    errorMap.put("lineNumber", lineNumber);
                    errMsgList.add(errorMap);
                    csaValidFlg = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).frtChrgMethCd, frtChrgMethCd);
                }
                // END   2019/10/17 T.Ogura [QC#53813,MOD]
                // frtChrgToCd null
                apiParam.xxLineList.no(rowCnt).frtChrgToCd.clear();
                // dropShipFlg
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).dropShipFlg, dropShipFlg);
                // shipToCustCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToCustCd, cusaShipToCustCd);
                // shipToLocNm
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToLocNm, shipToLocNm);
                // shipToAddlLocNm
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToAddlLocNm, shipToAddlLocNm);
                // shipToFirstLineAddr
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToFirstLineAddr, shipToFirstLineAddr);
                // shipToScdLineAddr
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToScdLineAddr, shipToScdLineAddr);
                // shipToCtyAddr
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToCtyAddr, shipToCtyAddr);
                // shipToStCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToStCd, (String) cusaCpoApiMap.get(SHIP_TO_ST_CD));
                // shipToPostCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToPostCd, (String) cusaCpoApiMap.get(SHIP_TO_POST_CD));
                // shipToCtryCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToCtryCd, (String) cusaCpoApiMap.get(SHIP_TO_CTRY_CD));
                // shipToCntyNm
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToCntyNm, (String) cusaCpoApiMap.get(SHIP_TO_CNTY_NM));
                // carrCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).carrCd, carrCd);
                // carrAcctNum
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).carrAcctNum, carrAcctNum);
                // shipCpltCd Null
                apiParam.xxLineList.no(rowCnt).shipCpltCd.clear();

            } else if (MODE_ADD_LINE.equals(processMode)) {
                // ediNum
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).ediNum, ediLineNum);
                // ediSubNum
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).ediSubNum, EDI_SUB_NUM_001);
                // mdseCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).mdseCd, (String) cusaCpoApiMap.get(ASL_MDSE_CD));
                // custMdseCd
                if (cusaCpoApiMap.get(CUST_MDSE_CD) != null) {
                    ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).custMdseCd, (String) cusaCpoApiMap.get(CUST_MDSE_CD));
                } else {
                    ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).custMdseCd, (String) cusaCpoApiMap.get(MDSE_CD));
                }
                // ordQty
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).ordQty, (BigDecimal) cusaCpoApiMap.get(PO_QTY));
                // invtyLocCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).invtyLocCd, (String) cusaCpoApiMap.get(VND_INVTY_LOC_CD));
                // custUomCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).custUomCd, CUST_UOM_CD_EA);
                // rddDt
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).rddDt, (String) cusaCpoApiMap.get(ETA_DT));
                // START 2023/02/17 TZ.Win [QC#60966, ADD]
                // rqstShipDt
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).rsdDt, (String) cusaCpoApiMap.get(RQST_SHIP_DT));
                // END 2023/02/17 TZ.Win [QC#60966, ADD]
                // shpgSvcLvlCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shpgSvcLvlCd, shpgSvcLvlCd);
                // frtChrgMethCd
                // START 2019/10/17 T.Ogura [QC#53813,MOD]
//                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).frtChrgMethCd, frtChrgMethCd);
                if (ZYPCommonFunc.hasValue(frtChrgMethCd) && apiParam.xxLineList.no(rowCnt).getAttr("frtChrgMethCd").getDigit() < frtChrgMethCd.length()) {
                    S21InfoLogOutput.println(NPZM0312E);
                    msgMap.addXxMsgId(NPZM0312E);
                    errorMap.put("msgId", NPZM0312E);
                    errorMap.put("lineNumber", lineNumber);
                    errMsgList.add(errorMap);
                    csaValidFlg = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).frtChrgMethCd, frtChrgMethCd);
                }
                // END   2019/10/17 T.Ogura [QC#53813,MOD]
                // frtChrgToCd null
                apiParam.xxLineList.no(rowCnt).frtChrgToCd.clear();
                // dropShipFlg
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).dropShipFlg, dropShipFlg);
                // shipToCustCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToCustCd, cusaShipToCustCd);
                // shipToLocNm
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToLocNm, shipToLocNm);
                // shipToAddlLocNm
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToAddlLocNm, shipToAddlLocNm);
                // shipToFirstLineAddr
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToFirstLineAddr, shipToFirstLineAddr);
                // shipToScdLineAddr
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToScdLineAddr, shipToScdLineAddr);
                // shipToCtyAddr
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToCtyAddr, shipToCtyAddr);
                // shipToStCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToStCd, (String) cusaCpoApiMap.get(SHIP_TO_ST_CD));
                // shipToPostCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToPostCd, (String) cusaCpoApiMap.get(SHIP_TO_POST_CD));
                // shipToCtryCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToCtryCd, (String) cusaCpoApiMap.get(SHIP_TO_CTRY_CD));
                // shipToCntyNm
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).shipToCntyNm, (String) cusaCpoApiMap.get(SHIP_TO_CNTY_NM));
                // carrCd
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).carrCd, carrCd);
                // carrAcctNum
                ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).carrAcctNum, carrAcctNum);
                // shipCpltCd Null
                apiParam.xxLineList.no(rowCnt).shipCpltCd.clear();

            } else if (MODE_CANCEL_LINE.equals(processMode)) {
                // ediNum
                apiParam.xxLineList.no(rowCnt).ediNum.clear();
                // ediSubNum
                apiParam.xxLineList.no(rowCnt).ediSubNum.clear();
                // mdseCd
                apiParam.xxLineList.no(rowCnt).mdseCd.clear();
                // custMdseCd
                apiParam.xxLineList.no(rowCnt).custMdseCd.clear();
                // ordQty
                apiParam.xxLineList.no(rowCnt).ordQty.clear();
                // invtyLocCd
                apiParam.xxLineList.no(rowCnt).invtyLocCd.clear();
                // custUomCd
                apiParam.xxLineList.no(rowCnt).custUomCd.clear();
                // rddDt
                apiParam.xxLineList.no(rowCnt).rddDt.clear();
                // START 2023/02/17 TZ.Win [QC#60966, ADD]
                // rqstShipDt
                apiParam.xxLineList.no(rowCnt).rsdDt.clear();
                // END 2023/02/17 TZ.Win [QC#60966, ADD]
                // shpgSvcLvlCd
                apiParam.xxLineList.no(rowCnt).shpgSvcLvlCd.clear();
                // frtChrgMethCd
                apiParam.xxLineList.no(rowCnt).frtChrgMethCd.clear();
                // frtChrgToCd null
                apiParam.xxLineList.no(rowCnt).frtChrgToCd.clear();
                // dropShipFlg
                apiParam.xxLineList.no(rowCnt).dropShipFlg.clear();
                // shipToCustCd
                apiParam.xxLineList.no(rowCnt).shipToCustCd.clear();
                // shipToLocNm
                apiParam.xxLineList.no(rowCnt).shipToLocNm.clear();
                // shipToAddlLocNm
                apiParam.xxLineList.no(rowCnt).shipToAddlLocNm.clear();
                // shipToFirstLineAddr
                apiParam.xxLineList.no(rowCnt).shipToFirstLineAddr.clear();
                // shipToScdLineAddr
                apiParam.xxLineList.no(rowCnt).shipToScdLineAddr.clear();
                // shipToCtyAddr
                apiParam.xxLineList.no(rowCnt).shipToCtyAddr.clear();
                // shipToStCd
                apiParam.xxLineList.no(rowCnt).shipToStCd.clear();
                // shipToPostCd
                apiParam.xxLineList.no(rowCnt).shipToPostCd.clear();
                // shipToCtryCd
                apiParam.xxLineList.no(rowCnt).shipToCtryCd.clear();
                // shipToCntyNm
                apiParam.xxLineList.no(rowCnt).shipToCntyNm.clear();
                // carrCd
                apiParam.xxLineList.no(rowCnt).carrCd.clear();
                // carrAcctNum
                apiParam.xxLineList.no(rowCnt).carrAcctNum.clear();
                // shipCpltCd Null
                apiParam.xxLineList.no(rowCnt).shipCpltCd.clear();

            }

            // cpoDtlLineNum
            ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).cpoDtlLineNum, cpoDtlLineNum);
            // cpoDtlLineSubNum
            ZYPEZDItemValueSetter.setValue(apiParam.xxLineList.no(rowCnt).cpoDtlLineSubNum, cpoDtlLineSubNum);

            // // LINE : xxLbReqInfoList
            // ******************************************
            // do not set

            // LINE : xxLineList Check
            // ******************************************

            // cpoDtlLineNum
            if (!ZYPCommonFunc.hasValue(apiParam.xxLineList.no(rowCnt).cpoDtlLineNum)) {
                S21InfoLogOutput.println(NLBM0001E);
                msgMap.addXxMsgId(NLBM0001E);
                errorMap.put("msgId", NLBM0001E);
                errorMap.put("lineNumber", lineNumber);
                errMsgList.add(errorMap);
            }

            // cpoDtlLineSubNum
            if (!ZYPCommonFunc.hasValue(apiParam.xxLineList.no(rowCnt).cpoDtlLineSubNum)) {
                S21InfoLogOutput.println(NLBM0001E);
                msgMap.addXxMsgId(NLBM0001E);
                errorMap.put("msgId", NLBM0001E);
                errorMap.put("lineNumber", lineNumber);
                errMsgList.add(errorMap);
            }

            // CSA Validation Check Line
            // ****************************************

            if (MODE_ADD_LINE.equals(processMode)) {

                if (!ZYPCommonFunc.hasValue(apiParam.xxLineList.no(rowCnt).mdseCd)) {
                    S21InfoLogOutput.println(NPAM1405E);
                    msgMap.addXxMsgId(NPAM1405E);
                    errorMap.put("msgId", NPAM1405E);
                    errorMap.put("lineNumber", lineNumber);
                    errMsgList.add(errorMap);
                    csaValidFlg = false;

                }

                if (!ZYPCommonFunc.hasValue(apiParam.xxLineList.no(rowCnt).ordQty)) {
                    S21InfoLogOutput.println(NPAM1406E);
                    msgMap.addXxMsgId(NPAM1406E);
                    errorMap.put("msgId", NPAM1406E);
                    errorMap.put("lineNumber", lineNumber);
                    errMsgList.add(errorMap);
                    csaValidFlg = false;

                } else {

                    if (apiParam.xxLineList.no(rowCnt).ordQty.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                        S21InfoLogOutput.println(NEAM0121E);
                        msgMap.addXxMsgId(NEAM0121E);
                        errorMap.put("msgId", NEAM0121E);
                        errorMap.put("lineNumber", lineNumber);
                        errMsgList.add(errorMap);
                        csaValidFlg = false;

                    }

                }

            }

            // ******************************************
            // CUSA Validation CHECK
            // ******************************************
            // PO Number

            if (!cusaValidationPoNumber(msgMap, poInfoMap, cusaShipToCustCd)) {
                cusaValidFlg = false;
                // QC#24788
                cusaDuplicateFlg = true;
                return apiParam;
            }

            // Set Item Check DS_PO_DTL.PO_MDSE_CMPSN_TP_CD="1"
            // ******************************************
            if (PO_MDSE_CMPSN_TP_PARENT.equals(cusaCpoApiMap.get(PO_MDSE_CMPSN_TP_CD))) {

                String setParentLineNum = (String) cusaCpoApiMap.get(PO_ORD_DTL_LINE_NUM);
                String parentMdseCd = (String) cusaCpoApiMap.get(ASL_MDSE_CD);
                BigDecimal parentPoQty = (BigDecimal) cusaCpoApiMap.get(PO_QTY);

                // Set Parent Check
                if (!cusaMdseType(msgMap, cusaCpoApiMap)) {
                    cusaValidFlg = false;
                    return apiParam;
                }

                for (int j = 0; j < sendPoInfoList.size(); j++) {

                    Map<String, Object> setItemMap = sendPoInfoList.get(j);

                    String setChildSetLineNum = null;
                    setChildSetLineNum = (String) setItemMap.get(SET_PO_ORD_DTL_LINE_NUM);

                    if (setChildSetLineNum != null) {

                        if (setParentLineNum.equals(setChildSetLineNum)) {

                            // set item Check
                            if (!cusaSetItem(msgMap, setItemMap, parentMdseCd, parentPoQty)) {
                                cusaValidFlg = false;
                                return apiParam;
                            }

                        }
                    }

                }
            }

            // drop ship check
            // ******************************************
            if (ZYPConstant.FLG_ON_Y.equals(dropShipFlg)) {
                if (!cusaDropShipItem(msgMap, cusaCpoApiMap, cusaShipToCustCd)) {
                    errorMap.put("msgId", NPAM1410E);
                    errorMap.put("lineNumber", lineNumber);
                    errMsgList.add(errorMap);
                    cusaValidFlg = false;
                    return apiParam;
                }

                // 2019/02/13 QC#30232 Add Start
                String sellToScdRefCmntTxt = null;
                String custIssPoNum = (String) poInfoMap.get(CUST_ISS_PO_NUM);
                String trxRefNum = (String) poInfoMap.get("TRX_REF_NUM");
                if (ZYPCommonFunc.hasValue(custIssPoNum) //
                        && ZYPCommonFunc.hasValue(trxRefNum)) {
                    sellToScdRefCmntTxt = custIssPoNum + "/" + trxRefNum;
                } else {
                    sellToScdRefCmntTxt = (String) poInfoMap.get(PO_ORD_NUM);
                }
                apiParam.sellToScdRefCmntTxt.setValue(sellToScdRefCmntTxt);
                // 2019/02/13 QC#30232 Add End
            } else { // 2019/02/13 QC#30232 Add Start
                apiParam.sellToScdRefCmntTxt.setValue((String) poInfoMap.get(PO_ORD_NUM)); // 2019/02/13 QC#30232 Add End
            }
            rowCnt++;
        }

        // setValidCount
        apiParam.xxLineList.setValidCount(rowCnt);

        // CSA Validation ERROR MSG Check
        if (!csaValidFlg) {
            // START 2019/10/17 T.Ogura [QC#53813,MOD]
//            if (apiParam.xxMsgIdList.getValidCount() > 0) {
//                for (int idx = 0; idx < apiParam.xxMsgIdList.getValidCount(); idx++) {
//                    S21InfoLogOutput.println(apiParam.xxMsgIdList.no(idx).xxMsgId.getValue());
//                    msgMap.addXxMsgId(apiParam.xxMsgIdList.no(idx).xxMsgId.getValue());
//                }
//
//                return apiParam;
//            }
            if (msgMap.getMsgMgr().getXxMsgIdListSize() > 0) {
                return apiParam;
            }
            // END   2019/10/17 T.Ogura [QC#53813,MOD]
        }

        // LINE : xxOrdMsgList
        // ******************************************
        if (MODE_CUSA_WS.equals(processMode)) {
            // txtTpCd
            ZYPEZDItemValueSetter.setValue(apiParam.xxOrdMsgList.no(0).txtTpCd, TXT_TP_CD_20);
            ZYPEZDItemValueSetter.setValue(apiParam.xxOrdMsgList.no(1).txtTpCd, TXT_TP_CD_20);
            ZYPEZDItemValueSetter.setValue(apiParam.xxOrdMsgList.no(2).txtTpCd, TXT_TP_CD_20);
            ZYPEZDItemValueSetter.setValue(apiParam.xxOrdMsgList.no(3).txtTpCd, TXT_TP_CD_20);

            // msgTxtInfoTxt
            // QC#14749
            setMsgWs(msgMap, poInfoMap, apiParam.xxOrdMsgList);
        }

        try {

            // Call API
            S21IntBizApiProxy intBizApiProxy = new S21IntBizApiProxy(S21BizApiHttpClientConst.SERVICE_SYSTEM_CODE_CUSA_WS);
            intBizApiProxy.execute(AWZC901001, NWZA0010, apiParam);

            if (!S21ApiUtil.isXxMsgId(apiParam)) {

                return apiParam;

            } else {

                List<String> msgList = S21ApiUtil.getXxMsgIdList(apiParam);

                for (String msg : msgList) {

                    // msgMap.addXxMsgId(msg);
                    S21InfoLogOutput.println(msg);

                }

                cusaValidFlg = false;

            }

        } catch (Exception e) {

            S21InfoLogOutput.println(NPAM1421E);

            cusaValidFlg = false;

            // get cusa cpo
            // *****************************************
            List<Map<String, Object>> cusaCpoList = new ArrayList<Map<String, Object>>();
            cusaCpoList = getCusaCpoList(msgMap, sendPoInfoList);

            if (!cusaCpoList.isEmpty()) {

                // cusa creted. set cpo Ordnum
                ZYPEZDItemValueSetter.setValue(apiParam.cpoOrdNum_OP, (String) cusaCpoList.get(0).get("CPO_ORD_NUM"));
                cusaCpoCreated = true;

            } else {

                errorMap.put("msgId", NPZM0296E);
                errorMap.put("lineNumber", SYSTEM_ERROR);
                errMsgList.add(errorMap);

            }

        }

        return apiParam;

    }

    /**
     * cusaValidationPoNumber
     * @param msgMap S21ApiMessageMap
     * @param poInfoMap Map<String, Object>
     * @param shipToCustCd String
     * @return boolean
     */
    private boolean cusaValidationPoNumber(S21ApiMessageMap msgMap, Map<String, Object> poInfoMap, String shipToCustCd) {

        boolean returnValue = true;

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, cusaGlblCmpyCd);
        ssmParam.put(BIND_CUST_ISS_PO_NUM, poInfoMap.get(PO_ORD_NUM));
        ssmParam.put(BIND_CUST_ISS_PO_DT, poInfoMap.get(PO_APVL_DT));
        ssmParam.put(BIND_SHIP_TO_CUST_CD, shipToCustCd);

        String poDate = null;

        poDate = (String) ssmBatchClient.queryObject("cusaValidationPoNumber", ssmParam);

        if (poDate != null) {

            S21InfoLogOutput.println(NPAM1407E);
            msgMap.addXxMsgId(NPAM1407E);
            returnValue = false;
            cusaValidFlg = false;
        }

        return returnValue;

    }

    /**
     * cusaMdseType
     * @param msgMap S21ApiMessageMap
     * @param cusaCpoApiMap Map<String, Object>
     * @return boolean
     */
    private boolean cusaMdseType(S21ApiMessageMap msgMap, Map<String, Object> cusaCpoApiMap) {

        boolean returnValue = true;

        Map<String, String> errorMap = new HashMap<String, String>();

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, cusaGlblCmpyCd);
        ssmParam.put(BIND_MDSE_CD, cusaCpoApiMap.get(ASL_MDSE_CD));

        String mdseCdTpCd = (String) ssmBatchClient.queryObject("cusaMdseType", ssmParam);

        String lineNumber = (String) cusaCpoApiMap.get("PO_ORD_DTL_LINE_NUM");

        if (!PO_MDSE_CMPSN_TP_CHILD.equals(mdseCdTpCd)) {
            S21InfoLogOutput.println(NPAM1408E);
            msgMap.addXxMsgId(NPAM1408E);
            errorMap.put("msgId", NPAM1408E);
            errorMap.put("lineNumber", lineNumber);
            errMsgList.add(errorMap);
            returnValue = false;
            cusaValidFlg = false;
        }

        return returnValue;

    }

    /**
     * cusaSetItem
     * @param msgMap S21ApiMessageMap
     * @param setItemMap Map<String, Object>
     * @return boolean
     */
    private boolean cusaSetItem(S21ApiMessageMap msgMap, Map<String, Object> setItemMap, String parentMdseCd, BigDecimal parentPoQty) {

        boolean returnValue = false;
        boolean mdseMatchFlag = false;
        boolean qtyMatchFlag = false;

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        Map<String, String> errorMap = new HashMap<String, String>();

        String csaSetMdseCd = (String) setItemMap.get(ASL_MDSE_CD);
        BigDecimal csaSetMdseQty = (BigDecimal) setItemMap.get(PO_QTY);

        ssmParam.put(BIND_GLBL_CMPY_CD, cusaGlblCmpyCd);
        ssmParam.put(BIND_PRNT_MDSE_CD, parentMdseCd);

        List<Map<String, Object>> cusaSetItemList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("cusaSetItem", ssmParam);

        // QC#20442
        // If the first 8 digits of Item exists in ORD_TAKE_MDSE of CSA, it compares with 8 digits of Child Code
        boolean isOrdTake = isOrdTakeMdse(msgMap, csaSetMdseCd);

        for (int i = 0; i < cusaSetItemList.size(); i++) {

            Map<String, Object> setItemListMap = cusaSetItemList.get(i);

            String isOrderTake = (String)setItemListMap.get(IS_ORD_TAKE);
            String cusaSetMdseCd = (String) setItemListMap.get(MDSE_CD);
            BigDecimal cusaSetMdseQty = (BigDecimal) setItemListMap.get(CHILD_MDSE_QTY);

            // QC#17077 Start
            // QC#20442
//          if (ZYPConstant.FLG_ON_Y.equals(isOrderTake)) {
            if (isOrdTake) {
                if (cusaSetMdseCd.length() > TXT_LENGTH_8) {
                    cusaSetMdseCd = cusaSetMdseCd.substring(0, TXT_LENGTH_8);
                }
                if (csaSetMdseCd.length() > TXT_LENGTH_8) {
                    csaSetMdseCd = csaSetMdseCd.substring(0, TXT_LENGTH_8);
                }
            }
        
            if (cusaSetMdseCd.equals(csaSetMdseCd)) {
            //if (cusaSetMdseCd.equals(csaSetMdseCd) || ((cusaSetMdseCd.substring(0, TXT_LENGTH_8)).equals(csaSetMdseCd.substring(0, TXT_LENGTH_8)))) {
            // QC#17077 End

                mdseMatchFlag = true;

                if (cusaSetMdseQty.compareTo(csaSetMdseQty.divide(parentPoQty, 1, BigDecimal.ROUND_HALF_UP)) == 0) {

                    qtyMatchFlag = true;
                }
            }

            if (mdseMatchFlag && qtyMatchFlag) {
                returnValue = true;
                break;
            }

        }

        String lineNumber = (String) setItemMap.get("PO_ORD_DTL_LINE_NUM");

        if (!mdseMatchFlag) {
            S21InfoLogOutput.println(NPAM1409E);
            msgMap.addXxMsgId(NPAM1409E);
            errorMap.put("msgId", NPAM1409E);
            errorMap.put("lineNumber", lineNumber);
            errMsgList.add(errorMap);
            returnValue = false;
        }

        if (!qtyMatchFlag) {
            S21InfoLogOutput.println(NPAM1409E);
            msgMap.addXxMsgId(NPAM1409E);
            errorMap.put("msgId", NPAM1409E);
            errorMap.put("lineNumber", lineNumber);
            errMsgList.add(errorMap);
            returnValue = false;
        }

        return returnValue;

    }

    /**
     * cusaDropShipItem
     * @param msgMap S21ApiMessageMap
     * @param cusaCpoApiMap Map<String, Object>
     * @param shipToCustCd String
     * @return boolean
     */
    private boolean cusaDropShipItem(S21ApiMessageMap msgMap, Map<String, Object> cusaCpoApiMap, String shipToCustCd) {
        boolean returnValue = true;
        // QC#27606
        if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            return false;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, cusaGlblCmpyCd);
        ssmParam.put(BIND_SHIP_TO_CUST_CD, shipToCustCd);

        if (ZYPCommonFunc.hasValue((String) cusaCpoApiMap.get(ASL_MDSE_CD))) {
            ssmParam.put(BIND_MDSE_CD, cusaCpoApiMap.get(ASL_MDSE_CD));
        } else {
            ssmParam.put(BIND_MDSE_CD, cusaCpoApiMap.get(MDSE_CD));
        }

        ssmParam.put(BIND_EDI_CUST_TP_CD_1, EDI_CUST_TP_CD_B);
        ssmParam.put(BIND_EDI_CUST_TP_CD_2, EDI_CUST_TP_CD_S);

        String cusaDropShipItem = (String) ssmBatchClient.queryObject("cusaDropShipItem", ssmParam);

        if (cusaDropShipItem == null) {
            S21InfoLogOutput.println(NPAM1410E);
            msgMap.addXxMsgId(NPAM1410E);
            cusaValidFlg = false;
            returnValue = false;
        }
        return returnValue;
    }

    /****************************************************************
     * // NPXC001001 CreatePOHistory
     ****************************************************************/
    /**
     * callCreatePoHistoryApi
     * @param msgMap S21ApiMessageMap
     * @param sendPoInfoList List<Map<String, Object>>
     * @return boolean
     */
    private boolean callCreatePoHistoryApi(S21ApiMessageMap msgMap, List<Map<String, Object>> sendPoInfoList, String eventId) {

        EZDDebugOutput.println(1, PROGRAM_ID + "[ callCreatePoHistoryApi ] start", this);

        boolean returnValue = true;

        NPZC134001PMsg poHistParam = (NPZC134001PMsg) msgMap.getPmsg();

        // GLBL_CMPY_CD
        String glblCmpyCd = null;

        // PO_ORD_NUM
        String poOrdNum = null;

        // PO_ORD_DTL_LINE_NUM
        String poOrdDtlLineNum = null;

        for (int i = 0; i < sendPoInfoList.size(); i++) {

            Map<String, Object> createPoHistMap = sendPoInfoList.get(i);

            glblCmpyCd = poHistParam.glblCmpyCd.getValue();
            poOrdNum = (String) createPoHistMap.get(PO_ORD_NUM);
            poOrdDtlLineNum = (String) createPoHistMap.get(PO_ORD_DTL_LINE_NUM);

            // RTRN="1"
            if (NPXC001001CreatePOHistory.createPOHistory(glblCmpyCd, eventId, poOrdNum, poOrdDtlLineNum) == 1) {
                msgMap.addXxMsgId(NPAM1411E);
                returnValue = false;
            }
        }

        return returnValue;

    }

    /****************************************************************
     * PO Status update NPZC0040
     ****************************************************************/

    /**
     * callPoStatusUpdateApi
     * @param msgMap S21ApiMessageMap
     * @param sendPoInfoList List<Map<String, Object>>
     * @param apiParam AWZC901001PMsg
     * @return boolean
     */
    private boolean callPoStatusUpdateApi(S21ApiMessageMap msgMap, List<Map<String, Object>> sendPoInfoList, AWZC901001PMsg apiParam) {

        EZDDebugOutput.println(1, PROGRAM_ID + "[ callPoStatusUpdateApi ] start", this);

        boolean returnValue = true;

        NPZC134001PMsg poStsParam = (NPZC134001PMsg) msgMap.getPmsg();

        Map<String, Object> statusUpdateApiMap = sendPoInfoList.get(0);

        NPZC004001PMsg statusUpdateApiParam = new NPZC004001PMsg();

        NPZC004001 api = new NPZC004001();

        if (XX_PROC_TP_CD_1.equals(poStsParam.xxProcTpCd.getValue())) {

            ZYPEZDItemValueSetter.setValue(statusUpdateApiParam.glblCmpyCd, poStsParam.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(statusUpdateApiParam.poOrdNum, (String) statusUpdateApiMap.get(PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(statusUpdateApiParam.poStsCd, PO_STS_CD_CONFIRMED);

            api.execute(statusUpdateApiParam, S21ApiInterface.ONBATCH_TYPE.BATCH);

            if (S21ApiUtil.isXxMsgId(statusUpdateApiParam)) {

                List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(statusUpdateApiParam);

                for (String xxMsgId : xxMsgIdList) {

                    S21InfoLogOutput.println(xxMsgId);

                    if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {

                        returnValue = false;

                    }
                }
            }

        } else {

            ZYPEZDItemValueSetter.setValue(statusUpdateApiParam.glblCmpyCd, poStsParam.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(statusUpdateApiParam.poOrdNum, (String) statusUpdateApiMap.get(PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(statusUpdateApiParam.poStsCd, PO_STS_CD_CONFIRMED);

            for (int i = 0; i < sendPoInfoList.size(); i++) {

                Map<String, Object> poOrdDtlLineMap = sendPoInfoList.get(i);

                ZYPEZDItemValueSetter.setValue(statusUpdateApiParam.poOrdDtlLineNum, (String) poOrdDtlLineMap.get(PO_ORD_DTL_LINE_NUM));

                api.execute(statusUpdateApiParam, S21ApiInterface.ONBATCH_TYPE.BATCH);

                if (S21ApiUtil.isXxMsgId(statusUpdateApiParam)) {

                    List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(statusUpdateApiParam);

                    for (String xxMsgId : xxMsgIdList) {

                        S21InfoLogOutput.println(xxMsgId);

                        if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {

                            returnValue = false;

                        }
                    }
                }

            }
        }
        return returnValue;
    }

    /**
     * @param msgMap
     * @param sendPoInfoList
     * @param apiParam
     * @return
     */
    private boolean updatePoDtlWithError(S21ApiMessageMap msgMap, List<Map<String, Object>> sendPoInfoList, AWZC901001PMsg apiParam) {

        NPZC134001PMsg poSendParam = (NPZC134001PMsg) msgMap.getPmsg();

        for (int i = 0; i < sendPoInfoList.size(); i++) {

            String poDtlLineNum = (String) sendPoInfoList.get(i).get(PO_ORD_DTL_LINE_NUM);

            PO_DTLTMsg poDtlTmsg = getPoDtl(poSendParam.glblCmpyCd.getValue(), poSendParam.poOrdNum.getValue(), poDtlLineNum);

            if (poDtlTmsg == null) {
                msgMap.addXxMsgId(NPZM0275E);
                return false;
            }

            String errMsgTxt = "";

            List<String> msgIds = S21ApiUtil.getXxMsgIdList(apiParam);

            for (String msgId : msgIds) {

                if (msgId.endsWith("E")) {
                    String msg = S21MessageFunc.clspGetMessage(msgId);
                    errMsgTxt = msg + errMsgTxt + ",";
                }
            }

            // START QC#20735 Mod.
            String msgId = null;
            String lineNumber = null;
            for (Map<String, String> errMsgMap : errMsgList) {

                msgId = errMsgMap.get("msgId");
                lineNumber = errMsgMap.get("lineNumber");

                // QC#20572
                if (poDtlLineNum.equals(lineNumber) || SYSTEM_ERROR.equals(msgId) || SYSTEM_ERROR.equals(lineNumber)) {
                    String msg = S21MessageFunc.clspGetMessage(msgId);
                    errMsgTxt = msg + errMsgTxt + ",";
                }
            }

            if (errMsgTxt.length() > TXT_LENGTH_400) {
                errMsgTxt = errMsgTxt.substring(0, TXT_LENGTH_400);
            }

            ZYPEZDItemValueSetter.setValue(poDtlTmsg.poDtlIntfcErrMsgTxt, errMsgTxt);
            // END QC#20735 Mod.

            S21ApiTBLAccessor.update(poDtlTmsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poDtlTmsg.getReturnCode())) {
                msgMap.addXxMsgId(NPZM0275E);
                return false;
            }

        }

        return true;

    }

    /**
     * poStatusUpdate
     * @param msgMap
     * @param sendPoInfoList
     * @param apiParam
     * @return boolean
     */
    private boolean poStatusUpdate(S21ApiMessageMap msgMap, List<Map<String, Object>> sendPoInfoList, AWZC901001PMsg apiParam) {

        EZDDebugOutput.println(1, PROGRAM_ID + "[ poStatusUpdate ] start", this);

        NPZC134001PMsg poSendParam = (NPZC134001PMsg) msgMap.getPmsg();

        POTMsg poTmsg = getPo(poSendParam.glblCmpyCd.getValue(), poSendParam.poOrdNum.getValue());
        if (poTmsg == null) {
            msgMap.addXxMsgId(NPAM1442E);
            return false;
        }

        String poSendTs = ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT);
        ZYPEZDItemValueSetter.setValue(poTmsg.poSendTs, poSendTs);
        
        if (!updatePo(poTmsg, apiParam, processMode, poSendParam)) {

            S21InfoLogOutput.println(NPAM1414E);
            msgMap.addXxMsgId(NPAM1414E);

        }

        for (int i = 0; i < sendPoInfoList.size(); i++) {

            String poDtlLineNum = (String) sendPoInfoList.get(i).get(PO_ORD_DTL_LINE_NUM);

            PO_DTLTMsg poDtlTmsg = getPoDtl(poSendParam.glblCmpyCd.getValue(), poSendParam.poOrdNum.getValue(), poDtlLineNum);
            if (poDtlTmsg == null) {
                msgMap.addXxMsgId(NPAM1442E);
                return false;
            }

            ZYPEZDItemValueSetter.setValue(poDtlTmsg.poSendTs, poSendTs);

            if (!updatePoDtl(poDtlTmsg, apiParam, processMode, poSendParam)) {
                S21InfoLogOutput.println(NPAM1414E);
                msgMap.addXxMsgId(NPAM1414E);
            }

        }

        return true;

    }

    /****************************************************************
     * PNPZC1320 Send PO API for Interface
     ****************************************************************/
    /**
     * callSendPoIfApIf
     * @param msgMap S21ApiMessageMap
     * @param interfaceId String
     * @param sendPoInfoList List<Map<String, Object>>
     * @return boolean
     */
    private boolean callSendPoIfApIf(S21ApiMessageMap msgMap, String interfaceId, List<Map<String, Object>> sendPoInfoList) {

        EZDDebugOutput.println(1, PROGRAM_ID + "[ callSendPoIfApIf ] start", this);

        boolean returnValue = true;

        NPZC134001PMsg sendPoParam = (NPZC134001PMsg) msgMap.getPmsg();
        NPZC132001PMsg sendPoApiIfParam = new NPZC132001PMsg();
        Map<String, Object> sendPoMap = sendPoInfoList.get(0);

        ZYPEZDItemValueSetter.setValue(sendPoApiIfParam.glblCmpyCd, sendPoParam.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(sendPoApiIfParam.itrlIntfcId, interfaceId);
        ZYPEZDItemValueSetter.setValue(sendPoApiIfParam.itrlTrxSq, this.transactionId);
        ZYPEZDItemValueSetter.setValue(sendPoApiIfParam.sendId, (String) sendPoMap.get(EDI_SEND_ID));
        ZYPEZDItemValueSetter.setValue(sendPoApiIfParam.rcvId, (String) sendPoMap.get(EDI_RCV_ID));
        ZYPEZDItemValueSetter.setValue(sendPoApiIfParam.poOrdNum, sendPoParam.poOrdNum.getValue());

        NPZC132001 api = new NPZC132001();
        api.execute(sendPoApiIfParam, S21ApiInterface.ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(sendPoApiIfParam)) {

            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(sendPoApiIfParam);

            for (String xxMsgId : xxMsgIdList) {

                S21InfoLogOutput.println(xxMsgId);

                if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {

                    msgMap.addXxMsgId(xxMsgId);
                    returnValue = false;

                }
            }
        }

        return returnValue;

    }

    /****************************************************************
     * Send PO API for Interface for AZERTY NPZC1330
     ****************************************************************/
    /**
     * callSendPoIfApIfAzerty
     * @param msgMap S21ApiMessageMap
     * @param interfaceId String
     * @return boolean
     */
    private boolean callSendPoIfApIfAzerty(S21ApiMessageMap msgMap, String interfaceId) {

        EZDDebugOutput.println(1, PROGRAM_ID + "[ callSendPoIfApIfAzerty ] start", this);

        boolean returnValue = true;

        NPZC134001PMsg sendPoAzParam = (NPZC134001PMsg) msgMap.getPmsg();
        NPZC133001PMsg sendPoApiIfAzertyParam = new NPZC133001PMsg();

        ZYPEZDItemValueSetter.setValue(sendPoApiIfAzertyParam.glblCmpyCd, sendPoAzParam.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(sendPoApiIfAzertyParam.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(sendPoApiIfAzertyParam.transactionId, this.transactionId);
        ZYPEZDItemValueSetter.setValue(sendPoApiIfAzertyParam.segmentId, new BigDecimal(NPZC1330_SEGMENT_ID));
        ZYPEZDItemValueSetter.setValue(sendPoApiIfAzertyParam.unitId, new BigDecimal(NPZC1330_UNIT_ID));
        ZYPEZDItemValueSetter.setValue(sendPoApiIfAzertyParam.seqNumber, new BigDecimal(NPZC1330_SEQ_NUMBER));
        ZYPEZDItemValueSetter.setValue(sendPoApiIfAzertyParam.poOrdNum, sendPoAzParam.poOrdNum.getValue());

        NPZC133001 api = new NPZC133001();
        api.execute(sendPoApiIfAzertyParam, S21ApiInterface.ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(sendPoApiIfAzertyParam)) {

            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(sendPoApiIfAzertyParam);

            for (String xxMsgId : xxMsgIdList) {

                S21InfoLogOutput.println(xxMsgId);

                if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {

                    msgMap.addXxMsgId(xxMsgId);

                    returnValue = false;

                }
            }
        }

        return returnValue;

    }

    /**
     * searchVndShipToXref
     * @param cusaCpoApiMap
     * @param apiParam
     * @return apiParam
     */
    private String searchCsaLocationCd(String dropShipFlg, Map<String, Object> cusaCpoApiMap, AWZC901001PMsg apiParam, S21ApiMessageMap msgMap, String str) {

        String csaLocCd = null;
        String cusaShipToCustCd = null;

        NPZC134001PMsg param = (NPZC134001PMsg) msgMap.getPmsg();

        if (ZYPConstant.FLG_OFF_N.equals(dropShipFlg)) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            ssmParam.put(BIND_VND_XREF_TP_CD, VND_XREF_TP_CD_1);
            ssmParam.put(BIND_EFF_FROM_DT, EFF_FROM_DATE);
            ssmParam.put(BIND_EFF_THRU_DT, EFF_THRU_DATE);
            ssmParam.put(BIND_SLS_DT, param.slsDt.getValue());

            // search 1
            ssmParam.put(BIND_INVTY_LOC_CD, str);
            ssmParam.put(BIND_PRCH_GRP_CD, cusaCpoApiMap.get(PRCH_GRP_CD));

            csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefInv", ssmParam);

            if (csaLocCd == null) {
                // search2
                ssmParam.put(BIND_INVTY_LOC_CD, str);
                ssmParam.put(BIND_PRCH_GRP_CD, STAR);

                csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefInv", ssmParam);

                if (csaLocCd == null) {
                    // search 3
                    ssmParam.put(BIND_RTL_WH_CD, cusaCpoApiMap.get(DEST_RTL_WH_CD));
                    ssmParam.put(BIND_PRCH_GRP_CD, cusaCpoApiMap.get(PRCH_GRP_CD));

                    csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);

                    if (csaLocCd == null) {
                        // search 4
                        ssmParam.put(BIND_RTL_WH_CD, cusaCpoApiMap.get(DEST_RTL_WH_CD));
                        ssmParam.put(BIND_PRCH_GRP_CD, STAR);

                        csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);
                    }

                }

            }

        } else if (ZYPConstant.FLG_ON_Y.equals(dropShipFlg)) {

            String dropShipWhCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_DROP_SHIP_WH_CD, param.glblCmpyCd.getValue());

            if (dropShipWhCd == null) {

                dropShipWhCd = DROP_SHIP_WH_CD_DS;

            }

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            ssmParam.put(BIND_VND_XREF_TP_CD, VND_XREF_TP_CD_1);
            ssmParam.put(BIND_EFF_FROM_DT, EFF_FROM_DATE);
            ssmParam.put(BIND_EFF_THRU_DT, EFF_THRU_DATE);
            ssmParam.put(BIND_SLS_DT, param.slsDt.getValue());

            // search1
            ssmParam.put(BIND_RTL_WH_CD, cusaCpoApiMap.get(DEST_RTL_WH_CD));
            ssmParam.put(BIND_PRCH_GRP_CD, cusaCpoApiMap.get(PRCH_GRP_CD));

            csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);

            if (csaLocCd == null) {

                // search 2
                ssmParam.put(BIND_RTL_WH_CD, cusaCpoApiMap.get(DEST_RTL_WH_CD));
                ssmParam.put(BIND_PRCH_GRP_CD, STAR);

                csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);

                if (csaLocCd == null) {

                    // search 3
                    ssmParam.put(BIND_RTL_WH_CD, dropShipWhCd);
                    ssmParam.put(BIND_PRCH_GRP_CD, cusaCpoApiMap.get(PRCH_GRP_CD));

                    csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);

                    if (csaLocCd == null) {
                        // search 4
                        ssmParam.put(BIND_RTL_WH_CD, dropShipWhCd);
                        ssmParam.put(BIND_PRCH_GRP_CD, STAR);

                        csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);

                    }

                }
            }

        }

        if (csaLocCd != null) {

            String cusaEdiTrdPtnrCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_EDI_TRD_PTNR_CD, param.glblCmpyCd.getValue());

            if (cusaEdiTrdPtnrCd != null) {

                StringTokenizer st = new StringTokenizer(cusaEdiTrdPtnrCd, ",");

                List<String> trdPtnrList = new ArrayList<String>();

                int i = 0;

                while (st.hasMoreTokens()) {

                    trdPtnrList.add(st.nextToken());

                    i++;
                }

                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(BIND_GLBL_CMPY_CD, cusaGlblCmpyCd);
                ssmParam.put(BIND_TRD_PTNR_LOC_CD, csaLocCd);
                ssmParam.put(BIND_EDI_TRD_PTNR_LIST, trdPtnrList);
                ssmParam.put(BIND_CRS_REF_ACTV_FLG, ZYPConstant.FLG_ON_Y);
                ssmParam.put(BIND_SLS_DT, param.slsDt.getValue());

                cusaShipToCustCd = (String) ssmBatchClient.queryObject("getCusaShipToCustCd", ssmParam);

            }

        }

        return cusaShipToCustCd;
    }

    /**
     * synchronizePoAck
     * @param PO_ACK_HDRtMsg
     * @param PO_ACK_DTLtmsgList
     * @return
     */
    private boolean synchronizePoAckHdr(BigDecimal pk, Map<String, Object> sendPoMap, S21ApiMessageMap msgMap) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        NPZC134001PMsg param = (NPZC134001PMsg) msgMap.getPmsg();

        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd);
        ssmParam.put(BIND_PO_ORD_NUM, sendPoMap.get(PO_ORD_NUM));
        ssmParam.put(BIND_PO_ACK_HDR_PK, pk);

        List<Map<String, Object>> updatePoAckHdrList = ssmBatchClient.queryObjectList("getPoAckHdrPkList", ssmParam);

        for (int i = 0; i < updatePoAckHdrList.size(); i++) {

            Map<String, Object> updateAckHdrPkMap = updatePoAckHdrList.get(i);

            BigDecimal updateAckHdrPk = (BigDecimal) updateAckHdrPkMap.get(PO_ACK_HDR_PK);

            if (updateAckHdrPk != pk) {

                PO_ACK_HDRTMsg inMsg = new PO_ACK_HDRTMsg();

                ZYPEZDItemValueSetter.setValue(inMsg.poAckHdrPk, pk);
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, param.glblCmpyCd);

                inMsg = (PO_ACK_HDRTMsg) S21ApiTBLAccessor.findByKey(inMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NPZM0279E);
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(inMsg.poAckHdrLtstFlg, ZYPConstant.FLG_OFF_N);

                S21ApiTBLAccessor.update(inMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NPZM0279E);
                    return false;
                }

            }

        }

        return true;
    }

    /**
     * synchronizePoAckDtl
     * @param poAckDtlPkList
     * @param sendPoMap
     * @param msgMap
     * @return
     */
    private boolean synchronizePoAckDtl(List<PO_ACK_DTLTMsg> poAckDtlTmsgList, List<BigDecimal> poAckDtlPkList, Map<String, Object> sendPoMap, S21ApiMessageMap msgMap) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        NPZC134001PMsg param = (NPZC134001PMsg) msgMap.getPmsg();

        for (int i = 0; i < poAckDtlTmsgList.size(); i++) {

            PO_ACK_DTLTMsg inMsg = poAckDtlTmsgList.get(i);

            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            ssmParam.put(BIND_PO_ORD_NUM, inMsg.poOrdNum.getValue());
            ssmParam.put(BIND_PO_ORD_DTL_LINE_NUM, inMsg.poOrdDtlLineNum.getValue());
            ssmParam.put(BIND_PO_ACK_DTL_PK_LIST, poAckDtlPkList);

            List<Map<String, Object>> updateTargetPoAckDtlPkList = ssmBatchClient.queryObjectList("getUpdateTargetPoAckDtlPk", ssmParam);

            if (updateTargetPoAckDtlPkList != null) {

                for (int j = 0; j < updateTargetPoAckDtlPkList.size(); j++) {

                    Map<String, Object> updateTargetPoAckDtlPk = updateTargetPoAckDtlPkList.get(j);

                    PO_ACK_DTLTMsg updateMsg = new PO_ACK_DTLTMsg();

                    ZYPEZDItemValueSetter.setValue(updateMsg.glblCmpyCd, param.glblCmpyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(updateMsg.poAckHdrPk, (BigDecimal) updateTargetPoAckDtlPk.get(PO_ACK_DTL_PK));

                    updateMsg = (PO_ACK_DTLTMsg) S21ApiTBLAccessor.findByKey(inMsg);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                        msgMap.addXxMsgId(NPZM0280E);
                        return false;
                    }

                    ZYPEZDItemValueSetter.setValue(updateMsg.poAckDtlLtstFlg, ZYPConstant.FLG_OFF_N);

                    S21ApiTBLAccessor.update(updateMsg);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                        msgMap.addXxMsgId(NPZM0280E);
                        return false;
                    }

                }

            }
        }

        return true;
    }

    /**
     * getPo
     * @param glblCmpyCd
     * @param poOrdNum
     * @return
     */
    protected static POTMsg getPo(String glblCmpyCd, String poOrdNum) {

        POTMsg inMsg = new POTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, poOrdNum);
        return (POTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    /**
     * getPoDtl
     * @param glblCmpyCd
     * @param poOrdNum
     * @param poOrdDtlLineNum
     * @return
     */
    protected static PO_DTLTMsg getPoDtl(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {
        PO_DTLTMsg inMsg = new PO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(inMsg.poOrdDtlLineNum, poOrdDtlLineNum);
        return (PO_DTLTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    /**
     * updatePoMsg
     * @param inMsg
     * @param poMsgTxt
     */
    protected static boolean updatePo(POTMsg inMsg, AWZC901001PMsg param, String processMode, NPZC134001PMsg poSendParam) {

        ZYPEZDItemValueSetter.setValue(inMsg.poSendFlg, ZYPConstant.FLG_ON_Y);

        if ((MODE_ADD_LINE.equals(processMode)) || (MODE_CANCEL_LINE.equals(processMode))) {

            ZYPEZDItemValueSetter.setValue(inMsg.vndIssOrdNum, param.cpoOrdNum);

        } else {

            if (MODE_CUSA_WS.equals(processMode) && ZYPConstant.FLG_ON_Y.equals(poSendParam.xxdrctCpoCratFlg.getValue())) {

                ZYPEZDItemValueSetter.setValue(inMsg.vndIssOrdNum, param.cpoOrdNum_OP);
            }
        }
        // QC#27628 add start
        // update carrAcctNum
        if (carrAcctNumFromXref) {
            ZYPEZDItemValueSetter.setValue(inMsg.carrAcctNum, param.xxLineList.no(0).carrAcctNum.getValue());
        }
        // QC#27628 add end

        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {

            return false;
        }

        return true;
    }

    /**
     * updateAdditionalPo
     * @param inMsg
     * @param poMsgTxt
     */
    protected static boolean updateAdditionalPo(POTMsg inMsg, AWZC901001PMsg param, String processMode, NPZC134001PMsg poSendParam) {

        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {

            return false;
        }

        return true;

    }

    /**
     * @param inMsg
     * @param poMsgTxt
     */
    protected boolean updatePoDtl(PO_DTLTMsg inMsg, AWZC901001PMsg param, String processMode, NPZC134001PMsg poSendParam) {

        if ((MODE_CUSA_PA.equals(processMode)) || (MODE_CUSA_AZ.equals(processMode))) {

            ZYPEZDItemValueSetter.setValue(inMsg.vndPoAckStsCd, VND_PO_ACK_STS_CD_IW);

        } else {

            if (ZYPConstant.FLG_OFF_N.equals(poSendParam.xxdrctCpoCratFlg.getValue())) {

                ZYPEZDItemValueSetter.setValue(inMsg.vndPoAckStsCd, VND_PO_ACK_STS_CD_IW);

            } else {

                ZYPEZDItemValueSetter.setValue(inMsg.vndPoAckStsCd, VND_PO_ACK_STS_CD_CW);

            }

        }

        // QC#27628 add start
        if (carrAcctNumFromXref) {
            // Freight Terms
            ZYPEZDItemValueSetter.setValue(inMsg.frtCondCd, FRT_COND.COLLECT);

            // Carrier
            ZYPEZDItemValueSetter.setValue(inMsg.carrCd, carrCdfromXref);
        }

        String defShpgSvcLvlCd = ZYPCodeDataUtil.getVarCharConstValue("SEND_PO_DEF_SHPG_SVC_LVL", poSendParam.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(defShpgSvcLvlCd)) {
            defShpgSvcLvlCd = SHPG_SVC_LVL.GROUND_SERVICE;
        }
        
        // QC#28731 mod start
        // SSL
        if (!ZYPCommonFunc.hasValue(inMsg.shpgSvcLvlCd)) {

            ZYPEZDItemValueSetter.setValue(inMsg.shpgSvcLvlCd, defShpgSvcLvlCd);

        } else {

            // check SSL combination.
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(BIND_GLBL_CMPY_CD, poSendParam.glblCmpyCd.getValue());
            String shpgSvcLvlCd = inMsg.shpgSvcLvlCd.getValue();

            if (!ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {
                shpgSvcLvlCd = STAR;
            }

            String carrCd = inMsg.carrCd.getValue();
            if (!ZYPCommonFunc.hasValue(carrCd)) {
                carrCd = STAR;
            }

            ssmParam.put(BIND_SHPG_SVC_LVL_CD, shpgSvcLvlCd);
            ssmParam.put(BIND_CARR_CD, carrCd);

            Map<String, Object> carrSvcLvlCd = (Map<String, Object>) ssmBatchClient.queryObject("getCarrSvcLvlCd", ssmParam);

            if (carrSvcLvlCd == null || carrSvcLvlCd.get(CARR_SVC_LVL_CD) == null) {
                ZYPEZDItemValueSetter.setValue(inMsg.shpgSvcLvlCd, defShpgSvcLvlCd);
            }
        }
        // QC#27628 add end
        // QC#28731 mod start
        
        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {

            return false;
        }

        return true;

    }

    /**
     * @param poInfoMap
     * @param msgMap
     * @return
     */
    private Map<String, Object> getTrdPtnrShpgCondCdInfo(Map poInfoMap, S21ApiMessageMap msgMap) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        NPZC134001PMsg param = (NPZC134001PMsg) msgMap.getPmsg();

        if (poInfoMap.get(DS_PO_TP_CD) != null && poInfoMap.get(SHPG_SVC_LVL_CD) != null) {

            ssmParam.clear();

            // search11
            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));
            ssmParam.put(BIND_SHPG_SVC_LCL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

            Map<String, Object> trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

            if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                ssmParam.clear();

                // search2
                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                    ssmParam.clear();

                    // search3
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_SHPG_SVC_LCL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        return trdPtnrShpgCondCd;

                    } else {

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        return trdPtnrShpgCondCd;

                    }
                } else {

                    ssmParam.clear();

                    // search3
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_SHPG_SVC_LCL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        return trdPtnrShpgCondCd;

                    } else {

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        return trdPtnrShpgCondCd;

                    }
                }
            } else {

                ssmParam.clear();

                // search2
                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                    ssmParam.clear();

                    // search3
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_SHPG_SVC_LCL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        return trdPtnrShpgCondCd;

                    } else {

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        return trdPtnrShpgCondCd;

                    }
                } else {

                    ssmParam.clear();

                    // search3
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_SHPG_SVC_LCL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        return trdPtnrShpgCondCd;

                    } else {

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        return trdPtnrShpgCondCd;

                    }

                }

            }

        } else {

            // seacrh2

            ssmParam.clear();

            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

            Map<String, Object> trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

            if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                ssmParam.clear();

                // search4
                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                return trdPtnrShpgCondCd;

            } else {

                ssmParam.clear();

                // search4
                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                return trdPtnrShpgCondCd;
            }
        }
    }


    /**
     * Set Message Whole Sales
     * @param msgMap S21ApiMessageMap
     * @param poInfoMap  Map<String, Object>
     * @param xxOrdMsgList AWZC901001_xxOrdMsgListPMsgArray
     */
    private void setMsgWs(S21ApiMessageMap msgMap, Map<String, Object> poInfoMap, AWZC901001_xxOrdMsgListPMsgArray xxOrdMsgList) {
        
        // 2017/12/11 T.Hakodate QC#21025 MOD START
        NPZC134001PMsg param = (NPZC134001PMsg) msgMap.getPmsg();

        StringBuilder sb = new StringBuilder();

        if (poInfoMap.get("CUST_ISS_PO_NUM") != null) {
            sb = sb.append((String) poInfoMap.get("CUST_ISS_PO_NUM"));
            sb = sb.append(" ");
        }

        String[] poMsgTpArray = new String[] {PO_MSG_TP.SHIPPER_NOTE, PO_MSG_TP.SPECIAL_INSTRUCTIONS };

        for (int i = 0; i < poMsgTpArray.length; i++) {

            List<PO_MSGTMsg> poMsgList = NPXC001001PoMsg.getPoMsg(param.glblCmpyCd.getValue(), poMsgTpArray[i], param.poOrdNum.getValue(), null);

            if (poMsgList != null && poMsgList.size() > 0) {

                String msg = NPXC001001PoMsg.concatenatePoMsg(poMsgList);

                sb = sb.append(msg);
                sb = sb.append(" ");
            }

        }

        if (sb == null) {
            return;
        }


//        List<Map<String, Object>> msgMapList = getMsgs(msgMap);
//        StringBuilder sb = new StringBuilder();
        // 2017/12/11 T.Hakodate QC#21025 MOD END
        
        // max digit
        int max = xxOrdMsgList.no(0).getAttr("msgTxtInfoTxt").getDigit();
        // 08/08/2017 CITS S.Endo Mod Sol#035(QC#18108) START
        int msgCnt = 0;
        // 2017/12/11 T.Hakodate QC#21025 MOD START
//        if ((msgMapList == null || msgMapList.size() == 0) && poInfoMap.get("CUST_ISS_PO_NUM") == null) {
//            return;
//        }
        // 2017/12/11 T.Hakodate QC#21025 MOD END

        // boolean msgAppendFlg = false;

//        // 2017/12/11 T.Hakodate QC#21025 ADD START
//        if (poInfoMap.get("CUST_ISS_PO_NUM") != null) {
//            sb = sb.append((String) poInfoMap.get("CUST_ISS_PO_NUM"));
//            sb = sb.append(" ");
//        }
//        // 2017/12/11 T.Hakodate QC#21025 ADD END
        
//        for (Map<String, Object> map : msgMapList) {
//            sb = sb.append((String) map.get(PO_MSG_TXT));
////            if (!msgAppendFlg) {
////                sb.append((String) map.get(PO_MSG_TXT));
////                msgAppendFlg = true;
////                continue;
////            } else {
////                sb.append(" ");
////                sb.append((String) map.get(PO_MSG_TXT));
////                msgAppendFlg = false;
////            }
////
////            if (sb.length() > max) {
////                sb.delete(max, sb.length());
////            }
////
////            if (msgCnt == 0) {
////                ZYPEZDItemValueSetter.setValue(xxOrdMsgList.no(msgCnt).msgTxtInfoTxt, sb.toString());
////                sb.setLength(0);
////                msgCnt++;
////            } else if (msgCnt == 1) {
////                ZYPEZDItemValueSetter.setValue(xxOrdMsgList.no(msgCnt).msgTxtInfoTxt, sb.toString());
////                sb.setLength(0);
////                msgCnt++;
////            } else if (msgCnt == 2) {
////                ZYPEZDItemValueSetter.setValue(xxOrdMsgList.no(msgCnt).msgTxtInfoTxt, sb.toString());
////                sb.setLength(0);
////                msgCnt++;
////            } else if (msgCnt == 3) {
////                ZYPEZDItemValueSetter.setValue(xxOrdMsgList.no(msgCnt).msgTxtInfoTxt, sb.toString());
////                sb.setLength(0);
////                msgCnt++;
////            }
//
//        }
        
        String concatStr = sb.toString();
        // QC#30232
        concatStr = concatStr.replaceAll("\\r\\n|\\r|\\n|\\t", " ");
        msgCnt = concatStr.length() / max;
        String sliceStr;
        int loopCount = msgCnt;
        if (msgCnt > 3) {
            loopCount = 3;
        }
        for (int i = 0; i < loopCount + 1; i++) {
            if (concatStr.length() < max) {
                ZYPEZDItemValueSetter.setValue(xxOrdMsgList.no(i).msgTxtInfoTxt, concatStr);
                break;
            }
            sliceStr = concatStr.substring(0, max);
            ZYPEZDItemValueSetter.setValue(xxOrdMsgList.no(i).msgTxtInfoTxt, sliceStr);
            concatStr = concatStr.substring(max);
        }


//        if (msgAppendFlg) {
//
//            if (sb.length() > max) {
//                sb.delete(max, sb.length());
//            }
//
//            if (msgCnt == 0) {
//                ZYPEZDItemValueSetter.setValue(xxOrdMsgList.no(msgCnt).msgTxtInfoTxt, sb.toString());
//                sb.setLength(0);
//                msgCnt++;
//            } else if (msgCnt == 1) {
//                ZYPEZDItemValueSetter.setValue(xxOrdMsgList.no(msgCnt).msgTxtInfoTxt, sb.toString());
//                sb.setLength(0);
//                msgCnt++;
//            } else if (msgCnt == 2) {
//                ZYPEZDItemValueSetter.setValue(xxOrdMsgList.no(msgCnt).msgTxtInfoTxt, sb.toString());
//                sb.setLength(0);
//                msgCnt++;
//            } else if (msgCnt == 3) {
//                ZYPEZDItemValueSetter.setValue(xxOrdMsgList.no(msgCnt).msgTxtInfoTxt, sb.toString());
//                sb.setLength(0);
//                msgCnt++;
//            }
//        }
        // setValidCount
        //xxOrdMsgList.setValidCount(msgCnt);
        xxOrdMsgList.setValidCount(loopCount + 1);
        //08/08/2017 CITS S.Endo Mod Sol#035(QC#18108) END
    }


    /**
     * Get Msgs
     * @param msgMap
     * @param onBatchType
     * @return
     */
    private List<Map<String, Object>> getMsgs(S21ApiMessageMap msgMap) {

        NPZC134001PMsg param = (NPZC134001PMsg) msgMap.getPmsg();

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        ssmParam.put(BIND_PO_ORD_NUM, param.poOrdNum.getValue());
        ssmParam.put(BIND_PO_MSG_TP_CD_SI, PO_MSG_TP.SPECIAL_INSTRUCTIONS);
        ssmParam.put(BIND_PO_MSG_TP_CD_SN, PO_MSG_TP.SHIPPER_NOTE);

        return ssmBatchClient.queryObjectList("getMsgs", ssmParam);
    }

    /**
     * 
     * @param glblCmpyCd
     * @param mdseCd
     * @return
     */
    private ORD_TAKE_MDSETMsg getOrdTakeMdse(String glblCmpyCd, String mdseCd) {

        ORD_TAKE_MDSETMsg inMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.ordTakeMdseCd, mdseCd);
        return (ORD_TAKE_MDSETMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }
    /**
     * 
     * @param glblCmpyCd
     * @param mdseCd
     * @return
     */
    private BigDecimal countCsaOrdTakeMdseV(String glblCmpyCd, String mdseCd) {

        BigDecimal count = BigDecimal.ZERO;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cusaGlblCmpyCd);
        ssmParam.put("ordTakeMdseCd", mdseCd);

        count = (BigDecimal) ssmBatchClient.queryObject("countCsaOrdTakeMdseV", ssmParam);
        return count;
    }
    /**
     * 
     * @param msgMap
     * @param csaSetMdseCd
     */
    private boolean isOrdTakeMdse (S21ApiMessageMap msgMap, String csaSetMdseCd ) {
        NPZC134001PMsg poHistParam = (NPZC134001PMsg) msgMap.getPmsg();
        String glblCmpyCd = poHistParam.glblCmpyCd.getValue();

        String ordTakeMdseCd = null;

        if (csaSetMdseCd.length() > TXT_LENGTH_8) {
            ordTakeMdseCd = csaSetMdseCd.substring(0, TXT_LENGTH_8);
        } else {
            ordTakeMdseCd = csaSetMdseCd;
        }

        ORD_TAKE_MDSETMsg ordTakeMdse = getOrdTakeMdse(glblCmpyCd ,ordTakeMdseCd);
        BigDecimal count = countCsaOrdTakeMdseV(glblCmpyCd ,ordTakeMdseCd);

        if (ordTakeMdse != null && count.intValue() > 0) {
            return true;
        }
        return false;
    }
    
    //QC#26074 Start
    private String searchVndLocationCd(String dropShipFlg, Map<String, Object> cusaCpoApiMap, AWZC901001PMsg apiParam, S21ApiMessageMap msgMap, String str) {

        String vndLocCd = null;

        NPZC134001PMsg param = (NPZC134001PMsg) msgMap.getPmsg();

        if (ZYPConstant.FLG_OFF_N.equals(dropShipFlg)) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            ssmParam.put(BIND_VND_XREF_TP_CD, VND_XREF_TP_CD_1);
            ssmParam.put(BIND_EFF_FROM_DT, EFF_FROM_DATE);
            ssmParam.put(BIND_EFF_THRU_DT, EFF_THRU_DATE);
            ssmParam.put(BIND_SLS_DT, param.slsDt.getValue());

            if (vndLocCd == null) {
                ssmParam.put(BIND_RTL_WH_CD, cusaCpoApiMap.get(DEST_RTL_WH_CD));
                ssmParam.put(BIND_PRCH_GRP_CD, cusaCpoApiMap.get(PRCH_GRP_CD));

                vndLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);

                if (vndLocCd == null) {
                    ssmParam.put(BIND_RTL_WH_CD, cusaCpoApiMap.get(DEST_RTL_WH_CD));
                    ssmParam.put(BIND_PRCH_GRP_CD, STAR);

                    vndLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);

                }
            }
        }
        return vndLocCd;
    }
    //QC#26074 End
    
    // QC#23356 Add Start
    /**
     * searchVndShipToXref
     * @param cusaCpoApiMap
     * @param apiParam
     * @return apiParam
     */
    private String getCarrAcctNum(Map<String, Object> cusaCpoApiMap, AWZC901001PMsg apiParam, S21ApiMessageMap msgMap, Map<String, Object> poInfoMap) {

        String carrAcctNum = null;
        String carrTpCd = null;
        String prchGrpCd = null;
        boolean cpoFlg = false;

        NPZC134001PMsg param = (NPZC134001PMsg) msgMap.getPmsg();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        Map<String, Object> cpoInfoMap = new HashMap<String, Object>();
        Map<String, Object> carrXrefMap = new HashMap<String, Object>();

        if (cusaCpoApiMap.get(CARR_ACCT_NUM) == null) {

            if (poInfoMap.get(CARR_CD) != null) {

                // Search VND
                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                ssmParam.put(BIND_CARR_CD, (String) poInfoMap.get(CARR_CD));

                // Get Carrier Type Code From VND
                carrTpCd = (String) ssmBatchClient.queryObject("getcarrTpCd", ssmParam);

                // Other -DS_ORD_CATG_CD / DS_ORD_TP_CD
                if (PO_ORD_SRC.SALES_ORDER.equals(poInfoMap.get(PO_ORD_SRC_CD))) {
                    ssmParam.clear();
                    // Search Order Category/Reason from CPO
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_CPO_ORD_NUM, (String) poInfoMap.get(CPO_ORD_NUM));

                    cpoInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getCpoInfo", ssmParam);

                    cpoFlg = true;

                }
                // Other -PRCH_GRP_CD
                if (poInfoMap.get(PRCH_GRP_CD) != null) {
                    prchGrpCd = (String) poInfoMap.get(PRCH_GRP_CD);
                }

                if (carrTpCd == null) {
                    return null;
                }

                // search 1
                ssmParam.clear();

                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                ssmParam.put(BIND_CARR_TP_CD, carrTpCd);

                if (carrAcctNum == null && cpoFlg) {
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));
                    ssmParam.put(BIND_PRCH_GRP_CD, prchGrpCd);
                    ssmParam.put(BIND_PO_ORD_SRC_CD, (String) cpoInfoMap.get(PO_ORD_SRC_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }

                if (carrAcctNum == null && cpoFlg) {
                    // search 2
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 3
                    ssmParam.put(BIND_DS_ORD_CATG_CD, STAR);
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 4
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 5
                    ssmParam.put(BIND_PRCH_GRP_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 6
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 7
                    ssmParam.put(BIND_DS_ORD_CATG_CD, STAR);
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 8
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }

                if (carrAcctNum == null) {
                    // search 9
                    ssmParam.put(BIND_PO_ORD_SRC_CD, STAR);
                    ssmParam.put(BIND_PRCH_GRP_CD, prchGrpCd);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null) {
                    // search 10
                    ssmParam.put(BIND_PRCH_GRP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }

                }

            } else {

                // Other -DS_ORD_CATG_CD / DS_ORD_TP_CD
                if (PO_ORD_SRC.SALES_ORDER.equals(poInfoMap.get(PO_ORD_SRC_CD))) {
                    // Search Order Category/Reason from CPO
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_CPO_ORD_NUM, (String) poInfoMap.get(CPO_ORD_NUM));

                    cpoInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getCpoInfo", ssmParam);

                    cpoFlg = true;
                }
                // Other -PRCH_GRP_CD
                if (poInfoMap.get(PRCH_GRP_CD) != null) {
                    prchGrpCd = (String) poInfoMap.get(PRCH_GRP_CD);
                }

                // Search CARR_TP_CD from TRD_PTNR_SHPG_X_REF
                Map<String, Object> trdPtnrShpgXRefMap = new HashMap<String, Object>();

                trdPtnrShpgXRefMap = getTrdPtnrShpgXRefInfo(poInfoMap, msgMap);

                if (trdPtnrShpgXRefMap != null) {
                    carrTpCd = (String) trdPtnrShpgXRefMap.get(CARR_TP_CD);
                }

                if (carrTpCd == null) {
                    return null;
                }

                // search 1
                ssmParam.clear();

                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                ssmParam.put(BIND_CARR_TP_CD, carrTpCd);

                if (carrAcctNum == null && cpoFlg) {
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));
                    ssmParam.put(BIND_PRCH_GRP_CD, prchGrpCd);
                    ssmParam.put(BIND_PO_ORD_SRC_CD, (String) cpoInfoMap.get(PO_ORD_SRC_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 2
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 3
                    ssmParam.put(BIND_DS_ORD_CATG_CD, STAR);
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 4
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 5
                    ssmParam.put(BIND_PRCH_GRP_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 6
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 7
                    ssmParam.put(BIND_DS_ORD_CATG_CD, STAR);
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 8
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }

                }
                if (carrAcctNum == null) {
                    // search 9
                    ssmParam.put(BIND_PO_ORD_SRC_CD, STAR);
                    ssmParam.put(BIND_PRCH_GRP_CD, prchGrpCd);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null) {
                    // search 10
                    ssmParam.put(BIND_PRCH_GRP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
            }

        } else {
            carrAcctNum = (String) cusaCpoApiMap.get(CARR_ACCT_NUM);
        }
        return carrAcctNum;
    }
    // QC#23356 Add End

    // START 2019/12/04 M.Naito [QC#54563-1,ADD]
    private static void convertCharacterForPoInfo(Map<String, Object> poInfoMap) {
        if (ZYPCommonFunc.hasValue((String) poInfoMap.get(CTAC_PSN_NM))) {
            poInfoMap.put(CTAC_PSN_NM, ZYPCharacterConversionUtil.convertCharacter((String) poInfoMap.get(CTAC_PSN_NM)));
        }
    }
    // END 2019/12/04 M.Naito [QC#54563-1,ADD]

}
