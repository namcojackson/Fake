/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPB.NPBB002001.constant;

/**
 * <pre>
 * Business ID : NPBB0020 Create Used Parts Return Request Batch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2016   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public class NPBB002001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NPBB002001";

    /** . */
    public static final String RUN_MODE_RF = "1";

    /** . */
    public static final String RUN_MODE_DP = "2";

    /** . */
    public static final String DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

    // =================================================
    // e-mail
    // =================================================
    /** Mail Template ID */
    public static final String MAIL_TEMPLATE_ID = "NPBB0020M001";

    /** Mail Group ID(To) */
    public static final String MAIL_TO_GROUP_ID = "NPBB0020";

    /** Mail Group ID(From) */
    public static final String MAIL_FROM_GROUP_ID = "FROM0005";

    /** Mail Language */
    public static final String ML_LANG = "en";

    /** Mail Language Code */
    public static final String ML_LANG_CD = "ISO-8859-1";

    /** . */
    public static final String EMAIL_PARAM_BATCH_ID = "batchId";

    /** . */
    public static final String EMAIL_PARAM_ERR_DATE = "errDate";

    /** . */
    public static final String EMAIL_PARAM_MESSAGE = "message";

    /**
     * Line Separator Form
     */
    public static final String LINE_SEPARATOR = "line.separator";

    // =================================================
    // Message Code
    // =================================================
    /** Global Company Code is mandatory. */
    public static final String NPAM1479E = "NPAM1479E";

    /** Sales date cannot be obtained. */
    public static final String NPAM1480E = "NPAM1480E";

    /** Cannot retrieve mail template <Template ID [@]> */
    public static final String NLAM1272E = "NLAM1272E";

    /** It failed to register an email. */
    public static final String NPZM0161E = "NPZM0161E";

    /** RTRN_TO_VND_CD In Refurbish is mandatory.(Marchandise Code[@]) */
    public static final String NPBM0008E = "NPBM0008E";

    // =================================================
    // Var Char Const Name
    // =================================================
    /** Var Char Const Name */
    public static final String PR_CRAT_SYSTEM_USER = "PR_CRAT_SYSTEM_USER";

    // =================================================
    // DB Param
    // =================================================
    /** . */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String DB_PARAM_MDSE_COST_TP_CD_STANDARD = "mdseCostTpStandard";

    /** . */
    public static final String DB_PARAM_MDSE_COST_TP_CD_RECOVERY = "mdseCostTpAssetRecovery";

    /** . */
    public static final String DB_PARAM_RTRN_CTRL_TP_CD_LIST = "rtrnCtrlTpCdList";

    /** . */
    public static final String DB_PARAM_EFF_FROM_DT = "effFromDt";

    /** . */
    public static final String DB_PARAM_EFF_THRU_DT = "effThruDt";

    /** . */
    public static final String DB_PARAM_LOC_STS_CD = "locStsCd";

    /** . */
    public static final String DB_PARAM_MDSE_ITEM_STS_CD = "mdseItemStsCd";

    /** . */
    public static final String DB_PARAM_CMPY_INVTY_FLG = "cmpyInvtyFlg";

    /** . */
    public static final String DB_PARAM_INVTY_ACCT_CD = "invtyAcctCd";

    // =================================================
    // DB Column
    // =================================================
    /** . */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** . */
    public static final String RTL_SWH_CD = "RTL_SWH_CD";

    /** . */
    public static final String RTRN_CTRL_TP_CD = "RTRN_CTRL_TP_CD";

    /** . */
    public static final String PRCH_REQ_APVL_STS_CD = "PRCH_REQ_APVL_STS_CD";

    /** . */
    public static final String PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    /** . */
    public static final String PRCH_GRP_CD = "PRCH_GRP_CD";

    /** . */
    public static final String INVTY_AVAL_QTY = "INVTY_AVAL_QTY";

    /** . */
    public static final String MDSE_CD = "MDSE_CD";

    /** . */
    public static final String RTRN_TO_PRNT_VND_CD = "RTRN_TO_PRNT_VND_CD";

    /** . */
    public static final String RTRN_TO_VND_CD = "RTRN_TO_VND_CD";

    /** . */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** . */
    public static final String STK_STS_CD = "STK_STS_CD";

}
