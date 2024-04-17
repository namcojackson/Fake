/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB060001.constant;

/**
 * <pre>    
 * Batch Program Class for Ship Parts Request to Choice
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 02/03/2016   CITS            S.Tanikawa      Create          
 * 06/26/2017   CITS            Y.IWASAKI       Update          QC#19370
 * 07/11/2017   CITS            Y.IWASAKI       Update          QC#19695
 * 07/14/2017   CITS            Y.IWASAKI       Update          QC#19942
 * 12/20/2017   CITS            S.Katsuma       Update          QC#22592
 * 05/20/2019   CITS            K.Ogino         Update          QC#50072
 * 06/19/2019   CITS            T.Wada          Update          QC#50866
 *</pre>
 */
public class NLBB060001Constant {

    // *********************************************************
    // Constants: Basic
    // *********************************************************
    
    /** Business ID */
    public static final String BUSINESS_ID = "NLBB0600";

    /** Fetch size */
    public static final int FETCH_SIZE = 1000;

    /** Debug level for debug */
    public static final int CST_DEBUG_MSG_LVL = 1;

    /** SO TEXT MAX SIZE */
    public static final int SO_TEXT_MAX_SIZE = 400;

    // *********************************************************
    // Constants: Message ID
    // *********************************************************

    /** Message ID : ZZBM0009I */
    public static final String ZZBM0009I = "ZZBM0009I";

    /** Message ID : ZZM9000E The field of [@] is not input. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message ID : ZZM9004E The value which is not numerical was input to the field of [@]. */
    public static final String ZZM9004E = "ZZM9004E";

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

    /** It failed to register an email. */
    public static final String NLEM0004E = "NLEM0004E";

    // *********************************************************
    // Constants: Message Parameter
    // *********************************************************

    /** Parameter Name: GLBL_CMPY_CD */
    public static final String PARAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Parameter Name: INTERFACE_ID */
    public static final String PARAM_NM_INTERFACE_ID = "Interface ID";

    /** Parameter Name : VAR_USER1 */
    public static final String PARAM_NM_VAR_USER1 = "Commit Count(VAR_USER1)";

    /** Parameter Name : VAR_USER2 */
    public static final String PARAM_NM_VAR_USER2 = "XXX(VAR_USER2)";

    /** Parameter Name : VAR_USER3 */
    public static final String PARAM_NM_VAR_USER3 = "XXX(VAR_USER3)";

    // *********************************************************
    // Constants: Format pattern
    // *********************************************************

    /** Datetime format : yyyyMMdd */
    public static final String FMT_YYYYMMDD = "yyyyMMdd";

    /** Datetime format : HHmmssSSS */
    public static final String FMT_HHMMSSSSS = "HHmmssSSS";

    /** Datetime format : yyyyMMddHHmmss */
    public static final String FMT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /** Datetime format : yyyyMMddHHmmssSSS */
    public static final String FMT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    // *********************************************************
    // Constants: Table Name
    // *********************************************************
    
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
    
    /** Table name : NLBI1010_01 */
    public static final String TBL_NLBI1010_01 = "NLBI1010_01";
    
    /** Table name : NLBI1010_02 */
    public static final String TBL_NLBI1010_02 = "NLBI1010_02";

    /** Table name of WMS_INBD_SO_HDR */
    public static final String TBL_WMS_INBD_SO_HDR = "WMS_INBD_SO_HDR";

    /** Table name of WMS_INBD_SO_DTL */
    public static final String TBL_WMS_INBD_SO_DTL = "WMS_INBD_SO_DTL";

    /** Table name of WMS_INBD_SO_TEXT */
    public static final String TBL_WMS_INBD_SO_TEXT = "WMS_INBD_SO_TEXT";

    /** Table name of WMS_INBD_SO_SHIP_TO */
    public static final String TBL_WMS_INBD_SO_SHIP_TO = "WMS_INBD_SO_SHIP_TO";

    /** Table name of WMS_INBD_SO_CHRG_TO */
    public static final String TBL_WMS_INBD_SO_CHRG_TO = "WMS_INBD_SO_CHRG_TO";

    /** Table name of WMS_INBD_SO_BILL_TO */
    public static final String TBL_WMS_INBD_SO_BILL_TO = "WMS_INBD_SO_BILL_TO";

    // *********************************************************
    // Constants: VAR_CHAR_CONST Name
    // *********************************************************

    /** NLBB0600_TARGET_ORDER : CHO */
    public static final String VAR_CHAR_CONST_NM_TARGET_ORDER = "NLBB0600_TARGET_ORDER";

