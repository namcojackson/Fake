/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB058001.constant;
/**
 * <pre>
 * NFCB058001Constant
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/04   Fujitsu         S.Fujita        Create          N/A
 *</pre>
 */
public class NFCB058001Constant {
    /** BUSINESS_ID */
    public static final String BUSINESS_ID = "NFCB0580";
    /** Format Time Stamp */
    public static final String TYPE_TIME_STAMP = "yyyyMMddHHmmss";

    /** VarChar Constant of AR_BAT_USR_ID */
    public static final String AR_BAT_USR_ID = "AR_BAT_USR_ID";
    /** VarChar Constant of AR_PUR_RCPT_BAT_NUM */
    public static final String AR_PUR_RCPT_BAT_NUM = "AR_PUR_RCPT_BAT_NUM";
    /** VarChar Constant of AR_PUR_RCPT_TP_CD */
    public static final String AR_PUR_RCPT_TP_CD = "AR_PUR_RCPT_TP_CD";
    /** VarChar Constant of AR_PUR_RCPT_TRX_TP_CD */
    public static final String AR_PUR_RCPT_TRX_TP_CD = "AR_PUR_RCPT_TRX_TP_CD";
    /** VarChar Constant of AR_RCPT_PROD_CD */
    public static final String AR_RCPT_PROD_CD = "AR_RCPT_PROD_CD";
    /** VarChar Constant of AR_RCPT_TOC_CD */
    public static final String AR_RCPT_TOC_CD = "AR_RCPT_TOC_CD";
    /** VarChar Constant of AR_SUB_SYS_ID */
    public static final String AR_SUB_SYS_ID = "AR_SUB_SYS_ID";

    /** @ cannot be obtained. */
    public static final String NFCM0531E = "NFCM0531E";
    /** Failed to add [@] ; Table=[@]. */
    public static final String NFCM0616E = "NFCM0616E";
    /** The invoice data has been updated by another user.  Please search again. */
    public static final String NFCM0079E = "NFCM0079E";
    /** [@] does not exist. */
    public static final String NFCM0619W = "NFCM0619W";
    /** Data could not be added due to [@]. Please try again. */
    public static final String NFCM0002E = "NFCM0002E";
    /** Transaction Data cannot be auto cash application in the future of receipt date. */
    public static final String NFCM0809E = "NFCM0809E";
    /** A valid Credit Profile cannot be obtained. Bill To Customer#:[@},Bill To Customer Account#:[@] */
    public static final String NFCM0810E = "NFCM0810E";
    /** A valid Exchange Rate cannot be obtained. Currency Code:[@},Entry Date:[@] */
    public static final String NFCM0811E = "NFCM0811E";
    /** An error on NFZC3010 API calling. Process Mode:[@],Apply Group Key:[@] */
    public static final String NFCM0812E = "NFCM0812E";
    /** An error on NFZC2020 API calling. [@]:[@]. Process Mode:02,Bill To Customer#:[@] */
    public static final String NFCM0813E = "NFCM0813E";

    /** Message String */
    public static final String AR_RCPT = "AR_RCPT";
    /** Message String */
    public static final String AR_RCPT_DTL = "AR_RCPT_DTL";
    /** Message String */
    public static final String AR_TRX_BAL = "AR_TRX_BAL";
    /** Message String */
    public static final String AR_APPLY_INTFC_WRK = "AR_APPLY_INTFC_WRK";
    /** Message String */
    public static final String RECEIPT = "The receipt data";
    /** Message String */
    public static final String MODE_RECEIPT = "1100 new receipt registration";
    /** Message String */
    public static final String MODE_APPLY = "1010 cash application";
    /** RcptBatSqNum */
    public static final String AR_RCPT_BAT_SQ_NUM = "001";
    /** RcptChkNum */
    public static final String AR_RCPT_CHK_NUM = "0000000000";
    /** UniqueID for RcptNum */
    public static final String BIZAPL_RCPTNUMKEY = "RC#";
    /** ArTrxBal SqRtnCd */
    public static final String AR_TRX_BAL_SQ_RTNCD_NORMAL = "0";
    /** ArTrxBal SqNum */
    public static final int AR_TRX_BAL_SQ_START_NUM = 0;
    /** CrRebillRsnCatg  -External- */
    public static final String EXTERNAL = "E";
    /** CrRebillRsnCatg  -Internal-*/
    public static final String INTERNAL = "I";
    /** Transaction Mode  -Credit Memo- */
    public static final String CREDIT = "Credit Memo";
    /** Transaction Mode  -Original Invoice- */
    public static final String ORIGINAL = "Original Invoice";
    /** Transaction Mode  -Rebill Invoice- */
    public static final String REBILL = "Rebill Invoice";

    /** Mail */
    /** Mail Template */
    public static final String MAIL_NFCB0580M001 = "NFCB0580M001";
    /** Mail Group Id Key: From*/
    public static final String MAIL_GRP_ID_FROM = "FROM0005";
    /** Mail Group Id Key: To */
    public static final String MAIL_GRP_ID_TO = "NFCB0580";
    /** Mail Key (To) */
    public static final String MAIL_KEY_1_TO = "To";
    /** Line Feed Code */
    public static final String LINE_FEED_CODE = "\r\n";

    /** Constant of AR_SUB_SYS_ID of API AFZC301001 */
    public static final String APPLY_RTNCD_UN_PROCCES = "0";
    /** Constant of AR_SUB_SYS_ID of API AFZC301001 */
    public static final String APPLY_RTNCD_NORMAL = "1";
    /** Constant of AR_SUB_SYS_ID of API AFZC301001 */
    public static final String APPLY_RTNCD_CASHAPP_ERROR = "2";
    /** Constant of AR_SUB_SYS_ID of API AFZC301001 */
    public static final String APPLY_RTNCD_EXCLUSION_ERROR = "3";
    /** Constant of AR_SUB_SYS_ID of API AFZC301001 */
    public static final String APPLY_RTNCD_OTHERS_ERROR = "4";
    /** Constant of AR_SUB_SYS_ID of API AFZC301001 */
    public static final String API_ERR_UNPROCESSING = "Unprocessing";
    /** Constant of AR_SUB_SYS_ID of API AFZC301001 */
    public static final String API_ERR_CASH_APPLICATION_ERROR = "Cash Application Error";
    /** Constant of AR_SUB_SYS_ID of API AFZC301001 */
    public static final String API_ERR_EXCLUSION_ERROR = "Exclusion Error";
    /** Constant of AR_SUB_SYS_ID of API AFZC301001 */
    public static final String API_ERR_OTHER_ERROR = "Others Errorr";

    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;
    /** Scale */
    public static final int VIEW_SCALE = 2;

    /** Parameter for AR_APPLY_INTFC_WRK */
    /** Deal Sequence Number */
    public static final String DEAL_SQ_NUM = "00000001";
    /** Deal Sequence Detail Number */
    public static final String DEAL_SQ_DTL_NUM = "0001";
    /** Process Type */
    public static final String PROC_TP_CD_NEW = "1";
    /** Receipt Number */
    public static final String ZERO = "0";
    /** BLANK */
    public static final String BLANK = "";
    /** YEAR */
    public static final int YEAR = 4;
    /** MONTH */
    public static final int MONTH = 6;
}
