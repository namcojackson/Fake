/* <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSAL0510.constant;

/**
 *<pre>
 * Sub Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/01/12   Hitachi         T.Tsuchida      Update          QC#2889
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 *</pre>
 */
public class NSAL0510Constant {

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
        , CLOSE("CMN_Close");

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

    /** ScreenID : NSAL0510Scrn00 */
    public static final String SCREEN_ID = "NSAL0510Scrn00";

    /** BizID : NSAL0510 */
    public static final String BIZ_ID = "NSAL0510";

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
        /** NSAL0510T010 */
        REF("NSAL0510T010")
        /** OpenWin_Serial */
        , OPENWIN_SERIAL("OpenWin_Serial")
        /** OpenWin_Account */
        , OPENWIN_ACCOUNT("OpenWin_Account")
        /** OpenWin_ModelName */
        , OPENWIN_MODELNAME("OpenWin_ModelName")
        /** OpenWin_Vendor */
        , OPENWIN_VENDOR("OpenWin_Vendor");

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

    /** NMAL6050_PRM_LENGTH : 11 */
    public static final int NMAL6050_PRM_LENGTH = 11;

    /** NSAL0500_PRM_LENGTH : 2 */
    public static final int NSAL0500_PRM_LENGTH = 2;

    /** NSAL0480_PRM_LENGTH : 1 */
    public static final int NSAL0480_PRM_LENGTH = 1;

    /** NSAL0030_PRM_LENGTH : 17 */
    public static final int NSAL0030_PRM_LENGTH = 17;

    /** TBL_NM */
    public enum TBL_NM {
        /** VND */
        VND
        /** SELL_TO_CUST */
        , SELL_TO_CUST
        /** MDL_NM */
        , MDL_NM;
    }

    /** COL_NM */
    public enum COL_NM {
        /** VND_CD */
        VND_CD
        /** VND_NM */
        , VND_NM
        /** SELL_TO_CUST_CD */
        , SELL_TO_CUST_CD
        /** DS_ACCT_NM */
        , DS_ACCT_NM
        /** T_MDL_NM */
        , T_MDL_NM;
    }

    /** Label */
    public static enum LBL {
        // START 2017/12/22 [QC#22831, MOD]
        /** Vendor PopUp */
        VND_POPUP("Supplier Site PopUp")
        /** Vendor Code */
        , VND_CD("Supplier Site Code")
        /** Vendor Name */
        , VND_NM("Supplier Site Name")
        /** Account PopUp */
        , ACCT_POPUP("Account PopUp")
        /** Account Number */
        , ACCT_NUM("Account Number")
        /** Account Name */
        , ACCT_NM("Account Name");
        // END  2017/12/22 [QC#22831, MOD]

        /** Label */
        private String lbl;

        /**
         * Set Label
         * @param lbl
         */
        LBL(String lbl) {
            this.lbl = lbl;
        }

        /**
         * Get Label
         * @return getLbl
         */
        public String getLbl() {
            return lbl;
        }

    }

    /**
     * NWAL1130 LENGTH
     */
    public static final int NWAL1130_PRM_LENGTH = 7;

    /**
     * The number of the attribute of WhereList
     */
    public static final int ATTR_NUM_WHERE_LIST = 4;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_DSP_OBJ_NM = 0;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_OBJ_ID = 1;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_OBJ_VALUE = 2;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_WHERE_FLG = 3;

    /**
     * The number of the attribute of ColumnList
     */
    public static final int ATTR_NUM_CLMN_LIST = 4;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_DSP_OBJ_NM = 0;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_OBJ_ID = 1;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_OBJ_LENGTH = 2;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_LINK_FLG = 3;

    /**
     * VND_CD LENGTH 
     */
    public static final int VND_CD_LENGTH = 15;

    /**
     * LOC_NM LENGTH 
     */
    public static final int LOC_NM_LENGTH = 30;

    /**
     * PRNT_VND_CD LENGTH 
     */
    public static final int PRNT_VND_CD_LENGTH = 15;

    /**
     * PRNT_VND_NM LENGTH 
     */
    public static final int PRNT_VND_NM_LENGTH = 30;

}
