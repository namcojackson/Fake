/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.api.NPZ.NPZC132001;

/**
 * <pre>
 * Send PO API for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/03/2015   CITS            T.Hakodate      Create          N/A
 * 12/22/2016   CITS            K.Ogino         UPDATE          QC#14749
 * 02/02/2018   CITS            K.Ogino         Update          QC#23814
 * 03/09/2018   CITS            K.Ogino         Update          QC#24732
 * 05/28/2018   CITS            K.Kameoka       UPDATE          QC#26074
 * 06/26/2018   CITS            T.Hakodate      UPDATE          QC#25392
 * 2021/04/30   CITS            K.Ogino         Update          QC#58769
 * 2023/02/17   Hitachi         TZ.Win          Update          QC#60966
 *</pre>
 */

public interface NPZC132001Constant {

    /** program id */
    static final String PROGRAM_ID = "NPZC132001";

    /** message id */
    // "Ship To Line Address"
    static final String MSG_ID_NPAM1379W = "NPAM1379W";

    /** Internal Interface. */
    static final String MSG_ID_NPAM1380E = "NPAM1380E";

    /** Send ID. */
    static final String MSG_ID_NPAM1381E = "NPAM1381E";

    /** Internal Transaction Sequence. */
    static final String MSG_ID_NPAM1382E = "NPAM1382E";

    /** Receive ID. */
    static final String MSG_ID_NPAM1383E = "NPAM1382E";

    /** PO Order Number. */
    static final String MSG_ID_NPAM1384E = "NPAM1384E";

    /** Customer EDI PO Type. */
    static final String MSG_ID_NPAM1385E = "NPAM1385E";

    /** Ship To Customer Location Name. */
    static final String MSG_ID_NPAM1386E = "NPAM1386E";

    /** Ship To First Line Address. */
    static final String MSG_ID_NPAM1387E = "NPAM1387E";

    /** Ship To Customer City Address. */
    static final String MSG_ID_NPAM1388E = "NPAM1388E";

    /** Ship To Customer State Code. */
    static final String MSG_ID_NPAM1389E = "NPAM1389E";

    /** Ship To Customer Post Code. */
    static final String MSG_ID_NPAM1390E = "NPAM1390E";

    /** MSG_ID_NPAM1391E. */
    static final String MSG_ID_NPAM1391E = "NPAM1391E";

    /** Failed to get Interface ID.. */
    static final String MSG_ID_NPAM1168E = "NPAM1168E";

    /** Ship To Customer CD is required.. */
    static final String MSG_ID_NPZM0287E = "NPZM0287E";

    /** "Unit Price" is required.. */
    static final String MSG_ID_NPZM0288E = "NPZM0288E";

    /** Global Company Code. */
    static final String MSG_ID_NSXM0001E = "NSXM0001E";

    /** MSG_ID_NPAM1482E. */
    static final String MSG_ID_NPAM1482E = "NPAM1482E";

    /** An error occurred in NPAA0010_01 registration. */
    static final String MSG_ID_NPZM0283E = "NPZM0283E";

    /** An error occurred in NPAA0010_02 registration. */
    static final String MSG_ID_NPZM0284E = "NPZM0284E";

    /** An error occurred in NPAA0020_01 registration. */
    static final String MSG_ID_NPZM0285E = "NPZM0285E";

    /** An error occurred in NPAA0020_02 registration. */
    static final String MSG_ID_NPZM0286E = "NPZM0286E";

    /** VAR_CUSA_GLBL_CMPY_CD. */
    static final String VAR_CUSA_GLBL_CMPY_CD = "CUSA_GLBL_CMPY_CD";

    /** error text */
    /** Global Company Code. */
    static final String ERR_GLBL_CMPY_CD = "Global Company Code";

    /** Internal Interface ID. */
    static final String ERR_ITRL_INTFC_ID = "Internal Interface ID";

    /** Internal Transaction Sequence. */
    static final String ERR_ITRL_TRX_SQ = "Internal Transaction Sequence";

    /** Send ID. */
    static final String ERR_SEND_ID = "Send ID";

    /** Receive ID. */
    static final String ERR_RCV_ID = "Receive ID";

    /** PO Order Number. */
    static final String ERR_PO_ORD_NUM = "PO Order Number";

