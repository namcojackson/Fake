/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB120001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Shipping Order to AGW
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/24/2023   Hitachi         M.Nakajima      Create          N/A
 *</pre>
 */
public class NLBB120001Constant {

    /** 
     * BUSINESS_ID : NSBB1200
     */
    public static final String BUSINESS_ID = "NSBB1200";

    /**
     * TABLE_NAME : NLBI1510_01 for SO/RWS Header
     */
    public static final String NLBI1510_01 = "NLBI1510_01";

    /**
     * TABLE_NAME : NLBI1510_02 for SO/RWS Detail
     */
    public static final String NLBI1510_02 = "NLBI1510_02";

    /**
     * TABLE_NAME : NLBI1510_03 for SO/RWS Text
     */
    public static final String NLBI1510_03 = "NLBI1510_03";

    /**
     * TABLE_NAME : NLBI1510_04 for SO/RWS Site Survey
     */
    public static final String NLBI1510_04 = "NLBI1510_04";

    /**
     * TABLE_NAME : NLBI1510_05 for SO/RWS Serial
     */
    public static final String NLBI1510_05 = "NLBI1510_05";

    /**
     * TABLE_NAME : SHPG_ORD
     */
    public static final String SHPG_ORD = "SHPG_ORD";

    /**
     * TABLE_NAME : SHPG_ORD_MSG
     */
    public static final String SHPG_ORD_MSG = "SHPG_ORD_MSG";

    /**
     * TABLE_NAME : SHPG_ORD_DTL
     */
    public static final String SHPG_ORD_DTL = "SHPG_ORD_DTL";

    /**
     * TABLE_NAME : MDSE
     */
    public static final String MDSE = "MDSE";

    /**
     * TABLE_NAME : SO_SER
     */
    public static final String SO_SER = "SO_SER";

    /**
     * TABLE_NAME : SHPG_ORD_CUST_DTL
     */
    public static final String SHPG_ORD_CUST_DTL = "SHPG_ORD_CUST_DTL";

    /**
     * TABLE_NAME : WMS_INBD_SO_HDR
     */
    public static final String WMS_INBD_SO_HDR = "WMS_INBD_SO_HDR";

    /**
     * TABLE_NAME : WMS_INBD_SO_DTL
     */
    public static final String WMS_INBD_SO_DTL = "WMS_INBD_SO_DTL";

    /**
     * TABLE_NAME : WMS_INBD_SO_TEXT
     */
    public static final String WMS_INBD_SO_TEXT = "WMS_INBD_SO_TEXT";

    /**
     * TABLE_NAME : INBD_PO_HDR
     */
    public static final String WMS_INBD_PO_HDR = "INBD_PO_HDR";

    /**
     * TABLE_NAME : RWS_HDR
     */
    public static final String RWS_HDR = "RWS_HDR";


    /**
     * DB COLUMN : GLBL_CMPY_CD
     */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /**
     * DB COLUMN : INTERFACE_ID
     */
    public static final String COL_INTERFACE_ID = "INTERFACE_ID";

    /**
     * DB COLUMN : TRANSACTION_ID
     */
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /**
     * DB COLUMN : UNIT_ID
     */
    public static final String COL_UNIT_ID = "UNIT_ID";

    /**
     * DB COLUMN : WH_OWNR_CD
     */
    public static final String COL_WH_OWNR_CD = "WH_OWNR_CD";

    /**
     * DB COLUMN : CARR_TP_NM
     */
    public static final String COL_CARR_TP_NM = "CARR_TP_NM";

    /**
     * DB COLUMN : SO_NUM
     */
    public static final String COL_SO_NUM = "SO_NUM";

    /**
     * DB COLUMN : SO_CUST_DATA_TP_CD
     */
    public static final String COL_SO_CUST_DATA_TP_CD = "SO_CUST_DATA_TP_CD";

    /**
     * DB COLUMN : CARR_CD
     */
    public static final String COL_CARR_CD = "CARR_CD";

    /**
     * DB COLUMN : SHPG_SVC_LVL_CD
     */
    public static final String COL_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /**
     * DB COLUMN : TPL_CARR_CD
     */
    public static final String COL_TPL_CARR_CD = "TPL_CARR_CD";

