/* <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSBL0320.constant;

/**
 *<pre>
 * Select SR Summary Criteria
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/04   Hitachi         Y.Osawa         Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          QC#5735
 *</pre>
 */
public class NSBL0320Constant {

    /** ScreenID : NSBL0320Scrn00 */
    public static final String SCREEN_ID = "NSBL0320Scrn00";

    /** BizID : NSBL0320 */
    public static final String BIZ_ID = "NSBL0320";

    /** NSBL0330_PRM_LENGTH */
    public static final int NSBL0330_PRM_LENGTH = 7;

    /** Function Id: READ */
    public static final String FUNC_ID_INQ = BIZ_ID + "T010";

    /**
     * Incorrect date is entered.
     */
    public static final String NSBM0023E = "NSBM0023E";

    /** Number 0 For Comparison */
    public static final int CON_NUM_0 = 0;

    /** Number 1 For Comparison */
    public static final int CON_NUM_1 = 1;

    /** Number 2 For Comparison */
    public static final int CON_NUM_2 = 2;

    /** Number 3 For Comparison */
    public static final int CON_NUM_3 = 3;

    /** Number 4 For Comparison */
    public static final int CON_NUM_4 = 4;

    /** Number 5 For Comparison */
    public static final int CON_NUM_5 = 5;

    /** Button Name */
    public static final String BTN_SELECT_ALL = "SelectAll";

    /** Button Name */
    public static final String BTN_UNSELECT_ALL = "UnselectAll";

    /** Button Name */
    public static final String BTN_SEARCH = "Search";

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
        /** SelectAll_Contr */
        , SELECT_ALL("SelectAll", "SelectAll", "Select All")
        /** SelectAll_Contr */
        , UNSELECT_ALL("UnselectAll", "UnselectAll", "Unselect All")
        /** Search */
        , SEARCH("Search", "Search", "Search")
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

    // START 2016/03/22 K.Yamada [QC#5735, ADD]
    /** Tab Name : Srch Smry */
    public static final String TAB_NAME_NSBL0330 = "Srch Smry";
    // END 2016/03/22 K.Yamada [QC#5735, ADD]
}
