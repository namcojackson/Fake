/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NPA.NPAB124001constant;

/**
 * <pre>
 * Business ID : NPAB124001
 * Function Name : Receiving PO ACK for CUSA Domestic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/15/2016   CITS            T.Hakodate      Create          WDS NA
 * 05/04/2016   CSAI            K.Lee           Update          QC#7895 
 * 03/12/2018   CITS            T.Wada          Update          QC#24404
 * 2019/05/13   CITS            K.Ogino         Update          QC#31207
 * 2022/11/08   CITS            A.Cullano       Update          QC#60776
 *</pre>
 */

public class NPAB124001constant {

    /** Failed to update @ table. @ is @. */
    public static final String NPAM1003E = "NPAM1003E";

    /** "CUSA Global Company Code" does not exist in "VAR_CHAR_CONST". */
    public static final String NPAM1496E = "NPAM1496E";

    /**
     * "CUSA EDI Trading Partner Code" does not exist in
     * "VAR_CHAR_CONST".
     */
    public static final String NPAM1497E = "NPAM1497E";

    /**
     * "S21 Parts PO Creation Application ID" does not exist in
     * "VAR_CHAR_CONST".
     */
    public static final String NPAM1498E = "NPAM1498E";

    /** CST_DEBUG_MSG_LVL. */
    public static final int CST_DEBUG_MSG_LVL = 1;

    /** FETCH_SIZE. */
    public static final int FETCH_SIZE = 1000;

    /** BUSHINESS_ID. */
    public static final String BUSINESS_ID = "NPAB124001";

    /** Date Time Pattern For Processing Date */
    public static final String TIME_FORMAT_PROC_DATE = "yyyyMMddHHmmssSSS";

    // /** batch mode S21 WS WebService . */
    // public static final String MODE_S21_WS = "1";

    /** batch mode S21 WS WebService . */
    public static final String IF_ID_NPAA0031 = "NPAA0031";

    // /** batch mode S21 WS EDI IF . */
    // public static final String MODE_S21_WS_EDI = "2";

    /** batch mode S21 WS EDI IF . */
    public static final String IF_ID_NPAA0032 = "NPAA0032";

    // /** batch mode S21 Parts . */
    // public static final String MODE_S21_PARTS = "3";

    /** batch mode S21 Parts . */
    public static final String IF_ID_NPAA0041 = "NPAA0041";

    /** VAR_CUSA_EDI_TRD_PTNR_CD . */
    public static final String VAR_CUSA_EDI_TRD_PTNR_CD = "CUSA_EDI_TRD_PTNR_CD";

    /** VAR_CUSA_PRT_EDI_TRD_PTRN_CD . */
    public static final String VAR_CUSA_PRT_EDI_TRD_PTNR_CD = "CUSA_PRT_EDI_TRD_PTNR_CD";

    /** VAR_CUSA_PARTS_PO_CRAT_APL_ID. */
    public static final String VAR_CUSA_PARTS_PO_CRAT_APL_ID = "CUSA_PARTS_PO_CRAT_APL_ID";

    /** VAR_CUSA_GLBL_CMPY_CD. */
    public static final String VAR_CUSA_GLBL_CMPY_CD = "CUSA_GLBL_CMPY_CD";

    /** The corresponding [@] does not exist. */
    public static final String NMAM0039E = "NMAM0039E";

    /** map key : glblCmpyCd. */
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** map key : interfaceId. */
    public static final String BIND_PROC_PGM_ID = "procPgmId";

    /** map key : shpgStsCdList. */
    public static final String BIND_SHPG_STS_CD_LIST = "shpgStsCdList";

    /** map key : ezinAplid. */
    public static final String BIND_EZINAPLID = "ezinAplid";

    /** map key : ezinAplid. */
    public static final String BIND_EZINUSERID = "ezinUserId";

    /** map key : cpoOrdTpCd. */
    public static final String BIND_CPO_ORD_TP_CD = "cpoOrdTpCd";

    /** map key : trxSrcTpCd. */
    public static final String BIND_TRX_SRC_TP_CD = "trxSrcTpCd";

    /** map key : bizLastUpdTs. */
    public static final String BIND_EZUPTIME = "ezupTime";

    /** map key : poAckLineStsCd. */
    public static final String BIND_PO_ACK_LINE_STS_CD = "poAckLineStsCd";

    /** map key : cpoOrdNum. */
    public static final String BIND_CPO_ORD_NUM = "cpoOrdNum";

    /** map key : cpoDtlLineNum. */
    public static final String BIND_CPO_DTL_LINE_NUM = "cpoDtlLineNum";

    /** map key : cpoDtlLineSubNum. */
    public static final String BIND_CPO_DTL_LINE_SUB_NUM = "cpoDtlLineSubNum";

    /** map key : poOrdNum. */
    public static final String BIND_PO_ORD_NUM = "poOrdNum";

    /** map key : poOrdDtlLineNum. */
    public static final String BIND_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /** map key : ordTakeMdseCd. */
    public static final String BIND_ORD_TAKE_MDSE_CD = "ordTakeMdseCd";

    /** map key : cpoSrcTpCd. */
    public static final String BIND_CPO_SRC_TP_CD = "cpoSrcTpCd";

    /* QC#24404 */
    /** map key : cpoSrcTpCdList. */
    public static final String BIND_CPO_SRC_TP_CD_LIST = "cpoSrcTpCdList";

    /** map key : ediTrxPrpsCd. */
    public static final String BIND_EDI_TRX_PRPS_CD = "ediTrxPrpsCd";

