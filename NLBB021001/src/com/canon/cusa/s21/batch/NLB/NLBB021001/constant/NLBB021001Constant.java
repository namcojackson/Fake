/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB021001.constant;

/**
 * <pre>
 * Batch Program Constant Class for Receive EDI214
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/25/2016   CITS            T.Wada          Create          
 *</pre>
 */
public class NLBB021001Constant {

    /** business_id */
    public static final String BUSINESS_ID = "NLBB0210";

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

    /** message id : business error */
    public static final String NLBM1088E = "NLBM1088E";

    /** message id : business error */
    public static final String NLBM1089E = "NLBM1089E";

    /** message id : business error */
    public static final String NLBM1090E = "NLBM1090E";

    /** message id : business error */
    public static final String NLBM1091E = "NLBM1091E";

    /** message id : business error */
    public static final String NLBM1092E = "NLBM1092E";

    /** message id : business error */
    public static final String NLBM1093E = "NLBM1093E";

    /** message id : business error */
    public static final String NLBM1094E = "NLBM1094E";

    /** message id : business error */
    public static final String NLBM1095E = "NLBM1095E";

    /** message id : business error */
    public static final String NLBM1101E = "NLBM1101E";

    /** message id : business error */
    public static final String NLBM1102E = "NLBM1102E";

    /** message id : DB error */
    public static final String NLBM1064E = "NLBM1064E";

    /** message id : no search results */
    public static final String NLBM1063E = "NLBM1063E";

    /** message id : mail error */
    public static final String NLBM1065E = "NLBM1065E";

    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "NLBB0210";

    /** mail group id for To */
    public static final String MAIL_GROUP_ID_TO = "NLBB0210";

    /** mail key for From */
    public static final String MAIL_KEY_FROM = "From";

    /** mail key for To */
    public static final String MAIL_KEY_TO = "To";

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = "NLBB9999M001";

    /** mail message header */
    public static final String MAIL_MSG_HEADER = "EDI_TP_CD  PRO#                           Message#  Message";

    /** template parameter key : batch_id */
    public static final String MAIL_TEMPLATE_KEY_ID = "batchId";

    /** template parameter key : errdate */
    public static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** template parameter key : message */
    public static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** line feed code */
    public static final String LINE_FEED_CODE = "\r\n";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** db column : EDI_GS_CTRL_CD */
    public static final String EDI_GS_CTRL_CD = "EDI_GS_CTRL_CD";

    /** db column : EDI_ST_CTRL_CD */
    public static final String EDI_ST_CTRL_CD = "EDI_ST_CTRL_CD";

    /** db column : EDI_STS_DT */
    public static final String EDI_STS_DT = "EDI_STS_DT";

    /** db column : EDI_STS_TM */
    public static final String EDI_STS_TM = "EDI_STS_TM";

    /** db column : POD_STS_CD */
    public static final String POD_STS_CD = "POD_STS_CD";

    /** db column : ediShpprNm */
    public static final String EDI_SHPPR_NM = "EDI_SHPPR_NM";

    /** db column : ediShpprAddr */
    public static final String EDI_SHPPR_ADDR = "EDI_SHPPR_ADDR";

    /** db column : ediShpprCtyNm */
    public static final String EDI_SHPPR_CTY_NM = "EDI_SHPPR_CTY_NM";

    /** db column : ediShpprStCd */
    public static final String EDI_SHPPR_ST_CD = "EDI_SHPPR_ST_CD";

    /** db column : ediShpprPostCd */
    public static final String EDI_SHPPR_POST_CD = "EDI_SHPPR_POST_CD";

    /** db column : ediBillToPtyNm */
    public static final String EDI_BILL_TO_PTY_NM = "EDI_BILL_TO_PTY_NM";

    /** db column : ediBillToPtyAddr */
    public static final String EDI_BILL_TO_PTY_ADDR = "EDI_BILL_TO_PTY_ADDR";

    /** db column : EDI_BILL_TO_PTY_CTY_NM */
    public static final String EDI_BILL_TO_PTY_CTY_NM = "EDI_BILL_TO_PTY_CTY_NM";

    /** db column : EDI_BILL_TO_PTY_ST_CD */
    public static final String EDI_BILL_TO_PTY_ST_CD = "EDI_BILL_TO_PTY_ST_CD";

    /** db column : EDI_BILL_TO_PTY_POST_CD */
    public static final String EDI_BILL_TO_PTY_POST_CD = "EDI_BILL_TO_PTY_POST_CD";

    /** db column : ediCnsgnNm */
    public static final String EDI_CNSGN_NM = "EDI_CNSGN_NM";

    /** db column : ediCnsgnAddr */
    public static final String EDI_CNSGN_ADDR = "EDI_CNSGN_ADDR";

    /** db column : ediCnsgnCtyNm */
    public static final String EDI_CNSGN_CTY_NM = "EDI_CNSGN_CTY_NM";

    /** db column : ediCnsgnStCd */
    public static final String EDI_CNSGN_ST_CD = "EDI_CNSGN_ST_CD";

    /** db column : ediCnsgnPostCd */
    public static final String EDI_CNSGN_POST_CD = "EDI_CNSGN_POST_CD";

