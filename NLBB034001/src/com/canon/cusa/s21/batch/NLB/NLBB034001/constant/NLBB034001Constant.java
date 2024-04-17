/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB034001.constant;

/**
 * <pre>
 * Business ID : NLBB0340 Send Freight Info to CTSI (IF Data Creation) Batch
 * Function Name : NLBB034001Constant
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/06/2016   CITS            R.Shimamoto     Create          N/A
 * 01/11/2017   CITS            R.Shimamoto     Update          QC#16278
 * 04/23/2018   CITS            K.Ogino         Update          QC#25669
 * </pre>
 */
public class NLBB034001Constant {

    // ssm parameter map
    /** map key : glblCmpyCd. */
    public static final String MAP_KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** map key : carrFrtInfoIntfcProcCd. */
    public static final String MAP_KEY_CARR_FRT_INFO_INTFC_PROC_CD = "carrFrtInfoIntfcProcCd";

    /** Error Message Text: Global Company Code */
    public static final String MSG_TXT_GLBL_CMPY_CD = "Global Company Code";

    /** Error Message Text: Interface Id */
    public static final String MSG_TXT_INTERFACE_ID = "Interface Id";

    /** Batch ID. */
    public static final String BATCH_ID = "NLBB0340";

    /** Business ID. */
    public static final String BUSINESS_ID = "NLBB034001";

    /** db column : CARR_FRT_INFO_INTFC_WRK_PK. */
    public static final String CARR_FRT_INFO_INTFC_WRK_PK = "CARR_FRT_INFO_INTFC_WRK_PK";

    /** db column : CARR_FRT_SRC_TP_NM. */
    public static final String CARR_FRT_SRC_TP_NM = "CARR_FRT_SRC_TP_NM";

    /** db column : CARR_FRT_PRFL_NUM. */
    public static final String CARR_FRT_PRFL_NUM = "CARR_FRT_PRFL_NUM";

    /** db column : TRX_HDR_NUM. */
    public static final String TRX_HDR_NUM = "TRX_HDR_NUM";

    /** db column : TRX_REF_NUM_01. */
    public static final String TRX_REF_NUM_01 = "TRX_REF_NUM_01";

    /** db column : TRX_REF_NUM_02. */
    public static final String TRX_REF_NUM_02 = "TRX_REF_NUM_02";

    /** db column : COA_CMPY_CD. */
    public static final String COA_CMPY_CD = "COA_CMPY_CD";

    /** db column : COA_BR_CD. */
    public static final String COA_BR_CD = "COA_BR_CD";

    /** db column : COA_CC_CD. */
    public static final String COA_CC_CD = "COA_CC_CD";

    /** db column : COA_ACCT_CD. */
    public static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** db column : COA_PROD_CD. */
    public static final String COA_PROD_CD = "COA_PROD_CD";

    /** db column : COA_CH_CD. */
    public static final String COA_CH_CD = "COA_CH_CD";

    /** db column : COA_AFFL_CD. */
    public static final String COA_AFFL_CD = "COA_AFFL_CD";

    /** db column : COA_PROJ_CD. */
    public static final String COA_PROJ_CD = "COA_PROJ_CD";

    /** db column : CARR_FRT_CHRG_BU_CD. */
    public static final String CARR_FRT_CHRG_BU_CD = "CARR_FRT_CHRG_BU_CD";

    /** db column : CARR_CD. */
    public static final String CARR_CD = "CARR_CD";

    /** db column : INVTY_TRX_DT. */
    public static final String INVTY_TRX_DT = "INVTY_TRX_DT";

    /** db column : FROM_POST_CD. */
    public static final String FROM_POST_CD = "FROM_POST_CD";

    /** db column : TO_POST_CD. */
    public static final String TO_POST_CD = "TO_POST_CD";

    /** db column : CARR_FRT_INFO_INTFC_PROC_CD. */
    public static final String CARR_FRT_INFO_INTFC_PROC_CD = "CARR_FRT_INFO_INTFC_PROC_CD";

