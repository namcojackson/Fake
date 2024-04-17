/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB031001.constant;

/** 
 *<pre>
 * Billing Data Stage
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Hitachi         T.Aoyagi        Create          N/A
 * 08/29/2016   Hitachi         K.Kishimoto     Update          QC13799
 *</pre>
 */
public class NSAB031001Constant {

    /** SSM ID : Base Charge */
    public static final String SSM_ID_BASE_CHRG = "getBaseChrg";

    /** SSM ID : Usage Charge */
    public static final String SSM_ID_USG_CHRG = "getUsgChrg";

    // Add Start 08/29/2016 <QC#13799>
    /** SSM ID : Usage Charge */
    public static final String SSM_ID_INCONSISTENCY_RELATION = "getInconsistencyRelation";
    // Add End   08/29/2016 <QC#13799>

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

    /** Process has been completed. Row#[@]. */
    public static final String NSAM0611I = "NSAM0611I";

    /** Unit of output */
    public static final int LOG_UNIT = 10;

    /** Unit of output */
    public static final String NSAB0310_LOG_UNIT = "NSAB0310_LOG_UNIT";

    /** NSAB0310 log. */
    public static final String NSAB0310_LOG = "NSAB0310_LOG";
}