    /** map key : setPoOrdDtlLineNum. */
    public static final String BIND_SET_PO_ORD_DTL_LINE_NUM = "setPoOrdDtlLineNum";

    /** map key : sdqPoTpFlg. */
    public static final String BIND_EDI_TRX_PRPS = "sdqPoTpFlg";

    /** map key : H_ediProcStsCdList. */
    public static final String BIND_H_EDI_PROC_STS_CD_LIST = "H_ediProcStsCdList";

    /** map key : H_ediProcStsCdList. */
    public static final String BIND_D_EDI_PROC_STS_CD_LIST = "D_ediProcStsCdList";

    /** map key : pCodeOrdrTypePartList. */
    public static final String BIND_P_CODE_ORDR_TYPE_PART_LIST = "pCodeOrdrTypePartList";

    /** map key : pCodeStatOeList. */
    public static final String BIND_P_CODE_STAT_OE_LIST = "pCodeStatOeList";

    /** map key : pCodeStatOe1. */
    public static final String BIND_P_CODE_STAT_OE_1 = "pCodeStatOe1";

    /** map key : pCodeStatOe4. */
    public static final String BIND_P_CODE_STAT_OE_4 = "pCodeStatOe4";

    /** map key : pCodeStatOe8. */
    public static final String BIND_P_CODE_STAT_OE_8 = "pCodeStatOe8";

    /** map key : pCodeStatOe9. */
    public static final String BIND_P_CODE_STAT_OE_9 = "pCodeStatOe9";

    /** map key : pFileId. */
    public static final String BIND_P_FILE_ID = "pFileId";

    /** map key : pCodeCustSoldToList. */
    public static final String BIND_P_CODE_CUST_SOLD_TO_LIST = "pCodeCustSoldToList";

    /** map key : pQtyShipSched. */
    public static final String BIND_P_QTY_SHIP_SCHED = "pQtyShipSched";

    /** map key : pQtyOrdrAcpt. */
    public static final String BIND_P_QTY_ORDR_ACPT = "pQtyOrdrAcpt";

    /** map key : pCodeIndHold. */
    public static final String BIND_P_CODE_IND_HOLD = "pCodeIndHold";

    /** map key : pFlagCancelBo. */
    public static final String BIND_P_FLAG_CANCEL_BO = "pFlagCancelBo";

    /** map key : H_ediProcStsCd. */
    public static final String BIND_H_EDI_PROC_STS_CD = "H_ediProcStsCd";

    /** map key : D_ediProcStsCd. */
    public static final String BIND_D_EDI_PROC_STS_CD = "D_ediProcStsCd";

    /** map key : ediProcStsCd_C. */
    public static final String BIND_EDI_PROC_STS_CD_C = "ediProcStsCd_C";

    /** map key : ediProcStsCd_E. */
    public static final String BIND_EDI_PROC_STS_CD_E = "ediProcStsCd_E";

    /** map key : ediTrdPtnrCdList. */
    public static final String BIND_EDI_TRD_PTNR_CD_LIST = "ediTrdPtnrCdList";

    /** map key : ediCustTpCd. */
    public static final String BIND_EDI_CUST_TP_CD = "ediCustTpCd";

    /** map key : ediPoOrdNum. */
    public static final String BIND_EDI_PO_ORD_NUM = "ediPoOrdNum";

    /** map key : vndShpgStsCd. */
    public static final String BIND_VND_SHPG_STS_CD = "vndShpgStsCd";

    /** map key : ediPoOrdDtlLineNum. */
    public static final String BIND_EDI_PO_ORD_DTL_LINE_NUM = "ediPoOrdDtlLineNum";

    /** map key : lakStsCd. */
    public static final String BIND_LAK_STS_CD = "lakStsCd";

    /** map key : cpoOrdTpCdList. */
    public static final String BIND_CPO_ORD_TP_CD_LIST = "cpoOrdTpCdList";

    // START 2022/11/08 A.Cullano [QC#60776, ADD]
    /** map key : mdseCd. */
    public static final String BIND_MDSE_CD = "mdseCd";

    /** map key : poyoDt. */
    public static final String BIND_POYO_DT = "poyoDt";
    // END 2022/11/08 A.Cullano [QC#60776, ADD]

    /** DS_BIZ_PROC_LOG_PK. */
    public static final String DS_BIZ_PROC_LOG_PK = "DS_BIZ_PROC_LOG_PK";

    /** DS_BIZ_LAST_UPD_TS. */
    public static final String DS_BIZ_LAST_UPD_TS = "DS_BIZ_LAST_UPD_TS";

    /** PO_REQ_FLG. */
    public static final String PO_REQ_FLG = "PO_REQ_FLG";

    /** INVTY_LOC_CD. */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** SHPG_STS_CD. */
    public static final String SHPG_STS_CD = "SHPG_STS_CD";

    /** SLS_HLD_QTY. */
    public static final String SLS_HLD_QTY = "SLS_HLD_QTY";

    /** CR_HLD_QTY. */
    public static final String CR_HLD_QTY = "CR_HLD_QTY";

    /** SP_SLS_HLD_QTY. */
    public static final String SP_SLS_HLD_QTY = "SP_SLS_HLD_QTY";

    /** SP_CR_HLD_QTY. */
    public static final String SP_CR_HLD_QTY = "SP_CR_HLD_QTY";

    /** SET_MDSE_CD. */
    public static final String SET_MDSE_CD = "SET_MDSE_CD";

    /** EDI_NUM. */
    public static final String EDI_NUM = "EDI_NUM";

