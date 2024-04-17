/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB001001;

import java.math.BigDecimal;

/**
 * <pre>
 * WMS SO Download
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/26/2013   CSAI            K.Hayashida     Create          MW Replace Initial
 * 09/11/2014   CSAI            K.Hayashida     Update          ITG#541344
 * 04/19/2016   CSAI            D.Fukaya        Update          QC#5252
 * 04/05/2017   CITS            R.Shimamoto     Update          QC#17981
 * 06/14/2017   CITS            T.Tokutomi      Update          QC#19109
 * 06/23/2017   CITS            T.Kikuhara      Update          QC#19529
 * 06/29/2017   CITS            K.Ogino         Update          QC#19634
 * 07/27/2017   CITS            Y.Iwasaki       Update          QC#20077
 * 08/01/2017   CITS            T.Tokutomi      Update          QC#20176
 * 11/02/2017   CITS            S.Katsuma       Update          SOL#170(QC#18624)
 * 05/11/2018   CITS            S.Katsuma       Update          QC#24714
 *</pre>
 */
public interface NLGB001001Constant {

    /** Business ID */
    String BUSINESS_ID = "NLGB0010";

    /** Output Log Program ID */
    String PROGRAM_ID = "NLGB001001:";

    /** Prameter Name: GLBL_CMPY_CD */
    String PRAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Prameter Name: WH_GRP_CD */
    String PRAM_NM_WH_GRP_CD = "Warehouse Group Code";

    /** [@] does not exist. Table:[@], Search Key:[@] */
    String NLGM0041E = "NLGM0041E";

    /**
     * The record cannot be registered. Registration Table Name: [@],
     * Table Name: [@], Key Field Name: [@], Key Value: [@]
     */
    String NLGM0045E = "NLGM0045E";

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

    /**
     * The record cannot be delete. Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    String NLGM0040E = "NLGM0040E";

    /**
     * The corresponding data does not exist. Table Name : [@],
     * Key Field Name:  [@], Key Value: [@]
     */
    String NLAM1001E = "NLAM1001E";

    /** DB Table: SHPG_ORD */
    String TBL_SHPG_ORD = "SHPG_ORD";

    /** DB Table: SHPG_ORD_DTL */
    String TBL_SHPG_ORD_DTL = "SHPG_ORD_DTL";

    /** DB Table: SHPG_ORD_MSG */
    String TBL_SHPG_ORD_MSG = "SHPG_ORD_MSG";

    /** DB Table: SHPG_ORD_CUST_DTL */
    String TBL_SHPG_ORD_CUST_DTL = "SHPG_ORD_CUST_DTL";

    /** DB Table: STS_STK_CONV */
    String TBL_STS_STK_CONV = "STS_STK_CONV";

    /** DB Table: WMS_OTBD_SO_WRK */
    String TBL_WMS_OTBD_SO_WRK = "WMS_OTBD_SO_WRK";

    /** DB Table: WMS_INBD_SO_HDR */
    String TBL_WMS_INBD_SO_HDR = "WMS_INBD_SO_HDR";

    /** DB Table: WMS_INBD_SO_DTL */
    String TBL_WMS_INBD_SO_DTL = "WMS_INBD_SO_DTL";

    /** DB Table: WMS_INBD_SO_SHIP_TO */
    String TBL_WMS_INBD_SO_SHIP_TO = "WMS_INBD_SO_SHIP_TO";

    /** DB Table: WMS_INBD_SO_TEXT */
    String TBL_WMS_INBD_SO_TEXT = "WMS_INBD_SO_TEXT";

    /** DB Table: WMS_INBD_SO_CHRG_TO */
    String TBL_WMS_INBD_SO_CHRG_TO = "WMS_INBD_SO_CHRG_TO";

    /** DB Table: WMS_INBD_SO_BILL_TO */
    String TBL_WMS_INBD_SO_BILL_TO = "WMS_INBD_SO_BILL_TO";

    /** DB Table: WMS_INBD_SO_RTRN_TO */
    String TBL_WMS_INBD_SO_RTRN_TO = "WMS_INBD_SO_RTRN_TO";

    /** DB Table: WMS_INTFC_CTRL */
    String WMS_INTFC_CTRL = "WMS_INTFC_CTRL";

    /** DB Table: NLGI1100_01 */
    String TBL_NLGI1100_01 = "NLGI1100_01";

    /** DB Table: NLGI1100_02 */
    String TBL_NLGI1100_02 = "NLGI1100_02";

    /** DB Table: NLGI1100_03 */
    String TBL_NLGI1100_03 = "NLGI1100_03";

    /** DB Table: NLGI1100_04 */
    String TBL_NLGI1100_04 = "NLGI1100_04";

    /** DB Table: NLGI2100_01 */
    String TBL_NLGI2100_01 = "NLGI2100_01";

    /** DB Table: NLGI2100_02 */
    String TBL_NLGI2100_02 = "NLGI2100_02";

    /** DB Table: NLGI2100_04 */
    String TBL_NLGI2100_04 = "NLGI2100_04";

    /** DB Table: NLGI2100_05 */
    String TBL_NLGI2100_05 = "NLGI2100_05";

    /** DB Table: NLGI2100_06 */
    String TBL_NLGI2100_06 = "NLGI2100_06";

    /** DB Table: WMS_WH_RTRN_ADDR */
    String TBL_WMS_WH_RTRN_ADDR = "WMS_WH_RTRN_ADDR";

    /** DB Table: WMS_SO_ORIG_LINE_SAVE */
    String TBL_WMS_SO_ORIG_LINE_SAVE = "WMS_SO_ORIG_LINE_SAVE";

    /** DB Table: CMPY_CD_CONV */
    String TBL_CMPY_CD_CONV = "CMPY_CD_CONV";

    /** DB Table: SCE_ORD_TP */
    String TBL_SCE_ORD_TP = "SCE_ORD_TP";

    /** DB Table: ORD_SRC_CONV */
    String TBL_ORD_SRC_CONV = "ORD_SRC_CONV";

    /** DB Column: GLBL_CMPY_CD */
    String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB Column: SO_NUM */
    String COL_SO_NUM = "SO_NUM";

    /** DB Column: WMS_OTBD_SO_WRK_PK */
    String COL_WMS_OTBD_SO_WRK_PK = "WMS_OTBD_SO_WRK_PK";

    /** DB Column: SO_TO_WMS_DATA_TXT */
    String COL_SO_TO_WMS_DATA_TXT = "SO_TO_WMS_DATA_TXT";

    /** DB Column: WMS_UNIQ_ID */
    String COL_WMS_UNIQ_ID = "WMS_UNIQ_ID";

    /** DB Column: WMS_SO_DNLD_SO_INTFC_ID */
    String COL_WMS_SO_DNLD_SO_INTFC_ID = "WMS_SO_DNLD_SO_INTFC_ID";

    /** DB Column: WMS_SO_DNLD_PO_INTFC_ID */
    String COL_WMS_SO_DNLD_PO_INTFC_ID = "WMS_SO_DNLD_PO_INTFC_ID";

    /** DB Column: CASES */
    String COL_CASES = "CASES";

    /** DB Column: PALLETS */
    String COL_PALLETS = "PALLETS";

    /** DB Column: NEW_SHIP_VIA */
    String COL_NEW_SHIP_VIA = "NEW_SHIP_VIA";

    /** DB Column: NEW_PRTY_CD */
    String COL_NEW_PRTY_CD = "NEW_PRTY_CD";

    /** DB Column: WMS_RTRN_TO_NM_01 */
    String COL_WMS_RTRN_TO_NM_01 = "WMS_RTRN_TO_NM_01";

    /** DB Column: WMS_RTRN_TO_NM_02 */
    String COL_WMS_RTRN_TO_NM_02 = "WMS_RTRN_TO_NM_02";

    /** DB Column: RTRN_TO_FIRST_LINE_ADDR */
    String COL_RTRN_TO_FIRST_LINE_ADDR = "RTRN_TO_FIRST_LINE_ADDR";

    /** DB Column: RTRN_TO_SCD_LINE_ADDR */
    String COL_RTRN_TO_SCD_LINE_ADDR = "RTRN_TO_SCD_LINE_ADDR";

    /** DB Column: RTRN_TO_THIRD_LINE_ADDR */
    String COL_RTRN_TO_THIRD_LINE_ADDR = "RTRN_TO_THIRD_LINE_ADDR";

    /** DB Column: RTRN_TO_FRTH_LINE_ADDR */
    String COL_RTRN_TO_FRTH_LINE_ADDR = "RTRN_TO_FRTH_LINE_ADDR";

    /** DB Column: RTRN_TO_CTY_ADDR */
    String COL_RTRN_TO_CTY_ADDR = "RTRN_TO_CTY_ADDR";

    /** DB Column: RTRN_TO_ST_CD */
    String COL_RTRN_TO_ST_CD = "RTRN_TO_ST_CD";

    /** DB Column: RTRN_TO_POST_CD */
    String COL_RTRN_TO_POST_CD = "RTRN_TO_POST_CD";

    /** DB Column: RTRN_TO_CTRY_CD */
    String COL_RTRN_TO_CTRY_CD = "RTRN_TO_CTRY_CD";

    /** DB Column: WMS_RTRN_TO_CTAC_NM */
    String COL_WMS_RTRN_TO_CTAC_NM = "WMS_RTRN_TO_CTAC_NM";

    /** DB Column: RTRN_TO_CTAC_NUM */
    String COL_RTRN_TO_CTAC_NUM = "RTRN_TO_CTAC_NUM";

    /** DB Column: WH_CD */
    String COL_WH_CD = "WH_CD";

    /** DB Column: WMS_RTRN_LB_CD */
    String COL_WMS_RTRN_LB_CD = "WMS_RTRN_LB_CD";

    /** DB Column: CRAT_DT_TM_TS */
    String COL_CRAT_DT_TM_TS = "CRAT_DT_TM_TS";

    /** DB Column: EST_SHIP_DT_TM_TS */
    String COL_EST_SHIP_DT_TM_TS = "EST_SHIP_DT_TM_TS";

    /** DB Column: WMS_RQST_DT_TM_TS */
    String COL_WMS_RQST_DT_TM_TS = "WMS_RQST_DT_TM_TS";

    /** DB Column: WMS_PRINT_DT_TM_TS */
    String COL_WMS_PRINT_DT_TM_TS = "WMS_PRINT_DT_TM_TS";

    /** DB Column: CANC_BY_DT_TM_TS */
    String COL_CANC_BY_DT_TM_TS = "CANC_BY_DT_TM_TS";

    /** DB Column: HOST_ORD_DT_TM_TS */
    String COL_HOST_ORD_DT_TM_TS = "HOST_ORD_DT_TM_TS";

    /** DB Column: SO_TO_WMS_DTL_LINE_NUM */
    String COL_SO_TO_WMS_DTL_LINE_NUM = "SO_TO_WMS_DTL_LINE_NUM";

