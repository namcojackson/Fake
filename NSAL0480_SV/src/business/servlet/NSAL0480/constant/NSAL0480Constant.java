/* <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSAL0480.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   Fujitsu         M.Yamada        Create          N/A
 * 2015/10/05   Hitachi         Y.Tsuchimoto    Update          NA#CSA
 * 2016/10/07   Hitachi         N.Arai          Update          QC#15001
 *</pre>
 */
public class NSAL0480Constant {

    /** NSAL0480 Button */
    public static enum NSAL0480Btn {

        /** */
        VIEW_ITEM("ViewItem", "ViewItem", "View Item")
        //
        , /* */;

        /** */
        private String btnName;

        /** */
        private String event;

        /** */
        private String label;

        private NSAL0480Btn(String btnName, String event, String label) {
            this.btnName = btnName;
            this.event = event;
            this.label = label;
        }

        /**
         * event
         * @return String
         */
        public String event() {
            return event;
        }

        /**
         * label
         * @return String
         */
        public String label() {
            return label;
        }

        /**
         * btnName
         * @return String
         */
        public String btnName() {
            return btnName;
        }
    }

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
        //
        , /* */;

        /** */
        private String btnLbl;

        BTN_LBL(String btnLbl) {
            this.btnLbl = btnLbl;
        }

        /**
         * @return getBtnLbl
         */
        public String getBtnLbl() {
            return btnLbl;
        }

    }

    /** ScreenID : NSAL0480Scrn00 */
    public static final String SCREEN_ID = "NSAL0480Scrn00";

    /** BizID : NSAL0480 */
    public static final String BIZ_ID = "NSAL0480";

    /** FUNCTION */
    public static enum FUNC {
        /** 20 */
        SEARCH("20")
        /** 40 */
        , UPDATE("40")
        //
        , /* */;

        /** */
        private String func;

        FUNC(String func) {
            this.func = func;
        }

        /**
         * @return getOpsTp
         */
        public String getFunc() {
            return func;
        }

    }

    /** SCREEN_LABEL */
    public static enum SCREEN_LABEL {
        /** Saved Search Options */
        SAVED_SEARCH_OPTIONS("Saved Search Options")
        /** Search Option Name */
        , SEARCH_OPTION_NAME("Search Option Name")
        //
        , /* */;

        /** */
        private String screenLabel;

        SCREEN_LABEL(String screenLabel) {
            this.screenLabel = screenLabel;
        }

        /**
         * @return getOpsTp
         */
        public String getscreenLabel() {
            return screenLabel;
        }

    }

    /** Function Code */
    public static enum FUNC_CD {
        /** NSAL0480T010 */
        REF("NSAL0480T010")
        /** NSAL0480T020 */
        , CONTR_MGR("NSAL0480T020")
        //
        , /* */;

        /** */
        private String funcCd;

        FUNC_CD(String funcCd) {
            this.funcCd = funcCd;
        }

        /**
         * @return getFuncCd
         */
        public String getFuncCd() {
            return funcCd;
        }

    }

    /** MSG_ID */
    public enum MSG_ID {

        /** [@] is not selected. */
        NSAM0199E
        //
        , /* */;

    }


    /** NMAL6050_PRM_LENGTH : 11 */
    public static final int NMAL6050_PRM_LENGTH = 11;

    // 2015/10/05 CSA Y.Tsuchimoto Add Start
    /** NMAL6030_PRM_LENGTH : 1 */
    public static final int NMAL6030_PRM_LENGTH = 7;
    // 2015/10/05 CSA Y.Tsuchimoto Add End

    /** NSAL0490_PRM_LENGTH : 1 */
    public static final int NSAL0490_PRM_LENGTH = 1;

    /** NSAL0XXX_PRM_LENGTH :  */
    public static final int NSAL0XXX_PRM_LENGTH = 0;

// START 2016/10/07 N.Arai [QC#15001, MOD]
    /** Item Name (Service Skills) */
    public static final String ITEM_NM_SVC_SKILL = "Service Skills";

    /** Parameter 0 */
    public static final int PRMS_00 = 0;

    /** Parameter 1 */
    public static final int PRMS_01 = 1;

    /** Parameter 2 */
    public static final int PRMS_02 = 2;

    /** Parameter 3 */
    public static final int PRMS_03 = 3;

    /** Parameter 4 */
    public static final int PRMS_04 = 4;

    /** Parameter 5 */
    public static final int PRMS_05 = 5;

    /** Parameter 6 */
    public static final int PRMS_06 = 6;

    /** Parameter 7 */
    public static final int PRMS_07 = 7;

    /** Parameter 8 */
    public static final int PRMS_08 = 8;

    /** Parameter 9 */
    public static final int PRMS_09 = 9;

    /** Parameter 10 */
    public static final int PRMS_10 = 10;

    /** Parameter 11 */
    public static final int PRMS_11 = 11;
// END 2016/10/07 N.Arai [QC#15001, MOD]

    /** TBL_NM */
    public enum TBL_NM {
        /** DS_MDL_GRP */
        DS_MDL_GRP
        /** MDSE */
        , MDSE
        //
        , /* */;
    }

    /** COL_NM */
    public enum COL_NM {
        /** MDL_GRP_NM */
        MDL_GRP_NM
        /** MDL_GRP_DESC_TXT */
        , MDL_GRP_DESC_TXT
        /** MDSE_CD */
        , MDSE_CD
        /** MDSE_NM */
        , MDSE_NM
        //
        , /* */;
    }

    /** Label */
    public static enum LBL {
        /** Model Group PopUp */
        MODEL_GROUP_POPUP("Model Group PopUp")
        /** Model Group Name */
        , MODEL_GROUP_NAME("Model Group Name")
        /** Model Group Description */
        , MODEL_GROUP_DESCRIPTION("Model Group Description")
        /** Merchandise PopUp */
        , MERCHANDISE_POPUP("Merchandise PopUp")
        /** Merchandise Code */
        , MERCHANDISE_CODE("Merchandise Code")
        /** Merchandise Name */
        , MERCHANDISE_NAME("Merchandise Name")
        //
        , /* */;

        /** */
        private String lbl;

        LBL(String lbl) {
            this.lbl = lbl;
        }

        /**
         * @return getLbl
         */
        public String getLbl() {
            return lbl;
        }

    }

}
