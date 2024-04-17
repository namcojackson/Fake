/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0420.constant;

/** 
 *<pre>
 * Mods Parts Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */

public class NSBL0420Constant {
    /** ScreenID : NSBL0420Scrn00 */
    public static final String SCREEN_ID = "NSBL0420Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NSBL0420";

    /** XX_MODE_CD: reference mode */
    public static final String REF_MODE = "02";

    /** XX_MODE_CD: edit mode */
    public static final String EDIT_MODE = "01";

    /** The number of popup parameter */
    public static final int POP_PARAM_NUM = 7;

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";

    /** Please select item(s). */
    public static final String NSBM0042E = "NSBM0042E";

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
        /** Add */
        , ADD("Add", "Add", "Add")
        /** SelectAll_Contr */
        , SELECT_ALL("SelectAll", "SelectAll", "Select All")
        /** SelectAll_Contr */
        , UNSELECT_ALL("UnselectAll", "UnselectAll", "Unselect All")
        /** SelectAll_Contr */
        , DELETE_DETAIL("Delete", "Delete", "Delete")
        /** Close */
        , PREV("PagePrev", "PagePrev", "Prev")
        /** SelectAll_Contr */
        , NEXT("PageNext", "PageNext", "Next")
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
}
