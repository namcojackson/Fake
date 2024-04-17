/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NMAL6890.constant;

/**
 * <pre>
 * Business ID : NMAL6890 Warehouse Search
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/11/2016   CITS            Y.Kuroda        Create          N/A
 * 08/17/2017   CITS            S.Endo          Update          Sol#013(QC#18717)
 * 10/21/2022   HITACHI         B.Amarsanaa     Update          QC#60608
 * 03/07/2023   Hitachi         S.Dong          Update          QC#61205
 *</pre>
 */
public class NMAL6890Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NMAL6890";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NMAL6890Scrn00";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /**
     * Event name : OpenWin_Owner.
     */
    public static final String EVENT_NM_NMAL6890SCRN00_OPEN_WIN_OWNR = "NMAL6820Scrn00_OpenWin_Owner";

    /**
     * Event name : OpenWin_AltOwner.
     */
    public static final String EVENT_NM_NMAL6890SCRN00_OPEN_WIN_ALT_OWNR = "NMAL6820Scrn00_OpenWin_AltOwner";

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
        SEARCH("Search");

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

    // =================================================
    // value for message kind error
    // =================================================
    /** error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // =================================================
    // Display Name
    // =================================================
    public static final String DISPLAY_NM_SRCH_OPT_NM = "Saved Option Name";

    public static final String DISPLAY_NM_WH_NM = "Warehouse Name";

    public static final String DISPLAY_NM_WH_DESC = "Warehouse Description";

    public static final String DISPLAY_NM_WH_CD = "Warehouse Code";

    public static final String DISPLAY_NM_WH_TYPE = "Warehouse Type";

    public static final String DISPLAY_NM_OWN = "Owner";

    public static final String DISPLAY_NM_ALT_OWN = "Alternate Owner";

    public static final String DISPLAY_NM_INV_ACCT = "Inventory Account";

    public static final String DISPLAY_NM_SHIP_TO_LOC_NM = "Ship To Location Name";

    public static final String DISPLAY_NM_RTRN_LOC_NM = "Return Location Name";

    public static final String DISPLAY_NM_WH_TEL_NUM = "Warehouse Phone Number";

    public static final String DISPLAY_NM_DFT_SRC_TYPE = "Default Source Type";

    public static final String DISPLAY_NM_SRC_WH_NM = "Source Warehouse Name";

    public static final String DISPLAY_NM_RTRN_SRC_TYPE = "Default Return Type";

    public static final String DISPLAY_NM_RTRN_WH_NM = "Return To Warehouse Name";

    //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) START
//    public static final String DISPLAY_NM_EMRGCY_SRC_TYPE = "Tech Emergency Source Type";
//
//    public static final String DISPLAY_NM_EMRGCY_SRC_NM = "Tech Emergency Source Name";
    public static final String DISPLAY_NM_EMRGCY_SRC_TYPE = "Tech Reference Satellite";

    public static final String DISPLAY_NM_EMRGCY_SRC_NM = "Tech Reference Satellite Name";
    //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) END
    public static final String DISPLAY_NM_ALLW_MIN_MAX = "Allow MIN-MAX Parts For Insourcing";
    //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD START
    public static final String DISPLAY_NM_CTY_ADDR = "City";
    //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD END
    public static final String DISPLAY_NM_TRD_PRT_ID = "Trading Partner ID";

    public static final String DISPLAY_NM_INV_OWN = "Inventory Ownership";

    public static final String DISPLAY_NM_SPACE = "Space";

    public static final String DISPLAY_NM_WH_START_DT = "Warehouse Start Date";

    public static final String DISPLAY_NM_WH_END_DT = "Warehouse End Date";

    // START 2023/03/07 S.Dong [QC#61205, ADD]
    public static final String DISPLAY_NM_WH_OP = "WH Operation";
    // END 2023/03/07 S.Dong [QC#61205, ADD]

    public static final String DISPLAY_NM_SHOW_SWH_LVL = "Show Sub WH Level";

    public static final String DISPLAY_NM_CUR_PAGE_NUM = "Current Page Number";

    public static final String DISPLAY_NM_TOT_PAGE_NUM = "Total Page Number";

    // =================================================
    // Display Name on Message
    // =================================================
    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_OWNER = "S21_PSN_V";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_OWNER = "PSN_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_OWNER = "FULL_PSN_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_OWNER = "PSN_CD";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_OWNER = "Owner Search Popup";

    /** Header Code of Label Name for NMAL6050 : Manager Code */
    public static final String HDR_CD_LB_NM_FOR_OWNER = "Owner Code";

    /** Header Name of Label Name for NMAL6050 : Manager Name */
    public static final String HDR_NM_LB_NM_FOR_OWNER = "Owner Name";

    /** Detail Code of Label Name for NMAL6050 : Manager Code */
    public static final String DTL_CD_LB_NM_FOR_OWNER = "Owner Code";

    /** Detail Name of Label Name for NMAL6050 : Manager Name */
    public static final String DTL_NM_LB_NM_FOR_OWNER = "Owner Name";
}
