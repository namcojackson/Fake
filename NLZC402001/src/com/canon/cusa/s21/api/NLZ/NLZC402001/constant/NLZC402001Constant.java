/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC402001.constant;

/**
 *<pre>
 * Delivery Confirmation API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/10/2015   CSAI            K.Lee           Create          Initial
 * 07/11/2016   CSAI            Y.Imazu         Update          QC#10917
 *</pre>
 */
public interface NLZC402001Constant {

    /** PRO_NUM does not exist. */
    public static final String NLBM1089E = "NLBM1089E";

    /** CARR_CD does not exist. */
    public static final String NLBM1090E = "NLBM1090E";

    /** EDI_STS_DT is incorrect. */
    public static final String NLBM1091E = "NLBM1091E";

    /** EDI_STS_TM is incorrect. */
    public static final String NLBM1092E = "NLBM1092E";

    /** POD_STS_CD does not exist. */
    public static final String NLBM1093E = "NLBM1093E";

    /** BOL does not exist. */
    public static final String NLBM1094E = "NLBM1094E";

    /** EDI_PICK_UP_TM is incorrect. */
    public static final String NLBM1102E = "NLBM1102E";

    /** DB error occurred. */
    public static final String NLBM1064E = "NLBM1064E";

    /** Target data does not exist. */
    public static final String NLBM1063E = "NLBM1063E";

    /** Failed to register SHPG_ORD_DTL. */
    public static final String NLZM2257E = "NLZM2257E";

    /** Shipping Information is not found. */
    public static final String NLZM2467E = "NLZM2467E";

    /** SHPG_STS_CD for BOL is the status for unshipped. */
    public static final String NLZM2472E = "NLZM2472E";

    /** SHPG_STS_CD for SHPG_PLN is the status for unshipped. */
    public static final String NLZM2473E = "NLZM2473E";

    /** The target "Shipping Order" cannot be found. */
    public static final String NLZM2391E = "NLZM2391E";

    /** It failed to update Shipping Order. */
    public static final String NLZM2513E = "NLZM2513E";

    /**
     * Parameter Pro Number does not match with Pro Number associated
     * with the shipping Information.
     */
    public static final String NLZM2468E = "NLZM2468E";

    /**
     * Parameter Carrier Code does not match with Carrier Code
     * associated with the shipping Information.
     */
    public static final String NLZM2469E = "NLZM2469E";

    /**
     * Parameter EDI Type Code does not match with EDI Type Code
     * associated with the shipping Information.
     */
    public static final String NLZM2470E = "NLZM2470E";

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

    /** db column : EDI_TRX_ID */
    public static final String EDI_TRX_ID = "EDI_TRX_ID";

    /** db column : EDI_SQ_ID */
    public static final String EDI_SQ_ID = "EDI_SQ_ID";

    /** db column : EDI_SQ_ID */
    public static final String ERR_MSG_CD = "ERR_MSG_CD";

    /** db column : PRO_NUM */
    public static final String PRO_NUM = "PRO_NUM";

    /** db column : WH_CD */
    public static final String WH_CD = "WH_CD";

    /** db column : INVTY_LOC_CD */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** db column : BOL_NUM */
    public static final String BOL_NUM = "BOL_NUM";

    /** db column : SHPG_PLN_NUM */
    public static final String SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** db column : SO_NUM */
    public static final String SO_NUM = "SO_NUM";

    /** db column : SO_SLP_NUM */
    public static final String SO_SLP_NUM = "SO_SLP_NUM";

    /** db column : SHPG_STS_CD */
    public static final String SHPG_STS_CD = "SHPG_STS_CD";

    /** db column : SHIP_FLG */
    public static final String SHIP_FLG = "SHIP_FLG";

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

    /** db column : schdDelyDt */
    public static final String SCHD_DELY_DT = "SCHD_DELY_DT";

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

    /** Shipping Mode Code : Arrived(Per Shipping Plan Number) */
    public static final String MODE_ARRIVED_SHPG_PLN = "26";
}