    /** SP_SET_MDSE_CD. */
    public static final String SP_SET_MDSE_CD = "SP_SET_MDSE_CD";

    /** CPO_ORD_NUM. */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** CPO_DTL_LINE_NUM. */
    public static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** CUST_ISS_PO_NUM. */
    public static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** CUST_ISS_PO_NUM. */
    public static final String ORD_HDR_STS_CD = "ORD_HDR_STS_CD";

    /** MDSE_CD. */
    public static final String MDSE_CD = "MDSE_CD";

    /** D_CUSA_MDSE_CD. */
    public static final String D_CUSA_MDSE_CD = "D_CUSA_MDSE_CD";

    /** MDSE_NM. */
    public static final String MDSE_NM = "MDSE_NM";

    /** M_MDSE_NM. */
    public static final String M_MDSE_NM = "M_MDSE_NM";

    /** M_MDSE_NM. */
    public static final String SP_SHPG_PLN_DPLY_LINE_NUM = "SP_SHPG_PLN_DPLY_LINE_NUM";

    /** M_MDSE_NM. */
    public static final String SP_MDSE_CD = "SP_MDSE_CD";

    /** SP_ORD_QTY. */
    public static final String SP_ORD_QTY = "SP_ORD_QTY";

    /** SP_UOM_CD. */
    public static final String SP_UOM_CD = "SP_UOM_CD";

    /** SP_EZUPTIME. */
    public static final String SP_EZUPTIME = "SP_EZUPTIME";

    /** SP_PO_ORD_NUM. */
    public static final String SP_PO_ORD_NUM = "SP_PO_ORD_NUM";

    /** D_CUST_MDSE_CD. */
    public static final String D_CUST_MDSE_CD = "D_CUST_MDSE_CD";

    /** D_MDSE_CD. */
    public static final String D_MDSE_CD = "D_MDSE_CD";

    /** D_ENT_DEAL_NET_UNIT_PRC_AMT. */
    public static final String D_ENT_DEAL_NET_UNIT_PRC_AMT = "D_ENT_DEAL_NET_UNIT_PRC_AMT";

    /** D_CPO_DTL_LINE_NUM. */
    public static final String D_CPO_DTL_LINE_NUM = "D_CPO_DTL_LINE_NUM";

    /** D_CPO_DTL_LINE_SUB_NUM. */
    public static final String D_CPO_DTL_LINE_SUB_NUM = "D_CPO_DTL_LINE_SUB_NUM";

    /** D_ORD_LINE_STS_CD. */
    public static final String D_ORD_LINE_STS_CD = "D_ORD_LINE_STS_CD";

    /** SP_SHPG_STS_CD. */
    public static final String SP_SHPG_STS_CD = "SP_SHPG_STS_CD";

    /** SP_SHPG_PLN_NUM. */
    public static final String SP_SHPG_PLN_NUM = "SP_SHPG_PLN_NUM";

    /** H_CPO_ORD_TS. */
    public static final String H_CPO_ORD_TS = "H_CPO_ORD_TS";

    /** SP_CARR_CD. */
    public static final String SP_CARR_CD = "SP_CARR_CD";

    /** SP_CARR_NM. */
    public static final String SP_CARR_NM = "SP_CARR_NM";

    /** SP_PRO_NUM. */
    public static final String SP_PRO_NUM = "SP_PRO_NUM";

    /** SP_SO_NUM. */
    public static final String SP_SO_NUM = "SP_SO_NUM";

    /** SP_SO_NUM. */
    public static final String SP_SO_SLP_NUM = "SP_SO_SLP_NUM";

    /** D_SHIP_TO_LOC_NM. */
    public static final String D_SHIP_TO_LOC_NM = "D_SHIP_TO_LOC_NM";

    /** CS_LOC_NUM. */
    public static final String CS_LOC_NUM = "CS_LOC_NUM";

    /** D_SHIP_TO_CUST_CD. */
    public static final String D_SHIP_TO_CUST_CD = "D_SHIP_TO_CUST_CD";

    /** CS_LOC_NUM. */
    public static final String H_SELL_TO_CUST_CD = "H_SELL_TO_CUST_CD";

    /** D_CCY_CD. */
    public static final String D_CCY_CD = "D_CCY_CD";

    /** SP_CUST_UOM_CD. */
    public static final String SP_CUST_UOM_CD = "SP_CUST_UOM_CD";

    /** S_ORD_QTY. */
    public static final String S_ORD_QTY = "S_ORD_QTY";

    /** P_PARTS_NUM. */
    public static final String P_PARTS_NUM = "P_PARTS_NUM";

    /** P_PARTS_NUM_ORIG. */
    public static final String P_PARTS_NUM_ORIG = "P_PARTS_NUM_ORIG";

    /** P_EDI_PARTS_NUM. */
    public static final String P_EDI_PARTS_NUM = "P_EDI_PARTS_NUM";

    /** P_DESC_PART. */
    public static final String P_DESC_PART = "P_DESC_PART";

    /** P_CUST_PART_NUM. */
    public static final String P_CUST_PART_NUM = "P_CUST_PART_NUM";

    /** P_QTY_ACPT_ORDR. */
    public static final String P_QTY_ACPT_ORDR = "P_QTY_ACPT_ORDR";

    /** P_PRC_UNIT. */
    public static final String P_PRC_UNIT = "P_PRC_UNIT";

    /** D_TRD_PTNR_SKU_CD. */
    public static final String D_TRD_PTNR_SKU_CD = "D_TRD_PTNR_SKU_CD";

    /** D_ORD_QTY. */
    public static final String D_ORD_QTY = "D_ORD_QTY";

