/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB081001.constant;

/**
 * <pre>
 * Batch Program Constant Class for Shipping Advice (EDI 945) from APEX.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS            T.Wada          Create          
 * 02/22/2017   CITS            T.Kikuhara      Update          QC#17545
 * 09/20/2017   CITS            T.Wada          UPDATE          QC#21283
 * 12/19/2017   CITS            T.Wada          UPDATE          QC#23020
 * 02/18/2017   Hitachi         H.Umeda         UPDATE          QC#30410
 * 05/22/2019   CITS            K.Ogino         UPDATE          QC#50389
 * 06/05/2019   CITS            K.Ogino         Update          QC#50666
 * 08/26/2019   CITS            K.Ogino         Update          QC#52584
 * 09/03/2019   CITS            K.Ogino         Update          QC#52035
 * 12/04/2019   Futsu           R.Nakamura      Update          QC#54887
 * 06/26/2020   CITS            K.Ogino         Update          QC#56444
 * 09/28/2021   CITS            J.Evangelista   Update          QC#58445
 *</pre>
 */
public class NLBB081001Constant {

    /** Business ID */
    // START 2019/02/18 [QC#30410, MOD]
    // public static final String BUSINESS_ID = "NLBB0710";
    public static final String BUSINESS_ID = "NLBB0810";
    // END   2019/02/18 [QC#30410, MOD]

    /** Target Interface ID for SO */
    public static final String SO_TARGET_ID = "NLBI0020";

    /** Target Interface ID for RWS */
    public static final String RWS_TARGET_ID = "NLAI0310";

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

    // START 2021/09/28 J.Evangelista [QC#58445,ADD]
    /**
     * Message ID: NLBM1375E
     * Interface data does not match any lines from shipping order.
     * Please perform ship confirmation manually.
     */
    public static final String NLBM1375E = "NLBM1375E";
    // END 2021/09/28 J.Evangelista [QC#58445,ADD]

    /** It failed to register an email. */
    public static final String NLEM0004E = "NLEM0004E";

    /** Error Code Validation Check Error */
    public static final int ERR_CD_VAL_CHECK = -1;

    /** DB Table NLBI1120_01 : */
    //public static final String TBL_NLBI1120_01 = "NLBI1120_01";

    /** DB Table NLBI1120_02 : */
    public static final String TBL_NLBI1120_02 = "NLBI1120_02";

    /** DB Table NLBI1120_03 : */
    public static final String TBL_NLBI1120_03 = "NLBI1120_03";

    /** DB Table NLBI0020_01 : */
    public static final String TBL_NLBI0020_01 = "NLBI0020_01";

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

    /** Column name of EZINTIME */
    public static final String COL_EZINTIME = "EZINTIME";

    /** Column name of WMS_TASK_CD */
    public static final String COL_WMS_TASK_CD = "WMS_TASK_CD";

    /** Column name of WMS_REC_ID */
    public static final String COL_WMS_REC_ID = "WMS_REC_ID";

    /** Column name of INTERFACE_ID */
    public static final String COL_INTERFACE_ID = "INTERFACE_ID";

    /** Column name of SEGMENT_ID */
    public static final String COL_SEGMENT_ID = "SEGMENT_ID";

    /** Column name of TRANSACTION_ID */
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /** Column name of TRANSACTION_ID_CHAR */
    public static final String COL_TRANSACTION_ID_CHAR = "TRANSACTION_ID_CHAR";

    /** Column name of UNIT_ID */
    public static final String COL_UNIT_ID = "UNIT_ID";

    /** Column name of UNIT_ID_CHAR */
    public static final String COL_UNIT_ID_CHAR = "UNIT_ID_CHAR";

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

    /** Column name of WMS_TRX_DT_TM_TS */
    public static final String COL_WMS_SHIP_DT_TM_TS = "WMS_SHIP_DT_TM_TS";

    /** Column name of S80_ORD_TP_CD */
    public static final String COL_S80_ORD_TP_CD = "S80_ORD_TP_CD";

    /** Column name of WH_OWNR_CD */
    public static final String COL_WH_OWNR_CD = "WH_OWNR_CD";

    /** Column name of WH_CD */
    public static final String COL_WH_CD = "WH_CD";

    /** Column name of WMS_MDSE_CD */
    public static final String COL_WMS_MDSE_CD = "WMS_MDSE_CD";

    /** Column name of WMS_CARR_CD */
    public static final String COL_WMS_CARR_CD = "WMS_CARR_CD";

    /** Column name of TPL_CARR_CD */
    public static final String COL_TPL_CARR_CD = "TPL_CARR_CD";
    
    /** Column name of TPL_LOC_TXT */
    public static final String COL_TPL_LOC_TXT = "TPL_LOC_TXT";

    /** Column name of TPL_SWH_CD */
    public static final String COL_TPL_SWH_CD = "TPL_SWH_CD";

    /** Column name of WMS_REF_NM */
    public static final String COL_WMS_REF_NM = "WMS_REF_NM";

    /** Column name of WMS_RSN_CD */
    public static final String COL_WMS_RSN_CD = "WMS_RSN_CD";

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

    /** Column name of RWS_NUM */
    public static final String COL_RWS_NUM = "RWS_NUM";

    /** Column name of INBD_OTBD_CD */
    public static final String COL_INBD_OTBD_CD = "INBD_OTBD_CD";

