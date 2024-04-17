/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570.constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 *<pre>
 * NWAL1570Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 * 2016/07/26   Fujitsu         K.Sato          Update          QC#11581
 * 2016/11/22   Fujitsu         K.Sato          Update          QC#15760
 * 2017/06/22   Fujitsu         H.Sugawara      Update          QC#17893
 * 2017/11/21   Fujitsu         T.Aoi           Update          QC#22550
 * 2017/12/13   Fujitsu         M.Ohno          Update          QC#22691
 * 2018/07/05   Fujitsu         M.Ishii         Update          QC#25786
 *</pre>
 */
public class NWAL1570Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1570";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1570Scrn00";
    /** Screen ID 01 */
    public static final String SCRN_ID_01 = "NWAL1570Scrn01";
    /** Screen ID 02 */
    public static final String SCRN_ID_02 = "NWAL1570Scrn02";
    /** Screen ID Other */
    public static final String SCRN_ID_OTH = "Other Screen";

    // --------------------------------
    // Authority
    // --------------------------------
    /** All reference authority */
    public static final String ALL_REF_AUTH = "NWAL1570T010";

    /** Reference only team authority */
    public static final String REF_ONLY_TEAM_AUTH = "NWAL1570T020";

    /** Reference only self-registration authority */
    public static final String REF_ONLY_SELF_RGTN_AUTH = "NWAL1570T030";

    // --------------------------------
    // Message ID   
    // --------------------------------
    /** For [@], please enter  [@] or a later date. */
    public static final String NWAM0223E = "NWAM0223E";

    /** For [@], please specify [@] or a larger quantity. */
    public static final String NWAM0224E = "NWAM0224E";

    // Add Start 2017/06/22 QC#17893
    /** For "@", please enter a date earlier than "@".  */
    public static final String NWAM0765E = "NWAM0765E";
    // Add End 2017/06/22 QC#17893

    /** Please specify one or more search criteria. */
    public static final String NWAM0785E = "NWAM0785E";

    /** Please specify the period of date within [@] days. */
    public static final String NWAM0786E = "NWAM0786E";

    /** [@] field has too many digits entered. */
    public static final String NWAM0860W = "NWAM0860W";

    /** Only numeric values can be entered in [@]. */
    public static final String NWAM0915E = "NWAM0915E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /**
     * Display By(Default)
     */
    public static final String DPLY_BY_DEF = "SRC_REF_OR_CPO_ORD_NUM";

    /** 8 Digit Mode */
    public static final String EIGHT_DIGIT_MODE = "08";

    /** 10 Digit Mode */
    public static final String TEN_DIGIT_MODE = "10";

    /** All Mode */
    public static final String ALL_MODE = "AL";

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
     * TAB_PARAM_ORD_LINE
     */
    public static final String TAB_PARAM_ORD_LINE = "D";

    /** Event Name(Search Order) */
    public static final String EVENT_NAME_SERACH_ORDER = "SearchOrder";

    /**
     * STS_NM_PEND_IMPT
     */
    public static final String STS_NM_PEND_IMPT = "PENDING IMPORT";

    /** SRCH_TXT_MAX */
    public static final int SRCH_TXT_MAX = 1000;

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
        /** CREDIT REVIEW */
        CR_HLD("6", "CREDIT REVIEW"),
        // 2017/12/13 QC#22691 mod start
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

    /** Bill Event List */
    public static final List<String> BILL_EVENT_LIST;
    static {
        List<String> billEventList = new ArrayList<String>();
        billEventList.add("OpenWin_BillToAcctNumber");
        billEventList.add("OpenWin_BillToLocNumber");
        billEventList.add("OpenWin_BillToName");
        BILL_EVENT_LIST = Collections.unmodifiableList(billEventList);
    }

    /** Ship Event List */
    public static final List<String> SHIP_EVENT_LIST;
    static {
        List<String> shipEventList = new ArrayList<String>();
        shipEventList.add("OpenWin_ShipToAcctNumber");
        shipEventList.add("OpenWin_ShipToLoctNumber");
        shipEventList.add("OpenWin_ShipToName");
        SHIP_EVENT_LIST = Collections.unmodifiableList(shipEventList);
    }

    /** Sold Event List */
    public static final List<String> SOLD_EVENT_LIST;
    static {
        List<String> soldEventList = new ArrayList<String>();
        soldEventList.add("OpenWin_SoldToAcctNumber");
        soldEventList.add("OpenWin_SoldToLocNumber");
        soldEventList.add("OpenWin_SoldToName");
        SOLD_EVENT_LIST = Collections.unmodifiableList(soldEventList);
    }

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

    /** Index Number 8 */
    public static final int IDX_8 = 8;

    /** Index Number 9 */
    public static final int IDX_9 = 9;

    /** Index Number 10 */
    public static final int IDX_10 = 10;

    /** Index Number 13 */
    public static final int IDX_13 = 13;

    /** Index Number 20 */
    public static final int IDX_20 = 20;

    /** Index Number 30 */
    public static final int IDX_30 = 30;

    /** Index Number 33 */
    public static final int IDX_33 = 33;

    /** Index Number 50 */
    public static final int IDX_50 = 50;

    /** Index Number 100 */
    public static final int IDX_100 = 100;

    /** F01 Column Count */
    public static final int F01_COLUMN_CNT = 71;

}
