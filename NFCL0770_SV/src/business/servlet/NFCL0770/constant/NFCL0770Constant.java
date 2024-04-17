package business.servlet.NFCL0770.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Fujitsu         S.Takami        Create          QC#28289
 * 2022/11/28   Hitachi         R.Takau         Update          QC#57252
 * </pre>
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
    public static final String SCRN_MODE_ENTRY = "1";

    /**
     */
    public static final String SCRN_MODE_CANCEL = "2";

    /**
     */
    public static final String SCRN_MODE_SUBMIT = "3";

    /**
     */
    public static final String DETAIL_MODE_ONACCOUNT = "1";

    /**
     */
    public static final String DETAIL_MODE_DEDUCTION = "2";

    /**
     */
    public static final String DETAIL_MODE_ADJUSTMENT = "3";

    /**
     */
    public static final BigDecimal CENT = new BigDecimal("0.01");

    /**
     */
    public static final String SCREEN_ID_NFCL0770 = "NFCL0770Scrn00";

    /**
     */
    public static final String TABLE_ID1 = "A1";

    /**
     */
    public static final String TABLE_ID2 = "A2";

    /**
     */
    public static final String XX_CHK_BOX = "xxChkBox";

    /**
     */
    public static final String PARAMETER_MODE = "2";

    /**
     */
    public static final String UPLOARD_CSV_ID = "NFC0020001";

    /**
     */
    public static final String CMN_CLOSE = "CMN_Close";

    /**
     */
    public static final String MODE_0610 = "0";

    /**
     */
    public static final String AR_CANCEL_LIMIT_MONTH = "AR_CANCEL_LIMIT_MONTH";

    /**
     */
    public static final String MEIL_PARAM_MODE = "6";

    /**
     */
    public static final String CANCELERROR_STATUS_Y = "Y";

    /**
     */
    public static final String CANCELERROR_STATUS_N = "N";

    /** AR_RCPT_TRX_TP_CD_ADVANCE */
    public static final String AR_RCPT_TRX_TP_CD_ADVANCE = "01";

    /**
     * This transaction has pending cash application and this application will make balance minus.
     */
    public static final String NFCM0880E = "NFCM0880E";

    /**
     * DATE_INFO enum.
     */
    public enum DATE_INFO {
        /** YEAR_START_POS */
        YEAR_START_POS(0)
        /** YEAR_END_POS */
        , YEAR_END_POS(4)
        /** MONTH_START_POS */
        , MONTH_START_POS(4)
        /** MONTH_END_POS */
        , MONTH_END_POS(6);

        /** value */
        private int value;

        private DATE_INFO(int value) {
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
     * PARAMS_NFCL5010 class.
     */
    public enum PARAMS_NFCL5010 {
        /** NUM_0 */
        NUM_0(0)
        /** NUM_1 */
        , NUM_1(1)
        /** NUM_2 */
        , NUM_2(2)
        /** LENGTH */
        , LENGTH(3);

        /** value */
        private int value;

        private PARAMS_NFCL5010(int value) {
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
     * PARAMS_NFCL5040 class.
     */
    public enum PARAMS_NFCL5040 {
        /** NUM_0 */
        NUM_0(0)
        /** NUM_1 */
        , NUM_1(1)
        /** NUM_2 */
        , NUM_2(2)
        /** NUM_3 */
        , NUM_3(3)
        /** NUM_4 */
        , NUM_4(4)
        /** LENGTH */
        , LENGTH(5);

        /** value */
        private int value;

        private PARAMS_NFCL5040(int value) {
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
     * PARAMS_NFCL5140 class.
     */
    public enum PARAMS_NFCL5140 {
        /** NUM_0 */
        NUM_0(0)
        /** NUM_1 */
        , NUM_1(1)
        /** NUM_2 */
        , NUM_2(2)
        /** NUM_3 */
        , NUM_3(3)
        /** NUM_4 */
        , NUM_4(4)
        /** NUM_5 */
        , NUM_5(5)
        /** NUM_6 */
        , NUM_6(6)
        /** NUM_7 */
        , NUM_7(7)
        /** NUM_8 */
        , NUM_8(8)
        /** NUM_9 */
        , NUM_9(9)
        /** NUM_10 */
        , NUM_10(10)
        /** LENGTH */
        , LENGTH(11)
        //START 2022/11/28 R.Takau [QC#57252, ADD]
        /** NUM_11 */
        , ADD_NUM_11(11)
        /** LENGTH */
        , ADD_LENGTH(12);
       //END 2022/11/28 R.Takau [QC#57252, ADD]
        /** value */
        private int value;

        private PARAMS_NFCL5140(int value) {
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
     * PARAMS_NFCL5050 class.
     */
    public enum PARAMS_NFCL5050 {
        /** NUM_0 */
        NUM_0(0)
        /** NUM_1 */
        , NUM_1(1)
        /** NUM_2 */
        , NUM_2(2)
        /** NUM_3 */
        , NUM_3(3)
        /** NUM_4 */
        , NUM_4(4)
        /** NUM_5 */
        , NUM_5(5)
        /** NUM_6 */
        , NUM_6(6)
        /** NUM_7 */
        , NUM_7(7)
        /** NUM_8 */
        , NUM_8(8)
        /** NUM_9 */
        , NUM_9(9)
        /** NUM_10 */
        , NUM_10(10)
        /** NUM_11 */
        , NUM_11(11)
        /** NUM_12 */
        , NUM_12(12)
        /** NUM_13 */
        , NUM_13(13)
        /** LENGTH */
        , LENGTH(14);

        /** value */
        private int value;

        private PARAMS_NFCL5050(int value) {
            this.value = value;
        }

        /**
         * @return int
         */
        public int getValue() {
            return value;
        }
    }

    /** Record Status */
    public enum RECORD_STS {

        /** Applied */
        APPLIED("Applied")
        /** New */
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

    /** Business ID */
    public static final String BIZ_ID = "NFCL0770";

    /** Function ID NFCL0770T020 (Update) */
    public static final String FUNC_ID_UPDATE = "NFCL0770T020";

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";
}