    /** D_UNIT_PRC_AMT. */
    public static final String D_UNIT_PRC_AMT = "D_UNIT_PRC_AMT";

    /** D_UNIT_PRC_AMT. */
    public static final String D_UOM_CD = "D_UOM_CD";

    /** D_ORIG_TRD_PTNR_SKU_CD. */
    public static final String D_ORIG_TRD_PTNR_SKU_CD = "D_ORIG_TRD_PTNR_SKU_CD";

    /** D_ORIG_ORD_QTY. */
    public static final String D_ORIG_ORD_QTY = "D_ORIG_ORD_QTY";

    /** D_ORIG_CUSA_MDSE_CD. */
    public static final String D_ORIG_CUSA_MDSE_CD = "D_ORIG_CUSA_MDSE_CD";

    /** CUST_MDSE_CD. */
    public static final String CUST_MDSE_CD = "CUST_MDSE_CD";

    /** ORD_QTY. */
    public static final String ORD_QTY = "ORD_QTY";
    
    public static final String ORIG_ORD_QTY = "ORIG_ORD_QTY";
    

    /** ENT_DEAL_NET_UNIT_PRC_AMT. */
    public static final String ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";

    /** CCY_CD. */
    public static final String CCY_CD = "CCY_CD";

    /** CUST_UOM_CD. */
    public static final String CUST_UOM_CD = "CUST_UOM_CD";

    /** SHPG_PLN_NUM. */
    public static final String SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** ASL_MDSE_CD. */
    public static final String ASL_MDSE_CD = "ASL_MDSE_CD";

    /** CPO_ORD_TS. */
    public static final String CPO_ORD_TS = "CPO_ORD_TS";

    /** PO_ORD_NUM. */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";

    /** P_NUM_ORDR. */
    public static final String P_NUM_ORDR = "P_NUM_ORDR";

    /** P_ORIG_TRD_PTNR_SKU_CD. */
    public static final String P_ORIG_TRD_PTNR_SKU_CD = "P_ORIG_TRD_PTNR_SKU_CD";

    /** P_QTY_PCSE_ORDR. */
    public static final String P_QTY_PCSE_ORDR = "P_QTY_PCSE_ORDR";

    /** P_DATE_ORDR_RCV. */
    public static final String P_DATE_ORDR_RCV = "P_DATE_ORDR_RCV";

    /** CARR_CD. */
    public static final String CARR_CD = "CARR_CD";

    /** CARR_NM. */
    public static final String CARR_NM = "CARR_NM";

    /** PRO_NUM. */
    public static final String PRO_NUM = "PRO_NUM";

    /** SO_NUM. */
    public static final String SO_NUM = "SO_NUM";

    /** SO_SLP_NUM. */
    public static final String SO_SLP_NUM = "SO_SLP_NUM";

    /** SHIP_TO_CUST_CD. */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** SHIP_TO_LOC_NM. */
    public static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /** SHIP_TO_LOC_NM. */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** SHIP_TO_LOC_NM. */
    public static final String LOC_NM = "LOC_NM";

    /** PSD_DT. */
    public static final String PSD_DT = "PSD_DT";

    /** PSD_DT. */
    public static final String PDD_DT = "PDD_DT";

    /** ETA_DT. */
    public static final String ETA_DT = "ETA_DT";

    /** SP_PSD_DT. */
    public static final String SP_PSD_DT = "SP_PSD_DT";

    /** SP_ACTL_SHIP_DT. */
    public static final String SP_ACTL_SHIP_DT = "SP_ACTL_SHIP_DT";

    /** SP_PDD_DT. */
    public static final String SP_PDD_DT = "SP_PDD_DT";

    /** ACTL_SHIP_DT. */
    public static final String ACTL_SHIP_DT = "ACTL_SHIP_DT";

    /** PO_ORD_DTL_LINE_NUM. */
    public static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** PO_ORD_DTL_LINE_NUM. */
    public static final String CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** PO_ORD_DTL_LINE_NUM. */
    public static final String ORD_LINE_STS_CD = "ORD_LINE_STS_CD";

    /** CSA_EDI_PO_HDR_V.EDI_PROC_STS_CD. */
    public static final String H_EDI_PROC_STS_CD = "H_EDI_PROC_STS_CD";

    /** CSA_EDI_PO_DTL_V.EDI_PROC_STS_CD. */
    public static final String D_EDI_PROC_STS_CD = "D_EDI_PROC_STS_CD";

    /** CSA_CPO_V.EZUPTIME. */
    public static final String EZUPTIME = "EZUPTIME";

    /** EDI_PO_DTL.EZUPTIME. */
    public static final String D_EZUPTIME = "D_EZUPTIME";

    /** EDI_PO_DTL_SDQ.EZUPTIME. */
    public static final String S_EZUPTIME = "S_EZUPTIME";

    /** CSA_EDI_PO_HDR_V.EZUPTIME. */
    public static final String H_EZUPTIME = "H_EZUPTIME";

    /** CSA_PRT_CUST_CPO_V.EZUPTIME. */
    public static final String P_EZUPTIME = "P_EZUPTIME";

    /** CSA_PRT_CPO_DTL_V.EZUPTIME. */
    public static final String CP_EZUPTIME = "CP_EZUPTIME";

    // START 2022/11/08 A.Cullano [QC#60776, ADD]
    /** CSA_PRT_POYO_V.EZUPTIME. */
    public static final String POYO_EZUPTIME = "POYO_EZUPTIME";
    // END 2022/11/08 A.Cullano [QC#60776, ADD]

