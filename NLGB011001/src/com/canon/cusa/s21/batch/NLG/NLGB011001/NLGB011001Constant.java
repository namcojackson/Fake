/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB011001;

/**
 * <pre>
 * Batch Program Class for Translate Put Away and RWS from WMS
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/19/2013   CSAI            K.Kondo         Create          MW Replace Initial
 * 02/01/2017   CITS            Y.Fujii         Update          QC#17214
 * 10/12/2017   CITS            T.Tokutomi      Update          QC#21731
 * 08/10/2019   CITS            M.Naito         Update          QC#52572
 * 02/22/2021   CITS            J.Evangelista   Update          QC#58380
 * 09/16/2021   CITS            R.Azucena       Update          QC#58435
 *</pre>
 */
public interface NLGB011001Constant {

    // *********************************************************
    // Constants: Fixed length Data value
    // *********************************************************
    /** Interface Record Type : PO Receipt */
    public static final String INTFC_REC_TP_PO_RECEIPT = "10";

    /** Interface Record Type : PO Close */
    public static final String INTFC_REC_TP_PO_CLOSE = "11";

    /** Interface Record Type : PO Cancel */
    public static final String INTFC_REC_TP_PO_CANCEL = "12";

    /** Fixed length Data Detail Type */
    public static final String INTFC_DTL_TP = "1";

    /** Fixed length Data Company Code */
    public static final String INTFC_CMPY_CD = "01";

    /** padding Value : WMS_ORD_QTY */
    public static final String PAD_VAL_WMS_ORD_QTY_IF = "0";

    /** padding Value : INBD_ORD_LINE_NUM */
    public static final String PAD_VAL_INBD_ORD_LINE_NUM_IF = "0";

    /** Prefix Value : S80_STK_STS_CD_IF */
    public static final String PFX_S80_STK_STS_CD_IF = "S";

    /** Fixed length Data length : WMS_TOT_WT */
    public static final String WMS_TOT_WT_IF = "0000000.0000";

    /** Fixed length Data length : WMS_BAT_ID */
    public static final String WMS_FRT_CHRG_AMT_IF = "00000000000.00";

    /** Fixed length Data length : LG_ORD_PROC_STS */
    public static final String PDLT_ORD_PROC_STS_IF = "O";

// START 2019/08/10 M.Naito [QC#52572,MOD]
    /** MW Replace Group : DBS */
    public static final String WH_GP_CD_2_DBS_WH = "2";
// END 2019/08/10 M.Naito [QC#52572,MOD]

    /** MW Replace Group : 3PL */
    public static final String WH_GP_CD_3_THIRD_PARTY_WH = "3";

    /** Source Type : WMS */
    public static final String SRC_TP_CD_WMS = "W";

    // *********************************************************
    // Constants: Fixed length Data length
    // *********************************************************
    /** Fixed length Data length : WH_CD */
    public static final int LG_INTFC_WH_CD = 4;

    /** Fixed length Data length : WMS_REC_ID */
    public static final int LG_INTFC_WMS_REC_ID = 7;

    /** Fixed length Data length : INTFC_REC_TP */
    public static final int LG_INTFC_REC_TP = 2;

    /** Fixed length Data length : INTFC_DTL_TP */
    public static final int LG_INTFC_DTL_TP = 1;

    /** Fixed length Data length : INTFC_FILL */
    public static final int LG_INTFC_FILL = 1;

    /** Fixed length Data length : INTFC_CMPY_CD */
    public static final int LG_INTFC_CMPY_CD = 2;

    /** Fixed length Data length : INBD_ORD_NUM */
    public static final int LG_INTFC_INBD_ORD_NUM = 15;

    /** Fixed length Data length : INBD_ORD_TP_CD */
    public static final int LG_INTFC_INBD_ORD_TP_CD = 1;

    /** Fixed length Data length : WMS_TRX_DT_TM_TS */
    public static final int LG_INTFC_WMS_TRX_DT_TM_TS = 18;

    /** Fixed length Data length : INBD_ORD_LINE_NUM */
    public static final int LG_INTFC_INBD_ORD_LINE_NUM = 5;

    /** Fixed length Data length : WMS_MDSE_CD */
    public static final int LG_INTFC_WMS_MDSE_CD = 25;

    /** Fixed length Data length : WMS_STK_STS_CD */
    public static final int LG_INTFC_WMS_STK_STS_CD = 2;

    /** Fixed length Data length : WMS_ORD_QTY */
    public static final int LG_INTFC_WMS_ORD_QTY = 9;

    /** Fixed length Data length : minus WMS_ORD_QTY */
    public static final int LG_INTFC_MIN_WMS_ORD_QTY = 8;

    /** Fixed length Data length : WMS_BAT_ID */
    public static final int LG_INTFC_WMS_BAT_ID = 10;

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

    // *********************************************************
    // Constants: Message ID
    // *********************************************************
    /**
     * Message ID: NLGM0045E The record cannot be registered.
     * Registration Table Name: [@], Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    public static final String NLGM0045E = "NLGM0045E";

    /**
     * Message ID: NLGM0046E The record cannot be updated. Table Name:
     * [@], Key Field Name: [@], Key Value: [@]
     */
    public static final String NLGM0046E = "NLGM0046E";

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