    /** DB Column: WMS_ORD_TP_CACHE_KEY */
    String WMS_ORD_TP_CACHE_KEY = "WMS_ORD_TP_CACHE_KEY";

    /** DB Column: WMS_ORD_TP_XPND_CD */
    String COL_WMS_ORD_TP_XPND_CD = "WMS_ORD_TP_XPND_CD";

    /** DB Column: S80_CMPY_CD */
    String COL_S80_CMPY_CD = "S80_CMPY_CD";

    /** DB Column: S80_ORD_TP_CD */
    String COL_S80_ORD_TP_CD = "S80_ORD_TP_CD";

    /** DB Column: S80_TRX_CD */
    String COL_S80_TRX_CD = "S80_TRX_CD";

    /** DB Column: S80_ORD_SRC_CD */
    String COL_S80_ORD_SRC_CD = "S80_ORD_SRC_CD";

    /** DB Column: FROM_STK_STS_CD */
    String COL_FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /** DB Column: SCE_ORD_TP_CD */
    String COL_SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /** DB Column: TRX_SRC_TP_CD */
    String COL_TRX_SRC_TP_CD = "TRX_SRC_TP_CD";

    /** DB Column: PICK_TKT_NUM */
    String COL_PICK_TKT_NUM = "PICK_TKT_NUM";

    /** DB Column: CUST_ISS_PO_NUM */
    String COL_CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** DB Column: SELL_TO_CUST_CD */
    String COL_SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** DB Column: BILL_TO_CUST_CD */
    String COL_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB Column: SHIP_TO_CUST_CD */
    String COL_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** DB Column: SO_CRAT_TS */
    String COL_SO_CRAT_TS = "SO_CRAT_TS";

    /** DB Column: PSD_DT */
    String COL_PSD_DT = "PSD_DT";

    /** DB Column: RDD_DT */
    String COL_RDD_DT = "RDD_DT";

    /** DB Column: DNLD_TM_TS */
    String COL_DNLD_TM_TS = "DNLD_TM_TS";

    /** DB Column: CARR_CD */
    String COL_CARR_CD = "CARR_CD";

    /** DB Column: DROP_SHIP_FLG */
    String COL_DROP_SHIP_FLG = "DROP_SHIP_FLG";

    /** DB Column: PRINT_SCC_LB_FLG */
    String COL_PRINT_SCC_LB_FLG = "PRINT_SCC_LB_FLG";

    /** DB Column: PRINT_UCC_LB_FLG */
    String COL_PRINT_UCC_LB_FLG = "PRINT_UCC_LB_FLG";

    /** DB Column: MIX_PLT_ALLW_FLG */
    String COL_MIX_PLT_ALLW_FLG = "MIX_PLT_ALLW_FLG";

    /** DB Column: PRINT_PLT_UCC_LB_FLG */
    String COL_PRINT_PLT_UCC_LB_FLG = "PRINT_PLT_UCC_LB_FLG";

    /** DB Column: PRINT_NON_ASN_UCC_LB_FLG */
    String COL_PRINT_NON_ASN_UCC_LB_FLG = "PRINT_NON_ASN_UCC_LB_FLG";

    /** DB Column: ASN_REQ_FLG */
    String COL_ASN_REQ_FLG = "ASN_REQ_FLG";

    /** DB Column: UCC_NUM_CD */
    String COL_UCC_NUM_CD = "UCC_NUM_CD";

    /** DB Column: EDI_TP_CD */
    String COL_EDI_TP_CD = "EDI_TP_CD";

    /** DB Column: CUST_STORE_NUM */
    String COL_CUST_STORE_NUM = "CUST_STORE_NUM";

    /** DB Column: SO_DEPT_NUM */
    String COL_SO_DEPT_NUM = "SO_DEPT_NUM";

    /** DB Column: TOT_SHIP_AMT */
    String COL_TOT_SHIP_AMT = "TOT_SHIP_AMT";

    /** DB Column: RTRN_LB_CD */
    String COL_RTRN_LB_CD = "RTRN_LB_CD";

    /** DB Column: SO_CONFIG_FLG */
    String COL_SO_CONFIG_FLG = "SO_CONFIG_FLG";

    /** DB Column: CARR_ACCT_NUM */
    String COL_CARR_ACCT_NUM = "CARR_ACCT_NUM";

    /** DB Column: SHPG_SVC_LVL_CD */
    String COL_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** DB Column: SVC_CONFIG_MSTR_PK */
    String COL_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** DB Column: PRE_ISTL_SHOP_RQST_FLG */
    String COL_PRE_ISTL_SHOP_RQST_FLG = "PRE_ISTL_SHOP_RQST_FLG";

    /** DB Column: WMS_RTE_PATH_CD */
    String COL_WMS_RTE_PATH_CD = "WMS_RTE_PATH_CD";

    /** DB Column: SRC_ORD_NUM */
    String COL_SRC_ORD_NUM = "SRC_ORD_NUM";

    /** DB Column: EXPT_SHPG_METH_CD */
    String COL_EXPT_SHPG_METH_CD = "EXPT_SHPG_METH_CD";

    /** DB Column: SHPG_METH_NM */
    String COL_SHPG_METH_NM = "SHPG_METH_NM";

    /** DB Column: S80_SHPG_TERM_CD */
    String COL_S80_SHPG_TERM_CD = "S80_SHPG_TERM_CD";

    /** DB Column: S80_SHPG_TERM_NM */
    String COL_S80_SHPG_TERM_NM = "S80_SHPG_TERM_NM";

    /** DB Column: SCHD_DELY_DT */
    String COL_SCHD_DELY_DT = "SCHD_DELY_DT";

    /** DB Column: OTBD_SRC_ORD_TP_TXT */
    String COL_OTBD_SRC_ORD_TP_TXT = "OTBD_SRC_ORD_TP_TXT";

    /** DB Column: SCE_ORD_TP_NM */
    String COL_SCE_ORD_TP_NM = "SCE_ORD_TP_NM";

    /** DB Column: LOC_GRP_CD */
    String COL_LOC_GRP_CD = "LOC_GRP_CD";

    /** DB Column: CPO_PSN_NM */
    String COL_CPO_PSN_NM = "CPO_PSN_NM";

    /** DB Column: PO_PSN_NM */
    String COL_PO_PSN_NM = "PO_PSN_NM";

    /** DB Column: SR_PSN_NM */
    String COL_SR_PSN_NM = "SR_PSN_NM";

    /** DB Column: SO_SLP_NUM */
    String COL_SO_SLP_NUM = "SO_SLP_NUM";

    /** DB Column: MDSE_CD */
    String COL_MDSE_CD = "MDSE_CD";

    /** DB Column: CUST_MDSE_CD */
    String COL_CUST_MDSE_CD = "CUST_MDSE_CD";

    /** DB Column: RQST_ORD_QTY */
    String COL_RQST_ORD_QTY = "RQST_ORD_QTY";

    /** DB Column: SHPG_BAL_QTY */
    String COL_SHPG_BAL_QTY = "SHPG_BAL_QTY";

    /** DB Column: SHPG_QTY */
    String COL_SHPG_QTY = "SHPG_QTY";

    /** DB Column: UNIT_PRC_AMT */
    String COL_UNIT_PRC_AMT = "UNIT_PRC_AMT";

    /** DB Column: DISC_PRC_AMT */
    String COL_DISC_PRC_AMT = "DISC_PRC_AMT";

    /** DB Column: DISC_UNIT_PRC_AMT */
    String COL_DISC_UNIT_PRC_AMT = "DISC_UNIT_PRC_AMT";

    /** DB Column: SER_NUM_TAKE_FLG */
    String COL_SER_NUM_TAKE_FLG = "SER_NUM_TAKE_FLG";

    /** DB Column: SET_MDSE_CD */
    String COL_SET_MDSE_CD = "SET_MDSE_CD";

    /** DB Column: SET_MDSE_NM */
    String COL_SET_MDSE_NM = "SET_MDSE_NM";

    /** DB Column: SET_SHPG_QTY */
    String COL_SET_SHPG_QTY = "SET_SHPG_QTY";

    /** DB Column: IN_EACH_QTY */
    String COL_IN_EACH_QTY = "IN_EACH_QTY";

    /** DB Column: TOT_SHPG_WT */
    String COL_TOT_SHPG_WT = "TOT_SHPG_WT";

    /** DB Column: TOT_SHPG_VOL */
    String COL_TOT_SHPG_VOL = "TOT_SHPG_VOL";

    /** DB Column: BAT_NUM_TAKE_FLG */
    String COL_BAT_NUM_TAKE_FLG = "BAT_NUM_TAKE_FLG";

    /** DB Column: CONFIG_ITEM_FLG */
    String COL_CONFIG_ITEM_FLG = "CONFIG_ITEM_FLG";

    /** DB Column: RTL_WH_CD */
    String COL_RTL_WH_CD = "RTL_WH_CD";

    /** DB Column: RTL_SWH_CD */
    String COL_RTL_SWH_CD = "RTL_SWH_CD";

    /** DB Column: PICK_SVC_CONFIG_MSTR_PK */
    String COL_PICK_SVC_CONFIG_MSTR_PK = "PICK_SVC_CONFIG_MSTR_PK";

    /** DB Column: BACK_ORD_IMPCT_TP_CD */
    String COL_BACK_ORD_IMPCT_TP_CD = "BACK_ORD_IMPCT_TP_CD";

    // QC#19109
    /** DB Column: WMS_ORD_NUM */
    String COL_WMS_ORD_NUM = "WMS_ORD_NUM";

    /** DB Column: TRX_HDR_NUM */
    String COL_TRX_HDR_NUM = "TRX_HDR_NUM";

    /** DB Column: TRX_LINE_NUM */
    String COL_TRX_LINE_NUM = "TRX_LINE_NUM";

    /** DB Column: TRX_LINE_SUB_NUM */
    String COL_TRX_LINE_SUB_NUM = "TRX_LINE_SUB_NUM";

    /** DB Column: RMV_CONFIG_FLG */
    String COL_RMV_CONFIG_FLG = "RMV_CONFIG_FLG";

    /** DB Column: ORD_QTY */
    String COL_ORD_QTY = "ORD_QTY";

    /** DB Column: RTRN_REQ_PRT_FLG */
    String COL_RTRN_REQ_PRT_FLG = "RTRN_REQ_PRT_FLG";

    /** DB Column: MDSE_ITEM_TP_CD */
    String COL_MDSE_ITEM_TP_CD = "MDSE_ITEM_TP_CD";

    /** DB Column: SO_CUST_DATA_TP_CD */
    String COL_SO_CUST_DATA_TP_CD = "SO_CUST_DATA_TP_CD";

    /** DB Column: PRCH_REQ_NUM */
    String COL_PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** DB Column: DPLY_LINE_NUM */
    String COL_DPLY_LINE_NUM = "DPLY_LINE_NUM";

