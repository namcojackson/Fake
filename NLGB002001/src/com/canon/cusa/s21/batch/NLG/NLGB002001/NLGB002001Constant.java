/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB002001;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 * <pre>
 * RWS and Item Download
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/12/2013   CSAI            K.Hayashida     Create          MW Replace Initial
 * 04/19/2016   CSAI            D.Fukaya        Update          QC#5252
 * 02/07/2017   CITS            Y.Fujii         Update          QC#17422
 * 05/08/2017   CITS            T.Kikuhara      Update          RS#3135
 * 06/22/2017   CITS            T.Kikuhara      Update          QC#19477
 * 06/29/2017   CITS            K.Ogino         Update          QC#19634
 * 09/15/2017   CITS            T.Wada          Update          QC#21166
 *</pre>
 */
public interface NLGB002001Constant {

    /** Business ID */
    String BUSINESS_ID = "NLGB0020";

    /** Output Log Program ID */
    String PROGRAM_ID = "NLGB002001:";

    /** Prameter Name: GLBL_CMPY_CD */
    String PRAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Prameter Name: WH_GRP_CD */
    String PRAM_NM_WH_GRP_CD = "Warehouse Group Code";

    /** Prameter Name: Process Type */
    String PRAM_NM_PROC_TP = "Process Type";

    /**
     * The record cannot be registered. Table Name: [@], Field Name:
     * [@], Key Value: [@]
     */
    String NLGM0007E = "NLGM0007E";

    /**
     * The corresponding data does not exist. Table Name : [@], Key
     * Field Name: [@], Key Value: [@]
     */
    String NLGM0044E = "NLGM0044E";

    /**
     * The record cannot be registered. Registration Table Name: [@],
     * Table Name: [@], Key Field Name: [@], Key Value: [@]
     */
    String NLGM0045E = "NLGM0045E";

    /**
     * The record cannot be delete. Table Name: [@], Key Field Name: [@], Key Value: [@]
     */
    String NLGM0040E = "NLGM0040E";
    /**
     * The record cannot be updated. Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    String NLGM0046E = "NLGM0046E";

    /**
     * Warehouse code to be processed is not set. Please check the WMS
     * warehouse table. MW_REPL_TRGT_GRP_CD: [@]
     */
    String NLGM0047E = "NLGM0047E";

    /**
     * [@] Parameter has not been set.
     */
    String NLGM0049E = "NLGM0049E";

    /**
     * Column: [@], Value: [@] is an invalid date. Table Name: [@],
     * Key Field Name: [@], Key Value: [@]
     */
    String NLGM0052E = "NLGM0052E";

    /** [@] is an invalid parameter. Parameter Name: [@] */
    String NLGM0060E = "NLGM0060E";

    /**
     * The corresponding data does not exist. Table Name : [@],
     * Key Field Name:  [@], Key Value: [@]
     */
    String NLAM1001E = "NLAM1001E";

    /** DB Table: RWS_HDR */
    String TBL_RWS_HDR = "RWS_HDR";

    /** DB Table: WMS_INBD_RWS_WRK */
    String TBL_WMS_INBD_RWS_WRK = "WMS_INBD_RWS_WRK";

    /** DB Table: WMS_INBD_PO_HDR */
    String TBL_WMS_INBD_PO_HDR = "WMS_INBD_PO_HDR";

    /** DB Table: WMS_INBD_PO_DTL */
    String TBL_WMS_INBD_PO_DTL = "WMS_INBD_PO_DTL";

    /** DB Table: WMS_INBD_PO_VND */
    String TBL_WMS_INBD_PO_VND = "WMS_INBD_PO_VND";

    /** DB Table: NLGI2100_01 */
    String TBL_NLGI2100_01 = "NLGI2100_01";

    /** DB Table: NLGI2100_02 */
    String TBL_NLGI2100_02 = "NLGI2100_02";

    /** DB Table: NLGI2100_03 */
    String TBL_NLGI2100_03 = "NLGI2100_03";

    /** DB Table: NLGI2100_04 */
    String TBL_NLGI2100_04 = "NLGI2100_04";

    /** DB Table: NLGI2100_05 */
    String TBL_NLGI2100_05 = "NLGI2100_05";

    /** DB Table: NLGI2100_06 */
    String TBL_NLGI2100_06 = "NLGI2100_06";

    /** DB Table: NLGI2100_07 */
    String TBL_NLGI2100_07 = "NLGI2100_07";

    /** DB Table: NLGI3100_01 */
    String TBL_NLGI3100_01 = "NLGI3100_01";

    /** DB Table: WMS_INBD_MDSE */
    String TBL_WMS_INBD_MDSE = "WMS_INBD_MDSE";

