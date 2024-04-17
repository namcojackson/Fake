/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB125001.constant;

/**
 * <pre>
 * NPAB125001:Receiving ASN for CUSA Domestic
 * 
 * Date         Company      Name             Create/Update    Defect No
 * ----------------------------------------------------------------------
 * 2013/07/29   Hitachi      T.Kanasaka       Create           QC1388
 * 2014/03/18   Hitachi      T.Kanasaka       Update           CSA-028
 * 2016/02/06   CITS         T.Hakodate       AllUpdate        CSA 
 * 2019/02/21   CITS         M.Naito          Update           QC#30311
 * 2019/08/15   CITS         K.Ogino          Update           QC#52677
 * 2019/09/11   CITS         T.Wada           Update           QC#53323
 *</pre>
 */
public class NPAB125001Constant {

    /** MDSE_CD_UPD_FLG */
    public static final String MDSE_CD_UPD_FLG = "MDSE_CD_UPD_FLG";

    /** Failed to update @ table. @ is @. */
    public static final String NPAM1003E = "NPAM1003E";

    /** The corresponding [@] does not exist.. */
    public static final String NMAM0039E = "NMAM0039E";

    /** It failed to insert the [@]. PK [@]. */
    public static final String NPAM1499E = "NPAM1499E";

    /** SHPG_MODE_CD does not exist. */
    public static final String NPAM1500E = "NPAM1500E";

    /** Failed to obtain DeliveryDate. */
    public static final String NPAM1501E = "NPAM1501E";

    /** SHPG_ORD_CUST_DTL does not exist. */
    public static final String NPAM1502E = "NPAM1502E";

    /** SHPG_MODE_CD does not exist. */
    public static final String NPAM1503E = "NPAM1503E";

    /** DB error occurred. */
    public static final String NPAM1504E = "NPAM1504E";

    /** "drop ship WH code" does not exist in "VAR_CHAR_CONST". */
    public static final String NPAM1505E = "NPAM1505E";

    /** Parameter delimiter */
    public static final String DELIMITER = ",";

    /** CST_DEBUG_MSG_LVL. */
    public static final int CST_DEBUG_MSG_LVL = 1;

    /** length of SO# */
    public static final int SO_NUM_LENGTH = 8;

    /** line separator */
    public static final String NEW_LINE = "line.separator";

    /** DUMMY_SO#_GRP_CMPY */
    public static final String DUMMY_SO_GRP_CMPY = "DUMMY_SO#_GRP_CMPY";

    /** TRX_LINE_SUB_NUM_000. */
    public static final String TRX_LINE_SUB_NUM_000 = "000";

    /** CPO_DTL_LINE_SUB_NUM_000. */
    public static final String CPO_DTL_LINE_SUB_NUM_000 = "000";

    /** EDI_SUB_NUM_001. */
    public static final String EDI_SUB_NUM_001 = "001";

    /** NULL_VAL */
    public static final String NULL_VAL = "*";

    /** MODE_DAILY */
    public static final String MODE_DAILY = "1";

    /** MODE_NIGHT */
    public static final String MODE_NIGHT = "2";

    /** FETCH_SIZE. */
    public static final int FETCH_SIZE = 1000;

    /** batch mode S21 WS WebService . */
    public static final String IF_ID_NPAA0051 = "NPAA0051";

    /** batch mode S21 WS EDI Paets . */
    public static final String IF_ID_NPAA0061 = "NPAA0061";

    /** BUSHINESS_ID. */
    public static final String BUSINESS_ID = "NPAB125001";

    /** "CUSA Global Company Code" does not exist in "VAR_CHAR_CONST". */
    public static final String NPAM1496E = "NPAM1496E";

    /**
     * "CUSA EDI Trading Partner Code" does not exist in
     * "VAR_CHAR_CONST".
     */
    public static final String NPAM1497E = "NPAM1497E";

    /**
     * Date Format DATE
     */
    public static final String TIME_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * Date Format DATE
     */
    public static final String TIME_FORMAT_DT = "yyyyMMdd";

    /** Actl Arv Date */
    public static final String ACTL_ARV_DT = "ACTL_ARV_DT";

    /** Delivery Date */
    public static final String DELIVERY_DATE = "DELIVERY_DATE";

    /** DB Return CD: Normal End */
    public static final String DB_RETURN_CD_NORMAL_END = "0000";

    /** map key start ************************************************************/

