/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0080.constant;

/**
 * <pre>
 * NFDL0080Constant.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/20   Fujitsu         M.Nakamura       Create          N/A
 * 2018/01/18   Fujitsu         T.Murai          Update          S21_NA#20563
 * </pre>
 */
public class NFDL0080Constant {

    /**
     * BusinessID:"NFDL0080"
     */
    public static final String BUSINESS_ID = "NFDL0080";

    /**
     * ScreenID:"NFDL0080Scrn00"
     */
    public static final String SCREEN_ID = "NFDL0080Scrn00";

    /**
     * DetailMaxCnt
     */
    public static final int DTL_LIMIT = 9999;

    // Add Start 2018/01/16 S21_NA#20563
    /**
     * This transaction has pending cash application and this application will make balance minus.
     */
    public static final String NFCM0880E = "NFCM0880E";
    // Add Start 2018/01/16 S21_NA#20563

    /** DATE_INFO enum. */
    public static enum DATE_INFO {
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
}
