/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFD.NFDB005001;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 04/27/2015   Fujitsu         Y.Kamide        Create          N/A
 * 08/07/2017   Hitachi         T.Tsuchida      Update          QC#19576
 * 2021/07/07   CITS            G.Delgado       Update          QC#58946
 *</pre>
 */
public class NFDB005001Constant {

    /** business_id : NFDB0030 */
    public static final String BUSINESS_ID = "NFDB0050";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Failed to update [@]. */
    public static final String NFDM0004E = "NFDM0004E";

    /**
     * The corresponding data does not exist.
     * <TABLE: [@], Data Key: [@]>
     */
    public static final String NFDM0005E = "NFDM0005E";

    /** The mail template cannot be acquired. <Template ID: [@]> */
    public static final String NFBM0184E = "NFBM0184E";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** INT_8 : 8 */
    public static final int INT_8 = 8;

    /** CONTR_HLD_STRTGY_NOTIFY_CLT_ITEM_CD */
    public static final String CONTR_HLD_STRTGY_NOTIFY_CLT_ITEM_CD = "11";

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
    public static final String MAIL_TMPL_ID = "NFDB0050M001";

    /** template parameter key : acctNum */
    public static final String MAIL_TMPL_KEY_ACCT_NUM = "acctNum";

    // START 2021/07/07 G.Delgado [QC#58946, MOD]
    // /** template parameter key : locNum */
    // public static final String MAIL_TMPL_KEY_LOC_NUM = "locNum";
    /** template parameter key : billToCustCd */
    public static final String MAIL_TMPL_KEY_BILL_TO_CUST_CD = "billToCustCd";
    // END 2021/07/07 G.Delgado [QC#58946, MOD]

    /** template parameter key : custTp */
    public static final String MAIL_TMPL_KEY_CUST_TP = "custTp";

    /** template parameter key : contractList */
    public static final String MAIL_TMPL_CREDIT_CONTR_LIST = "contractList";

    // START 2021/07/07 G.Delgado [QC#58946, ADD]
    /** Column name : Contract Number */
    public static final String COL_CONTRACT_NUMBER = "Contract Number";

    /** Column name : Start Date */
    public static final String COL_START_DATE = "Start Date";

    /** Column name : End Date */
    public static final String COL_END_DATE = "End Date";

    /** Number of tabs between each column */
    public static final int COL_TAB_SPACING = 2;

    /** Tab width to use for alignment */
    public static final int ALIGN_TAB_WIDTH = 4;
    // END 2021/07/07 G.Delgado [QC#58946, ADD]
}