    /**
     * DB COLUMN : TPL_SVC_LVL_CD
     */
    public static final String COL_TPL_SVC_LVL_CD = "TPL_SVC_LVL_CD";

    /**
     * DB COLUMN : TPL_CTRL_ID
     */
    public static final String COL_TPL_CTRL_ID = "TPL_CTRL_ID";

    /**
     * DB COLUMN : RTL_WH_CD
     */
    public static final String COL_RTL_WH_CD = "RTL_WH_CD";

    /**
     * DB COLUMN : REQ_DT_TM_TS_TXT
     */
    public static final String COL_REQ_DT_TM_TS_TXT = "REQ_DT_TM_TS_TXT";

    /**
     * DB COLUMN : SCHD_DELY_TM
     */
    public static final String COL_SCHD_DELY_TM = "SCHD_DELY_TM";

    /**
     * DB COLUMN : CTRY_CD
     */
    public static final String COL_CTRY_CD = "CTRY_CD";

    /**
     * DB COLUMN : POST_CD
     */
    public static final String COL_POST_CD = "POST_CD";

    /**
     * DB COLUMN : PSD_DT
     */
    public static final String COL_PSD_DT = "PSD_DT";

    /**
     * DB COLUMN : SHIP_TO_CUST_CD
     */
    public static final String COL_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /**
     * DB COLUMN : SELL_TO_CUST_CD
     */
    public static final String COL_SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /**
     * DB COLUMN : BILL_TO_CUST_CD
     */
    public static final String COL_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /**
     * DB COLUMN : SO_CUST_LINE_LOC_NM_01
     */
    public static final String COL_SO_CUST_LINE_LOC_NM_01 = "SO_CUST_LINE_LOC_NM_01";

    /**
     * DB COLUMN : SO_CUST_LINE_LOC_NM_02
     */
    public static final String COL_SO_CUST_LINE_LOC_NM_02 = "SO_CUST_LINE_LOC_NM_02";

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
     * DB COLUMN : CTY_ADDR
     */
    public static final String COL_CTY_ADDR = "CTY_ADDR";

    /**
     * DB COLUMN : ST_CD
     */
    public static final String COL_ST_CD = "ST_CD";

    /**
     * DB COLUMN : CTAC_PTNR_PSN_TEL_NUM
     */
    public static final String COL_CTAC_PTNR_PSN_TEL_NUM = "CTAC_PTNR_PSN_TEL_NUM";

    /**
     * DB COLUMN : CTAC_PTNR_PSN_NM
     */
    public static final String COL_CTAC_PTNR_PSN_NM = "CTAC_PTNR_PSN_NM";

    /**
     * DB COLUMN : CTAC_PSN_TEL_NUM
     */
    public static final String COL_CTAC_PSN_TEL_NUM = "CTAC_PSN_TEL_NUM";

    /**
     * DB COLUMN : RTRN_TO_CTAC_NUM
     */
    public static final String COL_RTRN_TO_CTAC_NUM = "RTRN_TO_CTAC_NUM";

    /**
     * DB COLUMN : CTAC_PSN_NM
     */
    public static final String COL_CTAC_PSN_NM = "CTAC_PSN_NM";

    /**
     * DB COLUMN : RTRN_LB_CD
     */
    public static final String COL_RTRN_LB_CD = "RTRN_LB_CD";

    /**
     * DB COLUMN : SHIP_TO_RTL_WH_CD
     */
    public static final String COL_SHIP_TO_RTL_WH_CD = "SHIP_TO_RTL_WH_CD";

    /**
     * DB COLUMN : SHIP_TO_RTL_SWH_CD
     */
    public static final String COL_SHIP_TO_RTL_SWH_CD = "SHIP_TO_RTL_SWH_CD";

    /**
     * DB COLUMN : WH_CD
     */
    public static final String COL_WH_CD = "WH_CD";

    /**
     * DB COLUMN : ORD_TP_TXT
     */
    public static final String COL_ORD_TP_TXT = "ORD_TP_TXT";

    /**
     * DB COLUMN : S80_CMPY_CD
     */
    public static final String COL_S80_CMPY_CD = "S80_CMPY_CD";

    /**
     * DB COLUMN : S80_ORD_TP_CD
     */
    public static final String COL_S80_ORD_TP_CD = "S80_ORD_TP_CD";

