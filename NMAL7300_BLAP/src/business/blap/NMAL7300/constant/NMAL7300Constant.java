/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7300.constant;

/**
 *<pre>
 * NMAL7300Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/10   Fujitsu         Y.Kanefusa      Create          S21_NA#20249
 *</pre>
 */
public class NMAL7300Constant {
    /** Check Type : Integer */
    public static final String CHK_TYPE_INT = "02";

    /** Check Type : Decimal Amount */
    public static final String CHK_TYPE_DEC_AMT = "04";

    /** Check Type : Decimal Percent */
    public static final String CHK_TYPE_DEC_PCT = "06";

    /** Length Quantity:10. */
    public static final int QTY_LEN = 10;

    /** Length Weight:15. */
    public static final int WT_LEN = 15;

    /** Length Amount:19. */
    public static final int AMT_LEN = 19;

    /** Length Percent:5. */
    public static final int PCT_LEN = 5;

    public static final int DEF_DIGIT_NUM = 2;

}
