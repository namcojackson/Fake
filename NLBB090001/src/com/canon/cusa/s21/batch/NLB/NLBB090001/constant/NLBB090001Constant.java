/**
 * <pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB090001.constant;

/**
 * <pre>    
 * Batch Program Class for Ship Parts Request to MNX
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 10/28/2020   CITS            H.Dimay         Create          QC#57659
 * 11/14/2020   CITS            K.Ogino         Update          QC#57659
 * 12/08/2020   CITS            K.Ogino         Update          QC#57659-2
 * 01/26/2020   CITS            A.Marte         Update          QC#58281
 *</pre>
 */
public class NLBB090001Constant {

    // *********************************************************
    // Constants: Basic
    // *********************************************************

    /** Business ID */
    public static final String BUSINESS_ID = "NLBB0900";

    /** Fetch size */
    public static final int FETCH_SIZE = 1000;

    /** Debug level for debug */
    public static final int CST_DEBUG_MSG_LVL = 1;

    /** SO TEXT MAX SIZE */
    public static final int SO_TEXT_MAX_SIZE = 400;

    /** Max length of CUST_ORD_NUM */
    public static final int MAXLEN_CUST_ORD_NUM = 30;

    /** Max length of CRAT_DT_TM_TS */
    public static final int MAXLEN_CRAT_DT_TM_TS = 14;

    /** Max length of CUST_DC_NUM */
    public static final int MAXLEN_CUST_DC_NUM = 8;

    /** Max length of OTBD_SRC_ORD_TP_TXT */
    public static final int MAXLEN_OTBD_SRC_ORD_TP_TXT = 30;

    /** Max length of SLS_ORD_ADMIN_NM */
    public static final int MAXLEN_SLS_ORD_ADMIN_NM = 20;

    /** Max length of SLS_REP_PSN_NM */
    public static final int MAXLEN_SLS_REP_PSN_NM = 20;

    /** Max length of WMS_SHIP_TO_NM_01 */
    public static final int MAXLEN_WMS_SHIP_TO_NM_01 = 35;

    /** Max length of WMS_SHIP_TO_NM_02 */
    public static final int MAXLEN_WMS_SHIP_TO_NM_02 = 35;

    /** Max length of SHIP_TO_CTAC_NUM */
    public static final int MAXLEN_SHIP_TO_CTAC_NUM = 15;

    /** Max length of WMS_CHRG_TO_NM_01 */
    public static final int MAXLEN_WMS_CHRG_TO_NM_01 = 35;

    /** Max length of WMS_CHRG_TO_NM_02 */
    public static final int MAXLEN_WMS_CHRG_TO_NM_02 = 35;

    /** Max length of CHRG_TO_CTAC_NUM */
    public static final int MAXLEN_CHRG_TO_CTAC_NUM = 15;

    /** Max length of WMS_BILL_TO_NM_01 */
    public static final int MAXLEN_WMS_BILL_TO_NM_01 = 35;

    /** Max length of WMS_BILL_TO_NM_02 */
    public static final int MAXLEN_WMS_BILL_TO_NM_02 = 35;

    /** Max length of BILL_TO_CTAC_NUM */
    public static final int MAXLEN_BILL_TO_CTAC_NUM = 15;

    /** Max length of COMPANY_NAME */
    public static final int MAXLEN_COMPANY_NAME = 100;

    /** Precision of TOT_SHIP_PRC_AMT_NUM */
    public static final int PRECN_TOT_SHIP_PRC_AMT_NUM = 15;

    /** Precision of TOT_WT_AMT_NUM */
    public static final int PRECN_TOT_WT_AMT_NUM = 15;

    /** Precision of UNIT_PRC_AMT_NUM */
    public static final int PRECN_UNIT_PRC_AMT_NUM = 15;

    /** Precision of UNIT_DISC_AMT_NUM */
    public static final int PRECN_UNIT_DISC_AMT_NUM = 15;

    /** Precision of UNIT_DISC_PRC_AMT_NUM */
    public static final int PRECN_UNIT_DISC_PRC_AMT_NUM = 15;

