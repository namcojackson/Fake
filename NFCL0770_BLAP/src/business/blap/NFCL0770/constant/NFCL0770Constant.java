package business.blap.NFCL0770.constant;

import java.math.BigDecimal;

/**
 * @author q05163
 * Static Constant class for NFCL0770
 */
public class NFCL0770Constant {
    /**
     */
    public static final String SCRN_STATUS_Y = "Y";

    /**
     */
    public static final String SCRN_STATUS_N = "N";

    /**
     */
    public static final String MODE_ENTRY = "1";

    /**
     */
    public static final String MODE_CANCEL = "2";

    /**
     */
    public static final String MODE_ONE = "3";

    /**
     */
    public static final String DETAIL_MODE_ONACOUNT = "1";

    /**
     */
    public static final String DETAIL_MODE_DEDUCTION = "2";

    /**
     */
    public static final String DETAIL_MODE_ADJUSTMENT = "3";

    /**
     */
    public static final String MODE_NAME_ENTRY = "Entry Cash Application";

    /**
     */
    public static final String MODE_NAME_CANCEL = "Cancel Cash Application";

    /**
     */
    public static final String MODE_NAME_ONE = "Cash Application";

    /**
     */
    public static final String AR_SUB_SYS_ID = "AR_SUB_SYS_ID";

    /**
     */
    public static final String ONL_BAT_TP_CD = "1";

    /**
     */
    public static final String CSV = ".csv";

    /**
     */
    public static final String XX_CHK_BOX = "xxChkBox";

    /**
     */
    public static final int SCALE_2 = 2;

    /**
     */
    public static final int YEAR_INDEX = 0;

    /**
     */
    public static final int MONTH_INDEX = 1;

    /**
     */
    public static final String API_RTNCD_UNPROCESSING = "0";

    /**
     */
    public static final String API_RTNCD_NORMAL = "1";

    /**
     */
    public static final String API_RTNCD_CASH_APPLICATION_ERROR = "2";

    /**
     */
    public static final String API_RTNCD_EXCLUSION_ERROR = "3";

    /**
     */
    public static final String API_RTNCD_OTHERS_ERROR = "4";

    /**
     */
    public static final String MANUAL_CASH_APPLICATION_ENTRY = "Manual Cash Application Entry";

    /**
     */
    public static final String MANUAL_CASH_APPLICATION_CANCEL = "Manual Cash Application Cancel";

    /**
     */
    public static final String UNPROCESSING = "Unprocessing";

    /**
     */
    public static final String CASH_APPLICATION_ERROR = "Cash Application Error";

    /**
     */
    public static final String EXCLUSION_ERROR = "Exclusion Error";

    /**
     */
    public static final String OTHERS_ERROR = "Others Error";

    /**
     */
    public static final String AR_RCPT_TP_PRM_NM = "AR_RCPT_TP";

    /**
     */
    public static final String AR_RCPT_TRX_TP_PRM_NM = "AR_RCPT_TRX_TP";

    /**
     */
    public static final String AR_ADJ_TP_PRM_NM = "AR_ADJ_TP";

    /**  */
    public static final String AR_RCPT_STS_NM = "AR_RCPT_STS";

    /**  */
    public static final String AR_RCPT_SRC_NM = "AR_RCPT_SRC";
    /**
     */
    public static final BigDecimal MAX_AMOUNT_DISPLAY = new BigDecimal("999999999999999.9999");

    /**
     */
    public static final BigDecimal MIN_AMOUNT_DISPLAY = new BigDecimal("-999999999999999.9999");

    /**
     */
    public static final String DATE_TYPE_YYYYMMDD = "yyyyMMdd";

    /**
     */
    public static final String DATE_TYPE_MMDDYYYY = "MM/dd/yyyy";

    /**
     * Number of digits of Zero padding (digits 4)
     */
    public static final int BIZAPL_INT_NUM_4 = 4;

    /**
     * Number of digits of Zero padding (digits 8)
     */
    public static final int BIZAPL_INT_NUM_8 = 8;

    /**
     * padding "0"
     */
    public static final String PAD_STR_ZERO = "0";

    /**
     *
     */
    public static final int MAX_LINE_DISPLAY = 9999;

