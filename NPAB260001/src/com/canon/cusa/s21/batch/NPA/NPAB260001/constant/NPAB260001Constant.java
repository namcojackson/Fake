/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB260001.constant;

/**
 * <pre>
 *Send PO to EDI Vendor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/11   CITS            T.Hakodate      Created         QC#
 * 2020/10/14   CITS            J.Evangelista   Update          QC#57795
 * 2021/04/30   CITS            K.Ogino         Update          QC#58769
 * 2022/03/08   CITS            R.Azucena       Update          QC#59752
 * 2023/03/03   Hitachi         E.Watabe        Update          QC#61128
 * 2023/09/25   Hitachi         G.Quan          Update          QC#61608
 */

public class NPAB260001Constant {

    // ********************************************************************************
    // DEFAULT CONSTANT
    // ********************************************************************************
    public static final String TPL_FRT_COND_CD_S03 = "S03";

    public static final String TPL_FRT_COND_CD_S04 = "S04";

    public static final String TPL_FRT_COND_CD_S05 = "S05";

    public static final String TPL_FRT_COND_CD_S06 = "S06";

    public static final long DEF_PO_SO_CMBN_MIN = 10;

    public static final long MIN_CALC_MULT_VAL = 60000;

    public static final String INTERFACE_ID_NPAI0400 = "NPAI0400";

    public static final String INTERFACE_ID_NPAI0500 = "NPAI0500";
    
    // START 2023/03/03 E.Watabe [QC#61128,ADD]
    public static final String INTERFACE_ID_NPAI0600 = "NPAI0600";
    // END 2023/03/03 E.Watabe [QC#61128,ADD]

    // START 2023/09/25 G.Quan [QC#61608, ADD]
    public static final String INTERFACE_ID_NPAI0800 = "NPAI0800";
    // END 2023/09/25 G.Quan [QC#61608, ADD]

    public static final String CUR_DT_TM_FMT = "yyyyMMddHHmmss";

    public static final String TIME_FORMAT = "yyyyMMddHHmmssSSS";

    public static final String VND_PO_ACK_LINS_STS_TXT = "Sent PO via Interface";

    public static final String EVENT_ID_SEND_PO = "Send PO";

    public static final String DS_PO_TP_SO = "SO";

    public static final String OTBD_PO_REC_ID_H1 = "H1";

    public static final String OTBD_PO_REC_ID_M1 = "M1";

    public static final String OTBD_PO_REC_ID_L1 = "L1";

    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    public static final String MAIL_GROUP_ID_TO = "NPAB2600";

    public static final String LINE_FEED_CODE = "\r\n";

    public static final String MAIL_TEMPLATE_ID = "NPAB2600M001";

    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    public static final String MAIL_TEMPLATE_KEY_BATCH_ID = "batchId";

    public static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    public static final String BUSINESS_ID = "NPAB2600";

    public static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    // ********************************************************************************
    // ERROR MESSAGE
    // ********************************************************************************
    public static final String NLGM0049E = "NLGM0049E";

    public static final String NPAM1172E = "NPAM1172E";

    public static final String NWBM0092E = "NWBM0092E";

    public static final String STAR = "*";

    // ********************************************************************************
    // String
    // ********************************************************************************
    public static final String CARR_CD = "CARR_CD";

    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    public static final String PO_ORD_NUM = "PO_ORD_NUM";

    public static final String PO_ORD_SRC_CD = "PO_ORD_SRC_CD";

    public static final String PRCH_GRP_CD = "PRCH_GRP_CD";

    public static final String DS_ORD_CATG_CD = "DS_ORD_CATG_CD";

    public static final String DS_ORD_RSN_CD = "DS_ORD_RSN_CD";

    public static final String CARR_TP_CD = "CARR_TP_CD";

    public static final String CARR_ACCT_NUM = "CARR_ACCT_NUM";

    public static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    public static final String VND_PO_ACK_STS_CD_IW = "IW";

    public static final String VND_PO_ACK_STS_CD_H = "H";

    public static final String ASL_MDSE_CD = "ASL_MDSE_CD";

    public static final String MDSE_CD = "MDSE_CD";

    public static final String MDSE_NM = "MDSE_NM";

    public static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    public static final String PO_DISP_QTY = "PO_DISP_QTY";

    public static final String ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";

    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    public static final String SHIP_TO_ADDL_LOC_NM = "SHIP_TO_ADDL_LOC_NM";

    public static final String SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";

    public static final String SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    public static final String SHIP_TO_POST_CD = "SHIP_TO_POST_CD";

    public static final String SHIP_TO_PROV_NM = "SHIP_TO_PROV_NM";

    public static final String SHIP_TO_CTRY_CD = "SHIP_TO_CTRY_CD";

    public static final String VND_UOM_CD = "VND_UOM_CD";

    public static final String VND_CD = "VND_CD";

    public static final String PO_QLFY_CD = "PO_QLFY_CD";

    public static final String DS_PO_TP_CD = "DS_PO_TP_CD";

    public static final String PO_APVL_DT = "PO_APVL_DT";

    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    public static final String WRK_TEL_NUM = "WRK_TEL_NUM";

    public static final String PO_ACK_HDR_SQ = "PO_ACK_HDR_SQ";

    public static final String PO_ACK_DTL_SQ = "PO_ACK_DTL_SQ";

    public static final String CMBN_PO_SO_XREF_SQ = "CMBN_PO_SO_XREF_SQ";

    public static final String PO_QTY = "PO_QTY";

    public static final String RQST_ORD_QTY = "RQST_ORD_QTY";

