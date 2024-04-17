/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB300001.constant;



/**
 * <pre>
 * Auto Reversal Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   Hitachi         K.Kasai         Create          N/A
 * 2016/12/12   Fujitsu         H.Ikeda         Update          QC#15823
 *</pre>
 */
public final class NFAB300001Constant {

    /** Batch Id */
    public static final String BATCH_ID = "NFAB300001";

    /** FETCH_SIZE_MAX */
    public static final int FETCH_SIZE_MAX = 1000;

    /** LENGTH:YYYYMM */
    public static final int LEN_YYYYMM = 6;

    /** BIGIN_INDEX:YY */
    public static final int BIGIN_IDX_YY = 2;

    /** END_INDEX:YY */
    public static final int END_IDX_YY = 4;

    /** BIGIN_INDEX:MM */
    public static final int BIGIN_IDX_MM = 4;

    /** END_INDEX:MM */
    public static final int END_IDX_MM = 6;

    // ---------------------------------------
    // Column Name
    // ---------------------------------------

    /** Column Name :MAN_JRNL_ENTRY_HDR_PK */
    public static final String MAN_JRNL_ENTRY_HDR_PK = "MAN_JRNL_ENTRY_HDR_PK";

    // ---------------------------------------
    // Messages
    // ---------------------------------------

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    // START 2016/12/12 H.Ikeda [QC#15823,ADD]
    /** Message Id : NFZM0028E */
    public static final String NFZM0028E = "NFZM0028E";
    // END   2016/12/12 H.Ikeda [QC#15823,ADD]

}
