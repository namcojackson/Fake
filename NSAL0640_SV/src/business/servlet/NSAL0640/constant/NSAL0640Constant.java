/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0640.constant;

/**
 *<pre>
 * Update Contract Branch/Representative
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/04/04   Hitachi         M.Gotou         Update          QC#4916
 *</pre>
 */
public final class NSAL0640Constant {


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

    /** Button Branch */
    public static final String BTN_BR = "OpenWin_SelectBranch";

    // add start 2016/04/04 CSA Defect#4916
    /** Button Rep */
    public static final String BTN_REP = "OpenWin_Rep";
    // add end 2016/04/04 CSA Defect#4916

    /** ScreenID : NSAL0640Scrn00 */
    public static final String SCREEN_ID = "NSAL0640Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0640";

    /** Divide String */
    public static final String DIV_STR = "-";

    /** NSAL0420_PRM_LENGTH */
    public static final int NSAL0420_PRM_LENGTH = 9;

    // add start 2016/04/04 CSA Defect#4916
    /** NSAL0420_PRM_LENGTH */
    public static final int NWAL1130_PRM_LENGTH = 7;
    // add end 2016/04/04 CSA Defect#4916

    /** OpenWin_SelectBranch */
    public static final String SELECT_BRANCH_HEAD = "OpenWin_SelectBranchHead";

    /** OpenWin_SelectBranchDtl */
    public static final String SELECT_BRANCH_DTL = "OpenWin_SelectBranchDtl";

    /** CONTR_BR_FIRST_ORG_CD */
    public static final String CONTR_BR_FIRST_ORG_CD = "CONTR_BR_FIRST_ORG_CD";
}
