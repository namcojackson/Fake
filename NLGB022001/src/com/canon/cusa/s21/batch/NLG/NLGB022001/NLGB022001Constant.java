/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB022001;

import java.math.BigDecimal;

/**
 * <pre>
 * Batch Program Constant Class for RWS and Item Download for DBS
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/29/2016   CITS            N.Akaishi       Create          V1.0
 * 01/18/2017   CITS            Y.Fujii         Update          QC#17074
 * 01/30/2017   CITS            R.Shimamoto     Update          QC#17315
 * 06/29/2017   CITS            K.Ogino         Update          QC#19634
 *</pre>
 */
public class NLGB022001Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NLGB0220";

    /** Output Log Program ID */
    public static final String PROGRAM_ID = "NLGB022001:";

    /** Prameter Name: GLBL_CMPY_CD */
    public static final String PRAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Prameter Name: WH_GRP_CD */
    public static final String PRAM_NM_WH_GRP_CD = "Warehouse Group Code";

    /** Prameter Name: Process Type */
    public static final String PRAM_NM_PROC_TP = "Process Type";

    /**
     * The record cannot be registered. Table Name: [@], Field Name: [@], Key Value: [@]
     */
    public static final String NLGM0007E = "NLGM0007E";

    /**
     * The record cannot be updated. Table Name: [@], Key Field Name: [@], Key Value: [@]
     */
    public static final String NLGM0008E = "NLGM0008E";

    /**
     * The corresponding data does not exist. Table Name : [@], Key
     * Field Name: [@], Key Value: [@]
     */
    public static final String NLGM0044E = "NLGM0044E";

    /**
     * The record cannot be registered. Registration Table Name: [@],
     * Table Name: [@], Key Field Name: [@], Key Value: [@]
     */
    public static final String NLGM0045E = "NLGM0045E";

    /**
     * The record cannot be delete. Table Name: [@], Key Field Name: [@], Key Value: [@]
     */
    public static final String NLGM0040E = "NLGM0040E";

    /**
     * The record cannot be updated. Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    public static final String NLGM0046E = "NLGM0046E";

    /**
     * Warehouse code to be processed is not set. Please check the WMS
     * warehouse table. MW_REPL_TRGT_GRP_CD: [@]
     */
    public static final String NLGM0047E = "NLGM0047E";

    /**
     * [@] Parameter has not been set.
     */
    public static final String NLGM0049E = "NLGM0049E";

    /**
     * Column: [@], Value: [@] is an invalid date. Table Name: [@],
     * Key Field Name: [@], Key Value: [@]
     */
    public static final String NLGM0052E = "NLGM0052E";

    /** [@] is an invalid parameter. Parameter Name: [@] */
    public static final String NLGM0060E = "NLGM0060E";

    /**
     * The corresponding data does not exist. Table Name : [@],
     * Key Field Name:  [@], Key Value: [@]
     */
    public static final String NLAM1001E = "NLAM1001E";

    /** DB Table: WMS_INBD_RWS_WRK */
    public static final String TBL_WMS_INBD_RWS_WRK = "WMS_INBD_RWS_WRK";

    /** DB Table: WMS_INBD_PO_HDR */
    public static final String TBL_WMS_INBD_PO_HDR = "WMS_INBD_PO_HDR";

    /** DB Table: WMS_INBD_PO_DTL */
    public static final String TBL_WMS_INBD_PO_DTL = "WMS_INBD_PO_DTL";

    /** DB Table: WMS_INBD_PO_VND */
    public static final String TBL_WMS_INBD_PO_VND = "WMS_INBD_PO_VND";

    /** DB Table: NLGI2200_01 */
    public static final String TBL_NLGI2200_01 = "NLGI2200_01";

    /** DB Table: NLGI2200_02 */
    public static final String TBL_NLGI2200_02 = "NLGI2200_02";

    /** DB Table: NLGI3200_01 */
    public static final String TBL_NLGI3200_01 = "NLGI3200_01";

    /** DB Table: NLGI3200_02 */
    public static final String TBL_NLGI3200_02 = "NLGI3200_02";

    /** DB Table: WMS_INBD_MDSE */
    public static final String TBL_WMS_INBD_MDSE = "WMS_INBD_MDSE";

    /** DB Table: WMS_INBD_MDSE_UPC */
    public static final String TBL_WMS_INBD_MDSE_UPC = "WMS_INBD_MDSE_UPC";

    /** DB Table: WMS_INBD_MDSE_SER */
    public static final String TBL_WMS_INBD_MDSE_SER = "WMS_INBD_MDSE_SER";

    /** DB Table: WMS_INBD_ITEM_WRK */
    public static final String TBL_WMS_INBD_ITEM_WRK = "WMS_INBD_ITEM_WRK";

    /** DB Table: WMS_INBD_ITEM_UPC_WRK */
    public static final String TBL_WMS_INBD_ITEM_UPC_WRK = "WMS_INBD_ITEM_UPC_WRK";

    /** DB Table: WMS_INBD_ITEM_SER_WRK */
    public static final String TBL_WMS_INBD_ITEM_SER_WRK = "WMS_INBD_ITEM_SER_WRK";

    /** DB Table: WMS_MDSE_LIST */
    public static final String TBL_WMS_MDSE_LIST = "WMS_MDSE_LIST";

    /** DB Table: WMS_INTFC_CTRL */
    public static final String TBL_WMS_INTFC_CTRL = "WMS_INTFC_CTRL";

    /** DB Table: CMPY_CD_CONV */
    public static final String TBL_CMPY_CD_CONV = "CMPY_CD_CONV";

    /** DB Table: SCE_ORD_TP */
    public static final String TBL_SCE_ORD_TP = "SCE_ORD_TP";

    /** DB Table: STS_STK_CONV */
    public static final String TBL_STS_STK_CONV = "STS_STK_CONV";

    /** DB Table: RWS_HDR */
    public static final String TBL_RWS_HDR = "RWS_HDR";

    /** DB Table: RWS_DTL */
    public static final String TBL_RWS_DTL = "RWS_DTL";

    /** DB Table: WMS_WH */
    public static final String TBL_WMS_WH = "WMS_WH";

    /** DB Table: ABC_ANLS_RSLT */
    public static final String TBL_ABC_ANLS_RSLT = "ABC_ANLS_RSLT";

    /** DB Table: SHPG_PLN */
    public static final String TBL_SHPG_PLN = "SHPG_PLN";

    /** DB Table: MRP_INFO */
    public static final String TBL_MRP_INFO = "MRP_INFO";

    /** DB Table: PO */
    public static final String TBL_PO = "PO";

    /** DB Column: GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB Column: WMS_PO_DNLD_INTFC_ID */
    public static final String COL_WMS_PO_DNLD_INTFC_ID = "WMS_PO_DNLD_INTFC_ID";

    /** DB Column: WMS_ITEM_DNLD_INTFC_ID */
    public static final String COL_WMS_ITEM_DNLD_INTFC_ID = "WMS_ITEM_DNLD_INTFC_ID";

    /** DB Column: RWS_TO_WMS_DATA_TXT */
    public static final String COL_RWS_TO_WMS_DATA_TXT = "RWS_TO_WMS_DATA_TXT";

    /** DB Column: WMS_WH_CD */
    public static final String COL_WMS_WH_CD = "WMS_WH_CD";

    /** DB Column: WMS_CMPY_CD */
    public static final String COL_WMS_CMPY_CD = "WMS_CMPY_CD";

    /** DB Column: WMS_SQ_NUM */
    public static final String COL_WMS_SQ_NUM = "WMS_SQ_NUM";

    /** DB Column: MDSE_CD */
    public static final String COL_MDSE_CD = "MDSE_CD";

    /** DB Column: WMS_MDSE_CD */
    public static final String COL_WMS_MDSE_CD = "WMS_MDSE_CD";

    /** DB Column: WMS_ITEM_CD */
    public static final String COL_WMS_ITEM_CD = "WMS_ITEM_CD";

    /** DB Column: WMS_INBD_RWS_WRK_PK */
    public static final String COL_WMS_INBD_RWS_WRK_PK = "WMS_INBD_RWS_WRK_PK";

    /** DB Column: PO_NUM */
    public static final String COL_PO_NUM = "PO_NUM";

    /** DB Column: WMS_EST_DT_TM_TS */
    public static final String COL_WMS_EST_DT_TM_TS = "WMS_EST_DT_TM_TS";

    /** DB Column: PO_FROM_DT_TM_TS */
    public static final String COL_PO_FROM_DT_TM_TS = "PO_FROM_DT_TM_TS";

    /** DB Column: WMS_INBD_ITEM_WRK_PK */
    public static final String COL_WMS_INBD_ITEM_WRK_PK = "WMS_INBD_ITEM_WRK_PK";

    /** DB Column: WMS_INBD_ITEM_UPC_WRK_PK */
    public static final String COL_WMS_INBD_ITEM_UPC_WRK_PK = "WMS_INBD_ITEM_UPC_WRK_PK";

    /** DB Column: WMS_INBD_ITEM_SER_WRK_PK */
    public static final String COL_WMS_INBD_ITEM_SER_WRK_PK = "WMS_INBD_ITEM_SER_WRK_PK";

    /** DB Column: LAST_INTFC_MDSE_UPD_TS */
    public static final String COL_LAST_INTFC_MDSE_UPD_TS = "LAST_INTFC_MDSE_UPD_TS";

    /** DB Column: RTL_WH_CD */
    public static final String COL_RTL_WH_CD = "RTL_WH_CD";

    /** DB Column: WMS_DESC_SHORT_NM */
    public static final String COL_WMS_DESC_SHORT_NM = "WMS_DESC_SHORT_NM";

    /** DB Column: SER_NUM */
    public static final String COL_SER_NUM = "SER_NUM";

    /** DB Column: RWS_NUM */
    public static final String COL_RWS_NUM = "RWS_NUM";

    /** DB Column: RWS_TO_WMS_DTL_LINE_NUM */
    public static final String COL_RWS_TO_WMS_DTL_LINE_NUM = "RWS_TO_WMS_DTL_LINE_NUM";

    /** DB Column: ABC_ANLS_CLS_TAG_CD */
    public static final String COL_ABC_ANLS_CLS_TAG_CD = "ABC_ANLS_CLS_TAG_CD";

    /** DB Column: CYCLE_CNT_FREQ_DAYS_AOT */
    public static final String COL_CYCLE_CNT_FREQ_DAYS_AOT = "CYCLE_CNT_FREQ_DAYS_AOT";

    /** DB COLUMN :RWS_REF_NUM */
    public static final String COL_RWS_REF_NUM  = "RWS_REF_NUM";

    /** DB COLUMN :FROM_LOC_CD */
    public static final String COL_FROM_LOC_CD  = "FROM_LOC_CD";

    /** DB COLUMN :WMS_PRCH_TP_CD */
    public static final String COL_WMS_PRCH_TP_CD  = "WMS_PRCH_TP_CD";

    /** DB COLUMN :WMS_TRX_CD */
    public static final String COL_WMS_TRX_CD  = "WMS_TRX_CD";

    /** DB COLUMN :IMPT_INV_VESL_NM */
    public static final String COL_IMPT_INV_VESL_NM  = "IMPT_INV_VESL_NM";

    /** DB COLUMN :IMPT_INV_BOL_NUM */
    public static final String COL_IMPT_INV_BOL_NUM  = "IMPT_INV_BOL_NUM";

    /** DB COLUMN :WH_CD */
    public static final String COL_WH_CD  = "WH_CD";

    /** DB COLUMN :SCE_ORD_TP_CD */
    public static final String COL_SCE_ORD_TP_CD  = "SCE_ORD_TP_CD";

    /** DB COLUMN :SVC_CONFIG_MSTR_PK */
    public static final String COL_SVC_CONFIG_MSTR_PK  = "SVC_CONFIG_MSTR_PK";

    /** DB COLUMN :S80_STK_STS_CD */
    public static final String COL_S80_STK_STS_CD  = "S80_STK_STS_CD";

    /** DB COLUMN :INVTY_STK_STS_CD */
    public static final String COL_INVTY_STK_STS_CD  = "INVTY_STK_STS_CD";

    /** DB COLUMN :RWS_DTL_LINE_NUM */
    public static final String COL_RWS_DTL_LINE_NUM  = "RWS_DTL_LINE_NUM";

    /** DB COLUMN :RWS_QTY */
    public static final String COL_RWS_QTY  = "RWS_QTY";

    /** DB COLUMN :WH_IN_ETA_DT */
    public static final String COL_WH_IN_ETA_DT  = "WH_IN_ETA_DT";

    /** DB COLUMN :IMPT_INV_NUM */
    public static final String COL_IMPT_INV_NUM  = "IMPT_INV_NUM";

    /** DB COLUMN :IMPT_INV_DO_NUM */
    public static final String COL_IMPT_INV_DO_NUM  = "IMPT_INV_DO_NUM";

    /** DB COLUMN :OUT_PACK_FROM_CSE_NUM */
    public static final String COL_OUT_PACK_FROM_CSE_NUM  = "OUT_PACK_FROM_CSE_NUM";

    /** DB COLUMN :OUT_PACK_TO_CSE_NUM */
    public static final String COL_OUT_PACK_TO_CSE_NUM  = "OUT_PACK_TO_CSE_NUM";

    /** DB COLUMN :RTL_SWH_CD */
    public static final String COL_RTL_SWH_CD  = "RTL_SWH_CD";

    /** DB COLUMN :THIRD_PTY_DSP_TP_CD */
    public static final String COL_THIRD_PTY_DSP_TP_CD  = "THIRD_PTY_DSP_TP_CD";

    /** DB COLUMN :SER_APVL_REQ_FLG */
    public static final String COL_SER_APVL_REQ_FLG  = "SER_APVL_REQ_FLG";

    /** DB COLUMN :FROM_LOC_NM_01 */
    public static final String COL_FROM_LOC_NM_01  = "FROM_LOC_NM_01";

    /** DB COLUMN :FROM_LOC_NM_02 */
    public static final String COL_FROM_LOC_NM_02  = "FROM_LOC_NM_02";

    /** DB COLUMN :FROM_LOC_ADDR_01 */
    public static final String COL_FROM_LOC_ADDR_01  = "FROM_LOC_ADDR_01";

    /** DB COLUMN :FROM_LOC_ADDR_02 */
    public static final String COL_FROM_LOC_ADDR_02  = "FROM_LOC_ADDR_02";

    /** DB COLUMN :FROM_LOC_ADDR_03 */
    public static final String COL_FROM_LOC_ADDR_03  = "FROM_LOC_ADDR_03";

    /** DB COLUMN :FROM_LOC_ADDR_04 */
    public static final String COL_FROM_LOC_ADDR_04  = "FROM_LOC_ADDR_04";

    /** DB COLUMN :FROM_LOC_CTY_ADDR */
    public static final String COL_FROM_LOC_CTY_ADDR  = "FROM_LOC_CTY_ADDR";

    /** DB COLUMN :FROM_LOC_ST_CD */
    public static final String COL_FROM_LOC_ST_CD  = "FROM_LOC_ST_CD";

    /** DB COLUMN :FROM_LOC_POST_CD */
    public static final String COL_FROM_LOC_POST_CD  = "FROM_LOC_POST_CD";

    /** DB COLUMN :FROM_LOC_CTRY_CD */
    public static final String COL_FROM_LOC_CTRY_CD  = "FROM_LOC_CTRY_CD";

    /** DB COLUMN :FROM_LOC_PSN_NM */
    public static final String COL_FROM_LOC_PSN_NM  = "FROM_LOC_PSN_NM";

    /** DB COLUMN :FROM_LOC_TEL_NUM */
    public static final String COL_FROM_LOC_TEL_NUM  = "FROM_LOC_TEL_NUM";

    /** DB COLUMN :SRC_ORD_NUM */
    public static final String COL_SRC_ORD_NUM  = "SRC_ORD_NUM";

    /** WML_WH_CD */
    public static final String COL_WML_WH_CD = "WML_WH_CD";

    /** WML_MDSE_CD */
    public static final String COL_WML_MDSE_CD = "WML_MDSE_CD";

    /** ABC_ANLS_RSLT_PK */
    public static final String COL_ABC_ANLS_RSLT_PK = "ABC_ANLS_RSLT_PK";

    /** Value of ORD_TP_TXT : PO */
    public static final String VAL_ORD_TP_TXT_PFX_PO = "PO";

    /** Value of PRINT_SWTH_CD : P */
    public static final String VAL_PRINT_SWTH_CD_P = "P";

    /** Value of WMS_DROP_STS : 0 */
    public static final String VAL_NOT_DROP = "0";

    /** Value of INTFC_TP_ID : 01 */
    public static final String VAL_INTFC_TP_ID_01 = "01";

    /** Value of WMS_INTFC_REC_ID : 01 */
    public static final String VAL_WMS_INTFC_REC_ID_01 = "01";

    /** Value of WMS_INTFC_REC_ID : 02 */
    public static final String VAL_WMS_INTFC_REC_ID_02 = "02";

    /** Value of STK_STS_CD : S */
    public static final String VAL_STK_STS_CD_S = "S";

    /** Value of INTFC_REC_ACT_CD : A */
    public static final String VAL_INTFC_REC_ACT_CD_A = "A";

    /** Value of Process Type RWS Download */
    public static final String VAL_PROC_TP_RWS = "1";

    /** Value of Process Type Item Download */
    public static final String VAL_PROC_TP_ITEM = "2";

    /** Value of INTFC_REC_TP_ID : Header */
    public static final String VAL_INTFC_REC_TP_ID_HDR = "1";

    /** Value of INTFC_REC_TP_ID : Detail */
    public static final String VAL_INTFC_REC_TP_ID_DTL = "2";

    /** Value of INTFC_REC_TP_ID : Vendor */
    public static final String VAL_INTFC_REC_TP_ID_VND = "3";

    /** Value of WMS_CMPY_CD */
    public static final String VAL_WMS_CMPY_CD_02 = "02";

    /** Default Value of INVTY_CATG_COUNT_CD */
