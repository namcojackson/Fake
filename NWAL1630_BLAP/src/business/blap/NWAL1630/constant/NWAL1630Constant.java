/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1630.constant;

/**
 *<pre>
 * NWAL1630Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 * 2017/09/22   Fujitsu         T.Ogura         Update          S21_NA#18859(Sol#154)
 * 2019/01/23   Fujitsu         K.Ishizuka      Update          S21_NA#29446
 *</pre>
 */
public class NWAL1630Constant {

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Error occurred in Pricing API. */
    public static final String NWAM0588E = "NWAM0588E";

    /** Price List could not be obtained. */
    public static final String NWZM0422E = "NWZM0422E";

    /** Price List cannot be identified. */
    public static final String NWAM0670E = "NWAM0670E";

    /** The combination of parameters "Serial#" and "Contract#" is incorrect. */
    public static final String NWAM0945E = "NWAM0945E";
    
    // 2019/01/23 S21_NA#29446 Add Start
    /** Black/White cap original */
    public static final String TERM_COND_CAP_BW_ORG_ATTRB_NM = "TERM_COND_CAP_BW_ORG_ATTRB_NM";

    /** Color cap original */
    public static final String TERM_COND_CAP_CLR_ORG_ATTRB_NM = "TERM_COND_CAP_CLR_ORG_ATTRB_NM";

    /** Total cap original */
    public static final String TERM_COND_CAP_TOT_ORG_ATTRB_NM = "TERM_COND_CAP_TOT_ORG_ATTRB_NM";
    // 2019/01/23 S21_NA#29446 Add End
}
