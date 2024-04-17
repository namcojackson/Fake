/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/03   CITS            M.Ouchi         Create          N/A
 * 08/09/2016   CITS            S.Endo          Update          QC#10839
 * 2016/08/22   CITS            T.Gotoda        Update          QC#12215
 * 2016/09/26   CITS            T.Gotoda        Update          QC#13163
 * 2016/12/15   CITS            R.Shimamoto     Update          QC#15816
 * 2018/02/15   CITS            T.Gotoda        Update          QC#22054
 * 2020/02/28   Fujitsu         C.Hara          Update          QC#55971
 * 2020/10/28   CITS            R.Kurahashi     Update          QC#57732
 * 2020/12/12   CITS            R.Kurahashi     Update          QC#57732-1
 * 2021/03/01   CITS            G.Delgado       Update          QC#56057
 *</pre>
 */
public class NMAL6860Constant {

    /**
     * The business Id.
     */
    public static final String BIZ_ID = "NMAL6860";

    /**
     * The screen Id.
     */
    public static final String SCRN_ID = "NMAL6860Scrn00";

    /**
     * The function code. (UPDATE)
     */
    public static final String FUCTION_CODE_UPDATE = "NMAL6860T020";

    /**
     * The tab name. (General)
     */
    public static final String TAB_NM_GENERAL = "General";

    /**
     * The tab name. (Detail)
     */
    public static final String TAB_NM_DETAIL = "Detail";

    /**
     * Anchor event name. (LiabilityAccount)
     */
    public static final String LIABILITY_ACCOUNT = "LiabilityAccount";

    /**
     * Anchor event name. (PrePayAccount)
     */
    public static final String PREPAY_ACCOUNT = "PrePayAccount";

    /**
     * Anchor event name. (VendorReturnAccount)
     */
    public static final String VENDOR_RETURN_ACCOUNT = "VendorReturnAccount";

    /**
     * The event name of "CMN_Close".
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";
    
    /**
     * DS Account Relation Type Code of "Bill To or Ship To".
     */
    public static final String DS_ACCT_RELN_TP_CD_BILL_TO_OR_SHIP_TO = "99";

    /** Display Hierarchy Account : 01:All */
    public static final String DISP_HIERARCHY_ACCT_ALL = "01";

    /** Account Type Liability  : 01 */
    public static final String ACC_TYPE_LIABILITY = "01";

    /** Account Type PrePay     : 02 */
    public static final String ACC_TYPE_PREPAY = "02";

    /** Account Type Vnd Return : 03 */
    public static final String ACC_TYPE_VNDRETURN = "03";

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
         * Application button : Add Supplier Site.
         */
        ADD_SUPPLIER_SITE("OnClick_AddSupplierSite"),

        /**
         * Application button : Add Contact Info.
         */
        ADD_CONTACT_INFO("OnClick_AddContactInfo"),

        // START 2020/12/12 R.Kurahashi [QC#57732-1,ADD]
        /**
         * Application button : Open Win City.
         */
        LINK_OPENWIN_CTY_EVENT_NM("OpenWin_City"),
        /**
         * Application button : Get Address.
         */
        BTN_GET_ADDRESS("GetAddress"),
        // END 2020/12/12 R.Kurahashi [QC#57732-1,ADD]
        
        /**
         * Application button : Acct.
         */
        LIABILITY_ACCOUNT("OnClick_LiabilityAccount");

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

    public static final String DATE_FORMAT = "yyyyMMdd";

    public static final String TEL_FAX_CORRECT_FORMAT = "'9999999999' or '999-999-9999'";

    // ------------------- The message code -------------------
    /** Please select at least 1 checkbox. */
    public static final String NZZM0011E = "NZZM0011E";
    /** The specified format is incorrect. It must be @. */
    public static final String NMAM8075E = "NMAM8075E";
    /** The format of [@] is incorrect. */
    public static final String NMAM0052E = "NMAM0052E";
    /** Cannot enter a date earlier than the today's date. */
    public static final String NMAM0808E = "NMAM0808E";
    /** [@] is not registered. */
    public static final String NMAM0011E = "NMAM0011E";
    /** Domestic supplier site, [@] field is mandatory. */
    public static final String NMAM8643E = "NMAM8643E";
    /** Phone number format is incorrect. Minimum length is 7digit, Maximum length is 20 digit. */
    public static final String NWAM0763E = "NWAM0763E";
    /** Error Message: [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";
    /** Please execute it again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** Function Code : Search */
    public static final String FUNCTION_CD_SEARCH = "20";
    /** Link OpenWin_City Link Event Name */
    public static final String LINK_OPENWIN_CTY_EVENT_NM = "OpenWin_City";
    /** Link OpenWin_InvoiceSupplier Link Event Name */
    public static final String LINK_OPENWIN_INVOICE_SUPPLIER = "OpenWin_InvoiceSupplier";
    // QC#13163 Start
    /** Link OpenWin_InvoiceSupplier Link Event Name */
    public static final String LINK_OPENWIN_PAYMENT_TERM = "OpenWin_PaymentTerm";
    // QC#13163 End
    // 2020/02/28 QC#55971 Add Start
    /** Link OpenWin_InvoiceSupplier Link Event Name */
    public static final String LINK_OPENWIN_SUPPLIER_TYPE = "OpenWin_SupplierType";
    // 2020/02/28 QC#55971 Add End
    // START 2020/10/28 R.Kurahashi [QC#57732,ADD]
    /** Button Get Address Button Name */
    public static final String BTN_GET_ADDRESS = "GetAddress";
    // END 2020/10/28 R.Kurahashi [QC#57732,ADD]
    /** Index Number 0 */
    public static final int IDX_0 = 0;

    /** Index Number 1 */
    public static final int IDX_1 = 1;

    /** Index Number 2 */
    public static final int IDX_2 = 2;

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Index Number 4 */
    public static final int IDX_4 = 4;

    /** Index Number 5 */
    public static final int IDX_5 = 5;

    /** Index Number 6 */
    public static final int IDX_6 = 6;

    /** Index Number 7 */
    public static final int IDX_7 = 7;

    /** / */
    public static final String SLASH = "";

    /** - */
    public static final String HYPHEN = "";

    /** . */
    public static final String PERIOD = "";
    
    // START 2020/10/28 R.Kurahashi [QC#57732,ADD]
    public static final String ENTRY_SUPPLIER = "SupplierEntry";
    
    public static final String NEW_SUPPLIER = "NewSupplier";
    // END 2020/10/28 R.Kurahashi [QC#57732,ADD]

    // START 2021/03/01 G.Delgado [QC#56057,ADD]
    /** VarcharConst Key : Unchangeable Supplier Types */
    public static final String NMAL6860_PRNT_VND_TP_CD_FIXED = "NMAL6860_PRNT_VND_TP_CD_FIXED";

    /** VarcharConst Key : Selectable Parent Vendor Type Code for ARRefund */
    public static final String ARREFUND_PRNT_VND_TP_CD = "ARREFUND_PRNT_VND_TP_CD";
    // END 2021/03/01 G.Delgado [QC#56057,ADD]
}
