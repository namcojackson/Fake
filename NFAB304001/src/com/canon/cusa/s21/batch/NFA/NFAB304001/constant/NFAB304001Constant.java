/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB304001.constant;

/**
 * <pre>
 * COA Hierarchy Creation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/21   Hitachi         T.Mizuki        Created         N/A
 * 2016/10/06   Hitachi         K.Kasai         Updated         QC#14932
 * </pre>
 */
public class NFAB304001Constant {

    /*****************************************************************
     * Other
     ****************************************************************/

    /**
     * MAX_INSERT_NUMBER:10000
     */
    public static final int MAX_INSERT_NUMBER = 10000;

    /*****************************************************************
     * Error Message
     ****************************************************************/

    /** @ is required. */
    public static final String NFAM0052E = "NFAM0052E";

    /** An input parameter, [@],  has not been set. */
    public static final String NZZM0012E = "NZZM0012E";

    // START 2016/10/06 [QC#14932, MOD]
    /** unique constraint violated. */
    public static final String ZZBM0074E = "ZZBM0074E";
    // END 2016/10/06 [QC#14932, MOD]

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /*****************************************************************
     * Message Parameter
     ****************************************************************/

    /** Interface Id */
    public static final String INTERFACE_ID = "interface Id";

}
