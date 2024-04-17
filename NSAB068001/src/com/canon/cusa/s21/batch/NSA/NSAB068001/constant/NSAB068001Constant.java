/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB068001.constant;

/**
 *<pre>
 * Contract Shell Productivity Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/14/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NSAB068001Constant {

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

    /** DS_CONTR_DTL_STS NONE. */
    public static final String STS_NONE = "NONE";

    /** RPT_SHELL_TP_CD SHELL */
    public static final String SHELL = "SHELL";

    /** SQL PARAM NAME */
    /** . */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";
    /** . */
    public static final String PARAM_TRGT_DT = "trgtDt";
    /** . */
    public static final String PARAM_STS_NONE = "none";
    /** . */
    public static final String PARAM_STS_SAVE = "save";
    /** . */
    public static final String PARAM_STS_SBMT = "sbmt";
    /** . */
    public static final String PARAM_STS_ACTV = "actv";
    /** . */
    public static final String PARAM_STS_IACT = "iact";
    /** . */
    public static final String PARAM_STS_RNWH = "rnwh";
    /** . */
    public static final String PARAM_STS_SIGD = "sigd";
    /** . */
    public static final String PARAM_STS_TRMD = "trmd";
    /** . */
    public static final String PARAM_STS_CANC = "canc";
    /** . */
    public static final String PARAM_STS_RNPO = "rnpo";
    /** . */
    public static final String PARAM_STS_DRFT = "drft";
    /** . */
    public static final String PARAM_STS_ENTE_CD = "entered";
    /** . */
    public static final String PARAM_STS_EFCT_CD = "efective";
    /** . */
    public static final String PARAM_STS_EXPR_CD = "expired";
    /** . */
    public static final String PARAM_STS_CACL_CD = "cancelled";
    /** . */
    public static final String PARAM_STS_DRFT_CD = "drftCd";
    /** . */
    public static final String PARAM_STS_TERM_CD = "terminated";
    /** . */
    public static final String PARAM_MACB = "macb";
    /** . */
    public static final String PARAM_MACU = "macu";
    /** . */
    public static final String PARAM_MACBU = "macbu";
    /** . */
    public static final String PARAM_FLEET = "fleet";
    /** . */
    public static final String PARAM_AGG = "agg";
    /** . */
    public static final String PARAM_INVOICE = "invoice";
    /** . */
    public static final String PARAM_CREDIT_MEMO = "creditMemo";
    /** . */
    public static final String PARAM_PROCESSED = "processed";
    /** . */
    public static final String PARAM_REVENUE = "revenue";
    /** . */
    public static final String PARAM_UNEARND_REVENUE = "unearndRevenue";
    /** . */
    public static final String PARAM_CPO = "cpo";
    /** . */
    public static final String PARAM_RPT_SHELL_TP_CD = "shell";

    /** DB COLUMN NAME */
    /** . */
    public static final String SVC_LINE_BIZ_CD = "SVC_LINE_BIZ_CD";
    /** . */
    public static final String SVC_RG_PK = "SVC_RG_PK";
    /** . */
    public static final String SVC_RG_DESC_TXT = "SVC_RG_DESC_TXT";
    /** . */
    public static final String SVC_CONTR_BR_CD = "SVC_CONTR_BR_CD";
    /** . */
    public static final String PSN_CD = "PSN_CD";
    /** . */
    public static final String PSN_FIRST_NM = "PSN_FIRST_NM";
    /** . */
    public static final String PSN_MID_NM = "PSN_MID_NM";
    /** . */
    public static final String PSN_LAST_NM = "PSN_LAST_NM";
    /** . */
    public static final String MTD_SHELL_CRAT_AMT = "MTD_SHELL_CRAT_AMT";
    /** . */
    public static final String MTD_SHELL_CLO_AMT = "MTD_SHELL_CLO_AMT";
    /** . */
    public static final String AGE_TO_ACTV_SHELL_AMT = "AGE_TO_ACTV_SHELL_AMT";
    /** . */
    public static final String AVG_AGE_SHELL_PEND_AMT = "AVG_AGE_SHELL_PEND_AMT";
    /** . */
    public static final String SHELL_PEND_TOT_AMT = "SHELL_PEND_TOT_AMT";
    /** . */
    public static final String PEND_UP_TO_DAYS_AMT_05 = "PEND_UP_TO_DAYS_AMT_05";
    /** . */
    public static final String PEND_UP_TO_DAYS_AMT_10 = "PEND_UP_TO_DAYS_AMT_10";
    /** . */
    public static final String PEND_UP_TO_DAYS_AMT_30 = "PEND_UP_TO_DAYS_AMT_30";
    /** . */
    public static final String PEND_UP_TO_DAYS_AMT_60 = "PEND_UP_TO_DAYS_AMT_60";
    /** . */
    public static final String PEND_OVER_DAYS_AMT_60 = "PEND_OVER_DAYS_AMT_60";
    /** . */
    public static final String ACTV_CONTR_AMT = "ACTV_CONTR_AMT";
    /** . */
    public static final String ACTV_CONTR_MACH_AMT = "ACTV_CONTR_MACH_AMT";
    /** . */
    public static final String MTD_NEW_CONTR_AMT = "MTD_NEW_CONTR_AMT";
    /** . */
    public static final String MTD_NEW_ACTV_SHELL_AMT = "MTD_NEW_ACTV_SHELL_AMT";
    /** . */
    public static final String AVG_DAYS_SHELL_ACTV_AMT = "AVG_DAYS_SHELL_ACTV_AMT";
    /** . */
    public static final String RNW_HLD_CONTR_AMT = "RNW_HLD_CONTR_AMT";
    /** . */
    public static final String RNW_PLAN_CONTR_AMT = "RNW_PLAN_CONTR_AMT";
    /** . */
    public static final String MTD_RNW_PROC_AMT = "MTD_RNW_PROC_AMT";
    /** . */
    public static final String MTD_RNW_ACTV_AMT = "MTD_RNW_ACTV_AMT";
    /** . */
    public static final String RNW_PEND_CONTR_AMT = "RNW_PEND_CONTR_AMT";
    /** . */
    public static final String MTR_READ_PLN_AMT = "MTR_READ_PLN_AMT";
    /** . */
    public static final String MTR_READ_CPTR_AMT = "MTR_READ_CPTR_AMT";
    /** . */
    public static final String MTR_READ_PAST_DUE_AOT = "MTR_READ_PAST_DUE_AOT";
    /** . */
    public static final String MTR_DUE_NEXT_IN_DAYS_AOT = "MTR_DUE_NEXT_IN_DAYS_AOT";
    /** . */
    public static final String MACH_DUE_BY_EOM_AOT = "MACH_DUE_BY_EOM_AOT";
    /** . */
    public static final String MTD_MACH_READ_CNT = "MTD_MACH_READ_CNT";
    /** . */
    public static final String MTD_MACH_PAST_DUE_AOT = "MTD_MACH_PAST_DUE_AOT";
    /** . */
    public static final String MTD_CONTR_MOD_AMT = "MTD_CONTR_MOD_AMT";
    /** . */
    public static final String MTD_CONTR_TRMN_AMT = "MTD_CONTR_TRMN_AMT";
    /** . */
    public static final String MTD_CONTR_LINE_CRAT_AMT = "MTD_CONTR_LINE_CRAT_AMT";
    /** . */
    public static final String MTD_CR_DOL_PROC_AMT = "MTD_CR_DOL_PROC_AMT";
    /** . */
    public static final String MTD_CR_DOL_PROC_CNT = "MTD_CR_DOL_PROC_CNT";
    /** . */
    public static final String MTD_NET_MAN_DOL_AMT = "MTD_NET_MAN_DOL_AMT";
    /** . */
    public static final String MTD_INCDT_CLO_AMT = "MTD_INCDT_CLO_AMT";
    /** . */
    public static final String MTD_CR_CNT = "MTD_CR_CNT";
    /** . */
    public static final String MTD_INV_CNT = "MTD_INV_CNT";
    /** . */
    public static final String RPT_SHELL_TP_CD = "RPT_SHELL_TP_CD";

}
