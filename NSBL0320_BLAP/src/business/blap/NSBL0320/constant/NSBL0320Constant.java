/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0320.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Select SR Summary Criteria
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public final class NSBL0320Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSBL0320";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

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

    /** NOT_NULL  */
    public static final String NOT_NULL = "1";
}
