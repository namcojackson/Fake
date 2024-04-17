/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0610.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * PI Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NLCL0610Constant {

    /**
     * The business Id.
     */
    public static final String BIZ_ID = "NLCL0610";

    /**
     * The screen Id.
     */
    public static final String SCRN_ID = "NLCL0610Scrn00";

    /**
     * The function code. (UPDATE)
     */
    public static final String FUCTION_CODE_UPDATE = "NLCL0610T020";
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
         * Application button : Cancel.
         */
        CANCEL("OnClick_Cancel"),

        /**
         * Application button : Edit.
         */
        EDIT("OnClick_Edit");

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

    /** Please select at least 1 checkbox. */
    public static final String NZZM0011E = "NZZM0011E";
    /** The field of [@] is not input. */
    public static final String ZZZM9007E = "ZZZM9007E";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : RTL_WH_CD
     */
    public static final String DISPLAY_NM_RTL_WH_CD = "Warehouse Code";

    /**
     * Display Name : RTL_WH_NM
     */
    public static final String DISPLAY_NM_RTL_WH_NM = "Warehouse Name";

    /**
     * Display Name : PHYS_INVTY_DT From
     */
    public static final String DISPLAY_NM_PHYS_INVTY_DT_FROM = "Schedule Date From";

    /**
     * Display Name : PHYS_INVTY_DT Thru
     */
    public static final String DISPLAY_NM_PHYS_INVTY_DT_THRU = "Schedule Date Thru";

    /**
     * Display Name : PHYS_INVTY＿CTRL_NM
     */
    public static final String DISPLAY_NM_PHYS_INVTY_CTRL_NM = "Physical Inventory";

    // =================================================
    // Popup Param
    // =================================================
    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String WINDOW_TITLE_WH_SEARCH = "Warehouse Search";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String WHERE_DISP_NM_FOR_WH_CODE = "Warehouse Code";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String WHERE_SQL_NM_FOR_WH_CODE = "RTL_WH_CD";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String WHERE_DISP_NM_FOR_WH_NAME = "Warehouse Name";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String WHERE_SQL_NM_FOR_WH_NAME = "RTL_WH_NM";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String COLUMN_DISP_NM_FOR_WH_CODE = "Warehosue Code";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String COLUMN_SQL_NM_FOR_WH_CODE = "RTL_WH_CD";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_WH_CODE = new BigDecimal(20);

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String COLUMN_DISP_NM_FOR_WH_NAME = "Warehouse Name";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String COLUMN_SQL_NM_FOR_WH_NAME = "RTL_WH_NM";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_WH_NAME = new BigDecimal(30);

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String SORT_COLUMN_NAME_FOR_WH_CODE = "RTL_WH_CD";

    /**
     * Param Value for NWAL1130(WH Search)
     */
    public static final String SORT_CONDITION_FOR_WH_CODE = "ASC";

}
