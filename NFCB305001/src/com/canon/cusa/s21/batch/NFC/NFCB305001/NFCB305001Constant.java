package com.canon.cusa.s21.batch.NFC.NFCB305001;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 *
 * <pre>
 * Send AR Refund Info to CFS
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/28/2016   CUSA            Y.Aikawa        Create          N/A
 * </pre>
 */

public class NFCB305001Constant {
    // ** Fixed Value ** //
    // ** Common ** //
    /** EMPTY_STRING */
    static final String EMPTY_STRING = "";
    /** Bulk Commit Limit */
    static final int INT_BULK_COM_LIM = 10000;
    /** yyyyMMdd */
    static final String YYYYMMDD = "yyyyMMdd";
    /** CFS */
    static final String CFS = "CFS";
    /** 8 Digits Zero Characters */
    static final String STR_8_DIGIT_ZERO = "00000000";
    /** 9 Digits spaces */
    static final String STR_9_DIGIT_SPACE = "         ";
    /** Integer 8 */
    static final Integer INT_8 = 8;
    /** Integer 10 */
    static final Integer INT_10 = 10;
    /** String 0 */
    static final String STR_0 = "0";
    /** Default Interface File Name */
    static final String DEF_INTFC_FILE_NM = "i106_incbs";

    // ** Interface ID ** //
    /** Interface ID */
    static final String NFCI1130 = "NFCI1130";

    // ** Message ID ** //
    /** Error Message (The mail template cannot be acquired.  <Template ID: [@]>) **/
    static final String NFBM0184E = "NFBM0184E";
    /** Error Message (Unexpected error has occurred.) **/
    static final String NFCM0624E = "NFCM0624E";
    /** Error Message (unique constraint violated.) **/
    static final String ZZBM0074E = "ZZBM0074E";
    /** Error Message (The field of [@] is illegal.) **/
    static final String ZZMM0007E = "ZZMM0007E";

    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_REFUND_RECORD = "SELECT_REFUND_RECORD";
    /** SSM Statement ID */
    static final String SELECT_LOCKBOX_RECORD = "SELECT_LOCKBOX_RECORD";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String AR_ORG_RCPT_NUM = "AR_ORG_RCPT_NUM";
    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";
    /** DB Item Column Name */
    static final String RCPT_NUM = "RCPT_NUM";
    /** DB Item Column Name */
    static final String DEAL_ORIG_RCPT_AMT = "DEAL_ORIG_RCPT_AMT";
    /** DB Item Column Name */
    static final String AR_RF_TRX_PK = "AR_RF_TRX_PK";
    /** DB Item Column Name */
    static final String AR_RF_AMT = "AR_RF_AMT";
    /** DB Item Column Name */
    static final String DEAL_RF_AMT = "DEAL_RF_AMT";
    /** DB Item Column Name */
    static final String EXISTS_AR_TRX_BAL = "EXISTS_AR_TRX_BAL";
    /** DB Item Column Name */
    static final String CUST_RCPT_AMT = "CUST_RCPT_AMT";
    /** DB Item Column Name */
    static final String CUST_INV_NUM = "CUST_INV_NUM";
    /** DB Item Column Name */
    static final String CUST_INV_AMT = "CUST_INV_AMT";

    /****************************** 
     * Mail Information
     ******************************/
    /** Subject : CSA Payment Feed to CFS */
    static final String MAIL_SUB_NM = "CSA Payment Feed to CFS";
    /** Mail Group ID (From) */
    static final String CST_MAIL_GRP_ID_FROM = "FROM0003";
    /** Mail Key (From) */
    static final String CST_MAIL_KEY_1_FROM = "NFC";
    /** Mail Group ID (To) */
    static final String CST_MAIL_GRP_ID_TO = "NFCB3050";
    /** Mail Key (To) */
    static final String CST_MAIL_KEY_TO_1 = "01";
    /** Mail Key (To) */
    static final String CST_MAIL_KEY_TO_2 = "TO";
    /** Mail Group ID (CC) */
    static final String CST_MAIL_GRP_ID_CC = "NFCB3050";
    /** Mail Key (CC) */
    static final String CST_MAIL_KEY_CC_1 = "01";
    /** Mail Key (CC) */
    static final String CST_MAIL_KEY_CC_2 = "CC";
    /** Mail Group ID (BCC) */
    static final String CST_MAIL_GRP_ID_BCC = "NFCB3050";
    /** Mail Key (BCC) */
    static final String CST_MAIL_KEY_BCC_1 = "01";
    /** Mail Key (BCC) */
    static final String CST_MAIL_KEY_BCC_2 = "BCC";
    /** Mail Template ID */
    static final String ML_TMPL_ID = "NFCB3050M001";
    /** template parameter key : subject */
    static final String MAIL_TEMPLATE_KEY_SUBJECT = "subject";
    /** template parameter key : message */
    static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";
    /** line feed code */
    static final String LINE_FEED_CODE = "\r\n";
    /** Mail Group ID (From) */
    static final String MAIL_GRP_ID_FROM = "FROM0003";
    /** Mail Key (From) */
    static final String MAIL_KEY_1_FROM = "NFC";
    /** Email Attachment line format */
    static final String FORMAT_EMAIL_LINE = "%-17s%16s%-10s%16s%-6s%-12s";

}
