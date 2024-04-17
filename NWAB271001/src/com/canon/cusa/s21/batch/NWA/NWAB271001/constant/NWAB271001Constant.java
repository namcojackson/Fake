/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB271001.constant;

/**
 * <pre>
 * Shipping Order Notification to Vendor Extract Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/09/07   Hitachi         H.Watanabe      Create          QC#60256
 *</pre>
 */
public final class NWAB271001Constant {

    /** Batch Id */
    public static final String BATCH_ID = "NWAB271001";

    /** FETCH_SIZE_MAX */
    public static final int FETCH_SIZE_MAX = 1000;

    /** DATE_FORMAT */
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /**  BEFORE_1HOUR */
    public static final int  BEFORE_1HOUR = -1;

    // ---------------------------------------
    // Messages
    // ---------------------------------------

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Failed to calculate last run time */
    public static final String NWAM8468E = "NWAM8468E";
}