    /** Scale of TOT_SHIP_PRC_AMT_NUM */
    public static final int SCALE_TOT_SHIP_PRC_AMT_NUM = 2;

    /** Scale of TOT_WT_AMT_NUM */
    public static final int SCALE_TOT_WT_AMT_NUM = 4;

    /** Scale of UNIT_PRC_AMT_NUM */
    public static final int SCALE_UNIT_PRC_AMT_NUM = 2;

    /** Scale of UNIT_DISC_AMT_NUM */
    public static final int SCALE_UNIT_DISC_AMT_NUM = 2;

    /** Scale of UNIT_DISC_PRC_AMT_NUM */
    public static final int SCALE_UNIT_DISC_PRC_AMT_NUM = 2;

    /** Padding Number of SO_SLP_NUM */
    public static final int PADNUM_SO_SLP_NUM = 3;

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

    /** Datetime format: MM/dd/yyyy HH:mm */
    public static final String FMT_MMDDYYYYHHMM = "MM/dd/yyyy HH:mm";

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
     * TABLE_NAME : RTL_WH
     */
    public static final String RTL_WH = "RTL_WH";

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

    /** Table name : NLBI1410_01 */
    public static final String TBL_NLBI1410_01 = "NLBI1410_01";

    /** Table name : NLBI1410_02 */
    public static final String TBL_NLBI1410_02 = "NLBI1410_02";

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

    /** NLBB0900_TARGET_ORDER : MNX */
    public static final String VAR_CHAR_CONST_NM_TARGET_ORDER = "NLBB0900_TARGET_ORDER";

    /** NLBB0900_TARGET_PR_LINE_TP_CD : 1007 */
    public static final String VAR_CHAR_CONST_NM_TARGET_PR_LINE_TP_CD = "NLBB0900_TARGET_PR_LINE_TP_CD";

    /** MNX_INVOKE_MODE */
    public static final String VAR_CHAR_CONST_NM_MNX_INVOKE_MODE = "MNX_INVOKE_MODE";

    // Add QC#57659-2
    /** NLBB0900_DEF_SHIP_CTAC_NM :  */
    public static final String VAR_CHAR_CONST_NM_DEF_SHIP_CTAC_NM = "NLBB0900_DEF_SHIP_CTAC_NM";
    // Add QC#57659-2
    /** NLBB0900_TARGET_DEF_SHIP_PHO_NUM :  */
    public static final String VAR_CHAR_CONST_NM_DEF_SHIP_PHO_NUM = "NLBB0900_DEF_SHIP_PHO_NUM";

    // *********************************************************
    // Constants: Column Name
    // *********************************************************

    /** Column name of GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name of INTERFACE_ID */
    public static final String COL_INTERFACE_ID = "INTERFACE_ID";

    /** Column name of TRANSACTION_ID */
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /** Column name of SO_NUM */
    public static final String COL_SO_NUM = "SO_NUM";

    /** Column name of ALLOC_REQ_FLG */
    public static final String COL_ALLOC_REQ_FLG = "ALLOC_REQ_FLG";

    /** Column name of CARR_CD */
    public static final String COL_CARR_CD = "CARR_CD";

    /** Column name of CTAC_PTNR_PSN_NM */
    public static final String COL_CTAC_PTNR_PSN_NM = "CTAC_PTNR_PSN_NM";

    /** Column name of CTAC_PTNR_PSN_TEL_NUM */
    public static final String COL_CTAC_PTNR_PSN_TEL_NUM = "CTAC_PTNR_PSN_TEL_NUM";

    /** Column name of CTRY_CD */
    public static final String COL_CTRY_CD = "CTRY_CD";

    /** Column name of CTY_ADDR */
    public static final String COL_CTY_ADDR = "CTY_ADDR";

    /** Column name of DELY_ADDL_CMNT_TXT */
    public static final String COL_DELY_ADDL_CMNT_TXT = "DELY_ADDL_CMNT_TXT";

