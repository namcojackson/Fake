/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB730001.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Price List Sync Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public final class NMAB730001Constant {
    /** DEFAULT_COMMIT_UNIT */
    public static final int DEFAULT_COMMIT_UNIT = 1000;

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** Amount is overflowed.(@:@ , @:@) */
    public static final String NMAM8424E = "NMAM8424E";

    /** MAX_AMT VALUE */
    public static final BigDecimal MAX_AMT = new BigDecimal("1000000000000000");
}
