/**
 * <Pre>
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 * </Pre>
 */
package business.blap.NFDL0080.constant;

/**
 * <pre>
 * NFDL0080Constant.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/20   Fujitsu         M.Nakamura       Create          N/A
 * 2018/04/19   Hitachi         Y.Takeno        Update          QC#20940
 * 2019/12/24   Fujitsu         H.Ikeda         Update          QC#54619
 * 2020/04/16   CITS            R.Azucena       Update          QC#58672
 * </pre>
 */
public class NFDL0080Constant {

    /**
     * BusinessID("NFDL0080")
     */
    public static final String BUSINESS_ID = "NFDL0080";

    /**
     * ViewScale("2")
     */
    public static final int VIEW_SCALE = 2;

    /**
     * ReceiptNumberingKey("RC#")
     */
    public static final String BIZAPL_RCPTNUMKEY = "RC#";

    /**
     * ArTrxNum StartNumber(0)
     */
    public static final int AR_TRX_BAL_SQ_START_NUM = 0;

    /**
     * ArTrxbalSqRtnCd Normal("0")
     */
    public static final String AR_TRX_BAL_SQ_RTNCD_NORMAL = "0";

    /**
     * ZeroPadding Digit(4)
     */
    public static final int MAX_LENGTH_4 = 4;

    /**
     * ZeroPadding String("0")
     */
    public static final String PAD_STR_0 = "0";

    /**
     * ZeroPadding StartNumber("1")
     */
    public static final String STR_1 = "1";

    /**
     * checkBox name("xxChkBox_A1")
     */
    public static final String CHK_BOX_NAME = "xxChkBox_A1";

    /**
     * ApplyBatchAPI RtnCd-UnProcces("0")
     */
    public static final String APPLY_RTNCD_UN_PROCCES = "0";

    /**
     * ApplyBatchAPI RtnCd-Normal("1")
     */
    public static final String APPLY_RTNCD_NORMAL = "1";

    /**
     * ApplyBatchAPI RtnCd-CashappError("2")
     */
    public static final String APPLY_RTNCD_CASHAPP_ERROR = "2";

    /**
     * ApplyBatchAPI RtnCd-ExclusionError("3")
     */
    public static final String APPLY_RTNCD_EXCLUSION_ERROR = "3";

    /**
     * ApplyBatchAPI RtnCd-OthersError("4")
     */
    public static final String APPLY_RTNCD_OTHERS_ERROR = "4";

    /**
     * RCPT_BAT_NUM Key for VAR_CHAR_CONST("AR_PUR_RCPT_BAT_NUM")
     */
    public static final String AR_RCPT_CONST_KEY_RCPT_BAT_NUM = "AR_PUR_RCPT_BAT_NUM";

    /**
     * RCPT_BAT_SQ_NUM Key for
     * VAR_CHAR_CONST("AR_PUR_RCPT_BAT_SQ_NUM")
     */
    public static final String AR_RCPT_CONST_KEY_RCPT_BAT_SQ_NUM = "AR_PUR_RCPT_BAT_SQ_NUM";

    /**
     * AR_BANK_ACCT_CD Key for VAR_CHAR_CONST("AR_PUR_BANK_ACCT_CD")
     */
    public static final String AR_RCPT_CONST_KEY_AR_BANK_ACCT_CD = "AR_PUR_BANK_ACCT_CD";

    /**
     * AR_RCPT_TOC_CD Key for VAR_CHAR_CONST("AR_RCPT_TOC_CD")
     */
    public static final String AR_RCPT_CONST_KEY_AR_RCPT_TOC_CD = "AR_RCPT_TOC_CD";

    /**
     * AR_RCPT_PROD_CD Key for VAR_CHAR_CONST("AR_RCPT_PROD_CD")
     */
    public static final String AR_RCPT_CONST_KEY_AR_RCPT_PROD_CD = "AR_RCPT_PROD_CD";

    /**
     * AR_PUR_CHK_NUM Key for VAR_CHAR_CONST("AR_PUR_CHK_NUM")
     */
    public static final String AR_RCPT_CONST_KEY_AR_PUR_CHK_NUM = "AR_PUR_CHK_NUM";

    /**
     */
    public static final String AR_STD_EXCH_RATE = "AR_STD_EXCH_RATE";

    /**
     */
    public static final String BIZ_APP_ID = "NFDL0080";

    /**
     */
    public static final String FORMAT_DT = "yyyyMMdd";

    /**
     */
    public static final String FORMAT_TM = "HHmmss";

    /** Key name of AR_CASH_APP_ON_BAT_CNT */
    public static final String KEY_NAME_OF_AR_CASH_APP_ON_BAT_CNT = "AR_CASH_APP_ON_BAT_CNT";

    /**
     * AR_ADJ_TP_NON_OPERATING_MISC
     */
    public static final String AR_ADJ_TP_NON_OPERATING_MISC = "AR_ADJ_TP_NON_OPERATING_MISC";

    /**
     * AR_ADJ_TP_FREIGHT_OUT
     */
    public static final String AR_ADJ_TP_FREIGHT_OUT = "AR_ADJ_TP_FREIGHT_OUT";

    /**
     * AR_ADJ_TP_ACCRUED_SALES_TAX
     */
    public static final String AR_ADJ_TP_ACCRUED_SALES_TAX = "AR_ADJ_TP_ACCRUED_SALES_TAX";

    /** max download count */
    public static final int MAX_DOWNLOAD_CNT = 65000;

    // START 2018/04/19 [QC#20940, ADD]
    /** NFDL0080_CLT_DTL_NOTE_TXT */
    public static final String NFDL0080_CLT_DTL_NOTE_TXT = "NFDL0080_CLT_DTL_NOTE_TXT";

    /** MAX_LENGTH_CLT_DTL_NOTE_TXT*/
    public static final int MAX_LENGTH_CLT_DTL_NOTE_TXT = 4000;
    // END   2018/04/24 [QC#20940, ADD]

    // START 2020/01/31 [QC#54826, ADD]
    /** NFDL0080_AR_RCPT_CMNT_TXT */
    public static final String NFDL0080_AR_RCPT_MISC_CMNT_TXT = "NFDL0080_AR_RCPT_MISC_CMNT_TXT";

    /** MAX_LENGTH_AR_RCPT_MISC_CMNT_TXT*/
    public static final int MAX_LENGTH_AR_RCPT_MISC_CMNT_TXT = 1000;
    // END 2020/01/31 [QC#54826, ADD]

    // START 2019/12/24 [QC#54619, ADD]
    /**
     * Number of digits of Zero padding (digits 4)
     */
    public static final int BIZAPL_INT_NUM_4 = 4;

    /**
     * padding "0"
     */
    public static final String PAD_STR_ZERO = "0";
    // END   2019/12/24 [QC#54619, ADD]

    // START 2021/04/16 R.Azucena [QC#58672, ADD]
    /** The target transaction is already in Refund process. */
    public static final String NFDM0053E = "NFDM0053E";
    // END 2021/04/16 R.Azucena [QC#58672, ADD]
}
