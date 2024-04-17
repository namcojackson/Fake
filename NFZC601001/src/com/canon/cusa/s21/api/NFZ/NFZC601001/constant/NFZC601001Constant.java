/**<pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NFZ.NFZC601001.constant;

/**
 * <pre>
 * NFZC601001Constant.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/20   Hitachi         E.Kameishi      Create          N/A.
 * 2017/03/14   Hitachi         E.Kameishi      Update          QC#18018
 * 2020/07/30   CITS            R.Kurahashi     Update          QC#57436
 * </pre>
 */
public class NFZC601001Constant {

    /** Message Code for Error */
    public static enum MSG_ERROR_CD {
        /** Message Id : NFCM0659E */
        NFCM0659E,
        /** Message Id : NFCM0578E */
        NFCM0578E,
        /** Message Id : NFCM0582E */
        NFCM0582E,
        /** Message Id : NFCM0550E */
        NFCM0550E,
        /** Message Id : NFCM0570E */
        NFCM0570E,
        /** Message Id : NFCM0561E */
        NFCM0561E,
        /** Message Id : NFCM0560E */
        NFCM0560E,
        /** Message Id : NFCM0813E */
        NFCM0813E,
        /** Message Id : NFCM0607E */
        NFCM0607E,
        /** Message Id : NFCM0608E */
        NFCM0608E,
        /** Message Id : NFCM0565E */
        NFCM0565E,
        /** Message Id : NFZM0031E */
        NFZM0031E,
        /** Message Id : NFZM0032E */
        NFZM0032E,
        /** Message Id : NFZM0033E */
        NFZM0033E,
        /** Message Id : NFZM0034E */
        NFZM0034E,
        /** Message Id : NFZM0035E */
        NFZM0035E,
        /** Message Id : NFZM0036E */
        NFZM0036E,
        /** Message Id : NFZM0037E */
        NFZM0037E,
        /** Message Id : NFZM0038E */
        NFZM0038E,
        /** Message Id : NFZM0039E */
        NFZM0039E,
        /** Message Id : NFZM0040E */
        NFZM0040E,
        /** Message Id : NFZM0041E */
        NFZM0041E,
        /** Message Id : NFZM0042E */
        NFZM0042E
    }

    /** GLBL_CMPY_CD */
    public static final String GLBL_CMPY_CD_ADB = "ADB";
    /** BIZ_APP_ID */
    public static final String CST_BIZ_APP_ID = "NFZC6010";
    /** four figure */
    public static final int THREE_FIGURE = 4;
    /** eight figure */
    public static final int EIGTH_FIGURE = 8;
    /** ACCT_MA_CD(eManage) : e-Manage */
    public static final String ACCT_MA_CD_EMANAGE = "EMNG";
    /** PMTC_TAX_INDEX_NUM : OFF */
    public static final String PMTC_TAX_INDEX_NUM_OFF = "2";
    /** PMTC_PRFL_ORD_OVRD_CD : NO */
    public static final String PMTC_PRFL_ORD_OVRD_CD_NO = "NO";
    /** NWZC2030 */
    public static final String NWZC2030 = "NWZC2030";

    /** AR_RCPT_TOC_CD */
    public static final String AR_RCPT_TOC_CD = "AR_RCPT_TOC_CD";
    /** AR_RCPT_PROD_CD */
    public static final String AR_RCPT_PROD_CD = "AR_RCPT_PROD_CD";
    /** AR_BAT_USR_ID */
    public static final String AR_BAT_USR_ID = "AR_BAT_USR_ID";

    /** DateFormat : yyMMddHHmmssSSS */
    public static final String YYYYMMDD_HHMMSS_SSS = "yyyyMMddHHmmssSSS";
    /** DateFormat : HHmmss */
    public static final String HHMMSS = "HHmmss";
    /** AR_BAT_RCPT_NM_EMNG */
    public static final String AR_BAT_RCPT_NM_EMNG = "EManage";
    /** Underscore */
    public static final String STR_UNDERSCORE = "_";
    /** AR_RCPT_SRC_CD : 00(Dummy) */
    public static final String AR_RCPT_SRC_CD_DUMMY = "00";
    /** Table Name :AR_RCPT_RCV_WRK */
    public static final String AR_RCPT_RCV_WRK = "AR_RCPT_RCV_WRK";
    /** Table Name :AR_RCPT_IN_PROC_WRK */
    public static final String AR_RCPT_IN_PROC_WRK = "AR_RCPT_IN_PROC_WRK";
    /** Table Name :AR_APPLY_INTFC_WRK */
    public static final String AR_APPLY_INTFC_WRK = "AR_APPLY_INTFC_WRK";
    /** Table Name :AR_BAT_RCPT */
    public static final String AR_BAT_RCPT = "AR_BAT_RCPT";