    /** CSA_PRT_CPO_DTL_V.P_CODE_WH. */
    public static final String P_CODE_WH = "P_CODE_WH";

    /** P_NUM_CUST_PO. */
    public static final String P_NUM_CUST_PO = "P_NUM_CUST_PO";

    /** P_NUM_LINE. */
    public static final String P_NUM_LINE = "P_NUM_LINE";

    /** P_CODE_SHIP_VIA. */
    public static final String P_CODE_SHIP_VIA = "P_CODE_SHIP_VIA";

    /** P_CODE_TABLE_DESCRIPTION. */
    public static final String P_CODE_TABLE_DESCRIPTION = "P_CODE_TABLE_DESCRIPTION";

    /** P_NUM_TRUCKER. */
    public static final String P_NUM_TRUCKER = "P_NUM_TRUCKER";

    /** PH_NAME_CUST_01. */
    public static final String PH_NAME_CUST_01 = "PH_NAME_CUST_01";

    /** PH_NAME_CUST_01. */
    public static final String PE_NAME_CUST_01 = "PE_NAME_CUST_01";

    /** P_CODE_CUST_SHIP_TO. */
    public static final String P_CODE_CUST_SHIP_TO = "P_CODE_CUST_SHIP_TO";

    /** P_NUM_SO. */
    public static final String P_NUM_SO = "P_NUM_SO";

    /** P_CODE_CUST_SOLD_TO. */
    public static final String P_CODE_CUST_SOLD_TO = "P_CODE_CUST_SOLD_TO";

    /** H_CUST_EDI_PO_NUM. */
    public static final String H_CUST_EDI_PO_NUM = "H_CUST_EDI_PO_NUM";

    /** H_CPO_ORD_NUM. */
    public static final String H_CPO_ORD_NUM = "H_CPO_ORD_NUM";

    /** H_ORD_HDR_STS_CD. */
    public static final String H_ORD_HDR_STS_CD = "H_ORD_HDR_STS_CD";

    /** H_CUST_ISS_PO_NUM. */
    public static final String H_CUST_ISS_PO_NUM = "H_CUST_ISS_PO_NUM";

    /** HC_EZUPTIME. */
    public static final String HC_EZUPTIME = "HC_EZUPTIME";

    /** D_CUST_EDI_PO_NUM. */
    public static final String D_CUST_EDI_PO_NUM = "D_CUST_EDI_PO_NUM";

    /** CSA_EDI_PO_DTL_V.EZUPTIME. */
    public static final String D_EDI_PO_LINE_NUM = "D_EDI_PO_LINE_NUM";

    /** D_EDI_NUM. */
    public static final String D_EDI_NUM = "D_EDI_NUM";

    /** SHPG_PLN_DPLY_LINE_NUM. */
    public static final String SHPG_PLN_DPLY_LINE_NUM = "SHPG_PLN_DPLY_LINE_NUM";

    /** PO_ACK_HDR_WRK_SQ. */
    public static final String PO_ACK_HDR_WRK_SQ = "PO_ACK_HDR_WRK_SQ";

    /** PO_ACK_HDR_WRK_SQ. */
    public static final String PO_ACK_DTL_WRK_SQ = "PO_ACK_DTL_WRK_SQ";

    /** PO_ACK_HDR_WRK_SQ. */
    public static final String DS_BIZ_PROC_LOG_SQ = "DS_BIZ_PROC_LOG_SQ";

    /** PO_ACK_HDR_WRK_SQ. */
    public static final String PO_ACK_HDR_SQ = "PO_ACK_HDR_SQ";

    /** PO_ACK_LINE_STS_CD. */
    public static final String PO_ACK_LINE_STS_CD = "PO_ACK_LINE_STS_CD";

    /** PO_ACK_LINE_STS_CD. */
    public static final String VND_PO_ACK_STS_CD = "VND_PO_ACK_STS_CD";

    /** VND_CPO_ORD_NUM. */
    public static final String VND_CPO_ORD_NUM = "VND_CPO_ORD_NUM";

    /** VND_CPO_DTL_LINE_NUM. */
    public static final String VND_CPO_DTL_LINE_NUM = "VND_CPO_DTL_LINE_NUM";

    /** VND_CPO_DTL_LINE_SUB_NUM. */
    public static final String VND_CPO_DTL_LINE_SUB_NUM = "VND_CPO_DTL_LINE_SUB_NUM";

    /** P_FLAG_CANCEL_BO. */
    public static final String P_FLAG_CANCEL_BO = "P_FLAG_CANCEL_BO";

    /** P_FLAG_CANCEL_BO. */
    public static final String P_QTY_ORDR_ACPT = "P_QTY_ORDR_ACPT";

    /** P_CODE_STAT_OE. */
    public static final String P_CODE_STAT_OE = "P_CODE_STAT_OE";

    /** P_CODE_STAT_SO. */
    public static final String P_CODE_STAT_SO = "P_CODE_STAT_SO";

    /** P_DATE_SHIP_PRSS. */
    public static final String P_DATE_SHIP_PRSS = "P_DATE_SHIP_PRSS";

    /** P_DATE_SO_ISSUE. */
    public static final String P_DATE_SO_ISSUE = "P_DATE_SO_ISSUE";

    /** ETD_DRV_PTRN_CD. */
    public static final String ETD_DRV_PTRN_CD = "ETD_DRV_PTRN_CD";

    /** ETA_DRV_PTRN_CD. */
    public static final String ETA_DRV_PTRN_CD = "ETA_DRV_PTRN_CD";

