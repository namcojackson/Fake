/* <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre> */
package business.servlet.NSAL0620.constant;

/**
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         T.Tsuchida      Create          N/A
 * 2016/02/18   Hitachi         T.Aoyagi        Update          QC3434
 * 2016/05/09   Hitachi         M.Gotou         Update          QC4093
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 * 2017/02/07   Hitachi         K.Kojima        Update          QC#17303
 * 2018/06/26   Fujitsu         T.Ogura         Update          QC#26336
 * 2018/07/18   CITS            T.Wada          Update          QC#26424
 * 2022/10/13   Hitachi         M.Komatsu       Update          QC#60078,60537
 *</pre>
 */
public class NSAL0620Constant {


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
        // START 2016/02/18 T.Aoyagi [QC3434, ADD]
        /** Copy */
        , COPY("OpenWin_CopyContr", "OpenWin_CopyContr", "Copy")
        // END 2016/02/18 T.Aoyagi [QC3434, ADD]
        /** SelectAll_Contr */
        , SELECT_ALL_CONTR("SelectAll_Contr", "SelectAll_Contr", "Select All")
        /** SelectAll_Contr */
        , UN_SELECT_ALL_CONTR("UnSelectAll_Contr", "UnSelectAll_Contr", "Unselect All")
        /** SelectAll_Contr */
        , OPEN_WIN_PREP_MASS_UPD_SCRN("OpenWin_PrepMassUpdScrn", "OpenWin_PrepMassUpdScrn", "Go")
        //START 2017/08/21 E.Kameishi [QC#8661,ADD]
        /** Print */
        , PRINT("Print", "Print", "Print")
        //END 2017/08/21 E.Kameishi [QC#8661,ADD]
        // START 2018/06/26 T.Ogura [QC#26336,ADD]
        /** Print */
        , METER_HISTORY("Meter_History", "Meter_History", "Meter_History")
        // END   2018/06/26 T.Ogura [QC#26336,ADD]
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

    /** Check Box */
    public static enum CHK_BOX {
        /** xxChkBox_A */
        XX_CHK_BOX_A("xxChkBox_A")
        /** xxChkBox_B */
        , XX_CHK_BOX_B("xxChkBox_B")
        /** xxChkBox_C */
        , XX_CHK_BOX_C("xxChkBox_C")
        /** xxChkBox_D */
        , XX_CHK_BOX_D("xxChkBox_D")
        /** xxChkBox_E */
        , XX_CHK_BOX_E("xxChkBox_E");

        /**  Check Box */
        private String chkBox;

        /**
         * Set Check Box
         * @param chkBox
         */
        CHK_BOX(String chkBox) {
            this.chkBox = chkBox;
        }

        /**
         * Get Check Box
         * @return chkBox
         */
        public String getChkBox() {
            return chkBox;
        }

    }

    /** ScreenID : NSAL0620Scrn00 */
    public static final String SCREEN_ID = "NSAL0620Scrn00";

    /** BizID : NSAL0620 */
    public static final String BIZ_ID = "NSAL0620";

    /** FUNCTION */
    public static enum FUNC {
        /** 20 */
        SEARCH("20")
        /** 40 */
        , UPDATE("40")
        //START 2017/08/21 E.Kameishi [QC#8661,ADD]
        /** 70 */
        , PRINT("70");
        //END 2017/08/21 E.Kameishi [QC#8661,ADD]

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
        /** NSAL0620T010 */
        REF("NSAL0620T010")
        /** NSAL0620T020 */
        , EDT("NSAL0620T020")
        /** OpenWin_SrchMdl */
        , OPEN_WIN_SRCH_MDL("OpenWin_SrchMdl")
        /** OpenWin_Account */
        , SEARCH_BRANCH_HIERARCHY("Search_BranchHierarchy")
        /** OpenWin_ModelName */
        , SELECT_BRANCH_CD("Select_BranchCd")
        // START 2022/10/13 M.Komatsu [QC#60078,ADD]
        /** OpenWin_ContType */
        ,OPEN_WIN_CONT_TYPE("OpenWin_ContType")
        /** OpenWin_ContStatus */
        ,OPEN_WIN_CONT_STATUS("OpenWin_ContStatus")
        /** OpenWin_ContCategory */
        ,OPEN_WIN_CONT_CATEGORY("OpenWin_ContCategory")
        /** OpenWin_MachStatus */
        ,OPEN_WIN_MACH_STATUS("OpenWin_MachStatus")
        /** OpenWin_BaseFreq */
        ,OPEN_WIN_BASE_FREQ("OpenWin_BaseFreq")
        /** OpenWin_OverageFreq */
        ,OPEN_WIN_OVERAGE_FREQ("OpenWin_OverageFreq");
        // END 2022/10/13 M.Komatsu [QC#60078,ADD]

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

    /** TBL_NM */
    public enum TBL_NM {
        /** DS_MDL */
        DS_MDL;
    }