    /** map key : soNum. */
    public static final String BIND_SO_NUM = "soNum";

    /** map key : soCustDataTpCd. */
    public static final String BIND_SO_CUST_DATA_TP_CD = "soCustDataTpCd";

    /** map key : soSlpNum. */
    public static final String BIND_SO_SLP_NUM = "soSlpNum";

    /** map key : procPgmId. */
    public static final String BIND_PROC_PGM_ID = "procPgmId";

    /** map key : glblCmpyCd. */
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** map key : calSubTpCd. */
    public static final String BIND_CAL_SUB_TP_CD = "calSubTpCd";

    /** map key : calMultCd. */
    public static final String BIND_CAL_MULT_CD = "calMultCd";

    /** map key : ordTakeMdseCd. */
    public static final String BIND_ORD_TAKE_MDSE_CD = "ordTakeMdseCd";

    /** map key : openFlg. */
    public static final String BIND_OPEN_FLG = "openFlg";

    /** map key : whCd. */
    public static final String BIND_WH_CD = "whCd";

    /** map key : shipToStCd. */
    public static final String BIND_ST_CD = "shipToStCd";

    /** map key : shpgModeCd. */
    public static final String BIND_SHPG_MODE_CD = "shpgModeCd";

    /** map key : shipToPostCd. */
    public static final String BIND_SHIP_TO_POST_CD = "shipToPostCd";

    /** map key : billToCustCdList. */
    public static final String BIND_BILL_TO_CUST_CD_LIST = "billToCustCdList";

    /** map key : sellToCustCdList. */
    public static final String BIND_SELL_TO_CUST_CD_LIST = "sellToCustCdList";

    /** map key : shpgStsCdList. */
    public static final String BIND_SHPG_STS_CD_LIST = "shpgStsCdList";

    /** map key : cusaGlblCmpyCd. */
    public static final String BIND_CUSA_GLBL_CMPY_CD = "cusaGlblCmpyCd";

    /** map key : frtChrgToCd. */
    public static final String BIND_FRT_CHRG_TO_CD = "frtChrgToCd";

    /** map key : frtChrgMethCd. */
    public static final String BIND_FRT_CHRG_METH_CD = "frtChrgMethCd";

    /** map key : shpgSvcLvlCd. */
    public static final String BIND_SHPG_SVC_LVL_CD = "shpgSvcLvlCd";

    /** map key : slsDate. */
    public static final String BIND_SLS_DATE = "slsDate";

    /** map key : ediPoNum. */
    public static final String BIND_CUST_EDI_PO_NUM = "ediPoNum";

    /** map key : custEdiPoDt. */
    public static final String BIND_CUST_EDI_PO_DT = "custEdiPoDt";

    /** map key : prpsCd. */
    public static final String BIND_EDI_TRX_PRPS_CD = "prpsCd";

    /** map key : ediPoLineNum. */
    public static final String BIND_EDI_PO_LINE_NUM = "ediPoLineNum";

    /** map key : ediPoSubLineNum. */
    public static final String BIND_EDI_PO_SUB_LINE_NUM = "ediPoSubLineNum";

    /** map key : cusaCustCd. */
    public static final String BIND_CUSA_CUST_CD = "cusaCustCd";

    /** map key : rwsRefNum. */
    public static final String BIND_RWS_REF_NUM = "rwsRefNum";

    /** map key : FROM_LOC_CD. */
    public static final String BIND_FROM_LOC_CD = "fromLocCd";

    /** map key : dsCondConstGrpId. */
    public static final String BIND_DS_COND_CONST_GRP_ID = "dsCondConstGrpId";

    /** map key : dsCondConstCd. */
    public static final String BIND_DS_COND_CONST_CD = "dsCondConstCd";

    /** map key : ediTrdPtnrCdList. */
    public static final String BIND_EDI_TRD_PTNR_CD_LIST = "ediTrdPtnrCdList";

    /** map key : ediCustTpCd. */
    public static final String BIND_EDI_CUST_TP_CD = "ediCustTpCd";

    /** map key : trxHdrNum. */
    public static final String BIND_TRX_HDR_NUM = "trxHdrNum";

    /** map key : trxLineNum. */
    public static final String BIND_TRX_LINE_NUM = "trxLineNum";

    /** map key : trxLineSubNum. */
    public static final String BIND_TRX_LINE_SUB_NUM = "trxLineSubNum";

