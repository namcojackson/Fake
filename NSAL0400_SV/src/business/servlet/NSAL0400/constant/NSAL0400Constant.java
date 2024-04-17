/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0400.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/19   Fujitsu         M.Yamada        Create          N/A
 * 2016/09/27   Hitachi         Y.Zhang         Update          QC#12582
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 *</pre>
 */
public class NSAL0400Constant {

    /** ScreenID : NSAL0400Scrn00 */
    public static final String SCREEN_ID = "NSAL0400Scrn00";

    /** BizID : NSAL0400 */
    public static final String BIZ_ID = "NSAL0400";

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

    /** SCREEN_LABEL */
    public static enum SCREEN_LABEL {
        /** Service Memo Reason Code */
        RSN_CD("Service Memo Reason Code")
        /** Service Comment Text */
        , CMNT("Service Comment Text")
        /** Termination Date */
        , TRMN_DT("Termination Date")
        /** Override Amount */
        , OVRD_AMT("Override Amount")
        //
        // START 2016/09/27 Y.Zhang [QC#12582, MOD]
        /** Mdse Cd */
        , MDSE_CD("Item Cd")
        // END 2016/09/27 Y.Zhang [QC#12582, MOD]
        /** Serial# */
        , SERIAL("Serial#")
        /** Model */
        , MODEL("Model")
        /** Ship To */
        , SHIP_TO("Ship To")
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        /** All Period Terminate */
        , ALL_PER_TRMN("All Period Terminate")
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
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
        /** NSAL0400T010 */
        REF("NSAL0400T010")
        /** NSAL0400T020 */
        , CONTR_MGR("NSAL0400T020")
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        /** NSAL0400T030 */
        , ALL_PER_TRMN("NSAL0400T030")
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
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

    /** NSAL0400 Button */
    public static enum NSAL0400Btn {

        /** */
        Review("Review", "Review", "Review")
        /** */
        , Apply("Apply", "Apply", "Apply to All")
        //
        , /* */;

        /** */
        private String btnName;

        /** */
        private String event;

        /** */
        private String label;

        private NSAL0400Btn(String btnName, String event, String label) {
            this.btnName = btnName;
            this.event = event;
            this.label = label;
        }

        /**
         * event
         * @return  String
         */
        public String event() {
            return event;
        }

        /**
         * label
         * @return  String
         */
        public String label() {
            return label;
        }

        /**
         * btnName
         * @return  String
         */
        public String btnName() {
            return btnName;
        }
    }

    /** label for NSBL0160 */
    public static enum LABEL {
        /** */
        DS_CONTR_PK
        /** */
        , DS_CONTR_DTL_PK
        //
        , /* */;
    }

    /** */
    public static final int PRM_CNT_NSBL0160 = 22;

}
