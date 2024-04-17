/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB030001.constant;

/**
 * <pre>
 * Batch Program Constant Class for WMS to Middleware Transaction.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/01/2013   CSAI            K.Kondo         Create          MW Replace Initial
 * 04/05/2016   CITS            N.Akaishi       Update          V1.0
 * 12/28/2016   CITS            T.Tokutomi      Update          QC#16682
 * 02/01/2017   CITS            K.Ogino         Update          QC#17352
 * 07/23/2018   CITS            Y.Iwasaki       Update          QC#26674
 * 09/06/2019   CITS            R.Kurahashi     Update          QC#53048
 *</pre>
 */
public class NLGB030001Constant {

    // *********************************************************
    // Constants: Message ID
    // *********************************************************
    /**
     * Message ID: NLGM0041E [@] does not exist. Table:[@], Search
     * Key:[@]
     */
    public static final String NLGM0041E = "NLGM0041E";

    /**
     * Message ID: NLGM0043E A code which does not exist in the Code
     * Table is set. Table:[@], Search Key:[@], Column:[@], Code: [@]
     */
    public static final String NLGM0043E = "NLGM0043E";

    /**
     * Message ID: NLGM0045E The record cannot be registered.
     * Registration Table Name: [@], Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    public static final String NLGM0045E = "NLGM0045E";

    /**
     * Message ID: NLGM0047E Warehouse code to be processed is not
     * set. Please check the WMS warehouse table. MW_REPL_TRGT_GRP_CD:
     * [@]
     */
    public static final String NLGM0047E = "NLGM0047E";

    /**
     * Message ID: NLGM0049E Parameter has not been set. [@] Parameter
     * has not been set.
     */
    public static final String NLGM0049E = "NLGM0049E";

    // *********************************************************
    // Constants: Unique
    // *********************************************************
    /** Business ID */
    public static final String BUSINESS_ID = "NLGB0300";

    /** Program ID for Log */
    public static final String PROGRAM_ID = " ## NLGB030001 ## ";

    /** Prameter Name: GLBL_CMPY_CD */
    public static final String PRAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Prameter Name: INTERFACE_ID */
    public static final String PRAM_NM_INTERFACE_ID = "Interface Id";

    /** Prameter Name: WH_GRP_CD */
    public static final String PRAM_NM_WH_GRP_CD = "Warehouse Group Code";

    /** SSM set Key Name: GLBL_CMPY_CD */
    public static final String KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** SSM set Key Name: INTERFACE_ID */
    public static final String KEY_INTERFACE_ID = "interfaceId";

    /** SSM set Key Name: TRANSACTION_ID */
    public static final String KEY_TRANSACTION_ID = "transactionId";

    /** SSM set Key Name: OTBD_ORD_NUM */
    public static final String KEY_OTBD_ORD_NUM = "otbdOrdNum";

    /** SSM set Key Name: INBD_ORD_NUM */
    public static final String KEY_INBD_ORD_NUM = "inbdOrdNum";

    /** SSM set Key Name: WMS_TRGT_WH_CD */
    public static final String KEY_WMS_TRGT_WH_CD = "wmsTrgtWhCd";

    /** SSM set Key Name: Length Cut WMS_GRP_ID_03_FROM */
    public static final String KEY_LG_CUT_WMS_GRP_ID_03_FROM = "lgCutWmsGrpID03From";

    /** SSM set Key Name: Length Cut WMS_GRP_ID_03_FROM */
    public static final String KEY_LG_CUT_WMS_GRP_ID_03_TO = "lgCutWmsGrpID03To";

    /** SSM set Key Name: whOwnrCd */
    public static final String KEY_WH_OWNR_CD = "whOwnrCd";

    /** SSM set Key Name: tplCarrCd */
    public static final String KEY_TPL_CARR_CD = "tplCarrCd";

    /** SSM set Key Name: wmsTaskCd */
    public static final String KEY_WMS_TASK_CD = "wmsTaskCd";

    /** SSM set Key Name: wmsStsCd */
    public static final String KEY_WMS_STS_CD = "wmsStsCd";
    
    /** SSM set Key Name: wmsStsCd */
    public static final String KEY_RTL_WH_CD = "rtlWhCd";

    /** setting of WRK_TRX_ID of table WMS_INBD_TRX */
    public static final String CON_WRK_TRX_ID = "000000000000000000000000000001";