    /**
     * DB COLUMN : S80_TRX_CD
     */
    public static final String COL_S80_TRX_CD = "S80_TRX_CD";

    /**
     * DB COLUMN : S80_ORD_SRC_CD
     */
    public static final String COL_S80_ORD_SRC_CD = "S80_ORD_SRC_CD";

    /**
     * DB COLUMN : S80_SHPG_TERM_CD
     */
    public static final String COL_S80_SHPG_TERM_CD = "S80_SHPG_TERM_CD";

    /**
     * DB COLUMN : S80_SHPG_TERM_NM
     */
    public static final String COL_S80_SHPG_TERM_NM = "S80_SHPG_TERM_NM";

    /**
     * DB COLUMN : S80_STK_STS_CD
     */
    public static final String COL_S80_STK_STS_CD = "S80_STK_STS_CD";

    /**
     * DB COLUMN : TRX_HDR_NUM
     */
    public static final String COL_TRX_HDR_NUM = "TRX_HDR_NUM";

    /**
     * DB COLUMN : PICK_TKT_NUM
     */
    public static final String COL_PICK_TKT_NUM = "PICK_TKT_NUM";

    /**
     * DB COLUMN : CUST_ISS_PO_NUM
     */
    public static final String COL_CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /**
     * DB COLUMN : EXPT_SHPG_METH_CD
     */
    public static final String COL_EXPT_SHPG_METH_CD = "EXPT_SHPG_METH_CD";

    /**
     * DB COLUMN : SHPG_METH_NM
     */
    public static final String COL_SHPG_METH_NM = "SHPG_METH_NM";

    /**
     * DB COLUMN : SO_CRAT_TS
     */
    public static final String COL_SO_CRAT_TS = "SO_CRAT_TS";

    /**
     * DB COLUMN : PSD_DT
     */
    public static final String COL_PSD_DT_TM = "PSD_DT_TM";

    /**
     * DB COLUMN : RDD_DT_TM
     */
    public static final String COL_RDD_DT_TM = "RDD_DT_TM";

    /**
     * DB COLUMN : DNLD_TM_TS
     * */
    public static final String COL_DNLD_TM_TS = "DNLD_TM_TS";

    /**
     * DB COLUMN : DROP_SHIP_FLG
     */
    public static final String COL_DROP_SHIP_FLG = "DROP_SHIP_FLG";

    /**
     * DB COLUMN : PRINT_SCC_LB_FLG
     */
    public static final String COL_PRINT_SCC_LB_FLG = "PRINT_SCC_LB_FLG";

    /**
     * DB COLUMN : PRINT_UCC_LB_FLG
     */
    public static final String COL_PRINT_UCC_LB_FLG = "PRINT_UCC_LB_FLG";

    /**
     * DB COLUMN : MIX_PLT_ALLW_FLG
     */
    public static final String COL_MIX_PLT_ALLW_FLG = "MIX_PLT_ALLW_FLG";

    /**
     * DB COLUMN : PRINT_PLT_UCC_LB_FLG
     */
    public static final String COL_PRINT_PLT_UCC_LB_FLG = "PRINT_PLT_UCC_LB_FLG";

    /**
     * DB COLUMN : PRINT_NON_ASN_UCC_LB_FLG
     */
    public static final String COL_PRINT_NON_ASN_UCC_LB_FLG = "PRINT_NON_ASN_UCC_LB_FLG";

    /**
     * DB COLUMN : ASN_REQ_FLG
     */
    public static final String COL_ASN_REQ_FLG = "ASN_REQ_FLG";

    /**
     * DB COLUMN : UCC_NUM_CD
     */
    public static final String COL_UCC_NUM_CD = "UCC_NUM_CD";

    /**
     * DB COLUMN : EDI_TP_CD
     */
    public static final String COL_EDI_TP_CD = "EDI_TP_CD";

    /**
     * DB COLUMN : CUST_STORE_NUM
     */
    public static final String COL_CUST_STORE_NUM = "CUST_STORE_NUM";

    /**
     * DB COLUMN : SO_DEPT_NUM
     */
    public static final String COL_SO_DEPT_NUM = "SO_DEPT_NUM";

    /**
     * DB COLUMN : TOT_SHIP_AMT
     */
    public static final String COL_TOT_SHIP_AMT = "TOT_SHIP_AMT";

