/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0760.constant;

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         T.Tsuchida      Create          N/A
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2018/09/11   Hitachi         Y.Takeno        Update          QC#24884
 *</pre>
 */
public class NFCL0760Constant {

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
        /** DETAIL */
        , ATTACHMENT("OpenWin_AttachFile", "OpenWin_AttachFile", "Attachment")
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

    /** ScreenID : NFCL0760Scrn00 */
    public static final String SCREEN_ID = "NFCL0760Scrn00";

    /** BizID : NFCL0760 */
    public static final String BIZ_ID = "NFCL0760";

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

    /** NSAL0760_PRM_LENGTH : 4 */
    public static final int NFCL0760_PRM_LENGTH = 4;

    /** PRM_ZERO : 0 */
    public static final int PRM_ZERO = 0;

    /** PRM_ONE : 1 */
    public static final int PRM_ONE = 1;

    /** PRM_TWO : 2 */
    public static final int PRM_TWO = 2;

    /** PRM_THREE : 3 */
    public static final int PRM_THREE = 3;

    // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
    /** PRM_FOUR : 4 */
    public static final int PRM_FOUR = 4;

    /** PRM_FIVE : 5 */
    public static final int PRM_FIVE = 5;
    // END 2017/08/21 T.Tsuchida [QC#19573,ADD]

    // START 2018/09/11 [QC#24884, ADD]
    /** Function ID NFCL0760T020 (Edit) */
    public static final String FUNC_ID_NFCL0760T020 = "NFCL0760T020";

    /** ZYPL0310 Screen Display Mode : Read-Only */
    public static String ZYPL0310_PARAM_DISPLAY_MODE_READ_ONLY = "Read-Only";

    /** ZYPL0310 Screen Display Mode : Modification */
    public static String ZYPL0310_PARAM_DISPLAY_MODE_MODIFICATION = "Modification";

    /** ZYPL0310_PARAM_BIZ_ID */
    public static final String ZYPL0310_PARAM_BIZ_ID = "NFDL0090";

    /** ZYPL0310_PARAM_FUNC_NM */
    public static final String ZYPL0310_PARAM_FUNC_NM = "Write Off Attachments";

    /** ZYPL0310_PARAM_PRIMARY_KEY_NM */
    public static final String ZYPL0310_PARAM_PRIMARY_KEY_NM = "Write Off Request#";

    /** ZYPL0310_PARAM_DOC_TYPE_TABLE_NM */
    public static final String ZYPL0310_PARAM_DOC_TYPE_TABLE_NM = "CLT_UPD_ATT_TP";

    /** NUM_CONST key : NFDL0090_PARAM_ATT_LIMIT_NUM */
    public static final String VAR_CHAR_CONST_KEY_NFDL0090_PARAM_ATT_LIMIT_NUM = "NFDL0090_PARAM_ATT_LIMIT_NUM";

    /** NUM_CONST key : NFDL0090_PARAM_ATT_DATA_VOL */
    public static final String VAR_CHAR_CONST_KEY_NFDL0090_PARAM_ATT_DATA_VOL = "NFDL0090_PARAM_ATT_DATA_VOL";
    // END   2018/09/11 [QC#24884, ADD]
}

