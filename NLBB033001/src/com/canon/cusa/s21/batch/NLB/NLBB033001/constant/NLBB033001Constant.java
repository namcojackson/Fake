/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB033001.constant;

/**
 * <pre>
 * Business ID : NLBB0330 Send Freight Info to CTSI (Work Table Creation) Batch
 * Function Name : NLBB033001Constant
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/31/2016   CITS            R.Shimamoto     Create          N/A
 * 04/14/2016   CITS            R.Shimamoto     Update          QC#6981
 * 12/12/2018   CITS            K.Ogino         Update          QC#29160
 * 03/04/2022   CITS            A.Cullano       Update          QC#59776
 *</pre>
 */
public class NLBB033001Constant {

    // ssm parameter map
    /** map key : glblCmpyCd. */
    public static final String MAP_KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** map key : sceOrdTpCd. */
    public static final String MAP_KEY_SCE_ORD_TP_CD = "sceOrdTpCd";

    /** map key : inbdOtbdCd. */
    public static final String MAP_KEY_INBD_OTBD_CD = "inbdOtbdCd";

    /** map key : fromLocTpCd. */
    public static final String MAP_KEY_FROM_LOC_TP_CD = "fromLocTpCd";

    /** map key : dropShipWhCd. */
    public static final String MAP_KEY_DROP_SHIP_WH_CD = "dropShipWhCd";

    /** map key : ntcDropFlg. */
    public static final String MAP_KEY_NTC_DROP_FLG = "ntcDropFlg";

    /** map key : dsBizLastUpdTs. */
    public static final String MAP_KEY_DS_BIZ_LAST_UPD_TS = "dsBizLastUpdTs";

    /** map key : thisProcTs. */
    public static final String MAP_KEY_THIS_PROC_TS = "thisProcTs";

    /** map key : shpgStsCd. */
    public static final String MAP_KEY_SHPG_STS_CD = "shpgStsCd";

    /** map key : batchId. */
    public static final String MAP_KEY_BATCH_ID = "batchId";

    /** map key : soSlpNum001. */
    public static final String MAP_KEY_SO_SLP_NUM_001 = "soSlpNum001";

    /** map key : soNum. */
    public static final String MAP_KEY_SO_NUM = "soNum";

    /** map key : rowNum. */
    public static final String MAP_KEY_ROW_NUM = "rowNum";

    /** map key : rwsNum. */
    public static final String MAP_KEY_RWS_NUM = "rwsNum";

    /** map key : poOrdNum. */
    public static final String MAP_KEY_PO_ORD_NUM = "poOrdNum";

    /** map key : rwsStsCd. */
    public static final String MAP_KEY_RWS_STS_CD = "rwsStsCd";

    /** Batch ID. */
    public static final String BATCH_ID = "NLBB0330";

    /** Business ID. */
    public static final String BUSINESS_ID = "NLBB033001";

    /** DS_BIZ_LAST_UPD_TS DEFAULT. */
    public static final String DS_BIZ_LAST_UPD_TS_DEFAULT = "00000101000000";

    /** TimeStamp Format(yyyyMMddHHmmssSSS). */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    // STR 04/28/2016 R.Shimamoto [QC#7662 DEL]
    // /** Date Format(HHmmss) */
    // public static final String DT_FORMAT_TS = "HHmmss";
    // END 04/28/2016 R.Shimamoto [QC#7662 DEL]

    /** Date Format(yyyyMMddHHmmss) */
    public static final String INVTY_TRX_DT_FORMAT_BF = "yyyyMMddHHmmss";

    /** Date Format(yyyyMMdd) */
    public static final String INVTY_TRX_DT_FORMAT_AF = "yyyyMMdd";

    /** DS_BIZ_PROC_LOG_SQ */
    public static final String DS_BIZ_PROC_LOG_SQ = "DS_BIZ_PROC_LOG_SQ";

    /** CARR_FRT_INFO_INTFC_WRK_SQ */
    public static final String CARR_FRT_INFO_INTFC_WRK_SQ = "CARR_FRT_INFO_INTFC_WRK_SQ";

    // db column
    /** db column : SCE_ORD_TP_CD. */
    public static final String SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /** db column : LOC_TP_CD. */
    public static final String LOC_TP_CD = "LOC_TP_CD";

