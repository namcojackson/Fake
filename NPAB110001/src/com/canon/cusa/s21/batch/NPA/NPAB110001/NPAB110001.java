package com.canon.cusa.s21.batch.NPA.NPAB110001;

import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BAT_ERR_MSG_TXT_LEN;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_ASL_END_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_ASL_START_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_ASN_SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_CARR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_EDI_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_OPEN_PO_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_ORIG_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_ORIG_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_PO_RCV_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_PO_RCV_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_PRCH_REQ_LINE_TP_CD_INSOURCED_PO;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_PRCH_REQ_REC_TP_CD_TECH;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_PRNT_CMPY_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_PROC_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_RWS_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_RWS_REF_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_RWS_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_SCE_ORD_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_SHPG_SVC_LCL_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_SLS_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_SPLY_ITEM_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_TRD_PTNR_CARR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BIND_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.BOL_NUM_LEN;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.CAL_SPLIT_FLAG;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.CARET;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.CRLF;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.CSA_EDI_NUM_LEN;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DEBUG_MESSAGE_CODE;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DOLLAR;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_ASL_DTL_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_ASL_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_ASL_UNIT_PRC_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_CTAC_PSN_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_DEST_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_DISP_PO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_ENT_DEAL_NET_UNIT_PRC_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_ENT_FUNC_NET_UNIT_PRC_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_ENT_PO_DTL_DEAL_NET_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_ENT_PO_DTL_FUNC_NET_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_EXCH_RATE;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_FROM_STK_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_FRT_COND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_MDSE_DESC_SHORT_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_ORIG_DISP_PO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_ORIG_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_ORIG_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_ORIG_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_PO_DISP_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_PO_DISP_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_PO_INV_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_PO_LINE_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_PO_MATCH_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_PO_MDSE_CMPSN_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_PO_SEND_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_PRCH_GRP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_PRCH_REQ_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_PRCH_REQ_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_PRCH_REQ_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_PRO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_RQST_RCV_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_RQST_RCV_TM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_RQST_SHIP_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_SET_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_SHIP_FROM_SO_NUM_LIST_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_SHIP_TO_ACCT_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_SRC_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_SVC_CONFIG_MSTR_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_TO_STK_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_TRX_REF_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_TRX_REF_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_TRX_REF_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_VND_INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_VND_ISS_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DPD_VND_PO_ACK_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_CARR_ACCT_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_DEST_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_DS_PO_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_DS_PO_TP_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_EIP_RPT_RQST_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_PO_APVL_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_PO_QLFY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_PO_SEND_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_PRNT_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_PRNT_VND_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_RQST_RCV_TM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_RQST_SHIP_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_RQST_TECH_TOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_RTRN_SHIP_TO_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_SEND_PO_EML_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_SEND_PO_FAX_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_SHIP_FROM_SO_NUM_LIST_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_SRC_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_TRSMT_METH_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_VND_PMT_TERM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.DP_VND_PMT_TERM_DESC_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.D_ASN_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.D_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.D_MDSE_CD_UPD_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.EDI_PO_ORD_DTL_LINE_NUM_LEN;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.EDI_PO_ORD_NUM_LEN;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.EDI_RCV_TS_LEN;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.ED_ASN_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.ED_ASN_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.ED_ASN_SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.ED_EDI_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.ED_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.ED_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.ED_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.EH_ASN_SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.EH_EDI_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.EH_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.EH_SHIP_FROM_SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.ES_ASN_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.ES_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.ES_SER_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.ES_SO_SER_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.FIELD_RWS_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.FLG_Y;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.HYPHEN;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.INDENT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.INV_NUM_LEN;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.LF_PAD_CHAR;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MAIL_CHARSET;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MAIL_KEY_FROM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MAIL_MSG_MAP_KEY_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MAIL_MSG_MAP_KEY_MSG_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MAIL_MSG_MAP_KEY_PO;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MAIL_MSG_MAP_KEY_SO;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MAIL_MSG_MAP_KEY_STS;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MAIL_MSG_MAP_KEY_VND;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MAIL_TMPL_ID_TO_ADMIN;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MAIL_TMPL_ID_TO_ORDER;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MANDATORY_DTL_ITEMS;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MANDATORY_HDR_ITEMS;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MANDATORY_SER_ITEMS;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MDSE_CD_LEN;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NLZM1006E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NLZM1011E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NLZM2180E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NLZM2181E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NLZM2182E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NLZM2183E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM0063E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM0064E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1003E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1169E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1171E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1173E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1176E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1178E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1200E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1201E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1265E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1285E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1286E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1288E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1290E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1291E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1298E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1320E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1321E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1496E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1507W;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1508W;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1575I;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1637E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPAM1641E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPZM0080E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.MSG_ID_NPZM0081E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.NPAM1364E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.NPZC1030_EVENT_ASN;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.NPZC1030_MODE_UPDATE;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.NPZM0156E;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.P1_PREFIX;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.P2_PREFIX;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_ADMIN_PSN_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_BILL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_CARR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_CCY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_CPO_DTL_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_CPO_ORD_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_CUST_ISS_PO_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_CUST_ISS_PO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_CUST_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_FRT_CHRG_METH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_FRT_CHRG_TO_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_ORD_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_PO_BAL_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_PO_ORD_DTL_CMNT_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_PO_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_PO_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_PO_LINE_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SELL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHIP_TO_ADDL_LOC_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHIP_TO_CNTY_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHIP_TO_CTRY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHIP_TO_CTY_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHIP_TO_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHIP_TO_FIRST_REF_CMNT_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHIP_TO_FRTH_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHIP_TO_LOC_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHIP_TO_POST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHIP_TO_PROV_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHIP_TO_SCD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHIP_TO_SCD_REF_CMNT_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHIP_TO_ST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHIP_TO_THIRD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHPG_PLN_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_SHPG_SVC_LVL_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PD_THIS_MTH_FOB_COST_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PM_PO_MSG_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PM_PO_MSG_SUBMT_PSN_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PM_PO_MSG_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PM_PO_MSG_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PM_PRCH_REQ_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PM_PRCH_REQ_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PM_PRCH_REQ_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_ORD_DTL_LINE_NUM_LEN;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_ORD_NUM_LEN;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_PO_APVL_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_PO_APVL_PSN_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_PO_APVL_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_PO_ORD_CMNT_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_PO_ORD_SRC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_PO_PRINT_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_PO_SEND_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_PO_SUBMT_PSN_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_PO_SUBMT_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_RCV_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_VND_DROP_SHIP_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_VND_ISS_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PO_VND_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PRCH_REQ_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PRCH_REQ_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.PRCH_REQ_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.RD_RWS_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.RWS_BAL_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.RWS_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.RWS_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.RWS_REF_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.RWS_REF_NUM_LEN;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.RWS_STK_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.SCE_ORD_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.SEARCH_ALL;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.SHIP_FROM_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.SQL_COND_KEY_GLBL_CMPY_CD_01;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.SQL_COND_KEY_INBD_LTST_REC_FLG_01;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.SQL_COND_KEY_PO_RCV_NUM_01;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.SQL_ID_011;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.TRX_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.TRX_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.TS_FORMAT_FOR_MAIL;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.TS_FORMAT_YYYYMMDD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.TS_FORMAT_YYYYMMDDHHMMSS;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.TS_FORMAT_YYYYMMDDHHMMSS_SSS;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.TS_LENGTH_YYYYMMDD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.TS_LENGTH_YYYYMMDDHHMMSS;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.TS_LENGTH_YYYYMMDDHHMMSS_SSS;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.VAR_CUSA_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.VAR_DROP_SHIP_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.VND_DROP_SHIP_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.XR_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant.XR_PO_ORD_NUM;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import parts.common.EZDDebugOutput;
import parts.common.EZDItemAttrDefines;
import parts.common.EZDItemAttribute;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.EDI_ASN_DTL_WRKTMsg;
import business.db.EDI_ASN_ERR_WRKTMsg;
import business.db.EDI_ASN_ERR_WRKTMsgArray;
import business.db.EDI_ASN_HDR_WRKTMsg;
import business.db.EDI_ASN_SER_NUM_WRKTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.INBD_VISTMsg;
import business.db.INBD_VISTMsgArray;
import business.db.MDSE_SER_NUM_RNGTMsg;
import business.db.MDSE_SER_NUM_RNGTMsgArray;
import business.db.POTMsg;
import business.db.PO_DTLTMsg;
import business.db.PO_RCV_DTLTMsg;
import business.db.PO_RCV_HDRTMsg;
import business.db.RCV_ASN_VNDTMsg;
import business.db.RWS_DTLTMsg;
import business.db.RWS_DTLTMsgArray;
import business.db.RWS_HDRTMsg;
import business.db.RWS_HDRTMsgArray;
import business.db.SHPG_ORD_PRO_NUM_LISTTMsg;
import business.db.WH_SCHDTMsg;
import business.db.WH_SCHDTMsgArray;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC201001PMsg;
import business.parts.NLZC210001PMsg;
import business.parts.NLZC210002PMsg;
import business.parts.NLZC304001PMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC104001PMsg;
import business.parts.NPZC106001PMsg;
import business.parts.NPZC109001PMsg;
import business.parts.NPZC109001_detailListPMsg;
import business.parts.NPZC110001PMsg;
import business.parts.NPZC131001PMsg;
import business.parts.NSZC043001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC200001.NLZC200001;
import com.canon.cusa.s21.api.NLZ.NLZC201001.NLZC201001;
import com.canon.cusa.s21.api.NLZ.NLZC210001.NLZC210001;
import com.canon.cusa.s21.api.NLZ.NLZC304001.NLZC304001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC104001.NPZC104001;
import com.canon.cusa.s21.api.NPZ.NPZC104001.constant.NPZC104001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC106001.NPZC106001;
import com.canon.cusa.s21.api.NPZ.NPZC109001.NPZC109001;
import com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC110001.NPZC110001;
import com.canon.cusa.s21.api.NPZ.NPZC131001.NPZC131001;
import com.canon.cusa.s21.api.NPZ.NPZC131001.constant.NPZC131001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.batch.NPA.NPAB110001.constant.NPAB110001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASN_EDI_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CARR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DRCT_SHIP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PUT_AWAY_CHK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeData;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
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
 * NPAB110001:Receiving ASN
 * 
 * Date         Company      Name             Create/Update    Defect No
 * ---------------------------------------------------------------------
 * 2016/03/07   CITS         T.Hakodate       ALL UPDATE       CSA NA
 * 2016/04/25   CITS         T.Hakodate       UPDATE           QC#7525
 * 07/18/2016   CSAI         Y.Imazu          Update           QC#6663
 * 2016/08/12   CITS         M.Okigami        UPDATE           PO to WMS without ASN
 * 2016/11/09   CITS         R.Shimamoto      Update           QC#15121
 * 2017/02/24   CITS         R.Shimamoto      Update           QC#17049
 * 2017/03/08   CITS         S.Endo           Update           QC#17627
 * 2017/03/08   CITS         S.Endo           Update           QC#17900
 * 2017/08/15   CITS         K.Ogino          Update           QC#20500
 * 2017/08/28   CITS         K.Ogino          Update           QC#20610
 * 2017/09/04   CITS         K.Ogino          Update           QC#20884
 * 2017/11/21   CITS         K.Ogino          Update           QC#18631
 * 2018/03/05   CITS         K.Ogino          Update           QC#24053
 * 2018/03/13   CITS         T.Hakodate       Update           QC#24777
 * 03/15/2018   CITS         K.Ogino          Update           QC#24780
 * 03/16/2018   CITS         T.hakodate       Update           QC#24777
 * 03/23/2018   CITS         S.Katsuma        Update           QC#24353
 * 04/02/2018   CITS         K.Ogino          Update           QC#25146
 * 06/19/2018   CITS         K.Ogino          Update           QC#26730
 * 09/13/2018   CITS         K.Ogino          Update           QC#26964/QC#26965(TST Impreso / Dietzgen PO EDI)
 * 11/14/2018   CITS         M.Naito          Update           QC#29048
 * 11/16/2018   CITS         T.Tokutomi       Update           QC#28968
 * 02/19/2019   Fujitsu      T.Ogura          Update           QC#30312
 * 02/21/2019   CITS         M.Naito          Update           QC#30273
 * 03/13/2019   CITS         K.Ogino          Update           QC#30696
 * 03/26/2019   CITS         K.Ogino          Update           QC#30857
 * 07/24/2019   CITS         M.Naito          Update           QC#51917
 * 08/08/2019   CITS         K.Ogino          Update           QC#52401
 * 09/12/2019   Fujitsu      T.Ogura          Update           QC#53424
 * 09/20/2019   CITS         T.Wada           Update           QC#53127
 * 10/17/2019   CITS         K.Ogino          Update           QC#54157
 * 10/25/2019   CITS         K.Ogino          Update           QC#52799
 * 11/04/2019   CITS         K.Ogino          Update           QC#54373
 * 04/21/2020   Fujitsu      T.Ogura          Update           QC#56545
 * 04/27/2020   Fujitsu      T.Ogura          Update           QC#56546
 * 06/04/2020   Fujitsu      T.Ogura          Update           QC#56912
 * 2021/04/16   CITS         K.Ogino          Update           QC#58256
 * 2023/03/10   Hitachi      TZ.Win           Update           QC#60960
 * 2023/03/27   Hitachi      TZ.Win           Update           QC#61269
 * 2023/06/26   Hitachi      TZ.Win           Update           QC#61614
 * 2023/07/20   Hitachi      S.Dong           Update           QC#61638
 * 2023/10/25   CITS         F.Komaki         Update           QC#61968
 * <pre>
 */

public class NPAB110001 extends S21BatchMain {

    // -- Input parameters ----------------------
    /** Global Company Code */
    private String globalCompanyCode = null;

    /** CUSA Global Company Code */
    private String cusaGlobalCompanyCode = null;

    /** dropShipWhCd DS */
    private String dropShipWhCd = null;

    // -- Other Internal Variable ---------------
    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Sales Date */
    private String salesDate = null;

    /** interfaceId */
    private String interfaceId = null;

    // -- Result of count ---------------
    /** total correct count */
    private int totalCorrectCount = 0;

    /** total Execute count */
    private int totalExecuteCount = 0;

    /** total error count */
    private int totalErrorCount = 0;

    /** Termination code */
    private TERM_CD termCd = null;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SQL access parts ssmLlcClient */
    private S21SsmLowLevelCodingClient ssmLlcClient = null;

    /** S21MailTemplate . */
    private S21MailTemplate template = null;

    /** ErrorList */
    private List<EDI_ASN_ERR_WRKTMsg> errorList = new ArrayList<EDI_ASN_ERR_WRKTMsg>();

    /** MailMessage Map <PO#, <SO#, rcvDate, vndCd, MsgList>> */
    private Map<String, Map<String, Object>> mailMsgMap = new TreeMap<String, Map<String, Object>>();

    /** MailMessage for Admin */
    private StringBuilder mailMessageForAdmin = new StringBuilder();

    // START 2023/06/26 TZ.Win [QC#61614, ADD]
    /** Warning flag for LINE STATUS: Closed/Open for Invoice */
    private boolean isProcessedwithWarning = false;

    /** Flag for PO STATUS Closed */
    private boolean isHeaderClosed = false;
    // END 2023/06/26 TZ.Win [QC#61614, ADD]

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NPAB110001().executeBatch(NPAB110001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // set termCd nomal
        termCd = TERM_CD.NORMAL_END;

        // Initialization
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        // ssmBatchClient
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Get the Global Company Code.
        globalCompanyCode = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(globalCompanyCode)) {
            throw new S21AbendException(MSG_ID_NPAM1173E, new String[] {"Global Company Code" });
        }