    /** Column name of FIRST_LINE_ADDR */
    public static final String COL_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /** Column name of FRTH_LINE_ADDR */
    public static final String COL_FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /** Column name of LOC_NM */
    public static final String COL_LOC_NM = "LOC_NM";

    /** Column name of MDSE_CD */
    public static final String COL_MDSE_CD = "MDSE_CD";

    /** Column name of OPEN_STS_FLG */
    public static final String COL_OPEN_STS_FLG = "OPEN_STS_FLG";

    /** Column name of ORD_QTY */
    public static final String COL_ORD_QTY = "ORD_QTY";

    /** Column name of POST_CD */
    public static final String COL_POST_CD = "POST_CD";

    /** Column name of PRCH_REQ_LINE_NUM */
    public static final String COL_PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** Column name of PRCH_REQ_LINE_SUB_NUM */
    public static final String COL_PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /** Column name of PRCH_REQ_LINE_TP_CD */
    public static final String COL_PRCH_REQ_LINE_TP_CD = "PRCH_REQ_LINE_TP_CD";

    /** Column name of PRCH_REQ_NUM */
    public static final String COL_PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** Column name of PRCH_REQ_REL_STS_CD */
    public static final String COL_PRCH_REQ_REL_STS_CD = "PRCH_REQ_REL_STS_CD";

    /** Column name of PRCH_REQ_TP_CD */
    public static final String COL_PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    /** Column name of SCD_LINE_ADDR */
    public static final String COL_SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /** Column name of SHIP_TO_CTRY_CD */
    public static final String COL_SHIP_TO_CTRY_CD = "SHIP_TO_CTRY_CD";

    /**Column name of SHIP_TO_CTY_ADDR */
    public static final String COL_SHIP_TO_CTY_ADDR = "SHIP_TO_CTY_ADDR";

    /** Column name of SHIP_TO_FIRST_LINE_ADDR */
    public static final String COL_SHIP_TO_FIRST_LINE_ADDR = "SHIP_TO_FIRST_LINE_ADDR";

    /** Column name of SHIP_TO_FRTH_LINE_ADDR */
    public static final String COL_SHIP_TO_FRTH_LINE_ADDR = "SHIP_TO_FRTH_LINE_ADDR";

    /** Column name of SHIP_TO_LOC_NM */
    public static final String COL_SHIP_TO_LOC_NM = "SHIP_TO_LOC_NM";

    /** Column name of SHIP_TO_POST_CD */
    public static final String COL_SHIP_TO_POST_CD = "SHIP_TO_POST_CD";

    /** Column name of SHIP_TO_SCD_LINE_ADDR */
    public static final String COL_SHIP_TO_SCD_LINE_ADDR = "SHIP_TO_SCD_LINE_ADDR";

    /** Column name of SHIP_TO_ST_CD */
    public static final String COL_SHIP_TO_ST_CD = "SHIP_TO_ST_CD";

    /** Column name of SHIP_TO_THIRD_LINE_ADDR */
    public static final String COL_SHIP_TO_THIRD_LINE_ADDR = "SHIP_TO_THIRD_LINE_ADDR";

    /** Column name of SHPG_QTY */
    public static final String COL_SHPG_QTY = "SHPG_QTY";

    /** Column name of SHPG_SVC_LVL_CD */
    public static final String COL_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** Column name of SO_MSG_DESC_TXT */
    public static final String COL_SO_MSG_DESC_TXT = "SO_MSG_DESC_TXT";

    /** Column name of SO_MSG_TP_CD */
    public static final String COL_SO_MSG_TP_CD = "SO_MSG_TP_CD";

    /** Column name of ST_CD */
    public static final String COL_ST_CD = "ST_CD";

    /** Column name of THIRD_LINE_ADDR */
    public static final String COL_THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /** Column name of TEL_NUM */
    public static final String COL_TEL_NUM = "TEL_NUM";

    /** Column name of TPL_LOC_NM */
    public static final String COL_TPL_LOC_NM = "TPL_LOC_NM";

    /** Column name of WH_OWNR_CD */
    public static final String COL_WH_OWNR_CD = "WH_OWNR_CD";