    /** NLBB0600_TARGET_PR_LINE_TP_CD : 1006 */
    public static final String VAR_CHAR_CONST_NM_TARGET_PR_LINE_TP_CD = "NLBB0600_TARGET_PR_LINE_TP_CD";

    // *********************************************************
    // Constants: Column Name
    // *********************************************************

    /**
     * DB COLUMN : GLBL_CMPY_CD
     */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /**
     * DB COLUMN : SO_NUM
     */
    public static final String COL_SO_NUM = "SO_NUM";

    /**
     * DB COLUMN : ALLOC_REQ_FLG
     */
    public static final String COL_ALLOC_REQ_FLG = "ALLOC_REQ_FLG";

    /**
     * DB COLUMN : CARR_CD
     */
    public static final String COL_CARR_CD = "CARR_CD";

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
     * DB COLUMN : PRCH_REQ_LINE_TP_CD
     */
    public static final String COL_PRCH_REQ_LINE_TP_CD = "PRCH_REQ_LINE_TP_CD";

    /**
     * DB COLUMN : PRCH_REQ_NUM
     */
    public static final String COL_PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /**
     * DB COLUMN : PRCH_REQ_REL_STS_CD
     */
    public static final String COL_PRCH_REQ_REL_STS_CD = "PRCH_REQ_REL_STS_CD";

    /**
     * DB COLUMN : SCD_LINE_ADDR
     */
    public static final String COL_SCD_LINE_ADDR = "SCD_LINE_ADDR";

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
     * DB COLUMN : WH_SYS_TP_CD
     */
    public static final String COL_WH_SYS_TP_CD = "WH_SYS_TP_CD";

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
     * DB COLUMN : PRCH_REQ_QTY
     */
    public static final String COL_PRCH_REQ_QTY = "PRCH_REQ_QTY";

    /** Column name of INBD_OTBD_CD */
    public static final String COL_INBD_OTBD_CD = "INBD_OTBD_CD";

    /** Column name of WH_CD */
    public static final String COL_WH_CD = "WH_CD";

    /** Column name of S80_CMPY_CD */
    public static final String COL_S80_CMPY_CD = "S80_CMPY_CD";

    /** Column name of TRX_HDR_NUM */
    public static final String COL_TRX_HDR_NUM = "TRX_HDR_NUM";

    /** Column name of PICK_TKT_NUM */
    public static final String COL_PICK_TKT_NUM = "PICK_TKT_NUM";

    /** Column name of CUST_ISS_PO_NUM */
    public static final String COL_CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";

    /** Column name of SELL_TO_CUST_CD */
    public static final String COL_SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** Column name of BILL_TO_CUST_CD */
    public static final String COL_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** Column name of SHIP_TO_CUST_CD */
    public static final String COL_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** Column name of SCE_ORD_TP_CD */
    public static final String COL_SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /** Column name of S80_ORD_TP_CD */
    public static final String COL_S80_ORD_TP_CD = "S80_ORD_TP_CD";

    /** Column name of S80_TRX_CD */
    public static final String COL_S80_TRX_CD = "S80_TRX_CD";

    /** Column name of S80_ORD_SRC_CD */
    public static final String COL_S80_ORD_SRC_CD = "S80_ORD_SRC_CD";

    /** Column name of SO_SHIP_VIA_CD */
    public static final String COL_SO_SHIP_VIA_CD = "SO_SHIP_VIA_CD";

    /** Column name of SHIP_VIA_DESC_TXT */
    public static final String COL_SHIP_VIA_DESC_TXT = "SHIP_VIA_DESC_TXT";

    /** Column name of SO_CRAT_TS */
    public static final String COL_SO_CRAT_TS = "SO_CRAT_TS";

    /** Column name of PSD_DT */
    public static final String COL_PSD_DT = "PSD_DT";

    /** Column name of RDD_DT */
    public static final String COL_RDD_DT = "RDD_DT";

    /** Column name of DNLD_TM_TS */
    public static final String COL_DNLD_TM_TS = "DNLD_TM_TS";

    /** Column name of S80_SHPG_TERM_CD */
    public static final String COL_S80_SHPG_TERM_CD = "S80_SHPG_TERM_CD";

    /** Column name of S80_SHPG_TERM_NM */
    public static final String COL_S80_SHPG_TERM_NM = "S80_SHPG_TERM_NM";

    /** Column name of DROP_SHIP_FLG */
    public static final String COL_DROP_SHIP_FLG = "DROP_SHIP_FLG";

