/* <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSAL0880.constant;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0880Constant {

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

    /** ScreenID : NSAL0880Scrn00 */
    public static final String SCREEN_ID = "NSAL0880Scrn00";

    /** BizID : NSAL0880 */
    public static final String BIZ_ID = "NSAL0880";

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
}