    /** Ship To Line Address Information. */
    static final String ERR_SHIP_TO_LIN_ADDR = "Ship To Line Address Information";

    /** Interface ID Pattern */
    /** ITRL_INTFC_ID_NPAA0010. */
    static final String ITRL_INTFC_ID_NPAA0010 = "NPAA0010";

    /** ITRL_INTFC_ID_NPAA0020. */
    static final String ITRL_INTFC_ID_NPAA0020 = "NPAA0020";

    /** MSG_TYPE_ERROR. */
    static final String MSG_TYPE_ERROR = "E";

    /** SQL Bind Name for SSM */
    /** BIND_GLBL_CMPY_CD. */
    static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    public static final String CARR_SVC_LVL_CD = "CARR_SVC_LVL_CD";

    /** BIND_PO_ORD_NUM. */
    static final String BIND_PO_ORD_NUM = "poOrdNum";

    /** BIND_PO_STS_CD. */
    static final String BIND_PO_STS_CD = "poStsCd";

    /** BIND_PO_MSG_TP_CD. */
    static final String BIND_PO_MSG_TP_CD = "poMsgTpCd";

    /** BIND_PO_MDSE_CMPSN_TP_CD. */
    static final String BIND_PO_MDSE_CMPSN_TP_CD = "poMdseCmpsnCd";

    /** invtyLocCd. */
    static final String BIND_INVTY_LOC_CD = "invtyLocCd";

    /** ediPoTpCd. */
    static final String BIND_EDI_PO_TP_CD = "ediPoTpCd";

    /** vndCd. */
    static final String BIND_VND_CD = "vndCd";

    /** vndSysTpCd. */
    static final String BIND_VND_SYS_TP_CD = "vndSysTpCd";

    /** dsPoTpCd. */
    static final String BIND_DS_PO_TP_CD = "dsPoTpCd";

    /** shpgSvcLclCd. */
    static final String BIND_SHPG_SVC_LCL_CD = "shpgSvcLclCd";

    /** shpgSvcLvlCd. */
    static final String BIND_SHPG_SVC_LVL_CD = "shpgSvcLvlCd";

    /** carrCd. */
    static final String BIND_CARR_CD = "carrCd";

    /** frtCondCd. */
    static final String BIND_FRT_COND_CD = "frtCondCd";

    /** trdPtnrLocCd. */
    static final String BIND_TRD_PTNR_LOC_CD = "trdPtnrLocCd";

    /** poMsgSegId. */
    static final String BIND_PO_MSG_SEG_ID = "poMsgSegId";

    /** vndXrefTpCd. */
    static final String BIND_VND_XREF_TP_CD = "vndXrefTpCd";

    /** eftFromDt. */
    static final String BIND_EFF_FROM_DT = "eftFromDt";

    /** eftThruDt. */
    static final String BIND_EFF_THRU_DT = "eftThruDt";

    /** slsDate. */
    static final String BIND_SLS_DT = "slsDate";

    /** prchGrpCd. */
    static final String BIND_PRCH_GRP_CD = "prchGrpCd";

    /** BIND_RTL_WH_CD. */
    static final String BIND_RTL_WH_CD = "rtlWhCd";

    /** BIND_EDI_TRD_PTNR_CD. */
    static final String BIND_EDI_TRD_PTNR_CD = "ediTrdPtnrCd";

    /** BIND_CRS_REF_ACTV_FLG. */
    static final String BIND_CRS_REF_ACTV_FLG = "crsRefActvFlg";

    /** GLBL_CMPY_CD. */
    static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** INVTY_LOC_CD. */
    static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** EDI_PO_TP_CD. */
    static final String EDI_PO_TP_CD = "EDI_PO_TP_CD";

    /** VND_CD. */
    static final String VND_CD = "VND_CD";

    /** VND_SYS_TP_CD. */
    static final String VND_SYS_TP_CD = "VND_SYS_TP_CD";

    /** DS_PO_TP_CD. */
    static final String DS_PO_TP_CD = "DS_PO_TP_CD";

    /** SHPG_SVC_LVL_CD. */
    static final String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** SHPG_SVC_LCL_CD. */
    static final String SHPG_SVC_LCL_CD = "SHPG_SVC_LCL_CD";

    /** TRD_PTNR_SHPG_COND_CD. */
    static final String TRD_PTNR_SHPG_COND_CD = "TRD_PTNR_SHPG_COND_CD";