    /** db column : WH_CD. */
    public static final String WH_CD = "WH_CD";

    /** db column : CARR_FRT_SRC_TP_XREF_NUM. */
    public static final String CARR_FRT_SRC_TP_XREF_NUM = "CARR_FRT_SRC_TP_XREF_NUM";

    /** db column : GLBL_CMPY_CD. */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** db column : INBD_OTBD_CD. */
    public static final String INBD_OTBD_CD = "INBD_OTBD_CD";

    /** db column : FROM_LOC_TP_CD. */
    public static final String FROM_LOC_TP_CD = "FROM_LOC_TP_CD";

    /** db column : DROP_SHIP_WH_CD. */
    public static final String DROP_SHIP_WH_CD = "DROP_SHIP_WH_CD";

    /** db column : SO_NUM. */
    public static final String SO_NUM = "SO_NUM";

    /** db column : SO_PROC_STS_CD. */
    public static final String SO_PROC_STS_CD = "SO_PROC_STS_CD";

    /** db column : SO_DATA_ACT_TP_CD. */
    public static final String SO_DATA_ACT_TP_CD = "SO_DATA_ACT_TP_CD";

    /** db column : SHIP_DT_TM_TS. */
    public static final String SHIP_DT_TM_TS = "SHIP_DT_TM_TS";

    /** db column : TOT_SHIP_WT. */
    public static final String TOT_SHIP_WT = "TOT_SHIP_WT";

    /** db column : TOT_FRT_AMT. */
    public static final String TOT_FRT_AMT = "TOT_FRT_AMT";

    /** db column : TRX_HDR_NUM. */
    public static final String TRX_HDR_NUM = "TRX_HDR_NUM";

    /** db column : POST_CD_STC. */
    public static final String POST_CD_STC = "POST_CD_STC";

    /** db column : POST_CD_RW. */
    public static final String POST_CD_RW = "POST_CD_RW";

    /** db column : COA_CH_CD. */
    public static final String COA_CH_CD = "COA_CH_CD";

    /** db column : COA_BR_CD_RW. */
    public static final String COA_BR_CD_RW = "COA_BR_CD_RW";

    /** db column : COA_BR_CD_TOC. */
    public static final String COA_BR_CD_TOC = "COA_BR_CD_TOC";

    /** db column : DS_COND_CONST_VAL_TXT_01. */
    public static final String DS_COND_CONST_VAL_TXT_01 = "DS_COND_CONST_VAL_TXT_01";

    /** db column : CARR_CD. */
    public static final String CARR_CD = "CARR_CD";

    /** db column : TRX_HDR_NUM_TP_CD. */
    public static final String TRX_HDR_NUM_TP_CD = "TRX_HDR_NUM_TP_CD";

    /** db column : TRX_HDR_NUM_TP_CD_01. */
    public static final String TRX_HDR_NUM_TP_CD_01 = "TRX_HDR_NUM_TP_CD_01";

    /** db column : TRX_HDR_NUM_TP_CD_02. */
    public static final String TRX_HDR_NUM_TP_CD_02 = "TRX_HDR_NUM_TP_CD_02";

    /** db column : PO_RCV_TRX_HDR_NUM. */
    public static final String PO_RCV_TRX_HDR_NUM = "PO_RCV_TRX_HDR_NUM";

    /** db column : CARR_FRT_SRC_TP_NM. */
    public static final String CARR_FRT_SRC_TP_NM = "CARR_FRT_SRC_TP_NM";

    /** db column : CARR_FRT_PRFL_NUM. */
    public static final String CARR_FRT_PRFL_NUM = "CARR_FRT_PRFL_NUM";

    /** db column : CARR_FRT_SRC_TP_DESC_TXT. */
    public static final String CARR_FRT_SRC_TP_DESC_TXT = "CARR_FRT_SRC_TP_DESC_TXT";

    /** db column : TRX_REF_NUM_TP_CD_01. */
    public static final String TRX_REF_NUM_TP_CD_01 = "TRX_REF_NUM_TP_CD_01";

    /** db column : DEF_COA_CMPY_CD. */
    public static final String DEF_COA_CMPY_CD = "DEF_COA_CMPY_CD";