    /**
     * Message ID: Order ID is Closed or canceled already. Order ID:
     * [@], Table Name: [@], Date Field Name: [@], Date registered:
     * [@].
     */
    public static final String NLGM0053E = "NLGM0053E";

    /**
     * Message ID: Since the quantity of PO Receipt (RCVD) is
     * insufficient, POClose processing is stopped. Order ID: [@],
     * Table Name: [@]
     */
    public static final String NLGM0055I = "NLGM0055I";

    /**
     * Message ID: Since it passed over the definite period of time
     * while PO Receipt (RCVD) had been deficiency in quantity, it
     * interfaces with S21. Order ID: [@], Table Name: [@]
     */
    public static final String NLGM0056I = "NLGM0056I";

    /**
     * "@" is not exist RWS Reference Number. [WMS_INBD_TRX_PK : @]
     */
    public static final String NLGM0080E = "NLGM0080E";

    /**
     * Message ID: The combination of Merchandise and Serial# is
     * duplicated.
     */
    public static final String NLGM0082E = "NLGM0082E";

    /**
     * The corresponding data does not exist. Table Name : [@], Key
     * Field Name: [@], Key Value: [@]
     */
    public static final String NLAM1001E = "NLAM1001E";

    /**
     * The length is invalid. Interface ID: [@], TRX_ID: [@], UNIT ID:
     * [@], Partition Length: [@] - [@]
     */
    public static final String NLAM1215E = "NLAM1215E";

    /**
     * The data type is invalid. I/F ID : [@] Transaction ID : [@]
     * Segment ID: [@] Unit ID : [@] Sequence No : [@]
     */
    public static final String NLAM1010E = "NLAM1010E";

    /**
     * 0,A value is not set in the required field. I/F ID : [@]
     * Transaction ID : [@] Segment ID: [@] Unit ID : [@] Sequence No
     * : [@]
     */
    public static final String NLAM1009E = "NLAM1009E";

    /**
     * There are no codes in the Code Master. Table Name: [@], Field
     * Name: [@], Search Value: [@]
     */
    public static final String NLAM1013E = "NLAM1013E";

    /**
     * The entered value for "@" is incorrect. Transaction ID : [@],
     * Segment ID : [@], Unit ID : [@], Sequence No : [@]
     */
    public static final String NLAM1235E = "NLAM1235E";

    /** Since physical inventory is in progress, cannot be processed. */
    public static final String NLBM1347E = "NLBM1347E";

    /** Already stocked in.[@] */
    public static final String NLZM1051E = "NLZM1051E";

    /** Actual stocked-in quantity exceeded the instruction.[@] */
    public static final String NLZM1052E = "NLZM1052E";

    /**
     * The Table Update process failed. The data does not exist. TBLE
     * ID : [@], Field Name: [@], Key Information: [@]
     */
    public static final String NLAM1134E = "NLAM1134E";

    /**
     * PO Complete information cannot be loaded since an error
     * occurred to Put Away Confirmation while loading data. PO# : [@]
     */
    public static final String NLAM1106E = "NLAM1106E";

    // QC#21731 Add
    /**
     * Shipping order of kitting was not completed. Transaction ID :
     * [@], Segment ID : [@], Unit ID : [@], Sequence No : [@],
     * Shipping Order No : [@].
     */
    public static final String NLGM0084E = "NLGM0084E";

    // START 2021/02/22 J.Evangelista [QC#58380,ADD]
    /**
     * Since it passed over the definite period of time and the quantity of
     * PO Receipt (RCVD) is insufficient, PO Close (PCLS) processing is stopped.
     * Order ID: [@], Table Name: [@]
     */
    public static final String NLGM0094E = "NLGM0094E";
    // END 2021/02/22 J.Evangelista [QC#58380,ADD]

    // *********************************************************
    // Constants: Unique
    // *********************************************************
    /** Business ID */
    public static final String BUSINESS_ID = "NLGB0110";

    /** Prameter Name: GLBL_CMPY_CD */
    public static final String PRAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Prameter Name: INTERFACE_ID */
    public static final String PRAM_NM_INTERFACE_ID = "Interface Id";

    /** Prameter Name: WH_GRP_CD */
    public static final String PRAM_NM_WH_GRP_CD = "Warehouse Group Code";

    /** Prameter Name: PO_PROCESS_CD */
    public static final String PRAM_NM_PO_PROCESS_CD = "PO Process Code";

    /** SSM set Key Name: KEY_WMS_CANC_DT_TM_TS */
    public static final String KEY_WMS_CANC_DT_TM_TS = "wmsCancDtTmTs";

    /** SSM set Key Name: KEY_WMS_CLO_DT_TM_TS */
    public static final String KEY_WMS_CLO_DT_TM_TS = "wmsCloDtTmTs";

    /** SSM set Key Name: KEY_SHIP_DT_TM_TS */
    public static final String KEY_SHIP_DT_TM_TS = "shipDtTmTs";

    /** PO Process Key: PO Receipt */
    public static final String PO_PROC_PO_RECEIPT = "1";

