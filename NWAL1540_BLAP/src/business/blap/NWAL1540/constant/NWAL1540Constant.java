/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1540.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NWAL1540Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/09   Fujitsu         M.Yamada        Create          N/A
 * 2017/10/11   Fujitsu         R.Nakamura      Update          QC#21664
 *</pre>
 */
public class NWAL1540Constant {

    // Mode
    /** <pre>Mode</pre> */
    public static enum MODE {
        /** <pre>ORDER</pre> */
        ORDER("O")//
        /** <pre>QUOTE</pre> */
        , QUOTE("Q") //
        , /* */;

        /** <pre>MODE</pre> */
        private String value;

        private MODE(String value) {
            this.value = value;
        }

        /**
         * @return mode value.
         */
        public String getValue() {
            return value;
        }
    }

    //--------------------------------
    // Message ID
    //--------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * <pre>There are too many search results, there is data that cannot be displayed.</pre>
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** <pre>Too many search results.  Please narrow your search criteria and retry.</pre> */
    public static final String NZZM0007E = "NZZM0007E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    //----------
    /** max download count */
    public static final int MAX_DOWNLOAD_CNT = 65000;

    /** fetch size */
    public static final int FETCH_SIZE = 3000;

    /**  */
    public static final String CSV_FILE_NAME = "NWAL1540_ProfitabilityReview";

    /** */
    public static final String EXTN_CSV = ".csv";