    /** Key of ASN WH_CD */
    String VAL_VARCHAR_KEY_ASN_WH_CD = "NLGB0010_ASN_WH_CD";

    /** Key of ADDR_LB_FMT WH_CD */
    String VAL_VARCHAR_KEY_ADDR_LB_FMT_WH_CD = "NLGB0010_ADDR_LB_FMT_WH_CD";

    /** Value of WMS_SHIP_VIA_RTE_MAP List Size */
    int VAL_WMS_SHIP_VIA_RTE_MAP_LIST_SIZE = 50;

    /** Value of WMS_FRT_OUT_CD_MAP List Size */
    int VAL_WMS_FRT_OUT_CD_MAP_LIST_SIZE = 50;

    /** Value of WMS_CTRY_CTAC List Size */
    int VAL_WMS_CTRY_CTAC_LIST_SIZE = 50;

    /** Value of SO_TO_WMS_DATA_TXT : 700 */
    int VAL_SO_TO_WMS_DATA_TXT_SIZE = 700;

    /** Value of WMS_UNIQ_ID : 5 */
    String VAL_WMS_UNIQ_ID_MA = "5";

    /** Value of WMS_UNIQ_ID : 6 */
    String VAL_WMS_UNIQ_ID_MENLO = "6";

    /** Value of WMS_UNIQ_ID : 8 */
    String VAL_WMS_UNIQ_ID_TECSYS = "8";

    /** Value of WMS_UNIQ_ID : 11 (WMS8.4.1) */
    String VAL_WMS_UNIQ_ID_TECSYS_2 = "11";

    /** Value of INTFC_REC_TP_ID : 1 */
    String VAL_INTFC_REC_TP_ID_HDR = "1";

    /** Value of INTFC_REC_TP_ID : 2 */
    String VAL_INTFC_REC_TP_ID_DTL = "2";

    /** Value of INTFC_REC_TP_ID : 3 */
    String VAL_INTFC_REC_TP_ID_SHIP = "3";

    /** Value of INTFC_REC_TP_ID : 4 */
    String VAL_INTFC_REC_TP_ID_TXT = "4";

    /** Value of INTFC_REC_TP_ID : 5 */
    String VAL_INTFC_REC_TP_ID_CHRG = "5";

    /** Value of INTFC_REC_TP_ID : 6 */
    String VAL_INTFC_REC_TP_ID_BILL = "6";

    /** Value of INTFC_REC_TP_ID : 8 */
    String VAL_INTFC_REC_TP_ID_RTRN = "8";

    /** Value of MD_BREAK_TP_CD : WGT */
    String VAL_MD_BREAK_TP_CD_WGT = "WGT";

    /** Value of SHIP_VIA_CD : * */
    String VAL_SHIP_VIA_CD_ALL = "*";

    /** Value of WH_CD : * */
    String VAL_WH_CD_ALL = "*";

    /** Value of WH_CD : 61 */
    String VAL_WH_CD_61 = "61";

    /** Value of WH_CD : 26 */
    String VAL_WH_CD_26 = "26";

    /** Value of WH_CD : 25 */
    String VAL_WH_CD_25 = "25";

    /** Value of WH_CD : 55 */
    String VAL_WH_CD_55 = "55";

    /** Value of WH_CD : 21 */
    String VAL_WH_CD_21 = "21";

    /** Value of WH_CD : 23 */
    String VAL_WH_CD_23 = "23";

    /** Value of INTFC_TP_ID : 2 */
    String VAL_INTFC_TP_ID_02 = "02";

    /** Value of INTFC_REC_TP_ID : 8 */
    String VAL_INTFC_REC_TP_ID_8 = "8";

    /** Value of ORD_TP_TXT : SO */
    String VAL_ORD_TP_TXT_PFX_SO = "SO";

    /** Value of ORD_TP_TXT : PO */
    String VAL_ORD_TP_TXT_PFX_PO = "PO";

    /** Value of ORD_TP_TXT : C */
    String VAL_ORD_TP_TXT_SFX_C = "C";

    /** Value of ORD_TP_TXT : W */
    String VAL_ORD_TP_TXT_SFX_W = "W";

    /** Value of ORD_TP_TXT : W */
    String VAL_ORD_TP_TXT_OUTBOUND_CONFIGURATION_CHANGE = "WOC";

    /** Value of ORD_TP_TXT : W */
    String VAL_ORD_TP_TXT_OUTBOUND_DIPOSAL = "VRD";

    /** Value of PRINT_TP_CD : B */
    String VAL_PRINT_TP_CD_B = "B";

    /** Value of ADDR_LB_FMT_TXT : ASN */
    String VAL_ADDR_LB_FMT_TXT_ASN = "ASN";

    /** Value of ADDR_LB_FMT_TXT : S329 */
    String VAL_ADDR_LB_FMT_TXT_S329 = "S329";

    /** Value of ADDR_LB_FMT_TXT : EXPORT */
    String VAL_ADDR_LB_FMT_TXT_EXPORT = "EXPORT";

    /** Value of PUERTO_RICO : PR */
    String VAL_ST_PUERTO_RICO = "PR";

    /** Value of SEGMENT_ID : 1 */
    BigDecimal VAL_SEGMENT_ID_1 = BigDecimal.ONE;

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

    /** Value of PRTY_NUM_TXT : 10 */
    String VAL_PRTY_NUM_TXT_10 = "10";

    /** Value of ST_CD : XX */
    String VAL_ST_CD_XX = "XX";

    /** Value of POST_CD : 99999 */
    String VAL_POST_CD_99999 = "99999";

    /** Value of RTRN_CTAC_NM_TXT : Attn: Customer Service */
    String VAL_RTRN_CTAC_NM_TXT_ATTN = "Attn: Customer Service";

    /** Value of PACK_CD_TXT : * */
    String VAL_PACK_CD_TXT_ALL = "*";

    /** Value of USR_CD_TXT : * */
    String VAL_USR_CD_TXT_ALL = "*";

    /** Value of SER_NUM_UOM_TXT : EA */
    String VAL_SER_NUM_UOM_TXT_EA = "EA";

    /** Value of ITEM_CD_TXT : [CONFIG] */
    String VAL_ITEM_CD_TXT_SFX = " [CONFIG]";

    /** Value of CHRG_TO_CUST_CD : E6026 */
    String VAL_CHRG_TO_CUST_CD_E6026 = "E6026";

    /** Value of CHRG_TO_CUST_CD : E6A26 */
    String VAL_CHRG_TO_CUST_CD_E6A26 = "E6A26";

    /** Value of CHRG_TO_CUST_CD : E6338 */
    String VAL_CHRG_TO_CUST_CD_E6338 = "E6338";

    /** Value of FRT_TERM_TXT : C */
    String VAL_FRT_TERM_TXT_C = "C";

    /** Value of FRT_TERM_TXT : P */
    String VAL_FRT_TERM_TXT_P = "P";

    /** Value of FRT_TERM_TXT : H */
    String VAL_FRT_TERM_TXT_H = "H";

    /** Value of FRT_TERM_TXT : O */
    String VAL_FRT_TERM_TXT_O = "O";

    /** Value of WMS_FRT_OUT_CD : 0 */
    String VAL_WMS_FRT_OUT_CD_0 = "0";

    /** Value of WMS_FRT_OUT_CD : 1 */
    String VAL_WMS_FRT_OUT_CD_1 = "1";

    /** Value of WMS_FRT_OUT_CD : 2 */
    String VAL_WMS_FRT_OUT_CD_2 = "2";

    /** Value of WMS_FRT_OUT_CD : 3 */
    String VAL_WMS_FRT_OUT_CD_3 = "3";

    /** Size of ORD_CMNT_TXT : 40 */
    int VAL_ORD_CMNT_TXT_SIZE = 40;

    /** Value of RTE_CD_TXT : W */
    String VAL_RTE_CD_TXT_SFX_W = "W";

    /** Value of CARR_CD_TXT : ZZZZ */
    String VAL_CARR_CD_TXT_ZZZZ = "ZZZZ";

    /** Value of CARR_CD_TXT : 1 */
    String VAL_CARR_CD_TXT_1 = "1";

    /** Value of RTE_CD_TXT : K */
    String VAL_RTE_CD_TXT_K = "K";

    /** Value of RTE_CD_TXT : 1 */
    String VAL_RTE_CD_TXT_1 = "1";

    /** Value of RTE_NM_TXT : ItemChange/Kit */
    String VAL_RTE_NM_TXT_ITEM = "ItemChange/Kit";

    /** Value of RTE_NM_TXT : GROUND */
    String VAL_RTE_NM_TXT_GROUND = "GROUND";

    /** Value of WMS_RESRC_TXT : Preprocess */
    String VAL_WMS_RESRC_TXT = "Preprocess";

    /** Value of TP_VND_CD */
    String VAL_TP_VND_CD_HAZMAT = "HAZMAT";

    /** Value of WMS_PRTY_CD */
    String VAL_WMS_PRTY_CD = "5";

    /** Value of SHIP_TO_CUST_CD */
    String VAL_SHIP_TO_CUST_CD = "999999";

    /** Value of OUTBOUND */
    String VAL_OUTBOUND = "2";

    /** Value of WMS_SO_STS_CD */
    String VAL_WMS_SO_STS_CD = "R";

    /** Default Value of WMS_ORD_SRC_CD */
    String VAL_DEF_WMS_ORD_SRC_CD = "*";

    /** Default Value of WH_CD */
    String VAL_DEF_WH_CD = "*";

    /** Default Value of CTRY_CD */
    String VAL_DEF_CTRY_CD = "*";

    /** Size of END_CUST_ORD_NUM : 30 */
    int VAL_END_CUST_ORD_NUM_SIZE = 30;

    /** Size of SHIP_CTY_TXT : 20 */
    int VAL_SHIP_CTY_TXT_SIZE = 20;

    /** Size of PSN_NM : 20 */
    int VAL_PSN_NM_SIZE = 20;

    /** Size of Decimal Place */
    int VAL_EST_DECIMAL_PLACE = 2;

    /** Substring end position for SO_CRAT_TS */
    int LG_SO_CRAT_TS = 14;

    /** Substring end position for CUST_DC_NUM */
    int LG_CUST_DC_NUM = 8;

    /** Value 1 */
    String VAL_1 = "1";

    /** Value 2 */
    String VAL_2 = "2";

    /** Value 000000 */
    String VAL_000000 = "000000";

    /** Value Blank */
    String VAL_BLANK = " ";

    /** Value Blank */
    String VAL_BLANK2 = "  ";

    /** Value / */
    String VAL_SLASH = "/";

    /** Value : */
    String VAL_COLON = ":";

    /** Value , */
    String VAL_COMMA = ",";

    /** Value : HAZMAT */
    String VAL_HAZMAT = "HAZMAT";

    /** Value of multiply ORD_LINE_TXT : 1000 */
    BigDecimal VAL_ORD_LINE_TXT_MULT = new BigDecimal(1000);

