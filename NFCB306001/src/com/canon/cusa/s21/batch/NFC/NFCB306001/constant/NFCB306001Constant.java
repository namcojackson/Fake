/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB306001.constant;

/**
 * <pre>
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/04/2016   Hitachi         J.Kim           Create          N/A
 * 05/11/2016   Hitachi         T.Tsuchida      Update          QC#7796
 * 07/05/2016   Hitachi         T.Tomita        Update          QC#10692
 * 2017/12/19   Hitachi         J.Kim           Update          QC#22199
 * </pre>
 */
public class NFCB306001Constant {

    /**
     * BATCH_PROGRAM_ID(NFCB3060)
     */
    public static final String BUSINESS_ID = "NFCB3060";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Max Commit Number */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /**
     * Error Msg : Global Company Code is required.
     */
    public static final String NFBM0199E = "NFBM0199E";

    /**
     * Error Msg : Data could not be added due to [@]. Please try again.
     */
    public static final String NFCM0002E = "NFCM0002E";

    /**
     * Error Msg : System error has occurred.  Transaction did not complete.
     */
    public static final String NFCM0025E = "NFCM0025E";

    /**
     * Error Msg : System error has occurred.
     */
    public static final String NFCM0032E = "NFCM0032E";

    /**
     * Error Msg : This data has been updated by another user.
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * Error Msg : @ is a mandatory field.
     */
    public static final String NFCM0504E = "NFCM0504E";

    /**
     * Error Msg : The data @ does not exist in the master.
     */
    public static final String NFCM0502E = "NFCM0502E";

    /** Service Machine Transaction Type Code Length */
    public static final int SVC_MACH_TRX_TP_CD_LEN = 20;

    /** Install Additional Comment Text Length */
    public static final int ISTL_ADDL_CMNT_TXT_LEN = 60;

    /** Substring Length : 12 */
    public static final int SUBSTRING_LEN12 = 12;

    /** Date Format(yyyyMMddHHmmss) */
    public static final String DT_FORMAT_TS = "yyyyMMdd";

    /**
     * Format YYYMMDDHHMMSSMMM
     */
    public static final String YYYYMMDDHHMMSSMMM = "yyyyMMddHHmmssSSS";

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
     * ZeroPadding Digit(8)
     */
    public static final int MAX_LENGTH_8 = 8;

    /**
     * ZeroPadding Digit(10)
     */
    public static final int MAX_LENGTH_10 = 10;

    /**
     * ZeroPadding Digit(15)
     */
    public static final int MAX_LENGTH_15 = 15;

    /**
     * ZeroPadding Str("0")
     */
    public static final String PAD_STR_0 = "0";

    /**
     * ZeroPadding StartNumber("1")
     */
    public static final String STR_1 = "1";

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
     * Deal Sequence Number
     */
    public static final String DEAL_SQ_NUM = "00000001";

    // START 2017/12/19 J.Kim [QC#22199,MOD]
    /**
     * AP Invoice Number
     */
    public static final String REFUND = "RFND";
    // END 2017/12/19 J.Kim [QC#22199,MOD]
}
