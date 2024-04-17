/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0380.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * History Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public final class NSBL0380Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSBL0380";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /** ORG_CD */
    public static final String ORG_CD = "Organization Code";

    /** DS Organization Unit */
    public static final String DS_ORG_UNIT = "DS Organization Unit";

    /** AS_OF_DATE */
    public static final String AS_OF_DATE = "As of Date";

    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * @ does not exist in @.
     */
    public static final String NSBM0025E = "NSBM0025E";

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /** An input parameter, [@], has not been set. */
    public static final String NZZM0012E = "NZZM0012E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** REGEX */
    public static final String REGEX = "([0-1][0-9])/([1-2]\\d{3})";

    /** NOT_NULL */
    public static final String NOT_NULL = "NOT_NULL_FLG";

    /** ORG_LAYER_NUM_1  */
    public static final BigDecimal ORG_LAYER_NUM_1 = new BigDecimal("1");

    /** ORG_LAYER_NUM_2  */
    public static final BigDecimal ORG_LAYER_NUM_2 = new BigDecimal("2");

    /** ORG_LAYER_NUM_3  */
    public static final BigDecimal ORG_LAYER_NUM_3 = new BigDecimal("3");

    /** ORG_LAYER_NUM_4  */
    public static final BigDecimal ORG_LAYER_NUM_4 = new BigDecimal("4");

    /** ORG_LAYER_NUM_5  */
    public static final BigDecimal ORG_LAYER_NUM_5 = new BigDecimal("5");

    /** ORG_LAYER_NUM_6  */
    public static final BigDecimal ORG_LAYER_NUM_6 = new BigDecimal("6");

    /** ORG_LAYER_NUM_7  */
    public static final BigDecimal ORG_LAYER_NUM_7 = new BigDecimal("7");

    /** ORG_LAYER_NUM_8  */
    public static final BigDecimal ORG_LAYER_NUM_8 = new BigDecimal("8");

    /** ORG_LAYER_NUM_9  */
    public static final BigDecimal ORG_LAYER_NUM_9 = new BigDecimal("9");

    /** ORG_LAYER_NUM_10  */
    public static final BigDecimal ORG_LAYER_NUM_10 = new BigDecimal("10");

    /** ORG_LAYER_NUM_11  */
    public static final BigDecimal ORG_LAYER_NUM_11 = new BigDecimal("11");

    /** begin index of year  */
    public static final int BEGININDEX_YEAR = 0;

    /** end index of year  */
    public static final int ENDINDEX_YEAR = 4;

    /** begin index of month  */
    public static final int BEGININDEX_MONTH = 4;

    /** end index of month  */
    public static final int ENDINDEX_MONTH = 6;

    /** check digit  */
    public static final int CHECK_DIGIT_2 = 10;

    /** Zero Padding  */
    public static final String ZERO_PADDING = "0";
}
