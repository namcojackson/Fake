/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB125001;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * 3rd Party FM (PO) 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CUSA            Y.Aikawa        Create          N/A
 * </pre>
 */
public interface NFBB125001Constant {
    // ** Fixed Value ** //
    // ** Common ** //
    /** Bulk Commit Limit */
    static final int INT_BULK_COM_LIM = 10000;

    // ** AP_INV_CATG_CD ** //
    /** AP_INV_CATG_CD 06 ASSET */
    static final String AP_INV_CATG_CD_06 = "06";

    // ** ACCT_INV_STS_CD ** //
    /** ACCT_INV_STS_CD 20 AUTHORIZED */
    static final String ACCT_INV_STS_CD_20 = "20";

    // ** NLZC309001 PROC_MODE_CD ** //
    /** PROC_MODE_CD 51 (AP Invoice) */
    static final String PROC_MODE_CD_51 = "51";

    // ** Message ID ** //
    /** Error Message */
    static final String NFBM0028E = "NFBM0028E";

    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_RECORD = "SELECT_RECORD";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";
    /** DB Item Column Name */
    static final String SHIP_TO_CUST_ACCT_CD = "SHIP_TO_CUST_ACCT_CD";
    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";
    /** DB Item Column Name */
    static final String INV_DT = "INV_DT";
    /** DB Item Column Name */
    static final String AP_VND_INV_SQ_NUM = "AP_VND_INV_SQ_NUM";
    /** DB Item Column Name */
    static final String STD_COST_AMT = "STD_COST_AMT";
    /** DB Item Column Name */
    static final String TOT_ASSET_QTY = "TOT_ASSET_QTY";
    // START 2018/04/10 J.Kim [QC#25381,ADD]
    /** DB Item Column Name */
    static final String AP_VND_INV_LINE_NUM = "AP_VND_INV_LINE_NUM";
    // END 2018/04/10 J.Kim [QC#25381,ADD]
}
