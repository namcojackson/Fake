/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1570.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelColumnStyle;

/**
 *<pre>
 * NWAL1570Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 * 2016/07/26   Fujitsu         K.Sato          Update          QC#11581
 * 2016/10/18   Fujitsu         K.Sato          Update          QC#15164
 * 2016/11/22   Fujitsu         K.Sato          Update          QC#15760
 * 2017/11/21   Fujitsu         T.Aoi           Update          QC#22550
 * 2017/12/13   Fujitsu         M.Ohno          Update          QC#22691
 * 2018/02/19   Fujitsu         K.Ishizuka      Update          QC#19913-2
 * 2018/02/22   Fujitsu         T.Aoi           Update          QC#21611
 * 2018/07/05   Fujitsu         M.Ishii         Update          QC#25786
 * 2018/08/01   Fujitsu         T.Aoi           Update          QC#26304
 * 2018/10/09   Fujitsu         Mz.Takahashi    Update          QC#28696
 * 2018/12/13   Fujitsu         Mz.Takahashi    Update          QC#29024
 * 2018/12/14   Fujitsu         Mz.Takahashi    Update          QC#29286
 * 2019/05/07   Fujitsu         Mz.Takahash     Update          QC#50031
 * 2019/12/09   Fujitsu         Y.Kanefusa      Update          S21_NA#54228
 *</pre>
 */
