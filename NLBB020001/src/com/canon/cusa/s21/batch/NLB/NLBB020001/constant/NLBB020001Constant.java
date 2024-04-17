/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB020001.constant;

/**
 * <pre>
 * Batch Program Constant Class for Receive EDI214
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/25/2016   CITS            T.Wada          Create          
 * 01/17/2018   CITS            S.Katsuma       Update          QC#23056
 * 02/23/2018   CITS            S.Katsuma       Update          QC#24011
 * 02/21/2019   Hitachi         H.Umeda         Update          QC#30411
 * 03/29/2019   Fujitsu         T.Ogura         Update          QC#30594
 * 05/24/2019   CITS            T.Tokutomi      Update          QC#50528
 *</pre>
 */
public class NLBB020001Constant {

    /** Business ID */
    // START 2019/02/21 [QC#30411, MOD]
    // public static final String BUSINESS_ID = "NLBB020";
    public static final String BUSINESS_ID = "NLBB0200";
    // END   2019/02/21 [QC#30411, MOD]

    // *********************************************************
    // Constants: Message ID
    // *********************************************************
    /** Message ID: Interface ID has not been set. */
    public static final String NLBI0020 = "NLBI0020";

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

    /** It failed to register an email. */
    public static final String NLEM0004E = "NLEM0004E";

    /** Error Code Validation Check Error */
    public static final int ERR_CD_VAL_CHECK = -1;

    /** DB Table NLBI1220_03 : */
    public static final String TBL_NLBI1310_01 = "NLBI1310_01";

    /** DB Table POD_ADDR_WRK : */
    public static final String TBL_POD_ADDR_WRK = "POD_ADDR_WRK";

    /** DB Table POD_STS_WRK : */
    public static final String TBL_POD_STS_WRK = "POD_STS_WRK";

    /** Debug level for Debug */
    public static final int CST_DEBUG_MSG_LVL = 1;

    /** Prameter Name: GLBL_CMPY_CD */
    public static final String PRAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Prameter Name: INTERFACE_ID */
    public static final String PRAM_NM_INTERFACE_ID = "Interface Id";

    /** Date and time formats */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmss";

    /** DB COLUMN :INTERFACE_ID **/
    public static final String COL_INTERFACE_ID = "INTERFACE_ID";

    /** DB COLUMN :TRANSACTION_ID **/
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /** DB COLUMN :SEGMENT_ID **/
    public static final String COL_SEGMENT_ID = "SEGMENT_ID";

    /** DB COLUMN :UNIT_ID **/
    public static final String COL_UNIT_ID = "UNIT_ID";

    /** DB COLUMN :SEQ_NUMBER **/
    public static final String COL_SEQ_NUMBER = "SEQ_NUMBER";

    /** DB COLUMN :EDI_CMPY_CD **/
    public static final String COL_EDI_CMPY_CD = "EDI_CMPY_CD";

    /** DB COLUMN :EDI_ACCT_NUM **/
    public static final String COL_EDI_ACCT_NUM = "EDI_ACCT_NUM";

    /** DB COLUMN :EDI_AFRT_NUM **/
    public static final String COL_EDI_AFRT_NUM = "EDI_AFRT_NUM";

    /** DB COLUMN :EDI_SHIP_DT_TXT **/
    public static final String COL_EDI_SHIP_DT_TXT = "EDI_SHIP_DT_TXT";

    /** DB COLUMN :EDI_CMTE_DT_TXT **/
    public static final String COL_EDI_CMTE_DT_TXT = "EDI_CMTE_DT_TXT";

    /** DB COLUMN :EDI_CMTE_TS **/
    public static final String COL_EDI_CMTE_TS = "EDI_CMTE_TS";

    /** DB COLUMN :EDI_TRK_IND **/
    public static final String COL_EDI_TRK_IND = "EDI_TRK_IND";

    /** DB COLUMN :EDI_SHPPR_NM **/
    public static final String COL_EDI_SHPPR_NM = "EDI_SHPPR_NM";

    /** DB COLUMN :EDI_SHPPR_CMPY_TXT **/
    public static final String COL_EDI_SHPPR_CMPY_TXT = "EDI_SHPPR_CMPY_TXT";

    /** DB COLUMN :EDI_SHPPR_ADDR **/
    public static final String COL_EDI_SHPPR_ADDR = "EDI_SHPPR_ADDR";

    /** DB COLUMN :EDI_SHPPR_CTY_NM **/
    public static final String COL_EDI_SHPPR_CTY_NM = "EDI_SHPPR_CTY_NM";

    /** DB COLUMN :EDI_SHPPR_ST_CD **/
    public static final String COL_EDI_SHPPR_ST_CD = "EDI_SHPPR_ST_CD";