    /** DB Table: WMS_INBD_MDSE_UPC */
    String TBL_WMS_INBD_MDSE_UPC = "WMS_INBD_MDSE_UPC";

    /** DB Table: WMS_INBD_MDSE_SER */
    String TBL_WMS_INBD_MDSE_SER = "WMS_INBD_MDSE_SER";

    /** DB Table: WMS_INBD_ITEM_WRK */
    String TBL_WMS_INBD_ITEM_WRK = "WMS_INBD_ITEM_WRK";

    /** DB Table: WMS_INBD_ITEM_UPC_WRK */
    String TBL_WMS_INBD_ITEM_UPC_WRK = "WMS_INBD_ITEM_UPC_WRK";

    /** DB Table: WMS_INBD_ITEM_SER_WRK */
    String TBL_WMS_INBD_ITEM_SER_WRK = "WMS_INBD_ITEM_SER_WRK";

    /** DB Table: WMS_MDSE_LIST */
    String TBL_WMS_MDSE_LIST = "WMS_MDSE_LIST";

    /** DB Table: WMS_RWS_ORIG_LINE_SAVE */
    String TBL_WMS_RWS_ORIG_LINE_SAVE = "WMS_RWS_ORIG_LINE_SAVE";

    /** DB Table: CMPY_CD_CONV */
    String TBL_CMPY_CD_CONV = "CMPY_CD_CONV";

    /** DB Table: SCE_ORD_TP */
    String TBL_SCE_ORD_TP = "SCE_ORD_TP";

    /** DB Table: STS_STK_CONV */
    String TBL_STS_STK_CONV = "STS_STK_CONV";

    /** DB Column: GLBL_CMPY_CD */
    String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB Column: WMS_PO_DNLD_INTFC_ID */
    String COL_WMS_PO_DNLD_INTFC_ID = "WMS_PO_DNLD_INTFC_ID";

    /** DB Column: WMS_ITEM_DNLD_INTFC_ID */
    String COL_WMS_ITEM_DNLD_INTFC_ID = "WMS_ITEM_DNLD_INTFC_ID";

    /** DB Column: RWS_TO_WMS_DATA_TXT */
    String COL_RWS_TO_WMS_DATA_TXT = "RWS_TO_WMS_DATA_TXT";

    /** DB Column: WH_CD */
    String COL_WMS_WH_CD = "WMS_WH_CD";

    /** DB Column: WMS_CMPY_CD */
    String COL_WMS_CMPY_CD = "WMS_CMPY_CD";

    /** DB Column: WMS_SQ_NUM */
    String COL_WMS_SQ_NUM = "WMS_SQ_NUM";

    /** DB Column: MDSE_CD */
    String COL_MDSE_CD = "MDSE_CD";

    /** DB Column: WMS_MDSE_CD */
    String COL_WMS_MDSE_CD = "WMS_MDSE_CD";

    /** DB Column: WMS_ITEM_CD */
    String COL_WMS_ITEM_CD = "WMS_ITEM_CD";

    /** DB Column: WMS_INBD_RWS_WRK_PK */
    String COL_WMS_INBD_RWS_WRK_PK = "WMS_INBD_RWS_WRK_PK";

    /** DB Column: PO_NUM */
    String COL_PO_NUM = "PO_NUM";

    /** DB Column: WMS_EST_DT_TM_TS */
    String COL_WMS_EST_DT_TM_TS = "WMS_EST_DT_TM_TS";

    /** DB Column: PO_FROM_DT_TM_TS */
    String COL_PO_FROM_DT_TM_TS = "PO_FROM_DT_TM_TS";

    /** DB Column: WMS_INBD_ITEM_WRK_PK */
    String COL_WMS_INBD_ITEM_WRK_PK = "WMS_INBD_ITEM_WRK_PK";

    /** DB Column: WMS_INBD_ITEM_UPC_WRK_PK */
    String COL_WMS_INBD_ITEM_UPC_WRK_PK = "WMS_INBD_ITEM_UPC_WRK_PK";

    /** DB Column: WMS_INBD_ITEM_SER_WRK_PK */
    String COL_WMS_INBD_ITEM_SER_WRK_PK = "WMS_INBD_ITEM_SER_WRK_PK";

    /** DB Column: LAST_INTFC_MDSE_UPD_TS */
    String COL_LAST_INTFC_MDSE_UPD_TS = "LAST_INTFC_MDSE_UPD_TS";

    /** DB Column: RTL_WH_CD */
    String COL_RTL_WH_CD = "RTL_WH_CD";

    /** DB Column: WMS_ORD_TP_CACHE_KEY */
    String WMS_ORD_TP_CACHE_KEY = "WMS_ORD_TP_CACHE_KEY";