    /** map key : trxSrcTpCd. */
    public static final String BIND_TRX_SRC_TP_CD = "trxSrcTpCd";

    /** map key : shipQty. */
    public static final String BIND_SHIP_QTY = "shipQty";

    /** map key : poReqFlg. */
    public static final String BIND_PO_REQ_FLG = "poReqFlg";

    /** map key : revRecogFlg. */
    public static final String BIND_REV_RECOG_FLG = "revRecogFlg";

    /** map key : soCloseFlg. */
    public static final String BIND_SO_CLOSE_FLG = "soCloseFlg";

    /** map key : trxCd. */
    public static final String BIND_TRX_CD = "trxCd";

    /** map key : trxRsnCd. */
    public static final String BIND_TRX_RSN_CD = "trxRsnCd";

    /** map key : invtyTrxDt. */
    public static final String BIND_INVTY_TRX_DT = "invtyTrxDt";

    /** map key : asnReqFlg. */
    public static final String BIND_ASN_REQ_FLG = "asnReqFlg";

    /** map key : soProcStsCd. */
    public static final String BIND_SO_PROC_STS_CD = "soProcStsCd";

    /** map key : soProcSts. */
    public static final String BIND_PO_PROC_STS_CD = "soProcSts";

    /** map key : revRecogMethCd_10. */
    public static final String BIND_REV_RECOG_METH_CD_10 = "revRecogMethCd_10";

    /** map key : revRecogMethCd_20. */
    public static final String BIND_REV_RECOG_METH_CD_20 = "revRecogMethCd_20";

    /** map key : revRecogMethCd_30. */
    public static final String BIND_REV_RECOG_METH_CD_30 = "revRecogMethCd_30";

    /** map key : revRecogMethCd_40. */
    public static final String BIND_REV_RECOG_METH_CD_40 = "revRecogMethCd_40";

    /** map key : shpgStsCd. */
    public static final String BIND_SHPG_STS_CD = "shpgStsCd";

    /** map key : exptInvTpCd. */
    public static final String BIND_EXPT_INV_TP_CD = "exptInvTpCd";

    /** map key : exptBolDt. */
    public static final String BIND_EXPT_BOL_DT = "exptBolDt";

    /** map key : etaDt. */
    public static final String BIND_ETA_DT = "etaDt";

    /** map key : ezUptime. */
    public static final String BIND_EZUPTIME = "ezUptime";

    /** map key : cpoOrdNum. */
    public static final String BIND_CPO_ORD_NUM = "cpoOrdNum";

    /** map key : cpoDtlLineNum. */
    public static final String BIND_CPO_DTL_LINE_NUM = "cpoDtlLineNum";

    /** map key : cpoDtlLineSubNum. */
    public static final String BIND_CPO_DTL_LINE_SUB_NUM = "cpoDtlLineSubNum";

    /** map key : rownum. */
    public static final String BIND_ROWNUM = "rownum";

    /** map key : shpgPlnNum. */
    public static final String BIND_SHPG_PLN_NUM = "shpgPlnNum";

    /** map key : cpoOrdTpList. */
    public static final String BIND_CPO_ORD_TP_LIST = "cpoOrdTpList";

    /** map key : mdseCd. */
    public static final String BIND_MDSE_CD = "mdseCd";

    /** map key : lakStsCd. */
    public static final String BIND_LAK_STS_CD = "lakStsCd";

    /** map key : revRecogMethCd. */
    public static final String BIND_REV_RECOG_METH_CD = "revRecogMethCd";

    /** map key : poOrdNum. */
    public static final String BIND_PO_ORD_NUM = "poOrdNum";

    /** map key : poOrdDtlLineNum. */
    public static final String BIND_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /** map key : setPoOrdDtlLineNum. */
    public static final String BIND_SET_PO_ORD_DTL_LINE_NUM = "setPoOrdDtlLineNum";

    /** map key : serTakeDtTmTs. */
    public static final String BIND_SER_TAKE_DT_TM_TS = "serTakeDtTmTs";

    /** map key : trdPtnrCdList. */
    public static final String BIND_P_CODE_TP_LIST = "trdPtnrCdList";

    /** map key : pCodeStatSo. */
    public static final String BIND_P_CODE_STAT_SO = "pCodeStatSo";

    /** Add QC#52677. map key : pCodeStatInv. */
    public static final String BIND_P_CODE_STAT_INV = "pCodeStatInv";