    /**
     *
     */
    public static final String AR_CSV_UPLD_CASHAPP_KEY = "AR_CSV_UPLD_CASHAPP";

    /**
     */
    public static final String BIZ_APP_ID = "NFCL2660";

    /**
     */
    public static final String FORMAT_DT = "yyyyMMdd";

    /**
     */
    public static final String FORMAT_TM = "HHmmss";

    /**
     */
    public static final String CANCELERROR_STATUS_Y = "Y";

    /**
     */
    public static final String CANCELERROR_STATUS_N = "N";

    /**
     */
    public static final String CMN_CLOSE = "CMN_Close";

    /** AR_RCPT_TRX_TP_CD:01 Advance Report */
    public static final String AR_RCPT_TRX_TP_CD_ADVANCE = "01";

    /**
     */
    public static final String SCRN_MODE_ENTRY = "1";

    /**
     */
    public static final String SCRN_MODE_CANCEL = "2";

    /**
     */
    public static final String BIZAPL_DAY_00 = "00";

    /**
     */
    public static final int BIZAPL_INT_NUM_0 = 0;

    /**
     */
    public static final int BIZAPL_INT_NUM_1 = 1;

    /**
     */
    public static final int BIZAPL_INT_NUM_2 = 2;

    /**
     */
    public static final int BIZAPL_INT_NUM_6 = 6;

    /**
     */
    public static final int BIZAPL_INT_NUM_12 = 12;

    /**
     */
    public static final int INIT_DETAIL_COUNT = 10;

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

    /** Key name of AR_CASH_APP_ON_BAT_CNT */
    public static final String KEY_NAME_OF_AR_CASH_APP_ON_BAT_CNT = "AR_CASH_APP_ON_BAT_CNT";

    /** Key name of AR_CASH_APP_ON_BAT_CNT */
    public static final String CASH_DISC_SHCD_MIN = "00000000";

    /** Default Receipt Number */
    public static final String DEF_RECEIPT_NUM = "0000000000";

    /**
     * ReceiptNumberingKey("RC#")
     */
    public static final String BIZAPL_RCPTNUMKEY = "RC#";

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

    /**  */
    public static final String AR_STD_EXCH_RATE = "AR_STD_EXCH_RATE";

    /**
     * ZeroPadding StartNumber("1")
     */
    public static final String STR_1 = "1";

    /**
     * ZeroPadding Digit(4)
     */
    public static final int MAX_LENGTH_4 = 4;

    /**
     * ZeroPadding String("0")
     */
    public static final String PAD_STR_0 = "0";

    /**
     * ArTrxNum StartNumber(0)
     */
    public static final int AR_TRX_BAL_SQ_START_NUM = 0;

    /**
     * ArTrxbalSqRtnCd Normal("0")
     */
    public static final String AR_TRX_BAL_SQ_RTNCD_NORMAL = "0";

    /**
     * ADD_LINE enum.
     */
    public enum ADD_LINE {

        /** NUM_OF_ADD_LINE_ONE */
        NUM_OF_ADD_LINE_ONE(1)
        /** NUM_OF_ADD_LINE_FIVE */
        , NUM_OF_ADD_LINE_FIVE(5)
        /** NUM_OF_ADD_LINE_TEN */
        , NUM_OF_ADD_LINE_TEN(10);

        /** value */
        private int value;

        private ADD_LINE(int value) {
            this.value = value;
        }

        /**
         * @return int
         */
        public int getValue() {
            return value;
        }

    }

    /**
     * Record Status
     */
    public enum RECORD_STS {

        /**  */
        APPLIED("Applied")
        /**  */
        , NEW("New");

        /** value */
        private String value;

        private RECORD_STS(String value) {
            this.value = value;
        }

        /**
         * @return String
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * Row Status
     */
    public enum ROW_STS {
        /**  */
         CASH_APPLICATION
        /**  */
        , CANCEL
        /**  */
        , DELETE
        /**  */
        , NOTHING
        /**  */
        , ALL
    }
    
    // Start 2022/11/30 R.Takau [QC#57252, ADD]
    public static final String OTHER_CODE = "998";
    // End 2022/11/30 R.Takau [QC#57252, ADD]
}
