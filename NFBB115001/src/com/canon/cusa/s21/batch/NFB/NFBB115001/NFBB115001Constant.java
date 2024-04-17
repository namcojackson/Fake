package com.canon.cusa.s21.batch.NFB.NFBB115001;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Update PAID status for AP Invoice Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/16/2016   CUSA            Y.Aikawa        Create          N/A
 * 2017/12/26   Hitachi         J.Kim           Update          QC#22458
 * </pre>
 */

public interface NFBB115001Constant {
    // ** Fixed Value ** //
    // ** Common ** //
    /** Bulk Commit Limit */
    static final int INT_BULK_COM_LIM = 10000;
    /** yyyyMMdd */
    static final String YYYYMMDD = "yyyyMMdd";
    /** AP_VND_INV_SQ_NUM 00 */
    static final String AP_VND_INV_SQ_NUM_00 = "00";

    // ** Message ID ** //
    /** Error Message */
    static final String NFBM0028E = "NFBM0028E";

    // START 2017/12/26 J.Kim [QC#22458,DEL]
    // // ** AP_MAINT_INV_STS_CD ** //
    // /** AP_MAINT_INV_STS_CD (Approved) */
    // static final String AP_MAINT_INV_STS_CD_APPROVED = "20";
    // END 2017/12/26 J.Kim [QC#22458,DEL]

    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_RECORD = "SELECT_RECORD";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";
    /** DB Item Column Name */
    static final String AP_INV_NUM = "AP_INV_NUM";
    /** DB Item Column Name */
    static final String ACCT_INV_STS_CD = "ACCT_INV_STS_CD";

    // ** Varchar Const ** //
    /** Varchar Const(NFBB115001_AP_MAINT_INV_STS_CD) */
    public static final String VARCHAR_CONST_NFBB115001_AP_MAINT_INV_STS_CD = "NFBB115001_AP_MAINT_INV_STS_CD";

}
