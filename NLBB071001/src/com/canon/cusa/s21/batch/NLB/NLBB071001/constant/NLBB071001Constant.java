/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB071001.constant;

/**
 * <pre>
 * Batch Program Constant Class for Shipping Advice (EDI 945) from APEX.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS            T.Wada          Create          
 * 11/02/2017   CITS            T.Wada          UPDATE          QC#21303
 * 05/22/2019   CITS            K.Ogino         UPDATE          QC#50389
 * 02/12/2020   Fujitsu         R.Nakamura      Update          QC#55733
 * 06/26/2020   CITS            K.Ogino         Update          QC#56444
 *</pre>
 */
public class NLBB071001Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NLBB0710";

    /** Target Interface ID */
    public static final String TARGET_ID = "WMS_INBD_TRX";

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

    /** DB Table NLBI0020_01 : */
    public static final String TBL_NLBI0020_01 = "NLBI0020_01";

    /** DB Table NLBI0020_01 : */
    public static final String TBL_NLBI1220_01 = "NLBI1220_01";

    /** DB Table NLBI1220_02 : */
    public static final String TBL_NLBI1220_02 = "NLBI1220_02";

    /** DB Table NLBI1220_03 : */
    public static final String TBL_NLBI1220_03 = "NLBI1220_03";

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

    /** Column name of GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name of WH_OWNR_CD */
    public static final String COL_WH_OWNR_CD = "WH_OWNR_CD";

    /** Column name of COL_INBD_OTBD_CD */
    public static final String COL_INBD_OTBD_CD = "INBD_OTBD_CD";

    /** Column name of WMS_TASK_CD */
    public static final String COL_WMS_TASK_CD = "WMS_TASK_CD";

    /** Column name of WMS_TASK_CD_SHIP */
    public static final String COL_WMS_TASK_CD_SHIP = "WMS_TASK_CD_SHIP";

    /** Column name of WMS_TASK_CD_SHIP */
    public static final String COL_WMS_TASK_CD_SERL = "WMS_TASK_CD_SERL";

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

    /** Column name of WMS_TASK_NM */
    public static final String COL_WMS_TASK_NM_ARY = "WMS_TASK_NM_ARY";

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

    /** Column name of WH_CD */
    public static final String COL_WH_CD = "WH_CD";

    /** Column name of CARR_CD */
    public static final String COL_CARR_CD = "CARR_CD";

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

    /** Column name of WMS_SUM_PROC_QTY */
    public static final String COL_WMS_SUM_PROC_QTY = "WMS_SUM_PROC_QTY";

    /** Column name of OTBD_ORD_LINE_NUM */
    public static final String COL_OTBD_ORD_LINE_NUM = "OTBD_ORD_LINE_NUM";

    /** Column name of RTL_WH_CD */
    public static final String COL_RTL_WH_CD = "RTL_WH_CD";

    /** Column name of RTL_SWH_CD */
    public static final String COL_RTL_SWH_CD = "RTL_SWH_CD";

    /** Column name of SER_NUM */
    public static final String COL_SER_NUM = "SER_NUM";

    /** Column name of EDI_TRX_ID */
    public static final String COL_EDI_TRX_ID = "EDI_TRX_ID";

    /** Column name of INVTY_OWNR_CD */
    public static final String COL_INVTY_OWNR_CD = "INVTY_OWNR_CD";

    /** Column name of FROM_STK_STS_CD */
    public static final String COL_FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /** Column name of DATE_TIME */
    public static final String COL_DATE_TIME = "DATE_TIME";

    /** Column name of INTFC_TP_ID */
    public static final String COL_INTFC_TP_ID = "INTFC_TP_ID";

    /** Column name of WMS_ORG_HOST_ID */
    public static final String COL_WMS_ORG_HOST_ID = "WMS_ORG_HOST_ID";

    /** Column name of INTFC_PROC_STS_CD */
    public static final String COL_INTFC_PROC_STS_CD = "INTFC_PROC_STS_CD";

    /** Column name of ERR_MSG_CD */
    public static final String COL_ERR_MSG_CD = "ERR_MSG_CD";
    
    /** Max Length Of WRK_TRX_ID */
    public static final int LG_WRK_TRX_ID = 30;
    
    /** Max Length Of INTFC_TRX_ID */
    public static final int LG_INTFC_TRX_ID = 30;
    
    /** Max Length Of UNIT_ID */
    public static final int LG_UNIT_ID = 30;
    
    /** Column name of EZINTIME */
    public static final String COL_EZINTIME = "EZINTIME";

    // Add Start 2020/02/12 QC#55733
    /** Column name of SO_SLP_NUM */
    public static final String COL_SO_SLP_NUM = "SO_SLP_NUM";

    /** Column name of SER_ITEM_FLG */
    public static final String COL_SER_ITEM_FLG = "SER_ITEM_FLG";

    /** Column name of SVC_MACH_USG_STS_CD_IN_INVTY */
    public static final String COL_SVC_MACH_USG_STS_CD_IN_INVTY = "SVC_MACH_USG_STS_CD_IN_INVTY";

    /** Column name of LOC_STS_CD_DC_STOC */
    public static final String COL_LOC_STS_CD_DC_STOC = "LOC_STS_CD_DC_STOC";

    /** Column name of SVC_MACH_MSTR_STS_CD */
    public static final String COL_SVC_MACH_MSTR_STS_CD = "SVC_MACH_MSTR_STS_CD";

    /** Column name of ROW_NUM */
    public static final String COL_ROW_NUM = "ROW_NUM";
    // Add End 2020/02/12 QC#55733

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

    /** Value MS_TASK_CD(LF) */
    public static final String VAL_WMS_TASK_CD_LF = "LF";

    /** Value MS_TASK_CD(LR) */
    public static final String VAL_WMS_TASK_CD_LR = "LR";

    /** Value MS_TASK_CD(LR) */
    public static final String VAL_WMS_TASK_CD_SHIP = "SHIP";

    /** Value MS_TASK_CD(LR) */
    public static final String VAL_WMS_TASK_CD_SERL = "SERL";

    /** Value CONST_EDI_TM_CD */
    public static final String VAL_CONST_EDI_TM_CD = "LT";

    /** Column name of PROC_STS_CD(0:未処理) */
    public static final String VAL_PROC_STS_CD_UNTREATED = "0";

    /** Column name of INTFC_PROC_STS_CD(1:No Error) */
    public static final String VAL_INTFC_PROC_STS_CD_OK = "1";

    /** Column name of INTFC_PROC_STS_CD(9:Has Error) */
    public static final String VAL_INTFC_PROC_STS_CD_NG = "9";

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

    /** Input datetime format */
    public static final String VAL_DATE_TIME_FORMAT_FROM = "yyyyMMddHHmmss";

    /** Output datetime format */
    public static final String VAL_DATE_TIME_FORMAT_TO = "yyyy/MM/ddHH:mm:ss";

    /** Format of SYSDATE : yyyy/MM/dd00:00:00 */
    public static final String FMT_YYYYMMDD000000 = "yyyy/MM/dd00:00:00";

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

    /** Max Length Of CARR_CD */
    public static final int LG_CARR_CD = 10;

    /** Max Length Of BOL_NUM2 */
    public static final int LG_BOL_NUM_2 = 30;

    /** Max Length Of OTBD_ORD_LINE_NUM */
    public static final int LG_OTBD_ORD_LINE_NUM = 3;

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

    /** start position for WMS_SHIP_DT(for POD) */
    public static final int LG_WMS_SHIP_DT_FROM_FOR_POD = 0;

    /** end position for WMS_SHIP_DT(for POD) */
    public static final int LG_WMS_SHIP_DT_TO_FOR_POD = 8;

    /** start position for WMS_SHIP_TM(for POD) */
    public static final int LG_WMS_SHIP_TM_FROM_FOR_POD = 8;

    /** end position for WMS_SHIP_TM(for POD) */
    public static final int LG_WMS_SHIP_TM_TO_FOR_POD = 14;

    /** start position for WMS_SHIP_DT */
    public static final int LG_WMS_SHIP_DT_FROM = 0;

    /** end position for WMS_SHIP_DT */
    public static final int LG_WMS_SHIP_DT_TO = 10;

    /** start position for WMS_SHIP_TM */
    public static final int LG_WMS_SHIP_TM_FROM = 11;

    /** end position for WMS_SHIP_TM */
    public static final int LG_WMS_SHIP_TM_TO = 19;

    /** start position for WMS_TRX_DT(for POD) */
    public static final int LG_WMS_TRX_DT_FROM_FOR_POD = 0;

    /** end position for WMS_TRX_DT(for POD) */
    public static final int LG_WMS_TRX_DT_TO_FOR_POD = 8;

    /** start position for WMS_TRX_TM(for POD) */
    public static final int LG_WMS_TRX_TM_FROM_FOR_POD = 8;

    /** end position for WMS_TRX_TM(for POD) */
    public static final int LG_WMS_TRX_TM_TO_FOR_POD = 14;

    /** start position for WMS_TRX_DT */
    public static final int LG_WMS_TRX_DT_FROM = 0;

    /** end position for WMS_TRX_DT */
    public static final int LG_WMS_TRX_DT_TO = 10;

    /** start position for WMS_TRX_TM */
    public static final int LG_WMS_TRX_TM_FROM = 11;

    /** end position for WMS_TRX_TM */
    public static final int LG_WMS_TRX_TM_TO = 19;

    /** String Length 20 */
    public static final int STRING_LENGTH_20 = 20;

    /***/
    public static final String VAL_EDI_TP_CD = "APEX";
    

    /** Column name of RWS_NUM */
    public static final String COL_RWS_NUM = "RWS_NUM";

    /** Column name of INTFC_ERR_MSG_CD */
    public static final String COL_INTFC_ERR_MSG_CD = "INTFC_ERR_MSG_CD";

    /** Column name of RWS_REF_NUM */
    public static final String COL_RWS_REF_NUM = "RWS_REF_NUM";
    
    /** Column name of COL_TASK_PIC */
    public static final String COL_TASK_PIC = "TASK_PIC";

    /** Column name of RWS_REF_NUM */
    public static final String COL_TASK_LR = "TASK_LR";

    /** Value WH_OWNR_CD(APX) */
    public static final String VAL_WH_OWNR_CD_IS_APX = "APX";
    /** Value WMS_TASK_CD(RCVD) */
    public static final String VAL_WMS_TASK_CD_RCVD = "RCVD";
    /** Column name of INVTY_STK_STS_CD */
    public static final String COL_INVTY_STK_STS_CD = "INVTY_STK_STS_CD";
    /** Column name of SER_CHK_CNT */
    public static final String COL_SER_CHK_CNT = "SER_CHK_CNT";
    /** Column name of SER_CHK_ROWNUM */
    public static final String COL_SER_CHK_ROWNUM = "SER_CHK_ROWNUM";
    
    /** Column name of SHPG_SVC_LVL_CD */
    public static final String COL_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** Add QC#50389. VAR_CHAR_CONST_KEY */
    public static final String NLBB0710_DEF_WMS_CARR_CD = "NLBB0710_DEF_WMS_CARR_CD";

    // QC#56444 Add
    /** Column name of SO_NUM */
    public static final String COL_SO_NUM = "SO_NUM";
    /** Column name of WMS_MDSE_CD */
    public static final String COL_MDSE_CD = "MDSE_CD";
    /** Column name of PICK_SVC_CONFIG_MSTR_PK */
    public static final String COL_PICK_SVC_CONFIG_MSTR_PK = "PICK_SVC_CONFIG_MSTR_PK";
    // QC#56444 End
}