    /** CSV file header Information */
    public static final String[] CSV_HEADER_INFO = new String[] {//
    //
//            "Trx Header#" //
//            , "Version#" //
//            , "Function Negotiated Deal Amount" //
//            , "Total Function Representative Revenue Amount" //
//            , "Total Function Representative Revenue Adjustment Amount" //
//            , "Total Function Final Floor Amount" //
//            , "Function Gross Profitability Amount" //
//            , "Gross Profitability Percent" //
//            , "Total Function MSRP Amount" //
//            , "Total Function Standard Floor Amount" //
//            , "Total Function Floor Adjustment Amount" //
//            , "Canon Strategic Marketing Program Order Flag" //
//            , "Total Function Canon Strategic Marketing Program Credit Amount" //
//            , "Total Function Canon Strategic Marketing Program Floor  Amount" //
//            , "Canon Strategic Marketing Program Contract Number" //
//            , "Dealer Reference Number" //
//            , "Canon Strategic Marketing Program Contract Start Date" //
//            , "Canon Strategic Marketing Program Contract End Date" //
//            , "Last Profitability Calculate User ID" //
//            , "Last Profitability Calculate Timestamp" //
//            , "Total Function Buyout Amount" //
//            , "Total Function Service Revenue Transfer(SRT) Amount" //
//            , "Total Function Service Cost Transfer(CT) Amount" //
//            , "Total Function Professional Services Amount" //
//            , "Total Function Order Management Billable Maintenance Amount" //
//            , "Total Function Alternate Gross Profit Amount" //
//            , "Alternate Gross Profitability Percent" //
//            , "Total Function Dealer Credit Amount" //
//            , "Total Function Dealer Invoice Amount" //
//            , "Total Function Reduce Compensation Amount" //
//            //
//            , "Order Profitability Transaction Category Code" //
//            , "Transaction Line Number" //
//            , "Transaction Line Subordinate Number" //
//            , "Direct Sales Order Position Number" //
//            , "DS CPO Line Number" //
//            , "DS CPO Line Sub number" //
//            , "Merchandise Code" //
//            , "MDSE Name" //
//            , "Retail Warehouse Code" //
//            , "Retail Sub Warehouse Code" //
//            , "Merchandise Inventory Cost Percent" //
//            , "Direct Sales Order Line Category Code" //
//            , "Order Quantity" //
//            , "Customer UOM Code" //
//            , "Floor Price List Code" //
//            , "Canon Strategic Marketing Program Price List Code" //
//            , "Function Dealer Credit Amount" //
//            , "Function Reduced Compensation Amount" //
//            , "Function Unit Floor Price Amount" //
//            , "Function Initial Floor Price Amount" //
//            , "Line Weight Amount Rate" //
//            , "Function General Floor Adjustment Amount" //
//            , "Function Specific Floor Adjustment Amount" //
//            , "Function Manual Floor Adjustment Amount" //
//            , "Function Floor Adjustment Amount" //
//            , "Function Canon Strategic Marketing Program Unit Credit Amount" //
//            , "Function Canon Strategic Marketing Program Credit Amount" //
//            , "Function Canon Strategic Marketing Program Floor Price Amount" //
//            , "Function Service Cost Transfer Amount" //
//            , "Function Weighted Service Cost Transfer Amount" //
//            , "Function Final Floor Price Amount" //
//            , "Function Unit List Price Amount" //
//            , "Function Net Unit Price Amount" //
//            , "Function Net Sell Price Amount" //
//            , "Function Initial Representative Revenue Amount" //
//            , "Function General Rep Revenue Adjustment Amount" //
//            , "Function Specified Rep Revenue Adjustment Amount" //
//            , "Function Manual Rep Revenue Adjustment Amount" //
//            , "Function Adjustment Representative Revenue Amount" //
//            , "Function Service Revenue Transfer Amount" //
//            , "Function Weighted Service Revenue Transfer Amount" //
//            , "Function Final Representative Revenue Amount" //
//            , "Function Unit MSRP Amount" //
//            , "Function Unit Standard Cost Amount" //
//            , "Function Final Standard Cost Amount" //
//            , "Canon Strategic Marketing Program Contract Number" //
//            , "Dealer Reference Number" //
//            , "Canon Strategic Marketing Program Contract Start Date" //
//            , "Canon Strategic Marketing Program Contract End Date" //
//            , "Change Order Flag" //
//            , "Chart Of Account Merchandise Type" //
//            , "Chart of Account Product Code" //
//            , "Merchandise Item Type" //
//            , "Last Profitability Calculate User ID" //
//            , "Last Profitability Calculate Timestamp" //
//            , "Order Profitability Rule Type Code" //
//            , "Floor Price Calculation Include Flag" //
//            , "Rep Revenue Calculate Include Flag" //
//            , "Discount Merchandise Type Flag" //
//            , "Reduced Floor Price Flag" //
//            , "Reduced Rep Revenue Flag" //
//            , "Discount Allocation Method Code" //
//            , "Dealer Credit Profitability Include Flag" //
//            , "Reduced Compensation Amount Flag" //

            "Order#" //
            , "Version#" //
            , "Negotiated Deal" //
            , "Final Rep Revenue" //
            , "Final Rep Floor" //
            , "Rep Rev Adj" //
            , "GP" //
            , "GP%" //
            , "CUSA GMD/NAD GP%" //
            , "Finder Fee" //
            , "Install Credit" //
            , "Dealer Credit" //
            , "MSRP" //
            , "Standard Floor" //
            , "Floor Adjustment" //
            , "CSMP Flag" //
            , "CSMP Credit" //
            , "CSMP#" //
            , "Buyout" //
            , "SRT" //
            , "CT" //
            , "Professional Service" //
            , "Service" //
            , "Supplies" //
            , "I/O/S" //
            , "Line#" //
            , "Config#" //
            , "Item" //
            , "Description" //
            , "Mdse Type" //
            , "Qty" //
            , "MSRP" //
            , "Unit CSMP Floor" //
            , "Unit Sell Price" //
            , "Extended Sell Price" //
            , "Unit Final Rep Floor" //
            , "Unit Final Rep Revenue" //
            , "Extended Final Floor" //
            , "Extended Final Rep Revenue" //
            , "CSMP Credits" //
            , "CSMP#" //
            , "Dealer Ref#" //
            , "CSMP Price List" //
            , "Unit List Price" //
            , "Sell Price List" //
            , "Unit Floor Price" //
            , "Floor Price List" //
            , "Line WT(%)" //
            , "Floor Adjustment" //
            , "Revenue Adjustment" //
            , "Inventory Cost" //
            , "SRT" //
            , "CT" //
            , "Misc Floor" //
            , "Misc Revenue" //
    };

    /** */
    public static final String FORMAT_ORG_DT = "yyyyMMdd";

    /** */
    public static final String FORMAT_ORG_TS = "yyyyMMddHHmmssSSS";

    /** */
    public static final String FORMAT_DT = "MM/dd/yyyy";

    /** */
    public static final String FORMAT_TS = "MM/dd/yyyy HH:mm:ss.SSS";

    /** <pre>scale of rate</pre> */
    public static final BigDecimal BIG_DECIMAL_100 = new BigDecimal(100);

    // Add Start 2017/10/11 QC#21664
    /** Order Line Source Category Code : Internal */
    public static final String ORD_PROC_NODE_PRFL_CD_33 = "33";
    // Add End 2017/10/11 QC#21664
}
