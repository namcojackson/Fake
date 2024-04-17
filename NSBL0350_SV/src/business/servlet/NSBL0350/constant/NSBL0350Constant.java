/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0350.constant;

/** 
 *<pre>
 * Group Level Report
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          QC#5735
 *</pre>
 */
public class NSBL0350Constant {

    /** ScreenID : NSBL0350Scrn00 */
    public static final String SCREEN_ID = "NSBL0350Scrn00";

    /** Business ID */
    public static final String BUSINESS_ID = "NSBL0350";

    /** The number of popup parameter */
    public static final int POP_PARAM_NUM = 7;

    /** The number of move window parameter */
    public static final int POP_PARAM_NUM_2 = 2;

    /** Row Name 1 */
    public static final String ROW_NAME_1 = "Total In Process Tasks";

    /** Row Name 2 */
    public static final String ROW_NAME_2 = "Parts Waiting Tasks";

    /** Row Name 3 */
    public static final String ROW_NAME_3 = "Specialist Waiting Tasks";

    /** Row Name 4 */
    public static final String ROW_NAME_4 = "Other Open Tasks";

    /** Row Name 5 */
    public static final String ROW_NAME_5 = "Customer Tasks";

    /** Row Name 6 */
    public static final String ROW_NAME_6 = "Escalated Tasks";

    /** Row Name 7 */
    public static final String ROW_NAME_7 = "Created Tasks";

    /** Row Name 8 */
    public static final String ROW_NAME_8 = "Per Tech";

    /** Row Name 9 */
    public static final String ROW_NAME_9 = "After Hour Tasks";

    /** Row Name 10 */
    public static final String ROW_NAME_10 = "Per Tech";

    /** Row Name 11 */
    public static final String ROW_NAME_11 = "Closed Tasks";

    /** Row Name 12 */
    public static final String ROW_NAME_12 = "Per Tech";

    /** Row Name 13 */
    public static final String ROW_NAME_13 = "Parts Failure";

    /** Row Name 14 */
    public static final String ROW_NAME_14 = "Post Entry Tasks";

    /** Row Name 15 */
    public static final String ROW_NAME_15 = "Available Tech";

    /** Row Name 16 */
    public static final String ROW_NAME_16 = "Response Time Customer Tasks";

    /** Row Name 17 */
    public static final String ROW_NAME_17 = "Response Time All Tasks";

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /** As of */
    public static final String AS_OF = "As of";

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
        /** Search_SvcGrp */
        , SEARCH_SVCGRP("Search_SvcGrp", "Search_SvcGrp", ">>")
        /** Search */
        , SEARCH("Search", "Search", "Search")
        /** Page Preview */
        , PREV("PagePrev", "PagePrev", "Prev")
        /** Page Next */
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

    // START 2016/03/22 K.Yamada [QC#5735, ADD]
    /** Tab Name : Sub Grp Lvl */
    public static final String TAB_NAME_NSBL0360 = "Sub Grp Lvl";
    // END 2016/03/22 K.Yamada [QC#5735, ADD]
}
