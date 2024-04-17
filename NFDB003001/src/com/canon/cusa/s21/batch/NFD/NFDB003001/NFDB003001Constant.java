/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFD.NFDB003001;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 04/20/2015   Fujitsu         Y.Kamide        Create          N/A
 * 2021/07/01   CITS            G.Delgado       Update          QC#58909
 *</pre>
 */
public class NFDB003001Constant {

    /** business_id : NFDB0030 */
    public static final String BUSINESS_ID = "NFDB003001";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Failed to update [@]. */
    public static final String NFDM0004E = "NFDM0004E";

    /** The mail template cannot be acquired. <Template ID: [@]> */
    public static final String NFBM0184E = "NFBM0184E";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** NOTIFY_COLLECTOR_ITEM_CD */
    public static final String NOTIFY_COLLECTOR_ITEM_CD = "07";

    /** KEY_CLT_STRGY_MAIL_ROW_CNT */
    public static final String KEY_CLT_STRGY_MAIL_ROW_CNT = "CLT_STRGY_MAIL_ROW_CNT";

    /** AR_CLT_DEF_EML_ADDR */
    public static final String AR_CLT_DEF_EML_ADDR = "AR_CLT_DEF_EML_ADDR";
    
    /** MAX_LINE */
    public static final int MAX_ROW = 100;

    /** UPDATE_CNT */
    public static final int UPDATE_CNT = 100;

    /** DELIM */
    public static final String DELIM = ", ";

    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** template ID */
    public static final String MAIL_TMPL_ID = "NFDB0030M001";

    /** template parameter key : acctNum */
    public static final String MAIL_TMPL_KEY_ACCT_NUM = "acctNum";

    // START 2021/07/01 G.Delgado [QC#58909,MOD]
    // /** template parameter key : locNum */
    // public static final String MAIL_TMPL_KEY_LOC_NUM = "locNum";
    /** template parameter key : billToCustCd */
    public static final String MAIL_TMPL_KEY_BILL_TO_CUST_CD = "billToCustCd";
    // END 2021/07/01 G.Delgado [QC#58909,MOD]

    /** template parameter key : custTp */
    public static final String MAIL_TMPL_KEY_CUST_TP = "custTp";

    /** template parameter key : openCreditMemoList */
    public static final String MAIL_TMPL_CREDIT_MEMO_LIST = "openCreditMemoList";

    /** template parameter key : openPaymentList */
    public static final String MAIL_TMPL_CREDIT_PYMT_LIST = "openPaymentList";

    /** template parameter key : invoicesList */
    public static final String MAIL_TMPL_CREDIT_INV_LIST = "invoicesList";

    /** MORE_DATA_EXISTS_MSG */
    public static final String MORE_DATA_EXISTS_MSG = "more data exists...";
}