    // START 2017/08/31 QC#20737 Add.
    /** INVTY_CTRL_FLG. */
    public static final String INVTY_CTRL_FLG = "INVTY_CTRL_FLG";

    /** AUTO_LIC_ALLOC_FLG. */
    public static final String AUTO_LIC_ALLOC_FLG = "AUTO_LIC_ALLOC_FLG";

    /** MDSE_TP_CD. */
    public static final String MDSE_TP_CD = "MDSE_TP_CD";

    // END 2017/08/31 QC#20737 Add.

    /** CPO_DTL_LINE_SUB_NUM. */
    public static final String CPO_DTL_LINE_SUB_NUM_000 = "000";

    /** PO_ORD_DTL_SUB_LINE_NUM_001. */
    public static final String PO_ORD_DTL_SUB_LINE_NUM_001 = "001";

    /** SHPG_PLN_DPLY_LINE_NUM_001. */
    public static final String SHPG_PLN_DPLY_LINE_NUM_001 = "001";

    /** USD. */
    public static final String CCY_CD_USD = "USD";

    /** USD. */
    public static final String UOM_CD_EA = "EA";

    /** EDI_TRX_PRPS_CD_O. */
    public static final String EDI_TRX_PRPS_CD_O = "O";

    /** ZERO. */
    public static final String ZERO = "0";

    /** ONE. */
    public static final String ONE = "1";

    /** TWO. */
    public static final String TWO = "2";

    /** P_CODE_STAT_OE_1. */
    public static final String P_CODE_STAT_OE_1 = "1";

    /** P_CODE_STAT_OE_1. */
    public static final String P_CODE_STAT_OE_2 = "2";

    /** P_CODE_STAT_OE_1. */
    public static final String P_CODE_STAT_OE_3 = "3";

    /** P_CODE_STAT_OE_1. */
    public static final String P_CODE_STAT_OE_4 = "4";

    /** P_CODE_STAT_OE_1. */
    public static final String P_CODE_STAT_OE_5 = "5";

    /** P_CODE_STAT_OE_1. */
    public static final String P_CODE_STAT_OE_6 = "6";

    /** P_CODE_STAT_OE_1. */
    public static final String P_CODE_STAT_OE_7 = "7";

    /** P_CODE_STAT_OE_1. */
    public static final String P_CODE_STAT_OE_8 = "8";

    /** P_CODE_STAT_OE_1. */
    public static final String P_CODE_STAT_OE_9 = "9";

    /** P_CODE_STAT_OE_1. */
    public static final String P_CODE_STAT_SO_1 = "1";

    /** P_CODE_STAT_OE_1. */
    public static final String P_CODE_STAT_SO_2 = "2";

    /** P_CODE_STAT_OE_1. */
    public static final String P_CODE_STAT_SO_3 = "3";

    /**
     * Date Format DATE
     */
    public static final String TIME_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * Date Format TIME_STAMP
     */
    public static final String TIME_STAMP = "yyyyMMdd";

    /** PO_ACK_CREATE_PTN_DIRECT_CPO. */
    public static final String PO_ACK_CREATE_PTN_DIRECT_CPO = "1";

    /** PO_ACK_CREATE_PTN_EDI_CPO. */
    public static final String PO_ACK_CREATE_PTN_EDI_CPO = "2";

    /** PO_ACK_CREATE_PTN_EDI_CANCEL. */
    public static final String PO_ACK_CREATE_PTN_EDI_CANCEL = "3";

    /** PO_ACK_CREATE_PTN_EDI_ERROR. */
    public static final String PO_ACK_CREATE_PTN_EDI_ERROR = "4";

    /** PO_ACK_CREATE_PTN_PARTS. */
    public static final String PO_ACK_CREATE_PTN_PARTS = "5";

    /** internal Value Items */
    public static final String[] INTERNAL_VALIAVLE_ITEMS = {"ackHdrPtn", "ackDtlPtn", "poAckNum", "vndInvtyLocCd", "poAckLineStsCd", "vndPoAckStsCd", "ETA", "ETD", "poAckLineStsTxt", "ediNum", "ediLineNum", "ordDtlLastUpdTs",
            "vndCpoDtlLineSubNum" };

    /** internal VAR_CHAR_CONST Items */
    public static final String[] INTERNAL_VAR_CHAR_CONST = {

    "cusaCpoHdlgApiIntfcId", "cusaEdiTrxPrpsOrig", "cusaCpoSrcTpGrpCmpy", "cusaEdiProcStsCanc", "cusaEdiProcStsErr", "cusaEdiProcStsItrlErr", "cusaEdiProcStsProc", "cusaPrtOrdTpPrtRtrn", "cusaPrtOrdTpRprPrtRtrn", "cusaPrtOrdTpRrFromDlr",
            "cusaPrtOeStsNorm", "cusaPrtOeStsCrHold", "cusaPrtOeStsBackOrd", "cusaPrtOeStsAuthHold", "cusaPrtOeStsRushOrdHold", "cusaPrtOeStsOdcHold", "cusaPrtOeStsVoid", "cusaPrtSoStsPrnt", "cusaPrtSoStsShip", "cusaPrtSoStsInv",
            "cusaPrtAckCancWrkFileId", "dropShipWhCd" };

    /** ackHdrPtn. */
    public static final String VAL_ACK_HDR_PTN = "ackHdrPtn";

    /** ackDtlPtn. */
    public static final String VAL_ACK_DTL_PTN = "ackDtlPtn";

    /** poAckNum. */
    public static final String VAL_PO_ACK_NUM = "poAckNum";

    /** vndInvtyLocCd. */
    public static final String VAL_VND_INVTY_LOC_CD = "vndInvtyLocCd";