    /** VAR_DROP_SHIP_WH_CD. */
    static final String VAR_DROP_SHIP_WH_CD = "DROP_SHIP_WH_CD";

    /** VAR_CUSA_EDI_TRD_PTNR_CD. */
    static final String VAR_CUSA_EDI_TRD_PTNR_CD = "CUSA_EDI_TRD_PTNR_CD";

    /** VND_DROP_SHIP_FLG. */
    static final String VND_DROP_SHIP_FLG = "VND_DROP_SHIP_FLG";

    /** DROP_SHIP_WH_CD_DS. */
    static final String DROP_SHIP_WH_CD_DS = "DS";

    /** CARR_CD. */
    static final String CARR_CD = "CARR_CD";

    /** FRT_COND_CD. */
    static final String FRT_COND_CD = "FRT_COND_CD";

    /** STAR. */
    static final String STAR = "*";

    /** PRCH_GRP_CD. */
    static final String PRCH_GRP_CD = "PRCH_GRP_CD";

    /** SHIP_VIA_CD. */
    static final String SHIP_VIA_CD = "SHIP_VIA_CD";

    /** EDI_FRT_CHRG_METH_CD. */
    static final String EDI_FRT_CHRG_METH_CD = "EDI_FRT_CHRG_METH_CD";

    /** CUST_MDSE_CD. */
    static final String CUST_MDSE_CD = "CUST_MDSE_CD";

    /** PO_APVL_DT. */
    static final String PO_APVL_DT = "PO_APVL_DT";

    /** SHIP_TO_CUST_CD. */
    static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** DS_PO_TP_NM. */
    static final String DS_PO_TP_NM = "DS_PO_TP_NM";

    /** DS_PO_TP_NM. */
    static final String ASL_MDSE_CD = "ASL_MDSE_CD";

    /** TECH_NM. */
    static final String TECH_NM = "TECH_NM";

    /** PO_MSG_TXT. */
    static final String PO_MSG_TXT = "PO_MSG_TXT";

    /** CPO_ORD_NUM. */
    static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** CUST_ISS_PO_NUM. */
    static final String CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** CARR_ACCT_NUM. */
    static final String CARR_ACCT_NUM = "CARR_ACCT_NUM";

    /** RTRN_SHIP_TO_RTL_WH_CD. */
    static final String RTRN_SHIP_TO_RTL_WH_CD = "RTRN_SHIP_TO_RTL_WH_CD";

    /** VND_PMT_TERM_DESC_TXT. */
    static final String VND_PMT_TERM_DESC_TXT = "VND_PMT_TERM_DESC_TXT";

    /** MDSE_CD. */
    static final String MDSE_CD = "MDSE_CD";

    /** PO_QLFY_CD. */
    static final String PO_QLFY_CD = "PO_QLFY_CD";

    /** SHIP_TO_ACCT_NM. */
    static final String SHIP_TO_ACCT_NM = "SHIP_TO_ACCT_NM";

    /** SHIP_TO_LOC_NM. */
    static final String SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    // QC#25392 ADD START
    /** SHIP_TO_CTRY_CD. */
    static final String SHIP_TO_CTRY_CD = "SHIP_TO_CTRY_CD";

    // QC#25392 ADD END

    /** SHIP_TO_ADDL_LOC_NM. */
    static final String SHIP_TO_ADDL_LOC_NM = "SHIP_TO_ADDL_LOC_NM";

    /** PO_ORD_DTL_LINE_NUM. */
    static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** SHIP_TO_FIRST_LINE_ADDR. */
    static final String SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";

    /** SHIP_TO_SCD_LINE_ADDR. */
    static final String SHIP_TO_SCD_LINE_ADDR = "SHIP_TO_SCD_LINE_ADDR";

    /** SHIP_TO_THIRD_LINE_ADDR. */
    static final String SHIP_TO_THIRD_LINE_ADDR = "SHIP_TO_THIRD_LINE_ADDR";

    /** SHIP_TO_FRTH_LINE_ADDR. */
    static final String SHIP_TO_FRTH_LINE_ADDR = "SHIP_TO_FRTH_LINE_ADDR";

    /** CTAC_PSN_NM. */
    static final String CTAC_PSN_NM = "CTAC_PSN_NM";

