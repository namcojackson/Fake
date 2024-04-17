/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB066001.constant;

/**
 *<pre>
 * Model Dimension Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NSAB066001Constant {

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

    /** message Item: Batch Process Date. */
    public static final String MSG_ITEM_BATCH_PROC_DATE = "Batch Process Date";

    /** message Item: DWH Target Year Month. */
    public static final String MSG_ITEM_DWH_TRGT_YR_MTH = "DWH Target Year Month";

    /** SSM set Key Name: CLR_MTR_GRP_TP_CD */
    public static final String KEY_CLR_MTR_GRP_TP_CD = "clrMtrGrpTpCd";

    /** SSM set Key Name: BW_MTR_GRP_TP_CD */
    public static final String KEY_BW_MTR_GRP_TP_CD = "bwMtrGrpTpCd";

    /** SSM set Key Name: COLOR_MTR_FLG_ON */
    public static final String KEY_COLOR_MTR_FLG_ON = "colorMtrFlgOn";

    /** SSM set Key Name: BW_MTR_FLG_ON */
    public static final String KEY_BW_MTR_FLG_ON = "bwMtrFlgOn";

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";
    /** DB COLUMN NAME */
    /** . */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** . */
    public static final String MDL_ID = "MDL_ID";
    /** . */
    public static final String MDL_GRP_ID = "MDL_GRP_ID";
    /** . */
    public static final String SVC_SEG_CD = "SVC_SEG_CD";
    /** . */
    public static final String MTR_GRP_PK = "MTR_GRP_PK";
    /** . */
    public static final String MDL_RGTN_STS_CD = "MDL_RGTN_STS_CD";
    /** . */
    public static final String MDL_ACTV_FLG = "MDL_ACTV_FLG";
    /** . */
    public static final String MDL_GRP_DESC_TXT = "MDL_GRP_DESC_TXT";
    /** . */
    public static final String MDL_DESC_TXT = "MDL_DESC_TXT";
    /** . */
    public static final String SVC_SEG_DESC_TXT = "SVC_SEG_DESC_TXT";
    /** . */
    public static final String MTR_GRP_DESC_TXT = "MTR_GRP_DESC_TXT";
    /** . */
    public static final String T_MDL_NM = "T_MDL_NM";
    /** . */
    public static final String MTR_GRP_TP_CD = "MTR_GRP_TP_CD";
    /** . */
    public static final String MTR_GRP_TP_DESC_TXT = "MTR_GRP_TP_DESC_TXT";
    /** . */
    public static final String MAX_COPY_PER_DAY_TOT_CNT = "MAX_COPY_PER_DAY_TOT_CNT";
    /** . */
    public static final String MAX_COPY_PER_DAY_BLACK_CNT = "MAX_COPY_PER_DAY_BLACK_CNT";
    /** . */
    public static final String MAX_COPY_TEST_CNT = "MAX_COPY_TEST_CNT";
    /** . */
    public static final String RCLL_INTVL_DAYS_AOT = "RCLL_INTVL_DAYS_AOT";
    /** . */
    public static final String RCLL_COPY_VOL_CNT = "RCLL_COPY_VOL_CNT";
    /** . */
    public static final String RCLL_GLBL_INTVL_DAYS_AOT = "RCLL_GLBL_INTVL_DAYS_AOT";
    /** . */
    public static final String RCLL_GLBL_COPY_VOL_CNT = "RCLL_GLBL_COPY_VOL_CNT";

}
