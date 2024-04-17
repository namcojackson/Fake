/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */

package com.canon.cusa.s21.batch.NWC.NWCB033001.constant;

/**
 * <pre>
 * CFS PO# Inbound Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/02   Fujitsu         W.Honda         Create          N/A
 * 2016/11/04   Fujitsu         W.Honda         Update          QC#15698
 * 2018/03/14   Fujitsu         A.Kosai         Update          S21_NA#24446
 *</pre>
 */


public class NWCB033001Constant {

    /** BIZ_APP_ID */
    public static final String BIZ_APP_ID = "NWCB0330";

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "Global Company Code";

    /** Interface Id */
    public static final String INTFC_ID = "Interface Id";

    /** Transaction ID */
    public static final String TRX_ID = "Transaction ID";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 100;

    /** CFS_FUND_APP_NUM */
    public static final String COL_CFS_FUND_APP_NUM = "CFS_FUND_APP_NUM";

    /** CFS_FUND_CUST_NM */
    public static final String COL_CFS_FUND_CUST_NM = "CFS_FUND_CUST_NM";

    /** CFS_FUND_PO_NUM */
    public static final String COL_CFS_FUND_PO_NUM = "CFS_FUND_PO_NUM";

    /** CFS_FUND_PO_AMT */
    public static final String COL_CFS_FUND_PO_AMT = "CFS_FUND_PO_AMT";

    /** CFS_FUND_CR_AMT */
    public static final String COL_CFS_FUND_CR_AMT = "CFS_FUND_CR_AMT";

    /** LTST_BKNG_CMNT_TS */
    public static final String COL_LTST_BKNG_CMNT_TS = "LTST_BKNG_CMNT_TS";

    /** LTST_BKNG_CMNT_TXT */
    public static final String COL_LTST_BKNG_CMNT_TXT = "LTST_BKNG_CMNT_TXT";

    /** LEASE_CMPY_USR_ID */
    public static final String COL_LEASE_CMPY_USR_ID = "LEASE_CMPY_USR_ID";

    /** LEASE_CMPY_USR_NM */
    public static final String COL_LEASE_CMPY_USR_NM = "LEASE_CMPY_USR_NM";

    // 2016/11/04 QC#15698 Add Start
    /** LEASE_CMPY_USR_NM */
    public static final String COL_EZINTIME = "EZINTIME";
    // 2016/11/04 QC#15698 Add End

    // 2018/03/14 S21_NA#24446 Add Start
    /** CFS_PO_CRAT_TS */
    public static final String COL_CFS_PO_CRAT_TS = "CFS_PO_CRAT_TS";
    // 2018/03/14 S21_NA#24446 Add End

    // =================================================
    // Message Code
    // =================================================

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** It failed to register @ Table.[@] */
    public static final String NWCM0109E = "NWCM0109E";

    // 2016/11/04 QC#15698 Add Start
    /** It failed to update @ Table.[@] */
    public static final String NWCM0110E = "NWCM0110E";
    // 2016/11/04 QC#15698 Add End

    /** [@] is overflowed. */
    public static final String NWCM0145E = "NWCM0145E";

}