    /** db column : DEF_COA_BR_CD. */
    public static final String DEF_COA_BR_CD = "DEF_COA_BR_CD";

    /** db column : TRX_REF_NUM_TP_CD_02. */
    public static final String TRX_REF_NUM_TP_CD_02 = "TRX_REF_NUM_TP_CD_02";

    /** db column : DEF_COA_CC_CD. */
    public static final String DEF_COA_CC_CD = "DEF_COA_CC_CD";

    /** db column : DEF_COA_ACCT_CD. */
    public static final String DEF_COA_ACCT_CD = "DEF_COA_ACCT_CD";

    /** db column : DEF_COA_PROD_CD. */
    public static final String DEF_COA_PROD_CD = "DEF_COA_PROD_CD";

    /** db column : DEF_COA_CH_CD. */
    public static final String DEF_COA_CH_CD = "DEF_COA_CH_CD";

    /** db column : DEF_COA_AFFL_CD. */
    public static final String DEF_COA_AFFL_CD = "DEF_COA_AFFL_CD";

    /** db column : DEF_COA_MDSE_TP_CD. */
    public static final String DEF_COA_MDSE_TP_CD = "DEF_COA_MDSE_TP_CD";

    /** db column : CARR_FRT_CHRG_BU_CD. */
    public static final String CARR_FRT_CHRG_BU_CD = "CARR_FRT_CHRG_BU_CD";

    /** db column : DEF_CARR_CD. */
    public static final String DEF_CARR_CD = "DEF_CARR_CD";

    /** db column : DEF_COA_CH_CD. */
    public static final String RWS_DT_TM_TS = "RWS_DT_TM_TS";

    /** db column : FROM_LOC_POST_CD. */
    public static final String FROM_LOC_POST_CD = "FROM_LOC_POST_CD";

    /** db column : PRCH_REQ_NUM. */
    public static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** db column : POST_CD. */
    public static final String POST_CD = "POST_CD";

    /** db column : PO_ORD_NUM. */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";

    /** db column : RWS_NUM. */
    public static final String RWS_NUM = "RWS_NUM";

    /** db column : RWS_NUM_RH. */
    public static final String RWS_NUM_RH = "RWS_NUM_RH";

    /** db column : IMPT_INV_PK. */
    public static final String IMPT_INV_PK = "IMPT_INV_PK";

    /** db column : IMPT_INV_NUM. */
    public static final String IMPT_INV_NUM = "IMPT_INV_NUM";

    /** db column : DEF_CARR_FRT_CHRG_BU_CD. */
    public static final String DEF_CARR_FRT_CHRG_BU_CD = "DEF_CARR_FRT_CHRG_BU_CD";

    /** db column : IMPT_INV_CLS_CD. */
    public static final String IMPT_INV_CLS_CD = "IMPT_INV_CLS_CD";

    /** db column : IMPT_INV_VER_NUM. */
    public static final String IMPT_INV_VER_NUM = "IMPT_INV_VER_NUM";

    /** db column : IMPT_INV_SLP_CLS_CD. */
    public static final String IMPT_INV_SLP_CLS_CD = "IMPT_INV_SLP_CLS_CD";

    /** db column : IMPT_CNTNR_LOT_NUM. */
    public static final String IMPT_CNTNR_LOT_NUM = "IMPT_CNTNR_LOT_NUM";

    /** db column : IMPT_CNTNR_NUM. */
    public static final String IMPT_CNTNR_NUM = "IMPT_CNTNR_NUM";

    /** db column : INBD_SRC_TP_CD. */
    public static final String INBD_SRC_TP_CD = "INBD_SRC_TP_CD";

    /** db column : RWS_STS_CD. */
    public static final String RWS_STS_CD = "RWS_STS_CD";

    /** db column : RWS_PRT_FLG. */
    public static final String RWS_PRT_FLG = "RWS_PRT_FLG";

    /** db column : IMPT_INV_VESL_NM. */
    public static final String IMPT_INV_VESL_NM = "IMPT_INV_VESL_NM";

    /** db column : IMPT_INV_BOL_NUM. */
    public static final String IMPT_INV_BOL_NUM = "IMPT_INV_BOL_NUM";

    /** db column : WH_IN_ETA_DT. */
    public static final String WH_IN_ETA_DT = "WH_IN_ETA_DT";