    /** DB Column: WMS_ORD_TP_XPND_CD */
    String COL_WMS_ORD_TP_XPND_CD = "WMS_ORD_TP_XPND_CD";

    /** DB COLUMN :RWS_NUM */
    String COL_RWS_NUM  = "RWS_NUM";

    /** DB COLUMN :RWS_TO_WMS_DTL_LINE_NUM  */
    String COL_RWS_TO_WMS_DTL_LINE_NUM = "RWS_TO_WMS_DTL_LINE_NUM";

    /** DB COLUMN :RWS_TO_WMS_QTY */
    String COL_RWS_TO_WMS_QTY  = "RWS_TO_WMS_QTY";

    /** DB COLUMN :RWS_ORIG_DTL_LINE_NUM */
    String COL_RWS_ORIG_DTL_LINE_NUM  = "RWS_ORIG_DTL_LINE_NUM";

    /** DB COLUMN :RWS_REF_NUM */
    String COL_RWS_REF_NUM  = "RWS_REF_NUM";

    /** DB COLUMN :FROM_LOC_CD */
    String COL_FROM_LOC_CD  = "FROM_LOC_CD";

    /** DB COLUMN :WMS_PRCH_TP_CD */
    String COL_WMS_PRCH_TP_CD  = "WMS_PRCH_TP_CD";

    /** DB COLUMN :WMS_TRX_CD */
    String COL_WMS_TRX_CD  = "WMS_TRX_CD";

    /** DB COLUMN :IMPT_INV_VESL_NM */
    String COL_IMPT_INV_VESL_NM  = "IMPT_INV_VESL_NM";

    /** DB COLUMN :IMPT_INV_BOL_NUM */
    String COL_IMPT_INV_BOL_NUM  = "IMPT_INV_BOL_NUM";

    /** DB COLUMN :WH_CD */
    String COL_WH_CD  = "WH_CD";

    /** DB COLUMN :SCE_ORD_TP_CD */
    String COL_SCE_ORD_TP_CD  = "SCE_ORD_TP_CD";

    /** DB COLUMN :SVC_CONFIG_MSTR_PK */
    String COL_SVC_CONFIG_MSTR_PK  = "SVC_CONFIG_MSTR_PK";

    /** DB COLUMN :S80_STK_STS_CD */
    String COL_S80_STK_STS_CD  = "S80_STK_STS_CD";

    /** DB COLUMN :INVTY_STK_STS_CD */
    String COL_INVTY_STK_STS_CD  = "INVTY_STK_STS_CD";

    /** DB COLUMN :RWS_DTL_LINE_NUM */
    String COL_RWS_DTL_LINE_NUM  = "RWS_DTL_LINE_NUM";

    /** DB COLUMN :RWS_QTY */
    String COL_RWS_QTY  = "RWS_QTY";

    /** DB COLUMN :WH_IN_ETA_DT */
    String COL_WH_IN_ETA_DT  = "WH_IN_ETA_DT";

    /** DB COLUMN :IMPT_INV_NUM */
    String COL_IMPT_INV_NUM  = "IMPT_INV_NUM";

    /** DB COLUMN :IMPT_INV_DO_NUM */
    String COL_IMPT_INV_DO_NUM  = "IMPT_INV_DO_NUM";

    /** DB COLUMN :OUT_PACK_FROM_CSE_NUM */
    String COL_OUT_PACK_FROM_CSE_NUM  = "OUT_PACK_FROM_CSE_NUM";

    /** DB COLUMN :OUT_PACK_TO_CSE_NUM */
    String COL_OUT_PACK_TO_CSE_NUM  = "OUT_PACK_TO_CSE_NUM";

    /** DB COLUMN :RTL_SWH_CD */
    String COL_RTL_SWH_CD  = "RTL_SWH_CD";

    /** DB COLUMN :THIRD_PTY_DSP_TP_CD */
    String COL_THIRD_PTY_DSP_TP_CD  = "THIRD_PTY_DSP_TP_CD";

    /** DB COLUMN :SER_APVL_REQ_FLG */
    String COL_SER_APVL_REQ_FLG  = "SER_APVL_REQ_FLG";

    /** DB COLUMN :FROM_LOC_NM_01 */
    String COL_FROM_LOC_NM_01  = "FROM_LOC_NM_01";

    /** DB COLUMN :FROM_LOC_NM_02 */
    String COL_FROM_LOC_NM_02  = "FROM_LOC_NM_02";

    /** DB COLUMN :FROM_LOC_ADDR_01 */
    String COL_FROM_LOC_ADDR_01  = "FROM_LOC_ADDR_01";

    /** DB COLUMN :FROM_LOC_ADDR_02 */
    String COL_FROM_LOC_ADDR_02  = "FROM_LOC_ADDR_02";