    /**
     * DB COLUMN : SO_CONFIG_FLG
     */
    public static final String COL_SO_CONFIG_FLG = "SO_CONFIG_FLG";

    /**
     * DB COLUMN : CARR_ACCT_NUM
     */
    public static final String COL_CARR_ACCT_NUM = "CARR_ACCT_NUM";

    /**
     * DB COLUMN : SCHD_DELY_DT
     */
    public static final String COL_SCHD_DELY_DT = "SCHD_DELY_DT";

    /**
     * DB COLUMN : SVC_CONFIG_MSTR_PK
     */
    public static final String COL_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /**
     * DB COLUMN : PRE_ISTL_SHOP_RQST_FLG
     */
    public static final String COL_PRE_ISTL_SHOP_RQST_FLG = "PRE_ISTL_SHOP_RQST_FLG";

    /**
     * DB COLUMN : WMS_RTE_PATH_CD
     */
    public static final String COL_WMS_RTE_PATH_CD = "WMS_RTE_PATH_CD";

    /**
     * DB COLUMN : SRC_ORD_NUM
     */
    public static final String COL_SRC_ORD_NUM = "SRC_ORD_NUM";

    /**
     * DB COLUMN : SR_PSN_NM
     */
    public static final String COL_SR_PSN_NM = "SR_PSN_NM";

    /**
     * DB COLUMN : SCE_ORD_TP_CD
     */
    public static final String COL_SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /**
     * DB COLUMN : OTBD_SRC_ORD_TP_TXT
     */
    public static final String COL_OTBD_SRC_ORD_TP_TXT = "OTBD_SRC_ORD_TP_TXT";

    /**
     * DB COLUMN : CPO_PSN_NM
     */
    public static final String COL_CPO_PSN_NM = "CPO_PSN_NM";

    /**
     * DB COLUMN : SCE_ORD_TP_NM
     */
    public static final String COL_SCE_ORD_TP_NM = "SCE_ORD_TP_NM";

    /**
     * DB COLUMN : LOC_GRP_CD
     */
    public static final String COL_LOC_GRP_CD = "LOC_GRP_CD";

    /**
     * DB COLUMN : PO_PSN_NM
     */
    public static final String COL_PO_PSN_NM = "PO_PSN_NM";

    /**
     * DB COLUMN : SO_MSG_TP_CD
     */
    public static final String COL_SO_MSG_TP_CD = "SO_MSG_TP_CD";

    /**
     * DB COLUMN : SO_MSG_DESC_TXT
     */
    public static final String COL_SO_MSG_DESC_TXT = "SO_MSG_DESC_TXT";

    /**
     * DB COLUMN : MDSE_CD
     */
    public static final String COL_MDSE_CD = "MDSE_CD";

    /**
     * DB COLUMN : SHPG_QTY
     */
    public static final String COL_SHPG_QTY = "SHPG_QTY";

    /**
     * DB COLUMN : SO_SLP_NUM
     */
    public static final String COL_SO_SLP_NUM = "SO_SLP_NUM";

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
     * DB COLUMN : TPL_LOC_NM
     */
    public static final String COL_TPL_LOC_NM = "TPL_LOC_NM";

    /**
     * DB COLUMN : PICK_SVC_CONFIG_MSTR_PK
     */
    public static final String COL_PICK_SVC_CONFIG_MSTR_PK = "PICK_SVC_CONFIG_MSTR_PK";

    /**
     * DB COLUMN : ITRL_ITEM_FLG
     */
    public static final String COL_ITRL_ITEM_FLG = "ITRL_ITEM_FLG";

    /**
     * DB COLUMN : RTL_SWH_CD
     */
    public static final String COL_RTL_SWH_CD = "RTL_SWH_CD";

    /**
     * DB COLUMN : CUST_MDSE_CD
     */
    public static final String COL_CUST_MDSE_CD = "CUST_MDSE_CD";

    /**
     * DB COLUMN : RQST_ORD_QTY
     */
    public static final String COL_RQST_ORD_QTY = "RQST_ORD_QTY";

    /**
     * DB COLUMN : SHPG_BAL_QTY
     */
    public static final String COL_SHPG_BAL_QTY = "SHPG_BAL_QTY";

    /**
     * DB COLUMN : UNIT_PRC_AMT
     */
    public static final String COL_UNIT_PRC_AMT = "UNIT_PRC_AMT";