//    public static final String VAL_INVTY_CATG_COUNT_CD_DEF = "B";
    public static final String VAL_INVTY_CATG_COUNT_CD_DEF = "D";

    /** Default Value of WMS_RESRC_TXT */
    public static final String VAL_WMS_RESRC_TXT_DEF = "S21 Merchandise";

    /** Value of STAGE_ACT_CD : "1" */
    public static final String VAL_STAGE_ACT_CD_1 = "1";

    /** Value of STAGE_REC_STS_CD : "2" */
    public static final String VAL_STAGE_REC_STS_CD_2 = "2";

    /** Value of RWS_TO_WMS_DATA_TXT : 700 */
    public static final int VAL_RWS_TO_WMS_DATA_TXT_SIZE = 700;

    /** Value : 1 */
    public static final int VAL_INT_1 = 1;

    /** Value : 0.001 */
    public static final String VAL_0_1 = "0.1";

    /** Value : 0.1 */
    public static final BigDecimal VAL_BIGDECIMAL_0_1 = new BigDecimal(VAL_0_1);

    /** Value : "0.001" */
    public static final String VAL_0_001 = "0.001";

    /** Value : 0.001(bigDecimal) */
    public static final BigDecimal VAL_BIGDECIMAL_0_001 = new BigDecimal(VAL_0_001);

    /** Value : INTFC_TASK_NM : RWS */
    public static final String VAL_INTFC_TASK_NM_RWS = "RWS";

    /** Value : INTFC_TASK_NM : RWS */
    public static final String VAL_INTFC_TASK_NM_ITEM = "ITEM";

    /** Value : TPL_ITEM_TP_TXT : HAZMAT */
    public static final String VAL_TPL_ITEM_TP_TXT_HAZMAT = "HAZMAT";

    /** Value : TPL_ITEM_TP_TXT : PARTS */
    public static final String VAL_TPL_ITEM_TP_TXT_PARTS = "PARTS";

    /** Value : TPL_ITEM_TP_TXT : SUPPLIES */
    public static final String VAL_TPL_ITEM_TP_TXT_SUPPLIES = "SUPPLIES";

    /** Value : TPL_ITEM_TP_TXT : EQUIPMENT */
    public static final String VAL_TPL_ITEM_TP_TXT_EQUIPMENT = "EQUIPMENT";

    /** Value : TPL_ACTV_TXT : Active */
    public static final String VAL_TPL_ACTV_TXT_ACTIVE  = "Active";

    /** Value : TPL_ORD_TP_TXT : RMA */
    public static final String VAL_TPL_ORD_TP_TXT_RMA  = "RMA";

    /** Value : TPL_ORD_TP_TXT : TRF */
    public static final String VAL_TPL_ORD_TP_TXT_TRF  = "TRF";

    /** Value : TPL_ORD_TP_TXT : PO */
    public static final String VAL_TPL_ORD_TP_TXT_PO  = "PO";

    /** Value of INTFC_REC_ACT_CD : "U" */
    public static final String VAL_INTFC_REC_ACT_CD_U = "U";

    /** Value of TPL_STAGE_ACT_CD : "UPDATE" */
    public static final String VAL_TPL_STAGE_ACT_CD_UPDATE = "UPDATE";

    /** Value of TPL_STAGE_ACT_CD : "NEW" */
    public static final String VAL_TPL_STAGE_ACT_CD_NEW = "NEW";

    /** Value of MDSE_ITEM_TP_CD : "03" */
    public static final String VAL_MDSE_ITEM_TP_CD_03 = "03";

    /** Value of MDSE_ITEM_TP_CD : "04" */
    public static final String VAL_MDSE_ITEM_TP_CD_04 = "04";

    /** Value : Length */
    public static final int VAL_LEN_2 = 2;

    /** Value : Length */
    public static final int VAL_LEN_3 = 3;

    /** Value : Length */
    public static final int VAL_LEN_4 = 4;

    /** Value : space */
    public static final String VAL_SPACE = " ";

    /** Value . */
    public static final String VAL_ZERO = "0";

    /** Value Blank */
    public static final String VAL_BLANK = " ";

    /** Value / */
    public static final String VAL_SLASH = "/";

    /** Value ' */
    public static final String VAL_APOS = "'";

    /** Value . */
    public static final String VAL_PERIOD = ".";

    /** Value 000000 */
    public static final String VAL_000000 = "000000";

    /** Value 0000 */
    public static final String VAL_0000 = "0000";

    /** Value 0000 */
    public static final String VAL_000 = "000";

    /** Value 1 */
    public static final String VAL_ONE = "1";

    /** Value 2 */
    public static final String VAL_TWO = "2";

    /** Format of SYSDATE : yyyyMMddHHmmss */
    public static final String FMT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /** Format of SYSDATE : yyyy-MM-dd 00:00:00 */
    public static final String FMT_YYYYMMDD000000 = "yyyy-MM-dd 00:00:00";

    /** Format of SYSDATE : yyyyMMddHHmmssSSS */
    public static final String FMT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /** Format of Number : 0.0 */
    public static final String FMT_0_0 = "0.0";

    /** Format of Number : 0.000 */
    public static final String FMT_0_000 = "0.000";

    /** Format of SYSDATE : dd-MMM-yyyy */
    public static final String FMT_DDMMMYYYY = "dd-MMM-yyyy";

    /** Format of SYSDATE : dd-MMM-yyyy HH:mm:ss */
    public static final String FMT_DDMMMYYYYHHMMSS = "dd-MMM-yyyy HH:mm:ss";

    /** Value : ORD_CMNT_BYTE_LENGTH */
    public static final int ORD_CMNT_BYTE_LENGTH = 255;

    /** Value : 3(for RWS_LINE_NUM) */
    public static final int IDX_3 = 3;

    /** Value : 4(length WMS_LINE_NUM for setting NLGI2200_01.TPL_ORD_LINE_ID) */
    public static final int LG_WMS_LINE_NUM = 4;

    /** Value : 4(substring start index S80_STK_STS_CD for setting NLGI2200_01.TPL_HLD_CD) */
    public static final int IDX_FROM_S80_STK_STS_CD_TPL_HLD_CD = 1;

    /** Value : 4(length cut S80_STK_STS_CD for setting NLGI2200_01.TPL_HLD_CD) */
    public static final int LG_CUT_S80_STK_STS_CD_TPL_HLD_CD = 2;

    /** Value : "" */
    public static final String VAL_EMPTY = "";

    /** RegEx Pattern(from)  : \\s+ */
    public static final String VAL_REGEX_PTRN_ORD_CMT_FROM = "\\s+";

    /** RegEx Pattern(to)  : \\s */
    public static final String VAL_REGEX_PTRN_ORD_CMT_TO = "\\s";

    /** Value of  S80_STK_STS_CD "1" */
    public static final String VAL_S80_STK_STS_CD_1 = "1";

    /** Value of WMS_INV_IND_S */
    public static final String VAL_WMS_INV_IND_S = "S";

    /** Length of HAZ_CLS_NM */
    public static final int VAL_HAZ_CLS_NM_SIZE = 25;

    /** Length of VND_NM */
    public static final int VAL_VND_NM_SIZE = 35;

    /** Substring start position for STK_STS_CD */
    public static final int LG_CUT_STK_STS_CD_FROM = 1;

    /** Substring end position for STK_STS_CD */
    public static final int LG_CUT_STK_STS_CD_TO = 2;

    /* HDR */

    /** Substring start position for WH_CD */
    public static final int LG_CUT_WH_CD_FROM = 0;

    /** Substring end position for WH_CD */
    public static final int LG_CUT_WH_CD_TO = 4;

    /** Substring start position for WMS_SQ_NUM */
    public static final int LG_CUT_WMS_SQ_NUM_FROM = 4;

    /** Substring end position for WMS_SQ_NUM */
    public static final int LG_CUT_WMS_SQ_NUM_TO = 11;

    /** Substring start position for INTFC_TP_ID */
    public static final int LG_CUT_INTFC_TP_ID_FROM = 11;

    /** Substring end position for INTFC_TP_ID */
    public static final int LG_CUT_INTFC_TP_ID_TO = 13;

    /** Substring start position for INTFC_REC_TP_ID */
    public static final int LG_CUT_INTFC_REC_TP_ID_FROM = 13;

    /** Substring end position for INTFC_REC_TP_ID */
    public static final int LG_CUT_INTFC_REC_TP_ID_TO = 14;

    /** Substring start position for INTFC_REC_ACT_CD */
    public static final int LG_CUT_INTFC_REC_ACT_CD_FROM = 14;

    /** Substring end position for INTFC_REC_ACT_CD */
    public static final int LG_CUT_INTFC_REC_ACT_CD_TO = 15;

    /** Substring start position for WMS_CMPY_CD */
    public static final int LG_CUT_WMS_CMPY_CD_FROM = 15;

    /** Substring end position for WMS_CMPY_CD */
    public static final int LG_CUT_WMS_CMPY_CD_TO = 17;

    /** Substring start position for WMS_PO_ID */
    public static final int LG_CUT_WMS_PO_ID_FROM = 21;

    /** Substring end position for WMS_PO_ID */
    public static final int LG_CUT_WMS_PO_ID_TO = 36;

    /** Substring start position for VND_CD */
    public static final int LG_CUT_HDR_VND_CD_FROM = 36;

    /** Substring end position for VND_CD */
    public static final int LG_CUT_HDR_VND_CD_TO = 46;

    /** Substring start position for WMS_PRCH_TP_CD */
    public static final int LG_CUT_HDR_WMS_PRCH_TP_CD_FROM = 46;

    /** Substring end position for WMS_PRCH_TP_CD */
    public static final int LG_CUT_HDR_WMS_PRCH_TP_CD_TO = 47;

    /** Substring start position for WMS_TRX_CD */
    public static final int LG_CUT_HDR_WMS_TRX_CD_FROM = 47;

    /** Substring end position for WMS_TRX_CD */
    public static final int LG_CUT_HDR_WMS_TRX_CD_TO = 49;

    /** Substring start position for WMS_PO_STS_CD */
    public static final int LG_CUT_HDR_WMS_PO_STS_CD_FROM = 49;

    /** Substring end position for WMS_PO_STS_CD */
    public static final int LG_CUT_HDR_WMS_PO_STS_CD_TO = 50;

    /** Substring start position for PO_FROM_DT_TM_TS */
    public static final int LG_CUT_HDR_PO_FROM_DT_TM_TS_FROM = 50;

    /** Substring end position for PO_FROM_DT_TM_TS */
    public static final int LG_CUT_HDR_PO_FROM_DT_TM_TS_TO = 60;

    /** Substring start position for PO_USER_ID */
    public static final int LG_CUT_HDR_PO_USER_ID_FROM = 60;

    /** Substring end position for PO_USER_ID */
    public static final int LG_CUT_HDR_PO_USER_ID_TO = 68;

    /** Substring start position for PRINT_SWTH_CD */
    public static final int LG_CUT_HDR_PRINT_SWTH_CD_FROM = 68;

    /** Substring end position for PRINT_SWTH_CD */
    public static final int LG_CUT_HDR_PRINT_SWTH_CD_TO = 69;

    /** Substring start position for WMS_VESL_NM */
    public static final int LG_CUT_HDR_WMS_VESL_NM_FROM = 69;

    /** Substring end position for WMS_VESL_NM */
    public static final int LG_CUT_HDR_WMS_VESL_NM_TO = 99;

    /** Substring start position for WMS_BOL_NUM */
    public static final int LG_CUT_HDR_WMS_BOL_NUM_FROM = 99;

    /** Substring end position for WMS_BOL_NUM */
    public static final int LG_CUT_HDR_WMS_BOL_NUM_TO = 119;

    /** Substring start position for INBD_PO_MSG_TXT_01 */
    public static final int LG_CUT_HDR_INBD_PO_MSG_TXT_01_FROM = 119;

    /** Substring end position for INBD_PO_MSG_TXT_01 */
    public static final int LG_CUT_HDR_INBD_PO_MSG_TXT_01_TO = 184;

    /** Substring start position for INBD_PO_MSG_TXT_02 */
    public static final int LG_CUT_HDR_INBD_PO_MSG_TXT_02_FROM = 184;

    /** Substring end position for INBD_PO_MSG_TXT_02 */
    public static final int LG_CUT_HDR_INBD_PO_MSG_TXT_02_TO = 249;

    /** Substring start position for INBD_PO_MSG_TXT_03 */
    public static final int LG_CUT_HDR_INBD_PO_MSG_TXT_03_FROM = 249;

    /** Substring end position for INBD_PO_MSG_TXT_03 */
    public static final int LG_CUT_HDR_INBD_PO_MSG_TXT_03_TO = 314;

    /** Substring start position for INBD_PO_MSG_TXT_04 */
    public static final int LG_CUT_HDR_INBD_PO_MSG_TXT_04_FROM = 314;

    /** Substring end position for INBD_PO_MSG_TXT_04 */
    public static final int LG_CUT_HDR_INBD_PO_MSG_TXT_04_TO = 379;

    /** Substring start position for WMS_SER_NUM */
    public static final int LG_CUT_HDR_WMS_SER_NUM_FROM = 379;

    /** Substring end position for WMS_SER_NUM */
    public static final int LG_CUT_HDR_WMS_SER_NUM_TO = 394;

    /* DTL */

    /** Substring start position for RTL_SWH_CD */
    public static final int LG_CUT_DTL_RTL_SWH_CD_FROM = 17;

    /** Substring end position for RTL_SWH_CD */
    public static final int LG_CUT_DTL_RTL_SWH_CD_TO = 21;

    /** Substring start position for WMS_LINE_NUM */
    public static final int LG_CUT_DTL_WMS_LINE_NUM_FROM = 36;

    /** Substring end position for WMS_LINE_NUM */
    public static final int LG_CUT_DTL_WMS_LINE_NUM_TO = 41;

    /** Substring start position for WMS_MDSE_CD */
    public static final int LG_CUT_DTL_WMS_MDSE_CD_FROM = 41;

    /** Substring end position for WMS_MDSE_CD */
    public static final int LG_CUT_DTL_WMS_MDSE_CD_TO = 66;

    /** Substring start position for S80_STK_STS_CD */
    public static final int LG_CUT_DTL_S80_STK_STS_CD_FROM = 66;

    /** Substring end position for S80_STK_STS_CD */
    public static final int LG_CUT_DTL_S80_STK_STS_CD_TO = 68;

    /** Substring start position for WMS_LINE_STS_CD */
    public static final int LG_CUT_DTL_WMS_LINE_STS_CD_FROM = 68;

    /** Substring end position for WMS_LINE_STS_CD */
    public static final int LG_CUT_DTL_WMS_LINE_STS_CD_TO = 69;

    /** Substring start position for WMS_INV_IND */
    public static final int LG_CUT_DTL_WMS_INV_IND_FROM = 69;

    /** Substring end position for WMS_INV_IND */
    public static final int LG_CUT_DTL_WMS_INV_IND_TO = 70;

    /** Substring start position for WMS_OPEN_QTY */
    public static final int LG_CUT_DTL_WMS_OPEN_QTY_FROM = 70;

    /** Substring end position for WMS_OPEN_QTY */
    public static final int LG_CUT_DTL_WMS_OPEN_QTY_TO = 79;

    /** Substring start position for WMS_EST_DT_TM_TS */
    public static final int LG_CUT_DTL_WMS_EST_DT_TM_TS_FROM = 79;

    /** Substring end position for WMS_EST_DT_TM_TS */
    public static final int LG_CUT_DTL_WMS_EST_DT_TM_TS_TO = 89;

    /** Substring start position for WMS_INV_ID */
    public static final int LG_CUT_DTL_WMS_INV_ID_FROM = 89;

    /** Substring end position for WMS_INV_ID */
    public static final int LG_CUT_DTL_WMS_INV_ID_TO = 104;

    /** Substring start position for WMS_DO_ID */
    public static final int LG_CUT_DTL_WMS_DO_ID_FROM = 104;

    /** Substring end position for WMS_DO_ID */
    public static final int LG_CUT_DTL_WMS_DO_ID_TO = 119;

    /** Substring start position for CSE_FROM_NUM */
    public static final int LG_CUT_DTL_CSE_FROM_NUM_FROM = 119;

    /** Substring end position for CSE_FROM_NUM */
    public static final int LG_CUT_DTL_CSE_FROM_NUM_TO = 124;

    /** Substring start position for CSE_TO_NUM */
    public static final int LG_CUT_DTL_CSE_TO_NUM_FROM = 124;

    /** Substring end position for CSE_TO_NUM */
    public static final int LG_CUT_DTL_CSE_TO_NUM_TO = 129;

    /** Substring start position for WMS_BAT_NUM */
    public static final int LG_CUT_DTL_WMS_BAT_NUM_FROM = 129;

    /** Substring end position for WMS_BAT_NUM */
    public static final int LG_CUT_DTL_WMS_BAT_NUM_TO = 139;

    /** Substring start position for WMS_COLLO_NUM */
    public static final int LG_CUT_DTL_WMS_COLLO_NUM_FROM = 139;

    /** Substring end position for WMS_COLLO_NUM */
    public static final int LG_CUT_DTL_WMS_COLLO_NUM_TO = 164;

    /* VND */

    /** Substring start position for VND_CD */
    public static final int LG_CUT_VND_VND_CD_FROM = 36;

    /** Substring end position for VND_CD */
    public static final int LG_CUT_VND_VND_CD_TO = 46;

    /** Substring start position for WMS_VND_NM_01 */
    public static final int LG_CUT_VND_WMS_VND_NM_01_FROM = 46;

    /** Substring end position for WMS_VND_NM_01 */
    public static final int LG_CUT_VND_WMS_VND_NM_01_TO = 81;

    /** Substring start position for WMS_VND_NM_02 */
    public static final int LG_CUT_VND_WMS_VND_NM_02_FROM = 81;

    /** Substring end position for WMS_VND_NM_02 */
    public static final int LG_CUT_VND_WMS_VND_NM_02_TO = 116;

    /** Substring start position for FIRST_LINE_ADDR */
    public static final int LG_CUT_VND_FIRST_LINE_ADDR_FROM = 116;

    /** Substring end position for FIRST_LINE_ADDR */
    public static final int LG_CUT_VND_FIRST_LINE_ADDR_TO = 151;

    /** Substring start position for SCD_LINE_ADDR */
    public static final int LG_CUT_VND_SCD_LINE_ADDR_FROM = 151;

    /** Substring end position for SCD_LINE_ADDR */
    public static final int LG_CUT_VND_SCD_LINE_ADDR_TO = 186;

    /** Substring start position for THIRD_LINE_ADDR */
    public static final int LG_CUT_VND_THIRD_LINE_ADDR_FROM = 186;

    /** Substring end position for THIRD_LINE_ADDR */
    public static final int LG_CUT_VND_THIRD_LINE_ADDR_TO = 221;

    /** Substring start position for FRTH_LINE_ADDR */
    public static final int LG_CUT_VND_FRTH_LINE_ADDR_FROM = 221;

    /** Substring end position for FRTH_LINE_ADDR */
    public static final int LG_CUT_VND_FRTH_LINE_ADDR_TO = 256;

    /** Substring start position for CTY_ADDR */
    public static final int LG_CUT_VND_CTY_ADDR_FROM = 256;

    /** Substring end position for CTY_ADDR */
    public static final int LG_CUT_VND_CTY_ADDR_TO = 276;

    /** Substring start position for ST_CD */
    public static final int LG_CUT_VND_ST_CD_FROM = 276;

    /** Substring end position for ST_CD */
    public static final int LG_CUT_VND_ST_CD_TO = 278;

    /** Substring start position for POST_CD */
    public static final int LG_CUT_VND_POST_CD_FROM = 278;

    /** Substring end position for POST_CD */
    public static final int LG_CUT_VND_POST_CD_TO = 293;

    /** Substring start position for CTRY_CD */
    public static final int LG_CUT_VND_CTRY_CD_FROM = 293;

    /** Substring end position for CTRY_CD */
    public static final int LG_CUT_VND_CTRY_CD_TO = 295;

    /** Substring start position for VND_TO_CTAC_NM */
    public static final int LG_CUT_VND_VND_TO_CTAC_NM_FROM = 295;

    /** Substring end position for VND_TO_CTAC_NM */
    public static final int LG_CUT_VND_VND_TO_CTAC_NM_TO = 320;

    /** Substring start position for VND_TO_CTAC_NUM */
    public static final int LG_CUT_VND_VND_TO_CTAC_NUM_FROM = 320;

    /** Substring end position for VND_TO_CTAC_NUM */
    public static final int LG_CUT_VND_VND_TO_CTAC_NUM_TO = 335;

    /** 0 */
    public static final int ZERO = 0;

    /** VAR_CHAR_CONST */
    /** NLGB022001_SHPG_PLN_ITEM_DNLD */
    public static final String VAR_CHAR_CONST_NLGB022001_SHPG_PLN_ITEM_DNLD = "NLGB022001_SHPG_PLN_ITEM_DNLD";

    /** NLGB022001_MRP_ITEM_DNLD */
    public static final String VAR_CHAR_CONST_NLGB022001_MRP_ITEM_DNLD = "NLGB022001_MRP_ITEM_DNLD";

    /** NLGB022001_PO_ITEM_DNLD */
    public static final String VAR_CHAR_CONST_NLGB022001_PO_ITEM_DNLD = "NLGB022001_PO_ITEM_DNLD";

    /** Bind Constance (SQL) */
    /** WMS_INTFC_TASK_NM : ITEM */
    public static final String SQL_BIND_WMS_INTFC_TASK_NM_ITEM = "ITEM";

    /** SQL Param Map Key */
    /** glblCmpyCd */
    public static final String MAP_KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** notDrop */
    public static final String MAP_KEY_NOT_DROP = "notDrop";

    /** len2 */
    public static final String MAP_KEY_LEN2 = "len2";

    /** len3 */
    public static final String MAP_KEY_LEN3 = "len3";

    /** wms */
    public static final String MAP_KEY_WMS = "wms";

    /** flgY */
    public static final String MAP_KEY_FLG_Y = "flgY";

    /** inbound */
    public static final String MAP_KEY_INBOUND = "inbound";

    /** lenVndNm */
    public static final String MAP_KEY_LEN_VND_NM = "lenVndNm";

    /** procStsCd */
    public static final String MAP_KEY_PROC_STS_CD = "procStsCd";

    /** lgCutWhCdFrom */
    public static final String MAP_KEY_LG_CUT_WH_CD_FROM = "lgCutWhCdFrom";

    /** lgCutWhCdTo */
    public static final String MAP_KEY_LG_CUT_WH_CD_TO = "lgCutWhCdTo";

    /** lgCutWmsMdseCdFrom */
    public static final String MAP_KEY_LG_CUT_WMS_MDSE_CD_FROM = "lgCutWmsMdseCdFrom";

    /** lgCutWmsMdseCdTo */
    public static final String MAP_KEY_LG_CUT_WMS_MDSE_CD_TO = "lgCutWmsMdseCdTo";

    /** lgCutWmsSqNumFrom */
    public static final String MAP_KEY_LG_CUT_WMS_SQ_NUM_FROM = "lgCutWmsSqNumFrom";

    /** lgCutWmsSqNumTo */
    public static final String MAP_KEY_LG_CUT_WMS_SQ_NUM_TO = "lgCutWmsSqNumTo";

    /** lgCutIntfcRecTpIdFrom */
    public static final String MAP_KEY_LG_CUT_INTFC_REC_TP_ID_FROM = "lgCutIntfcRecTpIdFrom";

    /** lgCutIntfcRecTpIdTo */
    public static final String MAP_KEY_LG_CUT_INTFC_REC_TP_ID_TO = "lgCutIntfcRecTpIdTo";

    /** wmsTrgtWhCd */
    public static final String MAP_KEY_WMS_TRGT_WH_CD = "wmsTrgtWhCd";

    /** wmsItemDnldStsCd */
    public static final String MAP_KEY_WMS_ITEM_DNLD_STS_CD = "wmsItemDnldStsCd";

    /** whCd */
    public static final String MAP_KEY_WH_CD = "whCd";

    /** wmsWhCd */
    public static final String MAP_KEY_WMS_WH_CD = "wmsWhCd";

    /** wmsSqNum */
    public static final String MAP_KEY_WMS_SQ_NUM = "wmsSqNum";

    /** rwsRefNum */
    public static final String MAP_KEY_RWS_REF_NUM = "rwsRefNum";

    /** rwsStsCd */
    public static final String MAP_KEY_RWS_STS_CD = "rwsStsCd";

    /** rwsNum */
    public static final String MAP_KEY_RWS_NUM = "rwsNum";

    /** rwsLineNum */
    public static final String MAP_KEY_RWS_LINE_NUM = "rwsLineNum";

    /** wmsIntfcTaskNm */
    public static final String MAP_KEY_WMS_INTFC_TASK_NM = "wmsIntfcTaskNm";

    /** wmsItemDnldIntfcId */
    public static final String MAP_KEY_WMS_ITEM_DNLD_INTFC_ID = "wmsItemDnldIntfcId";

    /** wmsDescShortNm */
    public static final String MAP_KEY_WMS_DESC_SHORT_NM = "wmsDescShortNm";

    /** glblCmpyCd01 */
    public static final String MAP_KEY_GLBL_CMPY_CD_01 = "glblCmpyCd01";

    /** rwsNum01 */
    public static final String MAP_KEY_RWS_NUM_01 = "rwsNum01";

    /** rwsLineNum01 */
    public static final String MAP_KEY_RWS_LINE_NUM_01 = "rwsLineNum01";

    /** mdseCd */
    public static final String MAP_KEY_MDSE_CD = "mdseCd";

    /** flag */
    public static final String MAP_KEY_FLAG = "flag";

    /** SQL Statement Id */
    /** getRws */
    public static final String SQL_STMT_ID_GET_RWS = "getRws";

    /** getFrceItemList */
    public static final String SQL_STMT_ID_GET_FRCE_ITEM_LIST = "getFrceItemList";

    /** getWmsInbdItemWrkList */
    public static final String SQL_STMT_ID_GET_WMS_INBD_ITEM_WRK_LIST = "getWmsInbdItemWrkList";

    /** getWmsInbdItemUpcWrkList */
    public static final String SQL_STMT_ID_GET_WMS_INBD_ITEM_UPC_WRK_LIST = "getWmsInbdItemUpcWrkList";

    /** getWmsInbdItemSerWrkList */
    public static final String SQL_STMT_ID_GET_WMS_INBD_ITEM_SER_WRK_LIST = "getWmsInbdItemSerWrkList";

    /** getShpgPlnItemList */
    public static final String SQL_STMT_ID_GET_SHPG_PLN_ITEM_LIST = "getShpgPlnItemList";

    /** getMrpItemList */
    public static final String SQL_STMT_ID_GET_MRP_ITEM_LIST = "getMrpItemList";

    /** getPoItemList */
    public static final String SQL_STMT_ID_GET_PO_ITEM_LIST = "getPoItemList";

    /** getRwsNum */
    public static final String SQL_STMT_ID_GET_RWS_NUM = "getRwsNum";

    /** getRwsSer */
    public static final String SQL_STMT_ID_GET_RWS_SER = "getRwsSer";

    /** getNewItem */
    public static final String SQL_STMT_ID_GET_NEW_ITEM = "getNewItem";

    /** getPrimaryKeyForAbcAnlsRslt */
    public static final String SQL_STMT_ID_GET_PRIMARY_KEY_FOR_ABC_ANLS_RSLT = "getPrimaryKeyForAbcAnlsRslt";

    /** isExistsWmsMdse */
    public static final String SQL_STMT_ID_IS_EXISTS_WMS_MDSE = "isExistsWmsMdse";

    /** VAL_DBS_CHAR_01 */
    public static final String VAL_DBS_CHAR_01 = "É";

    /** VAL_DBS_CHAR_02 */
    public static final String VAL_DBS_CHAR_02 = "é";

    /** VAL_DBS_CHAR_03 */
    public static final String VAL_DBS_CHAR_03 = "\t";

    /** VAL_DBS_CHAR_04 */
    public static final String VAL_DBS_CHAR_04 = "\n";

    /** VAL_DBS_CHAR_04 */
    public static final String VAL_DBS_CHAR_05 = "\r";

    /** VAL_DBS_CONV_CHAR_01 */
    public static final String VAL_DBS_CONV_CHAR_01 = "E";

    /** VAL_DBS_CONV_CHAR_02 */
    public static final String VAL_DBS_CONV_CHAR_02 = "e";

    /** VAL_DBS_CONV_CHAR_03 */
    public static final String VAL_DBS_CONV_CHAR_03 = " ";

    /** VAL_DBS_CONV_CHAR_04 */
    public static final String VAL_DBS_CONV_CHAR_04 = "";

    /** CONV_SCE_ORD_TP_CD */
    public static final String CONV_SCE_ORD_TP_CD = "CONV_SCE_ORD_TP_CD";

    /** CONV_STK_STS_CD */
    public static final String CONV_STK_STS_CD = "CONV_STK_STS_CD";
}