    /** RQST_DELY_DT. */
    static final String RQST_DELY_DT = "RQST_DELY_DT";

    /** ETA_DT. */
    static final String ETA_DT = "ETA_DT";

    /** PO_QTY. */
    static final String PO_QTY = "PO_QTY";

    /** ENT_DEAL_NET_UNIT_PRC_AMT. */
    static final String ENT_DEAL_NET_UNIT_PRC_AMT = "ENT_DEAL_NET_UNIT_PRC_AMT";

    /** VND_INVTY_LOC_CD. */
    static final String VND_INVTY_LOC_CD = "VND_INVTY_LOC_CD";

    /** DEST_RTL_WH_CD. */
    static final String DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /** SHIP_TO_CTY_ADDR. */
    static final String SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";

    /** SHIP_TO_ST_CD. */
    static final String SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /** SHIP_TO_POST_CD. */
    static final String SHIP_TO_POST_CD = "SHIP_TO_POST_CD";

    /** SHIP_TO_CTAC_PSN_NM. */
    static final String SHIP_TO_CTAC_PSN_NM = "SHIP_TO_CTAC_PSN_NM";

    /** CUST_EDI_PO_DT. */
    static final String CUST_EDI_PO_DT = "CUST_EDI_PO_DT";

    /** UNIT_PRC_AMT. */
    static final String UNIT_PRC_AMT = "UNIT_PRC_AMT";

    /** TRD_PTNR_SHIP_TO_CUST_CD. */
    static final String TRD_PTNR_SHIP_TO_CUST_CD = "TRD_PTNR_SHIP_TO_CUST_CD";

    /** SHIP_TO_CUST_LOC_NM */
    static final String SHIP_TO_CUST_LOC_NM = "SHIP_TO_CUST_LOC_NM";

    /** SHIP_TO_CUST_CTY_ADDR */
    static final String SHIP_TO_CUST_CTY_ADDR = "SHIP_TO_CUST_CTY_ADDR";

    /** SHIP_TO_CUST_ST_CD */
    static final String SHIP_TO_CUST_ST_CD = "SHIP_TO_CUST_ST_CD";

    /** SHIP_TO_CUST_POST_CD */
    static final String SHIP_TO_CUST_POST_CD = "SHIP_TO_CUST_POST_CD";

    /** TRD_PTNR_SHPG_SVC_LVL_CD */
    static final String TRD_PTNR_SHPG_SVC_LVL_CD = "TRD_PTNR_SHPG_SVC_LVL_CD";

    /** TRD_PTNR_CARR_CD */
    static final String TRD_PTNR_CARR_CD = "TRD_PTNR_CARR_CD";

    /** TRD_PTNR_SHIP_VIA_CD */
    static final String TRD_PTNR_SHIP_VIA_CD = "TRD_PTNR_SHIP_VIA_CD";

    /** TRD_PTNR_FRT_CHRG_METH_CD */
    static final String TRD_PTNR_FRT_CHRG_METH_CD = "TRD_PTNR_FRT_CHRG_METH_CD";

    /** EDI PO TP */
    /** EDI_PO_TP_CD_SA */
    static final String EDI_PO_TP_CD_SA = "SA";

    /** EDI_PO_TP_CD_DA */
    static final String EDI_PO_TP_CD_DS = "DS";

    /** EDI_PO_TP_CD_PT (PassThrough) */
    static final String EDI_PO_TP_CD_PT = "PT";

    /** TRD_PTNR_SHIP_TO_CATG_CDP */
    static final String TRD_PTNR_SHIP_TO_CATG_CD_01 = "01";

    /** TRD_PTNR_SHIP_TO_CATG_CD_02 CHOICE. */
    static final String TRD_PTNR_SHIP_TO_CATG_CD_02 = "02";

    /** TRD_PTNR_SHIP_TO_CATG_CD_05. */
    static final String TRD_PTNR_SHIP_TO_CATG_CD_05 = "05";

    /** TRD_PTNR_SHIP_TO_CATG_CD_06. */
    static final String TRD_PTNR_SHIP_TO_CATG_CD_06 = "06";

    /** PO_QLFY_CD_PC. */
    static final String PO_QLFY_CD_PC = "PC";

    /** PO_QLFY_CD_DS. */
    static final String PO_QLFY_CD_DS = "DS";