    /** map key : pCodeCustSoldTo. */
    public static final String BIND_P_CODE_CUST_SOLD_TO = "pCodeCustSoldTo";

    /** map key : pCodeCustSoldToList. */
    public static final String BIND_P_CODE_CUST_SOLD_TO_LIST = "pCodeCustSoldToList";

    /** map key : pCodeTransact. */
    public static final String BIND_P_CODE_TRANSACT = "pCodeTransact";

    /** map key : trdPtnrCdList. */
    public static final String BIND_P_QTY_SHIP = "pQtyShip";

    /** map key : pNumSo. */
    public static final String BIND_P_NUM_SO = "pNumSo";

    /** map key : pCodeWhShip. */
    public static final String BIND_P_CODE_WH_SHIP = "pCodeWhShip";

    /** map key : pCodeWh. */
    public static final String BIND_P_CODE_WH = "pCodeWh";

    /** map key : pNumCustPo. */
    public static final String BIND_P_NUM_CUST_PO = "pNumCustPo";

    /** map key : pCodeCustShipTo. */
    public static final String BIND_P_CODE_CUST_SHIP_TO = "pCodeCustShipTo";

    /** map key : pPartsNum. */
    public static final String BIND_P_PARTS_NUM = "pPartsNum";

    /** map key end ************************************************************/

    /** column key end ************************************************************/

    /** ETA_DAYS_AOT. */
    public static final String ETA_DAYS_AOT = "ETA_DAYS_AOT";

    /** P_CODE_CUST_CHRG_TO. */
    public static final String P_CODE_CUST_CHRG_TO = "P_CODE_CUST_CHRG_TO";

    /** P_CODE_CUST_SOLD_TO. */
    public static final String P_CODE_CUST_SOLD_TO = "P_CODE_CUST_SOLD_TO";

    /** P_DATE_SHIP_PRSS. */
    public static final String P_DATE_SHIP_PRSS = "P_DATE_SHIP_PRSS";

    /** P_AMT_INVO_FRT. */
    public static final String P_AMT_INVO_FRT = "P_AMT_INVO_FRT";

    /** P_QTY_SHIP. */
    public static final String P_QTY_SHIP = "P_QTY_SHIP";

    /** P_MDSE_CD_UPD_FLG. */
    public static final String P_MDSE_CD_UPD_FLG = "P_MDSE_CD_UPD_FLG";

    /** P_NUM_INVOICE. */
    public static final String P_NUM_INVOICE = "P_NUM_INVOICE";

    /** P_NUM_SO. */
    public static final String P_NUM_SO = "P_NUM_SO";

    /** P_CODE_WH_SHIP. */
    public static final String P_CODE_WH_SHIP = "P_CODE_WH_SHIP";

    /** P_NUM_TRUCKER. */
    public static final String P_NUM_TRUCKER = "P_NUM_TRUCKER";

    /** P_NUM_FEDX_CONTROL. */
    public static final String P_NUM_FEDX_CONTROL = "P_NUM_FEDX_CONTROL";

    /** P_NUM_CUST_PO. */
    public static final String P_NUM_CUST_PO = "P_NUM_CUST_PO";

 // START 2019/02/21 M.Naito [QC#30311,ADD]
    /** P_NUM_LINE. */
    public static final String P_NUM_LINE = "P_NUM_LINE";
 // END 2019/02/21 M.Naito [QC#30311,ADD]

    /** P_CODE_CUST_SHIP_TO. */
    public static final String P_CODE_CUST_SHIP_TO = "P_CODE_CUST_SHIP_TO";

    /** P_PARTS_NUM. */
    public static final String P_PARTS_NUM = "P_PARTS_NUM";

    /** SER_EVENT_TS. */
    public static final String SER_EVENT_TS = "SER_EVENT_TS";

    /** SER_TAKE_DT_TM_TS. */
    public static final String SER_TAKE_DT_TM_TS = "SER_TAKE_DT_TM_TS";

    /** CUST_ISS_PO_NUM. */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** REV_RECOG_METH_CD. */
    public static final String REV_RECOG_METH_CD = "REV_RECOG_METH_CD";

    /** EDI_NUM. */
    public static final String EDI_NUM = "EDI_NUM";

    /** EDI_PO_ORD_DTL_LINE_NUM. */
    public static final String EDI_PO_ORD_DTL_LINE_NUM = "EDI_PO_ORD_DTL_LINE_NUM";

