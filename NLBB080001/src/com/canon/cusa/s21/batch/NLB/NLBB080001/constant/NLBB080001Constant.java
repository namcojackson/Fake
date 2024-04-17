/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB080001.constant;

import java.math.BigDecimal;

/**
 * <pre>    
 * Batch Program Class for Ship Parts Request to Choice
 *  
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/03/2016   CITS            S.Tanikawa      Create          
 * 05/19/2017   CITS            T.Kikuhara      Update          RS#2831
 * 06/23/2017   CITS            T.Kikuhara      Update          QC#19529
 * 11/20/2017   CITS            T.Wada          Update          QC#22546
 * 12/20/2017   CITS            S.Katsuma       Update          QC#22592
 * 01/11/2018   CITS            T.Wada          Update          QC#18460
 * 01/24/2018   CITS            K.Ogino         Update          QC#23045
 * 06/06/2018   CITS            Y.Iwasaki       Update          QC#25312
 * 06/13/2018   CITS            T.Hakodate      Update          QC#26637
 * 01/18/2019   Fujitsu         S.Ohki          Update          QC#30006
 * 07/12/2019   CITS            K.Ogino         Update          QC#51496
 * 06/15/2021   CITS            M.Furugoori     Update          QC#56495
 * 06/15/2022   Hitachi         K.Kim           Update          QC#60022
 * 07/12/2023   Hitachi         M.Kikushima     Update          QC#61591
 *</pre>
 */
