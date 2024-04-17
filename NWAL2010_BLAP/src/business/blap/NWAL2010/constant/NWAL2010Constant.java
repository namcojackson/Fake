/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2010.constant;

/**
 *<pre>
 * NWAL2010Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Fujitsu         C.Yokoi         Create          N/A
 * 2022/12/16   Hitachi         R.Takau         Update          QC#60823
 *</pre>
 */
public class NWAL2010Constant {

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** The credit card information is not registered. */
    public static final String NWZM0971E = "NWZM0971E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";
    
    // START 2022/12/16 R.Takau [QC#60823, ADD]
    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";
    
    /** @ ended abnormally. @ */
    public static final String NWAM0447E = "NWAM0447E";
    
    // END 2022/12/16 R.Takau [QC#60823, ADD]
    
    /** Common Return */
    public static final String BTN_RETURN = "CMN_Return";

    /** CONST */
    public static final String TCEPPS_KEY_NM = "TCEPPS_KEY_NM";

    /** CONST */
    public static final String TCEPPS_TOKEN_EXPR_INTVL = "TCEPPS_TOKEN_EXPR_INTVL";
}
