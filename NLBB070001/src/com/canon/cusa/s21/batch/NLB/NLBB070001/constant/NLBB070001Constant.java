/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB070001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Batch Program Constant Class for SO (EDI 940) Download to APEX.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   CITS            T.Wada          Create 
 * 12/20/2017   CITS            S.Katsuma       Update          QC#22592
 * 01/11/2018   CITS            T.Wada          Update          QC#18460
 * 01/24/2018   CITS            K.Ogino         Update          QC#23045
 * 04/22/2019   CITS            K.Ogino         Update          QC#31196
 * 08/06/20191  CITS            R.Shimamoto     Update          QC#52369
 *</pre>
 */
public class NLBB070001Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NLBB0700";

    /** Output Log Program ID */
    public static final String PROGRAM_ID = "NLBB070001:";

    /** Output table : NLBI1110_01 for SO/RWS Header */
    public static final String TBL_NLBI1210_01 = "NLBI1210_01";

    /** Output table : NLBI1110_03 for SO/RWS Text */
    public static final String TBL_NLBI1210_03 = "NLBI1210_03";

    /** Output table : NLBI1110_02 for SO/RWS Detail */
    public static final String TBL_NLBI1210_02 = "NLBI1210_02";

    /** Output table : NLBI1110_05 for SO/RWS Serial */
    public static final String TBL_NLBI1210_05 = "NLBI1210_05";

    /** Output table : NLBI1110_04 for SO/RWS Site Survey */
    public static final String TBL_NLBI1210_04 = "NLBI1210_04";

    /** Output table : WMS_INBD_SO_HDR for SO(APEX) Header */
    public static final String TBL_WMS_INBD_SO_HDR = "WMS_INBD_SO_HDR";

    /** Output table : WMS_INBD_SO_DTL for SO(APEX) Detail */
    public static final String TBL_WMS_INBD_SO_DTL = "WMS_INBD_SO_DTL";

    /** Output table : WMS_INBD_SO_TEXT for SO(APEX) Text */
    public static final String TBL_WMS_INBD_SO_TEXT = "WMS_INBD_SO_TEXT";

    /** Output table : WMS_INBD_SO_SHIP_TO for SO(APEX) Ship To Cust */
    public static final String TBL_WMS_INBD_SO_SHIP_TO = "WMS_INBD_SO_SHIP_TO";

    /** Output table : WMS_INBD_SO_CHRG_TO for SO(APEX) Charge To Cust */
    public static final String TBL_WMS_INBD_SO_CHRG_TO = "WMS_INBD_SO_CHRG_TO";

    /** Output table : WMS_INBD_SO_BILL_TO for SO(APEX) Bill To Cust */
    public static final String TBL_WMS_INBD_SO_BILL_TO = "WMS_INBD_SO_BILL_TO";

    /** Output table : WMS_INBD_SO_CHRG_TO for RWS(APEX) Header */
    public static final String TBL_WMS_INBD_PO_HDR = "WMS_INBD_PO_HDR";

    /** Output table : WMS_INBD_PO_DTL for RWS(APEX) Detail */
    public static final String TBL_WMS_INBD_PO_DTL = "WMS_INBD_PO_DTL";

    /** Output table : WMS_INBD_PO_VND for RWS(APEX) Detail */
    public static final String TBL_WMS_INBD_PO_VNDL = "WMS_INBD_PO_VND";
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

    /** VAR_CHAR_CONCT_NM_TG_ORDER */
    public static final String VAR_CHAR_CONCT_NM_TG_ORDER = "NLBB0700_TARGET_ORDER";

    /** VAR_CHAR_CONCT_NM_TG_CARRIER */
    public static final String VAR_CHAR_CONCT_NM_TG_CARRIER = "NLBB0700_TARGET_CARRIER";

    /**
     * Format of SYSDATE : yyyy/MM/dd HH:mm:ss
     */
    public static final String FMT_YYYYMMDDHHMMSS = "yyyy/MM/dd HH:mm:ss";

    /**
     * Format of SYSDATE : yyyyMMddHHmmssSSS
     */
    public static final String FMT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

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
     * DB COLUMN : SHPG_PNT_CD
     */
    public static final String COL_SHPG_PNT_CD = "SHPG_PNT_CD";
    
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

    /** Column name of TOT_SHPG_WT */
    public static final String COL_TOT_SHPG_WT = "TOT_SHPG_WT";

    /** Column name of TOT_SHPG_VOL */
    public static final String COL_TOT_SHPG_VOL = "TOT_SHPG_VOL";

    /** Column name of DELY_DT */
    public static final String COL_DELY_DT = "DELY_DT";

    /** Column name of CTAC_PTNR_PSN_NM */
    public static final String COL_CTAC_PTNR_PSN_NM = "CTAC_PTNR_PSN_NM";

    /** Column name of CTAC_PTNR_PSN_TEL_NUM */
    public static final String COL_CTAC_PTNR_PSN_TEL_NUM = "CTAC_PTNR_PSN_TEL_NUM";

    /** Column name of PSD_DT */
    public static final String COL_PSD_DT = "PSD_DT";

    /** Column name of TRX_HDR_NUM */
    public static final String COL_TRX_HDR_NUM = "TRX_HDR_NUM";

    /** Column name of CUST_ISS_PO_NUM */
    public static final String COL_CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";
    
    /** Column name of WH_CD */
    public static final String COL_WH_CD = "WH_CD";

    /** Column name of INTFC_TP_ID */
    public static final String COL_INTFC_TP_ID = "INTFC_TP_ID";

    /** Column name of S80_CMPY_CD */
    public static final String COL_S80_CMPY_CD = "S80_CMPY_CD";

    /** Column name of PICK_TKT_NUM */
    public static final String COL_PICK_TKT_NUM = "PICK_TKT_NUM";

    /**
     * DB COLUMN : QTY_FORMAT
     */
    public static final String COL_QTY_FORMAT = "QTY_FORMAT";

    /** Key of ASN WH_CD */
    public static final String VAL_VARCHAR_KEY_ASN_WH_CD = "NLGB0210_ASN_WH_CD";

    /** Key of ADDR_LB_FMT WH_CD */
    public static final String VAL_VARCHAR_KEY_ADDR_LB_FMT_WH_CD = "NLGB0210_ADDR_LB_FMT_WH_CD";

    /** Value of WMS_SHIP_VIA_RTE_MAP List Size */
    public static final int VAL_WMS_SHIP_VIA_RTE_MAP_LIST_SIZE = 50;

    /** Value of WMS_FRT_OUT_CD_MAP List Size */
    public static final int VAL_WMS_FRT_OUT_CD_MAP_LIST_SIZE = 50;

    /** Value of WMS_CTRY_CTAC List Size */
    public static final int VAL_WMS_CTRY_CTAC_LIST_SIZE = 50;

    /** Value of SO_TO_WMS_DATA_TXT : 700 */
    public static final int VAL_SO_TO_WMS_DATA_TXT_SIZE = 700;

    /** Value of WMS_UNIQ_ID : 5 */
    public static final String VAL_WMS_UNIQ_ID_MA = "5";

    /** Value of WMS_UNIQ_ID : 6 */
    public static final String VAL_WMS_UNIQ_ID_MENLO = "6";

    /** Value of WMS_UNIQ_ID : 8 */
    public static final String VAL_WMS_UNIQ_ID_TECSYS = "8";

    /** Value of WMS_UNIQ_ID : 10 */
    public static final String VAL_WMS_UNIQ_ID_DBS = "10";

    /** Value of WMS_UNIQ_ID : 11 (WMS8.4.1) */
    public static final String VAL_WMS_UNIQ_ID_TECSYS_2 = "11";

    /** Value of INTFC_REC_TP_ID : 1 */
    public static final String VAL_INTFC_REC_TP_ID_HDR = "1";

    /** Value of INTFC_REC_TP_ID : 2 */
    public static final String VAL_INTFC_REC_TP_ID_DTL = "2";

    /** Value of INTFC_REC_TP_ID : 3 */
    public static final String VAL_INTFC_REC_TP_ID_SHIP = "3";

    /** Value of INTFC_REC_TP_ID : 4 */
    public static final String VAL_INTFC_REC_TP_ID_TXT = "4";

    /** Value of INTFC_REC_TP_ID : 5 */
    public static final String VAL_INTFC_REC_TP_ID_CHRG = "5";

    /** Value of INTFC_REC_TP_ID : 6 */
    public static final String VAL_INTFC_REC_TP_ID_BILL = "6";

    /** Value of MD_BREAK_TP_CD : WGT */
    public static final String VAL_MD_BREAK_TP_CD_WGT = "WGT";

    /** Value of SHIP_VIA_CD : * */
    public static final String VAL_SHIP_VIA_CD_ALL = "*";

    /** Value of WH_CD : * */
    public static final String VAL_WH_CD_ALL = "*";

    /** Value of WH_CD : 61 */
    public static final String VAL_WH_CD_61 = "61";

    /** Value of WH_CD : 26 */
    public static final String VAL_WH_CD_26 = "26";

    /** Value of WH_CD : 25 */
    public static final String VAL_WH_CD_25 = "25";

    /** Value of WH_CD : 55 */
    public static final String VAL_WH_CD_55 = "55";

    /** Value of WH_CD : 21 */
    public static final String VAL_WH_CD_21 = "21";

    /** Value of WH_CD : 23 */
    public static final String VAL_WH_CD_23 = "23";

    /** Value of INTFC_TP_ID : 02 */
    public static final String VAL_INTFC_TP_ID_02 = "02";

    /** Value of INTFC_REC_TP_ID : 8 */
    public static final String VAL_INTFC_REC_TP_ID_1 = "1";
    
    /** Value of INTFC_REC_TP_ID : 8 */
    public static final String VAL_INTFC_REC_TP_ID_8 = "8";

    /** Value of ORD_TP_TXT : SO */
    public static final String VAL_ORD_TP_TXT_PFX_SO = "SO";

    /** Value of ORD_TP_TXT : PO */
    public static final String VAL_ORD_TP_TXT_PFX_PO = "PO";

    /** Value of ORD_TP_TXT : C */
    public static final String VAL_ORD_TP_TXT_SFX_C = "C";

    /** Value of ORD_TP_TXT : W */
    public static final String VAL_ORD_TP_TXT_SFX_W = "W";

    /** Value of ADDR_LB_FMT_TXT : ASN */
    public static final String VAL_ADDR_LB_FMT_TXT_ASN = "ASN";

    /** Value of ADDR_LB_FMT_TXT : S329 */
    public static final String VAL_ADDR_LB_FMT_TXT_S329 = "S329";

    /** Value of ADDR_LB_FMT_TXT : EXPORT */
    public static final String VAL_ADDR_LB_FMT_TXT_EXPORT = "EXPORT";

    /** Value of PUERTO_RICO : PR */
    public static final String VAL_ST_PUERTO_RICO = "PR";

    /** Value of SCE_ORD_TP_CD : RT */
    public static final String VAL_SCE_ORD_TP_CD_RT = "RT";

    /** Value of SCE_ORD_TP_CD : RB */
    public static final String VAL_SCE_ORD_TP_CD_RB = "RB";

    /** Value of SEGMENT_ID : 1 */
    public static final BigDecimal VAL_SEGMENT_ID_1 = BigDecimal.ONE;

    /** Value of WMS_INTFC_REC_ID : 01 */
    public static final String VAL_WMS_INTFC_REC_ID_01 = "01";

    /** Value of WMS_INTFC_REC_ID : 02 */
    public static final String VAL_WMS_INTFC_REC_ID_02 = "02";

    /** Value of WMS_INTFC_REC_ID : 03 */
    public static final String VAL_WMS_INTFC_REC_ID_03 = "03";

    /** Value of WMS_INTFC_REC_ID : 04 */
    public static final String VAL_WMS_INTFC_REC_ID_04 = "04";

    /** Value of WMS_INTFC_REC_ID : 05 */
    public static final String VAL_WMS_INTFC_REC_ID_05 = "05";

    /** Value of PRTY_NUM_TXT : 10 */
    public static final String VAL_PRTY_NUM_TXT_10 = "10";

    /** Value of ST_CD : XX */
    public static final String VAL_ST_CD_XX = "XX";

    /** Value of POST_CD : 99999 */
    public static final String VAL_POST_CD_99999 = "99999";

    /** Value of RTRN_CTAC_NM_TXT : Attn: Customer Service */
    public static final String VAL_RTRN_CTAC_NM_TXT_ATTN = "Attn: Customer Service";

    /** Value of PACK_CD_TXT : * */
    public static final String VAL_PACK_CD_TXT_ALL = "*";

    /** Value of USR_CD_TXT : * */
    public static final String VAL_USR_CD_TXT_ALL = "*";

    /** Value of SER_NUM_UOM_TXT : EA */
    public static final String VAL_SER_NUM_UOM_TXT_EA = "EA";

    /** Value of ITEM_CD_TXT : [CONFIG] */
    public static final String VAL_ITEM_CD_TXT_SFX = " [CONFIG]";

    /** Value of CHRG_TO_CUST_CD : E6026 */
    public static final String VAL_CHRG_TO_CUST_CD_E6026 = "E6026";

    /** Value of CHRG_TO_CUST_CD : E6A26 */
    public static final String VAL_CHRG_TO_CUST_CD_E6A26 = "E6A26";

    /** Value of CHRG_TO_CUST_CD : E6338 */
    public static final String VAL_CHRG_TO_CUST_CD_E6338 = "E6338";

    /** Value of FRT_TERM_TXT : C */
    public static final String VAL_FRT_TERM_TXT_C = "C";

    /** Value of FRT_TERM_TXT : P */
    public static final String VAL_FRT_TERM_TXT_P = "P";

    /** Value of FRT_TERM_TXT : H */
    public static final String VAL_FRT_TERM_TXT_H = "H";

    /** Value of FRT_TERM_TXT : O */
    public static final String VAL_FRT_TERM_TXT_O = "O";

    /** Value of WMS_FRT_OUT_CD : 0 */
    public static final String VAL_WMS_FRT_OUT_CD_0 = "0";

    /** Value of WMS_FRT_OUT_CD : 1 */
    public static final String VAL_WMS_FRT_OUT_CD_1 = "1";

    /** Value of WMS_FRT_OUT_CD : 2 */
    public static final String VAL_WMS_FRT_OUT_CD_2 = "2";

    /** Value of WMS_FRT_OUT_CD : 3 */
    public static final String VAL_WMS_FRT_OUT_CD_3 = "3";

    /** Size of ORD_CMNT_TXT : 40 */
    public static final int VAL_ORD_CMNT_TXT_SIZE = 40;

    /** Value of RTE_CD_TXT : W */
    public static final String VAL_RTE_CD_TXT_SFX_W = "W";

    /** Value of CARR_CD_TXT : ZZZZ */
    public static final String VAL_CARR_CD_TXT_ZZZZ = "ZZZZ";

    /** Value of CARR_CD_TXT : 1 */
    public static final String VAL_CARR_CD_TXT_1 = "1";

    /** Value of RTE_CD_TXT : K */
    public static final String VAL_RTE_CD_TXT_K = "K";

    /** Value of RTE_CD_TXT : 1 */
    public static final String VAL_RTE_CD_TXT_1 = "1";

    /** Value of RTE_NM_TXT : ItemChange/Kit */
    public static final String VAL_RTE_NM_TXT_ITEM = "ItemChange/Kit";

    /** Value of RTE_NM_TXT : GROUND */
    public static final String VAL_RTE_NM_TXT_GROUND = "GROUND";

    /** Value of WMS_RESRC_TXT : Preprocess */
    public static final String VAL_WMS_RESRC_TXT = "Preprocess";

    /** Value of TP_VND_CD */
    public static final String VAL_TP_VND_CD_HAZMAT = "HAZMAT";

    /** Default Value of WMS_ORD_SRC_CD */
    public static final String VAL_DEF_WMS_ORD_SRC_CD = "*";

    /** Default Value of WH_CD */
    public static final String VAL_DEF_WH_CD = "*";

    /** Default Value of CTRY_CD */
    public static final String VAL_DEF_CTRY_CD = "*";

    /** Size of END_CUST_ORD_NUM : 30 */
    public static final int VAL_END_CUST_ORD_NUM_SIZE = 30;

    /** Size of SHIP_CTY_TXT : 20 */
    public static final int VAL_SHIP_CTY_TXT_SIZE = 20;

    /** Size of Decimal Place */
    public static final int VAL_EST_DECIMAL_PLACE = 2;

    /** Value 1 */
    public static final String VAL_1 = "1";

    /** Value 000000 */
    public static final String VAL_000000 = "000000";

    /** Value Blank */
    public static final String VAL_BLANK = " ";

    /** Value Blank */
    public static final String VAL_BLANK2 = "  ";

    /** Value / */
    public static final String VAL_SLASH = "/";

    /** Value : */
    public static final String VAL_COLON = ":";

    /** Value , */
    public static final String VAL_COMMA = ",";

    /** Value : HAZMAT */
    public static final String VAL_HAZMAT = "HAZMAT";

    /** Value of multiply ORD_LINE_TXT : 1000 */
    public static final BigDecimal VAL_ORD_LINE_TXT_MULT = new BigDecimal(1000);

    /** Value of ORD_CMNT_TXT : [SS] Status Change from { */
    public static final String VAL_ORD_CMNT_TXT_PFX = "[SS] Status Change from {";

    /** Value of ORD_CMNT_TXT : } to { */
    public static final String VAL_ORD_CMNT_TXT_CONJ = "} to {";

    /** Value of ORD_CMNT_TXT : } */
    public static final String VAL_ORD_CMNT_TXT_SFX = "}";

    /** Format of SYSDATE : yyyyMMddHHmmss */
    public static final String FMT_YYYYMMDDHHMMSS2 = "yyyy-MM-dd HH:mm:ss";

    /** Format of SYSDATE : yyyy-MM-dd 00:00:00 */
    public static final String FMT_YYYYMMDD000000 = "yyyy-MM-dd 00:00:00";

    // FROM
    /** CUST_DATA_TP_CD SHIP_TO */
    public static final String VAL_SO_CUST_DATA_TP_CD_SHIP_TO = "3";

    /** CUST_DATA_TP_CD BILL_TO */
    public static final String VAL_SO_CUST_DATA_TP_CD_BILL_TO = "1";

    /**  */
    /** TPL_CTRL_ID */
    public static final String VAL_TPL_CTRL_ID_CHA = "CHA";

    /** TPL_CTRL_ID */
    public static final String VAL_TPL_CTRL_ID_ORI = "ORI";

    /** Const Val for CARR_CUST_TXT : APXT */
    public static final String VAL_CONST_CARR_CUST_TXT = "APXT";

    /** Const Val for CARR_NM_01 */
    public static final String VAL_CONST_CARR_NM_01 = "APEX TRUCKING CO";

    /** Subpublic static final String start position for CTAC_CUST_TXT */
    public static final int LG_CUT_CTAC_CUST_TXT_FROM = 0;

    /** Max size of CTAC_CUST_TXT : 10 */
    public static final int LG_CUT_CTAC_CUST_TXT_MAX_SIZE = 10;

    /** Subpublic static final String start position for CTAC_PHO_NUM */
    public static final int LG_CUT_CTAC_PHO_NUM_FROM = 0;

    /** Max size of CTAC_PHO_NUM : 15 */
    public static final int LG_CUT_CTAC_PHO_NUM_MAX_SIZE = 15;

    /** Max size of Date Column Length : 14 */
    public static final int LG_DATE_COLUMN_SIZE = 14;

    /** Const Val for TPL_ORG_TXT */
    public static final String VAL_TPL_ORG_TXT_RO = "RO";

    /** Const Val for TPL_ORG_TXT */
    public static final String VAL_TPL_ORG_TXT_XX = "XX";

    /** Const Val for TPL_TP_TXT */
    public static final String VAL_ORD_TP_TXT_LR = "LR";

    /** Const Val for TPL_TP_TXT */
    public static final String VAL_ORD_TP_TXT_LF = "LF";

    /** Const Val for WMS_WT_UOM_CD */
    public static final String VAL_WMS_WT_UOM_CD_CONST = "LBR";

    /** Const Val for WMS_VOL_UOM_CD */
    public static final String VAL_WMS_VOL_UOM_CD_CONST = "INQ";

    /** SO TEXT MAX SIZE */
    public static final int LG_SO_TEXT_MAX_SIZE = 65;

    /** Const VAL For REC_ID */
    public static final String VAL_WMS_REC_ID_NLBI1210_01 = "01";

    /** Const VAL For REC_ID */
    public static final String VAL_WMS_REC_ID_NLBI1210_02 = "02";

    /** Const VAL For REC_ID */
    public static final String VAL_WMS_REC_ID_NLBI1210_03 = "03";

    /** Const VAL For REC_ID */
    public static final String VAL_WMS_REC_ID_NLBI1210_04 = "04";

    /** Const VAL For REC_ID */
    public static final String VAL_WMS_REC_ID_NLBI1210_05 = "05";

    /** Const VAL For DELY_REQ */
    public static final String VAL_DELY_REQ_ON = "Y";

    /** Const VAL For PICK_UP_RQST */
    public static final String VAL_PICK_UP_RQST_ON = "Y";

    /** VAL For TP_DELIV_MSG */
    public static final String VAL_TP_DELIV_MSG = "D";

    /** VAL For WMS_DROP_STS */
    public static final String VAL_WMS_DROP_STS = "N";

    /** VAL For WMS_ERR_STS */
    public static final String VAL_WMS_ERR_STS = "E";

    /** DB Column: TPL_CTRL_ID */
    public static final String COL_TPL_CTRL_ID = "TPL_CTRL_ID";

    /** DB Column: SHIP_CUST_TXT */
    public static final String COL_SHIP_CUST_TXT = "SHIP_CUST_TXT";

    /** DB Column: SHIP_NM_TXT_01 */
    public static final String COL_SHIP_NM_TXT_01 = "SHIP_NM_TXT_01";

    /** DB Column:SHIP_NM_TXT_02 */
    public static final String COL_SHIP_NM_TXT_02 = "SHIP_NM_TXT_02";

    /** DB Column: SHIP_FIRST_LINE_ADDR_TXT */
    public static final String COL_SHIP_FIRST_LINE_ADDR_TXT = "SHIP_FIRST_LINE_ADDR_TXT";

    /** DB Column: SHIP_SCD_LINE_ADDR_TXT */
    public static final String COL_SHIP_SCD_LINE_ADDR_TXT = "SHIP_SCD_LINE_ADDR_TXT";

    /** DB Column: SHIP_THIRD_LINE_ADDR_TXT */
    public static final String COL_SHIP_THIRD_LINE_ADDR_TXT = "SHIP_THIRD_LINE_ADDR_TXT";

    /** DB Column: SHIP_ZIP_OR_POST_CD_TXT */
    public static final String COL_SHIP_ZIP_OR_POST_CD_TXT = "SHIP_ZIP_OR_POST_CD_TXT";

    /** DB Column:SHIP_CTY_TXT */
    public static final String COL_SHIP_CTY_TXT = "SHIP_CTY_TXT";

    /** DB Column:SHIP_CTRY_TXT */
    public static final String COL_SHIP_CTRY_TXT = "SHIP_CTRY_TXT";

    /** DB Column: SHIP_ST_OR_PROV_TXT */
    public static final String COL_SHIP_ST_OR_PROV_TXT = "SHIP_ST_OR_PROV_TXT";

    /** DB Column: SHIP_PHO_NUM_TXT */
    public static final String COL_SHIP_PHO_NUM_TXT = "SHIP_PHO_NUM_TXT";

    /** DB Column: BILL_TO_CUST_CD */
    public static final String COL_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB Column:BILL_CUST_TXT */
    public static final String COL_BILL_CUST_TXT = "BILL_CUST_TXT";

    /** DB Column: BILL_NM_TXT_01 */
    public static final String COL_BILL_NM_TXT_01 = "BILL_NM_TXT_01";

    /** DB Column:BILL_NM_TXT_02 */
    public static final String COL_BILL_NM_TXT_02 = "BILL_NM_TXT_02";

    /** DB Column: BILL_FIRST_LINE_ADDR_TXT */
    public static final String COL_BILL_FIRST_LINE_ADDR_TXT = "BILL_FIRST_LINE_ADDR_TXT";

    /** DB Column:BILL_SCD_LINE_ADDR_TXT */
    public static final String COL_BILL_SCD_LINE_ADDR_TXT = "BILL_SCD_LINE_ADDR_TXT";

    /** DB Column: BILL_THIRD_LINE_ADDR_TXT */
    public static final String COL_BILL_THIRD_LINE_ADDR_TXT = "BILL_THIRD_LINE_ADDR_TXT";

    /** DB Column: BILL_ZIP_OR_POST_CD_TXT */
    public static final String COL_BILL_ZIP_OR_POST_CD_TXT = "BILL_ZIP_OR_POST_CD_TXT";

    /** DB Column: BILL_CTY_TXT */
    public static final String COL_BILL_CTY_TXT = "BILL_CTY_TXT";

    /** DB Column:BILL_CTRY_TXT */
    public static final String COL_BILL_CTRY_TXT = "BILL_CTRY_TXT";

    /** DB Column: BILL_ST_OR_PROV_TXT */
    public static final String COL_BILL_ST_OR_PROV_TXT = "BILL_ST_OR_PROV_TXT";

    /** DB Column:BILL_PHO_NUM_TXT */
    public static final String COL_BILL_PHO_NUM_TXT = "BILL_PHO_NUM_TXT";

    /** DB Column: CTAC_CUST_TXT */
    public static final String COL_CTAC_CUST_TXT = "CTAC_CUST_TXT";

    /** DB Column: CTAC_CUST_NM_01 */
    public static final String COL_CTAC_CUST_NM_01 = "CTAC_CUST_NM_01";

    /** DB Column: CTAC_CUST_NM_02 */
    public static final String COL_CTAC_CUST_NM_02 = "CTAC_CUST_NM_02";

    /** DB Column: CTAC_FIRST_LINE_ADDR */
    public static final String COL_CTAC_FIRST_LINE_ADDR = "CTAC_FIRST_LINE_ADDR";

    /** DB Column:CTAC_SCD_LINE_ADDR */
    public static final String COL_CTAC_SCD_LINE_ADDR = "CTAC_SCD_LINE_ADDR";

    /** DB Column: CTAC_THIRD_LINE_ADDR */
    public static final String COL_CTAC_THIRD_LINE_ADDR = "CTAC_THIRD_LINE_ADDR";

    /** DB Column:CTAC_ZIP_OR_POST_CD */
    public static final String COL_CTAC_ZIP_OR_POST_CD = "CTAC_ZIP_OR_POST_CD";

    /** DB Column: CTAC_CTY_TXT */
    public static final String COL_CTAC_CTY_TXT = "CTAC_CTY_TXT";

    /** DB Column:CTAC_CTRY_TXT */
    public static final String COL_CTAC_CTRY_TXT = "CTAC_CTRY_TXT";

    /** DB Column:CTAC_ST_OR_PROV_TXT */
    public static final String COL_CTAC_ST_OR_PROV_TXT = "CTAC_ST_OR_PROV_TXT";

    /** DB Column: CTAC_PHO_NUM */
    public static final String COL_CTAC_PHO_NUM = "CTAC_PHO_NUM";

    /** DB Column:CARR_CUST_TXT */
    public static final String COL_CARR_CUST_TXT = "CARR_CUST_TXT";

    /** DB Column: CARR_NM_01 */
    public static final String COL_CARR_NM_01 = "CARR_NM_01";

    /** DB Column:CARR_NM_02 */
    public static final String COL_CARR_NM_02 = "CARR_NM_02";

    /** DB Column: CARR_FIRST_LINE_ADDR */
    public static final String COL_CARR_FIRST_LINE_ADDR = "CARR_FIRST_LINE_ADDR";

    /** DB Column: CARR_SCD_LINE_ADDR */
    public static final String COL_CARR_SCD_LINE_ADDR = "CARR_SCD_LINE_ADDR";

    /** DB Column:CARR_THIRD_LINE_ADDR */
    public static final String COL_CARR_THIRD_LINE_ADDR = "CARR_THIRD_LINE_ADDR";

    /** DB Column: CARR_ZIP_OR_POST_CD */
    public static final String COL_CARR_ZIP_OR_POST_CD = "CARR_ZIP_OR_POST_CD";

    /** DB Column: CARR_CD */
    public static final String COL_CARR_CD = "CARR_CD";

    /** DB Column: CARR_CTY_TXT */
    public static final String COL_CARR_CTY_TXT = "CARR_CTY_TXT";

    /** DB Column: CARR_CTRY_TXT */
    public static final String COL_CARR_CTRY_TXT = "CARR_CTRY_TXT";

    /** DB Column: CARR_ST_OR_PROV_TXT */
    public static final String COL_CARR_ST_OR_PROV_TXT = "CARR_ST_OR_PROV_TXT";

    /** DB Column: CARR_PHO_NUM */
    public static final String COL_CARR_PHO_NUM = "CARR_PHO_NUM";

    /** DB Column:WMS_REQ_DT_TXT */
    public static final String COL_WMS_REQ_DT_TXT = "WMS_REQ_DT_TXT";

    /** DB Column: WMS_FNSH_REQ_DT_TXT */
    public static final String COL_WMS_FNSH_REQ_DT_TXT = "WMS_FNSH_REQ_DT_TXT";

    /** DB Column: WMS_ACT_DT_TXT */
    public static final String COL_WMS_ACT_DT_TXT = "WMS_ACT_DT_TXT";

    /** DB Column: WMS_FNSH_ACT_DT_TXT */
    public static final String COL_WMS_FNSH_ACT_DT_TXT = "WMS_FNSH_ACT_DT_TXT";

    /** DB Column: WMS_ORD_DT_TXT */
    public static final String COL_WMS_ORD_DT_TXT = "WMS_ORD_DT_TXT";

    /** DB Column: WMS_FNSH_ORD_DT_TXT */
    public static final String COL_WMS_FNSH_ORD_DT_TXT = "WMS_FNSH_ORD_DT_TXT";

    /** DB Column: WMS_DELY_DT_TXT */
    public static final String COL_WMS_DELY_DT_TXT = "WMS_DELY_DT_TXT";

    /** DB Column:WMS_DELY_TS */
    public static final String COL_WMS_DELY_TS = "WMS_DELY_TS";

    /** DB Column: WMS_FNSH_DELY_DT_TXT */
    public static final String COL_WMS_FNSH_DELY_DT_TXT = "WMS_FNSH_DELY_DT_TXT";

    /** DB Column: WMS_FNSH_DELY_TS */
    public static final String COL_WMS_FNSH_DELY_TS = "WMS_FNSH_DELY_TS";

    /** DB Column: TPL_SRC_ORD_NUM */
    public static final String COL_TPL_SRC_ORD_NUM = "TPL_SRC_ORD_NUM";

    /** DB Column: TPL_CUST_ORD_NUM */
    public static final String COL_TPL_CUST_ORD_NUM = "TPL_CUST_ORD_NUM";

    /** DB Column: ORD_DT_TM_TS_TXT */
    public static final String COL_ORD_DT_TM_TS_TXT = "ORD_DT_TM_TS_TXT";

    /** DB Column:WMS_REC_ID */
    public static final String COL_WMS_REC_ID = "WMS_REC_ID";

    /** DB Column: WMS_HDR_DELY_DT_TXT */
    public static final String COL_WMS_HDR_DELY_DT_TXT = "WMS_HDR_DELY_DT_TXT";

    /** DB Column: ORD_TP_TXT */
    public static final String COL_ORD_TP_TXT = "ORD_TP_TXT";

    /** DB Column: MDSE_CD */
    public static final String COL_MDSE_CD = "MDSE_CD";

    /** DB Column: MDSE_NM */
    public static final String COL_MDSE_NM = "MDSE_NM";

    /** DB Column: PLANT_CD */
    public static final String COL_PLANT_CD = "PLANT_CD";

    /** DB Column: RTL_SWH_CD */
    public static final String COL_RTL_SWH_CD = "RTL_SWH_CD";

    /** DB Column: SHPG_QTY */
    public static final String COL_SHPG_QTY = "SHPG_QTY";

    /** DB Column: SHPG_SVC_LVL_CD */
    public static final String COL_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** DB Column: TOT_SHIP_WT */
    public static final String COL_TOT_SHIP_WT = "TOT_SHIP_WT";

    /** DB Column: TOT_SHIP_VOL */
    public static final String COL_TOT_SHIP_VOL = "TOT_SHIP_VOL";

    /** DB Column: TRX_LINE_NUM */
    public static final String COL_TRX_LINE_NUM = "TRX_LINE_NUM";

    /** DB Column: SO_MSG_TP_CD */
    public static final String COL_SO_MSG_TP_CD = "SO_MSG_TP_CD";
    
    /** DB Column: SO_MSG_DESC_TXT */
    public static final String COL_SO_MSG_DESC_TXT = "SO_MSG_DESC_TXT";

    /** DB Column: INBD_SO_MSG_TXT_01 */
    public static final String COL_INBD_SO_MSG_TXT_01 = "INBD_SO_MSG_TXT_01";

    /** DB Column: INBD_SO_MSG_TXT_02 */
    public static final String COL_INBD_SO_MSG_TXT_02 = "INBD_SO_MSG_TXT_02";

    /** DB Column: INBD_SO_MSG_TXT_03 */
    public static final String COL_INBD_SO_MSG_TXT_03 = "INBD_SO_MSG_TXT_03";

    /** DB Column: INBD_SO_MSG_TXT_04 */
    public static final String COL_INBD_SO_MSG_TXT_04 = "INBD_SO_MSG_TXT_04";

    /** DB Column: SER_NUM */
    public static final String COL_SER_NUM = "SER_NUM";

    /** DB Column: RWS_QTY */
    public static final String COL_RWS_QTY = "RWS_QTY";

    /** DB Column: RWS_MSG_TXT */
    public static final String COL_RWS_MSG_TXT = "RWS_MSG_TXT";

    /** DB Column: WH_OWNR_CD */
    public static final String COL_WH_OWNR_CD = "WH_OWNR_CD";

    /** DB Column: CARR_TP_NM */
    public static final String COL_CARR_TP_NM = "CARR_TP_NM";

    /** Interface ID NLBI1210 */
    public static final String IF_ID_NLBI1210 = "NLBI1210";

    /** DB Column: TPL_CARR_CD */
    public static final String COL_TPL_CARR_CD = "TPL_CARR_CD";

    /** DB Column: TPL_CARR_CD */
    public static final String COL_TPL_CARR_NM = "TPL_CARR_NM";
    
    /** DB Column: TPL_SVC_LVL_CD */
    public static final String COL_TPL_SVC_LVL_CD = "TPL_SVC_LVL_CD";

    /** DB Column: DROP_SHIP_FLG */
    public static final String COL_DROP_SHIP_FLG = "DROP_SHIP_FLG";

    /** DB Column: S80_ORD_TP_CD */
    public static final String COL_S80_ORD_TP_CD = "S80_ORD_TP_CD";

    /** DB Column: S80_TRX_CD */
    public static final String COL_S80_TRX_CD = "S80_TRX_CD";

    /** DB Column: S80_TRX_SRC_TP */
    public static final String COL_S80_ORD_SRC_CD = "S80_ORD_SRC_CD";

    /** DB Column: SO_CRAT_TS */
    public static final String COL_SO_CRAT_TS = "SO_CRAT_TS";

    /** DB Column: DNLD_TM_TS */
    public static final String COL_DNLD_TM_TS = "DNLD_TM_TS";

    /** DB Column: S80_SHPG_TERM_CD */
    public static final String COL_S80_SHPG_TERM_CD = "S80_SHPG_TERM_CD";

    /** DB Column: S80_SHPG_TERM_NM */
    public static final String COL_S80_SHPG_TERM_NM = "S80_SHPG_TERM_NM";

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
    
   
    /** DB Column: RTRN_REQ_PRT_FLG */
    public static final String COL_RTRN_REQ_PRT_FLG = "RTRN_REQ_PRT_FLG";

    /** DB Column: SVC_CONFIG_MSTR_PK */
    public static final String COL_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** DB Column: PRE_ISTL_SHOP_RQST_FLG */
    public static final String COL_PRE_ISTL_SHOP_RQST_FLG = "PRE_ISTL_SHOP_RQST_FLG";

    /** DB Column: STAGE_ACT_CD */
    public static final String COL_STAGE_ACT_CD = "STAGE_ACT_CD";
    
    /** DB Column: WMS_RTE_PATH_CD */
    public static final String COL_WMS_RTE_PATH_CD = "WMS_RTE_PATH_CD";
    
    /** DB Column: OTBD_SRC_ORD_TP_TXT */
    public static final String COL_OTBD_SRC_ORD_TP_TXT = "OTBD_SRC_ORD_TP_TXT";
    
    /** DB Column: SRC_ORD_NUM */
    public static final String COL_SRC_ORD_NUM = "SRC_ORD_NUM";
    
    /** DB Column: SLS_ORD_ADMIN_NM */
    public static final String COL_SLS_ORD_ADMIN_NM = "SLS_ORD_ADMIN_NM";

    /** DB Column: S80_STK_STS_CD */
    public static final String COL_S80_STK_STS_CD = "S80_STK_STS_CD";
    

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

    /** DB Column: BAT_NUM_TAKE_FLG */
    public static final String COL_BAT_NUM_TAKE_FLG = "BAT_NUM_TAKE_FLG";

    /** DB Column: CONFIG_ITEM_FLG */
    public static final String COL_CONFIG_ITEM_FLG = "CONFIG_ITEM_FLG";

    /** DB Column: RTL_WH_CD */
    public static final String COL_RTL_WH_CD = "RTL_WH_CD";

    /** DB Column: PICK_SVC_CONFIG_MSTR_PK */
    public static final String COL_PICK_SVC_CONFIG_MSTR_PK = "PICK_SVC_CONFIG_MSTR_PK";

    /** DB Column: BACK_ORD_IMPCT_TP_CD */
    public static final String COL_BACK_ORD_IMPCT_TP_CD = "BACK_ORD_IMPCT_TP_CD";

    /** DB Column: RMV_CONFIG_FLG */
    public static final String COL_RMV_CONFIG_FLG = "RMV_CONFIG_FLG";

    /** DB Column: ORD_QTY */
    public static final String COL_ORD_QTY = "ORD_QTY";
    
    // QC#18460 Add
    /** DB Column: SCHD_DELY_TM */
    public static final String COL_SCHD_DELY_TM = "SCHD_DELY_TM";
    
    
    // ///////////////////////////////////////////////////////
    // Error Message Parameter
    // ///////////////////////////////////////////////////////
    /** Message string : Global Company Code */
    public static final String MSG_STR_COMP_CODE = "Global Company Code";

    /** Message string : Global Company Code */
    public static final String MSG_STR_INTERFACE_ID = "Interface ID";

    /** Message string : Commit Count */
    public static final String MSG_STR_PARAM_01 = "Commit Count(VAR_USER1)";

    /** MAX TEXT SIZE */
    public static final int VAL_MAX_TEXT_SIZE = 400;


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
    public static final int TPL_SITE_SRVY_TXT = 132;

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

    // QC#31196
    /** Column name of SHIP_TO_CTAC_PSN_NM */
    public static final String COL_SHIP_TO_CTAC_PSN_NM = "SHIP_TO_CTAC_PSN_NM";
    // QC#31196
    /** Max size of CTAC_CUST_NM_01 : 30 */
    public static final int LG_CUT_CTAC_CUST_NM_01_MAX_SIZE = 30;

    // QC#52369
    /** Format of date : yyyyMMddHHmm */
    public static final String VAL_DATE_FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmm";

    /** Format of date : yyyyMMdd */
    public static final String VAL_DATE_FORMAT_FROM = "yyyyMMdd";
}
