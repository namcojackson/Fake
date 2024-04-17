/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2220.constant;

/**
 *<pre>
 * Import  Search & Result
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/16   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NWAL2220Constant {

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

    /** ScreenID : NWAL2220Scrn00 */
    public static final String SCREEN_ID = "NWAL2220Scrn00";

    /** BizID : NWAL2220 */
    public static final String BIZ_ID = "NWAL2220";

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

    /**
     * TBL_NM_TOC : TOC
     */
    public static final String TBL_NM_TOC = "TOC";

    /**
     * COL_CD_TOC_CD : TOC_CD
     */
    public static final String COL_CD_TOC_CD = "TOC_CD";

    /**
     * COL_NM_TOC_NM : TOC_NM
     */
    public static final String COL_NM_TOC_NM = "TOC_NM";

    /**
     * COL_SORT_TOC_CD : TOC_CD
     */
    public static final String COL_SORT_TOC_CD = "TOC_CD";

    /**
     * SCR_NM_SLS_REP : Sales Rep
     */
    public static final String SCR_NM_SLS_REP = "Sales Rep";

    /**
     * HDR_COL_CD_SLS_REP : Sales Rep
     */
    public static final String HDR_COL_CD_SLS_REP = "Sales Rep";

    /**
     * HDR_COL_NM_SLS_REP : Sales Rep Name
     */
    public static final String HDR_COL_NM_SLS_REP = "Sales Rep Name";

    /**
     * DTL_COL_CD_SLS_REP : Sales Rep
     */
    public static final String DTL_COL_CD_SLS_REP = "Sales Rep";

    /**
     * DTL_COL_NM_SLS_REP : Sales Rep Name
     */
    public static final String DTL_COL_NM_SLS_REP = "Sales Rep Name";

    /**
     * TBL_NM_COA_BR : COA_BR
     */
    public static final String TBL_NM_COA_BR = "COA_BR";

    /**
     * COL_CD_COA_BR_CD : COA_BR_CD
     */
    public static final String COL_CD_COA_BR_CD = "COA_BR_CD";

    /**
     * COL_NM_COA_BR_NM : COA_BR_NM
     */
    public static final String COL_NM_COA_BR_NM = "COA_BR_NM";

    /**
     * COL_SORT_COA_BR_CD : COA_BR_CD
     */
    public static final String COL_SORT_COA_BR_CD = "COA_BR_CD";

    /**
     * SCR_NM_SLS_BR : Sales Branch
     */
    public static final String SCR_NM_SLS_BR = "Sales Branch";

    /**
     * HDR_COL_CD_SLS_BR : Sales Branch
     */
    public static final String HDR_COL_CD_SLS_BR = "Sales Branch";

    /**
     * HDR_COL_NM_SLS_BR : Sales Branch Name
     */
    public static final String HDR_COL_NM_SLS_BR = "Sales Branch Name";

    /**
     * DTL_COL_CD_SLS_BR : Sales Branch
     */
    public static final String DTL_COL_CD_SLS_BR = "Sales Branch";

    /**
     * DTL_COL_NM_SLS_BR : Sales Branch Name
     */
    public static final String DTL_COL_NM_SLS_BR = "Sales Branch Name";

    /**
     * EVNT_NM_SLS_REP : OpenWin_SlsRep
     */
    public static final String EVNT_NM_SLS_REP = "OpenWin_SlsRep";

    /**
     * EVNT_NM_SLS_BRANCH : OpenWin_SlsBranch
     */
    public static final String EVNT_NM_SLS_BRANCH = "OpenWin_SlsBranch";

    /**
     * EVNT_NM_BILL_TO_CUST : OpenWin_BillToCust
     */
    public static final String EVNT_NM_BILL_TO_CUST = "OpenWin_BillToCust";

    /**
     * EVNT_NM_SHIP_TO_CUST : OpenWin_ShipToCust
     */
    public static final String EVNT_NM_SHIP_TO_CUST = "OpenWin_ShipToCust";

    /**
     * EVNT_NM_SOLD_TO_CUST : OpenWin_SoldToCust
     */
    public static final String EVNT_NM_SOLD_TO_CUST = "OpenWin_SoldToCust";

    /**
     * NWAL2220_PRM_LENGTH : 2
     */
    public static final int NWAL2220_PRM_LENGTH = 2;

    /**
     * NMAL6050_PRM_LENGTH : 11
     */
    public static final int NMAL6050_PRM_LENGTH = 11;

    /**
     * NMAL6760_PRM_LENGTH : 35
     */
    public static final int NMAL6760_PRM_LENGTH = 35;

    /**
     * Display Hierarchy Accounts Code: 02
     */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";

    /**
     * Display Hierarchy Accounts Code: 03
     */
    public static final String DISP_HRCH_ACCT_CD_SHIP = "03";

    // 2016/10/04 S21_NA#12893 Add Start
    /** Index Number 0 */
    public static final int IDX_0 = 0;

    /** Index Number 1 */
    public static final int IDX_1 = 1;

    /** Index Number 2 */
    public static final int IDX_2 = 2;

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Index Number 4 */
    public static final int IDX_4 = 4;

    /** Index Number 5 */
    public static final int IDX_5 = 5;

    /** Index Number 6 */
    public static final int IDX_6 = 6;

    /** Index Number 7 */
    public static final int IDX_7 = 7;

    /** Column Width Size 10 */
    public static final int CLMN_WIDTH_SIZE_10 = 10;

    /** Column Width Size 12 */
    public static final int CLMN_WIDTH_SIZE_12 = 12;

    /** Column Width Size 20 */
    public static final int CLMN_WIDTH_SIZE_20 = 20;

    /** Column Width Size 30 */
    public static final int CLMN_WIDTH_SIZE_30 = 30;

    /** Percent */
    public static final String PERCENT = "%";
    // 2016/10/04 S21_NA#12893 Add End

}