    /** DB COLUMN :FROM_LOC_ADDR_03 */
    String COL_FROM_LOC_ADDR_03  = "FROM_LOC_ADDR_03";

    /** DB COLUMN :FROM_LOC_ADDR_04 */
    String COL_FROM_LOC_ADDR_04  = "FROM_LOC_ADDR_04";

    /** DB COLUMN :FROM_LOC_CTY_ADDR */
    String COL_FROM_LOC_CTY_ADDR  = "FROM_LOC_CTY_ADDR";

    /** DB COLUMN :FROM_LOC_ST_CD */
    String COL_FROM_LOC_ST_CD  = "FROM_LOC_ST_CD";

    /** DB COLUMN :FROM_LOC_POST_CD */
    String COL_FROM_LOC_POST_CD  = "FROM_LOC_POST_CD";

    /** DB COLUMN :FROM_LOC_CTRY_CD */
    String COL_FROM_LOC_CTRY_CD  = "FROM_LOC_CTRY_CD";

    /** DB COLUMN :FROM_LOC_PSN_NM */
    String COL_FROM_LOC_PSN_NM  = "FROM_LOC_PSN_NM";

    /** DB COLUMN :FROM_LOC_TEL_NUM */
    String COL_FROM_LOC_TEL_NUM  = "FROM_LOC_TEL_NUM";

    /** DB COLUMN :SRC_ORD_NUM */
    String COL_SRC_ORD_NUM  = "SRC_ORD_NUM";

    /** Value of ORD_TP_TXT : PO */
    String VAL_ORD_TP_TXT_PFX_PO = "PO";

    /** Value of PRINT_SWTH_CD : P */
    String VAL_PRINT_SWTH_CD_P = "P";

    /** Value of SEGMENT_ID : 1 */
    BigDecimal VAL_SEGMENT_ID_1 = BigDecimal.ONE;

    /** Value of WMS_DROP_STS : 0 */
    String VAL_NOT_DROP = "0";

    /** Value of INTFC_TP_ID : 01 */
    String VAL_INTFC_TP_ID_01 = "01";

    /** Value of WMS_INTFC_REC_ID : 01 */
    String VAL_WMS_INTFC_REC_ID_01 = "01";

    /** Value of WMS_INTFC_REC_ID : 02 */
    String VAL_WMS_INTFC_REC_ID_02 = "02";

    /** Value of WMS_INTFC_REC_ID : 03 */
    String VAL_WMS_INTFC_REC_ID_03 = "03";

    /** Value of WMS_INTFC_REC_ID : 04 */
    String VAL_WMS_INTFC_REC_ID_04 = "04";

    /** Value of WMS_INTFC_REC_ID : 05 */
    String VAL_WMS_INTFC_REC_ID_05 = "05";

    /** Value of WMS_INTFC_REC_ID : 06 */
    String VAL_WMS_INTFC_REC_ID_06 = "06";

    // QC#21166
    /** Value of WMS_INTFC_REC_ID : 07 */
    String VAL_WMS_INTFC_REC_ID_07 = "07";

    /** Value of ORD_CMNT_SQ_TXT : 10 */
    String VAL_ORD_CMNT_SQ_TXT_10 = "10";

    /** Value of ORD_CMNT_SQ_TXT */
    String VAL_ORD_CMNT_SQ_TXT_INV = "5";

    /** Value of ORD_CMNT_SQ_TXT */
    String VAL_ORD_CMNT_SQ_TXT_DO = "6";

    /** Value of ORD_CMNT_SQ_TXT */
    String VAL_ORD_CMNT_SQ_TXT_CSE = "7";

    /** Value of ORD_CMNT_TXT */
    String VAL_ORD_CMNT_TXT_PFX_VESL = "[Vessel] ";

    /** Value of ORD_CMNT_TXT */
    String VAL_ORD_CMNT_TXT_PFX_INV = "[Invoice] ";

    /** Value of ORD_CMNT_TXT */
    String VAL_ORD_CMNT_TXT_PFX_DO = "[DO] ";

    /** Value of ORD_CMNT_TXT */
    String VAL_ORD_CMNT_TXT_PFX_CSE = "[Case] from ";

    /** Value of ORD_CMNT_TXT */
    String VAL_ORD_CMNT_TXT_CON_CSE = " to ";

    /** Value of ORD_LINE_TXT : 1 */
    String VAL_ORD_LINE_TXT_1 = "1";

    /** Size of ORD_CMNT_TXT : 40 */
    int VAL_ORD_CMNT_TXT_SIZE = 40;

    /** Value of ORD_LINE_TXT : 1000 */
    BigDecimal VAL_ORD_LINE_TXT_MUL = new BigDecimal(1000);