    /** setting of STK_STS_CD of table WMS_INBD_TRX */
    public static final String DEFAULT_STK_STS_CD = "1";

    /** Value to determine the INBD_ORD_TP of PO */
    public static final String INBD_ORD_TP_PO = "PO";

    /** Value to determine the INBD_ORD_TP of SO */
    public static final String OTBD_ORD_TP_SO = "SO";

    /** Date and time formats */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmss";

    /** Length to check the ORD_TP_CD */
    public static final int ORD_TP_CD_CHK_LEN = 3;

    /** Division value ORD_LINE_DIV_NUM */
    public static final int ORD_LINE_DIV_NUM = 1000;

    /** transaction Id Padding length */
    public static final int LEN_PD_TRX_ID = 30;

    /** Task Code Size of array */
    public static final int LEN_WMS_TASC_CD_MAP = 20;

    /** Warehose Code Size of array */
    public static final int LEN_WMS_WH_MAP = 50;

    /** Substring From length for determine the OTBD_ORD_TP_CD */
    public static final int LG_CUT_ORD_TP_CD_DETEMINE_FROM = 0;

    /** Substring To length for determine the OTBD_ORD_TP_CD */
    public static final int LG_CUT_ORD_TP_CD_DETEMINE_TO = 2;

    /** Substring From length for OTBD_ORD_TP_CD */
    public static final int LG_CUT_ORD_TP_CD_FROM = 2;

    /** Substring To length for OTBD_ORD_TP_CD */
    public static final int LG_CUT_ORD_TP_CD_TO = 3;

    /** Substring From length for determine the WMS_GROUP_ID_03 */
    public static final int LG_CUT_WMS_GROUP_ID_03_FROM = 1;

    // 05/31/2016 CITS N.Akaishi Mod QC8770 START
    /** Substring From length WMS_PKG_CD(for Interface Data (SO)) */
    public static final int LG_CUT_WMS_PKG_CD_FROM = 0;

    /** Substring To length WMS_PKG_CD(for Interface Data (SO)) */
    public static final int LG_CUT_WMS_PKG_CD_TO = 3;

    /** Substring From length WMS_WH_CD(for Interface Data (SO)) */
    public static final int LG_CUT_WMS_WH_CD_FROM = 0;

    /** Substring To length WMS_WH_CD(for Interface Data (SO)) */
    public static final int LG_CUT_WMS_WH_CD_TO = 3;

    /** Length WMS_WH_CD(for Interface Data (SO)) */
    public static final int LG_WMS_WH_CD = 3;

    /** Length WMS_PKG_CD(for Interface Data (SO)) */
    public static final int LG_WMS_PKG_CD = 3;
    // 05/31/2016 CITS N.Akaishi Mod QC8770 END

// 2016/04/06 N.Akaishi [V1.0 Del] Start
//    /**
//     * VAR_CHAR_CONST Key Name: Length to cut the warehouse code for
//     * Tecsys
//     */
//    public static final String CD_KEY_LG_CUT_WH_CD_FOR_TECSYS = "LG_CUT_WH_CD_FOR_TECSYS";

    /** Table name of WMS_INBD_TRX */
    public static final String TBL_WMS_INBD_TRX = "WMS_INBD_TRX";

    /** Column name of GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name of INTFC_ID */
    public static final String COL_INTFC_ID = "INTFC_ID";

    /** Column name of INTFC_RCV_TS */
    public static final String COL_INTFC_RCV_TS = "INTFC_RCV_TS";

    /** Column name of INTFC_TRX_ID */
    public static final String COL_INTFC_TRX_ID = "INTFC_TRX_ID";

    /** Column name of INTFC_TRX_SQ_NUM */
    public static final String COL_INTFC_TRX_SQ_NUM = "INTFC_TRX_SQ_NUM";

    /** Column name of WRK_TRX_ID */
    public static final String COL_WRK_TRX_ID = "WRK_TRX_ID";

    /** Column name of INTFC_PROC_STS_CD */
    public static final String COL_INTFC_PROC_STS_CD = "INTFC_PROC_STS_CD";

    /** Column name of INTFC_ERR_MSG_CD */
    public static final String COL_INTFC_ERR_MSG_CD = "INTFC_ERR_MSG_CD";

    /** Column name of WMS_BATCH_ID */
    public static final String COL_WMS_BATCH_ID = "WMS_BATCH_ID";