    /** db column : LTST_WH_IN_ETA_DT. */
    public static final String LTST_WH_IN_ETA_DT = "LTST_WH_IN_ETA_DT";

    /** db column : FROM_LOC_NM_01. */
    public static final String FROM_LOC_NM_01 = "FROM_LOC_NM_01";

    /** db column : FROM_LOC_NM_02. */
    public static final String FROM_LOC_NM_02 = "FROM_LOC_NM_02";

    /** db column : FROM_LOC_CTY_ADDR. */
    public static final String FROM_LOC_CTY_ADDR = "FROM_LOC_CTY_ADDR";

    /** db column : FROM_LOC_ST_CD. */
    public static final String FROM_LOC_ST_CD = "FROM_LOC_ST_CD";

    /** db column : FROM_LOC_CTRY_CD. */
    public static final String FROM_LOC_CTRY_CD = "FROM_LOC_CTRY_CD";

    /** db column : FROM_LOC_PSN_NM. */
    public static final String FROM_LOC_PSN_NM = "FROM_LOC_PSN_NM";

    /** db column : FROM_LOC_TEL_NUM. */
    public static final String FROM_LOC_TEL_NUM = "FROM_LOC_TEL_NUM";

    /** db column : FROM_LOC_ADDR_01. */
    public static final String FROM_LOC_ADDR_01 = "FROM_LOC_ADDR_01";

    /** db column : FROM_LOC_ADDR_02. */
    public static final String FROM_LOC_ADDR_02 = "FROM_LOC_ADDR_02";

    /** db column : FROM_LOC_ADDR_03. */
    public static final String FROM_LOC_ADDR_03 = "FROM_LOC_ADDR_03";

    /** db column : FROM_LOC_ADDR_04. */
    public static final String FROM_LOC_ADDR_04 = "FROM_LOC_ADDR_04";

    /** db column : FROM_LOC_CD. */
    public static final String FROM_LOC_CD = "FROM_LOC_CD";

    /** db column : DNLD_DT_TM_TS. */
    public static final String DNLD_DT_TM_TS = "DNLD_DT_TM_TS";

    /** db column : TRX_CD. */
    public static final String TRX_CD = "TRX_CD";

    /** db column : TRX_RSN_CD. */
    public static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** db column : CNTNR_SEAL_NUM. */
    public static final String CNTNR_SEAL_NUM = "CNTNR_SEAL_NUM";

    /** db column : TOT_RCV_WT. */
    public static final String TOT_RCV_WT = "TOT_RCV_WT";

    /** db column : RWS_CARR_CD. */
    public static final String RWS_CARR_CD = "RWS_CARR_CD";

    /** db column : TRUCK_CNTNR_PK. */
    public static final String TRUCK_CNTNR_PK = "TRUCK_CNTNR_PK";

    /** db column : IMPT_INV_SHPG_METH_CD. */
    public static final String IMPT_INV_SHPG_METH_CD = "IMPT_INV_SHPG_METH_CD";

    /** db column : INLND_SHPG_METH_CD. */
    public static final String INLND_SHPG_METH_CD = "INLND_SHPG_METH_CD";

    /** db column : WMS_DROP_STS_CD. */
    public static final String WMS_DROP_STS_CD = "WMS_DROP_STS_CD";

    /** db column : RWS_REF_NUM. */
    public static final String RWS_REF_NUM = "RWS_REF_NUM";

    /** db column : DRCT_SHIP_TP_CD. */
    public static final String DRCT_SHIP_TP_CD = "DRCT_SHIP_TP_CD";

    /** db column : RTRN_AUTH_HDR_PK. */
    public static final String RTRN_AUTH_HDR_PK = "RTRN_AUTH_HDR_PK";

    /** db column : WH_ETA_TEMP_DT. */
    public static final String WH_ETA_TEMP_DT = "WH_ETA_TEMP_DT";

    /** db column : SCHD_ETA_CHK_FLG. */
    public static final String SCHD_ETA_CHK_FLG = "SCHD_ETA_CHK_FLG";

    /** db column : FINAL_ETA_CHK_FLG. */
    public static final String FINAL_ETA_CHK_FLG = "FINAL_ETA_CHK_FLG";