    /** Value of Process Type RWS Download */
    String VAL_PROC_TP_RWS = "1";

    /** Value of Process Type Item Download */
    String VAL_PROC_TP_ITEM = "2";

    /** Value of INTFC_REC_TP_ID : Header */
    String VAL_INTFC_REC_TP_ID_HDR = "1";

    /** Value of INTFC_REC_TP_ID : Detail */
    String VAL_INTFC_REC_TP_ID_DTL = "2";

    /** Value of INTFC_REC_TP_ID : Vendor */
    String VAL_INTFC_REC_TP_ID_VND = "3";

    /** Value of WMS_CMPY_CD */
    String VAL_WMS_CMPY_CD_02 = "02";

    /** Value of RWS_TO_WMS_DATA_TXT : 700 */
    int VAL_RWS_TO_WMS_DATA_TXT_SIZE = 700;

    /** Value : 0.1 */
    BigDecimal VAL_BIGDECIMAL_0_1 = new BigDecimal(0.1);

    /** Value : 0.1 */
    String VAL_STR_0_1 = "0.1";

    /** Value : 0.001 */
    BigDecimal VAL_BIGDECIMAL_0_001 = new BigDecimal(0.001);

    /** Value : 0.001 */
    String VAL_STR_0_001 = "0.001";

    /** Value : Length */
    int VAL_LEN_2 = 2;

    /** Value : Length */
    int VAL_LEN_3 = 3;

    /** Value : Length */
    int VAL_LEN_4 = 4;

    /** Size of WMS_BASE_UOM_QTY number of decimal places : 3 */
    int WMS_BASE_UOM_QTY_NUM_OF_DECL_PLACE = 3;

    /** Value Blank */
    String VAL_BLANK = " ";

    /** Value / */
    String VAL_SLASH = "/";

    /** Value ' */
    String VAL_APOS = "'";

    /** Value . */
    String VAL_PERIOD = ".";

    /** Value 000000 */
    String VAL_000000 = "000000";

    /** Value 1 */
    String VAL_ONE = "1";

    /** Value 2 */
    String VAL_TWO = "2";

    /** Format of SYSDATE : yyyyMMddHHmmss */
    String FMT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /** Format of SYSDATE : yyyy-MM-dd 00:00:00 */
    String FMT_YYYYMMDD000000 = "yyyy-MM-dd 00:00:00";

    /** Time Stamp Format */
    String FMT_HHMMSSSSS = "HHmmssSSS";

    /** Format of Number : 0 */
    String FMT_0 = "0";

    /** Format of Number : 0.0 */
    String FMT_0_0 = "0.0";

    /** Format of Number : 0.S */
    String FMT_0_S = "0.#";

    /** Format of Number : 0.000 */
    String FMT_0_000 = "0.000";

    /** Value of ITEM_UOM_CD_TXT */
    String VAL_ITEM_UOM_CD_TXT_EA = "EA";

    /** Default Value of INVTY_CATG_COUNT_CD */
    String VAL_INVTY_CATG_COUNT_CD_DEF = "D";

    /** Default Value of WMS_INV_IND */
    String VAL_WMS_INV_IND = "S";

    /** Default Value of WMS_RESRC_TXT */
    String VAL_WMS_RESRC_TXT_DEF = "S21 Merchandise";

    /** Default Value of LOT_ENTRY_OPT_CD */
    String VAL_DEF_LOT_ENTRY_OPT_CD = "R";

    /** Default Value of EXPR_LT_NUM */
    BigDecimal VAL_DEF_EXPR_LT_NUM = new BigDecimal(60);

    /** Default Value of SHELF_LIFE_NUM */
    BigDecimal VAL_DEF_SHELF_LIFE_NUM = BigDecimal.ZERO;

    /** Default Value of DEF_EXPR_PER_FLG */
    String VAL_DEF_DEF_EXPR_PER_FLG = ZYPConstant.FLG_OFF_N;

    /** Default Value of CONSL_AGE_LIMIT_NUM */
    BigDecimal VAL_DEF_CONSL_AGE_LIMIT_NUM = BigDecimal.ZERO;

    /** Length of HAZ_CLS_NM */
    int VAL_HAZ_CLS_NM_SIZE = 25;

    /** Length of VND_NM */
    int VAL_VND_NM_SIZE = 35;

    /** Substring start position for STK_STS_CD */
    int LG_CUT_STK_STS_CD_FROM = 1;

    /** Substring end position for STK_STS_CD */
    int LG_CUT_STK_STS_CD_TO = 2;

    /** Value of Prefix of HAZ_ID_NUM */
    String VAL_HAZ_ID_NUN_PFX = "UN";

