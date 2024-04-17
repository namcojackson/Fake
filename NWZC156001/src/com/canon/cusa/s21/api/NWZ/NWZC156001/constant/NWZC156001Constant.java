/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC156001.constant;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Constant Class
 */
public class NWZC156001Constant {

    // Process Mode
    /** <pre>Process Mode : called by Order Entry. calculate the profitability. no DB update.</pre> */
    public static final String MODE_ONLINE = "01";

    /** <pre>Process Mode : called by Order Entry. calculate the profitability. insert new version to DB.</pre> */
    public static final String MODE_UPDATE = "02";

    /** <pre>Process Mode : called by Quote Entry. calculate the profitability. no DB update.</pre> */
    public static final String MODE_QUOTE = "03";

// 2016/02/10 S21_NA#2939 Delete Start
//    /** <pre>Process Mode : called by IMPORT Entry. calculate the profitability. no DB update.</pre> */
//    public static final String MODE_IMPORT = "04";
// 2016/02/10 S21_NA#2939 Delete End

    /** <pre>Process Mode : called by Credit & ReBill Entry. calculate the profitability. insert new version to DB.</pre> */
    public static final String MODE_CREDIT = "05";

    /** <pre>Process Mode List</pre> */
// 2016/02/10 S21_NA#2939 Delete Start
//    public static final List<String> MODE_LIST = Arrays.asList(MODE_ONLINE, MODE_UPDATE, MODE_QUOTE, MODE_IMPORT, MODE_CREDIT);
    public static final List<String> MODE_LIST = Arrays.asList(MODE_ONLINE, MODE_UPDATE, MODE_QUOTE, MODE_CREDIT);
// 2016/02/10 S21_NA#2939 Delete End

    //
    /** <pre>ORD_PROC_NODE_PRFL</pre> */
    public static enum ORD_PROC_NODE_PRFL {
        /** <pre>EQUIPMENT</pre> */
        EQUIPMENT("30")//
        /** <pre>SUPPLIES</pre> */
        , SUPPLIES("31") //
        /** <pre>PARTS</pre> */
        , PARTS("32") //
        , /* */;

        /** <pre>ORD_PROC_NODE_PRFL_CD</pre> */
        private String value;

        private ORD_PROC_NODE_PRFL(String value) {
            this.value = value;
        }

        /**
         * <pre>get ORD_PROC_NODE_PRFL value
         * @return ORD_PROC_NODE_PRFL_CD
         * </pre>
         */
        public String getValue() {
            return value;
        }
    }

    //
    /** <pre>MDSE_TP_CTX_TP</pre> */
    public static enum MDSE_TP_CTX_TP {
        /** <pre>PRFT_SPLY_AMT_ITEMS</pre> */
        PRFT_SPLY_AMT_ITEMS //
        /** <pre>PRFT_SVC_AMT_ITEMS</pre> */
        , PRFT_SVC_AMT_ITEMS //
        /** <pre>PROF_SERVICES</pre> */
        , PROF_SERVICES //
        /** <pre>PRFT_SRT_MDSE_TP</pre> */
        , PRFT_SRT_MDSE_TP
        /** <pre>PRFT_CT_MDSE_TP</pre> */
        , PRFT_CT_MDSE_TP
        /** <pre>PRFT_MREV_MDSE_TP</pre> */
        , PRFT_MREV_MDSE_TP
        /** <pre>PRFT_MFLR_MDSE_TP</pre> */
        , PRFT_MFLR_MDSE_TP //
        , /* */;
    }

    /** <pre>MDSE_TP_CTX_TP</pre> */
    public static enum ORD_LINE_CTX_TP {
        /** <pre>LEASE_BUYOUT</pre> */
        LEASE_BUYOUT //
        , /* */;
    }

    //
    /** <pre>scale of amount</pre> */
    public static final int AMT_SCALE = 2;

    /** <pre>scale of percent</pre> */
    public static final int PCT_SCALE = 2;

    /** <pre>scale of rate</pre> */
//    public static final int RATE_SCALE = 6;// 2016/03/10 S21_NA#2939
    public static final int RATE_SCALE = 4;

    /** <pre>scale of rate</pre> */
    public static final BigDecimal BIG_DECIMAL_100 = new BigDecimal(100);