    /**
     * DB COLUMN : DISC_PRC_AMT
     */
    public static final String COL_DISC_PRC_AMT = "DISC_PRC_AMT";

    /**
     * DB COLUMN : DISC_UNIT_PRC_AMT
     */
    public static final String COL_DISC_UNIT_PRC_AMT = "DISC_UNIT_PRC_AMT";

    /**
     * DB COLUMN : SER_NUM_TAKE_FLG
     */
    public static final String COL_SER_NUM_TAKE_FLG = "SER_NUM_TAKE_FLG";

    /**
     * DB COLUMN : SET_MDSE_CD
     */
    public static final String COL_SET_MDSE_CD = "SET_MDSE_CD";

    /**
     * DB COLUMN : SET_MDSE_NM
     */
    public static final String COL_SET_MDSE_NM = "SET_MDSE_NM";

    /**
     * DB COLUMN : SET_SHPG_QTY
     */
    public static final String COL_SET_SHPG_QTY = "SET_SHPG_QTY";

    /**
     * DB COLUMN : IN_EACH_QTY
     */
    public static final String COL_IN_EACH_QTY = "IN_EACH_QTY";

    /**
     * DB COLUMN : TOT_SHPG_WT
     */
    public static final String COL_TOT_SHPG_WT = "TOT_SHPG_WT";

    /**
     * DB COLUMN : TOT_SHPG_VOL
     */
    public static final String COL_TOT_SHPG_VOL = "TOT_SHPG_VOL";

    /**
     * DB COLUMN : BAT_NUM_TAKE_FLG
     */
    public static final String COL_BAT_NUM_TAKE_FLG = "BAT_NUM_TAKE_FLG";

    /**
     * DB COLUMN : SET_CONFIG_ITEM_FLGMDSE_NM
     */
    public static final String COL_CONFIG_ITEM_FLG = "CONFIG_ITEM_FLG";

    /**
     * DB COLUMN : BACK_ORD_IMPCT_TP_CD
     */
    public static final String COL_BACK_ORD_IMPCT_TP_CD = "BACK_ORD_IMPCT_TP_CD";

    /**
     * DB COLUMN : RMV_CONFIG_FLG
     */
    public static final String COL_RMV_CONFIG_FLG = "RMV_CONFIG_FLG";

    /**
     * DB COLUMN : RTRN_REQ_PRT_FLG
     */
    public static final String COL_RTRN_REQ_PRT_FLG = "RTRN_REQ_PRT_FLG";

    /**
     * DB COLUMN : MDSE_ITEM_TP_CD
     */
    public static final String COL_MDSE_ITEM_TP_CD = "MDSE_ITEM_TP_CD";

    /**
     * DB COLUMN : SER_NUM
     */
    public static final String COL_SER_NUM = "SER_NUM";

    /**
     * DB COLUMN : RWS_NUM
     */
    public static final String COL_RWS_NUM = "RWS_NUM";

    /**
     * DB COLUMN : FROM_LOC_CD
     */
    public static final String COL_FROM_LOC_CD = "FROM_LOC_CD";

    /**
     * DB COLUMN : WH_IN_ETA_DT
     */
    public static final String COL_WH_IN_ETA_DT = "WH_IN_ETA_DT";

    /**
     * DB COLUMN : WH_IN_ETA_TM
     */
    public static final String COL_WH_IN_ETA_TM = "WH_IN_ETA_TM";

    /**
     * DB COLUMN : FROM_LOC_TEL_NUM
     */
    public static final String COL_FROM_LOC_TEL_NUM = "FROM_LOC_TEL_NUM";

    /**
     * DB COLUMN : FROM_LOC_PSN_NM
     */
    public static final String COL_FROM_LOC_PSN_NM = "FROM_LOC_PSN_NM";

    /**
     * DB COLUMN : FROM_LOC_NM_01
     */
    public static final String COL_FROM_LOC_NM_01 = "FROM_LOC_NM_01";

    /**
     * DB COLUMN : FROM_LOC_NM_02
     */
    public static final String COL_FROM_LOC_NM_02 = "FROM_LOC_NM_02";

    /**
     * DB COLUMN : FROM_LOC_ADDR_01
     */
    public static final String COL_FROM_LOC_ADDR_01 = "FROM_LOC_ADDR_01";