    /** Column name of WH_SYS_TP_CD */
    public static final String COL_WH_SYS_TP_CD = "WH_SYS_TP_CD";

    /** Column name of WMS_DROP_FLG */
    public static final String COL_WMS_DROP_FLG = "WMS_DROP_FLG";

    /** Column name of WMS_DROP_RQST_FLG */
    public static final String COL_WMS_DROP_RQST_FLG = "WMS_DROP_RQST_FLG";

    /** Column name of SRC_RTL_WH_CD */
    public static final String COL_SRC_RTL_WH_CD = "SRC_RTL_WH_CD";

    /** Column name of TPL_CARR_CD */
    public static final String COL_TPL_CARR_CD = "TPL_CARR_CD";

    /** Column name of TPL_CARR_NM */
    public static final String COL_TPL_CARR_NM = "TPL_CARR_NM";

    /** Column name of TPL_SVC_LVL_CD */
    public static final String COL_TPL_SVC_LVL_CD = "TPL_SVC_LVL_CD";

    /** Column name of TPL_SVC_LVL_NM */
    public static final String COL_TPL_SVC_LVL_NM = "TPL_SVC_LVL_NM";

    /** Column name of TPL_SPCL_SVC_NM */
    public static final String COL_TPL_SPCL_SVC_NM = "TPL_SPCL_SVC_NM";

    /** Column name of PRCH_REQ_QTY */
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

    /** Column name of RQST_RCV_DT_TM */
    public static final String COL_RQST_RCV_DT_TM = "RQST_RCV_DT_TM";

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

    /** Column name of DEST_RTL_WH_CD */
    public static final String COL_DEST_RTL_WH_CD = "DEST_RTL_WH_CD";

    /** Column name of ORIG_ORD_QTY_TXT */
    public static final String COL_ORIG_ORD_QTY_TXT = "ORIG_ORD_QTY_TXT";

    /** Column name of FR_SHIP_SO_CUST_LINE_LOC_NM_01 */
    public static final String COL_FR_SHIP_SO_CUST_LINE_LOC_NM_01 = "FR_SHIP_SO_CUST_LINE_LOC_NM_01";

    /** Column name of FR_SHIP_SO_CUST_LINE_LOC_NM_02 */
    public static final String COL_FR_SHIP_SO_CUST_LINE_LOC_NM_02 = "FR_SHIP_SO_CUST_LINE_LOC_NM_02";

    /** Column name of FR_SO_CUST_LINE_ADDR_01 */
    public static final String COL_FR_SO_CUST_LINE_ADDR_01 = "FR_SO_CUST_LINE_ADDR_01";

    /** Column name of FR_SO_CUST_LINE_ADDR_02 */
    public static final String COL_FR_SO_CUST_LINE_ADDR_02 = "FR_SO_CUST_LINE_ADDR_02";

    /** Column name of FR_SO_CUST_LINE_ADDR_03 */
    public static final String COL_FR_SO_CUST_LINE_ADDR_03 = "FR_SO_CUST_LINE_ADDR_03";

    /** Column name of FR_SO_CUST_LINE_ADDR_04 */
    public static final String COL_FR_SO_CUST_LINE_ADDR_04 = "FR_SO_CUST_LINE_ADDR_04";

    /** Column name of FR_CTY_ADDR */
    public static final String COL_FR_CTY_ADDR = "FR_CTY_ADDR";

    /** Column name of FR_ST_CD */
    public static final String COL_FR_ST_CD = "FR_ST_CD";

    /** Column name of FR_POST_CD */
    public static final String COL_FR_POST_CD = "FR_POST_CD";

    /** Column name of FR_CTRY_CD */
    public static final String COL_FR_CTRY_CD = "FR_CTRY_CD";

    /** Column name of SVC_TP_CD */
    public static final String COL_SVC_TP_CD = "SVC_TP_CD";

    /** Column name of SHIP_POST_TXT */
    public static final String COL_SHIP_POST_TXT = "SHIP_POST_TXT";

    /** Column name of SHIP_CTRY_TXT */
    public static final String COL_SHIP_CTRY_TXT = "SHIP_CTRY_TXT";