    /** Column name of WMS_UPD_HIST_NUM */
    public static final String COL_WMS_UPD_HIST_NUM = "WMS_UPD_HIST_NUM";

    /** Column name of WMS_TASK_CD */
    public static final String COL_WMS_TASK_CD = "WMS_TASK_CD";

    /** Column name of WMS_REC_ID */
    public static final String COL_WMS_REC_ID = "WMS_REC_ID";

    /** Column name of WH_CD */
    public static final String COL_WH_CD = "WH_CD";

    /** Column name of TRANSACTION_ID */
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /** Column name of INTERFACE_ID */
    public static final String COL_INTERFACE_ID = "INTERFACE_ID";

    /** Column name of SEGMENT_ID */
    public static final String COL_SEGMENT_ID = "SEGMENT_ID";

    /** Column name of UNIT_ID */
    public static final String COL_UNIT_ID = "UNIT_ID";

    /** Column name of SEQ_NUMBER */
    public static final String COL_SEQ_NUMBER = "SEQ_NUMBER";

    /** Column name of BOL_NUM */
    public static final String COL_BOL_NUM = "BOL_NUM";

    /** Column name of WMS_FRT_CHRG_AMT */
    public static final String COL_WMS_FRT_CHRG_AMT = "WMS_FRT_CHRG_AMT";

    /** Column name of OTBD_ORD_NUM */
    public static final String COL_OTBD_ORD_NUM = "OTBD_ORD_NUM";

    /** Column name of WMS_TRX_DT_TM_TS */
    public static final String COL_WMS_TRX_DT_TM_TS = "WMS_TRX_DT_TM_TS";

    /** Column name of WMS_TRX_DT_TM_TS */
    public static final String COL_WMS_SHIP_DT_TM_TS = "WMS_SHIP_DT_TM_TS";

    /** Column name of WMS_STS_CD */
    public static final String COL_WMS_STS_CD = "WMS_STS_CD";

    /** Column name of WMS_MDSE_CD */
    public static final String COL_WMS_MDSE_CD = "WMS_MDSE_CD";

    /** Column name of WMS_PKG_CD */
    public static final String COL_WMS_PKG_CD = "WMS_PKG_CD";

    /** Column name of WMS_STK_STS_CD */
    public static final String COL_WMS_STK_STS_CD = "WMS_STK_STS_CD";

    /** Column name of WMS_RCPT_DT_TM_TS */
    public static final String COL_WMS_RCPT_DT_TM_TS = "WMS_RCPT_DT_TM_TS";

    /** Column name of INBD_ORD_NUM */
    public static final String COL_INBD_ORD_NUM = "INBD_ORD_NUM";

    /** Column name of INBD_ORD_TP_CD */
    public static final String COL_INBD_ORD_TP_CD = "INBD_ORD_TP_CD";

    /** Column name of INBD_ORD_LINE_NUM */
    public static final String COL_INBD_ORD_LINE_NUM = "INBD_ORD_LINE_NUM";

    /** Column name of WMS_RCPT_ID */
    public static final String COL_WMS_RCPT_ID = "WMS_RCPT_ID";

    /** Column name of WMS_CARR_CD */
    public static final String COL_WMS_CARR_CD = "WMS_CARR_CD";

    /** Column name of CARR_CD */
    public static final String COL_CARR_CD = "WMS_CARR_CD";

    /** Column name of WMS_RSN_CD */
    public static final String COL_WMS_RSN_CD = "WMS_RSN_CD";

    /** Column name of WMS_ADJ_PCT */
    public static final String COL_WMS_ADJ_PCT = "WMS_ADJ_PCT";

    /** Column name of WMS_UOM_CD */
    public static final String COL_WMS_UOM_CD = "WMS_UOM_CD";

    /** Column name of WMS_PROC_QTY */
    public static final String COL_WMS_PROC_QTY = "WMS_PROC_QTY";

    /** Column name of OTBD_ORD_LINE_NUM */
    public static final String COL_OTBD_ORD_LINE_NUM = "OTBD_ORD_LINE_NUM";

    /** Column name of RTL_WH_CD */
    public static final String COL_RTL_WH_CD = "RTL_WH_CD";

    /** Column name of RTL_SWH_CD */
    public static final String COL_RTL_SWH_CD = "RTL_SWH_CD";

    /** Column name of SER_NUM */
    public static final String COL_SER_NUM = "SER_NUM";

    /** Column name of INBD_OTBD_CD */
    public static final String COL_INBD_OTBD_CD = "INBD_OTBD_CD";