    /** EDI_SUB_NUM. */
    public static final String EDI_SUB_NUM = "EDI_SUB_NUM";

    /** PO_ORD_DTL_LINE_NUM. */
    public static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** SHPG_STS_CD. */
    public static final String SHPG_STS_CD = "SHPG_STS_CD";

    /** BILL_TO_CUST_CD. */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** EZUPTIME. */
    public static final String EZUPTIME = "EZUPTIME";

    /** FRT_CHRG_METH_CD. */
    public static final String FRT_CHRG_METH_CD = "FRT_CHRG_METH_CD";

    /** FRT_CHRG_TO_CD. */
    public static final String FRT_CHRG_TO_CD = "FRT_CHRG_TO_CD";

    /** SELL_TO_CUST_CD. */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** SHIP_TO_CUST_CD. */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** SHPG_MODE_CD. */
    public static final String SHPG_MODE_CD = "SHPG_MODE_CD";

    /** SHPG_SVC_LVL_CD. */
    public static final String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** SHIP_DT_TM_TS. */
    public static final String SHIP_DT_TM_TS = "SHIP_DT_TM_TS";

    /** SO_NUM. */
    public static final String SO_NUM = "SO_NUM";

    /** SO_SER_ID. */
    public static final String SO_SER_ID = "SO_SER_ID";

    /** SER_NUM. */
    public static final String SER_NUM = "SER_NUM";

    /** TOT_FRT_AMT. */
    public static final String TOT_FRT_AMT = "TOT_FRT_AMT";

    /** SHIP_FROM_SO_NUM. */
    public static final String SHIP_FROM_SO_NUM = "SHIP_FROM_SO_NUM";

    /** TOT_SHIP_WT. */
    public static final String TOT_SHIP_WT = "TOT_SHIP_WT";

    /** WH_CD. */
    public static final String WH_CD = "WH_CD";

    /** ILMS_LIC_FLG. */
    public static final String ILMS_LIC_FLG = "ILMS_LIC_FLG";

    /** ILMS_LIC_ACCS_NUM. */
    public static final String ILMS_LIC_ACCS_NUM = "ILMS_LIC_ACCS_NUM";

    /** LIC_ACCS_NUM. */
    public static final String LIC_ACCS_NUM = "LIC_ACCS_NUM";

    /** ST_CD. */
    public static final String ST_CD = "ST_CD";

    /** POST_CD. */
    public static final String POST_CD = "POST_CD";

    /** BOL_NUM. */
    public static final String BOL_NUM = "BOL_NUM";

    /** FROM_STK_STS_CD. */
    public static final String FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /** MDSE_CD. */
    public static final String MDSE_CD = "MDSE_CD";

    /** PRO_NUM. */
    public static final String PRO_NUM = "PRO_NUM";

    /** ASL_MDSE_CD. */
    public static final String ASL_MDSE_CD = "ASL_MDSE_CD";

    /** SHIP_QTY. */
    public static final String SHIP_QTY = "SHIP_QTY";

    /** SO_SLP_NUM. */
    public static final String SO_SLP_NUM = "SO_SLP_NUM";

    /** TOT_FRT_AMT_DTL. */
    public static final String TOT_FRT_AMT_DTL = "TOT_FRT_AMT_DTL";

    /** TOT_SHIP_WT_DTL. */
    public static final String TOT_SHIP_WT_DTL = "TOT_SHIP_WT_DTL";

    /** VND_CD. */
    public static final String VND_CD = "VND_CD";

    /** INVTY_LOC_CD. */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** CPO_ORD_NUM. */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** CUST_ISS_PO_DT. */
    public static final String CUST_ISS_PO_DT = "CUST_ISS_PO_DT";

    /** CPO_DTL_LINE_NUM. */
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** PO_REQ_FLG. */
    public static final String PO_REQ_FLG = "PO_REQ_FLG";

    /** THIRD_PTY_INV_NUM. */
    public static final String THIRD_PTY_INV_NUM = "THIRD_PTY_INV_NUM";

    /** TRX_HDR_NUM. */
    public static final String TRX_HDR_NUM = "TRX_HDR_NUM";

    /** TRX_LINE_NUM. */
    public static final String TRX_LINE_NUM = "TRX_LINE_NUM";

    /** TRX_LINE_SUB_NUM. */
    public static final String TRX_LINE_SUB_NUM = "TRX_LINE_SUB_NUM";

