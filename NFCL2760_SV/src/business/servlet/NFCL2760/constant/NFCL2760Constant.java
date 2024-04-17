package business.servlet.NFCL2760.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/22   Fujitsu         H.Ikeda         Update          QC#12865
 * 2018/01/16   Fujitsu         T.Murai         Update          QC#20563
 * 2020/02/26   Fujitsu         H.Ikeda         Update          QC#55910
 * 2021/06/21   CITS            H.Dimay         Update          QC#58639
 * 2022/04/22   CITS            D.Mamaril       Update          QC#59333
 * 2022/08/23   Hitachi         A.Kohinata      Update          QC#60314
 * 2022/11/28	Hitachi			R.Takau			Update			QC#57252
 * 2023/06/13   Hitachi         S.Fujita        Update          QC#61487
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
    String SCRN_MODE_ENTRY = "1";

    /**
     */
    String SCRN_MODE_CANCEL = "2";

    /**
     */
    String SCRN_MODE_SUBMIT = "3";

    /**
     */
    String DETAIL_MODE_ONACCOUNT = "1";

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
    BigDecimal CENT = new BigDecimal("0.01");

    /**
     */
    String SCREEN_ID_NFCL2760 = "NFCL2760Scrn00";

    /**
     */
    String TABLE_ID1 = "A1";

    /**
     */
    String TABLE_ID2 = "A2";

    /**
     */
    String XX_CHK_BOX = "xxChkBox";

    /**
     */
    String PARAMETER_MODE = "2";

    /**
     */
    String UPLOARD_CSV_ID = "NFC0020001";

    /**
     */
    String CMN_CLOSE = "CMN_Close";

    /**
     */
    String MODE_0610 = "0";

    /**
     */
    String AR_CANCEL_LIMIT_MONTH = "AR_CANCEL_LIMIT_MONTH";

    /**
     */
    String MEIL_PARAM_MODE = "6";

    /**
     */
    String CANCELERROR_STATUS_Y = "Y";

    /**
     */
    String CANCELERROR_STATUS_N = "N";

    /** AR_RCPT_TRX_TP_CD_ADVANCE */
    String AR_RCPT_TRX_TP_CD_ADVANCE = "01";

    // 2018/01/16 QC#20563 add start
    /**
     * This transaction has pending cash application and this application will make balance minus. 
     */
    String NFCM0880E = "NFCM0880E";
    // 2018/01/16 QC#20563 add end

    // 2020/02/26 QC#55910 add start
    // 2022/08/23 QC#60314 del start
    ///**
    // */
    //int MAX_LINE_NUM = 19;
    // 2022/08/23 QC#60314 del end
    // 2020/02/26 QC#55910 add end
    
    /**
     * DATE_INFO enum.
     */
    enum DATE_INFO {
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
    enum PARAMS_NFCL5010 {
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
    enum PARAMS_NFCL5040 {
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
    enum PARAMS_NFCL5140 {
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
        /** LENGTH */
        , LENGTH(10)
        //START 2022/11/28 R.Takau [QC#57252, ADD]
        /** NUM_10 */
        , ADD_NUM_10(10)
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
    enum PARAMS_NFCL5050 {
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

    // Start 2016/12/22 H.Ikeda [QC#12865,ADD]
    /** Business ID */
    public static final String BIZ_ID = "NFCL2760";
    
    /** Function ID NFCL2760T020 (Update) */
    public static final String FUNC_ID_UPDATE = "NFCL2760T020";

    // START 2023/06/13 S.Fujita [QC#61487,ADD]
    /** Function ID NFCL2760T030 (Update for Collection Reps) */
    public static final String FUNC_ID_CLT_REPS = "NFCL2760T030";
    // END 2023/06/13 S.Fujita [QC#61487,ADD]

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";
    // End   2016/12/22 H.Ikeda [QC#12865,ADD]
}