    /** xxIntfcRtrnStsCd : 0(Normal) */
    public static final String XX_INTFC_RTRN_STS_CD_NORMAL = "0";
    /** xxIntfcRtrnStsCd : 9(Error) */
    public static final String XX_INTFC_RTRN_STS_CD_ERR = "9";
    /** Sales Date */
    public static final String SALES_DATE = "Sales Date";
    /** CR_CARD_CUST_REF_NUM */
    public static final String CR_CARD_CUST_REF_NUM = "CR_CARD_CUST_REF_NUM";
    /** CR_CARD_CUST_REF_NUM */
    public static final String CR_CARD_CHRG_FLG = "CR_CARD_CHRG_FLG";
    /** Invoice Information */
    public static final String INV_INFO = "Invoice Information";
    /** INV_NUM */
    public static final String INV_NUM = "INV_NUM";
    /** DEAL_APPLY_AMT */
    public static final String DEAL_APPLY_AMT = "DEAL_APPLY_AMT";
    // START 2017/03/14 E.Kameishi [QC#18018,ADD]
    /** String Colon */
    public static final String COLON = " : ";
    /** String Comma */
    public static final String DELIMITER = " , ";
    /** String GLBL_CMPY_CD */
    public static final String GLBL_CMPY_CD_STR = "GLBL_CMPY_CD";
    /** String XX_MODE_CD */
    public static final String XX_MODE_CD_STR = "XX_MODE_CD";
    /** String SLS_DT */
    public static final String SLS_DT_STR = "SLS_DT";
    /** String SELL_TO_CUST_CD */
    public static final String SELL_TO_CUST_CD_STR = "SELL_TO_CUST_CD";
    /** String CR_CARD_AUTH_AMT */
    public static final String CR_CARD_AUTH_AMT_STR = "CR_CARD_AUTH_AMT";
    /** String CR_CARD_TRX_TP_CD */
    public static final String CR_CARD_TRX_TP_CD_STR = "CR_CARD_TRX_TP_CD";
    /** String XX_PMTC_TAX_IND_NUM */
    public static final String XX_PMTC_TAX_IND_NUM_STR = "XX_PMTC_TAX_IND_NUM";
    /** String XX_PMTC_PRFL_ORD_OVRD_CD */
    public static final String XX_PMTC_PRFL_ORD_OVRD_CD_STR = "XX_PMTC_PRFL_ORD_OVRD_CD";
    /** String XX_PMTC_ORD_ID */
    public static final String XX_PMTC_ORD_ID_STR = "XX_PMTC_ORD_ID";
    /** NFZC2020 */
    public static final String NFZC2020 = "NFZC2020";
    /** String BILL_TO_CUST_CD */
    public static final String BILL_TO_CUST_CD_STR = "BILL_TO_CUST_CD";
    /** String PROC_DT */
    public static final String PROC_DT_STR = "PROC_DT";
    /** NFZC3010 */
    public static final String NFZC3010 = "NFZC3010";
    /** String APPLY_GRP_KEY */
    public static final String APPLY_GRP_KEY_STR = "APPLY_GRP_KEY";
    /** String DEAL_SQ_NUM */
    public static final String DEAL_SQ_NUM_STR = "DEAL_SQ_NUM";
    /** String BAT_DT */
    public static final String BAT_DT_STR = "BAT_DT";
    /** String FUNC_ID */
    public static final String FUNC_ID = "FUNC_ID";
    // END 2017/03/14 E.Kameishi [QC#18018,ADD]
    // START 2020/07/30 R.Kurahashi [QC#57436,ADD]
    /** String AR_RCPT_CHK_NUM_HDR */
    public static final String AR_RCPT_CHK_NUM_HDR = "IEX_";
    /** String AUTO_SQ_EXTN_KEY_AR_RCPT_CHK_NUM */
    public static final String AUTO_SQ_EXTN_KEY_AR_RCPT_CHK_NUM = "AR_RCPT_CHK_NUM";
    // END 2020/07/30 R.Kurahashi [QC#57436,ADD]
}
