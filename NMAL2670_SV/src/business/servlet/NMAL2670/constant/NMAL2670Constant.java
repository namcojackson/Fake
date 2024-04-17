/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2670.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL2670Constant {

    /** Screen ID */
    public static final String SCREEN_ID = "NMAL2670Scrn00";

    /** Business ID */
    public static final String APP_ID = "NMAL2670";

    /** Function Code */
    public static final String FUNCTION_CODE = "20";

    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** MESSAGE KIND ERROR */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Button Download */
    public static final String BTN_DOWNLOAD = "Download";

    /**
     * Common button An attribute
     */
    public enum CMN_BTN {

        /** CLEAR **/
        CLEAR("btn8", "CMN_Clear", "Clear"), CLOSE("btn10", "CMN_Close", "Close");

        /** htmlNm **/
        private String htmlNm;

        /** eventNm **/
        private String eventNm;

        /** label **/
        private String label;

        private CMN_BTN(String htmlNm, String eventNm, String label) {
            this.htmlNm = htmlNm;
            this.eventNm = eventNm;
            this.label = label;
        }

        /**
         * @return String
         */
        public String getHtmlNm() {
            return htmlNm;
        }

        /**
         * @return String
         */
        public String getEventNm() {
            return eventNm;
        }

        /**
         * @return String
         */
        public String getLabel() {
            return label;
        }
    }
}
