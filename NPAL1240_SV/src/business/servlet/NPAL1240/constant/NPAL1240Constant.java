/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1240.constant;

/**
 * Business ID : NPAL1240 Qualifier Setup
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/25   CITS            M.Ouchi         Create          N/A
 * </pre>
 */
public class NPAL1240Constant {

    /**
     * The business Id.
     */
    public static final String BIZ_ID = "NPAL1240";

    /**
     * The screen Id.
     */
    public static final String SCRN_ID = "NPAL1240Scrn00";

    /**
     * The function code.
     */
    public static final String FUNC_CD_SEARCH = "20";

    // ---------- The guard condition. ----------

    /**
     * The guard condition of "CMN_Close".
     */
    public static final String GUARD_CONDITION_CMN_CLOSE = "CMN_Close";

    // ---------- The constant. ----------

    /**
     * The mode. (Inquiry : 1)
     */
    public static final String MODE_INQUIRY = "1";

    /**
     * The mode. (Update : 2)
     */
    public static final String MODE_UPDATE = "2";

    /**
     * ASL Qualifier Type. (CUSTOMER_SPECIFIC : 01)
     */
    public static final String ASL_QLFY_TP_CUSTOMER_SPECIFIC = "01";

    /**
     * ASL Qualifier Type. (BIG_DEAL_SPECIFIC : 02)
     */
    public static final String ASL_QLFY_TP_BIG_DEAL_SPECIFIC = "02";

    /**
     * The index for CUSTOMER_SPECIFIC.
     */
    public static final int INDEX_FOR_CUSTOMER_SPECIFIC = 2;

    /**
     * The index for BIG_DEAL_SPECIFIC.
     */
    public static final int INDEX_FOR_BIG_DEAL_SPECIFIC = 4;

    /**
     * The maximum number of input parameters
     */
    public static final int MAX_NUM_INPUT_PARAM = 3;

    /**
     * The index of input parameters : Mode.
     */
    public static final int INPUT_PARAM_INDEX_MODE = 0;

    /**
     * The index of input parameters : Global Company Code.
     */
    public static final int INPUT_PARAM_INDEX_GLBL_CMPY_CD = 1;

    /**
     * The index of input parameters : ASL Qualifier Relation List.
     */
    public static final int INPUT_PARAM_INDEX_ASL_QLFY_RELN_LIST = 2;

    // ---------- The display name. ----------
    /**
     * The display name of ASL Qualifier Type.
     */
    public static final String DISPLAY_NM_ASL_QLFY_TP = "Qualifier Type";

    /**
     * The display name of ASL Qualifier Type.
     */
    public static final String DISPLAY_NM_ASL_QLFY_REF_CD = "Reference#";

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
         * Common button 8 : CLEAR.
         */
        CLEAR("btn8", "CMN_Clear", "Clear"),

        /**
         * Common button 10 : CLOSE.
         */
        CLOSE("btn10", "CMN_Close", "Close");

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
         * Application button : Ref#.
         */
        REF_NUM("OnClick_RefNum"),

        /**
         * Application button : Insert Row.
         */
        INSERT_ROW("OnClick_InsertRow"),

        /**
         * Application button : Delete Row.
         */
        DELETE_ROW("OnClick_DeleteRow");

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
}
