/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3160.constant;

/**
 *<pre>
 * Distribution Coordinator Work Bench
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/13   CITS            S.Katsuma       Create          N/A
 *</pre>
 */
public class NLBL3160Constant {

    /***************************************************
     * Function Button
     * [0]:Button Name [1]:Event Name [2]:Button Lavel
     ***************************************************/

    /** Function Button 1 */
    public static final String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Function Button 2 */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    public static final String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Function Button 4 */
    public static final String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Function Button 5 */
    public static final String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Function Button 6 */
    public static final String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_DETELE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Button Refresh */
    public static final String[] BTN_REFRESH = {"Refresh", "Refresh", "Refresh" };

    /** Screen ID */
    public static final String SCREEN_ID = "NLBL3160Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NLBL3160";

    /** Coordinator */
    public static final String FUNC_ID_COORD = BUSINESS_ID + "T010";

    /** Supervisor / Manager */
    public static final String FUNC_ID_SUPERVISOR_MANAGER = BUSINESS_ID + "T020";

    /** Logistics / IT */
    public static final String FUNC_ID_LOGI_IT = BUSINESS_ID + "T030";

    /** CLOSE */
    public static final String CLOSE = "CLOSE";

    /***************************************************
     * Event Name
     ***************************************************/

    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** Event Name: OpenWin_LocInfoRtlWh */
    public static final String EVENT_NM_OPENWIN_LOCINFO = "OpenWin_LocInfo";

    /***************************************************
     * Message
     ***************************************************/

    /** Please execute again after checking the warning field. */
//    public static final String NATM0001W = "NATM0001W";

    /***************************************************
     * Others
     ***************************************************/
    /** XX_SPL_CHAR_TXT */
    public static final String XX_SPL_CHAR_TXT = ";";

    /** SRCH_TXT_MAX */
    public static final int SRCH_TXT_MAX = 50;

    /**
     * Result Mode
     */
    public static enum RSLT_MODE {

        /** ORDER_INQUIRY */
        ORDER_INQUIRY("1", "Order Inquiry"),
        /** STATUS_SUMMARY */
        STATUS_SUMMARY("2", "Status Summary");
        /** rsltModeCd */
        String rsltModeCd;
        /** rsltModeNm */
        String rsltModeNm;

        private RSLT_MODE(String rsltModeCd, String rsltModeNm) {
            this.rsltModeCd = rsltModeCd;
            this.rsltModeNm = rsltModeNm;
        }

        /**
         * getRsltModeCd
         * @return String
         */
        public String getRsltModeCd() {
            return rsltModeCd;
        }

        /**
         * getRsltModeNm
         * @return String
         */
        public String getRsltModeNm() {
            return rsltModeNm;
        }
    }

    /**
     * Display Mode
     */
    public static enum DPLY_MODE {

        /** HEADER */
        HEADER("1", "Header"),
        /** CONFIG */
        CONFIG("2", "Config"),
        /** LINE */
        LINE("3", "Line");
        /** dplyModeCd */
        String dplyModeCd;
        /** dplyModeNm */
        String dplyModeNm;

        private DPLY_MODE(String dplyModeCd, String dplyModeNm) {
            this.dplyModeCd = dplyModeCd;
            this.dplyModeNm = dplyModeNm;
        }

        /**
         * getDplyModeCd
         * @return String
         */
        public String getDplyModeCd() {
            return dplyModeCd;
        }

        /**
         * getDplyModeNm
         * @return String
         */
        public String getDplyModeNm() {
            return dplyModeNm;
        }
    }
}
