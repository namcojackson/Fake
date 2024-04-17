/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB065001.constant;

/**
 *<pre>
 * Service Contract Branch Dimension Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NSAB065001Constant {

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

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";
    /** . */
    public static final String PARAM_SYS_SRC_CD = "sysSrcCd";

    /** DB COLUMN NAME */
    /** . */
    public static final String SVC_CONTR_BR_CD = "SVC_CONTR_BR_CD";
    /** . */
    public static final String SVC_LINE_BIZ_CD = "SVC_LINE_BIZ_CD";
    /** . */
    public static final String SVC_LINE_BIZ_DESC_TXT = "SVC_LINE_BIZ_DESC_TXT";
    /** . */
    public static final String SVC_LINE_BIZ_ACTV_FLG = "SVC_LINE_BIZ_ACTV_FLG";
    /** . */
    public static final String SVC_LINE_BIZ_EFF_FROM_DT = "SVC_LINE_BIZ_EFF_FROM_DT";
    /** . */
    public static final String SVC_LINE_BIZ_EFF_THRU_DT = "SVC_LINE_BIZ_EFF_THRU_DT";
    /** . */
    public static final String SVC_RG_PK = "SVC_RG_PK";
    /** . */
    public static final String SVC_RG_DESC_TXT = "SVC_RG_DESC_TXT";
    /** . */
    public static final String SVC_RG_ACTV_FLG = "SVC_RG_ACTV_FLG";
    /** . */
    public static final String SVC_RG_EFF_FROM_DT = "SVC_RG_EFF_FROM_DT";
    /** . */
    public static final String SVC_RG_EFF_THRU_DT = "SVC_RG_EFF_THRU_DT";
    /** . */
    public static final String SVC_RG_ORG_CD = "SVC_RG_ORG_CD";
    /** . */
    public static final String SVC_CONTR_BR_DESC_TXT = "SVC_CONTR_BR_DESC_TXT";
    /** . */
    public static final String SVC_CONTR_BR_ACTV_FLG = "SVC_CONTR_BR_ACTV_FLG";
    /** . */
    public static final String SVC_CONTR_BR_EFF_FROM_DT = "SVC_CONTR_BR_EFF_FROM_DT";
    /** . */
    public static final String SVC_CONTR_BR_EFF_THRU_DT = "SVC_CONTR_BR_EFF_THRU_DT";
}
