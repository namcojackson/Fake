/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1660.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NWAL1660Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         M.Yamada        Create          N/A
 * 2018/04/11   Fujitsu         Y.Kanefusa      Update          S21_NA#22965
 * 2018/05/23   Fujitsu         N.Sugiura       Update          S21_NA#21841(Sol#495)
 *</pre>
 */
public class NWAL1660Constant {

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

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** <pre>[@] could not be acquired.</pre> */
    public static final String NWAM0325E = "NWAM0325E";

    /** <pre>Manual Amount Rate exceeds the maximum.</pre> */
    public static final String NWAM0557E = "NWAM0557E";

    /** <pre>Manual Amount Rate is lower than the minimum.</pre> */
    public static final String NWAM0631E = "NWAM0631E";

    /** <pre>The target item is specified for Amount.  Cannot enter amount less than -100,000,000,000.</pre> */
    public static final String NWAM0732E = "NWAM0732E";

    /** <pre>The target item is specified for Amount.  Cannot enter amount less than one cent. </pre> */
    public static final String NWAM0733E = "NWAM0733E";

    /** <pre>The target item is specified for Amount.  Cannot enter amount more than 100,000,000,000.</pre> */
    public static final String NWAM0734E = "NWAM0734E";

    /** <pre>The target item is specified for Amount.  Cannot enter amount less than -100%.</pre> */
    public static final String NWAM0735E = "NWAM0735E";

    /** <pre>The target item is specified for Amount.  Cannot enter amount more than 100%.</pre> */
    public static final String NWAM0736E = "NWAM0736E";

    // --------------------------------
    /** MODE_REFERENCE = "10" */
    public static final String MODE_REFERENCE = "10";

    /** MODE_REGIST = "11" */
    public static final String MODE_REGIST = "11";

    // QC#22965 2018/04/11 Add Start
    /** PROCESS_LVL_HEADER = "01" */
    public static final String PROCESS_LVL_HEADER = "01";

    /** PROCESS_LVL_LINE = "02" */
    public static final String PROCESS_LVL_LINE = "02";
    // QC#22965 2018/04/11 Add End

    /** PCT_100 = new BigDecimal(100) */
    public static final BigDecimal PCT_100 = new BigDecimal(100);

    /** PCT_M100 = new BigDecimal(-100) */
    public static final BigDecimal PCT_M100 = new BigDecimal(-100);

    /** MINUS = new BigDecimal(-1) */
    public static final BigDecimal MINUS = new BigDecimal(-1);

    /** TOT_WT_SCALE = 6 */
    public static final int TOT_WT_SCALE = 6;

    /** LEN_ORD_TAKE_MDSE = 8 */
    public static final int LEN_ORD_TAKE_MDSE = 8;

    /** MAX_AMOUNT_DIGITS = 11 */
    public static final int MAX_AMOUNT_DIGITS = 11;

    // QC#21841 2018/05/23 Add Start
    /** tax jurisdiction(State) */
    public static final String TAX_JURISDICTION_ST = "State";

    /** tax jurisdiction(County) */
    public static final String TAX_JURISDICTION_CNTY = "County";

    /** tax jurisdiction(City) */
    public static final String TAX_JURISDICTION_CTY = "City";
    // QC#21841 2018/05/23 Add End
}