    /** Value of Prefix of NMFC_ITEM_SUB_NUM */
    String VAL_NMFC_ITEM_SUB_NUM_PFC = "SUB";

    /* HDR */

    /** Substring start position for WH_CD */
    int LG_CUT_WH_CD_FROM = 0;

    /** Substring end position for WH_CD */
    int LG_CUT_WH_CD_TO = 4;

    /** Substring start position for WMS_SQ_NUM */
    int LG_CUT_WMS_SQ_NUM_FROM = 4;

    /** Substring end position for WMS_SQ_NUM */
    int LG_CUT_WMS_SQ_NUM_TO = 11;

    /** Substring start position for INTFC_TP_ID */
    int LG_CUT_INTFC_TP_ID_FROM = 11;

    /** Substring end position for INTFC_TP_ID */
    int LG_CUT_INTFC_TP_ID_TO = 13;

    /** Substring start position for INTFC_REC_TP_ID */
    int LG_CUT_INTFC_REC_TP_ID_FROM = 13;

    /** Substring end position for INTFC_REC_TP_ID */
    int LG_CUT_INTFC_REC_TP_ID_TO = 14;

    /** Substring start position for INTFC_REC_ACT_CD */
    int LG_CUT_INTFC_REC_ACT_CD_FROM = 14;

    /** Substring end position for INTFC_REC_ACT_CD */
    int LG_CUT_INTFC_REC_ACT_CD_TO = 15;

    /** Substring start position for WMS_CMPY_CD */
    int LG_CUT_WMS_CMPY_CD_FROM = 15;

    /** Substring end position for WMS_CMPY_CD */
    int LG_CUT_WMS_CMPY_CD_TO = 17;

    /** Substring start position for WMS_PO_ID */
    int LG_CUT_WMS_PO_ID_FROM = 21;

    /** Substring end position for WMS_PO_ID */
    int LG_CUT_WMS_PO_ID_TO = 36;

    /** Substring start position for VND_CD */
    int LG_CUT_HDR_VND_CD_FROM = 36;

    /** Substring end position for VND_CD */
    int LG_CUT_HDR_VND_CD_TO = 46;

    /** Substring start position for WMS_PRCH_TP_CD */
    int LG_CUT_HDR_WMS_PRCH_TP_CD_FROM = 46;

    /** Substring end position for WMS_PRCH_TP_CD */
    int LG_CUT_HDR_WMS_PRCH_TP_CD_TO = 47;

    /** Substring start position for WMS_TRX_CD */
    int LG_CUT_HDR_WMS_TRX_CD_FROM = 47;

    /** Substring end position for WMS_TRX_CD */
    int LG_CUT_HDR_WMS_TRX_CD_TO = 49;

    /** Substring start position for WMS_PO_STS_CD */
    int LG_CUT_HDR_WMS_PO_STS_CD_FROM = 49;

    /** Substring end position for WMS_PO_STS_CD */
    int LG_CUT_HDR_WMS_PO_STS_CD_TO = 50;

    /** Substring start position for PO_FROM_DT_TM_TS */
    int LG_CUT_HDR_PO_FROM_DT_TM_TS_FROM = 50;

    /** Substring end position for PO_FROM_DT_TM_TS */
    int LG_CUT_HDR_PO_FROM_DT_TM_TS_TO = 60;

    /** Substring start position for PO_USER_ID */
    int LG_CUT_HDR_PO_USER_ID_FROM = 60;

    /** Substring end position for PO_USER_ID */
    int LG_CUT_HDR_PO_USER_ID_TO = 68;

    /** Substring start position for PRINT_SWTH_CD */
    int LG_CUT_HDR_PRINT_SWTH_CD_FROM = 68;

    /** Substring end position for PRINT_SWTH_CD */
    int LG_CUT_HDR_PRINT_SWTH_CD_TO = 69;

    /** Substring start position for WMS_VESL_NM */
    int LG_CUT_HDR_WMS_VESL_NM_FROM = 69;

    /** Substring end position for WMS_VESL_NM */
    int LG_CUT_HDR_WMS_VESL_NM_TO = 99;

    /** Substring start position for WMS_BOL_NUM */
    int LG_CUT_HDR_WMS_BOL_NUM_FROM = 99;

    /** Substring end position for WMS_BOL_NUM */
    int LG_CUT_HDR_WMS_BOL_NUM_TO = 119;

    /** Substring start position for INBD_PO_MSG_TXT_01 */
    int LG_CUT_HDR_INBD_PO_MSG_TXT_01_FROM = 119;

    /** Substring end position for INBD_PO_MSG_TXT_01 */
    int LG_CUT_HDR_INBD_PO_MSG_TXT_01_TO = 184;

