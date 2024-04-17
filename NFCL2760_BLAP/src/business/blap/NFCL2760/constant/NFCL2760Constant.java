package business.blap.NFCL2760.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2021/06/21   CITS            H.Dimay         Update          QC#58639
 * 2021/06/24   CITS            D.Morimoto      Update          QC#57729
 * 2022/01/06   CITS            G.Delgado       Update          QC#59329
 * 2022/04/22   CITS            D.Mamaril       Update          QC#59333
 * 2022/11/30	Hitachi			R.Takau			Update			QC#57252
 * </pre>
 */
public interface NFCL2760Constant {
    /**
     */
    String SCRN_STATUS_Y = "Y";

    /**
     */
    String SCRN_STATUS_N = "N";

    /**
     */
    String MODE_ENTRY = "1";

    /**
     */
    String MODE_CANCEL = "2";

    /**
     */
    String MODE_ONE = "3";

    /**
     */
    String DETAIL_MODE_ONACOUNT = "1";

    /**
     */
    String DETAIL_MODE_DEDUCTION = "2";

    /**
     */
    String DETAIL_MODE_ADJUSTMENT = "3";

    // START 2021/06/21 H.Dimay [QC#58639, ADD]
    /** ENTRY DETAIL REFUND */
    String DETAIL_MODE_REFUND = "4";
    // END 2021/06/21 H.Dimay [QC#58639, ADD]

    // START 2022/04/22 D.Mamaril [QC#59333,ADD]
    /** ENTRY DETAIL REFUND ONGOING */
    String DETAIL_MODE_REFUND_ONGOING = "5";
    // END 2022/04/22 D.Mamaril [QC#59333,ADD]

    /**
     */
    String MODE_NAME_ENTRY = "Entry Cash Application";

    /**
     */
    String MODE_NAME_CANCEL = "Cancel Cash Application";

    /**
     */
    String MODE_NAME_ONE = "Cash Application";

    /**
     */
    String AR_SUB_SYS_ID = "AR_SUB_SYS_ID";

    /**
     */
    String ONL_BAT_TP_CD = "1";

    /**
     */
    String CSV = ".csv";

    /**
     */
    String XX_CHK_BOX = "xxChkBox";

    /**
     */
    int SCALE_2 = 2;

    /**
     */
    int YEAR_INDEX = 0;

    /**
     */
    int MONTH_INDEX = 1;

    /**
     */
    String API_RTNCD_UNPROCESSING = "0";

    /**
     */
    String API_RTNCD_NORMAL = "1";

    /**
     */
    String API_RTNCD_CASH_APPLICATION_ERROR = "2";

    /**
     */
    String API_RTNCD_EXCLUSION_ERROR = "3";

    /**
     */
    String API_RTNCD_OTHERS_ERROR = "4";

    /**
     */
    String MANUAL_CASH_APPLICATION_ENTRY = "Manual Cash Application Entry";

    /**
     */
    String MANUAL_CASH_APPLICATION_CANCEL = "Manual Cash Application Cancel";

    /**
     */
    String UNPROCESSING = "Unprocessing";

    /**
     */
    String CASH_APPLICATION_ERROR = "Cash Application Error";

    /**
     */
    String EXCLUSION_ERROR = "Exclusion Error";

    /**
     */
    String OTHERS_ERROR = "Others Error";

    /**
     */
    String AR_RCPT_TP_PRM_NM = "AR_RCPT_TP";

    /**
     */
    String AR_RCPT_TRX_TP_PRM_NM = "AR_RCPT_TRX_TP";

    /**
     */
    String AR_ADJ_TP_PRM_NM = "AR_ADJ_TP";

    /**
     */
    BigDecimal MAX_AMOUNT_DISPLAY = new BigDecimal("999999999999999.9999");

    /**
     */
    BigDecimal MIN_AMOUNT_DISPLAY = new BigDecimal("-999999999999999.9999");

    /**
     */
    String DATE_TYPE_YYYYMMDD = "yyyyMMdd";