    /** VND_SYS_TP_CD : P : Parts */
    static final String VND_SYS_TP_CD_P = "P";

    /** VND_SYS_TP_CD : W : Whole Sales */
    static final String VND_SYS_TP_CD_W = "W";

    /** EDI_TRX_PRPS_CD_O. */
    static final String EDI_TRX_PRPS_CD_O = "O";

    /** EDI_PO_SUB_LINE_NUM_DEFOULT. */
    static final String EDI_PO_SUB_LINE_NUM_DEFOULT = "000001";

    /** EFF_THRU_DATE. */
    static final String EFF_THRU_DATE = "29001231";

    /** EFF_FROM_DATE. */
    static final String EFF_FROM_DATE = "19000101";

    /** VND_XREF_TP_CD_1. */
    static final String VND_XREF_TP_CD_1 = "1";

    /** PO_MSG_SEG_ID. */
    static final String PO_MSG_SEG_ID = "0";

    /** TIMESTANP. */
    static final String TIMESTANP = "yyyyMMddHHmmssSSS";

    /** UOM_CD_EA. */
    static final String UOM_CD_EA = "EA";

    /** TXT_LENGTH_0. */
    static final int TXT_LENGTH_0 = 0;

    /** TXT_LENGTH_8. */
    static final int TXT_LENGTH_8 = 8;

    /** TXT_LENGTH_20. */
    static final int TXT_LENGTH_20 = 20;

    /** TXT_LENGTH_35. */
    static final int TXT_LENGTH_35 = 35;

    /** TXT_LENGTH_36. */
    static final int TXT_LENGTH_36 = 36;

    /** TXT_LENGTH_60. */
    static final int TXT_LENGTH_60 = 60;

    /** TXT_LENGTH_70. */
    static final int TXT_LENGTH_70 = 70;

    /** BIND_PO_MSG_TP_CD Special Instruction. */
    static final String BIND_PO_MSG_TP_CD_SI = "poMsgTpCdSi";

    /** BIND_PO_MSG_TP_CD Shipper Note. */
    static final String BIND_PO_MSG_TP_CD_SN = "poMsgTpCdSn";

    /** DB_PO_MSG_SEG_ID. */
    static final String DB_PO_MSG_SEG_ID = "PO_MSG_SEG_ID";

    /** QC#23814 add VAR_CHOICE_DS_SHIP_TO_CUST_CD. */
    static final String VAR_CHOICE_DS_SHIP_TO_CUST_CD = "CHOICE_DS_SHIP_TO_CUST_CD";

    /** TXT_LENGTH_30. */
    static final int TXT_LENGTH_30 = 30;

    // QC#26074 Start
    /** MD_VND_LOC_CD */
    static final String MD_VND_LOC_CD = "999";

    // QC#26074 End

    // QC#23356 Start
    /** CARR_TP_CD */
    static final String CARR_TP_CD = "CARR_TP_CD";

    /** carrTpCd */
    static final String BIND_CARR_TP_CD = "carrTpCd";

    /** PO_ORD_SRC_CD */
    static final String PO_ORD_SRC_CD = "PO_ORD_SRC_CD";

    /** carrTpCd */
    static final String BIND_CPO_ORD_NUM = "cpoOrdNum";

    /** DS_ORD_CATG_CD */
    static final String DS_ORD_CATG_CD = "DS_ORD_CATG_CD";

    /** DS_ORD_RSN_CD */
    static final String DS_ORD_RSN_CD = "DS_ORD_RSN_CD";

    /** dsOrdRsnCd */
    static final String BIND_DS_ORD_RSN_CD = "dsOrdRsnCd";

    /** dsOrdCatgCd */
    static final String BIND_DS_ORD_CATG_CD = "dsOrdCatgCd";

    /** poOrdSrcCd */
    public static final String BIND_PO_ORD_SRC_CD = "poOrdSrcCd";
    // QC#23356 End

    // QC#58769
    public static final String BIND_DS_ORD_TP_CD = "dsOrdTpCd";
    public static final String DS_ORD_TP_CD = "DS_ORD_TP_CD";
    // START 2023/02/17 TZ.Win [QC#60966, ADD]
    /** */
    static final String RQST_SHIP_DT = "RQST_SHIP_DT";
    // END 2023/02/17 TZ.Win [QC#60966, ADD]
}
