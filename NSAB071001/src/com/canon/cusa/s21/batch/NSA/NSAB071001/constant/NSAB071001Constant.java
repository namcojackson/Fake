/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB071001.constant;

/**
 *<pre>
 * Monthly Usage History
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/29/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NSAB071001Constant {

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

    /**  Value : CATG_CD_MDL_GRP */
    public static final String CATG_CD_MDL_GRP = "MDL_GRP";

    /**  Value : "CATG_CD_MDL_ID" */
    public static final String CATG_CD_MDL_ID = "MDL";

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";
    /** . */
    public static final String PARAM_TRGT_YR_MTH = "trgtYrMth";
    /** . */
    public static final String PARAM_DS_MTR_READ_TP_GRP_CD = "dsMtrReadTpGrpCd";
    /** . */
    public static final String PARAM_CATG_CD_MDL_GRP = "catgCdMdlGrp";
    /** . */
    public static final String PARAM_CATG_CD_MDL_ID = "catgCdMdlId";

    /** DB COLUMN NAME */
    /** . */
    public static final String GLBL_CMPY_CD  = "GLBL_CMPY_CD";
    /** . */
    public static final String FCT_MLY_HIST_USG_PK = "FCT_MLY_HIST_USG_PK";
    /** . */
    public static final String DWH_TRGT_YR_MTH = "DWH_TRGT_YR_MTH";
    /** . */
    public static final String FCT_CATG_CD = "FCT_CATG_CD";
    /** . */
    public static final String FCT_CRAT_DT = "FCT_CRAT_DT";
    /** . */
    public static final String SVC_BY_LINE_BIZ_TP_CD = "SVC_BY_LINE_BIZ_TP_CD";
    /** . */
    public static final String MDL_GRP_ID = "MDL_GRP_ID";
    /** . */
    public static final String MDL_GRP_DESC_TXT = "MDL_GRP_DESC_TXT";
    /** . */
    public static final String MDL_ID = "MDL_ID";
    /** . */
    public static final String T_MDL_NM = "T_MDL_NM";
    /** . */
    public static final String COPY_USG_QTY = "COPY_USG_QTY";
    /** . */
    public static final String YR_ON_YR_COPY_PCT = "YR_ON_YR_COPY_PCT";
    /** . */
    public static final String MTH_ON_MTH_COPY_PCT = "MTH_ON_MTH_COPY_PCT";
    /** . */
    public static final String YR_ON_YR_COPY_QTY = "YR_ON_YR_COPY_QTY";
    /** . */
    public static final String MTH_ON_MTH_COPY_QTY = "MTH_ON_MTH_COPY_QTY";
    /** . */
    public static final String MAX_COPY_QTY = "MAX_COPY_QTY";
    /** . */
    public static final String MIN_COPY_QTY = "MIN_COPY_QTY";
    /** . */
    public static final String ACCT_ALL_MDL_GRP_COPY_PCT = "ACCT_ALL_MDL_GRP_COPY_PCT";

}
