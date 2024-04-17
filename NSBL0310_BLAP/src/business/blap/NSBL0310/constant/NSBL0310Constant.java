/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0310.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Service Request By Manager
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Harada        Create          N/A
 *</pre>
 */
public class NSBL0310Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NSBL0310";

    /** Max detail num */
    public static final int MAX_DETAIL = 50;

    /** Max detail num */
    public static final int MAX_SEARCH = 1000;

    /** 60 min */
    public static final int HOUR_MIN = 60;

    /** EFF_THRU_LIMIT_DT */
    public static final String LMT_DT = "29991231";

    /** NOT_NULL  */
    public static final String NOT_NULL = "1";

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

    /** Organization Code */
    public static final String ORG_CD = "Organization Code";

    /** DS Organization Unit */
    public static final String DS_ORG_UNIT = "DS Organization Unit";

    // ----------- Error Message ------------

    /** Please fill out the search criteria for 1 field or more. */
    public static final String NSBM0001E = "NSBM0001E";

    /** The data @ does not exist in the master. */
    public static final String NSBM0011E = "NSBM0011E";

    /** No search results found. */
    public static final String NSBM0002E = "NSBM0002E";

    /** */
    public static final String NSBM0009W = "NSBM0009W";

    /**
     * @ does not exist in @.
     */
    public static final String NSBM0025E = "NSBM0025E";


}