    /** db column : APPT_HOUR_MN. */
    public static final String APPT_HOUR_MN = "APPT_HOUR_MN";

    /** db column : APPT_DRYG_CARR_CD. */
    public static final String APPT_DRYG_CARR_CD = "APPT_DRYG_CARR_CD";

    /** db column : WH_SCHD_CMNT_TXT. */
    public static final String WH_SCHD_CMNT_TXT = "WH_SCHD_CMNT_TXT";

    /** db column : TRX_ORD_NUM. */
    public static final String TRX_ORD_NUM = "TRX_ORD_NUM";

    /** db column : SCHD_ETA_DT. */
    public static final String SCHD_ETA_DT = "SCHD_ETA_DT";

    /** db column : FINAL_ETA_DT. */
    public static final String FINAL_ETA_DT = "FINAL_ETA_DT";

    /** db column : SYS_SRC_CD. */
    public static final String SYS_SRC_CD = "SYS_SRC_CD";

    /** db column : FIRST_STK_IN_DT. */
    public static final String FIRST_STK_IN_DT = "FIRST_STK_IN_DT";

    /** db column : RWS_NUM_DRH. */
    public static final String RWS_NUM_DRH = "RWS_NUM_DRH";

    /** db column : IMPT_INTRX_SRC_TP_CDV_NUM. */
    public static final String TRX_SRC_TP_CD = "TRX_SRC_TP_CD";

    /** db column : PICK_UP_RQST_FLG. */
    public static final String PICK_UP_RQST_FLG = "PICK_UP_RQST_FLG";

    /** db column : PICK_UP_RQST_TS. */
    public static final String PICK_UP_RQST_TS = "PICK_UP_RQST_TS";

    /** db column : SVC_CONFIG_MSTR_PK. */
    public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** db column : SHIP_FROM_SO_NUM. */
    public static final String SHIP_FROM_SO_NUM = "SHIP_FROM_SO_NUM";

    /** db column : DS_CPO_CONFIG_PK. */
    public static final String DS_CPO_CONFIG_PK = "DS_CPO_CONFIG_PK";

    /** db column : RWS_CLO_DT_TM_TS. */
    public static final String RWS_CLO_DT_TM_TS = "RWS_CLO_DT_TM_TS";

    /** db column : CPO_ORD_NUM. */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /** Error Message Text: equals */
    public static final String MSG_TXT_EQUALS = "=";

    /** STR_01 */
    public static final String STR_01 = "01";

    /** STR_02 */
    public static final String STR_02 = "02";

    /** STR_03 */
    public static final String STR_03 = "03";

    /** STR_04 */
    public static final String STR_04 = "04";

    /** INT_1 */
    public static final int INT_1 = 1;

    // db table
    /** db table : CARR_FRT_INFO_INTFC_WRK */
    public static final String CARR_FRT_INFO_INTFC_WRK = "CARR_FRT_INFO_INTFC_WRK";

    /** db table : DS_BIZ_PROC_LOG */
    public static final String DS_BIZ_PROC_LOG = "DS_BIZ_PROC_LOG";

    // error message code
    /** The Constant [@] was not found on Constant table. */
    public static final String NPAM1010E = "NPAM1010E";

    /** message id : NWZM0650E */
    public static final String MSG_ID_NWZM0650E = "NWZM0650E";

    /** message id : NLAM1270E */
    public static final String MSG_ID_NLAM1270E = "NLAM1270E";

    /** MAIL_KEY_FROM */
    public static final String MAIL_KEY_FROM = "From";

    /** MAIL_KEY_TO */
    public static final String MAIL_KEY_TO = "To";

    /** MAIL_KEY_TO */
    public static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** MAIL_TEMPLATE_ID */
    public static final String MAIL_TEMPLATE_ID = "NLBB0330M001";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** It failed to register an  email. */
    public static final String NLBM1065E = "NLBM1065E";

    /** START 2022/03/04 A.Cullano [QC#59776, ADD] */
    /** CUSA Supplier ID */
    public static final String CUSA_SPLY = "614271";

    /** CUSA Supplier Site ID (Parts) */
    public static final String CUSA_SPLY_PRT = "0000900335";

    /** END 2022/03/04 A.Cullano [QC#59776, ADD] */

}