    /** Column name of PRINT_SCC_LB_FLG */
    public static final String COL_PRINT_SCC_LB_FLG = "PRINT_SCC_LB_FLG";

    /** Column name of PRINT_UCC_LB_FLG */
    public static final String COL_PRINT_UCC_LB_FLG = "PRINT_UCC_LB_FLG";

    /** Column name of MIX_PLT_ALLW_FLG */
    public static final String COL_MIX_PLT_ALLW_FLG = "MIX_PLT_ALLW_FLG";

    /** Column name of PRINT_PLT_UCC_LB_FLG */
    public static final String COL_PRINT_PLT_UCC_LB_FLG = "PRINT_PLT_UCC_LB_FLG";

    /** Column name of PRINT_NON_ASN_UCC_LB_FLG */
    public static final String COL_PRINT_NON_ASN_UCC_LB_FLG = "PRINT_NON_ASN_UCC_LB_FLG";

    /** Column name of ASN_REQ_FLG */
    public static final String COL_ASN_REQ_FLG = "ASN_REQ_FLG";

    /** Column name of IND_UCC_NUM_FLG */
    public static final String COL_IND_UCC_NUM_FLG = "IND_UCC_NUM_FLG";

    /** Column name of EDI_TP_CD */
    public static final String COL_EDI_TP_CD = "EDI_TP_CD";

    /** Column name of CUST_STORE_NUM */
    public static final String COL_CUST_STORE_NUM = "CUST_STORE_NUM";

    /** Column name of SO_DEPT_NUM */
    public static final String COL_SO_DEPT_NUM = "SO_DEPT_NUM";

    /** Column name of TOT_SHIP_AMT */
    public static final String COL_TOT_SHIP_AMT = "TOT_SHIP_AMT";

    /** Column name of TOT_SHPG_WT */
    public static final String COL_TOT_SHPG_WT = "TOT_SHPG_WT";

    /** Column name of TOT_WT_AMT_NUM */
    public static final String COL_TOT_WT_AMT_NUM = "TOT_WT_AMT_NUM";

    /** Column name of RTRN_LB_CD */
    public static final String COL_RTRN_LB_CD = "RTRN_LB_CD";

    /** Column name of SO_CONFIG_FLG */
    public static final String COL_SO_CONFIG_FLG = "SO_CONFIG_FLG";

    /** Column name of CARR_ACCT_NUM */
    public static final String COL_CARR_ACCT_NUM = "CARR_ACCT_NUM";

    /** Column name of WMS_SCHD_DELY_DT */
    public static final String COL_WMS_SCHD_DELY_DT = "WMS_SCHD_DELY_DT";

    /** Column name of WMS_CARR_CD */
    public static final String COL_WMS_CARR_CD = "WMS_CARR_CD";

    /** Column name of RTRN_ITEM_INCL_FLG */
    public static final String COL_RTRN_ITEM_INCL_FLG = "RTRN_ITEM_INCL_FLG";

    /** Column name of SVC_CONFIG_MSTR_PK */
    public static final String COL_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** Column name of PRE_ISTL_SHOP_RQST_FLG */
    public static final String COL_PRE_ISTL_SHOP_RQST_FLG = "PRE_ISTL_SHOP_RQST_FLG";

    /** COlumn name of STAGE_ACT_CD */
    public static final String COL_STAGE_ACT_CD = "STAGE_ACT_CD";

    /** Column name of WMS_RTE_PATH_CD */
    public static final String COL_WMS_RTE_PATH_CD = "WMS_RTE_PATH_CD";

    /** Column name of SRC_ORD_NUM */
    public static final String COL_SRC_ORD_NUM = "SRC_ORD_NUM";

    /** */
    public static final String COL_CPO_ORD_NUM = "CPO_ORD_NUM";

    /** */
    public static final String COL_DS_ORD_CATG_DESC_TXT = "DS_ORD_CATG_DESC_TXT";

    /** */
    public static final String COL_SCE_ORD_TP_NM = "SCE_ORD_TP_NM";

    /** */
    public static final String COL_PO_ORD_NUM = "PO_ORD_NUM";

    /** */
    public static final String COL_MIN_DATE = "MIN_DATE";

    /** */
    public static final String COL_MAX_DATE = "MAX_DATE";

    /** */
    public static final String COL_PSN_FIRST_NM = "PSN_FIRST_NM";

    /** */
    public static final String COL_PSN_LAST_NM = "PSN_LAST_NM";