    public static final String CUST_UOM_CD = "CUST_UOM_CD";

    public static final String SP_DEAL_UNIT_PRC_AMT = "SP_DEAL_UNIT_PRC_AMT";

    public static final String CTAC_PTNR_PSN_TEL_NUM = "CTAC_PTNR_PSN_TEL_NUM";

    public static final String SO_NUM = "SO_NUM";

    public static final String SO_SLP_NUM = "SO_SLP_NUM";

    public static final String TRD_PTNR_FRT_CHRG_METH_CD = "TRD_PTNR_FRT_CHRG_METH_CD";

    public static final String TRD_PTNR_CARR_CD = "TRD_PTNR_CARR_CD";

    public static final String RQST_RCV_DT = "RQST_RCV_DT";

    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    public static final String CCY_CD = "CCY_CD";

    public static final String CARR_NM = "CARR_NM";

    public static final String SO_CRAT_TS = "SO_CRAT_TS";

    public static final String RDD_DT = "RDD_DT";

    public static final String SO_MSG_DESC_TXT = "SO_MSG_DESC_TXT";

    public static final String SO_CUST_LINE_LOC_NM = "SO_CUST_LINE_LOC_NM";

    public static final String SO_CUST_LINE_ADDR = "SO_CUST_LINE_ADDR";

    public static final String SHIP_TO_LINE_ADDR = "SHIP_TO_LINE_ADDR";

    // START 2020/10/14 J.Evangelista [QC#57795,ADD]
    public static final String SVC_LFTGT_DELY_FLG = "SVC_LFTGT_DELY_FLG";
    
    public static final String SVC_INSD_DELY_FLG = "SVC_INSD_DELY_FLG";

    public static final String SVC_STRT_TRUCK_FLG = "SVC_STRT_TRUCK_FLG";
    // END   2020/10/14 J.Evangelista [QC#57795,ADD]

    // START 2022/03/08 R.Azucena[QC#59752, ADD]
    public static final String ADD_SHIP_TO_01_REF_CMNT_TXT = "ADD_SHIP_TO_01_REF_CMNT_TXT";
    // END 2022/03/08 R.Azucena[QC#59752, ADD]

    // ********************************************************************************
    // BIND
    // ********************************************************************************
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    public static final String BIND_CARR_CD = "carrCd";

    public static final String BIND_CPO_ORD_NUM = "cpoOrdNum";

    public static final String BIND_PRCH_GRP_CD = "prchGrpCd";

    public static final String BIND_CARR_TP_CD = "carrTpCd";

    public static final String BIND_DS_ORD_CATG_CD = "dsOrdCatgCd";

    public static final String BIND_DS_ORD_RSN_CD = "dsOrdRsnCd";

    public static final String BIND_PO_ORD_SRC_CD = "poOrdSrcCd";

    public static final String BIND_PKG_UOM_CD = "pkgUomCd";

    public static final String BIND_VND_CD = "vndCd";

    public static final String BIND_PO_ORD_NUM = "poOrdNum";

    public static final String BIND_PO_QLFY_CD = "poQlfyCd";

    public static final String BIND_PO_MSG_TP_CD_03 = "poMsgTpCd03";

    public static final String BIND_PO_MSG_TP_CD_04 = "poMsgTpCd04";

    public static final String BIND_PO_MSG_TP_CD_05 = "poMsgTpCd05";

    public static final String BIND_EDI_SEND_ID = "ediSendId";

    public static final String BIND_PO_STS_CD_LIST = "poStsCdList";

    public static final String BIND_VND_SYS_TP_CD_LIST = "vndSysTpCdList";

    // START 2020/10/14 J.Evangelista [QC#57795,DEL]
//    public static final String BIND_EDI_SEND_STS_CD_LIST = "ediSendStsCdList";
//
//    public static final String BIND_SHPG_STS_CD = "shpgStsCd";
//
//    public static final String BIND_SCE_ORD_TP_CD_LIST = "sceOrdTpCdList";
//
//    public static final String BIND_WMS_DROP_RQST_FLG = "wmsDropRqstFlg";
//
//    public static final String BIND_SO_CUST_DATA_TP_CD = "soCustDataTpCd";
//
//    public static final String BIND_WMS_DROP_FLG = "wmsDropFlg";
//
//    public static final String BIND_TRX_HDR_NUM = "trxHdrNum";
//
//    public static final String BIND_SO_NUM = "soNum";
//
//    public static final String BIND_WAIT_START = "waitStartTime";
//
//    public static final String BIND_WAIT_END = "waitEndTime";
//
//    public static final String BIND_SO_EDI_SEND_STS_CD = "soEdiSendStsCd";
//
//    public static final String BIND_SO_EDI_SEND_STS_CD_LIST = "soEdiSendStsCdList";
    // END   2020/10/14 J.Evangelista [QC#57795,DEL]

    // START 2020/10/14 J.Evangelista [QC#57795,ADD]
    public static final String BIND_REQ_SPCL_HDLG = "reqSpclHdlg";

    public static final String BIND_SVC_LFTGT_DELY_FLG = "svcLftgtDelyFlg";

    public static final String BIND_SVC_INSD_DELY_FLG = "svcInsdDelyFlg";

    public static final String BIND_SVC_STRT_TRUCK_FLG = "svcStrtTruckFlg";
    // END   2020/10/14 J.Evangelista [QC#57795,ADD]

    // QC#58769
    public static final String BIND_DS_ORD_TP_CD = "dsOrdTpCd";
    public static final String DS_ORD_TP_CD = "DS_ORD_TP_CD";
}