    /** Column name of REQ_DT_TM_TS_TXT */
    public static final String COL_REQ_DT_TM_TS_TXT = "REQ_DT_TM_TS_TXT";

    /** Column name of SHIP_CTAC_NM_TXT */
    public static final String COL_SHIP_CTAC_NM_TXT = "SHIP_CTAC_NM_TXT";

    /** Column name of SHIP_PHO_NUM_TXT */
    public static final String COL_SHIP_PHO_NUM_TXT = "SHIP_PHO_NUM_TXT";

    /** Column name of ORD_ID_TXT */
    public static final String COL_ORD_ID_TXT = "ORD_ID_TXT";

    /** Column name of SVC_TP_TXT */
    public static final String COL_SVC_TP_TXT = "SVC_TP_TXT";

    /** Column name of TPL_LOC_TXT */
    public static final String COL_TPL_LOC_TXT = "TPL_LOC_TXT";

    /** Column name of SHIP_FROM_NM_ALL_TXT */
    public static final String COL_SHIP_FROM_NM_ALL_TXT = "SHIP_FROM_NM_ALL_TXT";

    /** Column name of SHIP_FROM_ADDR_ALL_TXT */
    public static final String COL_SHIP_FROM_ADDR_ALL_TXT = "SHIP_FROM_ADDR_ALL_TXT";

    /** Column name of SHIP_FROM_CTY_TXT */
    public static final String COL_SHIP_FROM_CTY_TXT = "SHIP_FROM_CTY_TXT";

    /** Column name of SHIP_FROM_ST_TXT */
    public static final String COL_SHIP_FROM_ST_TXT = "SHIP_FROM_ST_TXT";

    /** Column name of SHIP_FROM_POST_TXT */
    public static final String COL_SHIP_FROM_POST_TXT = "SHIP_FROM_POST_TXT";

    /** Column name of SHIP_FROM_CTRY_TXT */
    public static final String COL_SHIP_FROM_CTRY_TXT = "SHIP_FROM_CTRY_TXT";

    /** Column name of SHIP_FROM_CTAC_NM */
    public static final String COL_SHIP_FROM_CTAC_NM = "SHIP_FROM_CTAC_NM";

    /** Column name of SHIP_FROM_PHO_NUM */
    public static final String COL_SHIP_FROM_PHO_NUM = "SHIP_FROM_PHO_NUM";

    /** Column name of SHIP_FROM_CMNT_ALL_TXT */
    public static final String COL_SHIP_FROM_CMNT_ALL_TXT = "SHIP_FROM_CMNT_ALL_TXT";

    /** Column name of SHIP_NM_ALL_TXT */
    public static final String COL_SHIP_NM_ALL_TXT = "SHIP_NM_ALL_TXT";

    /** Column name of SHIP_ADDR_ALL_TXT */
    public static final String COL_SHIP_ADDR_ALL_TXT = "SHIP_ADDR_ALL_TXT";

    /** Column name of SHIP_CTY_TXT */
    public static final String COL_SHIP_CTY_TXT = "SHIP_CTY_TXT";

    /** Column name of SHIP_ST_TXT */
    public static final String COL_SHIP_ST_TXT = "SHIP_ST_TXT";

    /** Column name of ORD_CMNT_ALL_TXT */
    public static final String COL_ORD_CMNT_ALL_TXT = "ORD_CMNT_ALL_TXT";

    /** Column name of TPL_CARR_SVC_LVL_CD */
    public static final String COL_TPL_CARR_SVC_LVL_CD = "TPL_CARR_SVC_LVL_CD";

    /** Column name of TPL_CARR_SVC_LVL_DESC_TXT */
    public static final String COL_TPL_CARR_SVC_LVL_DESC_TXT = "TPL_CARR_SVC_LVL_DESC_TXT";

    /** Column name of ORD_LINE_TXT */
    public static final String COL_ORD_LINE_TXT = "ORD_LINE_TXT";

    /** Column name of QTY_ORD_TXT */
    public static final String COL_QTY_ORD_TXT = "QTY_ORD_TXT";

