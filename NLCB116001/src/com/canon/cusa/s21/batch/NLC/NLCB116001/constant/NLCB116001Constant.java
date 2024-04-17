/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB116001.constant;

/**
 *<pre>
 * Inventory Aging Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/28/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NLCB116001Constant {

    /** fetchSize */
    public static final int FETCH_SIZE = 1000;

    /** commitCount */
    public static final int DEFAULT_COMMIT_COUNT = 1000;

    /** [@] has no value. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Failed to insert "@". */
    public static final String ZZZM9012E = "ZZZM9012E";

    /** Failed to delete "@". */
    public static final String ZZZM9014E = "ZZZM9014E";

    /** message Item: GlobalCompanyCode. */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Commit Transaction Count. */
    public static final String MSG_ITEM_COMMIT_TRANCNT = "Commit Transaction Count";

    /** message Item: Batch Process Date. */
    public static final String MSG_ITEM_BATCH_PROC_DATE = "Batch Process Date";

    /** message Item: Target Year Month. */
    public static final String MSG_ITEM_DWH_TRGT_YR_MTH = "Target Year Month";

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";
    /** . */
    public static final String PARAM_TRGT_YR_MTH = "trgtYrMth";

    /** DB COLUMN NAME */
    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** . */
    public static final String DWH_TRGT_YR_MTH = "DWH_TRGT_YR_MTH";
    /** . */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";
    /** . */
    public static final String RTL_WH_CD = "RTL_WH_CD";
    /** . */
    public static final String RTL_SWH_CD = "RTL_SWH_CD";
    /** . */
    public static final String RTL_WH_CATG_CD = "RTL_WH_CATG_CD";
    /** . */
    public static final String MDSE_CD = "MDSE_CD";
    /** . */
    public static final String STK_IN_DT = "STK_IN_DT";
    /** . */
    public static final String INVTY_AGING_DAYS_AOT = "INVTY_AGING_DAYS_AOT";
    /** . */
    public static final String INVTY_AGING_BCKT_DESC_TXT = "INVTY_AGING_BCKT_DESC_TXT";
    /** . */
    public static final String AGE_INVTY_QTY_01 = "AGE_INVTY_QTY_01";
    /** . */
    public static final String AGE_INVTY_QTY_02 = "AGE_INVTY_QTY_02";
    /** . */
    public static final String AGE_INVTY_QTY_03 = "AGE_INVTY_QTY_03";
    /** . */
    public static final String AGE_INVTY_OVER_QTY = "AGE_INVTY_OVER_QTY";
}