    /** SHPG_PLN_NUM. */
    public static final String SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** ORD_QTY. */
    public static final String ORD_QTY = "ORD_QTY";

    /** ACTL_SHIP_DT. */
    public static final String ACTL_SHIP_DT = "ACTL_SHIP_DT";

    /** PO_ORD_NUM. */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";

    /** SET_MDSE_CD. */
    public static final String SET_MDSE_CD = "SET_MDSE_CD";

    /** SET_ITEM_SHIP_CPLT_FLG. */
    public static final String SET_ITEM_SHIP_CPLT_FLG = "SET_ITEM_SHIP_CPLT_FLG";

    /** SHIP_CPLT_CD. */
    public static final String SHIP_CPLT_CD = "SHIP_CPLT_CD";

    /** CPO_ORD_TP_CD. */
    public static final String CPO_ORD_TP_CD = "CPO_ORD_TP_CD";

    /** INVTY_CTRL_FLG. */
    public static final String INVTY_CTRL_FLG = "INVTY_CTRL_FLG";

    /** AUTO_LIC_ALLOC_FLG. */
    public static final String AUTO_LIC_ALLOC_FLG = "AUTO_LIC_ALLOC_FLG";

    /** SO_CLOSE_FLG. */
    public static final String SO_CLOSE_FLG = "SO_CLOSE_FLG";

    /** CPO_HLD_FLG. */
    public static final String CPO_HLD_FLG = "CPO_HLD_FLG";

    /** CPO_DTL_HLD_FLG. */
    public static final String CPO_DTL_HLD_FLG = "CPO_DTL_HLD_FLG";

    /** SHIP_PLN_HLD_FLG. */
    public static final String SHIP_PLN_HLD_FLG = "SHIP_PLN_HLD_FLG";

    /** CPO_DTL_LINE_SUB_NUM. */
    public static final String CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** CONF_DTL_PRO_NUM. */
    public static final String CONF_DTL_PRO_NUM = "CONF_DTL_PRO_NUM";

    /** CONF_DTL_VND_CD. */
    public static final String CONF_DTL_VND_CD = "CONF_DTL_VND_CD";

    /** CONF_DTL_BOL_NUM. */
    public static final String CONF_DTL_BOL_NUM = "CONF_DTL_BOL_NUM";

    /** PO_SER_NUM_PK. */
    public static final String PO_SER_NUM_PK = "PO_SER_NUM_PK";

    /** ROW_ID. */
    public static final String ROW_ID = "ROW_ID";

    /** CAL_TP_CD. */
    public static final String CAL_TP_CD = "CAL_TP_CD";

    /** column key end ************************************************************/

    /** DS_BIZ_PROC_LOG_PK. */
    public static final String DS_BIZ_PROC_LOG_PK = "DS_BIZ_PROC_LOG_PK";

    /** DS_BIZ_LAST_UPD_TS. */
    public static final String DS_BIZ_LAST_UPD_TS = "DS_BIZ_LAST_UPD_TS";

    /** DS_BIZ_PROC_LOG_SQ. */
    public static final String DS_BIZ_PROC_LOG_SQ = "DS_BIZ_PROC_LOG_SQ";

    /** VAR_CUSA_GLBL_CMPY_CD. */
    public static final String VAR_CUSA_GLBL_CMPY_CD = "CUSA_GLBL_CMPY_CD";

    /** VAR_DROP_SHIP_WH_CD. */
    public static final String VAR_DROP_SHIP_WH_CD = "DROP_SHIP_WH_CD";

    /** VAR_CUSA_EDI_TRD_PTNR_CD . */
    public static final String VAR_CUSA_EDI_TRD_PTNR_CD = "CUSA_EDI_TRD_PTNR_CD";

    /** VAR_CUSA_PRT_EDI_TRD_PTRN_CD . */
    public static final String VAR_CUSA_PRT_EDI_TRD_PTNR_CD = "CUSA_PRT_EDI_TRD_PTNR_CD";

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Interface Id */
    public static final String INTERFACE_ID = "Interface Id";

    /** Transaction Id */
    public static final String CONFIG_ID = "Transaction Config Id";

    /** WS Interface Id */
    public static final String WS_IF_ID = "ALBA0010";

    /** PARTS Interface Id */
    public static final String PRT_IF_ID = "USDA0200";