    /** COL_NM */
    public enum COL_NM {
        /** MDL_DESC_TEXT */
        MDL_DESC_TEXT
        /** MDL_ID */
        , MDL_ID;
    }

    /** Label */
    public static enum LBL {
        /** Model Name PopUp */
        MODEL_NAME_POPUP("Model Name PopUp")
        /** Model ID */
        , MODEL_ID("Model ID")
        /** Model Group Description */
        , MODEL_NAME("Model Name");

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

    /** MASS_UPDATE_LIST */
    public static enum MASS_UPDATE_LIST {
        /** Read Method */
        NSAL0710("NSAL0710", "Read Method")
        /** Start Read Capture Popup */
        , NSAL0460("NSAL0460", "Start Read Capture Popup")
        /** Terminate contract/machine */
        , NSAL0400("NSAL0400", "Terminate contract/machine")
        /** Renew contract / machine */
        , NSAL0690("NSAL0690", "Renew contract / machine")
        /** Cancel contract / machine */
        , NSAL0670("NSAL0670", "Cancel contract / machine")
        /** Put Contracts on Hold (at contract level) */
        , NSAL0630("NSAL0630", "Put Contracts on Hold (at contract level)")
        /** Put contract/machine on Billing Hold */
        , NSAL0700("NSAL0700", "Put contract/machine on Billing Hold")
        /** Update Contract Branch */
        , NSAL0640("NSAL0640", "Update Contract Branch")
        /** Update PO # contract / machine level */
        , NSAL0730("NSAL0730", "Update PO # contract / machine level")
        /** Update Credit Card Info contract / machine level */
        , NSAL0390("NSAL0390", "Update Credit Card Info contract / machine level")
        /** Setup Auto-Estimation on contract */
        , NSAL0650("NSAL0650", "Setup Auto-Estimation on contract")
        /** Add Notes on contract */
        , NSAL0660("NSAL0660", "Add Notes on contract")
        /** Invoicing Rules on machine base */
        , NSAL0750("NSAL0750", "Invoicing Rules on machine base")
        /** Update Bill To on contract/machine level */
        , NSAL0720("NSAL0720", "Update Bill To on contract/machine level")
        /** Escalation Rules contract/machine level */
        , NSAL0740("NSAL0740", "Escalation Rules contract/machine level")
        /** Renewal Rules contract/machine level */
        , NSAL0380("NSAL0380", "Renewal Rules contract/machine level")
        // START 2017/02/07 K.Kojima [QC#17303,ADD]
        /** Meter Upload */
        , ZYPL0210("ZYPL0210", "Meter Upload");
        // END 2017/02/07 K.Kojima [QC#17303,ADD]

        /** scrnId */
        private String scrnId;
        /** scrnNm */
        private String scrnNm;

        MASS_UPDATE_LIST(String scrnId, String scrnNm) {
            this.scrnId = scrnId;
            this.scrnNm = scrnNm;
        }

        /**
         * @return scrnId
         */
        public String getScrnId() {
            return scrnId;
        }

        /**
         * @return scrnNm
         */
        public String getScrnNm() {
            return scrnNm;
        }

    }

    // add start 2016/05/09 CSA Defect#4093
    /**
     * Please set at least one search criteria.
     */
    public static final String NSAM0017E = "NSAM0017E";
    // add end 2016/05/09 CSA Defect#4093

    // **************** private buttonName definition *****************

    /** NMAL6760_PRM_LENGTH : 15 */
    public static final int NMAL6760_PRM_LENGTH = 15;

    /** NSAL0010_PRM_LENGTH : 1 */
    public static final int NSAL0010_PRM_LENGTH = 1;

    /** NSAL0030_PRM_LENGTH : 17 */
    public static final int NSAL0030_PRM_LENGTH = 17;

    /** NSAL0300_PRM_LENGTH : 1 */
    public static final int NSAL0300_PRM_LENGTH = 1;

    /** NSAL0480_PRM_LENGTH : 1 */
    public static final int NSAL0480_PRM_LENGTH = 1;

    /** NSAL0610_PRM_LENGTH : 1 */
    public static final int NSAL0610_PRM_LENGTH = 1;

    /** MASS_SCREEN_PRM_LENGTH : 1 */
    public static final int MASS_SCREEN_PRM_LENGTH = 1;

    // add start 2016/07/01 CSA Defect#11261
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
     * Length 16
     */
    public static final int LEN_16 = 16;

    /**
     * Length 50
     */
    public static final int LEN_50 = 50;
    // add end 2016/07/01 CSA Defect#11261

    // START 2018/07/11 T.Wada [QC#26424,ADD]
    public static final String LAST_DAY_OF_A_MONTH = "99";
    public static final String LAST_DAY = "Last Day";
    // END 2018/07/11 T.Wada [QC#26424,ADD]

    // START 2022/10/13 M.Komatsu [QC#60537,ADD]
    /** Merchandise Search mode : All Mode */
    public static final String MDSE_SEARCH_MODE_ALL = "AL";
    // END 2022/10/13 M.Komatsu [QC#60537,ADD]
}
