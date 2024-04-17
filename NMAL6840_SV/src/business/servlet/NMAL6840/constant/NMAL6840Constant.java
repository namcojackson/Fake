/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6840.constant;

/**
 * <p>
 * NMAL6840 Sub Warehouse Setup.
 * </p>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/27/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC# 2380
 * </pre>
 */
public class NMAL6840Constant {

    /**
     * The business Id.
     */
    public static final String BIZ_ID = "NMAL6840";

    /**
     * The screen Id.
     */
    public static final String SCRN_ID = "NMAL6840Scrn00";

    // ---------- The function code. ----------

    /**
     * The function code.
     */
    public static enum FUNC_CD {

        /**
         * Search.
         */
        SEARCH("20"),

        /**
         * Update.
         */
        UPDATE("40");

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

    /**
     * The minimum value of Retail Sub WH Sort Number.
     */
    public static final int RTL_SWH_SORT_NUM_MIN_VALUE = 1;

    /**
     * The maximum value of Retail Sub WH Sort Number.
     */
    public static final int RTL_SWH_SORT_NUM_MAX_VALUE = 999;

    /**
     * The minimum value of Merchandise Inventory Cost Percent.
     */
    public static final long MDSE_INVTY_COST_PCT_MIN_VALUE = 0L;

    /**
     * The maximum value of Merchandise Inventory Cost Percent.
     */
    public static final long MDSE_INVTY_COST_PCT_MAX_VALUE = 100L;

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
         * Application button : SEARCH.
         */
        SEARCH("OnClick_Search"),

        /**
         * Application button : INSERT_ROW.
         */
        INSERT_ROW("OnClick_InsertRow"),

        /**
         * Application button : DELETE_ROW.
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

    // ---------- The message code. ----------

    /**
     * If [@] is entered, please enter [@], too.
     */
    public static final String MESSAGE_CD_NMAM0049E = "NMAM0049E";

    /**
     * Please enter [Merchandise Inventory Cost Percent/Retail Sub
     * Warehouse Sort Number] between [0/1] and [100/999].
     */
    public static final String MESSAGE_CD_NMAM8093E = "NMAM8093E";

    /**
     * Retail Sub Warehouse Code is duplicated.
     */
    public static final String MESSAGE_CD_NMAM0072E = "NMAM0072E";

    /**
     * In case where [@] is [@], [@] is an invalid entry.
     */
    public static final String MESSAGE_CD_NMAM8148W = "NMAM8148W";

    // ---------- The display name. ----------

    /**
     * The display name : Retail Sub WH Code.
     */
    public static final String DISPLAY_NM_RTL_SWH_CD = "Retail Sub Warehouse Code";

    /**
     * The display name : Retail Sub WH Name.
     */
    public static final String DISPLAY_NM_RTL_SWH_NM = "Retail Sub Warehouse Name";

    /**
     * The display name : Retail Sub WH Description Text.
     */
    public static final String DISPLAY_NM_RTL_SWH_DESC_TXT = "Retail Sub Warehouse Description Text";

    /**
     * The display name : Merchandise Cost Type Code.
     */
    public static final String DISPLAY_NM_MDSE_COST_TP_CD = "Merchandise Cost Type Code";

    /**
     * The display name : Merchandise Inventory Cost Percent.
     */
    public static final String DISPLAY_NM_MDSE_INVTY_COST_PCT = "Merchandise Inventory Cost Percent";

    /**
     * The display name : Retail Sub WH Sort Number.
     */
    public static final String DISPLAY_NM_RTL_SWH_SORT_NUM = "Retail Sub Warehouse Sort Number";

    /**
     * The display name : Retail Sub WH Disabled Flag.
     */
    public static final String DISPLAY_NM_RTL_SWH_DSBL_FLG = "Retail Sub Warehouse Disabled Flag";

    /**
     * The display name : Mandatory Flag.
     */
    public static final String DISPLAY_NM_RTL_SWH_MND_FLG = "Mandatory Flag";

    // ------------------- Message Parameter -------------------

    /**
     * The message parameter : not 'Warehouse'
     */
    public static final String MESSAGE_PARAM_NOT_WH = "not 'Warehouse'";

    /**
     * The message parameter : Source WH/SWH
     */
    public static final String MESSAGE_PARAM_SRC_WH_SWH = "Source WH/SWH";
}