    /** Sales Date */
    public static final String SALES_DATE = "Sales Date";

    // I/F Header
    /** ITRL_INTFC_ID */
    public static final String H_ITRL_INTFC_ID = "H_ITRL_INTFC_ID";

    /** ITRL_TRX_SQ */
    public static final String H_ITRL_TRX_SQ = "H_ITRL_TRX_SQ";

    /** ASN_SO_NUM */
    public static final String H_ASN_SO_NUM = "H_ASN_SO_NUM";

    /** CUST_ISS_PO_NUM */
    public static final String H_CUST_ISS_PO_NUM = "H_CUST_ISS_PO_NUM";

    /** BILL_TO_CUST_CD */
    public static final String H_BILL_TO_CUST_CD = "H_BILL_TO_CUST_CD";

    /** SELL_TO_CUST_CD */
    public static final String H_SELL_TO_CUST_CD = "H_SELL_TO_CUST_CD";

    /** SHIP_TO_CUST_CD */
    public static final String H_SHIP_TO_CUST_CD = "H_SHIP_TO_CUST_CD";

    /** ASN_WH_CD */
    public static final String H_ASN_WH_CD = "H_ASN_WH_CD";

    /** ASN_SHIP_DT_TM_TS */
    public static final String H_ASN_SHIP_DT_TM_TS = "H_ASN_SHIP_DT_TM_TS";

    /** ASN_TOT_SHIP_WT */
    public static final String H_ASN_TOT_SHIP_WT = "H_ASN_TOT_SHIP_WT";

    /** ITRL_INTFC_ID */
    public static final String H_ASN_TOT_FRT_AMT = "H_ASN_TOT_FRT_AMT";

    /** SO_NUM */
    public static final String H_SO_NUM = "H_SO_NUM";

    // I/F Detail
    /** ITRL_INTFC_ID */
    public static final String D_ITRL_INTFC_ID = "D_ITRL_INTFC_ID";

    /** ITRL_TRX_SQ */
    public static final String D_ITRL_TRX_SQ = "D_ITRL_TRX_SQ";

    /** ASN_SO_NUM */
    public static final String D_ASN_SO_NUM = "D_ASN_SO_NUM";

    /** ASN_SO_SLP_NUM */
    public static final String D_ASN_SO_SLP_NUM = "D_ASN_SO_SLP_NUM";

    /** ASN_WH_CD */
    public static final String D_ASN_WH_CD = "D_ASN_WH_CD";

    /** ASN_PRO_NUM */
    public static final String D_ASN_PRO_NUM = "D_ASN_PRO_NUM";

    /** ASN_VND_CD */
    public static final String D_ASN_VND_CD = "D_ASN_VND_CD";

    /** ASN_BOL_NUM */
    public static final String D_ASN_BOL_NUM = "D_ASN_BOL_NUM";

    /** EDI_NUM */
    public static final String D_EDI_NUM = "D_EDI_NUM";

    /** EDI_SUB_NUM */
    public static final String D_EDI_SUB_NUM = "D_EDI_SUB_NUM";

    /** ASN_TTL_WT */
    public static final String D_ASN_TTL_WT = "D_ASN_TTL_WT";

    /** ASN_TOT_FRT_AMT */
    public static final String D_ASN_TOT_FRT_AMT = "D_ASN_TOT_FRT_AMT";

    /** ASN_PLN_DELY_DT */
    public static final String D_ASN_PLN_DELY_DT = "D_ASN_PLN_DELY_DT";

    /** ASN_MDSE_CD */
    public static final String D_ASN_MDSE_CD = "D_ASN_MDSE_CD";

    /** ASN_QTY */
    public static final String D_ASN_QTY = "D_ASN_QTY";

    /** ASN_STK_STS_CD */
    public static final String D_ASN_STK_STS_CD = "D_ASN_STK_STS_CD";

    /** MDSE_CD_UPD_FLG */
    public static final String D_MDSE_CD_UPD_FLG = "D_MDSE_CD_UPD_FLG";

    // I/F Serial
    /** ITRL_INTFC_ID */
    public static final String S_ITRL_INTFC_ID = "S_ITRL_INTFC_ID";

    /** ITRL_TRX_SQ */
    public static final String S_ITRL_TRX_SQ = "S_ITRL_TRX_SQ";

    /** GRP_CMPY_SHIP_SER_NUM_PK */
    public static final String S_GRP_CMPY_SHIP_SER_NUM_PK = "S_GRP_CMPY_SHIP_SER_NUM_PK";