    /** DB COLUMN :EDI_SHPPR_POST_CD **/
    public static final String COL_EDI_SHPPR_POST_CD = "EDI_SHPPR_POST_CD";

    /** DB COLUMN :EDI_SHPPR_CTRY_CD **/
    public static final String COL_EDI_SHPPR_CTRY_CD = "EDI_SHPPR_CTRY_CD";

    /** DB COLUMN :EDI_RCPNT_NM **/
    public static final String COL_EDI_RCPNT_NM = "EDI_RCPNT_NM";

    /** DB COLUMN :EDI_RCPNT_CMPY_TXT **/
    public static final String COL_EDI_RCPNT_CMPY_TXT = "EDI_RCPNT_CMPY_TXT";

    /** DB COLUMN :EDI_RCPNT_ADDR **/
    public static final String COL_EDI_RCPNT_ADDR = "EDI_RCPNT_ADDR";

    /** DB COLUMN :EDI_RCPNT_CTY_NM **/
    public static final String COL_EDI_RCPNT_CTY_NM = "EDI_RCPNT_CTY_NM";

    /** DB COLUMN :EDI_RCPNT_ST_CD **/
    public static final String COL_EDI_RCPNT_ST_CD = "EDI_RCPNT_ST_CD";

    /** DB COLUMN :EDI_RCPNT_POST_CD **/
    public static final String COL_EDI_RCPNT_POST_CD = "EDI_RCPNT_POST_CD";

    /** DB COLUMN :EDI_RCPNT_CTRY_CD **/
    public static final String COL_EDI_RCPNT_CTRY_CD = "EDI_RCPNT_CTRY_CD";

    /** DB COLUMN :EDI_SVC_CD **/
    public static final String COL_EDI_SVC_CD = "EDI_SVC_CD";

    /** DB COLUMN :EDI_WT_UOM_TXT **/
    public static final String COL_EDI_WT_UOM_TXT = "EDI_WT_UOM_TXT";

    /** DB COLUMN :EDI_WT_PKG_TXT **/
    public static final String COL_EDI_WT_PKG_TXT = "EDI_WT_PKG_TXT";

    /** DB COLUMN :EDI_REF_TXT **/
    public static final String COL_EDI_REF_TXT = "EDI_REF_TXT";

    /** DB COLUMN :EDI_CR_REF_TXT **/
    public static final String COL_EDI_CR_REF_TXT = "EDI_CR_REF_TXT";

    /** DB COLUMN :EDI_SHPPR_REF_TXT **/
    public static final String COL_EDI_SHPPR_REF_TXT = "EDI_SHPPR_REF_TXT";

    /** DB COLUMN :EDI_SCAN_TP_TXT **/
    public static final String COL_EDI_SCAN_TP_TXT = "EDI_SCAN_TP_TXT";

    /** DB COLUMN :EDI_SCAN_CTY_TXT **/
    public static final String COL_EDI_SCAN_CTY_TXT = "EDI_SCAN_CTY_TXT";

    /** DB COLUMN :EDI_SCAN_CTY_ZIP_CD **/
    public static final String COL_EDI_SCAN_CTY_ZIP_CD = "EDI_SCAN_CTY_ZIP_CD";

    /** DB COLUMN :EDI_SCAN_ST_TXT **/
    public static final String COL_EDI_SCAN_ST_TXT = "EDI_SCAN_ST_TXT";

    /** DB COLUMN :EDI_SCAN_DT_TXT **/
    public static final String COL_EDI_SCAN_DT_TXT = "EDI_SCAN_DT_TXT";

    /** DB COLUMN :EDI_SCAN_TS **/
    public static final String COL_EDI_SCAN_TS = "EDI_SCAN_TS";

    /** DB COLUMN :EDI_SCAN_STS_TXT **/
    public static final String COL_EDI_SCAN_STS_TXT = "EDI_SCAN_STS_TXT";

    /** DB COLUMN :EDI_SCAN_ATA_CD **/
    public static final String COL_EDI_SCAN_ATA_CD = "EDI_SCAN_ATA_CD";

    /** DB COLUMN :EDI_SCAN_CTRY_CD **/
    public static final String COL_EDI_SCAN_CTRY_CD = "EDI_SCAN_CTRY_CD";

    /** DB COLUMN :EDI_SIGNA_DELY_TXT **/
    public static final String COL_EDI_SIGNA_DELY_TXT = "EDI_SIGNA_DELY_TXT";

    /** DB COLUMN :EDI_DELY_ADDR **/
    public static final String COL_EDI_DELY_ADDR = "EDI_DELY_ADDR";

