/* <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSAL0420.constant;

/**
 *<pre>
 * Representative Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0420Constant {

    /** BUTTON Label */
    public static enum BTN_LBL {
        /** Save */
        SAVE("Save")
        /** Submit */
        , SUBMIT("Submit")
        /** Apply */
        , APPLY("Apply")
        /** Approve */
        , APPROVE("Approve")
        /** Reject */
        , REJECT("Reject")
        /** Download */
        , DOWNLOAD("Download")
        /** Delete */
        , DELETE("Delete")
        /** Clear */
        , CLEAR("Clear")
        /** Reset */
        , RESET("Reset")
        /** Return */
        , RETURN("Return")
        /** Close */
        , CLOSE("Close")
        //
        , /* */;

        /** Button Label */
        private String btnLbl;

        /**
         * Set Button Label
         * @param btnLbl
         */
        BTN_LBL(String btnLbl) {
            this.btnLbl = btnLbl;
        }

        /**
         * Get Button Label
         * @return getBtnLbl
         */
        public String getBtnLbl() {
            return btnLbl;
        }

    }

    /** ScreenID : NSAL0420Scrn00 */
    public static final String SCREEN_ID = "NSAL0420Scrn00";

    /** BizID : NSAL0420 */
    public static final String BIZ_ID = "NSAL0420";

    /** FUNCTION */
    public static enum FUNC {
        /** 20 */
        SEARCH("20");

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

    /** Function Code */
    public static enum FUNC_CD {
        /** NSAL0420T010 */
        REF("NSAL0420T010")
        /** OpenWin_Serial */
        , SEARCH_REPRESENTATIVE("Search_Representative")
        /** OpenWin_Account */
        , SEARCH_BRANCH_HIERARCHY("Search_BranchHierarchy")
        /** OpenWin_ModelName */
        , SELECT_BRANCH_CD("Select_BranchCd");

        /** Function Code */
        private String funcCd;

        /**
         * Set Function Code
         * @param funcCd
         */
        FUNC_CD(String funcCd) {
            this.funcCd = funcCd;
        }

        /**
         * Get Function Code
         * @return getFuncCd
         */
        public String getFuncCd() {
            return funcCd;
        }

    }

    // **************** private buttonName definition *****************

    /** NSAL1180_PRM_LENGTH : 3 */
    public static final int NSAL1180_PRM_LENGTH = 3;

    /** RETURN_PRM_LENGTH : 9 */
    public static final int RETURN_PRM_LENGTH = 9;
}