public class NLBB080001Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NLBB0800";

    /** Output Log Program ID */
    public static final String PROGRAM_ID = "NLBB080001:";

    /** Output table : NLBI1110_01 for SO/RWS Header */
    public static final String TBL_NLBI1110_01 = "NLBI1110_01";

    /** Output table : NLBI1110_03 for SO/RWS Text */
    public static final String TBL_NLBI1110_03 = "NLBI1110_03";

    /** Output table : NLBI1110_02 for SO/RWS Detail */
    public static final String TBL_NLBI1110_02 = "NLBI1110_02";

    /** Output table : NLBI1110_05 for SO/RWS Serial */
    public static final String TBL_NLBI1110_05 = "NLBI1110_05";

    /** Output table : NLBI1110_04 for SO/RWS Site Survey */
    public static final String TBL_NLBI1110_04 = "NLBI1110_04";

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

    // START 2017/12/20 S.Katsuma [QC#22592,ADD]
    /**
     * The record cannot be updated. Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    public static final String NLGM0046E = "NLGM0046E";
    // END 2017/12/20 S.Katsuma [QC#22592,ADD]

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

    /** Message ID : ZZM9000E The field of [@] is not input. */
    public static final String ZZM9000E = "ZZM9000E";

    /** The value which is not numerical was input to the field of [@]. */
    public static final String ZZM9004E = "ZZM9004E";

    /** Message ID : ZZBM0009I */
    public static final String ZZBM0009I = "ZZBM0009I";

    /**
     * The corresponding data does not exist. Table Name : [@],
     * Key Field Name:  [@], Key Value: [@]
     */
    public static final String NLAM1001E = "NLAM1001E";

    /** VAR_CHAR_CONCT_NM_TG_ORDER */
    public static final String VAR_CHAR_CONCT_NM_TG_ORDER = "NLBB0800_TARGET_ORDER";

    /** VAR_CHAR_CONCT_NM_TG_CARRIER */
    public static final String VAR_CHAR_CONCT_NM_TG_CARRIER = "NLBB0800_TARGET_CARRIER";

    // START 2022/06/15 [QC#60022, ADD]
    /** VAR_CHAR_CONCT_NM_TG_SCE_ORD_TP */
    public static final String VAR_CHAR_CONCT_NM_TG_SCE_ORD_TP = "STI_TARGET_SCE_ORD_TP_CD";
    // END 2022/06/15 [QC#60022, ADD]

    /** TPL_FROM_PTNR_ID : CANON */
    public static final String VAL_CONST_TPL_FROM_PTNR_ID = "OCE";

    /** TPL_TO_PTNR_ID : STI */
    public static final String VAL_CONST_TPL_TO_PTNR_ID = "STI";

    /** ORD_TP_TXT : LF */
    public static final String VAL_CONST_ORD_TP_TXT = "LF";

    /** TPL_PTNR_TXT : LF */
    public static final String VAL_CONST_TPL_PTNR_TXT = "WE";

    // START 2023/07/12 [QC#61591, ADD]
    /** ORD_TP_TXT : DP */
    public static final String VAL_CONST_ORD_TP_DP_TXT = "DP";
    // END 2023/07/12 [QC#61591, ADD]

    /** CARR_CD : SPCG */
    public static final String VAL_CONST_CARR_CD = "SPCG";

    /** TPL_ORG_TXT : 0110 */
    public static final String VAL_CONST_TPL_ORG_TXT = "0110";

    /** TPL_ITEM_TP_CD : 0110 */
    public static final String VAL_CONST_TPL_ITEM_TP_CD = "ZSUB";

    /** SO_MSG_TP_CD : S */
    public static final String VAL_CONST_SO_MSG_TP_CD = "S";

    /** String Value : 01 */
    public static final String VAL_01 = "01";

    /** String Value : 02 */
    public static final String VAL_02 = "02";

    /** String Value : 03 */
    public static final String VAL_03 = "03";

    /** String Value : 04 */
    public static final String VAL_04 = "04";

    /** String Value : 05 */
    public static final String VAL_05 = "05";

    /** Value of INTFC_REC_TP_ID : 1 */
    public static final String VAL_INTFC_REC_TP_ID_HDR = "1";

    /** Value of INTFC_REC_TP_ID : 2 */
    public static final String VAL_INTFC_REC_TP_ID_DTL = "2";

    /** Value of INTFC_REC_TP_ID : 3 */
    public static final String VAL_INTFC_REC_TP_ID_SHIP = "3";

    /** Value of INTFC_REC_TP_ID : 3 */
    public static final String VAL_INTFC_REC_TP_ID_VND = "3";

    /** Value of INTFC_REC_TP_ID : 4 */
    public static final String VAL_INTFC_REC_TP_ID_TXT = "4";

    /** Value of INTFC_REC_TP_ID : 5 */
    public static final String VAL_INTFC_REC_TP_ID_CHRG = "5";

    /** Value of INTFC_REC_TP_ID : 6 */
    public static final String VAL_INTFC_REC_TP_ID_BILL = "6";

    /** Value of INTFC_TP_ID : 01 */
    public static final String VAL_INTFC_TP_ID_01 = "01";

    /** Value of INTFC_TP_ID : 02 */
    public static final String VAL_INTFC_TP_ID_02 = "02";

    /** Value of WMS_PRTY_CD */
    public static final String VAL_WMS_PRTY_CD = "5";

    /** Value of WMS_SO_STS_CD */
    public static final String VAL_WMS_SO_STS_CD = "R";

    /** Value of PRINT_TP_CD : B */
    public static final String VAL_PRINT_TP_CD_B = "B";

    /** Value of CHANGE : CHA */
    public static final String VAL_CHANGE = "CHA";

    /** Value of NEW_ORDER : ORI */
    public static final String VAL_NEW_ORDER = "ORI";

    /** Value of ROSS Return : RB */
    public static final String VAL_ROSS_RETURN = "RB";

    /** Value of ORD_TP_TXT : LR */
    public static final String VAL_ORD_TP_TXT_LR = "LR";

    /** Value of ORD_TP_TXT : LF */
    public static final String VAL_ORD_TP_TXT_LF = "LF";

    /** Value of PRINT_SWTH_CD : P */
    public static final String VAL_PRINT_SWTH_CD_P = "P";

    /** Value of WMS_INV_IND : S */
    public static final String VAL_WMS_INV_IND_S = "S";

    /** Value of TPL_PTNR_TXT : WE */
    public static final String VAL_TPL_PTNR_TXT = "WE";

    /** Value of TPL_ITEM_TP_CD : ZSUB */
    public static final String VAL_TPL_ITEM_TP_CD = "ZSUB";

    /** Stage Rec Status Code 1:New */
    public static final String STAGE_REC_STS_NEW_UPDATE = "2";

    /** Substring end position for CUST_DC_NUM */
    public static final int LG_CUST_DC_NUM = 8;
    
    // QC# 26637 ADD START
    /** Substring end position for TEL_NUM */
    public static final int LG_TEL_NUM = 15;
    // QC# 26637 ADD END

    /** Value of SHIP_TO_CUST_CD */
    public static final String VAL_SHIP_TO_CUST_CD = "999999";

    /** Value of TPL_ORG_TXT */
    public static final String VAL_TPL_ORG_TXT = "0110";

    /** Value of OUTBOUND */
    public static final String VAL_OUTBOUND = "2";

    /** Stage Act Code 1:New */
    public static final String STAGE_ACT_NEW = "1";

    /** Stage Act Code 2:Update */
    public static final String STAGE_ACT_UPDATE = "2";

    /** Substring end position for SO_CRAT_TS */
    public static final int LG_SO_CRAT_TS = 14;

    /** Value 1 */
    public static final String VAL_1 = "1";

    /** Value 2 */
    public static final String VAL_2 = "2";

    /** Value 000000 */
    public static final String VAL_000000 = "000000";

    /** Value of MAX TOT_SHIP_PRC_AMT_NUM */
    public static final BigDecimal VAL_MAX_TOT_SHIP_PRC_AMT_NUM = new BigDecimal("9999999999999.99");

    /** Maximum length CUST_ISS_PO_NUM */
    public static final int MAX_LENGTH_CUST_ISS_PO_NUM = 30;

    /** Maximum length OTBD_SRC_ORD_TP_TXT */
    public static final int MAX_LENGTH_OTBD_SRC_ORD_TP_TXT = 30;

    /** Maximum length OTBD_SRC_ORD_TP_TXT */
    public static final int MAX_LENGTH_SO_CUST_LINE_LOC_NM = 35;

    /** Maximum length OTBD_SRC_ORD_TP_TXT */
    public static final int MAX_LENGTH_CTAC_PTNR_PSN_TEL_NUM = 15;

    /** Value of MAX_QTY */
    public static final BigDecimal VAL_MAX_QTY = new BigDecimal("9999999999999.99");

    /** Size of PSN_NM : 20 */
    public static final int VAL_PSN_NM_SIZE = 20;

    /** Value of MIN_DATE : 19000101 */
    public static final String VAL_MIN_DATE = "19000101";

    /** Value of MAX_DATE : 99991231 */
    public static final String VAL_MAX_DATE = "99991231";

    /** Format of date : yyyyMMdd */
    public static final String VAL_DATE_FORMAT_FROM = "yyyyMMdd";

    /** Format of dateTime : yyyy-MM-dd HH:mm:ss */
    public static final String VAL_DATE_TIME_FORMAT_TO = "yyyy-MM-dd HH:mm:ss";

    /**
     * Format of SYSDATE : yyyyMMddHHmmssSSS
     */
    public static final String FMT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /**
     * Format of SYSDATE : yyyyMMddHHmmss
     */
    public static final String FMT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * TABLE_NAME : SHPG_ORD
     */
    public static final String TBL_SHPG_ORD = "SHPG_ORD";

    /**
     * TABLE_NAME : SHPG_ORD_DTL
     */
    public static final String TBL_SHPG_ORD_DTL = "SHPG_ORD_DTL";

    /**
     * TABLE_NAME : SHPG_ORD_CUST_DTL
     */
    public static final String TBL_SHPG_ORD_CUST_DTL = "SHPG_ORD_CUST_DTL";

    /**
     * TABLE_NAME : SHPG_ORD_MSG
     */
    public static final String TBL_SHPG_ORD_MSG = "SHPG_ORD_MSG";

    /**
     * TABLE_NAME : DS_SITE_SRVY
     */
    public static final String TBL_DS_SITE_SRVY = "DS_SITE_SRVY";

    /**
     * TABLE_NAME : PRCH_REQ
     */
    public static final String TBL_PRCH_REQ = "PRCH_REQ";

    /**
     * TABLE_NAME : PRCH_REQ_DTL
     */
    public static final String TBL_PRCH_REQ_DTL = "PRCH_REQ_DTL";

    /**
     * TABLE_NAME : SHIP_TO_CUST
     */
    public static final String TBL_SHIP_TO_CUST = "SHIP_TO_CUST";

    /**
     * TABLE_NAME : MDSE
     */
    public static final String TBL_MDSE = "MDSE";

    /**
     * TABLE_NAME : SO_SER
     */
    public static final String TBL_SO_SER = "SO_SER";

    /**
     * TABLE_NAME : WMS_INBD_SO_HDR
     */
    public static final String TBL_WMS_INBD_SO_HDR = "WMS_INBD_SO_HDR";

    /**
     * TABLE_NAME : WMS_INBD_SO_DTL
     */
    public static final String TBL_WMS_INBD_SO_DTL = "WMS_INBD_SO_DTL";

    /**
     * TABLE_NAME : WMS_INBD_SO_TEXT
     */
    public static final String TBL_WMS_INBD_SO_TEXT = "WMS_INBD_SO_TEXT";

    /**
     * TABLE_NAME : INBD_PO_HDR
     */
    public static final String TBL_WMS_INBD_PO_HDR = "INBD_PO_HDR";

    /**
     * TABLE_NAME : CMPY_CD_CONV
     */
    public static final String TBL_CMPY_CD_CONV = "CMPY_CD_CONV";

    /**
     * TABLE_NAME : ORD_SRC_CONV
     */
    public static final String TBL_ORD_SRC_CONV = "ORD_SRC_CONV";

    /**
     * TABLE_NAME : STS_STK_CONV
     */
    public static final String TBL_STS_STK_CONV = "STS_STK_CONV";

    /**
     * TABLE_NAME : SCE_ORD_TP
     */
    public static final String TBL_SCE_ORD_TP = "SCE_ORD_TP";

    /**
     * TABLE_NAME : SCE_ORD_TP
     */
    public static final String TBL_RWS_HDR = "RWS_HDR";

    // ///////////////////////////////////////////////////////
    // DB COLUMN
    // ///////////////////////////////////////////////////////
    /**
     * DB COLUMN : GLBL_CMPY_CD
     */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name of INTERFACE_ID */
    public static final String COL_INTERFACE_ID = "INTERFACE_ID";

    /** Column name of TRANSACTION_ID */
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /** Column name of UNIT_ID */
    public static final String COL_UNIT_ID = "UNIT_ID";

    /** Column name of SEGMENT_ID */
    public static final String COL_SEGMENT_ID = "SEGMENT_ID";

    /** Column name of SEQ_NUMBER */
    public static final String COL_SEQ_NUMBER = "SEQ_NUMBER";

    /**
     * DB COLUMN : SO_NUM
     */
    public static final String COL_SO_NUM = "SO_NUM";

    /**
     * DB COLUMN : SO_SLP_NUM
     */
    public static final String COL_SO_SLP_NUM = "SO_SLP_NUM";

    /**
     * DB COLUMN : TRX_HDR_NUM
     */
    public static final String COL_TRX_HDR_NUM = "TRX_HDR_NUM";

    /**
     * DB COLUMN : TRX_LINE_NUM
     */
    public static final String COL_TRX_LINE_NUM = "TRX_LINE_NUM";

    /**
     * DB COLUMN : TRX_LINE_SUB_NUM
     */
    public static final String COL_TRX_LINE_SUB_NUM = "TRX_LINE_SUB_NUM";

    /**
     * DB COLUMN : PLANT_CD
     */
    public static final String COL_PLANT_CD = "PLANT_CD";

    /**
     * DB COLUMN : RWS_NUM
     */
    public static final String COL_RWS_NUM = "RWS_NUM";

    /**
     * DB COLUMN : RWS_LINE_NUM
     */
    public static final String COL_RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";

    /**
     * DB COLUMN : RWS_LINE_NUM
     */
    public static final String COL_RWS_LINE_NUM = "RWS_LINE_NUM";

    /**
     * DB COLUMN : ALLOC_REQ_FLG
     */
    public static final String COL_ALLOC_REQ_FLG = "ALLOC_REQ_FLG";

    /**
     * DB COLUMN : CARR_CD
     */
    public static final String COL_CARR_CD = "CARR_CD";

    /**
     * DB COLUMN : CARR_TP_NM
     */
    public static final String COL_CARR_TP_NM = "CARR_TP_NM";

    /**
     * DB COLUMN : CTAC_PTNR_PSN_NM
     */
    public static final String COL_CTAC_PTNR_PSN_NM = "CTAC_PTNR_PSN_NM";

    /**
     * DB COLUMN : CTAC_PTNR_PSN_TEL_NUM
     */
    public static final String COL_CTAC_PTNR_PSN_TEL_NUM = "CTAC_PTNR_PSN_TEL_NUM";

    /**
     * DB COLUMN : CTRY_CD
     */
    public static final String COL_CTRY_CD = "CTRY_CD";

    /**
     * DB COLUMN : CTY_ADDR
     */
    public static final String COL_CTY_ADDR = "CTY_ADDR";

    /**
     * DB COLUMN : DELY_ADDL_CMNT_TXT
     */
    public static final String COL_DELY_ADDL_CMNT_TXT = "DELY_ADDL_CMNT_TXT";

    /**
     * DB COLUMN : DELY_REQ_FLG
     */
    public static final String COL_DELY_REQ_FLG = "DELY_REQ_FLG";

    /**
     * DB COLUMN : DELY_DT
     */
    public static final String COL_DELY_DT = "DELY_DT";

    /**
     * DB COLUMN : PSD_DT
     */
    public static final String COL_PSD_DT = "PSD_DT";

    /**
     * DB COLUMN : PSD_DT
     */
    public static final String COL_PSD_DT_TM = "PSD_DT_TM";

    /**
     * DB COLUMN : FIRST_LINE_ADDR
     */
    public static final String COL_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /**
     * DB COLUMN : FRTH_LINE_ADDR
     */
    public static final String COL_FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /**
     * DB COLUMN : LOC_NM
     */
    public static final String COL_LOC_NM = "LOC_NM";

    /**
     * DB COLUMN : MDSE_CD
     */
    public static final String COL_MDSE_CD = "MDSE_CD";

    /**
     * DB COLUMN : SER_NUM
     */
    public static final String COL_SER_NUM = "SER_NUM";

    /**
     * DB COLUMN : OPEN_STS_FLG
     */
    public static final String COL_OPEN_STS_FLG = "OPEN_STS_FLG";

    /**
     * DB COLUMN : ORD_QTY
     */
    public static final String COL_ORD_QTY = "ORD_QTY";

    /**
     * DB COLUMN : POST_CD
     */
    public static final String COL_POST_CD = "POST_CD";

    /**
     * DB COLUMN : PRCH_REQ_LINE_NUM
     */
    public static final String COL_PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /**
     * DB COLUMN : PRCH_REQ_LINE_SUB_NUM
     */
    public static final String COL_PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /**
     * DB COLUMN : PRCH_REQ_NUM
     */
    public static final String COL_PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /**
     * DB COLUMN : PRCH_REQ_REL_STS_CD
     */
    public static final String COL_PRCH_REQ_REL_STS_CD = "PRCH_REQ_REL_STS_CD";

    /**
     * DB COLUMN : PROC_DATE
     */
    public static final String COL_PROC_DATE = "PROC_DATE";

    /**
     * DB COLUMN : SCD_LINE_ADDR
     */
    public static final String COL_SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /**
     * DB COLUMN : SHIP_TO_CUST_CD
     */
    public static final String COL_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /**
     * DB COLUMN : SHIP_TO_CTRY_CD
     */
    public static final String COL_SHIP_TO_CTRY_CD = "SHIP_TO_CTRY_CD";

    /**
     * DB COLUMN : SHIP_TO_CTY_ADDR
     */
    public static final String COL_SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";

    /**
     * DB COLUMN : SHIP_TO_FIRST_LINE_ADDR
     */
    public static final String COL_SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";

    /**
     * DB COLUMN : SHIP_TO_FRTH_LINE_ADDR
     */
    public static final String COL_SHIP_TO_FRTH_LINE_ADDR = "SHIP_TO_FRTH_LINE_ADDR";

    /**
     * DB COLUMN : SHIP_TO_LOC_NM
     */
    public static final String COL_SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /**
     * DB COLUMN : SHIP_TO_POST_CD
     */
    public static final String COL_SHIP_TO_POST_CD = "SHIP_TO_POST_CD";

    /**
     * DB COLUMN : SHIP_TO_SCD_LINE_ADDR
     */
    public static final String COL_SHIP_TO_SCD_LINE_ADDR = "SHIP_TO_SCD_LINE_ADDR";

    /**
     * DB COLUMN : SHIP_TO_ST_CD
     */
    public static final String COL_SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /**
     * DB COLUMN : SHIP_TO_THIRD_LINE_ADDR
     */
    public static final String COL_SHIP_TO_THIRD_LINE_ADDR = "SHIP_TO_THIRD_LINE_ADDR";

    /**
     * DB COLUMN : SHPG_QTY
     */
    public static final String COL_SHPG_QTY = "SHPG_QTY";

    /**
     * DB COLUMN : SHPG_SVC_LVL_CD
     */
    public static final String COL_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /**
     * DB COLUMN : SO_CUST_DATA_TP_CD
     */
    public static final String COL_SO_CUST_DATA_TP_CD = "SO_CUST_DATA_TP_CD";

    /**
     * DB COLUMN : SO_CUST_LINE_ADDR_01
     */
    public static final String COL_SO_CUST_LINE_ADDR_01 = "SO_CUST_LINE_ADDR_01";

    /**
     * DB COLUMN : SO_CUST_LINE_ADDR_02
     */
    public static final String COL_SO_CUST_LINE_ADDR_02 = "SO_CUST_LINE_ADDR_02";

    /**
     * DB COLUMN : SO_CUST_LINE_ADDR_03
     */
    public static final String COL_SO_CUST_LINE_ADDR_03 = "SO_CUST_LINE_ADDR_03";

    /**
     * DB COLUMN : SO_CUST_LINE_ADDR_04
     */
    public static final String COL_SO_CUST_LINE_ADDR_04 = "SO_CUST_LINE_ADDR_04";

    /**
     * DB COLUMN : SO_CUST_LINE_LOC_NM_01
     */
    public static final String COL_SO_CUST_LINE_LOC_NM_01 = "SO_CUST_LINE_LOC_NM_01";

    /**
     * DB COLUMN : SO_CUST_LINE_LOC_NM_02
     */
    public static final String COL_SO_CUST_LINE_LOC_NM_02 = "SO_CUST_LINE_LOC_NM_02";

    /**
     * DB COLUMN : SO_MSG_DESC_TXT
     */
    public static final String COL_SO_MSG_DESC_TXT = "SO_MSG_DESC_TXT";

    /**
     * DB COLUMN : SO_MSG_TP_CD
     */
    public static final String COL_SO_MSG_TP_CD = "SO_MSG_TP_CD";

    /**
     * DB COLUMN : ST_CD
     */
    public static final String COL_ST_CD = "ST_CD";

    /**
     * DB COLUMN : THIRD_LINE_ADDR
     */
    public static final String COL_THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /**
     * DB COLUMN : TPL_LOC_NM
     */
    public static final String COL_TPL_LOC_NM = "TPL_LOC_NM";

    /**
     * DB COLUMN : WH_OWNR_CD
     */
    public static final String COL_WH_OWNR_CD = "WH_OWNR_CD";

    /**
     * DB COLUMN : WH_SYS_TP
     */
    public static final String COL_WH_SYS_TP = "WH_SYS_TP";

    /**
     * DB COLUMN : WMS_DROP_FLG
     */
    public static final String COL_WMS_DROP_FLG = "WMS_DROP_FLG";

    /**
     * DB COLUMN : WMS_DROP_RQST_FLG
     */
    public static final String COL_WMS_DROP_RQST_FLG = "WMS_DROP_RQST_FLG";

    /**
     * DB COLUMN : SRC_RTL_WH_CD
     */
    public static final String COL_SRC_RTL_WH_CD = "SRC_RTL_WH_CD";

    /**
     * DB COLUMN : TPL_CARR_CD
     */
    public static final String COL_TPL_CARR_CD = "TPL_CARR_CD";

    /**
     * DB COLUMN : TPL_CARR_NM
     */
    public static final String COL_TPL_CARR_NM = "TPL_CARR_NM";

    /**
     * DB COLUMN : TPL_SVC_LVL_CD
     */
    public static final String COL_TPL_SVC_LVL_CD = "TPL_SVC_LVL_CD";

    /**
     * DB COLUMN : SHPG_PNT_CD
     */
    public static final String COL_SHPG_PNT_CD = "SHPG_PNT_CD";

    /**
     * DB COLUMN : TPL_CTRL_ID
     */
    public static final String COL_TPL_CTRL_ID = "TPL_CTRL_ID";

    /**
     * DB COLUMN : RWS_REF_NUM
     */
    public static final String COL_RWS_REF_NUM = "RWS_REF_NUM";

    /**
     * DB COLUMN : FROM_LOC_CD
     */
    public static final String COL_FROM_LOC_CD = "FROM_LOC_CD";

    /**
     * DB COLUMN : WH_IN_ETA_DT
     */
    public static final String COL_WH_IN_ETA_DT = "WH_IN_ETA_DT";

    /**
     * Add QC#51496. DB COLUMN : WH_IN_ETA_TM
     */
    public static final String COL_WH_IN_ETA_TM = "WH_IN_ETA_TM";

    /**
     * DB COLUMN : IMPT_INV_VESL_NM
     */
    public static final String COL_IMPT_INV_VESL_NM = "IMPT_INV_VESL_NM";

    /**
     * DB COLUMN : IMPT_INV_BOL_NUM
     */
    public static final String COL_IMPT_INV_BOL_NUM = "IMPT_INV_BOL_NUM";

    /** Column name of SCE_ORD_TP_CD */
    public static final String COL_SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /** Column name of HIT_BY_WMS_DROP_FLG */
    public static final String COL_HIT_BY_WMS_DROP_FLG = "HIT_BY_WMS_DROP_FLG";

    /** Column name of HIT_BY_DELY_REQ_FLG */
    public static final String COL_HIT_BY_DELY_REQ_FLG = "HIT_BY_DELY_REQ_FLG";

    /** Column name of HIT_BY_WMS_DROP_STS_CD */
    public static final String COL_HIT_BY_WMS_DROP_STS_CD = "HIT_BY_WMS_DROP_STS_CD";

    /** Column name of HIT_BY_PICK_UP_RQST_FLG */
    public static final String COL_HIT_BY_PICK_UP_RQST_FLG = "HIT_BY_PICK_UP_RQST_FLG";

    /** DB Column: WH_CD */
    public static final String COL_WH_CD = "WH_CD";

    /** DB Column: S80_CMPY_CD */
    public static final String COL_S80_CMPY_CD = "S80_CMPY_CD";

    /** DB Column: S80_ORD_TP_CD */
    public static final String COL_S80_ORD_TP_CD = "S80_ORD_TP_CD";

    /** DB Column: S80_TRX_CD */
    public static final String COL_S80_TRX_CD = "S80_TRX_CD";

    /** DB Column: S80_ORD_SRC_CD */
    public static final String COL_S80_ORD_SRC_CD = "S80_ORD_SRC_CD";

    /** DB Column: S80_STK_STS_CD */
    public static final String COL_S80_STK_STS_CD = "S80_STK_STS_CD";

    /** DB Column: S80_SHPG_TERM_CD */
    public static final String COL_S80_SHPG_TERM_CD = "S80_SHPG_TERM_CD";

    /** DB Column: S80_SHPG_TERM_NM */
    public static final String COL_S80_SHPG_TERM_NM = "S80_SHPG_TERM_NM";

    /** DB Column: TRX_SRC_TP_CD */
    public static final String COL_TRX_SRC_TP_CD = "TRX_SRC_TP_CD";

    /** DB Column: PICK_TKT_NUM */
    public static final String COL_PICK_TKT_NUM = "PICK_TKT_NUM";

    /** DB Column: EXPT_SHPG_METH_CD */
    public static final String COL_EXPT_SHPG_METH_CD = "EXPT_SHPG_METH_CD";

    /** DB Column: SHPG_METH_NM */
    public static final String COL_SHPG_METH_NM = "SHPG_METH_NM";

    /** DB Column: SO_CRAT_TS */
    public static final String COL_SO_CRAT_TS = "SO_CRAT_TS";

    /** DB Column: RDD_DT */
    public static final String COL_RDD_DT = "RDD_DT";

    /** DB Column: RDD_DT */
    public static final String COL_RDD_DT_TM = "RDD_DT_TM";

    /** DB Column: DNLD_TM_TS */
    public static final String COL_DNLD_TM_TS = "DNLD_TM_TS";

    /** DB Column: CUST_ISS_PO_NUM */
    public static final String COL_CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** DB Column: SELL_TO_CUST_CD */
    public static final String COL_SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** DB Column: BILL_TO_CUST_CD */
    public static final String COL_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB Column: DROP_SHIP_FLG */
    public static final String COL_DROP_SHIP_FLG = "DROP_SHIP_FLG";

    /** DB Column: PRINT_SCC_LB_FLG */
    public static final String COL_PRINT_SCC_LB_FLG = "PRINT_SCC_LB_FLG";

    /** DB Column: PRINT_UCC_LB_FLG */
    public static final String COL_PRINT_UCC_LB_FLG = "PRINT_UCC_LB_FLG";

    /** DB Column: MIX_PLT_ALLW_FLG */
    public static final String COL_MIX_PLT_ALLW_FLG = "MIX_PLT_ALLW_FLG";

    /** DB Column: PRINT_PLT_UCC_LB_FLG */
    public static final String COL_PRINT_PLT_UCC_LB_FLG = "PRINT_PLT_UCC_LB_FLG";

    /** DB Column: PRINT_NON_ASN_UCC_LB_FLG */
    public static final String COL_PRINT_NON_ASN_UCC_LB_FLG = "PRINT_NON_ASN_UCC_LB_FLG";

    /** DB Column: ASN_REQ_FLG */
    public static final String COL_ASN_REQ_FLG = "ASN_REQ_FLG";

    /** DB Column: UCC_NUM_CD */
    public static final String COL_UCC_NUM_CD = "UCC_NUM_CD";

    /** DB Column: EDI_TP_CD */
    public static final String COL_EDI_TP_CD = "EDI_TP_CD";

    /** DB Column: CUST_STORE_NUM */
    public static final String COL_CUST_STORE_NUM = "CUST_STORE_NUM";

    /** DB Column: SO_DEPT_NUM */
    public static final String COL_SO_DEPT_NUM = "SO_DEPT_NUM";

    /** DB Column: TOT_SHIP_AMT */
    public static final String COL_TOT_SHIP_AMT = "TOT_SHIP_AMT";

    /** DB Column: RTRN_LB_CD */
    public static final String COL_RTRN_LB_CD = "RTRN_LB_CD";

    /** DB Column: SO_CONFIG_FLG */
    public static final String COL_SO_CONFIG_FLG = "SO_CONFIG_FLG";

    /** DB Column: CARR_ACCT_NUM */
    public static final String COL_CARR_ACCT_NUM = "CARR_ACCT_NUM";

    /** DB Column: SVC_CONFIG_MSTR_PK */
    public static final String COL_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** DB Column: WMS_RTE_PATH_CD */
    public static final String COL_WMS_RTE_PATH_CD = "WMS_RTE_PATH_CD";

    /** DB Column: RTL_WH_CD */
    public static final String COL_RTL_WH_CD = "RTL_WH_CD";

    /** DB Column: RTL_SWH_CD */
    public static final String COL_RTL_SWH_CD = "RTL_SWH_CD";

    /** DB Column: SRC_ORD_NUM */
    public static final String COL_SRC_ORD_NUM = "SRC_ORD_NUM";

    /** DB Column: SR_PSN_NM */
    public static final String COL_SR_PSN_NM = "SR_PSN_NM";

    /** DB Column: SCHD_DELY_DT */
    public static final String COL_SCHD_DELY_DT = "SCHD_DELY_DT";

    /** DB Column: PRE_ISTL_SHOP_RQST_FLG */
    public static final String COL_PRE_ISTL_SHOP_RQST_FLG = "PRE_ISTL_SHOP_RQST_FLG";

    /** DB Column: OTBD_SRC_ORD_TP_TXT */
    public static final String COL_OTBD_SRC_ORD_TP_TXT = "OTBD_SRC_ORD_TP_TXT";

    /** DB Column: CPO_PSN_NM */
    public static final String COL_CPO_PSN_NM = "CPO_PSN_NM";

    /** DB Column: PO_PSN_NM */
    public static final String COL_PO_PSN_NM = "PO_PSN_NM";

    /** DB Column: SCE_ORD_TP_NM */
    public static final String COL_SCE_ORD_TP_NM = "SCE_ORD_TP_NM";

    /** DB Column: LOC_GRP_CD */
    public static final String COL_LOC_GRP_CD = "LOC_GRP_CD";

    /** DB Column: CUST_MDSE_CD */
    public static final String COL_CUST_MDSE_CD = "CUST_MDSE_CD";

    /** DB Column: RQST_ORD_QTY */
    public static final String COL_RQST_ORD_QTY = "RQST_ORD_QTY";

    /** DB Column: SHPG_BAL_QTY */
    public static final String COL_SHPG_BAL_QTY = "SHPG_BAL_QTY";

    /** DB Column: UNIT_PRC_AMT */
    public static final String COL_UNIT_PRC_AMT = "UNIT_PRC_AMT";

    /** DB Column: DISC_PRC_AMT */
    public static final String COL_DISC_PRC_AMT = "DISC_PRC_AMT";

    /** DB Column: DISC_UNIT_PRC_AMT */
    public static final String COL_DISC_UNIT_PRC_AMT = "DISC_UNIT_PRC_AMT";

    /** DB Column: SER_NUM_TAKE_FLG */
    public static final String COL_SER_NUM_TAKE_FLG = "SER_NUM_TAKE_FLG";

    /** DB Column: SET_MDSE_CD */
    public static final String COL_SET_MDSE_CD = "SET_MDSE_CD";

    /** DB Column: SET_MDSE_NM */
    public static final String COL_SET_MDSE_NM = "SET_MDSE_NM";

    /** DB Column: SET_SHPG_QTY */
    public static final String COL_SET_SHPG_QTY = "SET_SHPG_QTY";

    /** DB Column: IN_EACH_QTY */
    public static final String COL_IN_EACH_QTY = "IN_EACH_QTY";

    /** DB Column: TOT_SHPG_WT */
    public static final String COL_TOT_SHPG_WT = "TOT_SHPG_WT";

    /** DB Column: TOT_SHPG_VOL */
    public static final String COL_TOT_SHPG_VOL = "TOT_SHPG_VOL";

    /** DB Column: BAT_NUM_TAKE_FLG */
    public static final String COL_BAT_NUM_TAKE_FLG = "BAT_NUM_TAKE_FLG";

    /** DB Column: CONFIG_ITEM_FLG */
    public static final String COL_CONFIG_ITEM_FLG = "CONFIG_ITEM_FLG";

    /** DB Column: PICK_SVC_CONFIG_MSTR_PK */
    public static final String COL_PICK_SVC_CONFIG_MSTR_PK = "PICK_SVC_CONFIG_MSTR_PK";

    /** DB Column: BACK_ORD_IMPCT_TP_CD */
    public static final String COL_BACK_ORD_IMPCT_TP_CD = "BACK_ORD_IMPCT_TP_CD";

    /** DB Column: RMV_CONFIG_FLG */
    public static final String COL_RMV_CONFIG_FLG = "RMV_CONFIG_FLG";

    /** DB Column: RTRN_REQ_PRT_FLG */
    public static final String COL_RTRN_REQ_PRT_FLG = "RTRN_REQ_PRT_FLG";

    /** DB Column: MDSE_ITEM_TP_CD */
    public static final String COL_MDSE_ITEM_TP_CD = "MDSE_ITEM_TP_CD";

    /** DB Column: RWS_MSG_TXT */
    public static final String COL_RWS_MSG_TXT = "RWS_MSG_TXT";

    /** DB Column: RCV_PNT_CD */
    public static final String COL_RCV_PNT_CD = "RCV_PNT_CD";

    /** DB Column: ORD_TP_TXT */
    public static final String COL_ORD_TP_TXT = "ORD_TP_TXT";

    /** DB Column: FROM_LOC_PSN_NM */
    public static final String COL_FROM_LOC_PSN_NM = "FROM_LOC_PSN_NM";

    /** DB Column: FROM_LOC_TEL_NUM */
    public static final String COL_FROM_LOC_TEL_NUM = "FROM_LOC_TEL_NUM";

    /** DB Column: FROM_LOC_NM_01 */
    public static final String COL_FROM_LOC_NM_01 = "FROM_LOC_NM_01";

    /** DB Column: FROM_LOC_NM_02 */
    public static final String COL_FROM_LOC_NM_02 = "FROM_LOC_NM_02";

    /** DB Column: FROM_LOC_ADDR_01 */
    public static final String COL_FROM_LOC_ADDR_01 = "FROM_LOC_ADDR_01";

    /** DB Column: FROM_LOC_ADDR_02 */
    public static final String COL_FROM_LOC_ADDR_02 = "FROM_LOC_ADDR_02";

    /** DB Column: FROM_LOC_ADDR_03 */
    public static final String COL_FROM_LOC_ADDR_03 = "FROM_LOC_ADDR_03";

    /** DB Column: FROM_LOC_ADDR_04 */
    public static final String COL_FROM_LOC_ADDR_04 = "FROM_LOC_ADDR_04";

    /** DB Column: FROM_LOC_POST_CD */
    public static final String COL_FROM_LOC_POST_CD = "FROM_LOC_POST_CD";

    /** DB Column: FROM_LOC_CTY_ADDR */
    public static final String COL_FROM_LOC_CTY_ADDR = "FROM_LOC_CTY_ADDR";

    /** DB Column: FROM_LOC_CTRY_CD */
    public static final String COL_FROM_LOC_CTRY_CD = "FROM_LOC_CTRY_CD";

    /** DB Column: FROM_LOC_ST_CD */
    public static final String COL_FROM_LOC_ST_CD = "FROM_LOC_ST_CD";

    /** DB Column: RWS_QTY */
    public static final String COL_RWS_QTY = "RWS_QTY";

    /** DB Column: TRX_ORD_NUM */
    public static final String COL_TRX_ORD_NUM = "TRX_ORD_NUM";

    /** DB Column: IMPT_INV_NUM */
    public static final String COL_IMPT_INV_NUM = "IMPT_INV_NUM";

    /** DB Column: IMPT_INV_DO_NUM */
    public static final String COL_IMPT_INV_DO_NUM = "IMPT_INV_DO_NUM";

    /** DB Column: OUT_PACK_FROM_CSE_NUM */
    public static final String COL_OUT_PACK_FROM_CSE_NUM = "OUT_PACK_FROM_CSE_NUM";

    /** DB Column: OUT_PACK_TO_CSE_NUM */
    public static final String COL_OUT_PACK_TO_CSE_NUM = "OUT_PACK_TO_CSE_NUM";

    /** DB Column: THIRD_PTY_DSP_TP_CD */
    public static final String COL_THIRD_PTY_DSP_TP_CD = "THIRD_PTY_DSP_TP_CD";

    /** DB Column: SER_APVL_REQ_FLG */
    public static final String COL_SER_APVL_REQ_FLG = "SER_APVL_REQ_FLG";

    /** DB Column : TPL_FROM_PTNR_ID */
    public static final String COL_TPL_FROM_PTNR_ID = "TPL_FROM_PTNR_ID";

    /** DB Column : TPL_TO_PTNR_ID */
    public static final String COL_TPL_TO_PTNR_ID = "TPL_TO_PTNR_ID";

    /** DB Column : ORD_DT_TM_TS_TXT */
    public static final String COL_ORD_DT_TM_TS_TXT = "ORD_DT_TM_TS_TXT";

    /** DB Column : ORD_ID_TXT */
    public static final String COL_ORD_ID_TXT = "ORD_ID_TXT";

    /** DB Column : TPL_REF_NM */
    public static final String COL_TPL_REF_NM = "TPL_REF_NM";

    /** DB Column : TPL_LOC_TXT */
    public static final String COL_TPL_LOC_TXT = "TPL_LOC_TXT";

    /** DB Column : TPL_COND_TXT */
    public static final String COL_TPL_COND_TXT = "TPL_COND_TXT";

    /** DB Column : ORD_TP_TXT */
    //public static final String COL_ORD_TP_TXT = "ORD_TP_TXT";

    /** DB Column : TPL_ORG_TXT */
    public static final String COL_TPL_ORG_TXT = "TPL_ORG_TXT";

    /** DB Column : TPL_CTRL_ID */
    //public static final String COL_TPL_CTRL_ID = "TPL_CTRL_ID";

    /** DB Column : CARR_CD */
    //public static final String COL_CARR_CD = "CARR_CD";

    /** DB Column : REQ_DT_TM_TS_TXT */
    public static final String COL_REQ_DT_TM_TS_TXT = "REQ_DT_TM_TS_TXT";

    /** DB Column : RQST_SHIP_DT_TM_TS_TXT */
    public static final String COL_RQST_SHIP_DT_TM_TS_TXT = "RQST_SHIP_DT_TM_TS_TXT";

    /** DB Column : SHIP_CTAC_NM_TXT */
    public static final String COL_SHIP_CTAC_NM_TXT = "SHIP_CTAC_NM_TXT";

    /** DB Column : SHIP_CTAC_PHO_NUM */
    public static final String COL_SHIP_CTAC_PHO_NUM = "SHIP_CTAC_PHO_NUM";

    /** DB Column : TPL_PTNR_TXT */
    public static final String COL_TPL_PTNR_TXT = "TPL_PTNR_TXT";

    /** DB Column : SHIP_CUST_TXT */
    public static final String COL_SHIP_CUST_TXT = "SHIP_CUST_TXT";

    /** DB Column : SHIP_FIRST_LINE_NM */
    public static final String COL_SHIP_FIRST_LINE_NM = "SHIP_FIRST_LINE_NM";

    /** DB Column : SHIP_SCD_LINE_NM */
    public static final String COL_SHIP_SCD_LINE_NM = "SHIP_SCD_LINE_NM";

    /** DB Column : SHIP_THIRD_LINE_NM */
    public static final String COL_SHIP_THIRD_LINE_NM = "SHIP_THIRD_LINE_NM";

    /** DB Column : SHIP_FRTH_LINE_NM */
    public static final String COL_SHIP_FRTH_LINE_NM = "SHIP_FRTH_LINE_NM";

    /** DB Column : SHIP_FIRST_LINE_ADDR_TXT */
    public static final String COL_SHIP_FIRST_LINE_ADDR_TXT = "SHIP_FIRST_LINE_ADDR_TXT";

    /** DB Column : SHIP_SCD_LINE_ADDR_TXT */
    public static final String COL_SHIP_SCD_LINE_ADDR_TXT = "SHIP_SCD_LINE_ADDR_TXT";

    /** DB Column : SHIP_ZIP_OR_POST_CD_TXT */
    public static final String COL_SHIP_ZIP_OR_POST_CD_TXT = "SHIP_ZIP_OR_POST_CD_TXT";

    /** DB Column : SHIP_CTY_TXT */
    public static final String COL_SHIP_CTY_TXT = "SHIP_CTY_TXT";

    /** DB Column : SHIP_CTRY_TXT */
    public static final String COL_SHIP_CTRY_TXT = "SHIP_CTRY_TXT";

    /** DB Column : SHIP_ST_OR_PROV_TXT */
    public static final String COL_SHIP_ST_OR_PROV_TXT = "SHIP_ST_OR_PROV_TXT";

    /** DB Column : SHIP_PHO_NUM_TXT */
    public static final String COL_SHIP_PHO_NUM_TXT = "SHIP_PHO_NUM_TXT";

    /** DB Column : SO_MSG_DESC_TXT */
    //public static final String COL_SO_MSG_DESC_TXT = "SO_MSG_DESC_TXT";

    /** DB Column : ITEM_CD_TXT */
    public static final String COL_ITEM_CD_TXT = "ITEM_CD_TXT";

    /** DB Column : QTY_ORD_TXT */
    public static final String COL_QTY_ORD_TXT = "QTY_ORD_TXT";

    /** DB Column : ORD_LINE_TXT */
    public static final String COL_ORD_LINE_TXT = "ORD_LINE_TXT";

    /** DB Column : TPL_ITEM_TP_CD */
    public static final String COL_TPL_ITEM_TP_CD = "TPL_ITEM_TP_CD";

    /** DB Column : CUST_PO_TP_TXT */
    public static final String COL_CUST_PO_TP_TXT = "CUST_PO_TP_TXT";

    /** DB Column : CUST_PO_LINE_TXT */
    public static final String COL_CUST_PO_LINE_TXT = "CUST_PO_LINE_TXT";

    /** DB Column : TPL_LOC_TXT */
    //public static final String COL_TPL_LOC_TXT = "TPL_LOC_TXT";

    /** DB Column : TPL_SWH_CD */
    public static final String COL_TPL_SWH_CD = "TPL_SWH_CD";

    /** DB Column : TPL_SITE_SRVY_TXT */
    public static final String COL_TPL_SITE_SRVY_TXT = "TPL_SITE_SRVY_TXT";

    /** DB Column : TPL_SER_NUM */
    public static final String COL_TPL_SER_NUM = "TPL_SER_NUM";

    /** DB Column : RTRN_TO_CTAC_NUM */
    public static final String COL_RTRN_TO_CTAC_NUM = "RTRN_TO_CTAC_NUM";

    /** DB Column : CTAC_PSN_TP_CD */
    public static final String COL_CTAC_PSN_TP_CD = "CTAC_PSN_TP_CD";
    /** DB Column : CTAC_PSN_TEL_NUM */
    public static final String COL_CTAC_PSN_TEL_NUM = "CTAC_PSN_TEL_NUM";
    /** DB Column : CTAC_PSN_NM */
    public static final String COL_CTAC_PSN_NM = "CTAC_PSN_NM";

    // START 2021/06/15 [QC#56495, ADD]
    /** DB Column : ITRL_ITEM_FLG */
    public static final String COL_ITRL_ITEM_FLG = "ITRL_ITEM_FLG";
    // END 2021/06/15 [QC#56495, ADD]
    // ///////////////////////////////////////////////////////
    // Error Message Parameter
    // ///////////////////////////////////////////////////////
    /** Message string : Global Company Code */
    public static final String MSG_STR_COMP_CODE = "Global Company Code";

    /** Message string : Global Company Code */
    public static final String MSG_STR_INTERFACE_ID = "Interface ID";

    /** Message string : Commit Count */
    public static final String MSG_STR_PARAM_01 = "Commit Count(VAR_USER1)";

    // ///////////////////////////////////////////////////////
    // Error Mail Configuration Key
    // ///////////////////////////////////////////////////////
    /** */
    public static final String KEY_MESSAGE_ID = "KEY_MESSAGE_ID";

    /** */
    public static final String KEY_MESSAGE = "KEY_MESSAGE";

    /** */
    public static final String MAIL_GROUP_ID = BUSINESS_ID;

    /** */
    public static final String MAIL_TEMPLATE_ID = MAIL_GROUP_ID + "M001";

    /** */
    public static final String MAIL_KEY_FROM = "From";

    /** */
    public static final String MAIL_KEY_TO = "To";

    /** */
    public static final String MAIL_KEY_BATCH_ID = "batchId";

    /** */
    public static final String MAIL_KEY_ERR_DATE = "errDate";

    /** */
    public static final String MAIL_KEY_MESSAGE = "message";

    // ///////////////////////////////////////////////////////
    // Other values
    // ///////////////////////////////////////////////////////
    /** MAX TEXT SIZE */
    public static final int VAL_MAX_TEXT_SIZE = 400;
    
    // QC#18460
    /** Format of date : yyyyMMddHHmm */
    public static final String VAL_DATE_FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmm";

 // QC#23045
    /**  */
    public static final String LF_PAD_CHAR = "0";

    /**  */
    public static final int TRAN_ID_LENGTH = 30;

    /**  */
    public static final int SEQ_ID_LENGTH = 10;

    /**  */
    public static final int SER_LINE_NUM_LENGTH = 3;

    /**  */
    public static final int LINENUM_MIN = 1;

    /**  */
    public static final int RWS_LINE_LENGTH = 3;

    /**  */
    public static final int LINENUM_MAX = 999;

    /** */
    public static final String CRLF = "\r\n";

    /** */
    public static final String BLANKS = "    ";

    /** */
    public static final String NLZM1007E = "NLZM1007E";

    /** Already stocked in.[@] */
    public static final String NLZM1051E = "NLZM1051E";

    /** Actual stocked-in quantity exceeded the instruction.[@] */
    public static final String NLZM1052E = "NLZM1052E";

    /**
     * The Table Update process failed. The data does not exist. TBLE
     * ID : [@], Field Name: [@], Key Information: [@]
     */
    public static final String NLAM1134E = "NLAM1134E";

    /** Source Type : WMS */
    public static final String SRC_TP_CD_WMS = "W";

    // QC#30006 Add Start
    /** Contact Person Name Length */
    public static final int CTAC_PSN_NM_LENGTH = 25;
    // QC#30006 Add End
}