    /** Column name of SO_SLP_NUM */
    public static final String COL_SO_SLP_NUM = "SO_SLP_NUM";

    /** Column name of S80_STK_STS_CD */
    public static final String COL_S80_STK_STS_CD = "S80_STK_STS_CD";

    /** Column name of CUST_MDSE_CD */
    public static final String COL_CUST_MDSE_CD = "CUST_MDSE_CD";

    /** Column name of RQST_ORD_QTY */
    public static final String COL_RQST_ORD_QTY = "RQST_ORD_QTY";

    /** Column name of SHPG_BAL_QTY */
    public static final String COL_SHPG_BAL_QTY = "SHPG_BAL_QTY";

    /** Column name of UNIT_PRC_AMT */
    public static final String COL_UNIT_PRC_AMT = "UNIT_PRC_AMT";

    /** Column name of DISC_PRC_AMT */
    public static final String COL_DISC_PRC_AMT = "DISC_PRC_AMT";

    /** Column name of DISC_UNIT_PRC_AMT */
    public static final String COL_DISC_UNIT_PRC_AMT = "DISC_UNIT_PRC_AMT";

    /** Column name of SER_NUM_TAKE_FLG */
    public static final String COL_SER_NUM_TAKE_FLG = "SER_NUM_TAKE_FLG";

    /** Column name of S80_STK_STS_TO_CD */
    public static final String COL_S80_STK_STS_TO_CD = "S80_STK_STS_TO_CD";

    /** Column name of SET_MDSE_CD */
    public static final String COL_SET_MDSE_CD = "SET_MDSE_CD";

    /** Column name of SET_MDSE_NM */
    public static final String COL_SET_MDSE_NM = "SET_MDSE_NM";

    /** Column name of SET_SHPG_QTY */
    public static final String COL_SET_SHPG_QTY = "SET_SHPG_QTY";

    /** Column name of IN_EACH_QTY */
    public static final String COL_IN_EACH_QTY = "IN_EACH_QTY";

    /** Column name of TOT_SHPG_VOL */
    public static final String COL_TOT_SHPG_VOL = "TOT_SHPG_VOL";

    /** Column name of BAT_NUM_TAKE_FLG */
    public static final String COL_BAT_NUM_TAKE_FLG = "BAT_NUM_TAKE_FLG";

    /** Column name of CONFIG_ITEM_FLG */
    public static final String COL_CONFIG_ITEM_FLG = "CONFIG_ITEM_FLG";

    /** Column name of RTL_WH_CD */
    public static final String COL_RTL_WH_CD = "RTL_WH_CD";

    /** Column name of RTL_SWH_CD */
    public static final String COL_RTL_SWH_CD = "RTL_SWH_CD";

    /** Column name of PICK_SVC_CONFIG_MSTR_PK */
    public static final String COL_PICK_SVC_CONFIG_MSTR_PK = "PICK_SVC_CONFIG_MSTR_PK";

    /** Column name of BACK_ORD_IMPCT_TP_CD */
    public static final String COL_BACK_ORD_IMPCT_TP_CD = "BACK_ORD_IMPCT_TP_CD";

    /** Column name of TRX_LINE_NUM */
    public static final String COL_TRX_LINE_NUM = "TRX_LINE_NUM";

    /** Column name of RMV_CONFIG_FLG */
    public static final String COL_RMV_CONFIG_FLG = "RMV_CONFIG_FLG";

    /** Column name of ORIG_ORD_QTY */
    public static final String COL_ORIG_ORD_QTY = "ORIG_ORD_QTY";

    /** Column name of SHIP_SO_CUST_LINE_LOC_NM_01 */
    public static final String COL_SHIP_SO_CUST_LINE_LOC_NM_01 = "SHIP_SO_CUST_LINE_LOC_NM_01";

    /** Column name of SHIP_SO_CUST_LINE_LOC_NM_02 */
    public static final String COL_SHIP_SO_CUST_LINE_LOC_NM_02 = "SHIP_SO_CUST_LINE_LOC_NM_02";

    /** Column name of SHIP_SO_CUST_LINE_ADDR_01 */
    public static final String COL_SHIP_SO_CUST_LINE_ADDR_01 = "SHIP_SO_CUST_LINE_ADDR_01";

    /** Column name of SHIP_SO_CUST_LINE_ADDR_02 */
    public static final String COL_SHIP_SO_CUST_LINE_ADDR_02 = "SHIP_SO_CUST_LINE_ADDR_02";