    /** poAckLineStsCd. */
    public static final String VAL_PO_ACK_LINE_STS_CD = "poAckLineStsCd";

    /** vndPoAckStsCd. */
    public static final String VAL_VND_PO_ACK_STS_CD = "vndPoAckStsCd";

    /** vndCpoDtlLineSubNum. */
    public static final String VAL_VND_CPO_DTL_LINE_SUB_NUM = "vndCpoDtlLineSubNum";

    /** ETD. */
    public static final String VAL_ETD = "ETD";

    /** ETA. */
    public static final String VAL_ETA = "ETA";

    /** poAckLineStsTxt. */
    public static final String VAL_PO_ACK_LINE_STS_TXT = "poAckLineStsTxt";

    /** ediNum. */
    public static final String VAL_EDI_NUM = "ediNum";

    /** ediLineNum. */
    public static final String VAL_EDI_LINE_NUM = "ediLineNum";

    /** ordDtlLastUpdTs. */
    public static final String VAL_ORD_DTL_LAST_UPD_TS = "ordDtlLastUpdTs";

    /** VAR_CUSA_CPO_HDLG_API_INTFC_ID. */
    public static final String VAR_CUSA_CPO_HDLG_API_INTFC_ID = "CUSA_CPO_HDLG_API_INTFC_ID";

    /** VAR_CUSA_EDI_TRX_PRPS_ORIG. */
    public static final String VAR_CUSA_EDI_TRX_PRPS_ORIG = "CUSA_EDI_TRX_PRPS_ORIG";

    /** VAR_CUSA_CPO_SRC_TP_GRP_CMPY. */
    public static final String VAR_CUSA_CPO_SRC_TP_GRP_CMPY = "CUSA_CPO_SRC_TP_GRP_CMPY";

    /* QC#24404 */
    /** VAR_CUSA_CPO_SRC_TP_GRP_CMPY. */
    public static final String VAR_CUSA_CPO_SRC_TP_EDI = "CUSA_CPO_SRC_TP_EDI";

    /** VAR_CUSA_EDI_PROC_STS_CANC. */
    public static final String VAR_CUSA_EDI_PROC_STS_CANC = "CUSA_EDI_PROC_STS_CANC";

    /** VAR_CUSA_EDI_PROC_STS_ERR. */
    public static final String VAR_CUSA_EDI_PROC_STS_ERR = "CUSA_EDI_PROC_STS_ERR";

    /** VAR_CUSA_EDI_PROC_STS_ITRL_ERR. */
    public static final String VAR_CUSA_EDI_PROC_STS_ITRL_ERR = "CUSA_EDI_PROC_STS_ITRL_ERR";

    /** VAR_CUSA_EDI_PROC_STS_PROC. */
    public static final String VAR_CUSA_EDI_PROC_STS_PROC = "CUSA_EDI_PROC_STS_PROC";

    /** VAR_CUSA_PRT_ORD_TP_PRT_RTRN. */
    public static final String VAR_CUSA_PRT_ORD_TP_PRT_RTRN = "CUSA_PRT_ORD_TP_PRT_RTRN";

    /** VAR_CUSA_PRT_ORD_TP_RPR_PRT_RTRN. */
    public static final String VAR_CUSA_PRT_ORD_TP_RPR_PRT_RTRN = "CUSA_PRT_ORD_TP_RPR_PRT_RTRN";

    /** VAR_CUSA_PRT_ORD_TP_RR_FROM_DLR. */
    public static final String VAR_CUSA_PRT_ORD_TP_RR_FROM_DLR = "CUSA_PRT_ORD_TP_RR_FROM_DLR";

    /** VAR_CUSA_PRT_OE_STS_NORM. */
    public static final String VAR_CUSA_PRT_OE_STS_NORM = "CUSA_PRT_OE_STS_NORM";

    /** VAR_CUSA_PRT_OE_STS_CR_HOLD. */
    public static final String VAR_CUSA_PRT_OE_STS_CR_HOLD = "CUSA_PRT_OE_STS_CR_HOLD";

    /** VAR_CUSA_PRT_OE_STS_BACK_ORD. */
    public static final String VAR_CUSA_PRT_OE_STS_BACK_ORD = "CUSA_PRT_OE_STS_BACK_ORD";

    /** VAR_CUSA_PRT_OE_STS_AUTH_HOLD. */
    public static final String VAR_CUSA_PRT_OE_STS_AUTH_HOLD = "CUSA_PRT_OE_STS_AUTH_HOLD";

    /** VAR_CUSA_PRT_OE_STS_RUSH_ORD_HOLD. */
    public static final String VAR_CUSA_PRT_OE_STS_RUSH_ORD_HOLD = "CUSA_PRT_OE_STS_RUSH_ORD_HOLD";

    /** VAR_CUSA_PRT_OE_STS_ODC_HOLD. */
    public static final String VAR_CUSA_PRT_OE_STS_ODC_HOLD = "CUSA_PRT_OE_STS_ODC_HOLD";

    /** VAR_CUSA_PRT_OE_STS_VOID. */
    public static final String VAR_CUSA_PRT_OE_STS_VOID = "CUSA_PRT_OE_STS_VOID";

    /** VAR_CUSA_PRT_SO_STS_PRNT. */
    public static final String VAR_CUSA_PRT_SO_STS_PRNT = "CUSA_PRT_SO_STS_PRNT";

    /** VAR_CUSA_PRT_SO_STS_SHIP. */
    public static final String VAR_CUSA_PRT_SO_STS_SHIP = "CUSA_PRT_SO_STS_SHIP";