    /**
     * DB COLUMN : FROM_LOC_ADDR_02
     */
    public static final String COL_FROM_LOC_ADDR_02 = "FROM_LOC_ADDR_02";

    /**
     * DB COLUMN : FROM_LOC_ADDR_03
     */
    public static final String COL_FROM_LOC_ADDR_03 = "FROM_LOC_ADDR_03";

    /**
     * DB COLUMN : FROM_LOC_ADDR_04
     */
    public static final String COL_FROM_LOC_ADDR_04 = "FROM_LOC_ADDR_04";

    /**
     * DB COLUMN : FROM_LOC_POST_CD
     */
    public static final String COL_FROM_LOC_POST_CD = "FROM_LOC_POST_CD";

    /**
     * DB COLUMN : FROM_LOC_CTY_ADDR
     */
    public static final String COL_FROM_LOC_CTY_ADDR = "FROM_LOC_CTY_ADDR";

    /**
     * DB COLUMN : FROM_LOC_CTRY_CD
     */
    public static final String COL_FROM_LOC_CTRY_CD = "FROM_LOC_CTRY_CD";

    /**
     * DB COLUMN : FROM_LOC_ST_CD
     */
    public static final String COL_FROM_LOC_ST_CD = "FROM_LOC_ST_CD";

    /**
     * DB COLUMN : RWS_MSG_TXT
     */
    public static final String COL_RWS_MSG_TXT = "RWS_MSG_TXT";

    /**
     * DB COLUMN : RWS_REF_NUM
     */
    public static final String COL_RWS_REF_NUM = "RWS_REF_NUM";

    /**
     * DB COLUMN : IMPT_INV_VESL_NM
     */
    public static final String COL_IMPT_INV_VESL_NM = "IMPT_INV_VESL_NM";

    /**
     * DB COLUMN : IMPT_INV_BOL_NUM
     */
    public static final String COL_IMPT_INV_BOL_NUM = "IMPT_INV_BOL_NUM";

    /**
     * DB COLUMN : RWS_QTY
     */
    public static final String COL_RWS_QTY = "RWS_QTY";

    /**
     * DB COLUMN : RWS_LINE_NUM
     */
    public static final String COL_RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";

    /**
     * DB COLUMN : TRX_ORD_NUM
     */
    public static final String COL_TRX_ORD_NUM = "TRX_ORD_NUM";

    /**
     * DB COLUMN : IMPT_INV_NUM
     */
    public static final String COL_IMPT_INV_NUM = "IMPT_INV_NUM";

    /**
     * DB COLUMN : IMPT_INV_DO_NUM
     */
    public static final String COL_IMPT_INV_DO_NUM = "IMPT_INV_DO_NUM";

    /**
     * DB COLUMN : OUT_PACK_FROM_CSE_NUM
     */
    public static final String COL_OUT_PACK_FROM_CSE_NUM = "OUT_PACK_FROM_CSE_NUM";

    /**
     * DB COLUMN : OUT_PACK_TO_CSE_NUM
     */
    public static final String COL_OUT_PACK_TO_CSE_NUM = "OUT_PACK_TO_CSE_NUM";

    /**
     * DB COLUMN : THIRD_PTY_DSP_TP_CD
     */
    public static final String COL_THIRD_PTY_DSP_TP_CD = "THIRD_PTY_DSP_TP_CD";

    /**
     * DB COLUMN : SER_APVL_REQ_FLG
     */
    public static final String COL_SER_APVL_REQ_FLG = "SER_APVL_REQ_FLG";

    /**
     * DB COLUMN : RWS_LINE_NUM
     */
    public static final String COL_RWS_LINE_NUM = "RWS_LINE_NUM";

    /**
     * VAR_CHAR_CONST : NLBB1200_TARGET_ORDER
     */
    public static final String NLBB1200_TARGET_ORDER = "NLBB1200_TARGET_ORDER";

    /**
     * VAR_CHAR_CONST : NLBB1200_TARGET_CARRIER
     */
    public static final String NLBB1200_TARGET_CARRIER = "NLBB1200_TARGET_CARRIER";

    /**
     * VAR_CHAR_CONST : AGW_TARGET_SCE_ORD_TP_CD
     */
    public static final String AGW_TARGET_SCE_ORD_TP_CD = "AGW_TARGET_SCE_ORD_TP_CD";