    /**
     */
    String DATE_TYPE_MMDDYYYY = "MM/dd/yyyy";

    /**
     * Number of digits of Zero padding (digits 4)
     */
    int BIZAPL_INT_NUM_4 = 4;

    /**
     * Number of digits of Zero padding (digits 8)
     */
    int BIZAPL_INT_NUM_8 = 8;

    /**
     * padding "0"
     */
    String PAD_STR_ZERO = "0";

    /**
     * 
     */
    int MAX_LINE_DISPLAY = 9999;

    /**
     * 
     */
    String AR_CSV_UPLD_CASHAPP_KEY = "AR_CSV_UPLD_CASHAPP";

    /**
     */
    String BIZ_APP_ID = "NFCL2660";

    /**
     */
    String FORMAT_DT = "yyyyMMdd";

    /**
     */
    String FORMAT_TM = "HHmmss";

    /**
     */
    String CANCELERROR_STATUS_Y = "Y";

    /**
     */
    String CANCELERROR_STATUS_N = "N";

    /**
     */
    String CMN_CLOSE = "CMN_Close";

    /** AR_RCPT_TRX_TP_CD:01 Advance Report */
    String AR_RCPT_TRX_TP_CD_ADVANCE = "01";

    // 2021/06/24 QC#57729 Add START
    /** AR_RCPT_TRX_TP_CD:03 Offset */
    String AR_RCPT_TRX_TP_CD_OFFSET = "03";
    // 2021/06/24 QC#57729 Add END

    /**
     */
    String SCRN_MODE_ENTRY = "1";

    /**
     */
    String SCRN_MODE_CANCEL = "2";

    /**
     */
    String BIZAPL_DAY_00 = "00";

    /**
     */
    int BIZAPL_INT_NUM_0 = 0;

    /**
     */
    int BIZAPL_INT_NUM_1 = 1;

    /**
     */
    int BIZAPL_INT_NUM_2 = 2;

    /**
     */
    int BIZAPL_INT_NUM_6 = 6;

    /**
     */
    int BIZAPL_INT_NUM_12 = 12;

    // 2020/01/15 QC#54902 mod start
    // 2017/11/15 QC#21402 add start
    // START 2022/01/06 G.Delgado [QC#59329, DEL]
//    /**
//     */
//    //int INIT_DETAIL_COUNT = 10;
//    int INIT_DETAIL_COUNT = 20;
    // END 2022/01/06 G.Delgado [QC#59329, DEL]
    // 2017/11/15 QC#21402 add end
    // 2020/01/15 QC#54902 mod end

    /**
     * AR_ADJ_TP_NON_OPERATING_MISC
     */
    String AR_ADJ_TP_NON_OPERATING_MISC = "AR_ADJ_TP_NON_OPERATING_MISC";
    
    /**
     * AR_ADJ_TP_FREIGHT_OUT
     */
    String AR_ADJ_TP_FREIGHT_OUT = "AR_ADJ_TP_FREIGHT_OUT";
    
    /**
     * AR_ADJ_TP_ACCRUED_SALES_TAX
     */
    String AR_ADJ_TP_ACCRUED_SALES_TAX = "AR_ADJ_TP_ACCRUED_SALES_TAX";

    /** Key name of AR_CASH_APP_ON_BAT_CNT */
    String KEY_NAME_OF_AR_CASH_APP_ON_BAT_CNT = "AR_CASH_APP_ON_BAT_CNT";

    /** Key name of AR_CASH_APP_ON_BAT_CNT */
    String CASH_DISC_SHCD_MIN = "00000000";

    /**
     * ADD_LINE enum.
     */
    enum ADD_LINE {

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

    enum RECORD_STS {

        APPLIED("Applied")
        ,NEW("New");

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

    enum ROW_STS {
         CASH_APPLICATION
        ,CANCEL
        ,DELETE
        ,NOTHING
        ,ALL
    }
    // Start 2022/11/30 R.Takau [QC#57252, ADD]
    public static final String OTHER_CODE = "998";
    // End 2022/11/30 R.Takau [QC#57252, ADD]
}