    /** Value of ORD_CMNT_TXT : [SS] Status Change from { */
    String VAL_ORD_CMNT_TXT_PFX = "[SS] Status Change from {";

    /** Value of ORD_CMNT_TXT : } to { */
    String VAL_ORD_CMNT_TXT_CONJ = "} to {";

    /** Value of ORD_CMNT_TXT : } */
    String VAL_ORD_CMNT_TXT_SFX = "}";

    /** Value of MAX TOT_SHIP_PRC_AMT_NUM */
    BigDecimal VAL_MAX_TOT_SHIP_PRC_AMT_NUM = new BigDecimal("9999999999999.99");

    /** Value of MAX_QTY */
    BigDecimal VAL_MAX_QTY = new BigDecimal("9999999999999.99");

    /** Value of MIN_DATE : 19000101 */
    String VAL_MIN_DATE = "19000101";

    /** Value of MAX_DATE : 99991231 */
    String VAL_MAX_DATE = "99991231";

    /** Format of SYSDATE : yyyyMMddHHmmss */
    String FMT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /** Format of SYSDATE : yyyyMMddHHmmss */
    String FMT_YYYYMMDDHHMMSS2 = "yyyy-MM-dd HH:mm:ss";

    /** Format of SYSDATE : yyyy-MM-dd 00:00:00 */
    String FMT_YYYYMMDD000000 = "yyyy-MM-dd 00:00:00";

    /** Format of Number : 0 */
    String FMT_0 = "0";

    /** Format of Number : 0.00 */
    String FMT_0_00 = "0.00";

    /** Format of Number : 0.000 */
    String FMT_0_000 = "0.000";

    /** Substring start position for STK_STS_CD */
    int LG_CUT_STK_STS_CD_FROM = 1;

    /** Substring end position for STK_STS_CD */
    int LG_CUT_STK_STS_CD_TO = 2;

    /** Substring start position for RTL_WH_CD */
    int LG_CUT_RTL_WH_CD_FROM = 0;

    /** Substring end position for RTL_WH_CD */
    int LG_CUT_RTL_WH_CD_TO = 4;

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

    /** Substring start position for WMS_SO_ID */
    int LG_CUT_WMS_SO_ID_FROM = 21;

    /** Substring end position for WMS_SO_ID */
    int LG_CUT_WMS_SO_ID_TO = 36;

    /* HDR */

    /** Substring start position for WMS_ORD_NUM */
    int LG_CUT_HDR_WMS_ORD_NUM_FROM = 36;

    /** Substring end position for WMS_ORD_NUM */
    int LG_CUT_HDR_WMS_ORD_NUM_TO = 51;

    /** Substring start position for ALT_DOC_NUM */
    int LG_CUT_HDR_ALT_DOC_NUM_FROM = 51;

    /** Substring end position for ALT_DOC_NUM */
    int LG_CUT_HDR_ALT_DOC_NUM_TO = 66;

    /** Substring start position for CUST_ORD_NUM */
    int LG_CUT_HDR_CUST_ORD_NUM_FROM = 66;

    /** Substring end position for CUST_ORD_NUM */
    int LG_CUT_HDR_CUST_ORD_NUM_TO = 96;

    /** Substring start position for CHRG_TO_CUST_CD */
    int LG_CUT_HDR_CHRG_TO_CUST_CD_FROM = 96;

    /** Substring end position for CHRG_TO_CUST_CD */
    int LG_CUT_HDR_CHRG_TO_CUST_CD_TO = 106;

    /** Substring start position for BILL_TO_CUST_CD */
    int LG_CUT_HDR_BILL_TO_CUST_CD_FROM = 106;

    /** Substring end position for BILL_TO_CUST_CD */
    int LG_CUT_HDR_BILL_TO_CUST_CD_TO = 116;

    /** Substring start position for SHIP_TO_CUST_CD */
    int LG_CUT_HDR_SHIP_TO_CUST_CD_FROM = 116;

    /** Substring end position for SHIP_TO_CUST_CD */
    int LG_CUT_HDR_SHIP_TO_CUST_CD_TO = 126;

    /** Substring start position for WMS_PRTY_CD */
    int LG_CUT_HDR_WMS_PRTY_CD_FROM = 126;

    /** Substring end position for WMS_PRTY_CD */
    int LG_CUT_HDR_WMS_PRTY_CD_TO = 127;

    /** Substring start position for WMS_ORD_TP_CD */
    int LG_CUT_HDR_WMS_ORD_TP_CD_FROM = 127;

    /** Substring end position for WMS_ORD_TP_CD */
    int LG_CUT_HDR_WMS_ORD_TP_CD_TO = 128;

    /** Substring start position for WMS_TRX_CD */
    int LG_CUT_HDR_WMS_TRX_CD_FROM = 128;

    /** Substring end position for WMS_TRX_CD */
    int LG_CUT_HDR_WMS_TRX_CD_TO = 130;

    /** Substring start position for WMS_ORD_SRC_CD */
    int LG_CUT_HDR_WMS_ORD_SRC_CD_FROM = 130;

    /** Substring end position for WMS_ORD_SRC_CD */
    int LG_CUT_HDR_WMS_ORD_SRC_CD_TO = 132;

    /** Substring start position for WMS_SO_STS_CD */
    int LG_CUT_HDR_WMS_SO_STS_CD_FROM = 132;

    /** Substring end position for WMS_SO_STS_CD */
    int LG_CUT_HDR_WMS_SO_STS_CD_TO = 133;

    /** Substring start position for SO_SHIP_VIA_CD */
    int LG_CUT_HDR_SO_SHIP_VIA_CD_FROM = 133;

    /** Substring end position for SO_SHIP_VIA_CD */
    int LG_CUT_HDR_SO_SHIP_VIA_CD_TO = 135;

    /** Substring start position for SHIP_VIA_DESC_TXT */
    int LG_CUT_HDR_SHIP_VIA_DESC_TXT_FROM = 135;

    /** Substring end position for SHIP_VIA_DESC_TXT */
    int LG_CUT_HDR_SHIP_VIA_DESC_TXT_TO = 170;

    /** Substring start position for CRAT_DT_TM_TS */
    int LG_CUT_HDR_CRAT_DT_TM_TS_FROM = 170;

    /** Substring end position for CRAT_DT_TM_TS */
    int LG_CUT_HDR_CRAT_DT_TM_TS_TO = 180;

    /** Substring start position for EST_SHIP_DT_TM_TS */
    int LG_CUT_HDR_EST_SHIP_DT_TM_TS_FROM = 180;

    /** Substring end position for EST_SHIP_DT_TM_TS */
    int LG_CUT_HDR_EST_SHIP_DT_TM_TS_TO = 190;

    /** Substring start position for WMS_RQST_DT_TM_TS */
    int LG_CUT_HDR_WMS_RQST_DT_TM_TS_FROM = 190;

    /** Substring end position for WMS_RQST_DT_TM_TS */
    int LG_CUT_HDR_WMS_RQST_DT_TM_TS_TO = 200;

    /** Substring start position for WMS_PRINT_DT_TM_TS */
    int LG_CUT_HDR_WMS_PRINT_DT_TM_TS_FROM = 200;

    /** Substring end position for WMS_PRINT_DT_TM_TS */
    int LG_CUT_HDR_WMS_PRINT_DT_TM_TS_TO = 218;

    /** Substring start position for FRT_OUT_CD */
    int LG_CUT_HDR_FRT_OUT_CD_FROM = 218;

    /** Substring end position for FRT_OUT_CD */
    int LG_CUT_HDR_FRT_OUT_CD_TO = 219;

    /** Substring start position for FRT_OUT_DESC_TXT */
    int LG_CUT_HDR_FRT_OUT_DESC_TXT_FROM = 219;

    /** Substring end position for FRT_OUT_DESC_TXT */
    int LG_CUT_HDR_FRT_OUT_DESC_TXT_TO = 254;

    /** Substring start position for WMS_DEPT_CD */
    int LG_CUT_HDR_WMS_DEPT_CD_FROM = 254;

    /** Substring end position for WMS_DEPT_CD */
    int LG_CUT_HDR_WMS_DEPT_CD_TO = 259;

    /** Substring start position for PAY_TERM_CD */
    int LG_CUT_HDR_PAY_TERM_CD_FROM = 259;

    /** Substring end position for PAY_TERM_CD */
    int LG_CUT_HDR_PAY_TERM_CD_TO = 261;

    /** Substring start position for WMS_SO_CARR_CD */
    int LG_CUT_HDR_WMS_SO_CARR_CD_FROM = 261;

    /** Substring end position for WMS_SO_CARR_CD */
    int LG_CUT_HDR_WMS_SO_CARR_CD_TO = 265;

    /** Substring start position for IND_OTM_ADDR_SWTH_FLG */
    int LG_CUT_HDR_IND_OTM_ADDR_SWTH_FLG_FROM = 265;

    /** Substring end position for IND_OTM_ADDR_SWTH_FLG */
    int LG_CUT_HDR_IND_OTM_ADDR_SWTH_FLG_TO = 266;

    /** Substring start position for IND_SCC14_FLG */
    int LG_CUT_HDR_IND_SCC14_FLG_FROM = 267;

    /** Substring end position for IND_SCC14_FLG */
    int LG_CUT_HDR_IND_SCC14_FLG_TO = 268;

    /** Substring start position for IND_UCC_FLG */
    int LG_CUT_HDR_IND_UCC_FLG_FROM = 268;

    /** Substring end position for IND_UCC_FLG */
    int LG_CUT_HDR_IND_UCC_FLG_TO = 269;

    /** Substring start position for IND_MIXED_PLT_FLG */
    int LG_CUT_HDR_IND_MIXED_PLT_FLG_FROM = 269;

    /** Substring end position for IND_MIXED_PLT_FLG */
    int LG_CUT_HDR_IND_MIXED_PLT_FLG_TO = 270;

    /** Substring start position for IND_PLT_LB_FLG */
    int LG_CUT_HDR_IND_PLT_LB_FLG_FROM = 270;

    /** Substring end position for IND_PLT_LB_FLG */
    int LG_CUT_HDR_IND_PLT_LB_FLG_TO = 271;

    /** Substring start position for IND_NON_ASN_FLG */
    int LG_CUT_HDR_IND_NON_ASN_FLG_FROM = 272;

    /** Substring end position for IND_NON_ASN_FLG */
    int LG_CUT_HDR_IND_NON_ASN_FLG_TO = 273;

    /** Substring start position for IND_ASN_FLG */
    int LG_CUT_HDR_IND_ASN_FLG_FROM = 266;

    /** Substring end position for IND_ASN_FLG */
    int LG_CUT_HDR_IND_ASN_FLG_TO = 267;

    /** Substring start position for IND_UCC_NUM_FLG */
    int LG_CUT_HDR_IND_UCC_NUM_FLG_FROM = 271;

    /** Substring end position for IND_UCC_NUM_FLG */
    int LG_CUT_HDR_IND_UCC_NUM_FLG_TO = 272;

