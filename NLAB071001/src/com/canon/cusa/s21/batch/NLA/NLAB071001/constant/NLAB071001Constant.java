/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB071001.constant;

/**
 * <pre>
 * Batch Program Constant Class for Receiving Advice (944) from Apex.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/02/2016   CITS            T.Wada          Create
 * 02/22/2017   CITS            T.Kikuhara      Update          QC#17545
 * 09/29/2017   CITS            T.Wada          Update          QC#21283
 *</pre>
 */
public class NLAB071001Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NLAB0710";

    /** Interface ID NLBI0020 */
    public static final String TARGET_ID = "WMS_INBD_TRX";

    /** Message ID: Interface ID has not been set. */
    public static final String NLBI0020 = "NLBI0020";

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

    /** DB Table NLAI2230_01 : */
    public static final String TBL_NLAI2230_01 = "NLAI2230_01";

    /** DB Table NLAI2230_02 : */
    public static final String TBL_NLAI2230_02 = "NLAI2230_02";

    /** DB Table NLAI2230_03 : */
    public static final String TBL_NLAI2230_03 = "NLAI2230_03";

    /** Debug level for Debug */
    public static final int CST_DEBUG_MSG_LVL = 1;

    /** Prameter Name: GLBL_CMPY_CD */
    public static final String PRAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Prameter Name: INTERFACE_ID */
    public static final String PRAM_NM_INTERFACE_ID = "Interface Id";

    /** Date and time formats */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmss";

    /** Column name of GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name of WMS_TASK_CD */
    public static final String COL_WMS_TASK_CD = "WMS_TASK_CD";

    /** Column name of WMS_REC_ID */
    public static final String COL_WMS_REC_ID = "WMS_REC_ID";

    /** Column name of TRANSACTION_ID */
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /** Column name of INTERFACE_ID */
    public static final String COL_INTERFACE_ID = "INTERFACE_ID";

    /** Column name of SEGMENT_ID */
    public static final String COL_SEGMENT_ID = "SEGMENT_ID";

    /** Column name of UNIT_ID */
    public static final String COL_UNIT_ID = "UNIT_ID";

    /** Column name of SEQ_NUMBER */
    public static final String COL_SEQ_NUMBER = "SEQ_NUMBER";

    /** Column name of BOL_NUM */
    public static final String COL_BOL_NUM = "BOL_NUM";

    /** Column name of WMS_TASK_NM */
    public static final String COL_WMS_TASK_NM = "WMS_TASK_NM";

    /** Column name of OTBD_ORD_NUM */
    public static final String COL_OTBD_ORD_NUM = "OTBD_ORD_NUM";

    /** Column name of SHPG_PNT_CD */
    public static final String COL_SHPG_PNT_CD = "SHPG_PNT_CD";

    /** Column name of RCV_PNT_CD */
    public static final String COL_RCV_PNT_CD = "RCV_PNT_CD";

    /** Column name of WMS_TRX_DT_TM_TS */
    public static final String COL_WMS_TRX_DT_TM_TS = "WMS_TRX_DT_TM_TS";

    /** Column name of S80_ORD_TP_CD */
    public static final String COL_S80_ORD_TP_CD = "S80_ORD_TP_CD";

    /** Column name of INBD_OTBD_CD */
    public static final String COL_INBD_OTBD_CD = "INBD_OTBD_CD";

    /** Column name of WH_CD */
    public static final String COL_WH_CD = "WH_CD";

    /** Column name of WMS_MDSE_CD */
    public static final String COL_WMS_MDSE_CD = "WMS_MDSE_CD";

    /** Column name of TPL_LOC_TXT */
    public static final String COL_TPL_LOC_TXT = "TPL_LOC_TXT";

    /** Column name of TPL_SWH_CD */
    public static final String COL_TPL_SWH_CD = "TPL_SWH_CD";

    /** Column name of WMS_REF_NM */
    public static final String COL_WMS_REF_NM = "WMS_REF_NM";

    /** Column name of WMS_UOM_CD */
    public static final String COL_WMS_UOM_CD = "WMS_UOM_CD";

    /** Column name of WMS_PROC_QTY */
    public static final String COL_WMS_PROC_QTY = "WMS_PROC_QTY";

    /** Column name of OTBD_ORD_LINE_NUM */
    public static final String COL_OTBD_ORD_LINE_NUM = "OTBD_ORD_LINE_NUM";

    /** Column name of RTL_WH_CD */
    public static final String COL_RTL_WH_CD = "RTL_WH_CD";

    /** Column name of RTL_SWH_CD */
    public static final String COL_RTL_SWH_CD = "RTL_SWH_CD";

    /** Column name of SER_NUM */
    public static final String COL_SER_NUM = "SER_NUM";

    /** Column name of RWS_NUM */
    public static final String COL_RWS_NUM = "RWS_NUM";

    /** Column name of INVTY_OWNR_CD */
    public static final String COL_INVTY_OWNR_CD = "INVTY_OWNR_CD";

    /** Column name of WMS_ORG_HOST_ID */
    public static final String COL_WMS_ORG_HOST_ID = "WMS_ORG_HOST_ID";

    /** Column name of INTFC_TP_ID */
    public static final String COL_INTFC_TP_ID = "INTFC_TP_ID";

    /** Column name of WH_OWNR_CD */
    public static final String COL_WH_OWNR_CD = "WH_OWNR_CD";

    /** Column name of INVTY_STK_STS_CD */
    public static final String COL_INVTY_STK_STS_CD = "INVTY_STK_STS_CD";

    /** Column name of INTFC_PROC_STS_CD */
    public static final String COL_INTFC_PROC_STS_CD = "INTFC_PROC_STS_CD";

    /** Column name of INTFC_ERR_MSG_CD */
    public static final String COL_INTFC_ERR_MSG_CD = "INTFC_ERR_MSG_CD";

    /** Column name of EZINTIME */
    public static final String COL_EZINTIME = "EZINTIME";

    /** Column name of SER_CHK_CNT */
    public static final String COL_SER_CHK_CNT = "SER_CHK_CNT";

    /** Column name of SER_CHK_ROWNUM */
    public static final String COL_SER_CHK_ROWNUM = "SER_CHK_ROWNUM";

    /** Column name of DATE_TIME */
    public static final String COL_DATE_TIME = "DATE_TIME";

    /** Column name of RWS_REF_NUM */
    public static final String COL_RWS_REF_NUM = "RWS_REF_NUM";


    /** Value Blank */
    public static final String VAL_BLANK = " ";

    /** Value 0 */
    public static final String VAL_ZERO = "0";

    /** Value INBD_OTBD_CD(OTBD) */
    public static final String VAL_INBD_OTBD_CD_IS_OTBD = "0";

    /** Value INBD_OTBD_CD(INBD) */
    public static final String VAL_INBD_OTBD_CD_IS_INBD = "1";

    /** Value WMS_TASK_NM(ORI) */
    public static final String VAL_WMS_TASK_NM_ORI = "ORI";

    /** Value WMS_TASK_NM(PIC) */
    public static final String VAL_WMS_TASK_NM_PIC = "PIC";

    /** Value WMS_TASK_NM(PGI) */
    public static final String VAL_WMS_TASK_NM_PGI = "PGI";

    /** Value WMS_TASK_CD(LF) */
    public static final String VAL_WMS_TASK_CD_LF = "LF";

    /** Value WMS_TASK_CD(LR) */
    public static final String VAL_WMS_TASK_CD_LR = "LR";

    /** Value WMS_TASK_CD(RCVD) */
    public static final String VAL_WMS_TASK_CD_RCVD = "RCVD";

    /** Value CONST_EDI_TM_CD */
    public static final String VAL_CONST_EDI_TM_CD = "LT";

    /** Value WH_OWNR_CD(APX) */
    public static final String VAL_WH_OWNR_CD_IS_APX = "APX";

    /** Column name of INTFC_PROC_STS_CD(1:No Error) */
    public static final String VAL_INTFC_PROC_STS_CD_OK = "1";

    /** Column name of INTFC_PROC_STS_CD(9:Has Error) */
    public static final String VAL_INTFC_PROC_STS_CD_NG = "9";

    /** Column name of PROC_STS_CD(0:未処理) */
    public static final String VAL_PROC_STS_CD_UNTREATED = "0";

    /** Value for Look Up From VarcharConst */
    public static final String VAR_CHAR_CONCT_NM_TG_ORDER = "NLBB0700_TARGET_ORDER";

    /** Value INBD_OTBD_CD(Outbound) */
    public static final String VAR_INBD_OTBD_CD_OTBD = "2";

    /** Mail Temp ID */
    static final String MAIL_TEMP_ID = "NLBB0710M001";

    /** Mail Group Key: From User */
    static final String MAIL_GROUP_KEY_FROM = "FROM";

    /** Mail Group ID: From User */
    static final String MAIL_GROUP_ID_FROM = "FROM0002";

    /** Mail Group Key: To User */
    static final String MAIL_GROUP_KEY_TO = "TO";

    /** Mail Group ID: To User */
    static final String MAIL_GROUP_ID_TO = "NLBB0710";

    /** Mail item key: addrToList */
    static final String MAIL_ITEM_ADDR_TO_LIST = "toAddrList";

    /** Mail item key: msgTxt */
    static final String MAIL_ITEM_MSG_TXT = "msgTxt";

    /** Error Mail Fixed Text */
    // static final String MAIL_MSG_TXT =
    // "PO Order#   PO Order Detail Line#  Message Code  Message";
    /** Filler to format Error Mail Text */
    static final String MAIL_MSG_BLANK = " ";

    /** Mail String Field : ERR_MSG */
    static final String MAIL_FIELD_ERR_MSG = "ERR_MSG";

    /** Mail String Field : ERR_MSG */
    static final String MAIL_FIELD_TIMESTAMP = "TIMESTAMP";

    /** Error Mail Text item length : PO Order# */
    static final int MAIL_MSG_LG_PO_ORDER_NUM = 12;

    /** Error Mail Text item length : PO Order Detail Line# */
    static final int MAIL_MSG_LG_PO_ORDER_DTL_LINE_NUM = 23;

    /** Error Mail Text item length : Message Code */
    static final int MAIL_MSG_LG_MSG_CD = 14;

    /** Mail Language */
    public static final String ML_LANG = "en";

    /** Mail Language Code */
    public static final String ML_LANG_CD = "ISO-8859-1";

    /** Mail Line Separator */
    public static final String LINE_SEPT = System.getProperty("line.separator");

    /** Mail Time Format */
    public static final String TIME_FORMAT = "yyyyMMddHHmmssSSS";

    /** TimeStamp format : For Error Mail */
    static final String TIME_FORMAT_FOR_MAIL = "yyyy/MM/dd HH:mm:ss.SSS";

    /** Check Error Information Attribute : MSG */
    public static final String ERR_ITEM_MSG = "MSG";

    /** Check Error Information Attribute : MSG_CD */
    public static final String ERR_ITEM_MSG_CD = "MSG_CD";

    /** Format of SYSDATE : yyyyMMddHHmmss */
    public static final String VAL_DATETIME_FORMAT_FROM = "yyyyMMdd";

    /** Format of SYSDATE : yyyyMMddHHmmss */
    public static final String VAL_DATETIME_FORMAT_TO = "yyyy/MM/ddHH:mm:ss";

    /** Max Length Of EDI_TP_CD */
    public static final int LG_EDI_TP_CD = 10;

    /** Max Length Of WH_CD */
    public static final int LG_WH_CD = 4;

    /** Max Length Of SEQ_NUMBER */
    public static final int LG_SEQ_NUMBER = 7;

    /** Max Length Of OTBD_ORD_NUM */
    public static final int LG_OTBD_ORD_NUM = 15;

    /** Max Length Of S80_ORD_TP_CD */
    public static final int LG_S80_ORD_TP_CD = 1;

    /** Max Length Of RTL_WH_CD */
    public static final int LG_RTL_WH_CD = 4;

    /** Max Length Of RTL_SWH_CD */
    public static final int LG_RTL_SWH_CD = 4;

    /** Max Length Of BOL_NUM */
    public static final int LG_BOL_NUM = 15;

    /** Max Length Of BOL_NUM2 */
    public static final int LG_BOL_NUM_2 = 30;

    /** Max Length Of OTBD_ORD_LINE_NUM */
    public static final int LG_OTBD_ORD_LINE_NUM = 5;

    /** Max Length Of WMS_MDSE_CD */
    public static final int LG_WMS_MDSE_CD = 25;

    /** Max Length Of WMS_PROC_QTY */
    public static final int LG_WMS_PROC_QTY = 9;

    /** Max Length Of EDI_LN_CTRL_CD */
    public static final int LG_EDI_LN_CTRL_CD = 4;

    /** Max Length Of WMS_CARR_CD */
    public static final int LG_WMS_CARR_CD = 10;

    /** Max Length Of SER_NUM */
    public static final int LG_SER_NUM = 20;

    /** Max Length Of EDI_GS_CTL_CD */
    public static final int LG_EDI_GS_CTL_CD = 10;

    /** Max Length Of EDI_ST_CTRL_CD */
    public static final int LG_EDI_ST_CTRL_CD = 4;

    /** start position for WMS_TRX_DT */
    public static final int LG_WMS_TRX_DT_FROM = 0;

    /** end position for WMS_TRX_DT */
    public static final int LG_WMS_TRX_DT_TO = 10;

    /** start position for WMS_TRX_TM */
    public static final int LG_WMS_TRX_TM_FROM = 11;

    /** end position for WMS_TRX_TM */
    public static final int LG_WMS_TRX_TM_TO = 19;

    // QC#17545 START
    /** Max Length Of WRK_TRX_ID */
    public static final int LG_WRK_TRX_ID = 30;

    /** Max Length Of SQ_ID */
    public static final int LG_SQ_ID = 10;

    /** Max Length Of SER_LINE_NUM */
    public static final int LG_SER_LINE_NUM = 3;

    /** Max Length Of RWS_DTL_LINE_NUM */
    public static final int LG_RWS_DTL_LINE_NUM = 3;
    // QC#17545 END
    
    /** Max Length Of INTFC_TRX_ID */
    public static final int LG_INTFC_TRX_ID = 30;
    
    /** Max Length Of UNIT_ID */
    public static final int LG_UNIT_ID = 30;

}