    /** db column : POD_SRC_TP_CD */
    public static final String POD_SRC_TP_CD = "POD_SRC_TP_CD";

    /** db column : EDI_TRX_ID */
    public static final String EDI_TRX_ID = "EDI_TRX_ID";

    /** db column : EDI_SQ_ID */
    public static final String EDI_SQ_ID = "EDI_SQ_ID";

    /** db column : EDI_SQ_ID */
    public static final String ERR_MSG_CD = "ERR_MSG_CD";

    /** db column : PRO_NUM */
    public static final String PRO_NUM = "PRO_NUM";

    /** db column : POD_STS_DT */
    public static final String POD_STS_DT = "POD_STS_DT";

    /** db column : SHPG_STS_UPD_FLG */
    public static final String SHPG_STS_UPD_FLG = "SHPG_STS_UPD_FLG";

    /** db column : CARR_CD */
    public static final String CARR_CD = "CARR_CD";

    /** db column : EDI_TP_CD */
    public static final String EDI_TP_CD = "EDI_TP_CD";

    /** db column : EDI_PRO_NUM */
    public static final String EDI_PRO_NUM = "EDI_PRO_NUM";

    /** db column : ediStsRsnCd */
    public static final String EDI_STS_RSN_CD = "EDI_STS_RSN_CD";

    /** db column : ediTmCd */
    public static final String EDI_TM_CD = "EDI_TM_CD";

    /** db column : ediStsCtyNm */
    public static final String EDI_STS_CTY_NM = "EDI_STS_CTY_NM";

    /** db column : ediStsStCd */
    public static final String EDI_STS_ST_CD = "EDI_STS_ST_CD";

    /** db column : ediExCd */
    public static final String EDI_EX_CD = "EDI_EX_CD";

    /** db column : ediExPkgCd */
    public static final String EDI_EX_PKG_CD = "EDI_EX_PKG_CD";

    /** db column : ediExLoadQty */
    public static final String EDI_EX_LOAD_QTY = "EDI_EX_LOAD_QTY";

    /** db column : ediLoadQty */
    public static final String EDI_LOAD_QTY = "EDI_LOAD_QTY";

    /** db column : ediPkgCd */
    public static final String EDI_PKG_CD = "EDI_PKG_CD";

    /** db column : ediWt */
    public static final String EDI_WT = "EDI_WT";

    /** db column : ediWt */
    public static final String EDI_PICK_UP_DT = "EDI_PICK_UP_DT";

    /** db column : ediWt */
    public static final String EDI_PICK_UP_TM = "EDI_PICK_UP_TM";

    /** db column : EDI_STS_CD */
    public static final String EDI_STS_CD = "EDI_STS_CD";

    /** db column : EDI_CNTNR_NUM */
    public static final String EDI_CNTNR_NUM = "EDI_CNTNR_NUM";

    /** db column : EDI_WT_UNIT_CD */
    public static final String EDI_WT_UNIT_CD = "EDI_WT_UNIT_CD";

    /** db column : EDI_NET_AMT */
    public static final String EDI_NET_AMT = "EDI_NET_AMT";

    /** db column : EDI_SGN_CD */
    public static final String EDI_SGN_CD = "EDI_SGN_CD";

    /** db column : EDI_CTAC_TXT */
    public static final String EDI_CTAC_TXT = "EDI_CTAC_TXT";

    /** db column : EDI_CENT_CD */
    public static final String EDI_CENT_CD = "EDI_CENT_CD";

    /** db column : EDI_UCC_CD */
    public static final String EDI_UCC_CD = "EDI_UCC_CD";

    /** db column : SHPG_STS_CD */
    public static final String SHPG_STS_CD = "SHPG_STS_CD";

    /** first sequence number */
    public static final String FIRST_SEQUENCE_NUMBER = "001";

    /** length of PRO# */
    public static final int PRO_NUM_LENGTH = 30;

    /** length of EDI_TP_CD */
    public static final int EDI_TP_CD_LENGTH = 10;

    /** length of sequence number */
    public static final int SEQUENCE_LENGTH = 3;

    /** character of padding sequence number */
    public static final String PADDING_NUMBER = "0";

    /** Shipping Mode Code : Arrived(S/O Close) */
    public static final String MODE_ARRIVED_SOCLOSE = "15";

    /** Prameter Name: GLBL_CMPY_CD */
    public static final String PRAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Prameter Name: INTERFACE_ID */
    public static final String PRAM_NM_INTERFACE_ID = "Interface Id";

    /** */
    public static final int LEN_TM = 6;

    /** */
    public static final int POS_HH_ST = 0;

    /** */
    public static final int POS_HH_ED = 2;

    /** */
    public static final int POS_MM_ST = 2;

    /** */
    public static final int POS_MM_ED = 4;

    /** */
    public static final int POS_SS_ST = 4;

    /** */
    public static final int POS_SS_ED = 6;

    /** */
    public static final int MAX_NUM_HH = 23;

    /** */
    public static final int MAX_NUM_MM = 59;

    /** */
    public static final int MAX_NUM_SS = 59;

    /** */
    public static final int ZERO_VAL = 0;
}