    /** PO Process Key: PO Close */
    public static final String PO_PROC_CLOSE = "2";

    /** PO Process Key: PO Cancel */
    public static final String PO_PROC_CANCEL = "3";

    /** Table name of WMS_INBD_TRX */
    public static final String TBL_WMS_INBD_TRX = "WMS_INBD_TRX";

    /** Column name of WMS_INBD_TRX_PK */
    public static final String COL_WMS_INBD_TRX_PK = "WMS_INBD_TRX_PK";

    /** Column name of GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name of WH_CD */
    public static final String COL_WH_CD = "WH_CD";

    /** Column name of WMS_SQ_NUM */
    public static final String COL_WMS_SQ_NUM = "WMS_SQ_NUM";

    /** Column name of WMS_CANC_DT_TM_TS */
    public static final String COL_WMS_CANC_DT_TM_TS = "WMS_CANC_DT_TM_TS";

    /** Column name of COL_SHIP_DT_TM_TS */
    public static final String COL_SHIP_DT_TM_TS = "SHIP_DT_TM_TS";

    /** Column name of COL_WMS_CLO_DT_TM_TS */
    public static final String COL_WMS_CLO_DT_TM_TS = "WMS_CLO_DT_TM_TS";

    /**
     * VAR_CHAR_CONST Key Name: Verification interval of WMS PO
     * Close(minute)
     */
    public static final String VLD_INTVL_PO_CLO_QTY_MIN = "VLD_INTVL_PO_CLO_QTY_MIN";

    /** Default WMS PO Close Check Interval */
    public static final long DEF_PO_CLO_MIN_INTVL = 30;

    /** Minute Calculated Multiplication value */
    public static final long MIN_CALC_MULT_VAL = 60000;

    /** Current Date Time Format */
    public static final String CUR_DT_TM_FMT = "yyyyMMddHHmmss";

    /** SQL Conditions : length WMS_STK_STS_CD */
    public static final int LG_WMS_STK_STS_CD = 1;

    /** SQL Conditions : The lower limit of the quantity */
    public static final int LOW_LIMIT_ORD_QTY = 0;

    /** Substring From length for determine the WMS_REC_ID */
    public static final int LG_CUT_WMS_REC_ID_FROM = 0;

    /** Substring From length for determine the EZINTIME */
    public static final int LG_CUT_FROM_EZINTIME = 0;

    /** Substring To length for determine the EZINTIME */
    public static final int LG_CUT_TO_EZINTIME = 14;

    /** Value prefix of WMS_REC_ID for IF */
    public static final String VAR_PFX_WMS_REC_ID = "0000000";

    // *********************************************************
    // Constants: MAIL TEMPLATE
    // *********************************************************
    /** Mail template for Serial error */
    public static String MAIL_TEMPLATE_ID_SERIAL_ERR = "NLGB0110M001";

    /** Mail Group Id Key: From */
    public static String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail Group ID (Retail WH) */
    public static String MAIL_GROUP_ID_RWH = "NLGB011001";

    /** Mail Group ID (WMS WH) */
    public static String MAIL_GROUP_ID_WMS_WH = "NLGB011002";

    /** Mail Group ID (Default) */
    public static String MAIL_GROUP_ID_DEF = "NLGB011003";

    /** Mail Key 1 To */
    public static String MAIL_KEY_TO = "To";

    /** Message template replace from : WH_CD */
    public static String MT_WH_CD = "WH_CD";

    /** Message template replace from : DTL_MSG */
    public static String MT_DTL_MSG = "DTL_MSG";

    /**
     * Could not get MailGroupAddress. MAIL_GROUP_ID_TO : [@]
     * MAIL_KEY1 : [@]
     */
    public static String NPAM0063E = "NPAM0063E";

    /** The mail template cannot be acquired. <Template ID: [@]> */
    public static String NPAM0064E = "NPAM0064E";

    /** line feed code */
    public static String LINE_FEED_CODE = "\r\n";

    /** Email detail header */
    public static String DTL_MSG_HDR = "WH    MDSE              SERIAL#                         INBOUND ORDER#                  ERROR MESSAGE" + LINE_FEED_CODE
            + "---------------------------------------------------------------------------------------------------------------------------------------------------------------";

    /** Blank 1 */
    public static String BLANK_1 = " ";

    /** Blank 2 */
    public static String BLANK_2 = "  ";

    /** Blank 100 */
    public static String BLANK_100 = "                                                                                                    ";

    /** Width of email contents */
    public static int WIDTH_WH_CD = 4;

    /** Width of email contents */
    public static int WIDTH_MDSE_CD = 16;

    /** Width of email contents */
    public static int WIDTH_SERIAL = 30;

    /** Width of email contents */
    public static int WIDTH_INBD_ORD_NUM = 30;

    // START 2021/09/16 R.Azucena[QC#58435, ADD]
    /** Serial # is not set. */
    public static String NLZM2091E = "NLZM2091E";

    /** Serial# is not set. Please process it manually. */
    public static String NLGM0099E = "NLGM0099E";
    // END 2021/09/16 R.Azucena[QC#58435, ADD]
}