    /** Substring start position for WMS_LB_NUM */
    int LG_CUT_HDR_WMS_LB_NUM_FROM = 273;

    /** Substring end position for WMS_LB_NUM */
    int LG_CUT_HDR_WMS_LB_NUM_TO = 283;

    /** Substring start position for CANC_BY_DT_TM_TS */
    int LG_CUT_HDR_CANC_BY_DT_TM_TS_FROM = 336;

    /** Substring end position for CANC_BY_DT_TM_TS */
    int LG_CUT_HDR_CANC_BY_DT_TM_TS_TO = 346;

    /** Substring start position for CUST_STORE_NUM */
    int LG_CUT_HDR_CUST_STORE_NUM_FROM = 283;

    /** Substring end position for CUST_STORE_NUM */
    int LG_CUT_HDR_CUST_STORE_NUM_TO = 291;

    /** Substring start position for CUST_DC_NUM */
    int LG_CUT_HDR_CUST_DC_NUM_FROM = 291;

    /** Substring end position for CUST_DC_NUM */
    int LG_CUT_HDR_CUST_DC_NUM_TO = 299;

    /** Substring start position for WMS_CUST_DEPT_NUM */
    int LG_CUT_HDR_WMS_CUST_DEPT_NUM_FROM = 299;

    /** Substring end position for WMS_CUST_DEPT_NUM */
    int LG_CUT_HDR_WMS_CUST_DEPT_NUM_TO = 304;

    /** Substring start position for TOT_SHIP_PRC_AMT_NUM */
    int LG_CUT_HDR_TOT_SHIP_PRC_AMT_NUM_FROM = 304;

    /** Substring end position for TOT_SHIP_PRC_AMT_NUM */
    int LG_CUT_HDR_TOT_SHIP_PRC_AMT_NUM_TO = 318;

    /** Substring start position for RTRN_LB_CD */
    int LG_CUT_HDR_RTRN_LB_CD_FROM = 318;

    /** Substring end position for RTRN_LB_CD */
    int LG_CUT_HDR_RTRN_LB_CD_TO = 320;

    /** Substring start position for TP_VND_CD */
    int LG_CUT_HDR_TP_VND_CD_FROM = 320;

    /** Substring end position for TP_VND_CD */
    int LG_CUT_HDR_TP_VND_CD_TO = 332;

    /** Substring start position for EDI_TRNSP_TP_CD */
    int LG_CUT_HDR_EDI_TRNSP_TP_CD_FROM = 332;

    /** Substring end position for EDI_TRNSP_TP_CD */
    int LG_CUT_HDR_EDI_TRNSP_TP_CD_TO = 334;

    /** Substring start position for WMS_PMT_TERM_CD */
    int LG_CUT_HDR_WMS_PMT_TERM_CD_FROM = 334;

    /** Substring end position for WMS_PMT_TERM_CD */
    int LG_CUT_HDR_WMS_PMT_TERM_CD_TO = 336;

    /** Substring start position for IND_CONFIG_FLG */
    int LG_CUT_HDR_IND_CONFIG_FLG_FROM = 346;

    /** Substring end position for IND_CONFIG_FLG */
    int LG_CUT_HDR_IND_CONFIG_FLG_TO = 347;

    /** Substring start position for HOST_ORD_DT_TM_TS */
    int LG_CUT_HDR_HOST_ORD_DT_TM_TS_FROM = 347;

    /** Substring end position for HOST_ORD_DT_TM_TS */
    int LG_CUT_HDR_HOST_ORD_DT_TM_TS_TO = 357;

    /** Substring start position for WMS_NET_AMT_NUM */
    int LG_CUT_HDR_WMS_NET_AMT_NUM_FROM = 357;

    /** Substring end position for WMS_NET_AMT_NUM */
    int LG_CUT_HDR_WMS_NET_AMT_NUM_TO = 371;

    /** Substring start position for WMS_NET_DISC_AMT_NUM */
    int LG_CUT_HDR_WMS_NET_DISC_AMT_NUM_FROM = 371;

    /** Substring end position for WMS_NET_DISC_AMT_NUM */
    int LG_CUT_HDR_WMS_NET_DISC_AMT_NUM_TO = 385;

    /** Substring start position for SHPG_HDLG_AMT_NUM */
    int LG_CUT_HDR_SHPG_HDLG_AMT_NUM_FROM = 413;

    /** Substring end position for SHPG_HDLG_AMT_NUM */
    int LG_CUT_HDR_SHPG_HDLG_AMT_NUM_TO = 427;

    /** Substring start position for SHPG_HDLG_DISC_AMT_NUM */
    int LG_CUT_HDR_SHPG_HDLG_DISC_AMT_NUM_FROM = 385;

    /** Substring end position for SHPG_HDLG_DISC_AMT_NUM */
    int LG_CUT_HDR_SHPG_HDLG_DISC_AMT_NUM_TO = 399;

    /** Substring start position for TOT_DISC_AMT_NUM */
    int LG_CUT_HDR_TOT_DISC_AMT_NUM_FROM = 399;

    /** Substring end position for TOT_DISC_AMT_NUM */
    int LG_CUT_HDR_TOT_DISC_AMT_NUM_TO = 413;

    /** Substring start position for NET_TAX_AMT_NUM */
    int LG_CUT_HDR_NET_TAX_AMT_NUM_FROM = 427;

    /** Substring end position for NET_TAX_AMT_NUM */
    int LG_CUT_HDR_NET_TAX_AMT_NUM_TO = 441;

    /** Substring start position for SHPG_HDLG_TAX_AMT_NUM */
    int LG_CUT_HDR_SHPG_HDLG_TAX_AMT_NUM_FROM = 441;

    /** Substring end position for SHPG_HDLG_TAX_AMT_NUM */
    int LG_CUT_HDR_SHPG_HDLG_TAX_AMT_NUM_TO = 455;

    /** Substring start position for TOT_TAX_AMT_NUM */
    int LG_CUT_HDR_TOT_TAX_AMT_NUM_FROM = 455;

    /** Substring end position for TOT_TAX_AMT_NUM */
    int LG_CUT_HDR_TOT_TAX_AMT_NUM_TO = 469;

    /** Substring start position for TOT_ORD_AMT_NUM */
    int LG_CUT_HDR_TOT_ORD_AMT_NUM_FROM = 469;

    /** Substring end position for TOT_ORD_AMT_NUM */
    int LG_CUT_HDR_TOT_ORD_AMT_NUM_TO = 483;

    /** Substring start position for TOT_ORD_QTY */
    int LG_CUT_HDR_TOT_ORD_QTY_FROM = 483;

    /** Substring end position for TOT_ORD_QTY */
    int LG_CUT_HDR_TOT_ORD_QTY_TO = 492;

    /** Substring start position for IND_SGN_REQ_FLG */
    int LG_CUT_HDR_IND_SGN_REQ_FLG_FROM = 492;

    /** Substring end position for IND_SGN_REQ_FLG */
    int LG_CUT_HDR_IND_SGN_REQ_FLG_TO = 493;

    /** Substring start position for BILL_ACCT_NUM */
    int LG_CUT_HDR_BILL_ACCT_NUM_FROM = 493;

    /** Substring end position for BILL_ACCT_NUM */
    int LG_CUT_HDR_BILL_ACCT_NUM_TO = 513;

    /** Substring start position for END_CUST_ORD_NUM */
    int LG_CUT_HDR_END_CUST_ORD_NUM_FROM = 513;

    /** Substring end position for END_CUST_ORD_NUM */
    int LG_CUT_HDR_END_CUST_ORD_NUM_TO = 548;

    /** Substring start position for ALT_CUST_ORD_NUM */
    int LG_CUT_HDR_ALT_CUST_ORD_NUM_FROM = 548;

    /** Substring end position for ALT_CUST_ORD_NUM */
    int LG_CUT_HDR_ALT_CUST_ORD_NUM_TO = 583;

    /* DTL */

    /** Substring start position for RTL_SWH_CD */
    int LG_CUT_DTL_RTL_SWH_CD_FROM = 17;

    /** Substring end position for RTL_SWH_CD */
    int LG_CUT_DTL_RTL_SWH_CD_TO = 21;

    /** Substring start position for WMS_LINE_NUM */
    int LG_CUT_DTL_WMS_LINE_NUM_FROM = 36;

    /** Substring end position for WMS_LINE_NUM */
    int LG_CUT_DTL_WMS_LINE_NUM_TO = 39;

    /** Substring start position for WMS_MDSE_CD */
    int LG_CUT_DTL_WMS_MDSE_CD_FROM = 39;

    /** Substring end position for WMS_MDSE_CD */
    int LG_CUT_DTL_WMS_MDSE_CD_TO = 64;

    /** Substring start position for S80_STK_STS_CD */
    int LG_CUT_DTL_S80_STK_STS_CD_FROM = 64;

    /** Substring end position for S80_STK_STS_CD */
    int LG_CUT_DTL_S80_STK_STS_CD_TO = 66;

    /** Substring start position for CUST_MDSE_CD */
    int LG_CUT_DTL_CUST_MDSE_CD_FROM = 66;

    /** Substring end position for CUST_MDSE_CD */
    int LG_CUT_DTL_CUST_MDSE_CD_TO = 91;

    /** Substring start position for WMS_ORD_QTY */
    int LG_CUT_DTL_WMS_ORD_QTY_FROM = 91;

    /** Substring end position for WMS_ORD_QTY */
    int LG_CUT_DTL_WMS_ORD_QTY_TO = 100;

    /** Substring start position for BACK_ORD_QTY_NUM */
    int LG_CUT_DTL_BACK_ORD_QTY_NUM_FROM = 100;

    /** Substring end position for BACK_ORD_QTY_NUM */
    int LG_CUT_DTL_BACK_ORD_QTY_NUM_TO = 109;

    /** Substring start position for WMS_SHIP_QTY */
    int LG_CUT_DTL_WMS_SHIP_QTY_FROM = 109;

    /** Substring end position for WMS_SHIP_QTY */
    int LG_CUT_DTL_WMS_SHIP_QTY_TO = 118;

    /** Substring start position for UNIT_PRC_AMT_NUM */
    int LG_CUT_DTL_UNIT_PRC_AMT_NUM_FROM = 118;

    /** Substring end position for UNIT_PRC_AMT_NUM */
    int LG_CUT_DTL_UNIT_PRC_AMT_NUM_TO = 132;

    /** Substring start position for UNIT_DISC_AMT_NUM */
    int LG_CUT_DTL_UNIT_DISC_AMT_NUM_FROM = 132;

    /** Substring end position for UNIT_DISC_AMT_NUM */
    int LG_CUT_DTL_UNIT_DISC_AMT_NUM_TO = 146;

    /** Substring start position for UNIT_DISC_PRC_AMT_NUM */
    int LG_CUT_DTL_UNIT_DISC_PRC_AMT_NUM_FROM = 146;

