/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB032001.constant;

/** 
 *<pre>
 * Billing Calculation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class NSAB032001Constant {

    /** SSM ID : Base Charge */
    public static final String SSM_ID_BASE_CHRG = "getBaseChrg";

    /** SSM ID : Usage Charge */
    public static final String SSM_ID_USG_CHRG = "getUsgChrg";

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Variable item start position */
    public static final int VAR_ITEM_START_POSN = 12;

    /** Contract Closing Day :  0 */
    public static final String DAY0 = "0";

    /** Contract Closing Day : 99 */
    public static final String DAY99 = "99";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** It failed to insert the @ table. */
    public static final String NZZM0011E = "NZZM0011E";

    /** It failed to update the @ table. */
    public static final String NZZM0012E = "NZZM0012E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";
}