    /** blank */
    public static final String STR_BLANK = "";

    // Add Start 2017/10/10 QC#21664
    /** Inventory Owner Code : CSA */
    public static final String INVTY_OWNER_CD_CSA = "CSA";

    /** Order Line Source Category Code : Internal */
    public static final String ORD_LINE_SRC_CATG_CD_INTERNAL = "I";

    /** Order Line Source Category Code : Internal */
    public static final String ORD_PROC_NODE_PRFL_CD_33 = "33";
    // Add End 2017/10/10 QC#21664

    /** Max Pct */
    public final static BigDecimal MAX_PRC_VAL = new BigDecimal("99999999999999999.99");

    /** Min Pct */
    public final static BigDecimal MIN_PRC_VAL = new BigDecimal("-99999999999999999.99");

    // Messages
    /** <pre>NWZM0977E : Invalid value is set for process mode.</pre> */
    public static final String NWZM0977E = "NWZM0977E";

    /** <pre>NWZM0011E : "Data Global Company Code" is required.</pre> */
    public static final String NWZM0011E = "NWZM0011E";

    /** <pre>NWZM0346E : Sales Date is required.</pre> */
    public static final String NWZM0346E = "NWZM0346E";

    /** <pre>NWZM0027E : "Transaction Header Number" is required.</pre> */
    public static final String NWZM0027E = "NWZM0027E";

    /** <pre>NWZM1515E : "DS Order Category Code" is required.</pre> */
    public static final String NWZM1515E = "NWZM1515E";

    /** <pre>NWZM1253E : An input parameter "DS Order Type Code"  has not been set.</pre> */
    public static final String NWZM1253E = "NWZM1253E";

    /** <pre>NWZM1254E : An input parameter "DS Order Reason Code"  has not been set..</pre> */
    public static final String NWZM1254E = "NWZM1254E";

    /** <pre>NWZM1155E : Price Based Date of the parameter is not set.</pre> */
    public static final String NWZM1155E = "NWZM1155E";

    /** <pre>NWZM1516E : Pricing Calculation Date of the parameter is not set.</pre> */
    public static final String NWZM1516E = "NWZM1516E";

    /** <pre>NWZM1517E : Transaction Detail Category of the parameter is not set.</pre> */
    public static final String NWZM1517E = "NWZM1517E";

    /** <pre>NWZM0089E : "Transaction Line Number" is required.</pre> */
    public static final String NWZM0089E = "NWZM0089E";

    /** <pre>NWZM0043E : "Transaction Line Sub Number" is required.</pre> */
    public static final String NWZM0043E = "NWZM0043E";

    /** <pre>NWZM0021E : Merchandise Code is required.</pre> */
    public static final String NWZM0021E = "NWZM0021E";

    /** <pre>NWZM1518E : "DS Order Line Category Code" is required.</pre> */
    public static final String NWZM1518E = "NWZM1518E";

    /** <pre>NWZM0046E : "Order Quantity" is required.</pre> */
    public static final String NWZM0046E = "NWZM0046E";

    /** <pre>NWZM1406E : "Floor Price List Code" is required.</pre> */
    public static final String NWZM1406E = "NWZM1406E";

    /** <pre>NWZM0352E : "Function Net Unit Price Amount" is required.</pre> */
    public static final String NWZM0352E = "NWZM0352E";

    /** <pre>NWZM1519E : "Function Net Unit Sell Price Amount" is required.</pre> */
    public static final String NWZM1519E = "NWZM1519E";

    /** <pre>NWZM1520E : "Function Net Sell Price Amount" is required.</pre> */
    public static final String NWZM1520E = "NWZM1520E";

    /** <pre>NWZM1521E : It failed to get the Original Order.</pre> */
    public static final String NWZM1521E = "NWZM1521E";

    /** <pre>NWZM1522E : It failed to insert the ORD_PRFT_DTL.</pre> */
    public static final String NWZM1522E = "NWZM1522E";

    /** <pre>NWZM1523E : It failed to get the Profitability Rule Type.</pre> */
    public static final String NWZM1523E = "NWZM1523E";

    /** <pre>NWZM1524E : Negotiated Deal Amount of the parameter is not set.</pre> */
    public static final String NWZM1524E = "NWZM1524E";

}