    /** Substring start position for INBD_PO_MSG_TXT_02 */
    int LG_CUT_HDR_INBD_PO_MSG_TXT_02_FROM = 184;

    /** Substring end position for INBD_PO_MSG_TXT_02 */
    int LG_CUT_HDR_INBD_PO_MSG_TXT_02_TO = 249;

    /** Substring start position for INBD_PO_MSG_TXT_03 */
    int LG_CUT_HDR_INBD_PO_MSG_TXT_03_FROM = 249;

    /** Substring end position for INBD_PO_MSG_TXT_03 */
    int LG_CUT_HDR_INBD_PO_MSG_TXT_03_TO = 314;

    /** Substring start position for INBD_PO_MSG_TXT_04 */
    int LG_CUT_HDR_INBD_PO_MSG_TXT_04_FROM = 314;

    /** Substring end position for INBD_PO_MSG_TXT_04 */
    int LG_CUT_HDR_INBD_PO_MSG_TXT_04_TO = 379;

    /** Substring start position for WMS_SER_NUM */
    int LG_CUT_HDR_WMS_SER_NUM_FROM = 379;

    /** Substring end position for WMS_SER_NUM */
    int LG_CUT_HDR_WMS_SER_NUM_TO = 394;

    /* DTL */

    /** Substring start position for RTL_SWH_CD */
    int LG_CUT_DTL_RTL_SWH_CD_FROM = 17;

    /** Substring end position for RTL_SWH_CD */
    int LG_CUT_DTL_RTL_SWH_CD_TO = 21;

    /** Substring start position for WMS_LINE_NUM */
    int LG_CUT_DTL_WMS_LINE_NUM_FROM = 36;

    /** Substring end position for WMS_LINE_NUM */
    int LG_CUT_DTL_WMS_LINE_NUM_TO = 41;

    /** Substring start position for WMS_MDSE_CD */
    int LG_CUT_DTL_WMS_MDSE_CD_FROM = 41;

    /** Substring end position for WMS_MDSE_CD */
    int LG_CUT_DTL_WMS_MDSE_CD_TO = 66;

    /** Substring start position for S80_STK_STS_CD */
    int LG_CUT_DTL_S80_STK_STS_CD_FROM = 66;

    /** Substring end position for S80_STK_STS_CD */
    int LG_CUT_DTL_S80_STK_STS_CD_TO = 68;

    /** Substring start position for WMS_LINE_STS_CD */
    int LG_CUT_DTL_WMS_LINE_STS_CD_FROM = 68;

    /** Substring end position for WMS_LINE_STS_CD */
    int LG_CUT_DTL_WMS_LINE_STS_CD_TO = 69;

    /** Substring start position for WMS_INV_IND */
    int LG_CUT_DTL_WMS_INV_IND_FROM = 69;

    /** Substring end position for WMS_INV_IND */
    int LG_CUT_DTL_WMS_INV_IND_TO = 70;

    /** Substring start position for WMS_OPEN_QTY */
    int LG_CUT_DTL_WMS_OPEN_QTY_FROM = 70;

    /** Substring end position for WMS_OPEN_QTY */
    int LG_CUT_DTL_WMS_OPEN_QTY_TO = 79;

    /** Substring start position for WMS_EST_DT_TM_TS */
    int LG_CUT_DTL_WMS_EST_DT_TM_TS_FROM = 79;

    /** Substring end position for WMS_EST_DT_TM_TS */
    int LG_CUT_DTL_WMS_EST_DT_TM_TS_TO = 89;

    /** Substring start position for WMS_INV_ID */
    int LG_CUT_DTL_WMS_INV_ID_FROM = 89;

    /** Substring end position for WMS_INV_ID */
    int LG_CUT_DTL_WMS_INV_ID_TO = 104;

    /** Substring start position for WMS_DO_ID */
    int LG_CUT_DTL_WMS_DO_ID_FROM = 104;

    /** Substring end position for WMS_DO_ID */
    int LG_CUT_DTL_WMS_DO_ID_TO = 119;

    /** Substring start position for CSE_FROM_NUM */
    int LG_CUT_DTL_CSE_FROM_NUM_FROM = 119;

    /** Substring end position for CSE_FROM_NUM */
    int LG_CUT_DTL_CSE_FROM_NUM_TO = 124;

    /** Substring start position for CSE_TO_NUM */
    int LG_CUT_DTL_CSE_TO_NUM_FROM = 124;

    /** Substring end position for CSE_TO_NUM */
    int LG_CUT_DTL_CSE_TO_NUM_TO = 129;

    /** Substring start position for WMS_BAT_NUM */
    int LG_CUT_DTL_WMS_BAT_NUM_FROM = 129;

