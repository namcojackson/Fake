/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1880.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 *<pre>
 * NWAL1880Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/26   Fujitsu         K.Sato          Create          N/A
 * 2016/07/27   Fujitsu         K.Sato          Update          QC#11581
 *</pre>
 */
public class NWAL1880Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1880";

    // --------------------------------
    // Authority
    // --------------------------------
    /** All reference authority */
    public static final String ALL_REF_AUTH = "NWAL1880T010";

    /** Reference only team authority */
    public static final String REF_ONLY_TEAM_AUTH = "NWAL1880T020";

    /** Reference only self-registration authority */
    public static final String REF_ONLY_SELF_RGTN_AUTH = "NWAL1880T030";

    //--------------------------------
    // Message ID
    //--------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** 'Display By' data cannot be obtained. */
    public static final String NWAM0350E = "NWAM0350E";

    /**
     * FETCH_SIZE_MAX
     */
    public static final int FETCH_SIZE_MAX = 1000;

    /**
     * SPL_CHAR_TXT
     */
    public static final String NWAL1880_SPL_CHAR_TXT = "NWAL1880_SPL_CHAR_TXT";

    /**
     * hyphen
     */
    public static final String HYPHEN = "-";

    /**
     * comma
     */
    public static final String COMMA = ",";

    /**
     * equal
     */
    public static final String EQUQL = "=";

    /**
     * percent
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
     * LENGTH YYYYMM
     */
    public static final int LENGTH_YYYYMM = 6;

    /**
     * FLG_ON_Y
     */

    public static final String Y = ZYPConstant.FLG_ON_Y;
    /**
     * FLG_OFF_N
     */
    public static final String N = ZYPConstant.FLG_OFF_N;

    /**
     * STS_NM_PEND_IMPT
     */
    public static final String STS_NM_PEND_IMPT = "PENDING IMPORT";

    /**
     * DISP_BY_ITEM_ALL_CSA
     */
    public static final String DISP_BY_ALL_CSA = "ALL_CSA";

    /**
     * Display by Team Name
     */
    public static final String DISP_BY_TEAM_NM = "ORD_TEAM_MSTR_NM";

    /**
     * Display by Zone Name
     */
    public static final String DISP_BY_ZONE_NM = "ORD_ZN_DESC_TXT";

    /**
     * Display by Order Processor
     */
    public static final String DISP_BY_CRAT_BY_USR_ID = "CRAT_BY_USR_ID";

    /**
     * DISP_BY_ITEM_LABEL
     */
    public static final String DISP_BY_ITEM_LABEL = "dplyByItemLbNm";

    /**
     * DISP_BY_ITEM_NM
     */
    public static final String DISP_BY_ITEM_NM = "dplyByItemNm";

    /** Aging(a.Today) */
    public static final String ORD_AGING_BCKT_DESC_TXT_TODAY = "a.Today";

    /** Aging(b.1-2) */
    public static final String ORD_AGING_BCKT_DESC_TXT_1_2 = "b.1-2";

    /** Aging(c.3-10) */
    public static final String ORD_AGING_BCKT_DESC_TXT_3_10 = "c.3-10";

    /** Aging(d.11-30) */
    public static final String ORD_AGING_BCKT_DESC_TXT_11_30 = "d.11-30";

    /** Aging(e.31-50) */
    public static final String ORD_AGING_BCKT_DESC_TXT_31_50 = "e.31-50";

    /** Aging(f.51+) */
    public static final String ORD_AGING_BCKT_DESC_TXT_OVER_50 = "f.51+";

    /**
     * DPLY_BY_CONFIG
     */
    public static enum DPLY_BY_CONFIG {
        /** 2:Order Workbench */
        TRNST_RTE_NUM("2"),
        /** 1:1st-Layer */
        TRNST_RTE_ORD_NUM1("1"),
        /** 2:2nd-Layer */
        TRNST_RTE_ORD_NUM2("2"),
        /** 3:3rd-Layer */
        TRNST_RTE_ORD_NUM3("3");
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
     * SEARCH_STATUS
     *
     */
    public static enum SEARCH_STATUS {

        /** isOpenOrder */
        OPEN_ORD        ("isOpenOrder"         , "", 1),
        /** isCloseOrder */
        CLO_ORD         ("isCloseOrder"        , "", 2),
        /** isTeamSearch */
        TEAM            ("isTeamSearch"        , "", 3),
        /** isZoneSearch */
        ZONE            ("isZoneSearch"        , "", 4);

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

        // Order Team
        /** TEAM */
        , TEAM
        /** ZONE */
        , ZONE
        /** CREATED_BY */
        , CREATED_BY;
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
        /** SUPPLY ABUSE HOLD */
        SPLY_ABUSE("4", "SUPPLY ABUSE HOLD"),
        /** PROFITABILITY HOLD */
        PRFT("5", "PROFITABILITY HOLD"),
        /** CREDIT HOLD */
        CR_HLD("6", "CREDIT HOLD"),
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
        /** SO CANCELLED */
        SO_CANC("3", "SO CANCELLED"),
        /** PO CANCELLED */
        PO_CANC("4", "PO CANCELLED"),
        /** PENDING FULFILLMENT */
        PEND_FUFL("5", "PENDING FULFILLMENT"),
        /** PENDING ALLOCATION */
        PEND_ALLOC("6", "PENDING ALLOCATION"),
        /** BACK ORDER */
        BO("7", "BACK ORDER"),
        /** WAITING PICK */
        PEND_PICK("8", "WAITING PICK"),
        /** DELIVERED TO SHOP */
        DELY_TO_SHOP("9", "DELIVERED TO SHOP"),
        /** IN SHOP/CONFIG */
        IN_SHOP_CONFIG("10", "IN SHOP/CONFIG"),
        /** PENDING SHIP */
        PEND_SHIP("11", "PENDING SHIP"),
        /** SHIPPED */
        SHIP("12", "SHIPPED"),
        /** PENDING DELIVERY CONFIRMATION */
        PEND_DELY_CONF("13", "PENDING DELIVERY CONFIRMATION"),
        /** PENDING INSTALLATION */
        PEND_ISTL("14", "PENDING INSTALLATION"),
        /** ON LOAN */
        ON_LOAN("15", "ON LOAN"),
        /** WAITING RECEIPT */
        WAIT_RCPT("16", "WAITING RECEIPT"),
        /** PENDING INVOICE */
        PEND_INV("17", "PENDING INVOICE"),
        /** BILLING HOLD */
        BLLG_HLD("18", "BILLING HOLD"),
        /** CLOSED */
        SHIP_CLO("19", "CLOSED"),
        /** CLOSED LOAN RETURN */
        CLO_LOAN_RTRN("20", "CLOSED LOAN RETURN"),
        /** CLOSED LOAN SOLD */
        CLO_LOAN_SOLD("21", "CLOSED LOAN SOLD"),
        // QC#11581 MOD Start
//        /** INVOICED */
//        INV("22", "INVOICED"),
        /** CANCELLED */
        CANC("22", "CANCELLED");
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
        /** DPLY_BY_01_ITEM_CD */
        , DPLY_BY_01_ITEM_CD
        /** DPLY_BY_01_ITEM_NM */
        , DPLY_BY_01_ITEM_NM
        /** USR_ID */
        , USR_ID
        /** ENT_CNT */
        , ENT_CNT
        /** CR_HLD_CNT */
        , CR_HLD_CNT
        /** PRFT_CNT */
        , PRFT_CNT
        /** VLD_CNT */
        , VLD_CNT
        /** ALL_OTHER_CNT */
        , ALL_OTHER_CNT
        /** BOOK_CNT */
        , BOOK_CNT
        /** INVD_TODAY_CNT */
        , INVD_TODAY_CNT
        /** INVD_CUR_MTH_CNT */
        , INVD_CUR_MTH_CNT
        /** INVD_TOT_CNT */
        , INVD_TOT_CNT
        /** AGING_TODAY_CNT */
        , AGING_TODAY_CNT
        /** AGING_1_2_CNT */
        , AGING_1_2_CNT
        /** AGING_3_10_CNT */
        , AGING_3_10_CNT
        /** AGING_11_30_CNT */
        , AGING_11_30_CNT
        /** AGING_31_50_CNT */
        , AGING_31_50_CNT
        /** AGING_OVER_50_CNT */
        , AGING_OVER_50_CNT
        /** AGING_TOT_CNT */
        , AGING_TOT_CNT
        /** CR_REBIL_TODAY_CNT */
        , CR_REBIL_TODAY_CNT
        /** CR_REBIL_CUR_MTH_CNT */
        , CR_REBIL_CUR_MTH_CNT
        /** CR_REBIL_TOT_CNT */
        , CR_REBIL_TOT_CNT
        /** BLLG_HLD_CNT */
        , BLLG_HLD_CNT
        /** BLLG_HLD_AMT */
        , BLLG_HLD_AMT
        /** RVW_DEAL_CONFIG_CNT */
        , RVW_DEAL_CONFIG_CNT
        /** RVW_SOM_CNT */
        , RVW_SOM_CNT
        /** ACPT_DEAL_CONFIG_CNT */
        , ACPT_DEAL_CONFIG_CNT
        /** ACPT_SOM_CNT */
        , ACPT_SOM_CNT;
    }

}