    /** Column name of ITEM_CD_TXT */
    public static final String COL_ITEM_CD_TXT = "ITEM_CD_TXT";

    /** Column name of SEGMENT_ID */
    public static final String SEGMENT_ID = "SEGMENT_ID";

    /**  */
    public static final String BIND_SALES_DATE = "SALES_DATE";

    /**  */
    public static final String BIND_SO_CUST_DATA_TP_CD_SHIP = "SO_CUST_DATA_TP_CD_SHIP";

    /**  */
    public static final String BIND_SO_CUST_DATA_TP_CD_SELL = "SO_CUST_DATA_TP_CD_SELL";

    /** */
    public static final String BIND_SO_CUST_DATA_TP_CD_BILL = "SO_CUST_DATA_TP_CD_BILL";

    // START 2021/01/26 A.Marte [QC#58281, ADD]
    /** Column name of SEGMENT_ID*/
    public static final String COL_SEGMENT_ID = "SEGMENT_ID";
    // END 2021/01/26 A.Marte [QC#58281, ADD]


    // *********************************************************
    // Constants: Value
    // *********************************************************

    /**
     * SO_MSG_TP_CD: Delivery Message
     */
    public static final String VAL_TP_DELIV_MSG = "D";

    /**
     * TPL_FROM_PTNR_ID : MNX
     */
    public static final String VAL_TPL_FROM_PTNR_ID = "MNX";

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

    /**
     * SVC_TP_TXT : CPU
     */
    public static final String VAL_SVC_TP_TXT_CPU = "CPU";

    /**
     * SVC_TP_TXT : LOC
     */
    public static final String VAL_SVC_TP_TXT_LOC = "LOC";

    /**
     * SVC_TP_TXT : H3P
     */
    public static final String VAL_SVC_TP_TXT_H3P = "H3P";

    /**
     * MNX_INVOKE_MODE : 1
     */
    public static final String VAL_MNX_INVOKE_MODE_STUB_SUCCESS = "1";

    /**
     * MNX_INVOKE_MODE : 2
     */
    public static final String VAL_MNX_INVOKE_MODE_STUB_ERROR = "2";

    /**
     * MNX_INVOKE_MODE : 3
     */
    public static final String VAL_MNX_INVOKE_MODE_STUB_TIMEOUT = "3";

    /**
     * SpecialShipMethod : Saturday Delivery 
     */
    public static final String VAL_SPCL_SHIP_METHOD_SAT_DELY = "Saturday Delivery";

    /**
     * MNX Response Type: SUCCESS
     */
    public static final String VAL_MNX_RESPONSE_TYPE_SUCCESS = "SUCCESS";

    /**
     * MNX Response Type: ERROR
     */
    public static final String VAL_MNX_RESPONSE_TYPE_ERROR = "ERROR";

    /** */
    public static final String COL_CTAC_PSN_NM = "CTAC_PSN_NM";

    /** */
    public static final String SPACE = " ";

    /** */
    public static final String REG_CRLF = "\r\n";

    // START 2021/01/26 A.Marte [QC#58281, ADD]
    /** Mail Line Separator */
    public static final String LINE_SEPT = System.getProperty("line.separator");

    /** Value Blank */
    public static final String VAL_BLANK_14 = "              ";

    /** Error Main Split Line */
    public static final String VAL_SEP_LINE = "-------------------------------------------------------------------------";

    /** Value Blank */
    public static final String VAL_BLANK = " ";
    // END 2021/01/26 A.Marte [QC#58281, ADD]

    // *********************************************************
    // Constants: Stub Function Values
    // *********************************************************

    /**
     * stub_stiShippingOrder_Success: OrderNumber
     */
    public static final int STUB_SO_ORDER_NUM = 90683644;

    /**
     * stub_stiShippingOrder_Success: PickupNumber
     */
    public static final int STUB_SO_PICKUP_NUM = 9999999;

    /**
     * stub_stiShippingOrder_Success: VoucherNumber
     */
    public static final String STUB_SO_VOUCHER_NUM = "121070699";
}