public class NWAL1570Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1570";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1570Scrn00";

    /** Screen ID 01 */
    public static final String SCRN_ID_01 = "NWAL1570Scrn01";

    /** Screen ID 00 */
    public static final String SCRN_ID_02 = "NWAL1570Scrn02";

    /** Screen ID Other */
    public static final String SCRN_ID_OTH = "Other Screen";

    /** Screen Table ID 01 */
    public static final String SCRN_TBL_ID_01 = "SCRN01HEAD";

    /** Screen Table ID 02 */
    public static final String SCRN_TBL_ID_02 = "SCRN02HEAD";

    // --------------------------------
    // Authority
    // --------------------------------
    /** All reference authority */
    public static final String ALL_REF_AUTH = "NWAL1570T010";

    /** Reference only team authority */
    public static final String REF_ONLY_TEAM_AUTH = "NWAL1570T020";

    /** Reference only self-registration authority */
    public static final String REF_ONLY_SELF_RGTN_AUTH = "NWAL1570T030";

    //--------------------------------
    // Message ID
    //--------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** 'Display By' data cannot be obtained. */
    public static final String NWAM0350E = "NWAM0350E";

    /** [@] is not selected. */
    public static final String NWAM0697E = "NWAM0697E";

    /** You can not [@] this record Because of [@] already exists. */
    public static final String NWAM0837E = "NWAM0837E";

    /** Please specify the period of date within [@] days. */
    public static final String NWAM0786E = "NWAM0786E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** ZZZM9003I The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";
    
    /** Select at least one of display columns from Preferred View Setting. */ // 2018/02/19 S21_NA#19913-2 Add
    public static final String NWAM0949E = "NWAM0949E";

    /**
     * FLG_ON_Y
     */
    public static final String Y = ZYPConstant.FLG_ON_Y;
    /**
     * FLG_OFF_N
     */
    public static final String N = ZYPConstant.FLG_OFF_N;
    /**
     * PERCENT
     */
    public static final String PERCENT = "%";
    /**
     * CONDITION_IS_NULL
     */
    public static final String CONDITION_IS_NULL = "-";
    /**
     * CONDITION_IS_NULL_DATE
     */
    public static final String CONDITION_IS_NULL_DATE = "99991231";
    /**
     * DATE_LENGTH
     */
    public static final int DATE_LENGTH = 8;
    /**
     * FETCH_SIZE_MAX
     */
    public static final int FETCH_SIZE_MAX = 1000;
    /**
     * LIMIT_DL_ROWNUM : 65000
     */
    public static final int LIMIT_DL_ROWNUM = 65000;
    /**
     * DISP_BY_ITEM_MAX
     */
    public static final int DISP_BY_ITEM_MAX = 250;
    /**
     * DISP_BY_ITEM_LABEL
     */
    public static final String DISP_BY_ITEM_LABEL = "dplyByItemLbNm";
    /**
     * DISP_BY_ITEM_NM
     */
    public static final String DISP_BY_ITEM_NM = "dplyByItemNm";
    /**
     * DISP_BY_ITEM_NM
     */
    public static final String DISPLAY_FLG = "DISPLAY_FLG";
    /**
     * MAX_RNG_DAYS
     */
    public static final String NWAL1570_MAX_RNG_DAYS = "NWAL1570_MAX_RNG_DAYS";

    /**
     * SPL_CHAR_TXT
     */
    public static final String NWAL1570_SPL_CHAR_TXT = "NWAL1570_SPL_CHAR_TXT";

    /**
     * BLANK
     */
    public static final String BLANK = " ";

    /**
     * PERIOD
     */
    public static final String PERIOD = ".";

    /**
     * comma
     */
    public static final String COMMA = ",";

    /**
     * hyphen
     */
    public static final String HYPHEN = "-";

    /**
     * equal
     */
    public static final String EQUQL = "=";

    /**
     * Display Character Overall Total Line
     */
    public static final String DPLY_FIRST_RECORD = "Overall Total";

    /**
     * ORD_HDR_DPLY_STS_NM
     */
    public static final String ORD_HDR_DPLY_STS_NM = "ORD_HDR_DPLY_STS_NM";

    /**
     * ORD_LINE_DPLY_STS_NM
     */
    public static final String ORD_LINE_DPLY_STS_NM = "ORD_LINE_DPLY_STS_NM";

    /**
     * ORD_RTRN_LINE_DPLY_STS_NM
     */
    public static final String ORD_RTRN_LINE_DPLY_STS_NM = "ORD_RTRN_LINE_DPLY_STS_NM";

    /**
     * DATA_SECURITY_PROFILE_KEY
     */
    public static final String DATA_SECURITY_PROFILE_KEY = BIZ_ID + "_S21DataSecurityProfile";

    /**
     * STS_NM_PEND_IMPT
     */
    public static final String STS_NM_PEND_IMPT = "PENDING IMPORT";

    /**
     * Display by Team Name
     */
    public static final String DISP_BY_TEAM_NM = "ORD_TEAM_MSTR_NM";

    /**
     * Display by Zone Name
     */
    public static final String DISP_BY_ZONE_NM = "ORD_ZN_DESC_TXT";

    /**
     * CSV file name
     */
    public static final String CSV_FILE_NAME = BIZ_ID;

    /**
     * KEY_GRP_ID_01
     */
    public static final String KEY_GRP_ID_01 = "KEY_GRP_ID_01";

    /**
     * KEY_GRP_ID_02
     */
    public static final String KEY_GRP_ID_02 = "KEY_GRP_ID_02";

    /**
     * KEY_GRP_ID_03
     */
    public static final String KEY_GRP_ID_03 = "KEY_GRP_ID_03";

    // 2018/10/09 QC#28696 Add Start
    /**
     * Search Condition From Time
     */
    public static final String SRCH_COND_FROM_TIME = "000000000";

    /**
     * Search Condition Thru Time
     */
    public static final String SRCH_COND_THRU_TIME = "235959999";
    // 2018/10/09 QC#28696 Add End

    // 2018/12/13 QC#29024 Add Start
    /**
     * Real Time inquiry Flag
     */
    public static final String NWAL1570_REALTIME_INQUIRY_FLG = "NWAL1570_REALTIME_INQUIRY_FLG";
    // 2018/12/13 QC#29024 Add End

    // QC#54228 2019/12/09 Add Start
    /**
     * Column index 0;Display Order #
     */
    public static final int COLUMN_INDEX_0 = 0;
    // QC#54228 2019/12/09 Add End

    /**
     * DPLY_BY_CONFIG
     */
    public static enum DPLY_BY_CONFIG {
        /** 1:Order Inquiry */
        TRNST_RTE_NUM("1"),
        /** 1:1st-Layer */
        TRNST_RTE_ORD_NUM("1");
        /** value */
        private String value;

        private DPLY_BY_CONFIG(String value) {
            this.value = value;
        }

        /**
         * getValue
         * @return String
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * Result Mode
     */
    public static enum RSLT_MODE {

        /** ORDER_INQUIRY */
        ORDER_INQUIRY("1", "Order Inquiry"),
        /** STATUS_SUMMARY */
        STATUS_SUMMARY("2", "Status Summary");
        /** rsltModeCd */
        String rsltModeCd;
        /** rsltModeNm */
        String rsltModeNm;

        private RSLT_MODE(String rsltModeCd, String rsltModeNm) {
            this.rsltModeCd = rsltModeCd;
            this.rsltModeNm = rsltModeNm;
        }

        /**
         * getRsltModeCd
         * @return String
         */
        public String getRsltModeCd() {
            return rsltModeCd;
        }

        /**
         * getRsltModeNm
         * @return String
         */
        public String getRsltModeNm() {
            return rsltModeNm;
        }
    }

    /**
     * Display Mode
     */
    public static enum DPLY_MODE {

        /** HEADER */
        HEADER("1", "Header"),
        /** CONFIG */
        CONFIG("2", "Config"),
        /** LINE */
        LINE("3", "Line");
        /** dplyModeCd */
        String dplyModeCd;
        /** dplyModeNm */
        String dplyModeNm;

        private DPLY_MODE(String dplyModeCd, String dplyModeNm) {
            this.dplyModeCd = dplyModeCd;
            this.dplyModeNm = dplyModeNm;
        }

        /**
         * getDplyModeCd
         * @return String
         */
        public String getDplyModeCd() {
            return dplyModeCd;
        }

        /**
         * getDplyModeNm
         * @return String
         */
        public String getDplyModeNm() {
            return dplyModeNm;
        }
    }

    /**
     * Header Status
     */
    public static enum HDR_STS {

        /** ENTERED */
        ENT("1", "ENTERED"),
        /** DI CHECK HOLD */
        DI_HLD("2", "DI CHECK HOLD"),
        /** VALIDATION HOLD */
        VLD("3", "VALIDATION HOLD"),
        // 2018/07/05 QC#25786 mod start
//        /** SUPPLY ABUSE HOLD */
//        SPLY_ABUSE("4", "SUPPLY ABUSE HOLD"),
        /** SUPPLY ENFORCEMENT HOLD */
        SPLY_ENFORCEMENT("4", "SUPPLY ENFORCEMENT HOLD"),
        // 2018/07/05 QC#25786 mod end
        /** PROFITABILITY HOLD */
        PRFT("5", "PROFITABILITY HOLD"),
        // 2017/12/13 QC#22691 mod start
//        /** CREDIT HOLD */
//        CR_HLD("6", "CREDIT HOLD"),
        /** CREDIT HOLD */
        CR_HLD("6", "CREDIT REVIEW"),
        // 2017/12/13 QC#22691 mod end
        /** PENDING RE-SUBMISSION */
        PEND_RE_SUBMT("7", "PENDING RE-SUBMISSION"),
        /** BOOKED */
        BOOK("8", "BOOKED"),
        /** CLOSED */
        CLO("9", "CLOSED"),
        /** CANCELLED */
        CANC("10", "CANCELLED");
        /** hdrStsCd */
        String hdrStsCd;
        /** hdrStsNm */
        String hdrStsNm;

        private HDR_STS(String hdrStsCd, String hdrStsNm) {
            this.hdrStsCd = hdrStsCd;
            this.hdrStsNm = hdrStsNm;
        }

        /**
         * getHdrStsCd
         * @return String
         */
        public String getHdrStsCd() {
            return hdrStsCd;
        }

        /**
         * getHdrStsNm
         * @return String
         */
        public String getHdrStsNm() {
            return hdrStsNm;
        }
    }

    /**
     * Line Status
     */
    public static enum LINE_STS {

        /** ENTERED */
        ENT("1", "ENTERED"),
        /** BOOKED */
        BOOK("2", "BOOKED"),
        // 2017/11/21 QC#22550 Add Start
        /** AWAITING DROP SHIP */
        AWAIT_DROP_SHIP("3", "AWAITING DROP SHIP"),
        // 2017/11/21 QC#22550 Add End
        // 2017/11/21 QC#22550 Mod Start
        ///** SO CANCELLED */
        //SO_CANC("3", "SO CANCELLED"),
        /** PENDING RE-ALLOCATION */
        PEND_RE_ALLOC("4", "PENDING RE-ALLOCATION"),
        // 2017/11/21 QC#22550 Mod End
        /** PO CANCELLED */
        PO_CANC("5", "PO CANCELLED"),
        /** PENDING FULFILLMENT */
        PEND_FUFL("6", "PENDING FULFILLMENT"),
        /** PENDING ALLOCATION */
        PEND_ALLOC("7", "PENDING ALLOCATION"),
        /** BACK ORDER */
        BO("8", "BACK ORDER"),
        /** WAITING PICK */
        PEND_PICK("9", "WAITING PICK"),
        /** DELIVERED TO SHOP */
        DELY_TO_SHOP("10", "DELIVERED TO SHOP"),
        /** IN SHOP/CONFIG */
        IN_SHOP_CONFIG("11", "IN SHOP/CONFIG"),
        /** PENDING SHIP */
        PEND_SHIP("12", "PENDING SHIP"),
        /** SHIPPED */
        SHIP("13", "SHIPPED"),
        /** PENDING DELIVERY CONFIRMATION */
        PEND_DELY_CONF("14", "PENDING DELIVERY CONFIRMATION"),
        /** PENDING INSTALLATION */
        PEND_ISTL("15", "PENDING INSTALLATION"),
        /** ON LOAN */
        ON_LOAN("16", "ON LOAN"),
        /** WAITING RECEIPT */
        WAIT_RCPT("17", "WAITING RECEIPT"),
        /** PENDING INVOICE */
        PEND_INV("18", "PENDING INVOICE"),
        /** BILLING HOLD */
        BLLG_HLD("19", "BILLING HOLD"),
        // 2017/11/21 QC#22550 Add Start
        /** PENDING DEALER INSTALL */
        PEND_DLR_ISTL("20", "PENDING DEALER INSTALL"),
        // 2017/11/21 QC#22550 Add End
        /** CLOSED */
        SHIP_CLO("21", "CLOSED"),
        /** CLOSED LOAN RETURN */
        CLO_LOAN_RTRN("22", "CLOSED LOAN RETURN"),
        /** CLOSED LOAN SOLD */
        CLO_LOAN_SOLD("23", "CLOSED LOAN SOLD"),
        // QC#11581 MOD Start
//        /** INVOICED */
//        INV("22", "INVOICED"),
        /** CANCELLED */
        CANC("24", "CANCELLED");
        // QC#11581 MOD End

        /** lineStsCd */
        String lineStsCd;
        /** lineStsNm */
        String lineStsNm;

        private LINE_STS(String lineStsCd, String lineStsNm) {
            this.lineStsCd = lineStsCd;
            this.lineStsNm = lineStsNm;
        }

        /**
         * getLineStsCd
         * @return String
         */
        public String getLineStsCd() {
            return lineStsCd;
        }

        /**
         * getLineStsNm
         * @return String
         */
        public String getLineStsNm() {
            return lineStsNm;
        }
    }

    /**
     * Return Line Status
     */
    public static enum RTRN_LINE_STS {

        /** ENTERED */
        ENT("1", "ENTERED"),
        /** BOOKED */
        BOOK("2", "BOOKED"),
        /** PENDING RETURN */
        PEND_RTRN("3", "PENDING RETURN"),
        /** PENDING INSPECTION */
        PEND_INSP("4", "PENDING INSPECTION"),
        /** RWS CANCELLED */
        RWS_CANC("5", "RWS CANCELLED"),
        /** PARTIAL RECEIVED */
        PRTL_RCV("6", "PARTIAL RECEIVED"),
        /** PENDING INVOICE */
        PEND_INV("7", "PENDING INVOICE"),
        /** BILLING HOLD */
        BLLG_HLD("8", "BILLING HOLD"),
        // QC#11581 MOD Start
//        /** INVOICED */
//        INV("9", "INVOICED"),
        /** CLOSED */
        CLO("9", "CLOSED"),
        /** CANCELLED */
        CANC("10", "CANCELLED");
        // QC#11581 MOD End
        /** rtrnLineStsCd */
        String rtrnLineStsCd;
        /** rtrnLineStsNm */
        String rtrnLineStsNm;

        private RTRN_LINE_STS(String rtrnLineStsCd, String rtrnLineStsNm) {
            this.rtrnLineStsCd = rtrnLineStsCd;
            this.rtrnLineStsNm = rtrnLineStsNm;
        }

        /**
         * getRtrnLineStsCd
         * @return String
         */
        public String getRtrnLineStsCd() {
            return rtrnLineStsCd;
        }

        /**
         * getRtrnLineStsNm
         * @return String
         */
        public String getRtrnLineStsNm() {
            return rtrnLineStsNm;
        }
    }

    /**
     * SQL_COL
     */
    public static enum SQL_COL {
        /** GLBL_CMPY_CD */
        GLBL_CMPY_CD
        /** DS_CLO_ORD_PK */
        , DS_CLO_ORD_PK
        /** SRC_REF_OR_CPO_ORD_NUM */
        , SRC_REF_OR_CPO_ORD_NUM
        /** CPO_DTL_LINE_NUM */
        , CPO_DTL_LINE_NUM
        /** CPO_DTL_LINE_SUB_NUM */
        , CPO_DTL_LINE_SUB_NUM
        /** DS_ORD_POSN_NUM */
        , DS_ORD_POSN_NUM
        /** DS_CPO_LINE_NUM */
        , DS_CPO_LINE_NUM
        /** DS_CPO_LINE_SUB_NUM */
        , DS_CPO_LINE_SUB_NUM
        /** DS_CPO_RTRN_LINE_NUM */
        , DS_CPO_RTRN_LINE_NUM
        /** DS_CPO_RTRN_LINE_SUB_NUM */
        , DS_CPO_RTRN_LINE_SUB_NUM
        /** DS_CPO_CONFIG_PK */
        , DS_CPO_CONFIG_PK
        /** SHPG_PLN_NUM */
        , SHPG_PLN_NUM
        /** PRCH_REQ_NUM */
        , PRCH_REQ_NUM
        /** PO_ORD_NUM */
        , PO_ORD_NUM
        /** SO_NUM */
        , SO_NUM
        /** SO_SLP_NUM */
        , SO_SLP_NUM
        /** INV_NUM */
        , INV_NUM
        /** INV_BOL_LINE_NUM */
        , INV_BOL_LINE_NUM
        /** INV_LINE_NUM */
        , INV_LINE_NUM
        /** INV_LINE_SUB_NUM */
        , INV_LINE_SUB_NUM
        /** INV_LINE_SUB_TRX_NUM */
        , INV_LINE_SUB_TRX_NUM
        /** DS_INV_SLS_CR_PK */
        , DS_INV_SLS_CR_PK
        /** ADD_ORIG_CPO_ORD_NUM */
        , ADD_ORIG_CPO_ORD_NUM
        /** CUST_ISS_PO_NUM */
        , CUST_ISS_PO_NUM
        /** LEASE_CMPY_PO_NUM */
        , LEASE_CMPY_PO_NUM
        /** SOLD_TO_CUST_ACCT_NM */
        , SOLD_TO_CUST_ACCT_NM
        /** SOLD_TO_CUST_ACCT_CD */
        , SOLD_TO_CUST_ACCT_CD
        /** SOLD_TO_CUST_LOC_CD */
        , SOLD_TO_CUST_LOC_CD
        /** SHIP_TO_CUST_ACCT_NM */
        , SHIP_TO_CUST_ACCT_NM
        /** SHIP_TO_CUST_ACCT_CD */
        , SHIP_TO_CUST_ACCT_CD
        /** SHIP_TO_CUST_LOC_CD */
        , SHIP_TO_CUST_LOC_CD
        /** BILL_TO_CUST_ACCT_NM */
        , BILL_TO_CUST_ACCT_NM
        /** BILL_TO_CUST_ACCT_CD */
        , BILL_TO_CUST_ACCT_CD
        /** BILL_TO_CUST_LOC_CD */
        , BILL_TO_CUST_LOC_CD
        /** COA_BR_CD */
        , COA_BR_CD
        /** COA_BR_DESC_TXT */
        , COA_BR_DESC_TXT
        /** COA_EXTN_CD */
        , COA_EXTN_CD
        /** COA_EXTN_DESC_TXT */
        , COA_EXTN_DESC_TXT
        /** FIRST_ORG_CD */
        , FIRST_ORG_CD
        /** FIRST_ORG_NM */
        , FIRST_ORG_NM
        /** SCD_ORG_CD */
        , SCD_ORG_CD
        /** SCD_ORG_NM */
        , SCD_ORG_NM
        /** THIRD_ORG_CD */
        , THIRD_ORG_CD
        /** THIRD_ORG_NM */
        , THIRD_ORG_NM
        /** FRTH_ORG_CD */
        , FRTH_ORG_CD
        /** FRTH_ORG_NM */
        , FRTH_ORG_NM
        /** FIFTH_ORG_CD */
        , FIFTH_ORG_CD
        /** FIFTH_ORG_NM */
        , FIFTH_ORG_NM
        /** SIXTH_ORG_CD */
        , SIXTH_ORG_CD
        /** SIXTH_ORG_NM */
        , SIXTH_ORG_NM
        /** SVNTH_ORG_CD */
        , SVNTH_ORG_CD
        /** SVNTH_ORG_NM */
        , SVNTH_ORG_NM
        /** EIGHTH_ORG_CD */
        , EIGHTH_ORG_CD
        /** EIGHTH_ORG_NM */
        , EIGHTH_ORG_NM
        /** NINTH_ORG_CD */
        , NINTH_ORG_CD
        /** NINTH_ORG_NM */
        , NINTH_ORG_NM
        /** TENTH_ORG_CD */
        , TENTH_ORG_CD
        /** TENTH_ORG_NM */
        , TENTH_ORG_NM
        /** ELVTH_ORG_CD */
        , ELVTH_ORG_CD
        /** ELVTH_ORG_NM */
        , ELVTH_ORG_NM
        /** SLS_REP_CD */
        // 2018/02/22 QC#21611 Mod Start
        //, SLS_REP_TOC_CD
        , SLS_REP_PSN_NUM
        // 2018/02/22 QC#21611 Mod End
        /** SLS_REP_TOC_NM */
        , SLS_REP_TOC_NM
        /** CPO_SRC_TP_CD */
        , CPO_SRC_TP_CD
        /** CPO_SRC_TP_DESC_TXT */
        , CPO_SRC_TP_DESC_TXT
        /** DS_ORD_CATG_CD */
        , DS_ORD_CATG_CD
        /** DS_ORD_CATG_DESC_TXT */
        , DS_ORD_CATG_DESC_TXT
        /** DS_ORD_TP_CD */
        , DS_ORD_TP_CD
        /** DS_ORD_TP_DESC_TXT */
        , DS_ORD_TP_DESC_TXT
        /** DS_ORD_RSN_CD */
        , DS_ORD_RSN_CD
        /** DS_ORD_RSN_DESC_TXT */
        , DS_ORD_RSN_DESC_TXT
        /** CSMP_CONTR_NUM */
        , CSMP_CONTR_NUM
        /** PRC_CONTR_NUM */
        , PRC_CONTR_NUM
        /** PRC_CONTR_NM */
        , PRC_CONTR_NM
        /** ORD_SRC_REF_NUM */
        , ORD_SRC_REF_NUM
        /** IMPT_STS_CD */
        , IMPT_STS_CD
        /** ZEROTH_PROD_CTRL_CD */
        , ZEROTH_PROD_CTRL_CD
        /** ZEROTH_PROD_CTRL_NM */
        , ZEROTH_PROD_CTRL_NM
        /** FIRST_PROD_CTRL_CD */
        , FIRST_PROD_CTRL_CD
        /** FIRST_PROD_CTRL_NM */
        , FIRST_PROD_CTRL_NM
        /** SCD_PROD_CTRL_CD */
        , SCD_PROD_CTRL_CD
        /** SCD_PROD_CTRL_NM */
        , SCD_PROD_CTRL_NM
        /** THIRD_PROD_CTRL_CD */
        , THIRD_PROD_CTRL_CD
        /** THIRD_PROD_CTRL_NM */
        , THIRD_PROD_CTRL_NM
        /** FRTH_PROD_CTRL_CD */
        , FRTH_PROD_CTRL_CD
        /** FRTH_PROD_CTRL_NM */
        , FRTH_PROD_CTRL_NM
        /** FIFTH_PROD_CTRL_CD */
        , FIFTH_PROD_CTRL_CD
        /** FIFTH_PROD_CTRL_NM */
        , FIFTH_PROD_CTRL_NM
        /** MDSE_CD */
        , MDSE_CD
        /** MDSE_DESC_SHORT_TXT */
        , MDSE_DESC_SHORT_TXT
        /** COA_PROD_CD */
        , COA_PROD_CD
        /** COA_PROD_DESC_TXT */
        , COA_PROD_DESC_TXT
        /** COA_MDSE_TP_CD */
        , COA_MDSE_TP_CD
        /** COA_MDSE_TP_DESC_TXT */
        , COA_MDSE_TP_DESC_TXT
        /** REV_COA_ACCT_CD */
        , REV_COA_ACCT_CD
        /** REV_TRIAL_BAL_TP_CD */
        , REV_TRIAL_BAL_TP_CD
        /** MDL_ID */
        , MDL_ID
        /** T_MDL_NM */
        , T_MDL_NM
        /** SER_NUM */
        , SER_NUM
        /** RTRN_RSN_CD */
        , RTRN_RSN_CD
        /** RTRN_RSN_DESC_TXT */
        , RTRN_RSN_DESC_TXT
        /** ACTL_SHIP_DT */
        , ACTL_SHIP_DT
        /** INV_DT */
        , INV_DT
        /** ORD_LINE_SRC_CD */
        , ORD_LINE_SRC_CD
        /** ORD_LINE_SRC_NM */
        , ORD_LINE_SRC_NM
        /** RTL_WH_CD */
        , RTL_WH_CD
        // 2018/12/14 QC#29286 Mod Start
        /** RTL_WH_NM */
        //, RTL_WH_NM
        /** RTL_WH_DESC_TXT */
        , RTL_WH_DESC_TXT
        // 2018/12/14 QC#29286 Mod End
        /** RTL_SWH_CD */
        , RTL_SWH_CD
        /** RTL_SWH_NM */
        , RTL_SWH_NM
        /** INVTY_LOC_CD */
        , INVTY_LOC_CD
        /** VND_NM */
        , VND_NM
        /** DPLY_VND_NM */
        , DPLY_VND_NM
        /** DROP_SHIP_FLG */
        , DROP_SHIP_FLG
        /** DS_CONTR_NUM */
        , DS_CONTR_NUM
        /** SVC_CONFIG_MSTR_PK */
        , SVC_CONFIG_MSTR_PK
        /** SVC_MACH_MSTR_PK */
        , SVC_MACH_MSTR_PK
        /** ORD_TEAM_DESC_TXT */
        , ORD_TEAM_DESC_TXT
        /** ORD_ZN_DESC_TXT */
        , ORD_ZN_DESC_TXT
        /** CRAT_BY_USR_ID */
        , CRAT_BY_USR_ID
        /** CPO_ORD_TS */
        , CPO_ORD_TS
        /** ORD_BOOK_TS */
        , ORD_BOOK_TS
        /** ORD_HDR_STS_CD */
        , ORD_HDR_STS_CD
        /** ORD_LINE_STS_CD */
        , ORD_LINE_STS_CD
        /** RTRN_LINE_STS_CD */
        , RTRN_LINE_STS_CD
        /** ORD_HDR_DPLY_STS_NM */
        , ORD_HDR_DPLY_STS_NM
        /** ORD_LINE_DPLY_STS_NM */
        , ORD_LINE_DPLY_STS_NM
        /** ORD_RTRN_LINE_DPLY_STS_NM */
        , ORD_RTRN_LINE_DPLY_STS_NM
        /** CONFIG_CATG_CD */
        , CONFIG_CATG_CD
        /** SLS_REP_OR_SLS_TEAM_TOC_CD */
        , SLS_REP_OR_SLS_TEAM_TOC_CD
        /** SLS_REP_OR_SLS_TEAM_TOC_NM */
        , SLS_REP_OR_SLS_TEAM_TOC_NM
        /** ISTL_DT */
        , ISTL_DT
        /** RDD_DT */
        , RDD_DT
        /** PSD_DT */
        , PSD_DT
        /** PDD_DT */
        , PDD_DT
        /** PRC_CATG_CD */
        , PRC_CATG_CD
        /** PRC_CATG_DESC_TXT */
        , PRC_CATG_DESC_TXT
        /** ORD_QTY */
        , ORD_QTY
        /** AMT */
        , AMT
        /** CR_HLD_QTY */
        , CR_HLD_QTY
        /** CR_CHK_QTY */
        , CR_CHK_QTY
        /** SLS_HLD_QTY */
        , SLS_HLD_QTY
        /** ORD_AGING_BCKT_DESC_TXT */
        , ORD_AGING_BCKT_DESC_TXT
        /** ENT_CPO_DTL_FUNC_NET_AMT */
        , ENT_CPO_DTL_FUNC_NET_AMT
        /** ENT_CPO_DTL_FUNC_SLS_AMT */
        , ENT_CPO_DTL_FUNC_SLS_AMT
        /** SP_FUNC_UNIT_PRC_AMT */
        , SP_FUNC_UNIT_PRC_AMT
        /** SP_FUNC_NET_UNIT_PRC_AMT */
        , SP_FUNC_NET_UNIT_PRC_AMT
        /** INV_LINE_FUNC_NET_AMT */
        , INV_LINE_FUNC_NET_AMT
        /** FUNC_GRS_TOT_PRC_AMT */
        , FUNC_GRS_TOT_PRC_AMT
        /** FUNC_SLS_CR_AMT */
        , FUNC_SLS_CR_AMT
        /** TRX_CD */
        , TRX_CD
        /** TRX_RSN_CD */
        , TRX_RSN_CD
        /** DFRD_REV_FLG */
        , DFRD_REV_FLG
        /** LINE_BIZ_TP_CD */
        , LINE_BIZ_TP_CD
        /** LINE_BIZ_TP_DESC_TXT */
        , LINE_BIZ_TP_DESC_TXT
        /** DS_ORD_LINE_CATG_CD */
        , DS_ORD_LINE_CATG_CD
        /** DS_ORD_LINE_CATG_DESC_TXT */
        , DS_ORD_LINE_CATG_DESC_TXT
        // 2018/08/01 QC#26304 Add Start
        /** AQU_NUM */
        , AQU_NUM
        // 2018/08/01 QC#26304 Add End
        /** GROUPING_ID */
        , GROUPING_ID
        /** GROUPING01 */
        , GROUPING01
        /** GROUPING02 */
        , GROUPING02
        /** GROUPING03 */
        , GROUPING03
        /** DPLY_BY_01_ITEM_CD */
        , DPLY_BY_01_ITEM_CD
        /** DPLY_BY_02_ITEM_CD */
        , DPLY_BY_02_ITEM_CD
        /** DPLY_BY_03_ITEM_CD */
        , DPLY_BY_03_ITEM_CD
        /** DPLY_BY_01_ITEM_NM */
        , DPLY_BY_01_ITEM_NM
        /** DPLY_BY_02_ITEM_NM */
        , DPLY_BY_02_ITEM_NM
        /** DPLY_BY_03_ITEM_NM */
        , DPLY_BY_03_ITEM_NM
        /** DPLY_BY_01_SUM_LINE_FLG */
        , DPLY_BY_01_SUM_LINE_FLG
        /** DPLY_BY_02_SUM_LINE_FLG */
        , DPLY_BY_02_SUM_LINE_FLG
        /** DPLY_BY_03_SUM_LINE_FLG */
        , DPLY_BY_03_SUM_LINE_FLG
        /** PEND_IMPT_SMRY_AMT */
        , PEND_IMPT_SMRY_AMT
        /** ENT_SMRY_AMT */
        , ENT_SMRY_AMT
        /** DI_HLD_SMRY_AMT */
        , DI_HLD_SMRY_AMT
        /** VLD_SMRY_AMT */
        , VLD_SMRY_AMT
        /** PRFT_SMRY_AMT */
        , PRFT_SMRY_AMT
        /** CR_HLD_SMRY_AMT */
        , CR_HLD_SMRY_AMT
        /** SPLY_ABUSE_SMRY_AMT */
        , SPLY_ABUSE_SMRY_AMT
        /** PEND_RE_SUBMT_SMRY_AMT */
        , PEND_RE_SUBMT_SMRY_AMT
        /** BOOK_SMRY_AMT */
        , BOOK_SMRY_AMT
        // 2017/11/21 QC#22550 Add Start
        /** AWAIT_DROP_SHIP_SMRY_AMT */
        , AWAIT_DROP_SHIP_SMRY_AMT
        // 2017/11/21 QC#22550 Add End
        // 2017/11/21 QC#22550 Mod Start
        ///** SO_CANC_SMRY_AMT */
        //, SO_CANC_SMRY_AMT
        /** PEND_RE_ALLOC_SMRY_AMT */
        , PEND_RE_ALLOC_SMRY_AMT
        // 2017/11/21 QC#22550 Add End
        /** PO_CANC_SMRY_AMT */
        , PO_CANC_SMRY_AMT
        /** PEND_FUFL_SMRY_AMT */
        , PEND_FUFL_SMRY_AMT
        /** PEND_ALLOC_SMRY_AMT */
        , PEND_ALLOC_SMRY_AMT
        /** BO_SMRY_AMT */
        , BO_SMRY_AMT
        /** PEND_PICK_SMRY_AMT */
        , PEND_PICK_SMRY_AMT
        /** DELY_TO_SHOP_SMRY_AMT */
        , DELY_TO_SHOP_SMRY_AMT
        /** IN_SHOP_CONFIG_SMRY_AMT */
        , IN_SHOP_CONFIG_SMRY_AMT
        /** PEND_SHIP_SMRY_AMT */
        , PEND_SHIP_SMRY_AMT
        /** SHIP_SMRY_AMT */
        , SHIP_SMRY_AMT
        /** PEND_DELY_CONF_SMRY_AMT */
        , PEND_DELY_CONF_SMRY_AMT
        /** PEND_ISTL_SMRY_AMT */
        , PEND_ISTL_SMRY_AMT
        /** ON_LOAN_SMRY_AMT */
        , ON_LOAN_SMRY_AMT
        /** WAIT_RCPT_SMRY_AMT */
        , WAIT_RCPT_SMRY_AMT
        /** PEND_RTRN_SMRY_AMT */
        , PEND_RTRN_SMRY_AMT
        /** PEND_INSP_SMRY_AMT */
        , PEND_INSP_SMRY_AMT
        /** RWS_CANC_SMRY_AMT */
        , RWS_CANC_SMRY_AMT
        /** PRTL_RCV_SMRY_AMT */
        , PRTL_RCV_SMRY_AMT
        /** PEND_INV_SMRY_AMT */
        , PEND_INV_SMRY_AMT
        /** BLLG_HLD_SMRY_AMT */
        , BLLG_HLD_SMRY_AMT
        // 2017/11/21 QC#22550 Add Start
        /** PEND_DLR_ISTL_SMRY_AMT */
        , PEND_DLR_ISTL_SMRY_AMT
        // 2017/11/21 QC#22550 Add End
        /** SHIP_CLO_SMRY_AMT */
        , SHIP_CLO_SMRY_AMT
        /** CLO_LOAN_RTRN_SMRY_AMT */
        , CLO_LOAN_RTRN_SMRY_AMT
        /** CLO_LOAN_SOLD_SMRY_AMT */
        , CLO_LOAN_SOLD_SMRY_AMT
        /** INV_SMRY_AMT */
        , INV_SMRY_AMT
        /** DEFER_SMRY_AMT */
        , DEFER_SMRY_AMT
        /** CLO_SMRY_AMT */
        , CLO_SMRY_AMT
        /** CANC_SMRY_AMT */
        , CANC_SMRY_AMT
        /** PEND_IMPT_SMRY_CNT */
        , PEND_IMPT_SMRY_CNT
        /** ENT_SMRY_CNT */
        , ENT_SMRY_CNT
        /** DI_HLD_SMRY_CNT */
        , DI_HLD_SMRY_CNT
        /** VLD_SMRY_CNT */
        , VLD_SMRY_CNT
        /** PRFT_SMRY_CNT */
        , PRFT_SMRY_CNT
        /** CR_HLD_SMRY_CNT */
        , CR_HLD_SMRY_CNT
        /** SPLY_ABUSE_SMRY_CNT */
        , SPLY_ABUSE_SMRY_CNT
        /** PEND_RE_SUBMT_SMRY_CNT */
        , PEND_RE_SUBMT_SMRY_CNT
        /** BOOK_SMRY_CNT */
        , BOOK_SMRY_CNT
        // 2017/11/21 QC#22550 Add Start
        /** AWAIT_DROP_SHIP_SMRY_CNT */
        , AWAIT_DROP_SHIP_SMRY_CNT
        // 2017/11/21 QC#22550 Add End
        // 2017/11/21 QC#22550 Mod Start
        ///** SO_CANC_SMRY_CNT */
        //, SO_CANC_SMRY_CNT
        /** PEND_RE_ALLOC_SMRY_CNT */
        , PEND_RE_ALLOC_SMRY_CNT
        // 2017/11/21 QC#22550 Mod End
        /** PO_CANC_SMRY_CNT */
        , PO_CANC_SMRY_CNT
        /** PEND_FUFL_SMRY_CNT */
        , PEND_FUFL_SMRY_CNT
        /** PEND_ALLOC_SMRY_CNT */
        , PEND_ALLOC_SMRY_CNT
        /** BO_SMRY_CNT */
        , BO_SMRY_CNT
        /** PEND_PICK_SMRY_CNT */
        , PEND_PICK_SMRY_CNT
        /** DELY_TO_SHOP_SMRY_CNT */
        , DELY_TO_SHOP_SMRY_CNT
        /** IN_SHOP_CONFIG_SMRY_CNT */
        , IN_SHOP_CONFIG_SMRY_CNT
        /** PEND_SHIP_SMRY_CNT */
        , PEND_SHIP_SMRY_CNT
        /** SHIP_SMRY_CNT */
        , SHIP_SMRY_CNT
        /** PEND_DELY_CONF_SMRY_CNT */
        , PEND_DELY_CONF_SMRY_CNT
        /** PEND_ISTL_SMRY_CNT */
        , PEND_ISTL_SMRY_CNT
        /** ON_LOAN_SMRY_CNT */
        , ON_LOAN_SMRY_CNT
        /** WAIT_RCPT_SMRY_CNT */
        , WAIT_RCPT_SMRY_CNT
        /** PEND_RTRN_SMRY_CNT */
        , PEND_RTRN_SMRY_CNT
        /** PEND_INSP_SMRY_CNT */
        , PEND_INSP_SMRY_CNT
        /** RWS_CANC_SMRY_CNT */
        , RWS_CANC_SMRY_CNT
        /** PRTL_RCV_SMRY_CNT */
        , PRTL_RCV_SMRY_CNT
        /** PEND_INV_SMRY_CNT */
        , PEND_INV_SMRY_CNT
        /** BLLG_HLD_SMRY_CNT */
        , BLLG_HLD_SMRY_CNT
        // 2017/11/21 QC#22550 Add Start
        /** PEND_DLR_ISTL_SMRY_CNT */
        , PEND_DLR_ISTL_SMRY_CNT
        // 2017/11/21 QC#22550 Add ENd
        /** SHIP_CLO_SMRY_CNT */
        , SHIP_CLO_SMRY_CNT
        /** INV_SMRY_CNT */
        , INV_SMRY_CNT
        /** DEFER_SMRY_CNT */
        , DEFER_SMRY_CNT
        /** CLO_SMRY_CNT */
        , CLO_SMRY_CNT
        /** CLO_LOAN_RTRN_SMRY_CNT */
        , CLO_LOAN_RTRN_SMRY_CNT
        /** CLO_LOAN_SOLD_SMRY_CNT */
        , CLO_LOAN_SOLD_SMRY_CNT
        /** CANC_SMRY_CNT */
        , CANC_SMRY_CNT;
    }

    /**
     * SEARCH_STATUS
     *
     */
    public static enum SEARCH_STATUS {

        /** isOpenOrder */
        OPEN_ORD        ("isOpenOrder"         , "", 1),
        /** isCloseOrder */
        CLO_ORD         ("isCloseOrder"        , "", 2),
        /** isPendingImportOrder */
        PEND_IMPT_ORD   ("isPendingImportOrder", "", 3),
        
        /** Quick Column */
        QUICK           ("isQuick", "", 4);

        /** value */
        private String value;
        /** qtyCondition */
        private String qtyCondition;
        /** type */
        private int type;

        private SEARCH_STATUS(String value, String qtyCondition, int type) {
            this.value = value;
            this.qtyCondition = qtyCondition;
            this.type = type;
        }

        /**
         * getType
         * @return int
         */
        public int getType() {
            return type;
        }

        /**
         * getValue
         * @return String
         */
        public String getValue() {
            return value;
        }

        /**
         * getQtyCondition
         * @return String
         */
        public String getQtyCondition() {
            return qtyCondition;
        }
    }

    /**
     * SEARCH_COLUMN
     *
     */
    public static enum SEARCH_COLUMN {

        /** RESULT_NOT_FOUND */
        RESULT_NOT_FOUND

        // Header Search Criteria
        /** ORDER_NUMBER */
        , ORDER_NUMBER
        /** ORIGINAL_ORDER_NUMBER */
        , ORIGINAL_ORDER_NUMBER
        /** CUSTOMER_PO_NUMBER */
        , CUSTOMER_PO_NUMBER
        /** LEASE_PO_NUMBER */
        , LEASE_PO_NUMBER
        /** SOLD_TO_NAME */
        , SOLD_TO_NAME
        /** SOLD_TO_ACCOUNT */
        , SOLD_TO_ACCOUNT
        /** SOLD_TO_LOCATION */
        , SOLD_TO_LOCATION
        /** SHIP_TO_NAME */
        , SHIP_TO_NAME
        /** SHIP_TO_ACCOUNT */
        , SHIP_TO_ACCOUNT
        /** SHIP_TO_LOCATION */
        , SHIP_TO_LOCATION
        /** BILL_TO_NAME */
        , BILL_TO_NAME
        /** BILL_TO_ACCOUNT */
        , BILL_TO_ACCOUNT
        /** BILL_TO_LOCATION */
        , BILL_TO_LOCATION
        /** BUSINESS_UNIT */
        , BUSINESS_UNIT
        /** BRANCH */
        , BRANCH
        /** SALES_REP */
        , SALES_REP
        /** ORDER_SOURCE */
        , ORDER_SOURCE
        /** LOB */
        , LOB
        /** DS_ORDER_CATEGORY */
        , DS_ORDER_CATEGORY
        /** DS_ORDER_TYPE */
        , DS_ORDER_TYPE
        /** DS_SUB_REASON */
        , DS_SUB_REASON
        /** CSMP_NUMBER */
        , CSMP_NUMBER
        /** PRICE_CONTRACT_NUMBER */
        , PRICE_CONTRACT_NUMBER
        /** IMPORT_SOURCE_NUMBER */
        , IMPORT_SOURCE_NUMBER
        // 2018/08/01 QC#26304 Add Start
        /** AQU_NUMER */
        , AQU_NUMBER
        // 2018/08/01 QC#26304 Add End

        // Line Search Criteria
        /** ITEM_NAME */
        , ITEM_NAME
        /** ITEM_CODE */
        , ITEM_CODE
        /** PRODUCT_LINE_GROUP */
        , PRODUCT_LINE_GROUP
        /** PRODUCT_LINE */
        , PRODUCT_LINE
        /** PRODUCT_LEVEL_2 */
        , PRODUCT_LEVEL_2
        /** PRODUCT_LEVEL_3 */
        , PRODUCT_LEVEL_3
        /** PRODUCT_LEVEL_4 */
        , PRODUCT_LEVEL_4
        /** COA_PRODUCT */
        , COA_PRODUCT
        /** COA_MERCHANDISE */
        , COA_MERCHANDISE
        /** MODEL */
        , MODEL
        /** SERIAL_NUMBER */
        , SERIAL_NUMBER
        /** RETURN_REASON */
        , RETURN_REASON
        /** LINE_CATEGORY */
        , LINE_CATEGORY
        /** LINE_SOURCE */
        , LINE_SOURCE
        /** WH */
        , WH
        /** SUB_WH */
        , SUB_WH
        /** PO_VENDOR */
        , PO_VENDOR
        /** PO_ORD_NUM */
        , PO_ORD_NUM
        /** SO_NUMBER */
        , SO_NUMBER
        /** INVOICE_NUMBER */
        , INVOICE_NUMBER
        /** CONTRACT_NUMBER */
        , CONTRACT_NUMBER
        /** CONFIG_NUMBER */
        , CONFIG_NUMBER
        /** SVC_MACH_MSTR_PK */
        , SVC_MACH_MSTR_PK

        // Order Team
        /** TEAM */
        , TEAM
        /** ZONE */
        , ZONE
        /** CREATED_BY */
        , CREATED_BY

        // Status
        /** HEADER_STATUS */
        , HEADER_STATUS
        /** LINE_STATUS */
        , LINE_STATUS
        /** RETURN_LINE_STATUS */
        , RETURN_LINE_STATUS

        // Date Criteria
        /** ORDERED_DATE */
        , ORDERED_DATE
        /** BOOKED_DATE */
        , BOOKED_DATE
        /** SHIPPED_DATE */
        , SHIPPED_DATE
        /** INVOICE_DATE */
        , INVOICE_DATE
        /** IMPORT_DATE */
        , IMPORT_DATE

        /** Aging */
        , ORD_AGING

        // Summary Amount
        /** PEND_IMPT_SMRY_AMT */
        , PEND_IMPT_SMRY_AMT
        /** ENT_SMRY_AMT */
        , ENT_SMRY_AMT
        /** DI_HLD_SMRY_AMT */
        , DI_HLD_SMRY_AMT
        /** VLD_SMRY_AMT */
        , VLD_SMRY_AMT
        /** PRFT_SMRY_AMT */
        , PRFT_SMRY_AMT
        /** CR_HLD_SMRY_AMT */
        , CR_HLD_SMRY_AMT
        /** SPLY_ABUSE_SMRY_AMT */
        , SPLY_ABUSE_SMRY_AMT
        /** PEND_RE_SUBMT_SMRY_AMT */
        , PEND_RE_SUBMT_SMRY_AMT
        /** BOOK_SMRY_AMT */
        , BOOK_SMRY_AMT
        // 2017/11/21 QC#22550 Add Start
        /** AWAIT_DROP_SHIP_SMRY_AMT */
        , AWAIT_DROP_SHIP_SMRY_AMT
        // 2017/11/21 QC#22550 Add End
        // 2017/11/21 QC#22550 Mod Start
        ///** SO_CANC_SMRY_AMT */
        //, SO_CANC_SMRY_AMT
        /** PEND_RE_ALLOC_SMRY_AMT */
        , PEND_RE_ALLOC_SMRY_AMT
        // 2017/11/21 QC#22550 Mod End
        /** PO_CANC_SMRY_AMT */
        , PO_CANC_SMRY_AMT
        /** PEND_FUFL_SMRY_AMT */
        , PEND_FUFL_SMRY_AMT
        /** PEND_ALLOC_SMRY_AMT */
        , PEND_ALLOC_SMRY_AMT
        /** BO_SMRY_AMT */
        , BO_SMRY_AMT
        /** PEND_PICK_SMRY_AMT */
        , PEND_PICK_SMRY_AMT
        /** DELY_TO_SHOP_SMRY_AMT */
        , DELY_TO_SHOP_SMRY_AMT
        /** IN_SHOP_CONFIG_SMRY_AMT */
        , IN_SHOP_CONFIG_SMRY_AMT
        /** PEND_SHIP_SMRY_AMT */
        , PEND_SHIP_SMRY_AMT
        /** SHIP_SMRY_AMT */
        , SHIP_SMRY_AMT
        /** PEND_DELY_CONF_SMRY_AMT */
        , PEND_DELY_CONF_SMRY_AMT
        /** PEND_ISTL_SMRY_AMT */
        , PEND_ISTL_SMRY_AMT
        /** ON_LOAN_SMRY_AMT */
        , ON_LOAN_SMRY_AMT
        /** WAIT_RCPT_SMRY_AMT */
        , WAIT_RCPT_SMRY_AMT
        /** PEND_RTRN_SMRY_AMT */
        , PEND_RTRN_SMRY_AMT
        /** PEND_INSP_SMRY_AMT */
        , PEND_INSP_SMRY_AMT
        /** RWS_CANC_SMRY_AMT */
        , RWS_CANC_SMRY_AMT
        /** PEND_INV_SMRY_AMT */
        , PEND_INV_SMRY_AMT
        /** BLLG_HLD_SMRY_AMT */
        , BLLG_HLD_SMRY_AMT
        // 2017/11/21 QC#22550 Add Start
        /** PEND_DLR_ISTL_SMRY_AMT */
        , PEND_DLR_ISTL_SMRY_AMT
        // 2017/11/21 QC#22550 Add End
        /** SHIP_CLO_SMRY_AMT */
        , SHIP_CLO_SMRY_AMT
        /** INV_SMRY_AMT */
        , INV_SMRY_AMT
        /** CLO_SMRY_AMT */
        , CLO_SMRY_AMT
        /** CLO_LOAN_RTRN_SMRY_AMT */
        , CLO_LOAN_RTRN_SMRY_AMT
        /** CLO_LOAN_SOLD_SMRY_AMT */
        , CLO_LOAN_SOLD_SMRY_AMT
        /** CANC_SMRY_AMT */
        , CANC_SMRY_AMT

        // Summary Count
        /** PEND_IMPT_SMRY_CNT */
        , PEND_IMPT_SMRY_CNT
        /** ENT_SMRY_CNT */
        , ENT_SMRY_CNT
        /** DI_HLD_SMRY_CNT */
        , DI_HLD_SMRY_CNT
        /** VLD_SMRY_CNT */
        , VLD_SMRY_CNT
        /** PRFT_SMRY_CNT */
        , PRFT_SMRY_CNT
        /** CR_HLD_SMRY_CNT */
        , CR_HLD_SMRY_CNT
        /** SPLY_ABUSE_SMRY_CNT */
        , SPLY_ABUSE_SMRY_CNT
        /** PEND_RE_SUBMT_SMRY_CNT */
        , PEND_RE_SUBMT_SMRY_CNT
        /** BOOK_SMRY_CNT */
        , BOOK_SMRY_CNT
        // 2017/11/21 QC#22550 Add Start
        /** AWAIT_DROP_SHIP_SMRY_CNT */
        , AWAIT_DROP_SHIP_SMRY_CNT
        // 2017/11/21 QC#22550 Add End
        // 2017/11/21 QC#22550 Mod Start
        ///** SO_CANC_SMRY_CNT */
        //, SO_CANC_SMRY_CNT
        /** PEND_RE_ALLOC_SMRY_CNT */
        , PEND_RE_ALLOC_SMRY_CNT
        // 2017/11/21 QC#22550 Mod End
        /** PO_CANC_SMRY_CNT */
        , PO_CANC_SMRY_CNT
        /** PEND_FUFL_SMRY_CNT */
        , PEND_FUFL_SMRY_CNT
        /** PEND_ALLOC_SMRY_CNT */
        , PEND_ALLOC_SMRY_CNT
        /** BO_SMRY_CNT */
        , BO_SMRY_CNT
        /** PEND_PICK_SMRY_CNT */
        , PEND_PICK_SMRY_CNT
        /** DELY_TO_SHOP_SMRY_CNT */
        , DELY_TO_SHOP_SMRY_CNT
        /** IN_SHOP_CONFIG_SMRY_CNT */
        , IN_SHOP_CONFIG_SMRY_CNT
        /** PEND_SHIP_SMRY_CNT */
        , PEND_SHIP_SMRY_CNT
        /** SHIP_SMRY_CNT */
        , SHIP_SMRY_CNT
        /** PEND_DELY_CONF_SMRY_CNT */
        , PEND_DELY_CONF_SMRY_CNT
        /** PEND_ISTL_SMRY_CNT */
        , PEND_ISTL_SMRY_CNT
        /** ON_LOAN_SMRY_CNT */
        , ON_LOAN_SMRY_CNT
        /** WAIT_RCPT_SMRY_CNT */
        , WAIT_RCPT_SMRY_CNT
        /** PEND_RTRN_SMRY_CNT */
        , PEND_RTRN_SMRY_CNT
        /** PEND_INSP_SMRY_CNT */
        , PEND_INSP_SMRY_CNT
        /** RWS_CANC_SMRY_CNT */
        , RWS_CANC_SMRY_CNT
        /** PEND_INV_SMRY_CNT */
        , PEND_INV_SMRY_CNT
        /** BLLG_HLD_SMRY_CNT */
        , BLLG_HLD_SMRY_CNT
        // 2017/11/21 QC#22550 Add Start
        /** PEND_DLR_ISTL_SMRY_CNT */
        , PEND_DLR_ISTL_SMRY_CNT
        // 2017/11/21 QC#22550 Add End
        /** SHIP_CLO_SMRY_CNT */
        , SHIP_CLO_SMRY_CNT
        /** INV_SMRY_CNT */
        , INV_SMRY_CNT
        /** CLO_SMRY_CNT */
        , CLO_SMRY_CNT
        /** CLO_LOAN_RTRN_SMRY_CNT */
        , CLO_LOAN_RTRN_SMRY_CNT
        /** CLO_LOAN_SOLD_SMRY_CNT */
        , CLO_LOAN_SOLD_SMRY_CNT
        /** CANC_SMRY_CNT */
        , CANC_SMRY_CNT
    }

    // 2019/05/07 QC#50031 Add Start
    public static final int[][] EXCEL_COLUMNTYPE_INQUIRY = new int[][] {
         {  3 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } //Amount
    };

    public static final int[][] EXCEL_COLUMNTYPE_SUMMARY = new int[][] {
         {  1 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Pending Import Amount
        ,{  2 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Entered Amount
        ,{  3 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // DI Hold Amount
        ,{  4 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Validation Amount
        ,{  5 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Profitability Amount
        ,{  6 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Credit Review Amount
        ,{  7 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Supply Enforcement Amount
        ,{  8 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Pending Re Submission Amount
        ,{  9 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Awaiting Drop Ship Amount
        ,{ 10 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Pending Re Allocation Amount
        ,{ 11 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // PO Canceled Amount
        ,{ 12 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Pending Fulfillment Amount
        ,{ 13 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Pending Allocation Amount
        ,{ 14 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Back Ordered Amount
        ,{ 15 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Pending Pick Amount
        ,{ 16 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Pending Invoice Amount
        ,{ 17 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Delivered to Shop Amount
        ,{ 18 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // In Shop/ Config Amount
        ,{ 19 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Pending Shipment Amount
        ,{ 20 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Shipped Amount
        ,{ 21 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Pending Delivery Confirmation Amount
        ,{ 22 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Pending Installation Amount
        ,{ 23 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // On Loan Amount
        ,{ 24 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Waiting Receipt Amount
        ,{ 25 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Pending Return Amount
        ,{ 26 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Pending Inspection Amount
        ,{ 27 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // RWS Canceled Amount
        ,{ 28 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Partial Received Amount
        ,{ 29 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Billing Hold Amount
        ,{ 30 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Pending Dealer Install Amount
        ,{ 31 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Closed Amount
        ,{ 32 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Closed Loan Return Amount
        ,{ 33 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Closed Loan Sold Amount
        ,{ 34 ,ZYPExcelColumnStyle.EXCEL_STYLE_DECIMAL_TWODIGIT } // Canceled Amount
    };
    // 2019/05/07 QC#50031 Add End
    
}