    /** Substring end position for WMS_BAT_NUM */
    int LG_CUT_DTL_WMS_BAT_NUM_TO = 139;

    /** Substring start position for WMS_COLLO_NUM */
    int LG_CUT_DTL_WMS_COLLO_NUM_FROM = 139;

    /** Substring end position for WMS_COLLO_NUM */
    int LG_CUT_DTL_WMS_COLLO_NUM_TO = 164;

    /* VND */

    /** Substring start position for VND_CD */
    int LG_CUT_VND_VND_CD_FROM = 36;

    /** Substring end position for VND_CD */
    int LG_CUT_VND_VND_CD_TO = 46;

    /** Substring start position for WMS_VND_NM_01 */
    int LG_CUT_VND_WMS_VND_NM_01_FROM = 46;

    /** Substring end position for WMS_VND_NM_01 */
    int LG_CUT_VND_WMS_VND_NM_01_TO = 81;

    /** Substring start position for WMS_VND_NM_02 */
    int LG_CUT_VND_WMS_VND_NM_02_FROM = 81;

    /** Substring end position for WMS_VND_NM_02 */
    int LG_CUT_VND_WMS_VND_NM_02_TO = 116;

    /** Substring start position for FIRST_LINE_ADDR */
    int LG_CUT_VND_FIRST_LINE_ADDR_FROM = 116;

    /** Substring end position for FIRST_LINE_ADDR */
    int LG_CUT_VND_FIRST_LINE_ADDR_TO = 151;

    /** Substring start position for SCD_LINE_ADDR */
    int LG_CUT_VND_SCD_LINE_ADDR_FROM = 151;

    /** Substring end position for SCD_LINE_ADDR */
    int LG_CUT_VND_SCD_LINE_ADDR_TO = 186;

    /** Substring start position for THIRD_LINE_ADDR */
    int LG_CUT_VND_THIRD_LINE_ADDR_FROM = 186;

    /** Substring end position for THIRD_LINE_ADDR */
    int LG_CUT_VND_THIRD_LINE_ADDR_TO = 221;

    /** Substring start position for FRTH_LINE_ADDR */
    int LG_CUT_VND_FRTH_LINE_ADDR_FROM = 221;

    /** Substring end position for FRTH_LINE_ADDR */
    int LG_CUT_VND_FRTH_LINE_ADDR_TO = 256;

    /** Substring start position for CTY_ADDR */
    int LG_CUT_VND_CTY_ADDR_FROM = 256;

    /** Substring end position for CTY_ADDR */
    int LG_CUT_VND_CTY_ADDR_TO = 276;

    /** Substring start position for ST_CD */
    int LG_CUT_VND_ST_CD_FROM = 276;

    /** Substring end position for ST_CD */
    int LG_CUT_VND_ST_CD_TO = 278;

    /** Substring start position for POST_CD */
    int LG_CUT_VND_POST_CD_FROM = 278;

    /** Substring end position for POST_CD */
    int LG_CUT_VND_POST_CD_TO = 293;

    /** Substring start position for CTRY_CD */
    int LG_CUT_VND_CTRY_CD_FROM = 293;

    /** Substring end position for CTRY_CD */
    int LG_CUT_VND_CTRY_CD_TO = 295;

    /** Substring start position for VND_TO_CTAC_NM */
    int LG_CUT_VND_VND_TO_CTAC_NM_FROM = 295;

    /** Substring end position for VND_TO_CTAC_NM */
    int LG_CUT_VND_VND_TO_CTAC_NM_TO = 320;

    /** Substring start position for VND_TO_CTAC_NUM */
    int LG_CUT_VND_VND_TO_CTAC_NUM_FROM = 320;

    /** Substring end position for VND_TO_CTAC_NUM */
    int LG_CUT_VND_VND_TO_CTAC_NUM_TO = 335;

    /** DB Column Attribute: curStdCostNum */
    String ATT_CUR_STD_COST_NUM = "curStdCostNum";

    /** Value PADING */
    String VAL_PADING = "9";

    /** Value PADING_SCALE */
    String VAL_PADING_SCALE = ".99";

    /** Value . */
    String VAL_ESC_PERIOD = "\\.";

    /** VAR_CHAR_CONST_NM: NLGB0020_CRAT_SET_THIRDPTYDSP . */
    String NLGB0020_CRAT_SET_THIRDPTYDSP = "NLGB0020_CRAT_SET_THIRDPTYDSP";

    /** CONV_SCE_ORD_TP_CD */
    String CONV_SCE_ORD_TP_CD = "CONV_SCE_ORD_TP_CD";

    /** CONV_STK_STS_CD */
    String CONV_STK_STS_CD = "CONV_STK_STS_CD";
}
