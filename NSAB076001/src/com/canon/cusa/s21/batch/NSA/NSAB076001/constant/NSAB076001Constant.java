/*
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB076001.constant;

import java.math.BigDecimal;

/**
 * <pre>    
 * Supply Revenue Cost Year Summary Creation
 *  
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/09   Hitachi         K.Ochiai        Create          N/A
 * 2018/07/18   Hitachi         K.Kitachi       Update          QC#26241
 *</pre>
 */

public class NSAB076001Constant {

    /** PROGRAM_ID */
    public static final String PROGRAM_ID = "NSAB076001";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** Error Message : Sales date cannot be obtained. */
    public static final String NSAM0178E = "NSAM0178E";

    /** It failed to register [@]. */
    public static final String NSAM0469E = "NSAM0469E";

    /** It failed to delete @ Table. */
    public static final String NSAM0475E = "NSAM0475E";

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** ONE_HUNDRED */
    public static final int MULTIPLY_ONE_HUNDRED = 100;

    // START 2018/07/18 K.Kitachi [QC#26241, ADD]
    /** MAX_VAL_AMT */
    public static final BigDecimal MAX_VAL_AMT = new BigDecimal("999999999999999.99");

    /** MAX_VAL_PCT */
    public static final BigDecimal MAX_VAL_PCT = new BigDecimal("999.99");
    // END 2018/07/18 K.Kitachi [QC#26241, ADD]
}
