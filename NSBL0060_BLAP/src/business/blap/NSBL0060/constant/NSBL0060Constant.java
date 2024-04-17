/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0060.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/20/2013   Hitachi         T.Aoyagi        Create          N/A
 * 08/05/2013   Hitachi         Y.Igarashi      Update          QC1339
 *</pre>
 */
public class NSBL0060Constant {

    /**
     * Date store pattern
     */
    public static final String DT_STORE_PATTERN = "yyyyMMdd";

    /**
     * Default hour
     */
    public static final String DEFAULT_HOUR = "000";
    
    /**
     * Default minute
     */
    public static final String DEFAULT_MINUTE = "00";

    /**
     * Max hour
     */
    public static final String MAX_HOUR = "999";

    /**
     * Max response time
     */
    public static final String MAX_RSP_TIME = "999:59";

    /**
     * Max meter count
     */
    public static final BigDecimal MAX_METER_COUNT = new BigDecimal("9999999999");

    /**
     * One hour(int)
     */
    public static final int ONE_HOUR = 60;

    /**
     * One day(Long)
     */
    public static final long ONE_DAY = 24 * 60 * 60 * 1000;

    /**
     * String zero
     */
    public static final String STR_ZERO = "0";

    /**
     * Colon
     */
    public static final String COLON = ":";

    /**
     * Column COUNT
     */
    public static final String COL_COUNT = "COUNT";

    /**
     * Column AVG
     */
    public static final String COL_AVG = "AVG";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * Too many search results.  Please narrow your search criteria and retry
     */
    public static final String ZZZM9002W = "ZZZM9002W";

    // QC1339 Add start
    /**
     * ASTERISK (*)
     */
    public static final String ASTERISK = "*";
    // QC1339 Add end
}