    /** Substring end position for UNIT_DISC_PRC_AMT_NUM */
    int LG_CUT_DTL_UNIT_DISC_PRC_AMT_NUM_TO = 160;

    /** Substring start position for WMS_TOT_AMT_NUM */
    int LG_CUT_DTL_WMS_TOT_AMT_NUM_FROM = 160;

    /** Substring end position for WMS_TOT_AMT_NUM */
    int LG_CUT_DTL_WMS_TOT_AMT_NUM_TO = 174;

    /** Substring start position for IND_SER_ID */
    int LG_CUT_DTL_IND_SER_ID_FROM = 174;

    /** Substring end position for IND_SER_ID */
    int LG_CUT_DTL_IND_SER_ID_TO = 175;

    /** Substring start position for IND_VOID_ALLW_FLG */
    int LG_CUT_DTL_IND_VOID_ALLW_FLG_FROM = 175;

    /** Substring end position for IND_VOID_ALLW_FLG */
    int LG_CUT_DTL_IND_VOID_ALLW_FLG_TO = 176;

    /** Substring start position for S80_STK_STS_CD_TO_CD */
    int LG_CUT_DTL_S80_STK_STS_CD_TO_CD_FROM = 176;

    /** Substring end position for S80_STK_STS_CD_TO_CD */
    int LG_CUT_DTL_S80_STK_STS_CD_TO_CD_TO = 178;

    /** Substring start position for MDSE_CD_SET_CD */
    int LG_CUT_DTL_MDSE_CD_SET_CD_FROM = 178;

    /** Substring end position for MDSE_CD_SET_CD */
    int LG_CUT_DTL_MDSE_CD_SET_CD_TO = 203;

    /** Substring start position for MDSE_CD_SET_DESC_TXT */
    int LG_CUT_DTL_MDSE_CD_SET_DESC_TXT_FROM = 203;

    /** Substring end position for MDSE_CD_SET_DESC_TXT */
    int LG_CUT_DTL_MDSE_CD_SET_DESC_TXT_TO = 238;

    /** Substring start position for SHIP_SET_QTY */
    int LG_CUT_DTL_SHIP_SET_QTY_FROM = 238;

    /** Substring end position for SHIP_SET_QTY */
    int LG_CUT_DTL_SHIP_SET_QTY_TO = 247;

    /** Substring start position for UNIT_INSD_QTY */
    int LG_CUT_DTL_UNIT_INSD_QTY_FROM = 247;

    /** Substring end position for UNIT_INSD_QTY */
    int LG_CUT_DTL_UNIT_INSD_QTY_TO = 256;

    /** Substring start position for ESS_PO_SQ_NUM */
    int LG_CUT_DTL_ESS_PO_SQ_NUM_FROM = 256;

    /** Substring end position for ESS_PO_SQ_NUM */
    int LG_CUT_DTL_ESS_PO_SQ_NUM_TO = 259;

    /** Substring start position for ESS_MDSE_LINE_NUM */
    int LG_CUT_DTL_ESS_MDSE_LINE_NUM_FROM = 259;

    /** Substring end position for ESS_MDSE_LINE_NUM */
    int LG_CUT_DTL_ESS_MDSE_LINE_NUM_TO = 262;

    /** Substring start position for ESS_LINE_NUM */
    int LG_CUT_DTL_ESS_LINE_NUM_FROM = 262;

    /** Substring end position for ESS_LINE_NUM */
    int LG_CUT_DTL_ESS_LINE_NUM_TO = 264;

    /** Substring start position for ESS_MSG_LINE_NUM */
    int LG_CUT_DTL_ESS_MSG_LINE_NUM_FROM = 264;

    /** Substring end position for ESS_MSG_LINE_NUM */
    int LG_CUT_DTL_ESS_MSG_LINE_NUM_TO = 266;

    /** Substring start position for TOT_WT_AMT_NUM */
    int LG_CUT_DTL_TOT_WT_AMT_NUM_FROM = 274;

    /** Substring end position for TOT_WT_AMT_NUM */
    int LG_CUT_DTL_TOT_WT_AMT_NUM_TO = 286;

    /** Substring start position for TOT_VOL_AMT_NUM */
    int LG_CUT_DTL_TOT_VOL_AMT_NUM_FROM = 266;

    /** Substring end position for TOT_VOL_AMT_NUM */
    int LG_CUT_DTL_TOT_VOL_AMT_NUM_TO = 274;

    /** Substring start position for SO_MDSE_TP_CD */
    int LG_CUT_DTL_SO_MDSE_TP_CD_FROM = 286;

    /** Substring end position for SO_MDSE_TP_CD */
    int LG_CUT_DTL_SO_MDSE_TP_CD_TO = 306;

    /** Substring start position for WMS_PACK_TP_CD */
    int LG_CUT_DTL_WMS_PACK_TP_CD_FROM = 306;

    /** Substring end position for WMS_PACK_TP_CD */
    int LG_CUT_DTL_WMS_PACK_TP_CD_TO = 336;

    /** Substring start position for BAT_CPTR_REQ_FLG */
    int LG_CUT_DTL_BAT_CPTR_REQ_FLG_FROM = 336;

    /** Substring end position for BAT_CPTR_REQ_FLG */
    int LG_CUT_DTL_BAT_CPTR_REQ_FLG_TO = 337;

    /** Substring start position for IND_CONFIG_FLG */
    int LG_CUT_DTL_IND_CONFIG_FLG_FROM = 337;

    /** Substring end position for IND_CONFIG_FLG */
    int LG_CUT_DTL_IND_CONFIG_FLG_TO = 338;

    /** Substring start position for WMS_NET_AMT_NUM */
    int LG_CUT_DTL_WMS_NET_AMT_NUM_FROM = 338;

    /** Substring end position for WMS_NET_AMT_NUM */
    int LG_CUT_DTL_WMS_NET_AMT_NUM_TO = 352;

    /** Substring start position for WMS_DISC_AMT_NUM */
    int LG_CUT_DTL_WMS_DISC_AMT_NUM_FROM = 352;

    /** Substring end position for WMS_DISC_AMT_NUM */
    int LG_CUT_DTL_WMS_DISC_AMT_NUM_TO = 366;

    /** Substring start position for WMS_TAX_AMT_NUM */
    int LG_CUT_DTL_WMS_TAX_AMT_NUM_FROM = 366;

    /** Substring end position for WMS_TAX_AMT_NUM */
    int LG_CUT_DTL_WMS_TAX_AMT_NUM_TO = 380;

    /* SHIP */

    /** Substring start position for WMS_CUST_CD */
    int LG_CUT_SHIP_WMS_CUST_CD_FROM = 36;

    /** Substring end position for WMS_CUST_CD */
    int LG_CUT_SHIP_WMS_CUST_CD_TO = 46;

    /** Substring start position for WMS_SHIP_TO_NM_01 */
    int LG_CUT_SHIP_WMS_SHIP_TO_NM_01_FROM = 46;

    /** Substring end position for WMS_SHIP_TO_NM_01 */
    int LG_CUT_SHIP_WMS_SHIP_TO_NM_01_TO = 81;

    /** Substring start position for WMS_SHIP_TO_NM_02 */
    int LG_CUT_SHIP_WMS_SHIP_TO_NM_02_FROM = 81;

    /** Substring end position for WMS_SHIP_TO_NM_02 */
    int LG_CUT_SHIP_WMS_SHIP_TO_NM_02_TO = 116;

    /** Substring start position for FIRST_LINE_ADDR */
    int LG_CUT_SHIP_FIRST_LINE_ADDR_FROM = 116;

    /** Substring end position for FIRST_LINE_ADDR */
    int LG_CUT_SHIP_FIRST_LINE_ADDR_TO = 151;

    /** Substring start position for SCD_LINE_ADDR */
    int LG_CUT_SHIP_SCD_LINE_ADDR_FROM = 151;

    /** Substring end position for SCD_LINE_ADDR */
    int LG_CUT_SHIP_SCD_LINE_ADDR_TO = 186;

    /** Substring start position for THIRD_LINE_ADDR */
    int LG_CUT_SHIP_THIRD_LINE_ADDR_FROM = 186;

    /** Substring end position for THIRD_LINE_ADDR */
    int LG_CUT_SHIP_THIRD_LINE_ADDR_TO = 221;

    /** Substring start position for FRTH_LINE_ADDR */
    int LG_CUT_SHIP_FRTH_LINE_ADDR_FROM = 221;

    /** Substring end position for FRTH_LINE_ADDR */
    int LG_CUT_SHIP_FRTH_LINE_ADDR_TO = 256;

    /** Substring start position for CTY_ADDR */
    int LG_CUT_SHIP_CTY_ADDR_FROM = 256;

    /** Substring end position for CTY_ADDR */
    int LG_CUT_SHIP_CTY_ADDR_TO = 276;

    /** Substring start position for ST_CD */
    int LG_CUT_SHIP_ST_CD_FROM = 276;

    /** Substring end position for ST_CD */
    int LG_CUT_SHIP_ST_CD_TO = 278;

    /** Substring start position for POST_CD */
    int LG_CUT_SHIP_POST_CD_FROM = 278;

    /** Substring end position for POST_CD */
    int LG_CUT_SHIP_POST_CD_TO = 293;

    /** Substring start position for CTRY_CD */
    int LG_CUT_SHIP_CTRY_CD_FROM = 293;

    /** Substring end position for CTRY_CD */
    int LG_CUT_SHIP_CTRY_CD_TO = 295;

    /** Substring start position for WMS_SHIP_TO_CTAC_NM */
    int LG_CUT_SHIP_WMS_SHIP_TO_CTAC_NM_FROM = 295;

    /** Substring end position for WMS_SHIP_TO_CTAC_NM */
    int LG_CUT_SHIP_WMS_SHIP_TO_CTAC_NM_TO = 320;

    /** Substring start position for SHIP_TO_CTAC_NUM */
    int LG_CUT_SHIP_SHIP_TO_CTAC_NUM_FROM = 320;

    /** Substring end position for SHIP_TO_CTAC_NUM */
    int LG_CUT_SHIP_SHIP_TO_CTAC_NUM_TO = 335;

    /* TEXT */

    /** Substring start position for WMS_TXT_CD */
    int LG_CUT_TEXT_WMS_TXT_CD_FROM = 36;

    /** Substring end position for WMS_TXT_CD */
    int LG_CUT_TEXT_WMS_TXT_CD_TO = 37;

    /** Substring start position for WMS_PRINT_TP_CD */
    int LG_CUT_TEXT_WMS_PRINT_TP_CD_FROM = 37;

    /** Substring end position for WMS_PRINT_TP_CD */
    int LG_CUT_TEXT_WMS_PRINT_TP_CD_TO = 38;

    /** Substring start position for INBD_SO_MSG_TXT_01 */
    int LG_CUT_TEXT_INBD_SO_MSG_TXT_01_FROM = 38;