    /** VAR_CUSA_PRT_SO_STS_INV. */
    public static final String VAR_CUSA_PRT_SO_STS_INV = "CUSA_PRT_SO_STS_INV";

    /** VAR_CUSA_PRT_ACK_CANC_WRK_FILE_ID. */
    public static final String VAR_CUSA_PRT_ACK_CANC_WRK_FILE_ID = "CUSA_PRT_ACK_CANC_WRK_FILE_ID";

    /** VAR_DROP_SHIP_WH_CD. */
    public static final String VAR_DROP_SHIP_WH_CD = "DROP_SHIP_WH_CD";

    /** VAL_CUSA_CPO_HDLG_API_INTFC_ID. */
    public static final String VAL_CUSA_CPO_HDLG_API_INTFC_ID = "cusaCpoHdlgApiIntfcId";

    /** VAL_CUSA_EDI_TRX_PRPS_ORIG. */
    public static final String VAL_CUSA_EDI_TRX_PRPS_ORIG = "cusaEdiTrxPrpsOrig";

    /** VAL_CUSA_CPO_SRC_TP_GRP_CMPY. */
    public static final String VAL_CUSA_CPO_SRC_TP_GRP_CMPY = "cusaCpoSrcTpGrpCmpy";

    /* QC#24404 */
    /** VAL_CUSA_CPO_SRC_TP_EDI. */
    public static final String VAL_CUSA_CPO_SRC_TP_EDI = "cusaCpoSrcTpEdi";

    /** VAL_CUSA_EDI_PROC_STS_CANC. */
    public static final String VAL_CUSA_EDI_PROC_STS_CANC = "cusaEdiProcStsCanc";

    /** VAL_CUSA_EDI_PROC_STS_ERR. */
    public static final String VAL_CUSA_EDI_PROC_STS_ERR = "cusaEdiProcStsErr";

    /** VAL_CUSA_EDI_PROC_STS_ITRL_ERR. */
    public static final String VAL_CUSA_EDI_PROC_STS_ITRL_ERR = "cusaEdiProcStsItrlErr";

    /** VAL_CUSA_EDI_PROC_STS_PROC. */
    public static final String VAL_CUSA_EDI_PROC_STS_PROC = "cusaEdiProcStsProc";

    /** VAL_CUSA_PRT_ORD_TP_PRT_RTRN. */
    public static final String VAL_CUSA_PRT_ORD_TP_PRT_RTRN = "cusaPrtOrdTpPrtRtrn";

    /** VAL_CUSA_PRT_ORD_TP_RPR_PRT_RTRN. */
    public static final String VAL_CUSA_PRT_ORD_TP_RPR_PRT_RTRN = "cusaPrtOrdTpRprPrtRtrn";

    /** VAL_CUSA_PRT_ORD_TP_RR_FROM_DLR. */
    public static final String VAL_CUSA_PRT_ORD_TP_RR_FROM_DLR = "cusaPrtOrdTpRrFromDlr";

    /** VAL_CUSA_PRT_OE_STS_NORM. */
    public static final String VAL_CUSA_PRT_OE_STS_NORM = "cusaPrtOeStsNorm";

    /** VAL_CUSA_PRT_OE_STS_CR_HOLD. */
    public static final String VAL_CUSA_PRT_OE_STS_CR_HOLD = "cusaPrtOeStsCrHold";

    /** VAL_CUSA_PRT_OE_STS_BACK_ORD. */
    public static final String VAL_CUSA_PRT_OE_STS_BACK_ORD = "cusaPrtOeStsBackOrd";

    /** VAL_CUSA_PRT_OE_STS_AUTH_HOLD. */
    public static final String VAL_CUSA_PRT_OE_STS_AUTH_HOLD = "cusaPrtOeStsAuthHold";

    /** VAL_CUSA_PRT_OE_STS_RUSH_ORD_HOLD. */
    public static final String VAL_CUSA_PRT_OE_STS_RUSH_ORD_HOLD = "cusaPrtOeStsRushOrdHold";

    /** VAL_CUSA_PRT_OE_STS_ODC_HOLD. */
    public static final String VAL_CUSA_PRT_OE_STS_ODC_HOLD = "cusaPrtOeStsOdcHold";

    /** VAL_CUSA_PRT_OE_STS_VOID. */
    public static final String VAL_CUSA_PRT_OE_STS_VOID = "cusaPrtOeStsVoid";

    /** VAL_CUSA_PRT_SO_STS_PRNT. */
    public static final String VAL_CUSA_PRT_SO_STS_PRNT = "cusaPrtSoStsPrnt";

    /** VAL_CUSA_PRT_SO_STS_SHIP. */
    public static final String VAL_CUSA_PRT_SO_STS_SHIP = "cusaPrtSoStsShip";

    /** VAL_CUSA_PRT_SO_STS_INV. */
    public static final String VAL_CUSA_PRT_SO_STS_INV = "cusaPrtSoStsInv";

    /** VAL_CUSA_PRT_ACK_CANC_WRK_FILE_ID. */
    public static final String VAL_CUSA_PRT_ACK_CANC_WRK_FILE_ID = "cusaPrtAckCancWrkFileId";

    /** VAL_CUSA_PRT_ACK_CANC_WRK_FILE_ID. */
    public static final String VAL_DROP_SHIP_WH_CD = "dropShipWhCd";

    /** Add QC#31207. P_DATE_STOCK_IN_EST */
    public static final String P_DATE_STOCK_IN_EST = "P_DATE_STOCK_IN_EST";

}