    /** ASN_MDSE_CD */
    public static final String S_ASN_MDSE_CD = "S_ASN_MDSE_CD";

    /** ASN_SO_NUM */
    public static final String S_ASN_SO_NUM = "S_ASN_SO_NUM";

    /** ASN_WH_CD */
    public static final String S_ASN_WH_CD = "S_ASN_WH_CD";

    /** SER_NUM */
    public static final String S_SER_NUM = "S_SER_NUM";

    /** SER_TAKE_DT_TM_TS */
    public static final String S_SER_TAKE_DT_TM_TS = "S_SER_TAKE_DT_TM_TS";

    /** ASN_SO_SLP_NUM */
    public static final String S_ASN_SO_SLP_NUM = "S_ASN_SO_SLP_NUM";

    /** CUST_ISS_PO_NUM */
    public static final String ED_PO_ORD_NUM = "ED_PO_ORD_NUM";

    /** MDSE_CD */
    public static final String D_MDSE_CD = "D_MDSE_CD";

    /** GLBL_CMPY_CD */
    public static final String EH_GLBL_CMPY_CD = "EH_GLBL_CMPY_CD";

    /** ED_ASN_SO_NUM */
    public static final String EH_ASN_SO_NUM = "EH_ASN_SO_NUM";

    /** PO_ORD_NUM */
    public static final String EH_EDI_PO_ORD_NUM = "EH_EDI_PO_ORD_NUM";

    /** SHIP_FROM_SO_NUM */
    public static final String EH_SHIP_FROM_SO_NUM = "EH_SHIP_FROM_SO_NUM";

    /** ASN_SHIP_DT_TM_TS */
    public static final String EH_EDI_RCV_TS = "EH_EDI_RCV_TS";

    /** ASN_EDI_PROC_STS_CD */
    public static final String EH_ASN_EDI_PROC_STS_CD = "EH_ASN_EDI_PROC_STS_CD";

    /** EZCANCELFLAG */
    public static final String EZCANCELFLAG = "EZCANCELFLAG";

    /** TABLE_TYPE */
    public static final String TABLE_TYPE = "TABLE_TYPE";

    /** TABLE_TYPE */
    public static final String TABLE_TYPE_HDR = "1";

    /** TABLE_TYPE */
    public static final String TABLE_TYPE_DTL = "2";

    /** TABLE_TYPE */
    public static final String TABLE_TYPE_SER = "3";

    /** Left padding char */
    public static final String LF_PAD_CHAR = "0";

    /** Mail Template ID Send To Admin User */
    public static final String MAIL_TMPL_ID_TO_ADMIN = "NPAB125001M001";

    /** TimeStamp format : 17 byte */
    public static final String TS_FORMAT_YYYYMMDDHHMMSS_SSS = "yyyyMMddHHmmssSSS";

    /** HYPHEN */
    public static final String HYPHEN = "-";

    /** Mandatory ASN Serial Items */
    public static final String[] MANDATORY_SER_ITEMS = {"asnSoSlpNum" };

    // -- Error Message Code --------------------
    /** Failed to insert. [@] */
    public static final String NPAM1172E = "NPAM1172E";

    /** The field of [@] is not input. */
    public static final String NPAM1173E = "NPAM1173E";

    /** It failed to register an email. */
    public static final String NPAM1200E = "NPAM1200E";

    /** ASN SO Duplication Error occurred. ASN SO# : [@] */
    public static final String NPAM1312E = "NPAM1312E";

    /**
     * Mandatory parameter is not set. Item Name: [@], Table Name:
     * [@], Key Item: [@].
     */
    public static final String NPAM1321E = "NPAM1321E";

    /** MODE_WS */
    public static final String MODE_WS = "1";

    /** MODE_PARTS */
    public static final String MODE_PARTS = "2";

    /** STAT_SO_CREATED */
    public static final String STAT_SO_CREATED = "1";

    /** STAT_SO_SHIPPED */
    public static final String STAT_SO_SHIPPED = "2";

    /** STAT_SO_INVOICED */
    public static final String STAT_SO_INVOICED = "3";

    /** CODE_TRANSACT_10 */
    public static final String CODE_TRANSACT_10 = "10";

    /** DUMMY_ST_CD */
    public static final String DUMMY_ST_CD = "99";
}
