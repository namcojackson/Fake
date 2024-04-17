package business.blap.NFCL0730.constant;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/17/2015   Fujitsu         T.Tanaka        Create          Initial
 * 2016/06/21   Hitachi         K.Kojima        Update          QC#10214
 * 2022/11/10   Hitachi         S.Naya          Update          QC#57252
 * 2023/01/27   Hitachi         T.Kuroda        Update          QC#61089
 *</pre>
 */
public interface NFCL0730Constant {
    
    /**
     * ReceiptNumberingKey("RC#")
     */
    String BIZAPL_RCPTNUMKEY = "RC#";

    /**
     * ZeroPadding Digit(4)
     */
    int MAX_LENGTH_4 = 4;

    /**
     * ZeroPadding Str("0")
     */
    String PAD_STR_0 = "0";

    /**
     * ZeroPadding StartNumber("1")
     */
    String STR_1 = "1";

    /**
     * ArTrxbalSqRtnCd Normal("0")
     */
    String AR_TRX_BAL_SQ_RTNCD_NORMAL = "0";

    /**
     * ArTrxNum StartNumber(0)
     */
    int AR_TRX_BAL_SQ_START_NUM = 0;


    /**
     * RCPT_BAT_NUM Key for VAR_CHAR_CONST("AR_PUR_RCPT_BAT_NUM")
     */
    String AR_RCPT_CONST_KEY_RCPT_BAT_NUM = "AR_PUR_RCPT_BAT_NUM";

    /**
     * RCPT_BAT_SQ_NUM Key for
     * VAR_CHAR_CONST("AR_PUR_RCPT_BAT_SQ_NUM")
     */
    String AR_RCPT_CONST_KEY_RCPT_BAT_SQ_NUM = "AR_PUR_RCPT_BAT_SQ_NUM";

    /**
     * AR_RCPT_TP_CD Key for VAR_CHAR_CONST("AR_PUR_RCPT_TRX_TP_CD")
     */
    String AR_RCPT_CONST_KEY_AR_RCPT_TRX_TP_CD = "AR_PUR_RCPT_TRX_TP_CD";

    /**
     * AR_RCPT_TP_CD Key for VAR_CHAR_CONST("AR_PUR_RCPT_TP_CD")
     */
    String AR_RCPT_CONST_KEY_AR_RCPT_TP_CD = "AR_PUR_RCPT_TP_CD";

    /**
     * AR_BANK_ACCT_CD Key for VAR_CHAR_CONST("AR_PUR_BANK_ACCT_CD")
     */
    String AR_RCPT_CONST_KEY_AR_BANK_ACCT_CD = "AR_PUR_BANK_ACCT_CD";

    /**
     * AR_RCPT_TOC_CD Key for VAR_CHAR_CONST("AR_RCPT_TOC_CD")
     */
    String AR_RCPT_CONST_KEY_AR_RCPT_TOC_CD = "AR_RCPT_TOC_CD";

    /**
     * AR_RCPT_PROD_CD Key for VAR_CHAR_CONST("AR_RCPT_PROD_CD")
     */
    String AR_RCPT_CONST_KEY_AR_RCPT_PROD_CD = "AR_RCPT_PROD_CD";

    /**
     * AR_PUR_CHK_NUM Key for VAR_CHAR_CONST("AR_PUR_CHK_NUM")
     */
    String AR_RCPT_CONST_KEY_AR_PUR_CHK_NUM = "AR_PUR_CHK_NUM";

    /**
     * ApplyBatchAPI RtnCd-UnProcces("0")
     */
    String APPLY_RTNCD_UN_PROCCES = "0";

    /**
     * ApplyBatchAPI RtnCd-Normal("1")
     */
    String APPLY_RTNCD_NORMAL = "1";

    /**
     * ApplyBatchAPI RtnCd-CashappError("2")
     */
    String APPLY_RTNCD_CASHAPP_ERROR = "2";

    /**
     * ApplyBatchAPI RtnCd-ExclusionError("3")
     */
    String APPLY_RTNCD_EXCLUSION_ERROR = "3";

    /**
     * ApplyBatchAPI RtnCd-OthersError("4")
     */
    String APPLY_RTNCD_OTHERS_ERROR = "4";

    // START 2016/06/21 K.Kojima [QC#10214,ADD]
    /** Message ID : NFCM0845E */
    public static String NFCM0845E = "NFCM0845E";
    // END 2016/06/21 K.Kojima [QC#10214,ADD]
    // START 2022/11/10 S.Naya [QC#57252,ADD]
    /** @ is invalid. */
    static final String NFCM0833E = "NFCM0833E";

    /** Other Code */
    public static final String OTHER_CODE = "999";
    // END   2022/11/10 S.Naya [QC#57252,ADD]

    // START 2023/01/27 T.Kuroda [QC#61089, ADD]
    /** Business Application ID */
    public static final String BIZ_ID = "NFCL0730";
    // END   2023/01/27 T.Kuroda [QC#61089, ADD]

}