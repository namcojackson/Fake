/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2010   Fujitsu         I.Kondo         Create          N/A
 * 11/04/2010   Fujitsu         I.Kondo         Update          DefID:M15
 * 03/17/2011   Fujitsu         K.Kimura        Update          DefID:1875
 * 2018/11/01   Fujitsu         S.Takami        Update          QC#28289
 * 2022/11/28	Hitachi			R.Takau			Update			QC#57272
 * </pre>
 */
package business.servlet.NFCL5140.constant;

/**
 * NFCL5140Constant interface.
 */
public interface NFCL5140Constant {

    /**
     */
    int NMAL6050_PARAM_LENGTH = 11;

    /**
     */
    int NMAL6050_PARAM_TBL_NM = 0;

    /**
     */
    int NMAL6050_PARAM_TBL_CD_COLUMN_CD = 1;

    /**
     */
    int NMAL6050_PARAM_TBL_CD_COLUMN_NM = 2;

    /**
     */
    int NMAL6050_PARAM_TBL_SORT_NUM_COLUMN_NM = 3;

    /**
     */
    int NMAL6050_PARAM_SCR_NM = 4;

    /**
     */
    int NMAL6050_PARAM_HDR_CD_LABEL = 5;

    /**
     */
    int NMAL6050_PARAM_HDR_NM_LABEL = 6;

    /**
     */
    int NMAL6050_PARAM_DTL_CD_LABEL = 7;

    /**
     */
    int NMAL6050_PARAM_DTL_NM_LABEL = 8;

    /**
     */
    int NMAL6050_PARAM_CONDITION_CD = 9;

    /**
     */
    int NMAL6050_PARAM_CONDITION_NM = 10;
    
    // START 2022/11/10  [QC#57252,ADD]
    /** @ is invalid. */
    static final String NFBM0041E = "NFBM0041E";
    
    /** [@] field is mandatory. */
    static final String ZZM9000E = "ZZM9000E";
    
    /**
     */
    int PARAM_INDEX_9 = 9;
    
    /**
     */
    int PARAM_INDEX_10 = 10;
    // END 2022/11/10  [QC#57252,ADD]
    
    /**
     */
    String PRM_OPEN_WIN_TOC_0 = "S21_ORG";

    /**
     */
    String PRM_OPEN_WIN_TOC_1 = "TOC_CD";

    /**
     */
    String PRM_OPEN_WIN_TOC_2 = "TOC_NM";

    /**
     */
    String PRM_OPEN_WIN_TOC_3 = "TOC_CD";

    /**
     */
    String PRM_OPEN_WIN_TOC_4 = "Dept Code Search";

    /**
     */
    String PRM_OPEN_WIN_TOC_5 = "Dept Code";

    /**
     */
    String PRM_OPEN_WIN_TOC_6 = "Dept Name";

    /**
     */
    String PRM_OPEN_WIN_TOC_7 = "Dept Code";

    /**
     */
    String PRM_OPEN_WIN_TOC_8 = "Dept Name";

    /**
     */
    String PRM_OPEN_WIN_PROD_0 = "COA_PROD";

    /**
     */
    String PRM_OPEN_WIN_PROD_1 = "COA_PROD_CD";

    /**
     */
    String PRM_OPEN_WIN_PROD_2 = "COA_PROD_NM";

    /**
     */
    String PRM_OPEN_WIN_PROD_3 = "COA_PROD_CD";

    /**
     */
    String PRM_OPEN_WIN_PROD_4 = "Prod Code Search";

    /**
     */
    String PRM_OPEN_WIN_PROD_5 = "Prod Code";

    /**
     */
    String PRM_OPEN_WIN_PROD_6 = "Prod Name";

    /**
     */
    String PRM_OPEN_WIN_PROD_7 = "Prod Code";

    /**
     */
    String PRM_OPEN_WIN_PROD_8 = "Prod Name";

    /**
     */
    String GUARD_SELECT = "Select_ConditionCode";
    
    // START 2022/11/11 R.Takau [QC#57252,ADD]
    /**
     */
    String BTN_A = "OpenWin_ChargeAccount";
    
    /**
     */
    String SLC_OTHER = "998";
    // END 2022/11/11 R.Takau [QC#57252,ADD]
    
    /**
     * TRX_TP_CD enum.
     */
    enum AR_TRX_TP_CD {
        /** ACC */
        ACC("1"),
        /** DED */
        DED("2"),
        /** ADJ */
        ADJ("3");

        /** value */
        private String value;

        /**
         * @param value String
         */
        private AR_TRX_TP_CD(String value) {
            this.value = value;
        }

        /**
         * @return String
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * Debug Message Level
     */
    int DEBUG_MSG_LVL = 1;

    /**
     * PARAMS enum.
     */
    enum PARAMS {
        /** NUM_0 */
        NUM_0(0)
        /** NUM_1 */
        , NUM_1(1)
        /** NUM_2 */
        , NUM_2(2)
        /** NUM_3 */
        , NUM_3(3)
        /** NUM_4 */
        , NUM_4(4)
        /** NUM_5 */
        , NUM_5(5)
        /** NUM_6 */
        , NUM_6(6)
        /** NUM_7 */
        , NUM_7(7)
        /** NUM_8 */
        , NUM_8(8)
        /** NUM_9 */
        , NUM_9(9)
        /** LENGTH */
        , LENGTH(10)
        // START 2018/11/01 S.Takami [QC#28289, Add]
        /** Offset Flag */
        , ADD_NUM_10(10)
        /** Additional Length */
        , ADD_LENGTH(11)
        // END   2018/11/01 S.Takami [QC#28289, Add]
        // START 2022/11/28 R.Takau [QC#57272,ADD]
        /** NUM_11 */
        , ADDED_NUM_11(11)
        /** Additional Length */
        , ADDED_LENGTH(12);
        // END 2022/11/28 R.Takau [QC#57272,ADD]
        /** value */
        private int value;

        /**
         * @param value int
         */
        private PARAMS(int value) {
            this.value = value;
        }

        /**
         * @return int
         */
        public int getValue() {
            return value;
        }
    }
}
