/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8840.constant;

/**
 *<pre>
 * NYEL8840Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/26   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NYEL8840Constant {

    /** Business ID */
    public static final String BIZ_ID = "NYEL8840";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NYEL8840Scrn00";

    // --------------------------------
    // Common button
    // --------------------------------
    public static enum CMN_BTN {

        /** F1 : Save */
        CMN_SAV("btn1", "CMN_Save", "Save")

        /** F2 : Submit */
        , CMN_SUB("btn2", "CMN_Submit", "Submit")

        /** F3 : Apply */
        , CMN_APL("btn3", "CMN_Apply", "Apply")

        /** F4 : Apply */
        , CMN_APR("btn4", "CMN_Approve", "Approve")

        /** F5 : Reject */
        , CMN_RJT("btn5", "CMN_Reject", "Reject")

        /** F6 : Download */
        , CMN_DWL("btn6", "CMN_Download", "Download")

        /** F7 : Delete */
        , CMN_DEL("btn7", "CMN_Delete", "Delete")

        /** F8 : Clear */
        , CMN_CLR("btn8", "CMN_Clear", "Clear")

        /** F9 : Reset */
        , CMN_RST("btn9", "CMN_Reset", "Reset")

        /** F10 : Return */
        , CMN_RTN("btn10", "CMN_Return", "Return")
        //
        , /* */;

        /** */
        private String btnName;

        /** */
        private String event;

        /** */
        private String label;

        private CMN_BTN(String btnName, String event, String label) {
            this.btnName = btnName;
            this.event = event;
            this.label = label;
        }

        /**
         * event
         * @return String
         */
        public String event() {
            return event;
        }

        /**
         * label
         * @return String
         */
        public String label() {
            return label;
        }

        /**
         * btnName
         * @return String
         */
        public String btnName() {
            return btnName;
        }
    }

    // -------------------------------
    /** number of popup */
    public static final int CMN_POPUP_PRM_NUM = 11;
}