    /** DB COLUMN :GLBL_CMPY_CD **/
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB COLUMN :EDI_TRX_ID **/
    public static final String COL_EDI_TRX_ID = "EDI_TRX_ID";

    /** DB COLUMN :EDI_SQ_ID **/
    public static final String COL_EDI_SQ_ID = "EDI_SQ_ID";

    /** DB COLUMN :ERR_MSG_CD **/
    public static final String COL_ERR_MSG_CD = "ERR_MSG_CD";

    /** DB COLUMN :PROC_STS_CD **/
    public static final String COL_PROC_STS_CD = "PROC_STS_CD";

    /** DB COLUMN :EDI_TP_CD **/
    public static final String COL_EDI_TP_CD = "EDI_TP_CD";

    /** DB COLUMN :EDI_GS_CTRL_CD **/
    public static final String COL_EDI_GS_CTRL_CD = "EDI_GS_CTRL_CD";

    /** DB COLUMN :EDI_ST_CTRL_CD **/
    public static final String COL_EDI_ST_CTRL_CD = "EDI_ST_CTRL_CD";

    /** DB COLUMN :EDI_LN_CTRL_CD **/
    public static final String COL_EDI_LN_CTRL_CD = "EDI_LN_CTRL_CD";

    /** DB COLUMN :EDI_PRO_NUM **/
    public static final String COL_EDI_PRO_NUM = "EDI_PRO_NUM";

    /** DB COLUMN :EDI_STS_CD **/
    public static final String COL_EDI_STS_CD = "EDI_STS_CD";

    /** DB COLUMN :EDI_STS_DT **/
    public static final String COL_EDI_STS_DT = "EDI_STS_DT";

    /** DB COLUMN :EDI_STS_TM **/
    public static final String COL_EDI_STS_TM = "EDI_STS_TM";

    /** DB COLUMN :EDI_TM_CD **/
    public static final String COL_EDI_TM_CD = "EDI_TM_CD";

    /** DB COLUMN :EDI_STS_RSN_CD **/
    public static final String COL_EDI_STS_RSN_CD = "EDI_STS_RSN_CD";

    /** DB COLUMN :EDI_STS_CTY_NM **/
    public static final String COL_EDI_STS_CTY_NM = "EDI_STS_CTY_NM";

    /** DB COLUMN :EDI_STS_ST_CD **/
    public static final String COL_EDI_STS_ST_CD = "EDI_STS_ST_CD";

    /** DB COLUMN :EDI_CNTNR_NUM **/
    public static final String COL_EDI_CNTNR_NUM = "EDI_CNTNR_NUM";

    /** DB COLUMN :EDI_WT **/
    public static final String COL_EDI_WT = "EDI_WT";

    /** DB COLUMN :EDI_WT_UNIT_CD **/
    public static final String COL_EDI_WT_UNIT_CD = "EDI_WT_UNIT_CD";

    /** DB COLUMN :EDI_LOAD_QTY **/
    public static final String COL_EDI_LOAD_QTY = "EDI_LOAD_QTY";

    /** DB COLUMN :EDI_PKG_CD **/
    public static final String COL_EDI_PKG_CD = "EDI_PKG_CD";

    /** DB COLUMN :EDI_NET_AMT **/
    public static final String COL_EDI_NET_AMT = "EDI_NET_AMT";

    /** DB COLUMN :EDI_EX_CD **/
    public static final String COL_EDI_EX_CD = "EDI_EX_CD";

    /** DB COLUMN :EDI_EX_PKG_CD **/
    public static final String COL_EDI_EX_PKG_CD = "EDI_EX_PKG_CD";

    /** DB COLUMN :EDI_EX_LOAD_QTY **/
    public static final String COL_EDI_EX_LOAD_QTY = "EDI_EX_LOAD_QTY";

    /** DB COLUMN :EDI_SGN_CD **/
    public static final String COL_EDI_SGN_CD = "EDI_SGN_CD";

    /** DB COLUMN :EDI_CTAC_TXT **/
    public static final String COL_EDI_CTAC_TXT = "EDI_CTAC_TXT";

    /** DB COLUMN :EDI_PICK_UP_DT **/
    public static final String COL_EDI_PICK_UP_DT = "EDI_PICK_UP_DT";

    /** DB COLUMN :EDI_PICK_UP_TM **/
    public static final String COL_EDI_PICK_UP_TM = "EDI_PICK_UP_TM";

    /** DB COLUMN :EDI_CENT_CD **/
    public static final String COL_EDI_CENT_CD = "EDI_CENT_CD";

    /** DB COLUMN :EDI_UCC_CD **/
    public static final String COL_EDI_UCC_CD = "EDI_UCC_CD";