    /**
     *  time-stamp length
     */
    public static final int LENGTH_TS = 14;

    /**
     *  time-stamp length
     */
    public static final int LENGTH_TS_8 = 8;

    /** 
     * Date Format : yyyyMMddHHmmss
     */
    public static final String FMT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * Date Format : yyyyMMddHHmmssSSS
     */
    public static final String FMT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /**
     * Date Format : yyyyMMddHHmm
     */
    public static final String FMT_YYYYMMDDHHMM = "yyyyMMddHHmm";

    /**
     * Date Format : yyyyMMdd
     */
    public static final String FMT_YYYYMMDD = "yyyyMMdd";

    /**
     * Value 999999
     */
    public static final String VAL_999999 = "999999";

    /**
     * Value 1
     */
    public static final String VAL_1 = "1";

    /**
     * Value 2
     */
    public static final String VAL_2 = "2";

    /**
     * Value 000000
     */
    public static final String VAL_000000 = "000000";

    /**
     * Value 01
     * */
    public static final String VAL_01 = "01";

    /**
     * Value 05
     * */
    public static final String VAL_05 = "05";

    /**
     * Value 06
     * */
    public static final String VAL_06 = "06";

    /**
     * Value 07
     * */
    public static final String VAL_07 = "07";

    /**
     * Value 08
     * */
    public static final String VAL_08 = "08";

    /**
     * Value 09
     * */
    public static final String VAL_09 = "09";

    /**
     * Value MAX_QTY
     */
    public static final BigDecimal VAL_MAX_QTY = new BigDecimal("9999999999999.99");

    /**
     * Max length TOT_SHIP_PRC_AMT_NUM
     */
    public static final BigDecimal MAX_VAL_TOT_SHIP_PRC_AMT_NUM = new BigDecimal("9999999999999.99");

    /**
     * Max length CUST_ISS_PO_NUM
     */
    public static final int MAX_LENGTH_CUST_ISS_PO_NUM = 30;

    /**
     * Max length OTBD_SRC_ORD_TP_TXT
     */
    public static final int MAX_LENGTH_OTBD_SRC_ORD_TP_TXT = 30;

    /**
     * Max length CTAC_PSN_NM
     */
    public static final int MAX_LENGTH_CTAC_PSN_NM = 25;

    /**
     * length CUST_DC_NUM
     */
    public static final int LENGTH_CUST_DC_NUM = 8;

    /**
     * Max length TXT
     * */
    public static final int MAX_LENGTH_TXT = 400;

    /**
     * Value of PSN_NM size : 20
     */
    public static final int VAL_PSN_NM_SIZE = 20;

    /**
     * Max length SO_CUST_LINE_LOC_NM
     */
    public static final int MAX_LENGTH_SO_CUST_LINE_LOC_NM = 35;

    /**
     * Max length CTAC_PTNR_PSN_TEL_NUM
     */
    public static final int MAX_LENGTH_CTAC_PTNR_PSN_TEL_NUM = 15;

    /**
     * Max length LENGTH_FROM_LOC_NM
     */
    public static final int LENGTH_FROM_LOC_NM = 35;

    /**
     * size SIZE_3
     */
    public static final int SIZE_3 = 3;

    /**
     * Value of MIN_DATE : 19000101
     */
    public static final String VAL_MIN_DATE = "19000101";

    /**
     * Value of MAX_DATE : 99991231
     */
    public static final String VAL_MAX_DATE = "99991231";

    /**
     * INTFC_TP_ID : 01
     */
    public static final String VAL_INTFC_TP_ID_01 = "01";

    /**
     * INTFC_TP_ID : 02
     */
    public static final String VAL_INTFC_TP_ID_02 = "02";

    /**
     * INTFC_REC_TP_ID : 1
     */
    public static final String VAL_INTFC_REC_TP_ID_HDR = "1";

    /**
     * INTFC_REC_TP_ID : 2
     */
    public static final String VAL_INTFC_REC_TP_ID_DTL = "2";

    /**
     * INTFC_REC_TP_ID : 3
     */
    public static final String VAL_INTFC_REC_TP_ID_SHIP = "3";

    /**
     * NTFC_REC_TP_ID : 3
     * */
    public static final String VAL_INTFC_REC_TP_ID_VND = "3";

