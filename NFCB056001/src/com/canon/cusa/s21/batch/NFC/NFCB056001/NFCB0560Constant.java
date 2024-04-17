package com.canon.cusa.s21.batch.NFC.NFCB056001;

public interface NFCB0560Constant {

    /** Bulk Insert Count */
    int BULK_CNT = 1000;

    /** Message Id */
    String NFCM0501E = "NFCM0501E";

    /** Message Id */
    String NFDM0003E = "NFDM0003E";

    /** Message Id */
    String NFCM0638E = "NFCM0638E";

    /** Message Id */
    String NFDM0013E = "NFDM0013E";

    /** */
    String FUNC_ID = "NFCB0560";

    /** DB field */
    String CR_CARD_SETL_DT = "CR_CARD_SETL_DT";

    /** DB field */
    String AR_TRX_NUM = "AR_TRX_NUM";

    /** DB field */
    String GRP_INV_NUM = "GRP_INV_NUM";

    /** DB field */
    String DEAL_RMNG_BAL_GRS_AMT = "DEAL_RMNG_BAL_GRS_AMT";

    /** DB field */
    String DEAL_ORIG_GRS_AMT = "DEAL_ORIG_GRS_AMT";

    /** DB field */
    String BILL_TO_CUST_ACCT_CD = "BILL_TO_CUST_ACCT_CD";

    /** DB field */
    String CR_CARD_AUTH_REF_NUM = "CR_CARD_AUTH_REF_NUM";

    /** DB field */
    String CR_CARD_AUTH_AMT = "CR_CARD_AUTH_AMT";

    /** DB field */
    String CR_CARD_TRX_PK = "CR_CARD_TRX_PK";

    /** DB field */
    String AR_TRX_BAL_PK = "AR_TRX_BAL_PK";
    // START 2018/07/27 E.Kameishi [QC#27419, ADD]
    /** CONST KEY Field **/
    public static final String AR_RCPT_CHK_NUM_HDR = "IEX_";

    /**
     * ReceiptCheckNumberingKey("AR_RCPT_CHK_NUM")
     */
    public static final String AUTO_SQ_EXTN_KEY_AR_RCPT_CHK_NUM = "AR_RCPT_CHK_NUM";
    // END 2018/07/27 E.Kameishi [QC#27419, ADD]

}