    /** DB COLUMN :EDI_BILL_TO_PTY_NM **/
    public static final String COL_EDI_BILL_TO_PTY_NM = "EDI_BILL_TO_PTY_NM";

    /** DB COLUMN :EDI_BILL_TO_PTY_ADDR **/
    public static final String COL_EDI_BILL_TO_PTY_ADDR = "EDI_BILL_TO_PTY_ADDR";

    /** DB COLUMN :EDI_BILL_TO_PTY_CTY_NM **/
    public static final String COL_EDI_BILL_TO_PTY_CTY_NM = "EDI_BILL_TO_PTY_CTY_NM";

    /** DB COLUMN :EDI_BILL_TO_PTY_ST_CD **/
    public static final String COL_EDI_BILL_TO_PTY_ST_CD = "EDI_BILL_TO_PTY_ST_CD";

    /** DB COLUMN :EDI_BILL_TO_PTY_POST_CD **/
    public static final String COL_EDI_BILL_TO_PTY_POST_CD = "EDI_BILL_TO_PTY_POST_CD";

    /** DB COLUMN :EDI_CNSGN_NM **/
    public static final String COL_EDI_CNSGN_NM = "EDI_CNSGN_NM";

    /** DB COLUMN :EDI_CNSGN_ADDR **/
    public static final String COL_EDI_CNSGN_ADDR = "EDI_CNSGN_ADDR";

    /** DB COLUMN :EDI_CNSGN_CTY_NM **/
    public static final String COL_EDI_CNSGN_CTY_NM = "EDI_CNSGN_CTY_NM";

    /** DB COLUMN :EDI_CNSGN_ST_CD **/
    public static final String COL_EDI_CNSGN_ST_CD = "EDI_CNSGN_ST_CD";

    /** DB COLUMN :EDI_CNSGN_POST_CD **/
    public static final String COL_EDI_CNSGN_POST_CD = "EDI_CNSGN_POST_CD";

    /** DB COLUMN :POD_SRC_TP_CD **/
    public static final String COL_POD_SRC_TP_CD = "POD_SRC_TP_CD";

    // START 2018/01/17 S.Katsuma [QC#23056,ADD]
    /** DB COLUMN :TRX_REF_NUM **/
    public static final String COL_TRX_HDR_NUM = "TRX_HDR_NUM";

    /** DB COLUMN :TRX_REF_LINE_NUM **/
    public static final String COL_TRX_LINE_NUM = "TRX_LINE_NUM";
    // END 2018/01/17 S.Katsuma [QC#23056,ADD]
    

    /** Function Return Status (Application Exception) */
    public static final int ST_APPL_ERR_END = -1;

    /** Function Return Status (Normal End) */
    public static final int ST_NORMAL_END = 0;

    /** EDI_SCAN_TP_TXT : PROOF_OF_PICKUP */
    public static final String TP_PROOF_OF_PICKUP = "08";

    /** EDI_SCAN_TP_TXT : HOLD_AT_LOCATION */
    public static final String TP_HOLD_AT_LOCATION = "41";

    /** EDI_SCAN_TP_TXT : PROOF_OF_DELIVERY */
    public static final String TP_PROOF_OF_DELIVERY = "20";

    /** Const Value for EDI_TP_CD */
    public static final String VAL_EDI_TP_CD = "FEDEX";

    // START 2018/02/23 S.Katsuma [QC#24011,ADD]
    /** Const Value for EDI_LN_CTRL_CD */
    public static final String VAL_EDI_LN_CTRL_CD = "0001";

    /** Const Value for EDI_LN_CTRL_CD */
    public static final String VAL_EDI_TM_CD = "LT";
    // END 2018/02/23 S.Katsuma [QC#24011,ADD]

    /** Max Length Of EDI_ST_CTRL_CD */
    public static final int LG_EDI_ST_CTRL_CD = 4;

    /** Max Length Of EDI_LN_CTRL_CD */
    public static final int LG_EDI_LN_CTRL_CD = 4;

    /** Value 0 */
    public static final String VAL_ZERO = "0";

    // START 2018/01/17 S.Katsuma [QC#23056,ADD]
    /** CARR_TP_CD_FEDEX */
    public static final String CARR_TP_CD_FEDEX = "1";
    // END 2018/01/17 S.Katsuma [QC#23056,ADD]

    /** Value Blank */
    public static final String VAL_BLANK = " ";    // 2019/03/29 T.Ogura [QC#30594,ADD]

    // QC#50528 Add
    /** EDI_DUMMY_DATE */
    public static final String EDI_DUMMY_DATE = "00000000";
}