    /**
     * INTFC_REC_TP_ID : 4
     */
    public static final String VAL_INTFC_REC_TP_ID_TXT = "4";

    /**
     * INTFC_REC_TP_ID : 5
     */
    public static final String VAL_INTFC_REC_TP_ID_CHRG = "5";

    /**
     * INTFC_REC_TP_ID : 6
     */
    public static final String VAL_INTFC_REC_TP_ID_BILL = "6";

    /**
     * WMS_PRTY_CD : 5
     * */
    public static final String VAL_WMS_PRTY_CD = "5";

    /**
     * WMS_SO_STS_CD : R
     * */
    public static final String VAL_WMS_SO_STS_CD = "R";

    /**
     * WMS_INV_IND : S
     */
    public static final String VAL_WMS_INV_IND_S = "S";

    /**
     * PRINT_TP_CD_B : B
     * */
    public static final String VAL_PRINT_TP_CD_B = "B";

    /**
     * PRINT_SWTH_CD : P
     * */
    public static final String VAL_PRINT_SWTH_CD_P = "P";

    /**
     * CHANGE : CHA
     */
    public static final String VAL_CHANGE = "CHA";

    /**
     * NEW_ORDER : ORI
     */
    public static final String VAL_NEW_ORDER = "ORI";

    /**
     * ROSS Return : RB
     */
    public static final String VAL_ROSS_RETURN = "RB";

    /**
     * ORD_TP_TXT : LR
     */
    public static final String VAL_ORD_TP_TXT_LR = "LR";

    /**
     * ORD_TP_TXT : LF
     */
    public static final String VAL_ORD_TP_TXT_LF = "LF";

    // START 2023/07/12 [QC#61591, ADD]
    /** ORD_TP_TXT : DP */
    public static final String VAL_ORD_TP_TXT_DP = "DP";
    // END 2023/07/12 [QC#61591, ADD]

    /**
     * CARR_CD : AI41
     */
    public static final String VAL_CONST_CARR_CD = "AI41";

    /**
     * TPL_FROM_PTNR_ID : CANON
     */
    public static final String VAL_CONST_TPL_FROM_PTNR_ID = "OCE";

    /**
     * TPL_TO_PTNR_ID : AGW
     */
    public static final String VAL_CONST_TPL_TO_PTNR_ID = "AGW";

    /**
     * TPL_ORG_TXT : 0110
     */
    public static final String VAL_CONST_TPL_ORG_TXT = "0110";

    /**
     * TPL_ITEM_TP_CD : ZSUB
     */
    public static final String VAL_CONST_TPL_ITEM_TP_CD = "ZSUB";

    /**
     * TPL_PTNR_TXT : WE
     */
    public static final String VAL_CONST_TPL_PTNR_TXT = "WE";

    /**
     * SO_MSG_TP_CD : S
     * */
    public static final String VAL_CONST_SO_MSG_TP_CD = "S";

    /**
     * STAGE_REC_STS_NEW_UPDATE : 2
     */
    public static final String STAGE_REC_STS_NEW_UPDATE = "2";

    /**
     * STAGE_ACT_NEW : 1
     */
    public static final String STAGE_ACT_NEW = "1";

    /**
     * STAGE_ACT_UPDATE : 2
     */
    public static final String STAGE_ACT_UPDATE = "2";

    // -----------------------------
    // Message
    // -----------------------------

    /** The field of [@] is not input. */
    public static final String ZZM9000E = "ZZM9000E";

    /** The value which is not numerical was input to the field of [@]. */
    public static final String ZZM9004E = "ZZM9004E";

    /** */
    public static final String ZZBM0009I = "ZZBM0009I";

    /** The record cannot be registered. 
     * Registration Table Name: [@], Table Name: [@], Key Field Name: [@], Key Value: [@] */
    public static final String NLGM0045E = "NLGM0045E";

    // -----------------------------
    // Error Mail Configuration Key
    // -----------------------------
    /** */
    public static final String KEY_MESSAGE_ID = "KEY_MESSAGE_ID";

    /** */
    public static final String KEY_MESSAGE = "KEY_MESSAGE";

    /** */
    public static final String MAIL_GROUP_ID = "NLBB1200";

    /** */
    public static final String MAIL_TEMPLATE_ID = "NLBB1200M001";

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

}