    /** Column name of WMS_TASK_CD_ARY */
    public static final String COL_WMS_TASK_CD_ARY = "WMS_TASK_CD_ARY";

    /** Column name of INVTY_OWNR_CD */
    public static final String COL_INVTY_OWNR_CD = "INVTY_OWNR_CD";

    /** Column name of INVTY_LOC_CD */
    public static final String COL_INVTY_LOC_CD = "INVTY_LOC_CD";

    /** Column name of FROM_STK_STS_CD */
    public static final String COL_FROM_STK_STS_CD = "FROM_STK_STS_CD";

    /** Column name of INVTY_STK_STS_CD */
    public static final String COL_INVTY_STK_STS_CD = "INVTY_STK_STS_CD";

    /** Column name of WMS_ORG_HOST_ID */
    public static final String COL_WMS_ORG_HOST_ID = "WMS_ORG_HOST_ID";

    /** Column name of INTFC_TP_ID */
    public static final String COL_INTFC_TP_ID = "INTFC_TP_ID";

    /** Column name of TPL_CARR_SVC_LVL_CD */
    public static final String COL_TPL_CARR_SVC_LVL_CD = "TPL_CARR_SVC_LVL_CD";

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

    /** Value WMS_TASK_CD(101) */
    public static final String VAL_WMS_TASK_CD_101 = "101";

    /** Value WMS_TASK_CD(106) */
    public static final String VAL_WMS_TASK_CD_106 = "106";

    /** Value WMS_TASK_CD(100) */
    public static final String VAL_WMS_TASK_CD_100 = "100";

    /** Value WMS_TASK_CD(126) */
    public static final String VAL_WMS_TASK_CD_126 = "126";

    /** Value MS_TASK_CD(LF) */
    public static final String VAL_WMS_TASK_CD_LF = "LF";

    /** Value MS_TASK_CD(LR) */
    public static final String VAL_WMS_TASK_CD_LR = "LR";

    /** Value CONST_EDI_TM_CD */
    public static final String VAL_CONST_EDI_TM_CD = "LT";

    /** Value CONST_EDI_TM_CD */
    public static final String VAL_WMS_RSN_CD = "11";

    /** Value INBD_OTBD_CD(Outbound) */
    public static final String VAL_INBD_OTBD_CD_OTBD = "2";

    /** Value INBD_OTBD_CD(Intbound) */
    public static final String VAL_INBD_OTBD_CD_INBD = "1";

    /** Value INBD_OTBD_CD(Intbound) */
    public static final String VAL_WRK_TRX_ID = "000000000000000000000000000001";

    /** Value for Look Up From VarcharConst */
    public static final String VAR_CHAR_CONST_NM_TG_ORDER = "NLBB0800_TARGET_ORDER";

    /** Value for Look Up From VarcharConst */
    public static final String VAR_CHAR_CONST_NM_PACK_FLG = "WMS_PACK_CD_SET_OWNER_CD_FLG";

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

    /** Max Length */
    public static final String MAX_LG = "maxLen";

    /** Max Length Of 30 */
    public static final int MAX_LG_30 = 30;

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
    public static final int LG_SO_OTBD_ORD_LINE_NUM = 3;

    /** Max Length Of OTBD_ORD_LINE_NUM */
    public static final int LG_RWS_OTBD_ORD_LINE_NUM = 5;

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

    /** String Length 20 */
    public static final int STRING_LENGTH_20 = 20;

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

    // QC#23020
    /** Column name of CARR_CD */
    public static final String COL_CARR_CD = "CARR_CD";

    /** Column name of SHPG_SVC_LVL_CD */
    public static final String COL_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** Add QC#50389. VAR_CHAR_CONST_KEY */
    public static final String NLBB0810_DEF_WMS_CARR_CD = "NLBB0810_DEF_WMS_CARR_CD";

    /** Add QC#50666. Column name of SO_NUM */
    public static final String COL_SO_NUM = "SO_NUM";

    // START 2019/08/20 [QC#52458, ADD]
    /** SQL Parameter Name Outbound Order Line Number ZERO */
    public static final String OTBD_ORD_LINE_NUM_ZERO = "OTBD_ORD_LINE_NUM_ZERO";

    /** SQL Parameter Line Number ZERO */
    public static final int LINE_NUM_ZERO = 0;
    // END 2019/08/20 [QC#52458, ADD]

    /** Add QC#52584. Value Blank */
    public static final String VAL_BLANK_14 = "              ";

    /** Add QC#52584. Error Mainl Sprit Line */
    public static final String VAL_SEP_LINE = "-------------------------------------------------------------------------";

    /** Add QC#52035. NLGM0028E : [@] is not found. */
    public static final String NLGM0028E = "NLGM0028E";

    // Add Start 2019/12/04 QC#54887
    /** You can not execute because details are not exists in this Order#:[@]. Please execute manual support from S21 screen. */
    public static final String NLGM0090E = "NLGM0090E";
    // Add End 2019/12/04 QC#54887

    /** Column name of MDSE_CD */
    public static final String COL_MDSE_CD = "MDSE_CD";

    /** Column name of PICK_SVC_CONFIG_MSTR_PK */
    public static final String COL_PICK_SVC_CONFIG_MSTR_PK = "PICK_SVC_CONFIG_MSTR_PK";

}
