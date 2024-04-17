/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0750.constant;

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         T.Tsuchida      Create          N/A
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 *</pre>
 */
public class NFCL0750Constant {

    // [0]:Button Name [1]:Event Name [2]:Button Label
    /** BUTTON Label */
    public static enum BTN_LBL {
        /** Save */
        SAVE("btn1", "CMN_Save", "Save")
        /** Submit */
        , SUBMIT("btn2", "CMN_Submit", "Submit")
        /** Apply */
        , APPLY("btn3", "CMN_Apply", "Apply")
        /** Approve */
        , APPROVE("btn4", "CMN_Approve", "Approve")
        /** Reject */
        , REJECT("btn5", "CMN_Reject", "Reject")
        /** Download */
        , DOWNLOAD("btn6", "CMN_Download", "Download")
        /** Delete */
        , DELETE("btn7", "CMN_Delete", "Delete")
        /** Clear */
        , CLEAR("btn8", "CMN_Clear", "Clear")
        /** Reset */
        , RESET("btn9", "CMN_Reset", "Reset")
        /** Return */
        , RETURN("btn10", "CMN_Return", "Return")
        /** Close */
        , CLOSE("btn10", "CMN_Close", "Close")
        /** DETAIL */
        , DETAIL("OpenWin_Detail", "OpenWin_Detail", "Detail")
        //
        , /* */;

        /** Original Name */
        private String orgNm;
        /** Guard Name */
        private String guard;
        /** Label Name */
        private String label;

        /**
         * Set Button Label
         * @param orgNm
         * @param guard
         * @param label
         */
        BTN_LBL(String orgNm, String guard, String label) {
            this.orgNm = orgNm;
            this.guard = guard;
            this.label = label;
        }

        /**
         * Get Original Name
         * @return getOrgNm
         */
        public String getOrgNm() {
            return orgNm;
        }

        /**
         * Get Guard Name
         * @return getGuardNm
         */
        public String getGuardNm() {
            return guard;
        }

        /**
         * Get Label Name
         * @return getLblNm
         */
        public String getLblNm() {
            return label;
        }

    }

    /** ScreenID : NFCL0750Scrn00 */
    public static final String SCREEN_ID = "NFCL0750Scrn00";

    /** BizID : NFCL0750 */
    public static final String BIZ_ID = "NFCL0750";

    /** FUNCTION */
    public static enum FUNC {
        /** 20 */
        SEARCH("20")
        /** 40 */
        , UPDATE("40");

        /** Function */
        private String func;

        /**
         * Set Function
         * @param func
         */
        FUNC(String func) {
            this.func = func;
        }

        /**
         * Get Function
         * @return func
         */
        public String getFunc() {
            return func;
        }

    }

    /** NMAL6760_PRM_LENGTH : 15 */
    public static final int NMAL6760_PRM_LENGTH = 15;

    // START 2017/08/21 T.Tsuchida [QC#19573,MOD]
    /** NSAL0760_PRM_LENGTH : 6 */
    public static final int NFCL0760_PRM_LENGTH = 6;
    // END 2017/08/21 T.Tsuchida [QC#19573,MOD]

    // START 2018/04/19 J.Kim [QC#24885,ADD]
    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";
    // END 2018/04/19 J.Kim [QC#24885,ADD]
}

