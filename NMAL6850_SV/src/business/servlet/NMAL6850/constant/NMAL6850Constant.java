/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6850.constant;

/**
 * <pre>
 * NMAL6850 Supplier Search.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/24   CITS            M.Ouchi         Create          N/A
 * 2020/02/28   Fujitsu         C.Hara          Update          QC#55971
 * </pre>
 */
public class NMAL6850Constant {

    /**
     * The business Id.
     */
    public static final String BIZ_ID = "NMAL6850";

    /**
     * The screen Id.
     */
    public static final String SCRN_ID = "NMAL6850Scrn00";

    /**
     * The function code. (UPDATE)
     */
    public static final String FUCTION_CODE_UPDATE = "NMAL6850T020";

    // 2020/02/28 QC#55971 Add Start
    /**
     * The event name of "CMN_Close".
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";
    // 2020/02/28 QC#55971 Add End

    // ---------- The function code. ----------

    /**
     * The function code.
     */
    public static enum FUNC_CD {

        /**
         * Search.
         */
        SEARCH("20");

        /**
         * The function code.
         */
        private String code;

        /**
         * Constructor.
         * @param code the function code
         */
        private FUNC_CD(String code) {
            this.code = code;
        }

        /**
         * Gets the code.
         * @return the code.
         */
        public String getCode() {
            return code;
        }
    }

    // ---------- The button definition. ----------

    /**
     * The button status.
     */
    public static enum BTN_STATUS {
        /**
         * enabled.
         */
        ENABLED(1, true),

        /**
         * disabled.
         */
        DISABLED(0, false);

        /**
         * The status.
         */
        private int status;

        /**
         * The status.
         */
        private boolean enabled;

        /**
         * Constructor.
         * @param status the status.
         * @param enabled is enabled
         */
        private BTN_STATUS(int status, boolean enabled) {
            this.status = status;
            this.enabled = enabled;
        }

        /**
         * Gets the status.
         * @return the status.
         */
        public int getStatus() {
            return status;
        }

        /**
         * Check if is enabled.
         * @return is enabled
         */
        public boolean isEnabled() {
            return enabled;
        }
    }

    /**
     * The common button.
     */
    public static enum BTN_CMN {

        /**
         * Common button 1 : SAVE.
         */
        SAVE("btn1", "CMN_Save", "Save"),

        /**
         * Common button 2 : SUBMIT.
         */
        SUBMIT("btn2", "CMN_Submit", "Submit"),

        /**
         * Common button 3 : APPLY.
         */
        APPLY("btn3", "CMN_Apply", "Apply"),

        /**
         * Common button 4 : APPROVE.
         */
        APPROVE("btn4", "CMN_Approve", "Approve"),

        /**
         * Common button 5 : REJECT.
         */
        REJECT("btn5", "CMN_Reject", "Reject"),

        /**
         * Common button : DOWNLOAD.
         */
        DOWNLOAD("btn6", "CMN_Download", "Download"),

        /**
         * Common button 7 : DELETE.
         */
        DELETE("btn7", "CMN_Delete", "Delete"),

        /**
         * Common button 8 : CLEAR.
         */
        CLEAR("btn8", "CMN_Clear", "Clear"),

        /**
         * Common button 9 : RESET.
         */
        RESET("btn9", "CMN_Reset", "Reset"),

        /**
         * Common button 10 : RETURN.
         */
        RETURN("btn10", "CMN_Return", "Return");

        /**
         * The button name.
         */
        private String buttonName;

        /**
         * The event name.
         */
        private String eventName;

        /**
         * The button label.
         */
        private String label;

        /**
         * <p>
         * Constructor.
         * </p>
         * @param buttonName the button name
         * @param eventName the event name
         * @param label the label
         */
        private BTN_CMN(String buttonName, String eventName, String label) {
            this.buttonName = buttonName;
            this.eventName = eventName;
            this.label = label;
        }

        /**
         * Gets the button name.
         * @return the button name
         */
        public String getButtonName() {
            return buttonName;
        }

        /**
         * Gets the event name
         * @return the event name
         */
        public String getEventName() {
            return eventName;
        }

        /**
         * Gets the label.
         * @return the label
         */
        public String getLabel() {
            return label;
        }
    }

    /**
     * The application button.
     */
    public static enum BTN_APP {

        /**
         * Application button : Search.
         */
        SEARCH("OnClick_Search"),

        /**
         * Application button : New.
         */
        NEW("OnClick_New");

        /**
         * The name.
         */
        private String name;

        /**
         * <p>
         * Constructor.
         * </p>
         * @param name the name
         */
        private BTN_APP(String name) {
            this.name = name;
        }

        /**
         * Gets the event name
         * @return the event name
         */
        public String getName() {
            return name;
        }
    }

    // ---------- The display name. ----------

    /**
     * The display name : Supplier Number.
     */
    public static final String DISPLAY_NM_PRNT_VND_CD = "Supplier Number";

    /**
     * The display name : Supplier Name.
     */
    public static final String DISPLAY_NM_SPLY_NM = "Supplier Name";

    /**
     * The display name : Supplier Type.
     */
    public static final String DISPLAY_NM_SPLY_TP = "Supplier Type";

    /**
     * The display name : TaxPayer Reg No.
     */
    public static final String DISPLAY_NM_TAX_PAYER_REG_NUM = "TaxPayer Reg No";

    /**
     * The display name : Inactive On.
     */
    public static final String DISPLAY_NM_INAC_DT = "Inactive On";

    /**
     * The display name : Payment Terms.
     */
    public static final String DISPLAY_NM_PMT_TERM_DESC_TXT = "Payment Terms";

    /**
     * The display name : Payment Method.
     */
    public static final String DISPLAY_NM_VND_PMT_METH_DESC_TXT = "Payment Method";

    /**
     * The display name : ARCS Supplier Number.
     */
    public static final String DISPLAY_NM_ARCS_SPLY_NUM = "ARCS Supplier Number";
}