    /** Substring end position for INBD_SO_MSG_TXT_01 */
    int LG_CUT_TEXT_INBD_SO_MSG_TXT_01_TO = 103;

    /** Substring start position for INBD_SO_MSG_TXT_02 */
    int LG_CUT_TEXT_INBD_SO_MSG_TXT_02_FROM = 103;

    /** Substring end position for INBD_SO_MSG_TXT_02 */
    int LG_CUT_TEXT_INBD_SO_MSG_TXT_02_TO = 168;

    /** Substring start position for INBD_SO_MSG_TXT_03 */
    int LG_CUT_TEXT_INBD_SO_MSG_TXT_03_FROM = 168;

    /** Substring end position for INBD_SO_MSG_TXT_03 */
    int LG_CUT_TEXT_INBD_SO_MSG_TXT_03_TO = 233;

    /** Substring start position for INBD_SO_MSG_TXT_04 */
    int LG_CUT_TEXT_INBD_SO_MSG_TXT_04_FROM = 233;

    /** Substring end position for INBD_SO_MSG_TXT_04 */
    int LG_CUT_TEXT_INBD_SO_MSG_TXT_04_TO = 298;

    /* CHRG */

    /** Substring start position for WMS_CUST_CD */
    int LG_CUT_CHRG_WMS_CUST_CD_FROM = 36;

    /** Substring end position for WMS_CUST_CD */
    int LG_CUT_CHRG_WMS_CUST_CD_TO = 46;

    /** Substring start position for WMS_CHRG_TO_NM_01 */
    int LG_CUT_CHRG_WMS_CHRG_TO_NM_01_FROM = 46;

    /** Substring end position for WMS_CHRG_TO_NM_01 */
    int LG_CUT_CHRG_WMS_CHRG_TO_NM_01_TO = 81;

    /** Substring start position for WMS_CHRG_TO_NM_02 */
    int LG_CUT_CHRG_WMS_CHRG_TO_NM_02_FROM = 81;

    /** Substring end position for WMS_CHRG_TO_NM_02 */
    int LG_CUT_CHRG_WMS_CHRG_TO_NM_02_TO = 116;

    /** Substring start position for FIRST_LINE_ADDR */
    int LG_CUT_CHRG_FIRST_LINE_ADDR_FROM = 116;

    /** Substring end position for FIRST_LINE_ADDR */
    int LG_CUT_CHRG_FIRST_LINE_ADDR_TO = 151;

    /** Substring start position for SCD_LINE_ADDR */
    int LG_CUT_CHRG_SCD_LINE_ADDR_FROM = 151;

    /** Substring end position for SCD_LINE_ADDR */
    int LG_CUT_CHRG_SCD_LINE_ADDR_TO = 186;

    /** Substring start position for THIRD_LINE_ADDR */
    int LG_CUT_CHRG_THIRD_LINE_ADDR_FROM = 186;

    /** Substring end position for THIRD_LINE_ADDR */
    int LG_CUT_CHRG_THIRD_LINE_ADDR_TO = 221;

    /** Substring start position for FRTH_LINE_ADDR */
    int LG_CUT_CHRG_FRTH_LINE_ADDR_FROM = 221;

    /** Substring end position for FRTH_LINE_ADDR */
    int LG_CUT_CHRG_FRTH_LINE_ADDR_TO = 256;

    /** Substring start position for CTY_ADDR */
    int LG_CUT_CHRG_CTY_ADDR_FROM = 256;

    /** Substring end position for CTY_ADDR */
    int LG_CUT_CHRG_CTY_ADDR_TO = 276;

    /** Substring start position for ST_CD */
    int LG_CUT_CHRG_ST_CD_FROM = 276;

    /** Substring end position for ST_CD */
    int LG_CUT_CHRG_ST_CD_TO = 278;

    /** Substring start position for POST_CD */
    int LG_CUT_CHRG_POST_CD_FROM = 278;

    /** Substring end position for POST_CD */
    int LG_CUT_CHRG_POST_CD_TO = 293;

    /** Substring start position for CTRY_CD */
    int LG_CUT_CHRG_CTRY_CD_FROM = 293;

    /** Substring end position for CTRY_CD */
    int LG_CUT_CHRG_CTRY_CD_TO = 295;

    /** Substring start position for WMS_CHRG_TO_CTAC_NM */
    int LG_CUT_CHRG_WMS_CHRG_TO_CTAC_NM_FROM = 295;

    /** Substring end position for WMS_CHRG_TO_CTAC_NM */
    int LG_CUT_CHRG_WMS_CHRG_TO_CTAC_NM_TO = 320;

    /** Substring start position for CHRG_TO_CTAC_NUM */
    int LG_CUT_CHRG_CHRG_TO_CTAC_NUM_FROM = 320;

    /** Substring end position for CHRG_TO_CTAC_NUM */
    int LG_CUT_CHRG_CHRG_TO_CTAC_NUM_TO = 335;

    /* BILL */

    /** Substring start position for WMS_CUST_CD */
    int LG_CUT_BILL_WMS_CUST_CD_FROM = 36;

    /** Substring end position for WMS_CUST_CD */
    int LG_CUT_BILL_WMS_CUST_CD_TO = 46;

    /** Substring start position for WMS_BILL_TO_NM_01 */
    int LG_CUT_BILL_WMS_BILL_TO_NM_01_FROM = 46;

    /** Substring end position for WMS_BILL_TO_NM_01 */
    int LG_CUT_BILL_WMS_BILL_TO_NM_01_TO = 81;

    /** Substring start position for WMS_BILL_TO_NM_02 */
    int LG_CUT_BILL_WMS_BILL_TO_NM_02_FROM = 81;

    /** Substring end position for WMS_BILL_TO_NM_02 */
    int LG_CUT_BILL_WMS_BILL_TO_NM_02_TO = 116;

    /** Substring start position for FIRST_LINE_ADDR */
    int LG_CUT_BILL_FIRST_LINE_ADDR_FROM = 116;

    /** Substring end position for FIRST_LINE_ADDR */
    int LG_CUT_BILL_FIRST_LINE_ADDR_TO = 151;

    /** Substring start position for SCD_LINE_ADDR */
    int LG_CUT_BILL_SCD_LINE_ADDR_FROM = 151;

    /** Substring end position for SCD_LINE_ADDR */
    int LG_CUT_BILL_SCD_LINE_ADDR_TO = 186;

    /** Substring start position for THIRD_LINE_ADDR */
    int LG_CUT_BILL_THIRD_LINE_ADDR_FROM = 186;

    /** Substring end position for THIRD_LINE_ADDR */
    int LG_CUT_BILL_THIRD_LINE_ADDR_TO = 221;

    /** Substring start position for FRTH_LINE_ADDR */
    int LG_CUT_BILL_FRTH_LINE_ADDR_FROM = 221;

    /** Substring end position for FRTH_LINE_ADDR */
    int LG_CUT_BILL_FRTH_LINE_ADDR_TO = 256;

    /** Substring start position for CTY_ADDR */
    int LG_CUT_BILL_CTY_ADDR_FROM = 256;

    /** Substring end position for CTY_ADDR */
    int LG_CUT_BILL_CTY_ADDR_TO = 276;

    /** Substring start position for ST_CD */
    int LG_CUT_BILL_ST_CD_FROM = 276;

    /** Substring end position for ST_CD */
    int LG_CUT_BILL_ST_CD_TO = 278;

    /** Substring start position for POST_CD */
    int LG_CUT_BILL_POST_CD_FROM = 278;

    /** Substring end position for POST_CD */
    int LG_CUT_BILL_POST_CD_TO = 293;

    /** Substring start position for CTRY_CD */
    int LG_CUT_BILL_CTRY_CD_FROM = 293;

    /** Substring end position for CTRY_CD */
    int LG_CUT_BILL_CTRY_CD_TO = 295;

    /** Substring start position for WMS_BILL_TO_CTAC_NM */
    int LG_CUT_BILL_WMS_BILL_TO_CTAC_NM_FROM = 295;

    /** Substring end position for WMS_BILL_TO_CTAC_NM */
    int LG_CUT_BILL_WMS_BILL_TO_CTAC_NM_TO = 320;

    /** Substring start position for BILL_TO_CTAC_NUM */
    int LG_CUT_BILL_BILL_TO_CTAC_NUM_FROM = 320;

    /** Substring end position for BILL_TO_CTAC_NUM */
    int LG_CUT_BILL_BILL_TO_CTAC_NUM_TO = 335;

    /* RTRN */
    /** Substring start position for WMS_CUST_CD */
    int LG_CUT_WMS_CUST_CD_FROM = 36;

    /** Substring end position for WMS_CUST_CD */
    int LG_CUT_WMS_CUST_CD_TO = 46;

    /** Substring start position for WMS_RTRN_TO_NM_01 */
    int LG_CUT_WMS_RTRN_TO_NM_01_FROM = 46;

    /** Substring end position for WMS_RTRN_TO_NM_01 */
    int LG_CUT_WMS_RTRN_TO_NM_01_TO = 81;

    /** Substring start position for WMS_RTRN_TO_NM_02 */
    int LG_CUT_WMS_RTRN_TO_NM_02_FROM = 81;

    /** Substring end position for WMS_RTRN_TO_NM_02 */
    int LG_CUT_WMS_RTRN_TO_NM_02_TO = 116;

    /** Substring start position for FIRST_LINE_ADDR */
    int LG_CUT_FIRST_LINE_ADDR_FROM = 116;

    /** Substring end position for FIRST_LINE_ADDR */
    int LG_CUT_FIRST_LINE_ADDR_TO = 151;

    /** Substring start position for SCD_LINE_ADDR */
    int LG_CUT_SCD_LINE_ADDR_FROM = 151;

    /** Substring end position for SCD_LINE_ADDR */
    int LG_CUT_SCD_LINE_ADDR_TO = 186;

    /** Substring start position for THIRD_LINE_ADDR */
    int LG_CUT_THIRD_LINE_ADDR_FROM = 186;

    /** Substring end position for THIRD_LINE_ADDR */
    int LG_CUT_THIRD_LINE_ADDR_TO = 221;

    /** Substring start position for FRTH_LINE_ADDR */
    int LG_CUT_FRTH_LINE_ADDR_FROM = 221;

    /** Substring end position for FRTH_LINE_ADDR */
    int LG_CUT_FRTH_LINE_ADDR_TO = 256;

    /** Substring start position for CTY_ADDR */
    int LG_CUT_CTY_ADDR_FROM = 256;

    /** Substring end position for CTY_ADDR */
    int LG_CUT_CTY_ADDR_TO = 276;

    /** Substring start position for ST_CD */
    int LG_CUT_ST_CD_FROM = 276;

    /** Substring end position for ST_CD */
    int LG_CUT_ST_CD_TO = 278;

    /** Substring start position for POST_CD */
    int LG_CUT_POST_CD_FROM = 278;

    /** Substring end position for POST_CD */
    int LG_CUT_POST_CD_TO = 293;

