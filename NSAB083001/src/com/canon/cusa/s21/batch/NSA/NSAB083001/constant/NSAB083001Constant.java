/*
 * <pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB083001.constant;

/**
 * <pre>
 * Contract Branch Rep Update Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/07/09   Hitachi         H.Watanabe      Create          CCI-QC#60080
 *</pre>
 */
public final class NSAB083001Constant {

    /** Batch Id */
    public static final String BATCH_ID = "NSAB083001";

    /** FETCH_SIZE_MAX */
    public static final int FETCH_SIZE_MAX = 1000;

    /** MAX_COMMIT_LIMIT */
    public static final int MAX_COMMIT_LIMIT = 1000;

    /** UPD_SYSTEM_NAME */
    public static final String UPD_SYSTEM_NAME = "SYSTEM";

    /** MAX_DATA */
    public static final int MAX_DATA = 99999999;

    // ---------------------------------------
    // Messages
    // ---------------------------------------

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    public static final String ZYEM0004E = "ZYEM0004E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** @ does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /**
     * Relationships are incorrect among LOB, Branch and Rep.
     */
    public static final String NSAM0762E = "NSAM0762E";

    // ---------------------------------------
    // Update Status
    // ---------------------------------------

    /** UPD_STS_COMPLETE */
    public static final String UPD_STS_COMPLETE = "UPD_STS_COMPLETE";

    /** UPD_STS_WARNING */
    public static final String UPD_STS_WARNING = "UPD_STS_WARNING";

    /** UPD_STS_ERROR */
    public static final String UPD_STS_ERROR = "UPD_STS_ERROR";

}