        // Get salesDate.
        salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(MSG_ID_NPAM1173E, new String[] {"Sales Date" });
        }

        // Get String interfaceId = getInterfaceID();
        interfaceId = getInterfaceID();

        // Get mail template ID
        template = new S21MailTemplate(this.globalCompanyCode, MAIL_TMPL_ID_TO_ORDER);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(MSG_ID_NPAM0064E, new String[] {MAIL_TMPL_ID_TO_ORDER });
        }
    }

    protected void mainRoutine() {

        this.cusaGlobalCompanyCode = ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_GLBL_CMPY_CD, this.globalCompanyCode);
        this.dropShipWhCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_DROP_SHIP_WH_CD, this.globalCompanyCode);

        if (!ZYPCommonFunc.hasValue(this.cusaGlobalCompanyCode)) {

            throw new S21AbendException(MSG_ID_NPAM1496E);

        }

        this.ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        processAsn();
    }

    @Override
    protected void termRoutine() {

        // insert Error
        if (!errorList.isEmpty()) {
            insertEdiAsnErrWrk();
        }

        // Send Mail
        if (!mailMsgMap.isEmpty()) {
            sendEMailToUser();
            termCd = TERM_CD.WARNING_END;
        }

        if (mailMessageForAdmin.length() > 0) {
            sendEMailToAdmin();
            termCd = TERM_CD.WARNING_END;
        }

        setTermState(this.termCd, this.totalCorrectCount, this.totalErrorCount, this.totalExecuteCount);
    }

    /**
     * processAsn
     */
    private void processAsn() {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            // get EDI ASN Header and SO#
            // **********************************************************

            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
            paramMap.put(BIND_EDI_PO_ORD_DTL_LINE_NUM, SEARCH_ALL);
            paramMap.put(BIND_PROC_FLG, ZYPConstant.FLG_OFF_0);

            if (ZYPCommonFunc.hasValue(interfaceId)) {
                paramMap.put("itrlIntfcId", interfaceId);
            }

            preparedStatement = ssmLlcClient.createPreparedStatement("getEdiAsnHeader", paramMap);
            resultSet = preparedStatement.executeQuery();

            // loop SO# START
            while (resultSet.next()) {

                boolean isError = false;
                // QC#26964/QC#26965
                BigDecimal cmbnSoCnt = resultSet.getBigDecimal("CMBN_SO_CNT"); // 0 < 1:Dietzgen ASN
                BigDecimal soOnlyCnt = resultSet.getBigDecimal("SO_ONLY_CNT"); // 0 < 1:TST Impreso or Dietzgen ASN

                // get EDI ASN Header
                // ****************************************************************
                EDI_ASN_HDR_WRKTMsg asnHdrTMsg = new EDI_ASN_HDR_WRKTMsg();

                ZYPEZDItemValueSetter.setValue(asnHdrTMsg.glblCmpyCd, resultSet.getString(EH_GLBL_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(asnHdrTMsg.asnSoNum, resultSet.getString(EH_ASN_SO_NUM));

                asnHdrTMsg = (EDI_ASN_HDR_WRKTMsg) EZDTBLAccessor.findByKey(asnHdrTMsg);

                totalExecuteCount++;

                if (asnHdrTMsg == null) {
                    addMailMessageToAdmin(resultSet.getString(EH_ASN_SO_NUM), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1288E, new String[] {"EDI_ASN_HDR_WRK", "ASN_SO_NUM", resultSet.getString(EH_ASN_SO_NUM) }));
                    totalErrorCount++;
                    continue;
                }
                
                // START 2023/03/13 TZ.Win [QC#60960, ADD]
                // START 2023/06/26 TZ.Win [QC#61614, MOD]
                // boolean isProcessedwithWarning = false;
                isProcessedwithWarning = false;
                isHeaderClosed = false;
                // END 2023/06/26 TZ.Win [QC#61614, MOD]
                POTMsg poTMsg = new POTMsg();

                ZYPEZDItemValueSetter.setValue(poTMsg.glblCmpyCd, resultSet.getString(EH_GLBL_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, asnHdrTMsg.ediPoOrdNum);

                poTMsg = (POTMsg) EZDTBLAccessor.findByKey(poTMsg);

                ZYPEZDItemValueSetter.setValue(asnHdrTMsg.poOrdNum, getResultSetValueForPO(resultSet, PO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(asnHdrTMsg.vndCd, getResultSetValueForPO(resultSet, VND_CD));
                if (ZYPConstant.FLG_ON_Y.equals(getResultSetValueForPO(resultSet, VND_DROP_SHIP_FLG))) {
                    ZYPEZDItemValueSetter.setValue(asnHdrTMsg.brFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(asnHdrTMsg.brFlg, ZYPConstant.FLG_OFF_N);
                }

                // START 2023/06/26 TZ.Win [QC#61614, MOD]
                // if ((poTMsg == null) || (BigDecimal.ZERO.compareTo(getPoDtlAsnDtlCount(asnHdrTMsg)) < 0)) {  
                if (poTMsg == null) {
                // END 2023/06/26 TZ.Win [QC#61614, MOD]

                    isProcessedwithWarning=true;
                    updateEdiAsnHdrWrkWithProcessedWarning(asnHdrTMsg, isProcessedwithWarning);
                    commit();
                    totalCorrectCount++;
                    continue;
                }
                // END 2023/03/13 TZ.Win [QC#60960, ADD]

                // delete Edi Asn Error Work
                // ****************************************************************
                deleteEdiAsnErrWrk(asnHdrTMsg);
                commit();

                // QC#26964/QC#26965
                if (BigDecimal.ZERO.compareTo(soOnlyCnt) < 0) {
                    // TST Impreso / Dietzgen SO Only Process
                    ZYPEZDItemValueSetter.setValue(asnHdrTMsg.poOrdNum, getResultSetValueForPO(resultSet, PO_ORD_NUM));
                    ZYPEZDItemValueSetter.setValue(asnHdrTMsg.vndCd, getResultSetValueForPO(resultSet, VND_CD));

                    if (ZYPConstant.FLG_ON_Y.equals(getResultSetValueForPO(resultSet, VND_DROP_SHIP_FLG))) {
                        ZYPEZDItemValueSetter.setValue(asnHdrTMsg.brFlg, ZYPConstant.FLG_ON_Y);
                    } else {
                        ZYPEZDItemValueSetter.setValue(asnHdrTMsg.brFlg, ZYPConstant.FLG_OFF_N);
                    }

                    List<EDI_ASN_DTL_WRKTMsg> asnDtlList = new ArrayList<EDI_ASN_DTL_WRKTMsg>();
                    List<Map<String, String>> cmbnDtlList = new ArrayList<Map<String, String>>();

                    if (getEdiDetailList(asnHdrTMsg, asnDtlList, cmbnDtlList) || asnDtlList.isEmpty()) {
                        addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1288E, new String[] {"EDI_ASN_DTL_WRK", "ASN_SO_NUM", asnHdrTMsg.asnSoNum.getValue() }));
                        isError = true;
                    }

                    if (!isError) {
                        if (!cmbnSoShipProcess(asnHdrTMsg, asnDtlList, false)) {
                            isError = true;
                        }
                    }
                // check PO. Normal ASN or Dietzgen Combine SO Process.
                // ****************************************************************
                } else if (checkAsnHdr(resultSet, asnHdrTMsg)) {

                    isError = true;

                } else {

                    // set PO Order Number , Vendor Code , Branch Flag
                    // ****************************************************************
                    ZYPEZDItemValueSetter.setValue(asnHdrTMsg.poOrdNum, getResultSetValueForPO(resultSet, PO_ORD_NUM));
                    ZYPEZDItemValueSetter.setValue(asnHdrTMsg.vndCd, getResultSetValueForPO(resultSet, VND_CD));

                    if (ZYPConstant.FLG_ON_Y.equals(getResultSetValueForPO(resultSet, VND_DROP_SHIP_FLG))) {
                        ZYPEZDItemValueSetter.setValue(asnHdrTMsg.brFlg, ZYPConstant.FLG_ON_Y);
                    } else {
                        ZYPEZDItemValueSetter.setValue(asnHdrTMsg.brFlg, ZYPConstant.FLG_OFF_N);
                    }

                    // get EDI ASN Detail
                    // ****************************************************************
                    List<EDI_ASN_DTL_WRKTMsg> asnDtlList = new ArrayList<EDI_ASN_DTL_WRKTMsg>();
                    // QC#26964/QC#26965
                    List<Map<String, String>> cmbnDtlList = new ArrayList<Map<String, String>>();
                    // QC#26964/QC#26965
                    if (getEdiDetailList(asnHdrTMsg, asnDtlList, cmbnDtlList) || asnDtlList.isEmpty()) {
                        addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1288E, new String[] {"EDI_ASN_DTL_WRK", "ASN_SO_NUM", asnHdrTMsg.asnSoNum.getValue() }));
                        isError = true;
                    }

                    // get EDI ASN Serial Number Work
                    // ****************************************************************
                    List<EDI_ASN_SER_NUM_WRKTMsg> asnSerList = new ArrayList<EDI_ASN_SER_NUM_WRKTMsg>();

                    getEdiAsnSerNumList(asnHdrTMsg, asnSerList);

                    // check ASN Detail and Serial Items
                    // ****************************************************************
                    if (!isError && checkAsnDtlSerItem(asnHdrTMsg, asnDtlList, asnSerList)) {
                        isError = true;
                    }

                    // update Mdse Code. Update QC#26964/QC#26965
                    // ****************************************************************
                    if (!isError && updateMdseCd(asnHdrTMsg, asnDtlList, asnSerList, cmbnDtlList)) {
                        isError = true;
                    }

                    // no check error
                    // ****************************************************************
                    // START 2023/06/26 TZ.Win [QC#61614, MOD]
                    if (!isError && !isProcessedwithWarning) {
                    // END 2023/06/26 TZ.Win [QC#61614, MOD]

                        // commit();

                        ZYPEZDItemValueSetter.setValue(asnHdrTMsg.asnEdiProcStsCd, ASN_EDI_PROC_STS.PROCESSED);

                        boolean dropShipFlag = checkDropShip(asnHdrTMsg);

                        if (dropShipFlag) {

                            // CASE2 Direct to Customer. Update QC#26964/QC#26965
                            // ****************************************************************
                            if (!isError && callApiToCustomer(asnHdrTMsg, asnDtlList, asnSerList, cmbnDtlList)) {
                                isError = true;
                            }

                            // QC#26964/QC#26965. Dietzgen Combine SO Process.
                            if (!isError && BigDecimal.ZERO.compareTo(cmbnSoCnt) < 0) {
                                if (!cmbnSoShipProcess(asnHdrTMsg, asnDtlList, true)) {
                                    isError = true;
                                }
                            }

                        } else {

                            // split SHPG_PLN, PO_DTL
                            // ****************************************************************
                            List<Map<String, Object>> asnDtlMapList = new ArrayList<Map<String, Object>>();
                            NPZC104001PMsg npzc104001Pmsg = new NPZC104001PMsg();

                            if (!isError && splitPoDtl(asnHdrTMsg, asnDtlList, asnSerList, npzc104001Pmsg)) {

                                isError = true;
                            }

                            if (!isError && splitSetPoDtl(asnHdrTMsg, asnDtlList, asnSerList, npzc104001Pmsg)) {

                                isError = true;
                            }

                            // call API
                            // ****************************************************************
                            if (!isError && !callAPI(resultSet, asnHdrTMsg, asnDtlList, asnDtlMapList, npzc104001Pmsg)) {

                                isError = true;
                            }
                        }
                    }
                }
                // transaction
                // ****************************************************************
                // START 2023/06/26 TZ.Win [QC#61614, ADD]
                if(!isError && isProcessedwithWarning){
                    updateEdiAsnHdrWrkWithProcessedWarning(asnHdrTMsg, isProcessedwithWarning);
                    commit();
                    totalCorrectCount++;
                    continue;
                }
                // END 2023/06/26 TZ.Win [QC#61614, ADD]

                if (isError) {
                    rollback();
                    totalErrorCount++;
                    updateEdiAsnHdrWrk(asnHdrTMsg, isError);
                    commit();

                } else {
                    updateEdiAsnHdrWrk(asnHdrTMsg, isError);
                    commit();
                    totalCorrectCount++;

                }
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    /**
     * callApiToCustomer. Update QC#26964/QC#26965
     * @param asnHdrTMsg EDI_ASN_HDR_WRKTMsg
     * @param asnDtlList List<EDI_ASN_DTL_WRKTMsg>
     * @param asnSerList List<EDI_ASN_SER_NUM_WRKTMsg>
     * @param cmbnDtlList List<Map<String, String>> 
     * @return boolean
     */
    private boolean callApiToCustomer(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, List<EDI_ASN_DTL_WRKTMsg> asnDtlList, List<EDI_ASN_SER_NUM_WRKTMsg> asnSerList, List<Map<String, String>> cmbnDtlList) {

        boolean isError = false;
        String carrCd = null;
        int cnt = 0;

        EDI_ASN_DTL_WRKTMsg asnDtlTMsg = asnDtlList.get(cnt);

        if (ZYPCommonFunc.hasValue((cmbnDtlList.get(cnt).get(asnDtlTMsg.asnSoSlpNum.getValue())))) {
            // Skip Combine SO ASN
            return false;
        }

        List<String> carrCdFromXrefList = getCarrCdFromXref(asnHdrTMsg, asnDtlTMsg, asnHdrTMsg.brFlg.getValue());
        // QC#52401 Mod
        if (carrCdFromXrefList.size() >= 1) {
            for (String retCarrCd : carrCdFromXrefList) {
                if (ZYPCommonFunc.hasValue(retCarrCd) && !"*".equals(retCarrCd)) {
                    carrCd = retCarrCd;
                    break;
                } else {
                    carrCd = retCarrCd;
                }
            }
        }

        // START 2018/03/23 S.Katsuma [QC#24353,ADD]
        if (!ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(asnDtlTMsg.asnCarrCd)) {
            carrCd = getCarrCdFromOtbdCarr(asnDtlTMsg);
        }
        // END 2018/03/23 S.Katsuma [QC#24353,ADD]
        // QC#26730
        if (ZYPCommonFunc.hasValue(carrCd) && "*".equals(carrCd)) {
            carrCd = "";
        }

        // QC#52401
        if (!ZYPCommonFunc.hasValue(carrCd)) {

            PO_DTLTMsg tMsg = new PO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(tMsg.poOrdNum, asnHdrTMsg.poOrdNum.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.poOrdDtlLineNum, asnDtlTMsg.poOrdDtlLineNum.getValue());

            tMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.carrCd)) {
                carrCd = tMsg.carrCd.getValue();
            }
        }

        // NPZC1310 Drop Ship Create API
        // ************************************************
        NPZC131001PMsg pMsg = new NPZC131001PMsg();

        // Header
        // ************************************************
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC131001Constant.MODE_CREATE);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, asnHdrTMsg.poOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.rwsRefNum, asnHdrTMsg.shipFromSoNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.shipDt, (asnHdrTMsg.asnShipDtTmTs.getValue()).substring(0, 8));
        ZYPEZDItemValueSetter.setValue(pMsg.vndInvNum, asnHdrTMsg.vndInvNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.asnSoNum, asnHdrTMsg.asnSoNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.shipFromSoNum, asnHdrTMsg.shipFromSoNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.frtAmt, asnHdrTMsg.asnTotFrtAmt.getValue());

        // Detail
        // ************************************************
        for (int i = 0; i < asnDtlList.size(); i++) {

            EDI_ASN_DTL_WRKTMsg asnDtlTMsgTemp = asnDtlList.get(i);

            if (ZYPCommonFunc.hasValue((cmbnDtlList.get(cnt).get(asnDtlTMsgTemp.asnSoSlpNum.getValue())))) {
                // Skip Combine SO ASN
                cnt++;
                continue;
            }

            cnt++;

            if (!ZYPCommonFunc.hasValue(pMsg.etaDt)) {
                ZYPEZDItemValueSetter.setValue(pMsg.etaDt, asnDtlTMsgTemp.asnPlnDelyDt.getValue());
            }

            if (pMsg.etaDt.getValue().compareTo(asnDtlTMsgTemp.asnPlnDelyDt.getValue()) < 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.etaDt, asnDtlTMsgTemp.asnPlnDelyDt.getValue());
            }

            ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(i).poOrdDtlLineNum, asnDtlTMsgTemp.poOrdDtlLineNum.getValue());

            // QC#15661
            // if
            // (ZYPCommonFunc.hasValue(asnDtlTMsgTemp.ediPoOrdDtlLineNum))
            // {
            // // ws
            // ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(i).poOrdDtlLineNum,
            // asnDtlTMsgTemp.ediPoOrdDtlLineNum.getValue());
            //
            // } else {
            // // parts
            // ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(i).poOrdDtlLineNum,
            // asnDtlTMsgTemp.poOrdDtlLineNum.getValue());
            // }

            ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(i).mdseCd, asnDtlTMsgTemp.mdseCd.getValue());

            // T.Wada Mod QC13224
            if (ZYPCommonFunc.hasValue(asnDtlTMsgTemp.asnStkStsCd)) {
                ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(i).stkStsCd, asnDtlTMsgTemp.asnStkStsCd.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(i).stkStsCd, STK_STS.GOOD);
            }

            ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(i).shipQty, asnDtlTMsgTemp.asnQty.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(i).asnLineNum, asnDtlTMsgTemp.asnLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(i).carrCd, carrCd);
            ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(i).bolNum, asnDtlTMsgTemp.asnBolNum.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.ordDtlInfo.no(i).proNum, asnDtlTMsgTemp.asnProNum.getValue());

            pMsg.ordDtlInfo.setValidCount(i + 1);
        }

        // serial
        // ************************************************
        for (int j = 0; j < asnSerList.size(); j++) {

            EDI_ASN_SER_NUM_WRKTMsg asnSerTMsgTemp = asnSerList.get(j);

            ZYPEZDItemValueSetter.setValue(pMsg.serNumList.no(j).poOrdDtlLineNum, asnSerTMsgTemp.poOrdDtlLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.serNumList.no(j).mdseCd, asnSerTMsgTemp.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.serNumList.no(j).serNum, asnSerTMsgTemp.serNum.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.serNumList.no(j).soSerId, asnSerTMsgTemp.soSerId.getValue());

            pMsg.serNumList.setValidCount(j + 1);

        }

        new NPZC131001().execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);

            for (String xxMsgId : xxMsgIdList) {
                S21InfoLogOutput.println(xxMsgId);
                if (xxMsgId.endsWith("E")) {
                    String errorApi = "NPZC131001:" + S21MessageFunc.clspGetMessage(xxMsgId);
                    // START 2019/02/19 T.Ogura [QC#30312,MOD]
//                    addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
//                    addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                    addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1641E, new String[] {errorApi, pMsg.poOrdNum.getValue(), pMsg.asnSoNum.getValue() }));
                    addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1641E, new String[] {errorApi, pMsg.poOrdNum.getValue(), pMsg.asnSoNum.getValue() }));
                    // END   2019/02/19 T.Ogura [QC#30312,MOD]
                    isError = true;
                } else if (xxMsgId.equals("NLZM2320W")) {
                    continue;
                }
            }

        }

        return isError;
    }

    /**
     * callAPI
     * @param resultSet ResultSet
     * @param asnHdrTMsg EDI_ASN_HDR_WRKTMsg
     * @param asnDtlList List<EDI_ASN_DTL_WRKTMsg>
     * @param asnDtlMapList List<Map<String, Object>>
     * @param poDtlSplitMapList HashMap<String, String>
     * @return boolean
     * @throws SQLException
     */
    private boolean callAPI(ResultSet resultSet, EDI_ASN_HDR_WRKTMsg asnHdrTMsg, List<EDI_ASN_DTL_WRKTMsg> asnDtlList, List<Map<String, Object>> asnDtlMapList, NPZC104001PMsg npzc104001Pmsg) throws SQLException {

        String shipFromSoNum = resultSet.getString(EH_SHIP_FROM_SO_NUM);
//        String poOrdCmntTxt = getResultSetValueForPO(resultSet, PO_ORD_CMNT_TXT);    // 2019/09/12 T.Ogura [QC#53424,DEL]
        String poRcvNum = null;
        String rwsNum = null;
        String vndCd = getResultSetValueForPO(resultSet, VND_CD);

        List<Map<String, Object>> rwsList = getRwsCreatedByPoCreateApi(asnHdrTMsg, vndCd);
        if (rwsList != null && !rwsList.isEmpty()) {
            List<Map<String, Object>> recvedPoDtlList = getRecvedPoDtl(asnHdrTMsg);
            if (recvedPoDtlList != null && !recvedPoDtlList.isEmpty()) {
                if (!callFsrUpdateAPI(asnHdrTMsg, (String) rwsList.get(0).get(RWS_NUM))) {
                    return false;
                }
                S21InfoLogOutput.println(MSG_ID_NPAM1575I, new String[] {asnHdrTMsg.asnSoNum.getValue(), asnHdrTMsg.poOrdNum.getValue() });
                return true;
            } else {
                // QC#17627
                Map<String, List<Map<String, Object>>> rwsListMap = new HashMap<String, List<Map<String, Object>>>();
                for (Map<String, Object> rws : rwsList) {
                    List<Map<String, Object>> tempList;
                    if (!rwsListMap.containsKey(rws.get(RWS_NUM))) {
                        tempList = new ArrayList<Map<String, Object>>();
                    } else {
                        tempList = rwsListMap.get(rws.get(RWS_NUM));
                    }
                    tempList.add(rws);
                    rwsListMap.put((String) rws.get(RWS_NUM), tempList);
                }
                boolean resultCancelRws = true;
                for (Map.Entry<String, List<Map<String, Object>>> entry : rwsListMap.entrySet()) {
                    if (!cancelRws(asnHdrTMsg, entry.getValue())) {
                        resultCancelRws = false;
                    }
                }
                if (!resultCancelRws) {
                    return false;
                }
                // QC#17627
            }
        }

        // NLZZC2010 PO Receiving API
        // *************************************************
        // START 2019/09/12 T.Ogura [QC#53424,MOD]
//        if (callPoReceivingApi(asnHdrTMsg, asnDtlList, vndCd, shipFromSoNum, poOrdCmntTxt, npzc104001Pmsg)) {
        if (callPoReceivingApi(asnHdrTMsg, asnDtlList, vndCd, shipFromSoNum, npzc104001Pmsg)) {
        // END   2019/09/12 T.Ogura [QC#53424,MOD]

            poRcvNum = asnHdrTMsg.poRcvNum.getValue();

        } else {

            return false;
        }

        // NLZC2000 RWS API
        // *************************************************
        if (ZYPCommonFunc.hasValue(poRcvNum)) {

            if (callRwsApi(poRcvNum, asnHdrTMsg)) {

                rwsNum = asnHdrTMsg.rwsNum.getValue();

            } else {

                return false;

            }
        }

        if (ZYPCommonFunc.hasValue(rwsNum)) {

            // DS RWS Update
            // ************************************************
            if (dsRwsUpdate(shipFromSoNum, poRcvNum, asnHdrTMsg)) {

                return false;
            }

            // NLZC3040 RWS Serial API
            // ************************************************
            if (callRwsSerialApi(poRcvNum, asnHdrTMsg, rwsNum)) {

                return false;
            }

            /* 07/18/2016 CSAI Y.Imazu Add QC#6663 START */
            // Release Pending Parts
            // ************************************************
            if (!callFsrUpdateAPI(asnHdrTMsg, rwsNum)) {

                return false;
            }
            /* 07/18/2016 CSAI Y.Imazu Add QC#6663 END */

            // 02/21/2017 QC#17049 Add.
            // NPZC1030 PR Update API（Update Ship Qty）
            // *************************************************
            // START 2019/09/12 T.Ogura [QC#53424,MOD]
//            if (!callPrUpdateAPI(asnHdrTMsg, asnDtlList, vndCd, shipFromSoNum, poOrdCmntTxt, npzc104001Pmsg)) {
            if (!callPrUpdateAPI(asnHdrTMsg, asnDtlList, vndCd, shipFromSoNum, npzc104001Pmsg)) {
            // END   2019/09/12 T.Ogura [QC#53424,MOD]

                return false;
            }

        }

        return true;
    }

    /**
     * getRwsCreatedByPoCreateApi
     * @param asnHdrTMsg EDI_ASN_HDR_WRKTMsg
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getRwsCreatedByPoCreateApi(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, String vndCd) {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_PO_ORD_NUM, asnHdrTMsg.poOrdNum.getValue());
        // START 04/21/2020 T.Ogura [QC#56545,MOD]
//        paramMap.put(BIND_RWS_STS_CD, RWS_STS.CANCELED);
        String[] rwsStsCdAry = {RWS_STS.RECEIVED, RWS_STS.CANCELED };
        paramMap.put(BIND_RWS_STS_CD, rwsStsCdAry);
        // END   04/21/2020 T.Ogura [QC#56545,MOD]

        // 2023/10/25 QC#61968 START
        // QC#26964 Add start
        // String vndSysTpCd = getVndSysTpCd(vndCd);
        // if (ZYPCommonFunc.hasValue(vndSysTpCd) && (VND_SYS_TP.DIETZGEN.equals(vndSysTpCd) || VND_SYS_TP.TST_IMPRESO.equals(vndSysTpCd))) {
        //     paramMap.put(BIND_SCE_ORD_TP_CD, NLXSceConst.SCE_ORD_TP_CD_DP);
        // } else {
        //     paramMap.put(BIND_SCE_ORD_TP_CD, NLXSceConst.SCE_ORD_TP_CD_DG);
        // }
        // QC#26964 Add end
        String sceOrdTpCd = getsceOrdTpCd(vndCd);
        if (ZYPCommonFunc.hasValue(sceOrdTpCd) && (SCE_ORD_TP.DOMESTIC_PO_EXISTS.equals(sceOrdTpCd))) {
            paramMap.put(BIND_SCE_ORD_TP_CD, NLXSceConst.SCE_ORD_TP_CD_DP);
        } else {
            paramMap.put(BIND_SCE_ORD_TP_CD, NLXSceConst.SCE_ORD_TP_CD_DG);
        }
        // 2023/10/25 QC#61968 END

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRwsCreatedByPoCreateApi", paramMap);
    }

    // QC#26964 Add start
    /**
     * getVndSysTpCd
     * @param vndCd
     * @return
     */
    private String getVndSysTpCd(String vndCd) {

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", this.globalCompanyCode);
        paramMap.put("rcvAsnVndCd", vndCd);

        String vndSysTpCd = (String) ssmBatchClient.queryObject("getVndSysTpCd", paramMap);

        return vndSysTpCd;
    }

    // QC#26964 Add end

    /**
     * TODO getPrByPrUpdateApi
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getPrByPrUpdateApi(String poOrdNum, String poOrdDtlLineNum) {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_PO_ORD_NUM, poOrdNum);
        paramMap.put(BIND_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);
        paramMap.put(BIND_PRCH_REQ_LINE_TP_CD_INSOURCED_PO, PRCH_REQ_LINE_TP.INSOURCED_PO);
        paramMap.put(BIND_PRCH_REQ_REC_TP_CD_TECH, PRCH_REQ_REC_TP.TECH_REQUEST);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPrByPrUpdateApi", paramMap);
    }

    /**
     * getRecvedPoDtl
     * @param asnHdrTMsg EDI_ASN_HDR_WRKTMsg
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getRecvedPoDtl(EDI_ASN_HDR_WRKTMsg asnHdrTMsg) {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_PO_ORD_NUM, asnHdrTMsg.poOrdNum.getValue());
        paramMap.put(BIND_PO_RCV_QTY, BigDecimal.ZERO);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRecvedPoDtl", paramMap);
    }

    /**
     * cancelRws
     * @param asnHdrTMsg EDI_ASN_HDR_WRKTMsg
     * @param rwsList List<Map<String, Object>>
     * @return boolean
     */
    private boolean cancelRws(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, List<Map<String, Object>> rwsList) {
        // Cancel RWS_HDR
        RWS_HDRTMsg inRwsHdrTMsg = new RWS_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(inRwsHdrTMsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(inRwsHdrTMsg.rwsNum, (String) rwsList.get(0).get(RWS_NUM));
        ZYPEZDItemValueSetter.setValue(inRwsHdrTMsg.rwsStsCd, RWS_STS.CANCELED);
        EZDTBLAccessor.updateSelectionField(inRwsHdrTMsg, new String[] {FIELD_RWS_STS_CD });
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inRwsHdrTMsg.getReturnCode())) {
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1171E, new String[] {inRwsHdrTMsg.getTableName() }));
            return false;
        }

        // Cancel RWS_DTL
        RWS_DTLTMsg inRwsDtlTMsg = new RWS_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inRwsDtlTMsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(inRwsDtlTMsg.rwsNum, (String) rwsList.get(0).get(RWS_NUM));
        ZYPEZDItemValueSetter.setValue(inRwsDtlTMsg.rwsStsCd, RWS_STS.CANCELED);
        EZDTBLAccessor.updateByPartialKey(inRwsDtlTMsg, new String[] {FIELD_RWS_STS_CD });
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inRwsDtlTMsg.getReturnCode()) || EZDTBLAccessor.RTNCD_NOT_FOUND.equals(inRwsDtlTMsg.getReturnCode())) {
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1171E, new String[] {inRwsDtlTMsg.getTableName() }));
            return false;
        }

        // Cancel PO_RCV_HDR
        PO_RCV_HDRTMsg inPoRcvHdrTMsg = new PO_RCV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(inPoRcvHdrTMsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(inPoRcvHdrTMsg.poRcvNum, (String) rwsList.get(0).get(TRX_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(inPoRcvHdrTMsg.rwsStsCd, RWS_STS.CANCELED);
        EZDTBLAccessor.updateSelectionField(inPoRcvHdrTMsg, new String[] {FIELD_RWS_STS_CD });
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inPoRcvHdrTMsg.getReturnCode())) {
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1171E, new String[] {inPoRcvHdrTMsg.getTableName() }));
            return false;
        }

        // Cancel PO_RCV_DTL
        PO_RCV_DTLTMsg inPoRcvDtlTMsg = new PO_RCV_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inPoRcvDtlTMsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(inPoRcvDtlTMsg.poRcvNum, (String) rwsList.get(0).get(TRX_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(inPoRcvDtlTMsg.rwsStsCd, RWS_STS.CANCELED);
        EZDTBLAccessor.updateByPartialKey(inPoRcvDtlTMsg, new String[] {FIELD_RWS_STS_CD });
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inPoRcvDtlTMsg.getReturnCode()) || EZDTBLAccessor.RTNCD_NOT_FOUND.equals(inPoRcvDtlTMsg.getReturnCode())) {
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1171E, new String[] {inPoRcvDtlTMsg.getTableName() }));
            return false;
        }

        // Update and Delete INBD_VIS
        INBD_VISTMsg inInbdVisTMsg = new INBD_VISTMsg();
        inInbdVisTMsg.setSQLID(SQL_ID_011);
        inInbdVisTMsg.setConditionValue(SQL_COND_KEY_GLBL_CMPY_CD_01, this.globalCompanyCode);
        inInbdVisTMsg.setConditionValue(SQL_COND_KEY_PO_RCV_NUM_01, (String) rwsList.get(0).get(TRX_ORD_NUM));
        inInbdVisTMsg.setConditionValue(SQL_COND_KEY_INBD_LTST_REC_FLG_01, ZYPConstant.FLG_ON_Y);
        INBD_VISTMsgArray outInbdVisTMsgList = (INBD_VISTMsgArray) EZDTBLAccessor.findByCondition(inInbdVisTMsg);
        if (outInbdVisTMsgList == null || outInbdVisTMsgList.getValidCount() == 0) {
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1171E, new String[] {inInbdVisTMsg.getTableName() }));
            return false;
        }
        for (int i = 0; i < outInbdVisTMsgList.getValidCount(); i++) {
            INBD_VISTMsg outInbdVisTMsg = outInbdVisTMsgList.no(i);
            ZYPEZDItemValueSetter.setValue(outInbdVisTMsg.inbdLtstRecFlg, ZYPConstant.FLG_OFF_N);
            EZDTBLAccessor.update(outInbdVisTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outInbdVisTMsg.getReturnCode())) {
                addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1171E, new String[] {outInbdVisTMsg.getTableName() }));
                return false;
            }
            EZDTBLAccessor.logicalRemove(outInbdVisTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outInbdVisTMsg.getReturnCode())) {
                addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1298E));
                return false;
            }
        }

        // Delete WH_SCHD
        WH_SCHDTMsg inWhSchdTMsg = new WH_SCHDTMsg();
        inWhSchdTMsg.setSQLID(SQL_ID_011);
        inWhSchdTMsg.setConditionValue(SQL_COND_KEY_GLBL_CMPY_CD_01, this.globalCompanyCode);
        inWhSchdTMsg.setConditionValue(SQL_COND_KEY_PO_RCV_NUM_01, (String) rwsList.get(0).get(TRX_ORD_NUM));
        WH_SCHDTMsgArray outWhSchdTMsgList = (WH_SCHDTMsgArray) EZDTBLAccessor.findByCondition(inWhSchdTMsg);
        if (outWhSchdTMsgList == null || outWhSchdTMsgList.getValidCount() == 0) {
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1171E, new String[] {inWhSchdTMsg.getTableName() }));
            return false;
        }
        for (int i = 0; i < outWhSchdTMsgList.getValidCount(); i++) {
            WH_SCHDTMsg outWhSchdTMsg = outWhSchdTMsgList.no(i);
            EZDTBLAccessor.logicalRemove(outWhSchdTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outWhSchdTMsg.getReturnCode())) {
                addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1298E));
                return false;
            }
        }

        // 2016/11/09 R.Shimamoto QC#15121 Mod Start.
        if (NLXSceConst.SCE_ORD_TP_CD_DG.equals((String) rwsList.get(0).get(SCE_ORD_TP_CD))) {

            // Call Inventory Update API(NLZC002001)
            String stdCcyCd = getStdCcy();
            for (Map<String, Object> rws : rwsList) {
                NLZC002001PMsg invtyUpdPMsg = new NLZC002001PMsg();
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.glblCmpyCd, this.globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.trxCd, TRX.MOVEMENT);
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.trxRsnCd, TRX_RSN.INBOUND_IN_TRANSIT_STOCK_OUT);
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.xxTrxDtlCd, NLZC002001.TRX_DTL_DOM);
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.mdseCd, (String) rws.get(MDSE_CD));
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.invtyLocCd, (String) rws.get(INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.locStsCd, LOC_STS.IN_TRANSIT);
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.stkStsCd, (String) rws.get(RWS_STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.xxRqstQty, (BigDecimal) rws.get(RWS_BAL_QTY));
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.invtyTrxDt, this.salesDate);
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.xxSysTp, NLZC002001.SYS_TP_INBD);
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.poRcvNum, (String) rws.get(TRX_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.poRcvLineNum, (String) rws.get(TRX_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.rwsNum, (String) rws.get(RWS_NUM));
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.rwsRefNum, (String) rws.get(RWS_REF_NUM));
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.rwsDtlLineNum, (String) rws.get(RWS_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.poOrdNum, (String) rws.get(PO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.poOrdDtlLineNum, (String) rws.get(PO_ORD_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.invtyTrxSlpNum, (String) rws.get(RWS_REF_NUM));
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.vndCd, (String) rws.get(SHIP_FROM_LOC_CD));
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.ccyCd, stdCcyCd);
                ZYPEZDItemValueSetter.setValue(invtyUpdPMsg.uomCd, PKG_UOM.EACH);

                NLZC002001 rwsSerAPI = new NLZC002001();
                rwsSerAPI.execute(invtyUpdPMsg, ONBATCH_TYPE.BATCH);
                boolean isError = false;
                for (String xxMsgId : S21ApiUtil.getXxMsgIdList(invtyUpdPMsg)) {
                    if (!ZYPCommonFunc.hasValue(xxMsgId)) {
                        continue;
                    }

                    S21InfoLogOutput.println(xxMsgId);

                    if (xxMsgId.endsWith("E")) {
                        String errorApi = "NLZC002001:" + S21MessageFunc.clspGetMessage(xxMsgId);
                        addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                        addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                        isError = true;
                    }
                }
                if (isError) {
                    return false;
                }
            }
        }
        // 2016/11/09 R.Shimamoto QC#15121 Mod End.

        // Call POYO Update API (NPZC1090) [Create Inbounds
        // Visibility]
        for (Map<String, Object> rws : rwsList) {
            NPZC109001PMsg poyoVisPMsg = new NPZC109001PMsg();
            ZYPEZDItemValueSetter.setValue(poyoVisPMsg.glblCmpyCd, this.globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(poyoVisPMsg.xxModeCd, NPZC109001Constant.POYO_INSERT_MODE);

            NPZC109001_detailListPMsg detail = poyoVisPMsg.detailList.no(0);
            ZYPEZDItemValueSetter.setValue(detail.poOrdNum, (String) rws.get(PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(detail.poOrdDtlLineNum, (String) rws.get(PO_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(detail.xxQty10Num, (BigDecimal) rws.get(RWS_BAL_QTY));
            poyoVisPMsg.detailList.setValidCount(1);

            NPZC109001 poyoVisAPI = new NPZC109001();
            poyoVisAPI.execute(poyoVisPMsg, ONBATCH_TYPE.BATCH);
            boolean isError = false;
            for (String xxMsgId : S21ApiUtil.getXxMsgIdList(poyoVisPMsg)) {
                if (!ZYPCommonFunc.hasValue(xxMsgId)) {
                    continue;
                }

                S21InfoLogOutput.println(xxMsgId);

                if (xxMsgId.endsWith("E")) {
                    String errorApi = "NPZC109001:" + S21MessageFunc.clspGetMessage(xxMsgId);
                    addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                    addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                    isError = true;
                }
            }
            if (isError) {
                return false;
            }
        }

        return true;
    }

    /**
     * getStdCcy
     * @return String
     */
    private String getStdCcy() {

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.globalCompanyCode);
        glblCmpyTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {

            return null;
        }

        return glblCmpyTMsg.stdCcyCd.getValue();
    }

    /* 07/18/2016 CSAI Y.Imazu Add QC#6663 START */
    /**
     * callFsrUpdateAPI
     * @param asnHdrTMsg EDI_ASN_HDR_WRKTMsg
     * @param rwsNum String
     * @return boolean true : OK, false : NG
     */
    private boolean callFsrUpdateAPI(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, String rwsNum) {

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.globalCompanyCode);
        param.put("rwsNum", rwsNum);
        param.put("prchReqRecTpCd", PRCH_REQ_REC_TP.TECH_REQUEST);
        param.put("svcTaskStsPendPrt", SVC_TASK_STS.PENDING_PARTS);

        List<Map<String, Object>> fsrTaskMapList = (ArrayList<Map<String, Object>>) ssmBatchClient.queryObjectList("getFsrTaskList", param);

        if (fsrTaskMapList == null || fsrTaskMapList.isEmpty()) {

            return true;
        }

        boolean isNormal = true;

        // Call FSR Update API
        for (Map<String, Object> fsrTaskMap : fsrTaskMapList) {

            NSZC043001PMsg pMsg = new NSZC043001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
            ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, (String) fsrTaskMap.get("FSR_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.userId, (String) fsrTaskMap.get("RQST_TECH_TOC_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(0).svcTaskNum, (String) fsrTaskMap.get("SVC_TASK_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(0).erlStartTs, ZYPCommonFunc.rightPad((String) fsrTaskMap.get("LTST_WH_IN_ETA_DT"), 17, "0"));
            ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(0).xxOrigFollUpTaskFlg, ZYPConstant.FLG_ON_Y);
            pMsg.taskList.setValidCount(1);

            NSZC043001 api = new NSZC043001();
            api.execute(pMsg, ONBATCH_TYPE.BATCH);

            for (String xxMsgId : S21ApiUtil.getXxMsgIdList(pMsg)) {

                if (!ZYPCommonFunc.hasValue(xxMsgId)) {

                    continue;
                }

                S21InfoLogOutput.println(xxMsgId);

                if (xxMsgId.endsWith("E")) {

                    String errorApi = "NSZC043001:" + S21MessageFunc.clspGetMessage(xxMsgId);
                    addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                    addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                    isNormal = false;
                }
            }
        }

        return isNormal;
    }

    /* 07/18/2016 CSAI Y.Imazu Add QC#6663 END */

    /**
     * deleteEdiAsnErrWrk
     * @param asnHdrTMsg EDI_ASN_HDR_WRKTMsg
     */
    private void deleteEdiAsnErrWrk(EDI_ASN_HDR_WRKTMsg asnHdrTMsg) {

        EDI_ASN_ERR_WRKTMsg inTMsg = new EDI_ASN_ERR_WRKTMsg();

        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", globalCompanyCode);
        inTMsg.setConditionValue("asnSoNum01", asnHdrTMsg.asnSoNum.getValue());

        EDI_ASN_ERR_WRKTMsgArray outTMsgArray = (EDI_ASN_ERR_WRKTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inTMsg);

        // logical remove
        for (int i = 0; i < outTMsgArray.getValidCount(); i++) {
            EZDTBLAccessor.logicalRemove(outTMsgArray.no(i));
        }
    }

    /**
     * checkAsnHdr
     * @param resultSet ResultSet
     * @param asnHdrTMsg EDI_ASN_HDR_WRKTMsg
     * @return boolean
     * @throws SQLException
     */
    private boolean checkAsnHdr(ResultSet resultSet, EDI_ASN_HDR_WRKTMsg asnHdrTMsg) throws SQLException {

        boolean isErr = false;

        StringBuilder subMsg = new StringBuilder("EDI PO#=").append((String) resultSet.getString(EH_EDI_PO_ORD_NUM));
        StringBuilder subMsgAddPo = new StringBuilder(subMsg).append(", PO#=").append(getResultSetValueForPO(resultSet, PO_ORD_NUM));

        // mandatory check
        // ********************************
        if (checkMandatoryHdrItem(asnHdrTMsg)) {
            isErr = true;
        }

        // check SHIP_FROM_SO_NUM digit
        // ********************************
        if (checkHdrDigit(asnHdrTMsg, resultSet, subMsgAddPo)) {
            isErr = true;
        }

        if (isErr) {
            return isErr;
        }

        // check PO_ORD_NUM
        // ********************************
        if (checkPoOrdNum(asnHdrTMsg, resultSet, subMsg)) {
            isErr = true;
        }

        // check PO_STS_CD
        // ********************************
        if (checkPoStsCd(asnHdrTMsg, resultSet, subMsgAddPo)) {
            isErr = true;
        }

        return isErr;
    }

    /**
     * getEdiDetailList. Update QC#26964/QC#26965
     * @param asnHdrTMsg EDI_ASN_HDR_WRKTMsg
     * @param asnDtlList List<EDI_ASN_DTL_WRKTMsg>
     * @param cmbnDtlList List<Map<String, String>>
     */
    private boolean getEdiDetailList(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, List<EDI_ASN_DTL_WRKTMsg> asnDtlList, List<Map<String, String>> cmbnDtlList) {

        boolean isError = false;

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_ASN_SO_NUM, asnHdrTMsg.asnSoNum.getValue());
        paramMap.put("ediPoOrdNum", asnHdrTMsg.ediPoOrdNum.getValue());
        List<Map<String, Object>> asnDetailList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getEdiAsnDetail", paramMap);

        for (Map<String, Object> asnDetail : asnDetailList) {

            EDI_ASN_DTL_WRKTMsg asnDtlTMsg = new EDI_ASN_DTL_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(asnDtlTMsg.glblCmpyCd, this.globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(asnDtlTMsg.asnSoNum, asnHdrTMsg.asnSoNum.getValue());
            ZYPEZDItemValueSetter.setValue(asnDtlTMsg.asnLineNum, (String) asnDetail.get(ED_ASN_LINE_NUM));

            asnDtlTMsg = (EDI_ASN_DTL_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(asnDtlTMsg);

            if (asnDtlTMsg == null) {
                addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1003E, new String[] {"EDI_ASN_DTL_WRK", "SO_NUM, SO_SER_ID",
                        asnHdrTMsg.asnSoNum.getValue() + (String) asnDetail.get(ED_ASN_LINE_NUM) }));
                isError = true;
            }

            ZYPEZDItemValueSetter.setValue(asnDtlTMsg.poOrdNum, asnHdrTMsg.poOrdNum.getValue());
            asnDtlList.add(asnDtlTMsg);

            Map<String, String> lineSoMap = new HashMap<String, String>();
            lineSoMap.put(asnDtlTMsg.asnSoSlpNum.getValue(), (String) asnDetail.get("SO_NUM"));
            cmbnDtlList.add(lineSoMap);
        }

        return isError;
    }

    /**
     * getEdiAsnSerNumList
     * @param asnHdrTMsg
     * @param asnSerList
     * @return
     */
    private void getEdiAsnSerNumList(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, List<EDI_ASN_SER_NUM_WRKTMsg> asnSerList) {

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_ASN_SO_NUM, asnHdrTMsg.asnSoNum.getValue());

        List<Map<String, String>> asnSerMapList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getEdiAsnSerNumWrk", paramMap);

        for (Map<String, String> asnSerMap : asnSerMapList) {

            EDI_ASN_SER_NUM_WRKTMsg asnSerTMsg = new EDI_ASN_SER_NUM_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(asnSerTMsg.glblCmpyCd, this.globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(asnSerTMsg.asnMdseCd, asnSerMap.get(ES_ASN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(asnSerTMsg.asnSoNum, asnHdrTMsg.asnSoNum);
            ZYPEZDItemValueSetter.setValue(asnSerTMsg.soSerId, asnSerMap.get(ES_SO_SER_ID));
            // QC#20884
            ZYPEZDItemValueSetter.setValue(asnSerTMsg.asnSoSlpNum, asnSerMap.get("ED_ASN_SO_SLP_NUM"));

            asnSerTMsg = (EDI_ASN_SER_NUM_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait((EZDTMsg) asnSerTMsg);

            if (asnSerTMsg == null) {
                addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1003E, new String[] {"EDI_ASN_SER_NUM_WRK", "ASN_MDSE_CD, SO_NUM, SO_SER_ID",
                        asnSerMap.get(ES_ASN_MDSE_CD) + ", " + asnHdrTMsg.asnSoNum.getValue() + ", " + asnSerMap.get(ES_SO_SER_ID) }));

            }

            // check ASN_MDSE_CD digit
            String mdseCd = asnSerMap.get(XR_MDSE_CD);
            if (ZYPCommonFunc.hasValue(mdseCd) && mdseCd.length() > MDSE_CD_LEN) {
                StringBuilder subMsg = new StringBuilder("EDI PO#=").append(asnHdrTMsg.ediPoOrdNum.getValue());
                subMsg.append(", SO SER ID=").append(asnSerMap.get(ES_SO_SER_ID));
                addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1320E, new String[] {createDigitErrMsg(subMsg, "ASN_MDSE_CD", mdseCd, MDSE_CD_LEN) }));
                continue;
            }

            // set Mdse Code, PO Order Number, PO Order Detail Line
            // Number
            ZYPEZDItemValueSetter.setValue(asnSerTMsg.mdseCd, mdseCd);
            ZYPEZDItemValueSetter.setValue(asnSerTMsg.poOrdNum, asnHdrTMsg.poOrdNum);
            ZYPEZDItemValueSetter.setValue(asnSerTMsg.poOrdDtlLineNum, getEdiNum((String) asnSerMap.get(ED_EDI_PO_ORD_DTL_LINE_NUM)));
            asnSerList.add(asnSerTMsg);
        }

    }

    /**
     * Get CSA EDI_NUM from CUSA EDI_NUM
     * @param ediNum CUSA EDI_NUM
     * @return ediNum CSA EDI_NUM
     */
    private String getEdiNum(String ediNum) {

        if (ediNum == null) {
            return ediNum;
        }

        if (ediNum.length() > CSA_EDI_NUM_LEN) {
            return ediNum.substring(ediNum.length() - CSA_EDI_NUM_LEN);
        } else {
            return ediNum;
        }
    }

    /**
     * checkAsnDtlSerItem
     * @param asnHdrTMsg
     * @param asnDtlList
     * @param asnSerList
     * @return
     */

    private boolean checkAsnDtlSerItem(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, List<EDI_ASN_DTL_WRKTMsg> asnDtlList, List<EDI_ASN_SER_NUM_WRKTMsg> asnSerList) {

        boolean isErr = false;

        for (EDI_ASN_DTL_WRKTMsg asnDtlTMsg : asnDtlList) {
            // mandatory check
            if (checkMandatoryDtlItem(asnHdrTMsg, asnDtlTMsg)) {
                isErr = true;
            }

            // digit check
            if (checkDtlDigit(asnHdrTMsg, asnDtlTMsg)) {
                isErr = true;
            }
        }

        for (EDI_ASN_SER_NUM_WRKTMsg asnSerTMsg : asnSerList) {
            // mandatory check
            if (checkMandatorySerItem(asnHdrTMsg, asnSerTMsg)) {
                isErr = true;
            }
        }

        return isErr;
    }

    /**
     * updateMdseCd. Update QC#26964/QC#26965
     * @param asnHdrTMsg
     * @param asnDtlList
     * @param asnSerList
     * @param cmbnDtlList
     * @return
     */
    private boolean updateMdseCd(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, List<EDI_ASN_DTL_WRKTMsg> asnDtlList, List<EDI_ASN_SER_NUM_WRKTMsg> asnSerList, List<Map<String, String>> cmbnDtlList) {

        boolean isErr = false;
        int cnt = 0;
        // update Edi Asn Detail Work
        // *************************************************************
        for (EDI_ASN_DTL_WRKTMsg asnDtlTMsg : asnDtlList) {

            if (ZYPCommonFunc.hasValue((cmbnDtlList.get(cnt).get(asnDtlTMsg.asnSoSlpNum.getValue())))) {
                // Skip Combine SO ASN
                cnt++;
                continue;
            }

            cnt++;
            // QC#25146 Process Flow Change Start
            boolean apiAllErr = true;
            String mdseCd = null;

            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
            paramMap.put(BIND_ASL_START_DT, this.salesDate);
            paramMap.put(BIND_ASL_END_DT, this.salesDate);
            paramMap.put(BIND_SLS_DT, this.salesDate);
            paramMap.put(BIND_VND_CD, asnHdrTMsg.vndCd.getValue());
            paramMap.put(BIND_SPLY_ITEM_NUM, asnDtlTMsg.asnMdseCd.getValue());

            List<String> csaMdseCdList = (List<String>) ssmBatchClient.queryObjectList("mdseCdFromAsnDtl", paramMap);

            // START 2018/11/14 M.Naito [QC#29048,ADD]
            // check TST / Impreso Inc 
            String vndSysTpCd = getVndSysTpCd(asnHdrTMsg.vndCd.getValue());
            if ((csaMdseCdList == null || csaMdseCdList.isEmpty()) && ZYPCommonFunc.hasValue(vndSysTpCd) && VND_SYS_TP.TST_IMPRESO.equals(vndSysTpCd)) {
                csaMdseCdList.add(asnDtlTMsg.asnMdseCd.getValue());
            }
            // END 2018/11/14 M.Naito [QC#29048,ADD]

            if (csaMdseCdList == null || csaMdseCdList.isEmpty()) {
                StringBuilder subMsg = new StringBuilder("EDI PO#=").append(asnHdrTMsg.ediPoOrdNum.getValue());
                subMsg.append(", EDI Line#=").append(getSubMsgItemValue(asnDtlTMsg.ediPoOrdDtlLineNum.getValue(), EDI_PO_ORD_DTL_LINE_NUM_LEN));
                String errorApi = "NPZC104001:" + S21MessageFunc.clspGetMessage(NPAM1364E) + " " + subMsg;
                addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                isErr = true;
                continue;
            }

            NPZC110001PMsg pMsg = null;
            // QC#30696
            HashMap<String, String> apiErrMap = new LinkedHashMap<String, String>();
            for (String csaMdseCd : csaMdseCdList) {

                if (ZYPCommonFunc.hasValue(csaMdseCd)) {
                    // check ASN_MDSE_CD digit
                    if (ZYPCommonFunc.hasValue(csaMdseCd) && csaMdseCd.length() > MDSE_CD_LEN) {
                        StringBuilder subMsg = new StringBuilder("EDI PO#=").append(asnHdrTMsg.ediPoOrdNum.getValue());
                        subMsg.append(", EDI Line#=").append(getSubMsgItemValue(asnDtlTMsg.ediPoOrdDtlLineNum.getValue(), EDI_PO_ORD_DTL_LINE_NUM_LEN));
                        String errMsg = S21MessageFunc.clspGetMessage(MSG_ID_NPAM1320E, new String[] {createDigitErrMsg(subMsg, "ASN_MDSE_CD", csaMdseCd, MDSE_CD_LEN) });
                        S21InfoLogOutput.println(errMsg);
                        apiErrMap.put(csaMdseCd, errMsg);
                        continue;
                    }

                    pMsg = callGetPoLineFromEdiApi(asnHdrTMsg, asnDtlTMsg, csaMdseCd);

                    // api error occured
                    if (S21ApiUtil.isXxMsgId(pMsg)) {

                        List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
                        StringBuilder subMsg = new StringBuilder("EDI PO#=").append(asnHdrTMsg.ediPoOrdNum.getValue());
                        subMsg.append(", EDI Line#=").append(getSubMsgItemValue(asnDtlTMsg.ediPoOrdDtlLineNum.getValue(), EDI_PO_ORD_DTL_LINE_NUM_LEN));

                        for (String xxMsgId : xxMsgIdList) {
                            String errorApi = "NPZC110001:" + S21MessageFunc.clspGetMessage(xxMsgId) + " " + subMsg;
                            S21InfoLogOutput.println(errorApi);
                            apiErrMap.put(xxMsgId, errorApi);
                        }
                        continue;

                    } else if (pMsg.poDetailOutList.getValidCount() > 0 && ZYPCommonFunc.hasValue(pMsg.poDetailOutList.no(0).xxMsgId)) {
                        StringBuilder subMsg = new StringBuilder("EDI PO#=").append(asnHdrTMsg.ediPoOrdNum.getValue());
                        subMsg.append(", EDI Line#=").append(getSubMsgItemValue(asnDtlTMsg.ediPoOrdDtlLineNum.getValue(), EDI_PO_ORD_DTL_LINE_NUM_LEN));
                        subMsg.append(", CSA MDSE_CD#=").append(csaMdseCd);
                        String errorApi = "NPZC110001:" + S21MessageFunc.clspGetMessage(pMsg.poDetailOutList.no(0).xxMsgId.getValue()) + " "  + subMsg;
                        S21InfoLogOutput.println(errorApi);
                        apiErrMap.put(csaMdseCd, errorApi);
                        continue;

                    } else {
                        apiAllErr = false;
                        mdseCd = csaMdseCd;
                        break;
                    }

                } else {
                    continue;
                }
            }

            if (apiAllErr || pMsg == null) {
                // QC#30696
                StringBuilder subMsg = new StringBuilder();
                if (apiErrMap.isEmpty()) {
                    String xxMsgId = NPZM0156E;
                    subMsg.append("EDI PO#=").append(asnHdrTMsg.ediPoOrdNum.getValue());
                    subMsg.append(", EDI Line#=").append(getSubMsgItemValue(asnDtlTMsg.ediPoOrdDtlLineNum.getValue(), EDI_PO_ORD_DTL_LINE_NUM_LEN));
                    String errorApi = S21MessageFunc.clspGetMessage(xxMsgId) + subMsg;
                    addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                    addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                } else {
                    for (String msg : apiErrMap.values()) {
                        String errorApi = msg;
                        addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                        addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                    }
                }
                isErr = true;
                continue;
            }

            // PO Detail Check
            List<Map<String, Object>> poDtlList = null;
            boolean isDltErr = false;

            if (!ZYPCommonFunc.hasValue(pMsg.poDetailOutList.no(0).poOrdDtlLineNum)) {

                StringBuilder subMsg = new StringBuilder("EDI PO#=").append(asnHdrTMsg.ediPoOrdNum.getValue());
                subMsg.append(", EDI Line#=").append(getSubMsgItemValue(asnDtlTMsg.ediPoOrdDtlLineNum.getValue(), EDI_PO_ORD_DTL_LINE_NUM_LEN));
                subMsg.append(", PO#=").append(asnHdrTMsg.poOrdNum.getValue());
                subMsg.append(", PO Line#=").append(getSubMsgItemValue(asnDtlTMsg.poOrdDtlLineNum.getValue(), PO_ORD_DTL_LINE_NUM_LEN));
                subMsg.append(", Mdse Cd=").append(asnDtlTMsg.mdseCd.getValue());
                //QC#28968 Update error message.
                addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1637E, new String[] {subMsg.toString() }));

                isErr = true;

                continue;

            } else {

                paramMap = new HashMap<String, Object>();
                paramMap.put(BIND_GLBL_CMPY_CD, globalCompanyCode);
                paramMap.put(BIND_PO_ORD_NUM, pMsg.poOrdNum_O.getValue());
                paramMap.put(BIND_PO_ORD_DTL_LINE_NUM, pMsg.poDetailOutList.no(0).poOrdDtlLineNum.getValue());

                poDtlList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPoDtl", paramMap);

                // START 04/27/2020 T.Ogura [QC#56546,ADD]
                if (poDtlList == null || poDtlList.isEmpty()) {
                    poDtlList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPoDtlAgain", paramMap);
                }
                // END   04/27/2020 T.Ogura [QC#56546,ADD]

                if (poDtlList == null || poDtlList.isEmpty() || poDtlList.size() > 1) {

                    StringBuilder subMsg = new StringBuilder("EDI PO#=").append(asnHdrTMsg.ediPoOrdNum.getValue());
                    subMsg.append(", EDI Line#=").append(getSubMsgItemValue(asnDtlTMsg.ediPoOrdDtlLineNum.getValue(), EDI_PO_ORD_DTL_LINE_NUM_LEN));
                    subMsg.append(", PO#=").append(asnHdrTMsg.poOrdNum.getValue());
                    subMsg.append(", PO Line#=").append(getSubMsgItemValue(asnDtlTMsg.poOrdDtlLineNum.getValue(), PO_ORD_DTL_LINE_NUM_LEN));
                    subMsg.append(", Mdse Cd=").append(asnDtlTMsg.mdseCd.getValue());
                    //QC#28968 Update error message.
                    addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1637E, new String[] {subMsg.toString() }));

                    isErr = true;

                    continue;

                } else {

                    // update Mdse Code
                    // QC#52799
                    if (ZYPCommonFunc.hasValue(asnDtlTMsg.mdseCdUpdFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(asnDtlTMsg.mdseCdUpdFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(asnDtlTMsg.mdseCd, mdseCd);
                    } else {
                        ZYPEZDItemValueSetter.setValue(asnDtlTMsg.mdseCd, (String) poDtlList.get(0).get(PD_MDSE_CD));
                    }

                    ZYPEZDItemValueSetter.setValue(asnDtlTMsg.poOrdDtlLineNum, (String) poDtlList.get(0).get(PD_PO_ORD_DTL_LINE_NUM));

                    EZDTBLAccessor.update(asnDtlTMsg);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(asnDtlTMsg.getReturnCode())) {
                        addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1003E, new String[] {"EDI_ASN_DTL_WRK", "ASN_SO_NUM, ASN_LINE_NUM",
                                asnDtlTMsg.asnSoNum.getValue() + ", " + asnDtlTMsg.asnLineNum.getValue() }));
                        return false;
                    }

                    // START 2023/07/18 TZ.Win [QC#61614, ADD]
                    String poLineStsCd = (String) poDtlList.get(0).get(PD_PO_LINE_STS_CD);
                    if (PO_LINE_STS.OPEN_FOR_INVOICE.equals(poLineStsCd) || PO_LINE_STS.CLOSED.equals(poLineStsCd)) {

                        isProcessedwithWarning = true;
                        continue;
                    } else if (isHeaderClosed) {

                        StringBuilder subMsg = new StringBuilder("EDI PO#=").append(asnHdrTMsg.ediPoOrdNum.getValue());
                        StringBuilder subMsgAddPo = new StringBuilder(subMsg).append(", PO#=").append(asnHdrTMsg.poOrdNum.getValue());
                        addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1201E, new String[] {subMsgAddPo.toString() }));

                        isErr = true;
                        continue;
                    }
                    // END 2023/07/18 TZ.Win [QC#61614, ADD]

                    // QC#19664 Start
                    if (asnDtlTMsg.asnMdseCd.getValue().equals((String) poDtlList.get(0).get(MDSE_CD))) {
                        if (ZYPCommonFunc.hasValue(pMsg.poDetailOutList.no(0).xxMsgId)) {
                            String poMdseCd = (String) poDtlList.get(0).get(PD_MDSE_CD);
                            StringBuilder subMsg = new StringBuilder("EDI PO#=").append(asnHdrTMsg.ediPoOrdNum.getValue());
                            subMsg.append(", EDI Line#=").append(getSubMsgItemValue(asnDtlTMsg.ediPoOrdDtlLineNum.getValue(), EDI_PO_ORD_DTL_LINE_NUM_LEN));
                            subMsg.append(", PO#=").append(asnHdrTMsg.poOrdNum.getValue());
                            subMsg.append(", PO Line#=").append(getSubMsgItemValue(asnDtlTMsg.poOrdDtlLineNum.getValue(), PO_ORD_DTL_LINE_NUM_LEN));
                            subMsg.append(", Mdse Cd=").append(asnDtlTMsg.mdseCd.getValue());
                            addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1290E, new String[] {subMsg.toString() + ", PO Mdse Cd=" + poMdseCd }));

                            isDltErr = true;
                        }
                    }
                    // QC#19664 End
                }
            }

            // EDI_ASN_SER_NUM_WRK
            // ****************************************************************
            for (EDI_ASN_SER_NUM_WRKTMsg ediAsnSerNumTMsg : asnSerList) {

                if (ediAsnSerNumTMsg.asnSoSlpNum.getValue().equals(asnDtlTMsg.asnSoSlpNum.getValue())) {

                    ZYPEZDItemValueSetter.setValue(ediAsnSerNumTMsg.mdseCd, mdseCd);
                    ZYPEZDItemValueSetter.setValue(ediAsnSerNumTMsg.poOrdNum, asnHdrTMsg.poOrdNum);
                    ZYPEZDItemValueSetter.setValue(ediAsnSerNumTMsg.poOrdDtlLineNum, asnDtlTMsg.poOrdDtlLineNum);

                    EZDTBLAccessor.update(ediAsnSerNumTMsg);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ediAsnSerNumTMsg.getReturnCode())) {

                        addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1003E, new String[] {"EDI_ASN_SER_NUM_WRK", "ASN_MDSE_CD, SO_NUM, SO_SER_ID",
                                ediAsnSerNumTMsg.asnMdseCd.getValue() + ", " + ediAsnSerNumTMsg.asnSoNum.getValue() + ", " + ediAsnSerNumTMsg.soSerId.getValue() }));
                        return false;

                    }
                }
            }

            StringBuilder subMsg = new StringBuilder("EDI PO#=").append(asnHdrTMsg.ediPoOrdNum.getValue());
            subMsg.append(", EDI Line#=").append(getSubMsgItemValue(asnDtlTMsg.ediPoOrdDtlLineNum.getValue(), EDI_PO_ORD_DTL_LINE_NUM_LEN));
            subMsg.append(", PO#=").append(asnHdrTMsg.poOrdNum.getValue());
            subMsg.append(", PO Line#=").append(getSubMsgItemValue(asnDtlTMsg.poOrdDtlLineNum.getValue(), PO_ORD_DTL_LINE_NUM_LEN));
            subMsg.append(", Mdse Cd=").append(asnDtlTMsg.mdseCd.getValue());

            // check Mdse
            // ****************************************************************
            String asnMdseCd = asnDtlTMsg.mdseCd.getValue();

            if (!existMdse(asnMdseCd)) {
                addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1176E, new String[] {subMsg.toString() }));
                isDltErr = true;
            }

            // check Asn Qty
            // ****************************************************************
            if (!ZYPCommonFunc.hasValue(asnDtlTMsg.asnQty)) {
                addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1291E, new String[] {subMsg.toString(), null }));
                isDltErr = true;

            } else if (BigDecimal.ZERO.compareTo(asnDtlTMsg.asnQty.getValue()) > 0) {
                addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1291E, new String[] {subMsg.toString(), asnDtlTMsg.asnQty.getValue().toString() }));
                isDltErr = true;
            }

            // check po status
            // ****************************************************************
            String poStsCd = (String) poDtlList.get(0).get(PD_PO_STS_CD);

            if (ZYPCommonFunc.hasValue(poStsCd)) {

                if (PO_STS.CLOSED.equals(poStsCd)) {

                    addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1201E, new String[] {subMsg.toString() }));
                    isDltErr = true;

                } else if (PO_STS.CANCELLED.equals(poStsCd)) {

                    addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1285E, new String[] {subMsg.toString() }));
                    isDltErr = true;

                } else if (PO_STS.SAVED.equals(poStsCd)) {

                    addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPZM0080E) + "[" + subMsg.toString() + "]");
                    isDltErr = true;

                } else if (PO_STS.WAITING_FOR_APPROVAL.equals(poStsCd)) {

                    addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPZM0081E) + "[" + subMsg.toString() + "]");
                    isDltErr = true;
                }

            } else {

                addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1286E, new String[] {subMsg.toString() }));
                isDltErr = true;

            }

            if (isDltErr) {
                isErr = true;
            }
        }
        // QC#25146 End
        return isErr;
    }

    /**
     * lockTables
     * @param asnHdrTMsg
     * @param asnDtlQueryMap
     */
    private void lockTables(Map<String, Object> asnDtlQueryMap, POTMsg poTMsg, PO_DTLTMsg poDtlTMsg) {

        // lock PO
        ZYPEZDItemValueSetter.setValue(poTMsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, (String) asnDtlQueryMap.get(ED_PO_ORD_NUM));
        poTMsg = (POTMsg) EZDTBLAccessor.findByKeyForUpdateWait((poTMsg));

        // lock PO_DTL
        ZYPEZDItemValueSetter.setValue(poDtlTMsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(poDtlTMsg.poOrdNum, (String) asnDtlQueryMap.get(ED_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(poDtlTMsg.poOrdDtlLineNum, (String) asnDtlQueryMap.get(ED_PO_ORD_DTL_LINE_NUM));
        poDtlTMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait((poDtlTMsg));

    }

    /**
     * existMdse
     * @param mdseCd
     * @return
     */
    private boolean existMdse(String mdseCd) {

        boolean existFlag = false;

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_MDSE_CD, mdseCd);

        String result = (String) ssmBatchClient.queryObject("getMdse", paramMap);

        if (ZYPCommonFunc.hasValue(result)) {
            existFlag = true;
        }

        return existFlag;

    }

    /**
     * addPoInfo
     * @param pMsg
     * @param inMap
     */
    private int addPoInfo(NPZC104001PMsg pMsg, Map<String, Object> inMap, List<Map<String, Object>> poMsgList, int k, List<EDI_ASN_SER_NUM_WRKTMsg> asnSerList) {

        if (k == 0) {
            // header
            // ************************************************************************
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.globalCompanyCode);
            // QC#24780
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC104001Constant.MODE_ASN);
            ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC104001Constant.EVENT_SUBMIT);
            ZYPEZDItemValueSetter.setValue(pMsg.procDt, this.salesDate);
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, (String) inMap.get(PD_PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.dsPoTpCd, (String) inMap.get(DP_DS_PO_TP_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.dsPoTpNm, (String) inMap.get(DP_DS_PO_TP_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.poQlfyCd, (String) inMap.get(DP_PO_QLFY_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poSubmtPsnCd, (String) inMap.get(PO_PO_SUBMT_PSN_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poSubmtTs, (String) inMap.get(PO_PO_SUBMT_TS));
            ZYPEZDItemValueSetter.setValue(pMsg.poApvlStsCd, (String) inMap.get(DP_PO_APVL_STS_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poApvlPsnCd, (String) inMap.get(PO_PO_APVL_PSN_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poApvlDt, (String) inMap.get(PO_PO_APVL_DT));
            ZYPEZDItemValueSetter.setValue(pMsg.poApvlTs, (String) inMap.get(PO_PO_APVL_TS));
            ZYPEZDItemValueSetter.setValue(pMsg.destRtlWhCd, (String) inMap.get(DP_DEST_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.srcRtlWhCd, (String) inMap.get(DP_SRC_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdSrcCd, (String) inMap.get(PO_PO_ORD_SRC_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, (String) inMap.get(DP_PRNT_VND_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.prntVndNm, (String) inMap.get(DP_PRNT_VND_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.vndCd, (String) inMap.get(PO_VND_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.vndNm, (String) inMap.get(PO_VND_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.dealCcyCd, (String) inMap.get(PD_CCY_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.vndDropShipFlg, (String) inMap.get(PO_VND_DROP_SHIP_FLG));
            ZYPEZDItemValueSetter.setValue(pMsg.prchGrpCd, (String) inMap.get(DPD_PRCH_GRP_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.vndPmtTermCd, (String) inMap.get(DP_VND_PMT_TERM_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.vndPmtTermDescTxt, (String) inMap.get(DP_VND_PMT_TERM_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(pMsg.rqstTechTocCd, (String) inMap.get(DP_RQST_TECH_TOC_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.rqstRcvDt, (String) inMap.get(DPD_RQST_RCV_DT));
            ZYPEZDItemValueSetter.setValue(pMsg.rqstRcvTm, (String) inMap.get(DP_RQST_RCV_TM));
            // START 2023/07/20 S.Dong [QC#61638, ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.rqstShipDt, (String) inMap.get(DP_RQST_SHIP_DT));
            // END 2023/07/20 S.Dong [QC#61638, ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, (String) inMap.get(PD_SHIP_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, (String) inMap.get(PD_SHIP_TO_LOC_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToAcctNm, (String) inMap.get(DPD_SHIP_TO_ACCT_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToAddlLocNm, (String) inMap.get(PD_SHIP_TO_ADDL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, (String) inMap.get(PD_SHIP_TO_FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, (String) inMap.get(PD_SHIP_TO_SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, (String) inMap.get(PD_SHIP_TO_THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, (String) inMap.get(PD_SHIP_TO_FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstRefCmntTxt, (String) inMap.get(PD_SHIP_TO_FIRST_REF_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdRefCmntTxt, (String) inMap.get(PD_SHIP_TO_SCD_REF_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, (String) inMap.get(PD_SHIP_TO_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, (String) inMap.get(PD_SHIP_TO_ST_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, (String) inMap.get(PD_SHIP_TO_PROV_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, (String) inMap.get(PD_SHIP_TO_CNTY_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, (String) inMap.get(PD_SHIP_TO_POST_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, (String) inMap.get(PD_SHIP_TO_CTRY_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, (String) inMap.get(DPD_CTAC_PSN_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.rtrnShipToRtlWhCd, (String) inMap.get(DP_RTRN_SHIP_TO_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.shipFromSoNumListTxt, (String) inMap.get(DP_SHIP_FROM_SO_NUM_LIST_TXT));
            ZYPEZDItemValueSetter.setValue(pMsg.carrCd, (String) inMap.get(PD_CARR_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, (String) inMap.get(DP_CARR_ACCT_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd, (String) inMap.get(PD_SHPG_SVC_LVL_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.frtChrgToCd, (String) inMap.get(PD_FRT_CHRG_TO_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.frtChrgMethCd, (String) inMap.get(PD_FRT_CHRG_METH_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, (String) inMap.get(DP_LINE_BIZ_TP_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdCmntTxt, (String) inMap.get(PO_PO_ORD_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(pMsg.trsmtMethTpCd, (String) inMap.get(DP_TRSMT_METH_TP_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.sendPoFaxNum, (String) inMap.get(DP_SEND_PO_FAX_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.sendPoEmlAddr, (String) inMap.get(DP_SEND_PO_EML_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.poSendFlg, (String) inMap.get(PO_PO_SEND_FLG));
            ZYPEZDItemValueSetter.setValue(pMsg.poSendTs, (String) inMap.get(DP_PO_SEND_TS));
            ZYPEZDItemValueSetter.setValue(pMsg.poPrintFlg, (String) inMap.get(PO_PO_PRINT_FLG));
            ZYPEZDItemValueSetter.setValue(pMsg.dsctnInd, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pMsg.wfFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pMsg.vndIssOrdNum, (String) inMap.get(PO_VND_ISS_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.eipRptRqstPk, (BigDecimal) inMap.get(DP_EIP_RPT_RQST_PK));

            // poInfo
            // ************************************************************************

            int poInfoIndex = 0;
            for (int i = 0; i < poMsgList.size(); i++) {
                Map<String, Object> poMsg = poMsgList.get(i);
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(i).poMsgPk, (BigDecimal) poMsg.get(PM_PO_MSG_PK));
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(i).poMsgTpCd, (String) poMsg.get(PM_PO_MSG_TP_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(i).poMsgSubmtPsnCd, (String) poMsg.get(PM_PO_MSG_SUBMT_PSN_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(i).prchReqNum, (String) poMsg.get(PM_PRCH_REQ_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(i).prchReqLineNum, (String) poMsg.get(PM_PRCH_REQ_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(i).prchReqLineSubNum, (BigDecimal) poMsg.get(PM_PRCH_REQ_LINE_SUB_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(i).xxDsMultMsgDplyTxt, (String) poMsg.get(PM_PO_MSG_TXT));
                poInfoIndex++;
            }
            pMsg.poInfo.setValidCount(poInfoIndex);
        }

        // poLineInfo
        // ************************************************************************
        int poLineInfoIndex = pMsg.poLineInfo.getValidCount();

        BigDecimal calSplitLineCnt = (BigDecimal) inMap.get("CAL_SPLIT_LINE_COUNT");
        BigDecimal splitLineNum = (BigDecimal) inMap.get("SPLIT_LINE_NUM");

        if (ZYPConstant.FLG_ON_Y.equals(inMap.get(CAL_SPLIT_FLAG))) {

            if (ZYPConstant.FLG_ON_Y.equals(inMap.get(D_MDSE_CD_UPD_FLG))) {

                BigDecimal asnQty = (BigDecimal) inMap.get(D_ASN_QTY);
                BigDecimal totAsnQty = (BigDecimal) inMap.get("TOT_SHIP_QTY");
                BigDecimal poQty = (BigDecimal) inMap.get(PD_PO_QTY);
                // QC#54373 Start
                BigDecimal origPoQty = (BigDecimal) inMap.get(PD_PO_QTY);

                if (calSplitLineCnt.compareTo(splitLineNum) == 0) {
                    if (poQty.compareTo(totAsnQty) > 0) {
                        poQty = poQty.subtract(totAsnQty);
                        // update original line. Qty change
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlLineNum, (String) inMap.get(ED_PO_ORD_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslMdseCd, (String) inMap.get(DPD_ASL_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseCd, (String) inMap.get(PD_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poQty, poQty);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispQty, poQty);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poLineTpCd, (String) inMap.get(DPD_PO_LINE_TP_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMdseCmpsnTpCd, (String) inMap.get(DPD_PO_MDSE_CMPSN_TP_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).setPoOrdDtlLineNum, (String) inMap.get(DPD_SET_PO_ORD_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseDescShortTxt, (String) inMap.get(DPD_MDSE_DESC_SHORT_TXT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poInvQty, (BigDecimal) inMap.get(DPD_PO_INV_QTY));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispUomCd, (String) inMap.get(DPD_PO_DISP_UOM_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).thisMthFobCostAmt, (BigDecimal) inMap.get(PD_THIS_MTH_FOB_COST_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entDealNetUnitPrcAmt, (BigDecimal) inMap.get(DPD_ENT_DEAL_NET_UNIT_PRC_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlDealNetAmt, (BigDecimal) inMap.get(DPD_ENT_PO_DTL_DEAL_NET_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entFuncNetUnitPrcAmt, (BigDecimal) inMap.get(DPD_ENT_FUNC_NET_UNIT_PRC_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlFuncNetAmt, (BigDecimal) inMap.get(DPD_ENT_PO_DTL_FUNC_NET_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).exchRate, (BigDecimal) inMap.get(DPD_EXCH_RATE));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custUomCd, (String) inMap.get(PD_CUST_UOM_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).destRtlSwhCd, (String) inMap.get(DPD_DEST_RTL_SWH_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).srcRtlSwhCd, (String) inMap.get(DPD_SRC_RTL_SWH_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).invtyLocCd, (String) inMap.get(DPD_INVTY_LOC_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvDt, (String) inMap.get(DPD_RQST_RCV_DT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvTm, (String) inMap.get(DPD_RQST_RCV_TM));
                        // START 2023/07/20 S.Dong [QC#61638, ADD]
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstShipDt, (String) inMap.get(DPD_RQST_SHIP_DT));
                        // END 2023/07/20 S.Dong [QC#61638, ADD]
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).frtCondCd, (String) inMap.get(DPD_FRT_COND_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origMdseCd, (String) inMap.get(DPD_ORIG_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).fromStkStsCd, (String) inMap.get(DPD_FROM_STK_STS_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).toStkStsCd, (String) inMap.get(DPD_TO_STK_STS_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).adminPsnCd, (String) inMap.get(PD_ADMIN_PSN_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMatchTpCd, (String) inMap.get(DPD_PO_MATCH_TP_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).ordQty, (BigDecimal) inMap.get(PD_ORD_QTY));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdNum, (String) inMap.get(PD_CPO_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineNum, (String) inMap.get(PD_CPO_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineSubNum, (String) inMap.get(PD_CPO_DTL_LINE_SUB_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoNum, (String) inMap.get(PD_CUST_ISS_PO_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoDt, (String) inMap.get(PD_CUST_ISS_PO_DT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdTpCd, (String) inMap.get(PD_CPO_ORD_TP_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).billToCustCd, (String) inMap.get(PD_BILL_TO_CUST_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).sellToCustCd, (String) inMap.get(PD_SELL_TO_CUST_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shpgPlnNum, (String) inMap.get(PD_SHPG_PLN_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqNum, (String) inMap.get(DPD_PRCH_REQ_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineNum, (String) inMap.get(DPD_PRCH_REQ_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineSubNum, (BigDecimal) inMap.get(DPD_PRCH_REQ_LINE_SUB_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefNum, (String) inMap.get(DPD_TRX_REF_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineNum, (String) inMap.get(DPD_TRX_REF_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineSubNum, (String) inMap.get(DPD_TRX_REF_LINE_SUB_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslDtlPk, (BigDecimal) inMap.get(DPD_ASL_DTL_PK));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslUnitPrcAmt, (BigDecimal) inMap.get(DPD_ASL_UNIT_PRC_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shipFromSoNumListTxt, (String) inMap.get(DPD_SHIP_FROM_SO_NUM_LIST_TXT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndInvtyLocCd, (String) inMap.get(DPD_VND_INVTY_LOC_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndIssPoOrdNum, (String) inMap.get(DPD_VND_ISS_PO_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).proNum, (String) inMap.get(DPD_PRO_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndPoAckStsCd, (String) inMap.get(DPD_VND_PO_ACK_STS_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdNum, (String) inMap.get(DPD_ORIG_PO_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdDtlLineNum, (String) inMap.get(DPD_ORIG_PO_ORD_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origDispPoDtlLineNum, (String) inMap.get(DPD_ORIG_DISP_PO_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).svcConfigMstrPk, (BigDecimal) inMap.get(DPD_SVC_CONFIG_MSTR_PK));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poSendTs, (String) inMap.get(DPD_PO_SEND_TS));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlCmntTxt, (String) inMap.get(PD_PO_ORD_DTL_CMNT_TXT));
                        pMsg.poLineInfo.setValidCount(++poLineInfoIndex);
                        k = k + 1;
                    } else {
                        poQty = totAsnQty;
                        // update original line. Item Change
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlLineNum, (String) inMap.get(ED_PO_ORD_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslMdseCd, (String) inMap.get(ED_ASN_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseCd, (String) inMap.get(ED_ASN_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poQty, poQty);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispQty, poQty);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poLineTpCd, (String) inMap.get(DPD_PO_LINE_TP_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMdseCmpsnTpCd, (String) inMap.get(DPD_PO_MDSE_CMPSN_TP_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).setPoOrdDtlLineNum, (String) inMap.get(DPD_SET_PO_ORD_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseDescShortTxt, (String) inMap.get(DPD_MDSE_DESC_SHORT_TXT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poInvQty, (BigDecimal) inMap.get(DPD_PO_INV_QTY));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispUomCd, (String) inMap.get(DPD_PO_DISP_UOM_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).thisMthFobCostAmt, (BigDecimal) inMap.get(PD_THIS_MTH_FOB_COST_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entDealNetUnitPrcAmt, (BigDecimal) inMap.get(DPD_ENT_DEAL_NET_UNIT_PRC_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlDealNetAmt, (BigDecimal) inMap.get(DPD_ENT_PO_DTL_DEAL_NET_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entFuncNetUnitPrcAmt, (BigDecimal) inMap.get(DPD_ENT_FUNC_NET_UNIT_PRC_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlFuncNetAmt, (BigDecimal) inMap.get(DPD_ENT_PO_DTL_FUNC_NET_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).exchRate, (BigDecimal) inMap.get(DPD_EXCH_RATE));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custUomCd, (String) inMap.get(PD_CUST_UOM_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).destRtlSwhCd, (String) inMap.get(DPD_DEST_RTL_SWH_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).srcRtlSwhCd, (String) inMap.get(DPD_SRC_RTL_SWH_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).invtyLocCd, (String) inMap.get(DPD_INVTY_LOC_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvDt, (String) inMap.get(DPD_RQST_RCV_DT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvTm, (String) inMap.get(DPD_RQST_RCV_TM));
                        // START 2023/07/20 S.Dong [QC#61638, ADD]
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstShipDt, (String) inMap.get(DPD_RQST_SHIP_DT));
                        // END 2023/07/20 S.Dong [QC#61638, ADD]
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).frtCondCd, (String) inMap.get(DPD_FRT_COND_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origMdseCd, (String) inMap.get(DPD_ORIG_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).fromStkStsCd, (String) inMap.get(DPD_FROM_STK_STS_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).toStkStsCd, (String) inMap.get(DPD_TO_STK_STS_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).adminPsnCd, (String) inMap.get(PD_ADMIN_PSN_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMatchTpCd, (String) inMap.get(DPD_PO_MATCH_TP_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).ordQty, (BigDecimal) inMap.get(PD_ORD_QTY));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdNum, (String) inMap.get(PD_CPO_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineNum, (String) inMap.get(PD_CPO_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineSubNum, (String) inMap.get(PD_CPO_DTL_LINE_SUB_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoNum, (String) inMap.get(PD_CUST_ISS_PO_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoDt, (String) inMap.get(PD_CUST_ISS_PO_DT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdTpCd, (String) inMap.get(PD_CPO_ORD_TP_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).billToCustCd, (String) inMap.get(PD_BILL_TO_CUST_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).sellToCustCd, (String) inMap.get(PD_SELL_TO_CUST_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shpgPlnNum, (String) inMap.get(PD_SHPG_PLN_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqNum, (String) inMap.get(DPD_PRCH_REQ_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineNum, (String) inMap.get(DPD_PRCH_REQ_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineSubNum, (BigDecimal) inMap.get(DPD_PRCH_REQ_LINE_SUB_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefNum, (String) inMap.get(DPD_TRX_REF_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineNum, (String) inMap.get(DPD_TRX_REF_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineSubNum, (String) inMap.get(DPD_TRX_REF_LINE_SUB_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslDtlPk, (BigDecimal) inMap.get(DPD_ASL_DTL_PK));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslUnitPrcAmt, (BigDecimal) inMap.get(DPD_ASL_UNIT_PRC_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shipFromSoNumListTxt, (String) inMap.get(DPD_SHIP_FROM_SO_NUM_LIST_TXT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndInvtyLocCd, (String) inMap.get(DPD_VND_INVTY_LOC_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndIssPoOrdNum, (String) inMap.get(DPD_VND_ISS_PO_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).proNum, (String) inMap.get(DPD_PRO_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndPoAckStsCd, (String) inMap.get(DPD_VND_PO_ACK_STS_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdNum, (String) inMap.get(DPD_ORIG_PO_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdDtlLineNum, (String) inMap.get(DPD_ORIG_PO_ORD_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origDispPoDtlLineNum, (String) inMap.get(DPD_ORIG_DISP_PO_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).svcConfigMstrPk, (BigDecimal) inMap.get(DPD_SVC_CONFIG_MSTR_PK));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poSendTs, (String) inMap.get(DPD_PO_SEND_TS));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlCmntTxt, (String) inMap.get(PD_PO_ORD_DTL_CMNT_TXT));
                        pMsg.poLineInfo.setValidCount(++poLineInfoIndex);
                        k = k + 1;
                    }

                    if (origPoQty.compareTo(totAsnQty) != 0) {
                        // create new line
                        String nextPoOrdDtlLineNum = getNextPoOrdDtlLineNum((String) inMap.get(PD_PO_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlLineNum, nextPoOrdDtlLineNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslMdseCd, (String) inMap.get(ED_ASN_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseCd, (String) inMap.get(ED_ASN_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poQty, asnQty);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispQty, asnQty);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).invtyLocCd, (String) inMap.get(DPD_INVTY_LOC_CD));

                        updateEdiAsnDtlWrk(inMap, nextPoOrdDtlLineNum);
                    } else {
                        updateEdiAsnDtlWrk(inMap, (String) inMap.get(ED_PO_ORD_DTL_LINE_NUM));
                        return k;
                    }

                } else if (poQty.compareTo(totAsnQty) != 0) {

                    // create new line
                    String nextPoOrdDtlLineNum = getNextPoOrdDtlLineNum((String) inMap.get(PD_PO_ORD_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlLineNum, nextPoOrdDtlLineNum);
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslMdseCd, (String) inMap.get(ED_ASN_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseCd, (String) inMap.get(ED_ASN_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poQty, asnQty);
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispQty, asnQty);
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).invtyLocCd, (String) inMap.get(DPD_INVTY_LOC_CD));

                    updateEdiAsnDtlWrk(inMap, nextPoOrdDtlLineNum);
                } else {

                    updateEdiAsnDtlWrk(inMap, (String) inMap.get(ED_PO_ORD_DTL_LINE_NUM));
                    return k;
                }
// 
//            } else if (calSplitLineCnt.compareTo(splitLineNum) == 0) {
//
//                BigDecimal totAsnQty = (BigDecimal) inMap.get(CAL_TOT_ASN_QTY);
//                BigDecimal poQty = (BigDecimal) inMap.get(PD_PO_QTY);
//
//                if (poQty.compareTo(totAsnQty) > 0) {
//                    poQty = poQty.subtract(totAsnQty);
//                } else {
//                    poQty = totAsnQty;
//                }
//
//                // update original line
//                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlLineNum, (String) inMap.get(ED_PO_ORD_DTL_LINE_NUM));
//                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslMdseCd, (String) inMap.get(DPD_ASL_MDSE_CD));
//                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseCd, (String) inMap.get(DPD_ASL_MDSE_CD));
//                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poQty, poQty);
//                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispQty, poQty);
//
//                updateEdiAsnDtlWrk(inMap, (String) inMap.get(ED_PO_ORD_DTL_LINE_NUM));
            } else {
                updateEdiAsnDtlWrk(inMap, (String) inMap.get(ED_PO_ORD_DTL_LINE_NUM));
                return k;
            }

        } else {

            if (ZYPConstant.FLG_ON_Y.equals(inMap.get(D_MDSE_CD_UPD_FLG))) {

                BigDecimal asnQty = (BigDecimal) inMap.get("TOT_SHIP_QTY");
                BigDecimal poQty = (BigDecimal) inMap.get(PD_PO_QTY);

                if (poQty.compareTo(asnQty) != 0) {

                    if (poQty.compareTo(asnQty) > 0) {
                        poQty = poQty.subtract(asnQty);
                    } else {
                        poQty = asnQty;
                    }

                    // update original line
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlLineNum, (String) inMap.get(ED_PO_ORD_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslMdseCd, (String) inMap.get(DPD_ASL_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseCd, (String) inMap.get(PD_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poQty, poQty);
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispQty, poQty);
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).dispPoDtlLineNum, (String) inMap.get(DPD_DISP_PO_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poLineTpCd, (String) inMap.get(DPD_PO_LINE_TP_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMdseCmpsnTpCd, (String) inMap.get(DPD_PO_MDSE_CMPSN_TP_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).setPoOrdDtlLineNum, (String) inMap.get(DPD_SET_PO_ORD_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseDescShortTxt, (String) inMap.get(DPD_MDSE_DESC_SHORT_TXT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poInvQty, (BigDecimal) inMap.get(DPD_PO_INV_QTY));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispUomCd, (String) inMap.get(DPD_PO_DISP_UOM_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).thisMthFobCostAmt, (BigDecimal) inMap.get(PD_THIS_MTH_FOB_COST_AMT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entDealNetUnitPrcAmt, (BigDecimal) inMap.get(DPD_ENT_DEAL_NET_UNIT_PRC_AMT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlDealNetAmt, (BigDecimal) inMap.get(DPD_ENT_PO_DTL_DEAL_NET_AMT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entFuncNetUnitPrcAmt, (BigDecimal) inMap.get(DPD_ENT_FUNC_NET_UNIT_PRC_AMT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlFuncNetAmt, (BigDecimal) inMap.get(DPD_ENT_PO_DTL_FUNC_NET_AMT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).exchRate, (BigDecimal) inMap.get(DPD_EXCH_RATE));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custUomCd, (String) inMap.get(PD_CUST_UOM_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).destRtlSwhCd, (String) inMap.get(DPD_DEST_RTL_SWH_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).srcRtlSwhCd, (String) inMap.get(DPD_SRC_RTL_SWH_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).invtyLocCd, (String) inMap.get(DPD_INVTY_LOC_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvDt, (String) inMap.get(DPD_RQST_RCV_DT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvTm, (String) inMap.get(DPD_RQST_RCV_TM));
                    // START 2023/07/20 S.Dong [QC#61638, ADD]
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstShipDt, (String) inMap.get(DPD_RQST_SHIP_DT));
                    // END 2023/07/20 S.Dong [QC#61638, ADD]
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).frtCondCd, (String) inMap.get(DPD_FRT_COND_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origMdseCd, (String) inMap.get(DPD_ORIG_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).fromStkStsCd, (String) inMap.get(DPD_FROM_STK_STS_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).toStkStsCd, (String) inMap.get(DPD_TO_STK_STS_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).adminPsnCd, (String) inMap.get(PD_ADMIN_PSN_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMatchTpCd, (String) inMap.get(DPD_PO_MATCH_TP_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).ordQty, (BigDecimal) inMap.get(PD_ORD_QTY));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdNum, (String) inMap.get(PD_CPO_ORD_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineNum, (String) inMap.get(PD_CPO_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineSubNum, (String) inMap.get(PD_CPO_DTL_LINE_SUB_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoNum, (String) inMap.get(PD_CUST_ISS_PO_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoDt, (String) inMap.get(PD_CUST_ISS_PO_DT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdTpCd, (String) inMap.get(PD_CPO_ORD_TP_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).billToCustCd, (String) inMap.get(PD_BILL_TO_CUST_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).sellToCustCd, (String) inMap.get(PD_SELL_TO_CUST_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shpgPlnNum, (String) inMap.get(PD_SHPG_PLN_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqNum, (String) inMap.get(DPD_PRCH_REQ_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineNum, (String) inMap.get(DPD_PRCH_REQ_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineSubNum, (BigDecimal) inMap.get(DPD_PRCH_REQ_LINE_SUB_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefNum, (String) inMap.get(DPD_TRX_REF_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineNum, (String) inMap.get(DPD_TRX_REF_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineSubNum, (String) inMap.get(DPD_TRX_REF_LINE_SUB_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslDtlPk, (BigDecimal) inMap.get(DPD_ASL_DTL_PK));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslUnitPrcAmt, (BigDecimal) inMap.get(DPD_ASL_UNIT_PRC_AMT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shipFromSoNumListTxt, (String) inMap.get(DPD_SHIP_FROM_SO_NUM_LIST_TXT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndInvtyLocCd, (String) inMap.get(DPD_VND_INVTY_LOC_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndIssPoOrdNum, (String) inMap.get(DPD_VND_ISS_PO_ORD_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).proNum, (String) inMap.get(DPD_PRO_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndPoAckStsCd, (String) inMap.get(DPD_VND_PO_ACK_STS_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdNum, (String) inMap.get(DPD_ORIG_PO_ORD_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdDtlLineNum, (String) inMap.get(DPD_ORIG_PO_ORD_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origDispPoDtlLineNum, (String) inMap.get(DPD_ORIG_DISP_PO_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).svcConfigMstrPk, (BigDecimal) inMap.get(DPD_SVC_CONFIG_MSTR_PK));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poSendTs, (String) inMap.get(DPD_PO_SEND_TS));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlCmntTxt, (String) inMap.get(PD_PO_ORD_DTL_CMNT_TXT));
                    pMsg.poLineInfo.setValidCount(++poLineInfoIndex);
                    k = k + 1;

                    // create new line
                    String nextPoOrdDtlLineNum = getNextPoOrdDtlLineNum((String) inMap.get(PD_PO_ORD_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlLineNum, nextPoOrdDtlLineNum);
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslMdseCd, (String) inMap.get(ED_ASN_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseCd, (String) inMap.get(ED_ASN_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poQty, (BigDecimal) inMap.get(D_ASN_QTY));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispQty, (BigDecimal) inMap.get(D_ASN_QTY));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).invtyLocCd, (String) inMap.get(DPD_INVTY_LOC_CD));

                    updateEdiAsnDtlWrk(inMap, nextPoOrdDtlLineNum);

                } else {

                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlLineNum, (String) inMap.get(ED_PO_ORD_DTL_LINE_NUM));
                    // QC#19664 Start
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslMdseCd, (String) inMap.get(ED_ASN_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseCd, (String) inMap.get(D_MDSE_CD));
                    // QC#19664 End
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poQty, (BigDecimal) inMap.get(D_ASN_QTY));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispQty, (BigDecimal) inMap.get(D_ASN_QTY));

                }

            } else {

                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlLineNum, (String) inMap.get(ED_PO_ORD_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslMdseCd, (String) inMap.get(DPD_ASL_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseCd, (String) inMap.get(ED_ASN_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poQty, (BigDecimal) inMap.get(D_ASN_QTY));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispQty, (BigDecimal) inMap.get(DPD_PO_DISP_QTY));

            }

        }
        // QC#54373 end
        // QC#28968 Update. delete setup dispPoDtlLineNum. DispPoDtlLineNum is set automatically.
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poLineTpCd, (String) inMap.get(DPD_PO_LINE_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMdseCmpsnTpCd, (String) inMap.get(DPD_PO_MDSE_CMPSN_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).setPoOrdDtlLineNum, (String) inMap.get(DPD_SET_PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseDescShortTxt, (String) inMap.get(DPD_MDSE_DESC_SHORT_TXT));
        // START 06/04/2020 T.Ogura [QC#56912,MOD]
//        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poInvQty, (BigDecimal) inMap.get(DPD_PO_INV_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poInvQty, BigDecimal.ZERO);
        // END   06/04/2020 T.Ogura [QC#56912,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispUomCd, (String) inMap.get(DPD_PO_DISP_UOM_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).thisMthFobCostAmt, (BigDecimal) inMap.get(PD_THIS_MTH_FOB_COST_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entDealNetUnitPrcAmt, (BigDecimal) inMap.get(DPD_ENT_DEAL_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlDealNetAmt, (BigDecimal) inMap.get(DPD_ENT_PO_DTL_DEAL_NET_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entFuncNetUnitPrcAmt, (BigDecimal) inMap.get(DPD_ENT_FUNC_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlFuncNetAmt, (BigDecimal) inMap.get(DPD_ENT_PO_DTL_FUNC_NET_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).exchRate, (BigDecimal) inMap.get(DPD_EXCH_RATE));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custUomCd, (String) inMap.get(PD_CUST_UOM_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).destRtlSwhCd, (String) inMap.get(DPD_DEST_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).srcRtlSwhCd, (String) inMap.get(DPD_SRC_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).invtyLocCd, (String) inMap.get(DPD_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvDt, (String) inMap.get(DPD_RQST_RCV_DT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvTm, (String) inMap.get(DPD_RQST_RCV_TM));
        // START 2023/07/20 S.Dong [QC#61638, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstShipDt, (String) inMap.get(DPD_RQST_SHIP_DT));
        // END 2023/07/20 S.Dong [QC#61638, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).frtCondCd, (String) inMap.get(DPD_FRT_COND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origMdseCd, (String) inMap.get(DPD_ORIG_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).fromStkStsCd, (String) inMap.get(DPD_FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).toStkStsCd, (String) inMap.get(DPD_TO_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).adminPsnCd, (String) inMap.get(PD_ADMIN_PSN_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMatchTpCd, (String) inMap.get(DPD_PO_MATCH_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).ordQty, (BigDecimal) inMap.get(PD_ORD_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdNum, (String) inMap.get(PD_CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineNum, (String) inMap.get(PD_CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineSubNum, (String) inMap.get(PD_CPO_DTL_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoNum, (String) inMap.get(PD_CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoDt, (String) inMap.get(PD_CUST_ISS_PO_DT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdTpCd, (String) inMap.get(PD_CPO_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).billToCustCd, (String) inMap.get(PD_BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).sellToCustCd, (String) inMap.get(PD_SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shpgPlnNum, (String) inMap.get(PD_SHPG_PLN_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqNum, (String) inMap.get(DPD_PRCH_REQ_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineNum, (String) inMap.get(DPD_PRCH_REQ_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineSubNum, (BigDecimal) inMap.get(DPD_PRCH_REQ_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefNum, (String) inMap.get(DPD_TRX_REF_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineNum, (String) inMap.get(DPD_TRX_REF_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineSubNum, (String) inMap.get(DPD_TRX_REF_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslDtlPk, (BigDecimal) inMap.get(DPD_ASL_DTL_PK));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslUnitPrcAmt, (BigDecimal) inMap.get(DPD_ASL_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shipFromSoNumListTxt, (String) inMap.get(DPD_SHIP_FROM_SO_NUM_LIST_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndInvtyLocCd, (String) inMap.get(DPD_VND_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndIssPoOrdNum, (String) inMap.get(DPD_VND_ISS_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).proNum, (String) inMap.get(DPD_PRO_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndPoAckStsCd, (String) inMap.get(DPD_VND_PO_ACK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdNum, (String) inMap.get(DPD_ORIG_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdDtlLineNum, (String) inMap.get(DPD_ORIG_PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origDispPoDtlLineNum, (String) inMap.get(DPD_ORIG_DISP_PO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).svcConfigMstrPk, (BigDecimal) inMap.get(DPD_SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poSendTs, (String) inMap.get(DPD_PO_SEND_TS));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlCmntTxt, (String) inMap.get(PD_PO_ORD_DTL_CMNT_TXT));
        pMsg.poLineInfo.setValidCount(++poLineInfoIndex);

        // serNumList
        // ************************************************************************
        // do nothing

        return ++k;

    }

    // QC#54373 Mod
    private int addSetPoInfo(NPZC104001PMsg pMsg, List<Map<String, Object>> asnDtlQueryList, List<Map<String, Object>> poMsgList, int k, List<EDI_ASN_SER_NUM_WRKTMsg> asnSerList) {
        // QC#54373 Start
        PO_DTLTMsg poDtlTMsg = null;
        String maxLineNum = "000";
        Map<String, Integer> addLineMap = new HashMap<String, Integer>();
        // poLineInfo
        // ************************************************************************
        int poLineInfoIndex = pMsg.poLineInfo.getValidCount();
        String setMdseLineNum = "";
        BigDecimal preSplitLineNum = BigDecimal.ZERO;
        boolean parentUpdFlg = true;

        for (int i = 0; i < asnDtlQueryList.size(); i++) {

            Map<String, Object> inMap = asnDtlQueryList.get(i);

            boolean needToSplitFlag = false;
            BigDecimal balQty = (BigDecimal) inMap.get(PD_PO_BAL_QTY);
            BigDecimal asnQty = (BigDecimal) inMap.get(D_ASN_QTY);

            // mdse split check
            if (ZYPConstant.FLG_OFF_N.equals(inMap.get(CAL_SPLIT_FLAG))) {

                // get UnReceived Qty(Partially Received & Unreceived)

                Map<String, Object> params = new HashMap<String, Object>();
                params.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
                params.put(BIND_PO_ORD_NUM, (String) inMap.get(PD_PO_ORD_NUM));
                params.put(BIND_PO_ORD_DTL_LINE_NUM, (String) inMap.get(DPD_ORIG_PO_ORD_DTL_LINE_NUM));
                params.put(BIND_SCE_ORD_TP_CD, NLXSceConst.SCE_ORD_TP_CD_DG);
                String[] stsCdAry = {RWS_STS.PRINTED, RWS_STS.RECEIVING };
                params.put(BIND_RWS_STS_CD, stsCdAry);

                Map<String, Object> unreceivedData = (Map<String, Object>) ssmBatchClient.queryObject("getRwsCreatedByAsnUnReceived", params);
                if (null != unreceivedData && unreceivedData.size() > 0) {
                    balQty = balQty.subtract((BigDecimal) unreceivedData.get(RWS_BAL_QTY));
                }

                // qty check
                if (ZYPConstant.FLG_ON_Y.equals(inMap.get("SET_MDSE_UPD_FLG")) && balQty.compareTo(asnQty) >= 0) {

                    needToSplitFlag = true;

                }

            } else {

                needToSplitFlag = true;

            }

            if (!needToSplitFlag) {
                continue;
            }

            if (k == 0) {
                // header
                // ************************************************************************
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.globalCompanyCode);
                // QC#24780
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC104001Constant.MODE_ASN);
                ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC104001Constant.EVENT_SUBMIT);
                ZYPEZDItemValueSetter.setValue(pMsg.procDt, this.salesDate);
                ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, (String) inMap.get(PD_PO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.dsPoTpCd, (String) inMap.get(DP_DS_PO_TP_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.dsPoTpNm, (String) inMap.get(DP_DS_PO_TP_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.poQlfyCd, (String) inMap.get(DP_PO_QLFY_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poSubmtPsnCd, (String) inMap.get(PO_PO_SUBMT_PSN_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poSubmtTs, (String) inMap.get(PO_PO_SUBMT_TS));
                ZYPEZDItemValueSetter.setValue(pMsg.poApvlStsCd, (String) inMap.get(DP_PO_APVL_STS_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poApvlPsnCd, (String) inMap.get(PO_PO_APVL_PSN_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poApvlDt, (String) inMap.get(PO_PO_APVL_DT));
                ZYPEZDItemValueSetter.setValue(pMsg.poApvlTs, (String) inMap.get(PO_PO_APVL_TS));
                ZYPEZDItemValueSetter.setValue(pMsg.destRtlWhCd, (String) inMap.get(DP_DEST_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.srcRtlWhCd, (String) inMap.get(DP_SRC_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poOrdSrcCd, (String) inMap.get(PO_PO_ORD_SRC_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, (String) inMap.get(DP_PRNT_VND_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.prntVndNm, (String) inMap.get(DP_PRNT_VND_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.vndCd, (String) inMap.get(PO_VND_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.vndNm, (String) inMap.get(PO_VND_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.dealCcyCd, (String) inMap.get(PD_CCY_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.vndDropShipFlg, (String) inMap.get(PO_VND_DROP_SHIP_FLG));
                ZYPEZDItemValueSetter.setValue(pMsg.prchGrpCd, (String) inMap.get(DPD_PRCH_GRP_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.vndPmtTermCd, (String) inMap.get(DP_VND_PMT_TERM_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.vndPmtTermDescTxt, (String) inMap.get(DP_VND_PMT_TERM_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(pMsg.rqstTechTocCd, (String) inMap.get(DP_RQST_TECH_TOC_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.rqstRcvDt, (String) inMap.get(DPD_RQST_RCV_DT));
                ZYPEZDItemValueSetter.setValue(pMsg.rqstRcvTm, (String) inMap.get(DP_RQST_RCV_TM));
                // START 2023/07/20 S.Dong [QC#61638, ADD]
                ZYPEZDItemValueSetter.setValue(pMsg.rqstShipDt, (String) inMap.get(DP_RQST_SHIP_DT));
                // END 2023/07/20 S.Dong [QC#61638, ADD]
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, (String) inMap.get(PD_SHIP_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, (String) inMap.get(PD_SHIP_TO_LOC_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToAcctNm, (String) inMap.get(DPD_SHIP_TO_ACCT_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToAddlLocNm, (String) inMap.get(PD_SHIP_TO_ADDL_LOC_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, (String) inMap.get(PD_SHIP_TO_FIRST_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, (String) inMap.get(PD_SHIP_TO_SCD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, (String) inMap.get(PD_SHIP_TO_THIRD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, (String) inMap.get(PD_SHIP_TO_FRTH_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstRefCmntTxt, (String) inMap.get(PD_SHIP_TO_FIRST_REF_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToScdRefCmntTxt, (String) inMap.get(PD_SHIP_TO_SCD_REF_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, (String) inMap.get(PD_SHIP_TO_CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, (String) inMap.get(PD_SHIP_TO_ST_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, (String) inMap.get(PD_SHIP_TO_PROV_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, (String) inMap.get(PD_SHIP_TO_CNTY_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, (String) inMap.get(PD_SHIP_TO_POST_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, (String) inMap.get(PD_SHIP_TO_CTRY_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, (String) inMap.get(DPD_CTAC_PSN_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.rtrnShipToRtlWhCd, (String) inMap.get(DP_RTRN_SHIP_TO_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.shipFromSoNumListTxt, (String) inMap.get(DP_SHIP_FROM_SO_NUM_LIST_TXT));
                ZYPEZDItemValueSetter.setValue(pMsg.carrCd, (String) inMap.get(PD_CARR_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, (String) inMap.get(DP_CARR_ACCT_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd, (String) inMap.get(PD_SHPG_SVC_LVL_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.frtChrgToCd, (String) inMap.get(PD_FRT_CHRG_TO_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.frtChrgMethCd, (String) inMap.get(PD_FRT_CHRG_METH_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, (String) inMap.get(DP_LINE_BIZ_TP_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poOrdCmntTxt, (String) inMap.get(PO_PO_ORD_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(pMsg.trsmtMethTpCd, (String) inMap.get(DP_TRSMT_METH_TP_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.sendPoFaxNum, (String) inMap.get(DP_SEND_PO_FAX_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.sendPoEmlAddr, (String) inMap.get(DP_SEND_PO_EML_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.poSendFlg, (String) inMap.get(PO_PO_SEND_FLG));
                ZYPEZDItemValueSetter.setValue(pMsg.poSendTs, (String) inMap.get(DP_PO_SEND_TS));
                ZYPEZDItemValueSetter.setValue(pMsg.poPrintFlg, (String) inMap.get(PO_PO_PRINT_FLG));
                ZYPEZDItemValueSetter.setValue(pMsg.dsctnInd, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(pMsg.wfFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(pMsg.vndIssOrdNum, (String) inMap.get(PO_VND_ISS_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.eipRptRqstPk, (BigDecimal) inMap.get(DP_EIP_RPT_RQST_PK));

                // poInfo
                // ************************************************************************

                int poInfoIndex = 0;
                for (int c = 0; c < poMsgList.size(); c++) {
                    // QC#18631 Mod Start
                    Map<String, Object> poMsg = poMsgList.get(c);
                    // QC#18631 Mod End
                    ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(c).poMsgPk, (BigDecimal) poMsg.get(PM_PO_MSG_PK));
                    ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(c).poMsgTpCd, (String) poMsg.get(PM_PO_MSG_TP_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(c).poMsgSubmtPsnCd, (String) poMsg.get(PM_PO_MSG_SUBMT_PSN_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(c).prchReqNum, (String) poMsg.get(PM_PRCH_REQ_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(c).prchReqLineNum, (String) poMsg.get(PM_PRCH_REQ_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(c).prchReqLineSubNum, (BigDecimal) poMsg.get(PM_PRCH_REQ_LINE_SUB_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(c).xxDsMultMsgDplyTxt, (String) poMsg.get(PM_PO_MSG_TXT));
                    poInfoIndex++;
                }
                pMsg.poInfo.setValidCount(poInfoIndex);
            }

            BigDecimal splitLineCnt = (BigDecimal) inMap.get("CAL_SPLIT_LINE_COUNT");
            BigDecimal splitLineNum = (BigDecimal) inMap.get("SPLIT_LINE_NUM");

            if (ZYPConstant.FLG_ON_Y.equals(inMap.get("SET_MDSE_UPD_FLG"))) {

                asnQty = (BigDecimal) inMap.get("TOT_SHIP_QTY");
                BigDecimal poQty = (BigDecimal) inMap.get(PD_PO_QTY);
                BigDecimal shipQty = (BigDecimal) inMap.get(D_ASN_QTY);

                if (poQty.compareTo(asnQty) != 0) {

                    if (poQty.compareTo(asnQty) > 0) {
                        poQty = poQty.subtract(asnQty);
                    } else {
                        poQty = asnQty;
                    }

                    // update original line
                    if (splitLineCnt.compareTo(splitLineNum) == 0) {
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlLineNum, (String) inMap.get(ED_PO_ORD_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslMdseCd, (String) inMap.get(DPD_ASL_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseCd, (String) inMap.get(DPD_ASL_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poQty, poQty);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispQty, poQty);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).dispPoDtlLineNum, (String) inMap.get(DPD_DISP_PO_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poLineTpCd, (String) inMap.get(DPD_PO_LINE_TP_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMdseCmpsnTpCd, (String) inMap.get(DPD_PO_MDSE_CMPSN_TP_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).setPoOrdDtlLineNum, (String) inMap.get(DPD_SET_PO_ORD_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseDescShortTxt, (String) inMap.get(DPD_MDSE_DESC_SHORT_TXT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poInvQty, (BigDecimal) inMap.get(DPD_PO_INV_QTY));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispUomCd, (String) inMap.get(DPD_PO_DISP_UOM_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).thisMthFobCostAmt, (BigDecimal) inMap.get(PD_THIS_MTH_FOB_COST_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entDealNetUnitPrcAmt, (BigDecimal) inMap.get(DPD_ENT_DEAL_NET_UNIT_PRC_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlDealNetAmt, (BigDecimal) inMap.get(DPD_ENT_PO_DTL_DEAL_NET_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entFuncNetUnitPrcAmt, (BigDecimal) inMap.get(DPD_ENT_FUNC_NET_UNIT_PRC_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlFuncNetAmt, (BigDecimal) inMap.get(DPD_ENT_PO_DTL_FUNC_NET_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).exchRate, (BigDecimal) inMap.get(DPD_EXCH_RATE));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custUomCd, (String) inMap.get(PD_CUST_UOM_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).destRtlSwhCd, (String) inMap.get(DPD_DEST_RTL_SWH_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).srcRtlSwhCd, (String) inMap.get(DPD_SRC_RTL_SWH_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).invtyLocCd, (String) inMap.get(DPD_INVTY_LOC_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvDt, (String) inMap.get(DPD_RQST_RCV_DT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvTm, (String) inMap.get(DPD_RQST_RCV_TM));
                        // START 2023/07/20 S.Dong [QC#61638, ADD]
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstShipDt, (String) inMap.get(DPD_RQST_SHIP_DT));
                        // END 2023/07/20 S.Dong [QC#61638, ADD]
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).frtCondCd, (String) inMap.get(DPD_FRT_COND_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origMdseCd, (String) inMap.get(DPD_ORIG_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).fromStkStsCd, (String) inMap.get(DPD_FROM_STK_STS_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).toStkStsCd, (String) inMap.get(DPD_TO_STK_STS_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).adminPsnCd, (String) inMap.get(PD_ADMIN_PSN_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMatchTpCd, (String) inMap.get(DPD_PO_MATCH_TP_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).ordQty, (BigDecimal) inMap.get(PD_ORD_QTY));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdNum, (String) inMap.get(PD_CPO_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineNum, (String) inMap.get(PD_CPO_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineSubNum, (String) inMap.get(PD_CPO_DTL_LINE_SUB_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoNum, (String) inMap.get(PD_CUST_ISS_PO_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoDt, (String) inMap.get(PD_CUST_ISS_PO_DT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdTpCd, (String) inMap.get(PD_CPO_ORD_TP_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).billToCustCd, (String) inMap.get(PD_BILL_TO_CUST_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).sellToCustCd, (String) inMap.get(PD_SELL_TO_CUST_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shpgPlnNum, (String) inMap.get(PD_SHPG_PLN_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqNum, (String) inMap.get(DPD_PRCH_REQ_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineNum, (String) inMap.get(DPD_PRCH_REQ_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineSubNum, (BigDecimal) inMap.get(DPD_PRCH_REQ_LINE_SUB_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefNum, (String) inMap.get(DPD_TRX_REF_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineNum, (String) inMap.get(DPD_TRX_REF_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineSubNum, (String) inMap.get(DPD_TRX_REF_LINE_SUB_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslDtlPk, (BigDecimal) inMap.get(DPD_ASL_DTL_PK));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslUnitPrcAmt, (BigDecimal) inMap.get(DPD_ASL_UNIT_PRC_AMT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shipFromSoNumListTxt, (String) inMap.get(DPD_SHIP_FROM_SO_NUM_LIST_TXT));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndInvtyLocCd, (String) inMap.get(DPD_VND_INVTY_LOC_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndIssPoOrdNum, (String) inMap.get(DPD_VND_ISS_PO_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).proNum, (String) inMap.get(DPD_PRO_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndPoAckStsCd, (String) inMap.get(DPD_VND_PO_ACK_STS_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdNum, (String) inMap.get(DPD_ORIG_PO_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdDtlLineNum, (String) inMap.get(DPD_ORIG_PO_ORD_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origDispPoDtlLineNum, (String) inMap.get(DPD_ORIG_DISP_PO_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).svcConfigMstrPk, (BigDecimal) inMap.get(DPD_SVC_CONFIG_MSTR_PK));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poSendTs, (String) inMap.get(DPD_PO_SEND_TS));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlCmntTxt, (String) inMap.get(PD_PO_ORD_DTL_CMNT_TXT));
                        pMsg.poLineInfo.setValidCount(++poLineInfoIndex);
                        if (addLineMap.get(inMap.get(ED_PO_ORD_DTL_LINE_NUM)) == null) {
                            updateEdiAsnDtlWrk(inMap, pMsg.poLineInfo.no(k).poOrdDtlLineNum.getValue());
                        } else {
                            updateEdiAsnDtlWrk(inMap, pMsg.poLineInfo.no(addLineMap.get(inMap.get(ED_PO_ORD_DTL_LINE_NUM))).poOrdDtlLineNum.getValue());
                            if (preSplitLineNum.compareTo(splitLineNum) != 0) {
                                poDtlTMsg = null;
                            }
                        }
                        k = k + 1;
//                        continue;
//                        poDtlTMsg = null;
                    } 

                    // Update Parent Set Line
                    if (poDtlTMsg == null || preSplitLineNum.compareTo(splitLineNum) != 0) {
                        poDtlTMsg = new PO_DTLTMsg();
                        ZYPEZDItemValueSetter.setValue(poDtlTMsg.glblCmpyCd, this.globalCompanyCode);
                        ZYPEZDItemValueSetter.setValue(poDtlTMsg.poOrdNum, (String) inMap.get(PD_PO_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(poDtlTMsg.poOrdDtlLineNum, (String) inMap.get(DPD_SET_PO_ORD_DTL_LINE_NUM));
                        poDtlTMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait((poDtlTMsg));

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
                        params.put("prntMdseCd", poDtlTMsg.mdseCd.getValue());
                        params.put("childMdseCd", (String) inMap.get(DPD_ORIG_MDSE_CD));

                        BigDecimal childQty = (BigDecimal) ssmBatchClient.queryObject("getCmpsnChildQty", params);
                        BigDecimal setQty = poDtlTMsg.poQty.getValue().subtract(asnQty.divide(childQty));

                        // update original parent line
                        if (parentUpdFlg) {
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlLineNum, poDtlTMsg.poOrdDtlLineNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslMdseCd, poDtlTMsg.aslMdseCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseCd, poDtlTMsg.mdseCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poQty, setQty);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispQty, pMsg.poLineInfo.no(k).poQty);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).dispPoDtlLineNum, poDtlTMsg.dispPoDtlLineNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poLineTpCd, poDtlTMsg.poLineTpCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMdseCmpsnTpCd, poDtlTMsg.poMdseCmpsnTpCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).setPoOrdDtlLineNum, poDtlTMsg.setPoOrdDtlLineNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseDescShortTxt, poDtlTMsg.mdseDescShortTxt);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poInvQty, poDtlTMsg.poInvQty);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispUomCd, poDtlTMsg.poDispUomCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).thisMthFobCostAmt, poDtlTMsg.thisMthFobCostAmt);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entDealNetUnitPrcAmt, poDtlTMsg.entDealNetUnitPrcAmt);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlDealNetAmt, pMsg.poLineInfo.no(k).entDealNetUnitPrcAmt.getValue().multiply(setQty));
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entFuncNetUnitPrcAmt, poDtlTMsg.entFuncNetUnitPrcAmt);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlFuncNetAmt, pMsg.poLineInfo.no(k).entFuncNetUnitPrcAmt.getValue().multiply(setQty));
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).exchRate, poDtlTMsg.exchRate);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custUomCd, poDtlTMsg.custUomCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).destRtlSwhCd, poDtlTMsg.destRtlSwhCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).srcRtlSwhCd, poDtlTMsg.srcRtlSwhCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).invtyLocCd, poDtlTMsg.invtyLocCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvDt, poDtlTMsg.rqstRcvDt);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvTm, poDtlTMsg.rqstRcvTm);
                            // START 2023/07/20 S.Dong [QC#61638, ADD]
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstShipDt, poDtlTMsg.rqstShipDt);
                            // END 2023/07/20 S.Dong [QC#61638, ADD]
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).frtCondCd, poDtlTMsg.frtCondCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origMdseCd, poDtlTMsg.origMdseCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).fromStkStsCd, poDtlTMsg.fromStkStsCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).toStkStsCd, poDtlTMsg.toStkStsCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).adminPsnCd, poDtlTMsg.adminPsnCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMatchTpCd, poDtlTMsg.poMatchTpCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).ordQty, poDtlTMsg.ordQty);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdNum, poDtlTMsg.cpoOrdNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineNum, poDtlTMsg.cpoDtlLineNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineSubNum, poDtlTMsg.cpoDtlLineSubNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoNum, poDtlTMsg.custIssPoNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoDt, poDtlTMsg.custIssPoDt);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdTpCd, poDtlTMsg.cpoOrdTpCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).billToCustCd, poDtlTMsg.billToCustCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).sellToCustCd, poDtlTMsg.sellToCustCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shpgPlnNum, poDtlTMsg.shpgPlnNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqNum, poDtlTMsg.prchReqNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineNum, poDtlTMsg.prchReqLineNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineSubNum, poDtlTMsg.prchReqLineSubNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefNum, poDtlTMsg.trxRefNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineNum, poDtlTMsg.trxRefLineNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineSubNum, poDtlTMsg.trxRefLineSubNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslDtlPk, poDtlTMsg.aslDtlPk);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslUnitPrcAmt, poDtlTMsg.aslUnitPrcAmt);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shipFromSoNumListTxt, poDtlTMsg.shipFromSoNumListTxt);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndInvtyLocCd, poDtlTMsg.vndInvtyLocCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndIssPoOrdNum, poDtlTMsg.vndIssPoOrdNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).proNum, poDtlTMsg.proNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndPoAckStsCd, poDtlTMsg.vndPoAckStsCd);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdNum, poDtlTMsg.origPoOrdNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdDtlLineNum, poDtlTMsg.origPoOrdDtlLineNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origDispPoDtlLineNum, poDtlTMsg.origDispPoDtlLineNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).svcConfigMstrPk, poDtlTMsg.svcConfigMstrPk);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poSendTs, poDtlTMsg.poSendTs);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlCmntTxt, poDtlTMsg.poOrdDtlCmntTxt);
                            pMsg.poLineInfo.setValidCount(++poLineInfoIndex);
                            k = k + 1;
                            parentUpdFlg = false;
                        }

                        // Add Set Parent Item New Line
                        BigDecimal newSetParentQty = poDtlTMsg.poQty.getValue().subtract(setQty);
                        if ("000".equals(maxLineNum)) {
                            maxLineNum = getNextPoOrdDtlLineNum((String) inMap.get(PD_PO_ORD_NUM));
                        } else {
                            int lineNum = Integer.parseInt(maxLineNum);
                            lineNum++;
                            maxLineNum = ZYPCommonFunc.leftPad(Integer.valueOf(lineNum).toString(), 3, "0");
                        }
                        setMdseLineNum = maxLineNum;
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlLineNum, setMdseLineNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslMdseCd, poDtlTMsg.aslMdseCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseCd, poDtlTMsg.mdseCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poQty, shipQty);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispQty, pMsg.poLineInfo.no(k).poQty);
//                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).dispPoDtlLineNum, poDtlTMsg.dispPoDtlLineNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poLineTpCd, poDtlTMsg.poLineTpCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMdseCmpsnTpCd, poDtlTMsg.poMdseCmpsnTpCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).setPoOrdDtlLineNum, poDtlTMsg.setPoOrdDtlLineNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseDescShortTxt, poDtlTMsg.mdseDescShortTxt);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poInvQty, poDtlTMsg.poInvQty);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispUomCd, poDtlTMsg.poDispUomCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).thisMthFobCostAmt, poDtlTMsg.thisMthFobCostAmt);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entDealNetUnitPrcAmt, poDtlTMsg.entDealNetUnitPrcAmt);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlDealNetAmt, pMsg.poLineInfo.no(k).entDealNetUnitPrcAmt.getValue().multiply(newSetParentQty));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entFuncNetUnitPrcAmt, poDtlTMsg.entFuncNetUnitPrcAmt);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlFuncNetAmt, pMsg.poLineInfo.no(k).entFuncNetUnitPrcAmt.getValue().multiply(newSetParentQty));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).exchRate, poDtlTMsg.exchRate);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custUomCd, poDtlTMsg.custUomCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).destRtlSwhCd, poDtlTMsg.destRtlSwhCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).srcRtlSwhCd, poDtlTMsg.srcRtlSwhCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).invtyLocCd, poDtlTMsg.invtyLocCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvDt, poDtlTMsg.rqstRcvDt);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvTm, poDtlTMsg.rqstRcvTm);
                        // START 2023/07/20 S.Dong [QC#61638, ADD]
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstShipDt, poDtlTMsg.rqstShipDt);
                        // END 2023/07/20 S.Dong [QC#61638, ADD]
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).frtCondCd, poDtlTMsg.frtCondCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origMdseCd, poDtlTMsg.origMdseCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).fromStkStsCd, poDtlTMsg.fromStkStsCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).toStkStsCd, poDtlTMsg.toStkStsCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).adminPsnCd, poDtlTMsg.adminPsnCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMatchTpCd, poDtlTMsg.poMatchTpCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).ordQty, poDtlTMsg.ordQty);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdNum, poDtlTMsg.cpoOrdNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineNum, poDtlTMsg.cpoDtlLineNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineSubNum, poDtlTMsg.cpoDtlLineSubNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoNum, poDtlTMsg.custIssPoNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoDt, poDtlTMsg.custIssPoDt);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdTpCd, poDtlTMsg.cpoOrdTpCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).billToCustCd, poDtlTMsg.billToCustCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).sellToCustCd, poDtlTMsg.sellToCustCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shpgPlnNum, poDtlTMsg.shpgPlnNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqNum, poDtlTMsg.prchReqNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineNum, poDtlTMsg.prchReqLineNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineSubNum, poDtlTMsg.prchReqLineSubNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefNum, poDtlTMsg.trxRefNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineNum, poDtlTMsg.trxRefLineNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineSubNum, poDtlTMsg.trxRefLineSubNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslDtlPk, poDtlTMsg.aslDtlPk);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslUnitPrcAmt, poDtlTMsg.aslUnitPrcAmt);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shipFromSoNumListTxt, poDtlTMsg.shipFromSoNumListTxt);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndInvtyLocCd, poDtlTMsg.vndInvtyLocCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndIssPoOrdNum, poDtlTMsg.vndIssPoOrdNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).proNum, poDtlTMsg.proNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndPoAckStsCd, poDtlTMsg.vndPoAckStsCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdNum, poDtlTMsg.origPoOrdNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdDtlLineNum, poDtlTMsg.origPoOrdDtlLineNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origDispPoDtlLineNum, poDtlTMsg.origDispPoDtlLineNum);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).svcConfigMstrPk, poDtlTMsg.svcConfigMstrPk);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poSendTs, poDtlTMsg.poSendTs);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlCmntTxt, poDtlTMsg.poOrdDtlCmntTxt);
                        pMsg.poLineInfo.setValidCount(++poLineInfoIndex);
                        k = k + 1;
                    }

                    // create new line
                    int lineNum = Integer.parseInt(maxLineNum);
                    lineNum++;
                    maxLineNum = ZYPCommonFunc.leftPad(Integer.valueOf(lineNum).toString(), 3, "0");
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlLineNum, maxLineNum);
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslMdseCd, (String) inMap.get(ED_ASN_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseCd, (String) inMap.get(ED_ASN_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poQty, shipQty);
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispQty, shipQty);
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).invtyLocCd, (String) inMap.get(DPD_INVTY_LOC_CD));

                    setSetPoDtlLine(pMsg, k, setMdseLineNum, inMap);
                    updateEdiAsnDtlWrk(inMap, maxLineNum);
                    addLineMap.put((String) inMap.get(ED_PO_ORD_DTL_LINE_NUM), k);

                    pMsg.poLineInfo.setValidCount(++poLineInfoIndex);
                    k = k + 1;
                    preSplitLineNum = splitLineNum;

                } else {

                    if (splitLineCnt.compareTo(splitLineNum) == 0) {
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlLineNum, (String) inMap.get(ED_PO_ORD_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslMdseCd, (String) inMap.get(ED_ASN_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseCd, (String) inMap.get(D_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poQty, asnQty);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispQty, pMsg.poLineInfo.no(k).poQty);
                        setSetPoDtlLine(pMsg, k, (String) inMap.get(DPD_SET_PO_ORD_DTL_LINE_NUM), inMap);
                        updateEdiAsnDtlWrk(inMap, pMsg.poLineInfo.no(k).poOrdDtlLineNum.getValue());
                        pMsg.poLineInfo.setValidCount(++poLineInfoIndex);
                        k = k + 1;
                    } else {
                        updateEdiAsnDtlWrk(inMap, (String) inMap.get(ED_PO_ORD_DTL_LINE_NUM));
                        preSplitLineNum = splitLineNum;
                        continue;
                    }
                }

            } else {

                // update original Line
                if (splitLineCnt.compareTo(splitLineNum) == 0) {
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlLineNum, (String) inMap.get(ED_PO_ORD_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslMdseCd, (String) inMap.get(DPD_ASL_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseCd, (String) inMap.get(ED_ASN_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poQty, (BigDecimal) inMap.get(PD_PO_QTY));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispQty, pMsg.poLineInfo.no(k).poQty);
                    setSetPoDtlLine(pMsg, k, (String) inMap.get(DPD_SET_PO_ORD_DTL_LINE_NUM), inMap);
                    updateEdiAsnDtlWrk(inMap, pMsg.poLineInfo.no(k).poOrdDtlLineNum.getValue());
                } else {
                    preSplitLineNum = splitLineNum;
                    continue;
                }

                pMsg.poLineInfo.setValidCount(++poLineInfoIndex);
                k = k + 1;
            }

        }
        // QC#54373 End
        // }

        // serNumList
        // ************************************************************************
        // do nothing

        return k;

    }

    /**
     * @param asnHdrTMsg
     * @param asnDtlList
     * @param asnDtlMapList
     * @param asnSerList
     * @param poDtlSplitMapList
     * @return
     */
    private boolean splitPoDtl(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, List<EDI_ASN_DTL_WRKTMsg> asnDtlList, List<EDI_ASN_SER_NUM_WRKTMsg> asnSerList, NPZC104001PMsg npzc104001Pmsg) {

        boolean isError = false;

        HashMap<String, String> paramMap = new HashMap<String, String>();

        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_ASN_SO_NUM, asnHdrTMsg.asnSoNum.getValue());

        List<Map<String, Object>> asnDtlQueryList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getEdiAsnDtl", paramMap);

        BigDecimal balQty = new BigDecimal(0);
        BigDecimal asnQty = new BigDecimal(0);

        String poOrdNum = "";
        String prevPoOrdNum = "";
        int apiLineCount = 0;

        for (int i = 0; i < asnDtlQueryList.size(); i++) {

            boolean needToSplitFlag = false;

            Map<String, Object> asnDtlQueryMap = asnDtlQueryList.get(i);

            HashMap<String, String> poMsgMap = new HashMap<String, String>();

            poMsgMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
            poMsgMap.put(BIND_PO_ORD_NUM, (String) asnDtlQueryMap.get(PD_PO_ORD_NUM));

            List<Map<String, Object>> poMsgList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPoMsgList", poMsgMap);

            if (!poOrdNum.equals(prevPoOrdNum)) {
                poOrdNum = (String) asnDtlQueryMap.get(PO_ORD_NUM);
                apiLineCount = 0;
            }

            balQty = (BigDecimal) asnDtlQueryMap.get(PD_PO_BAL_QTY);
            asnQty = (BigDecimal) asnDtlQueryMap.get(D_ASN_QTY);

            // mdse split check
            if (ZYPConstant.FLG_OFF_N.equals(asnDtlQueryMap.get(CAL_SPLIT_FLAG))) {

                // QC#17900
                // get UnReceived Qty(Partially Received & Unreceived)

                Map<String, Object> params = new HashMap<String, Object>();
                params.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
                params.put(BIND_PO_ORD_NUM, (String) asnDtlQueryMap.get(PD_PO_ORD_NUM));
                params.put(BIND_PO_ORD_DTL_LINE_NUM, (String) asnDtlQueryMap.get(DPD_ORIG_PO_ORD_DTL_LINE_NUM));
                params.put(BIND_SCE_ORD_TP_CD, NLXSceConst.SCE_ORD_TP_CD_DG);
                String[] stsCdAry = {RWS_STS.PRINTED, RWS_STS.RECEIVING };
                params.put(BIND_RWS_STS_CD, stsCdAry);

                Map<String, Object> unreceivedData = (Map<String, Object>) ssmBatchClient.queryObject("getRwsCreatedByAsnUnReceived", params);
                if (null != unreceivedData && unreceivedData.size() > 0) {
                    balQty = balQty.subtract((BigDecimal) unreceivedData.get(RWS_BAL_QTY));

                }
                // QC#17900

                // qty check
                if (ZYPConstant.FLG_ON_Y.equals(asnDtlQueryMap.get(D_MDSE_CD_UPD_FLG)) && balQty.compareTo(asnQty) >= 0) {

                    needToSplitFlag = true;

                }

            } else {

                needToSplitFlag = true;

            }

            if (needToSplitFlag) {

                apiLineCount = addPoInfo(npzc104001Pmsg, asnDtlQueryMap, poMsgList, apiLineCount, asnSerList);

                if (apiLineCount == 0) {
                    continue;
                }

                // lock tables.
                // PO , PO_DTL , PO_ACCT
                // ****************************************************************
                POTMsg poTMsg = new POTMsg();
                PO_DTLTMsg poDtlTMsg = new PO_DTLTMsg();
                lockTables(asnDtlQueryMap, poTMsg, poDtlTMsg);

                // callPoCreateApi
                // ****************************************************************
                if (callPoCreateApi(npzc104001Pmsg, asnHdrTMsg, asnDtlQueryList)) {

                    isError = true;
                    apiLineCount = 0;

                } else {

                    for (EDI_ASN_DTL_WRKTMsg asnDtlTMsg : asnDtlList) {

                        // update EDI_ASN_DTL_WRKT
                        // ****************************************************************
                        if (asnDtlTMsg.asnSoNum.getValue().equals((String) asnDtlQueryMap.get(ED_ASN_SO_NUM)) && asnDtlTMsg.asnLineNum.getValue().equals((String) asnDtlQueryMap.get(ED_ASN_LINE_NUM))) {

                            if (!ZYPCommonFunc.hasValue(asnDtlTMsg.poOrdDtlLineNum.getValue())) {
                                ZYPEZDItemValueSetter.setValue(asnDtlTMsg.poOrdDtlLineNum, poDtlTMsg.poOrdDtlLineNum);
                                EZDTBLAccessor.update(asnDtlTMsg);

                                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(asnDtlTMsg.getReturnCode())) {
                                    String errorKey = "GLBL_CMPY_CD,ASN_SO_NUM,ASN_LINE_NUM";
                                    String errorVal = (String) asnDtlQueryMap.get(ED_GLBL_CMPY_CD) + "," + (String) asnDtlQueryMap.get(ED_ASN_SO_NUM) + "," + (String) asnDtlQueryMap.get(ED_ASN_LINE_NUM);
                                    addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1003E, new String[] {"EDI_ASN_DTL_WRK", errorKey, errorVal }));
                                    isError = true;
                                }
                            }

                            for (EDI_ASN_SER_NUM_WRKTMsg asnSerTMsg : asnSerList) {
                                if (asnSerTMsg.asnSoSlpNum.getValue().equals(asnDtlTMsg.asnSoSlpNum.getValue()) && asnSerTMsg.asnMdseCd.getValue().equals(asnDtlTMsg.asnMdseCd.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(asnSerTMsg.poOrdDtlLineNum, asnDtlTMsg.poOrdDtlLineNum);
                                    EZDTBLAccessor.update(asnSerTMsg);
                                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(asnDtlTMsg.getReturnCode())) {
                                        String errorKey = "GLBL_CMPY_CD,ASN_MDSE_CD,ASN_SO_NUM,SO_SER_ID";
                                        String errorVal = asnSerTMsg.glblCmpyCd.getValue() + "," + asnSerTMsg.asnMdseCd.getValue() + "," + asnSerTMsg.asnSoNum.getValue() + "," + asnSerTMsg.soSerId.getValue();
                                        addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1003E, new String[] {"EDI_ASN_SER_NUM_WRK", errorKey, errorVal }));
                                        isError = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            prevPoOrdNum = poOrdNum;

        }

        return isError;

    }

    /**
     * @param asnHdrTMsg
     * @param asnDtlList
     * @param asnDtlMapList
     * @param asnSerList
     * @param poDtlSplitMapList
     * @return
     */
    private boolean splitSetPoDtl(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, List<EDI_ASN_DTL_WRKTMsg> asnDtlList, List<EDI_ASN_SER_NUM_WRKTMsg> asnSerList, NPZC104001PMsg npzc104001Pmsg) {

        boolean isError = false;

        HashMap<String, String> paramMap = new HashMap<String, String>();

        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_ASN_SO_NUM, asnHdrTMsg.asnSoNum.getValue());
        paramMap.put("setFlg", ZYPConstant.FLG_ON_Y);

        List<Map<String, Object>> asnDtlQueryList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getEdiAsnDtl", paramMap);

        String poOrdNum = "";
        String prevPoOrdNum = "";
        int apiLineCount = npzc104001Pmsg.poLineInfo.getValidCount();

        if (asnDtlQueryList != null && !asnDtlQueryList.isEmpty()) {

            Map<String, Object> asnDtlQueryMap = asnDtlQueryList.get(0);

            HashMap<String, String> poMsgMap = new HashMap<String, String>();

            poMsgMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
            poMsgMap.put(BIND_PO_ORD_NUM, (String) asnDtlQueryMap.get(PD_PO_ORD_NUM));

            List<Map<String, Object>> poMsgList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPoMsgList", poMsgMap);

            if (!poOrdNum.equals(prevPoOrdNum)) {
                poOrdNum = (String) asnDtlQueryMap.get(PO_ORD_NUM);
                apiLineCount = 0;
            }

            apiLineCount = addSetPoInfo(npzc104001Pmsg, asnDtlQueryList, poMsgList, apiLineCount, asnSerList);

            if (apiLineCount > 0) {
                // lock tables.
                // PO , PO_DTL , PO_ACCT
                // ****************************************************************
                POTMsg poTMsg = new POTMsg();
                PO_DTLTMsg poDtlTMsg = new PO_DTLTMsg();
                lockTables(asnDtlQueryMap, poTMsg, poDtlTMsg);

                // callPoCreateApi
                // ****************************************************************
                if (callPoCreateApi(npzc104001Pmsg, asnHdrTMsg, asnDtlQueryList)) {

                    isError = true;

                } else {

                    for (EDI_ASN_DTL_WRKTMsg asnDtlTMsg : asnDtlList) {

                        // update EDI_ASN_DTL_WRKT
                        // ****************************************************************
                        if (asnDtlTMsg.asnSoNum.getValue().equals((String) asnDtlQueryMap.get(ED_ASN_SO_NUM)) && asnDtlTMsg.asnLineNum.getValue().equals((String) asnDtlQueryMap.get(ED_ASN_LINE_NUM))) {

                            if (!ZYPCommonFunc.hasValue(asnDtlTMsg.poOrdDtlLineNum.getValue())) {
                                ZYPEZDItemValueSetter.setValue(asnDtlTMsg.poOrdDtlLineNum, poDtlTMsg.poOrdDtlLineNum);
                                EZDTBLAccessor.update(asnDtlTMsg);

                                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(asnDtlTMsg.getReturnCode())) {
                                    String errorKey = "GLBL_CMPY_CD,ASN_SO_NUM,ASN_LINE_NUM";
                                    String errorVal = (String) asnDtlQueryMap.get(ED_GLBL_CMPY_CD) + "," + (String) asnDtlQueryMap.get(ED_ASN_SO_NUM) + "," + (String) asnDtlQueryMap.get(ED_ASN_LINE_NUM);
                                    addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1003E, new String[] {"EDI_ASN_DTL_WRK", errorKey, errorVal }));
                                    isError = true;
                                }
                            }

                            for (EDI_ASN_SER_NUM_WRKTMsg asnSerTMsg : asnSerList) {
                                if (asnSerTMsg.asnSoSlpNum.getValue().equals(asnDtlTMsg.asnSoSlpNum.getValue()) && asnSerTMsg.asnMdseCd.getValue().equals(asnDtlTMsg.asnMdseCd.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(asnSerTMsg.poOrdDtlLineNum, asnDtlTMsg.poOrdDtlLineNum);
                                    EZDTBLAccessor.update(asnSerTMsg);
                                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(asnDtlTMsg.getReturnCode())) {
                                        String errorKey = "GLBL_CMPY_CD,ASN_MDSE_CD,ASN_SO_NUM,SO_SER_ID";
                                        String errorVal = asnSerTMsg.glblCmpyCd.getValue() + "," + asnSerTMsg.asnMdseCd.getValue() + "," + asnSerTMsg.asnSoNum.getValue() + "," + asnSerTMsg.soSerId.getValue();
                                        addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1003E, new String[] {"EDI_ASN_SER_NUM_WRK", errorKey, errorVal }));
                                        isError = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            prevPoOrdNum = poOrdNum;

        }

        return isError;

    }

    /**
     * callPoCreateApi
     * @param pMsg
     * @return
     */
    private boolean callPoCreateApi(NPZC104001PMsg pMsg, EDI_ASN_HDR_WRKTMsg asnHdrTMsg, List<Map<String, Object>> asnDtlQueryList) {

        boolean isErr = false;

        // Call PO Create API
        new NPZC104001().execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);

            for (String xxMsgId : xxMsgIdList) {

                if (xxMsgId.endsWith("E")) {
                    String errorApi = "NPZC104001:" + S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                    addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                    addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                    isErr = true;
                    S21InfoLogOutput.println(xxMsgId);
                }
            }

            // QC#24780 Detail Error
            for (int i = 0; i < pMsg.poLineInfo.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(pMsg.poLineInfo.no(i).xxMsgId)) {
                    String xxMsgId = pMsg.poLineInfo.no(i).xxMsgId.getValue();
                    if (xxMsgId.endsWith("E")) {
                        if (ZYPCommonFunc.hasValue(pMsg.poOrdNum) && ZYPCommonFunc.hasValue(pMsg.poLineInfo.no(i).poOrdDtlLineNum)) {
                            String poOrdNum = pMsg.poOrdNum.getValue();
                            String poOrdDtlLineNum = pMsg.poLineInfo.no(i).poOrdDtlLineNum.getValue();
                            for (int c = 0; c < asnDtlQueryList.size(); c++) {
                                Map<String, Object> asnDtlMap = asnDtlQueryList.get(c);
                                String asnPoOrdNum = (String) asnDtlMap.get("ED_PO_ORD_NUM");
                                String asnPoOrdDtlLineNum = (String) asnDtlMap.get("ED_PO_ORD_DTL_LINE_NUM");
                                if (poOrdNum.equals(asnPoOrdNum) && poOrdDtlLineNum.equals(asnPoOrdDtlLineNum)) {
                                    EDI_ASN_DTL_WRKTMsg tMsg = new EDI_ASN_DTL_WRKTMsg();
                                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
                                    ZYPEZDItemValueSetter.setValue(tMsg.asnSoNum, (String) asnDtlMap.get("ED_ASN_SO_NUM"));
                                    ZYPEZDItemValueSetter.setValue(tMsg.asnLineNum, (String) asnDtlMap.get("ED_ASN_LINE_NUM"));
                                    tMsg = (EDI_ASN_DTL_WRKTMsg) EZDTBLAccessor.findByKey(tMsg);
                                    if (tMsg == null) {
                                        break;
                                    }
                                    String errorApi = "NPZC104001:" + S21MessageFunc.clspGetMessage(xxMsgId);
                                    addMailMessage(asnHdrTMsg, tMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                                    pMsg.poLineInfo.clear();
                                    pMsg.poLineInfo.setValidCount(0);
                                }
                            }
                            isErr = true;
                        }
                    }
                }
            }
        }

        return isErr;
    }

    /**
     * callGetPoLineFromEdiApi
     * @param asnHdrTMsg
     * @param asnDtlTMsg
     * @param mdseCd
     * @return
     */
    private NPZC110001PMsg callGetPoLineFromEdiApi(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, EDI_ASN_DTL_WRKTMsg asnDtlTMsg, String mdseCd) {

        NPZC110001PMsg pMsg = new NPZC110001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.ediPoOrdNum, asnHdrTMsg.ediPoOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum_I, asnHdrTMsg.poOrdNum);

        int idx = 0;

        ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(idx).poOrdDtlLineNum, asnDtlTMsg.poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(idx).ediPoOrdDtlLineNum, asnDtlTMsg.ediPoOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(idx).mdseCd, mdseCd);
        idx++;

        pMsg.poDetailInList.setValidCount(idx);
        new NPZC110001().execute(pMsg, ONBATCH_TYPE.BATCH);

        return pMsg;
    }

    /**
     * List<Map<String, Object>>
     * @param asnDtlTMsg
     * @return
     */
    private List<Map<String, Object>> getToSendPoReceivingInfoList(EDI_ASN_DTL_WRKTMsg asnDtlTMsg) {

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_ORIG_PO_ORD_NUM, asnDtlTMsg.poOrdNum.getValue());
        paramMap.put(BIND_ORIG_PO_ORD_DTL_LINE_NUM, asnDtlTMsg.poOrdDtlLineNum.getValue());
        paramMap.put(BIND_OPEN_PO_FLG, ZYPConstant.FLG_ON_Y);

        List<Map<String, Object>> getToSendPoReceivingInfoList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getToSendPoReceivingInfo", paramMap);

        // START 04/27/2020 T.Ogura [QC#56546,ADD]
        if (getToSendPoReceivingInfoList == null || getToSendPoReceivingInfoList.isEmpty()) {
            getToSendPoReceivingInfoList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getToSendPoReceivingInfoAgain", paramMap);
        }
        // END   04/27/2020 T.Ogura [QC#56546,ADD]

        return getToSendPoReceivingInfoList;
    }

    /**
     * @param asnDtlTMsg
     * @return
     */
    private List<String> getCarrCdFromXref(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, EDI_ASN_DTL_WRKTMsg asnDtlTMsg, String brFlg) {

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_VND_CD, asnHdrTMsg.vndCd.getValue());
        paramMap.put(BIND_TRD_PTNR_CARR_CD, asnDtlTMsg.asnCarrCd.getValue());
        // QC#58256
        paramMap.put("carrTpF", CARR_TP.FEDEX);
        paramMap.put("carrCdF", "FDXE");
        paramMap.put("carrTpU", CARR_TP.UPS);
        paramMap.put("carrCdU", "UPSN");
        paramMap.put("ast", "*");
        paramMap.put("otherCarrCd", "*");
        paramMap.put("cusaPatVndCd", "0000900335");

        List<String> carrCdFromXrefList = (List<String>) ssmBatchClient.queryObjectList("getCarrCdFromXref", paramMap);

        if (ZYPConstant.FLG_ON_Y.equals(brFlg) && carrCdFromXrefList.size() > 1) {

            PO_DTLTMsg tMsg = new PO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(tMsg.poOrdNum, asnHdrTMsg.poOrdNum.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.poOrdDtlLineNum, asnDtlTMsg.poOrdDtlLineNum.getValue());

            tMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKey(tMsg);

            paramMap.put(BIND_SHPG_SVC_LCL_CD, tMsg.shpgSvcLvlCd.getValue());
            carrCdFromXrefList = (List<String>) ssmBatchClient.queryObjectList("getCarrCdFromXref", paramMap);

        }

        return carrCdFromXrefList;
    }

    /**
     * getrwsRefNum
     * @param asnHdrTMsg
     * @param asnDtlTMsg
     * @return
     */
    private String getrwsRefNum(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, EDI_ASN_DTL_WRKTMsg asnDtlTMsg, List<Map<String, Object>> list) {

        String shipFromSoNum = asnHdrTMsg.shipFromSoNum.getValue();
        String rwsRefNum = null;
        String rtlWhCd = null;

        if (!list.isEmpty()) {
            rtlWhCd = (String) list.get(0).get(RTL_WH_CD);
        }

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_WH_CD, rtlWhCd);
        paramMap.put(BIND_RWS_REF_NUM, shipFromSoNum);

        List<String> rwsRefNumList = (List<String>) ssmBatchClient.queryObjectList("getRwsRefNum", paramMap);

        if (rwsRefNumList.isEmpty()) {

            rwsRefNum = shipFromSoNum;

        } else {

            paramMap.put(BIND_RWS_REF_NUM, CARET + shipFromSoNum + HYPHEN + "[0-9]{1,2}" + DOLLAR);

            List<String> maxRwsRefNumList = (List<String>) ssmBatchClient.queryObjectList("getMaxRwsRefNum", paramMap);

            if (maxRwsRefNumList == null || maxRwsRefNumList.isEmpty()) {

                if (shipFromSoNum.length() < RWS_REF_NUM_LEN - 2) {

                    rwsRefNum = shipFromSoNum + HYPHEN + "01";

                } else {

                    rwsRefNum = shipFromSoNum + HYPHEN + "1";

                }

            } else {

                // if (!maxRwsRefNumList.isEmpty()) {

                String maxRwsRefNum = maxRwsRefNumList.get(0);

                if (maxRwsRefNum == null) {

                    if (shipFromSoNum.length() < RWS_REF_NUM_LEN - 2) {

                        rwsRefNum = shipFromSoNum + HYPHEN + "01";

                    } else {

                        rwsRefNum = shipFromSoNum + HYPHEN + "1";

                    }

                } else {

                    int index = maxRwsRefNum.lastIndexOf(HYPHEN);

                    String strRevision = String.valueOf(Integer.parseInt(maxRwsRefNum.substring(index + 1)) + 1);

                    if (shipFromSoNum.length() < RWS_REF_NUM_LEN - 2) {

                        rwsRefNum = shipFromSoNum + HYPHEN + ZYPCommonFunc.leftPad(strRevision, 2, LF_PAD_CHAR);

                    } else {

                        rwsRefNum = shipFromSoNum + HYPHEN + strRevision;
                    }
                }
            }
        }

        return rwsRefNum;
    }

    // /**
    // * @param asnHdrTMsg
    // * @param asnDtlTMsg
    // * @return
    // */
    // private String getRwsInfoForDropShip(EDI_ASN_HDR_WRKTMsg
    // asnHdrTMsg, List<EDI_ASN_DTL_WRKTMsg> asnDtlList) {
    //
    // String rwsRefNum = null;
    //
    // EDI_ASN_DTL_WRKTMsg asnDtlTMsg = asnDtlList.get(0);
    // List<Map<String, Object>> sendPoReceivingInfoList =
    // getToSendPoReceivingInfoList(asnDtlTMsg);
    //
    // String shipFromSoNum = asnHdrTMsg.shipFromSoNum.getValue();
    // String invtyLocCd = (String)
    // sendPoReceivingInfoList.get(0).get(INVTY_LOC_CD);
    //
    // HashMap<String, String> paramMap = new HashMap<String,
    // String>();
    // paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
    // paramMap.put(BIND_WH_CD, invtyLocCd);
    // paramMap.put(BIND_RWS_REF_NUM, shipFromSoNum);
    //
    // List<String> rwsRefNumList = (List<String>)
    // ssmBatchClient.queryObjectList("getRwsRefNum", paramMap);
    //
    // if (rwsRefNumList.isEmpty()) {
    //
    // paramMap.put(BIND_RWS_REF_NUM, CARET + shipFromSoNum + HYPHEN +
    // "[0-9]{1,2}" + DOLLAR);
    //
    // List<String> maxRwsRefNumList = (List<String>)
    // ssmBatchClient.queryObjectList("getMaxRwsRefNum", paramMap);
    //
    // if (maxRwsRefNumList.isEmpty()) {
    //
    // S21InfoLogOutput.println(MSG_ID_NLZM1011E);
    // addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(),
    // S21MessageFunc.clspGetMessage(MSG_ID_NLZM1011E));
    //
    // } else {
    //
    // rwsRefNum = maxRwsRefNumList.get(0);
    // }
    //
    // } else if (rwsRefNumList.size() == 1) {
    //
    // rwsRefNum = rwsRefNumList.get(0);
    //
    // }
    //
    // return rwsRefNum;
    //
    // }

    /**
     * checkDropShip
     * @param asnHdrTMsg
     * @return
     */
    private boolean checkDropShip(EDI_ASN_HDR_WRKTMsg asnHdrTMsg) {

        boolean dropShipFlag = false;

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_PO_ORD_NUM, asnHdrTMsg.poOrdNum.getValue());

        String poQlfyCd = (String) ssmBatchClient.queryObject("getPoQlfyCd", paramMap);

        if (ZYPCommonFunc.hasValue(poQlfyCd) && dropShipWhCd.equals(poQlfyCd)) {

            dropShipFlag = true;

        }

        return dropShipFlag;

    }

    /**
     * getsceOrdTpCd
     * @param vndCd
     * @return sceOrdTpCd
     */
    private String getsceOrdTpCd(String vndCd) {

        String sceOrdTpCd = null;
        HashMap<String, String> paramMap = new HashMap<String, String>();

        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_PRNT_CMPY_VND_CD, vndCd);

        String result = (String) ssmBatchClient.queryObject("getSceOrdTpCd", paramMap);

        if (ZYPCommonFunc.hasValue(result)) {

            sceOrdTpCd = SCE_ORD_TP.DOMESTIC_CANON_GROUP;

        } else {

            sceOrdTpCd = SCE_ORD_TP.DOMESTIC_PO_EXISTS;

        }

        return sceOrdTpCd;
    }

    /**
     * @param asnHdrTMsg
     * @param asnDtlList
     * @param vndCd
     * @param shipFromSoNum
     * @param npzc104001Pmsg
     * @return
     */
    // START 2019/09/12 T.Ogura [QC#53424,MOD]
//    private boolean callPoReceivingApi(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, List<EDI_ASN_DTL_WRKTMsg> asnDtlList, String vndCd, String shipFromSoNum, String poOrdCmntTxt, NPZC104001PMsg npzc104001Pmsg) {
    private boolean callPoReceivingApi(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, List<EDI_ASN_DTL_WRKTMsg> asnDtlList, String vndCd, String shipFromSoNum, NPZC104001PMsg npzc104001Pmsg) {
    // END   2019/09/12 T.Ogura [QC#53424,MOD]

        boolean result = true;
        String carrCd = null;
        // START 2019/02/19 M.Naito [QC#30273,ADD]
        String invtyLocCd = null;
        String whCd = null;
        String prchReqNum = null;
        String prchReqLineNum = null;
        BigDecimal prchReqLineSubNum = null;
        // END 2019/02/19 M.Naito [QC#30273,ADD]

        NLZC201001PMsg pMsg = new NLZC201001PMsg();

        List<NLZC201001PMsg> pMsgList = new ArrayList<NLZC201001PMsg>();

        String sceOrdTpCd = getsceOrdTpCd(vndCd);

        EDI_ASN_DTL_WRKTMsg asnDtlTMsg = asnDtlList.get(0);

        List<Map<String, Object>> sendPoReceivingInfoList = getToSendPoReceivingInfoList(asnDtlTMsg);
        List<String> carrCdFromXrefList = getCarrCdFromXref(asnHdrTMsg, asnDtlTMsg, asnHdrTMsg.brFlg.getValue());

        if (carrCdFromXrefList.size() == 1) {

            carrCd = carrCdFromXrefList.get(0);

        } else if (carrCdFromXrefList.size() > 1) {

            // QC#58256
            if (!"NPAA0061".equals(asnHdrTMsg.itrlIntfcId.getValue())) {
                // Not CUSA Parts
                carrCd = "";
            } else {
                // CUSA Parts
                carrCd = carrCdFromXrefList.get(0);
            }
        }

        // START 2018/03/23 S.Katsuma [QC#24353,ADD]
        if (!ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(asnDtlTMsg.asnCarrCd)) {
            carrCd = getCarrCdFromOtbdCarr(asnDtlTMsg);
        }
        // END 2018/03/23 S.Katsuma [QC#24353,ADD]

        // QC#58256
        if ("NPAA0061".equals(asnHdrTMsg.itrlIntfcId.getValue()) && !ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(asnDtlTMsg.asnCarrCd)) {
            carrCd = "*";
        }
        // QC#52401
        if (!ZYPCommonFunc.hasValue(carrCd)) {

            PO_DTLTMsg tMsg = new PO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(tMsg.poOrdNum, asnHdrTMsg.poOrdNum.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.poOrdDtlLineNum, asnDtlTMsg.poOrdDtlLineNum.getValue());

            tMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.carrCd)) {
                carrCd = tMsg.carrCd.getValue();
            }
        }

        String rwsRefNum = getrwsRefNum(asnHdrTMsg, asnDtlTMsg, sendPoReceivingInfoList);

        // START 2019/02/21 M.Naito [QC#30273,MOD]
//        String invtyLocCd = (String) (sendPoReceivingInfoList.get(0).get(INVTY_LOC_CD));
//        String whCd = (String) (sendPoReceivingInfoList.get(0).get(RTL_WH_CD));
//        String prchReqNum = (String) (sendPoReceivingInfoList.get(0).get(PRCH_REQ_NUM));
//        String prchReqLineNum = (String) (sendPoReceivingInfoList.get(0).get(PRCH_REQ_LINE_NUM));
//        BigDecimal prchReqLineSubNum = (BigDecimal) (sendPoReceivingInfoList.get(0).get(PRCH_REQ_LINE_SUB_NUM));
        if (!sendPoReceivingInfoList.isEmpty()) {
            invtyLocCd = (String) (sendPoReceivingInfoList.get(0).get(INVTY_LOC_CD));
            whCd = (String) (sendPoReceivingInfoList.get(0).get(RTL_WH_CD));
            prchReqNum = (String) (sendPoReceivingInfoList.get(0).get(PRCH_REQ_NUM));
            prchReqLineNum = (String) (sendPoReceivingInfoList.get(0).get(PRCH_REQ_LINE_NUM));
            prchReqLineSubNum = (BigDecimal) (sendPoReceivingInfoList.get(0).get(PRCH_REQ_LINE_SUB_NUM));
        }
        // END 2019/02/21 M.Naito [QC#30273,MOD]

        if (rwsRefNum.length() > RWS_REF_NUM_LEN) {
            StringBuilder subMsg = new StringBuilder("EDI PO#=").append(asnHdrTMsg.ediPoOrdNum.getValue());
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1320E, new String[] {createDigitErrMsg(subMsg, "RWS_REF_NUM", rwsRefNum, RWS_REF_NUM_LEN) }));
            addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1320E, new String[] {createDigitErrMsg(subMsg, "RWS_REF_NUM", rwsRefNum, RWS_REF_NUM_LEN) }));
            return false;
        }

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, sceOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.rwsRefNum, rwsRefNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvFromLocTpCd, LOC_TP.VENDOR);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvFromLocCd, asnHdrTMsg.vndCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.whCd, whCd);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvDrctShipTpCd, DRCT_SHIP_TP.N_OR_A);
        ZYPEZDItemValueSetter.setValue(pMsg.inlndCarrCd, carrCd);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvEtaDt, asnDtlList.get(0).asnPlnDelyDt);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvTrxHdrNum, asnHdrTMsg.poOrdNum.getValue());

        if (ZYPCommonFunc.hasValue(asnHdrTMsg.vndInvNum)) {

            ZYPEZDItemValueSetter.setValue(pMsg.domInvNum, asnHdrTMsg.vndInvNum);

        } else {

            ZYPEZDItemValueSetter.setValue(pMsg.domInvNum, shipFromSoNum);

        }

        ZYPEZDItemValueSetter.setValue(pMsg.custPoNum, asnHdrTMsg.poOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.shipFromSoNum, asnHdrTMsg.shipFromSoNum.getValue());
        // START 2019/09/12 T.Ogura [QC#53424,DEL]
//        ZYPEZDItemValueSetter.setValue(pMsg.MsgInfoLIst.no(0).poRcvMsgTxt, poOrdCmntTxt);
//        // QC#24053
//        pMsg.MsgInfoLIst.setValidCount(1);
        // END   2019/09/12 T.Ogura [QC#53424,DEL]

        int validCount = 1;
        int lineNum = 0;

        for (int i = 0; i < asnDtlList.size(); i++) {

            // START 2019/02/21 M.Naito [QC#30273,MOD]
            // QC#30857
            sendPoReceivingInfoList = getToSendPoReceivingInfoList(asnDtlList.get(i));
            if (!sendPoReceivingInfoList.isEmpty()) {
                invtyLocCd = (String) (sendPoReceivingInfoList.get(0).get(INVTY_LOC_CD));
                prchReqNum = (String) (sendPoReceivingInfoList.get(0).get(PRCH_REQ_NUM));
                prchReqLineNum = (String) (sendPoReceivingInfoList.get(0).get(PRCH_REQ_LINE_NUM));
                prchReqLineSubNum = (BigDecimal) (sendPoReceivingInfoList.get(0).get(PRCH_REQ_LINE_SUB_NUM));
            }
            // END 2019/02/21 M.Naito [QC#30273,MOD]


            // check PO Matching Type

            if (ZYPConstant.FLG_ON_Y.equals(getInvtyCtrlFlg((String) asnDtlList.get(i).mdseCd.getValue())) && !ZYPConstant.FLG_OFF_N.equals(getPoMatchTpCd(asnDtlList.get(i)))) {

                ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(lineNum).mdseCd, asnDtlList.get(i).mdseCd);

                // T.Wada Mod QC13224
                if (ZYPCommonFunc.hasValue(asnDtlList.get(i).asnStkStsCd)) {

                    ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(lineNum).stkStsCd, asnDtlList.get(i).asnStkStsCd);

                } else {

                    ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(lineNum).stkStsCd, STK_STS.GOOD);
                }

                ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(lineNum).poRcvQty, asnDtlList.get(i).asnQty);

                // getpoOrdDtlLineNum
                String poOrdDtlLineNum = getPoOrdDtlLineNum(asnDtlList.get(i));

                // ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(i).poRcvTrxLineNum,
                // asnDtlList.get(i).poOrdDtlLineNum);
                ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(lineNum).poRcvTrxLineNum, poOrdDtlLineNum);

                ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(lineNum).asnSoNum, asnHdrTMsg.asnSoNum);
                ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(lineNum).asnLineNum, asnDtlList.get(i).asnLineNum);
                ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(lineNum).carrCd, carrCd);
                ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(lineNum).bolNum, asnDtlList.get(i).asnBolNum);
                ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(lineNum).proNum, asnDtlList.get(i).asnProNum);
                ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(lineNum).invtyLocCd, invtyLocCd);
                ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(lineNum).shipFromInvtyLocCd, asnHdrTMsg.vndCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(lineNum).prchReqNum, prchReqNum);
                ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(lineNum).prchReqLineNum, prchReqLineNum);
                ZYPEZDItemValueSetter.setValue(pMsg.OrdInfoLIst.no(lineNum).prchReqLineSubNum, prchReqLineSubNum);
                pMsg.OrdInfoLIst.setValidCount(validCount);
                validCount++;
                lineNum++;
                pMsgList.add(pMsg);

            }

        }

        if (pMsg.OrdInfoLIst.getValidCount() > 0) {

            // Call PO Receiving API
            new NLZC201001().execute(pMsg, ONBATCH_TYPE.BATCH);

            if (S21ApiUtil.isXxMsgId(pMsg)) {

                List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);

                for (String xxMsgId : xxMsgIdList) {

                    S21InfoLogOutput.println(xxMsgId);
                    String errorApi = "NLZC201001:" + S21MessageFunc.clspGetMessage(xxMsgId);
                    addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                    addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                }

                result = false;

            } else {

                ZYPEZDItemValueSetter.setValue(asnHdrTMsg.poRcvNum, pMsg.poRcvNum.getValue());
            }

        }

        return result;

    }

    /**
     * callRwsApi
     * @param poRcvNum
     * @param asnHdrTMsg
     * @return
     */
    private boolean callRwsApi(String poRcvNum, EDI_ASN_HDR_WRKTMsg asnHdrTMsg) {

        boolean result = false;
        NLZC200001PMsg pMsg = new NLZC200001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.inbdSrcTpCd, INBD_SRC_TP.PO_RECEIVING);
        ZYPEZDItemValueSetter.setValue(pMsg.poRcvNum, poRcvNum);

        new NLZC200001().execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);

            for (String xxMsgId : xxMsgIdList) {
                S21InfoLogOutput.println(xxMsgId);
                String errorApi = "NLZC201001:" + S21MessageFunc.clspGetMessage(xxMsgId);
                addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
            }

        } else {

            ZYPEZDItemValueSetter.setValue(asnHdrTMsg.rwsNum, pMsg.RWSNumList.no(0).rwsNum);
            result = true;
        }

        return result;
    }

    /**
     * @param shipFromSoNum
     * @param poRcvNum
     * @param asnHdrTMsg
     * @return
     */
    private boolean dsRwsUpdate(String shipFromSoNum, String poRcvNum, EDI_ASN_HDR_WRKTMsg asnHdrTMsg) {

        boolean isError = false;

        HashMap<String, String> paramMap = new HashMap<String, String>();

        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_PO_RCV_NUM, asnHdrTMsg.poRcvNum.getValue());

        List<Map<String, Object>> lineList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPoRcvDtl", paramMap);

        for (int i = 0; i < lineList.size(); i++) {

            Map<String, Object> lineMap = lineList.get(i);

            String trxOrdNum = poRcvNum;
            String trxLineNum = (String) lineMap.get(PO_RCV_LINE_NUM);
            String imptInvNum = null;
            String imptInvDoNum = shipFromSoNum;

            if (ZYPCommonFunc.hasValue(asnHdrTMsg.vndInvNum)) {
                imptInvNum = asnHdrTMsg.vndInvNum.getValue();
            } else {
                imptInvNum = shipFromSoNum;
            }
            if (dsRwsUpdateProcess(asnHdrTMsg, trxOrdNum, trxLineNum, imptInvNum, imptInvDoNum)) {
                isError = true;
                break;
            }
        }

        return isError;
    }

    /**
     * rwsUpdateProcess
     * @param asnHdrTMsg
     * @param trxOrdNum
     * @param trxLineNum
     * @param imptInvNum
     * @param imptInvDoNum
     * @return error occured return true
     */
    private boolean dsRwsUpdateProcess(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, String trxOrdNum, String trxLineNum, String imptInvNum, String imptInvDoNum) {

        boolean isError = false;

        // Select RWS Header Data
        RWS_HDRTMsg rwsHdrTMsg = selectRwsHdrTMsg(asnHdrTMsg, trxOrdNum);
        if (rwsHdrTMsg == null) {
            isError = true;
            return isError;
        }

        // Select RWS Detail Data
        RWS_DTLTMsg rwsDtlTMsg = selectRwsDtlTMsg(asnHdrTMsg, rwsHdrTMsg, trxLineNum);
        if (rwsDtlTMsg == null) {
            isError = true;
            return isError;
        }

        // Update RWS Header Data
        isError = updateRwsHdrTMsg(asnHdrTMsg, rwsHdrTMsg, imptInvNum);

        // Update RWS Detail Data
        isError = updateRwsDtlTMsg(asnHdrTMsg, rwsDtlTMsg, imptInvNum, imptInvDoNum);

        return isError;

    }

    /**
     * selectRwsHdrTMsg
     * @param asnHdrTMsg
     * @param trxOrdNum
     * @return
     */
    private RWS_HDRTMsg selectRwsHdrTMsg(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, String trxOrdNum) {

        RWS_HDRTMsg inMsg = new RWS_HDRTMsg();

        inMsg.setSQLID("013");
        inMsg.setConditionValue("glblCmpyCd01", this.globalCompanyCode);
        inMsg.setConditionValue("trxOrdNum01", trxOrdNum);

        RWS_HDRTMsgArray resultArray = (RWS_HDRTMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);

        if (resultArray.getValidCount() == 0) {
            S21InfoLogOutput.println(MSG_ID_NLZM1011E);
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NLZM1011E));
        } else if (resultArray.getValidCount() > 1) {
            S21InfoLogOutput.println(MSG_ID_NLZM2180E);
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NLZM2180E));
        }

        return resultArray.no(0);
    }

    /**
     * @param asnHdrTMsg
     * @param rwsHdrTMsg
     * @param trxLineNum
     * @return
     */
    private RWS_DTLTMsg selectRwsDtlTMsg(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, RWS_HDRTMsg rwsHdrTMsg, String trxLineNum) {

        RWS_DTLTMsg inMsg = new RWS_DTLTMsg();

        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", this.globalCompanyCode);
        inMsg.setConditionValue("rwsNum01", rwsHdrTMsg.rwsNum.getValue());
        inMsg.setConditionValue("trxLineNum01", trxLineNum);

        RWS_DTLTMsgArray resultArray = (RWS_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);

        if (resultArray.getValidCount() == 0) {
            S21InfoLogOutput.println(MSG_ID_NLZM1006E);
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NLZM1006E));
        } else if (resultArray.getValidCount() > 1) {
            S21InfoLogOutput.println(MSG_ID_NLZM2181E);
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NLZM2181E));
        }

        return resultArray.no(0);
    }

    /**
     * updateRwsHdrTMsg
     * @param asnHdrTMsg
     * @param rwsHdrTMsg
     * @param imptInvNum
     */
    private boolean updateRwsHdrTMsg(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, RWS_HDRTMsg rwsHdrTMsg, String imptInvNum) {

        boolean isError = false;

        ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.imptInvNum, imptInvNum);
        EZDTBLAccessor.update(rwsHdrTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrTMsg.getReturnCode())) {
            S21InfoLogOutput.println(MSG_ID_NLZM2181E);
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NLZM2182E));
            isError = true;
        }

        return isError;
    }

    /**
     * updateRwsDtlTMsg
     * @param asnHdrTMsg
     * @param rwsDtlTMsg
     * @param imptInvNum
     * @param imptInvDoNum
     */
    private boolean updateRwsDtlTMsg(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, RWS_DTLTMsg rwsDtlTMsg, String imptInvNum, String imptInvDoNum) {

        boolean isError = false;

        ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.imptInvNum, imptInvNum);
        ZYPEZDItemValueSetter.setValue(rwsDtlTMsg.imptInvDoNum, imptInvDoNum);
        EZDTBLAccessor.update(rwsDtlTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDtlTMsg.getReturnCode())) {
            S21InfoLogOutput.println(MSG_ID_NLZM2183E);
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NLZM2183E));
            isError = true;
        }

        return isError;
    }

    /**
     * @param poRcvNum
     * @param asnHdrTMsg
     * @param asnDtlList
     * @param rwsNum
     * @return
     */
    private boolean callRwsSerialApi(String poRcvNum, EDI_ASN_HDR_WRKTMsg asnHdrTMsg, String rwsNum) {

        boolean isError = false;

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_RWS_NUM, rwsNum);
        // QC#20500 Add Start
        paramMap.put(FLG_Y, ZYPConstant.FLG_ON_Y);
        // QC#20500 Add End

        List<Map<String, Object>> rwsSerList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRwsSerInfo", paramMap);

        if (!rwsSerList.isEmpty()) {

            for (int i = 0; i < rwsSerList.size(); i++) {
                Map<String, Object> rwsSerNum = rwsSerList.get(i);
                chkSerNumRng(asnHdrTMsg, (String) rwsSerNum.get(ES_SER_NUM), (String) rwsSerNum.get(ES_MDSE_CD));
            }

            NLZC304001PMsg pMsg = new NLZC304001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rwsNum);

            // QC#53127 Del Start
//            for (int i = 0; i < rwsSerList.size(); i++) {
//                ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).rwsDtlLineNum, (String) rwsSerList.get(i).get(RD_RWS_DTL_LINE_NUM));
//                ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).serNum, (String) rwsSerList.get(i).get(ES_SER_NUM));
//                ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).mdseCd, (String) rwsSerList.get(i).get(ES_MDSE_CD));
//                ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).putAwayChkStsCd, PUT_AWAY_CHK_STS.NOT_PROCESSED);
//                ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).serNumSendFlg, ZYPConstant.FLG_OFF_N);
//                pMsg.SerialList.setValidCount(i + 1);
//            }
            // QC#53127 Del End

            // QC#53127 Add Start
            List<String> processedSerNumList = new ArrayList<String>();
            List<String> processedLineNumList = new ArrayList<String>();
            boolean goNext = true;
            BigDecimal rdRwsQty = BigDecimal.ZERO;
            String rdRwsDtlLineNum = "000";
            int cnt = 0;
            int regCnt = 0;
            for (int ii = 0; ii<rwsSerList.size(); ii++) {

                if (!rdRwsDtlLineNum.equals((String) rwsSerList.get(ii).get(RD_RWS_DTL_LINE_NUM))) {

                    // get Line Qty
                    rdRwsQty = (BigDecimal) rwsSerList.get(ii).get("RD_RWS_QTY");

                    rdRwsDtlLineNum = (String) rwsSerList.get(ii).get(RD_RWS_DTL_LINE_NUM);

                    goNext = false;
                } else {
                    if (goNext) {
                        continue;
                    }
                }

                // Is this serial already processed ?
                String esSerNum = (String) rwsSerList.get(ii).get(ES_SER_NUM);
                // QC#54157
                String mdseCdSerNum = (String) rwsSerList.get(ii).get("MDSE_SER_NUM");
                boolean isProcessed = false;
                for (int c = 0; c < processedSerNumList.size(); c++) {
                    // QC#54157
                    if(mdseCdSerNum.equals((String)processedSerNumList.get(c))) {
                        isProcessed = true;
                    }
                }
                // Is this line number already processed ?
                for (int c = 0; c < processedLineNumList.size(); c++) {
                    if(rdRwsDtlLineNum.equals((String)processedLineNumList.get(c))) {
                        isProcessed = true;
                    }
                }
                if (isProcessed) {
                    continue;
                }
                
                // Regist
                ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(cnt).rwsDtlLineNum, rdRwsDtlLineNum);
                ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(cnt).serNum, (String) esSerNum);
                ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(cnt).mdseCd, (String) rwsSerList.get(ii).get(ES_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(cnt).putAwayChkStsCd, PUT_AWAY_CHK_STS.NOT_PROCESSED);
                ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(cnt).serNumSendFlg, ZYPConstant.FLG_OFF_N);
                pMsg.SerialList.setValidCount(cnt+1);
                cnt++;
                regCnt++;    // Registered count of line items.
                // Mod QC#54157
                processedSerNumList.add(mdseCdSerNum);
              
                // Check if the quantity of line item has been registered.
                if (regCnt == rdRwsQty.intValue()) {
                    regCnt = 0;
                    goNext = true;
                    processedLineNumList.add(rdRwsDtlLineNum);
                }
            }
            // QC#53127 Add End

            // Call RWS Serial API
            new NLZC304001().execute(pMsg, ONBATCH_TYPE.BATCH);

            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
                for (String xxMsgId : xxMsgIdList) {
                    S21InfoLogOutput.println(xxMsgId);
                    String errorApi = "NLZC304001:" + S21MessageFunc.clspGetMessage(xxMsgId);
                    addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                    addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                }

                isError = true;
            }

        }
        return isError;
    }

    /**
     * updateEdiAsnHdrWrk
     * @param asnHdrTMsg
     * @return
     */
    private boolean updateEdiAsnHdrWrk(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, boolean flg) {

        EDI_ASN_HDR_WRKTMsg tMsg = new EDI_ASN_HDR_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, asnHdrTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.asnSoNum, asnHdrTMsg.asnSoNum);

        tMsg = (EDI_ASN_HDR_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

        if (tMsg == null) {
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1003E, new String[] {"EDI_ASN_HDR_WRK", "ASN_SO_NUM", asnHdrTMsg.asnSoNum.getValue() }));
            return false;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.poRcvNum, asnHdrTMsg.poRcvNum);
        ZYPEZDItemValueSetter.setValue(tMsg.rwsNum, asnHdrTMsg.rwsNum);
        ZYPEZDItemValueSetter.setValue(tMsg.brFlg, asnHdrTMsg.brFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.poOrdNum, asnHdrTMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(tMsg.vndCd, asnHdrTMsg.vndCd);

        if (flg) {

            ZYPEZDItemValueSetter.setValue(tMsg.asnEdiProcStsCd, ASN_EDI_PROC_STS.ERROR);
            ZYPEZDItemValueSetter.setValue(tMsg.procFlg, ZYPConstant.FLG_ON_1);

        } else {

            ZYPEZDItemValueSetter.setValue(tMsg.asnEdiProcStsCd, ASN_EDI_PROC_STS.PROCESSED);
            ZYPEZDItemValueSetter.setValue(tMsg.procFlg, ZYPConstant.FLG_ON_1);
        }

        EZDTBLAccessor.update(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1003E, new String[] {"EDI_ASN_HDR_WRK", "ASN_SO_NUM", asnHdrTMsg.asnSoNum.getValue() }));
            return false;
        }

        return true;
    }

    // START 2023/03/13 TZ.Win [QC#60960, ADD]
    /**
     * updateEdiAsnHdrWrkWithProcessedWarning
     * @param asnHdrTMsg
     * @return
     */
    private boolean updateEdiAsnHdrWrkWithProcessedWarning(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, boolean flg) {

        EDI_ASN_HDR_WRKTMsg tMsg = new EDI_ASN_HDR_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, asnHdrTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.asnSoNum, asnHdrTMsg.asnSoNum);

        tMsg = (EDI_ASN_HDR_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

        if (tMsg == null) {
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1003E, new String[] {"EDI_ASN_HDR_WRK", "ASN_SO_NUM", asnHdrTMsg.asnSoNum.getValue() }));
            return false;
        }

        ZYPEZDItemValueSetter.setValue(tMsg.poRcvNum, asnHdrTMsg.poRcvNum);
        ZYPEZDItemValueSetter.setValue(tMsg.rwsNum, asnHdrTMsg.rwsNum);
        ZYPEZDItemValueSetter.setValue(tMsg.brFlg, asnHdrTMsg.brFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.poOrdNum, asnHdrTMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(tMsg.vndCd, asnHdrTMsg.vndCd);

        if (flg) {

            ZYPEZDItemValueSetter.setValue(tMsg.asnEdiProcStsCd, ASN_EDI_PROC_STS.PROCESSED_WITH_WARNING);
            ZYPEZDItemValueSetter.setValue(tMsg.procFlg, ZYPConstant.FLG_ON_1);
        }

        EZDTBLAccessor.update(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1003E, new String[] {"EDI_ASN_HDR_WRK", "ASN_SO_NUM", asnHdrTMsg.asnSoNum.getValue() }));
            return false;
        }

        return true;
    }
    // END 2023/03/13 TZ.Win [QC#60960, ADD]

    /**
     * set Mail Message
     * @param soNum String
     * @param errMsg String
     */
    private void addMailMessageToAdmin(String soNum, String errMsg) {
        mailMessageForAdmin.append("SO#:");
        mailMessageForAdmin.append(soNum);
        mailMessageForAdmin.append(" Reason:");
        mailMessageForAdmin.append(errMsg);
        mailMessageForAdmin.append("\r\n");

        S21InfoLogOutput.println(errMsg);
    }

    /**
     * post Error Mail
     */
    private void sendEMailToAdmin() {
        final List<NWXC001001MailSubstituteString> sbsStrList = new ArrayList<NWXC001001MailSubstituteString>();
        NWXC001001MailSubstituteString sbsStr;

        // Set Batch ID
        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchId");
        sbsStr.setSbstStr(this.getClass().getSimpleName());
        sbsStrList.add(sbsStr);

        // Set Batch Name
        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchNm");
        sbsStr.setSbstStr("");
        sbsStrList.add(sbsStr);

        // Set Batch Process Log ID
        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchProcLogId");
        sbsStr.setSbstStr(super.getBatchProcessLogID());
        sbsStrList.add(sbsStr);

        // Set TODAY
        sbsStr = new NWXC001001MailSubstituteString();
        // 2013/12/24 QC3138 Add Start
        String curTs = ZYPDateUtil.getCurrentSystemTime(TS_FORMAT_YYYYMMDDHHMMSS);
        ZYPLocalTimeData locTmDatlocal = ZYPLocalTimeUtil.convertTimeSys2Biz(curTs, TS_FORMAT_YYYYMMDDHHMMSS);
        curTs = locTmDatlocal.getTime();
        sbsStr.setSbstKey("TODAY");
        sbsStr.setSbstStr(curTs.substring(0, 8));
        sbsStrList.add(sbsStr);
        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("TIME");
        sbsStr.setSbstStr(curTs.substring(8));
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("ERR_MSG");
        sbsStr.setSbstStr(mailMessageForAdmin.toString());
        sbsStrList.add(sbsStr);

        // Post Mail
        boolean isNormalEnd = new NWXC001001Mail().postMail(globalCompanyCode, "NPAB1100", MAIL_TMPL_ID_TO_ADMIN, sbsStrList);

        if (!isNormalEnd) {
            S21InfoLogOutput.println(MSG_ID_NPAM1200E);
        }
    }

    /**
     * @param str String
     * @param len int
     * @return String
     */
    private String cutStringLeft(String str, int len) {
        if (ZYPCommonFunc.hasValue(str) && str.length() > len) {
            str = str.substring(0, len);
        }
        return str;
    }

    /**
     * getPoMatchTpCd
     * @param mdseCd
     * @return
     */
    private String getPoMatchTpCd(EDI_ASN_DTL_WRKTMsg tmsg) {

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", this.globalCompanyCode);
        paramMap.put("poOrdNum", tmsg.poOrdNum.getValue());
        paramMap.put("poOrdDtlLineNum", tmsg.poOrdDtlLineNum.getValue());

        String poMatchTpCd = (String) ssmBatchClient.queryObject("getPoMatchTpCd", paramMap);

        return poMatchTpCd;
    }

    /**
     * getInvtyCtrlFlg
     * @param mdseCd
     * @return
     */
    private String getInvtyCtrlFlg(String mdseCd) {

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_MDSE_CD, mdseCd);
        String invtyCtrlFlg = (String) ssmBatchClient.queryObject("getInvtyCtrlFlg", paramMap);

        return invtyCtrlFlg;
    }

    /**
     * sendEMailToUser send e-Mail to order manager
     */
    private void sendEMailToUser() {

        S21MailGroup groupFrom = new S21MailGroup(this.globalCompanyCode, MAIL_GROUP_ID_FROM);

        groupFrom.setMailKey1(MAIL_KEY_FROM);

        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(MSG_ID_NPAM0063E, new String[] {MAIL_GROUP_ID_FROM, MAIL_KEY_FROM });
        }

        String ediPoOrdNum = null;
        String poOrdNum = null;
        Map<String, Object> msgInfo = null;
        List<String> msgList = null;
        StringBuilder outMsg = null;

        Iterator<String> it = mailMsgMap.keySet().iterator();

        while (it.hasNext()) {

            ediPoOrdNum = it.next();
            msgInfo = mailMsgMap.get(ediPoOrdNum);
            poOrdNum = (String) msgInfo.get(MAIL_MSG_MAP_KEY_PO);
            msgList = (List<String>) msgInfo.get(MAIL_MSG_MAP_KEY_MSG_LIST);
            outMsg = new StringBuilder();

            for (int i = 0; i < msgList.size(); i++) {

                if (i != 0) {

                    outMsg.append(CRLF);
                    outMsg.append(INDENT);
                }

                outMsg.append(msgList.get(i));
            }
            // START 2023/03/27 TZ.Win [QC#61269,MOD]
            List<S21MailAddress> addrToList = null;
            // END 2023/03/27 TZ.Win [QC#61269,MOD]
            
            // START 2023/03/27 TZ.Win [QC#61269,ADD]
            RCV_ASN_VNDTMsg rcvAsnVnd = new RCV_ASN_VNDTMsg();
            ZYPEZDItemValueSetter.setValue(rcvAsnVnd.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(rcvAsnVnd.rcvAsnVndCd, (String) msgInfo.get(MAIL_MSG_MAP_KEY_VND));
            rcvAsnVnd = (RCV_ASN_VNDTMsg) EZDTBLAccessor.findByKey(rcvAsnVnd);
            
            if (rcvAsnVnd == null || rcvAsnVnd.rcvErrEmlAddr.getValue() == null || rcvAsnVnd.rcvErrEmlAddr.getValue().equals("")) {
                addrToList = getAddressList(poOrdNum);
            } else {
                List<String> rcvErrEmlAddrList = Arrays.asList(rcvAsnVnd.rcvErrEmlAddr.getValue().split(","));
                addrToList = new ArrayList<S21MailAddress>(rcvErrEmlAddrList.size());
                for (int i = 0; i < rcvErrEmlAddrList.size(); i++) {
                    S21MailAddress address = new S21MailAddress(globalCompanyCode, rcvErrEmlAddrList.get(i));
                    addrToList.add(address);
                }
            }
            // END 2023/03/27 TZ.Win [QC#61269,ADD]
            
            if (addrToList == null || addrToList.isEmpty()) {
                throw new S21AbendException(MSG_ID_NPAM1265E);
            }

            S21MailTemplate template = new S21MailTemplate(this.globalCompanyCode, MAIL_TMPL_ID_TO_ORDER);
            if (!ZYPCommonFunc.hasValue(template.getBody())) {
                throw new S21AbendException(MSG_ID_NPAM0064E, new String[] {MAIL_TMPL_ID_TO_ORDER });
            }

            template.setTemplateParameter("ediPoOrdNum", getSubMsgItemValue(ediPoOrdNum, EDI_PO_ORD_NUM_LEN));
            template.setTemplateParameter("poOrdNum", getSubMsgItemValue(poOrdNum, PO_ORD_NUM_LEN));
            template.setTemplateParameter("asnSoNum", (String) msgInfo.get(MAIL_MSG_MAP_KEY_SO));
            template.setTemplateParameter("ediSts", (String) msgInfo.get(MAIL_MSG_MAP_KEY_STS));

            String rcvDate = (String) msgInfo.get(MAIL_MSG_MAP_KEY_DATE);

            String outFormat = template.getDefaultDateFormat() + TS_FORMAT_FOR_MAIL.substring(10);

            if (ZYPCommonFunc.hasValue(rcvDate) && rcvDate.length() == TS_LENGTH_YYYYMMDD) {

                rcvDate = ZYPDateUtil.DateFormatter(rcvDate, TS_FORMAT_YYYYMMDD, outFormat);

            } else if (ZYPCommonFunc.hasValue(rcvDate) && rcvDate.length() == TS_LENGTH_YYYYMMDDHHMMSS) {

                ZYPLocalTimeData locTmDatlocal = ZYPLocalTimeUtil.convertTimeSys2Biz(rcvDate, TS_FORMAT_YYYYMMDDHHMMSS);
                rcvDate = locTmDatlocal.getTime();
                rcvDate = ZYPDateUtil.DateFormatter(rcvDate, TS_FORMAT_YYYYMMDDHHMMSS, outFormat);

            } else if (ZYPCommonFunc.hasValue(rcvDate) && rcvDate.length() == TS_LENGTH_YYYYMMDDHHMMSS_SSS) {

                ZYPLocalTimeData locTmDatlocal = ZYPLocalTimeUtil.convertTimeSys2Biz(rcvDate, TS_FORMAT_YYYYMMDDHHMMSS_SSS);
                rcvDate = locTmDatlocal.getTime();
                rcvDate = ZYPDateUtil.DateFormatter(rcvDate, TS_FORMAT_YYYYMMDDHHMMSS_SSS, outFormat);
            }

            template.setTemplateParameter("rcvDate", getSubMsgItemValue(rcvDate, EDI_RCV_TS_LEN));
            template.setTemplateParameter("errMsg", outMsg.toString());
            S21Mail poErrMail = new S21Mail(this.globalCompanyCode);
            poErrMail.setFromAddress(addrFromList.get(0));
            poErrMail.setToAddressList(addrToList);
            poErrMail.setMailTemplate(template);
            poErrMail.setSubject(template.getSubject(), MAIL_CHARSET);

            String sendMail = poErrMail.postMail();
            EZDDebugOutput.println(DEBUG_MESSAGE_CODE, sendMail, this);
        }
    }

    /**
     * getAddressList
     * @param poOrdNum PO Order Number
     * @return Address list
     */
    private List<S21MailAddress> getAddressList(String poOrdNum) {
        NPZC106001 getMailAddrForPo = new NPZC106001();
        NPZC106001PMsg apiParam = new NPZC106001PMsg();
        ZYPEZDItemValueSetter.setValue(apiParam.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(apiParam.poOrdNum, poOrdNum);
        getMailAddrForPo.execute(apiParam, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiParam)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiParam);
            for (String xxMsgId : xxMsgIdList) {
                S21InfoLogOutput.println(xxMsgId);
            }
        }
        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>(apiParam.EmlAddrList.getValidCount());
        for (int i = 0; i < apiParam.EmlAddrList.getValidCount(); i++) {
            S21MailAddress address = new S21MailAddress(globalCompanyCode, apiParam.EmlAddrList.no(i).emlAddr.getValue());
            addrToList.add(address);
        }
        return addrToList;
    }

    /**
     * addMailMessage
     * @param asnHdrTMsg EDI_ASN_HDR_WRKTMsg
     * @param asnDtlTMsg EDI_ASN_DTL_WRKTMsg
     * @param msg Message
     */
    private void addMailMessage(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, EDI_ASN_DTL_WRKTMsg asnDtlTMsg, String msg) {

        Map<String, Object> msgInfo = mailMsgMap.get(asnHdrTMsg.ediPoOrdNum.getValue());

        if (msgInfo == null) {

            msgInfo = new HashMap<String, Object>();
            msgInfo.put(MAIL_MSG_MAP_KEY_PO, asnHdrTMsg.poOrdNum.getValue());
            msgInfo.put(MAIL_MSG_MAP_KEY_SO, asnHdrTMsg.asnSoNum.getValue());
            msgInfo.put(MAIL_MSG_MAP_KEY_STS, ASN_EDI_PROC_STS.ERROR);
            msgInfo.put(MAIL_MSG_MAP_KEY_DATE, asnHdrTMsg.ediRcvTs.getValue());
            // START 2023/03/27 TZ.Win [QC#61269,ADD]
            msgInfo.put(MAIL_MSG_MAP_KEY_VND, asnHdrTMsg.vndCd.getValue());
            // END 2023/03/27 TZ.Win [QC#61269,ADD]
            List<String> msgList = new ArrayList<String>();
            msgList.add(msg);
            msgInfo.put(MAIL_MSG_MAP_KEY_MSG_LIST, msgList);
            mailMsgMap.put(asnHdrTMsg.ediPoOrdNum.getValue(), msgInfo);

        } else {

            List<String> msgList = (List<String>) msgInfo.get(MAIL_MSG_MAP_KEY_MSG_LIST);

            if (!msgList.contains(msg)) {

                msgList.add(msg);

            }
        }

        S21InfoLogOutput.println(msg);

        EDI_ASN_ERR_WRKTMsg asnError = new EDI_ASN_ERR_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(asnError.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(asnError.asnSoNum, asnHdrTMsg.asnSoNum);
        ZYPEZDItemValueSetter.setValue(asnError.batErrMsgTxt, cutStringLeft(msg, BAT_ERR_MSG_TXT_LEN));

        if (asnDtlTMsg != null) {

            ZYPEZDItemValueSetter.setValue(asnError.asnLineNum, asnDtlTMsg.asnLineNum);
        }

        errorList.add(asnError);
    }

    /**
     * insertEdiAsnErrWrk
     */
    private void insertEdiAsnErrWrk() {
        for (EDI_ASN_ERR_WRKTMsg inTMsg : errorList) {
            ZYPEZDItemValueSetter.setValue(inTMsg.ediAsnErrWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.EDI_ASN_ERR_WRK_SQ));
            EZDTBLAccessor.insert(inTMsg);
        }
    }

    /**
     * getResultSetValueForPO
     * @param resultSet ResultSet("getEdiAsnHeader")
     * @param item ResultSet Item Name
     * @return PO's item value
     * @exception SQLException
     */
    private String getResultSetValueForPO(ResultSet resultSet, String item) throws SQLException {

        boolean isP1 = false;

        if (ZYPCommonFunc.hasValue(resultSet.getString(P1_PREFIX + PO_ORD_NUM))) {

            isP1 = true;

        } else if (ZYPCommonFunc.hasValue(resultSet.getString(P2_PREFIX + PO_ORD_NUM))) {

            isP1 = false;

        } else {

            return null;
        }

        if (isP1) {

            item = P1_PREFIX + item;

        } else {

            item = P2_PREFIX + item;

        }

        return resultSet.getString(item);
    }

    /**
     * getSubMsgItemValue
     * @param value String
     * @param digit int
     * @return ItemValue(arg value or padding "-")
     */
    private String getSubMsgItemValue(String value, int digit) {

        if (!ZYPCommonFunc.hasValue(value)) {

            value = ZYPCommonFunc.leftPad("", digit, HYPHEN);

        }

        return value;
    }

    /**
     * @param serNum
     * @param mdseCd
     * @return
     */
    private void chkSerNumRng(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, String serNum, String mdseCd) {

        MDSE_SER_NUM_RNGTMsg mdseSerNumRngTMsg = new MDSE_SER_NUM_RNGTMsg();
        mdseSerNumRngTMsg.setSQLID("001");
        mdseSerNumRngTMsg.setConditionValue("glblCmpyCd01", this.globalCompanyCode);
        mdseSerNumRngTMsg.setConditionValue("mdseCd01", mdseCd);

        MDSE_SER_NUM_RNGTMsgArray mdseSerNumRngTMsgArray = (MDSE_SER_NUM_RNGTMsgArray) EZDTBLAccessor.findByCondition(mdseSerNumRngTMsg);

        int lgSerNum = 0;
        String fromSerNum = null;
        String thruSerNum = null;

        // no data
        if (mdseSerNumRngTMsgArray.length() == 0) {
            return;
        }

        // check serial length
        boolean lngChkPassed = false;

        for (int i = 0; i < mdseSerNumRngTMsgArray.length(); i++) {
            lgSerNum = mdseSerNumRngTMsgArray.no(i).lgSerNum.getValueInt();
            if (lgSerNum == serNum.length()) {
                lngChkPassed = true;
                break;
            }
        }

        if (!lngChkPassed) {
            addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1507W));
        }

        // check serial range
        boolean rngChkPassed = false;

        for (int i = 0; i < mdseSerNumRngTMsgArray.length(); i++) {

            lgSerNum = mdseSerNumRngTMsgArray.no(i).lgSerNum.getValueInt();
            fromSerNum = mdseSerNumRngTMsgArray.no(i).fromSerNum.getValue();
            thruSerNum = mdseSerNumRngTMsgArray.no(i).thruSerNum.getValue();

            if (lgSerNum == serNum.length()) {

                if (!ZYPCommonFunc.hasValue(fromSerNum) && !ZYPCommonFunc.hasValue(thruSerNum)) {
                    rngChkPassed = true;
                    break;
                }

                if (ZYPCommonFunc.hasValue(fromSerNum) && !ZYPCommonFunc.hasValue(thruSerNum) && fromSerNum.compareTo(serNum) <= 0) {
                    rngChkPassed = true;
                    break;
                }

                if (!ZYPCommonFunc.hasValue(fromSerNum) && ZYPCommonFunc.hasValue(thruSerNum) && thruSerNum.compareTo(serNum) >= 0) {
                    rngChkPassed = true;
                    break;
                }

                if (ZYPCommonFunc.hasValue(fromSerNum) && ZYPCommonFunc.hasValue(thruSerNum) && fromSerNum.compareTo(serNum) <= 0 && thruSerNum.compareTo(serNum) >= 0) {
                    rngChkPassed = true;
                    break;
                }
            }
        }

        if (!rngChkPassed) {
            addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1508W));
        }

    }

    /**
     * createDigitErrMsg
     * @param keyInfo Key Info Message
     * @param itemName Error Item Name
     * @param itemValue Error Item Value
     * @param digit Error Check Digit Number
     * @return Message Info of Digit Over Error
     */
    private String createDigitErrMsg(StringBuilder keyInfo, String itemName, String itemValue, int digit) {
        StringBuilder msg = new StringBuilder();
        msg.append(keyInfo);
        msg.append(", item=");
        msg.append(itemName);
        msg.append(", value=");
        msg.append(itemValue);
        msg.append(", digits=");
        msg.append(digit);
        return msg.toString();
    }

    /**
     * checkMandatoryHdrItem
     * @param asnHdrTMsg
     * @return
     */
    private boolean checkMandatoryHdrItem(EDI_ASN_HDR_WRKTMsg asnHdrTMsg) {

        boolean isErr = false;
        StringBuilder sbKey = new StringBuilder();
        sbKey.append("ASN_SO_NUM=");
        sbKey.append(asnHdrTMsg.asnSoNum.getValue());

        for (String itemName : MANDATORY_HDR_ITEMS) {
            if (!hasValue(asnHdrTMsg, itemName)) {
                EZDItemAttribute attr = asnHdrTMsg.getAttr(itemName);
                addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1321E, new String[] {attr.getDBColumnName(), asnHdrTMsg.getTableName(), sbKey.toString() }));
                isErr = true;
            }
        }
        return isErr;
    }

    /**
     * checkMandatoryDtlItem
     * @param asnHdrTMsg
     * @param asnDtlTMsg
     * @return
     */
    private boolean checkMandatoryDtlItem(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, EDI_ASN_DTL_WRKTMsg asnDtlTMsg) {

        boolean isErr = false;
        StringBuilder sbKey = new StringBuilder();

        sbKey.append("ASN_SO_NUM=");
        sbKey.append(asnDtlTMsg.asnSoNum.getValue());
        sbKey.append(", ASN_LINE_NUM=");
        sbKey.append(asnDtlTMsg.asnLineNum.getValue());

        for (String itemName : MANDATORY_DTL_ITEMS) {
            if (!hasValue(asnDtlTMsg, itemName)) {
                EZDItemAttribute attr = asnDtlTMsg.getAttr(itemName);
                addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1321E, new String[] {attr.getDBColumnName(), asnDtlTMsg.getTableName(), sbKey.toString() }));
                isErr = true;
            }
        }
        return isErr;
    }

    /**
     * checkHdrDigit
     * @param asnHdrTMsg
     * @param resultSet
     * @param subMsgAddPo
     * @return
     * @throws SQLException
     */
    private boolean checkHdrDigit(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, ResultSet resultSet, StringBuilder subMsgAddPo) throws SQLException {

        boolean isErr = false;

        // START 2019/09/12 T.Ogura [QC#53424,DEL]
//        // check PO_ORD_CMNT_TXT
//        String poOrdCmntTxt = getResultSetValueForPO(resultSet, PO_ORD_CMNT_TXT);
//
//        if (ZYPCommonFunc.hasValue(poOrdCmntTxt) && poOrdCmntTxt.length() > PO_RCV_MSG_TXT_LEN) {
//            addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1320E, new String[] {createDigitErrMsg(subMsgAddPo, "PO_ORD_CMNT_TXT", poOrdCmntTxt, PO_RCV_MSG_TXT_LEN) }));
//            isErr = true;
//        }
        // END   2019/09/12 T.Ogura [QC#53424,DEL]

        // check SHIP_FROM_SO_NUM
        String shipFromSoNum = (String) resultSet.getString(EH_SHIP_FROM_SO_NUM);

        if (ZYPCommonFunc.hasValue(shipFromSoNum) && shipFromSoNum.length() > INV_NUM_LEN) {
            addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1320E, new String[] {createDigitErrMsg(subMsgAddPo, "SHIP_FROM_SO_NUM", shipFromSoNum, INV_NUM_LEN) }));
            isErr = true;
        }

        return isErr;
    }

    /**
     * checkDtlDigit
     * @param asnHdrTMsg
     * @param asnDtlTMsg
     * @return
     * @throws SQLException
     */
    private boolean checkDtlDigit(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, EDI_ASN_DTL_WRKTMsg asnDtlTMsg) {

        boolean isErr = false;

        String asnBolNum = asnDtlTMsg.asnBolNum.getValue();

        if (ZYPCommonFunc.hasValue(asnBolNum) && asnBolNum.length() > BOL_NUM_LEN) {
            StringBuilder subMsg = new StringBuilder("EDI PO#=").append(asnHdrTMsg.ediPoOrdNum.getValue());
            subMsg.append(", EDI Line#=").append(getSubMsgItemValue(asnDtlTMsg.ediPoOrdDtlLineNum.getValue(), EDI_PO_ORD_DTL_LINE_NUM_LEN));
            subMsg.append(", PO#=").append(asnHdrTMsg.poOrdNum.getValue());
            addMailMessage(asnHdrTMsg, asnDtlTMsg, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1320E, new String[] {createDigitErrMsg(subMsg, "ASN_BOL_NUM", asnBolNum, BOL_NUM_LEN) }));
            isErr = true;
        }

        return isErr;
    }

    /**
     * checkPoOrdNum
     * @param asnHdrTMsg
     * @param resultSet
     * @param subMsg
     * @return
     * @throws SQLException
     */
    private boolean checkPoOrdNum(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, ResultSet resultSet, StringBuilder subMsg) throws SQLException {

        boolean isErr = false;

        if (!ZYPCommonFunc.hasValue(getResultSetValueForPO(resultSet, PO_ORD_NUM))) {
            addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1169E, new String[] {subMsg.toString() }));
            isErr = true;

        } else if (ZYPCommonFunc.hasValue(resultSet.getString(XR_PO_ORD_NUM)) && !ZYPCommonFunc.hasValue(resultSet.getString(P1_PREFIX + PO_ORD_NUM))) {
            addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1169E, new String[] {subMsg.toString() }));
            isErr = true;
        }

        return isErr;
    }

    /**
     * checkPoStsCd
     * @param asnHdrTMsg
     * @param resultSet
     * @param subMsgAddPo
     * @return
     * @throws SQLException
     */
    private boolean checkPoStsCd(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, ResultSet resultSet, StringBuilder subMsgAddPo) throws SQLException {

        boolean isErr = false;

        String poStsCd = getResultSetValueForPO(resultSet, PO_STS_CD);

        if (ZYPCommonFunc.hasValue(poStsCd)) {

            // Check Combine SO Only
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
            paramMap.put("asnSoNum", asnHdrTMsg.asnSoNum.getValue());
            paramMap.put("ediPoOrdNum", asnHdrTMsg.ediPoOrdNum.getValue());
            paramMap.put("soPrinted", SHPG_STS.S_OR_O_PRINTED);

            Map<String, Object> cmbnSoCntMap = (Map<String, Object>) ssmBatchClient.queryObject("cntCmbnSoCnt", paramMap);

            BigDecimal soCnt = (BigDecimal) cmbnSoCntMap.get("SO_CNT");
            BigDecimal edCnt = (BigDecimal) cmbnSoCntMap.get("ED_CNT");

            if (soCnt.compareTo(edCnt) == 0) {
                return false;
            }
            
            if (PO_STS.CLOSED.equals(poStsCd)) {
                // START 2023/07/18 TZ.Win [QC#61614, MOD]
//                addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1201E, new String[] {subMsgAddPo.toString() }));
//                isErr = true;
                isHeaderClosed = true;
                // END 2023/07/18 TZ.Win [QC#61614, MOD]
            }

            if (PO_STS.CANCELLED.equals(poStsCd)) {
                addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1285E, new String[] {subMsgAddPo.toString() }));
                isErr = true;
            }

            if (PO_STS.SAVED.equals(poStsCd)) {
                addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPZM0080E) + "[" + subMsgAddPo.toString() + "]");
                isErr = true;
            }

            if (PO_STS.WAITING_FOR_APPROVAL.equals(poStsCd)) {
                addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPZM0081E) + "[" + subMsgAddPo.toString() + "]");
                isErr = true;
            }

        } else {

            addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1286E, new String[] {subMsgAddPo.toString() }));

            isErr = true;
        }

        return isErr;
    }

    /**
     * checkMandatorySerItem
     * @param asnHdrTMsg
     * @param asnSerTMsg
     * @return
     */
    private boolean checkMandatorySerItem(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, EDI_ASN_SER_NUM_WRKTMsg asnSerTMsg) {

        boolean isErr = false;

        StringBuilder sbKey = new StringBuilder();
        sbKey.append("ASN_MDSE_CD=");
        sbKey.append(asnSerTMsg.asnMdseCd.getValue());
        sbKey.append(", ASN_SO_NUM=");
        sbKey.append(asnSerTMsg.asnSoNum.getValue());
        sbKey.append(", SO_SER_ID=");
        sbKey.append(asnSerTMsg.soSerId.getValue());

        for (String itemName : MANDATORY_SER_ITEMS) {
            if (!hasValue(asnSerTMsg, itemName)) {
                EZDItemAttribute attr = asnSerTMsg.getAttr(itemName);
                addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1321E, new String[] {attr.getDBColumnName(), asnSerTMsg.getTableName(), sbKey.toString() }));
                isErr = true;
            }
        }

        return isErr;
    }

    /**
     * hasValue
     * @param tMsg EZDTMsg
     * @param itemName Check Item Name
     * @return true: has Value, false: has Not Value
     */
    private boolean hasValue(EZDTMsg tMsg, String itemName) {
        boolean hasValue = false;
        EZDItemAttribute attr = tMsg.getAttr(itemName);
        int type = attr.getType();
        switch (type) {
            case EZDItemAttrDefines.TYPE_SEISU_SYOSU:
            case EZDItemAttrDefines.TYPE_SEISU:
            case EZDItemAttrDefines.TYPE_SYOSU:
                BigDecimal valueB = tMsg.getValueBigDecimal(itemName, 0);
                if (valueB != null) {
                    hasValue = true;
                }
                break;

            default:
                String valueS = tMsg.getValueString(itemName, 0);
                if (ZYPCommonFunc.hasValue(valueS)) {
                    hasValue = true;
                }
                break;
        }

        return hasValue;
    }

    /**
     * getPoMatchTpCd
     * @param mdseCd
     * @return
     */
    private String getNextPoOrdDtlLineNum(String poOrdNum) {

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", this.globalCompanyCode);
        paramMap.put("poOrdNum", poOrdNum);

        String nextPoOrdDtlLineNum = (String) ssmBatchClient.queryObject("getNextPoOrdDtlLineNum", paramMap);

        return nextPoOrdDtlLineNum;
    }

    /**
     * getPoOrdDtlLineNum
     * @param tmsg
     * @return
     */
    private String getPoOrdDtlLineNum(EDI_ASN_DTL_WRKTMsg tmsg) {

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", this.globalCompanyCode);
        paramMap.put("asnSoNum", tmsg.asnSoNum.getValue());
        paramMap.put("asnLineNum", tmsg.asnLineNum.getValue());

        String poOrdDtlLineNum = (String) ssmBatchClient.queryObject("getPoOrdDtlLineNum", paramMap);

        return poOrdDtlLineNum;
    }

    /**
     * updateEdiAsnDtlWrk
     * @param inMap
     * @param nextPoOrdDtlLineNum
     * @return
     */
    private boolean updateEdiAsnDtlWrk(Map<String, Object> inMap, String nextPoOrdDtlLineNum) {

        EDI_ASN_DTL_WRKTMsg asnDtlTMsg = new EDI_ASN_DTL_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(asnDtlTMsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(asnDtlTMsg.asnSoNum, (String) inMap.get("ED_ASN_SO_NUM"));
        ZYPEZDItemValueSetter.setValue(asnDtlTMsg.asnLineNum, (String) inMap.get("ED_ASN_LINE_NUM"));

        asnDtlTMsg = (EDI_ASN_DTL_WRKTMsg) EZDTBLAccessor.findByKey(asnDtlTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(asnDtlTMsg.getReturnCode())) {
            addMailMessageToAdmin(asnDtlTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1171E, new String[] {"EDI_ASN_DTL_WRK" }));
            return false;
        }

        ZYPEZDItemValueSetter.setValue(asnDtlTMsg.poOrdDtlLineNum, nextPoOrdDtlLineNum);

        EZDTBLAccessor.update(asnDtlTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(asnDtlTMsg.getReturnCode())) {
            addMailMessageToAdmin(asnDtlTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1171E, new String[] {"EDI_ASN_DTL_WRK" }));
            return false;
        }

        return true;
    }

    /**
     * callPrUpdateAPI
     * @param asnHdrTMsg
     * @param asnDtlList
     * @param vndCd
     * @param shipFromSoNum
     * @param npzc104001Pmsg
     * @return boolean true : OK, false : NG
     */
    // START 2019/09/12 T.Ogura [QC#53424,MOD]
//    private boolean callPrUpdateAPI(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, List<EDI_ASN_DTL_WRKTMsg> asnDtlList, String vndCd, String shipFromSoNum, String poOrdCmntTxt, NPZC104001PMsg npzc104001Pmsg) {
    private boolean callPrUpdateAPI(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, List<EDI_ASN_DTL_WRKTMsg> asnDtlList, String vndCd, String shipFromSoNum, NPZC104001PMsg npzc104001Pmsg) {
    // END   2019/09/12 T.Ogura [QC#53424,MOD]

        boolean isNormal = true;
        for (int i = 0; i < asnDtlList.size(); i++) {

            // Get update PO Requisition & Tech Request
            List<Map<String, Object>> prDetailList = getPrByPrUpdateApi(asnHdrTMsg.poOrdNum.getValue(), asnDtlList.get(i).poOrdDtlLineNum.getValue());

            if (!prDetailList.isEmpty() && prDetailList != null) {
                Map<String, Object> updatePrDetail = prDetailList.get(0);

                if (updatePrDetail.get("ORG_PRCH_REQ_NUM") == null || updatePrDetail.get("BASE_PRCH_REQ_NUM") == null) {
                    continue;
                }

                NPZC103001 dPZC103001 = new NPZC103001();
                // ORG_PRCH_REQ_DTL Update.
                NPZC103001PMsg pMsg = new NPZC103001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC1030_MODE_UPDATE);
                ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC1030_EVENT_ASN);
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, (String) updatePrDetail.get("ORG_PRCH_REQ_NUM"));
                // Detail
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineNum, (String) updatePrDetail.get("ORG_PRCH_REQ_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineSubNum, (BigDecimal) updatePrDetail.get("ORG_PRCH_REQ_LINE_SUB_NUM"));
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).shipQty, asnDtlList.get(i).asnQty.getValue());
                pMsg.prchReqInfo.setValidCount(1);

                dPZC103001.execute(pMsg, ONBATCH_TYPE.BATCH);

                List<String> errList;
                errList = S21ApiUtil.getXxMsgIdList(pMsg);

                if (errList.size() > 0) {
                    for (String xxMsgId : errList) {
                        if (xxMsgId.endsWith("E")) {
                            // START 2019/07/24 M.Naito [QC#51917,MOD]
//                          String errorApi = "NSZC043001:" + S21MessageFunc.clspGetMessage(xxMsgId);
                            String errorApi = "NPZC103001:" + S21MessageFunc.clspGetMessage(xxMsgId);
                            // END 2019/07/24 M.Naito [QC#51917,MOD]
                            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                            addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                            isNormal = false;
                        }
                    }

                }

                // BASE_PRCH_REQ_DTL Update.
                pMsg = new NPZC103001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC1030_MODE_UPDATE);
                ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC1030_EVENT_ASN);
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqNum, (String) updatePrDetail.get("BASE_PRCH_REQ_NUM"));
                // Detail
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineNum, (String) updatePrDetail.get("BASE_PRCH_REQ_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).prchReqLineSubNum, (BigDecimal) updatePrDetail.get("BASE_PRCH_REQ_LINE_SUB_NUM"));
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(0).shipQty, asnDtlList.get(i).asnQty.getValue());
                pMsg.prchReqInfo.setValidCount(1);

                dPZC103001.execute(pMsg, ONBATCH_TYPE.BATCH);

                errList = S21ApiUtil.getXxMsgIdList(pMsg);

                if (errList.size() > 0) {
                    for (String xxMsgId : errList) {
                        if (xxMsgId.endsWith("E")) {
                            // START 2019/07/24 M.Naito [QC#51917,MOD]
//                          String errorApi = "NSZC043001:" + S21MessageFunc.clspGetMessage(xxMsgId);
                            String errorApi = "NPZC103001:" + S21MessageFunc.clspGetMessage(xxMsgId);
                            // END 2019/07/24 M.Naito [QC#51917,MOD]
                            addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                            addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                            isNormal = false;
                        }
                    }

                }
            }

        }

        return isNormal;
    }

    private void setSetPoDtlLine(NPZC104001PMsg pMsg, int k, String setMdseLine, Map<String, Object> inMap) {
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).setPoOrdDtlLineNum, setMdseLine);
        // QC#54373
//        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).dispPoDtlLineNum, (String) inMap.get(DPD_DISP_PO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poLineTpCd, (String) inMap.get(DPD_PO_LINE_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMdseCmpsnTpCd, (String) inMap.get(DPD_PO_MDSE_CMPSN_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).mdseDescShortTxt, (String) inMap.get(DPD_MDSE_DESC_SHORT_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poInvQty, (BigDecimal) inMap.get(DPD_PO_INV_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poDispUomCd, (String) inMap.get(DPD_PO_DISP_UOM_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).thisMthFobCostAmt, (BigDecimal) inMap.get(PD_THIS_MTH_FOB_COST_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entDealNetUnitPrcAmt, (BigDecimal) inMap.get(DPD_ENT_DEAL_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlDealNetAmt, pMsg.poLineInfo.no(k).entDealNetUnitPrcAmt.getValue().multiply(pMsg.poLineInfo.no(k).poQty.getValue()));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entFuncNetUnitPrcAmt, (BigDecimal) inMap.get(DPD_ENT_FUNC_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).entPoDtlFuncNetAmt, pMsg.poLineInfo.no(k).entFuncNetUnitPrcAmt.getValue().multiply(pMsg.poLineInfo.no(k).poQty.getValue()));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).exchRate, (BigDecimal) inMap.get(DPD_EXCH_RATE));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custUomCd, (String) inMap.get(PD_CUST_UOM_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).destRtlSwhCd, (String) inMap.get(DPD_DEST_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).srcRtlSwhCd, (String) inMap.get(DPD_SRC_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).invtyLocCd, (String) inMap.get(DPD_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvDt, (String) inMap.get(DPD_RQST_RCV_DT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstRcvTm, (String) inMap.get(DPD_RQST_RCV_TM));
        // START 2023/07/20 S.Dong [QC#61638, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).rqstShipDt, (String) inMap.get(DPD_RQST_SHIP_DT));
        // END 2023/07/20 S.Dong [QC#61638, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).frtCondCd, (String) inMap.get(DPD_FRT_COND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origMdseCd, (String) inMap.get(DPD_ORIG_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).fromStkStsCd, (String) inMap.get(DPD_FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).toStkStsCd, (String) inMap.get(DPD_TO_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).adminPsnCd, (String) inMap.get(PD_ADMIN_PSN_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poMatchTpCd, (String) inMap.get(DPD_PO_MATCH_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).ordQty, (BigDecimal) inMap.get(PD_ORD_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdNum, (String) inMap.get(PD_CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineNum, (String) inMap.get(PD_CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoDtlLineSubNum, (String) inMap.get(PD_CPO_DTL_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoNum, (String) inMap.get(PD_CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).custIssPoDt, (String) inMap.get(PD_CUST_ISS_PO_DT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).cpoOrdTpCd, (String) inMap.get(PD_CPO_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).billToCustCd, (String) inMap.get(PD_BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).sellToCustCd, (String) inMap.get(PD_SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shpgPlnNum, (String) inMap.get(PD_SHPG_PLN_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqNum, (String) inMap.get(DPD_PRCH_REQ_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineNum, (String) inMap.get(DPD_PRCH_REQ_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).prchReqLineSubNum, (BigDecimal) inMap.get(DPD_PRCH_REQ_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefNum, (String) inMap.get(DPD_TRX_REF_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineNum, (String) inMap.get(DPD_TRX_REF_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).trxRefLineSubNum, (String) inMap.get(DPD_TRX_REF_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslDtlPk, (BigDecimal) inMap.get(DPD_ASL_DTL_PK));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).aslUnitPrcAmt, (BigDecimal) inMap.get(DPD_ASL_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).shipFromSoNumListTxt, (String) inMap.get(DPD_SHIP_FROM_SO_NUM_LIST_TXT));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndInvtyLocCd, (String) inMap.get(DPD_VND_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndIssPoOrdNum, (String) inMap.get(DPD_VND_ISS_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).proNum, (String) inMap.get(DPD_PRO_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).vndPoAckStsCd, (String) inMap.get(DPD_VND_PO_ACK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdNum, (String) inMap.get(DPD_ORIG_PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origPoOrdDtlLineNum, (String) inMap.get(DPD_ORIG_PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).origDispPoDtlLineNum, (String) inMap.get(DPD_ORIG_DISP_PO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).svcConfigMstrPk, (BigDecimal) inMap.get(DPD_SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poSendTs, (String) inMap.get(DPD_PO_SEND_TS));
        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(k).poOrdDtlCmntTxt, (String) inMap.get(PD_PO_ORD_DTL_CMNT_TXT));
    }

    // START 2018/03/23 S.Katsuma [QC#24353,ADD]
    private String getCarrCdFromOtbdCarr(EDI_ASN_DTL_WRKTMsg asnDtlTMsg) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
        paramMap.put(BIND_CARR_CD, asnDtlTMsg.asnCarrCd.getValue());

        List<String> carrCdFromOtbdCarr = (List<String>) ssmBatchClient.queryObjectList("getCarrCdFromOtbdCarr", paramMap);
        if (carrCdFromOtbdCarr != null && !carrCdFromOtbdCarr.isEmpty()) {
            return carrCdFromOtbdCarr.get(0);
        } else {
            return null;
        }
    }

    /**
     * QC#26964/QC#26965 cmbnSoShipProcess
     * @param asnHdrTMsg asnHdrTMsg
     * @param asnDtlList List<EDI_ASN_DTL_WRKTMsg>
     * @param isCmbn boolean
     * @return boolean
     */
    private boolean cmbnSoShipProcess(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, List<EDI_ASN_DTL_WRKTMsg> asnDtlList, boolean isCmbn) {

        String preSoNum = "";
        boolean isErrPrc = false;
        ArrayList<NLZC210001PMsg> soConfPList = new ArrayList<NLZC210001PMsg>();
        ArrayList<NLZC210002PMsg> soConfSerPList = new ArrayList<NLZC210002PMsg>();
        int count = 0;
        int recordSumQty = 0;
        int shpgOrdDtlCnt = 0;
        int shpgOrdDtlSumQty = 0;
        boolean isApiCall = false;

        for (EDI_ASN_DTL_WRKTMsg ediAsnDtlWrkTMsg : asnDtlList) {

            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(BIND_GLBL_CMPY_CD, this.globalCompanyCode);
            if (isCmbn) {
                paramMap.put("ediPoOrdNum", asnHdrTMsg.ediPoOrdNum.getValue());
                paramMap.put("ediPoOrdDtlLineNum", ediAsnDtlWrkTMsg.ediPoOrdDtlLineNum.getValue());
            } else {
                paramMap.put("soNum", asnHdrTMsg.ediPoOrdNum.getValue());
                paramMap.put("soSlpNum", ediAsnDtlWrkTMsg.ediPoOrdDtlLineNum.getValue());
            }
            paramMap.put("soPrinted", SHPG_STS.S_OR_O_PRINTED);

            List<Map<String, Object>> cmbnSoList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getCmbnSoList", paramMap);

            if (cmbnSoList == null || cmbnSoList.isEmpty()) {
                continue;
            }

            for (Map<String, Object> soMap : cmbnSoList) {

                boolean isError = false;
                String soNum = (String) soMap.get("SO_NUM");
                String soSlpNum = (String) soMap.get("SO_SLP_NUM");

                if (!preSoNum.equals(soNum)) {

                    if (isApiCall && !soConfPList.isEmpty()) {
                        // Call API
                        NLZC210001 soConfApi = new NLZC210001();
                        soConfApi.execute(soConfPList, soConfSerPList, ONBATCH_TYPE.BATCH);

                        boolean isErr = soConfApiResult(asnHdrTMsg, soConfPList, soConfSerPList);
                        if(isErr) {
                            break;
                        }
                    }

                    soConfPList = new ArrayList<NLZC210001PMsg>();
                    soConfSerPList = new ArrayList<NLZC210002PMsg>();

                    count = 0;
                    recordSumQty = 0;
                    shpgOrdDtlCnt = 0;
                    shpgOrdDtlSumQty = 0;

                    // Get Shipping Order Information
                    Map<String, String> ssmParam = new HashMap<String, String>();
                    ssmParam.put("glblCmpyCd", this.globalCompanyCode);
                    ssmParam.put("soNum", soNum);

                    Map<String, Object> cntSoMap = (Map<String, Object>) ssmBatchClient.queryObject("getSoSum", ssmParam);

                    if (cntSoMap != null && !cntSoMap.isEmpty()) {

                        // Create Record
                        shpgOrdDtlCnt = ((BigDecimal) cntSoMap.get("COUNT")).intValue();
                        shpgOrdDtlSumQty = ((BigDecimal) cntSoMap.get("SUM_ORD_QTY")).intValue();

                    } else {
                        addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage("ZZZM9006E", new String[] {"Shipping Order Data" }));
                        addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage("ZZZM9006E", new String[] {"Shipping Order Data" }));
                        isError = true;
                        isErrPrc = true;
                    }
                }

                if (isError) {
                    continue;
                }

                // so conf param set
                NLZC210001PMsg pMsg = new NLZC210001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(pMsg.whCd, (String) soMap.get("INVTY_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.soNum, soNum);
                ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, (String) soMap.get("SCE_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.shipDtTmTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime("HHmmss")));
                ZYPEZDItemValueSetter.setValue(pMsg.soSlpNum, soSlpNum);
                ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd_DT, SO_PROC_STS.SHIP);
                ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) soMap.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.fromStkStsCd, (String) soMap.get("FROM_STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.shipQty, ediAsnDtlWrkTMsg.asnQty.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.bolNum, ediAsnDtlWrkTMsg.asnBolNum.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.vndCd, ediAsnDtlWrkTMsg.asnCarrCd.getValue());
                ZYPEZDItemValueSetter.setValue(pMsg.proNum, ediAsnDtlWrkTMsg.asnProNum.getValue());

                if (ZYPCommonFunc.hasValue(asnHdrTMsg.asnTotFrtAmt)) {

                    ZYPEZDItemValueSetter.setValue(pMsg.totFrtAmt, ediAsnDtlWrkTMsg.asnTotFrtAmt.getValue());

                } else {

                    ZYPEZDItemValueSetter.setValue(pMsg.totFrtAmt, BigDecimal.ZERO);
                }

                soConfPList.add(pMsg);
                isApiCall = true;
                count++;
                recordSumQty = recordSumQty + pMsg.shipQty.getValueInt();

                // set Serial
                Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put("glblCmpyCd", this.globalCompanyCode);
                if (isCmbn) {
                    paramMap.put("ediPoOrdNum", asnHdrTMsg.ediPoOrdNum.getValue());
                    paramMap.put("ediPoOrdDtlLineNum", ediAsnDtlWrkTMsg.ediPoOrdDtlLineNum.getValue());
                } else {
                    paramMap.put("soNum", asnHdrTMsg.ediPoOrdNum.getValue());
                    paramMap.put("soSlpNum", ediAsnDtlWrkTMsg.ediPoOrdDtlLineNum.getValue());
                }
                ssmParam.put("asnSoNum", asnHdrTMsg.asnSoNum.getValue());
                List<Map<String, Object>> cntSoMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getCmbnAsnSerNumWrk", ssmParam);

                if (cntSoMapList != null && !cntSoMapList.isEmpty()) {
                    for (Map<String, Object> map : cntSoMapList) {

                        NLZC210002PMsg pMsg2 = new NLZC210002PMsg();
                        ZYPEZDItemValueSetter.setValue(pMsg2.glblCmpyCd, this.globalCompanyCode);
                        ZYPEZDItemValueSetter.setValue(pMsg2.whCd, (String) soMap.get("INVTY_LOC_CD"));
                        ZYPEZDItemValueSetter.setValue(pMsg2.soNum, soNum);
                        ZYPEZDItemValueSetter.setValue(pMsg2.sceOrdTpCd, (String) soMap.get("SCE_ORD_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(pMsg2.shipDtTmTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime("HHmmss")));
                        ZYPEZDItemValueSetter.setValue(pMsg2.soProcStsCd, SO_PROC_STS.SHIP);
                        ZYPEZDItemValueSetter.setValue(pMsg2.soSlpNum, soSlpNum);
                        ZYPEZDItemValueSetter.setValue(pMsg2.mdseCd, (String) soMap.get("MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(pMsg2.serNum, (String) map.get("SER_NUM"));
                        ZYPEZDItemValueSetter.setValue(pMsg2.serTakeDtTmTs, pMsg2.shipDtTmTs.getValue());
                        soConfSerPList.add(pMsg2);
                    }
                }
                // if fully SO is shipped, set proc status code is SHIP
                if (shpgOrdDtlCnt == count && shpgOrdDtlSumQty == recordSumQty) {

                    for (int i = 0; i < soConfPList.size(); i++) {

                        ZYPEZDItemValueSetter.setValue(soConfPList.get(i).soProcStsCd, SO_PROC_STS.SHIP);
                    }
                }

                preSoNum = soNum;
            }
        }

        if (isErrPrc) {
            return false;
        }

        if (isApiCall && !soConfPList.isEmpty()) {
            // Call API
            NLZC210001 soConfApi = new NLZC210001();
            soConfApi.execute(soConfPList, soConfSerPList, ONBATCH_TYPE.BATCH);

            boolean isErr = soConfApiResult(asnHdrTMsg, soConfPList, soConfSerPList);
            if(isErr) {
                return false;
            }
        }

        // Insert SHPG_ORD_PRO_NUM_LIST
        insertShipOrdProNumList(soConfPList);

        return true;
    }

    /**
     * QC#26964/QC#26965. insertShipOrdProNumList
     * @param soConfPList List<NLZC210001PMsg>
     */
    private void insertShipOrdProNumList(List<NLZC210001PMsg> soConfPList) {

        HashMap<String, ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg>> insertList = new HashMap<String, ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg>>();

        for (int i = 0; i < soConfPList.size(); i++) {

            NLZC210001PMsg pMsg = soConfPList.get(i);

            String soNum = pMsg.soNum.getValue();
            String sceOrdTpCd = pMsg.sceOrdTpCd.getValue();

            ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg> list = new ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg>();
            if (insertList.containsKey(soNum)) {
                list = insertList.get(soNum);
            } else {
                insertList.put(soNum, list);
            }

            SHPG_ORD_PRO_NUM_LISTTMsg sopn = new SHPG_ORD_PRO_NUM_LISTTMsg();

            ZYPEZDItemValueSetter.setValue(sopn.trxHdrNum, soNum);
            ZYPEZDItemValueSetter.setValue(sopn.trxLineNum, pMsg.soSlpNum);
            ZYPEZDItemValueSetter.setValue(sopn.proNum, pMsg.proNum);
            ZYPEZDItemValueSetter.setValue(sopn.sceOrdTpCd, sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(sopn.inbdOtbdCd, INBD_OTBD.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(sopn.proCratDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddhhmmss"));
            ZYPEZDItemValueSetter.setValue(sopn.proSendFlg, ZYPConstant.FLG_ON_Y);

            list.add(sopn);

        }

        // sort
        for (Map.Entry<String, ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg>> entry : insertList.entrySet()) {
            Collections.sort(entry.getValue(), new NPAB110001Constant.Comp("proNum"));
        }

        // insert
        for (Map.Entry<String, ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg>> entry : insertList.entrySet()) {
            ArrayList<SHPG_ORD_PRO_NUM_LISTTMsg> list = entry.getValue();

            String mstrProNum = null;
            for (SHPG_ORD_PRO_NUM_LISTTMsg tMsg : list) {
                if (mstrProNum == null) {
                    mstrProNum = tMsg.proNum.getValue();
                }

                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.shpgOrdProNumListPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SHPG_ORD_PRO_NUM_LIST_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.mstrProNum, mstrProNum);

                EZDTBLAccessor.insert(tMsg);
            }
        }
    }

    private boolean soConfApiResult(EDI_ASN_HDR_WRKTMsg asnHdrTMsg, ArrayList<NLZC210001PMsg> soConfPList, ArrayList<NLZC210002PMsg> soConfSerPList) {

        if (soConfPList != null && !soConfPList.isEmpty()) {

            for (NLZC210001PMsg shipConfApiPMsg : soConfPList) {

                List<String> apiMsgList = S21ApiUtil.getXxMsgIdList(shipConfApiPMsg);

                if (!apiMsgList.isEmpty()) {

                    for (String msgId : apiMsgList) {

                        if (ZYPCommonFunc.hasValue(msgId)) {

                            if (msgId.endsWith("E")) {

                                String errorApi = "NLZC210001:" + S21MessageFunc.clspGetMessage(msgId);
                                addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                                addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                                return true;
                            }
                        }
                    }
                }
            }
        }

        if (soConfSerPList != null && !soConfSerPList.isEmpty()) {

            for (NLZC210002PMsg shipConfSerApiPMsg : soConfSerPList) {

                List<String> apiMsgList = S21ApiUtil.getXxMsgIdList(shipConfSerApiPMsg);

                if (!apiMsgList.isEmpty()) {

                    for (String msgId : apiMsgList) {

                        if (ZYPCommonFunc.hasValue(msgId)) {

                            if (msgId.endsWith("E")) {

                                String errorApi = "NLZC210001:" + S21MessageFunc.clspGetMessage(msgId);
                                addMailMessageToAdmin(asnHdrTMsg.asnSoNum.getValue(), S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                                addMailMessage(asnHdrTMsg, null, S21MessageFunc.clspGetMessage(MSG_ID_NPAM1178E, new String[] {errorApi }));
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
    // START 2023/07/18 TZ.Win [QC#61614, DEL]
    // START 2023/05/24 TZ.Win [QC#60960, ADD]
//    private BigDecimal getPoDtlAsnDtlCount(EDI_ASN_HDR_WRKTMsg asnHdrTMsg) {
//
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put(BIND_GLBL_CMPY_CD, globalCompanyCode);
//        params.put(BIND_PO_ORD_NUM, asnHdrTMsg.poOrdNum.getValue());
//        params.put(BIND_ASN_SO_NUM, asnHdrTMsg.asnSoNum.getValue());
//        params.put(BIND_SLS_DT, this.salesDate);
//        
//        List<String> poLineStsCdList = new ArrayList<String>();
//
//        poLineStsCdList.add(PO_LINE_STS.CLOSED);
//        poLineStsCdList.add(PO_LINE_STS.OPEN_FOR_INVOICE);
//        params.put(BIND_PO_LINE_STS_CD_LIST, poLineStsCdList);
//
//        return (BigDecimal) ssmBatchClient.queryObject("getPoDtlAsnDtlCount", params);
//    }
    // END 2023/05/24 TZ.Win [QC#60960, ADD]
    // END 2023/07/18 TZ.Win [QC#61614, DEL]
}
