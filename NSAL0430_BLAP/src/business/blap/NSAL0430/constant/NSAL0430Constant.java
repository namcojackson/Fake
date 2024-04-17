/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0430.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            SRAA            Create          N/A
 *</pre>
 */
public class NSAL0430Constant {

    /**
     * Business ID
     */
    public static final String BIZ_ID = "NSAL0430";

    /**
     * System Error : @
     */
    public static final String NSAM0219E = "NSAM0219E";

    /**
     * No more than 2 reads can be selected. Please uncheck a read to
     * select another.
     */
    public static final String NSAM0334E = "NSAM0334E";

    /**
     * Please select 2 reads to estimate a read for this machine.
     */
    public static final String NSAM0335E = "NSAM0335E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * Invalid SVC_PHYS_MTR_READ_GRP_SQ
     */
    public static final BigDecimal INVLD_SVC_PHYS_MTR_READ_GRP_SQ = BigDecimal.ONE.negate();
}
