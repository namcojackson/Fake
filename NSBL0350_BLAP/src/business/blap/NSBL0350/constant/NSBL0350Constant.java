/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0350.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Group Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/18   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public final class NSBL0350Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSBL0350";

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
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * @ does not exist in @.
     */
    public static final String NSBM0025E = "NSBM0025E";

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /** Service Group */
    public static final String SVC_GRP = "Service Group";

    /** Organization Code */
    public static final String ORG_CD = "Organization Code";

    /** DS Organization Unit */
    public static final String DS_ORG_UNIT = "DS Organization Unit";

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
}
