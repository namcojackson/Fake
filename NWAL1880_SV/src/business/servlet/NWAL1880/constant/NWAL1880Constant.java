/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1880.constant;

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

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1880Scrn00";

    // --------------------------------
    // Authority
    // --------------------------------
    /** All reference authority */
    public static final String ALL_REF_AUTH = "NWAL1880T010";

    /** Reference only team authority */
    public static final String REF_ONLY_TEAM_AUTH = "NWAL1880T020";

    /** Reference only self-registration authority */
    public static final String REF_ONLY_SELF_RGTN_AUTH = "NWAL1880T030";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] field has too many digits entered. */
    public static final String NWAM0860W = "NWAM0860W";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Event Name(Search Order) */
    public static final String EVENT_NAME_SERACH_ORDER = "SearchOrder";

    /** Order Counts(Entered) */
    public static final String ORD_CNT_ENT = "OrdCntEnt";

    /** Order Counts(Credit Hold) */
    public static final String ORD_CNT_CR_HLD = "OrdCntCrHld";

    /** Order Counts(Profitability Hold) */
    public static final String ORD_CNT_PRFT_HLD = "OrdCntPrftHld";

    /** Order Counts(Validation Hold) */
    public static final String ORD_CNT_VLD_HLD = "OrdCntVldHld";

    /** Order Counts(All Other) */
    public static final String ORD_CNT_ALL_OTH = "OrdCntAllOth";

    /** Order Counts(Booked) */
    public static final String ORD_CNT_BOOK = "OrdCntBook";

    /** Order Invoiced(Today) */
    public static final String ORD_INV_TODAY = "OrdInvToday";

    /** Order Invoiced(Current Month) */
    public static final String ORD_INV_CUR_MTH = "OrdInvCurMth";

    /** Order Invoiced(Total) */
    public static final String ORD_INV_TOT = "OrdInvTot";

    /** Order Aging(Today) */
    public static final String ORD_AGING_TODAY = "OrdAgingToday";

    /** Order Aging(1-2) */
    public static final String ORD_AGING_1_2 = "OrdAging1_2";

    /** Order Aging(3-10) */
    public static final String ORD_AGING_3_10 = "OrdAging3_10";

    /** Order Aging(11-30) */
    public static final String ORD_AGING_11_30 = "OrdAging11_30";

    /** Order Aging(31-50) */
    public static final String ORD_AGING_31_50 = "OrdAging31_50";

    /** Order Aging(50+) */
    public static final String ORD_AGING_OVER_50 = "OrdAgingOrver50";

    /** Order Aging(Total) */
    public static final String ORD_AGING_TOT = "OrdAgingTot";

    /** Credit & Rebill(Today) */
    public static final String CR_REBIL_TOTAY = "CrRebilToday";

    /** Credit & Rebill(Current Month) */
    public static final String CR_REBIL_CUR_MTH = "CrRebilCurMth";

    /** Credit & Rebill(Total) */
    public static final String CR_REBIL_TOT = "CrRebilTot";

    /** Billing Hold(Count) */
    public static final String BILL_HLD_CNT = "BillHldCnt";

    /** Billing Hold(Amount) */
    public static final String BILL_HLD_AMT = "BillHldAmt";

    /** Order Source(Review Deal Config) */
    public static final String ORD_SRC_RVW_DEAL_CONFIG = "OrdSrcRvwDealConfig";

    /** Order Source(Review Deal Config) */
    public static final String ORD_SRC_RVW_SOM = "OrdSrcRvwSom";

    /** Order Source(Accepted Deal Config) */
    public static final String ORD_SRC_ACPT_DEAL_CONFIG = "OrdSrcAcptDealConfig";

    /** Order Source(Accepted Deal Config) */
    public static final String ORD_SRC_ACPT_SOM = "OrdSrcAcptSom";

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

    /** DT_FMT_YYYYMM */
    public static final String DT_FMT_YYYYMMDD = "yyyyMMdd";

    /** SRCH_TXT_MAX */
    public static final int SRCH_TXT_MAX = 1000;

    /** Display By Item Name Label(Team) */
    public static final String DISP_BY_ITEM_LBL_NM_TEAM = "Team";

    /** Display By Item Name Label(Zone) */
    public static final String DISP_BY_ITEM_LBL_NM_ZN = "Zone";

    /** Display By Item Name Label(Order Processor) */
    public static final String DISP_BY_ITEM_LBL_NM_USER = "Order Processor";

    /** CONDITION_IS_NULL */
    public static final String CONDITION_IS_NULL = "-";

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

}
