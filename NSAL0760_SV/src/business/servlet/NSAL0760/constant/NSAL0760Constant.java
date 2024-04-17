/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0760.constant;

/**
 *<pre>
 * Contract Status History
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class NSAL0760Constant {
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

    /** ScreenID : NSAL0760Scrn00 */
    public static final String SCREEN_ID = "NSAL0760Scrn00";

    /** BizID : NSAL0760 */
    public static final String BIZ_ID = "NSAL0760";

    /** Button Search */
    public static final String[] BTN_SEARCH = {"Search", "Search", "Search" };

    /** Button Audit */
    public static final String[] BTN_AUDIT = {"Audit", "Audit", "Audit" };

    /**  Audit Trail parameter */
    public static final int AUDIT_TRAIL_PRM  = 5; 
}