    /** Column name of SHIP_SO_CUST_LINE_ADDR_03 */
    public static final String COL_SHIP_SO_CUST_LINE_ADDR_03 = "SHIP_SO_CUST_LINE_ADDR_03";

    /** Column name of SHIP_SO_CUST_LINE_ADDR_04 */
    public static final String COL_SHIP_SO_CUST_LINE_ADDR_04 = "SHIP_SO_CUST_LINE_ADDR_04";

    /** Column name of SHIP_CTY_ADDR */
    public static final String COL_SHIP_CTY_ADDR = "SHIP_CTY_ADDR";

    /** Column name of SHIP_ST_CD */
    public static final String COL_SHIP_ST_CD = "SHIP_ST_CD";

    /** Column name of SHIP_POST_CD */
    public static final String COL_SHIP_POST_CD = "SHIP_POST_CD";

    /** Column name of SHIP_CTRY_CD */
    public static final String COL_SHIP_CTRY_CD = "SHIP_CTRY_CD";

    /** Column name of SHIP_CTAC_PTNR_PSN_NM */
    public static final String COL_SHIP_CTAC_PTNR_PSN_NM = "SHIP_CTAC_PTNR_PSN_NM";

    /** Column name of SHIP_CTAC_PTNR_PSN_TEL_NUM */
    public static final String COL_SHIP_CTAC_PTNR_PSN_TEL_NUM = "SHIP_CTAC_PTNR_PSN_TEL_NUM";

    /** Column name of SELL_SO_CUST_LINE_LOC_NM_01 */
    public static final String COL_SELL_SO_CUST_LINE_LOC_NM_01 = "SELL_SO_CUST_LINE_LOC_NM_01";

    /** Column name of SELL_SO_CUST_LINE_LOC_NM_02 */
    public static final String COL_SELL_SO_CUST_LINE_LOC_NM_02 = "SELL_SO_CUST_LINE_LOC_NM_02";

    /** Column name of SELL_SO_CUST_LINE_ADDR_01 */
    public static final String COL_SELL_SO_CUST_LINE_ADDR_01 = "SELL_SO_CUST_LINE_ADDR_01";

    /** Column name of SELL_SO_CUST_LINE_ADDR_02 */
    public static final String COL_SELL_SO_CUST_LINE_ADDR_02 = "SELL_SO_CUST_LINE_ADDR_02";

    /** Column name of SELL_SO_CUST_LINE_ADDR_03 */
    public static final String COL_SELL_SO_CUST_LINE_ADDR_03 = "SELL_SO_CUST_LINE_ADDR_03";

    /** Column name of SELL_SO_CUST_LINE_ADDR_04 */
    public static final String COL_SELL_SO_CUST_LINE_ADDR_04 = "SELL_SO_CUST_LINE_ADDR_04";

    /** Column name of SELL_CTY_ADDR */
    public static final String COL_SELL_CTY_ADDR = "SELL_CTY_ADDR";

    /** Column name of SELL_ST_CD */
    public static final String COL_SELL_ST_CD = "SELL_ST_CD";

    /** Column name of SELL_POST_CD */
    public static final String COL_SELL_POST_CD = "SELL_POST_CD";

    /** Column name of SELL_CTRY_CD */
    public static final String COL_SELL_CTRY_CD = "SELL_CTRY_CD";

    /** Column name of SELL_CTAC_PTNR_PSN_NM */
    public static final String COL_SELL_CTAC_PTNR_PSN_NM = "SELL_CTAC_PTNR_PSN_NM";

    /** Column name of SELL_CTAC_PTNR_PSN_TEL_NUM */
    public static final String COL_SELL_CTAC_PTNR_PSN_TEL_NUM = "SELL_CTAC_PTNR_PSN_TEL_NUM";

    /** Column name of BILL_SO_CUST_LINE_LOC_NM_01 */
    public static final String COL_BILL_SO_CUST_LINE_LOC_NM_01 = "BILL_SO_CUST_LINE_LOC_NM_01";

    /** Column name of BILL_SO_CUST_LINE_LOC_NM_02 */
    public static final String COL_BILL_SO_CUST_LINE_LOC_NM_02 = "BILL_SO_CUST_LINE_LOC_NM_02";

    /** Column name of BILL_SO_CUST_LINE_ADDR_01 */
    public static final String COL_BILL_SO_CUST_LINE_ADDR_01 = "BILL_SO_CUST_LINE_ADDR_01";