    /** Substring start position for CTRY_CD */
    int LG_CUT_CTRY_CD_FROM = 293;

    /** Substring end position for CTRY_CD */
    int LG_CUT_CTRY_CD_TO = 295;

    /** Substring start position for WMS_RTRN_TO_CTAC_NM */
    int LG_CUT_WMS_RTRN_TO_CTAC_NM_FROM = 295;

    /** Substring end position for WMS_RTRN_TO_CTAC_NM */
    int LG_CUT_WMS_RTRN_TO_CTAC_NM_TO = 320;

    /** Substring start position for RTRN_TO_CTAC_NUM */
    int LG_CUT_RTRN_TO_CTAC_NUM_FROM = 320;

    /** Substring end position for RTRN_TO_CTAC_NUM */
    int LG_CUT_RTRN_TO_CTAC_NUM_TO = 335;

    /** Stage Act Code 1:New */
    String STAGE_ACT_NEW = "1";

    /** Stage Act Code 2:Update */
    String STAGE_ACT_UPDATE = "2";

    /** Stage Rec Status Code 1:New */
    String STAGE_REC_STS_NEW_UPDATE = "2";

    /** WMS Interface Task Name: SO */
    String WMS_INTFC_TASK_NM_SO = "SO";

    /** Back Order Interface Type ID : 02 */
    String BACK_ORDER_INTFC_TP_ID = "02";

    /** Back Order Interface Record Type ID : 2 */
    String BACK_ORDER_INTFC_REC_TP_ID = "2";

    /** Value of ORD_LINE_TXT : 1000 */
    BigDecimal VAL_ORD_LINE_TXT_MUL = new BigDecimal(1000);

    /** DB Table: WMS_RWS_ORIG_LINE_SAVE */
    String TBL_WMS_RWS_ORIG_LINE_SAVE = "WMS_RWS_ORIG_LINE_SAVE";

    /** DB COLUMN :RWS_NUM */
    String COL_RWS_NUM  = "RWS_NUM";

    /** DB COLUMN :RWS_TO_WMS_DTL_LINE_NUM  */
    String COL_RWS_TO_WMS_DTL_LINE_NUM = "RWS_TO_WMS_DTL_LINE_NUM";

    /** DB COLUMN :SO_CUST_LINE_LOC_NM_01  */
    String COL_SO_CUST_LINE_LOC_NM_01 = "SO_CUST_LINE_LOC_NM_01";

    /** DB COLUMN :SO_CUST_LINE_LOC_NM_02  */
    String COL_SO_CUST_LINE_LOC_NM_02 = "SO_CUST_LINE_LOC_NM_02";

    /** DB COLUMN :SO_CUST_LINE_ADDR_01  */
    String COL_SO_CUST_LINE_ADDR_01 = "SO_CUST_LINE_ADDR_01";

    /** DB COLUMN :SO_CUST_LINE_ADDR_02  */
    String COL_SO_CUST_LINE_ADDR_02 = "SO_CUST_LINE_ADDR_02";

    /** DB COLUMN :SO_CUST_LINE_ADDR_03  */
    String COL_SO_CUST_LINE_ADDR_03 = "SO_CUST_LINE_ADDR_03";

    /** DB COLUMN :SO_CUST_LINE_ADDR_04  */
    String COL_SO_CUST_LINE_ADDR_04 = "SO_CUST_LINE_ADDR_04";

    /** DB COLUMN :CTY_ADDR  */
    String COL_CTY_ADDR = "CTY_ADDR";

    /** DB COLUMN :ST_CD  */
    String COL_ST_CD = "ST_CD";

    /** DB COLUMN :POST_CD  */
    String COL_POST_CD = "POST_CD";

    /** DB COLUMN :CTRY_CD  */
    String COL_CTRY_CD = "CTRY_CD";

    /** DB COLUMN :CTAC_PTNR_PSN_NM  */
    String COL_CTAC_PTNR_PSN_NM = "CTAC_PTNR_PSN_NM";

    /** DB COLUMN :CTAC_PTNR_PSN_TEL_NUM  */
    String COL_CTAC_PTNR_PSN_TEL_NUM = "CTAC_PTNR_PSN_TEL_NUM";

    /** Maximum length CUST_ISS_PO_NUM */
    int MAX_LENGTH_CUST_ISS_PO_NUM = 30;

    /** Maximum length OTBD_SRC_ORD_TP_TXT */
    int MAX_LENGTH_OTBD_SRC_ORD_TP_TXT = 30;

    /** Maximum length OTBD_SRC_ORD_TP_TXT */
    int MAX_LENGTH_SO_CUST_LINE_LOC_NM = 35;

    /** Maximum length OTBD_SRC_ORD_TP_TXT */
    int MAX_LENGTH_CTAC_PTNR_PSN_TEL_NUM = 15;

    /** WMS_ORD_TP :NLGB0010_CRAT_WTANDSHIPVIA_NOT */
    String NLGB0010_CRAT_WTANDSHIPVIA_NOT = "NLGB0010_CRAT_WTANDSHIPVIA_NOT";

    /** WMS_ORD_TP :NLGB0010_CREATE_RTRN_TO_NOT */
    String NLGB0010_CREATE_RTRN_TO_NOT = "NLGB0010_CREATE_RTRN_TO_NOT";

    /** WMS_ORD_TP :NLGB0010_REGIST_SO_PO */
    String NLGB0010_REGIST_SO_PO = "NLGB0010_REGIST_SO_PO";

    /** WMS_ORD_TP :NLGB0010_REGIST_PO */
    String NLGB0010_REGIST_PO = "NLGB0010_REGIST_PO";

    /** WMS_ORD_TP :NLGB0010_CASEANDPALLET_NOT */
    String NLGB0010_CASEANDPALLET_NOT = "NLGB0010_CASEANDPALLET_NOT";

    /** WMS_ORD_TP :NLGB0010_SET_NLGI1100_OUT_KIT */
    String NLGB0010_SET_NLGI1100_OUT_KIT = "NLGB0010_SET_NLGI1100_OUT_KIT";

    /** WMS_ORD_TP :NLGB0010_SET_NLGI1100_OUT_IC */
    String NLGB0010_SET_NLGI1100_OUT_IC = "NLGB0010_SET_NLGI1100_OUT_IC";

    /** WMS_ORD_TP :NLGB0010_SET_NLGI1100_IN_IC */
    String NLGB0010_SET_NLGI1100_IN_IC = "NLGB0010_SET_NLGI1100_IN_IC";

    /** WMS_ORD_TP :NLGB0010_SET_NLGI1100_OUT_EXP */
    String NLGB0010_SET_NLGI1100_OUT_EXP = "NLGB0010_SET_NLGI1100_OUT_EXP";

    /** WMS_ORD_TP :NLGB0010_SET_NLGI1100_IN_ICNOT */
    String NLGB0010_SET_NLGI1100_IN_ICNOT = "NLGB0010_SET_NLGI1100_IN_ICNOT";

    /** WMS_ORD_TP :NLGB0010_CREATE_NLGI210005_NOT */
    String NLGB0010_CREATE_NLGI210005_NOT = "NLGB0010_CREATE_NLGI210005_NOT";

    /** WMS_ORD_TP :NLGB0010_CRAT_SET_ORDCMNTTXT */
    String NLGB0010_CRAT_SET_ORDCMNTTXT = "NLGB0010_CRAT_SET_ORDCMNTTXT";

    /** WMS_ORD_TP :NLGB0010_IS_CREATE_POIF04 */
    String NLGB0010_IS_CREATE_POIF04 = "NLGB0010_IS_CREATE_POIF04";

    // START 2018/05/11 S.Katsuma [QC#24714,ADD]
    /** WMS_ORD_TP :NLGB0010_SET_NLGI1100_OUT_RMN */
    String NLGB0010_SET_NLGI1100_OUT_RMN = "NLGB0010_SET_NLGI1100_OUT_RMN";
    // END 2018/05/11 S.Katsuma [QC#24714,ADD]

    /** DB Column: TO_STK_STS_CD */
    String COL_TO_STK_STS_CD = "TO_STK_STS_CD";

    /** DB Column: FRT_CHRG_TO_CD */
    String COL_FRT_CHRG_TO_CD = "FRT_CHRG_TO_CD";

    /** DB Column: FRT_CHRG_METH_CD */
    String COL_FRT_CHRG_METH_CD = "FRT_CHRG_METH_CD";

    /** DB Column: S80_STK_STS_CD */
    String COL_S80_STK_STS_CD_FROM = "S80_STK_STS_CD_FROM";

    /** DB Column: S80_STK_STS_CD */
    String COL_S80_STK_STS_CD_TO = "S80_STK_STS_CD_TO";

    /** DB Table: SHIP_VIA_CONV */
    String TBL_SHIP_VIA_CONV = "SHIP_VIA_CONV";

    /** DB Table: SHPG_TERM_CONV */
    String TBL_SHPG_TERM_CONV = "SHPG_TERM_CONV";

    /** DB Table: STS_STK_CONV */
    String TBL_FROM_STS_STK_CONV = "STS_STK_CONV";

    /** DB Table: STS_STK_CONV */
    String TBL_TO_STS_STK_CONV = "STS_STK_CONV";

    /** CONV_SCE_ORD_TP_CD */
    String CONV_SCE_ORD_TP_CD = "CONV_SCE_ORD_TP_CD";

    /** CONV_TRX_SRC_TP_CD */
    String CONV_TRX_SRC_TP_CD = "CONV_TRX_SRC_TP_CD";

    /** CONV_SHPG_SVC_LVL_CD */
    String CONV_SHPG_SVC_LVL_CD = "CONV_SHPG_SVC_LVL_CD";
    
    /** CONV_FRT_CHRG_TO_CD */
    String CONV_FRT_CHRG_TO_CD = "CONV_FRT_CHRG_TO_CD";

    /** CONV_FRT_CHRG_METH_CD */
    String CONV_FRT_CHRG_METH_CD = "CONV_FRT_CHRG_METH_CD";
    
    /** CONV_FROM_STK_STS_CD */
    String CONV_FROM_STK_STS_CD = "CONV_FROM_STK_STS_CD";
    
    /** CONV_TO_STK_STS_CD */
    String CONV_TO_STK_STS_CD = "CONV_TO_STK_STS_CD";

    // START 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
    /** DS_CPO_CONFIG_PK */
    String DS_CPO_CONFIG_PK = "DS_CPO_CONFIG_PK";

    /** SVC_LB_GRP_REQ_FLG */
    String SVC_LB_GRP_REQ_FLG = "SVC_LB_GRP_REQ_FLG";

    /** SVC_LB_GRP_CD */
    String SVC_LB_GRP_CD = "SVC_LB_GRP_CD";

    /** T_MDL_NM */
    String T_MDL_NM = "T_MDL_NM";
    // END 2017/11/02 S.Katsuma [SOL#170(QC#18624), ADD]
}
