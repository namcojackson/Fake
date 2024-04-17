/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1350.constant;

/**
 *<pre>
 * NSAL1350Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/26   Hitachi         T.Mizuki        Create          N/A
 * 2019/11/22   Fujitsu         C.Hara          Update          QC#54213
 *</pre>
 */
public class NSAL1350Constant {

    //--------------------------------
    // Message ID
    //--------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** <pre>There are too many search results, there is data that cannot be displayed.</pre> */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    // 2019/11/21 QC#54213 Add Start
    /** Bill Only Warehouse Code */
    public static final String BILL_ONLY_WH = "BO";
    // 2019/11/21 QC#54213 Add End

}