    /** Column name of BILL_SO_CUST_LINE_ADDR_02 */
    public static final String COL_BILL_SO_CUST_LINE_ADDR_02 = "BILL_SO_CUST_LINE_ADDR_02";

    /** Column name of BILL_SO_CUST_LINE_ADDR_03 */
    public static final String COL_BILL_SO_CUST_LINE_ADDR_03 = "BILL_SO_CUST_LINE_ADDR_03";

    /** Column name of BILL_SO_CUST_LINE_ADDR_04 */
    public static final String COL_BILL_SO_CUST_LINE_ADDR_04 = "BILL_SO_CUST_LINE_ADDR_04";

    /** Column name of BILL_CTY_ADDR */
    public static final String COL_BILL_CTY_ADDR = "BILL_CTY_ADDR";

    /** Column name of BILL_ST_CD */
    public static final String COL_BILL_ST_CD = "BILL_ST_CD";

    /** Column name of BILL_POST_CD */
    public static final String COL_BILL_POST_CD = "BILL_POST_CD";

    /** Column name of BILL_CTRY_CD */
    public static final String COL_BILL_CTRY_CD = "BILL_CTRY_CD";

    /** Column name of BILL_CTAC_PTNR_PSN_NM */
    public static final String COL_BILL_CTAC_PTNR_PSN_NM = "BILL_CTAC_PTNR_PSN_NM";

    /** Column name of BILL_CTAC_PTNR_PSN_TEL_NUM */
    public static final String COL_BILL_CTAC_PTNR_PSN_TEL_NUM = "BILL_CTAC_PTNR_PSN_TEL_NUM";

    /** */
    public static final String COL_ORIG_ORD_QTY_TXT = "ORIG_ORD_QTY_TXT";

    /** */
    public static final String BIND_SALES_DATE = "SALES_DATE";

    /** */
    public static final String BIND_SO_CUST_DATA_TP_CD_SHIP = "SO_CUST_DATA_TP_CD_SHIP";

    /** */
    public static final String BIND_SO_CUST_DATA_TP_CD_SELL = "SO_CUST_DATA_TP_CD_SELL";

    /** */
    public static final String BIND_SO_CUST_DATA_TP_CD_BILL = "SO_CUST_DATA_TP_CD_BILL";

    // *********************************************************
    // Constants: Value
    // *********************************************************

    /**
     * SO_MSG_TP_CD: Delivery Message
     */
    public static final String VAL_TP_DELIV_MSG = "D";

    /**
     * TPL_FROM_PTNR_ID : CHOICE
     */
    public static final String VAL_TPL_FROM_PTNR_ID = "CHOICE";

    /**
     * TPL_TO_PTNR_ID : OCE
     */
    public static final String VAL_TPL_TO_PTNR_ID = "OCE";

    /**
     * TPL_ACCT_TXT : OCE
     */
    public static final String VAL_TPL_ACCT_TXT = "OCE";

    /**
     * WMS_REC_ID : 01
     */
    public static final String VAL_WMS_REC_ID_H = "01";

    /**
     * WMS_REC_ID : 02
     */
    public static final String VAL_WMS_REC_ID_D = "02";

    /**
     * WH_SYS_TP : 3PL (04)
     */
    public static final String VAL_WH_SYS_TP_3PL = "04";

    /** */
    public static final String VAL_INTFC_TP_ID = "02";

    /** */
    public static final String VAL_INTFC_REC_TP_ID_HDR = "1";

    /** */
    public static final String VAL_INTFC_REC_TP_ID_DTL = "2";

    /** */
    public static final String VAL_INTFC_REC_TP_ID_SHIPTO = "3";

    /** */
    public static final String VAL_INTFC_REC_TP_ID_TEXT = "4";

    /** */
    public static final String VAL_INTFC_REC_TP_ID_CHRGTO = "5";

    /** */
    public static final String VAL_INTFC_REC_TP_ID_BILLTO = "6";

    /** */
    public static final String VAL_WMS_PRTY_CD = "5";

    /** */
    public static final String VAL_WMS_SO_STS_CD = "R";

    /** */
    public static final String VAL_STAGE_REC_STS_CD = "2";

    /** */
    public static final String VAL_MIN_DATE = "00000000";

    /** */
    public static final String VAL_MAX_DATE = "99999999";

    /** */
    public static final String VAL_WMS_PRINT_TP_CD = "B";

    /** */
    public static final String COL_CTAC_PSN_NM = "CTAC_PSN_NM";

    /** */
    public static final String SPACE = " ";

    /** */
    public static final String REG_CRLF = "\r\n";
}