    /** db column : CARR_FRT_INFO_PRFL_NUM. */
    public static final String CARR_FRT_INFO_PRFL_NUM = "CARR_FRT_INFO_PRFL_NUM";

    /** db column : CARR_FRT_INFO_SRC_TP_NM. */
    public static final String CARR_FRT_INFO_SRC_TP_NM = "CARR_FRT_INFO_SRC_TP_NM";

    /** db column : ITRL_INTFC_ID. */
    public static final String ITRL_INTFC_ID = "ITRL_INTFC_ID";

    /** db column : ITRL_TRX_SQ. */
    public static final String ITRL_TRX_SQ = "ITRL_TRX_SQ";

    /** Error Message Text: delimiter */
    public static final String MSG_TXT_DELIMITER = ",";

    /** Error Message Text: equals */
    public static final String MSG_TXT_EQUALS = "=";

    /** Error Message Text: space */
    public static final String MSG_TXT_SPACE = " ";

    /** STR_01 */
    public static final String STR_01 = "01";

    /** STR_02 */
    public static final String STR_02 = "02";

    /** STR_03 */
    public static final String STR_03 = "03";

    /** STR_04 */
    public static final String STR_04 = "04";

    /** INT_1 */
    public static final int INT_1 = 1;

    // db table
    /** db table : CARR_FRT_INFO_INTFC_WRK */
    public static final String CARR_FRT_INFO_INTFC_WRK = "CARR_FRT_INFO_INTFC_WRK";

    /** db table : NLBI1330_01 */
    public static final String NLBI1330_01 = "NLBI1330_01";

    // error message code
    /** The Constant [@] was not found on Constant table. */
    public static final String NPAM1010E = "NPAM1010E";

    /** message id : NWZM0650E */
    public static final String MSG_ID_NWZM0650E = "NWZM0650E";

    /** message id : NLAM1270E */
    public static final String MSG_ID_NLAM1270E = "NLAM1270E";

    /** message id : ZZZM9025E */
    public static final String MSG_ID_ZZZM9025E = "ZZZM9025E";

    /** message id : NLGM0045E */
    public static final String MSG_ID_NLGM0045E = "NLGM0045E";

    /** message id : NLGM0046E */
    public static final String MSG_ID_NLGM0046E = "NLGM0046E";

    /** message id : NLGM0044E */
    public static final String MSG_ID_NLGM0044E = "NLGM0044E";

    /** */
    public static final String MSG_ID_NLBM1065E = "NLBM1065E";

    /** */
    public static final String MSG_ID_NLBM1358E = "NLBM1358E";

    /** message id : NLAM1273E */
    public static final String MSG_ID_NLAM1273E = "NLAM1273E";

    /** message id : NLAM1272E */
    public static final String MSG_ID_NLAM1272E = "NLAM1272E";

    /** message id : NLAM1101E */
    public static final String MSG_ID_NLAM1101E = "NLAM1101E";

    /** Mail Gropu Id Key: From */
    public static final String MAIL_GROUP_ID_FROM = "NLBB034001";

    /** System Error Mail group id for To. */
    public static final String MAIL_GROUP_ID_TO_SYSTEM = "NLBB034001";

    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID_M001 = "NLBB0340M001";

    /** Mail Template Key: Message */
    public static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Mail Timestamp Format */
    public static final String MAIL_TS_FMT = "MM/dd/yyyy HH:mm:ss.SSS";

    /** Mail String Field : TIMESTAMP */
    public static final String MAIL_FIELD_TIMESTAMP = "TIMESTAMP";

    /** Mail String Field : MESSAGE */
    public static final String MAIL_FIELD_MESSAGE = "MAIL_MESSAGE";

    /** Error Massage: CRLF */
    public static final String ERR_MSG_CRLF = "\r\n";

    /** QC#25669 */
    public static final String COA_GL_SEG_TXT = "COA_GL_SEG_TXT";

}