    /** Column name of WMS_TASK_CD_1 */
    public static final String COL_WMS_TASK_CD_1 = "WMS_TASK_CD_1";

    /** Column name of USR_ID_02 */
    public static final String COL_USR_ID_02 = "USR_ID_02";

    /** Column name of USR_ID_03 */
    public static final String COL_USR_ID_03 = "USR_ID_03";

    /** Column name of INTFC_TP_ID */
    public static final String COL_INTFC_TP_ID = "INTFC_TP_ID";

    /** Column name of WMS_ORG_HOST_ID */
    public static final String COL_WMS_ORG_HOST_ID = "WMS_ORG_HOST_ID";

    /** for (ADJ,ADJ2) **/
    /** Column name of WMS_FROM_PKG_CD */
    public static final String COL_WMS_FROM_PKG_CD = "WMS_FROM_PKG_CD";

    /** Column name of WMS_TO_PKG_CD */
    public static final String COL_WMS_TO_PKG_CD = "WMS_TO_PKG_CD";

    /** Column name of WMS_FROM_STK_STS_CD */
    public static final String COL_WMS_FROM_STK_STS_CD = "WMS_FROM_STK_STS_CD";

    /** Column name of WMS_TO_STK_STS_CD */
    public static final String COL_WMS_TO_STK_STS_CD = "WMS_TO_STK_STS_CD";

    /** Column name of INVTY_OWNR_CD */
    public static final String COL_INVTY_OWNR_CD = "INVTY_OWNR_CD";

    /** Column name of INVTY_OWNR_CD */
    public static final String COL_WMS_ORD_TP_CD = "WMS_ORD_TP_CD";

    /** Table name of NLGI0200_01 */
    public static final String TBL_NLGI0200_01 = "NLGI0200_01";

    /** Table name of NLGI0200_02 */
    public static final String TBL_NLGI0200_02 = "NLGI0200_02";

    /** Table name of NLGI0200_03 */
    public static final String TBL_NLGI0200_03 = "NLGI0200_03";

    /** Table name of NLBI0250_01 */
    public static final String TBL_NLBI0250_01 = "NLBI0250_01";

    /** Table name of NLCI0250_01 */
    public static final String TBL_NLCI0250_01 = "NLCI0250_01";

    /** 0 */
    public static final int ZERO = 0;

    /** 6 */
    public static final int SIX = 6;

    /** "0" */
    public static final String VAL_ZERO = "0";

    /** "" */
    public static final String VAL_EMPTY = "";

    /** "," */
    public static final String VAL_CAMMA = ",";

    /** Function Return Status (Normal End) */
    public static final int ST_NORMAL_END = 0;

    /** Function Return Status (Application Exception) */
    public static final int ST_APPL_ERR_END = -1;

    /** WMS_STS_CD : A */
    public static final String WMS_STS_CD_A = "A";

    /** WMS_STS_CD : T */
    public static final String WMS_STS_CD_T = "T";

    /** WMS_STS_CD : M */
    public static final String WMS_STS_CD_M = "M";

    /** SQL Statement Id */
    /** getTplCarrSvcLvl */
    public static final String SQL_STMT_ID_GET_TPL_CARR_SVC_LVL = "getTplCarrSvcLvl";

    /** getWmsTaskInfo */
    public static final String SQL_STMT_ID_GET_WMS_TASK_INFO = "getWmsTaskInfo";

    /** getPoIfData */
    public static final String SQL_STMT_ID_GET_PO_IF_DATA = "getPoIfData";

    /** getAdjIfData */
    public static final String SQL_STMT_ID_GET_ADJ_IF_DATA = "getAdjIfData";

    /** getSoIfData */
    public static final String SQL_STMT_ID_GET_SO_IF_DATA = "getSoIfData";

    /** getPodIfData */
    public static final String SQL_STMT_ID_GET_POD_IF_DATA = "getPodIfData";

    /** getInvtyIfData */
    public static final String SQL_STMT_ID_GET_INVTY_IF_DATA = "getInvtyIfData";
    
    /** getInvtyIfData */
    public static final String SQL_STMT_ID_GET_WMS_PKG_CD = "getRtlSwhCd";
    
    /** COL_RCV_SER_TAKE_FLG */
    public static final String COL_RCV_SER_TAKE_FLG = "RCV_SER_TAKE_FLG";
}
